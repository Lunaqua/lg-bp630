#
# setup environment
#

if [ -z ${MAKE} ];then
  ARMQ=`whereis armq`
  if [ "$ARMQ" = "armq:" ]; then
    MAKE="make -j 4"
  else
    MAKE="armq make"
  fi
fi

if [ -z ${CROSS_COMPILE} ]; then
    export CROSS_COMPILE="armv6z-mediatek-linux-gnueabi-"
fi

if [ -e file_link.txt ];   then
l=`awk 'END{print NR}' file_link.txt`
for ((i=1; i<=$l; i++)); do
rm -f $(awk -v nr=$i 'NR==nr {print $3 }' file_link.txt)
ln -s $(awk -v nr=$i 'NR==nr {print $1 " " $3 }' file_link.txt)
done
fi

# build uboot
U_BOOT_DIR=.

#${MAKE} -C ${U_BOOT_DIR} mt8530_base_config
${MAKE} -C ${U_BOOT_DIR} O=../../../build/u-boot/u-boot-2009.08
MAKE_RET=$?
if [ $MAKE_RET -ne 0 ]; then

    ${MAKE} -C ${U_BOOT_DIR} mt8530_base_config O=../../../build/u-boot/u-boot-2009.08
    ${MAKE} -C ${U_BOOT_DIR} O=../../../build/u-boot/u-boot-2009.08
    MAKE_RET=$?

    if [ $MAKE_RET -ne 0 ]; then
        echo "U-Boot Build Fail....($MAKE_RET)"
        exit $MAKE_RET
    else
        echo "U-Boot Build OK..."
    fi
else
    echo "U-Boot Build OK..."
fi

#cp -f ${U_BOOT_DIR}/u-boot.bin ../../../../res/u-boot-8530.bin

cp -f ../../../build/u-boot/u-boot-2009.08/u-boot.bin ../../../../res/u-boot-8530.bin


#cp -f ${U_BOOT_DIR}/tools/mkimage  ../../../../tool/mkimage

exit 0
