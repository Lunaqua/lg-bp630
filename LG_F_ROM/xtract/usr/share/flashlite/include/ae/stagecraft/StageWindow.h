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

#ifndef _INCLUDE_AE_STAGECRAFT_STAGEWINDOW_H
#define _INCLUDE_AE_STAGECRAFT_STAGEWINDOW_H

#if 0 //def LGE_PLUGIN_FLASHWARE
#include <string>
#include <vector>
#endif

#include <ae/AETemplates.h>
#include <ae/stagecraft/StagecraftTypes.h>
#include <ae/stagecraft/ASExtensions.h>

namespace ae            {
namespace stagecraft    {

class StageWindow;
class StageWindowParameters;

////////////////////////////////////////////////////////////////////////////////
// class StageWindowNotifier
/// Clients may implement this interface to receive asynchronous updates on a StageWindow's status.
class StageWindowNotifier
{
public:
    enum Event
    {
        kOnUnsupportedVersion,      /// RunEvent   - The SWF content is authored at an unsupported file format version.
                                    ///              The Flash engine will render a placeholder image instead of the Flash movie contents.
                                    ///              This event is only sent if StageWindowParams::m_bRenderPlaceholderForUnsupportedVersions is true;
                                    ///              otherwise the movie will send a kOnCompletion event with a final error status of kErrorUnsupportedVersion.
        kOnContentLoaded,           /// RunEvent   - (NOT IMPLEMENTED!) after the SWF is loaded, stage dims are available now, prior to plane creation
        kOnPlay,                    /// RunEvent   - Flash content is now playing (on initial playback start, or transition from pause or stop)
        kOnPause,                   /// RunEvent   - Flash content is now paused
        kOnStop,                    /// RunEvent   - Flash content is now stopped (NOT sent prior to shutdown)
        kOnCompletion,              /// RunEvent   - Flash content playback is complete
        kOnActivate,                /// RunEvent   - StageWindow has gained input and audio focus
        kOnDeactivate,              /// RunEvent   - StageWindow has lost input and audio focus
        kOnActionScriptStalled,     /// RunEvent   - (NOT IMPLEMENTED!) For future use to notify that ActionScript processing is affecting movie playback
        kOnActionScriptDisabled,    /// RunEvent   - Runaway ActionScript code interrupted and further ActionScript processing DISABLED
        kOnContentError,            /// RunEvent   - Recoverable playback error detected (e.g., media rendering failure, network access failure)
        kOnRenderPlaneUpdate,       /// PlaneEvent - notified after each frame has been rendered on the render plane
        kOnOutputPlaneUpdate,       /// PlaneEvent - notified after each frame is blitted onto the output plane
	
	kOnToFullscreen = 256,
	kOnFromFullscreen,
    };
    virtual void OnStageWindowRunEvent(StageWindow * pStageWindow, Event nEvent) = 0;
        ///< Notifier function for RunEvent types.
    virtual void OnStageWindowPlaneEvent(StageWindow * pStageWindow, Event nEvent, Plane * pPlane, const ae::stagecraft::Rect & updateRect) = 0;
        ///< Notifier function for PlaneEvent types.
        /// Stagecraft clients have two ways to be notified when the render plane and output plane
        /// have been updated.  One way is to write their own implementation of the abstract class
        /// ae::stagecraft::Plane and use its OnRectUpdated() notification function to trap these
        /// events.  The other way is to use this notification function in the StageWindowNotifier.
        /// This notifier is supplied for clients who want to use an off-the-shelf Plane / PlaneFactory
        /// implementation without modification and still receive frame-update events in the context
        /// of a StageWindowNotifier implementation.
protected:
    virtual ~StageWindowNotifier() { }
};

////////////////////////////////////////////////////////////////////////////////
// enum Quality
/// Flash quality settings.
/// The Flash rendering engine can operate at different quality levels, that require
/// increasing amounts of CPU when rendering each frame.
enum Quality
{
    kQualityLow,
    kQualityMedium,
    kQualityHigh
};

////////////////////////////////////////////////////////////////////////////////
// enum Security
/// Flash security settings.
enum Security
{
    kTrusted,           /// SWFs may read from local files, interact with any server,
                        /// and script any other SWF.
    kSandboxed          /// If the networking bit is set while publishing, then the player
                        /// will treat the swf as if it is in the local with networking sandbox.
                        /// else, if the networking bit is not set, it will treat the swf as local untrusted.
};

////////////////////////////////////////////////////////////////////////////////
// enum OutputFlags
/// Flags that can be logically OR-ed together to form the value of m_outputFlags
/// in the StageWindowParameters block.
enum OutputFlags
{
    kOutputASTrace          = (1 << 0),                 /// AS trace() output
    kOutputFPS              = (1 << 1),                 /// dump periodic FPS stats
    kOutputFPSVerbose       = (1 << 2),                 /// dump verbose periodic FPS stats
    kOutputDoPlayLoopTiming = (1 << 3),                 /// debug information on FL DoPlay() timing
    kOutputFeatureDump      = (1 << 4)                  /// dump FL feature definitions
};

////////////////////////////////////////////////////////////////////////////////
// class StageWindowParameters
/// A parameter block for configuring a StageWindow instance.
class StageWindowParameters
{
public:
    const char *            m_pContentURL;              ///< URL of content to play
                                                        ///     - note that only filenames and file:// URLs are currently supported

    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    // Added to support browser plug-in 
    const char *            m_pOriginContentURL;        /// Original URL of content to play
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////

