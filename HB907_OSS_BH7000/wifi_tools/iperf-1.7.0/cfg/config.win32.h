/* config.h.  Generated by hand for Windows. */
#ifndef CONFIG_H
#define CONFIG_H

/* ===================================================================
 * config.h
 *
 * config.h is derived from config.h.in -- do not edit config.h
 *
 * This contains variables that the configure script checks and
 * then defines or undefines. The source code checks for these
 * variables to know if certain features are present.
 * 
 * by Mark Gates <mgates@nlanr.net>
 *
 * Copyright  1999  The Board of Trustees of the University of Illinois
 * All rights reserved.  See doc/license.txt for complete text.
 *
 * $Id: //BDP/OSS/BDP_V301/BDP_Linux/oss/source/wifi_tools/iperf-1.7.0/cfg/config.win32.h#1 $
 * =================================================================== */

/* Define if threads exist (using pthreads or Win32 threads) */
/* #undef HAVE_POSIX_THREAD */
#define HAVE_WIN32_THREAD 1
/* #undef _REENTRANT */

/* Define if on OSF1 and need special extern "C" around some header files */
/* #undef SPECIAL_OSF1_EXTERN */

/* Define if the strings.h header file exists */
/* #undef HAVE_STRINGS_H */

/* Define the int32_t, u_int32_t, size_t, ssize_t, and socklen_t types */
/* On the Cray J90 there is no 4 byte integer, so we define int32_t
 * but it is 8 bytes, and we leave HAVE_INT32_T undefined. */
#define HAVE_INT32_T 1
#define HAVE_U_INT32_T 1
#define HAVE_INT64_T 1

#define int32_t   int     /* <sys/types.h   */
#define u_int32_t unsigned int
/* #undef size_t */
#define ssize_t   int

/* added by feng qin --- end */
/* win32 has not u_int16_t used in endian.c */
#define u_int16_t unsigned short

/* true/false used in signal.h */
#define true 1
#define false 0
/* added by feng qin --- end */


/* socklen_t usually defined in <sys/socket.h>. Unfortunately it doesn't
 * work on some systems (like DEC OSF/1), so we'll use our own Socklen_t */
#define Socklen_t int

/* Define if you have these functions. */
#define HAVE_SNPRINTF 1
/* #undef HAVE_INET_PTON */
/* #undef HAVE_INET_NTOP */
/* #undef HAVE_GETTIMEOFDAY */
/* #undef HAVE_PTHREAD_CANCEL */
/* #undef HAVE_USLEEP */

/* standard C++, which isn't always... */
/* #undef bool */
/* #undef true */
/* #undef false */

/* Define if the host is Big Endian (network byte order) */
/* #undef WORDS_BIGENDIAN */

/* Define if multicast support exists */
#define MCAST 1

/* Define if all IPv6 headers/structures are present */
#define IPV6 1

/* Define if Debugging of sockets is desired */
/* #undef DB_MJZ */

#endif /* CONFIG_H */
