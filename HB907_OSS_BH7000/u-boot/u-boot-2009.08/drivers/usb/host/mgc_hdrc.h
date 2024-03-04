/*-----------------------------------------------------------------------------
 * Copyright (c) 2006, MediaTek Inc.
 * All rights reserved.
 *
 * Unauthorized use, practice, perform, copy, distribution, reproduction,
 * or disclosure of this information in whole or in part is prohibited.
 *---------------------------------------------------------------------------
 *
 * $Author: richard.sun $
 * $Date: 2010/12/08 $
 * $RCSfile: mgc_hdrc.h,v $
 * $Revision: #1 $
 *
 *---------------------------------------------------------------------------*/

/** @file mgc_hdrc.h
 *  This header file implements the mtk53xx USB host controller driver.
 *
 * Status:  Passed basic stress testing, works with hubs, mice, keyboards,
 * and usb-storage.
 *
 * TODO:
 * - usb suspend/resume triggered by prHcd (with USB_SUSPEND)
 * - various issues noted in the code
 * - performance work; use both register banks; ...
 * - use urb->iso_frame_desc[] with ISO transfers
 */

#ifndef _MGC_HDRC_H_
#define _MGC_HDRC_H_

#include "asm/arch/mt8530.h"

#define IO_BASE_USB                     IO_BASE

#define USB1_BASE	        				(0x0E000)
#define USB2_BASE	        				(0x3C000)
#define USB3_BASE	        				(0x0F000)

// Modify USB_ADDR to support multiple port usb.
#define USB_ADDR	        				(IO_BASE_USB + (UINT32)pUsbBase)

#define USB_DMA_OFFSET				(0x00A00)
#define USB_BASE_OFFSET				(0x00800)
#define USB_MISC_OFFSET				(0x00600)
#define USB_CBUF_OFFSET				(0x00500)
#define USB_PHY_OFFSET				(0x00400)
#define USB_ANAPHYBASE                          (0x00700)


/*
 *     MUSBHDRC Register map 
 */

/* Common USB registers */

#define M_REG_FADDR        0x00   /* 8 bit */
#define M_REG_POWER        0x01   /* 8 bit */
#define M_REG_INTRTX       0x02  
#define M_REG_INTRRX       0x04
#define M_REG_INTRTXE      0x06  
#define M_REG_INTRRXE      0x08  
#define M_REG_INTRUSB      0x0A   /* 8 bit */
#define M_REG_INTRUSBE     0x0B   /* 8 bit */
#define M_REG_FRAME        0x0C  
#define M_REG_INDEX        0x0E   /* 8 bit */
#define M_REG_TESTMODE     0x0F   /* 8 bit */
#define M_REG_TARGET_FUNCTION_BASE     0x80   /* 8 bit */

/* Indexed registers */

#define M_REG_TXMAXP       0x10
#define M_REG_CSR0         0x12
#define M_REG_TXCSR        0x12
#define M_REG_RXMAXP       0x14
#define M_REG_RXCSR        0x16
#define M_REG_COUNT0       0x18
#define M_REG_RXCOUNT      0x18
#define M_REG_TXTYPE       0x1A    /* 8 bit, only valid in Host mode */
#define M_REG_TYPE0        0x1A    /* 2 bit, only valid in MDRC Host mode */
#define	M_REG_NAKLIMIT0	   0x1B    /* 8 bit, only valid in Host mode */
#define M_REG_TXINTERVAL   0x1B    /* 8 bit, only valid in Host mode */
#define M_REG_RXTYPE       0x1C    /* 8 bit, only valid in Host mode */
#define M_REG_RXINTERVAL   0x1D    /* 8 bit, only valid in Host mode */
#define M_REG_CONFIGDATA   0x1F    /* 8 bit */
#define M_REG_FIFOSIZE     0x1F    /* 8 bit */    




/* FIFOs for Endpoints 0 - 15, 32 bit word boundaries */

#define M_FIFO_EP0         0x20

/* Additional Control Registers */

#define	M_REG_DEVCTL	   0x60	   /* 8 bit */    

/* Dynamic FIFO sizing */

#define M_REG_TXFIFOSZ     0x62    /* 8 bit, TxFIFO size */
#define M_REG_RXFIFOSZ     0x63    /* 8 bit, RxFIFO size */
#define M_REG_TXFIFOADD    0x64    /* 16 bit, TxFIFO address */
#define M_REG_RXFIFOADD    0x66    /* 16 bit, RxFIFO address */

