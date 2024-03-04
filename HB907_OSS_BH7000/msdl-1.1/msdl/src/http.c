/***********************************************************************
 *    http.c: for downloading via http (Hyper Text Transfer Protocol)
 ***********************************************************************
 * Copyright (C) 2007 metro <me_t_ro@yahoo.com>
 *
 * This file is part of msdl, media stream downloader
 *
 * This file is based on http implementation of mplayer.
 * mplayer's http implementation was very nice.
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

#include "msdllib.h"
#include "display.h"
#include "network.h"
#include "http.h"


const char http_useragent[] = "User-Agent: MSDL";



/*
 * new_http_header : create new HTTP header
 */
struct http_header_t *new_http_header(void)
{
    struct http_header_t *hdr;
    hdr = (struct http_header_t *)xmalloc(sizeof(struct http_header_t));
    return hdr;
}



/*
 * free HTTP header
 */
void free_http_header(struct http_header_t *h)
{
    struct http_field_t *field, *prev;
  
    if(h == NULL) return; /* http_hdr is not malloc()ed yet. */
  
    if(h->protocol) free(h->protocol);
    if(h->method) free(h->method);
    if(h->uri) free(h->uri);
    if(h->reason_phrase) free(h->reason_phrase);
    if(h->field_search) free(h->field_search);
    if(h->buffer) free(h->buffer);
    field = h->first_field;

    for(field = h->first_field; field ; ) {
	if(field->field_name)
	    free(field->field_name);
	prev = field;
	field = field->next;
	free(prev);
    }
    free(h);
}



/*
 * returns first filed with 'field name'.
 * http_get_next_field will get next field with 'filed_name'
 */
char *http_get_field(struct http_header_t *http_hdr, const char *field_name)
{
    if(!http_hdr || !field_name) return NULL;
  
    http_hdr->field_search_pos = http_hdr->first_field;
    http_hdr->field_search = (char *)xrealloc(http_hdr->field_search,
					      strlen(field_name) + 1);
    /* copy field name first */
    
    strcpy(http_hdr->field_search, field_name);

    /* get first field value with this field_name. */
    return http_get_next_field(http_hdr);
}



/*
 * return field string after "http_hdr->field_search".
 *        NULL if not found.
 */
char *http_get_next_field(struct http_header_t *http_hdr)
{
    char *ptr;
    struct http_field_t *field;

    if(!http_hdr) return NULL;
    field = http_hdr->field_search_pos;
  
    while(field) {
	ptr = strstr(field->field_name,":");
	if(ptr == NULL) return NULL; /* the header is not valid... */
	if(!strncasecmp(field->field_name,http_hdr->field_search,
			ptr - field->field_name)) { /* found field!!!! */
	    ptr++; /* skip ':' */
	    while(*ptr == ' ') ptr++; /* skip %20 */
	    http_hdr->field_search_pos = field->next; /* points to next field */
	    return ptr; /* return the string without filed name!! */
	}
	field = field->next;
    }
    return NULL; /* NOT FOUND */
}



/*
 * http_set_field : make new field and attach it to last_field->next.
 */
void http_set_field(struct http_header_t *http_hdr, const char *field_name)
{
    struct http_field_t *new_field;
  
    if(http_hdr == NULL || field_name == NULL) return;
  
    new_field = xmalloc(sizeof(struct http_field_t));

    new_field->next = NULL;
    new_field->field_name = xmalloc(strlen(field_name) + 1);
    strcpy(new_field->field_name, field_name);
  
    if(http_hdr->last_field == NULL) {
	http_hdr->first_field = new_field; /* this was first filed!! */
    }
    else {
	http_hdr->last_field->next = new_field; /* attach to last    */
    }
    http_hdr->last_field = new_field;
    http_hdr->field_nb++;
}



/*
 * send 'http_hdr' to network. it has to be built by build_request funcs
 *                return value:  what xsend returned
 */
int http_send_header(struct stream_t *stream,
		     struct http_header_t *http_hdr)
{
    int ret;
  
