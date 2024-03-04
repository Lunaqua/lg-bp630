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
*   The function bodies of mt85 primary layer
*
*/

#include <pthread.h>
#include <assert.h>

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

#include <direct/messages.h>

#include <core/palette.h>
#include <core/surface.h>

#include <gfx/convert.h>
//#include <gfx_if.h>

#include "mt85.h"

D_DEBUG_DOMAIN( MTK_PRIMARY, "MTK/Primary", "MTK Primary" );

typedef enum
{
    OSD_BM_NONE,
    OSD_BM_PIXEL,
    OSD_BM_REGION,
    OSD_BM_PLANE
} OSD_BLEND_MODE_T;

unsigned int _mapColorFormat(DFBSurfacePixelFormat format) 
{
    unsigned int mt_format = CM_Reserved0;
    switch(format)
    {
        case DSPF_UNKNOWN:
        case DSPF_ARGB1555:      	
            break;
        case DSPF_RGB16:  
            mt_format = CM_RGB565_DIRECT16;
            //D_INFO( "_mapColorFormat(): CM_RGB565_DIRECT16\n");
            break;        	
        case DSPF_RGB24:
        case DSPF_RGB32:
            break;
        case DSPF_ARGB:
            mt_format = CM_ARGB8888_DIRECT32;
            //D_INFO( "_mapColorFormat(): CM_ARGB8888_DIRECT32\n");
            break;
        case DSPF_A8:
        case DSPF_YUY2:
        case DSPF_RGB332:
        case DSPF_UYVY:
        case DSPF_I420:
        case DSPF_YV12:
            break;     
        case DSPF_LUT8:
            mt_format = CM_RGB_CLUT8;        
            //D_INFO( "_mapColorFormat(): CM_RGB_CLUT8\n");
            break;
        case DSPF_ALUT44:
        case DSPF_AiRGB:
        case DSPF_A1:
        case DSPF_NV12:
        case DSPF_NV16:
        case DSPF_ARGB2554:
            break;
        case DSPF_ARGB4444:
            mt_format = CM_ARGB4444_DIRECT16;
            //D_INFO( "_mapColorFormat(): CM_ARGB4444_DIRECT16\n");
            break;
        case DSPF_NV21:
            break;
        case DSPF_AYUV:
            mt_format = CM_AYCbCr8888_DIRECT32;
            //D_INFO( "_mapColorFormat(): CM_AYCbCr8888_DIRECT32\n");
            break;        
        case DSPF_A4:
        case DSPF_ARGB1666:
        case DSPF_ARGB6666:
        case DSPF_RGB18:
        case DSPF_LUT2:
        case DSPF_RGB444:
        case DSPF_RGB555:
            break;
      }
    return mt_format;
}

/**********************************************************************************************************************/

static DFBResult
primaryInitScreen( CoreScreen           *screen,
                   CoreGraphicsDevice   *device,
                   void                 *driver_data,
                   void                 *screen_data,
                   DFBScreenDescription *description )
{
     //D_INFO( "MediaTek/System/Screen: %s: #%d\n", __FUNCTION__, __LINE__ );

     /* Set the screen capabilities. */
     description->caps = DSCCAPS_NONE;

     /* Set the screen name. */
     snprintf( description->name,
               DFB_SCREEN_DESC_NAME_LENGTH, "MediaTek 85xx Primary Screen" );

     return DFB_OK;
}

static DFBResult
primaryGetScreenSize( CoreScreen *screen,
                      void       *driver_data,
                      void       *screen_data,
                      int        *ret_width,
                      int        *ret_height )
{
	 unsigned int data[2];
	 struct mt85fb_get get;
	 get.get_type = MT85FB_GET_PANEL_SIZE;
	 get.get_size = 8;
	 get.get_data = data;
    
     //D_INFO( "MediaTek/System/Screen: %s: #%d\n", __FUNCTION__, __LINE__ );
     
     if (ioctl( dfb_mt85->fd, FBIO_GET, &get ) < 0) {
         D_PERROR( "DirectFB/MT85: FBIO_SET failed!\n" );
         return DFB_IO;
     }
    
     *ret_width  = data[0];
     *ret_height = data[1];
      
     return DFB_OK;
}

