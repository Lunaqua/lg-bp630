/*
 * mt85xx upg status functions
 */


/*
 * header file
 */
#include <common.h>
#include <nand.h>
#include <malloc.h>
#include <asm/arch/mt85xx_upg_status.h>
#include <asm/arch/mt85xx_part_tbl.h>
#include <asm/arch/nand_operation.h>
#include <asm/arch/args_to_uboot.h>


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
static int _i_init_upg_status(UPG_STATUS_T* pr_upg_status, UINT32 *pu4_raw_data)
{
	UPG_STATUS_T* pr_raw;

	/* check raw data sig */
	pr_raw = (UPG_STATUS_T*)pu4_raw_data;
	if(fg_is_upg_status_inited(pr_raw) == FALSE)
	{
	    puts("[_i_init_upg_status] check sig FAIL.\n");
	    return -ERR_END_SIG;
	}

	/* memcpy */
	memcpy(pr_upg_status, pr_raw, sizeof(UPG_STATUS_T));

	return UPG_STATUS_OK;
}

static int _i_gen_raw_data_from_upg_status(UPG_STATUS_T* pr_upg_status, UINT32 *pu4_raw_data, UINT32 u4_raw_data_sz)
{
    if(sizeof(UPG_STATUS_T) > u4_raw_data_sz * sizeof(UINT32))
    {
        printf("[_i_gen_raw_data_from_upg_status] upg_status size[0x%08x] > raw data buffer size[0x%08x]\n", 
			sizeof(UPG_STATUS_T), (u4_raw_data_sz * sizeof(UINT32)));
		return -ERR_BUF_SZ;
    }
		
	memcpy(pu4_raw_data, pr_upg_status, sizeof(UPG_STATUS_T));

	return UPG_STATUS_OK;
}

static int _i_cmp_upg_status(UPG_STATUS_T *pr_upg_status_1, UPG_STATUS_T *pr_upg_status_2)
{
	return memcmp(pr_upg_status_1, pr_upg_status_2, sizeof(UPG_STATUS_T));
}

static int _i_gen_upg_status_by_part_tbl(UPG_STATUS_T* pr_upg_status)
{
    UINT32 i, u4_part_end;
	UINT32 u4_pri_count = 0;

	/* get partition table and partition number */
//	extern PART_TBL_ITEM r_part_tbl[];
//	extern UINT32 u4_part_nr;

    /* gen upg_status */
	memset(pr_upg_status, 0, sizeof(UPG_STATUS_T));
	pr_upg_status->u4_head_sig           = UPG_STATUS_HEAD_SIG;
	pr_upg_status->u4_progress           = US_PROGRESS_UPG_SUC;
	pr_upg_status->u4_end_sig            = UPG_STATUS_END_SIG;

    u4_part_end = u4_part_nr+1;
	for(i=1; i<u4_part_end; i++)
	{
	    if(fg_is_primary_part(&(r_part_tbl[i])) == TRUE)
	    {
	        pr_upg_status->au4_active_id_list[u4_pri_count++] = r_part_tbl[i].u4_id;
	    }
	}

	pr_upg_status->u4_active_id_list_nr  = u4_pri_count;

	/* boot args */
	pr_upg_status->u4_end_boot_sig =  UPG_STATUS_END_BOOT_SIG;

    return UPG_STATUS_OK;
}

