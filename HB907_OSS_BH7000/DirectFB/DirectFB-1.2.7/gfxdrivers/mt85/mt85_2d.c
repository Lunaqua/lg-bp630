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
*   The function bodies of mt85 2d
*
*/

#include <config.h>

#include <sys/ioctl.h>

#include <assert.h>

#include <directfb.h>

#include <direct/debug.h>
#include <direct/memcpy.h>
#include <direct/messages.h>

#include <core/state.h>
#include <core/surface.h>

#include <gfx/convert.h>

#include <mtk/mt85.h>

//#include <gfx_if.h>

#include "mt85_2d.h"
#include "mt85_gfxdriver.h"


#define MT85_2D_LOG 0

D_DEBUG_DOMAIN( MT85_2D, "MT85/2D", "MT85 2D Acceleration" );

/*
 * State validation flags.
 *
 * There's no prefix because of the macros below.
 */
enum {
    DESTINATION  = 0x00000001,
    COLOR       = 0x00000002,

    SOURCE      = 0x00000010,
    BLTOPTS_KEY  = 0x00000020,

    ALL        = 0x00000033
};

/*
 * State handling macros.
 */

#define MT85_VALIDATE(flags)       do { mdev->v_flags |=  (flags); } while (0)
#define MT85_INVALIDATE(flags)     do { mdev->v_flags &= ~(flags); } while (0)

#define MT85_CHECK_VALIDATE(flag)   do {                        \
                        if (! (mdev->v_flags & flag))          \
                           mt85_validate_##flag( mdrv, mdev, state );  \
                    } while (0)

#define MT85_RGB16_TO_ARGB(pixel)   ( 0xFF000000 |           \
                    ((((pixel) & 0xF800)==0xF800?0xFF00:((pixel) & 0xF800)) << 8) | \
                    ((((pixel) & 0x07E0)==0x07E0?0x07F8:((pixel) & 0x07E0)) << 5) | \
                    ((((pixel) & 0x001F)==0x001F?0x00FF:((pixel) & 0x001F)) << 3) )

#define MT85_ARGB4444_TO_ARGB(pixel)  (((((pixel) & 0xF000) << 16) | (((pixel) & 0xF000) <<  12)) | \
                    ((((pixel) & 0x0F00) <<  12) | (((pixel) & 0x0F00) <<  8)) | \
                    ((((pixel) & 0x00F0) <<  8) | (((pixel) & 0x00F0) <<  4)) | \
                    ((((pixel) & 0x000F) <<  4) | ((pixel) & 0x000F)) )

#define MT85_DFBColor_TO_ARGB(color)  ((((color).a) << 24) | \
                    (((color).r) << 16) | \
                    (((color).g) << 8) | \
                    ((color).b))

#define MT85_DFBColor_TO_ARGB4444(color)  ((((color).a&0xF0) << 8) | \
                    (((color).r&0xF0) << 4) | \
                    (((color).g&0xF0)) | \
                    (((color).b&0xF0)>>4))

/**************************************************************************************************/

static bool mt85FillRectangle ( void *drv, void *dev, DFBRectangle *rect );
#if (DFB_SUPPORT_GFX_ADAPTER && ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8520) || (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530)))
static bool mt85PseudFill(void *drv, void *dev, DFBRectangle *rect);
#endif
static bool mt85FillRectangleBlend ( void *drv, void *dev, DFBRectangle *rect );

static bool mt85DrawRectangle ( void *drv, void *dev, DFBRectangle *rect );

static bool mt85DrawLine     ( void *drv, void *dev, DFBRegion *line );

static bool mt85Blit        ( void *drv, void *dev, DFBRectangle *srect, int dx, int dy );
static bool mt85ColorKeyBlit ( void *drv, void *dev, DFBRectangle *srect, int dx, int dy );
static bool mt85BlitBlend    ( void *drv, void *dev, DFBRectangle *srect, int dx, int dy );

static bool mt85StretchBlit   ( void *drv, void *dev, DFBRectangle *srect, DFBRectangle *drect );
static bool mt85ColorKeyStretchBlit( void *drv, void *dev, DFBRectangle *srect, DFBRectangle *drect );
static bool mt85StretchBlitBlend( void *drv, void *dev, DFBRectangle *srect, DFBRectangle *drect );

#if ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550) || (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555))
static unsigned int ui4_src_PM_flg = 0;
static unsigned int ui4_dst_PM_flg = 0;

static bool mt85FillRectanglePM ( void *drv, void *dev, DFBRectangle *rect );
static bool mt85FillRectangleBlendPM( void *drv, void *dev, DFBRectangle *rect );

static bool mt85BlitPM        ( void *drv, void *dev, DFBRectangle *srect, int dx, int dy );
static bool mt85BlitBlendPM    ( void *drv, void *dev, DFBRectangle *srect, int dx, int dy );

static bool mt85StretchBlitPM   ( void *drv, void *dev, DFBRectangle *srect, DFBRectangle *drect );
static bool mt85StretchBlitBlendPM( void *drv, void *dev, DFBRectangle *srect, DFBRectangle *drect );
#endif


#if (DFB_SUPPORT_GFX_ADAPTER)
static bool b_hw_last = FALSE;
static void _mt85CheckFlush(void *drv, void *dev);
#endif
/**************************************************************************************************/
/*
CHUN modiification.
Don't call GFX_Set directly when set state, but save them.
*/
UINT8 * p_tmp_src;
UINT8 * p_tmp_dst;
UINT32 u4_tmp_dst_cm;
UINT32 u4_tmp_dst_pitch;
UINT32 u4_tmp_src_cm;
UINT32 u4_tmp_src_pitch;
UINT32 u4_tmp_color;
UINT32 u4_tmp_blt_opt_min;
UINT32 u4_tmp_blt_opt_max;
UINT32 u4_tmp_blt_opt;
UINT32 u4_tmp_alpha;
/*
CHUN modification end
*/

UINT32 g_u4_src_ck = 0;
UINT32 g_u4_dst_ck = 0;
UINT32 g_keysdsel = 0xff;

static unsigned char _blendSupportArray[DSBF_SRCALPHASAT + 1][DSBF_SRCALPHASAT + 1] =
{
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, MT85FB_ROP_CLEAR+1, 0, 0, 0, MT85FB_ROP_DST_IN+1, MT85FB_ROP_DST_OUT+1, 0, 0, 0, 0, 0},
    {0, MT85FB_ROP_SRC+1, 0, 0, 0, 0, MT85FB_ROP_SRC_OVER+1, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, MT85FB_ROP_SRC_IN+1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, MT85FB_ROP_SRC_OUT+1, MT85FB_ROP_DST_OVER+1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
};

static inline unsigned int _checkAlphaComp(DFBSurfaceBlendFunction src_blend, DFBSurfaceBlendFunction dst_blend)
{
    return _blendSupportArray[src_blend][dst_blend];
}
/*
 * Called by mt85SetState() to ensure that the destination registers are properly set
 * for execution of rendering functions.
 */

static inline void
mt85_validate_DESTINATION( MT85DriverData *mdrv, MT85DeviceData *mdev,
                 CardState     *state )
{
    u32 color_mode = 0;


    /* Remember destination parameters for usage in rendering functions. */
    mdev->dst_addr   = state->dst.addr;
    mdev->dst_phys   = state->dst.phys;
    mdev->dst_pitch  = state->dst.pitch;
    mdev->dst_format = state->dst.buffer->format;
    mdev->dst_bpp    = DFB_BYTES_PER_PIXEL( mdev->dst_format );

    switch (mdev->dst_format) {
        case DSPF_RGB16:
            color_mode = CM_RGB565_DIRECT16;
            //D_INFO( "mt85_validate_DESTINATION(): CM_RGB565_DIRECT16\n" );
            break;

        case DSPF_ARGB4444:
            color_mode = CM_ARGB4444_DIRECT16;
            //D_INFO( "mt85_validate_DESTINATION(): CM_ARGB4444_DIRECT16\n" );
            break;

        case DSPF_ARGB:
            color_mode = CM_ARGB8888_DIRECT32;
            //D_INFO( "mt85_validate_DESTINATION(): CM_ARGB8888_DIRECT32\n" );
            break;

        case DSPF_LUT8:
            color_mode = CM_RGB_CLUT8;
            //D_INFO( "mt85_validate_DESTINATION(): CM_RGB_CLUT8\n" );
            break;

        /*CHUN*/
        case DSPF_AYUV:
            //printf(" AYUV Hit:%s #%d\n",__FUNCTION__, __LINE__);
            color_mode = CM_AYCbCr8888_DIRECT32;
            //D_INFO( "mt85_validate_DESTINATION(): CM_AYCbCr8888_DIRECT32\n" );
            break;

        default:
            D_BUG( "unexpected destination format 0x%08x", mdev->dst_format );
    }


    /*GFX_SetDst( (UINT8*) mdev->dst_phys, color_mode, mdev->dst_pitch );*/
    p_tmp_dst      = (UINT8*) mdev->dst_phys;
    u4_tmp_dst_cm    = color_mode;
    u4_tmp_dst_pitch = mdev->dst_pitch;

    /* Set the flag. */
    MT85_VALIDATE( DESTINATION );
}

/*
 * Called by mt85SetState() to ensure that the color register is properly set
 * for execution of rendering functions.
 */
static inline void
mt85_validate_COLOR( MT85DriverData *mdrv, MT85DeviceData *mdev,
             CardState     *state )
{
    switch (mdev->dst_format) {
        case DSPF_RGB16:
            mdev->color_pixel = PIXEL_RGB16(state->color.r,
                              state->color.g,
                              state->color.b );
            //D_INFO( "mt85_validate_COLOR(): DSPF_RGB16\n" );
            break;
        case DSPF_ARGB4444:
            mdev->color_pixel = PIXEL_ARGB4444( state->color.a,
                              state->color.r,
                              state->color.g,
                              state->color.b );
            //D_INFO( "mt85_validate_COLOR(): DSPF_ARGB4444\n" );
            break;

        case DSPF_ARGB:
            mdev->color_pixel = PIXEL_ARGB( state->color.a,
                            state->color.r,
                            state->color.g,
                            state->color.b );
            //D_INFO( "mt85_validate_COLOR(): DSPF_ARGB\n" );
            break;

        case DSPF_LUT8:
            mdev->color_pixel = state->color_index;
            //D_INFO( "mt85_validate_COLOR(): DSPF_LUT8\n" );
            break;
   
        /*CHUN*/
        case DSPF_AYUV:

            mdev->color_pixel = PIXEL_AYUV( state->color.a,
                            state->color.r,
                            state->color.g,
                            state->color.b );
            //printf(" AYUV Hit:%s #%d\n",__FUNCTION__, __LINE__);
            //D_INFO( "mt85_validate_COLOR(): DSPF_LUT8\n" );
  
            break;

        default:
            D_BUG( "unexpected format %s", dfb_pixelformat_name(mdev->dst_format) );
    }

    /*GFX_SetColor( mdev->color_pixel );*/
    u4_tmp_color = mdev->color_pixel;
    /* Set the flag. */
    MT85_VALIDATE( COLOR );
}

/*
 * Called by mt85SetState() to ensure that the source registers are properly set
 * for execution of blitting functions.
 */
static inline void
mt85_validate_SOURCE( MT85DriverData *mdrv, MT85DeviceData *mdev,
              CardState     *state )
{
    u32 color_mode = 0;

    /* Remember source parameters for usage in rendering functions. */
    mdev->src_addr   = state->src.addr;
    mdev->src_phys   = state->src.phys;
    mdev->src_pitch  = state->src.pitch;
    mdev->src_format = state->src.buffer->format;
    mdev->src_bpp    = DFB_BYTES_PER_PIXEL( mdev->src_format );

    switch (mdev->src_format) {
        case DSPF_RGB16:
            color_mode = CM_RGB565_DIRECT16;
            //D_INFO( "mt85_validate_SOURCE(): CM_RGB565_DIRECT16\n" );
            break;

        case DSPF_ARGB4444:
            color_mode = CM_ARGB4444_DIRECT16;
            //D_INFO( "mt85_validate_SOURCE(): CM_ARGB4444_DIRECT16\n" );
            break;

        case DSPF_ARGB:
            color_mode = CM_ARGB8888_DIRECT32;
            //D_INFO( "mt85_validate_SOURCE(): CM_ARGB8888_DIRECT32\n" );
            break;
        case DSPF_LUT8:
            color_mode = CM_RGB_CLUT8;
            //D_INFO( "mt85_validate_SOURCE(): CM_RGB_CLUT8\n" );
            break;

        /*CHUN*/
        case DSPF_AYUV:
            color_mode = CM_AYCbCr8888_DIRECT32;
            //printf(" AYUV Hit:%s #%d\n",__FUNCTION__, __LINE__);
  
            //D_INFO( "mt85_validate_SOURCE(): CM_AYCbCr8888_DIRECT32\n" );
  
            break;
        default:
            D_BUG( "unexpected source format 0x%08x", mdev->src_format );
    }

    /*GFX_SetSrc( (UINT8*) mdev->src_phys, color_mode, mdev->src_pitch );*/
    p_tmp_src      = (UINT8*) mdev->src_phys;
    u4_tmp_src_cm    = color_mode;
    u4_tmp_src_pitch = mdev->src_pitch;

    /* Set the flag. */
    MT85_VALIDATE( SOURCE );
}

/*
 * Called by mt85SetState() to ensure that the blitting options and color ke
 * are properly set for execution of blitting functions.
 */
static inline void
mt85_validate_BLTOPTS_KEY( MT85DriverData *mdrv, MT85DeviceData *mdev,
                 CardState     *state )
{
    u32 bltopts = 0;
    u32 key_min = 0;
    u32 key_max = 0;

    if (mdev->src_format != mdev->dst_format)
        bltopts |= D_GFXFLAG_CFMT_ENA;

    if (state->blittingflags & DSBLIT_SRC_COLORKEY) {
        DFBColor key;

        bltopts |= D_GFXFLAG_TRANSPARENT;

        dfb_pixel_to_color( mdev->src_format, state->src_colorkey, &key );

        switch (mdev->src_format) {
            case DSPF_ARGB4444:
           /*HF modify*/
#ifndef HF_MW_MODIFY
                key_min = MT85_ARGB4444_TO_ARGB(state->src_colorkey);
                key_max = MT85_ARGB4444_TO_ARGB(state->src_colorkey) | 0xFF000000;
#else
                key_min = state->src_colorkey;
                key_max = state->src_colorkey;
#endif
                //D_INFO( "mt85_validate_BLTOPTS_KEY(): DSPF_ARGB4444\n" );
                /*CHUN modify end*/
                break;

            case DSPF_ARGB:
                key_min = PIXEL_ARGB( 0x00, key.r, key.g, key.b );
                key_max = PIXEL_ARGB( 0xff, key.r, key.g, key.b );
                //D_INFO( "mt85_validate_BLTOPTS_KEY(): DSPF_ARGB\n" );
                break;

            case DSPF_RGB16:
                key_min = MT85_RGB16_TO_ARGB(state->src_colorkey);
                key_max = MT85_RGB16_TO_ARGB(state->src_colorkey);
                //D_INFO( "mt85_validate_BLTOPTS_KEY(): DSPF_RGB16\n" );
                break;
            case DSPF_LUT8:
                key_min = state->src_colorkey;
                key_max = state->src_colorkey;
                //dfb_palette_search(state->source.palette, 0, 0, 0, 0)
                //D_INFO( "mt85_validate_BLTOPTS_KEY(): DSPF_LUT8\n" );
                break;

            default:
                D_BUG( "unexpected source format 0x%08x", mdev->src_format );
        }
    }

    //printf("opt: 0x%8x, key_nim: 0x%8x, key_max: 0x%8x\n" , bltopts, key_min, key_max);


    /*GFX_SetBltOpt( bltopts, key_min, key_max );*/
    u4_tmp_blt_opt = bltopts;
    u4_tmp_blt_opt_min = key_min;
    u4_tmp_blt_opt_max = key_max;

    mdev->bltopts = bltopts;

    /* Set the flag. */
    MT85_VALIDATE( BLTOPTS_KEY );
}

