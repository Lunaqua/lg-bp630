/*******************************************************************************
* ADOBE SYSTEMS INCORPORATED
* Copyright 2007 Adobe Systems Incorporated
* All Rights Reserved.
*
* NOTICE:  Adobe permits you to use, modify, and distribute this file in
* accordance with the  terms of the Adobe license agreement accompanying it. If
* you have received this file from a source other than Adobe, then your use,
* modification, or distribution of it requires the prior written permission of
* Adobe.
*******************************************************************************/

#ifndef _INCLUDE_AE_STAGECRAFT_STAGECRAFTKEYDEFS_H
#define _INCLUDE_AE_STAGECRAFT_STAGECRAFTKEYDEFS_H

////////////////////////////////////////////////////////////////////////////////
// Standard Flash key symbols in AEKEY space, defined so they don't collide
// with ascii characters.
//
// Example usage:
//   Call IStagecraft::DispatchKeyDown() or StageWindow::DispatchKeyDown() with
//   the AEKEY code AEKEY_UP to dispatch the AS2 Key.UP key or the AS3
//   Keyboard.UP key.  To dispatch an ampersand (which has the same ascii value
//   as Key.UP: 38), call DispatchKeyDown() with the ascii value directly - e.g.
//   DispatchKeyDown(38) or DispatchKeyDown('&').
//
#define AEKEY_ALT                       0x00400012 ///< used to dispatch Key.ALT
#define AEKEY_BACKSPACE                 0x00400008 ///< used to dispatch Key.BACKSPACE
#define AEKEY_CAPSLOCK                  0x00400014 ///< used to dispatch Key.CAPSLOCK
#define AEKEY_CONTROL                   0x00400011 ///< used to dispatch Key.CONTROL
#define AEKEY_INSERT                    0x0040002D ///< used to dispatch Key.INSERT
#define AEKEY_DELETEKEY                 0x0040002E ///< used to dispatch Key.DELETEKEY
#define AEKEY_DOWN                      0x00400028 ///< used to dispatch Key.DOWN
#define AEKEY_END                       0x00400023 ///< used to dispatch Key.END
#define AEKEY_ENTER                     0x0040000D ///< used to dispatch Key.ENTER
#define AEKEY_ESCAPE                    0x0040001B ///< used to dispatch Key.ESCAPE
#define AEKEY_HOME                      0x00400024 ///< used to dispatch Key.HOME
#define AEKEY_LEFT                      0x00400025 ///< used to dispatch Key.LEFT
#define AEKEY_PGDN                      0x00400022 ///< used to dispatch Key.PGDN
#define AEKEY_PGUP                      0x00400021 ///< used to dispatch Key.PGUP
#define AEKEY_RIGHT                     0x00400027 ///< used to dispatch Key.RIGHT
#define AEKEY_SHIFT                     0x00400010 ///< used to dispatch Key.SHIFT
#define AEKEY_SPACE                     0x00400020 ///< used to dispatch Key.SPACE
#define AEKEY_TAB                       0x00400009 ///< used to dispatch Key.TAB
#define AEKEY_UP                        0x00400026 ///< used to dispatch Key.UP

////////////////////////////////////////////////////////////////////////////////
// Special control symbols in AEKEY space
//
// Example usage:
//   Call StageWindow::DispatchKeyDown() with AEKEY_TERMINATEANIMATION to
//   unconditionally terminate the a specific StageWindow flash instance.
//   (Or use IStagecraft::DispatchKeyDown(AEKEY_TERMINATEANIMATION) to terminate
//   the currently active StageWindow).
//
#define AEKEY_TERMINATEANIMATION        0x00400100 ///< ctrl: unconditional terminate

