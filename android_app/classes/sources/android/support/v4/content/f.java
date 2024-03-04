package android.support.v4.content;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.support.annotation.ac;
import android.support.annotation.af;
import android.support.annotation.ag;
import java.io.FileDescriptor;
import java.io.PrintWriter;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class f<D> {
    int p;
    c<D> q;
    b<D> r;
    Context s;
    boolean t = false;
    boolean u = false;
    boolean v = true;
    boolean w = false;
    boolean x = false;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public final class a extends ContentObserver {
        public a() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            f.this.H();
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface b<D> {
        void a(@af f<D> fVar);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface c<D> {
        void onLoadComplete(@af f<D> fVar, @ag D d);
    }

    public f(@af Context context) {
        this.s = context.getApplicationContext();
    }

    @ac
    public void A() {
        this.t = false;
        k();
    }

    @ac
    public void B() {
        this.u = true;
        C();
    }

    @ac
    protected void C() {
    }

    @ac
    public void D() {
        l();
        this.v = true;
        this.t = false;
        this.u = false;
        this.w = false;
        this.x = false;
    }

    public boolean E() {
        boolean z = this.w;
        this.w = false;
        this.x |= z;
        return z;
    }

    public void F() {
        this.x = false;
    }

    public void G() {
        if (this.x) {
            H();
        }
    }

    @ac
    public void H() {
        if (this.t) {
            z();
        } else {
            this.w = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @ac
    public void a() {
    }

    @ac
    public void a(int i, @af c<D> cVar) {
        if (this.q != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.q = cVar;
        this.p = i;
    }

    @ac
    public void a(@af b<D> bVar) {
        if (this.r != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.r = bVar;
    }

    @ac
    public void a(@af c<D> cVar) {
        c<D> cVar2 = this.q;
        if (cVar2 == null) {
            throw new IllegalStateException("No listener register");
        }
        if (cVar2 != cVar) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.q = null;
    }

    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mId=");
        printWriter.print(this.p);
        printWriter.print(" mListener=");
        printWriter.println(this.q);
        if (this.t || this.w || this.x) {
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.t);
            printWriter.print(" mContentChanged=");
            printWriter.print(this.w);
            printWriter.print(" mProcessingChange=");
            printWriter.println(this.x);
        }
        if (this.u || this.v) {
            printWriter.print(str);
            printWriter.print("mAbandoned=");
            printWriter.print(this.u);
            printWriter.print(" mReset=");
            printWriter.println(this.v);
        }
    }

    @ac
    public void b(@af b<D> bVar) {
        b<D> bVar2 = this.r;
        if (bVar2 == null) {
            throw new IllegalStateException("No listener register");
        }
        if (bVar2 != bVar) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.r = null;
    }

    @ac
    public void b(@ag D d) {
        c<D> cVar = this.q;
        if (cVar != null) {
            cVar.onLoadComplete(this, d);
        }
    }

    @ac
    protected boolean b() {
        return false;
    }

    @af
    public String c(@ag D d) {
        StringBuilder sb = new StringBuilder(64);
        android.support.v4.j.h.a(d, sb);
        sb.append("}");
        return sb.toString();
    }

    @ac
    protected void j() {
    }

    @ac
    protected void k() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @ac
    public void l() {
    }

    @ac
    public void r() {
        b<D> bVar = this.r;
        if (bVar != null) {
            bVar.a(this);
        }
    }

    @af
    public Context s() {
        return this.s;
    }

    public int t() {
        return this.p;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        android.support.v4.j.h.a(this, sb);
        sb.append(" id=");
        sb.append(this.p);
        sb.append("}");
        return sb.toString();
    }

    public boolean u() {
        return this.t;
    }

    public boolean v() {
        return this.u;
    }

    public boolean w() {
        return this.v;
    }

    @ac
    public final void x() {
        this.t = true;
        this.v = false;
        this.u = false;
        j();
    }

    @ac
    public boolean y() {
        return b();
    }

    @ac
    public void z() {
        a();
    }
}
