package android.arch.lifecycle;

import android.support.annotation.ac;
import android.support.annotation.af;
import android.support.annotation.ag;
import java.util.Iterator;
import java.util.Map;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class j<T> extends l<T> {
    private android.arch.a.b.b<LiveData<?>, a<?>> a = new android.arch.a.b.b<>();

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class a<V> implements m<V> {
        final LiveData<V> a;
        final m<V> b;
        int c = -1;

        a(LiveData<V> liveData, m<V> mVar) {
            this.a = liveData;
            this.b = mVar;
        }

        void a() {
            this.a.observeForever(this);
        }

        void b() {
            this.a.removeObserver(this);
        }

        @Override // android.arch.lifecycle.m
        public void onChanged(@ag V v) {
            if (this.c != this.a.getVersion()) {
                this.c = this.a.getVersion();
                this.b.onChanged(v);
            }
        }
    }

    @ac
    public <S> void a(@af LiveData<S> liveData) {
        a<?> b = this.a.b(liveData);
        if (b != null) {
            b.b();
        }
    }

    @ac
    public <S> void a(@af LiveData<S> liveData, @af m<S> mVar) {
        a<?> aVar = new a<>(liveData, mVar);
        a<?> a2 = this.a.a(liveData, aVar);
        if (a2 != null && a2.b != mVar) {
            throw new IllegalArgumentException("This source was already added with the different observer");
        }
        if (a2 == null && hasActiveObservers()) {
            aVar.a();
        }
    }

    @Override // android.arch.lifecycle.LiveData
    @android.support.annotation.i
    protected void onActive() {
        Iterator<Map.Entry<LiveData<?>, a<?>>> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().getValue().a();
        }
    }

    @Override // android.arch.lifecycle.LiveData
    @android.support.annotation.i
    protected void onInactive() {
        Iterator<Map.Entry<LiveData<?>, a<?>>> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().getValue().b();
        }
    }
}
