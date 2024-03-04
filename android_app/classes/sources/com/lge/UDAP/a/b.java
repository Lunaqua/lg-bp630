package com.lge.UDAP.a;

import java.nio.ByteBuffer;
import java.util.zip.CRC32;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class b {
    public static final int a = 4;
    public static final int b = 1;
    public static final int c = 4;
    public static final int d = 17;
    public static final int e = 4;
    public static final int f = 4;
    public static final int g = 9;
    private byte[] h;

    public b(String str, String str2, String str3, byte b2) {
        this.h = null;
        byte[] bytes = str.getBytes();
        byte[] bytes2 = str2.getBytes();
        byte[] bytes3 = str3.getBytes();
        Integer valueOf = Integer.valueOf(str2.length());
        Integer valueOf2 = Integer.valueOf(str3.length());
        int intValue = valueOf.intValue() + 21 + 4 + valueOf2.intValue();
        int i = intValue + 9;
        ByteBuffer allocate = ByteBuffer.allocate(i);
        allocate.order(a.b);
        allocate.putInt(0);
        allocate.put(b2);
        allocate.putInt(intValue);
        allocate.put(bytes);
        allocate.putInt(valueOf.intValue());
        allocate.put(bytes2);
        allocate.putInt(valueOf2.intValue());
        allocate.put(bytes3);
        allocate.putInt(0, a(allocate.array(), i - intValue));
        this.h = allocate.array();
    }

    private int a(byte[] bArr, int i) {
        CRC32 crc32 = new CRC32();
        crc32.update(bArr, i, bArr.length - i);
        return (int) crc32.getValue();
    }

    public byte[] a() {
        return this.h;
    }
}
