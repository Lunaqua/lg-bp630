/*
 * See file CREDITS for list of people who contributed to this
 * project.
 *
 *  Copyright(C) 2006 NXP BV, All rights reserved.
 *  by Jean-Paul Saman
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

#include <common.h>
#include <asm/io.h>
#include <asm/arch/mt85xx.h>
#include <asm/arch/sys_proto.h>
#include <asm/arch/sys_info.h>
#include <asm/mach-types.h>
#include <asm/arch/x_typedef.h>
#include <asm/arch/x_ckgen.h>
#include <mt85xx_gpio.h>

#include <chip_ver.h>

#ifndef __UBOOT_GPIO_PINMUX__
#define __UBOOT_GPIO_PINMUX__
#endif
#include <asm/arch/mt85xx_gpio_pinmux.h>
#include <asm/arch/mt85xx_pinmux_table.h>

//-----------------------------------------------------------------------------
// definitions
//-----------------------------------------------------------------------------
#define TOTAL_GPIO_IDX          5
#define GPIO_INDEX_MASK         ((1 << 5) - 1)

//pdwnc
#define PDWNC_PAD_PINMUX1   0x0F4
#define PDWNC_PAD_PINMUX2   0x0F8
#define PDWNC_PAD_PINMUX3   0x0FC
#define PDWNC_GPIOIN        0x0D0
#define PDWNC_GPIOEN        0x0D4
#define PDWNC_GPIOOUT       0x0D8

#define GPIO_EN_WRITE(idx,val)          CKGEN_WRITE32((REG_RW_GPIO_EN_OFFSET+(4*(idx))), (val))
#define GPIO_OUT_WRITE(idx,val)         CKGEN_WRITE32((REG_RW_GPIO_OUT_OFFSET+(4*(idx))), (val))
#define GPIO_IN_WRITE(idx,val)          CKGEN_WRITE32((REG_RW_GPIO_IN_OFFSET+(4*(idx))), (val))
#define GPIO_EN_REG(idx)                CKGEN_REG32(REG_RW_GPIO_EN_OFFSET+(4*(idx)))
#define GPIO_OUT_REG(idx)               CKGEN_REG32(REG_RW_GPIO_OUT_OFFSET+(4*(idx)))
#define GPIO_IN_REG(idx)                CKGEN_REG32(REG_RW_GPIO_IN_OFFSET+(4*(idx)))

#define PINMUX_WRITE(idx,val)           CKGEN_WRITE32((REG_RW_PINMUX_OFFSET+(4*(idx))), (val))
#define PINMUX_REG(idx)                 CKGEN_REG32(REG_RW_PINMUX_OFFSET+(4*(idx)))

#define GPIO_PDWNC_READ(i4Addr)         (*((volatile UINT32*)(PDWNC_BASE + (i4Addr)))) 
#define GPIO_PDWNC_WRITE(i4Addr, u4Val) ((*((volatile UINT32*)(PDWNC_BASE + (i4Addr)))) = (u4Val))

/*****************************************
 * Routine: BSP_PinSet
 *****************************************/
INT32 BSP_PinSet(INT32 i4FuncSel, INT32 i4Func)
{
	UINT32 u4Mask;
    UINT32 u4Val;
	
	if ((i4Func < 0) ||
		(i4Func > MAX_PINMUX_FUNCTION) ||
		(i4Func > _au1PinmuxFunctionMasks[i4FuncSel]) ||
		(i4FuncSel < 0) ||
		(i4FuncSel > MAX_PINMUX_SEL) ||
		(_au1PinmuxFunctionMasks[i4FuncSel] == 0))
	{
		return -1;
	}

#if(CONFIG_CHIP_VER_CURR >= CONFIG_CHIP_VER_MT8550)
	if(i4FuncSel >= SEL_FECTL_0)
	{
        u4Mask = ~((UINT32)_au1PinmuxFunctionMasks[i4FuncSel] << ((i4FuncSel - SEL_FECTL_0)%32));
		u4Val = CKGEN_REG32(REG_RW_PAD_FECTL_0+(4*((i4FuncSel - SEL_FECTL_0)/32)));
    	u4Val &= u4Mask;
    	u4Val |= ((UINT32)i4Func << ((i4FuncSel - SEL_FECTL_0)%32));

	    CKGEN_WRITE32(REG_RW_PAD_FECTL_0+(4*((i4FuncSel - SEL_FECTL_0)/32)),u4Val);
	}
	else
#endif
    {
    	u4Mask = ~((UINT32)_au1PinmuxFunctionMasks[i4FuncSel] << (i4FuncSel%32));
    	u4Val = PINMUX_REG(i4FuncSel/32);
    	u4Val &= u4Mask;
    	u4Val |= ((UINT32)i4Func << (i4FuncSel%32));
    	
        PINMUX_WRITE((i4FuncSel/32), (u4Val));
    }

	return 0;
}

