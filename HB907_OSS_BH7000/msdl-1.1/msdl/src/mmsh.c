/***********************************************************************
 *    mmsh.c:  downloading via mmsh (Microsoft Media Service over HTTP)
 ***********************************************************************
 * Copyright (C) 2007 metro <me_t_ro@yahoo.com>
 *
 * This file is part of msdl, media stream downloader
 *
 * This file is based on mmsh implementation of mplayer,
 * and Windows Media Player transaction I saw through
 * packet monitoring program, wireshark (http://www.wireshark.org/)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston MA 02110-1301, USA.
 *
 ***********************************************************************/



#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include <sys/types.h>
#include <sys/socket.h>

#include "msdl.h"
#include "msdllib.h"
#include "display.h"
#include "network.h"
#include "http.h"
#include "asf.h"
#include "mmsh.h"


/* Windows Media Player stuff */
const char mmsh_useragent[] = "User-Agent: NSPlayer/11.0.5721.5145";
const char mmsh_xclientguid[] = "Pragma: xClientGUID={3300AD50-2C39-46C0-AE0A-E1D79F0FD2D8}";
const char mmsh_xacceptauth[] = "X-Accept-Authentication: Negotiate, NTLM, Digest, Basic";


/*
 * definitions of type of ASF streaming
 */
enum {
  
    ASF_Unknown_e,
    ASF_Seekable_e,
    ASF_Nonseekable_e,
    ASF_Authenticate_e,

} ASF_streaming_types;



/* 
 * asf chunk magics
 */
enum {

    ASF_STREAMING_CLEAR     = 0x4324,          /* $C */
    ASF_STREAMING_DATA      = 0x4424,          /* $D */
    ASF_STREAMING_END_TRANS = 0x4524,          /* $E */
    ASF_STREAMING_HEADER    = 0x4824,          /* $H */
    ASF_STREAMING_IGNORE    = 0x4d24,          /* $M */
  
    ASF_STREAMING_DATA2     = 0x44a4,
};




static struct mmsh_ctrl_t *new_mmsh_ctrl_t(void);

static void free_mmsh_ctrl_t(struct mmsh_ctrl_t *ctrlt);

static struct http_header_t *mmsh_request(struct stream_t *stream,
					  struct http_header_t *http_hdr);

static int mmsh_parse_response(struct mmsh_ctrl_t *mmsh_ctrl,
			       struct http_header_t *http_hdr);

static int asf_streaming(struct asf_stream_chunk_t *stream_chunk);

static int mmsh_get_asf_header(struct stream_t *stream,uint8_t **asfheader,
			       struct asf_stream_chunk_t *first_chunk);

static int mmsh_get_media_packet(struct stream_t *stream,
				 uint8_t *buffer, size_t max_size);

/*
 * allocate/free mmsh_ctrl_t
 */
static struct mmsh_ctrl_t *new_mmsh_ctrl_t(void)
{
    struct mmsh_ctrl_t *ctrlt = 
	(struct mmsh_ctrl_t *)xmalloc(sizeof(struct mmsh_ctrl_t));
    ctrlt->hinfo = new_asf_headerinfo_t();
    return ctrlt;
}


static void free_mmsh_ctrl_t(struct mmsh_ctrl_t *ctrlt)
{
    if(ctrlt->hinfo) free_asf_headerinfo_t(ctrlt->hinfo);
    free(ctrlt);
}




/*
 * mmsh_request : make request to the server
 */
static struct http_header_t *mmsh_request(struct stream_t *stream,
					  struct http_header_t *http_hdr)
{
  
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    struct mmsh_ctrl_t *mmsh_ctrl = stream_ctrl->mmsh_ctrl;
    struct url_t *url = NULL;
  
    char *ptr;
    int i, enable;
    int asf_streams_count = 0,stream_id;
    char str[BUFSIZE_1K];
    uint32_t offset_lo = 0,offset_hi = 0;
  
  
    /* Sanity Check */
    if(stream_ctrl == NULL || http_hdr == NULL) return NULL;

    url = stream->url;
    if(url == NULL) return NULL;

  
    http_set_field(http_hdr, "Accept: */*");
    http_set_field(http_hdr, mmsh_useragent);
  
