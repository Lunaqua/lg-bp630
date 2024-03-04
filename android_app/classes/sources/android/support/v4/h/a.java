package android.support.v4.h;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.support.annotation.af;
import android.support.annotation.ag;
import java.io.File;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class a {
    static final String a = "DocumentFile";
    @ag
    private final a b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(@ag a aVar) {
        this.b = aVar;
    }

    @ag
    public static a a(@af Context context, @af Uri uri) {
        if (Build.VERSION.SDK_INT >= 19) {
            return new g(null, context, uri);
        }
        return null;
    }

    @af
    public static a a(@af File file) {
        return new e(null, file);
    }

    @ag
    public static a b(@af Context context, @af Uri uri) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new h(null, context, DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri)));
        }
        return null;
    }

    public static boolean c(@af Context context, @ag Uri uri) {
        if (Build.VERSION.SDK_INT >= 19) {
            return DocumentsContract.isDocumentUri(context, uri);
        }
        return false;
    }

    @af
    public abstract Uri a();

    @ag
    public abstract a a(@af String str);

    @ag
    public abstract a a(@af String str, @af String str2);

    @ag
    public a b(@af String str) {
        a[] n;
        for (a aVar : n()) {
            if (str.equals(aVar.b())) {
                return aVar;
            }
        }
        return null;
    }

    @ag
    public abstract String b();

    @ag
    public abstract String c();

    public abstract boolean c(@af String str);

    @ag
    public a d() {
        return this.b;
    }

    public abstract boolean e();

    public abstract boolean f();

    public abstract boolean g();

    public abstract long h();

    public abstract long i();

    public abstract boolean j();

    public abstract boolean k();

    public abstract boolean l();

    public abstract boolean m();

    @af
    public abstract a[] n();
}