////////////////////////////////////////////////////////////////////////////////
// Digital Home Key Codes
//
// Example Usage:
//    Dispatch an AEKEY_CODE code, and the equivalent
//    AS2 Key.CODE or AS3 Keyboard.CODE will result.
//      e.g. DispatchKeyDown(AEKEY_RED) results in Key.RED
//
#define AEKEY_POWER                     0x01000000 ///< basic power toggle
#define AEKEY_VOLUME_UP                 0x01000001 ///< volume up
#define AEKEY_VOLUME_DOWN               0x01000002 ///< volume down
#define AEKEY_VOLUME_MUTE               0x01000003 ///< volume mute
#define AEKEY_CHANNEL_UP                0x01000004 ///< channel up
#define AEKEY_CHANNEL_DOWN              0x01000005 ///< channel down
#define AEKEY_RECORD                    0x01000006 ///< record item or engage record transport mode
#define AEKEY_PLAY                      0x01000007 ///< engage play transport mode
#define AEKEY_PAUSE                     0x01000008 ///< engage pause transport mode
#define AEKEY_STOP                      0x01000009 ///< engage stop transport mode
#define AEKEY_FAST_FORWARD              0x0100000A ///< engage fast-forward transport mode
#define AEKEY_REWIND                    0x0100000B ///< engage rewind transport mode
#define AEKEY_SKIP_FORWARD              0x0100000C ///< quick skip ahead (usually 30 seconds)
#define AEKEY_SKIP_BACKWARD             0x0100000D ///< quick skip backward (usually 7-10 seconds)
#define AEKEY_NEXT                      0x0100000E ///< skip to next track or chapter
#define AEKEY_PREVIOUS                  0x0100000F ///< skip to previous track or chapter
#define AEKEY_LIVE                      0x01000010 ///< return to live [position in broadcast]
#define AEKEY_LAST                      0x01000011 ///< watch last channel or show watched
#define AEKEY_MENU                      0x01000012 ///< engage menu
#define AEKEY_INFO                      0x01000013 ///< info button
#define AEKEY_GUIDE                     0x01000014 ///< engage program guide
#define AEKEY_EXIT                      0x01000015 ///< exits current application mode
#define AEKEY_BACK                      0x01000016 ///< return back to previous page in application
#define AEKEY_AUDIO                     0x01000017 ///< select the audio mode
#define AEKEY_SUBTITLE                  0x01000018 ///< toggle subtitles
#define AEKEY_DVR                       0x01000019 ///< engage dvr application mode
#define AEKEY_VOD                       0x0100001A ///< engage video on demand
#define AEKEY_INPUT                     0x0100001B ///< cycle input
#define AEKEY_SETUP                     0x0100001C ///< engage setup application or menu
#define AEKEY_HELP                      0x0100001D ///< engage help application or context-sensitive help
#define AEKEY_MASTER_SHELL              0x0100001E ///< engage "Master Shell" e.g. TiVo or other vendor button
#define AEKEY_RED                       0x0100001F ///< red function key button
#define AEKEY_GREEN                     0x01000020 ///< green function key button
#define AEKEY_YELLOW                    0x01000021 ///< yellow function key button
#define AEKEY_BLUE                      0x01000022 ///< blue function key button
#ifdef SC_BDP
#define BDKEY_CLEAR			0x01000031 /// <clear key>
#endif

#ifndef SUPPORT_1G_ARCH
//LG IR KEY
#define MSG_KEY				16
#define KEY_UP		64
#define KEY_DOWN		65
#define KEY_LEFT		7
#define KEY_RIGHT	6
#define KEY_ENTER			68
#ifdef SC_BDP
#define KEY_POWER			8
#define KEY_EXIT			91 //..124	
#else
#define KEY_EXIT			91	//91==0x5B
#endif
#define KEY_BACK			40	//40==0x28
#define KEY_MENU			67  //67==0x43

#define KEY_SAP				10
#define KEY_TIMER			14
#define KEY_GAMEMODE		48
#define KEY_CC				57
#define KEY_QMENU			69
#define KEY_EZPIC			77
#define KEY_EZSOUND			82
#define KEY_AUTOSCAN		84
#define KEY_CHADDDEL		85
#define KEY_ASPECT_RATIO	121
#define KEY_AD				145
#define KEY_EJECT			202