    http_add_basic_authentication(http_hdr,url->username,url->password);
  

    /* proxy check. use "http://" for proxy. */
    if(url->http_proxy) {
	char *new_url = xmalloc(7 + strlen(url->file) + 1);
	/* use "http://" because it is http proxy!! */
	sprintf(new_url,"http://%s",url->file);
	http_set_uri(http_hdr,new_url);
	free(new_url);
    }
    else {
	http_set_uri(http_hdr,url->filepath);
    }
  
    snprintf(str,BUFSIZE_1K,"Host: %.220s",url->hostname); /* %.220s:%d,port*/
    http_set_field(http_hdr,str);
  
    http_set_field(http_hdr,mmsh_xclientguid);
  
    if(!stream_ctrl->packet_count) {
	offset_lo = offset_hi = 0xffffffff;
    }
  
    snprintf(str,BUFSIZE_1K,
	     "Pragma: no-cache,rate=1.000,stream-time=0,stream-offset=%u:%u,packet-num=%u,max-duration=%u",
	     offset_lo,offset_hi,stream_ctrl->packet_count,0);
    /* for some reason this is required */
    http_set_field(http_hdr,str);
  

    switch(mmsh_ctrl->streaming_type) {
    
    case ASF_Seekable_e:
    case ASF_Nonseekable_e:
    

	http_set_field(http_hdr,"Pragma: xPlayStrm=1");
	http_set_field(http_hdr,"Pragma: version11-enabled=1");
    
	ptr = str;
	ptr += sprintf(ptr,"Pragma: stream-switch-entry=");
	if(mmsh_ctrl->hinfo->streams->n_audio > 0) {
	    /*printf("mmsh_ctrl->n_audio : %d\n",mmsh_ctrl->n_audio); */
	    for(i = 0; i < mmsh_ctrl->hinfo->streams->n_audio; i++) {
		stream_id = mmsh_ctrl->hinfo->streams->audio_streams[i];
		/*printf("stream_id = %d %d\n",stream_id,mmsh_ctrl->audio_id); */
		if(stream_id == mmsh_ctrl->hinfo->streams->audio_id) {
		    enable = 0; /* enable stream  */
		}
		else {
		    enable = 2; /* disable stream */
		}
		asf_streams_count++;
		ptr += sprintf(ptr,"ffff:%d:%d ",stream_id,enable);
	    }
	}
	if(mmsh_ctrl->hinfo->streams->n_video > 0) {
	    for(i = 0; i < mmsh_ctrl->hinfo->streams->n_video; i++) {
		stream_id = mmsh_ctrl->hinfo->streams->video_streams[i];
		if(stream_id == mmsh_ctrl->hinfo->streams->video_id) {
		    enable = 0; /* enable stream  */
		}
		else {
		    enable = 2; /* disable stream */
		}
		asf_streams_count++;
		ptr += sprintf(ptr,"ffff:%d:%d ",stream_id,enable);
	    }
	}
	http_set_field(http_hdr,str);

	snprintf(str,BUFSIZE_1K,"Pragma: stream-switch-count=%d",asf_streams_count);
	http_set_field(http_hdr,str);


	break;

    case ASF_Unknown_e:
	/* The First request must come here. */
	break;

    default:
	display(MSDL_ERR,"unknown asf stream\n");
    }
  
    http_set_field(http_hdr,"Connection: Close");
    http_request_get(http_hdr);

    return http_hdr;
}



/*
 *  asf_header_check :  check if http_hdr->body is ASF object.
 *                     return value :  0 ... it was NOT asf object
 *                                     1 ... OK it was ASF object!
 */
int asf_header_check(struct http_header_t *http_hdr)
{
    struct asf_obj_header_t *asfobjh;
    if(!http_hdr) return 0;
    if(http_hdr->body == NULL || http_hdr->body_len < sizeof(struct asf_obj_header_t))
	return 0;
    asfobjh = (struct asf_obj_header_t *)http_hdr->body;
    /* get GUID first 4 byte for check */
    if(get32_le(asfobjh->guid) == 0x75b22630) return 1;
    return 0;
}



/*
 * returns content_type value.
 * this function is based on MPlayer /MPlayer/stream/asf_streaming.c
 */