    ae::stagecraft::Point   m_pPluginAbsolutePosition;  // The absolute position of the leftmost upper corner of the flash plugin window

    const char *            m_pKeymapURL;               /// URL of keymap XML file
                                                        ///     - note that only filenames and file:// URLs are currently supported
                                                        ///     - if a keymap URL is not provided, the StageWindow will attempt to open
                                                        ///       keymap.xml in the same directory as the m_pContentURL
    Plane *                 m_pRenderPlane;             ///< plane to render each Flash frame (StageWindow will try to create its own if NULL)
    Plane *                 m_pOutputPlane;             ///< plane to copy each fully rendered Flash frame for final display
                                                        ///     - StageWindow will create its own if NULL and if m_bCreateOutputPlane is true
    const char *            m_pRenderPlaneClassName;    ///< render plane class (NULL means the platform-dependent default)
                                                        ///     - this field is not used if a render plane is provided in m_pRenderPlane
    const char *            m_pBitmapPlaneClassName;    ///< off-screen plane class (NULL means the platform-dependent default)
    const char *            m_pOutputPlaneClassName;    ///< output plane class (NULL means the platform-dependent default)
                                                        ///     - this field is not used if an output plane is provided in m_pOutputPlane
    Dims                    m_contentDims;              ///< dims of render plane (default is SWF-authored Stage dims)
                                                        ///     - Flash engine will render Flash content to these dims
                                                        ///     - TODO: option to use a sub-rect of the render plane for Flash content
    Dims                    m_outputDims;               ///< dims of output plane
                                                        ///     - if Dims(0, 0) then default is same dims as render plane
                                                        ///     - position of output plane is plane factory's default
                                                        ///     - frames will be scaled with a 2D Blit from content plane to output plane
    Rect                    m_outputRect;               ///< rect of output plane
                                                        ///     - x and y specify output plane's position on screen
                                                        ///     - frames will be scaled with a 2D Blit from content plane to output plane
                                                        ///     - if m_outputRect is non-zero then m_outputDims will be ignored
    ColorFormat             m_renderColorFormat;        ///< use kNullColorFormat for same as output plane or system default
    ColorFormat             m_outputColorFormat;        ///< use kNullColorFormat for system default
    bool                    m_bCreateOutputPlane;       ///< set to false to disable output plane creation (default is true)
    bool                    m_bScaleRenderPlaneToFit;   ///< if true, and m_contentDims is (0, 0), then the render plane dims will be scaled down
                                                        ///     to fit (preserving the aspect ratio) in the case where the SWF-authored Stage
                                                        ///     dims are larger than the output plane's maximum dimensions (usu. screen dims)
                                                        ///     (default is true)
    const char *            m_pTitle;                   ///< specify a title for output plane creation (default is SWF filename)
    StageWindowNotifier *   m_pStageWindowNotifier;     ///< notifier object to receive asynchronous state notifications during playback
    u8                      m_nBGAlpha;                 ///< Flash Stage bgalpha - 0 is fully transparent, 255 is fully opaque
    Quality                 m_quality;                  ///< Flash playback quality level
    Security                m_security;                 ///< Flash FI_SecuritySandbox value.
    const char *            m_pASExtensionsFilter;      ///< A comma-delimited string representing the allowed AS extension classes for this
                                                        ///     StageWindow.  NULL represents the default filter of "*", which allows
                                                        ///     access to all AS extensions.  An empty string ("") means don't allow any
                                                        ///     extensions.  A single wildcard char of '*' is allowed as the last field of
                                                        ///     an AS namespace filter entry.  An example: "com.adobe.*,com.mycorp.*"
    const char *            m_pFlashVars;               ///< A string containing name value pairs that will be passed to the Flash Instance
                                                        ///     as root movie parameters.  The format of the string is a set of name=value
                                                        ///     combinations separated by '&'. Special and/or non-printable characters can
                                                        ///     be escaped with a '%' followed by a 2 digit hexadecimal value. A single
                                                        ///     blank space can be represented using the '+' sign.
                                                        ///     <p>Example: "username=The+Fonz&password=aaayyy"
    const char *            m_pSSLClientCertTable;      ///< A string of bracket delimited 2 or 3-tuples that represent mappings of host
                                                        ///     names to file names of the host-specific client certificate and (optional) private key
                                                        ///     files to be used for ssl mutual authentication for this flash instance.
                                                        /// A string of bracket delimited 2 or 3-tuples that represent mappings of host
                                                        ///     names to file names of the host-specific client certificate and (optional) private key
                                                        ///     files to be used for ssl mutual authentication for this flash instance against the specified host(s).
                                                        ///     The format of the bracket delimited string is:<br>
                                                        ///     <code>"[hostname1.domain1.com^CertFileName1.pem^PrivateKeyFileName1.pem][hostname2.domain2.com^CertFileName2.pem][hostname3.domain3.com^CertFileName3.pem^PrivateKeyFileName3.pem]"</code><br>
                                                        ///     where each 2 or 3-tuple is caret (^) delimited such that the first
                                                        ///     part is the URL encoded host name and the second and third parts are
                                                        ///     the URL encoded filenames for the client certificate and private key, to be used
                                                        ///     for SSL mutual authentication against that host.
                                                        ///     The filenames are filenames with no path component.  The path is assumed to be
                                                        ///     /etc/stagecraft-data/ssl/certs (or /tmp/stagecraft-data/ssl/certs if /etc/stagecraft-data
                                                        ///     is not writable).
                                                        ///     <br>If the m_pSSLClientCertTable string is NULL or empty, ssl mutual authentication will not be performed.
                                                        ///     <br>If the string is present, then for each http:// or https:// request,
                                                        ///     the table will be consulted for a matching hostname record.  If found, the client
                                                        ///     certificate and private key files referenced will be used for ssl mutual authentication
                                                        ///     if the server requests it.  The certificates and private keys should be stored in files in PEM format.                                
    bool                    m_bRenderPlaceholderForUnsupportedVersions;
                                                        ///< If true, render a placeholder image for unsupported SWF versions.
                                                        ///     This value determines if, on loading a SWF authored with an unsupported Flash
                                                        ///     version, Stagecraft should immediately transition to a fatal error condition
                                                        ///     (kErrorUnsupportedVersion), or just send a recoverable error notification
                                                        ///     (kOnUnsupportedVersion) and render a placeholder image instead of the Flash content.
    bool                    m_bUnthrottleFramerate;     ///< play back Flash frames as fast as CPU can handle
    u32                     m_outputFlags;              ///< choose types of console output to enable
    u32                     m_nMaxMemoryUsageBytes;     ///< Maximum system (not graphics) memory available to this Flash instance.
                                                        ///     Set to zero for no limit.  (default is zero)
};

////////////////////////////////////////////////////////////////////////////////
// class StageWindowSet
/// A class representing a collection of StageWindows.
class StageWindowSet : public AEArray<StageWindow *> { } ;

////////////////////////////////////////////////////////////////////////////////
// enum StatusCode
/// A status code representing the current state of a StageWindow.
/// If a StageWindow has an error status, no further action on it will succeed.
/// (I.e., it can't be re-configured and re-used.)
enum StatusCode
{
    kStatusUnconfigured,                /// initial creation
    kStatusConfigured,                  /// successful configuration
    kStatusLoading,                     /// SWF is currently loading
    kStatusLoaded,                      /// SWF loaded, stage dims available, planes not created yet
    kStatusReadyToAnimate,              /// SWF loaded and planes created
    kStatusPlaying,                     /// playing
    kStatusPaused,                      /// paused
    kStatusStopped,                     /// stopped
    kStatusComplete,                    /// playback complete

