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

/** @file fcihw_io.h
 *  FCI io function declaration.
 */

#ifndef FCIHW_IO_H
#define FCIHW_IO_H

//#define REG_DEBUG

//---------------------------------------------------------------------------
// Include files
//---------------------------------------------------------------------------

//---------------------------------------------------------------------------
// Type definitions
//---------------------------------------------------------------------------

/**
* Base address definition
*/


#define MBIM_VIRT                       (IO_BASE + 0x08000)
#define SDIO_BASE                       (IO_BASE + 0x0B000)

/**
* FCI interrupt vector id
*/
#define FCI_INT_VECTOR_ID              ((uint32_t)28)

#define MGC_BIM_READ32(_offset)         *((volatile uint32_t *)(MBIM_VIRT + _offset))
#define MGC_BIM_WRITE32(_offset, value)  (*((volatile uint32_t *)((MBIM_VIRT)+ (_offset))) = value)

/**
* Read an 8-bit register from the core
* @param _offset offset into the core's register space
* @return 8-bit datum
*/
#define FCI_READ8(_offset) *((volatile uint8_t *)((SDIO_BASE)+ (_offset)))

/**
* Read a 16-bit register from the core
* @param _offset offset into the core's register space
* @return 16-bit datum
*/
#define FCI_READ16(_offset) *((volatile uint16_t *)((SDIO_BASE)+ (_offset)))

/**
* Write an 8-bit core register
* @param _pBase core base address in memory
* @param _offset offset into the core's register space
* @param _data 8-bit datum
*/
#define FCI_WRITE8(_offset, _data)                                                   \
{                                                                                            \
volatile uint32_t u4TmpVar;                                                                  \
u4TmpVar = *((volatile uint32_t*)(((uint32_t)SDIO_BASE)  + ((uint32_t)(_offset) & 0xFC))); \
u4TmpVar &= ~(((uint32_t)0xFF) << (8*((uint32_t)(_offset) & 0x03)));                                   \
u4TmpVar |= (uint32_t)(((_data) & 0xFF) << (8*((uint32_t)(_offset) & 0x03)));                          \
*((volatile uint32_t*)(((uint32_t)SDIO_BASE)  + ((uint32_t)(_offset) & 0xFC))) = u4TmpVar; \
}

/**
* Write a 16-bit core register
* @param _pBase core base address in memory
* @param _offset offset into the core's register space
* @param _data 16-bit datum
*/
#define FCI_WRITE16(_offset, _data)                                                  \
{                                                                                            \
volatile uint32_t u4TmpVar;                                                                  \
u4TmpVar = *((volatile uint32_t*)(((uint32_t)SDIO_BASE)  + ((uint32_t)(_offset) & 0xFC))); \
u4TmpVar &= ~(((uint32_t)0xFFFF) << (8*((uint32_t)(_offset) & 0x03)));                                 \
u4TmpVar |= (_data) << (8*((uint32_t)(_offset) & 0x03));                                               \
*((volatile uint32_t*)(((uint32_t)SDIO_BASE)  + ((uint32_t)(_offset) & 0xFC))) = u4TmpVar; \
}

//============================================================================
// Macros for register read/write
//============================================================================
#define CKGEN_READ32(offset)           (*((volatile UINT32*)(CKGEN_BASE + offset)))
#define CKGEN_WRITE32(offset, value)   (*((volatile UINT32*)(CKGEN_BASE + offset)) = (value))
#define CKGEN_REG32(offset)            (*((volatile UINT32*)(CKGEN_BASE + offset)))

#define CKGEN_SETBIT(offset, dBit)        CKGEN_WRITE32(offset, CKGEN_READ32(offset) | (dBit))
#define CKGEN_CLRBIT(offset, dBit)        CKGEN_WRITE32(offset, CKGEN_READ32(offset) & (~(dBit)))

//============================================================================
// Macros for register read/write
//============================================================================
#define PDWNC_READ8(offset)                       (*((volatile UINT8*)(PDWNC_BASE + offset)))
#define PDWNC_READ16(offset)                     (*((volatile UINT16*)(PDWNC_BASE + offset)))
#define PDWNC_READ32(offset)                     (*((volatile UINT32*)(PDWNC_BASE + offset)))

#define PDWNC_WRITE8(offset, value)               (*((volatile UINT8*)(PDWNC_BASE + offset)) = (value))
#define PDWNC_WRITE16(offset, value)              (*((volatile UINT16*)(PDWNC_BASE + offset)) = (value))
#define PDWNC_WRITE32(offset, value)		(*((volatile UINT32*)(PDWNC_BASE + offset)) = (value))

#define PDWNC_REG32(offset)					(*((volatile UINT32*)(PDWNC_BASE + offset)))

#define PDWNC_SETBIT(offset, dBit)       PDWNC_WRITE32(offset, PDWNC_REG32(offset) | (dBit));
#define PDWNC_CLRBIT(offset, dBit)       PDWNC_WRITE32(offset, PDWNC_REG32(offset) & (~dBit));

#ifdef REG_DEBUG
extern void FCIHW_IoWr(UINT32 u4Offset, UINT32 u4Value, INT8 *szFile, INT32 Line);
extern UINT32 FCIHW_IoRd(UINT32 u4Offset, INT8 *szFile, INT32 Line);
#define FCI_WRITE32(offset, value)      FCIHW_IoWr(offset, value, (INT8 *)__func__, __LINE__)
#define FCI_READ32(offset)              FCIHW_IoRd(offset, (INT8 *)__func__, __LINE__)
#else

/**
* Read a 32-bit register from the core
* @param _offset offset into the core's register space
* @return 32-bit datum
*/
#define FCI_READ32(_offset) *((volatile uint32_t *)((SDIO_BASE)+ (_offset)))

/**
* Write a 32-bit core register
* @param _pBase core base address in memory
* @param _offset offset into the core's register space
* @param _data 32-bit datum
*/
#define FCI_WRITE32(_offset, _data) \
(*((volatile uint32_t *)(((uint32_t)SDIO_BASE) + (_offset))) = (_data))

#endif

//---------------------------------------------------------------------------
// Inter-file functions
//---------------------------------------------------------------------------

//---------------------------------------------------------------------------
// Public functions
//---------------------------------------------------------------------------


#endif // FCIHW_IO_H
