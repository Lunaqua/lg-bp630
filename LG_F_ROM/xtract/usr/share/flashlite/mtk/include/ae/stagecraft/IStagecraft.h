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

#ifndef _INCLUDE_AE_STAGECRAFT_ISTAGECRAFT_H
#define _INCLUDE_AE_STAGECRAFT_ISTAGECRAFT_H

#include <ae/stagecraft/ASExtensions.h>
#include <ae/stagecraft/StagecraftKeyDefs.h>
#include <ae/stagecraft/StagecraftTypes.h>
#include <ae/stagecraft/StageWindow.h>

//[CJ] MTK_PORT
typedef void (*FL_FUNC_CB)(int iEvent, void *pvPrivate);
//--
namespace ae            {
namespace stagecraft    {

///////////////////////////////////////////////////////////////////////////////
/// @mainpage                   Welcome to the Stagecraft Reference Manual
///
/// @section intro Introduction
///
/// The IStagecraft interface is the main entry-point for creating and managing
/// Flash instances using Stagecraft.
///
/// In Stagecraft, Flash instances are embodied by the class StageWindow.  StageWindows
/// are created and destroyed through the IStagecraft interface with the functions
/// CreateStageWindow(), DestroyStageWindow(), CreateStageWindowSet(), and
/// DestroyStageWindowSet().
///
/// <p>Some notes on multiple instances:
///
/// <p>The IStagecraft interface can create and manage multiple independent StageWindows,
/// each running a separate Flash application.  The StageWindowSet class is provided
/// as a convenience to simplify the basic case of creating a set of Flash apps and
/// running them all to completion, but clients can achieve this functionality
/// (and a higher level of control) by accessing the StageWindow interface directly.
///
/// <p>The IStagecraft interface maintains a sense of the "active" StageWindow, such
/// that exactly one StageWindow can be active at a time.  When the IStagecraft
/// module is initially created, there are no StageWindows instantiated, so there
/// is therefore no active StageWindow.  But as soon as a StageWindow is created,
/// it will become the active StageWindow.  Also, if the active StageWindow is
/// destroyed, the Stagecraft module will automatically activate another StageWindow
/// by choosing the most-recently created one.
///
/// <p>The active StageWindow is the default client of user input events dispatched via
/// the IUserInput interface.  Note that some clients will prefer to dispatch user input
/// events directly to the appropriate StageWindow instance themselves, and not through
/// the IUserInput interface.
///
/// <p>The active StageWindow will also have some kind of audio output priority over
/// the other instantiated StageWindows.  The implementation and configuration of this
/// priority is TBD.

class IStagecraft : public IAEModule
{
public: 
    inline static IStagecraft * InitializeStagecraftLibrary() { return InitializeStagecraftLibrary(0, NULL); }
        ///<initializes the Stagecraft library without command line parameters
        ///
        /// This parameterless version of InitializeStagecraftLibrary() initializes the
        /// Stagecraft Library without command line parameters.
        /// @return the IStagecraft interface
        /// @note Only one thread in your process may call InitializeStagecraftLibrary().  From that point
        /// forward the IStagecraft interface is thread safe and any thread may use the IStagecraft
        /// interface handle returned.
        /// @note Typically you do not call this function unless you are function main() of your process.
        /// @see InitializeStagecraftLibrary(int argc, const char ** argv)
        /// @see UninitializeStagecraftLibrary()
         
    static IStagecraft * InitializeStagecraftLibrary(int argc, const char ** argv);
        ///<initializes the Stagecraft library
        ///
        /// This function initializes the Stagecraft library.  Your process argc and argv
        /// parameters from function main should be passed into this library initialization
        /// function.  Calling this function is equivalent to calling:
        /// IAEKernel::InitializeAEKernel() and then using the returned kernel interface
        /// to acquire the stagecraft module.  This function is provided for the convenience
        /// of [most] clients who need not bother with the details of the lower level
        /// IAEKernel interface.
        /// 
        /// <p>This function is used as follows:
        /// <pre>IStagecraft * pIStagecraft = IStagecraft::InitializeStagecraftLibrary(argc, argv);</pre>
        /// @param argc The argc argument count as passed in to function main.
        /// @param argv The argv parameter as passed in to function main.
        /// @return the IStagecraft interface handle.
        /// @note Only one thread in your process may call InitializeStagecraftLibrary().  Typically
        /// this is done from your process' function main().  From the point of stagecraft library
        /// initialization, the IStagecraft interface returned by this function is thread safe and
        /// any thread may use it.
        /// @note For clients who wish to use the IAEKernel also, once InitializeStagecraftLibrary is called,
        /// then IAEKernel::GetKernel() is also available.
        /// @note Typically you do not call this function unless you are function main() of your process.
        /// @see UninitializeStagecraftLibrary()
        /// @see IAEKernel::GetKernel()
        
