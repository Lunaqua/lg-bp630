/**
 * \file music-players.h
 * List of music players as USB ids.
 *
 * Copyright (C) 2005-2007 Richard A. Low <richard@wentnet.com>
 * Copyright (C) 2005-2009 Linus Walleij <triad@df.lth.se>
 * Copyright (C) 2006-2007 Marcus Meissner
 * Copyright (C) 2007 Ted Bullock
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
/*
 * un-support MTP device list, trying real bad to get all devices into
 * this list by stealing from everyone I know.
 */

  /*
   * Creative Technology
   * Initially the Creative devices was all we supported so these are
   * the most thoroughly tested devices. Presumably only the devices
   * with older firmware (the ones that have 32bit object size) will
   * need the DEVICE_FLAG_BROKEN_MTPGETOBJPROPLIST_ALL flag. This bug
   * manifest itself when you have a lot of folders on the device,
   * some of the folders will start to disappear when getting all objects
   * and properties.
   */
 
  /*
   * Philips
   */
  
  { "Philips", 0x0471, NULL, 0x2006, 0 },   
  { "Philips", 0x0471, NULL, 0x2004, 0 },
  { "Genesys", 0x05e3, NULL, 0x0718, 0 },
  { "Philips", 0x0471, NULL, 0x0848, 0 },
  { "Philips", 0x0471, "SA401X", 0x841,0},
  { "Sony",    0x054c, "Walkman NWZ-E436F", 0x0385, 0},
  { "Sony",    0x054c, "Walkman NWZ-A826/NWZ-A828/NWZ-A829", 0x035b, 0},
  { "SanDisk", 0x0781, "Sansa e280", 0x7421, 0},
  { "Samsung", 0x04e8, "YH-999", 0x5a0f, 0},
  { "iRiver", 0x1006, "Portable Media Center", 0x4002, 0},
  { "YP-T7V",  0x04e8, NULL, 0x5027, 0 }
      
  

      
  
