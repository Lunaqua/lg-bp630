/*
 * boot command for mt85xx platform
 */


/*
 * header file
 */
#include <common.h>
#include <command.h>
#include <image.h>
#include <nand.h>
#include <asm/byteorder.h>
#include <asm/arch/mt85xx_part_tbl.h>
#include <asm/arch/mt85xx_upg_status.h>
#include <asm/arch/mt85xx_recovery.h>
#include <asm/arch/nand_operation.h>
#include <asm/arch/mt85xx_pdwnc.h>
#include <stdio_dev.h>
#include <asm/arch/x_pdwnc.h>
#include <upg_config.h>
#include <asm/arch/args_to_uboot.h>


#ifdef CONFIG_CMD_MT85XX_BOOT
/*
 * defines
 */
#define BOOT_CMD_TYPE_NAND		0
#define BOOT_CMD_TYPE_REBOOT	1
#define BOOT_CMD_TYPE_TEST		9
#define BOOT_CMD_TYPE_DEFAULT	-1

#define BOOT_OK					0
#define BOOT_ERR_PART_TBL		1
#define BOOT_ERR_KERN			2
#define BOOT_ERR_ROOTFS			3


/* config */
#define CFG_VERIFY_KERNEL		1


/*
 * extern data & function
 */ 
DECLARE_GLOBAL_DATA_PTR;


/*
 * global variable
 */
extern UPG_STATUS_T r_upg_status;

/*
 * private function
 */
PART_TBL_ITEM* pr_kern_act;
PART_TBL_ITEM* _get_act_kern_part_info(UPG_STATUS_T* pr_upg_status)
{
    PART_TBL_ITEM* pr_kern;

	/* already know */
    if(pr_kern_act != NULL)
		return pr_kern_act;

    /* check */
    pr_kern = mt85xx_get_part_info_by_name(KERN_PART_NAME);
	if(fg_is_part_active(pr_kern, pr_upg_status) == TRUE)
	{
	    pr_kern_act = pr_kern;
	    return pr_kern_act;
	}

	pr_kern = mt85xx_get_part_info_by_name(KERN_BK_UP_PART_NAME);
	if(fg_is_part_active(pr_kern, pr_upg_status) == TRUE)
	{
	    pr_kern_act = pr_kern;
	    return pr_kern_act;
	}

	return NULL;
}

image_header_t _r_header;

int _i_load_kernel(PART_TBL_ITEM *p_kernel_part)
{
	int r;
	u_long data, len, checksum, addr;
	image_header_t *hdr = &_r_header;
	char boot_cmd[64];

	/* get kernel header */
	r = mtk_nand_read((u_long) hdr, p_kernel_part->u4_offset, sizeof(image_header_t));
	if (r) {
		puts("[MT85XX Boot] nand read error.\n");
		return BOOT_ERR_KERN;
	} 

	if (ntohl(hdr->ih_magic) != IH_MAGIC) {
		printf("\n[MT85XX Boot] Bad Magic Number 0x%x **\n", hdr->ih_magic);
		return BOOT_ERR_KERN;
	}

	data = (u_long)hdr;
	len = sizeof(image_header_t);

	checksum = ntohl(hdr->ih_hcrc);
	hdr->ih_hcrc = 0;

	if (crc32 (0, (uchar *)data, len) != checksum) {
		puts ("[MT85XX Boot] Bad Header hdr. checksum error.\n");
		return BOOT_ERR_KERN;
	}

	image_print_contents(hdr);
	
	/* get load dram addr */
	addr = ntohl(hdr->ih_load) - sizeof (image_header_t);
	
	sprintf(boot_cmd, "nboot 0x%08x %d 0x%08x", (UINT32)addr, nand_curr_device, p_kernel_part->u4_offset);
						       
	return run_command(boot_cmd, 0);
}



void _v_upgrade(void)
{
    int ret;
    puts("[MT85XX Boot] upgrade\n");
	if((ret = run_command("upg",0)) < 0)
	{
	    puts("[MT85XX Boot] please insert usb disc enter the \"upg\" command to upgrade back end binary.\n");
	}

    #if (!CFG_UPG_SUPPORT_JIGMODE)
	if((ret = run_command("upg usb fe",0)) < 0)
	{
	    puts("[MT85XX Boot] please insert usb disc enter the \"upg usb fe\" command to upgrade front end binary.\n");
	}
    #endif
//    puts("[MT85XX Boot] upgrade finish\n");
}