#define KEY_CH_UP 			0
#define KEY_CH_DOWN		1
#define KEY_VOL_UP			2
#define KEY_VOL_DOWN		3
#define KEY_MUTE			9// 0x09:	Mute
#define KEY_TV_VIDEO		11 //0xB TV VIDEO
#define KEY_TV_RADIO		240 //0xB TV VIDEO
#define KEY_INFO			170 //0xB TV VIDEO
#define KEY_RED				114//114	//114==0x72
#define KEY_GREEN			113
#define KEY_YELLOW			99	//99==0x63
#define KEY_BLUE			97

#define KEY_MARK			125	//125==0x7D
#define KEY_PLAY 			176		/* 0xB0 Ac≫y   */
#define KEY_PAUSE			186		/* 0xBA AI½AA¤Ao   */
#define KEY_STOP 			177		/* 0xB1 A¤Ao   */
#define KEY_REW				143			/* 0x8F μC°¨±a	*/
#define KEY_FF				142		/* 0x8E ≫¡¸®°¨±a */
#define KEY_REC				189		//189==0xBD
#ifdef SC_BDP
#define KEY_SKIP_FORWARD 	179
#define KEY_SKIP_BACKWARD	178
#define KEY_CLEAR				0xA7AF
#endif
#define KEY_0				16			/* Number 0 */
#define KEY_1				17			/* Number 1 */
#define KEY_2				18			/* Number 2 */
#define KEY_3				19			/* Number 3 */
#define KEY_4				20			/* Number 4 */
#define KEY_5				21			/* Number 5 */
#define KEY_6				22			/* Number 6 */
#define KEY_7				23			/* Number 7 */
#define KEY_8				24			/* Number 8 */
#define KEY_9				25			/* Number 9 */

#define KEY_FAVORITE		30//89==0x59
#define KEY_GAMEMODE		48

#define KEY_DASH			76
#define KEY_FLASHBACK		26

#define KEY_PRLIST			83 // 0x53:

#define KEY_YAHOO			88

#ifdef SC_DTV
#define KEY_CONTENT_LINK	89	//89==0x59
#else
#define KEY_CONTENT_LINK	89 //.. 124
#endif

#define KEY_TTX				32
#define KEY_TTX_OPTION		33
/* appstore handled*/
#define KEY_3D						219	/* 3D  */
#define KEY_3D_MODE					220	/* 3D MODE */
#define KEY_3D_LR					221	/* 3D L/R */
#define KEY_GUIDE					169	/* GUIDE  */
//#define KEY_GRIDGUIDE				169	/* KEY_GRIDGUIDE  */
#define KEY_GUIDEPAL				171	/* KEY_GUIDEPAL  */
#define KEY_HCEC					126	/* 3D MODE */
#define DSC_IR_KEY_VIDEO1			90	/* DSC_IR_KEY_VIDEO1*/
#define DSC_IR_KEY_VIDEO2			208	/* DSC_IR_KEY_VIDEO2*/
#define DSC_IR_KEY_VIDEO3			209	/* DSC_IR_KEY_VIDEO3*/
#define DSC_IR_KEY_COMP1			191	/* DSC_IR_KEY_COMP1*/
#define DSC_IR_KEY_COMP2			212	/* DSC_IR_KEY_COMP2*/
#define DSC_IR_KEY_COMP3			217	/* DSC_IR_KEY_COMP3*/
#define DSC_IR_KEY_HDMI1			206	/* DSC_IR_KEY_HDMI1*/
#define DSC_IR_KEY_HDMI2			204	/* DSC_IR_KEY_HDMI2*/
#define DSC_IR_KEY_HDMI3			233	/* DSC_IR_KEY_HDMI3*/
#define DSC_IR_KEY_HDMI4			218	/* DSC_IR_KEY_HDMI4*/
#define DSC_IR_KEY_RGBPC			213	/* DSC_IR_KEY_RGBPC*/
#define DSC_IR_KEY_RGBDTV			215	/* DSC_IR_KEY_RGBDTV*/
#define DSC_IR_KEY_RGBDVI			198	/* DSC_IR_KEY_RGBDVI*/
#define KEY_TV						15	/* KEY_TV*/
#define KEY_HOME					124	/* KEY_HOME  */
#define KEY_HOME_US					200	/* KEY_HOME_US  */

