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
*   The definition of mt85 in directfb to co work with frame buffer
*
*/

#ifndef FB_MT85XX_H
#define FB_MT85XX_H

#include "mtk_dfb_cfg.h"

#define GFX_DRV_CMDBUF_DYNAMIC_SIZE         1

#define DFB_SUPPORT_GFX_ADAPTER 1

#if DFB_SUPPORT_GFX_ADAPTER
#define SUPPORT_FRAME_ACCURATE
//#define GFX_SUPPORT_SINGLE_BUFFER
#define GFX_SUPPORT_DOUBLE_BUFFER
#define GL_XOR

#if CONFIG_DRV_3D_SUPPORT
    #define GFX_SUPPORT_3D_DOUBLE_BUFFER
#endif
#endif

#define FB_INIT_BY_AP 1
#define FB_DBG 0
#define FB_PROF 0

#if FB_PROF
#undef FB_DBG
#define FB_DBG 1
#endif

#define FBIO_RECTFILL 0x4620
#define FBIO_COPYAREA 0x4621
#define FBIO_IMAGEBLIT 0x4622
#define FBIO_REGISTERBUFFER 0x4623
#define FBIO_UNREGISTERBUFFER 0x4624
#define FBIO_RECTFILLROP 0x4625
#define FBIO_COPYAREAROP 0x4626
#define FBIO_SET 0x4627
#define FBIO_PALETTE 0x4628
#define FBIO_GETIMAGEBUFFER 0x4629
#define FBIO_IMAGEBLITROP 0x462A
#define FBIO_FONTRENDER 0x462B
#define FBIO_SETBLITOPT 0x462c
//new function
#define FBIO_DRAWLINE 0x462D
#define FBIO_DRAWLINEROP 0x462E
#define FBIO_DRAWRECT 0x462F
#define FBIO_DRAWRECTROP 0x4632
#define FBIO_DRAWTEXT 0x4631
// For DirectFB user mode cmdque
#define FBIO_GFX_FLUSH 0x4640
#define FBIO_GFX_WAIT 0x4641
// For get /set plane order array to sync with m/w
#define FBIO_GET_PLANE_ORDER_ARRAY 0x4642
#define FBIO_SET_PLANE_ORDER_ARRAY 0x4643
#define FBIO_SET_OSD_PLANE_ORDER_ARRAY 0x4644
#define FBIO_GET 0x4645
// for GFX_LOCK
#define FBIO_GFX_LOCK 0x4649
#define FBIO_GFX_UNLOCK 0x464A
// new for MT8530
#define FBIO_BLITBLEND 0x464B
#define FBIO_STRETCHBLIT 0x464C
#if DFB_SUPPORT_GFX_ADAPTER
// DFB_SUPPORT_GFX_ADAPTER
#define FBIO_CMDBUFWAITDONE 0x464D
#define FBIO_GETCMDBUF 0x464E
#define FBIO_GETCMDBUFLEN 0x464F
#define FBIO_RELCMDBUF 0x4650
#define FBIO_DISPATCHHW 0x4651
#endif
#define FBIO_GETMULTIPOOL 0x4652

#if FB_INIT_BY_AP
#define FBIO_INIT 0x4630
#endif
#if FB_DBG
#define FBIO_GETTIME 0x4630
#endif

#define MT85FB_ROP_CLEAR       0
#define MT85FB_ROP_DST_IN      1
#define MT85FB_ROP_DST_OUT     2
#define MT85FB_ROP_DST_OVER    3
#define MT85FB_ROP_SRC         4
#define MT85FB_ROP_SRC_IN      5
#define MT85FB_ROP_SRC_OUT     6
#define MT85FB_ROP_SRC_OVER    7
#define MT85FB_ROP_DST         8
#define MT85FB_ROP_SRC_ATOP    9
#define MT85FB_ROP_DST_ATOP    10
#define MT85FB_ROP_XOR         11

#define MT85FB_SET_X 0
#define MT85FB_SET_Y 1
#define MT85FB_SET_W 2
#define MT85FB_SET_H 3
#define MT85FB_SET_VIRT_W 4
#define MT85FB_SET_VIRT_H 5	
#define MT85FB_SET_OFFSET_X 6
#define MT85FB_SET_OFFSET_Y 7
#define MT85FB_SET_BPP 8
#define MT85FB_SET_CM 9
#define MT85FB_SET_VISIBLE 10
#define MT85FB_SET_BASE 11
#define MT85FB_SET_PITCH 12
#define MT85FB_SET_ORDER 13
#define MT85FB_SET_OPACITY 14
#define MT85FB_SET_MIXSEL 15
#define MT85FB_SET_COLORKEYEN 16
#define MT85FB_SET_COLORKEY 17
#define MT85FB_SET_PLANE_ID 18