/* 
 * boot up flow 
 */
void _v_do_mt85xx_boot_nand(cmd_tbl_t *cmdtp, int flag, int argc, char *argv[])
{
	PART_TBL_ITEM *p_kernel_part;
	
	//if(r_args_to_uboot.u4_head_sig != ARGS_TO_UBOOT_HEAD_SIG)
	{
		/* check upg status */
		if(fg_is_upg_status_inited(&r_upg_status)==FALSE)
		{
			puts("[MT85XX Boot] Error: upg status is not inited.\n");
			return;
		}
	
		if((r_upg_status.u4_progress & US_PROGRESS_MASK) == US_PROGRESS_UPG_START)
		{
			/* active partitions is ok, recovery alternative partitions */
			if(i_recovery_alt_parts(&r_upg_status) != BOOT_OK)
			{
				puts("[MT85XX Boot] Error: can't recovery alternative partitions\n");
				return;
			}

			/* set progress = upg_suc */
			r_upg_status.u4_progress = (r_upg_status.u4_progress & ~US_PROGRESS_MASK) | US_PROGRESS_UPG_SUC;
			if(i_set_upg_status(&r_upg_status) != UPG_STATUS_OK)
			{
				puts("[MT85XX Boot] Error: can't set upg status\n");
				return;
			}

			_v_do_reboot();
		}
		else if((r_upg_status.u4_progress & US_PROGRESS_MASK) == US_PROGRESS_UPG_BE)
		{
    		puts("[MT85XX Boot] Error: last upg BE fail \n");
			puts("[MT85XX Boot] upgrade BE again \n");

    		return;
		}
		else if((r_upg_status.u4_progress & US_PROGRESS_MASK) == US_PROGRESS_UPG_FE)
		{
    		puts("[MT85XX Boot] Error: last upg FE fail \n");
			puts("[MT85XX Boot] upgrade FE again \n");
        	
        	return;
		}
	}

	/* set cec la pa data to gpr */
	//v_data_to_pdwnc(r_upg_status.u4_cec_pa, r_upg_status.u4_cec_la, r_upg_status.u4_cec_switch, r_upg_status.u4_cec_trad_mode);

	if(r_args_to_uboot.u4_head_sig != ARGS_TO_UBOOT_HEAD_SIG)
	{
		/* boot with active kernel */
		p_kernel_part = _get_act_kern_part_info(&r_upg_status);
		if (p_kernel_part == NULL)
		{
			puts("[MT85XX Boot] can't get kernel partition info.\n");
			return;
		}
		_i_load_kernel(p_kernel_part);
	
		/* never go here : reocvery */
		puts("[MT85XX Boot] Error: nand boot fail. kernel recovery.\n");
		i_recovery_act_parts(&r_upg_status);
	
		/* boot again */
		_i_load_kernel(p_kernel_part);
	}
	else
	{
		char boot_cmd[64];
		sprintf(boot_cmd, "bootm 0x%08x", r_args_to_uboot.u4_kernel_addr);
		run_command(boot_cmd, 0);
	}

	/* never go here : upgrade */
	puts("[MT85XX Boot] Error: recovery fail. upgrade.\n");
}

void _v_do_reboot(void)
{
	/*
	 * use powerdown watch dog to reset system
	 */
	UINT32 u4Test;

	//printk("MTK Reboot is working now.\n");
	
	PDWNC_WRITE32(REG_RW_WDT, 0xFFFFFFF0);

	for(u4Test = 0; u4Test < 1000; u4Test++)
	{

	} 
	PDWNC_WRITE32(REG_RW_WDTSET, 1);
}

void _v_do_test(void)
{
#if 0
    int ret = 0;
    u_long retnand = 0;
    nand_upgrade(0x00000000, 0x02FFFFF0, 0x00000000, 0x80000000, &retnand);
    printf("nand_upgrade: retnand = 0x%08x\n", retnand);
    //nand_read_skip_badblock(0x14000000, 0x02FFFFF0, 0x00000000, 0x80000000, &retnand);
	nand_read_skip_bad(0x14000000, 0x00000000, 0x02FFFFF0);
    printf("nand_read_skip_bad: retnand = 0x%08x\n", retnand);
    
    ret = memcmp((void *)0x10000000,(void *) 0x14000000, 0x02FFFFF0);
    printf("memcmp ret=%d\n", ret);
#endif
}

