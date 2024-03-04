/*
 *  linux/drivers/mmc/mtk_sd.c - Secure Digital Host Controller Interface driver
 *
 *  Copyright (C) 2009 Mediatek.Inc, All Rights Reserved.
 *
 */

#include <config.h>
#include <common.h>
#include <command.h>
#include <hwconfig.h>
#include <mmc.h>
#include <part.h>
#include <malloc.h>
#include <mmc.h>
#include <asm/io.h>
#include <asm/arch/x_typedef.h>
#include <asm/arch/mt85xx.h>

#include "mtk_sd.h"
#include "fci_type.h"
#include "fcihw_reg.h"
#include "fcihw_io.h"
#include "sdc_const.h"
#include "x_ckgen.h"

#define DRIVER_NAME "MTK_SD"
#define DRIVER_VERSION "0.10"

#define BUGMAIL "Mediatek. Inc"

DECLARE_GLOBAL_DATA_PTR;

#if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530)
static u8 sd_clk_array[] = {0, 1, 3, 6, 13, 27, 33, 34, 36};
#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550) || (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555)
static u8 sd_clk_array[] = {0, 1, 3, 6, 13, 18, 23, 27, 33, 36, 37, 43};
#endif
static int sd_clk_index = 0;

static struct sdhci_host fci_host;

static UINT32 _u4FciHwRstWtTimes = 1;
static volatile INT32 _i4FCIPrevSlot = NONE_SLOT;
static INT32 _u4SDMultiFuncCfg = SD_MLT_FNC_CFG;
static UINT32 _u4SDClockSetting = 0;
static BOOL  _fgFCI4BitBusEnable = TRUE;
static UINT32 _u4SRAMSel = 0;   // 0: SRAM size is 32x 32 bits (128ytes).
static INT32 _u4SDPwrCdPinCfg = SD_PWR_CD_PIN_CFG;
static UINT32 _u4SdDbgError = 0x00;

/// ISR is registered or using polling mode.
static INT32 _fgFCIIsrEn = FALSE;

/// SD, MS Int Status
static UINT32 _u4FCISStatus = 0x0;

/**
* LOG function
*/

#ifdef FCI_LOG_ENABLE
#define FCI_LOG_ERROR                       ((u32)1 << 0)
#define FCI_LOG_CARD                         ((u32)1 << 1)
#define FCI_LOG_REQ                           ((u32)1 << 2)
#define FCI_LOG_CLK                           ((u32)1 << 3)
#define FCI_LOG_RETRY                       ((u32)1 << 4)

#define FCI_LOG_INIT                          ((u32)1 << 5)
#define FCI_LOG_FUNC                        ((u32)1 << 6)
#define FCI_LOG_FSM                          ((u32)1 << 7)
#define FCI_LOG_IRQ                           ((u32)1 << 8)
#define FCI_LOG_SDHWCMD                 ((u32)1 << 9)
#define FCI_LOG_IOCMD                       ((u32)1 << 10)
#define FCI_LOG_RW                            ((u32)1 << 11)
#define FCI_LOG_BUFFER                     ((u32)1 << 12)
#define FCI_LOG_DBG                          ((u32)1 << 13)

static unsigned int fci_log_en = FCI_LOG_RETRY | FCI_LOG_ERROR | FCI_LOG_CARD | FCI_LOG_CLK; //FCI_LOG_REQ | FCI_LOG_ERROR | FCI_LOG_INIT | FCI_LOG_FUNC | FCI_LOG_CLK | FCI_LOG_IRQ | FCI_LOG_SDHWCMD | FCI_LOG_RW | FCI_LOG_BUFFER | FCI_LOG_IOCMD;

#define FCI_LOG(n, f, x...) if (fci_log_en & (n)){ printf(DRIVER_NAME ": <%s> " f, __func__,## x); }

#else

#define FCI_LOG(n, f, x...)

#endif

// ---------------------------------
// Return BOOL : TRUE   - card exist
// Return BOOL : FALSE  - card not exist
// ---------------------------------
BOOL sdhci_card_exist(void)
{
#if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530)

    if (_u4SDPwrCdPinCfg == 1)
    {
        //以下CKGEN 的 register 都 program成 0，VIND7才可做 GPIO 用：
        CKGEN_CLRBIT(0xC0, ((UINT32)0x01 << 26));    // 00C0[26]
        CKGEN_CLRBIT(0xC8, ((UINT32)0xFF << 24) | ((UINT32)0x3F << 16));    // 00C8[31:24], 00C8[22:16]
        CKGEN_CLRBIT(0xCC, ((UINT32)0x0F << 0));    // 00CC[3:0]
        CKGEN_CLRBIT(0xD0, ((UINT32)0x3F << 22));    // 00D0[28:22]
        CKGEN_CLRBIT(0xD4, ((UINT32)0x01 << 3));    // 00D4[3]
        
        // 設定I/O方向（0：input、1：output）
        CKGEN_CLRBIT(0x188, ((UINT32)0x01 << 4));    // 0188[4]

        return (CKGEN_READ32(0x1C8) & ((UINT32)0x01 << 4)) ? FALSE : TRUE;
    }
    else if (_u4SDPwrCdPinCfg == 2)
    {
        // Use VOUTCLK2 as Power controller Pin
        // 以下bit需先set為0才可以當GPIO
        CKGEN_CLRBIT(0xC4, ((UINT32)0x01 << 1)); // pad_cfg_1[1] = 00C4h[1]
        CKGEN_CLRBIT(0xCC, ((UINT32)0x03 << 0)); // pad_cfg_3[1:0] = 00CCh[1:0]
        CKGEN_CLRBIT(0xCC, ((UINT32)0x01 << 4)); // pad_cfg_3[4] = 00CCh[4]
        CKGEN_CLRBIT(0xD4, ((UINT32)0x01 << 5)); // pad_cfg_5[5] = 00D4h[5]
               
        // 設定I/O方向（0：input、1：output）
        CKGEN_CLRBIT(0x188, ((UINT32)0x01 << 10));    // 0188h[10] (0: input, 1:output)

        // GPIO in read : 01C8h[10]
        return (CKGEN_READ32(0x1C8) & ((UINT32)0x01 << 10)) ? FALSE : TRUE;
    }
    
#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550)

    if (_u4SDPwrCdPinCfg == 1)
    {
        // GPIO-7
        // 設定I/O方向（0：input、1：output）
        CKGEN_CLRBIT(0x180, ((UINT32)0x01 << 7));    // 0180[7]

        return (CKGEN_READ32(0x1C0) & ((UINT32)0x01 << 7)) ? FALSE : TRUE; // 01C0[7]：input value
    }
    else if (_u4SDPwrCdPinCfg == 2)
    {
        // GPIO-8
        PDWNC_SETBIT(0xF4, ((UINT32)1<<30));    // 0xF00240F4[30] = 1b1
        //PDWNC_CLRBIT(0xF8, ((UINT32)1<<30));    // 0xF00240F8[30] = 1b0
        
        // 設定I/O方向（0：input、1：output）
        PDWNC_CLRBIT(0xD4, ((UINT32)1<<30));    // 0xF00240D4[30] = 1b0

        return (PDWNC_READ32(0xD0) & ((UINT32)0x01 << 30)) ? FALSE : TRUE; // 0xF00240D0[30]：input value
    }
    
#endif    

    return FALSE;
}

//-------------------------------------------------------------------------
/** _Fcihw_ChangeSettings
 *  Modify settings according to current FCI clock
 *  @param i4ClkMHz    clock setting (Mhz).
 *  @return  VOID
 */
//-------------------------------------------------------------------------
static VOID _Fcihw_ChangeSettings(INT32 i4ClkMHz)
{       
    if (i4ClkMHz > 25)
    {
        _u4FciHwRstWtTimes = 1;
    }
    else if (i4ClkMHz >= 18)
    {
        _u4FciHwRstWtTimes = 3;
    }
    else if (i4ClkMHz >= 13)
    {
        _u4FciHwRstWtTimes = 4;
    }
    else if (i4ClkMHz >= 6)
    {
        _u4FciHwRstWtTimes = 8;
    }
    else if (i4ClkMHz >= 3)
    {
        _u4FciHwRstWtTimes = 16;
    }
    else if (i4ClkMHz >= 1)
    {
        _u4FciHwRstWtTimes = 64;
    }
    else
    {
        _u4FciHwRstWtTimes = 256;
    }

    _u4FciHwRstWtTimes *= 10;
}

//-------------------------------------------------------------------------
/** FCIHW_SDDrivingCurrent
 *  Set SD clock
 *  @param i4ClkMHz    clock setting (Mhz).
 *  @return  VOID
 */
//-------------------------------------------------------------------------

static VOID FCIHW_SDDrivingCurrent(INT32 i4ClkMHz)
{
#if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530)
    switch(_u4SDMultiFuncCfg)
    {
        case 1:
        {
            // Driving current setting
            if (i4ClkMHz >= 27)
            {
                // Set driving current E4+E8
                CKGEN_SETBIT(REG_RW_PAD_CTRL_6, ((UINT32)1 <<16) | ((UINT32)1 <<17)); // E2+E4 for CLK pin
                CKGEN_SETBIT(REG_RW_PAD_CTRL_6, ((UINT32)1 <<24) | ((UINT32)1 <<25)); // E2+E4 for CMD pin                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_6, ((UINT32)1 <<20) | ((UINT32)1 <<21)); // E2+E4 for D0 pin                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<4) | ((UINT32)1 <<5)); // E2+E4 for D1 pin                                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<0) | ((UINT32)1 <<1)); // E2+E4 for D2 pin                                                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_6, ((UINT32)1 <<28) | ((UINT32)1 <<29)); // E2+E4 for D3 pin                                
            }
            else
            {
                // Set driving current E2
                CKGEN_SETBIT(REG_RW_PAD_CTRL_6, ((UINT32)1 <<16)); // E2 for CLK pin
                CKGEN_SETBIT(REG_RW_PAD_CTRL_6, ((UINT32)1 <<24)); // E2 for CMD pin                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_6, ((UINT32)1 <<20)); // E2 for D0 pin                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<4)); // E2 for D1 pin                                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<0)); // E2 for D2 pin                                                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_6, ((UINT32)1 <<28)); // E2 for D3 pin   

                CKGEN_CLRBIT(REG_RW_PAD_CTRL_6, ((UINT32)1 <<17)); // E2 for CLK pin
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_6, ((UINT32)1 <<25)); // E2 for CMD pin                
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_6, ((UINT32)1 <<21)); // E2 for D0 pin                                
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<5)); // E2 for D1 pin                                                
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<1)); // E2 for D2 pin                                                                
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_6, ((UINT32)1 <<29)); // E2 for D3 pin
            }
        }
        break;

        case 2:
        {
            // Driving current setting
            if (i4ClkMHz >= 27)
            {
                // Set driving current E4+E8
                CKGEN_SETBIT(REG_RW_PAD_CTRL_0, ((UINT32)1 <<16) | ((UINT32)1 <<17)); // E2+E4 for CLK pin
                CKGEN_SETBIT(REG_RW_PAD_CTRL_1, ((UINT32)1 <<4) | ((UINT32)1 <<5)); // E2+E4 for CMD pin                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_0, ((UINT32)1 <<20) | ((UINT32)1 <<21)); // E2+E4 for D0 pin                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_1, ((UINT32)1 <<0) | ((UINT32)1 <<1)); // E2+E4 for D1 pin                                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_1, ((UINT32)1 <<14) | ((UINT32)1 <<15)); // E2+E4 for D2 pin                                                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_1, ((UINT32)1 <<9) | ((UINT32)1 <<10)); // E2+E4 for D3 pin                                
            }
            else
            {
                // Set driving current E2
                CKGEN_SETBIT(REG_RW_PAD_CTRL_0, ((UINT32)1 <<16)); // E2 for CLK pin
                CKGEN_SETBIT(REG_RW_PAD_CTRL_1, ((UINT32)1 <<4)); // E2 for CMD pin                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_0, ((UINT32)1 <<20)); // E2 for D0 pin                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_1, ((UINT32)1 <<0)); // E2 for D1 pin                                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_1, ((UINT32)1 <<14)); // E2 for D2 pin                                                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_1, ((UINT32)1 <<9)); // E2 for D3 pin   

                CKGEN_CLRBIT(REG_RW_PAD_CTRL_0, ((UINT32)1 <<17)); // E2 for CLK pin
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_1, ((UINT32)1 <<5)); // E2 for CMD pin                
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_0, ((UINT32)1 <<21)); // E2 for D0 pin                                
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_1, ((UINT32)1 <<1)); // E2 for D1 pin                                                
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_1, ((UINT32)1 <<15)); // E2 for D2 pin                                                                
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_1, ((UINT32)1 <<10)); // E2 for D3 pin 
            }
        }
        break;
                
        case 3:
        {
            // Driving current setting
            if (i4ClkMHz >= 27)
            {
                // Set driving current E4+E8
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<16) | ((UINT32)1 <<17)); // E2+E4 for CLK pin
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<12) | ((UINT32)1 <<13)); // E2+E4 for CMD pin                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<20) | ((UINT32)1 <<21)); // E2+E4 for D0 pin                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<8) | ((UINT32)1 <<9)); // E2+E4 for D1 pin                                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<0) | ((UINT32)1 <<1)); // E2+E4 for D2 pin                                                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<4) | ((UINT32)1 <<5)); /// E2+E4 for D3 pin                                
            }
            else
            {
                // Set driving current E2
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<16)); // E2 for CLK pin
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<12)); // E2 for CMD pin                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<20)); // E2 for D0 pin                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<8)); // E2 for D1 pin                                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<0)); // E2 for D2 pin                                                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<4)); // E2 for D3 pin   

                CKGEN_CLRBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<17)); // E2 for CLK pin
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<13)); // E2 for CMD pin                
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<21)); // E2 for D0 pin                                
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<9)); // E2 for D1 pin                                                
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<1)); // E2 for D2 pin                                                                
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<5)); // E2 for D3 pin 
            }
        }
        break;
                
        case 4:
        {
            // Driving current setting
            if (i4ClkMHz >= 27)
            {
                // Set driving current E4+E8
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<24) | ((UINT32)1 <<25)); // E2+E4 for CLK pin
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<20) | ((UINT32)1 <<21)); // E2+E4 for CMD pin                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<28) | ((UINT32)1 <<29)); // E2+E4 for D0 pin                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<16) | ((UINT32)1 <<17)); // E2+E4 for D1 pin                                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<8) | ((UINT32)1 <<9)); // E2+E4 for D2 pin                                                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<12) | ((UINT32)1 <<13)); // E2+E4 for D3 pin                                
            }
            else
            {
                // Set driving current E2
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<24)); // E2 for CLK pin
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<20)); // E2 for CMD pin                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<28)); // E2 for D0 pin                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<16)); // E2 for D1 pin                                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<8)); // E2 for D2 pin                                                                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<12)); // E2 for D3 pin   

                CKGEN_CLRBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<25)); // E2 for CLK pin
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<21)); // E2 for CMD pin                
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<29)); // E2 for D0 pin                                
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<17)); // E2 for D1 pin                                                
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<9)); // E2 for D2 pin                                                                
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_7, ((UINT32)1 <<13)); // E2 for D3 pin 
            }
        }
        break;
        
        case 5:
        {
            // Driving current setting
            if (i4ClkMHz >= 27)
            {
                // Set driving current E4+E8
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<0) | ((UINT32)1 <<1)); // E2+E4 for CLK pin
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<8) | ((UINT32)1 <<9)); // E2+E4 for CMD pin                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<4) | ((UINT32)1 <<5)); // E2+E4 for D0 pin                              
            }
            else
            {
                // Set driving current E2
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<0)); // E2 for CLK pin
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<8)); // E2 for CMD pin                
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<4)); // E2 for D0 pin  

                CKGEN_CLRBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<1)); // E2 for CLK pin
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<9)); // E2 for CMD pin                
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_10, ((UINT32)1 <<5)); // E2 for D0 pin  
            }
        }
        break;
    }