static int _i_set_upg_status_into_nand(UPG_STATUS_T* pr_upg_status)
{
    PART_TBL_ITEM *p_upg_status_part;
    CHAR* pu1_buf;
	UINT32 *pu4_buf;
	UINT32 u4BlockSize;
	int r;

	/* get block size */
	u4BlockSize = nand_info[nand_curr_device].erasesize;
	if(u4BlockSize == 0)
	{
	    puts("[_i_set_upg_status_into_nand] Get block size FAIL.\n");
	    return -ERR_BLOCK_SIZE_ZERO;
	}
	
    /* get memory buffer */
	pu1_buf = (CHAR*)malloc((u4BlockSize*2) + 4);
	if(pu1_buf == NULL)
	{
	    puts("[_i_set_upg_status_into_nand] malloc FAIL.\n");
		return -ERR_MALLOC_FAIL;
	}
	pu4_buf = (UINT32*)(((UINT32)pu1_buf + 4)&0xFFFFFFFC);

    /* set 1st data block */
	r = _i_gen_raw_data_from_upg_status(pr_upg_status, pu4_buf, u4BlockSize/4);
	if(r != UPG_STATUS_OK)
	{
	    puts("[_i_set_upg_status_into_nand] set 1st data FAIL.\n");
		return r;
	}

    /* set 2nd data block */
	r = _i_gen_raw_data_from_upg_status(pr_upg_status, (pu4_buf + u4BlockSize/4), u4BlockSize/4);
	if(r != UPG_STATUS_OK)
	{
	    puts("[_i_set_upg_status_into_nand] set 2nd data FAIL.\n");
		return r;
	}

	/* get upg_status partition info */
    p_upg_status_part = mt85xx_get_part_info_by_name(UPG_STATUS_PART_NAME);
	if(p_upg_status_part == NULL)
	{
	    puts("[_i_set_upg_status_into_nand] Get upg_status partition info FAIL.\n");
		free(pu1_buf);
	    return -ERR_NO_PART_INFO;
	}

	/* write into nand */
	r = mtk_nand_write((u_long)pu4_buf, (u4BlockSize*2), p_upg_status_part->u4_offset);
	if(r != 0)
	{
	    puts("[_i_set_upg_status_into_nand] nand write FAIL.\n");
		free(pu1_buf);
	    return -ERR_NAND_WRITE_FAIL;
	}

	free(pu1_buf);
	return UPG_STATUS_OK;
}

static int _i_get_upg_status_in_nand(UPG_STATUS_T* pr_upg_status)
{
    PART_TBL_ITEM *p_upg_status_part;
	UPG_STATUS_T r_upg_status_2;
	UINT32 u4BlockSize;
	CHAR *pu1_buf;
	UINT32 *pu4_buf;
	int r;
   
    if((r_args_to_uboot.u4_head_sig == ARGS_TO_UBOOT_HEAD_SIG) 
		&& (r_args_to_uboot.u4_upg_status_addr != 0)) 
    {
        if(UPG_STATUS_OK == _i_init_upg_status(pr_upg_status, (UINT32 *)r_args_to_uboot.u4_upg_status_addr))
        {
            puts("[_i_get_upg_status_in_nand]upg status passed from preloader\n");
            return UPG_STATUS_OK;
        }
    }
	
	/* get upg_status partition info */
    p_upg_status_part = mt85xx_get_part_info_by_name(UPG_STATUS_PART_NAME);
	if(p_upg_status_part == NULL)
	{
	    puts("[_i_get_upg_status_in_nand] Get upg_status partition info FAIL.\n");
	    return -ERR_NO_PART_INFO;
	}

	/* get block size */
	u4BlockSize = nand_info[nand_curr_device].erasesize;
	if(u4BlockSize == 0)
	{
	    puts("[_i_get_upg_status_in_nand] Get block size FAIL.\n");
	    return -ERR_BLOCK_SIZE_ZERO;
	}

	/* read upg_status partition */
	pu1_buf = (CHAR *)malloc(u4BlockSize*2 + 4);
	if(pu1_buf == NULL)
	{
	    puts("[_i_get_upg_status_in_nand] malloc FAIL.\n");
	    return -ERR_MALLOC_FAIL;
	}

	pu4_buf = (UINT32*)(((UINT32)pu1_buf + 4)&0xFFFFFFFC);
	r = mtk_nand_read((u_long)pu4_buf, p_upg_status_part->u4_offset, (u4BlockSize*2));
	if(r != 0)
	{
	    printf("[_i_get_upg_status_in_nand] mtk_nand_read FAIL. error number:[%d]\n", r);
		free(pu1_buf);
		return -ERR_NAND_READ_FAIL;
	}

	/* get 1st data block */
	r = _i_init_upg_status(pr_upg_status, pu4_buf);
	if(r != UPG_STATUS_OK)
	{
		/* err handle - no upg_status in nand*/
		puts("[_i_get_upg_status_in_nand] no upg_status in nand.\n");
		
		/* gen upg_status */
		r = _i_gen_upg_status_by_part_tbl(pr_upg_status);
		if(r != UPG_STATUS_OK)
		{
		    printf("[_i_get_upg_status_in_nand] _i_gen_upg_status_by_part_tbl FAIL. error number:[%d]\n", r);
			free(pu1_buf);
			return r;		
		}
		
		/* set upg_status into nand */
		r = _i_set_upg_status_into_nand(pr_upg_status);
		if(r != UPG_STATUS_OK)
		{
		    printf("[_i_get_upg_status_in_nand] _i_set_upg_status_into_nand FAIL. error number:[%d]\n", r);
			free(pu1_buf);
			return r;
		}
		
		goto get_upg_status_done;
	}
	
	/* get 2nd data block */
	r = _i_init_upg_status(&r_upg_status_2, (UINT32*)(pu4_buf + (u4BlockSize/4)));
	if (r != UPG_STATUS_OK)
	{
		/* err can handle - can't find 2nd upg_status in nand */
		puts("[_i_get_upg_status_in_nand] can't find 2nd upg_status in nand.\n");
		
		/* set upg_status into nand */
		r = _i_set_upg_status_into_nand(pr_upg_status);
		if(r != UPG_STATUS_OK)
		{
		    printf("[_i_get_upg_status_in_nand] _i_set_upg_status_into_nand FAIL. error number:[%d]\n", r);
			free(pu1_buf);
			return r;
		}

		goto get_upg_status_done;
	}

	/* compare 2 data block */
	r = _i_cmp_upg_status(pr_upg_status, &r_upg_status_2);
	if(r != 0)
	{
		/* err can handle - 1st upg_status != 2nd upg_status */
		puts("[_i_get_upg_status_in_nand] 1st upg_status != 2nd upg_status.\n");
	
		/* set upg_status into nand */
		r = _i_set_upg_status_into_nand(pr_upg_status);
		if(r != UPG_STATUS_OK)
		{
		    printf("[_i_get_upg_status_in_nand] i_set_upg_status FAIL. error number:[%d]\n", r);
			free(pu1_buf);
			return r;
		}
		
		goto get_upg_status_done;
	}

get_upg_status_done:
    
	free(pu1_buf);
	return UPG_STATUS_OK;
}


