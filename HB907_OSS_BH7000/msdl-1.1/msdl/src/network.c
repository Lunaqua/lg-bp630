/***********************************************************************
 *    network.c: network related utility functions
 ***********************************************************************
 * Copyright (C) 2007 metro <me_t_ro@yahoo.com>
 *
 * This file is part of msdl, media stream downloader
 *
 * network-unrelated functions goes to msdllib.c
 *
 * struct stream_t is a stream describer, and 
 * stream_ctrl is stream controller, actually works as a
 * network buffer and wrapper for all supported protocols.
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

#include <errno.h>
#include <ctype.h>
#include <fcntl.h>
#include <sys/time.h>
#include <sys/types.h>
#include <sys/select.h>

#include <netdb.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#include "msdl.h"
#include "msdllib.h"
#include "display.h"
#include "network.h"



/*
 * setup stream_t
 */
struct stream_t *new_stream_t(void)
{
    struct stream_t *st = (struct stream_t *)xmalloc(sizeof(struct stream_t));
    return st;
}



/*
 * free stream_t
 */
void free_stream_t(struct stream_t *st)
{
    if(!st) return;
    free(st);
}



/*
 * setup buffer, setup URL.
 */
struct stream_ctrl_t *new_stream_ctrl_t(void)
{
  
    struct stream_ctrl_t *sc =
	(struct stream_ctrl_t *)xmalloc(sizeof(struct stream_ctrl_t));

    sc->buffer = xmalloc(BUF_SIZE);
    sc->buffer_size = BUF_SIZE;

    sc->write_buffer = xmalloc(BUF_SIZE);
    sc->write_buffer_size = BUF_SIZE;
    
    return sc;
}



/*
 * free stream_ctrl;
 */
void free_stream_ctrl_t(struct stream_ctrl_t *sc)
{
    if(!sc) return;

    /* if(sc->url) free_url_t(sc->url); not malloc()ed in new_stream_ctrl_t,
       thus, free this outside free_stream_ctrl_t().*/
    if(sc->buffer) free(sc->buffer);
    if(sc->write_buffer) free(sc->write_buffer);
  
    free(sc);
}






/*
 *  unescape url string.
 *  such as : change '%41' to 'A'.
 */
void url_unescape_string(char *dst,char *src)
{
    uint8_t ch,ch1,ch2;
    int i,len;
  
    len = strlen(src);
  
    for(i = 0; i < len; i++) {
	ch = src[i];
	if(ch == '%' && (i < len - 2)) { /* need two more chars after '%' */
	    ch1 = toupper(src[i+1]);
	    ch2 = toupper(src[i+2]); /* uppercase chars */
      
	    if((('0' <= ch1 && ch1 <= '9') || ('A' <= ch1 && ch1 <= 'F')) && 
	       (('0' <= ch2 && ch2 <= '9') || ('A' <= ch2 && ch2 <= 'F'))) {
		ch1 = ('0' <= ch1 && ch1 <= '9') ? ch1 - '0' : ch1 - 'A';
		ch2 = ('0' <= ch2 && ch2 <= '9') ? ch2 - '0' : ch2 - 'A';
		ch = (ch1 << 4) | ch2;
		i += 2;
	    }
	}
	*dst++ = ch;
    }
    *dst++='\0';
}



/*
 * return true if 'c' is valid url character
 */
inline int url_valid_char(int c)
{
    return (isalpha(c) ||
	    isdigit(c) ||
	    c == '_' || c == '%' || c == '+' ||
	    c == '-' || c == '?' || c == ':' ||
	    c == '.' || c == '/' || c == '~' ||
	    c == '-' || c == '=' || c == '&');
}




/*
 * set (url_t)->protocol_type.
 * sets UNKNONW_PROTOCOL if protocol string not supported.
 */
int set_protocol_type(char *protocol,struct url_t *u)
{
    if(!strcasecmp(protocol,"mms") || 
       !strcasecmp(protocol,"mmst")) {
	u->protocol_type = MMST;
    }
    else if(!strcasecmp(protocol,"mmsh")) {
	u->protocol_type = MMSH;
    }
    else if(!strcasecmp(protocol,"http")) {
	u->protocol_type = HTTP;
    }
    else if(!strcasecmp(protocol,"rtsp")) {
	u->protocol_type = RTSP;
    }
    else if(!strcasecmp(protocol,"ftp")) {
	u->protocol_type = FTP;
    }
    else {
	u->protocol_type = UNKNOWN_PROTOCOL;
    }
  
    return u->protocol_type;
}




