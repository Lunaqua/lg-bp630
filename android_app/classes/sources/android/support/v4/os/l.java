package android.support.v4.os;

import android.os.Parcel;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class l {
    private l() {
    }

    public static void a(Parcel parcel, boolean z) {
        parcel.writeInt(z ? 1 : 0);
    }

    public static boolean a(Parcel parcel) {
        return parcel.readInt() != 0;
    }
}
