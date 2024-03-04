/***********************************************************************
 *    asf.c:  for interpreting ASF(Advanced Systems Format) format
 ***********************************************************************
 * Copyright (C) 2007 metro <me_t_ro@yahoo.com>
 *
 * This file is part of msdl, media stream downloader
 *
 * This file is based on asf implementation of mplayer,
 * and various information on the internet about ASF.
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
#include "asf.h"

/*
  guid
*/
const char asf_stream_header_guid[16] =
{0x91, 0x07, 0xdc, 0xb7, 0xb7, 0xa9, 0xcf, 0x11,
 0x8e, 0xe6, 0x00, 0xc0, 0x0c, 0x20, 0x53, 0x65};

const char asf_file_header_guid[16] = 
{0xa1, 0xdc, 0xab, 0x8c, 0x47, 0xa9, 0xcf, 0x11,
 0x8e, 0xe4, 0x00, 0xc0, 0x0c, 0x20, 0x53, 0x65};

const char asf_content_desc_guid[16] =
{0x33, 0x26, 0xb2, 0x75, 0x8e, 0x66, 0xcf, 0x11,
 0xa6, 0xd9, 0x00, 0xaa, 0x00, 0x62, 0xce, 0x6c};

const char asf_stream_group_guid[16] =
{0xce, 0x75, 0xf8, 0x7b, 0x8d, 0x46, 0xd1, 0x11,
 0x8d, 0x82, 0x00, 0x60, 0x97, 0xc9, 0xa2, 0xb2};

const char asf_data_chunk_guid[16] =
{0x36, 0x26, 0xb2, 0x75, 0x8e, 0x66, 0xcf, 0x11,
 0xa6, 0xd9, 0x00, 0xaa, 0x00, 0x62, 0xce, 0x6c};

const char asf_ext_stream_embed_stream_header[16] =
{0xe2, 0x65, 0xfb, 0x3a, 0xef, 0x47, 0xf2, 0x40,
 0xac, 0x2c, 0x70, 0xa9, 0x0d, 0x71, 0xd3, 0x43};

const char asf_ext_stream_audio[16] =
{0x9d, 0x8c, 0x17, 0x31, 0xe1, 0x03, 0x28, 0x45,
 0xb5, 0x82, 0x3d, 0xf9, 0xdb, 0x22, 0xf5, 0x03};

const char asf_ext_stream_header[16] =
{0xCB, 0xA5, 0xE6, 0x14, 0x72, 0xC6, 0x32, 0x43,
 0x83, 0x99, 0xA9, 0x69, 0x52, 0x06, 0x5B, 0x5A};


static int max_idx(int s_count,int *s_rates,int bound);
static struct asf_file_header_t *new_asf_file_header_t(void);
static void free_asf_file_header_t(struct asf_file_header_t *ast);

static struct asf_streams_t *new_asf_streams_t(void);
static void free_asf_streams_t(struct asf_streams_t *ast);



static struct asf_file_header_t *new_asf_file_header_t(void)
{
    struct asf_file_header_t *afht =
	(struct asf_file_header_t *)xmalloc(sizeof(struct asf_file_header_t));
    return afht;
}



static void free_asf_file_header_t(struct asf_file_header_t *afht)
{
    free(afht);
}



static struct asf_streams_t *new_asf_streams_t(void)
{
    struct asf_streams_t *ast =
	(struct asf_streams_t *)xmalloc(sizeof(struct asf_streams_t));
    return ast;
}



static void free_asf_streams_t(struct asf_streams_t *ast)
{
    if(ast->audio_streams) free(ast->audio_streams);
    if(ast->video_streams) free(ast->video_streams);
    free(ast);
}



struct asf_headerinfo_t *new_asf_headerinfo_t(void)
{
    struct asf_headerinfo_t *info =
	(struct asf_headerinfo_t *)xmalloc(sizeof(struct asf_headerinfo_t));

    info->asf_header = NULL;
    info->asf_header_len = 0;

    info->fileh = new_asf_file_header_t();
    info->streams = new_asf_streams_t();
    return info;
}



void free_asf_headerinfo_t(struct asf_headerinfo_t *info)
{
    if(info->asf_header) free(info->asf_header);

    if(info->fileh) free_asf_file_header_t(info->fileh);
    if(info->streams) free_asf_streams_t(info->streams);
    free(info);
}



/* 
 * buffer is starting of asf header, and asf_header_size is its size.
 * in asf_choose_best_streams, choose highest streams which works fine
 * with specified bandwidth(bw).
 * all parsed result will be available in asf_http_ctrl;
 *              *** THIS IS A WRAPPER FUNCTION ***
 *                 return value:      1 ... success
 *                                   -1 ... error
 */
