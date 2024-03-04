#!/bin/sh
#

echo "Stagecraft Copy crt Files"

cd /var/local/misc/stagecraft-data
mkdir ssl
cd ssl
mkdir certs
cd certs
cp -f /usr/share/fl/*.* .
cd /
