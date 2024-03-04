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

#ifndef _INCLUDE_AE_DDK_GRAPHICSDRIVER_HOST_WIN32PLANEFACTORY_H
#define _INCLUDE_AE_DDK_GRAPHICSDRIVER_HOST_WIN32PLANEFACTORY_H

#include <ae/ddk/graphicsdriver/IGraphicsDriver.h>
#include <ae/IAEKernel.h>

////////////////////////////////////////////////////////////////////////////////
// #defines
#define WIN32_PLANE_CLASS_NAME  "Win32Plane"

namespace ae                {
namespace ddk               {
namespace graphicsdriver    {
namespace host              {

////////////////////////////////////////////////////////////////////////////////
// class Win32PlaneFactory
/// A factory class for creating Win32 output planes.
/// This factory creates a separate window for each output plane and channels user
/// input events provided from the Windows API into the associated StageWindow
/// instance.  It also activates the associated StageWindow when the user activates
/// the window.
class Win32PlaneFactory : public PlaneFactory
{
public:
    virtual ae::stagecraft::Plane * CreatePlane(const ae::stagecraft::Dims & dims, ae::stagecraft::ColorFormat colorFormat,
                                            ae::stagecraft::StageWindow * pStageWindow) = 0;
        ///< create a plane
        /// The Win32PlaneFactory returns NULL for this function.  Clients need to use
        /// another plane type (e.g. MemPlanes) for the StageWindow render plane.
    virtual ae::stagecraft::Plane * CreateOutputPlane(const ae::stagecraft::Rect & rect, bool bDefaultPosition,
                                            ae::stagecraft::Plane * pCompatiblePlane, ae::stagecraft::StageWindow * pStageWindow,
                                            const char * pTitle) = 0;
        ///< create an output plane
        /// If bDefaultPosition is true, this function allows the Windows API to automatically
        /// position the newly-created output plane on the desktop.  Windows will tile
        /// consecutively-created windows.
	virtual ae::stagecraft::Dims    GetMaxOutputPlaneDims() = 0;
        ///< returns current screen dimensions
    virtual void                    DestroyPlane(ae::stagecraft::Plane * pPlane) = 0;
        ///< destroy a plane
};

}}}}; // end namespace ae::ddk::graphicsdriver::host

#endif // #ifndef _INCLUDE_AE_DDK_GRAPHICSDRIVER_HOST_WIN32PLANEFACTORY_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
////////////////////////////////////////////////////////////////////////////////
//  20-Aug-08   dsletten    created
//  03-Oct-08   dsletten    doxygenated
//  29-Oct-08   dwoodward   eliminated unused functions
//  09-Dec-08   dsletten    added pStageWindow param to PlaneFactory::CreatePlane()

