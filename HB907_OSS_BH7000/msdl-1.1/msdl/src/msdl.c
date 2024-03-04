/***********************************************************************
 *    msdl.c:  download management functions
 ***********************************************************************
 * Copyright (C) 2007 metro <me_t_ro@yahoo.com>
 *
 * This file is part of msdl, media stream downloader
 * See README for program usage and information.
 * See COPYING for license information.
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

#include <ctype.h>
#include <unistd.h>
#include <getopt.h>
#include <time.h>


#include "msdl.h"
#include "msdllib.h"
#include "display.h"
#include "network.h"
#include "mmst.h"
#include "mmsh.h"
#include "http.h"
#include "ftp.h"
#include "rtsp.h"




static int streaming_download(const char *local_file,struct url_t *url,
			      struct download_opts_t *dlopts);
static int filename_cmp(void *a,void *b);
static int is_metafile(char *name);
static struct list_h *get_url_list_from_file(char *filename);




/* GLOBAL VARIABLE */
struct options_t *options;



/*
 * meta_f ... 1 --> always metafile
 *            0 --> don't know, judge from name
 *           -1 --> NEVER.
 */
struct target_t *new_target_t(char *name,int meta_f)
{
    struct target_t *t = xmalloc(sizeof(struct target_t));
    t->target_name = strdup(name);
    t->metafile_f = meta_f;
    return t;
}



void free_target_t(struct target_t *t)
{
    if(!t) {
	return;
    }
    free(t->target_name);
    free(t);
}



struct options_t *new_options_t(void)
{
    struct options_t *opt = xmalloc(sizeof(struct options_t));
    memset(opt,0,sizeof(struct options_t));
    return opt;
}



void free_options_t(struct options_t *opt)
{
    if(!opt) {
	return;
    }
  
    if(opt->protocol)       free(opt->protocol);
    if(opt->local_filename) free(opt->local_filename);
    if(opt->username)       free(opt->username);
    if(opt->password)       free(opt->password);
    if(opt->http_proxy)     free(opt->http_proxy);
    if(opt->speed)          free(opt->speed);
    if(opt->range)          free(opt->range);

    free_list_h(opt->targets,(void (*)(void *))free_target_t);
  
    free(opt);
}


struct download_opts_t *new_download_opts_t(void)
{
    struct download_opts_t *dlopts = xmalloc(sizeof(struct download_opts_t));
    memset(dlopts,0,sizeof(struct download_opts_t));
    
    return dlopts;
}



void free_download_opts_t(struct download_opts_t *dlopts)
{
    if(!dlopts) {
	return;
    }

    if(dlopts->speed) free(dlopts->speed);
    if(dlopts->range) free(dlopts->range);
    
    free(dlopts);
}



struct dlresult_t *new_dlresult_t(void)
{
    struct dlresult_t *dlr = xmalloc(sizeof(struct dlresult_t));
    memset(dlr,0,sizeof(struct dlresult_t));
    return dlr;
}



void free_dlresult_t(struct dlresult_t *dlr)
{
    if(!dlr) {
	return;
    }
    if(dlr->success_list) free_list_h(dlr->success_list,free);
    if(dlr->failed_list)  free_list_h(dlr->failed_list,free);
  
    free(dlr);
}




/*
 * take 2 urls, a and b. only use filename at the end of url.
 *           return value:     0 ... same file
 *                       : non 0 ... file name differs
 */
static int filename_cmp(void *a,void *b)
{
    char *s = (char *)a,*t = (char *)b;
    s = strrchr(s,'/') + 1;
    t = strrchr(t,'/') + 1;
    
    return strcmp(s,t);
}



/*
 * files with these extensions are regarded as metafiles.
 */
char *metafile_prefixes[] = {
    /* Windows Media metafiles */
    "asx",
    "wvx",
    "wax",
    /* Real Audio metafiles */
    "ram",
    "rpm",
    "smi",
};

char *metafile_starts[] = {
    "meta",
};



/*
 * judge if url 'name' is windows/real media metafile or not, from 
 * metafile_prefixes described above
 *            return value:   0  ... not
 *                            1  ... yes it is.
 */
