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
/******************************************************************************
* [File]			NFI.h
* [Version]			v1.0
* [Revision Date]	2007-08-02
* [Author]			Meng-Chang Liu, MC_Liu@mtk.com.tw, 26615, 2007-08-02
* [Description]
*	MT8520 Download Agent NFI include file
* [Copyright]
*	Copyright (C) 2007 MediaTek Incorporation. All Rights Reserved.
******************************************************************************/
#ifndef _NFI2_H
#define _NFI2_H
#include <chip_ver.h>

#define INT_WR_CLR

//#define LINUX_ISR_ENABLE

#define FDM_BYTES  9
#define FDM_ECC_BYTES 9
#define MTD_NAND_DEFAULT_TIMEOUT	0x0002FFFF

#define NFI_BASE 0x7001e400

typedef enum {
	 CS0 = 0
	,CS1
	,CS2
	,CS3
	,CS4
	,CS5
	,CS6
	,CS7
	,CS_WITH_DECODER
	,MAX_CS = CS_WITH_DECODER
	,HW_CHIP_SELECT_END
} HW_ChipSelect_E;

#define INT_WR_CLR

/* Register definition */
#if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8560)
#define NFI_DRAM_WDLE       ((volatile u16 *)(0x70000000+0x1EFFC))
#endif

#define NFI_CNFG             ((volatile u16 *)(NFI_BASE+0x0000))
#define NFI_PAGEFMT     ((volatile u16 *)(NFI_BASE+0x0004))
#define NFI_CON       ((volatile u16 *)(NFI_BASE+0x0008))
#define NFI_ACCCON      ((volatile u32 *)(NFI_BASE+0x000C))
#define NFI_INTR_EN     ((volatile u16 *)(NFI_BASE+0x0010))
#define NFI_INTR        ((volatile u16 *)(NFI_BASE+0x0014))
#define NFI_CMD         ((volatile u16 *)(NFI_BASE+0x0020))
#define NFI_ADDRNOB      ((volatile u16 *)(NFI_BASE+0x0030))
#define NFI_COLADDR       ((volatile u32 *)(NFI_BASE+0x0034))
#define NFI_ROWADDR       ((volatile u32 *)(NFI_BASE+0x0038))
#define NFI_STRDATA   ((volatile u16 *)(NFI_BASE+0x0040))
#define NFI_DATAW       ((volatile u32 *)(NFI_BASE+0x0050))
#define NFI_DATAR       ((volatile u32 *)(NFI_BASE+0x0054))
#define NFI_STA         ((volatile u16 *)(NFI_BASE+0x0060))
#define NFI_FIFOSTA     ((volatile u16 *)(NFI_BASE+0x0064))
#define NFI_ADDRCNTR   ((volatile u16 *)(NFI_BASE+0x0070))
#define NFI_STRADDR    ((volatile u32 *)(NFI_BASE+0x0080))
#define NFI_BYTELEN     ((volatile u16 *)(NFI_BASE+0x0084))
//#define NFI_CNFG         ((volatile u16 *)(NFI_BASE+0x0034))
#define NFI_PAGECNTR	   ((volatile u16 *)(NFI_BASE+0x0040))
#define NFI_SYM0_ADDR   ((volatile u16 *)(NFI_BASE+0x0050))
#define NFI_SYM1_ADDR   ((volatile u16 *)(NFI_BASE+0x0054))
#define NFI_SYM2_ADDR   ((volatile u16 *)(NFI_BASE+0x0058))
#define NFI_SYM3_ADDR   ((volatile u16 *)(NFI_BASE+0x005C))
#define NFI_SYM0_DAT    ((volatile u32 *)(NFI_BASE+0x0060))
#define NFI_SYM1_DAT    ((volatile u32 *)(NFI_BASE+0x0064))
#define NFI_SYM2_DAT    ((volatile u32 *)(NFI_BASE+0x0068))
#define NFI_SYM3_DAT    ((volatile u32 *)(NFI_BASE+0x006C))
#define NFI_ERRDET      ((volatile u16 *)(NFI_BASE+0x0070))
#define NFI_PAR0        ((volatile u16 *)(NFI_BASE+0x0080))
#define NFI_PAR1        ((volatile u16 *)(NFI_BASE+0x0084))
#define NFI_PAR2        ((volatile u16 *)(NFI_BASE+0x0088))
#define NFI_PAR3        ((volatile u16 *)(NFI_BASE+0x008C))
#define NFI_PAR4        ((volatile u16 *)(NFI_BASE+0x0090))
#define NFI_PAR5        ((volatile u16 *)(NFI_BASE+0x0094))
#define NFI_PAR6        ((volatile u16 *)(NFI_BASE+0x0098))
#define NFI_PAR7        ((volatile u16 *)(NFI_BASE+0x009C))
/* DMA address 16byte alignment. */
#define NFI_FDM0L		((volatile u32 *)(NFI_BASE+0x200))
#define NFI_FDM0M	   ((volatile u32 *)(NFI_BASE+0x204))
#define NFI_DMARD_SADR		((volatile u32 *)(NFI_BASE+0xA8))
#define NFI_DMARD_EADR	   ((volatile u32 *)(NFI_BASE+0xAC))
#define NFI_DMA_TRIGGER			((volatile u16 *)(NFI_BASE+0xB0))

