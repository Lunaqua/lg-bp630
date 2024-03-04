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
*   The function bodies of mt85 surface pool
*
*/

#include <asm/types.h>

#include <direct/debug.h>
#include <direct/mem.h>

#include <core/surface_pool.h>

#include <gfx/convert.h>

#include "mt85.h"
#include "mt85_surfacemanager.h"


D_DEBUG_DOMAIN( MT85_Surfaces, "MT85/Surfaces", "MT85 Framebuffer Surface Pool" );
D_DEBUG_DOMAIN( MT85_SurfLock, "MT85/SurfLock", "MT85 Framebuffer Surface Pool Locks" );

/**********************************************************************************************************************/

typedef struct {
     int             magic;

     SurfaceManager *manager;
} MT85PoolData;

typedef struct {
     int             magic;

     CoreDFB        *core;
} MT85PoolLocalData;

typedef struct {
     int             magic;

     int             offset;
     int             pitch;
     int             size;

#if CONFIG_DIRECTFB_MULTI_MEM_POOL
     int             poolno;
#endif

     Chunk          *chunk;
} MT85AllocationData;

/**********************************************************************************************************************/

static int
mt85PoolDataSize()
{
     return sizeof(MT85PoolData);
}

static int
mt85PoolLocalDataSize()
{
     return sizeof(MT85PoolLocalData);
}

static int
mt85AllocationDataSize()
{
     return sizeof(MT85AllocationData);
}

static DFBResult
mt85InitPool( CoreDFB                    *core,
              CoreSurfacePool            *pool,
              void                       *pool_data,
              void                       *pool_local,
              void                       *system_data,
              CoreSurfacePoolDescription *ret_desc )
{
     DFBResult           ret;
     MT85PoolData      *data  = pool_data;
     MT85PoolLocalData *local = pool_local;

     D_DEBUG_AT( MT85_Surfaces, "%s()\n", __FUNCTION__ );

     D_ASSERT( core != NULL );
     D_MAGIC_ASSERT( pool, CoreSurfacePool );
     D_ASSERT( data != NULL );
     D_ASSERT( local != NULL );
     D_ASSERT( ret_desc != NULL );

     ret = dfb_surfacemanager_create( core, dfb_mt85->fix.smem_len, &data->manager );
     if (ret)
          return ret;

     ret_desc->caps     = CSPCAPS_NONE;
     ret_desc->access   = CSAF_CPU_READ | CSAF_CPU_WRITE | CSAF_GPU_READ | CSAF_GPU_WRITE | CSAF_SHARED;
     ret_desc->types    = CSTF_LAYER | CSTF_WINDOW | CSTF_CURSOR | CSTF_FONT | CSTF_SHARED | CSTF_EXTERNAL;
     ret_desc->priority = CSPP_DEFAULT;

     snprintf( ret_desc->name, DFB_SURFACE_POOL_DESC_NAME_LENGTH, "mt85fb Memory" );

     local->core = core;

     D_MAGIC_SET( data, MT85PoolData );
     D_MAGIC_SET( local, MT85PoolLocalData );


     D_ASSERT( dfb_mt85 != NULL );
     D_ASSERT( dfb_mt85->shared != NULL );

     dfb_mt85->shared->manager = data->manager;

     return DFB_OK;
}

static DFBResult
mt85JoinPool( CoreDFB         *core,
              CoreSurfacePool *pool,
              void            *pool_data,
              void            *pool_local,
              void            *system_data )
{
     MT85PoolData      *data  = pool_data;
     MT85PoolLocalData *local = pool_local;

     D_DEBUG_AT( MT85_Surfaces, "%s()\n", __FUNCTION__ );

     D_ASSERT( core != NULL );
     D_MAGIC_ASSERT( pool, CoreSurfacePool );
     D_MAGIC_ASSERT( data, MT85PoolData );
     D_ASSERT( local != NULL );

     local->core = core;

     D_ASSERT( dfb_mt85 != NULL );
     D_ASSERT( dfb_mt85->shared != NULL );
     D_ASSERT( dfb_mt85->shared->manager != NULL );

     data->manager = dfb_mt85->shared->manager;

     D_MAGIC_SET( local, MT85PoolLocalData );

     return DFB_OK;
}

