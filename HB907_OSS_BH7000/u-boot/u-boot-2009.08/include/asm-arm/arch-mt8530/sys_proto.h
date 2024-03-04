/*
 * See file CREDITS for list of people who contributed to this
 * project.
 *
 *  Copyright(C) 2006, NXP BV, All rights reserved.
 *  by Jean-Paul Saman
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
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

#ifndef _ENERGIZER_SYS_PROTO_H_
#define _ENERGIZER_SYS_PROTO_H_

//void peripheral_enable(void);

u32 get_cpu_type(void);
u32 get_cpu_rev(void);
u32 get_mem_type(void);
u32 wait_on_value(u32 read_bit_mask, u32 match_value, u32 read_addr, u32 bound);
u32 get_board_type(void);
void display_board_info(u32);

#endif
