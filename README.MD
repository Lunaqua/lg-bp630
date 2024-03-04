# LG BP630 --
## Reverse Engineering Repository

This repository contains preliminary research concerning the reverse engineering and possible vulnerability exploitation of the LG BP630 Blu-ray (3D) Player. It released around 2012-2013. This is a fairly standard player running the so-called "Legacy NetCast" platform (used in LG devices up to 2014, before they bought and transitioned over to WebOS).

### Player Information

[LG INFO](https://www.lg.com/uk/video/blu-ray-dvd-players/bp630/)

[FIRMWARE DOWNLOAD](https://gscs-b2c.lge.com/downloadFile?fileId=KRSWD000013858-b1-a2.zip)

This player is based upon the [Mediatek MT8580](https://www.mediatek.com/products/blu-ray-and-dvd/mt8580) SOC, containing a [single-core ARM Cortex A9](https://webostv.developer.lge.com/more/netcast/platform-specifications#netcast-30-h12-2012) CPU. The operating system is LG's NetCast platform based upon a combination of HTML and Flash based interfaces, running on top of the Linux Kernel (2.6.35).

### Directory Information

`android_app`: This contains the extracted contents of the "LG AV Remote" android application. It uses UDAP to communicate with the player over the internet.

`autoscript`: This is a feature present in many MT85** series SOC's, usable via USB, while notes for this feature exist in the main binary, it is unknown whether the player allows this feature.

`ghidra_stuff`: Contains ghidra project for multiple of the machines programs. Most importantly `bdprog`.

`HB907..7000`: Contains the modified version of the Linux kernel used for a device in the same BP*** family. No modules however.

`images`: Contains relevant images to the project.

`LG_F_ROM`: This contains various information concerning the firmware update file (and hence the main firmware). Including an extracted SquashFS filesystem.

`license..00`: License documents for the kernel.

`sacd_extract`: Players using the MT8580 chipset (including some Sony and Oppo units), are used by some in the audiophile community for ripping Super-Audio CDs. This script was obtained from [here](https://audiophilestyle.com/forums/topic/28569-sacd-ripping-using-an-oppo-or-pioneer-yes-its-true/).

`udap_stuff`: LG Netcast equipment have a custom protocol called UDAP, this is based on UPnP, and utilises the HTTP/1.1 protocol for communication over TCP/IP. This protocol is well-document and may be of keen interest.

### Root files

`./BP630-.._U.pdf`: This is the user manual for the player.

`./bp630.pdf`: This is the service manual for the player.

`./README.MD`: Hello, you're here.

`./LG_BD..t.txt`: Binwalk output running on the firmware update.

## Notable Things

* The firmware is easily extracted and modified. Although certain sections are odd.
* The device can be updated via USB or Disc, and the same version can be reinstalled.
* The only checks on this seems to be a CRC and filesize check, hard to say exactly what part of the firmware they apply to however.

* There doesn't appear to be an debug or serial header on the motherboard.

* UDAP exists
* This could be very vulnerable, however it would take some research of both the protocol and the implementation.

* The main binary is large (24MiB).
* However it is well documented with lots of strings.