/* factory key */
#define KEY_EYE_Q					149
#define KEY_IN_STOP         		250
#define KEY_IN_START        		251
#define KEY_P_CHECK         		252
#define KEY_HDMI_CHECK				166					//[081020 leekyungho] : IR_KEY_HDMI_CHECK(0xA6) 기능 UI 대응
#define KEY_S_CHECK         		253
#define KEY_POWERONLY   			254
#define KEY_ADJ	   					255
#define KEY_FRONT_AV	   			81
#define KEY_FMODE_INIT				39
#define KEY_FMODE_START				234
#define KEY_FMODE_F1				235
#define KEY_IN_TIME          		38
#define KEY_LAMP_RESET				53				/* for KOR model */
#define KEY_DISPMODE_READY			236				/* for KOR model */
#define KEY_DISPMODE				128
#define KEY_BLUETOOTH				31
#define KEY_USB_CHECK				238				/* 081106 swbyun : IR_KEY_USB_CHECK(0xEE) 기능 UI 대응 */
#define KEY_TILT					249				/* Module Pattern Generation */
#define KEY_HOTELMODE				207				/* 조정 리모콘의 TILT key에 할당 dragon77 2004-09-16 */
#define KEY_HOTELMODE_READY			35


#define KEY_FRONTKEY_INPUT_SELECT		4342
#define KEY_FRONTKEY_MENU				4341
#define KEY_FRONTKEY_ENTER				4343
#define KEY_FRONTKEY_CH_UP				4339
#define KEY_FRONTKEY_CH_DOWN			4340
#define KEY_FRONTKEY_VOL_UP				4337
#define KEY_FRONTKEY_VOL_DOWN			4338
#define KEY_FRONTKEY_POWER				4344

#define KEY_GAME_REAL_SWING				4358
#define KEY_GAME_SUDO_SWING				4359

#define KEY_WL_VIRKEY_AV1					16400
#define KEY_WL_VIRKEY_AV2					16401
#define KEY_WL_VIRKEY_COMP1					16402
#define KEY_WL_VIRKEY_COMP2					16403
#define KEY_WL_VIRKEY_RGB					16404
#define KEY_WL_VIRKEY_HDMI1					16405
#define KEY_WL_VIRKEY_HDMI2					16406
#define KEY_WL_VIRKEY_HDMI3					16407
#define KEY_WL_VIRKEY_HDMI4					16408
#define KEY_VIRKEY_NETCAST					24576
#else
//LG IR KEY
#define LG_MSG_KEY				16

#define LG_KEY_UP		64
#define LG_KEY_DOWN		65
#define LG_KEY_LEFT		7
#define LG_KEY_RIGHT	6
#define LG_KEY_ENTER			68
#ifdef SC_BDP
#define LG_KEY_POWER			8
#define LG_KEY_EXIT			91 //..124	
#else
#define LG_KEY_EXIT			91	//91==0x5B
#endif
#define LG_KEY_BACK			40	//40==0x28
#define LG_KEY_MENU			67  //67==0x43

#define LG_KEY_SAP				10
#define LG_KEY_TIMER			14
#define LG_KEY_GAMEMODE		48
#define LG_KEY_CC				57
#define LG_KEY_QMENU			69
#define LG_KEY_EZPIC			77
#define LG_KEY_EZSOUND			82
#define LG_KEY_AUTOSCAN		84
#define LG_KEY_CHADDDEL		85
#define LG_KEY_ASPECT_RATIO	121
#define LG_KEY_AD				145
#define LG_KEY_EJECT			202

#define LG_KEY_CH_UP 			0
#define LG_KEY_CH_DOWN		1
#define LG_KEY_VOL_UP			2
#define LG_KEY_VOL_DOWN		3
#define LG_KEY_MUTE			9// 0x09:	Mute
#define LG_KEY_TV_VIDEO		11 //0xB TV VIDEO
#define LG_KEY_TV_RADIO		240 //0xB TV VIDEO
#define LG_KEY_INFO			170 //0xB TV VIDEO