/*
 * public functions
 */
int i_get_upg_status(UPG_STATUS_T* pr_upg_status)
{
    int r;
	
    /* check input data */
	if(pr_upg_status == NULL)
		return -ERR_INPUT_ARGS;

	/* get upg_status in nand */
	r = _i_get_upg_status_in_nand(pr_upg_status);
	if(r!= UPG_STATUS_OK)
	{
		printf("[i_get_upg_status]ERROR!!! err number:[%d]\n", r);
		return r;
	}

	return UPG_STATUS_OK;	
}

int i_set_upg_status(UPG_STATUS_T* pr_upg_status)
{
    int r;

    /* check input data */
	if(pr_upg_status == NULL)
		return -ERR_INPUT_ARGS;

	/* set upg_status in to nand */
	r = _i_set_upg_status_into_nand(pr_upg_status);
	if(r!= UPG_STATUS_OK)
	{
		printf("[i_set_upg_status]ERROR!!! err number:[%d]\n", r);
		return r;
	}
	
	return UPG_STATUS_OK;
}

BOOL fg_is_upg_status_inited(UPG_STATUS_T* pr_upg_status)
{
	if(	(pr_upg_status->u4_head_sig != UPG_STATUS_HEAD_SIG) ||
		(pr_upg_status->u4_end_sig != UPG_STATUS_END_SIG) ||
		(pr_upg_status->u4_end_boot_sig != UPG_STATUS_END_BOOT_SIG))
	{
		return FALSE;
	}

	return TRUE;
}

BOOL fg_is_part_active(PART_TBL_ITEM* pr_part, UPG_STATUS_T* pr_upg_status)
{
    UINT32 i;
	
    for(i=0; i<pr_upg_status->u4_active_id_list_nr; i++)
    {
        if(pr_part->u4_id == pr_upg_status->au4_active_id_list[i])
        {
            return TRUE;
        }
    }

	return FALSE;
}


