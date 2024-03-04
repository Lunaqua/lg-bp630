package android.arch.lifecycle;

import android.support.annotation.ac;
import android.support.annotation.af;
import android.support.annotation.an;
import android.support.annotation.av;
import android.support.annotation.aw;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class b<T> {
    @av
    final Runnable a;
    @av
    final Runnable b;
    private final Executor c;
    private final LiveData<T> d;
    private AtomicBoolean e;
    private AtomicBoolean f;

    public b() {
        this(android.arch.a.a.a.c());
    }

    public b(@af Executor executor) {
        this.e = new AtomicBoolean(true);
        this.f = new AtomicBoolean(false);
        this.a = new Runnable() { // from class: android.arch.lifecycle.b.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            @aw
            public void run() {
                boolean z;
                do {
                    if (b.this.f.compareAndSet(false, true)) {
                        Object obj = null;
                        z = false;
                        while (b.this.e.compareAndSet(true, false)) {
                            try {
                                obj = b.this.c();
                                z = true;
                            } finally {
                                b.this.f.set(false);
                            }
                        }
                        if (z) {
                            b.this.d.postValue(obj);
                        }
                    } else {
                        z = false;
                    }
                    if (!z) {
                        return;
                    }
                } while (b.this.e.get());
            }
        };
        this.b = new Runnable() { // from class: android.arch.lifecycle.b.3
            @Override // java.lang.Runnable
            @ac
            public void run() {
                boolean hasActiveObservers = b.this.d.hasActiveObservers();
                if (b.this.e.compareAndSet(false, true) && hasActiveObservers) {
                    b.this.c.execute(b.this.a);
                }
            }
        };
        this.c = executor;
        this.d = new LiveData<T>() { // from class: android.arch.lifecycle.b.1
            @Override // android.arch.lifecycle.LiveData
            protected void onActive() {
                b.this.c.execute(b.this.a);
            }
        };
    }

    @af
    public LiveData<T> a() {
        return this.d;
    }

    public void b() {
        android.arch.a.a.a.a().c(this.b);
    }

    @aw
    protected abstract T c();
}
