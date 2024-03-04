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
*   The function bodies of mt85 system
*
*/

#include <pthread.h>

#include <sys/ioctl.h>
#include <sys/mman.h>

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>
#include <fcntl.h>

#include <asm/types.h>

#include <directfb.h>

#include <direct/mem.h>
#include <direct/memcpy.h>
#include <direct/messages.h>
#include <direct/system.h>
#include <direct/util.h>

#include <fusion/arena.h>
#include <fusion/fusion.h>
#include <fusion/reactor.h>
#include <fusion/shmalloc.h>

#include <core/core.h>
#include <core/layers.h>
#include <core/gfxcard.h>
#include <core/screens.h>
#include <core/surface_pool.h>

#include <misc/conf.h>
#include <misc/util.h>

#include "mt85.h"
#include "mt85_vt.h"

#include <core/core_system.h>

DFB_CORE_SYSTEM( mt85sys )

MT85 *dfb_mt85 = NULL;

#if CONFIG_DIRECTFB_MULTI_MEM_POOL
MULTI_POOL mt85_multi_pool;
#endif

#if DFB_SUPPORT_GFX_ADAPTER
UINT32           _h_mt85_Ticket = 0;
UINT32           _gptCmdbufDrv  = 0;
UINT32           _gui4CmdbufLen = 0;

static INT32  mt85AdapterCmdBufGetCmdBuf(INT32 fd)
{
    MT85_ADAPTER_CMDBUF_T*      ptCmdBuff = NULL;
//    GFX_CMD_BUFF_T              t_cmdbuff;
    INT32                       i4Ret = 0;
    GFX_DRV_CMDBUF_PRIORITY     u4Priority = GFX_DRV_CMDBUF_PRIORITY_HIGH;

    /*printf("[dfb] mt85AdapterCmdBufGetCmdBuf (1)\n");
    printf("[DFB_ADAPTER] union GFX_CMD_BUF_U size = %d\n", ((sizeof(GFX_CMD_BUF_U) + 3)/4));*/

    ptCmdBuff = (MT85_ADAPTER_CMDBUF_T *)malloc(sizeof(MT85_ADAPTER_CMDBUF_T));
    ASSERT(ptCmdBuff != NULL);
    
    ptCmdBuff->fg_compress = FALSE;
    ptCmdBuff->u4GfxPriority = (UINT32)u4Priority;

//    t_cmdbuff.ui4_gfx_ticketid = 0;
//    t_cmdbuff.e_cmdbuff_priority = u4Priority;

//    if (ioctl(fd, FBIO_GETCMDBUF, &t_cmdbuff) < 0)
//    {
//        D_PERROR( "MediaTek/Driver: FBIO_GETCMDBUF failed!\n" );
//    }
    
    if (ioctl(fd, FBIO_GETCMDBUFLEN, &_gui4CmdbufLen) < 0)
    {
        D_PERROR( "MediaTek/Driver: FBIO_GETCMDBUFLEN failed!\n" );
    }
    
    printf("%s: u4CmdbufLen = 0x%X\n", __FUNCTION__, _gui4CmdbufLen);

//    ptCmdBuff->prGfxMwCmdBuf = (GFX_LINUX_CMDBUF_T *)t_cmdbuff.ui4_gfx_ticketid;
    _gptCmdbufDrv = (UINT32)mmap(NULL, _gui4CmdbufLen, PROT_READ | PROT_WRITE, MAP_SHARED, dfb_mt85->fd, 0);
    ptCmdBuff->prGfxMwCmdBuf = (GFX_LINUX_CMDBUF_T*)(_gptCmdbufDrv + sizeof(GFX_DRV_CMDBUF_T));
    _h_mt85_Ticket = (UINT32)(ptCmdBuff);
    ptCmdBuff->u4Private = _h_mt85_Ticket;
    ptCmdBuff->prGfxMwCmdBuf->u4FlushFlag = DFB_CMDBUF_FLUSH_FLAG;

    /*printf("%s: prGfxMwCmdBuf = 0x%X\n", __FUNCTION__, ptCmdBuff->prGfxMwCmdBuf);
    printf("%s: u4GfxHwInstId = 0x%X\n", __FUNCTION__, ptCmdBuff->prGfxMwCmdBuf->u4GfxHwInstId);
    printf("%s: u4GfxCmdMaxCount = %d\n", __FUNCTION__, ptCmdBuff->prGfxMwCmdBuf->u4GfxCmdMaxCount);
    printf("%s: u4GfxCurrPriority = %d\n", __FUNCTION__, ptCmdBuff->prGfxMwCmdBuf->u4GfxCurrPriority);
    printf("%s: u4RdPoint = 0x%X\n", __FUNCTION__, ptCmdBuff->prGfxMwCmdBuf->u4RdPoint);
    printf("%s: u4WrPoint = 0x%X\n", __FUNCTION__, ptCmdBuff->prGfxMwCmdBuf->u4WrPoint);
    printf("%s: u4GfxCmdBufEvent = 0x%X\n", __FUNCTION__, ptCmdBuff->prGfxMwCmdBuf->u4GfxCmdBufEvent);*/

    /*printf("[dfb] mt85AdapterCmdBufGetCmdBuf (2) ptCmdBuff->prGfxMwCmdBuf = 0x%x\n", ptCmdBuff->prGfxMwCmdBuf);*/
    
    return 0;
}

