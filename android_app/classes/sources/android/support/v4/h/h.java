package android.support.v4.h;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.util.Log;
import java.util.ArrayList;
@ak(a = 21)
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class h extends a {
    private Context b;
    private Uri c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(@ag a aVar, Context context, Uri uri) {
        super(aVar);
        this.b = context;
        this.c = uri;
    }

    @ag
    private static Uri a(Context context, Uri uri, String str, String str2) {
        try {
            return DocumentsContract.createDocument(context.getContentResolver(), uri, str, str2);
        } catch (Exception unused) {
            return null;
        }
    }

    private static void a(@ag AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    @Override // android.support.v4.h.a
    public Uri a() {
        return this.c;
    }

    @Override // android.support.v4.h.a
    @ag
    public a a(String str) {
        Uri a = a(this.b, this.c, "vnd.android.document/directory", str);
        if (a != null) {
            return new h(this, this.b, a);
        }
        return null;
    }

    @Override // android.support.v4.h.a
    @ag
    public a a(String str, String str2) {
        Uri a = a(this.b, this.c, str, str2);
        if (a != null) {
            return new h(this, this.b, a);
        }
        return null;
    }

    @Override // android.support.v4.h.a
    @ag
    public String b() {
        return b.b(this.b, this.c);
    }

    @Override // android.support.v4.h.a
    @ag
    public String c() {
        return b.c(this.b, this.c);
    }

    @Override // android.support.v4.h.a
    public boolean c(String str) {
        try {
            Uri renameDocument = DocumentsContract.renameDocument(this.b.getContentResolver(), this.c, str);
            if (renameDocument != null) {
                this.c = renameDocument;
                return true;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    @Override // android.support.v4.h.a
    public boolean e() {
        return b.e(this.b, this.c);
    }

    @Override // android.support.v4.h.a
    public boolean f() {
        return b.f(this.b, this.c);
    }

    @Override // android.support.v4.h.a
    public boolean g() {
        return b.a(this.b, this.c);
    }

    @Override // android.support.v4.h.a
    public long h() {
        return b.g(this.b, this.c);
    }

    @Override // android.support.v4.h.a
    public long i() {
        return b.h(this.b, this.c);
    }

    @Override // android.support.v4.h.a
    public boolean j() {
        return b.i(this.b, this.c);
    }

    @Override // android.support.v4.h.a
    public boolean k() {
        return b.j(this.b, this.c);
    }

    @Override // android.support.v4.h.a
    public boolean l() {
        try {
            return DocumentsContract.deleteDocument(this.b.getContentResolver(), this.c);
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // android.support.v4.h.a
    public boolean m() {
        return b.k(this.b, this.c);
    }

    @Override // android.support.v4.h.a
    public a[] n() {
        ContentResolver contentResolver = this.b.getContentResolver();
        Uri uri = this.c;
        Uri buildChildDocumentsUriUsingTree = DocumentsContract.buildChildDocumentsUriUsingTree(uri, DocumentsContract.getDocumentId(uri));
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                cursor = contentResolver.query(buildChildDocumentsUriUsingTree, new String[]{"document_id"}, null, null, null);
                while (cursor.moveToNext()) {
                    arrayList.add(DocumentsContract.buildDocumentUriUsingTree(this.c, cursor.getString(0)));
                }
            } catch (Exception e) {
                Log.w("DocumentFile", "Failed query: " + e);
            }
            Uri[] uriArr = (Uri[]) arrayList.toArray(new Uri[arrayList.size()]);
            a[] aVarArr = new a[uriArr.length];
            for (int i = 0; i < uriArr.length; i++) {
                aVarArr[i] = new h(this, this.b, uriArr[i]);
            }
            return aVarArr;
        } finally {
            a(cursor);
        }
    }
}
