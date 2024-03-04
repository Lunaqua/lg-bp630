package android.support.v4.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.v4.view.ab;
import android.support.v4.view.ae;
import android.support.v4.widget.k;
import android.support.v7.widget.ActivityChooserView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.util.ArrayList;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class j extends android.support.v4.view.a {
    public static final int a = Integer.MIN_VALUE;
    public static final int b = -1;
    private static final String e = "android.view.View";
    private static final Rect f = new Rect(ActivityChooserView.a.a, ActivityChooserView.a.a, Integer.MIN_VALUE, Integer.MIN_VALUE);
    private static final k.a<android.support.v4.view.a.c> o = new k.a<android.support.v4.view.a.c>() { // from class: android.support.v4.widget.j.1
        @Override // android.support.v4.widget.k.a
        public void a(android.support.v4.view.a.c cVar, Rect rect) {
            cVar.a(rect);
        }
    };
    private static final k.b<android.support.v4.j.s<android.support.v4.view.a.c>, android.support.v4.view.a.c> p = new k.b<android.support.v4.j.s<android.support.v4.view.a.c>, android.support.v4.view.a.c>() { // from class: android.support.v4.widget.j.2
        @Override // android.support.v4.widget.k.b
        public int a(android.support.v4.j.s<android.support.v4.view.a.c> sVar) {
            return sVar.b();
        }

        @Override // android.support.v4.widget.k.b
        public android.support.v4.view.a.c a(android.support.v4.j.s<android.support.v4.view.a.c> sVar, int i) {
            return sVar.f(i);
        }
    };
    private final AccessibilityManager k;
    private final View l;
    private a m;
    private final Rect g = new Rect();
    private final Rect h = new Rect();
    private final Rect i = new Rect();
    private final int[] j = new int[2];
    int c = Integer.MIN_VALUE;
    int d = Integer.MIN_VALUE;
    private int n = Integer.MIN_VALUE;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class a extends android.support.v4.view.a.d {
        a() {
        }

        @Override // android.support.v4.view.a.d
        public android.support.v4.view.a.c a(int i) {
            return android.support.v4.view.a.c.a(j.this.b(i));
        }

        @Override // android.support.v4.view.a.d
        public boolean a(int i, int i2, Bundle bundle) {
            return j.this.a(i, i2, bundle);
        }

        @Override // android.support.v4.view.a.d
        public android.support.v4.view.a.c b(int i) {
            int i2 = i == 2 ? j.this.c : j.this.d;
            if (i2 == Integer.MIN_VALUE) {
                return null;
            }
            return a(i2);
        }
    }

    public j(@af View view) {
        if (view == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.l = view;
        this.k = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        if (ab.g(view) == 0) {
            ab.e(view, 1);
        }
    }

    private static Rect a(@af View view, int i, @af Rect rect) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (i == 17) {
            rect.set(width, 0, width, height);
        } else if (i == 33) {
            rect.set(0, height, width, height);
        } else if (i == 66) {
            rect.set(-1, 0, -1, height);
        } else if (i != 130) {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        } else {
            rect.set(0, -1, width, -1);
        }
        return rect;
    }

    private void a(int i, Rect rect) {
        b(i).a(rect);
    }

    private boolean a(int i, Bundle bundle) {
        return ab.a(this.l, i, bundle);
    }

    private boolean a(Rect rect) {
        if (rect == null || rect.isEmpty() || this.l.getWindowVisibility() != 0) {
            return false;
        }
        View view = this.l;
        do {
            ViewParent parent = view.getParent();
            if (!(parent instanceof View)) {
                return parent != null;
            }
            view = (View) parent;
            if (view.getAlpha() <= 0.0f) {
                break;
            }
        } while (view.getVisibility() == 0);
        return false;
    }

    private boolean b(int i, @ag Rect rect) {
        Object a2;
        android.support.v4.j.s<android.support.v4.view.a.c> f2 = f();
        int i2 = this.d;
        android.support.v4.view.a.c a3 = i2 == Integer.MIN_VALUE ? null : f2.a(i2);
        if (i == 1 || i == 2) {
            a2 = k.a(f2, p, o, a3, i, ab.m(this.l) == 1, false);
        } else if (i != 17 && i != 33 && i != 66 && i != 130) {
            throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        } else {
            Rect rect2 = new Rect();
            int i3 = this.d;
            if (i3 != Integer.MIN_VALUE) {
                a(i3, rect2);
            } else if (rect != null) {
                rect2.set(rect);
            } else {
                a(this.l, i, rect2);
            }
            a2 = k.a(f2, p, o, a3, rect2, i);
        }
        android.support.v4.view.a.c cVar = (android.support.v4.view.a.c) a2;
        return c(cVar != null ? f2.e(f2.a((android.support.v4.j.s<android.support.v4.view.a.c>) cVar)) : Integer.MIN_VALUE);
    }

    private AccessibilityEvent c(int i, int i2) {
        return i != -1 ? d(i, i2) : g(i2);
    }

    private boolean c(int i, int i2, Bundle bundle) {
        return i2 != 1 ? i2 != 2 ? i2 != 64 ? i2 != 128 ? b(i, i2, bundle) : j(i) : i(i) : d(i) : c(i);
    }

    private AccessibilityEvent d(int i, int i2) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
        android.support.v4.view.a.c b2 = b(i);
        obtain.getText().add(b2.x());
        obtain.setContentDescription(b2.y());
        obtain.setScrollable(b2.t());
        obtain.setPassword(b2.s());
        obtain.setEnabled(b2.r());
        obtain.setChecked(b2.j());
        a(i, obtain);
        if (obtain.getText().isEmpty() && obtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        obtain.setClassName(b2.w());
        android.support.v4.view.a.e.a(obtain, this.l, i);
        obtain.setPackageName(this.l.getContext().getPackageName());
        return obtain;
    }

    private static int e(int i) {
        if (i != 19) {
            if (i != 21) {
                return i != 22 ? 130 : 66;
            }
            return 17;
        }
        return 33;
    }

    private android.support.v4.j.s<android.support.v4.view.a.c> f() {
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        android.support.v4.j.s<android.support.v4.view.a.c> sVar = new android.support.v4.j.s<>();
        for (int i = 0; i < arrayList.size(); i++) {
            sVar.b(i, h(i));
        }
        return sVar;
    }

    private void f(int i) {
        int i2 = this.n;
        if (i2 == i) {
            return;
        }
        this.n = i;
        a(i, 128);
        a(i2, 256);
    }

    private AccessibilityEvent g(int i) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i);
        this.l.onInitializeAccessibilityEvent(obtain);
        return obtain;
    }

    private boolean g() {
        int i = this.d;
        return i != Integer.MIN_VALUE && b(i, 16, null);
    }

    @af
    private android.support.v4.view.a.c h() {
        android.support.v4.view.a.c a2 = android.support.v4.view.a.c.a(this.l);
        ab.a(this.l, a2);
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        if (a2.e() <= 0 || arrayList.size() <= 0) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                a2.c(this.l, ((Integer) arrayList.get(i)).intValue());
            }
            return a2;
        }
        throw new RuntimeException("Views cannot have both real and virtual children");
    }

    @af
    private android.support.v4.view.a.c h(int i) {
        android.support.v4.view.a.c c = android.support.v4.view.a.c.c();
        c.j(true);
        c.c(true);
        c.b(e);
        c.b(f);
        c.d(f);
        c.e(this.l);
        a(i, c);
        if (c.x() == null && c.y() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        c.a(this.h);
        if (this.h.equals(f)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int f2 = c.f();
        if ((f2 & 64) == 0) {
            if ((f2 & 128) == 0) {
                c.a((CharSequence) this.l.getContext().getPackageName());
                c.b(this.l, i);
                if (this.c == i) {
                    c.f(true);
                    c.d(128);
                } else {
                    c.f(false);
                    c.d(64);
                }
                boolean z = this.d == i;
                if (z) {
                    c.d(2);
                } else if (c.k()) {
                    c.d(1);
                }
                c.d(z);
                this.l.getLocationOnScreen(this.j);
                c.c(this.g);
                if (this.g.equals(f)) {
                    c.a(this.g);
                    if (c.a != -1) {
                        android.support.v4.view.a.c c2 = android.support.v4.view.a.c.c();
                        for (int i2 = c.a; i2 != -1; i2 = c2.a) {
                            c2.e(this.l, -1);
                            c2.b(f);
                            a(i2, c2);
                            c2.a(this.h);
                            this.g.offset(this.h.left, this.h.top);
                        }
                        c2.z();
                    }
                    this.g.offset(this.j[0] - this.l.getScrollX(), this.j[1] - this.l.getScrollY());
                }
                if (this.l.getLocalVisibleRect(this.i)) {
                    this.i.offset(this.j[0] - this.l.getScrollX(), this.j[1] - this.l.getScrollY());
                    if (this.g.intersect(this.i)) {
                        c.d(this.g);
                        if (a(this.g)) {
                            c.e(true);
                        }
                    }
                }
                return c;
            }
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
    }

    private boolean i(int i) {
        int i2;
        if (this.k.isEnabled() && this.k.isTouchExplorationEnabled() && (i2 = this.c) != i) {
            if (i2 != Integer.MIN_VALUE) {
                j(i2);
            }
            this.c = i;
            this.l.invalidate();
            a(i, 32768);
            return true;
        }
        return false;
    }

    private boolean j(int i) {
        if (this.c == i) {
            this.c = Integer.MIN_VALUE;
            this.l.invalidate();
            a(i, 65536);
            return true;
        }
        return false;
    }

    protected abstract int a(float f2, float f3);

    @Override // android.support.v4.view.a
    public android.support.v4.view.a.d a(View view) {
        if (this.m == null) {
            this.m = new a();
        }
        return this.m;
    }

    public final void a(int i) {
        b(i, 0);
    }

    protected abstract void a(int i, @af android.support.v4.view.a.c cVar);

    protected void a(int i, @af AccessibilityEvent accessibilityEvent) {
    }

    protected void a(int i, boolean z) {
    }

    protected void a(@af android.support.v4.view.a.c cVar) {
    }

    @Override // android.support.v4.view.a
    public void a(View view, android.support.v4.view.a.c cVar) {
        super.a(view, cVar);
        a(cVar);
    }

    protected void a(@af AccessibilityEvent accessibilityEvent) {
    }

    protected abstract void a(List<Integer> list);

    public final void a(boolean z, int i, @ag Rect rect) {
        int i2 = this.d;
        if (i2 != Integer.MIN_VALUE) {
            d(i2);
        }
        if (z) {
            b(i, rect);
        }
    }

    public final boolean a(int i, int i2) {
        ViewParent parent;
        if (i == Integer.MIN_VALUE || !this.k.isEnabled() || (parent = this.l.getParent()) == null) {
            return false;
        }
        return ae.a(parent, this.l, c(i, i2));
    }

    boolean a(int i, int i2, Bundle bundle) {
        return i != -1 ? c(i, i2, bundle) : a(i2, bundle);
    }

    public final boolean a(@af KeyEvent keyEvent) {
        int i = 0;
        if (keyEvent.getAction() != 1) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return b(2, (Rect) null);
                }
                if (keyEvent.hasModifiers(1)) {
                    return b(1, (Rect) null);
                }
                return false;
            }
            if (keyCode != 66) {
                switch (keyCode) {
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                        if (keyEvent.hasNoModifiers()) {
                            int e2 = e(keyCode);
                            int repeatCount = keyEvent.getRepeatCount() + 1;
                            boolean z = false;
                            while (i < repeatCount && b(e2, (Rect) null)) {
                                i++;
                                z = true;
                            }
                            return z;
                        }
                        return false;
                    case 23:
                        break;
                    default:
                        return false;
                }
            }
            if (keyEvent.hasNoModifiers() && keyEvent.getRepeatCount() == 0) {
                g();
                return true;
            }
            return false;
        }
        return false;
    }

    public final boolean a(@af MotionEvent motionEvent) {
        if (this.k.isEnabled() && this.k.isTouchExplorationEnabled()) {
            int action = motionEvent.getAction();
            if (action == 7 || action == 9) {
                int a2 = a(motionEvent.getX(), motionEvent.getY());
                f(a2);
                return a2 != Integer.MIN_VALUE;
            } else if (action == 10 && this.n != Integer.MIN_VALUE) {
                f(Integer.MIN_VALUE);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public final int b() {
        return this.c;
    }

    @af
    android.support.v4.view.a.c b(int i) {
        return i == -1 ? h() : h(i);
    }

    public final void b(int i, int i2) {
        ViewParent parent;
        if (i == Integer.MIN_VALUE || !this.k.isEnabled() || (parent = this.l.getParent()) == null) {
            return;
        }
        AccessibilityEvent c = c(i, 2048);
        android.support.v4.view.a.a.b(c, i2);
        ae.a(parent, this.l, c);
    }

    protected abstract boolean b(int i, int i2, @ag Bundle bundle);

    public final int c() {
        return this.d;
    }

    public final boolean c(int i) {
        int i2;
        if ((this.l.isFocused() || this.l.requestFocus()) && (i2 = this.d) != i) {
            if (i2 != Integer.MIN_VALUE) {
                d(i2);
            }
            this.d = i;
            a(i, true);
            a(i, 8);
            return true;
        }
        return false;
    }

    public final void d() {
        b(-1, 1);
    }

    @Override // android.support.v4.view.a
    public void d(View view, AccessibilityEvent accessibilityEvent) {
        super.d(view, accessibilityEvent);
        a(accessibilityEvent);
    }

    public final boolean d(int i) {
        if (this.d != i) {
            return false;
        }
        this.d = Integer.MIN_VALUE;
        a(i, false);
        a(i, 8);
        return true;
    }

    @Deprecated
    public int e() {
        return b();
    }
}