/**********************************************************************************************************************/

/*const*/ ScreenFuncs mt85PrimaryScreenFuncs = {
     InitScreen:    primaryInitScreen,
     GetScreenSize: primaryGetScreenSize
};

/**********************************************************************************************************************/
/**********************************************************************************************************************/

static int
primaryLayerDataSize()
{
     //D_INFO( "MediaTek/System/Layer: %s: #%d\n", __FUNCTION__, __LINE__ );
     
     return sizeof(MT85LayerData);
}

static int
primaryRegionDataSize()
{
     //D_INFO( "MediaTek/System/Layer: %s: #%d: NULL\n", __FUNCTION__, __LINE__ );
     
     return 0;
}

static DFBResult
primaryInitLayer( CoreLayer                  *layer,
                  void                       *driver_data,
                  void                       *layer_data,
                  DFBDisplayLayerDescription *description,
                  DFBDisplayLayerConfig      *config,
                  DFBColorAdjustment         *adjustment )
{

     MT85DriverData *sdrv = driver_data;
     MT85LayerData  *data = layer_data;
     
     //D_INFO( "MediaTek/System/Layer: %s: #%d\n", __FUNCTION__, __LINE__ );

     data->layer = sdrv->num_plane++;

     /* set capabilities and type */
     description->caps = 
	     DLCAPS_SURFACE | 
	     DLCAPS_SCREEN_POSITION | 
	     DLCAPS_SCREEN_SIZE | /*CHUN Add this*/ 	     
	     DLCAPS_LEVELS | 
	     DLCAPS_OPACITY | 
	     DLCAPS_ALPHACHANNEL;
	     
     description->type = DLTF_GRAPHICS;

     /* set name */
     snprintf( description->name,
               DFB_DISPLAY_LAYER_DESC_NAME_LENGTH, "MediaTek 85xx Primary Layer" );

     /* fill out the default configuration */
     config->flags       = DLCONF_WIDTH       | DLCONF_HEIGHT |
                           DLCONF_PIXELFORMAT | DLCONF_BUFFERMODE | DLCONF_OPTIONS;
     config->width       = MT85_PRIMARY_DEFAULT_WIDTH;
     config->height      = MT85_PRIMARY_DEFAULT_HEIGHT;
     config->pixelformat = DSPF_ARGB;
     config->buffermode  = DLBM_FRONTONLY;
     config->options     = DLOP_NONE;
     
     return DFB_OK;
}

static DFBResult
primaryTestRegion( CoreLayer                  *layer,
                   void                       *driver_data,
                   void                       *layer_data,
                   CoreLayerRegionConfig      *config,
                   CoreLayerRegionConfigFlags *failed )
{
     CoreLayerRegionConfigFlags fail = CLRCF_NONE;

     //D_INFO( "MediaTek/System/Layer: %s: #%d\n", __FUNCTION__, __LINE__ );
     
     /* check options */
     if (config->options & ~MT85_PRIMARY_SUPPORTED_OPTIONS)
     {
          fail |= CLRCF_OPTIONS;
     }
     
     /* check format */
     switch (config->format) {
          case DSPF_LUT8:
          case DSPF_ARGB4444:
          case DSPF_ARGB:
          case DSPF_RGB16:          	
          case DSPF_AYUV:          	
               break;

          default:
               fail |= CLRCF_FORMAT;
               break;
     }

     /* check width */
     if (config->width > 1920 || config->width < 2)
          fail |= CLRCF_WIDTH;

     /* check height */
     if (config->height > 1080 || config->height < 2)
          fail |= CLRCF_HEIGHT;

     /* write back failing fields */
     if (failed)
          *failed = fail;

     /* return failure if any field failed */
     if (fail)
     {
     printf(" <<<<<<<<<<<<< MT85 unsp:%d, w:%d,h:%d\n", fail, config->width,config->height);
          return DFB_UNSUPPORTED;
      }
     return DFB_OK;
}

