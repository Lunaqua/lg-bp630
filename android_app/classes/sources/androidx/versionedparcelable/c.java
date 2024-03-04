package androidx.versionedparcelable;

import android.os.Parcelable;
import android.support.annotation.an;
import java.io.InputStream;
import java.io.OutputStream;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c {
    private c() {
    }

    public static Parcelable a(h hVar) {
        return new ParcelImpl(hVar);
    }

    public static <T extends h> T a(Parcelable parcelable) {
        if (parcelable instanceof ParcelImpl) {
            return (T) ((ParcelImpl) parcelable).a();
        }
        throw new IllegalArgumentException("Invalid parcel");
    }

    public static <T extends h> T a(InputStream inputStream) {
        return (T) new g(inputStream, null).t();
    }

    public static void a(h hVar, OutputStream outputStream) {
        g gVar = new g(null, outputStream);
        gVar.a(hVar);
        gVar.b();
    }
}
