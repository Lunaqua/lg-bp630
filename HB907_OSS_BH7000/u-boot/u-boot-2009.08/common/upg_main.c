//#include "upg_typedef.h"
#include <asm/arch/x_typedef.h>
#include "upg_main.h"
#include "upg_flash.h"
#include "upg_usb.h"
#include "upg_util.h"
#include "nand.h"
#include <asm/arch/mt85xx_part_tbl.h>
#include <upg_config.h>
#include <asm/arch/mt85xx_upg_status.h>
#include <linux/mtd/mt85xx_part_oper.h>

PARTITION_INFO_ENTRY 	partInfoTable[MAX_PARTITION_INFO_ENTRY_NUM];
PARTITION_INFO_ENTRY 	partInfoTableFlash[MAX_PARTITION_INFO_ENTRY_NUM]; //PIT has stored to flash

BINARY_INFO_PART_ENTRY		binaryInfoTable[MAX_PARTITION_INFO_ENTRY_NUM];

BACKUP_INFO_ENTRY backupInfoTable[64];
UINT32 u4BackupInfTableItemNum = 0;

#if UPG_SUPPORT_NEW_PIT_FORMAT
UINT32 _u4PitTableVersion = 0;
#endif

static PARTITION_INFO_ENTRY *upg_status_part_Info = NULL;
static UPG_STATUS_T pr_upg_status;
extern UPG_STATUS_T r_upg_status;

#if UPG_SUPPORT_UBI
//for stor PIT addr and size
UINT32 u4PITStartAddr = 0;
UINT32 u4PITSize = 0;
#endif
#if UPG_SUPPORT_NEW_FE_UPG
CHAR* szCurrentWorkUsbPath;
#endif

BOOL _fgNewPitIndexFormat = FALSE;
UINT32 u4BITHeadSigAddr = 0;
UINT32 u4BITTailSigAddr = 0;

volatile UINT32 *u4pImgStartAddr = NULL; //global pointer
volatile UINT32 u4ImgSize = 0;
volatile UINT8 *u1pImgBackUpInfoStartAddr = NULL; //global pointer


UINT32 u4PitFromBeItemNum = 0;

INT32 i_set_upg_status_by_upg(UINT32 upg_status);

static void vFillBackupTable()
{   	
   strncpy(backupInfoTable[0].szName, "mac_addr_1", 16);
   strncpy(backupInfoTable[1].szName, "mac_addr_2", 16);
   strncpy(backupInfoTable[2].szName, "key_block_1", 16);
   strncpy(backupInfoTable[3].szName, "key_block_2", 16);
   #if (CFG_BRANCH_NAME == 1)
   strncpy(backupInfoTable[4].szName, "fe_test_data", 16);
   strncpy(backupInfoTable[5].szName, "fe_parameters", 16);
   strncpy(backupInfoTable[6].szName, "fe_power_curve", 16);
   u4BackupInfTableItemNum = 7;
   #else
   strncpy(backupInfoTable[4].szName, "fe_bin_1", 16);
   strncpy(backupInfoTable[5].szName, "fe_bin_2", 16);
   strncpy(backupInfoTable[6].szName, "fe_test_data", 16);
   strncpy(backupInfoTable[7].szName, "fe_parameters", 16);
   strncpy(backupInfoTable[8].szName, "fe_power_curve", 16);
   u4BackupInfTableItemNum = 9;
   #endif
}


static void dumpPIT() {
	UINT32 i = 0;
	UPG_LOG(UPG_LOG_DEBUG, "================PIT dump==================\n");
	for (i = 0; (i < MAX_PARTITION_INFO_ENTRY_NUM) && 
			(partInfoTable[i].u4PartID != INVALID_PART_ID); i++) {
		UPG_LOG(UPG_LOG_DEBUG, "partInfoTable[%2d].u4PartID  			= %d\n", i, partInfoTable[i].u4PartID);	
		UPG_LOG(UPG_LOG_DEBUG, "partInfoTable[%2d].szName    			= %s\n", i, partInfoTable[i].szName);
		UPG_LOG(UPG_LOG_DEBUG, "partInfoTable[%2d].u4OffsetFromFlash	= 0x%X\n", i, partInfoTable[i].u4OffsetFromFlash);
		UPG_LOG(UPG_LOG_DEBUG, "partInfoTable[%2d].u4PartSize    		= 0x%X\n", i, partInfoTable[i].u4PartSize);
		UPG_LOG(UPG_LOG_DEBUG, "partInfoTable[%2d].u4PartInfo     		= 0x%X\n\n", i, partInfoTable[i].u4PartInfo);
	}
}


static void dumpBIT() {
	UINT32 i = 0;
	UPG_LOG(UPG_LOG_DEBUG, "================BIT dump==================\n");
	for (i = 0; (i < MAX_PARTITION_INFO_ENTRY_NUM) && 
			(binaryInfoTable[i].u4PartID != INVALID_PART_ID); i++) {
		UPG_LOG(UPG_LOG_DEBUG, "binaryInfoTable[%d].u4PartID  		= %d\n", i, binaryInfoTable[i].u4PartID);
		UPG_LOG(UPG_LOG_DEBUG, "binaryInfoTable[%d].u4HasMoreBin  	= %d\n", i, binaryInfoTable[i].u4HasMoreBin);
		int j = 0;
		for (j = 0; (j < MAX_BINARY_INFO_PART_BIN_ENTRY_NUM) 
			&& (binaryInfoTable[i].partBinEntries[j].u4PartID != INVALID_PART_ID); j++) {
			UPG_LOG(UPG_LOG_DEBUG, "\tbinaryInfoTable[%d].partBinEntries[%d].u4PartID  = %d\n", 
				i, j, binaryInfoTable[i].partBinEntries[j].u4PartID);	
			UPG_LOG(UPG_LOG_DEBUG, "\tbinaryInfoTable[%d].partBinEntries[%d].u4OffsetFromPart  = 0x%X\n", 
				i, j, binaryInfoTable[i].partBinEntries[j].u4OffsetFromPart);	
			UPG_LOG(UPG_LOG_DEBUG, "\tbinaryInfoTable[%d].partBinEntries[%d].u4BinSize  = 0x%X\n", 
				i, j, binaryInfoTable[i].partBinEntries[j].u4BinSize);			
			UPG_LOG(UPG_LOG_DEBUG, "\tbinaryInfoTable[%d].partBinEntries[%d].u4OffsetFromFinalImg  = 0x%X\n\n", 
				i, j, binaryInfoTable[i].partBinEntries[j].u4OffsetFromFinalImg);
		}
	}
}

void initPIT(void) {
	INT32 i = 0;
	for (i = 0; i < MAX_PARTITION_INFO_ENTRY_NUM; i++) {
		partInfoTable[i].u4PartID = INVALID_PART_ID;
	}
}

void initPITFlash(void) {
	INT32 i = 0;
	for (i = 0; i < MAX_PARTITION_INFO_ENTRY_NUM; i++) {
		partInfoTableFlash[i].u4PartID = INVALID_PART_ID;
	}
}


void initBIT(void) {
	INT32 i = 0;
	for (i = 0; i < MAX_PARTITION_INFO_ENTRY_NUM; i++) {
		binaryInfoTable[i].u4PartID = INVALID_PART_ID;
		binaryInfoTable[i].u4HasMoreBin = 0;
		int j = 0;
		for (j = 0; j < MAX_BINARY_INFO_PART_BIN_ENTRY_NUM; j++) {
			binaryInfoTable[i].partBinEntries[j].u4PartID = INVALID_PART_ID;
		}
	}
}

UINT32 getPartOffsetFromFlash(UINT32 u4PartID) {
	UINT32 u4PartOffsetFromFlash = 0;
	INT32 i = 0;
	for (i = 0; i < MAX_PARTITION_INFO_ENTRY_NUM; i++) {
		if (partInfoTable[i].u4PartID == u4PartID) {
			u4PartOffsetFromFlash = partInfoTable[i].u4OffsetFromFlash;
			break;
		}
	}

	return u4PartOffsetFromFlash;
}

UINT32 getPartSize(UINT32 u4PartID) {
	UINT32 u4PartSize = 0;
	INT32 i = 0;
	for (i = 0; i < MAX_PARTITION_INFO_ENTRY_NUM; i++) {
		if (partInfoTable[i].u4PartID == u4PartID) {
			u4PartSize = partInfoTable[i].u4PartSize;
			break;
		}
	}

	return u4PartSize;
}

CHAR * getPartName(UINT32 u4PartID) {
	CHAR * szName;
	INT32 i = 0;
	for (i = 0; i < MAX_PARTITION_INFO_ENTRY_NUM; i++) {
		if (partInfoTable[i].u4PartID == u4PartID) {
			szName = partInfoTable[i].szName;
			break;
		}
	}

	return szName;
}


UINT32 getPartInfo(UINT32 u4PartID) {
	UINT32 u4PartInfo = 0;
	INT32 i = 0;
	for (i = 0; i < MAX_PARTITION_INFO_ENTRY_NUM; i++) {
		if (partInfoTable[i].u4PartID == u4PartID) {
			u4PartInfo = partInfoTable[i].u4PartInfo;
			break;
		}
	}

	return u4PartInfo;
}


INT32 isValidPartIDOfBIT(INT32 i4PartID)
{
	INT32 i = 0;
	for (i = 0; (i < MAX_PARTITION_INFO_ENTRY_NUM) && 
			(binaryInfoTable[i].u4PartID != INVALID_PART_ID); i++) {
		if (i4PartID == binaryInfoTable[i].u4PartID) {
			return 0;
		}
	}

	return -1;
}

#if UPG_SUPPORT_UBI

INT32 i4CreateVolume(INT32 i4UbiMtdId, INT32 i4PitId) {
    INT32 i = 0;
    INT32 i4Ret = -1;
    char _tmp_buf[1024];
    CHAR *		szPartName = getPartName(partInfoTable[i4PitId].u4PartID );
    CHAR * _VolType;     
    
    i4Ret = sprintf(_tmp_buf, "ubi part %s", szPartName);	
    if (-1 == i4Ret) {
       UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]upg_main.c creat szMtdPartsCmd failed\n");	
       return i4Ret;
    }
    #if 0
    i4Ret = run_command(_tmp_buf, 0);
    UPG_LOG(UPG_LOG_DEBUG, "[UPG]run_command:ubi part ubi, cmd = %s  ret:%d\n", _tmp_buf, i4Ret);
    if (-1 == i4Ret) 
    #endif
    {
       //UPG_LOG(UPG_LOG_ERROR, "[UPG_DEBUG]call cmd: %s failed, need erase nand flash \n", _tmp_buf);   
	   UPG_LOG(UPG_LOG_DEBUG, "[UPG_DEBUG]eraseDataFromNand , partition id = %d, u4PartSize = %d, u4OffsetFromFlash = %d \n", partInfoTable[i4PitId].u4PartID, partInfoTable[i4PitId].u4PartSize, partInfoTable[i4PitId].u4OffsetFromFlash);	 
       i4Ret = eraseDataFromNand(partInfoTable[i4PitId].u4OffsetFromFlash, partInfoTable[i4PitId].u4PartSize, partInfoTable[i4PitId].u4PartID);
       if(-1 == i4Ret)
       {
          UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]eraseDataFromNand failed, partition id = %d, u4PartSize = %d, u4OffsetFromFlash = %d \n", partInfoTable[i4PitId].u4PartID, partInfoTable[i4PitId].u4PartSize, partInfoTable[i4PitId].u4OffsetFromFlash);	
          return i4Ret;
       }
	   
	   UPG_LOG(UPG_LOG_DEBUG, "[UPG]ubi part ubi run_command:%s 	\n",_tmp_buf); 
       i4Ret = run_command(_tmp_buf, 0);
       if(-1 == i4Ret)
       {
          UPG_LOG(UPG_LOG_ERROR, "[UPG]ubi part ubi run_command:%s fail	ret:%d\n",_tmp_buf, i4Ret);
          return i4Ret;
       }    
    }
              
    for (i = 0; (i < MAX_PARTITION_INFO_ENTRY_NUM) && 
        (partInfoTable[i].u4PartID != INVALID_PART_ID); i++) {
       if((ISUBIVOLUME(partInfoTable[i].u4PartInfo) == 4)&&(GETMTDID(partInfoTable[i].u4PartInfo) == i4UbiMtdId))
       {
          szPartName = getPartName(partInfoTable[i].u4PartID );  
          if(GETVOLTYPE(partInfoTable[i].u4PartInfo) == 2)
            _VolType = "s";
          else
            _VolType = "d";
          //create volume
          i4Ret = sprintf(_tmp_buf, "ubi create %s 0x%x %s", getPartName(partInfoTable[i].u4PartID),partInfoTable[i].u4PartSize, _VolType);		  
		  UPG_LOG(UPG_LOG_DEBUG, "[UPG_DEBUG]upg_main.c create volume %s \n",_tmp_buf);	 
          if (-1 == i4Ret) {
             UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]upg_main.c create volume name failed\n");	
             return i4Ret;
          }	  
          i4Ret = run_command(_tmp_buf, 0);	  
          UPG_LOG(UPG_LOG_DEBUG, "[UPG_DEBUG]run_command:create volume cmd = %s, ret:%d\n", _tmp_buf, i4Ret);
          if(-1 == i4Ret)
          {
             UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]run_command:ubi create volume failed,command = %s,	ret:%d\n", _tmp_buf, i4Ret);
             return i4Ret;
          }    
       
       }
    }

    return i4Ret;

    
}

INT32 i4SetUBIMountEnv(PARTITION_INFO_ENTRY partInfoTable[]) {
    INT32 i = 0;	
    INT32 i4Ret = 0;
    char _bootargs_buf[1024] = "";
    char _tmp_buf[1024] = "";
    /* mtdparts */
    strcat(_bootargs_buf, "mtdparts=mt85xx_nand:");
    for (i = 0; (i < MAX_PARTITION_INFO_ENTRY_NUM) && 
                (partInfoTable[i].u4PartID != INVALID_PART_ID); i++) {
          if( ISUBIVOLUME(partInfoTable[i].u4PartInfo) != 4 )
          {
            #if UPG_SUPPORT_ADAPTIVE_PIT
            i4Ret = sprintf(_tmp_buf, "%dK(%s),", partInfoTable[i].u4PartSize >> 10,partInfoTable[i].szName);	
            #else
            i4Ret = sprintf(_tmp_buf, "%dM(%s),", partInfoTable[i].u4PartSize >> 20,partInfoTable[i].szName);
            #endif	
            if (-1 == i4Ret) {
              UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]upg_main.c creat szMtdPartsCmd failed\n");	
              return i4Ret;
            }
            strcat(_bootargs_buf, _tmp_buf);	
          }
    }
	//UPG_LOG(UPG_LOG_DEBUG, "[UPG_DEBUG]upg_main.c vSetUBIMountEnv set mtdparts = %s \n", _bootargs_buf);	 
    i4Ret = setenv("mtdparts", _bootargs_buf);
    if (-1 == i4Ret) {
       UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]upg_main.c vSetUBIMountEnv set mtdparts failed, mtdparts = %s \n", _bootargs_buf);	
       return i4Ret;
    }
    i4Ret =  setenv("mtdids", "nand0=mt85xx_nand");
    if (-1 == i4Ret) {
       UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]upg_main.c vSetUBIMountEnv set mtdids failed\n");	
       return i4Ret;
    }
    UPG_LOG(UPG_LOG_DEBUG, "[UPG_DEBUG]upg_main.c vSetUBIMountEnv set mtdparts OK\n");

    return i4Ret;  
}

#endif

/*-----------------------------------------------------------------------------
 * Name: CheckXorValueFromFinalBinaryImage
 *
 * Description: Check the xor value from final binary image
 *
 * Inputs:    	u4pImgStartAddr	Image start address from memory
 *				u4ImgSize		Image size loaded to memory
 * Outputs: -
 *
 * Returns: 	UPGR_OK			Check  success
 *				UPGR_FAIL		Find PIT failed
 ----------------------------------------------------------------------------*/
