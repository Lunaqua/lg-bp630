/***********************************************************************
 *    ftp.c: for downloading via ftp (File Transfer Protocol)
 ***********************************************************************
 * Copyright (C) 2007 metro <me_t_ro@yahoo.com>
 *
 * This file is part of msdl, media stream downloader
 *
 * This is just an very simple ftp implementation.
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
#include <ctype.h>
#include <time.h>

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>

#include "msdl.h"
#include "msdllib.h"
#include "display.h"
#include "network.h"
#include "ftp.h"



static int is_lf_terminated_line(char *buf);
static inline int is_ftp_OK(int code);
static int ftp_recv_response(struct stream_t *stream,struct ftp_response_t *fres);
static int ftp_recv_response_ignore_message(struct stream_t  *stream);


static int ftp_send_command(struct stream_t *stream,char *msg);
static int ftp_read_welcome(struct stream_t *stream);
static int ftp_user(struct stream_t *stream,const char *username);
static int ftp_pass(struct stream_t *stream,const char *password);
static int ftp_epsv(struct stream_t *stream,const char *connect_host,int *newsock);
static int ftp_pasv(struct stream_t *stream,int *newsock);
static int ftp_passive_mode(struct stream_t *stream,
			    const char *current_host,int *newsock);
static int ftp_tell_port(struct stream_t *stream,int *newsock);
static int ftp_bin_mode(struct stream_t *stream);
static int ftp_ask_size(struct stream_t *stream,const char *filepath,uint64_t *size);
static int ftp_retr(struct stream_t *stream,const char *filepath);



static const char ftp_default_passwd[] = "hugahuga@huhun.com";


struct ftp_ctrl_t *new_ftp_ctrl_t(void)
{
    struct ftp_ctrl_t *fctrl = xmalloc(sizeof(struct ftp_ctrl_t));
    memset(fctrl,0,sizeof(struct ftp_ctrl_t));
    fctrl->mode = PASSIVE_FTP; /* defualt passive mode (this is easyer)*/
    return fctrl;
}



void free_ftp_ctrl_t(struct ftp_ctrl_t *fctrl)
{
    if(fctrl->command_sock > 0) close(fctrl->command_sock);
    if(fctrl->data_wait_sock > 0) close(fctrl->data_wait_sock);
    if(fctrl->data_sock > 0) close(fctrl->data_sock);
    
    free(fctrl);
}



struct ftp_response_t *new_ftp_response_t(void)
{
    struct ftp_response_t *fres = xmalloc(sizeof(struct ftp_response_t));
    fres->num_lines = 0;
    fres->lines = NULL;
    
    return fres;
}



void free_ftp_response_t(struct ftp_response_t *fres)
{
    if(fres->lines) free_list_h(fres->lines,*free);
    free(fres);
}



static int is_lf_terminated_line(char *buf)
{
    if(buf == NULL) return 0;
    
    if(strchr(buf,'\n')) {
	return 1;
    }
    return 0;
}



static int ftp_recv_response(struct stream_t *stream,struct ftp_response_t *fres)
{

    size_t linebuflen = 0;
    char *linebuffer = NULL;
    int i;
    int total = 0;
    int status_code;

    do {
	linebuflen = 0;
	linebuffer = NULL;
	total = 0;
	char *lineend;

	
	do {
	    linebuflen += BUFSIZE_1K;
	    linebuffer = (char *)xrealloc(linebuffer,
					  linebuflen + 1);
	
	    /*
	      set zero to realloc()ed region
	      +1 for NULL char at the end of buffer.
	    */
	    memset(linebuffer + total,0,linebuflen + 1 - total);
	    
	    
	    i = recv_data(stream,linebuffer,linebuflen);
	    if(i <= 0) {

		display(MSDL_ERR,"xrecv error: xrecv() returned %d\n",i);
		goto failed;
	    }
		
	    total += i;
	} while(!is_lf_terminated_line(linebuffer));
	
	lineend = strchr(linebuffer,'\n');
	lineend++;
	
	stream_data_push_back(stream,lineend,total - (lineend - linebuffer));

	memset(lineend,0,total - (lineend - linebuffer));
	list_h_append(&fres->lines,linebuffer);
	
	fres->num_lines++;
	
	display(MSDL_DBG,"FTP RESPONSE line ==================\n"
		"%s"
		"==(%d bytes)\n",linebuffer,lineend - linebuffer);
	
	status_code = (linebuffer[0] - '0') * 100 + 
	    (linebuffer[1] - '0') * 10 + (linebuffer[2] - '0');
	
    } while(isdigit(linebuffer[0]) && isdigit(linebuffer[1]) &&
	    isdigit(linebuffer[2]) && (linebuffer[3] == '-') );
    
    return status_code;
    
  failed:
    if(linebuffer) free(linebuffer);
    return 0;
}