//  MTK Notice: Max Liao, 2006/08/29.
//  added for non-32 bits aligned fifo read/write. Base addr = USB_BASE = 0x20029600.
//#ifdef CONFIG_ARCH_MT5381
//#undef MTK_MHDRC
//#else
//#define MTK_MHDRC
//#endif
//For MT8530, should define MTK_MHDRC
#define MTK_MHDRC

#ifdef MTK_MHDRC
#define	M_REG_FIFOBYTECNT	 0xEC
#else
#define	M_REG_FIFOBYTECNT	 0x80
#endif

//  MTK Notice: Max Liao, 2006/08/18.
//  support one to one mapping ep and device address.
#define	M_REG_EP0ADDR	 0x90
#define	M_REG_EP1ADDR	 0x94
#define	M_REG_EP2ADDR	 0x98
#define	M_REG_EP3ADDR	 0x9C
#define	M_REG_EP4ADDR	 0xA0
#define	M_REG_EPXADDR(X)	 (M_REG_EP0ADDR + ((X) << 2))

//  MTK Notice: Max Liao, 2006/05/22.
//  read PHY line state.
#define M_REG_PHYC6		                0x14
#define M_REG_LINESTATE_MASK	0x00030000

#define LINESTATE_DISCONNECT         0x00000000
#define LINESTATE_FS_SPEED              0x00010000
#define LINESTATE_LS_SPEED              0x00020000
#define LINESTATE_HWERROR              0x00030000
    
//  MTK Notice: Max Liao, 2006/05/29.
//  MTK add: soft reset register. Base addr = USB_MISCBASE.
#define M_REG_SOFTRESET		                    0x0
#define M_REG_SOFT_RESET_ACTIVE             0x0 
#define M_REG_SOFT_RESET_DEACTIVE         0x3

//  MTK add: access unit control register. Base addr = USB_MISCBASE.
#define M_REG_ACCESSUNIT		            0x4
#define M_REG_ACCESSUNIT_8BIT           0x0
#define M_REG_ACCESSUNIT_16BIT         0x1
#define M_REG_ACCESSUNIT_32BIT         0x2
#define M_REG_DEV_ADDR_MODE                    0x10

//  MTK add: data toggle control register. Base addr = USB_MISCBASE.
#define M_REG_RXDATATOG		            0x10
#define M_REG_TXDATATOG		            0x14
#define M_REG_SET_DATATOG(ep, v)	    (((1 << ep) << 16) | (v << ep))

//  MTK add: request packet number. Base addr = USB_DMABASE.
#define M_REG_REQPKT(ep)	                    (0x100 + ((ep)<<2))	      

//  MTK Notice: Max Liao, 2006/09/19.
//  MTK add: IN packet interrupt. Base addr = USB_MISCBASE.
/*
0x20029608[15:0] : Interrupt mask ( default : 16'hFFFF, will change to 16'h0 later)
0x2002960C [15:0]: interrupt status ( default : 0)
bit[15:0] RX IN endpoint request bit[0] : EP0, bit[1] : EP1, ...
Notes: Endpoint number is logical endpoint number, not physical.
*/
#define M_REG_INPKT_ENABLE	            0x8
#define M_REG_INPKT_STATUS	            0xC

//  MTK Notice: Max Liao, 2006/09/19.
//  MTK add: Powe down register. Base addr = USB_MISCBASE.
/*
bit 0 : Enable DRAM clock, default : 1<Enable>
bit 1 : Enable Hardware Auto-PowerDown/Up, default : 0<Disable>, Auto-Clear after PowerUp
bit 2 : Read Only, 1: PHY Clock valid, 0: PHY clock is off.
After turn off DRAM clock, only 0x20029680 registers is accessable.
Write other registers makes no effect, and read other registers return constant value, 32'hDEAD_BEAF
*/
#define M_REG_AUTOPOWER	                    0x80
#define M_REG_AUTOPOWER_DRAMCLK         0x01
#define M_REG_AUTOPOWER_ON                     0x02
#define M_REG_AUTOPOWER_PHYCLK             0x04

#define MOTG_PHY_REG_READ32(r)\
    *((volatile UINT32 *)(USB_ADDR + USB_PHY_OFFSET + (r)))

#define MOTG_PHY_REG_WRITE32(r, v)\
   *((volatile UINT32 *)(USB_ADDR + USB_PHY_OFFSET + (r))) = v

#define MOTG_ANAPHY_REG_READ32(r)\
    *((volatile UINT32 *)(USB_ADDR + USB_ANAPHYBASE + (r)))