/*****************************************
 * Routine: BSP_PinGet
 *****************************************/
INT32 BSP_PinGet(INT32 i4FuncSel)
{
    INT32 i4Data;

	if ((i4FuncSel < 0) ||
		(i4FuncSel > MAX_PINMUX_SEL))
	{
		return -1;
	}
#if(CONFIG_CHIP_VER_CURR >= CONFIG_CHIP_VER_MT8550)
	if(i4FuncSel >= SEL_FECTL_0)		
		i4Data = (CKGEN_REG32(REG_RW_PAD_FECTL_0+(4*((i4FuncSel - SEL_FECTL_0)/32))) 
		            >> ((i4FuncSel - SEL_FECTL_0)%32)) & _au1PinmuxFunctionMasks[i4FuncSel];
	else
#endif 
    i4Data = (PINMUX_REG(i4FuncSel/32) >> (i4FuncSel%32)) & _au1PinmuxFunctionMasks[i4FuncSel];
	
    return (i4Data);
}

/*****************************************
 * Routine: GPIO_InOut_Sel
 *****************************************/
void GPIO_InOut_Sel(INT32 i4GpioNum, INT32 i4Output)
{		
	UINT32 u4Val;
	INT32 i4Idx;
   
	i4Idx = ((UINT32)i4GpioNum >> TOTAL_GPIO_IDX);
   
	if((UINT32)i4GpioNum <= PIN_TCK)
	{	   
	  u4Val = GPIO_EN_REG(i4Idx);	 
	  u4Val = (i4Output) ? (u4Val | (1U << (i4GpioNum & GPIO_INDEX_MASK))) : (u4Val & ~(1U << (i4GpioNum & GPIO_INDEX_MASK)));
	  GPIO_EN_WRITE(i4Idx, u4Val);
	}
	else if((UINT32)i4GpioNum <= TOTAL_GPIO_NUM)
	{
	  u4Val = GPIO_PDWNC_READ(PDWNC_GPIOEN);	
	  u4Val = (i4Output) ? (u4Val | (1U << (i4GpioNum - PIN_VFD_STB))) : (u4Val & ~(1U << (i4GpioNum - PIN_VFD_STB)));
	  GPIO_PDWNC_WRITE(PDWNC_GPIOEN, u4Val);
	}

}  

/*****************************************
 * Routine: GPIO_Output
 *****************************************/
void GPIO_Output(INT32 i4GpioNum, INT32 i4High)
{
	UINT32 u4Val;
	INT32 i4Idx;
   
	i4Idx = ((UINT32)i4GpioNum >> TOTAL_GPIO_IDX);
   
	if((UINT32)i4GpioNum <= PIN_TCK)
	{
	  u4Val = GPIO_OUT_REG(i4Idx);
	  u4Val = (i4High) ? (u4Val | (1U << (i4GpioNum & GPIO_INDEX_MASK))) : (u4Val & ~(1U << (i4GpioNum & GPIO_INDEX_MASK)));
	  GPIO_OUT_WRITE(i4Idx, u4Val);
	}
	else if((UINT32)i4GpioNum <= TOTAL_GPIO_NUM)
	{
	  u4Val = GPIO_PDWNC_READ(PDWNC_GPIOOUT);	  
	  u4Val = (i4High) ? (u4Val | (1U << (i4GpioNum - PIN_VFD_STB))) : (u4Val & ~(1U << (i4GpioNum - PIN_VFD_STB)));
	  GPIO_PDWNC_WRITE(PDWNC_GPIOOUT, u4Val);
	}
}

