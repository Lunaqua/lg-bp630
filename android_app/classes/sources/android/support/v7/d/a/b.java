package android.support.v7.d.a;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.v7.d.a.a;
import android.support.v7.f.d;
import android.support.v7.f.e;
import android.support.v7.widget.RecyclerView;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class b<T> {
    private static final Executor e = new a();
    final android.support.v7.d.a.a<T> a;
    final Executor b;
    int c;
    private final e d;
    @ag
    private List<T> f;
    @af
    private List<T> g;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class a implements Executor {
        final Handler a = new Handler(Looper.getMainLooper());

        a() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(@af Runnable runnable) {
            this.a.post(runnable);
        }
    }

    public b(@af e eVar, @af android.support.v7.d.a.a<T> aVar) {
        this.g = Collections.emptyList();
        this.d = eVar;
        this.a = aVar;
        this.b = aVar.a() != null ? aVar.a() : e;
    }

    public b(@af RecyclerView.a aVar, @af d.c<T> cVar) {
        this(new android.support.v7.f.a(aVar), new a.C0043a(cVar).a());
    }

    @af
    public List<T> a() {
        return this.g;
    }

    public void a(@ag final List<T> list) {
        final int i = this.c + 1;
        this.c = i;
        final List<T> list2 = this.f;
        if (list == list2) {
            return;
        }
        if (list == null) {
            int size = list2.size();
            this.f = null;
            this.g = Collections.emptyList();
            this.d.b(0, size);
        } else if (list2 != null) {
            this.a.b().execute(new Runnable() { // from class: android.support.v7.d.a.b.1
                @Override // java.lang.Runnable
                public void run() {
                    final d.b a2 = d.a(new d.a() { // from class: android.support.v7.d.a.b.1.1
                        @Override // android.support.v7.f.d.a
                        public int a() {
                            return list2.size();
                        }

                        @Override // android.support.v7.f.d.a
                        public boolean a(int i2, int i3) {
                            Object obj = list2.get(i2);
                            Object obj2 = list.get(i3);
                            return (obj == null || obj2 == null) ? obj == null && obj2 == null : b.this.a.c().a(obj, obj2);
                        }

                        @Override // android.support.v7.f.d.a
                        public int b() {
                            return list.size();
                        }

                        @Override // android.support.v7.f.d.a
                        public boolean b(int i2, int i3) {
                            Object obj = list2.get(i2);
                            Object obj2 = list.get(i3);
                            if (obj == null || obj2 == null) {
                                if (obj == null && obj2 == null) {
                                    return true;
                                }
                                throw new AssertionError();
                            }
                            return b.this.a.c().b(obj, obj2);
                        }

                        @Override // android.support.v7.f.d.a
                        @ag
                        public Object c(int i2, int i3) {
                            Object obj = list2.get(i2);
                            Object obj2 = list.get(i3);
                            if (obj == null || obj2 == null) {
                                throw new AssertionError();
                            }
                            return b.this.a.c().c(obj, obj2);
                        }
                    });
                    b.this.b.execute(new Runnable() { // from class: android.support.v7.d.a.b.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (b.this.c == i) {
                                b.this.a(list, a2);
                            }
                        }
                    });
                }
            });
        } else {
            this.f = list;
            this.g = Collections.unmodifiableList(list);
            this.d.a(0, list.size());
        }
    }

    void a(@af List<T> list, @af d.b bVar) {
        this.f = list;
        this.g = Collections.unmodifiableList(list);
        bVar.a(this.d);
    }
}