    kErrorIncompleteConfiguration,      /// missing content URL or invalid AS extension filter
    kErrorModuleUnavailable,            /// missing required AE module
    kErrorNotEnoughMemory,              /// not enough memory
    kErrorInvalidURL,                   /// couldn't load content URL
    kErrorStartupFailure,               /// Flash lib did not start successfully
    kErrorUnsupportedVersion,           /// the SWF version is unsupported
                                        /// Note that this error state will only be set if
                                        /// StageWindowParams::m_bRenderPlaceholderForUnsupportedVersions is set to false.
    kErrorCorruptMovie,                 /// the SWF data is unreadable
    kErrorRenderPlaneCreationFailure,   /// couldn't create render plane
    kErrorOutputPlaneCreationFailure,   /// couldn't create output plane
    kErrorIncompatibleRenderPlane,      /// render plane is not compatible with Flash lib
    kErrorFatalPlaybackError            /// Flash lib exited abnormally
};

////////////////////////////////////////////////////////////////////////////////
// class StageWindow
/// A class representing a single instance of a Flash player application.
class StageWindow
{
public:
    virtual bool        Configure(const StageWindowParameters & params) = 0;
        ///< Configures the StageWindow with settings from a parameter block.
        /// For this function to succeed the StageWindow must be in the kStatusUnconfigured
        /// state.  This function is synchronous and returns immediately.  On success, the
        /// status of the StageWindow is advanced to kStatusConfigured.
    virtual bool        Configure(int argc, const char ** argv) = 0;
        ///< Configures the StageWindow with settings from the command-line.
        /// Intended for clients providing a command-line application that can run
        /// a single Flash instance.  For an application that supports multiple
        /// instances, see the StageWindowSet functions exposed by the IStagecraft
        /// interface.  This function is synchronous and returns immediately.  On success, the
        /// status of the StageWindow is advanced to kStatusConfigured.
    virtual bool        RegisterExtensionClass(const ae::stagecraft::ASExtensionClassInfo & extensionClassInfo) = 0;
        ///< RegisterExtensionClass() registers an extension class for this StageWindow.
        /// Extension classes may only be registered when the StageWindow status is
        /// kStatusConfigured, kStatusLoaded, or kStatusReadyToAnimate.
        /// Returns false if the StageWindow is in another state or if the extension does not
        /// match the extension class filter or if an extension with the same class name is
        /// already registered.
        /// <p>NOTE: Once an extension class is registered it cannot be unregistered!
public:
    virtual bool        LoadSWF() = 0;
        ///< BLOCKING function to load the SWF; makes SWF dims available
        /// Loads SWF and makes the SWF-authored dims available via GetSWFStageDims().
        /// Status must be kStatusConfigured for LoadSWF() to succeed.
        /// Note that a non-blocking version of this function, LoadSWFAsync(), is
        /// also available to support future versions of Stagecraft that can load
        /// SWF content from network URLs.  Currently SWFs can only be loaded from
        /// the local fileystem, so the blocking of this function should be minimal.
        /// <p>On success, the status will advance to kStatusLoaded unless the client
        /// has already provided the required planes in a previous call to Configure(),
        /// in which case the status will advance to kStatusReadyToAnimate.
    virtual bool        CreatePlanes() = 0;
        ///< BLOCKING function to create render and output planes as req'd by configuration
        /// Creates render (and optionally output) planes based on the settings provided
        /// from a previous call to Configure().  Status must be kStatusConfigured or
        /// kStatusLoaded for CreatePlanes() to succeed.  If status is kStatusConfigured
        /// then CreatePlanes() will make a blocking call to LoadSWF() before creating planes.
        /// Note that users may also provide their own Planes when in the kStatusLoaded state
        /// by calling SetRenderPlane() and SetOutputPlane().
        /// On success, the status will advance to kStatusReadyToAnimate.
    virtual bool        RunToCompletion() = 0;
        ///< BLOCKING function to run the SWF until completion
        /// This function puts the Flash movie into the Playing state and blocks until
        /// animation is complete.  Generally movie completion is triggered by the end-user
        /// dispatching an AEKEY_TERMINATEANIMATION key event to the StageWindow or by
        /// a code module calling the Terminate() function.
        /// <p>Status must be one of kStatusConfigured, kStatusLoaded, kStatusReadyToAnimate,
        /// or kStatusPlaying for this function to succeed.  RunToCompletion() will call
        /// the functions LoadSWF(), CreatePlanes(), and Play() to initiate playback as
        /// necessary depending on the current status, and will then wait until animation
        /// is complete before return control to the caller, unless an error condition
        /// results.
public:
    virtual bool        LoadSWFAsync() = 0;
        ///< NON-BLOCKING function to begin asynchronous SWF loading
        /// Initiates asynchronous SWF loading.  Status must be kStatusConfigured for
        /// this function to succeed.  This function is not particularly useful at the
        /// moment because Stagecraft can only load SWF data from the local filesystem,
        /// which does not block for very long.  In the future, Stagecraft will support
        /// network URLs for the SWF content, which could result in a SWF-loading stage
        /// that could take a noticable amount of time to complete.
        /// <p>While the SWF is loading, the StageWindow will transition to kStatusLoading.
        /// When the load is complete, the status will transition to kStatusLoaded.
        /// Clients can receive notification of these transitions via the
        /// StageWindowNotifier specified to Configure(), or poll using GetStatus().
public:
    virtual bool        Play() = 0;
        ///< NON-BLOCKING function to begin asynchronous SWF playback
        /// Begins animation playback on a StageWindow.  Status must be kStatusReadyToAnimate,
        /// kStatusPaused, or kStatusStopped for this function to succeed.  Note that the
        /// status might not immediately transition to kStatusPlaying after this function
        /// returns; the request will be handled in short time but asynchronously.
    virtual bool        Pause() = 0;
        ///< NON-BLOCKING function to pause asynchronous SWF playback
    virtual bool        Stop() = 0;
        ///< NON-BLOCKING function to stop asynchronous SWF playback (NOT IMPLEMENTED)
    virtual bool        Terminate() = 0;
        ///< NON-BLOCKING function to terminate playback
        /// Terminate() will return true as long as the StageWindow is not currently
        /// in kStatusComplete or any of the error statuses.  Requests the Flash player
        /// to complete playback and transition to kStatusComplete.  Once the StageWindow
        /// is in kStatusComplete (or any of the error statuses) then the StageWindow
        /// will no longer reference any client-provided render or output planes, and
        /// they may be safely destroyed.
public:
    virtual bool        SetRenderPlane(Plane * pRenderPlane) = 0;
        ///< Registers a client-provided render plane.
        /// Registers a client-provided render plane.  Status must be kStatusConfigured
        /// or kStatusLoaded for SetRenderPlane() to succeed, and a render plane must
        /// not have been previously supplied to Configure().  On success, status will
        /// advance to kStatusReadyToAnimate.
    virtual bool        SetOutputPlane(Plane * pOutputPlane) = 0;
        ///< Registers a client-provided output plane.
        /// Registers a client-provided output plane.  Status must be kStatusConfigured,
        /// kStatusLoaded, or kStatusReadyToAnimate to succeed, and an output plane
        /// must not have been previously supplied to Configure() or to a previous
        /// call to SetOutputPlane().  On success, status will not change.
public:
    virtual void        SetActiveStageWindow() = 0;
        ///< Makes this the active StageWindow.
        /// Any instantiated StageWindow can become the active StageWindow even if
        /// it is not in a playing state.
    virtual bool        IsActiveStageWindow() = 0;
        ///< Returns true if this is the current active StageWindow.
public:
	virtual void			DispatchEvent(u8 *pEvent, u8 *pData) = 0;
    virtual void        DispatchKeyDown(u32 nKey , bool keyskip) = 0;
        ///< Dispatches a key-down event directly to this StageWindow.
    virtual void        DispatchKeyUp(u32 nKey) = 0;
        ///< Dispatches a key-up event directly to this StageWindow.
    virtual void        DispatchMouseButtonDown(MouseButton button, u16 x, u16 y, bool keyskip) = 0;
        ///< Dispatches a mouse-down event directly to this StageWindow.
        /// NOTE: When the StageWindow is configured with an output plane, the mouse point
        /// is assumed to map to the dimensions of the output plane, not the render plane.
    virtual void        DispatchMouseButtonUp(MouseButton button, u16 x, u16 y, bool keyskip) = 0;
        ///< Dispatches a mouse-up event directly to this StageWindow.
        /// NOTE: When the StageWindow is configured with an output plane, the mouse point
        /// is assumed to map to the dimensions of the output plane, not the render plane.
    virtual void        DispatchMouseMove(u16 x, u16 y) = 0;
        ///< Dispatches a mouse move event directly to this StageWindow.
        /// NOTE: When the StageWindow is configured with an output plane, the mouse point
        /// is assumed to map to the dimensions of the output plane, not the render plane.
    virtual void        DispatchScrollWheelScroll(ScrollWheelDirection direction, u16 nScrollAmount) = 0;
        ///< Dispatches a scroll wheel event directly to this StageWindow.
    
