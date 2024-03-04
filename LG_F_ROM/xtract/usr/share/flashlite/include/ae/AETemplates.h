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

#ifndef _AE_INCLUDE_AEKERNEL_AETEMPLATES_H
#define _AE_INCLUDE_AEKERNEL_AETEMPLATES_H

#include <ae/IAEKernel.h>

////////////////////////////////////////////////////////////////////////////////
// class AETemplates (helper functions for templates)
class AETemplates
{
public:
    class PlacementNew { };
};

inline void * operator new(AESIZE nSize, void * pMem, AETemplates::PlacementNew *)
{
    return pMem;
}

inline void operator delete(void * pMem1, void * pMem2, AETemplates::PlacementNew *)
{
}

////////////////////////////////////////////////////////////////////////////////
// class AEArray
template <class ENTRYTYPE>
class AEArray
{
public:
    enum { kInitialMemSize = 8 };
    typedef int (CompareFunc)(const ENTRYTYPE * pEntry1, const ENTRYTYPE * pEntry2);

public:
    AEArray(u32 nInitialMemSize = kInitialMemSize, bool bMemMoveElements = true);
    AEArray(const AEArray & that);
    virtual ~AEArray();

public:
    AEArray &        operator = (const AEArray & that);
    const ENTRYTYPE& operator [](u32 nIndex) const { AEASSERT(nIndex < m_nSize); return m_pEntries[nIndex]; }  // for const objects
    ENTRYTYPE&       operator [](u32 nIndex) { AEASSERT(nIndex < m_nSize); return m_pEntries[nIndex]; }        // for non-const objects
    
public:
    inline ENTRYTYPE &  GetAt(u32 nIndex) const { AEASSERT(nIndex < m_nSize); return m_pEntries[nIndex]; }
    inline void         SetAt(u32 nIndex, const ENTRYTYPE & entry) { AEASSERT(nIndex < m_nSize); m_pEntries[nIndex] = entry; }
    void                InsertAt(u32 nIndex, const ENTRYTYPE & entry);
    void                RemoveAt(u32 nIndex);
    inline void         Append(const ENTRYTYPE & entry) { InsertAt(m_nSize, entry); }
    inline u32          GetSize() const { return m_nSize; }
    void                SetSize(u32 nSize);
    inline bool         IsEmpty() const { return (m_nSize == 0); }
    inline void         qsort(CompareFunc * pCompareFunc) { IAEKernel::GetKernel()->qsort((void *)m_pEntries, m_nSize, sizeof(ENTRYTYPE), (void *)pCompareFunc); }
                        // qsort performs a quicksort, provided that array elements can be memmoved
                        // around in memory without disruption - so don't call qsort on arrays that
                        // contain objects that can't be memmoved around
protected:
    inline void ConstructEntries(ENTRYTYPE * pEntries, u32 nNumEntries) { IAEKernel::GetKernel()->memset(pEntries, 0, nNumEntries * sizeof(ENTRYTYPE)); for (; nNumEntries--; pEntries++) ::new((void *)pEntries, (AETemplates::PlacementNew *)NULL) ENTRYTYPE; }
    inline void DestructEntries(ENTRYTYPE * pEntries, u32 nNumEntries) { for (; nNumEntries--; pEntries++) pEntries->~ENTRYTYPE(); }

private:
    operator const void * () const;
        // so AEArray; delete AEArray is prevented

protected:
    ENTRYTYPE * m_pEntries;
    u32 m_nSize;
    u32 m_nAllocSize;
    bool m_bMemMoveElements;
};