#define NFI_CSEL	      ((volatile u16 *)(NFI_BASE+0x090))
//#define NFI_CSEL               ((volatile u16 *)(CKGEN_BASE+0xD4))
    //#define CS0    (0x0FFF&(*NFI_CSEL))
    //#define CS1    (0x8000|(*NFI_CSEL&0x0FFF))

#define NFI_ECC_RDY   ((volatile u32 *)(NFI_BASE+0xFFC))
      #define WAIT_RDY_MASK         0x1000

#define NFI_CLK_SEL       ((volatile u32 *)(CKGEN_BASE+0x70))

#define PAD_CFG_5           ((volatile u32 *)(CKGEN_BASE+0xD4))
    #define NFCEN2_SEL_PWM0             0x8000

#define NFI_NFI2                ((volatile u32 *)(0x70000064))
    #define NFI2_MASK                            0x00400000

/*******************************************************************************
 * NFI register definition
 *******************************************************************************/
/* NFI_CNFG */
#define AHB_MODE        0x0001
#define READ_MODE      0x0002
#define RB_CS1                0x0010  // NFI2+0090[4]
#define SEL_SEC_512BYTE 0x0020
#define BYTE_RW		      0x0040
#define HW_ECC_EN      0x0100
#define AUTO_FMT_EN  0x0200
#define OP_IDLE            0x0000
#define OP_READ           0x1000
#define OP_READ_ID_ST   0x2000
#define OP_PROGRAM    0x3000
#define OP_ERASE         0x4000
#define OP_RESET         0x5000
#define OP_CUSTOM    0x6000

/* NFI_PAGEFMT */
#define PAGEFMT_2K_512			0x0000
#define PAGEFMT_4K_2K			0x0001
#define PAGEFMT_8K_4K         0x0002
#define PAGEFMT_8BITS		0x0000
#define PAGEFMT_16BITS		0x0008
#define SPARE_32_16  0x0000
#define SPARE_52_26  0x0010
#define SPARE_54_27  0x0020
#define FDM_NUM(x)                (((u32) x &0x1F) << 6)
#define FDM_ECC_NUM(x)                (((u32) x &0x1F) << 11)


/* NFI_CON */
#define FIFO_FLUSH       0x0001
#define NFI_RST             0x0002
#define SINGLE_RD          0x0010
#define NOB_BYTE             0x0020
#define NOB_WORD           0x0040
#define NOB_DWORD          0x0080
#define BURST_RD           0x0100
#define BURST_WR           0x0200
#define SEC_NUM(x)                (((u32) x &0x0F) << 12)

/* NFI_ACCCON */
//#define ACCCON				0x07C6	// C2R=111, W2R=11, WH=00, WST=00, RLT=10
//#define ACCCON        0x071C0002  // C2R=111, W2R=11, WH=00, WST=00, RLT=10
#define ACCCON        (LCD2NAND| PRECS|C2R | W2R | WH | WST | RLT)


