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

#ifndef _AE_INCLUDE_AEKERNEL_AEKERNEL_H
#define _AE_INCLUDE_AEKERNEL_AEKERNEL_H

#include <ae/IAEModule.h>
#include <ae/AEError.h>

class IAEKernel : public IAEModule
{
public:
    static IAEKernel * GetKernel();
        ///<gets the Adobe Electronics Kernel
        ///
        /// This function gets the interface handle to the Adobe Electronics Kernel.  This function may
        /// be called from any thread at any time and always returns a thread-safe non-null IAEKernel pointer,
        ///  provided that InitializeAEKernel() has been called once in your process (typically from function main()).
        /// @return the IAEKernel interface handle to the Adobe Electronics Kernel
        /// @see InitializeAEKernel

    inline static IAEKernel * InitializeAEKernel() { return InitializeAEKernel(0, NULL); }
        ///<initializes the Adobe Electronics Kernel without command line parameters
        ///
        /// This parameterless version of InitializeAEKernel() initializes the
        /// AEKernel without command line parameters.  Only one thread in your process may call this
        /// function once.  Once InitializeAEKernel() is called, any thread may call IAEKernel::GetKernel()
        /// at any time to get the IAEKernel interface pointer.
        /// @return the IAEKernel interface
        /// @note Only one thread in your process may call InitializeAEKernel().  From that point
        /// forward the IAEKernel interface is thread safe and any thread may use the IAEKernel
        /// interface handle returned.
        /// @return the IAEKernel interface handle to the initialized Adobe Electronics Kernel
        /// @note Typically you do not call this function unless you are function main() of your process.
        /// @see InitializeAEKernel(int argc, const char ** argv)
        /// @see UninitializeAEKernel()
        /// @see IAEKernel::GetKernel()
         
    static IAEKernel * InitializeAEKernel(int argc, const char ** argv);
        ///<initializes the Adobe Electronics Kernel
        ///
        /// This function initializes the Adobe Electronics Kernel (AEKernel).  Your process argc and argv
        /// 
        /// <p>This function is used as follows:
        /// <pre>IAEKernel * pKernel = IStagecraft::InitializeAEKernel(argc, argv);</pre>
        /// @param argc The argc argument count as passed in to function main.
        /// @param argv The argv parameter as passed in to function main.
        /// @return the IAEKernel interface handle to the initialized Adobe Electronics Kernel
        /// @note Only one thread in your process may call InitializeAEKernel().  From that point
        /// forward the IAEKernel interface is thread safe and any thread may use the IAEKernel
        /// interface handle returned.  Any thread may also from that point onward call IAEKernel::GetKernel()
        /// at any time to obtain the kernel interface handle.
        /// @note Only one thread in your process may call InitializeAEKernel().  From that point
        /// forward the IAEKernel interface is thread safe and any thread may use the IAEKernel.
        /// interface handle returned.
        /// @note Typically you do not call this function unless you are function main() of your process.
        /// @see UninitializeAEKernel()
        /// @see IAEKernel::GetKernel()
        