#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550)
    switch(_u4SDMultiFuncCfg)
    {
        case 1:
        {
            // Driving current setting
            if (i4ClkMHz >= 27)
            {
                // Set driving current E4+E8
                // Set driving current E4+E8
                PDWNC_SETBIT(0xE4, ((UINT32)1<<13));  // E2 for CLK pin
                PDWNC_SETBIT(0xE8, ((UINT32)1<<13));  // E4 for CLK pin
                PDWNC_SETBIT(0xE4, ((UINT32)1<<20));  // E2 for CMD pin
                PDWNC_SETBIT(0xE8, ((UINT32)1<<20));  // E4 for CMD pin
                PDWNC_SETBIT(0xE4, ((UINT32)1<<22));  // E2 for D0 pin
                PDWNC_SETBIT(0xE8, ((UINT32)1<<22));  // E4 for D0 pin
                PDWNC_SETBIT(0xE4, ((UINT32)1<<17));  // E2 for D1 pin
                PDWNC_SETBIT(0xE8, ((UINT32)1<<17));  // E4 for D1 pin
                PDWNC_SETBIT(0xE4, ((UINT32)1<<18));  // E2 for D2 pin
                PDWNC_SETBIT(0xE8, ((UINT32)1<<18));  // E4 for D2 pin
                PDWNC_SETBIT(0xE4, ((UINT32)1<<19));  // E2 for D3 pin
                PDWNC_SETBIT(0xE8, ((UINT32)1<<19));  // E4 for D3 pin 			
            }
            else
            {
                // Set driving current E2
                PDWNC_SETBIT(0xE4, ((UINT32)1<<13));  // E2 for CLK pin
                PDWNC_SETBIT(0xE4, ((UINT32)1<<20));  // E2 for CMD pin
                PDWNC_SETBIT(0xE4, ((UINT32)1<<22));  // E2 for D0 pin
                PDWNC_SETBIT(0xE4, ((UINT32)1<<17));  // E2 for D1 pin
                PDWNC_SETBIT(0xE4, ((UINT32)1<<18));  // E2 for D2 pin
                PDWNC_SETBIT(0xE4, ((UINT32)1<<19));  // E2 for D3 pin
		 // clear driving current E4
                PDWNC_CLRBIT(0xE8, ((UINT32)1<<13));  // E4 for CLK pin
                PDWNC_CLRBIT(0xE8, ((UINT32)1<<20));  // E4 for CMD pin
                PDWNC_CLRBIT(0xE8, ((UINT32)1<<22));  // E4 for D0 pin
                PDWNC_CLRBIT(0xE8, ((UINT32)1<<17));  // E4 for D1 pin
                PDWNC_CLRBIT(0xE8, ((UINT32)1<<18));  // E4 for D2 pin
                PDWNC_CLRBIT(0xE8, ((UINT32)1<<19));  // E4 for D3 pin 
            }
        }
        break;

        case 2:
        {
            // Driving current setting
            if (i4ClkMHz >= 27)
            {
                // Set driving current E2+E4
                CKGEN_SETBIT(0x0104, ((UINT32)1 <<4) | ((UINT32)1 <<5));    // E2+E4 for CLK pin
                CKGEN_SETBIT(0x0108, ((UINT32)1 <<2) | ((UINT32)1 <<3));    // E2+E4 for CMD pin
                CKGEN_SETBIT(0x0104, ((UINT32)1 <<10) | ((UINT32)1 <<11)); // E2+E4 for D0 pin
                CKGEN_SETBIT(0x0104, ((UINT32)1 <<28) | ((UINT32)1 <<29)); // E2+E4 for D1 pin
                CKGEN_SETBIT(0x0108, ((UINT32)1 <<14) | ((UINT32)1 <<15)); // E2+E4 for D2 pin
                CKGEN_SETBIT(0x0108, ((UINT32)1 <<8) | ((UINT32)1 <<9));    // E2+E4 for D3 pin
            }
            else
            {
                // Set driving current E2
                CKGEN_SETBIT(0x0104, ((UINT32)1 <<4));   // E2 for CLK pin
                CKGEN_SETBIT(0x0108, ((UINT32)1 <<2));   // E2 for CMD pin
                CKGEN_SETBIT(0x0104, ((UINT32)1 <<10)); // E2 for D0 pin
                CKGEN_SETBIT(0x0104, ((UINT32)1 <<28)); // E2 for D1 pin
                CKGEN_SETBIT(0x0108, ((UINT32)1 <<14)); // E2 for D2 pin
                CKGEN_SETBIT(0x0108, ((UINT32)1 <<8));  // E2 for D3 pin
               // clear driving current E4
                CKGEN_CLRBIT(0x0104, ((UINT32)1 <<5));   // E4 for CLK pin
                CKGEN_CLRBIT(0x0108, ((UINT32)1 <<3));   // E4 for CMD pin
                CKGEN_CLRBIT(0x0104, ((UINT32)1 <<11)); // E4 for D0 pin
                CKGEN_CLRBIT(0x0104, ((UINT32)1 <<29)); // E4 for D1 pin
                CKGEN_CLRBIT(0x0108, ((UINT32)1 <<15)); // E4 for D2 pin
                CKGEN_CLRBIT(0x0108, ((UINT32)1 <<9));  // E4 for D3 pin
            }
        }
        break;

        case 3:
        {
            // Driving current setting
            if (i4ClkMHz >= 27)
            {
                // Set driving current E4+E8
                CKGEN_SETBIT(0x0128, ((UINT32)1 <<22) | ((UINT32)1 <<23));  // E2+E4 for CLK pin
                CKGEN_SETBIT(0x0128, ((UINT32)1 <<16) | ((UINT32)1 <<17));  // E2+E4 for CMD pin
                CKGEN_SETBIT(0x0128, ((UINT32)1 <<28) | ((UINT32)1 <<29));  // E2+E4 for D0 pin
                CKGEN_SETBIT(0x0128, ((UINT32)1 <<10) | ((UINT32)1 <<11));  // E2+E4 for D1 pin
                CKGEN_SETBIT(0x0124, ((UINT32)1 <<30) | ((UINT32)1 <<31));  // E2+E4 for D2 pin
                CKGEN_SETBIT(0x0128, ((UINT32)1 <<4) | ((UINT32)1 <<5));     // E2+E4 for D3 pin
            }
            else
            {
                // Set driving current E2
                CKGEN_SETBIT(0x0128, ((UINT32)1 <<22)); // E2 for CLK pin
                CKGEN_SETBIT(0x0128, ((UINT32)1 <<16)); // E2 for CMD pin
                CKGEN_SETBIT(0x0128, ((UINT32)1 <<28)); // E2 for D0 pin
                CKGEN_SETBIT(0x0128, ((UINT32)1 <<10)); // E2 for D1 pin
                CKGEN_SETBIT(0x0124, ((UINT32)1 <<30)); // E2 for D2 pin
                CKGEN_SETBIT(0x0128, ((UINT32)1 <<4));  // E2 for D3 pin
               // clear drving current E4
                CKGEN_CLRBIT(0x0128, ((UINT32)1 <<23)); // E4 for CLK pin
                CKGEN_CLRBIT(0x0128, ((UINT32)1 <<17)); // E4 for CMD pin
                CKGEN_CLRBIT(0x0128, ((UINT32)1 <<29)); // E4 for D0 pin
                CKGEN_CLRBIT(0x0128, ((UINT32)1 <<11)); // E4 for D1 pin
                CKGEN_CLRBIT(0x0124, ((UINT32)1 <<31)); // E4 for D2 pin
                CKGEN_CLRBIT(0x0128, ((UINT32)1 <<5));  // E4 for D3 pin
            }
        }
        break;

        case 4:
        {
            // Driving current setting
            if (i4ClkMHz >= 27)
            {
                // Set driving current E4+E8
                CKGEN_SETBIT(0x0114, ((UINT32)1 <<20) | ((UINT32)1 <<21));  // E2+E4 for CLK pin
                CKGEN_SETBIT(0x0114, ((UINT32)1 <<26) | ((UINT32)1 <<27));  // E2+E4 for CMD pin
                CKGEN_SETBIT(0x0118, ((UINT32)1 <<18) | ((UINT32)1 <<19));  // E2+E4 for D0 pin
                CKGEN_SETBIT(0x0118, ((UINT32)1 <<12) | ((UINT32)1 <<13));  // E2+E4 for D1 pin
                CKGEN_SETBIT(0x0118, ((UINT32)1 <<0) | ((UINT32)1 <<1));     // E2+E4 for D2 pin
                CKGEN_SETBIT(0x0118, ((UINT32)1 <<6) | ((UINT32)1 <<7));     // E2+E4 for D3 pin
            }
            else
            {
                // Set driving current E2
                CKGEN_SETBIT(0x0114, ((UINT32)1 <<20));  // E2 for CLK pin
                CKGEN_SETBIT(0x0114, ((UINT32)1 <<26));  // E2 for CMD pin
                CKGEN_SETBIT(0x0118, ((UINT32)1 <<18));  // E2 for D0 pin
                CKGEN_SETBIT(0x0118, ((UINT32)1 <<12));  // E2 for D1 pin
                CKGEN_SETBIT(0x0118, ((UINT32)1 <<0));   // E2 for D2 pin
                CKGEN_SETBIT(0x0118, ((UINT32)1 <<6));   // E2 for D3 pin
               //clear drving current E4
                CKGEN_CLRBIT(0x0114, ((UINT32)1 <<21));  // E4 for CLK pin
                CKGEN_CLRBIT(0x0114, ((UINT32)1 <<27));  // E4 for CMD pin
                CKGEN_CLRBIT(0x0118, ((UINT32)1 <<19));  // E4 for D0 pin
                CKGEN_CLRBIT(0x0118, ((UINT32)1 <<13));  // E4 for D1 pin
                CKGEN_CLRBIT(0x0118, ((UINT32)1 <<1));   // E4 for D2 pin
                CKGEN_CLRBIT(0x0118, ((UINT32)1 <<7));   // E4 for D3 pin
            }
        }
        break;

        case 5:
        {
            // Driving current setting
            if (i4ClkMHz >= 27)
            {
                // Set driving current E4+E8
                CKGEN_SETBIT(0x0124, ((UINT32)1 <<30) | ((UINT32)1 <<31)); // E2+E4 for CLK pin
                CKGEN_SETBIT(0x0128, ((UINT32)1 <<10) | ((UINT32)1 <<11)); // E2+E4 for CMD pin
                CKGEN_SETBIT(0x0128, ((UINT32)1 <<4) | ((UINT32)1 <<5)); // E2+E4 for D0 pin
            }
            else
            {
                // Set driving current E2
                CKGEN_SETBIT(0x0124, ((UINT32)1 <<30)); // E2 for CLK pin
                CKGEN_SETBIT(0x0128, ((UINT32)1 <<10)); // E2 for CMD pin
                CKGEN_SETBIT(0x0128, ((UINT32)1 <<4)); // E2 for D0 pin

                CKGEN_CLRBIT(0x0124, ((UINT32)1 <<31)); // E2 for CLK pin
                CKGEN_CLRBIT(0x0128, ((UINT32)1 <<11)); // E2 for CMD pin
                CKGEN_CLRBIT(0x0128, ((UINT32)1 <<5)); // E2 for D0 pin
            }
        }
        break;
    }

#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555)
    switch(_u4SDMultiFuncCfg)
    {
        case 1: // multi-funciton 1
        {
            // Driving current setting
            if (i4ClkMHz >= 27)
            {
                // Set driving current E2+E4
                CKGEN_SETBIT(0x150, ((UINT32)1 <<8) | ((UINT32)1 <<9)); // E2+E4 for CLK pin
                CKGEN_SETBIT(0x150, ((UINT32)1 <<2) | ((UINT32)1 <<3)); // E2+E4 for CMD pin
                CKGEN_SETBIT(0x150, ((UINT32)1 <<14) | ((UINT32)1 <<15)); // E2+E4 for D0 pin
                CKGEN_SETBIT(0x150, ((UINT32)1 <<21) | ((UINT32)1 <<22)); // E2+E4 for D1 pin
                CKGEN_SETBIT(0x150, ((UINT32)1 <<26) | ((UINT32)1 <<27)); // E2+E4 for D2 pin
                CKGEN_SETBIT(0x154, ((UINT32)1 <<0) | ((UINT32)1 <<1)); // E2+E4 for D3 pin
            }
            else
            {
                // Set driving current E2
                CKGEN_SETBIT(0x150, ((UINT32)1 <<8)); // E2 for CLK pin
                CKGEN_SETBIT(0x150, ((UINT32)1 <<2)); // E2 for CMD pin
                CKGEN_SETBIT(0x150, ((UINT32)1 <<14)); // E2 for D0 pin
                CKGEN_SETBIT(0x150, ((UINT32)1 <<21)); // E2 for D1 pin
                CKGEN_SETBIT(0x150, ((UINT32)1 <<26)); // E2 for D2 pin
                CKGEN_SETBIT(0x154, ((UINT32)1 <<0)); // E2 for D3 pin

                CKGEN_CLRBIT(0x150, ((UINT32)1 <<9)); // E4 for CLK pin
                CKGEN_CLRBIT(0x150, ((UINT32)1 <<3)); // E4 for CMD pin
                CKGEN_CLRBIT(0x150, ((UINT32)1 <<15)); // E4 for D0 pin
                CKGEN_CLRBIT(0x150, ((UINT32)1 <<22)); // E4 for D1 pin
                CKGEN_CLRBIT(0x150, ((UINT32)1 <<27)); // E4 for D2 pin
                CKGEN_CLRBIT(0x154, ((UINT32)1 <<1)); // E4 for D3 pin
            }
        }
        break;

        case 2: // multi-funciton 2
        {
            // Driving current setting
            if (i4ClkMHz >= 27)
            {
                // Set driving current E2+E4
                CKGEN_SETBIT(0x104, ((UINT32)1 <<4) | ((UINT32)1 <<5)); // E2+E4 for CLK pin
                CKGEN_SETBIT(0x108, ((UINT32)1 <<2) | ((UINT32)1 <<3)); // E2+E4 for CMD pin
                CKGEN_SETBIT(0x104, ((UINT32)1 <<10) | ((UINT32)1 <<11)); // E2+E4 for D0 pin
                CKGEN_SETBIT(0x104, ((UINT32)1 <<28) | ((UINT32)1 <<29)); // E2+E4 for D1 pin
                CKGEN_SETBIT(0x108, ((UINT32)1 <<14) | ((UINT32)1 <<15)); // E2+E4 for D2 pin
                CKGEN_SETBIT(0x108, ((UINT32)1 <<8) | ((UINT32)1 <<9)); // E2+E4 for D3 pin
            }
            else
            {
                // Set driving current E2
                CKGEN_SETBIT(0x104, ((UINT32)1 <<4)); // E2 for CLK pin
                CKGEN_SETBIT(0x108, ((UINT32)1 <<2)); // E2 for CMD pin
                CKGEN_SETBIT(0x104, ((UINT32)1 <<10)); // E2 for D0 pin
                CKGEN_SETBIT(0x104, ((UINT32)1 <<28)); // E2 for D1 pin
                CKGEN_SETBIT(0x108, ((UINT32)1 <<14)); // E2 for D2 pin
                CKGEN_SETBIT(0x108, ((UINT32)1 <<8)); // E2 for D3 pin

                CKGEN_CLRBIT(0x104, ((UINT32)1 <<5)); // E2 for CLK pin
                CKGEN_CLRBIT(0x108, ((UINT32)1 <<3)); // E2 for CMD pin
                CKGEN_CLRBIT(0x104, ((UINT32)1 <<11)); // E2 for D0 pin
                CKGEN_CLRBIT(0x104, ((UINT32)1 <<29)); // E2 for D1 pin
                CKGEN_CLRBIT(0x108, ((UINT32)1 <<15)); // E2 for D2 pin
                CKGEN_CLRBIT(0x108, ((UINT32)1 <<9)); // E2 for D3 pin
            }
        }
        break;

        case 3: // multi-function 3
        {
            // Driving current setting
            if (i4ClkMHz >= 27)
            {
                // Set driving current E2+E4
                CKGEN_SETBIT(0x114, ((UINT32)1 <<20) | ((UINT32)1 <<21)); // E2+E4 for CLK pin
                CKGEN_SETBIT(0x114, ((UINT32)1 <<26) | ((UINT32)1 <<27)); // E2+E4 for CMD pin
                CKGEN_SETBIT(0x118, ((UINT32)1 <<18) | ((UINT32)1 <<19)); // E2+E4 for D0 pin
                CKGEN_SETBIT(0x118, ((UINT32)1 <<12) | ((UINT32)1 <<13)); // E2+E4 for D1 pin
                CKGEN_SETBIT(0x118, ((UINT32)1 <<0) | ((UINT32)1 <<1)); // E2+E4 for D2 pin
                CKGEN_SETBIT(0x118, ((UINT32)1 <<6) | ((UINT32)1 <<7)); /// E2+E4 for D3 pin
            }
            else
            {
                // Set driving current E2
                CKGEN_SETBIT(0x118, ((UINT32)1 <<20)); // E2 for CLK pin
                CKGEN_SETBIT(0x118, ((UINT32)1 <<26)); // E2 for CMD pin
                CKGEN_SETBIT(0x118, ((UINT32)1 <<18)); // E2 for D0 pin
                CKGEN_SETBIT(0x118, ((UINT32)1 <<12)); // E2 for D1 pin
                CKGEN_SETBIT(0x118, ((UINT32)1 <<0)); // E2 for D2 pin
                CKGEN_SETBIT(0x118, ((UINT32)1 <<6)); // E2 for D3 pin

                CKGEN_CLRBIT(0x118, ((UINT32)1 <<21)); // E2 for CLK pin
                CKGEN_CLRBIT(0x118, ((UINT32)1 <<27)); // E2 for CMD pin
                CKGEN_CLRBIT(0x118, ((UINT32)1 <<19)); // E2 for D0 pin
                CKGEN_CLRBIT(0x118, ((UINT32)1 <<13)); // E2 for D1 pin
                CKGEN_CLRBIT(0x118, ((UINT32)1 <<1)); // E2 for D2 pin
                CKGEN_CLRBIT(0x118, ((UINT32)1 <<7)); // E2 for D3 pin
            }
        }
        break;
    }
#endif
}        

static BOOL CKGEN_AgtOnClk(e_CLK_T eAgt)
{
    UINT32 u4Tmp;

#if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530) 

    if (eAgt < e_CLK_MAX) {    /* CONFG 2: IR, SD, MS, ABIST*/
        u4Tmp = CKGEN_READ32(REG_RW_CLK_CFG2);
        switch (eAgt) {
            case e_CLK_SD:
                u4Tmp = u4Tmp & (~CLK_PDN_SD);
                break;
            case e_CLK_MS:
                u4Tmp = u4Tmp & (~CLK_PDN_MS);
                break;
            default:
                return FALSE;
        }
        CKGEN_WRITE32(REG_RW_CLK_CFG2, u4Tmp);
    } else {
        return FALSE;
    }

#elif ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550) ||  (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555))

    if (eAgt < e_CLK_SVO_STDBY) {    // CONFIG 2: IR, SD, MS, ABIST
        u4Tmp = CKGEN_READ32(REG_RW_CLK_CFG2);
        switch (eAgt) {
            case e_CLK_SD:
                u4Tmp = u4Tmp & (~CLK_PDN_SD);
                break;
            case e_CLK_MS:
                u4Tmp = u4Tmp & (~CLK_PDN_MS);
                break;
            default:
                return FALSE;
        }
        CKGEN_WRITE32(REG_RW_CLK_CFG2, u4Tmp);
    } else {
        return FALSE;
    }
    
#endif

    return TRUE;
}

