package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class n {
    private static final boolean d = false;
    private static final String e = "ChildrenHelper";
    final b a;
    final a b = new a();
    final List<View> c = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a {
        static final int a = 64;
        static final long b = Long.MIN_VALUE;
        long c = 0;
        a d;

        a() {
        }

        private void b() {
            if (this.d == null) {
                this.d = new a();
            }
        }

        void a() {
            this.c = 0L;
            a aVar = this.d;
            if (aVar != null) {
                aVar.a();
            }
        }

        void a(int i) {
            if (i < 64) {
                this.c |= 1 << i;
                return;
            }
            b();
            this.d.a(i - 64);
        }

        void a(int i, boolean z) {
            if (i >= 64) {
                b();
                this.d.a(i - 64, z);
                return;
            }
            boolean z2 = (this.c & b) != 0;
            long j = (1 << i) - 1;
            long j2 = this.c;
            this.c = ((j2 & (j ^ (-1))) << 1) | (j2 & j);
            if (z) {
                a(i);
            } else {
                b(i);
            }
            if (z2 || this.d != null) {
                b();
                this.d.a(0, z2);
            }
        }

        void b(int i) {
            if (i < 64) {
                this.c &= (1 << i) ^ (-1);
                return;
            }
            a aVar = this.d;
            if (aVar != null) {
                aVar.b(i - 64);
            }
        }

        boolean c(int i) {
            if (i < 64) {
                return (this.c & (1 << i)) != 0;
            }
            b();
            return this.d.c(i - 64);
        }

        boolean d(int i) {
            if (i >= 64) {
                b();
                return this.d.d(i - 64);
            }
            long j = 1 << i;
            boolean z = (this.c & j) != 0;
            this.c &= j ^ (-1);
            long j2 = j - 1;
            long j3 = this.c;
            this.c = Long.rotateRight(j3 & (j2 ^ (-1)), 1) | (j3 & j2);
            a aVar = this.d;
            if (aVar != null) {
                if (aVar.c(0)) {
                    a(63);
                }
                this.d.d(0);
            }
            return z;
        }

        int e(int i) {
            a aVar = this.d;
            return aVar == null ? i >= 64 ? Long.bitCount(this.c) : Long.bitCount(this.c & ((1 << i) - 1)) : i < 64 ? Long.bitCount(this.c & ((1 << i) - 1)) : aVar.e(i - 64) + Long.bitCount(this.c);
        }

        public String toString() {
            if (this.d == null) {
                return Long.toBinaryString(this.c);
            }
            return this.d.toString() + "xx" + Long.toBinaryString(this.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface b {
        int a();

        int a(View view);

        void a(int i);

        void a(View view, int i);

        void a(View view, int i, ViewGroup.LayoutParams layoutParams);

        RecyclerView.x b(View view);

        View b(int i);

        void b();

        void c(int i);

        void c(View view);

        void d(View view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(b bVar) {
        this.a = bVar;
    }

    private int f(int i) {
        if (i < 0) {
            return -1;
        }
        int a2 = this.a.a();
        int i2 = i;
        while (i2 < a2) {
            int e2 = i - (i2 - this.b.e(i2));
            if (e2 == 0) {
                while (this.b.c(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += e2;
        }
        return -1;
    }

    private void g(View view) {
        this.c.add(view);
        this.a.c(view);
    }

    private boolean h(View view) {
        if (this.c.remove(view)) {
            this.a.d(view);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        this.b.a();
        for (int size = this.c.size() - 1; size >= 0; size--) {
            this.a.d(this.c.get(size));
            this.c.remove(size);
        }
        this.a.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i) {
        int f = f(i);
        View b2 = this.a.b(f);
        if (b2 == null) {
            return;
        }
        if (this.b.d(f)) {
            h(b2);
        }
        this.a.a(f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(View view) {
        int a2 = this.a.a(view);
        if (a2 < 0) {
            return;
        }
        if (this.b.d(a2)) {
            h(view);
        }
        this.a.a(a2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int a2 = i < 0 ? this.a.a() : f(i);
        this.b.a(a2, z);
        if (z) {
            g(view);
        }
        this.a.a(view, a2, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(View view, int i, boolean z) {
        int a2 = i < 0 ? this.a.a() : f(i);
        this.b.a(a2, z);
        if (z) {
            g(view);
        }
        this.a.a(view, a2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(View view, boolean z) {
        a(view, -1, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        return this.a.a() - this.c.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b(View view) {
        int a2 = this.a.a(view);
        if (a2 == -1 || this.b.c(a2)) {
            return -1;
        }
        return a2 - this.b.e(a2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View b(int i) {
        return this.a.b(f(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c() {
        return this.a.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View c(int i) {
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = this.c.get(i2);
            RecyclerView.x b2 = this.a.b(view);
            if (b2.e() == i && !b2.p() && !b2.s()) {
                return view;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(View view) {
        return this.c.contains(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View d(int i) {
        return this.a.b(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(View view) {
        int a2 = this.a.a(view);
        if (a2 >= 0) {
            this.b.a(a2);
            g(view);
            return;
        }
        throw new IllegalArgumentException("view is not a child, cannot hide " + view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(int i) {
        int f = f(i);
        this.b.d(f);
        this.a.c(f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(View view) {
        int a2 = this.a.a(view);
        if (a2 < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        } else if (this.b.c(a2)) {
            this.b.b(a2);
            h(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f(View view) {
        int a2 = this.a.a(view);
        if (a2 == -1) {
            h(view);
            return true;
        } else if (this.b.c(a2)) {
            this.b.d(a2);
            h(view);
            this.a.a(a2);
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return this.b.toString() + ", hidden list:" + this.c.size();
    }
}
