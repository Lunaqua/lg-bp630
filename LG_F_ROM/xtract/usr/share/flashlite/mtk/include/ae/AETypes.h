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

#ifndef _AE_INCLUDE_AEKERNEL_AETYPES_H
#define _AE_INCLUDE_AEKERNEL_AETYPES_H

////////////////////////////////////////////////////////////////////////////////
//
// NOTE: This is the *only* header file which is authorized to contain
//       #ifdefs.
//
//       This file is the single point of modification to identify platform
//       differences.  Everything else goes through 'driver module' public interfaces whose
//       platform dependencies are masked through a platform independent
//       interface constructed upon the types defined herein.
//
////////////////////////////////////////////////////////////////////////////////

typedef unsigned char           u8;
typedef unsigned short          u16;
typedef unsigned long           u32;
typedef signed char             s8;
typedef signed short            s16;
typedef signed long             s32;

#if defined (WIN32)
    typedef unsigned __int64    u64;
    typedef signed __int64      s64;
    
    #define AEMODULE_IMPLEMENT(Interface, Implementation) \
        extern "C" void IAEModule_AEModule_PutKernel(void * pKernel); \
        extern "C" __declspec(dllexport) IAEModule * IAEModule_##Interface##_ModuleLoad() { return AE_NEW Implementation; } \
        extern "C" __declspec(dllexport) void IAEModule_##Interface##_ModuleUnload(IAEModule * pModule) { AE_DELETE ((Implementation *)pModule); IAEModule_AEModule_PutKernel(0); } 

    #define AEU64CONST(n)       n##ui64
	#define AES64CONST(n)		n##i64	
    #define AE_ENDIAN_LITTLE
    
    #define AESIZE              unsigned int

    #define AEULONG_MAX         (0xFFFFFFFF)

    #pragma warning (disable: 4355)
        // warning C4355: 'this' : used in base member initializer list
    #pragma warning (disable: 4786)
        // warning C4786: 'this' : identifier was truncated to '255' characters in the debug information
    //#pragma warning (disable: 4996)
        // warning C4996: 'strncpy': This function or variable may be unsafe. Consider using strncpy_s instead.
    // #pragma warning (disable: 4250)
        // warning C4250: 'DerivedConcrete' : inherits 'BaseConcrete::BaseFunc' via dominance
#else
    typedef unsigned long long  u64;
    typedef signed long long    s64;

    #define AEMODULE_IMPLEMENT(Interface, Implementation) \
        extern "C" void IAEModule_AEModule_PutKernel(void * pKernel); \
        extern "C" IAEModule * IAEModule_##Interface##_ModuleLoad() { return AE_NEW Implementation; } \
        extern "C" void IAEModule_##Interface##_ModuleUnload(IAEModule * pModule) { AE_DELETE((Implementation *)pModule); IAEModule_AEModule_PutKernel(0); } 

    #define AEU64CONST(n)         n##ULL
	#define AES64CONST(n)		  n##LL
    #define AE_ENDIAN_LITTLE
    
    #if defined(__mn10300__)
        #define AESIZE          long unsigned int
    #else
        #define AESIZE          unsigned int
    #endif

    #define AEULONG_MAX         (0xFFFFFFFF)


#endif

#ifndef AETYPES_DONT_DEFINE_NULL
    #ifdef __cplusplus
        #ifdef NULL
            #undef NULL
        #endif
        #define                 NULL    (0)
    #else
        #ifdef NULL
            #undef NULL
        #endif
        #define                 NULL    ((void)0)
    #endif
#endif

////////////////////////////////////////////////////////////////////////////////
// Byte swapping macros
#define AE_SWAP16(n) ( (((n) >> 8) & 0x00FF) | (((n) << 8) & 0xFF00) )
#define AE_SWAP32(n) ( (((n) >> 24) & 0x000000FF) | (((n) >> 8) & 0x0000FF00) | (((n) << 8) & 0x00FF0000) | (((n) << 24) & 0xFF000000) )
#define AE_SWAP64(n) ( (((n) >> 56) & 0x00000000000000FF) | (((n) >> 40) & 0x000000000000FF00) | (((n) >> 24) & 0x0000000000FF0000) | (((n) >> 8) & 0x00000000FF000000) | (((n) << 8) & 0x000000FF00000000) | (((n) << 24) & 0x0000FF0000000000) | (((n) << 40) & 0x00FF000000000000) | (((n) << 56) & 0xFF00000000000000) )

////////////////////////////////////////////////////////////////////////////////
// Endian macros
#if defined(AE_ENDIAN_LITTLE)

    #define AE_LE16(n)          (n)
    #define AE_LE32(n)          (n)
    #define AE_LE64(n)          (n)

    #define AE_BE16(n)          AE_SWAP16((n))
    #define AE_BE32(n)          AE_SWAP32((n))
    #define AE_BE64(n)          AE_SWAP64((n))

    #define AE_HTONS(n)         AE_SWAP16((n))
    #define AE_HTONL(n)         AE_SWAP32((n))
    #define AE_HTONLL(n)        AE_SWAP64((n))
    #define AE_NTOHS(n)         AE_SWAP16((n))
    #define AE_NTOHL(n)         AE_SWAP32((n))
    #define AE_NTOHLL(n)        AE_SWAP64((n))