//DH
// NFI_CLK
//#if(CONFIG_DRV_MT8520)
#ifdef ARCH_MT8520
#define NFI_CLK_27  0x0
#define NFI_CLK_162 0x50
#define NFI_CLK NFI_CLK_162
#define PDN_NFI 0x02000000

    #if NFI_CLK == NFI_CLK_27
#define RLT           0x01        // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=2
#define WST           (0x01 << 4)   // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=1
#define WH            (0x02 << 8)  // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=1
#define W2R           (0x01 << 12) // 0:2T, 1:4T, 2:6T, 3:8T ; recommanded value=3
#define C2R           ((u32)0x01 << 16) // C2R=111
#define PRECS       ((u32)0x01 << 22)
#define LCD2NAND      ((u32)0x01 << 28)
    #elif NFI_CLK == NFI_CLK_162
#define RLT           0x04        // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=2
#define WST           (0x04 << 4)   // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=1
#define WH            (0x02<< 8)  // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=1
#define W2R           (0x0 << 12) // 0:2T, 1:4T, 2:6T, 3:8T ; recommanded value=3
#define C2R           ((u32)0x0 << 16) // C2R=111
#define PRECS       ((u32)0x0 << 22)
#define LCD2NAND      ((u32)0x0 << 28)
    #endif
#else
#define PDN_NFI 0x00080000
#define NFI_CLK_144 0x00030000
#define NFI_CLK_27 0x0
#define NFI_CLK_200 0x00050000
#define NFI_CLK_234 0x00010000
#define NFI_CLK NFI_CLK_144

    #if NFI_CLK == NFI_CLK_27
#define RLT           0xF        // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=2
#define WST           (0xF << 4)   // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=1
#define WH            (0xF<< 8)  // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=1
#define W2R           (0xF << 12) // 0:2T, 1:4T, 2:6T, 3:8T ; recommanded value=3
#define C2R           ((u32)0x3F << 16) // C2R=111
#define PRECS       ((u32)0x0F << 22)
#define LCD2NAND      ((u32)0xF << 28)    
    #else
#define RLT           0x05        // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=2
#define WST           (0x03 << 4)   // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=1
#define WH            (0x1<< 8)  // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=1
#define W2R           (0x0 << 12) // 0:2T, 1:4T, 2:6T, 3:8T ; recommanded value=3
#define C2R           ((u32)0x0 << 16) // C2R=111
#define PRECS       ((u32)0x0 << 22)
#define LCD2NAND      ((u32)0x0 << 28)
    #endif

#endif


/*
#define RLT           0x0f        // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=2
#define WST           0x0f << 4   // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=1
#define WH            0x0f << 8  // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=1
#define W2R           0x0f << 12 // 0:2T, 1:4T, 2:6T, 3:8T ; recommanded value=3
#define C2R           0x3f << 16 // C2R=111
#define PRECS       0x0f << 22
#define LCD2NAND 0x0f << 28
*/

/*
//WCP
#define RLT           0x01        // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=2
#define WST           0x01 << 4   // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=1
#define WH            0x02 << 8  // 0:0T, 1:1T, 2:2T, 3:3T ; recommanded value=1
#define W2R           0x03 << 12 // 0:2T, 1:4T, 2:6T, 3:8T ; recommanded value=3
#define C2R           0x02 << 16 // C2R=111
#define PRECS       0x3F << 22
#define LCD2NAND 0xFF << 28
*/

/* NFI_INTR_EN */
#define RD_DONE_EN     0x01
#define WR_DONE_EN     0x02
#define RESET_DONE_EN  0x04
#define ERASE_DONE_EN  0x08
//#define ERR_DET_EN         0x10
//#define ERR_COR_EN         0x100
#define BUSY_RETURN_EN     0x0010
#define ACCESS_LOCK_EN     0x0020
#define AHB_DONE_EN          0x0040
//#define ALL_EN             0xFFFF

/* NFI_INTR */
#define RD_DONE    0x01
#define WR_DONE     0x02
#define RESET_DONE  0x04
#define ERASE_DONE  0x08
//#define ERR_DET_EN         0x10
//#define ERR_COR_EN         0x100
#define BUSY_RETURN     0x0010
#define ACCESS_LOCK     0x0020
#define AHB_DONE          0x0040

