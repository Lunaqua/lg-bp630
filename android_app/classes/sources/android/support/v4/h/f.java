package android.support.v4.h;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.an;
import android.support.annotation.av;
import android.support.annotation.t;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class f {
    private static final int e = 1;
    private static final int f = 0;
    @t(a = "mLock")
    private HandlerThread b;
    @t(a = "mLock")
    private Handler c;
    private final int h;
    private final int i;
    private final String j;
    private final Object a = new Object();
    private Handler.Callback g = new Handler.Callback() { // from class: android.support.v4.h.f.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                f.this.c();
                return true;
            } else if (i != 1) {
                return true;
            } else {
                f.this.a((Runnable) message.obj);
                return true;
            }
        }
    };
    @t(a = "mLock")
    private int d = 0;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a<T> {
        void a(T t);
    }

    public f(String str, int i, int i2) {
        this.j = str;
        this.i = i;
        this.h = i2;
    }

    private void b(Runnable runnable) {
        synchronized (this.a) {
            if (this.b == null) {
                this.b = new HandlerThread(this.j, this.i);
                this.b.start();
                this.c = new Handler(this.b.getLooper(), this.g);
                this.d++;
            }
            this.c.removeMessages(0);
            this.c.sendMessage(this.c.obtainMessage(1, runnable));
        }
    }

    public <T> T a(final Callable<T> callable, int i) {
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition newCondition = reentrantLock.newCondition();
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        b(new Runnable() { // from class: android.support.v4.h.f.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    atomicReference.set(callable.call());
                } catch (Exception unused) {
                }
                reentrantLock.lock();
                try {
                    atomicBoolean.set(false);
                    newCondition.signal();
                } finally {
                    reentrantLock.unlock();
                }
            }
        });
        reentrantLock.lock();
        try {
            if (atomicBoolean.get()) {
                long nanos = TimeUnit.MILLISECONDS.toNanos(i);
                do {
                    try {
                        nanos = newCondition.awaitNanos(nanos);
                    } catch (InterruptedException unused) {
                    }
                    if (!atomicBoolean.get()) {
                        return (T) atomicReference.get();
                    }
                } while (nanos > 0);
                throw new InterruptedException("timeout");
            }
            return (T) atomicReference.get();
        } finally {
            reentrantLock.unlock();
        }
    }

    void a(Runnable runnable) {
        runnable.run();
        synchronized (this.a) {
            this.c.removeMessages(0);
            this.c.sendMessageDelayed(this.c.obtainMessage(0), this.h);
        }
    }

    public <T> void a(final Callable<T> callable, final a<T> aVar) {
        final Handler handler = new Handler();
        b(new Runnable() { // from class: android.support.v4.h.f.2
            @Override // java.lang.Runnable
            public void run() {
                final Object obj;
                try {
                    obj = callable.call();
                } catch (Exception unused) {
                    obj = null;
                }
                handler.post(new Runnable() { // from class: android.support.v4.h.f.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        aVar.a(obj);
                    }
                });
            }
        });
    }

    @av
    public boolean a() {
        boolean z;
        synchronized (this.a) {
            z = this.b != null;
        }
        return z;
    }

    @av
    public int b() {
        int i;
        synchronized (this.a) {
            i = this.d;
        }
        return i;
    }

    void c() {
        synchronized (this.a) {
            if (this.c.hasMessages(1)) {
                return;
            }
            this.b.quit();
            this.b = null;
            this.c = null;
        }
    }
}