static DFBResult
mt85DestroyPool( CoreSurfacePool *pool,
                 void            *pool_data,
                 void            *pool_local )
{
     MT85PoolData      *data  = pool_data;
     MT85PoolLocalData *local = pool_local;

     D_DEBUG_AT( MT85_Surfaces, "%s()\n", __FUNCTION__ );

     D_MAGIC_ASSERT( pool, CoreSurfacePool );
     D_MAGIC_ASSERT( data, MT85PoolData );
     D_MAGIC_ASSERT( local, MT85PoolLocalData );

     dfb_surfacemanager_destroy( data->manager );

     D_MAGIC_CLEAR( data );
     D_MAGIC_CLEAR( local );

     return DFB_OK;
}

static DFBResult
mt85LeavePool( CoreSurfacePool *pool,
               void            *pool_data,
               void            *pool_local )
{
     MT85PoolData      *data  = pool_data;
     MT85PoolLocalData *local = pool_local;

     D_DEBUG_AT( MT85_Surfaces, "%s()\n", __FUNCTION__ );

     D_MAGIC_ASSERT( pool, CoreSurfacePool );
     D_MAGIC_ASSERT( data, MT85PoolData );
     D_MAGIC_ASSERT( local, MT85PoolLocalData );

     (void) data;

     D_MAGIC_CLEAR( local );

     return DFB_OK;
}

static DFBResult
mt85TestConfig( CoreSurfacePool         *pool,
                void                    *pool_data,
                void                    *pool_local,
                CoreSurfaceBuffer       *buffer,
                const CoreSurfaceConfig *config )
{
     DFBResult           ret;
     CoreSurface        *surface;
     MT85PoolData      *data  = pool_data;
     MT85PoolLocalData *local = pool_local;

     D_DEBUG_AT( MT85_Surfaces, "%s( %p )\n", __FUNCTION__, buffer );

     D_MAGIC_ASSERT( pool, CoreSurfacePool );
     D_MAGIC_ASSERT( data, MT85PoolData );
     D_MAGIC_ASSERT( local, MT85PoolLocalData );
     D_MAGIC_ASSERT( buffer, CoreSurfaceBuffer );

     surface = buffer->surface;
     D_MAGIC_ASSERT( surface, CoreSurface );

     ret = dfb_surfacemanager_allocate( local->core, data->manager, buffer, NULL );

     D_DEBUG_AT( MT85_Surfaces, "  -> %s\n", DirectFBErrorString(ret) );

     return ret;
}

static DFBResult
mt85AllocateBuffer( CoreSurfacePool       *pool,
                    void                  *pool_data,
                    void                  *pool_local,
                    CoreSurfaceBuffer     *buffer,
                    CoreSurfaceAllocation *allocation,
                    void                  *alloc_data )
{
     DFBResult           ret;
     Chunk              *chunk;
     CoreSurface        *surface;
     MT85PoolData       *data  = pool_data;
     MT85PoolLocalData  *local = pool_local;
     MT85AllocationData *alloc = alloc_data;

     D_DEBUG_AT( MT85_Surfaces, "%s( %p )\n", __FUNCTION__, buffer );

     D_MAGIC_ASSERT( pool, CoreSurfacePool );
     D_MAGIC_ASSERT( data, MT85PoolData );
     D_MAGIC_ASSERT( local, MT85PoolLocalData );
     D_MAGIC_ASSERT( buffer, CoreSurfaceBuffer );

     surface = buffer->surface;
     D_MAGIC_ASSERT( surface, CoreSurface );

     ret = dfb_surfacemanager_allocate( local->core, data->manager, buffer, &chunk );
     if (ret)
          return ret;

     D_MAGIC_ASSERT( chunk, Chunk );

     alloc->offset = chunk->offset;
     alloc->pitch  = chunk->pitch;
     alloc->size   = chunk->length;

#if CONFIG_DIRECTFB_MULTI_MEM_POOL
     alloc->poolno = chunk->poolno;
     /*printf("===%s:%d:use pool:[%d]===\n",__FUNCTION__,__LINE__,alloc->poolno);*/
#endif

     alloc->chunk  = chunk;

     D_DEBUG_AT( MT85_Surfaces, "  -> offset %d, pitch %d, size %d\n", alloc->offset, alloc->pitch, alloc->size );

     allocation->size   = alloc->size;
     allocation->offset = alloc->offset;

     D_MAGIC_SET( alloc, MT85AllocationData );

     return DFB_OK;
}

