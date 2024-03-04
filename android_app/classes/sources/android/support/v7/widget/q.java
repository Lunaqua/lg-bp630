package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class q extends an {
    private static final boolean n = false;
    private static TimeInterpolator o;
    private ArrayList<RecyclerView.x> p = new ArrayList<>();
    private ArrayList<RecyclerView.x> q = new ArrayList<>();
    private ArrayList<b> r = new ArrayList<>();
    private ArrayList<a> s = new ArrayList<>();
    ArrayList<ArrayList<RecyclerView.x>> a = new ArrayList<>();
    ArrayList<ArrayList<b>> b = new ArrayList<>();
    ArrayList<ArrayList<a>> c = new ArrayList<>();
    ArrayList<RecyclerView.x> d = new ArrayList<>();
    ArrayList<RecyclerView.x> e = new ArrayList<>();
    ArrayList<RecyclerView.x> f = new ArrayList<>();
    ArrayList<RecyclerView.x> g = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a {
        public RecyclerView.x a;
        public RecyclerView.x b;
        public int c;
        public int d;
        public int e;
        public int f;

        private a(RecyclerView.x xVar, RecyclerView.x xVar2) {
            this.a = xVar;
            this.b = xVar2;
        }

        a(RecyclerView.x xVar, RecyclerView.x xVar2, int i, int i2, int i3, int i4) {
            this(xVar, xVar2);
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.f = i4;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.a + ", newHolder=" + this.b + ", fromX=" + this.c + ", fromY=" + this.d + ", toX=" + this.e + ", toY=" + this.f + '}';
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b {
        public RecyclerView.x a;
        public int b;
        public int c;
        public int d;
        public int e;

        b(RecyclerView.x xVar, int i, int i2, int i3, int i4) {
            this.a = xVar;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }
    }

    private void a(List<a> list, RecyclerView.x xVar) {
        for (int size = list.size() - 1; size >= 0; size--) {
            a aVar = list.get(size);
            if (a(aVar, xVar) && aVar.a == null && aVar.b == null) {
                list.remove(aVar);
            }
        }
    }

    private boolean a(a aVar, RecyclerView.x xVar) {
        boolean z = false;
        if (aVar.b == xVar) {
            aVar.b = null;
        } else if (aVar.a != xVar) {
            return false;
        } else {
            aVar.a = null;
            z = true;
        }
        xVar.a.setAlpha(1.0f);
        xVar.a.setTranslationX(0.0f);
        xVar.a.setTranslationY(0.0f);
        a(xVar, z);
        return true;
    }

    private void b(a aVar) {
        if (aVar.a != null) {
            a(aVar, aVar.a);
        }
        if (aVar.b != null) {
            a(aVar, aVar.b);
        }
    }

    private void w(final RecyclerView.x xVar) {
        final View view = xVar.a;
        final ViewPropertyAnimator animate = view.animate();
        this.f.add(xVar);
        animate.setDuration(g()).alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: android.support.v7.widget.q.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                animate.setListener(null);
                view.setAlpha(1.0f);
                q.this.k(xVar);
                q.this.f.remove(xVar);
                q.this.c();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                q.this.n(xVar);
            }
        }).start();
    }

    private void x(RecyclerView.x xVar) {
        if (o == null) {
            o = new ValueAnimator().getInterpolator();
        }
        xVar.a.animate().setInterpolator(o);
        d(xVar);
    }

    @Override // android.support.v7.widget.RecyclerView.f
    public void a() {
        boolean z = !this.p.isEmpty();
        boolean z2 = !this.r.isEmpty();
        boolean z3 = !this.s.isEmpty();
        boolean z4 = !this.q.isEmpty();
        if (z || z2 || z4 || z3) {
            Iterator<RecyclerView.x> it = this.p.iterator();
            while (it.hasNext()) {
                w(it.next());
            }
            this.p.clear();
            if (z2) {
                final ArrayList<b> arrayList = new ArrayList<>();
                arrayList.addAll(this.r);
                this.b.add(arrayList);
                this.r.clear();
                Runnable runnable = new Runnable() { // from class: android.support.v7.widget.q.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Iterator it2 = arrayList.iterator();
                        while (it2.hasNext()) {
                            b bVar = (b) it2.next();
                            q.this.b(bVar.a, bVar.b, bVar.c, bVar.d, bVar.e);
                        }
                        arrayList.clear();
                        q.this.b.remove(arrayList);
                    }
                };
                if (z) {
                    android.support.v4.view.ab.a(arrayList.get(0).a.a, runnable, g());
                } else {
                    runnable.run();
                }
            }
            if (z3) {
                final ArrayList<a> arrayList2 = new ArrayList<>();
                arrayList2.addAll(this.s);
                this.c.add(arrayList2);
                this.s.clear();
                Runnable runnable2 = new Runnable() { // from class: android.support.v7.widget.q.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Iterator it2 = arrayList2.iterator();
                        while (it2.hasNext()) {
                            q.this.a((a) it2.next());
                        }
                        arrayList2.clear();
                        q.this.c.remove(arrayList2);
                    }
                };
                if (z) {
                    android.support.v4.view.ab.a(arrayList2.get(0).a.a, runnable2, g());
                } else {
                    runnable2.run();
                }
            }
            if (z4) {
                final ArrayList<RecyclerView.x> arrayList3 = new ArrayList<>();
                arrayList3.addAll(this.q);
                this.a.add(arrayList3);
                this.q.clear();
                Runnable runnable3 = new Runnable() { // from class: android.support.v7.widget.q.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Iterator it2 = arrayList3.iterator();
                        while (it2.hasNext()) {
                            q.this.c((RecyclerView.x) it2.next());
                        }
                        arrayList3.clear();
                        q.this.a.remove(arrayList3);
                    }
                };
                if (z || z2 || z3) {
                    android.support.v4.view.ab.a(arrayList3.get(0).a, runnable3, (z ? g() : 0L) + Math.max(z2 ? e() : 0L, z3 ? h() : 0L));
                } else {
                    runnable3.run();
                }
            }
        }
    }

    void a(final a aVar) {
        RecyclerView.x xVar = aVar.a;
        final View view = xVar == null ? null : xVar.a;
        RecyclerView.x xVar2 = aVar.b;
        final View view2 = xVar2 != null ? xVar2.a : null;
        if (view != null) {
            final ViewPropertyAnimator duration = view.animate().setDuration(h());
            this.g.add(aVar.a);
            duration.translationX(aVar.e - aVar.c);
            duration.translationY(aVar.f - aVar.d);
            duration.alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: android.support.v7.widget.q.7
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    duration.setListener(null);
                    view.setAlpha(1.0f);
                    view.setTranslationX(0.0f);
                    view.setTranslationY(0.0f);
                    q.this.a(aVar.a, true);
                    q.this.g.remove(aVar.a);
                    q.this.c();
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    q.this.b(aVar.a, true);
                }
            }).start();
        }
        if (view2 != null) {
            final ViewPropertyAnimator animate = view2.animate();
            this.g.add(aVar.b);
            animate.translationX(0.0f).translationY(0.0f).setDuration(h()).alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: android.support.v7.widget.q.8
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    animate.setListener(null);
                    view2.setAlpha(1.0f);
                    view2.setTranslationX(0.0f);
                    view2.setTranslationY(0.0f);
                    q.this.a(aVar.b, false);
                    q.this.g.remove(aVar.b);
                    q.this.c();
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    q.this.b(aVar.b, false);
                }
            }).start();
        }
    }

    void a(List<RecyclerView.x> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).a.animate().cancel();
        }
    }

    @Override // android.support.v7.widget.an
    public boolean a(RecyclerView.x xVar) {
        x(xVar);
        this.p.add(xVar);
        return true;
    }

    @Override // android.support.v7.widget.an
    public boolean a(RecyclerView.x xVar, int i, int i2, int i3, int i4) {
        View view = xVar.a;
        int translationX = i + ((int) xVar.a.getTranslationX());
        int translationY = i2 + ((int) xVar.a.getTranslationY());
        x(xVar);
        int i5 = i3 - translationX;
        int i6 = i4 - translationY;
        if (i5 == 0 && i6 == 0) {
            l(xVar);
            return false;
        }
        if (i5 != 0) {
            view.setTranslationX(-i5);
        }
        if (i6 != 0) {
            view.setTranslationY(-i6);
        }
        this.r.add(new b(xVar, translationX, translationY, i3, i4));
        return true;
    }

    @Override // android.support.v7.widget.an
    public boolean a(RecyclerView.x xVar, RecyclerView.x xVar2, int i, int i2, int i3, int i4) {
        if (xVar == xVar2) {
            return a(xVar, i, i2, i3, i4);
        }
        float translationX = xVar.a.getTranslationX();
        float translationY = xVar.a.getTranslationY();
        float alpha = xVar.a.getAlpha();
        x(xVar);
        int i5 = (int) ((i3 - i) - translationX);
        int i6 = (int) ((i4 - i2) - translationY);
        xVar.a.setTranslationX(translationX);
        xVar.a.setTranslationY(translationY);
        xVar.a.setAlpha(alpha);
        if (xVar2 != null) {
            x(xVar2);
            xVar2.a.setTranslationX(-i5);
            xVar2.a.setTranslationY(-i6);
            xVar2.a.setAlpha(0.0f);
        }
        this.s.add(new a(xVar, xVar2, i, i2, i3, i4));
        return true;
    }

    @Override // android.support.v7.widget.RecyclerView.f
    public boolean a(@android.support.annotation.af RecyclerView.x xVar, @android.support.annotation.af List<Object> list) {
        return !list.isEmpty() || super.a(xVar, list);
    }

    void b(final RecyclerView.x xVar, int i, int i2, int i3, int i4) {
        final View view = xVar.a;
        final int i5 = i3 - i;
        final int i6 = i4 - i2;
        if (i5 != 0) {
            view.animate().translationX(0.0f);
        }
        if (i6 != 0) {
            view.animate().translationY(0.0f);
        }
        final ViewPropertyAnimator animate = view.animate();
        this.e.add(xVar);
        animate.setDuration(e()).setListener(new AnimatorListenerAdapter() { // from class: android.support.v7.widget.q.6
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                if (i5 != 0) {
                    view.setTranslationX(0.0f);
                }
                if (i6 != 0) {
                    view.setTranslationY(0.0f);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                animate.setListener(null);
                q.this.l(xVar);
                q.this.e.remove(xVar);
                q.this.c();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                q.this.o(xVar);
            }
        }).start();
    }

    @Override // android.support.v7.widget.RecyclerView.f
    public boolean b() {
        return (this.q.isEmpty() && this.s.isEmpty() && this.r.isEmpty() && this.p.isEmpty() && this.e.isEmpty() && this.f.isEmpty() && this.d.isEmpty() && this.g.isEmpty() && this.b.isEmpty() && this.a.isEmpty() && this.c.isEmpty()) ? false : true;
    }

    @Override // android.support.v7.widget.an
    public boolean b(RecyclerView.x xVar) {
        x(xVar);
        xVar.a.setAlpha(0.0f);
        this.q.add(xVar);
        return true;
    }

    void c() {
        if (b()) {
            return;
        }
        i();
    }

    void c(final RecyclerView.x xVar) {
        final View view = xVar.a;
        final ViewPropertyAnimator animate = view.animate();
        this.d.add(xVar);
        animate.alpha(1.0f).setDuration(f()).setListener(new AnimatorListenerAdapter() { // from class: android.support.v7.widget.q.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                view.setAlpha(1.0f);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                animate.setListener(null);
                q.this.m(xVar);
                q.this.d.remove(xVar);
                q.this.c();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                q.this.p(xVar);
            }
        }).start();
    }

    @Override // android.support.v7.widget.RecyclerView.f
    public void d() {
        int size = this.r.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            b bVar = this.r.get(size);
            View view = bVar.a.a;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            l(bVar.a);
            this.r.remove(size);
        }
        for (int size2 = this.p.size() - 1; size2 >= 0; size2--) {
            k(this.p.get(size2));
            this.p.remove(size2);
        }
        int size3 = this.q.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.x xVar = this.q.get(size3);
            xVar.a.setAlpha(1.0f);
            m(xVar);
            this.q.remove(size3);
        }
        for (int size4 = this.s.size() - 1; size4 >= 0; size4--) {
            b(this.s.get(size4));
        }
        this.s.clear();
        if (b()) {
            for (int size5 = this.b.size() - 1; size5 >= 0; size5--) {
                ArrayList<b> arrayList = this.b.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    b bVar2 = arrayList.get(size6);
                    View view2 = bVar2.a.a;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    l(bVar2.a);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.b.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.a.size() - 1; size7 >= 0; size7--) {
                ArrayList<RecyclerView.x> arrayList2 = this.a.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    RecyclerView.x xVar2 = arrayList2.get(size8);
                    xVar2.a.setAlpha(1.0f);
                    m(xVar2);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.a.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.c.size() - 1; size9 >= 0; size9--) {
                ArrayList<a> arrayList3 = this.c.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    b(arrayList3.get(size10));
                    if (arrayList3.isEmpty()) {
                        this.c.remove(arrayList3);
                    }
                }
            }
            a(this.f);
            a(this.e);
            a(this.d);
            a(this.g);
            i();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.f
    public void d(RecyclerView.x xVar) {
        View view = xVar.a;
        view.animate().cancel();
        int size = this.r.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            } else if (this.r.get(size).a == xVar) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                l(xVar);
                this.r.remove(size);
            }
        }
        a(this.s, xVar);
        if (this.p.remove(xVar)) {
            view.setAlpha(1.0f);
            k(xVar);
        }
        if (this.q.remove(xVar)) {
            view.setAlpha(1.0f);
            m(xVar);
        }
        for (int size2 = this.c.size() - 1; size2 >= 0; size2--) {
            ArrayList<a> arrayList = this.c.get(size2);
            a(arrayList, xVar);
            if (arrayList.isEmpty()) {
                this.c.remove(size2);
            }
        }
        for (int size3 = this.b.size() - 1; size3 >= 0; size3--) {
            ArrayList<b> arrayList2 = this.b.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                } else if (arrayList2.get(size4).a == xVar) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    l(xVar);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.b.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.a.size() - 1; size5 >= 0; size5--) {
            ArrayList<RecyclerView.x> arrayList3 = this.a.get(size5);
            if (arrayList3.remove(xVar)) {
                view.setAlpha(1.0f);
                m(xVar);
                if (arrayList3.isEmpty()) {
                    this.a.remove(size5);
                }
            }
        }
        this.f.remove(xVar);
        this.d.remove(xVar);
        this.g.remove(xVar);
        this.e.remove(xVar);
        c();
    }
}
