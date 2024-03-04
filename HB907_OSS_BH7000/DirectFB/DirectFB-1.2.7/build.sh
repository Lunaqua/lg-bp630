#!/bin/sh
#Script to make DirectFB by Yan Wang in MediaTek

if [ true ]; then

	export DEST_DIR=$(pwd)/../../../library/DirectFB/DirectFB-1.2.7

	touch install_flag

	make DESTDIR=${DEST_DIR} install

	MAKE_RET=$?
	if [ $MAKE_RET -ne 0 ]; then
		echo "DirectFB Build Fail...($MAKE_RET)...Try Again..."
#		exit $MAKE_RET

		export LDFLAGS="-L$(pwd)/../../../library/zlib/zlib-1.2.3/usr/lib -L$(pwd)/../../../library/freetype/freetype-2.3.9/usr/lib -L$(pwd)/../../../library/libpng/libpng-1.2.35/usr/lib -L$(pwd)/../../../library/jpeg/jpeg-7/usr/lib $LDFLAGS"
		export CFLAGS="$CFLAGS -DCONFIG_CHIP_VER_CURR=8550 -DCONFIG_CHIP_VER_MT8530=8530 -DCONFIG_CHIP_VER_MT8550=8550 -DCONFIG_CHIP_VER_MT8555=8555 -I$(pwd)/../../../library/linux-fusion/linux-fusion-8.2.0/include -I$(pwd)/../../../library/zlib/zlib-1.2.3/usr/include -I$(pwd)/../../../library/freetype/freetype-2.3.9/usr/include -I$(pwd)/../../../library/libpng/libpng-1.2.35/usr/include -I$(pwd)/../../../library/jpeg/jpeg-7/usr/include"

		make clean
		make distclean

		if test -z "${CROSS_COMPILE}"; then
			CROSS_COMPILE=armv6z-mediatek-linux-gnueabi-
		fi

		./configure \
		--prefix=/usr \
		--exec-prefix=/ \
		--host=armv6z-mediatek-linux-gnueabi \
		--enable-debug=no \
		--enable-multi \
		--enable-x11=no \
		--enable-osx=no \
		--enable-voodoo=no \
		--enable-mmx=no \
		--enable-sse=no \
		--enable-devmem=no \
		--enable-fbdev=no \
		--enable-mtk \
		--enable-jpeg \
		--enable-png \
		--enable-gif \
		--enable-freetype \
		--enable-video4linux=no \
		--with-gfxdrivers=mt85 \
		--without-tools \
		--with-inputdrivers=lirc,ps2mouse,keyboard \
		--without-smooth-scaling

#		--with-inputdrivers=lirc,linuxinput,ps2mouse,keyboard,serialmouse \

		make DESTDIR=${DEST_DIR} install

		MAKE_RET=$?
		if [ $MAKE_RET -ne 0 ]; then
		   echo -e "\033[40;31mDirectFB Build Fail......($MAKE_RET)\033[0m"
		   exit $MAKE_RET
		else
		    echo -e "\033[40;32mDirectFB Build Success\033[0m"
		fi

	else
	    echo -e "\033[40;32mDirectFB Build Success\033[0m"
	fi

	exit 0

else
   echo -e "\033[40;31mDirectFB is not supported!\033[0m"
   exit 0
fi