/*****************************************
 * Routine: GPIO_Input
 *****************************************/
INT32 GPIO_Input(INT32 i4GpioNum)
{
	UINT32 u4Val;
	INT32 i4Idx;

	if((UINT32)i4GpioNum <= PIN_TCK)
	{
	  i4Idx = ((UINT32)i4GpioNum >> TOTAL_GPIO_IDX);
	  u4Val = GPIO_IN_REG(i4Idx);
	
	  return ((u4Val & (1U << (i4GpioNum & GPIO_INDEX_MASK))) ? 1U : 0);
	}
	else if((UINT32)i4GpioNum <= TOTAL_GPIO_NUM)
	{
	  u4Val = GPIO_PDWNC_READ(PDWNC_GPIOIN);
	  return ((u4Val & (1U << (i4GpioNum - PIN_VFD_STB))) ? 1U : 0);
	}

	return ((INT32)0);
}

/*****************************************
 * Routine: GPIO_Release
 *****************************************/
void GPIO_Release(INT32 i4GpioNum)
{
	UINT32 u4Num;
#if(CONFIG_CHIP_VER_CURR >= CONFIG_CHIP_VER_MT8550)
	UINT32 u4Temp;
#endif
	
	if((UINT32)i4GpioNum <= PIN_TCK)
	{
	  if(((UINT32)i4GpioNum >= PIN_TDO) && ((UINT32)i4GpioNum <= PIN_TCK))
	  { //for TDO, TRST, TMS, TDI, TCK 
		CKGEN_WRITE32(REG_RW_TST_CFG0, (CKGEN_READ32(REG_RW_TST_CFG0) & (~(0x1<<3))));
	  }
	
	#if(CONFIG_CHIP_VER_CURR >= CONFIG_CHIP_VER_MT8550)
	  if(((UINT32)i4GpioNum >= PIN_VOUTCLK1) && ((UINT32)i4GpioNum <= PIN_VOUTVSYNC))
	  { //for 8550 remap
		 for(u4Temp = 0; u4Temp < ((PIN_VOUTVSYNC - PIN_VOUTCLK1) + 1); u4Temp ++)
		 {
			if(_au2GPIORemapTable[u4Temp][0] == (UINT16)i4GpioNum)
			{
				u4Temp = _au2GPIORemapTable[u4Temp][1] - PIN_VFD_STB;

				u4Num = GPIO_PDWNC_READ(PDWNC_PAD_PINMUX2);
				u4Num = (u4Num & ~(1U << u4Temp));
				GPIO_PDWNC_WRITE(PDWNC_PAD_PINMUX2, u4Num);
				
				u4Num = GPIO_PDWNC_READ(PDWNC_PAD_PINMUX1);
				u4Num = (u4Num & ~(1U << u4Temp));
				GPIO_PDWNC_WRITE(PDWNC_PAD_PINMUX1, u4Num);
				
				break;
			}
		 }
	  }
#endif
	
	  for (u4Num = 1; ((u4Num < (2*_au1GPIOConfig[i4GpioNum][0])) && (u4Num < 2*MAX_PINMUX_FUNCTION)); u4Num = u4Num + 2)
	  {
 #if(CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555)
               if(_au1GPIOConfig[i4GpioNum][u4Num] == SEL_EXT_SF_CS1)
               {
                 if((BSP_PinGet(_au1GPIOConfig[i4GpioNum][u4Num]) == _au1GPIOConfig[i4GpioNum][u4Num+1])
		   	   && (BSP_PinGet(_au1GPIOConfig[i4GpioNum][u4Num+2]) == _au1GPIOConfig[i4GpioNum][u4Num+3]))
                 {
                      BSP_PinSet(_au1GPIOConfig[i4GpioNum][u4Num], 0); 
                      BSP_PinSet(_au1GPIOConfig[i4GpioNum][u4Num+2], 0);
                  }
                }
		else
  #endif
		if(BSP_PinGet(_au1GPIOConfig[i4GpioNum][u4Num]) == _au1GPIOConfig[i4GpioNum][u4Num+1])
		{
			if(_au1GPIOConfig[i4GpioNum][u4Num+1] == 0)
			{//for SFCK, SFCS, SFDO, SFDI
			  BSP_PinSet(_au1GPIOConfig[i4GpioNum][u4Num], 1);
			}
			else
			{
			  BSP_PinSet(_au1GPIOConfig[i4GpioNum][u4Num], 0);
			}
		 }
	   }
	}
	else if((UINT32)i4GpioNum <= TOTAL_GPIO_NUM)
	{
    #if(CONFIG_CHIP_VER_CURR >= CONFIG_CHIP_VER_MT8550)
	  if(((UINT32)i4GpioNum >= PIN_VFD_STB) && ((UINT32)i4GpioNum <= PIN_ETTXCLK))
#elif(CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530)
	  if(((UINT32)i4GpioNum >= PIN_VFD_STB) && ((UINT32)i4GpioNum <= PIN_LCDRD))
#endif  	
	  {
		u4Num = GPIO_PDWNC_READ(PDWNC_PAD_PINMUX2);
		u4Num = (u4Num & ~(1U << (i4GpioNum - PIN_VFD_STB)));
		GPIO_PDWNC_WRITE(PDWNC_PAD_PINMUX2, u4Num);

      #if(CONFIG_CHIP_VER_CURR >= CONFIG_CHIP_VER_MT8550)
		if(((UINT32)i4GpioNum >= PIN_VFD_STB) && ((UINT32)i4GpioNum <= PIN_LCDRD))
  #endif 
		{
		u4Num = GPIO_PDWNC_READ(PDWNC_PAD_PINMUX3);
		u4Num = (u4Num | (1U << (i4GpioNum - PIN_VFD_STB)));
		GPIO_PDWNC_WRITE(PDWNC_PAD_PINMUX3, u4Num);
	  }
	  }
 
  #if(CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555)
	 if(((UINT32)i4GpioNum >= PIN_ETMDIO) && ((UINT32)i4GpioNum <= PIN_GPIO8))
	 {
		u4Num = GPIO_PDWNC_READ(PDWNC_PAD_PINMUX3);
		u4Num = (u4Num & ~(1U << (i4GpioNum - PIN_VFD_STB)));
		GPIO_PDWNC_WRITE(PDWNC_PAD_PINMUX3, u4Num);
	 }
  #endif	

	  u4Num = GPIO_PDWNC_READ(PDWNC_PAD_PINMUX1);
	  u4Num = (u4Num | (1U << (i4GpioNum - PIN_VFD_STB)));
	  GPIO_PDWNC_WRITE(PDWNC_PAD_PINMUX1, u4Num);
	}
}