#define MOTG_ANAPHY_REG_WRITE32(r, v)\
   *((volatile UINT32 *)(USB_ADDR + USB_ANAPHYBASE + (r))) = v

#define MOTG_MISC_REG_READ32(r)\
    *((volatile UINT32 *)(USB_ADDR + USB_MISC_OFFSET + (r)))

#define MOTG_MISC_REG_WRITE32(r, v)\
    *((volatile UINT32 *)(USB_ADDR + USB_MISC_OFFSET + (r))) = v

#define MOTG_DMA_REG_READ32(r)\
    *((volatile UINT32 *)(USB_ADDR + USB_DMA_OFFSET + (r)))
    
#define MOTG_DMA_REG_WRITE32(r, v)\
    *((volatile UINT32 *)(USB_ADDR + USB_DMA_OFFSET + (r))) = v

#define MOTG_REG_READ8(r)\
    *((volatile UINT8 *)(USB_ADDR + USB_BASE_OFFSET + (r)))

#define MOTG_REG_WRITE8(r,v)\
    do {\
    volatile UINT32 temp; \
    temp = *((volatile UINT32 *)(USB_ADDR + USB_BASE_OFFSET + ((r) & 0xFC))); \
    temp &= ~(0xFF << (8*((r) & 0x03)));\
    temp |= (volatile UINT32)((v) << (8*((r) & 0x03)));\
    *((volatile UINT32 *)(USB_ADDR + USB_BASE_OFFSET + ((r) & 0xFC))) = temp; \
    }while(0)

#define MOTG_REG_READ16(r)\
    *((UINT16 *)(USB_ADDR + USB_BASE_OFFSET + (r)))

#define MOTG_REG_WRITE16(r,v)\
    do {\
    volatile UINT32 temp; \
    temp = *((volatile UINT32 *)(USB_ADDR + USB_BASE_OFFSET+ ((r) & 0xFC))); \
    temp &= ~(0xFFFF << (8*((r) & 0x03)));\
    temp |= (volatile UINT32)((v) << (8*((r) & 0x03)));\
    *((volatile UINT32 *)(USB_ADDR + USB_BASE_OFFSET+ ((r) & 0xFC))) = temp; \
    }while(0)
        
#define MOTG_REG_READ32(r)\
    *((volatile UINT32 *)(USB_ADDR + USB_BASE_OFFSET + (r)))

#define MOTG_REG_WRITE32(r,v)\
    *((volatile UINT32 *)(USB_ADDR + USB_BASE_OFFSET + (r))) = v

#define MTKCBUF_REG_READ32(r)\
    *((volatile UINT32 *)(USB_ADDR + USB_CBUF_OFFSET + (r)))
    
#define MTKCBUF_REG_WRITE32(r,v)\
    *((volatile UINT32 *)(USB_ADDR + USB_CBUF_OFFSET + (r))) = v

#define FIFO_ADDRESS(e)\
    (USB_ADDR + (USB_BASE_OFFSET) + (e<<2) + M_FIFO_EP0)

#define REG_RO_CHIP_ID      0x01fc      // Chip ID Register

/* "bus control" registers */
#define MGC_O_MHDRC_TXFUNCADDR	0x00
#define MGC_O_MHDRC_TXHUBADDR	0x02
#define MGC_O_MHDRC_TXHUBPORT	0x03

#define MGC_O_MHDRC_RXFUNCADDR	0x04
#define MGC_O_MHDRC_RXHUBADDR	0x06
#define MGC_O_MHDRC_RXHUBPORT	0x07

#define MGC_BUSCTL_OFFSET(_bEnd, _bOffset)	(0x80 + (8*_bEnd) + _bOffset)

/* TxType/RxType */
#define MGC_M_TYPE_PROTO	0x30
#define MGC_S_TYPE_PROTO	4
#define MGC_M_TYPE_REMOTE_END	0xf
    
/*
 *     MUSBHDRC Register bit masks
 */

/* POWER */

#define M_POWER_ISOUPDATE   0x80 
#define	M_POWER_SOFTCONN    0x40
#define	M_POWER_HSENAB	    0x20
#define	M_POWER_HSMODE	    0x10
#define M_POWER_RESET       0x08
#define M_POWER_RESUME      0x04
#define M_POWER_SUSPENDM    0x02
#define M_POWER_ENSUSPEND   0x01

/* INTRUSB, INTRUSBE */