INT32 CheckXorValueFromFinalBinaryImage(UINT8 *u4pImgStartAddr, UINT32 u4ImgSize) 
{
      UINT32 u4CalXorLen = 0;
      INT32 i = 0;
      UINT32 u4XorChecksum = 0;
      UINT32 u4SearchSAddr = 0;
      BOOL fgFindXorSignal = FALSE;
      BOOL fgXorSignalMatch = FALSE;
      UINT32 u4SignalOff;
      
      u4SearchSAddr = (u4ImgSize/16 - 2) * 16;
      for (i = u4SearchSAddr; i < u4ImgSize; i += 4) 
      {
         if( (*(UINT32 *) (u4pImgStartAddr + i) == XORHEADSIG)&&
             (*(UINT32 *) (u4pImgStartAddr + i + 8) == XORTAILSIG) )
         {
            fgFindXorSignal = TRUE;
            u4SignalOff = i;
            UPG_LOG(UPG_LOG_ERROR, "[UPG]find xor signal, signal offset = 0x%X\n", i); 
            break;
         }
      }
      if(fgFindXorSignal)
      {
         u4XorChecksum = *((UINT32*)u4pImgStartAddr);	   
         for (i = 1; i < u4SignalOff/4; i++) 
         {
            u4XorChecksum = u4XorChecksum ^ ((UINT32)(*((UINT32*)(u4pImgStartAddr + i*4))));
            //Printf("[appendXOR] u4XorChecksum = 0x%08x !! \n", u4XorChecksum);
         }  
         UPG_LOG(UPG_LOG_DEBUG, "[appendXOR] last u4XorChecksum = 0x%08x !! \n", u4XorChecksum);
         if( u4XorChecksum == *(UINT32 *) (u4pImgStartAddr + u4SignalOff + 4))
         {
            fgXorSignalMatch = TRUE;
         }
      }
      if(fgFindXorSignal && (!fgXorSignalMatch))
      {
         UPG_LOG(UPG_LOG_ERROR, "[UPG] xor checksume unmatch, xor checksume cal by upg = 0x%X, xor checksume from be = 0x%X\n", 
                                u4XorChecksum, *(UINT32 *) (u4pImgStartAddr + u4SignalOff + 4)); 
         return UPGR_FAIL;
      }
      else
      {
         return UPGR_OK;
      }

}
/*-----------------------------------------------------------------------------
 * Name: checkRegionFromFinalBinaryImage
 *
 * Description: check region value from final binary image
 *
 * Inputs:    	u4pImgStartAddr	Image start address from memory
 *				u4ImgSize		Image size loaded to memory
 * Outputs: -
 *
 * Returns: 	UPGR_OK			region value match
 *				UPGR_FAIL		region value unmatch
 ----------------------------------------------------------------------------*/

INT32 checkRegionFromFinalBinaryImage(UINT8 *u4pImgStartAddr, UINT32 u4ImgSize) {
UINT32 u4CheckDataLen = 1024*1024;//just check last 1m data if include region value
BOOL fgFindRegion = FALSE;
BOOL fgFindRegionValue = FALSE;

UINT32 i,j;
u4pImgStartAddr += u4ImgSize - u4CheckDataLen;
for (i = 0; i < u4CheckDataLen; i += 4) 
{
    if ( (*(UINT32 *) (u4pImgStartAddr + i) == REGIONHEADSIG) &&
         (*(UINT32 *) (u4pImgStartAddr + i + 4) == REGIONHEADSIG) &&
         (*(UINT32 *) (u4pImgStartAddr + i + 8) == REGIONHEADSIG) &&
         (*(UINT32 *) (u4pImgStartAddr + i + 12) == REGIONHEADSIG) ) 
         {			
              UPG_LOG(UPG_LOG_DEBUG, "[UPG]get region value head addr:0x%X\n", (u4pImgStartAddr + i)); 
              u4pImgStartAddr = u4pImgStartAddr + i;
              fgFindRegion = TRUE;
              break;
   }
}
if(!fgFindRegion)
{
	UPG_LOG(UPG_LOG_DEBUG, "[UPG]Cannot find region area in be image! \n"); 
	return UPGR_OK;
}
 u4pImgStartAddr = strstr(u4pImgStartAddr, PANA_REGION_NAME);
 if(u4pImgStartAddr == NULL)
 { 	
	UPG_LOG(UPG_LOG_DEBUG, "[UPG]Cannot find region name in region area\n"); 
	return UPGR_OK;
 }
 u4pImgStartAddr += 11;
 for (i = 0; i < 32; i++ ) 
 {
   if(*(u4pImgStartAddr+i) == 0x5F)
   {
  	 for(j = 0; j < 10; j++)
  	  {
  	  	if(*(u4pImgStartAddr+i+j) == 0x27)
  	  	{
  	  	  	u4pImgStartAddr += i + 1;
			fgFindRegionValue = TRUE;
			UPG_LOG(UPG_LOG_DEBUG, "[UPG] find region value in region area,add = 0x%08x,region value len = %d\n",u4pImgStartAddr, j -1); 
			break;
  	  	}
  	  }
    }
 }
 if(!fgFindRegionValue)
 {
  	UPG_LOG(UPG_LOG_DEBUG, "[UPG]Cannot find region value in region area! \n"); 
	return UPGR_FAIL;
 }
    if(memcmp(u4pImgStartAddr,str(CONFIG_CURRENT_REGION_CODE),j - 1 ) != 0)
 {
   	UPG_LOG(UPG_LOG_DEBUG, "[UPG]region value in be unmatch with u-boot value! \n"); 
	return UPGR_FAIL;
 }

 return UPGR_OK;
}

/*-----------------------------------------------------------------------------
 * Name: findPITFromFinalBinaryImage
 *
 * Description: Find partition info table from final binary image
 *
 * Inputs:    	u4pImgStartAddr	Image start address from memory
 *				u4ImgSize		Image size loaded to memory
 * Outputs: -
 *
 * Returns: 	UPGR_OK			Find PIT success
 *				UPGR_FAIL		Find PIT failed
 ----------------------------------------------------------------------------*/

INT32 findPITFromFinalBinaryImage(UINT8 *u4pImgStartAddr, UINT32 u4ImgSize) {
     initPIT();
     INT32 i = 0;
     UINT32 u4PITHeadSigAddr = 0;
     UINT32 u4PITTailSigAddr = 0;
     BOOL fgFindPITFromBE = FALSE;
     #if UPG_SUPPORT_NEW_PIT_FORMAT
     UINT32 u4PitHeaderLen = 0;
     UINT32 u4PitItemLen = 0;
     UINT32 u4PitItemNum = 0;
     #endif
     UINT32 u4PitIdxItemLen = 16;

#if !UPG_SUPPORT_UBI
	for (i = 0; i < u4ImgSize; i += PIT_SEARCH_STEP) {
		if ( (*(UINT32 *) (u4pImgStartAddr + i) == PITHEADSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 4) == PITHEADSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 8) == PITHEADSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 12) == PITHEADSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 16) == PITHEADSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 20) == PITHEADSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 24) == PITHEADSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 28) == PITHEADSIG) ) {			
			UPG_LOG(UPG_LOG_DEBUG, "[UPG]get pit head addr:0x%X\n", (u4pImgStartAddr + i)); 
			u4PITHeadSigAddr = u4pImgStartAddr + i;
			break;
		}
	}


	for (i = 0; i < u4ImgSize; i += PIT_SEARCH_STEP) {
		if ( (*(UINT32 *) (u4pImgStartAddr + i) == PITTAILSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 4) == PITTAILSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 8) == PITTAILSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 12) == PITTAILSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 16) == PITTAILSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 20) == PITTAILSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 24) == PITTAILSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 28) == PITTAILSIG) ) {		
			UPG_LOG(UPG_LOG_DEBUG, "[UPG]get pit tail addr:0x%X\n", (u4pImgStartAddr + i));	
			u4PITTailSigAddr = u4pImgStartAddr + i;
			break;
		}
	}
	UPG_LOG(UPG_LOG_DEBUG, "[UPG]findPITFromFinalBinaryImage u4PITHeadSigAddr=0x%X u4PITTailSigAddr=0x%X\n",
		u4PITHeadSigAddr, u4PITTailSigAddr);

	if (u4PITHeadSigAddr == NULL || u4PITTailSigAddr == NULL) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]findPITFromFinalBinaryImage not found PIT head and tail sig\n");
		return UPGR_FAIL;
	}
#endif
#if UPG_SUPPORT_UBI
	for (i = 0; i < u4ImgSize; i += PIT_SEARCH_STEP) {
		if ( (*(UINT32 *) (u4pImgStartAddr + i) == PITINDEXHEADSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 4) == PITINDEXHEADSIG)  
			) {	

                 if(*(UINT32 *) (u4pImgStartAddr + i + 8) == 1)
                 {
                    _fgNewPitIndexFormat = TRUE;
                 }
                 else if((*(UINT32 *) (u4pImgStartAddr + i + 8) == PITINDEXHEADSIG) &&
                         (*(UINT32 *) (u4pImgStartAddr + i + 12) == PITINDEXHEADSIG))
                 {
                    _fgNewPitIndexFormat = FALSE;
                 }
                 else
                 {
                    continue;
                 }
                 UPG_LOG(UPG_LOG_DEBUG, "[UPG]get pit head addr:0x%X\n", (u4pImgStartAddr + i)); 
                 u4PITHeadSigAddr = u4pImgStartAddr + i;
                 break;
		}
	}


	for (i = 0; i < u4ImgSize; i += PIT_SEARCH_STEP) {
		if ( (*(UINT32 *) (u4pImgStartAddr + i) == PITINDEXTAILSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 4) == PITINDEXTAILSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 8) == PITINDEXTAILSIG) &&
			(*(UINT32 *) (u4pImgStartAddr + i + 12) == PITINDEXTAILSIG) 
			) {		
			UPG_LOG(UPG_LOG_DEBUG, "[UPG]get pit tail addr:0x%X\n", (u4pImgStartAddr + i));	
			u4PITTailSigAddr = u4pImgStartAddr + i;
			break;
		}
	}
	UPG_LOG(UPG_LOG_DEBUG, "[UPG]findPITFromFinalBinaryImage u4PITHeadSigAddr=0x%X u4PITTailSigAddr=0x%X\n",
		u4PITHeadSigAddr, u4PITTailSigAddr);

	if (u4PITHeadSigAddr == NULL || u4PITTailSigAddr == NULL) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]findPITFromFinalBinaryImage not found PIT head and tail sig\n");
		return UPGR_FAIL;
	}
    //FOR PITIndex 
    u4PITHeadSigAddr += 16;
    if(_fgNewPitIndexFormat)
       u4PitIdxItemLen = 32;
	
    for (i = 0; ((u4PITHeadSigAddr + i * u4PitIdxItemLen) < u4PITTailSigAddr); i++) {
         UINT32 u4Nansize = *(UINT32 *) (u4PITHeadSigAddr + (i * u4PitIdxItemLen) );
         UINT32 u4OffsetFromImag = *(UINT32 *) (u4PITHeadSigAddr + (i * u4PitIdxItemLen)+ 4);
         UINT32 u4TableSize = *(UINT32 *) (u4PITHeadSigAddr + (i * u4PitIdxItemLen) + 8);
         UINT32 u4ParTableTagId = *(UINT32 *) (u4PITHeadSigAddr + (i * u4PitIdxItemLen) + 12);
		 UPG_LOG(UPG_LOG_DEBUG, "[UPG]upg_main.c current nand size = %d, PITI u4Nansize = %d \n", nand_info[0].size, u4Nansize);
         if(u4Nansize == nand_info[0].size )
         {
            UPG_LOG(UPG_LOG_DEBUG, "[UPG]upg_main.c findPITFromFinalBinaryImage got 1024 PIT \n");
			if(_fgNewPitIndexFormat)
            {
               u4BITHeadSigAddr = *(UINT32 *) (u4PITHeadSigAddr + (i * u4PitIdxItemLen) + 16) + u4pImgStartAddr;
               u4BITTailSigAddr = *(UINT32 *) (u4PITHeadSigAddr + (i * u4PitIdxItemLen) + 20) + u4BITHeadSigAddr - 20;
            }
            u4PITHeadSigAddr = u4pImgStartAddr + u4OffsetFromImag;
            u4PITTailSigAddr = u4PITHeadSigAddr + u4TableSize - 32;
            UPG_LOG(UPG_LOG_DEBUG, "[UPG]findPITFromFinalBinaryImage u4PITHeadSigAddr=0x%X u4PITTailSigAddr=0x%X\n",
            u4PITHeadSigAddr, u4PITTailSigAddr);
            u4PITStartAddr = u4PITHeadSigAddr;
            u4PITSize      = u4TableSize;
            fgFindPITFromBE = TRUE;
            break;
         }
    
    }
    
    //
    if(!fgFindPITFromBE)
    {
      UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]findPITFromFinalBinaryImage error, Cannot find PIT table from BE which match with nand size =0x%X\n",
              nand_info[0].size);
      return UPGR_FAIL;
    }
    //
    #if UPG_SUPPORT_NEW_PIT_FORMAT
    _u4PitTableVersion = *((UINT32*)(u4PITHeadSigAddr + 8));
    #endif 
    #if UPG_SUPPORT_NEW_PIT_FORMAT
    if(_u4PitTableVersion == PITHEADSIG)
    {
    #endif
    for (i = 0; i < u4ImgSize; i += PIT_SEARCH_STEP) {
       if ( (*(UINT32 *) (u4PITHeadSigAddr + i) == PITTAILSIG) &&
       (*(UINT32 *) (u4PITHeadSigAddr + i + 4) == PITTAILSIG) &&
       (*(UINT32 *) (u4PITHeadSigAddr + i + 8) == PITTAILSIG) &&
       (*(UINT32 *) (u4PITHeadSigAddr + i + 12) == PITTAILSIG) &&
       (*(UINT32 *) (u4PITHeadSigAddr + i + 16) == PITTAILSIG) &&
       (*(UINT32 *) (u4PITHeadSigAddr + i + 20) == PITTAILSIG) &&
       (*(UINT32 *) (u4PITHeadSigAddr + i + 24) == PITTAILSIG) &&
       (*(UINT32 *) (u4PITHeadSigAddr + i + 28) == PITTAILSIG) ) {		
          UPG_LOG(UPG_LOG_DEBUG, "[UPG]get pit tail addr:0x%X\n", (u4PITHeadSigAddr + i));	
          u4PITTailSigAddr = u4PITHeadSigAddr + i;
          break;
       }
    }
    #if UPG_SUPPORT_NEW_PIT_FORMAT
    }
    #endif
#endif

 #if !UPG_SUPPORT_ADAPTIVE_PIT
    #if UPG_SUPPORT_NEW_PIT_FORMAT
    if(_u4PitTableVersion == PITHEADSIG)
    {
    #endif
	for (i = 1; ((u4PITHeadSigAddr + i * PIT_ENTRY_SIZE) < u4PITTailSigAddr); i++) {
		CHAR * szName = u4PITHeadSigAddr + (i * PIT_ENTRY_SIZE);
		UINT32 u4PartID = *(UINT32 *) (u4PITHeadSigAddr + (i * PIT_ENTRY_SIZE) + 16);
		UINT32 u4PartInfo = *(UINT32 *) (u4PITHeadSigAddr + (i * PIT_ENTRY_SIZE) + 20);
		UINT32 u4OffsetFromFlash = *(UINT32 *) (u4PITHeadSigAddr + (i * PIT_ENTRY_SIZE) + 24);
		UINT32 u4PartSize = *(UINT32 *) (u4PITHeadSigAddr + (i * PIT_ENTRY_SIZE) + 28);
		UPG_LOG(UPG_LOG_DEBUG, "[UPG]findPITFromFinalBinaryImage[i=%2d,name=%16s,partid=%2d,partinfo=%2d,offset=0x%8X,size=0x%8X\n",
			i, szName, u4PartID, u4PartInfo, u4OffsetFromFlash, u4PartSize);
					
		strncpy(partInfoTable[i-1].szName, szName, 16);		
		partInfoTable[i-1].u4PartID = u4PartID;
		partInfoTable[i-1].u4PartInfo = u4PartInfo;
		partInfoTable[i-1].u4OffsetFromFlash = u4OffsetFromFlash;
		partInfoTable[i-1].u4PartSize = u4PartSize;
	}
    u4PitFromBeItemNum = i - 1;
    #if UPG_SUPPORT_NEW_PIT_FORMAT
    }
    else if(_u4PitTableVersion == 1)
    {
       u4PitHeaderLen = *((UINT32*)(u4PITHeadSigAddr + 12));
       u4PitItemLen = *((UINT32*)(u4PITHeadSigAddr + 16));
       u4PitItemNum = *((UINT32*)(u4PITHeadSigAddr + 20));
       for (i = 0; i< u4PitItemNum; i++) {
           CHAR * szName = u4PITHeadSigAddr + u4PitHeaderLen + (i * u4PitItemLen);
           UINT32 u4PartID = *(UINT32 *) (u4PITHeadSigAddr + u4PitHeaderLen + (i * u4PitItemLen) + 16);
           UINT32 u4PartInfo = *(UINT32 *) (u4PITHeadSigAddr + u4PitHeaderLen + (i * u4PitItemLen) + 20);
           UINT32 u4OffsetFromFlash = *(UINT32 *) (u4PITHeadSigAddr + u4PitHeaderLen + (i * u4PitItemLen) + 24);
           UINT32 u4PartSize = *(UINT32 *) (u4PITHeadSigAddr + u4PitHeaderLen + (i * u4PitItemLen) + 28);
           UPG_LOG(UPG_LOG_DEBUG, "[UPG]findPITFromFinalBinaryImage[i=%2d,name=%16s,partid=%2d,partinfo=%2d,offset=0x%8X,size=0x%8X\n",
                     i, szName, u4PartID, u4PartInfo, u4OffsetFromFlash, u4PartSize);
           
           strncpy(partInfoTable[i].szName, szName, 16);		
           partInfoTable[i].u4PartID = u4PartID;
           partInfoTable[i].u4PartInfo = u4PartInfo;
           partInfoTable[i].u4OffsetFromFlash = u4OffsetFromFlash;
           partInfoTable[i].u4PartSize = u4PartSize;
       }  
       u4PitFromBeItemNum = u4PitItemNum;
    }
    else
    {
        UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]findPITFromFinalBinaryImage error, unknown pit version = 0x%08x \n",
                               _u4PitTableVersion);
        return UPGR_FAIL; 
    }
    #endif
