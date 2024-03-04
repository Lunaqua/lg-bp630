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

#ifndef _MT85XX_SYS_H_
#define _MT85XX_SYS_H_

#include <asm/sizes.h>
#include <chip_ver.h>

#define CONFIG_MT8520 0
#define CONFIG_MT8530 1

/* depends on ic */
#if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8520)
#include <asm/arch/mt8520.h>
  error "config should be mt8530."
#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555)
#include <asm/arch/mt8555.h>
#else
#include <asm/arch/mt8530.h>
#endif


/* Common */

/* RISC work mode define */
#define Mode_USR            0x10
#define Mode_FIQ            0x11
#define Mode_IRQ            0x12
#define Mode_SVC            0x13
#define Mode_ABT            0x17
#define Mode_UDF            0x1B
#define I_Bit               0x80
#define F_Bit               0x40


#endif