/*
 * return 1 if ftp message is positive 
 *        0 if negative (not OK message)
 */
static inline int is_ftp_OK(int code)
{
    return ((100 <= code) && (code < 400)) ? 1 : 0;
}



/*
 *  get rtsp response but ignore its message
 *              return value:     status code
 */
static int ftp_recv_response_ignore_message(struct stream_t  *stream)
{
    struct ftp_response_t *ftp_response;
    int status_code;

    ftp_response = new_ftp_response_t();
    status_code = ftp_recv_response(stream,ftp_response);
    if(!is_ftp_OK(status_code) && ftp_response->lines) {
	display(MSDL_ERR,"%s",ftp_response->lines->p);
    }
    free_ftp_response_t(ftp_response);
    
    return status_code;
}



/*
 * send ftp command string.
 *              return value:     what xsend returned
 */
static int ftp_send_command(struct stream_t *stream,char *msg)
{
    int ret;
    
    display(MSDL_DBG,"--------------------------\nsent: %s\n",msg);
    ret = xsend(stream->stream_ctrl->ftp_ctrl->command_sock,msg,strlen(msg));

    return ret;
}



/*
 * read first messgae from ftp server
 *              return value:     status code: normal case
 */
static int ftp_read_welcome(struct stream_t *stream)
{
    struct ftp_response_t *ftp_response;
    int status_code;
    
    ftp_response = new_ftp_response_t();
    status_code = ftp_recv_response(stream,ftp_response);
    if(ftp_response->lines) {
	display(MSDL_VER,"%s\n",ftp_response->lines->p);
    }
    free_ftp_response_t(ftp_response);

    return status_code;
}



/*
 * user name
 *              return value:     status code: normal case
 */
static int ftp_user(struct stream_t *stream,const char *username)
{
    char *sendbuffer = xmalloc(strlen(username) + 16);
    int status_code;

    snprintf(sendbuffer,BUFSIZE_1K,"USER %s\n",username);
    ftp_send_command(stream,sendbuffer);

    status_code = ftp_recv_response_ignore_message(stream);

    free(sendbuffer);
    return status_code;
}



/*
 * password verification
 *              return value:     status code: normal case
 */
static int ftp_pass(struct stream_t *stream,const char *password)
{
    struct ftp_response_t *ftp_response;
    char *sendbuffer = xmalloc(strlen(password) + 16);
    int status_code;

    snprintf(sendbuffer,BUFSIZE_1K,"PASS %s\n",password);
    ftp_send_command(stream,sendbuffer);
    
    ftp_response = new_ftp_response_t();
    status_code = ftp_recv_response(stream,ftp_response);
    if(!is_ftp_OK(status_code) && ftp_response->lines) {
	display(MSDL_ERR,"FTP login failed: %s\n",ftp_response->lines->p);
    }
    free_ftp_response_t(ftp_response);
    
    free(sendbuffer);
    return status_code;
}



/*
 * switch to extended passive mode. 'newsock' is new data_socket opened by
 * entering passive mode. needs current conneting host as argument
 *              return value:   status code
 */
