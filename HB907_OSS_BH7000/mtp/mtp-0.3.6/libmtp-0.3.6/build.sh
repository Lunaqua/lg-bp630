export CPPFLAGS=-I`pwd`/../../../../library/mtp/mtp-0.3.6/install/include
export LDFLAGS=-L`pwd`/../../../../library/mtp/mtp-0.3.6/install/lib

if [ -z ${CROSS_COMPILE} ]; then
    export CROSS_COMPILE="armv6z-mediatek-linux-gnueabi-"
fi

CC=${CROSS_COMPILE}gcc ./configure --host=`uname -m`-linux  --build=arm-linux --target=arm-linux --prefix=`pwd`/../../../../library/mtp/mtp-0.3.6/install
make
MAKE_RET=$?
if [ $MAKE_RET -ne 0 ]; then
    echo "LIBMTP Build Fail....($MAKE_RET)"
    exit $MAKE_RET
else
    echo "LIBMTP Build OK..."
fi
make install
if [ $MAKE_RET -ne 0 ]; then
    echo "LIBMTP Install Fail....($MAKE_RET)"
    exit $MAKE_RET
else
    echo "LIBMTP Install OK..."
fi
unset CPPFLAGS
unset LDFLAGS
