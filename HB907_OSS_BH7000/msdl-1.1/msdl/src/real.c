/***********************************************************************
 *    real.c : for downloading from real server using rtsp
 ***********************************************************************
 * Copyright (C) 2007 metro <me_t_ro@yahoo.com>
 *
 * This file is part of msdl, media stream downloader
 *
 * This file is based on rtsp (real) implementation of 
 * mplayer and xine, and includes code from both projects.
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
#include "real.h"
#include "realchallenge.h"
#include "rmff.h"
#include "sdpreal.h"
#include "asmrule.h"

/* realplayer specific stuff */
const char real_clientid[] = "ClientID: Linux_2.6_10.0.0.0_play32_RN01_EN_586";
const char real_useragent[] = "User-Agent: RealMedia Player Version 10.0.0.0 (linux-2.6-libc6-gcc4.1-i586)";
const char real_companyid[] = "CompanyID: X160hPo6JmsD6Vger24oAg==";
const char real_clientchallenge[] = "ClientChallenge: cd7cb1ac431e8d1ad4d2fadb8cf762d1";
const char real_playerstarttime[] = "PlayerStarttime: [19/03/2007:21:42:56 00:00]";
const char real_transport[] = "Transport: x-pn-tng/tcp;mode=play,rtp/avp/tcp;unicast;mode=play";



/*
 * count rules 
 *     >>>> CAUTION <<<<
 * starting '#' is not necessary, but ending ';' is necessary, so 
 * couting ; is correct.
 */
static int count_rules(char *asmrule)
{
    int count = 0;
    while(*asmrule)
	if(*(asmrule++) == ';') count++;
  
    return count;
}

static char *next_rule(char *data)
{
    data = strchr(data,';');
    return data + 1;
}



/*
 * finds rule which matches bandwidth.
 *          return value:    number of matched rules
 * rulearray contains its rule numbers.
 */
int find_asmrule_match(char *asmrule,int **matched,int bw)
{
    int i;
    int rules;
    int matchedrules = 0;

    rules = count_rules(asmrule);
  
    *matched = (int *)xmalloc(sizeof(int) * rules);
  
    for(i = 0; i < rules ;i++) {
	/* copy one rule to string*/
	if(asmrule_match(asmrule,bw)) { /* match!! */
	    display(MSDL_DBG,"rule[%d] = %d matched\n",matchedrules,i);
	    (*matched)[matchedrules] = i;
	    matchedrules++;
	}
	asmrule = next_rule(asmrule);
    }
  
    return matchedrules;
}



/*
 * copy mlti_data('type specific data') to it's buffer.
 *
 * *buf is malloc()ed in this function. don't forget to free later
 */
static int select_mlti_data(uint8_t *mlti_data,int mlti_data_size,
			    int match,uint8_t **buf)
{

    int numrules,codec,size;
    int i;
  
    if(!mlti_data) return 0; /* just to make sure */

    if(memcmp(mlti_data,"MLTI",4)) {
	display(MSDL_ERR | MSDL_VER,
		"'MLTI' not found on OpaqueData base64 decoded string\n");
	*buf = (uint8_t *)xmalloc(mlti_data_size);
	memcpy(*buf,mlti_data,mlti_data_size);
	return mlti_data_size;
    }
  
    mlti_data += 4;
  
    numrules = get16_be(mlti_data);
    if(match >= numrules) {
	display(MSDL_ERR,"matched rule >= number of rules .. strange ...\n");
	return 0;
    }
  
    mlti_data += (match + 1) * 2;
  
    codec = get16_be(mlti_data);
  
    mlti_data += (numrules - match) * 2;

    numrules = get16_be(mlti_data);
  
    if(codec >= numrules) {
	display(MSDL_ERR,"number of codecs >= number of codecs .. strange ...\n");
	return 0;
    }
  
    mlti_data += 2;
  
    for(i = 0; i < codec ; i++) {
	size = get32_be(mlti_data);
	mlti_data += size + 4;
    }

    size = get32_be(mlti_data);
  
    *buf = (uint8_t *)xmalloc(size);
    memcpy(*buf,mlti_data + 4,size);
  
    return size;
}



/*
 * parse sdp(stream description) lines.
 * 
 * subscribe is "Subscribe: " which is send with SET_PARAMETER request.
 *
 */
struct rmff_header_t *real_parse_sdp(char *data,char **subscribe,int bw)
{
  
