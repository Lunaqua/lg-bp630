/*******************************************************************************
* ADOBE SYSTEMS INCORPORATED
* Copyright 2008 Adobe Systems Incorporated
* All Rights Reserved.
*
* NOTICE:  Adobe permits you to use, modify, and distribute this file in
* accordance with the  terms of the Adobe license agreement accompanying it. If
* you have received this file from a source other than Adobe, then your use,
* modification, or distribution of it requires the prior written permission of
* Adobe.
*******************************************************************************/

#ifndef _INCLUDE_AE_EDK_IEDKEXTENSIONS_H
#define _INCLUDE_AE_EDK_IEDKEXTENSIONS_H

#include <ae/stagecraft/ASExtensions.h>

namespace ae    {
namespace edk   {

class IEDKExtensions : public IAEModule
{
public:
    virtual void GetDefaultExtensions(ae::stagecraft::ASExtensionClassInfoArray & extensionArrayToFill) = 0;
        // The purpose of the IEDKExtensions module is to fill the supplied array with default
        // ASExtensionInfoClasses via this GetDefaultExtensions() function.  The IEDKExtensions module
        // gets loaded automatically by the runtime, and any ASExtensionInfoClasses filled
        // in extensionArrayToFill in this function will be automatically available to Flash Instances.
};

}}; // end namespace ae::edk

#endif // #ifndef _INCLUDE_AE_EDK_IEDKEXTENSIONS_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
////////////////////////////////////////////////////////////////////////////////
//  14-Aug-08   dwoodward   created
//  15-Mar-09   dwoodward   made GetDefaultExtensions() fill an array instead of returning one
