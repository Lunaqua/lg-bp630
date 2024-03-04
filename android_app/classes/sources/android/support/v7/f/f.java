package android.support.v7.f;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.f.h;
import android.support.v7.f.i;
import android.util.Log;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class f<T> implements h<T> {

    /* renamed from: android.support.v7.f.f$1  reason: invalid class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    class AnonymousClass1 implements h.b<T> {
        static final int b = 1;
        static final int c = 2;
        static final int d = 3;
        final /* synthetic */ h.b e;
        final a a = new a();
        private final Handler g = new Handler(Looper.getMainLooper());
        private Runnable h = new Runnable() { // from class: android.support.v7.f.f.1.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    b a = AnonymousClass1.this.a.a();
                    if (a == null) {
                        return;
                    }
                    int i = a.b;
                    if (i == 1) {
                        AnonymousClass1.this.e.a(a.c, a.d);
                    } else if (i == 2) {
                        AnonymousClass1.this.e.a(a.c, (i.a) a.h);
                    } else if (i != 3) {
                        Log.e("ThreadUtil", "Unsupported message, what=" + a.b);
                    } else {
                        AnonymousClass1.this.e.b(a.c, a.d);
                    }
                }
            }
        };

        AnonymousClass1(h.b bVar) {
            this.e = bVar;
        }

        private void a(b bVar) {
            this.a.b(bVar);
            this.g.post(this.h);
        }

        @Override // android.support.v7.f.h.b
        public void a(int i, int i2) {
            a(b.a(1, i, i2));
        }

        @Override // android.support.v7.f.h.b
        public void a(int i, i.a<T> aVar) {
            a(b.a(2, i, aVar));
        }

        @Override // android.support.v7.f.h.b
        public void b(int i, int i2) {
            a(b.a(3, i, i2));
        }
    }

    /* renamed from: android.support.v7.f.f$2  reason: invalid class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    class AnonymousClass2 implements h.a<T> {
        static final int c = 1;
        static final int d = 2;
        static final int e = 3;
        static final int f = 4;
        final /* synthetic */ h.a g;
        final a a = new a();
        private final Executor i = AsyncTask.THREAD_POOL_EXECUTOR;
        AtomicBoolean b = new AtomicBoolean(false);
        private Runnable j = new Runnable() { // from class: android.support.v7.f.f.2.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    b a = AnonymousClass2.this.a.a();
                    if (a == null) {
                        AnonymousClass2.this.b.set(false);
                        return;
                    }
                    int i = a.b;
                    if (i == 1) {
                        AnonymousClass2.this.a.a(1);
                        AnonymousClass2.this.g.a(a.c);
                    } else if (i == 2) {
                        AnonymousClass2.this.a.a(2);
                        AnonymousClass2.this.a.a(3);
                        AnonymousClass2.this.g.a(a.c, a.d, a.e, a.f, a.g);
                    } else if (i == 3) {
                        AnonymousClass2.this.g.a(a.c, a.d);
                    } else if (i != 4) {
                        Log.e("ThreadUtil", "Unsupported message, what=" + a.b);
                    } else {
                        AnonymousClass2.this.g.a((i.a) a.h);
                    }
                }
            }
        };

        AnonymousClass2(h.a aVar) {
            this.g = aVar;
        }

        private void a() {
            if (this.b.compareAndSet(false, true)) {
                this.i.execute(this.j);
            }
        }

        private void a(b bVar) {
            this.a.b(bVar);
            a();
        }

        private void b(b bVar) {
            this.a.a(bVar);
            a();
        }

        @Override // android.support.v7.f.h.a
        public void a(int i) {
            b(b.a(1, i, (Object) null));
        }

        @Override // android.support.v7.f.h.a
        public void a(int i, int i2) {
            a(b.a(3, i, i2));
        }

        @Override // android.support.v7.f.h.a
        public void a(int i, int i2, int i3, int i4, int i5) {
            b(b.a(2, i, i2, i3, i4, i5, null));
        }

        @Override // android.support.v7.f.h.a
        public void a(i.a<T> aVar) {
            a(b.a(4, 0, aVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a {
        private b a;

        a() {
        }

        synchronized b a() {
            if (this.a == null) {
                return null;
            }
            b bVar = this.a;
            this.a = this.a.a;
            return bVar;
        }

        synchronized void a(int i) {
            while (this.a != null && this.a.b == i) {
                b bVar = this.a;
                this.a = this.a.a;
                bVar.a();
            }
            if (this.a != null) {
                b bVar2 = this.a;
                b bVar3 = bVar2.a;
                while (bVar3 != null) {
                    b bVar4 = bVar3.a;
                    if (bVar3.b == i) {
                        bVar2.a = bVar4;
                        bVar3.a();
                    } else {
                        bVar2 = bVar3;
                    }
                    bVar3 = bVar4;
                }
            }
        }

        synchronized void a(b bVar) {
            bVar.a = this.a;
            this.a = bVar;
        }

        synchronized void b(b bVar) {
            if (this.a == null) {
                this.a = bVar;
                return;
            }
            b bVar2 = this.a;
            while (bVar2.a != null) {
                bVar2 = bVar2.a;
            }
            bVar2.a = bVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b {
        private static b i;
        private static final Object j = new Object();
        b a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public Object h;

        b() {
        }

        static b a(int i2, int i3, int i4) {
            return a(i2, i3, i4, 0, 0, 0, null);
        }

        static b a(int i2, int i3, int i4, int i5, int i6, int i7, Object obj) {
            b bVar;
            synchronized (j) {
                if (i == null) {
                    bVar = new b();
                } else {
                    bVar = i;
                    i = i.a;
                    bVar.a = null;
                }
                bVar.b = i2;
                bVar.c = i3;
                bVar.d = i4;
                bVar.e = i5;
                bVar.f = i6;
                bVar.g = i7;
                bVar.h = obj;
            }
            return bVar;
        }

        static b a(int i2, int i3, Object obj) {
            return a(i2, i3, 0, 0, 0, 0, obj);
        }

        void a() {
            this.a = null;
            this.g = 0;
            this.f = 0;
            this.e = 0;
            this.d = 0;
            this.c = 0;
            this.b = 0;
            this.h = null;
            synchronized (j) {
                if (i != null) {
                    this.a = i;
                }
                i = this;
            }
        }
    }

    @Override // android.support.v7.f.h
    public h.a<T> a(h.a<T> aVar) {
        return new AnonymousClass2(aVar);
    }

    @Override // android.support.v7.f.h
    public h.b<T> a(h.b<T> bVar) {
        return new AnonymousClass1(bVar);
    }
}