/* NFI_ADDRNOB */
#define COL_ADDR_NOB(x)   (((u32) x &0x0F) )
#define ROW_ADDR_NOB(x)   (((u32) x &0x0F) << 4 )

/* NFI_STRDATA */
#define STR_DATA    0x01

/* NFI_STA  */
#define STATUS_CMD         0x1
#define STATUS_ADDR        0x2
#define STATUS_DATAR       0x4
#define STATUS_DATAW       0x8
#define STATUS_BUSY        0x100


/* NFI_FIFOSTA */
#define RD_EMPTY_MASK      0x0040
#define RD_FULL_MASK       0x0080
#define WR_EMPTY_MASK      0x4000
#define WR_FULL_MASK      	0x8000
#define RD_REMAIN_MASK    0x001F
//#define FIFO_FLUSH         0x010
//#define RESET              0x020




/* NFI_CSEL */
#define DEVICE_512		   0x00
#define DEVICE_2K		      0x01


/* Status register */
#define STATUS_FAIL			0x01
#define STATUS_READY		0x40
#define STATUS_WR_ALLOW		0x80
 

/*NFI_DMA*/
#define DMA_RDTRIG				((u32) 1 << 1)
#define DMA_WRTRIG				((u32) 1 << 0)

#define NFI_Wait_Ready(timeout)   while ( (*NFI_STA  & STATUS_BUSY) && (timeout--) )

/* FLASH OPERATION STATUS */
typedef enum {
	 S_DONE = 0
	,S_PGM_FAILED
	,S_ERASE_FAILED
	,S_TIMEOUT
	,S_IN_PROGRESS
	,S_CMD_ERR
	,S_BLOCK_LOCKED_ERR
	,S_BLOCK_UNSTABLE
	,S_VPP_RANGE_ERR
	,S_INVALID_BEGIN_ADDR
	,S_INVALID_RANGE
	,S_PGM_AT_ODD_ADDR
	,S_PGM_WITH_ODD_LENGTH
	,S_BUFPGM_NO_SUPPORT
	,S_UNKNOWN_ERR
	,S_BAD_BLOCK
	,S_ECC_1BIT_CORRECT
	,S_ECC_2BITS_ERR
	,S_ECC_UNCORRECT_ERR
	,S_ECC_CORRECTABLE_ERR
	,S_SPARE_CHKSUM_ERR
	,S_HW_COPYBACK_ERR
	,S_INVALID_PAGE_INDEX
	,S_NFI_NOT_SUPPORT
	,S_NFI_CS1_NOT_SUPPORT
	,S_NFI_16BITS_IO_NOT_SUPPORT
	,S_NO_GOOD_BLOCK_FOUND
	,S_SETUP_PLL_ERR
	,S_MOBILE_RAM_NOT_SUPPORT
	,S_RAM_FLOARTING
	,S_RAM_UNACCESSABLE
	,S_RAM_ERROR
	,S_DEVICE_NOT_FOUND
	,S_REACH_END_ADDR
	,S_BLOADER_IS_TOO_LARGE
	,S_SIBLEY_REWRITE_OBJ_MODE_REGION
	,S_SIBLEY_WRITE_B_HALF_IN_CTRL_MODE_REGION
	,S_SIBLEY_ILLEGAL_CMD
	,S_SIBLEY_PGM_AT_THE_SAME_REGIONS
	,S_CREATE_SEMAPHORE_FAIL
	,S_DEL_SEMAPHORE_FAIL
	,S_CREATE_EVENT_FAIL
  ,S_DEL_EVENT_FAIL	
	,S_REG_ISR_FAIL
	,S_UNREG_ISR_FAIL	
	,STATUS_END
} STATUS_E;

#define NFI_Wait(condition_expression, timeout)		while( (condition_expression) && (--timeout) )


#define NFI_EVENT_NAME 		"NFI_EVENT"

#define SECTOR_BYTES  ((*NFI_CNFG&SEL_SEC_512BYTE)?512:1024)

    #ifdef _XOS_ISR_ENABLE_
extern HANDLE_T _hNFIEvent;
extern HANDLE_T _hNFISema;
    #else

    #endif //_XOS_ISR_ENABLE

//extern BOOL _bUsingISR;


#endif //_NFI_H
