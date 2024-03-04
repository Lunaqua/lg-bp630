/********************************************************************************************
 *     LEGAL DISCLAIMER
 *
 *     (Header of MediaTek Software/Firmware Release or Documentation)
 *
 *     BY OPENING OR USING THIS FILE, BUYER HEREBY UNEQUIVOCALLY ACKNOWLEDGES AND AGREES
 *     THAT THE SOFTWARE/FIRMWARE AND ITS DOCUMENTATIONS ("MEDIATEK SOFTWARE") RECEIVED
 *     FROM MEDIATEK AND/OR ITS REPRESENTATIVES ARE PROVIDED TO BUYER ON AN "AS-IS" BASIS
 *     ONLY. MEDIATEK EXPRESSLY DISCLAIMS ANY AND ALL WARRANTIES, EXPRESS OR IMPLIED,
 *     INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
 *     A PARTICULAR PURPOSE OR NONINFRINGEMENT. NEITHER DOES MEDIATEK PROVIDE ANY WARRANTY
 *     WHATSOEVER WITH RESPECT TO THE SOFTWARE OF ANY THIRD PARTY WHICH MAY BE USED BY,
 *     INCORPORATED IN, OR SUPPLIED WITH THE MEDIATEK SOFTWARE, AND BUYER AGREES TO LOOK
 *     ONLY TO SUCH THIRD PARTY FOR ANY WARRANTY CLAIM RELATING THERETO. MEDIATEK SHALL ALSO
 *     NOT BE RESPONSIBLE FOR ANY MEDIATEK SOFTWARE RELEASES MADE TO BUYER'S SPECIFICATION
 *     OR TO CONFORM TO A PARTICULAR STANDARD OR OPEN FORUM.
 *
 *     BUYER'S SOLE AND EXCLUSIVE REMEDY AND MEDIATEK'S ENTIRE AND CUMULATIVE LIABILITY WITH
 *     RESPECT TO THE MEDIATEK SOFTWARE RELEASED HEREUNDER WILL BE, AT MEDIATEK'S OPTION,
 *     TO REVISE OR REPLACE THE MEDIATEK SOFTWARE AT ISSUE, OR REFUND ANY SOFTWARE LICENSE
 *     FEES OR SERVICE CHARGE PAID BY BUYER TO MEDIATEK FOR SUCH MEDIATEK SOFTWARE AT ISSUE.
 *
 *     THE TRANSACTION CONTEMPLATED HEREUNDER SHALL BE CONSTRUED IN ACCORDANCE WITH THE LAWS
 *     OF THE STATE OF CALIFORNIA, USA, EXCLUDING ITS CONFLICT OF LAWS PRINCIPLES.
 ************************************************************************************************/

#ifndef X_CKGEN_H
#define X_CKGEN_H

#if (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8530) 

typedef enum
{
    e_CLK_RISC,	                 // 0
    e_CLK_ADSP,	                 // 1
    e_CLK_ADSP2,	         // 2
    e_CLK_FLASH,	         // 3
    e_CLK_NFLASH,	         // 4
    e_CLK_SACD,	                 // 5
    e_CLK_RISC2,	         // 6
    e_CLK_SLOW_RISC,             // 7
    e_CLK_VDEC,	                 // 8
    e_CLK_MC,	                 // 9
    e_CLK_OSD,	                 // 10
    e_CLK_PNG,	                 // 11
    e_CLK_RESZ,	                 // 12
    e_CLK_GRAPH,	         // 13
    e_CLK_NR,	                 // 14
    e_CLK_DEMUX,                 // 15
    e_CLK_IR_DIV,	         // 16
    e_CLK_SD,	                 // 17
    e_CLK_MS,	                 // 18
    e_CLK_ABIST,	         // 19
    e_CLK_MAX
} e_CLK_T;

#define REG_RW_PAD_CTRL_6              0x0118        //Pad PU/PD/SMT/SR/E2/E4 Control Register 6
#define REG_RW_PAD_CTRL_7              0x011C        //Pad PU/PD/SMT/SR/E2/E4 Control Register 7
#define REG_RW_PAD_CTRL_1              0x0104        //Pad PU/PD/SMT/SR/E2/E4 Control Register 1
#define REG_RW_PAD_CTRL_0              0x0100        //Pad PU/PD/SMT/SR/E2/E4 Control Register 0
  #define SPDIF_DV_MASK                     (3<<28)
  #define SPDIF_DV_CUR_0                    (0<<28)
  #define SPDIF_DV_CUR_1                    (1<<28)
  #define SPDIF_DV_CUR_2                   (2<<28)
  #define SPDIF_DV_CUR_3                    (3<<28)
  #define AOMCLK_DRV_CUR_0 			(0<<0) // 4mA
  #define AOMCLK_DRV_CUR_1 			(1<<0) // 8mA
  #define AOMCLK_DRV_CUR_2 			(2<<0) // 12mA
  #define AOMCLK_DRV_CUR_3 			(3<<0) // 16mA
