/*
 * See file CREDITS for list of people who contributed to this
 * project.
 *
 *  Copyright(C) 2006 NXP BV, All rights reserved.
 *  by Jean-Paul Saman
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

#include <common.h>
#include <asm/io.h>
#include <asm/arch/mt85xx.h>
#include <asm/arch/sys_proto.h>
#include <asm/arch/sys_info.h>
#include <i2c.h>
#include <asm/mach-types.h>

#include <asm/arch/x_typedef.h>
#include <asm/arch/x_bim.h>
//#include <usb.h>
#include <mt85xx_gpio.h>
#include <configs/mt8530_base.h>

#include <asm/arch/mt85xx_part_tbl.h>
#include <asm/arch/mt85xx_upg_status.h>
#include <stdio_dev.h>
#include "drv_config_mem.h"
#include <asm/arch/args_to_uboot.h>
#include <upg_config.h>

#include <chip_ver.h>
#if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530)
#include <BL_VERSION_8530.h>
#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550)
#include <BL_VERSION_8550.h>
#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555)
#include <BL_VERSION_8555.h>
#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8560)
#include <BL_VERSION_8560.h>
#elif (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8580)
#include <BL_VERSION_8580.h>
#endif

extern UPG_STATUS_T r_upg_status;
ARGS_TO_UBOOT r_args_to_uboot;

DECLARE_GLOBAL_DATA_PTR;


/*******************************************************
 * Routine: delay
 * Description: spinning delay to use before udelay works
 ******************************************************/
static inline void delay (unsigned long loops)
{
    __asm__ volatile ("1:\n" "subs %0, %1, #1\n"
        "bne 1b":"=r" (loops):"0" (loops));
}

#if defined(CONFIG_SHOW_BOOT_PROGRESS)
void show_boot_progress(int progress)
{
    printf("Boot reached stage %d\n", progress);
}
#endif


/*****************************************
 * Routine: board_init
 * Description: Early hardware init.
 *****************************************/
#define MEM_BUF_ARGS_TO_UBOOT       0xC0000400
#define AVM_OFFSET_LIMIT                0x0100000
int board_init (void)
{
    DECLARE_GLOBAL_DATA_PTR;
    gd->bd->bi_arch_number = MACH_TYPE_MT85XX;
    gd->bd->bi_boot_params = 0x00000100;

  #if (CONFIG_MT85XX_FPGA==0)
    pinmux_init();

    /* get args to uboot (from preloader) */
    if(((ARGS_TO_UBOOT*)MEM_BUF_ARGS_TO_UBOOT)->u4_head_sig == ARGS_TO_UBOOT_HEAD_SIG)
    {
        r_args_to_uboot = *((ARGS_TO_UBOOT*)MEM_BUF_ARGS_TO_UBOOT);
    }
  #else
        r_args_to_uboot = *((ARGS_TO_UBOOT*)MEM_BUF_ARGS_TO_UBOOT);
  #endif

    return 0;
}

/*******************************************************
 * Routine: misc_init_r
 * Description: Init ethernet (done here so udelay works)
 ********************************************************/
extern UPG_STATUS_T r_upg_status;

PART_TBL_ITEM* pr_rootfs_act;
PART_TBL_ITEM* _get_act_rootfs_part_info(UPG_STATUS_T* pr_upg_status)
{
    PART_TBL_ITEM* pr_rootfs;

    /* already know */
    if(pr_rootfs_act != NULL)
        return pr_rootfs_act;

    /* check */
    pr_rootfs = mt85xx_get_part_info_by_name(ROOTFS_PART_NAME);
    if(fg_is_part_active(pr_rootfs, pr_upg_status) == TRUE)
    {
        pr_rootfs_act = pr_rootfs;
        return pr_rootfs_act;
    }

    pr_rootfs = mt85xx_get_part_info_by_name(ROOTFS_BK_UP_PART_NAME);
    if(fg_is_part_active(pr_rootfs, pr_upg_status) == TRUE)
    {
        pr_rootfs_act = pr_rootfs;
        return pr_rootfs_act;
    }

    return NULL;
}