static INT32 mt85AdapterCmdBufRelCmdBuf(INT32 fd)
{
    INT32                   i4Ret = 0;
    MT85_ADAPTER_CMDBUF_T*  ptCmdBuff = NULL;

    ptCmdBuff = (MT85_ADAPTER_CMDBUF_T* )_h_mt85_Ticket;
    
    if(_gptCmdbufDrv)
    {
        i4Ret = munmap( (void*)_gptCmdbufDrv, _gui4CmdbufLen );
        if(!i4Ret)
        {
            _gptCmdbufDrv = 0;
            /*printf("*************Unmap DirectFB GFX command buffer success.\n");*/
        }
        else
        {
            printf("*************Unmap DirectFB GFX command buffer failed! ERROR_ID = %d\n", i4Ret);
            ASSERT(0);
        }
    }

    if (ioctl(fd, FBIO_RELCMDBUF, ptCmdBuff->prGfxMwCmdBuf) < 0)
    {
        D_PERROR( "MediaTek/Driver: FBIO_RELCMDBUF failed!\n" );
    }

    free((VOID*)ptCmdBuff);
    
    return i4Ret;
}

INT32 mt85AdapterCmdBufInit(INT32 fd)
{
    if (0 != mt85AdapterCmdBufGetCmdBuf(fd))
    {
        return -1;
    }
    
    return 0;
}

INT32 mt85AdapterCmdBufUninit(INT32 fd)
{
    if (0 != mt85AdapterCmdBufRelCmdBuf(fd))
    {
        return -1;
    }
    
    return 0;
}
#endif

/**********************************************************************************************************************/

static void
system_get_info( CoreSystemInfo *info )
{
     info->type = CORE_ANY;
     info->caps = CSCAPS_ACCELERATION;

     snprintf( info->name, DFB_CORE_SYSTEM_INFO_NAME_LENGTH, "MediaTek 85xx" );
}