void _v_help(void)
{
    puts("mt85xx_boot help:\n");
    puts("mt85xx_boot nand : boot from nand\n");
}

/*
 * command
 */
int do_mt85xx_boot (cmd_tbl_t *cmdtp, int flag, int argc, char *argv[])
{
    int type = BOOT_CMD_TYPE_DEFAULT;
	
    /* parse argv */
    if(argc >= 2)
    {
		if(strncmp(argv[1], "nand", 4)==0)
		{
		    type = BOOT_CMD_TYPE_NAND;
		}
		if(strncmp(argv[1], "reboot", 6)==0)
		{
		    type = BOOT_CMD_TYPE_REBOOT;
		}
		if(strncmp(argv[1], "test", 4)==0)
		{
		    type = BOOT_CMD_TYPE_TEST;
		}
    }

    /* execute boot command */
	switch (type){
	case BOOT_CMD_TYPE_NAND:
        #if (CFG_UPG_SUPPORT_JIGMODE)
        if(fg_ne_bl_chk_jig())
        {
          _v_upgrade();
        }
        else
        #endif
        {
          _v_do_mt85xx_boot_nand(cmdtp, flag, argc, argv);
          _v_upgrade();
        }
        break;
	case BOOT_CMD_TYPE_REBOOT:
		_v_do_reboot();
		break;
	case BOOT_CMD_TYPE_TEST:
		_v_do_test();
		break;
	default:
		_v_help();
		break;
	}

    /* end */	
    return 1;
}

U_BOOT_CMD(
	mt85xx_boot,	2,	1,	do_mt85xx_boot,
	"mt85xx_boot   - boot command for mt85xx platform\n",
	"mt85xx_boot nand : boot from nand\n"
	"mt85xx_boot reboot : wdt reboot\n"
);
int do_log_disable (cmd_tbl_t *cmdtp, int flag, int argc, char *argv[])
{
	int i=0;
	UINT32 app_en=0,driver_en=0,mmw_en=0,mw_en=0,sys_en=0;
		if(strcmp (argv[1], "on") == 0)
		{

			r_upg_status.u4_log_cfg = (r_upg_status.u4_log_cfg & ~US_PRINT_MASK) | US_PRINT_DIS;
			if(i_set_upg_status(&r_upg_status) != UPG_STATUS_OK)
			{
				puts("can't set print disable\n");
				return -1;
			}
			else 
			{
				puts("set print disable success\n");
			}
		}
		else if(strcmp (argv[1], "off") == 0)
		{
			r_upg_status.u4_log_cfg = (r_upg_status.u4_log_cfg & ~US_PRINT_MASK) | US_PRINT_EN;
			if(i_set_upg_status(&r_upg_status) != UPG_STATUS_OK)
			{
				puts("can't set print enable\n");
				return -1;
			}
			else 
			{
				puts("set print enable success\n");
			}
		}
		else
		{
			for (i=1;i<=4;i++)
			{
				if (strcmp (argv[i], "app") == 0)
				{
					app_en=0x80;
				puts("app_en=0x7F\n");
				}
				else if(strcmp (argv[i], "driver") == 0)
				{
					driver_en=0x40;
					puts("driver_en=0xBF\n");
				}
				else if(strcmp (argv[i], "mmw") == 0)
				{
					mmw_en=0x20;
					puts("mmw_en=0xDF\n");
				}
				else if(strcmp (argv[i], "mw") == 0)
				{
					mw_en=0x10;
					puts("mw_en=0xEF\n");
				}
				else if(strcmp (argv[i], "sys") == 0)
				{
					sys_en=0x08;
					puts("sys_en=0xF7\n");
				}
		 		 else
				{
					i=5;
				}
			}			
			r_upg_status.u4_log_cfg = (r_upg_status.u4_log_cfg & ~US_PRINT_MASK)| app_en | driver_en | mmw_en | mw_en | sys_en;
//				r_upg_status.u4_log_cfg = US_PRINT_MASK & app_en&driver_en&mmw_en&mw_en&sys_en; 
			if(i_set_upg_status(&r_upg_status) != UPG_STATUS_OK)
			{
				puts("can't set print enable\n");
				return -1;
			}
			else 
			{
				puts("set print enable some Layer success\n");
			}
		}

		return 0;
}
U_BOOT_CMD(
	dis_log,	7,	1,	do_log_disable,
	"log_enable\n",
	"log_enable_fail\n"
);

#endif

