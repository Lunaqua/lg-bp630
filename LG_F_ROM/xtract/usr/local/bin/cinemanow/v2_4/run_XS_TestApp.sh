#!/bin/sh
export LD_LIBRARY_PATH=/usr/local/lib
if [ -d "/misc/cinemanow/khm" ]
then
echo "credential folder already exists"
else
echo "create the credential folder"
mkdir /misc/cinemanow
mkdir /misc/cinemanow/khm
fi
if [ -d "/misc/cinemanow/cpx" ]
then
echo "credential folder already exists"
else
echo "create the credential folder"
mkdir /misc/cinemanow
mkdir /misc/cinemanow/cpx
fi

if [ -d "/misc/cinemanow/cn" ]
then
echo "credential folder already exists"
else
echo "create the credential folder"
mkdir /misc/cinemanow
mkdir /misc/cinemanow/cn
fi

if [ -d "/misc/cinemanow/tt" ]
then
echo "credential folder already exists"
else
echo "create the credential folder"
mkdir /misc/cinemanow
mkdir /misc/cinemanow/tt
fi

cp -rf /usr/share/cinemanow/v2_4/KHM/* /misc/cinemanow/khm
cp -rf /usr/share/cinemanow/v2_4/CPX/* /misc/cinemanow/cpx
cp -rf /usr/share/cinemanow/v2_4/CN/* /misc/cinemanow/cn
cp -rf /usr/share/cinemanow/v2_4/TT/* /misc/cinemanow/tt

/usr/local/bin/cinemanow/v2_4/RNO_XS_OrbitTestApp $* 