    // 20100727 DYKim
    virtual void 		DispatchForceScreenUpdate() = 0;

	// 20110317 DYKim - EI_Event
	virtual void		DispatchEIEvent(u8 *pEvent, u8 *pData, u8 *pDataSub) = 0;

	// 20100811 DYKim
	virtual void *		GetPlayerObject() = 0;
	
	#if 1
	virtual	void							InitPluginFunction() = 0;
	virtual void 							SetPluginFunction(void *pluginObject, struct PluginCallback *callback) = 0;
	virtual struct PluginCallback *			GetPluginFunction() = 0;
	virtual void *							GetPluginObject() = 0;
	virtual void *							PluginCallFunction (void *pMMObj, const char* request, void *allocWrapper) = 0;
	virtual bool 							GetCompletedCallFunction() = 0;
	virtual void 							SetCompletedCallFunction(bool flag) = 0;
	#endif
	
	virtual void		DispatchFontChangeEvent() = 0;
	virtual bool		GetScriptStruckStatus() = 0;				
	virtual u32			GetDisplayMode() = 0;				
	virtual void		SetDisplayMode(u32 nDisplayMode) = 0;
    virtual u32        	GetKeyBoardMode() = 0;		
    virtual void        SetKeyBoardMode(u32 nKeyBoardMode) = 0;
    virtual u16        	GetExecutePID() = 0;		
    virtual void        SetExecutePID(u16 nExecutePID) = 0;		

