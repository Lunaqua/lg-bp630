/***********************************************************************
 *   wmserver.c : for downloading from WMServer using rtsp
 ***********************************************************************
 * Copyright (C) 2007 metro <me_t_ro@yahoo.com>
 *
 * This file is part of msdl, media stream downloader
 *
 * This file is based on information shown on wireshark,
 * analyzing transaction of Windows media player.
 *
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
#include "rtsp.h"
#include "asf.h"
#include "sdpcommon.h"
#include "sdpwms.h"



/*
 * Windows Media Player provided RTSP strings
 */
static const char wms_useragent[] =
    "User-Agent: WMPlayer/11.0.5721.5145 guid/3300AD50-2C39-46C0-AE0A-E1D79F0FD2D8";
static const char wms_supported[] =
    "Supported: com.microsoft.wm.srvppair, com.microsoft.wm.sswitch, com.microsoft.wm.eosmsg, com.microsoft.wm.fastcache, com.microsoft.wm.packetpairssrc, com.microsoft.wm.startupprofile";

static const char wms_authentication[]=
    "X-Accept-Authentication: Negotiate, NTLM, Digest, Basic";
static const char wms_transport[] =
    "Transport: RTP/AVP/TCP;unicast;interleaved=0-1;ssrc=75afe1e1;mode=PLAY";





/*
 * send DESCRIBE 
 * return value:   -1: failed   1: success   0: retry with other protocol
 */
static int wmserver_rtsp_describe(struct stream_t *stream,char **description_ret)
{
    struct rtsp_ctrl_t *rtsp_ctrl = stream->stream_ctrl->rtsp_ctrl;
    struct rtsp_header_t *rtsp_hdr = NULL;
    char *description = NULL;
    int ret = 0;
    char *field = 0;
    int len = 0;

    /* send DESCRIBE request */
    rtsp_hdr = new_rtsp_header_with_standard_fields(rtsp_ctrl);

    rtsp_set_field(rtsp_hdr, wms_useragent);
    rtsp_set_field(rtsp_hdr, wms_supported);
    rtsp_request_describe(rtsp_hdr,stream->url,NULL);
    rtsp_send_request_and_free(stream,rtsp_hdr);

    /* receive reply for DESCRIBE */
    rtsp_hdr = new_rtsp_header();
    ret  = rtsp_recv_header(stream,rtsp_hdr);
    if(ret < 0) {
	free_rtsp_header(rtsp_hdr);
	goto failed_retry;
    }
    
    /* NOT OK */
    if(rtsp_hdr->status_code < 200 || 299 < rtsp_hdr->status_code) { 
	display(MSDL_ERR,"status code %d (%s)\n",
		rtsp_hdr->status_code,rtsp_hdr->reason_phrase);
	
	field = rtsp_get_field(rtsp_hdr,"Alert");
	if(field) {
	    while(*field == ' ') field++;
	    display(MSDL_ERR,"message from server --> %s\n",field);
	}
	free_rtsp_header(rtsp_hdr);
	goto failed;
    }

    
    len = 0;
    /* Content-length must be present */
    if((field = rtsp_get_field(rtsp_hdr,"Content-Length")) != NULL) {
	while(*field == ' ') field++;
	len = atoi(field);
    }
    else { /* no Content-length */
	display(MSDL_ERR,"warning: No Content-Length in fields!!\n");
    }
  
    if((field = rtsp_get_field(rtsp_hdr,"ETag")) == NULL) {
	display(MSDL_ERR,"warning: No ETag!!\n");
    }

    free_rtsp_header(rtsp_hdr);
    
    /*
      copy description (sdp)
    */
    description = (char *)xmalloc(len + 1);
    len = read_data(stream,(uint8_t *)description,len);
    description[len] = '\0';
    
    display(MSDL_DBG,"=desc=================\n%s\n=(%d bytes)========desc=\n",
	    description,(int)strlen(description));
    *description_ret = description;
    
    return 1;
	
  failed:
    if(description) free(description);
    *description_ret = NULL;
    return -1;

  failed_retry:
    if(description) free(description);
    *description_ret = NULL;
    return 0;
}