    /* dbg */
    display(MSDL_DBG,"SEND http header ------------>\n"
	    "%s\n--(%d bytes)---------------<\n",
	    http_hdr->buffer,(int)http_hdr->buffer_len);
  
    ret = xsend(stream->sock,http_hdr->buffer,http_hdr->buffer_len);
    return ret;
}



/*
 * receive http message from stream->sock.
 *      header is guraranteed to be complete after this function,
 *      body should not be complete.
 *      so search Content-length and get all the body later.
 *               return status code : success
 *                               -1 : failure
 */
int http_recv_header(struct stream_t *stream, struct http_header_t *http_hdr)
{
    int ret,i,total;
  
    http_hdr->buffer_len = 0;
    http_hdr->buffer = NULL;
    total = 0;
  
    do { /* get http reply */
	http_hdr->buffer_len += BUFSIZE_1K;
	http_hdr->buffer = (char *)xrealloc(http_hdr->buffer,
					    http_hdr->buffer_len + 1);
	
	/* THIS IS NECESSARY!!! 
	   because it might include \r\n\r\n in realloc()ed buffer
	   (and acrually it does sometime!!)
	*/
	memset(http_hdr->buffer + total,0,http_hdr->buffer_len + 1 - total);
	
	i = recv_data(stream,http_hdr->buffer + total,BUFSIZE_1K);
	if(i <= 0) {
	    display(MSDL_ERR,"xrecv error: xrecv() returned %d\n",i);
	    goto failed;
	}
	total += i;
    
    } while(!http_is_entire_header(http_hdr));
  
    /* http_hdr->buffer_size is length in buffer, not the malloc()ed size.  */
    http_hdr->buffer_len = total;
    http_hdr->buffer[total] = '\0';

  
    ret = http_response_parse(http_hdr);
    if(ret < 0) {
	display(MSDL_ERR,"response HTTP header parse failed\n");
	goto failed;
    }
  
    /* push body back !!! */
    if(http_hdr->body_len) {
	stream_data_push_back(stream,http_hdr->body,http_hdr->body_len);
    }
  
    /* http_hdr->buffer *ONLY* contains header, no body follows */
    memset(http_hdr->body,0,http_hdr->body_len);

    /* dbg */
    display(MSDL_DBG,"Resopnse header===========\n"
	    "%s\n"
	    "--(%d bytes)-------------------\n",
	    http_hdr->buffer,(int)http_hdr->buffer_len);
    

    /* success */
    return http_hdr->status_code;
    
  failed:
    return -1;
}



