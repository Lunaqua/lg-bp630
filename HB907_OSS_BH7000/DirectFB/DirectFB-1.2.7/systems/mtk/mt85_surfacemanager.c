/********************************************************************************************
 *     LEGAL DISCLAIMER 
 *
 *     (Header of MediaTek Software/Firmware Release or Documentation)
 *
 *     BY OPENING OR USING THIS FILE, BUYER HEREBY UNEQUIVOCALLY ACKNOWLEDGES AND AGREES 
 *     THAT THE SOFTWARE/FIRMWARE AND ITS DOCUMENTATIONS ("MEDIATEK SOFTWARE") RECEIVED 
 *     FROM MEDIATEK AND/OR ITS REPRESENTATIVES ARE PROVIDED TO BUYER ON AN "AS-IS" BASIS 
 *     ONLY. MEDIATEK EXPRESSLY DISCLAIMS ANY AND ALL WARRANTIES, EXPRESS OR IMPLIED, 
 *     INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR 
 *     A PARTICULAR PURPOSE OR NONINFRINGEMENT. NEITHER DOES MEDIATEK PROVIDE ANY WARRANTY 
 *     WHATSOEVER WITH RESPECT TO THE SOFTWARE OF ANY THIRD PARTY WHICH MAY BE USED BY, 
 *     INCORPORATED IN, OR SUPPLIED WITH THE MEDIATEK SOFTWARE, AND BUYER AGREES TO LOOK 
 *     ONLY TO SUCH THIRD PARTY FOR ANY WARRANTY CLAIM RELATING THERETO. MEDIATEK SHALL ALSO
 *     NOT BE RESPONSIBLE FOR ANY MEDIATEK SOFTWARE RELEASES MADE TO BUYER'S SPECIFICATION 
 *     OR TO CONFORM TO A PARTICULAR STANDARD OR OPEN FORUM.
 *     
 *     BUYER'S SOLE AND EXCLUSIVE REMEDY AND MEDIATEK'S ENTIRE AND CUMULATIVE LIABILITY WITH 
 *     RESPECT TO THE MEDIATEK SOFTWARE RELEASED HEREUNDER WILL BE, AT MEDIATEK'S OPTION, 
 *     TO REVISE OR REPLACE THE MEDIATEK SOFTWARE AT ISSUE, OR REFUND ANY SOFTWARE LICENSE 
 *     FEES OR SERVICE CHARGE PAID BY BUYER TO MEDIATEK FOR SUCH MEDIATEK SOFTWARE AT ISSUE. 
 *     
 *     THE TRANSACTION CONTEMPLATED HEREUNDER SHALL BE CONSTRUED IN ACCORDANCE WITH THE LAWS 
 *     OF THE STATE OF CALIFORNIA, USA, EXCLUDING ITS CONFLICT OF LAWS PRINCIPLES.  
 ************************************************************************************************/
/*
*
* \par Description
*   The function bodies of mt85 surface manager
*
*/

#include <fusion/shmalloc.h>

#include <directfb.h>
#include <directfb_util.h>

#include <core/core.h>

#include <core/gfxcard.h>
#include <core/surface.h>
#include <core/surface_buffer.h>

#include <direct/debug.h>
#include <direct/messages.h>
#include <direct/util.h>

#include <gfx/convert.h>

#include <sys/ioctl.h>
#include "mt85.h"

#define CONFIG_DIRECTFB_MEM_PRINT 0

#if CONFIG_DIRECTFB_MEM_PRINT
static unsigned int n_all_lenth = 0;
#endif

#if CONFIG_DIRECTFB_DYNAMIC_MEM
#define DFB_DYNAMIC_MEM_LIMIT 0
static unsigned int all_lenth = 0;
#endif

#include "mt85_surfacemanager.h"

D_DEBUG_DOMAIN( SurfMan, "SurfaceManager", "DirectFB Surface Manager" );