/*
 * send SETUP request
 * return value:   -1: failed  last status code: SETUP request sent
 */
static int wmserver_rtsp_setup(struct stream_t *stream,
			       struct asf_headerinfo_t *asf_headerinfo,struct sdpwms_t *sdpwms)
{
    struct rtsp_ctrl_t *rtsp_ctrl = stream->stream_ctrl->rtsp_ctrl;
    struct list_h *p = NULL;
    struct sdpwms_stream_t *sdpwmsstream = NULL;
    struct rtsp_header_t *rtsp_hdr = NULL;
    char *controlstr = NULL;
    char *buffer = NULL;
    int ret = 0;

    
    if(!stream || !asf_headerinfo || !sdpwms) { /* NULL check */
	return -1;
    }
    
    if(!(asf_headerinfo->streams->n_audio + asf_headerinfo->streams->n_video)) {
	return -1;
    }

    buffer = xmalloc(BUFSIZE_1K);
  
    /* SETUP for audio */
    if(asf_headerinfo->streams->n_audio) {

	rtsp_hdr = new_rtsp_header_with_standard_fields(rtsp_ctrl);
	rtsp_set_field(rtsp_hdr,wms_supported);
	rtsp_set_field(rtsp_hdr,wms_transport);
    
    
	/* find control for audio_id */
	controlstr = NULL;
	for(p = sdpwms->streams ; p ; p = p->next) {
	    sdpwmsstream = p->p;
	    if(sdpwmsstream->streamnum == asf_headerinfo->streams->audio_id) {
		controlstr = sdpwmsstream->control;
	    }
	}
    
	if(!controlstr) {
	    controlstr = "audio"; /* default */
	}

	/* stream number */
	snprintf(buffer,BUFSIZE_1K - 1,"%s/%s",rtsp_ctrl->mrl,controlstr);
	
	rtsp_request_setup(rtsp_hdr,stream->url,buffer);
	rtsp_send_request_and_free(stream,rtsp_hdr);

	
	/* receive message for SETUP */
	ret = rtsp_recv_header_ignore_message(stream);
    }

    /* SETUP for video */
    if(asf_headerinfo->streams->n_video) {
	
	rtsp_hdr = new_rtsp_header_with_standard_fields(rtsp_ctrl);
	rtsp_set_field(rtsp_hdr,wms_supported);
	rtsp_set_field(rtsp_hdr,wms_transport);
	

	/* find control for audio_id */
	controlstr = NULL;
	for(p = sdpwms->streams ; p ; p = p->next) {
	    sdpwmsstream = p->p;
	    if(sdpwmsstream->streamnum == asf_headerinfo->streams->video_id) {
		controlstr = sdpwmsstream->control;
	    }
	}
    
	if(!controlstr) {
	    controlstr = "video"; /* default */
	}
    
	snprintf(buffer,BUFSIZE_1K - 1,"%s/%s",rtsp_ctrl->mrl,controlstr);

	rtsp_request_setup(rtsp_hdr,stream->url,buffer);
	rtsp_send_request_and_free(stream,rtsp_hdr);
	
	/* receive message for SETUP */
	ret = rtsp_recv_header_ignore_message(stream);
    }

    free(buffer);
    return ret;
}


/*
 * send SET_PARAMETER request
 * return value:  -1:failure   status code: success
 */
static int wmserver_rtsp_set_parameter(struct stream_t *stream)
{
    struct rtsp_ctrl_t *rtsp_ctrl = stream->stream_ctrl->rtsp_ctrl;
    struct rtsp_header_t *rtsp_hdr = NULL;
    char *buffer = xmalloc(BUFSIZE_1K);
    int ret = 0;
    
    rtsp_hdr = new_rtsp_header_with_standard_fields(rtsp_ctrl);
    rtsp_set_field(rtsp_hdr,wms_useragent);
    rtsp_request_set_parameter(rtsp_hdr,stream->url,NULL);
    rtsp_send_request_and_free(stream,rtsp_hdr);
    
    /*
      receive message for SET_PARAMETER 
      if something needs to be done to message, use rtsp_recv_header 
    */
    ret = rtsp_recv_header_ignore_message(stream);
    
    free(buffer);
    return ret;
}


