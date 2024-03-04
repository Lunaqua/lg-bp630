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

#ifndef X_MT85XX_PINMUX_H
#define X_MT85XX_PINMUX_H

//-----------------------------------------------------------------------------
// Include files
//-----------------------------------------------------------------------------
#include <asm/arch/x_ckgen.h>
#include <chip_ver.h>
#include <asm/config.h>

#define __UBOOT_GPIO_PINMUX__
#include <asm/arch/mt85xx_gpio_pinmux.h>

#if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530)

#if SUPPORT_WP
#define NAND_WP         PIN_UNKNOWN
#endif

#if SUPPORT_UPG_STATUS
#define UPG_STATUS_PIN  PIN_GPIO6 
#endif

#if SUPPORT_AMUTE
#define AUDIO_MUTE      PIN_AMUTE
#endif

#if SUPPORT_JIG
#define START_BIT       PIN_GPIO1

#define VIDEO_MUTE      PIN_VIND6      // Control for Video Mute 0:Mute, 1:Output

#define JIG_MODE0       PIN_GPIO4      //JIG mode flag input 0
#define JIG_MODE1       PIN_GPIO5      //JIG mode flag input 1

#define USB_PCONT1      PIN_VIND5
#define USB_PCONT2      PIN_GPIO3
#define USB_PCONT3 		PIN_UNKNOWN

#define PIN_FE_RST1		PIN_UNKNOWN
#endif

#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550)

#if SUPPORT_WP
#define NAND_WP  PIN_UNKNOWN
#endif

#if SUPPORT_UPG_STATUS
#define UPG_STATUS_PIN  PIN_GPIO6 
#endif

#if SUPPORT_AMUTE
#define AUDIO_MUTE      PIN_AMUTE
#endif

#if SUPPORT_JIG
#define START_BIT       PIN_GPIO1

#define VIDEO_MUTE      PIN_SCL      // Control for Video Mute 0:Mute, 1:Output

#define JIG_MODE0       PIN_GPIO4      //JIG mode flag input 0
#define JIG_MODE1       PIN_GPIO5      //JIG mode flag input 1

#define USB_PCONT1      PIN_GPIO7
#define USB_PCONT2      PIN_GPIO3
#define USB_PCONT3 		PIN_GPIO8

#define PIN_FE_RST1		PIN_UNKNOWN
#endif

#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555)

#if SUPPORT_WP
#define NAND_WP         PIN_UNKNOWN
#endif

#if SUPPORT_UPG_STATUS
#define UPG_STATUS_PIN  PIN_GPIO6 
#endif

#if SUPPORT_AMUTE
#define AUDIO_MUTE      PIN_AMUTE
#endif

#if SUPPORT_JIG
#define START_BIT       PIN_GPIO1

#define VIDEO_MUTE      PIN_VFD_STB    // Control for Video Mute 0:Mute, 1:Output

#define JIG_MODE0       PIN_GPIO4      //JIG mode flag input 0
#define JIG_MODE1       PIN_GPIO5      //JIG mode flag input 1

#define USB_PCONT1      PIN_GPIO7
#define USB_PCONT2      PIN_SFCS
#define USB_PCONT3 		PIN_UNKNOWN

#define PIN_FE_RST1		PIN_NFRBN2
#endif

#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8560)

  #if SUPPORT_WP
#define NAND_WP         PIN_UNKNOWN
  #endif

  #if SUPPORT_UPG_STATUS
#define UPG_STATUS_PIN  PIN_GPIO6 
  #endif

  #if SUPPORT_AMUTE
#define AUDIO_MUTE      PIN_AMUTE
  #endif

  #if SUPPORT_JIG
#define START_BIT       PIN_GPIO1
#define VIDEO_MUTE      PIN_VFD_STB    // Control for Video Mute 0:Mute, 1:Output
#define JIG_MODE0       PIN_GPIO4      //JIG mode flag input 0
#define JIG_MODE1       PIN_GPIO5      //JIG mode flag input 1
#define USB_PCONT1      PIN_GPIO7
#define USB_PCONT2      PIN_SFCS
#define USB_PCONT3      PIN_UNKNOWN
#define PIN_FE_RST1     PIN_NFRBN2
  #endif

#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8580)

  #if SUPPORT_WP
#define NAND_WP         PIN_UNKNOWN
  #endif

  #if SUPPORT_UPG_STATUS
#define UPG_STATUS_PIN  PIN_GPIO6 
  #endif

  #if SUPPORT_AMUTE
#define AUDIO_MUTE      PIN_AMUTE
  #endif

  #if SUPPORT_JIG
#define START_BIT       PIN_GPIO1
#define VIDEO_MUTE      PIN_VFD_STB    // Control for Video Mute 0:Mute, 1:Output
#define JIG_MODE0       PIN_GPIO4      //JIG mode flag input 0
#define JIG_MODE1       PIN_GPIO5      //JIG mode flag input 1
#define USB_PCONT1      PIN_GPIO7
#define USB_PCONT2      PIN_SFCS
#define USB_PCONT3      PIN_UNKNOWN
#define PIN_FE_RST1     PIN_NFRBN2
  #endif

#endif // end of #if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530)

#define SUPPORT_UPG_FLASH_LED   0
#define UPG_FLASH_LED_FORM      0

//-----------------------------------------------------------------------------
// Constant definitions
//-----------------------------------------------------------------------------
#define OUTPUT 		1
#define INPUT 		0
#define HIGH        1
#define LOW         0

typedef enum{
  UPG_NONE,
  UPG_START,
  UPG_ERASE,
  UPG_PROGRAM,
  UPG_READ,
  UPG_CHECK,
  UPG_END_OK,
  UPG_END_FAIL
} UPG_STATUS;

#if (UPG_FLASH_LED_FORM == 1)
#define PIN_CON_RED_LED      PIN_VIND7
#define PIN_CON_GREEN_LED    PIN_VIND8
#elif (UPG_FLASH_LED_FORM == 0)
#define PIN_CON_RED_LED      PIN_VOUTD0    
#define PIN_CON_GREEN_LED    PIN_VOUTHSYNC  
#endif
//-----------------------------------------------------------------------------
// Macro definitions
//-----------------------------------------------------------------------------

//-----------------------------------------------------------------------------
// Public function declaration
//-----------------------------------------------------------------------------
extern int pinmux_init(void);
extern INT32 BSP_PinSet(INT32 i4FuncSel, INT32 i4Func);
extern INT32 BSP_PinGet(INT32 i4FuncSel);

extern void  GPIO_Config(INT32 i4GpioNum, INT32 i4Output, INT32 i4High);
extern void  GPIO_Output(INT32 i4GpioNum, INT32 i4High);
extern INT32 GPIO_Input(INT32 i4GpioNum);
extern void  GPIO_InOut_Sel(INT32 i4GpioNum, INT32 i4Output);

extern void v_set_upg_status(UPG_STATUS ustatus, u_long size);
extern void v_show_upg_result(void);

extern BOOL fg_ne_bl_chk_jig(void);
extern void vSetUpgStatus(BOOL fgVal);
extern BOOL  NAND_COMMON_WriteProtect(BOOL bMode); 


#endif  // X_MT85XX_PINMUX_H