static DFBResult
primarySetRegion( CoreLayer                  *layer,
                  void                       *driver_data,
                  void                       *layer_data,
                  void                       *region_data,
                  CoreLayerRegionConfig      *config,
                  CoreLayerRegionConfigFlags  updated,
                  CoreSurface                *surface,
                  CorePalette                *palette,
                  CoreSurfaceBufferLock      *lock )
{
     struct mt85fb_set set;

     MT85LayerData  *data = layer_data;
   
     //D_INFO( "MediaTek/System/Layer: %s: #%d\n", __FUNCTION__, __LINE__ );
     
     assert(data->layer < MT85_MAX_OSD_PLANE);

     if (updated & ~CLRCF_PALETTE) {
          D_DEBUG_AT(MTK_PRIMARY, "primarySetRegion(): \n"); 
          D_DEBUG_AT(MTK_PRIMARY, "    width: %d; height: %d\n", config->width, config->height); 
          D_DEBUG_AT(MTK_PRIMARY, "    color format: %d \n", config->format); 
          D_DEBUG_AT(MTK_PRIMARY, "    phyical addr: 0x%08x \n", lock->phys); 
          D_DEBUG_AT(MTK_PRIMARY, "    OffsetX: %d; OffsetY: %d\n", config->dest.x, config->dest.y); 
          D_DEBUG_AT(MTK_PRIMARY, "    OsdPlaneID: %d\n",  data->layer); 
          
          set.rSetting.u4Pitch = config->width * DFB_BITS_PER_PIXEL( config->format ) >> 3;
          set.rSetting.u1CM = _mapColorFormat(config->format);
          D_DEBUG_AT(MTK_PRIMARY, "_mapColorFormat(): %d\n", set.rSetting.u1CM);
          
          set.rSetting.u2W       = config->width;
          set.rSetting.u2H       = config->height;

          set.rSetting.u4Base    = lock->phys;
          
          set.rSetting.fgVisible = 1;         
          
          set.rSetting.u2OffsetX = config->dest.x;
          set.rSetting.u2OffsetY = config->dest.y;
          
          set.rSetting.u4OsdPlaneID = data->layer;
          	
          set.mask = 0;
          
         if(config->options & DLOP_OPACITY)
         {
             set.rSetting.u4Opacity = config->opacity;
             set.mask |= MT85FB_SET_MASK_OPACITY;  
             D_DEBUG_AT(MTK_PRIMARY, "primarySetRegion(): DLOP_OPACITY\n"); 
         }
         else
         {
             set.rSetting.u4Opacity = 0xff;
         }
#if 0   //to be done
         if(config->options & DLOP_ALPHACHANNEL)
         {
             set.rSetting.u4MixSel = OSD_BM_PIXEL;
             D_DEBUG_AT(MTK_PRIMARY, "primarySetRegion(): DLOP_ALPHACHANNEL\n"); 
         }
         else
         {
             set.rSetting.u4MixSel = OSD_BM_PLANE;
         }
#endif
         set.rSetting.u4MixSel = OSD_BM_PIXEL;
         
         if(config->options & DLOP_SRC_COLORKEY)
         {
             unsigned int color_key = 0;
             switch(config->format)
             	{
               case DSPF_ARGB4444: /* ??? */
//                  color_key = PIXEL_ARGB4444(0xff, config->src_key.r, config->src_key.g, config->src_key.b);              
                    D_DEBUG_AT(MTK_PRIMARY, "primarySetRegion(): DSPF_ARGB4444\n"); 
                    color_key = PIXEL_ARGB4444(/*0xff*/0, config->src_key.r, config->src_key.g, config->src_key.b);
                     set.mask  |= (MT85FB_SET_MASK_COLORKEY | MT85FB_SET_MASK_COLORKEYEN);                    
                     break;

               case DSPF_ARGB:
                    D_DEBUG_AT(MTK_PRIMARY, "primarySetRegion(): DSPF_ARGB\n"); 
                    color_key = PIXEL_ARGB(0xff, config->src_key.r, config->src_key.g, config->src_key.b);
                     set.mask  |= (MT85FB_SET_MASK_COLORKEY | MT85FB_SET_MASK_COLORKEYEN);                                        
                     break;             
               default:
               	break;
             	}
              set.rSetting.u4ColorKeyEn = 1;                                 
              set.rSetting.u4ColorKey = color_key;                                               
         }
         else
         {
             set.mask  |= MT85FB_SET_MASK_COLORKEYEN;   
             set.rSetting.u4ColorKeyEn = 0;
         }

          set.mask |=  MT85FB_SET_MASK_W |
                     MT85FB_SET_MASK_H |
                     MT85FB_SET_MASK_VIRT_W |
                     MT85FB_SET_MASK_VIRT_H |
                     MT85FB_SET_MASK_VISIBLE |
                     MT85FB_SET_MASK_PITCH |
                     MT85FB_SET_MASK_CM |
                     MT85FB_SET_MASK_BASE |
                     MT85FB_SET_MASK_OFFSET_X |
                     MT85FB_SET_MASK_OFFSET_Y|
                     MT85FB_SET_MASK_MIXSEL |
                     MT85FB_SET_MASK_PLANE_ID;
                     
          if (ioctl( dfb_mt85->fd, FBIO_SET, &set ) < 0) {
               D_PERROR( "DirectFB/MT85: FBIO_SET failed!\n" );
               return DFB_IO;
          }
     }

     if ((updated & CLRCF_PALETTE) && palette) {
          int i;
          struct mt85fb_palette pale;
          D_DEBUG_AT(MTK_PRIMARY, "primarySetRegion(): CLRCF_PALETTE\n"); 
          pale.plane_id = data->layer;

          for (i=0; i<256; i++) {
               pale.palette[i] = PIXEL_ARGB( palette->entries[i].a,
                                       palette->entries[i].r,
                                       palette->entries[i].g,
                                       palette->entries[i].b );
          }

          ioctl( dfb_mt85->fd, FBIO_PALETTE, &pale );
     }

     return DFB_OK;
}