////////////////////////////////////////////////////////////////////////////////
// class AEHashTable
template <class ENTRYKEY, class ENTRYVALUE>
class AEHashTable
{
public:
    typedef bool (EntryEnumProc)(const ENTRYKEY & key, const ENTRYVALUE & data, void * pClientData);
    enum { kInitialTableSize = 8, kGrowthFactor = 3 };

public:
    AEHashTable(u32 nInitialTableSize = kInitialTableSize);
    virtual ~AEHashTable() { RemoveAllEntries(); }

public:
    bool SetAt(const ENTRYKEY & key, const ENTRYVALUE & value);
        // SetAt() copies the value and sticks the copy in the hash
        // table, keyed by key.  It replaces any currently keyed value
        // with the [copy of the] new value.
    bool GetAt(const ENTRYKEY & key, ENTRYVALUE & valueToSet) const;
        // GetAt() sets valueToSet with the a copy of the value in the
        // hash table keyed by key returning true if successful, false
        // if no value exists that is keyed by key.
    bool RemoveAt(const ENTRYKEY & key);
        // RemoveAt() removes the entry at key, returning true if an entry was
        // removed, false if no entry existed keyed by key
    void RemoveAllEntries();
        // RemoveAllEntries() removes all entries from the hash table.
    bool EnumerateEntries(EntryEnumProc * pEnumProc, void * pClientData) const;
        // EnumerateEntries() calls back pEnumProc  for each entry in the
        // hash table, returning true if the enumeration ran to completion,
        // false if it was aborted becuase the *pEnumProc returned false at some
        // point to abort the enumeration.  During EnumerateEntries() it is save
        // to call RemoveAt() on the entry being called back.
    inline u32 GetSize() const { return m_nSize; }

protected:
    void GrowTable();

protected:
    class TableEntry
    {
    public:
        TableEntry(const ENTRYKEY & key, const ENTRYVALUE & value, TableEntry * pNext) : m_key(key), m_value(value), m_pNext(pNext) { }
        ~TableEntry() { }
    public: 
        ENTRYKEY m_key;
        ENTRYVALUE m_value;
        TableEntry * m_pNext;
    };

protected:
    u32 m_nSize;
    AEArray<TableEntry *> m_table;
};

////////////////////////////////////////////////////////////////////////////////
// inline class AEString - a UTF-8 string
class AEString : protected AEArray<u8>
{
public:
    AEString() : AEArray<u8>(0) { }
    AEString(const char * pString) : AEArray<u8>(0) { *this = pString; }
    virtual ~AEString()         { } 
public:
    inline bool IsEmpty()               { return (0 == m_nSize) ? true : false; }
    inline void Empty()                 { SetSize(0); }
    inline u32  GetLength()             { return (0 == m_nSize) ? 0 : (m_nSize - 1); }
    inline u32  GetTerminatedLength()   { return m_nSize; }
    inline operator const char * ()     { return (0 == m_nSize ? "" : (const char *)m_pEntries); }
    inline AEString & operator = (const char * pString)
    {
        if (pString && *pString)
        {
            IAEKernel * pKernel = IAEKernel::GetKernel();
            u32 nSize = pKernel->strlen(pString) + 1;
            SetSize(nSize);
            pKernel->memcpy(m_pEntries, pString, nSize);
        }
        else SetSize(0);
        return *this;
    }
    inline char * SetStringLengthGetBuffer(u32 nStringLength)
    {
        SetSize(nStringLength + 1);
        *(m_pEntries + nStringLength) = 0;
        return (char *)m_pEntries;
    }
    inline void SetStringLength(u32 nStringLength)
    {
        SetSize(nStringLength + 1);
        *(m_pEntries + nStringLength) = 0;
    }
    inline AEString & operator += (const char * pString)
    {
        if (pString && *pString)
        {
            IAEKernel * pKernel = IAEKernel::GetKernel();
            u32 nLength = GetLength();
            u32 nAdditionalSize = pKernel->strlen(pString) + 1;
            SetSize(nLength + nAdditionalSize);
            pKernel->memcpy(m_pEntries + nLength, pString, nAdditionalSize);
        }
        return *this;
    }
    inline AEString & operator += (char ch)
    {
        if (ch)
        {
            u32 nLength = GetLength();
            SetSize(nLength + 2);
            *(m_pEntries + nLength) = ch;
            *(m_pEntries + nLength + 1) = 0;
        }
        return *this;
    }
    inline char operator [] (u32 nIndex) const { return (nIndex < m_nSize) ? *(((char *)m_pEntries) + nIndex) : 0; }
        // NOTE this operator does not support taking the address of an element.  Do NOT do this: &string[4]
    inline char operator [] (int nIndex) const { return operator[]((u32)nIndex); }
        // Note: nIndex is of type int in this version because of the dang compiler warning about ambiguous operators: if string[4]
        // is written, the literal 4 is treated as an int and then without this explicit int operator, the compiler gets confused whether it
        // should use the [](u32) operator or do implicit type casting to (const char *)
        // and use the compiler built in [](int) for const char *.  This makes that explicit.
};

