/******************************************************************************
 *   Software Center, LG ELECTRONICS INC., SEOUL, KOREA
 *   Copyright(c) 1999 by LG Electronics Inc.
 *
 *   All rights reserved. No part of this work may be reproduced, stored in a
 *   retrieval system, or transmitted by any means without prior written
 *   permission of LG Electronics Inc.
 *****************************************************************************/

/** @file StagecraftAddonKey.h
 *
 *  StagecraftAddonKey header
 *
 *  @author     hyunsung.lee(hyunsung.lee@lge.com)
 *  @version    1.0
 *  @date       2010.11.02
 *  @note
 *  @see
 */


/* codes to identify some of the special keys accepted by Flash Player */
enum FI_KeyCode
{
	FI_KEY_LEFT     = 1,    /* Four-way directional navigation */
	FI_KEY_RIGHT    = 2,    /* Four-way directional navigation */
	FI_KEY_HOME     = 3,    /* Send Key */
	FI_KEY_END      = 4,    /* End Key */
	FI_KEY_INSERT   = 5,	/* Insert Key */
	FI_KEY_DELETE   = 6,	/* Delete Key */
	FI_KEY_BACKSPACE = 8,	/* Backspace */
	FI_KEY_SELECT   = 13,   /* Select key */
	FI_KEY_UP       = 14,   /* Four-way directional navigation */
	FI_KEY_DOWN     = 15,   /* Four-way directional navigation */
	FI_KEY_PAGEUP   = 16,   /* Left soft key */
	FI_KEY_PAGEDOWN = 17,   /* Right soft key */
	FI_KEY_FORWARD  = 18,   /* Two-way directional navigation */
	FI_KEY_BACKWARD = 26,   /* Two-way directional navigation */
	FI_KEY_ESCAPE	= 19,	/* Escape Key */
	FI_KEY_EXIT		= 88,	/* Escape Key */
	FI_KEY_ENTER	= 31,   /* Enter key */
	FI_KEY_TAB		= 300,	/* Tab Key */
	FI_KEY_CAPS		= 302,	/* Capslock Key */
	FI_KEY_SHIFT    = 303,	/* Shift Key */
	FI_KEY_CTRL	    = 304,	/* Ctrl Key */
	FI_KEY_HASH     = 35,   /* '#" */
	FI_KEY_ASTERISK = 42,   /* '*' */
	FI_KEY_0        = 48,   /* '0' */
	FI_KEY_1        = 49,   /* '1' */
	FI_KEY_2        = 50,   /* '2' */
	FI_KEY_3        = 51,   /* '3' */
	FI_KEY_4        = 52,   /* '4' */
	FI_KEY_5        = 53,   /* '5' */
	FI_KEY_6        = 54,   /* '6' */
	FI_KEY_7        = 55,   /* '7' */
	FI_KEY_8        = 56,   /* '8' */
	FI_KEY_9        = 57,   /* '9' */
	FI_KEY_A		= 65,	/* '8' */
	FI_KEY_B		= 66,	/* '9' */
	FI_KEY_MENU		= 77,	/* 'M' */
	FI_KEY_YAHOO	= 89,	/* 'Y' */

	FI_KEY_PLUS     = 43,   /* Volume Up Key */
	FI_KEY_MINUS    = 45,   /* Volume Down Key */
	FI_KEY_TILDE    = 126,  /* Voice Memo Key */
	FI_KEY_PRLIST    = 127,  /* PRLIST  Key */
	FI_KEY_EXCLAMATION  = 33,   /* Camera Key */
	FI_KEY_AT       = 64,   /* WAP Key */
	FI_KEY_COMMA    = 44,   /* MP3 1 Key (Rewind) */
	FI_KEY_DOT      = 46,   /* MP3 2 Key (Play) */
	FI_KEY_SLASH    = 47,   /* MP3 3 Key (Fast Fwd) */
	FI_KEY_VERTICALBAR	= 124,
#ifdef FEATURE_ATS_TEST
	FI_KEY_VOLDOWN	= 174,	 /* VolumeDwon key */
	FI_KEY_VOLUP	= 175,	 /* VolumeUp key */
#endif

	FI_KEY_SAP 		=	250,
	FI_KEY_TIMER	=	251,
	FI_KEY_GAMEMODE	=	252,
	FI_KEY_CC		=	253,
	FI_KEY_QMENU	=	254,	
	FI_KEY_EZPIC	=	255,
	FI_KEY_EZSOUND	=	256,
	FI_KEY_AUTOSCAN	=	257,
	FI_KEY_CHADDDEL	=	258,
	FI_KEY_ASPECT_RATIO	=	259,
	FI_KEY_EYE_Q	=	260,
	FI_KEY_AD		=	261,
	FI_KEY_EJECT	=	262,
	FI_KEY_TTX		=	263,
	FI_KEY_TTX_OPTION		=	264,

	FI_KEY_FRONTKEY_INPUT_SELECT		=350,
	FI_KEY_FRONTKEY_MENU				=351,
	FI_KEY_FRONTKEY_ENTER				=352,
	FI_KEY_FRONTKEY_CH_UP				=353,
	FI_KEY_FRONTKEY_CH_DOWN				=354,
	FI_KEY_FRONTKEY_VOL_UP				=355,
	FI_KEY_FRONTKEY_VOL_DOWN			=356,
	FI_KEY_FRONTKEY_TVGUIDE 			=357,
	FI_KEY_VIRKEY_NETCAST 				=6000,	
	
};
