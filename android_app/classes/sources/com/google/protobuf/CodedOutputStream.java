package com.google.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class CodedOutputStream {
    public static final int a = 4096;
    public static final int b = 4;
    public static final int c = 8;
    private final byte[] d;
    private final int e;
    private int f;
    private final OutputStream g;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class OutOfSpaceException extends IOException {
        private static final long a = -6947486886997889499L;

        OutOfSpaceException() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
    }

    private CodedOutputStream(OutputStream outputStream, byte[] bArr) {
        this.g = outputStream;
        this.d = bArr;
        this.f = 0;
        this.e = bArr.length;
    }

    private CodedOutputStream(byte[] bArr, int i, int i2) {
        this.g = null;
        this.d = bArr;
        this.f = i;
        this.e = i + i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int i) {
        if (i > 4096) {
            return 4096;
        }
        return i;
    }

    public static CodedOutputStream a(OutputStream outputStream) {
        return a(outputStream, 4096);
    }

    public static CodedOutputStream a(OutputStream outputStream, int i) {
        return new CodedOutputStream(outputStream, new byte[i]);
    }

    public static CodedOutputStream a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static CodedOutputStream a(byte[] bArr, int i, int i2) {
        return new CodedOutputStream(bArr, i, i2);
    }

    public static int b(double d) {
        return 8;
    }

    public static int b(float f) {
        return 4;
    }

    public static int b(int i, double d) {
        return o(i) + b(d);
    }

    public static int b(int i, float f) {
        return o(i) + b(f);
    }

    public static int b(int i, String str) {
        return o(i) + b(str);
    }

    public static int b(int i, boolean z) {
        return o(i) + b(z);
    }

    public static int b(ByteString byteString) {
        return q(byteString.a()) + byteString.a();
    }

    public static int b(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            return q(bytes.length) + bytes.length;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported.", e);
        }
    }

    public static int b(boolean z) {
        return 1;
    }

    public static int c(int i, ByteString byteString) {
        return o(i) + b(byteString);
    }

    public static int d(int i, ByteString byteString) {
        return (o(1) * 2) + i(2, i) + c(3, byteString);
    }

    public static int d(MessageLite messageLite) {
        return messageLite.getSerializedSize();
    }

    private void d() {
        OutputStream outputStream = this.g;
        if (outputStream == null) {
            throw new OutOfSpaceException();
        }
        outputStream.write(this.d, 0, this.f);
        this.f = 0;
    }

    public static int e(int i, MessageLite messageLite) {
        return (o(i) * 2) + d(messageLite);
    }

    @Deprecated
    public static int e(MessageLite messageLite) {
        return d(messageLite);
    }

    public static int f(int i, long j) {
        return o(i) + f(j);
    }

    @Deprecated
    public static int f(int i, MessageLite messageLite) {
        return e(i, messageLite);
    }

    public static int f(long j) {
        return l(j);
    }

    public static int f(MessageLite messageLite) {
        int serializedSize = messageLite.getSerializedSize();
        return q(serializedSize) + serializedSize;
    }

    public static int g(int i, int i2) {
        return o(i) + h(i2);
    }

    public static int g(int i, long j) {
        return o(i) + g(j);
    }

    public static int g(int i, MessageLite messageLite) {
        return o(i) + f(messageLite);
    }

    public static int g(long j) {
        return l(j);
    }

    public static int h(int i) {
        if (i >= 0) {
            return q(i);
        }
        return 10;
    }

    public static int h(int i, int i2) {
        return o(i) + i(i2);
    }

    public static int h(int i, long j) {
        return o(i) + h(j);
    }

    public static int h(int i, MessageLite messageLite) {
        return (o(1) * 2) + i(2, i) + g(3, messageLite);
    }

    public static int h(long j) {
        return 8;
    }

    public static int i(int i) {
        return 4;
    }

    public static int i(int i, int i2) {
        return o(i) + j(i2);
    }

    public static int i(int i, long j) {
        return o(i) + i(j);
    }

    public static int i(long j) {
        return 8;
    }

    public static int j(int i) {
        return q(i);
    }

    public static int j(int i, int i2) {
        return o(i) + k(i2);
    }

    public static int j(int i, long j) {
        return o(i) + j(j);
    }

    public static int j(long j) {
        return l(n(j));
    }

    public static int k(int i) {
        return q(i);
    }

    public static int k(int i, int i2) {
        return o(i) + l(i2);
    }

    public static int l(int i) {
        return 4;
    }

    public static int l(int i, int i2) {
        return o(i) + m(i2);
    }

    public static int l(long j) {
        if (((-128) & j) == 0) {
            return 1;
        }
        if (((-16384) & j) == 0) {
            return 2;
        }
        if (((-2097152) & j) == 0) {
            return 3;
        }
        if (((-268435456) & j) == 0) {
            return 4;
        }
        if (((-34359738368L) & j) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j) == 0) {
            return 8;
        }
        return (j & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    public static int m(int i) {
        return q(s(i));
    }

    public static long n(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int o(int i) {
        return q(WireFormat.a(i, 0));
    }

    public static int q(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public static int s(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public void a() {
        if (this.g != null) {
            d();
        }
    }

    public void a(byte b2) {
        if (this.f == this.e) {
            d();
        }
        byte[] bArr = this.d;
        int i = this.f;
        this.f = i + 1;
        bArr[i] = b2;
    }

    public void a(double d) {
        m(Double.doubleToRawLongBits(d));
    }

    public void a(float f) {
        r(Float.floatToRawIntBits(f));
    }

    public void a(int i, double d) {
        m(i, 1);
        a(d);
    }

    public void a(int i, float f) {
        m(i, 5);
        a(f);
    }

    public void a(int i, int i2) {
        m(i, 0);
        b(i2);
    }

    public void a(int i, long j) {
        m(i, 0);
        a(j);
    }

    public void a(int i, ByteString byteString) {
        m(i, 2);
        a(byteString);
    }

    public void a(int i, MessageLite messageLite) {
        m(i, 3);
        a(messageLite);
        m(i, 4);
    }

    public void a(int i, String str) {
        m(i, 2);
        a(str);
    }

    public void a(int i, boolean z) {
        m(i, 0);
        a(z);
    }

    public void a(long j) {
        k(j);
    }

    public void a(ByteString byteString) {
        byte[] c2 = byteString.c();
        p(c2.length);
        b(c2);
    }

    public void a(MessageLite messageLite) {
        messageLite.writeTo(this);
    }

    public void a(String str) {
        byte[] bytes = str.getBytes("UTF-8");
        p(bytes.length);
        b(bytes);
    }

    public void a(boolean z) {
        n(z ? 1 : 0);
    }

    public int b() {
        if (this.g == null) {
            return this.e - this.f;
        }
        throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array.");
    }

    public void b(int i) {
        if (i >= 0) {
            p(i);
        } else {
            k(i);
        }
    }

    public void b(int i, int i2) {
        m(i, 5);
        c(i2);
    }

    public void b(int i, long j) {
        m(i, 0);
        b(j);
    }

    public void b(int i, ByteString byteString) {
        m(1, 3);
        c(2, i);
        a(3, byteString);
        m(1, 4);
    }

    @Deprecated
    public void b(int i, MessageLite messageLite) {
        a(i, messageLite);
    }

    public void b(long j) {
        k(j);
    }

    @Deprecated
    public void b(MessageLite messageLite) {
        a(messageLite);
    }

    public void b(byte[] bArr) {
        b(bArr, 0, bArr.length);
    }

    public void b(byte[] bArr, int i, int i2) {
        int i3 = this.e;
        int i4 = this.f;
        if (i3 - i4 >= i2) {
            System.arraycopy(bArr, i, this.d, i4, i2);
            this.f += i2;
            return;
        }
        int i5 = i3 - i4;
        System.arraycopy(bArr, i, this.d, i4, i5);
        int i6 = i + i5;
        int i7 = i2 - i5;
        this.f = this.e;
        d();
        if (i7 > this.e) {
            this.g.write(bArr, i6, i7);
            return;
        }
        System.arraycopy(bArr, i6, this.d, 0, i7);
        this.f = i7;
    }

    public void c() {
        if (b() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public void c(int i) {
        r(i);
    }

    public void c(int i, int i2) {
        m(i, 0);
        d(i2);
    }

    public void c(int i, long j) {
        m(i, 1);
        c(j);
    }

    public void c(int i, MessageLite messageLite) {
        m(i, 2);
        c(messageLite);
    }

    public void c(long j) {
        m(j);
    }

    public void c(MessageLite messageLite) {
        p(messageLite.getSerializedSize());
        messageLite.writeTo(this);
    }

    public void d(int i) {
        p(i);
    }

    public void d(int i, int i2) {
        m(i, 0);
        e(i2);
    }

    public void d(int i, long j) {
        m(i, 1);
        d(j);
    }

    public void d(int i, MessageLite messageLite) {
        m(1, 3);
        c(2, i);
        c(3, messageLite);
        m(1, 4);
    }

    public void d(long j) {
        m(j);
    }

    public void e(int i) {
        p(i);
    }

    public void e(int i, int i2) {
        m(i, 5);
        f(i2);
    }

    public void e(int i, long j) {
        m(i, 0);
        e(j);
    }

    public void e(long j) {
        k(n(j));
    }

    public void f(int i) {
        r(i);
    }

    public void f(int i, int i2) {
        m(i, 0);
        g(i2);
    }

    public void g(int i) {
        p(s(i));
    }

    public void k(long j) {
        while (((-128) & j) != 0) {
            n((((int) j) & 127) | 128);
            j >>>= 7;
        }
        n((int) j);
    }

    public void m(int i, int i2) {
        p(WireFormat.a(i, i2));
    }

    public void m(long j) {
        n(((int) j) & 255);
        n(((int) (j >> 8)) & 255);
        n(((int) (j >> 16)) & 255);
        n(((int) (j >> 24)) & 255);
        n(((int) (j >> 32)) & 255);
        n(((int) (j >> 40)) & 255);
        n(((int) (j >> 48)) & 255);
        n(((int) (j >> 56)) & 255);
    }

    public void n(int i) {
        a((byte) i);
    }

    public void p(int i) {
        while ((i & (-128)) != 0) {
            n((i & 127) | 128);
            i >>>= 7;
        }
        n(i);
    }

    public void r(int i) {
        n(i & 255);
        n((i >> 8) & 255);
        n((i >> 16) & 255);
        n((i >> 24) & 255);
    }
}
