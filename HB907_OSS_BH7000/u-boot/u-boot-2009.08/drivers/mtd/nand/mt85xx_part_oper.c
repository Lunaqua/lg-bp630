/*
 * mt85xx partition operation functions
 */
#ifndef MT85XX_PART_OPER_C
#define MT85XX_PART_OPER_C


/*
 * header file
 */
#include <common.h>
#include <nand.h>
#include <malloc.h>
#include <asm/arch/x_typedef.h>

#include <asm/errno.h>
#include <asm/io.h>

#include <linux/mtd/mtd.h>
#include <linux/mtd/nand.h>
#include <linux/mtd/nand_ecc.h>
#include <linux/bitops.h>
#include <asm/arch/mt85xx_part_tbl.h>
#include "linux/mtd/mt85xx_part_oper.h"
#include <linux/mtd/mtd.h>
#include <linux/mtd/partitions.h>
#include <linux/err.h>

/* proc */

#define MAX_PAGE_SIZE	4096
#define ISUBIVOLUME(binInfo) ((0x0000F000 & binInfo)>> 12) 	//bit[1512]

#define ASSERT(e) \
        if(!(e)){ \
          while(1); \
        }
#define NAND_LOG_ERROR 0
#define NAND_LOG_INFO  1
#define NAND_LOG_DEBUG 2
INT32 NAND_LOG_LEVEL = NAND_LOG_INFO; //default log level

void NAND_LOG(int level, char *s, ...)
{
  if(level<= NAND_LOG_LEVEL)
  {
	va_list args;
	int i;
	char printbuffer[400];
	char *log="[NAND]";

	va_start (args, log);

	/* For this to work, printbuffer must be larger than
	 * anything we ever want to print.
	 */
	i = vsprintf (printbuffer, log, args);
	va_end (args);
	/* Print the string */
	puts (printbuffer);
   /*////////////////////*/
	va_start (args, s);

	/* For this to work, printbuffer must be larger than
	 * anything we ever want to print.
	 */
	i = vsprintf (printbuffer, s, args);
	va_end (args);
	/* Print the string */
	puts (printbuffer);
  }
}
UINT32 get_part_id_by_name(const char* part_name)
{
  PART_TBL_ITEM *r_part_tbl;
  if (part_name == NULL)
  {
    printk("get_part_id_by_name,name is NULL\n");
    return 0;
  }
  r_part_tbl = mt85xx_get_part_info_by_name(part_name);
  return r_part_tbl->u4_id;
}
UINT32 get_part_info_by_name(const char* part_name)
{
  PART_TBL_ITEM *r_part_tbl;
  if (part_name == NULL)
  {
    printk("get_part_info_by_name,name is NULL\n");
    return 0;
  }
  r_part_tbl = mt85xx_get_part_info_by_name(part_name);
  return r_part_tbl->u4_info;
}
UINT32 get_part_addr_by_name(const char* part_name)
{
  PART_TBL_ITEM *r_part_tbl;
  if (part_name == NULL)
  {
    printk("get_part_addr_by_name,name is NULL\n");
    return 0;
  }
  r_part_tbl = mt85xx_get_part_info_by_name(part_name);
  return r_part_tbl->u4_offset;
}
UINT32 get_part_size_by_name(const char* part_name)
{
  PART_TBL_ITEM *r_part_tbl;
  if (part_name == NULL)
  {
    printk("get_part_size_by_name,name is NULL\n");
    return 0;
  }
  r_part_tbl = mt85xx_get_part_info_by_name(part_name);
  return r_part_tbl->u4_size;
}
UINT32 get_page_size(void)
{
  struct mtd_info *mtd;
  mtd = get_mtd_device(NULL, 0);
  return mtd->writesize;
}
UINT32 get_block_size(void)
{
  struct mtd_info *mtd;
  mtd = get_mtd_device(NULL, 0);
  return mtd->erasesize;
}
static void erase_buffer(void *buffer, size_t size)
{
	const uint8_t kEraseByte = 0xff;
	if (buffer != NULL && size > 0) 
	{
		memset(buffer, kEraseByte, size);
	}
}

#if ADAPT_MTD_LINUX
enum PIT_VER_T _pit_ver=V_ERR;
UINT32 *orig_pit=NULL;
UINT32 *adpt_pit=NULL;
UINT32 _u4_orig_part_nr=0;
UINT32 _u4_same=0;

UINT32 u4GetPartNum(UINT32 pit, UINT32 u4HeadSig, UINT32 u4TailSig)
{
  UINT32 i=0;
  V1_PIT_HEADER_T *v1part=NULL;
  PART_TBL_ITEM *v0Part=NULL;
  INT32 pitver=i4CheckPITVer((UINT32*)pit,u4HeadSig);
  UINT32 len=0;
  if(pitver == V0)
  {
	for(i=0; i<PART_TBL_ITEM_NR_MAX; i++)
	{
	  v0Part = (PART_TBL_ITEM*)(pit+i*sizeof(PART_TBL_ITEM));
	  if((v0Part->u4_id     != u4TailSig) ||
	  	 (v0Part->u4_info   != u4TailSig) ||
	  	 (v0Part->u4_offset != u4TailSig) ||
	  	 (v0Part->u4_size   != u4TailSig) )
	  	{
	  	  continue;
	  	}
	    /*end sig found*/
		len = i;
	}
  }
  else if(pitver == V1)
  {
    v1part = (V1_PIT_HEADER_T*)pit;
	len = v1part->u4_item_num;
  }
  else
  {
    len = 0;
  }
  NAND_LOG(NAND_LOG_DEBUG,"pit part num 0x%x\n", len);
  return len;
}

UINT32 u4GetItemLen(UINT32 pit, UINT32 u4HeadSig, UINT32 u4TailSig)
{
	V1_PIT_HEADER_T *v1part=NULL;
	INT32 pitver=i4CheckPITVer((UINT32*)pit,u4HeadSig);
    UINT32 len=0;
	
    if(pitver == V0)
    {
      len = sizeof(PART_TBL_ITEM);
    }
	else if (pitver == V1)
	{
	  v1part=(V1_PIT_HEADER_T*)pit;
	  len = v1part->u4_item_len;
	}
	else
	{
	  len = 0;
	}
	
	NAND_LOG(NAND_LOG_DEBUG,"pit item len 0x%x\n", len);
    return len;
}