    static void UninitializeStagecraftLibrary();
        ///<uninitializes the Stagecraft library
        ///
        /// This function uninitializes the Stagecraft library.  Prior to calling UninitializeStagecraftLibrary()
        /// the client must release all objects and resources created through the IStagecraft interface.
        /// @note Only one thread in your process may call UninitializeStagecraftLibrary() - the same thread
        /// that called InitializeStagecraftLibrary().
        /// @note Typically you do not call this function unless you are function main() of your process.
        /// @see InitializeStagecraftLibrary()
        
public:
    virtual void            InitParameters(StageWindowParameters & paramsToSet) = 0;
        ///< Clears a stage window parameter block to system defaults.
        /// Note that this function may be moved to the StageWindow interface in the future.
        /// Most default values should be obvious.  One exception is m_outputFlags, which
        /// is set to kOutputASTrace in DEBUG builds and is zeroed-out in release builds.
        /// Note that AS Trace output behavior can be over-ridden from the command-line
        /// options --astrace and --noastrace.
    virtual bool            ParseCommandLineParameters(int argc, const char ** argv, StageWindowParameters & paramsToSet) = 0;
        ///< Sets a stage window parameter block from command-line arguments.
        /// Note that this function may be moved to the StageWindow interface in the future.
    virtual StageWindow *   CreateStageWindow() = 0;
        ///< Creates a single StageWindow instance.
    virtual StageWindow *   CreateStageWindow(const StageWindowParameters & params) = 0;
        ///< Creates and configures a single StageWindow instance.
    virtual void            DestroyStageWindow(StageWindow * pStageWindow) = 0;
        ///< Destroys a StageWindow instance.

    virtual s32             ShowUsage(const char * pExecutableName, u32 nMaxInstances, void * pShell = NULL) = 0;
        ///< Uses AEPRINT() to print out a usage description of a stagecraft command-line executable.
        /// See stagecraft_main.cpp for a usage example.  Set nMaxInstances to the maximum number
        /// of StageWindows that you want to allow users to specify.  Currently pShell is unused,
        /// as output is sent directly to the AEPRINT() macro.

	virtual AEError         CreateAndConfigureStageWindowSet(StageWindowSet & set, int argc, const char ** argv, u32 nMaxStageWindows) = 0;
        ///< Creates and configures a set of StageWindows
	virtual bool            RunStageWindowSetToCompletion(StageWindowSet & set) = 0;
        ///< Begins playback on all StageWindows in a set and blocks until every one has terminated.
    virtual void            DestroyStageWindowSet(StageWindowSet & set) = 0;
        ///< Destroys all StageWindows contained in a StageWindowSet.

public:
    virtual bool RegisterExtensionClass(const ae::stagecraft::ASExtensionClassInfo & extensionClassInfo) = 0;
        ///< Registers a system-wide default extension class.
        /// RegisterExtensionClass() registers a class that will be made available to all
        /// subsequently-created StageWindows, filtered by the StageWindow's configured extension filter string.
        /// It is good practice to register all of your classes up-front.   A good way to do this is by
        /// placing your extension classes in the IEDKExtensions module, where they will be automatically registered.
        /// Call %RegisterExtensionClass() to register individual extension classes.
        /// For instructions on how to create an extension class.  See <b>some link</b>.

    virtual bool UnregisterExtensionClass(const ae::stagecraft::ASExtensionClassInfo & extensionClassInfo) = 0;
        ///< Unregisters a system-wide default extension class.
        /// UnregisterExtensionClass() will only succeed when all flash instances have gone dormant.
        /// That is to say you cannot unregister extension classes while any flash instances are running.
        /// Once an extension class is successfully unregistered, ownership is released and the
        /// pExtensionClass may be destroyed.

public:
//[CJ] MTK_PORT
    virtual void ActiveInputEvent(bool fgEnable) = 0;
    virtual void SetFlashVars(const char* values) = 0;
    virtual void SetQuality(short quality) = 0;
    virtual void SetAllowFullScreen(int allowFullScreen) = 0;
    virtual void SetScriptAccess(int pAccessLevel) = 0;
    virtual void SetBaseURL(const char* pBaseUrl) = 0;
    virtual void SetBackgroundColor(short red, short green, short blue) = 0;