#endif


	UPG_LOG(UPG_LOG_DEBUG, "[UPG]findPITFromFinalBinaryImage PIT data dump:\n");
	dumpPIT();

	return UPGR_OK;
}


/*-----------------------------------------------------------------------------
 * Name: findBITFromFinalBinaryImage
 *
 * Description: Find binary info table from final binary image
 *
 * Inputs:    	u4pImgStartAddr	Image start address from memory
 *				u4ImgSize		Image size loaded to memory
 * Outputs: -
 *
 * Returns: 	UPGR_OK			Find BIT success
 *				UPGR_FAIL		Find BIT failed
 ----------------------------------------------------------------------------*/

INT32 findBITFromFinalBinaryImage(UINT8 *u4pImgStartAddr, UINT32 u4ImgSize) {
	initBIT();
	INT32 i = 0;
	

	INT32 u4PrePartID = INVALID_PART_ID;
	INT32 u4PartIdx = -1;
	INT32 u4BinIdx = -1;
	if(!_fgNewPitIndexFormat)
    {
    	for (i = 0; i < u4ImgSize; i += BIT_SEARCH_STEP) {
    		if ( (*(UINT32 *) (u4pImgStartAddr + i) == BITHEADSIG) &&
    			(*(UINT32 *) (u4pImgStartAddr + i + 4) == BITHEADSIG) &&
    			(*(UINT32 *) (u4pImgStartAddr + i + 8) == BITHEADSIG) &&
    			(*(UINT32 *) (u4pImgStartAddr + i + 12) == BITHEADSIG) &&
    			(*(UINT32 *) (u4pImgStartAddr + i + 16) == BITHEADSIG)) {			
    			UPG_LOG(UPG_LOG_DEBUG, "[UPG]get bit head addr:0x%X\n", (u4pImgStartAddr + i));
    			u4BITHeadSigAddr = u4pImgStartAddr + i;
    			break;
    		}
    	}
    
    
    	for (; i < u4ImgSize; i += 4) {
    		if ( (*(UINT32 *) (u4pImgStartAddr + i) == BITTAILSIG) &&
    			(*(UINT32 *) (u4pImgStartAddr + i + 4) == BITTAILSIG) &&
    			(*(UINT32 *) (u4pImgStartAddr + i + 8) == BITTAILSIG) &&
    			(*(UINT32 *) (u4pImgStartAddr + i + 12) == BITTAILSIG) &&
    			(*(UINT32 *) (u4pImgStartAddr + i + 16) == BITTAILSIG)) {			
    			UPG_LOG(UPG_LOG_DEBUG, "[UPG]get bit tail addr:0x%X\n", (u4pImgStartAddr + i));	
    			u4BITTailSigAddr = u4pImgStartAddr + i;
    			break;
    		}
    	}
    }

	UPG_LOG(UPG_LOG_DEBUG, "[UPG]findBITFromFinalBinaryImage u4BITHeadSigAddr=0x%X u4BITTailSigAddr=0x%X\n",
		u4BITHeadSigAddr, u4BITTailSigAddr);

	if (u4BITHeadSigAddr == NULL || u4BITTailSigAddr == NULL) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]findBITFromFinalBinaryImage not found BIT head and tail sig\n");
		return UPGR_FAIL;
	}

	for (i = 1; ((u4BITHeadSigAddr + i * BIT_ENTRY_SIZE) < u4BITTailSigAddr); i++) {
		UINT32 u4PartID = *(UINT32 *) (u4BITHeadSigAddr + (i * BIT_ENTRY_SIZE));
		UINT32 u4OffsetFromFinalImg = *(UINT32 *) (u4BITHeadSigAddr + (i * BIT_ENTRY_SIZE) + 4);
		UINT32 u4BinSize = *(UINT32 *) (u4BITHeadSigAddr + (i * BIT_ENTRY_SIZE) + 8);;
		UINT32 u4OffsetFromPart = *(UINT32 *) (u4BITHeadSigAddr + (i * BIT_ENTRY_SIZE) + 12);
		UINT32 u4BinInfo = *(UINT32 *) (u4BITHeadSigAddr + (i * BIT_ENTRY_SIZE) + 16);
				
		UPG_LOG(UPG_LOG_DEBUG, "[UPG]findBITFromFinalBinaryImage[i=%2d,partid=%2d,u4OffsetFromFinalImg=0x%8X,u4BinSize=0x%8X,u4OffsetFromPart=0x%8X\n",
			i, u4PartID, u4OffsetFromFinalImg, u4BinSize, u4OffsetFromPart);

		UPG_LOG(UPG_LOG_DEBUG, "u4PartID=%d, u4PrePartID=%x, u4PartIdx=%d, u4BinIdx=%d\n",
			u4PartID, u4PrePartID, u4PartIdx, u4BinIdx);		
		if (u4PartID != u4PrePartID) {//coming a new partid
			if (u4PartIdx > -1) {
				if (u4BinIdx > 1) {
					binaryInfoTable[u4PartIdx].u4HasMoreBin = 1;
				} else {
					binaryInfoTable[u4PartIdx].u4HasMoreBin = 0;
				}
			}
			u4PrePartID = u4PartID;
			u4PartIdx++;
			u4BinIdx = 0;//Reset
			binaryInfoTable[u4PartIdx].u4PartID = u4PartID;//todo
		}
		if (u4BinIdx > 0) {
           binaryInfoTable[u4PartIdx].u4HasMoreBin = 1;
        }
		binaryInfoTable[u4PartIdx].partBinEntries[u4BinIdx].u4PartID = u4PartID;
		binaryInfoTable[u4PartIdx].partBinEntries[u4BinIdx].u4BinSize = u4BinSize;
		binaryInfoTable[u4PartIdx].partBinEntries[u4BinIdx].u4OffsetFromFinalImg = u4OffsetFromFinalImg;
		binaryInfoTable[u4PartIdx].partBinEntries[u4BinIdx].u4OffsetFromPart = u4OffsetFromPart;
		binaryInfoTable[u4PartIdx].partBinEntries[u4BinIdx++].u4BinInfo = u4BinInfo;
	}

	UPG_LOG(UPG_LOG_DEBUG, "[UPG]findBITFromFinalBinaryImage BIT data dump:\n");
	dumpBIT();	
	return UPGR_OK;
}


void freeLoadImageStartAddr(UINT32 **u4pImgStartAddr) {
#if UPG_DYNAMIC_IMG_BUF	
	if (u4pImgStartAddr != NULL) {
		free(*u4pImgStartAddr);
	}
#endif
	UPG_LOG(UPG_LOG_DEBUG, "freeLoadImageStartAddr u4pImgStartAddr=0x%x\n", u4pImgStartAddr);
	*u4pImgStartAddr = NULL;
	UPG_LOG(UPG_LOG_DEBUG, "freeLoadImageStartAddr 2 u4pImgStartAddr=0x%x\n", u4pImgStartAddr);
}


/*-----------------------------------------------------------------------------
 * Name: loadImage
 *
 * Description: Load image to memory
 *
 * Inputs:    	upgInterface	Image load interface such as usb, tftp
 *				imageType		Image type such as IMG_TYPE_BE, IMG_TYPE_FE
 *				u4pImgStartAddr	Image start address loaded to memory
 *				u4ImgSize		Image size
 * Outputs: -
 *
 * Returns: 	UPGR_OK			Load image success
 *				UPGR_FAIL		Load image failed
 ----------------------------------------------------------------------------*/
    INT32 loadImage(upg_interface_t upgInterface, img_type_t imageType, UINT32 *u4pImgStartAddr, UINT32 *u4pImgSize, INT32* upgBootPart, INT32* upgBackupPart) {
		INT32 i4Ret = UPGR_OK;
	
		if (upgInterface == UPG_INTERFACE_USB) {//Load from USB
			CHAR* szUpgUSBPath;
			CHAR* szUpgImgName;
            #if CFG_UPG_SUPPORT_JIGMODE
            CHAR* szUpgUSBPathFW;
			CHAR* szUpgImgMatchName;
            szUpgUSBPath = getenv(UPG_USB_FW_PATH_TAG);			
			szUpgUSBPathFW = getenv(UPG_USB_PATH_TAG);
            if ( (IMG_TYPE_BE == imageType)&&(upgBootPart != NULL)&&(upgBackupPart != NULL))
            {
              *upgBootPart = 0;
              *upgBackupPart = 0;
            }
			#else			
			szUpgUSBPath = getenv(UPG_USB_PATH_TAG);
			#endif
			i4Ret = startUSB();
            if (i4Ret == UPGR_FAIL) {
                UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]start usb fail!! \n");
                return UPGR_FAIL;
            }
            
			if (IMG_TYPE_BE == imageType) {
				#if CFG_UPG_SUPPORT_JIGMODE
                szUpgImgName =  malloc(256 * sizeof(CHAR));
                memset((void*)szUpgImgName, 0, 256);
                if (szUpgImgName == NULL) {
                  UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]find matched file maloc memory failed.\n");
                  return UPGR_FAIL;
                }				
                *szUpgImgName = '\0';
				szUpgImgMatchName = getenv(UPG_BSM_BE_NAME_TAG); 
				i4Ret = usbFindFile(szUpgUSBPath, szUpgImgMatchName, szUpgImgName);
				if (i4Ret == UPGR_FAIL) {
                   UPG_LOG(UPG_LOG_DEBUG, "[UPG_ERROR]cannot find matched file %s in path s% \n",szUpgImgMatchName, szUpgUSBPath);
   				   *szUpgImgName = '\0';                   
   				   szUpgImgMatchName = getenv(UPG_BSM_BE_NAME_TAG); 
   				   i4Ret = usbFindFile(szUpgUSBPathFW, szUpgImgMatchName, szUpgImgName);
				   if (i4Ret == UPGR_FAIL) {	  	
					  UPG_LOG(UPG_LOG_DEBUG, "[UPG_ERROR]cannot find matched file %s in path s% \n",szUpgImgMatchName, szUpgUSBPathFW);
					  return UPGR_FAIL;
				   	}
                }
				#else
                szUpgImgName = getenv(UPG_BE_NAME_TAG);  
				#endif
			} else if (IMG_TYPE_FE == imageType) {
                #if CFG_UPG_SUPPORT_JIGMODE
                szUpgImgName = getenv(UPG_BSM_FE_NAME_TAG);  
                #else
                szUpgImgName = getenv(UPG_FE_NAME_TAG);   
                #endif
			} else {
				UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]loadImage invalid image type:%d\n", imageType);
				return UPGR_FAIL;
			}
	
			CHAR* szFullPath = malloc(1024 * sizeof(CHAR));
			if (szFullPath == NULL) {
				UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]loadImage maloc memory failed.\n");
				return UPGR_FAIL;
			}
			*szFullPath = '\0';
			
			szFullPath = catFilePathName(szFullPath, szUpgUSBPath, szUpgImgName);
			if (szFullPath == NULL) {
				UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]loadImage full path is NULL\n");
				return UPGR_FAIL;
			}
			UPG_LOG(UPG_LOG_DEBUG, "[UPG]loadImage full path:%s\n", szFullPath);
			
			
			i4Ret = usbGetImage(szFullPath, u4pImgStartAddr, u4pImgSize);
	
			UPG_LOG(UPG_LOG_DEBUG, "[UPG]loadImage after usbGetImage u4pImgStartAddr:0x%x, u4pImgSize=0x%x\n",
				u4pImgStartAddr, *u4pImgSize);
			
			if(*u4pImgSize > 0x6400000)//image size > 100M, need print error messaeg
			{
				UPG_LOG(UPG_LOG_ERROR, "[UPG]loadImage after usbGetImage,image size > 100M,  u4pImgSize=0x%x\n",
										*u4pImgSize);				
			}
			u1pImgBackUpInfoStartAddr = (UINT8*)((UINT32)u4pImgStartAddr + *u4pImgSize + 0x100000);

            #if UPG_SUPPORT_NEW_FE_UPG
            szCurrentWorkUsbPath = szUpgUSBPath;
            #endif
            #if CFG_UPG_SUPPORT_JIGMODE
            if(i4Ret == UPGR_FAIL)
            {
               if ((IMG_TYPE_BE == imageType)&&(upgBootPart != NULL)&&(upgBackupPart != NULL))
               {
                 *upgBootPart = 1;
                 *upgBackupPart = 1;
               }
               UPG_LOG(UPG_LOG_DEBUG, "[UPG_DEBUG] loadImage from USB upgall path failed\n");
               memset((void*)(szFullPath),0,1024 * sizeof(CHAR));
               szFullPath = catFilePathName(szFullPath, szUpgUSBPathFW, szUpgImgName);
               if (szFullPath == NULL) {
                 UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]loadImage full path is NULL\n");
                 return UPGR_FAIL;
               }
               UPG_LOG(UPG_LOG_DEBUG, "[UPG]loadFWImage full path:%s\n", szFullPath);
                             
               i4Ret = usbGetImage(szFullPath, u4pImgStartAddr, u4pImgSize);
               #if UPG_SUPPORT_NEW_FE_UPG
               szCurrentWorkUsbPath = szUpgUSBPathFW;
               #endif
			   
               UPG_LOG(UPG_LOG_DEBUG, "[UPG]loadImage after from USB upgfw path usbGetImage u4pImgStartAddr:0x%x, u4pImgSize=0x%x\n",
               u4pImgStartAddr, *u4pImgSize);
            }
            #endif
	
			if (i4Ret == UPGR_FAIL) {
				UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] loadBEImage from USB failed\n");
				free(szFullPath);
				return UPGR_FAIL;
			}
	
			free(szFullPath);
			
		} 
