/*
 * mt85xx partition table functions
 */


/*
 * header file
 */
#include <common.h>
#include <asm/arch/mt85xx_part_tbl.h>
//#include <nand.h>
#include <asm/arch/nand_operation.h>

#include <linux/mtd/mtd.h>
#include <linux/mtd/partitions.h>
#include <ubi_uboot.h>
#include <asm/errno.h>
#include <jffs2/load_kernel.h>
#include <linux/mtd/mt85xx_part_oper.h>
#include <asm/arch/args_to_uboot.h>

/*
 * define
 */
 
UINT32 _u4SearchInternal=0;
#define PART_TBL_ITEM_NR_MAX 64
#define SEARCH_PART_TBL_START     0x00200000 /*   2MB */
#define SEARCH_PART_TBL_END       0x00A00000 /*  16MB */
#define SEARCH_PART_TBL_INTERNAL  _u4SearchInternal //0x00040000 /*   1MB */
#if ADAPT_MTD_LINUX
UINT32 u4_adpt = 0;
#endif
/* part info */
#define PI_PRIMARY_FLAG_OFST	3	/*bit[3]*/
#define PI_PRIMARY_FLAG_MASK	0x00000008
#define PI_BACKUP_ID_OFST		4	/*bit[4:9]*/
#define PI_BACKUP_ID_MASK		0x000003F0
#define PI_NEW_FORMAT_OFST		12	/*bit[12:15]*/
#define PI_NEW_FORMAT_MASK		0x0000F000
#define PI_PARENT_PART_ID_OFST	16	/*bit[16:21]*/
#define PI_PARENT_PART_ID_MASK	0x003F0000

#define MEM_BUF_PIT_SZ ((PART_TBL_ITEM_NR_MAX + 1) * 64)

/*
 * global variable
 */
PART_TBL_ITEM r_part_tbl[PART_TBL_ITEM_NR_MAX];
UINT32 u4_part_nr = 0;

static UINT32 _u4_pit_format_version = 0;

 BOOL fg_mtdparts_init = FALSE;

/*
 * private functions
 */
#if SUPPORT_V0_FORMAT
int _i_count_part_nr(PART_TBL_ITEM* r_tbl, UINT32 u4Sz, UINT32 *pu4_part_nr, UINT32 headsig, UINT32 tailsig)
{
    UINT32 i;

    for(i=1; i<u4Sz; i++)
    {
        /* search end sig */
        if((r_tbl[i].u4_id     != tailsig) ||
		   (r_tbl[i].u4_info   != tailsig) ||
		   (r_tbl[i].u4_offset != tailsig) ||
		   (r_tbl[i].u4_size   != tailsig))
        {
			continue;
        }
        /* end sig found */
        *pu4_part_nr = i - 1;
        return 0;
    }

    /* end sig not found */
    puts("[_i_count_part_nr]end sig not found\n");
	return -1;
}

static BOOL _fg_is_v0_pit(UINT32 addr, UINT32 headsig, UINT32 tailsig)
{
	PART_TBL_ITEM* hdr = (PART_TBL_ITEM*)addr;

	/* check v1 header */
	if((hdr->u4_id    != headsig) ||
	   (hdr->u4_info  != headsig) ||
	   (hdr->u4_offset!= headsig) ||
	   (hdr->u4_size  != headsig))
	{
		return FALSE;
	}
	return TRUE;
}
#endif

static int _i_chk_pit_version(UINT32 addr, UINT32* p_version, UINT32 headsig, UINT32 tailsig)
{
	PIT_HEADER_T* hdr = (PIT_HEADER_T*)addr;
	
	/* check v1 header */
#if SUPPORT_V0_FORMAT
	if(_fg_is_v0_pit(addr, headsig, tailsig) != FALSE)
	{
		*p_version = 0;
		return 0;
	}
#endif
	if((hdr->u4_head_sig_1 != headsig) ||
	   (hdr->u4_head_sig_2 != headsig)
	  )
	{
		/* unknown version */
		return -1;
	}

	/* return version and success message */
	*p_version = hdr->u4_version;
	return 0;
}