static BOOL CKGEN_AgtOffClk(e_CLK_T eAgt)
{
    UINT32 u4Tmp;

#if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530) 

    if (eAgt < e_CLK_MAX) {    /* CONFG 2: IR, SD, MS, ABIST*/
        u4Tmp = CKGEN_READ32(REG_RW_CLK_CFG2);
        switch (eAgt) {
            case e_CLK_SD:
                u4Tmp = u4Tmp | CLK_PDN_SD;
                break;
            case e_CLK_MS:
                u4Tmp = u4Tmp | CLK_PDN_MS;
                break;
            default:
                return FALSE;
        }
        CKGEN_WRITE32(REG_RW_CLK_CFG2, u4Tmp);
    } else {
        return FALSE;
    }

#elif ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550) || (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555))

    if (eAgt < e_CLK_SVO_STDBY) {    // CONFIG 2: IR, SD, MS, ABIST
        u4Tmp = CKGEN_READ32(REG_RW_CLK_CFG2);
        switch (eAgt) {
            case e_CLK_SD:
                u4Tmp = u4Tmp | CLK_PDN_SD;
                break;
            case e_CLK_MS:
                u4Tmp = u4Tmp | CLK_PDN_MS;
                break;
            default:
                return FALSE;
        }
        CKGEN_WRITE32(REG_RW_CLK_CFG2, u4Tmp);
    }
    else {
        return FALSE;
    }
    
#endif
    
    return TRUE;
}

static BOOL CKGEN_AgtSelClk(e_CLK_T eAgt, UINT32 u4Sel)
{
    UINT32 u4Tmp;

#if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530) 

    if (eAgt < e_CLK_MAX) {    /* CONFG 2: IR, SD, MS, ABIST*/
        u4Tmp = CKGEN_READ32(REG_RW_CLK_CFG2);
        switch (eAgt) {
            case e_CLK_SD:
                u4Sel = (u4Sel << CLK_CLK_SD_SEL_OFFSET) & CLK_CLK_SD_SEL_MASK;
                u4Tmp = u4Tmp & (~CLK_CLK_SD_SEL_MASK);
                u4Tmp = u4Tmp | u4Sel;
                break;
            case e_CLK_MS:
                u4Sel = (u4Sel << CLK_CLK_MS_SEL_OFFSET) & CLK_CLK_MS_SEL_MASK;
                u4Tmp = u4Tmp & (~CLK_CLK_MS_SEL_MASK);
                u4Tmp = u4Tmp | u4Sel;
                break;
            default:
                return FALSE;
        }
        CKGEN_WRITE32(REG_RW_CLK_CFG2, u4Tmp);
    } else {
        return FALSE;
    }
    
#elif ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550) ||  (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555)) 

    if (eAgt < e_CLK_SVO_STDBY) {    // CONFIG 2: IR, SD, MS, ABIST
        u4Tmp = CKGEN_READ32(REG_RW_CLK_CFG2);
        switch (eAgt) {
            case e_CLK_SD:
                u4Sel = (u4Sel << CLK_CLK_SD_SEL_OFFSET) & CLK_CLK_SD_SEL_MASK;
                u4Tmp = u4Tmp & (~CLK_CLK_SD_SEL_MASK);
                u4Tmp = u4Tmp | u4Sel;
                break;
            case e_CLK_MS:
                u4Sel = (u4Sel << CLK_CLK_MS_SEL_OFFSET) & CLK_CLK_MS_SEL_MASK;
                u4Tmp = u4Tmp & (~CLK_CLK_MS_SEL_MASK);
                u4Tmp = u4Tmp | u4Sel;
                break;
            default:
                return FALSE;
        }
        CKGEN_WRITE32(REG_RW_CLK_CFG2, u4Tmp);
    } else {
        return FALSE;
    }
    
#endif

    return TRUE;    
}

//-------------------------------------------------------------------------
/** FCIHW_ChangeSDCClock
 *  Set SD clock
 *  @param i4ClkMHz    clock setting (Mhz).
 *  @return  VOID
 */
//-------------------------------------------------------------------------
static VOID FCIHW_ChangeSDCClock(INT32 i4ClkMHz)
{   
#if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530)

    switch(i4ClkMHz)    // unit : Mhz
    {
        case 54:
            {
                _u4SDClockSetting = 54;
                // 0x7000_0078 bit[14:8] = 001_0000, SD_CLK = 54.35 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                break;
            }

        case 50:
            {
                _u4SDClockSetting = 50;
                // Switch to 54Mhz then switch back to 50 Mhz
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = 100_0000, SD_CLK = 50.20 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL2_1_12);
                break;
            }
            
        case 43:
            {
                _u4SDClockSetting = 43;
                // Switch to 54Mhz then switch back to 43Mhz
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = 010_0000, SD_CLK = 43.48 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_10);
                break;
            }
            
        case 36:
            {
                _u4SDClockSetting = 36;
                // Switch to 54Mhz then switch back to 36 Mhz
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = 011_0000, SD_CLK = 36.23 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_12);
                break;
            }
            
        case 34:
            {
                _u4SDClockSetting = 34;
                // Switch to 54Mhz then switch back to 34 Mhz
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = 101_0000, SD_CLK = 33.75 Mhz (DMPLL/8 = 270/8 = 33.75 Mhz)
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_DMPLL_1_8);
                break;
            }
            
        case 33:
            {
                _u4SDClockSetting = 33;
                // Switch to 54Mhz then switch back to 33 Mhz
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = 110_0000, SD_CLK = 33.47
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL2_1_18);
                break;
            }
            
        case 27:
            {
                _u4SDClockSetting = 27;
                // Switch to 54Mhz then switch back to 27 Mhz
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                
                // 0x7000_0078 bit[14:8] = 000_0000, SD_CLK = 27.03 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M);
                break;
            }
                        
        case 13:
        default:
            {
                _u4SDClockSetting = 13;
                // Switch to 54Mhz then switch back to 27 Mhz
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = 111_0001, SD_CLK = 13.50 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M_1_2);
                break;
            }            

        case 6:
            {
                _u4SDClockSetting = 6;
                // Switch to 54Mhz then switch back to 27 Mhz
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = 111_0010, SD_CLK = 6.75 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M_1_4);
                break;
            }            
            
        case 3:
            {
                _u4SDClockSetting = 3;
                // Switch to 54Mhz then switch back to 27 Mhz
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                //  0x7000_0078 bit[14:8] = 111_0011, SD_CLK = 3.375 MHz  OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M_1_8);
                break;
            }        
            
        case 1:
            {
                _u4SDClockSetting = 1;
                // Switch to 54Mhz then switch back to 210Khz
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                //  0x7000_0078 bit[14:8] = 111_0100, SD_CLK = 1.688 MHz  OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M_1_16);
                break;
            }
        
        case 0:
            {
                _u4SDClockSetting = 0;
                // Switch to 54Mhz then switch back to 210Khz
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                //  0x7000_0078 bit[14:8] = 111_0110, SD_CLK = 0.422 MHz  OK
                // CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M_1_64);
                //  0x7000_0078 bit[14:8] = 111_0111, SD_CLK = 0.211 MHz  OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M_1_128);
                break;

                // For reference
                // 0x7000_0078 bit[14:8] = 111_0101, SD_CLK = 0.844 MHz  OK
                // 0x7000_0078 bit[14:8] = 111_0111, SD_CLK = 0.211 MHz  OK
                // 0x7000_0078 bit[14:8] = 111_1000, SD_CLK = 0.105 MHz  OK
                // 0x7000_0078 bit[14:8] = 111_1XXX, SD_CLK = 0.105 MHz  OK                
            }
    }
     
#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550)

   switch (i4ClkMHz)
   {
        case 54:
            {
                _u4SDClockSetting = 54;
                // 0x7000_0078 bit[14:8] = xxx_0001, SD_CLK = 54 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                break;
            }

        case 50:
            {
                _u4SDClockSetting = 50;
                // 0x7000_0078 bit[14:8] = xxx_0100, SD_CLK = 50 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL2_1_12);
                break;
            }
            
        case 43:
            {
                _u4SDClockSetting = 43;
                // 0x7000_0078 bit[14:8] = 010_0010, SD_CLK = 43.2 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_10);
                break;
            }

        case 37:
            {
                _u4SDClockSetting = 37;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);                  
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = xxx_0101, SD_CLK = 37.5 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_DMPLL_1_8);
                break;
            }

			
        case 36:
            {
                _u4SDClockSetting = 36;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);                  
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = xxx_0011, SD_CLK = 36 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_12);
                break;
            }
            
        case 33:
            {
                _u4SDClockSetting = 33;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);                  
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = xxx_0110, SD_CLK = 33.33MHz
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL2_1_18);
                break;
            }
            
        case 27:
            {
                _u4SDClockSetting = 27;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);                  
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = xxx_0000, SD_CLK = 27 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M);
                break;
            }

        case 23:
            {
                _u4SDClockSetting = 23;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);                  
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = xxx_0111, SD_CLK = 23.08 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL2_1_26);
                break;
            }

        case 18:
            {
                _u4SDClockSetting = 18;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);                  
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = xxx_0111, SD_CLK = 23.08 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_24);
                break;
            }
                        
        case 13:
        default:
            {
                _u4SDClockSetting = 13;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);                  
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = 000_1001, SD_CLK = 13.50 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M_1_2);
                break;
            }            

        case 6:
            {
                _u4SDClockSetting = 6;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);                  
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = 001_1001, SD_CLK = 6.75 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M_1_4);
                break;
            }            
            
        case 3:
            {
                _u4SDClockSetting = 3;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);                  
                x_thread_delay(50);
                //  0x7000_0078 bit[14:8] = 010_1001, SD_CLK = 3.38 MHz  OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M_1_8);
                break;
            }        
            
        case 1:
            {
                _u4SDClockSetting = 1;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);                  
                x_thread_delay(50);
                //  0x7000_0078 bit[14:8] = 011_0100, SD_CLK = 1.69 MHz  OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M_1_16);
                break;
            }  
            
        case 0:
            {
                _u4SDClockSetting = 0;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);                  
                x_thread_delay(50);
                //  0x7000_0078 bit[14:8] = 101_1001, SD_CLK = 421.885 KHz  OK
                // CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M_1_64);
                // 0x7000_0078 bit[14:8] = 110_1001, SD_CLK = 210.94 KHz  OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M_1_128);
                break;

                // For reference
                // 0x7000_0078 bit[14:8] = 100_1001, SD_CLK = 843.75 KHz  OK
                // 0x7000_0078 bit[14:8] = 110_1001, SD_CLK = 210.94 KHz  OK
                // 0x7000_0078 bit[14:8] = 111_1001, SD_CLK = 105.47 KHz  OK

            }
    } 

#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555)

    switch(i4ClkMHz)    // unit : Mhz
    {
        case 54:
            {
                _u4SDClockSetting = 54;
                // 0x7000_0078 bit[14:8] = xxx_0001, SD_CLK = 54 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                break;
            }

        case 50:
            {
                _u4SDClockSetting = 50;
                // 0x7000_0078 bit[14:8] = xxx_0100, SD_CLK = 50 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL2_1_12);
                break;
            }
            
        case 43:
            {
                _u4SDClockSetting = 43;
                // 0x7000_0078 bit[14:8] = xxx_0010, SD_CLK = 43.2 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_10);
                break;
            }

        case 37:
            {
                _u4SDClockSetting = 37;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = xxx_0101, SD_CLK = 37.5 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_DMPLL_1_8);
                break;
            }
		
        case 36:
            {
                _u4SDClockSetting = 36;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = xxx_0011, SD_CLK = 36 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_12);
                break;
            }
            
        case 33:
            {
                _u4SDClockSetting = 33;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = xxx_0110, SD_CLK = 33.3 Mhz
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL2_1_18);
                break;
            }
            
        case 27:
            {
                _u4SDClockSetting = 27;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = xxx_0000, SD_CLK = 27.03 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M);
                break;
            }

        case 23:
            {
                _u4SDClockSetting = 23;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = xxx_0111, SD_CLK = 23.08 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL2_1_26);
                break;
            }

        case 18:
            {
                _u4SDClockSetting = 18;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = xxx_1000, SD_CLK = 18 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_24);
                break;
            }
		
        case 13:
        default:
            {
                _u4SDClockSetting = 13;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = 000_1001, SD_CLK = 13.50 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M_1_2);
                break;
            }            

        case 6:
            {
                _u4SDClockSetting = 6;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = 001_1001, SD_CLK = 6.75 MHz OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M_1_4);
                break;
            }            
            
        case 3:
            {
                _u4SDClockSetting = 3;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                //  0x7000_0078 bit[14:8] = 010_1001, SD_CLK = 3.38 MHz  OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M_1_8);
                break;
            }        
            
        case 1:
            {
                _u4SDClockSetting = 1;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                //  0x7000_0078 bit[14:8] = 011_1001, SD_CLK = 1.69 MHz  OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M_1_16);
                break;
            }  
            
        case 0:
            {
                _u4SDClockSetting = 0;
                // Switch to 54Mhz then switch back
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_SYSPLL1_1_8);
                x_thread_delay(50);
                // 0x7000_0078 bit[14:8] = 110_1001, SD_CLK = 210 KHz  OK
                CKGEN_AgtSelClk(e_CLK_SD, CLK_CLK_SD_SEL_27M_1_128);
                break;

                // For reference
                // 0x7000_0078 bit[14:8] = 100_1001, SD_CLK = 840 KHz  OK
                // 0x7000_0078 bit[14:8] = 101_1001, SD_CLK = 420 KHz  OK
                // 0x7000_0078 bit[14:8] = 110_1001, SD_CLK = 210 KHz  OK
                // 0x7000_0078 bit[14:8] = 111_1001, SD_CLK = 105 KHz  OK                
            }
    } 

#endif
 
    fci_host.clk = i4ClkMHz;
 
    FCIHW_SDDrivingCurrent(i4ClkMHz);

    _Fcihw_ChangeSettings((INT32)_u4SDClockSetting);

    // Wait clock stable
    udelay(100000);

    if (_u4SDClockSetting == 0)
    {
        FCI_LOG(FCI_LOG_CLK, "SD Clock Set to 210 Khz (clk minus: %d)\n", fci_host.sd_clk_minus);    
    }
    else
    {
        FCI_LOG(FCI_LOG_CLK, "SD Clock Set to %d Mhz (clk minus: %d)\n", _u4SDClockSetting, fci_host.sd_clk_minus);    
    }
}

static void sdhci_set_clock(struct sdhci_host *host, UINT32 clock)
{
    u8 clk;  
   
#if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530)
    if (clock >= 36000000)
      sd_clk_index = 7;
    else if (clock >= 34000000)
      sd_clk_index = 6;
    else if (clock >= 33000000)
      sd_clk_index = 5;
    else if (clock >= 27000000)
      sd_clk_index = 4;
    else if (clock >= 25000000)
      sd_clk_index = 3;
    else if (clock >= 13000000)
      sd_clk_index = 3;
    else if (clock >= 6000000)
      sd_clk_index = 2;
    else if (clock >= 3000000)
      sd_clk_index = 1;
    else
      sd_clk_index = 0;
#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550) || (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555)
    if (clock >= 43000000)
      sd_clk_index = 10;
    else if (clock >= 37000000)
      sd_clk_index = 9;
    else if (clock >= 36000000)
      sd_clk_index = 8;
    else if (clock >= 33000000)
      sd_clk_index = 7;
    else if (clock >= 27000000)
      sd_clk_index = 6;
    else if (clock >= 23000000)
      sd_clk_index = 5;
    else if (clock >= 18000000)
      sd_clk_index = 4;
    else if (clock >= 13000000)
      sd_clk_index = 3;
    else if (clock >= 6000000)
      sd_clk_index = 2;
    else if (clock >= 3000000)
      sd_clk_index = 1;
    else
      sd_clk_index = 0;
#endif

    if (sd_clk_index - host->sd_clk_minus >= 0)
    {
        clk = sd_clk_array[sd_clk_index - host->sd_clk_minus];
    }
    else
    {
        clk = sd_clk_array[0];
    }
    
    FCI_LOG(FCI_LOG_DBG, "sd_clk_index = %d, sd_clk_minus = %d, clk = %d Mhz\n", sd_clk_index, host->sd_clk_minus, clk);

    /*
     * Only write to the clock register when
     * there is an actual change.
     */
    if (clk != host->clk) 
    {
       FCIHW_ChangeSDCClock((INT32)clk);
    }
}

//-------------------------------------------------------------------------
/** SDCHW_InitSemaphore
 *  Make sure all SD semaphore is in lock state.
 *  @param  VOID
 *  @retval   VOID.
 */
//-------------------------------------------------------------------------
VOID SDCHW_InitSemaphore(struct sdhci_host *host)
{ 
    // Clear FCI Status variable
    _u4FCISStatus = 0x0;
}

//-------------------------------------------------------------------------
/** _Sdchw_WaitWBFBit
 *  Check if the end of write busy status on data line.
 *  @param  VOID.
 *  @retval   S_OK       Success.
 *  @retval   Others      Fail.
 */
