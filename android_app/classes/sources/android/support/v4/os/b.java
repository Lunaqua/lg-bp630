package android.support.v4.os;

import android.os.Build;
import android.os.CancellationSignal;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class b {
    private boolean a;
    private a b;
    private Object c;
    private boolean d;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        void a();
    }

    private void e() {
        while (this.d) {
            try {
                wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    public void a(a aVar) {
        synchronized (this) {
            e();
            if (this.b == aVar) {
                return;
            }
            this.b = aVar;
            if (this.a && aVar != null) {
                aVar.a();
            }
        }
    }

    public boolean a() {
        boolean z;
        synchronized (this) {
            z = this.a;
        }
        return z;
    }

    public void b() {
        if (a()) {
            throw new k();
        }
    }

    public void c() {
        synchronized (this) {
            if (this.a) {
                return;
            }
            this.a = true;
            this.d = true;
            a aVar = this.b;
            Object obj = this.c;
            if (aVar != null) {
                try {
                    aVar.a();
                } catch (Throwable th) {
                    synchronized (this) {
                        this.d = false;
                        notifyAll();
                        throw th;
                    }
                }
            }
            if (obj != null && Build.VERSION.SDK_INT >= 16) {
                ((CancellationSignal) obj).cancel();
            }
            synchronized (this) {
                this.d = false;
                notifyAll();
            }
        }
    }

    public Object d() {
        Object obj;
        if (Build.VERSION.SDK_INT < 16) {
            return null;
        }
        synchronized (this) {
            if (this.c == null) {
                this.c = new CancellationSignal();
                if (this.a) {
                    ((CancellationSignal) this.c).cancel();
                }
            }
            obj = this.c;
        }
        return obj;
    }
}