#define MT85FB_SET_MASK_X (1u << MT85FB_SET_X)
#define MT85FB_SET_MASK_Y (1u << MT85FB_SET_Y)
#define MT85FB_SET_MASK_W (1u << MT85FB_SET_W)
#define MT85FB_SET_MASK_H (1u << MT85FB_SET_H)
#define MT85FB_SET_MASK_VIRT_W (1u << MT85FB_SET_VIRT_W)
#define MT85FB_SET_MASK_VIRT_H (1u << MT85FB_SET_VIRT_H)
#define MT85FB_SET_MASK_OFFSET_X (1u << MT85FB_SET_OFFSET_X)
#define MT85FB_SET_MASK_OFFSET_Y (1u << MT85FB_SET_OFFSET_Y)
#define MT85FB_SET_MASK_BPP (1u << MT85FB_SET_BPP)
#define MT85FB_SET_MASK_CM (1u << MT85FB_SET_CM)
#define MT85FB_SET_MASK_VISIBLE (1u << MT85FB_SET_VISIBLE)
#define MT85FB_SET_MASK_BASE (1u << MT85FB_SET_BASE)
#define MT85FB_SET_MASK_PITCH (1u << MT85FB_SET_PITCH)
#define MT85FB_SET_MASK_ORDER (1u << MT85FB_SET_ORDER)
#define MT85FB_SET_MASK_OPACITY (1u << MT85FB_SET_OPACITY)
#define MT85FB_SET_MASK_MIXSEL (1u << MT85FB_SET_MIXSEL)
#define MT85FB_SET_MASK_COLORKEY (1u << MT85FB_SET_COLORKEY)
#define MT85FB_SET_MASK_COLORKEYEN (1u << MT85FB_SET_COLORKEYEN)
#define MT85FB_SET_MASK_PLANE_ID (1u << MT85FB_SET_PLANE_ID)

#define MT85FB_GET_PANEL_SIZE 0

//new feature
struct mt85fb_DrawLine
{
    unsigned short u2X1;
    unsigned short u2Y1;
    unsigned short u2X2;
    unsigned short u2Y2;
    unsigned short u2DstPitch;
    unsigned int            u4color;
    unsigned char* pu1DstBaseAddr;
    unsigned int             u4Bpp;
    //color mode
    int         i4ColorMode;
};

struct mt85fb_DrawLineRop
{
    unsigned short u2X1;
    unsigned short u2Y1;
    unsigned short u2X2;
    unsigned short u2Y2;
    unsigned short u2DstPitch;
    unsigned int   u4color;
    unsigned int   u4Rop;
    unsigned short u2Alpha;
    unsigned char* pu1DstBaseAddr;
    unsigned int             u4Bpp;
    //color mode
    int         i4ColorMode;
};

struct mt85fb_DrawRect {
    unsigned short                  u2DstX;
    unsigned short                  u2DstY;
    unsigned short                  u2DstPitch;
    // Rectangular's width and height
    unsigned short                  u2Width;
    unsigned short                  u2Height;
    // Destiniation paper
    unsigned char*                  pu1DstBaseAddr;
    // Color
    unsigned int	        	                u4Color;
    unsigned int			                    u4Bpp;
    unsigned int                            u4BoarderWidth;
    //color mode
    int         i4ColorMode;
};

struct mt85fb_DrawRectRop {
    unsigned short                  u2DstX;
    unsigned short                  u2DstY;
    unsigned short                  u2DstPitch;
    // Rectangular's width and height
    unsigned short                  u2Width;
    unsigned short                  u2Height;
    // Destiniation paper
    unsigned char*                  pu1DstBaseAddr;
    // Color
    unsigned int                             u4Color; 
    unsigned int			                    u4Alpha;    
    unsigned int 			                u4Rop;
    unsigned int			                    u4Bpp;    
    unsigned int                             u4BoarderWidth;
    //color mode
    int         i4ColorMode;
};

struct mt85fb_DrawText {
    unsigned short u2X;
    unsigned short u2Y;
    unsigned short u2Width;
    unsigned short u2Height;
    unsigned short u2DstPitch;
    unsigned char*          pTextArray;
    unsigned char*  pu1DstBaseAddr;
    unsigned int   u4Color;
    unsigned int   u4Alpha;
    unsigned int   u4Bpp;
    //color mode
    int         i4ColorMode;
};



//end new feature
struct mt85fb_alloc
{
    unsigned int len;
    unsigned int addr;
    unsigned int phys;
};

struct mt85fb_imagebuffer
{
    unsigned int u4Size;
    unsigned int u4VirtAddr;
    unsigned int u4PhyAddr;
};

struct mt85fb_imageblit
{
    unsigned short       u2SrcX;
    unsigned short       u2SrcY;
    unsigned short       u2SrcPitch;
    unsigned short                  u2DstX;
    unsigned short                  u2DstY;
    unsigned short                  u2DstPitch;
    // Blitting area's width and height
    unsigned short                  u2Width;
    unsigned short                  u2Height;
    // Source paper
    unsigned char*                  pu1SrcBaseAddr;
    // Destination paper
    unsigned char*                  pu1DstBaseAddr;

    int			i4SrcBpp;   
    int			i4DstBpp;     
    //color mode
    int         i4SrcColorMode;
    int         i4DstColorMode;
    
};