/**************************************************************************************************/

 /*
  * after the video memory has been written to by the CPU (e.g. modification
  * of a texture) make sure the accelerator won't use cached texture data
  */

void
mt85FlushTextureCache( void *driver_data, void *device_data )
{
    //D_INFO( "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );

#if 0
    MT85DriverData *mdrv = (MT85DriverData*) driver_data;

    if (ioctl( mdrv->fd, FBIO_GFX_WAIT ) < 0) {
        D_PERROR( "MediaTek/Driver: FBIO_GFX_WAIT failed!\n" );
    }
#endif
}

 /*
  * After the video memory has been written to by the accelerator
  * make sure the CPU won't read back cached data.
  */

void
mt85FlushReadCache( void *driver_data, void *device_data )
{
    //D_INFO( "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );

#if 0
    MT85DriverData *mdrv = (MT85DriverData*) driver_data;

    if (ioctl( mdrv->fd, FBIO_GFX_WAIT ) < 0) {
        D_PERROR( "MediaTek/Driver: FBIO_GFX_WAIT failed!\n" );
    }
#endif
}


/*
 * Wait for the blitter to be idle.
 *
 * This function is called before memory that has been written to by the hardware is about to be
 * accessed by the CPU (software driver) or another hardware entity like video encoder (by Flip()).
 * It can also be called by applications explicitly, e.g. at the end of a benchmark loop to include
 * execution time of queued commands in the measurement.
 */
DFBResult
mt85EngineSync( void *drv, void *dev )
{
    DFBResult      ret  = DFB_OK;
    MT85DriverData *mdrv = (MT85DriverData*) drv;

    //D_INFO( "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );
    _mt85CheckFlush(drv, dev);
//    if (ioctl( mdrv->fd, FBIO_GFX_WAIT ) < 0) {
//        ret = errno2result( errno );
//
//        D_PERROR( "MediaTek/Driver: FBIO_GFX_WAIT failed!\n" );
//    }

    return ret;
}

/*
 * Reset the graphics engine.
 */
void
mt85EngineReset( void *drv, void *dev )
{
    //D_INFO( "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );

    /*GFX_SetAlpha( 0xff );

    u4_tmp_alpha = 0xff;*/
}

/*
 * Start processing of queued commands if required.
 *
 * This function is called before returning from the graphics core to the application.
 * Usually that's after each rendering function. The only functions causing multiple commands
 * to be queued with a single emition at the end are DrawString(), TileBlit(), BatchBlit(),
 * DrawLines() and possibly FillTriangle() which is emulated using multiple FillRectangle() calls.
 */
void
mt85EmitCommands( void *drv, void *dev )
{
#if (DFB_SUPPORT_GFX_ADAPTER)
    b_hw_last = TRUE;
#endif
    /*MT85DriverData *mdrv = (MT85DriverData*) drv;

    //D_INFO( "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );

    if (ioctl( mdrv->fd, FBIO_GFX_FLUSH ) < 0)
        D_PERROR( "MediaTek/Driver: FBIO_GFX_FLUSH failed!\n" );*/
}

/*
 * Check for acceleration of 'accel' using the given 'state'.
 */
void
mt85CheckState( void           *drv,
           void           *dev,
           CardState        *state,
           DFBAccelerationMask  accel )
{
    //D_INFO( "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );

    /*D_DEBUG_AT( MT85_2D, "mt85CheckState (state %p, accel 0x%08x) <- dest %p\n",
          state, accel, state->destination );*/

    /* Return if the desired function is not supported at all. */
    if (accel & ~(MT85_SUPPORTED_DRAWINGFUNCTIONS | MT85_SUPPORTED_BLITTINGFUNCTIONS))
    {
    #if (DFB_SUPPORT_GFX_ADAPTER)
        _mt85CheckFlush(drv, dev);
    #endif
        return;
    }

    /* Return if the destination format is not supported. */
    switch (state->destination->config.format) {
        case DSPF_RGB16:
        case DSPF_ARGB4444:
        case DSPF_ARGB:
        case DSPF_LUT8:
        /*CHUN*/
        case DSPF_AYUV:
            break;

        default:
        #if (DFB_SUPPORT_GFX_ADAPTER)
            _mt85CheckFlush(drv, dev);
        #endif
            return;
    }

    /* Check if drawing or blitting is requested. */
    if (DFB_DRAWING_FUNCTION( accel )) {
        /* Return if unsupported drawing flags are set. */
        if (state->drawingflags & ~MT85_SUPPORTED_DRAWINGFLAGS)
        {
        #if (DFB_SUPPORT_GFX_ADAPTER)
            _mt85CheckFlush(drv, dev);
        #endif
            return;
        }

    #if ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8520) || (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530))
        if((accel & DFXL_FILLRECTANGLE) && (state->drawingflags & DSDRAW_BLEND) && (state->destination->config.caps & DSCAPS_PREMULTIPLIED))
        {
        #if (DFB_SUPPORT_GFX_ADAPTER)
            _mt85CheckFlush(drv, dev);
        #endif
            return;
        }
    #endif

        /*
        if(state->drawingflags & DSDRAW_BLEND)
        {
         return;
        }
        */

    }
    else {
        /* Return if the source format is not supported. */
        switch (state->source->config.format) {
            case DSPF_RGB16:
            case DSPF_ARGB4444:
            case DSPF_ARGB:
            case DSPF_AYUV:
            break;
            case DSPF_LUT8:
            if(state->destination->config.format !=DSPF_LUT8)
            {
            #if (DFB_SUPPORT_GFX_ADAPTER)
                _mt85CheckFlush(drv, dev);
            #endif
                return;
            }
            else
                break;
        default:
        #if (DFB_SUPPORT_GFX_ADAPTER)
            _mt85CheckFlush(drv, dev);
        #endif
            return;
        }

        /* Return if unsupported blitting flags are set. */
        if (state->blittingflags & ~MT85_SUPPORTED_BLITTINGFLAGS)
        {
        #if (DFB_SUPPORT_GFX_ADAPTER)
            _mt85CheckFlush(drv, dev);
        #endif
          return;
        }

        if(accel & (DFXL_BLIT | DFXL_STRETCHBLIT))
        {
            if (state->blittingflags & (DSBLIT_BLEND_ALPHACHANNEL | DSBLIT_BLEND_COLORALPHA))
            {
                if((state->source->config.caps & DSCAPS_PREMULTIPLIED) 
                        || (state->destination->config.caps & DSCAPS_PREMULTIPLIED))
                {
                #if (DFB_SUPPORT_GFX_ADAPTER)
                    _mt85CheckFlush(drv, dev);
                #endif
                    return;
                }
            }
            else
            {
                if((state->source->config.caps & DSCAPS_PREMULTIPLIED)
                        != (state->destination->config.caps & DSCAPS_PREMULTIPLIED))
                {
                #if (DFB_SUPPORT_GFX_ADAPTER)
                    _mt85CheckFlush(drv, dev);
                #endif
                    return;
                }
            }            
        }

        /*
        if(state->blittingflags & (DSBLIT_BLEND_ALPHACHANNEL | DSBLIT_BLEND_COLORALPHA))
        {
         return;
        }
        */

    }

    /* Enable acceleration of the function. */
    state->accel |= accel;
}

/*
 * Make sure that the hardware is programmed for execution of 'accel' according to the 'state'.
 */
void
mt85SetState( void           *drv,
         void           *dev,
         GraphicsDeviceFuncs *funcs,
         CardState        *state,
         DFBAccelerationMask  accel )
{
    MT85DeviceData       *mdev    = (MT85DeviceData*) dev;
    MT85DriverData       *mdrv    = (MT85DriverData*) drv;

    StateModificationFlags  modified = state->mod_hw;

    //D_INFO( "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );

    /*D_DEBUG_AT( MT85_2D, "mt85SetState (state %p, accel 0x%08x) <- dest %p, modified 0x%08x\n",
          state, accel, state->destination, modified );*/

    /*
     * 1) Invalidate hardware states
     *
     * Each modification to the hw independent state invalidates one or more hardware states.
     */

    /* Simply invalidate all? */
    if (modified == SMF_ALL) {
        MT85_INVALIDATE( ALL );
    }
    else if (modified) {
        /* Invalidate destination registers. */
        if (modified & SMF_DESTINATION)
            MT85_INVALIDATE( DESTINATION | COLOR );
        else if (modified & SMF_COLOR)
            MT85_INVALIDATE( COLOR );

        /* Invalidate source registers. */
        if (modified & SMF_SOURCE)
            MT85_INVALIDATE( SOURCE | BLTOPTS_KEY );
        else if (modified & (SMF_SRC_COLORKEY | SMF_BLITTING_FLAGS))
            MT85_INVALIDATE( BLTOPTS_KEY );
    }

    /*
     * 2) Validate hardware states
     *
     * Each function has its own set of states that need to be validated.
     */

    /* Always requiring valid destination... */
    MT85_CHECK_VALIDATE( DESTINATION );

    /* Remember color. */
    mdev->color = state->color;

    mdev->porterduff = _checkAlphaComp(state->src_blend, state->dst_blend);

#if MT85_2D_LOG
    printf("[mt85_2d] accel = 0x%.8X, drawingflags=%d\n", accel, state->drawingflags);
#endif

    /* Depending on the function... */
    switch (accel) {
        case DFXL_FILLRECTANGLE:
            if (state->drawingflags & DSDRAW_SRC_PREMULTIPLY)
            {
                u16 ca = state->color.a + 1;
             
                state->color.r = (state->color.r * ca) >> 8;
                state->color.g = (state->color.g * ca) >> 8;
                state->color.b = (state->color.b * ca) >> 8;
            }
            if (state->drawingflags & DSDRAW_DEMULTIPLY)
            {
                u16 ca = state->color.a + 1;
             
                state->color.r = (state->color.r << 8) / ca;
                state->color.g = (state->color.g << 8) / ca;
                state->color.b = (state->color.b << 8) / ca;
            }

            /* ...require valid drawing color. */
            MT85_CHECK_VALIDATE( COLOR );

            if(state->drawingflags & DSDRAW_BLEND)
            {
        #if DFB_SUPPORT_GFX_ADAPTER
            #if ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8520) || (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530))
                if(state->destination->config.caps & DSCAPS_PREMULTIPLIED)
                {
                    printf("[DFB] Do not support PRE-MULTIPLY in MT8520 | MT8530 (FillBlend)\n");
                    funcs->FillRectangle = mt85PseudFill;
                }
                else
            #elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550)
                if(state->destination->config.caps & DSCAPS_PREMULTIPLIED)
                {
                    funcs->FillRectangle = mt85FillRectangleBlendPM;
                }
                else
            #elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555)
                if (state->destination->config.caps & DSCAPS_PREMULTIPLIED)
                {
//                    u16 ca = state->color.a + 1;
//
//                    state->color.r = (state->color.r * ca) >> 8;
//                    state->color.g = (state->color.g * ca) >> 8;
//                    state->color.b = (state->color.b * ca) >> 8;

                    funcs->FillRectangle = mt85FillRectangleBlendPM;
                }
                else
            #endif
                funcs->FillRectangle = mt85FillRectangleBlend;
        #else
            #if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555)
                if(state->destination->config.caps & DSCAPS_PREMULTIPLIED)
                {
                    funcs->FillRectangle = mt85FillRectanglePM;
                }
                else
            #endif
                funcs->FillRectangle = mt85FillRectangleBlend;
        #endif
            }
            else
            {
            #if DFB_SUPPORT_GFX_ADAPTER
                // Normal fill rectangle
                if (state->destination->config.caps & DSCAPS_PREMULTIPLIED)
                {
                    u16 ca = state->color.a + 1;
                    //printf("This destination surface is PREMULTIPLIED!\n");
                    state->color.r = (state->color.r * ca) >> 8;
                    state->color.g = (state->color.g * ca) >> 8;
                    state->color.b = (state->color.b * ca) >> 8;
                }
            #endif

                funcs->FillRectangle = mt85FillRectangle;
            }

            if (mdev->bltopts)
            {
                mdev->bltopts = 0;
    
                /*GFX_SetBltOpt( 0, 0, 0 );*/
                u4_tmp_blt_opt = 0;
                u4_tmp_blt_opt_min = 0;
                u4_tmp_blt_opt_max = 0;
    
    
                MT85_INVALIDATE( BLTOPTS_KEY );
            }

            /*
             * 3) Tell which functions can be called without further validation, i.e. SetState()
             *
             * When the hw independent state is changed, this collection is reset.
             */
            state->set = MT85_SUPPORTED_DRAWINGFUNCTIONS;
            break;

        case DFXL_DRAWRECTANGLE:
            if (state->drawingflags & DSDRAW_SRC_PREMULTIPLY)
            {
                u16 ca = state->color.a + 1;
             
                state->color.r = (state->color.r * ca) >> 8;
                state->color.g = (state->color.g * ca) >> 8;
                state->color.b = (state->color.b * ca) >> 8;
            }
            if (state->drawingflags & DSDRAW_DEMULTIPLY)
            {
                u16 ca = state->color.a + 1;
             
                state->color.r = (state->color.r << 8) / ca;
                state->color.g = (state->color.g << 8) / ca;
                state->color.b = (state->color.b << 8) / ca;
            }

            /* ...require valid drawing color. */
            MT85_CHECK_VALIDATE( COLOR );
  
            funcs->DrawRectangle = mt85DrawRectangle;
  
            state->set = MT85_SUPPORTED_DRAWINGFUNCTIONS;
            break;

        case DFXL_DRAWLINE:
            /* ...require valid drawing color. */
            MT85_CHECK_VALIDATE( COLOR );
  
            funcs->DrawLine = mt85DrawLine;
  
            state->set = MT85_SUPPORTED_DRAWINGFUNCTIONS;
            break;

        case DFXL_BLIT:
        case DFXL_STRETCHBLIT:
            /* ...require valid source and blitting options. */
            MT85_CHECK_VALIDATE( SOURCE );
            MT85_CHECK_VALIDATE( BLTOPTS_KEY );
  
            /* Use alpha blending? */
            if (state->blittingflags & (DSBLIT_BLEND_ALPHACHANNEL | DSBLIT_BLEND_COLORALPHA))
            {
                if (!(state->blittingflags & DSBLIT_BLEND_COLORALPHA))
                {
                    mdev->color.a = 0xff;
                }

        #if DFB_SUPPORT_GFX_ADAPTER
            #if ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8520) || (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530))
                if((state->source->config.caps & DSCAPS_PREMULTIPLIED) || 
                   (state->destination->config.caps & DSCAPS_PREMULTIPLIED))
                {
                    printf("[DFB] Do not support this operation in MT8520 | MT8530 (BitbltBlend/StretchBitbltBlend)\n");
                }
                else
            #elif ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550) || (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555))
                if ((state->source->config.caps & DSCAPS_PREMULTIPLIED) || 
                    (state->destination->config.caps & DSCAPS_PREMULTIPLIED))
                {
                    ui4_src_PM_flg = (state->source->config.caps & DSCAPS_PREMULTIPLIED) ? 1 : 0;
                    ui4_dst_PM_flg = (state->destination->config.caps & DSCAPS_PREMULTIPLIED) ? 1 : 0;

                    funcs->Blit = mt85BlitBlendPM;
                    funcs->StretchBlit = mt85StretchBlitBlendPM;
                }
                else
            #endif
        #else
            #if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555)
                if((state->source->config.caps & DSCAPS_PREMULTIPLIED) ||
                   (state->destination->config.caps & DSCAPS_PREMULTIPLIED))
                {
                    funcs->Blit = mt85BlitBlendPM;
                    funcs->StretchBlit = mt85StretchBlitBlendPM;
                }
                else
            #endif
        #endif
                {
                    /* Normal surface */
                    funcs->Blit = mt85BlitBlend;
                    funcs->StretchBlit = mt85StretchBlitBlend;
                }
            }
            else if (state->blittingflags & (DSBLIT_SRC_COLORKEY | DSBLIT_DST_COLORKEY))
            {
                if (state->blittingflags & DSBLIT_SRC_COLORKEY)
                {
                    g_keysdsel = 0;
                    g_u4_src_ck = state->src_colorkey;
                }
                else if (state->blittingflags & DSBLIT_DST_COLORKEY)
                {
                    g_keysdsel = 1;
                    g_u4_dst_ck = state->dst_colorkey;
                }
                else
                {
                    g_keysdsel = 0xff;
                    printf("ERROR: DSBLIT_SRC_COLORKEY && DSBLIT_DST_COLORKEY\n");
                    ASSERT(0);
                }

                funcs->Blit = mt85ColorKeyBlit;
                funcs->StretchBlit = mt85ColorKeyStretchBlit;
            }
            else
            {
        #if DFB_SUPPORT_GFX_ADAPTER
                // Normal Bitblt/StretchBitblt
                mdev->color.a = 0xff;

            #if ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8520) || (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530))
                if((state->source->config.caps & DSCAPS_PREMULTIPLIED) == 
                   (state->destination->config.caps & DSCAPS_PREMULTIPLIED))
                {
                    funcs->Blit = mt85Blit;
                    funcs->StretchBlit = mt85StretchBlit;
                }
                else
                {
                    printf("[DFB] Do not support this operation in MT8520 | MT8530 (Bitblt/StretchBitblt)\n");
                }
            #elif ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550) || (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555))
                if((state->source->config.caps & DSCAPS_PREMULTIPLIED) || 
                   (state->destination->config.caps & DSCAPS_PREMULTIPLIED))
                {
                    ui4_src_PM_flg = (state->source->config.caps & DSCAPS_PREMULTIPLIED) ? 1 : 0;
                    ui4_dst_PM_flg = (state->destination->config.caps & DSCAPS_PREMULTIPLIED) ? 1 : 0;

                    funcs->Blit = mt85BlitPM;
                    funcs->StretchBlit = mt85StretchBlitPM;
                }
                else
                {
                    /* Normal surface */
                    funcs->Blit = mt85Blit;
                    funcs->StretchBlit = mt85StretchBlit;
                }
            #endif
        #else
            #if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555)
                if((state->source->config.caps & DSCAPS_PREMULTIPLIED)
                        != (state->destination->config.caps & DSCAPS_PREMULTIPLIED))
                {
                    funcs->Blit = mt85BlitPM;
                    funcs->StretchBlit = mt85StretchBlitPM;
                }
                else
            #endif
                {
                    funcs->Blit = mt85Blit;
                    funcs->StretchBlit = mt85StretchBlit;
                }
        #endif
            }

            /*
             * 3) Tell which functions can be called without further validation, i.e. SetState()
             *
             * When the hw independent state is changed, this collection is reset.
             */
            state->set = MT85_SUPPORTED_BLITTINGFUNCTIONS;
            break;

        default:
            D_BUG( "unexpected drawing/blitting function" );
            break;
    }

    /*
     * 4) Clear modification flags
     *
     * All flags have been evaluated in 1) and remembered for further validation.
     * If the hw independent state is not modified, this function won't get called
     * for subsequent rendering functions, unless they aren't defined by 3).
     */
    state->mod_hw = 0;
}

