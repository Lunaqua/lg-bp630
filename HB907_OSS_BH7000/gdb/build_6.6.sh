#pathch gdb-6.6
patch -N -p1 -d gdb-6.6 < gdb-6.6-patch/gdb-6.6-thread_db_use_events_mtk.patch
#build in arm platform
./gdb-6.6/configure --prefix=/usr --build=armv6z-mediatek-linux-gnueabi
make 
make install
