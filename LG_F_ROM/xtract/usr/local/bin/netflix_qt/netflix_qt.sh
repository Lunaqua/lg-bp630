#!/bin/sh

#Netflix 4.0 config
#defaut source is delicated App Icon
#it will be set in setenv("NF_BUTTON_SRC") in nf_stub. 
echo "### Start Netflix from button source: "${NF_BUTTON_SRC}
echo "### Start Netflix contains title id: " ${NF_TITLE_ID}
TRACE=7
if [ "$NF_DBG_TRACE" = "" ]; then
	TRACE=7
else
	TRACE=$NF_DBG_TRACE
fi

TITLEID=""
LAUNCHPARAMS=""

if [ "$NF_TITLE_ID" = "" ]; then
	TITLEID=""
	LAUNCHPARAMS="-Q source_type=${NF_BUTTON_SRC}"
else
	TITLEID=${NF_TITLE_ID}
	LAUNCHPARAMS="-Q ${TITLEID}"
fi

echo "### Start Netflix with Trace Level: "${TRACE}

echo "### Start Netflix with params: "${LAUNCHPARAMS}

export LD_LIBRARY_PATH=usr/local/freetype_origin:/usr/lib:/usr/local/lib:${LD_LIBRARY_PATH}
echo "### LD_LIBRARY_PATH -->"${LD_LIBRARY_PATH}
export NF_IDFILE=/misc/nf/sysdata/SDK.bin
#read-only resource data(css,html, ca files)
export NF_DATA_DIR=/usr/share/netflix/40/data
export QWS_DISPLAY=directfb
export NF_DISK_CACHE_PATH=/tmp/nf/gibbon
export NF_CACHE_PATH=/misc/nf/cache
export QT_PLUGIN_PATH=/plugins

echo "Call nfq_stub" $PWD
#/usr/local/bin/netflix_qt/netflix -Q source_type=${NF_BUTTON_SRC} -jz -T ${TRACE}
/usr/local/bin/netflix_qt/netflix ${LAUNCHPARAMS}
echo "******netflix qt process exit!******"
