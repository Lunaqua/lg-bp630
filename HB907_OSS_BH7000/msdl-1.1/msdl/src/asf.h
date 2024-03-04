/***********************************************************************
 *    asf.h:  for undersanding ASF(Advanced Systems Format) format
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



#ifndef __ASF_H__
#define __ASF_H__


/*
 * ASF Object header
 */
struct __attribute__((packed))  asf_obj_header_t {
    uint8_t guid[16];
    uint64_t size;
}; 


/*
 * M$ ASF Header!!
 */
struct __attribute__((packed)) asf_header_t {
    struct asf_obj_header_t objh; // object header
    uint32_t cno; // number of subchunks
    uint8_t v1;   // unknown (0x01)
    uint8_t v2;   // unknown (0x02)
};


/* 
 * M$ ASF file header.
 */
struct __attribute__((packed)) asf_file_header_t {
    uint8_t  stream_id[16];  // stream GUID
    uint64_t file_size;
    uint64_t creation_time;  //File creation time FILETIME 8
    uint64_t num_packets;    //Number of packets UINT64 8
    uint64_t play_duration;  //Timestamp of the end position UINT64 8
    uint64_t send_duration;  //Duration of the playback UINT64 8
    uint64_t preroll;        //Time to bufferize before playing UINT32 4
    uint32_t flags;          //Unknown, maybe flags ( usually contains 2 ) UINT32 4
    uint32_t min_packet_size;//Min size of the packet, in bytes UINT32 4
    uint32_t max_packet_size;//Max size of the packet  UINT32 4
    uint32_t max_bitrate;    //Maximum bitrate of the media (sum of all the stream)
};

struct __attribute__((packed)) asf_stream_header_t {
    uint8_t type[16]; // Stream type (audio/video) GUID 16
    uint8_t concealment[16]; // Audio error concealment type GUID 16
    uint64_t unk1; // Unknown, maybe reserved ( usually contains 0 ) UINT64 8
    uint32_t type_size; //Total size of type-specific data UINT32 4
    uint32_t stream_size; //Size of stream-specific data UINT32 4
    uint16_t stream_no; //Stream number UINT16 2
    uint32_t unk2; //Unknown UINT32 4
};



struct __attribute__((packed)) asf_stream_chunk_t {
    uint16_t type;
    uint16_t size;
    uint32_t seqnum;
};



struct __attribute__((packed)) asf_stream_chunk_extra_t {
    uint16_t unknown;
    uint16_t size_confirm;
};



struct asf_streams_t {
    int audio_id,video_id; /* best stuff. (ids)   */
    int n_audio, n_video;  /* numbers of streams  */
    int *audio_streams,*video_streams; /* streams */
};



struct asf_headerinfo_t {
    uint8_t *asf_header;              /* raw header         */
    int asf_header_len;               /* its length         */
    struct asf_file_header_t *fileh;  /* basic information  */
    struct asf_streams_t *streams;    /* stream information */
};



struct asf_headerinfo_t *new_asf_headerinfo_t(void);
void free_asf_headerinfo_t(struct asf_headerinfo_t *info);

int asf_interpret_header(struct asf_headerinfo_t *hinfo,const int bw,
			 const uint8_t *buffer,const size_t asf_header_size);

int find_asf_guid(const uint8_t *buffer,const char *guid, int cur_pos,int buffer_len);

int asf_get_file_property(const uint8_t *header,int asf_header_size,
			  struct asf_file_header_t *fileh);
int asf_choose_best_streams(const uint8_t *header, int asf_header_size,
			    int bw, struct asf_streams_t *streams);

#endif /* __ASF_H__ */