int asf_interpret_header(struct asf_headerinfo_t *hinfo,const int bw,
			 const uint8_t *buffer,const size_t asf_header_size)
{
    int ret = 0;
    /*
      The ASF header might come in more than 2 packets. so we have to 
      get all chunks before construct asf_header_t(asfh).
    */  
    ret = asf_get_file_property(buffer,
				asf_header_size,
				hinfo->fileh);
    if(ret < 0) { /* error */
	display(MSDL_ERR,"packet size get error\n");
	return -1;
    }
  
    ret = asf_choose_best_streams(buffer,asf_header_size,
				  bw, /* choose maximum bandwidth */
				  hinfo->streams);
    return ret;
}



int find_asf_guid(const uint8_t *buffer,const char *guid, int cur_pos,int buffer_len)
{
    int i = 0;
    for(i = cur_pos; i < buffer_len - 19 ; i++) {
	if(memcmp(buffer + i,guid,16) == 0)
	    return i + 16 + 8; /* point after guid + length */
    }
    return -1;
}



/*
 * find best idx
 */
static int max_idx(int s_count,int *s_rates,int bound)
{
    int i = 0, best = -1, rate = -1;
    for(i = 0; i< s_count; i++) {
	if(s_rates[i] > rate && s_rates[i] <= bound) {
	    rate = s_rates[i];
	    best = i;
	}
    }
    return best;
}



/*
 * get file property.
 * return value        packet size  ... success
 *                              -1  ... error
 */
int asf_get_file_property(const uint8_t *header,int asf_header_size,
			  struct asf_file_header_t *fileh)
{
    int pos = 0;
    int packet_size = -1;
  
    /*
      examine file properties
    */
    pos = find_asf_guid(header,asf_file_header_guid,0,asf_header_size);
  
    if(pos >= 0) { /* found --> comes file header. */
    
	if((pos + sizeof(struct asf_file_header_t )) > asf_header_size) /* 2big */
	    return -1;
    
    
	memcpy((uint8_t *)fileh,header + pos,sizeof(struct asf_file_header_t));
    
	/* little endian */
	fileh->file_size = le2me_64(fileh->file_size);
	fileh->creation_time = le2me_64(fileh->creation_time);
	fileh->num_packets = le2me_64(fileh->num_packets);
	fileh->play_duration = le2me_64(fileh->play_duration);
	fileh->send_duration = le2me_64(fileh->send_duration);
	fileh->preroll = le2me_64(fileh->preroll);
	fileh->flags = le2me_32(fileh->flags);
	fileh->min_packet_size = le2me_32(fileh->min_packet_size);
	fileh->max_packet_size = le2me_32(fileh->max_packet_size);
	fileh->max_bitrate = le2me_32(fileh->max_bitrate);
    
	display(MSDL_VER,"file size = %lld\n",fileh->file_size);
	display(MSDL_VER,"play_duration = %d\n",
		fileh->play_duration/10000000);
	display(MSDL_VER,"send_duration = %d\n",
		fileh->send_duration/10000000);
	display(MSDL_VER,"# of packets = %d\n",
		fileh->num_packets);
	display(MSDL_VER,"flags = %x\n",
		fileh->flags);
    
	packet_size = fileh->max_packet_size;

	display(MSDL_VER,"packet_size = %d\n",
		fileh->max_packet_size);
	display(MSDL_VER,"min_packsize = %d\n",
		fileh->min_packet_size);
    
    }
  
    /* packet_size = -1 if error. */
    return packet_size;
}



/*
 * choose best audio and video streams, below bw.
 * 'header' is an asf header, and 'asf_header_size' is its size.
 * the result will be in 'streams'
 *             return value:   1  ... success
 *                            -1  ... failed
 */
