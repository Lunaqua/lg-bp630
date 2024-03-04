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

#ifndef _INCLUDE_AE_DDK_GRAPHICSDRIVER_HOST_MEMPLANEFACTORY_H
#define _INCLUDE_AE_DDK_GRAPHICSDRIVER_HOST_MEMPLANEFACTORY_H

#include <ae/stagecraft/StagecraftTypes.h>
#include <ae/ddk/graphicsdriver/IGraphicsDriver.h>

////////////////////////////////////////////////////////////////////////////////
// #defines
#define MEM_PLANE_CLASS_NAME  "MemPlane"

namespace ae                {
namespace ddk               {
namespace graphicsdriver    {
namespace host              {

////////////////////////////////////////////////////////////////////////////////
// class MemPlaneFactory
/// A factory class for creating system-memory-based planes.
class MemPlaneFactory : public ae::ddk::graphicsdriver::PlaneFactory
{
public:
    virtual ae::stagecraft::Plane * CreatePlane(const ae::stagecraft::Dims & dims, ae::stagecraft::ColorFormat colorFormat,
                                            ae::stagecraft::StageWindow * pStageWindow) = 0;
        ///< create a plane
    virtual ae::stagecraft::Plane * CreatePlane(u8 * pBits, const ae::stagecraft::Dims & dims, u32 nRowBytes, ae::stagecraft::ColorFormat colorFormat) = 0;
        ///< create a plane from a pre-existing block of memory
        /// This is a custom CreatePlane() that creates a Stagecraft Plane object from an existing
        /// in-memory bitmap.
    virtual ae::stagecraft::Plane * CreateOutputPlane(const ae::stagecraft::Rect & rect, bool bDefaultPosition,
                                            ae::stagecraft::Plane * pCompatiblePlane, ae::stagecraft::StageWindow * pStageWindow,
                                            const char * pTitle) = 0;
        ///< create an output plane
        /// The MemPlaneFactory implementation returns a plane instance stored in system-memory.
        /// This function is equivalent to calling the regular CreatePlane() function.
        /// The image on these output planes obviously can't be seen by the end-user, however
        /// this output-plane functionality is provided to assist accelerated bring-up of
        /// the Stagecraft system on a new platform.
	virtual ae::stagecraft::Dims    GetMaxOutputPlaneDims() = 0;
        ///< MemPlaneFactory's impl returns Dims(0, 0)
    virtual void DestroyPlane(ae::stagecraft::Plane * pPlane) = 0;
        ///< destroys a plane
};

}}}}; // end namespace ae::ddk::graphicsdriver::host

#endif // #ifndef _INCLUDE_AE_DDK_GRAPHICSDRIVER_HOST_MEMPLANEFACTORY_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
////////////////////////////////////////////////////////////////////////////////
//  20-Aug-08   dsletten    created
//  03-Oct-08   dsletten    doxygenated
//  29-Oct-08   dwoodward   eliminated unused functions
//  09-Dec-08   dsletten    added pStageWindow param to PlaneFactory::CreatePlane()