#ifdef CONFIG_MMC 
		else if (upgInterface == UPG_INTERFACE_MMC) {//Load from MMC
			CHAR* szUpgMMCPath;
			CHAR* szUpgImgName;
            #if CFG_UPG_SUPPORT_JIGMODE
            CHAR* szUpgMMCPathFW;
			CHAR* szUpgImgMatchName;
            szUpgMMCPath = getenv(UPG_USB_FW_PATH_TAG);			
			szUpgMMCPathFW = getenv(UPG_USB_PATH_TAG);
            if ( (IMG_TYPE_BE == imageType)&&(upgBootPart != NULL)&&(upgBackupPart != NULL))
            {
              *upgBootPart = 0;
              *upgBackupPart = 0;
            }
			#else			
			szUpgMMCPath = getenv(UPG_USB_PATH_TAG);
			#endif
			i4Ret = startMMC();
            if (i4Ret == UPGR_FAIL) {
                UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]start mmc fail!! \n");
                return UPGR_FAIL;
            }
            
			if (IMG_TYPE_BE == imageType) {
				#if CFG_UPG_SUPPORT_JIGMODE
                szUpgImgName =  malloc(256 * sizeof(CHAR));
                memset((void*)szUpgImgName, 0, 256);
                if (szUpgImgName == NULL) {
                  UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]find matched file maloc memory failed.\n");
                  return UPGR_FAIL;
                }				
                *szUpgImgName = '\0';
				szUpgImgMatchName = getenv(UPG_BSM_BE_NAME_TAG); 
				i4Ret = mmcFindFile(szUpgMMCPath, szUpgImgMatchName, szUpgImgName);
				if (i4Ret == UPGR_FAIL) {
                   UPG_LOG(UPG_LOG_DEBUG, "[UPG_ERROR]cannot find matched file %s in path s% \n",szUpgImgMatchName, szUpgUSBPath);
   				   *szUpgImgName = '\0';                   
   				   szUpgImgMatchName = getenv(UPG_BSM_BE_NAME_TAG); 
   				   i4Ret = mmcFindFile(szUpgMMCPathFW, szUpgImgMatchName, szUpgImgName);
				   if (i4Ret == UPGR_FAIL) {	  	
					  UPG_LOG(UPG_LOG_DEBUG, "[UPG_ERROR]cannot find matched file %s in path s% \n",szUpgImgMatchName, szUpgMMCPathFW);
					  return UPGR_FAIL;
				   	}
                }
				#else
                szUpgImgName = getenv(UPG_BE_NAME_TAG);  
				#endif
			} else if (IMG_TYPE_FE == imageType) {
                #if CFG_UPG_SUPPORT_JIGMODE
                szUpgImgName = getenv(UPG_BSM_FE_NAME_TAG);  
                #else
                szUpgImgName = getenv(UPG_FE_NAME_TAG);   
                #endif
			} else {
				UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]loadImage invalid image type:%d\n", imageType);
				return UPGR_FAIL;
			}
	
			CHAR* szFullPath = malloc(1024 * sizeof(CHAR));
			if (szFullPath == NULL) {
				UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]loadImage maloc memory failed.\n");
				return UPGR_FAIL;
			}
			*szFullPath = '\0';
			
			szFullPath = catFilePathName(szFullPath, szUpgMMCPath, szUpgImgName);
			if (szFullPath == NULL) {
				UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]loadImage full path is NULL\n");
				return UPGR_FAIL;
			}
			UPG_LOG(UPG_LOG_DEBUG, "[UPG]loadImage full path:%s\n", szFullPath);
			
			
			i4Ret = mmcGetImage(szFullPath, u4pImgStartAddr, u4pImgSize);
	
			UPG_LOG(UPG_LOG_DEBUG, "[UPG]loadImage after mmcGetImage u4pImgStartAddr:0x%x, u4pImgSize=0x%x\n",
				u4pImgStartAddr, *u4pImgSize);
			
               if(*u4pImgSize > 0x6400000)//image size > 100M, need print error messaeg
               {
				UPG_LOG(UPG_LOG_ERROR, "[UPG]loadImage after mmcGetImage,image size > 100M,  u4pImgSize=0x%x\n",
                                           *u4pImgSize);			   
               }
               u1pImgBackUpInfoStartAddr = (UINT8*)((UINT32)u4pImgStartAddr + *u4pImgSize + 0x100000);
			   
            #if UPG_SUPPORT_NEW_FE_UPG
            szCurrentWorkUsbPath = szUpgMMCPath;
            #endif
            #if CFG_UPG_SUPPORT_JIGMODE
            if(i4Ret == UPGR_FAIL)
            {
               if ((IMG_TYPE_BE == imageType)&&(upgBootPart != NULL)&&(upgBackupPart != NULL))
               {
                 *upgBootPart = 1;
                 *upgBackupPart = 1;
               }
               UPG_LOG(UPG_LOG_DEBUG, "[UPG_DEBUG] loadImage from MMC upgall path failed\n");
               memset((void*)(szFullPath),0,1024 * sizeof(CHAR));
               szFullPath = catFilePathName(szFullPath, szUpgMMCPathFW, szUpgImgName);
               if (szFullPath == NULL) {
                 UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]loadImage full path is NULL\n");
                 return UPGR_FAIL;
               }
               UPG_LOG(UPG_LOG_DEBUG, "[UPG]loadFWImage full path:%s\n", szFullPath);
                             
               i4Ret = mmcGetImage(szFullPath, u4pImgStartAddr, u4pImgSize);
               #if UPG_SUPPORT_NEW_FE_UPG
               szCurrentWorkUsbPath = szUpgMMCPathFW;
               #endif
			   
               UPG_LOG(UPG_LOG_DEBUG, "[UPG]loadImage after from MMC upgfw path mmcGetImage u4pImgStartAddr:0x%x, u4pImgSize=0x%x\n",
               u4pImgStartAddr, *u4pImgSize);
            }
            #endif
	
			if (i4Ret == UPGR_FAIL) {
				UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] loadBEImage from MMC failed\n");
				free(szFullPath);
				return UPGR_FAIL;
			}
	
			free(szFullPath);
			
		}
#endif // #ifdef CONFIG_MMC 
		else if (upgInterface == UPG_INTERFACE_TFTP) {//Load from TFTP
			CHAR* szUpgImgName;
			INT32 u4ImageSize = 0;
			
			UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] loadImage from TFTP \n");
			//return UPGR_FAIL;
	
			if (IMG_TYPE_BE == imageType) {
				szUpgImgName = getenv(UPG_BE_NAME_TAG); 
			} else if (IMG_TYPE_FE == imageType) {
				szUpgImgName = getenv(UPG_FE_NAME_TAG); 
			} else {
				UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]loadImage invalid image type:%d\n", imageType);
			return UPGR_FAIL;
		}
	
			//get file via tftp
			CHAR tftploadfilecmd[1024];
			sprintf(tftploadfilecmd, "tftpboot 0x%X %s", u4pImgStartAddr, szUpgImgName);
			INT32 u4Ret = run_command(tftploadfilecmd, 0);
			UPG_LOG(UPG_LOG_DEBUG, "[UPG]tftploadfilecmd run_command:%s  ret:%d\n", tftploadfilecmd, u4Ret);
			if (-1 == u4Ret) {
				UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]call cmd: %s failed\n", tftploadfilecmd);	
				return UPGR_FAIL;
			}
	
			CHAR * tmp;
			UINT32 u4FileSize = 0;
			if ((tmp = getenv ("tftpfilesize")) == NULL) {
				UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]loadFile tftpfilesize==NULL\n");
				return UPGR_FAIL;
			} else {
				u4FileSize = simple_strtoul (tmp, NULL, 16);
				if (u4FileSize <= 0) {
					UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]loadFile tftpfilesize==0\n");
					return UPGR_FAIL;	
				} else {
					UPG_LOG(UPG_LOG_DEBUG, "[UPG]loadFile filesize=0x%X\n", u4FileSize);
					*u4pImgSize = u4FileSize;
					return UPGR_OK;
				}
			}
		}
	
		return i4Ret;
	}



/*-----------------------------------------------------------------------------
 * Name: upgFinalImgToNand
 *
 * Description: Upgrade final images(include more binary images) into nand flash
 *
 * Inputs:    	u4pImgStartAddr	Final image start address loaded to memory
 *				u4ImgSize		Final image size
 * Outputs: -
 *
 * Returns: 	UPGR_OK			Upgrade to nand success
 *				UPGR_FAIL		Upgrade to nand failed
 ----------------------------------------------------------------------------*/
INT32 upgFinalImgToNand(UINT32 *u4pImgStartAddr, UINT32 u4ImgSize, INT32 i4PassedPartID) {
	UINT32 i = 0;
	UINT32 j = 0;
	INT32 u4Ret = 0;
	
	for (i = 0; (i < MAX_PARTITION_INFO_ENTRY_NUM) && 
			(binaryInfoTable[i].u4PartID != INVALID_PART_ID); i++) {
		if ((i4PassedPartID == binaryInfoTable[i].u4PartID) ||	(i4PassedPartID == INVALID_PART_ID)) {//todo
			if (binaryInfoTable[i].u4HasMoreBin == 0) {//only one bin each part
				UINT32 u4PartID  = binaryInfoTable[i].u4PartID;
				UINT32 u4BinSize = binaryInfoTable[i].partBinEntries[0].u4BinSize;
				UINT32 u4OffsetFromFinalImg = binaryInfoTable[i].partBinEntries[0].u4OffsetFromFinalImg;
				UINT32 u4OffsetFromPart = binaryInfoTable[i].partBinEntries[0].u4OffsetFromPart;
				UINT32 u4PartOffsetFromFlash = getPartOffsetFromFlash(u4PartID);
				UINT32 u4PartSize = getPartSize(u4PartID);

	            UPG_LOG(UPG_LOG_DEBUG, "[UPG]write data to nand partid=%d, u4OffsetFromPart=0x%x, u4PartOffsetFromFlash=0x%x\
	                u4PartSize=0x%x, memstartaddr=0x%x, size=0x%x\n",
	                u4PartID, u4OffsetFromPart, u4PartOffsetFromFlash, 
	                u4PartSize, (u4pImgStartAddr +  u4OffsetFromFinalImg), u4BinSize);
				UPG_LOG(UPG_LOG_DEBUG, "[UPG] upgFinalImgToNand eighth bytes is:0x%8x 0x%8x\n", 
					*(UINT32 * ) ((UCHAR *)u4pImgStartAddr +  u4OffsetFromFinalImg), 
					*((UINT32 * ) ((UCHAR *)u4pImgStartAddr +  u4OffsetFromFinalImg) + 1));
				u4Ret = writeDataToNand(((UCHAR *)u4pImgStartAddr +  u4OffsetFromFinalImg),
					u4BinSize, u4PartID, u4OffsetFromPart, u4PartOffsetFromFlash, u4PartSize);	

				if (u4Ret == UPGR_FAIL) {
					UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]upgFinalImgToNand 1 write data to nand failed\n");
					return UPGR_FAIL;
				} 

				if (i4PassedPartID == u4PartID) {
					break; //todo only upgrade one part
				}
			} else {
				//TODO
				//Merge more bins to one and write to nand only once
				UINT32 u4AllBinsSize = 0;		//include padding data between each binary
				UINT32 u4PartID  = binaryInfoTable[i].u4PartID;
				UINT32 u4OffsetFromPart = binaryInfoTable[i].partBinEntries[0].u4OffsetFromPart;
				UINT32 u4PartOffsetFromFlash = getPartOffsetFromFlash(u4PartID);
				UINT32 u4PartSize = getPartSize(u4PartID);

				for (j = 0; (j < MAX_BINARY_INFO_PART_BIN_ENTRY_NUM) 
					&& (binaryInfoTable[i].partBinEntries[j].u4PartID != INVALID_PART_ID); j++) {
					u4AllBinsSize = binaryInfoTable[i].partBinEntries[j].u4OffsetFromPart;//last bin offset + last bin size
				}
				u4AllBinsSize += binaryInfoTable[i].partBinEntries[j-1].u4BinSize;			
				UPG_LOG(UPG_LOG_DEBUG, "[UPG]upgFinalImgToNand u4AllBinsSize=0x%X\n", u4AllBinsSize);

				UINT32 u4AlignedAllBinSize = u4AllBinsSize;

				if ((u4AlignedAllBinSize % NAND_PAGE_SIZE) != 0) //ImageSize alignment
	  			{
	    			u4AlignedAllBinSize = u4AlignedAllBinSize / NAND_PAGE_SIZE;
	    			u4AlignedAllBinSize = (u4AlignedAllBinSize + 1) * NAND_PAGE_SIZE;
	  			}
				
				UINT8 * u1pBuf = malloc(u4AlignedAllBinSize);
				if (u1pBuf == NULL) {
					UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]upgFinalImgToNand malloc(size=0x%X) failed\n", u4AllBinsSize);
					return UPGR_FAIL;
				}

				UINT32 u4BinSize = 0;
				UINT32 u4PreBinSize = 0;
				UINT32 u4OffsetFromFinalImg = 0;
				UINT32 u4PreOffsetFromPart = 0;
				UINT32 u4CurOffsetFromPart = 0;
				UINT32 u4FistBinOffsetFromPart = 0;
				INT32 u4FirstBin = 1;
				
				//merge bins to buffer
				for (j = 0; (j < MAX_BINARY_INFO_PART_BIN_ENTRY_NUM) 
					&& (binaryInfoTable[i].partBinEntries[j].u4PartID != INVALID_PART_ID); j++) {
					
					u4CurOffsetFromPart = binaryInfoTable[i].partBinEntries[j].u4OffsetFromPart;
					UINT32 u4OffsetFromFinalImg = binaryInfoTable[i].partBinEntries[j].u4OffsetFromFinalImg;
					u4BinSize = binaryInfoTable[i].partBinEntries[j].u4BinSize;
					UPG_LOG(UPG_LOG_DEBUG, "u4CurOffsetFromPart=0x%X, u4PreOffsetFromPart=0x%X, u4OffsetFromFinalImg=0x%X\
						, u4BinSize=0x%X, u4PreBinSize=0x%X\n", u4CurOffsetFromPart,
						u4PreOffsetFromPart, u4OffsetFromFinalImg, u4BinSize, u4PreBinSize);
					if (u4CurOffsetFromPart > (u4PreOffsetFromPart + u4PreBinSize) && u4FirstBin != 1) {//Fill padding data
						UINT32 u4PadingDataLen = u4CurOffsetFromPart - (u4PreOffsetFromPart + u4PreBinSize);
						UINT8 u1PadData = 0xFF;

						memset(u1pBuf + u4PreOffsetFromPart + u4PreBinSize - u4FistBinOffsetFromPart,
							u1PadData, u4PadingDataLen);
					}

					
					if (u4FirstBin) {					
						memcpy(u1pBuf, ((UCHAR *)u4pImgStartAddr) + u4OffsetFromFinalImg, u4BinSize);
						u4FistBinOffsetFromPart = u4CurOffsetFromPart;
						u4FirstBin = 0;
					} else {					
						memcpy(u1pBuf + (u4CurOffsetFromPart - u4FistBinOffsetFromPart),
							((UCHAR *)u4pImgStartAddr) + u4OffsetFromFinalImg, u4BinSize);
					}				
					
					u4PreOffsetFromPart = u4CurOffsetFromPart;
					u4PreBinSize = u4BinSize;
				}

	            UPG_LOG(UPG_LOG_DEBUG, "[UPG]write data to nand more bins partid=%d, u4OffsetFromPart=0x%x, u4PartOffsetFromFlash=0x%x\
	                u4PartSize=0x%x, memstartaddr=0x%x, size=0x%x\n",
	                u4PartID, u4OffsetFromPart, u4PartOffsetFromFlash, 
	                u4PartSize, u1pBuf, u4AllBinsSize);
				UPG_LOG(UPG_LOG_DEBUG, "[UPG]upgFinalImgToNand more bins eighth bytes is:0x%8x 0x%8x\n", 
					*(UINT32 * ) u1pBuf, 
					*((UINT32 * ) u1pBuf + 1));

				u4Ret = writeDataToNand(u1pBuf, u4AllBinsSize, u4PartID, 
					u4OffsetFromPart, u4PartOffsetFromFlash, u4PartSize);	

				if (u4Ret == UPGR_FAIL) {
					UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]upgFinalImgToNand 2 write data to nand failed\n");
	                free(u1pBuf);
					return UPGR_FAIL;
				} 
				
				free(u1pBuf);

				if (i4PassedPartID == u4PartID) {
					break; //todo only upgrade one part
				}
			}

		}//todo
	}
	return UPGR_OK;
}


/*-----------------------------------------------------------------------------
 * Name: upgBEImge
 *
 * Description: Upgrade backend image such as mt8530_linux_img_dbg.bin
 *
 * Inputs:    	upgInterface	Upgrade interface(USB, TFTP)
 * Outputs: -
 *
 * Returns: 	UPGR_OK			Upgrade success
 *				UPGR_FAIL		Upgrade failed
 ----------------------------------------------------------------------------*/