#define REG_RW_PAD_CTRL_10             0x0128        //Pad PU/PD/SMT/SR/E2/E4 Control Register 10

#define REG_RW_CLK_CFG2                  0x0078             //Clock Selection Configuration 2
  #define CLK_PDN_ABIST                          (1U << 31) //turn off ABIST frequency meter clock
  #define CLK_CLK_ABIST_SEL_MASK                 0x70000000
  #define CLK_CLK_ABIST_SEL_OFFSET               28         //Selection of ABIST clock frequency
  #define CLK_CLK_ABIST_SEL_27M                  0
  #define CLK_CLK_ABIST_SEL_AD_XTAL27M_CK        1
  #define CLK_CLK_ABIST_SEL_AD_MEMPLL_MONCK      2
  #define CLK_CLK_ABIST_SEL_AD_PLL_MONCK         3
  #define CLK_CLK_ABIST_SEL_AD_HDMI_LBOUT        4
  #define CLK_CLK_ABIST_SEL_USBPHY_CK            5
  #define CLK_PDN_MS                             (1U << 23) //turn off MS
  #define CLK_CLK_MS_SEL_MASK                    0x007F0000
  #define CLK_CLK_MS_SEL_OFFSET                  16         //Selection of MS clock frequency
  #define CLK_CLK_MS_SEL_27M                     0x00
  #define CLK_CLK_MS_SEL_SYSPLL1_1_8             0x10
  #define CLK_CLK_MS_SEL_SYSPLL1_1_10            0x20
  #define CLK_CLK_MS_SEL_SYSPLL1_1_12            0x30
  #define CLK_CLK_MS_SEL_SYSPLL2_1_12            0x40
  #define CLK_CLK_MS_SEL_DMPLL_1_8               0x50
  #define CLK_CLK_MS_SEL_SYSPLL2_1_18            0x60
  #define CLK_CLK_MS_SEL_27M_1_2                 0x71
  #define CLK_CLK_MS_SEL_27M_1_4                 0x72
  #define CLK_CLK_MS_SEL_27M_1_8                 0x73
  #define CLK_CLK_MS_SEL_27M_1_16                0x74
  #define CLK_CLK_MS_SEL_27M_1_32                0x75
  #define CLK_CLK_MS_SEL_27M_1_64                0x76
  #define CLK_CLK_MS_SEL_27M_1_128               0x77
  #define CLK_CLK_MS_SEL_27M_1_256               0x78
  #define CLK_PDN_SD                             (1U << 15) //turn off SD
  #define CLK_CLK_SD_SEL_MASK                    0x00007F00
  #define CLK_CLK_SD_SEL_OFFSET                  8          //Selection of SD clock frequency
  #define CLK_CLK_SD_SEL_27M                     0x00
  #define CLK_CLK_SD_SEL_SYSPLL1_1_8             0x10
  #define CLK_CLK_SD_SEL_SYSPLL1_1_10            0x20
  #define CLK_CLK_SD_SEL_SYSPLL1_1_12            0x30
  #define CLK_CLK_SD_SEL_SYSPLL2_1_12            0x40
  #define CLK_CLK_SD_SEL_DMPLL_1_8               0x50
  #define CLK_CLK_SD_SEL_SYSPLL2_1_18            0x60
  #define CLK_CLK_SD_SEL_27M_1_2                 0x71
  #define CLK_CLK_SD_SEL_27M_1_4                 0x72
  #define CLK_CLK_SD_SEL_27M_1_8                 0x73
  #define CLK_CLK_SD_SEL_27M_1_16                0x74
  #define CLK_CLK_SD_SEL_27M_1_32                0x75
  #define CLK_CLK_SD_SEL_27M_1_64                0x76
  #define CLK_CLK_SD_SEL_27M_1_128               0x77
  #define CLK_CLK_SD_SEL_27M_1_256               0x78
  #define CLK_PDN_IR                             (1U << 7)  //turn off IR
  #define CLK_CLK_IR_DIV_SEL_MASK                0x0000000F
  #define CLK_CLK_IR_DIV_SEL_OFFSET              0          //Select divisor used to divide 27MHz clock, for IR clock
  #define CLK_CLK_IR_DIV_SEL_27M                 0
  #define CLK_CLK_IR_DIV_SEL_27M_1_2             1
  #define CLK_CLK_IR_DIV_SEL_27M_1_4             2
  #define CLK_CLK_IR_DIV_SEL_27M_1_8             3
  #define CLK_CLK_IR_DIV_SEL_27M_1_16            4
  #define CLK_CLK_IR_DIV_SEL_27M_1_32            5
  #define CLK_CLK_IR_DIV_SEL_27M_1_64            6
  #define CLK_CLK_IR_DIV_SEL_27M_1_128           7
  #define CLK_CLK_IR_DIV_SEL_27M_1_256           8

 #define REG_RW_PAD_CFG_5               0x00D4        //Pad Multifunction Configuration Register 5
    #define RW_PAD_CFG_5_VOUTCLK1_SSEL_MASK (3<<24)
    #define RW_PAD_CFG_5_VOUTCLK1_SSEL  (0 << 24)

