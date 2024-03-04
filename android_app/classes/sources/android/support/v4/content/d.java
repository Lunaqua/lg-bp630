package android.support.v4.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.v4.content.f;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class d extends a<Cursor> {
    final f<Cursor>.a h;
    Uri i;
    String[] j;
    String k;
    String[] l;
    String m;
    Cursor n;
    android.support.v4.os.b o;

    public d(@af Context context) {
        super(context);
        this.h = new f.a();
    }

    public d(@af Context context, @af Uri uri, @ag String[] strArr, @ag String str, @ag String[] strArr2, @ag String str2) {
        super(context);
        this.h = new f.a();
        this.i = uri;
        this.j = strArr;
        this.k = str;
        this.l = strArr2;
        this.m = str2;
    }

    @Override // android.support.v4.content.f
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void b(Cursor cursor) {
        if (w()) {
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        Cursor cursor2 = this.n;
        this.n = cursor;
        if (u()) {
            super.b((d) cursor);
        }
        if (cursor2 == null || cursor2 == cursor || cursor2.isClosed()) {
            return;
        }
        cursor2.close();
    }

    public void a(@af Uri uri) {
        this.i = uri;
    }

    public void a(@ag String str) {
        this.k = str;
    }

    @Override // android.support.v4.content.a, android.support.v4.content.f
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.a(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("mUri=");
        printWriter.println(this.i);
        printWriter.print(str);
        printWriter.print("mProjection=");
        printWriter.println(Arrays.toString(this.j));
        printWriter.print(str);
        printWriter.print("mSelection=");
        printWriter.println(this.k);
        printWriter.print(str);
        printWriter.print("mSelectionArgs=");
        printWriter.println(Arrays.toString(this.l));
        printWriter.print(str);
        printWriter.print("mSortOrder=");
        printWriter.println(this.m);
        printWriter.print(str);
        printWriter.print("mCursor=");
        printWriter.println(this.n);
        printWriter.print(str);
        printWriter.print("mContentChanged=");
        printWriter.println(this.w);
    }

    public void a(@ag String[] strArr) {
        this.j = strArr;
    }

    @Override // android.support.v4.content.a
    /* renamed from: b */
    public void a(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            return;
        }
        cursor.close();
    }

    public void b(@ag String str) {
        this.m = str;
    }

    public void b(@ag String[] strArr) {
        this.l = strArr;
    }

    @Override // android.support.v4.content.a
    public void f() {
        super.f();
        synchronized (this) {
            if (this.o != null) {
                this.o.c();
            }
        }
    }

    @Override // android.support.v4.content.a
    /* renamed from: i */
    public Cursor d() {
        synchronized (this) {
            if (g()) {
                throw new android.support.v4.os.k();
            }
            this.o = new android.support.v4.os.b();
        }
        try {
            Cursor a = b.a(s().getContentResolver(), this.i, this.j, this.k, this.l, this.m, this.o);
            if (a != null) {
                try {
                    a.getCount();
                    a.registerContentObserver(this.h);
                } catch (RuntimeException e) {
                    a.close();
                    throw e;
                }
            }
            synchronized (this) {
                this.o = null;
            }
            return a;
        } catch (Throwable th) {
            synchronized (this) {
                this.o = null;
                throw th;
            }
        }
    }

    @Override // android.support.v4.content.f
    protected void j() {
        Cursor cursor = this.n;
        if (cursor != null) {
            b(cursor);
        }
        if (E() || this.n == null) {
            z();
        }
    }

    @Override // android.support.v4.content.f
    protected void k() {
        y();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.content.f
    public void l() {
        super.l();
        k();
        Cursor cursor = this.n;
        if (cursor != null && !cursor.isClosed()) {
            this.n.close();
        }
        this.n = null;
    }

    @af
    public Uri m() {
        return this.i;
    }

    @ag
    public String[] n() {
        return this.j;
    }

    @ag
    public String o() {
        return this.k;
    }

    @ag
    public String[] p() {
        return this.l;
    }

    @ag
    public String q() {
        return this.m;
    }
}
