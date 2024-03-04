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

#ifndef _INCLUDE_AE_DDK_GRAPHICSDRIVER_HOST_X11PLANEFACTORY_H
#define _INCLUDE_AE_DDK_GRAPHICSDRIVER_HOST_X11PLANEFACTORY_H

#include <ae/IAEKernel.h>
#include <ae/ddk/graphicsdriver/IGraphicsDriver.h>

////////////////////////////////////////////////////////////////////////////////
// #defines
#define X11_PLANE_CLASS_NAME  "X11Plane"

namespace ae                {
namespace ddk               {
namespace graphicsdriver    {
namespace host              {

////////////////////////////////////////////////////////////////////////////////
// class X11PlaneFactory

/// A factory class for creating X11 output planes.
/// This factory creates a separate window for each output plane and channels user
/// input events provided from the X11 API into the associated StageWindow
/// instance.  It also activates the associated StageWindow when the user activates
/// the window.
///
/// <p>Known limitations:
///
/// <p>This driver maps the 'ESC' key to send a termination request to the
/// current flash instance, but processing of the event sometimes stalls out
/// and the window doesn't close immediately.
///
/// <p>If the user clicks in the close box of a flash instance, it will cause
/// the entire stagecraft process to exit immediately.  Xlib makes it challenging
/// to handle this severance of the X connection, so currently we just drop it
/// on the floor and Xlib performs its default behavior which is to kill the
/// current process.
/// 
/// <p>All planes are created in a "default position" assigned by X.  Any
/// requested x, y position is ignored.
class X11PlaneFactory : public PlaneFactory
{
public:
    virtual ae::stagecraft::Plane * CreatePlane(const ae::stagecraft::Dims & dims, ae::stagecraft::ColorFormat colorFormat,
                                            ae::stagecraft::StageWindow * pStageWindow) = 0;
        ///< create a plane
        /// The X11PlaneFactory returns NULL for this function.  Clients need to use
        /// another plane type (e.g. MemPlanes) for the StageWindow render plane.
    virtual ae::stagecraft::Plane * CreateOutputPlane(const ae::stagecraft::Rect & rect, bool bDefaultPosition,
                                            ae::stagecraft::Plane * pCompatiblePlane, ae::stagecraft::StageWindow * pStageWindow,
                                            const char * pTitle) = 0;
        ///< create an output plane
	virtual ae::stagecraft::Dims    GetMaxOutputPlaneDims() = 0;
        ///< returns current screen dimensions (NOT IMPLEMENTED)
    virtual void                    DestroyPlane(ae::stagecraft::Plane * pPlane) = 0;
        ///< destroy a plane
};

}}}}; // end namespace ae::ddk::graphicsdriver::host

#endif // #ifndef _INCLUDE_AE_DDK_GRAPHICSDRIVER_HOST_X11PLANEFACTORY_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
////////////////////////////////////////////////////////////////////////////////
//  07-Oct-08   dsletten    created
//  09-Dec-08   dsletten    added pStageWindow param to PlaneFactory::CreatePlane()