int mmsh_streaming_type(char *content_type,char *features,
			struct http_header_t *http_hdr)
{
    if(content_type == NULL) return ASF_Unknown_e;

    
    if(!strcasecmp(content_type, "application/octet-stream") ||
       !strcasecmp(content_type, "application/vnd.ms.wms-hdr.asfv1") ||
       !strcasecmp(content_type, "application/x-mms-framed") ||
       !strcasecmp(content_type, "video/x-ms-asf")) {               
    
	if(strstr(features, "seekable") ) {
	    display(MSDL_VER,"seekable ASF stream\n");
	    return ASF_Seekable_e;
	} else {
	    display(MSDL_VER,"non-seekable ASF stream\n");
	    return ASF_Nonseekable_e;
	}
    }
    else {
	display(MSDL_VER,"content type: %s\n",content_type);
	return ASF_Unknown_e;
    }
}



/*
 * mmsh_parse_response :  basically parse Pragma lines.
 *          return value       : -1 ... failure
 *                     other values ... success or authenticate
 */
static int mmsh_parse_response(struct mmsh_ctrl_t *mmsh_ctrl,
			       struct http_header_t *http_hdr)
{
    char *content_type, *pragma;
    char features[128] = "\0";
    size_t len;
  
    
    switch(http_hdr->status_code) {
    case 200:
	break;
    
    case 401: /* Authentification required */
	mmsh_ctrl->streaming_type = ASF_Authenticate_e;
	return http_hdr->status_code;
    
    default:
	display(MSDL_ERR,"request failed: %d \n%s\n",
		http_hdr->status_code,http_hdr->reason_phrase);
	return -1;
    }
    
    content_type = http_get_field(http_hdr, "Content-Type");

    pragma = http_get_field(http_hdr, "Pragma");
    
    while(pragma != NULL) {
	char *comma_ptr = NULL;
	char *end;
	
	do {
      
	    if(!strncasecmp(pragma,"features=",9)) { /* found. */
		/* only interested in features. */
	
		pragma += 9; /* after "features=" */
		end = strstr(pragma,",");
		if(!end) len = strlen(pragma);
		else 	 len = end - pragma;

		/* 64 - 1. should be smaller than this. */	
		if(len > sizeof(features) - 1) {
		    len = sizeof(features) - 1;
		}
		strncpy(features,pragma,len);
		features[len] = '\0';
		break;
	    }
	    comma_ptr = strstr(pragma,",");   /* find next comma */
	    if(comma_ptr != NULL)  {
		pragma = comma_ptr+1;           /* skip comma      */
		while(*pragma == ' ') pragma++; /* skip %20        */
	    } /*if there are more ',' pragma points after ','.   */
      
	} while(comma_ptr != NULL);
    
	pragma = http_get_next_field(http_hdr); /* keep getting Pragma lines. */
    
    }
    mmsh_ctrl->streaming_type
	= mmsh_streaming_type(content_type,features,http_hdr);
    
    return http_hdr->status_code;
}



/*
 * interpret asf_stream_chunk.
 *           return value: type of chunk
 */
static int asf_streaming(struct asf_stream_chunk_t *stream_chunk)
{
  
    /* little endian */
    display(MSDL_DBG,"ASF chunk == type: 0x%02x, size: %d (0x%02x), seq: 0x%04x\n",
	    le2me_16(stream_chunk->type),
	    le2me_16(stream_chunk->size),le2me_16(stream_chunk->size), 
	    le2me_32(stream_chunk->seqnum));
  
    switch(le2me_16(stream_chunk->type)) {
    case ASF_STREAMING_CLEAR:          /* $C  Clear ASF configuration  */
	display(MSDL_VER,"Clearing ASF stream configuration\n");
	break;
    
    case ASF_STREAMING_DATA:           /* $D  Data follows             */
    case ASF_STREAMING_DATA2:
	/*display(MSDL_VER,"Data follows\n"); */
	break;
    
    case ASF_STREAMING_END_TRANS:      /* $E  End of transfer          */
	display(MSDL_VER,"Transfer complete!!\n");
	break;

    case ASF_STREAMING_HEADER:         /* $H  ASF Header chunk follows */
	/* display(MSDL_VER,"ASF header chunk follows\n"); */
	break;
    case ASF_STREAMING_IGNORE:
	break;

    default:
	display(MSDL_ERR | MSDL_DBG,"not ASF chunk: [0x%x]!!\n",
		le2me_16(stream_chunk->type));
    }
    return le2me_16(stream_chunk->type);
}