struct _SurfaceManager {
     int                  magic;

     FusionSHMPoolShared *shmpool;
     
#if CONFIG_DIRECTFB_MULTI_MEM_POOL
     Chunk               *m_chunks[FB_MEM_SLICE_NUM];

     int                  m_offset[FB_MEM_SLICE_NUM];
     int                  m_length[FB_MEM_SLICE_NUM];
     int                  m_avail[FB_MEM_SLICE_NUM];

     int                  m_min_toleration[FB_MEM_SLICE_NUM];
#else
     Chunk               *chunks;

     int                  offset;
     int                  length;         /* length of the heap in bytes */
     int                  avail;          /* amount of available memory in bytes */

     int                  min_toleration;
#endif
     
     bool                 suspended;
};


static Chunk *split_chunk ( SurfaceManager *manager,
                            Chunk          *chunk,
                            int             length );

static Chunk *free_chunk  ( SurfaceManager *manager,
                            Chunk          *chunk );

static Chunk *occupy_chunk( SurfaceManager     *manager,
                            Chunk              *chunk,
                            CoreSurfaceBuffer  *buffer,
                            int                 length,
                            int                 pitch );


DFBResult
dfb_surfacemanager_create( CoreDFB         *core,
                           unsigned int     length,
                           SurfaceManager **ret_manager )
{
    
#if CONFIG_DIRECTFB_MULTI_MEM_POOL

     FusionSHMPoolShared *pool;
     SurfaceManager      *manager;
     Chunk               *chunk;
     int                  i = 0;

     D_DEBUG_AT( SurfMan, "%s( %p, %d )\n", __FUNCTION__, core, length );

     D_ASSERT( core != NULL );
     D_ASSERT( ret_manager != NULL );

     pool = dfb_core_shmpool( core );

     manager = SHCALLOC( pool, 1, sizeof(SurfaceManager) );
     if (!manager)
          return D_OOSHM();

     for (i = 0; i < mt85_multi_pool.num; i++)
     {
         chunk = SHCALLOC( pool, 1, sizeof(Chunk) );
         if (!chunk) {
              D_OOSHM();
              SHFREE( pool, manager );
              return DFB_NOSHAREDMEMORY;
         }
            
         manager->m_chunks[i]  = chunk;
         manager->m_offset[i]  = 0;
         manager->m_length[i]  = mt85_multi_pool.size[i];
         manager->m_avail[i]   = manager->m_length[i] - manager->m_offset[i];
         
         chunk->offset    = manager->m_offset[0];
         chunk->length    = manager->m_avail[0];
         chunk->poolno    = 0;
         
         D_MAGIC_SET( chunk, Chunk );
     }

     manager->shmpool = pool;

     D_MAGIC_SET( manager, SurfaceManager );

     D_DEBUG_AT( SurfMan, "  -> %p\n", manager );

     *ret_manager = manager;

     return DFB_OK;

#else 

     FusionSHMPoolShared *pool;
     SurfaceManager      *manager;
     Chunk               *chunk;

     D_DEBUG_AT( SurfMan, "%s( %p, %d )\n", __FUNCTION__, core, length );

     D_ASSERT( core != NULL );
     D_ASSERT( ret_manager != NULL );

     pool = dfb_core_shmpool( core );

     manager = SHCALLOC( pool, 1, sizeof(SurfaceManager) );
     if (!manager)
          return D_OOSHM();

     chunk = SHCALLOC( pool, 1, sizeof(Chunk) );
     if (!chunk) {
          D_OOSHM();
          SHFREE( pool, manager );
          return DFB_NOSHAREDMEMORY;
     }

     manager->shmpool = pool;
     manager->chunks  = chunk;
     manager->offset  = 0;
     manager->length  = length;
     manager->avail   = manager->length - manager->offset;

     D_MAGIC_SET( manager, SurfaceManager );

     chunk->offset    = manager->offset;
     chunk->length    = manager->avail;

     D_MAGIC_SET( chunk, Chunk );

     D_DEBUG_AT( SurfMan, "  -> %p\n", manager );

     *ret_manager = manager;

     return DFB_OK;

#endif
}

