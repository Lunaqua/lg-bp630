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

#ifndef _AE_INCLUDE_AEKERNEL_AEMODULE_H
#define _AE_INCLUDE_AEKERNEL_AEMODULE_H

#include <ae/AETypes.h>

class IAEModule
{
protected:
    virtual ~IAEModule() { }
};

#endif // #ifndef _AE_INCLUDE_AEKERNEL_AEMODULE_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
////////////////////////////////////////////////////////////////////////////////
//  07-Sep-07   dwoodward   created
//  06-Dec-07   dwoodward   added protected virtual destructor
