package android.support.v7.f;

import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.av;
import android.support.annotation.x;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class d {
    private static final Comparator<f> a = new Comparator<f>() { // from class: android.support.v7.f.d.1
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(f fVar, f fVar2) {
            int i = fVar.a - fVar2.a;
            return i == 0 ? fVar.b - fVar2.b : i;
        }
    };

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class a {
        public abstract int a();

        public abstract boolean a(int i, int i2);

        public abstract int b();

        public abstract boolean b(int i, int i2);

        @ag
        public Object c(int i, int i2) {
            return null;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b {
        public static final int a = -1;
        private static final int b = 1;
        private static final int c = 2;
        private static final int d = 4;
        private static final int e = 8;
        private static final int f = 16;
        private static final int g = 5;
        private static final int h = 31;
        private final List<f> i;
        private final int[] j;
        private final int[] k;
        private final a l;
        private final int m;
        private final int n;
        private final boolean o;

        b(a aVar, List<f> list, int[] iArr, int[] iArr2, boolean z) {
            this.i = list;
            this.j = iArr;
            this.k = iArr2;
            Arrays.fill(this.j, 0);
            Arrays.fill(this.k, 0);
            this.l = aVar;
            this.m = aVar.a();
            this.n = aVar.b();
            this.o = z;
            b();
            c();
        }

        private static C0046d a(List<C0046d> list, int i, boolean z) {
            int size = list.size() - 1;
            while (size >= 0) {
                C0046d c0046d = list.get(size);
                if (c0046d.a == i && c0046d.c == z) {
                    list.remove(size);
                    while (size < list.size()) {
                        list.get(size).b += z ? 1 : -1;
                        size++;
                    }
                    return c0046d;
                }
                size--;
            }
            return null;
        }

        private void a(int i, int i2, int i3) {
            if (this.j[i - 1] != 0) {
                return;
            }
            a(i, i2, i3, false);
        }

        private void a(List<C0046d> list, android.support.v7.f.e eVar, int i, int i2, int i3) {
            if (!this.o) {
                eVar.a(i, i2);
                return;
            }
            for (int i4 = i2 - 1; i4 >= 0; i4--) {
                int i5 = i3 + i4;
                int i6 = this.k[i5] & 31;
                if (i6 == 0) {
                    eVar.a(i, 1);
                    for (C0046d c0046d : list) {
                        c0046d.b++;
                    }
                } else if (i6 == 4 || i6 == 8) {
                    int i7 = this.k[i5] >> 5;
                    eVar.c(a(list, i7, true).b, i);
                    if (i6 == 4) {
                        eVar.a(i, 1, this.l.c(i7, i5));
                    }
                } else if (i6 != 16) {
                    throw new IllegalStateException("unknown flag for pos " + i5 + " " + Long.toBinaryString(i6));
                } else {
                    list.add(new C0046d(i5, i, false));
                }
            }
        }

        private boolean a(int i, int i2, int i3, boolean z) {
            int i4;
            int i5;
            int i6;
            if (z) {
                i2--;
                i4 = i;
                i5 = i2;
            } else {
                i4 = i - 1;
                i5 = i4;
            }
            while (i3 >= 0) {
                f fVar = this.i.get(i3);
                int i7 = fVar.a + fVar.c;
                int i8 = fVar.b + fVar.c;
                if (z) {
                    for (int i9 = i4 - 1; i9 >= i7; i9--) {
                        if (this.l.a(i9, i5)) {
                            i6 = this.l.b(i9, i5) ? 8 : 4;
                            this.k[i5] = (i9 << 5) | 16;
                            this.j[i9] = (i5 << 5) | i6;
                            return true;
                        }
                    }
                    continue;
                } else {
                    for (int i10 = i2 - 1; i10 >= i8; i10--) {
                        if (this.l.a(i5, i10)) {
                            i6 = this.l.b(i5, i10) ? 8 : 4;
                            int i11 = i - 1;
                            this.j[i11] = (i10 << 5) | 16;
                            this.k[i10] = (i11 << 5) | i6;
                            return true;
                        }
                    }
                    continue;
                }
                i4 = fVar.a;
                i2 = fVar.b;
                i3--;
            }
            return false;
        }

        private void b() {
            f fVar = this.i.isEmpty() ? null : this.i.get(0);
            if (fVar != null && fVar.a == 0 && fVar.b == 0) {
                return;
            }
            f fVar2 = new f();
            fVar2.a = 0;
            fVar2.b = 0;
            fVar2.d = false;
            fVar2.c = 0;
            fVar2.e = false;
            this.i.add(0, fVar2);
        }

        private void b(int i, int i2, int i3) {
            if (this.k[i2 - 1] != 0) {
                return;
            }
            a(i, i2, i3, true);
        }

        private void b(List<C0046d> list, android.support.v7.f.e eVar, int i, int i2, int i3) {
            if (!this.o) {
                eVar.b(i, i2);
                return;
            }
            for (int i4 = i2 - 1; i4 >= 0; i4--) {
                int i5 = i3 + i4;
                int i6 = this.j[i5] & 31;
                if (i6 == 0) {
                    eVar.b(i + i4, 1);
                    for (C0046d c0046d : list) {
                        c0046d.b--;
                    }
                } else if (i6 == 4 || i6 == 8) {
                    int i7 = this.j[i5] >> 5;
                    C0046d a2 = a(list, i7, false);
                    eVar.c(i + i4, a2.b - 1);
                    if (i6 == 4) {
                        eVar.a(a2.b - 1, 1, this.l.c(i5, i7));
                    }
                } else if (i6 != 16) {
                    throw new IllegalStateException("unknown flag for pos " + i5 + " " + Long.toBinaryString(i6));
                } else {
                    list.add(new C0046d(i5, i + i4, true));
                }
            }
        }

        private void c() {
            int i = this.m;
            int i2 = this.n;
            for (int size = this.i.size() - 1; size >= 0; size--) {
                f fVar = this.i.get(size);
                int i3 = fVar.a + fVar.c;
                int i4 = fVar.b + fVar.c;
                if (this.o) {
                    while (i > i3) {
                        a(i, i2, size);
                        i--;
                    }
                    while (i2 > i4) {
                        b(i, i2, size);
                        i2--;
                    }
                }
                for (int i5 = 0; i5 < fVar.c; i5++) {
                    int i6 = fVar.a + i5;
                    int i7 = fVar.b + i5;
                    int i8 = this.l.b(i6, i7) ? 1 : 2;
                    this.j[i6] = (i7 << 5) | i8;
                    this.k[i7] = (i6 << 5) | i8;
                }
                i = fVar.a;
                i2 = fVar.b;
            }
        }

        public int a(@x(a = 0) int i) {
            if (i >= 0) {
                int[] iArr = this.j;
                if (i < iArr.length) {
                    int i2 = iArr[i];
                    if ((i2 & 31) == 0) {
                        return -1;
                    }
                    return i2 >> 5;
                }
            }
            throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + i + ", old list size = " + this.j.length);
        }

        @av
        List<f> a() {
            return this.i;
        }

        public void a(@af android.support.v7.f.e eVar) {
            android.support.v7.f.c cVar = eVar instanceof android.support.v7.f.c ? (android.support.v7.f.c) eVar : new android.support.v7.f.c(eVar);
            ArrayList arrayList = new ArrayList();
            int i = this.m;
            int i2 = this.n;
            for (int size = this.i.size() - 1; size >= 0; size--) {
                f fVar = this.i.get(size);
                int i3 = fVar.c;
                int i4 = fVar.a + i3;
                int i5 = fVar.b + i3;
                if (i4 < i) {
                    b(arrayList, cVar, i4, i - i4, i4);
                }
                if (i5 < i2) {
                    a(arrayList, cVar, i4, i2 - i5, i5);
                }
                for (int i6 = i3 - 1; i6 >= 0; i6--) {
                    if ((this.j[fVar.a + i6] & 31) == 2) {
                        cVar.a(fVar.a + i6, 1, this.l.c(fVar.a + i6, fVar.b + i6));
                    }
                }
                i = fVar.a;
                i2 = fVar.b;
            }
            cVar.a();
        }

        public void a(@af RecyclerView.a aVar) {
            a(new android.support.v7.f.a(aVar));
        }

        public int b(@x(a = 0) int i) {
            if (i >= 0) {
                int[] iArr = this.k;
                if (i < iArr.length) {
                    int i2 = iArr[i];
                    if ((i2 & 31) == 0) {
                        return -1;
                    }
                    return i2 >> 5;
                }
            }
            throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + i + ", new list size = " + this.k.length);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class c<T> {
        public abstract boolean a(@af T t, @af T t2);

        public abstract boolean b(@af T t, @af T t2);

        @ag
        public Object c(@af T t, @af T t2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: android.support.v7.f.d$d  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class C0046d {
        int a;
        int b;
        boolean c;

        public C0046d(int i, int i2, boolean z) {
            this.a = i;
            this.b = i2;
            this.c = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class e {
        int a;
        int b;
        int c;
        int d;

        public e() {
        }

        public e(int i, int i2, int i3, int i4) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class f {
        int a;
        int b;
        int c;
        boolean d;
        boolean e;

        f() {
        }
    }

    private d() {
    }

    @af
    public static b a(@af a aVar) {
        return a(aVar, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c9  */
    @android.support.annotation.af
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.support.v7.f.d.b a(@android.support.annotation.af android.support.v7.f.d.a r15, boolean r16) {
        /*
            Method dump skipped, instructions count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.f.d.a(android.support.v7.f.d$a, boolean):android.support.v7.f.d$b");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0042, code lost:
        if (r24[r13 - 1] < r24[r13 + r5]) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ba, code lost:
        if (r25[r13 - 1] < r25[r13 + 1]) goto L77;
     */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e3 A[LOOP:4: B:52:0x00cf->B:56:0x00e3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00ee A[EDGE_INSN: B:88:0x00ee->B:58:0x00ee ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.support.v7.f.d.f a(android.support.v7.f.d.a r19, int r20, int r21, int r22, int r23, int[] r24, int[] r25, int r26) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.f.d.a(android.support.v7.f.d$a, int, int, int, int, int[], int[], int):android.support.v7.f.d$f");
    }
}
