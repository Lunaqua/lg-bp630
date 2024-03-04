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
*   The function bodies of mt85 graphic driver
*
*/

#include <stdio.h>
#include <sys/mman.h>

#include <directfb.h>

#include <direct/debug.h>
#include <direct/messages.h>
#include <direct/system.h>

#include <core/gfxcard.h>
#include <core/system.h>
#include <core/layers.h>
#include <core/screens.h>

#include <mtk/mt85.h>

//#include <gfx_if.h>

#include "mt85_2d.h"
#include "mt85_gfxdriver.h"

#include <core/graphics_driver.h>

DFB_GRAPHICS_DRIVER( mt85gfx )

D_DEBUG_DOMAIN( MT85_GFX, "MT85/GfxDriver", "MT85 GfxDriver" );

/**********************************************************************************************************************/

static int
driver_probe( CoreGraphicsDevice *device )
{
     switch (dfb_gfxcard_get_accelerator( device )) {
          case FB_ACCEL_MEDIATEK_85XX:
               return 1;
     }

     return 0;
}

static void
driver_get_info( CoreGraphicsDevice *device,
                 GraphicsDriverInfo *info )
{
     /* fill driver info structure */
     snprintf( info->name,
               DFB_GRAPHICS_DRIVER_INFO_NAME_LENGTH,
               "MediaTek 85xx Driver" );

     snprintf( info->vendor,
               DFB_GRAPHICS_DRIVER_INFO_VENDOR_LENGTH,
               "MediaTek" );

     info->version.major = 1;
     info->version.minor = 2;

     info->driver_data_size = sizeof(MT85DriverData);
     info->device_data_size = sizeof(MT85DeviceData);
}

static DFBResult
driver_init_driver( CoreGraphicsDevice  *device,
                    GraphicsDeviceFuncs *funcs,
                    void                *driver_data,
                    void                *device_data,
                    CoreDFB             *core )
{
     MT85DriverData *mdrv = driver_data;
     MT85           *mt85 = dfb_system_data();

     D_DEBUG_AT(MT85_GFX, "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );

     mdrv->fd = mt85->fd;

     mdrv->num_plane = 0;

#if 0 /*yjg*/
     mdrv->shm_cmd_que = mmap( NULL, direct_pagesize(), PROT_READ | PROT_WRITE, MAP_SHARED,
                               mdrv->fd, direct_page_align( mt85->fix.smem_len ) );
     if (mdrv->shm_cmd_que == MAP_FAILED) {
          D_PERROR( "MediaTek/Driver: Mapping shared command queue control structure failed!\n" );
          return DFB_INIT;
     }

     D_DEBUG_AT( "MediaTek/Driver: Mapped shared command queue control structure to %p\n", mdrv->shm_cmd_que );

     mdrv->cmd_que_buf = mmap( NULL, 256 * 1024, PROT_READ | PROT_WRITE, MAP_SHARED,
                               mdrv->fd, direct_page_align( mt85->fix.smem_len ) + direct_pagesize() );
     if (mdrv->cmd_que_buf == MAP_FAILED) {
          D_PERROR( "MediaTek/Driver: Mapping DMA region failed!\n" );
          munmap( (void*) mdrv->shm_cmd_que, direct_pagesize() );
          return DFB_INIT;
     }

     D_DEBUG_AT( "MediaTek/Driver: Mapped DMA region to %p\n", mdrv->cmd_que_buf );

     //GFX_CmdQueInit( mdrv->shm_cmd_que, &mdrv->cmd_que_buf );
#endif

     /* initialize function pointers */
     funcs->EngineSync   = mt85EngineSync;
     funcs->EngineReset  = mt85EngineReset;
     funcs->EmitCommands = mt85EmitCommands;
     funcs->CheckState   = mt85CheckState;
     funcs->SetState     = mt85SetState;

     funcs->FlushTextureCache = mt85FlushTextureCache;
     funcs->FlushReadCache = mt85FlushReadCache;


     /* Register primary screen functions */
     mdrv->screen = dfb_screens_register( device, driver_data, &mt85PrimaryScreenFuncs );

     /* Register primary layer functions */
     mdrv->layer1 = dfb_layers_register( mdrv->screen, driver_data, &mt85PrimaryLayerFuncs );

     /* Register secondary layer functions */
     mdrv->layer2 = dfb_layers_register( mdrv->screen, driver_data, &mt85PrimaryLayerFuncs );
     
     return DFB_OK;
}

static DFBResult
driver_init_device( CoreGraphicsDevice *device,
                    GraphicsDeviceInfo *device_info,
                    void               *driver_data,
                    void               *device_data )
{
     D_DEBUG_AT(MT85_GFX, "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );

     /* fill device info */
     snprintf( device_info->vendor, DFB_GRAPHICS_DEVICE_INFO_VENDOR_LENGTH, "MediaTek" );
     snprintf( device_info->name,   DFB_GRAPHICS_DEVICE_INFO_NAME_LENGTH,   "85xx" );

     /* device limitations */
     device_info->limits.surface_byteoffset_alignment = 16;
     device_info->limits.surface_bytepitch_alignment  = 16;

     device_info->caps.flags    = 0;
     device_info->caps.accel    = MT85_SUPPORTED_DRAWINGFUNCTIONS |
                                  MT85_SUPPORTED_BLITTINGFUNCTIONS;
     device_info->caps.drawing  = MT85_SUPPORTED_DRAWINGFLAGS;
     device_info->caps.blitting = MT85_SUPPORTED_BLITTINGFLAGS;

     return DFB_OK;
}

static void
driver_close_device( CoreGraphicsDevice *device,
                     void               *driver_data,
                     void               *device_data )
{
     D_DEBUG_AT(MT85_GFX, "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );
}

static void
driver_close_driver( CoreGraphicsDevice *device,
                     void               *driver_data )
{
     MT85DriverData *mdrv = driver_data;

     D_DEBUG_AT(MT85_GFX, "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );

#if 0 /*yjg*/
     munmap( (void*) mdrv->cmd_que_buf, 256 * 1024 );
     munmap( (void*) mdrv->shm_cmd_que, direct_pagesize() );
#endif
}