int asf_choose_best_streams(const uint8_t *header, int asf_header_size,
			    int bw, struct asf_streams_t *streams)
{

    int i,pos,start;
    int *v_rates = NULL, *a_rates = NULL;
    int v_rate = 0, a_rate = 0, v_idx = -1, a_idx = -1;
  
    /*
      header is entire ASF header, including very first asf_header_t.
    */
    pos = sizeof(struct asf_header_t);
    start = pos;
  
    /*
      choose best (fastest) streams
    */
  
    while((pos = find_asf_guid(header,asf_stream_header_guid,pos,asf_header_size)) >= 0) {
	struct asf_stream_header_t *streamh = 
	    (struct asf_stream_header_t *)(header + pos);

	pos += sizeof(struct asf_stream_header_t);
	if(pos > asf_header_size)  /* error */
	    return -1;
    
    
	/* get ASF GUID PREFIX (first 4 byte of GUID) */
	switch(get32_le(streamh->type)) {

      
	case 0xf8699e40: /* audio stream */
	    display(MSDL_VER,"audio stream detected!!!!\n");
	    if(streams->audio_streams == NULL) { /* no audio stream registerd yet */
		streams->audio_streams = (int *)xmalloc(sizeof(int));
		streams->n_audio = 1;
	    }
	    else { /* more audio streams.!! */
		streams->n_audio++;
		streams->audio_streams = 
		    (int *)xrealloc(streams->audio_streams,
				    streams->n_audio * sizeof(int));
	    }
	    streams->audio_streams[streams->n_audio - 1] =
		le2me_16(streamh->stream_no);
	    break;
      
	case 0xbc19efc0: /* video streams */
	    display(MSDL_VER,"video stream detected!!!!\n");
	    if(streams->video_streams == NULL) { /* no video stream registerd yet */
		streams->video_streams = (int *)xmalloc(sizeof(int));
		streams->n_video = 1;
	    }
	    else { /* more video streams.!! */
		streams->n_video++;
		streams->video_streams = 
		    (int *)xrealloc(streams->video_streams,
				    streams->n_video * sizeof(int));
	    }
	    streams->video_streams[streams->n_video - 1] =
		le2me_16(streamh->stream_no);
	    break;
      
	case 0x59dacfc0: /* Command media stream... whatever*/
	    display(MSDL_VER,"Command media stream detected, but ignore this.\n");
	    break;
      
	case 0x91bd222c: /* File transfer media stream... 
			    I don't know what the heck this is.*/
	    display(MSDL_VER,"File transfer stream detected, but ignore this.\n");
	    break;
      
	}
    }

    a_rates = xmalloc(streams->n_audio * sizeof(int));
    v_rates = xmalloc(streams->n_video * sizeof(int));

  
    pos = find_asf_guid(header,asf_stream_group_guid,start,asf_header_size);
    if(pos >= 0) {
	/* stream bitrate proterties object */

	int stream_count = 0;
	uint8_t *ptr = (uint8_t *)header + pos;
    
	display(MSDL_VER,"stream bitrate properties object\n");
    
	stream_count = (*(uint16_t*)ptr); /* little endian. */
	ptr += sizeof(uint16_t);
	if(ptr > header + asf_header_size) goto failed;
	display(MSDL_VER,"stream count = [0x%x] [%u]\n",stream_count,stream_count);
	display(MSDL_VER,"audio streams: %d, video streams: %d\n",
		streams->n_audio,streams->n_video);

	for(i = 0; i < stream_count; i++) {
	    uint32_t rate = 0;
	    int id = 0;
	    int j = 0;
      
	    id = get16_le(ptr);
	    ptr += sizeof(uint16_t);
	    if(ptr > header + asf_header_size) goto failed;
	    rate = get32_le(ptr);
	    ptr += sizeof(uint32_t);
	    if(ptr > header + asf_header_size) goto failed;
      
	    display(MSDL_VER,"stream_id =  [0x%x] [%u]\n"
		    "max bitrate = [0x%x] [%u]\n",
		    id,id,rate,rate);
      
	    for(j = 0; j < streams->n_audio; j++) {
		if(id == streams->audio_streams[j]) {
		    display(MSDL_VER,"is audio stream\n");
		    a_rates[j] = rate;
		    break;
		}
	    }
	    for(j = 0; j < streams->n_video; j++) {
		if(id == streams->video_streams[j]) {
		    display(MSDL_VER,"is video stream\n");
		    v_rates[j] = rate;
		    break;
		}
	    }
	}
    }

    /* just to make sure bw is not Zero! */
    if(bw == 0) {
	bw = INT_MAX_BANDWIDTH;
    }

    if(streams->n_audio) {
	/* find lowest-bitrate audio stream */
	a_rate = a_rates[0];
	a_idx = 0;
	for(i = 0; i < streams->n_audio; i++) {
	    if(a_rates[i] < a_rate) {
		a_rate = a_rates[i];
		a_idx = i;
	    }
	}
	if(max_idx(streams->n_video,v_rates,bw - a_rate) < 0) {
	    /* both audio and video are not possible, try video only next */
	    a_idx = -1;
	    a_rate = 0;
	}
    }
  
    /* find best video stream */
    v_idx = max_idx(streams->n_video,v_rates,bw - a_rate);
    if(v_idx >= 0) {
	v_rate = v_rates[v_idx];
    }
  
    /* find best auido stream */
    a_idx = max_idx(streams->n_audio,a_rates,bw - v_rate);

    free(v_rates);
    v_rates = NULL;
    free(a_rates);
    a_rates = NULL;

    if(a_idx < 0 && v_idx < 0) {
	display(MSDL_ERR,"bandwidth too low ... cannot get streams.");
	return -1;
    }
  
  
    if(a_idx >= 0) {
	streams->audio_id = streams->audio_streams[a_idx];
    }
    else if(streams->n_audio) {
	display(MSDL_ERR,"Bandwidth too too small so deselected audio....sad.\n");
    }
  
    if(v_idx >= 0) {
	streams->video_id = streams->video_streams[v_idx];
    }
    else if(streams->n_video) {
	display(MSDL_ERR,"Bandwidth too too small so deselected video....sad.\n");
    }
  
  
    return 1;

  failed:
    free(v_rates);
    free(a_rates);
    return -1;

}