INT32 upgBEImage(upg_interface_t upgInterface, INT32 upgBootPart, INT32 upgBackupPart) {
	INT32 i4Ret = UPGR_OK;
	INT32 i = 0;
        BOOL fgCreatUbiV = FALSE;
#if UPG_SUPPORT_ADAPTIVE_PIT
	INT32 u4AdaPitAddr = 0;
#endif
	
	UPG_LOG(UPG_LOG_DEBUG, "[UPG]upgBEImage loadImage u4ImgStartAddr=0x%x, u4ImgSize=0x%x\n",
			u4pImgStartAddr, u4ImgSize);
	
	if (u4pImgStartAddr == NULL) {
		i4Ret = reloadImage(upgInterface, IMG_TYPE_BE, &upgBootPart, &upgBackupPart);

		UPG_LOG(UPG_LOG_DEBUG, "[UPG]upgBEImage loadImage u4ImgStartAddr=0x%x, u4ImgSize=0x%x\n",
			u4pImgStartAddr, u4ImgSize);

		if	(i4Ret == UPGR_FAIL) {
			UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] upgBEImage loadImage failed\n");
			freeLoadImageStartAddr(&u4pImgStartAddr);
			return UPGR_FAIL;
		}
	}

        //check xor checksume
        /*i4Ret = CheckXorValueFromFinalBinaryImage((UINT8 *)u4pImgStartAddr, u4ImgSize);
	if (i4Ret == UPGR_FAIL) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] Upgrade BE image CheckXorValueFromFinalBinaryImage failed\n");
		freeLoadImageStartAddr(&u4pImgStartAddr);
		return UPGR_FAIL;
	}*/
	//parse BIT and PIT table	
	UPG_LOG(UPG_LOG_DEBUG, "[UPG]upgBEImage loaded image u4ImgSize=0x%X, u4pImgStartAddr=0x%X\n", 
		u4ImgSize, u4pImgStartAddr);
	//check region value in be binary
	
#if (CFG_BRANCH_NAME == 1)
	if(checkRegionFromFinalBinaryImage((UINT8 *)u4pImgStartAddr, u4ImgSize) == UPGR_FAIL)
        {
            UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] Check region value fail, region value unmatch!!! \n");
            //return UPGR_FAIL;
        }
	//end of region check
#endif
	i4Ret = findPITFromFinalBinaryImage((UINT8 *)u4pImgStartAddr, u4ImgSize);
	if (i4Ret == UPGR_FAIL) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] Upgrade BE image findPITFromFinalBinaryImage failed\n");
		freeLoadImageStartAddr(&u4pImgStartAddr);
		return UPGR_FAIL;
	}
#if UPG_SUPPORT_ADAPTIVE_PIT
    if(i4CompareFlashPIT(u4PITStartAddr,u4PITSize ) < 1)
    {
    	fgCreatUbiV = TRUE;
    	 //back up key info to dram, then write to nand with new pit
    #if UPG_SUPPORT_BACKUP_NAND_DATA
    if((fgCreatUbiV)&&(u4_part_nr))
    {
      UINT32 j = 0;
      UINT32 u4CurOff = 0;
      BOOL fgBackupInfoSuc = FALSE;
      vFillBackupTable();
      for(i = 0; i < u4BackupInfTableItemNum; i++)
      {
        fgBackupInfoSuc = FALSE;
        for(j = 0; j< u4_part_nr; j++)
        {
           if(strcmp( (char*)r_part_tbl[j+1].pc_name, (char*)backupInfoTable[i].szName) == 0)
           {
              UINT32 offset=r_part_tbl[j+1].u4_offset;
              UINT32 badblk=0;
              nand_info_t *nand = &nand_info[nand_curr_device];
              UINT32 readLen=r_part_tbl[j+1].u4_size;
              for(;offset<(r_part_tbl[j+1].u4_offset+r_part_tbl[j+1].u4_size);)
              {
                 if(nand_block_isbad (nand, offset & ~(nand->erasesize - 1)))
                    readLen -= nand->erasesize;
                    offset+=nand->erasesize;
              }
              i4Ret = readDataFromNand((UINT32)(u1pImgBackUpInfoStartAddr + u4CurOff), r_part_tbl[j+1].u4_offset, readLen);
              if (i4Ret != UPGR_OK) {
                UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] back up nand flash -> read nand failed\n");
                return UPGR_FAIL;
              }
              backupInfoTable[i].u4OffsetInBackupBuf = u4CurOff;
              backupInfoTable[i].u4Length = readLen;
              u4CurOff += readLen;
              fgBackupInfoSuc = TRUE;
              i4Ret = eraseDataFromNand(r_part_tbl[j+1].u4_offset, readLen, r_part_tbl[j+1].u4_id);
              if(-1 == i4Ret)
              {
                UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]back up nand flash -> eraseDataFromNand failed, partition id = %d, u4PartSize = %d, u4OffsetFromFlash = %d \n", partInfoTable[i].u4PartID, partInfoTable[i].u4PartSize, partInfoTable[i].u4OffsetFromFlash);	
                return UPGR_FAIL;
              }
			  
              UPG_LOG(UPG_LOG_DEBUG, "[UPG_ERROR]back up nand flash -> eraseDataFromNand failed, partition id = %d, u4PartSize = %d, u4OffsetFromFlash = %d \n", partInfoTable[i].u4PartID, partInfoTable[i].u4PartSize, partInfoTable[i].u4OffsetFromFlash); 
              break;
           }
        }
        if(!fgBackupInfoSuc)
        {
           UPG_LOG(UPG_LOG_ERROR, "[UPG_DEBUG]back up nand flash fail, failed pit name = %s!! \n",backupInfoTable[i].szName);		   	 
        }   
      }

      if(u4CurOff > 0x1900000)//if > 19M, print log,
      {	  	
         UPG_LOG(UPG_LOG_ERROR, "[UPG_DEBUG]back up nand flash, all back up size = %d > 19M!! \n",u4CurOff);			  
      }
    }
    #endif
       u4AdaPitAddr = i4SetNewFlashPIT(u4PITStartAddr);
       
    }
	u4AdaPitAddr = i4GetAdptPit();
    if(u4AdaPitAddr < 0 )
    {
        UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] Update Adaptive pit failed\n");
        return UPGR_FAIL;
    }
	
   //add by qq, for adt pit
    #if UPG_SUPPORT_NEW_PIT_FORMAT
    if(_u4PitTableVersion == PITHEADSIG)
    {
    #endif
	for (i = 1; ((u4AdaPitAddr + i * PIT_ENTRY_SIZE) < (u4AdaPitAddr + u4PITSize)); i++) {
		CHAR * szName = u4AdaPitAddr + (i * PIT_ENTRY_SIZE);
		UINT32 u4PartID = *(UINT32 *) (u4AdaPitAddr + (i * PIT_ENTRY_SIZE) + 16);
		UINT32 u4PartInfo = *(UINT32 *) (u4AdaPitAddr + (i * PIT_ENTRY_SIZE) + 20);
		UINT32 u4OffsetFromFlash = *(UINT32 *) (u4AdaPitAddr + (i * PIT_ENTRY_SIZE) + 24);
		UINT32 u4PartSize = *(UINT32 *) (u4AdaPitAddr + (i * PIT_ENTRY_SIZE) + 28);
		UPG_LOG(UPG_LOG_DEBUG, "[UPG]findPITFromFinalBinaryImage[i=%2d,name=%16s,partid=%2d,partinfo=%2d,offset=0x%8X,size=0x%8X\n",
			i, szName, u4PartID, u4PartInfo, u4OffsetFromFlash, u4PartSize);
					
		strncpy(partInfoTable[i-1].szName, szName, 16);		
		partInfoTable[i-1].u4PartID = u4PartID;
		partInfoTable[i-1].u4PartInfo = u4PartInfo;
		partInfoTable[i-1].u4OffsetFromFlash = u4OffsetFromFlash;
		partInfoTable[i-1].u4PartSize = u4PartSize;
	}
    u4PitFromBeItemNum = i - 1;
    #if UPG_SUPPORT_NEW_PIT_FORMAT
    }
    else if(_u4PitTableVersion == 1)
    {
       UINT32 u4PitHeaderLen = *((UINT32*)(u4AdaPitAddr + 12));
       UINT32 u4PitItemLen = *((UINT32*)(u4AdaPitAddr + 16));
       UINT32 u4PitItemNum = *((UINT32*)(u4AdaPitAddr + 20));
       for (i = 0; i< u4PitItemNum; i++) {
           CHAR * szName = u4AdaPitAddr + u4PitHeaderLen + (i * u4PitItemLen);
           UINT32 u4PartID = *(UINT32 *) (u4AdaPitAddr + u4PitHeaderLen + (i * u4PitItemLen) + 16);
           UINT32 u4PartInfo = *(UINT32 *) (u4AdaPitAddr + u4PitHeaderLen + (i * u4PitItemLen) + 20);
           UINT32 u4OffsetFromFlash = *(UINT32 *) (u4AdaPitAddr + u4PitHeaderLen + (i * u4PitItemLen) + 24);
           UINT32 u4PartSize = *(UINT32 *) (u4AdaPitAddr + u4PitHeaderLen + (i * u4PitItemLen) + 28);
           UPG_LOG(UPG_LOG_DEBUG, "[UPG]findPITFromFinalBinaryImage[i=%2d,name=%16s,partid=%2d,partinfo=%2d,offset=0x%8X,size=0x%8X\n",
                     i, szName, u4PartID, u4PartInfo, u4OffsetFromFlash, u4PartSize);
           
           strncpy(partInfoTable[i].szName, szName, 16);		
           partInfoTable[i].u4PartID = u4PartID;
           partInfoTable[i].u4PartInfo = u4PartInfo;
           partInfoTable[i].u4OffsetFromFlash = u4OffsetFromFlash;
           partInfoTable[i].u4PartSize = u4PartSize;
       }  
       u4PitFromBeItemNum = u4PitItemNum;
    }
    else
    {
        UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]findPITFromFinalBinaryImage error, unknown pit version = 0x%08x \n",
                               _u4PitTableVersion);
        return UPGR_FAIL; 
    }
    #endif
   //end of 
#endif

	i4Ret = findBITFromFinalBinaryImage((UINT8 *)u4pImgStartAddr, u4ImgSize);
	if (i4Ret == UPGR_FAIL) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] Upgrade BE image findBITFromFinalBinaryImage failed\n");	
		freeLoadImageStartAddr(&u4pImgStartAddr);		
		return UPGR_FAIL;
	}
    //for compare PIT in nand with PIT in BE.
    #if (!UPG_SUPPORT_ADAPTIVE_PIT)
    for(i = 0; i < u4PitFromBeItemNum; i++)
    {
       if( (r_part_tbl[i+1].u4_id != partInfoTable[i].u4PartID) ||
           (r_part_tbl[i+1].u4_info != partInfoTable[i].u4PartInfo) ||
           (r_part_tbl[i+1].u4_offset != partInfoTable[i].u4OffsetFromFlash) ||
           (r_part_tbl[i+1].u4_size != partInfoTable[i].u4PartSize) ||
           (strcmp( (char*)r_part_tbl[i+1].pc_name,(char *)partInfoTable[i].szName)) )
       {
           fgCreatUbiV = TRUE;
           UPG_LOG(UPG_LOG_DEBUG, "[UPG_DEBUG] PIT in nand different with PIT in BE!! \n");
           UPG_LOG(UPG_LOG_DEBUG, "[UPG_DEBUG] r_part_tbl %d, %d, %d, %d, %s!! \n",
                                  r_part_tbl[i+1].u4_id,
                                  r_part_tbl[i+1].u4_info, 
                                  r_part_tbl[i+1].u4_offset, 
                                  r_part_tbl[i+1].u4_size,
                                  r_part_tbl[i+1].pc_name);
           
           UPG_LOG(UPG_LOG_DEBUG, "[UPG_DEBUG] partInfoTable %d, %d, %d, %d, %s!! \n"
                                  ,partInfoTable[i].u4PartID,
                                  partInfoTable[i].u4PartInfo,
                                  partInfoTable[i].u4OffsetFromFlash, 
                                  partInfoTable[i].u4PartSize,
                                  partInfoTable[i].szName);
       }
       //for new pit ,need add some item compare
    }
    UPG_LOG(UPG_LOG_DEBUG, "[UPG_DEBUG]compare PIT in nand with PIT in BE, compared item num = %d!! \n",u4PitFromBeItemNum);
    #endif
	//Store each image to flash(nand) todo
	//adjust FE image partition if need todo
	

#if UPG_SUPPORT_UBI
   //set ubi enviornment
   //if(fgCreatUbiV)
   {
    i4Ret =  i4SetUBIMountEnv(partInfoTable);
     if(-1 == i4Ret)
     {
       UPG_LOG(UPG_LOG_DEBUG, "[UPG_ERROR] Upgrade BE image failed, set UBI env fail,ret:%d\n", i4Ret);
       return UPGR_FAIL;
     }    
   }
   	
   //parsing PIT , find part_info_1,part_info_2 and UBI volume, and write PIT to flash or create UBI vlolume
   for (i = 0; (i < MAX_PARTITION_INFO_ENTRY_NUM) && 
			(partInfoTable[i].u4PartID != INVALID_PART_ID); i++) {
        INT32		u4PartID = partInfoTable[i].u4PartID;
        INT32		u4PartInfo = partInfoTable[i].u4PartInfo;					//u4PartInfo[0:0]---Nor flag(1 Nor, 0 Nand)
        CHAR *		szPartName = getPartName(u4PartID);;
        UINT32		u4OffsetFromFlash = partInfoTable[i].u4OffsetFromFlash;
        UINT32		u4PartSize = partInfoTable[i].u4PartSize;
		UINT32 u4IsPrimary = ISPRIMARY(u4PartInfo);
		UINT32 u4IsUBIVolume = ISUBIVOLUME(u4PartInfo);
        UINT32 u4IsPIT = strstr(szPartName, "part_info_") ? 1:0;
        UINT32 u4IsUpgStats = strstr(szPartName, "upg_status") ? 1:0;
        UPG_LOG(UPG_LOG_DEBUG, "upgBEImage(search PIT) u4PartID=%d, szPartName=%s, u4OffsetFromFlash=0x%x, u4PartSize=%d, u4IsPrimary=%d, u4IsUBIVolume=%d, u4IsPIT=%d \n",
               u4PartID, szPartName, u4OffsetFromFlash, u4PartSize, u4IsPrimary, u4IsUBIVolume, u4IsPIT);
        if( (1 == u4IsUpgStats)&&(fgCreatUbiV) )
        {
           UPG_LOG(UPG_LOG_DEBUG, "[UPG_DEBUG]eraseDataFromNand , partition id = %d, u4PartSize = %d, u4OffsetFromFlash = %d \n", partInfoTable[i].u4PartID, partInfoTable[i].u4PartSize, partInfoTable[i].u4OffsetFromFlash);	 
           i4Ret = eraseDataFromNand(partInfoTable[i].u4OffsetFromFlash, partInfoTable[i].u4PartSize, partInfoTable[i].u4PartID);
           if(-1 == i4Ret)
           {
             UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]eraseDataFromNand failed, partition id = %d, u4PartSize = %d, u4OffsetFromFlash = %d \n", partInfoTable[i].u4PartID, partInfoTable[i].u4PartSize, partInfoTable[i].u4OffsetFromFlash);	
             return UPGR_FAIL;
           }
        }
		#if 1
        else if( (u4IsUBIVolume == 3)&&(fgCreatUbiV) )
        {
           i4Ret = i4CreateVolume(u4PartID, i);
           if(-1 == i4Ret)
           {
              UPG_LOG(UPG_LOG_DEBUG, "[UPG_ERROR] Upgrade BE image,i4CreateVolume failed, ret:%d\n", i4Ret);
              return UPGR_FAIL;
           }                
        }
		#endif
   	}
