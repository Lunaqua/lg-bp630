package com.google.protobuf;

import android.support.v7.widget.ActivityChooserView;
import com.google.protobuf.MessageLite;
import java.io.InputStream;
import java.util.ArrayList;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class CodedInputStream {
    private static final int l = 64;
    private static final int m = 67108864;
    private static final int n = 4096;
    private final byte[] a;
    private int b;
    private int c;
    private int d;
    private final InputStream e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;

    private CodedInputStream(InputStream inputStream) {
        this.h = ActivityChooserView.a.a;
        this.j = 64;
        this.k = m;
        this.a = new byte[4096];
        this.b = 0;
        this.d = 0;
        this.g = 0;
        this.e = inputStream;
    }

    private CodedInputStream(byte[] bArr, int i, int i2) {
        this.h = ActivityChooserView.a.a;
        this.j = 64;
        this.k = m;
        this.a = bArr;
        this.b = i2 + i;
        this.d = i;
        this.g = -i;
        this.e = null;
    }

    private void B() {
        this.b += this.c;
        int i = this.g;
        int i2 = this.b;
        int i3 = i + i2;
        int i4 = this.h;
        if (i3 <= i4) {
            this.c = 0;
            return;
        }
        this.c = i3 - i4;
        this.b = i2 - this.c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int i, InputStream inputStream) {
        if ((i & 128) == 0) {
            return i;
        }
        int i2 = i & 127;
        int i3 = 7;
        while (i3 < 32) {
            int read = inputStream.read();
            if (read == -1) {
                throw InvalidProtocolBufferException.a();
            }
            i2 |= (read & 127) << i3;
            if ((read & 128) == 0) {
                return i2;
            }
            i3 += 7;
        }
        while (i3 < 64) {
            int read2 = inputStream.read();
            if (read2 == -1) {
                throw InvalidProtocolBufferException.a();
            }
            if ((read2 & 128) == 0) {
                return i2;
            }
            i3 += 7;
        }
        throw InvalidProtocolBufferException.c();
    }

    public static long a(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static CodedInputStream a(InputStream inputStream) {
        return new CodedInputStream(inputStream);
    }

    public static CodedInputStream a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static CodedInputStream a(byte[] bArr, int i, int i2) {
        return new CodedInputStream(bArr, i, i2);
    }

    private boolean a(boolean z) {
        int i = this.d;
        int i2 = this.b;
        if (i >= i2) {
            int i3 = this.g;
            if (i3 + i2 == this.h) {
                if (z) {
                    throw InvalidProtocolBufferException.a();
                }
                return false;
            }
            this.g = i3 + i2;
            this.d = 0;
            InputStream inputStream = this.e;
            this.b = inputStream == null ? -1 : inputStream.read(this.a);
            int i4 = this.b;
            if (i4 == 0 || i4 < -1) {
                throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + this.b + "\nThe InputStream implementation is buggy.");
            } else if (i4 == -1) {
                this.b = 0;
                if (z) {
                    throw InvalidProtocolBufferException.a();
                }
                return false;
            } else {
                B();
                int i5 = this.g + this.b + this.c;
                if (i5 > this.k || i5 < 0) {
                    throw InvalidProtocolBufferException.h();
                }
                return true;
            }
        }
        throw new IllegalStateException("refillBuffer() called when buffer wasn't empty.");
    }

    static int b(InputStream inputStream) {
        int read = inputStream.read();
        if (read != -1) {
            return a(read, inputStream);
        }
        throw InvalidProtocolBufferException.a();
    }

    public static int c(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public byte A() {
        if (this.d == this.b) {
            a(true);
        }
        byte[] bArr = this.a;
        int i = this.d;
        this.d = i + 1;
        return bArr[i];
    }

    public int a() {
        if (y()) {
            this.f = 0;
            return 0;
        }
        this.f = s();
        if (WireFormat.b(this.f) != 0) {
            return this.f;
        }
        throw InvalidProtocolBufferException.d();
    }

    public void a(int i) {
        if (this.f != i) {
            throw InvalidProtocolBufferException.e();
        }
    }

    @Deprecated
    public void a(int i, MessageLite.Builder builder) {
        a(i, builder, (ExtensionRegistryLite) null);
    }

    public void a(int i, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) {
        int i2 = this.i;
        if (i2 >= this.j) {
            throw InvalidProtocolBufferException.g();
        }
        this.i = i2 + 1;
        builder.mergeFrom(this, extensionRegistryLite);
        a(WireFormat.a(i, 4));
        this.i--;
    }

    public void a(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) {
        int s = s();
        if (this.i >= this.j) {
            throw InvalidProtocolBufferException.g();
        }
        int f = f(s);
        this.i++;
        builder.mergeFrom(this, extensionRegistryLite);
        a(0);
        this.i--;
        g(f);
    }

    public void b() {
        int a;
        do {
            a = a();
            if (a == 0) {
                return;
            }
        } while (b(a));
    }

    public boolean b(int i) {
        int a = WireFormat.a(i);
        if (a == 0) {
            g();
            return true;
        } else if (a == 1) {
            v();
            return true;
        } else if (a == 2) {
            i(s());
            return true;
        } else if (a == 3) {
            b();
            a(WireFormat.a(WireFormat.b(i), 4));
            return true;
        } else if (a != 4) {
            if (a == 5) {
                u();
                return true;
            }
            throw InvalidProtocolBufferException.f();
        } else {
            return false;
        }
    }

    public double c() {
        return Double.longBitsToDouble(v());
    }

    public float d() {
        return Float.intBitsToFloat(u());
    }

    public int d(int i) {
        if (i >= 0) {
            int i2 = this.j;
            this.j = i;
            return i2;
        }
        throw new IllegalArgumentException("Recursion limit cannot be negative: " + i);
    }

    public int e(int i) {
        if (i >= 0) {
            int i2 = this.k;
            this.k = i;
            return i2;
        }
        throw new IllegalArgumentException("Size limit cannot be negative: " + i);
    }

    public long e() {
        return t();
    }

    public int f(int i) {
        if (i >= 0) {
            int i2 = i + this.g + this.d;
            int i3 = this.h;
            if (i2 <= i3) {
                this.h = i2;
                B();
                return i3;
            }
            throw InvalidProtocolBufferException.a();
        }
        throw InvalidProtocolBufferException.b();
    }

    public long f() {
        return t();
    }

    public int g() {
        return s();
    }

    public void g(int i) {
        this.h = i;
        B();
    }

    public long h() {
        return v();
    }

    public byte[] h(int i) {
        if (i < 0) {
            throw InvalidProtocolBufferException.b();
        }
        int i2 = this.g;
        int i3 = this.d;
        int i4 = i2 + i3 + i;
        int i5 = this.h;
        if (i4 > i5) {
            i((i5 - i2) - i3);
            throw InvalidProtocolBufferException.a();
        }
        int i6 = this.b;
        if (i <= i6 - i3) {
            byte[] bArr = new byte[i];
            System.arraycopy(this.a, i3, bArr, 0, i);
            this.d += i;
            return bArr;
        } else if (i >= 4096) {
            this.g = i2 + i6;
            this.d = 0;
            this.b = 0;
            int i7 = i6 - i3;
            int i8 = i - i7;
            ArrayList<byte[]> arrayList = new ArrayList();
            while (i8 > 0) {
                byte[] bArr2 = new byte[Math.min(i8, 4096)];
                int i9 = 0;
                while (i9 < bArr2.length) {
                    InputStream inputStream = this.e;
                    int read = inputStream == null ? -1 : inputStream.read(bArr2, i9, bArr2.length - i9);
                    if (read == -1) {
                        throw InvalidProtocolBufferException.a();
                    }
                    this.g += read;
                    i9 += read;
                }
                i8 -= bArr2.length;
                arrayList.add(bArr2);
            }
            byte[] bArr3 = new byte[i];
            System.arraycopy(this.a, i3, bArr3, 0, i7);
            for (byte[] bArr4 : arrayList) {
                System.arraycopy(bArr4, 0, bArr3, i7, bArr4.length);
                i7 += bArr4.length;
            }
            return bArr3;
        } else {
            byte[] bArr5 = new byte[i];
            int i10 = i6 - i3;
            System.arraycopy(this.a, i3, bArr5, 0, i10);
            this.d = this.b;
            while (true) {
                a(true);
                int i11 = i - i10;
                int i12 = this.b;
                if (i11 <= i12) {
                    System.arraycopy(this.a, 0, bArr5, i10, i11);
                    this.d = i11;
                    return bArr5;
                }
                System.arraycopy(this.a, 0, bArr5, i10, i12);
                int i13 = this.b;
                i10 += i13;
                this.d = i13;
            }
        }
    }

    public int i() {
        return u();
    }

    public void i(int i) {
        if (i < 0) {
            throw InvalidProtocolBufferException.b();
        }
        int i2 = this.g;
        int i3 = this.d;
        int i4 = i2 + i3 + i;
        int i5 = this.h;
        if (i4 > i5) {
            i((i5 - i2) - i3);
            throw InvalidProtocolBufferException.a();
        }
        int i6 = this.b;
        if (i <= i6 - i3) {
            this.d = i3 + i;
            return;
        }
        int i7 = i6 - i3;
        this.g = i2 + i7;
        this.d = 0;
        this.b = 0;
        while (i7 < i) {
            InputStream inputStream = this.e;
            int skip = inputStream == null ? -1 : (int) inputStream.skip(i - i7);
            if (skip <= 0) {
                throw InvalidProtocolBufferException.a();
            }
            i7 += skip;
            this.g += skip;
        }
    }

    public boolean j() {
        return s() != 0;
    }

    public String k() {
        int s = s();
        int i = this.b;
        int i2 = this.d;
        if (s > i - i2 || s <= 0) {
            return new String(h(s), "UTF-8");
        }
        String str = new String(this.a, i2, s, "UTF-8");
        this.d += s;
        return str;
    }

    public ByteString l() {
        int s = s();
        int i = this.b;
        int i2 = this.d;
        if (s > i - i2 || s <= 0) {
            return ByteString.a(h(s));
        }
        ByteString a = ByteString.a(this.a, i2, s);
        this.d += s;
        return a;
    }

    public int m() {
        return s();
    }

    public int n() {
        return s();
    }

    public int o() {
        return u();
    }

    public long p() {
        return v();
    }

    public int q() {
        return c(s());
    }

    public long r() {
        return a(t());
    }

    public int s() {
        int i;
        byte A = A();
        if (A >= 0) {
            return A;
        }
        int i2 = A & Byte.MAX_VALUE;
        byte A2 = A();
        if (A2 >= 0) {
            i = A2 << 7;
        } else {
            i2 |= (A2 & Byte.MAX_VALUE) << 7;
            byte A3 = A();
            if (A3 >= 0) {
                i = A3 << 14;
            } else {
                i2 |= (A3 & Byte.MAX_VALUE) << 14;
                byte A4 = A();
                if (A4 < 0) {
                    int i3 = i2 | ((A4 & Byte.MAX_VALUE) << 21);
                    byte A5 = A();
                    int i4 = i3 | (A5 << 28);
                    if (A5 < 0) {
                        for (int i5 = 0; i5 < 5; i5++) {
                            if (A() >= 0) {
                                return i4;
                            }
                        }
                        throw InvalidProtocolBufferException.c();
                    }
                    return i4;
                }
                i = A4 << 21;
            }
        }
        return i2 | i;
    }

    public long t() {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte A = A();
            j |= (A & Byte.MAX_VALUE) << i;
            if ((A & 128) == 0) {
                return j;
            }
        }
        throw InvalidProtocolBufferException.c();
    }

    public int u() {
        return (A() & 255) | ((A() & 255) << 8) | ((A() & 255) << 16) | ((A() & 255) << 24);
    }

    public long v() {
        byte A = A();
        byte A2 = A();
        return ((A2 & 255) << 8) | (A & 255) | ((A() & 255) << 16) | ((A() & 255) << 24) | ((A() & 255) << 32) | ((A() & 255) << 40) | ((A() & 255) << 48) | ((A() & 255) << 56);
    }

    public void w() {
        this.g = -this.d;
    }

    public int x() {
        int i = this.h;
        if (i == Integer.MAX_VALUE) {
            return -1;
        }
        return i - (this.g + this.d);
    }

    public boolean y() {
        return this.d == this.b && !a(false);
    }

    public int z() {
        return this.g + this.d;
    }
}