static int ftp_epsv(struct stream_t *stream,const char *connect_host,int *newsock)
{
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    struct ftp_response_t *ftp_response;
    int status_code;
    int data_sock = 0;

    ftp_send_command(stream,"EPSV\n");

    ftp_response = new_ftp_response_t();
    status_code = ftp_recv_response(stream,ftp_response);
    if(!is_ftp_OK(status_code)) {
	/*ACTIVE mode*/
	stream_ctrl->ftp_ctrl->mode = ACTIVE_FTP;
	free_ftp_response_t(ftp_response);
	*newsock = 0;
	return status_code;
    }
    else {
	/*
	  rfc 2428
	  <text indicating server is entering extended passive mode>	\
	  (<d><d><d><tcp-port><d>)
	*/
	char *line = ftp_response->lines->p;
	char *p;
	
	p = strchr(line,')');
	if(p) {
	    int epsvport = 0;
	    char delimiter;
	    p--; /* backword skip ')' */
	    while(*p == ' ') p--;
	    delimiter = *p;
	    p--; /* backword skip '|' */
	    for(; (p >= line) && (*p != delimiter) ; p--);
	    p++;
	    for(; isdigit(*p) ; p++) {
		epsvport *= 10;
		epsvport += *p - '0';
	    }
	    
	    data_sock = server_connect(connect_host,epsvport);
	    free_ftp_response_t(ftp_response);
	    *newsock = data_sock;
	    return status_code;
	}
    }
    
    free_ftp_response_t(ftp_response);
    *newsock = data_sock;
    return status_code;
}



/*
 * switch to passive mode. 'newsock' is new data_socket opened by
 * entering passive mode.
 *              return value:   status code
 */
static int ftp_pasv(struct stream_t *stream,int *newsock)
{
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    struct ftp_response_t *ftp_response;
    int data_sock = 0;
    int status_code;
    
    ftp_send_command(stream,"PASV\n");
    
    ftp_response = new_ftp_response_t();
    status_code = ftp_recv_response(stream,ftp_response);
    if(!is_ftp_OK(status_code)) {
	/*ACTIVE mode*/
	stream_ctrl->ftp_ctrl->mode = ACTIVE_FTP;
	free_ftp_response_t(ftp_response);
	*newsock = 0;
	return status_code;
    }
    else {
	char connect_host[INET6_ADDRSTRLEN + 4];
	/* PASSIVE mode */
	int pasvport;
	int i;
	int value[6] = {0,0,0,0,0,0};
	char *line = ftp_response->lines->p;
	char *p;
	
	p = strrchr(line,',');
	if(p) {
	    for( ; (p >= line) && (isdigit(*p) || *p == ',') ; p--);
	    if(p <= line) {
		free_ftp_response_t(ftp_response);
		return 0;
	    }
	    p++;
	    
	    for(i = 0; i < 6 ; i++) {
		value[i] = 0;
		for(; p && *p && isdigit(*p) ; p++) {
		    value[i] *= 10;
		    value[i] += *p - '0';
		}
		value[i] &= 0xff;
		if(!p || !(*p)) {
		    break;
		}
		else {
		    p++;
		}
	    }
	    
	    if(p && *p) {
		snprintf(connect_host,sizeof(connect_host) - 1,
		         "%d.%d.%d.%d",value[0],value[1],value[2],value[3]);
		pasvport = (value[4] << 8) + value[5];
		data_sock = server_connect(connect_host,pasvport);
		free_ftp_response_t(ftp_response);
		*newsock = data_sock;
		return status_code;
	    }
	}
    }
    
    /*cannot get port number from recv reply*/
    display(MSDL_ERR,"cannot get port number from recv reply\n");
    stream_ctrl->ftp_ctrl->mode = ACTIVE_FTP;
    free_ftp_response_t(ftp_response);
    *newsock = 0;
    return status_code;
}



/*
 * switch to passive mode. 'newsock' is new data_socket opened by
 * current_host is needed by ftp_epsv.
 *              return value:   status code
 */