static int is_metafile(char *name)
{
    int i = 0;
    char *p = NULL;
  
    if((p = strrchr(name,'.'))) {
	p++;
	for(i = 0; i < sizeof(metafile_prefixes)/sizeof(char *) ; i++) {
	    if(!strcmp(metafile_prefixes[i],p)) return 1;
	}
    }
  
    if((p = strrchr(name,'/'))) {
	p++;
	for(i = 0; i < sizeof(metafile_starts)/sizeof(char *) ; i++) {
	    if(!strncmp(metafile_starts[i],p,strlen(metafile_starts[i]))) return 1;
	}
    }

    return 0;
}



/*
 * get string(url) list from file 'filename'
 * for not downloading same URLs again and again,
 * this function returns list via "uniq" filter.
 *
 *            return value:   string list ... success
 *                            NULL        ... no url in file
 */
static struct list_h *get_url_list_from_file(char *filename)
{
    FILE *localfp = NULL;
    struct list_h *url_list = NULL;
    int linesize = BUFSIZE_1K;
    char *line = xmalloc(linesize); /* 1024 bytes default */
    char *p = NULL;
    char *sep = NULL;
    int url_len = 0;

    /* 
       try to download first url in the list
       --> maybe playlist contain same url twice
    */
    if((localfp = fopen(filename,"rb")) == NULL) {
	display(MSDL_ERR,"cannot open file %s\n",filename);
	goto failed;
    }
    while(fgets(line,linesize,localfp)) {
	while((line[strlen(line) - 1] != '\n')) { /* didn't read the whole line */

	    linesize += BUFSIZE_1K;
	    line = xrealloc(line,linesize);

	    if(fgets(line + strlen(line),BUFSIZE_1K,localfp) == NULL) {
		/* error or EOF */
		break;
	    }
	}
    
	p = line;
	while((sep = strstr(p,"://"))) { /* contains url string */
	    /* protocol is alphabet */
	    for(sep--; sep >= p && isalpha(*sep) ; sep--); 
	    sep++;
	    for(url_len = 0; url_valid_char(sep[url_len]); url_len++);
	    sep[url_len] = '\0';
      
	    /* for not downloading same file again and again */
	    if(sep[url_len - 1] != '/') { /*ignore directories */
		
		if(search_list_h(url_list,sep,filename_cmp) == NULL) {
		    /* same file not found */
		    list_h_append(&url_list,strdup(sep));
		}
		else {
		    display(MSDL_VER,"file: <%s> is not uniq, ignore this\n",
			    strrchr(sep,'/') + 1);
		}
	    }
      
	    p = &(sep[url_len]) + 1;
	}
    }
    
    
    free(line);
    fclose(localfp);
    return url_list;


  failed:
    free(line);
    return NULL;
}



/*
 * force using 'protocol'. members in 'url' will be changed
 * to match the protocol specified.
 */
void force_protocol(char *protocol, struct url_t *url)
{
    char *new_url;
    int new_url_len;

    if(protocol == NULL) return;
  
    new_url_len = strlen(protocol) + strlen("://") + strlen(url->file);
    new_url = xmalloc(new_url_len + 1);
    sprintf(new_url,"%s://%s",protocol,url->file);

    free(url->url);
    free(url->protocol);
    /* file and filepath are not malloc()ed, they points to part of url->url */
  

    url->url = new_url;
    url->protocol = strdup(protocol);
  
    url->file = strstr(new_url,"://") + 3;
    if(strchr(url->file,'/')) {
	url->filepath = strchr(url->file,'/');
    }
    else {
	url->filepath = url->file + strlen(url->file);
    }
  
    /* set protocol type */
    set_protocol_type(protocol,url);
  
}





/*
 * main download manager function 
 *
 * 'target' is url or local file which contains url(s) to download
 * 
 * return value:       number of files which successfully donwloaded
 *
 */