    static void UninitializeAEKernel();
        ///<uninitializes the Adobe Electronics Kernel
        ///
        /// This function uninitializes the Adobe Electronics Kernel (AEKernel).  Prior to calling UninitializeAEKernel()
        /// the client must release all objects and resources created through the IAEKernel interface.
        /// UninitializeAEKernel() must be called by the same thread that called InitializeAEKernel().  Once
        /// UninitializeAEKernel(), the IAEKernel pointer returned by InitializeAEKernel() is no longer valid
        /// and must not be used.
        /// 
        /// @note Only one thread in your process may call UninitializeAEKernel() - the same thread
        /// that called InitializeAEKernel().
        /// @note Typically you do not call this function unless you are function main() of your process.
        /// @see InitializeAEKernel()
        
public:
    IAEKernel() :   AEERROR_INIT(ThreadAlreadyRunning),
                    AEERROR_INIT(ThreadCreationFailure),
                    AEERROR_INIT(ThreadCantWaitOnSelf), 
                    AEERROR_INIT(ThreadSetPriority),
                    AEERROR_INIT(ThreadGenericError),
                    AEERROR_INIT(ThreadInvalidParameter)         { }

public:
    class CalendarTime
    {
    public:
        CalendarTime() { }
        CalendarTime(u8 second, u8 minute, u8 hour, u8 day, u8 month, u16 year) : m_second(second), m_minute(minute), m_hour(hour), m_day(day), m_month(month), m_year(year) { }
    public:
        u8 m_second;   // 0-59
        u8 m_minute;   // 0-59
        u8 m_hour;     // 0-23
        u8 m_day;      // 1-31
        u8 m_month;    // 0-11
        u16 m_year;    
    };

    class Time
    {
    public:
        inline Time()                                       { m_nNanoseconds = 0; }
        inline Time(const Time & that)                      { m_nNanoseconds = that.m_nNanoseconds; }
    public:
        inline s64 GetNanoseconds() const                   { return m_nNanoseconds; }
        inline s64 GetMicroseconds() const                  { return (m_nNanoseconds / 1000); }
        inline s64 GetMilliseconds() const                  { return (m_nNanoseconds / 1000000); }
        inline s64 GetSeconds() const                       { return (m_nNanoseconds / 1000000000); }
        inline void SetNanoseconds(s64 nNanoseconds)        { m_nNanoseconds = nNanoseconds; }
        inline void SetMicroseconds(s64 nMicroseconds)      { m_nNanoseconds = nMicroseconds * 1000; }
        inline void SetMilliseconds(s64 nMilliseconds)      { m_nNanoseconds = nMilliseconds * 1000000; }
        inline void SetSeconds(s64 nSeconds)                { m_nNanoseconds = nSeconds * 1000000000; }
        inline void SetForever()                            { m_nNanoseconds = AES64CONST(0x7FFFFFFFFFFFFFFF); }
    public:
        inline bool operator == (const Time & that) const   { return (m_nNanoseconds == that.m_nNanoseconds); }
        inline bool operator <= (const Time & that) const   { return (m_nNanoseconds <= that.m_nNanoseconds); }
        inline bool operator >= (const Time & that) const   { return (m_nNanoseconds >= that.m_nNanoseconds); }
        inline bool operator != (const Time & that) const   { return (m_nNanoseconds != that.m_nNanoseconds); }
        inline bool operator <  (const Time & that) const   { return (m_nNanoseconds <  that.m_nNanoseconds); }
        inline bool operator >  (const Time & that) const   { return (m_nNanoseconds >  that.m_nNanoseconds); }
        inline bool operator == (s64 nNanoseconds)  const   { return (m_nNanoseconds == nNanoseconds);        }
        inline bool operator <= (s64 nNanoseconds)  const   { return (m_nNanoseconds <= nNanoseconds);        }
        inline bool operator >= (s64 nNanoseconds)  const   { return (m_nNanoseconds >= nNanoseconds);        }
        inline bool operator != (s64 nNanoseconds)  const   { return (m_nNanoseconds != nNanoseconds);        }
        inline bool operator <  (s64 nNanoseconds)  const   { return (m_nNanoseconds <  nNanoseconds);        }
        inline bool operator >  (s64 nNanoseconds)  const   { return (m_nNanoseconds >  nNanoseconds);        }
        inline bool IsForever()                     const   { return (m_nNanoseconds == AES64CONST(0x7FFFFFFFFFFFFFFF)); }
    public:
        inline Time & operator =  (const Time & that)       { m_nNanoseconds = that.m_nNanoseconds; return *this; }
        inline Time & operator += (const Time & that)       { m_nNanoseconds += that.m_nNanoseconds; return *this; }
        inline Time & operator -= (const Time & that)       { m_nNanoseconds -= that.m_nNanoseconds; return *this; }
        inline const Time operator + (const Time & that) const { return Time(m_nNanoseconds + that.m_nNanoseconds); }
        inline const Time operator - (const Time & that) const { return Time(m_nNanoseconds - that.m_nNanoseconds); }
        inline Time & operator =  (s64 nNanoseconds)        { m_nNanoseconds = nNanoseconds; return *this; }
        inline Time & operator += (s64 nNanoseconds)        { m_nNanoseconds += nNanoseconds; return *this; }
        inline Time & operator -= (s64 nNanoseconds)        { m_nNanoseconds -= nNanoseconds; return *this; }
        inline const Time operator + (s64 nNanoseconds)  const { return Time(m_nNanoseconds + nNanoseconds); }
        inline const Time operator - (s64 nNanoseconds)  const { return Time(m_nNanoseconds - nNanoseconds); }
    public:
        inline static const Time Nanoseconds(s64 nNanoseconds)    { return Time(nNanoseconds); }
        inline static const Time Microseconds(s64 nMicroseconds)  { return Time(nMicroseconds * 1000); }
        inline static const Time Milliseconds(s64 nMilliseconds)  { return Time(nMilliseconds * 1000000); }
        inline static const Time Seconds(s64 nSeconds)            { return Time(nSeconds * 1000000000); }
        inline static const Time Forever()                        { return Time(AES64CONST(0x7FFFFFFFFFFFFFFF)); }
    private:
        inline Time(s64 nNanoseconds)                       { m_nNanoseconds = nNanoseconds; }
    private:
        s64 m_nNanoseconds;
    };