static DFBResult
primaryRemoveRegion( CoreLayer             *layer,
                     void                  *driver_data,
                     void                  *layer_data,
                     void                  *region_data )
{
     struct mt85fb_set set;
     MT85LayerData  *data = layer_data;

     //D_INFO( "MediaTek/System/Layer: %s: #%d\n", __FUNCTION__, __LINE__ );
     
     assert(data->layer < MT85_MAX_OSD_PLANE);
     
     set.rSetting.fgVisible = 0;
     set.rSetting.u4OsdPlaneID = data->layer;
     	
     set.mask = MT85FB_SET_MASK_VISIBLE | MT85FB_SET_MASK_PLANE_ID;


     if (ioctl( dfb_mt85->fd, FBIO_SET, &set ) < 0) {
          D_PERROR( "DirectFB/MT85: FBIO_SET failed!\n" );
          return DFB_IO;
     }

     return DFB_OK;
}

static DFBResult
primaryFlipRegion( CoreLayer             *layer,
                   void                  *driver_data,
                   void                  *layer_data,
                   void                  *region_data,
                   CoreSurface           *surface,
                   DFBSurfaceFlipFlags    flags,
                   CoreSurfaceBufferLock *lock )
{
     struct mt85fb_set set;
     MT85LayerData  *data = layer_data;

     //D_INFO( "MediaTek/System/Layer: %s: #%d\n", __FUNCTION__, __LINE__ );
     
     assert(data->layer < MT85_MAX_OSD_PLANE);
     
     set.rSetting.u4Base = lock->phys;
     set.rSetting.u4OsdPlaneID = data->layer;
     
     set.mask = MT85FB_SET_MASK_BASE | MT85FB_SET_MASK_PLANE_ID;

     if (ioctl( dfb_mt85->fd, FBIO_SET, &set ) < 0) {
          D_PERROR( "DirectFB/MT85: FBIO_SET failed!\n" );
          return DFB_IO;
     }

     dfb_surface_flip( surface, false );

     return DFB_OK;
}