void
dfb_surfacemanager_destroy( SurfaceManager *manager )
{
     Chunk *chunk;
     void  *next;
     int i = 0;     

     D_DEBUG_AT( SurfMan, "%s( %p )\n", __FUNCTION__, manager );

     D_MAGIC_ASSERT( manager, SurfaceManager );
     
#if CONFIG_DIRECTFB_MULTI_MEM_POOL
     /* Deallocate all video chunks. */    
     for (i = 0; i < mt85_multi_pool.num; i++)
     {
         chunk = manager->m_chunks[i];
         while (chunk) {
              next = chunk->next;

              D_MAGIC_CLEAR( chunk );

              SHFREE( manager->shmpool, chunk );

              chunk = next;
         }
     } 
#else
     /* Deallocate all video chunks. */
     chunk = manager->chunks;
     while (chunk) {
          next = chunk->next;

          D_MAGIC_CLEAR( chunk );

          SHFREE( manager->shmpool, chunk );

          chunk = next;
     }
#endif

     D_MAGIC_CLEAR( manager );

     /* Deallocate manager struct. */
     SHFREE( manager->shmpool, manager );
}

#if CONFIG_DIRECTFB_MULTI_MEM_POOL
void
search_pool( SurfaceManager *manager, Chunk **c, CoreSurfaceBuffer *buffer, Chunk **best_free, Chunk **best_occupied, int length, int poolno )
{
    /* FIXME_SC_2  Workaround creation happening before graphics driver initialization. */
    if (!(*c)->next)
    {
         int length = mt85_multi_pool.size[poolno];
         
         if ((*c)->length != length - manager->m_offset[poolno]) {
              D_WARN( "workaround" );
         
              manager->m_length[poolno] = length;
              manager->m_avail[poolno]  = length - manager->m_offset[poolno];
         
              (*c)->length = length - manager->m_offset[poolno];
         }

    }

    while ((*c)) {
         D_MAGIC_ASSERT( (*c), Chunk );

         if ((*c)->length >= length) {
              if ((*c)->buffer) {
                   (*c)->tolerations++;
                   if ((*c)->tolerations > 0xff)
                        (*c)->tolerations = 0xff;

                   if (//FIXME_SC_1  !c->buffer->video.locked              &&
                       (*c)->buffer->policy <= buffer->policy   &&
                       (*c)->buffer->policy != CSP_VIDEOONLY    &&
                      ((buffer->policy > (*c)->buffer->policy)  ||
                       ((*c)->tolerations > manager->m_min_toleration[poolno]/8 + 2)))
                   {
                        /* found a nice place to chill */
                        if (!(*best_occupied)  ||
                            (*best_occupied)->length > (*c)->length  ||
                            (*best_occupied)->tolerations < (*c)->tolerations)
                             /* first found or better one? */
                             (*best_occupied) = (*c);
                   }
              }
              else {
                   /* found a nice place to chill */
                   if (!(*best_free)  ||  (*best_free)->length > (*c)->length)
                   {
                        /* first found or better one? */
                        (*best_free) = (*c);
                   }
              }
         }

         (*c) = (*c)->next;
    }
}
#endif /* CONFIG_DIRECTFB_MULTI_MEM_POOL */

/** public functions NOT locking the surfacemanger theirself,
    to be called between lock/unlock of surfacemanager **/