#elif defined (AE_ENDIAN_BIG)

    #define AE_LE16(n)          AE_SWAP16((n))
    #define AE_LE32(n)          AE_SWAP32((n))
    #define AE_LE64(n)          AE_SWAP64((n))

    #define AE_BE16(n)          (n)
    #define AE_BE32(n)          (n)
    #define AE_BE64(n)          (n)

    #define AE_HTONS(n)         (n)
    #define AE_HTONL(n)         (n)
    #define AE_HTONLL(n)        (n)
    #define AE_NTOHS(n)         (n)
    #define AE_NTOHL(n)         (n)
    #define AE_NTOHLL(n)        (n)

#else
    #error No AE_ENDIAN_BIG or AE_ENDIAN_LITTLE macro defined
#endif

////////////////////////////////////////////////////////////////////////////////
// BEGIN AETypes memory implementation

////////////
// dummy structures to force proper operator selection
typedef struct { } AEMem_Selector_AE_NEW_DELETE;
typedef struct { } AEMem_Selector_AE_VECTOR_NEW_DELETE;
typedef struct { } AEMem_Selector_AE_MALLOC_FREE;
typedef struct { } AEMem_Selector_PLACEMENT;

////////////
// operators for AE_NEW and AE_DELETE
void *  operator    new         (AESIZE nSize, const char * pFilename, int nLineNum, AEMem_Selector_AE_NEW_DELETE * pSelector);
void    operator    delete      (void * pMem,  const char * pFilename, int nLineNum, AEMem_Selector_AE_NEW_DELETE * pSelector);

////////////
// operators for AE_VECTOR_NEW and AE_VECTOR_DELETE
void *  operator    new         (AESIZE nSize, AESIZE nNumElements, AESIZE nElementSize, const char * pFilename, int nLineNum, AEMem_Selector_AE_VECTOR_NEW_DELETE * pSelector);
void    operator    delete      (void * pMem,  AESIZE nNumElements, AESIZE nElementSize, const char * pFilename, int nLineNum, AEMem_Selector_AE_VECTOR_NEW_DELETE * pSelector);

////////////
// operators for AE_MALLOC and AE_FREE
void *  operator    new         (AESIZE nSize, AESIZE nAllocSize, const char * pFilename, int nLineNum, AEMem_Selector_AE_MALLOC_FREE * pSelector);
void    operator    delete      (void * pMem,  AESIZE nAllocSize, const char * pFilename, int nLineNum, AEMem_Selector_AE_MALLOC_FREE * pSelector);

////////////
// operators for PLACEMENT new and delete
inline  void * operator new     (AESIZE nSize, void * pMem, AEMem_Selector_PLACEMENT * pSelector)   { return pMem; }
inline  void   operator delete  (void * pMem1, void * pMem2, AEMem_Selector_PLACEMENT * pSelector)  { }

////////////
// operators to trap misused vector invocation
void *  operator    new[]       (AESIZE nSize, const char * pFilename, int nLineNum, AEMem_Selector_AE_NEW_DELETE * pSelector);
void    operator    delete[]    (void * pMem,  const char * pFilename, int nLineNum, AEMem_Selector_AE_NEW_DELETE * pSelector);
void *  operator    new[]       (AESIZE nSize, AESIZE nNumElements, AESIZE nElementSize, const char * pFilename, int nLineNum, AEMem_Selector_AE_VECTOR_NEW_DELETE * pSelector);
void    operator    delete[]    (void * pMem,  AESIZE nNumElements, AESIZE nElementSize, const char * pFilename, int nLineNum, AEMem_Selector_AE_VECTOR_NEW_DELETE * pSelector);
void *  operator    new[]       (AESIZE nSize, AESIZE nAllocSize, const char * pFilename, int nLineNum, AEMem_Selector_AE_MALLOC_FREE * pSelector);
void    operator    delete[]    (void * pMem,  AESIZE nAllocSize, const char * pFilename, int nLineNum, AEMem_Selector_AE_MALLOC_FREE * pSelector);

////////////
// mem helper functions
template<class TYPE>
void AETypes_ScalarDelete(TYPE * pMem, const char * pFilename, int nLineNum)
{
    if (pMem)
    {
        pMem->~TYPE();
        operator delete(pMem, pFilename, nLineNum, (AEMem_Selector_AE_NEW_DELETE *)0);
    }
}              

template<class TYPE>
TYPE * AETypes_VectorNew(AESIZE nNumElements, const char * pFilename, int nLineNum)
{
    TYPE * pMem = (TYPE *)(nNumElements ? ::new(nNumElements, sizeof(TYPE), pFilename, nLineNum, (AEMem_Selector_AE_VECTOR_NEW_DELETE *)0) u8 : 0);
    if (pMem)
    {
        TYPE * pItem = pMem;
        while (nNumElements--) ::new(pItem++, (AEMem_Selector_PLACEMENT *)0) TYPE;
    }
    return pMem;
}              

