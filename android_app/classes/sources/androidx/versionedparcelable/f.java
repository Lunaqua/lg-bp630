package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.an;
import android.util.SparseIntArray;
@an(a = {an.a.LIBRARY})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class f extends e {
    private static final boolean a = false;
    private static final String b = "VersionedParcelParcel";
    private final SparseIntArray c;
    private final Parcel d;
    private final int e;
    private final int f;
    private final String g;
    private int h;
    private int i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), com.lge.media.launcher.a.d);
    }

    f(Parcel parcel, int i, int i2, String str) {
        this.c = new SparseIntArray();
        this.h = -1;
        this.i = 0;
        this.d = parcel;
        this.e = i;
        this.f = i2;
        this.i = this.e;
        this.g = str;
    }

    private int d(int i) {
        int readInt;
        do {
            int i2 = this.i;
            if (i2 >= this.f) {
                return -1;
            }
            this.d.setDataPosition(i2);
            int readInt2 = this.d.readInt();
            readInt = this.d.readInt();
            this.i += readInt2;
        } while (readInt != i);
        return this.d.dataPosition();
    }

    @Override // androidx.versionedparcelable.e
    public void a(double d) {
        this.d.writeDouble(d);
    }

    @Override // androidx.versionedparcelable.e
    public void a(float f) {
        this.d.writeFloat(f);
    }

    @Override // androidx.versionedparcelable.e
    public void a(int i) {
        this.d.writeInt(i);
    }

    @Override // androidx.versionedparcelable.e
    public void a(long j) {
        this.d.writeLong(j);
    }

    @Override // androidx.versionedparcelable.e
    public void a(Bundle bundle) {
        this.d.writeBundle(bundle);
    }

    @Override // androidx.versionedparcelable.e
    public void a(IBinder iBinder) {
        this.d.writeStrongBinder(iBinder);
    }

    @Override // androidx.versionedparcelable.e
    public void a(IInterface iInterface) {
        this.d.writeStrongInterface(iInterface);
    }

    @Override // androidx.versionedparcelable.e
    public void a(Parcelable parcelable) {
        this.d.writeParcelable(parcelable, 0);
    }

    @Override // androidx.versionedparcelable.e
    public void a(String str) {
        this.d.writeString(str);
    }

    @Override // androidx.versionedparcelable.e
    public void a(boolean z) {
        this.d.writeInt(z ? 1 : 0);
    }

    @Override // androidx.versionedparcelable.e
    public void a(byte[] bArr) {
        if (bArr == null) {
            this.d.writeInt(-1);
            return;
        }
        this.d.writeInt(bArr.length);
        this.d.writeByteArray(bArr);
    }

    @Override // androidx.versionedparcelable.e
    public void a(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            this.d.writeInt(-1);
            return;
        }
        this.d.writeInt(bArr.length);
        this.d.writeByteArray(bArr, i, i2);
    }

    @Override // androidx.versionedparcelable.e
    public void b() {
        int i = this.h;
        if (i >= 0) {
            int i2 = this.c.get(i);
            int dataPosition = this.d.dataPosition();
            this.d.setDataPosition(i2);
            this.d.writeInt(dataPosition - i2);
            this.d.setDataPosition(dataPosition);
        }
    }

    @Override // androidx.versionedparcelable.e
    public boolean b(int i) {
        int d = d(i);
        if (d == -1) {
            return false;
        }
        this.d.setDataPosition(d);
        return true;
    }

    @Override // androidx.versionedparcelable.e
    protected e c() {
        Parcel parcel = this.d;
        int dataPosition = parcel.dataPosition();
        int i = this.i;
        if (i == this.e) {
            i = this.f;
        }
        return new f(parcel, dataPosition, i, this.g + "  ");
    }

    @Override // androidx.versionedparcelable.e
    public void c(int i) {
        b();
        this.h = i;
        this.c.put(i, this.d.dataPosition());
        a(0);
        a(i);
    }

    @Override // androidx.versionedparcelable.e
    public int d() {
        return this.d.readInt();
    }

    @Override // androidx.versionedparcelable.e
    public long e() {
        return this.d.readLong();
    }

    @Override // androidx.versionedparcelable.e
    public float f() {
        return this.d.readFloat();
    }

    @Override // androidx.versionedparcelable.e
    public double g() {
        return this.d.readDouble();
    }

    @Override // androidx.versionedparcelable.e
    public String h() {
        return this.d.readString();
    }

    @Override // androidx.versionedparcelable.e
    public IBinder i() {
        return this.d.readStrongBinder();
    }

    @Override // androidx.versionedparcelable.e
    public byte[] j() {
        int readInt = this.d.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        this.d.readByteArray(bArr);
        return bArr;
    }

    @Override // androidx.versionedparcelable.e
    public <T extends Parcelable> T k() {
        return (T) this.d.readParcelable(getClass().getClassLoader());
    }

    @Override // androidx.versionedparcelable.e
    public Bundle l() {
        return this.d.readBundle(getClass().getClassLoader());
    }

    @Override // androidx.versionedparcelable.e
    public boolean m() {
        return this.d.readInt() != 0;
    }
}
