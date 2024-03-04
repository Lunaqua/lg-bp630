package android.arch.lifecycle;

import android.arch.lifecycle.d;
import android.support.annotation.ac;
import android.support.annotation.af;
import android.support.annotation.ag;
import java.util.Iterator;
import java.util.Map;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class LiveData<T> {
    private static final Object NOT_SET = new Object();
    static final int START_VERSION = -1;
    private volatile Object mData;
    private boolean mDispatchInvalidated;
    private boolean mDispatchingValue;
    private volatile Object mPendingData;
    private final Runnable mPostValueRunnable;
    private int mVersion;
    private final Object mDataLock = new Object();
    private android.arch.a.b.b<m<T>, LiveData<T>.b> mObservers = new android.arch.a.b.b<>();
    private int mActiveCount = 0;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    class LifecycleBoundObserver extends LiveData<T>.b implements GenericLifecycleObserver {
        @af
        final f a;

        LifecycleBoundObserver(f fVar, @af m<T> mVar) {
            super(mVar);
            this.a = fVar;
        }

        @Override // android.arch.lifecycle.GenericLifecycleObserver
        public void a(f fVar, d.a aVar) {
            if (this.a.getLifecycle().a() == d.b.DESTROYED) {
                LiveData.this.removeObserver(this.c);
            } else {
                a(a());
            }
        }

        @Override // android.arch.lifecycle.LiveData.b
        boolean a() {
            return this.a.getLifecycle().a().a(d.b.STARTED);
        }

        @Override // android.arch.lifecycle.LiveData.b
        boolean a(f fVar) {
            return this.a == fVar;
        }

        @Override // android.arch.lifecycle.LiveData.b
        void b() {
            this.a.getLifecycle().b(this);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class a extends LiveData<T>.b {
        a(m<T> mVar) {
            super(mVar);
        }

        @Override // android.arch.lifecycle.LiveData.b
        boolean a() {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public abstract class b {
        final m<T> c;
        boolean d;
        int e = -1;

        b(m<T> mVar) {
            this.c = mVar;
        }

        void a(boolean z) {
            if (z == this.d) {
                return;
            }
            this.d = z;
            boolean z2 = LiveData.this.mActiveCount == 0;
            LiveData.this.mActiveCount += this.d ? 1 : -1;
            if (z2 && this.d) {
                LiveData.this.onActive();
            }
            if (LiveData.this.mActiveCount == 0 && !this.d) {
                LiveData.this.onInactive();
            }
            if (this.d) {
                LiveData.this.dispatchingValue(this);
            }
        }

        abstract boolean a();

        boolean a(f fVar) {
            return false;
        }

        void b() {
        }
    }

    public LiveData() {
        Object obj = NOT_SET;
        this.mData = obj;
        this.mPendingData = obj;
        this.mVersion = -1;
        this.mPostValueRunnable = new Runnable() { // from class: android.arch.lifecycle.LiveData.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                Object obj2;
                synchronized (LiveData.this.mDataLock) {
                    obj2 = LiveData.this.mPendingData;
                    LiveData.this.mPendingData = LiveData.NOT_SET;
                }
                LiveData.this.setValue(obj2);
            }
        };
    }

    private static void assertMainThread(String str) {
        if (android.arch.a.a.a.a().d()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void considerNotify(LiveData<T>.b bVar) {
        if (bVar.d) {
            if (!bVar.a()) {
                bVar.a(false);
                return;
            }
            int i = bVar.e;
            int i2 = this.mVersion;
            if (i >= i2) {
                return;
            }
            bVar.e = i2;
            bVar.c.onChanged(this.mData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchingValue(@ag LiveData<T>.b bVar) {
        if (this.mDispatchingValue) {
            this.mDispatchInvalidated = true;
            return;
        }
        this.mDispatchingValue = true;
        do {
            this.mDispatchInvalidated = false;
            if (bVar == null) {
                android.arch.a.b.b<m<T>, LiveData<T>.b>.d c = this.mObservers.c();
                while (c.hasNext()) {
                    considerNotify((b) c.next().getValue());
                    if (this.mDispatchInvalidated) {
                        break;
                    }
                }
            } else {
                considerNotify(bVar);
                bVar = null;
            }
        } while (this.mDispatchInvalidated);
        this.mDispatchingValue = false;
    }

    @ag
    public T getValue() {
        T t = (T) this.mData;
        if (t != NOT_SET) {
            return t;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getVersion() {
        return this.mVersion;
    }

    public boolean hasActiveObservers() {
        return this.mActiveCount > 0;
    }

    public boolean hasObservers() {
        return this.mObservers.a() > 0;
    }

    @ac
    public void observe(@af f fVar, @af m<T> mVar) {
        if (fVar.getLifecycle().a() == d.b.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(fVar, mVar);
        LiveData<T>.b a2 = this.mObservers.a(mVar, lifecycleBoundObserver);
        if (a2 != null && !a2.a(fVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (a2 != null) {
            return;
        }
        fVar.getLifecycle().a(lifecycleBoundObserver);
    }

    @ac
    public void observeForever(@af m<T> mVar) {
        a aVar = new a(mVar);
        LiveData<T>.b a2 = this.mObservers.a(mVar, aVar);
        if (a2 != null && (a2 instanceof LifecycleBoundObserver)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (a2 != null) {
            return;
        }
        aVar.a(true);
    }

    protected void onActive() {
    }

    protected void onInactive() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void postValue(T t) {
        boolean z;
        synchronized (this.mDataLock) {
            z = this.mPendingData == NOT_SET;
            this.mPendingData = t;
        }
        if (z) {
            android.arch.a.a.a.a().b(this.mPostValueRunnable);
        }
    }

    @ac
    public void removeObserver(@af m<T> mVar) {
        assertMainThread("removeObserver");
        LiveData<T>.b b2 = this.mObservers.b(mVar);
        if (b2 == null) {
            return;
        }
        b2.b();
        b2.a(false);
    }

    @ac
    public void removeObservers(@af f fVar) {
        assertMainThread("removeObservers");
        Iterator<Map.Entry<m<T>, LiveData<T>.b>> it = this.mObservers.iterator();
        while (it.hasNext()) {
            Map.Entry<m<T>, LiveData<T>.b> next = it.next();
            if (next.getValue().a(fVar)) {
                removeObserver(next.getKey());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @ac
    public void setValue(T t) {
        assertMainThread("setValue");
        this.mVersion++;
        this.mData = t;
        dispatchingValue(null);
    }
}