static int ftp_passive_mode(struct stream_t *stream,const char *current_host,int *newsock)
{
    struct sockaddr_storage ss;
    socklen_t sslen;
    int command_sock = stream->stream_ctrl->ftp_ctrl->command_sock;
    
    /* choose same protocol family as command_sock */
    memset(&ss,0,sizeof(ss));
    sslen = sizeof(ss);
    if(getsockname(command_sock,(struct sockaddr *)&ss,&sslen) < 0) {
	perror("getsockname() failed");
	*newsock = 0;
	return -1;
    }
    
    if(ss.ss_family == AF_INET) {       /* IPv4 */
	/*return ftp_epsv(stream,current_host,newsock);*/
	return ftp_pasv(stream,newsock);
    }
    else if(ss.ss_family == AF_INET6) { /* IPv6 */
	if(!current_host) {
	    return -1;
	}
	return ftp_epsv(stream,current_host,newsock);
    }
    else {
	display(MSDL_ERR,"unknown protocol family %d\n",ss.ss_family);
    }
    
    return -1;
}



/*
 * tell waiting port to ftp server, only used in active mode.
 *             return value:     status code: normal case
 *                                        -1: internal error
 */
static int ftp_tell_port(struct stream_t *stream,int *newsock)
{
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    int command_sock = stream_ctrl->ftp_ctrl->command_sock;
    int data_wait_sock;

    char sendbuffer[INET6_ADDRSTRLEN + 32];
    struct sockaddr_storage ss;
    socklen_t sslen;
    int local_port;
    
    /* get ip address of myself */
    memset(&ss,0,sizeof(ss));
    sslen = sizeof(ss);
    if(getsockname(command_sock,(struct sockaddr *)&ss,&sslen) < 0) {
	perror("getsockname() failed");
	*newsock = 0;
	return -1;
    }
   

    /* for random port number */
    srand(time(NULL));
    local_port = 49152 + (rand() % (65535 - 49152));
    
    /* use same protocol family as command_socket */
    data_wait_sock = waiting_socket(ss.ss_family,local_port);
    if(data_wait_sock < 0) {
	*newsock = 0;
	return -1;
    }    
    
    if(ss.ss_family == AF_INET) {
	struct sockaddr_in *sinp;
	uint32_t local_ipv4addr;
	    
	sinp = (struct sockaddr_in *)&ss;
	
	local_ipv4addr = ntohl(sinp->sin_addr.s_addr);
	snprintf(sendbuffer,sizeof(sendbuffer) - 1,"PORT %d,%d,%d,%d,%d,%d\n",
		 (local_ipv4addr >> 24) & 0xff,
		 (local_ipv4addr >> 16) & 0xff,
		 (local_ipv4addr >>  8) & 0xff,
		 (local_ipv4addr      ) & 0xff,
		 (local_port >> 8) & 0xff,
		 (local_port     ) & 0xff);
	
	/*
	  {
	  char local_ipv4addrstr[INET6_ADDRSTRLEN + 5];
	  inet_ntop(sinp->sin_family,&(sinp->sin_addr.s_addr),
	  local_ipv4addrstr,INET6_ADDRSTRLEN + 4);
	  snprintf(sendbuffer,sizeof(sendbuffer) - 1,
	  "EPRT  |1|%s|%d|\r\n",local_ipv4addrstr,local_port);
	  }
	*/

    }
    else if(ss.ss_family == AF_INET6) {
	struct sockaddr_in6 *sin6p;
	char local_ipv6addr[INET6_ADDRSTRLEN + 4];
	
	sin6p = (struct sockaddr_in6 *)&ss;
	inet_ntop(sin6p->sin6_family,&(sin6p->sin6_addr.s6_addr),
		  local_ipv6addr,INET6_ADDRSTRLEN + 4);
	
	snprintf(sendbuffer,sizeof(sendbuffer) - 1,
		 "EPRT |2|%s|%d|\n",local_ipv6addr,local_port);
	
    }
    else {
	display(MSDL_ERR,"unknown protocol family: %d\n",
		ss.ss_family);
	*newsock = 0;
	return -1;
    }
    
    ftp_send_command(stream,sendbuffer);
    
    *newsock = data_wait_sock;
    
    return ftp_recv_response_ignore_message(stream);
}