#elif ((CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8550) ||  (CONFIG_CHIP_VER_CURR == CONFIG_CHIP_VER_MT8555))

typedef enum
{
    e_CLK_RISC,	           // 0
    e_CLK_ADSP,	           // 1
    e_CLK_ADSP2,	         // 2
    e_CLK_FLASH,	         // 3
    e_CLK_NFLASH,	         // 4
    e_CLK_SLOW_RISC,       // 5
    e_CLK_VDEC,	           // 6
    e_CLK_MC,	             // 7
    e_CLK_OSD,	           // 8
    e_CLK_PNG,	           // 9
    e_CLK_RESZ,	           // 10
    e_CLK_GRAPH,	         // 11
    e_CLK_NR,	             // 12
    e_CLK_DEMUX,           // 13
    e_CLK_IR_DIV,	         // 14
    e_CLK_SD,	             // 15
    e_CLK_MS,	             // 16
    e_CLK_ABIST,	         // 17
    e_CLK_SVO_STDBY,       // 18
    e_CLK_GCPU,            // 19
    e_CLK_MVDO2,           // 20
    e_CLK_MAX
} e_CLK_T;

#define REG_RW_CLK_CFG2                 0x0078              //Clock Selection Configuration 2
  #define CLK_PDN_ABIST                          (1U << 31) //turn off ABIST frequency meter clock
  #define CLK_CLK_ABIST_SEL_MASK                 0x70000000
  #define CLK_CLK_ABIST_SEL_OFFSET               28         //Selection of ABIST clock frequency
  #define CLK_CLK_ABIST_SEL_27M                  0
  #define CLK_CLK_ABIST_SEL_AD_XTAL27M_CK        1
  #define CLK_CLK_ABIST_SEL_AD_MEMPLL_MONCK      2
  #define CLK_CLK_ABIST_SEL_AD_PLL_MONCK         3
  #define CLK_CLK_ABIST_SEL_AD_HDMI_LBOUT        4
  #define CLK_CLK_ABIST_SEL_USBPHY_CK30          5
  #define CLK_CLK_ABIST_SEL_USBPHY_CK240         6
  #define CLK_CLK_ABIST_SEL_CLK_IR               7
  #define CLK_PDN_MS                             (1U << 23) //turn off MS
  #define CLK_CLK_MS_SEL_MASK                    0x007F0000
  #define CLK_CLK_MS_SEL_OFFSET                  16         //Selection of MS clock frequency
  #define CLK_CLK_MS_SEL_27M                     0x00
  #define CLK_CLK_MS_SEL_SYSPLL1_1_8             0x01  // 54Mhz    //mhzhang
  #define CLK_CLK_MS_SEL_SYSPLL1_1_10            0x02 // 43.2Mhz  //mhzhang
  #define CLK_CLK_MS_SEL_SYSPLL1_1_12            0x03  // 36Mhz  //mhzhang
  #define CLK_CLK_MS_SEL_SYSPLL2_1_12            0x04   // 50Mhz  //mhzhang
  #define CLK_CLK_MS_SEL_DMPLL_1_8               0x05   // 37.5Mhz  //mhzhang
  #define CLK_CLK_MS_SEL_SYSPLL2_1_18            0x06   // 33.33Mhz  //mhzhang
  //added for 8550 //mhzhang
  #define CLK_CLK_MS_SEL_SYSPLL2_1_26       0x07  //23.08Mhz //mhzhang
  #define CLK_CLK_MS_SEL_SYSPLL1_1_24         0x08  // 18Mhz //mhzhang
  
  #define CLK_CLK_MS_SEL_27M_1_2                 0x09   // 13.5Mhz  //mhzhang
  #define CLK_CLK_MS_SEL_27M_1_4                 0x19    // 6.75Mhz  //mhzhang
  #define CLK_CLK_MS_SEL_27M_1_8                 0x29   // 3.38Mhz  //mhzhang
  #define CLK_CLK_MS_SEL_27M_1_16                0x39   // 1.69Mhz  //mhzhang
  #define CLK_CLK_MS_SEL_27M_1_32                0x49   // 847.75Khz  //mhzhang
  #define CLK_CLK_MS_SEL_27M_1_64                0x59   // 421.885Khz  //mhzhang
  #define CLK_CLK_MS_SEL_27M_1_128               0x69   // 210.94Khz  //mhzhang
  #define CLK_CLK_MS_SEL_27M_1_256               0x79   // 105.47Khz  //mhzhang
  #define CLK_PDN_SD                             (1U << 15) //turn off SD
  #define CLK_CLK_SD_SEL_MASK                    0x00007F00
  #define CLK_CLK_SD_SEL_OFFSET                  8          //Selection of SD clock frequency
  #define CLK_CLK_SD_SEL_27M                     0x00
  #define CLK_CLK_SD_SEL_SYSPLL1_1_8             0x01    // 54Mhz  //mhzhang
  #define CLK_CLK_SD_SEL_SYSPLL1_1_10            0x02    // 43.2Mhz  //mhzhang
  #define CLK_CLK_SD_SEL_SYSPLL1_1_12            0x03    // 36Mhz  //mhzhang
  #define CLK_CLK_SD_SEL_SYSPLL2_1_12            0x04    // 50Mhz  //mhzhang
  #define CLK_CLK_SD_SEL_DMPLL_1_8               0x05    // 37.5Mhz  //mhzhang
  #define CLK_CLK_SD_SEL_SYSPLL2_1_18            0x06    // 33.33Mhz  //mhzhang
  //added for 8550 //mhzhang
  #define CLK_CLK_SD_SEL_SYSPLL2_1_26        0x07  // 23.08 Mhz 
  #define CLK_CLK_SD_SEL_SYSPLL1_1_24        0x08 // 18 Mhz
  
  #define CLK_CLK_SD_SEL_27M_1_2                 0x09    // 13.5Mhz  //mhzhang
  #define CLK_CLK_SD_SEL_27M_1_4                 0x19    // 6.75Mhz  //mhzhang
  #define CLK_CLK_SD_SEL_27M_1_8                 0x29    // 3.38Mhz  //mhzhang
  #define CLK_CLK_SD_SEL_27M_1_16                0x39    // 1.69Mhz  //mhzhang
  #define CLK_CLK_SD_SEL_27M_1_32                0x49    // 843.75 Khz  //mhzhang
  #define CLK_CLK_SD_SEL_27M_1_64                0x59    // 421.885 Khz  //mhzhang
  #define CLK_CLK_SD_SEL_27M_1_128               0x69    // 210.94 Khz  //mhzhang
  #define CLK_CLK_SD_SEL_27M_1_256               0x79    // 105.47 Khz  //mhzhang
  #define CLK_PDN_IR                             (1U << 7)  //turn off IR
  #define CLK_CLK_IR_DIV_SEL_MASK                0x0000000F
  #define CLK_CLK_IR_DIV_SEL_OFFSET              0          //Select divisor used to divide 27MHz clock, for IR clock
  #define CLK_CLK_IR_DIV_SEL_27M                 0
  #define CLK_CLK_IR_DIV_SEL_27M_1_2             1
  #define CLK_CLK_IR_DIV_SEL_27M_1_4             2
  #define CLK_CLK_IR_DIV_SEL_27M_1_8             3
  #define CLK_CLK_IR_DIV_SEL_27M_1_16            4
  #define CLK_CLK_IR_DIV_SEL_27M_1_32            5
  #define CLK_CLK_IR_DIV_SEL_27M_1_64            6
  #define CLK_CLK_IR_DIV_SEL_27M_1_128           7
  #define CLK_CLK_IR_DIV_SEL_27M_1_256           8
  
#endif

#endif  // X_CKGEN_H

