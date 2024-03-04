#! /bin/sh

if [ -z ${CROSS_COMPILE} ]; then
    echo  "cross compile not found!"
    export CROSS_COMPILE="armv6z-mediatek-linux-gnueabi-"
fi

export CC=${CROSS_COMPILE}gcc
export AR=${CROSS_COMPILE}ar
export LD=${CROSS_COMPILE}ld
export RANLIB=${CROSS_COMPILE}ranlib

./configure --prefix=$(pwd)/library/msdl \
            --host=armv6z-mediatek-linux-gnueabi 


make
make install