int _i_find_part_tbl(PART_TBL_ITEM* r_tbl, UINT32 u4Sz, UINT32 *pu4_part_nr, UINT32 headsig, UINT32 tailsig)
{
    int r;
    UINT32 i;
    void *pu1_buf;
    int iRet = 0;
	int pu1_buf_alloc = 0;
	
    if((r_args_to_uboot.u4_head_sig == ARGS_TO_UBOOT_HEAD_SIG) 
	   && (r_args_to_uboot.u4_part_tbl_addr != 0)) 
    {
        if(0 == _i_chk_pit_version((UINT32)r_args_to_uboot.u4_part_tbl_addr, &_u4_pit_format_version, headsig, tailsig))
        {
            pu1_buf = (void *)r_args_to_uboot.u4_part_tbl_addr;
			puts("[_i_find_part_tbl]Part tbl info passed from preloader\n");
			goto part_tbl_check;
        }
    }
		
	pu1_buf	= malloc(MEM_BUF_PIT_SZ);
	if(pu1_buf == NULL)
	{
	    puts("[_i_find_part_tbl] malloc FAIL.\n");
	    return -5;
	}

	pu1_buf_alloc = 1;
	
    for(i=SEARCH_PART_TBL_START; i<SEARCH_PART_TBL_END; i+=SEARCH_PART_TBL_INTERNAL)
    {
        /* read nand */
		memset(pu1_buf, 0, MEM_BUF_PIT_SZ);
        r = mtk_nand_read((u_long)pu1_buf, (u_long)i, MEM_BUF_PIT_SZ);
        if(r)
        {
            puts("[_i_find_part_tbl] mtk_nand_read error!!\n");
            continue;
        }
        
        /* check head */ 
        if(_i_chk_pit_version((UINT32)pu1_buf, &_u4_pit_format_version, headsig, tailsig))
        {
            continue;
        }
		
part_tbl_check:
	
    #if SUPPORT_V0_FORMAT
        if(_u4_pit_format_version==0)
        {
            for(i=0; i<u4Sz; i++)
            {
				memcpy((void *)(r_tbl + i), 
					    (pu1_buf + i*PIT_ITEM_V0_SIZE), 
					    PIT_ITEM_V0_SIZE);
            }
			
            /* check if all data loaded */
            /* count partition number */
            r = _i_count_part_nr(r_tbl, u4Sz, pu4_part_nr, headsig, tailsig);
            if(r)
            {
                iRet = -2;
				goto find_part_tbl_done;
            }

            puts("[_i_find_part_tbl] version is 0!!\n");
        }
		else
    #endif
        
        if(_u4_pit_format_version==1)
        {
            UINT32 data_sz;
            PIT_HEADER_T* hdr = (PIT_HEADER_T*)pu1_buf;
            
            /* check if all data loaded */
            data_sz = hdr->u4_header_len + (hdr->u4_item_num * hdr->u4_item_len);
            if(data_sz >= MEM_BUF_PIT_SZ)
            {
                iRet = -2;
				goto find_part_tbl_done;
            }

            if(hdr->u4_item_num >= PART_TBL_ITEM_NR_MAX)
            {
                iRet = -2;
				goto find_part_tbl_done;
            }
			
            /* count partition number */
            *pu4_part_nr = hdr->u4_item_num;

            memcpy((void *)r_tbl, pu1_buf, sizeof(PART_TBL_ITEM)); /*header*/
            for(i=1; i<=hdr->u4_item_num; i++)
            {
               memcpy((void *)(r_tbl + i), 
			   	      (pu1_buf + hdr->u4_header_len + ((i-1) * hdr->u4_item_len)),
			   	       sizeof(PART_TBL_ITEM));
            }

			puts("[_i_find_part_tbl] version is 1!!\n");
        }
        else
        {
            /* not supoorted version - should never happen */
			iRet = -3;
			goto find_part_tbl_done;
        }
        
        /* found */
		goto find_part_tbl_done;
    }

    /* not found */
    puts("[_i_find_part_tbl] can't find partition table!!\n");
	iRet = -1;
	
find_part_tbl_done: 

	if(1 == pu1_buf_alloc)
	{
	    free(pu1_buf);
	}
	return iRet;
}


/*
 * public functions
 */
void mt85xx_part_tbl_init(void)
{
    int r;
	
    _u4SearchInternal = get_block_size();
#if ADAPT_MTD_LINUX
	printk("u-boot adaptive mtd mechanism applied.\n");
    /*find adapt partition table in nand*/
    r = _i_find_part_tbl(r_part_tbl, PART_TBL_ITEM_NR_MAX, &u4_part_nr, ADPT_HEAD_SIG, ADPT_TAIL_SIG);
    #if 0 //prepare for adpt burner, open this case if adpt burner is already used.
	if(r)
	{
	   	if(i4SearchOrigPIT() != -1)
	   	{
	   	  if(!i4SetNewFlashPIT((UINT32)orig_pit))
	   	  {
	   		  r = _i_find_part_tbl(r_part_tbl, PART_TBL_ITEM_NR_MAX, &u4_part_nr, ADPT_HEAD_SIG, ADPT_TAIL_SIG);
	   		  if(!r)
	   		  {
	   	          puts("[mt85xx_part_tbl_init] orig pit found, create adpt pit success.\n");
	   			  return;
	   		}
	   	  }
	   	  else
	   	  {
	   	    puts("[mt85xx_part_tbl_init] orig pit found, but create adapt pit failed.\n");
	   	  }
	   	}
	   	else
	   	{
	   		puts("[mt85xx_part_tbl_init] no adpt & orig pit found.\n");
	   	}
	}
	#else
	if(r)
    {
    	u4_adpt = 0;
    	r = _i_find_part_tbl(r_part_tbl, PART_TBL_ITEM_NR_MAX, &u4_part_nr, HEAD_SIG, TAIL_SIG);
    	if (!r)
    	{
    	  /*arrive here, it means the bootloader is changed from non-adaptive feature to adaptive feature, 
    		   so the BE with adaptive feature should be upgrade*/
    	  u4_adpt = 1; 
    	  puts("[mt85xx_part_tbl_init] non-adapt to adapt, please upgrade BE firstly.\n");
    	  return;
    	}
    	puts("[mt85xx_part_tbl_init] ERROR.\n");
    }
	#endif
#else
    /*find partition table in nand*/
    r = _i_find_part_tbl(r_part_tbl, PART_TBL_ITEM_NR_MAX, &u4_part_nr, HEAD_SIG, TAIL_SIG);
    if(r)
    {
		puts("[mt85xx_part_tbl_init] ERROR.\n");
    }
#endif
}