/*
 * send PLAY request
 * return value:  -1:failure   status code: success
 */
static int wmserver_rtsp_play(struct stream_t *stream)
{
    struct rtsp_ctrl_t *rtsp_ctrl = stream->stream_ctrl->rtsp_ctrl;
    struct rtsp_header_t *rtsp_hdr;
    char *buffer = xmalloc(BUFSIZE_1K);
    int ret = 0;
    int bandwidth_to_send = 0;

    rtsp_hdr = new_rtsp_header_with_standard_fields(rtsp_ctrl);
    
    rtsp_set_field(rtsp_hdr,wms_useragent);

    rtsp_set_range_field(rtsp_hdr,stream->dlopts->range);
    rtsp_set_speed_field(rtsp_hdr,stream->dlopts->speed);

    /* bandwidth specified */
    bandwidth_to_send =
	(stream->dlopts->bandwidth) ? (stream->dlopts->bandwidth) : INT_MAX_BANDWIDTH;

    snprintf(buffer,BUFSIZE_1K - 1,"Bandwidth: %d",bandwidth_to_send);
    rtsp_set_field(rtsp_hdr,buffer);
    snprintf(buffer,BUFSIZE_1K - 1,
	     "X-Accelerate-Streaming: AccelDuration = 18000;AccelBandwidth=%d",bandwidth_to_send);
    rtsp_set_field(rtsp_hdr,buffer);
    
    rtsp_request_play(rtsp_hdr,stream->url,NULL);
    rtsp_send_request_and_free(stream,rtsp_hdr);
    
    /* receive message for PLAY request */
    ret = rtsp_recv_header_ignore_message(stream);
    
    free(buffer);
    return ret;
}




/* 
 * get asf_headerinfo and sdpwms by sdpstr, and stream->stream_ctrl->bandwidth
 * return value: 0 : failed       1: success
 */
static int wmserver_get_info_from_sdp_string(struct stream_t *stream,char *sdpstr,
					     struct asf_headerinfo_t **asf_headerinfo_ret,
					     struct sdpwms_t **sdpwms_ret)
{
    struct asf_headerinfo_t *asf_headerinfo = NULL;
    struct sdpwms_t *sdpwms = NULL;

    if(!stream || !sdpstr) { /* stream could not be NULL */
	goto failed;
    }

    /* parse sdp and get information about file to download */
    sdpwms = wmserver_parse_sdp(sdpstr);
    if(sdpwms->asf_header_len <= sizeof(struct asf_header_t)) {
	display(MSDL_ERR,"asf header smaller than asf_header_t\n");
	goto failed;
    }
  
    asf_headerinfo = new_asf_headerinfo_t();
    asf_interpret_header(asf_headerinfo,stream->stream_ctrl->bandwidth,
			 sdpwms->asf_header,sdpwms->asf_header_len);


    /* set asf header len */
    asf_headerinfo->asf_header_len = sdpwms->asf_header_len;
    
    /* copy raw header */
    asf_headerinfo->asf_header = xmalloc(sdpwms->asf_header_len);
    memcpy(asf_headerinfo->asf_header,sdpwms->asf_header,sdpwms->asf_header_len);
  

    /* return structures */
    *asf_headerinfo_ret = asf_headerinfo;
    *sdpwms_ret         = sdpwms;

    return 1;

  failed:
    if(asf_headerinfo) free_asf_headerinfo_t(asf_headerinfo);
    if(sdpwms) free_sdpwms_t(sdpwms);
    *asf_headerinfo_ret = NULL;
    *sdpwms_ret = NULL;
    return 0;
}



/*
 * send SETUP,SEND_REQUEST,PLAY requests.
 *
 *      return value                1 : success
 *                                  0 : error, retry via mms
 *                                 -1 : error.
 */
int wmserver_setup_and_get_header(struct stream_t *stream,
				  struct asf_headerinfo_t **asf_headerinfo_ret)
{
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    struct rtsp_ctrl_t *rtsp_ctrl = stream_ctrl->rtsp_ctrl;