static DFBResult
system_initialize( CoreDFB *core, void **data )
{
     DFBResult                 ret = DFB_INIT;
     struct fb_var_screeninfo  var;
     FusionSHMPoolShared      *pool;
     CoreScreen               *screen;
     MT85Shared               *shared = NULL;
     int                       i = 0;

     (void)screen;

     D_INFO( "MediaTek/System: %s: #%d\n", __FUNCTION__, __LINE__ );

     /*system("mount -t tmpfs none /tmp");
     system("insmod  /lib/modules/2.6.27-mt85xx/BDP/fusion.ko");*/
     
     
     D_ASSERT( dfb_mt85 == NULL );

     pool = dfb_core_shmpool( core );

     dfb_mt85 = D_CALLOC( 1, sizeof(MT85) );
     if (!dfb_mt85)
          return D_OOM();

     dfb_mt85->fd = -1;

     shared = (MT85Shared*) SHCALLOC( pool, 1, sizeof(MT85Shared) );
     if (!shared) {
          ret = D_OOSHM();
          goto error;
     }

     shared->shmpool = pool;

     fusion_arena_add_shared_field( dfb_core_arena( core ), "mt85", shared );

     dfb_mt85->core   = core;
     dfb_mt85->shared = shared;

     dfb_mt85->fd = direct_try_open( "/dev/fb0", "/dev/fb/0", O_RDWR, true );
     if (dfb_mt85->fd < 0)
          goto error;
     
     if (dfb_config->vt) {
          ret = dfb_vt_initialize();
          if (ret)
               goto error;
     }

     ret = DFB_INIT;

     /* Initialization of mt85fb */
     var.xres           = MT85_PRIMARY_DEFAULT_WIDTH;
     var.yres           = MT85_PRIMARY_DEFAULT_HEIGHT;
     var.xres_virtual   = MT85_PRIMARY_DEFAULT_WIDTH;
     var.yres_virtual   = MT85_PRIMARY_DEFAULT_HEIGHT * 4;
     var.xoffset        = 0;
     var.yoffset        = 0;
     var.bits_per_pixel = 32;
     var.vmode = GFX_COLORMODE_ARGB_D8888;

     if (ioctl( dfb_mt85->fd, FBIO_INIT, &var ) < 0) {
          D_PERROR( "DirectFB/MT85: FBIO_INIT failed!\n" );
          goto error;
     }

     if (ioctl( dfb_mt85->fd, FBIOGET_FSCREENINFO, &dfb_mt85->fix ) < 0) {
          D_PERROR( "DirectFB/MT85: FBIOGET_FSCREENINFO failed!\n" );
          goto error;
     }

#if 0
     D_INFO( "MediaTek/System: fix.id: %s\n", dfb_mt85->fix.id );
     D_INFO( "MediaTek/System: fix.smem_start: 0x%x\n", dfb_mt85->fix.smem_start );
     D_INFO( "MediaTek/System: fix.smem_len: 0x%x\n", dfb_mt85->fix.smem_len );
     D_INFO( "MediaTek/System: fix.type: 0x%x\n", dfb_mt85->fix.type );
     D_INFO( "MediaTek/System: fix.type_aux: 0x%x\n", dfb_mt85->fix.type_aux );
     D_INFO( "MediaTek/System: fix.visual: 0x%x\n", dfb_mt85->fix.visual );
     D_INFO( "MediaTek/System: fix.xpanstep: 0x%x\n", dfb_mt85->fix.xpanstep );
     D_INFO( "MediaTek/System: fix.ypanstep: 0x%x\n", dfb_mt85->fix.ypanstep );
     D_INFO( "MediaTek/System: fix.ywrapstep: 0x%x\n", dfb_mt85->fix.ywrapstep );
     D_INFO( "MediaTek/System: fix.line_length: 0x%x\n", dfb_mt85->fix.line_length );
     D_INFO( "MediaTek/System: fix.mmio_start: 0x%x\n", dfb_mt85->fix.mmio_start );
     D_INFO( "MediaTek/System: fix.mmio_len: 0x%x\n", dfb_mt85->fix.mmio_len );
     D_INFO( "MediaTek/System: fix.accel: 0x%x\n", dfb_mt85->fix.accel );
#endif

     if (ioctl( dfb_mt85->fd, FBIO_GETIMAGEBUFFER, &dfb_mt85->imagebuf ) < 0) {
          D_PERROR( "DirectFB/MT85: FBIO_GETIMAGEBUFFER failed!\n" );
          goto error;
     }

     /* Map the framebuffer */
#if CONFIG_DIRECTFB_DYNAMIC_MEM
     printf("UUU:CONFIG_DIRECTFB_DYNAMIC_MEM\n");

     dfb_mt85->mem = 0;

#else /* CONFIG_DIRECTFB_DYNAMIC_MEM */
#if CONFIG_DIRECTFB_MULTI_MEM_POOL

    printf("UUU:CONFIG_DIRECTFB_MULTI_MEM_POOL\n");

    if (ioctl( dfb_mt85->fd, FBIO_GETMULTIPOOL, &mt85_multi_pool) < 0) {
        D_PERROR( "DirectFB/MT85: FBIO_GETMULTIPOOL failed!\n" );
        goto error;
    }

    if (mt85_multi_pool.num == 0)
    {
        D_PERROR( "DirectFB/MT85: Get memory failed!\n" );
        goto error;
    }

    for (i = 0; i < mt85_multi_pool.num; i++)
    {
        mt85_multi_pool.addr[i] = mmap( NULL, mt85_multi_pool.size[i] - i * 0x1000, PROT_READ | PROT_WRITE, MAP_SHARED, dfb_mt85->fd, 0 );

        if (mt85_multi_pool.addr[i] == MAP_FAILED) {
            D_PERROR( "DirectFB/MT85: Could not mmap pool!\n");
            mt85_multi_pool.addr[i] = 0;
            goto error;
        }
    }

    dfb_mt85->mem = 0;

#if 0
        for (i = 0; i < mt85_multi_pool.num; i++)
        {
            printf("======%s:%d====================<Pool %d>====\n",__FUNCTION__,__LINE__,i);
            printf("======%s:%d=================[addr=0x%lx]====\n",__FUNCTION__,__LINE__,(long unsigned int)mt85_multi_pool.addr[i]);
            printf("======%s:%d=================[phys=0x%lx]====\n",__FUNCTION__,__LINE__,(long unsigned int)mt85_multi_pool.phys[i]);
            printf("======%s:%d=================[size=0x%lx]====\n",__FUNCTION__,__LINE__,mt85_multi_pool.size[i]);
            printf("======%s:%d=================[type=%d]====\n",__FUNCTION__,__LINE__,mt85_multi_pool.type[i]);
        }
#endif

#else /* CONFIG_DIRECTFB_MULTI_MEM_POOL */

     /*printf("UUU:Normal\n");*/

     dfb_mt85->mem = mmap( NULL, dfb_mt85->fix.smem_len, PROT_READ | PROT_WRITE, MAP_SHARED, dfb_mt85->fd, 0 );
     if (dfb_mt85->mem == MAP_FAILED) {
          D_PERROR( "DirectFB/MT85: Could not mmap the framebuffer!\n");
          dfb_mt85->mem = NULL;
          goto error;
     }

#endif /* CONFIG_DIRECTFB_MULTI_MEM_POOL */
#endif /* CONFIG_DIRECTFB_DYNAMIC_MEM */

     dfb_surface_pool_initialize( core, &mt85SurfacePoolFuncs, &dfb_mt85->shared->pool );

     *data = dfb_mt85;

#if DFB_SUPPORT_GFX_ADAPTER
     mt85AdapterCmdBufInit(dfb_mt85->fd);
#endif

     return DFB_OK;


error:
#if CONFIG_DIRECTFB_MULTI_MEM_POOL
     for (i = 0; i < mt85_multi_pool.num; i++)
     {
         if (mt85_multi_pool.addr[i])
         {
            munmap(mt85_multi_pool.addr[i], mt85_multi_pool.size[i]);
            mt85_multi_pool.addr[i] = 0;
         }
     }
#endif

     if (shared)
          SHFREE( pool, shared );

     if (dfb_mt85->mem)
          munmap( dfb_mt85->mem, dfb_mt85->fix.smem_len );

     if (dfb_mt85->fd >= 0)
          close( dfb_mt85->fd );
     
     D_FREE( dfb_mt85 );
     dfb_mt85 = NULL;

     return ret;
}