/*
 * setup various URL informations.
 * allocate struct url_t 
 */
struct url_t *new_url_t(char *url)
{
    struct url_t *u;
    char *p;
    int len;
    int valid = 0; /* default invalid */

    /*
      url is malloc() ed.
      protocol is malloc() ed.
      hostname is malloc() ed.
      file is NOT.
    */
  
    /* return if not URL */
    if(!strstr(url,"://")) return NULL;
  
    u = (struct url_t *)xmalloc(sizeof(struct url_t));
    u->url = strdup(url);
    u->file = strstr(u->url,"://") + 3;
    u->port = 0;
  
    if(u->file) {
	/* copy protocol name. */
	len = u->file - u->url - 3;
	u->protocol = (char *)xmalloc(len + 1);
	strncpy(u->protocol,u->url,len);
	u->protocol[len] = '\0';
  
	/* copy file name. */
	p = strchr(u->file,'/');
	if(p) {
	    u->filepath = p;    /* filepath: used in mmsh req. */
	    len = p - u->file;
	    u->hostname = (char *)xmalloc(len + 1);
	    strncpy(u->hostname,u->file,len);
	    u->hostname[len] = '\0';
      
	    /* port specification after hostname ( http://www.foo.com:8080/bar/) */
	    if((p = strchr(u->hostname,':')) != NULL) {
		u->port = atoi(p+1);
		*p = '\0';
	    }
      
	    /* sets u->protocol_type */
	    set_protocol_type(u->protocol,u);
      
	    /* everything done, valid url. */
	    valid = 1;
	}
    }  
  
    u->username = NULL;
    u->password = NULL;
    u->http_proxy = NULL;
  
    if(!valid) {
	free_url_t(u);
	u = NULL;
	/* invalid URL. */
    }
  
    return u;
}



void free_url_t(struct url_t *u)
{
    if(u->url) free(u->url);
    if(u->protocol) free(u->protocol);
    if(u->hostname) free(u->hostname);
    /* u->file is not malloc() ed, just points to url. */
    /* u->filepath is not malloc()ed either            */
    if(u->username) free(u->username);
    if(u->password) free(u->password);
    if(u->http_proxy) free(u->http_proxy);
    free(u);
}



/*
 * connect to 'servername' with 'port'.
 *           return value :    socket number ... success
 *                             -1            ... error
 */
int server_connect(const char *servername,const int port)
{
    
    int sock_server = 0;
    int ret;
    
    char hoststr[INET6_ADDRSTRLEN + 4]; /* IPv4 / IPv6 dual */
    char portstr[8];
    fd_set set;
    struct addrinfo hints,*result = NULL;
    
    struct timeval tv;    
    int try_count = 0;
    

    if(!servername) {
	goto failed;
    }
    
    if(0 <= port && port <= 0xffff) { /* valid params  */
	snprintf(portstr,7,"%d",port);
    }
    else {
	display(MSDL_ERR,"port number %d not valid",port);
	goto failed;
    }
    
    memset(&hints,0,sizeof(struct addrinfo));
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = SOCK_STREAM;
    
    ret = getaddrinfo(servername,portstr,&hints,&result);
    if(ret != 0) {
	display(MSDL_ERR,"Host [ %s ] not found. (%s)\n",servername,gai_strerror(ret));
	goto failed;
    }
    

    if(result->ai_family == AF_INET6) {
	inet_ntop(result->ai_family,
		  &((struct sockaddr_in6 *)result->ai_addr)->sin6_addr,
		  hoststr,
		  sizeof(hoststr));
    }
    else { /* default IPv4 */
	inet_ntop(result->ai_family,
		  &((struct sockaddr_in *)result->ai_addr)->sin_addr,
		  hoststr,
		  sizeof(hoststr));
    }
    
    /* display host:ports */
    display(MSDL_NOR,"Host: [ %s:%s ]  ",hoststr,portstr);
    
    
    sock_server = socket(result->ai_family,result->ai_socktype,result->ai_protocol);
  
    if(sock_server == -1) {
	display(MSDL_ERR,"socket() error");
	return -1;
    }
    
    /* Turn the socket to non-blocking socket, so we can timeout. */
    fcntl(sock_server,F_SETFL,fcntl(sock_server,F_GETFL) | O_NONBLOCK);
    
    if(connect(sock_server,result->ai_addr,result->ai_addrlen) == -1) {
	/* failed not because it was non-blocking */
	if(errno != EINPROGRESS) {
	    display(MSDL_ERR,"connect() failed");
	    goto failed;
	}
    }
  
    for(;;) {
    
	/* init */
	tv.tv_sec = 3;
	tv.tv_usec = 0;
	FD_ZERO(&set);
	FD_SET(sock_server,&set);
	
	ret = select(sock_server+1,NULL,&set,NULL,&tv);

	if(ret < 0) { /* select failed! */
	    display(MSDL_ERR,"select() failed");
	    goto failed;
	}
	else if(ret > 0) break;
	else if(try_count > 5) { /* 12 sec */
	    display(MSDL_ERR,"timeout!\n");
	    goto failed;
	}
    
	display(MSDL_NOR,".");
	try_count++;
    }
  
    /* Turn the socket to blocking, as we don't need it. */
    fcntl(sock_server, F_SETFL, fcntl(sock_server,F_GETFL) & ~O_NONBLOCK);
  
    display(MSDL_NOR,"  connected!\n");
    
    freeaddrinfo(result);
    return sock_server;
    
    /* failure */
  failed:
    if(sock_server) close(sock_server);
    if(result) freeaddrinfo(result);
    return -1;
}
  