    virtual void SetCallback(FL_FUNC_CB pfnCB, void *pvPrivate) = 0;
    virtual void SendCallback(int iEvent) = 0;
//--
    virtual void DispatchKeyDown(u32 nKey) = 0;
        ///< dispatches a key down event to the StageWindow with focus
        ///
        /// DispatchKeyDown() dispatches a key down event to the StageWindow with focus.
        ///
        /// @param nKey the AEKEY to dispatch
        
    virtual void DispatchKeyUp(u32 nKey) = 0;
        ///< dispatches a key up event to the StageWindow with focus
        ///
        /// DispatchKeyUp() dispatches a key up event to the StageWindow with focus.
        ///
        /// @param nKey the AEKEY to dispatch
        
    virtual void DispatchMouseButtonDown(MouseButton button, u16 x, u16 y) = 0;
        ///< dispatches a mouse button down event to the StageWindow with focus
        ///
        /// DispatchKeyDown() dispatches a mouse button down event to the StageWindow with focus.
        ///
        /// @param button the mouse button id
        /// @param x the x coordinate of the mouse location
        /// @param y the y coordinate of the mouse location
        
    virtual void DispatchMouseButtonUp(MouseButton button, u16 x, u16 y) = 0;
        ///< dispatches mouse button up event to the StageWindow with focus
        ///
        /// DispatchKeyDown() dispatches mouse button up event to the StageWindow with focus.
        ///
        /// @param button the mouse button id
        /// @param x the x coordinate of the mouse location
        /// @param y the y coordinate of the mouse location
        
    virtual void DispatchMouseMove(u16 x, u16 y) = 0;
        ///< dispatches a mouse move event to the StageWindow with focus
        ///
        /// DispatchKeyDown() dispatches a mouse move event to the StageWindow with focus.
        ///
        /// @param x the x coordinate of the mouse location
        /// @param y the y coordinate of the mouse location
        
    virtual void DispatchScrollWheelScroll(ScrollWheelDirection direction, u16 nScrollAmount) = 0;
        ///< dispatches a scroll wheel scroll event to the StageWindow with focus
        ///
        /// DispatchKeyDown() dispatches a scroll wheel scroll event to the StageWindow with focus.
        ///
        /// @param direction the direction of the scroll wheel scroll
        /// @param nScrollAmount the scroll amount of the scroll normalized to a 16-bit range

//[CJ] MTK_PORT
        // Test by sjhuang
        virtual void CallFunction(char *request) =0;
        virtual void    RefreshPlane(s32 x, s32 y, u32 w, u32 h) =0;
//--        
};

}} // end namespace ae::stagecraft

#endif // #ifndef _INCLUDE_AE_STAGECRAFT_ISTAGECRAFT_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
////////////////////////////////////////////////////////////////////////////////
//  12-Oct-07   dwoodward   created
//  06-Dec-07   dwoodward   added protected virtual destructors
//  02-Jan-08   dwoodward   shored up MainstageNotifier
//  15-Feb-08   dwoodward   added bCenterContentRect parm to CreateMainstage()
//  27-Mar-08   dwoodward   updated CreateMainstage() args, added nBGAlpha and quality parameters
//  29-Mar-08   dwoodward   added GetSwfAuthoredStageDims() and SetRenderPlane()
//  14-May-08   dwoodward   removed letter 'I' prefix from module directory names
//  14-May-08   dwoodward   updated #ifndef guard label
//  19-May-08   sshanson    integrated GraphicsDriver
//  12-Aug-08   dwoodward   added ASExtensionClass stuff
//  03-Oct-08   dsletten    doxygenated
//  09-Oct-08   dwoodward   added InitializeStagecraftLibrary and UnitializeStagecraftLibrary
//  27-Oct-08   dwoodward   made this the main page for doxygen
//  29-Oct-08   dwoodward   moved user input functions here from IUserInput
//  29-Oct-08   dwoodward   got rid of StartupAppEnvironment
//	09-Mar-09	abhardwa	EDK Enhancements
//  15-Mar-09   dwoodward   made RegisterExtensionClass() and UnRegisterExtensionClass() take a
//                          const ASExtensionClassInfo reference