static DFBResult
system_join( CoreDFB *core, void **data )
{
     DFBResult   ret = DFB_INIT;
     CoreScreen *screen;
     void       *shared;
     int                       i = 0;

     D_INFO( "MediaTek/System: %s: #%d\n", __FUNCTION__, __LINE__ );

     D_ASSERT( dfb_mt85 == NULL );

     if (dfb_config->vt) {
          ret = dfb_vt_join();
          if (ret)
               return ret;
     }


     dfb_mt85 = D_CALLOC( 1, sizeof(MT85) );
     if (!dfb_mt85)
          return D_OOM();

     fusion_arena_get_shared_field( dfb_core_arena( core ), "mt85", &shared );

     dfb_mt85->core   = core;
     dfb_mt85->shared = shared;

     dfb_mt85->fd = direct_try_open( "/dev/fb0", "/dev/fb/0", O_RDWR, true );
     if (dfb_mt85->fd < 0)
          goto error;

     if (ioctl( dfb_mt85->fd, FBIOGET_FSCREENINFO, &dfb_mt85->fix ) < 0) {
          D_PERROR( "DirectFB/MT85: FBIOGET_FSCREENINFO failed!\n" );
          goto error;
     }

     if (ioctl( dfb_mt85->fd, FBIO_GETIMAGEBUFFER, &dfb_mt85->imagebuf ) < 0) {
          D_PERROR( "DirectFB/MT85: FBIO_GETIMAGEBUFFER failed!\n" );
          goto error;
     }
     
#if CONFIG_DIRECTFB_DYNAMIC_MEM
     
     dfb_mt85->mem = 0;
     
#else /* CONFIG_DIRECTFB_DYNAMIC_MEM */
#if CONFIG_DIRECTFB_MULTI_MEM_POOL  

     if (ioctl( dfb_mt85->fd, FBIO_GETMULTIPOOL, &mt85_multi_pool) < 0) {
         D_PERROR( "DirectFB/MT85: FBIO_GETMULTIPOOL failed!\n" );
         goto error;
     }
 
     if (mt85_multi_pool.num == 0)
     {
         D_PERROR( "DirectFB/MT85: Get memory failed!\n" );
         goto error;
     }

     for (i = 0; i < mt85_multi_pool.num; i++)
     {
         mt85_multi_pool.addr[i] = mmap( NULL, mt85_multi_pool.size[i] - i * 0x1000, PROT_READ | PROT_WRITE, MAP_SHARED, dfb_mt85->fd, 0 );

         if (mt85_multi_pool.addr[i] == MAP_FAILED) {
             D_PERROR( "DirectFB/MT85: Could not mmap pool!\n");
             mt85_multi_pool.addr[i] = 0;
             goto error;
         }
     }
     dfb_mt85->mem = 0;

#if 0
        for (i = 0; i < mt85_multi_pool.num; i++)
        {
            printf("======%s:%d====================<Pool %d>====\n",__FUNCTION__,__LINE__,i);
            printf("======%s:%d=================[addr=0x%lx]====\n",__FUNCTION__,__LINE__,(long unsigned int)mt85_multi_pool.addr[i]);
            printf("======%s:%d=================[phys=0x%lx]====\n",__FUNCTION__,__LINE__,(long unsigned int)mt85_multi_pool.phys[i]);
            printf("======%s:%d=================[size=0x%lx]====\n",__FUNCTION__,__LINE__,mt85_multi_pool.size[i]);
            printf("======%s:%d=================[type=%d]====\n",__FUNCTION__,__LINE__,mt85_multi_pool.type[i]);
        }
#endif

#else /* CONFIG_DIRECTFB_MULTI_MEM_POOL */

     /* Map the framebuffer */
     dfb_mt85->mem = mmap( NULL, dfb_mt85->fix.smem_len, PROT_READ | PROT_WRITE, MAP_SHARED, dfb_mt85->fd, 0 );
     if (dfb_mt85->mem == MAP_FAILED) {
          D_PERROR( "DirectFB/MT85: Could not mmap the framebuffer!\n");
          dfb_mt85->mem = NULL;
          goto error;
     }

#endif /* CONFIG_DIRECTFB_MULTI_MEM_POOL */
#endif /* CONFIG_DIRECTFB_DYNAMIC_MEM */

     dfb_surface_pool_join( core, dfb_mt85->shared->pool, &mt85SurfacePoolFuncs );

     (void)screen;

     *data = dfb_mt85;

#if DFB_SUPPORT_GFX_ADAPTER
     mt85AdapterCmdBufInit(dfb_mt85->fd);
#endif

     return DFB_OK;


error:
#if CONFIG_DIRECTFB_MULTI_MEM_POOL
     for (i = 0; i < mt85_multi_pool.num; i++)
     {
         if (mt85_multi_pool.addr[i])
         {
            munmap(mt85_multi_pool.addr[i], mt85_multi_pool.size[i]);
            mt85_multi_pool.addr[i] = 0;
         }
     }
#endif

     if (dfb_mt85->mem)
          munmap( dfb_mt85->mem, dfb_mt85->fix.smem_len );

     if (dfb_mt85->fd >= 0)
          close( dfb_mt85->fd );

     D_FREE( dfb_mt85 );
     dfb_mt85 = NULL;

     return ret;
}