/*
 * switch to ftp binary mode
 *             return value:     status code
 */
static int ftp_bin_mode(struct stream_t *stream)
{
    ftp_send_command(stream,"TYPE I\n");
    return ftp_recv_response_ignore_message(stream);
}



/*
 * ask file size of 'filepath' and it goes to 'size'
 *             return value:     status code
 */
static int ftp_ask_size(struct stream_t *stream,const char *filepath,uint64_t *size)
{
    struct ftp_response_t *ftp_response;
    char *sendbuffer = xmalloc(strlen(filepath) + 16);
    int status_code;
    
    snprintf(sendbuffer,BUFSIZE_1K,"SIZE %s\n",filepath);
    ftp_send_command(stream,sendbuffer);

    ftp_response = new_ftp_response_t();
    status_code = ftp_recv_response(stream,ftp_response);
    if(is_ftp_OK(status_code)) {
	char *p = (char *)ftp_response->lines->p;
	uint64_t filesize = 0;
	
	while(isdigit(*p)) p++; /* skip status code */
	while((*p) == ' ') p++; /* skip white space */
	while(isdigit(*p)) {
	    filesize *= 10;
	    filesize += *p - '0';
	    p++;
	}
	display(MSDL_DBG,"file size %d bytes\n",filesize);
	
	*size = filesize;
    }
    else {
	if(ftp_response->lines) {
	    display(MSDL_ERR,"%s",(char *)ftp_response->lines->p);
	}
	*size = 0;
    }

    free_ftp_response_t(ftp_response);
    free(sendbuffer);
    return status_code;
}



/*
 * send RETR request for downloading file.
 */
static int ftp_retr(struct stream_t *stream,const char *filepath)
{
    char *sendbuffer = xmalloc(strlen(filepath) + 16);
    struct ftp_response_t *ftp_response;
    int status_code;

    snprintf(sendbuffer,BUFSIZE_1K,"RETR %s\n",filepath);
    ftp_send_command(stream,sendbuffer);
    
    ftp_response = new_ftp_response_t();
    status_code = ftp_recv_response(stream,ftp_response);
    if(!is_ftp_OK(status_code)) {
	display(MSDL_ERR,"%s",(char *)ftp_response->lines->p);
    }

    free_ftp_response_t(ftp_response);
    free(sendbuffer);
    return status_code;
}



/*
 * starts ftp streaming(actually this is downlaoding).
 * 
 *    return value :   negative or 0  ... error
 *                                 1  ... success
 */
