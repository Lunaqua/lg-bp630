#!/bin/sh

curPath=${PWD}
if [ 2 -gt $# ]; then
   echo ""
   echo "   usage: run_XS_TestApp.sh gen <| other retailer prefix> countryID [540|1080]"
   echo ""
   exit 1

else
   storename=$1
   uppercaseStorename=`echo ${storename} | tr '[a-z]' '[A-Z]'`

   export countryID="$2"
   export regionCode="00"
   configurationFile="${curPath}/${countryID}_${regionCode}/${storename}configuration.xml"

   if [ -f "$configurationFile" ]; then
         if [ "$3" = '' ]; then
            resolution=540
         else
            resolution=$3
         fi
         export configuration="${curPath}/${countryID}_${regionCode}/${storename}configuration.xml"
         export settings="${curPath}/${countryID}_${regionCode}/${storename}settings.xml"
         export bcSettings=""
         export bcCachePath=""
         export updateLocalPath="${curPath}"
         export updateVirtualPath=""
         export loaderLocalPath="${curPath}"
         export loaderVirtualPath=""
         export cacheLocalPath="${curPath}/cache${uppercaseStorename}"
         export cacheVirtualPath=""
         export modelCode="PC"
         export firmwareVer="Ubuntu"
         export swfFile="Loader${resolution}.swf"
         export htmlFile="Loader${resolution}.html"
         ./RNO_XS_OrbitTestApp  
   else
      echo ""
      echo "   ***** Configuration file does not exist: $configurationFile"
      echo "   ***** Please check validity of retailer prefix and country ID entered! *****"
      echo ""
   fi
fi