static DFBResult 
primaryGetLevel( CoreLayer              *layer,
                                         void                   *driver_data,
                                         void                   *layer_data,
                                         int                    *level )
{
     unsigned int au4Array[5];
     unsigned int i;
     MT85LayerData* data = layer_data;
     
     //D_INFO( "MediaTek/System/Layer: %s: #%d\n", __FUNCTION__, __LINE__ );

     assert(data->layer < MT85_MAX_OSD_PLANE);
     
     if (ioctl( dfb_mt85->fd, FBIO_GET_PLANE_ORDER_ARRAY, au4Array ) < 0) {
          D_PERROR( "DirectFB/MT85: FBIO_SET failed!\n" );
          return DFB_IO;
     }
     for(i = 0; i < 5; i++)
     {
         if(au4Array[i] == data->layer + 3)
         {
             *level = i;
         }
     }         
     
     return DFB_OK;     
}


static DFBResult 
primarySetLevel             ( CoreLayer              *layer,
                                         void                   *driver_data,
                                         void                   *layer_data,
                                         int                     level )
{
     MT85LayerData  *data = layer_data;
     unsigned int i;
     unsigned int j;
     unsigned int au4Array[5];
     unsigned int hw_layer_id;
     
     //D_INFO( "MediaTek/System/Layer: %s: #%d\n", __FUNCTION__, __LINE__ );
     
     assert(data->layer < MT85_MAX_OSD_PLANE);    

     if (ioctl( dfb_mt85->fd, FBIO_GET_PLANE_ORDER_ARRAY, au4Array ) < 0) {
          D_PERROR( "DirectFB/MT85: FBIO_SET failed!\n" );
          return DFB_IO;
     }
     
     hw_layer_id = data->layer + 3;

     for(i = 0; i < 5; i++)
     {
         if(au4Array[i] == hw_layer_id)
         {
             if(level == i)
             {                 
                 //return DFB_OK;
                 break;
             }
             /*CHUN 
               The plane is found.
             */
             printf(" [FOOL Search Found]%d\n",i);
             break;
         }
     }     
     /*HF CHUN for ajust zorder->*/
     #if 1
     if(level < i)
     {
        for(j = i; j > level; j --)            
        {
            au4Array[j] = au4Array[j - 1];
        }
     }
     else 
     if(level > i)
     {
        for(j = i; j < level; j ++)
        {
            au4Array[j] = au4Array[j + 1];
        }
     }
     au4Array[level] = hw_layer_id;
     #else
     au4Array[i] = 0xff; // None
     
     au4Array[level] = hw_layer_id;     
     #endif
     
     if (ioctl( dfb_mt85->fd, FBIO_SET_PLANE_ORDER_ARRAY, au4Array ) < 0) {
          D_PERROR( "DirectFB/MT85: FBIO_SET failed!\n" );
          return DFB_IO;
     }     

     return DFB_OK;
}


/**********************************************************************************************************************/

/*const*/ DisplayLayerFuncs mt85PrimaryLayerFuncs = {
     LayerDataSize:      primaryLayerDataSize,
     RegionDataSize:     primaryRegionDataSize,
     InitLayer:          primaryInitLayer,
     GetLevel:         primaryGetLevel,
     SetLevel:         primarySetLevel,
     TestRegion:         primaryTestRegion,
     SetRegion:          primarySetRegion,
     RemoveRegion:       primaryRemoveRegion,
     FlipRegion:         primaryFlipRegion,
};