class AETokenArray : protected AEArray<const char *>
{
public: 
    AETokenArray() : AEArray<const char *>(0)                                                   { m_pString = NULL; }
    AETokenArray(const char * pString, const char * pDelimiters) : AEArray<const char *>(0)     { m_pString = NULL; Tokenize(pString, pDelimiters); }
    virtual ~AETokenArray()                                                                     { Clear(); }
public:
    void Clear()
    {
        if (NULL != m_pString)
        {
            AE_FREE(m_pString);
            m_pString = NULL;
            SetSize(0);
        }
    }
    void Tokenize(const char * pString, const char * pDelimiters)
    {
        Clear();
        if (pString && *pString && pDelimiters && *pDelimiters)
        {
            IAEKernel * pKernel = IAEKernel::GetKernel();
            if (NULL != (m_pString = AE_MALLOC(char, pKernel->strlen(pString) + 1)))
            {
                pKernel->strcpy(m_pString, pString);
                pString = m_pString;
                while (1)
                {
                    // skip over delimiters
                    while (*pString && IsDelimiter(*pString, pDelimiters)) pString++;
                    if (0 == *pString) break;
                    // append a token
                    Append((const char *)pString);
                    // skip to next delim
                    while (*pString && (! IsDelimiter(*pString, pDelimiters))) pString++;
                    if (0 == *pString) break;
                    // terminate the token
                    *((char *)pString) = 0; pString++;
                }
            } 
        }
    }
    u32 GetNumTokens() const                { return m_nSize; }
    const char * GetTokenAt(u32 i) const    { return GetAt(i); }
private:
    bool IsDelimiter(char c, const char * pDelimiters)
    {
        while (*pDelimiters) { if (c == *pDelimiters) return true; pDelimiters++; }
        return false;
    }
private:
    char * m_pString;
};

////////////////////////////////////////////////////////////////////////////////
// template class AEArray<ENTRYTYPE> implementation
template <class ENTRYTYPE>
AEArray<ENTRYTYPE>::AEArray(u32 nInitialMemSize, bool bMemMoveElements)
{
	m_nSize = m_nAllocSize = 0;
	m_pEntries = NULL;
	m_bMemMoveElements = bMemMoveElements;
    SetSize(nInitialMemSize);
    SetSize(0);
}

template <class ENTRYTYPE>
AEArray<ENTRYTYPE>::AEArray(const AEArray<ENTRYTYPE> & that)
{
	m_nSize = m_nAllocSize = 0;
	m_pEntries = NULL;
	m_bMemMoveElements = that.m_bMemMoveElements;
	
	u32 nThatSize = that.GetSize();
	if (0 == nThatSize)
	{
        SetSize(that.m_nAllocSize);
        SetSize(0);
	}
	else
	{
	   SetSize(nThatSize);
	   for (u32 i = 0; i < nThatSize; i++) m_pEntries[i] = that.m_pEntries[i];
	}
}

