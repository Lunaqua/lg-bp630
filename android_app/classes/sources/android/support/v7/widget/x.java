package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class x implements Runnable {
    static final ThreadLocal<x> a = new ThreadLocal<>();
    static Comparator<b> e = new Comparator<b>() { // from class: android.support.v7.widget.x.1
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(b bVar, b bVar2) {
            if ((bVar.d == null) != (bVar2.d == null)) {
                return bVar.d == null ? 1 : -1;
            } else if (bVar.a != bVar2.a) {
                return bVar.a ? -1 : 1;
            } else {
                int i = bVar2.b - bVar.b;
                if (i != 0) {
                    return i;
                }
                int i2 = bVar.c - bVar2.c;
                if (i2 != 0) {
                    return i2;
                }
                return 0;
            }
        }
    };
    long c;
    long d;
    ArrayList<RecyclerView> b = new ArrayList<>();
    private ArrayList<b> f = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a implements RecyclerView.i.a {
        int a;
        int b;
        int[] c;
        int d;

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a() {
            int[] iArr = this.c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.d = 0;
        }

        void a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        void a(RecyclerView recyclerView, boolean z) {
            this.d = 0;
            int[] iArr = this.c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.i iVar = recyclerView.F;
            if (recyclerView.E == null || iVar == null || !iVar.z()) {
                return;
            }
            if (z) {
                if (!recyclerView.x.d()) {
                    iVar.a(recyclerView.E.a(), this);
                }
            } else if (!recyclerView.B()) {
                iVar.a(this.a, this.b, recyclerView.ac, this);
            }
            if (this.d > iVar.C) {
                iVar.C = this.d;
                iVar.D = z;
                recyclerView.w.b();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean a(int i) {
            if (this.c != null) {
                int i2 = this.d * 2;
                for (int i3 = 0; i3 < i2; i3 += 2) {
                    if (this.c[i3] == i) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override // android.support.v7.widget.RecyclerView.i.a
        public void b(int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i2 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i3 = this.d * 2;
            int[] iArr = this.c;
            if (iArr == null) {
                this.c = new int[4];
                Arrays.fill(this.c, -1);
            } else if (i3 >= iArr.length) {
                this.c = new int[i3 * 2];
                System.arraycopy(iArr, 0, this.c, 0, iArr.length);
            }
            int[] iArr2 = this.c;
            iArr2[i3] = i;
            iArr2[i3 + 1] = i2;
            this.d++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b {
        public boolean a;
        public int b;
        public int c;
        public RecyclerView d;
        public int e;

        b() {
        }

        public void a() {
            this.a = false;
            this.b = 0;
            this.c = 0;
            this.d = null;
            this.e = 0;
        }
    }

    private RecyclerView.x a(RecyclerView recyclerView, int i, long j) {
        if (a(recyclerView, i)) {
            return null;
        }
        RecyclerView.p pVar = recyclerView.w;
        try {
            recyclerView.p();
            RecyclerView.x a2 = pVar.a(i, false, j);
            if (a2 != null) {
                if (!a2.r() || a2.p()) {
                    pVar.a(a2, false);
                } else {
                    pVar.a(a2.a);
                }
            }
            return a2;
        } finally {
            recyclerView.b(false);
        }
    }

    private void a() {
        b bVar;
        int size = this.b.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RecyclerView recyclerView = this.b.get(i2);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.ab.a(recyclerView, false);
                i += recyclerView.ab.d;
            }
        }
        this.f.ensureCapacity(i);
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView recyclerView2 = this.b.get(i4);
            if (recyclerView2.getWindowVisibility() == 0) {
                a aVar = recyclerView2.ab;
                int abs = Math.abs(aVar.a) + Math.abs(aVar.b);
                int i5 = i3;
                for (int i6 = 0; i6 < aVar.d * 2; i6 += 2) {
                    if (i5 >= this.f.size()) {
                        bVar = new b();
                        this.f.add(bVar);
                    } else {
                        bVar = this.f.get(i5);
                    }
                    int i7 = aVar.c[i6 + 1];
                    bVar.a = i7 <= abs;
                    bVar.b = abs;
                    bVar.c = i7;
                    bVar.d = recyclerView2;
                    bVar.e = aVar.c[i6];
                    i5++;
                }
                i3 = i5;
            }
        }
        Collections.sort(this.f, e);
    }

    private void a(@android.support.annotation.ag RecyclerView recyclerView, long j) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.P && recyclerView.y.c() != 0) {
            recyclerView.d();
        }
        a aVar = recyclerView.ab;
        aVar.a(recyclerView, true);
        if (aVar.d != 0) {
            try {
                android.support.v4.os.o.a("RV Nested Prefetch");
                recyclerView.ac.a(recyclerView.E);
                for (int i = 0; i < aVar.d * 2; i += 2) {
                    a(recyclerView, aVar.c[i], j);
                }
            } finally {
                android.support.v4.os.o.a();
            }
        }
    }

    private void a(b bVar, long j) {
        RecyclerView.x a2 = a(bVar.d, bVar.e, bVar.a ? Long.MAX_VALUE : j);
        if (a2 == null || a2.b == null || !a2.r() || a2.p()) {
            return;
        }
        a(a2.b.get(), j);
    }

    static boolean a(RecyclerView recyclerView, int i) {
        int c = recyclerView.y.c();
        for (int i2 = 0; i2 < c; i2++) {
            RecyclerView.x e2 = RecyclerView.e(recyclerView.y.d(i2));
            if (e2.c == i && !e2.p()) {
                return true;
            }
        }
        return false;
    }

    private void b(long j) {
        for (int i = 0; i < this.f.size(); i++) {
            b bVar = this.f.get(i);
            if (bVar.d == null) {
                return;
            }
            a(bVar, j);
            bVar.a();
        }
    }

    void a(long j) {
        a();
        b(j);
    }

    public void a(RecyclerView recyclerView) {
        this.b.add(recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.c == 0) {
            this.c = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.ab.a(i, i2);
    }

    public void b(RecyclerView recyclerView) {
        this.b.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            android.support.v4.os.o.a("RV Prefetch");
            if (!this.b.isEmpty()) {
                int size = this.b.size();
                long j = 0;
                for (int i = 0; i < size; i++) {
                    RecyclerView recyclerView = this.b.get(i);
                    if (recyclerView.getWindowVisibility() == 0) {
                        j = Math.max(recyclerView.getDrawingTime(), j);
                    }
                }
                if (j != 0) {
                    a(TimeUnit.MILLISECONDS.toNanos(j) + this.d);
                }
            }
        } finally {
            this.c = 0L;
            android.support.v4.os.o.a();
        }
    }
}
