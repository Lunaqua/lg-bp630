/***********************************************************************
 *    network.h: network related utility functions
 ***********************************************************************
 * Copyright (C) 2007 metro <me_t_ro@yahoo.com>
 *
 * This file is part of msdl, media stream downloader
 *
 * network non-related stuff should go msdllib.h
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



#ifndef __NETWORK_H__
#define __NETWORK_H__

#include <inttypes.h>

/*
 * default ports. MMSH is MMS over HTTP, so HTTP port is used.
 */
enum {
    HTTP_PORT = 80,
    HTTP_PROXY_PORT = 8080,
    MMS_PORT  = 1755,
    RTSP_PORT = 554,
    FTP_PORT  = 21,
};



/*
 * protocol type listing
 */
enum {
    UNKNOWN_PROTOCOL, /* unknown protocol                    */
    MMST,             /* mms over tcp                        */
    MMSH,             /* mms over http                       */
    HTTP,             /* http                                */
    RTSP,             /* rtsp (real time streaming protocol) */
    FTP,              /* ftp                                 */
    RTSP_REAL,        /* rtsp - real /helix                  */
    RTSP_WMS,         /* rtsp - windows media server         */
};



/*
 * buffer sizes. don't touch BUF_SIZE !!!
 */
enum {
    BUF_SIZE = 102400,    /* buffer for stream_ctrl->buffer , write_buffer */
    BUFSIZE_4_DL = 10240, /* 10kb                                          */
    BUFSIZE_1K = 1024,
};



enum {
    INT_MAX_BANDWIDTH = 0x7fffffff, /* INT MAX as maximum bandwidth */
};



/*
 * specifies stream.
 */
struct stream_t {
    int sock;                          /* socket to get stream from  */
    struct url_t *url;                 /* url to download.           */
    struct stream_ctrl_t *stream_ctrl; /* status of the stream       */
    struct download_opts_t *dlopts;    /* download options           */
    
    /* downloading functions */
    int (*start)(struct stream_t *);                   /* stream starter */
    int (*read)(struct stream_t *, uint8_t *,size_t);  /* stream reader  */
    void (*close)(struct stream_t *);                  /* stream closer  */
};



/*
 * streaming status which goes to steram_ctrl->status
 */
enum {
    streaming_handshaking,    /* handshaking, doing setup                */
    streaming_downloading,    /* downloading stream                      */
    streaming_finished,       /* end of steam packet received, etc.      */
    streaming_rewind,         /* rewind before write data                */
    streaming_other_protocol, /* use other protocol to download this url */
};



/*
 * protocol unspecific datas.
 */
struct stream_ctrl_t {
    int protocol;               /* downloading protocol                       */

    int packet_length;          /* length of each packet for this stream.     */

    uint64_t file_size;         /* size of downloading file                   */
  
    int total_packets;          /* number of packets to be received.          */
    int packet_count;           /* how many packet received                   */
  
    uint8_t *buffer;            /* buffer for data which read from network    */
    uint32_t buffer_size;       /* size of malloc() [physical size of buffer] */
    uint32_t data_len;          /* how many bytes to be read from now         */
    uint32_t buffer_pos;        /* read by here so far                        */
    
    uint8_t *write_buffer;      /* data to write to file (buffer)             */
    uint32_t write_buffer_size; /* malloc()ed size                            */
    uint32_t write_data_len;    /* how many bytes to be written from now      */
    uint32_t write_pos;         /* written by here so far                     */

    unsigned int bandwidth;     /* bandwidth                                  */
    int status;                 /* playing status                             */
    int retry_protocol;         /* protocol to try again                      */

    union {
	void *data;               /* protocol specific datas goes here          */
	struct mmst_ctrl_t *mmst_ctrl;  /* easy access for mmst_ctrl_t          */
	struct mmsh_ctrl_t *mmsh_ctrl;  /* same as above.                       */
	struct http_ctrl_t *http_ctrl;  /* same as above.                       */
	struct rtsp_ctrl_t *rtsp_ctrl;  /* same                                 */
	struct ftp_ctrl_t  *ftp_ctrl;   /* same                                 */
    };
};


/*
 * example:       exmp://foo.bar.com/example/hoge.asf
 */
struct url_t {
    char *url;         /* exmp://foo.bar.com/example/hoge.asf */
    char *protocol;    /* exmp                                */
    char *hostname;    /* foo.bar.com                         */
    char *file;        /* foo.bar.com/example/hoge.asf        */ /* not malloc()ed */
    char *filepath;    /* /example/hoge.asf                   */ /* not malloc()ed */
    unsigned int port; /* some port                           */
    int protocol_type; /* protocol type (above)               */
    char *username;
    char *password;
    char *http_proxy;
};



struct stream_t *new_stream_t(void);
void free_stream_t(struct stream_t *st);

struct stream_ctrl_t *new_stream_ctrl_t(void);
void free_stream_ctrl_t(struct stream_ctrl_t *sc);

struct url_t *new_url_t(char *url);
void free_url_t(struct url_t *u);

inline int url_valid_char(int c);
void url_unescape_string(char *dst,char *src);
int set_protocol_type(char *protocol,struct url_t *u);

int sock_check_data(int sock,int timeout);

int server_connect(const char *servername,const int port);
int waiting_socket(int family,int port);
int accept_connection(int wait_sock);

int xrecv(int sock,void *buf,size_t count);
int xsend(int sock,void *buf,size_t count);

int stream_check_data(struct stream_t *stream,int timeout);
int get_data(int sock, void *buf, size_t count);
int read_data(struct stream_t *stream, void *buffer, size_t size);
int recv_data(struct stream_t *stream, void *buffer, size_t max);
int stream_data_push_back(struct stream_t *stream,void *buffer,int size);


#endif /* __NETWORK_H__ */