template <class ENTRYTYPE>
AEArray<ENTRYTYPE>::~AEArray()
{
	if (m_pEntries)
	{
        DestructEntries(m_pEntries, m_nAllocSize);
		AE_FREE(m_pEntries);
	}
}

template <class ENTRYTYPE> AEArray<ENTRYTYPE> &
AEArray<ENTRYTYPE>::operator = (const AEArray<ENTRYTYPE> & that)
{
    m_bMemMoveElements = that.m_bMemMoveElements;
    SetSize(that.m_nSize);
    for (u32 i = 0; i < that.m_nSize; i++) m_pEntries[i] = that.m_pEntries[i];
    return *this;
}

template <class ENTRYTYPE> void
AEArray<ENTRYTYPE>::InsertAt(u32 nIndex, const ENTRYTYPE & entry)
{
    if (nIndex >= m_nSize) SetSize(nIndex + 1);
    else
    {
        SetSize(m_nSize + 1);
        if (m_bMemMoveElements)
        {
            DestructEntries(&m_pEntries[m_nSize-1], 1);
            IAEKernel::GetKernel()->memmove(&m_pEntries[nIndex+1], &m_pEntries[nIndex], ((m_nSize-1) - nIndex) * sizeof(ENTRYTYPE));
            ConstructEntries(&m_pEntries[nIndex], 1);
        }
        else
        {
            for (u32 i = m_nSize-1; i > nIndex; i--) m_pEntries[i] = m_pEntries[i-1];
        }
    }
    
	AEASSERT(nIndex < m_nSize);
    m_pEntries[nIndex] = entry;
}

template <class ENTRYTYPE> void
AEArray<ENTRYTYPE>::RemoveAt(u32 nIndex)
{	
	AEASSERT(nIndex < m_nSize);

    DestructEntries(&m_pEntries[nIndex], 1);
	if (nIndex < (m_nSize - 1)) 
	{
	    if (m_bMemMoveElements) IAEKernel::GetKernel()->memmove(&m_pEntries[nIndex], &m_pEntries[nIndex + 1], (m_nSize - (nIndex + 1)) * sizeof(ENTRYTYPE));
	    else
	    {
	        for (u32 i = nIndex; i < m_nSize-1; i++) m_pEntries[i] = m_pEntries[i+1];
	    }
	}
    ConstructEntries(&m_pEntries[m_nSize - 1], 1);
    
    m_nSize--;
}

template <class ENTRYTYPE> void
AEArray<ENTRYTYPE>::SetSize(u32 nSize)
{
	if (nSize > m_nAllocSize)
	{
        u32 nNewMemSize;
        if (m_nAllocSize)
        {
            u32 nDouble = 2 * m_nAllocSize;
            nNewMemSize = nDouble * ((nSize / nDouble) + 1);
        }
        else nNewMemSize = nSize;

        // alloc new entries, copy into, construct new ones, and discard the old entry array
		ENTRYTYPE * pNewEntries = AE_MALLOC(ENTRYTYPE, nNewMemSize); // nNewMemSize is the size in num ENTRYTYPEs 
		if (m_bMemMoveElements)
		{
            if (m_nAllocSize) IAEKernel::GetKernel()->memcpy(pNewEntries, m_pEntries, m_nAllocSize * sizeof(ENTRYTYPE));
            ConstructEntries(&pNewEntries[m_nSize], nNewMemSize - m_nSize);
		    AE_FREE(m_pEntries);
		}
		else
		{
            ConstructEntries(pNewEntries, nNewMemSize);
            for (u32 i = 0; i < m_nSize; i++) pNewEntries[i] = m_pEntries[i];
            if (m_nAllocSize)
            {
                DestructEntries(m_pEntries, m_nAllocSize);
                AE_FREE(m_pEntries);
            }
		}
		m_pEntries = pNewEntries;
		m_nAllocSize = nNewMemSize;
	}

    m_nSize = nSize;
}