enum {
    GET_DATA_TIMEOUT = 180, /* 3 Minutes */
    XRECV_TIMEOUT = 180,
};


/*
 * prepare listning socket opening 'port'.
 * protocol family can be specified by 'family'
 *
 *           return value :       sock ... success
 *                                  -1 ... failure
 */
int waiting_socket(int family,int port)
{
    int sock;
    int ret;
    char portstr[8];
    struct addrinfo hints,*result = NULL;
    
    if(port < 0 || 0xffff < port) {
	display(MSDL_ERR,"internal: invalid port number\n");
	goto failed;
    }
    
    memset(portstr,0,8);
    snprintf(portstr,7,"%d",port);
    

    memset(&hints,0,sizeof(hints));
    hints.ai_family = family;
    hints.ai_socktype = SOCK_STREAM;
    hints.ai_flags = AI_PASSIVE;

    
    ret = getaddrinfo(NULL,portstr,&hints,&result);
    if(ret != 0) {
	perror("getaddrinfo() failed");
	goto failed;
    }

    sock = socket(result->ai_family,result->ai_socktype,result->ai_protocol);
    if(sock < 0) {
	perror("socket() failed");
	goto failed;
    }
    

    ret = bind(sock,(struct sockaddr *)result->ai_addr,result->ai_addrlen);
    if(ret < 0) {
	perror("bind() failed");
	goto failed;
    }
    

    ret = listen(sock,1);
    if(ret < 0) {
	perror("listen() failed");
	goto failed;
    }

    freeaddrinfo(result);
    return sock;
    
  failed:
    if(result) freeaddrinfo(result);
    return -1;
}



/*
 * accept connectoin from client.
 * return value :         sock .. success
 *                          -1 .. failure
 */
int accept_connection(int wait_sock)
{
    struct sockaddr_storage ss;
    socklen_t sslen;
    int sock;
    int ret;
    
    ret = sock_check_data(wait_sock,15);
    if(ret <= 0) {
	return -1;
    }
    
    sslen = sizeof(ss);
    sock = accept(wait_sock,(struct sockaddr *)&ss,&sslen);
    if(sock == -1) {
	perror("accept() failed");
	return -1;
    }

    return sock;
}



/*
 * this is wrapper for recv, and has timeout.
 * return value:            length --> success
 *                              -1 --> error/timeout
 *                               0 --> eof
 */
int xrecv(int sock,void *buf,size_t count)
{
    fd_set fds;
    struct timeval tv;
    int retval;
  
    tv.tv_sec  = XRECV_TIMEOUT;
    tv.tv_usec = 0;
  
    FD_ZERO(&fds);
    FD_SET(sock,&fds);

    retval = select(sock + 1,&fds,NULL,NULL,&tv);
    if(retval == -1) {  /* select() Error (system call error) */
	perror("select() failed\n");
	goto failed;
    }
    else if(!retval) {  /* No data arrived (error)*/
	display(MSDL_ERR,"timeout!! could not receive data\n");
	goto failed;
    }

    retval = recv(sock,buf,count,0);
  
    if(retval < 0) {       /* recv()   Error (system call error) */
	perror("recv() failed\n");
	goto failed;
    }

    return retval;
  
  failed:
    return -1;
}



int xsend(int sock,void *buf,size_t count)
{
    return (send(sock,buf,count,0));
}