INT32 u4SetPartHead(UINT32 pit, UINT32 pitver, UINT32 u4HeadSig)
{
  UINT32 *phead = (UINT32*)pit;
  int i=0;
  if (pitver == V0)
  {
    for (i=0; i<8; i++)
    {
      *(phead+i) = u4HeadSig;
    }
  }
  else if (pitver == V1)
  {
    *phead = u4HeadSig;
    *(phead+1) = u4HeadSig;
  }
  else
  {
    NAND_LOG(NAND_LOG_ERROR,"set part head failed!\n");
    return -1;
  }
  NAND_LOG(NAND_LOG_DEBUG,"set part head success!\n");
  return 0;
}
INT32 u4SetPartTail(UINT32 pit, UINT32 pitlen, UINT32 pitver, UINT32 u4TailSig)
{
  UINT32 *ptail = (UINT32*)(pit + sizeof(PART_TBL_ITEM)*(pitlen+1));
  int i=0;
  if (pitver == V0)
  {
    for (i=0; i<8; i++)
    {
      *(ptail+i) = u4TailSig;
    }
  }
  else if (pitver == V1)
  {
    ;//do nothing, v1 version has no tail.
  }
  else
  {
    NAND_LOG(NAND_LOG_ERROR,"set part tail failed!\n");
    return -1;
  }
  
  NAND_LOG(NAND_LOG_DEBUG,"set part tail success!\n");
  return 0;
}

INT32 i4CheckPITVer(UINT32 *phead, UINT32 u4HeadSig)
{
  if ((*(phead) != u4HeadSig) || (*(phead+1) != u4HeadSig))
  {
    NAND_LOG(NAND_LOG_DEBUG, "no part head sig found -1\n");
  	return -1;
  }
  else
  {
    if (*(phead+2) != u4HeadSig || *(phead+3) != u4HeadSig)
    {
      NAND_LOG(NAND_LOG_DEBUG, "v1 pit found\n");
	  return V1;
    }
	else
	{
	  NAND_LOG(NAND_LOG_DEBUG, "v0 pit found\n");
	  return V0;
	}
  	return 0;
  }
}

PART_TBL_ITEM* pr_get_pit_item_by_idx(UINT32 pit, UINT32 idx, UINT32 pitver)
{
  V1_PIT_HEADER_T* hdr = (V1_PIT_HEADER_T*)pit;
  if (pitver == V0)
  {
  	return (PART_TBL_ITEM*)(pit + sizeof(PART_TBL_ITEM)*(idx+1));
  }
  return (PART_TBL_ITEM*)(pit + hdr->u4_header_len + (idx * hdr->u4_item_len));
}

PART_TBL_ITEM* mt85xx_get_part_item_by_name(UINT32 pit, const char* part_name, UINT32 pitver)
{
  UINT32 i=0;
  PART_TBL_ITEM *item=NULL;
  for(i=0 ; i<u4_part_nr; i++)
  {
    item = pr_get_pit_item_by_idx(pit, i, pitver);
	if(strcmp(item->pc_name, part_name))
    {
            continue;
    }
	
	NAND_LOG(NAND_LOG_DEBUG, "get part item by name success\n");
	return item;
  }
  
  NAND_LOG(NAND_LOG_ERROR, "get part item by name error\n");
  return NULL;
}

UINT32 get_part_id_by_name_new(UINT32 pit, const char* part_name, UINT32 pitver)
{
  PART_TBL_ITEM *temp=NULL;
  temp = mt85xx_get_part_item_by_name(pit, part_name, pitver);
  if (temp)
    return temp->u4_id;
  else
  	return 0;
}
INT32 i4CopyPartItem(UINT32 pdest, UINT32 psource, UINT32 pitver)
{
  UINT32 u4ItemLen=0,u4CheckLen=0;
  
  if (pitver == V0)
  {
	u4CheckLen = 32;
  }
  else if (pitver == V1)
  {
	u4CheckLen = 40;
  }
  u4ItemLen = u4GetItemLen(psource, HEAD_SIG, TAIL_SIG);
  memcpy((u_char*)pdest, (u_char*)psource, u4CheckLen);
  return 0;
}
INT32 i4SetEnv(UINT32 pit, UINT32 u4PartCnt) 
{
    INT32 i = 0;	
    INT32 i4Ret = 0;
    char _bootargs_buf[1024] = "";
    char _tmp_buf[1024] = "";
	PART_TBL_ITEM *item=NULL;
    /* mtdparts */
    strcat(_bootargs_buf, "mtdparts=mt85xx_nand:");
    for (i = 0; (i < u4PartCnt); i++) 
    {
      item = pr_get_pit_item_by_idx(pit, i, _pit_ver);
      if( ISUBIVOLUME(item->u4_info) != 4 )
      {
        i4Ret = sprintf(_tmp_buf, "%dK(%s),", item->u4_size >> 10,item->pc_name);	
        if (-1 == i4Ret) 
		{
          NAND_LOG(NAND_LOG_ERROR, "[mt85xx_part_oper]creat szMtdPartsCmd failed\n");	
          return i4Ret;
        }
        strcat(_bootargs_buf, _tmp_buf);	
      }
    }
	NAND_LOG(NAND_LOG_INFO, "mt85xx_part_oper.c vSetEnv set mtdparts = %s \n", _bootargs_buf);	 
    i4Ret = setenv("mtdparts", _bootargs_buf);
    if (-1 == i4Ret) {
       NAND_LOG(NAND_LOG_ERROR, "mt85xx_part_oper set mtdparts failed, mtdparts = %s \n", _bootargs_buf);	
       return i4Ret;
    }
    i4Ret =  setenv("mtdids", "nand0=mt85xx_nand");
    if (-1 == i4Ret) {
       NAND_LOG(NAND_LOG_ERROR, "mt85xx_part_oper set mtdids failed\n");	
       return i4Ret;
    }
	NAND_LOG(NAND_LOG_INFO, "mt85xx_part_oper set env OK\n");

    return i4Ret;  
}
#if 0
INT32 i4SetMtdTbl(UINT32 pit, UINT32 u4PartCnt)
{
  UINT32 i=0;
  PART_TBL_ITEM *item=NULL;
  UINT32 pitver= i4CheckPITVer((UINT32*)pit, ADPT_HEAD_SIG);
  
  i4SetEnv(pit, u4PartCnt);
  for (i = 0; i < u4PartCnt; i++) 
  {
    item = pr_get_pit_item_by_idx(pit, i, pitver);
   // if( ISUBIVOLUME(item->u4_info) != 4 )
    {
	   if(mt85xx_init_mtd_table(item->pc_name) != 0)	
      {
        NAND_LOG(NAND_LOG_ERROR, "[Set Mtd Table]%s not be added!\n", item->pc_name);	
        return -1;
      }
    }
  }
	//set mtd success
	return 0;
}
#endif
INT32 i4FindPartTbl(UINT32 *pit, UINT32 *pitnr, UINT32 headsig, UINT32 tailsig)
{
    int r=0;
    UINT32 i=0;
	UINT32 internal=get_block_size();
	if(!pit)
	{
  	  NAND_LOG(NAND_LOG_ERROR, "[i4FindPartTbl] pit NULL.\n");
  	  r = -1;
  	  goto err;
	}
    for(i=SEARCH_PART_TBL_START; i<SEARCH_PART_TBL_END; i+=internal)
    {
        /* read nand */
		r = i4NFBPartitionRead(0,(UINT32)i,(UINT32)pit, PART_TBL_ITEM_NR_MAX*sizeof(NEW_PART_TBL_ITEM));
        if(r)
        {
            NAND_LOG(NAND_LOG_ERROR, "[i4FindPartTbl] NFBPartitionRead error!!\n");
            continue;
        }

        /* check head, the head sig is stored in the begining 8 byte of pc_name */
	    if ((i4CheckPITVer(pit, headsig)) < 0)
	    {
		  continue;
	    }
        r = 0;
		*pitnr = u4GetPartNum((UINT32*)pit, headsig, tailsig);
		NAND_LOG(NAND_LOG_DEBUG, "[i4FindPartTbl] pit found!!\n");
		goto close;
		
    }
    /* not found */
err:
    NAND_LOG(NAND_LOG_ERROR, "[i4FindPartTbl] can't find pit!!\n");
	r = -1;
close:
	return r;

}
INT32 i4SearchAdptPIT(void)
{
  int r=0;
  UINT32 pitlen=0;
  /*find partition table in nand*/
  if(_u4_same)
  {
    if(!adpt_pit)
    {
      adpt_pit = kzalloc(sizeof(NEW_PART_TBL_ITEM)*PART_TBL_ITEM_NR_MAX, GFP_KERNEL);
      if(!adpt_pit)
      {
         NAND_LOG(NAND_LOG_ERROR, "[i4SearchAdptPIT] allocate adpt_pit memo failed.\n");
  	   return -1;
      }
    }
    r = i4FindPartTbl(adpt_pit, &pitlen, ADPT_HEAD_SIG, ADPT_TAIL_SIG);
    if(r)
    {
      NAND_LOG(NAND_LOG_INFO, "[i4SearchAdptPIT] error.\n");
  	  return -1;
    }
	#if 0
    if(i4SetMtdTbl((UINT32)adpt_pit, pitlen))
    {
      r = -1;
      NAND_LOG(NAND_LOG_ERROR, "mount adaptive mtd failed\n");
  	  return -1;
    }
	#endif
  }
  
  NAND_LOG(NAND_LOG_INFO, "[i4SearchAdptPIT] success.\n");
  return (INT32)adpt_pit;
}
INT32 i4GetAdptPit(void)
{
  return i4SearchAdptPIT();
}

