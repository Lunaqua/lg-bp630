#! /bin/sh
if [ -z ${CROSS_COMPILE} ]; then
    export CROSS_COMPILE="armv6z-mediatek-linux-gnueabi-"
fi

PKG_CONFIG_PATH=`pwd`/../../../../library/mtp/mtp-0.3.6/install/lib/pkgconfig/ PKG_CONFIG=pkg-config CC=${CROSS_COMPILE}gcc  ./configure --host=`uname -m`-linux --target=arm-linux --prefix=`pwd`/../../../../library/mtp/mtp-0.3.6/install
make
MAKE_RET=$?
if [ $MAKE_RET -ne 0 ]; then
    echo "LIBUSB Build Fail....($MAKE_RET)"
    exit $MAKE_RET
else
    echo "LIBUSB Build OK..."
fi
make install
if [ $MAKE_RET -ne 0 ]; then
    echo "LIBUSB Install Fail....($MAKE_RET)"
    exit $MAKE_RET
else
    echo "LIBUSB Install OK..."
fi