//-------------------------------------------------------------------------
static INT32 _Sdchw_WaitWBFBit(VOID)
{
    UINT32 u4Val;

#ifdef CC_CARD_DETECT_PIN
     // handle interrupt status only in card inserted state.
    if (!(sdhci_card_exist() || fci_host.fakeIns))
    {
        FCI_LOG(FCI_LOG_ERROR, "(%d) No Card error.\n", __LINE__);
        return E_NO_CARD;
    }
#endif

    // read the status
    u4Val = FCI_READ32(RW_FCI_INTR_STAT_REG);

    // Save the status for further use
    _u4FCISStatus |= u4Val;

    // error: time out.
    if (u4Val&(INTR_SD_WDTOI))
    {
        FCI_LOG(FCI_LOG_ERROR, "(%d) Wait TO IntrStat:0x%08x\n", __LINE__, u4Val);
        _u4SdDbgError |= (0x01 << 1);
        return E_CMD_TIMEOUT;
    }
    // error: CRC error.
    else if (u4Val&(INTR_SD_WRCRCI))
    {
        FCI_LOG(FCI_LOG_ERROR, "(%d) Wait CRC IntrStat:0x%08x\n", __LINE__, u4Val);
        _u4SdDbgError |= (0x01 << 2);
        return E_CMD_RSP_CRC_ERR;
    }
    // error: FLASH programming error.
    else if (u4Val&(INTR_SD_FPEI))
    {
        FCI_LOG(FCI_LOG_ERROR, "(%d) Wait FPE IntrStat:0x%08x\n", __LINE__, u4Val);
        _u4SdDbgError |= (0x01 << 3);
        return E_CMD_RSP_CRC_ERR;
    }
#if (FCI_INTERRUPT_ARCHITECTURE == FCI_NEW_INTERRUPT_ARCHITECTURE)    // MT8550/8555 use INTR_SD_DTRDYI instead of INTR_SD_NWRITEBUSY
    else if (u4Val & INTR_SD_DTRDYI)
#else
    else if (u4Val & INTR_SD_NWRITEBUSY)
#endif
    {
        FCI_LOG(FCI_LOG_SDHWCMD, "(%d) IntrStat:0x%08x\n", __LINE__,
        FCI_READ32(RW_FCI_INTR_STAT_REG));
        return S_OK;
    }

    FCI_LOG(FCI_LOG_ERROR, "Wait Idle is timeout. IntrStat:0x%08x\n", u4Val);
    _u4SdDbgError |= (0x01 << 4);

    return E_NO_RESPONSE;
}

//-------------------------------------------------------------------------
/** _Sdchw_WaitDataRdyBit
 *  Check if data line ready.
 *  @param  VOID.
 *  @retval   S_OK       Success.
 *  @retval   Others      Fail.
 */
//-------------------------------------------------------------------------
static INT32 _Sdchw_WaitDataRdyBit(VOID)
{
    UINT32 u4Val;

#ifdef CC_CARD_DETECT_PIN
     // handle interrupt status only in card inserted state.
    if (!(sdhci_card_exist() || fci_host.fakeIns))
    {
        FCI_LOG(FCI_LOG_ERROR, "(%d) No Card error.\n", __LINE__);
        return E_NO_CARD;
    }
#endif

    // read the status
    u4Val = FCI_READ32(RW_FCI_INTR_STAT_REG);

    // Save the status for further use
    _u4FCISStatus |= u4Val;

    // error: time out.
    if (u4Val&(INTR_SD_RDTOI))
    {
        FCI_LOG(FCI_LOG_ERROR, "(%d) Wait TO IntrStat:0x%08x\n", __LINE__, u4Val);
        _u4SdDbgError |= (0x01 << 5);
        return E_CMD_TIMEOUT;
    }
    // error: CRC error.
    else if (u4Val&(INTR_SD_RDCRCI))
    {
        FCI_LOG(FCI_LOG_ERROR, "(%d) Wait CRC IntrStat:0x%08x\n", __LINE__, u4Val);
        _u4SdDbgError |= (0x01 << 6);
        return E_CMD_RSP_CRC_ERR;
    }
    else if (u4Val & INTR_SD_DTRDYI)
    {
        FCI_LOG(FCI_LOG_SDHWCMD, "(%d) ok IntrStat:0x%08x\n",  __LINE__, u4Val);
        return S_OK;
    }

    FCI_LOG(FCI_LOG_ERROR, "Wait DataRdy is timeout. IntrStat:0x%08x\n", u4Val);

    _u4SdDbgError |= (0x01 << 7);

    return E_NO_RESPONSE;
}

//-------------------------------------------------------------------------
/** _Sdchw_WaitCmdRdyBit
 *  Check if command line ready.
 *  @param  VOID.
 *  @retval   S_OK       Success.
 *  @retval   Others      Fail.
 */
//-------------------------------------------------------------------------
static INT32 _Sdchw_WaitCmdRdyBit(VOID)
{
    UINT32 u4Val;

#ifdef CC_CARD_DETECT_PIN
     // handle interrupt status only in card inserted state.
    if (!(sdhci_card_exist() || fci_host.fakeIns))
    {
        FCI_LOG(FCI_LOG_ERROR, "(%d) No Card error.\n", __LINE__);
        return E_NO_CARD;
    }
#endif

    // read the status
    u4Val = FCI_READ32(RW_FCI_INTR_STAT_REG);

    // Save the status for further use
    _u4FCISStatus |= u4Val;
    
    // error: time out.
    if (u4Val&(INTR_SD_CMDTOI))
    {
        FCI_LOG(FCI_LOG_ERROR, "Wait TO IntrStat:0x%08x\n", u4Val);
        _u4SdDbgError |= (0x01 << 8);
        return E_CMD_TIMEOUT;
    }
    // error: CRC error.
    else if (u4Val&(INTR_SD_CMDCRCI))
    {
        FCI_LOG(FCI_LOG_ERROR, "Wait CRC IntrStat:0x%08x\n", u4Val);
        _u4SdDbgError |= (0x01 << 9);
        return E_CMD_RSP_CRC_ERR;
    }
    else if (u4Val & INTR_SD_CMDRDYI)
    {
        FCI_LOG(FCI_LOG_SDHWCMD, "ok IntrStat:0x%08x\n", u4Val);
        return S_OK;
    }

    FCI_LOG(FCI_LOG_DBG, "Wait CmdRdy is timeout, u4Val = 0x%x\n", u4Val);
    _u4SdDbgError |= (0x01 << 10);

    return E_NO_RESPONSE;
}

//-------------------------------------------------------------------------
/** _Sdchw_WaitCmdRdyOrTo
 *  Wait command line ready. Use in commad write operation.
 *  @param VOID.
 *  @retval  S_OK  Success.
 *  @retval  Others  Fail.
 */
//-------------------------------------------------------------------------
static INT32 _Sdchw_WaitCmdRdyOrTo(struct sdhci_host *host, struct mmc_cmd *cmd)
{
    INT32 i4Ret;
    UINT32 u4Val;
    long u4JiffSt = 0, u4JiffEnd = 0, u4RbTime;
    
    // if (_fgFCIIsrEn)
    {
         // handle interrupt status only in card inserted state.
#ifdef CC_CARD_DETECT_PIN
        if (!(sdhci_card_exist() || host->fakeIns))
        {
            FCI_LOG(FCI_LOG_ERROR, "(%d) No Card error.\n", __LINE__);
            return E_NO_CARD;
        }
#endif

        i4Ret = _Sdchw_WaitCmdRdyBit();

        if (i4Ret != S_OK)
        {
            switch (i4Ret)
            {
                case E_CMD_TIMEOUT:
                    host->u4CmdError = TIMEOUT;
                    break;
                case E_CMD_RSP_CRC_ERR:
                    host->u4CmdError = UNUSABLE_ERR;
                    // Decrease freq
                    host->sd_clk_minus++;
                    sdhci_set_clock(host, host->clk);
                    break;
                case E_NO_CARD:
                    host->u4CmdError = NO_CARD_ERR;
                    break;
                case E_NO_RESPONSE:
                    host->u4CmdError = TIMEOUT;
                    break;
                default:
                    break;
            }
            
            FCI_LOG(FCI_LOG_ERROR, "Wait CmdRdy Error, Cmd: %d, Arg: 0x%x, i4Ret: 0x%x, host->u4CmdError: 0x%x\n", cmd->cmdidx, cmd->cmdarg, i4Ret, host->u4CmdError);
            _u4SdDbgError |= (0x01 << 11);
        }
        
        if (i4Ret == E_NO_RESPONSE)
        {
            FCI_LOG(FCI_LOG_ERROR, "Wait CmdRdy Error of no response IntrStat:0x%08x <= Failed @@\n", FCI_READ32(RW_FCI_INTR_STAT_REG));
            _u4SdDbgError |= (0x01 << 13);
        }

        //NOTICE: R1B type response should wait for data line at 0.
        //handle R1B type response.
        if (cmd->resp_type == MMC_RSP_R1b)
        {
            u4JiffSt = get_timer(0);
            
            while (1)
            {
                 // handle interrupt status only in card inserted state.
                u4Val = FCI_READ32(RW_FCI_INTR_STAT_REG);
                if (u4Val & INTR_SD_RESPBUSY)
                {
                    u4JiffEnd = get_timer(0);
                    u4RbTime = (u4JiffEnd - u4JiffSt) * 1000 / CONFIG_SYS_HZ;   // unit : ms
       
                    if (u4RbTime > 10000) // 10 second timeout
                    {
                        FCI_LOG(FCI_LOG_ERROR, "Response Busy Error, u4RbTime = %d !!\n", (u32)u4RbTime);
                        _u4SdDbgError |= (0x01 << 14);
                        break;
                    }
                }
                else
                {
                    FCI_LOG(FCI_LOG_SDHWCMD, "Response busy u4RbTime = %d !!\n", (u32)u4RbTime);
                    break;
                }
            }
        }

        return i4Ret;
    }

    FCI_LOG(FCI_LOG_ERROR, "No Response Error !!\n");
    _u4SdDbgError |= (0x01 << 25);
                        
    return E_NO_RESPONSE;
}

//-------------------------------------------------------------------------
/** SDCHW_SetMultiFlag
 *  Set SD multiple block counter enable.
 *  @param fgMulti    TRUE: enable multi-block. FALSE: disable multi-block.
 *  @return  VOID.
 */
//-------------------------------------------------------------------------
static VOID SDCHW_SetMultiFlag(BOOL fgMulti)
{
    FCI_LOG(FCI_LOG_SDHWCMD, "fgMulti = %s\n", fgMulti ? "TRUE" : "FALSE");
    
    if (fgMulti)
    {
        FCI_WRITE32(RW_FCI_CTRL_REG, FCI_READ32(RW_FCI_CTRL_REG) | CTRL_SD_MLBLCNEN);
    }
    else
    {
        FCI_WRITE32(RW_FCI_CTRL_REG,
        FCI_READ32(RW_FCI_CTRL_REG) & ~(CTRL_SD_MLBLCNEN));
    }
}

//-------------------------------------------------------------------------
/** SDCHW_ReadResp
 *  read SD response.
 *  @param index    response index, range in 1~4..
 *  @return  response value.
 */
//-------------------------------------------------------------------------
static UINT32 SDCHW_ReadResp(INT32 index)
{
    return FCI_READ32((UINT32)(RO_SD_RESP1_REG + (4 *(index - 1))));
}

//-------------------------------------------------------------------------
/** SDCHW_Set4BitOn
 *  set SD wide bus enable
 *  @param fgSet    TRUE: 4 bits data line, FALSE: 1 bit data line.
 *  @return  VOID
 */
//-------------------------------------------------------------------------
static VOID SDCHW_Set4BitOn(BOOL fgSet)
{
    UINT32 u4Val;

    FCI_LOG(FCI_LOG_SDHWCMD, "Sd Host Bus switch to to %d Mode ~~\n", fgSet ? 4:1);

    u4Val = FCI_READ32(RW_FCI_CTRL_REG);

    // Setup FCI hardware bus width
    if (fgSet)
    {
        FCI_WRITE32(RW_FCI_CTRL_REG, u4Val | CTRL_SD_WIDEBUSEN);

#if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530)
        CKGEN_CLRBIT(REG_RW_PAD_CFG_5, ((UINT32)1 <<14));
        // 0x7000_00d4 bit[14] set to 1'b0, SD dat1 be bi-directional mode
#elif ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550) || (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555)) 
        CKGEN_CLRBIT(0xE0,(( (UINT32)1)<<27)); //2009/11/5, [27]=0 enalbe PAD 4 bit mode
#endif
    }
    else
    {
        FCI_WRITE32(RW_FCI_CTRL_REG, u4Val&(~CTRL_SD_WIDEBUSEN));

#if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530)
        CKGEN_SETBIT(REG_RW_PAD_CFG_5, ((UINT32)1 <<14));
        // When SDIO 1-bit mode is needed, 0x7000_00d4 bit[14] should be set to 1'b1
#elif ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550) || (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555))
        CKGEN_SETBIT(0xE0, (((UINT32)1)<<27)); // 2009/11/5	 
#endif
    }
}

static void sdhci_get_short_reply(struct mmc_cmd *cmd)
{
    cmd->response[0]  = SDCHW_ReadResp(1);
}

static void sdhci_get_long_reply(struct mmc_cmd *cmd)
{
    cmd->response[0]  = SDCHW_ReadResp(1);
    cmd->response[1]  = SDCHW_ReadResp(2);
    cmd->response[2]  = SDCHW_ReadResp(3);
    cmd->response[3]  = SDCHW_ReadResp(4);
}

//-------------------------------------------------------------------------
/** SDCHW_CmdIdx
 *  SD command response index. 3 bits only. Due to insufficient register space.
 *  It show the LSB 3 bits of SD cmd.
 *  @param VOID.
 *  @return  SD command index.
 */
//-------------------------------------------------------------------------
static UINT32 SDCHW_CmdIdx(VOID)
{
    return ((FCI_READ32(RW_FCI_INTR_STAT_REG) & INTR_SD_CMD_IDX) >> 24);
}

//-------------------------------------------------------------------------
/** _SDC_CheckStatus
 *  check card response
 *  @param  pu2RCA  point to RCA buffer.
 *  @retval   S_OK : Success.
 *  @retval   Others : Fail.
 */
//-------------------------------------------------------------------------
static INT32 _SDC_CheckStatus(UINT32 u4CmdIdx)
{
    UINT32 u4Val;

    INT32 i4Count;

    u4Val = SDCHW_CmdIdx();
    
    FCI_LOG(FCI_LOG_SDHWCMD , "RegIdx is 0x%02x (u4CmdIdx & 0x07) is 0x%02x\n", u4Val, (u4CmdIdx & 0x07));
    
    i4Count = 0;

    do
    {
        u4Val = SDCHW_CmdIdx();

        i4Count++;
        if (i4Count > 5)
        {
            FCI_LOG(FCI_LOG_ERROR , "Retry : RegIdx is 0x%02x (u4CmdIdx & 0x07) is 0x%02x, i4Count = %d\n", u4Val, (u4CmdIdx & 0x07), i4Count);
            _u4SdDbgError |= (0x01 << 17);
            return E_STATUS;
        }
    } while (u4Val != (u4CmdIdx & 0x07));

    u4Val = SDCHW_ReadResp(1);

    if ((u4Val & SDC_CSTA_MASK) == 0)
    {
        return S_OK;
    }
    /* NOTICE: THIS IS VERY TRICKY.... !!!!!
         if next sector is out of range, this bit will on.
        so, let's ignore this bit. XXX */
    if ((u4Val & SDC_CSTA_MASK) == SDC_OUT_OF_RANGE)
    {
        return S_OK;
    }

    FCI_LOG(FCI_LOG_ERROR, "SDC Check status failed. u4Val:0x%08x\n", u4Val);
    _u4SdDbgError |= (0x01 << 18);

    if (u4Val & SDC_CARD_IS_LOCKED)
    {
        FCI_LOG(FCI_LOG_ERROR, "SDC Check status failed, Card is locked. u4Val:0x%08x\n", u4Val);
        _u4SdDbgError |= (0x01 << 19);
        return E_CARD_IS_LOCKED;
    }

    return E_STATUS;
}

//-------------------------------------------------------------------------
/** FCIHW_HWConfigSetup
 *  Setup multi-func, driving current, pull up resistor ... HW setting.
 *  @param  i4Slot  slot number.
 *  @return  VOID.
 */