DFBResult dfb_surfacemanager_allocate( CoreDFB            *core,
                                       SurfaceManager     *manager,
                                       CoreSurfaceBuffer  *buffer,
                                       Chunk             **ret_chunk )
{
#if CONFIG_DIRECTFB_DYNAMIC_MEM

     int pitch;
     int length;
     Chunk *c;
     CoreGraphicsDevice *device;

     Chunk *best_free     = NULL;
     Chunk *best_occupied = NULL;

     D_MAGIC_ASSERT( manager, SurfaceManager );
     D_MAGIC_ASSERT( buffer, CoreSurfaceBuffer );
     D_MAGIC_ASSERT( buffer->surface, CoreSurface );
//NULL means check only     D_ASSERT( ret_chunk != NULL );

     D_DEBUG_AT( SurfMan, "%s( %p ) <- %dx%d %s\n", __FUNCTION__, buffer,
                 buffer->surface->config.size.w, buffer->surface->config.size.h,
                 dfb_pixelformat_name( buffer->surface->config.format ) );

     if (manager->suspended)
          return DFB_SUSPENDED;

     /* FIXME: Only one global device at the moment. */
     device = dfb_core_get_part( core, DFCP_GRAPHICS );
     D_ASSERT( device != NULL );

     dfb_gfxcard_calc_buffer_size( device, buffer, &pitch, &length );

     D_DEBUG_AT( SurfMan, "  -> pitch %d, length %d\n", pitch, length );

     if (ret_chunk)
     {
         struct mt85fb_alloc alloc;

#if DFB_DYNAMIC_MEM_LIMIT 
         if (all_lenth + length > DFB_DYNAMIC_MEM_LIMIT)
         {
             /*printf("====DO NOTHING==== now :%d :%s:%d ====0====\n",all_lenth,__FUNCTION__,__LINE__);*/
             D_PERROR( "DirectFB/Surfacemanager: DFB_NOVIDEOMEMORY failed!\n" );
             return DFB_NOVIDEOMEMORY;
         }
#endif /* DFB_DYNAMIC_MEM_LIMIT */

         alloc.len = (unsigned int)length;

         /*printf("-----%s:%d---pitch:%d; length:%d-----\n",__FUNCTION__,__LINE__,pitch,length);*/
         
         if (ioctl( dfb_mt85->fd, FBIO_ALLOC, &alloc ) < 0)
         {
             D_PERROR( "DirectFB/Surfacemanager: Ioctl FBIO_ALLOC failed!\n" );
             return DFB_IO;
         }

         if (!(alloc.addr))
         {
             D_PERROR( "DirectFB/Surfacemanager: DFB_NOVIDEOMEMORY failed!\n" );
             return DFB_NOVIDEOMEMORY;
         }

         all_lenth += length;
         /*printf("======== now:%d :%s:%d ====1====\n",all_lenth,__FUNCTION__,__LINE__);*/
         
         *ret_chunk = (Chunk*) SHCALLOC( manager->shmpool, 1, sizeof(Chunk) );
         (*ret_chunk)->length = length;
         (*ret_chunk)->pitch = pitch;
         (*ret_chunk)->offset = (int)(alloc.addr);
         (*ret_chunk)->tolerations = (int)(alloc.phys); /*just make use of tolerations*/
         
         /*printf("-----%s:%d---addr:0x%x; phys:0x%x-----\n",__FUNCTION__,__LINE__,alloc.addr,alloc.phys);*/

         D_MAGIC_SET( *ret_chunk, Chunk );
     }
     
     return DFB_OK;

#else /* CONFIG_DIRECTFB_DYNAMIC_MEM */
#if CONFIG_DIRECTFB_MULTI_MEM_POOL

     int pitch;
     int length;
     Chunk *c;
     CoreGraphicsDevice *device;

     int poolno;
     int i;

     Chunk *best_free     = NULL;
     Chunk *best_occupied = NULL;

     D_MAGIC_ASSERT( manager, SurfaceManager );
     D_MAGIC_ASSERT( buffer, CoreSurfaceBuffer );
     D_MAGIC_ASSERT( buffer->surface, CoreSurface );
//NULL means check only     D_ASSERT( ret_chunk != NULL );

     D_DEBUG_AT( SurfMan, "%s( %p ) <- %dx%d %s\n", __FUNCTION__, buffer,
                 buffer->surface->config.size.w, buffer->surface->config.size.h,
                 dfb_pixelformat_name( buffer->surface->config.format ) );

     if (manager->suspended)
          return DFB_SUSPENDED;

     /* FIXME: Only one global device at the moment. */
     device = dfb_core_get_part( core, DFCP_GRAPHICS );
     D_ASSERT( device != NULL );

     dfb_gfxcard_calc_buffer_size( device, buffer, &pitch, &length );

     D_DEBUG_AT( SurfMan, "  -> pitch %d, length %d\n", pitch, length );

     /* examine chunks */
     for (i = 0; i < mt85_multi_pool.num; i++)
     {
         c = manager->m_chunks[i];
         D_MAGIC_ASSERT( c, Chunk );
     
         /*printf("......Searching Pool %d......\n", i);*/
         search_pool( manager, &c, buffer, &best_free, &best_occupied, length, i );
         if (best_free)
         {
            /*printf("......Found\n");*/
            poolno = i;
            break;
         }
     }

     /* if we found a place */
     if (best_free) {
          D_DEBUG_AT( SurfMan, "  -> found free (%d)\n", best_free->length );

          /* NULL means check only. */
          if (ret_chunk)
          {
               *ret_chunk = occupy_chunk( manager, best_free, buffer, length, pitch );
               (*ret_chunk)->poolno = poolno;
          }

          return DFB_OK;
     }

     /* no luck */
     return DFB_NOVIDEOMEMORY;

#else /* CONFIG_DIRECTFB_MULTI_MEM_POOL */

     int pitch;
     int length;
     Chunk *c;
     CoreGraphicsDevice *device;

     Chunk *best_free     = NULL;
     Chunk *best_occupied = NULL;

     D_MAGIC_ASSERT( manager, SurfaceManager );
     D_MAGIC_ASSERT( buffer, CoreSurfaceBuffer );
     D_MAGIC_ASSERT( buffer->surface, CoreSurface );
//NULL means check only     D_ASSERT( ret_chunk != NULL );

     D_DEBUG_AT( SurfMan, "%s( %p ) <- %dx%d %s\n", __FUNCTION__, buffer,
                 buffer->surface->config.size.w, buffer->surface->config.size.h,
                 dfb_pixelformat_name( buffer->surface->config.format ) );

     if (manager->suspended)
          return DFB_SUSPENDED;

     /* FIXME: Only one global device at the moment. */
     device = dfb_core_get_part( core, DFCP_GRAPHICS );
     D_ASSERT( device != NULL );

     dfb_gfxcard_calc_buffer_size( device, buffer, &pitch, &length );

     D_DEBUG_AT( SurfMan, "  -> pitch %d, length %d\n", pitch, length );

     /* examine chunks */
     c = manager->chunks;
     D_MAGIC_ASSERT( c, Chunk );

     /* FIXME_SC_2  Workaround creation happening before graphics driver initialization. */
     if (!c->next) {
          int length = dfb_gfxcard_memory_length();

          if (c->length != length - manager->offset) {
               D_WARN( "workaround" );

               manager->length = length;
               manager->avail  = length - manager->offset;

               c->length = length - manager->offset;
          }
     }

     while (c) {
          D_MAGIC_ASSERT( c, Chunk );

          if (c->length >= length) {
               if (c->buffer) {
                    c->tolerations++;
                    if (c->tolerations > 0xff)
                         c->tolerations = 0xff;

                    if (//FIXME_SC_1  !c->buffer->video.locked              &&
                        c->buffer->policy <= buffer->policy   &&
                        c->buffer->policy != CSP_VIDEOONLY    &&
                       ((buffer->policy > c->buffer->policy)  ||
                        (c->tolerations > manager->min_toleration/8 + 2)))
                    {
                         /* found a nice place to chill */
                         if (!best_occupied  ||
                             best_occupied->length > c->length  ||
                             best_occupied->tolerations < c->tolerations)
                              /* first found or better one? */
                              best_occupied = c;
                    }
               }
               else {
                    /* found a nice place to chill */
                    if (!best_free  ||  best_free->length > c->length)
                         /* first found or better one? */
                         best_free = c;
               }
          }

          c = c->next;
     }

     /* if we found a place */
     if (best_free) {
          D_DEBUG_AT( SurfMan, "  -> found free (%d)\n", best_free->length );

          /* NULL means check only. */
          if (ret_chunk)
          {
#if CONFIG_DIRECTFB_MEM_PRINT
               n_all_lenth += length;
               printf("======== Alloc: %d, Now: %d ========\n", length, n_all_lenth);
#endif
               *ret_chunk = occupy_chunk( manager, best_free, buffer, length, pitch );
          }

          return DFB_OK;
     }

     D_DEBUG_AT( SurfMan, "  -> failed (%d/%d avail)\n", manager->avail, manager->length );

     /* no luck */
     return DFB_NOVIDEOMEMORY;
     
#endif /* CONFIG_DIRECTFB_MULTI_MEM_POOL */     
#endif /* CONFIG_DIRECTFB_DYNAMIC_MEM */

}

