package android.support.v4.h;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.ag;
import android.support.annotation.ak;
@ak(a = 19)
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class g extends a {
    private Context b;
    private Uri c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(@ag a aVar, Context context, Uri uri) {
        super(aVar);
        this.b = context;
        this.c = uri;
    }

    @Override // android.support.v4.h.a
    public Uri a() {
        return this.c;
    }

    @Override // android.support.v4.h.a
    public a a(String str) {
        throw new UnsupportedOperationException();
    }

    @Override // android.support.v4.h.a
    public a a(String str, String str2) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }
}
