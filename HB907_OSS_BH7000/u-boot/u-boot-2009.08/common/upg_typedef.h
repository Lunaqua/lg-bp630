
#ifndef _UPG_TYPEDEF_H_
#define _UPG_TYPEDEF_H_


typedef unsigned char   BYTE;
typedef unsigned char   UCHAR;
typedef unsigned char   UINT8;
typedef unsigned short  UINT16;
typedef unsigned long   UINT32;
//typedef unsigned long long  UINT64;
typedef signed char   CHAR;
typedef signed char   INT8;
typedef signed short  INT16;
typedef signed long   INT32;
//typedef signed long long  INT64;
typedef UINT8  BOOL;
typedef float  FLOAT;
typedef double  DOUBLE;

#if defined(__linux__) 
//#define stricmp strcasecmp
#define stricmp strcmp							//todo
#else
#define stricmp stricmp
#endif

#ifndef NULL
    #define NULL                0
#endif  // NULL

#ifndef TRUE
    #define TRUE                (0 == 0)
#endif  // TRUE

#ifndef FALSE
    #define FALSE               (0 != 0)
#endif  // FALSE


#endif // _UPG_TYPEDEF_H_
