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
*   The definition of mt85 2d
*
*/

#ifndef __MT85_2D_H__
#define __MT85_2D_H__

#include <mtk/mt85.h>

#if DFB_SUPPORT_GFX_ADAPTER
#define MT85_SUPPORTED_DRAWINGFLAGS      (DSDRAW_BLEND | \
                                          DSDRAW_SRC_PREMULTIPLY | \
                                          DSDRAW_DST_PREMULTIPLY | \
                                          DSDRAW_DEMULTIPLY)
#else
#define MT85_SUPPORTED_DRAWINGFLAGS      (DSDRAW_SRC_PREMULTIPLY | \
                                          DSDRAW_DEMULTIPLY)
#endif

#define MT85_SUPPORTED_DRAWINGFUNCTIONS  (DFXL_FILLRECTANGLE | \
                                          DFXL_DRAWRECTANGLE)



#if DFB_SUPPORT_GFX_ADAPTER
#define MT85_SUPPORTED_BLITTINGFLAGS     (DSBLIT_BLEND_ALPHACHANNEL | \
                                          DSBLIT_BLEND_COLORALPHA   | \
                                          DSBLIT_SRC_PREMULTIPLY    | \
                                          DSBLIT_DST_PREMULTIPLY    | \
                                          DSBLIT_SRC_COLORKEY)
#else
#define MT85_SUPPORTED_BLITTINGFLAGS     (DSBLIT_BLEND_ALPHACHANNEL | \
                                          DSBLIT_BLEND_COLORALPHA   | \
                                          DSBLIT_SRC_COLORKEY)
#endif

#define MT85_SUPPORTED_BLITTINGFUNCTIONS (DFXL_BLIT | \
                                          DFXL_STRETCHBLIT)



DFBResult mt85EngineSync   ( void                *drv,
                             void                *dev );

void mt85FlushTextureCache( void *driver_data, void *device_data );

void mt85FlushReadCache( void *driver_data, void *device_data );

void      mt85EngineReset  ( void                *drv,
                             void                *dev );

void      mt85EmitCommands ( void                *drv,
                             void                *dev );

void      mt85CheckState   ( void                *drv,
                             void                *dev,
                             CardState           *state,
                             DFBAccelerationMask  accel );

void      mt85SetState     ( void                *drv,
                             void                *dev,
                             GraphicsDeviceFuncs *funcs,
                             CardState           *state,
                             DFBAccelerationMask  accel );

#endif