/*
 * fallback from mmsh to http WITHOUT re-sending GET
 * message to server.
 * this will be more pleasant to server.
 *
 * this function must be called when
 * [ no data in http_hdr->body (already moved)]
 * [ http_hdr is alreadyh set ]
 *    return value:    
 */
int mmsh_fallback_to_http(struct stream_t *stream,
			  struct http_header_t *http_hdr)
{
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    int ret;
    display(MSDL_ERR | MSDL_VER,"probablly not mmsh. fallback to http.\n");
  
    /* quit mmsh, and start http */
    free_mmsh_ctrl_t(stream_ctrl->mmsh_ctrl);
    stream_ctrl->http_ctrl = new_http_ctrl_t();
  
    stream->start = http_streaming_start;
    stream->read  = http_streaming_read;
    stream->close = http_streaming_close;

    /* read content-length */
    ret = http_parse_response(stream_ctrl->http_ctrl,http_hdr);
    stream->stream_ctrl->file_size = stream_ctrl->http_ctrl->content_length;
  
  
    /* 
       free_http_header(http_hdr);  don't free it here.
       it will be free()ed later
    */
  
    stream_ctrl->protocol = HTTP;
  
    return ret;
}



/*
 * get entire asf header.
 * this function is needed because asf_header might come
 * within more than 2 packets.
 *  *** CAUTION ***
 *  DO NOT FORGET TO free() asfheader later!!
 *  all header is going to stored in buffer.
 *                    return value:   -1  ... failure
 *                                  other ... length of asf header
 */
static int mmsh_get_asf_header(struct stream_t *stream,uint8_t **asfheader,
			       struct asf_stream_chunk_t *first_chunk)
{
    struct asf_stream_chunk_t chunk;
    struct asf_stream_chunk_extra_t chunk_extra;
    struct asf_header_t asfh;
    uint8_t *buffer = NULL;
  
    int asf_header_len = 0;
    int total;
    int ret;
  
    total = 0;
    memcpy(&chunk,first_chunk,sizeof(chunk));
  
    for(;;) {
	/* read chunk extra */
	read_data(stream,&chunk_extra,sizeof(chunk_extra));
	ret = le2me_16(chunk.size) - sizeof(chunk);
    
	buffer = (uint8_t *)xrealloc(buffer,ret + total);
    
	stream->stream_ctrl->packet_count++; /* increment packet count */
    
	ret = read_data(stream,buffer + total,ret);
	if(ret < 0) {
	    goto failed;
	}
    
	if(asf_header_len == 0) { /* first loop */
	    if(ret < sizeof(struct asf_header_t)) {
		display(MSDL_ERR,"1st chunk %d shorter than asf_header_t\n",ret);
		goto failed;
	    }
	    memcpy(&asfh,buffer,sizeof(struct asf_header_t));
	    asf_header_len = le2me_64(asfh.objh.size);
	    if(asf_header_len == 0) {
		display(MSDL_ERR,"asf_header_len is zero\n");
		goto failed;
	    }
	}
    
	total += ret;
    
	if(total >= asf_header_len) {
	    break; /* got entire header --- break */
	}
	else {
	    int chunk_type;
	    /* get chunk for next loop */
	    read_data(stream,&chunk,sizeof(chunk));
	    chunk_type = asf_streaming(&chunk);
      
	    if(chunk_type != ASF_STREAMING_HEADER) { /* not a header packet */
		display(MSDL_ERR,"non-header paceket when header packet expected\n");
		goto failed;
	    }
	}
    } 
  
    *asfheader = buffer;
    return total;
  
  failed:
    free(buffer);
    *asfheader = NULL;
    return -1;
}




/*
 * starts mms over http streaming. (mmsh)
 * 
 *    return value :   negative or 0  ... error
 *                                 1  ... success
 */