#endif
//
    for (i = 0; (i < MAX_PARTITION_INFO_ENTRY_NUM) && 
         (partInfoTable[i].u4PartID != INVALID_PART_ID); i++) 
    {
       if( ISUBIVOLUME(partInfoTable[i].u4PartInfo) != 4 )
       {
         if(mt85xx_init_mtd_table(partInfoTable[i].szName) != 0)	
         {
           UPG_LOG(UPG_LOG_ERROR, "[mtd_table init]%s not be added!\n", partInfoTable[i].szName);	
           return UPGR_FAIL;
         }
       }
    }

   i4Ret = i_set_upg_status_by_upg(US_PROGRESS_UPG_BE);
   if(i4Ret != UPG_STATUS_OK)
   {
      UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]i_set_upg_status_by_upg failed\n");	
      return UPGR_FAIL; 
   }
		   
	 //parsing PIT , find part_info_1,part_info_2 and UBI volume, and write PIT to flash or create UBI vlolume
   for (i = 0; (i < MAX_PARTITION_INFO_ENTRY_NUM) && 
			(partInfoTable[i].u4PartID != INVALID_PART_ID); i++) {
        INT32		u4PartID = partInfoTable[i].u4PartID;
        INT32		u4PartInfo = partInfoTable[i].u4PartInfo;					//u4PartInfo[0:0]---Nor flag(1 Nor, 0 Nand)
        CHAR *		szPartName = getPartName(u4PartID);;
        UINT32		u4OffsetFromFlash = partInfoTable[i].u4OffsetFromFlash;
        UINT32		u4PartSize = partInfoTable[i].u4PartSize;
		UINT32 u4IsPrimary = ISPRIMARY(u4PartInfo);
		UINT32 u4IsUBIVolume = ISUBIVOLUME(u4PartInfo);
        UINT32 u4IsPIT = strstr(szPartName, "part_info_") ? 1:0;
        UINT32 u4IsUpgStats = strstr(szPartName, "upg_status") ? 1:0;
        UPG_LOG(UPG_LOG_DEBUG, "upgBEImage(search PIT) u4PartID=%d, szPartName=%s, u4OffsetFromFlash=0x%x, u4PartSize=%d, u4IsPrimary=%d, u4IsUBIVolume=%d, u4IsPIT=%d \n",
               u4PartID, szPartName, u4OffsetFromFlash, u4PartSize, u4IsPrimary, u4IsUBIVolume, u4IsPIT);
        if((u4IsPIT == 1)&&(u4PITStartAddr != 0)&&(u4PITSize != 0)&&((u4IsPrimary == 0 && upgBackupPart == 1)||(u4IsPrimary == 1)))
        {
           UPG_LOG(UPG_LOG_DEBUG, "[UPG]write PIT data to nand partid=%d, u4OffsetFromPart=0x%x, u4PartOffsetFromFlash=0x%x\
                   u4PartSize=0x%x, memstartaddr=0x%x, size=0x%x\n",
                   u4PartID, 0, u4OffsetFromFlash, 
                   u4PartSize, (u4PITStartAddr), u4PITSize);
           UPG_LOG(UPG_LOG_DEBUG, "[UPG] upgPITToNand eighth bytes is:0x%8x 0x%8x\n", 
                   *(UINT32 * ) ((UCHAR *)u4PITStartAddr), 
                   *((UINT32 * ) ((UCHAR *)u4PITStartAddr) + 1));
           
           i4Ret = writeDataToNand(((UCHAR *)u4PITStartAddr),
           u4PITSize, u4PartID, 0, u4OffsetFromFlash, u4PartSize);	
           
           if (i4Ret == UPGR_FAIL) {
             UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]upgPITToNand 1 write data to nand failed\n");
             return UPGR_FAIL;
           } 
        }
        
   	}

	for (i = 0; (i < MAX_PARTITION_INFO_ENTRY_NUM) && 
			(binaryInfoTable[i].u4PartID != INVALID_PART_ID); i++) {
		UINT32 u4PartID  = binaryInfoTable[i].u4PartID;
		CHAR * szPartName = getPartName(u4PartID);
		UINT32 u4BinInfo = binaryInfoTable[i].partBinEntries[0].u4BinInfo;
		UINT32 u4IsBackup = ISBACKUP(u4BinInfo);
		UINT32 u4IsBootPart = strstr(szPartName, "boot_") ? 1:0;
		UPG_LOG(UPG_LOG_DEBUG, "upgBEImage u4PartID=%d, szPartName=%s, u4BinInfo=0x%x, u4IsBackup=%d, u4IsBootPart=%d\n",
			u4PartID, szPartName, u4BinInfo, u4IsBackup, u4IsBootPart);
		if (u4IsBackup == 1 && upgBackupPart == 0) {
			continue; //skip this part
		} else if (u4IsBootPart== 1 && upgBootPart == 0) {
			continue; //skip this part
		} else {
			//do it
		}
		i4Ret = upgFinalImgToNand(u4pImgStartAddr, u4ImgSize, u4PartID);
		if (i4Ret == UPGR_FAIL) {
			UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] Upgrade BE image write partid[%d] to nand failed\n", u4PartID);	
			//freeLoadImageStartAddr(&u4pImgStartAddr);//todo
			return UPGR_FAIL;
		}
	}

	//write back up info ro nand 
	
#if UPG_SUPPORT_BACKUP_NAND_DATA
    if((fgCreatUbiV)&&(u4_part_nr))
    {
      UINT32 j = 0;
      BOOL fgBackupInfoSuc = FALSE;
      vFillBackupTable();
      for(i = 0; i < u4BackupInfTableItemNum; i++)
      {
        if(backupInfoTable[i].u4Length != 0)  //LIQINGQING  add this line  100809 for tianle format->auto upg 2 times issue
        {
        fgBackupInfoSuc = FALSE;
        for(j = 0; j< u4_part_nr; j++)
        {
           if(strcmp( (char*)partInfoTable[j].szName, (char*)backupInfoTable[i].szName) == 0)
           {
              //i4Ret = eraseDataFromNand(partInfoTable[j].u4OffsetFromFlash, partInfoTable[j].u4PartSize, partInfoTable[j].u4PartID);
              //if(-1 == i4Ret)
              //{
                //UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]write back up nand flash -> eraseDataFromNand failed, partition id = %d, u4PartSize = %d, u4OffsetFromFlash = %d \n", partInfoTable[i].u4PartID, partInfoTable[i].u4PartSize, partInfoTable[i].u4OffsetFromFlash);	
                //return UPGR_FAIL;
              //}
              i4Ret = writeDataToNand((UINT32*)((UINT32)u1pImgBackUpInfoStartAddr + backupInfoTable[i].u4OffsetInBackupBuf),
			  	                      backupInfoTable[i].u4Length,
			  	                      partInfoTable[j].u4PartID,
			  	                      0,
			  	                      partInfoTable[j].u4OffsetFromFlash,
			  	                      partInfoTable[j].u4PartSize);
              if (i4Ret != UPGR_OK) {
                UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]write back up nand flash -> write nand failed\n");
                return UPGR_FAIL;
              }
			  
              UPG_LOG(UPG_LOG_DEBUG, "[UPG_ERROR]write back up nand flash -> write nand failed\n");
              fgBackupInfoSuc = TRUE;          
              break;
           }
        }
        if(!fgBackupInfoSuc)
        {
           UPG_LOG(UPG_LOG_ERROR, "[UPG_DEBUG]write back up nand flash fail, failed pit name = %s!! \n",backupInfoTable[i].szName);		   	 
        }   
      }
    }
    }
#endif
	//freeLoadImageStartAddr(&u4pImgStartAddr);//todo

    i4Ret = i_set_upg_status_by_upg(US_PROGRESS_UPG_SUC);
    if(i4Ret != UPG_STATUS_OK)
    {
       UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]i_set_upg_status_by_upg failed\n");	
       return UPGR_FAIL; 
    }

	return UPGR_OK;
}


/*-----------------------------------------------------------------------------
 * Name: reLoadBE
 *
 * Description: Reload image from interface such as USB, TFTP
 *
 * Inputs:    	upgInterface	Interface to load image such as usb, tftp
 * Outputs: -
 *
 * Returns: 	UPGR_OK			Reload image success
 *				UPGR_FAIL		Reload image failed
 ----------------------------------------------------------------------------*/
INT32 reloadImage(upg_interface_t upgInterface, img_type_t imageType, INT32* upgBootPart, INT32* upgBackupPart) {
	INT32 i4Ret = 0;
	INT32 i = 0;

	freeLoadImageStartAddr(&u4pImgStartAddr);//free first
	
#if UPG_DYNAMIC_IMG_BUF
	u4pImgStartAddr = malloc(UPG_IMAGE_BUFFER_SIZE);
	if (u4pImgStartAddr == NULL) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]reloadBE malloc memory failed\n");
		return UPGR_FAIL;
	} else {	
		UPG_LOG(UPG_LOG_DEBUG, "[UPG]reloadBE malloc memory addr:0x%X\n", u4pImgStartAddr);
	}

#else
	u4pImgStartAddr = UPG_IMAGE_BUFFER_ADDR;
#endif 

	//load be image
	i4Ret = loadImage(upgInterface, imageType, u4pImgStartAddr, &u4ImgSize, upgBootPart, upgBackupPart );

	UPG_LOG(UPG_LOG_DEBUG, "[UPG]reloadBE loadImage u4ImgStartAddr=0x%x, u4ImgSize=0x%x\n",
		u4pImgStartAddr, u4ImgSize);

	if  (i4Ret == UPGR_FAIL) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]reloadBE loadImage failed\n");
		freeLoadImageStartAddr(&u4pImgStartAddr);
		return UPGR_FAIL;
	}

	return UPGR_OK;
}


/*-----------------------------------------------------------------------------
 * Name: listPITForBE
 *
 * Description: List partition info table for backend image
 *
 * Inputs:    	upgInterface	Interface to load image such as usb, tftp
 * Outputs: -
 *
 * Returns: 	UPGR_OK			List PIT success
 *				UPGR_FAIL		List PIT failed
 ----------------------------------------------------------------------------*/
INT32 listPITForBE(upg_interface_t upgInterface) {
	INT32 i4Ret = 0;
	INT32 i = 0;
//	UINT32 u4ImgSize = 0;
//	UINT32 *u4pImgStartAddr;

	UPG_LOG(UPG_LOG_DEBUG, "[UPG]listPITForBE loadImage u4ImgStartAddr=0x%x, u4ImgSize=0x%x\n",
			u4pImgStartAddr, u4ImgSize);

	if (u4pImgStartAddr == NULL) {
		i4Ret = reloadImage(upgInterface, IMG_TYPE_BE, NULL, NULL);

		UPG_LOG(UPG_LOG_DEBUG, "[UPG]listPITForBE loadImage u4ImgStartAddr=0x%x, u4ImgSize=0x%x\n",
			u4pImgStartAddr, u4ImgSize);

		if  (i4Ret == UPGR_FAIL) {
			UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] listPITForBE loadImage failed\n");
			freeLoadImageStartAddr(&u4pImgStartAddr);
			return UPGR_FAIL;
		}
	}

	//find BIT and PIT	

	i4Ret = findBITFromFinalBinaryImage((UINT8 *) u4pImgStartAddr, u4ImgSize);
	if (i4Ret == UPGR_FAIL) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] listPITForBE findBITFromFinalBinaryImage failed\n");	
		freeLoadImageStartAddr(&u4pImgStartAddr);	
		return UPGR_FAIL;
	}	

	i4Ret = findPITFromFinalBinaryImage((UINT8 *) u4pImgStartAddr, u4ImgSize);
	if (i4Ret == UPGR_FAIL) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]listPITForBE findPITFromFinalBinaryImage failed\n");
		freeLoadImageStartAddr(&u4pImgStartAddr);
		return UPGR_FAIL;
	}

	//list PIT for BE image
	printf("=========Partition info table for backend image=========\n");
	printf("PartName         PartID  Offset            Size          \n");
	for (i = 0; (i < MAX_PARTITION_INFO_ENTRY_NUM) && 
			(binaryInfoTable[i].u4PartID != INVALID_PART_ID); i++) {
		INT32 i4PartID = binaryInfoTable[i].u4PartID;
		INT32 i4PartSize = getPartSize(i4PartID);
		INT32 i4PartOffset = getPartOffsetFromFlash(i4PartID);
		CHAR * szPartName = getPartName(i4PartID);

		printf("%-16s %02d      0x%08X        0x%08X\n", szPartName, i4PartID, i4PartOffset, i4PartSize);
	}
	

	//freeLoadImageStartAddr(&u4pImgStartAddr);//todo
	return i4Ret;
}

/*-----------------------------------------------------------------------------
 * Name: writePartBE
 *
 * Description: Write partial binary of backend image to nand flash by part id
 *
 * Inputs:    	upgInterface	Interface to load image such as usb, tftp
 *				upg_part_id		partition id
 * Outputs: -
 *
 * Returns: 	UPGR_OK			Write partial binary success
 *				UPGR_FAIL		Write partial binary failed
 ----------------------------------------------------------------------------*/
INT32 writePartBE(upg_interface_t upgInterface, INT32 i4PartID) {
	INT32 i4Ret = 0;
	INT32 i = 0;
//	UINT32 u4ImgSize = 0;
//	UINT32 *u4pImgStartAddr = 0;

	UPG_LOG(UPG_LOG_DEBUG, "[UPG]writePartBE loadImage u4ImgStartAddr=0x%x, u4ImgSize=0x%x\n",
			u4pImgStartAddr, u4ImgSize);

	if (u4pImgStartAddr == NULL) {		
		i4Ret = reloadImage(upgInterface, IMG_TYPE_BE, NULL, NULL);

		UPG_LOG(UPG_LOG_DEBUG, "[UPG]writePartBE loadImage u4ImgStartAddr=0x%x, u4ImgSize=0x%x\n",
			u4pImgStartAddr, u4ImgSize);

		if  (i4Ret == UPGR_FAIL) {
			UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] writePartBE loadImage failed\n");
			freeLoadImageStartAddr(&u4pImgStartAddr);
			return UPGR_FAIL;
		}
	}

	
	//find BIT and PIT	
	i4Ret = findBITFromFinalBinaryImage((UINT8 *) u4pImgStartAddr, u4ImgSize);
	if (i4Ret == UPGR_FAIL) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] writePartBE findBITFromFinalBinaryImage failed\n");	
		freeLoadImageStartAddr(&u4pImgStartAddr);	
		return UPGR_FAIL;
	}	

	i4Ret = findPITFromFinalBinaryImage((UINT8 *) u4pImgStartAddr, u4ImgSize);
	if (i4Ret == UPGR_FAIL) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]writePartBE findPITFromFinalBinaryImage failed\n");
		freeLoadImageStartAddr(&u4pImgStartAddr);
		return UPGR_FAIL;
	}


	//write partial binary to nand
	//Check part id
	i4Ret = isValidPartIDOfBIT(i4PartID);
	if (i4Ret != 0) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]writePartBE invalid part id: %d\n\t\
			Pls run \"upg_part list\" to get partition info table\n", i4PartID);
		//freeLoadImageStartAddr(&u4pImgStartAddr);
		return UPGR_FAIL;	
	}	

	i4Ret = upgFinalImgToNand(u4pImgStartAddr, u4ImgSize, i4PartID);

	if (i4Ret == UPGR_FAIL) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]writePartBE write binary to nand flash failed\n");
		return UPGR_FAIL;
	}

	//freeLoadImageStartAddr(&u4pImgStartAddr);//todo
	return i4Ret;
}