static DFBResult
mt85DeallocateBuffer( CoreSurfacePool       *pool,
                      void                  *pool_data,
                      void                  *pool_local,
                      CoreSurfaceBuffer     *buffer,
                      CoreSurfaceAllocation *allocation,
                      void                  *alloc_data )
{
     MT85PoolData       *data  = pool_data;
     MT85AllocationData *alloc = alloc_data;

     D_DEBUG_AT( MT85_Surfaces, "%s( %p )\n", __FUNCTION__, buffer );

     D_MAGIC_ASSERT( pool, CoreSurfacePool );
     D_MAGIC_ASSERT( data, MT85PoolData );
     D_MAGIC_ASSERT( buffer, CoreSurfaceBuffer );
     D_MAGIC_ASSERT( alloc, MT85AllocationData );

     if (alloc->chunk)
          dfb_surfacemanager_deallocate( data->manager, alloc->chunk );

     D_MAGIC_CLEAR( alloc );

     return DFB_OK;
}

static DFBResult
mt85Lock( CoreSurfacePool       *pool,
          void                  *pool_data,
          void                  *pool_local,
          CoreSurfaceAllocation *allocation,
          void                  *alloc_data,
          CoreSurfaceBufferLock *lock )
{
     MT85AllocationData *alloc = alloc_data;

     D_MAGIC_ASSERT( pool, CoreSurfacePool );
     D_MAGIC_ASSERT( allocation, CoreSurfaceAllocation );
     D_MAGIC_ASSERT( alloc, MT85AllocationData );
     D_MAGIC_ASSERT( lock, CoreSurfaceBufferLock );

     D_DEBUG_AT( MT85_SurfLock, "%s( %p )\n", __FUNCTION__, lock->buffer );

     lock->pitch  = alloc->pitch;
     lock->offset = alloc->offset;
#if CONFIG_DIRECTFB_DYNAMIC_MEM
{
     lock->addr   = alloc->offset;
     lock->phys   = alloc->chunk->tolerations;
     
     /*printf("-----%s:%d---lock->addr:0x%x--\n",__FUNCTION__,__LINE__,lock->addr);
     printf("-----%s:%d---lock->phys:0x%x--\n",__FUNCTION__,__LINE__,lock->phys);*/
}
#else /* CONFIG_DIRECTFB_DYNAMIC_MEM */

#if CONFIG_DIRECTFB_MULTI_MEM_POOL
     lock->addr   = mt85_multi_pool.addr[alloc->poolno] + alloc->offset;
     lock->phys   = mt85_multi_pool.phys[alloc->poolno] + alloc->offset;

     /*printf("---lock->addr:[0x%x]--phys:[0x%x]--pool:[%d]\n", lock->addr, lock->phys, alloc->poolno);*/

#else

     lock->addr   = dfb_mt85->mem + alloc->offset;
     lock->phys   = dfb_mt85->fix.smem_start + alloc->offset;

#endif

#endif /* CONFIG_DIRECTFB_DYNAMIC_MEM */

     D_DEBUG_AT( MT85_SurfLock, "  -> offset %lu, pitch %d, addr %p, phys 0x%08lx\n",
                 lock->offset, lock->pitch, lock->addr, lock->phys );

     return DFB_OK;
}

static DFBResult
mt85Unlock( CoreSurfacePool       *pool,
            void                  *pool_data,
            void                  *pool_local,
            CoreSurfaceAllocation *allocation,
            void                  *alloc_data,
            CoreSurfaceBufferLock *lock )
{
     MT85AllocationData *alloc = alloc_data;

     D_MAGIC_ASSERT( pool, CoreSurfacePool );
     D_MAGIC_ASSERT( allocation, CoreSurfaceAllocation );
     D_MAGIC_ASSERT( alloc, MT85AllocationData );
     D_MAGIC_ASSERT( lock, CoreSurfaceBufferLock );

     D_DEBUG_AT( MT85_SurfLock, "%s( %p )\n", __FUNCTION__, lock->buffer );

     (void) alloc;

     return DFB_OK;
}

/**********************************************************************************************************************/

const SurfacePoolFuncs mt85SurfacePoolFuncs = {
     PoolDataSize:       mt85PoolDataSize,
     PoolLocalDataSize:  mt85PoolLocalDataSize,
     AllocationDataSize: mt85AllocationDataSize,

     InitPool:           mt85InitPool,
     JoinPool:           mt85JoinPool,
     DestroyPool:        mt85DestroyPool,
     LeavePool:          mt85LeavePool,

     TestConfig:         mt85TestConfig,
     AllocateBuffer:     mt85AllocateBuffer,
     DeallocateBuffer:   mt85DeallocateBuffer,

     Lock:               mt85Lock,
     Unlock:             mt85Unlock
};

