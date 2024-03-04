## Makefile.am for DirectFB/gfxdrivers/mt85

INCLUDES = \
	-I$(top_srcdir)/include	\
	-I$(top_builddir)/lib	\
	-I$(top_srcdir)/lib	\
	-I$(top_srcdir)/src	\
	-I$(top_srcdir)/systems \
	-I$(top_srcdir)/bd_inc

mt85_LTLIBRARIES = libdirectfb_mt85.la

if BUILD_STATIC
mt85_DATA = $(mt85_LTLIBRARIES:.la=.o)
endif

mt85dir = $(MODULEDIR)/gfxdrivers

libdirectfb_mt85_la_SOURCES =	\
	mt85_2d.c	\
	mt85_2d.h	\
	mt85_gfxdriver.c	\
	mt85_gfxdriver.h

libdirectfb_mt85_la_LDFLAGS = \
	-export-dynamic		\
	-avoid-version		\
	$(DFB_LDFLAGS)

libdirectfb_mt85_la_LIBADD = \
	$(top_builddir)/lib/direct/libdirect.la \
	$(top_builddir)/src/libdirectfb.la


include $(top_srcdir)/rules/libobject.make
	