INT32 i4StoreAdptPit(void)
{
  UINT32 u4Id=0;
  UINT32 offset=0;
  UINT32 itemnr=0;

  if (_pit_ver == V0)
  {
    itemnr = u4_part_nr + 2;
  }
  else if (_pit_ver == V1)
  {
    itemnr = u4_part_nr + 1;
  }
  else
  {
    NAND_LOG(NAND_LOG_DEBUG, "store adpt pit failed, due to part version error\n");
    return -1;
  }
  
  if (!_u4_same)
  {
    NAND_LOG(NAND_LOG_DEBUG, "store adpt pit in part_info_1 partition\n");
    u4Id = get_part_id_by_name_new((UINT32)adpt_pit, "part_info_1", _pit_ver);
	if (u4Id != 0)
	{
      /*store adaptive PIT part_info_1 parition.*/
  	offset = ADPT_PIT_OFFSET + pr_get_pit_item_by_idx((UINT32)adpt_pit, u4Id-1, _pit_ver)->u4_offset;
      if (i4NFBPartitionWrite(0, offset, (UINT32)adpt_pit, 
      	                 (UINT32)(itemnr * sizeof(NEW_PART_TBL_ITEM))))
      {
        NAND_LOG(NAND_LOG_ERROR, "store part_info_1 adpt pit failed\n");
    	  return -1;
      }
	}
	else
	{
	  NAND_LOG(NAND_LOG_INFO, "no partition part_info_1 \n");
	}
    NAND_LOG(NAND_LOG_DEBUG, "store adpt pit in part_info_2 partition\n");
    u4Id = get_part_id_by_name_new((UINT32)adpt_pit, "part_info_2", _pit_ver);
	if (u4Id != 0)
	{
  	  offset = ADPT_PIT_OFFSET + pr_get_pit_item_by_idx((UINT32)adpt_pit, u4Id-1, _pit_ver)->u4_offset;
      /*store adaptive PIT to part_info_2 parition.*/
      if (i4NFBPartitionWrite(0, offset, (UINT32)adpt_pit,
      	                 (UINT32)(itemnr * sizeof(NEW_PART_TBL_ITEM))))
      {
        NAND_LOG(NAND_LOG_ERROR, "store part_info_2 adpt pit failed\n");
    	  return -1;
      }
	}
    else
    {
      NAND_LOG(NAND_LOG_INFO, "no partition part_info_2 \n");
    }
    NAND_LOG(NAND_LOG_INFO, "store adpt pit success\n");
  }
  return 0;
}