////////////////////////////////////////////FE Upgrade/////////////////////////
static INT32 i4OddSocMainCodeCheckSum(UINT8 *pbBuf, UINT32 u4BufLen)
{
    UINT32 u4CheckSum = 0;      
    UINT32 u4Temp = 0;
    INT32 i4Idx;
    INT32 i4Ret = 0;

    if (*((UINT32*)(pbBuf+0xC100)) != 0x00000000 && pbBuf[0xC008] >= 0x24 && u4BufLen >= 0xF0000)
    {
        UPG_LOG(UPG_LOG_INFO, "[ODD]======= Calculate FE firmware checksum =======\n");

        // Summation from 0x0 ~ 0x4000
        for (i4Idx = 0; i4Idx < 0x4000; i4Idx +=4)
        {
            u4Temp = ((UINT32)pbBuf[i4Idx+0]) << 24 |
                            ((UINT32)pbBuf[i4Idx+1]) << 16 |
                            ((UINT32)pbBuf[i4Idx+2]) << 8 |
                            ((UINT32)pbBuf[i4Idx+3]);

            u4CheckSum += u4Temp;
        }

        // Summation from 0x7C00 ~0xC000
        for (i4Idx = 0x7C00; i4Idx < 0xC000; i4Idx +=4)
        {
            u4Temp = ((UINT32)pbBuf[i4Idx+0]) << 24 |
                            ((UINT32)pbBuf[i4Idx+1]) << 16 |
                            ((UINT32)pbBuf[i4Idx+2]) << 8 |
                            ((UINT32)pbBuf[i4Idx+3]);

            u4CheckSum += u4Temp;
        }

        // Summation from 0x10000 ~0xF0000
        for (i4Idx = 0x10000; i4Idx < 0xF0000; i4Idx +=4)
        {
            u4Temp = ((UINT32)pbBuf[i4Idx+0]) << 24 |
                            ((UINT32)pbBuf[i4Idx+1]) << 16 |
                            ((UINT32)pbBuf[i4Idx+2]) << 8 |
                            ((UINT32)pbBuf[i4Idx+3]);

            u4CheckSum += u4Temp;
        }

        // Add 0xC100 (Big endian)
        u4Temp = ((UINT32)pbBuf[0xC100+0]) << 24 |
                        ((UINT32)pbBuf[0xC100+1]) << 16 |
                        ((UINT32)pbBuf[0xC100+2]) << 8 |
                        ((UINT32)pbBuf[0xC100+3]);

        u4CheckSum += u4Temp;

        if (u4CheckSum == ODD_SOC_FE_FW_CHECKSUM)
        {
            UPG_LOG(UPG_LOG_INFO, "[UPG] FE firmware CheckSum OK !!\n");
            i4Ret = 0;
        }
        else
        {
            UPG_LOG(UPG_LOG_ERROR, "[UPG] FE firmware CheckSum Failed : 0x%x !!\n", u4CheckSum);            
            i4Ret = -1;
        }
    }
    else
    {
        UPG_LOG(UPG_LOG_INFO, "[UPG] FE firmware without CheckSum..\n");
        i4Ret = 0;
    }

    return i4Ret;
}

INT32 i4OddSocVerifyMainCode(UINT8 *pbBuf, UINT32 u4BufLen)
{
    //ASSERT(pbBuf);

    if ((*(UINT32*)pbBuf == 0x00000000) || (*(UINT32*)pbBuf == 0xFFFFFFFF))	// check binary size
    {
        return (-1);
    }
    
    // Check F/E Firmware's CheckSum
    if (i4OddSocMainCodeCheckSum(pbBuf, u4BufLen) != 0)
    {
        return (-1);
    }

    return 0;
}   


BOOL fg_chk_loader_bin(UINT32 u4ImgSA)
{
  UINT32 u4FlagAddr = u4ImgSA + LOADER_FLAG_OFFSET;
  if((*(UINT32*)(u4FlagAddr)!=LOADER_HEAD_SIG) ||
     (*(UINT32*)(u4FlagAddr+0x4)!=LOADER_TAIL_SIG))
  {
    return (FALSE);
  }

  return TRUE;
}


/*-----------------------------------------------------------------------------
 * Name: findPITFromNand
 *
 * Description: Find partition info table from nand flash
 *
 * Inputs:    	pitTable		PIT read from nand flash
 * Outputs: -
 *
 * Returns: 	UPGR_OK			Find PIT success
 *				UPGR_FAIL		Find PIT failed
 ----------------------------------------------------------------------------*/

INT32 findPITFromNand(PARTITION_INFO_ENTRY pitTable[]) {
	INT32 i = 0;
	INT32 offset = 0;
	INT32 i4Ret = 0;
	
	UINT32 u4PITHeadSigAddr = 0;
	UINT32 u4PITTailSigAddr = 0;
	INT8* u4pImgStartAddr = 0;

    #if UPG_SUPPORT_NEW_PIT_FORMAT
    UINT32 u4PitHeaderLen = 0;
    UINT32 u4PitItemLen = 0;
    UINT32 u4PitItemNum = 0;
    #endif

	u4pImgStartAddr = malloc(UPG_LOADER_IMAGE_BUFFER_SIZE);
	if (u4pImgStartAddr == NULL) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] findPITFromNand malloc memory failed\n");
		return UPGR_FAIL;
	} else {	
		UPG_LOG(UPG_LOG_DEBUG, "[UPG]findPITFromNand malloc memory addr:0x%X\n", u4pImgStartAddr);
	}

	for	(offset = 0; offset < UPG_MAX_SEARCH_SIZE; offset += UPG_LOADER_IMAGE_BUFFER_SIZE) {
		i4Ret = readDataFromNand(u4pImgStartAddr, offset, UPG_LOADER_IMAGE_BUFFER_SIZE);

		if (i4Ret != UPGR_OK) {
			UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] findPITFromNand read nand failed\n");
			return UPGR_FAIL;
		}
		#if !UPG_SUPPORT_NEW_PIT_FORMAT
		for (i = 0; i < UPG_LOADER_IMAGE_BUFFER_SIZE; i += PIT_SEARCH_STEP) {
			if ( (*(UINT32 *) (u4pImgStartAddr + i) == PITHEADSIG) &&
				(*(UINT32 *) (u4pImgStartAddr + i + 4) == PITHEADSIG) &&
				(*(UINT32 *) (u4pImgStartAddr + i + 8) == PITHEADSIG) &&
				(*(UINT32 *) (u4pImgStartAddr + i + 12) == PITHEADSIG) &&
				(*(UINT32 *) (u4pImgStartAddr + i + 16) == PITHEADSIG) &&
				(*(UINT32 *) (u4pImgStartAddr + i + 20) == PITHEADSIG) &&
				(*(UINT32 *) (u4pImgStartAddr + i + 24) == PITHEADSIG) &&
				(*(UINT32 *) (u4pImgStartAddr + i + 28) == PITHEADSIG) ) {			
				UPG_LOG(UPG_LOG_DEBUG, "[UPG]findPITFromNand get pit head addr:0x%X\n", (u4pImgStartAddr + i)); 
				u4PITHeadSigAddr = u4pImgStartAddr + i;
				break;
			}
		}
		#else
		for (i = 0; i < UPG_LOADER_IMAGE_BUFFER_SIZE; i += 16) {
			if ( (*(UINT32 *) (u4pImgStartAddr + i) == PITHEADSIG) &&
				 (*(UINT32 *) (u4pImgStartAddr + i + 4) == PITHEADSIG) ) {	

                   if((*(UINT32 *) (u4pImgStartAddr + i + 8) == PITHEADSIG) &&
                      (*(UINT32 *) (u4pImgStartAddr + i + 12) == PITHEADSIG) &&
                      (*(UINT32 *) (u4pImgStartAddr + i + 16) == PITHEADSIG) &&
                      (*(UINT32 *) (u4pImgStartAddr + i + 20) == PITHEADSIG) &&
                      (*(UINT32 *) (u4pImgStartAddr + i + 24) == PITHEADSIG) &&
                      (*(UINT32 *) (u4pImgStartAddr + i + 28) == PITHEADSIG) )
                   {
                       _u4PitTableVersion = PITHEADSIG;
                       UPG_LOG(UPG_LOG_DEBUG, "[UPG]findPITFromNand get pit head addr(old pit format):0x%X\n", (u4pImgStartAddr + i)); 
                       u4PITHeadSigAddr = u4pImgStartAddr + i;
                       break;					
                   }
                   else if((*(UINT32 *) (u4pImgStartAddr + i + 8)) == 1)
                   {
                       _u4PitTableVersion = 1;
                       u4PitHeaderLen = (*(UINT32 *) (u4pImgStartAddr + i + 12));
                       u4PitItemLen = (*(UINT32 *) (u4pImgStartAddr + i + 16));
                       u4PitItemNum = (*(UINT32 *) (u4pImgStartAddr + i + 20));
                       UPG_LOG(UPG_LOG_DEBUG, "[UPG]findPITFromNand get pit head addr(new pit format):0x%X\n", (u4pImgStartAddr + i)); 
                       u4PITHeadSigAddr = u4pImgStartAddr + i;
                       u4PITTailSigAddr = u4PITHeadSigAddr + u4PitHeaderLen + u4PitItemLen*u4PitItemNum;
                       break;	
                   }
				
			}
		}
		#endif

        #if UPG_SUPPORT_NEW_PIT_FORMAT
        if(_u4PitTableVersion == PITHEADSIG)
        {
        #endif
		for (i = 0; i < UPG_LOADER_IMAGE_BUFFER_SIZE; i += PIT_SEARCH_STEP) {
			if ( (*(UINT32 *) (u4pImgStartAddr + i) == PITTAILSIG) &&
				(*(UINT32 *) (u4pImgStartAddr + i + 4) == PITTAILSIG) &&
				(*(UINT32 *) (u4pImgStartAddr + i + 8) == PITTAILSIG) &&
				(*(UINT32 *) (u4pImgStartAddr + i + 12) == PITTAILSIG) &&
				(*(UINT32 *) (u4pImgStartAddr + i + 16) == PITTAILSIG) &&
				(*(UINT32 *) (u4pImgStartAddr + i + 20) == PITTAILSIG) &&
				(*(UINT32 *) (u4pImgStartAddr + i + 24) == PITTAILSIG) &&
				(*(UINT32 *) (u4pImgStartAddr + i + 28) == PITTAILSIG) ) {
			
				UPG_LOG(UPG_LOG_DEBUG, "[UPG]findPITFromNand get pit tail addr:0x%X\n", (u4pImgStartAddr + i));	
				u4PITTailSigAddr = u4pImgStartAddr + i;
				break;
			}
		}
        #if UPG_SUPPORT_NEW_PIT_FORMAT
        }
        #endif


		if (u4PITHeadSigAddr == NULL || u4PITTailSigAddr == NULL) {
			//UPG_LOG(UPG_LOG_DEBUG, "[UPG]upg_main.c findPITFromFinalBinaryImage failed:not found PIT head or tail sig\n");
			continue;
		}

        UPG_LOG(UPG_LOG_DEBUG, "[UPG]findPITFromNand u4PITHeadSigAddr=0x%X u4PITTailSigAddr=0x%X\n",
                               u4PITHeadSigAddr, u4PITTailSigAddr);

        #if UPG_SUPPORT_NEW_PIT_FORMAT
        if(_u4PitTableVersion == PITHEADSIG)
        {
        #endif
		for (i = 1; ((u4PITHeadSigAddr + i * PIT_ENTRY_SIZE) < u4PITTailSigAddr); i++) {
			CHAR * szName = u4PITHeadSigAddr + (i * PIT_ENTRY_SIZE);
			UINT32 u4PartID = *(UINT32 *) (u4PITHeadSigAddr + (i * PIT_ENTRY_SIZE) + 16);
			UINT32 u4PartInfo = *(UINT32 *) (u4PITHeadSigAddr + (i * PIT_ENTRY_SIZE) + 20);
			UINT32 u4OffsetFromFlash = *(UINT32 *) (u4PITHeadSigAddr + (i * PIT_ENTRY_SIZE) + 24);
			UINT32 u4PartSize = *(UINT32 *) (u4PITHeadSigAddr + (i * PIT_ENTRY_SIZE) + 28);
			UPG_LOG(UPG_LOG_INFO, "[UPG] findPITFromNand[i=%2d, name=%16s, partid=%2d, partinfo=%2d, offset=0x%8X, size=0x%8X\n",
				i, szName, u4PartID, u4PartInfo, u4OffsetFromFlash, u4PartSize);
						
			strncpy(pitTable[i-1].szName, szName, 16);		
			pitTable[i-1].u4PartID = u4PartID;
			pitTable[i-1].u4PartInfo = u4PartInfo;
			pitTable[i-1].u4OffsetFromFlash = u4OffsetFromFlash;
			pitTable[i-1].u4PartSize = u4PartSize;
		}
            break; //got pit         
        #if UPG_SUPPORT_NEW_PIT_FORMAT
        }
        #endif
        #if UPG_SUPPORT_NEW_PIT_FORMAT		
        else if(_u4PitTableVersion == 1)
        {
            for (i = 0; i< u4PitItemNum; i++) {
                CHAR * szName = u4PITHeadSigAddr + u4PitHeaderLen + (i * u4PitItemLen);
                UINT32 u4PartID = *(UINT32 *) (u4PITHeadSigAddr + u4PitHeaderLen + (i * u4PitItemLen) + 16);
                UINT32 u4PartInfo = *(UINT32 *) (u4PITHeadSigAddr + u4PitHeaderLen + (i * u4PitItemLen) + 20);
                UINT32 u4OffsetFromFlash = *(UINT32 *) (u4PITHeadSigAddr + u4PitHeaderLen + (i * u4PitItemLen) + 24);
                UINT32 u4PartSize = *(UINT32 *) (u4PITHeadSigAddr + u4PitHeaderLen + (i * u4PitItemLen) + 28);
                UPG_LOG(UPG_LOG_DEBUG, "[UPG]findPITFromFinalBinaryImage[i=%2d,name=%16s,partid=%2d,partinfo=%2d,offset=0x%8X,size=0x%8X\n",
                                       i, szName, u4PartID, u4PartInfo, u4OffsetFromFlash, u4PartSize);
		
                strncpy(pitTable[i].szName, szName, 16);		
                pitTable[i].u4PartID = u4PartID;
                pitTable[i].u4PartInfo = u4PartInfo;
                pitTable[i].u4OffsetFromFlash = u4OffsetFromFlash;
                pitTable[i].u4PartSize = u4PartSize;
            }
		break; //got pit
	}
        //else
        //{
        //}
        #endif       
    }
	   

	if (u4PITHeadSigAddr == NULL || u4PITTailSigAddr == NULL) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]findPITFromNand failed:not found PIT head and tail sig\n");
		free(u4pImgStartAddr);
		return UPGR_FAIL;
	}

	free(u4pImgStartAddr);
	
	return UPGR_OK;
}


INT32 getFEPartsInfo(INT32 *u4FEPartID, INT32 *u4FEPartOffset, INT32 *u4FEPartSize,
		INT32 *u4FEBakPartID, INT32 *u4FEBakPartOffset, INT32 *u4FEBakPartSize,
		INT32 *u4FETestPartID, INT32 *u4FETestPartOffset, INT32 *u4FETestPartSize) {

	INT32 i = 0;
	for (i = 0; (i < MAX_PARTITION_INFO_ENTRY_NUM) && 
			(partInfoTableFlash[i].u4PartID != INVALID_PART_ID); i++) {
		CHAR * szName = partInfoTableFlash[i].szName;
		if (strcmp(szName, FE_PART_NAME) == 0) {
			*u4FEPartID = partInfoTableFlash[i].u4PartID;
			*u4FEPartOffset = partInfoTableFlash[i].u4OffsetFromFlash;
			*u4FEPartSize = partInfoTableFlash[i].u4PartSize;
		} else if (strcmp(szName, FE_BAK_PART_NAME) == 0) {
			*u4FEBakPartID = partInfoTableFlash[i].u4PartID;
			*u4FEBakPartOffset = partInfoTableFlash[i].u4OffsetFromFlash;
			*u4FEBakPartSize = partInfoTableFlash[i].u4PartSize;
		} else if (strcmp(szName, FE_FA_PART_NAME) == 0) {
			*u4FETestPartID = partInfoTableFlash[i].u4PartID;
			*u4FETestPartOffset = partInfoTableFlash[i].u4OffsetFromFlash;
			*u4FETestPartSize = partInfoTableFlash[i].u4PartSize;
		} else {
			continue;
		}
		
	}

	if (*u4FEPartID < 0 || *u4FEPartOffset < 0 || *u4FEPartSize < 0
		|| *u4FEBakPartID < 0 || *u4FEBakPartOffset < 0 || *u4FEBakPartSize < 0
		|| *u4FETestPartID < 0 || *u4FETestPartOffset < 0 || *u4FETestPartSize < 0) {

		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]FE parts(fe, fe_bak, fe_test) info is invalid\n");
		return UPGR_FAIL;
	}

	return UPGR_OK;
}


INT32 checkFEImgAndParts(u4ImgSize, u4FEPartSize, u4FEBakPartSize) {
	if (u4ImgSize > u4FEPartSize ) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] FE image size[0x%x] > FE Part size[0x%x]\n",
			u4ImgSize, u4FEPartSize, u4FEBakPartSize);
		return UPGR_FAIL;
	}

	if ( u4ImgSize > u4FEBakPartSize) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] FE image size[0x%x] >  FE Bak Part size[0x%x]\n",
			u4ImgSize, u4FEPartSize, u4FEBakPartSize);
		return UPGR_OK;
	}//for 64m nand flash

	return UPGR_OK;
}