int msdl(struct target_t *target,struct options_t *options,struct dlresult_t *result)
{
    struct list_h *target_list = NULL;        /* string                */
    struct list_h *p = NULL;                  /* url target list       */
    struct url_t *url = NULL;                 /* for each target url   */
    struct download_opts_t *dlopts = NULL;    /* download options      */
    
    struct target_t *metafile = NULL;         /* save name of metafile */
  
    int downloaded_files_count = 0;
    int ret;
  
  
    target_list = NULL;
  
    /*
      set urls to download.
    */
    if(strstr(target->target_name,"://")) {
    
	/* we will get metafile from network */
	if(!(target->metafile_f == -1) && /* -1 is no-metafile flag */
	   (is_metafile(target->target_name) || target->metafile_f)) {
	    
	    /* set metafile name */
	    if(options->local_filename) { /* change name */
		metafile = new_target_t(options->local_filename,0);
	    }
	    else {
		metafile = new_target_t((strrchr(target->target_name,'/') + 1),0);
	    }
	}
	
	list_h_append(&target_list,strdup(target->target_name));
    }
    else { /* probablly local file (metafile or playlist)*/
    
	target_list = get_url_list_from_file(target->target_name);
    }
  
    if(target_list == NULL) {
	display(MSDL_ERR,"please specify url or file contains url\n");
	if(metafile) free_target_t(metafile);
	return 0;
    }
  
  
    /*
      set download options, such as bandwidth, some mode, etc.
      !!!! CAUTION !!!!
      these options are applied to all download in target list.
    */
    dlopts = new_download_opts_t();
    if(options->bandwidth) {
	dlopts->bandwidth = options->bandwidth;
    }
  
    if(options->no_passive_ftp_f) {
	dlopts->no_passive_ftp = 1;
    }
    
    if(options->speed) {
	dlopts->speed = strdup(options->speed);
    }

    if(options->range) {
	dlopts->range = strdup(options->range);
    }
    
    /*
      try to download files from urls one by one
    */
    for(p = target_list ; p ; p = p->next) {
    
	char *new_file; /* file name to be created */

	/*
	  check if target is really a file
	*/
	if(!strcmp((strrchr(p->p,'/') + 1),"")) { /* no file */
	    display(MSDL_NOR,"ignore %s\n : no file specified\n",p->p);
	    continue;
	}
    
	url = new_url_t(p->p);
	if(url == NULL) continue;
    
	/*
	  add username and password, and proxy settings
	*/
	if(options->username) url->username = strdup(options->username);
	if(options->password) url->password = strdup(options->password);
	if(options->http_proxy) url->http_proxy = strdup(options->http_proxy);
	
    
	/*
	  create file which contains downloaded data.
	*/
	if(options->local_filename && strcmp(options->local_filename,"")) {
	    /* change name */
	    new_file = strdup(options->local_filename);
	    strcpy(options->local_filename,""); /* don't use local_filename again */
	}
	else {
	    new_file = strdup(strrchr(url->file,'/') + 1);
	}
    
	/*
	  display download file name
	*/
	display(MSDL_NOR,"download [ %s ]\n",new_file);
	display(MSDL_VER,"%s\n",url->url);	
	
	/*
	  if url starts with "http", try MMSH first, and fallback on HTTP.
	  also, if MMST and HTTP_PROXY specified, use http.
	*/
	if(url->protocol_type == HTTP ||
	   (url->protocol_type == MMST && url->http_proxy)) {
	    url->protocol_type = MMSH;
	}
	/*
	  if url starts with "mms", try rtsp because it's the standard.
	  pure mmst protocol is obsolate.
	*/
	if(url->protocol_type == MMST) {
	    force_protocol("rtsp",url); /* change url */
	    url->protocol_type = RTSP_WMS;
	}
    
	/*
	  choose protocol if specified
	*/
	if(options->protocol) {
    
	    force_protocol(options->protocol,url);
      
	    if(url->protocol_type == UNKNOWN_PROTOCOL) {
		display(MSDL_ERR,"protocol [ %s ] not supported: fallback to http\n",
			options->protocol);
		url->protocol_type = HTTP;
	    }
	}
    

	/*
	  download file
	*/
	ret = streaming_download(new_file,url,dlopts);
	if(ret > 0) {
	    downloaded_files_count++;
	    list_h_append(&result->success_list,strdup(url->url));
	}
	else { /* failed */
	    list_h_append(&result->failed_list,strdup(url->url));
	}
    
    
	/*
	  cleanup operations
	*/
	if(url)      free_url_t(url);
	if(new_file) free(new_file);
    }

    free_list_h(target_list,free);

    /*
      download what's written inside the file if we...

      1. got file from network (see at the top of this function)
      2. it was metafile       (see at the top of this function)
      3. succeeeded downloading metafile itself
    */
    if(metafile && downloaded_files_count) {
	/*
	  this will not be infinite recursion because
	  'metafile' will not contain "://"
	*/
	downloaded_files_count += msdl(metafile,options,result);
    }
  
  
    if(dlopts)   free_download_opts_t(dlopts);
    if(metafile) free_target_t(metafile);

    return downloaded_files_count;
  
}