////////////////////////////////////////////////////////////////////////////////
// template class AEHashTable<ENTRYKEY, ENTRYVALUE> global hash functions
template <class ENTRYKEY> inline u32
AEHashTable_KeyHash(const ENTRYKEY & key)
{
    u32 nKey = (u32)key;
    return nKey >> 4;
}

template <class ENTRYKEY> inline bool
AEHashTable_KeyCompare(const ENTRYKEY & key1, const ENTRYKEY & key2)
{
    return (key1 == key2);
}

inline u32
AEHashTable_KeyHash(const char * pString)
{
    u32 nHash = 0;
    while (*pString) { nHash *= 65599; nHash += *pString++; }
	return nHash;
}

inline bool
AEHashTable_KeyCompare(const char * pStringKey1, const char * pStringKey2)
{
    return (0 == IAEKernel::GetKernel()->strcmp(pStringKey1, pStringKey2));
}

////////////////////////////////////////////////////////////////////////////////
// template class AEHashTable<ENTRYKEY, ENTRYVALUE> implementation
template <class ENTRYKEY, class ENTRYVALUE>
AEHashTable<ENTRYKEY, ENTRYVALUE>::AEHashTable(u32 nInitialTableSize) : m_nSize(0), m_table(nInitialTableSize < kInitialTableSize ? kInitialTableSize : nInitialTableSize)
{
    m_table.SetSize(nInitialTableSize < kInitialTableSize ? kInitialTableSize : nInitialTableSize);
    for (u32 i = 0; i < m_table.GetSize(); i++) m_table[i] = NULL;
}

template <class ENTRYKEY, class ENTRYVALUE> bool
AEHashTable<ENTRYKEY, ENTRYVALUE>::SetAt(const ENTRYKEY & key, const ENTRYVALUE & data)
{
    u32 nHash = AEHashTable_KeyHash(key) % m_table.GetSize();
    TableEntry * pCurr = m_table[nHash];
    while (pCurr)
    {
        if (AEHashTable_KeyCompare(key, pCurr->m_key))
        {
            pCurr->m_value = data;
            return true;
        }
        pCurr = pCurr->m_pNext;
    }
    m_table[nHash] = AE_NEW TableEntry(key, data, m_table[nHash]);
    m_nSize++;
    if (m_nSize > (kGrowthFactor * m_table.GetSize())) GrowTable();
    return true;
}

template <class ENTRYKEY, class ENTRYVALUE> bool
AEHashTable<ENTRYKEY, ENTRYVALUE>::GetAt(const ENTRYKEY & key, ENTRYVALUE & dataToSet) const
{
    TableEntry * pBucket = m_table[AEHashTable_KeyHash(key) % m_table.GetSize()];
    while (pBucket)
    {
        if (AEHashTable_KeyCompare(key, pBucket->m_key))
        {
            dataToSet = pBucket->m_value;
            return true;
        }
        pBucket = pBucket->m_pNext;
    }
    return false;
}

template <class ENTRYKEY, class ENTRYVALUE> bool
AEHashTable<ENTRYKEY, ENTRYVALUE>::RemoveAt(const ENTRYKEY & key)
{
    u32 nHash = AEHashTable_KeyHash(key) % m_table.GetSize();
    TableEntry * pCurr = m_table[nHash];
    TableEntry * pPrev = NULL;
    while (pCurr)
    {
        if (AEHashTable_KeyCompare(key, pCurr->m_key))
        {
            if (pPrev) pPrev->m_pNext = pCurr->m_pNext;
            else m_table[nHash] = pCurr->m_pNext;
            AE_DELETE(pCurr);
            m_nSize--;
            return true;
        }
        pPrev = pCurr; pCurr = pCurr->m_pNext;
    }
    return false;
}

