package android.support.v4.os;

import android.os.Parcel;
import android.os.Parcelable;
@Deprecated
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class m {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static class a<T> implements Parcelable.ClassLoaderCreator<T> {
        private final n<T> a;

        a(n<T> nVar) {
            this.a = nVar;
        }

        @Override // android.os.Parcelable.Creator
        public T createFromParcel(Parcel parcel) {
            return this.a.a(parcel, null);
        }

        @Override // android.os.Parcelable.ClassLoaderCreator
        public T createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return this.a.a(parcel, classLoader);
        }

        @Override // android.os.Parcelable.Creator
        public T[] newArray(int i) {
            return this.a.a(i);
        }
    }

    private m() {
    }

    @Deprecated
    public static <T> Parcelable.Creator<T> a(n<T> nVar) {
        return new a(nVar);
    }
}