template<class TYPE>
void AETypes_VectorDelete(TYPE * pMem, const char * pFilename, int nLineNum)
{
    if (pMem)
    {
        AESIZE nNumElements = *(((AESIZE *)pMem) - 1); // nNumElements stored in pointer - sizeof(AESIZE)
        while (nNumElements--) (pMem + nNumElements)->~TYPE();
        operator delete(pMem, 0, 0, pFilename, nLineNum, (AEMem_Selector_AE_VECTOR_NEW_DELETE *)0);
    }
}              

////////////////
// MEMORY MACROS
//
// AE_NEW - use this like you would use scalar new (e.g. new Foo becomes AE_NEW Foo)
//          do not use this for vector new (i.e. do *NOT* do this: AE_NEW Foo[5]
//          instead use AE_VECTOR_NEW(Foo, 5)
//
// AE_DELETE - use this on objects you allocated with AE_NEW
//
// AE_VECTOR_NEW - use this like you would use vector new
//                 (e.g. new Foo[5] becomes AE_VECTOR_NEW(Foo, 5)
//                 Only use this for vector new of objects that need construction, not integral data types.
//                 So don't do this: AE_VECTOR_NEW(u8, 32768) - it will cause a loop
//                 on construction and destruction each of 32768 times on a noop constructor/destructor.
//                 (This will be fixed later).  INSTEAD - use AE_MALLOC(u8, 32768)
//
// AE_VECTOR_DELETE - use this on objects you allocated with AE_VECTOR_NEW
//
// AE_MALLOC - use this like you would malloc, except specify the type and the num elements,
//             e.g. u8 * pBytes = ::malloc(nBytes) becomes u8 * pBytes = AE_MALLOC(u8, nBytes);
// AE_FREE - use this on memory you allocated with AE_MALLOC

#ifdef _DEBUG
    #define AE_NEW                      ::new(__FILE__, __LINE__, (AEMem_Selector_AE_NEW_DELETE *)0)
    #define AE_DELETE(pMem)             AETypes_ScalarDelete((pMem), __FILE__, __LINE__)
    #define AE_VECTOR_NEW(TYPE, nCount) AETypes_VectorNew<TYPE>((nCount), __FILE__, __LINE__)
    #define AE_VECTOR_DELETE(pMem)      AETypes_VectorDelete((pMem), __FILE__, __LINE__)
    #define AE_MALLOC(TYPE, nCount)     (TYPE *)::new(((nCount) * (sizeof(TYPE))), __FILE__, __LINE__, (AEMem_Selector_AE_MALLOC_FREE *)0) u8
    #define AE_FREE(pMem)               if (pMem) operator delete((pMem), 0, __FILE__, __LINE__, (AEMem_Selector_AE_MALLOC_FREE *)0)
#else
    #define AE_NEW                      ::new(NULL, 0, (AEMem_Selector_AE_NEW_DELETE *)0)
    #define AE_DELETE(pMem)             AETypes_ScalarDelete((pMem), NULL, 0)
    #define AE_VECTOR_NEW(TYPE, nCount) AETypes_VectorNew<TYPE>((nCount), NULL, 0)
    #define AE_VECTOR_DELETE(pMem)      AETypes_VectorDelete((pMem), NULL, 0)
    #define AE_MALLOC(TYPE, nCount)     (TYPE *)::new(((nCount) * (sizeof(TYPE))), NULL, 0, (AEMem_Selector_AE_MALLOC_FREE *)0) u8
    #define AE_FREE(pMem)               if (pMem) operator delete((pMem), 0, NULL, 0, (AEMem_Selector_AE_MALLOC_FREE *)0)
#endif

// END AETypes memory implementation
////////////////////////////////////////////////////////////////////////////////


#endif // #ifndef _AE_INCLUDE_AEKERNEL_AETYPES_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
////////////////////////////////////////////////////////////////////////////////
//  20-Aug-07   dwoodward   created
//  02-Jan-08   dwoodward   disable warning 4355 on windows
//  23-Jan-08   dwoodward   changed U64CONST to AEU64CONST
//  01-Jun-08   dwoodward   disable warning 4786 on windows - namespaced templates fire it
//  02-Jun-08   dwoodward   added byte-swapping and endian macros
//  29-Aug-08   dwoodward   force AEModule linkage by referencing IAEModule_AEModule_PutKernel
//  04-Dec-08   dwoodward   added AESIZE for platform independent size_t
//  05-Dec-08   dwoodward   added specific AESIZE def for __mn10300__
//  09-Dec-08   dsletten    added AEULONG_MAX
//  09-Dec-08   dwoodward   added AE_NEW / AE_DELETE AETypes memory implementation
//	12-Dec-08	mkumarb		added AES64CONST for defining s64 constants

