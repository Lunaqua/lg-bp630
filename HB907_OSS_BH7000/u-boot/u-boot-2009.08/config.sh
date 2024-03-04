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
# build uboot
U_BOOT_DIR=.

${MAKE} -C ${U_BOOT_DIR} mt8530_base_config O=../../../build/u-boot/u-boot-2009.08