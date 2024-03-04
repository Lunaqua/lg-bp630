package android.support.v7.widget;

import android.support.v4.j.p;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ad;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class d implements ad.a {
    static final int a = 0;
    static final int b = 1;
    private static final boolean i = false;
    private static final String j = "AHT";
    final ArrayList<b> c;
    final ArrayList<b> d;
    final a e;
    Runnable f;
    final boolean g;
    final ad h;
    private p.a<b> k;
    private int l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        RecyclerView.x a(int i);

        void a(int i, int i2);

        void a(int i, int i2, Object obj);

        void a(b bVar);

        void b(int i, int i2);

        void b(b bVar);

        void c(int i, int i2);

        void d(int i, int i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b {
        static final int a = 1;
        static final int b = 2;
        static final int c = 4;
        static final int d = 8;
        static final int e = 30;
        int f;
        int g;
        Object h;
        int i;

        b(int i, int i2, int i3, Object obj) {
            this.f = i;
            this.g = i2;
            this.i = i3;
            this.h = obj;
        }

        String a() {
            int i = this.f;
            return i != 1 ? i != 2 ? i != 4 ? i != 8 ? "??" : "mv" : "up" : "rm" : "add";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            int i = this.f;
            if (i != bVar.f) {
                return false;
            }
            if (i == 8 && Math.abs(this.i - this.g) == 1 && this.i == bVar.g && this.g == bVar.i) {
                return true;
            }
            if (this.i == bVar.i && this.g == bVar.g) {
                Object obj2 = this.h;
                if (obj2 != null) {
                    if (!obj2.equals(bVar.h)) {
                        return false;
                    }
                } else if (bVar.h != null) {
                    return false;
                }
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((this.f * 31) + this.g) * 31) + this.i;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.g + "c:" + this.i + ",p:" + this.h + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(a aVar) {
        this(aVar, false);
    }

    d(a aVar, boolean z) {
        this.k = new p.b(30);
        this.c = new ArrayList<>();
        this.d = new ArrayList<>();
        this.l = 0;
        this.e = aVar;
        this.g = z;
        this.h = new ad(this);
    }

    private void b(b bVar) {
        g(bVar);
    }

    private void c(b bVar) {
        boolean z;
        char c;
        int i2 = bVar.g;
        int i3 = bVar.g + bVar.i;
        int i4 = bVar.g;
        int i5 = 0;
        char c2 = 65535;
        while (i4 < i3) {
            if (this.e.a(i4) != null || d(i4)) {
                if (c2 == 0) {
                    e(a(2, i2, i5, null));
                    z = true;
                } else {
                    z = false;
                }
                c = 1;
            } else {
                if (c2 == 1) {
                    g(a(2, i2, i5, null));
                    z = true;
                } else {
                    z = false;
                }
                c = 0;
            }
            if (z) {
                i4 -= i5;
                i3 -= i5;
                i5 = 1;
            } else {
                i5++;
            }
            i4++;
            c2 = c;
        }
        if (i5 != bVar.i) {
            a(bVar);
            bVar = a(2, i2, i5, null);
        }
        if (c2 == 0) {
            e(bVar);
        } else {
            g(bVar);
        }
    }

    private int d(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        for (int size = this.d.size() - 1; size >= 0; size--) {
            b bVar = this.d.get(size);
            if (bVar.f == 8) {
                if (bVar.g < bVar.i) {
                    i5 = bVar.g;
                    i6 = bVar.i;
                } else {
                    i5 = bVar.i;
                    i6 = bVar.g;
                }
                if (i2 < i5 || i2 > i6) {
                    if (i2 < bVar.g) {
                        if (i3 == 1) {
                            bVar.g++;
                            i7 = bVar.i + 1;
                        } else if (i3 == 2) {
                            bVar.g--;
                            i7 = bVar.i - 1;
                        }
                        bVar.i = i7;
                    }
                } else if (i5 == bVar.g) {
                    if (i3 == 1) {
                        i9 = bVar.i + 1;
                    } else {
                        if (i3 == 2) {
                            i9 = bVar.i - 1;
                        }
                        i2++;
                    }
                    bVar.i = i9;
                    i2++;
                } else {
                    if (i3 == 1) {
                        i8 = bVar.g + 1;
                    } else {
                        if (i3 == 2) {
                            i8 = bVar.g - 1;
                        }
                        i2--;
                    }
                    bVar.g = i8;
                    i2--;
                }
            } else if (bVar.g > i2) {
                if (i3 == 1) {
                    i4 = bVar.g + 1;
                } else if (i3 == 2) {
                    i4 = bVar.g - 1;
                }
                bVar.g = i4;
            } else if (bVar.f == 1) {
                i2 -= bVar.i;
            } else if (bVar.f == 2) {
                i2 += bVar.i;
            }
        }
        for (int size2 = this.d.size() - 1; size2 >= 0; size2--) {
            b bVar2 = this.d.get(size2);
            if (bVar2.f == 8) {
                if (bVar2.i != bVar2.g && bVar2.i >= 0) {
                }
                this.d.remove(size2);
                a(bVar2);
            } else {
                if (bVar2.i > 0) {
                }
                this.d.remove(size2);
                a(bVar2);
            }
        }
        return i2;
    }

    private void d(b bVar) {
        int i2 = bVar.g;
        int i3 = bVar.g + bVar.i;
        int i4 = i2;
        int i5 = 0;
        char c = 65535;
        for (int i6 = bVar.g; i6 < i3; i6++) {
            if (this.e.a(i6) != null || d(i6)) {
                if (c == 0) {
                    e(a(4, i4, i5, bVar.h));
                    i4 = i6;
                    i5 = 0;
                }
                c = 1;
            } else {
                if (c == 1) {
                    g(a(4, i4, i5, bVar.h));
                    i4 = i6;
                    i5 = 0;
                }
                c = 0;
            }
            i5++;
        }
        if (i5 != bVar.i) {
            Object obj = bVar.h;
            a(bVar);
            bVar = a(4, i4, i5, obj);
        }
        if (c == 0) {
            e(bVar);
        } else {
            g(bVar);
        }
    }

    private boolean d(int i2) {
        int size = this.d.size();
        for (int i3 = 0; i3 < size; i3++) {
            b bVar = this.d.get(i3);
            if (bVar.f == 8) {
                if (a(bVar.i, i3 + 1) == i2) {
                    return true;
                }
            } else if (bVar.f == 1) {
                int i4 = bVar.g + bVar.i;
                for (int i5 = bVar.g; i5 < i4; i5++) {
                    if (a(i5, i3 + 1) == i2) {
                        return true;
                    }
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    private void e(b bVar) {
        int i2;
        if (bVar.f == 1 || bVar.f == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int d = d(bVar.g, bVar.f);
        int i3 = bVar.g;
        int i4 = bVar.f;
        if (i4 == 2) {
            i2 = 0;
        } else if (i4 != 4) {
            throw new IllegalArgumentException("op should be remove or update." + bVar);
        } else {
            i2 = 1;
        }
        int i5 = d;
        int i6 = i3;
        int i7 = 1;
        for (int i8 = 1; i8 < bVar.i; i8++) {
            int d2 = d(bVar.g + (i2 * i8), bVar.f);
            int i9 = bVar.f;
            if (i9 == 2 ? d2 == i5 : i9 == 4 && d2 == i5 + 1) {
                i7++;
            } else {
                b a2 = a(bVar.f, i5, i7, bVar.h);
                a(a2, i6);
                a(a2);
                if (bVar.f == 4) {
                    i6 += i7;
                }
                i5 = d2;
                i7 = 1;
            }
        }
        Object obj = bVar.h;
        a(bVar);
        if (i7 > 0) {
            b a3 = a(bVar.f, i5, i7, obj);
            a(a3, i6);
            a(a3);
        }
    }

    private void f(b bVar) {
        g(bVar);
    }

    private void g(b bVar) {
        this.d.add(bVar);
        int i2 = bVar.f;
        if (i2 == 1) {
            this.e.c(bVar.g, bVar.i);
        } else if (i2 == 2) {
            this.e.b(bVar.g, bVar.i);
        } else if (i2 == 4) {
            this.e.a(bVar.g, bVar.i, bVar.h);
        } else if (i2 == 8) {
            this.e.d(bVar.g, bVar.i);
        } else {
            throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    int a(int i2, int i3) {
        int size = this.d.size();
        while (i3 < size) {
            b bVar = this.d.get(i3);
            if (bVar.f == 8) {
                if (bVar.g == i2) {
                    i2 = bVar.i;
                } else {
                    if (bVar.g < i2) {
                        i2--;
                    }
                    if (bVar.i <= i2) {
                        i2++;
                    }
                }
            } else if (bVar.g > i2) {
                continue;
            } else if (bVar.f == 2) {
                if (i2 < bVar.g + bVar.i) {
                    return -1;
                }
                i2 -= bVar.i;
            } else if (bVar.f == 1) {
                i2 += bVar.i;
            }
            i3++;
        }
        return i2;
    }

    @Override // android.support.v7.widget.ad.a
    public b a(int i2, int i3, int i4, Object obj) {
        b a2 = this.k.a();
        if (a2 == null) {
            return new b(i2, i3, i4, obj);
        }
        a2.f = i2;
        a2.g = i3;
        a2.i = i4;
        a2.h = obj;
        return a2;
    }

    d a(b... bVarArr) {
        Collections.addAll(this.c, bVarArr);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        a(this.c);
        a(this.d);
        this.l = 0;
    }

    @Override // android.support.v7.widget.ad.a
    public void a(b bVar) {
        if (this.g) {
            return;
        }
        bVar.h = null;
        this.k.a(bVar);
    }

    void a(b bVar, int i2) {
        this.e.a(bVar);
        int i3 = bVar.f;
        if (i3 == 2) {
            this.e.a(i2, bVar.i);
        } else if (i3 != 4) {
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        } else {
            this.e.a(i2, bVar.i, bVar.h);
        }
    }

    void a(List<b> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            a(list.get(i2));
        }
        list.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(int i2) {
        return (i2 & this.l) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(int i2, int i3, int i4) {
        if (i2 == i3) {
            return false;
        }
        if (i4 == 1) {
            this.c.add(a(8, i2, i3, null));
            this.l |= 8;
            return this.c.size() == 1;
        }
        throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(int i2, int i3, Object obj) {
        if (i3 < 1) {
            return false;
        }
        this.c.add(a(4, i2, i3, obj));
        this.l |= 4;
        return this.c.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b(int i2) {
        return a(i2, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        this.h.a(this.c);
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.c.get(i2);
            int i3 = bVar.f;
            if (i3 == 1) {
                f(bVar);
            } else if (i3 == 2) {
                c(bVar);
            } else if (i3 == 4) {
                d(bVar);
            } else if (i3 == 8) {
                b(bVar);
            }
            Runnable runnable = this.f;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.c.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(int i2, int i3) {
        if (i3 < 1) {
            return false;
        }
        this.c.add(a(1, i2, i3, null));
        this.l |= 1;
        return this.c.size() == 1;
    }

    public int c(int i2) {
        int size = this.c.size();
        for (int i3 = 0; i3 < size; i3++) {
            b bVar = this.c.get(i3);
            int i4 = bVar.f;
            if (i4 != 1) {
                if (i4 != 2) {
                    if (i4 == 8) {
                        if (bVar.g == i2) {
                            i2 = bVar.i;
                        } else {
                            if (bVar.g < i2) {
                                i2--;
                            }
                            if (bVar.i <= i2) {
                                i2++;
                            }
                        }
                    }
                } else if (bVar.g > i2) {
                    continue;
                } else if (bVar.g + bVar.i > i2) {
                    return -1;
                } else {
                    i2 -= bVar.i;
                }
            } else if (bVar.g <= i2) {
                i2 += bVar.i;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        int size = this.d.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.e.b(this.d.get(i2));
        }
        a(this.d);
        this.l = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(int i2, int i3) {
        if (i3 < 1) {
            return false;
        }
        this.c.add(a(2, i2, i3, null));
        this.l |= 2;
        return this.c.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        return this.c.size() > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        c();
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.c.get(i2);
            int i3 = bVar.f;
            if (i3 == 1) {
                this.e.b(bVar);
                this.e.c(bVar.g, bVar.i);
            } else if (i3 == 2) {
                this.e.b(bVar);
                this.e.a(bVar.g, bVar.i);
            } else if (i3 == 4) {
                this.e.b(bVar);
                this.e.a(bVar.g, bVar.i, bVar.h);
            } else if (i3 == 8) {
                this.e.b(bVar);
                this.e.d(bVar.g, bVar.i);
            }
            Runnable runnable = this.f;
            if (runnable != null) {
                runnable.run();
            }
        }
        a(this.c);
        this.l = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f() {
        return (this.d.isEmpty() || this.c.isEmpty()) ? false : true;
    }
}
