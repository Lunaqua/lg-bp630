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

#ifndef _AE_INCLUDE_AEKERNEL_AEERROR_H
#define _AE_INCLUDE_AEKERNEL_AEERROR_H

#include <ae/AETypes.h>

////////////////////////////////////////////////////////////////////////////////
// class AEError
class AEError
{
public:
	inline void strncpyTerm(char * pDest, const char * pSource, u32 nBuffSize)
	{
		if (nBuffSize)
		{
			while (--nBuffSize)
			{
				if (0 == *pSource) break;
				*pDest++ = *pSource++;
			}
			*pDest = 0;
		}
	}
public:
    AEError() : m_nErrorValue(Uninitialized)
    {
        strncpyTerm(m_errorName, "Uninitialized", kMaxName);
    }
    AEError(const char * const pName, u32 nValue) : m_nErrorValue(nValue)
    {
        strncpyTerm(m_errorName, pName, kMaxName);
    }
    AEError(const AEError &other) : m_nErrorValue(other.m_nErrorValue)
    {
        strncpyTerm(m_errorName, other.m_errorName, kMaxName);
    }
    virtual ~AEError() {}

    bool operator==(const AEError &rhs)
    {
        return(this->m_nErrorValue == rhs.m_nErrorValue);
    }

    bool operator!=(const AEError &rhs)
    {
        // Implement operator!=() in terms of operator==().
        return(!(*this == rhs));
    }

    AEError &operator=(const AEError &rhs)
    {
        if (this != &rhs)
        {
            strncpyTerm(m_errorName, rhs.m_errorName, kMaxName);
            m_nErrorValue = rhs.m_nErrorValue;
        }
        return(*this);
    }

    operator const u32() const
    {
        return(Val());
    }

    const u32 Val(void) const {return(m_nErrorValue);}
    const char *Name(void) const {return(m_errorName);}

    /*! 
     * AEError::OK is the only global AEError type.
     * 
     */
    enum GlobalErrors
    {
        OK          = 0,
        NoMemory,
        Timeout,
        Generic,
        NotFound,
        Usage,
        InvalidParameters,
        Uninitialized = 0xffffffff
    };

    struct BaseErrorNumbers
    {
        enum
        {
            AEKernel            = 0x00000100,
            IFlashLib           = 0x00000200,
            IShell              = 0x00000300,
            IStagecraft         = 0x00000400,
            IGraphicsDriver     = 0x00000500,
            IXMLReaderLib       = 0x00000600,
            IStreamPlayer       = 0x00000700,

            //! For implementation-specific errors, add
            //! IMPLEMENTATION to the BaseErrorNumber. 
            //! For example, an error for the XWindows implementation of 
            //! IGraphicsDriver, errors start at 0x80000500. 
            IMPLEMENTATION  = 0x80000000
        };
    };

private:
    friend bool operator == (const AEError & lhs, const AEError & rhs);
    friend bool operator != (const AEError & lhs, const AEError & rhs);

private:
    enum { kMaxName = 32 };

private:
    char    m_errorName[kMaxName];
    u32     m_nErrorValue;
};

////////////////////////////////////////////////////////////////////////////////
// AEError global comparison operators
inline bool
operator == (const AEError & lhs, const AEError & rhs)
{
    return (lhs.m_nErrorValue == rhs.m_nErrorValue);
}

inline bool
operator != (const AEError & lhs, const AEError & rhs)
{
    return (! (lhs.m_nErrorValue == rhs.m_nErrorValue));
}

////////////////////////////////////////////////////////////////////////////////
// AEError public constant errors
const AEError AEError_OK =          AEError("AEError_OK",       AEError::OK);
const AEError AEError_NoMemory =    AEError("AEError_NoMemory", AEError::NoMemory);
const AEError AEError_Timeout =     AEError("AEError_Timeout",  AEError::Timeout);
const AEError AEError_Generic =     AEError("AEError_Generic",  AEError::Generic);
const AEError AEError_NotFound =    AEError("AEError_NotFound", AEError::NotFound);
const AEError AEError_Usage =       AEError("AEError_Usage",    AEError::Usage);
const AEError AEError_InvalidParameters = AEError("AEError_InvalidParameters",    AEError::InvalidParameters);

////////////////////////////////////////////////////////////////////////////////
// AEError platform independent helper macros
#define AEERROR_INIT(e)              Err_##e(#e, e)
#define AEERROR_TRACE(e)             { if (e != AEError_OK) AETRACE("AEERROR - %s:%d: %s\n", __FILE__, __LINE__, e.Name()); }

#endif // #ifndef _AE_INCLUDE_AEKERNEL_AEERROR_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
//////////////////////////////////////////////////////////////////////////////// 
//  27-May-08   sshanson    Added IImageDecoderDriver enum
//  18-Jun-08	sshanson	Changed kMaxName to 32
//  20-Jun-08   dwoodward   cleaned up macros; added timeout and generic errors
//  14-Jul-08   dsletten    added error base for IXMLDecoderDriver
//	08-Aug-08	dwoodward	implemented inline strncpyTerm - strncpy deprecated in vs2008
//	22-Sep-08	dwoodward	added AEError_InvalidParameters
//	22-Oct-08	dwoodward	got rid of stdlib includes
//  29-Oct-08   dwoodward   no more IUserInput; reordered BaseErrorNumbers
//  03-Mar-08   dwoodward   no trailing commas on the last item in an enum