template <class ENTRYKEY, class ENTRYVALUE> void
AEHashTable<ENTRYKEY, ENTRYVALUE>::RemoveAllEntries()
{
    u32 nSize = m_table.GetSize();
    for (u32 i = 0; i < nSize; i++)
    {
        TableEntry * pCurr = m_table[i];
        TableEntry * pDel;
        while (pCurr)
        {
            pDel = pCurr;
            pCurr = pCurr->m_pNext;
            AE_DELETE(pDel);
        }
        m_table[i] = NULL;
    }
    m_nSize = 0;
}

template <class ENTRYKEY, class ENTRYVALUE> bool
AEHashTable<ENTRYKEY, ENTRYVALUE>::EnumerateEntries(EntryEnumProc * pEnumProc, void * pClientData) const
{
    u32 nSize = m_table.GetSize();
    for (u32 i = 0; i < nSize; i++)
    {
        TableEntry * pCurr = m_table[i];
        TableEntry * pNext;
        while (pCurr)
        {
            pNext = pCurr->m_pNext;
            if (! (*pEnumProc)(pCurr->m_key, pCurr->m_value, pClientData)) return false;
            pCurr = pNext;
        }
    }
    return true;
}

template <class ENTRYKEY, class ENTRYVALUE> void
AEHashTable<ENTRYKEY, ENTRYVALUE>::GrowTable()
{
    u32 nSizeOld = m_table.GetSize();
    u32 nSizeNew = 2 * nSizeOld;
    u32 nHashNew;
    m_table.SetSize(nSizeNew);
    for (u32 i = nSizeOld; i < nSizeNew; i++) m_table[i] = NULL;

    for (u32 nHashOld = 0; nHashOld < nSizeOld; nHashOld++)
    {
        TableEntry * pCurr = m_table[nHashOld];
        TableEntry * pPrev = NULL;
        while (pCurr)
        {
            nHashNew = AEHashTable_KeyHash(pCurr->m_key) % nSizeNew;
            if (nHashNew == nHashOld)
            {
                pPrev = pCurr;
                pCurr = pCurr->m_pNext;
            }
            else
            {
                if (pPrev)
                {
                    pPrev->m_pNext = pCurr->m_pNext;
                    pCurr->m_pNext = m_table[nHashNew];
                    m_table[nHashNew] = pCurr;
                    pCurr = pPrev->m_pNext;
                }
                else
                {
                    m_table[nHashOld] = pCurr->m_pNext;
                    pCurr->m_pNext = m_table[nHashNew];
                    m_table[nHashNew] = pCurr;
                    pCurr = m_table[nHashOld];
                }
            }
        }
    }
}

////////////////////////////////////////////////////////////////////////////////
// typesafe min/max functions
//
template <typename T> const T& AEmin(const T &a, const T &b) {return a < b ? a : b;}
template <typename T> const T& AEmax(const T &a, const T &b) {return a > b ? a : b;}

#endif // #ifndef _AE_INCLUDE_AEKERNEL_AETEMPLATES_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
////////////////////////////////////////////////////////////////////////////////
//  26-Sep-07   dwoodward   created
//  09-May-08   padolph     fixed const signatures of AEArray::operator[]
//                          added AECallback interface and AEMemberFunctionCallback template
//  29-May-08   padolph     added max and min functions
//  29-May-08   dwoodward   added AEString as a placeholder for AEString
//  10-Jul-08   padolph     minor change to AEmin/AEmax signatures to use const
//  14-Aug-08   dwoodward   added some basic functionality to AEString
//  19-Aug-08   dwoodward   more AEString
//  24-Sep-08   dwoodward   added += operator to AEString
//  02-Oct-08   dwoodward   added AETokenArray
//  23-Oct-08   dwoodward   added bMemMoveElements to AEArray constructor - defaults to true
//                          so array sizing ops default to memmove as before; if false, copy operators used instead
//  04-Oct-08   dwoodward   use AESIZE as the size_t argument to operator new
//  07-Dec-08   dwoodward   added +=(char) operator to AEString
//  11-Dec-08   dwoodward   use AETypes.h allocation macros