/*-----------------------------------------------------------------------------
 * Name: fgFEBakExist
 *
 * Description: This API chech whether MTD_LOADER_BACKUP image  exist in nand flash
 *
 * Inputs:    	None
 *
 * Outputs: -	None
 *
 * Returns: 	TRUE			FE bak image exist in nand flash
 *				FALSE			FE bak  image  not exist in nand flash
 ----------------------------------------------------------------------------*/
BOOL fgFEBakExist(UINT32 partId, UINT32 partOffset, UINT32 partSize, UINT32 u4PartID, UINT32 u4OffsetFromPart)
{

	INT8* u4pImgStartAddr = 0;
	INT32 i4Ret = 0;
	UINT32 u4Value;
  	UINT32 u4CheckDword = 4;
	INT32 i = 0;

	u4pImgStartAddr = malloc(partSize);
	if (u4pImgStartAddr == NULL) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] fgFEBakExist malloc memory failed\n");
		return FALSE;
	} else {
		UPG_LOG(UPG_LOG_DEBUG, "[UPG]fgFEBakExist malloc memory addr:0x%X\n", u4pImgStartAddr);
	}
         //for  read mode change
	//i4Ret = readDataFromNand((UINT32*) u4pImgStartAddr, partOffset, partSize);
        i4Ret = readDataFromNandByPar((UINT32*) u4pImgStartAddr, u4OffsetFromPart, partSize, u4PartID );
	if (i4Ret != UPGR_OK) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] read FE bak image from nand failed");
		free(u4pImgStartAddr);
		return FALSE;
	}

  for (i = 0; i < u4CheckDword; i++)
  {
    u4Value = *(UINT32*)(u4pImgStartAddr+ i * 4);	
	UPG_LOG(UPG_LOG_DEBUG, "fgFEBakExist u4Value=0x%x\n", u4Value);
    if ((u4Value != 0x00000000) && (u4Value != 0xFFFFFFFF))
    {
      //exist loader backup image.
      UPG_LOG(UPG_LOG_INFO, "fgFEBakExist return TRUE\n");
	  free(u4pImgStartAddr);
      return (TRUE);
    }
  }

  free(u4pImgStartAddr);
  //not exist loader backup image
  return (FALSE);
}


/*-----------------------------------------------------------------------------
 * Name: upgFEImge
 *
 * Description: Upgrade backend image(loader binary) such as DVD.bin
 *
 * Inputs:    	upgInterface	Upgrade interface(USB, TFTP)
 * Outputs: -
 *
 * Returns: 	UPGR_OK			Upgrade success
 *				UPGR_FAIL		Upgrade failed
 ----------------------------------------------------------------------------*/

INT32 upgFEImage(upg_interface_t upgInterface) {
//	UINT32 u4ImgSize = 0;
//	UINT32 *u4pImgStartAddr = 0;
	INT32 i4Ret = UPGR_OK;
	INT32 u4FEPartID = -1;
	INT32 u4FEPartOffset = 0;
	INT32 u4FEPartSize = 0;
	
	INT32 u4FEBakPartID = -1;
	INT32 u4FEBakPartOffset = 0;
	INT32 u4FEBakPartSize = 0;
	
	INT32 u4FETestPartID = -1;
	INT32 u4FETestPartOffset = 0;
	INT32 u4FETestPartSize = 0;
    INT32 i = 0;
    #if UPG_SUPPORT_NEW_FE_UPG
    //add for new fe upg flow
	CHAR* szUpgUSBPath;
	CHAR* szUpgImgSearchFileName = "fe_upg.id";
	CHAR* szUpgImgName = NULL;
	UINT32 u4UpgFePartId = 0;
	#if CFG_UPG_SUPPORT_JIGMODE
            CHAR* szUpgUSBPathFW;
	#endif
	#endif

	//1 load image
	i4Ret = reloadImage(upgInterface, IMG_TYPE_FE, NULL, NULL);
	
	UPG_LOG(UPG_LOG_DEBUG, "[UPG]upgFEImage loadImage u4ImgStartAddr=0x%x, u4ImgSize=0x%x\n",
		u4pImgStartAddr, u4ImgSize);

	if	(i4Ret == UPGR_FAIL) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] upgFEImage loadImage failed\n");
		freeLoadImageStartAddr(&u4pImgStartAddr);
		return UPGR_FAIL;
	}	

	//2 Check image
#if 0	
	if (!fg_chk_loader_bin(u4pImgStartAddr)) {
#else		
	if (i4OddSocVerifyMainCode(u4pImgStartAddr, u4ImgSize) != 0) {
#endif		
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] Loaded image is not a valid FE image\n");
		return UPGR_FAIL;
	}

	//3. Find PIT from nand flash
	initPITFlash();
	
	i4Ret = findPITFromNand(partInfoTableFlash);
	if (i4Ret != UPGR_OK) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] Find PIT from nand failed, pls upgrade Backend image firtly\n");	
		freeLoadImageStartAddr(&u4pImgStartAddr);
		return UPGR_FAIL;
	}
    #if UPG_SUPPORT_NEW_FE_UPG
	//4. Check image, if image size > 1M, it is a new format, otherwise it is old format
    szUpgImgName =  malloc(256 * sizeof(CHAR));
    memset((void*)szUpgImgName, 0, 256);
    if (szUpgImgName == NULL)
    {
      UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]UPG FE, maloc memory for search file failed.\n");
      return UPGR_FAIL;
    }		
    if(u4ImgSize > 1024*1024)// new format
    {
       //check if file fe_upg.id exist
       szUpgUSBPath = szCurrentWorkUsbPath;
       i4Ret = usbFindFile(szUpgUSBPath, szUpgImgSearchFileName, szUpgImgName);
       if (i4Ret == UPGR_FAIL)//upg first part 
       {
          u4UpgFePartId = 0;
          u4ImgSize = 1024*1024;
       }
       else
       {
          CHAR* szFullPath = malloc(1024 * sizeof(CHAR));
          CHAR* szFileData = malloc(1024 * sizeof(CHAR));
          UINT32 u4ReadFileSize = 0;
          if (szFullPath == NULL) {
            UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]loadImage maloc memory failed.\n");
            return UPGR_FAIL;
          }
          *szFullPath = '\0';
          
          szFullPath = catFilePathName(szFullPath, szUpgUSBPath, szUpgImgSearchFileName);
          if (szFullPath == NULL) {
             UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]loadImage full path is NULL\n");
             return UPGR_FAIL;
          }
          UPG_LOG(UPG_LOG_DEBUG, "[UPG](loadImage->load fe_upg.id ) full path:%s\n", szFullPath);
          
          
          i4Ret = usbGetImage(szFullPath, szFileData, &u4ReadFileSize);
          u4UpgFePartId = *(szFileData) - 0x30;
          if(u4UpgFePartId > 2)
            UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR](fe_upg.id -> id) = %d .\n",u4UpgFePartId);
          u4pImgStartAddr += (1024*1024*u4UpgFePartId)/4;
          u4ImgSize = 1024*1024;
          UPG_LOG(UPG_LOG_DEBUG, "[UPG]upgFEImage update imgstartAddr and size, u4ImgStartAddr=0x%x, u4ImgSize=0x%x\n",
                u4pImgStartAddr, u4ImgSize);
          free(szFullPath);
          free(szFileData);
       
       }
    }
    if(szUpgImgName != NULL)
      free(szUpgImgName);
	#endif
    //set env and create pit table
    //set ubi enviornment
   memcpy((void *) (partInfoTable),( void * )(partInfoTableFlash),sizeof(PARTITION_INFO_ENTRY)*(MAX_PARTITION_INFO_ENTRY_NUM-1));
   {
    i4Ret =  i4SetUBIMountEnv(partInfoTableFlash);
    if(-1 == i4Ret)
    {
      UPG_LOG(UPG_LOG_DEBUG, "[UPG_ERROR] Upgrade FE image failed, set partition info env fail,ret:%d\n", i4Ret);
      return UPGR_FAIL;
    }    
   }
	for (i = 0; (i < MAX_PARTITION_INFO_ENTRY_NUM) && 
         (partInfoTableFlash[i].u4PartID != INVALID_PART_ID); i++) 
    {
       if( ISUBIVOLUME(partInfoTableFlash[i].u4PartInfo) != 4 )
       {
         if(mt85xx_init_mtd_table(partInfoTableFlash[i].szName) != 0)	
         {
           UPG_LOG(UPG_LOG_ERROR, "[mtd_table init]%s not be added!\n", partInfoTableFlash[i].szName);	
           return UPGR_FAIL;
         }
       }
    }
	//Get FE parts info
	i4Ret = getFEPartsInfo(&u4FEPartID, &u4FEPartOffset, &u4FEPartSize,
		&u4FEBakPartID, &u4FEBakPartOffset, &u4FEBakPartSize,
		&u4FETestPartID, &u4FETestPartOffset, &u4FETestPartSize);
	
	//Check image size and partition size
	i4Ret = checkFEImgAndParts(u4ImgSize, u4FEPartSize, u4FEBakPartSize);
	if (i4Ret != UPGR_OK) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] FE image size is not matched with partition size\n");
		freeLoadImageStartAddr(&u4pImgStartAddr);
		return UPGR_FAIL;
	}

    i4Ret = i_set_upg_status_by_upg(US_PROGRESS_UPG_FE);
    if(i4Ret != UPG_STATUS_OK)
    {
       UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]i_set_upg_status_by_upg failed\n");	
       return UPGR_FAIL; 
    }
	
	//Update image to nand partition
	UPG_LOG(UPG_LOG_INFO, "[UPG] Write FE image to FE part (partid=%d, offset=0x%x, size=0x%x)\n",
		u4FEPartID, u4FEPartOffset, u4FEPartSize);
	i4Ret = writeDataToNand(u4pImgStartAddr, u4ImgSize, u4FEPartID,
		0, u4FEPartOffset, u4FEPartSize);
	if (i4Ret != UPGR_OK) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] Write FE image to nand failed\n");
		freeLoadImageStartAddr(&u4pImgStartAddr);
		return UPGR_FAIL;
	}
	

	//check backup FE is exist
    if(u4FEBakPartSize != 0)
    {
       if (!fgFEBakExist(u4FEBakPartID, u4FEBakPartOffset, u4FEBakPartSize, u4FEBakPartID, 0)) 
       {
          UPG_LOG(UPG_LOG_INFO, "[UPG] Write FE image to FE Bak part (partid=%d, offset=0x%x, size=0x%x)\n",
                                 u4FEBakPartID, u4FEBakPartOffset, u4FEBakPartSize);
          i4Ret = writeDataToNand(u4pImgStartAddr, u4ImgSize, u4FEBakPartID,
          0, u4FEBakPartOffset, u4FEBakPartSize);
          if (i4Ret != UPGR_OK) 
          {
             UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] Write FE bak image to nand failed\n");
             freeLoadImageStartAddr(&u4pImgStartAddr);
             return UPGR_FAIL;
          }
       } 
       else 
       {
          UPG_LOG(UPG_LOG_INFO, "[UPG] FE bak image exist\n");
       }
    }

	//erase FA
	UPG_LOG(UPG_LOG_INFO, "[UPG] Erase FE FA (partid=%d, offset=0x%x, size=0x%x)\n",
		u4FETestPartID, u4FETestPartOffset, u4FETestPartSize);
	i4Ret = eraseDataFromNand(u4FETestPartOffset, u4FETestPartSize, u4FETestPartID);
	if (i4Ret != UPGR_OK) {
		UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR] Erase FE FA failed\n");
		freeLoadImageStartAddr(&u4pImgStartAddr);
		return UPGR_FAIL;
	}
	
	freeLoadImageStartAddr(&u4pImgStartAddr);

    i4Ret = i_set_upg_status_by_upg(US_PROGRESS_UPG_SUC);
    if(i4Ret != UPG_STATUS_OK)
    {
       UPG_LOG(UPG_LOG_ERROR, "[UPG_ERROR]i_set_upg_status_by_upg failed\n");	
       return UPGR_FAIL; 
    }
	
	return i4Ret;
}

INT32 i_set_upg_status_by_upg(UINT32 upg_status)
{
    INT32 i;
    void* pu1_buf;
	void *pu4_buf;
	UINT32 u4BlockSize;
	INT32 r;
	
    if(upg_status_part_Info == NULL)
    {
        for (i = 0; (i < MAX_PARTITION_INFO_ENTRY_NUM) && 
			       (partInfoTable[i].u4PartID != INVALID_PART_ID); i++) 
		{
			if(strstr(getPartName(partInfoTable[i].u4PartID), UPG_STATUS_PART_NAME) != NULL)
			{
			    upg_status_part_Info = &(partInfoTable[i]);
				break;
			}
   	    }
    }

	/* get block size */
	u4BlockSize = nand_info[nand_curr_device].erasesize;
	if(u4BlockSize == 0)
	{
	    puts("[i_set_upg_status_by_upg] Get block size FAIL.\n");
	    return -ERR_BLOCK_SIZE_ZERO;
	}
	
    /* get memory buffer */
	pu1_buf = malloc((size_t)((u4BlockSize*2) + 4));
	if(pu1_buf == NULL)
	{
	    puts("[i_set_upg_status_by_upg] malloc FAIL.\n");
		return -ERR_MALLOC_FAIL;
	}
	pu4_buf = (void*)(((UINT32)pu1_buf + 4)&0xFFFFFFFC);

    if(!fg_is_upg_status_inited(&pr_upg_status))
    {
    	UINT32 u4_pri_count = 0;
        PART_TBL_ITEM pr_part_item;
		
        /* gen upg_status */
    	memset(pr_upg_status, 0, sizeof(UPG_STATUS_T));		
    	pr_upg_status.u4_head_sig = UPG_STATUS_HEAD_SIG;
    	pr_upg_status.u4_progress = US_PROGRESS_UPG_SUC;
    	pr_upg_status.u4_end_sig  = UPG_STATUS_END_SIG;

        pr_upg_status.u4_cec_la        = r_upg_status.u4_cec_la;
		pr_upg_status.u4_cec_pa        = r_upg_status.u4_cec_pa;
		pr_upg_status.u4_cec_switch    = r_upg_status.u4_cec_switch;
		pr_upg_status.u4_cec_trad_mode = r_upg_status.u4_cec_trad_mode;
		pr_upg_status.u4_log_cfg       = r_upg_status.u4_log_cfg;
		pr_upg_status.u4_sweety_switch = r_upg_status.u4_sweety_switch;
		
    	for (i = 0; (i < MAX_PARTITION_INFO_ENTRY_NUM) && 
			        (partInfoTable[i].u4PartID != INVALID_PART_ID); i++) 
    	{
    	    pr_part_item.u4_info = partInfoTable[i].u4PartInfo;
			if(fg_is_primary_part(&pr_part_item) == TRUE)	
    	    {
    	        pr_upg_status.au4_active_id_list[u4_pri_count++] = partInfoTable[i].u4PartID;
    	    }
    	}
    
    	pr_upg_status.u4_active_id_list_nr  = u4_pri_count;
    
    	/* boot args */
    	pr_upg_status.u4_end_boot_sig =  UPG_STATUS_END_BOOT_SIG;
    }

    pr_upg_status.u4_progress = (pr_upg_status.u4_progress & ~US_PROGRESS_MASK) | upg_status;


    if(sizeof(UPG_STATUS_T) > ((u4BlockSize/4) * sizeof(UINT32)))
    {
        puts("[i_set_upg_status_by_upg] upg_status size[0x%08x] > raw data buffer size[0x%08x]\n", 
			    sizeof(UPG_STATUS_T), ((u4BlockSize/4) * sizeof(UINT32)));
		free(pu1_buf);
		return -ERR_BUF_SZ;
    }
	
	/* set 1st data block */	
	memcpy((void *)pu4_buf, (void *)&pr_upg_status, sizeof(UPG_STATUS_T));
    /* set 2nd data block */
    memcpy((void *)(pu4_buf + u4BlockSize), (void *)&pr_upg_status, sizeof(UPG_STATUS_T));

    /* write into nand */
    r = writeDataToNand((UINT32 *)pu4_buf, (u4BlockSize*2), upg_status_part_Info->u4PartID, 0, 
		                upg_status_part_Info->u4OffsetFromFlash, upg_status_part_Info->u4PartSize);	
	if(r != 0)
	{
	    puts("[i_set_upg_status_by_upg] nand write FAIL.\n");
		free(pu1_buf);
	    return -ERR_NAND_WRITE_FAIL;
	}

	free(pu1_buf);
	return UPG_STATUS_OK;
}