//-------------------------------------------------------------------------
static VOID FCIHW_HWConfigSetup(INT32 i4Slot)
{
    UINT32 u4Reg = 0;
    
#if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530)

    if (i4Slot == SDC_SLOT)
    {
        switch(_u4SDMultiFuncCfg)
        {
            case 1: // multi-func 1
            { 
                // (1) Multiple function
                // 1. 0x7000_00D4 bit[17:15] = 3
                // 1. 0x7000_00D4 bit[20:18] = 3
                // 1. 0x7000_00D4 bit[23:21] = 3                
                u4Reg = CKGEN_READ32(0xD4);
                u4Reg &= ~(((UINT32)0x07 << 15) | ((UINT32)0x07 << 18) | ((UINT32)0x07 << 21));
                u4Reg |=   (((UINT32)0x03 << 15) | ((UINT32)0x03 << 18) | ((UINT32)0x03 << 21));
                CKGEN_WRITE32(0xD4, u4Reg);
                
                // 2. 0x7000_00C0 bit[27] = 0                
                // 2. 0x7000_00C0 bit[28] = 0 
                u4Reg = CKGEN_READ32(0xC0);
                u4Reg &= ~(((UINT32)0x01 << 27) | ((UINT32)0x01 << 28));
                u4Reg |=   (((UINT32)0x00 << 27) | ((UINT32)0x00 << 28)); 
                CKGEN_WRITE32(0xC0, u4Reg);
                
                // 3. 0x7000_00CC bit[5] = 0 
                u4Reg = CKGEN_READ32(0xC0);
                u4Reg &= ~((UINT32)0x01 << 5);
                u4Reg |=   ((UINT32)0x00 << 5); 
                CKGEN_WRITE32(0xC0, u4Reg);  

                // Setup Pull up register
                CKGEN_SETBIT(REG_RW_PAD_CTRL_6, (((UINT32)1) << 26)); // VIND10, SD-CMD, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_6, (((UINT32)1) << 27)); // VIND10, SD-CMD, Clear PD
                CKGEN_SETBIT(REG_RW_PAD_CTRL_6, (((UINT32)1) << 22)); // VIND9, SD-D0, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_6, (((UINT32)1) << 23)); // VIND9, SD-D0, Clear PD
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, (((UINT32)1) << 6)); // VIND13, SD-D1, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_7, (((UINT32)1) << 7)); // VIND13, SD-D1, Clear PD
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, (((UINT32)1) << 2)); // VIND12, SD-D2, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_7, (((UINT32)1) << 3)); // VIND12, SD-D2, Clear PD
                CKGEN_SETBIT(REG_RW_PAD_CTRL_6, (((UINT32)1) << 30)); // VIND11, SD-D3, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_6, (((UINT32)1) << 31)); // VIND11, SD-D3, Clear PD                

                // 4 bit configuration
                _fgFCI4BitBusEnable = TRUE;
                break;
            }
            case 2: // multi-func 2
            {
                // (1) Multiple function
                // 1. 0x7000_00D4 bit[17:15] = 4
                // 1. 0x7000_00D4 bit[20:18] = 4
                // 1. 0x7000_00D4 bit[23:21] = 4
                u4Reg = CKGEN_READ32(0xD4);
                u4Reg &= ~(((UINT32)0x07 << 15) | ((UINT32)0x07 << 18) | ((UINT32)0x07 << 21));
                u4Reg |=   (((UINT32)0x04 << 15) | ((UINT32)0x04 << 18) | ((UINT32)0x04 << 21));
                CKGEN_WRITE32(0xD4, u4Reg);
                
                // 2. 0x7000_00C0 bit[20:0] = 0
                u4Reg = CKGEN_READ32(0xC0);
                u4Reg &= ~((UINT32)0x1FFFFF << 0);
                u4Reg |=   ((UINT32)0x00 << 0);
                CKGEN_WRITE32(0xC0, u4Reg);

                // Setup Pull up register
                CKGEN_SETBIT(REG_RW_PAD_CTRL_1, (((UINT32)1) << 6)); // SPMCLK, SD-CMD, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_1, (((UINT32)1) << 7)); // SPMCLK, SD-CMD, Clear PD
                CKGEN_SETBIT(REG_RW_PAD_CTRL_0, (((UINT32)1) << 22)); // AOSDATA4, SD-D0, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_0, (((UINT32)1) << 23)); // AOSDATA4, SD-D0, Clear PD
                CKGEN_SETBIT(REG_RW_PAD_CTRL_1, (((UINT32)1) << 2)); // SPDATA, SD-D1, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_1, (((UINT32)1) << 3)); // SPDATA, SD-D1, Clear PD
                CKGEN_SETBIT(REG_RW_PAD_CTRL_1, (((UINT32)1) << 16)); // SPLRCK, SD-D2, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_1, (((UINT32)1) << 17)); // SPLRCK, SD-D2, Clear PD
                CKGEN_SETBIT(REG_RW_PAD_CTRL_1, (((UINT32)1) << 11)); // SPBCK, SD-D3, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_1, (((UINT32)1) << 12)); // SPBCK, SD-D3, Clear PD
                
                // 4 bit configuration
                _fgFCI4BitBusEnable = TRUE;
                break;
            }
            case 3: // multi-func 3
            {
                // (1) Multiple function
                // 1. 0x7000_00D4 bit[17:15] = 1
                // 1. 0x7000_00D4 bit[20:18] = 1
                // 1. 0x7000_00D4 bit[23:21] = 1
                u4Reg = CKGEN_READ32(0xD4);
                u4Reg &= ~(((UINT32)0x07 << 15) | ((UINT32)0x07 << 18) | ((UINT32)0x07 << 21));
                u4Reg |=   (((UINT32)0x01 << 15) | ((UINT32)0x01 << 18) | ((UINT32)0x01 << 21));
                CKGEN_WRITE32(0xD4, u4Reg);

                // Setup Pull up register
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, (((UINT32)1) << 14)); // GPIO3, SD-CMD, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_10, (((UINT32)1) << 15)); // GPIO3, SD-CMD, Clear PD
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, (((UINT32)1) << 22)); // GPIO5, SD-D0, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_10, (((UINT32)1) << 23)); // GPIO5, SD-D0, Clear PD
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, (((UINT32)1) << 10)); // GPIO2, SD-D1, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_10, (((UINT32)1) << 11)); // GPIO2, SD-D1, Clear PD
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, (((UINT32)1) << 2)); // GPIO0, SD-D2, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_10, (((UINT32)1) << 3)); // GPIO0, SD-D2, Clear PD
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, (((UINT32)1) << 6)); // GPIO1, SD-D3, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_10, (((UINT32)1) << 7)); // GPIO1, SD-D3, Clear PD                
                
                // 4 bit configuration
                _fgFCI4BitBusEnable = TRUE;
                break;
            }
            case 4: // multi-func 4
            {
                // (1) Multiple function
                // 1. 0x7000_00D4 bit[17:15] = 2
                // 1. 0x7000_00D4 bit[20:18] = 2
                // 1. 0x7000_00D4 bit[23:21] = 2
                u4Reg = CKGEN_READ32(0xD4);
                u4Reg &= ~(((UINT32)0x07 << 15) | ((UINT32)0x07 << 18) | ((UINT32)0x07 << 21));
                u4Reg |=   (((UINT32)0x02 << 15) | ((UINT32)0x02 << 18) | ((UINT32)0x02 << 21));
                CKGEN_WRITE32(0xD4, u4Reg);

                // 3. 0x7000_00C0 bit[29] = 0
                // 2. 0x7000_00C0 bit[28] = 0                
                u4Reg = CKGEN_READ32(0xC0);
                u4Reg &= ~(((UINT32)0x01 << 28) | ((UINT32)0x01 << 29));
                u4Reg |=   (((UINT32)0x00 << 28) | ((UINT32)0x00 << 29));                 
                CKGEN_WRITE32(0xC0, u4Reg);
                
                // Setup Pull up register
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, (((UINT32)1) << 22)); // VIND17, SD-CMD, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_7, (((UINT32)1) << 23)); // VIND17, SD-CMD, Clear PD
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, (((UINT32)1) << 30)); // VIND19, SD-D0, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_7, (((UINT32)1) << 31)); // VIND19, SD-D0, Clear PD
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, (((UINT32)1) << 18)); // VIND16, SD-D1, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_7, (((UINT32)1) << 19)); // VIND16, SD-D1, Clear PD
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, (((UINT32)1) << 10)); // VIND14, SD-D2, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_7, (((UINT32)1) << 11)); // VIND14, SD-D2, Clear PD
                CKGEN_SETBIT(REG_RW_PAD_CTRL_7, (((UINT32)1) << 14)); // VIND15, SD-D3, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_7, (((UINT32)1) << 15)); // VIND15, SD-D3, Clear PD
                
                // 4 bit configuration
                _fgFCI4BitBusEnable = TRUE;
                break;
            }
            case 5: // multi-func 5
            {
                // (1) Multiple function
                // 1. 0x7000_00D4 bit[17:15] = 5
                u4Reg = CKGEN_READ32(0xD4);
                u4Reg &= ~((UINT32)0x07 << 15);
                u4Reg |=   ((UINT32)0x05 << 15);
                CKGEN_WRITE32(0xD4, u4Reg);      
                
                // Setup Pull up register
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, (((UINT32)1) << 10)); // GPIO2, SD-CMD, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_10, (((UINT32)1) << 11)); // GPIO2, SD-CMD, Clear PD
                CKGEN_SETBIT(REG_RW_PAD_CTRL_10, (((UINT32)1) << 6)); // GPIO1, SD-D0, Set PU
                CKGEN_CLRBIT(REG_RW_PAD_CTRL_10, (((UINT32)1) << 7)); // GPIO1, SD-D0, Clear PD
                
                // 4 bit configuration
                _fgFCI4BitBusEnable = FALSE;
                break;
            }
            default:
            {
                break;
            }
        }
    }
#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550) //mhzhang
    if (i4Slot == SDC_SLOT)
    {
        switch(_u4SDMultiFuncCfg)
        {
            case 1: // multi-func 1
            { 
                // (1) Multiple function
                // 1. 0x7000_00E0 bit[18:16] = 1
                // 1. 0x7000_00E0 bit[22:20] = 1
                // 1. 0x7000_00E0 bit[26:24] = 1                
                // 1. 0x7000_00E0 bit[30:28] = 1                                
                u4Reg = CKGEN_READ32(0xE0);
                u4Reg &= ~(((UINT32)0x07 << 16) | ((UINT32)0x07 << 20) | ((UINT32)0x07 << 24) | ((UINT32)0x07 << 28));
                u4Reg |=   (((UINT32)0x01 << 16) | ((UINT32)0x01 << 20) | ((UINT32)0x01 << 24) | ((UINT32)0x01 << 28));
                CKGEN_WRITE32(0xE0, u4Reg);

               // common setting // using the same GPIO , high porioty than FCI, disable them
               // 7000_00C4[5] = 0
               // 7000_00CC[7] = 0
               // 7000_00C4[5] = 0
               // 7000_00C4[6] = 0
               // 7000_00CC[7] = 0               
               CKGEN_CLRBIT(0xC4, ((UINT32)0x3)<<5);
               CKGEN_CLRBIT(0xCC, ((UINT32)0x1)<<7);
			   
                // Setup Pull up register
                // clock don't need to Pull-up or Pull-down
                //PDWNC_SETBIT(0x0EC, (((UINT32)1) <<13)); // EXRXD3, SD-CLK, Set PU 
                //PDWNC_CLRBIT(0x0F0, (((UINT32)1) <<13)); // EXRXD3, SD-CLK, Clear PD
                PDWNC_SETBIT(0x0EC, (((UINT32)1) <<20)); // ETTXD0, SD-CMD, Set PU
                PDWNC_CLRBIT(0x0F0, (((UINT32)1) <<20)); // ETTXD0, SD-CMD, Clear PD                
                PDWNC_SETBIT(0x0EC, (((UINT32)1) <<22)); // ETTXCLK, SD-D0, Set PU
                PDWNC_CLRBIT(0x0F0, (((UINT32)1) <<22)); // ETTXCLK, SD-D0, Clear PD               
                PDWNC_SETBIT(0x0EC, (((UINT32)1) <<17)); // ETTXD3, SD-D1, Set PU
                PDWNC_CLRBIT(0x0F0, (((UINT32)1) <<17)); // ETTXD3, SD-D1, Clear PD
                PDWNC_SETBIT(0x0EC, (((UINT32)1) <<18)); // ETTXD2, SD-D2, Set PU
                PDWNC_CLRBIT(0x0F0, (((UINT32)1) <<18)); // ETTXD2, SD-D2, Clear PD
                PDWNC_SETBIT(0x0EC, (((UINT32)1) <<19)); // ETTXD1, SD-D3, Set PU
                PDWNC_CLRBIT(0x0F0, (((UINT32)1) <<19)); // ETTXD1, SD-D3, Clear PD
                
                // 4 bit configuration
                _fgFCI4BitBusEnable = TRUE;
                break;
            }
            case 2: // multi-func 2
            {
                // (1) Multiple function
                // 1. 0x7000_00E0 bit[18:16] = 2
                // 1. 0x7000_00E0 bit[22:20] = 2
                // 1. 0x7000_00E0 bit[26:24] = 2
                // 1. 0x7000_00E0 bit[30:28] = 2                
                u4Reg = CKGEN_READ32(0xE0);
                u4Reg &= ~(((UINT32)0x07 << 16) | ((UINT32)0x07 << 20) | ((UINT32)0x07 << 24) | ((UINT32)0x07 << 28));
                u4Reg |=   (((UINT32)0x02 << 16) | ((UINT32)0x02 << 20) | ((UINT32)0x02 << 24) | ((UINT32)0x02 << 28));
                CKGEN_WRITE32(0xE0, u4Reg);
				
                //common setting // disable pirioty higher than FCI
                // 7000_00C0[9] = 0
                // 7000_00C0[20] = 0
                // 7000_00C0[10] = 0
                // 7000_00C0[17:15] = 0
                // 7000_00C0[18] = 0
                // 7000_00C0[19] = 0
                // 7000_00C0[1:0] = 0
               CKGEN_CLRBIT(0xC0, ((UINT32)0x1)<<9);
               CKGEN_CLRBIT(0xC0, ((UINT32)0x1)<<20);
               CKGEN_CLRBIT(0xC0, ((UINT32)0x1)<<10);		  
               CKGEN_CLRBIT(0xC0, ((UINT32)0x7)<<15);
               CKGEN_CLRBIT(0xC0, ((UINT32)0x1)<<18);
               CKGEN_CLRBIT(0xC0, ((UINT32)0x1)<<19);
               CKGEN_CLRBIT(0xC0, ((UINT32)0x3)<<0);
		  
                // Setup Pull up register
                // clock don't need to Pull-up or Pull -down
                //CKGEN_SETBIT(0x0104, (((UINT32)1) << 6));  // AOSDATA3, SD-CLK, Set PU
                //CKGEN_CLRBIT(0x0104, (((UINT32)1) << 7));  // AOSDATA3, MS-CLK, Clear PD
                CKGEN_SETBIT(0x0108, (((UINT32)1) << 4));  // SPMCLK, SD-CMD, Set PU
                CKGEN_CLRBIT(0x0108, (((UINT32)1) << 5));  // SPMCLK, MS-CMD, Clear PD                
                CKGEN_SETBIT(0x0104, (((UINT32)1) << 12)); // AOSDATA4, SD-D0, Set PU
                CKGEN_CLRBIT(0x0104, (((UINT32)1) << 13)); // AOSDATA4, MS-D0, Clear PD                
                CKGEN_SETBIT(0x0104, (((UINT32)1) << 30)); // SPDATA, SD-D1, Set PU
                CKGEN_CLRBIT(0x0104, (((UINT32)1) << 31)); // SPDATA, MS-D1, Clear PD                
                CKGEN_SETBIT(0x0108, (((UINT32)1) << 16)); // SPLRCK, SD-D2, Set PU
                CKGEN_CLRBIT(0x0108, (((UINT32)1) << 17)); // SPLRCK, MS-D2, Clear PD                
                CKGEN_SETBIT(0x0108, (((UINT32)1) << 10)); // SPBCK, SD-D3, Set PU
                CKGEN_CLRBIT(0x0108, (((UINT32)1) << 11)); // SPBCK, MS-D3, Clear PD                

                // 4 bit configuration
                _fgFCI4BitBusEnable = TRUE;
                break;
            }
            case 3: // multi-func 3
            {
                // (1) Multiple function
                // 1. 0x7000_00E0 bit[18:16] = 3
                // 1. 0x7000_00E0 bit[22:20] = 3
                // 1. 0x7000_00E0 bit[26:24] = 3
                // 1. 0x7000_00E0 bit[30:28] = 3                
                u4Reg = CKGEN_READ32(0xE0);
                u4Reg &= ~(((UINT32)0x07 << 16) | ((UINT32)0x07 << 20) | ((UINT32)0x07 << 24) | ((UINT32)0x07 << 28));
                u4Reg |=   (((UINT32)0x03 << 16) | ((UINT32)0x03 << 20) | ((UINT32)0x03 << 24) | ((UINT32)0x03 << 28));
                CKGEN_WRITE32(0xE0, u4Reg);

                // Setup Pull up register
                //CKGEN_SETBIT(0x0128, (((UINT32)1) << 24)); // GPIO4, SD-CMD, Set PU
                //CKGEN_CLRBIT(0x0128, (((UINT32)1) << 25)); // GPIO4, MS-CMD, Clear PD
                CKGEN_SETBIT(0x0128, (((UINT32)1) << 18)); // GPIO3, SD-CMD, Set PU
                CKGEN_CLRBIT(0x0128, (((UINT32)1) << 19)); // GPIO3, MS-CMD, Clear PD
                CKGEN_SETBIT(0x0128, (((UINT32)1) << 30)); // GPIO5, SD-CMD, Set PU
                CKGEN_CLRBIT(0x0128, (((UINT32)1) << 31)); // GPIO5, MS-CMD, Clear PD
                CKGEN_SETBIT(0x0128, (((UINT32)1) << 12)); // GPIO2, SD-CMD, Set PU
                CKGEN_CLRBIT(0x0128, (((UINT32)1) << 13)); // GPIO2, MS-CMD, Clear PD
                CKGEN_SETBIT(0x0128, (((UINT32)1) << 0));  // GPIO0, SD-CMD, Set PU
                CKGEN_CLRBIT(0x0128, (((UINT32)1) << 1));  // GPIO0, MS-CMD, Clear PD
                CKGEN_SETBIT(0x0128, (((UINT32)1) << 6));  // GPIO1, SD-CMD, Set PU
                CKGEN_CLRBIT(0x0128, (((UINT32)1) << 7));  // GPIO1, MS-CMD, Clear PD

                
                // 4 bit configuration
                _fgFCI4BitBusEnable = TRUE;
                break;
            }
            case 4: // multi-func 4
            {
                // (1) Multiple function
                // 1. 0x7000_00E0 bit[18:16] = 4
                // 1. 0x7000_00E0 bit[22:20] = 4
                // 1. 0x7000_00E0 bit[26:24] = 4
                // 1. 0x7000_00E0 bit[30:28] = 4                
                u4Reg = CKGEN_READ32(0xE0);
                u4Reg &= ~(((UINT32)0x07 << 16) | ((UINT32)0x07 << 20) | ((UINT32)0x07 << 24) | ((UINT32)0x07 << 28));
                u4Reg |=   (((UINT32)0x04 << 16) | ((UINT32)0x04 << 20) | ((UINT32)0x04 << 24) | ((UINT32)0x04 << 28));
                CKGEN_WRITE32(0xE0, u4Reg);

                // common setting  // disable pirioty higher than FCI
                // 7000_00C0[31]=0
                // 7000_00D4[2]=1
                CKGEN_CLRBIT(0xC0, ((UINT32)1)<<31);
                CKGEN_SETBIT(0xD4, ((UINT32)1)<<2);
				
                // Setup Pull up register
                //CKGEN_SETBIT(0x0114, (((UINT32)1) << 22)); // NFCEN2, SD-CLK, Set PU
                //CKGEN_CLRBIT(0x0114, (((UINT32)1) << 23)); // NFCEN2, MS-CLK, Clear PD
                CKGEN_SETBIT(0x0114, (((UINT32)1) << 28)); // NFRBN2, SD-CMD, Set PU
                CKGEN_CLRBIT(0x0114, (((UINT32)1) << 29)); // NFRBN2, MS-CMD, Clear PD
                CKGEN_SETBIT(0x0118, (((UINT32)1) << 20)); // SFDO, SD-D0, Set PU
                CKGEN_CLRBIT(0x0118, (((UINT32)1) << 21)); // SFDO, MS-D0, Clear PD
                CKGEN_SETBIT(0x0118, (((UINT32)1) << 14)); // SFDI, SD-D1, Set PU
                CKGEN_CLRBIT(0x0118, (((UINT32)1) << 15)); // SFDI, MS-D1, Clear PD
                CKGEN_SETBIT(0x0118, (((UINT32)1) << 2));  // SFCS, SD-D2, Set PU
                CKGEN_CLRBIT(0x0118, (((UINT32)1) << 3));  // SFCS, MS-D2, Clear PD
                CKGEN_SETBIT(0x0118, (((UINT32)1) << 8));  // SFCK, SD-D3, Set PU
                CKGEN_CLRBIT(0x0118, (((UINT32)1) << 9));  // SFCK, MS-D3, Clear PD

                
                // 4 bit configuration
                _fgFCI4BitBusEnable = TRUE;
                break;
            }
            case 5: // multi-func 5
            {
                // (1) Multiple function
                // 1. 0x7000_00E0 bit[18:16] = 5
                // 1. 0x7000_00E0 bit[22:20] = 5                
                u4Reg = CKGEN_READ32(0xE0);
                u4Reg &= ~(((UINT32)0x07 << 16) | ((UINT32)0x07 << 20));
                u4Reg |=   (((UINT32)0x05 << 16) |((UINT32)0x05 << 20));
                CKGEN_WRITE32(0xE0, u4Reg);      
                
                // Setup Pull up register
                //CKGEN_SETBIT(0x0128, (((UINT32)1) << 0));   // GPIO0, SD-CLK, Set PU
                //CKGEN_CLRBIT(0x0128, (((UINT32)1) << 1));   // GPIO0, SD-CLK, Clear PD
                CKGEN_SETBIT(0x0128, (((UINT32)1) << 12)); // GPIO2, SD-CMD, Set PU
                CKGEN_CLRBIT(0x0128, (((UINT32)1) << 13)); // GPIO2, SD-CMD, Clear PD
                CKGEN_SETBIT(0x0128, (((UINT32)1) << 6));  // GPIO1, SD-D0, Set PU
                CKGEN_CLRBIT(0x0128, (((UINT32)1) << 7));  // GPIO1, SD-D0, Clear PD
                
                // 4 bit configuration
                _fgFCI4BitBusEnable = FALSE;
                break;
            }
            default:
            {
                break;
            }
        }
    }

