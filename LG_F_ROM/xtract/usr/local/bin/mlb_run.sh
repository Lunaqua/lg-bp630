#!/bin/sh
export LD_LIBRARY_PATH=/usr/local/lib:/usr/share/flashlite/lib:/lib:/usr/lib:/mnt/rootfs_normal/lib:/usr/share/browser/lib:$LD_LIBRARY_PATH


if [ -d "/misc/data/mlb" ]
then
echo "/misc/data/mlb folder already exists"
else
echo "create the /misc/data/mlb folder"
mkdir /misc/data/mlb
fi

if [ -d "/tmp/MLB" ]
then
echo "/tmp/MLB folder already exists"
else
echo "create the /tmp/MLB folder"
mkdir /tmp/MLB
fi

echo "Start Run LG MLB Flash: /usr/local/bin/scLauncher"

/usr/local/bin/scLauncher $*