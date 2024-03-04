#!/bin/sh
#

echo "Enter pandora.sh"
echo "Call pandorastub" $PWD

export QT_QWS_FONTDIR=/usr/share/font
echo "pandora font path = " $QT_QWS_FONTDIR

echo "pandora for vosd"
echo "/usr/local/bin/pandora/PandoraApp -qws -display directfb:layerid=2"
/usr/local/bin/pandora/PandoraApp -qws -display directfb:layerid=2