#define CFG_BOOTARGS_ROOT "noinitrd root=/dev/mtdblock7 ro"
#define CFG_BOOGARGS_MTD  "mtdparts=mt85xx_nand:4M(boot_1),4M(boot_2),4M(part_info_1),4M(part_info_2),4M(kernel_1),4M(kernel_2),\
128M(rootfs_1),128M(rootfs_2),4M(standby_1),4M(standby_2),4M(fe_bin_1),4M(fe_bin_2),4M(fe_test_data),\
4M(APDA),4M(CPS_manager),4M(key_block_1),4M(key_block_2),4M(fe_parameters),4M(fe_power_curve),4M(upg_status),4M(acfg),\
4M(misc_data),4M(fast_init_logo),4M(fast_init_parameters),4M(log),256M(BUDA),8M(browser),32M(cust_part_1)"
#define CFG_BOOTARGS_CONSOLE "console=ttyMT0 kgdboc=ttyMT0 "
#define CFG_BOOTARGS_MEM    "mem=256M"
char _bootargs_buf[1024];
void _v_set_boot_args(void)
{

    int i;
    unsigned int fl_base,  fl_size, avm_base, temp;
    char _tmp_buf[64];
    PART_TBL_ITEM* pr_rootfs_part_act;

    fl_base = BIM_READ32(REG_RW_GPRDW2);
    fl_size = BIM_READ32(REG_RW_GPRDW4);
    avm_base = BIM_READ32(REG_RW_GPRDW5);

    temp = fl_base - AVM_OFFSET_LIMIT;
    if (avm_base == temp)
    {
        fl_base = avm_base;
        fl_size += AVM_OFFSET_LIMIT ;
    }

    printf("Bootloader version %d\n", BL_VERSION);

    /* rootfs */
    //sprintf(_tmp_buf, "noinitrd root=/dev/mtdblock%d ro ", pr_rootfs_part_act->u4_id);
    if(r_args_to_uboot.u4_head_sig != ARGS_TO_UBOOT_HEAD_SIG)
    {
        pr_rootfs_part_act = _get_act_rootfs_part_info(&r_upg_status);
        if(pr_rootfs_part_act == NULL)
        {
            return ;
        }
        mtk_nand_read((fl_base - pr_rootfs_part_act->u4_size), pr_rootfs_part_act->u4_offset, pr_rootfs_part_act->u4_size);

        sprintf(_tmp_buf, "root=/dev/ram0 rw initrd=0x%08x,%dM ",
                (fl_base - pr_rootfs_part_act->u4_size), (pr_rootfs_part_act->u4_size>>20));
    }
    else
    {
        sprintf(_tmp_buf, "root=/dev/ram0 rw initrd=0x%08x,0x%08x ",
                (r_args_to_uboot.u4_initrd_addr), (r_args_to_uboot.u4_initrd_size));
    }
    strcpy(_bootargs_buf, _tmp_buf);

#if 0
    /* mtdparts */
    strcat(_bootargs_buf, "mtdparts=mt85xx_nand:");

    /*first*/
    i = 1;
    for(; i<=u4_part_nr; i++)
    {
        if(u4_get_format_type(&(r_part_tbl[i])) < PIFT_UBI_VOL)
        {
            sprintf(_tmp_buf, "%dK(%s)",
                    r_part_tbl[i].u4_size >> 10,
                    r_part_tbl[i].pc_name);

            strcat(_bootargs_buf, _tmp_buf);
            break;
        }
    }

    /* rest */
    i++;
    for(; i<=u4_part_nr; i++)
    {
        if(u4_get_format_type(&(r_part_tbl[i])) < PIFT_UBI_VOL)
        {
            sprintf(_tmp_buf, ",%dK(%s)",
                    r_part_tbl[i].u4_size >> 10,
                    r_part_tbl[i].pc_name);

            strcat(_bootargs_buf, _tmp_buf);
        }
    }

    /* end */
    sprintf(_tmp_buf, " ");

    strcat(_bootargs_buf, _tmp_buf);
#endif

    /* console */
    strcat(_bootargs_buf, CFG_BOOTARGS_CONSOLE);

    /* dram size */
    sprintf(_tmp_buf, "mem=%dM ",
                    (gd->bd->bi_dram[0].size >> 20));
    strcat(_bootargs_buf, _tmp_buf);

    /* mt85xx reserve memory (for fast logo) */
    sprintf(_tmp_buf, "mt85xx_reserve=%dM,%dM ", // base,size
                    (fl_base >> 20),
                    (fl_size >> 20));
    strcat(_bootargs_buf, _tmp_buf);

    /* mt85xx reserve memory (for drvmem( */
    //if (CONFIG_CONTINUOUS_CH1_SIZE != 0 && CONFIG_CONTINUOUS_CH2_SIZE != 0)
    if ((CONFIG_CONTINUOUS_CH1_SIZE != 0) || (CONFIG_CONTINUOUS_CH2_SIZE != 0))
    {
      if (r_args_to_uboot.u4_initrd_addr > r_args_to_uboot.u4_dram_size_ch1)
      {
        sprintf(_tmp_buf, "drvmem=%dM,%dM ",
            (r_args_to_uboot.u4_dram_size_ch1 - CONFIG_CONTINUOUS_CH1_SIZE) >> 20,
            (CONFIG_CONTINUOUS_CH1_SIZE + CONFIG_CONTINUOUS_CH2_SIZE) >> 20);
      }
      else
      {
        sprintf(_tmp_buf, "drvmem=%dM,%dM ",
            (r_args_to_uboot.u4_initrd_addr - CONFIG_CONTINUOUS_CH1_SIZE) >> 20,
            (CONFIG_CONTINUOUS_CH1_SIZE + CONFIG_CONTINUOUS_CH2_SIZE) >> 20);
      }
        strcat(_bootargs_buf, _tmp_buf);
    }

		/* bootloader version */
		sprintf(_tmp_buf, "BL_Ver=%d ", BL_VERSION);
		strcat(_bootargs_buf, _tmp_buf);
		
    /* disable print setting */
    if(disable_print)
    {
        sprintf(_tmp_buf, "disable_print=%d quiet", disable_print);
        strcat(_bootargs_buf, _tmp_buf);
    }
    setenv("bootargs", _bootargs_buf);
}