int mmsh_streaming_start(struct stream_t *stream)
{
  
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    struct mmsh_ctrl_t *mmsh_ctrl = stream_ctrl->mmsh_ctrl;
    struct url_t *url = stream->url;
    struct http_header_t *http_hdr = NULL;

    int asf_header_len;
  
    int sock; /* socket to use   */
    int done; /* everything done */
    int ret;

    char *connect_host;
    int connect_port;
  
  
    stream_ctrl->status = streaming_handshaking;
  
    sock = 0;
    mmsh_ctrl->streaming_type = ASF_Unknown_e;
    mmsh_ctrl->request = 1;
  
    if(url->port == 0) {
	url->port = HTTP_PORT;
    }

    if(stream->dlopts->bandwidth) {
	stream_ctrl->bandwidth = stream->dlopts->bandwidth;
    }
    else {
	stream_ctrl->bandwidth = INT_MAX_BANDWIDTH;
    }
    
    /* set host and port for proxy */
    if(url->http_proxy) { /* proxy specified */
	char *port_str = strchr(url->http_proxy,':');
	if(!port_str) {
	    display(MSDL_ERR,"proxy port not specified, assuming 8080\n");
	    connect_port = HTTP_PROXY_PORT;
	}
	else {
	    *port_str = '\0';
	    port_str++;
	    connect_port = atoi(port_str);
	    if(connect_port == 0) {
		connect_port = HTTP_PROXY_PORT;
	    }
	}
	connect_host = url->http_proxy; /* port (ex. :8080) is removed. */
    }
    else {
	connect_host = url->hostname;
	connect_port = url->port;
    }
    /* proxy setting: done */

  
    do {
    
	done = 1;
    
	if(sock > 0) close(sock); /* reset everything */
    
	sock = server_connect(connect_host,connect_port);
	if(sock < 0) return sock; /* failure */

	stream->sock = sock; /* set socket# to stream_t */
    
    
	http_hdr = new_http_header();

	stream_ctrl->packet_count = 0;
	mmsh_request(stream,http_hdr);
    
	ret = http_send_header(stream,http_hdr);
	free_http_header(http_hdr);
	http_hdr = NULL;
    
	http_hdr = new_http_header(); // reset, and make a new one.
	http_recv_header(stream,http_hdr);
    
	ret = mmsh_parse_response(mmsh_ctrl,http_hdr);
	if(ret < 0) {
	    display(MSDL_ERR | MSDL_VER,"MMSH connection failed\n");
	    goto failed;
	}
    
	switch(mmsh_ctrl->streaming_type) {
      
	case ASF_Seekable_e:
	case ASF_Nonseekable_e:
      
	    stream->stream_ctrl->protocol = MMSH;
	    if(mmsh_ctrl->request == 1) { /* first request, */
	
		struct asf_stream_chunk_t chunk; /* for chunk header for asf header */
		int chunk_type; /* little endian */
		uint8_t *buffer = NULL;

		/* we are supposed to get the ASF header! ( 100% ) */
		display(MSDL_VER,"1st response from server...  \n");
	
		/* keep getting chunk until stop getting IGNORE */
		for(;;) {
		    read_data(stream,&chunk,sizeof(chunk));
		    chunk_type = asf_streaming(&chunk);
		    if(chunk_type != ASF_STREAMING_IGNORE) break;
	  
		    buffer = xmalloc(chunk.size - 4);
		    read_data(stream,buffer,chunk.size - 4);
		    free(buffer);
		} 
	
	
		if(chunk_type == ASF_STREAMING_HEADER) {
	  
		    /* header may come within more than 2 packets */
		    asf_header_len = mmsh_get_asf_header(stream,&buffer,&chunk);
	  
		    if(asf_header_len < 0) goto failed;
	  
		    display(MSDL_VER,"ASF header received (size = %d)\n",asf_header_len);
	  
		    /* interpret M$ asf header */
		    ret = asf_interpret_header(mmsh_ctrl->hinfo,stream_ctrl->bandwidth,
					       buffer,asf_header_len);
	  
		    /* set necessary information to mmsh_ctrl */
		    stream_ctrl->file_size = mmsh_ctrl->hinfo->fileh->file_size;

		    stream_ctrl->packet_length = mmsh_ctrl->packet_size =
			mmsh_ctrl->hinfo->fileh->max_packet_size;
		    /* and everything is done */
	  
		    free(buffer);
		    buffer = NULL;
	  
		    if(ret < 0) goto failed;
	  
		    if(mmsh_ctrl->hinfo->streams->n_audio == 0 &&
		       mmsh_ctrl->hinfo->streams->n_video == 0) {
			/* this means no stream to DL */
			display(MSDL_ERR,"No stream to download!\n");
			goto failed;
		    }
		    display(MSDL_VER,"\n");
		    mmsh_ctrl->request++;
		    done = 0; /* need one more request to establish a stream! */
		}
	
		else if(chunk_type == ASF_STREAMING_CLEAR || 
			chunk_type == ASF_STREAMING_DATA || 
			chunk_type == ASF_STREAMING_END_TRANS) {
		    display(MSDL_ERR,"ASF header expected!!\n");
		    goto failed;
		}
		else { /* not mmsh, try http here. */
	  
		    /* push back "chunk" to be read again */	  
		    stream_data_push_back(stream,&chunk,sizeof(chunk));
		    mmsh_fallback_to_http(stream,http_hdr);
		}
	    }

	    break;
      
	case ASF_Authenticate_e:
	    http_authenticate(http_hdr);
	    goto failed;
      
	case ASF_Unknown_e:
	    mmsh_fallback_to_http(stream,http_hdr);
	    break;
      
	default: /* should not come here */
	    break;
	}
    
	/* allways comes here to free http_hdr */
	if(http_hdr) free_http_header(http_hdr);
	http_hdr = NULL;

    } while(!done);
  
    stream->stream_ctrl->status = streaming_downloading;
  
    return 1;

  failed:
    free_http_header(http_hdr);
    http_hdr = NULL;
    return 0;
}