#if !(DFB_SUPPORT_GFX_ADAPTER)
/*
 * Render a filled rectangle using the current hardware state.
 */
static bool
mt85FillRectangle( void *drv, void *dev, DFBRectangle *rect )
{
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;
    struct mt85fb_fillrect t_arg;

#if 0
    D_INFO( "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );

    D_DEBUG_AT( MT85_2D, "%s( %d,%d-%dx%d )\n", __FUNCTION__, DFB_RECTANGLE_VALS( rect ) );

    D_INFO( "MediaTek/Driver: %s: #%d: mdev->dst_phys:=0x%lx\n", __FUNCTION__, __LINE__, mdev->dst_phys );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->dst_addr:=0x%lx\n", __FUNCTION__, __LINE__, (long unsigned int)(mdev->dst_addr) );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->src_phys:=0x%lx\n", __FUNCTION__, __LINE__, mdev->src_phys );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->src_addr:=0x%lx\n", __FUNCTION__, __LINE__, (long unsigned int)(mdev->src_addr) );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.a:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.a );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.r:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.r );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.g:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.g );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.b:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.b );

    D_INFO( "MediaTek/Driver: %s: #%d: rect.x=%d\n", __FUNCTION__, __LINE__, rect->x );
    D_INFO( "MediaTek/Driver: %s: #%d: rect.y=%d\n", __FUNCTION__, __LINE__, rect->y );
    D_INFO( "MediaTek/Driver: %s: #%d: rect.w=%d\n", __FUNCTION__, __LINE__, rect->w );
    D_INFO( "MediaTek/Driver: %s: #%d: rect.h=%d\n\n", __FUNCTION__, __LINE__, rect->h );
#endif

    t_arg.u2DstX = rect->x;
    t_arg.u2DstY = rect->y;
    t_arg.u2Width = rect->w;
    t_arg.u2Height = rect->h;

    t_arg.u2DstPitch = mdev->dst_pitch;
    t_arg.pu1DstBaseAddr = mdev->dst_phys;
    t_arg.i4Bpp = mdev->dst_bpp;
    //t_arg.i4Color = MT85_DFBColor_TO_ARGB(mdev->color);
    //D_INFO( "mt85FillRectangle():t_arg.pu1DstBaseAddr = 0x%08x\n", t_arg.pu1DstBaseAddr);
    
#if 1
    switch(mdev->dst_format)
    {
    case DSPF_ARGB4444:
    t_arg.i4Color = MT85_DFBColor_TO_ARGB4444(mdev->color);
    //D_INFO( "mt85FillRectangle(): i4color=0x%08x, color mode= DSPF_ARBG4444\n",t_arg.i4Color);
    break;
    
    case DSPF_ARGB:
    t_arg.i4Color = MT85_DFBColor_TO_ARGB(mdev->color);
    //D_INFO( "mt85FillRectangle(): i4color=0x%08x, color mode= DSPF_ARBG\n",t_arg.i4Color);
    break;

    default:
    break;
    }
#endif
    t_arg.i4ColorMode = _mapColorFormat(mdev->dst_format);


    
/*
    D_INFO( "mt85FillRectangle(): A=0x%02x, R=0x%02x, G = 0x%02x, B=0x%02x\n",
                            mdev->color.a,
                            mdev->color.r,
                            mdev->color.g,
                            mdev->color.b);
                            */

    //D_INFO( "mt85FillRectangle(): dst pixel format=%d,dst_pitch = %d,dst_bpp=%d,dst_phys=0x%08x\n",
    //       mdev->dst_format,mdev->dst_pitch,mdev->dst_bpp,mdev->dst_phys);

    if (ioctl( mdrv->fd, FBIO_RECTFILL, &t_arg ) < 0)
       D_PERROR( "MediaTek/Driver: FBIO_RECTFILL failed!\n" );

    return true;
}

static bool
mt85FillRectangleBlend( void *drv, void *dev, DFBRectangle *rect )
{
    return mt85FillRectangle(drv, dev, rect);
}

static bool
mt85DrawRectangle( void *drv, void *dev, DFBRectangle *rect )
{
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;
    struct mt85fb_DrawRect t_arg;

#if 0
    D_INFO( "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );

    D_DEBUG_AT( MT85_2D, "%s( %d,%d-%dx%d )\n", __FUNCTION__, DFB_RECTANGLE_VALS( rect ) );

    D_INFO( "MediaTek/Driver: %s: #%d: mdev->dst_phys:=0x%lx\n", __FUNCTION__, __LINE__, mdev->dst_phys );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->dst_addr:=0x%lx\n", __FUNCTION__, __LINE__, (long unsigned int)(mdev->dst_addr) );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->src_phys:=0x%lx\n", __FUNCTION__, __LINE__, mdev->src_phys );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->src_addr:=0x%lx\n", __FUNCTION__, __LINE__, (long unsigned int)(mdev->src_addr) );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.a:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.a );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.r:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.r );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.g:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.g );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.b:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.b );

    D_INFO( "MediaTek/Driver: %s: #%d: rect.x=%d\n", __FUNCTION__, __LINE__, rect->x );
    D_INFO( "MediaTek/Driver: %s: #%d: rect.y=%d\n", __FUNCTION__, __LINE__, rect->y );
    D_INFO( "MediaTek/Driver: %s: #%d: rect.w=%d\n", __FUNCTION__, __LINE__, rect->w );
    D_INFO( "MediaTek/Driver: %s: #%d: rect.h=%d\n\n", __FUNCTION__, __LINE__, rect->h );
#endif

    t_arg.u2DstX = rect->x;
    t_arg.u2DstY = rect->y;
    t_arg.u2Width = rect->w;
    t_arg.u2Height = rect->h;

    t_arg.u2DstPitch = mdev->dst_pitch;
    t_arg.pu1DstBaseAddr = mdev->dst_phys;
    //t_arg.u4Color = MT85_DFBColor_TO_ARGB(mdev->color);
    t_arg.u4Bpp = mdev->dst_bpp;
    t_arg.u4BoarderWidth = 1; /*TBD*/
    
#if 1
    switch(mdev->dst_format)
    {
    case DSPF_ARGB4444:
    t_arg.u4Color = MT85_DFBColor_TO_ARGB4444(mdev->color);
    break;
    
    case DSPF_ARGB:
    t_arg.u4Color = MT85_DFBColor_TO_ARGB(mdev->color);
    break;

    default:
    break;
    }
#endif
    t_arg.i4ColorMode = _mapColorFormat(mdev->dst_format);
    
    //D_INFO( "mt85DrawRectangle(): dst pixel format=%d,dst_pitch = %d,dst_bpp=%d,dst_phys=0x%08x\n",
    //       mdev->dst_format,mdev->dst_pitch,mdev->dst_bpp,mdev->dst_phys);

    if (ioctl( mdrv->fd, FBIO_DRAWRECT, &t_arg ) < 0)
       D_PERROR( "MediaTek/Driver: FBIO_RECTFILL failed!\n" );

    return true;
}