	virtual void 		ExternalInterfaceSetInt(char *varString, int val) = 0;
	virtual void 		ExternalInterfaceSetString(char *varString, char * val) = 0;
	
public:
    virtual StatusCode  GetStatus() = 0;
        ///< Returns the current status of the StageWindow.
    virtual Dims        GetSWFStageDims() = 0;
        ///< Returns the stage dimensions set in the Flash application.
        /// This function will return Dims(0, 0) if the SWF has not yet been loaded.
    virtual Plane *     GetRenderPlane() = 0;
        ///< Returns the render plane associated with this StageWindow.
        /// Can return NULL before status kStatusReadyToAnimate.
    virtual Plane *     GetOutputPlane() = 0;
        ///< Returns the output plane associated with this StageWindow.
        /// This function can return NULL depending on the configuration of the StageWindow.
    virtual class MemoryWatchdog * GetMemoryWatchdog() = 0;
        ///< Returns a memory watchdog interface for respectful memory allocation.
        /// This interface should be used to allocate large amounts of memory that are
        /// associated with this StageWindow instance.  The watchdog can return NULL
        /// if the StageWindow instance has reached its memory allocation limit.  All
        /// memory needs to be released before the StageWindow object is destroyed.
        /// GetMemoryWatchdog() will return NULL until a successful call to Configure().
public:
    virtual void SetContext(const char * pContextName, void * pContext) = 0;
        ///< Sets a client context identified by name
        /// Call %SetContext() to set a client context identified by a context name
        /// Use this to associate named client data with the stage window.
        /// These contexts are just associated with the StageWindow and unassociated
        /// when the StageWindow is destroyed.  The StageWindow does not attempt to
        /// manage any resources that may be associated with the context
        /// @param pContextName the context name
        /// @param pContext the context to associate
        /// @see GetContext()
        /// @note Call %SetContext() with a NULL pContext to clear the context associated with pContextName.
        ///       It is not strictly necessary to clear contexts, they will be automatically cleared
        ///       at StageWindow destruction time.
    virtual void * GetContext(const char * pContextName) = 0;
        ///< Retrieves a client context identified by name
        /// Call %GetContext() to get the previously set named context.
        /// Use this to retrieve client data previously associated with the stage window.
        /// @param pContextName the context name
        /// @return the context previously associated with pContextName
        /// @see SetContext()
protected:
    virtual ~StageWindow() { }
};

////////////////////////////////////////////////////////////////////////////////
// class MemoryWatchdog
/// This class provides an interface for allocating memory associated with a
/// particular StageWindow instance. It is used to limit memory usage of one
/// instance and will return NULL once the limit has been reached.
///
/// <p>Use the WATCHDOG_TRACKER_PARAMS #define to fill in the final two arguments of the
/// Alloc() and Free() function calls to register the location of each memory
/// operation for debug tracking, like this:
///
/// pMemoryWatchdog->Alloc(nSizeBytes, WATCHDOG_TRACKER_PARAMS);
//
//
#ifdef _DEBUG
    #define WATCHDOG_TRACKER_PARAMS __FILE__, __LINE__
#else
    #define WATCHDOG_TRACKER_PARAMS NULL, 0
#endif
//
//
class MemoryWatchdog
{
protected:
    MemoryWatchdog() {}
    virtual ~MemoryWatchdog() {}
public:
    virtual void * Alloc(u32 nBytes, const char * pFilename, u32 nLineNum) = 0; /// allocates a block of memory
    virtual void Free(void * pMem, const char * pFilename, u32 nLineNum) = 0; /// de-allocates memory allocated with Alloc()
    virtual u32 GetBytesUsed() = 0; /// returns number of bytes currently allocated by this watchdog
    virtual u32 GetBytesAvailable() = 0; /// returns number of bytes available to allocate by this watchdog
    /// NOTE: returns 0xFFFFFFFF when there is no limit
};


}} // end namespace ae::stagecraft