    struct sdpreal_t *desc;
    struct list_h *p;
    struct rmff_header_t *rmff_header;
    uint8_t *buf;
    int *matched; /* array of matched rules. malloced in find_asmrule_match */
    int matched_rules;
    int i,j;
    int mlti_data_len;
  
    int subscribe_len = BUFSIZE_1K;
    char subbuf[32];
  
    int duration =0;
    int max_bit_rate = 0;
    int avg_bit_rate = 0;
    int max_packet_size = 0;
    int avg_packet_size = 0;

    desc = sdpreal_parse(data);
  
    rmff_header = new_rmff_header_t();
    rmff_header->fileheader = new_rmff_fileheader(4 + desc->stream_count);
    rmff_header->cont = new_rmff_cont(desc->title,desc->author,
				      desc->copyright,desc->abstract);
    rmff_header->data = new_rmff_dataheader(0,0);
    rmff_header->streams =
	xmalloc(sizeof(struct rmff_mdpr_t*) * (desc->stream_count + 1));
    display(MSDL_VER,"number of streams: %u\n",desc->stream_count);
  
    *subscribe = (char *)xmalloc(subscribe_len);
    strcpy(*subscribe,"Subscribe: ");
  
    for(i = 0,p = desc->streams; i < desc->stream_count; i++,p = p->next) {
    
	struct sdpreal_stream_t *stream = p->p;
  
	/*  Subscribe: stream=0;rule=16,stream=0;rule=17 */
    
	matched_rules = find_asmrule_match(stream->asm_rule_book,
					   &matched,bw);
	if((strlen(*subscribe) + 32 * matched_rules) > subscribe_len) {
	    /* subscribe should be longer */
	    subscribe_len = (subscribe_len * 2 >
			     strlen(*subscribe) + 32 * matched_rules) ?
		subscribe_len * 2 : strlen(*subscribe) + 32 * matched_rules;
	    *subscribe = (char *)xrealloc(*subscribe,subscribe_len);
	}
    
	for(j = 0; j < matched_rules ; j++) {
	    snprintf(subbuf,sizeof(subbuf),"stream=%u;rule=%u,",i,matched[j]);
	    strcat(*subscribe,subbuf);
	}
    
	buf = NULL;
	if(!stream->mlti_data) {
	    mlti_data_len = 0;
	}
	else {
	    /* buf is malloc()ed inside select_mlti_data */
	    mlti_data_len = select_mlti_data(stream->mlti_data,
					     stream->mlti_data_size,
					     matched[0],&buf);
	}
    
	rmff_header->streams[i] =
	    new_rmff_mdpr(stream->stream_id,
			  stream->max_bit_rate,
			  stream->avg_bit_rate,
			  stream->max_packet_size,
			  stream->avg_packet_size,
			  stream->start_time,
			  stream->preroll,
			  stream->duration,
			  stream->stream_name,
			  stream->mime_type,
			  mlti_data_len,
			  buf);
    
	duration = (duration > stream->duration) ? 
	    duration : stream->duration;
	max_bit_rate += stream->max_bit_rate;
	avg_bit_rate += stream->avg_bit_rate;
	max_packet_size = (max_packet_size > stream->max_packet_size) ?
	    max_packet_size : stream->max_packet_size;
    
	if(avg_packet_size) {
	    avg_packet_size = (avg_packet_size + stream->avg_packet_size) / 2;
	}
	else {
	    avg_packet_size = stream->avg_packet_size;
	}
    
	if(matched) {
	    free(matched);
	    matched = NULL;
	}
	if(buf) {
	    free(buf);
	    buf = NULL;
	}
    
    }
  
    if((*subscribe)[strlen(*subscribe) -1 ] == ',') {
	(*subscribe)[strlen(*subscribe) -1 ] = '\0'; /* delete last comma */
    }
  
    rmff_header->prop =
	new_rmff_prop(max_bit_rate,
		      avg_bit_rate,
		      max_packet_size,
		      avg_packet_size,
		      0,
		      duration,
		      0,0,0,
		      desc->stream_count,
		      desc->flags);

    rmff_fix_header(rmff_header);
  
    free_sdpreal_t(desc);

    rmff_print_header(rmff_header);
  
    return rmff_header;
}



