package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.an;
@an(a = {an.a.LIBRARY})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new Parcelable.Creator<ParcelImpl>() { // from class: androidx.versionedparcelable.ParcelImpl.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ParcelImpl createFromParcel(Parcel parcel) {
            return new ParcelImpl(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ParcelImpl[] newArray(int i) {
            return new ParcelImpl[i];
        }
    };
    private final h a;

    protected ParcelImpl(Parcel parcel) {
        this.a = new f(parcel).t();
    }

    public ParcelImpl(h hVar) {
        this.a = hVar;
    }

    public <T extends h> T a() {
        return (T) this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        new f(parcel).a(this.a);
    }
}