/* table for base 64 */
const char b64[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

/*
 * base64_encode : base 64 encode.
 *
 * enc:    string to base64 encode.
 * enclen: length of enc
 * out:    output base64 encoded result.
 * outmax: length of out (buffer length)
 *
 * copied from Mplayer/stream/http.c
 */
int base64_encode(const void *enc,int enclen,char *out,int outsize)
{
    unsigned char *encbuf;
    int outlen;
    unsigned int bits;
    unsigned int shift;
 
    encbuf = (unsigned char*)enc;
    outlen = 0;
    bits = 0;
    shift = 0;
    outsize &= ~3;
  
    while( outlen < outsize ) {
	if( enclen>0 ) {
	    /* Shift in byte */
	    bits <<= 8;
	    bits |= *encbuf;
	    shift += 8;
	    /* Next byte     */
	    encbuf++;
	    enclen--;
	} else if( shift>0 ) {
	    /* Pad last bits to 6 bits - will end next loop */
	    bits <<= 6 - shift;
	    shift = 6;
	} else {
	    /*
	      As per RFC 2045, section 6.8, 
	      pad output as necessary: 0 to 2 '=' chars.
	    */
	    while( outlen & 3 ){
		*out++ = '=';
		outlen++;
	    }
  
	    return outlen;
	}
 
	/* Encode 6 bit segments */
	while( shift>=6 ) {
	    shift -= 6;
	    *out = b64[ (bits >> shift) & 0x3F ];
	    out++;
	    outlen++;
	}
    }
  
    /* output overflow */
    return -1;
}



/*
 * add BASIC authentication string to header.
 *                return value: 0
 */
int http_add_basic_authentication(struct http_header_t *http_hdr,
				  const char *username, const char *password)
{
    char *auth = NULL, *user_pass = NULL, *b64_user_pass = NULL;
    int encoded_len ,pass_len = 0, out_len;
  
    int res = -1;
    /* username cannot be NULL. */
    if(http_hdr == NULL || username == NULL)  return -1;
  
    if(password != NULL) {
	pass_len = strlen(password);
    }

    user_pass = (char *)xmalloc(strlen(username) + pass_len + 2);
    sprintf(user_pass, "%s:%s",username,(password == NULL) ? "" : password);
  
    /* base64 encode with at least 33% more data than the original size. */
    encoded_len = strlen(user_pass) * 2;
    b64_user_pass = (char *)xmalloc(encoded_len);
  
    out_len = base64_encode(user_pass,strlen(user_pass),
			    b64_user_pass,encoded_len);
    if(out_len < 0) {
	fatal_error("Base64 output overflow!!\n");
    }
  
    b64_user_pass[out_len] = '\0';
    auth = xmalloc(encoded_len + 22);
  
    sprintf(auth,"Authorization: Basic %s",b64_user_pass);
    http_set_field(http_hdr,auth);
    res = 0;
 
    free(user_pass);
    free(b64_user_pass);
    free(auth);
    
    return res;
}



int http_authenticate(struct http_header_t *http_hdr)
{
    char *auth;
  
    auth = http_get_field(http_hdr,"WWW-Authenticate");
    if(auth) { /* found!!!  */
	char *auth_space;
	auth_space = strstr(auth,"realm=");
	if(auth_space != NULL) auth_space += 6;
	display(MSDL_NOR,"authentication required %s!!\n",auth_space);
    }
    else {     /* not found */
	display(MSDL_NOR,"authentication required!!\n");
    }
  
    return 0;
}



void http_set_uri(struct http_header_t *http_hdr,const char *uri)
{
    if(http_hdr == NULL || uri == NULL) return;

    http_hdr->uri = strdup(uri);
}



void http_set_method(struct http_header_t *http_hdr, const char *method)
{
    if(http_hdr == NULL || method == NULL) return;

    http_hdr->method = strdup(method);
}



char *http_request_get(struct http_header_t *http_hdr)
{
    http_set_method(http_hdr,"GET");
  
    return (http_build_request(http_hdr));
}



char *http_request_post(struct http_header_t *http_hdr)
{
    http_set_method(http_hdr,"POST");
  
    return (http_build_request(http_hdr));
}



/*
 * make complete http_header_t.
 * the request string goes to http_hdr->buffer
 *             return value:   http_hdr->buffer ( built request )
 */
char *http_build_request(struct http_header_t *http_hdr)
{
    char *ptr, *uri = NULL;
    int len;
    struct http_field_t *field;
  

    if(http_hdr->uri == NULL) {
	http_set_uri(http_hdr,"/");
    }
    else {
	uri = strdup(http_hdr->uri);
    }
  
  
    /*
     * culculate the request length.
     */

    /* method line */
    len = strlen(http_hdr->method) + strlen(uri) + 12;
    /* fields */
    field = http_hdr->first_field;
    while(field) {
	len += strlen(field->field_name) + 2;
	field = field->next;
    }
    /* CRLF */
    len += 2;
    /* request body */
    if(http_hdr->body) {
	len += http_hdr->body_len;
    }
  

    /*
     * free the buffer which used 4 last request.
     */
    if(http_hdr->buffer) {
	free(http_hdr->buffer);
	http_hdr->buffer = NULL;
    }
  
    http_hdr->buffer_len = len;
    http_hdr->buffer = xmalloc(len + 1); /* 1 for '\0' */
  
  
    /* 
     * build the request
     */
    ptr = http_hdr->buffer;
    /* method line */
    ptr += sprintf(ptr,"%s %s HTTP/1.%d\r\n",
		   http_hdr->method,uri,http_hdr->http_minor_version);
    field = http_hdr->first_field;
    /* fields */
    while(field != NULL) {
	ptr += sprintf(ptr,"%s\r\n",field->field_name);
	field = field->next;
    }
    ptr += sprintf(ptr,"\r\n");
  
    if(http_hdr->body != NULL) {
	memcpy(ptr,http_hdr->body,http_hdr->body_len);
    }
    if(uri) free(uri);
  
    return http_hdr->buffer;
}




/*
 * concatinate http_hdr->buffer and response.
 * return value is new buffer size. (not counting NULL char)
 */
int http_response_append(struct http_header_t *http_hdr,
			 char *response,int len)
{
    if(http_hdr == NULL || response == NULL || len < 0) return 0;
    
    /* *** caution *** */
    /* http_hdr->buffer_size does NOT include '\0' at the end. */
  
    /*
      if(http_hdr->buffer_size + len + 1 > HDR_SIZE_MAX) { // +1 for '\0'
      display(MSDL_ERR,"%d maybe recv failed\n",http_hdr->buffer_size);
      return -1;
      }
    */
    http_hdr->buffer = (char *)xrealloc(http_hdr->buffer,
					http_hdr->buffer_len + len + 1);
    /* concat buffer. */
    memcpy(http_hdr->buffer + http_hdr->buffer_len, response, len);
    http_hdr->buffer_len += len;
    http_hdr->buffer[http_hdr->buffer_len] = '\0'; /* terminate. */

    return http_hdr->buffer_len;
}



/*
 * judges if http_hdr is complete HTTP header, or still needs some parts.
 *    return value :       0 ... NOT complete
 *                         1 ... COMPLETE!!
 */
int http_is_entire_header(struct http_header_t *http_hdr)
{
    if(http_hdr == NULL) return 0; /* unlikely, but error */
  
    if(http_hdr->buffer == NULL) return 0;   /* nothing received. */
    
    if(strstr(http_hdr->buffer,"\r\n\r\n") || 
       strstr(http_hdr->buffer,"\n\n")) {
	return 1;
    }
    return 0;
}



/*
 * http_response_parse :  parse http response header 
 * make http_hdr from raw data in http_hdr->buffer
 *
 *    return value :        -1 ... ERROR
 *                           0 ... already parsed.
 *                           1 ... success.
 */
int http_response_parse(struct http_header_t *http_hdr)
{
    char *hdr_ptr, *ptr;
    char *field = NULL;
  
    int pos_hdr_sep, hdr_sep_len;
    size_t len;
  
    if(http_hdr == NULL) return -1;
    if(http_hdr->is_parsed) return 0; /* already parsed. */
  
  
    /*
      1. get protocol.
    */
    hdr_ptr = strstr(http_hdr->buffer, " ");
    if(hdr_ptr == NULL) {
	display(MSDL_ERR,"Malformed answer : No %20 separator\n");
	return -1;
    }
  
    len = hdr_ptr - http_hdr->buffer;
    http_hdr->protocol = xmalloc(len + 1);
    strncpy(http_hdr->protocol,http_hdr->buffer,len);
    http_hdr->protocol[len] = '\0';
  
  
    /* get (useless) minor version here. */
    if(!strncasecmp(http_hdr->protocol,"HTTP",4)) {
	if(sscanf(http_hdr->protocol+5,"1.%d",
		  &(http_hdr->http_minor_version)) != 1)
	    display(MSDL_ERR,"Malformed answer: http minor version unsepcified\n");
	/* actually, lack of HTTP version is not fatal AT ALL. */
    }

    /*
      2. get status code
    */
    if(sscanf(++hdr_ptr,"%d",&(http_hdr->status_code)) != 1) {
	display(MSDL_ERR,"Malformed answer : No http status code!!\n");
	return -1;
    } 
  
    hdr_ptr += 4; /* "[0-9][0-9][0-9] " */
  
    /*
      3. get reason phrase
    */
    ptr = strstr(hdr_ptr,"\n");
    if(ptr == NULL) {
	display(MSDL_ERR,"Malformed answer : unable to get reason_phrase\n");
	return -1;
    }
    len = ptr - hdr_ptr; /* len of reason phrase. */
  
    http_hdr->reason_phrase = xmalloc(len + 1);
    strncpy(http_hdr->reason_phrase,hdr_ptr,len);
    if(http_hdr->reason_phrase[len - 1] == '\r') { /* M$ style newline! */
	len --; /* we don't need \r. */
    }
    http_hdr->reason_phrase[len] = '\0'; /* terminate string */

    hdr_sep_len = 4; /* header separator length */
    ptr = strstr(http_hdr->buffer,"\r\n\r\n");
    if(!ptr) {
	ptr = strstr(http_hdr->buffer,"\n\n");
	if(!ptr) {
	    display(MSDL_ERR,"Header may be incomplete!\n");
	    return -1;
	}
	hdr_sep_len = 2;
    }
    pos_hdr_sep = ptr - http_hdr->buffer;

    /* points to first line after method line. */
    hdr_ptr = strstr(http_hdr->buffer,"\n") + 1;

  
    /* get all fields. they are separated by '\n' */
    do {
	ptr = hdr_ptr;
	while(*ptr != '\r' && *ptr != '\n') ptr++;
	len  = ptr - hdr_ptr;

	if(len == 0) break; /* met \n\n --> end of header! */
    
	field = (char *)xrealloc(field,len + 1); /* +1 for '\0' */
	strncpy(field,hdr_ptr,len);
	field[len] = '\0';
    
	http_set_field(http_hdr,field);
	hdr_ptr = ptr + ((*ptr == '\r') ? 2 : 1); /* points to next line */
    } while(hdr_ptr < (http_hdr->buffer + pos_hdr_sep));
  
    if(field) {
	free(field);
    }

    if(pos_hdr_sep + hdr_sep_len < http_hdr->buffer_len) {
	/* response has data within. --> store data !! or data will be lost! */
	http_hdr->body = http_hdr->buffer + pos_hdr_sep + hdr_sep_len;
	/* now its pointing to data in buffer. */
	http_hdr->body_len = http_hdr->buffer_len - (pos_hdr_sep + hdr_sep_len);
    }
  
    http_hdr->is_parsed = 1;
  
    return 1;
}




struct http_header_t *http_request(struct stream_t *stream,
				   struct http_header_t *http_hdr)
{
    struct url_t *url = NULL;
  
    char str[BUFSIZE_1K];

    if(stream == NULL || http_hdr == NULL) return NULL;
    
    url = stream->url;
    if(url == NULL) return NULL;

  
    http_set_field(http_hdr, "Accept: */*");
    http_set_field(http_hdr, http_useragent);
  
    http_add_basic_authentication(http_hdr,url->username,url->password);
  
  
    if(url->http_proxy) {
	http_set_uri(http_hdr,url->url);
    }
    else {
	http_set_uri(http_hdr,url->filepath);
    }
  
    snprintf(str,BUFSIZE_1K,"Host: %.220s:%d",url->hostname,url->port);
    http_set_field(http_hdr,str);
  
  
    http_set_field(http_hdr,"Connection: Close");
  
    return http_hdr;
}





int http_parse_response(struct http_ctrl_t *http_ctrl,
			struct http_header_t *http_hdr)
{
    char *content_length = NULL;
  
    switch(http_hdr->status_code) {
    case 200:
	/* OK */
	break;

    case 401:
	/* print auth requirement string */
	http_authenticate(http_hdr);
	return http_hdr->status_code;

    default:
	/* other error */
	return -1;
    }
  
    content_length = http_get_field(http_hdr, "Content-Length");
    if(content_length) {
	http_ctrl->content_length = atoi(content_length);
    }
    else {
	http_ctrl->content_length = 0;
    }
  
    return http_hdr->status_code;
}



/*
 * starts mmst streaming(actually this is downlaoding).
 * 
 *    return value :   negative or 0  ... error
 *                                 1  ... success
 */
int http_streaming_start(struct stream_t *stream)
{
    struct stream_ctrl_t *stream_ctrl = stream->stream_ctrl;
    struct http_ctrl_t *http_ctrl = stream_ctrl->http_ctrl;
    struct url_t *url = stream->url;
    struct http_header_t *http_hdr = NULL;
    char *connect_host;
    int connect_port;
  
    int sock;
    int ret;
  
    stream_ctrl->status = streaming_handshaking;
    sock = 0;

    if(url->port == 0) {
	url->port = HTTP_PORT;
    }


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
    
    sock = server_connect(connect_host,connect_port);
    if(sock < 0) {
	goto failed;
    }
    stream->sock = sock;

    http_hdr = new_http_header();
    http_request(stream,http_hdr);
    http_request_get(http_hdr);
    ret = http_send_header(stream,http_hdr);
    free_http_header(http_hdr);
    http_hdr = NULL;
    
    http_hdr = new_http_header();
    http_recv_header(stream,http_hdr);
  
    ret = http_parse_response(http_ctrl,http_hdr);
    if(ret < 0) {
	display(MSDL_ERR,"response HTTP header parse failed\n");
	goto failed;
    }
  
  
    switch(http_hdr->status_code) {
    case HTTP_OK_e:
	break;

    case HTTP_Authenticate_e:
	goto failed;
    
    case HTTP_NotOK_e:
	goto failed;
    }
  
    /* set file size to download */
    stream_ctrl->file_size = http_ctrl->content_length;
    
    stream_ctrl->status = streaming_downloading;
    stream_ctrl->protocol = HTTP;
  
    free_http_header(http_hdr);
    http_hdr = NULL;
    return 1;
  
  failed:
    free_http_header(http_hdr);
    http_hdr = NULL;
    return 0;
}



/*
 * read http (stream). filles buffer, and buffer size is 'size' 
 *
 *        read cached data from stream->stream_ctrl->buffer
 *        check for to see if network access is necessary
 *        get chunk(media packet) from network.
 *
 *  return value: bytes written to buffer. -1 if error.
 */
int http_streaming_read(struct stream_t *stream,
			uint8_t *buffer, size_t buffer_size)
{
    struct http_ctrl_t *http_ctrl = stream->stream_ctrl->http_ctrl;
    int ret;
  
    if(http_ctrl->content_length) {
	int rest = http_ctrl->content_length - http_ctrl->down_length;
    
	if(rest == 0) { /* finished */
	    ret = 0;
	}
	else { /* needs to get from network or buffer */
	    int recv_length = (rest > buffer_size) ? buffer_size : rest; /* smaller */
	    ret = recv_data(stream,buffer,recv_length);
	    http_ctrl->down_length += ret;
	}
    }
    else {
	/*
	  recv() returns 0 if we call recv on
	  already shutdown()ed socket
	*/
	ret = recv_data(stream,buffer,buffer_size);
    }
  
  
    if(ret == 0) {
	stream->stream_ctrl->status = streaming_finished;
    }

    return ret;
}



struct http_ctrl_t *new_http_ctrl_t(void)
{
    struct http_ctrl_t *ctrl = xmalloc(sizeof(struct http_ctrl_t));
    return ctrl;
}



void free_http_ctrl_t(struct http_ctrl_t *ctrl)
{
    free(ctrl);
}



void http_streaming_close(struct stream_t *stream)
{
    if(stream->sock > 0)
	close(stream->sock);
  
    free_http_ctrl_t(stream->stream_ctrl->http_ctrl);
    free_stream_ctrl_t(stream->stream_ctrl);
    free_stream_t(stream);
}



struct stream_t *http_streaming_init(struct url_t *url)
{
    struct stream_t *stream = new_stream_t();
    stream->url = url;
    stream->stream_ctrl = new_stream_ctrl_t();
    stream->stream_ctrl->http_ctrl = new_http_ctrl_t();
  
    stream->start = http_streaming_start;
    stream->read  = http_streaming_read;
    stream->close = http_streaming_close;
  
    return stream;
}


