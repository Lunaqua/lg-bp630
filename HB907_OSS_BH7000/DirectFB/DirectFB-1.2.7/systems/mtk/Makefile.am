## Makefile.am for DirectFB/systems/mtk

INCLUDES = \
	-I$(top_srcdir)/include		\
	-I$(top_builddir)/include	\
	-I$(top_builddir)/lib		\
	-I$(top_srcdir)/lib		\
	-I$(top_srcdir)/src \
	-I$(top_srcdir)/bd_inc


internalincludedir = $(INTERNALINCLUDEDIR)/mtk

internalinclude_HEADERS = \
	mt85.h		\
	mt85_surfacemanager.h \
	mt85_vt.h


systemsdir = $(MODULEDIR)/systems

if BUILD_STATIC
systems_DATA = libdirectfb_mtk.o
endif
systems_LTLIBRARIES = libdirectfb_mtk.la

libdirectfb_mtk_la_LDFLAGS = \
	-avoid-version	\
	-module

libdirectfb_mtk_la_SOURCES = \
	mt85.c		\
	mt85_primary.c  \
	mt85_surface_pool.c	\
	mt85_surfacemanager.c	\
	mt85_vt.c

libdirectfb_mtk_la_LIBADD = \
	$(top_builddir)/lib/direct/libdirect.la \
	$(top_builddir)/lib/fusion/libfusion.la \
	$(top_builddir)/src/libdirectfb.la


include $(top_srcdir)/rules/libobject.make
