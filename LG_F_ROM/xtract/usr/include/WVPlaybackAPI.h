//
// Declarations for Widevine Playback API Abstract Interface
//
// Copyright 2009-2010 Widevine Technologies, Inc.
//

#ifndef __WV_PLAYBACK_API_H__
#define __WV_PLAYBACK_API_H__

#include <string>
#include <vector>
#include "WVStreamControlAPI.h"

class WVPlaybackAPI {
 public:
  enum WVEventType {
    WV_Event_Playing,
    WV_Event_FastForward,
    WV_Event_Rewind,
    WV_Event_Paused,
    WV_Event_Stopped,
    WV_Event_Seek,
    WV_Event_Underflow,
    WV_Event_EndOfStream
  };

  //
  // Callback Function Datatypes
  //
  typedef void (*WVQualityCallback)(size_t numLevels, size_t currentLevel, bool isHD, void* userData);
  typedef void (*WVBandwidthCallback)(unsigned long bytesPerSecond, void* userData);
  typedef void (*WVEventCallback)(WVEventType eventType,
                                  const std::string& time, void* userData);
  typedef void (*WVErrorCallback)(WVStatus errorCode, void* userData);
  typedef void (*WVTimeCallback)(const std::string& time, void* userData);

  //
  // Object Initialization Methods
  //
  static WVPlaybackAPI* Instance();

  virtual WVStatus Initialize() = 0;
  void Terminate();

  //
  // Configuration Methods
  //
  virtual WVStatus ConfigureCredentials(const WVCredentials& credentials) = 0;
  virtual WVStatus ConfigureBandwidthCheck(const std::string& url,
                                           unsigned long intervalSeconds) = 0;
  virtual WVStatus ConfigureBufferSize(unsigned long newSize) = 0;
  virtual WVStatus ConfigureProxy(const WVProxySettings& proxySettings) = 0;

  virtual WVStatus ConfigureQualityCallback(WVQualityCallback callback, void* userData) = 0;
  virtual WVStatus ConfigureBandwidthCallback(WVBandwidthCallback callback, void* userData) = 0;
  virtual WVStatus ConfigureEventCallback(WVEventCallback callback, void* userData) = 0;
  virtual WVStatus ConfigureErrorCallback(WVErrorCallback callback,
                                          unsigned long warningTimeout, void* userData) = 0;
  virtual WVStatus ConfigureTimeCallback(WVTimeCallback callback,
                                         const std::string& format, void* userData) = 0;

  //
  //  Session Establishment Methods
  //
  virtual WVStatus OpenURL(WVSession*& session, const std::string& url) = 0;
  virtual WVStatus Close(WVSession*& session) = 0;

  //
  //  Transport Control Methods
  //
  virtual WVStatus Play(WVSession* session, float speedRequested, float* speedUsed) = 0;
  virtual WVStatus Pause(WVSession* session) = 0;
  virtual WVStatus Stop(WVSession* session) = 0;
  virtual WVStatus Seek(WVSession* session, const std::string& time) = 0;

  //
  // Information Methods
  //

  //
  // METHOD: GetVersion
  //
  // Query the version number of the Widevine library
  //
  // Parameters:
  //     void
  //
  // Returns:
  //     Widevine library build version string
  //
  static std::string GetVersion();

  //
  // METHOD: UniqueID
  //
  // Get the Widevine unique identifier for this device
  //
  // For CE devices, this will be from the Widevine Keybox.
  // For Desktop platforms, it will be a persistent GUID
  //     generated by Widevine.
  // For iPhone, it will be the unit's serial number.
  //
  // Parameters:
  //     None
  //
  // Returns:
  //     On success, string containing the unique ID of the device
  //     On error, empty string is returned
  //
  static std::string UniqueID();

  // format can be "npt" for times of the form "hh:mm:ss.msec"
  // or "sec" for times of the form "seconds.msec"
  virtual std::string GetDuration(WVSession* session, const std::string& format) = 0;


  // METHOD: GetCurrentBandwidth
  //
  // This method retrieves information about the adaptive streaming bandwidth.
  // for the media stream that was setup on the specified session.
  //
  // Parameters:
  //    [in] session - The session to query
  //
  //    [out] bandwidth - The currently observered average network throughput
  //                      in bits per second
  //
  // Returns:
  //     WV_Status_OK on success, otherwise one of the WVStatus values
  //     indicating the specific error.
  //
  WVStatus GetCurrentBandwidth(WVSession* session, unsigned long& bandwidth);


  // METHOD: GetAdaptiveBitrateInfo
  //
  // This method provides adaptive streaming metrics for the media stream
  // that was set up on the specified session.  This information may be used,
  // for example, to display bit rate metering information on the UI to
  // indicate quality of service.
  //
  // Example: if the multi-rate file  contains low, mid and high rate encoded
  // streams at 700kb, 1.5Mb and 2.5Mb respectively and the lowest bit rate
  // file is currently being streamed, then on return, encodedRates =
  // {700000, 1500000, 2500000}, ratesReturned = 3 and currentIndex = 0.
  //
  // Parameters:
  //    [in] session - The session to query
  //
  //    [out] encodedRates - The bit rate of each separate encoding of the
  //          asset in the multi-rate encoded file, in bits per second.
  //
  //    [out] currentIndex  The index of the rate in encodedRates that is
  //          currently streaming. If none, currentIndex == ratesReturned
  //
  //   Returns:
  //      WV_Status_OK on success, otherwise one of the WVStatus values
  //      indicating the specific error.
  //
  WVStatus GetAdaptiveBitrateInfo(WVSession* session, std::vector<unsigned long>& encodedRates, size_t& currentIndex);

 protected:
  // Singleton constructor/destructor are protected
  WVPlaybackAPI() {}
  virtual ~WVPlaybackAPI() {}

 private:
  static WVPlaybackAPI* sInstance;
};

#endif
