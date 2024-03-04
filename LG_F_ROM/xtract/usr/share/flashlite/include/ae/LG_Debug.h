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

#ifndef _LG_DEBUG_H_
#define _LG_DEBUG_H_

#include <ae/IAEModule.h>
#include <ae/AEError.h>


// turbok3
//*************************************************************************************************************************************
#define _LG_DEBUG_ON_

#define _SC_DEBUG_ON_				"/tmp/sc_debug_on"
#define _TELE_MODULE_DEBUG_ON_	"/tmp/tele_debug_on"


#define _SC_RELEASE_MODE_

#ifdef _SC_RELEASE_MODE_
#define 	CHECK_DBG_FILE(X)		(1)
#else
#define 	CHECK_DBG_FILE(X)	(::access((const char*)X, F_OK))
#endif

#ifdef _LG_DEBUG_ON_
#define SC_SPRINTF_BUFLEN		2048 //4096
typedef enum
{
	SC_LOG_DEFAULT = 0,
	SC_LOG_RED,
	SC_LOG_GREEN,
	SC_LOG_BLUE,
	SC_LOG_YELLOW,
	SC_LOG_MAGENTA,
	SC_LOG_CYAN,
	SC_LOG_WHITE
} SC_LOG_MSG_COLOR;
extern void SC_PrintDbgString(bool bForce, SC_LOG_MSG_COLOR color , const char * pFormatString, ...);
#endif //_LG_DEBUG_ON_
//*************************************************************************************************************************************

#endif // #ifndef _LG_DEBUG_H_
