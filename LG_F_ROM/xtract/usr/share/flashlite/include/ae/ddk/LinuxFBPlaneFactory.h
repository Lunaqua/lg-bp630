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

#ifndef _INCLUDE_AE_DDK_GRAPHICSDRIVER_HOST_LINUXFBPLANEFACTORY_H
#define _INCLUDE_AE_DDK_GRAPHICSDRIVER_HOST_LINUXFBPLANEFACTORY_H

#include <ae/ddk/graphicsdriver/IGraphicsDriver.h>

////////////////////////////////////////////////////////////////////////////////
// #defines
#define LINUXFB_PLANE_CLASS_NAME  "LinuxFBPlane"

namespace ae                {
namespace ddk               {
namespace graphicsdriver    {
namespace host              {

////////////////////////////////////////////////////////////////////////////////
// class LinuxFBPlaneFactory
/// A factory class for creating output planes on the linux framebuffer device.
/// The LinuxFBPlaneFactory creates output planes that use the linux
/// framebuffer device at /dev/fb0.  It does not create off-screen planes.
/// Clients should use MemPlanes for rendering.
class LinuxFBPlaneFactory : public ae::ddk::graphicsdriver::PlaneFactory
{
public:
    // PlaneFactory interface:
    virtual ae::stagecraft::Plane * CreatePlane(const ae::stagecraft::Dims & dims, ae::stagecraft::ColorFormat colorFormat,
                                            ae::stagecraft::StageWindow * pStageWindow) = 0;
        ///< create a plane
        /// LinuxFBPlaneFactory's impl returns NULL.
    virtual ae::stagecraft::Plane * CreateOutputPlane(const ae::stagecraft::Rect & rect, bool bDefaultPosition,
                                            ae::stagecraft::Plane * pCompatiblePlane, ae::stagecraft::StageWindow * pStageWindow,
                                            const char * pTitle) = 0;
        ///< create an output plane
        /// Returns NULL if the extent of the plane would extend beyond the frame buffer's dimensions.
	virtual ae::stagecraft::Dims    GetMaxOutputPlaneDims() = 0;
        ///< get max output plane dims
    virtual void DestroyPlane(ae::stagecraft::Plane * pPlane) = 0;
        ///< destroy a plane
};

}}}}; // end namespace ae::ddk::graphicsdriver::host

#endif // #ifndef _INCLUDE_AE_DDK_GRAPHICSDRIVER_HOST_LINUXFBPLANEFACTORY_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
////////////////////////////////////////////////////////////////////////////////
//  25-Sep-08   dsletten    created
//  03-Oct-08   dsletten    doxygenated
//  29-Oct-08   dwoodward   eliminated unused functions
//  09-Dec-08   dsletten    added pStageWindow param to PlaneFactory::CreatePlane()

