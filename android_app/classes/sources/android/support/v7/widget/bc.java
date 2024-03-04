package android.support.v7.widget;

import android.support.v4.j.p;
import android.support.v7.widget.RecyclerView;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class bc {
    private static final boolean c = false;
    @android.support.annotation.av
    final android.support.v4.j.a<RecyclerView.x, a> a = new android.support.v4.j.a<>();
    @android.support.annotation.av
    final android.support.v4.j.j<RecyclerView.x> b = new android.support.v4.j.j<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a {
        static final int a = 1;
        static final int b = 2;
        static final int c = 4;
        static final int d = 8;
        static final int e = 3;
        static final int f = 12;
        static final int g = 14;
        static p.a<a> k = new p.b(20);
        int h;
        @android.support.annotation.ag
        RecyclerView.f.d i;
        @android.support.annotation.ag
        RecyclerView.f.d j;

        private a() {
        }

        static a a() {
            a a2 = k.a();
            return a2 == null ? new a() : a2;
        }

        static void a(a aVar) {
            aVar.h = 0;
            aVar.i = null;
            aVar.j = null;
            k.a(aVar);
        }

        static void b() {
            do {
            } while (k.a() != null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface b {
        void a(RecyclerView.x xVar);

        void a(RecyclerView.x xVar, @android.support.annotation.af RecyclerView.f.d dVar, @android.support.annotation.ag RecyclerView.f.d dVar2);

        void b(RecyclerView.x xVar, @android.support.annotation.ag RecyclerView.f.d dVar, RecyclerView.f.d dVar2);

        void c(RecyclerView.x xVar, @android.support.annotation.af RecyclerView.f.d dVar, @android.support.annotation.af RecyclerView.f.d dVar2);
    }

    private RecyclerView.f.d a(RecyclerView.x xVar, int i) {
        a c2;
        RecyclerView.f.d dVar;
        int a2 = this.a.a(xVar);
        if (a2 < 0 || (c2 = this.a.c(a2)) == null || (c2.h & i) == 0) {
            return null;
        }
        c2.h &= i ^ (-1);
        if (i == 4) {
            dVar = c2.i;
        } else if (i != 8) {
            throw new IllegalArgumentException("Must provide flag PRE or POST");
        } else {
            dVar = c2.j;
        }
        if ((c2.h & 12) == 0) {
            this.a.d(a2);
            a.a(c2);
        }
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecyclerView.x a(long j) {
        return this.b.a(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        this.a.clear();
        this.b.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j, RecyclerView.x xVar) {
        this.b.b(j, xVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(RecyclerView.x xVar, RecyclerView.f.d dVar) {
        a aVar = this.a.get(xVar);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(xVar, aVar);
        }
        aVar.i = dVar;
        aVar.h |= 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(b bVar) {
        RecyclerView.f.d dVar;
        RecyclerView.f.d dVar2;
        for (int size = this.a.size() - 1; size >= 0; size--) {
            RecyclerView.x b2 = this.a.b(size);
            a d = this.a.d(size);
            if ((d.h & 3) != 3) {
                if ((d.h & 1) == 0) {
                    if ((d.h & 14) != 14) {
                        if ((d.h & 12) == 12) {
                            bVar.c(b2, d.i, d.j);
                        } else if ((d.h & 4) != 0) {
                            dVar = d.i;
                            dVar2 = null;
                        } else if ((d.h & 8) == 0) {
                            int i = d.h;
                        }
                        a.a(d);
                    }
                    bVar.b(b2, d.i, d.j);
                    a.a(d);
                } else if (d.i != null) {
                    dVar = d.i;
                    dVar2 = d.j;
                }
                bVar.a(b2, dVar, dVar2);
                a.a(d);
            }
            bVar.a(b2);
            a.a(d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(RecyclerView.x xVar) {
        a aVar = this.a.get(xVar);
        return (aVar == null || (aVar.h & 1) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.ag
    public RecyclerView.f.d b(RecyclerView.x xVar) {
        return a(xVar, 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        a.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(RecyclerView.x xVar, RecyclerView.f.d dVar) {
        a aVar = this.a.get(xVar);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(xVar, aVar);
        }
        aVar.h |= 2;
        aVar.i = dVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.ag
    public RecyclerView.f.d c(RecyclerView.x xVar) {
        return a(xVar, 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(RecyclerView.x xVar, RecyclerView.f.d dVar) {
        a aVar = this.a.get(xVar);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(xVar, aVar);
        }
        aVar.j = dVar;
        aVar.h |= 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d(RecyclerView.x xVar) {
        a aVar = this.a.get(xVar);
        return (aVar == null || (aVar.h & 4) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(RecyclerView.x xVar) {
        a aVar = this.a.get(xVar);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(xVar, aVar);
        }
        aVar.h |= 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(RecyclerView.x xVar) {
        a aVar = this.a.get(xVar);
        if (aVar == null) {
            return;
        }
        aVar.h &= -2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(RecyclerView.x xVar) {
        int b2 = this.b.b() - 1;
        while (true) {
            if (b2 < 0) {
                break;
            } else if (xVar == this.b.c(b2)) {
                this.b.a(b2);
                break;
            } else {
                b2--;
            }
        }
        a remove = this.a.remove(xVar);
        if (remove != null) {
            a.a(remove);
        }
    }

    public void h(RecyclerView.x xVar) {
        f(xVar);
    }
}
