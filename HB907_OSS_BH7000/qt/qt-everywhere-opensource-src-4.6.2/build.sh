#!/bin/sh
#Script to build Qt by Yan Wang in MediaTek

if [ true ]; then

	unset MAKE
	export MAKE="make"

	QT_BUILD_FORCE=
	QT_CONFIG_MODIFIED=
	QT_PRINT_USAGE=
	QT_BACKUP_BIN=

#-------------------------------------------------------------------------------
# Check input option
#-------------------------------------------------------------------------------
	if [ $# -eq 1 ]; then
		if [ x$1 == "xclean" ]; then
				${MAKE} clean
				exit 0
			elif [ x$1 == "xconfclean" ]; then
				${MAKE} confclean
				exit 0
			elif [ x$1 == "x-f" ]; then
				QT_BUILD_FORCE=1
			else
				QT_PRINT_USAGE=1
		fi
	elif  [ $# -gt 1 ]; then
		QT_PRINT_USAGE=1
	fi

#-------------------------------------------------------------------------------
# Print usage and exit
#-------------------------------------------------------------------------------
	if [ -n "${QT_PRINT_USAGE}" ]; then
		echo
		echo "Usage: sh build.sh [options]"
		echo
		echo "  * Qt configuration could be customized by modifying the file ./config."
		echo "  * If Qt had been installed and no configuration changed, quick build"
		echo "  *     mode will be adopted."
		echo "  * At most only one option could be accepted by build.sh at a time."
		echo
		echo "Options:"
		echo "  clean       make clean"
		echo "  confclean   make confclean"
		echo "  -f          force build, ignore whether Qt configuration is modified"
		echo "  -h          This help"
		echo
		exit 0
	fi

#-------------------------------------------------------------------------------
# Check whether the file config exist and whether it is modified
#-------------------------------------------------------------------------------
	if [ -f config ]; then
		if [ -f config_pre ]; then
			cmp config config_pre > /dev/null
			if [ $? -ne 0 ]; then
				QT_CONFIG_MODIFIED=1
				echo
				echo "Qt configuration is modified"
				echo
			fi
		fi
	else
		echo
		echo -e "\033[40;31mQt Configuration File Not Found...\033[0m"
		echo -e "\033[40;31mPlease Check the File ./config\033[0m"
		echo
		exit 11
	fi

	unset DEST_DIR
	export DEST_DIR=$(pwd)/../../../library/qt

#-------------------------------------------------------------------------------
# Check whether the lib is already installed
# if it is installed and no force and no config modified, exit
# if it is installed and not exit, backup bin
#-------------------------------------------------------------------------------
	if [ -e ${DEST_DIR}/lib/libQtCore.so.4.6.2 ]; then
		if [ -z "${QT_BUILD_FORCE}" -a -z "${QT_CONFIG_MODIFIED}" ]; then
			echo
			echo -e "\033[40;32mQt Quick Build Success\033[0m"
			echo
			echo -e "    \033[0;36m*****************\033[0;37m Notice \033[0;36m*****************\033[0m"
			echo -e "    \033[0;36m*\033[0;37m   Qt configuration is not modified     \033[0;36m*\033[0m"
			echo -e "    \033[0;36m*\033[0;37m       So build Qt in quick mode        \033[0;36m*\033[0m"
			echo -e "    \033[0;36m*\033[0;37m   If you want to re-build Qt forcely   \033[0;36m*\033[0m"
			echo -e "    \033[0;36m*\033[0;37m       Please run \033[0;33msh build.sh -f\033[0;37m        \033[0;36m*\033[0m"
			echo -e "    \033[0;36m*\033[0;37m   If you want to get more help         \033[0;36m*\033[0m"
			echo -e "    \033[0;36m*\033[0;37m       Please run \033[0;33msh build.sh -h\033[0;37m        \033[0;36m*\033[0m"
			echo -e "    \033[0;36m******************************************\033[0m"
			echo
			exit 0
		else
			if [ "`uname -i`" != "i386" ]; then
				QT_BACKUP_BIN=1
				mv ${DEST_DIR}/bin ${DEST_DIR}/bin_i386
			fi
		fi
	fi

#-------------------------------------------------------------------------------
# Check source code and tarball
# if source code is already extracted and no config modified,
#   try to make it without re-config
#-------------------------------------------------------------------------------
	if [ -d src -a -d translations ]; then
		echo "Qt source code already extracted"
		echo		
		if [ -z "${QT_CONFIG_MODIFIED}" ]; then
			cp -f $(pwd)/patch/qdirectfbkeyboard.cpp $(pwd)/src/plugins/gfxdrivers/directfb
			cp -f $(pwd)/patch/qdirectfbscreen.cpp $(pwd)/src/plugins/gfxdrivers/directfb
			cp -f $(pwd)/patch/directfb.pri $(pwd)/src/gui/embedded
			cp -f $(pwd)/patch/qlineedit.cpp $(pwd)/src/gui/widgets
			cp -f $(pwd)/patch/qlineedit_p.cpp $(pwd)/src/gui/widgets
			touch $(pwd)/src/plugins/gfxdrivers/directfb/*
			touch $(pwd)/src/gui/widgets/qlineedit.cpp
			touch $(pwd)/src/gui/widgets/qlineedit_p.cpp
			${MAKE} install
			MAKE_RET=$?
			if [ ${MAKE_RET} -eq 0 ]; then
				echo
				echo -e "\033[40;32mQt Build Success\033[0m"
				echo
				if [ -n "${QT_BACKUP_BIN}" ]; then
					mv ${DEST_DIR}/bin ${DEST_DIR}/bin_not_use
					mv ${DEST_DIR}/bin_i386 ${DEST_DIR}/bin
				fi
				exit 0
			fi
		fi
	else
		if [ -f qt-everywhere-opensource-src-4.6.2.tar.bz2 ]; then
			echo "Qt source code tarball found"
			echo "Extracting......"
			tar xjf qt-everywhere-opensource-src-4.6.2.tar.bz2
			MAKE_RET=$?
			if [ ${MAKE_RET} -ne 0 ]; then
				echo
				echo -e "\033[40;31mExtract Qt Source Code Tarball Fail...(${MAKE_RET})\033[0m"
				echo
				if [ -n "${QT_BACKUP_BIN}" ]; then
					mv ${DEST_DIR}/bin ${DEST_DIR}/bin_not_use
					mv ${DEST_DIR}/bin_i386 ${DEST_DIR}/bin
				fi
				exit ${MAKE_RET}
			fi
			cp -f $(pwd)/patch/qdirectfbkeyboard.cpp $(pwd)/src/plugins/gfxdrivers/directfb
			cp -f $(pwd)/patch/qdirectfbscreen.cpp $(pwd)/src/plugins/gfxdrivers/directfb
			cp -f $(pwd)/patch/directfb.pri $(pwd)/src/gui/embedded
			cp -f $(pwd)/patch/qlineedit.cpp $(pwd)/src/gui/widgets
			cp -f $(pwd)/patch/qlineedit_p.cpp $(pwd)/src/gui/widgets
			touch $(pwd)/src/plugins/gfxdrivers/directfb/*
			touch $(pwd)/src/gui/widgets/qlineedit.cpp
			touch $(pwd)/src/gui/widgets/qlineedit_p.cpp
		else
			echo
			echo -e "\033[40;31mQt Source Code Tarball Not Found...\033[0m"
			echo
			if [ -n "${QT_BACKUP_BIN}" ]; then
				mv ${DEST_DIR}/bin ${DEST_DIR}/bin_not_use
				mv ${DEST_DIR}/bin_i386 ${DEST_DIR}/bin
			fi
			exit 13
		fi
	fi

#-------------------------------------------------------------------------------
# Make confclean and re-config
#-------------------------------------------------------------------------------
	${MAKE} confclean

	export DFB_DIR=$(pwd)/../../../library/DirectFB
	export DFB_INC_DIR=${DFB_DIR}/usr/include/directfb
	export DFB_LIB_DIR=${DFB_DIR}/lib

	export QT_CFLAGS_DIRECTFB="-I${DFB_INC_DIR} -D_REENTRANT"
	export QT_LIBS_DIRECTFB="-L${DFB_LIB_DIR} -ldirectfb -lfusion -ldirect"

	sh ./config

	MAKE_RET=$?
	if [ ${MAKE_RET} -ne 0 ]; then
		echo
		echo -e "\033[40;31mConfigure Qt Fail...(${MAKE_RET})\033[0m"
		echo -e "\033[40;31mPlease Check the File ./config\033[0m"
		echo
		if [ -n "${QT_BACKUP_BIN}" ]; then
			mv ${DEST_DIR}/bin ${DEST_DIR}/bin_not_use
			mv ${DEST_DIR}/bin_i386 ${DEST_DIR}/bin
		fi
		exit ${MAKE_RET}
	fi

#-------------------------------------------------------------------------------
# Make install, if success and config modified, backup it
#-------------------------------------------------------------------------------
	${MAKE} install

	MAKE_RET=$?
	if [ ${MAKE_RET} -ne 0 ]; then
		echo -e "\033[40;31mQt Build Fail...(${MAKE_RET})\033[0m"
	else
		if [ -n "${QT_CONFIG_MODIFIED}" ]; then
			rm -f ./config_pre
			cp config config_pre
		fi
		echo
		echo -e "\033[40;32mQt Build Success\033[0m"
		echo
	fi

	if [ -n "${QT_BACKUP_BIN}" ]; then
		mv ${DEST_DIR}/bin ${DEST_DIR}/bin_not_use
		mv ${DEST_DIR}/bin_i386 ${DEST_DIR}/bin
	fi

	exit ${MAKE_RET}

else
   echo
   echo -e "\033[40;31mQt is not supported!\033[0m"
   echo
   exit 0
fi