DFBResult dfb_surfacemanager_deallocate( SurfaceManager *manager,
                                         Chunk          *chunk )
{
#if CONFIG_DIRECTFB_DYNAMIC_MEM
     struct mt85fb_alloc free;

     D_MAGIC_ASSERT( manager, SurfaceManager );
     D_MAGIC_ASSERT( chunk, Chunk );

     free.len = (unsigned int)(chunk->length);
     free.addr = (unsigned int)(chunk->offset);
     free.phys = (unsigned int)(chunk->tolerations);

     /*printf("-----%s:%d---free.addr:0x%x-----\n", __FUNCTION__,__LINE__, free.addr);
     printf("-----%s:%d---free.phys:0x%x-----\n", __FUNCTION__,__LINE__, free.phys);
     printf("-----%s:%d---free.len:%d-----\n", __FUNCTION__,__LINE__, free.len);*/
     
     if (ioctl( dfb_mt85->fd, FBIO_FREE, &free) < 0)
     {
         D_PERROR( "DirectFB/Surfacemanager: Ioctl FBIO_ALLOC failed!\n" );
         return DFB_IO;
     }
     
     all_lenth -= free.len;
     /*printf("======== now:%d :%s:%d ====2====\n",all_lenth,__FUNCTION__,__LINE__);*/
     
     D_MAGIC_CLEAR( chunk );
     
     SHFREE( manager->shmpool, chunk );

     return DFB_OK;

#else
     CoreSurfaceBuffer *buffer;

     D_MAGIC_ASSERT( manager, SurfaceManager );
     D_MAGIC_ASSERT( chunk, Chunk );

     buffer = chunk->buffer;
     D_MAGIC_ASSERT( buffer, CoreSurfaceBuffer );
     D_MAGIC_ASSERT( buffer->surface, CoreSurface );

     D_DEBUG_AT( SurfMan, "%s( %p ) <- %dx%d %s\n", __FUNCTION__, buffer,
                 buffer->surface->config.size.w, buffer->surface->config.size.h,
                 dfb_pixelformat_name( buffer->surface->config.format ) );

#if CONFIG_DIRECTFB_MEM_PRINT
     n_all_lenth -= (unsigned int)(chunk->length);
     printf("======== Free: %d, Now:%d ========\n", chunk->length, n_all_lenth);
#endif

     free_chunk( manager, chunk );

     return DFB_OK;
#endif
}