    class CountdownTimer
    {
    public:
        CountdownTimer()                { m_stopTime.SetForever();  }
        CountdownTimer(Time duration)   { SetDuration(duration);    }
    public:
        void SetDuration(Time duration) { m_stopTime = duration.IsForever() ? duration : GetKernel()->GetKernelTime() + duration; }
        bool IsExpired()                { return m_stopTime.IsForever() ? false : ((GetKernel()->GetKernelTime() >= m_stopTime) ? true : false); }
        Time GetTimeRemaining()         
        {
            if (m_stopTime.IsForever()) return m_stopTime;
            Time timeNow = GetKernel()->GetKernelTime();
            return ((timeNow >= m_stopTime) ? Time() : (m_stopTime - timeNow));
        }
    private:
        Time m_stopTime;
    };

    class FixedPoint
    {
    public:
        inline FixedPoint()                                         { m_nValue = 0; }
        inline FixedPoint(s16 nNumerator, s16 nDenominator)         { m_nValue = ((nNumerator << 16) / nDenominator); }
        inline FixedPoint(u16 nNumerator, u16 nDenominator)         { m_nValue = ((nNumerator << 16) / nDenominator); }
        inline FixedPoint(s16 nNumerator, u16 nDenominator)         { m_nValue = ((nNumerator << 16) / nDenominator); }
        inline FixedPoint(u16 nNumerator, s16 nDenominator)         { m_nValue = ((nNumerator << 16) / nDenominator); }
        inline FixedPoint(s32 nValue)                               { m_nValue = nValue; }
        inline FixedPoint(const FixedPoint & that)                  { m_nValue = that.m_nValue; }
        inline s32 GetValue() const                                 { return m_nValue; }
		inline		operator s32() const							{ return m_nValue; }
        inline bool operator == (const FixedPoint & that) const     { return (m_nValue == that.m_nValue); }
        inline bool operator == (s32 nValue)  const                 { return (m_nValue == nValue);        }
        inline bool operator <= (const FixedPoint & that) const     { return (m_nValue <= that.m_nValue); }
        inline bool operator >= (const FixedPoint & that) const     { return (m_nValue >= that.m_nValue); }
        inline bool operator != (const FixedPoint & that) const     { return (m_nValue != that.m_nValue); }
        inline bool operator <  (const FixedPoint & that) const     { return (m_nValue <  that.m_nValue); }
        inline bool operator >  (const FixedPoint & that) const     { return (m_nValue >  that.m_nValue); }
        inline bool operator <= (s32 nValue)  const                 { return (m_nValue <= nValue);        }
        inline bool operator >= (s32 nValue)  const                 { return (m_nValue >= nValue);        }
        inline bool operator != (s32 nValue)  const                 { return (m_nValue != nValue);        }
        inline bool operator <  (s32 nValue)  const                 { return (m_nValue <  nValue);        }
        inline bool operator >  (s32 nValue)  const                 { return (m_nValue >  nValue);        }
    public:
        inline FixedPoint & operator =  (const FixedPoint & that)   { m_nValue = that.m_nValue; return *this; }
        inline FixedPoint & operator += (const FixedPoint & that)   { m_nValue += that.m_nValue; return *this; }
        inline FixedPoint & operator -= (const FixedPoint & that)   { m_nValue -= that.m_nValue; return *this; }
        inline FixedPoint   operator +  (const FixedPoint & that) const { return FixedPoint(m_nValue + that.m_nValue); }
        inline FixedPoint   operator -  (const FixedPoint & that) const { return FixedPoint(m_nValue - that.m_nValue); }
        inline FixedPoint & operator =  (s32 nValue)                { m_nValue = nValue; return *this; }
        inline FixedPoint & operator += (s32 nValue)                { m_nValue += nValue; return *this; }
        inline FixedPoint & operator -= (s32 nValue)                { m_nValue -= nValue; return *this; }
        inline FixedPoint   operator +  (s32 nValue)  const         { return FixedPoint(m_nValue + nValue); }
        inline FixedPoint   operator -  (s32 nValue)  const         { return FixedPoint(m_nValue - nValue); }
    private:
        s32 m_nValue;
    };
public:
    class Thread
    {
    public:
        typedef void    (ThreadProc)(Thread * pThread, void * pClientData);
        typedef void    (DetachCleanupProc)(Thread * pThread, void * pClientData);