#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555)  // mhzhang

    if(i4Slot == SDC_SLOT)
    {
        switch(_u4SDMultiFuncCfg)
        {
            case 1:
            {
        		    //multifunction 1
        		    // 0x7000_00E0[18:16]=1
        		    // 0x7000_00E0[22:20]=1
        		    // 0x7000_00E0[26:24]=1
        		    // 0x7000_00E0[30:28]=1
        		    CKGEN_SETBIT(0xE0, ((UINT32)0x7)<<9);
        		    CKGEN_SETBIT(0xE0, ((UINT32)0x7)<<20);
        		    CKGEN_SETBIT(0xE0, ((UINT32)0x7)<<24);
        		    CKGEN_SETBIT(0xE0, ((UINT32)0x7)<<28);
        			
        		    //common setting
        		    CKGEN_CLRBIT(0xE4, ((UINT32)0x7)<<9);
        		    CKGEN_CLRBIT(0xE4, ((UINT32)0x3)<<12);
        		    CKGEN_CLRBIT(0xE4, ((UINT32)0x7)<<14);
        		    CKGEN_CLRBIT(0xE4, ((UINT32)0x3)<<4);
        			
        		    // set up Pull Up
        		    // enable Pull Up for SD
        		    // clock don't need Pull-up or Pull-down
        		    //CKGEN_SETBIT(0x150, ((UINT32)0x1)<<10);
        		    CKGEN_SETBIT(0x150, ((UINT32)0x1)<<4);
        		    CKGEN_SETBIT(0x150, ((UINT32)0x1)<<16);
        		    CKGEN_SETBIT(0x150, ((UINT32)0x1)<<23);
        		    CKGEN_SETBIT(0x150, ((UINT32)0x1)<<28);
        		    CKGEN_SETBIT(0x154, ((UINT32)0x1)<<2);
        		   // disable Pull Down of MS
        		    //CKGEN_CLRBIT(0x150, ((UINT32)0x1)<<11);
        		    CKGEN_CLRBIT(0x150, ((UINT32)0x1)<<5);
        		    CKGEN_CLRBIT(0x150, ((UINT32)0x1)<<17);
        		    CKGEN_CLRBIT(0x150, ((UINT32)0x1)<<24);
        		    CKGEN_CLRBIT(0x150, ((UINT32)0x1)<<29);
        		    CKGEN_CLRBIT(0x154, ((UINT32)0x1)<<3);				
 
                 // 4 bit configuration
                 _fgFCI4BitBusEnable = TRUE;
                 break;			
            }
     	      case 2:
     	      {
                  // multifunction 2:
                  // 0x7000_00E0[18:16] = 2
                  // 0x7000_00E0[22:20] = 2
                  // 0x7000_00E0[26:24] = 2
                  // 0x7000_00E0[30:28] = 2
                  CKGEN_SETBIT(0xE0, ((UINT32)0x7)<<16);
                  CKGEN_SETBIT(0xE0, ((UINT32)0x7)<<20);
                  CKGEN_SETBIT(0xE0, ((UINT32)0x7)<<24);
                  CKGEN_SETBIT(0xE0, ((UINT32)0x7)<<28);

                  //common setting
                  CKGEN_CLRBIT(0xC0, ((UINT32)0x3f)<<15);
                  CKGEN_CLRBIT(0xC0, ((UINT32)0x3)<<9);
                  CKGEN_CLRBIT(0xC0, ((UINT32)0x3)<<0);
                  CKGEN_CLRBIT(0xD4, ((UINT32)0x7)<<21);
                  CKGEN_CLRBIT(0xD4, ((UINT32)0x7)<<18);
                  CKGEN_CLRBIT(0xE4, ((UINT32)0x1)<<29);

                  // set pull up
                  // enable Pull Up for SD
                  //CKGEN_SETBIT(0x104, ((UINT32)0x1)<<6);
                  CKGEN_SETBIT(0x108, ((UINT32)0x1)<<4);
                  CKGEN_SETBIT(0x104, ((UINT32)0x1)<<12);
                  CKGEN_SETBIT(0x104, ((UINT32)0x1)<<30);
                  CKGEN_SETBIT(0x108, ((UINT32)0x1)<<16);
                  CKGEN_SETBIT(0x108, ((UINT32)0x1)<<10);
                  // dsiable Pull Down of MS
                  //CKGEN_CLRBIT(0x104, ((UINT32)0x1)<<7);
                  CKGEN_CLRBIT(0x108, ((UINT32)0x1)<<5);
                  CKGEN_CLRBIT(0x104, ((UINT32)0x1)<<13);
                  CKGEN_CLRBIT(0x104, ((UINT32)0x1)<<31);
                  CKGEN_CLRBIT(0x108, ((UINT32)0x1)<<17);
                  CKGEN_CLRBIT(0x108, ((UINT32)0x1)<<11);	

                  // 4 bit configuration
                  _fgFCI4BitBusEnable = TRUE;
                  break;			
              }
     		    case 3:
     		    {
                  // multifunciton 3
                  // 0x7000_00E0[18:16] = 3
                  // 0x7000_00E0[22:20] = 3
                  // 0x7000_00E0[26:24] = 3
                  // 0x7000_00E0[30:28] = 3
                  CKGEN_SETBIT(0xE0, ((UINT32)0x7)<<16);
                  CKGEN_SETBIT(0xE0, ((UINT32)0x7)<<20);
                  CKGEN_SETBIT(0xE0, ((UINT32)0x7)<<24);
                  CKGEN_SETBIT(0xE0, ((UINT32)0x7)<<28);

                  //common setting
                  CKGEN_CLRBIT(0xD4, ((UINT32)0x1ff)<<15);
                  CKGEN_CLRBIT(0xC0, ((UINT32)0x1)<<31);
                  CKGEN_CLRBIT(0xD4, ((UINT32)0x1)<<2);

                  // set Pull up
                  // enalbe Pull Up for SD
                  //CKGEN_SETBIT(0x114, ((UINT32)0x1)<<22);
                  CKGEN_SETBIT(0x114, ((UINT32)0x1)<<28);
                  CKGEN_SETBIT(0x118, ((UINT32)0x1)<<20);
                  CKGEN_SETBIT(0x118, ((UINT32)0x1)<<14);
                  CKGEN_SETBIT(0x118, ((UINT32)0x1)<<2);
                  CKGEN_SETBIT(0x118, ((UINT32)0x1)<<8);
                  // disable Pull Down of MS
                  //CKGEN_CLRBIT(0x114, ((UINT32)0x1)<<23);
                  CKGEN_CLRBIT(0x114, ((UINT32)0x1)<<29);
                  CKGEN_CLRBIT(0x118, ((UINT32)0x1)<<21);
                  CKGEN_CLRBIT(0x118, ((UINT32)0x1)<<15);
                  CKGEN_CLRBIT(0x118, ((UINT32)0x1)<<3);
                  CKGEN_CLRBIT(0x118, ((UINT32)0x1)<<9);

                  // 4 bit configuration
                  _fgFCI4BitBusEnable = TRUE;
                  break;			
              }
          default:
              {
                  break;
              }
        }
    }             		
#endif    
}

//-------------------------------------------------------------------------
/** _Fcihw_CheckFifo
 *  Check and wait FIFO back to default status.
 *  @param  VOID.
 *  @return  VOID
 */
//-------------------------------------------------------------------------
static VOID _Fcihw_CheckFifo(VOID)
{
    UINT32 u4Val;

    UINT32 u4Count = 0;

    do
    {
        u4Count++;

        if (u4Count > 1000)
        {
            FCI_LOG(FCI_LOG_ERROR, "_Fcihw_CheckFifo timeout.\n");
            _u4SdDbgError |= (0x01 << 0);
            return;
        }

        u4Val = FCI_READ32(RW_FCI_INTR_STAT_REG);
        u4Val &= INTR_FIFO_MASK;
    } while (u4Val != (INTR_FCI_WRFIFO_EMPTY | INTR_FCI_RDFIFO_EMPTY));
}

//-------------------------------------------------------------------------
/** _Fcihw_ResetFifo
 *  Reset FCI SRAM and FIFO status.
 *  @param  void.
 *  @return  void
 */
//-------------------------------------------------------------------------
static void _Fcihw_ResetFifo(void)
{
    UINT32 u4Val;

   FCI_LOG(FCI_LOG_INIT, "\n");
  
    u4Val = FCI_READ32(RW_FCI_CTRL_REG);
    ASSERT((u4Val & CTRL_FCI_FIFORST) == 0);
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val | CTRL_FCI_FIFORST);
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val);
}

//-------------------------------------------------------------------------
/** _Fcihw_ResetInt
 *  Reset FCI interrupt.
 *  @param  void.
 *  @return  void
 */
//-------------------------------------------------------------------------
static void _Fcihw_ResetInt(void)
{
    UINT32 u4Val;

   FCI_LOG(FCI_LOG_INIT, "\n");
  
    u4Val = FCI_READ32(RW_FCI_CTRL_REG);
    ASSERT((u4Val & CTRL_FCI_INTRST) == 0);

    /* It's a bug that interrupt might come at this time.
     * Why does it happen? fix me! XXX
     * Let interrupt cannot come at here tentatively. */
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val | CTRL_FCI_INTRST);
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val);
}

//-------------------------------------------------------------------------
/** _Fcihw_ResetDramClk
 *  Reset FCI Dram Clock Domain
 *  @param  VOID.
 *  @return  VOID
 */
//-------------------------------------------------------------------------
static VOID _Fcihw_ResetDramClk(VOID)
{
    UINT32 u4Val;

    u4Val = FCI_READ32(RW_FCI_CTRL_REG);
    ASSERT((u4Val & CTRL_DRAM_CLKRST) == 0);
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val | CTRL_DRAM_CLKRST);
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val);
}

//-------------------------------------------------------------------------
/** FCIHW_SetFifoRead
 *  Data transfer from memory card to FCI controller.
 *  @param  VOID
 *  @return  VOID
 */
//-------------------------------------------------------------------------
static VOID FCIHW_SetFifoRead(VOID)
{
    UINT32 u4Val;

    u4Val = FCI_READ32(RW_FCI_CTRL_REG);
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val & ~(CTRL_FCI_FIFO_W_DIR));
}

//-------------------------------------------------------------------------
/** FCIHW_SetFifoWrite
 *  Data transfer from FCI controller to memory card.
 *  @param  VOID
 *  @return  VOID
 */
//-------------------------------------------------------------------------
static VOID FCIHW_SetFifoWrite(VOID)
{
    UINT32 u4Val;

    u4Val = FCI_READ32(RW_FCI_CTRL_REG);
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val | CTRL_FCI_FIFO_W_DIR);
}

//-------------------------------------------------------------------------
/** _Fcihw_ResetSlot
 *  Reset and clear interrupt FCI specific interface.
 *  @param  u4SlotIntr   specific card interrupt source.
 *  @param  u4SlotRest   specific card reset bit.
 *  @return  VOID
 */
//-------------------------------------------------------------------------
static VOID _Fcihw_ResetSlot(UINT32 u4SlotIntr, UINT32 u4SlotRest)
{
    UINT32 u4Val;
    UINT32 u4Loop;

    // Disable Slot interrupt.
    if (_fgFCIIsrEn)
    {
        u4Val = FCI_READ32(RW_FCI_INTR_CTRL_REG);
        u4Val &= ~(u4SlotIntr);
        u4Val &= ~(INTR_SRAMSELI);
        u4Val |= FCI_SRAMSEL(_u4SRAMSel);
        FCI_WRITE32(RW_FCI_INTR_CTRL_REG, u4Val);
    }
    
    // Reset slot.
    u4Val = FCI_READ32(RW_FCI_CTRL_REG);
    ASSERT((u4Val&(u4SlotRest)) == 0);
    
    for(u4Loop = 0; u4Loop < _u4FciHwRstWtTimes; u4Loop++)
    {
        FCI_WRITE32(RW_FCI_CTRL_REG, u4Val | u4SlotRest | CTRL_FCI_FIFORST);    
    }
    
    for(u4Loop = 0; u4Loop < (4*_u4FciHwRstWtTimes); u4Loop++)
    {
        FCI_WRITE32(RW_FCI_CTRL_REG, u4Val | CTRL_FCI_FIFORST);
    }

    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val);
    
    // Reset FCI FIFO.
    //_Fcihw_ResetFifo();

    // Reset FCI Dram domain.
    _Fcihw_ResetDramClk();
    
    // Reset FCI interrupt.
    _Fcihw_ResetInt();
}

//-------------------------------------------------------------------------
/** SDCHW_SendCmd
 *  Send SD command and argument.
 *  @param u4Cmd    SD command.
 *  @param u4Arg      SD argument.
 *  @retval  S_OK  Success.
 *  @retval  Others  Fail.
 */
