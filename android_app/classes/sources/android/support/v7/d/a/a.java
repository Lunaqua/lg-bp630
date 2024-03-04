package android.support.v7.d.a;

import android.support.annotation.af;
import android.support.annotation.an;
import android.support.v7.f.d;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class a<T> {
    @af
    private final Executor a;
    @af
    private final Executor b;
    @af
    private final d.c<T> c;

    /* renamed from: android.support.v7.d.a.a$a  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class C0043a<T> {
        private static final Object d = new Object();
        private static Executor e = null;
        private Executor a;
        private Executor b;
        private final d.c<T> c;

        public C0043a(@af d.c<T> cVar) {
            this.c = cVar;
        }

        @af
        @an(a = {an.a.LIBRARY_GROUP})
        public C0043a<T> a(Executor executor) {
            this.a = executor;
            return this;
        }

        @af
        public a<T> a() {
            if (this.b == null) {
                synchronized (d) {
                    if (e == null) {
                        e = Executors.newFixedThreadPool(2);
                    }
                }
                this.b = e;
            }
            return new a<>(this.a, this.b, this.c);
        }

        @af
        public C0043a<T> b(Executor executor) {
            this.b = executor;
            return this;
        }
    }

    a(@af Executor executor, @af Executor executor2, @af d.c<T> cVar) {
        this.a = executor;
        this.b = executor2;
        this.c = cVar;
    }

    @af
    @an(a = {an.a.LIBRARY_GROUP})
    public Executor a() {
        return this.a;
    }

    @af
    public Executor b() {
        return this.b;
    }

    @af
    public d.c<T> c() {
        return this.c;
    }
}