PART_TBL_ITEM* mt85xx_get_part_info_by_name(const char* part_name)
{
    int r;
    UINT32 i;
    if (part_name == NULL)
    {
      printk("mt85xx_get_part_info_by_name,name is NULL\n");
	  return NULL;
    }
    for(i=1 ; i<=u4_part_nr; i++)
    {
        /* compare partition name*/
        r = strcmp(r_part_tbl[i].pc_name, part_name);
		if(r)
        {
            continue;
        }

		/* found */
		//p_part_info = &(r_part_tbl[i]);
		return &(r_part_tbl[i]);
    }

    /* not found */    
	//printf("[mt85xx_get_part_info_by_name] can't find part [%s]\n", part_name);
	return NULL;
}

PART_TBL_ITEM* mt85xx_get_part_info_by_id(UINT32 u4_id)
{
    UINT32 i;

    for(i=1 ; i<=u4_part_nr; i++)
    {
        /* compare id */
		if(r_part_tbl[i].u4_id != u4_id)
        {
            continue;
        }

		/* found */
		//p_part_info = &(r_part_tbl[i]);
		return &(r_part_tbl[i]);
    }

    /* not found */    
	//printf("[mt85xx_get_part_info_by_id] can't find part [%d]\n", u4_id);
	return NULL;
}

BOOL fg_is_primary_part(PART_TBL_ITEM* pr_part_item)
{
    if((pr_part_item->u4_info & PI_PRIMARY_FLAG_MASK) == 0)
		return FALSE;
	
    return TRUE;
}

UINT32 u4_get_backup_part_id(PART_TBL_ITEM* pr_part_item)
{
    return (pr_part_item->u4_info & PI_BACKUP_ID_MASK) >> PI_BACKUP_ID_OFST;
}

UINT32 u4_get_format_type(PART_TBL_ITEM* pr_part_item)
{
	return (pr_part_item->u4_info & PI_NEW_FORMAT_MASK) >> PI_NEW_FORMAT_OFST;
}

UINT32 u4_get_parent_part_id(PART_TBL_ITEM* pr_part_item)
{
	return (pr_part_item->u4_info & PI_PARENT_PART_ID_MASK) >> PI_PARENT_PART_ID_OFST;
}

PART_TBL_ITEM* get_parent_part_info(PART_TBL_ITEM* pr_part)
{
    if(pr_part==NULL)
		return NULL;

	return mt85xx_get_part_info_by_id(u4_get_parent_part_id(pr_part));	
}

PART_TBL_ITEM* get_backup_part_info(PART_TBL_ITEM* pr_part)
{
    if(pr_part==NULL)
		return NULL;

	return mt85xx_get_part_info_by_id(u4_get_backup_part_id(pr_part));
}

INT32 mt85xx_init_mtd_table(char *id)
{
    char mtd_dev[16];
	//char buffer[150] = "";
	char* buffer = kmalloc(80, GFP_KERNEL);
    struct mtd_device *dev;
    struct part_info *part;
	struct mtd_info *mtd;
    u8 pnum;
	struct mtd_partition mtd_part;
    INT32 iRet;
    
    memset(buffer,0,80);
    
    if(!fg_mtdparts_init)
    {
    	if (mtdparts_init() != 0) {
    		printf("Error initializing mtdparts!\n");
    		kfree(buffer);
    		return 1;
    	}

		fg_mtdparts_init = TRUE;
    }
	
	if (find_dev_and_part(id, &dev, &pnum, &part)) {
		printf("Partition %s not found!\n", id);
		kfree(buffer);
		return -1;
	}
	
	sprintf(mtd_dev, "%s%d", MTD_DEV_TYPE(dev->id->type), dev->id->num);
	mtd = get_mtd_device_nm(mtd_dev);
	if (IS_ERR(mtd)) {
		printf("Partition %s not found on device %s!\n", id, mtd_dev);
		kfree(buffer);
		return -2;
	}

	//sprintf(buffer, "mtd=%d", pnum);
	//sprintf(buffer, "mtd=%d(%s)", pnum, id);
	sprintf(buffer, "mtd=(%s)", id);
	
	memset(&mtd_part, 0, sizeof(mtd_part));
	mtd_part.name = buffer;
	mtd_part.size = part->size;
	mtd_part.offset = part->offset;
	iRet = add_mtd_partitions(mtd, &mtd_part, 1);
	if(iRet != 0)
	{
	    kfree(buffer);
	}

	return iRet;
}
