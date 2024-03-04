#!/bin/sh
#Script to make busybox by Zhifeng in MediaTek

if [ -z ${MAKE}];then
    MAKE="make"
fi

${MAKE}
if [ $? -ne 0 ]; then
  echo "make fail!"
  exit 1
fi

${MAKE} install 
if [ $? -ne 0 ]; then
  echo "make install fail!"
  exit 1
fi

#if [ -e ./rootfs_squash.img ]; then
#   rm -f ./rootfs_squash.img
#   if [ $? -ne 0 ]; then
#     echo "rootfs_squash.img exist but can not be removed!"
#     exit 1
#   fi
#   echo "rootfs_squash.img exist, removed"
#fi

#../../../../tool/mksquashfs ./rootfs/* ./rootfs_squash.img -all-root
#if [ $? -ne 0 ]; then
#  echo "mksquashfs fail!"
#  exit 1
#fi

#cp -af ./rootfs_squash.img ../../../library/busybox/busybox-1.17.1/rootfs_squash.img
#if [ $? -ne 0 ]; then
#  echo "copy file fail!"
#  exit 1
#fi

if [ -e ./rootfs.tar.gz ]; then
   rm -f ./rootfs.tar.gz
   if [ $? -ne 0 ]; then
     echo "rootfs.tar.gz exist but can not be removed!"
     exit 1
   fi
   echo "rootfs.tar.gz exist, removed"
fi

cd rootfs
tar -czf ../rootfs.tar.gz *
cd ..
if [ $? -ne 0 ]; then
  echo "tar fail!"
  exit 1
fi

cp -af ./rootfs.tar.gz ../../../library/busybox/busybox-1.17.1/rootfs.tar.gz
if [ $? -ne 0 ]; then
  echo "copy file fail!"
  exit 1
fi

cp -af ./busybox_unstripped ../../../library/busybox/busybox-1.17.1/busybox_unstripped
if [ $? -ne 0 ]; then
  echo "copy file fail!"
  exit 1
fi

cp -af ./busybox_unstripped.map ../../../library/busybox/busybox-1.17.1/busybox_unstripped.map
if [ $? -ne 0 ]; then
  echo "copy file fail!"
  exit 1
fi

echo "busybox build pass!"
exit 0  
