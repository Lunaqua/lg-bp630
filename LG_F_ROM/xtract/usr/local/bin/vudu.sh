#!/bin/sh
#

echo "Enter vudu.sh"

#export VLOGTOCONSOLE=1
#export VLOGLEVEL=info
#LD_PRELOAD=/lib/libSegFault.so ./usr/local/bin/vudu_client
./usr/local/bin/vudu_client $1 $2 $3 $4 $5 $6 $7