    public:
        virtual bool    Run(const char * pName, ThreadProc * pThreadProc, void * pClientData) = 0;
        virtual bool    Detach(DetachCleanupProc * pDetachCleanupProc = NULL) = 0;
        virtual bool    WaitUntilFinished(Time timeout = Time::Forever()) = 0;

        virtual bool    SetPriority(u8 nPriority) = 0;
        virtual bool    SetStackSize(u32 nStackSize) = 0;

    public:
        virtual u8      GetPriority() = 0;
        virtual u32     GetStackSize() = 0;
        virtual void    GetName(char * pNameBuffToSet, u32 nBuffLen) = 0;

    protected:
        virtual ~Thread()   { }
    };

	// Base for any class that needs lock/unlock semantics
	class Lockable
	{
	public:
        virtual void Lock() = 0;
        virtual void Unlock() = 0;

    protected:
        virtual ~Lockable() { }
    };

	class Mutex : public Lockable
    {
    public:
        virtual bool TryLock() = 0;
    protected:
        virtual ~Mutex() { }
    };

    class ScopedLock
    {
    public:
        //! A ScopedLock object can be created on the stack. When it's 
        //! instantiated, the mutex is locked. When the ScopedLock goes out 
        //! of scope, it automatically unlocks the mutex. This prevents 
        //! Mutexes from accidentally remaining locked from multiple exit 
        //! paths or thrown exceptions. 
        //!  
        //! \par Example
        //! void foo() 
        //! { 
        //!     mutex->Lock();
        //!     if (...)
        //!         return;     // Oops, mutex still locked.
        //!     ...
        //!     mutex->Unlock();
        //! } 
        //!  
        //! becomes 
        //! void foo() 
        //! { 
        //!     ScopedLock l(mutex);
        //!     if (...)
        //!         return;   // l goes out of scope, mutex unlocked.
        //!     ...
        //!  }
        //! 
        explicit ScopedLock(Lockable *lock) : m_lock(*lock) {m_lock.Lock();}
        explicit ScopedLock(Lockable &lock) : m_lock(lock)  {m_lock.Lock();}
        ~ScopedLock() {m_lock.Unlock();}
    private:
        Lockable &m_lock;
    };

