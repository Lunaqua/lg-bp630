/*
 * mt85xx recovery functions
 */


/*
 * header file
 */
#include <common.h>
#include <asm/arch/mt85xx_upg_status.h>
#include <asm/arch/mt85xx_part_tbl.h>
#include <asm/arch/mt85xx_recovery.h>
//extern int nand_read_raw_skip_bad(u_long dstaddr, u_long noffset, u_long size);
//extern int nand_write_raw_skip_bad(u_long srcaddr, u_long size, u_long noffset, int partid);


/*
 * define
 */


/*
 * private variable
 */


/*
 * global variable
 */


/*
 * private functions
 */
#define BUFFER_TMP	0x08000000 // same as UPG_IMAGE_BUFFER_ADDR
#define BUFFER_SIZE	80*1024*1024 // same as UPG_IMAGE_BUFFER_SIZE
int _i_recovery_part(PART_TBL_ITEM* pr_dst_part, PART_TBL_ITEM* pr_src_part)
{
	int r;
	UINT32 u4MinSize = MIN(pr_dst_part->u4_size, pr_src_part->u4_size);

#if 1 // temp solution for preventing bad block happened
	UINT32 u4BadBlockBuf = MIN((u4MinSize>>3), 0x00400000);
	u4MinSize = u4MinSize - u4BadBlockBuf;
#endif

	/* check */
	if(u4MinSize>BUFFER_SIZE)
	{
		return -RECOVER_ERR_NO_MEM_BUF;
	}
	
	/* read src data from nand */
	r = mtk_nand_read(BUFFER_TMP, pr_src_part->u4_offset, u4MinSize);
	if(r)
	{
		return r;
	}

	/* write data into dst partition */
	r = mtk_nand_write_align(BUFFER_TMP, u4MinSize, pr_dst_part->u4_offset);
	if(r)
	{
		return r;
	}

	return RECOVER_OK;
}

static int _i_recovery_all_parts(UPG_STATUS_T *pr_upg_status, int active)
{
	int r;
    UINT32 i;
	PART_TBL_ITEM *pr_dst_part, *pr_src_part, *pr_tmp;
	
	for(i=0; i<pr_upg_status->u4_active_id_list_nr; i++)
	{
		printf("[recovery]%d: act_id=%d\n", i, pr_upg_status->au4_active_id_list[i]);
		
	    /* get active partition info */
		pr_dst_part = mt85xx_get_part_info_by_id(pr_upg_status->au4_active_id_list[i]);
		if(pr_dst_part == NULL)
		{
		    puts("[_i_recover_all_parts] get act part info fail.\n");
			return -RECOVER_ERR_NO_ACTIVE_PART;
		}
		printf("\tact_id=%d, name=%s, info=0x%08x\n", pr_dst_part->u4_id, pr_dst_part->pc_name, pr_dst_part->u4_info);

	    /* get alternative partition info */
		pr_src_part = get_backup_part_info(pr_dst_part);
		if(pr_src_part == NULL)
		{
		    puts("[_i_recover_all_parts] get backup part info fail.\n");
			return -RECOVER_ERR_NO_BACKUP_PART;
		}
		printf("\tbak_id=%d, name=%s, info=0x%08x\n", pr_src_part->u4_id, pr_src_part->pc_name, pr_src_part->u4_info);

		/* next if no backup */
		if(pr_dst_part->u4_id == pr_src_part->u4_id)
		{
			puts("\tno backup\n");
			continue;
		}

		/* next if not mtd data */
		if(	(u4_get_format_type(pr_dst_part)==PIFT_UBI_VOL) ||
			(u4_get_format_type(pr_src_part)==PIFT_UBI_VOL))
		{
			puts("\tno support ubi recovery\n");
			continue;
		}

		/* decide dst and src partition */
		if(active==0)
		{
			/* swap act and alt */
			pr_tmp = pr_dst_part; pr_dst_part = pr_src_part; pr_src_part = pr_tmp;
		}

		/* recovery */
		printf("\trecovery [%d -> %d].\n", pr_src_part->u4_id, pr_dst_part->u4_id);
		r = _i_recovery_part(pr_dst_part, pr_src_part);
		if(r)
		{
			puts("[_i_recover_all_parts] recovery error\n");
			return r;
		}
	}

	return RECOVER_OK;
}

/*
 * public functions
 */
int i_recovery_act_parts(UPG_STATUS_T *pr_upg_status)
{
    puts("[MT85XX Boot] recovery all active partition\n");
	return _i_recovery_all_parts(pr_upg_status, 1);
}

int i_recovery_alt_parts(UPG_STATUS_T *pr_upg_status)
{
    puts("[MT85XX Boot] recovery all alternative partition\n");
	return _i_recovery_all_parts(pr_upg_status, 0);
}