static bool
mt85DrawLine( void *drv, void *dev, DFBRegion *line )
{
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;
    struct mt85fb_DrawLine t_arg;

#if 0
    D_INFO( "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );
#endif

    t_arg.u2X1 = line->x1;
    t_arg.u2Y1 = line->y1;
    t_arg.u2X2 = line->x2;
    t_arg.u2Y2 = line->y2;

    t_arg.u2DstPitch = mdev->dst_pitch;
    t_arg.pu1DstBaseAddr = mdev->dst_phys;
    //t_arg.u4color = MT85_DFBColor_TO_ARGB(mdev->color);
    t_arg.u4Bpp = mdev->dst_bpp;
#if 1
    switch(mdev->dst_format)
    {
    case DSPF_ARGB4444:
    t_arg.u4color = MT85_DFBColor_TO_ARGB4444(mdev->color);
    break;
    
    case DSPF_ARGB:
    t_arg.u4color = MT85_DFBColor_TO_ARGB(mdev->color);
    break;

    default:
    break;
    }
#endif
    t_arg.i4ColorMode = _mapColorFormat(mdev->dst_format);
    //D_INFO( "mt85DrawLine(): dst pixel format=%d,dst_pitch = %d,dst_bpp=%d,dst_phys=0x%08x\n",
    //       mdev->dst_format,mdev->dst_pitch,mdev->dst_bpp,mdev->dst_phys);

    if (ioctl( mdrv->fd, FBIO_DRAWLINE, &t_arg ) < 0)
    	  D_PERROR( "MediaTek/Driver: FBIO_RECTFILL failed!\n" );

    return true;
}

/*
 * Copy a rectangle using the current hardware state.
 */
static bool
mt85Blit( void *drv, void *dev, DFBRectangle *srect, int dx, int dy )
{
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;
    struct mt85fb_copyarearop t_arg;

#if 0
    D_INFO( "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );

    D_DEBUG_AT( MT85_2D, "%s( %d,%d-%dx%d -> %d, %d )\n", __FUNCTION__,
          DFB_RECTANGLE_VALS( srect ), dx, dy );

    D_INFO( "MediaTek/Driver: %s: #%d: mdev->dst_phys:=0x%lx\n", __FUNCTION__, __LINE__, mdev->dst_phys );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->dst_addr:=0x%lx\n", __FUNCTION__, __LINE__, (long unsigned int)(mdev->dst_addr) );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->src_phys:=0x%lx\n", __FUNCTION__, __LINE__, mdev->src_phys );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->src_addr:=0x%lx\n", __FUNCTION__, __LINE__, (long unsigned int)(mdev->src_addr) );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.a:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.a );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.r:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.r );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.g:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.g );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.b:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.b );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->src_pitch:=%d\n", __FUNCTION__, __LINE__, mdev->src_pitch );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->dst_pitch:=%d\n", __FUNCTION__, __LINE__, mdev->dst_pitch );

    D_INFO( "MediaTek/Driver: %s: #%d: srect.x=%d\n", __FUNCTION__, __LINE__, srect->x );
    D_INFO( "MediaTek/Driver: %s: #%d: srect.y=%d\n", __FUNCTION__, __LINE__, srect->y );
    D_INFO( "MediaTek/Driver: %s: #%d: srect.w=%d\n", __FUNCTION__, __LINE__, srect->w );
    D_INFO( "MediaTek/Driver: %s: #%d: srect.h=%d\n", __FUNCTION__, __LINE__, srect->h );

    D_INFO( "MediaTek/Driver: %s: #%d: dx=%d\n", __FUNCTION__, __LINE__, dx );
    D_INFO( "MediaTek/Driver: %s: #%d: dy=%d\n\n", __FUNCTION__, __LINE__, dy );
#endif

    t_arg.dx = dx;
    t_arg.dy = dy;
    t_arg.width = srect->w;
    t_arg.height = srect->h;
    t_arg.sx = srect->x;
    t_arg.sy = srect->y;
    t_arg.rop = 0; /*no use*/
    t_arg.alpha = mdev->color.a;
    t_arg.dst_phys = mdev->dst_phys;
    t_arg.src_phys = mdev->src_phys;
    t_arg.u2SrcPitch = mdev->src_pitch;
    t_arg.u2DstPitch = mdev->dst_pitch;

    t_arg.i4SrcColorMode = _mapColorFormat(mdev->src_format);
    t_arg.i4DstColorMode = _mapColorFormat(mdev->dst_format);

    if (t_arg.i4SrcColorMode != t_arg.i4DstColorMode)
    {
        /* Todo: color format of src and dst are different */
        return false;
    }

    if (ioctl( mdrv->fd, FBIO_COPYAREA, &t_arg ) < 0)
        D_PERROR( "MediaTek/Driver: FBIO_RECTFILL failed!\n" );

    return true;
}

static bool
mt85BlitBlend( void *drv, void *dev, DFBRectangle *srect, int dx, int dy )
{
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;
    struct mt85fb_copyarearop t_arg;

#if 0
    D_INFO( "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );

    D_DEBUG_AT( MT85_2D, "%s( %d,%d-%dx%d -> %d, %d )\n", __FUNCTION__,
          DFB_RECTANGLE_VALS( srect ), dx, dy );

    D_INFO( "MediaTek/Driver: %s: #%d: mdev->dst_phys:=0x%lx\n", __FUNCTION__, __LINE__, mdev->dst_phys );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->dst_addr:=0x%lx\n", __FUNCTION__, __LINE__, (long unsigned int)(mdev->dst_addr) );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->src_phys:=0x%lx\n", __FUNCTION__, __LINE__, mdev->src_phys );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->src_addr:=0x%lx\n", __FUNCTION__, __LINE__, (long unsigned int)(mdev->src_addr) );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.a:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.a );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.r:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.r );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.g:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.g );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.b:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.b );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->src_pitch:=%d\n", __FUNCTION__, __LINE__, mdev->src_pitch );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->dst_pitch:=%d\n", __FUNCTION__, __LINE__, mdev->dst_pitch );

    D_INFO( "MediaTek/Driver: %s: #%d: srect.x=%d\n", __FUNCTION__, __LINE__, srect->x );
    D_INFO( "MediaTek/Driver: %s: #%d: srect.y=%d\n", __FUNCTION__, __LINE__, srect->y );
    D_INFO( "MediaTek/Driver: %s: #%d: srect.w=%d\n", __FUNCTION__, __LINE__, srect->w );
    D_INFO( "MediaTek/Driver: %s: #%d: srect.h=%d\n", __FUNCTION__, __LINE__, srect->h );

    D_INFO( "MediaTek/Driver: %s: #%d: dx=%d\n", __FUNCTION__, __LINE__, dx );
    D_INFO( "MediaTek/Driver: %s: #%d: dy=%d\n\n", __FUNCTION__, __LINE__, dy );
#endif

    t_arg.dx = dx;
    t_arg.dy = dy;
    t_arg.width = srect->w;
    t_arg.height = srect->h;
    t_arg.sx = srect->x;
    t_arg.sy = srect->y;
    t_arg.rop = 0; /*no use*/
    t_arg.alpha = mdev->color.a;
    t_arg.dst_phys = mdev->dst_phys;
    t_arg.src_phys = mdev->src_phys;
    t_arg.u2SrcPitch = mdev->src_pitch;
    t_arg.u2DstPitch = mdev->dst_pitch;

    t_arg.i4SrcColorMode = _mapColorFormat(mdev->src_format);
    t_arg.i4DstColorMode = _mapColorFormat(mdev->dst_format);

    if (t_arg.i4SrcColorMode != t_arg.i4DstColorMode)
    {
        /* Todo: color format of src and dst are different */
        return false;
    }

    if (ioctl( mdrv->fd, FBIO_BLITBLEND, &t_arg ) < 0)
        D_PERROR( "MediaTek/Driver: FBIO_RECTFILL failed!\n" );

    return true;
}

/*
 * Stretch a rectangle using the current hardware state.
 */
static bool
mt85StretchBlit( void *drv, void *dev, DFBRectangle *srect, DFBRectangle *drect )
{
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;
    struct mt85fb_stretchblit t_arg;

#if 0
    D_INFO( "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );

    D_INFO( "MediaTek/Driver: %s: #%d: mdev->dst_phys:=0x%lx\n", __FUNCTION__, __LINE__, mdev->dst_phys );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->dst_addr:=0x%lx\n", __FUNCTION__, __LINE__, (long unsigned int)(mdev->dst_addr) );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->src_phys:=0x%lx\n", __FUNCTION__, __LINE__, mdev->src_phys );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->src_addr:=0x%lx\n", __FUNCTION__, __LINE__, (long unsigned int)(mdev->src_addr) );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.a:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.a );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.r:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.r );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.g:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.g );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.b:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.b );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->src_pitch:=%d\n", __FUNCTION__, __LINE__, mdev->src_pitch );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->dst_pitch:=%d\n", __FUNCTION__, __LINE__, mdev->dst_pitch );

    D_INFO( "MediaTek/Driver: %s: #%d: srect.x=%d\n", __FUNCTION__, __LINE__, srect->x );
    D_INFO( "MediaTek/Driver: %s: #%d: srect.y=%d\n", __FUNCTION__, __LINE__, srect->y );
    D_INFO( "MediaTek/Driver: %s: #%d: srect.w=%d\n", __FUNCTION__, __LINE__, srect->w );
    D_INFO( "MediaTek/Driver: %s: #%d: srect.h=%d\n", __FUNCTION__, __LINE__, srect->h );

    D_INFO( "MediaTek/Driver: %s: #%d: drect.x=%d\n", __FUNCTION__, __LINE__, drect->x );
    D_INFO( "MediaTek/Driver: %s: #%d: drect.y=%d\n", __FUNCTION__, __LINE__, drect->y );
    D_INFO( "MediaTek/Driver: %s: #%d: drect.w=%d\n", __FUNCTION__, __LINE__, drect->w );
    D_INFO( "MediaTek/Driver: %s: #%d: drect.h=%d\n\n", __FUNCTION__, __LINE__, drect->h );
#endif

    t_arg.sx = srect->x;
    t_arg.sy = srect->y;
    t_arg.sw = srect->w;
    t_arg.sh = srect->h;
    t_arg.dx = drect->x;
    t_arg.dy = drect->y;
    t_arg.dw = drect->w;
    t_arg.dh = drect->h;
    t_arg.rop = 0; /*no use*/
    t_arg.alpha = mdev->color.a;
    t_arg.dst_phys = mdev->dst_phys;
    t_arg.src_phys = mdev->src_phys;
    t_arg.u2SrcPitch = mdev->src_pitch;
    t_arg.u2DstPitch = mdev->dst_pitch;

    t_arg.i4SrcColorMode = _mapColorFormat(mdev->src_format);
    t_arg.i4DstColorMode = _mapColorFormat(mdev->dst_format);

    if (t_arg.i4SrcColorMode != t_arg.i4DstColorMode)
    {
        /* Todo: color format of src and dst are different */
        return false;
    }

    if (ioctl( mdrv->fd, FBIO_STRETCHBLIT, &t_arg ) < 0)
        D_PERROR( "MediaTek/Driver: FBIO_RECTFILL failed!\n" );

    return true;
}

static bool
mt85StretchBlitBlend( void *drv, void *dev, DFBRectangle *srect, DFBRectangle *drect )
{
    return mt85StretchBlit(drv, dev, srect, drect);
}

#if ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550) || (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555))

static bool
mt85FillRectanglePM( void *drv, void *dev, DFBRectangle *rect )
{
    return mt85FillRectangle(drv, dev, rect);
}

static bool
mt85FillRectangleBlendPM( void *drv, void *dev, DFBRectangle *rect )
{
    return mt85FillRectangleBlend(drv, dev, rect);
}

static bool
mt85BlitPM( void *drv, void *dev, DFBRectangle *srect, int dx, int dy )
{
    return mt85Blit(drv, dev, srect, dx, dy);
}

static bool
mt85BlitBlendPM( void *drv, void *dev, DFBRectangle *srect, int dx, int dy )
{
    return mt85Blit(drv, dev, srect, dx, dy);
}

static bool
mt85StretchBlitPM( void *drv, void *dev, DFBRectangle *srect, DFBRectangle *drect )
{
    return mt85StretchBlit(drv, dev, srect, drect);
}

static bool
mt85StretchBlitBlendPM( void *drv, void *dev, DFBRectangle *srect, DFBRectangle *drect )
{
    return mt85StretchBlitBlend(drv, dev, srect, drect);
}

#endif


#else

#define DFB_NOWAIT         ((UINT32) 0x00000000)
#define DFB_WAIT           ((UINT32) 0x00000001)
#define DFB_FLAG           ((UINT32) 0x00000001)
#define DFB_MODULE_ID        ((UINT32) 0x12345678)
#define DFB_COMPRESSED        ((UINT32) 0x00000000)

static UINT32        _fb_Comp_Color = 0xFFFFFFFF;
static BOOL          _b_DfbRectSrcOpt = TRUE;
static GFX_PD_RULE_T    _e_PdRule = GFX_SRC_OVER;

extern UINT32        _h_mt85_Ticket;

// API -- add cmd to cmd buf in user space