/*****************************************
 * Routine: GPIO_Config
 *****************************************/
void GPIO_Config(INT32 i4GpioNum, INT32 i4Output, INT32 i4High)
{
	GPIO_Release(i4GpioNum);
	if (i4Output == INPUT)
	{
		GPIO_InOut_Sel(i4GpioNum, INPUT);
	}
	else
	{
		GPIO_Output(i4GpioNum, i4High);
		GPIO_InOut_Sel(i4GpioNum, OUTPUT);
	}
}

/*****************************************
 * Routine: board_init
 * Description: Early hardware init.
 *****************************************/
int pinmux_init (void)
{
  #if !SUPPORT_JIG
    //GPIO_Config(PIN_VOUTHSYNC, OUTPUT, LOW);
    GPIO_Config(PIN_VOUTVSYNC, OUTPUT, LOW);
  #else
    GPIO_Config(USB_PCONT1, OUTPUT, HIGH); 
    GPIO_Config(USB_PCONT2, OUTPUT, HIGH); 
	GPIO_Config(USB_PCONT3, OUTPUT, HIGH);

	GPIO_Config(PIN_FE_RST1, OUTPUT, HIGH);
	
    if (GPIO_Input(START_BIT))
    {
        GPIO_Config(VIDEO_MUTE, OUTPUT, HIGH);
    }
  #endif

  #if SUPPORT_AMUTE
    GPIO_Config(AUDIO_MUTE, OUTPUT, LOW);   
  #endif
  
  #if SUPPORT_WP
    GPIO_Config(NAND_WP, OUTPUT, HIGH); 
  #endif

  #if SUPPORT_UPG_STATUS
    GPIO_Config(UPG_STATUS_PIN, OUTPUT, LOW);
  #endif
  
  #if SUPPORT_UPG_FLASH_LED
    GPIO_Config(PIN_CON_RED_LED, OUTPUT, LOW);
    GPIO_Config(PIN_CON_GREEN_LED, OUTPUT, LOW);
  #endif
  
	return 0;
}