    class Event
    {
    public:
        virtual void Set() = 0;
        virtual bool Wait(Time timeout = Time::Forever()) = 0;
        virtual void Clear() = 0;
        virtual bool IsSet() = 0;
    protected:
        virtual ~Event() { }
    };

    class Message
    {
    public:
        virtual u32 GetID() = 0;
        virtual void * GetData() = 0;
    protected:
        virtual ~Message() { }
    };

    class TimerMessageData
    {
    public:
        virtual void            ReleaseTimerData() = 0;
    public:
        virtual const Time &    GetFireTimeKernel() = 0;
        virtual const Time &    GetFireTimeGMT() = 0;
        virtual u32             GetTimerID() = 0;
    protected:
        virtual ~TimerMessageData() { }
    };

    class MessageQueue : public Lockable
    {
    public:
        virtual void        Send(u32 nMessageID, void * pData) = 0;
        virtual void        Post(u32 nMessageID, void * pData) = 0;

        virtual void        SendFront(u32 nMessageID, void * pData) = 0;
        virtual void        PostFront(u32 nMessageID, void * pData) = 0;

        virtual Message *   Receive(Time timeout = Time::Forever()) = 0;
        virtual void        ReleaseMessage(Message * pMessage) = 0;

        virtual u32         GetLength() = 0;
        virtual Message *   GetAt(u32 nIndex) = 0;
            ///< Gets Message at index nIndex without removing it from the queue.
            /// Note that index zero is the "front" of the MessageQueue.
        virtual void        SetAt(u32 nIndex, Message * pMessage) = 0;
        virtual void        RemoveAt(u32 nIndex) = 0;
        virtual void        InsertAt(u32 nIndex, Message * pMessage) = 0;
        virtual void        Append(Message * pMessage) = 0;
        virtual void        RemoveAll() = 0;
    protected:
        virtual ~MessageQueue() { }
    };

    class Stacktrace
    {
    public:
        virtual u32 GetDepth() const = 0;
        virtual AESIZE GetAddress(u32 frameIndex) const = 0;
        virtual const char * GetFrameString(u32 frameIndex) const = 0;
    protected:
        virtual ~Stacktrace() {}
    };

public:
    virtual IAEModule *     AcquireModule(const char * pModuleName) = 0;
    virtual void            ReleaseModule(IAEModule * pModule) = 0;

    virtual Thread *        CreateThread() = 0;
    virtual void            DestroyThread(Thread * pThread) = 0;
    virtual Thread *        GetCurrentThread() = 0;
    virtual void            Sleep(const Time & sleepTime) = 0;

    virtual AEError         GetLastError() = 0;
    virtual void            SetLastError(const AEError & error) = 0;
    inline  void            ClearLastError() { SetLastError(AEError_OK); }

    virtual Mutex *         CreateMutex() = 0;
    virtual void            DestroyMutex(Mutex * pMutex) = 0;

    virtual Event *         CreateEvent() = 0;
    virtual void            DestroyEvent(Event * pEvent) = 0;

    virtual Stacktrace *    CreateStacktrace(u32 nMaxDepth, u32 nDiscardDepth) = 0;
    virtual void            DestroyStacktrace(Stacktrace * pStacktrace) = 0;

    virtual void *          Alloc(u32 nBytes, const char * pFilename, int nLineNum) = 0;
    virtual void *          ReAlloc(void * pMem, u32 nBytes, const char * pFilename, int nLineNum) = 0;
    virtual void            Free(void * pMem, const char * pFilename, int nLineNum) = 0;