#define LG_KEY_RED				114//114	//114==0x72
#define LG_KEY_GREEN			113
#define LG_KEY_YELLOW			99	//99==0x63
#define LG_KEY_BLUE			97

#define LG_KEY_MARK			125	//125==0x7D
#define LG_KEY_PLAY 			176		/* 0xB0 Ac¡iy   */
#define LG_KEY_PAUSE			186		/* 0xBA AI¨oAA￠´Ao   */
#define LG_KEY_STOP 			177		/* 0xB1 A￠´Ao   */
#define LG_KEY_REW				143			/* 0x8F ￥iC¡Æ¡§¡¾a	*/
#define LG_KEY_FF				142		/* 0x8E ¡i￠®￠￢￠c¡Æ¡§¡¾a */
#define LG_KEY_REC				189		//189==0xBD
#ifdef SC_BDP
#define LG_KEY_SKIP_FORWARD 	179
#define LG_KEY_SKIP_BACKWARD	178
#endif

#define LG_KEY_0				16			/* Number 0 */
#define LG_KEY_1				17			/* Number 1 */
#define LG_KEY_2				18			/* Number 2 */
#define LG_KEY_3				19			/* Number 3 */
#define LG_KEY_4				20			/* Number 4 */
#define LG_KEY_5				21			/* Number 5 */
#define LG_KEY_6				22			/* Number 6 */
#define LG_KEY_7				23			/* Number 7 */
#define LG_KEY_8				24			/* Number 8 */
#define LG_KEY_9				25			/* Number 9 */
#define LG_KEY_FAVORITE		30//89==0x59
#define LG_KEY_GAMEMODE		48

#define LG_KEY_DASH			76
#define LG_KEY_FLASHBACK		26

#define LG_KEY_PRLIST			83 // 0x53:

#define LG_KEY_YAHOO			88

#ifdef SC_DTV
#define LG_KEY_CONTENT_LINK	89	//89==0x59
#else
#define LG_KEY_CONTENT_LINK	89 //.. 124
#endif

/* appstore handled*/
#define LG_KEY_3D						219	/* 3D  */
#define LG_KEY_3D_MODE					220	/* 3D MODE */
#define LG_KEY_3D_LR					221	/* 3D L/R */
#define LG_KEY_GUIDE					169	/* GUIDE  */
//#define KEY_GRIDGUIDE				169	/* KEY_GRIDGUIDE  */
#define LG_KEY_GUIDEPAL				171	/* KEY_GUIDEPAL  */
#define LG_KEY_HCEC					126	/* 3D MODE */
#define LG_DSC_IR_KEY_VIDEO1			90	/* DSC_IR_KEY_VIDEO1*/
#define LG_DSC_IR_KEY_VIDEO2			208	/* DSC_IR_KEY_VIDEO2*/
#define LG_DSC_IR_KEY_VIDEO3			209	/* DSC_IR_KEY_VIDEO3*/
#define LG_DSC_IR_KEY_COMP1			191	/* DSC_IR_KEY_COMP1*/
#define LG_DSC_IR_KEY_COMP2			212	/* DSC_IR_KEY_COMP2*/
#define LG_DSC_IR_KEY_COMP3			217	/* DSC_IR_KEY_COMP3*/
#define LG_DSC_IR_KEY_HDMI1			206	/* DSC_IR_KEY_HDMI1*/
#define LG_DSC_IR_KEY_HDMI2			204	/* DSC_IR_KEY_HDMI2*/
#define LG_DSC_IR_KEY_HDMI3			233	/* DSC_IR_KEY_HDMI3*/
#define LG_DSC_IR_KEY_HDMI4			218	/* DSC_IR_KEY_HDMI4*/
#define LG_DSC_IR_KEY_RGBPC			213	/* DSC_IR_KEY_RGBPC*/
#define LG_DSC_IR_KEY_RGBDTV			215	/* DSC_IR_KEY_RGBDTV*/
#define LG_DSC_IR_KEY_RGBDVI			198	/* DSC_IR_KEY_RGBDVI*/
#define LG_KEY_TV						15	/* KEY_TV*/
#define LG_KEY_HOME					124	/* KEY_HOME  */
#define LG_KEY_HOME_US					200	/* KEY_HOME_US  */