static DFBResult
system_shutdown( bool emergency )
{
     DFBResult            ret;
     MT85Shared          *shared;
     FusionSHMPoolShared *pool;
     int                  i = 0;

     D_INFO( "MediaTek/System: %s: #%d\n", __FUNCTION__, __LINE__ );

     D_ASSERT( dfb_mt85 != NULL );

#if DFB_SUPPORT_GFX_ADAPTER
     mt85AdapterCmdBufUninit(dfb_mt85->fd);
#endif

     shared = dfb_mt85->shared;
     D_ASSERT( shared != NULL );

     pool = shared->shmpool;
     D_ASSERT( pool != NULL );

     dfb_surface_pool_destroy( shared->pool );

     SHFREE( pool, shared );

     if (dfb_mt85->mem)
        munmap( dfb_mt85->mem, dfb_mt85->fix.smem_len );

#if CONFIG_DIRECTFB_MULTI_MEM_POOL
     for (i = 0; i < mt85_multi_pool.num; i++)
     {
         if (mt85_multi_pool.addr[i])
         {
            munmap(mt85_multi_pool.addr[i], mt85_multi_pool.size[i]);
            mt85_multi_pool.addr[i] = 0;
         }
     }
#endif

     if (dfb_config->vt) {
          ret = dfb_vt_shutdown( emergency );
          if (ret)
               return ret;
     }


     close( dfb_mt85->fd );

     D_FREE( dfb_mt85 );
     dfb_mt85 = NULL;

     return DFB_OK;
}