#endif // #ifndef _INCLUDE_AE_STAGECRAFT_STAGEWINDOW_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
////////////////////////////////////////////////////////////////////////////////
//  20-Aug-08   dsletten    created
//  19-Sep-08   dsletten    added activation notifiers and methods
//  19-Sep-08   dsletten    added m_pASExtensionsFilter to StageWindowParameters
//	01-Oct-08	kchandra	sending the co-ordinates along with mouse button up/down event
//  02-Oct-08   sshanson    store the Security level in the StageWindowParameters
//  03-Oct-08   dsletten    doxygenated
//  24-Oct-08   dsletten    StageWindowNotifier: added recoverable error Events,
//                          removed OnStageWindowStartupEvent(), added updateRect
//                          param to OnStageWindowPlaneEvent()
//  29-Oct-08   dwoodward   ScrollDirection is now ScrollWheelDirection
//  29-Oct-08   dsletten    added kOnUnsupportedVersion, kErrorUnsupportedVersion,
//                          kErrorCorruptMovie, m_bRenderPlaceholderForUnsupportedVersions
//  09-Dec-08   dsletten    added class MemoryWatchdog, StageWindowParameters::m_nMaxMemoryUsageBytes,
//                          StageWindow::GetMemoryWatchdog()
//  13-Dec-08   dwoodward   added pFilename and nLineNum parameters to MemoryWatchdog::Alloc() and Free()
//  16-Dec-08   sshanson    added --preroll command line option
//  15-Jan-09   dsletten    added LoadSWFAsync(), SetRenderPlane(), SetOutputPlane()
//  17-Jan-09   dwoodward   added m_pFlashVars to StageWindowParameters
//  21-Jan-09   dsletten    added m_bScaleRenderPlaneToFit
//  03-Mar-08   dwoodward   no trailing commas on the last item in an enum
//	09-Mar-09	abhardwa	EDK Enhancements
//  13-Mar-09   sshanson    removed --preroll command line option
//  15-Mar-09   dwoodward   made RegisterExtensionClass() take a const ASExtensionClassInfo reference
//  20-Mar-09   dwoodward   added GetContext() and SetContext()
//  01-May-09   dwoodward   added m_pSSLClientCertTable to StageWindowParameters
