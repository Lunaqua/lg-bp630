/**
 * \file mtp-device.h
 * List of mtp probly devices as USB ids.
 *
 * Copyright (C) 2005-2007 mtk 
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * This file is supposed to be included within a struct from both libmtp
 * and libgphoto2.
 *
 * Information can be harvested from Windows driver .INF files, see:
 * http://msdn.microsoft.com/en-us/library/aa973606.aspx
 */
 /**********************************
  * only the device class & interface class
  * match following case, libmtp will to
  * check wheather it is a MTP device.
  * device class        interface clas
  *************************************/
{0x0,     0x6},
{0x6,     0x6},
{0xff,    0x6},
{0x6,     0xff},
{0x6,     0x0},
{0x0,     0x0},
{0xff,    0xff},
{0xff,    0x0},
{0x0,     0xff}
 #if !CFG_MASS_STORAGE_FIRST
 ,{0x0,     0x8}
 #endif
   