int sock_check_data(int sock,int timeout)
{

    fd_set fds;
    struct timeval tv;
    int ret;
    
    FD_ZERO(&fds);
    FD_SET(sock,&fds);

    tv.tv_sec = timeout;
    tv.tv_usec = 0;

    ret = select(sock + 1,&fds,NULL,NULL,&tv);

    if(ret == -1) {
	perror("stream_check_data: select() failed");
    }
    
    return ret;
}


int stream_check_data(struct stream_t *stream,int timeout)
{
    if(stream->stream_ctrl->data_len) {
	return 1;
    }
    
    return sock_check_data(stream->sock,timeout);
}



/*
 * recv count bytes of data from sock to buf.
 * this is only used when count bytes of data are
 * supposed to come from sock.
 * this function must be used when 'count' bytes are guaranteed
 * to be received.
 *          return value: length --> success.
 *                        0      --> eof
 *                        -1     --> timeout or error (fatal)
 */
int get_data(int sock,void *buf,size_t count)
{
    int len;
    size_t total = 0;
  
    while(total < count) { /* more data to recv. */
    
	len = xrecv(sock,(uint8_t *)buf + total,count - total);
    
	if(len < 0) {       /* Error. timeout, syscall error */
	    goto failed;
	}
	else if(len == 0) { /* EOF */
	    display(MSDL_ERR,"met EOF when %d bytes are to come\n",count);
	    goto meteof;
	}
    
	total += len;
    }
  
    return total;
  
  meteof:
    return 0;
  
  failed:
    return -1;
}



/*
 * read 'size' bytes from *resources*.
 *
 *       *resources* are : [in this order]
 *                    1. stream_ctrl->buffer (data which came with http header)
 *                    2. stream->sock   (network)
 *
 *               return value : size which read.
 *                              -1 if get_data failed.
 */
int read_data(struct stream_t *stream, void *buffer, size_t size)
{
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    int len; /* how many bytes are stored in 'buffer' */
    int ret;

    /*printf("read_data size = %d   stream_ctrl->data_len = %d,"
      " stream_ctrl->buffer_pos = %d\n",
      size,stream_ctrl->data_len, stream_ctrl->buffer_pos);*/
  
    len = 0;
    if(stream_ctrl->data_len) {
	/* there is a data to read in stream_ctrl->buffer */
    
	len = (size < stream_ctrl->data_len)
	    ? size : stream_ctrl->data_len; /* smaller */
    
	memcpy((uint8_t *)buffer,stream_ctrl->buffer + stream_ctrl->buffer_pos,len);
    
	stream_ctrl->buffer_pos += len;
	stream_ctrl->data_len -= len;
    
	if(stream_ctrl->data_len == 0) {
	    stream_ctrl->buffer_pos = 0;
	}
    }
  
    if(len < size) {
	ret = get_data(stream->sock,(uint8_t *)buffer + len, size - len);
	if(ret < 0) { /* get_data mets timeout/error */
	    return -1;
	}
	else {
	    len += ret;
	}
    }
  
    return len;
}



/*
 * different from read_data, this function's 3ed argument is 
 * max byte to read from stream.
 */
int recv_data(struct stream_t *stream,void *buffer,size_t max)
{
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    int len; /* how many bytes are stored in 'buffer' */
    int ret;
  
    if((len = stream_ctrl->data_len)) {
	/* there is a data to read in stream_ctrl->buffer */
    
	len = (max < len) ? max : len; /* smaller */
    
	memcpy((uint8_t *)buffer,stream_ctrl->buffer + stream_ctrl->buffer_pos,len);
    
	stream_ctrl->buffer_pos += len;
	stream_ctrl->data_len -= len;
    
	if(stream_ctrl->data_len == 0) {
	    stream_ctrl->buffer_pos = 0;
	}
    }
  
    /* still network stream can be read. */
    if(len < max) {
	ret = xrecv(stream->sock,(uint8_t *)buffer + len,max - len);
	if(ret < 0) {
	    return -1;
	}
	else {
	    len += ret;
	}
    }
    return len;
}



/*
 * push data to stream_ctrl->buffer so that the data can be read later
 */
int stream_data_push_back(struct stream_t *stream,void *buffer,int size)
{
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
  
    if(stream_ctrl->data_len) {
	memmove(stream_ctrl->buffer + stream_ctrl->buffer_pos + size,
		stream_ctrl->buffer + stream_ctrl->buffer_pos,
		stream_ctrl->data_len);

    }
    memcpy(stream_ctrl->buffer + stream_ctrl->buffer_pos,buffer,size);
    stream_ctrl->data_len += size;  
  
    return (stream_ctrl->data_len);
}


