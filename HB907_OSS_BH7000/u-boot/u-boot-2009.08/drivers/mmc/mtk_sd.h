/*
 *  linux/drivers/mmc/mtk_sd.h - Secure Digital Host Controller Interface driver
 *
 *  Copyright (C) 2009 Jethro Chang, All Rights Reserved.
 *
 */
 
#define CC_CARD_DETECT_PIN
#define SD_BUF_ALLOC_IN_SATA_BLTIN

#define SD_READ_ERROR_RETRY
#define SD_READ_ERROR_RETRY_CNT   4
#define SD_FORCE_REMOVE_RETRY_CNT   4
//#define USE_SDCR_SEMA

#define FCI_LOG_ENABLE
//#define SD_CMD_DEBUG
#define FCI_READ_WRITE_TEST

//#define CC_PIO_MODE_SUPPORT


/**
* Basic Type definition
*/

#define UINT32 uint32_t
#define UINT16 unsigned short
#define UINT8 unsigned char

#define INT32 int32_t
#define INT16 signed short
#define INT8 signed char

#define BOOL  int32_t

#define ASSERT(xxx) 

#define VOID void

#define x_thread_delay(x)  udelay(x*1000);

#define Printf  printk

//#define HalFlushInvalidateDCache(xxx)  flush_cache_all();

//#define VERIFY(xxx) 

#define UNUSED(x)               (void)x

#define x_sema_lock(x,y)  0

/**
* Host definition
*/

#define FCI_DMA_BUFFER_SIZE		          65536
#define FCI_DMA_BUFFER_SIZE_ORDER		4   // 2^4 * 4K = 64K bytes
#define FCI_BLOCK_SIZE  512

#define CMD_DEBUG_LENGTH  10

struct sdhci_host
{
	struct mmc_cmd cmd_rec[CMD_DEBUG_LENGTH];
	int        cmd_with_data_rec[CMD_DEBUG_LENGTH];
	struct mmc_data data_rec[CMD_DEBUG_LENGTH];
	UINT32  error_rec[CMD_DEBUG_LENGTH];
	int        sd_rec_index;
	
	struct mmc_cmd *pre_cmd;
	
	// Current command & argument 
	UINT32  u4Cmd;
	UINT32  u4CmdError;
	UINT32  u4Arg;
	int        sd_clk_minus;		/* slower the sd clock */
	
	int			flags;		/* Driver states */

	int         fgPowerSwitchOn;    /* Power switch on status */
	int         fgPowerSwitchTest;  /* Power switch on/off test */	
	int         forceRemove;          /* force remove sd card or not */
	int         forceRemoveCnt;     /* force remove counter */
	int         fakeIns;                  /* Fake force insert */
	
   unsigned int rreqcnt;              /* variable to store read count */
   unsigned int wreqcnt;              /* variable to store write count */
   
	u8			clk;		/* Current clock speed */
	unsigned char		bus_width;	/* Current bus width */
	int		hispeedcard;          /* HiSpeed card or not */
   
	int			chip_id;	/* ID of controller */

	int			base;		/* I/O port base */
};