static void fill_buffer_with_ws(char *buffer,int buflen)
{
    if(strlen(buffer) < buflen) {
	memset(buffer + strlen(buffer),' ',buflen - strlen(buffer) - 1);
    }
    buffer[buflen - 1] = '\0';
}



/*
 * display download progress.
 */
void display_progress_info(uint64_t downloaded,uint64_t filesize)
{
    const int width = 48;
    char buffer[width + 1]; /* 1 for '\0' */
  
    if(filesize == 0) {
	snprintf(buffer,width,"DL:  %lld B",(long long)downloaded);
    }
    else {
	snprintf(buffer,width,"DL:  %lld/%lld B -- %3d %%",
		 (long long)downloaded,
		 (long long)filesize,
		 (int)(((double)downloaded/(double)filesize) * 100));
    }
  
    fill_buffer_with_ws(buffer,width + 1);
  
    display(MSDL_NOR,"%s",buffer);
  
}



/*
 * display download speed.
 */
void display_dl_speed(int speed)
{
    const int width = 12;
    char buffer[width + 1];

    if(speed > 1000000000) {
	snprintf(buffer,width,"%.1f gB/s",(double)speed / 1000000000);
    }
    else if(speed > 1000000) {
	snprintf(buffer,width,"%.1f mB/s",(double)speed / 1000000);
    }
    else if(speed > 1000) {
	snprintf(buffer,width,"%.1f kB/s",(double)speed / 1000);
    }
    else {
	snprintf(buffer,width,"%.1d B/s",speed);
    }

    fill_buffer_with_ws(buffer,width + 1); /* fill str with ' ' */
  
    display(MSDL_NOR,"%s\r",buffer); /* set cursor to beginning of line */
}



void display_remain_time(uint64_t remain_size,int speed)
{
    const int width = 12;
    char buffer[width + 1];
    uint64_t etl;

    /* remain size / speed is estimated second left */
    if(speed) {
	etl = remain_size / speed;
    }
    else {
	etl = 100 * 3600;
    }
  
    if((etl / 3600) > 100) {
	snprintf(buffer,width,"--:--:--");
    }
    else {
	snprintf(buffer,width,"%02d:%02d:%02d",
		 (int)(etl / 3600),(int)((etl % 3600 / 60)),(int)(etl % 60));
    }
  
    fill_buffer_with_ws(buffer,width + 1);
  
    display(MSDL_NOR,"%s",buffer);
}






/*
 * print protocol name to screen.
 */
void display_protocol(struct stream_t *stream)
{
    display(MSDL_VER,"download protocol: ");
    switch (stream->stream_ctrl->protocol) {
    case MMST:
	display(MSDL_VER,"mmst");
	break;
    case MMSH:
	display(MSDL_VER,"mmsh");
	break;
    case RTSP_REAL:
	display(MSDL_VER,"rtsp - real");
	break;
    case RTSP_WMS:
	display(MSDL_VER,"rtsp - wms");
	break;
    case HTTP:
	display(MSDL_VER,"http");
	break;
    case FTP:
	display(MSDL_VER,"ftp");
	break;
    default:
	display(MSDL_ERR," :) ...bug");
	break;
    }
  
    display(MSDL_VER,"\n\n");
}




/*
 * main downloading function.
 * select DLing function according to url->protocol.
 * download 'url' to 'localfile'
 *
 *      return value;   1 ... succeeded
 *                      0 ... failed
 */
int streaming_download(const char *local_file,struct url_t *url,
		       struct download_opts_t *dlopts)
{
    int ret = 0;
    struct stream_t *stream = NULL;
    uint8_t *buffer = NULL;
    int initial_port = url->port; /* port specified in URL, foobar:8080 etc..*/
    int retries = 0;

    FILE *fp = NULL;
    uint64_t size_written = 0;   /* size written to file */

    /* for displaying dl speed */
    time_t cur_time = 0,last_time = 0;
    uint64_t last_size_written = 0;
    int speed = 0;