struct mt85fb_imageblitrop
{
    unsigned short                  u2SrcX;
    unsigned short                  u2SrcY;
    unsigned short                  u2SrcPitch;
    unsigned short                  u2DstX;
    unsigned short                  u2DstY;
    unsigned short                  u2DstPitch;
    // Blitting area's width and height
    unsigned short                  u2Width;
    unsigned short                  u2Height;
    // Source paper
    unsigned char*                  pu1SrcBaseAddr;
    // Destination paper
    unsigned char*                  pu1DstBaseAddr;
        
    int					 i4Alpha;
    int					 i4Rop;   
    
    int			i4SrcBpp;   
    int			i4DstBpp;      
    //color mode
    int         i4SrcColorMode;
    int         i4DstColorMode;
    
    
};

struct mt85fb_fillrect {
    unsigned short                  u2DstX;
    unsigned short                  u2DstY;
    unsigned short                  u2DstPitch;
    // Rectangular's width and height
    unsigned short                  u2Width;
    unsigned short                  u2Height;
    // Destiniation paper
    unsigned char*                  pu1DstBaseAddr;
    // Color
    int			                  i4Color;

    int			i4Bpp;
    int         i4ColorMode;
    
};

struct mt85fb_fillrectrop {
    unsigned short                  u2DstX;
    unsigned short                  u2DstY;
    unsigned short                  u2DstPitch;
    // Rectangular's width and height
    unsigned short                  u2Width;
    unsigned short                  u2Height;
    // Destiniation paper
    unsigned char*                  pu1DstBaseAddr;
    // Color
    int                     i4Color;
       
    int			 i4Alpha;    
    int 		i4Rop;
   
    int			i4Bpp; 
    //color mode
    int         i4ColorMode;
};

struct mt85fb_copyarearop 
{
	unsigned int dx;
	unsigned int dy;
	unsigned int width;
	unsigned int height;
	unsigned int sx;
	unsigned int sy;
	unsigned int rop;	
	unsigned int alpha;
    unsigned long dst_phys;
    unsigned long src_phys;
    unsigned short u2SrcPitch;
    unsigned short u2DstPitch;
    
    //color mode
    int         i4SrcColorMode;
    int         i4DstColorMode;
};

struct mt85fb_stretchblit 
{
	unsigned int dx;
	unsigned int dy;
	unsigned int dw;
	unsigned int dh;
	unsigned int sx;
	unsigned int sy;
	unsigned int sw;
	unsigned int sh;
	unsigned int rop;	
	unsigned int alpha;
    unsigned long dst_phys;
    unsigned long src_phys;
    unsigned short u2SrcPitch;
    unsigned short u2DstPitch;
    //color mode
    int         i4SrcColorMode;
    int         i4DstColorMode;
};

struct mt85fb_setting 
{
    unsigned int u4Pitch;
    unsigned int u1CM;	
    unsigned int u2X;
    unsigned int u2Y;
    unsigned int u2W;
    unsigned int u2H;   
    unsigned int u2OffsetX;
    unsigned int u2OffsetY;    
    unsigned int fgVisible;
    unsigned int u4Base;
    unsigned int u4Order;    
    unsigned int u4Opacity;        
    unsigned int u4MixSel;       
    unsigned int u4ColorKey;
    unsigned int u4ColorKeyEn;
    unsigned int u4OsdPlaneID;        
};  

struct mt85fb_set 
{
    struct mt85fb_setting rSetting;
    unsigned int mask;
};

struct mt85fb_get 
{
    unsigned int get_type;
    unsigned int get_size;
    unsigned int *get_data;
};

struct mt85fb_palette
{
    unsigned int plane_id;
    unsigned int palette[256];
};


typedef enum
{
    E_GFXLIB_SRC_X_ERR = 1000,
    E_GFXLIB_SRC_Y_ERR,
    E_GFXLIB_DST_X_ERR,
    E_GFXLIB_DST_Y_ERR,
    E_GFXLIB_WIDTH_ERR,
    E_GFXLIB_HEIGHT_ERR,
    E_GFXLIB_SRC_PITCH_ERR,
    E_GFXLIB_DST_PITCH_ERR,
    E_GFXLIB_SRC_ADDR_ERR,
    E_GFXLIB_DST_ADDR_ERR,
    E_GFXLIB_ROP_ERR,
    E_GFXLIB_PALE_ADDR_ERR,
    
    E_GFXLIB_SET_X_ERR,
    E_GFXLIB_SET_Y_ERR,
    E_GFXLIB_SET_WIDTH_ERR,
    E_GFXLIB_SET_HEIGHT_ERR,
    E_GFXLIB_SET_PITCH_ERR,
    E_GFXLIB_SET_BPP_ERR,    
    E_GFXLIB_SET_ADDR_ERR,
    E_GFXLIB_SET_COLORMODE_ERR,
    E_GFXLIB_SET_ENABLE_ERR,

    E_GFXLIB_ERR_MAX
} E_GFXLIB_T;

extern unsigned int _mapColorFormat(DFBSurfacePixelFormat format);

#endif /*__MT85_FB__*/