    virtual Time            GetKernelTime() = 0;
    virtual Time            GetKernelTimeResolution() = 0;
    
    virtual void            SetTimeGMT(const Time & timeGMT) = 0;
    virtual Time            GetTimeGMT() = 0;

    virtual void            SetTimeLocal(const Time & timeLocal) = 0;
    virtual Time            GetTimeLocal() = 0;

	virtual Time			ConvertGMTToLocal(const Time & timeGMT) = 0;

    virtual void            TimeToCalendarTime(const Time & time, CalendarTime & calendarTimeToSet) = 0;

    virtual u32             GetTimerMessageID() = 0;
    virtual u32             SetTimer(const Time & timePeriod, MessageQueue & queue, bool bRecurring = true) = 0;
    virtual u32             SetTimerGMT(const Time & timeStartGMT, MessageQueue & queue, const Time & recurringPeriod = Time()) = 0;
    virtual void            ClearTimer(u32 nTimerID, MessageQueue & queue) = 0;

    virtual MessageQueue *  CreateMessageQueue() = 0;
    virtual void            DestroyMessageQueue(MessageQueue * pMessageQueue) = 0;

    virtual u32             GetUniqueID() = 0;

    virtual int             GetArgc() = 0;
    virtual const char **   GetArgv() = 0;

    virtual bool            IsShutdownPending() = 0;

public:
    virtual void            memset(void * pDest, int c, u32 nCount) = 0;
    virtual void            memcpy(void * pDest, const void * pSource, u32 nCount) = 0;
    virtual void            memmove(void * pDest, const void * pSource, u32 nCount) = 0;
    virtual int             memcmp(const void * pBuff1, const void * pBuff2, u32 nCount) = 0;
    virtual void            qsort(void * base, u32 num, int width, void * pFunc) = 0;

    virtual u32             strlen(const char * pString) = 0;
    virtual int             strcmp(const char * pString1, const char * pString2) = 0;
    virtual int             strncmp(const char * pString1, const char * pString2, u32 nCount) = 0;
    virtual int             strcasecmp(const char * pString1, const char * pString2) = 0;
    virtual int             strncasecmp(const char * pString1, const char * pString2, u32 nCount) = 0;
    virtual char *          strcpy(char * pString1, const char * pString2) = 0;
    virtual char *          strcat(char * pString1, const char * pString2) = 0;
    virtual char *          strstr(const char * pStringHaystack, const char * pStringNeedle) = 0;
    virtual int             sprintf(char * pString, const char * pFormat, ...) = 0;
    virtual int             snprintf(char * pString, u32 nCount, const char * pFormat, ...) = 0;
    virtual long            strtol(const char * pString, char ** pStringToSet, int base) = 0;
    virtual unsigned long   strtoul(const char * pString, char ** pStringToSet, int base) = 0;
    virtual double          strtod(const char * pString, char ** pStringToSet) = 0; 
    virtual char *          strchr(const char * pString, int c) = 0;
    virtual char *          strrchr(const char * pString, int c) = 0;

    virtual void strncpyTerm(char * pDest, const char * pSource, u32 nDestBuffLen) = 0;
    virtual void AssertFailed(const char * pFile, int nLine, const char * pCondition) = 0;
    virtual bool ReadConsoleInputString(char * pBuff, u32 nBuffLen, Time timeout = Time::Forever()) = 0;
    virtual void WriteConsoleOutputString(const char * pFormatString, ...) = 0;

public:
    const AEError Err_ThreadAlreadyRunning;
    const AEError Err_ThreadCreationFailure;
    const AEError Err_ThreadCantWaitOnSelf;
    const AEError Err_ThreadSetPriority;
    const AEError Err_ThreadGenericError;
    const AEError Err_ThreadInvalidParameter;

private:
    // IAEKernel errors.
    enum IAEKernelError
    {
        ThreadAlreadyRunning = AEError::BaseErrorNumbers::AEKernel,
        ThreadCreationFailure,
        ThreadCantWaitOnSelf,
        ThreadSetPriority,
        ThreadGenericError,
        ThreadInvalidParameter
    };
};