int ftp_streaming_start(struct stream_t *stream)
{
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    struct url_t *url = stream->url;
    char *connect_host = NULL;
    int connect_port = 0;

    int command_sock = 0;
    int data_wait_sock = 0;
    int data_sock = 0;
    
    int status_code = 0;
    uint64_t file_size = 0;

    
    stream_ctrl->status = streaming_handshaking;
    
    if(stream->dlopts->no_passive_ftp) {
	stream->stream_ctrl->ftp_ctrl->mode = ACTIVE_FTP;
    }
    
    if(url->port == 0) {
	url->port = FTP_PORT;
    }

    connect_host = url->hostname;
    connect_port = url->port;
    
    command_sock = server_connect(connect_host,connect_port);
    if(command_sock < 0) {
	goto failed;
    }
    
    /*
      stream_sock is CURRENT using socket, so now it's command_sock
      ( later changed to data_sock, as it will be active )
    */
    stream->sock = command_sock;
    stream_ctrl->ftp_ctrl->command_sock = command_sock;


    status_code = ftp_read_welcome(stream);
    if(!is_ftp_OK(status_code)) {
	goto failed;
    }

    
    /*
      USER, default is anonymous login.
    */
    status_code = ftp_user(stream,(url->username) ? url->username : "anonymous");
    

    /*
      PASS
    */
    status_code =ftp_pass(stream,(url->password) ? url->password : ftp_default_passwd);
    if(!is_ftp_OK(status_code)) {
	goto failed;
    }
    

    /*
      PASV (if not ACTIVE mode specified)
      
      this may fail, and in that case fall back to ACTIVE.
    */
    if(stream_ctrl->ftp_ctrl->mode == PASSIVE_FTP) {
	status_code = ftp_passive_mode(stream,connect_host,&data_sock);
	if((!is_ftp_OK(status_code)) || (data_sock <= 0)) {
	    stream_ctrl->ftp_ctrl->mode = ACTIVE_FTP;
	}
    }
    
    
    /*
      PORT
    */
    if(stream_ctrl->ftp_ctrl->mode == ACTIVE_FTP) {
	status_code = ftp_tell_port(stream,&data_wait_sock);
	if((!is_ftp_OK(status_code)) || (data_wait_sock <= 0)) {
	    goto failed;
	}
    }
    

    if(stream_ctrl->ftp_ctrl->mode == ACTIVE_FTP) {
	data_sock = accept_connection(data_wait_sock);
	if(data_sock < 0) {
	    display(MSDL_ERR,"could not establish data connection\n");
	    goto failed;
	}
    }
    
    
    /*
      TYPE
    */
    status_code = ftp_bin_mode(stream);
    

    /*
      SIZE
    */
    status_code = ftp_ask_size(stream,url->filepath,&file_size);
    if(file_size) {
	stream_ctrl->file_size = stream_ctrl->ftp_ctrl->file_size = file_size;
    }
    


    /*
      RETR
    */
    status_code = ftp_retr(stream,url->filepath);
    if(!is_ftp_OK(status_code)) {
	goto failed;
    }
    
    
    
    stream->stream_ctrl->protocol = FTP;
    
    stream_ctrl->ftp_ctrl->command_sock = command_sock; /* just to make sure */
    stream_ctrl->ftp_ctrl->data_wait_sock = data_wait_sock;
    stream_ctrl->ftp_ctrl->data_sock = data_sock;

    stream_ctrl->ftp_ctrl->down_size = 0;
    stream_ctrl->status = streaming_downloading;

    /* stream_sock is CURRENT using socket, so now it's changed to data_sock */
    stream->sock = data_sock;
    
    return 1;

  failed:
    return 0;
}



/*
 * read mms over http stream. filles buffer, and buffer size is 'size' 
 *
 *        ftp does not need special buffering using
 *        stream_ctrl->buffer, and write_buffer
 *        
 *  return value: bytes written to buffer.
 */
int ftp_streaming_read(struct stream_t *stream,
		       uint8_t *buffer, size_t buffer_size)
{
    struct ftp_ctrl_t *ftp_ctrl = stream->stream_ctrl->ftp_ctrl;
    size_t read_len;

    read_len = xrecv(ftp_ctrl->data_sock,buffer,buffer_size);
    if(read_len < 0) {
	perror("recv() error");
	return -1;
    }
    if(read_len == 0) {
	stream->stream_ctrl->status = streaming_finished;
    }

    
    ftp_ctrl->down_size += read_len;
    return read_len;
}



void ftp_streaming_close(struct stream_t *stream)
{
    /*
      FTP uses 2(or 3 in active) sockets, and they are closed in free_ftp_ctrl_t
    */
    ftp_send_command(stream,"QUIT\n"); /* to be nice to server. */
    
    free_ftp_ctrl_t(stream->stream_ctrl->ftp_ctrl);
    free_stream_ctrl_t(stream->stream_ctrl);
    free_stream_t(stream);
}



struct stream_t *ftp_streaming_init(struct url_t *url)
{
    struct stream_t *stream = new_stream_t();
    stream->url = url;
    stream->stream_ctrl = new_stream_ctrl_t();
    stream->stream_ctrl->ftp_ctrl = new_ftp_ctrl_t();
  
    stream->start = ftp_streaming_start;
    stream->read  = ftp_streaming_read;
    stream->close = ftp_streaming_close;

    return stream;
}