//-------------------------------------------------------------------------
static INT32 SDCHW_SendCmd(struct sdhci_host *host, UINT32 u4Cmd, UINT32 u4Arg)
{
#if ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550) ||  (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555))     // MT8550/8555 solve this problem
    UINT32 u4CmdRB, u4RetryCnt = 0;
#endif

    // Save for check
    host->u4Cmd = u4Cmd;
    host->u4Arg = u4Arg;
    
    // Because we need to check CR, DR, WBF semaphore according to interrupt status,
    // use interrupt reset to clear these interrupt status bits.
    _Fcihw_ResetInt();

    // Make sure all the semaphore is in lock state.
    SDCHW_InitSemaphore(host);

    FCI_WRITE32(RW_SD_ARG_REG, u4Arg);
    //x_thread_delay(1);
    FCI_LOG(FCI_LOG_SDHWCMD, "SdSendCmd Cmd:0x%X Arg:0x%X\n", u4Cmd, u4Arg);

#if ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550) ||  (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555))     // MT8550/8555 don't need retry
Retry:

    FCI_WRITE32(RW_SD_CTRL_REG, u4Cmd);

    u4CmdRB = FCI_READ32(RW_SD_CTRL_REG);

    if (u4CmdRB != u4Cmd && u4RetryCnt < 1000)
    {
        u4RetryCnt++;
        goto Retry;
    }

    if(u4RetryCnt >= 1000)
    {
        FCI_LOG(FCI_LOG_ERROR, "(%d) Cmd Retry Error : Cmd: 0x%X, CmdRB: 0x%X, Retry: %d\n", __LINE__, u4Cmd, u4CmdRB, u4RetryCnt);
        _u4SdDbgError |= (0x01 << 20);
        return -1;
    }

#else
    FCI_WRITE32(RW_SD_CTRL_REG, u4Cmd);
#endif

    return 0;
}

//-------------------------------------------------------------------------
/** SDCHW_OpenDataRdyInt
 *  open SDDR Interrupt and turn off SDWBF interrupt.
 *  @param VOID.
 *  @return  VOID
 */
//-------------------------------------------------------------------------
static VOID SDCHW_OpenDataRdyInt(VOID)
{
    UINT32 u4Val;

    u4Val = FCI_READ32(RW_FCI_INTR_CTRL_REG);
    //u4Val &= ~(INTR_SD_PFI |INTR_SD_CMDRDYI);
    u4Val &= ~(INTR_SD_PFI);
    u4Val |= INTR_SD_DTRDYI;
    FCI_WRITE32(RW_FCI_INTR_CTRL_REG, u4Val);
}

//-------------------------------------------------------------------------
/** SDCHW_WaitDataRdyOrTo
 *  Wait data line ready. Use in data read operation.
 *  @param VOID.
 *  @retval  S_OK  Success.
 *  @retval  Others  Fail.
 */
//-------------------------------------------------------------------------
static INT32 SDCHW_WaitDataRdyOrTo(struct sdhci_host *host)
{
    INT32 i4Ret;

    //if (_fgFCIIsrEn)
    {
#ifdef CC_CARD_DETECT_PIN
         // handle interrupt status only in card inserted state.
        if (!(sdhci_card_exist() || host->fakeIns))
        {
            FCI_LOG(FCI_LOG_ERROR, "(%d) No Card error.\n", __LINE__);
            return E_NO_CARD;
        }
#endif

        i4Ret = _Sdchw_WaitDataRdyBit();

        if (i4Ret == E_NO_RESPONSE)
        {
            FCI_LOG(FCI_LOG_ERROR, "Wait DataRdy Err of no response IntrStat:0x%08x <= Failed @@\n",
            FCI_READ32(RW_FCI_INTR_STAT_REG));
            _u4SdDbgError |= (0x01 << 15);
        }

        // wait FIFO empty.
        _Fcihw_CheckFifo();

        return i4Ret;
    }

    return E_NO_RESPONSE;
}

//-------------------------------------------------------------------------
/** SDCHW_OpenWBFInt
 *  open SDWBF Interrupt and turn off SDDR interrupt.
 *  @param VOID.
 *  @return  VOID
 */
//-------------------------------------------------------------------------
static VOID SDCHW_OpenWBFInt(VOID)
{
    UINT32 u4Val;

    u4Val = FCI_READ32(RW_FCI_INTR_CTRL_REG);
#if (FCI_INTERRUPT_ARCHITECTURE == FCI_NEW_INTERRUPT_ARCHITECTURE)    // MT8550/8555 use INTR_SD_DTRDYI instead
    u4Val &= ~INTR_SD_PFI;
    //u4Val |= INTR_SD_PFI | INTR_SD_CMDRDYI;
    u4Val |= INTR_SD_DTRDYI;
#else
    u4Val &= ~INTR_SD_DTRDYI;
    //u4Val |= INTR_SD_PFI | INTR_SD_CMDRDYI;
    u4Val |= INTR_SD_PFI;
#endif
    FCI_WRITE32(RW_FCI_INTR_CTRL_REG, u4Val);
}

//-------------------------------------------------------------------------
/** SDCHW_WaitDataIdle
 *  wait the end of SD write buffer busy.
 *  @param VOID.
 *  @retval  S_OK  Success.
 *  @retval  Others  Fail.
 */
//-------------------------------------------------------------------------
static INT32 SDCHW_WaitDataIdle(struct sdhci_host *host)
{
    INT32 i4Ret;

    //if (_fgFCIIsrEn)
    {
#ifdef CC_CARD_DETECT_PIN
         // handle interrupt status only in card inserted state.
        if (!(sdhci_card_exist() || host->fakeIns))
        {
            FCI_LOG(FCI_LOG_ERROR, "(%d) No Card error.\n", __LINE__);
            return E_NO_CARD;
        }
#endif

        i4Ret = _Sdchw_WaitWBFBit();

        if (i4Ret == E_NO_RESPONSE)
        {
            FCI_LOG(FCI_LOG_ERROR, "Wait Idle Err of no response IntrStat:0x%08x <= Failed @@\n",
            FCI_READ32(RW_FCI_INTR_STAT_REG));
            _u4SdDbgError |= (0x01 << 16);
        }
        return i4Ret;
    }

    return E_NO_RESPONSE;
}

//-------------------------------------------------------------------------
/** FCIHW_Init
 *  Set clock and reset FCI interface.
 *  @param void
 *  @retval  S_OK  Success.
 *  @retval  Others  Fail.
 */
//-------------------------------------------------------------------------
static int FCIHW_Init(void)
{
    UINT32 u4Val;

   FCI_LOG(FCI_LOG_INIT, "\n");
  
    FCI_WRITE32(RW_FCI_CTRL_REG, 0);
    _Fcihw_ResetFifo();
    _Fcihw_ResetInt();

    /* Reset SD/MS/SM controller. */
    u4Val = FCI_READ32(RW_FCI_CTRL_REG);
    u4Val &= ~(CTRL_SD_RST | CTRL_MS_RST | CTRL_SM_RST);
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val | CTRL_SD_RST);
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val);
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val | CTRL_MS_RST);
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val);

    /* Set the initialized value of all FCI registers. */
    FCI_WRITE32(RW_MS_CTRL_REG, 0);
    FCI_WRITE32(RW_SD_CTRL_REG, 0);

    FCI_WRITE32(RW_FCI_CTRL_REG, FCI_MS_BSYCNT(0));
    FCI_WRITE32(RW_FCI_INTR_CTRL_REG, FCI_SEL_SD | FCI_SRAMSEL(3));
    FCI_WRITE32(RW_SD_BUSYCNT_REG,
        MAX_NAC_BSYCNT | MAX_NWP_BSYCNT | MAX_NCR_BSYCNT |CTRL_FCI_SDWC);

    return S_OK;
}

//-------------------------------------------------------------------------
/** FCIHW_DmaWriteDramEnable
 *  Transfer data from memory card to FCI controller.
 *  @param pu1Buf       user's read buffer pointer.
 *  @param u4BlkSz     read block size in bytes.
 *  @return  VOID
 */
//-------------------------------------------------------------------------
static VOID FCIHW_DmaWriteDramEnable(UINT8 *pu1Buf, UINT32 u4BlkSz)
{
    UINT32 u4Val;
    UINT32 u4UpAddr = 0;

    // DMA address must be double word alignment.
    ASSERT(pu1Buf);
    ASSERT(((UINT32)pu1Buf % 4) == 0);
    ASSERT(((UINT32)(pu1Buf + u4BlkSz) % 4) == 0);

    //flush_cache_all();
    //BSP_FlushDCacheRange((UINT32)pu1Buf, u4BlkSz);
    _Fcihw_ResetFifo();
    _Fcihw_ResetDramClk();

    u4Val = FCI_READ32(RW_FCI_CTRL_REG);
    FCI_WRITE32(RW_FCI_CTRL_REG, (u4Val | CTRL_FCI_DMAEN) & ~(CTRL_FCI_FIFO_W_DIR));

    FCI_WRITE32(RW_DMA_LOW_BOUNDARY_REG, (UINT32)pu1Buf);
    FCI_WRITE32(RW_DMA_UP_BOUNDARY_REG, u4UpAddr);
}

//-------------------------------------------------------------------------
/** FCIHW_DmaReadDramEnable
 *  Transfer data from FCI controller to memory card.
 *  @param pu1Buf       user's read buffer pointer.
 *  @param u4BlkSz     write block size in bytes.
 *  @return  VOID
 */
//-------------------------------------------------------------------------
static VOID FCIHW_DmaReadDramEnable(UINT8 *pu1Buf, UINT32 u4BlkSz)
{
    UINT32 u4Val;

    // DMA address must be double word alignment.
    ASSERT(pu1Buf);
    ASSERT(((UINT32)pu1Buf % 4) == 0);
    ASSERT(((UINT32)(pu1Buf + u4BlkSz) % 4) == 0);

    //flush_cache_all();
    //BSP_FlushDCacheRange((UINT32)pu1Buf, u4BlkSz);
    _Fcihw_ResetFifo();
    _Fcihw_ResetDramClk();

    u4Val = FCI_READ32(RW_FCI_CTRL_REG);
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val | CTRL_FCI_DMAEN | CTRL_FCI_FIFO_W_DIR);

    FCI_WRITE32(RW_DMA_DS_REG, u4BlkSz);

    // Notice: Setting DMA start address will trigger DMA to work.
    // So It should be the last command at DMA setup.
    FCI_WRITE32(RW_DMA_START_ADDR_REG, (UINT32)pu1Buf);
}

//-------------------------------------------------------------------------
/** FCIHW_DmaDisable
 *  Disable DMA transfer.
 *  @param VOID.
 *  @return  VOID
 */
//-------------------------------------------------------------------------
static VOID FCIHW_DmaDisable(VOID)
{
    UINT32 u4Val;

    u4Val = FCI_READ32(RW_FCI_CTRL_REG);
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val & ~(CTRL_FCI_DMAEN));
    _Fcihw_ResetFifo();
    //HalFlushInvalidateDCache();
}

//-------------------------------------------------------------------------
/** FCI_RegInit
 *  Initailize all FCI card h/w register.
 *  @param  void
 *  @return  0: success. -1: FCI H/W initialization fail.
 */
//-------------------------------------------------------------------------
static INT32 FCI_RegInit(void)
{
    INT32 i4Ret;

    i4Ret = FCIHW_Init();

    if (i4Ret != S_OK)
    {
        return -1;
    }

    // i4Ret = FCIHW_SetIsr(TRUE);

    if (i4Ret != S_OK)
    {
        return -1;
    }

    return 0;
}

//-------------------------------------------------------------------------
/** SDCHW_Init
 *  Initialize SD card interface.
 *  @param VOID.
 *  @return  VOID
 */
//-------------------------------------------------------------------------
static VOID SDCHW_Init(VOID)
{
    FCIHW_ChangeSDCClock(0);

    FCI_WRITE32(RW_FCI_CTRL_REG, CTRL_SD_RST | CTRL_DRAM_CLKRST | CTRL_DRAM_CLKEN);

    FCI_WRITE32(RW_FCI_CTRL_REG, CTRL_DRAM_CLKEN);

    FCI_WRITE32(RW_FCI_CTRL_REG, CTRL_SD_SIEN | CTRL_SD_NEGDRIVE | CTRL_DRAM_CLKEN | CTRL_SD_DATASWAP | CTRL_SD_RECEDGE);

    FCI_WRITE32(RW_SD_BUSYCNT_REG,
        MAX_NAC_BSYCNT | MAX_NWP_BSYCNT | MAX_NCR_BSYCNT |CTRL_FCI_SDWC);
    
    _Fcihw_ResetInt();
    _Fcihw_ResetFifo();

    // check if ISR is registered ?
    if (_fgFCIIsrEn)
    {
        // turn on interrupt.
        FCI_WRITE32(RW_FCI_INTR_CTRL_REG,
        INTR_SRAMSELI | FCI_SEL_SD | INTR_SD_ALLI | INTR_FCI_ALLI);
    }
    else
    {
        FCI_WRITE32(RW_FCI_INTR_CTRL_REG, INTR_SRAMSELI | FCI_SEL_SD);
    }
}

//-------------------------------------------------------------------------
/** FCIHW_PowerOn
 *  Power on FCI module (DRAM interface)
 *  @param  void
 *  @return TRUE.
 */
//-------------------------------------------------------------------------

static BOOL FCIHW_PowerOn(void)
{
    BOOL fgRet;
    UINT32 u4Val;

    FCI_LOG(FCI_LOG_FUNC, "\n");

    fgRet = CKGEN_AgtOnClk(e_CLK_SD);
    
    // turn on MS & SD control clock
    u4Val = FCI_READ32(RW_FCI_CTRL_REG);
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val & ~(CTRL_MS_PWS|CTRL_SD_PWS) );

    // turn on FCI DRAM clock
    u4Val = FCI_READ32(RW_FCI_CTRL_REG);
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val | CTRL_DRAM_CLKEN );
    
    return fgRet;
}

#if 0
//-------------------------------------------------------------------------
/** FCIHW_PowerOff
 *  Power off FCI module (DRAM interface)
 *  @param  void
 *  @return TRUE.
 */
//-------------------------------------------------------------------------

static BOOL FCIHW_PowerOff(void)
{
    BOOL fgRet;
    UINT32 u4Val;
    
    FCI_LOG(FCI_LOG_FUNC, "\n");    

    // turn off MS & SD control clock
    u4Val = FCI_READ32(RW_FCI_CTRL_REG);
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val | CTRL_MS_PWS | CTRL_SD_PWS );

    // turn off FCI DRAM clock
    u4Val = FCI_READ32(RW_FCI_CTRL_REG);
    FCI_WRITE32(RW_FCI_CTRL_REG, u4Val & ~CTRL_DRAM_CLKEN );

    fgRet = CKGEN_AgtOffClk(e_CLK_SD);
    
    return fgRet;
}
#endif

//-------------------------------------------------------------------------
/** _Fcihw_SetupSlot
 *  Setup FCI specific interface and enable interrupt.
 *  @param  u4SlotIntr   specific card interrupt source.
 *  @param  u4SelSlot   FCI controller selection.
 *  @return  VOID
 */
//-------------------------------------------------------------------------
static VOID _Fcihw_SetupSlot(UINT32 u4SlotIntr, UINT32 u4SelSlot)
{
    UINT32 u4Val;
    
    // write Slot interrupt enable bit.
    u4Val = FCI_READ32(RW_FCI_INTR_CTRL_REG);
    u4Val &= ~(INTR_FCISELI | INTR_FCI_ALLI | u4SlotIntr);

    if (_fgFCIIsrEn)
    {
        u4Val |= u4SlotIntr | u4SelSlot | INTR_FCI_ALLI;
    }
    else
    {
        u4Val |= u4SelSlot;
    }

    // SD Default speed mode setting
    u4Val &= ~(INTR_FCI_HIGH_SPEED_MODE);    
  
    FCI_WRITE32(RW_FCI_INTR_CTRL_REG, u4Val);
}

//-------------------------------------------------------------------------
/** FCIHW_ResetSlot
 *  reset specific card slot. call this function when card swap.
 *  @param  i4Slot  slot number.
 *  @return  VOID
 */
//-------------------------------------------------------------------------
static VOID FCIHW_ResetSlot(INT32 i4Slot)
{
    UINT32 u4Val;

    FCI_LOG(FCI_LOG_FUNC, "\n");
    
    _i4FCIPrevSlot = i4Slot;

    // It must to disable interrupt before register setting.
    switch (i4Slot)
    {
    case SDC_SLOT:

        // disable interrupt.
        _Fcihw_ResetSlot(INTR_SD_ALLI, CTRL_SD_RST);

        FCI_WRITE32(RW_SD_BUSYCNT_REG,
            MAX_NAC_BSYCNT | MAX_NWP_BSYCNT | MAX_NCR_BSYCNT |CTRL_FCI_SDWC);

        u4Val = FCI_READ32(RW_FCI_CTRL_REG);
        // Clear MS, SM part.
        u4Val &= 0xFF0000FF;

        // SD Default speed mode setting
        u4Val |= CTRL_SD_MLBLCNEN | CTRL_SD_NEGDRIVE | CTRL_SD_DATASWAP | CTRL_SD_SIEN |CTRL_SD_RECEDGE;
        u4Val &= ~(CTRL_SD_WIDEBUSEN | CTRL_SD_HS_ADJ);
        
        if (fci_host.bus_width == 4)
        {
            u4Val |= CTRL_SD_WIDEBUSEN;
        }
        FCI_WRITE32(RW_FCI_CTRL_REG, u4Val);

        // turn on interrupt.
        _Fcihw_SetupSlot(INTR_SD_ALLI, FCI_SEL_SD);
        break;

    default:
        break;
    }
}

//-------------------------------------------------------------------------
/** FCI_Init
 *  Initailize all FCI card.
 *  @param  void
 *  @retval   FCI_TYPE_NONE  no card.
 *  @retval   FCI_TYPE_MS  MS card.
 *  @retval   FCI_TYPE_SD          SD card.
 *  @retval   FCI_TYPE_SMXD  SM/xD card.
 *  @retval   -1                          init fail.
 */
