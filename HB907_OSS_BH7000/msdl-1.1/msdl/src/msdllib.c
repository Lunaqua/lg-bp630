/***********************************************************************
 *    msdllib.c:  utility functions
 ***********************************************************************
 * Copyright (C) 2007 metro <me_t_ro@yahoo.com>
 *
 * This file is part of msdl, media stream downloader
 *
 * utility functions.
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

#include "msdl.h"
#include "msdllib.h"
#include "display.h"


/*
 * malloc() wrapper
 */
void *xmalloc(const size_t size)
{
    void *p = malloc(size);
    if(!p) {
	fatal_error("could not allocate memory\n");
    }
    memset(p,0,size);
    return p;
}



/*
 * realloc() wrapper
 */
void *xrealloc(void *p,const size_t size)
{
    p = realloc(p,size);
    if(!p) {
	fatal_error("could not reallocate memory\n");
    }
    return p;
}


/*
 * list manipulation functions
 * new, append, search and free
 */
struct list_h *new_list_h(void *p)
{
    struct list_h *newl = xmalloc(sizeof(struct list_h));
    newl->p = p;
    newl->next = NULL;
    newl->prev = NULL;
    return newl;
}



/*
 * append 'p' to 'list'
 */
struct list_h *list_h_append(struct list_h **list,void *p)
{
    /* non-recursive method for fast append */
    struct list_h **cur,**prev;

    for(cur = prev = list ; *cur ; prev = cur,cur = &((*cur)->next));
    /* now *cur points to NULL */
    *cur = new_list_h(p);
    (*cur)->next = NULL;
    (*cur)->prev = *prev; /* if first element, prev is itself*/

    return *cur;
}



/*
 * count elements in list_h
 */
int list_h_num(struct list_h *list)
{
    int num;
  
    for(num = 0; list ; list = list->next) num++;
    return num;
}



/*
 * search list_h, using 'comp' function.
 */
struct list_h *search_list_h(struct list_h *list,void *p,
			     int (*comp)(void *,void *))
{
    struct list_h *iter;
    if(list == NULL) return NULL;

    /* non-recursive */
    for(iter = list ; iter ; iter = iter->next) {
	if(!comp(iter->p,p))
	    break;
    }
    
    return iter;
}



void free_list_h(struct list_h *list,void (*free_er)(void *))
{
    struct list_h *cur,*next;
    if(list == NULL) return; 

    /* non-recursive */
    for(cur = list ; cur ; cur = next) {
	next = cur->next;
	free_er(cur->p);
	free(cur);
    }
}



/* only4string debuginfo function */
void print_list_h(struct list_h *list)
{
    if(list == NULL) return;

    printf("%s\n",(char *)list->p);
    print_list_h(list->next);
}


