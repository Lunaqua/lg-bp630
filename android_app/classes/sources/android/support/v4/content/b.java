package android.support.v4.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.OperationCanceledException;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class b {
    private b() {
    }

    public static Cursor a(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, android.support.v4.os.b bVar) {
        Object d;
        if (Build.VERSION.SDK_INT < 16) {
            if (bVar != null) {
                bVar.b();
            }
            return contentResolver.query(uri, strArr, str, strArr2, str2);
        }
        if (bVar != null) {
            try {
                d = bVar.d();
            } catch (Exception e) {
                if (e instanceof OperationCanceledException) {
                    throw new android.support.v4.os.k();
                }
                throw e;
            }
        } else {
            d = null;
        }
        return contentResolver.query(uri, strArr, str, strArr2, str2, (CancellationSignal) d);
    }
}