/*
 * send OPTIONS request, this is used as very first trial to server
 * we will need rtsp_hdr later, so return that.
 * return value:   -1: failure   status_code: success, rtsp_hdr_ret(malloc)
 */
int real_rtsp_options(struct stream_t *stream,struct rtsp_header_t **rtsp_hdr_ret)
{
    int ret = 0;
    struct rtsp_ctrl_t *rtsp_ctrl = stream->stream_ctrl->rtsp_ctrl;
    struct rtsp_header_t *rtsp_hdr = NULL;

        /* default is rtsp-real (becasue OPTIONS req is supported) */
    rtsp_hdr = new_rtsp_header_with_standard_fields(rtsp_ctrl);
    
    rtsp_set_field(rtsp_hdr,real_useragent);
    rtsp_set_field(rtsp_hdr,"GUID: 00000000-0000-0000-0000-000000000000");
    rtsp_set_field(rtsp_hdr,real_clientid);  
    rtsp_set_field(rtsp_hdr,"Pragma: initiate-session");
    rtsp_set_field(rtsp_hdr,"RegionData: 0");
    rtsp_set_field(rtsp_hdr,real_clientchallenge);
    rtsp_set_field(rtsp_hdr,real_companyid);
    rtsp_set_field(rtsp_hdr,real_playerstarttime);
  
    rtsp_request_options(rtsp_hdr,stream->url,NULL);
    rtsp_send_request_and_free(stream,rtsp_hdr);
    
    rtsp_hdr = new_rtsp_header();
    ret = rtsp_recv_header(stream,rtsp_hdr);
    if(ret < 0) {
	goto failed;
    }

    *rtsp_hdr_ret = rtsp_hdr;
    return ret;

  failed:
    if(rtsp_hdr) free_rtsp_header(rtsp_hdr);
    *rtsp_hdr_ret = NULL;
    return -1;
}



/*
 * send DESCRIBE request
 * return value:   -1: failure   status_code: success
 */
