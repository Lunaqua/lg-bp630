package com.google.protobuf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class ByteString {
    public static final ByteString a = new ByteString(new byte[0]);
    private final byte[] b;
    private volatile int c;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static final class CodedBuilder {
        private final CodedOutputStream a;
        private final byte[] b;

        private CodedBuilder(int i) {
            this.b = new byte[i];
            this.a = CodedOutputStream.a(this.b);
        }

        public ByteString a() {
            this.a.c();
            return new ByteString(this.b);
        }

        public CodedOutputStream b() {
            return this.a;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class Output extends FilterOutputStream {
        private final ByteArrayOutputStream a;

        private Output(ByteArrayOutputStream byteArrayOutputStream) {
            super(byteArrayOutputStream);
            this.a = byteArrayOutputStream;
        }

        public ByteString a() {
            return new ByteString(this.a.toByteArray());
        }
    }

    private ByteString(byte[] bArr) {
        this.c = 0;
        this.b = bArr;
    }

    public static ByteString a(String str) {
        try {
            return new ByteString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported?", e);
        }
    }

    public static ByteString a(String str, String str2) {
        return new ByteString(str.getBytes(str2));
    }

    public static ByteString a(ByteBuffer byteBuffer) {
        return a(byteBuffer, byteBuffer.remaining());
    }

    public static ByteString a(ByteBuffer byteBuffer, int i) {
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        return new ByteString(bArr);
    }

    public static ByteString a(List<ByteString> list) {
        if (list.size() == 0) {
            return a;
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        int i = 0;
        for (ByteString byteString : list) {
            i += byteString.a();
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (ByteString byteString2 : list) {
            System.arraycopy(byteString2.b, 0, bArr, i2, byteString2.a());
            i2 += byteString2.a();
        }
        return new ByteString(bArr);
    }

    public static ByteString a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static ByteString a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new ByteString(bArr2);
    }

    public static Output b(int i) {
        return new Output(new ByteArrayOutputStream(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CodedBuilder c(int i) {
        return new CodedBuilder(i);
    }

    public static Output h() {
        return b(32);
    }

    public byte a(int i) {
        return this.b[i];
    }

    public int a() {
        return this.b.length;
    }

    public void a(byte[] bArr, int i) {
        byte[] bArr2 = this.b;
        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
    }

    public void a(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.b, i, bArr, i2, i3);
    }

    public String b(String str) {
        return new String(this.b, str);
    }

    public boolean b() {
        return this.b.length == 0;
    }

    public byte[] c() {
        byte[] bArr = this.b;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    public ByteBuffer d() {
        return ByteBuffer.wrap(this.b).asReadOnlyBuffer();
    }

    public String e() {
        try {
            return new String(this.b, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported?", e);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            byte[] bArr = this.b;
            int length = bArr.length;
            byte[] bArr2 = ((ByteString) obj).b;
            if (length != bArr2.length) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                if (bArr[i] != bArr2[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public InputStream f() {
        return new ByteArrayInputStream(this.b);
    }

    public CodedInputStream g() {
        return CodedInputStream.a(this.b);
    }

    public int hashCode() {
        int i = this.c;
        if (i == 0) {
            byte[] bArr = this.b;
            int length = bArr.length;
            for (byte b : bArr) {
                length = (length * 31) + b;
            }
            i = length == 0 ? 1 : length;
            this.c = i;
        }
        return i;
    }
}