/*
 * read from media stream.
 * 
 * fill 'buffer' with data, and buffer size is 'max_size'.
 * receive one chunk from stream->sock, and fills 'max_size' bytes to buffer.
 * and if there are more bytes in the chunk, the data is stored to
 * stream->stream_ctrl->buffer.
 *
 *    return value: positive value ... bytes written to buffer.
 *                  0              ... END OF FILE
 *                              or ... Clear EVERYTHING!!! RESET!!!
 *                 -1              ... ERROR
 */
static int mmsh_get_media_packet(struct stream_t *stream,
				 uint8_t *buffer, size_t max_size)
{
  
    static struct asf_stream_chunk_t chunk;
    static struct asf_stream_chunk_extra_t chunk_extra;
    int chunk_size = 0;
    int chunk_type;
    int ret;
    int data_follows_f;
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    struct mmsh_ctrl_t *mmsh_ctrl = stream_ctrl->mmsh_ctrl;

  
    do {
	data_follows_f = 1;
    
	/* get chunk header from network. */
	if(read_data(stream,&chunk,sizeof(chunk)) < sizeof(chunk)) {
	    goto failed;
	}

	/* little endian. (asf streaming interpret as little endian.) */
	chunk_type = asf_streaming(&chunk);

	/*HTTP message packet ... continue */
	if(!(memcmp((uint8_t *)&chunk,"HTTP",4))) {
	    struct http_header_t *http_hdr;
	    char *field = NULL;
	    int len = 0;
      
	    stream_data_push_back(stream,(uint8_t *)&chunk,sizeof(chunk));
	    http_hdr = new_http_header();
	    http_recv_header(stream,http_hdr);

	    if((field = http_get_field(http_hdr,"Content-Length")) != NULL) {
		while(*field == ' ') field++;
		len = atoi(field);
	    }

	    free_http_header(http_hdr);
      
	    if(len) {
		uint8_t *buf = xmalloc(len);
		read_data(stream,buf,len);
		free(buf); /* Ignore all */
	    }
	    data_follows_f = 0; /* don't forget to clear this. as no data follows */

	}
	else if(chunk_type == ASF_STREAMING_END_TRANS) {
      
	    stream_ctrl->status = streaming_finished;
	    display(MSDL_VER,"Transaction finished normally!\n");
	    return 0;
	}
	else if(chunk_type == ASF_STREAMING_CLEAR) {
	    stream_ctrl->status = streaming_rewind;
      
	    /* reset every headerinfo data */
	    free_asf_headerinfo_t(mmsh_ctrl->hinfo);
	    mmsh_ctrl->hinfo = new_asf_headerinfo_t();

	    display(MSDL_VER,"Clear Configuration! reset!\n");
	    data_follows_f = 0; /* loop again , and get header/data/eof packet */
	}
	else if(chunk_type == ASF_STREAMING_DATA   ||
		chunk_type == ASF_STREAMING_DATA2  ||
		chunk_type == ASF_STREAMING_HEADER) {
	    if(read_data(stream,&chunk_extra,sizeof(chunk_extra)) < 0) {
		goto failed;
	    }
	}
	else if(chunk_type == ASF_STREAMING_IGNORE){
	    int i;
	    char *buffer;
	    buffer = xmalloc(chunk.size - 4);
	    read_data(stream,buffer,chunk.size - 4);
	    display(MSDL_DBG,"Metadata ----\n");
	    for(i = 0;i<chunk.size - 4;i++) display(MSDL_DBG,"%02x",(uint8_t)buffer[i]);
	    display(MSDL_DBG,"-(%d B)--------\n",chunk.size - 4);
	    free(buffer);
	    data_follows_f = 0;
	}
	else { /* ignore this*/
	    display(MSDL_ERR,"unknown packet type!!\n");
	}
    
    } while(data_follows_f == 0);
  
    /* 
       size of this chunk. ( media stream data )
       >>> CAUTION <<< 
       chunk_size is size of chunk to get from network, but
       have to write mmsh_ctrl->packet_size bytes in ASF file.
       so 0 padding of 'mmsh_ctrl->packet_size - chunk_size' bytes
       is necessary.
    */
    chunk_size = le2me_16(chunk.size) - sizeof(chunk);
  

    stream_ctrl->packet_count++; /* increment received packet count */
  
  
    /* header or data, as other packets are already processed */ 
    if(chunk_type == ASF_STREAMING_HEADER) {
	struct asf_headerinfo_t *hinfo = mmsh_ctrl->hinfo;
    
	display(MSDL_VER,"ASF Header received\n");
    
	if(chunk_size <= max_size) { /* header can be stored in buffer */
	    /* buffer is long enogh. */
	    ret = read_data(stream,buffer,chunk_size);
	    if(ret < 0) {
		goto failed;
	    }
	    /* no padding, because this is ASF header */
	}
	else { /* buffer is not long enough for header. */
	    /* data is to be written in file, so it goes to write_buffer */
	    if(read_data(stream, stream->stream_ctrl->write_buffer, chunk_size) < 0) {
		goto failed;
	    }
	    memcpy(buffer, stream_ctrl->write_buffer, max_size);
	    stream_ctrl->write_pos = max_size;
	    stream_ctrl->write_data_len = chunk_size - max_size;
	    ret = max_size;
	}
    
    
	/*
	  interpret asf_header.
	  it has to be done here and doing complecated stuff because
      
	  . there is "clear configuration" packet, thus header comes multiple times.
	  . header we receive in 'mmsh_streaming_start' and this function differs.
	  . we don't know in how many packets the header will come.

	  and there are necessary stuff after asf_header, so
	  asf_header_len is not likely asfh.objh.size(pure header length, not
	  including the "necessary stuff").
      
	  :) it sucks...
	*/
    
	/* copy asf header */
	hinfo->asf_header = xrealloc(hinfo->asf_header,
				     hinfo->asf_header_len + chunk_size);
	memcpy(hinfo->asf_header + hinfo->asf_header_len,
	       (chunk_size <= max_size) ? buffer : stream_ctrl->write_buffer,
	       chunk_size);
	hinfo->asf_header_len += chunk_size;
    
	if(hinfo->asf_header_len >= sizeof(struct asf_header_t) && 
	   hinfo->asf_header_len >=
	   le2me_64(((struct asf_header_t *)hinfo->asf_header)->objh.size)) {
	    asf_get_file_property(hinfo->asf_header,
				  hinfo->asf_header_len,
				  hinfo->fileh);
	    
	    if(ret < 0) { /* error */
		display(MSDL_ERR,"asf_get_file_property error\n");
		goto failed;
	    }
      
	    stream_ctrl->packet_length = mmsh_ctrl->packet_size =
		hinfo->fileh->max_packet_size;
	    stream_ctrl->file_size = hinfo->fileh->file_size;
	}

	return ret;
    }
    else {
	display(MSDL_DBG,"ASF Data received\n");
	if(mmsh_ctrl->packet_size < chunk_size) {
	    display(MSDL_ERR,"chunk size bigger than pakcet size..\n");
	    goto failed;
	}
      
	if(mmsh_ctrl->packet_size <= max_size) {
	    /* buffer is bigger enough for this chunk. */
	    if(read_data(stream, buffer, chunk_size) < 0) {
		goto failed;
	    }
	    memset(buffer + chunk_size, 0, /* padding */
		   mmsh_ctrl->packet_size - chunk_size);
	    ret = stream->stream_ctrl->mmsh_ctrl->packet_size;
	}
	else { /* max_size < packet_size .. cannot write in buffer. */
	    if(read_data(stream, stream_ctrl->write_buffer, chunk_size) < 0) {
		goto failed;
	    }
	    /*
	      this is OK because stream_ctrl->buffer is empty
	      when entered this function. 
	    */
	    memset(stream_ctrl->write_buffer + chunk_size, 0, /* padding */
		   mmsh_ctrl->packet_size - chunk_size);
	    memcpy(buffer,stream_ctrl->write_buffer, max_size);
	    ret = stream_ctrl->write_pos = max_size;
      
	    stream_ctrl->write_data_len = mmsh_ctrl->packet_size - max_size;
	}
	return ret;
    }
  
  failed: /* probably timeout error */
    return -1;
  
}