/*****************************************
 * Routine: v_set_upg_status
 *****************************************/
#if (SUPPORT_UPG_LED_CUSTOMER_T)

#define UPG_PIN_RED     PIN_VIND1//PIN_ETTXD1
#define UPG_PIN_GREEN   PIN_VIND0//PIN_ETCRS

extern void udelay (unsigned long usec);

static void led_ctrl_red(BOOL b_on)
{
    GPIO_Output(UPG_PIN_RED, b_on ? LOW : HIGH);
}

static void led_ctrl_green(BOOL b_on)
{
    GPIO_Output(UPG_PIN_GREEN, b_on ? LOW : HIGH);
}

void v_set_upg_status(UPG_STATUS ustatus, u_long size)
{
    static u_long upg_time = 0;
	static int status = 0;
  
    switch(ustatus)
    {
        case UPG_ERASE:
		case UPG_PROGRAM:
		case UPG_READ:
		case UPG_CHECK:
            upg_time += size;
            if(upg_time >= 1000)
            {
                upg_time = 0;
            
                status = !status;
                led_ctrl_green(status);
            }
            break;
            
		case UPG_START:
	        GPIO_PDWNC_WRITE(0x188, 0x245);
            udelay(1000);
                
            GPIO_Config(UPG_PIN_RED, OUTPUT, HIGH);
            GPIO_Config(UPG_PIN_GREEN, OUTPUT, HIGH);
            
            led_ctrl_red(TRUE);
            led_ctrl_green(TRUE);
			return;

		case UPG_END_OK:
            led_ctrl_red(FALSE);
            led_ctrl_green(TRUE);
			return;

		case UPG_END_FAIL:
            led_ctrl_red(TRUE);
            led_ctrl_green(FALSE);
			return;

		default:
			return;
    }
}

#elif SUPPORT_UPG_FLASH_LED  
static UPG_STATUS upg_status = UPG_NONE;
void v_set_upg_status(UPG_STATUS ustatus, u_long size)
{
  #if (UPG_FLASH_LED_FORM == 0)
    static u_long upg_time = 0;
	static int status = 0;
    u_long time_now = 0;
  #endif
  
    switch(ustatus)
    {
      #if (UPG_FLASH_LED_FORM == 0)
        case UPG_ERASE:
			time_now = (size >= 0x40000) ? (3000 * (size/0x40000)) : (3000 /(0x40000/size));
			break;

		case UPG_PROGRAM:
			time_now = (size >= 0x800) ? (1000 * (size/0x800)) : (1000 /(0x800/size));
			break;

		case UPG_READ:
			time_now = (size >= 0x800) ? (30 * (size/0x800)) : (30 /(0x800/size));
			break;

		case UPG_CHECK:
			time_now = (size >= 0x20000) ? (40000 * (size/0x20000)) : (40000 /(0x20000/size));
			break;
      #endif
	   
		case UPG_START:
			upg_status = UPG_START;
		  #if (UPG_FLASH_LED_FORM == 1)
		    GPIO_Output(PIN_CON_RED_LED, HIGH);
			GPIO_Output(PIN_CON_GREEN_LED, HIGH);
		  #elif (UPG_FLASH_LED_FORM == 0)
			GPIO_Output(PIN_CON_RED_LED, LOW);
			GPIO_Output(PIN_CON_GREEN_LED, LOW);
		  #endif	
			return;

		case UPG_END_OK:
			upg_status = UPG_END_OK;
		  #if (UPG_FLASH_LED_FORM == 1)
		    GPIO_Output(PIN_CON_RED_LED, LOW);
			GPIO_Output(PIN_CON_GREEN_LED, HIGH);
		  #elif (UPG_FLASH_LED_FORM == 0)	
			GPIO_Output(PIN_CON_RED_LED, HIGH);
			GPIO_Output(PIN_CON_GREEN_LED, HIGH);
		  #endif 	
			return;

		case UPG_END_FAIL:
			upg_status = UPG_END_FAIL; 
		  #if (UPG_FLASH_LED_FORM == 1)
		    GPIO_Output(PIN_CON_RED_LED, HIGH);
			GPIO_Output(PIN_CON_GREEN_LED, LOW);
		  #elif (UPG_FLASH_LED_FORM == 0)	
			GPIO_Output(PIN_CON_RED_LED, LOW);
			GPIO_Output(PIN_CON_GREEN_LED, LOW);
		  #endif	
			return;

		default:
			return;
    }

  #if (UPG_FLASH_LED_FORM == 0)	
    upg_time += time_now;

	if(upg_time >= 200000)
	{
	    upg_time = 0;

		status = (status == 0) ? 1 : 0;

	    if(status == 1)
        {
    	    GPIO_Output(PIN_CON_GREEN_LED, HIGH);
        }
    	else
    	{
    		GPIO_Output(PIN_CON_GREEN_LED, LOW);
    	}	
	}
  #endif	
}

