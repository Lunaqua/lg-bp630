package android.support.v4.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.v4.j.t;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class a<D> extends f<D> {
    static final String a = "AsyncTaskLoader";
    static final boolean b = false;
    volatile a<D>.RunnableC0016a c;
    volatile a<D>.RunnableC0016a d;
    long e;
    long f;
    Handler g;
    private final Executor h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.support.v4.content.a$a  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public final class RunnableC0016a extends i<Void, Void, D> implements Runnable {
        boolean a;
        private final CountDownLatch f = new CountDownLatch(1);

        RunnableC0016a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.support.v4.content.i
        public D a(Void... voidArr) {
            try {
                return (D) a.this.e();
            } catch (android.support.v4.os.k e) {
                if (e()) {
                    return null;
                }
                throw e;
            }
        }

        public void a() {
            try {
                this.f.await();
            } catch (InterruptedException unused) {
            }
        }

        @Override // android.support.v4.content.i
        protected void a(D d) {
            try {
                a.this.b(this, d);
            } finally {
                this.f.countDown();
            }
        }

        @Override // android.support.v4.content.i
        protected void b(D d) {
            try {
                a.this.a((a<RunnableC0016a>.RunnableC0016a) this, (RunnableC0016a) d);
            } finally {
                this.f.countDown();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a = false;
            a.this.c();
        }
    }

    public a(@af Context context) {
        this(context, i.c);
    }

    private a(@af Context context, @af Executor executor) {
        super(context);
        this.f = -10000L;
        this.h = executor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.content.f
    public void a() {
        super.a();
        y();
        this.c = new RunnableC0016a();
        c();
    }

    public void a(long j) {
        this.e = j;
        if (j != 0) {
            this.g = new Handler();
        }
    }

    void a(a<D>.RunnableC0016a runnableC0016a, D d) {
        a((a<D>) d);
        if (this.d == runnableC0016a) {
            G();
            this.f = SystemClock.uptimeMillis();
            this.d = null;
            r();
            c();
        }
    }

    public void a(@ag D d) {
    }

    @Override // android.support.v4.content.f
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.a(str, fileDescriptor, printWriter, strArr);
        if (this.c != null) {
            printWriter.print(str);
            printWriter.print("mTask=");
            printWriter.print(this.c);
            printWriter.print(" waiting=");
            printWriter.println(this.c.a);
        }
        if (this.d != null) {
            printWriter.print(str);
            printWriter.print("mCancellingTask=");
            printWriter.print(this.d);
            printWriter.print(" waiting=");
            printWriter.println(this.d.a);
        }
        if (this.e != 0) {
            printWriter.print(str);
            printWriter.print("mUpdateThrottle=");
            t.a(this.e, printWriter);
            printWriter.print(" mLastLoadCompleteTime=");
            t.a(this.f, SystemClock.uptimeMillis(), printWriter);
            printWriter.println();
        }
    }

    void b(a<D>.RunnableC0016a runnableC0016a, D d) {
        if (this.c != runnableC0016a) {
            a((a<a<D>.RunnableC0016a>.RunnableC0016a) runnableC0016a, (a<D>.RunnableC0016a) d);
        } else if (v()) {
            a((a<D>) d);
        } else {
            F();
            this.f = SystemClock.uptimeMillis();
            this.c = null;
            b((a<D>) d);
        }
    }

    @Override // android.support.v4.content.f
    protected boolean b() {
        if (this.c != null) {
            if (!this.t) {
                this.w = true;
            }
            if (this.d != null) {
                if (this.c.a) {
                    this.c.a = false;
                    this.g.removeCallbacks(this.c);
                }
                this.c = null;
                return false;
            } else if (this.c.a) {
                this.c.a = false;
                this.g.removeCallbacks(this.c);
                this.c = null;
                return false;
            } else {
                boolean a2 = this.c.a(false);
                if (a2) {
                    this.d = this.c;
                    f();
                }
                this.c = null;
                return a2;
            }
        }
        return false;
    }

    void c() {
        if (this.d != null || this.c == null) {
            return;
        }
        if (this.c.a) {
            this.c.a = false;
            this.g.removeCallbacks(this.c);
        }
        if (this.e <= 0 || SystemClock.uptimeMillis() >= this.f + this.e) {
            this.c.a(this.h, (Object[]) null);
            return;
        }
        this.c.a = true;
        this.g.postAtTime(this.c, this.f + this.e);
    }

    @ag
    public abstract D d();

    @ag
    protected D e() {
        return d();
    }

    public void f() {
    }

    public boolean g() {
        return this.d != null;
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public void h() {
        a<D>.RunnableC0016a runnableC0016a = this.c;
        if (runnableC0016a != null) {
            runnableC0016a.a();
        }
    }
}