#define M_INTR_VBUSERROR    0x80   /* only valid when A device */
#define M_INTR_SESSREQ      0x40   /* only valid when A device */
#define M_INTR_DISCONNECT   0x20
#define M_INTR_CONNECT      0x10   /* only valid in Host mode */
#define M_INTR_SOF          0x08 
#define M_INTR_RESET        0x04
#define M_INTR_BABBLE       0x04
#define M_INTR_RESUME       0x02
#define M_INTR_SUSPEND      0x01   /* only valid in Peripheral mode */

/* TESTMODE */

#define M_TEST_FORCEFS      0x20
#define M_TEST_FORCEHS      0x10
#define M_TEST_PACKET       0x08
#define M_TEST_K            0x04
#define M_TEST_J            0x02
#define M_TEST_SE0_NAK      0x01

/* DEVCTL */

#define M_DEVCTL_BDEVICE    0x80   
#define M_DEVCTL_FSDEV      0x40
#define M_DEVCTL_LSDEV      0x20
#define M_DEVCTL_HM         0x04
#define M_DEVCTL_HR         0x02
#define M_DEVCTL_SESSION    0x01

/* CSR0 in Peripheral and Host mode */

#define	M_CSR0_FLUSHFIFO      0x0100
#define M_CSR0_TXPKTRDY       0x0002
#define M_CSR0_RXPKTRDY       0x0001


/* CSR0 in HSFC */

#define M_CSR0_INPKTRDY       0x02
#define M_CSR0_OUTPKTRDY      0x01

/* CSR0 in Peripheral mode */

#define M_CSR0_P_SVDSETUPEND  0x0080
#define M_CSR0_P_SVDRXPKTRDY  0x0040
#define M_CSR0_P_SENDSTALL    0x0020
#define M_CSR0_P_SETUPEND     0x0010
#define M_CSR0_P_DATAEND      0x0008
#define M_CSR0_P_SENTSTALL    0x0004

/* CSR0 in Host mode */

#define	M_CSR0_H_NAKTIMEOUT   0x0080
#define M_CSR0_H_STATUSPKT    0x0040
#define M_CSR0_H_REQPKT       0x0020
#define M_CSR0_H_ERROR        0x0010
#define M_CSR0_H_SETUPPKT     0x0008
#define M_CSR0_H_RXSTALL      0x0004
/* New in 15-July-2005 (v1.4?) */
#define M_CSR0_H_NO_PING	0x0800

/* CONFIGDATA */

#define M_CONFIGDATA_DMA        0x40
#define M_CONFIGDATA_BIGENDIAN  0x20
#define M_CONFIGDATA_HBRXE      0x10
#define M_CONFIGDATA_HBTXE      0x08
#define M_CONFIGDATA_DYNFIFO    0x04
#define M_CONFIGDATA_SOFTCONE   0x02
#define M_CONFIGDATA_UTMIDW     0x01   /* data width 0 => 8bits, 1 => 16bits */

/* TXCSR in Peripheral and Host mode */

#define M_TXCSR_AUTOSET       0x8000
#define M_TXCSR_ISO           0x4000
#define M_TXCSR_MODE          0x2000
#define M_TXCSR_DMAENAB       0x1000
#define M_TXCSR_FRCDATATOG    0x0800
#define M_TXCSR_DMAMODE       0x0400
#define M_TXCSR_CLRDATATOG    0x0040
#define M_TXCSR_FLUSHFIFO     0x0008
#define M_TXCSR_FIFONOTEMPTY  0x0002
#define M_TXCSR_TXPKTRDY      0x0001

/* TXCSR in Peripheral mode */

#define M_TXCSR_P_INCOMPTX    0x0080
#define M_TXCSR_P_SENTSTALL   0x0020
#define M_TXCSR_P_SENDSTALL   0x0010
#define M_TXCSR_P_UNDERRUN    0x0004

/* TXCSR in Host mode */

#define M_TXCSR_H_NAKTIMEOUT  0x0080
#define M_TXCSR_H_RXSTALL     0x0020
#define M_TXCSR_H_ERROR       0x0004

/* RXCSR in Peripheral and Host mode */

#define M_RXCSR_AUTOCLEAR     0x8000
#define M_RXCSR_DMAENAB       0x2000
#define M_RXCSR_DISNYET       0x1000
#define M_RXCSR_DMAMODE       0x0800
#define M_RXCSR_INCOMPRX      0x0100
#define M_RXCSR_CLRDATATOG    0x0080
#define M_RXCSR_FLUSHFIFO     0x0010
#define M_RXCSR_DATAERROR     0x0008
#define M_RXCSR_FIFOFULL      0x0002
#define M_RXCSR_RXPKTRDY      0x0001