////////////////////////////////////////////////////////////////////////////////
// AEKernel.h platform independent #defines
#ifdef _DEBUG
    #define AEASSERT(x) do { if (! (x)) IAEKernel::GetKernel()->AssertFailed(__FILE__, __LINE__, "##x##"); } while (false)
    #define AEVERIFY(x) AEASSERT(x)
    #define AEPRINT IAEKernel::GetKernel()->WriteConsoleOutputString
    #define AETRACE IAEKernel::GetKernel()->WriteConsoleOutputString
#else
    #define AEASSERT(x)
    #define AEVERIFY(x) ((void)(x))
    #define AEPRINT IAEKernel::GetKernel()->WriteConsoleOutputString
    extern "C" inline void AEKernel_DummyDoTrace(const char *, ...) { }
    #define AETRACE   1 ? (void)0 : ::AEKernel_DummyDoTrace
#endif

#define AEKERNEL_ERROR(e) (IAEKernel::GetKernel()->e)

#endif // #ifndef _AE_INCLUDE_AEKERNEL_AEKERNEL_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
////////////////////////////////////////////////////////////////////////////////
//  07-Sep-07   dwoodward   created
//  06-Dec-07   dwoodward   added protected virtual destructors
//  02-Jan-08   dwoodward   added more string functions
//  23-Jan-08   dwoodward   changed U64CONST to AEU64CONST
//  17-Apr-08   padoloph    added Lockable and ScopedLock, made MessageQueue Locakable,
//                          and added const to some return-by-value methods
//  18-May-08   dwoodward   added GetArgc() and GetArgv()
//  22-May-08   sshanson    Cleaned up Win32 warnings by overloading the FixedPoint
//                          constructor and changed FixedPoint::Val() to GetValue().
//  29-May-08   sshanson    added SetTimeLocal() and GetTimeLocal()
//  29-May-08   sshanson    added GetAsCalendarTime() and Calendar object
//  19-Jun-08   dwoodward   added class CountdownTimer; IsShutdownPending()
//  20-Jun-08   dwoodward   added thread specific GetLastError() / SetLastError() functions
//                          cleaned up macros, renamed Calendar to CalendarTime
//  06-Oct-08   dwoodward   fixed comment for day field of CalendarTime
//  09-Oct-08   dwoodward   added InitializeAEKernel() and UniitializeAEKernel()
//  09-Oct-08   dwoodward   added memcmp
//  12-Oct-08   dwoodward   added SendFront() and PostFront() to class MessageQueue
//  13-Oct-08   dwoodward   added RemoveAll()
//  21-Oct-08   dwoodward   made Time(u64) constructor private
//  21-Oct-08   dwoodward   added strstr
//  27-Oct-08   dwoodward   fixed up comments for doxygen
//  29-Nov-08   dwoodward   added ReadConsoleInputString, renamed DoTrace() to WriteConsoleOutputString
//  09-Dec-08   dwoodward   added pFilename and nLineNum parameters to Alloc() and Free()
//	12-Dec-08	mkumarb		added ConvertGMTToLocal() to help converting gmt time to local 
//	12-Dec-08	mkumarb		changed Time to use s64 instead of u64 for storing nanoseconds
//  13-Dec-08   dwoodward   added AEVERIFY and AEPRINT macros
//  11-Feb-09   padolph     added snprintf and Stacktrace
//  02-May-09   dwoodward   added strcasecmp and strncasecmp
//  16-Aug-09   dwoodward   added ReAlloc and strtod
