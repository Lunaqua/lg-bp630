/******************************************************************************
ADOBE SYSTEMS INCORPORATED
Copyright Adobe Macromedia Software LLC 1993-2006
All Rights Reserved

NOTICE: Adobe permits you to use, modify, and distribute this file in
accordance with the terms of the Adobe license agreement accompanying it. If
you have received this file from a source other than Adobe, then your use,
modification, or distribution of it requires the prior written permission
of Adobe.
******************************************************************************/

#ifndef __FLASHLITEINTERFACE_H__
#define __FLASHLITEINTERFACE_H__

#ifdef __cplusplus
extern "C" {
#endif

/* contains flashlite struct & enum declarations */
#include "commondef.h"
#include "flashlitedef.h"

/* This file contains function prototype definitions for the SI_
 * and FI_ functions that define the interface between the client
 * and the FlashLite Player.
 *
 */
/*****************************************************************************\
    Our defines for dynamic library export symbols. Meaning of these symbols
    can be controlled via flashliteInterfaceConfig.h
\*****************************************************************************/
#ifdef FLASH_GENERIC
#undef DLLEXPORT
#undef DLLENTRYPOINT
#endif

#ifndef DLLEXPORT
    #define DLLEXPORT
#endif

#ifndef DLLIMPORT
    #define DLLIMPORT
#endif

#ifndef DLLENTRYPOINT
    #define DLLENTRYPOINT
#endif

#if defined( EXPORT_ALL_FI_FUNCTIONS ) && defined( IMPORT_ALL_SI_FUNCTIONS )

	#ifdef FLASHLITE_DLL_EXPORT
		// When building the flashlite library, it needs to enable FLASHLITE_DLL_EXPORT.
		// FI functions are declared as 'export', because FI functions are referred from 
		// outside of flashlite library. SI functions are declared as 'import', because 
		// the implementations of SI functions don't exist in the flashlite library.
		#define DLLEXPORTFNI        DLLEXPORT
		#define DLLEXPORTFNI_IMPL   DLLEXPORT_IMPL
		#define DLLIMPORTFNI        DLLIMPORT
		#define DLLIMPORTFNI_IMPL   DLLIMPORT_IMPL
	#else
		// When building the host-side library, it needs to disable FLASHLITE_DLL_EXPORT.
		// FI functions are declared as 'import', because the implementations of
		// FI functions don't exist in the client library. SI functions are declared
		// as 'export', because SI functions are referred from inside of flashlite library.
		#define DLLEXPORTFNI        DLLIMPORT
		#define DLLEXPORTFNI_IMPL   DLLIMPORT_IMPL
		#define DLLIMPORTFNI        DLLEXPORT
		#define DLLIMPORTFNI_IMPL   DLLEXPORT_IMPL
	#endif // FLASHLITE_DLL_EXPORT

#else

	#ifdef EXPORT_ALL_FI_FUNCTIONS
		#define DLLEXPORTFNI        DLLEXPORT
		#define DLLEXPORTFNI_IMPL   DLLEXPORT_IMPL
	#else
		#define DLLEXPORTFNI
		#define DLLEXPORTFNI_IMPL
	#endif // EXPORT_ALL_FI_FUNCTIONS

	#define DLLIMPORTFNI
	#define DLLIMPORTFNI_IMPL

#endif // defined( EXPORT_ALL_FI_FUNCTIONS ) && defined( IMPORT_ALL_SI_FUNCTIONS )

	
/****************************************************************************\
    MM_Object structure definitions. All FI_xxx functions takes MM_Object
    structure pointer.
\****************************************************************************/

struct SI_Fncs;
struct FI_Fncs;

struct MM_Object
{
    const struct FI_Fncs*   pFIFncs;
    struct SI_HostObject*   pHostObj;
	NO_OPERATOR_NEW_DELETE
};




/****************************************************************************
 *  SI_xxx Function prototype declarations (grouped based on functionality)
 ****************************************************************************/

/************************************************************************
 * SI_xxx functions used for dynamic memory management functions
 */

/* This function notifies the device that it can free the
 * memory block allocated by SI_Malloc.
 */
DLLIMPORTFNI typedef void            (*PFNC_SI_FREE)                 (struct MM_Object* pMMObj, void* memory);

/* Gets the maximum amount of memory available to the player.
 */
DLLIMPORTFNI typedef long            (*PFNC_SI_GETMAXAVAILABLEPLAYERMEMORY)  (struct MM_Object* pMMObj);

/* This function requests a contiguous non-movable chunk of system memory
 * of a size that is a multiple of 32k. The numBlocks argument indicates the
 * number of 32k blocks that must be allocated. It must return a pointer to the
 * memory or 0 if the memory allocation failed..
 */
DLLIMPORTFNI typedef void*           (*PFNC_SI_MALLOC)               (struct MM_Object* pMMObj, unsigned short numBlocks);

/* Function to notify the hosting application of a change to the frame
 * rate. This happens when a new movie is loaded as the root movie.
 */
DLLIMPORTFNI typedef void            (*PFNC_SI_UPDATEFRAMEINTERVAL)  (struct MM_Object* pMMObj, unsigned long newFrameInterval);

/************************************************************************
 *  SI_xxx function used for debugging purpose
 */
// debugging function to aid debugging on the device
DLLIMPORTFNI typedef void            (*PFNC_SI_DEBUG)                (struct MM_Object* pMMObj, const char* message, long number);

/************************************************************************
 *  SI_xxx functions used for off-screen frame-buffer access
 */

/* Function to return the lock the frame buffer memory and return
 * a pointer to it.
 */
DLLIMPORTFNI typedef unsigned char*  (*PFNC_SI_GETLOCKEDFRAMEBUFFER) (struct MM_Object* pMMObj);

/* Function to release the lock on the frame buffer memory.
 */
DLLIMPORTFNI typedef void            (*PFNC_SI_UNLOCKFRAMEBUFFER)    (struct MM_Object* pMMObj, unsigned char* pBits);


/* Function to lock the text buffer memory and return a pointer to it.
   If the text buffer is requested, device text render should occur on this
   buffer instead of the frame buffer. The text buffer is needed for hardware
   rendering so that it can convert the device rendered text to a texture/image.
   The texture buffer must be an 8bit/pixel grey scale buffer that is the same
   size as the frame buffer. Pixel values of 0 indicate full transparency
   and pixel values of 255 indicate full opacity.
 */
typedef unsigned char*  (*PFNC_SI_GETLOCKEDTEXTBUFFER) (struct MM_Object* pMMObj,
														unsigned int* width,
														unsigned int* height,
														unsigned int* pitch, 
														unsigned int* depth);

/* Function to release the lock on the frame buffer memory.
 */
typedef void            (*PFNC_SI_UNLOCKTEXTBUFFER)    (struct MM_Object* pMMObj, unsigned char* pBits);

// ACHW

/* Function to create context */
DLLIMPORTFNI typedef short           (*PFNC_SI_GETHARDWAREINTERFACES) (struct MM_Object* pMMObj, void** ppEgl, void** ppApi);

DLLIMPORTFNI typedef FI_Display      (*PFNC_SI_EGLGETDISPLAY)        (struct MM_Object* pMMObj);

DLLIMPORTFNI typedef short           (*PFNC_SI_EGLINITIALIZE)        (struct MM_Object* pMMObj,
                                                                      FI_Display display,
                                                                      short* pMajor,
                                                                      short* pMinor);

DLLIMPORTFNI typedef short           (*PFNC_SI_EGLTERMINATE)         (struct MM_Object* pMMObj,
                                                                      FI_Display display);

DLLIMPORTFNI typedef FI_Context      (*PFNC_SI_EGLCREATECONTEXT)     (struct MM_Object* pMMObj,
                                                                      FI_Display display,
                                                                      FI_Config config,
                                                                      FI_Context shareContext,
                                                                      const long* pAttribList);

DLLIMPORTFNI typedef short           (*PFNC_SI_EGLCHOOSECONFIG)      (struct MM_Object* pMMObj,
                                                                      FI_Display display,
                                                                      const long* pAttribList,
                                                                      FI_Config* pConfigs,
                                                                      unsigned short configSize,
                                                                      unsigned short* pNumConfig);

DLLIMPORTFNI typedef struct FI_Surface* (*PFNC_SI_EGLCREATEPBUFFERSURFACE) (struct MM_Object* pMMObj,
                                                                            FI_Display display,
                                                                            FI_Config config,
                                                                            const long* pAttribList);

DLLIMPORTFNI typedef struct FI_Surface* (*PFNC_SI_EGLCREATENATIVEPIXMAPSURFACE) (struct MM_Object* pMMObj,
                                                                                 FI_Display display,
                                                                                 FI_Config config,
                                                                                 unsigned short width,
                                                                                 unsigned short height,
                                                                                 const long* pAttribList,
                                                                                 struct FI_PixmapInfo* pPixmapInfo);

DLLIMPORTFNI typedef struct FI_Surface* (*PFNC_SI_EGLCREATEPBUFFERFROMCLIENTBUFFER) (struct MM_Object* pMMObj,
                                                                                     FI_Display display,
                                                                                     unsigned long buftype,
                                                                                     FI_ClientBuffer buffer,
                                                                                     FI_Config config,
                                                                                     const long* pAttribList);

DLLIMPORTFNI typedef short           (*PFNC_SI_EGLMAKECURRENT)       (struct MM_Object* pMMObj,
                                                                      FI_Display display,
                                                                      const struct FI_Surface* pDraw,
                                                                      const struct FI_Surface* pRead,
                                                                      FI_Context context);

DLLIMPORTFNI typedef short           (*PFNC_SI_EGLDESTROYCONTEXT)    (struct MM_Object* pMMObj,
                                                                      FI_Display display,
                                                                      FI_Context context);

DLLIMPORTFNI typedef short           (*PFNC_SI_EGLDESTROYSURFACE)    (struct MM_Object* pMMObj,
                                                                      FI_Display display,
                                                                      struct FI_Surface* pSurface);

DLLIMPORTFNI typedef short           (*PFNC_SI_EGLGETCONFIGATTRIB)   (struct MM_Object* pMMObj,
                                                                      FI_Display display,
                                                                      FI_Config config,
                                                                      long attrib,
                                                                      long* pValue);

DLLIMPORTFNI typedef short           (*PFNC_SI_EGLCOPYSURFACETOFRAMEBUFFER) (struct MM_Object* pMMObj,
                                                                             FI_Display display,
                                                                             const struct FI_Surface* pSurface,
                                                                             unsigned char* pDst,
                                                                             unsigned short width,
                                                                             unsigned short height,
                                                                             unsigned short pitch,
                                                                             struct FI_Rect* dirtyRect);
// ACHW

/************************************************************************
 *  SI_xxx functions used for data access
 */

DLLIMPORTFNI typedef short  (*PFNC_SI_SETPERSISTENTDATA)	(struct MM_Object* pMMObj,
															 const char* rootMovieGUID,
															 const char* name,
															 const unsigned char* data,
															 unsigned long dataSize,
															 unsigned long requestId);

DLLIMPORTFNI typedef short (*PFNC_SI_GETPERSISTENTDATASIZE)	(struct MM_Object* pMMObj,
															 const char* rootMovieGUID,
															 const char* name,
															 unsigned long requestId);

DLLIMPORTFNI typedef short (*PFNC_SI_GETPERSISTENTDATA)	(struct MM_Object* pMMObj,
														 const char* rootMovieGUID,
														 const char* name,
														 unsigned char* data,
														 unsigned long dataSize,
														 unsigned long requestId);

DLLIMPORTFNI typedef short (*PFNC_SI_GETMAXPERSISTENTSTORAGE)	(struct MM_Object* pMMObj,
																 const char* rootMovieGUID,
																 unsigned long* pSize);

DLLIMPORTFNI typedef void (*PFNC_SI_CANCELSHAREDOBJECTREQUEST)	(struct MM_Object* pMMObj,
																 unsigned long requestId);

/************************Local Connection interface************************************/
#if 0
DLLIMPORTFNI typedef void* (*PFNC_SI_CREATESHAREDMEMORY)	(struct MM_Object* pMMObj,
															 unsigned long size);

DLLIMPORTFNI typedef void (*PFNC_SI_DESTROYSHAREDMEMORY)	(struct MM_Object* pMMObj,
															 void* ptr);
#endif

DLLIMPORTFNI typedef unsigned long  (*PFNC_SI_CREATESHAREDMEMORY)   (struct MM_Object* pMMObj,
															         unsigned long size);

DLLIMPORTFNI typedef void           (*PFNC_SI_DESTROYSHAREDMEMORY)  (struct MM_Object* pMMObj,
															         unsigned long nSharedMemoryID);

DLLIMPORTFNI typedef void *         (*PFNC_SI_LOCKSHAREDMEMORY)     (struct MM_Object* pMMObj,
															         unsigned long nSharedMemoryID);

DLLIMPORTFNI typedef void           (*PFNC_SI_UNLOCKSHAREDMEMORY)   (struct MM_Object* pMMObj,
															         unsigned long nSharedMemoryID);

/* Function to transfer a GetURL call to the hosting  application.
 * It is the responsibility of the hosting application to get the
 * html file referenced by "url" and display it in a new page (after
 * closing the current version of the player. The protocols supported
 * include http:, mailto: and tel:.
 */
DLLIMPORTFNI typedef unsigned short  (*PFNC_SI_GETURL)			(struct MM_Object* pMMObj, 
																 struct FI_GetURLCallInfo* pGetURLInfo);

DLLIMPORTFNI typedef short			(*PFNC_SI_OPENURLSTREAM)	(struct MM_Object* pMMObj,
																 struct FI_URLStreamInfo* pStreamInfo,
																 unsigned short streamId);

DLLIMPORTFNI typedef void			(*PFNC_SI_LOADURLSTREAMBUFFER) (struct MM_Object* pMMObj,
																	unsigned short streamId,
																	struct FI_NetworkBufferInfo* pNetBuf);

DLLIMPORTFNI typedef void			(*PFNC_SI_CANCELURLSTREAM)	(struct MM_Object* pMMObj,
																 unsigned short streamId);

DLLIMPORTFNI typedef void			(*PFNC_SI_RELEASEURLSTREAMDATABUFFER)	(struct MM_Object* pMMObj,
																			 unsigned char * dataBuffer);

DLLIMPORTFNI typedef int			(*PFNC_SI_SEEKURLSTREAMFLV)	(struct MM_Object* pMMObj,
																	unsigned short streamId,
																	SZ_Type offset,
																	struct FI_NetworkBufferInfo* pNetBuf);

DLLIMPORTFNI typedef SZ_Type		(*PFNC_SI_FLVGETBYTESLOADED)(struct MM_Object* pMMObj,
																 unsigned short streamId);

/************************File access interface**************************************/

DLLIMPORTFNI typedef unsigned short	(*PFNC_SI_OPENFILE)			(struct MM_Object* pMMObj,
																 const char * fileName,
																 enum FI_FileMode mode,
																 void ** fileHandle);

DLLIMPORTFNI typedef void			(*PFNC_SI_CLOSEFILE)		(struct MM_Object* pMMObj,
																 void * fileHandle);

DLLIMPORTFNI typedef unsigned long	(*PFNC_SI_READFILE)			(struct MM_Object* pMMObj, 
																 void * fileHandle,
																 unsigned char * buffer,
																 unsigned long bufferSize);

DLLIMPORTFNI typedef SZ_Type		(*PFNC_SI_GETFILESIZE)		(struct MM_Object* pMMObj,
																 void * fileHandle);

DLLIMPORTFNI typedef unsigned short	(*PFNC_SI_SEEKFILE) 		(struct MM_Object* pMMObj,
																 void * fileHandle,
																 SZ_Type offset,
																 unsigned short origin);

/************************************************************************************/

/************************************************************************
 *  SI_xxx functions used for key inputs
 */

/* Function to support text entry into input text fields. This
 * function is called by Flash Player when the user wants to enter
 * text into an input text field. The host application is expected
 * to invoke the FEP.
 */
DLLIMPORTFNI typedef void            (*PFNC_SI_GETINPUTTEXT)         (struct MM_Object* pMMObj, 
                                                                      const struct FI_Text* initialText,
                                                                      const struct FI_Text* dialogTitleText,
                                                                      unsigned short maxNumChars,
                                                                      unsigned short textFlags);


/************************************************************************
 *  SI_xxx functions used for text rendering
 */

/* Function to create a device font and return a handle/id that can
 * be later used to make the font the current font to use for drawing
 * text. This function must return 1 if successful or 0 if not.
 */
DLLIMPORTFNI typedef unsigned short  (*PFNC_SI_CREATEFONT)           (struct MM_Object* pMMObj,
                                                                      const struct FI_FontInfo* fontInfo,
                                                                      void** fontId);

/* Function to destroy/release a device font handle.
 * This function must return 1 if successful or 0 if not.
 */
DLLIMPORTFNI typedef unsigned short  (*PFNC_SI_DESTROYFONT)          (struct MM_Object* pMMObj, void* fontId);

/* Function to make the font the current font to be used for
 * drawing device text. This function must return 1 if successful
 * or 0 if not.
 */
DLLIMPORTFNI typedef unsigned short  (*PFNC_SI_SELECTFONT)           (struct MM_Object* pMMObj, void* fontId);

/* Notifies the device that device text drawing will commence.
 */
DLLIMPORTFNI typedef void            (*PFNC_SI_BEGINTEXTDRAW)        (struct MM_Object* pMMObj);

/* Function to draw device text using the current font to the
 * frame buffer.  This function must return 1 if successful or 0
 * if not.
 */
DLLIMPORTFNI typedef unsigned short  (*PFNC_SI_DEVICETEXTOUT)        (struct MM_Object* pMMObj,
                                                                      unsigned short fontSize,
                                                                      const struct FI_Text * text,
                                                                      struct FI_TextInfo* textInfo,
																	  void *bits, int width, int height);

/* Function to break a complex text string into lines.
 * This function must return 1 if successful or 0 if not.
 */
DLLIMPORTFNI typedef unsigned short  (*PFNC_SI_COMPLEXLINEBREAK)      (struct MM_Object* pMMObj,
                                                                       unsigned short fontSize,
                                                                       const struct FI_Text *text,
                                                                       struct FI_DeviceTextInfo *pDeviceTextInfo);

/* Function to notify the device that it has finished drawing
 * device text (for the moment).
 */
DLLIMPORTFNI typedef void            (*PFNC_SI_ENDTEXTDRAW)          (struct MM_Object* pMMObj);

/* Function to access the character information for a device font
 * character.
 */
DLLIMPORTFNI typedef unsigned short  (*PFNC_SI_GETCHARACTERINFO)     (struct MM_Object* pMMObj,
                                                                      unsigned short fontSize,
                                                                      const struct FI_Text * text,
                                                                      struct FI_CharacterInfo* info);

/* Function to access the bitmap for a device font character.
 */
DLLIMPORTFNI typedef unsigned short  (*PFNC_SI_GETBITMAPINFO)        (struct MM_Object* pMMObj,
                                                                      unsigned short fontSize,
                                                                      const struct FI_Text * text,
                                                                      struct FI_BitmapInfo* info);

/* Function to access device font information
 */
DLLIMPORTFNI typedef void            (*PFNC_SI_GETFONTMETRICS)       (struct MM_Object* pMMObj,
                                                                      unsigned short fontSize,
                                                                      short* ascent,
                                                                      short* descent,
                                                                      unsigned short* singleByteSpaceWidth);

/* Function to get the width of a text string in pixels
 */
DLLIMPORTFNI typedef unsigned short  (*PFNC_SI_GETTEXTWIDTH)         (struct MM_Object* pMMObj,
                                                                      unsigned short fontSize,
                                                                      const struct FI_Text * text,
                                                                      unsigned long* width);

/* Function to convert platform encoding to Unicode (UTF-16)
 */
DLLIMPORTFNI typedef SI_Error (*PFNC_SI_CONVERTCSTRINGTOUTF16STRING) (struct MM_Object* pMMObj,
                                                                      const char* srcCString,
                                                                      struct FI_ReturnString* dstUTF16String);

/* Function to convert Unicode (UTF-16) to platform encoding
 */
DLLIMPORTFNI typedef SI_Error (*PFNC_SI_CONVERTUTF16STRINGTOCSTRING) (struct MM_Object* pMMObj,
                                                                      const FI_UTF16Char* srcString,
                                                                      struct FI_ReturnString* dstCString);


/************************************************************************
 *  SI_xxx functions used for error notification purposes
 */

/* Function to notify the hosting application of errors. It is the
 * responsibility of the hosting application to respond to the
 * error and to convey an appropriate error message to the user
 * (if needed).
 */
DLLIMPORTFNI typedef void            (*PFNC_SI_NOTIFYERROR)          (struct MM_Object* pMMObj, short errorCode);


/************************************************************************
 *  SI_xxx functions used for sound control
 */

/* Function to send sound data to device, for devices that need
 * to cache sound data before they can play the sound.
 */
DLLIMPORTFNI typedef short   		(*PFNC_SI_CACHESOUND)			(struct MM_Object* pMMObj,
																	 void* soundBuffer,
                                                                     unsigned long soundBufferSize,
                                                                     unsigned long * soundID);

/* Function to request the device to close the streaming sound device that
 * was opened earlier through a call to SI_OpenStreamSoundDevice.
 */
DLLIMPORTFNI typedef unsigned short  (*PFNC_SI_CLOSESTREAMSOUNDDEVICE)   (struct MM_Object* pMMObj);

/* Function to request that the device create an instance of a sound decompressor.
 * Compressed sound data (MP3, ADPCM, etc.) will be sent to the decompressor by
 * calls to SI_SetupSoundDecompressor and uncompressed data will be requested
 * by calls to SI_DecompressSound.
 */
DLLIMPORTFNI typedef void*           (*PFNC_SI_CREATESOUNDDECOMPRESSOR)  (struct MM_Object* pMMObj,
                                                                          struct FI_StreamSoundInfo* pSoundInfo,
                                                                          unsigned long formatInfo);

/* Function to request uncompressed PCM sound samples from the device. The
 * decompressor instance will have been previously created by a call to
 * SI_CreateSoundDecompressor, and will have been initialized with compressed
 * sound data by a call to SI_SetupSoundDecompressor.
 */
DLLIMPORTFNI typedef long            (*PFNC_SI_DECOMPRESSSOUND)      (struct MM_Object* pMMObj,
                                                                      void* decompressor,
                                                                      void* destBuffer,
                                                                      unsigned long numSamples);

/* Function to request that the device destroy the instance of a sound decompressor.
 * The decompressor will have been previously created by a call to
 * SI_CreateSoundDecompressor.
 */
DLLIMPORTFNI typedef void            (*PFNC_SI_DESTROYSOUNDDECOMPRESSOR) (struct MM_Object* pMMObj, void* decompressor);

/* Function to delete the previously cached sound data.
 */
DLLIMPORTFNI typedef void            (*PFNC_SI_FREECACHEDSOUND)      (struct MM_Object* pMMObj,
                                                                      unsigned long soundID);

/* Function to request the device initialize the streaming sound interface. Buffers
 * of sound data will later be sent to the device by calls to SI_PlayStreamSoundBuffer.
 */
DLLIMPORTFNI typedef unsigned short  (*PFNC_SI_OPENSTREAMSOUNDDEVICE)(struct MM_Object* pMMObj);

/* Function to play the previously cached sound data.
 */
DLLIMPORTFNI typedef unsigned short  (*PFNC_SI_PLAYCACHEDSOUND)      (struct MM_Object* pMMObj,
                                                                      unsigned long soundID,
                                                                      unsigned long count);

/* Function to request the device to start playing the attached
 * sound buffer.
 */
DLLIMPORTFNI typedef short  			(*PFNC_SI_PLAYSOUND)		(struct MM_Object* pMMObj,
                                                                      void* soundBuffer,
                                                                      unsigned long soundBufferSize,
                                                                      unsigned long count);

/* Function used to send buffers of PCM sound data to the device. The Flash Player
 * will request that the device initialize the streaming sound interface by
 * calling SI_OpenStreamSoundDevice before sending buffers.
 */
DLLIMPORTFNI typedef unsigned short  (*PFNC_SI_PLAYSTREAMSOUNDBUFFER)(struct MM_Object* pMMObj, void* data);

/* Function used to initialize the decompressor instance with compressed sound
 * data (MP3, ADPCM, etc.). The decompressor instance will have been previously
 * created by a call to SI_CreateSoundDecompressor.
 */
DLLIMPORTFNI typedef void            (*PFNC_SI_SETUPSOUNDDECOMPRESSOR)   (struct MM_Object* pMMObj,
                                                                          void* decompressor,
                                                                          struct FI_StreamSoundDecompressorInfo* pDecompressorInfo);

/* Function to request the device to stop playing the sound buffer
 * sent earlier through a call to SI_PlaySound.
 */
DLLIMPORTFNI typedef void            (*PFNC_SI_STOPSOUND)            (struct MM_Object* pMMObj);

/* Function to request the device to pause playing the device sound
 * sent earlier through a call to SI_PlaySound.
 */
DLLIMPORTFNI typedef  unsigned short (*PFNC_SI_PAUSESOUND)           (struct MM_Object* pMMObj);

/* Function to request the device to resume playing the device sound
 * sent earlier through a call to SI_ResumeSound.
 */
DLLIMPORTFNI typedef  unsigned short (*PFNC_SI_RESUMESOUND)          (struct MM_Object* pMMObj);


/* Device video functions
 *
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_CREATE_VIDEO)             (struct MM_Object* pMMObj, const struct FI_Rect* location, unsigned long* retVideoID);
DLLIMPORTFNI typedef short           (*PFNC_SI_PLAY_VIDEO)               (struct MM_Object* pMMObj, const struct FI_Video* videoInfo);
DLLIMPORTFNI typedef void            (*PFNC_SI_CLOSE_VIDEO)              (struct MM_Object* pMMObj, unsigned long videoID);
DLLIMPORTFNI typedef short           (*PFNC_SI_STOP_VIDEO)               (struct MM_Object* pMMObj, unsigned long videoID);
DLLIMPORTFNI typedef short           (*PFNC_SI_PAUSE_VIDEO)              (struct MM_Object* pMMObj, unsigned long videoID);
DLLIMPORTFNI typedef short           (*PFNC_SI_RESUME_VIDEO)             (struct MM_Object* pMMObj, unsigned long videoID);
DLLIMPORTFNI typedef short           (*PFNC_SI_GET_VIDEO_METHODS)        (struct MM_Object* pMMObj, struct FI_ReturnString* pMethods);
DLLIMPORTFNI typedef short           (*PFNC_SI_PROCESS_VIDEO_METHOD)     (struct MM_Object* pMMObj, unsigned long videoID, const struct FI_Method* method, struct FI_ReturnString* pRetString);
DLLIMPORTFNI typedef void            (*PFNC_SI_DESTROY_VIDEO)            (struct MM_Object* pMMObj, unsigned long videoID);
DLLIMPORTFNI typedef void            (*PFNC_SI_SET_VIDEO_RECT)           (struct MM_Object* pMMObj, unsigned long videoID, const struct FI_Rect* location);
DLLIMPORTFNI typedef void            (*PFNC_SI_GET_VIDEO_SIZE)           (struct MM_Object* pMMObj, unsigned long videoID, unsigned long* retWidth, unsigned long* retHeight);
DLLIMPORTFNI typedef short           (*PFNC_SI_GET_VIDEO_BITS)           (struct MM_Object* pMMObj, unsigned long videoID, unsigned char** bits);


/* FLV video functions
 *
 */
DLLIMPORTFNI typedef void            (*PFNC_SI_FLVSTATUS)               (struct MM_Object* pMMObj, const char* code);



/************************************************************************
 *  SI_xxx functions used for device control and integration
 */

/* Function to transfer a FSCommand call to the hosting application.
 * It is the responsibility of the hosting application to perform the
 * command. Returns 1 if command supported or zero if not supported.
 */
DLLIMPORTFNI typedef unsigned short  (*PFNC_SI_DOFSCOMMAND)          (struct MM_Object* pMMObj,
                                                                      const struct FI_Text* cmd, 
                                                                      const struct FI_Text* args, 
                                                                      struct FI_PlayerEventInfo* pEventInfo);

/* Used for formatting timestamp that was retrieved earlier using SI_GetUTCTimeStamp
 * TimeStamp is formatted into a string representation in accordance to the
 * current locale and time zone.
 * Return -1 if it is not supported, 0 for failure, 1 for success.
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_FORMATDATE)           (struct MM_Object* pMMObj,
                                                                      const struct FI_LargeInteger* pTimeStamp,
                                                                      unsigned long modifiers,
                                                                      struct FI_ReturnString* pString);

/* Used to format timestamp retrieved earlier using SI_GetUTCTimeStamp
 * into a string representing time of day, in accordance to the
 * current locale and time zone.
 *  Return -1 if it is not supported, 0 for failure, 1 for success.
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_FORMATTIMEOFDAY)      (struct MM_Object* pMMObj,
                                                                      const struct FI_LargeInteger* pTimeStamp,
                                                                      unsigned long modifiers,
                                                                      struct FI_ReturnString* pString);

/* Gets the current battery level of the device. It must be a
 * value between 0 and the value returned from SI_GetMaxBatteryLevel
 * access to the device battery level is supported. It must return -1
 * if access is not supported.
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_GETBATTERYLEVEL)      (struct MM_Object* pMMObj);

/* Stores a NULL terminated text string identifying the device
 * type and manufacturer in the supplied buffer. The buffer passed
 * can contain up to 128 bytes. The function must return 1 if
 * successful. It must return 0 if it failed. It must return -1
 * if this function is not supported.
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_GETDEVICE)            (struct MM_Object* pMMObj,
                                                                      unsigned char* buffer);

/* Returns a bitfield identifying the device capabilities.
 */
DLLIMPORTFNI typedef unsigned short  (*PFNC_SI_GETDEVICECAPABILITIES)(struct MM_Object* pMMObj);

/* Stores a NULL terminated text string representing the unique ID
 * of the device in the supplied buffer. The buffer passed can
 * contain up to 128 bytes. The function must return 1 if
 * successful. It must return 0 if it failed. It must return -1 if
 * the function is not support (i.e. the information is not
 * accessible.)
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_GETDEVICEID)          (struct MM_Object* pMMObj,
                                                                      unsigned char* buffer);

/* Stores a NULL terminated text string identifying the current
 * language of operation on the device in the supplied buffer. The
 * buffer passed can contain up to 128 bytes. The function must
 * return 1 if successful. It must return 0 if it failed. It must
 * return -1 if the function is not support (i.e. the information
 * is not accessible.)
 *
 * The language string can be any 2-letter language code as defined
 * by ISO-639-1 standard, and optionally 2-letter country code as
 * defined by ISO-3166. Some examples are:
 *      "cs"    : Czech
 *      "da"    : Danish
 *      "de"    : German
 *      "en-UK" : UK English
 *      "en-US" : USA English
 *      "es"    : Spanish
 *      "fi"    : Finnish
 *      "fr"    : French
 *      "hu"    : Hungarian
 *      "it"    : Italian
 *      "ja"    : Japanese
 *      "ko"    : Korean
 *      "nl"    : Dutch
 *      "no"    : Norwegian
 *      "po"    : Polish
 *      "pt"    : Portuguese
 *      "ru"    : Russian
 *      "sv"    : Swedish
 *      "tr"    : Turkish
 *      "xu"    : indeterminable
 *      "zh-CN" : simplified Chinese
 *      "zh-TW" : traditional Chinese
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_GETLANGUAGE)              (struct MM_Object* pMMObj,
                                                                          unsigned char* buffer);

/* Gets the maximum value for battery level supported by the
 * device. It must return -1 if the function is not support
 * (i.e. the information is not accessible.)  */
DLLIMPORTFNI typedef short           (*PFNC_SI_GETMAXBATTERYLEVEL)   (struct MM_Object* pMMObj);


/* Returns the maximum volume level.  It must return -1 if the
 * function is not support * (i.e. the information is not
 * accessible.)
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_GETMAXVOLUMELEVEL)    (struct MM_Object* pMMObj);


/* Stores a NULL terminated text string representing the type of
 * phone and OS in the supplied buffer. The buffer passed can
 * contain up to 128 bytes. The function must return 1 if
 * successful. It must return 0 if it failed. It must return -1 if
 * the function is not support (i.e. the information is not
 * accessible.)
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_GETPLATFORM)              (struct MM_Object* pMMObj,
                                                                          unsigned char* buffer);

/* Returns a value indicating the power source. A return value of
 * zero signifies the power source is the battery. A return value
 * of 1 indicates an external power source.  A return value of -1
 * indicates the function is not support (i.e. the information is
 * not accessible.)
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_GETPOWERSOURCE)       (struct MM_Object* pMMObj);


/* Used for timestamping data, format/retrieve date and time.
 * Timestamp must be represented by two values of type unsigned long.
 * Timestamp must allow retrieval of value of time with precision upto
 * 1 millisecond, must be independent of time zone and device.
 * Return 0 for failure, 1 for success.
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_GETUTCTIMESTAMP)		(struct MM_Object* pMMObj, struct FI_LargeInteger* pResult);


/* Used for convert UTC time to local time
 * Timestamp must be represented by two values of type unsigned long.
 * Timestamp must allow retrieval of value of time with precision upto
 * 1 millisecond, must be independent of time zone and device.
 * Return 0 for failure, 1 for success.
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_CONVERTUTCTOLOCALTIME)    (struct MM_Object* pMMObj, 
                                                                          const struct FI_LargeInteger* pUTCTimeStamp, 
                                                                          struct FI_LargeInteger* pLocalTimeStamp);


/* Used for convert local time to UTC time
 * Timestamp must be represented by two values of type unsigned long.
 * Timestamp must allow retrieval of value of time with precision upto
 * 1 millisecond, must be independent of time zone and device.
 * Return 0 for failure, 1 for success.
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_CONVERTLOCALTOUTCTIME)    (struct MM_Object* pMMObj, 
                                                                          const struct FI_LargeInteger*pLocalTimeStamp,
                                                                          struct FI_LargeInteger* pUTCTimeStamp);

/* Returns the current volume level. It must return a value
 * between 0 and the value returned from SI_GetMaxVolumeLevel. A
 * return value of -1 indicates the function is not support
 * (i.e. the information is not accessible.)
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_GETVOLUMELEVEL)       (struct MM_Object* pMMObj);

/* Launch an application specified by the arg list. This function
 * must return 1 if the application was successfully launched.
 * It must return 0 if it failed to launch the application. A
 * return value of -1 indicates the function is not support
 * (i.e. the information is not accessible.)
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_LAUNCH)               (struct MM_Object* pMMObj,
                                                                      unsigned short argc,
                                                                      const struct FI_Text** argv,
                                                                      struct FI_PlayerEventInfo* pEventInfo,
                                                                      short isEmbedded);

/* Tell the device to shut down the player (asynchronously).
 * This function must return 1 if the application will shut down.
 * It must return 0 if it will not shut down. A return value of
 * -1 indicates the function is not supported (i.e. the
 * application can not exit.) */
DLLIMPORTFNI typedef short           (*PFNC_SI_QUITFLASHLITE)        (struct MM_Object* pMMObj, struct FI_PlayerEventInfo* pEventInfo);

/* Resets softkeys to defaults. This function must return -1
 * to indicate that the function is not supported, 0 to indicate
 * failure, or 1 to indicate success.
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_RESETSOFTKEYS)        (struct MM_Object* pMMObj);

/*
 * Returns the number of softkeys in the device.
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_GETNUMBEROFSOFTKEYS) (struct MM_Object* pMMObj);


/* Gets the current location information for the soft keys. will return
 *	-1: Function is not supported
 * 	 0: Softkeys on top
 * 	 1: Sofkeys on left
 * 	 2: Softkeys on bottom
 * 	 3: Softkeys on right
 */
DLLIMPORTFNI typedef short			(*PFNC_SI_GETSOFTKEYLOCATION) (struct MM_Object* pMMObj);

/* Asks the device to move between normal and full-screen
 * mode. The device needs to delete the old frame buffer, create a
 * new one, call FI_SetFrameBuffer and then FI_SetDisplayRect. It
 * should return 1 on success, 0 on failure and -1 if the function
 * is not supported.
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_SETFULLSCREEN)        (struct MM_Object* pMMObj,unsigned short onOrOff);

/* Sets the softkeys (if possible). It must return 1 if success,
 * return 0 if failed or return -1 if the function is not supported.
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_SETSOFTKEYS)          (struct MM_Object* pMMObj,
                                                                      unsigned short argc,
                                                                      const struct FI_Text** argv);

/* This function starts the phone vibrating on and off. A return
 * value of 1 indicates that the vibration was successfully
 * started. A return value of 0 indicates failure to start the
 * vibration. A return value of -1 indicates the function is not
 * supported.
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_STARTVIBRATE)         (struct MM_Object* pMMObj,
																	  unsigned short timeOn,
                                                                      unsigned short timeOff,
                                                                      unsigned short repeatCount);

/* This stops the phone from vibrating. This function must return -1
 * to indicate that the function is not supported, 0 to indicate
 * failure, or 1 to indicate success.
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_STOPVIBRATE)          (struct MM_Object* pMMObj);




/*
 * Platform image decoding functions
 */
DLLIMPORTFNI typedef short           (*PFNC_SI_GETIMAGEINFO)         (struct MM_Object* pMMObj,
																	  unsigned long pImageId,
                                                                      const struct FI_ImageData* pImageData);

DLLIMPORTFNI typedef short			 (*PFNC_SI_CANCELIMAGEREQUEST)	(struct MM_Object* pMMObj,
																	 unsigned long pImageId);

DLLIMPORTFNI typedef short           (*PFNC_SI_DECODEJPEGIMAGE)      (struct MM_Object* pMMObj,
																	  unsigned long pImageId,
																	  const struct FI_ImageData* pImageData);

/*
 * Screen fscommand- and System.capabilities support functions
 */
DLLIMPORTFNI typedef short       (*PFNC_SI_EXTENDBACKLIGHTDURATION)  (struct MM_Object* pMMObj,
                                                                      unsigned short duration);

DLLIMPORTFNI typedef short       (*PFNC_SI_GETSCREENINFO)            (struct MM_Object* pMMObj,
                                                                      unsigned short* x,
                                                                      unsigned short* y,
                                                                      unsigned short* rotation);

/*
 * Network fscommand support functions
 */
DLLIMPORTFNI typedef short       (*PFNC_SI_GETNETWORKINFO)           (struct MM_Object* pMMObj,
                                                                      unsigned short infoType,
                                                                      long* retVal);

DLLIMPORTFNI typedef short       (*PFNC_SI_GETNETWORKINFOSTRING)     (struct MM_Object* pMMObj,
                                                                      unsigned short infoType,
                                                                      struct FI_ReturnString* retVal);

DLLIMPORTFNI typedef short		(*PFNC_SI_GETCALLERINFO)			(struct MM_Object* pMMObj,
																	 struct FI_ReturnString* name,
																	 struct FI_ReturnString* number);

/*
 * XMLSocket specific functions
 */
DLLIMPORTFNI typedef short		(*PFNC_SI_OPENSOCKET)				(struct MM_Object* pMMObj,
																	 struct FI_SocketConnectionInfo* socketInfo,
																	 const struct FI_PlayerEventInfo*  pEventInfo);

DLLIMPORTFNI typedef short		(*PFNC_SI_CLOSESOCKET)				(struct MM_Object* pMMObj,
																	 unsigned long socketId);

DLLIMPORTFNI typedef short		(*PFNC_SI_SOCKETSEND)				(struct MM_Object* pMMObj,
																	 unsigned long socketId, 
																	 struct FI_NetworkBufferInfo* pDataBuffer);


/*
 * SVG specific functions
 */

/*
 * Function to report errors specific to SVG content
 */
DLLIMPORTFNI typedef void            (*PFNC_SI_NOTIFYSVGCONTENTERROR) (struct MM_Object* pMMObj,
                                                                       short svgErrorCode,
                                                                       const char * value,
                                                                       const char * auxValue );


/*
 * Inline text entry functions
 */
DLLIMPORTFNI typedef void			(*PFNC_SI_ENABLEIME) (struct MM_Object* pMMObj, struct FI_TextField* textField);
DLLIMPORTFNI typedef void			(*PFNC_SI_DISABLEIME) (struct MM_Object* pMMObj);
DLLIMPORTFNI typedef void			(*PFNC_SI_DRAWFULLSCREENINPUTMODEINDICATOR) (struct MM_Object* pMMObj, struct FI_Rect* editingTextFieldBounds, struct FI_Rect* indicatorBounds);

/*
 *
 */
DLLIMPORTFNI typedef short			(*PFNC_SI_UPDATEBACKGROUNDREGION) (struct MM_Object* pMMObj, 
																	   struct FI_Rect *rects,
																	   unsigned short numRects);

 /*
 * Begin Native video specific functions
 */
#if defined(FEATURE_VIDEO_DLL) || defined(FEATURE_H264_DLL)
struct IVideoDecompressorInstance;

DLLIMPORTFNI typedef struct IVideoDecompressorInstance * (*PFNC_SI_CREATE_DECOMPRESSOR) ( struct MM_Object * player, void * iAllocator, unsigned long codecType );
DLLIMPORTFNI typedef void (*PFNC_SI_DESTROY_DECOMPRESSOR) (struct MM_Object * player, struct IVideoDecompressorInstance * decompressor);
#endif //FEATURE_VIDEO_DLL

/*
 * End native video specific functions
 */

/*
 * External API functions
 */
DLLIMPORTFNI typedef int			(*PFNC_SI_EVALJS) (struct MM_Object* pMMObj, 
													   const char* expression);

DLLIMPORTFNI typedef int			(*PFNC_SI_DOFLASHCALL) (struct MM_Object *pMMObj, 
															const char* request);

DLLIMPORTFNI typedef void *		(*PFNC_SI_GETDOMOBJECTID) (struct MM_Object *pMMObj, 
														   struct FI_AllocatorWrapper * allocWrapper);

/*
 * Extension functions
 */
struct PluginInfo;
struct ExtensionInterface;
DLLIMPORTFNI typedef struct PluginInfo* (*PFNC_SI_GETPLUGININFO) (struct MM_Object *pMMObj, struct ExtensionInterface* pEI);

/*
 * OpenGL-ES functions
 */
DLLIMPORTFNI typedef unsigned char (*PFNC_SI_GLISTEXTURE) (struct MM_Object* pMMObj, unsigned int texture);

DLLIMPORTFNI typedef unsigned int (*PFNC_SI_GLGETERROR) (struct MM_Object* pMMObj);

DLLIMPORTFNI typedef void (*PFNC_SI_GLACTIVETEXTURE) (struct MM_Object* pMMObj, unsigned int texture);

DLLIMPORTFNI typedef void (*PFNC_SI_GLALPHAFUNCX) (struct MM_Object* pMMObj, unsigned int func, int ref);

DLLIMPORTFNI typedef void (*PFNC_SI_GLBINDBUFFER) (struct MM_Object* pMMObj, unsigned int target, unsigned int buffer);

DLLIMPORTFNI typedef void (*PFNC_SI_GLBINDTEXTURE) (struct MM_Object* pMMObj, unsigned int target, unsigned int texture);

DLLIMPORTFNI typedef void (*PFNC_SI_GLBLENDFUNC) (struct MM_Object* pMMObj, unsigned int sfactor, unsigned int dfactor);

DLLIMPORTFNI typedef void (*PFNC_SI_GLBUFFERDATA) (struct MM_Object* pMMObj, unsigned int target, int size, const void* data, unsigned int usage);

DLLIMPORTFNI typedef void (*PFNC_SI_GLCLEAR) (struct MM_Object* pMMObj, unsigned int mask);

DLLIMPORTFNI typedef void (*PFNC_SI_GLCLEARCOLORX) (struct MM_Object* pMMObj, int red, int green, int blue, int alpha);

DLLIMPORTFNI typedef void (*PFNC_SI_GLCLEARDEPTHX) (struct MM_Object* pMMObj, int depth);

DLLIMPORTFNI typedef void (*PFNC_SI_GLCLEARSTENCIL) (struct MM_Object* pMMObj, int s);

DLLIMPORTFNI typedef void (*PFNC_SI_GLCLIENTACTIVETEXTURE) (struct MM_Object* pMMObj, unsigned int texture);

DLLIMPORTFNI typedef void (*PFNC_SI_GLCLIPPLANEX) (struct MM_Object* pMMObj, unsigned int plane, const int* data );

DLLIMPORTFNI typedef void (*PFNC_SI_GLCOLOR4X) (struct MM_Object* pMMObj, int red, int green, int blue, int alpha);

DLLIMPORTFNI typedef void (*PFNC_SI_GLCOLORMASK) (struct MM_Object* pMMObj, unsigned char red, unsigned char green, unsigned char blue, unsigned char alpha);

DLLIMPORTFNI typedef void (*PFNC_SI_GLCULLFACE) (struct MM_Object* pMMObj, unsigned int mode);

DLLIMPORTFNI typedef void (*PFNC_SI_GLDELETETEXTURES) (struct MM_Object* pMMObj, int n, const unsigned int* textures);

DLLIMPORTFNI typedef void (*PFNC_SI_GLDEPTHFUNC) (struct MM_Object* pMMObj, unsigned int func);

DLLIMPORTFNI typedef void (*PFNC_SI_GLDEPTHMASK) (struct MM_Object* pMMObj, unsigned char flag);

DLLIMPORTFNI typedef void (*PFNC_SI_GLDISABLE) (struct MM_Object* pMMObj, unsigned int cap);

DLLIMPORTFNI typedef void (*PFNC_SI_GLDISABLECLIENTSTATE) (struct MM_Object* pMMObj, unsigned int array);

DLLIMPORTFNI typedef void (*PFNC_SI_GLDRAWARRAYS) (struct MM_Object* pMMObj, unsigned int mode, int first, int count);

DLLIMPORTFNI typedef void (*PFNC_SI_GLDRAWELEMENTS) (struct MM_Object* pMMObj, unsigned int mode, int count, unsigned int type, const void* indices);

DLLIMPORTFNI typedef void (*PFNC_SI_GLENABLE) (struct MM_Object* pMMObj, unsigned int cap);

DLLIMPORTFNI typedef void (*PFNC_SI_GLENABLECLIENTSTATE) (struct MM_Object* pMMObj, unsigned int array);

DLLIMPORTFNI typedef void (*PFNC_SI_GLFINISH) (struct MM_Object* pMMObj);

DLLIMPORTFNI typedef void (*PFNC_SI_GLGENTEXTURES) (struct MM_Object* pMMObj, int n, unsigned int* textures);

DLLIMPORTFNI typedef void (*PFNC_SI_GLGETINTEGERV) (struct MM_Object* pMMObj, unsigned int pname, int* params);

DLLIMPORTFNI typedef void (*PFNC_SI_GLHINT) (struct MM_Object* pMMObj, unsigned int target, unsigned int mode);

DLLIMPORTFNI typedef void (*PFNC_SI_GLLINEWIDTHX) (struct MM_Object* pMMObj, int width);

DLLIMPORTFNI typedef void (*PFNC_SI_GLLOADIDENTITY) (struct MM_Object* pMMObj);

DLLIMPORTFNI typedef void (*PFNC_SI_GLLOADMATRIXX) (struct MM_Object* pMMObj, const int* m);

DLLIMPORTFNI typedef void (*PFNC_SI_GLLOGICOP) (struct MM_Object* pMMObj, unsigned int opcode);

DLLIMPORTFNI typedef void (*PFNC_SI_GLMATRIXMODE) (struct MM_Object* pMMObj, unsigned int mode);

DLLIMPORTFNI typedef void (*PFNC_SI_GLMULTMATRIXX) (struct MM_Object* pMMObj, const int* m);

DLLIMPORTFNI typedef void (*PFNC_SI_GLORTHOX) (struct MM_Object* pMMObj, int left, int right, int bottom, int top, int zNear, int zFar);

DLLIMPORTFNI typedef void (*PFNC_SI_GLPIXELSTOREI) (struct MM_Object* pMMObj, unsigned int pname, int param);

DLLIMPORTFNI typedef void (*PFNC_SI_GLPOLYGONOFFSETX) (struct MM_Object* pMMObj, int factor, int units);

DLLIMPORTFNI typedef void (*PFNC_SI_GLPOPMATRIX) (struct MM_Object* pMMObj);

DLLIMPORTFNI typedef void (*PFNC_SI_GLPUSHMATRIX) (struct MM_Object* pMMObj);

DLLIMPORTFNI typedef void (*PFNC_SI_GLSCALEX) (struct MM_Object* pMMObj, int x, int y, int z);

DLLIMPORTFNI typedef void (*PFNC_SI_GLSCISSOR) (struct MM_Object* pMMObj, int x, int y, int width, int height);

DLLIMPORTFNI typedef void (*PFNC_SI_GLSHADEMODEL) (struct MM_Object* pMMObj, unsigned int mode);

DLLIMPORTFNI typedef void (*PFNC_SI_GLSTENCILFUNC) (struct MM_Object* pMMObj, unsigned int func, int ref, unsigned int mask);

DLLIMPORTFNI typedef void (*PFNC_SI_GLSTENCILOP) (struct MM_Object* pMMObj, unsigned int fail, unsigned int zfail, unsigned int zpass);

DLLIMPORTFNI typedef void (*PFNC_SI_GLTEXCOORDPOINTER) (struct MM_Object* pMMObj, int size, unsigned int type, int stride, const void* pointer);

DLLIMPORTFNI typedef void (*PFNC_SI_GLTEXENVX) (struct MM_Object* pMMObj, unsigned int target, unsigned int pname, int param);

DLLIMPORTFNI typedef void (*PFNC_SI_GLTEXENVXV) (struct MM_Object* pMMObj, unsigned int target, unsigned int pname, const int* params);

DLLIMPORTFNI typedef void (*PFNC_SI_GLTEXIMAGE2D) (struct MM_Object* pMMObj, unsigned int target, int level, int internalformat, int width, int height, int border, unsigned int format, unsigned int type, const void* pixels);

DLLIMPORTFNI typedef void (*PFNC_SI_GLTEXPARAMETERX) (struct MM_Object* pMMObj, unsigned int target, unsigned int pname, int param);

DLLIMPORTFNI typedef void (*PFNC_SI_GLTEXSUBIMAGE2D) (struct MM_Object* pMMObj, unsigned int target, int level, int xoffset, int yoffset, int width, int height, unsigned int format, unsigned int type, const void* pixels);

DLLIMPORTFNI typedef void (*PFNC_SI_GLTRANSLATEX) (struct MM_Object* pMMObj, int x, int y, int z);

DLLIMPORTFNI typedef void (*PFNC_SI_GLVERTEXPOINTER) (struct MM_Object* pMMObj, int size, unsigned int type, int stride, const void* pointer);

DLLIMPORTFNI typedef void (*PFNC_SI_GLVIEWPORT) (struct MM_Object* pMMObj, int x, int y, int width, int height);

/*
 * OpenVG functions
 */

DLLIMPORTFNI typedef void (*PFNC_SI_VGAPPENDPATH)(void* dstPath, void* srcPath);
DLLIMPORTFNI typedef void (*PFNC_SI_VGAPPENDPATHDATA)(void* dstPath, int numSegments, const unsigned char * pathSegments, const void * pathData);
DLLIMPORTFNI typedef void (*PFNC_SI_VGCLEAR)(int x, int y, int width, int height);
DLLIMPORTFNI typedef void (*PFNC_SI_VGCLEARIMAGE)(void* image, int x, int y, int width, int height);
DLLIMPORTFNI typedef void (*PFNC_SI_VGCLEARPATH)(void* path, unsigned int capabilities);
DLLIMPORTFNI typedef void* (*PFNC_SI_VGCREATEIMAGE)(int format, int width, int height, unsigned int allowedQuality);
DLLIMPORTFNI typedef void* (*PFNC_SI_VGCREATEMASKLAYER)(int width, int height);
DLLIMPORTFNI typedef void* (*PFNC_SI_VGCREATEPAINT)(void);
DLLIMPORTFNI typedef void* (*PFNC_SI_VGCREATEPATH)(int pathFormat, unsigned int datatype, float scale, float bias, int segmentCapacityHint, int coordCapacityHint, unsigned int capabilities);
DLLIMPORTFNI typedef void (*PFNC_SI_VGCOPYMASK)(void* maskLayer, int sx, int sy, int dx, int dy, int width, int height);
DLLIMPORTFNI typedef void (*PFNC_SI_VGDESTROYIMAGE)(void* image);
DLLIMPORTFNI typedef void (*PFNC_SI_VGDESTROYMASKLAYER)(void* maskLayer);
DLLIMPORTFNI typedef void (*PFNC_SI_VGDESTROYPAINT)(void* paint);
DLLIMPORTFNI typedef void (*PFNC_SI_VGDESTROYPATH)(void* path);
DLLIMPORTFNI typedef void (*PFNC_SI_VGDRAWIMAGE)(void* image);
DLLIMPORTFNI typedef void (*PFNC_SI_VGDRAWPATH)(void* path, unsigned int paintModes);
DLLIMPORTFNI typedef void (*PFNC_SI_VGFINISH)(void);
DLLIMPORTFNI typedef void (*PFNC_SI_VGFLUSH)(void);
DLLIMPORTFNI typedef unsigned int (*PFNC_SI_VGGETERROR)(void);
DLLIMPORTFNI typedef void (*PFNC_SI_VGGETMATRIX)(float * m);
DLLIMPORTFNI typedef int (*PFNC_SI_VGGETPARAMETERI)(void* object, int paramType);
DLLIMPORTFNI typedef int (*PFNC_SI_VGGETI)(int type);
DLLIMPORTFNI typedef void (*PFNC_SI_VGIMAGESUBDATA)(void* image, const void * data, int dataStride, unsigned int dataFormat, int x, int y, int width, int height);
DLLIMPORTFNI typedef int (*PFNC_SI_VGINTERPOLATEPATH)(void* dstPath, void* startPath, void* endPath, float amount);
DLLIMPORTFNI typedef void (*PFNC_SI_VGLOADIDENTITY)(void);
DLLIMPORTFNI typedef void (*PFNC_SI_VGLOADMATRIX)(const float * m);
DLLIMPORTFNI typedef void (*PFNC_SI_VGMASK)(void* mask,  unsigned int operation, int x, int y, int width, int height);
DLLIMPORTFNI typedef void (*PFNC_SI_VGMODIFYPATHCOORDS)(void* dstPath, int startIndex, int numSegments, const void * pathData);
DLLIMPORTFNI typedef void (*PFNC_SI_VGMULTMATRIX)(const float * m);
DLLIMPORTFNI typedef void (*PFNC_SI_VGPAINTPATTERN)(void* paint, void* pattern);
DLLIMPORTFNI typedef void (*PFNC_SI_VGPATHBOUNDS)(void* path, float * minX, float * minY, float * width, float * height);
DLLIMPORTFNI typedef void (*PFNC_SI_VGRENDERTOMASK)(void* path, unsigned int paintModes, unsigned int operation);
DLLIMPORTFNI typedef void (*PFNC_SI_VGSCALE)(float sx, float sy);
DLLIMPORTFNI typedef void (*PFNC_SI_VGSETCOLOR)(void* paint, unsigned int rgba);
DLLIMPORTFNI typedef void (*PFNC_SI_VGSETPAINT)(void* paint, unsigned int paintModes);
DLLIMPORTFNI typedef void (*PFNC_SI_VGSETPARAMETERFV)(void* object, int paramType, int count, const float * values);
DLLIMPORTFNI typedef void (*PFNC_SI_VGSETPARAMETERI)(void* object, int paramType, int value);
DLLIMPORTFNI typedef void (*PFNC_SI_VGSETF)(int type, float value);
DLLIMPORTFNI typedef void (*PFNC_SI_VGSETFV)(int type, int count, const float * values);
DLLIMPORTFNI typedef void (*PFNC_SI_VGSETI)(int type, int value);
DLLIMPORTFNI typedef void (*PFNC_SI_VGSETIV)(int type, int count, const int * values);
DLLIMPORTFNI typedef void (*PFNC_SI_VGTRANSFORMPATH)(void* dstPath, void* srcPath);
DLLIMPORTFNI typedef void (*PFNC_SI_VGTRANSLATE)(float tx, float ty);


/*
* List of Security related SI Functions.
*/
DLLIMPORTFNI typedef void (*PFNC_SI_SHOWLOCALSECURITYDIALOG) (struct MM_Object* pMMObj, const char* pPathToDisplay, const char* pAccessedDomain);

/*
 * 2D Hardware Blitter Acceleration  functions
 */
// Return 0 if the Accelerator can perform the operation.
typedef short (*PFNC_SI_BLIT) (struct MM_Object* pMMObj, struct FI_BlitOp *blit);
// Return 0 if the Accelerator can perform the operation.
typedef short (*PFNC_SI_FILL)  (struct MM_Object* pMMObj, struct FI_FillOp *blit);
// Return 0 if the Accelerator can perform the operation. If this
// function returns non-zero then the blit operations revert back to
// the default CPU-based algorithm.
typedef void (*PFNC_SI_FLUSHBLIT) (struct MM_Object *pMMObj);
// Allocate RAM for a Bitmap that can be accessed by the Blitter Accelerator.
typedef void *(*PFNC_SI_BMMALLOC)(struct MM_Object *pMMObj, enum FI_BitmapPixelFormat format, int width, int height, long *rowBytesToSet, int useVideoMemory);
// Free the RAM allocated by PFNC_SI_BMMALLOC
typedef void (*PFNC_SI_BMFREE)(struct MM_Object *pMMObj, void *handle);
// Mark the bitmap identified by the handle as unmovable and return a pointer to the bitmap memory.
typedef void* (*PFNC_SI_BMLOCK)(struct MM_Object *pMMObj, void *handle);
// Mark the bitmap identified by the handle as movable, the pointer returned by SI_BMLOCK is invalid now.
typedef void (*PFNC_SI_BMUNLOCK)(struct MM_Object *pMMObj, void *handle);
// Return the color transform capabilties of the HW
typedef short (*PFNC_SI_BLITCAPS)(struct MM_Object *pMMObj);

/*
 * RTMP digest operation functions
 */
typedef void          (*PFNC_SI_RTMP_CALCHMAC)(struct MM_Object * pMMObj, const unsigned char * pData, unsigned long nDataLen, const unsigned char * pKey, unsigned long nKeyLen, unsigned char * pDigestToSet);
typedef void          (*PFNC_SI_RTMP_CALCPLAYERHMAC)(struct MM_Object * pMMObj, unsigned char hashVersion, const unsigned char * pData, unsigned long nDataLen, unsigned char * pDigestToSet);
typedef unsigned long (*PFNC_SI_RTMP_ADDPLAYERHMAC1)(struct MM_Object * pMMObj, const unsigned char * pKey, unsigned long nKeyLen, unsigned char * pMessage);
typedef void          (*PFNC_SI_RTMP_ADDPLAYERHMAC3)(struct MM_Object * pMMObj, const unsigned char * pData, unsigned char * pMessage, unsigned char nRTMPEVersion);
typedef unsigned long (*PFNC_SI_RTMP_VERIFYSERVERHMAC)(struct MM_Object * pMMObj, const unsigned char * pMessage, unsigned char * pDigestToSet);
typedef bool          (*PFNC_SI_RTMP_VERIFYSERVERHMAC3)(struct MM_Object * pMMObj, const unsigned char * pData, const unsigned char * pMessage, unsigned char nRTMPEVersion);


/****************************************************************************\
    SI_xxx sub-structure. These structures will be used in SI_Fncs structure
\****************************************************************************/

/*
 * Structure containing SI_xxx inline text entry functions
 */
struct SI_InlineTextEntryFncs
{
	PFNC_SI_ENABLEIME				pFncSI_EnableIME;
	PFNC_SI_DISABLEIME				pFncSI_DisableIME;
	PFNC_SI_DRAWFULLSCREENINPUTMODEINDICATOR pFncSI_DrawFullScreenInputModeIndicator;

	NO_OPERATOR_NEW_DELETE
};

/*
 * Structure containing SI_xxx image decode functions
 */
struct SI_ImageFncs
{
    PFNC_SI_GETIMAGEINFO            pFncSI_GetImageInfo;
	PFNC_SI_CANCELIMAGEREQUEST		pFncSI_CancelImageRequest;
	PFNC_SI_DECODEJPEGIMAGE			pFncSI_DecodeJpegImage;
	NO_OPERATOR_NEW_DELETE
};

/*
 * Structure containing SI_xxx SVG functions
 */
struct SI_SVGFncs
{
    PFNC_SI_NOTIFYSVGCONTENTERROR           pFncSI_NotifySVGContentError;
	NO_OPERATOR_NEW_DELETE
};


/*
 *  Structure containing SI_xxx functions used for FlashLite player control
 */
struct SI_FlashPlayerControlFncs
{
    PFNC_SI_FREE                                    pFncSI_Free;
    PFNC_SI_GETMAXAVAILABLEPLAYERMEMORY             pFncSI_GetMaxAvailablePlayerMemory;
    PFNC_SI_MALLOC                                  pFncSI_Malloc;
    PFNC_SI_UPDATEFRAMEINTERVAL                     pFncSI_UpdateFrameInterval;
	PFNC_SI_UPDATEBACKGROUNDREGION					pFncSI_UpdateBackgroundRegion;
	NO_OPERATOR_NEW_DELETE
};

/*
 *  Structure containing SI_xxx functions used for debugging
 */
struct SI_DebugFncs
{
    PFNC_SI_DEBUG                                   pFncSI_Debug;
	NO_OPERATOR_NEW_DELETE
};

/*
 *  Structure containing SI_xxx functions used for frame-buffer access
 */
struct SI_FrameBufferFncs
{
    PFNC_SI_GETLOCKEDFRAMEBUFFER                    pFncSI_GetLockedFrameBuffer;
    PFNC_SI_UNLOCKFRAMEBUFFER                       pFncSI_UnlockFrameBuffer;
// ACHW
    PFNC_SI_GETHARDWAREINTERFACES                   pFncSI_GetHardwareInterfaces;
    PFNC_SI_EGLGETDISPLAY                           pFncSI_eglGetDisplay;
    PFNC_SI_EGLINITIALIZE                           pFncSI_eglInitialize;
    PFNC_SI_EGLTERMINATE                            pFncSI_eglTerminate;
    PFNC_SI_EGLCREATECONTEXT                        pFncSI_eglCreateContext;
    PFNC_SI_EGLCHOOSECONFIG                         pFncSI_eglChooseConfig;
    PFNC_SI_EGLCREATEPBUFFERSURFACE                 pFncSI_eglCreatePbufferSurface;
    PFNC_SI_EGLCREATENATIVEPIXMAPSURFACE            pFncSI_eglCreateNativePixmapSurface;
    PFNC_SI_EGLCREATEPBUFFERFROMCLIENTBUFFER        pFncSI_eglCreatePbufferFromClientBuffer;
    PFNC_SI_EGLMAKECURRENT                          pFncSI_eglMakeCurrent;
    PFNC_SI_EGLDESTROYCONTEXT                       pFncSI_eglDestroyContext;
    PFNC_SI_EGLDESTROYSURFACE                       pFncSI_eglDestroySurface;
    PFNC_SI_EGLGETCONFIGATTRIB                      pFncSI_eglGetConfigAttrib;
    PFNC_SI_EGLCOPYSURFACETOFRAMEBUFFER             pFncSI_eglCopySurfaceToFrameBuffer;
// ACHW
	NO_OPERATOR_NEW_DELETE
};

/*
 *  Structure containing SI_xxx functions used for Data access
 */
struct SI_DataAccessFncs
{
    PFNC_SI_SETPERSISTENTDATA						pFncSI_SetPersistentData;
	PFNC_SI_GETPERSISTENTDATASIZE					pFncSI_GetPersistentDataSize;
    PFNC_SI_GETPERSISTENTDATA						pFncSI_GetPersistentData;
    PFNC_SI_GETMAXPERSISTENTSTORAGE					pFncSI_GetMaxPersistentStorage;
    PFNC_SI_CANCELSHAREDOBJECTREQUEST				pFncSI_CancelSharedObjectRequest;
	PFNC_SI_CREATESHAREDMEMORY						pFncSI_CreateSharedMemory;
    PFNC_SI_DESTROYSHAREDMEMORY						pFncSI_DestroySharedMemory;
	PFNC_SI_LOCKSHAREDMEMORY						pFncSI_LockSharedMemory;
    PFNC_SI_UNLOCKSHAREDMEMORY						pFncSI_UnlockSharedMemory;
	NO_OPERATOR_NEW_DELETE
};

/***********Structure containing SI_xxx functions used for File access******/

struct SI_FileAccessFncs
{
	PFNC_SI_OPENFILE								pFncSI_OpenFile;
	PFNC_SI_CLOSEFILE								pFncSI_CloseFile;
	PFNC_SI_READFILE								pFncSI_ReadFile;
	PFNC_SI_GETFILESIZE								pFncSI_GetFileSize;
	PFNC_SI_SEEKFILE								pFncSI_SeekFile;
	NO_OPERATOR_NEW_DELETE
};

/****************************************************************************/

/*
 *  Structure containing SI_xxx functions used for URL access
 */
struct SI_URLAccessFncs
{
    PFNC_SI_GETURL                                  pFncSI_GetURL;
    PFNC_SI_OPENURLSTREAM                           pFncSI_OpenURLStream;
    PFNC_SI_LOADURLSTREAMBUFFER                     pFncSI_LoadURLStreamBuffer;
    PFNC_SI_CANCELURLSTREAM                         pFncSI_CancelURLStream;
    PFNC_SI_RELEASEURLSTREAMDATABUFFER		    	pFncSI_ReleaseURLStreamDataBuffer;
    PFNC_SI_SEEKURLSTREAMFLV	                    pFncSI_SeekURLStreamFLV;
	PFNC_SI_FLVGETBYTESLOADED						pFncSI_FLVGetBytesLoaded;
    NO_OPERATOR_NEW_DELETE
};




/*
 *  Structure containing SI_xxx functions used for key input
 */
struct SI_KeyInputFncs
{
    PFNC_SI_GETINPUTTEXT                            pFncSI_GetInputText;
	NO_OPERATOR_NEW_DELETE
};

/*
 *  Structure containing SI_xxx functions used for character encoding
 */
struct SI_CharacterEncodingFncs
{
    PFNC_SI_CONVERTCSTRINGTOUTF16STRING     pFncSI_ConvertCStringToUTF16String;
    PFNC_SI_CONVERTUTF16STRINGTOCSTRING     pFncSI_ConvertUTF16StringToCString;
	NO_OPERATOR_NEW_DELETE
};

/*
 *  Structure containing SI_xxx functions used for font rendering
 */
struct SI_TextRenderingFncs
{
    PFNC_SI_BEGINTEXTDRAW                           pFncSI_BeginTextDraw;
    PFNC_SI_CREATEFONT                              pFncSI_CreateFont;
    PFNC_SI_DEVICETEXTOUT                           pFncSI_DeviceTextOut;
    PFNC_SI_COMPLEXLINEBREAK                        pFncSI_ComplexLineBreak;
    PFNC_SI_DESTROYFONT                             pFncSI_DestroyFont;
    PFNC_SI_ENDTEXTDRAW                             pFncSI_EndTextDraw;
    PFNC_SI_GETBITMAPINFO                           pFncSI_GetBitmapInfo;
    PFNC_SI_GETCHARACTERINFO                        pFncSI_GetCharacterInfo;
    PFNC_SI_GETFONTMETRICS                          pFncSI_GetFontMetrics;
    PFNC_SI_GETTEXTWIDTH                            pFncSI_GetTextWidth;
    PFNC_SI_SELECTFONT                              pFncSI_SelectFont;

    PFNC_SI_GETLOCKEDTEXTBUFFER                    pFncSI_GetLockedTextBuffer;
    PFNC_SI_UNLOCKTEXTBUFFER                       pFncSI_UnlockTextBuffer;
	NO_OPERATOR_NEW_DELETE
};


/*
 *  Structure containing SI_xxx functions used for FlashLite error notification
 */
struct SI_ErrorNotifyFncs
{
    PFNC_SI_NOTIFYERROR                             pFncSI_NotifyFlashLiteError;
	NO_OPERATOR_NEW_DELETE
};

/*
 *  Structure containing SI_xxx functions used for sound control
 */
struct SI_SoundControlFncs
{
    PFNC_SI_CACHESOUND                              pFncSI_CacheSound;
    PFNC_SI_CREATESOUNDDECOMPRESSOR                 pFncSI_CreateSoundDecompressor;
    PFNC_SI_CLOSESTREAMSOUNDDEVICE                  pFncSI_CloseStreamSoundDevice;
    PFNC_SI_DECOMPRESSSOUND                         pFncSI_DecompressSound;
    PFNC_SI_DESTROYSOUNDDECOMPRESSOR                pFncSI_DestroySoundDecompressor;
    PFNC_SI_FREECACHEDSOUND                         pFncSI_FreeCachedSound;
    PFNC_SI_OPENSTREAMSOUNDDEVICE                   pFncSI_OpenStreamSoundDevice;
    PFNC_SI_PLAYSOUND                               pFncSI_PlaySound;
    PFNC_SI_PLAYCACHEDSOUND                         pFncSI_PlayCachedSound;
    PFNC_SI_PLAYSTREAMSOUNDBUFFER                   pFncSI_PlayStreamSoundBuffer;
    PFNC_SI_SETUPSOUNDDECOMPRESSOR                  pFncSI_SetupSoundDecompressor;
    PFNC_SI_STOPSOUND                               pFncSI_StopSound;
    PFNC_SI_PAUSESOUND                              pFncSI_PauseSound;
    PFNC_SI_RESUMESOUND                             pFncSI_ResumeSound;
	NO_OPERATOR_NEW_DELETE
};

/*
 *  Structure containing SI_xxx functions used for Video
 */

struct SI_VideoFncs
{
    PFNC_SI_CREATE_VIDEO                            pFncSI_CreateVideo;
    PFNC_SI_PLAY_VIDEO                              pFncSI_PlayVideo;
    PFNC_SI_CLOSE_VIDEO                             pFncSI_CloseVideo;
    PFNC_SI_STOP_VIDEO                              pFncSI_StopVideo;
    PFNC_SI_PAUSE_VIDEO                             pFncSI_PauseVideo;
    PFNC_SI_RESUME_VIDEO                            pFncSI_ResumeVideo;
    PFNC_SI_GET_VIDEO_METHODS                       pFncSI_GetVideoMethods;
    PFNC_SI_PROCESS_VIDEO_METHOD                    pFncSI_ProcessVideoMethod;
    PFNC_SI_DESTROY_VIDEO                           pFncSI_DestroyVideo;
    PFNC_SI_SET_VIDEO_RECT                          pFncSI_SetVideoRect;
	PFNC_SI_GET_VIDEO_SIZE							pFncSI_GetVideoSize;
	PFNC_SI_GET_VIDEO_BITS							pFncSI_GetVideoBits;
	NO_OPERATOR_NEW_DELETE
};


/*
 *  Structure containing SI_xxx functions used for FLV
 */
struct SI_FLVFncs
{
	PFNC_SI_FLVSTATUS				    pFncSI_FLVStatus;
	NO_OPERATOR_NEW_DELETE
};


/*
 *  Structure containing SI_xxx functions used for device control and integration
 */
struct SI_DeviceControlFncs
{
    PFNC_SI_CONVERTUTCTOLOCALTIME                   pFncSI_ConvertUTCToLocalTime;
    PFNC_SI_CONVERTLOCALTOUTCTIME                   pFncSI_ConvertLocalToUTCTime;
    PFNC_SI_DOFSCOMMAND                             pFncSI_DoFsCommand;
    PFNC_SI_FORMATDATE                              pFncSI_FormatDate;
    PFNC_SI_FORMATTIMEOFDAY                         pFncSI_FormatTimeOfDay;
    PFNC_SI_GETBATTERYLEVEL                         pFncSI_GetBatteryLevel;
    PFNC_SI_GETDEVICE                               pFncSI_GetDevice;
    PFNC_SI_GETDEVICECAPABILITIES                   pFncSI_GetDeviceCapabilities;
    PFNC_SI_GETDEVICEID                             pFncSI_GetDeviceID;
    PFNC_SI_GETLANGUAGE                             pFncSI_GetLanguage;
    PFNC_SI_GETMAXBATTERYLEVEL                      pFncSI_GetMaxBatteryLevel;
    PFNC_SI_GETMAXVOLUMELEVEL                       pFncSI_GetMaxVolumeLevel;
    PFNC_SI_GETPLATFORM                             pFncSI_GetPlatform;
    PFNC_SI_GETPOWERSOURCE                          pFncSI_GetPowerSource;
    PFNC_SI_GETUTCTIMESTAMP                         pFncSI_GetUTCTimeStamp;
    PFNC_SI_GETVOLUMELEVEL                          pFncSI_GetVolumeLevel;
    PFNC_SI_LAUNCH                                  pFncSI_Launch;
    PFNC_SI_QUITFLASHLITE                           pFncSI_QuitFlashLite;
    PFNC_SI_RESETSOFTKEYS                           pFncSI_ResetSoftkeys;
    PFNC_SI_GETNUMBEROFSOFTKEYS                     pFncSI_GetNumberOfSoftkeys;
	PFNC_SI_GETSOFTKEYLOCATION						pFncSI_GetSoftkeyLocation;
    PFNC_SI_SETFULLSCREEN                           pFncSI_SetFullScreen;
    PFNC_SI_SETSOFTKEYS                             pFncSI_SetSoftkeys;
    PFNC_SI_STARTVIBRATE                            pFncSI_StartVibrate;
    PFNC_SI_STOPVIBRATE                             pFncSI_StopVibrate;
    PFNC_SI_EXTENDBACKLIGHTDURATION                 pFncSI_ExtendBacklightDuration;
    PFNC_SI_GETSCREENINFO                           pFncSI_GetScreenInfo;
    PFNC_SI_GETNETWORKINFO                          pFncSI_GetNetworkInfo;
    PFNC_SI_GETNETWORKINFOSTRING                    pFncSI_GetNetworkInfoString;
    PFNC_SI_GETCALLERINFO		                    pFncSI_GetCallerInfo;
	NO_OPERATOR_NEW_DELETE
};

/*
 *  Structure containing SI_xxx functions used for XML Socket
 */
struct SI_SocketFncs
{
	PFNC_SI_OPENSOCKET								pFncSI_OpenSocket;
	PFNC_SI_CLOSESOCKET								pFncSI_CloseSocket;
	PFNC_SI_SOCKETSEND								pFncSI_SocketSend;
	NO_OPERATOR_NEW_DELETE
};

/*
*  Structure containing SI_xxx functions used for Native Video (Flash video & not device video)
 */

#if defined(FEATURE_VIDEO_DLL) || defined(FEATURE_H264_DLL)
struct SI_NativeVideoFncs
{
    PFNC_SI_CREATE_DECOMPRESSOR                     pFncSI_CreateDecompressor;
    PFNC_SI_DESTROY_DECOMPRESSOR                    pFncSI_DestroyDecompressor;
	NO_OPERATOR_NEW_DELETE
};
#endif //FEATURE_VIDEO_DLL

/*
 * Structure containing SI_xxx functions used for External API
 */
struct SI_ExternalAPIFncs
{
	PFNC_SI_EVALJS									pFncSI_EvalJs;
	PFNC_SI_DOFLASHCALL								pFncSI_DoFlashCall;
	PFNC_SI_GETDOMOBJECTID							pFncSI_GetDomObjectId;
	NO_OPERATOR_NEW_DELETE
};

/*
 * Structure containing Miscellaneous SI_xxx functions
 */
struct SI_MiscFncs
{
	PFNC_SI_GETPLUGININFO							pFncSI_GetPluginInfo;
	NO_OPERATOR_NEW_DELETE
};

/*
 * Structure containing SI_xxx functions used for OpenGL-ES
 */
struct SI_OpenGLESFncs
{
	PFNC_SI_GLISTEXTURE								pFncSI_glIsTexture;
	PFNC_SI_GLGETERROR								pFncSI_glGetError;
	PFNC_SI_GLACTIVETEXTURE							pFncSI_glActiveTexture;
	PFNC_SI_GLALPHAFUNCX							pFncSI_glAlphaFuncx;
	PFNC_SI_GLBINDBUFFER							pFncSI_glBindBuffer;
	PFNC_SI_GLBINDTEXTURE							pFncSI_glBindTexture;
	PFNC_SI_GLBLENDFUNC								pFncSI_glBlendFunc;
	PFNC_SI_GLBUFFERDATA							pFncSI_glBufferData;
	PFNC_SI_GLCLEAR									pFncSI_glClear;
	PFNC_SI_GLCLEARCOLORX							pFncSI_glClearColorx;
	PFNC_SI_GLCLEARDEPTHX							pFncSI_glClearDepthx;
	PFNC_SI_GLCLEARSTENCIL							pFncSI_glClearStencil;
	PFNC_SI_GLCLIENTACTIVETEXTURE					pFncSI_glClientActiveTexture;
	PFNC_SI_GLCLIPPLANEX							pFncSI_glClipPlanex;
	PFNC_SI_GLCOLOR4X								pFncSI_glColor4x;
	PFNC_SI_GLCOLORMASK								pFncSI_glColorMask;
	PFNC_SI_GLCULLFACE								pFncSI_glCullFace;
	PFNC_SI_GLDELETETEXTURES						pFncSI_glDeleteTextures;
	PFNC_SI_GLDEPTHFUNC								pFncSI_glDepthFunc;
	PFNC_SI_GLDEPTHMASK								pFncSI_glDepthMask;
	PFNC_SI_GLDISABLE								pFncSI_glDisable;
	PFNC_SI_GLDISABLECLIENTSTATE					pFncSI_glDisableClientState;
	PFNC_SI_GLDRAWARRAYS							pFncSI_glDrawArrays;
	PFNC_SI_GLDRAWELEMENTS							pFncSI_glDrawElements;
	PFNC_SI_GLENABLE								pFncSI_glEnable;
	PFNC_SI_GLENABLECLIENTSTATE						pFncSI_glEnableClientState;
	PFNC_SI_GLFINISH								pFncSI_glFinish;
	PFNC_SI_GLGENTEXTURES							pFncSI_glGenTextures;
	PFNC_SI_GLGETINTEGERV							pFncSI_glGetIntegerv;
	PFNC_SI_GLHINT									pFncSI_glHint;
	PFNC_SI_GLLINEWIDTHX							pFncSI_glLineWidthx;
	PFNC_SI_GLLOADIDENTITY							pFncSI_glLoadIdentity;
	PFNC_SI_GLLOADMATRIXX							pFncSI_glLoadMatrixx;
	PFNC_SI_GLLOGICOP								pFncSI_glLogicOp;
	PFNC_SI_GLMATRIXMODE							pFncSI_glMatrixMode;
	PFNC_SI_GLMULTMATRIXX							pFncSI_glMultMatrixx;
	PFNC_SI_GLORTHOX								pFncSI_glOrthox;
	PFNC_SI_GLPIXELSTOREI							pFncSI_glPixelStorei;
	PFNC_SI_GLPOLYGONOFFSETX						pFncSI_glPolygonOffsetx;
	PFNC_SI_GLPOPMATRIX								pFncSI_glPopMatrix;
	PFNC_SI_GLPUSHMATRIX							pFncSI_glPushMatrix;
	PFNC_SI_GLSCALEX								pFncSI_glScalex;
	PFNC_SI_GLSCISSOR								pFncSI_glScissor;
	PFNC_SI_GLSHADEMODEL							pFncSI_glShadeModel;
	PFNC_SI_GLSTENCILFUNC							pFncSI_glStencilFunc;
	PFNC_SI_GLSTENCILOP								pFncSI_glStencilOp;
	PFNC_SI_GLTEXCOORDPOINTER						pFncSI_glTexCoordPointer;
	PFNC_SI_GLTEXENVX								pFncSI_glTexEnvx;
	PFNC_SI_GLTEXENVXV								pFncSI_glTexEnvxv;
	PFNC_SI_GLTEXIMAGE2D							pFncSI_glTexImage2D;
	PFNC_SI_GLTEXPARAMETERX							pFncSI_glTexParameterx;
	PFNC_SI_GLTEXSUBIMAGE2D							pFncSI_glTexSubImage2D;
	PFNC_SI_GLTRANSLATEX							pFncSI_glTranslatex;
	PFNC_SI_GLVERTEXPOINTER							pFncSI_glVertexPointer;
	PFNC_SI_GLVIEWPORT								pFncSI_glViewport;
	NO_OPERATOR_NEW_DELETE
};

/*
 * Structure containing SI_xxx functions used for OpenVG 1.1
 */
struct SI_OpenVGFncs
{
	PFNC_SI_VGAPPENDPATH				pFncSI_vgAppendPath;
	PFNC_SI_VGAPPENDPATHDATA			pFncSI_vgAppendPathData;
	PFNC_SI_VGCLEAR						pFncSI_vgClear;
	PFNC_SI_VGCLEARIMAGE				pFncSI_vgClearImage;
	PFNC_SI_VGCLEARPATH					pFncSI_vgClearPath;
	PFNC_SI_VGCREATEIMAGE				pFncSI_vgCreateImage;
	PFNC_SI_VGCREATEMASKLAYER			pFncSI_vgCreateMaskLayer;
	PFNC_SI_VGCREATEPAINT				pFncSI_vgCreatePaint;
	PFNC_SI_VGCREATEPATH				pFncSI_vgCreatePath;
	PFNC_SI_VGCOPYMASK					pFncSI_vgCopyMask;
	PFNC_SI_VGDESTROYIMAGE				pFncSI_vgDestroyImage;
	PFNC_SI_VGDESTROYMASKLAYER			pFncSI_vgDestroyMaskLayer;
	PFNC_SI_VGDESTROYPAINT				pFncSI_vgDestroyPaint;
	PFNC_SI_VGDESTROYPATH				pFncSI_vgDestroyPath;
	PFNC_SI_VGDRAWIMAGE					pFncSI_vgDrawImage;
	PFNC_SI_VGDRAWPATH					pFncSI_vgDrawPath;
	PFNC_SI_VGFINISH					pFncSI_vgFinish;
	PFNC_SI_VGFLUSH						pFncSI_vgFlush;
	PFNC_SI_VGGETERROR					pFncSI_vgGetError;
	PFNC_SI_VGGETMATRIX					pFncSI_vgGetMatrix;
	PFNC_SI_VGGETPARAMETERI				pFncSI_vgGetParameteri;
	PFNC_SI_VGGETI						pFncSI_vgGeti;
	PFNC_SI_VGIMAGESUBDATA				pFncSI_vgImageSubData;
	PFNC_SI_VGINTERPOLATEPATH			pFncSI_vgInterpolatePath;
	PFNC_SI_VGLOADIDENTITY				pFncSI_vgLoadIdentity;
	PFNC_SI_VGLOADMATRIX				pFncSI_vgLoadMatrix;
	PFNC_SI_VGMASK						pFncSI_vgMask;
	PFNC_SI_VGMODIFYPATHCOORDS			pFncSI_vgModifyPathCoords;
	PFNC_SI_VGMULTMATRIX				pFncSI_vgMultMatrix;
	PFNC_SI_VGPAINTPATTERN				pFncSI_vgPaintPattern;
	PFNC_SI_VGPATHBOUNDS				pFncSI_vgPathBounds;
	PFNC_SI_VGRENDERTOMASK				pFncSI_vgRenderToMask;
	PFNC_SI_VGSCALE						pFncSI_vgScale;
	PFNC_SI_VGSETCOLOR					pFncSI_vgSetColor;
	PFNC_SI_VGSETPAINT					pFncSI_vgSetPaint;
	PFNC_SI_VGSETPARAMETERFV			pFncSI_vgSetParameterfv;
	PFNC_SI_VGSETPARAMETERI				pFncSI_vgSetParameteri;
	PFNC_SI_VGSETF						pFncSI_vgSetf;
	PFNC_SI_VGSETFV						pFncSI_vgSetfv;
	PFNC_SI_VGSETI						pFncSI_vgSeti;
	PFNC_SI_VGSETIV						pFncSI_vgSetiv;
	PFNC_SI_VGTRANSFORMPATH				pFncSI_vgTransformPath;
	PFNC_SI_VGTRANSLATE					pFncSI_vgTranslate;
	NO_OPERATOR_NEW_DELETE
};

/*
 * Structure containing SI_xxx functions used Security related functions.
 */
struct SI_SecurityFncs
{
	PFNC_SI_SHOWLOCALSECURITYDIALOG					pFncSI_showLocalSecurityDialog;
	NO_OPERATOR_NEW_DELETE
};


/*
 * Structure containing SI_xxx functions used for 2D Hardware blitter acceleration.
 */
struct SI_2DHWBlitFncs
{
    PFNC_SI_BLIT                pFncSI_2DBlit;
    PFNC_SI_FILL                pFncSI_Fill;
    PFNC_SI_FLUSHBLIT           pFncSI_FlushBlit;
    PFNC_SI_BMMALLOC            pFncSI_BmMalloc;
    PFNC_SI_BMFREE              pFncSI_BmFree;
    PFNC_SI_BMLOCK              pFncSI_BmLock;
    PFNC_SI_BMUNLOCK            pFncSI_BmUnlock;
	PFNC_SI_BLITCAPS			pFncSI_BlitCaps;
    NO_OPERATOR_NEW_DELETE
};

/*
 * Structure containing SI_xxx functions used for RTMP digest operations.
 */
struct SI_RTMPDigestFncs
{
    PFNC_SI_RTMP_CALCHMAC               pFncSI_RTMP_CalcHMAC;
    PFNC_SI_RTMP_CALCPLAYERHMAC         pFncSI_RTMP_CalcPlayerHMAC;
    PFNC_SI_RTMP_ADDPLAYERHMAC1         pFncSI_RTMP_AddPlayerHMAC1;
    PFNC_SI_RTMP_ADDPLAYERHMAC3         pFncSI_RTMP_AddPlayerHMAC3;
    PFNC_SI_RTMP_VERIFYSERVERHMAC       pFncSI_RTMP_VerifyServerHMAC;
    PFNC_SI_RTMP_VERIFYSERVERHMAC3      pFncSI_RTMP_VerifyServerHMAC3;
    NO_OPERATOR_NEW_DELETE
};


/****************************************************************************\
    SI_Fncs structure definitions
\****************************************************************************/

struct SI_Fncs
{
//  SI_Fncs_Members
    unsigned short                              SIVersion;
    unsigned short                              sizeof_SI_Fncs;
    void*                                       pMM_Reserved;
    struct SI_FlashPlayerControlFncs*           pSIFlashPlayerControl;
    struct SI_DebugFncs*                        pSIDebugFncs;
    struct SI_FrameBufferFncs*                  pSIFrameBufferFncs;
    struct SI_DataAccessFncs*                   pSIDataAccessFncs;
	struct SI_URLAccessFncs*					pSIURLAccessFncs;
    struct SI_KeyInputFncs*                     pSIKeyInputFncs;
    struct SI_TextRenderingFncs*                pSITextRenderingFncs;
    struct SI_ErrorNotifyFncs*                  pSIFlashLiteErrorNotifyFncs;
    struct SI_SoundControlFncs*                 pSISoundControlFncs;
    struct SI_DeviceControlFncs*                pSIDeviceControlFncs;
    struct SI_MMIFncs*                          pSIMMIFncs;
    struct SI_VideoFncs*                        pSIVideoFncs;
    struct SI_ImageFncs*                        pSIImageFncs;
    struct SI_SVGFncs*                          pSISVGFncs;
    struct SI_CharacterEncodingFncs*            pSICharacterEncodingFncs;
	struct SI_InlineTextEntryFncs*				pSIInlineTextEntryFncs;
	struct SI_SocketFncs*						pSISocketFncs;
	struct SI_FileAccessFncs*					pSIFileAccessFncs;
    struct SI_NativeVideoFncs*                  pSINativeVideoFncs;
	struct SI_ExternalAPIFncs*					pSIExternalAPIFncs;
    struct SI_OpenGLESFncs*                     pSIOpenGLESFncs;
    struct SI_MiscFncs*							pSIMiscFncs;
    struct SI_2DHWBlitFncs*                     pSI2DHWBlitFncs;
	struct SI_FLVFncs*							pSIFLVFncs;
	struct SI_SecurityFncs*						pSISecurityFncs;
    struct SI_OpenVGFncs*                       pSIOpenVGFncs;
    struct SI_RTMPDigestFncs*                   pSIRTMPDigestFncs;

	// DO NOT under any circumstances put function pointers in here with #ifdef FEATURE_ around them.
	// If the table size is different because of slightly different compilation options, it ends up crashing
	// somewhere. If the feature is not supported, the structure pointer will remain NULL and the callback
	// will not be made.

	NO_OPERATOR_NEW_DELETE
};


/****************************************************************************\
    FI_xxx Function prototype declarations (grouped based on functionalities)
\****************************************************************************/

#ifdef DLL_SINGLE_ENTRYPOINT
typedef void*               (*PFNC_FI_FLASHLITE_GETINTERFACE)   (EFlashLiteInterfaceIndex index);
#endif

/*
 *  FI_xxx functions used for FlashLite player control. First four functions
 *  are exported functions.
 */
typedef struct MM_Object*   (*PFNC_FI_CREATEPLAYER)             (void* memoryPool,
                                                                 unsigned long size,
                                                                 struct SI_HostObject* pHostObj,
                                                                 const struct SI_Fncs* pSIFncs,
                                                                 unsigned long configFlags);
typedef short				(*PFNC_FI_CHECKSWFDATABUFFERVALIDITY)   (const unsigned char* swfBuffer,
                                                                     unsigned long bufferLength,
																	 void* memoryPool,
																	 unsigned short poolSize);

typedef short				(*PFNC_FI_GETMETADATA)				( const unsigned char* swfBuffer,
																  unsigned long bufferLength,
																  struct FI_MetaDataInfo* pMetaInfoStruct
																 );

typedef short				(*PFNC_FI_GetSharedObjectGUID)      ( struct MM_Object* pMMObj,
																  const unsigned char *swfBuffer,
																  unsigned long bufferLength,
																  void* memoryPool,
																  unsigned long poolSize,
																  struct FI_ReturnString* pGUID
																 );


typedef const char*         (*PFNC_FI_GETVERSION)               (void);
typedef unsigned short      (*PFNC_FI_GETDISPLAYFORMAT)         (struct MM_Object* pMMObj);


typedef void                (*PFNC_FI_DESTROYPLAYER)            (struct MM_Object* pMMObj);
typedef short      			(*PFNC_FI_SETDISPLAYRECT)           (struct MM_Object* pMMObj,
                                                                 const struct FI_Rect* rect);
typedef short				(*PFNC_FI_SETDIRTYRECT)             (struct MM_Object* pMMObj,
                                                                 const struct FI_Rect * rect,
																 unsigned short onCurrentFrame);
typedef short				(*PFNC_FI_SETCLIPPINGRECT)          (struct MM_Object* pMMObj,
                                                                 const struct FI_Rect * rect);
typedef unsigned short      (*PFNC_FI_SETSWFDATABUFFER)         (struct MM_Object* pMMObj,
                                                                 const char* swfURL,
                                                                 const unsigned char* swfBuffer,
                                                                 unsigned long bufferLength,
                                                                 unsigned short inlineContent,
																 unsigned short bufferMode,
																 enum FI_SecuritySandbox swfSandbox);

typedef unsigned short      (*PFNC_FI_SETPREINSTALLEDASCLASS)   (struct MM_Object* pMMObj,
                                                                 const unsigned char* swfBuffer,
                                                                 unsigned long bufferLength,
                                                                 unsigned short bIsNativeLibrary);


typedef void                (*PFNC_FI_GETSWFDIMENSIONS)         (struct MM_Object* pMMObj,
                                                                 unsigned short* width,
                                                                 unsigned short* height);
typedef unsigned short      (*PFNC_FI_DOPLAY)                   (struct MM_Object* pMMObj,
                                                                 unsigned short forceRender);
typedef short*              (*PFNC_FI_GETINTERRUPTVARIABLE)     (struct MM_Object* pMMObj);
typedef void                (*PFNC_FI_SETLOOPING)               (struct MM_Object* pMMObj, short loop);
typedef void                (*PFNC_FI_SETBACKGROUNDCOLOR)       (struct MM_Object* pMMObj,
																 short red, short green, short blue);
typedef void				(*PFNC_FI_SETBACKGROUNDALPHA)		(struct MM_Object* pMMObj,
																 unsigned short alpha,
																 unsigned short blendWithBackground);
typedef short               (*PFNC_FI_GETQUALITY)               (struct MM_Object* pMMObj);
typedef void                (*PFNC_FI_SETQUALITY)               (struct MM_Object* pMMObj, short quality);
typedef void                (*PFNC_FI_PAUSE)                    (struct MM_Object* pMMObj);
typedef void                (*PFNC_FI_RESUME)                   (struct MM_Object* pMMObj);
typedef void                (*PFNC_FI_STOP)                     (struct MM_Object* pMMObj);
typedef void                (*PFNC_FI_NOTIFYVIDEOSTATUS)        (struct MM_Object* pMMObj, unsigned long videoID, int severity, struct FI_Text* pRetCode);
typedef unsigned short      (*PFNC_FI_ISPLAYING)                (struct MM_Object* pMMObj);
typedef unsigned short      (*PFNC_FI_FRAMENUMBER)              (struct MM_Object* pMMObj);
typedef unsigned short      (*PFNC_FI_NUMBEROFFRAMES)           (struct MM_Object* pMMObj);

typedef unsigned short		(*PFNC_FI_NUMBEROFVIDEOOVERLAYRECTS)(struct MM_Object* pMMObj);
typedef unsigned short		(*PFNC_FI_GETVIDEOOVERLAYRECTS)		(struct MM_Object* pMMObj,
																 struct FI_Rect* rects,
																 unsigned short rectsLen);

/*
 * User Transform Interfaces
 */
typedef unsigned short  (*PFNC_FI_ROTATE)                       (struct MM_Object* pMMObj,
                                                                 long degrees,
                                                                 long xorigin,
                                                                 long yorigin);

typedef unsigned short  (*PFNC_FI_TRANSLATE)                    (struct MM_Object* pMMObj,
                                                                 long dx,
                                                                 long dy);

typedef unsigned short  (*PFNC_FI_SCALE)                        (struct MM_Object* pMMObj,
                                                                 long scale,
                                                                 long xorigin,
                                                                 long yorigin);

typedef void            (*PFNC_FI_RESETTRANSFORM)               (struct MM_Object* pMMObj);
typedef unsigned short  (*PFNC_FI_GETZOOMANDPANENABLED)         (struct MM_Object* pMMObj);


typedef unsigned short 	(*PFNC_FI_CONVERTTOUSERSPACE)			( struct MM_Object* pMMObj,
																  long screenx,
																  long screeny,
																  long *userx,
																  long *usery );
typedef unsigned short 	(*PFNC_FI_CONVERTTOUSERSPACEF)			( struct MM_Object* pMMObj,
																  float screenx,
																  float screeny,
																  float *userx,
																  float *usery );

typedef unsigned short	(*PFNC_FI_CONVERTTOVIEWPORTSPACE)		( struct MM_Object* pMMObj,
																  long userx,
																  long usery,
																  long *screenx,
																  long *screeny );
typedef unsigned short	(*PFNC_FI_CONVERTTOVIEWPORTSPACEF)		( struct MM_Object* pMMObj,
																  float userx,
																  float usery,
																  float *screenx,
																  float *screeny );

/*
 *  FI_xxx functions used for FlashLite off-screen frame-buffer control.
 */
typedef short      		(*PFNC_FI_SETFRAMEBUFFER)           (struct MM_Object* pMMObj,
                                                                 unsigned char* bufferPtr,
                                                                 unsigned short width,
                                                                 unsigned short height,
                                                                 unsigned short pitch,
																 unsigned short lDisplayFormat);

typedef short			(*PFNC_FI_CONFIGUREHARDWARERENDERING)	(struct MM_Object*, struct FI_HardwareConfig* config);

typedef short           (*PFNC_FI_SETHARDWAREFRAMEBUFFER)       (struct MM_Object* pMMObj,
                                                                 FI_Display  display,
                                                                 struct FI_Surface* pSurface,
                                                                 FI_Context  context,
                                                                 unsigned short width,
                                                                 unsigned short height);

typedef void			(*PFNC_FI_SETRENDERHINT)				(struct MM_Object* pMMObj,
																 enum FI_RenderHint renderHint);

typedef enum FI_RenderMode	(*PFNC_FI_GETRENDERMODE)				(struct MM_Object* pMMObj);

typedef void			(*PFNC_FI_SETHARDWAREDEVICELIMIT)			(struct MM_Object* pMMObj, enum FI_HardwareDeviceLimitIndex index, long value);
typedef long			(*PFNC_FI_GETHARDWAREDEVICELIMIT)			(struct MM_Object* pMMObj, enum FI_HardwareDeviceLimitIndex index );

typedef unsigned short      (*PFNC_FI_GETDIRTYRECT)             (struct MM_Object* pMMObj,
                                                                 struct FI_Rect* dirtyRect);

typedef unsigned short	(*PFNC_FI_GETNUMBEROFSUBDIRTYRECTS)		(struct MM_Object* pMMObj);
typedef unsigned short	(*PFNC_FI_GETSUBDIRTYRECT)          	(struct MM_Object* pMMObj,
 																 unsigned int index,
                                                                  struct FI_Rect* dirtyRect);

typedef void			(*PFNC_FI_NOTIFYFULLSCREENSTATUS)		(struct MM_Object* pMMObj,
																 int flag);
/*
 *  FI_xxx functions used for FlashLite URL access.
 */
typedef struct FI_NetworkBufferInfo*    (*PFNC_FI_URLSTREAMOPENED)          (struct MM_Object* pMMObj,
                                                                 unsigned short streamId,
                                                                 struct FI_URLStreamHeaderInfo* pHeaders,
                                                                 short* pErrorCode);

typedef void                (*PFNC_FI_URLSTREAMSETFINALURL)		(struct MM_Object* pMMObj,
                                                                 unsigned short streamId,
                                                                 const char* finalURL,
																 enum FI_SecuritySandbox swfSandbox);

typedef struct FI_NetworkBufferInfo* (*PFNC_FI_URLSTREAMDATALOADED) (struct MM_Object* pMMObj,
                                                                 unsigned short streamId,
                                                                 struct FI_NetworkBufferInfo* pNetBuf);

typedef void                (*PFNC_FI_URLSTREAMCLOSED)          (struct MM_Object* pMMObj,
                                                                 unsigned short streamId,
                                                                 unsigned short closedStatusCode,
																 unsigned int httpStatusCode);

typedef unsigned short		(*PFNC_FI_SETURLSTREAMDATABUFFER)	(struct MM_Object* pMMObj,
																 unsigned short streamId,
																 struct FI_URLStreamHeaderInfo* pHeaders,
																 const unsigned char* dataBuffer,
																 unsigned long bufferLength);

/*
 * Callback function for SharedObject requests.
 */
typedef void (*PFNC_FI_NOTIFYSHAREDOBJECTSTATUS)	(struct MM_Object* pMMObj,
													 unsigned long requestId,
													 unsigned long dataSize,
													 short status);
/*
 *  FI_xxx functions used for FlashLite key and text input.
 */
typedef void                (*PFNC_FI_SETINPUTTEXT)             (struct MM_Object* pMMObj,
                                                                 const struct FI_Text* inputText);
typedef void                (*PFNC_FI_CANCELINPUTTEXT)          (struct MM_Object* pMMObj);
typedef unsigned short      (*PFNC_FI_OFFERKEYEVENT)            (struct MM_Object* pMMObj,
                                                                 const struct FI_KeyId* keyCode,
                                                                 unsigned short eventCode);
typedef unsigned short      (*PFNC_FI_OFFERKEYPRESSEVENT)       (struct MM_Object* pMMObj,
                                                                 const struct FI_KeyId* keyCode);

/*
 *  FI_xxx functions used for FlashLite stylus/mouse input.
 */
typedef unsigned short      (*PFNC_FI_DOHITTEST)                (struct MM_Object* pMMObj,
                                                                 unsigned short xPos,
                                                                 unsigned short yPos);
typedef unsigned short      (*PFNC_FI_OFFERMOUSEEVENT)          (struct MM_Object* pMMObj,
                                                                 unsigned short xMousePos,
                                                                 unsigned short yMousePos,
                                                                 unsigned short mouseEvent);
/*
 *  FI_xxx functions used for FlashLite sound control.
 */
typedef void                (*PFNC_FI_SETUPDEVICESOUND)         (struct MM_Object* pMMObj,
                                                                 unsigned short numFormats,
                                                                 const char* formatInfo[],
                                                                 unsigned short mustbeCached);
typedef unsigned short      (*PFNC_FI_SETUPSTREAMSOUND)         (struct MM_Object* pMMObj,
                                                                 struct FI_StreamSoundInfo* pSoundInfo,
                                                                 struct FI_StreamSoundSetupInfo* pSetupInfo);
typedef void                (*PFNC_FI_STREAMSOUNDBUFFERCOMPLETE)    (struct MM_Object* pMMObj);
typedef void                (*PFNC_FI_STREAMSOUNDRESET)         (struct MM_Object* pMMObj);

typedef void				(*PFNC_FI_NOTIFYDEVICESOUNDSTATUS)	(struct MM_Object* pMMObj,
																 unsigned short status);

/*
 * FI functions for letting player know what MIME types are supported by devide codecs.
 * Currently only used to populate System.capabilities.
 * Device must call these before animation is started.
 *
 * FI_SetupDeviceImage: MIME types supported by the devices image codecs and that can be used by the loadMovie ActionScript function.
 * FI_SetupDeviceVideo: MIME types supported by the devices video codecs and that can be used by the ActionScript Video object.
 * FI_SetupDeviceSound: MIME types supported by the devices audio codecs and that can be used by the ActionScript Sound object.
 *
 * @param numFormats Number of elements MIME types in formats-array
 * @param formats Array of strigs of which each is one supported MIME type.
 * @return 1 if function call was successfull, 0 if it failed
 */

typedef unsigned short      (*PFNC_FI_SETUPDEVICEIMAGE)         (struct MM_Object* pMMObj,
                                                                 unsigned short numFormats,
                                                                 const char* formatInfo[]);
typedef unsigned short      (*PFNC_FI_SETUPDEVICEVIDEO)         (struct MM_Object* pMMObj,
                                                                 unsigned short numFormats,
                                                                 const char* formatInfo[]);

/*
 *  FI_xxx functions used for character encoding
 */
typedef void            (*PFNC_FI_SETNATIVECODEPAGE)            (struct MM_Object* pMMObj,
                                                                 FI_CodePage code);


/*
 *  FI_xxx functions used for setting the boundary navigation behaviors of text fields
 */
typedef void            (*PFNC_FI_SETTEXTFIELDBOUNDARYBEHAVIOR)	(struct MM_Object* pMMObj,
																 unsigned long behavior);

/***************** FI_xxx functions used for vector font support ****************/

typedef unsigned short  (*PFNC_FI_SETVECTORFONTDATA)            (struct MM_Object* pMMObj, const unsigned char * pFontData, unsigned long dataSize);
typedef void            (*PFNC_FI_SETVECTORFONTMAP)             (struct MM_Object* pMMObj, const char* sansName, const char* serifName, const char* typewriterName);
typedef unsigned short	(*PFNC_FI_ADDVECTORFONTFILE)			(struct MM_Object* pMMObj,  const char *filename);

/********************************************************************************/

/*
 * FI_xxx functions used for SVG
 */

// Function to set the SVG data to be played back.
typedef unsigned short  (*PFNC_FI_SETSVGDATABUFFER) (struct MM_Object* pMMObj,
                                                     const char* svgURL,
                                                     const unsigned char* svgBuffer,
                                                     unsigned long bufferLength);







// Get the dimensions of the SVG movie. If the movie is valid,
// the dimensions will be set. Otherwise, the dimensions will be (0,0).
typedef void            (*PFNC_FI_GETSVGDIMENSIONS) (struct MM_Object* pMMObj,
                                                     unsigned short * width,
                                                     unsigned short * height);


typedef unsigned short	(*PFNC_FI_SETSVGVIEWPORTSIZE) (struct MM_Object* pMMObj,
													   unsigned short width,
													   unsigned short height);

/*
 * FI_xxx functions used for XMLSocket
 */
//Function to send the data reserved from server to the corresponding XMLSocket
typedef short			(*PFNC_FI_SOCKETRECV)			(struct MM_Object* pMMObj,
														 unsigned long socketId,
														 struct FI_NetworkBufferInfo* pDataBuffer,
														 struct FI_NetworkBufferInfo** pNextRecvBuffer);

//Function to notify the player to close an open XMLSocket
typedef short			(*PFNC_FI_NOTIFYSOCKETSTATUS)	(struct MM_Object* pMMObj,
														 unsigned long socketId,
														 unsigned short status,
														 short errorCode);

typedef short			(*PFNC_FI_NOTIFYRESOLVEDADDRESS)(struct MM_Object* pMMObj,
														 unsigned long socketId,
														 const struct FI_IpInfo * ipInfo);

/*
 * FI_xxx functions used for Image decoding
 */
//Function to notify the player that the image information has been retrieved
typedef struct FI_DecodeImageInfo* (*PFNC_FI_GETDECODEIMAGEBUFFER)  (struct MM_Object* pMMObj,
																     unsigned long imageId,
																     const struct FI_ImageInfo* pImageInfo);

//Function to notify the player that the image has been decoded
typedef short					   (*PFNC_FI_NOTIFYIMAGEDECODESTATUS)  (struct MM_Object* pMMObj,
																		unsigned long imageId,
																        short error);
/*
 * FI_xxx functions used for Web browsability
 */

typedef void						(*PFNC_FI_SETBASEURL)				(struct MM_Object* pMMObj,
																		 const char* baseURL);

typedef void						(*PFNC_FI_SETSCRIPTACCESS)			(struct MM_Object* pMMObj,
																		 int pAccessLevel);

typedef int							(*PFNC_FI_GETSCRIPTACCESS)			(struct MM_Object* pMMObj);

typedef int							(*PFNC_FI_TCURRENTFRAME)			(struct MM_Object *pMMObj,
																		 const char* pTargetIn,
																		 const struct FI_BrowserURLInfo *pURLInfoIn);

typedef void*						(*PFNC_FI_TCURRENTLABEL)			(struct MM_Object *pMMObj,
																		 const char* pTargetIn,
													 					 const struct FI_BrowserURLInfo* pBrowserInfoIn,
																		 struct FI_AllocatorWrapper* currentLabel);

typedef void*						(*PFNC_FI_TGETPROPERTY)				(struct MM_Object *pMMObj,
																		 const struct FI_TargetPropertyInfo* ptargetInfoIn,
																		 const struct FI_BrowserURLInfo* pBrowserInfoIn,
																		 struct FI_AllocatorWrapper* propertyValue);


typedef int							(*PFNC_FI_TSETPROPERTY)				(struct MM_Object *pMMObj,
																		 const struct FI_TargetPropertyInfo* ptargetInfoIn,
																		 const struct FI_BrowserURLInfo* pBrowserInfoIn,
																		 const char* value);

typedef int							(*PFNC_FI_GETBACKGROUNDCOLOR)		(struct MM_Object* pMMObj, struct FI_Color *returnBgColor);

typedef void						(*PFNC_FI_LOADLAYER)				(struct MM_Object* pMMObj,
																		 const char* swfURL,
																		 int layer,
																		 struct FI_BrowserURLInfo* urlInfo);

typedef int							(*PFNC_FI_CANCONTAINERACCESSMOVIE)	(struct MM_Object *pMMObj,
																		 const struct FI_BrowserURLInfo* pURLInfoIn,
																		 int bAllowLocalUntrusted);
typedef void						(*PFNC_FI_SETCONTAINERSECURITYCONTEXT) (struct MM_Object *pMMObj,
																			const struct FI_BrowserURLInfo* pUrlInfoIn);

typedef int							(*PFNC_FI_SETVARIABLE2)				(struct MM_Object *pMMObj,
																		 const char* pVariableNameIn,
																		 const char* pVariableValueIn,
																		 const struct FI_BrowserURLInfo* pBrowserInfoIn);
typedef void*						(*PFNC_FI_GETVARIABLE2)				(struct MM_Object *pMMObj,
																		 const char* pVariableNameIn,
																		 const struct FI_BrowserURLInfo* pBrowserInfoIn,
																		 struct FI_AllocatorWrapper* hostAllocWrapper);

typedef void						(*PFNC_FI_SETSCALEMODE)				(struct MM_Object *pMMObj,
																		 int scaleModeValue);

typedef int							(*PFNC_FI_GETSCALEMODE)				(struct MM_Object  *pMMObj);

typedef void						(*PFNC_FI_SETALIGNMODE)				(struct MM_Object* mm_object, int alignMode );

typedef int							(*PFNC_FI_GETALIGNMODE)				(struct MM_Object* mm_object);

typedef void						(*PFNC_FI_SETALLOWFULLSCREEN)		(struct MM_Object* mm_object, int allowFullScreen);

typedef int							(*PFNC_FI_GETALLOWFULLSCREEN)		(struct MM_Object* mm_object);

/*
 * FI_xxx functions used for External API.
 */
typedef void *						(*PFNC_FI_CALLFUNCTION)				(struct MM_Object *pMMObj,
																		 const char* request,
																		 struct FI_AllocatorWrapper* allocWrapper);

typedef void						(*PFNC_FI_SETRETURNVALUEFROMCONTAINER)	(struct MM_Object *pMMObj,
																			 const char* returnValue);

/*
 * FI_xxx functions used  to set AS variables
 */
typedef void						(*PFNC_FI_SETFLASHVARS)				(struct MM_Object* mm_object,
																		 const char* values,
																		 unsigned short useUTF8 );
/*
 * FI_xxx functions used to get SWF Info
 */

typedef short			(*PFNC_FI_SWF_GET_INFO)			(const unsigned char* swfBuffer, unsigned long bufferLength,
														void* memoryPool,
														unsigned short poolSize,
														struct FI_SWFInfo* swfInfo);

typedef unsigned short  (*PFNC_FI_SWF_TIMESTAMP_INFO)	(struct MM_Object* pMMObj,
														const struct FI_LargeInteger* pTimeStamp,
														struct FI_DateTimeInfo* pDateTimeInfo);

/*
 * Native video functions
 */
typedef short (*PFNC_FI_SETMAXNATIVEVIDEOFRAMESTOPROCESS) (struct MM_Object* pMMObj,
														 unsigned int numFrames);

/****************************************************************************\
    FI_xxx sub-structure. These structures will be used in FI_Fncs structure
\****************************************************************************/

struct FI_MIMETypeFncs
{
    PFNC_FI_SETUPDEVICEIMAGE                        pFncFI_SetupDeviceImage;
    PFNC_FI_SETUPDEVICEVIDEO                        pFncFI_SetupDeviceVideo;
    // SetupDeviceSound is in sound funcs
	NO_OPERATOR_NEW_DELETE
};


/*
 * Structure containing FI_xxx functions for SVG.
 */
struct FI_SVGFncs
{
    PFNC_FI_SETSVGDATABUFFER            pFncFI_SetSVGDataBuffer;
    PFNC_FI_GETSVGDIMENSIONS            pFncFI_GetSVGDimensions;
	PFNC_FI_SETSVGVIEWPORTSIZE			pFncFI_SetSVGViewportSize;
	NO_OPERATOR_NEW_DELETE
};


/*
 *  Structure containing FI_xxx functions used for FlashLite player control
 */
struct FI_FlashPlayerControlFncs
{
    PFNC_FI_CREATEPLAYER                            pFncFI_CreatePlayer;
    PFNC_FI_CHECKSWFDATABUFFERVALIDITY              pFncFI_CheckSWFDataBufferValidity;
	PFNC_FI_GETVERSION                              pFncFI_GetVersion;
    PFNC_FI_GETDISPLAYFORMAT                        pFncFI_GetDisplayFormat;

    PFNC_FI_DESTROYPLAYER                           pFncFI_DestroyPlayer;
	PFNC_FI_SETDISPLAYRECT                          pFncFI_SetDisplayRect;
    PFNC_FI_SETSWFDATABUFFER                        pFncFI_SetSWFDataBuffer;
    PFNC_FI_SETDIRTYRECT                            pFncFI_SetDirtyRect;
    PFNC_FI_SETCLIPPINGRECT                         pFncFI_SetClippingRect;

    PFNC_FI_SETFRAMEBUFFER                          pFncFI_SetFrameBuffer;

    PFNC_FI_GETDIRTYRECT                            pFncFI_GetDirtyRect;
 	PFNC_FI_GETNUMBEROFSUBDIRTYRECTS				pFncFI_GetNumberOfSubDirtyRects;
 	PFNC_FI_GETSUBDIRTYRECT							pFncFI_GetSubDirtyRect;
 
	PFNC_FI_NOTIFYFULLSCREENSTATUS					pFncFI_NotifyFullScreenStatus;

    PFNC_FI_NUMBEROFVIDEOOVERLAYRECTS               pFncFI_NumberOfVideoOverlayRects;
    PFNC_FI_GETVIDEOOVERLAYRECTS                    pFncFI_GetVideoOverlayRects;

    PFNC_FI_GETSWFDIMENSIONS                        pFncFI_GetSWFDimensions;
    PFNC_FI_DOPLAY                                  pFncFI_DoPlay;
    PFNC_FI_GETINTERRUPTVARIABLE                    pFncFI_GetInterruptVariable;
    PFNC_FI_SETLOOPING                              pFncFI_SetLooping;
    PFNC_FI_SETBACKGROUNDCOLOR                      pFncFI_SetBackgroundColor;
	PFNC_FI_SETBACKGROUNDALPHA                      pFncFI_SetBackgroundAlpha;
    PFNC_FI_GETQUALITY                              pFncFI_GetQuality;
    PFNC_FI_SETQUALITY                              pFncFI_SetQuality;
    PFNC_FI_PAUSE                                   pFncFI_Pause;
    PFNC_FI_RESUME                                  pFncFI_Resume;
    PFNC_FI_STOP                                    pFncFI_Stop;
    PFNC_FI_NOTIFYVIDEOSTATUS                       pFncFI_NotifyVideoStatus;
    PFNC_FI_ISPLAYING                               pFncFI_IsPlaying;
    PFNC_FI_FRAMENUMBER                             pFncFI_FrameNumber;
    PFNC_FI_NUMBEROFFRAMES                          pFncFI_NumberOfFrames;

    PFNC_FI_ROTATE                                  pFncFI_Rotate;
    PFNC_FI_TRANSLATE                               pFncFI_Translate;
    PFNC_FI_SCALE                                   pFncFI_Scale;
    PFNC_FI_GETZOOMANDPANENABLED                    pFncFI_GetZoomAndPanEnabled;
    PFNC_FI_RESETTRANSFORM                          pFncFI_ResetTransform;

    PFNC_FI_CONVERTTOUSERSPACE						pFncFI_ConvertToUserSpace;
    PFNC_FI_CONVERTTOUSERSPACEF						pFncFI_ConvertToUserSpaceF;
    PFNC_FI_CONVERTTOVIEWPORTSPACE					pFncFI_ConvertToViewportSpace;
    PFNC_FI_CONVERTTOVIEWPORTSPACEF					pFncFI_ConvertToViewportSpaceF;

    PFNC_FI_SETNATIVECODEPAGE                       pFncFI_SetNativeCodePage;
	PFNC_FI_SETTEXTFIELDBOUNDARYBEHAVIOR			pFncFI_SetTextFieldBoundaryBehavior;

	PFNC_FI_GETMETADATA								pFncFI_GetMetaData;

	PFNC_FI_GetSharedObjectGUID						pFncFI_GetSharedObjectGUID;

	PFNC_FI_SETPREINSTALLEDASCLASS					pFncFI_SetPreInstalledASClass; // FEATURE_SETPREINSTALLEDASCLASS

	PFNC_FI_CONFIGUREHARDWARERENDERING				pFncFI_ConfigureHardwareRendering;

	PFNC_FI_SETHARDWAREFRAMEBUFFER                  pFncFI_SetHardwareFrameBuffer; // ACHW
	PFNC_FI_SETRENDERHINT							pFncFI_SetRenderHint; // ACHW
	PFNC_FI_GETRENDERMODE							pFncFI_GetRenderMode; // ACHW
	PFNC_FI_SETHARDWAREDEVICELIMIT					pFncFI_SetHardwareDeviceLimit;
	PFNC_FI_GETHARDWAREDEVICELIMIT					pFncFI_GetHardwareDeviceLimit;

	NO_OPERATOR_NEW_DELETE
};


/*
 *  Structure containing FI_xxx functions used for FlashLite player URL access.
 */
struct FI_URLAccessFncs
{
    PFNC_FI_URLSTREAMOPENED                         pFncFI_URLStreamOpened;
    PFNC_FI_URLSTREAMSETFINALURL                    pFncFI_URLStreamSetFinalURL;
    PFNC_FI_URLSTREAMDATALOADED                     pFncFI_URLStreamDataLoaded;
    PFNC_FI_URLSTREAMCLOSED                         pFncFI_URLStreamClosed;
	PFNC_FI_SETURLSTREAMDATABUFFER					pFncFI_SetURLStreamDataBuffer;
	NO_OPERATOR_NEW_DELETE
};


/*
 *  Structure containing FI_xxx functions used for FlashLite player Data access.
 */
struct FI_DataAccessFncs
{
    PFNC_FI_NOTIFYSHAREDOBJECTSTATUS                pFncFI_NotifySharedObjectStatus;
	NO_OPERATOR_NEW_DELETE
};


/*
 *  Structure containing FI_xxx functions used for FlashLite player text/key input.
 */
struct FI_KeyInputFncs
{
    PFNC_FI_CANCELINPUTTEXT                         pFncFI_CancelInputText;
    PFNC_FI_OFFERKEYEVENT                           pFncFI_OfferKeyEvent;
    PFNC_FI_OFFERKEYPRESSEVENT                      pFncFI_OfferKeyPressEvent;
    PFNC_FI_SETINPUTTEXT                            pFncFI_SetInputText;
	NO_OPERATOR_NEW_DELETE
};


/*
 *  Structure containing FI_xxx functions used for FlashLite player mouse/stylus input.
 */
struct FI_MouseInputFncs
{
    PFNC_FI_OFFERMOUSEEVENT                         pFncFI_OfferMouseEvent;
    PFNC_FI_DOHITTEST                               pFncFI_DoHitTest;
	NO_OPERATOR_NEW_DELETE
};

/*
 *  Structure containing FI_xxx functions used for FlashLite player sound control.
 */
struct FI_SoundControlFncs
{
    PFNC_FI_SETUPDEVICESOUND                        pFncFI_SetupDeviceSound;
    PFNC_FI_SETUPSTREAMSOUND                        pFncFI_SetupStreamSound;
    PFNC_FI_STREAMSOUNDBUFFERCOMPLETE               pFncFI_StreamSoundBufferComplete;
    PFNC_FI_STREAMSOUNDRESET                        pFncFI_StreamSoundReset;
	PFNC_FI_NOTIFYDEVICESOUNDSTATUS					pFncFI_NotifyDeviceSoundStatus;
	NO_OPERATOR_NEW_DELETE
};


/*
 *  Structure containing FI_xxx functions for vector font support
 */
struct FI_PlayerEmbeddedVectorFontFncs
{
    PFNC_FI_SETVECTORFONTDATA                       pFncFI_SetVectorFontData;
    PFNC_FI_SETVECTORFONTMAP                        pFncFI_SetVectorFontMap;
	PFNC_FI_ADDVECTORFONTFILE						pFncFI_AddVectorFontFile;
	NO_OPERATOR_NEW_DELETE
};

/*
 *  Structure containing FI_xxx functions used for XMLSocket.
 */
struct FI_SocketFncs
{
	PFNC_FI_SOCKETRECV								pFncFI_SocketRecv;
	PFNC_FI_NOTIFYSOCKETSTATUS						pFncFI_NotifySocketStatus;
	PFNC_FI_NOTIFYRESOLVEDADDRESS					pFncFI_NotifyResolvedAddress;
	NO_OPERATOR_NEW_DELETE
};

/*
 *  Structure containing FI_xxx functions used for external image decoding
 */
struct FI_ImageDecodeFncs
{
	PFNC_FI_GETDECODEIMAGEBUFFER					pFncFI_GetDecodeImageBuffer;
	PFNC_FI_NOTIFYIMAGEDECODESTATUS					pFncFI_NotifyImageDecodeStatus;
	NO_OPERATOR_NEW_DELETE
};

/*
 * FEATURE_WEB_BROWSABILITY
 *  Structure containing FI_xxx functions used for Web browsability
 */

struct FI_WebBrowsabilityFncs
{
	PFNC_FI_SETBASEURL								pFncFI_SetBaseURL;
	PFNC_FI_SETSCRIPTACCESS							pFncFI_SetScriptAccess;
	PFNC_FI_GETSCRIPTACCESS							pFncFI_GetScriptAccess;
	PFNC_FI_TCURRENTFRAME							pFncFI_TCurrentFrame;
	PFNC_FI_TCURRENTLABEL							pFncFI_TCurrentLabel;
	PFNC_FI_TGETPROPERTY							pFncFI_TGetProperty;
	PFNC_FI_TSETPROPERTY							pFncFI_TSetProperty;
	PFNC_FI_GETBACKGROUNDCOLOR                      pFncFI_GetBackgroundColor;
	PFNC_FI_CANCONTAINERACCESSMOVIE					pFncFI_CanContainerAccessMovie;
	PFNC_FI_SETCONTAINERSECURITYCONTEXT				pFncFI_SetContainerSecurityContext;
	PFNC_FI_LOADLAYER								pFncFI_LoadLayer;
	PFNC_FI_SETVARIABLE2							pFncFI_SetVariable2;
	PFNC_FI_GETVARIABLE2							pFncFI_GetVariable2;

	PFNC_FI_GETSCALEMODE							pFncFI_GetScaleMode;
	PFNC_FI_SETSCALEMODE							pFncFI_SetScaleMode;
	PFNC_FI_GETALIGNMODE							pFncFI_GetAlignMode;
	PFNC_FI_SETALIGNMODE							pFncFI_SetAlignMode;

	PFNC_FI_GETALLOWFULLSCREEN						pFncFI_GetAllowFullScreen;
	PFNC_FI_SETALLOWFULLSCREEN						pFncFI_SetAllowFullScreen;

	NO_OPERATOR_NEW_DELETE
};

/*
 * FEATURE_EXTERNAL_API
 *  Structure containing FI_xxx functions used for External API.
 */
struct FI_ExternalAPIFncs
{
	PFNC_FI_CALLFUNCTION							pFncFI_CallFunction;
	PFNC_FI_SETRETURNVALUEFROMCONTAINER				pFncFI_SetReturnValueFromContainer;
	NO_OPERATOR_NEW_DELETE
};

struct FI_AsApiFncs
{
	PFNC_FI_SETFLASHVARS							pFncFI_AsApi;
	NO_OPERATOR_NEW_DELETE
};

/*
 * Native video functions
 */
struct FI_NativeVideoFncs
{
	PFNC_FI_SETMAXNATIVEVIDEOFRAMESTOPROCESS		pFncFI_SetMaxVideoFramesToProcess;
};

/*
 *  Structure containing FI_xxx functions used for SWF Information API
 */
struct FI_SwfInfoFncs
{
	PFNC_FI_SWF_GET_INFO							pFncFI_GetSwfInfo;
	PFNC_FI_SWF_TIMESTAMP_INFO						pFncFI_TimeStampToDateTimeInfo;
	NO_OPERATOR_NEW_DELETE
};
/****************************************************************************\
    FI_Fncs structure definitions
\****************************************************************************/
struct FI_Fncs
{
	unsigned short                                  FIVersion;
	unsigned short                                  sizeof_FI_Fncs;
	void*                                           pMM_Reserved;
#if defined USE_ATS_REFERENCE || defined ATSTEST_EXPORTS
	struct		 FI_FlashPlayerControlFncs*         pFIFlashPlayerControlFncs;
	struct FI_URLAccessFncs*                  pFIURLAccessFncs;
	struct FI_DataAccessFncs*				    pFIDataAccessFncs;
	struct		 FI_KeyInputFncs*                   pFIKeyInputFncs;

	struct FI_MouseInputFncs*                 pFIMouseInputFncs;
	struct FI_SoundControlFncs*               pFISoundControlFncs;

	struct FI_DebugFncs*                      pFIDebugFncs;

	struct FI_PlayerEmbeddedVectorFontFncs*   pFIPlayerEmbeddedVectorFontFncs;
	struct FI_SVGFncs*                        pFISVGFncs;
	struct FI_MIMETypeFncs*                   pFIMIMETypeFncs;

	struct FI_MMIFncs*                        pFIMMIFncs;

	struct FI_SocketFncs*						pFISocketFncs;

	struct FI_ImageDecodeFncs*				pFIImageDecodeFncs;

	struct FI_WebBrowsabilityFncs*			pFIWebBrowsabilityFncs;
	struct FI_ExternalAPIFncs*				pFIExternalAPIFncs;
	struct FI_AsApiFncs*						pFIAsApiFncs;

	struct FI_NativeVideoFncs*				pFINativeVideoFncs;
	
	struct FI_SwfInfoFncs*					pFISwfInfoFncs;



#else
	const struct FI_FlashPlayerControlFncs*         pFIFlashPlayerControlFncs;

	const struct FI_URLAccessFncs*                  pFIURLAccessFncs;
	const struct FI_DataAccessFncs*				    pFIDataAccessFncs;

	const struct FI_KeyInputFncs*                   pFIKeyInputFncs;
	const struct FI_MouseInputFncs*                 pFIMouseInputFncs;
	const struct FI_SoundControlFncs*               pFISoundControlFncs;

	const struct FI_DebugFncs*                      pFIDebugFncs;

	const struct FI_PlayerEmbeddedVectorFontFncs*   pFIPlayerEmbeddedVectorFontFncs;
	const struct FI_SVGFncs*                        pFISVGFncs;
	const struct FI_MIMETypeFncs*                   pFIMIMETypeFncs;

	const struct FI_MMIFncs*                        pFIMMIFncs;

	const struct FI_SocketFncs*						pFISocketFncs;

	const struct FI_ImageDecodeFncs*				pFIImageDecodeFncs;

	const struct FI_WebBrowsabilityFncs*			pFIWebBrowsabilityFncs;
	const struct FI_ExternalAPIFncs*				pFIExternalAPIFncs;
	const struct FI_AsApiFncs*						pFIAsApiFncs;
	
	const struct FI_NativeVideoFncs*				pFINativeVideoFncs;

	const struct FI_SwfInfoFncs*					pFISwfInfoFncs;


#endif

	// DO NOT under any circumstances put function pointers in here with #ifdef FEATURE_ around them.
	// If the table size is different because of slightly different compilation options, it ends up crashing
	// somewhere. If the feature is not supported, the structure pointer will remain NULL and the callback
	// will not be made.

	NO_OPERATOR_NEW_DELETE
};



/**********************************************************************************
 *
 * Service Provider/Device Interfaces (SI functions)
 *
 * These functions need to be implemented for a specific device.
 *
**********************************************************************************/

/************************************************************************/
/* Time functions:
 * Note: These functions must be linked in with player.
 */

/* Return current system time in seconds (lower resolution -
 * should be less expensive than SI_GetTime.
 */
extern unsigned long    SI_GetApproxTime (void);


/* Return current system time in milliseconds
 */
extern unsigned long    SI_GetTime (void);

/* Return CPU counter
 */
extern unsigned int SI_GetCPUCounter(void);

/* Initialization of hardware interfaces
 */
extern short SI_InitHardwareInterfaces(void* pEgl, void* pApi);

#ifdef DLL_SINGLE_ENTRYPOINT
/*******************************************************************************************************
    FlashLite interfaces: FI functions
*******************************************************************************************************/
DLLENTRYPOINT_IMPL extern void* FlashLite_GetInterface ( EFlashLiteInterfaceIndex index );
#endif  // DLL_SINGLE_ENTRYPOINT


/************************************************************************/
/* Exported FI_ functions */


/* Function to check validity of an SWF data buffer - can be
 * called before the FlashPlayer is created.
 */
DLLEXPORT extern short					FI_CheckSWFDataBufferValidity (const unsigned char* swfBuffer,
                                                                       unsigned long bufferLength,
																	   void* memoryPool,
																	   unsigned short poolSize);

DLLEXPORT extern short					FI_GetMetaData( const unsigned char* swfBuffer,
													    unsigned long bufferLength,
                                                        struct FI_MetaDataInfo* pMetaInfoStruct
													  );

DLLEXPORT extern short					FI_GetSharedObjectGUID( struct MM_Object* pMMObj,
																const unsigned char *swfBuffer,
																unsigned long bufferLength,
																void* memoryPool,
																unsigned long poolSize,
																struct FI_ReturnString* pGUID
															   );

/* Create a Flash Player
 */
DLLEXPORT extern struct MM_Object*      FI_CreatePlayer (void* memoryPool,
                                                         unsigned long bytes,
                                                         struct SI_HostObject* pHostObj,
                                                         const struct SI_Fncs* pSIFncs,
                                                         unsigned long configFlags);

/* Return version string of Player library */
DLLEXPORT extern const char*            FI_GetVersion (void);


/* Return display format of Player library */
DLLEXPORT extern unsigned short   FI_GetDisplayFormat (struct MM_Object* pMMObj);


/************************************************************************/
/* FI_ functions accessible through FI_Fncs struct */


/************************************************************************\
    FI_xxx Function for Flash Lite player control
\************************************************************************/

/* Destroy a Flash Player
 */
DLLEXPORTFNI extern void                FI_DestroyPlayer        (struct MM_Object* pMMObj);

/* Function to specify the viewing area (screen size)
 */
DLLEXPORTFNI extern short      			FI_SetDisplayRect       (struct MM_Object* pMMObj,
                                                                 const struct FI_Rect* rect);

/* Function to set the frame buffer to be rendered to
 */
DLLEXPORTFNI extern short      			FI_SetFrameBuffer       (struct MM_Object* pMMObj,
                                                                 unsigned char* bufferPtr,
                                                                 unsigned short width,
                                                                 unsigned short height,
                                                                 unsigned short pitch,
																 unsigned short lDisplayFormat);

DLLEXPORTFNI extern short				FI_ConfigureHardwareRendering(struct MM_Object*,
																	  struct FI_HardwareConfig* config);

// ACHW
DLLEXPORTFNI extern short               FI_SetHardwareFrameBuffer(struct MM_Object* pMMObj,
                                                                  FI_Display  display,
                                                                  struct FI_Surface* surface,
                                                                  FI_Context  context,
                                                                  unsigned short width,
                                                                  unsigned short height);

/* Function to set rendring hint to use hardware or software
 */
DLLEXPORTFNI extern void				FI_SetRenderHint		(struct MM_Object* pMMObj,
																 enum FI_RenderHint renderHint);

/* Function to get rendring mode to use hardware or software
 */
DLLEXPORTFNI extern enum FI_RenderMode		FI_GetRenderMode		(struct MM_Object* pMMObj);

/* device memory limitation set/get interface
 */
DLLEXPORTFNI extern void				FI_SetHardwareDeviceLimit	(struct MM_Object* pMMObj, enum FI_HardwareDeviceLimitIndex, long value);
DLLEXPORTFNI extern long				FI_GetHardwareDeviceLimit	(struct MM_Object* pMMObj, enum FI_HardwareDeviceLimitIndex);
// ACHW

/* Function to set the SWF data to be played back
 */
DLLEXPORTFNI extern unsigned short      FI_SetSWFDataBuffer     (struct MM_Object* pMMObj,
                                                                 const char* swfURL,
                                                                 const unsigned char* swfBuffer,
                                                                 unsigned long bufferLength,
                                                                 unsigned short inlineContent,
																 unsigned short bufferMode,
																 enum FI_SecuritySandbox swfSandbox);
/* Set a pre-installed Actionscript class
 */
DLLEXPORTFNI unsigned short FI_SetPreInstalledASClass(struct MM_Object* pMMObj,
													  const unsigned char * pFontData,
													  unsigned long dataSize,
													  unsigned short bIsNativeLibrary);

/* Specifies a region of the display rect that the player will only
 * render to.
 */
DLLEXPORTFNI extern short				FI_SetClippingRect      (struct MM_Object* pMMObj,
                                                                 const struct FI_Rect * rect);

/* Marks a region of the display rect as requiring a render update. */
DLLEXPORTFNI extern short				FI_SetDirtyRect         (struct MM_Object* pMMObj,
                                                                 const struct FI_Rect * rect,
																 unsigned short onCurrentFrame);

/* Stores the bounds of the updated display area after a call to
 * FI_DoPlay() in the provided struct. If the dirty area is empty,
 * FI_GetDirtyRect() returns FALSE and the rect bounds will be undefined.
 */
DLLEXPORTFNI extern unsigned short      FI_GetDirtyRect         (struct MM_Object* pMMObj,
                                                                 struct FI_Rect* dirtyRect);

DLLEXPORTFNI extern unsigned short		FI_GetNumberOfSubDirtyRects (struct MM_Object* pMMObj);
DLLEXPORTFNI extern unsigned short		FI_GetSubDirtyRect		(struct MM_Object* pMMObj,
  																 unsigned int index,
                                                                   struct FI_Rect* dirtyRect);
    

/* This function is used to inform fullscreen status to the core , by the platform 
whenever player enters or exits fullscreen
*/
DLLEXPORTFNI extern void				FI_NotifyFullScreenStatus(struct MM_Object* pMMObj,
                                                                 int flag);
/* Get the dimensions of the Flash movie. If the movie is valid,
 * the dimensions will be set. Otherwise, the dimensions will be (0,0).
 */
DLLEXPORTFNI extern void                FI_GetSWFDimensions     (struct MM_Object* pMMObj,
                                                                 unsigned short * width,
                                                                 unsigned short * height);


/* For animation playback - can be used in a while loop. This
 * function takes care of drawing to the frame buffer as well.
 */
DLLEXPORTFNI extern unsigned short      FI_DoPlay               (struct MM_Object* pMMObj,
                                                                 unsigned short forceRender);


/* Variable to be set by host application for interrupting
 * rendering.
 */
DLLEXPORTFNI extern short *             FI_GetInterruptVariable (struct MM_Object* pMMObj);


/* Turn looping on/off
 */
DLLEXPORTFNI extern void                FI_SetLooping           (struct MM_Object* pMMObj, short loop);
/* Set the background color
 */
DLLEXPORTFNI extern void                FI_SetBackgroundColor   (struct MM_Object* pMMObj, short red,
                                                                 short green, short blue);

DLLEXPORTFNI extern void				FI_SetBackgroundAlpha	(struct MM_Object* pMMObj,
																 unsigned short alpha,
																 unsigned short blendWithBackground);


/* Get the current rendering quality (0, 1, 2)
 */
DLLEXPORTFNI extern short               FI_GetQuality           (struct MM_Object* pMMObj);

/* Set the rendering quality (0, 1, 2)
 */
DLLEXPORTFNI extern void                FI_SetQuality           (struct MM_Object* pMMObj, short quality);

/* Functions to pause and resume the player. After calling
 * FI_Resume, the next call to FI_DoPlay, will cause playback to
 * resume from where the movie had been paused.
 */
DLLEXPORTFNI extern void                FI_Pause                (struct MM_Object* pMMObj);


/* Functions to pause and resume the player. After calling
 * FI_Resume, the next call to FI_DoPlay, will cause playback to
 * resume from where the movie had been paused.
 */
DLLEXPORTFNI extern void                FI_Resume               (struct MM_Object* pMMObj);

/* Function to stop the player on the current frame. The next call
 * to FI_DoPlay, will cause playback to start from the start of
 * the movie.
 */
DLLEXPORTFNI extern void                FI_Stop                 (struct MM_Object* pMMObj);

/*
 * This function is called by the host to update the status of the video object
 */
DLLEXPORTFNI extern void                FI_NotifyVideoStatus    (struct MM_Object* pMMObj, unsigned long videoID, int severity, struct FI_Text* pRetCode);

/* Check if the movie is playing
 */
DLLEXPORTFNI extern unsigned short      FI_IsPlaying            (struct MM_Object* pMMObj);

/* Function to return the current frame number the playHead of the
 * movie is currently on.
 */
DLLEXPORTFNI extern unsigned short      FI_FrameNumber          (struct MM_Object* pMMObj);

/* Function to return the the number of frames in the movie being
 * played back.
 */
DLLEXPORTFNI extern unsigned short      FI_NumberOfFrames       (struct MM_Object* pMMObj);

#ifdef FLASH_STAGECRAFT
/* Function to return the the number of frames that have been processed as a result
 * of the expiration of the frame delay time.  Includes "skipped" frames that are
 * processed but never rendered in order to maintain sync with audio playback.  This
 * is intended as a good metric for calculating the frames-per-second of a movie.
 */
DLLEXPORTFNI extern unsigned long       Stagecraft_FI_TotalFramesAdvanced(struct MM_Object* pMMObj);

/* Returns frame interval in milliseconds of the Flash movie at the root level, which is
 * also the rate at which all sub-movies are played.  Use this function to determine when
 * the frame interval requested by SI_UpdateFrameInterval is faster than the actual
 * Flash frame rate, which occurs when A/V playback requires more frequent calls to
 * FI_DoPlay().
 */
DLLEXPORTFNI extern unsigned long       Stagecraft_FI_GetRootLevelFrameInterval(struct MM_Object* pMMObj);


DLLEXPORTFNI extern unsigned long Stagecraft_FI_SetRootLevelFrameInterval(struct MM_Object* pMMObj ,int frame);

#endif // #ifdef FLASH_STAGECRAFT


/* return whether content allows panning/zooming (ie. use of
 * FI_Translate and FI_Scale).
 */
DLLEXPORTFNI extern unsigned short      FI_GetZoomAndPanEnabled(struct MM_Object* pMMObj);

/* Apply a rotation to the user transform.
 */
DLLEXPORTFNI extern unsigned short      FI_Rotate (struct MM_Object* pMMObj,
                                                   long degrees,        /* 16:16 fixed point value */
                                                   long xorigin,        /* 16:16 fixed point value */
                                                   long yorigin);       /* 16:16 fixed point value */


/* Apply a translation to the user transform.
 */
DLLEXPORTFNI extern unsigned short      FI_Translate (struct MM_Object* pMMObj,
                                                      long dx,          /* 16:16 fixed point value */
                                                      long dy);         /* 16:16 fixed point value */


/* Apply a uniform scale to the user transform.
 */
DLLEXPORTFNI extern unsigned short      FI_Scale (struct MM_Object* pMMObj,
                                                  long scale,           /* 16:16 fixed point value */
                                                  long xorigin,         /* 16:16 fixed point value */
                                                  long yorigin);        /* 16:16 fixed point value */


/* Reset the user transform.
 */
DLLEXPORTFNI extern void                FI_ResetTransform (struct MM_Object* pMMObj);

DLLEXPORTFNI extern unsigned short 		FI_ConvertToUserSpace (struct MM_Object* pMMObj,
															   long screenx,
															   long screeny,
															   long *userx,
															   long *usery);
DLLEXPORTFNI extern unsigned short		FI_ConvertToUserSpaceF (struct MM_Object* pMMObj,
																float screenx,
																float screeny,
																float *userx,
																float *usery);
DLLEXPORTFNI extern unsigned short		FI_ConvertToViewportSpace (struct MM_Object* pMMObj,
																   long userx,
																   long usery,
																   long *screenx,
																   long *screeny);
DLLEXPORTFNI extern unsigned short		FI_ConvertToViewportSpaceF (struct MM_Object* pMMObj,
																	float userx,
																	float usery,
																	float *screenx,
																	float *screeny);


DLLEXPORTFNI extern unsigned short		FI_NumberOfVideoOverlayRects(struct MM_Object* pMMObj);

DLLEXPORTFNI extern unsigned short		FI_GetVideoOverlayRects(struct MM_Object* pMMObj,
																struct FI_Rect* rects,
																unsigned short rectsLen);
																


/************************************************************************\
    FI_xxx Function for Flash Lite player URL stream
\************************************************************************/

DLLEXPORTFNI extern struct FI_NetworkBufferInfo*    FI_URLStreamOpened (struct MM_Object* pMMObj,
																		unsigned short streamId,
																		struct FI_URLStreamHeaderInfo* pHeaders,
																		short* pErrorCode);

DLLEXPORTFNI extern void                            FI_URLStreamSetFinalURL (struct MM_Object* pMMObj,
																			 unsigned short streamId,
																			 const char* finalURL,
																			 enum FI_SecuritySandbox swfSandbox);

DLLEXPORTFNI extern struct FI_NetworkBufferInfo*    FI_URLStreamDataLoaded (struct MM_Object* pMMObj,
                                                                            unsigned short streamId,
                                                                            struct FI_NetworkBufferInfo* pNetBuf);

DLLEXPORTFNI extern void                            FI_URLStreamClosed (struct MM_Object* pMMObj,
                                                                        unsigned short streamId,
                                                                        unsigned short closedStatusCode,
																		unsigned int httpStatusCode);
#ifdef _LG_HTTP_RETRY_CONTROL_
DLLEXPORTFNI extern void                            FI_URLStreamSetErrorStatus (struct MM_Object* pMMObj, 
                                                                        unsigned short streamId,
                                                                        bool bCurlStatusCode);
DLLEXPORTFNI extern void                            FI_URLStreamSetConnectionStatus (struct MM_Object* pMMObj, 
                                                                        unsigned short streamId,
                                                                        bool bConnectionStatus);
#endif //_STOP_EVENT_

DLLEXPORTFNI unsigned short							FI_SetURLStreamDataBuffer	(struct MM_Object* pMMObj,
																		unsigned short streamId,
																		struct FI_URLStreamHeaderInfo* pHeaders,
																		const unsigned char* dataBuffer,
																		unsigned long bufferLength);

/*
 * Callback function for SI_CleanUpSharedObjects.
 */
DLLEXPORTFNI extern void FI_NotifySharedObjectStatus	(struct MM_Object* pMMObj,
														 unsigned long requestId,
														 unsigned long dataSize,
														 short status);

/************************************************************************\
    FI_xxx Functions for Flash Lite player text input
\************************************************************************/

/* Function to be called by host application to set the text input
 * by the user (using an FEP). This is generally in response to
 * a previous call to SI_GetInputText.
 */
DLLEXPORTFNI extern void                FI_SetInputText         (struct MM_Object* pMMObj,
                                                                 const struct FI_Text* inputText);


/* Function to be called by host application to cancel the text input
 * by the user (using an FEP). This is generally in response to
 * a previous call to SI_GetInputText.
 */
DLLEXPORTFNI extern void                FI_CancelInputText      (struct MM_Object* pMMObj);



/************************************************************************\
    FI_xxx Functions for Flash Lite player key event
\************************************************************************/

/* Function to send down, press and release key events to the player.
 * Keys supported are: 0-9, # and *, Select, Directional keys and soft
 * keys. The valid keyCodes are:
 *          # key:      35
 *          * key:      42
 *          0-9 keys:   48-57
 *          Select:     13
 *          Up:         26      only for two way directional navigation
 *          Down:       18      only for two way directional navigation
 *          Left:       1       only for four way directional navigation
 *          Right:      2       only for four way directional navigation
 *          Up:         14      only for four way directional navigation
 *          Down:       15      only for four way directional navigation
 *          PageUp:     16      used when soft keys are remapped
 *          PageDown:   17      used when soft keys are remapped
 *
 * The event code is for either the down, press (hold), or release event.
 * Valid values for eventCode are:
 *          down:       0x10
 *          up:         0x11
 *          press:      0x12
 */
DLLEXPORTFNI extern unsigned short      FI_OfferKeyEvent        (struct MM_Object* pMMObj,
                                                                 const struct FI_KeyId* keyCode,
                                                                 unsigned short eventCode);


/* Function to send key events to the player. This is for devices that
 * don't support sending down, press and release events separately.
 * The keyCodes are the same as for FI_OfferKeyEvent.
 */
DLLEXPORTFNI extern unsigned short      FI_OfferKeyPressEvent   (struct MM_Object* pMMObj,
                                                                 const struct FI_KeyId* keyCode);




/************************************************************************\
    FI_xxx Functions for Flash Lite player mouse/stylus event
\************************************************************************/
/* Returns information, if the stylus/mouse click was on any selectable button
 */
DLLEXPORTFNI extern unsigned short      FI_DoHitTest            (struct MM_Object* pMMObj,
                                                                 unsigned short xPos,
                                                                 unsigned short yPos);



/* Sends the position of the cursor and pointer event type
 * to the player. Valid values for mouseEvent are:
 *      mouseDown:  0x20
 *      mouseUp:    0x21
 */
DLLEXPORTFNI extern unsigned short      FI_OfferMouseEvent      (struct MM_Object* pMMObj,
                                                                 unsigned short xMousePos,
                                                                 unsigned short yMousePos,
                                                                 unsigned short eventCode);



/************************************************************************\
    FI_xxx Functions for Flash Lite player sound control
\************************************************************************/
/* Function to inform the player of the sound file formats (MIDI, MFi,
 * SMAF, etc.) supported by the device. Sound data in these formats
 * may later be sent to the device through calls to SI_PlaySound or
 * SI_CacheSound.
 */
DLLEXPORTFNI extern void                FI_SetupDeviceSound     (struct MM_Object* pMMObj,
                                                                 unsigned short numFormats,
                                                                 const char* formatInfo[],
                                                                 unsigned short mustbeCached);


/* Function to inform the player of the status of a device sound. Possible
 * values for the status flag are FI_DeviceSoundStarted,
 * FI_DeviceSoundComplete, and FI_DeviceSoundFailed.
 */
DLLEXPORTFNI extern void				FI_NotifyDeviceSoundStatus	(struct MM_Object* pMMObj,
																	 unsigned short status);


/* Streaming sound support functions */

/* Function to inform the player that the device supports the playback
 * of buffers of PCM sound data and of the compressed streaming sound data
 * formats (MP3, ADPCM, etc.) supported by the device. The Flash Player
 * may later request that the device create instances of de-compressors
 * for these sound formats (by a call to SI_CreateSoundDecompressor) and
 * send buffers of PCM data by calls to SI_PlayStreamSoundBuffer.
 */
DLLEXPORTFNI extern unsigned short      FI_SetupStreamSound     (struct MM_Object* pMMObj,
                                                                 struct FI_StreamSoundInfo* pSoundInfo,
                                                                 struct FI_StreamSoundSetupInfo* pSetupInfo);

/* Function to inform the player that the device has completed playback
 * of the buffer of PCM sound data previously sent by a call to
 * SI_PlayStreamSoundBuffer.
 */
DLLEXPORTFNI extern void                FI_StreamSoundBufferComplete (struct MM_Object* pMMObj);


/* Function to inform the player that the device has had to reset its sound
 * interface due to buffer under-run conditions or otherwise lost track of
 * some of the buffers previously sent by calls to SI_PlayStreamSoundBuffer.
 */
DLLEXPORTFNI extern void                FI_StreamSoundReset     (struct MM_Object* pMMObj);


DLLEXPORTFNI extern void                FI_SetNativeCodePage    (struct MM_Object* pMMObj,
                                                                 FI_CodePage);

DLLEXPORTFNI extern void                FI_SetTextFieldBoundaryBehavior(struct MM_Object* pMMObj,
																 unsigned long behavior);

/*
 * MIME type related FI functions
 */
DLLEXPORTFNI extern unsigned short FI_SetupDeviceImage          (struct MM_Object* pMMObj,
                                                                 unsigned short numFormats,
                                                                 const char* formatInfo[]);
DLLEXPORTFNI extern unsigned short FI_SetupDeviceVideo          (struct MM_Object* pMMObj,
                                                                 unsigned short numFormats,
                                                                 const char* formatInfo[]);

// Functions for FEATURE_SVG
DLLEXPORTFNI unsigned short FI_SetSVGDataBuffer (struct MM_Object* pMMObj, const char* svgURL, const unsigned char* svgBuffer, unsigned long bufferLength);
DLLEXPORTFNI void           FI_GetSVGDimensions (struct MM_Object* pMMObj, unsigned short * width, unsigned short * height);
DLLEXPORTFNI unsigned short	FI_SetSVGViewportSize(struct MM_Object* pMMObj, unsigned short width, unsigned short height);
// End functions for FEATURE_SVG

/* Functions for embedded vector fonts */
DLLEXPORTFNI unsigned short FI_SetVectorFontData (struct MM_Object* pMMObj, const unsigned char * pFontData, unsigned long dataSize);
DLLEXPORTFNI void           FI_SetVectorFontMap  (struct MM_Object* pMMObj, const char* sansName, const char* serifName, const char* typewriterName);
DLLEXPORTFNI unsigned short FI_AddVectorFontFile (struct MM_Object* pMMObj,  const char *filename);


/*
 * Functions for XMLSocket
 */

DLLEXPORTFNI short FI_SocketRecv			(struct MM_Object* pMMObj, unsigned long socketId, struct FI_NetworkBufferInfo* pDataBuffer, struct FI_NetworkBufferInfo** pNextRecvBuffer);
DLLEXPORTFNI short FI_NotifySocketStatus	(struct MM_Object* pMMObj, unsigned long socketId, unsigned short status, short errorCode);
DLLEXPORTFNI short FI_NotifyResolvedAddress	(struct MM_Object* pMMObj, unsigned long socketId, const struct FI_IpInfo *info);

DLLEXPORTFNI struct FI_DecodeImageInfo* FI_GetDecodeImageBuffer(struct MM_Object* pMMObj, unsigned long pImageId, const struct FI_ImageInfo* pImageInfo);
DLLEXPORTFNI short FI_NotifyImageDecodeStatus							   (struct MM_Object* pMMObj, unsigned long pImageId, short error);


/*
* Functions for Web browsability
*/
#ifdef FEATURE_WEB_BROWSABILITY
DLLEXPORTFNI extern void		    FI_SetBaseURL						(struct MM_Object* pMMObj,
																		 const char* baseURL);

DLLEXPORTFNI extern void			FI_SetScriptAccess					(struct MM_Object* pMMObj,
																		 int pAccessLevel);

DLLEXPORTFNI extern int				FI_GetScriptAccess					(struct MM_Object* pMMObj);

DLLEXPORTFNI extern int				FI_TCurrentFrame					(struct MM_Object* pMMObj,
																		 const char* target,
																		 const struct FI_BrowserURLInfo* pURLInfoIn);

DLLEXPORTFNI extern void*			FI_TCurrentLabel					(struct MM_Object* pMMObj,
																		 const char* pTargetIn,
																		 const struct FI_BrowserURLInfo* pBrowserInfoIn,
																		 struct FI_AllocatorWrapper* currentLabel);

DLLEXPORTFNI extern void*			FI_TGetProperty						(struct MM_Object* pMMObj,
																		 const struct FI_TargetPropertyInfo* ptargetInfoIn,
																		 const struct FI_BrowserURLInfo* pBrowserInfoIn,
																		 struct FI_AllocatorWrapper* propertyValue);

DLLEXPORTFNI extern int				FI_TSetProperty						(struct MM_Object* pMMObj,
																		 const struct FI_TargetPropertyInfo* ptargetInfoIn,
																		 const struct FI_BrowserURLInfo* pBrowserInfoIn,
																		 const char* value);
DLLEXPORTFNI extern int             FI_GetBackgroundColor				(struct MM_Object* pMMObj, struct FI_Color *returnBgColor);

DLLEXPORTFNI extern int			    FI_CanContainerAccessMovie			(struct MM_Object *pMMObj,
																		 const struct FI_BrowserURLInfo* pURLInfoIn,
																		 int bAllowLocalUntrusted);

DLLEXPORTFNI extern void			FI_SetContainerSecurityContext		(struct MM_Object * pMMObj,
																		 const struct FI_BrowserURLInfo* pURLInfoIn);

DLLEXPORTFNI extern int				FI_SetVariable2						(struct MM_Object *pMMObj,
																		 const char* pVariableNameIn,
																		 const char* pVariableValueIn,
																		 const struct FI_BrowserURLInfo* pBrowserInfoIn);
DLLEXPORTFNI extern void*			FI_GetVariable2						(struct MM_Object *pMMObj,
																		 const char* pVariableNameIn,
																		 const struct FI_BrowserURLInfo* pBrowserInfoIn,
																		 struct FI_AllocatorWrapper* hostAllocWrapper);

DLLEXPORTFNI extern void			FI_SetScaleMode						(struct MM_Object * pMMObj,
																		 int scaleModeValue);

DLLEXPORTFNI extern int				FI_GetScaleMode						(struct MM_Object * pMMObj);

DLLEXPORTFNI extern void			FI_SetAlignMode						( struct MM_Object * pMMObj, int alignMode );

DLLEXPORTFNI extern int				FI_GetAlignMode						( struct MM_Object * pMMObj );

#ifdef FEATURE_FULLSCREEN
DLLEXPORTFNI extern void			FI_SetAllowFullScreen				(struct MM_Object * pMMObj , int allowFullScreen);

DLLEXPORTFNI extern int				FI_GetAllowFullScreen				(struct MM_Object * pMMObj);
#endif //FEATURE_FULLSCREEN

DLLEXPORTFNI extern void			FI_LoadLayer						(struct MM_Object* pMMObj,
																		const char*swfURL,
																		int layer,
																		struct FI_BrowserURLInfo* pURLInfo);
#endif//FEATURE_WEB_BROWSABILITY
/*
* Functions for External API
*/
//#ifdef FEATURE_EXTERNAL_API
DLLEXPORTFNI extern void *		    FI_CallFunction						(struct MM_Object* pMMObj,
																		 const char* request,
																		 struct FI_AllocatorWrapper* allocWrapper);

DLLEXPORTFNI extern void		    FI_SetReturnValueFromContainer		(struct MM_Object* pMMObj,
																		 const char* returnValue);
//#endif//FEATURE_EXTERNAL_API

DLLEXPORTFNI extern void		    FI_SetFlashVars						(struct MM_Object* mm_object,
																		 const char* values,
																		 unsigned short useUTF8);

DLLEXPORTFNI extern void		    FI_SetFlashVars2						(struct MM_Object * mm_object,
																		 const char * strName,
																		 const char * strValue,
																		 unsigned short useUTF8);

/*
 * Functions for SWF Info API
 */

DLLEXPORTFNI extern unsigned short FI_TimeStampToDateTimeInfo(struct MM_Object* pMMObj,
                                          const struct FI_LargeInteger* pTimeStamp,
                                          struct FI_DateTimeInfo* pDateTimeInfo);

DLLEXPORT extern short FI_GetSwfInfo (const unsigned char* swfBuffer, unsigned long bufferLength,
                                           void* memoryPool, unsigned short poolSize,
                                           struct FI_SWFInfo* swfInfo);


DLLEXPORT short FI_SetMaxVideoFramesToProcess(struct MM_Object* pMMObj,
												   unsigned int numFrames);

#ifdef __cplusplus
}
#endif

#endif /* __FLASHLITEINTERFACE_H__ */