    struct asf_headerinfo_t *asf_headerinfo = NULL;
    struct sdpwms_t *sdpwms = NULL;

    char *description = NULL;
    int ret = 0;
  
    /*
      use "-b" option 4 low bandwidth..
    */
    if(stream->dlopts->bandwidth) {
	stream_ctrl->bandwidth = stream->dlopts->bandwidth;
    }
    else {
	stream_ctrl->bandwidth = INT_MAX_BANDWIDTH;
    }

    
    rtsp_ctrl->cseq = 1;

    ret = wmserver_rtsp_describe(stream,&description);
    if(ret == 0) { /* retry */
	goto failed_retry;
    }
    else if(ret < 0) {
	goto failed;
    }
    

    if(!wmserver_get_info_from_sdp_string(stream,description,&asf_headerinfo,&sdpwms)) {
	goto failed;
    }
    
    /* SETUP requests (for audio and video) */
    if(wmserver_rtsp_setup(stream,asf_headerinfo,sdpwms) < 0) {
	goto failed;
    }

    /* send SET_PARAMETER request to download stream */
    if(wmserver_rtsp_set_parameter(stream) < 0) {
	goto failed;
    }
  
    /*  send PLAY request to download stream */
    if(wmserver_rtsp_play(stream) < 0) {
	goto failed;
    }

    /* copy to write buffer */
    stream_ctrl->write_pos = 0;
    memcpy(stream_ctrl->write_buffer, sdpwms->asf_header, sdpwms->asf_header_len);
    stream_ctrl->write_data_len = sdpwms->asf_header_len;

    free_sdpwms_t(sdpwms);
    free(description);
    *asf_headerinfo_ret = asf_headerinfo;
    return 1;
  
  failed:
    if(asf_headerinfo) free_asf_headerinfo_t(asf_headerinfo);
    if(sdpwms) free_sdpwms_t(sdpwms);
    if(description) free(description);
    
    *asf_headerinfo_ret = NULL;
    return -1;

    
  failed_retry:
    if(asf_headerinfo) free_asf_headerinfo_t(asf_headerinfo);
    if(sdpwms) free_sdpwms_t(sdpwms);
    if(description) free(description);
    
    *asf_headerinfo_ret = NULL;
    return 0;
}



/*
 * After reveiving EOF packet, ANNOUNCE may come when
 * streaming still continues. (this is most likely to be a download protection)
 * 
 *
 *
 */
struct asf_headerinfo_t *wmserver_announce_continue(struct stream_t *stream)
{
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    struct rtsp_ctrl_t *rtsp_ctrl = stream_ctrl->rtsp_ctrl;
  
    int i = 0;                      /* for various purposes                */
    char checkbuf[10];              /* to check if the message is ANNOUCNE */
    struct rtsp_header_t *rtsp_hdr = NULL;
    int got_cseq = 0;               /* cseq received                */
    int status_code = 0;            /* rtsp_hdr status code         */
    char *field = NULL;             /* filed pointer for rtsp_hdr   */
    int len = 0;                    /* len of description (content length) */
    char *description = NULL;           /* description, data of rtsp packet    */
    char *buffer = NULL;            /* temp buffer for building req */

    struct sdpwms_t *sdpwms = NULL;
    struct asf_headerinfo_t* asf_headerinfo = NULL;

    
    buffer = xmalloc(BUFSIZE_1K);

    i = recv_data(stream,checkbuf,8);

    if(i < 8 || memcmp(checkbuf,"ANNOUNCE",8)) {
	goto failed;
    }
  
    stream_data_push_back(stream,checkbuf,i); /* push back "ANNOUNCE" */
  
    rtsp_hdr = new_rtsp_header();
    rtsp_recv_header(stream,rtsp_hdr);
  
    status_code = rtsp_hdr->status_code;
    /* get content length data */
    if((field = rtsp_get_field(rtsp_hdr,"Content-Length")) != NULL) {
	while(*field == ' ') field++;
	len = atoi(field);	
    }
    
