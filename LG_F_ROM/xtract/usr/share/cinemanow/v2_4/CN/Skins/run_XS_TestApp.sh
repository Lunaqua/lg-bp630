#!/bin/sh

curPath=${PWD}
if [ 0 = $# ]; then
   echo "example: run_XS_TestApp.sh cn|gen|cpx [540|1080]"
   exit 1

############################BBy CN store###################################
elif [ "$1" = 'cn' ]; then
   echo "*****This is the Best Buy UI*****"
   storename=$1
   if [ "$2" = '' ]; then
      resolution=540
   else
      resolution=$2
   fi
   export countryID="1"
   export regionCode="00"
   export configuration="${curPath}/${countryID}_${regionCode}/cnconfiguration.xml"
   export settings="${curPath}/${countryID}_${regionCode}/cnsettings.xml"
   export bcSettings="${curPath}/cnsettings.xml"
   export bcCachePath="${curPath}/cacheCNV2"
   export updateLocalPath="${curPath}"
   export updateVirtualPath=""
   export loaderLocalPath="${curPath}"
   export loaderVirtualPath=""
   export cacheLocalPath="${curPath}/cacheCNV2"
   export cacheVirtualPath=""
   export modelCode="PC"
   export firmwareVer="Ubuntu"
   export swfFile="Loader${resolution}.swf"
   export htmlFile="Loader${resolution}.html"

############################Generic store###################################
elif [ "$1" = 'gen' ]; then
   echo "*****This is the Generic UI*****"
   storename=$1
   if [ "$2" = '' ]; then
      resolution=540
   else
      resolution=$2
   fi
   export countryID="1"
   export regionCode="00"
   export configuration="${curPath}/${countryID}_${regionCode}/genconfiguration.xml"
   export settings="${curPath}/${countryID}_${regionCode}/gensettings.xml"
   export bcSettings=""
   export bcCachePath=""
   export updateLocalPath="${curPath}"
   export updateVirtualPath=""
   export loaderLocalPath="${curPath}"
   export loaderVirtualPath=""
   export cacheLocalPath="${curPath}/cacheGENV2"
   export cacheVirtualPath=""
   export modelCode="PC"
   export firmwareVer="Ubuntu"
   export swfFile="Loader${resolution}.swf"
   export htmlFile="Loader${resolution}.html"

############################Cineplex store###################################
elif [ "$1" = 'cpx' ]; then
   echo "*****This is the Cineplex UI*****"
   storename=$1
   if [ "$2" = '' ]; then
      resolution=540
   else
      resolution=$2
   fi
   export countryID="41"
   export regionCode="00"
   export configuration="${curPath}/${countryID}_${regionCode}/cpxconfiguration.xml"
   export settings="${curPath}/${countryID}_${regionCode}/cpxsettings.xml"
   export bcSettings=""
   export bcCachePath=""
   export updateLocalPath="${curPath}"
   export updateVirtualPath=""
   export loaderLocalPath="${curPath}"
   export loaderVirtualPath=""
   export cacheLocalPath="${curPath}/cacheCPXV2"
   export cacheVirtualPath=""
   export modelCode="PC"
   export firmwareVer="Ubuntu"
   export swfFile="Loader${resolution}.swf"
   export htmlFile="Loader${resolution}.html"
fi

./RNO_XS_OrbitTestApp  