void v_show_upg_result(void)
{
  #if (UPG_FLASH_LED_FORM == 0)
    static int flash_count = 0;
	
    if(upg_status != UPG_END_FAIL)
		return;

	switch(flash_count)
	{
		case 1:
			GPIO_Output(PIN_CON_GREEN_LED, HIGH);
			break;

		case 2:
			GPIO_Output(PIN_CON_GREEN_LED, LOW);
			GPIO_Output(PIN_CON_RED_LED, HIGH);
			break;

		case 3:
			GPIO_Output(PIN_CON_RED_LED, LOW);
			break;

		case 9:
			flash_count = 0;
			return;

		default:
			break;
	}

	flash_count ++;
  #endif	
}
#endif

//================================================
// GPIO JIG Mode Private Function
//================================================
#if SUPPORT_JIG
void vEnableJigMode0(void)
{
    GPIO_Config(JIG_MODE0, INPUT, HIGH);        	    
}

void vEnableJigMode1(void)
{
    GPIO_Config(JIG_MODE1, INPUT, HIGH);    
}

BOOL fgGetJigMode0(void) //JIG_MODE0
{
    if(GPIO_Input(JIG_MODE0))   
    {
        return TRUE;
    }
    return FALSE;
}

BOOL fgGetJigMode1(void)  //JIG_MODE1
{
    if(GPIO_Input(JIG_MODE1))   
    {
        return TRUE;
    }
    return FALSE;
}

// for jig mode check
BOOL fg_ne_bl_chk_jig(void)
{
    vEnableJigMode0();
    vEnableJigMode1();
    
    if(fgGetJigMode0()==TRUE && fgGetJigMode1()==FALSE)
    {
        return TRUE;
    }
    return FALSE;
}
#endif

//================================================
// Update UPG_STATUS for during upgrade FW.
//================================================
#if SUPPORT_UPG_STATUS
void vSetUpgStatus(BOOL fgVal)
{
	if (!fgVal)
	{
		GPIO_Output(UPG_STATUS_PIN, LOW);  
	}
	else
	{
		GPIO_Output(UPG_STATUS_PIN, HIGH);
	}
}
#endif
 
//================================================
// NAND Write Protect
//================================================
#if SUPPORT_WP
BOOL  NAND_COMMON_WriteProtect(BOOL bMode) 
{
    if ( TRUE == bMode) //write protect
    {
        GPIO_Output(NAND_WP, LOW);    
    }
    else //release write protection
    {
        GPIO_Output(NAND_WP, HIGH);
    }
    return TRUE;
}
#endif