//-------------------------------------------------------------------------
INT32 FCI_Init(void)
{
    INT32 i4Ret;

    i4Ret = FCI_RegInit();

    if (i4Ret != S_OK)
    {
        return -1;
    }

    FCIHW_PowerOn();
    udelay(200000);

    // Setup multi-func, driving current & pull up resistor
    FCIHW_HWConfigSetup(SDC_SLOT);

    FCIHW_ResetSlot(SDC_SLOT);

    SDCHW_Init();    
    
    return (sdhci_card_exist() ? 0 : -1);
}

static int sdhci_prepare_data(struct mmc_data *data)
{
    unsigned int size;

    FCI_LOG(FCI_LOG_FUNC, "blocksize %04x blks %04x flags %08x\n", data->blocksize, data->blocks, data->flags);
    FCI_LOG(FCI_LOG_RW, "MMC_DATA_WRITE = 0x%02x\n", MMC_DATA_WRITE);
    FCI_LOG(FCI_LOG_RW, "MMC_DATA_READ = 0x%02x\n", MMC_DATA_READ);
  
    /*
     * Calculate size.
     */
    size = data->blocks * data->blocksize;
    
    /*
     * The buffer for DMA is only 64 kB.
     */
    // BUG_ON(size > 0x10000);

    if (size > 0x10000) 
    {
        FCI_LOG(FCI_LOG_ERROR, "DMA size larger then 64KB, size = 0x%x\n", size);
        _u4SdDbgError |= (0x01 << 21);
        return -1;
    }
    
    // Setup DMA 
    if (data->flags & MMC_DATA_WRITE)
    {
       FCIHW_DmaReadDramEnable((UINT8*)(data->src), size);
    }
    else if (data->flags & MMC_DATA_READ)
    {
       FCIHW_DmaWriteDramEnable((UINT8*)(data->dest), size);
    }

    return 0;
}

/*
 * Sends a command out on the bus.  Takes the mmc pointer,
 * a command pointer, and an optional data pointer.
 */
static int
sdhci_send_cmd(struct mmc *mmc, struct mmc_cmd *cmd, struct mmc_data *data)
{
   UINT32 u4Cmd = 0x00;
   UINT32 u4BlkField;
   INT32 i4Ret = 0;      
   struct sdhci_host *host = mmc->priv;   
   long u4JiffSt = 0, u4JiffEnd = 0, u4RbTime;
   UINT32 u4IntrStatus, u4DataFinishIntr;
   
    // Save current command
    memcpy(&(host->cmd_rec[host->sd_rec_index]), cmd, sizeof(struct mmc_cmd));
    
    if (data)
    {
        memcpy(&(host->data_rec[host->sd_rec_index]), data, sizeof(struct mmc_data));    
        host->cmd_with_data_rec[host->sd_rec_index] = 1;
    }
    else
    {
        host->cmd_with_data_rec[host->sd_rec_index] = 0;      
    }

    if (host->pre_cmd)
    {
        if (host->pre_cmd->cmdidx != MMC_CMD_APP_CMD && cmd->cmdidx == SD_CMD_SWITCH_FUNC)
        {
            if (cmd->cmdarg == 0x80fffff1)
            {
                cmd->response[0] = 0x900;

                host->hispeedcard = TRUE;
                goto end;
            }
        }
    }
    
    // Reset FCI host before read/write             
    if (data)
    {
        FCIHW_ResetSlot(SDC_SLOT);
    }
    
    FCI_LOG(FCI_LOG_FUNC, "Start - Sending cmd (%d), flags = 0x%08x\n", cmd->cmdidx, cmd->flags);

   /*
    * Send the command
    */
    // Setup command 1 : opcode & response type
    switch (cmd->resp_type)
     {
         case MMC_RSP_NONE:
           u4Cmd = SD_GENERAL_CMD(cmd->cmdidx, TYPE_NORES);
           FCI_LOG(FCI_LOG_SDHWCMD, "Response None\n");
           break;
         case MMC_RSP_R1:
         //case MMC_RSP_R6:
           u4Cmd = SD_GENERAL_CMD(cmd->cmdidx, TYPE_R1);
           FCI_LOG(FCI_LOG_SDHWCMD, "Response R1 or R6\n");
           break;
         case MMC_RSP_R1b:
           u4Cmd = SD_GENERAL_CMD(cmd->cmdidx, TYPE_R1b);
           FCI_LOG(FCI_LOG_SDHWCMD, "Response R1B\n");
           break;
         case MMC_RSP_R2:
           u4Cmd = SD_GENERAL_CMD(cmd->cmdidx, TYPE_R2);
           FCI_LOG(FCI_LOG_SDHWCMD, "Response R2\n");
           break;
         case MMC_RSP_R3:
           u4Cmd = SD_GENERAL_CMD(cmd->cmdidx, TYPE_R3);
           FCI_LOG(FCI_LOG_SDHWCMD, "Response R3\n");
           break;
    }
    
    // Setup command 2 : Read/Write flag  
    if (data)
    {
         if (data->blocks > 1)
         {
              SDCHW_SetMultiFlag(TRUE);
         }
         else
         {
              SDCHW_SetMultiFlag(FALSE);
         }
         
         if (data->flags & MMC_DATA_WRITE)
         {
             FCIHW_SetFifoWrite();
             
             host->wreqcnt++;    
    
             // turn off SDDR and turn on SDWBF.
             if (_fgFCIIsrEn)
             {
                SDCHW_OpenWBFInt();
             }
             
             if (data->blocks > 1)
             {
                 u4BlkField = (((data->blocks - 1) & 0x07f) << 12);
                 u4Cmd |= (SD_CMD_WRITE_BIT | SD_CMD_RW_MULTI_BIT | u4BlkField | FCI_BLOCK_SIZE);
             }
             else
             {
                 u4Cmd |= (SD_CMD_WRITE_BIT | (data->blocks * data->blocksize));
             }
         }
         else if (data->flags & MMC_DATA_READ)
         {
             FCIHW_SetFifoRead();

             host->rreqcnt++;
             
             // open SDDR interrupt, and close SDWBF interrupt.
             if (_fgFCIIsrEn)
             {
                SDCHW_OpenDataRdyInt();
             }
             
             if (data->blocks > 1)
             {
                 u4BlkField = (((data->blocks - 1) & 0x07f) << 12);
                 u4Cmd |= (SD_CMD_READ_BIT | SD_CMD_RW_MULTI_BIT | u4BlkField | FCI_BLOCK_SIZE);
             }
             else
             {
                u4Cmd |= (SD_CMD_READ_BIT | (data->blocks * data->blocksize));
             }
         }        
    }  
   
    // Setup command 3 : Add stop bit for stop command
    if (cmd->cmdidx == MMC_CMD_STOP_TRANSMISSION)
    {
        u4Cmd |= SD_CMD_STOP_BIT;
    } 

    // Clear Cmd Error flag
    host->u4CmdError = 0;
    
    /*
     * Does the request include data?
     */
    if (data) 
    {
        i4Ret = sdhci_prepare_data(data);

        if (i4Ret != 0)
        {
            host->u4CmdError = COMM_ERR;
            FCI_LOG(FCI_LOG_ERROR, "sdhci_prepare_data failed !!\n");
            _u4SdDbgError |= (0x01 << 22);
            goto end;
        }
    }

    // Isssue command
    i4Ret = SDCHW_SendCmd(host, u4Cmd, cmd->cmdarg);   
 
    if (i4Ret != 0)
    {
        host->u4CmdError = TIMEOUT;
        FCI_LOG(FCI_LOG_ERROR, "SDCHW_SendCmd failed, Cmd: 0x%X, Arg: 0x%X, i4Ret: 0x%x\n", u4Cmd, cmd->cmdarg, i4Ret);
        _u4SdDbgError |= (0x01 << 23);
        goto end;
    }

    /*
     * Wait for the request to complete.
     */   

    u4JiffSt = get_timer(0);

    do
    {       
       u4IntrStatus = FCI_READ32(RW_FCI_INTR_STAT_REG);
       FCI_LOG(FCI_LOG_IRQ, "Waiting host->sdcr_sema, u4IntrStatus = 0x%x\n", u4IntrStatus);
       u4JiffEnd = get_timer(0);
       u4RbTime = (u4JiffEnd - u4JiffSt) * 1000 / CONFIG_SYS_HZ;   // unit : ms
       if (u4RbTime > 2)  // timeout for 64 serial clocks
       {
          u4IntrStatus |= INTR_SD_CMDTOI;
          FCI_LOG(FCI_LOG_ERROR, "Waiting cmd %d TO, u4RbTime = %d, u4IntrStatus = 0x%x\n", cmd->cmdidx, (u32)u4RbTime, u4IntrStatus);
       }
    } while (!((INTR_SD_CMDRDYI | INTR_SD_CMD_ERRI) & u4IntrStatus));

    // Save FCI Status for further usage
    _u4FCISStatus |= ((INTR_SD_CMDRDYI | INTR_SD_CMD_ERRI) & u4IntrStatus);

    FCI_LOG(FCI_LOG_IRQ, "Get Unlock sdcr_lock\n");

    // wait command ready.
    i4Ret = _Sdchw_WaitCmdRdyOrTo(&fci_host, cmd);
   
    if (i4Ret == S_OK)
    {    
        /*
         * Do we expect a reply?
         */
        if (cmd->resp_type & MMC_RSP_PRESENT) 
        {
          // Check command index
          switch (cmd->cmdidx)
          {
              case SD_CMD_SWITCH_FUNC:
              case MMC_CMD_SELECT_CARD:
              case SD_CMD_SEND_IF_COND:
              case MMC_CMD_STOP_TRANSMISSION:
              case MMC_CMD_SET_BLOCKLEN:
              case MMC_CMD_READ_SINGLE_BLOCK:
              case MMC_CMD_READ_MULTIPLE_BLOCK:
              case MMC_CMD_WRITE_SINGLE_BLOCK:
              case MMC_CMD_WRITE_MULTIPLE_BLOCK:
              case 42:
              case SD_CMD_APP_SEND_SCR:
              case MMC_CMD_APP_CMD:
                i4Ret = _SDC_CheckStatus(cmd->cmdidx);
                break;
          }
           
          if (i4Ret != S_OK)
          {
              host->u4CmdError = UNUSABLE_ERR;
              FCI_LOG(FCI_LOG_ERROR , "CheckStatus failed %d\n", i4Ret);
              _u4SdDbgError |= (0x01 << 24);
              goto end;
          }

        if (cmd->resp_type & MMC_RSP_136)
          sdhci_get_long_reply(cmd);
        else
          sdhci_get_short_reply(cmd);
        }
    }

    // Handle data read/write
    if (data)
    {
        // Decide finish interrupt bit
#if (FCI_INTERRUPT_ARCHITECTURE == FCI_NEW_INTERRUPT_ARCHITECTURE)    // WBE bit is not used in MT8550/8555 (use DTRDYI instead)
        u4DataFinishIntr = INTR_SD_DTRDYI | INTR_SD_DATA_ERRI;
#else
        if (data->flags & MMC_DATA_WRITE)
        {
            u4DataFinishIntr = INTR_SD_NWRITEBUSY | INTR_SD_DATA_ERRI;
        }
        else
        {
            u4DataFinishIntr = INTR_SD_DTRDYI | INTR_SD_DATA_ERRI;
        }    
#endif

        // Check if the command finished
        do
        {       
           u4IntrStatus = FCI_READ32(RW_FCI_INTR_STAT_REG);
           FCI_LOG(FCI_LOG_IRQ, "Waiting host->sddr_sema, u4IntrStatus = 0x%x\n", u4IntrStatus);
        } while (!(u4DataFinishIntr & u4IntrStatus));

        FCI_LOG(FCI_LOG_IRQ, "Get Unlock sdcr_lock\n");

        // Save FCI Status for further usage
        _u4FCISStatus |= (u4DataFinishIntr & u4IntrStatus);

        if (data->flags & MMC_DATA_WRITE)
        {
            i4Ret = SDCHW_WaitDataIdle(host);
        }
        else
        {
            i4Ret = SDCHW_WaitDataRdyOrTo(host);
        }
        
        if (i4Ret != S_OK)
        {       
           switch (i4Ret)
           {
                case E_CMD_TIMEOUT:
                    host->u4CmdError = TIMEOUT;
                    break;
                case E_CMD_RSP_CRC_ERR:
                    host->u4CmdError = UNUSABLE_ERR;
                    // Decrease freq
                    host->sd_clk_minus++;
                    sdhci_set_clock(host, host->clk);
                    break;
                case E_NO_CARD:
                    host->u4CmdError = NO_CARD_ERR;
                    break;
                case E_NO_RESPONSE:
                    host->u4CmdError = TIMEOUT;
                    break;
                default:
                    break;
           }
           
           FCI_LOG(FCI_LOG_ERROR, "(%d) failed, Cmd: %d, Arg: 0x%x, i4Ret: 0x%x, host->u4CmdError: 0x%x\n", __LINE__, cmd->cmdidx, cmd->cmdarg, i4Ret, host->u4CmdError);           
           _u4SdDbgError |= (0x01 << 25);
        } 
        
        /*
         * Disable DMA on the host.
         */
        FCIHW_DmaDisable();
    }
    
end:

    FCI_LOG(FCI_LOG_SDHWCMD, "Finish - Sent cmd (%d) %08x %08x %08x %08x, error: %d, i4Ret: 0x%x\n", 
      cmd->cmdidx, cmd->response[0], cmd->response[1], cmd->response[2], cmd->response[3], host->u4CmdError, i4Ret);   
    
    // Save current command again for response saving
    memcpy(&(host->cmd_rec[host->sd_rec_index]), cmd, sizeof(struct mmc_cmd));
    // Save error
    host->error_rec[host->sd_rec_index] = host->u4CmdError;
    
    // Save current finished command as pre_cmd
    host->pre_cmd = &(host->cmd_rec[host->sd_rec_index++]);

    if (host->sd_rec_index > CMD_DEBUG_LENGTH)
    {
        host->sd_rec_index = 0;
    }
    
    return host->u4CmdError;
}

static void sdhci_set_ios(struct mmc *mmc)
{
	/* Set the clock speed */
	
   sdhci_set_clock(mmc->priv, mmc->clock);

	/* Set the bus width */

    /*
     * Store bus width for later. Will be used when
     * setting up the data transfer.
     */

    if ( fci_host.bus_width != mmc->bus_width)
    {
        fci_host.bus_width = mmc->bus_width;

        switch(fci_host.bus_width)
        {
          case 1:
            SDCHW_Set4BitOn(FALSE);
            FCI_LOG(FCI_LOG_CARD, "Switch to 1 Bit Mode.\n");   
            break;
          case 4:
            SDCHW_Set4BitOn(TRUE);
            FCI_LOG(FCI_LOG_CARD, "Switch to 4 Bit Mode.\n");   
            break;
        }
    }
}

void sdhci_print_mmc_commands(void)
{
   struct sdhci_host *host = &fci_host;
   int i, index;

   printf("  ==== sd/mmc command history ====\n");
      
   for (i=0; i < CMD_DEBUG_LENGTH; i++)
   {
      index = ( i  + host->sd_rec_index) % CMD_DEBUG_LENGTH;

      printf("   %02d : CMD (%d), arg : 0x%08X, resp_type : 0x%X, err : 0x%08X\n      : resp : 0x%08X 0x%08X 0x%08X 0x%08X\n", i, host->cmd_rec[index].cmdidx, host->cmd_rec[index].cmdarg, 
        host->cmd_rec[index].resp_type, host->error_rec[index], host->cmd_rec[index].response[0], host->cmd_rec[index].response[1], host->cmd_rec[index].response[2], host->cmd_rec[index].response[3]);

      if (host->cmd_with_data_rec[index] == 1)
      {
          printf("      : data flags : 0x%08X, %d blocks, buf : 0x%08X\n", host->data_rec[index].flags, host->data_rec[index].blocks, (UINT32)(host->data_rec[index].src)); 
      }
   }
}

static int sdhci_init(struct mmc *mmc)
{
    struct sdhci_host *host = &fci_host;

    // Reset fci_host
    memset(host, 0, sizeof(struct sdhci_host));
    
    // set default bus_width
    mmc->bus_width = 1;

    // set initial clock (initial clock will be set in FCI_Init)
    mmc->clock = 1;
    
    // Initialize fci hardware
    return FCI_Init();
}

static int sdhci_initialize(bd_t *bis)
{
    struct mmc *mmc;
    struct sdhci_host *host = &fci_host;

    // Reset fci_host
    memset(host, 0, sizeof(struct sdhci_host));
    
    mmc = malloc(sizeof(struct mmc));

    memset(mmc, 0, sizeof(struct mmc));

    sprintf(mmc->name, DRIVER_NAME);
    
    host->chip_id = CONFIG_CHIP_VER_CURR;
    host->base = SDIO_BASE;
      
    mmc->priv = host;
    mmc->send_cmd = sdhci_send_cmd;
    mmc->set_ios = sdhci_set_ios;
    mmc->init = sdhci_init;

    mmc->voltages |= (MMC_VDD_30_31 | MMC_VDD_31_32 | MMC_VDD_32_33 | MMC_VDD_33_34 | MMC_VDD_34_35 | MMC_VDD_35_36);

    mmc->host_caps = MMC_MODE_4BIT | MMC_MODE_HS | MMC_MODE_HS_52MHz;

    mmc->f_min = 400000;
    mmc->f_max = 36000000;    

    mmc_register(mmc);

    printf("[%s] %d", __func__, (int)host->chip_id);
    printf(" at 0x%x config %d pwr %d\n", (int)host->base, _u4SDMultiFuncCfg, _u4SDPwrCdPinCfg);

    return 0;
}

int sdhci_mmc_init(bd_t *bis)
{
    return sdhci_initialize(bis);
}