static int real_rtsp_describe(struct stream_t *stream,char **description_ret)
{
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    struct rtsp_ctrl_t *rtsp_ctrl = stream_ctrl->rtsp_ctrl;
    struct rtsp_header_t *rtsp_hdr = NULL;

    char *description = NULL;
    char *buffer = xmalloc(BUFSIZE_1K);
    char *field = NULL;
    int len = 0;
    int ret = 0;
    
    rtsp_hdr = new_rtsp_header_with_standard_fields(rtsp_ctrl);
    rtsp_set_field(rtsp_hdr, "Accept: application/sdp");
    rtsp_set_field(rtsp_hdr, real_useragent);
    rtsp_set_field(rtsp_hdr, "Require: com.real.retain-entity-for-setup");
  
    snprintf(buffer,BUFSIZE_1K - 1,"Bandwidth: %u",stream_ctrl->bandwidth);
    rtsp_set_field(rtsp_hdr,buffer); 
  
    rtsp_set_field(rtsp_hdr, "Language: en-US");
    rtsp_set_field(rtsp_hdr, "RegionData: 0");
    rtsp_set_field(rtsp_hdr, real_clientid);
    rtsp_set_field(rtsp_hdr, "GUID: 00000000-0000-0000-0000-000000000000");
    rtsp_set_field(rtsp_hdr, "SupportsMaximumASMBandwidth: 1");
  
  
    rtsp_request_describe(rtsp_hdr,stream->url,NULL);
    rtsp_send_request_and_free(stream,rtsp_hdr);
    
    rtsp_hdr = new_rtsp_header();
    ret = rtsp_recv_header(stream,rtsp_hdr);
    if(ret < 0) {
	free_rtsp_header(rtsp_hdr);
	goto failed;
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
    if((field = rtsp_get_field(rtsp_hdr,"Content-length")) != NULL) {
	while(*field == ' ') field++;
	len = atoi(field);
    }
    else { /* no Content-length */
	display(MSDL_ERR,"warning: No Content-length in fields!!\n");
    }
  
    if((field = rtsp_get_field(rtsp_hdr,"ETag")) != NULL) {
	while(*field == ' ') field++;
	rtsp_ctrl->etag = strdup(field);
    }
    else {
	display(MSDL_ERR,"warning: No ETag!!\n");
	rtsp_ctrl->etag = NULL;
    }

    free_rtsp_header(rtsp_hdr);  
    
    /*
      copy description (sdp)
    */
    description = (char *)xmalloc(len + 1);
    read_data(stream,(uint8_t *)description,len);
    description[len] = '\0';
  
    display(MSDL_DBG,"==================\n%s\n=(%d bytes)========\n",
	    description,(int)strlen(description));
    
    
    if(buffer) free(buffer);
    *description_ret = description;
    return ret;
    
  failed:
    if(description) free(description);
    if(buffer) free(buffer);
    *description_ret = NULL;
    return -1;
}



/*
 * send SETUP requst. 2 setups for multiple streams.
 * return value ...     what rtsp_recv_header returned
 */
static int real_rtsp_setup(struct stream_t *stream,struct rmff_header_t *rmff_header)
{
    struct rtsp_ctrl_t *rtsp_ctrl = stream->stream_ctrl->rtsp_ctrl;
    struct rtsp_header_t *rtsp_hdr = NULL;
    char *buffer = xmalloc(BUFSIZE_1K);
    int ret = 0;
    char challenge2[64];
    char checksum[34];
    
    rtsp_hdr = new_rtsp_header_with_standard_fields(rtsp_ctrl);
    
    real_calc_challenge2_and_checksum(rtsp_ctrl->challenge,challenge2,checksum);
  
    snprintf(buffer,BUFSIZE_1K - 1,"RealChallenge2: %s, sd=%s",
	     challenge2,checksum);
    rtsp_set_field(rtsp_hdr,buffer);
    snprintf(buffer,BUFSIZE_1K - 1,"If-Match: %s",rtsp_ctrl->etag);
    rtsp_set_field(rtsp_hdr,buffer);
    //rtsp_set_field(rtsp_hdr,"RDTFeatureLevel: 2");
    rtsp_set_field(rtsp_hdr,real_transport);
    snprintf(buffer,BUFSIZE_1K - 1,"%s/streamid=0",rtsp_ctrl->mrl);
    rtsp_request_setup(rtsp_hdr,stream->url,buffer);
    rtsp_send_request_and_free(stream,rtsp_hdr);
    
    /* receive message for SETUP */
    ret = rtsp_recv_header_ignore_message(stream);
    
    
    /*
      send messages if multiple stream exists (max is 2 streams anyways...)
    */
    if(rmff_header->prop->num_streams > 1) {
	rtsp_hdr = new_rtsp_header_with_standard_fields(rtsp_ctrl);

	rtsp_set_field(rtsp_hdr,real_transport);
	snprintf(buffer,BUFSIZE_1K - 1,"If-Match: %s",rtsp_ctrl->etag);
	rtsp_set_field(rtsp_hdr,buffer);
	snprintf(buffer,BUFSIZE_1K - 1,"%s/streamid=1",rtsp_ctrl->mrl);
	rtsp_request_setup(rtsp_hdr,stream->url,buffer);
    
	rtsp_send_request_and_free(stream,rtsp_hdr);
	
	/* receive message for SETUP */
	ret = rtsp_recv_header_ignore_message(stream);
    }
    
    free(buffer);
    return ret; 
}



/*
 * send SET_PARAMETER, with stream choosing subscribe.
 * return value ...     what rtsp_recv_header returned
 */
static int real_rtsp_set_parameter(struct stream_t *stream,char *subscribe)
{
    struct rtsp_ctrl_t *rtsp_ctrl = stream->stream_ctrl->rtsp_ctrl;
    struct rtsp_header_t *rtsp_hdr = NULL;
    int ret = 0;
    
    rtsp_hdr = new_rtsp_header_with_standard_fields(rtsp_ctrl);
    rtsp_set_field(rtsp_hdr,subscribe);
    rtsp_request_set_parameter(rtsp_hdr,stream->url,NULL);
    rtsp_send_request_and_free(stream,rtsp_hdr);
    
    /*
      receive message for SET_PARAMETER
      if something to be done, use regular rtsp_recv_header() instead.
    */
    ret = rtsp_recv_header_ignore_message(stream);

    if(stream->dlopts->bandwidth) { /* when user specified bandwidth */
	char *buffer = xmalloc(BUFSIZE_1K);
	
	/* send SET_PARAMETER(2nd) request to download stream */
	rtsp_hdr = new_rtsp_header_with_standard_fields(rtsp_ctrl);
	snprintf(buffer,BUFSIZE_1K - 1,
		 "SetDeliveryBandwidth: Bandwidth=%d;BackOff=0",stream->dlopts->bandwidth);
	rtsp_set_field(rtsp_hdr,buffer);
	rtsp_request_set_parameter(rtsp_hdr,stream->url,NULL);
	rtsp_send_request_and_free(stream,rtsp_hdr);
	
	/*
	  NOT receive message for SET_PARAMETER
	  (!!caution!! the answer will arrive among rdt datas. how dumb!!)
	*/
	free(buffer);
    }
    
    return ret;
}


/*
 * send PLAY request
 * return value ...     what rtsp_recv_header returned
 */
static int real_rtsp_play(struct stream_t *stream)
{
    struct rtsp_ctrl_t *rtsp_ctrl = stream->stream_ctrl->rtsp_ctrl;
    struct rtsp_header_t *rtsp_hdr = NULL;
    char *buffer = xmalloc(BUFSIZE_1K);

    int ret = 0;
    
    rtsp_hdr = new_rtsp_header_with_standard_fields(rtsp_ctrl);

    rtsp_set_range_field(rtsp_hdr,stream->dlopts->range);
    rtsp_set_speed_field(rtsp_hdr,stream->dlopts->speed);
    
    rtsp_request_play(rtsp_hdr,stream->url,NULL);
  
    rtsp_send_request_and_free(stream,rtsp_hdr);
    
    /* receive message for PLAY request */
    ret = rtsp_recv_header_ignore_message(stream);

    free(buffer);
    return ret;
}




/*
 * send SETUP,SEND_REQUEST,PLAY requests.
 *
 *      return value               1: success
 *                                -1: error
 */
int real_setup_and_get_header(struct stream_t *stream,
			      struct rmff_header_t **rmff_header_ret)
{
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    struct rmff_header_t *rmff_header = NULL;
    
    char *description = NULL;
    char *subscribe = NULL;

    /*
      !!!CAUTION!!!
      setting maxbw too high may cause download failure!!
    */
    
    /* don't use this value, its just for choosing highest rate stream */
    if(stream->dlopts->bandwidth) {
	stream_ctrl->bandwidth = stream->dlopts->bandwidth;
    }
    else {
	stream_ctrl->bandwidth = 10485800;
    }


    if(real_rtsp_describe(stream,&description) < 0) {
	goto failed;
    }
    
    /* parse sdp and get information about file to download */
    rmff_header = real_parse_sdp(description,&subscribe,stream_ctrl->bandwidth);
    if(!rmff_header) {
	goto failed;
    }


    display(MSDL_VER,"sdp parse done.\n");

    /* send SETUP request */
    if(real_rtsp_setup(stream,rmff_header) < 0) {
	goto failed;
    }
    
    /* send SET_PARAMETER request to download stream */
    if(real_rtsp_set_parameter(stream,subscribe) < 0) {
	goto failed;
    }
    
    /*  send PLAY request to download stream */
    if(real_rtsp_play(stream) < 0) {
	goto failed;
    }
    
    free(subscribe);
    free(description);
    
    *rmff_header_ret = rmff_header;
    return 1;
  
  failed:
    if(subscribe) free(subscribe);
    if(description) free(description);
    if(rmff_header) free_rmff_header_t(rmff_header);
    *rmff_header_ret = NULL;
    return -1;
}



/*
 * read from media stream. (real rdp packet)
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
int real_rdt_get_media_packet(struct stream_t *stream,
			      uint8_t *buffer,size_t max_size)
{
    int n = 1;
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    uint8_t header[8];
    struct rmff_pheader_t ph = {0};
    int size = 0;
    int flags1 = 0,flags2 = 0;
    int unknown1 = 0;
    uint32_t ts = 0;
  
  
    n = rtsp_real_get_data(stream,header,8); /* through away all non-rdt packets*/
    if(n < 8) {
	if(stream_ctrl->status == streaming_finished){
	    display(MSDL_VER,"got end of stream message packet\n");
	    return 0;
	}
	else {
	    goto failed;
	}
    }
  
    if(header[0] != 0x24) {
	int i;
	display(MSDL_ERR,"wrong rdt magic : [0x%02x]\n",header[0]);
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
  
    if((flags1!=0x40) && (flags1!=0x42)) {
	display(MSDL_DBG | MSDL_ERR,"wrong rdt flags1 : [0x%02x]\n",flags1);
	if(header[6] == 0x06) {
	    display(MSDL_VER,"got end of stream packet\n");
	    stream_ctrl->status = streaming_finished;
	    return 0;
	}
	header[0] = header[5];
	header[1] = header[6];
	header[2] = header[7];
    
	n = read_data(stream,header + 3,5);
	if(n < 5) goto failed;
    
	n = read_data(stream,header + 4,4);
	if(n < 4) goto failed;
    
	flags1 = header[4];
	size -= 9;
    
    }
    flags2 = header[7];
    unknown1 = (header[5] << 16) + (header[6] << 8) + header[7];
    n = read_data(stream,header,6);
    if(n < 6) goto failed;
  
    ts = get32_be(header);
    stream_ctrl->packet_count++;
    display(MSDL_DBG,"ts: %u size: %u, flags:0x%02x, unknown:%u  0x%02x 0x%02x, flags2 %d\n",
	    ts,size,flags1,unknown1,header[4],header[5],flags2);
  
    size += 2;
  
    ph.object_version = 0;
    ph.length = size;
    ph.stream_number = (flags1>>1) & 1;
    ph.timestamp = ts;
    ph.reserved = 0;
  
    if((flags2 & 1) == 0 &&
       (stream_ctrl->rtsp_ctrl->prev_ts != ts ||
	stream_ctrl->rtsp_ctrl->prev_stream_number != ph.stream_number)) {
	stream_ctrl->rtsp_ctrl->prev_ts = ts;
	stream_ctrl->rtsp_ctrl->prev_stream_number = ph.stream_number;
	ph.flags = 2;
    }
  
  
    else {
	ph.flags = 0;
    }
  
    if(max_size > size) {
	/* all data can go to buffer --> just do it!! */
	rmff_dump_pheader(&ph,buffer);
	size -= 12;
	n = read_data(stream,buffer + 12,size);
	if(n <= 0) goto failed;
    
	return n + 12;
    }
    else {
	/*
	  buffer is not enough.. copy max_size data to 
	  buffer and the rest goes to stream_ctrl->buffer.
	*/
	rmff_dump_pheader(&ph,stream_ctrl->write_buffer);
    
	n = read_data(stream,stream_ctrl->write_buffer + 12,size - 12);
	if(n <= 0) goto failed;
    
	/*
	  it is guranteed that stream_ctrl->buffer is
	  practically empty when it comes here!
	  --> reset stream_ctrl->write_pos and so on...
	*/
    
	memcpy(buffer,stream_ctrl->write_buffer,max_size);
	stream_ctrl->write_data_len = size - max_size;
	stream_ctrl->write_pos = max_size;
    
	return max_size;
    }
  
  
  failed:
    return -1;
}



/*
 * get_data function for RTSP-REAL protocol.
 * buffer is guranteed to be bigger than size,
 * if not, this function may cause segfault.
 * all data goes to 'buffer'.
 *        return value     bytes read form sock : success
 *                             0                : EOF (retval of read_data)
 *                            -1                : error
 *
 */
int rtsp_real_get_data(struct stream_t *stream,uint8_t *buffer,size_t size)
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
	    if((field = rtsp_get_field(rtsp_hdr,"Content-length")) != NULL) {
		while(*field == ' ') field++;
		len = atoi(field);	
		body = xmalloc(len);
		len = read_data(stream,body,len);
	    }
	    /* get Cseq, for sending OK back */
	    if((field = rtsp_get_field(rtsp_hdr,"Cseq")) != NULL) {
		while(*field == ' ') field++;
		got_cseq = atoi(field);	
	    }

	    free_rtsp_header(rtsp_hdr);

      
	    if(!memcmp(buffer,"RTSP",4)) { /* RTSP/1.0 200 OK , ignore this */
		/*
		  somehow RTSP message reply can be found among rdt messages.
		  send nothing.
		*/
		if(!got_cseq == rtsp_ctrl->cseq) {
		    display(MSDL_DBG | MSDL_ERR,"CSeq mismatch: expected %d, got %d",
			    rtsp_ctrl->cseq,got_cseq);
		}
		/* DO NOT send RTSP OK */
	    }
	    else { /* not RTSP, whatever it is, send OK would be just fine  */
		rtsp_200ok(stream,got_cseq,rtsp_ctrl->session);
	    }
      
	    free(body);
	    i = read_data(stream,buffer,4);
	}
   
	/* normal case, probably rdp pacekt */
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
