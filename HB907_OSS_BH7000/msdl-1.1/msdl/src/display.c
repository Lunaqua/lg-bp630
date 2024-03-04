/***********************************************************************
 *    display.c:  utility functions
 ***********************************************************************
 * Copyright (C) 2007 metro <me_t_ro@yahoo.com>
 *
 * This file is part of msdl, media stream downloader
 *
 * screen display function.
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
#include <stdarg.h>

#include "msdl.h"
#include "msdllib.h"
#include "display.h"





/*
 * escape sequence for each color
 */
static char *disp_colors[NUM_COLORS] = {"\033[m",
					"\033[01;31m",
					"\033[01;32m",
					"\033[01;33m",
					"\033[01;34m",
					"\033[01;35m",
					"\033[01;36m"};


/*
 * print escape sequence to stdout
 */
void colorize(int color)
{
    fprintf(stdout,"%s",disp_colors[color]);
}





/*
 * display message
 *         valid flag are : MSDL_ERR   --> error message
 *                          MSDL_NOR   --> normal stdout output
 *                          MSDL_DBG   --> only shown in debug mode
 *                          MSDL_VER   --> verbose message
 */
void display(const int flag,const char *fmt, ...)
{
    va_list ap;
    FILE *out;
    
    if(!options->debug_f) { /* if debug_f was true, show everything */
	if((flag & MSDL_DBG)) { /* do not show debug messages */
	    return;
	}
	if(options->quiet_f || /* not MSDL_DBG */
	   (!options->verbose_f && (flag & MSDL_VER))) {
	    return;
	}
    }
    
    va_start(ap,fmt);

    if(flag & MSDL_ERR) { /* error output --> stderr */
	out = stderr;
    }
    else {
	out = stdout;
    }

    vfprintf(out,fmt,ap);
    fflush(out);
    
    va_end(ap);
}



/*
 * exit after displaying error message
 */
void fatal_error(const char *fmt, ...)
{
    va_list ap;

    va_start(ap,fmt);


    vfprintf(stderr,fmt,ap);
    fflush(stderr);
    
    va_end(ap);
    exit(1);
}