static DFBResult
system_leave( bool emergency )
{
     DFBResult ret;
     int       i = 0;

     D_INFO( "MediaTek/System: %s: #%d\n", __FUNCTION__, __LINE__ );

     D_ASSERT( dfb_mt85 != NULL );

#if DFB_SUPPORT_GFX_ADAPTER
     mt85AdapterCmdBufUninit(dfb_mt85->fd);
#endif

     if (dfb_mt85->mem)
        munmap( dfb_mt85->mem, dfb_mt85->fix.smem_len );
     
#if CONFIG_DIRECTFB_MULTI_MEM_POOL
     for (i = 0; i < mt85_multi_pool.num; i++)
     {
         if (mt85_multi_pool.addr[i])
         {
            munmap(mt85_multi_pool.addr[i], mt85_multi_pool.size[i]);
            mt85_multi_pool.addr[i] = 0;
         }
     }
#endif

     if (dfb_config->vt) {
          ret = dfb_vt_leave( emergency );
          if (ret)
               return ret;
     }

     close( dfb_mt85->fd );

     D_FREE( dfb_mt85 );
     dfb_mt85 = NULL;

     return DFB_OK;
}

static DFBResult
system_suspend()
{
     return DFB_OK;
}

static DFBResult
system_resume()
{
     return DFB_OK;
}

/******************************************************************************/

static volatile void *
system_map_mmio( unsigned int    offset,
                 int             length )
{
     return NULL;
}

static void
system_unmap_mmio( volatile void  *addr,
                   int             length )
{
}

static int
system_get_accelerator()
{
     return FB_ACCEL_MEDIATEK_85XX;
}

static VideoMode *
system_get_modes()
{
     return NULL;
}

static VideoMode *
system_get_current_mode()
{
     return NULL;
}

static DFBResult
system_thread_init()
{
     return DFB_OK;
}

static bool
system_input_filter( CoreInputDevice *device,
                     DFBInputEvent   *event )
{
     if (dfb_config->vt && dfb_config->vt_switching) {
          switch (event->type) {
               case DIET_KEYPRESS:
                    if (DFB_KEY_TYPE(event->key_symbol) == DIKT_FUNCTION &&
                        event->modifiers == (DIMM_CONTROL | DIMM_ALT))
                         return dfb_vt_switch( event->key_symbol - DIKS_F1 + 1 );

                    break;

               case DIET_KEYRELEASE:
                    if (DFB_KEY_TYPE(event->key_symbol) == DIKT_FUNCTION &&
                        event->modifiers == (DIMM_CONTROL | DIMM_ALT))
                         return true;

                    break;

               default:
                    break;
          }
     }

     return false;
}

static unsigned long
system_video_memory_physical( unsigned int offset )
{
     return dfb_mt85->fix.smem_start + offset;
}

static void *
system_video_memory_virtual( unsigned int offset )
{
     return dfb_mt85->mem + offset;
}

static unsigned int
system_videoram_length()
{
     return dfb_mt85->fix.smem_len;
}

static unsigned long
system_aux_memory_physical( unsigned int offset )
{
     return 0;
}

static void *
system_aux_memory_virtual( unsigned int offset )
{
     return NULL;
}

static unsigned int
system_auxram_length()
{
     return 0;
}

static void
system_get_busid( int *ret_bus, int *ret_dev, int *ret_func )
{
}

static void
system_get_deviceid( unsigned int *ret_vendor_id,
                     unsigned int *ret_device_id )
{
}