/** internal functions NOT locking the surfacemanager **/

static Chunk *
split_chunk( SurfaceManager *manager, Chunk *c, int length )
{
     Chunk *newchunk;

     D_MAGIC_ASSERT( c, Chunk );

     if (c->length == length)          /* does not need be splitted */
          return c;

     newchunk = (Chunk*) SHCALLOC( manager->shmpool, 1, sizeof(Chunk) );

     /* calculate offsets and lengths of resulting chunks */
     newchunk->offset = c->offset + c->length - length;
     newchunk->length = length;
     c->length -= newchunk->length;

     /* insert newchunk after chunk c */
     newchunk->prev = c;
     newchunk->next = c->next;
     if (c->next)
          c->next->prev = newchunk;
     c->next = newchunk;

     D_MAGIC_SET( newchunk, Chunk );

     return newchunk;
}

static Chunk *
free_chunk( SurfaceManager *manager, Chunk *chunk )
{
     D_MAGIC_ASSERT( manager, SurfaceManager );
     D_MAGIC_ASSERT( chunk, Chunk );

     if (!chunk->buffer) {
          D_BUG( "freeing free chunk" );
          return chunk;
     }
     else {
          D_DEBUG_AT( SurfMan, "Deallocating %d bytes at offset %d.\n", chunk->length, chunk->offset );
     }

#if CONFIG_DIRECTFB_MULTI_MEM_POOL
     if (chunk->buffer->policy == CSP_VIDEOONLY)
          manager->m_avail[chunk->poolno] += chunk->length;

     chunk->buffer = NULL;

     manager->m_min_toleration[chunk->poolno]--;
#else
     if (chunk->buffer->policy == CSP_VIDEOONLY)
          manager->avail += chunk->length;

     chunk->buffer = NULL;

     manager->min_toleration--;
#endif

     if (chunk->prev  &&  !chunk->prev->buffer) {
          Chunk *prev = chunk->prev;

          //D_DEBUG_AT( SurfMan, "  -> merging with previous chunk at %d\n", prev->offset );

          prev->length += chunk->length;

          prev->next = chunk->next;
          if (prev->next)
               prev->next->prev = prev;

          //D_DEBUG_AT( SurfMan, "  -> freeing %p (prev %p, next %p)\n", chunk, chunk->prev, chunk->next);

          D_MAGIC_CLEAR( chunk );

          SHFREE( manager->shmpool, chunk );
          chunk = prev;
     }

     if (chunk->next  &&  !chunk->next->buffer) {
          Chunk *next = chunk->next;

          //D_DEBUG_AT( SurfMan, "  -> merging with next chunk at %d\n", next->offset );

          chunk->length += next->length;

          chunk->next = next->next;
          if (chunk->next)
               chunk->next->prev = chunk;

          D_MAGIC_CLEAR( next );

          SHFREE( manager->shmpool, next );
     }

     return chunk;
}

static Chunk *
occupy_chunk( SurfaceManager *manager, Chunk *chunk, CoreSurfaceBuffer *buffer, int length, int pitch )
{
     D_MAGIC_ASSERT( manager, SurfaceManager );
     D_MAGIC_ASSERT( chunk, Chunk );

#if CONFIG_DIRECTFB_MULTI_MEM_POOL
     if (buffer->policy == CSP_VIDEOONLY)
          manager->m_avail[chunk->poolno] -= length;
#else
     if (buffer->policy == CSP_VIDEOONLY)
          manager->avail -= length;
#endif

     chunk = split_chunk( manager, chunk, length );

     D_DEBUG_AT( SurfMan, "Allocating %d bytes at offset %d.\n", chunk->length, chunk->offset );

     chunk->buffer = buffer;
     chunk->pitch  = pitch;
     
#if CONFIG_DIRECTFB_MULTI_MEM_POOL
     manager->m_min_toleration[chunk->poolno]++;
#else
     manager->min_toleration++;
#endif

     return chunk;
}