    if((field = rtsp_get_field(rtsp_hdr,"Cseq")) != NULL) {
	while(*field == ' ') field++;
	got_cseq = atoi(field);	
    }

    /*
      copy sdp
    */
    description = xmalloc(len + 1);
    len = read_data(stream,description,len);
    description[len] = '\0';
    
    free_rtsp_header(rtsp_hdr);
  
    if(status_code != RTSP_STATUS_ANNOUNCE) {
	goto failed; /* not likely but have to check this */
    }
  
    /*
      got annouce packet, and data is in description
    */
    rtsp_200ok(stream,got_cseq,rtsp_ctrl->session); /* OK (required, according to RFC )*/

    
    wmserver_get_info_from_sdp_string(stream,description,&asf_headerinfo,&sdpwms);
    
    /* Be careful with Cseq: */
  
    /* SETUP */
    if(wmserver_rtsp_setup(stream,asf_headerinfo,sdpwms) < 0) {
	goto failed;
    }
	
    /* send SET_PARAMETER request to download stream */
    if(wmserver_rtsp_set_parameter(stream) < 0) {
	goto failed;	
    }
    
    /*  send PLAY request to download stream */
    if(wmserver_rtsp_play(stream) < 0) {
	goto failed;	
    }

    free_sdpwms_t(sdpwms);    
    free(description);
    free(buffer);
    return asf_headerinfo;
  

  failed:
    if(description) free(description);
    if(sdpwms) free_sdpwms_t(sdpwms);
    if(asf_headerinfo) free_asf_headerinfo_t(asf_headerinfo);
    if(buffer) free(buffer);
    return NULL;
    
}


/*
 * get_data function for RTSP-WMS protocol.
 * buffer is guranteed to be bigger than size,
 * if not, this function may cause segfault.
 * all data goes to 'buffer'.
 *        return value     bytes read form sock : success
 *                             0                : EOF (retval of read_data)
 *                            -1                : error
 *
 */
int rtsp_wmserver_get_data(struct stream_t *stream,uint8_t *buffer,size_t size)
{
    int i;

    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    struct rtsp_ctrl_t *rtsp_ctrl = stream_ctrl->rtsp_ctrl;


    if(size > 4) {
    
	i = read_data(stream,buffer,4);
	if(i < 4) { /* failed (0 or -1)*/
	    return i;
	}

    
	while(!memcmp(buffer,"SET_",4) ||
	      !memcmp(buffer,"OPTI",4) ||
	      !memcmp(buffer,"RTSP",4) ||
	      !memcmp(buffer,"ANNO",4)) {
      
	    struct rtsp_header_t *rtsp_hdr;
	    int status_code;
	    int bufsize;
	    int len;
	    uint8_t *body = NULL;
	    char *field;
	    int got_cseq;
      
	    buffer[4] = '\0'; /* insert \0 byte (buffer is bigger than 4 bytes)*/
      
	    bufsize = 0;
	    /* push back 4 bytes */
	    stream_data_push_back(stream,buffer,4);
      
	    rtsp_hdr = new_rtsp_header();
	    rtsp_recv_header(stream,rtsp_hdr);
      
      
	    /*
	      get necessary information from rtsp header.
	    */
	    status_code = rtsp_hdr->status_code;
      
	    /* get content length data */
	    if((field = rtsp_get_field(rtsp_hdr,"Content-Length")) != NULL) {
		while(*field == ' ') field++;
		len = atoi(field);
		body = xmalloc(len);
		len = read_data(stream,body,len);
	    }
      
	    if((field = rtsp_get_field(rtsp_hdr,"Cseq")) != NULL) {
		while(*field == ' ') field++;
		got_cseq = atoi(field);	
	    }
      
	    free_rtsp_header(rtsp_hdr);

      
	    if(!memcmp(buffer,"RTSP",4)) { /* RTSP/1.0 200 OK ... ignore this */
	
		if(got_cseq != rtsp_ctrl->cseq) {
		    display(MSDL_DBG | MSDL_ERR,"CSeq mismatch: expected %d, got %d",
			    rtsp_ctrl->cseq,got_cseq);
		}
		/* DO NOT send OK back to this */
	    }
	    else if(status_code == RTSP_STATUS_SET_PARAMETER) { /* SET_PARAMETER */
		char *p;

		/* EOF message comes here */
		if((p = strstr((char *)body,"EOF:"))) {
		    p += 4;
		    while(*p == ' ') p++; 
		    if((!strncasecmp(p,"true",4))) { /* EOF */
	    
			display(MSDL_VER,"End of file packet received\n");
			stream_ctrl->status = streaming_finished;
			free(body);
			return 0;
		    }
		}
		rtsp_200ok(stream,got_cseq,rtsp_ctrl->session);	
	    }      
	    else {
		rtsp_200ok(stream,got_cseq,rtsp_ctrl->session);
	    }
      
	    free(body);
	    i = read_data(stream,buffer,4);
	}
    
	i = read_data(stream,buffer + 4,size - 4) + 4;
    
    } /* if(size > 4) */
    else { /* size is smaller than 4 bytes */
	i = read_data(stream,buffer,size);
    }
  
  
    if(i != size) {
	display(MSDL_ERR,"rtsp_get_data: got %i of %u bytes\n",
		i,(unsigned int)size);
    }
    return i;
}