/* RXCSR in Peripheral mode */

#define M_RXCSR_P_ISO         0x4000
#define M_RXCSR_P_SENTSTALL   0x0040
#define M_RXCSR_P_SENDSTALL   0x0020
#define M_RXCSR_P_OVERRUN     0x0004

/* RXCSR in Host mode */

#define M_RXCSR_H_AUTOREQ     0x4000
#define M_RXCSR_H_RXSTALL     0x0040
#define M_RXCSR_H_REQPKT      0x0020
#define M_RXCSR_H_ERROR       0x0004



/*
 *   Another map and bit definition for 8 bit access
 */

#define M_REG_INTRTX1      0x02   /* 8 bit */  
#define M_REG_INTRTX2      0x03   /* 8 bit */  
#define M_REG_INTRRX1      0x04   /* 8 bit */
#define M_REG_INTRRX2      0x05   /* 8 bit */
#define M_REG_INTRTX1E     0x06   /* 8 bit */  
#define M_REG_INTRTX2E     0x07   /* 8 bit */  
#define M_REG_INTRRX1E     0x08   /* 8 bit */    
#define M_REG_INTRRX2E     0x09   /* 8 bit */    
#define M_REG_TXCSR1       0x12
#define M_REG_TXCSR2       0x13
#define M_REG_RXCSR1       0x16
#define M_REG_RXCSR2       0x17

/* TXCSR in Peripheral and Host mode */

#define M_TXCSR2_AUTOSET       0x80
#define M_TXCSR2_ISO           0x40
#define M_TXCSR2_MODE          0x20
#define M_TXCSR2_DMAENAB       0x10
#define M_TXCSR2_FRCDATATOG    0x08
#define M_TXCSR2_DMAMODE       0x04

#define M_TXCSR1_CLRDATATOG    0x40
#define M_TXCSR1_FLUSHFIFO     0x08
#define M_TXCSR1_FIFONOTEMPTY  0x02
#define M_TXCSR1_TXPKTRDY      0x01

/* TXCSR in Peripheral mode */

#define M_TXCSR1_P_INCOMPTX    0x80
#define M_TXCSR1_P_SENTSTALL   0x20
#define M_TXCSR1_P_SENDSTALL   0x10
#define M_TXCSR1_P_UNDERRUN    0x04

/* TXCSR in Host mode */

#define M_TXCSR1_H_NAKTIMEOUT  0x80
#define M_TXCSR1_H_RXSTALL     0x20
#define M_TXCSR1_H_ERROR       0x04

/* RXCSR in Peripheral and Host mode */

#define M_RXCSR2_AUTOCLEAR     0x80
#define M_RXCSR2_DMAENAB       0x20
#define M_RXCSR2_DISNYET       0x10
#define M_RXCSR2_DMAMODE       0x08
#define M_RXCSR2_INCOMPRX      0x01

#define M_RXCSR1_CLRDATATOG    0x80
#define M_RXCSR1_FLUSHFIFO     0x10
#define M_RXCSR1_DATAERROR     0x08
#define M_RXCSR1_FIFOFULL      0x02
#define M_RXCSR1_RXPKTRDY      0x01

/* RXCSR in Peripheral mode */

#define M_RXCSR2_P_ISO         0x40
#define M_RXCSR1_P_SENTSTALL   0x40
#define M_RXCSR1_P_SENDSTALL   0x20
#define M_RXCSR1_P_OVERRUN     0x04

/* RXCSR in Host mode */

#define M_RXCSR2_H_AUTOREQ     0x40
#define M_RXCSR1_H_RXSTALL     0x40
#define M_RXCSR1_H_REQPKT      0x20
#define M_RXCSR1_H_ERROR       0x04


/* 
 *  DRC register access macros
 */

#define MGC_Read_RxCount(core)   MOTG_REG_READ16(core, M_REG_RXCOUNT);

/*
 *  A_DEVICE, B_DEVICE must be qualified by CID_VALID for valid context.
 *  x is a pointer to the core object.
 */
#define A_DEVICE(x)         ((x)->cid == CID_A_DEVICE)
#define B_DEVICE(x)         ((x)->cid == CID_B_DEVICE)

#define VBUS_MASK            0x18    /* DevCtl D4 - D3 */

#endif /* _MGC_HDRC_H_ */