INT32 i4SearchOrigPIT(void)
{
  int r=0;
  /*find partition table in nand*/
  if(!orig_pit)
  {
    orig_pit = kzalloc(sizeof(NEW_PART_TBL_ITEM)*PART_TBL_ITEM_NR_MAX, GFP_KERNEL);
	if(!orig_pit)
    {
       NAND_LOG(NAND_LOG_ERROR, "[i4SearchOrigPIT] allocate orig_pit memo failed.\n");
	   return -1;
    }
  }
  r = i4FindPartTbl(orig_pit, &_u4_orig_part_nr, HEAD_SIG, TAIL_SIG);
  if(r)
  {
    NAND_LOG(NAND_LOG_ERROR, "[i4SearchOrigPIT] ERROR.\n");
	return -1;
  }
  _pit_ver = i4CheckPITVer(orig_pit, HEAD_SIG);
  _u4_orig_part_nr = u4GetPartNum((UINT32)orig_pit, HEAD_SIG, TAIL_SIG);
  NAND_LOG(NAND_LOG_INFO, "[i4SearchOrigPIT] SUCCESS.\n");
  return (INT32)orig_pit;

}
INT32 i4CompareFlashPIT(UINT32 bin_pit, UINT32 len)
{
  INT32 i4Ret=0;
  UINT32 u4ItemLen=0, i=0;
  UINT32 u4CheckLen=0;

  orig_pit = kzalloc(sizeof(NEW_PART_TBL_ITEM)*PART_TBL_ITEM_NR_MAX, GFP_KERNEL);
  adpt_pit = kzalloc(sizeof(NEW_PART_TBL_ITEM)*PART_TBL_ITEM_NR_MAX, GFP_KERNEL);
  if(!orig_pit)
  {
     NAND_LOG(NAND_LOG_ERROR, "[i4CompareFlashPIT] allocate orig_pit memo failed.\n");
	 i4Ret = -1;
     goto err;
  }
  if(!adpt_pit)
  {
     NAND_LOG(NAND_LOG_ERROR, "[i4CompareFlashPIT] allocate adpt_pit memo failed.\n");
	 i4Ret = -1;
     goto err;
  }
  if((i4SearchOrigPIT())<0)
  {
    goto err;
  }
  
  if(i4CheckPITVer((UINT32*)bin_pit, HEAD_SIG) != _pit_ver)
  {
    /*the part version is differnt*/
    NAND_LOG(NAND_LOG_INFO, "[i4CompareFlashPIT] version different.\n");
    return 0;
  }
  
  if (_pit_ver == V0)
  {
	u4CheckLen = 32;
  }
  else if (_pit_ver == V1)
  {
	u4CheckLen = 40;
  }
  
  u4ItemLen = u4GetItemLen((UINT32)orig_pit, HEAD_SIG, TAIL_SIG);

  if (i4Ret == 0)
  {
    /*check whether the partition number are same or not . 
       if not , the compared result can return 'different' directly and doesn't need to compare item by item*/
    if (u4GetPartNum(bin_pit, HEAD_SIG, TAIL_SIG) != _u4_orig_part_nr)
    {
      NAND_LOG(NAND_LOG_INFO, "[i4CompareFlashPIT] partition number is different.\n");
      return 0;
    }
	/*compare item by item, from the 1st item to start
	note: the revered filed is un-necessary to compare, because the content in reserved filed is meaningless.*/
    for (i = 1 ; i < _u4_orig_part_nr ; i++)
    {
      i4Ret = memcmp(((UCHAR *)bin_pit + (u4ItemLen*i)) , ((UCHAR *)orig_pit + (u4ItemLen*i)), u4CheckLen);
      if (i4Ret != 0)
      {
        //compare failed.
        break;
      }
    }
	if (i4Ret == 0)
	{
	  if (u4_adpt == 1)
	  {
	    NAND_LOG(NAND_LOG_INFO, "[i4CompareFlashPIT] different, need to creat adpt pit!\n");
		return 0;
	  }
	  else
	  {
	    _u4_same = 1;
	    NAND_LOG(NAND_LOG_INFO, "[i4CompareFlashPIT] same.\n");
        return 1;
	  }
	}
	else
	{
	  NAND_LOG(NAND_LOG_INFO, "[i4CompareFlashPIT] different.\n");
      return 0;
	}
  }
err:
  NAND_LOG(NAND_LOG_ERROR, "[i4CompareFlashPIT] search original pit failed.\n");
  return -1;
}
INT32 i4SetNewFlashPIT(UINT32 bin_pit)
{
  UINT32 u4Id = 0;
  UINT32 u4Offset=0, u4Size=0, u4GoodCnt=0, u4BadCnt=0, u4Blockstart=0;  
  INT32 i4Ret = -1;
  struct mtd_info *mtd=NULL;
  PART_TBL_ITEM *prdest=NULL, *prdestup=NULL, *prsource=NULL;
  UINT32 u4DestOft=0;

  NAND_LOG(NAND_LOG_DEBUG, "set new flash pit\n");
  if(!adpt_pit)
  {
    adpt_pit = kzalloc(sizeof(NEW_PART_TBL_ITEM)*PART_TBL_ITEM_NR_MAX, GFP_KERNEL);
    if(!adpt_pit)
    {
      NAND_LOG(NAND_LOG_ERROR, "[i4SearchAdptPIT] allocate adpt_pit memo failed.\n");
  	  return -1;
    }
  }
	 
  mtd = get_mtd_device(NULL, 0);
  _pit_ver = i4CheckPITVer((UINT32*) bin_pit, HEAD_SIG);
  if (_pit_ver == V0)
  {
	prdest = kzalloc(sizeof(PART_TBL_ITEM),GFP_KERNEL);
	prdestup = kzalloc(sizeof(PART_TBL_ITEM),GFP_KERNEL);
	prsource = kzalloc(sizeof(PART_TBL_ITEM),GFP_KERNEL);
  }
  else if (_pit_ver == V1)
  {
	prdest = kzalloc(sizeof(NEW_PART_TBL_ITEM),GFP_KERNEL);
	prdestup = kzalloc(sizeof(PART_TBL_ITEM),GFP_KERNEL);
    prsource = kzalloc(sizeof(PART_TBL_ITEM),GFP_KERNEL);
  }

  if (!prdest || !prdestup || !prsource)
  {
    NAND_LOG(NAND_LOG_ERROR, "[i4SetNewFlashPIT]allocate prdest memo failed\n");
    i4Ret = -1;
	goto closeall;
  }		
  u4_part_nr = u4GetPartNum(bin_pit, HEAD_SIG, TAIL_SIG);
  if (u4_part_nr == 0)
  {
    NAND_LOG(NAND_LOG_ERROR, "the size of part tbl you past is zero\n");
    i4Ret = -1;
    goto closeall;
  }

  /*scan each partition to creat a new adaptive table*/
  /*step 0: fill in the header of adapt pit, according to bin_pit*/
  i4CopyPartItem((UINT32)adpt_pit,(UINT32)bin_pit, _pit_ver);
  
  /*step 1: check 1st partition alone*/
  NAND_LOG(NAND_LOG_DEBUG, "step 1: check 1st partition\n");
  prdest = pr_get_pit_item_by_idx((UINT32)adpt_pit, 0, _pit_ver);
  prsource = pr_get_pit_item_by_idx((UINT32)bin_pit, 0, _pit_ver);
  u4Offset = prsource->u4_offset;
  u4Size = prsource->u4_size;
  u4BadCnt = 0;
  for (u4GoodCnt = 0; (u4GoodCnt * mtd->erasesize) < u4Size;)
  {
    u4Blockstart = u4Offset & (~mtd->erasesize + 1);
    u4Offset += mtd->erasesize;
	if(mtd->block_isbad(mtd, u4Blockstart))
	{
	  u4BadCnt ++;
      continue;
	}
	u4GoodCnt++;
  }
  i4CopyPartItem((UINT32)prdest,(UINT32)prsource, _pit_ver);
  if( ISUBIVOLUME(prdest->u4_info) != 4 )
  {
    prdest->u4_size = prsource->u4_size + u4BadCnt*mtd->erasesize; 
  }
  /*step 2: check the 2nd~last partition*/
  NAND_LOG(NAND_LOG_DEBUG, "step 2: check 2nd~last partition\n");
  for (u4Id = 1; u4Id < u4_part_nr; u4Id ++) //scan flash to creat adaptive 
  {
    prdest = pr_get_pit_item_by_idx((UINT32)adpt_pit, u4Id, _pit_ver);
	prsource = pr_get_pit_item_by_idx((UINT32)bin_pit, u4Id, _pit_ver);
	prdestup = pr_get_pit_item_by_idx((UINT32)adpt_pit, u4Id-1, _pit_ver);
	if( ISUBIVOLUME(prsource->u4_info) != 4 ) // it is main partition
    {
      if (ISUBIVOLUME(prdestup->u4_info) != 4)//main partition
      {
	    prdest->u4_offset = prdestup->u4_offset + prdestup->u4_size;
      }
	  else //vol partition
	  {
	    prdestup = pr_get_pit_item_by_idx((UINT32)adpt_pit, u4_get_parent_part_id(prdestup)-1, _pit_ver);
	    prdest->u4_offset = prdestup->u4_offset + prdestup->u4_size;
	  }
    }
	else //it is vol partition
	{
	  if (ISUBIVOLUME(prdestup->u4_info) != 4)//main partition
	  {
	    prdest->u4_offset = prdestup->u4_offset;
	  }
	  else // vol partition
	  {
	    prdest->u4_offset = prdestup->u4_offset + prdestup->u4_size;
	  }
	}
    u4DestOft=u4Offset = prdest->u4_offset;
	u4Size = prsource->u4_size;
	u4BadCnt = 0;
	for (u4GoodCnt = 0; ((u4GoodCnt * mtd->erasesize) < u4Size);)
	{
	  u4Blockstart = u4Offset & (~mtd->erasesize + 1);
	  if (u4Offset >= (mtd->size - 4*mtd->erasesize)) //reserve at least 8 block to store bbt
	  {
	    NAND_LOG(NAND_LOG_ERROR, "[i4SetNewFlashPIT]Sorry, no enough block to use for \"%s\", please check PIT\n", prsource->pc_name);
        while(1);
	  }
	  u4Offset += mtd->erasesize;
	  if(mtd->block_isbad(mtd, u4Blockstart))
	  {
	    u4BadCnt ++;
		NAND_LOG(NAND_LOG_INFO, "[i4SetNewFlashPIT]bad block found at 0x%x\n", u4Blockstart);
		continue;
	  }
	  u4GoodCnt++;
	}
	i4CopyPartItem((UINT32)prdest,(UINT32)prsource, _pit_ver);
	prdest->u4_offset = u4DestOft;
	prdest->u4_size = prsource->u4_size + u4BadCnt*mtd->erasesize; 
 
  }
  /*step 3: add the head&tail as adaptive pit, the head&tail style is same the binary pit, because it will be searched by other modules*/
  NAND_LOG(NAND_LOG_DEBUG, "step 3: set adpt pit head&tail\n");
  u4SetPartHead((UINT32)adpt_pit, _pit_ver, ADPT_HEAD_SIG);
  u4SetPartTail((UINT32)adpt_pit, u4_part_nr, _pit_ver, ADPT_TAIL_SIG);
  #if 0
  /*re-install mtd with new adaptive PIT */
  NAND_LOG(NAND_LOG_DEBUG, "step 5: re-init adpt mtd\n");
  if (i4SetMtdTbl((UINT32)adpt_pit, u4_part_nr))
  {
    i4Ret = -1;
    NAND_LOG(NAND_LOG_ERROR, "mount adaptive mtd failed\n");
	goto closeall;
  }
   
  NAND_LOG(NAND_LOG_DEBUG, "step 6: store adpt pit in part_info_1 partition\n");
  u4Id = get_part_id_by_name_new((UINT32)adpt_pit, "part_info_1", _pit_ver);
  /*store adaptive PIT part_info_1 parition.*/
   if (i4NFBPartitionWrite(u4Id, ADPT_PIT_OFFSET,(UINT32)adpt_pit,
  	                 (UINT32)((u4_part_nr+1) * sizeof(NEW_PART_TBL_ITEM))))
  {
    i4Ret = -1;
    NAND_LOG(NAND_LOG_ERROR, "store part_info_1 adpt pit failed\n");
	goto closeall;
  }
  NAND_LOG(NAND_LOG_DEBUG, "step 7: store adpt pit in part_info_2 partition\n");
  u4Id = get_part_id_by_name_new((UINT32)adpt_pit, "part_info_2", _pit_ver);
  /*store adaptive PIT to part_info_2 parition.*/
  if (i4NFBPartitionWrite(u4Id,ADPT_PIT_OFFSET,(UINT32)adpt_pit,
  	                 (UINT32)((u4_part_nr+1) * sizeof(NEW_PART_TBL_ITEM))))
  {
    i4Ret = -1;
    NAND_LOG(NAND_LOG_ERROR, "store part_info_2 adpt pit failed\n");
	goto closeall;
  }
  #else
  if(i4StoreAdptPit())
  {
	i4Ret = -1;
	goto closeall;
  }
  #endif
  i4Ret = 0;
  
closeall:
  if (i4Ret<0)
  {
    NAND_LOG(NAND_LOG_ERROR, "set new flash pit failed\n");
  }
  else
  {
    NAND_LOG(NAND_LOG_DEBUG, "set new flash pit success\n");
  }
  return i4Ret;
}
#endif
INT32 i4NFBPartitionRead(UINT32 u4DevId, UINT32 u8Offset, UINT32 u4MemPtr, UINT32 u4MemLen)
{
    UINT32 start_addr=0;        // start address
    UINT32 ofs=0;
    UINT32 blockstart = 1;
    int  pagelen, badblock = 0;
    UINT32 u4RdByteCnt = 0;
	UINT32 u4RdLen = 0;
    INT32 i4Ret = 0;
	UINT8 *pu1MemBuf = (UINT8*)u4MemPtr;
	struct mtd_info *mtd=NULL;
    NAND_LOG(NAND_LOG_INFO, "[part read]u4DevId = %d, u8Offset = 0x%x, u4MemPtr = 0x%x, u4MemLen = 0x%x \n", 
		    u4DevId, u8Offset, u4MemPtr, u4MemLen);

	ASSERT(pu1MemBuf != NULL);
	mtd = get_mtd_device(NULL, u4DevId);

    /* Initialize page size */
    pagelen = mtd->writesize;
    start_addr = u8Offset;        // start address
    
    // Check, if length fits into device
    if ( ((u4MemLen / pagelen) * mtd->writesize) > (mtd->size - u8Offset)) 
    {
        NAND_LOG(NAND_LOG_ERROR, "[part write] Image %d bytes, NAND page %d bytes, OOB area %u bytes, device size %u bytes\n",
                 u4MemLen, pagelen, mtd->oobsize, mtd->size);
        NAND_LOG(NAND_LOG_ERROR, "[part write] Error : Input file can not fit into device\n");        
        i4Ret = -1;
        goto closeall;
    }
    
    ofs = 0;
    /* Check all the blocks from the begining of the partition for bad blocks */
    while ( (ofs < (start_addr & (~mtd->erasesize + 1))) && (ofs < mtd->size))
    {
        if ((i4Ret = mtd->block_isbad(mtd, ofs)) < 0) 
        {
            NAND_LOG(NAND_LOG_ERROR, "[part read]Error check bad block\n");
            i4Ret = -1;
            goto closeall;
        }
        
        if (i4Ret == 1) 
        {
            start_addr = start_addr + mtd->erasesize;
            NAND_LOG(NAND_LOG_INFO, "[part read]=> Bad block at partition_%d, 0x%x will be skipped\n", u4DevId, ofs);
        }
        else
        {
            NAND_LOG (NAND_LOG_DEBUG, "[part read]=> Good block\n");
        }

        ofs +=  mtd->erasesize;
    }

    for (ofs = start_addr; ofs < mtd->size && u4RdByteCnt < u4MemLen ;) 
    {
        // new eraseblock , check for bad block
        if (blockstart != (ofs & (~mtd->erasesize + 1))) 
        {
            blockstart = ofs & (~mtd->erasesize + 1);
		    //NAND_LOG("[part read] partition_%d - check block 0x%X\n", u4DevId, blockstart);
			if ((badblock = mtd->block_isbad(mtd, blockstart)) < 0)
            {
                NAND_LOG(NAND_LOG_ERROR, "[part read]Error check bad block\n");
                goto closeall;
            }
        }

        if (badblock) 
        {
            // skip the bad block
            ofs += mtd->erasesize;
			badblock = 0;
            NAND_LOG(NAND_LOG_INFO, "[part read] partition_%d - bad block found at : 0x%X\n", u4DevId, blockstart);
            continue;
        }
        else 
        {
            /* Read page data and exit on failure */
			if ((u4MemLen - u4RdByteCnt) < mtd->writesize)
			{
			  pagelen = (u4MemLen - u4RdByteCnt);
			}
            if ((i4Ret = mtd->read(mtd, ofs, pagelen, &u4RdLen, (u_char *)(pu1MemBuf + u4RdByteCnt)))<0)
            {
                NAND_LOG(NAND_LOG_ERROR, "[part read]Page read error occur at : 0x%x\n", ofs);
                goto closeall;
            }
            ofs += u4RdLen;
            u4RdByteCnt += (UINT32)u4RdLen;
        }

    }

    if (u4RdByteCnt < u4MemLen)
    {
        NAND_LOG(NAND_LOG_ERROR, "[part read]Can't read enough data, u4RdByteCnt = 0x%x, u4MemLen = 0x%x\n", u4RdByteCnt, u4MemLen);        
        goto closeall;
    }
    /* Exit happy */
    return i4Ret;

closeall:    
    i4Ret = -1;    
    return i4Ret;
}
#define PART_WRITE_VERIFY 0
INT32 i4NFBPartitionWrite(UINT32 u4DevId, UINT32 u8Offset , UINT32 u4MemPtr, UINT32 u4MemLen)
{
    int imglen = u4MemLen, pagelen=0;
	size_t retlen=0;
    UINT8 baderaseblock = FALSE;
    int blockstart = -1;
	loff_t offs=0;    
    int readlen=0, writelen=0, readcnt=0, headoffset=0, tailoffset=0, headsize=0, tailsize=0, headend=0, tailstart=0;
    UINT32 mtdoffset=0, writeoffset=0, tempoffset=0;
    INT32 i4Ret = 0;
    UINT32 u4WtByteCnt = 0;
	UINT8  *pu1MemBuf = (UINT8*)u4MemPtr;
	u_char *pu1TempMemBuf = NULL;
	struct erase_info *erase=NULL;
	struct mtd_info *mtd;
	u_char *pu1BlockMemBuf = NULL;
	BOOL fgBlockDataBack = FALSE;
	
    erase = kzalloc(sizeof(struct erase_info),GFP_KERNEL);
	if (!erase)
	{
	  NAND_LOG(NAND_LOG_ERROR, "[part write]allocate erase info memo failed\n");
	  return -ENOMEM;
	}
	
    ASSERT(pu1MemBuf != NULL);
    mtd = get_mtd_device(NULL, u4DevId);
   
    mtdoffset = u8Offset;        // start address
    
    NAND_LOG(NAND_LOG_INFO, "[part write]u4DevId = %d, u8Offset = 0x%x, u4MemPtr = 0x%x, u4MemLen = 0x%x \n", 
		    u4DevId, u8Offset, u4MemPtr, u4MemLen);
    
    pagelen = mtd->writesize;

    headsize = 0;
	tailsize = 0;
	
    /*
     * For the standard input case, the input size is merely an
     * invariant placeholder and is set to the write page
     * size. Otherwise, just use the input file size.
     *
     * TODO: Add support for the -l,--length=length option (see
     * previous discussion by Tommi Airikka <tommi.airikka@ericsson.com> at
     * <http://lists.infradead.org/pipermail/linux-mtd/2008-September/
     * 022913.html>
     */

    // Check, if length fits into device
    if ( ((imglen / pagelen) * mtd->writesize) > (mtd->size - mtdoffset)) 
    {
        NAND_LOG(NAND_LOG_ERROR, "[part write] Image %d bytes, NAND page %d bytes, OOB area %u bytes, device size %u bytes\n",
                 imglen, pagelen, mtd->oobsize, mtd->size);
        NAND_LOG(NAND_LOG_ERROR, "[part write] Error : Input file can not fit into device\n");        
        i4Ret = -1;
        goto closeall;
    }
    pu1TempMemBuf = kzalloc(MAX_PAGE_SIZE,GFP_KERNEL);
    ASSERT(pu1TempMemBuf != NULL);

	pu1BlockMemBuf = kzalloc(mtd->erasesize,GFP_KERNEL);
    ASSERT(pu1BlockMemBuf != NULL);

    offs = 0;
    /* Check all the blocks in an erase block for bad blocks */
    while ( (offs < (mtdoffset & (~mtd->erasesize + 1))) && (offs < mtd->size))
    {
        if ((i4Ret = mtd->block_isbad(mtd, offs)) < 0) 
        {
            NAND_LOG(NAND_LOG_ERROR, "[part write]Error check bad block\n");
            i4Ret = -1;
            goto closeall;
        }
        
        if (i4Ret == 1) 
        {
            NAND_LOG(NAND_LOG_INFO, "[part write]=> Bad block at partition_%d, offset 0x%x \n", (int)u4DevId, (uint32_t)offs);
            mtdoffset = mtdoffset + mtd->erasesize;
        }
        else
        {
            NAND_LOG (NAND_LOG_DEBUG, "[part write]=> Good block\n");
        }

        offs +=  mtd->erasesize;
    }

    /*
     * Get data from input and write to the device while there is
     * still input to read and we are still within the device
     * bounds. Note that in the case of standard input, the input
     * length is simply a quasi-boolean flag whose values are page
     * length or zero.
     */    
    while ((imglen || headsize || tailsize) && (mtdoffset < mtd->size)) 
    {
        // new eraseblock , check for bad block(s)
        // Stay in the loop to be sure if the mtdoffset changes because
        // of a bad block, that the next block that will be written to
        // is also checked. Thus avoiding errors if the block(s) after the
        // skipped block(s) is also bad (number of blocks depending on
        // the blockalign
        while (blockstart != (mtdoffset & (~mtd->erasesize + 1))) 
        {
		    headoffset = 0;
			tailoffset = 0;
			headsize = 0;
			tailsize = 0;
			headend = blockstart;
            blockstart = mtdoffset & (~mtd->erasesize + 1);
			tailstart = blockstart + mtd->erasesize;
            offs = blockstart;
            baderaseblock = FALSE;
            NAND_LOG(NAND_LOG_INFO, "[part write] Try to writing data to block %d at offset 0x%x\n",
                         blockstart / mtd->erasesize, blockstart);

            /* Check all the blocks in an erase block for bad blocks */
            do 
            {
                if ((i4Ret = mtd->block_isbad(mtd, offs)) < 0) 
                {
                    NAND_LOG(NAND_LOG_ERROR, "[part write]Error check bad block\n");
                    i4Ret = -1;
                    goto closeall;
                }
                
                if (i4Ret == 1) 
                {
                    baderaseblock = TRUE;
                    NAND_LOG(NAND_LOG_INFO, "[part write] => Bad block at partition_%x, offset 0x%x, will be skipped\n", u4DevId, blockstart);
                }
                else
                {
                    NAND_LOG (NAND_LOG_DEBUG, "[part write]=> Good block\n");
                }

                if (baderaseblock) 
                {
                    mtdoffset = mtdoffset + mtd->erasesize;
                }
                offs +=  mtd->erasesize;
            } while ( offs < blockstart + mtd->erasesize );

            if (!baderaseblock)
            {
                // Erase the erase-block before write data into the block  
               // wait_queue_head_t waitq;
			   // DECLARE_WAITQUEUE(wait, current);
			   // init_waitqueue_head(&waitq);
                headsize = mtdoffset - blockstart;
				headend = mtdoffset;
				if((mtdoffset+imglen) >= (blockstart+mtd->erasesize))
				{
				  tailsize = 0;  
				}
				else
				{
				  tailsize = (blockstart+mtd->erasesize) - (mtdoffset+imglen);
				} 
				tailoffset = mtd->erasesize - tailsize;
				tailstart = blockstart + tailoffset;
				if((headsize > 0) || (tailsize > 0))
				{
				   tempoffset = blockstart;
				   
                   if(!fgBlockDataBack)
				   {
    				   readlen = 0;
    				   memset(pu1BlockMemBuf, 0xFF, mtd->erasesize);
                       for (offs = blockstart; offs < mtd->size && readlen < mtd->erasesize ; offs+=pagelen) 
                       {
                               /* Read page data and exit on failure */
                               if (mtd->read(mtd, offs, pagelen, &readcnt, (u_char *)(pu1BlockMemBuf + readlen)))
                               {
                                   NAND_LOG(NAND_LOG_ERROR, "[part read]Page read error occur at : 0x%x\n", offs);
                                   goto closeall;
                               }
    
    			               if(readcnt != pagelen)
    			               {
    			                   NAND_LOG(NAND_LOG_ERROR, "[part read]Page read error (not): readcnt = 0x%x\n", readcnt);
                                   goto closeall;
    			               }
    						   else
    			               {
                                   readlen += readcnt;
    			               }
                       }
				   }
                   else
                   {
				       fgBlockDataBack = FALSE;
                   }
				}
				
                erase->addr = blockstart;
                erase->len = mtd->erasesize;
				erase->mtd = mtd;
				//erase->callback = mtdchar_erase_callback;
			   // erase->priv = (unsigned long)&waitq;
				
                //NAND_LOG("[part write] Erasing this block before write from 0x%08lX-0x%08lX\n", (long)erase->addr, (long)erase->addr+erase->len-1);
                i4Ret = mtd->erase(mtd, erase);
				/*if (!i4Ret) 
				{
    				set_current_state(TASK_UNINTERRUPTIBLE);
    				add_wait_queue(&waitq, &wait);
    				if (erase->state != MTD_ERASE_DONE &&
    				    erase->state != MTD_ERASE_FAILED)
    					schedule();
    				remove_wait_queue(&waitq, &wait);
    				set_current_state(TASK_RUNNING);
    
    				i4Ret = (erase->state == MTD_ERASE_FAILED)?-5:0;
			    }*/
				if (i4Ret != 0)
                {
                    NAND_LOG(NAND_LOG_ERROR, "[part write] block erase failed !!\n");
                    goto closeall;
                }
            }
        }

        writelen = 0;
		readlen = 0;
		readcnt = 0;
        memset(pu1TempMemBuf, 0xFF, mtd->writesize);
		
        if(headsize > 0)
        {
            writelen = mtd->writesize;

            if(headsize >= writelen)
            {
               headsize -= writelen;
            }
			else
			{
			   writelen = headsize;
			   headsize = 0;
			}
            writeoffset = tempoffset;
			tempoffset += writelen; 

			memcpy(pu1TempMemBuf, (pu1BlockMemBuf+headoffset), writelen);

			headoffset += writelen;
        }

		if((imglen > 0) && (writelen < mtd->writesize))
		{
		    readlen = mtd->writesize - writelen;
        if (imglen < readlen)
        {
            readlen = imglen;
        }
			memcpy((pu1TempMemBuf+writelen), (pu1MemBuf+u4WtByteCnt), readlen);

            if(writelen == 0)
            {
                writeoffset = mtdoffset;
            }

			imglen -= readlen;
            u4WtByteCnt += readlen;
			mtdoffset +=readlen;
		}

		if((tailsize > 0)&&((readlen + writelen) < mtd->writesize))
		{
		    readcnt = mtd->writesize - (readlen + writelen);
			
            if(tailsize >= readcnt)
            {
               tailsize -= readcnt;
            }
			else
			{
			   readcnt = tailsize;
			   tailsize = 0;
			}

			if((readlen + writelen) == 0)
			{
			   writeoffset = mtdoffset; 
			}

			memcpy((pu1TempMemBuf+(readlen + writelen)), (pu1BlockMemBuf+tailoffset), readcnt);

            mtdoffset += readcnt;
			tailoffset += readcnt;
		}
		
        /* Write out the Page data */
        if (mtd->write(mtd, writeoffset, mtd->writesize, &retlen, (u_char *)(pu1TempMemBuf)))
        {
            int rewind_blocks;
          //  struct erase_info *erase;

            NAND_LOG(NAND_LOG_ERROR, "[part write] mtd->write error at offset 0x%x\n", writeoffset);

            /* Must rewind to blockstart if we can */
            rewind_blocks = (writeoffset - blockstart) / mtd->writesize; /* Not including the one we just attempted */
            
           // wait_queue_head_t waitq;
			//DECLARE_WAITQUEUE(wait, current);
			//init_waitqueue_head(&waitq);
				
            erase->addr = blockstart;
            erase->len = mtd->erasesize;
			erase->mtd = mtd;
			//erase->callback = mtdchar_erase_callback;
			//erase->priv = (unsigned long)&waitq;
				
            NAND_LOG(NAND_LOG_ERROR, "[NAND] Erasing failed write from 0x%08lX-0x%08lX\n", 
                (long)erase->addr, (long)(erase->addr+erase->len-1));
                
			i4Ret = mtd->erase(mtd, erase);
			/*if (!i4Ret) 
			{
				set_current_state(TASK_UNINTERRUPTIBLE);
				add_wait_queue(&waitq, &wait);
				if (erase->state != MTD_ERASE_DONE &&
					erase->state != MTD_ERASE_FAILED)
					schedule();
				remove_wait_queue(&waitq, &wait);
				set_current_state(TASK_RUNNING);
					
				i4Ret = (erase->state == MTD_ERASE_FAILED)?-5:0;
		    }*/
			if (i4Ret != 0)
			{
				NAND_LOG(NAND_LOG_ERROR, "[part write] block erase failed-1 !!");
			    goto closeall;
			}


            // Mark bad block
            {
                loff_t bad_addr = writeoffset & (~mtd->erasesize + 1);
                
                NAND_LOG(NAND_LOG_INFO, "[part write]Marking block at 0x%08lX bad\n", (long)bad_addr);
                
                if (mtd->block_markbad(mtd, bad_addr)) 
                {
                    NAND_LOG(NAND_LOG_ERROR, "[part write]block mark bad block Error !!\n");                    
                    /* But continue anyway */
                }
            }
            
            if(mtdoffset <= headend)
            {
                writelen = 0;
            }
			else if(mtdoffset <= tailstart)
			{
			    writelen = mtdoffset - headend;
			}
			else 
			{
			    writelen = tailstart- headend;
            }
        
            mtdoffset = blockstart + mtd->erasesize + headend;			
            imglen += writelen;
            u4WtByteCnt -= writelen;

			fgBlockDataBack = TRUE;
        
            continue;
        }
    }

closeall:
    kfree(erase);
	kfree(pu1TempMemBuf);
	kfree(pu1BlockMemBuf);
    if (imglen > 0) 
    {
        NAND_LOG(NAND_LOG_ERROR, "[part write]Data was only partially written due to error, imglen = 0x%x\n", imglen);
        i4Ret = -1;
    }
#if PART_WRITE_VERIFY
    // Read back for verify
    if (i4Ret == 0)
    {
          UINT8 *pu1MemVerfBuf = NULL;
          pu1MemVerfBuf = kzalloc(u4MemLen, GFP_KERNEL);
          if (!pu1MemVerfBuf)
          {
            NAND_LOG(NAND_LOG_ERROR, "[part write] Memo kzalloc failed\n");
		    return -ENOMEM;
          }

          NAND_LOG(NAND_LOG_INFO, "[part write]Read back verify at offset 0x%x, len 0x%x ... \n", u8Offset, u4MemLen);
		  i4Ret = i4NFBPartitionRead(u4DevId, u8Offset, (UINT32)pu1MemVerfBuf, u4MemLen);
          if (i4Ret != 0)
          {
              NAND_LOG(NAND_LOG_ERROR, "[part write] Read back failed !! Fetal Error\n");
              ASSERT(i4Ret == 0);
          }

          i4Ret = memcmp(pu1MemBuf, pu1MemVerfBuf, u4MemLen);
          
          if (i4Ret == 0)
          {
              NAND_LOG(NAND_LOG_INFO, "[part write]Read back compare check OK ^^\n");
          }
          else
          {
              NAND_LOG(NAND_LOG_ERROR, "[part write]Read back compare failed !! Fetal Error\n");
              ASSERT(i4Ret == 0);
          }
          
          kfree(pu1MemVerfBuf);
    }
#endif    
    /* Return happy */
    return i4Ret;
}
EXPORT_SYMBOL(get_part_id_by_name);
EXPORT_SYMBOL(get_part_info_by_name);
EXPORT_SYMBOL(get_part_addr_by_name);
EXPORT_SYMBOL(get_part_size_by_name);
EXPORT_SYMBOL(get_page_size);
EXPORT_SYMBOL(get_block_size);
EXPORT_SYMBOL(i4NFBPartitionRead);
EXPORT_SYMBOL(i4NFBPartitionWrite);

#endif