/*
 * read from media stream. (wmserver rdp packet)
 * 
 * fill 'buffer' with data, and buffer size is 'max_size'.
 * receive one chunk from stream->sock, and fills 'max_size' bytes to buffer.
 * and if there are more bytes in the chunk, the data is stored to
 * stream->stream_ctrl->buffer.
 *
 *    return value: positive value ... bytes written to buffer.
 *                  0              ... END OF STREAM
 *                 -1              ... ERROR
 */
int wmserver_rtp_get_media_packet(struct stream_t *stream,
				  uint8_t *buffer,size_t max_size)
{
    int i;
    int n = 1;
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    uint8_t header[8];
    uint8_t header2[8];
  
    int size   = 0;       /* size of =in-wma-usable part of= packet */
    int flags1 = 0;
    int flags2 = 0;
  
    int cc = 0;           /* number of csrc */
    uint16_t seq = 0;
    uint32_t ts  = 0;
    uint32_t ssrc = 0;    /* syncronization source */
    uint32_t csrc = 0;    /* contributing source   */
    uint8_t magicheader[4];
  
    n = rtsp_wmserver_get_data(stream,header,8);
    
    if(n < 8) {
	if(stream_ctrl->status == streaming_finished) {
      
	    struct asf_headerinfo_t *asf_headerinfo;
      
	    /*
	      wait for 2 seconds.. this is necessary becasue
	      otherwise it would block when waiting "ANNOUNCE"
	    */
	    if(stream_check_data(stream,2) > 0) {
	
		if((asf_headerinfo = wmserver_announce_continue(stream))) {
		    int ret = 0;
		    display(MSDL_VER,"streaming still continues!!\n");

	  
		    /* write header to write buffer and return... */
		    if(asf_headerinfo->asf_header_len <= max_size) { /* everything can go... */
			memcpy(buffer,asf_headerinfo->asf_header,
			       asf_headerinfo->asf_header_len);
			ret = asf_headerinfo->asf_header_len;
		    }
		    else {
			memcpy(buffer,asf_headerinfo->asf_header,max_size);
			memcpy(stream_ctrl->write_buffer, asf_headerinfo->asf_header + max_size,
			       asf_headerinfo->asf_header_len - max_size);
			stream_ctrl->write_data_len = asf_headerinfo->asf_header_len - max_size;
			ret = max_size;
		    }
	  
		    /*free_asf_headerinfo_t(asf_headerinfo); this is done in free_rtsp_ctrl_t */
		    stream_ctrl->status = streaming_rewind;
	  
		    return ret; /* write asf header */
		}
	    }
      
	    /* if no ANNOUCNE, finish streaming */
	    display(MSDL_DBG,"finish rtsp streaming\n");
	    return 0; /* streaming really finished */
	}
	else {
	    goto failed;
	}
    }
  
  
    if(header[0] != 0x24) {
	int i;
	display(MSDL_ERR,"wrong rdt/rtp magic : [0x%02x]\n",header[0]);
	/* dump header*/
	for( i = 0; i < 8; i++) display(MSDL_VER,"[%02x] ",header[i]);
	display(MSDL_ERR,"\n");
	display(MSDL_ERR,"(");
	for( i = 0; i < 8; i++) display(MSDL_VER,"%c",header[i]);
	display(MSDL_ERR,")\n");
	goto failed;
    }
    size = (header[1] << 16) + (header[2] << 8) + header[3];

    flags1 = header[4];
    flags2 = header[5];
    cc = (flags1 & 0x0f);
  
    seq = get16_be((uint8_t *)&header + 2);
  
    read_data(stream,&header2,8);
    ts = get32_be((uint8_t *)&header2);
    ssrc = get32_be((uint8_t *)&header2 + 4);
    size -= 12;  
  
    for(i = 0 ; i < cc ; i++ ) {
	read_data(stream,(uint8_t *)&csrc,4);
	csrc = be2me_32(csrc);
	size -= 4;
    }
  

    /*
      DIRTY, but this seems to be the only way to donwload :D
    */
    for(;size > 0; size -= 4) { /* skip until magic(0x82) appears */
	read_data(stream,(uint8_t *)&magicheader,4);
	if(magicheader[0] == 0x82) break; /* magiq */
    }

    display(MSDL_DBG,"f1: %x, f2: %x, cc: %d, size: %d, ts: %u,"
	    "ssrc: %x, csrc :%x\n",flags1,flags2,cc,size,ts,ssrc,csrc);
    display(MSDL_DBG,"magic: [");
    for(i = 0 ; i < 4 ; i++) {
	display(MSDL_DBG,"%02x ",magicheader[i]);
    }
    display(MSDL_DBG,"]\n");
  
  
    stream_ctrl->packet_count++;
  
    /*
      we have to do this to show rtsp server that
      this client is still alive, otherwise server sends
      EOS pacekt...
    */ 
    if(!(stream_ctrl->packet_count % 200)) {
	struct rtsp_header_t *rtsp_hdr;
	rtsp_hdr = new_rtsp_header_with_standard_fields(stream_ctrl->rtsp_ctrl);
	
	rtsp_request_get_parameter(rtsp_hdr,stream->url,NULL);
	rtsp_send_request_and_free(stream,rtsp_hdr);
    }
  

    /* store to buffer, or write_buffer */
    if(max_size > stream_ctrl->packet_length) {
	/* all data can go to buffer --> just do it!! */
	memcpy(buffer,&magicheader,4);
	n = read_data(stream,buffer + 4,size - 4);
	if(n <= 0) goto failed;
    
	memset(buffer + size,0,stream_ctrl->packet_length - size); /* padding */
	return stream_ctrl->packet_length;
    }
    else {
	/*
	  buffer is not enough.. copy max_size data to 
	  buffer and the rest goes to stream_ctrl->write_buffer.
	*/
	memcpy(stream_ctrl->write_buffer,&magicheader,4);
	n = read_data(stream,stream_ctrl->write_buffer + 4,size - 4);
	if(n <= 0) goto failed;
    
	/*
	  it is guranteed that stream_ctrl->write_buffer is
	  practically empty when it comes here!
	  --> reset stream_ctrl->write_pos and so on...
	*/
	if(stream_ctrl->packet_length < size) {
	    display(MSDL_ERR,"packet_length(%d) is smaller than size(%d)\n",
		    stream_ctrl->packet_length,size);
	    goto failed; /* this should not happen*/
	}
	memset(stream_ctrl->write_buffer + size,0,
	       stream_ctrl->packet_length - size); /* padding */
	memcpy(buffer,stream_ctrl->write_buffer,max_size);
    
	stream_ctrl->write_data_len = stream_ctrl->packet_length - max_size;
	stream_ctrl->write_pos += max_size;
    
	return max_size;
    }
  
  
  failed:
    return -1;
}