static INT32 mt85AdapterCmdBufSetCmd(MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf,  const void *prGfxCmd, GFX_BUFF_CMD_TYPE_T eCmdType, BOOL bSyncEn, MT85DriverData *mdrv)
{
    INT32 i4Ret = 1;
    GFX_BUFF_CMD_ITEM_T* pu4CmdQue = NULL;
    GFX_LINUX_CMDBUF_T* prBufFifo = NULL;
    UINT32 u4FreeSpace = 0;

    prBufFifo = prGfxCmdBuf->prGfxMwCmdBuf;
    prBufFifo->u4GfxCmdBufCompress = prGfxCmdBuf->fg_compress;

    if (prBufFifo->u4WrPoint >= prBufFifo->u4RdPoint)
    {
        u4FreeSpace = prBufFifo->u4GfxCmdMaxCount - prBufFifo->u4WrPoint + prBufFifo->u4RdPoint;
    }
    else if (prBufFifo->u4WrPoint < prBufFifo->u4RdPoint)
    {
        u4FreeSpace = prBufFifo->u4RdPoint - prBufFifo->u4WrPoint;
    }

    pu4CmdQue = (GFX_BUFF_CMD_ITEM_T *)(prBufFifo + 1) + prBufFifo->u4WrPoint;

#if GFX_DRV_CMDBUF_DYNAMIC_SIZE
    if (((prBufFifo->u4WrPoint + 1) % prBufFifo->u4GfxCmdMaxCount) != prBufFifo->u4RdPoint)
#else
    if (((prBufFifo->u4WrPoint + 1) % MAX_BUFF_SIZE) != prBufFifo->u4RdPoint)
#endif
    {
        pu4CmdQue->u4CmdType = eCmdType;

        if(bSyncEn)
        {
            pu4CmdQue->u4FlushType = GFX_DRV_FORCE_FLUSH;
        }
        else
        {
            pu4CmdQue->u4FlushType = GFX_DRV_AUTO_FLUSH;	
        }

        switch (eCmdType)
        {
            case GFX_BUFF_TYPE_FILL_RECT:
                memcpy((void *)pu4CmdQue->u4CmdBuff, prGfxCmd, sizeof(GFX_FILL_T));
                break;

            case GFX_BUFF_TYPE_BITBLT:
                memcpy((void *)pu4CmdQue->u4CmdBuff, prGfxCmd, sizeof(GFX_BITBLT_T));
                break;

            case GFX_BUFF_TYPE_FLUSH:
                pu4CmdQue->u4CmdType = GFX_BUFF_TYPE_FLUSH;
                pu4CmdQue->u4FlushType = GFX_DRV_FORCE_FLUSH; 
                break;

            case GFX_BUFF_TYPE_STRETCH_BITBLT:
            case GFX_BUFF_TYPE_ADV_STRETCH_BITBLT:
                memcpy((void *)pu4CmdQue->u4CmdBuff, prGfxCmd, sizeof(GFX_STRETCH_BITBLT_T));  
                break;

            case GFX_BUFF_TYPE_ALPHA_COMPOSITION:
                memcpy((void *)pu4CmdQue->u4CmdBuff, prGfxCmd, sizeof(GFX_ALPHA_COMPOSITION_T));
                break;

            case GFX_BUFF_TYPE_STRETCH_COMPOSITION:
                memcpy((void *)pu4CmdQue->u4CmdBuff, prGfxCmd, sizeof(GFX_ROTATE_STRETCH_ALPHACOMP_T));  
                break;

            case GFX_BUFF_TYPE_FILL_RECT_BLEND:
                memcpy((void *)pu4CmdQue->u4CmdBuff, prGfxCmd, sizeof(GFX_FILL_BLEND_T));  
                break;

            case GFX_BUFF_TYPE_STRETCH_BITBLT_BLEND:
                memcpy((void *)pu4CmdQue->u4CmdBuff, prGfxCmd, sizeof(GFX_ROTATE_STRETCH_ALPHACOMP_T));  
                break;

            case GFX_BUFF_TYPE_Y2R_BITBLT:
                memcpy((void *)pu4CmdQue->u4CmdBuff, prGfxCmd, sizeof(GFX_YCBCR_TO_RGB_T));  
                break;

            case GFX_BUFF_TYPE_COLORKEYBITBLT:
                memcpy((void *)pu4CmdQue->u4CmdBuff, prGfxCmd, sizeof(GFX_COLORKEY_BITBLT_T));  
                break;

            case GFX_BUFF_TYPE_COLORKEY_STRETCH_BITBLT:
                memcpy((void *)pu4CmdQue->u4CmdBuff, prGfxCmd, sizeof(GFX_COLORKEY_STRETCH_BITBLT_T));  
                break;

        #if ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550) || (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555))
            case GFX_BUFF_TYPE_PM_FILL_RECT:
                memcpy((void *)pu4CmdQue->u4CmdBuff, prGfxCmd, sizeof(GFX_FILL_T));  
                break;

            case GFX_BUFF_TYPE_PM_FILL_RECT_BLEND:
                memcpy((void *)pu4CmdQue->u4CmdBuff, prGfxCmd, sizeof(GFX_FILL_BLEND_T));  
                break;

            case GFX_BUFF_TYPE_PM_BITBLT:
                memcpy((void *)pu4CmdQue->u4CmdBuff, prGfxCmd, sizeof(GFX_PREMULT_BITBLT_T));  
                break;

            case GFX_BUFF_TYPE_PM_ALPHA_COMPOSITION:
                memcpy((void *)pu4CmdQue->u4CmdBuff, prGfxCmd, sizeof(GFX_PREMULT_ALPHA_COMPOSITION_T));  
                break;

            case GFX_BUFF_TYPE_PM_STRETCH_BITBLT:
                memcpy((void *)pu4CmdQue->u4CmdBuff, prGfxCmd, sizeof(GFX_PREMULT_STRETCH_BITBLT_T));  
                break;

            case GFX_BUFF_TYPE_PM_STRETCH_BITBLT_BLEND:
                memcpy((void *)pu4CmdQue->u4CmdBuff, prGfxCmd, sizeof(GFX_PREMULT_STRETCH_BITBLT_BLEND_T));  
                break;
        #endif

            default :
                ASSERT(0);
                break;
        }

    #if GFX_DRV_CMDBUF_DYNAMIC_SIZE
        if ((prBufFifo->u4WrPoint + 1) < prBufFifo->u4GfxCmdMaxCount)
    #else
        if ((prBufFifo->u4WrPoint + 1) < MAX_BUFF_SIZE)
    #endif
        {
            prBufFifo->u4WrPoint++;
        }
        else
        {
            prBufFifo->u4WrPoint = 0;
        }

        if (bSyncEn || (u4FreeSpace == prBufFifo->u4GfxCmdMaxCount))
        {
            if (ioctl(mdrv->fd, FBIO_DISPATCHHW, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
            {
                D_PERROR( "MediaTek/Driver: FBIO_DISPATCHHW failed!\n" );
                return -1;
            }
        }
        else if(u4FreeSpace != prBufFifo->u4GfxCmdMaxCount)
        {
            // If GfxDrv is sleeping now, we should wake it up
            if (GFX_CMDBUF_STATE_ACTIVE == prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState)
            {
                if (ioctl(mdrv->fd, FBIO_DISPATCHHW, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
                {
                    D_PERROR( "MediaTek/Driver: FBIO_DISPATCHHW failed!\n" );
                    return -1;
                }
            }
        }

        i4Ret = 1;
    }
    else
    {
        if(prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState != GFX_CMDBUF_STATE_INFLUSH)
        {
            if (ioctl(mdrv->fd, FBIO_DISPATCHHW, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
            {
                D_PERROR( "MediaTek/Driver: FBIO_DISPATCHHW failed!\n" );
                return -1;
            }
        }
    
        i4Ret = -1;
    }

    return i4Ret;
}

static INT32 mt85AdapterCmdBufAddCmd(MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf, const void *prGfxCmd, GFX_BUFF_CMD_TYPE_T  eCmdType, BOOL bSyncEn, MT85DriverData *mdrv)
{
    static UINT32 u4LastCmdType = 0;
    INT32 u4Ret = 0xffffffff;

    if ((u4LastCmdType == GFX_BUFF_TYPE_FLUSH) && (eCmdType == GFX_BUFF_TYPE_FLUSH))
    {
        return u4Ret;
    }
    u4LastCmdType = eCmdType;

    while ((u4Ret = mt85AdapterCmdBufSetCmd(prGfxCmdBuf, prGfxCmd, eCmdType,bSyncEn, mdrv)) < 0)
    {
        usleep(1);
    }

    return u4Ret;
}

/*
static void mt85AdapterCmdBufCleanBuf(MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf)
{
    INT32            i4Ret = 0;
    GFX_LINUX_CMDBUF_T*    prBufFifo = prGfxCmdBuf->prGfxMwCmdBuf;

    prBufFifo->u4RdPoint = prBufFifo->u4WrPoint;

    while (mt85AdapterCmdBufSetCmd(prGfxCmdBuf, NULL, GFX_BUFF_TYPE_STOP, FALSE) < 0)
    {
       x_thread_delay(5);
    }
}*/

static GFX_COLORMODE_T mt85CmChg(DFBSurfacePixelFormat eDfbCm)
{
    GFX_COLORMODE_T eGfxCm = 0;

    switch(eDfbCm)
    {
    case DSPF_ARGB4444:
       eGfxCm = GFX_COLORMODE_ARGB_D4444;
       break;
    case DSPF_ARGB:
       eGfxCm = GFX_COLORMODE_ARGB_D8888;
       break;
    case DSPF_RGB16:
       eGfxCm = GFX_COLORMODE_RGB_D565;
       break;
    case DSPF_ARGB1555:
       eGfxCm = GFX_COLORMODE_ARGB_D1555;
       break;
    case DSPF_LUT8:
       eGfxCm = GFX_COLORMODE_ARGB_CLUT8;
       break;
    default:
       eGfxCm = 0;
       printf("[dfb] mt85CmChg error eDfbCm = %d\n", eDfbCm);
       break;
    }

    return (eGfxCm);
}

#if ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8520) || (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530))
static bool mt85PseudFill(void *drv, void *dev, DFBRectangle *rect)
{
    printf("$$$$$$$$$$$[%s]$$$$$$$$$$$$$\n", __FUNCTION__);
    return true;
}
#endif

/*
 * Render a filled rectangle using the current hardware state.
 */
static bool mt85FillRectangle(void *drv, void *dev, DFBRectangle *rect)
{
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;

    GFX_FILL_T rFill = {0};
    MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf = (MT85_ADAPTER_CMDBUF_T *)_h_mt85_Ticket;
    ASSERT(prGfxCmdBuf != NULL);
    ASSERT(mdrv && mdev && rect);

#if MT85_2D_LOG
    printf("[%s] x=%d,y=%d,w=%d,h=%d,color=0x%X\n", __FUNCTION__, rect->x, rect->y, rect->w, rect->h,MT85_DFBColor_TO_ARGB(mdev->color));
#endif
    rFill.u4TicketId = _h_mt85_Ticket;
    rFill.u4Flag = DFB_FLAG;
    rFill.pv_dst = (void*)mdev->dst_phys;
    ASSERT(rFill.pv_dst);
    rFill.pv_dst2 = NULL;
    rFill.i4_dst_x = rect->x;
    rFill.i4_dst_y = rect->y;
    rFill.ui4_dst_pitch = mdev->dst_pitch;
    rFill.ui4_dst_pitch2 = 0;
    rFill.e_dst_cm = mt85CmChg(mdev->dst_format);
    rFill.b_compressed = DFB_COMPRESSED;
    rFill.ui4_width = rect->w;
    rFill.ui4_height = rect->h;
    switch(rFill.e_dst_cm)
    {
        case GFX_COLORMODE_ARGB_D4444:
            rFill.ui4_color = MT85_DFBColor_TO_ARGB4444(mdev->color);
            break;
        
        case GFX_COLORMODE_ARGB_D8888:
            rFill.ui4_color = MT85_DFBColor_TO_ARGB(mdev->color);
            break;

        default:
            break;
    }
    rFill.ui4ModuleID = DFB_MODULE_ID;

    if(!prGfxCmdBuf->fg_compress)
    {
        prGfxCmdBuf->fg_compress = rFill.b_compressed;
    }

    if (((GFX_CMDBUF_STATE)(prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState)) >= GFX_CMDBUF_STATE_MAX)
    {
        ASSERT(0);
    }
    else
    {
        mt85AdapterCmdBufAddCmd(prGfxCmdBuf, (void*)(&rFill), GFX_BUFF_TYPE_FILL_RECT, DFB_NOWAIT, mdrv);

        if (DFB_NOWAIT)
        {
            if (ioctl(mdrv->fd, FBIO_CMDBUFWAITDONE, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
            {
                D_PERROR( "MediaTek/Driver: FBIO_CMDBUFWAITDONE failed!\n" );
                return false;
            }
        }
    }

    return true;
}

static bool
mt85FillRectangleBlend( void *drv, void *dev, DFBRectangle *rect )
{
    /* Todo: fillrect blend */
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;

    GFX_FILL_BLEND_T rFillBlend = {0};
    MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf = (MT85_ADAPTER_CMDBUF_T *)_h_mt85_Ticket;
    ASSERT(prGfxCmdBuf != NULL);
    ASSERT(mdrv && mdev && rect);

#if MT85_2D_LOG
    printf("[%s] x=%d,y=%d,w=%d,h=%d,color=0x%X\n", __FUNCTION__, rect->x, rect->y, rect->w, rect->h,MT85_DFBColor_TO_ARGB(mdev->color));
#endif
    rFillBlend.u4TicketId = _h_mt85_Ticket;
    rFillBlend.u4Flag = DFB_FLAG;
    rFillBlend.pv_dst = (void*)mdev->dst_phys;
    ASSERT(rFillBlend.pv_dst);
    rFillBlend.pv_dst2 = NULL;
    rFillBlend.i4_dst_x = rect->x;
    rFillBlend.i4_dst_y = rect->y;
    rFillBlend.ui4_dst_pitch = mdev->dst_pitch;
    rFillBlend.ui4_dst_pitch2 = 0;
    rFillBlend.e_dst_cm = mt85CmChg(mdev->dst_format);
    rFillBlend.b_compressed = DFB_COMPRESSED;
    rFillBlend.ui4_width = rect->w;
    rFillBlend.ui4_height = rect->h;

    switch(mdev->dst_format)
    {
        case DSPF_ARGB4444:
            rFillBlend.ui4_color= MT85_DFBColor_TO_ARGB4444(mdev->color);
            break;

        case DSPF_ARGB:
    rFillBlend.ui4_color = MT85_DFBColor_TO_ARGB(mdev->color);
            break;

        default:
            break;
    }
//    rFillBlend.ui4_color = MT85_DFBColor_TO_ARGB(mdev->color);
    rFillBlend.ui4ModuleID = DFB_MODULE_ID;
    rFillBlend.ui1_alpha = mdev->color.a;

    if(!prGfxCmdBuf->fg_compress)
    {
        prGfxCmdBuf->fg_compress = rFillBlend.b_compressed;
    }

    if (((GFX_CMDBUF_STATE)(prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState)) >= GFX_CMDBUF_STATE_MAX)
    {
        ASSERT(0);
    }
    else
    {
        mt85AdapterCmdBufAddCmd(prGfxCmdBuf, (void*)(&rFillBlend), GFX_BUFF_TYPE_FILL_RECT_BLEND, DFB_NOWAIT, mdrv);

        if (DFB_NOWAIT)
        {
            if (ioctl(mdrv->fd, FBIO_CMDBUFWAITDONE, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
            {
                D_PERROR( "MediaTek/Driver: FBIO_CMDBUFWAITDONE failed!\n" );
                return false;
            }
        }
    }

    return true;
}

static bool
mt85DrawRectangle( void *drv, void *dev, DFBRectangle *rect )
{
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;
    struct mt85fb_DrawRect t_arg;

    ASSERT(mdrv && mdev && rect);
#if 0
    D_INFO( "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );

    D_DEBUG_AT( MT85_2D, "%s( %d,%d-%dx%d )\n", __FUNCTION__, DFB_RECTANGLE_VALS( rect ) );

    D_INFO( "MediaTek/Driver: %s: #%d: mdev->dst_phys:=0x%lx\n", __FUNCTION__, __LINE__, mdev->dst_phys );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->dst_addr:=0x%lx\n", __FUNCTION__, __LINE__, (long unsigned int)(mdev->dst_addr) );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->src_phys:=0x%lx\n", __FUNCTION__, __LINE__, mdev->src_phys );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->src_addr:=0x%lx\n", __FUNCTION__, __LINE__, (long unsigned int)(mdev->src_addr) );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.a:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.a );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.r:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.r );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.g:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.g );
    D_INFO( "MediaTek/Driver: %s: #%d: mdev->color.b:=0x%x\n", __FUNCTION__, __LINE__, mdev->color.b );

    D_INFO( "MediaTek/Driver: %s: #%d: rect.x=%d\n", __FUNCTION__, __LINE__, rect->x );
    D_INFO( "MediaTek/Driver: %s: #%d: rect.y=%d\n", __FUNCTION__, __LINE__, rect->y );
    D_INFO( "MediaTek/Driver: %s: #%d: rect.w=%d\n", __FUNCTION__, __LINE__, rect->w );
    D_INFO( "MediaTek/Driver: %s: #%d: rect.h=%d\n\n", __FUNCTION__, __LINE__, rect->h );
#endif

#if MT85_2D_LOG
    printf("[%s] x=%d,y=%d,w=%d,h=%d\n", __FUNCTION__, rect->x, rect->y, rect->w, rect->h);
#endif
    t_arg.u2DstX = rect->x;
    t_arg.u2DstY = rect->y;
    t_arg.u2Width = rect->w;
    t_arg.u2Height = rect->h;

    t_arg.u2DstPitch = mdev->dst_pitch;
    t_arg.pu1DstBaseAddr = (unsigned char*)mdev->dst_phys;
    //t_arg.u4Color = MT85_DFBColor_TO_ARGB(mdev->color);
    t_arg.u4Bpp = mdev->dst_bpp;
    t_arg.u4BoarderWidth = 1; /*TBD*/
    
#if 1
    switch(mdev->dst_format)
    {
    case DSPF_ARGB4444:
    t_arg.u4Color = MT85_DFBColor_TO_ARGB4444(mdev->color);
    break;
    
    case DSPF_ARGB:
    t_arg.u4Color = MT85_DFBColor_TO_ARGB(mdev->color);
    break;

    default:
    break;
    }
#endif
    
    //D_INFO( "mt85DrawRectangle(): dst pixel format=%d,dst_pitch = %d,dst_bpp=%d,dst_phys=0x%08x\n",
    //       mdev->dst_format,mdev->dst_pitch,mdev->dst_bpp,mdev->dst_phys);

    t_arg.i4ColorMode = _mapColorFormat(mdev->dst_format);

    if (ioctl( mdrv->fd, FBIO_DRAWRECT, &t_arg ) < 0)
    {
        D_PERROR( "MediaTek/Driver: FBIO_RECTFILL failed!\n" );
        return false;
    }

    return true;
}

static bool
mt85DrawLine( void *drv, void *dev, DFBRegion *line )
{
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;
    struct mt85fb_DrawLine t_arg;

    ASSERT(mdrv && mdev && line);
#if 0
    D_INFO( "MediaTek/Driver: %s: #%d\n", __FUNCTION__, __LINE__ );
#endif

#if MT85_2D_LOG
    printf("$$$$$$$$$$$[%s]$$$$$$$$$$$$$\n", __FUNCTION__);
#endif
    t_arg.u2X1 = line->x1;
    t_arg.u2Y1 = line->y1;
    t_arg.u2X2 = line->x2;
    t_arg.u2Y2 = line->y2;

    t_arg.u2DstPitch = mdev->dst_pitch;
    t_arg.pu1DstBaseAddr = (unsigned char*)mdev->dst_phys;
    //t_arg.u4color = MT85_DFBColor_TO_ARGB(mdev->color);
    t_arg.u4Bpp = mdev->dst_bpp;
#if 1
    switch(mdev->dst_format)
    {
    case DSPF_ARGB4444:
    t_arg.u4color = MT85_DFBColor_TO_ARGB4444(mdev->color);
    break;
    
    case DSPF_ARGB:
    t_arg.u4color = MT85_DFBColor_TO_ARGB(mdev->color);
    break;

    default:
    break;
    }
#endif
    //D_INFO( "mt85DrawLine(): dst pixel format=%d,dst_pitch = %d,dst_bpp=%d,dst_phys=0x%08x\n",
    //       mdev->dst_format,mdev->dst_pitch,mdev->dst_bpp,mdev->dst_phys);

    t_arg.i4ColorMode = _mapColorFormat(mdev->dst_format);

    if (ioctl( mdrv->fd, FBIO_DRAWLINE, &t_arg ) < 0)
    {
    	D_PERROR( "MediaTek/Driver: FBIO_RECTFILL failed!\n" );
        return false;
    }

    return true;
}

static bool mt85Blit( void *drv, void *dev, DFBRectangle *srect, int dx, int dy )
{
    /* Todo: color format of src and dst are different */
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;

    GFX_BITBLT_T rBitblt = {0};
    MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf = (MT85_ADAPTER_CMDBUF_T *)_h_mt85_Ticket;
    ASSERT(prGfxCmdBuf != NULL);
    ASSERT(mdrv && mdev && srect);

#if MT85_2D_LOG
    printf("[%s] x=%d,y=%d,w=%d,h=%d,dx=%d,dy=%d,a=%d\n", __FUNCTION__, srect->x, srect->y, srect->w, srect->h, dx, dy, mdev->color.a);
#endif
    rBitblt.u4TicketId = _h_mt85_Ticket;
    rBitblt.u4Flag = DFB_FLAG;
    rBitblt.pv_src = (void*)mdev->src_phys;
    ASSERT(rBitblt.pv_src);
    rBitblt.i4_src_x = srect->x;
    rBitblt.i4_src_y = srect->y;
    rBitblt.ui4_src_pitch = mdev->src_pitch;
    rBitblt.e_src_cm = mt85CmChg(mdev->src_format);
    rBitblt.b_src_compressed = DFB_COMPRESSED;

    rBitblt.pv_dst = (void*)mdev->dst_phys;
    ASSERT(rBitblt.pv_dst);
    rBitblt.i4_dst_x = dx;
    rBitblt.i4_dst_y = dy;
    rBitblt.ui4_dst_pitch = mdev->dst_pitch;
    rBitblt.e_dst_cm = mt85CmChg(mdev->dst_format);
    rBitblt.b_dst_compressed = DFB_COMPRESSED;

    rBitblt.ui4_width = srect->w;
    rBitblt.ui4_height = srect->h;
    rBitblt.ui4ModuleID = DFB_MODULE_ID;
    rBitblt.ui1_alpha = mdev->color.a;

    /*rBitblt.e_ycbcr_format = 
    rBitblt.pv_cbcr_or_palette = 
    rBitblt.ui4_cbcr_pitch = 
    rBitblt.ui1_ycbcr_alpha = 
    rBitblt.e_byte_aligned = 
    rBitblt.h_palette = 
    rBitblt.u4_alCom_normal = 
    rBitblt.u4_src_picth_en = */
    
    if(!prGfxCmdBuf->fg_compress && 
      (rBitblt.b_src_compressed || rBitblt.b_dst_compressed))
    {
        prGfxCmdBuf->fg_compress = TRUE;
    }

    if (((GFX_CMDBUF_STATE)(prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState)) >= GFX_CMDBUF_STATE_MAX)
    {
        ASSERT(0);
    }
    else
    {
        mt85AdapterCmdBufAddCmd(prGfxCmdBuf, (void*)(&rBitblt), GFX_BUFF_TYPE_BITBLT, DFB_NOWAIT, mdrv);

        if (DFB_NOWAIT)
        {
            if (ioctl(mdrv->fd, FBIO_CMDBUFWAITDONE, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
            {
                D_PERROR( "MediaTek/Driver: FBIO_CMDBUFWAITDONE failed!\n" );
                return false;
            }
        }
    }

    return true;
}

static bool mt85ColorKeyBlit( void *drv, void *dev, DFBRectangle *srect, int dx, int dy )
{
    /* Todo: color format of src and dst are different */
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;

    GFX_COLORKEY_BITBLT_T rBitblt = {0};
    MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf = (MT85_ADAPTER_CMDBUF_T *)_h_mt85_Ticket;
    ASSERT(prGfxCmdBuf != NULL);
    ASSERT(mdrv && mdev && srect);

#if MT85_2D_LOG
    printf("[%s] x=%d,y=%d,w=%d,h=%d,dx=%d,dy=%d,a=%d\n", __FUNCTION__, srect->x, srect->y, srect->w, srect->h, dx, dy, mdev->color.a);
#endif
    rBitblt.u4TicketId = _h_mt85_Ticket;
    rBitblt.u4Flag = DFB_FLAG;
    rBitblt.pv_src = (void*)mdev->src_phys;
    ASSERT(rBitblt.pv_src);
    rBitblt.i4_src_x = srect->x;
    rBitblt.i4_src_y = srect->y;
    rBitblt.ui4_src_pitch = mdev->src_pitch;
    rBitblt.e_src_cm = mt85CmChg(mdev->src_format);
    rBitblt.b_src_compressed = DFB_COMPRESSED;

    rBitblt.pv_dst = (void*)mdev->dst_phys;
    ASSERT(rBitblt.pv_dst);
    rBitblt.i4_dst_x = dx;
    rBitblt.i4_dst_y = dy;
    rBitblt.ui4_dst_pitch = mdev->dst_pitch;
    rBitblt.e_dst_cm = mt85CmChg(mdev->dst_format);
    rBitblt.b_dst_compressed = DFB_COMPRESSED;

    rBitblt.ui4_width = srect->w;
    rBitblt.ui4_height = srect->h;
    rBitblt.ui4ModuleID = DFB_MODULE_ID;

    rBitblt.b_key_sd_sel = g_keysdsel;
    rBitblt.ui4_color_space_min = g_keysdsel ? g_u4_dst_ck : g_u4_src_ck;
    rBitblt.ui4_color_space_max = rBitblt.ui4_color_space_min;

    /*rBitblt.e_ycbcr_format = 
    rBitblt.pv_cbcr_or_palette = 
    rBitblt.ui4_cbcr_pitch = 
    rBitblt.ui1_ycbcr_alpha = 
    rBitblt.e_byte_aligned = 
    rBitblt.h_palette = 
    rBitblt.u4_alCom_normal = 
    rBitblt.u4_src_picth_en = */
    
    if(!prGfxCmdBuf->fg_compress && 
      (rBitblt.b_src_compressed || rBitblt.b_dst_compressed))
    {
        prGfxCmdBuf->fg_compress = TRUE;
    }

    if (((GFX_CMDBUF_STATE)(prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState)) >= GFX_CMDBUF_STATE_MAX)
    {
        ASSERT(0);
    }
    else
    {
        mt85AdapterCmdBufAddCmd(prGfxCmdBuf, (void*)(&rBitblt), GFX_BUFF_TYPE_COLORKEYBITBLT, DFB_NOWAIT, mdrv);

        if (DFB_NOWAIT)
        {
            if (ioctl(mdrv->fd, FBIO_CMDBUFWAITDONE, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
            {
                D_PERROR( "MediaTek/Driver: FBIO_CMDBUFWAITDONE failed!\n" );
                return false;
            }
        }
    }

    return true;
}

static bool mt85BlitIdx( void *drv, void *dev, DFBRectangle *srect, int dx, int dy )
{
//    MT85DriverData *mdrv = (MT85DriverData*) drv;
//    MT85DeviceData *mdev = (MT85DeviceData*) dev;
//    IDirectFBPalette *palette = NULL;
//
//    GFX_YCBCR_TO_RGB_T rBitblt = {0};
//    MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf = (MT85_ADAPTER_CMDBUF_T *)_h_mt85_Ticket;
//    ASSERT(prGfxCmdBuf != NULL);
//    ASSERT(mdrv && mdev && srect);
//
//    rBitblt.ui1_alpha = mdev->color.a;
//    rBitblt.u4TicketId = _h_mt85_Ticket;
//    rBitblt.u4Flag = DFB_FLAG;
//    rBitblt.ui4ModuleID = DFB_MODULE_ID;
//    rBitblt.pv_src = (VOID*)mdev->src_phys;
//    rBitblt.i4_src_x = srect->x;
//    rBitblt.i4_src_y = srect->y;
//    rBitblt.ui4_src_pitch = mdev->src_pitch;
//    rBitblt.e_src_cm = mt85CmChg(mdev->src_format);
//    rBitblt.b_src_compressed = DFB_COMPRESSED;
//    rBitblt.pv_dst = (VOID*)mdev->dst_phys;
//    rBitblt.i4_dst_x = dx;
//    rBitblt.i4_dst_y = dy;
//    rBitblt.ui4_dst_pitch = mdev->dst_pitch;
//    rBitblt.e_dst_cm = mt85CmChg(mdev->dst_format);
//    rBitblt.b_dst_compressed = DFB_COMPRESSED;
//    rBitblt.ui4_width = srect->w;
//    rBitblt.ui4_height = srect->h;
//
//    palette->GetEntries( palette, colors, 256, 0 );
//
////    rBitblt.pv_cbcr_or_palette = ;
////    rBitblt.e_byte_aligned = ;
//
////    rBitblt.e_ycbcr_format = ;
////    rBitblt.ui4_cbcr_pitch = ;
////    rBitblt.ui1_ycbcr_alpha = ;
////    rBitblt.h_palette = ;
////    rBitblt.u4_alCom_normal = ;
////    rBitblt.u4_src_picth_en = ;
//
//    if(!prGfxCmdBuf->fg_compress && 
//      (rBitblt.b_src_compressed || rBitblt.b_dst_compressed))
//    {
//        prGfxCmdBuf->fg_compress = TRUE;
//    }
//
//    if (((GFX_CMDBUF_STATE)(prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState)) >= GFX_CMDBUF_STATE_MAX)
//    {
//        ASSERT(0);
//    }
//    else
//    {
//        mt85AdapterCmdBufAddCmd(prGfxCmdBuf, (void*)(&rBitblt), GFX_BUFF_TYPE_Y2R_BITBLT, DFB_NOWAIT, mdrv);
//
//        if (DFB_NOWAIT)
//        {
//            if (ioctl(mdrv->fd, FBIO_CMDBUFWAITDONE, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
//            {
//                D_PERROR( "MediaTek/Driver: FBIO_CMDBUFWAITDONE failed!\n" );
//                return false;
//            }
//        }
//    }
//
//    return true;

    return mt85Blit(drv, dev, srect, dx, dy);
}

static bool mt85BlitBlend( void *drv, void *dev, DFBRectangle *srect, int dx, int dy )
{
    /* Todo: color format of src and dst are different */
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;

    GFX_ALPHA_COMPOSITION_T rBitblt = {0};
    MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf = (MT85_ADAPTER_CMDBUF_T *)_h_mt85_Ticket;
    ASSERT(prGfxCmdBuf != NULL);
    ASSERT(mdrv && mdev && srect);

#if MT85_2D_LOG
    printf("[%s] x=%d,y=%d,w=%d,h=%d,dx=%d,dy=%d,a=%d\n", __FUNCTION__, srect->x, srect->y, srect->w, srect->h, dx, dy, mdev->color.a);
#endif
    rBitblt.b_rect_src_option = _b_DfbRectSrcOpt;  //temp
    rBitblt.e_rule = _e_PdRule;
    rBitblt.ui1_alpha = mdev->color.a;
    rBitblt.ui4_color = _fb_Comp_Color;
    rBitblt.u4TicketId = _h_mt85_Ticket;
    rBitblt.u4Flag = DFB_FLAG;
    rBitblt.ui4ModuleID = DFB_MODULE_ID;
    rBitblt.pv_src = (void*)mdev->src_phys;
    ASSERT(rBitblt.pv_src);
    rBitblt.i4_src_x = srect->x;
    rBitblt.i4_src_y = srect->y;
    rBitblt.ui4_src_pitch = mdev->src_pitch;
    rBitblt.e_src_cm = mt85CmChg(mdev->src_format);
    rBitblt.b_src_compressed = DFB_COMPRESSED;
    rBitblt.pv_dst = (void*)mdev->dst_phys;
    ASSERT(rBitblt.pv_dst);
    rBitblt.i4_dst_x = dx;
    rBitblt.i4_dst_y = dy;
    rBitblt.ui4_dst_pitch = mdev->dst_pitch;
    rBitblt.e_dst_cm = mt85CmChg(mdev->dst_format);
    rBitblt.b_dst_compressed = DFB_COMPRESSED;
    rBitblt.ui4_width = srect->w;
    rBitblt.ui4_height = srect->h;
    
    /*rBitblt.e_ycbcr_format = 
    rBitblt.pv_cbcr_or_palette = 
    rBitblt.ui4_cbcr_pitch = 
    rBitblt.ui1_ycbcr_alpha = 
    rBitblt.e_byte_aligned = 
    rBitblt.h_palette = 
    rBitblt.u4_alCom_normal = 
    rBitblt.u4_src_picth_en = */

    if(!prGfxCmdBuf->fg_compress && (rBitblt.b_src_compressed || rBitblt.b_dst_compressed))
    {
        prGfxCmdBuf->fg_compress = TRUE;
    }

    if (((GFX_CMDBUF_STATE)(prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState)) >= GFX_CMDBUF_STATE_MAX)
    {
        ASSERT(0);
    }
    else
    {
        mt85AdapterCmdBufAddCmd(prGfxCmdBuf, (void*)(&rBitblt), GFX_BUFF_TYPE_ALPHA_COMPOSITION, DFB_NOWAIT, mdrv);

        if (DFB_NOWAIT)
        {
            if (ioctl(mdrv->fd, FBIO_CMDBUFWAITDONE, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
            {
                D_PERROR( "MediaTek/Driver: FBIO_CMDBUFWAITDONE failed!\n" );
            }
        }
    }

    return true;
}

/*
 * Stretch a rectangle using the current hardware state.
 */
static bool
mt85StretchBlit( void *drv, void *dev, DFBRectangle *srect, DFBRectangle *drect )
{
    /* Todo: color format of src and dst are different */
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;

    GFX_STRETCH_BITBLT_T rStretch = {0};
    MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf = (MT85_ADAPTER_CMDBUF_T *)_h_mt85_Ticket;
    ASSERT(prGfxCmdBuf != NULL);
    ASSERT(mdrv && mdev && srect && drect);

#if MT85_2D_LOG
    printf("[%s] x=%d,y=%d,w=%d,h=%d,dx=%d,dy=%d,dw=%d,dh=%d\n", __FUNCTION__, srect->x, srect->y, srect->w, srect->h, drect->x, drect->y, drect->w, drect->h);
#endif
    rStretch.ui4_dst_width = drect->w;
    rStretch.ui4_dst_height = drect->h;
    rStretch.u4TicketId = _h_mt85_Ticket;
    rStretch.u4Flag = DFB_FLAG;
    rStretch.ui4ModuleID = DFB_MODULE_ID;
    rStretch.pv_src = (void*)mdev->src_phys;
    ASSERT(rStretch.pv_src);
    rStretch.i4_src_x = srect->x;
    rStretch.i4_src_y = srect->y;
    rStretch.ui4_src_pitch = mdev->src_pitch;
    rStretch.e_src_cm = mt85CmChg(mdev->src_format);
    rStretch.b_src_compressed = DFB_COMPRESSED;
    rStretch.pv_dst = (void*)mdev->dst_phys;
    ASSERT(rStretch.pv_dst);
    rStretch.i4_dst_x = drect->x;
    rStretch.i4_dst_y = drect->y;
    rStretch.ui4_dst_pitch = mdev->dst_pitch;
    rStretch.e_dst_cm = mt85CmChg(mdev->dst_format);
    rStretch.b_dst_compressed = DFB_COMPRESSED;
    rStretch.ui4_width = srect->w;
    rStretch.ui4_height = srect->h;
    
    /*rStretch.e_ycbcr_format = 
    rStretch.pv_cbcr_or_palette = 
    rStretch.ui4_cbcr_pitch = 
    rStretch.ui1_ycbcr_alpha = 
    rStretch.e_byte_aligned = 
    rStretch.h_palette = 
    rStretch.u4_alCom_normal = 
    rStretch.u4_src_picth_en = */

    if(!prGfxCmdBuf->fg_compress && 
      (rStretch.b_src_compressed || rStretch.b_dst_compressed))
    {
        prGfxCmdBuf->fg_compress = TRUE;
    }

    if (((GFX_CMDBUF_STATE)(prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState)) >= GFX_CMDBUF_STATE_MAX)
    {
        ASSERT(0);
    }
    else
    {
        mt85AdapterCmdBufAddCmd(prGfxCmdBuf, (void*)(&rStretch), GFX_BUFF_TYPE_ADV_STRETCH_BITBLT, DFB_NOWAIT, mdrv);

        if (DFB_NOWAIT)
        {
            if (ioctl(mdrv->fd, FBIO_CMDBUFWAITDONE, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
            {
                D_PERROR( "MediaTek/Driver: FBIO_CMDBUFWAITDONE failed!\n" );
                return false;
            }
        }
    }

    return true;
}

static bool mt85ColorKeyStretchBlit( void *drv, void *dev, DFBRectangle *srect, DFBRectangle *drect )
{
    /* Todo: color format of src and dst are different */
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;

    GFX_COLORKEY_STRETCH_BITBLT_T rStretch = {0};
    MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf = (MT85_ADAPTER_CMDBUF_T *)_h_mt85_Ticket;
    ASSERT(prGfxCmdBuf != NULL);
    ASSERT(mdrv && mdev && srect && drect);

#if MT85_2D_LOG
    printf("[%s] x=%d,y=%d,w=%d,h=%d,dx=%d,dy=%d,dw=%d,dh=%d\n", __FUNCTION__, srect->x, srect->y, srect->w, srect->h, drect->x, drect->y, drect->w, drect->h);
#endif
    rStretch.ui4_dst_width = drect->w;
    rStretch.ui4_dst_height = drect->h;
    rStretch.u4TicketId = _h_mt85_Ticket;
    rStretch.u4Flag = DFB_FLAG;
    rStretch.ui4ModuleID = DFB_MODULE_ID;
    rStretch.pv_src = (void*)mdev->src_phys;
    ASSERT(rStretch.pv_src);
    rStretch.i4_src_x = srect->x;
    rStretch.i4_src_y = srect->y;
    rStretch.ui4_src_pitch = mdev->src_pitch;
    rStretch.e_src_cm = mt85CmChg(mdev->src_format);
    rStretch.b_src_compressed = DFB_COMPRESSED;
    rStretch.pv_dst = (void*)mdev->dst_phys;
    ASSERT(rStretch.pv_dst);
    rStretch.i4_dst_x = drect->x;
    rStretch.i4_dst_y = drect->y;
    rStretch.ui4_dst_pitch = mdev->dst_pitch;
    rStretch.e_dst_cm = mt85CmChg(mdev->dst_format);
    rStretch.b_dst_compressed = DFB_COMPRESSED;
    rStretch.ui4_width = srect->w;
    rStretch.ui4_height = srect->h;
    
    rStretch.b_key_sd_sel = g_keysdsel;
    rStretch.ui4_color_space_min = g_keysdsel ? g_u4_dst_ck : g_u4_src_ck;
    rStretch.ui4_color_space_max = rStretch.ui4_color_space_min;

    /*rStretch.e_ycbcr_format = 
    rStretch.pv_cbcr_or_palette = 
    rStretch.ui4_cbcr_pitch = 
    rStretch.ui1_ycbcr_alpha = 
    rStretch.e_byte_aligned = 
    rStretch.h_palette = 
    rStretch.u4_alCom_normal = 
    rStretch.u4_src_picth_en = */

    if(!prGfxCmdBuf->fg_compress && 
      (rStretch.b_src_compressed || rStretch.b_dst_compressed))
    {
        prGfxCmdBuf->fg_compress = TRUE;
    }

    if (((GFX_CMDBUF_STATE)(prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState)) >= GFX_CMDBUF_STATE_MAX)
    {
        ASSERT(0);
    }
    else
    {
        mt85AdapterCmdBufAddCmd(prGfxCmdBuf, (void*)(&rStretch), GFX_BUFF_TYPE_COLORKEY_STRETCH_BITBLT, DFB_NOWAIT, mdrv);

        if (DFB_NOWAIT)
        {
            if (ioctl(mdrv->fd, FBIO_CMDBUFWAITDONE, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
            {
                D_PERROR( "MediaTek/Driver: FBIO_CMDBUFWAITDONE failed!\n" );
                return false;
            }
        }
    }

    return true;
}

static bool
mt85StretchBlitBlend( void *drv, void *dev, DFBRectangle *srect, DFBRectangle *drect )
{
    /* Todo: color format of src and dst are different */
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;

    GFX_ROTATE_STRETCH_ALPHACOMP_T rStretch = {0};
    MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf = (MT85_ADAPTER_CMDBUF_T *)_h_mt85_Ticket;
    ASSERT(prGfxCmdBuf != NULL);
    ASSERT(mdrv && mdev && srect && drect);

#if MT85_2D_LOG
    printf("[%s] x=%d,y=%d,w=%d,h=%d,dx=%d,dy=%d,dw=%d,dh=%d,a=%d\n", __FUNCTION__, srect->x, srect->y, srect->w, srect->h, drect->x, drect->y, drect->w, drect->h, mdev->color.a);
#endif
    rStretch.u4TicketId = _h_mt85_Ticket;
    rStretch.u4Flag = DFB_FLAG;
    rStretch.pv_src = (void*)mdev->src_phys;
    ASSERT(rStretch.pv_src);
    rStretch.i4_src_x = srect->x;
    rStretch.i4_src_y = srect->y;
    rStretch.ui4_src_pitch = mdev->src_pitch;
    rStretch.e_src_cm = mt85CmChg(mdev->src_format);
    rStretch.b_src_compressed = DFB_COMPRESSED;

    rStretch.pv_dst = (void*)mdev->dst_phys;
    ASSERT(rStretch.pv_dst);
    rStretch.i4_dst_x = drect->x;
    rStretch.i4_dst_y = drect->y;
    rStretch.ui4_dst_pitch = mdev->dst_pitch;
    rStretch.e_dst_cm = mt85CmChg(mdev->dst_format);
    rStretch.b_dst_compressed = DFB_COMPRESSED;

    rStretch.ui4_width = srect->w;
    rStretch.ui4_height = srect->h;
    rStretch.ui4ModuleID = DFB_MODULE_ID;

    rStretch.ui4_dst_width = drect->w;
    rStretch.ui4_dst_height = drect->h;
    rStretch.b_rect_src_option = _b_DfbRectSrcOpt;
    rStretch.e_option = GFX_OPTION_COMP;
    rStretch.e_rule = _e_PdRule;
    rStretch.ui1_ac_ar = mdev->color.a;
//    rStretch.ui1_alpha_assigned = ;
    rStretch.ui4_color = MT85_DFBColor_TO_ARGB(mdev->color);
//    rStretch.ui4_rot_op = ;

    if(!prGfxCmdBuf->fg_compress && 
       (rStretch.b_src_compressed || rStretch.b_dst_compressed))
    {
        prGfxCmdBuf->fg_compress = TRUE;
    }

    if (((GFX_CMDBUF_STATE)(prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState)) >= GFX_CMDBUF_STATE_MAX)
    {
        ASSERT(0);
    }
    else
    {
        mt85AdapterCmdBufAddCmd(prGfxCmdBuf, (void*)(&rStretch), GFX_BUFF_TYPE_STRETCH_BITBLT_BLEND, DFB_NOWAIT, mdrv);

        if (DFB_NOWAIT)
        {
            if (ioctl(mdrv->fd, FBIO_CMDBUFWAITDONE, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
            {
                D_PERROR( "MediaTek/Driver: FBIO_CMDBUFWAITDONE failed!\n" );
                return false;
            }
        }
    }

    return true;
}

#if ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550) || (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555))
// Pre-multiply
static bool
mt85FillRectanglePM( void *drv, void *dev, DFBRectangle *rect )
{
#if 1
#if MT85_2D_LOG
    printf("$$$$$$$$$$$[%s]$$$$$$$$$$$$$\n", __FUNCTION__);
#endif
    return mt85FillRectangle(drv, dev, rect);
#else
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;

    GFX_FILL_T rFill = {0};
    MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf = (MT85_ADAPTER_CMDBUF_T *)_h_mt85_Ticket;
    ASSERT(prGfxCmdBuf != NULL);
    ASSERT(mdrv && mdev && rect);

    rFill.b_compressed = DFB_COMPRESSED;
    rFill.ui4_color = MT85_DFBColor_TO_ARGB(mdev->color);
    rFill.e_dst_cm = mt85CmChg(mdev->dst_format);
    rFill.i4_dst_x = rect->x;
    rFill.i4_dst_y = rect->y;
    rFill.pv_dst = (void*)mdev->dst_phys;
    rFill.pv_dst2 = NULL;
    rFill.u4Flag = DFB_FLAG;
    rFill.u4TicketId = _h_mt85_Ticket;
    rFill.ui4ModuleID = DFB_MODULE_ID;
    rFill.ui4_dst_pitch = mdev->dst_pitch;
    rFill.ui4_dst_pitch2 = 0;
    rFill.ui4_height = rect->h;
    rFill.ui4_width = rect->w;

    if(!prGfxCmdBuf->fg_compress)
    {
        prGfxCmdBuf->fg_compress = rFill.b_compressed;
    }

    if (((GFX_CMDBUF_STATE)(prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState)) >= GFX_CMDBUF_STATE_MAX)
    {
        ASSERT(0);
    }
    else
    {
        mt85AdapterCmdBufAddCmd(prGfxCmdBuf, (void*)(&rFill), GFX_BUFF_TYPE_PM_FILL_RECT, DFB_NOWAIT, mdrv);

        if (DFB_NOWAIT)
        {
            if (ioctl(mdrv->fd, FBIO_CMDBUFWAITDONE, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
            {
                D_PERROR( "MediaTek/Driver: FBIO_CMDBUFWAITDONE failed!\n" );
                return false;
            }
        }
    }

    return true;
#endif
}

static bool
mt85FillRectangleBlendPM( void *drv, void *dev, DFBRectangle *rect )
{
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;

    GFX_FILL_BLEND_T rFillBlend = {0};
    MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf = (MT85_ADAPTER_CMDBUF_T *)_h_mt85_Ticket;
    ASSERT(prGfxCmdBuf != NULL);
    ASSERT(mdrv && mdev && rect);

#if MT85_2D_LOG
    printf("[%s] x=%d,y=%d,w=%d,h=%d\n", __FUNCTION__, rect->x, rect->y, rect->w, rect->h);
#endif
    rFillBlend.u4TicketId = _h_mt85_Ticket;
    rFillBlend.u4Flag = DFB_FLAG;
    rFillBlend.pv_dst = (void*)mdev->dst_phys;
    ASSERT(rFillBlend.pv_dst);
    rFillBlend.pv_dst2 = NULL;
    rFillBlend.i4_dst_x = rect->x;
    rFillBlend.i4_dst_y = rect->y;
    rFillBlend.ui4_dst_pitch = mdev->dst_pitch;
    rFillBlend.ui4_dst_pitch2 = 0;
    rFillBlend.e_dst_cm = mt85CmChg(mdev->dst_format);
    rFillBlend.b_compressed = DFB_COMPRESSED;
    rFillBlend.ui4_width = rect->w;
    rFillBlend.ui4_height = rect->h;
    rFillBlend.ui4_color = MT85_DFBColor_TO_ARGB(mdev->color);
    rFillBlend.ui4ModuleID = DFB_MODULE_ID;
    rFillBlend.ui1_alpha = mdev->color.a;

    if(!prGfxCmdBuf->fg_compress)
    {
        prGfxCmdBuf->fg_compress = rFillBlend.b_compressed;
    }

    if (((GFX_CMDBUF_STATE)(prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState)) >= GFX_CMDBUF_STATE_MAX)
    {
        ASSERT(0);
    }
    else
    {
        mt85AdapterCmdBufAddCmd(prGfxCmdBuf, (void*)(&rFillBlend), GFX_BUFF_TYPE_PM_FILL_RECT_BLEND, DFB_NOWAIT, mdrv);

        if (DFB_NOWAIT)
        {
            if (ioctl(mdrv->fd, FBIO_CMDBUFWAITDONE, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
            {
                D_PERROR( "MediaTek/Driver: FBIO_CMDBUFWAITDONE failed!\n" );
                return false;
            }
        }
    }

    return true;
}

static bool
mt85BlitPM( void *drv, void *dev, DFBRectangle *srect, int dx, int dy )
{
    /* Todo: color format of src and dst are different */
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;

    GFX_PREMULT_BITBLT_T rBitblt = {0};
    MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf = (MT85_ADAPTER_CMDBUF_T *)_h_mt85_Ticket;
    ASSERT(prGfxCmdBuf != NULL);
    ASSERT(mdrv && mdev && srect);

#if MT85_2D_LOG
    printf("[%s] x=%d,y=%d,w=%d,h=%d,dx=%d,dy=%d\n", __FUNCTION__, srect->x, srect->y, srect->w, srect->h, dx, dy);
#endif
    rBitblt.ui1_alpha = mdev->color.a;
    rBitblt.u4TicketId = _h_mt85_Ticket;
    rBitblt.u4Flag = DFB_FLAG;
    rBitblt.ui4ModuleID = DFB_MODULE_ID;
    rBitblt.pv_src = (void*)mdev->src_phys;
    rBitblt.i4_src_x = srect->x;
    rBitblt.i4_src_y = srect->y;
    rBitblt.ui4_src_pitch = mdev->src_pitch;
    rBitblt.e_src_cm = mt85CmChg(mdev->src_format);
    rBitblt.b_src_compressed = DFB_COMPRESSED;
    rBitblt.pv_dst = (void*)mdev->dst_phys;
    rBitblt.i4_dst_x = dx;
    rBitblt.i4_dst_y = dy;
    rBitblt.ui4_dst_pitch = mdev->dst_pitch;
    rBitblt.e_dst_cm = mt85CmChg(mdev->dst_format);
    rBitblt.b_dst_compressed = DFB_COMPRESSED;
    rBitblt.ui4_width = srect->w;
    rBitblt.ui4_height = srect->h;
    rBitblt.ui4_src_premult = ui4_src_PM_flg;
    rBitblt.ui4_dst_premult = ui4_dst_PM_flg;

    if(!prGfxCmdBuf->fg_compress && 
      (rBitblt.b_src_compressed || rBitblt.b_dst_compressed))
    {
        prGfxCmdBuf->fg_compress = TRUE;
    }

    if (((GFX_CMDBUF_STATE)(prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState)) >= GFX_CMDBUF_STATE_MAX)
    {
        ASSERT(0);
    }
    else
    {
        mt85AdapterCmdBufAddCmd(prGfxCmdBuf, (void*)(&rBitblt), GFX_BUFF_TYPE_PM_BITBLT, DFB_NOWAIT, mdrv);

        if (DFB_NOWAIT)
        {
            if (ioctl(mdrv->fd, FBIO_CMDBUFWAITDONE, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
            {
                D_PERROR( "MediaTek/Driver: FBIO_CMDBUFWAITDONE failed!\n" );
                return false;
            }
        }
    }

    return true;
}

static bool
mt85BlitBlendPM( void *drv, void *dev, DFBRectangle *srect, int dx, int dy )
{
    /* Todo: color format of src and dst are different */
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;

    GFX_PREMULT_ALPHA_COMPOSITION_T rBitblt = {0};
    MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf = (MT85_ADAPTER_CMDBUF_T *)_h_mt85_Ticket;
    ASSERT(prGfxCmdBuf != NULL);
    ASSERT(mdrv && mdev && srect);

#if MT85_2D_LOG
    printf("[%s] x=%d,y=%d,w=%d,h=%d,dx=%d,dy=%d\n", __FUNCTION__, srect->x, srect->y, srect->w, srect->h, dx, dy);
#endif
    rBitblt.b_rect_src_option = _b_DfbRectSrcOpt;  //temp
    rBitblt.ui1_alpha = mdev->color.a;
    rBitblt.ui4_color = _fb_Comp_Color;
    rBitblt.u4TicketId = _h_mt85_Ticket;
    rBitblt.u4Flag = DFB_FLAG;
    rBitblt.ui4ModuleID = DFB_MODULE_ID;
    rBitblt.pv_src = (void*)mdev->src_phys;
    rBitblt.i4_src_x = srect->x;
    rBitblt.i4_src_y = srect->y;
    rBitblt.ui4_src_pitch = mdev->src_pitch;
    rBitblt.e_src_cm = mt85CmChg(mdev->src_format);
    rBitblt.b_src_compressed = DFB_COMPRESSED;
    rBitblt.pv_dst = (void*)mdev->dst_phys;
    rBitblt.i4_dst_x = dx;
    rBitblt.i4_dst_y = dy;
    rBitblt.ui4_dst_pitch = mdev->dst_pitch;
    rBitblt.e_dst_cm = mt85CmChg(mdev->dst_format);
    rBitblt.b_dst_compressed = DFB_COMPRESSED;
    rBitblt.ui4_width = srect->w;
    rBitblt.ui4_height = srect->h;
    rBitblt.ui4_src_premult = ui4_src_PM_flg;
    rBitblt.ui4_dst_premult = ui4_dst_PM_flg;
    rBitblt.e_rule = _e_PdRule;

    if(!prGfxCmdBuf->fg_compress && (rBitblt.b_src_compressed || rBitblt.b_dst_compressed))
    {
        prGfxCmdBuf->fg_compress = TRUE;
    }

    if (((GFX_CMDBUF_STATE)(prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState)) >= GFX_CMDBUF_STATE_MAX)
    {
        ASSERT(0);
    }
    else
    {
        mt85AdapterCmdBufAddCmd(prGfxCmdBuf, (void*)(&rBitblt), GFX_BUFF_TYPE_PM_ALPHA_COMPOSITION, DFB_NOWAIT, mdrv);

        if (DFB_NOWAIT)
        {
            if (ioctl(mdrv->fd, FBIO_CMDBUFWAITDONE, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
            {
                D_PERROR( "MediaTek/Driver: FBIO_CMDBUFWAITDONE failed!\n" );
            }
        }
    }

    return true;
}

static bool
mt85StretchBlitPM( void *drv, void *dev, DFBRectangle *srect, DFBRectangle *drect )
{
    /* Todo: color format of src and dst are different */
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;

    GFX_PREMULT_STRETCH_BITBLT_T rStretch = {0};
    MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf = (MT85_ADAPTER_CMDBUF_T *)_h_mt85_Ticket;
    ASSERT(prGfxCmdBuf != NULL);
    ASSERT(mdrv && mdev && srect && drect);

#if MT85_2D_LOG
    printf("[%s] x=%d,y=%d,w=%d,h=%d,dx=%d,dy=%d,dw=%d,dh=%d\n", __FUNCTION__, srect->x, srect->y, srect->w, srect->h, drect->x, drect->y, drect->w, drect->h);
#endif
    rStretch.ui4_dst_width = drect->w;
    rStretch.ui4_dst_height = drect->h;
    rStretch.u4TicketId = _h_mt85_Ticket;
    rStretch.u4Flag = DFB_FLAG;
    rStretch.ui4ModuleID = DFB_MODULE_ID;
    rStretch.pv_src = (void*)mdev->src_phys;
    rStretch.i4_src_x = srect->x;
    rStretch.i4_src_y = srect->y;
    rStretch.ui4_src_pitch = mdev->src_pitch;
    rStretch.e_src_cm = mt85CmChg(mdev->src_format);
    rStretch.b_src_compressed = DFB_COMPRESSED;
    rStretch.pv_dst = (void*)mdev->dst_phys;
    rStretch.i4_dst_x = drect->x;
    rStretch.i4_dst_y = drect->y;
    rStretch.ui4_dst_pitch = mdev->dst_pitch;
    rStretch.e_dst_cm = mt85CmChg(mdev->dst_format);
    rStretch.b_dst_compressed = DFB_COMPRESSED;
    rStretch.ui4_width = srect->w;
    rStretch.ui4_height = srect->h;
    rStretch.ui4_src_premult = ui4_src_PM_flg;
    rStretch.ui4_dst_premult = ui4_dst_PM_flg;
    rStretch.ui1_alpha = mdev->color.a;
    rStretch.e_rule = _e_PdRule;

    if(!prGfxCmdBuf->fg_compress && 
      (rStretch.b_src_compressed || rStretch.b_dst_compressed))
    {
        prGfxCmdBuf->fg_compress = TRUE;
    }

    if (((GFX_CMDBUF_STATE)(prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState)) >= GFX_CMDBUF_STATE_MAX)
    {
        ASSERT(0);
    }
    else
    {
        mt85AdapterCmdBufAddCmd(prGfxCmdBuf, (void*)(&rStretch), GFX_BUFF_TYPE_PM_STRETCH_BITBLT, DFB_NOWAIT, mdrv);

        if (DFB_NOWAIT)
        {
            if (ioctl(mdrv->fd, FBIO_CMDBUFWAITDONE, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
            {
                D_PERROR( "MediaTek/Driver: FBIO_CMDBUFWAITDONE failed!\n" );
                return false;
            }
        }
    }

    return true;
}

static bool
mt85StretchBlitBlendPM( void *drv, void *dev, DFBRectangle *srect, DFBRectangle *drect )
{
    /* Todo: color format of src and dst are different */
    MT85DriverData *mdrv = (MT85DriverData*) drv;
    MT85DeviceData *mdev = (MT85DeviceData*) dev;

    GFX_PREMULT_STRETCH_BITBLT_BLEND_T rStretch = {0};
    MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf = (MT85_ADAPTER_CMDBUF_T *)_h_mt85_Ticket;
    ASSERT(prGfxCmdBuf != NULL);
    ASSERT(mdrv && mdev && srect && drect);

#if MT85_2D_LOG
    printf("[%s] x=%d,y=%d,w=%d,h=%d,dx=%d,dy=%d,dw=%d,dh=%d\n", __FUNCTION__, srect->x, srect->y, srect->w, srect->h, drect->x, drect->y, drect->w, drect->h);
#endif
    rStretch.ui4_dst_width = drect->w;
    rStretch.ui4_dst_height = drect->h;
    rStretch.u4TicketId = _h_mt85_Ticket;
    rStretch.u4Flag = DFB_FLAG;
    rStretch.ui4ModuleID = DFB_MODULE_ID;
    rStretch.pv_src = (void*)mdev->src_phys;
    rStretch.i4_src_x = srect->x;
    rStretch.i4_src_y = srect->y;
    rStretch.ui4_src_pitch = mdev->src_pitch;
    rStretch.e_src_cm = mt85CmChg(mdev->src_format);
    rStretch.b_src_compressed = DFB_COMPRESSED;
    rStretch.pv_dst = (void*)mdev->dst_phys;
    rStretch.i4_dst_x = drect->x;
    rStretch.i4_dst_y = drect->y;
    rStretch.ui4_dst_pitch = mdev->dst_pitch;
    rStretch.e_dst_cm = mt85CmChg(mdev->dst_format);
    rStretch.b_dst_compressed = DFB_COMPRESSED;
    rStretch.ui4_width = srect->w;
    rStretch.ui4_height = srect->h;
    rStretch.ui4_src_premult = ui4_src_PM_flg;
    rStretch.ui4_dst_premult = ui4_dst_PM_flg;
    rStretch.ui1_alpha = mdev->color.a;
    rStretch.e_rule = _e_PdRule;

    if(!prGfxCmdBuf->fg_compress && 
       (rStretch.b_src_compressed || rStretch.b_dst_compressed))
    {
        prGfxCmdBuf->fg_compress = TRUE;
    }

    if (((GFX_CMDBUF_STATE)(prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState)) >= GFX_CMDBUF_STATE_MAX)
    {
        ASSERT(0);
    }
    else
    {
        mt85AdapterCmdBufAddCmd(prGfxCmdBuf, (void*)(&rStretch), GFX_BUFF_TYPE_PM_STRETCH_BITBLT_BLEND, DFB_NOWAIT, mdrv);

        if (DFB_NOWAIT)
        {
            if (ioctl(mdrv->fd, FBIO_CMDBUFWAITDONE, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
            {
                D_PERROR( "MediaTek/Driver: FBIO_CMDBUFWAITDONE failed!\n" );
                return false;
            }
        }
    }

    return true;
}
#endif

static void _mt85CheckFlush(void *drv, void *dev)
{
    INT32 u4Ret = 0;

    if (b_hw_last)
    {
        MT85DriverData *mdrv = (MT85DriverData*) drv;

        MT85_ADAPTER_CMDBUF_T *prGfxCmdBuf = (MT85_ADAPTER_CMDBUF_T *)_h_mt85_Ticket;
        ASSERT(prGfxCmdBuf != NULL);

        if (((GFX_CMDBUF_STATE)(prGfxCmdBuf->prGfxMwCmdBuf->u4GfxCmdBufState)) >= GFX_CMDBUF_STATE_MAX)
        {
            ASSERT(0);
        }
        else
        {
            if (prGfxCmdBuf->prGfxMwCmdBuf->u4RdPoint != prGfxCmdBuf->prGfxMwCmdBuf->u4WrPoint)
            {
                u4Ret = mt85AdapterCmdBufAddCmd(prGfxCmdBuf, NULL, GFX_BUFF_TYPE_FLUSH, DFB_WAIT, mdrv);
                if (u4Ret != 0xffffffff)
                {
                #if MT85_2D_LOG
                    printf("[%s] Send flush command! rd=%d,wr=%d\n", __FUNCTION__, prGfxCmdBuf->prGfxMwCmdBuf->u4RdPoint, prGfxCmdBuf->prGfxMwCmdBuf->u4WrPoint);
                #endif
                    if (DFB_WAIT)
                    {
                        if (ioctl(mdrv->fd, FBIO_CMDBUFWAITDONE, prGfxCmdBuf->prGfxMwCmdBuf) < 0)
                        {
                            D_PERROR( "MediaTek/Driver: FBIO_CMDBUFWAITDONE failed!\n" );
                        }
                    }
                }
            }
        }

        b_hw_last = FALSE;
    }    
}

#endif