    /*
      download header.
    */
    for(retries = 2 ; retries > 0 ; retries--) {
    
	switch(url->protocol_type) {
	case MMST:
	    stream = mmst_streaming_init(url);
	    break;
	case HTTP:
	    stream = http_streaming_init(url);
	    break;
	case MMSH:
	    stream = mmsh_streaming_init(url);
	    break;
	case FTP:
	    stream = ftp_streaming_init(url);
	    break;
	case RTSP:
	case RTSP_WMS:
	case RTSP_REAL:
	    stream = rtsp_streaming_init(url);
	    break;

	default:
	    display(MSDL_ERR,"protocol [ %s:// ] not supported\n",url->protocol);
	    goto failed;
	}

	stream->dlopts = dlopts;

	/*
	  protocol initiation
	*/
	ret = stream->start(stream);
	
	if(ret > 0) {
	    /* success */
	    break;
	}
	else if(stream->stream_ctrl->status == streaming_other_protocol) {
	    /* retry required (maximum chance is 'retries' times) */
	    url->protocol_type = stream->stream_ctrl->retry_protocol;
	    url->port = initial_port;
	    
	    stream->close(stream); /* close stream for this protocol */
	    stream = NULL;
	    continue;
	}
	else {
	    /* protocol initiation error */
	    display(MSDL_ERR,"cannot establish stream\n");
	    goto failed;
	}
    }
  
    if(retries <= 0) {
	display(MSDL_ERR,"no more retry chance\n");
	goto failed;
    }
  
    display_protocol(stream);
  
    /*
      download media.
    */
    buffer = (uint8_t *)xmalloc(BUFSIZE_4_DL);  

    /* create file here */
    if((fp = fopen(local_file,"wb")) ==  NULL) {
	display(MSDL_ERR,"cannot create file \"%s\n",local_file);
	perror("");
	goto failed;
    }
    
    
    last_time = time(NULL);
    last_size_written = 0;
    speed = 0;
    
    for(;;) {
    
	if(stream_check_data(stream,STREAM_CHECK_DATA_TIME) <= 0) {
	    /* some error or data didn't come within few(default 5) seconds*/
	    display_progress_info(size_written,stream->stream_ctrl->file_size);
	    display_dl_speed(0);
	}
    
    
	ret = stream->read(stream,buffer,BUFSIZE_4_DL);

	/* 
	   write received packet directory to file.
	   everything, such as padding has been done alrady, just write it.
	*/
	if(ret > 0) { /* success */
      
	    if(stream->stream_ctrl->status == streaming_rewind) {
		/*
		  have to write data AFTER deleting all current file content.
		 */
		display(MSDL_VER,"rewind file!!\n");
		rewind(fp);
		stream->stream_ctrl->packet_count = 0;
		size_written = 0;
		last_size_written = 0;
		speed = 0;
		
		stream->stream_ctrl->status = streaming_downloading;
	    }
	    
	    size_written += ret;

	    display_progress_info(size_written,stream->stream_ctrl->file_size);
      
	    cur_time = time(NULL);
	    if(cur_time - last_time >= 1) { /* 1 is display update second */
		/* update speed */
		speed = (size_written - last_size_written) / (cur_time - last_time);
		/* update last_* info */
		last_time = cur_time;
		last_size_written = size_written;
	    }
	    display_dl_speed(speed);
	    
	    display(MSDL_DBG,"\npacket count: %d\n",stream->stream_ctrl->packet_count);
      
	    fwrite(buffer,sizeof(uint8_t),ret,fp);
	
	}
	else if(stream->stream_ctrl->status == streaming_finished) {

	    /*
	      show 100%, because 99% sucks ...
	      have to do this because stream->stream_ctrl->file_size
	      may show wrogn file size.
	    */
      
	    display_progress_info(size_written,size_written);
	    display(MSDL_NOR,"\nfinished!!\n");
	    break;
	}
	/*
	  return value is 0 if failure
	*/
	else { /* failure */
	    display(MSDL_ERR,"!!! aborted by error !!!\n");
	    goto failed;
	}
    }  
    
    display(MSDL_NOR,"\n");
    if(stream) stream->close(stream);
    if(buffer) free(buffer);
    fclose(fp);
    return 1;


  failed:
  
    display(MSDL_NOR,"\n");
    if(stream) stream->close(stream);
    if(buffer) free(buffer);
    if(fp)     fclose(fp);
    return 0;
}


