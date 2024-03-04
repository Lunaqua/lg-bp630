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



#ifndef __DISPLAY_H__
#define __DISPLAY_H__



/*
 * colors available in most color terminals...
 */
enum {
    C_DEFAULT = 0,
    C_RED,
    C_GREEN,
    C_YELLOW,
    C_BLUE,
    C_MAGENTA,
    C_CYAN,
    NUM_COLORS,
} colors_available;



/*
 * display flags (bitfield)
 */
enum {
    MSDL_NOR = 1,       /* stdout  */
    MSDL_VER = 1 << 1,  /* verbose */
    MSDL_DBG = 1 << 2,  /* debug   */
    MSDL_ERR = 1 << 3,  /* stderr  */
} display_flags_bits;



void colorize(int color);
void display(const int flag,const char *fmt, ...);
void fatal_error(const char *fmt, ...);



#endif /* __DISPLAY_H__ */

