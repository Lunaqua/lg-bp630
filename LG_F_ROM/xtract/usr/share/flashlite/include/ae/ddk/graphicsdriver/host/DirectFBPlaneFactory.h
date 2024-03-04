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

#ifndef _INCLUDE_AE_DDK_GRAPHICSDRIVER_HOST_DIRECTFBPLANEFACTORY_H
#define _INCLUDE_AE_DDK_GRAPHICSDRIVER_HOST_DIRECTFBPLANEFACTORY_H

#include <ae/ddk/graphicsdriver/IGraphicsDriver.h>
#include <ae/IAEKernel.h>

////////////////////////////////////////////////////////////////////////////////
// type declarations
typedef void AE_IDirectFB;
typedef void AE_IDirectFBSurface;

// NOTE: AE include files can't themselves #include any headers from third-party
// code by convention, so this file can't #include <directfb.h>.  These typedefs
// define types that can be cast into their DirectFB analogues by the client.

////////////////////////////////////////////////////////////////////////////////
// #defines
#define DIRECTFB_PLANE_CLASS_NAME  "DirectFBPlane"

namespace ae                {
namespace ddk               {
namespace graphicsdriver    {
namespace host              {

////////////////////////////////////////////////////////////////////////////////
// class DirectFBPlane
/// A sub-class of the Stagecraft Plane class to provide additional DirectFB functionality.
class DirectFBPlane : public ae::stagecraft::Plane
{
public:
    virtual AE_IDirectFBSurface * GetSurface() = 0;
        ///< returns the IDirectFBSurface interface pointer associated with this plane instance
};

////////////////////////////////////////////////////////////////////////////////
// class DirectFBPlaneFactory
/// A factory class for creating planes implemented with the DirectFB API.
class DirectFBPlaneFactory : public PlaneFactory
{
public:
    // PlaneFactory interface:
    virtual const char *            GetClassName() const = 0;
        ///< returns the class name of the planes that this factory creates
    virtual ae::stagecraft::Plane * CreatePlane(const ae::stagecraft::Dims & dims, ae::stagecraft::ColorFormat colorFormat,
                                            ae::stagecraft::StageWindow * pStageWindow) = 0;
        ///< create a plane
        /// This function creates a DirectFB plane that is not visible.  The current implementation
        /// requests a DirectFB surface allocated in video memory, and fails if there is not enough
        /// video memory.  (We assume that the DirectFB software implementation of blits to and from
        /// a surface in system memory is not better than Flash's own software implementations.)
        /// <p>If DirectFB has not been started yet, this function will call StartDirectFB(),
        /// StartDirectFBUserInputDriver(), and in _DEBUG builds only it will start up a TTY input
        /// driver to send keyboard input to Stagecraft.  (See TTYUserInputDriver.h for notes about
        /// key mapping by the TTY driver.)
    virtual ae::stagecraft::Plane * CreateOutputPlane(const ae::stagecraft::Rect & rect, bool bDefaultPosition,
                                            ae::stagecraft::Plane * pCompatiblePlane, ae::stagecraft::StageWindow * pStageWindow,
                                            const char * pTitle) = 0;
        ///< create an output plane
        /// This factory implementation starts by opening a single visible, full-screen output surface
        /// that covers the entire frame buffer.  Subsequent output planes are created as sub-surfaces
        /// of this initial surface.  At the moment, there is no ability to specify the Z-order of
        /// overlapping output planes -- they will simply contend for output pixels and cause flickering
        /// where they intersect.
        /// <p>If DirectFB has not been started yet, this function will call StartDirectFB(),
        /// StartDirectFBUserInputDriver(), and in _DEBUG builds only it will start up a TTY input
        /// driver to send keyboard input to Stagecraft.  (See TTYUserInputDriver.h for notes about
        /// key mapping by the TTY driver.)
    virtual ae::stagecraft::Dims    GetMaxOutputPlaneDims() = 0;
        ///< DirectFBPlaneFactory's impl returns current frame buffer dimensions
        /// <p>If DirectFB has not been started yet, this function will call StartDirectFB(),
        /// StartDirectFBUserInputDriver(), and in _DEBUG builds only it will start up a TTY input
        /// driver to send keyboard input to Stagecraft.  (See TTYUserInputDriver.h for notes about
        /// key mapping by the TTY driver.)
    virtual void                    DestroyPlane(ae::stagecraft::Plane * pPlane) = 0;
        ///< destroy a plane
public:
    // additional public functions:
    virtual bool                    StartDirectFB() = 0;
        ///< This function will initialize DirectFB.
        /// Clients may ask this PlaneFactory to start DirectFB, or they may start it
        /// themselves and pass the IDirectFB interface pointer to the factory via the
        /// SetDirectFB() function.
        /// Returns false if previously started or if SetDirectFB() was called.
    virtual bool                    SetDirectFB(AE_IDirectFB * pDirectFB) = 0;
        ///< Set the IDirectFB interface pointer.
        /// If the client application is responsible for starting DirectFB, then it may
        /// pass the IDirectFB interface pointer to the plane factory with this function.
        /// Returns false if previously set or if StartDirectFB() has previously been called.
    virtual bool                    StartDirectFBUserInputDriver() = 0;
        ///< Starts a user input thread to receive input events from the DirectFB API.
        /// This user input driver will dispatch user events to the IUserInput interface.
    virtual AE_IDirectFB *          GetDirectFB() = 0;
        ///< Returns the IDirectFB interface pointer.
        /// Note that this returns a custom data type and the client must cast to an IDirectFB *.
        /// (By convention, public AE include files can't include thirdparty include files.)

public:
		// ae::ddk:graphicsdriver::host::DirectFBPlane interface:
	virtual AE_IDirectFBSurface *	GetDfbSurface() = 0; //added by huizh;
//	virtual u8 * GetDfbLockBits() = 0; //added by huizh
	virtual int * GetPlaneWidth()=0; //added by huizh;
	virtual int * GetPlaneHeight()=0; //added by huizh;
	virtual void SetPlaneWidth(int width)=0; //added by huizh;
	virtual void SetPlaneHeight(int height)=0; //added by huizh;

	virtual AE_IDirectFBSurface * FindSurface(void * pBits) = 0; //.. GENE 20100531
		
    virtual ae::stagecraft::Plane * CreatePlane(AE_IDirectFBSurface * pSurface, const ae::stagecraft::Rect & rect) = 0;
        ///< create a plane from a pre-existing IDirectFBSurface.  (NOT YET IMPLEMENTED.)

	virtual bool SetDisplayLayer(int width , int height )=0;
	virtual bool PrimarySurfaceClear()=0;
		
};

}}}}; // end namespace ae::ddk::graphicsdriver::host

#endif // #ifndef _INCLUDE_AE_DDK_GRAPHICSDRIVER_HOST_DIRECTFBPLANEFACTORY_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
////////////////////////////////////////////////////////////////////////////////
//  01-Sep-08   dsletten    created
//  03-Oct-08   dsletten    doxygenated
//  27-Oct-08   dwoodward   fixed up a comment so as not to confuse doxygen
//  29-Oct-08   dwoodward   eliminated unused functions
//  09-Dec-08   dsletten    added pStageWindow param to PlaneFactory::CreatePlane()
//  08-Jan-09   dsletten    added documentation about DirectFB and TTY driver init
