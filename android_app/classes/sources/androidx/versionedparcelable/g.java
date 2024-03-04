package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import android.support.annotation.an;
import android.util.SparseArray;
import androidx.versionedparcelable.e;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Set;
@an(a = {an.a.LIBRARY})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class g extends e {
    private static final Charset a = Charset.forName("UTF-16");
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final int f = 4;
    private static final int g = 5;
    private static final int h = 6;
    private static final int i = 7;
    private static final int j = 8;
    private static final int k = 9;
    private static final int l = 10;
    private static final int m = 11;
    private static final int n = 12;
    private static final int o = 13;
    private static final int p = 14;
    private final DataInputStream q;
    private final DataOutputStream r;
    private final SparseArray<b> s = new SparseArray<>();
    private DataInputStream t;
    private DataOutputStream u;
    private a v;
    private boolean w;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a {
        final ByteArrayOutputStream a = new ByteArrayOutputStream();
        final DataOutputStream b = new DataOutputStream(this.a);
        private final int c;
        private final DataOutputStream d;

        a(int i, DataOutputStream dataOutputStream) {
            this.c = i;
            this.d = dataOutputStream;
        }

        void a() {
            this.b.flush();
            int size = this.a.size();
            this.d.writeInt((this.c << 16) | (size >= 65535 ? android.support.v4.d.a.a.a : size));
            if (size >= 65535) {
                this.d.writeInt(size);
            }
            this.a.writeTo(this.d);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class b {
        final DataInputStream a;
        final int b;
        private final int c;

        b(int i, int i2, DataInputStream dataInputStream) {
            this.c = i2;
            this.b = i;
            byte[] bArr = new byte[this.c];
            dataInputStream.readFully(bArr);
            this.a = new DataInputStream(new ByteArrayInputStream(bArr));
        }
    }

    public g(InputStream inputStream, OutputStream outputStream) {
        this.q = inputStream != null ? new DataInputStream(inputStream) : null;
        this.r = outputStream != null ? new DataOutputStream(outputStream) : null;
        this.t = this.q;
        this.u = this.r;
    }

    private void a(int i2, String str, Bundle bundle) {
        switch (i2) {
            case 0:
                bundle.putParcelable(str, null);
                return;
            case 1:
            case 2:
                bundle.putBundle(str, l());
                return;
            case 3:
                bundle.putString(str, h());
                return;
            case 4:
                bundle.putStringArray(str, (String[]) b(new String[0]));
                return;
            case 5:
                bundle.putBoolean(str, m());
                return;
            case 6:
                bundle.putBooleanArray(str, n());
                return;
            case 7:
                bundle.putDouble(str, g());
                return;
            case 8:
                bundle.putDoubleArray(str, r());
                return;
            case 9:
                bundle.putInt(str, d());
                return;
            case 10:
                bundle.putIntArray(str, o());
                return;
            case 11:
                bundle.putLong(str, e());
                return;
            case 12:
                bundle.putLongArray(str, p());
                return;
            case 13:
                bundle.putFloat(str, f());
                return;
            case 14:
                bundle.putFloatArray(str, q());
                return;
            default:
                throw new RuntimeException("Unknown type " + i2);
        }
    }

    private void a(Object obj) {
        int intValue;
        if (obj == null) {
            intValue = 0;
        } else if (obj instanceof Bundle) {
            a(1);
            a((Bundle) obj);
            return;
        } else if (obj instanceof String) {
            a(3);
            a((String) obj);
            return;
        } else if (obj instanceof String[]) {
            a(4);
            a((Object[]) ((String[]) obj));
            return;
        } else if (obj instanceof Boolean) {
            a(5);
            a(((Boolean) obj).booleanValue());
            return;
        } else if (obj instanceof boolean[]) {
            a(6);
            a((boolean[]) obj);
            return;
        } else if (obj instanceof Double) {
            a(7);
            a(((Double) obj).doubleValue());
            return;
        } else if (obj instanceof double[]) {
            a(8);
            a((double[]) obj);
            return;
        } else if (!(obj instanceof Integer)) {
            if (obj instanceof int[]) {
                a(10);
                a((int[]) obj);
                return;
            } else if (obj instanceof Long) {
                a(11);
                a(((Long) obj).longValue());
                return;
            } else if (obj instanceof long[]) {
                a(12);
                a((long[]) obj);
                return;
            } else if (obj instanceof Float) {
                a(13);
                a(((Float) obj).floatValue());
                return;
            } else if (obj instanceof float[]) {
                a(14);
                a((float[]) obj);
                return;
            } else {
                throw new IllegalArgumentException("Unsupported type " + obj.getClass());
            }
        } else {
            a(9);
            intValue = ((Integer) obj).intValue();
        }
        a(intValue);
    }

    @Override // androidx.versionedparcelable.e
    public void a(double d2) {
        try {
            this.u.writeDouble(d2);
        } catch (IOException e2) {
            throw new e.a(e2);
        }
    }

    @Override // androidx.versionedparcelable.e
    public void a(float f2) {
        try {
            this.u.writeFloat(f2);
        } catch (IOException e2) {
            throw new e.a(e2);
        }
    }

    @Override // androidx.versionedparcelable.e
    public void a(int i2) {
        try {
            this.u.writeInt(i2);
        } catch (IOException e2) {
            throw new e.a(e2);
        }
    }

    @Override // androidx.versionedparcelable.e
    public void a(long j2) {
        try {
            this.u.writeLong(j2);
        } catch (IOException e2) {
            throw new e.a(e2);
        }
    }

    @Override // androidx.versionedparcelable.e
    public void a(Bundle bundle) {
        try {
            if (bundle == null) {
                this.u.writeInt(-1);
                return;
            }
            Set<String> keySet = bundle.keySet();
            this.u.writeInt(keySet.size());
            for (String str : keySet) {
                a(str);
                a(bundle.get(str));
            }
        } catch (IOException e2) {
            throw new e.a(e2);
        }
    }

    @Override // androidx.versionedparcelable.e
    public void a(IBinder iBinder) {
        if (!this.w) {
            throw new RuntimeException("Binders cannot be written to an OutputStream");
        }
    }

    @Override // androidx.versionedparcelable.e
    public void a(IInterface iInterface) {
        if (!this.w) {
            throw new RuntimeException("Binders cannot be written to an OutputStream");
        }
    }

    @Override // androidx.versionedparcelable.e
    public void a(Parcelable parcelable) {
        if (!this.w) {
            throw new RuntimeException("Parcelables cannot be written to an OutputStream");
        }
    }

    @Override // androidx.versionedparcelable.e
    public void a(String str) {
        try {
            if (str == null) {
                this.u.writeInt(-1);
                return;
            }
            byte[] bytes = str.getBytes(a);
            this.u.writeInt(bytes.length);
            this.u.write(bytes);
        } catch (IOException e2) {
            throw new e.a(e2);
        }
    }

    @Override // androidx.versionedparcelable.e
    public void a(boolean z) {
        try {
            this.u.writeBoolean(z);
        } catch (IOException e2) {
            throw new e.a(e2);
        }
    }

    @Override // androidx.versionedparcelable.e
    public void a(boolean z, boolean z2) {
        if (!z) {
            throw new RuntimeException("Serialization of this object is not allowed");
        }
        this.w = z2;
    }

    @Override // androidx.versionedparcelable.e
    public void a(byte[] bArr) {
        try {
            if (bArr == null) {
                this.u.writeInt(-1);
                return;
            }
            this.u.writeInt(bArr.length);
            this.u.write(bArr);
        } catch (IOException e2) {
            throw new e.a(e2);
        }
    }

    @Override // androidx.versionedparcelable.e
    public void a(byte[] bArr, int i2, int i3) {
        try {
            if (bArr == null) {
                this.u.writeInt(-1);
                return;
            }
            this.u.writeInt(i3);
            this.u.write(bArr, i2, i3);
        } catch (IOException e2) {
            throw new e.a(e2);
        }
    }

    @Override // androidx.versionedparcelable.e
    public boolean a() {
        return true;
    }

    @Override // androidx.versionedparcelable.e
    public void b() {
        a aVar = this.v;
        if (aVar != null) {
            try {
                if (aVar.a.size() != 0) {
                    this.v.a();
                }
                this.v = null;
            } catch (IOException e2) {
                throw new e.a(e2);
            }
        }
    }

    @Override // androidx.versionedparcelable.e
    public boolean b(int i2) {
        b bVar = this.s.get(i2);
        if (bVar != null) {
            this.s.remove(i2);
            this.t = bVar.a;
            return true;
        }
        while (true) {
            try {
                int readInt = this.q.readInt();
                int i3 = readInt & android.support.v4.d.a.a.a;
                if (i3 == 65535) {
                    i3 = this.q.readInt();
                }
                b bVar2 = new b((readInt >> 16) & android.support.v4.d.a.a.a, i3, this.q);
                if (bVar2.b == i2) {
                    this.t = bVar2.a;
                    return true;
                }
                this.s.put(bVar2.b, bVar2);
            } catch (IOException unused) {
                return false;
            }
        }
    }

    @Override // androidx.versionedparcelable.e
    protected e c() {
        return new g(this.t, this.u);
    }

    @Override // androidx.versionedparcelable.e
    public void c(int i2) {
        b();
        this.v = new a(i2, this.r);
        this.u = this.v.b;
    }

    @Override // androidx.versionedparcelable.e
    public int d() {
        try {
            return this.t.readInt();
        } catch (IOException e2) {
            throw new e.a(e2);
        }
    }

    @Override // androidx.versionedparcelable.e
    public long e() {
        try {
            return this.t.readLong();
        } catch (IOException e2) {
            throw new e.a(e2);
        }
    }

    @Override // androidx.versionedparcelable.e
    public float f() {
        try {
            return this.t.readFloat();
        } catch (IOException e2) {
            throw new e.a(e2);
        }
    }

    @Override // androidx.versionedparcelable.e
    public double g() {
        try {
            return this.t.readDouble();
        } catch (IOException e2) {
            throw new e.a(e2);
        }
    }

    @Override // androidx.versionedparcelable.e
    public String h() {
        try {
            int readInt = this.t.readInt();
            if (readInt > 0) {
                byte[] bArr = new byte[readInt];
                this.t.readFully(bArr);
                return new String(bArr, a);
            }
            return null;
        } catch (IOException e2) {
            throw new e.a(e2);
        }
    }

    @Override // androidx.versionedparcelable.e
    public IBinder i() {
        return null;
    }

    @Override // androidx.versionedparcelable.e
    public byte[] j() {
        try {
            int readInt = this.t.readInt();
            if (readInt > 0) {
                byte[] bArr = new byte[readInt];
                this.t.readFully(bArr);
                return bArr;
            }
            return null;
        } catch (IOException e2) {
            throw new e.a(e2);
        }
    }

    @Override // androidx.versionedparcelable.e
    public <T extends Parcelable> T k() {
        return null;
    }

    @Override // androidx.versionedparcelable.e
    public Bundle l() {
        int d2 = d();
        if (d2 < 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        for (int i2 = 0; i2 < d2; i2++) {
            a(d(), h(), bundle);
        }
        return bundle;
    }

    @Override // androidx.versionedparcelable.e
    public boolean m() {
        try {
            return this.t.readBoolean();
        } catch (IOException e2) {
            throw new e.a(e2);
        }
    }
}