/* factory key */
#define LG_KEY_EYE_Q					149
#define LG_KEY_IN_STOP         		250
#define LG_KEY_IN_START        		251
#define LG_KEY_P_CHECK         		252
#define LG_KEY_HDMI_CHECK				166					//[081020 leekyungho] : IR_KEY_HDMI_CHECK(0xA6) ±a´E UI ´eAA
#define LG_KEY_S_CHECK         		253
#define LG_KEY_POWERONLY   			254
#define LG_KEY_ADJ	   					255
#define LG_KEY_FRONT_AV	   			81
#define LG_KEY_FMODE_INIT				39
#define LG_KEY_FMODE_START				234
#define LG_KEY_FMODE_F1				235
#define LG_KEY_IN_TIME          		38
#define LG_KEY_LAMP_RESET				53				/* for KOR model */
#define LG_KEY_DISPMODE_READY			236				/* for KOR model */
#define LG_KEY_DISPMODE				128
#define LG_KEY_BLUETOOTH				31
#define LG_KEY_USB_CHECK				238				/* 081106 swbyun : IR_KEY_USB_CHECK(0xEE) ±a´E UI ´eAA */
#define LG_KEY_TILT					249				/* Module Pattern Generation */
#define LG_KEY_HOTELMODE				207				/* A¶A¤ ¸®¸ðAUAC TILT key¿¡ CO´c dragon77 2004-09-16 */
#define LG_KEY_HOTELMODE_READY			35


#define LG_KEY_FRONTKEY_INPUT_SELECT		4342
#define LG_KEY_FRONTKEY_MENU				4341
#define LG_KEY_FRONTKEY_ENTER				4343
#define LG_KEY_FRONTKEY_CH_UP				4339
#define LG_KEY_FRONTKEY_CH_DOWN			4340
#define LG_KEY_FRONTKEY_VOL_UP				4337
#define LG_KEY_FRONTKEY_VOL_DOWN			4338
#define LG_KEY_FRONTKEY_POWER				4344

#define LG_KEY_GAME_REAL_SWING				4358
#define LG_KEY_GAME_SUDO_SWING				4359

#define LG_KEY_WL_VIRKEY_AV1					16400
#define LG_KEY_WL_VIRKEY_AV2					16401
#define LG_KEY_WL_VIRKEY_COMP1					16402
#define LG_KEY_WL_VIRKEY_COMP2					16403
#define LG_KEY_WL_VIRKEY_RGB					16404
#define LG_KEY_WL_VIRKEY_HDMI1					16405
#define LG_KEY_WL_VIRKEY_HDMI2					16406
#define LG_KEY_WL_VIRKEY_HDMI3					16407
#define LG_KEY_WL_VIRKEY_HDMI4					16408
#define LG_KEY_VIRKEY_NETCAST					24576
#endif // #ifndef SUPPORT_1G_ARCH


typedef enum KEYBOARD
{
	KEYBOARD_NOMAL		= 0x00,		/**< KEY_NOMAL */
	KEYBOARD_SCREEN		= 0x01,		/**< KEY_SCREEN */
	KEYBOARD_WIDGETFULL	= 0x02,		/**< KEY_WIDGET */
	KEYBOARD_WIDGETICON	= 0x04, 	/**< KEY_WIDGET */
	KEYBOARD_EMANUAL		= 0x08,		/**< KEY_EMANUAL */
	KEYBOARD_NONE		= 0x0F,		/**< KEY_NONE */
} KEYBOARD_T;


#endif // #ifndef _INCLUDE_AE_STAGECRAFT_STAGECRAFTKEYDEFS_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
////////////////////////////////////////////////////////////////////////////////
//  26-Oct-07   dwoodward   created
//  05-Mar-09   dwoodward   auto-regenerated by KeyName.as
