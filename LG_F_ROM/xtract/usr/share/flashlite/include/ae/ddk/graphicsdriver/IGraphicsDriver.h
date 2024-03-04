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

#ifndef _INCLUDE_AE_DDK_GRAPHICSDRIVER_IGRAPHICSDRIVER_H
#define _INCLUDE_AE_DDK_GRAPHICSDRIVER_IGRAPHICSDRIVER_H

#include <ae/IAEModule.h>
#include <ae/AETemplates.h>
#include <ae/stagecraft/StagecraftTypes.h>
#include <ae/stagecraft/StageWindow.h>

namespace ae                {
namespace ddk               {
namespace graphicsdriver    {

////////////////////////////////////////////////////////////////////////////////
// class PlaneFactory
/// A factory class to create and destroy planes.
/// It is not necessary for Stagecraft clients to implement and register a PlaneFactory,
/// however, it provides a convenient structure for localizing the lifetime management
/// of a plane type.
/// <p>By registering a PlaneFactory with the GraphicsDriver interface, a client
/// enables Stagecraft to automatically create off-screen planes to hold bitmap
/// data that it will composite into a complete frame of Flash animation.  Stagecraft
/// uses this system to accelerate Flash rendering, in many instances by using
/// a hardware graphics accelerator on the target platform.
/// <p> Stagecraft can also automatically create render and output planes by using
/// registered PlaneFactories in order to simplify the setup process, although clients
/// may also explicitly provide their own pre-created render and output planes.
class PlaneFactory
{
public:
    virtual const char *            GetClassName() const = 0;
        ///< returns the class name of the planes that this factory creates
    virtual ae::stagecraft::Plane * CreatePlane(const ae::stagecraft::Dims & dims, ae::stagecraft::ColorFormat colorFormat,
                                            ae::stagecraft::StageWindow * pStageWindow) = 0;
        ///< create a plane
        /// Planes created with CreatePlane() are meant to be offscreen planes for compositing
        /// and caching bitmap data.  The StageWindow pointer, if not NULL, is guaranteed to
        /// exist for the lifetime of the Plane.  The plane should use the StageWindow's
        /// MemoryWatchdog object for large allocations of system memory in order to associate
        /// the memory useage with the StageWindow instance.
    virtual ae::stagecraft::Plane * CreateOutputPlane(const ae::stagecraft::Rect & rect, bool bDefaultPosition,
                                            ae::stagecraft::Plane * pCompatiblePlane, ae::stagecraft::StageWindow * pStageWindow,
                                            const char * pTitle) = 0;
        ///< create an output plane
        /// If bDefaultPosition is true, then the x and y values of the rect parameter
        /// will be ignored and the factory is free to position the output plane in a
        /// "default" position.  This may result in the window being centered on the
        /// screen, or it may result in windows being created in a "tiled" position in
        /// a graphical windowed environment.  If pStageWindow is not NULL, then the
        /// client guarantees that the pStageWindow will exist for the lifetime of the
        /// output plane, and the output plane can use it to deliver user input events
        /// and focus change requests directly.  The plane should also use the StageWindow's
        /// MemoryWatchdog object for large allocations of system memory in order to associate
        /// the memory useage with the StageWindow instance.  The pTitle parameter may be
        /// used to title the containing window if supported by the platform implementation.
	virtual ae::stagecraft::Dims    GetMaxOutputPlaneDims() = 0;
        ///< get max output plane dims, e.g., screen size, or Dims(0, 0) if unsupported
    virtual void                    DestroyPlane(ae::stagecraft::Plane * pPlane) = 0;
        ///< destroy a plane
protected:
    virtual ~PlaneFactory() { }
};

////////////////////////////////////////////////////////////////////////////////
// class IGraphicsDriver
/// Interface to the GraphicsDriver module.
class IGraphicsDriver : public IAEModule
{
public:
	virtual ae::stagecraft::Plane * CreatePlane(const char * pClassName, const ae::stagecraft::Dims & dims,
                                            ae::stagecraft::ColorFormat colorFormat, ae::stagecraft::StageWindow * pStageWindow) = 0;
        ///< Create a plane.
    virtual ae::stagecraft::Plane * CreateOutputPlane(const char * pClassName, const ae::stagecraft::Rect & rect,
                                            bool bDefaultPosition, ae::stagecraft::Plane * pCompatiblePlane,
                                            ae::stagecraft::StageWindow * pStageWindow, const char * pTitle) = 0;
        ///< Create an output plane.
	virtual void                    DestroyPlane(ae::stagecraft::Plane * pPlane) = 0;
        ///< Destroy a plane.

public:
    virtual PlaneFactory *  AcquirePlaneFactory(const char * pClassName) = 0;
        ///< Obtains an interface handle to a registered PlaneFactory.
    virtual void            ReleasePlaneFactory(PlaneFactory * pPlaneFactory) = 0;
        ///< Releases an interface to a registered PlaneFactory.
        /// Calls to AcquirePlaneFactory() and ReleasePlaneFactory() must be balanced for proper system operation.
    virtual bool            RegisterPlaneFactory(PlaneFactory * pPlaneFactory) = 0;
        ///< Registers a PlaneFactory with the GraphicsDriver module.
	virtual bool            GetRegisteredPlaneClasses(AEArray<const char *> & listToSet) = 0;
        ///< Returns a list of registered PlaneFactory names.
        /// Do not attempt to delete the strings in the list when you are done with it.
	virtual ae::stagecraft::Dims GetMaxOutputPlaneDims() = 0;
		///< Returns the maximum output dims of the screen

public:
    virtual bool Blit(ae::stagecraft::Plane * pSource, ae::stagecraft::Plane * pDest, const ae::stagecraft::I2D::SrcBlitInfo & sourceInfo,
                            const ae::stagecraft::Rect & destRect, const struct ae::stagecraft::I2D::Transformation * pTrans) = 0;
        ///< Perform a Blit operation from a Source plane to a Destination plane.
        ///  The source and destination planes do not need to be from the same PlaneFactory.
        ///  The default implementation uses a software blitter emulator to perform the blit operation.
};

}}}; // end namespace ae::ddk::graphicsdriver

#endif // #ifndef _INCLUDE_AE_DDK_GRAPHICSDRIVER_IGRAPHICSDRIVER_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
////////////////////////////////////////////////////////////////////////////////
//  20-Aug-08   dsletten    created
//  03-Oct-08   dsletten    doxygenated
//  29-Oct-08   dwoodward   eliminated unused functions; added UnregisterPlaneFactory()
//  09-Dec-08   dsletten    added pStageWindow param to CreatePlane()
//	12-Dec-08	mkumarb		added GetMaxOutputPlaneDims()
//  21-Apr-09   sshanson    added Blit() for global software Blit operations.