/*
 * read mms over http stream. filles buffer, and buffer size is 'size' 
 *
 *        read cached data from stream->stream_ctrl->buffer
 *        check for to see if network access is necessary
 *        get chunk(media packet) from network.
 *
 *  return value: bytes written to buffer. -1 if error.
 */
int mmsh_streaming_read(struct stream_t *stream,
			uint8_t *buffer, size_t buffer_size)
{
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    size_t pos = 0; /* how many bytes are in 'buffer' */
    int ret = 0;

    if(buffer_size == 0) {
	display(MSDL_ERR,"buffer_size must be bigger than 0\n");
	return -1;
    }
  
    if(stream_ctrl->write_data_len) { /* there are waiting data to be written */
    
	if(buffer_size <= stream_ctrl->write_data_len) {
	    /*
	      buffer_size < stream_ctrl->write_data_len...
	      fill buffer, and return.
	    */
	    memcpy(buffer,stream_ctrl->write_buffer + stream_ctrl->write_pos,
		   buffer_size);
	    stream_ctrl->write_data_len -= buffer_size;
	    stream_ctrl->write_pos += buffer_size;
	    return buffer_size;
	}
	else { 
	    /*
	      stream_ctrl->write_data_len < buffer_size,
	      have to read from network.
	    */
	    memcpy(buffer,stream_ctrl->write_buffer + stream_ctrl->write_pos,
		   stream_ctrl->write_data_len);
	    pos = stream_ctrl->write_data_len;
	    /* and stream_ctrl->write_buffer is empty. */
	    stream_ctrl->write_data_len = 0;
	    stream_ctrl->write_pos = 0;
	}
    }
  

    /* still have to get data from network. */
    if(stream_ctrl->status != streaming_finished) { /* not finished */
	ret = mmsh_get_media_packet(stream, buffer + pos, buffer_size - pos);
    }
  
    if(ret >= 0) return (ret + pos);
    else         return -1; /* error */
}



struct stream_t *mmsh_streaming_init(struct url_t *url)
{
    struct stream_t *stream = new_stream_t();
    stream->url = url;
    stream->stream_ctrl = new_stream_ctrl_t();
    stream->stream_ctrl->mmsh_ctrl = new_mmsh_ctrl_t();
  
    stream->start = mmsh_streaming_start;
    stream->read  = mmsh_streaming_read;
    stream->close = mmsh_streaming_close;
   
    return stream;
}



void mmsh_streaming_close(struct stream_t *stream)
{
    if(stream->sock > 0)
	close(stream->sock);
  
    free_mmsh_ctrl_t(stream->stream_ctrl->mmsh_ctrl);
    free_stream_ctrl_t(stream->stream_ctrl);
    free_stream_t(stream);
}