void print_args_to_uboot(void)
{
    if(r_args_to_uboot.u4_head_sig != ARGS_TO_UBOOT_HEAD_SIG)
    {
        puts("no r_args_to_uboot\n");
        return ;
    }

    printf("r_args_to_uboot:\n");
    printf("\thead sig\t: 0x%08x\n", r_args_to_uboot.u4_head_sig);
    printf("\tversion\t: %d\n", r_args_to_uboot.u4_version);
    printf("\tboot type\t: %d\n", r_args_to_uboot.u4_boot_type);
    printf("\tdram ch1\t: 0x%08x\n", r_args_to_uboot.u4_dram_size_ch1);
    printf("\tdram ch2\t: 0x%08x\n", r_args_to_uboot.u4_dram_size_ch2);
    printf("\tkern addr\t: 0x%08x\n", r_args_to_uboot.u4_kernel_addr);
    printf("\tinitrd addr\t: 0x%08x\n", r_args_to_uboot.u4_initrd_addr);
    printf("\tinitrd size\t: 0x%08x\n", r_args_to_uboot.u4_initrd_size);
}

#define SB_BOOT_TYPE_NORMAL     0
#define SB_BOOT_TYPE_RECOVERY   1
#define SB_BOOT_TYPE_UPG        2
#define SB_BOOT_TYPE_ERR        0xff
int misc_init_r (void)
{
    UINT32 u4BootType;

    /* print dbg msg - args to uboot */
    print_args_to_uboot();

    /* bim 2 way write */
    puts("enable bim two way write.\n");
    BIM_WRITE32(REG_RW_MISC2, (BIM_READ32(REG_RW_MISC2) & ~REG_SAFEMODE_MASK));

    /* set boot args */
    _v_set_boot_args();

    /* boot type */
    if(r_args_to_uboot.u4_head_sig != ARGS_TO_UBOOT_HEAD_SIG)
    {
        u4BootType = BIM_READ32(REG_RW_GPRDW1);
    }
    else
    {
        u4BootType = r_args_to_uboot.u4_boot_type;
    }
    printf("boot type:[%d]\n", u4BootType);

    switch (u4BootType){
        case SB_BOOT_TYPE_NORMAL:
            break;
        case SB_BOOT_TYPE_RECOVERY:
            /* recovery */
            i_recovery_act_parts(&r_upg_status);
            break;
        case SB_BOOT_TYPE_UPG:
            /* upg */
            if(run_command("upg",0) < 0)
            {
#ifdef CONFIG_MMC
                puts("[MT85XX Boot] can't upgrade back end binary from usb interface, try mmc interface.\n");
                goto try_mmc_upgrade;
#endif
                puts("[MT85XX Boot] please insert usb disc and reset to upgrade back end binary.\n");
                while(1);
            }

#if (!CFG_UPG_SUPPORT_JIGMODE) && (CFG_UPG_SUPPORT_FE)
            if(run_command("upg usb fe",0) < 0)
            {
                puts("[MT85XX Boot] please insert usb disc and reset to upgrade fe binary.\n");
                while(1);
            }
          #endif

#ifdef CONFIG_MMC

          // if usb upgrade successfully, then go to reboot directly, don't try mmc interface
          goto upgrade_done_reboot;

try_mmc_upgrade:

#ifdef CONFIG_GENERIC_MMC
            puts ("MMC interface upgrade :   \n");
            mmc_initialize (gd->bd);
#endif
            if(run_command("upg mmc",0) < 0)
            {
                puts("[MT85XX Boot] please insert sd/sdhc/mmc memory card and reset to upgrade back end binary.\n");
                while(1);
            }

#if (!CFG_UPG_SUPPORT_JIGMODE) && (CFG_UPG_SUPPORT_FE)
            if(run_command("upg mmc fe",0) < 0)
            {
                puts("[MT85XX Boot] please insert sd/sdhc/mmc memory card and reset to upgrade fe binary.\n");
                while(1);
            }
#endif

upgrade_done_reboot:
#endif

            /* reboot */
            while(1)
            {
            #if ( !CFG_UPG_SUPPORT_JIGMODE && !SUPPORT_UPG_LED_CUSTOMER_T)
                run_command("mt85xx_boot reboot",0);
            #endif
            }
            break;
        case SB_BOOT_TYPE_ERR:
        default:
            // for 8550 preloader not sync yet, boot normal.
            // after 8550 sync, there should be a dead loop in this case
            while(1);
            break;
    }

    return 0;
}


