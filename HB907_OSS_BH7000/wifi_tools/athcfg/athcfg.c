/*
 * Copyright (c) 2002-2005 Atheros Communications, Inc.
 * All rights reserved.
 */



#include <stdlib.h>
#include <assert.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/param.h>
#include <string.h>
#include <sys/errno.h>
#include <stdio.h>
#include <net/if.h>
#include <errno.h>
#include <math.h>
#include <sys/un.h>
#include <arpa/inet.h>

#ifdef OS_TYPE_LINUX
    #include <sys/ioctl.h>
#endif

#ifdef OS_TYPE_FREEBSD
    #include <sys/ioccom.h>
#endif

#ifdef OS_TYPE_LINUX
#include "athcfg.h"
#include "athcfg_wcmd.h"
#else
#include <athcfg.h>
#include <athcfg_wcmd.h>
#endif

#ifdef OMNI_MX_LED
int athcfg_ledstats_get(char *ifrn_name, int nargs, char *args[]);
int athcfg_ledstats_set(char *ifrn_name, int nargs, char *args[]);
#endif


/************************* SETTING ROUTINES **************************/
/*------------------------------------------------------------------*/

/*
  * Macro to handle errors when setting WE
  * Print a nice error message and exit...
  * We define them as macro so that "return" do the right thing.
  * The "do {...} while(0)" is a standard trick
  */
#define ERR_SET_EXT(ifrn_name, request) \
    fprintf(stderr, "            ERROR for wireless request -- \"%s\"\n\
        For interface -- [%s]\n \
        In function -- %s:\n", \
            request, ifrn_name, __FUNCTION__);

#define ABORT_ARG_NUM(rname, request) \
    do { \
        ERR_SET_EXT(rname, request); \
        fprintf(stderr, "    too few arguments.\n"); \
        return(-1); \
    } while(0)

#define ABORT_ARG_TYPE(rname, request, arg) \
    do { \
        ERR_SET_EXT(rname, request); \
        fprintf(stderr, "                Invalid argument \"%s\".\n", arg); \
    } while(0)

#define ABORT_ARG_SIZE(ifrn_name, request, max) \
    do { \
        ERR_SET_EXT(ifrn_name, request); \
        fprintf(stderr, "                Argument is too big (max size is -- %d)\n", max); \
        return(-3); \
    } while(0)

#define ERR_MALLOC_FAIL(x) \
    do { \
        fprintf(stderr, "Malloc failed in -- %s: for -- %s:", __FUNCTION__,x ); \
        return 0; \
    } while(0)

#define ENUM_PRINT(string) #string

/* For doing log10/exp10 without libm */

#define LOG10_MAGIC 1.25892541179

#define IS_WORD_ALIGNED(x) (x % 4 == 0)

/* subroutine for converting from dbm2mwatt and mwatt2dbm */

/**
 * Convert a value in dBm to a vlaue in milliWatt
 */
int
ath_dbm2mwatt(int in)
{
#ifdef WE_NOLIBM
    /* Version without libm : slower */
    int ip = in / 10;
    int fp = in % 10;
    int k;
    double res = 1.0;
    /* Split integral and floating part to avoid accumulating rounding errors*/
    for(k = 0; k < ip; k++)
    res *= 10;
    for(k = 0; k < fp; k++)
    res *= LOG10_MAGIC;
    return ((int) res);    

#else /* WE_NOLIBM */
    /* Version with libm : faster */
    return((int) (floor (pow(10.0, (( (double) in) / 10.0 )))));
#endif /* WE_NOLIBM */
}    
 
/**
 * Convert a vlaue in milliWatt to a value in dBm.
 */
int
ath_mwatt2dbm(int in)
{
#ifdef WE_NOLIBM
    int res = 0;
    /* Slpit integral and floating part to avoid accumulating rounding errors */
    while(fin > 10.0)
    {
    res += 1;
    fin /=10.0;
    }
    while(fin > 1.000001) /* Eliminating rounding errors, take cell */
    {
    res += 1;
    fin /= LOG10_MAGIC;
    }
    return (res);
#else   /* WE_NOLIBM */
    /* Version with libm : faster */
    return((int) (floor(10.0 * log10(( double ) in) )) );
#endif  /* WE_NOLIBM */
}    

/* Some usefull constants */
#define KILO        1e3
#define MEGA        1e6
#define GIGA        1e9

/**
 * Display the command usage as user inputs a wrong command
 */
#define COMMAND_USAGE_INFO\
    do {\
        printf("!! ERROR !! Unacceptable command\n");\
        printf("Please enter a valid command as below -- \n");\
        printf("athcfg [interface_name] [command_name] [operation_name] [parameters]\n");\
        printf("For Example -- athcfg ath0 ssid set Atheros\n\n");\
    } while(0)
                    

/**
 * @brief Handlers for SSID-related commands.
 */ 
static  athcfg_cmd_t athcfg_ssid_cmds[] = 
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_ssid_get ,
        .cmd_usage = "ssid get" ,
        .min_args =  0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_ssid_set ,
        .cmd_usage = "ssid set <ssid>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for RTS-Threshold-related commands.
 */
static athcfg_cmd_t athcfg_rts_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_rts_get ,
        .cmd_usage = "rts get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_rts_set ,
        .cmd_usage = "rts set <rts>" ,
        .min_args = 1 ,
        .max_args = 1 }},
    }, 
};

/**
 * @brief Handlers for FRAGMENT-Threshold-related commands.
 */
static athcfg_cmd_t athcfg_fragthres_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_fragthres_get ,
        .cmd_usage = "fragthres get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_fragthres_set ,
        .cmd_usage = "fragthres set <fragthres>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for BSSID-related commands 
 */
static athcfg_cmd_t athcfg_bssid_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_bssid_get ,
        .cmd_usage = "bssid get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_bssid_set ,
        .cmd_usage = "bssid set <bssid>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for multicast-related commands 
 */
static athcfg_cmd_t athcfg_mcast_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_mcast_get ,
        .cmd_usage = "mcast del" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_mcast_set ,
        .cmd_usage = "mcast set <bssid>" ,
        .min_args = 2 ,
        .max_args = 2 }}, 
    },
};



/**
 * @brief Handlers for NICKNAME-related commands.
 */
static athcfg_cmd_t athcfg_nick_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_nick_get ,
        .cmd_usage = "nickname get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_nick_set ,
        .cmd_usage = "nickname set <nickname>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for FREQURNCY-related commands.
 */
static athcfg_cmd_t athcfg_freq_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_freq_get ,
        .cmd_usage = "freq get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_freq_set ,
        .cmd_usage = "freq set <freq>" ,
        .min_args = 2 ,
        .max_args = 2 }}, 
    },
};

/**
 * @brief Handlers for TXPOWER-related commands.
 */
static athcfg_cmd_t athcfg_txpower_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_txpower_get ,
        .cmd_usage = "txpower get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_txpower_set ,
        .cmd_usage = "txpower set <txpower>" ,
        .min_args = 1 ,
        .max_args = 3 }}, 
    },
};

/**
 * @brief Handlers for PARAMS-related commands.
 */
static athcfg_cmd_t athcfg_param_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_param_get,
        .cmd_usage = "param get" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_param_set ,
        .cmd_usage = "param set <param>" ,
        .min_args = 2 ,  
        .max_args = 2 }}, 
    },
};

/**
 * @brief Handlers for OPT_IE-related commands.
 */
static athcfg_cmd_t athcfg_optie_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_optie_get ,
        .cmd_usage = "optie get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_optie_set ,
        .cmd_usage = "optie set <optie>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for APP_IE-related commands.
 */
static athcfg_cmd_t athcfg_appie_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_appie_get ,
        .cmd_usage = "appie get" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_appie_set ,
        .cmd_usage = "appie set <appie>" ,
        .min_args = 2 ,
        .max_args = 2 }}, 
    },
};

/**
 * @brief Handlers for KEY_INFO-related commands.
 */
static athcfg_cmd_t athcfg_keyinfo_cmds[] =
{   
    {
        .cmd_name = "get", 
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_key_get ,
        .cmd_usage = "key get" ,
        .min_args = 2 ,
        .max_args = 2 }},
    },
    {
        .cmd_name = "set", 
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_key_set ,
        .cmd_usage = "key set <key>" ,
        .min_args = 1 ,  
        .max_args = 5 }},  
    },
};

/**
 * @brief Handlers for SCAN-related commands.
 */
static athcfg_cmd_t athcfg_scan_cmds[] =
{
    {
        .cmd_name = "get", 
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_scan_get, 
        .cmd_usage = "scan get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE, 
        .u = { .hdlr = { .fn = athcfg_scan_set ,
        .cmd_usage = "scan set <scan>" ,
        .min_args = 0 , 
        .max_args = 0 }}, 
    },
};

/**
 * @brief Handlers for SPECIF SCAN CHANNEL-related commands.
 */
static athcfg_cmd_t athcfg_spec_chan_scan_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "specscan get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE, 
        .u = { .hdlr = { .fn = athcfg_spec_chan_scan_set ,
        .cmd_usage = "specscan set" ,
        .min_args = 1 , 
        .max_args = 2 }}, 
    },
};


/**
 * @brief Handlers for TESTMODE-related commands.
 */
static  athcfg_cmd_t athcfg_testmode_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_testmode_get ,
        .cmd_usage = "testmode get <cmd>" ,
        .min_args = 0 ,
        .max_args = 1 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_testmode_set ,
        .cmd_usage = "testmode set <cmd> <param>" ,
        .min_args = 1 ,
        .max_args = 3 }}, 
    },
};
/**
 * @brief Handlers for BAND_MODE-related commands.
 */
static  athcfg_cmd_t athcfg_mode_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_mode_get ,
        .cmd_usage = "mode get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_mode_set ,
        .cmd_usage = "mode set <mode>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for CHANNEL_LIST-related commands.
 */
static athcfg_cmd_t athcfg_chanlist_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_chanlist_get ,
        .cmd_usage = "chanlist get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_chanlist_set ,
        .cmd_usage = "chanlist set <chanlist>" ,
        .min_args = 32 ,
        .max_args = 32 }}, 
    },
};

/**
 * @brief Handlers for WMM_PARAMS-related commands.
 */
static athcfg_cmd_t athcfg_wmmparam_cmds[] =
{
    {
        .cmd_name = "get", 
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_wmmparam_get ,
        .cmd_usage = "wmmparam get" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_wmmparam_set ,
        .cmd_usage = "wmmparam set <wmmparam>" ,
        .min_args = 4 , 
        .max_args = 4 }},
    },
};

/**
 * @brief Handlers for NAME-related commands
 */
static athcfg_cmd_t athcfg_name_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_name_get ,
        .cmd_usage = "name get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "name set <name>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for IC_CAPS-related commands.
 */
static  athcfg_cmd_t athcfg_ic_caps_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_iccaps_get ,
        .cmd_usage = "iccaps get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "iccaps set <iccaps>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for RETRIES-related commands.
 */
static athcfg_cmd_t athcfg_retries_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_retries_get ,
        .cmd_usage = "retries get" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "retries set <retries>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for WAPLIST-related commands.
 */
static athcfg_cmd_t athcfg_waplist_cmds[] =
{   
    {
        .cmd_name = "get", 
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_waplist_get ,
        .cmd_usage = "waplist get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set", 
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "waplist set <waplist>" ,
        .min_args = 1 , 
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for ADDBA_STATUS-related commands.
 */
static athcfg_cmd_t athcfg_addbastat_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_addbastat_get ,
        .cmd_usage = "addbastat get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "addbastat set <addbastat>" ,
        .min_args = 1 ,             
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for CHANNEL_INFO-related commands.
 */
static athcfg_cmd_t athcfg_chaninfo_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_chaninfo_get ,
        .cmd_usage = "chaninfo get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "chaninfo set <chaninfo>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for WPA_IE-related commands.
 */
static athcfg_cmd_t athcfg_wpaie_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_wpaie_get ,
        .cmd_usage = "wpaie get" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "wpaie set <wpaie>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for WSC_IE-related commands.
 */
static athcfg_cmd_t athcfg_wscie_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_wscie_get ,
        .cmd_usage = "wscie get" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "wscie set <wscie>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for TX_POWER_LIMIT-related commands.
 */
static athcfg_cmd_t athcfg_txpowlimit_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL,
        .cmd_usage = "txpowlimit get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_txpowlimit_set ,
        .cmd_usage = "txpowlimit set <txpowlimit>" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for ATH_CAPS-related commands.
 */
static athcfg_cmd_t athcfg_athcap_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "athcaps get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_athcap_set ,
        .cmd_usage = "athcaps set <athcaps>" ,
        .min_args = 2 ,             
        .max_args = 2 }}, 
    },
};

/**
 * @brief Handlers for TURBO-related commands.
 */
static athcfg_cmd_t athcfg_turbo_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "turbo get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_turbo_set ,
        .cmd_usage = "turbo set <turbo>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for FILTER-related commands.
 */
static athcfg_cmd_t athcfg_filter_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "filter get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    }, 
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_filter_set ,
        .cmd_usage = "filter set <filter>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
}; 

/**
 * @brief Handlers for ADDBA_RESPONSE-related commands.
 */
static athcfg_cmd_t athcfg_ADDBAresp_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "addbaresp get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = {.fn = athcfg_ADDBAresp_set ,
        .cmd_usage = "addbaresp set <addbaresp>" ,
        .min_args = 3 ,         
        .max_args = 3 }},
    },
};

/**
 * @brief Handlers for MLME-related commands.
 */
static athcfg_cmd_t athcfg_mlme_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = {. hdlr = { .fn = NULL,
        .cmd_usage = "mlme get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_mlme_set ,
        .cmd_usage = "mlme set <mlme>" ,
        .min_args = 2 ,        
        .max_args = 3 }}, 
    },
};

/**
 * @brief Handlers for SEND ADDBA-related commands.
 */
static athcfg_cmd_t athcfg_sendADDBA_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "sendaddba get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_sendADDBA_set ,
        .cmd_usage = "sendaddba set <sendaddba>" ,
        .min_args = 3 ,                  
        .max_args = 3 }}, 
    },
};

/**
 * @brief Handlers for SEND DELBA-related commands.
 */
static athcfg_cmd_t athcfg_sendDELBA_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "senddelba get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_sendDELBA_set ,
        .cmd_usage = "senddelba set <senddelba>" ,
        .min_args = 4 ,                          
        .max_args = 4 }},
    },
};

/**
 * @brief Handlers for KEY_INFO-related commands.
 */
static athcfg_cmd_t athcfg_delkey_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "delkey get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_delkey_set ,
        .cmd_usage = "delkey set <delkey>" ,
        .min_args = 1 ,  
        .max_args = 2 }}, 
    },
};

/**
 * @brief Handlers for DEL_MAC-related commands.
 */
static athcfg_cmd_t athcfg_delmac_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "delmac get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_delmac_set ,
        .cmd_usage = "delmac set <delmac>" ,
        .min_args = 1 , 
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for ADD_MAC-related commands.
 */
static athcfg_cmd_t athcfg_addmac_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL,
        .cmd_usage = "addmac get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_addmac_set ,
        .cmd_usage = "addmac set <addmac>" ,
        .min_args = 1 , 
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for OPERATION_MODE-related commands.
 */
static athcfg_cmd_t athcfg_opmode_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_opmode_get ,
        .cmd_usage = "opmode get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_opmode_set ,
        .cmd_usage = "opmode set <opmode>" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for Next Scan Delay-related commands.
 * This parameter delays for 'val' secs before triggering
 * next scan
 **/

static athcfg_cmd_t athcfg_scandelay_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_scandelay_get,
        .cmd_usage = "next-scandelay get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_scandelay_set,
        .cmd_usage = "next-scandelay" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
};


/**
 * @brief Handlers for Auto Association-related commands.
 * This parameter Enables/Disables auto association after 
 * ssid triggered scan completion
 **/

static athcfg_cmd_t athcfg_autoassociation_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_autoassoc_get,
        .cmd_usage = "auto-assoc get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_autoassoc_set,
        .cmd_usage = "auto-assoc" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
};


/**
 * @brief Handlers for VAP DELETE-related commands.
 **/
static athcfg_cmd_t athcfg_vapdelete_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL,
        .cmd_usage = "vapdelete get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_vapdelete_set ,
        .cmd_usage = "athX vapdelete" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
};

/**
 * @brief Handlers for ATH_STATUS-related commands.
 */
static athcfg_cmd_t athcfg_athstats_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_athstats_get ,
        .cmd_usage = "athstats get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL,
        .cmd_usage = "athstats set <athstats>" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
};





/**
 * @brief Handlers for ATH_DIALOG_INFO-related commands.
 */
static athcfg_cmd_t athcfg_athdiag_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_athdiag_get ,
        .cmd_usage = "athdiag get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL,
        .cmd_usage = "athdiag set <athdiag>" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for PHYERR_INFO-related commands.
 */
static athcfg_cmd_t athcfg_phyerr_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_phyerr_get ,
        .cmd_usage = "phyerr get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL,
        .cmd_usage = "phyerr set <phyerr>" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for CWM_INFO-related commands.
 */
static athcfg_cmd_t athcfg_athcwm_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_athcwm_get ,
        .cmd_usage = "athcwm get" ,
        .min_args = 1 ,
        .max_args = 4 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL,
        .cmd_usage = "athcwm set <athcwm>" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for ETHTOOL-related commands.
 */
static athcfg_cmd_t athcfg_ethtool_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_ethtool_get ,
        .cmd_usage = "ethtool get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL,
        .cmd_usage = "ethool set <ethool>" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for VAP_CREATE-related commands.
 */
static athcfg_cmd_t athcfg_vapcreate_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "vapcreate get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_vapcreate_set ,
        .cmd_usage = "vapcreate set <vapcreate>" ,
        .min_args = 3 , 
        .max_args = 3 }},
    },
};

/**
 * @brief Handlers for STATUS_INFO-related commands.
 */
static athcfg_cmd_t athcfg_stats_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_stats_get ,
        .cmd_usage = "stats get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL,
        .cmd_usage = "stats set <stats>" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for ATH_MAC_INFO-related commands.
 */
static athcfg_cmd_t athcfg_athmac_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "athmac get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_athmac_set ,
        .cmd_usage = "athmac set <athmac>" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for MTU_INFO-related commands.
 */
static athcfg_cmd_t athcfg_mtu_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL,
        .cmd_usage = "mtu get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_mtu_set ,
        .cmd_usage = "mtu set <mtu>" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for TX_TIMEOUT-related commands.
 */
static athcfg_cmd_t athcfg_txtimeout_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "txtimeout get",
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_txtimeout_set ,
        .cmd_usage = "txtimeout set <txtimeout>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for MODE_INIT-related commands.
 */
static athcfg_cmd_t athcfg_modeinit_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "modeinit get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_modeinit_set ,
        .cmd_usage = "modeinit set <modeinit>" ,
        .min_args = 0 ,
        .max_args = 0 }}, 
    },
};

/**
 * @brief Handlers for STATS_CLEAR-related commands.
 */
static athcfg_cmd_t athcfg_statsclear_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_statsclear_get ,
        .cmd_usage = "statsclear get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL,
        .cmd_usage = "statsclear set <statsclear>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for GET_RANGE-related commands.
 */ 
static athcfg_cmd_t athcfg_getrange_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_getrange_get ,
        .cmd_usage = "range get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "range set <getrange>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
};

/**
 * @brief Handlers for STATS_INFO -related commands.
 */
static athcfg_cmd_t athcfg_statsinfo_cmds[] =
{
     {
         .cmd_name = "get",
         .handler_present = TRUE,
         .u = { .hdlr = { .fn = athcfg_statsinfo_get ,
         .cmd_usage = "statsinfo get" ,
         .min_args = 0 ,
         .max_args = 0 }},
     },
     {
         .cmd_name = "set",
         .handler_present = FALSE,
         .u = { .hdlr = { .fn = NULL ,
         .cmd_usage = "statsinfo set <statsinfo>" ,
         .min_args = 1 ,
         .max_args = 1 }}, 
     },
};

/**
 * @brief Handlers for STATION_INFO -related commands.
 */
static athcfg_cmd_t athcfg_stainfo_cmds[] =
{
     {
         .cmd_name = "get",
         .handler_present = TRUE,
         .u = { .hdlr = { .fn = athcfg_stainfo_get ,
         .cmd_usage = "stainfo get" ,
         .min_args = 0 ,
         .max_args = 0 }},
     },
     {
         .cmd_name = "set",
         .handler_present = FALSE,
         .u = { .hdlr = { .fn = NULL,
         .cmd_usage = "stainfo set <stainfo>" ,
         .min_args = 1 ,
         .max_args = 1 }}, 
     },
};

/**
 * @brief Handlers for STASTATS_INFO -related commands.
 */
static athcfg_cmd_t athcfg_stastats_cmds[] =
{
     {
         .cmd_name = "get",
         .handler_present = TRUE,
         .u = { .hdlr = { .fn = athcfg_stastats_get ,
         .cmd_usage = "stastats get" ,
         .min_args = 1 ,
         .max_args = 1 }},
     },
     {
         .cmd_name = "set",
         .handler_present = FALSE,
         .u = { .hdlr = { .fn = NULL,
         .cmd_usage = "stastats set <stastats>" ,
         .min_args = 1 ,
         .max_args = 1 }},
     },
};

/**
 * SYSCTL Set/Get IOCTL's 
 */

/**
 * @brief Handlers for EIFS_MASK-related commands.
 */
static athcfg_cmd_t athcfg_eifsmask_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_eifsmask_get ,
        .cmd_usage = " eifs-mask get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_eifsmask_set ,
        .cmd_usage = " eifs-mask set <eifs-mask>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for EIFS_DUR-related commands.
 */
static athcfg_cmd_t athcfg_eifsdur_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_eifsdur_get ,
        .cmd_usage = " eifs-dur get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_eifsdur_set ,
        .cmd_usage = " eifs-dur set <eifs-dur>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for SLOT_TIME-related commands.
 */
static athcfg_cmd_t athcfg_slottime_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_slottime_get ,
        .cmd_usage = " slot-time get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_slottime_set ,
        .cmd_usage = " slot-time set <slot-time>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for ACK_TIMEOUT-related commands.
 */
static athcfg_cmd_t athcfg_acktimeout_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_acktimeout_get ,
        .cmd_usage = "ack-timeout get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_acktimeout_set ,
        .cmd_usage = "ack-timeout set <ack-timeout>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for CTS_TIMEOUT-related commands.
 */
static athcfg_cmd_t athcfg_ctstimeout_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_ctstimeout_get ,
        .cmd_usage = "cts-timeout get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_ctstimeout_set ,
        .cmd_usage = "cts-timeout set <cts-timeout>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for SOFT_LED-related commands.
 */
static athcfg_cmd_t athcfg_softled_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_softled_get ,
        .cmd_usage = "soft-led get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_softled_set ,
        .cmd_usage = "soft-led set <soft-led>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for LED_PIN-related commands.
 */
static athcfg_cmd_t athcfg_ledpin_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_ledpin_get ,
        .cmd_usage = "led-pin get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_ledpin_set ,
        .cmd_usage = "led-pin set <led-pin>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for DEBUG-related commands.
 */
static athcfg_cmd_t athcfg_debug_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_debug_get ,
        .cmd_usage = "debug get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_debug_set ,
        .cmd_usage = "debug set <debug>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for TX_ANTENNA-related commands.
 */
static athcfg_cmd_t athcfg_tx_antenna_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_tx_antenna_get ,
        .cmd_usage = "tx-antenna get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_tx_antenna_set ,
        .cmd_usage = "tx-antenna set <tx-antenna>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for RX_ANTENNA-related commands.
 */
static athcfg_cmd_t athcfg_rx_antenna_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_rx_antenna_get ,
        .cmd_usage = "rx-antenna get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_rx_antenna_set ,
        .cmd_usage = "rx-antenna set <rx-antenna>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for DIVERSITY-related commands.
 */
static athcfg_cmd_t athcfg_deversity_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_diversity_get ,
        .cmd_usage = "diversity get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_diversity_set ,
        .cmd_usage = "diversity set <diversity>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for TX_INTR_PERIOD-related commands.
 */
static athcfg_cmd_t athcfg_tx_intrperiod_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_tx_intrperiod_get ,
        .cmd_usage = "tx-intr-period get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_tx_intrperiod_set ,
        .cmd_usage = "tx-intr-period set <tx-intr-period>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for product-info related commands.
 */
static athcfg_cmd_t athcfg_product_info_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_product_info_get ,
        .cmd_usage = "product-info get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
};

/**
 * @brief Handlers for FF_TXQ_MIN-related commands.
 */
static athcfg_cmd_t athcfg_txq_min_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_txq_min_get ,
        .cmd_usage = "fftxq-min get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_txq_min_set ,
        .cmd_usage = "fftxq-min set <fftxq-min>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for TKIP_MIC-related commands.
 */
static athcfg_cmd_t athcfg_tkipmic_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_tkipmic_get ,
        .cmd_usage = "tkip-mic get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_tkipmic_set ,
        .cmd_usage = "tkip-mic set <tkipmic>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for GLOBAL_TX_TIMEOUT-related commands.
 */
static athcfg_cmd_t athcfg_glob_txtimeout_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_glob_txtimeout_get ,
        .cmd_usage = "glob-tx-timeout get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_glob_txtimeout_set ,
        .cmd_usage = "glob-tx-timeout set <glob-tx-timeout>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for SW_WSC_BUTTON-related commands.
 */
static athcfg_cmd_t athcfg_sw_wscbutton_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_sw_wscbutton_get ,
        .cmd_usage = "sw-wsc-button get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_sw_wscbutton_set ,
        .cmd_usage = "sw-wsc-button set <sw-wsc-button>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for COUNTRY_CODE-related commands.
 */
static athcfg_cmd_t athcfg_country_code_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_country_code_get ,
        .cmd_usage = "country-code get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "country-code set <country-code>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for REG_DOMAIN-related commands.
 */
static athcfg_cmd_t athcfg_reg_domain_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_reg_domain_get ,
        .cmd_usage = "reg-domain get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_reg_domain_set ,
        .cmd_usage = "reg-domain set <reg-domain>" ,
        .min_args = 1 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for REG_DOMAIN-related commands.
 */
static athcfg_cmd_t athcfg_dbg_info_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_dbg_info_get ,
        .cmd_usage = "dbg-info get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_dbg_info_set ,
        .cmd_usage = "dbg-info set <dbg-info>" ,
        .min_args = 0 , 
        .max_args = 2 }},
    },
};

/**
 * @brief Handlers for host stats commands.
 */
static athcfg_cmd_t athcfg_hst_stats_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_hst_stats_get ,
        .cmd_usage = "hst-stats get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "hst-stats set" ,
        .min_args = 0 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for host 11n stats commands.
 */
static athcfg_cmd_t athcfg_hst_11n_stats_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_hst_11n_stats_get ,
        .cmd_usage = "hst-11n-stats get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "hst-11n-stats set" ,
        .min_args = 0 , 
        .max_args = 1 }},
    },
};

/**
 * @brief Handlers for getting/setting register values 
 */
static athcfg_cmd_t athcfg_reg_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_reg_get ,
        .cmd_usage = "reg get <reg_start_addr> [<reg_end_addr>]" ,
        .min_args = 1 ,
        .max_args = 2 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_reg_set ,
        .cmd_usage = "reg set <reg_addr> <reg_val>" ,
        .min_args = 2 , 
        .max_args = 2 }},
    },
};

#if 0
/**
 * @brief Handlers for target stats commands.
 */
static athcfg_cmd_t athcfg_tgt_stats_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_tgt_stats_get ,
        .cmd_usage = "tgt-stats get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "tgt-stats set" ,
        .min_args = 0 , 
        .max_args = 1 }},
    },
};
#endif
/**
 * @brief Handlers for target 11n stats commands.
 */
static athcfg_cmd_t athcfg_tgt_11n_stats_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_tgt_11n_stats_get ,
        .cmd_usage = "tgt-11n-stats get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
        .cmd_usage = "tgt-11n-stats set" ,
        .min_args = 0 , 
        .max_args = 1 }},
    },
};

#ifdef OMNI_MX_LED



/**
 * @brief Handlers for target 11n stats commands.
 */
static athcfg_cmd_t athcfg_ledstats_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_ledstats_get ,
        .cmd_usage = "led-stats get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_ledstats_set ,
        .cmd_usage = "led-stats set" ,
        .min_args = 1 ,	
        .max_args = 1 }},
    },
};
#endif


/**
 * @brief Handlers for pktlog commands.
 */
static athcfg_cmd_t athcfg_pktlog_cmds[] =
{
    {
        .cmd_name = "enable",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_pktlog_enable ,
        .cmd_usage = "pktlog enable [rx,tx,rcu,rcf,tcp]" ,
        .min_args = 0 ,
        .max_args = 1 }},
    },
    {
        .cmd_name = "disable",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_pktlog_disable ,
        .cmd_usage = "pktlog disable" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_pktlog_set_cmd ,
        .cmd_usage = "pktlog set" ,
        .min_args = 2 ,
        .max_args = 2 }},
    },
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_pktlog_get_data ,
        .cmd_usage = "pktlog get" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
    
};

/**
 * @brief Handlers for pktlog commands.
 */
static athcfg_cmd_t athcfg_tx99_cmds[] =
{
	{
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_tx99_set_cmd ,
        .cmd_usage = "tx99 set <option>" ,
        .min_args = 1 ,
        .max_args = 4 }},
    },
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_tx99_get_cmd ,
        .cmd_usage = "tx99 get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    
};


/**
 * @brief Handlers for nominal noise floor set and get
 */
static athcfg_cmd_t athcfg_nominal_nf_cmd[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_nominal_nf_get ,
            .cmd_usage = "nom-nf get" ,
            .min_args = 0 ,
            .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_nominal_nf_set ,
            .cmd_usage = "nom-nf set" ,
            .min_args = 1 , 
            .max_args = 1 }},
    },
};

/**
 * @brief Handlers for minimum noise floor set and get
 */
static athcfg_cmd_t athcfg_minimum_nf_cmd[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_minimum_nf_get ,
            .cmd_usage = "min-nf get" ,
            .min_args = 0 ,
            .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_minimum_nf_set ,
            .cmd_usage = "min-nf set" ,
            .min_args = 1 , 
            .max_args = 1 }},
    },
};

/**
 * @brief Handlers for maximum noise floor set and get
 */
static athcfg_cmd_t athcfg_maximum_nf_cmd[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_maximum_nf_get ,
            .cmd_usage = "max-nf get" ,
            .min_args = 0 ,
            .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_maximum_nf_set ,
            .cmd_usage = "max-nf set" ,
            .min_args = 1 , 
            .max_args = 1 }},
    },
};

/**
 * @brief Handlers for noise floor delta set and get
 */
static athcfg_cmd_t athcfg_nf_delta_cmd[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_nf_delta_get ,
            .cmd_usage = "nf-delta get" ,
            .min_args = 0 ,
            .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_nf_delta_set ,
            .cmd_usage = "nf-delta set" ,
            .min_args = 1 , 
            .max_args = 1 }},
    },
};

/**
 * @brief Handlers for noise floor weight set and get
 */
static athcfg_cmd_t athcfg_nf_weight_cmd[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_nf_weight_get ,
            .cmd_usage = "nf-weight get" ,
            .min_args = 0 ,
            .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_nf_weight_set ,
            .cmd_usage = "nf-weight set" ,
            .min_args = 1 , 
            .max_args = 1 }},
    },
};

/**
 * @brief Handlers for noise floor based switch set and get
 */
static athcfg_cmd_t athcfg_nf_switch_cmd[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_nf_switch_get ,
            .cmd_usage = "nf-switch get" ,
            .min_args = 0 ,
            .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_nf_switch_set ,
            .cmd_usage = "nf-switch set" ,
            .min_args = 1 , 
            .max_args = 1 }},
    },
};

/**
 * @brief Handler for enable antenna diversity
 */
static athcfg_cmd_t athcfg_ant_div_enable_cmd[] =
{
    {
        .cmd_name = "enable",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_ant_div_enable,
            .cmd_usage = "ant-div enable" ,
            .min_args = 0 ,
            .max_args = 0 }},
    },
    {
        .cmd_name = "disable",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_ant_div_disable ,
            .cmd_usage = "ant-div disable" ,
            .min_args = 0 , 
            .max_args = 0 }},
    },
};


/**
 * @brief Handler for getting antenna diversity parameters
 */
static athcfg_cmd_t athcfg_ant_div_param_get_cmd[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_ant_div_get_param,
            .cmd_usage = "ant-div-params get" ,
            .min_args = 0 ,
            .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL ,
            .cmd_usage = "ant-div-params set" ,
            .min_args = 1 , 
            .max_args = 1 }},
    },
};

/**
 * @brief Handler for seting default tx antenna
 */
static athcfg_cmd_t athcfg_ant_div_default_cmd[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL,
            .cmd_usage = "ant-div-default get" ,
            .min_args = 0 ,
            .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_ant_div_set_def_ant ,
            .cmd_usage = "ant-div-default set" ,
            .min_args = 1 , 
            .max_args = 1 }},
    },
};

/**
 * @brief Handlers for pktlog commands.
 */
static athcfg_cmd_t athcfg_ether_dongle_mac_cmds[] =
{
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_ether_dongle_mac_set_cmd ,
        .cmd_usage = "ether-dongle-mac set" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_ether_dongle_mac_get_cmd ,
        .cmd_usage = "ether-dongle-mac get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    
};

/**
 * @brief Handler for seting default tx antenna
 */
static athcfg_cmd_t athcfg_sta_start_assoc_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = FALSE,
        .u = { .hdlr = { .fn = NULL,
            .cmd_usage = "sta-assoc get" ,
            .min_args = 0 ,
            .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_sta_assoc_set_cmd,
            .cmd_usage = "sta-assoc set" ,
            .min_args = 0 , 
            .max_args = 0 }},
    },
};

/**
 * @brief Handlers for RADIO on/off commands.
 */
static athcfg_cmd_t athcfg_radio_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_radio_get ,
        .cmd_usage = "radio get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_radio_set ,
        .cmd_usage = "radio set <0|1>" ,
        .min_args = 1 ,
        .max_args = 1 }},
    }, 
};

/**
 * @brief Handlers for HIDDEN-SSID-related commands.
 */ 
static  athcfg_cmd_t athcfg_hidden_ssid_cmds[] = 
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_hidden_ssid_get ,
        .cmd_usage = "hidden-ssid get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_hidden_ssid_set ,
        .cmd_usage = "hidden-ssid set <0|1>" ,
        .min_args = 1 ,
        .max_args = 1 }}, 
    },
};

static  athcfg_cmd_t athcfg_keepscanlist_cmds[] =
{
    {
        .cmd_name = "set",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_keepscanlist_set ,
        .cmd_usage = "keepscanlist set <0|1>" ,
        .min_args = 1 ,
        .max_args = 1 }},
    },
};

/**
 *  * @brief Handlers for htrates-related commands.
 *   */
static  athcfg_cmd_t athcfg_htrates_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_htrates_get ,
        .cmd_usage = "htrates get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
};

/**
 *  *  * @brief Handlers for wmi-timeout command.
 *   *   */
static  athcfg_cmd_t athcfg_wmi_timeout_cmds[] =
{
    {
        .cmd_name = "get",
        .handler_present = TRUE,
        .u = { .hdlr = { .fn = athcfg_wmi_timeout_get ,
        .cmd_usage = "wmi-timeout get" ,
        .min_args = 0 ,
        .max_args = 0 }},
    },
};

/**
 * @brief Handlers for first-level commands.
 */ 
static athcfg_cmd_t athcfg_cmds[] = 
{
    {
        .cmd_name = "tx99",
        .handler_present = FALSE,
        .u.next_level = athcfg_tx99_cmds,
    },
    {
        .cmd_name = "ssid",
        .handler_present = FALSE,
        .u.next_level = athcfg_ssid_cmds,
    },
    {
        .cmd_name = "rts",
        .handler_present = FALSE,
        .u.next_level = athcfg_rts_cmds,
    },
    {
        .cmd_name = "fragthres",
        .handler_present = FALSE,
        .u.next_level = athcfg_fragthres_cmds,
    },
    {
        .cmd_name = "bssid",
        .handler_present = FALSE,
        .u.next_level = athcfg_bssid_cmds,
    },
    {
        .cmd_name = "mcast",
        .handler_present = FALSE,
        .u.next_level = athcfg_mcast_cmds,
    },
    {
        .cmd_name = "nickname",
        .handler_present = FALSE,
        .u.next_level = athcfg_nick_cmds,
    },
    {
        .cmd_name = "freq",
        .handler_present = FALSE,
        .u.next_level = athcfg_freq_cmds,
    },
    {
        .cmd_name = "txpower",
        .handler_present = FALSE,
        .u.next_level = athcfg_txpower_cmds,
    },
    {
        .cmd_name = "param",
        .handler_present = FALSE,
        .u.next_level = athcfg_param_cmds,
    },
    {
        .cmd_name = "optie",
        .handler_present = FALSE,
        .u.next_level = athcfg_optie_cmds,
    },
    {
        .cmd_name = "appie",
        .handler_present = FALSE,
        .u.next_level = athcfg_appie_cmds,
    },
    {
        .cmd_name = "key",
        .handler_present = FALSE,
        .u.next_level = athcfg_keyinfo_cmds,
    },
    {
        .cmd_name = "scan",
        .handler_present = FALSE,
        .u.next_level = athcfg_scan_cmds,
    },
    {
        .cmd_name = "mode",
        .handler_present = FALSE,
        .u.next_level = athcfg_mode_cmds,
    },
    {
        .cmd_name = "chanlist",
        .handler_present = FALSE, 
        .u.next_level = athcfg_chanlist_cmds,
    },
    {
        .cmd_name = "wmmparam",
        .handler_present = FALSE,
        .u.next_level = athcfg_wmmparam_cmds,
    },
    {
        .cmd_name = "name",
        .handler_present = FALSE,
        .u.next_level = athcfg_name_cmds,
    },
    {
        .cmd_name = "iccaps",
        .handler_present = FALSE,
        .u.next_level = athcfg_ic_caps_cmds,
    },
    {
        .cmd_name = "retries",
        .handler_present = FALSE,
        .u.next_level = athcfg_retries_cmds,
    },
    {
        .cmd_name = "waplist",
        .handler_present = FALSE,
        .u.next_level = athcfg_waplist_cmds,
    },
    {
        .cmd_name = "addbastat",
        .handler_present = FALSE,
        .u.next_level = athcfg_addbastat_cmds,
    },
    {
        .cmd_name = "chaninfo",
        .handler_present = FALSE,
        .u.next_level = athcfg_chaninfo_cmds,
    },
    {
        .cmd_name = "wpaie",
        .handler_present = FALSE,
        .u.next_level = athcfg_wpaie_cmds,
    },
    {
        .cmd_name = "wscie",
        .handler_present = FALSE,
        .u.next_level = athcfg_wscie_cmds,
    },
    {
        .cmd_name = "txpowlimit",
        .handler_present = FALSE,
        .u.next_level = athcfg_txpowlimit_cmds,
    },
    {
        .cmd_name = "athcap",
        .handler_present = FALSE,
        .u.next_level = athcfg_athcap_cmds,
    },
    {
        .cmd_name = "turbo",
        .handler_present = FALSE,
        .u.next_level = athcfg_turbo_cmds,
    },
    {
        .cmd_name = "filter",
        .handler_present = FALSE,
        .u.next_level = athcfg_filter_cmds,
    },
    {
        .cmd_name = "addbaresp",
        .handler_present = FALSE,
        .u.next_level = athcfg_ADDBAresp_cmds,
    },
    {
        .cmd_name = "mlme",
        .handler_present = FALSE,
        .u.next_level = athcfg_mlme_cmds,
    },
    {
        .cmd_name = "sendaddba",
        .handler_present = FALSE,
        .u.next_level = athcfg_sendADDBA_cmds,
    },
    {
        .cmd_name = "senddelba",
        .handler_present = FALSE,
        .u.next_level = athcfg_sendDELBA_cmds,
    },
    {
        .cmd_name = "delkey",
        .handler_present = FALSE,
        .u.next_level = athcfg_delkey_cmds,
    },
    {
        .cmd_name = "delmac",
        .handler_present = FALSE,
        .u.next_level = athcfg_delmac_cmds,
    },
    {
        .cmd_name = "addmac",
        .handler_present = FALSE,
        .u.next_level = athcfg_addmac_cmds,
    },
    {
        .cmd_name = "opmode",
        .handler_present = FALSE,
        .u.next_level = athcfg_opmode_cmds,
    },
    {
        .cmd_name = "vapdelete",
        .handler_present = FALSE,
        .u.next_level = athcfg_vapdelete_cmds,
    },
    {
        .cmd_name = "athstats",
        .handler_present = FALSE,
        .u.next_level = athcfg_athstats_cmds,
    },
    {
        .cmd_name = "athdiag",
        .handler_present = FALSE,
        .u.next_level = athcfg_athdiag_cmds,
    },
    {
        .cmd_name = "phyerr",
        .handler_present = FALSE,
        .u.next_level = athcfg_phyerr_cmds,
    },
    {
        .cmd_name = "athcwm",
        .handler_present = FALSE,
        .u.next_level = athcfg_athcwm_cmds,
    },
    {
        .cmd_name = "ethtool",
        .handler_present = FALSE,
        .u.next_level = athcfg_ethtool_cmds,
    },
    {
        .cmd_name = "vapcreate",
        .handler_present = FALSE,
        .u.next_level = athcfg_vapcreate_cmds,
    },
    {
        .cmd_name = "stats",
        .handler_present = FALSE,
        .u.next_level = athcfg_stats_cmds,
    },
    {
        .cmd_name = "athmac",
        .handler_present = FALSE,
        .u.next_level = athcfg_athmac_cmds,
    },
    {
        .cmd_name = "mtu",
        .handler_present = FALSE,
        .u.next_level = athcfg_mtu_cmds,
    },
    {
        .cmd_name = "txtimeout",
        .handler_present = FALSE,
        .u.next_level = athcfg_txtimeout_cmds,
    },
    {
        .cmd_name = "modeinit",
        .handler_present = FALSE,
        .u.next_level = athcfg_modeinit_cmds,
    },
    {
        .cmd_name = "statsclear",
        .handler_present = FALSE,
        .u.next_level = athcfg_statsclear_cmds,
    },
    {
        .cmd_name = "range",
        .handler_present = FALSE,
        .u.next_level = athcfg_getrange_cmds,
    },
    {
         .cmd_name = "statsinfo",
         .handler_present = FALSE,
         .u.next_level = athcfg_statsinfo_cmds,
    },
    {
         .cmd_name = "stainfo",
         .handler_present = FALSE,
         .u.next_level = athcfg_stainfo_cmds,
    },
    {
         .cmd_name = "stastats",
         .handler_present = FALSE,
         .u.next_level = athcfg_stastats_cmds,
    },   
    {
         .cmd_name = "eifs-mask",
         .handler_present = FALSE,
         .u.next_level = athcfg_eifsmask_cmds,
    },   
    {
         .cmd_name = "eifs-dur",
         .handler_present = FALSE,
         .u.next_level = athcfg_eifsdur_cmds,
    },   
    {
         .cmd_name = "slot-time",
         .handler_present = FALSE,
         .u.next_level = athcfg_slottime_cmds,
    },   
    {
         .cmd_name = "ack-timeout",
         .handler_present = FALSE,
         .u.next_level = athcfg_acktimeout_cmds,
    },   
    {
         .cmd_name = "cts-timeout",
         .handler_present = FALSE,
         .u.next_level = athcfg_ctstimeout_cmds,
    },   
    {
         .cmd_name = "soft-led",
         .handler_present = FALSE,
         .u.next_level = athcfg_softled_cmds,
    },   
    {
         .cmd_name = "led-pin",
         .handler_present = FALSE,
         .u.next_level = athcfg_ledpin_cmds,
    },   
    {
         .cmd_name = "debug",
         .handler_present = FALSE,
         .u.next_level = athcfg_debug_cmds,
    },   
    {
         .cmd_name = "tx-antenna",
         .handler_present = FALSE,
         .u.next_level = athcfg_tx_antenna_cmds,
    },   
    {
         .cmd_name = "rx-antenna",
         .handler_present = FALSE,
         .u.next_level = athcfg_rx_antenna_cmds,
    },   
    {
         .cmd_name = "diversity",
         .handler_present = FALSE,
         .u.next_level = athcfg_deversity_cmds,
    },   
    {
         .cmd_name = "tx-intr-period",
         .handler_present = FALSE,
         .u.next_level = athcfg_tx_intrperiod_cmds,
    },   
    {
         .cmd_name = "product-info",
         .handler_present = FALSE,
         .u.next_level = athcfg_product_info_cmds,
    },
    {
         .cmd_name = "fftxq-min",
         .handler_present = FALSE,
         .u.next_level = athcfg_txq_min_cmds,
    },   
    {
         .cmd_name = "tkip-mic",
         .handler_present = FALSE,
         .u.next_level = athcfg_tkipmic_cmds,
    },   
    {
         .cmd_name = "glob-tx-timeout",
         .handler_present = FALSE,
         .u.next_level = athcfg_glob_txtimeout_cmds,
    },   
    {
         .cmd_name = "sw-wsc-button",
         .handler_present = FALSE,
         .u.next_level = athcfg_sw_wscbutton_cmds,
    },   
    {
         .cmd_name = "country-code",
         .handler_present = FALSE,
         .u.next_level = athcfg_country_code_cmds,
    },   
    
    {
        .cmd_name = "nom-nf",
        .handler_present = FALSE,
        .u.next_level = athcfg_nominal_nf_cmd,
    },   
    {
        .cmd_name = "min-nf",
        .handler_present = FALSE,
        .u.next_level = athcfg_minimum_nf_cmd,
    },   
    {
        .cmd_name = "max-nf",
        .handler_present = FALSE,
        .u.next_level = athcfg_maximum_nf_cmd,
    },   
    {
        .cmd_name = "nf-delta",
        .handler_present = FALSE,
        .u.next_level = athcfg_nf_delta_cmd,
    },   
    {
        .cmd_name = "nf-weight",
        .handler_present = FALSE,
        .u.next_level = athcfg_nf_weight_cmd,
    },   
    {
        .cmd_name = "nf-switch",
        .handler_present = FALSE,
        .u.next_level = athcfg_nf_switch_cmd,
    },   
    
    {
        .cmd_name = "ant-div",
        .handler_present = FALSE,
        .u.next_level = athcfg_ant_div_enable_cmd,
    },   
    {
        .cmd_name = "ant-div-params",
        .handler_present = FALSE,
        .u.next_level = athcfg_ant_div_param_get_cmd,
    },   
    {
        .cmd_name = "ant-div-default",
        .handler_present = FALSE,
        .u.next_level = athcfg_ant_div_default_cmd,
    },   
    {
         .cmd_name = "reg-domain",
         .handler_present = FALSE,
         .u.next_level = athcfg_reg_domain_cmds,
    },   
    {
         .cmd_name = "dbg-info",
         .handler_present = FALSE,
         .u.next_level = athcfg_dbg_info_cmds,
    },   
    {
    .cmd_name = "hst-stats",
    .handler_present = FALSE,
        .u.next_level = athcfg_hst_stats_cmds,
    },
    {
     .cmd_name = "hst-11n-stats",
         .handler_present = FALSE,
         .u.next_level = athcfg_hst_11n_stats_cmds,
    },      
    {
     .cmd_name = "tgt-11n-stats",
         .handler_present = FALSE,
         .u.next_level = athcfg_tgt_11n_stats_cmds,
    },      
    {
         .cmd_name = "reg",
         .handler_present = FALSE,
         .u.next_level = athcfg_reg_cmds,
    },
    {
         .cmd_name = "pktlog",
         .handler_present = FALSE,
         .u.next_level = athcfg_pktlog_cmds,
    },
    {
        .cmd_name = "nextscandelay",
        .handler_present = FALSE,
        .u.next_level = athcfg_scandelay_cmds,
    },

#ifdef OMNI_MX_LED	
    {
         .cmd_name = "led-stats",
      	 .handler_present = FALSE,
	       .u.next_level = athcfg_ledstats_cmds,
    },
#endif       
    {
         .cmd_name = "ether-dongle-mac",
         .handler_present = FALSE,
         .u.next_level = athcfg_ether_dongle_mac_cmds,
    }, 
    {
         .cmd_name = "sta-assoc",
         .handler_present = FALSE,
         .u.next_level = athcfg_sta_start_assoc_cmds,
    }, 
    {
         .cmd_name = "auto-assoc",
         .handler_present = FALSE,
         .u.next_level = athcfg_autoassociation_cmds,
    },
    {
         .cmd_name = "radio",
         .handler_present = FALSE,
         .u.next_level = athcfg_radio_cmds,
    },
    {
         .cmd_name = "specscan",
         .handler_present = FALSE,
         .u.next_level = athcfg_spec_chan_scan_cmds,
    },
    {
        .cmd_name = "hidden-ssid",
        .handler_present = FALSE,
        .u.next_level = athcfg_hidden_ssid_cmds,
    },     
    {
         .cmd_name = "testmode",
         .handler_present = FALSE,
         .u.next_level = athcfg_testmode_cmds,
    }, 
    {
         .cmd_name = "htrates",
         .handler_present = FALSE,
         .u.next_level = athcfg_htrates_cmds,
    },
    {
         .cmd_name = "wmi-timeout",
         .handler_present = FALSE,
         .u.next_level = athcfg_wmi_timeout_cmds,
    },
    {
        .cmd_name = "keepscanlist",
        .handler_present = FALSE,
        .u.next_level = athcfg_keepscanlist_cmds,
    },

};


/*****************************************************************************
 *                      Helper functions                                     *
 *****************************************************************************/
/**
 * @brief Looks for Next Level
 */
athcfg_cmd_t * 
athcfg_cmd_lookup(athcfg_cmd_t prev_level[], int size, char *key)
{
    int i = 0;
    for(i = 0; i < size; i++){
        if(!(strcmp(prev_level[i].cmd_name, key))) {
            if (! (prev_level[i].handler_present))
                return((prev_level[i].u.next_level));
            else 
                return (&prev_level[i]);
        }
    }
    return 0;
}

/**
 * @brief Counting the number of elements in an ARRAY
 */
int
athcfg_arr_nelem(athcfg_cmd_t *prev_level)
{
   if(! (strcmp(prev_level->cmd_name,"get") ))
        return(2);
    else
        return(sizeof(athcfg_cmds) /sizeof(athcfg_cmds[0]) );
}

/**
 * @brief Display the list of commands supported 
 */
static void 
athcfg_usage(athcfg_cmd_t *cmds)
{
    /*
     * Cycle through and print all possible usage strings from this level on.
     */
    int i = 0,j = 0;
    int space = 0;
    athcfg_cmd_t *next_level;
 
    printf("Athcfg Usage:-- \n");   
    
    for(i = 0; i < (sizeof(athcfg_cmds)/sizeof(athcfg_cmds[0]))  ; i++) {
    if (!cmds[i].handler_present)
        printf(" %s",cmds[i].cmd_name);
        next_level = cmds[i].u.next_level;
    
        for(j = 0; j < 2; j++) {
            if(j == 0){
            for(space = 0; space <= 25-(strlen(cmds[i].cmd_name)); space++) 
                printf(" ");   
                printf("[");       
            }   
            if (next_level[j].handler_present)
                printf(" %s ",next_level[j].cmd_name);

            if ( (next_level[0].handler_present) && (next_level[1].handler_present) && j == 0 )
                printf("|");
        }
        printf("]\n");
    }
}

/**
 * @brief Call IOCTL in SHIM
 */
static int
athcfg_do_ioctl(int number, athcfg_wcmd_t *i_req)
{
    int s;
#ifdef OS_TYPE_LINUX
    struct ifreq ifr;
    strcpy(ifr.ifr_name, i_req->if_name);
    ifr.ifr_data = (__caddr_t)i_req;
#endif
    /* Try to open the socket, if success returns it */
    s = socket(AF_INET, SOCK_DGRAM, 0);
    if(s < 0) {
        fprintf(stderr, "Error creating socket: %s\n", strerror(errno));
        return -1;
    }
#ifdef OS_TYPE_LINUX
    if ((errno=ioctl (s,number, &ifr)) !=0) {
#else
    if ((errno=ioctl (s,number, i_req)) !=0) {
#endif
        fprintf(stderr, "Error doing ioctl(): %s\n", strerror(errno));
    close(s);
        return -1;
    }
    close(s);
    return 0;
}

/** 
 *@brief The main program starts
 */
int
main(int argc, char *argv[])
{
    athcfg_cmd_t *level = NULL;
    athcfg_cmd_t *prev_level = NULL;
    int num_elems = 0;
    int check1 = 0;
    int check2 = 0;
    char *ifrn_name;
    int i = 0; 

    if (argc <=1) {
        athcfg_usage(athcfg_cmds);
        return 0;
    }

    /* Check the validity of the user input */
    if (argc <= 3) {
        fprintf(stderr, "Too few args!  ");
        COMMAND_USAGE_INFO;
        athcfg_usage(athcfg_cmds);
        return EINVAL;
    }

    /* take the interface for further processing */
    ifrn_name = argv[1];

    /* check the validity of command_name */
    for(i = 0; i < (sizeof(athcfg_cmds)/sizeof(athcfg_cmds[0])) ; i++) {
        if( !(strcmp (argv[2], athcfg_cmds[i].cmd_name)) ) {
            check1 = 1; 
            break;
        }     
    }

    if(!(check1)) { 
        COMMAND_USAGE_INFO;    
        athcfg_usage(athcfg_cmds);
        return EINVAL;  
    }
   
    if(!(strcmp(argv[3], "get")) || !(strcmp(argv[3], "set")) || !(strcmp(argv[3], "enable")) || !(strcmp(argv[3], "disable")))  
        check2 = 1;

    if(!(check2)) {
        COMMAND_USAGE_INFO;
        athcfg_usage(athcfg_cmds);
        return EINVAL;
    }

    /*
     * Start at the top-most level and stop when a handler is found
     */ 
    prev_level = &athcfg_cmds[0];
    for (i = 2; i <= 3; i++) {
        num_elems = athcfg_arr_nelem(prev_level);
        level = athcfg_cmd_lookup(prev_level, num_elems, argv[i]);
        if (level == NULL) {
            fprintf(stderr, "Invalid arg %s!\n", argv[i]);
            athcfg_usage(athcfg_cmds);
            return EINVAL;
        }
        prev_level = level;
    }
    /*
     * Pass all remaining args as long as they are within bounds.
     */
    if (argc - i < level->h_min_args || argc - i > level->h_max_args) {
        fprintf(stderr, "Unacceptable arg count: minimum argument --  %d\n",\
                              level->h_min_args);
        fprintf(stderr,"            maximum argument --  %d\n",\
                              level->h_max_args);
        athcfg_usage(athcfg_cmds);
        return EINVAL;
    }
    return level->h_fn(argv[1], argc - i, &argv[i]);
}

/*****************************************************************************
 *                      Command Handlers                                     *  
 *****************************************************************************/
/**
 * @brief Handlers for Get/Set ESSID
 */
int
athcfg_ssid_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_ssid_t ssid; 
    memset(&ssid, 0, sizeof(athcfg_wcmd_ssid_t));
    args[0] = NULL;

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    ssid.len = ATHCFG_WCMD_MAX_SSID;
    i_req.type = ATHCFG_WCMD_GET_ESSID;
    i_req.d_essid = ssid;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "SSID -- %s\n", i_req.d_essid.byte);

    return 0;
}

int
athcfg_ssid_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req ;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_ssid_t ssid; 
    memset(&ssid, 0, sizeof(athcfg_wcmd_ssid_t));

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    ssid.len = strlen(args[0]); 
    if( (!strcasecmp(args[0], "off")) || (!strcasecmp(args[0], "any")) ) {
        ssid.flags = 0;
        ssid.len = 0;
        ssid.byte[0] = '\0';
    }        
    else if( (!strcasecmp(args[0], "on")) ) { 
        nargs = 0;
        args[0] = 0;
        athcfg_ssid_get(ifrn_name, nargs, args);
        ssid.flags = 1;            
    }
    else if( (strlen(args[0]) <= ATHCFG_WCMD_MAX_SSID) )
        strcpy((char *)ssid.byte, args[0]);
    
    else 
    ABORT_ARG_SIZE(ifrn_name, "SSID", ATHCFG_WCMD_MAX_SSID);

    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_ESSID;
    i_req.d_essid = ssid;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "SSID -- %s\n", i_req.d_essid.byte);

    return 0;
}

 /**
 * @brief Handlers for Get/Set RTS THRESHOLD
 */
int
athcfg_rts_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_rts_th_t rts; 
    memset(&rts, 0, sizeof(athcfg_wcmd_rts_th_t));
    args[0] = NULL;
    
    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_RTS_THRES;
    i_req.d_rts = rts;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "RTS Threshold -- %d\n", i_req.d_rts.threshold);

    return 0;
}

int
athcfg_rts_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_rts_th_t rts; 
    memset(&rts, 0, sizeof(athcfg_wcmd_rts_th_t));

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    rts.threshold = -1;
    rts.disabled = 0;
    rts.fixed = 1;
    
    if(!strcasecmp(args[0], "off"))
    rts.disabled = 1;     /* i.e. max size */
    
    else if (!strcasecmp(args[0], "auto"))
        rts.fixed = 0;

    else if(!strcasecmp(args[0], "fixed")) 
        rts.fixed = 1;

    else if(sscanf(args[0], "%hd", &(rts.threshold)) != 1 ) {
    ABORT_ARG_TYPE("ifrn_name", "ATHCFG_WCMD_SET_RTS_THRES", args[0]);
    printf("Please choose one from the list below --\n");
    printf("                    off\n");
    printf("                    auto\n");
    printf("                    fixed\n");
    printf("                    short int value");
    return 0;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_RTS_THRES;
    i_req.d_rts = rts;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "RTS Threshold -- %d\n", i_req.d_rts.threshold);

    return 0;
}

/**
 * @brief Handlers for Get/Set FRAGMENT THRESHOLD
 */
int
athcfg_fragthres_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_frag_th_t frag; 
    memset(&frag, 0, sizeof(athcfg_wcmd_frag_th_t));
    args[0] = NULL;

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_FRAGMENT;
    i_req.d_frag = frag;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "Fragment threshold -- %d\n", i_req.d_frag.threshold);

    return 0;
}

int
athcfg_fragthres_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_frag_th_t frag; 
    memset(&frag, 0, sizeof(athcfg_wcmd_frag_th_t));

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    frag.threshold = -1;
    frag.disabled = 0;
    frag.fixed = 1;
    
    if(!strcasecmp(args[0], "off"))
    frag.disabled = 1;     /* i.e. max size */

    else if(!strcasecmp(args[0], "auto"))
        frag.fixed = 0;

    else if (!strcasecmp(args[0], "fixed")) {
            /* Get old RTS threshold */
        athcfg_fragthres_get(ifrn_name, nargs -1, ++args);
        frag.fixed = 1;
    }
    
    else if(sscanf(args[0], "%li", (unsigned long *) &(frag.threshold)) != 1) {
    ABORT_ARG_TYPE(ifrn_name, "ATHCFG_WCMD_SET_FRAGMENT", args[0]);
    printf("Please choose one from the list below --\n");
    printf("                    off\n");
    printf("                    auto\n");
    printf("                    fixed\n");
    printf("                    short int value");
    return 0;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_FRAGMENT;
    i_req.d_frag = frag;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "Fragment Threshold -- %d\n", i_req.d_frag.threshold);

    return 0;
}

/**
 * @brief Handlers for Get/Set BSSID
 */
int
athcfg_bssid_get(char *ifrn_name, int nargs, char *args[])
{
    int count = 0; 
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_bssid_t bssid; 
    memset(&bssid, 0, sizeof(athcfg_wcmd_bssid_t));
    args[0] = NULL;

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_BSSID;
    i_req.d_bssid = bssid;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;
    
    printf("BSSID --- ");    
    for(count = 0; count < 6; count++){
        fprintf(stdout, "%02x", i_req.d_bssid.bssid[count]);
    if(count <5)
        printf(":");
    }
    printf("\n");


    return 0;
}

int
athcfg_bssid_set(char *ifrn_name, int nargs, char *args[])
{
    int count = 0;
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_bssid_t bssid; 
    memset(&bssid, 0, sizeof(athcfg_wcmd_bssid_t));

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    if( (!strcasecmp(args[0],"any")) || (!strcasecmp(args[0],"auto")) ) 
        memset((char *)bssid.bssid, 0xFF, ATHCFG_WCMD_ADDR_LEN);

    else if( (!strcasecmp(args[0],"off")) )        
        memset((char *)bssid.bssid, 0x00, ATHCFG_WCMD_ADDR_LEN);

    else if( (strlen(args[0]) ==  ATHCFG_WCMD_MAC_STR_LEN) ) {
    sscanf(args[0],"%x:%x:%x:%x:%x:%x", (unsigned int *)&bssid.bssid[0],\
             (unsigned int *)&bssid.bssid[1],\
             (unsigned int *)&bssid.bssid[2],\
             (unsigned int *)&bssid.bssid[3],\
             (unsigned int *)&bssid.bssid[4],\
             (unsigned int *)&bssid.bssid[5] );
    }   
    else {
    printf("!! ERROR !!\n");
        printf("Please enter bssid in format of -- 00:11:22:33:44:55\n");
    ABORT_ARG_SIZE(ifrn_name, "Bssid", ATHCFG_WCMD_ADDR_LEN);
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_BSSID;
    i_req.d_bssid = bssid;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;
    
    printf("BSSID -- ");
    for(count = 0; count < 6; count++){
        fprintf(stdout, "%x", i_req.d_bssid.bssid[count]);
    if(count <5)
        printf(":");
    }
    printf("\n");
    return 0;
}


/**
 * @brief Handlers for get/set multicast address
 */
int
athcfg_mcast_get(char *ifrn_name, int nargs, char *args[])
{
    return 0;
}

int
athcfg_mcast_set(char *ifrn_name, int nargs, char *args[])
{
    int count = 0, sock;
    struct ifreq i_req;
    unsigned int addr[ATHCFG_WCMD_ADDR_LEN];
    
    memset(&i_req,0, sizeof(struct ifreq));

    assert (nargs == 2);

    if (strlen(ifrn_name) > sizeof(i_req.ifr_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    if( strcmp(args[0], "add") && strcmp(args[0], "del") ) {
        fprintf(stderr, "Please enter command as \n\r\t - athcfg %s mcast set [add/del] 01:11:22:33:44:55 !\n", ifrn_name);
        return EIO;
    }

    if((strlen(args[1]) == ATHCFG_WCMD_MAC_STR_LEN)) {

        sscanf(args[1],"%x:%x:%x:%x:%x:%x",(unsigned int *)&addr[0],\
            (unsigned int *)&addr[1],\
            (unsigned int *)&addr[2],\
            (unsigned int *)&addr[3],\
            (unsigned int *)&addr[4],\
            (unsigned int *)&addr[5] );

    }
    else {
        printf("!! ERROR !!\n");
        printf("Please enter mac addr in format of -- 00:11:22:33:44:55\n");
        return EIO;
    }


    if((sock = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) == -1)
    {
        printf("%s",strerror(errno));
        return EIO;
    }

    /*
    fprintf(stderr, "[%s] mcast addr (%x:%x:%x:%x:%x:%x) to %s !\n", (strcmp(args[0], "add"))?"del":"add", addr[0], addr[1], addr[2], addr[3], addr[4], addr[5], ifrn_name);
    */

    strncpy(i_req.ifr_name, ifrn_name, strlen(ifrn_name));

    i_req.ifr_ifru.ifru_addr.sa_data[0] = addr[0];
    i_req.ifr_ifru.ifru_addr.sa_data[1] = addr[1];
    i_req.ifr_ifru.ifru_addr.sa_data[2] = addr[2];
    i_req.ifr_ifru.ifru_addr.sa_data[3] = addr[3];
    i_req.ifr_ifru.ifru_addr.sa_data[4] = addr[4];
    i_req.ifr_ifru.ifru_addr.sa_data[5] = addr[5];

    if( ioctl(sock, (strcmp(args[0], "add"))?SIOCDELMULTI:SIOCADDMULTI,&i_req)!= -1)
    {
        printf("%s",strerror(errno));
        return EIO;
    }
               
    printf("MCAST -- ");
    for(count = 0; count < 6; count++){
        fprintf(stdout, "%x", i_req.ifr_ifru.ifru_addr.sa_data[count]);
        
    if(count <5)
        printf(":");
    }

    printf("\n");

    return 0;
}


/**
 * @brief Handlers for Get/Set NICKNAME
 */
int
athcfg_nick_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    athcfg_wcmd_nickname_t nick; 
    memset(&nick, 0, sizeof(athcfg_wcmd_nickname_t));
    args[0] = NULL;

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_NICKNAME;
    i_req.d_nickname = nick;
    
    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "Nickname -- %s\n", i_req.d_nickname.name);
    
    return 0; 
}   

int
athcfg_nick_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_nickname_t nick; 
    memset(&nick, 0, sizeof(athcfg_wcmd_nickname_t));

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    if(strlen((char *)args[0]) <= ATHCFG_WCMD_NICK_NAME)
        strcpy(( char *)nick.name,args[0]);
    else
        ABORT_ARG_SIZE(ifrn_name, "Nickname", ATHCFG_WCMD_SET_NICKNAME);

    nick.len = strlen(args[0]);
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_NICKNAME;
    i_req.d_nickname = nick;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "Nickname -- %s\n", ((char *)i_req.d_nickname.name) );
        return 0;
}

/**
 * @brief Handlers for Get/Set FREQUENCY
 */
int
athcfg_freq_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_freq_t freq; 
    memset(&freq, 0, sizeof(athcfg_wcmd_freq_t));
    args[0] = NULL;

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_FREQUENCY;
    i_req.d_freq = freq;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "Frequency -- %d   %d\n", i_req.d_freq.m, i_req.d_freq.e);

    return 0;
}

int
athcfg_freq_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_freq_t freq; 
    memset(&freq, 0, sizeof(athcfg_wcmd_freq_t));
    assert (nargs >= 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    freq.m = atoi(args[0]); 
    freq.e = atoi(args[1]); 
    
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_FREQUENCY;
    i_req.d_freq = freq;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "Frequency -- %d.%d\n", i_req.d_freq.m,i_req.d_freq.e);
    
    return 0;
}

/**
 * @brief Handlers for Get/Set TX-POWER.
 */
int
athcfg_txpower_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_txpower_t txpower; 
    memset(&txpower, 0, sizeof(athcfg_wcmd_txpower_t));
    args[0] = NULL;

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_TX_POWER;
    i_req.d_txpower = txpower;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;
   
    //Remind: the unit of i_req.d_txpower.txpowertable[x] is 0.5 dBm.
    const char *rateString[] = {" 6mb OFDM", " 9mb OFDM", "12mb OFDM", "18mb OFDM",
                                "24mb OFDM", "36mb OFDM", "48mb OFDM", "54mb OFDM",
                                "1L   CCK ", "XR       ", "2L   CCK ", "2S   CCK ", 
                                "5.5L CCK ", "5.5S CCK ", "11L  CCK ", "11S  CCK ",
                                "HT20mcs 0", "HT20mcs 1", "HT20mcs 2", "HT20mcs 3",
                                "HT20mcs 4", "HT20mcs 5", "HT20mcs 6", "HT20mcs 7",
                                "HT40mcs 0", "HT40mcs 1", "HT40mcs 2", "HT40mcs 3",
                                "HT40mcs 4", "HT40mcs 5", "HT40mcs 6", "HT40mcs 7",
                               //"Dup CCK  ", "Dup OFDM ", "Ext CCK  ", "Ext OFDM ",
    };
    a_int32_t i;
    
    //Show current tx power table in 1 dBm unit
    //Don't show "Dup CCK  ", "Dup OFDM ", "Ext CCK  ", "Ext OFDM rate 
    for (i = 0; i < (ATHCFG_WCMD_TX_POWER_TABLE_SIZE-4); i+=4) {  
        if (i == 8)  //Don't show XR rate
        {        
            fprintf(stdout, " %s %3d.%1d dBm | %s %3d.%1d dBm | %s %3d.%1d dBm \n",
            rateString[i], i_req.d_txpower.txpowertable[i] / 2, (i_req.d_txpower.txpowertable[i] % 2) * 5,
            rateString[i + 2], i_req.d_txpower.txpowertable[i + 2] / 2, (i_req.d_txpower.txpowertable[i + 2] % 2) * 5,
            rateString[i + 3], i_req.d_txpower.txpowertable[i + 3] / 2, (i_req.d_txpower.txpowertable[i + 3] % 2) * 5);
            continue;
        }            
        fprintf(stdout, " %s %3d.%1d dBm | %s %3d.%1d dBm | %s %3d.%1d dBm | %s %3d.%1d dBm\n",
        rateString[i], i_req.d_txpower.txpowertable[i] / 2, (i_req.d_txpower.txpowertable[i] % 2) * 5,
        rateString[i + 1], i_req.d_txpower.txpowertable[i + 1] / 2, (i_req.d_txpower.txpowertable[i + 1] % 2) * 5,
        rateString[i + 2], i_req.d_txpower.txpowertable[i + 2] / 2, (i_req.d_txpower.txpowertable[i + 2] % 2) * 5,
        rateString[i + 3], i_req.d_txpower.txpowertable[i + 3] / 2, (i_req.d_txpower.txpowertable[i + 3] % 2) * 5);
    }

    //Mark it, due to it is useless.
    //fprintf(stdout, "Transmit Power -- %d\n", i_req.d_txpower.txpower);

    return 0;
}

int
athcfg_txpower_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_txpower_t txpower; 
    memset(&txpower, 0, sizeof(athcfg_wcmd_txpower_t));

    assert (nargs >= 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    txpower.txpower = -1;
    txpower.disabled = 0;
    txpower.flags = ATHCFG_WCMD_TXPOW_DBM; 
    txpower.fixed = 1;

    if((!(strcmp(args[0], "off")) )    || (!(strcmp(args[0], "auto")) )  || 
       (!(strcmp(args[0], "on")) )     || (!(strcmp(args[0], "fixed")) ) ||
       ((index(args[0], 'm') != NULL)) ||  ((index(args[0], 'd') != NULL )) ) {

        if(!(strcmp(args[0], "off")) )
            txpower.disabled = 1;
        else if(!(strcmp(args[0], "auto")) )
            txpower.fixed = 0;
        else if(!(strcmp(args[0], "on")) ) {
            athcfg_txpower_get(ifrn_name, 0, args);
            txpower.disabled = 0;       
        }else if(!(strcmp(args[0], "fixed")) ) { 
            athcfg_txpower_get(ifrn_name, 0,args);
            txpower.fixed = 1;
            txpower.disabled = 0;       
        }else {
            int power = 0;
            int ismwatt = 0;
            if(sscanf (args[0], "%i", &(power)) != 1) {
                printf("!! ERROR !! Unacceptable Command\n");
                printf(" Please choose one from the list below -- \n");
                printf("                       -- off\n");
                printf("                       -- auto\n");
                printf("                       -- on\n");
                printf("                       -- fixed\n");
                printf("                       -- Interger value\n");
                return 0;
            }

            ismwatt = ((index(args[0], 'm') != NULL) && (index(args[0], 'd') == NULL ));
            if(!ismwatt) {
                power = ath_dbm2mwatt(power);
                txpower.flags = ATHCFG_WCMD_TXPOW_MWATT;
            } else {
                power = ath_mwatt2dbm(power);
                txpower.flags = ATHCFG_WCMD_TXPOW_DBM;
            }           
            txpower.txpower = power;    
        }
    } else if (!(strcmp(args[0], "ex")) ) {
        int power = 0;
    
        if (nargs >= 3) {
            txpower.freq = atoi(args[2]); 
            if (txpower.freq > 161)
            {
                printf("!! ERROR !! Unacceptable freq\n");
                return 0;
            }
            if(sscanf (args[1], "%i", &(power)) != 1) {
                    printf("!! ERROR !! Unacceptable power\n");
                    return 0;
            }
            printf("power=%d, freq=%d\n", power, txpower.freq);
            txpower.flags = ATHCFG_WCMD_TXPOW_DBM_EX; 
            txpower.txpower = power;
        } else {
            printf("!! ERROR !! Unacceptable parameters\n");
            return 0;
        }
    } else {
        printf("!! -- ERROR -- !!\n");
        printf("Choose one from the list below --> \n");
        printf("                off \n");
        printf("                                 auto \n");      
        printf("                                 on \n");      
        printf("                                 fixed \n");      
        printf("                                 xxdBm \n");      
        printf("                                 xxmW \n");      
    }

    i_req.type = ATHCFG_WCMD_SET_TX_POWER;
    i_req.d_txpower = txpower;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    printf("Value set -- %d\n", txpower.txpower);

    fprintf(stdout, "Transmit Power -- %d\n", i_req.d_txpower.txpower);

    return 0;
}

/**
 * @brief Handlers for Get PARAM-INFO.
 */
int
athcfg_param_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_param_t param; 
    memset(&param, 0, sizeof(athcfg_wcmd_param_t));

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
     /* loop for the variable, user asked to get */
    if(!(strcmp(args[0], "turbo")))
            param.param_id = ATHCFG_WCMD_PARAM_TURBO;

    else if (!(strcmp (args[0], "phy-mode") ))
        param.param_id = ATHCFG_WCMD_PARAM_MODE;

    else if (!(strcmp (args[0], "auth-mode") ))
        param.param_id = ATHCFG_WCMD_PARAM_AUTHMODE;

    else if (!(strcmp (args[0], "prot-mode")  ))
        param.param_id = ATHCFG_WCMD_PARAM_PROTMODE;

    else if (!(strcmp (args[0], "multi-chiper") ))
        param.param_id = ATHCFG_WCMD_PARAM_MCASTCIPHER;

    else if (!(strcmp (args[0], "multikey-len") ))
        param.param_id = ATHCFG_WCMD_PARAM_MCASTKEYLEN;

    else if (!(strcmp (args[0],  "uni-chipers") ))
        param.param_id = ATHCFG_WCMD_PARAM_UCASTCIPHERS;

    else if (!(strcmp (args[0],  "uni-chiper") ))
        param.param_id = ATHCFG_WCMD_PARAM_UCASTCIPHER;

    else if (!(strcmp (args[0], "unikey-len") ))
        param.param_id = ATHCFG_WCMD_PARAM_UCASTKEYLEN;

    else if (!(strcmp (args[0], "wpa-mode") ))
        param.param_id = ATHCFG_WCMD_PARAM_WPA;

    else if (!(strcmp (args[0], "roaming") ))
        param.param_id = ATHCFG_WCMD_PARAM_ROAMING;

    else if (!(strcmp (args[0], "privacy") ))
        param.param_id = ATHCFG_WCMD_PARAM_PRIVACY;

    else if (!(strcmp (args[0], "counter-measures") ))
        param.param_id = ATHCFG_WCMD_PARAM_COUNTERMEASURES;

    else if (!(strcmp (args[0], "drop-unencry") ))
        param.param_id = ATHCFG_WCMD_PARAM_DROPUNENCRYPTED;

    else if (!(strcmp (args[0], "driver-caps") ))
        param.param_id = ATHCFG_WCMD_PARAM_DRIVER_CAPS;

    else if (!(strcmp (args[0], "mac-cmd") ))
        param.param_id = ATHCFG_WCMD_PARAM_MACCMD;

    else if (!(strcmp (args[0], "wmm-mode") ))
        param.param_id = ATHCFG_WCMD_PARAM_WMM;

    else if (!(strcmp (args[0], "hide-ssid") ))
        param.param_id = ATHCFG_WCMD_PARAM_HIDESSID;

    else if (!(strcmp (args[0],  "ap-bridge") ))
        param.param_id = ATHCFG_WCMD_PARAM_APBRIDGE;

    else if (!(strcmp (args[0], "key-algo") ))
        param.param_id = ATHCFG_WCMD_PARAM_KEYMGTALGS;

    else if (!(strcmp (args[0], "rsn-caps") ))
        param.param_id = ATHCFG_WCMD_PARAM_RSNCAPS;

    else if (!(strcmp (args [0], "stainact-timeout") ))
        param.param_id = ATHCFG_WCMD_PARAM_INACT;

    else if (!(strcmp (args[0], "staauth-inacttimeout") ))
        param.param_id = ATHCFG_WCMD_PARAM_INACT_AUTH;

    else if (!(strcmp (args[0], "stainit-inacttimeout") ))
        param.param_id = ATHCFG_WCMD_PARAM_INACT_INIT;

    else if (!(strcmp (args[0], "ath-caps") ))
        param.param_id = ATHCFG_WCMD_PARAM_ABOLT;

    else if (!(strcmp (args[0], "dtim-period") ))
        param.param_id = ATHCFG_WCMD_PARAM_DTIM_PERIOD;

    else if (!(strcmp (args[0], "beacon-intv") ))
        param.param_id = ATHCFG_WCMD_PARAM_BEACON_INTERVAL;

    else if (!(strcmp (args[0], "11h-mode") ))
        param.param_id = ATHCFG_WCMD_PARAM_DOTH;

    else if (!(strcmp (args[0], "currchan-power") ))
        param.param_id = ATHCFG_WCMD_PARAM_PWRTARGET;

    else if (!(strcmp (args[0], "gen-reassoc") ))
        param.param_id = ATHCFG_WCMD_PARAM_GENREASSOC;

    else if (!(strcmp (args[0], "compress") ))
        param.param_id = ATHCFG_WCMD_PARAM_COMPRESSION;

    else if (!(strcmp (args[0], "fast-frame") ))
        param.param_id = ATHCFG_WCMD_PARAM_FF;

    else if (!(strcmp (args[0], "xr-supp") ))
        param.param_id = ATHCFG_WCMD_PARAM_XR;

    else if (!(strcmp (args[0], "burst") ))
        param.param_id = ATHCFG_WCMD_PARAM_BURST;

    else if (!(strcmp (args[0], "pure-11g") ))
        param.param_id = ATHCFG_WCMD_PARAM_PUREG;

    else if (!(strcmp (args[0], "ar-supp") ))
        param.param_id = ATHCFG_WCMD_PARAM_AR;

    else if (!(strcmp (args[0], "wds-enable") ))
        param.param_id = ATHCFG_WCMD_PARAM_WDS;

    else if (!(strcmp (args[0], "bg-scan") ))
        param.param_id = ATHCFG_WCMD_PARAM_BGSCAN;

    else if (!(strcmp (args[0], "bgscan-thres") ))
        param.param_id = ATHCFG_WCMD_PARAM_BGSCAN_IDLE;

    else if (!(strcmp (args[0], "bgscan-intv") ))
        param.param_id = ATHCFG_WCMD_PARAM_BGSCAN_INTERVAL;

    else if (!(strcmp (args[0], "multi-txrate") ))
        param.param_id =  ATHCFG_WCMD_PARAM_MCAST_RATE;

    else if (!(strcmp (args[0], "cove-class") ))
        param.param_id = ATHCFG_WCMD_PARAM_COVERAGE_CLASS;

    else if (!(strcmp (args[0], "countryie-enable") ))
        param.param_id = ATHCFG_WCMD_PARAM_COUNTRY_IE;

    else if (!(strcmp (args[0], "scancash-thres") ))
        param.param_id = ATHCFG_WCMD_PARAM_SCANVALID;

    else if (!(strcmp (args[0], "rssithres-11a") ))
        param.param_id = ATHCFG_WCMD_PARAM_ROAM_RSSI_11A;

    else if (!(strcmp (args[0], "rssithres-11b") ))
        param.param_id = ATHCFG_WCMD_PARAM_ROAM_RSSI_11B;

    else if (!(strcmp (args[0], "rssithres-11g") ))
        param.param_id = ATHCFG_WCMD_PARAM_ROAM_RSSI_11G;

    else if (!(strcmp (args[0], "txrate-11a") ))
        param.param_id = ATHCFG_WCMD_PARAM_ROAM_RATE_11A;

    else if (!(strcmp (args[0], "txrate-11b") ))
        param.param_id = ATHCFG_WCMD_PARAM_ROAM_RATE_11B;

    else if (!(strcmp (args[0], "txrate-11g") ))
        param.param_id = ATHCFG_WCMD_PARAM_ROAM_RATE_11G;

    else if (!(strcmp (args[0], "qos-info") ))
        param.param_id = ATHCFG_WCMD_PARAM_UAPSDINFO;

    else if (!(strcmp (args[0], "sleep") ))
        param.param_id = ATHCFG_WCMD_PARAM_SLEEP;

    else if (!(strcmp (args[0], "qos-sleep") ))
        param.param_id = ATHCFG_WCMD_PARAM_QOSNULL;

    else if (!(strcmp (args[0], "pspoll") ))
        param.param_id = ATHCFG_WCMD_PARAM_PSPOLL;

    else if (!(strcmp (args[0], "eospdrop") ))
        param.param_id = ATHCFG_WCMD_PARAM_EOSPDROP;

    else if (!(strcmp (args[0], "markdfs-chan") ))
        param.param_id = ATHCFG_WCMD_PARAM_MARKDFS;

    else if (!(strcmp (args[0], "regclass") ))
        param.param_id = ATHCFG_WCMD_PARAM_REGCLASS;

    else if (!(strcmp (args[0], "chan-bandwidth") ))
        param.param_id = ATHCFG_WCMD_PARAM_CHANBW;

    else if (!(strcmp (args[0], "aggr-mode") ))
        param.param_id = ATHCFG_WCMD_PARAM_WMM_AGGRMODE;

    else if (!(strcmp (args[0],  "shortprmble") ))
        param.param_id = ATHCFG_WCMD_PARAM_SHORTPREAMBLE;

    else if (!(strcmp (args[0], "dfs-chan") ))
        param.param_id = ATHCFG_WCMD_PARAM_BLOCKDFSCHAN;

    else if (!(strcmp (args[0], "cwm-mode") ))
        param.param_id = ATHCFG_WCMD_PARAM_CWM_MODE;

    else if (!(strcmp (args[0], "ext-chan") ))
        param.param_id = ATHCFG_WCMD_PARAM_CWM_EXTOFFSET;

    else if (!(strcmp (args[0], "extchan-protmode") ))
        param.param_id = ATHCFG_WCMD_PARAM_CWM_EXTPROTMODE;

    else if (!(strcmp (args[0], "extchan-protspace") ))
        param.param_id = ATHCFG_WCMD_PARAM_CWM_EXTPROTSPACING;

    else if (!(strcmp (args[0], "cwmstate-enable") ))
        param.param_id = ATHCFG_WCMD_PARAM_CWM_ENABLE;

    else if (!(strcmp (args[0], "extchan-busythres") ))
        param.param_id = ATHCFG_WCMD_PARAM_CWM_EXTBUSYTHRESHOLD;

    else if (!(strcmp (args[0], "cwmh") ))
        param.param_id = ATHCFG_WCMD_PARAM_CWM_CHWIDTH;

    else if (!(strcmp (args[0], "half-gi") ))
        param.param_id = ATHCFG_WCMD_PARAM_SHORT_GI;

    else if (!(strcmp (args[0], "fast-chanchange") ))
        param.param_id = ATHCFG_WCMD_PARAM_FAST_CC;

    else if (!(strcmp (args[0], "11n-ampdu") ))
        param.param_id = ATHCFG_WCMD_PARAM_AMPDU;

    else if (!(strcmp (args[0], "ampdu-limit") ))
        param.param_id = ATHCFG_WCMD_PARAM_AMPDU_LIMIT;

    else if (!(strcmp (args[0], "ampdu-density") ))
        param.param_id = ATHCFG_WCMD_PARAM_AMPDU_DENSITY;

    else if (!(strcmp (args[0], "ampdu-subfram") ))
        param.param_id = ATHCFG_WCMD_PARAM_AMPDU_SUBFRAMES;

    else if (!(strcmp (args[0], "amsdu-supp") ))
        param.param_id = ATHCFG_WCMD_PARAM_AMSDU;

    else if (!(strcmp (args[0], "amsdu-length") ))
        param.param_id = ATHCFG_WCMD_PARAM_AMSDU_LIMIT;

    else if (!(strcmp (args[0], "country-code") ))
        param.param_id = ATHCFG_WCMD_PARAM_COUNTRYCODE;

    else if (!(strcmp (args[0], "txchan-mask") ))
        param.param_id = ATHCFG_WCMD_PARAM_TX_CHAINMASK;

    else if (!(strcmp (args[0], "rxchan-mask") ))
        param.param_id = ATHCFG_WCMD_PARAM_RX_CHAINMASK;

    else if (!(strcmp (args[0], "rts-rate") ))
        param.param_id = ATHCFG_WCMD_PARAM_RTSCTS_RATECODE;

    else if (!(strcmp (args[0], "ht-prot") ))
        param.param_id = ATHCFG_WCMD_PARAM_HT_PROTECTION;

    else if (!(strcmp (args[0], "reset-once") ))
        param.param_id = ATHCFG_WCMD_PARAM_RESET_ONCE;

    else if (!(strcmp (args[0], "set-addba") ))
        param.param_id = ATHCFG_WCMD_PARAM_SETADDBAOPER;

    else if (!(strcmp (args[0], "txchanmask-legacy") ))
        param.param_id = ATHCFG_WCMD_PARAM_TX_CHAINMASK_LEGACY;

    else if (!(strcmp (args[0], "11n-rate") ))
        param.param_id = ATHCFG_WCMD_PARAM_11N_RATE;

    else if (!(strcmp (args[0], "11n-retries") ))
        param.param_id = ATHCFG_WCMD_PARAM_11N_RETRIES;

    else if (!(strcmp (args[0], "wds-autodetect") ))
        param.param_id = ATHCFG_WCMD_PARAM_WDS_AUTODETECT;

    else if (!(strcmp (args[0], "rb-inout") ))
        param.param_id = ATHCFG_WCMD_PARAM_RB;

    else if (!(strcmp (args[0], "rb-detect") ))
        param.param_id = ATHCFG_WCMD_PARAM_RB_DETECT;

    else if (!(strcmp (args[0], "rbskip-thres") ))
        param.param_id = ATHCFG_WCMD_PARAM_RB_SKIP_THRESHOLD;

    else if (!(strcmp (args[0], "rb-timeout") ))
        param.param_id = ATHCFG_WCMD_PARAM_RB_TIMEOUT;

    else if (!(strcmp (args[0], "htie-stat") ))
        param.param_id = ATHCFG_WCMD_PARAM_NO_HTIE;

    else if (!(strcmp (args[0], "MAXSTA") ))
        param.param_id = ATHCFG_WCMD_PARAM_MAXSTA;

    else {
        printf("!! ERROR !! \n");
        printf("Choose one from the list below -- \n");
        printf("                   -- turbo\n");
        printf("                   -- phy-mode\n");
        printf("                   -- auth-mode\n");
        printf("                   -- prot-mode\n");
        printf("                   -- multi-chiper\n");
        printf("                   -- multikey-len\n");
        printf("                   -- uni-chipers\n");
        printf("                   -- uni-chiper\n");
        printf("                   -- unikey-len\n");
        printf("                   -- wpa-mode\n");
        printf("                   -- roaming\n");
        printf("                   -- privacy\n");
        printf("                   -- counter-measures\n");
        printf("                   -- drop-unencry\n");
        printf("                   -- driver-caps\n");
        printf("                   -- mac-cmd\n");
        printf("                   -- wmm-mode\n");
        printf("                   -- hide-ssid\n");
        printf("                   -- ap-bridge\n");
        printf("                   -- key-algo\n");
        printf("                   -- rsn-caps\n");
        printf("                   -- stainact-timeout\n");
        printf("                   -- staauth-inacttimeout\n");
        printf("                   -- stainit-inacttimeout\n");
        printf("                   -- ath-caps\n");
        printf("                   -- dtim-period\n");
        printf("                   -- beacon-intv\n");
        printf("                   -- 11h-mode\n");
        printf("                   -- currchan-power\n");
        printf("                   -- gen-reassoc\n");
        printf("                   -- compress\n");
        printf("                   -- fast-frame\n");
        printf("                   -- xr-supp\n");
        printf("                   -- burst\n");
        printf("                   -- pure-11g\n");
        printf("                   -- ar-supp\n");
        printf("                   -- wds-enable\n");
        printf("                   -- bg-scan\n");
        printf("                   -- bgscan-thres\n");
        printf("                   -- bgscan-intv\n");
        printf("                   -- multi-txrate\n");
        printf("                   -- cove-class\n");
        printf("                   -- countryie-enable\n");
        printf("                   -- scancash-thres\n");
        printf("                   -- rssithres-11a\n");
        printf("                   -- rssithres-11b\n");
        printf("                   -- rssithres-11g\n");
        printf("                   -- txrate-11a\n");
        printf("                   -- txrate-11b\n");
        printf("                   -- txrate-11g\n");
        printf("                   -- qos-info\n");
        printf("                   -- sleep\n");
        printf("                   -- qos-sleep\n");
        printf("                   -- pspoll\n");
        printf("                   -- eospdrop\n");
        printf("                   -- markdfs-chan\n");
        printf("                   -- regclass\n");
        printf("                   -- chan-bandwidth\n");
        printf("                   -- aggr-mode\n");
        printf("                   -- shortprmble\n");
        printf("                   -- dfs-chan\n");
        printf("                   -- cwm-mode\n");
        printf("                   -- ext-chan\n");
        printf("                   -- extchan-protmode\n");
        printf("                   -- extchan-protspace\n");
        printf("                   -- cwmstate-enable\n");
        printf("                   -- extchan-busythres\n");
        printf("                   -- cwmh\n");
        printf("                   -- half-gi\n");
        printf("                   -- fast-chanchange\n");
        printf("                   -- 11n-ampdu\n");
        printf("                   -- ampdu-limit\n");
        printf("                   -- ampdu-density\n");
        printf("                   -- ampdu-subfram\n");
        printf("                   -- amsdu-supp\n");
        printf("                   -- amsdu-length\n");
        printf("                   -- country-code\n");
        printf("                   -- txchan-mask\n");
        printf("                   -- rxchan-mask\n");
        printf("                   -- rts-rate\n");
        printf("                   -- ht-prot\n");
        printf("                   -- reset-once\n");
        printf("                   -- set-addba\n");
        printf("                   -- txchanmask-legacy\n");
        printf("                   -- 11n-rate\n");
        printf("                   -- 11n-retries\n");
        printf("                   -- wds-autodetect\n");
        printf("                   -- rb-inout\n");
        printf("                   -- rb-detect\n");
        printf("                   -- rbskip-thres\n");
        printf("                   -- rb-timeout\n");
        printf("                   -- htie-stat\n");
        printf("                   -- MAXSTA\n");

       return  0;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_PARAM;
    i_req.d_param = param;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    switch(i_req.d_param.param_id) {
    case ATHCFG_WCMD_PARAM_TURBO:
        printf("%s  ",ENUM_PRINT(TURBO --));
        break;

    case ATHCFG_WCMD_PARAM_MODE:
        printf("%s  ",ENUM_PRINT(MODE --));
            break;
    
    case ATHCFG_WCMD_PARAM_AUTHMODE:
        printf("%s  ",ENUM_PRINT(AUTHMODE --));
            break;

    case ATHCFG_WCMD_PARAM_PROTMODE:
        printf("%s  ",ENUM_PRINT(PROTMODE --));
            break;

    case ATHCFG_WCMD_PARAM_MCASTCIPHER:
        printf("%s  ",ENUM_PRINT(MULTICASTCIPHER --));
            break;
        
    case ATHCFG_WCMD_PARAM_MCASTKEYLEN:
            printf("%s  ",ENUM_PRINT(MULTICASTKEYLEN --));
            break;

    case ATHCFG_WCMD_PARAM_UCASTCIPHERS:
            printf("%s  ",ENUM_PRINT(UNICASTCIPHERS --));
            break;

    case ATHCFG_WCMD_PARAM_UCASTCIPHER:
            printf("%s ",ENUM_PRINT(UNICASTCHIPER --));
            break;

        case ATHCFG_WCMD_PARAM_UCASTKEYLEN:
            printf("%s ",ENUM_PRINT(UCASTCHIPER --));
            break;

    case ATHCFG_WCMD_PARAM_WPA:
            printf("%s  ",ENUM_PRINT(WPA --));
            break;

    case ATHCFG_WCMD_PARAM_ROAMING:
            printf("%s  ",ENUM_PRINT(ROAMING --));
            break;

    case ATHCFG_WCMD_PARAM_PRIVACY:
        printf("%s  ",ENUM_PRINT(PRIVACY --));
            break;

    case ATHCFG_WCMD_PARAM_COUNTERMEASURES:
            printf("%s  ",ENUM_PRINT(COUNTERMEASURES --));
            break;

    case ATHCFG_WCMD_PARAM_DROPUNENCRYPTED:
            printf("%s  ",ENUM_PRINT(DROPUNENCRYPTED --));
            break;

    case ATHCFG_WCMD_PARAM_DRIVER_CAPS:
            printf("%s  ",ENUM_PRINT(DRIVER CAPS --));
            break;

    case ATHCFG_WCMD_PARAM_MACCMD:
            printf("%s  ",ENUM_PRINT(MACCMD --));
            break;

    case ATHCFG_WCMD_PARAM_WMM:
            printf("%s  ",ENUM_PRINT(WMM --));
            break;

    case ATHCFG_WCMD_PARAM_HIDESSID:
            printf("%s  ",ENUM_PRINT(HIDESSID --));
            break;

    case ATHCFG_WCMD_PARAM_APBRIDGE:
            printf("%s  ",ENUM_PRINT(APBRIDGE --));
            break;

    case ATHCFG_WCMD_PARAM_KEYMGTALGS:
            printf("%s  ",ENUM_PRINT(KEYMGTALGS --));
            break;

    case ATHCFG_WCMD_PARAM_RSNCAPS:
            printf("%s  ",ENUM_PRINT(RSNCAPS --));
            break;

    case ATHCFG_WCMD_PARAM_INACT:
            printf("%s  ",ENUM_PRINT(INACT --));
            break;

    case ATHCFG_WCMD_PARAM_INACT_AUTH:
            printf("%s  ",ENUM_PRINT(INACT AUTH --));
            break;

    case ATHCFG_WCMD_PARAM_INACT_INIT:
            printf("%s  ",ENUM_PRINT(INACT INIT --));
            break;

    case ATHCFG_WCMD_PARAM_ABOLT:
            printf("%s  ",ENUM_PRINT(ABOLT --));
            break;

    case ATHCFG_WCMD_PARAM_DTIM_PERIOD:
            printf("%s  ",ENUM_PRINT(DTIM PERIOD --));
            break;

    case ATHCFG_WCMD_PARAM_BEACON_INTERVAL:
            printf("%s  ",ENUM_PRINT(BEACON INTERVAL --));
            break;

    case ATHCFG_WCMD_PARAM_DOTH:
            printf("%s  ",ENUM_PRINT(DOTH --));
            break;

    case ATHCFG_WCMD_PARAM_PWRTARGET:
            printf("%s  ",ENUM_PRINT(PWRTARGET --));
            break;

    case ATHCFG_WCMD_PARAM_GENREASSOC:
            printf("%s  ",ENUM_PRINT(GENREASSOCIATION --));
            break;

    case ATHCFG_WCMD_PARAM_COMPRESSION:
            printf("%s  ",ENUM_PRINT(COMPRESSION --));
            break;

    case ATHCFG_WCMD_PARAM_FF:
            printf("%s  ",ENUM_PRINT(FF --));
            break;

    case ATHCFG_WCMD_PARAM_XR:
            printf("%s  ",ENUM_PRINT(XR --));
            break;

    case ATHCFG_WCMD_PARAM_BURST:
            printf("%s  ",ENUM_PRINT(BURST --));
            break;

    case ATHCFG_WCMD_PARAM_PUREG:
            printf("%s  ",ENUM_PRINT(PUREG --));
            break;

    case ATHCFG_WCMD_PARAM_AR:
            printf("%s  ",ENUM_PRINT(AR --));
            break;

    case ATHCFG_WCMD_PARAM_WDS:
            printf("%s  ",ENUM_PRINT(WDS --));
            break;

    case ATHCFG_WCMD_PARAM_BGSCAN:
            printf("%s  ",ENUM_PRINT(BGSCAN --));
            break;

    case ATHCFG_WCMD_PARAM_BGSCAN_IDLE:
            printf("%s  ",ENUM_PRINT(BGSCAN IDEL --));
            break;

    case ATHCFG_WCMD_PARAM_BGSCAN_INTERVAL:
            printf("%s  ",ENUM_PRINT(BGSCAN INTERVAL --));
            break;

    case ATHCFG_WCMD_PARAM_MCAST_RATE:
            printf("%s  ",ENUM_PRINT(MULTICAST RATE --));
            break;

    case ATHCFG_WCMD_PARAM_COVERAGE_CLASS:
            printf("%s  ",ENUM_PRINT(COVERAGE CLASS --));
            break;

    case ATHCFG_WCMD_PARAM_COUNTRY_IE:
            printf("%s  ",ENUM_PRINT(COUNTRY IE --));
            break;

    case ATHCFG_WCMD_PARAM_SCANVALID:
            printf("%s  ",ENUM_PRINT(SCAN VALID --));
            break;
    
    case ATHCFG_WCMD_PARAM_ROAM_RSSI_11A:
            printf("%s  ",ENUM_PRINT(ROAM RSSI 11A --));
            break;

    case ATHCFG_WCMD_PARAM_ROAM_RSSI_11B:
            printf("%s  ",ENUM_PRINT(ROAM RSSI 11B --));
            break;
    
        case ATHCFG_WCMD_PARAM_ROAM_RSSI_11G:
            printf("%s  ",ENUM_PRINT(ROAM RSSI 11G --));
            break;

        case ATHCFG_WCMD_PARAM_ROAM_RATE_11A:
            printf("%s  ",ENUM_PRINT(ROAM RATE 11A --));
            break;

        case ATHCFG_WCMD_PARAM_ROAM_RATE_11B:
            printf("%s  ",ENUM_PRINT(ROAM RATE 11B --));
            break;

    case ATHCFG_WCMD_PARAM_ROAM_RATE_11G:
            printf("%s  ",ENUM_PRINT(ROAM RATE 11G --));
            break;

    case ATHCFG_WCMD_PARAM_UAPSDINFO:
            printf("%s  ",ENUM_PRINT(UAPSDINFO --));
            break;

    case ATHCFG_WCMD_PARAM_SLEEP:
            printf("%s  ",ENUM_PRINT(SLEEP --));
            break;

    case ATHCFG_WCMD_PARAM_QOSNULL:
            printf("%s  ",ENUM_PRINT(QOSNULL --));
            break;
    
    case ATHCFG_WCMD_PARAM_PSPOLL:
            printf("%s  ",ENUM_PRINT(PSPOLL --));
            break;

    case ATHCFG_WCMD_PARAM_EOSPDROP:
            printf("%s  ",ENUM_PRINT(EOSPDROP --));
            break;

    case ATHCFG_WCMD_PARAM_MARKDFS:
            printf("%s  ",ENUM_PRINT(MARKDFS --));
            break;

    case ATHCFG_WCMD_PARAM_REGCLASS:
            printf("%s  ",ENUM_PRINT(REGCLASS --));
            break;

    case ATHCFG_WCMD_PARAM_CHANBW:
            printf("%s  ",ENUM_PRINT(CHANBW --));
            break;
    
    case ATHCFG_WCMD_PARAM_WMM_AGGRMODE:
            printf("%s  ",ENUM_PRINT(WMM AGGRMODE --));
            break;

    case ATHCFG_WCMD_PARAM_SHORTPREAMBLE:
            printf("%s  ",ENUM_PRINT(SHORT PREAMBLE --));
            break;

    case ATHCFG_WCMD_PARAM_BLOCKDFSCHAN:
            printf("%s  ",ENUM_PRINT(BLOCKDF SCAN --));
            break;

    case ATHCFG_WCMD_PARAM_CWM_MODE:
            printf("%s  ",ENUM_PRINT(CWM MODE --));
            break;

    case ATHCFG_WCMD_PARAM_CWM_EXTOFFSET:
            printf("%s  ",ENUM_PRINT(CWM EXTOFFSET --));
            break;

    case ATHCFG_WCMD_PARAM_CWM_EXTPROTMODE:
            printf("%s  ",ENUM_PRINT(CWM EXTPROTMODE --));
            break;

    case ATHCFG_WCMD_PARAM_CWM_EXTPROTSPACING:
            printf("%s  ",ENUM_PRINT(CWM EXTPROTSPACING --));
            break;

    case ATHCFG_WCMD_PARAM_CWM_ENABLE:
            printf("%s  ",ENUM_PRINT(CWM ENABLE --));
            break;

    case ATHCFG_WCMD_PARAM_CWM_EXTBUSYTHRESHOLD:
            printf("%s  ",ENUM_PRINT(CWM EXBUSY THREHOLD --));
            break;

    case ATHCFG_WCMD_PARAM_CWM_CHWIDTH:
            printf("%s  ",ENUM_PRINT(CWM CHANNEL WIDTH --));
            break;

    case ATHCFG_WCMD_PARAM_SHORT_GI:
            printf("%s  ",ENUM_PRINT(SHORT GI --));
            break;

    case ATHCFG_WCMD_PARAM_FAST_CC:
            printf("%s  ",ENUM_PRINT(FAST CC --));
            break;

    case ATHCFG_WCMD_PARAM_AMPDU:
            printf("%s  ",ENUM_PRINT(AMPDU --));
            break;

    case ATHCFG_WCMD_PARAM_AMPDU_LIMIT:
            printf("%s  ",ENUM_PRINT(AMPDU LIMIT --));
            break;

    case ATHCFG_WCMD_PARAM_AMPDU_DENSITY:
            printf("%s  ",ENUM_PRINT(AMPDU DENSITY --));
            break;

    case ATHCFG_WCMD_PARAM_AMPDU_SUBFRAMES:
            printf("%s  ",ENUM_PRINT(AMPDU SUBFRAMES --));
            break;

    case ATHCFG_WCMD_PARAM_AMSDU:
            printf("%s  ",ENUM_PRINT(AMSDU --));
            break;

    case ATHCFG_WCMD_PARAM_AMSDU_LIMIT:
            printf("%s  ",ENUM_PRINT(AMSDU LIMIT --));
            break;

    case ATHCFG_WCMD_PARAM_COUNTRYCODE:
            printf("%s  ",ENUM_PRINT(COUNTRY CODE --));
            fprintf(stdout, " 0x%02x\n", i_req.d_param.value);  
            break;

    case ATHCFG_WCMD_PARAM_TX_CHAINMASK:
            printf("%s  ",ENUM_PRINT(TX CHAIN MASK --));
            break;

    case ATHCFG_WCMD_PARAM_RX_CHAINMASK:
            printf("%s  ",ENUM_PRINT(RX CHAIN MASK --));
            break;

    case ATHCFG_WCMD_PARAM_RTSCTS_RATECODE:
            printf("%s  ",ENUM_PRINT(RTS CTS RATE CODE --));
            break;

    case ATHCFG_WCMD_PARAM_HT_PROTECTION:
            printf("%s  ",ENUM_PRINT(HT PROTECTION --));
            break;

    case ATHCFG_WCMD_PARAM_RESET_ONCE:
            printf("%s  ",ENUM_PRINT(RESET ONCE --));
            break;

    case ATHCFG_WCMD_PARAM_SETADDBAOPER:
            printf("%s  ",ENUM_PRINT(SET ADDBA OPERATION --));
            break;

    case ATHCFG_WCMD_PARAM_TX_CHAINMASK_LEGACY:
            printf("%s  ",ENUM_PRINT(TX CHAIN MASK LEGACY --));
            break;
    
    case ATHCFG_WCMD_PARAM_11N_RATE:
            printf("%s  ",ENUM_PRINT(11N RATE --));
            break;

    case ATHCFG_WCMD_PARAM_11N_RETRIES:
            printf("%s  ",ENUM_PRINT(RETRIES --));
            break;

    case ATHCFG_WCMD_PARAM_WDS_AUTODETECT:
            printf("%s  ",ENUM_PRINT(WDS AUTODETECT --));
            break;

    case ATHCFG_WCMD_PARAM_RB:
            printf("%s  ",ENUM_PRINT(RB --));
            break;

    case ATHCFG_WCMD_PARAM_RB_DETECT:
            printf("%s  ",ENUM_PRINT(RB DETECT --));
            break;

    case ATHCFG_WCMD_PARAM_RB_SKIP_THRESHOLD:
            printf("%s  ",ENUM_PRINT(RB SKIP THRESHOLD --));
            break;

    case ATHCFG_WCMD_PARAM_RB_TIMEOUT:
            printf("%s  ",ENUM_PRINT(RB TIMEOUT --));
            break;

    case ATHCFG_WCMD_PARAM_NO_HTIE:
            printf("%s  ",ENUM_PRINT(RB HTTE --));
            break;

    case ATHCFG_WCMD_PARAM_MAXSTA:
            printf("%s  ",ENUM_PRINT(MAXIMUM STATION --));
            break;
     }

    fprintf(stdout, " %d\n", i_req.d_param.value);  

    return 0;
}

int
athcfg_param_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_param_t param; 
    memset(&param, 0, sizeof(athcfg_wcmd_param_t));

    assert (nargs == 2);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    /* loop for the variable, user asked to set */
    if(!(strcmp(args[0], "turbo"))) {
        param.param_id = ATHCFG_WCMD_PARAM_TURBO;
        param.value = atoi(args[1]);   
    }
    else if (!(strcmp (args[0], "phy-mode") )) {
        param.param_id = ATHCFG_WCMD_PARAM_MODE;
        param.value = atoi(args[1]);
     }
     else if (!(strcmp (args[0], "auth-mode") )) {
        param.param_id = ATHCFG_WCMD_PARAM_AUTHMODE;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "prot-mode")  )) {
        param.param_id = ATHCFG_WCMD_PARAM_PROTMODE;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "multi-chiper") )) {
        param.param_id = ATHCFG_WCMD_PARAM_MCASTCIPHER;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "multikey-len") )) {
        param.param_id = ATHCFG_WCMD_PARAM_MCASTKEYLEN;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0],  "uni-chipers") )) {
        param.param_id = ATHCFG_WCMD_PARAM_UCASTCIPHERS;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0],  "uni-chiper") )) {
        param.param_id = ATHCFG_WCMD_PARAM_UCASTCIPHER;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "unikey-len") )) {
        param.param_id = ATHCFG_WCMD_PARAM_UCASTKEYLEN;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "wpa-mode") )) {
        param.param_id = ATHCFG_WCMD_PARAM_WPA;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "roaming") )) {
        param.param_id = ATHCFG_WCMD_PARAM_ROAMING;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "privacy") )) {
        param.param_id = ATHCFG_WCMD_PARAM_PRIVACY;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "counter-measures") )) {
        param.param_id = ATHCFG_WCMD_PARAM_COUNTERMEASURES;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "drop-unencry") )) {
        param.param_id = ATHCFG_WCMD_PARAM_DROPUNENCRYPTED;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "driver-caps") )) {
        param.param_id = ATHCFG_WCMD_PARAM_DRIVER_CAPS;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "mac-cmd") )) {
        param.param_id = ATHCFG_WCMD_PARAM_MACCMD;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "wmm-mode") )) {
        param.param_id = ATHCFG_WCMD_PARAM_WMM;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "hide-ssid") )) {
        param.param_id = ATHCFG_WCMD_PARAM_HIDESSID;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0],  "ap-bridge") )) {
        param.param_id = ATHCFG_WCMD_PARAM_APBRIDGE;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "key-algo") )) {
        param.param_id = ATHCFG_WCMD_PARAM_KEYMGTALGS;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "rsn-caps") )) {
        param.param_id = ATHCFG_WCMD_PARAM_RSNCAPS;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args [0], "stainact-timeout") )) {
        param.param_id = ATHCFG_WCMD_PARAM_INACT;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "staauth-inacttimeout") )) {
        param.param_id = ATHCFG_WCMD_PARAM_INACT_AUTH;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "stainit-inacttimeout") )) {
        param.param_id = ATHCFG_WCMD_PARAM_INACT_INIT;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "ath-caps") )) {
        param.param_id = ATHCFG_WCMD_PARAM_ABOLT;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "dtim-period") )) {
        param.param_id = ATHCFG_WCMD_PARAM_DTIM_PERIOD;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "beacon-intv") )) {
        param.param_id = ATHCFG_WCMD_PARAM_BEACON_INTERVAL;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "11h-mode") )) {
        param.param_id = ATHCFG_WCMD_PARAM_DOTH;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "currchan-power") )) {
        param.param_id = ATHCFG_WCMD_PARAM_PWRTARGET;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "gen-reassoc") )) {
        param.param_id = ATHCFG_WCMD_PARAM_GENREASSOC;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "compress") )) {
        param.param_id = ATHCFG_WCMD_PARAM_COMPRESSION;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "fast-frame") )) {
        param.param_id = ATHCFG_WCMD_PARAM_FF;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "xr-supp") )) {
        param.param_id = ATHCFG_WCMD_PARAM_XR;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "burst") )) {
        param.param_id = ATHCFG_WCMD_PARAM_BURST;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "pure-11g") )) {
        param.param_id = ATHCFG_WCMD_PARAM_PUREG;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "ar-supp") )) {
        param.param_id = ATHCFG_WCMD_PARAM_AR;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "wds-enable") )) {
        param.param_id = ATHCFG_WCMD_PARAM_WDS;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "bg-scan") )) {
        param.param_id = ATHCFG_WCMD_PARAM_BGSCAN;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "bgscan-thres") )) {
        param.param_id = ATHCFG_WCMD_PARAM_BGSCAN_IDLE;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "bgscan-intv") )) {
        param.param_id = ATHCFG_WCMD_PARAM_BGSCAN_INTERVAL;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "multi-txrate") )) {
        param.param_id =  ATHCFG_WCMD_PARAM_MCAST_RATE;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "cove-class") )) {
        param.param_id = ATHCFG_WCMD_PARAM_COVERAGE_CLASS;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "countryie-enable") )) {
        param.param_id = ATHCFG_WCMD_PARAM_COUNTRY_IE;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "scancash-thres") )) {
        param.param_id = ATHCFG_WCMD_PARAM_SCANVALID;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "rssithres-11a") )) {
        param.param_id = ATHCFG_WCMD_PARAM_ROAM_RSSI_11A;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "rssithres-11b") )) {
        param.param_id = ATHCFG_WCMD_PARAM_ROAM_RSSI_11B;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "rssithres-11g") )) {
        param.param_id = ATHCFG_WCMD_PARAM_ROAM_RSSI_11G;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "txrate-11a") )) {
        param.param_id = ATHCFG_WCMD_PARAM_ROAM_RATE_11A;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "txrate-11b") )) {
        param.param_id = ATHCFG_WCMD_PARAM_ROAM_RATE_11B;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "txrate-11g") )) {
        param.param_id = ATHCFG_WCMD_PARAM_ROAM_RATE_11G;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "qos-info") )) {
        param.param_id = ATHCFG_WCMD_PARAM_UAPSDINFO;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "sleep") )) {
        param.param_id = ATHCFG_WCMD_PARAM_SLEEP;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "qos-sleep") )) {
        param.param_id = ATHCFG_WCMD_PARAM_QOSNULL;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "pspoll") )) {
        param.param_id = ATHCFG_WCMD_PARAM_PSPOLL;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "eospdrop") )) {
        param.param_id = ATHCFG_WCMD_PARAM_EOSPDROP;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "markdfs-chan") )) {
        param.param_id = ATHCFG_WCMD_PARAM_MARKDFS;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "regclass") )) {
        param.param_id = ATHCFG_WCMD_PARAM_REGCLASS;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "chan-bandwidth") )) {
        param.param_id = ATHCFG_WCMD_PARAM_CHANBW;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "aggr-mode") )) {
        param.param_id = ATHCFG_WCMD_PARAM_WMM_AGGRMODE;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0],  "shortprmble") )) {
        param.param_id = ATHCFG_WCMD_PARAM_SHORTPREAMBLE;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "dfs-chan") )) {
        param.param_id = ATHCFG_WCMD_PARAM_BLOCKDFSCHAN;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "cwm-mode") )) {
        param.param_id = ATHCFG_WCMD_PARAM_CWM_MODE;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "ext-chan") )) {
        param.param_id = ATHCFG_WCMD_PARAM_CWM_EXTOFFSET;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "extchan-protmode") )) {
        param.param_id = ATHCFG_WCMD_PARAM_CWM_EXTPROTMODE;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "extchan-protspace") )) {
        param.param_id = ATHCFG_WCMD_PARAM_CWM_EXTPROTSPACING;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "cwmstate-enable") )) {
        param.param_id = ATHCFG_WCMD_PARAM_CWM_ENABLE;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "extchan-busythres") )) {
        param.param_id = ATHCFG_WCMD_PARAM_CWM_EXTBUSYTHRESHOLD;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "cwmh") )) {
        param.param_id = ATHCFG_WCMD_PARAM_CWM_CHWIDTH;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "half-gi") )) {
        param.param_id = ATHCFG_WCMD_PARAM_SHORT_GI;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "fast-chanchange") )) {
        param.param_id = ATHCFG_WCMD_PARAM_FAST_CC;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "11n-ampdu") )) {
        param.param_id = ATHCFG_WCMD_PARAM_AMPDU;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "ampdu-limit") )) {
        param.param_id = ATHCFG_WCMD_PARAM_AMPDU_LIMIT;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "ampdu-density") )) {
        param.param_id = ATHCFG_WCMD_PARAM_AMPDU_DENSITY;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "ampdu-subfram") )) {
        param.param_id = ATHCFG_WCMD_PARAM_AMPDU_SUBFRAMES;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "amsdu-supp") )) {
        param.param_id = ATHCFG_WCMD_PARAM_AMSDU;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "amsdu-length") )) {
        param.param_id = ATHCFG_WCMD_PARAM_AMSDU_LIMIT;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "country-code") )) {
        param.param_id = ATHCFG_WCMD_PARAM_COUNTRYCODE;
        param.value = strtol(args[1],NULL,10);
    }
    else if (!(strcmp (args[0], "txchan-mask") )) {
        param.param_id = ATHCFG_WCMD_PARAM_TX_CHAINMASK;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "rxchan-mask") )) {
        param.param_id = ATHCFG_WCMD_PARAM_RX_CHAINMASK;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "rts-rate") )) {
        param.param_id = ATHCFG_WCMD_PARAM_RTSCTS_RATECODE;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "ht-prot") )) {
        param.param_id = ATHCFG_WCMD_PARAM_HT_PROTECTION;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "reset-once") )) {
        param.param_id = ATHCFG_WCMD_PARAM_RESET_ONCE;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "set-addba") )) {
        param.param_id = ATHCFG_WCMD_PARAM_SETADDBAOPER;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "txchanmask-legacy") )) {
        param.param_id = ATHCFG_WCMD_PARAM_TX_CHAINMASK_LEGACY;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "11n-rate") )) {
        param.param_id = ATHCFG_WCMD_PARAM_11N_RATE;
        sscanf(args[1],"%x",(unsigned int *)&param.value);
    }
    else if (!(strcmp (args[0], "11n-retries") )) {
        param.param_id = ATHCFG_WCMD_PARAM_11N_RETRIES;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "wds-autodetect") )) {
        param.param_id = ATHCFG_WCMD_PARAM_WDS_AUTODETECT;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "rb-inout") )) {
        param.param_id = ATHCFG_WCMD_PARAM_RB;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "rb-detect") )) {
        param.param_id = ATHCFG_WCMD_PARAM_RB_DETECT;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "rbskip-thres") )) {
        param.param_id = ATHCFG_WCMD_PARAM_RB_SKIP_THRESHOLD;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "rb-timeout") )) {
        param.param_id = ATHCFG_WCMD_PARAM_RB_TIMEOUT;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "htie-stat") )) {
        param.param_id = ATHCFG_WCMD_PARAM_NO_HTIE;
        param.value = atoi(args[1]);
    }
    else if (!(strcmp (args[0], "MAXSTA") )) {
        param.param_id = ATHCFG_WCMD_PARAM_MAXSTA;
        param.value = atoi(args[1]);
    }   
    else if (!(strcmp (args[0], "ether-dongle") )) {
        param.param_id = ATHCFG_WCMD_PARAM_ETHER_DONGLE;
        param.value = atoi(args[1]);
    }

    else {
        printf("!! ERROR !! \n");
        printf("Choose one from the list below -- \n");
        printf("                   -- turbo\n");
        printf("                   -- phy-mode\n");
        printf("                   -- auth-mode\n");
        printf("                   -- prot-mode\n");
        printf("                   -- multi-chiper\n");
        printf("                   -- multikey-len\n");
        printf("                   -- uni-chipers\n");
        printf("                   -- uni-chiper\n");
        printf("                   -- unikey-len\n");
        printf("                   -- wpa-mode\n");
        printf("                   -- roaming\n");
        printf("                   -- privacy\n");
        printf("                   -- counter-measures\n");
        printf("                   -- drop-unencry\n");
        printf("                   -- driver-caps\n");
        printf("                   -- mac-cmd\n");
        printf("                   -- wmm-mode\n");
        printf("                   -- hide-ssid\n");
        printf("                   -- ap-bridge\n");
        printf("                   -- key-algo\n");
        printf("                   -- rsn-caps\n");
        printf("                   -- stainact-timeout\n");
        printf("                   -- staauth-inacttimeout\n");
        printf("                   -- stainit-inacttimeout\n");
        printf("                   -- ath-caps\n");
        printf("                   -- dtim-period\n");
        printf("                   -- beacon-intv\n");
        printf("                   -- 11h-mode\n");
        printf("                   -- currchan-mode\n");
        printf("                   -- gen-reassoc\n");
        printf("                   -- compress\n");
        printf("                   -- fast-frame\n");
        printf("                   -- xr-supp\n");
        printf("                   -- burst\n");
        printf("                   -- pure-11g\n");
        printf("                   -- ar-supp\n");
        printf("                   -- wds-enable\n");
        printf("                   -- bg-scan\n");
        printf("                   -- bgscan-thres\n");
        printf("                   -- bgscan-intv\n");
        printf("                   -- multi-txrate\n");
        printf("                   -- cove-class\n");
        printf("                   -- countryie-enable\n");
        printf("                   -- scancash-thres\n");
        printf("                   -- rssithres-11a\n");
        printf("                   -- rssithres-11b\n");
        printf("                   -- rssithres-11g\n");
        printf("                   -- txrate-11a\n");
        printf("                   -- txrate-11b\n");
        printf("                   -- txrate-11g\n");
        printf("                   -- qos-info\n");
        printf("                   -- sleep\n");
        printf("                   -- qos-sleep\n");
        printf("                   -- pspoll\n");
        printf("                   -- eospdrop\n");
        printf("                   -- markdfs-chan\n");
        printf("                   -- regclass\n");
        printf("                   -- chan-bandwidth\n");
        printf("                   -- aggr-mode\n");
        printf("                   -- shortprmble\n");
        printf("                   -- dfs-chan\n");
        printf("                   -- cwm-mode\n");
        printf("                   -- ext-chan\n");
        printf("                   -- extchan-protmode\n");
        printf("                   -- extchan-protspace\n");
        printf("                   -- cwmstate-enable\n");
        printf("                   -- extchan-busythres\n");
        printf("                   -- cwmh\n");
        printf("                   -- half-gi\n");
        printf("                   -- fast-chanchange\n");
        printf("                   -- 11n-ampdu\n");
        printf("                   -- ampdu-limit\n");
        printf("                   -- ampdu-density\n");
        printf("                   -- ampdu-subfram\n");
        printf("                   -- amsdu-supp\n");
        printf("                   -- amsdu-length\n");
        printf("                   -- country-code\n");
        printf("                   -- txchan-mask\n");
        printf("                   -- rxchan-mask\n");
        printf("                   -- rts-rate\n");
        printf("                   -- ht-prot\n");
        printf("                   -- reset-once\n");
        printf("                   -- set-addba\n");
        printf("                   -- txchanmask-legacy\n");
        printf("                   -- 11n-rate\n");
        printf("                   -- 11n-retries\n");
        printf("                   -- wds-autodetect\n");
        printf("                   -- rb-inout\n");
        printf("                   -- rb-detect\n");
        printf("                   -- rbskip-thres\n");
        printf("                   -- rb-timeout\n");
        printf("                   -- htie-stat\n");
        printf("                   -- MAXSTA\n");
        printf("                   -- ether-dongle\n");

       return  0;
        }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_PARAM;
    i_req.d_param = param;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    switch(i_req.d_param.param_id) {
        case ATHCFG_WCMD_PARAM_TURBO:
            printf("%s  ",ENUM_PRINT(TURBO --));
            break;

        case ATHCFG_WCMD_PARAM_MODE:
            printf("%s  ",ENUM_PRINT(MODE --));
            break;

        case ATHCFG_WCMD_PARAM_AUTHMODE:
            printf("%s  ",ENUM_PRINT(AUTHMODE --));
            break;

        case ATHCFG_WCMD_PARAM_PROTMODE:
            printf("%s  ",ENUM_PRINT(PROTMODE --));
            break;

        case ATHCFG_WCMD_PARAM_MCASTCIPHER:
            printf("%s  ",ENUM_PRINT(MULTICASTCIPHER --));
            break;

        case ATHCFG_WCMD_PARAM_MCASTKEYLEN:
            printf("%s  ",ENUM_PRINT(MULTICASTKEYLEN --));
            break;

        case ATHCFG_WCMD_PARAM_UCASTCIPHERS:
            printf("%s  ",ENUM_PRINT(UNICASTCIPHERS --));
            break;

        case ATHCFG_WCMD_PARAM_UCASTCIPHER:
            printf("%s ",ENUM_PRINT(UNICASTCHIPER --));
            break;

        case ATHCFG_WCMD_PARAM_UCASTKEYLEN:
            printf("%s ",ENUM_PRINT(UCASTCHIPER --));
            break;

        case ATHCFG_WCMD_PARAM_WPA:
            printf("%s  ",ENUM_PRINT(WPA --));
            break;

        case ATHCFG_WCMD_PARAM_ROAMING:
            printf("%s  ",ENUM_PRINT(ROAMING --));
            break;

        case ATHCFG_WCMD_PARAM_PRIVACY:
            printf("%s  ",ENUM_PRINT(PRIVACY --));
            break;

        case ATHCFG_WCMD_PARAM_COUNTERMEASURES:
            printf("%s  ",ENUM_PRINT(COUNTERMEASURES --));
            break;

        case ATHCFG_WCMD_PARAM_DROPUNENCRYPTED:
            printf("%s  ",ENUM_PRINT(DROPUNENCRYPTED --));
            break;

        case ATHCFG_WCMD_PARAM_DRIVER_CAPS:
            printf("%s  ",ENUM_PRINT(DRIVER CAPS --));
            break;

        case ATHCFG_WCMD_PARAM_MACCMD:
            printf("%s  ",ENUM_PRINT(MACCMD --));
            break;

        case ATHCFG_WCMD_PARAM_WMM:
            printf("%s  ",ENUM_PRINT(WMM --));
            break;

        case ATHCFG_WCMD_PARAM_HIDESSID:
            printf("%s  ",ENUM_PRINT(HIDESSID --));
            break;

        case ATHCFG_WCMD_PARAM_APBRIDGE:
            printf("%s  ",ENUM_PRINT(APBRIDGE --));
            break;

        case ATHCFG_WCMD_PARAM_KEYMGTALGS:
            printf("%s  ",ENUM_PRINT(KEYMGTALGS --));
            break;
        

        case ATHCFG_WCMD_PARAM_RSNCAPS:
            printf("%s  ",ENUM_PRINT(RSNCAPS --));
            break;

        case ATHCFG_WCMD_PARAM_INACT:
            printf("%s  ",ENUM_PRINT(INACT --));
            break;

        case ATHCFG_WCMD_PARAM_INACT_AUTH:
            printf("%s  ",ENUM_PRINT(INACT AUTH --));
            break;

        case ATHCFG_WCMD_PARAM_INACT_INIT:
            printf("%s  ",ENUM_PRINT(INACT INIT --));
            break;

        case ATHCFG_WCMD_PARAM_ABOLT:
            printf("%s  ",ENUM_PRINT(ABOLT --));
            break;

        case ATHCFG_WCMD_PARAM_DTIM_PERIOD:
            printf("%s  ",ENUM_PRINT(DTIM PERIOD --));
            break;

        case ATHCFG_WCMD_PARAM_BEACON_INTERVAL:
            printf("%s  ",ENUM_PRINT(BEACON INTERVAL --));
            break;

        case ATHCFG_WCMD_PARAM_DOTH:
            printf("%s  ",ENUM_PRINT(DOTH --));
            break;

        case ATHCFG_WCMD_PARAM_PWRTARGET:
            printf("%s  ",ENUM_PRINT(PWRTARGET --));
            break;

        case ATHCFG_WCMD_PARAM_GENREASSOC:
            printf("%s  ",ENUM_PRINT(GENREASSOCIATION --));
            break;

        case ATHCFG_WCMD_PARAM_COMPRESSION:
            printf("%s  ",ENUM_PRINT(COMPRESSION --));
            break;

        case ATHCFG_WCMD_PARAM_FF:
            printf("%s  ",ENUM_PRINT(FF --));
            break;

        case ATHCFG_WCMD_PARAM_XR:
            printf("%s  ",ENUM_PRINT(XR --));
            break;

        case ATHCFG_WCMD_PARAM_BURST:
            printf("%s  ",ENUM_PRINT(BURST --));
            break;

        case ATHCFG_WCMD_PARAM_PUREG:
            printf("%s  ",ENUM_PRINT(PUREG --));
            break;

        case ATHCFG_WCMD_PARAM_AR:
            printf("%s  ",ENUM_PRINT(AR --));
            break;

        case ATHCFG_WCMD_PARAM_WDS:
            printf("%s  ",ENUM_PRINT(WDS --));
            break;

        case ATHCFG_WCMD_PARAM_BGSCAN:
            printf("%s  ",ENUM_PRINT(BGSCAN --));
            break;

        case ATHCFG_WCMD_PARAM_BGSCAN_IDLE:
            printf("%s  ",ENUM_PRINT(BGSCAN IDEL --));
            break;

        case ATHCFG_WCMD_PARAM_BGSCAN_INTERVAL:
            printf("%s  ",ENUM_PRINT(BGSCAN INTERVAL --));
            break;

        case ATHCFG_WCMD_PARAM_MCAST_RATE:
            printf("%s  ",ENUM_PRINT(MULTICAST RATE --));
            break;

        case ATHCFG_WCMD_PARAM_COVERAGE_CLASS:
            printf("%s  ",ENUM_PRINT(COVERAGE CLASS --));
            break;

        case ATHCFG_WCMD_PARAM_COUNTRY_IE:
            printf("%s  ",ENUM_PRINT(COUNTRY IE --));
            break;

        case ATHCFG_WCMD_PARAM_SCANVALID:
            printf("%s  ",ENUM_PRINT(SCAN VALID --));
            break;

        case ATHCFG_WCMD_PARAM_ROAM_RSSI_11A:
            printf("%s  ",ENUM_PRINT(ROAM RSSI 11A --));
            break;

        case ATHCFG_WCMD_PARAM_ROAM_RSSI_11B:
            printf("%s  ",ENUM_PRINT(ROAM RSSI 11B --));
            break;

        case ATHCFG_WCMD_PARAM_ROAM_RSSI_11G:
            printf("%s  ",ENUM_PRINT(ROAM RSSI 11G --));
            break;

        case ATHCFG_WCMD_PARAM_ROAM_RATE_11A:
            printf("%s  ",ENUM_PRINT(ROAM RATE 11A --));
            break;

        case ATHCFG_WCMD_PARAM_ROAM_RATE_11B:
            printf("%s  ",ENUM_PRINT(ROAM RATE 11B --));
            break;

        case ATHCFG_WCMD_PARAM_ROAM_RATE_11G:
            printf("%s  ",ENUM_PRINT(ROAM RATE 11G --));
            break;

        case ATHCFG_WCMD_PARAM_UAPSDINFO:
            printf("%s  ",ENUM_PRINT(UAPSDINFO --));
            break;

        case ATHCFG_WCMD_PARAM_SLEEP:
            printf("%s  ",ENUM_PRINT(SLEEP --));
            break;

        case ATHCFG_WCMD_PARAM_QOSNULL:
            printf("%s  ",ENUM_PRINT(QOSNULL --));
            break;

        case ATHCFG_WCMD_PARAM_PSPOLL:
            printf("%s  ",ENUM_PRINT(PSPOLL --));
            break;

        case ATHCFG_WCMD_PARAM_EOSPDROP:
            printf("%s  ",ENUM_PRINT(EOSPDROP --));
            break;

        case ATHCFG_WCMD_PARAM_MARKDFS:
            printf("%s  ",ENUM_PRINT(MARKDFS --));
            break;

        case ATHCFG_WCMD_PARAM_REGCLASS:
            printf("%s  ",ENUM_PRINT(REGCLASS --));
            break;

        case ATHCFG_WCMD_PARAM_CHANBW:
            printf("%s  ",ENUM_PRINT(CHANBW --));
            break;

        case ATHCFG_WCMD_PARAM_WMM_AGGRMODE:
            printf("%s  ",ENUM_PRINT(WMM AGGRMODE --));
            break;

        case ATHCFG_WCMD_PARAM_SHORTPREAMBLE:
            printf("%s  ",ENUM_PRINT(SHORT PREAMBLE --));
            break;

        case ATHCFG_WCMD_PARAM_BLOCKDFSCHAN:
            printf("%s  ",ENUM_PRINT(BLOCKDF SCAN --));
            break;

        case ATHCFG_WCMD_PARAM_CWM_MODE:
            printf("%s  ",ENUM_PRINT(CWM MODE --));
            break;

        case ATHCFG_WCMD_PARAM_CWM_EXTOFFSET:
            printf("%s  ",ENUM_PRINT(CWM EXTOFFSET --));
            break;

        case ATHCFG_WCMD_PARAM_CWM_EXTPROTMODE:
            printf("%s  ",ENUM_PRINT(CWM EXTPROTMODE --));
            break;

        case ATHCFG_WCMD_PARAM_CWM_EXTPROTSPACING:
            printf("%s  ",ENUM_PRINT(CWM EXTPROTSPACING --));
            break;

        case ATHCFG_WCMD_PARAM_CWM_ENABLE:
            printf("%s  ",ENUM_PRINT(CWM ENABLE --));
            break;

        case ATHCFG_WCMD_PARAM_CWM_EXTBUSYTHRESHOLD:
            printf("%s  ",ENUM_PRINT(CWM EXBUSY THREHOLD --));
            break;

        case ATHCFG_WCMD_PARAM_CWM_CHWIDTH:
            printf("%s  ",ENUM_PRINT(CWM CHANNEL WIDTH --));
            break;

        case ATHCFG_WCMD_PARAM_SHORT_GI:
            printf("%s  ",ENUM_PRINT(SHORT GI --));
            break;

        case ATHCFG_WCMD_PARAM_FAST_CC:
            printf("%s  ",ENUM_PRINT(FAST CC --));
            break;

        case ATHCFG_WCMD_PARAM_AMPDU:
            printf("%s  ",ENUM_PRINT(AMPDU --));
            break;

        case ATHCFG_WCMD_PARAM_AMPDU_LIMIT:
            printf("%s  ",ENUM_PRINT(AMPDU LIMIT --));
            break;

        case ATHCFG_WCMD_PARAM_AMPDU_DENSITY:
            printf("%s  ",ENUM_PRINT(AMPDU DENSITY --));
            break;

        case ATHCFG_WCMD_PARAM_AMPDU_SUBFRAMES:
            printf("%s  ",ENUM_PRINT(AMPDU SUBFRAMES --));
            break;

        case ATHCFG_WCMD_PARAM_AMSDU:
            printf("%s  ",ENUM_PRINT(AMSDU --));
            break;

        case ATHCFG_WCMD_PARAM_AMSDU_LIMIT:
            printf("%s  ",ENUM_PRINT(AMSDU LIMIT --));
            break;

        case ATHCFG_WCMD_PARAM_COUNTRYCODE:
            printf("%s  ",ENUM_PRINT(COUNTRY CODE --));
            fprintf(stdout, " 0x%02x\n", i_req.d_param.value);  
            return 0;
            break;

        case ATHCFG_WCMD_PARAM_TX_CHAINMASK:
            printf("%s  ",ENUM_PRINT(TX CHAIN MASK --));
            break;

        case ATHCFG_WCMD_PARAM_RX_CHAINMASK:
            printf("%s  ",ENUM_PRINT(RX CHAIN MASK --));
            break;

        case ATHCFG_WCMD_PARAM_RTSCTS_RATECODE:
            printf("%s  ",ENUM_PRINT(RTS CTS RATE CODE --));
            break;

        case ATHCFG_WCMD_PARAM_HT_PROTECTION:
            printf("%s  ",ENUM_PRINT(HT PROTECTION --));
            break;

        case ATHCFG_WCMD_PARAM_RESET_ONCE:
            printf("%s  ",ENUM_PRINT(RESET ONCE --));
            break;

        case ATHCFG_WCMD_PARAM_SETADDBAOPER:
            printf("%s  ",ENUM_PRINT(SET ADDBA OPERATION --));
            break;

        case ATHCFG_WCMD_PARAM_TX_CHAINMASK_LEGACY:
            printf("%s  ",ENUM_PRINT(TX CHAIN MASK LEGACY --));
            break;

        case ATHCFG_WCMD_PARAM_11N_RATE:
            printf("%s  ",ENUM_PRINT(11N RATE --));
            break;

        case ATHCFG_WCMD_PARAM_11N_RETRIES:
            printf("%s  ",ENUM_PRINT(RETRIES --));
            break;

        case ATHCFG_WCMD_PARAM_WDS_AUTODETECT:
            printf("%s  ",ENUM_PRINT(WDS AUTODETECT --));
            break;

        case ATHCFG_WCMD_PARAM_RB:
            printf("%s  ",ENUM_PRINT(RB --));
            break;

        case ATHCFG_WCMD_PARAM_RB_DETECT:
            printf("%s  ",ENUM_PRINT(RB DETECT --));
            break;

        case ATHCFG_WCMD_PARAM_RB_SKIP_THRESHOLD:
            printf("%s  ",ENUM_PRINT(RB SKIP THRESHOLD --));
            break;

        case ATHCFG_WCMD_PARAM_RB_TIMEOUT:
            printf("%s  ",ENUM_PRINT(RB TIMEOUT --));
            break;

        case ATHCFG_WCMD_PARAM_NO_HTIE:
            printf("%s  ",ENUM_PRINT(RB HTTE --));
            break;

        case ATHCFG_WCMD_PARAM_MAXSTA:
            printf("%s  ",ENUM_PRINT(MAXIMUM STATION --));
            break;
        case ATHCFG_WCMD_PARAM_ETHER_DONGLE:
            printf("%s  ",ENUM_PRINT(Station dongle mode  --));

            break;
     }

    fprintf(stdout, " %d\n", i_req.d_param.value);  

    return 0;
}
 
/**
 * @brief Handlers for Get OPT-IE.
 */
int
athcfg_optie_get(char *ifrn_name, int nargs, char *args[])
{   
    athcfg_wcmd_t i_req; ; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_optie_t optie; 
    memset(&optie, 0, sizeof(athcfg_wcmd_optie_t));
    args[0] = NULL;

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    optie.len = ATHCFG_WCMD_IE_MAXLEN;
    i_req.type = ATHCFG_WCMD_GET_OPT_IE;
    i_req.d_optie = optie;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "OPT-IE -- %s\n", i_req.d_optie.data);

    return 0;
}

int
athcfg_optie_set(char *ifrn_name, int nargs, char *args[])
{   
    athcfg_wcmd_t i_req;  
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_optie_t optie; 
    memset(&optie, 0, sizeof(athcfg_wcmd_optie_t));

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    strcpy((char *)optie.data, args[0]);

    optie.len = strlen(args[0]);
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_OPT_IE;
    i_req.d_optie = optie;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "OPT-IE -- %s\n", i_req.d_optie.data);

    return 0;
}

/**
 * @brief Handlers for Get APP-IE.
 */
int
athcfg_appie_get(char *ifrn_name, int nargs, char *args[])
{   

    int count = 0;

    athcfg_wcmd_t i_req;  
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_appie_t appie; 
    memset(&appie, 0, sizeof(athcfg_wcmd_appie_t));

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    if( !(strcmp(args[0],"beacon")) )
        appie.frmtype = ATHCFG_WCMD_APPIE_FRAME_BEACON;

    else if( !(strcmp(args[0], "probe-req")) )
        appie.frmtype = ATHCFG_WCMD_APPIE_FRAME_PROBE_REQ;

    else if( !(strcmp(args[0], "probe-resp")) )
        appie.frmtype = ATHCFG_WCMD_APPIE_FRAME_PROBE_RESP;

    else if( !(strcmp(args[0], "assoc-req")) )
        appie.frmtype = ATHCFG_WCMD_APPIE_FRAME_ASSOC_REQ;

    else if( !(strcmp(args[0], "assoc-resp")) )
        appie.frmtype = ATHCFG_WCMD_APPIE_FRAME_ASSOC_RESP;
    
    else if( !(strcmp(args[0], "frame-num")) )
        appie.frmtype = ATHCFG_WCMD_APPIE_NUM_OF_FRAME;

    else {
        printf("!! ERROR !! - Unexceptable parameter\n");
        printf("Please choose from the list below -- \n");
        printf("                  -- beacon\n");
        printf("                  -- probe-req\n");
        printf("                  -- probe-resp\n");
        printf("                  -- assoc-req\n");
        printf("                  -- assoc-resp\n");
        printf("                  -- fram-num\n");
    }   
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_APP_IE_BUF;
    i_req.d_appie.len = ATHCFG_WCMD_IE_MAXLEN;
    i_req.d_appie = appie;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    printf("APP-IE -- ");
    for(count = 0; count < i_req.d_appie.len; count++)
        printf("%x ",i_req.d_appie.data[count]);
    printf("\n");    

    return 0;
}

int
athcfg_appie_set(char *ifrn_name, int nargs, char *args[])
{   
    athcfg_wcmd_t i_req;  
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_appie_t appie; 
    memset(&appie, 0, sizeof(athcfg_wcmd_appie_t));
    assert (nargs > 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
   
    if( !(strcmp(args[0],"beacon")) )
        appie.frmtype = ATHCFG_WCMD_APPIE_FRAME_BEACON;

    else if( !(strcmp(args[0], "probe-req")) )
        appie.frmtype = ATHCFG_WCMD_APPIE_FRAME_PROBE_REQ;

    else if( !(strcmp(args[0], "probe-resp")) )
        appie.frmtype = ATHCFG_WCMD_APPIE_FRAME_PROBE_RESP;

    else if( !(strcmp(args[0], "assoc-req")) )
        appie.frmtype = ATHCFG_WCMD_APPIE_FRAME_ASSOC_REQ;

    else if( !(strcmp(args[0], "assoc-resp")) )
        appie.frmtype = ATHCFG_WCMD_APPIE_FRAME_ASSOC_RESP;
    
    else if( !(strcmp(args[0], "frame-num")) )
        appie.frmtype = ATHCFG_WCMD_APPIE_NUM_OF_FRAME;

    else {
        printf("!! ERROR !! - Unexceptable parameter\n");
        printf("Please choose from the list below -- \n");
        printf("                  -- beacon\n");
        printf("                  -- probe-req\n");
        printf("                  -- probe-resp\n");
        printf("                  -- assoc-req\n");
        printf("                  -- assoc-resp\n");
        printf("                  -- fram-num\n");
    }   

    strcpy((char *)appie.data, args[1]);
    appie.len = strlen(args[1]);
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_APP_IE_BUF;
    i_req.d_appie = appie;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    return 0;
}

/**
 * @brief Handlers for Get KEY-INFO.
 */
int
athcfg_key_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_keyinfo_t key; 
    memset(&key, 0, sizeof(athcfg_wcmd_keyinfo_t));

    assert (nargs == 2);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    /* Get the Index */
    if(3 < atoi(args[0]) || atoi(args[0]) < 0) {
        printf("!! Error !!\n");
        printf("Enter a valid Index value--  0 | 1 | 2 | 3\n");
        return 0;
    }
    else
        key.ik_keyix = atoi(args[0]);

    if( (strlen(args[1]) ==  ATHCFG_WCMD_MAC_STR_LEN) ) {
        sscanf(args[0],"%x:%x:%x:%x:%x:%x",(unsigned int *)&(key.ik_macaddr[0]),\
            (unsigned int *)&(key.ik_macaddr[1]),\
            (unsigned int *)&(key.ik_macaddr[2]),\
            (unsigned int *)&(key.ik_macaddr[3]),\
            (unsigned int *)&(key.ik_macaddr[4]),\
            (unsigned int *)&(key.ik_macaddr[5]) );
    }   
    else {
        printf("!! ERROR !!\n");
        printf("Please enter Mac in format of -- 00:11:22:33:44:55\n");
        ABORT_ARG_SIZE(ifrn_name, "KEY", ATHCFG_WCMD_SET_KEY);
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_KEY;
    i_req.d_key = key;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "Key -- %s\n",(char *) i_req.d_key.ik_keydata);
    return 0;
}

int
athcfg_key_set(char *ifrn_name, int nargs, char *args[])
{
    int i = 0;  
    int j = 0;
    char key_data[40];
    int tmp[14];
   
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_keyinfo_t key; 
    memset(&key, 0, sizeof(athcfg_wcmd_keyinfo_t));

    assert (nargs >= 1);
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));

    /* Set the privacy bit before setting the Key */
    /* If driver is not handling, comment if driver is taking care */
    /* Run the set Param command to set the privacy bit */
#if 0
    athcfg_wcmd_param_t param;
    memset(&param, 0, sizeof(athcfg_wcmd_param_t));

    param.value = 1;
   
    param.param_id = ATHCFG_WCMD_PARAM_PRIVACY; 
    i_req.type = ATHCFG_WCMD_SET_PARAM;
    i_req.d_param = param;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
         return EIO;

#endif

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    /* Check if only the index need to be set, that is for the default key set */
    if(nargs == 1) {
       if (!strcmp(args[0], "enc-off")) {
           printf("turn off enc\n");
           key.ik_flags |= ATHCFG_WCMD_ENC_DISABLED;
       }
       else if(4 < atoi(args[0]) || atoi(args[0]) < 0) {
           printf("!! Error !!\n");
           printf("Enter a valid Index value--  1 | 2 | 3 | 4\n");
           return 0;
       }
       else
           key.ik_flags |= atoi(args[0]);
        
           i_req.type = ATHCFG_WCMD_SET_ENC;
           i_req.d_key = key;
           printf("-- You should configure the wep key for the required INDEX\
                         before making it as a default key\n");

           if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
               return EIO;
    }
    /* Configure the wep key */    
    else{        
    /* Get the Index */
    if(4 < atoi(args[0]) || atoi(args[0]) < 0) {
        printf("!! Error !!\n");
        printf("Enter a valid Index value--  1 | 2 | 3 | 4\n");
        return 0;
    }
    else
        key.ik_flags |= atoi(args[0]);

    /* Get the Key type */
    if(!(strcmp(args[1], "wep")) ) {
        key.ik_type = ATHCFG_WCMD_CIPHERMODE_WEP;
    }
    else if(!(strcmp(args[1], "none")) ) {
        key.ik_flags = 0;      
        key.ik_type = ATHCFG_WCMD_CIPHERMODE_NONE;    
    }
    
#if 0
    else if(!(strcmp(args[1], "tkip")) ) {
        key.ik_flags = ATHCFG_WCMD_VAPKEY_XMIT | ATHCFG_WCMD_VAPKEY_RECV;       
        key.ik_type = ATHCFG_WCMD_CIPHERMODE_TKIP;
    }   

    else if(!(strcmp(args[1], "aes-ocb")) ) {
        key.ik_flags = ATHCFG_WCMD_VAPKEY_XMIT | ATHCFG_WCMD_VAPKEY_RECV;       
        key.ik_type = ATHCFG_WCMD_CIPHERMODE_AES_OCB;
    }   

    else if(!(strcmp(args[1], "aes-ccm")) ) {
        key.ik_flags = ATHCFG_WCMD_VAPKEY_XMIT | ATHCFG_WCMD_VAPKEY_RECV;       
        key.ik_type = ATHCFG_WCMD_CIPHERMODE_AES_CCM;       
    }   

    else if(!(strcmp(args[1], "ckip")) ) {
        key.ik_flags = ATHCFG_WCMD_VAPKEY_XMIT | ATHCFG_WCMD_VAPKEY_RECV;       
        key.ik_type = ATHCFG_WCMD_CIPHERMODE_CKIP;
    }

#endif
    else {
        printf("!! ERROR !!\n");
        printf("Please enter a valid key type --\n");
        printf("                  -- wep\n");
        printf("                  -- none\n");
        return 0;

   if(!(strcmp(args[2], "open")) ) 
        key.ik_flags |= ATHCFG_WCMD_ENC_OPEN;
   else if(!(strcmp(args[2], "restrict")))        
        key.ik_flags |= ATHCFG_WCMD_ENC_RESTRICTED;
       
#if 0
        printf("                  -- tkip\n");
        printf("                  -- aes-ocb\n");   
        printf("                  -- aes-ccm\n");
        printf("                  -- ckip\n");
#endif
    }   
    /* Get the Key */
    /* key in string */
    if( (strlen(args[3]) == 5) ) {
        key.ik_keylen = 5;
        for( i = 0; i < (strlen(args[3])); i++)
            key.ik_keydata[i] = args[3][i];         
    }

    else if( (strlen(args[3]) == 13) ) {
        key.ik_keylen = 13;
        for( i = 0; i < (strlen(args[3])); i++)
            key.ik_keydata[i] = args[3][i];         
    }   

    /* key in Hex */
    /* create a temp char array (key_data[]) for getting the key */
    else if(strlen(args[3]) == 10) {
        key.ik_keylen = 5;
        for(i = 0; i < 14; i++) {
           key_data[i+2] = ':';
            i = i+2;
        }  
        key_data[14] = '\0';
    }

    else if(strlen(args[3]) == 26) {    
        key.ik_keylen = 13; 
        for(i = 0; i < 38; i++) {
            key_data[i+2] = ':';
            i = i+2;
        }
        key_data[38] = '\0';
    }   

    else { 
        printf("!! ERROR !!\n");
        printf("Please enter a valid key Either in form of string or in Hexadecimal  --\n");
        printf("                         key in Hex    10|26     --1234567890 || 0102030405060708090a0b0c0d\n");
        printf("                         Key in string  5|13     --""vwxyz""  || "".1.4.6.8.0.23""\n");         
        return 0;
    }
        
    /* copy the key in the temp char array */ 
    for(i = 0; i < strlen(args[3]); i++) {
        if(key_data[j] == ':')
        j = j+1;
        key_data[j] = args[3][i];
        j = j+1;
    }

    /* get the HEX value of the key in the final array to pass the driver */
    if(strlen(args[3]) == 10) { 
        sscanf(key_data, "%x:%x:%x:%x:%x",(unsigned int *)&(tmp[0]), (unsigned int *)&(tmp[1]), (unsigned int *)&(tmp[2]),
                                          (unsigned int *)&(tmp[3]), (unsigned int *)&(tmp[4]));

        for(i=0;i<5;i++)
            key.ik_keydata[i] = tmp[i];
    }
    if (strlen(args[3]) == 26) {
        sscanf(key_data, "%x:%x:%x:%x:%x:%x:%x:%x:%x:%x:%x:%x:%x",(unsigned int *)&tmp[0], (unsigned int *)&tmp[1],\
                     (unsigned int *)&tmp[2], (unsigned int *)&tmp[3], (unsigned int *)&tmp[4],\
                     (unsigned int *)&tmp[5], (unsigned int *)&tmp[6], (unsigned int *)&tmp[7],\
                     (unsigned int *)&tmp[8], (unsigned int *)&tmp[9], (unsigned int *)&tmp[10],\
                     (unsigned int *)&tmp[11], (unsigned int *)&tmp[12]);

        for(i=0;i<13;i++)
            key.ik_keydata[i] = tmp[i];
    }
   if( (strlen(args[4]) ==  ATHCFG_WCMD_MAC_STR_LEN) ) {
    sscanf(args[4],"%x:%x:%x:%x:%x:%x",(unsigned int *)&(key.ik_macaddr[0]),\
            (unsigned int *)&(key.ik_macaddr[1]),\
            (unsigned int *)&(key.ik_macaddr[2]),\
            (unsigned int *)&(key.ik_macaddr[3]),\
            (unsigned int *)&(key.ik_macaddr[4]),\
            (unsigned int *)&(key.ik_macaddr[5]) );
    }   
    else {
        printf("!! ERROR !!\n");
        printf("Please enter Mac in format of -- 00:11:22:33:44:55\n");
        ABORT_ARG_SIZE(ifrn_name, "KEY", ATHCFG_WCMD_SET_KEY);
    }

    i_req.type = ATHCFG_WCMD_SET_ENC;
    i_req.d_key = key;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;
    printf("-- You can now choose the INDEX number to make it as a default key\n");
    
    for(i = 0; i < key.ik_keylen; i++)
    fprintf(stdout, "Key[%d] -- %c  %x\n",i,i_req.d_key.ik_keydata[i],  i_req.d_key.ik_keydata[i]);
    }
    return 0;
}

/**
 * @brief Handlers for Get/Set SCAN-INFO. 
 */
int
athcfg_scan_get(char *ifrn_name, int nargs, char *args[])
{
    int count = 0;
    int mac_count = 0;
    int rate_count = 0;
    int ie_count = 0;
    athcfg_wcmd_t i_req = {{0}}; 
    athcfg_wcmd_scan_t scan = {{{0}}};
    a_uint8_t offset = 0;
    athcfg_wcmd_scan_result_t  *result;
 
    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_SCAN;
    scan.more = 0;
    scan.offset = 0;
    i_req.d_scan = &scan;

    printf("Scan Result for interface [%s] is as below --\n",ifrn_name);
    do
    {  
        if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
            return EIO;
        
        offset += i_req.d_scan->len;
        scan.offset = offset;
        if (i_req.d_scan->len) {   
            for(count = 0; count < i_req.d_scan->len ; count++) {
                result = &i_req.d_scan->result[count];
                printf("%s \n       ",i_req.d_scan->result[count].isr_ssid);
                printf("     BSSID "); 
                for(mac_count= 0; mac_count < 6; mac_count++){
                    fprintf(stdout, "%x", i_req.d_scan->result[count]
                                      .isr_bssid[mac_count]);
                    if(mac_count <5)
                        printf(":");
                }
                printf("  ");
                printf("Freq %d  ",i_req.d_scan->result[count].isr_freq);
                printf("Bea-Intv %d\n",i_req.d_scan->result[count].isr_intval);
                printf("            Flags %d  ",i_req.d_scan->result[count]
                                                            .isr_flags);
                printf("Noise %d  ",i_req.d_scan->result[count].isr_noise);
                printf("Rssi %d  ",i_req.d_scan->result[count].isr_rssi);
                printf("NRate %d  ",i_req.d_scan->result[count].isr_nrates);
                printf("ERP %d\n",i_req.d_scan->result[count].isr_erp);
                printf("            Rate ");

                for(rate_count = 0; rate_count < 
                     (strlen(i_req.d_scan->result[count].isr_rates)); 
                     rate_count++)
                    printf("%d  ",result->isr_rates[rate_count]); 

                printf("Cap-Info %d\n",result->isr_capinfo);

                if(result->isr_wpa_ie.len ) {
                    printf("            WPA-IE ");
                    for(ie_count = 0; ie_count < result->isr_wpa_ie.len; 
                        ie_count++)
                        printf("%x ", result->isr_wpa_ie.data[ie_count]);

                    printf("\n");                                              
                }                               
                if(result->isr_wme_ie.len ) { 
                    printf("            WME-IE ");
                    for(ie_count = 0; ie_count < result->isr_wme_ie.len; 
                        ie_count++)
                        printf("%x ",result->isr_wme_ie.data[ie_count]);
                    printf("\n");
                }                               
                            
                if(result->isr_ath_ie.len )  {
                    printf("            ATH-IE ");
                    for(ie_count = 0; ie_count < result->isr_ath_ie.len; 
                        ie_count++)
                        printf("%x ",result->isr_ath_ie.data[ie_count]);
                    printf("\n");
                }
                if(result->isr_rsn_ie.len )  {
                    printf("            RSN-IE ");
                    for(ie_count = 0; ie_count < result->isr_rsn_ie.len; 
                        ie_count++)
                        printf("%x ",result->isr_rsn_ie.data[ie_count]);
                    printf("\n");
                }     
                          
               if(result->isr_wps_ie.len )  {
                    printf("            WPS-IE ");
                    for(ie_count = 0; ie_count < result->isr_wps_ie.len; 
                        ie_count++)
                        printf("%x ",result->isr_wps_ie.data[ie_count]);
                    printf("\n");
                }        

               if(result->isr_htcap_ie.len )  {
                   printf("            HTCAP-IE ");
                   for(ie_count = 0; ie_count < result->isr_htcap_ie.len; 
                           ie_count++)
                       printf("%x ",result->isr_htcap_ie.data[ie_count]);
                    printf("\n");

                    printf("            HTCAP-MCSSET "); 
                   for(ie_count = 0; ie_count < ATHCFG_WCMD_MAX_HT_MCSSET; ie_count ++)
                       printf("%x ",result->isr_htcap_mcsset[ie_count]);
                    printf("\n");
               }        
               if(result->isr_htinfo_ie.len )  {
                   printf("            HTINFO-IE ");
                   for(ie_count = 0; ie_count < result->isr_htinfo_ie.len; 
                           ie_count++)
                       printf("%x ",result->isr_htinfo_ie.data[ie_count]);
                    printf("\n");
               }        
               printf("\n");
            }                            
        }
        else 
            printf("There are no AP in the scan list \n");      
    } while(i_req.d_scan->more);

    return 0;
}

int
athcfg_scan_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    assert (nargs == 0);
    args[0] = NULL;
 
    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_SCAN;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;
#if 0    
    printf("Scan Result for interface [%s] is as below --\n",ifrn_name);
    fprintf(stdout, "Scan Len --    %d\n", i_req.d_scan.arg->isr_len);
    fprintf(stdout, "Scan Frequency --  %d\n", i_req.d_scan.arg->isr_freq);
    fprintf(stdout, "Scan Flags --  %d\n", i_req.d_scan.arg->isr_flags);
    fprintf(stdout, "Scan Noise --  %d\n", i_req.d_scan.arg->isr_noise);
    fprintf(stdout, "Scan Rssi --   %d\n", i_req.d_scan.arg->isr_rssi);
    fprintf(stdout, "Scan Interval --   %d\n", i_req.d_scan.arg->isr_intval);
    fprintf(stdout, "Scan Cap-Info  %d\n", i_req.d_scan.arg->isr_capinfo);
    fprintf(stdout, "Scan ERP --    %d\n", i_req.d_scan.arg->isr_erp);
    fprintf(stdout, "Scan BSSID --  %s\n", i_req.d_scan.arg->isr_bssid);
    fprintf(stdout, "Scan NRate     %d\n", i_req.d_scan.arg->isr_nrates);
    fprintf(stdout, "Scan Rate --   %s\n", i_req.d_scan.arg->isr_rates);
    fprintf(stdout, "Scan SSID-Len --   %d\n", i_req.d_scan.arg->isr_ssid_len);
    fprintf(stdout, "Scan IE-Len -- %d\n", i_req.d_scan.arg->isr_ie_len);
    fprintf(stdout, "Scan Pad --    %s\n", i_req.d_scan.arg->isr_pad);
#endif
    return 0;
}


/**
 * @brief Handlers for Get/Set MODE.
 */
int
athcfg_mode_get(char *ifrn_name, int nargs, char *args[])
{
    int param_check = 0;
    athcfg_wcmd_t i_req; 
    memset(&i_req, 0, sizeof(athcfg_wcmd_t));
    /* Get the htie-stat to check the mode either 11A/B/G */
    athcfg_wcmd_param_t param; 
    memset(&param, 0, sizeof(athcfg_wcmd_param_t));

    param.param_id = ATHCFG_WCMD_PARAM_NO_HTIE;
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_PARAM;
    i_req.d_param = param;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;
    param_check = i_req.d_param.value;

    memset(&i_req, 0, sizeof(athcfg_wcmd_t));
    athcfg_wcmd_phymode_t  phymode;
    memset(&phymode, 0, sizeof(athcfg_wcmd_phymode_t));
    args[0] = NULL;

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_MODE;
    i_req.d_phymode = phymode;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;
    
    printf("MODE -- ");

    if(i_req.d_phymode == 1)
        printf("11A\n");
    else if (i_req.d_phymode == 2)
        printf("11B\n");
    else if (i_req.d_phymode == 3)
        printf("11G\n");
    else if (i_req.d_phymode == 10)
        printf("11NA HT20\n");
    else if (i_req.d_phymode == 11)
        printf("11NA HT40\n");
    else if (i_req.d_phymode == 12)
        printf("11NG HT20\n");
    else if (i_req.d_phymode == 13)
        printf("11NG HT40\n");
    else { 
        printf("Invalid return from driver\n");     
    }

    return 0;
}

int
athcfg_testmode_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_testmode_t testmode; 
    memset(&testmode, 0, sizeof(athcfg_wcmd_testmode_t));

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    
    if (!strcmp(args[0], "bssid")) {
        testmode.mode = 0;
        
        if((strlen(args[1]) == ATHCFG_WCMD_MAC_STR_LEN)) {
            unsigned int addr[ATHCFG_WCMD_ADDR_LEN];
            int i;
            
            sscanf(args[1],"%x:%x:%x:%x:%x:%x",(unsigned int *)&addr[0],\
            (unsigned int *)&addr[1],\
            (unsigned int *)&addr[2],\
            (unsigned int *)&addr[3],\
            (unsigned int *)&addr[4],\
            (unsigned int *)&addr[5] );

            for(i=0; i<ATHCFG_WCMD_ADDR_LEN; i++) {
                testmode.bssid[i] = addr[i];
            }
        }
    }
    else if (!strcmp(args[0], "freq")) {
        testmode.mode = 1;
        testmode.freq = atoi(args[1]); 
    }
    else if (!strcmp(args[0], "rxstart")) {
        testmode.mode = 2;
    }
    else if (!strcmp(args[0], "rxstop")) {
        testmode.mode = 3;
    }
    else {
        printf("!! ERROR !! \n");
        printf("Choose one from the list below -->\n");
        printf("                 bssid [BSSID]\n");
        printf("                 freq [FREQ]\n");
        printf("                 rxstart\n");
        printf("                 rxstop\n");
        return 0;
    }
    
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_TESTMODE;
    i_req.data.testmode = testmode;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    return 0;
}

/**
 * @brief Handlers for Get/Set MODE.
 */
int
athcfg_testmode_get(char *ifrn_name, int nargs, char *args[])
{
    int param_check = 0;
    athcfg_wcmd_t i_req; 
    memset(&i_req, 0, sizeof(athcfg_wcmd_t));

    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_TESTMODE;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    printf("rssi=%d, %d, %d, %d\n", i_req.data.testmode.rssi_combined,
            i_req.data.testmode.rssi0, i_req.data.testmode.rssi1,
            i_req.data.testmode.rssi2);

    return 0;
}

int
athcfg_mode_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_phymode_t mode ; 
    memset(&mode, 0, sizeof(athcfg_wcmd_phymode_t));

    assert (nargs == 1);
    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    
    if ((!strcmp(args[0], "AUTO")) || atoi(args[0]) == 0)
        mode = 0 ;
    else if ((!strcmp(args[0], "11A")) || atoi(args[0]) == 1){
        printf("!! For setting the AP in 11A only mode --\
                                       First set the interface in Legacy mode, that is -\
                               athcfg ath0 param set htie-stat 1\
                                               Then set the mode to 11NA.\n");
        return 0;
    }
    else if ((!strcmp(args[0], "11B")) || atoi(args[0]) == 2){
        printf("!! For setting the AP in 11B only mode --\
                                       First set the interface in Legacy mode, that is -\
                               athcfg ath0 param set htie-stat 1\
                                               Then set the mode to 11NG.\n");
        return 0;
       
    }                        
    else if ((!strcmp(args[0], "11G")) || atoi(args[0]) == 3){
        printf("!! For setting the AP in 11G only mode --\
                                       First set the interface in Legacy mode, that is -\
                               athcfg ath0 param set htie-stat 1\
                                               Then set the mode to 11NG.\n");
        return 0;
    }        
    else if ((!strcmp(args[0], "FH")) || atoi(args[0]) == 4)
        mode = 4;
    else if ((!strcmp(args[0], "11NA")) || atoi(args[0]) == 7)
        mode = 7;
    else if ((!strcmp(args[0], "11NG")) || atoi(args[0]) == 8)
        mode = 8;
    else if ((!strcmp(args[0], "11AST")) || atoi(args[0]) == 9)
        mode = 9;
    else {
        printf("!! ERROR !! \n");
        printf("Choose one from the list below -->\n");
//      printf("                 11AST || 5 \n");
//      printf("                 AUTO  || 0 \n");
//      printf("                 11A   || 1 \n");
//      printf("                 11B   || 2 \n");
//      printf("                 11G   || 3 \n");
        printf("                 11NA  || 6 \n");
        printf("                 11NG  || 7 \n");
//      printf("                 FH    || 4 \n");
        return 0;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_MODE;
    i_req.d_phymode = mode;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    return 0;
}

/**
 * @brief Handlers for Get/Set CHANNELS.
 */
int
athcfg_chanlist_get(char *ifrn_name, int nargs, char *args[])
{
    int i = 0;
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_chanlist_t chanlist ;
    memset(&i_req, 0, sizeof(athcfg_wcmd_chanlist_t));

    args[0] = NULL;

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_CHAN_LIST;
    i_req.d_chanlist = chanlist;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;
    for(i = 0; i < sizeof(i_req.d_chanlist.chanlist); i++)
        printf("channel [%d]    -- %x\n",i,i_req.d_chanlist.chanlist[i]);

    return 0;
}

int
athcfg_chanlist_set(char *ifrn_name, int nargs, char *args[])
{
    int count = 0;
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_chanlist_t chanlist;
    memset(&i_req, 0, sizeof(athcfg_wcmd_chanlist_t));

    assert (nargs >= 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    if (nargs > ATHCFG_WCMD_CHAN_BYTES) 
        ABORT_ARG_SIZE(ifrn_name,"Mode-Name",ATHCFG_WCMD_CHAN_BYTES);
    else {
        for (count = 0; count < nargs; count++)    
            chanlist.chanlist[count] = atoi(args[count]);
    }            

    i_req.type = ATHCFG_WCMD_SET_CHAN_LIST;
    i_req.d_chanlist = chanlist;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;
    for(count = 0; count < sizeof(i_req.d_chanlist.chanlist); count++)
        printf("channel [%d]    -- %x\n",count,i_req.d_chanlist.chanlist[count]);

    return 0;
}


/**
 * @brief Handlers for Get WMM_PARAM.
 */
int
athcfg_wmmparam_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_wmmparaminfo_t wmmparam; 
    memset(&wmmparam, 0, sizeof(athcfg_wcmd_wmmparaminfo_t));

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

     if (!(strcmp (args[0], "cwmin") ))
        wmmparam.cmd = ATHCFG_WCMD_WMMPARAMS_CWMIN;

    else if (!(strcmp (args[0], "cwmax") ))
        wmmparam.cmd = ATHCFG_WCMD_WMMPARAMS_CWMAX;

    else if (!(strcmp (args[0], "aifs") ))
        wmmparam.cmd = ATHCFG_WCMD_WMMPARAMS_AIFS;

    else if (!(strcmp (args[0], "txop-limit") ))
        wmmparam.cmd = ATHCFG_WCMD_WMMPARAMS_TXOPLIMIT;

    else if (!(strcmp (args[0], "acm") ))
        wmmparam.cmd = ATHCFG_WCMD_WMMPARAMS_ACM;

    else if (!(strcmp (args[0], "noack-policy") ))
        wmmparam.cmd = ATHCFG_WCMD_WMMPARAMS_NOACKPOLICY;

    else {
        printf("!! ERROR !! \n");
        printf("Choose one from the list below --> \n");
        printf("                     cwmin \n");
        printf("                     cwmax \n");
        printf("                     aifs \n");
        printf("                     txop-limit \n");
        printf("                     acm \n");
        printf("                     noack-policy \n");
        return 0;
     } 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_WMM_PARAM;
    i_req.d_wmmparam = wmmparam;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    switch(i_req.d_wmmparam.cmd) {
        case ATHCFG_WCMD_WMMPARAMS_CWMIN:
            printf("%s\n" ,ENUM_PRINT(COMMAND WMMPARAMS CWMIN));
            break;
    
        case ATHCFG_WCMD_WMMPARAMS_CWMAX:
            printf("%s\n" ,ENUM_PRINT(COMMAND WMMPARAMS CWMIN));
            break;

        case ATHCFG_WCMD_WMMPARAMS_AIFS:
            printf("%s\n" ,ENUM_PRINT(COMMAND WMMPARAMS AIFS));
            break;

        case ATHCFG_WCMD_WMMPARAMS_TXOPLIMIT:
            printf("%s\n" ,ENUM_PRINT(COMMAND WMMPARAMS TXOPLIMIT));
            break;

        case ATHCFG_WCMD_WMMPARAMS_ACM:
            printf("%s\n" ,ENUM_PRINT(COMMAND WMMPARAMS ACM));
            break;

        case ATHCFG_WCMD_WMMPARAMS_NOACKPOLICY:
            printf("%s\n" ,ENUM_PRINT(COMMAND WMMPARAMS NOACKPOLICY));
            break;
    }      
    fprintf(stdout, "WmmParam-ac -- %d\nWmmParam-bss -- %d\nWmmParam-value -- %d\n", i_req.d_wmmparam.ac, i_req.d_wmmparam.bss, i_req.d_wmmparam.value);

    return 0;
}

int
athcfg_wmmparam_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_wmmparaminfo_t wmmparam; 
    memset(&wmmparam, 0, sizeof(athcfg_wcmd_wmmparaminfo_t));

    assert (nargs >= 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    if (!(strcmp (args[0], "cwmin") ))
        wmmparam.cmd = ATHCFG_WCMD_WMMPARAMS_CWMIN;

    else if (!(strcmp (args[0], "cwmax") ))
        wmmparam.cmd = ATHCFG_WCMD_WMMPARAMS_CWMAX;

    else if (!(strcmp (args[0], "aifs") ))
        wmmparam.cmd = ATHCFG_WCMD_WMMPARAMS_AIFS;

    else if (!(strcmp (args[0], "txop-limit") ))
        wmmparam.cmd = ATHCFG_WCMD_WMMPARAMS_TXOPLIMIT;

    else if (!(strcmp (args[0], "acm") ))
        wmmparam.cmd = ATHCFG_WCMD_WMMPARAMS_ACM;

    else if (!(strcmp (args[0], "noack-policy") ))
        wmmparam.cmd = ATHCFG_WCMD_WMMPARAMS_NOACKPOLICY;

    else {
        printf("!! ERROR !! \n");
        printf("Choose one from the list below --> \n");
        printf("                     cwmin \n");
        printf("                     cwmax \n");
        printf("                     aifs \n");
        printf("                     txop-limit \n");
        printf("                     acm \n");
        printf("                     noack-policy \n");
        return 0; 
     } 
    
    wmmparam.ac = atoi(args[1]);    
    wmmparam.bss = atoi(args[2]);   
    wmmparam.value = atoi(args[3]); 
    
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_WMM_PARAM;
    i_req.d_wmmparam = wmmparam;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;
   
    switch(i_req.d_wmmparam.cmd) {
        case ATHCFG_WCMD_WMMPARAMS_CWMIN:
            printf("%s\n" ,ENUM_PRINT(COMMAND WMMPARAMS CWMIN));
            break;

        case ATHCFG_WCMD_WMMPARAMS_CWMAX:
            printf("%s\n" ,ENUM_PRINT(COMMAND WMMPARAMS CWMIN));
            break;

        case ATHCFG_WCMD_WMMPARAMS_AIFS:
            printf("%s\n" ,ENUM_PRINT(COMMAND WMMPARAMS AIFS));
            break;

        case ATHCFG_WCMD_WMMPARAMS_TXOPLIMIT:
            printf("%s\n" ,ENUM_PRINT(COMMAND WMMPARAMS TXOPLIMIT));
            break;

        case ATHCFG_WCMD_WMMPARAMS_ACM:
            printf("%s\n" ,ENUM_PRINT(COMMAND WMMPARAMS ACM));
            break;

        case ATHCFG_WCMD_WMMPARAMS_NOACKPOLICY:
            printf("%s\n" ,ENUM_PRINT(COMMAND WMMPARAMS NOACKPOLICY));
            break;
    }
    fprintf(stdout, "WmmParam-ac -- %d\nWmmParam-bss -- %d\nWmmParam-value -- %d\n", i_req.d_wmmparam.ac, i_req.d_wmmparam.bss, i_req.d_wmmparam.value);

    return 0;
}

/**
 * @brief Handlers for Get NAME.
 */
int
athcfg_name_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_vapname_t name; 
    memset(&name, 0, sizeof(athcfg_wcmd_vapname_t));
    args[0] = NULL;

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_VAPNAME;
    i_req.d_vapname = name;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;
    
    fprintf(stdout, "Name -- %s\n",(char *) i_req.d_vapname.name);

    return 0;
}   

/**
 * @brief Handlers for Get IC-CAPS.
 */
int
athcfg_iccaps_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_ic_caps_t iccaps = 0;
    args[0] = NULL;

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_IC_CAPS;
    i_req.d_iccaps = iccaps;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "IC-CAPS -- %d\n", i_req.d_iccaps);

    return 0;
}

/**
 * @brief Handlers for Get RETRIES.
 */
int
athcfg_retries_get(char *ifrn_name, int nargs, char *args[])
{
    int count = 0;
    double temp = 0;

    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_retries_t retries;
    memset(&retries, 0, sizeof(athcfg_wcmd_retries_t));

    assert (nargs >= 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    retries.flags = ATHCFG_WCMD_RETRY_LIMIT;
    retries.disabled = 0;       

    /* check value modifier */
    if(!strcmp(args[count], "min")) {
        retries.flags |= ATHCFG_WCMD_RETRY_MIN;
        ++count;
    }   

    else if (!strcmp(args[count], "max")) {
        retries.flags |= ATHCFG_WCMD_RETRY_MAX;
        ++count;
    }   

    else if (!strcmp(args[count], "limit")) {
        retries.flags |= ATHCFG_WCMD_RETRY_LIMIT;    
        ++count;
    }

    else if(!strcmp(args[count], "lifetime")) {
        retries.flags &= ~ATHCFG_WCMD_RETRY_LIMIT;
        retries.flags |= ATHCFG_WCMD_RETRY_LIFETIME;
        ++count;
    }

    else if(sscanf(args[count], "%lg", &(temp)) == 1) {
        /* Limit is absolute, on the other hand lifetime is seconds */
        if(!(retries.flags & ATHCFG_WCMD_RETRY_LIMIT)) {
            /* Normalise lifetime */
            temp *= MEGA;   /* default = s */
            if(index(args[count], 'u')) temp /= MEGA;
            if(index(args[count], 'm')) temp /= KILO;
        }
        retries.value = (long) temp;
        ++count;
    }
    else {
        ABORT_ARG_TYPE(ifrn_name, "ATHCFG_WCMD_GET_RETRIES", args[count]);
        printf("Please choose one from the list below --\n");
        printf("                      -- min\n");
        printf("                      -- max\n");
        printf("                      -- limit\n");
        printf("                      -- lifetime\n");
        printf("                      -- int value\n"); 
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_RETRIES;
    i_req.d_retries = retries;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "Retries -- %d\n", i_req.d_retries.value);

    return 0;
}

/**
 * @brief Handlers for Get WAP-LIST.
 */
int
athcfg_waplist_get(char *ifrn_name, int nargs, char *args[])
{
    int count = 0;
    int intf = 0;
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_vaplist_t vaplist; 
    memset(&vaplist, 0, sizeof(athcfg_wcmd_vaplist_t));
    args[0] = NULL;

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_WAP_LIST;
    i_req.d_vaplist.len = ATHCFG_WCMD_MAX_AP;
    i_req.d_vaplist = vaplist;
    
    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    for (intf = 0; intf < ATHCFG_WCMD_MAX_AP; intf++) {
        printf("Vaplist for interface ath%d -- \n",intf );
        printf("Mode is -- ");
            
            if(i_req.d_vaplist.list[intf].mode == ATHCFG_WCMD_OPMODE_HOSTAP ) 
                printf(" HOSTAP \n");
            else if(i_req.d_vaplist.list[intf].mode == ATHCFG_WCMD_OPMODE_IBSS )
                printf(" IBSS \n");
            else if(i_req.d_vaplist.list[intf].mode == ATHCFG_WCMD_OPMODE_STA )
                printf(" STATION \n");
            else if(i_req.d_vaplist.list[intf].mode == ATHCFG_WCMD_OPMODE_WDS )
                printf(" WDS \n");
            else if(i_req.d_vaplist.list[intf].mode == ATHCFG_WCMD_OPMODE_AHDEMO )
                printf(" AHDEMO \n");
            else if(i_req.d_vaplist.list[intf].mode == ATHCFG_WCMD_OPMODE_MONITOR )
                printf(" MONITOR \n");
 
        printf("Mac Address is -- ");
        for(count = 0; count < 6; count++){
            fprintf(stdout, "%x", i_req.d_vaplist.list[intf].mac.addr[count]);
            if(count <5)
                printf(":");
            }
        printf("\n");
            
            
        printf("Link Quality -- %d\n",i_req.d_vaplist.list[intf].qual.qual);
        printf("Signal Level dBm-- %d\n",i_req.d_vaplist.list[intf].qual.level);
        printf("Noise Level dBm -- %d\n",i_req.d_vaplist.list[intf].qual.noise);
        printf("Flag updates -- %d\n",i_req.d_vaplist.list[intf].qual.updated);
    }        

    return 0;
}

/**
 * @brief Handlers for Get ADDBA-STATUS.
 */
int
athcfg_addbastat_get(char *ifrn_name, int nargs, char *args[])
{   
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_addba_status_t addbastat;
    memset(&addbastat, 0, sizeof(athcfg_wcmd_addba_status_t));
    args[0] = NULL;

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_ADDBA_STATUS;
    i_req.d_addba_status = addbastat;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "Addba-aid -- %d\nAddba-tid -- %d   Status -- %d\n", i_req.d_addba_status.aid,i_req.d_addba_status.tid,i_req.d_addba_status.status);

    return 0;
}

/**
 * @brief Handlers for Get CHANNEL-INFO.
 */
int
athcfg_chaninfo_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_chaninfo_t chaninfo; 
    memset(&chaninfo, 0, sizeof(athcfg_wcmd_chaninfo_t));
    args[0] = NULL;

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_CHAN_INFO;
    i_req.d_chaninfo = chaninfo;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "Chan-Freq -- %d\nChan-Flags -- %d\nChan-IEEE -- %d\nChan-Maxregpower -- %d\nChan-MaxPower -- %d\nChan-MinPower -- %d\n\
Chan-RegClassid -- %d\nNo. of Channels -- %d\n", i_req.d_chaninfo.chans.freq, i_req.d_chaninfo.chans.flags, \
       i_req.d_chaninfo.chans.ieee, i_req.d_chaninfo.chans.maxregpower, i_req.d_chaninfo.chans.maxpower, i_req.d_chaninfo.chans.minpower,\
       i_req.d_chaninfo.chans.regclassid,i_req.d_chaninfo.nchans);

    return 0;
}

/**
 * @brief Handlers for Get WPA-IE.
 */
int
athcfg_wpaie_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_wpaie_t wpaie; 
    memset(&wpaie, 0, sizeof(athcfg_wcmd_wpaie_t));

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    if( (strlen(args[0]) ==  ATHCFG_WCMD_MAC_STR_LEN) ) {
    sscanf(args[0],"%x:%x:%x:%x:%x:%x",(unsigned int *)&(wpaie.mac.addr[0]),\
            (unsigned int *)&(wpaie.mac.addr[1]),\
            (unsigned int *)&(wpaie.mac.addr[2]),\
            (unsigned int *)&(wpaie.mac.addr[3]),\
            (unsigned int *)&(wpaie.mac.addr[4]),\
            (unsigned int *)&(wpaie.mac.addr[5]) );
    }   
    else {
        printf("!! ERROR !!\n");
        printf("Please enter Mac in format of -- 00:11:22:33:44:55\n");
        ABORT_ARG_SIZE(ifrn_name, "WPA-IE", ATHCFG_WCMD_GET_WPA_IE);
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    wpaie.ie.len = ATHCFG_WCMD_IE_MAXLEN;
    i_req.type = ATHCFG_WCMD_GET_WPA_IE;
    i_req.d_wpaie = wpaie;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "WPA-IE-MAC -- %s\nWPA-IE -- %s\n", i_req.d_wpaie.mac.addr, (char *) i_req.d_wpaie.ie.data);

    return 0;
}

/**
 * @brief Handlers for Get WSC-IE.
 */
int
athcfg_wscie_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_wscie_t wscie; 
    memset(&wscie, 0, sizeof(athcfg_wcmd_wscie_t));

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    if( (strlen(args[0]) ==  ATHCFG_WCMD_MAC_STR_LEN) ) {
        sscanf(args[0],"%x:%x:%x:%x:%x:%x",(unsigned int *)&(wscie.mac.addr[0]),\
            (unsigned int *)&(wscie.mac.addr[1]),\
            (unsigned int *)&(wscie.mac.addr[2]),\
            (unsigned int *)&(wscie.mac.addr[3]),\
            (unsigned int *)&(wscie.mac.addr[4]),\
            (unsigned int *)&(wscie.mac.addr[5]) );
    }   
    else {
        printf("!! ERROR !!\n");
        printf("Please enter Mac in format of -- 00:11:22:33:44:55\n");
        ABORT_ARG_SIZE(ifrn_name, "WSC-IE", ATHCFG_WCMD_GET_WSC_IE);
    }
    
    wscie.ie.len = ATHCFG_WCMD_IE_MAXLEN;
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_WSC_IE;
    i_req.d_wscie = wscie;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "WSC-IE-MAC -- %s\nWSC-IE -- %s\n", i_req.d_wscie.mac.addr, i_req.d_wscie.ie.data);

    return 0;
}

/**
 * @brief Handlers for Set TXPOWER-LIMIT.
 */
int
athcfg_txpowlimit_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_txpowlimit_t txpowlimit ;
    memset(&txpowlimit, 0, sizeof(athcfg_wcmd_txpowlimit_t)); 

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    txpowlimit = atoi(args[0]);
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_TXPOWER_LIMIT;
    i_req.d_txpowlimit = txpowlimit;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "Tx-Power_Limit -- %d\n", i_req.d_txpowlimit);

    return 0;
}

/**
 * @brief Handlers for Set ATH-CAP.
 */
int
athcfg_athcap_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_devcap_t devcap; 
    memset(&devcap, 0, sizeof(athcfg_wcmd_devcap_t));

    assert (nargs >= 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    devcap.cap = atoi(args[0]);
    devcap.setting = atoi(args[1]);
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_DEV_CAP;
    i_req.d_devcap = devcap;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "ATH-CAP -- %d\nATH-Setting -- %d\n", i_req.d_devcap.cap, i_req.d_devcap.setting);

    return 0;
}

/**
 * @brief Handlers for Set TURBO.
 */
int
athcfg_turbo_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;  
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_turbo_t turbo ;
    memset(&turbo, 0, sizeof(athcfg_wcmd_turbo_t)); 

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    turbo = atoi(args[0]);
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_TURBO;
    i_req.d_turbo = turbo;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "Turbo -- %d\n", i_req.d_turbo);

    return 0;
}

/**
 * @brief Handlers for Set FITLER.
 */

int
athcfg_filter_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; ;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_filter_type_t filter ;
    memset(&filter, 0, sizeof(athcfg_wcmd_filter_type_t)); 

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    if (!(strcmp (args[0], "beacon") ))
        filter = ATHCFG_WCMD_FILTER_TYPE_BEACON;

    else if (!(strcmp (args[0], "probe-req") ))
        filter = ATHCFG_WCMD_FILTER_TYPE_PROBE_REQ;

    else if (!(strcmp (args[0], "probe-resp") ))
        filter = ATHCFG_WCMD_FILTER_TYPE_PROBE_RESP;

    else if (!(strcmp (args[0], "assoc-req") ))
        filter = ATHCFG_WCMD_FILTER_TYPE_ASSOC_REQ;

    else if (!(strcmp (args[0], "assoc-resp") ))
        filter = ATHCFG_WCMD_FILTER_TYPE_ASSOC_RESP;

    else if (!(strcmp (args[0], "auth") ))
        filter = ATHCFG_WCMD_FILTER_TYPE_AUTH;

    else if (!(strcmp (args[0], "deauth") ))
       filter = ATHCFG_WCMD_FILTER_TYPE_DEAUTH;

    else if (!(strcmp (args[0], "disassoc") ))
       filter = ATHCFG_WCMD_FILTER_TYPE_DISASSOC;

    else if (!(strcmp (args[0], "all") ))
       filter = ATHCFG_WCMD_FILTER_TYPE_ALL;

    else {
       printf("!! ERROR !! \n");
       printf("Choose one from the list below --> \n");
       printf("                 beacon \n");
       printf("                 probe-req \n"); 
       printf("                 probe-resp \n");
       printf("                 assoc-req \n");
       printf("                 assoc-resp \n");
       printf("                 auth \n");
       printf("                 deauth\n");
       printf("                 disassoc \n");
       printf("                 all \n");
       return 0;       
    }  
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_FILTER;
    i_req.d_filter = filter;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;
    
    switch(i_req.d_filter) {
        case ATHCFG_WCMD_FILTER_TYPE_BEACON:
        printf("%s\n", ENUM_PRINT(Filter Type -- BEACON));
        break;

    case ATHCFG_WCMD_FILTER_TYPE_PROBE_REQ:
        printf("%s\n", ENUM_PRINT(Filter Type -- PROBE REQUEST));
        break;

    case ATHCFG_WCMD_FILTER_TYPE_PROBE_RESP:
        printf("%s\n", ENUM_PRINT(Filter Type -- PROBE RESPONSE));
        break;

    case ATHCFG_WCMD_FILTER_TYPE_ASSOC_REQ:
        printf("%s\n", ENUM_PRINT(Filter Type -- ASSOC REQUEST));
            break;

    case ATHCFG_WCMD_FILTER_TYPE_ASSOC_RESP:
        printf("%s\n", ENUM_PRINT(Filter Type -- ASSOC RESPONSE));
            break;

    case ATHCFG_WCMD_FILTER_TYPE_AUTH:
        printf("%s\n", ENUM_PRINT(Filter Type -- AUTHENTICATION));
            break;

    case ATHCFG_WCMD_FILTER_TYPE_DEAUTH:
        printf("%s\n", ENUM_PRINT(Filter Type -- DEAUTHENTICATION));
            break;

    case ATHCFG_WCMD_FILTER_TYPE_DISASSOC:
        printf("%s\n", ENUM_PRINT(Filter Type -- DISASSOCIATION));
            break;

    case ATHCFG_WCMD_FILTER_TYPE_ALL:
        printf("%s\n", ENUM_PRINT(Filter Type -- ALL));
            break;

    }
    return 0;
}

/**
 * @brief Handlers for Set ADDBA-RESPONSE.
 */
int
athcfg_ADDBAresp_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_addba_resp_t addba_resp; 
    memset(&addba_resp, 0, sizeof(athcfg_wcmd_addba_resp_t));

    assert (nargs >= 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    addba_resp.aid = atoi(args[0]); 
    addba_resp.tid = atoi(args[1]); 

    if( (!strcasecmp(args[2], "unspecify")) )
        addba_resp.reason = ATHCFG_WCMD_REASON_UNSPECIFIED ;    
    else if( (!strcasecmp(args[2], "auth-expire")))     
        addba_resp.reason = ATHCFG_WCMD_REASON_AUTH_EXPIRE;
    else if( (!strcasecmp(args[2], "auth-leave")))    
        addba_resp.reason = ATHCFG_WCMD_REASON_AUTH_LEAVE;
    else if( (!strcasecmp(args[2], "assoc-expire")))    
        addba_resp.reason = ATHCFG_WCMD_REASON_ASSOC_EXPIRE;
    else if( (!strcasecmp(args[2], "assoc-tomany")))    
        addba_resp.reason = ATHCFG_WCMD_REASON_ASSOC_TOOMANY;
    else if( (!strcasecmp(args[2], "not-authed")))    
        addba_resp.reason = ATHCFG_WCMD_REASON_NOT_AUTHED;
    else if( (!strcasecmp(args[2], "rsn-req")))    
        addba_resp.reason = ATHCFG_WCMD_REASON_RSN_REQUIRED;
    else if( (!strcasecmp(args[2], "rsn-inconsis")))    
        addba_resp.reason = ATHCFG_WCMD_REASON_RSN_INCONSISTENT;
    else if( (!strcasecmp(args[2], "ie-inval")))    
        addba_resp.reason = ATHCFG_WCMD_REASON_IE_INVALID;
    else if( (!strcasecmp(args[2], "mic-fail")))    
        addba_resp.reason = ATHCFG_WCMD_REASON_MIC_FAILURE;
    else if( (!strcasecmp(args[2], "stat-succ")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_SUCCESS;
    else if( (!strcasecmp(args[2], "stat-unspe")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_UNSPECIFIED;
    else if( (!strcasecmp(args[2], "stat-capinfo")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_CAPINFO;
    else if( (!strcasecmp(args[2], "stat-not-assoc")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_NOT_ASSOCED;
    else if( (!strcasecmp(args[2], "stat-other")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_OTHER;
    else if( (!strcasecmp(args[2], "stat-alg")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_ALG;
    else if( (!strcasecmp(args[2], "stat-seq")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_SEQUENCE;
    else if( (!strcasecmp(args[2], "stat-chall")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_CHALLENGE;
    else if( (!strcasecmp(args[2], "stat-timeout")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_TIMEOUT;
    else if( (!strcasecmp(args[2], "stat-toomany")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_TOOMANY;
    else if( (!strcasecmp(args[2], "stat-basic-rate")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_BASIC_RATE;
    else if( (!strcasecmp(args[2], "stat-sp-req")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_SP_REQUIRED;
    else if( (!strcasecmp(args[2], "stat-pbcc-req")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_PBCC_REQUIRED;
    else if( (!strcasecmp(args[2], "stat-ca-req")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_CA_REQUIRED;
    else if( (!strcasecmp(args[2], "stat-tomany-sta")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_TOO_MANY_STATIONS;
    else if( (!strcasecmp(args[2], "stat-rates")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_RATES;
    else if( (!strcasecmp(args[2], "stat-shotlist-req")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_SHORTSLOT_REQUIRED;
    else if( (!strcasecmp(args[2], "stat-dssofdm-req")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_DSSSOFDM_REQUIRED;
    else if( (!strcasecmp(args[2], "stat-refuse")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_REFUSED;
    else if( (!strcasecmp(args[2], "stat-inval-param")))    
        addba_resp.reason = ATHCFG_WCMD_STATUS_INVALID_PARAM;
    else {
        printf("!! ERROR !!\n");
        printf("Choose one from the list below  -- unspecify\n");
        printf("                                -- auth-expire\n");
        printf("                                -- auth-leave\n");
        printf("                                -- assoc-expire\n");
        printf("                                -- assoc-tomany\n");
        printf("                                -- not-authed\n");
        printf("                                -- rsn-req\n");
        printf("                                -- rsn-inconsis\n");
        printf("                                -- ie-inval\n");
        printf("                                -- mic-fail\n");
        printf("                                -- stat-succ\n");
        printf("                                -- stat-unspe\n");
        printf("                                -- stat-capinfo\n");
        printf("                                -- nstat-not-assoc\n");
        printf("                                -- stat-other\n");
        printf("                                -- stat-alg\n");
        printf("                                -- stat-seq\n");
        printf("                                -- stat-chall\n");
        printf("                                -- stat-timeout\n");
        printf("                                -- stat-toomany\n");
        printf("                                -- stat-basic-rate\n");
        printf("                                -- stat-sp-req\n");
        printf("                                -- stat-pbcc-req\n");
        printf("                                -- stat-ca-req\n");
        printf("                                -- stat-tomany-sta\n");
        printf("                                -- stat-rates\n");
        printf("                                -- stat-shotlist-req\n");
        printf("                                -- stat-dssofdm-req\n");
        printf("                                -- stat-refuse\n");
        printf("                                -- stat-inval-param\n");
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_ADDBA_RESPONSE;
    i_req.d_addba_resp = addba_resp;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

//    fprintf(stdout, "Addbaresp-aid -- %d\nAddbaresp-tis -- %d\nAddbaresp-rg1 --   %d\n", i_req.d_addba_resp.aid, i_req.d_addba_resp.tid,
//                                             i_req.d_addba_resp.arg1);

    return 0;
}

/**
 * @brief Handlers for Set MLME.
 */
int
athcfg_mlme_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    unsigned int tmpmac[6], i;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_mlme_t mlme;
    memset(&mlme, 0, sizeof(athcfg_wcmd_mlme_t));

    assert (nargs >= 1);
    
    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    mlme.op = atoi(args[0]);     

    if (!(strcmp (args[1], "assoc") ))
        mlme.reason = ATHCFG_WCMD_MLME_ASSOC;

    else if (!(strcmp (args[1], "disassoc") ))
    {
        mlme.reason = ATHCFG_WCMD_MLME_DISASSOC;
        /* set meme.op value to 1, just let disassoc command can success */
        mlme.op = 1;
    }

    else if (!(strcmp (args[1], "deauth") ))
        mlme.reason = ATHCFG_WCMD_MLME_DEAUTH;

    else if (!(strcmp (args[1], "authorize") ))
        mlme.reason = ATHCFG_WCMD_MLME_AUTHORIZE;

    else if (!(strcmp (args[1], "unauthorize") ))
        mlme.reason = ATHCFG_WCMD_MLME_UNAUTHORIZE;

    else {
        printf("!! ERROR !!\n");
        printf("Choose one from the list below -->\n");
        printf("                     assoc \n");
        printf("                     disassoc \n");
        printf("                     deauth \n");
        printf("                     authorize \n");
        printf("                     unauthoroze \n");
        return 0;
    }        
    
    if (mlme.reason != ATHCFG_WCMD_MLME_DISASSOC)   /* station disassociation doesn't need check MAC address */
    {
     if( (strlen(args[2]) ==  ATHCFG_WCMD_MAC_STR_LEN) ) {
         
         sscanf(args[2],"%x:%x:%x:%x:%x:%x", &(tmpmac[0]),\
            &(tmpmac[1]),\
            &(tmpmac[2]),\
            &(tmpmac[3]),\
            &(tmpmac[4]),\
            &(tmpmac[5]) );
		 for(i=0; i< 6; i++)
           mlme.mac.addr[i] = tmpmac[i];
     }   
     else {
        printf("!! ERROR !!\n");
        printf("Please enter Mac in format of -- 00:11:22:33:44:55\n");
        ABORT_ARG_SIZE(ifrn_name, "MLME", ATHCFG_WCMD_SET_MLME);
     }
    }  
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_MLME;
    i_req.d_mlme = mlme;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "Mlme-OP -- %d\nMlme-Mac --%s\n", i_req.d_mlme.op, i_req.d_mlme.mac.addr);

    switch(i_req.d_mlme.reason) {
        case ATHCFG_WCMD_MLME_ASSOC:
            printf("%s\n", ENUM_PRINT(Reason -- ASSOCIATION));
            break;
    
        case ATHCFG_WCMD_MLME_DISASSOC:
            printf("%s\n", ENUM_PRINT(Reason -- DISASSOCIATION));
            break;

        case ATHCFG_WCMD_MLME_DEAUTH:
            printf("%s\n", ENUM_PRINT(Reason -- DEAUTHENTICATION));
            break;

        case ATHCFG_WCMD_MLME_AUTHORIZE:
            printf("%s\n", ENUM_PRINT(Reason -- AUTHORIZATION));
            break;

        case ATHCFG_WCMD_MLME_UNAUTHORIZE:
            printf("%s\n", ENUM_PRINT(Reason -- UNAUTHORIZATION));
            break;
    }       
    return 0;
}

/**
 * @brief Handlers for Set SEND_ADDBA.
 */
int
athcfg_sendADDBA_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_addba_t addba; 
    memset(&addba, 0, sizeof(athcfg_wcmd_addba_t));

    assert (nargs >= 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    addba.aid = atoi(args[0]);      
    addba.tid = atoi(args[1]);      
    addba.size = atoi(args[2]);     
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_SEND_ADDBA;
    i_req.d_addba = addba;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

//    fprintf(stdout, "SendADDBA-aid -- %d\nSendADDBA-tid -- %d\nSendADDBA-arg -- %d\n", i_req.d_addba.aid, i_req.d_addba.tid,
//                                             i_req.d_addba.arg1);

    return 0;
}

/**
 * @brief Handlers for Set SEND_DELBA.
 */
int
athcfg_sendDELBA_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; ;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_delba_t delba; ;
    memset(&delba, 0, sizeof(athcfg_wcmd_delba_t));

    assert (nargs >= 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    delba.aid = atoi(args[0]);      
    delba.tid = atoi(args[1]);      
    delba.identifier = atoi(args[2]);     
    
    if( (!strcasecmp(args[3], "unspecify")) )
        delba.reason = ATHCFG_WCMD_REASON_UNSPECIFIED ; 
    else if( (!strcasecmp(args[3], "auth-expire")))     
        delba.reason = ATHCFG_WCMD_REASON_AUTH_EXPIRE;
    else if( (!strcasecmp(args[3], "auth-leave")))    
        delba.reason = ATHCFG_WCMD_REASON_AUTH_LEAVE;
    else if( (!strcasecmp(args[3], "assoc-expire")))    
        delba.reason = ATHCFG_WCMD_REASON_ASSOC_EXPIRE;
    else if( (!strcasecmp(args[3], "assoc-tomany")))    
        delba.reason = ATHCFG_WCMD_REASON_ASSOC_TOOMANY;
    else if( (!strcasecmp(args[3], "not-authed")))    
        delba.reason = ATHCFG_WCMD_REASON_NOT_AUTHED;
    else if( (!strcasecmp(args[3], "rsn-req")))    
        delba.reason = ATHCFG_WCMD_REASON_RSN_REQUIRED;
    else if( (!strcasecmp(args[3], "rsn-inconsis")))    
        delba.reason = ATHCFG_WCMD_REASON_RSN_INCONSISTENT;
    else if( (!strcasecmp(args[3], "ie-inval")))    
        delba.reason = ATHCFG_WCMD_REASON_IE_INVALID;
    else if( (!strcasecmp(args[3], "mic-fail")))    
        delba.reason = ATHCFG_WCMD_REASON_MIC_FAILURE;
    else if( (!strcasecmp(args[3], "stat-succ")))    
        delba.reason = ATHCFG_WCMD_STATUS_SUCCESS;
    else if( (!strcasecmp(args[3], "stat-unspe")))    
        delba.reason = ATHCFG_WCMD_STATUS_UNSPECIFIED;
    else if( (!strcasecmp(args[3], "stat-capinfo")))    
        delba.reason = ATHCFG_WCMD_STATUS_CAPINFO;
    else if( (!strcasecmp(args[3], "stat-not-assoc")))    
        delba.reason = ATHCFG_WCMD_STATUS_NOT_ASSOCED;
    else if( (!strcasecmp(args[3], "stat-other")))    
        delba.reason = ATHCFG_WCMD_STATUS_OTHER;
    else if( (!strcasecmp(args[3], "stat-alg")))    
        delba.reason = ATHCFG_WCMD_STATUS_ALG;
    else if( (!strcasecmp(args[3], "stat-seq")))    
        delba.reason = ATHCFG_WCMD_STATUS_SEQUENCE;
    else if( (!strcasecmp(args[3], "stat-chall")))    
        delba.reason = ATHCFG_WCMD_STATUS_CHALLENGE;
    else if( (!strcasecmp(args[3], "stat-timeout")))    
        delba.reason = ATHCFG_WCMD_STATUS_TIMEOUT;
    else if( (!strcasecmp(args[3], "stat-toomany")))    
        delba.reason = ATHCFG_WCMD_STATUS_TOOMANY;
    else if( (!strcasecmp(args[3], "stat-basic-rate")))    
        delba.reason = ATHCFG_WCMD_STATUS_BASIC_RATE;
    else if( (!strcasecmp(args[3], "stat-sp-req")))    
        delba.reason = ATHCFG_WCMD_STATUS_SP_REQUIRED;
    else if( (!strcasecmp(args[3], "stat-pbcc-req")))    
        delba.reason = ATHCFG_WCMD_STATUS_PBCC_REQUIRED;
    else if( (!strcasecmp(args[3], "stat-ca-req")))    
        delba.reason = ATHCFG_WCMD_STATUS_CA_REQUIRED;
    else if( (!strcasecmp(args[3], "stat-tomany-sta")))    
        delba.reason = ATHCFG_WCMD_STATUS_TOO_MANY_STATIONS;
    else if( (!strcasecmp(args[3], "stat-rates")))    
        delba.reason = ATHCFG_WCMD_STATUS_RATES;
    else if( (!strcasecmp(args[3], "stat-shotlist-req")))    
        delba.reason = ATHCFG_WCMD_STATUS_SHORTSLOT_REQUIRED;
    else if( (!strcasecmp(args[3], "stat-dssofdm-req")))    
        delba.reason = ATHCFG_WCMD_STATUS_DSSSOFDM_REQUIRED;
    else if( (!strcasecmp(args[3], "stat-refuse")))    
        delba.reason = ATHCFG_WCMD_STATUS_REFUSED;
    else if( (!strcasecmp(args[3], "stat-inval-param")))    
        delba.reason = ATHCFG_WCMD_STATUS_INVALID_PARAM;
    else {
        printf("!! ERROR !!\n");
        printf("Choose one from the list below  -- unspecify\n");
        printf("                                -- auth-expire\n");
        printf("                                -- auth-leave\n");
        printf("                                -- assoc-expire\n");
        printf("                                -- assoc-tomany\n");
        printf("                                -- not-authed\n");
        printf("                                -- rsn-req\n");
        printf("                                -- rsn-inconsis\n");
        printf("                                -- ie-inval\n");
        printf("                                -- mic-fail\n");
        printf("                                -- stat-succ\n");
        printf("                                -- stat-unspe\n");
        printf("                                -- stat-capinfo\n");
        printf("                                -- nstat-not-assoc\n");
        printf("                                -- stat-other\n");
        printf("                                -- stat-alg\n");
        printf("                                -- stat-seq\n");
        printf("                                -- stat-chall\n");
        printf("                                -- stat-timeout\n");
        printf("                                -- stat-toomany\n");
        printf("                                -- stat-basic-rate\n");
        printf("                                -- stat-sp-req\n");
        printf("                                -- stat-pbcc-req\n");
        printf("                                -- stat-ca-req\n");
        printf("                                -- stat-tomany-sta\n");
        printf("                                -- stat-rates\n");
        printf("                                -- stat-shotlist-req\n");
        printf("                                -- stat-dssofdm-req\n");
        printf("                                -- stat-refuse\n");
        printf("                                -- stat-inval-param\n");
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_SEND_DELBA;
    i_req.d_delba = delba;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

//    fprintf(stdout, "SendDELBA-aid -- %d\nSendDELBA-tid -- %d\nSendDELBA-arg1 -- %d\nSendDELBA-arg2 -- %d\n", i_req.d_delba.aid, 
//                                  i_req.d_delba.tid, i_req.d_delba.arg1, i_req.d_delba.arg2);

    return 0;
}

/**
 * @brief Handlers for Set DEL-KEY.
 */

int
athcfg_delkey_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_keyinfo_t delkey; 
    memset(&delkey, 0, sizeof(athcfg_wcmd_keyinfo_t));

    assert (nargs >= 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    if (nargs == 1) {
        if (!strcmp(args[0], "all")) {
            strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
            i_req.type = ATHCFG_WCMD_SET_DELKEY_ALL;
            if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
                return EIO;
            printf("delete all keys\n");            
            return 0;
        }
    }

    /* Get the Index */
    if(3 < atoi(args[0]) || atoi(args[0]) < 0) {
        printf("!! Error !!\n");
        printf("Enter a valid Index value--  0 | 1 | 2 | 3\n");
        return 0;
    }
    else
        delkey.ik_keyix = atoi(args[0]);

    if( (strlen(args[1]) ==  ATHCFG_WCMD_MAC_STR_LEN) ) {
        sscanf(args[0],"%x:%x:%x:%x:%x:%x",(unsigned int *)&(delkey.ik_macaddr[0]),\
            (unsigned int *)&(delkey.ik_macaddr[1]),\
            (unsigned int *)&(delkey.ik_macaddr[2]),\
            (unsigned int *)&(delkey.ik_macaddr[3]),\
            (unsigned int *)&(delkey.ik_macaddr[4]),\
            (unsigned int *)&(delkey.ik_macaddr[5]) );
    }   
    else {
        printf("!! ERROR !!\n");
        printf("Please enter Mac in format of -- 00:11:22:33:44:55\n");
        ABORT_ARG_SIZE(ifrn_name, "KEY", ATHCFG_WCMD_SET_DELKEY);
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_DELKEY;
    i_req.d_key = delkey;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "Key -- %s\n",(char *) i_req.d_key.ik_keydata);

    return 0;
}

/**
 * @brief Handler for DELETE-MAC.
 */
int
athcfg_delmac_set(char *ifrn_name, int nargs, char *args[])
{
    int count = 0;  
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    a_int32_t mac_buf[6];
    athcfg_ethaddr_t delmac ;
    memset(&delmac, 0, sizeof(athcfg_ethaddr_t)); 

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    if( (strlen(args[0]) ==  ATHCFG_WCMD_MAC_STR_LEN) ) {
#if 0
        sscanf(args[0],"%x:%x:%x:%x:%x:%x",(unsigned int *)&(delmac.addr[0]),\
            (unsigned int *)&(delmac.addr[1]),\
            (unsigned int *)&(delmac.addr[2]),\
            (unsigned int *)&(delmac.addr[3]),\
            (unsigned int *)&(delmac.addr[4]),\
            (unsigned int *)&(delmac.addr[5]) );
 #endif
        sscanf(args[0], "%x:%x:%x:%x:%x:%x",&mac_buf[0],&mac_buf[1],&mac_buf[2],&mac_buf[3],&mac_buf[4],&mac_buf[5]);
        for(count = 0; count < 6; count++)
               delmac.addr[count]=(a_uint8_t)mac_buf[count] ;
    }   
    else {
        printf("!! ERROR !!\n");
        printf("Please enter Mac in format of -- 00:11:22:33:44:55\n");
        ABORT_ARG_SIZE(ifrn_name, "DELMAC", ATHCFG_WCMD_ADDR_LEN);
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_DELMAC;
    i_req.d_mac = delmac;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    printf("MAC Deleted -- ");
    for(count = 0; count < 6; count++){
        fprintf(stdout, "%x", i_req.d_mac.addr[count]);
    if(count <5)
        printf(":");
    }
    printf("\n");

    return 0;
}

/**
 * @brief Handler for ADD-MAC.
 */
int
athcfg_addmac_set(char *ifrn_name, int nargs, char *args[])
{   
    int count = 0;
    athcfg_wcmd_t i_req; 
    a_int32_t mac_buf[6];
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_ethaddr_t addmac;
    memset(&addmac, 0, sizeof(athcfg_ethaddr_t));

    assert (nargs == 1); 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    printf("Mac -- %s\n",args[0]);
    if( (strlen(args[0]) ==  ATHCFG_WCMD_MAC_STR_LEN) ) {
 #if 0
        printf("entered strlen %d\n", strlen(args[0]) );
        sscanf(args[0],"%x:%x:%x:%x:%x:%x",(unsigned int *)&(addmac.addr[0]),\
            (unsigned int *)&(addmac.addr[1]),\
            (unsigned int *)&(addmac.addr[2]),\
            (unsigned int *)&(addmac.addr[3]),\
            (unsigned int *)&(addmac.addr[4]),\
            (unsigned int *)&(addmac.addr[5]) );
#endif
    sscanf(args[0], "%x:%x:%x:%x:%x:%x",&mac_buf[0],&mac_buf[1],&mac_buf[2],&mac_buf[3],&mac_buf[4],&mac_buf[5]);
        for(count = 0; count < 6; count++)
               addmac.addr[count]=(a_uint8_t)mac_buf[count] ;

    }   
    else {
    printf("!! ERROR !!\n");
        printf("Please enter Mac in format of -- 00:11:22:33:44:55\n");
    ABORT_ARG_SIZE(ifrn_name, "ADDMAC", ATHCFG_WCMD_ADDR_LEN);
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_ADD_MAC;
    i_req.d_mac = addmac;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    printf("MAC Added -- ");
    for(count = 0; count < 6; count++){
        fprintf(stdout, "%x", i_req.d_mac.addr[count]);
    if(count <5)
        printf(":");
    }
    printf("\n");

    return 0;
}


/**
 * @brief Handlers for Get/Set OP-MODE.
 */
int
athcfg_opmode_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_vapmode_t vapmode; 
    memset(&vapmode, 0, sizeof(athcfg_wcmd_vapmode_t));

    args[0] = NULL;
   
    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_VAPMODE;
    i_req.d_vapmode = vapmode;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    switch(i_req.d_vapmode) {
        case ATHCFG_WCMD_VAPMODE_MASTER:
            printf("%s\n", ENUM_PRINT(Operating Mode -- HOSTAPD MODE));
            break;
    
        case ATHCFG_WCMD_VAPMODE_MONITOR:
             printf("%s\n", ENUM_PRINT(Operating Mode -- MONITOR MODE));
            break;

        case ATHCFG_WCMD_VAPMODE_AUTO:
            printf("%s\n", ENUM_PRINT(Operating Mode -- AUTO MODE));
            break;

        case ATHCFG_WCMD_VAPMODE_REPEAT:
            printf("%s\n", ENUM_PRINT(Operating Mode -- REPEAT MODE));
            break;

        case ATHCFG_WCMD_VAPMODE_INFRA:
            printf("%s\n", ENUM_PRINT(Operating Mode -- STATION MODE));
            break;

        case ATHCFG_WCMD_VAPMODE_SECOND:
            printf("%s\n", ENUM_PRINT(Operating Mode -- SECONDARY MODE)); 
            break;

        default:
            return 0;
    }
    return 0;
}



int
athcfg_autoassoc_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 

    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_AUTOASSOC;
    i_req.d_autoassoc = atoi(args[0]);

    if(athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "Auto Association set to %d\n", i_req.d_autoassoc);

    return 0;
}

int
athcfg_autoassoc_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_ic_autoassoc_t icautoassoc = 0;
    args[0] = NULL;

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_AUTOASSOC;
    i_req.d_autoassoc = icautoassoc;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "Auto Association -- %d\n", i_req.d_autoassoc);

    return 0;
}


int
athcfg_scandelay_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 

    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_SCANDELAY;
    i_req.d_icscandelay = atoi(args[0]);

    if(athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    return 0;
}

int
athcfg_scandelay_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_ic_nextscandelay_t icscandelay = 0;
    args[0] = NULL;

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_SCANDELAY;
    i_req.d_icscandelay = icscandelay;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "SCAN DELAY -- %03u secs\n", i_req.d_icscandelay);

    return 0;
}

int
athcfg_vapdelete_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 

    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_vapmode_t vapmode; 
    memset(&vapmode, 0, sizeof(athcfg_wcmd_vapmode_t));

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_VAPDELETE;
    i_req.d_vapmode = vapmode;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;
    
    printf("VAP DELETE SUCCESSFUL\n");
        
    return 0;
}

int
athcfg_opmode_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_vapmode_t vapmode; 
    memset(&vapmode, 0, sizeof(athcfg_wcmd_vapmode_t));

    assert (nargs >= 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    if (!(strcmp (args[0], "hostap") ))
        vapmode = ATHCFG_WCMD_VAPMODE_MASTER;

    else if (!(strcmp (args[0], "monitor") ))
        vapmode = ATHCFG_WCMD_VAPMODE_MONITOR;

    else if (!(strcmp (args[0], "adhoc") ))
        vapmode = ATHCFG_WCMD_VAPMODE_ADHOC;

    else if (!(strcmp (args[0], "auto") ))
        vapmode = ATHCFG_WCMD_VAPMODE_AUTO;

    else if (!(strcmp (args[0], "station") ))
        vapmode = ATHCFG_WCMD_VAPMODE_INFRA;

    else if (!(strcmp (args[0], "repeat") ))
        vapmode = ATHCFG_WCMD_VAPMODE_REPEAT;

    else if (!(strcmp (args[0], "secondary") ))
        vapmode = ATHCFG_WCMD_VAPMODE_SECOND;

    else {
        printf("!! ERROR !!\n");
        printf("Choose one from the list below --> \n");
        printf("                     hostap \n");
        printf("                     monitor \n");
        printf("                     adhoc \n");
        printf("                     auto \n");
        printf("                     station \n");
        printf("                     repeat \n");
        printf("                     secondary \n");
        return 0;
    }      
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_VAPMODE;
    i_req.d_vapmode = vapmode;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    switch(i_req.d_vapmode) {
        case ATHCFG_WCMD_VAPMODE_MASTER:
            printf("%s\n", ENUM_PRINT(Operating Mode -- HOSTAPD MODE));
            break;

        case ATHCFG_WCMD_VAPMODE_MONITOR:
            printf("%s\n", ENUM_PRINT(Operating Mode -- MONITOR MODE));
            break;

        case ATHCFG_WCMD_VAPMODE_ADHOC:
            printf("%s\n", ENUM_PRINT(Operating Mode -- ADHOC MODE));
            break;

        case ATHCFG_WCMD_VAPMODE_AUTO:
            printf("%s\n", ENUM_PRINT(Operating Mode -- AUTO MODE));
            break;

        case ATHCFG_WCMD_VAPMODE_INFRA:
            printf("%s\n", ENUM_PRINT(Operating Mode -- STATION MODE));
            break;

        case ATHCFG_WCMD_VAPMODE_REPEAT:
            printf("%s\n", ENUM_PRINT(Operating Mode -- REPEAT MODE)); 
            break;

        case ATHCFG_WCMD_VAPMODE_SECOND:
             printf("%s\n", ENUM_PRINT(Operating Mode -- SECONDARY MODE));
             break;

    }
    return 0;
}

/**
 * @brief Handlers for Get ATH-STATUS.
 */
int
athcfg_athstats_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    int i = 0;    
    assert (nargs == 0);
    args[0] = NULL;

    athcfg_wcmd_phystats_t *phystats; 

    if( (phystats = malloc(sizeof(athcfg_wcmd_phystats_t)) ) == NULL)
        ERR_MALLOC_FAIL("phystats");

    memset(phystats, 0, sizeof(athcfg_wcmd_phystats_t));

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        free(phystats);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_STATUS;
    i_req.d_phystats = phystats;
    
    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0) {
        free(phystats);
        return EIO;
    }

    printf("ATH STATUS for Interface [%s] are as below ---------------\n",ifrn_name);
    fprintf(stdout,"Device reset by watchdog            -- %d\n",i_req.d_phystats->ast_watchdog);
    fprintf(stdout,"Fatal Hardware error Interrupt      -- %d\n",i_req.d_phystats->ast_hardware);
    fprintf(stdout,"Beacon miss Interrupt           -- %d\n",i_req.d_phystats->ast_bmiss);
    fprintf(stdout,"Rx overrun Interrupt            -- %d\n",i_req.d_phystats->ast_rxorn);
    fprintf(stdout,"Rx eol Interrupt                -- %d\n",i_req.d_phystats->ast_rxeol);
    fprintf(stdout,"Tx underrun Interrupt           -- %d\n",i_req.d_phystats->ast_txurn);
    fprintf(stdout,"Tx timeout Interrupt            -- %d\n",i_req.d_phystats->ast_txto);
    fprintf(stdout,"Carrier sense timeout Interrupt     -- %d\n",i_req.d_phystats->ast_cst);
    fprintf(stdout,"Mibs Interrupt              -- %d\n",i_req.d_phystats->ast_mib);
    fprintf(stdout,"Packets sent on the Interface       -- %d\n",i_req.d_phystats->ast_tx_packets);
    fprintf(stdout,"Management frames Transmitted       -- %d\n",i_req.d_phystats->ast_tx_mgmt);
    fprintf(stdout,"Frames discarded prior to Assoc     -- %d\n",i_req.d_phystats->ast_tx_discard);
    fprintf(stdout,"Frames discarded 'cuz device gone       -- %d\n",i_req.d_phystats->ast_tx_invalid);
    fprintf(stdout,"Tx queue stopped 'cuz full          -- %d\n",i_req.d_phystats->ast_tx_qstop);
    fprintf(stdout,"Tx encapsultaion failed         -- %d\n",i_req.d_phystats->ast_tx_encap);
    fprintf(stdout,"Tx failed 'cuz no node          -- %d\n",i_req.d_phystats->ast_tx_nonode);
    fprintf(stdout,"Tx failed 'cuz no tx buffer (data)      -- %d\n",i_req.d_phystats->ast_tx_nobuf);
    fprintf(stdout,"Tx failed 'cuz no tx buffer (mgmt)      -- %d\n",i_req.d_phystats->ast_tx_nobufmgt);
    fprintf(stdout,"Tx failed 'cuz too many retries     -- %d\n",i_req.d_phystats->ast_tx_xretries);
    fprintf(stdout,"Tx failed 'cuz FIFO underrun                -- %d\n",i_req.d_phystats->ast_tx_fifoerr);
    fprintf(stdout,"Tx failed 'cuz xmit filtered                -- %d\n",i_req.d_phystats->ast_tx_filtered);
    fprintf(stdout,"Tx timer expired                            -- %d\n",i_req.d_phystats->ast_tx_timer_exp);
    fprintf(stdout,"Tx on-chip retries (short)                  -- %d\n",i_req.d_phystats->ast_tx_shortretry);
    fprintf(stdout,"Tx on-chip retries (long)                   -- %d\n",i_req.d_phystats->ast_tx_longretry);
    fprintf(stdout,"Tx failed 'cuz bogus xmit rate              -- %d\n",i_req.d_phystats->ast_tx_badrate);
    fprintf(stdout,"Tx frames with no ack marked                -- %d\n",i_req.d_phystats->ast_tx_noack);
    fprintf(stdout,"Tx frames with rts enabled                  -- %d\n",i_req.d_phystats->ast_tx_rts);
    fprintf(stdout,"Tx frames with cts enabled                  -- %d\n",i_req.d_phystats->ast_tx_cts);
    fprintf(stdout,"Tx frames with short preamble               -- %d\n",i_req.d_phystats->ast_tx_shortpre);
    fprintf(stdout,"Tx frames with alternate rate               -- %d\n",i_req.d_phystats->ast_tx_altrate);
    fprintf(stdout,"Tx frames with protection                   -- %d\n",i_req.d_phystats->ast_tx_protect);
    fprintf(stdout,"Rx failed 'cuz of desc overrun              -- %d\n",i_req.d_phystats->ast_rx_orn);
    fprintf(stdout,"Rx failed 'cuz of bad CRC                   -- %d\n",i_req.d_phystats->ast_rx_crcerr);
    fprintf(stdout,"Rx failed 'cuz of FIFO overrun              -- %d\n",i_req.d_phystats->ast_rx_fifoerr);
    fprintf(stdout,"Rx failed 'cuz decryption                   -- %d\n",i_req.d_phystats->ast_rx_badcrypt);
    fprintf(stdout,"Rx failed 'cuz MIC failure                  -- %d\n",i_req.d_phystats->ast_rx_badmic);
    fprintf(stdout,"Rx PHY error summary count                  -- %d\n",i_req.d_phystats->ast_rx_phyerr);
    fprintf(stdout,"Rx PHY error per-code counts                -- %s\n",(char *)i_req.d_phystats->ast_rx_phy);
    fprintf(stdout,"Rx discarded 'cuz frame too short           -- %d\n",i_req.d_phystats->ast_rx_tooshort);
    fprintf(stdout,"Rx discarded 'cuz frame too large           -- %d\n",i_req.d_phystats->ast_rx_toobig);
    fprintf(stdout,"Rx setup failed 'cuz no skbuff              -- %d\n",i_req.d_phystats->ast_rx_nobuf);
    fprintf(stdout,"Packet recv on the interface                -- %d\n",i_req.d_phystats->ast_rx_packets);
    fprintf(stdout,"Management frames received                  -- %d\n",i_req.d_phystats->ast_rx_mgt);
    fprintf(stdout,"Control frames received                     -- %d\n",i_req.d_phystats->ast_rx_ctl);
    fprintf(stdout,"Tx rssi of last ack [combined]              -- %d\n",i_req.d_phystats->ast_tx_rssi_combined);
    fprintf(stdout,"Tx rssi of last ack [ctl, chain 0]          -- %d\n",i_req.d_phystats->ast_tx_rssi_ctl0);
    fprintf(stdout,"Tx rssi of last ack [ctl, chain 1]          -- %d\n",i_req.d_phystats->ast_tx_rssi_ctl1);
    fprintf(stdout,"Tx rssi of last ack [ctl, chain 2]          -- %d\n",i_req.d_phystats->ast_tx_rssi_ctl2);
    fprintf(stdout,"Tx rssi of last ack [ext, chain 0]          -- %d\n",i_req.d_phystats->ast_tx_rssi_ext0);
    fprintf(stdout,"Tx rssi of last ack [ext, chain 1]          -- %d\n",i_req.d_phystats->ast_tx_rssi_ext1);
    fprintf(stdout,"Tx rssi of last ack [ext, chain 2]          -- %d\n",i_req.d_phystats->ast_tx_rssi_ext2);
    fprintf(stdout,"Rx rssi of last ack [combined]              -- %d\n",i_req.d_phystats->ast_rx_rssi_combined);
    fprintf(stdout,"Rx rssi of last ack [ctl, chain 0]          -- %d\n",i_req.d_phystats->ast_rx_rssi_ctl0);
    fprintf(stdout,"Rx rssi of last ack [ctl, chain 1]          -- %d\n",i_req.d_phystats->ast_rx_rssi_ctl1);
    fprintf(stdout,"Rx rssi of last ack [ctl, chain 2]          -- %d\n",i_req.d_phystats->ast_rx_rssi_ctl2);
    fprintf(stdout,"Rx rssi of last ack [ext, chain 0]          -- %d\n",i_req.d_phystats->ast_rx_rssi_ext0);
    fprintf(stdout,"Rx rssi of last ack [ext, chain 1]          -- %d\n",i_req.d_phystats->ast_rx_rssi_ext1);
    fprintf(stdout,"Rx rssi of last ack [ext, chain 2]          -- %d\n",i_req.d_phystats->ast_rx_rssi_ext2);
    fprintf(stdout,"Beacons transmitted             -- %d\n",i_req.d_phystats->ast_be_xmit);
    fprintf(stdout,"No skbuff available for beacon              -- %d\n",i_req.d_phystats->ast_be_nobuf);
    fprintf(stdout,"Periodic calibration calls                  -- %d\n",i_req.d_phystats->ast_per_cal);
    fprintf(stdout,"Periodic calibration failed                 -- %d\n",i_req.d_phystats->ast_per_calfail);
    fprintf(stdout,"Periodic calibration rfgain reset           -- %d\n",i_req.d_phystats->ast_per_rfgain);
    fprintf(stdout,"Rate control checks                         -- %d\n",i_req.d_phystats->ast_rate_calls);
    fprintf(stdout,"Rate control raised xmit rate               -- %d\n",i_req.d_phystats->ast_rate_raise);
    fprintf(stdout,"Rate control dropped xmit rate              -- %d\n",i_req.d_phystats->ast_rate_drop);
    fprintf(stdout,"Rx/default antenna switches                 -- %d\n",i_req.d_phystats->ast_ant_defswitch);
    fprintf(stdout,"Tx antenna switches                         -- %d\n",i_req.d_phystats->ast_ant_txswitch);
    fprintf(stdout,"Rx frames with antenna                      -- %s\n",(char *)i_req.d_phystats->ast_ant_rx);
    fprintf(stdout,"Tx frames with antenna                      -- %s\n",(char *)i_req.d_phystats->ast_ant_tx);
    fprintf(stdout,"Driver suspend calls                        -- %d\n",i_req.d_phystats->ast_suspend);
    fprintf(stdout,"Driver resume calls                         -- %d\n",i_req.d_phystats->ast_resume);
    fprintf(stdout,"Driver shutdown calls                       -- %d\n",i_req.d_phystats->ast_shutdown);
    fprintf(stdout,"Driver init calls                           -- %d\n",i_req.d_phystats->ast_init);
    fprintf(stdout,"Driver stop calls                           -- %d\n",i_req.d_phystats->ast_stop);
    fprintf(stdout,"Driver resets                               -- %d\n",i_req.d_phystats->ast_reset);
    fprintf(stdout,"Nodes allocated                             -- %d\n",i_req.d_phystats->ast_nodealloc);
    fprintf(stdout,"Nodes deleted                               -- %d\n",i_req.d_phystats->ast_nodefree);
    fprintf(stdout,"Keys allocated                              -- %d\n",i_req.d_phystats->ast_keyalloc);
    fprintf(stdout,"Keys allocates                              -- %d\n",i_req.d_phystats->ast_keydelete);
    fprintf(stdout,"Beacon stuck                                -- %d\n",i_req.d_phystats->ast_bstuck);
    fprintf(stdout,"Drain tx queue                              -- %d\n",i_req.d_phystats->ast_draintxq);
    fprintf(stdout,"Stop tx queue dma                           -- %d\n",i_req.d_phystats->ast_stopdma);
    fprintf(stdout,"Stop recv                                   -- %d\n",i_req.d_phystats->ast_stoprecv);
    fprintf(stdout,"Start recv                                  -- %d\n",i_req.d_phystats->ast_startrecv);
    fprintf(stdout,"Flush rec                                   -- %d\n",i_req.d_phystats->ast_flushrecv);
    fprintf(stdout,"Channel changes                             -- %d\n",i_req.d_phystats->ast_chanchange);
    fprintf(stdout,"Number of fast channel changes              -- %d\n",i_req.d_phystats->ast_fastcc);
    fprintf(stdout,"Number of failed fast channel changes       -- %d\n",i_req.d_phystats->ast_fastcc_errs);
    fprintf(stdout,"Channel sets                                -- %d\n",i_req.d_phystats->ast_chanset);
    fprintf(stdout,"CWM - mac mode switch                       -- %d\n",i_req.d_phystats->ast_cwm_mac);
    fprintf(stdout,"CWM - phy mode switch                       -- %d\n",i_req.d_phystats->ast_cwm_phy);
    fprintf(stdout,"CWM - requeue dest node packets             -- %d\n",i_req.d_phystats->ast_cwm_requeue);
    fprintf(stdout,"Pre-delimiter crc errors                    -- %d\n",i_req.d_phystats->ast_rx_delim_pre_crcerr);
    fprintf(stdout,"Post-delimiter crc errors                   -- %d\n",i_req.d_phystats->ast_rx_delim_post_crcerr);
    fprintf(stdout,"Decrypt busy errors                         -- %d\n",i_req.d_phystats->ast_rx_decrypt_busyerr);
    /*
     *  11n tx/rx stats
     */
    fprintf(stdout,"Total tx data packets                       -- %d\n",i_req.d_phystats->ast_11n.tx_pkts);
    fprintf(stdout,"Tx drops in wrong stat                      -- %d\n",i_req.d_phystats->ast_11n.tx_checks);
    fprintf(stdout,"Tx drops due to qdepth limit                -- %d\n",i_req.d_phystats->ast_11n.tx_drops);
    fprintf(stdout,"Tx when h/w queue depth is low              -- %d\n",i_req.d_phystats->ast_11n.tx_minqdepth);
    fprintf(stdout,"Tx pkts when h/w queue is busy              -- %d\n",i_req.d_phystats->ast_11n.tx_queue);
    fprintf(stdout,"Tx completions                              -- %d\n",i_req.d_phystats->ast_11n.tx_comps);
    fprintf(stdout,"Tx pkts filtered for requeueing             -- %d\n",i_req.d_phystats->ast_11n.tx_stopfiltered);
    fprintf(stdout,"Txq empty occurences                        -- %d\n",i_req.d_phystats->ast_11n.tx_qnull);
    fprintf(stdout,"Tx no skbs for encapsulations               -- %d\n",i_req.d_phystats->ast_11n.tx_noskbs);
    fprintf(stdout,"Tx key setup failures                       -- %d\n",i_req.d_phystats->ast_11n.tx_badsetups);
    fprintf(stdout,"Tx no desc for legacy packets               -- %d\n",i_req.d_phystats->ast_11n.tx_schednone);
    fprintf(stdout,"Tx bars sent                                -- %d\n",i_req.d_phystats->ast_11n.tx_bars);
    fprintf(stdout,"Tx bars excessively retried                 -- %d\n",i_req.d_phystats->ast_11n.txbar_xretry);
    fprintf(stdout,"Tx bars retried                             -- %d\n",i_req.d_phystats->ast_11n.txbar_compretries);
    fprintf(stdout,"Tx bars last frame failed                   -- %d\n",i_req.d_phystats->ast_11n.txbar_errlast);
    fprintf(stdout,"Tx unaggregated frame completions           -- %d\n",i_req.d_phystats->ast_11n.tx_compunaggr);
    fprintf(stdout,"Tx unaggregated excessive retries           -- %d\n",i_req.d_phystats->ast_11n.txunaggr_xretry);
    fprintf(stdout,"Tx aggregated completions                   -- %d\n",i_req.d_phystats->ast_11n.tx_compaggr);
    fprintf(stdout,"Tx block ack window advanced                -- %d\n",i_req.d_phystats->ast_11n.tx_bawadv);
    fprintf(stdout,"Tx block ack window retries                 -- %d\n",i_req.d_phystats->ast_11n.tx_bawretries);
    fprintf(stdout,"Tx block ack window additions               -- %d\n",i_req.d_phystats->ast_11n.tx_bawnorm);
    fprintf(stdout,"Tx block ack window updates                 -- %d\n",i_req.d_phystats->ast_11n.tx_bawupdates);
    fprintf(stdout,"Tx block ack window advances                -- %d\n",i_req.d_phystats->ast_11n.tx_bawupdtadv);
    fprintf(stdout,"Tx retries of sub frames                    -- %d\n",i_req.d_phystats->ast_11n.tx_retries);
    fprintf(stdout,"Tx excessive retries of aggregates          -- %d\n",i_req.d_phystats->ast_11n.tx_xretries);
    fprintf(stdout,"Tx no skbs for aggr encapsualtion           -- %d\n",i_req.d_phystats->ast_11n.txaggr_noskbs);
    fprintf(stdout,"Tx enc key setup failures                   -- %d\n",i_req.d_phystats->ast_11n.txaggr_badkeys);
    fprintf(stdout,"Tx no frame scheduled: baw limited          -- %d\n",i_req.d_phystats->ast_11n.txaggr_schedwindow);
    fprintf(stdout,"Tx frames not aggregated                    -- %d\n",i_req.d_phystats->ast_11n.txaggr_single);
    fprintf(stdout,"Tx aggr good completions                    -- %d\n",i_req.d_phystats->ast_11n.txaggr_compgood);
    fprintf(stdout,"Tx aggr excessive retries                   -- %d\n",i_req.d_phystats->ast_11n.txaggr_compxretry);
    fprintf(stdout,"Tx aggr unacked subframes                   -- %d\n",i_req.d_phystats->ast_11n.txaggr_compretries);
    fprintf(stdout,"Tx non-aggr unacked subframes               -- %d\n",i_req.d_phystats->ast_11n.txunaggr_compretries);
    fprintf(stdout,"Tx aggr old frames requeued                 -- %d\n",i_req.d_phystats->ast_11n.txaggr_prepends);
    fprintf(stdout,"Filtered aggr packet                        -- %d\n",i_req.d_phystats->ast_11n.txaggr_filtered);
    fprintf(stdout,"Fifo underrun of aggregate                  -- %d\n",i_req.d_phystats->ast_11n.txaggr_fifo);
    fprintf(stdout,"Txop exceeded for an aggregate              -- %d\n",i_req.d_phystats->ast_11n.txaggr_xtxop);
    fprintf(stdout,"Aggregate descriptor config error           -- %d\n",i_req.d_phystats->ast_11n.txaggr_desc_cfgerr);
    fprintf(stdout,"Data underrun for an aggregate              -- %d\n",i_req.d_phystats->ast_11n.txaggr_data_urun);
    fprintf(stdout,"Delimiter underrun for an aggregate         -- %d\n",i_req.d_phystats->ast_11n.txaggr_delim_urun);
    fprintf(stdout,"Tx aggr: last sub-frame failed              -- %d\n",i_req.d_phystats->ast_11n.txaggr_errlast);
    fprintf(stdout,"Tx aggr h/w long retries                    -- %d\n",i_req.d_phystats->ast_11n.txaggr_longretries);
    fprintf(stdout,"Tx aggr h/w short retries                   -- %d\n",i_req.d_phystats->ast_11n.txaggr_shortretries);
    fprintf(stdout,"Tx aggr : tx timer expired                  -- %d\n",i_req.d_phystats->ast_11n.txaggr_timer_exp);
    fprintf(stdout,"Tx aggr : BA bug                            -- %d\n",i_req.d_phystats->ast_11n.txaggr_babug);
    fprintf(stdout,"Rx pkts                                     -- %d\n",i_req.d_phystats->ast_11n.rx_pkts);
    fprintf(stdout,"Rx aggregated packets                       -- %d\n",i_req.d_phystats->ast_11n.rx_aggr);
    fprintf(stdout,"Rx pkts with bad version                    -- %d\n",i_req.d_phystats->ast_11n.rx_aggrbadver);
    fprintf(stdout,"Rx bars                                     -- %d\n",i_req.d_phystats->ast_11n.rx_bars);
    fprintf(stdout,"Rx non qos-data frames                      -- %d\n",i_req.d_phystats->ast_11n.rx_nonqos);
    fprintf(stdout,"Rx sequence resets                          -- %d\n",i_req.d_phystats->ast_11n.rx_seqreset);
    fprintf(stdout,"Rx old packets                              -- %d\n",i_req.d_phystats->ast_11n.rx_oldseq);
    fprintf(stdout,"Rx block ack window reset                   -- %d\n",i_req.d_phystats->ast_11n.rx_bareset);
    fprintf(stdout,"Rx pts indicated due to baw resets          -- %d\n",i_req.d_phystats->ast_11n.rx_baresetpkts);
    fprintf(stdout,"Rx duplicate pkts                           -- %d\n",i_req.d_phystats->ast_11n.rx_dup);
    fprintf(stdout,"Rx block ack window advanced                -- %d\n",i_req.d_phystats->ast_11n.rx_baadvance);
    fprintf(stdout,"Rx pkt completions                          -- %d\n",i_req.d_phystats->ast_11n.rx_recvcomp);
    fprintf(stdout,"Rx bar discarded                            -- %d\n",i_req.d_phystats->ast_11n.rx_bardiscard);
    fprintf(stdout,"Rx pkt completions on bar reception         -- %d\n",i_req.d_phystats->ast_11n.rx_barcomps);
    fprintf(stdout,"Rx pkt completions on bar reception         -- %d\n",i_req.d_phystats->ast_11n.rx_barrecvs);
    fprintf(stdout,"Rx pkt sequences skipped on timeout         -- %d\n",i_req.d_phystats->ast_11n.rx_skipped);
    fprintf(stdout,"Rx indications due to timeout               -- %d\n",i_req.d_phystats->ast_11n.rx_comp_to);
    fprintf(stdout,"Watchdog: tx is active                      -- %d\n",i_req.d_phystats->ast_11n.wd_tx_active);
    fprintf(stdout,"Watchdog: tx is not active              -- %d\n",i_req.d_phystats->ast_11n.wd_tx_inactive);
    fprintf(stdout,"Watchdog: tx is hung                        -- %d\n",i_req.d_phystats->ast_11n.wd_tx_hung);
    fprintf(stdout,"Watchdog: spurious tx hang                  -- %d\n",i_req.d_phystats->ast_11n.wd_spurious);
    fprintf(stdout,"Filter & requeue on 20/40 transitions       -- %d\n",i_req.d_phystats->ast_11n.tx_requeue);
    fprintf(stdout,"Draining tx queue on error                  -- %d\n",i_req.d_phystats->ast_11n.tx_drain_txq);
    fprintf(stdout,"Draining tid buf queue on error             -- %d\n",i_req.d_phystats->ast_11n.tx_drain_tid);
    fprintf(stdout,"Buffers drained from pending tid queue      -- %d\n",i_req.d_phystats->ast_11n.tx_drain_bufs);
    fprintf(stdout,"Pausing tx on tid                           -- %d\n",i_req.d_phystats->ast_11n.tx_tidpaused);
    fprintf(stdout,"Resuming tx on tid                          -- %d\n",i_req.d_phystats->ast_11n.tx_tidresumed);
    fprintf(stdout,"Unaggregated tx pkts filtered               -- %d\n",i_req.d_phystats->ast_11n.tx_unaggr_filtered);
    fprintf(stdout,"Aggregated tx pkts filtered                 -- %d\n",i_req.d_phystats->ast_11n.tx_aggr_filtered);
    fprintf(stdout,"Total sub-frames filtered                   -- %d\n",i_req.d_phystats->ast_11n.tx_filtered);
    fprintf(stdout,"Tr head                                     -- %d\n",i_req.d_phystats->ast_trc.tr_head);
    fprintf(stdout,"Tr tail                     -- %d\n",i_req.d_phystats->ast_trc.tr_tail);
    for(i = 1; i <= ATHCFG_WCMD_NUM_TR_ENTS; i++) {
        fprintf(stdout,"starting sequence of aggr - [%d]        -- %d\n",i,i_req.d_phystats->ast_trc.tr_ents[i].tre_seqst);
        fprintf(stdout,"starting sequence of ba   - [%d]        -- %d\n",i,i_req.d_phystats->ast_trc.tr_ents[i].tre_baseqst);
        fprintf(stdout,"packets in aggregate      - [%d]        -- %d\n",i,i_req.d_phystats->ast_trc.tr_ents[i].tre_npkts);
        fprintf(stdout,"aggregation length        - [%d[        -- %d\n",i,i_req.d_phystats->ast_trc.tr_ents[i].tre_aggrlen);
        fprintf(stdout,"block ack bitmap word 0   - [%d]        -- %d\n",i,i_req.d_phystats->ast_trc.tr_ents[i].tre_bamap0);
        fprintf(stdout,"block ack bitmap word 1   - [%d]        -- %d\n",i,i_req.d_phystats->ast_trc.tr_ents[i].tre_bamap1);
    }

    if( phystats )
        free(phystats);

    return 0;
}

/** 
 * @brief Handlers for GET ATH-DIALOG.
 */ 
int
athcfg_athdiag_get(char *ifrn_name, int nargs, char *args[])
{   
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_diag_t devdiag; 
    memset(&devdiag, 0, sizeof(athcfg_wcmd_diag_t));
    args[0] = NULL;
    
    assert (nargs == 0);
    
    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_DIALOG;
    i_req.d_diag = devdiag;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout,"Diag-Name -- %s\nDiag-ID -- %d\nDiag-In-Size -- %d\nDiag-In-Data -- %s\nDiag-Out-Data -- %s\nDiag-Out-Size %d\n",\
            i_req.d_diag.ad_name,i_req.d_diag.ad_id,i_req.d_diag.ad_in_size,i_req.d_diag.ad_in_data,i_req.d_diag.ad_out_data,\
            i_req.d_diag.ad_out_size);

    return 0;
}


/** 
 * @brief Handlers for GET PHY-ERR.
 */ 
int
athcfg_phyerr_get(char *ifrn_name, int nargs, char *args[])
{   
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_phyerr_t phyerr; 
    memset(&phyerr, 0, sizeof(athcfg_wcmd_phyerr_t));
    args[0] = NULL; 
    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_PHYERR;
    i_req.d_phyerr = phyerr;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout,"Phyerr-Name -- %s\nPhyerr-ID -- %d\nPhyerr-In-Size -- %d\nPhyerr-In-Data -- %s\nPhyerr-Out-Data -- %s\nPhyerr-Out-Size -- %d\n",\
            i_req.d_phyerr.ad_name,i_req.d_phyerr.ad_id,i_req.d_phyerr.ad_in_size,i_req.d_phyerr.ad_in_data,i_req.d_phyerr.ad_out_data,\
            i_req.d_phyerr.ad_out_size);

    return 0;
}

/** 
 * @brief Handlers for GET ATH-CWM.
 */
int
athcfg_athcwm_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_cwm_t athcwm; 
    memset(&athcwm, 0, sizeof(athcfg_wcmd_cwm_t));

    athcfg_wcmd_cwmdbg_t cwmdbg;
    memset(&cwmdbg, 0, sizeof(athcfg_wcmd_cwmdbg_t));
    athcfg_wcmd_cwminfo_t cwminfo;
    memset(&cwminfo, 0, sizeof(athcfg_wcmd_cwminfo_t));

    assert (nargs > 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    
    if( !(strcmp(args[0], "cwm-info")) ) {
        athcwm.type = ATHCFG_WCMD_CWMTYPE_INFO;
        athcwm.cwm_info = (athcfg_wcmd_cwminfo_t)cwminfo;
    }
    else if ( !(strcmp(args[0],"cwm-dbg")) ) {
        athcwm.type = ATHCFG_WCMD_CWMTYPE_DBG;

        if( !(strcmp(args[1],"cwm-event")) ) {
            cwmdbg.dc_cmd = ATHCFG_WCMD_CWM_CMD_EVENT;

            if( !(strcmp(args[2], "tx-timeout")) )
                cwmdbg.dc_arg = ATHCFG_WCMD_CWMEVENT_TXTIMEOUT;


            else if( !(strcmp(args[2], "ext-chclear")) )
                cwmdbg.dc_arg = ATHCFG_WCMD_CWMEVENT_EXTCHCLEAR;

            else if( !(strcmp(args[2], "ext-chbusy")) )
                cwmdbg.dc_arg = ATHCFG_WCMD_CWMEVENT_EXTCHBUSY;

            else if( !(strcmp(args[2], "extchstop")) )
                cwmdbg.dc_arg = ATHCFG_WCMD_CWMEVENT_EXTCHSTOP;

            else if( !(strcmp(args[2], "extchresume")) )
                cwmdbg.dc_arg = ATHCFG_WCMD_CWMEVENT_EXTCHRESUME;

            else if( !(strcmp(args[2], "destcw20")) )
                cwmdbg.dc_arg = ATHCFG_WCMD_CWMEVENT_DESTCW20;

            else if( !(strcmp(args[2], "destcw40")) )
                cwmdbg.dc_arg = ATHCFG_WCMD_CWMEVENT_DESTCW40;

            else {
                printf("!! ERROR !!\n");
                printf("Choose one from the list below --\n");
                printf("                                 txtimeout\n");
                printf("                                 extchclear\n");
                printf("                                 extchbusy\n");
                printf("                                 extchstop\n");
                printf("                                 extchresume\n");
                printf("                                 destcw20\n");
                printf("                                 destcw40\n");
            }

        }
        else if( !(strcmp(args[1], "cwm-ctl")) ) {
            cwmdbg.dc_cmd = ATHCFG_WCMD_CWM_CMD_CTL;

            if( !(strcmp(args[2], "enable" )) )
            cwmdbg.dc_arg = 1;

            else if( !(strcmp(args[2], "disable")) )
            cwmdbg.dc_arg = 0;

            else {
            printf(" !! ERROR !!\n");
            printf("Choose one from the list below --\n");
            printf("                                 enable\n"); 
            printf("                                 disable\n");
            }
            athcwm.cwm_dbg = (athcfg_wcmd_cwmdbg_t)cwmdbg;      
        }
        
        else if( !(strcmp(args[1], "cwm-ext")) ) {
            cwmdbg.dc_cmd = ATHCFG_WCMD_CWM_CMD_EXT;

            if( !(strcmp(args[2], "enable" )) )
            cwmdbg.dc_arg = 1;

            else if( !(strcmp(args[2], "disable")) )
            cwmdbg.dc_arg = 0;
        
            else {
            printf(" !! ERROR !!\n");
            printf("Choose one from the list below --\n");
            printf("                                 enable\n"); 
            printf("                                 disable\n");
            }
            athcwm.cwm_dbg = (athcfg_wcmd_cwmdbg_t)cwmdbg;      
        }

        else if( !(strcmp(args[1], "cwm-vctl")) ) {
            cwmdbg.dc_cmd = ATHCFG_WCMD_CWM_CMD_VCTL;

            if( !(strcmp(args[2], "enable" )) )
            cwmdbg.dc_arg = 1;

            else if( !(strcmp(args[2], "disable")) )
            cwmdbg.dc_arg = 0;

            else {
            printf(" !! ERROR !!\n");
            printf("Choose one from the list below --\n");
            printf("                                 enable\n"); 
            printf("                                 disable\n");
            }
            athcwm.cwm_dbg = (athcfg_wcmd_cwmdbg_t)cwmdbg;      
        }

        else if( !(strcmp(args[1], "cwm-vext")) ) {                     
            cwmdbg.dc_cmd = ATHCFG_WCMD_CWM_CMD_VEXT;

            if( !(strcmp(args[2], "enable" )) )
            cwmdbg.dc_arg = 1;

            else if( !(strcmp(args[2], "disable")) )
            cwmdbg.dc_arg = 0;

            else {
            printf(" !! ERROR !!\n");
            printf("Choose one from the list below --\n");
            printf("                                 enable\n"); 
            printf("                                 disable\n");
            }
            athcwm.cwm_dbg = (athcfg_wcmd_cwmdbg_t)cwmdbg;      
        }

        else {
            printf(" !! ERROR !!\n");
            printf("Choose one from the list below --\n");
            printf("                                 cwm-ctl\n");
            printf("                                 cwm-ext\n");
            printf("                                 cwm-vctl\n");
            printf("                                 cwm-vext\n");
        }
    
    }
    else {
            printf(" !!ERROR !!\n");
            printf("Choose one from the list below --\n");
            printf("                                 cwm-info\n");
            printf("                                 cwm-dbg\n");
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_CWM;
    i_req.d_cwm = athcwm;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    if(i_req.d_cwm.type == ATHCFG_WCMD_CWMTYPE_INFO){
        printf("CHAN-WIDTH -- %d\n",i_req.d_cwm.cwm_info.ci_chwidth);
        printf("MAC-MODE -- %d\n",i_req.d_cwm.cwm_info.ci_macmode);
        printf("PHY-MODE -- %d\n",i_req.d_cwm.cwm_info.ci_phymode);
        printf("EXT-BUSY-PER -- %d\n",i_req.d_cwm.cwm_info.ci_extbusyper);
    }

    if(i_req.d_cwm.type == ATHCFG_WCMD_CWMTYPE_DBG) {
       if(i_req.d_cwm.cwm_dbg.dc_cmd ==  ATHCFG_WCMD_CWM_CMD_EVENT)
           printf("DBG-CMD -- Send event\n");

       else if(i_req.d_cwm.cwm_dbg.dc_cmd ==  ATHCFG_WCMD_CWM_CMD_CTL)
           printf("DBG-CMD -- Control channel busy\n");

       else if(i_req.d_cwm.cwm_dbg.dc_cmd ==  ATHCFG_WCMD_CWM_CMD_EXT)
           printf("DBG-CMD -- Extension channel busy\n");

       else if(i_req.d_cwm.cwm_dbg.dc_cmd ==  ATHCFG_WCMD_CWM_CMD_VCTL)
           printf("DBG-CMD -- Virtual control channel busy\n");

       else if(i_req.d_cwm.cwm_dbg.dc_cmd ==  ATHCFG_WCMD_CWM_CMD_VEXT)
           printf("DBG-CMD -- Virtual extension channel busy\n");

       else {
            printf("Illegal value retured by driver --\n");
            return 0;
       }            

       if(i_req.d_cwm.cwm_dbg.dc_arg == ATHCFG_WCMD_CWMEVENT_TXTIMEOUT)
            printf("DBG-ARG -- Tx timeout interrupt\n");

       if(i_req.d_cwm.cwm_dbg.dc_arg == ATHCFG_WCMD_CWMEVENT_EXTCHCLEAR)
            printf("DBG-ARG -- Extension channel sensing clear\n");
         
       if(i_req.d_cwm.cwm_dbg.dc_arg == ATHCFG_WCMD_CWMEVENT_EXTCHBUSY)
            printf("DBG-ARG -- Extension channel sensing busy\n");
        
       if(i_req.d_cwm.cwm_dbg.dc_arg == ATHCFG_WCMD_CWMEVENT_EXTCHRESUME)
            printf("DBG-ARG -- Extension channel sensing resume\n");

       if(i_req.d_cwm.cwm_dbg.dc_arg == ATHCFG_WCMD_CWMEVENT_DESTCW20)
            printf("DBG-ARG -- Channel width changes to 40\n");

       if(i_req.d_cwm.cwm_dbg.dc_arg == ATHCFG_WCMD_CWMEVENT_DESTCW40)
            printf("DBG-ARG -- Channel width changed to 40\n");

       if(i_req.d_cwm.cwm_dbg.dc_arg == ATHCFG_WCMD_CWMEVENT_MAX)
            printf("DBG-ARG -- Max event\n");
        
       else {
            printf("Illegal value retured by driver --\n");    
            return 0;
       }            
    }   

    return 0;
}

/** 
 * @brief Handlers for GET ETHTOOL-INFO.
 */
int
athcfg_ethtool_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    args[0] = NULL;
    assert (nargs == 0); 

    athcfg_wcmd_ethtool_info_t ethtool ;

    memset(&ethtool, 0, sizeof(athcfg_wcmd_ethtool_info_t));

    ethtool.cmd = 3;/* hard code as per the driver needed, as it can process
            only one cmd for now (3) */

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_ETHTOOL;
    i_req.d_ethtool = ethtool;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout,"Ethtool-cmd -- %d\nEthtool-Driver -- %s\nEthtool-Version -- %s\nEthtool-Firmeare-ver -- %s\n",i_req.d_ethtool.drv.cmd,\
                         i_req.d_ethtool.drv.driver, i_req.d_ethtool.drv.version, i_req.d_ethtool.drv.fw_version);

    return 0;
}

/**
 * @brief Handler for SET VAP-CREATE.
 */
int
athcfg_vapcreate_set(char *ifrn_name, int nargs, char *args[])
{   
    athcfg_wcmd_t i_req; 

    athcfg_wcmd_vapinfo_t *vapcreate; 
    
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    vapcreate = &i_req.d_vapinfo;
    
    assert (nargs >= 1); 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    if( (strlen(args[0]) <=  ATHCFG_WCMD_NAME_SIZE) )
        strcpy((char *)vapcreate->icp_name, args[0]);

    else 
        ABORT_ARG_SIZE(ifrn_name, "VAP-Name", ATHCFG_WCMD_NAME_SIZE);

    if (!(strcmp (args[1], "infra-sta") ))
        vapcreate->icp_opmode = ATHCFG_WCMD_OPMODE_STA;

    else if (!(strcmp (args[1], "ibss-adhoc") ))
        vapcreate->icp_opmode = ATHCFG_WCMD_OPMODE_IBSS;

    else if (!(strcmp (args[1], "ahdemo") ))
        vapcreate->icp_opmode = ATHCFG_WCMD_OPMODE_AHDEMO;

    else if (!(strcmp (args[1], "hostap") ))
        vapcreate->icp_opmode = ATHCFG_WCMD_OPMODE_HOSTAP;

    else if (!(strcmp (args[1], "monitor") ))
        vapcreate->icp_opmode = ATHCFG_WCMD_OPMODE_MONITOR;

    else if (!(strcmp (args[1], "wds") ))
        vapcreate->icp_opmode = ATHCFG_WCMD_OPMODE_WDS;

    else {
        printf("!! ERROR !!\n");
        printf("Choose one from the list below --> \n");
        printf("                     infra-sta \n");
        printf("                     ibss-adhoc \n");
        printf("                     ahdemo \n");
        printf("                     hostap \n");
        printf("                     monitor \n");
        printf("                     wds \n");
        return 0;
    }       
    vapcreate->icp_flags = atoi(args[2]);
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_DEV_VAP_CREATE;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "VAP-Name -- %s\n", i_req.d_vapinfo.icp_name);

    return 0;
}

/** 
 * @brief Handlers for Get STATUS.
 */ 
int
athcfg_stats_get(char *ifrn_name, int nargs, char *args[])
{   
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    args[0] = NULL; 
    assert (nargs == 0);

    athcfg_wcmd_devstats_t *stats; 
    memset(&stats, 0, sizeof(athcfg_wcmd_devstats_t));

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

     if( (stats = malloc(sizeof(athcfg_wcmd_devstats_t)) ) == NULL )
        ERR_MALLOC_FAIL("devstats");
    
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEVSTATS; 
    i_req.d_devstats = stats;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0) {
        free(stats);
        return EIO;
    }

    printf("STATUS for Interface [%s] are as below ---------------\n",ifrn_name);
    fprintf(stdout,"Total packets received                  -- %llu\n",i_req.d_devstats->rx_packets);
    fprintf(stdout,"Total packets transmitted                   -- %llu\n",i_req.d_devstats->tx_packets);
    fprintf(stdout,"Total bytes received                        -- %llu\n",i_req.d_devstats->rx_bytes);
    fprintf(stdout,"Total bytes transmitted                     -- %llu\n",i_req.d_devstats->tx_bytes);
    fprintf(stdout,"Bad packets received                        -- %llu\n",i_req.d_devstats->rx_errors);
    fprintf(stdout,"Packet transmit problems                    -- %llu\n",i_req.d_devstats->tx_errors);
    fprintf(stdout,"No space in linux buffers                   -- %llu\n",i_req.d_devstats->rx_dropped);
    fprintf(stdout,"No space available in linux                 -- %llu\n",i_req.d_devstats->tx_dropped);
    fprintf(stdout,"Multicast packets received                  -- %llu\n",i_req.d_devstats->multicast);
    fprintf(stdout,"No of collisions                            -- %llu\n",i_req.d_devstats->collisions);
    fprintf(stdout,"Rx length errors                            -- %llu\n",i_req.d_devstats->rx_length_errors);
    fprintf(stdout,"Receiver ring buff overflow                 -- %llu\n",i_req.d_devstats->rx_over_errors);
    fprintf(stdout,"Recved pkt with crc error                   -- %llu\n",i_req.d_devstats->rx_crc_errors);
    fprintf(stdout,"Recv'd frame alignment error                -- %llu\n",i_req.d_devstats->rx_frame_errors);
    fprintf(stdout,"Recv'r fifo overrun                         -- %llu\n",i_req.d_devstats->rx_fifo_errors);
    fprintf(stdout,"Receiver missed packet                      -- %llu\n",i_req.d_devstats->rx_missed_errors);
    fprintf(stdout,"No of tx abborted                           -- %llu\n",i_req.d_devstats->tx_aborted_errors);
    fprintf(stdout,"No of tx carrier error                      -- %llu\n",i_req.d_devstats->tx_carrier_errors);
    fprintf(stdout,"Tx fifo overrun                             -- %llu\n",i_req.d_devstats->tx_fifo_errors);
    fprintf(stdout,"Tx heart beat errors                        -- %llu\n",i_req.d_devstats->tx_heartbeat_errors);
    fprintf(stdout,"Tx window errors                            -- %llu\n",i_req.d_devstats->tx_window_errors);
    fprintf(stdout,"Rx compressed                               -- %llu\n",i_req.d_devstats->rx_compressed);
    fprintf(stdout,"Tx compressed                               -- %llu\n",i_req.d_devstats->tx_compressed);
    
    if(stats)
        free(stats);

    return 0;
}


/**
 * @brief Handlers for Set ATH-MAC.
 */
int
athcfg_athmac_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    int tmp[6];
    int count;
    athcfg_ethaddr_t athmac; 
        
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    memset(&athmac, 0, sizeof(athcfg_ethaddr_t));
    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    
    if( (strlen(args[0]) ==  ATHCFG_WCMD_MAC_STR_LEN) ) {
        sscanf(args[0],"%x:%x:%x:%x:%x:%x", 
                 (unsigned int *)&tmp[0],\
                 (unsigned int *)&tmp[1],\
                 (unsigned int *)&tmp[2],\
                 (unsigned int *)&tmp[3],\
                 (unsigned int *)&tmp[4],\
                 (unsigned int *)&tmp[5] );            
    }   
    else {
        printf("!! ERROR !!\n");
        printf("Please enter Mac in format of -- 00:11:22:33:44:55\n");
        ABORT_ARG_SIZE(ifrn_name, "DELMAC", ATHCFG_WCMD_ADDR_LEN);
    }
    
    for(count=0; count < ATHCFG_ETH_LEN; count++)
        athmac.addr[count] = tmp[count];    
        
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_DEV_MAC;
    i_req.d_mac = athmac;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    return 0;
}

/**
 * @brief Handlers for Set MTU.
 */
int
athcfg_mtu_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_mtu_t mtu ;
    memset(&mtu, 0, sizeof(athcfg_wcmd_mtu_t));

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    mtu = atoi(args[0]);
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_MTU;
    i_req.d_mtu = mtu;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "Mtu -- %d\n", i_req.d_mtu);

    return 0;
}

/**
 * @brief Handlers for Set TX-TIMEOUT.
 */
int 
athcfg_txtimeout_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    assert (nargs == 1);
    args[0] = NULL;

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_DEV_TX_TIMEOUT;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    return 0;
}

/**
 * @brief Handlers for Set MODE-INIT.
 */
int
athcfg_modeinit_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    assert (nargs == 0);
    args[0] = NULL;

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_DEV_MODE_INIT;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    return 0;
}

/**
 * @brief Handlers for Get STATS-CLEAR.
 */
int
athcfg_statsclear_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    assert (nargs == 0);
    args[0] = NULL;

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_STATUS_CLR;
 
    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    return 0;
}

/**
 * @brief Handlers for Get RANGE..
 */
int
athcfg_getrange_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    struct athcfg_wcmd_vapparam_range *getrange = NULL; 

    assert (nargs == 0);
    args[0] = NULL;  
    int i = 0;
    
    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    if( (getrange = malloc(sizeof(athcfg_wcmd_vapparam_range_t)) ) == NULL )
        ERR_MALLOC_FAIL("range");

    memset(getrange, 0, sizeof(athcfg_wcmd_vapparam_range_t));     
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_RANGE;
    i_req.d_range = getrange;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0) {
        free(getrange);
        return EIO;
    }

    printf("Range value for the interface [%s] is as below ------------",ifrn_name);
    fprintf(stdout, "Range-Throughtput -- %d\n", i_req.d_range->throughput);
    fprintf(stdout, "Range-Min-Nwid -- %d\n", i_req.d_range->min_nwid);                                             
    fprintf(stdout, "Range-Max-Nwid -- %d\n", i_req.d_range->max_nwid);                                             
    fprintf(stdout, "Range-Old-Num-Channels -- %d\n", i_req.d_range->old_num_channels);                                             
    fprintf(stdout, "Range-Old-Num-Frequency -- %d\n", i_req.d_range->old_num_frequency);                                             
    fprintf(stdout, "Range-Event-Capability --%s\n", (char *)i_req.d_range->event_capa);                                             
    fprintf(stdout, "Range-Sensitivity -- %d\n", i_req.d_range->sensitivity);
    fprintf(stdout, "Range-Num-Bit-Rates%d\n", i_req.d_range->num_bitrates);
    fprintf(stdout, "Range-Bit-Rates%s\n", (char *)i_req.d_range->bitrate);
    fprintf(stdout, "Rnage-Min-Rts%d\n", i_req.d_range->min_rts);
    fprintf(stdout, "Range-Max-Rts -- %d\n", i_req.d_range->max_rts);                                             
    fprintf(stdout, "Range-Min-Frag -- %d\n", i_req.d_range->min_frag);
    fprintf(stdout, "Range-Max-Frag -- %d\n", i_req.d_range->max_frag);
    fprintf(stdout, "Range-Min-Pmp -- %d\n", i_req.d_range->min_pmp);
    fprintf(stdout, "Range-Max-Pmp -- %d\n", i_req.d_range->max_pmp);
    fprintf(stdout, "Range-Min-Pmt -- %d\n", i_req.d_range->min_pmt);                                             
    fprintf(stdout, "Range-max-Pmt -- %d\n", i_req.d_range->max_pmt);
    fprintf(stdout, "Range-Pmp-Flags -- %d\n", i_req.d_range->pmp_flags);
    fprintf(stdout, "Range-Pmt-Flags -- %d\n", i_req.d_range->pmt_flags);                                             
    fprintf(stdout, "Range-Pm-Capability -- %d\n", i_req.d_range->pm_capa);
    fprintf(stdout, "Range-Encoding-Size -- %s\n", (char *)i_req.d_range->enc_sz);
    fprintf(stdout, "Ranfe-Num-Encoding-sizes -- %d\n", i_req.d_range->num_enc_sz);
    fprintf(stdout, "Range-Max-Encoding-Tokens -- %d\n", i_req.d_range->max_enc_tk);
    fprintf(stdout, "Range-Encoding-Login-Index -- %d\n", i_req.d_range->enc_login_idx);                                             
    fprintf(stdout, "Range-Tx-Power-Capa. -- %d\n", i_req.d_range->txpower_capa);
    fprintf(stdout, "Range-Num-Tx-Power -- %d\n", i_req.d_range->num_txpower);
    fprintf(stdout, "Range-Tx-Power -- %s\n", (char *)i_req.d_range->txpower);
    fprintf(stdout, "Range-WE-Ver-Compiled -- %d\n", i_req.d_range->we_version_compiled);
    fprintf(stdout, "Range-WE-Ver-Source -- %d\n", i_req.d_range->we_version_source);                                             
    fprintf(stdout, "Range-Retry-Capa. -- %d\n", i_req.d_range->retry_capa);
    fprintf(stdout, "Range-Retry-Flags -- %d\n", i_req.d_range->retry_flags);
    fprintf(stdout, "Range-Retry-Time-Flags -- %d\n", i_req.d_range->r_time_flags);
    fprintf(stdout, "Range-Min-Retry -- %d\n", i_req.d_range->min_retry);
    fprintf(stdout, "Range-Max-Retry -- %d\n", i_req.d_range->max_retry);                                             
    fprintf(stdout, "Range-Min-Retry-Time -- %d\n", i_req.d_range->min_r_time);
    fprintf(stdout, "Range-Max-Retry-Time -- %d\n", i_req.d_range->max_r_time);
    fprintf(stdout, "Range-Num-Channels -- %d\n", i_req.d_range->num_channels);
    fprintf(stdout, "Range-Num-Frequency -- %d\n", i_req.d_range->num_frequency);
    fprintf(stdout, "Range-Encod-Capa. -- %d\n", i_req.d_range->enc_capa);
    fprintf(stdout, "Range-Max-Qual-Qual -- %d\n", i_req.d_range->max_qual.qual);
    fprintf(stdout, "Range-Max-Qual-Level -- %d\n", i_req.d_range->max_qual.level);
    fprintf(stdout, "Range-Max-Qual-Noise -- %d\n", i_req.d_range->max_qual.noise);
    fprintf(stdout, "Range-Max-Qual-Updated -- %d\n", i_req.d_range->max_qual.updated);                                             
    fprintf(stdout, "Range-Avg-Qual-Qual -- %d\n", i_req.d_range->avg_qual.qual);
    fprintf(stdout, "Range-Avg-Qual-Level -- %d\n", i_req.d_range->avg_qual.level);
    fprintf(stdout, "Range-Avg-Qual-Noise -- %d\n", i_req.d_range->avg_qual.noise);
    fprintf(stdout, "Range-Avg=Qual-Updated -- %d\n", i_req.d_range->avg_qual.updated);
    for(i = 0; i < ATHCFG_WCMD_MAX_FREQ; i++) {
    fprintf(stdout, "Range-Freq.M -- %d\n", i_req.d_range->freq[i].m);
        fprintf(stdout, "Range-Freq.E -- %d\n", i_req.d_range->freq[i].e);
    fprintf(stdout, "Range.Freq.I -- %d\n", i_req.d_range->freq[i].i);
        fprintf(stdout, "Range.Freq.Flags -- %d\n", i_req.d_range->freq[i].flags);
    }
    
    if(getrange)
    free(getrange);

    return 0;
}

/**
 * @brief Handlers for Get STATS-INFO.
 */
int
athcfg_statsinfo_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_vapstats_t vapstats; 

    memset(&vapstats, 0, sizeof(athcfg_wcmd_vapstats_t));

    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_VAP_STATS;
    i_req.d_vapstats = &vapstats;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    printf("STATS-INFO for the interface [%s] is as below ------------",ifrn_name);
    fprintf(stdout, "Statsinfo-Status -- %d\n", i_req.d_vapstats->status);
    fprintf(stdout, "Statsinfo-Qual-Qual -- %d\n", i_req.d_vapstats->qual.qual);
    fprintf(stdout, "Statsinfo-Qual-Level -- %d\n", i_req.d_vapstats->qual.level);
    fprintf(stdout, "Statsinfo-Qual-Noise -- %d\n", i_req.d_vapstats->qual.noise);
    fprintf(stdout, "Statsinfo-Qual-Updated -- %d\n", i_req.d_vapstats->qual.updated);
    fprintf(stdout, "Statsinfo-Discard-Nwid -- %d\n", i_req.d_vapstats->discard.nwid);
    fprintf(stdout, "Statsinfo-Discard-Code -- %d\n", i_req.d_vapstats->discard.code);
    fprintf(stdout, "Statsinfo-Discard-Fragment -- %d\n", i_req.d_vapstats->discard.fragment);
    fprintf(stdout, "Statsinfo-Discard.Retries -- %d\n", i_req.d_vapstats->discard.retries);
    fprintf(stdout, "Statsinfo-Discard-Misc -- %d\n", i_req.d_vapstats->discard.misc);
    fprintf(stdout, "Statsinfo-Miss-Beacon -- %d\n", i_req.d_vapstats->miss.beacon);

    return 0;
}

/**
 * @brief Handlers for Get STATION-STATS.
 */
int
athcfg_stastats_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_stastats_t stastat; 
    assert (nargs == 1);
    int count = 0;    
    
    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    if( (strlen(args[0]) ==  ATHCFG_WCMD_MAC_STR_LEN) ) {
        sscanf(args[0],"%x:%x:%x:%x:%x:%x",\
                (unsigned int *)&(stastat.mac.addr[0]),\
                (unsigned int *)&(stastat.mac.addr[1]),\
                (unsigned int *)&(stastat.mac.addr[2]),\
                (unsigned int *)&(stastat.mac.addr[3]),\
                (unsigned int *)&(stastat.mac.addr[4]),\
                (unsigned int *)&(stastat.mac.addr[5]) );
    }   
    else {
        printf("!! ERROR !!\n");
        printf("Please enter Mac in format of -- 00:11:22:33:44:55\n");
        ABORT_ARG_SIZE(ifrn_name, "STASTATS", ATHCFG_WCMD_ADDR_LEN);
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_STA_STATS;
    i_req.d_stastats = &stastat;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    printf("STATION-STATS for the Mac Address --  [");
    for(count = 0; count < 6; count++){
        fprintf(stdout, "%x", stastat.mac.addr[count]);
    if(count <5)
        printf(":");
    }
    printf("]\n");

    fprintf(stdout, "Stastats-Ns-Rx-Data -- %d\n", i_req.d_stastats->data.ns_rx_data);
    fprintf(stdout, "Stastats-Ns-Rx-Mgmt -- %d\n", i_req.d_stastats->data.ns_rx_mgmt);
    fprintf(stdout, "Stastats-Ns-Rx-Ctrl -- %d\n", i_req.d_stastats->data.ns_rx_ctrl);
    fprintf(stdout, "Stastats-Ns-Rx-Ucast -- %d\n", i_req.d_stastats->data.ns_rx_ucast);
    fprintf(stdout, "Stastats-Ns-Rx-Mcast -- %d\n", i_req.d_stastats->data.ns_rx_mcast);
    fprintf(stdout, "Stastats-Ns-Rx-Bytes -- %llu\n", i_req.d_stastats->data.ns_rx_bytes);
    fprintf(stdout, "Stastats-Ns-Rx-Beacon -- %llu\n", i_req.d_stastats->data.ns_rx_beacons);
    fprintf(stdout, "Stastats-Ns-Rx-Probresp -- %d\n", i_req.d_stastats->data.ns_rx_proberesp);
    fprintf(stdout, "Stastats-Ns-Rx-Dup --%d\n", i_req.d_stastats->data.ns_rx_dup);
    fprintf(stdout, "Stastats-Ns-Rx-Noprivacy -- %d\n", i_req.d_stastats->data.ns_rx_noprivacy);
    fprintf(stdout, "Stastats-Ns-Rx-Wepfail -- %d\n", i_req.d_stastats->data.ns_rx_wepfail);
    fprintf(stdout, "Stastats-Ns-Rx-Demicfail -- %d\n", i_req.d_stastats->data.ns_rx_demicfail);
    fprintf(stdout, "Stastats-Ns-Rx-Decap -- %d\n", i_req.d_stastats->data.ns_rx_decap);
    fprintf(stdout, "Stastats-Ns-Rx-Defrag -- %d\n", i_req.d_stastats->data.ns_rx_defrag);
    fprintf(stdout, "Stastats-Ns-Rx-Disassoc -- %d\n", i_req.d_stastats->data.ns_rx_disassoc);
    fprintf(stdout, "Stastats-Ns-Rx-Deauth -- %d\n", i_req.d_stastats->data.ns_rx_deauth);
    fprintf(stdout, "Stastats-Ns-Rx-Action -- %d\n", i_req.d_stastats->data.ns_rx_action);
    fprintf(stdout, "Stastats-Ns-Rx-Decrypt-CRC -- %d\n", i_req.d_stastats->data.ns_rx_decryptcrc);
    fprintf(stdout, "Stastats-Ns-Rx-Unaith -- %d\n", i_req.d_stastats->data.ns_rx_unauth);
    fprintf(stdout, "Stastats-Ns-Rx-Unencrypted -- %d\n", i_req.d_stastats->data.ns_rx_unencrypted);
    fprintf(stdout, "Stastats-Ns-Tx-Data -- %d\n", i_req.d_stastats->data.ns_tx_data);
    fprintf(stdout, "Stastats-Ns-Tx-Mgmt -- %d\n", i_req.d_stastats->data.ns_tx_mgmt);
    fprintf(stdout, "Stastats-Ns-Tx-Ucast -- %d\n", i_req.d_stastats->data.ns_tx_ucast);
    fprintf(stdout, "Stastats-Ns-Tx-Mcast --%d\n", i_req.d_stastats->data.ns_tx_mcast);
    fprintf(stdout, "Stastats-Ns-Tx-Bytes -- %llu\n", i_req.d_stastats->data.ns_tx_bytes);
    fprintf(stdout, "Stastats-Ns-Tx-Probresp -- %d\n", i_req.d_stastats->data.ns_tx_probereq);
    fprintf(stdout, "Stastats-Ns-Tx-Uapsd -- %d\n", i_req.d_stastats->data.ns_tx_uapsd);
    fprintf(stdout, "Stastats-Ns-Tx-Nonvlan-Tag -- %d\n", i_req.d_stastats->data.ns_tx_novlantag);
    fprintf(stdout, "Stastats-Ns-Tx-Vlanmis-Match -- %d\n", i_req.d_stastats->data.ns_tx_vlanmismatch);
    fprintf(stdout, "Stastats-Nx-Tx-Eosp-Lost -- %d\n", i_req.d_stastats->data.ns_tx_eosplost);
    fprintf(stdout, "Stastats-Nx-Tx-Ps-Discard -- %d\n", i_req.d_stastats->data.ns_ps_discard);
    fprintf(stdout, "Stastats-Nx-Tx-Uapsd-Trig -- %d\n", i_req.d_stastats->data.ns_uapsd_triggers);
    fprintf(stdout, "Stastats-Nx-Tx-Assoc -- %d\n", i_req.d_stastats->data.ns_tx_assoc);
    fprintf(stdout, "Stastats-Nx-Tx-Assoc-Fail -- %d\n", i_req.d_stastats->data.ns_tx_assoc_fail);
    fprintf(stdout, "Stastats-Nx-Tx-Auth -- %d\n", i_req.d_stastats->data.ns_tx_auth);
    fprintf(stdout, "Stastats-Nx-Tx-Auth-Fail -- %d\n", i_req.d_stastats->data.ns_tx_auth_fail);
    fprintf(stdout, "Stastats-Nx-Tx-Deauth -- %d\n", i_req.d_stastats->data.ns_tx_deauth);
    fprintf(stdout, "Stastats-Nx-Tx-Deauth-Code -- %d\n", i_req.d_stastats->data.ns_tx_deauth_code);
    fprintf(stdout, "Stastats-Nx-Tx-Disassoc -- %d\n", i_req.d_stastats->data.ns_tx_disassoc);
    fprintf(stdout, "Stastats-Nx-Tx-Disassoc-Code -- %d\n", i_req.d_stastats->data.ns_tx_disassoc_code);
    fprintf(stdout, "Stastats-Nx-Tx-Psq-Drops -- %d\n", i_req.d_stastats->data.ns_psq_drops);
   
    return 0;
}

/**
 * @brief Handlers for Get STATION-INFO. 
 */
int
athcfg_stainfo_get(char *ifrn_name, int nargs, char *args[])
{
    int count = 0;
    int mac_count = 0;
    int rate_count = 0;
    int ie_count = 0;
    int seq_count = 0;
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_stainfo_t stainfo; 
    memset(&stainfo, 0, sizeof(athcfg_wcmd_stainfo_t));
    assert (nargs == 0);
    args[0] = NULL;

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    stainfo.len = ATHCFG_WCMD_MAX_AP;
    i_req.type = ATHCFG_WCMD_GET_STATION_LIST;
    i_req.d_station = &stainfo;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;
    if(i_req.d_station->len)  {
        
        printf("STATION-INFO for the interface [%s] is as below ------------\n",ifrn_name);

    for(count = 0; count < i_req.d_station->len; count++) {
        printf("Station[%d] -- \n",count);
        printf("              Freq %d  ",i_req.d_station->list[count].isi_freq);
        printf("Flags %d  ",i_req.d_station->list[count].
                                                        isi_flags);
        printf("State %d  ",i_req.d_station->list[count].
                                                        isi_state);
        printf("Auth-mode %d  ",i_req.d_station->list[count].
                                                        isi_authmode);
        printf("Rssi %d\n",i_req.d_station->list[count].
                                                        isi_rssi);
        printf("              Capi-nfo %d",i_req.d_station->list[count].
                                                        isi_capinfo);
        printf(" Ath-flags %d  ",i_req.d_station->list[count].
                                                        isi_athflags);
        printf("Erp %d  ",i_req.d_station->list[count].
                                                        isi_erp);
        printf("Macaddr ");
            for(mac_count= 0; mac_count < 6; mac_count++){
                fprintf(stdout, "%02x", i_req.d_station->list[count]
                                  .isi_macaddr[mac_count]);
                if(mac_count <5)
                    printf(":");
            }
        printf("\n");            
       printf("              Nrates %d  ",i_req.d_station->list[count].
                                                        isi_nrates);
        printf("Rate ");
        for(rate_count = 0; rate_count < (strlen(i_req.d_scan->result[count].isr_rates)); rate_count++)
       printf("%d ",i_req.d_station->list[count].isi_rates[rate_count]); 
       printf("Tx-rate %d\n",i_req.d_station->list[count].
                                                        isi_txrate);
        printf("              Tx-rateKbps %d  ",i_req.d_station->list
                                                 [count].isi_txrateKbps);
        printf("Assoc-id %d  ",i_req.d_station->list[count].
                                                        isi_associd);
        printf("Tx-power %d  ",i_req.d_station->list[count].
                                                        isi_txpower);
        printf("Vlan %d\n",i_req.d_station->list[count].
                                                        isi_vlan);
        printf("              Tx-seqs ");
        for(seq_count = 0; seq_count < 17; seq_count++) {
            if(i_req.d_station->list[count].isi_txseqs[seq_count] )
                printf("%d",i_req.d_station->list[count].isi_txseqs[seq_count]);
        }                
        printf(" Rx-seqs ");
         for(seq_count = 0; seq_count < 17; seq_count++) {
            if(i_req.d_station->list[count].isi_rxseqs[seq_count] )
                printf("%d",i_req.d_station->list[count].isi_rxseqs[seq_count]);
        }                
       printf(" Inact %d  ",i_req.d_station->list[count].
                                                        isi_inact);
        printf("Upasd %d",i_req.d_station->list[count].
                                                        isi_uapsd);
        printf("Op-mode ");
        if( (i_req.d_station->list[count].isi_opmode) == 0)
            printf("IBSS  ");
        if( (i_req.d_station->list[count].isi_opmode) == 1)
            printf("Station  ");
        if( (i_req.d_station->list[count].isi_opmode) == 2)
            printf("WDS  ");
        if( (i_req.d_station->list[count].isi_opmode) == 3)
            printf("AHDEMO  ");
        if( (i_req.d_station->list[count].isi_opmode) == 6)
            printf("HOSTAP  ");
        if( (i_req.d_station->list[count].isi_opmode) == 8)
            printf("MONITOR  ");

        printf("\n              Chiper %d  ",i_req.d_station->list[count].
                                                        isi_cipher);
        printf("Assoc-time %d  ",i_req.d_station->
                                            list[count].isi_assoc_time);
        printf("Htcap %d\n",i_req.d_station->list[count].
                                                        isi_htcap);
        if(strlen(i_req.d_station->list[count].isi_wpa_ie) )  {
        printf("              WPA-IE ");
        for(ie_count = 0; ie_count < (i_req.d_station->list[count]
                               .isi_wpa_ie[1]); ie_count++)
                    printf("%x ",i_req.d_station->list[count].isi_wpa_ie[ie_count]);
           printf("\n");                                              
           }                               
        if(strlen(i_req.d_station->list[count].isi_wme_ie)  ) { 
        printf("              WME-IE ");
         for(ie_count = 0; ie_count < (i_req.d_station->list[count]
                               .isi_wme_ie[1]); ie_count++)
                    printf("%x ",i_req.d_station->list[count].isi_wme_ie[ie_count]);
           printf("\n");
           }                               
                            
         if(strlen(i_req.d_station->list[count].isi_ath_ie) )  {
         printf("             ATH-IE ");
         for(ie_count = 0; ie_count < (i_req.d_station->list[count]
                               .isi_ath_ie[1]); ie_count++)
                    printf("%x ",i_req.d_station->list[count].isi_ath_ie[ie_count]);
            }                               
            printf("\n");
        }            
    }
    else 
       printf("There is no station in scan list\n");

    return 0;
}

/**
 * @brief Handlers for GET/Set EIFS_MASK.
 */
int
athcfg_eifsmask_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_EIFS_MASK;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "EIFS-MASk -- %d\n", i_req.d_datum);
    return 0;
}

int
athcfg_eifsmask_set(char *ifrn_name, int nargs, char *args[])
{
     athcfg_wcmd_t i_req; 
     memset(&i_req,0, sizeof(athcfg_wcmd_t));

     assert (nargs == 1);
 
     if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
          fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
          return E2BIG;
     }
     i_req.d_datum = 0;

     i_req.d_datum = atoi(args[0]);
     strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
     i_req.type = ATHCFG_WCMD_SET_DEV_EIFS_MASK;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
          return EIO;
 
     fprintf(stdout, "EIFS-MASK -- %d\n", i_req.d_datum);
     return 0;
}

/**
 * @brief Handlers for GET/Set EIFS_DUR.
 */
int
athcfg_eifsdur_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_EIFS_DUR;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "EIFS-DUR -- %d\n", i_req.d_datum);
    return 0;
}

int
athcfg_eifsdur_set(char *ifrn_name, int nargs, char *args[])
{
     athcfg_wcmd_t i_req; 
     memset(&i_req,0, sizeof(athcfg_wcmd_t));

     assert (nargs == 1);
 
     if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
          fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
          return E2BIG;
     }
     i_req.d_datum = 0;

     i_req.d_datum = atoi(args[0]);
     strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
     i_req.type = ATHCFG_WCMD_SET_DEV_EIFS_DUR;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
          return EIO;
 
     fprintf(stdout, "EIFS-DUR -- %d\n", i_req.d_datum);
     return 0;
}

/**
 * @brief Handlers for GET/Set SLOT-TIME.
 */
int
athcfg_slottime_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_SLOTTIME;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "SLOT-TIME -- %d\n", i_req.d_datum);
    return 0;
}

int
athcfg_slottime_set(char *ifrn_name, int nargs, char *args[])
{
     athcfg_wcmd_t i_req; 
     memset(&i_req,0, sizeof(athcfg_wcmd_t));

     assert (nargs == 1);
 
     if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
          fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
          return E2BIG;
     }
     i_req.d_datum = 0;

     i_req.d_datum = atoi(args[0]);
     strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
     i_req.type = ATHCFG_WCMD_SET_DEV_SLOTTIME;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
          return EIO;
 
     fprintf(stdout, "SLOT-TIME -- %d\n", i_req.d_datum);
     return 0;
}

/**
 * @brief Handlers for GET/Set ACK-TIMEOUT.
 */
int
athcfg_acktimeout_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_ACKTIMEOUT;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "ACK-TIMEOUT -- %d\n", i_req.d_datum);
    return 0;
}

int
athcfg_acktimeout_set(char *ifrn_name, int nargs, char *args[])
{
     athcfg_wcmd_t i_req; 
     memset(&i_req,0, sizeof(athcfg_wcmd_t));

     assert (nargs == 1);
 
     if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
          fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
          return E2BIG;
     }
     i_req.d_datum = 0;

     i_req.d_datum = atoi(args[0]);
     strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
     i_req.type = ATHCFG_WCMD_SET_DEV_ACKTIMEOUT;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
          return EIO;
 
     fprintf(stdout, "ACK-TIMEOUT -- %d\n", i_req.d_datum);
     return 0;
}

/**
 * @brief Handlers for GET/Set CTS-TIMEOUT.
 */
int
athcfg_ctstimeout_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_CTSTIMEOUT;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "CST-TIMEOUT -- %d\n", i_req.d_datum);
    return 0;
}

int
athcfg_ctstimeout_set(char *ifrn_name, int nargs, char *args[])
{
     athcfg_wcmd_t i_req; 
     memset(&i_req,0, sizeof(athcfg_wcmd_t));

     assert (nargs == 1);
 
     if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
          fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
          return E2BIG;
     }
     i_req.d_datum = 0;

     i_req.d_datum = atoi(args[0]);
     strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
     i_req.type = ATHCFG_WCMD_SET_DEV_CTSTIMEOUT;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
          return EIO;
 
     fprintf(stdout, "CST-TIMEOUT -- %d\n", i_req.d_datum);
     return 0;
}

/**
 * @brief Handlers for GET/Set SOFT-LED.
 */
int
athcfg_softled_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_SOFTLED;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "SOFT-LED -- %d\n", i_req.d_datum);
    return 0;
}

int
athcfg_softled_set(char *ifrn_name, int nargs, char *args[])
{
     athcfg_wcmd_t i_req; 
     memset(&i_req,0, sizeof(athcfg_wcmd_t));

     assert (nargs == 1);
 
     if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
          fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
          return E2BIG;
     }
     i_req.d_datum = 0;

     i_req.d_datum = atoi(args[0]);
     strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
     i_req.type = ATHCFG_WCMD_SET_DEV_SOFTLED;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
          return EIO;
 
     fprintf(stdout, "SOFT-LED -- %d\n", i_req.d_datum);
     return 0;
}

/**
 * @brief Handlers for GET/Set LED-PIN.
 */
int
athcfg_ledpin_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_LEDPIN;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "LED-PIN -- %d\n", i_req.d_datum);
    return 0;
}

int
athcfg_ledpin_set(char *ifrn_name, int nargs, char *args[])
{
     athcfg_wcmd_t i_req; 
     memset(&i_req,0, sizeof(athcfg_wcmd_t));

     assert (nargs == 1);
 
     if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
          fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
          return E2BIG;
     }
     i_req.d_datum = 0;

     i_req.d_datum = atoi(args[0]);
     strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
     i_req.type = ATHCFG_WCMD_SET_DEV_LEDPIN;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
          return EIO;
 
     fprintf(stdout, "LED-PIN -- %d\n", i_req.d_datum);
     return 0;
}

/**
 * @brief Handlers for GET/Set DEBUG.
 */
int
athcfg_debug_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_DEBUG;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "DEBUG -- %d\n", i_req.d_datum);
    return 0;
}

int
athcfg_debug_set(char *ifrn_name, int nargs, char *args[])
{
     athcfg_wcmd_t i_req; 
     memset(&i_req,0, sizeof(athcfg_wcmd_t));

     assert (nargs == 1);
 
     if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
          fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
          return E2BIG;
     }
     i_req.d_datum = 0;

     i_req.d_datum = atoi(args[0]);
     strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
     i_req.type = ATHCFG_WCMD_SET_DEV_DEBUG;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
          return EIO;
 
     fprintf(stdout, "DEBUG -- %d\n", i_req.d_datum);
     return 0;
}

/**
 * @brief Handlers for GET/Set TX-ANTENNA.
 */
int
athcfg_tx_antenna_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_TXANTENNA;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "Tx-Antenna -- %d\n", i_req.d_datum);
    return 0;
}

int
athcfg_tx_antenna_set(char *ifrn_name, int nargs, char *args[])
{
     athcfg_wcmd_t i_req; 
     memset(&i_req,0, sizeof(athcfg_wcmd_t));

     assert (nargs == 1);
 
     if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
          fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
          return E2BIG;
     }
     i_req.d_datum = 0;

     i_req.d_datum = atoi(args[0]);
     strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
     i_req.type = ATHCFG_WCMD_SET_DEV_TXANTENNA;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
          return EIO;
 
     fprintf(stdout, "Tx-Antenna -- %d\n", i_req.d_datum);
     return 0;
}

/**
 * @brief Handlers for GET/Set RX-ANTENNA.
 */
int
athcfg_rx_antenna_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_RXANTENNA;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "Rx-Antenna -- %d\n", i_req.d_datum);
    return 0;
}

int
athcfg_rx_antenna_set(char *ifrn_name, int nargs, char *args[])
{
     athcfg_wcmd_t i_req; 
     memset(&i_req,0, sizeof(athcfg_wcmd_t));

     assert (nargs == 1);
 
     if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
          fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
          return E2BIG;
     }
     i_req.d_datum = 0;

     i_req.d_datum = atoi(args[0]);
     strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
     i_req.type = ATHCFG_WCMD_SET_DEV_RXANTENNA;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
          return EIO;
 
     fprintf(stdout, "Rx-Antenna -- %d\n", i_req.d_datum);
     return 0;
}

/**
 * @brief Handlers for GET/Set DIVERSITY.
 */
int
athcfg_diversity_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_DIVERSITY;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "Diversity -- %d\n", i_req.d_datum);
    return 0;
}

int
athcfg_diversity_set(char *ifrn_name, int nargs, char *args[])
{
     athcfg_wcmd_t i_req; 
     memset(&i_req,0, sizeof(athcfg_wcmd_t));

     assert (nargs == 1);
 
     if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
          fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
          return E2BIG;
     }
     i_req.d_datum = 0;

     i_req.d_datum = atoi(args[0]);
     strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
     i_req.type = ATHCFG_WCMD_SET_DEV_DIVERSITY;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
          return EIO;
 
     fprintf(stdout, "Diversity -- %d\n", i_req.d_datum);
     return 0;
}
/**
 * @brief Handlers for GET/Set TX-INTR-PERIOD.
 */
int
athcfg_tx_intrperiod_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_TXINTRPERIOD;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "Tx-Intr-Period -- %d\n", i_req.d_datum);
    return 0;
}

int
athcfg_tx_intrperiod_set(char *ifrn_name, int nargs, char *args[])
{
     athcfg_wcmd_t i_req; 
     memset(&i_req,0, sizeof(athcfg_wcmd_t));

     assert (nargs == 1);
 
     if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
          fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
          return E2BIG;
     }
     i_req.d_datum = 0;

     i_req.d_datum = atoi(args[0]);
     strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
     i_req.type = ATHCFG_WCMD_SET_DEV_TXINTRPERIOD;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
          return EIO;
 
     fprintf(stdout, "Tx-Intr-Period -- %d\n", i_req.d_datum);
     return 0;
}

/**
 * @brief Handlers for get Product-Info.
 */
int
athcfg_product_info_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    athcfg_wcmd_product_info_t product_info;
    memset(&i_req, 0, sizeof(athcfg_wcmd_t));
    memset(&product_info, 0, sizeof(athcfg_wcmd_product_info_t));

    assert(nargs == 0);
    args[0] = NULL;

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_PRODUCT_INFO;
    i_req.d_productinfo = product_info;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
     return EIO;

    fprintf(stdout, "idVendor       -- 0x%x\n", i_req.d_productinfo.idVendor);
    fprintf(stdout, "idProduct      -- 0x%x\n", i_req.d_productinfo.idProduct);
    fprintf(stdout, "product        -- %s\n", i_req.d_productinfo.product);
    fprintf(stdout, "manufacturer   -- %s\n", i_req.d_productinfo.manufacturer);
    fprintf(stdout, "serial         -- %s\n", i_req.d_productinfo.serial);
    return 0;

}

/**
 * @brief Handlers for GET/Set FF-TXQ-MIN.
 */
int
athcfg_txq_min_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_FFTXQMIN;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "Fast-Frame-Txq-Min -- %d\n", i_req.d_datum);
    return 0;
}

int
athcfg_txq_min_set(char *ifrn_name, int nargs, char *args[])
{
     athcfg_wcmd_t i_req; 
     memset(&i_req,0, sizeof(athcfg_wcmd_t));

     assert (nargs == 1);
 
     if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
          fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
          return E2BIG;
     }
     i_req.d_datum = 0;

     i_req.d_datum = atoi(args[0]);
     strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
     i_req.type = ATHCFG_WCMD_SET_DEV_FFTXQMIN;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
          return EIO;
 
     fprintf(stdout, "Fast-Frame-Txq-Min -- %d\n", i_req.d_datum);
     return 0;
}

/**
 * @brief Handlers for GET/Set TKIP-MIC.
 */
int
athcfg_tkipmic_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_TKIPMIC;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "Tkip-Mic -- %d\n", i_req.d_datum);
    return 0;
}

int
athcfg_tkipmic_set(char *ifrn_name, int nargs, char *args[])
{
     athcfg_wcmd_t i_req; 
     memset(&i_req,0, sizeof(athcfg_wcmd_t));

     assert (nargs == 1);
 
     if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
          fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
          return E2BIG;
     }
     i_req.d_datum = 0;

     i_req.d_datum = atoi(args[0]);
     strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
     i_req.type = ATHCFG_WCMD_SET_DEV_TKIPMIC;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
          return EIO;
 
     fprintf(stdout, "Tkip-Mic -- %d\n", i_req.d_datum);
     return 0;
}

/**
 * @brief Handlers for GET/Set GLOBAL-TX-TIMEOUT.
 */
int
athcfg_glob_txtimeout_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_GLOBALTXTIMEOUT;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "Global_Tx-Timeout -- %d\n", i_req.d_datum);
    return 0;
}

int
athcfg_glob_txtimeout_set(char *ifrn_name, int nargs, char *args[])
{
     athcfg_wcmd_t i_req; 
     memset(&i_req,0, sizeof(athcfg_wcmd_t));

     assert (nargs == 1);
 
     if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
          fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
          return E2BIG;
     }
     i_req.d_datum = 0;

     i_req.d_datum = atoi(args[0]);
     strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
     i_req.type = ATHCFG_WCMD_SET_DEV_GLOBALTXTIMEOUT;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
          return EIO;
 
     fprintf(stdout, "Global-Tx-Timeout -- %d\n", i_req.d_datum);
     return 0;
}

/**
 * @brief Handlers for GET/Set SW-WSC-BUTTON.
 */
int
athcfg_sw_wscbutton_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_SW_WSC_BUTTON;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "WSC-Button -- %d\n", i_req.d_datum);
    return 0;
}

int
athcfg_sw_wscbutton_set(char *ifrn_name, int nargs, char *args[])
{
     athcfg_wcmd_t i_req; 
     memset(&i_req,0, sizeof(athcfg_wcmd_t));

     assert (nargs == 1);
 
     if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
          fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
          return E2BIG;
     }
     i_req.d_datum = 0;

     i_req.d_datum = atoi(args[0]);
     strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
     i_req.type = ATHCFG_WCMD_SET_DEV_SW_WSC_BUTTON;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
          return EIO;
 
     fprintf(stdout, "WSC-Button -- %d\n", i_req.d_datum);
     return 0;
}

/**
 * @brief Handlers for GET COUNTRY-CODE.
 */
int
athcfg_country_code_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_COUNTRYCODE;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "Country-Code -- %x\n", i_req.d_datum);
    return 0;
}

/**
 * @brief Handlers for GET REGION-DOMAIN.
 */
int
athcfg_reg_domain_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }
    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_REGDOMAIN;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "Region-Domain -- %d\n", i_req.d_datum);
    return 0;
}

/**
 * @brief Handlers for SET REGION-DOMAIN.
 * if 15th bit 1 reg domain is set if set to 0 country code is set 
 */
int
athcfg_reg_domain_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }
 
    i_req.d_datum =  strtol(args[0],NULL,16);
    printf(" regulatory code  0x%x\n", i_req.d_datum);
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_REGULATORYCODE;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    return 0;
}



/**
 * @brief Handlers for GET REGION-DOMAIN.
 */
int
athcfg_dbg_info_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    athcfg_wcmd_dbg_info_t   *dbg_info;
    
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs <= 2);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }
    dbg_info = &i_req.d_dbginfo;
    memset(dbg_info,0, sizeof(athcfg_wcmd_dbg_info_t));
    
    /* Default value */
    dbg_info->type = ATHCFG_WCMD_DBG_DUMMY;
    
    if (nargs >= 1) {
        if (!strcmp (args[0], "trigger"))
            dbg_info->type = ATHCFG_WCMD_DBG_TRIGGER;
        else if (!strcmp (args[0], "capture-pkt"))
           dbg_info->type = ATHCFG_WCMD_DBG_CAP_PKT;
        else if (!strcmp (args[0], "other"))
           dbg_info->type = ATHCFG_WCMD_DBG_OTHER;

        if (nargs == 2) {
            printf("DBG-INFO:Not implemented \n");
        }
    }

    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_DBG_INFO;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
         return EIO;
    
    return 0;
}


int
athcfg_dbg_info_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    athcfg_wcmd_dbg_info_t   *dbg_info;
    
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs <= 2);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }
    dbg_info = &(i_req.d_dbginfo);
    memset(dbg_info,0, sizeof(athcfg_wcmd_dbg_info_t));

    /* Default value */
    dbg_info->type = ATHCFG_WCMD_DBG_DUMMY;
    
    if (nargs >= 1) {
        if (!strcmp (args[0], "trigger"))
            dbg_info->type = ATHCFG_WCMD_DBG_TRIGGER;
        else if (!strcmp (args[0], "capture-pkt"))
           dbg_info->type = ATHCFG_WCMD_DBG_CAP_PKT;
        else if (!strcmp (args[0], "other"))
           dbg_info->type = ATHCFG_WCMD_DBG_OTHER;

        if (nargs == 2) {
            printf("DBG-INFO:Not implemented \n");
        }
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DBG_INFO;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
    
    return 0;
}

int athcfg_hst_stats_get(char *ifrn_name, int nargs, char *args[])
{ 
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    assert (nargs == 0);
    args[0] = NULL;
    
    athcfg_wcmd_hst_phystats_t *hst_phystats;
    if( (hst_phystats = malloc(sizeof(athcfg_wcmd_hst_phystats_t)) ) == NULL)
        ERR_MALLOC_FAIL("hst-phystats");

    memset(hst_phystats, 0, sizeof(athcfg_wcmd_hst_phystats_t));
    
    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        free(hst_phystats);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_HST_STATS;
    i_req.d_hststats = hst_phystats;
    
    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0) {
        free(hst_phystats);
        return EIO;
    }
    
    printf("HST STATUS for Interface [%s] are as below ---------------\n",ifrn_name);
    fprintf(stdout,"Device reset by watchdog            -- %d\n",i_req.d_hststats->ast_watchdog);
    
    /*fprintf(stdout,"Fatal Hardware error Interrupt        -- %d\n",i_req.d_phystats->ast_hardware);
    fprintf(stdout,"Beacon miss Interrupt           -- %d\n",i_req.d_phystats->ast_bmiss);
    fprintf(stdout,"Rx overrun Interrupt            -- %d\n",i_req.d_phystats->ast_rxorn);
    fprintf(stdout,"Rx eol Interrupt                -- %d\n",i_req.d_phystats->ast_rxeol);
    fprintf(stdout,"Tx underrun Interrupt           -- %d\n",i_req.d_phystats->ast_txurn);
    fprintf(stdout,"Tx timeout Interrupt            -- %d\n",i_req.d_phystats->ast_txto);
    fprintf(stdout,"Carrier sense timeout Interrupt     -- %d\n",i_req.d_phystats->ast_cst);
    fprintf(stdout,"Mibs Interrupt              --%d\n",i_req.d_phystats->ast_mib);*/
    
    fprintf(stdout,"Packets sent on the Interface       -- %d\n",i_req.d_hststats->ast_tx_packets);
    /*fprintf(stdout,"Management frames Transmitted     -- %d\n",i_req.d_phystats->ast_tx_mgmt);*/
    fprintf(stdout,"Frames discarded prior to Assoc     -- %d\n",i_req.d_hststats->ast_tx_discard);
    fprintf(stdout,"Frames discarded 'cuz device gone       -- %d\n",i_req.d_hststats->ast_tx_invalid);
    fprintf(stdout,"Tx queue stopped 'cuz full          -- %d\n",i_req.d_hststats->ast_tx_qstop);
    /*fprintf(stdout,"Tx encapsultaion failed           -- %d\n",i_req.d_phystats->ast_tx_encap);*/
    fprintf(stdout,"Tx failed 'cuz no node          -- %d\n",i_req.d_hststats->ast_tx_nonode);
    
    /*fprintf(stdout,"Tx failed 'cuz no tx buffer (data)        -- %d\n",i_req.d_hststats->ast_tx_nobuf);
    fprintf(stdout,"Tx failed 'cuz no tx buffer (mgmt)          -- %d\n",i_req.d_hststats->ast_tx_nobufmgt);
    fprintf(stdout,"Tx failed 'cuz too many retries             -- %d\n",i_req.d_hststats->ast_tx_xretries);
    fprintf(stdout,"Tx failed 'cuz FIFO underrun                -- %d\n",i_req.d_hststats->ast_tx_fifoerr);
    fprintf(stdout,"Tx failed 'cuz xmit filtered                -- %d\n",i_req.d_hststats->ast_tx_filtered);
    fprintf(stdout,"Tx timer expired                            -- %d\n",i_req.d_hststats->ast_tx_timer_exp);
    fprintf(stdout,"Tx on-chip retries (short)                  -- %d\n",i_req.d_hststats->ast_tx_shortretry);
    fprintf(stdout,"Tx on-chip retries (long)                   -- %d\n",i_req.d_hststats->ast_tx_longretry);*/
    
    fprintf(stdout,"Tx failed 'cuz bogus xmit rate              -- %d\n",i_req.d_hststats->ast_tx_badrate);
    fprintf(stdout,"Tx frames with no ack marked                -- %d\n",i_req.d_hststats->ast_tx_noack);
    /*fprintf(stdout,"Tx frames with rts enabled                -- %d\n",i_req.d_hststats->ast_tx_rts);*/
    fprintf(stdout,"Tx frames with cts enabled                  -- %d\n",i_req.d_hststats->ast_tx_cts);
    fprintf(stdout,"Tx frames with short preamble               -- %d\n",i_req.d_hststats->ast_tx_shortpre);
    
    /*fprintf(stdout,"Tx frames with alternate rate             -- %d\n",i_req.d_hststats->ast_tx_altrate);
    fprintf(stdout,"Tx frames with protection                   -- %d\n",i_req.d_hststats->ast_tx_protect);
    fprintf(stdout,"Rx failed 'cuz of desc overrun              -- %d\n",i_req.d_hststats->ast_rx_orn);*/
    
    fprintf(stdout,"Rx failed 'cuz of bad CRC                   -- %d\n",i_req.d_hststats->ast_rx_crcerr);
    fprintf(stdout,"Rx failed 'cuz of FIFO overrun              -- %d\n",i_req.d_hststats->ast_rx_fifoerr);
    fprintf(stdout,"Rx failed 'cuz decryption                   -- %d\n",i_req.d_hststats->ast_rx_badcrypt);
    fprintf(stdout,"Rx failed 'cuz MIC failure                  -- %d\n",i_req.d_hststats->ast_rx_badmic);
    fprintf(stdout,"Rx PHY error summary count                  -- %d\n",i_req.d_hststats->ast_rx_phyerr);
    fprintf(stdout,"Rx PHY error per-code counts                -- %s\n",(char *)i_req.d_hststats->ast_rx_phy);
    fprintf(stdout,"Rx discarded 'cuz frame too short           -- %d\n",i_req.d_hststats->ast_rx_tooshort);
    fprintf(stdout,"Rx discarded 'cuz frame too large           -- %d\n",i_req.d_hststats->ast_rx_toobig);
    /*fprintf(stdout,"Rx setup failed 'cuz no skbuff            -- %d\n",i_req.d_hststats->ast_rx_nobuf);*/
    fprintf(stdout,"Packet recv on the interface                -- %d\n",i_req.d_hststats->ast_rx_packets);
    fprintf(stdout,"Management frames received                  -- %d\n",i_req.d_hststats->ast_rx_mgt);
    fprintf(stdout,"Control frames received                     -- %d\n",i_req.d_hststats->ast_rx_ctl);
    
    /*fprintf(stdout,"Tx rssi of last ack [combined]            -- %d\n",i_req.d_hststats->ast_tx_rssi_combined);
    fprintf(stdout,"Tx rssi of last ack [ctl, chain 0]          -- %d\n",i_req.d_hststats->ast_tx_rssi_ctl0);
    fprintf(stdout,"Tx rssi of last ack [ctl, chain 1]          -- %d\n",i_req.d_hststats->ast_tx_rssi_ctl1);
    fprintf(stdout,"Tx rssi of last ack [ctl, chain 2]          -- %d\n",i_req.d_hststats->ast_tx_rssi_ctl2);
    fprintf(stdout,"Tx rssi of last ack [ext, chain 0]          -- %d\n",i_req.d_hststats->ast_tx_rssi_ext0);
    fprintf(stdout,"Tx rssi of last ack [ext, chain 1]          -- %d\n",i_req.d_hststats->ast_tx_rssi_ext1);
    fprintf(stdout,"Tx rssi of last ack [ext, chain 2]          -- %d\n",i_req.d_hststats->ast_tx_rssi_ext2);*/
    
    fprintf(stdout,"Rx rssi of last ack [combined]              -- %d\n",i_req.d_hststats->ast_rx_rssi_combined);
    fprintf(stdout,"Rx rssi of last ack [ctl, chain 0]          -- %d\n",i_req.d_hststats->ast_rx_rssi_ctl0);
    fprintf(stdout,"Rx rssi of last ack [ctl, chain 1]          -- %d\n",i_req.d_hststats->ast_rx_rssi_ctl1);
    fprintf(stdout,"Rx rssi of last ack [ctl, chain 2]          -- %d\n",i_req.d_hststats->ast_rx_rssi_ctl2);
    fprintf(stdout,"Rx rssi of last ack [ext, chain 0]          -- %d\n",i_req.d_hststats->ast_rx_rssi_ext0);
    fprintf(stdout,"Rx rssi of last ack [ext, chain 1]          -- %d\n",i_req.d_hststats->ast_rx_rssi_ext1);
    fprintf(stdout,"Rx rssi of last ack [ext, chain 2]          -- %d\n",i_req.d_hststats->ast_rx_rssi_ext2);
    fprintf(stdout,"Beacon Rx rssi [combined]                   -- %d\n",i_req.d_hststats->ast_bc_rx_rssi_combined);
    fprintf(stdout,"Beacon Rx rssi [ctl, chain 0]               -- %d\n",i_req.d_hststats->ast_bc_rx_rssi_ctl0);
    fprintf(stdout,"Beacon Rx rssi [ctl, chain 1]               -- %d\n",i_req.d_hststats->ast_bc_rx_rssi_ctl1);
    fprintf(stdout,"Beacon Rx rssi [ctl, chain 2]               -- %d\n",i_req.d_hststats->ast_bc_rx_rssi_ctl2);
    /*fprintf(stdout,"Beacons transmitted                       -- %d\n",i_req.d_hststats->ast_be_xmit);*/
    fprintf(stdout,"No skbuff available for beacon              -- %d\n",i_req.d_hststats->ast_be_nobuf);
    fprintf(stdout,"Periodic calibration calls                  -- %d\n",i_req.d_hststats->ast_per_cal);
    fprintf(stdout,"Periodic calibration failed                 -- %d\n",i_req.d_hststats->ast_per_calfail);
    fprintf(stdout,"Periodic calibration rfgain reset           -- %d\n",i_req.d_hststats->ast_per_rfgain);
    fprintf(stdout,"Rate control checks                         -- %d\n",i_req.d_hststats->ast_rate_calls);
    fprintf(stdout,"Rate control raised xmit rate               -- %d\n",i_req.d_hststats->ast_rate_raise);
    fprintf(stdout,"Rate control dropped xmit rate              -- %d\n",i_req.d_hststats->ast_rate_drop);
    /*fprintf(stdout,"Rx/default antenna switches               -- %d\n",i_req.d_hststats->ast_ant_defswitch);*/
    fprintf(stdout,"Tx antenna switches                         -- %d\n",i_req.d_hststats->ast_ant_txswitch);
    fprintf(stdout,"Rx frames with antenna                      -- %s\n",(char *)i_req.d_hststats->ast_ant_rx);
    /*fprintf(stdout,"Tx frames with antenna                    -- %s\n",(char*)i_req.d_hststats->ast_ant_tx);*/
    fprintf(stdout,"Driver suspend calls                        -- %d\n",i_req.d_hststats->ast_suspend);
    fprintf(stdout,"Driver resume calls                         -- %d\n",i_req.d_hststats->ast_resume);
    fprintf(stdout,"Driver shutdown calls                       -- %d\n",i_req.d_hststats->ast_shutdown);
    fprintf(stdout,"Driver init calls                           -- %d\n",i_req.d_hststats->ast_init);
    fprintf(stdout,"Driver stop calls                           -- %d\n",i_req.d_hststats->ast_stop);
    fprintf(stdout,"Driver resets                               -- %d\n",i_req.d_hststats->ast_reset);
    fprintf(stdout,"Nodes allocated                             -- %d\n",i_req.d_hststats->ast_nodealloc);
    fprintf(stdout,"Nodes deleted                               -- %d\n",i_req.d_hststats->ast_nodefree);
    fprintf(stdout,"Keys allocated                              -- %d\n",i_req.d_hststats->ast_keyalloc);
    fprintf(stdout,"Keys allocates                              -- %d\n",i_req.d_hststats->ast_keydelete);
    fprintf(stdout,"Beacon stuck                                -- %d\n",i_req.d_hststats->ast_bstuck);
   
    /* fprintf(stdout,"Drain tx queue                         -- %d\n",i_req.d_hststats->ast_draintxq);
    fprintf(stdout,"Stop tx queue dma                         -- %d\n",i_req.d_hststats->ast_stopdma);
    fprintf(stdout,"Stop recv                                 --%d\n",i_req.d_hststats->ast_stoprecv);*/
    
    fprintf(stdout,"Start recv                                  -- %d\n",i_req.d_hststats->ast_startrecv);
    fprintf(stdout,"Flush rec                                   -- %d\n",i_req.d_hststats->ast_flushrecv);
    fprintf(stdout,"Channel changes                             -- %d\n",i_req.d_hststats->ast_chanchange);
    fprintf(stdout,"Number of fast channel changes              -- %d\n",i_req.d_hststats->ast_fastcc);
    fprintf(stdout,"Number of failed fast channel changes       -- %d\n",i_req.d_hststats->ast_fastcc_errs);
    fprintf(stdout,"Channel sets                                -- %d\n",i_req.d_hststats->ast_chanset);
    fprintf(stdout,"CWM - mac mode switch                       -- %d\n",i_req.d_hststats->ast_cwm_mac);
    fprintf(stdout,"CWM - phy mode switch                       -- %d\n",i_req.d_hststats->ast_cwm_phy);
    fprintf(stdout,"CWM - requeue dest node packets             -- %d\n",i_req.d_hststats->ast_cwm_requeue);
    fprintf(stdout,"Pre-delimiter crc errors                    -- %d\n",i_req.d_hststats->ast_rx_delim_pre_crcerr);
    fprintf(stdout,"Post-delimiter crc errors                   -- %d\n",i_req.d_hststats->ast_rx_delim_post_crcerr);
    fprintf(stdout,"Decrypt busy errors                         -- %d\n",i_req.d_hststats->ast_rx_decrypt_busyerr);
    fprintf(stdout,"Current rx rate                             -- %d\n",i_req.d_hststats->ast_rx_rate);
        
    if(hst_phystats)
        free(hst_phystats);
 
    return 0;
}
 
int athcfg_hst_11n_stats_get(char *ifrn_name, int nargs, char *args[])
{ 
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    assert (nargs == 0);
    args[0] = NULL;
    
    athcfg_wcmd_hst_phystats_t *hst11n_phystats;
    if( (hst11n_phystats = malloc(sizeof(athcfg_wcmd_hst_phystats_t)) ) == NULL)
        ERR_MALLOC_FAIL("hst-11n-phystats");

    memset(hst11n_phystats, 0, sizeof(athcfg_wcmd_hst_phystats_t));
    
    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        free(hst11n_phystats);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_HST_11N_STATS;
    i_req.d_hststats = hst11n_phystats;
    
    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0) {
        free(hst11n_phystats);
        return EIO;      
    }
    
    printf("HST 11N STATUS for Interface [%s] are as below ---------------\n",ifrn_name);
    fprintf(stdout,"Total tx data packets                       -- %d\n",i_req.d_hststats->ast_11n.tx_pkts);
    fprintf(stdout,"Tx drops in wrong stat                      -- %d\n",i_req.d_hststats->ast_11n.tx_checks);
    fprintf(stdout,"Tx drops due to qdepth limit                -- %d\n",i_req.d_hststats->ast_11n.tx_drops);
    /*fprintf(stdout,"Tx when h/w queue depth is low            -- %d\n",i_req.d_hststats->ast_11n.tx_minqdepth);*/
    fprintf(stdout,"Tx pkts when h/w queue is busy              -- %d\n",i_req.d_hststats->ast_11n.tx_queue);
    
    /*fprintf(stdout,"Tx pkts filtered for requeueing             -- %d\n",i_req.d_hststats->ast_11n.tx_stopfiltered);
    fprintf(stdout,"Txq empty occurences                        -- %d\n",i_req.d_hststats->ast_11n.tx_qnull);
    fprintf(stdout,"Tx no skbs for encapsulations               -- %d\n",i_req.d_hststats->ast_11n.tx_noskbs);
    fprintf(stdout,"Tx key setup failures                     --%d\n",i_req.d_hststats->ast_11n.tx_badsetups);*/
    
    fprintf(stdout,"Tx no desc for legacy packets               -- %d\n",i_req.d_hststats->ast_11n.tx_schednone);
    
    /*fprintf(stdout,"Tx bars sent                                -- %d\n",i_req.d_hststats->ast_11n.tx_bars);
    fprintf(stdout,"Tx bars excessively retried                 -- %d\n",i_req.d_hststats->ast_11n.txbar_xretry);
    fprintf(stdout,"Tx bars retried                             -- %d\n",i_req.d_hststats->ast_11n.txbar_compretries);
    fprintf(stdout,"Tx bars last frame failed                   -- %d\n",i_req.d_hststats->ast_11n.txbar_errlast);
    fprintf(stdout,"Tx unaggregated frame completions           -- %d\n",i_req.d_hststats->ast_11n.tx_compunaggr);
    fprintf(stdout,"Tx unaggregated excessive retries           -- %d\n",i_req.d_hststats->ast_11n.txunaggr_xretry);
   
    fprintf(stdout,"Tx block ack window advanced                -- %d\n",i_req.d_hststats->ast_11n.tx_bawadv);
    fprintf(stdout,"Tx block ack window retries                 -- %d\n",i_req.d_hststats->ast_11n.tx_bawretries);
    fprintf(stdout,"Tx block ack window additions               -- %d\n",i_req.d_hststats->ast_11n.tx_bawnorm);
    fprintf(stdout,"Tx block ack window updates                 -- %d\n",i_req.d_hststats->ast_11n.tx_bawupdates);
    fprintf(stdout,"Tx block ack window advances                -- %d\n",i_req.d_hststats->ast_11n.tx_bawupdtadv);
   
    fprintf(stdout,"Tx excessive retries of aggregates        --%d\n",i_req.d_hststats->ast_11n.tx_xretries);*/

    fprintf(stdout,"Tx no skbs for aggr encapsualtion           -- %d\n",i_req.d_hststats->ast_11n.txaggr_noskbs);
    fprintf(stdout,"Tx enc key setup failures                   -- %d\n",i_req.d_hststats->ast_11n.txaggr_badkeys);

/* fprintf(stdout,"Tx no frame scheduled: baw limited          -- %d\n",i_req.d_hststats->ast_11n.txaggr_schedwindow);
    fprintf(stdout,"Tx frames not aggregated                    -- %d\n",i_req.d_hststats->ast_11n.txaggr_single);
    fprintf(stdout,"Tx aggr good completions                    -- %d\n",i_req.d_hststats->ast_11n.txaggr_compgood);
    fprintf(stdout,"Tx aggr excessive retries                   -- %d\n",i_req.d_hststats->ast_11n.txaggr_compxretry);
    fprintf(stdout,"Tx aggr unacked subframes                   -- %d\n",i_req.d_hststats->ast_11n.txaggr_compretries);
   fprintf(stdout,"Tx non-aggr unacked subframes               -- %d\n",i_req.d_hststats->ast_11n.txunaggr_compretries);
    fprintf(stdout,"Tx aggr old frames requeued                 -- %d\n",i_req.d_hststats->ast_11n.txaggr_prepends);
    fprintf(stdout,"Filtered aggr packet                        -- %d\n",i_req.d_hststats->ast_11n.txaggr_filtered);
    fprintf(stdout,"Fifo underrun of aggregate                  -- %d\n",i_req.d_hststats->ast_11n.txaggr_fifo);
    fprintf(stdout,"Txop exceeded for an aggregate              -- %d\n",i_req.d_hststats->ast_11n.txaggr_xtxop);
    fprintf(stdout,"Aggregate descriptor config error           -- %d\n",i_req.d_hststats->ast_11n.txaggr_desc_cfgerr);
    fprintf(stdout,"Data underrun for an aggregate              -- %d\n",i_req.d_hststats->ast_11n.txaggr_data_urun);
    fprintf(stdout,"Delimiter underrun for an aggregate         -- %d\n",i_req.d_hststats->ast_11n.txaggr_delim_urun);
    fprintf(stdout,"Tx aggr: last sub-frame failed              -- %d\n",i_req.d_hststats->ast_11n.txaggr_errlast);
    fprintf(stdout,"Tx aggr h/w long retries                    -- %d\n",i_req.d_hststats->ast_11n.txaggr_longretries);
    fprintf(stdout,"Tx aggr h/w short retries                 --%d\n",i_req.d_hststats->ast_11n.txaggr_shortretries);
    fprintf(stdout,"Tx aggr : tx timer expired                --%d\n",i_req.d_hststats->ast_11n.txaggr_timer_exp);
    fprintf(stdout,"Tx aggr : BA bug                          --%d\n",i_req.d_hststats->ast_11n.txaggr_babug);*/
   
    fprintf(stdout,"Rx pkts                                     -- %d\n",i_req.d_hststats->ast_11n.rx_pkts);
    fprintf(stdout,"Rx aggregated packets                       -- %d\n",i_req.d_hststats->ast_11n.rx_aggr);
    fprintf(stdout,"Rx pkts with bad version                    -- %d\n",i_req.d_hststats->ast_11n.rx_aggrbadver);
    fprintf(stdout,"Rx bars                                     -- %d\n",i_req.d_hststats->ast_11n.rx_bars);
    fprintf(stdout,"Rx non qos-data frames                      -- %d\n",i_req.d_hststats->ast_11n.rx_nonqos);
    fprintf(stdout,"Rx sequence resets                          -- %d\n",i_req.d_hststats->ast_11n.rx_seqreset);
    fprintf(stdout,"Rx old packets                              -- %d\n",i_req.d_hststats->ast_11n.rx_oldseq);
    fprintf(stdout,"Rx block ack window reset                   -- %d\n",i_req.d_hststats->ast_11n.rx_bareset);
    fprintf(stdout,"Rx pts indicated due to baw resets          -- %d\n",i_req.d_hststats->ast_11n.rx_baresetpkts);
    fprintf(stdout,"Rx duplicate pkts                           -- %d\n",i_req.d_hststats->ast_11n.rx_dup);
    fprintf(stdout,"Rx block ack window advanced                -- %d\n",i_req.d_hststats->ast_11n.rx_baadvance);
    fprintf(stdout,"Rx pkt completions                          -- %d\n",i_req.d_hststats->ast_11n.rx_recvcomp);
    fprintf(stdout,"Rx bar discarded                            -- %d\n",i_req.d_hststats->ast_11n.rx_bardiscard);
    fprintf(stdout,"Rx pkt completions on bar reception         -- %d\n",i_req.d_hststats->ast_11n.rx_barcomps);
    fprintf(stdout,"Rx pkt completions on bar reception         -- %d\n",i_req.d_hststats->ast_11n.rx_barrecvs);
    fprintf(stdout,"Rx pkt sequences skipped on timeout         -- %d\n",i_req.d_hststats->ast_11n.rx_skipped);
    fprintf(stdout,"Rx indications due to timeout               -- %d\n",i_req.d_hststats->ast_11n.rx_comp_to);
    fprintf(stdout,"Watchdog: tx is active                      -- %d\n",i_req.d_hststats->ast_11n.wd_tx_active);
    fprintf(stdout,"Watchdog: tx is not active              -- %d\n",i_req.d_hststats->ast_11n.wd_tx_inactive);
    fprintf(stdout,"Watchdog: tx is hung                        -- %d\n",i_req.d_hststats->ast_11n.wd_tx_hung);
    fprintf(stdout,"Watchdog: spurious tx hang                  -- %d\n",i_req.d_hststats->ast_11n.wd_spurious);
    fprintf(stdout,"Filter & requeue on 20/40 transitions       -- %d\n",i_req.d_hststats->ast_11n.tx_requeue);
    /*fprintf(stdout,"Draining tx queue on error                -- %d\n",i_req.d_hststats->ast_11n.tx_drain_txq);*/
    fprintf(stdout,"Draining tid buf queue on error             -- %d\n",i_req.d_hststats->ast_11n.tx_drain_tid);
    fprintf(stdout,"Buffers drained from pending tid queue      -- %d\n",i_req.d_hststats->ast_11n.tx_drain_bufs);
    fprintf(stdout,"Pausing tx on tid                           -- %d\n",i_req.d_hststats->ast_11n.tx_tidpaused);
    fprintf(stdout,"Resuming tx on tid                          -- %d\n",i_req.d_hststats->ast_11n.tx_tidresumed);
    fprintf(stdout,"Unaggregated tx pkts filtered               -- %d\n",i_req.d_hststats->ast_11n.tx_unaggr_filtered);
    /*fprintf(stdout,"Aggregated tx pkts filtered               -- %d\n",i_req.d_hststats->ast_11n.tx_aggr_filtered);*/
    fprintf(stdout,"Total sub-frames filtered                   -- %d\n",i_req.d_hststats->ast_11n.tx_filtered);
    
    if( hst11n_phystats )
        free(hst11n_phystats);

    return 0;
 }   


int athcfg_pktlog_get_data(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;

    FILE *fp = fopen(args[0],"w");
        

    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_PKTLOG_GET_DATA;

    i_req.d_pktlog_read = (adf_net_wcmd_pktlog_read_t *)malloc(sizeof(adf_net_wcmd_pktlog_read_t));

    do{
    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0) {
        free(i_req.d_pktlog_read);
        return EIO;
    }
    //printf("\nReceived %d bytes of data\n",i_req.d_pktlog_read->nbytes);
    
    fwrite(&(i_req.d_pktlog_read->data), sizeof(a_uint8_t), i_req.d_pktlog_read->nbytes,fp);
    }while(i_req.d_pktlog_read->nbytes);

    if( i_req.d_pktlog_read )
        free(i_req.d_pktlog_read);

    fclose(fp);
    
    return 0;    
}

int athcfg_tx99_set_cmd(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;

    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));

	/* enable */
	if(!strcasecmp(args[0], "start")){
		if(nargs > 1)
		{
			fprintf(stderr,"Too many arguments\n");
			fprintf(stderr,"Usage: athcfg ath0 tx99 set start\n");
			return EINVAL;
		}
		i_req.type = ATHCFG_WCMD_ENABLE_TX99;
	}	
	/* disable */
	else if(!strcasecmp(args[0], "stop")){
		if(nargs > 1)
		{
			fprintf(stderr,"Too many arguments\n");
			fprintf(stderr,"Usage: athcfg ath0 tx99 set stop\n");
			return EINVAL;
		}
		i_req.type = ATHCFG_WCMD_DISABLE_TX99;
	}	
	/* rate */
	else if(!strcasecmp(args[0], "rate")){
		if(nargs != 2)
		{
			fprintf(stderr,"Wrong arguments\n");
			fprintf(stderr,"Usage: athcfg ath0 tx99 set rate [Tx rate]\n");
			return EINVAL;
		}
		i_req.type = ATHCFG_WCMD_SET_TX99_RATE;
		i_req.d_tx99_rate = atoi(args[1]);
	}
	/* Tx frequency, bandwidth and extension channel offset */
	else if(!strcasecmp(args[0], "freq")){
		if(nargs != 4)
		{
			fprintf(stderr,"Wrong arguments\n");
			fprintf(stderr,"Usage: athcfg ath0 tx99 set freq [freq] [bandwidth] [ext offset]\n");
			return EINVAL;
		}
		i_req.type = ATHCFG_WCMD_SET_TX99_FREQ;
		i_req.d_tx99_freq = atoi(args[1]);
		i_req.d_tx99_htmode = atoi(args[2]);
		i_req.d_tx99_htext = atoi(args[3]);
	}
	else if(!strcasecmp(args[0], "pwr")){
		if(nargs != 2)
		{
			fprintf(stderr,"Wrong arguments\n");
			fprintf(stderr,"Usage: athcfg ath0 tx99 set pwr [Tx pwr]\n");
			return EINVAL;
		}
		i_req.type = ATHCFG_WCMD_SET_TX99_POWER;
		i_req.d_tx99_power = atoi(args[1]);
	}
	else if(!strcasecmp(args[0], "txmode")){
		if(nargs != 2)
		{
			fprintf(stderr,"Wrong arguments\n");
			fprintf(stderr,"Usage: athcfg ath0 tx99 set txmode [Tx mode]\n");
			return EINVAL;
		}
		i_req.type = ATHCFG_WCMD_SET_TX99_TXMODE;
		i_req.d_tx99_txmode = atoi(args[1]);
	}
	else if(!strcasecmp(args[0], "type")){
		if(nargs != 2)
		{
			fprintf(stderr,"Wrong arguments\n");
			fprintf(stderr,"Usage: athcfg ath0 tx99 set type [Tx type]\n");
			return EINVAL;
		}
		i_req.type = ATHCFG_WCMD_SET_TX99_TYPE;
		i_req.d_tx99_type = atoi(args[1]);
	}
	else if(!strcasecmp(args[0], "txchain")){
		if(nargs != 2)
		{
			fprintf(stderr,"Wrong arguments\n");
			fprintf(stderr,"Usage: athcfg ath0 tx99 set txchain [Tx chain mask]\n");
			return EINVAL;
		}
		i_req.type = ATHCFG_WCMD_SET_TX99_CHANMASK;
		i_req.d_tx99_chanmask = atoi(args[1]);
	}
	else 
    	return EINVAL;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
      return EIO;
    return 0;
}

int athcfg_tx99_get_cmd(char *ifrn_name, int nargs, char *args[])
{ 
	athcfg_wcmd_t i_req;

    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));

	i_req.type = ATHCFG_WCMD_GET_TX99;

	if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
      return EIO;

	fprintf(stdout,"TX99 parameter dump:\n");
	fprintf(stdout,":\n");
	
	return 0;
}

int athcfg_pktlog_set_cmd(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;

    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));

    if(!strcasecmp(args[0], "size")){
    i_req.type = ATHCFG_WCMD_PKTLOG_SET_SIZE;
    i_req.d_size = atoi(args[1]);
    }
    else 
    return EINVAL;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
      return EIO;
    return 0;
}


int athcfg_pktlog_enable(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_ENABLE_PKTLOG;

    if(nargs){
    if (strstr(args[0], "rx"))
        i_req.d_pktlog.eventlist |= PKTLOG_EVENT_RX_MASK;
    if (strstr(args[0], "tx"))
        i_req.d_pktlog.eventlist |= PKTLOG_EVENT_TX_MASK;
    if (strstr(args[0], "rcf"))
        i_req.d_pktlog.eventlist |= PKTLOG_EVENT_RCF_MASK;
    if (strstr(args[0], "rcu"))
        i_req.d_pktlog.eventlist |= PKTLOG_EVENT_RCU_MASK;
    if (strstr(args[0], "tcp"))
        i_req.d_pktlog.eventlist |= PKTLOG_EVENT_TCP_MASK;
    }
    else
    i_req.d_pktlog.eventlist = PKTLOG_EVENT_RX_MASK |
                            PKTLOG_EVENT_TX_MASK |
                    PKTLOG_EVENT_RCF_MASK |
                    PKTLOG_EVENT_RCU_MASK |
                    PKTLOG_EVENT_TCP_MASK;
    
    //printf("\nfilter = %x",i_req.d_pktlog.eventlist);

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
    return EIO;

    return 0;
}

int athcfg_pktlog_disable(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_DISABLE_PKTLOG;
    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
    return EIO;

    return 0;
}


 
 int athcfg_tgt_11n_stats_get(char *ifrn_name, int nargs, char *args[])
{ 
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    assert (nargs == 0);
    args[0] = NULL;
    
    athcfg_wcmd_tgt_phystats_t *tgt11n_phystats;
    if( (tgt11n_phystats = malloc(sizeof(athcfg_wcmd_tgt_phystats_t)) ) == NULL)
        ERR_MALLOC_FAIL("tgt-11n-phystats");

    memset(tgt11n_phystats, 0, sizeof(athcfg_wcmd_tgt_phystats_t));
    
    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        free(tgt11n_phystats);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_DEV_TGT_11N_STATS;
    i_req.d_tgtstats = tgt11n_phystats;
    
    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0) {
        free(tgt11n_phystats);
        return EIO;      
    }
    
    printf("TGT 11N STATUS for Interface [%s] are as below ---------------\n",ifrn_name);
    fprintf(stdout,"Total tx data packets on tgt                -- %d\n",i_req.d_tgtstats->ast_11n_tgt.tx_tgt);
    fprintf(stdout,"Total tx data aggregated on tgt             -- %d\n",i_req.d_tgtstats->ast_11n_tgt.tx_nframes);
    fprintf(stdout,"Tx completions                              -- %d\n",i_req.d_tgtstats->ast_11n_tgt.tx_comps);
    fprintf(stdout,"Txq empty occurences                        -- %d\n",i_req.d_tgtstats->ast_11n_tgt.tx_qnull);
    fprintf(stdout,"Tx unaggregated frame completions           -- %d\n",i_req.d_tgtstats->ast_11n_tgt.tx_compunaggr);
    fprintf(stdout,"Tx aggregated completions                   -- %d\n",i_req.d_tgtstats->ast_11n_tgt.tx_compaggr);
    fprintf(stdout,"Tx rate in Kbps                             -- %d\n",i_req.d_tgtstats->ast_11n_tgt.tx_rate);
    fprintf(stdout,"Rx rate in Kbps                             -- %d\n",i_req.d_tgtstats->ast_11n_tgt.rx_rate);
    fprintf(stdout,"Tx rssi combined                            -- %d\n",i_req.d_tgtstats->ast_11n_tgt.ast_tx_rssi_combined);
    fprintf(stdout,"Tx rssi ctl0                                -- %d\n",i_req.d_tgtstats->ast_11n_tgt.ast_tx_rssi_ctl0);
    fprintf(stdout,"Tx rssi ctl1                                -- %d\n",i_req.d_tgtstats->ast_11n_tgt.ast_tx_rssi_ctl1);
    fprintf(stdout,"Tx rssi ctl2                                -- %d\n",i_req.d_tgtstats->ast_11n_tgt.ast_tx_rssi_ctl2);
    fprintf(stdout,"Tx rssi ext0                                -- %d\n",i_req.d_tgtstats->ast_11n_tgt.ast_tx_rssi_ext0);
    fprintf(stdout,"Tx rssi ext1                                -- %d\n",i_req.d_tgtstats->ast_11n_tgt.ast_tx_rssi_ext1);
    fprintf(stdout,"Tx rssi ext2                                -- %d\n",i_req.d_tgtstats->ast_11n_tgt.ast_tx_rssi_ext2);
   /* fprintf(stdout,"Tx aggr good completions                    -- %d\n",i_req.d_tgtstats->ast_11n_tgt.txaggr_compgood);
    fprintf(stdout,"Tx aggr unacked subframes               -- %d\n",i_req.d_tgtstats->ast_11n_tgt.txaggr_compretries);
    fprintf(stdout,"Tx aggr old frames requeued             -- %d\n",i_req.d_tgtstats->ast_11n_tgt.txaggr_prepends);
    fprintf(stdout,"Data underrun for an aggregate          -- %d\n",i_req.d_tgtstats->ast_11n_tgt.txaggr_data_urun);
    fprintf(stdout,"Delimiter underrun for an aggregate     -- %d\n",i_req.d_tgtstats->ast_11n_tgt.txaggr_delim_urun);
    */
    
    if(tgt11n_phystats)
        free(tgt11n_phystats);

    return 0;
 }

int athcfg_reg_get(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    athcfg_wcmd_reg_t reg;
    a_uint32_t start_addr, end_addr;

    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    memset(&reg, 0, sizeof(athcfg_wcmd_reg_t));

    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }

    int ret = sscanf(args[0], "0x%x", &start_addr);
    if (ret != 1) {
        fprintf(stderr, "Register address must be in hex\n");
        return 0; 
    }

   if (nargs == 2) {
        ret = sscanf(args[1], "0x%x", &end_addr);
        if (ret != 1) {
           fprintf(stderr, "Register address must be in hex\n");
           return 0; 
        }
    } else {
        end_addr = start_addr;
    }

    if (!IS_WORD_ALIGNED(start_addr) || !IS_WORD_ALIGNED(end_addr)) {
        fprintf(stderr, "Register addresses must be on a 4-byte boundary\n");
        return 0;
    }

    if (end_addr < start_addr) {
        fprintf(stderr, "end address must be greater than the start address\n");
        return 0;
    }

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_GET_REG;   

    for (reg.addr = start_addr; reg.addr <= end_addr; reg.addr += 4) {
    i_req.d_reg = reg;
        if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
            return EIO;
        fprintf(stdout, "reg_addr: 0x%x reg_val: 0x%x\n", reg.addr, i_req.d_reg.val);
    }

    return 0;
}

int athcfg_reg_set(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    athcfg_wcmd_reg_t reg;
    int ret1, ret2;

    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    memset(&reg, 0, sizeof(athcfg_wcmd_reg_t));

    assert(nargs == 2);
    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }

    ret1 = sscanf(args[0], "0x%x", &reg.addr);
    ret2 = sscanf(args[1], "0x%x", &reg.val);
    if (ret1 != 1 || ret2 != 1) {
        fprintf(stdout, "Register address and value must be in hex\n");
        return 0;
    }

    if (!IS_WORD_ALIGNED(reg.addr)) {
        fprintf(stderr, "Register addresses must be on a 4-byte boundary\n");
        return 0;
    }

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_SET_REG;
    i_req.d_reg = reg;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "reg_addr: 0x%x set to reg_val: 0x%x\n", reg.addr, reg.val);

    return 0;
}
#ifdef OMNI_MX_LED
/********************* SAH *************************************/

/**
 * @brief Handlers for GET/Set LED-PIN.
 */
int
athcfg_ledstats_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    
    assert (nargs == 0);
    args[0] = NULL; 

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
         fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
         return E2BIG;
    }

    i_req.d_datum = 0; 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_LEDSTATS;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
         return EIO;
 
    fprintf(stdout, "LED-STATS -- %d\n", i_req.d_datum);
    return 0;
}

int
athcfg_ledstats_set(char *ifrn_name, int nargs, char *args[])
{
     athcfg_wcmd_t i_req; 
     memset(&i_req,0, sizeof(athcfg_wcmd_t));

     assert (nargs == 1);
 
     if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
          fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
          return E2BIG;
     }
     i_req.d_datum = 0;

     i_req.d_datum = atoi(args[0]);
     strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
     i_req.type = ATHCFG_WCMD_SET_LEDSTATS;

     if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
          return EIO;
 
     fprintf(stdout, "LED-STATS -- %d\n", i_req.d_datum);
     return 0;
}

/**********************************************************************************/
#endif /*OMNI_MX_LED*/

int athcfg_nominal_nf_get(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_NOMINAL_NF;
    i_req.data.nf.get = 1;
    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    printf("\nNominal Noise Floor is %d\n",i_req.data.nf.val);
    return 0;
}

int athcfg_nominal_nf_set(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    a_int16_t val;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_NOMINAL_NF;
    val  = atoi(args[0]);
    if( val < MIN_NOISE_FLOOR_VALUE ) {
        printf(" Nominal noise floor value cannot be less then %d\n", MIN_NOISE_FLOOR_VALUE);
        return EIO;    
    }
        
    if( val > MAX_NOISE_FLOOR_VALUE ) {
        printf(" Nominal noise floor value cannot more then %d\n", MAX_NOISE_FLOOR_VALUE);
        return EIO;    
    }
    i_req.data.nf.get = 0;
    i_req.data.nf.val = val;
    
    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;
    
    return 0;
}

int athcfg_minimum_nf_get(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_MIN_NOISE_FLOOR;
    i_req.data.nf.get = 1;
    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    printf("\nMinium Noise Floor is %d\n",i_req.data.nf.val);
        return 0;

    return 0;
}

int athcfg_minimum_nf_set(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    a_int16_t val;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_MIN_NOISE_FLOOR;
    val  = atoi(args[0]);
    if( val < MIN_NOISE_FLOOR_VALUE ) {
        printf(" Nominal noise floor value cannot be less then %d\n", MIN_NOISE_FLOOR_VALUE);
        return EIO;    
    }
        
    if( val > MAX_NOISE_FLOOR_VALUE ) {
        printf(" Nominal noise floor value cannot more then %d\n", MAX_NOISE_FLOOR_VALUE);
        return EIO;    
    }
    i_req.data.nf.get = 0;
    i_req.data.nf.val = val;
    
    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;
    
    return 0;
}
int athcfg_maximum_nf_get(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_MAX_NOISE_FLOOR;
    i_req.data.nf.get = 1;
    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    printf("\nMinium Noise Floor is %d\n",i_req.data.nf.val);
            return 0;

    return 0;
}

int athcfg_maximum_nf_set(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    a_int16_t val;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_MAX_NOISE_FLOOR;
    val  = atoi(args[0]);
    if( val < MIN_NOISE_FLOOR_VALUE ) {
        printf(" Nominal noise floor value cannot be less then %d\n", MIN_NOISE_FLOOR_VALUE);
        return EIO;    
    }
        
    if( val > MAX_NOISE_FLOOR_VALUE ) {
        printf(" Nominal noise floor value cannot more then %d\n", MAX_NOISE_FLOOR_VALUE);
        return EIO;    
    }
    i_req.data.nf.get = 0;
    i_req.data.nf.val = val;
    
    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;
    
    return 0;
}

int athcfg_nf_delta_get(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_NF_DELTA;
    i_req.data.nf.get = 1;
    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    printf("\nNoise Floor Delta is %d\n",i_req.data.nf.val);
            return 0;

    return 0;
}

int athcfg_nf_delta_set(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    a_int16_t val;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_NF_DELTA;
    val  = atoi(args[0]);
    if( val < 0 ) {
        printf(" Noise Floor Delta should be greter then 0");
        return EIO;    
    }
        
    i_req.data.nf.get = 0;
    i_req.data.nf.val = val;
    
    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;
    
    return 0;
}
int athcfg_nf_weight_get(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_NF_WEIGHT;
    i_req.data.nf.get = 1;
    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    printf("\nNoise Floor Weight is %d\n",i_req.data.nf.val);

    return 0;
}

int athcfg_nf_weight_set(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    a_int16_t val;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_NF_WEIGHT;
    val  = atoi(args[0]);
    if( val < 1 ) {
        printf(" Noise Floor Weight should be integer, 1 or greater");
        return EIO;    
    }
        
    i_req.data.nf.get = 0;
    i_req.data.nf.val = val;
    
    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;
    
    return 0;
}
int athcfg_nf_switch_get(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_CHANNEL_SWITCH;
    i_req.data.nf.get = 1;
    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    printf("\nNF based channel Switching is  %s\n",i_req.data.nf.val? "Enabled":"Disabled");

    return 0;
}

int athcfg_nf_switch_set(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    a_int16_t val;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_CHANNEL_SWITCH;
    val  = atoi(args[0]) ? 1:0;
        
    i_req.data.nf.get = 0;
    i_req.data.nf.val = val;
    
    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;
    
    return 0;
}

int athcfg_ant_div_enable(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_ENABLE_DIV;
        
    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;
    
    return 0;
}

int athcfg_ant_div_disable(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_DISABLE_DIV;
        
    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;
    
    return 0;
}

int athcfg_ant_div_get_param(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_GET_DIV_PARAM;
        
    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;
    
    return 0;
}

int athcfg_ant_div_set_def_ant(char *ifname, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    a_int16_t val;

    if (strlen(ifname) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifname);
        return E2BIG;
    }
    

    strncpy(i_req.if_name, ifname, strlen(ifname));
    i_req.type = ATHCFG_WCMD_SET_DEF_TX;
    val  = atoi(args[0]) ? 1:0;
    i_req.data.divParam.val = val;
        
    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;
    
    return 0;
}


int
athcfg_ether_dongle_mac_get_cmd(char *ifrn_name, int nargs, char *args[])
{
    int count = 0; 
    athcfg_wcmd_t i_req; 
    athcfg_ethaddr_t mac; 

    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    memset(&mac, 0, sizeof(athcfg_ethaddr_t));
    args[0] = NULL;

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_ETHER_DONGLE_MAC;
    i_req.d_mac = mac;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;
    
    printf("ether dongle mac --- ");    
    for(count = 0; count < 6; count++){
        fprintf(stdout, "%02x", i_req.d_mac.addr[count]);
    if(count <5)
        printf(":");
    }
    printf("\n");


    return 0;
}

int
athcfg_ether_dongle_mac_set_cmd(char *ifrn_name, int nargs, char *args[])
{
    int count = 0;
    athcfg_wcmd_t i_req; 
    athcfg_ethaddr_t mac; 
    int tmp[6];

    memset(&i_req,0, sizeof(athcfg_wcmd_t));
    memset(&mac, 0, sizeof(athcfg_ethaddr_t));

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    if( (strlen(args[0]) ==  ATHCFG_WCMD_MAC_STR_LEN) ) {
        sscanf(args[0],"%x:%x:%x:%x:%x:%x", 
                 (unsigned int *)&tmp[0],\
                 (unsigned int *)&tmp[1],\
                 (unsigned int *)&tmp[2],\
                 (unsigned int *)&tmp[3],\
                 (unsigned int *)&tmp[4],\
                 (unsigned int *)&tmp[5] );
    }   
    else {
        printf("!! ERROR !!\n");
        printf("Please enter mac in format of -- 00:11:22:33:44:55\n");
        ABORT_ARG_SIZE(ifrn_name, "MAC", ATHCFG_WCMD_ADDR_LEN);
    }

    for(count=0; count < ATHCFG_ETH_LEN; count++)
        mac.addr[count] = tmp[count];

    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_ETHER_DONGLE_MAC;
    i_req.d_mac = mac;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;
    
    printf("ETHER DONGLE MAC -- ");
    for(count = 0; count < 6; count++){
        //fprintf(stdout, "%x", i_req.d_mac.addr[count]);
        fprintf(stdout, "%x", mac.addr[count]);
    if(count <5)
        printf(":");
    }
    printf("\n");
    return 0;
}

int
athcfg_sta_assoc_set_cmd(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
	int count;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_STA_ASSOC;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

	return 0;
}


 /**
 * @brief Handlers for RADIO on/off
 */
int
athcfg_radio_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    athcfg_wcmd_radio_t radio; 
    
    memset(&i_req,0, sizeof(athcfg_wcmd_t));    
    memset(&radio, 0, sizeof(athcfg_wcmd_radio_t));
    
    args[0] = NULL;
    
    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_RADIO;
    i_req.d_radio = radio;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    if ( i_req.d_radio.enable )
        fprintf(stdout, "RADIO is enabled\n");
    else
        fprintf(stdout, "RADIO is disabled\n");
            
    return 0;
}

int
athcfg_radio_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    athcfg_wcmd_radio_t radio; 
    
    memset(&i_req,0, sizeof(athcfg_wcmd_t));    
    memset(&radio, 0, sizeof(athcfg_wcmd_radio_t));

    assert (nargs == 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    
    radio.enable = atoi(args[0]);
        
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_RADIO;
    i_req.d_radio = radio;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    fprintf(stdout, "RADIO -- %d\n", i_req.d_radio.enable);

    return 0;
}

int
athcfg_spec_chan_scan_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_spec_chan_scan_t scscan;
    memset(&scscan, 0, sizeof(athcfg_wcmd_spec_chan_scan_t));

    assert (nargs >= 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
   
    scscan.spec_chan_scan = atoi(args[0]); /* enable/disable(1/0) specify channel scan */
    
        
   
    if (scscan.spec_chan_scan)
    {
        if (args[1] != NULL)
            scscan.spec_chan_num = atoi(args[1]);
        else {
            printf("!! ERROR !!\n");
            printf("Please enter specify channel number\n");            
            return EINVAL;
        }
    }
                 
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_SPEC_CHAN_SCAN;
    i_req.d_spec_chan_scan = scscan;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    return 0;
}


/**
 * @brief Handlers for Get/Set hidden ESSID scan
 */
int
athcfg_hidden_ssid_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req; 
    athcfg_wcmd_hiddenssid_scan_t hiddenssidscan; 
    
    memset(&i_req,0, sizeof(athcfg_wcmd_t));    
    memset(&hiddenssidscan, 0, sizeof(athcfg_wcmd_hiddenssid_scan_t));
    
    args[0] = NULL;
    
    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
    
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_HIDDEN_SSID;
    i_req.d_hiddenssid_scan = hiddenssidscan;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    if ( i_req.d_hiddenssid_scan.enable )
        fprintf(stdout, "Hidden-ssid scan is enabled\n");
    else
        fprintf(stdout, "Hidden-ssid scan is disabled\n");
            
    return 0;
}

int
athcfg_hidden_ssid_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_hiddenssid_scan_t hiddenssidscan;
    memset(&hiddenssidscan, 0, sizeof(athcfg_wcmd_hiddenssid_scan_t));

    assert (nargs >= 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }
   
    hiddenssidscan.enable = atoi(args[0]); /* enable/disable(1/0) */        
                   
    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_HIDDEN_SSID;
    i_req.d_hiddenssid_scan = hiddenssidscan;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    return 0;
}

int
athcfg_keepscanlist_set(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_keepscanlist_t keepscanlist;  
    memset(&keepscanlist, 0, sizeof(athcfg_wcmd_keepscanlist_t));
    assert (nargs >= 1);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    keepscanlist.enable = atoi(args[0]); /* enable/disable(1/0) */

    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_SET_KEEPSCANLIST;
    i_req.d_keepscanlist = keepscanlist;

    if (athcfg_do_ioctl(SIOCSADFWIOCTL, &i_req) < 0)
        return EIO;

    return 0;
}

int
athcfg_htrates_get(char *ifrn_name, int nargs, char *args[])
{
    int i;
    athcfg_wcmd_t i_req;
    memset(&i_req,0, sizeof(athcfg_wcmd_t));

    athcfg_wcmd_htrates_t htrates;
    memset(&htrates, 0, sizeof(athcfg_wcmd_htrates_t));

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_HTRATES;
    i_req.d_htrates = &htrates;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    for(i = 0; i < i_req.d_htrates->len; i++) {
        printf("rate[%d]: 0x%x\n", i, i_req.d_htrates->htrates[i]);
    }
    printf("\n");

    return 0;
}

int
athcfg_wmi_timeout_get(char *ifrn_name, int nargs, char *args[])
{
    athcfg_wcmd_t i_req;

    memset(&i_req, 0, sizeof(athcfg_wcmd_t));

    assert (nargs == 0);

    if (strlen(ifrn_name) > sizeof(i_req.if_name)) {
        fprintf(stderr, "Ifname too big %s!\n", ifrn_name);
        return E2BIG;
    }

    strncpy(i_req.if_name, ifrn_name, strlen(ifrn_name));
    i_req.type = ATHCFG_WCMD_GET_WMI_STATS_TIMEOUT;

    if (athcfg_do_ioctl(SIOCGADFWIOCTL, &i_req) < 0)
        return EIO;

    printf("[athcfg] wmi timeout: %u\n", i_req.d_wmi_timeout);

    return 0;
}