/**********************************************
 * Routine: dram_init
 * Description: sets uboots idea of sdram size
 **********************************************/
int dram_init (void)
{
    u32 btype;

    btype = get_board_type();

    display_board_info(btype);

    /* get dram size data from preloader */
    gd->bd->bi_dram[0].start = PHYS_SDRAM_1;
  #if CONFIG_MT85XX_FPGA
    gd->bd->bi_dram[0].size = 0x08000000;
  #else
    if(r_args_to_uboot.u4_head_sig != ARGS_TO_UBOOT_HEAD_SIG)
    {
        gd->bd->bi_dram[0].size = BIM_READ32(REG_RW_GPRDW0);
    }
    else
    {
        gd->bd->bi_dram[0].size = r_args_to_uboot.u4_dram_size_ch1 + r_args_to_uboot.u4_dram_size_ch2;
    }
  #endif

    return 0;
}

#if 0
int musb_init (void)
{
    int i;

    /* setup usb and read file. */
    // reset usb
    usb_stop();
    printf("(Re)start USB...\n");

    // set usb to full speed.
    i = usb_speed(0);
    if (i < 0)
    {
        printf("usb set speed failed\n");
        return 1;
    }

    // reinit usb, and get device number.
    i = usb_init();
    if (i < 0)
    {
        printf("usb init failed\n");
        return 1;
    }

    return 0;
}
#endif

