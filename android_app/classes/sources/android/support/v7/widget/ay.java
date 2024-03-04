package android.support.v7.widget;

import android.support.annotation.an;
import android.support.v7.widget.ActivityChooserView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class ay implements View.OnAttachStateChangeListener, View.OnHoverListener, View.OnLongClickListener {
    private static final String a = "TooltipCompatHandler";
    private static final long b = 2500;
    private static final long c = 15000;
    private static final long d = 3000;
    private static ay n;
    private static ay o;
    private final View e;
    private final CharSequence f;
    private final int g;
    private final Runnable h = new Runnable() { // from class: android.support.v7.widget.ay.1
        @Override // java.lang.Runnable
        public void run() {
            ay.this.a(false);
        }
    };
    private final Runnable i = new Runnable() { // from class: android.support.v7.widget.ay.2
        @Override // java.lang.Runnable
        public void run() {
            ay.this.a();
        }
    };
    private int j;
    private int k;
    private az l;
    private boolean m;

    private ay(View view, CharSequence charSequence) {
        this.e = view;
        this.f = charSequence;
        this.g = android.support.v4.view.ac.c(ViewConfiguration.get(this.e.getContext()));
        d();
        this.e.setOnLongClickListener(this);
        this.e.setOnHoverListener(this);
    }

    private static void a(ay ayVar) {
        ay ayVar2 = n;
        if (ayVar2 != null) {
            ayVar2.c();
        }
        n = ayVar;
        ay ayVar3 = n;
        if (ayVar3 != null) {
            ayVar3.b();
        }
    }

    public static void a(View view, CharSequence charSequence) {
        ay ayVar = n;
        if (ayVar != null && ayVar.e == view) {
            a((ay) null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new ay(view, charSequence);
            return;
        }
        ay ayVar2 = o;
        if (ayVar2 != null && ayVar2.e == view) {
            ayVar2.a();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    private boolean a(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (Math.abs(x - this.j) > this.g || Math.abs(y - this.k) > this.g) {
            this.j = x;
            this.k = y;
            return true;
        }
        return false;
    }

    private void b() {
        this.e.postDelayed(this.h, ViewConfiguration.getLongPressTimeout());
    }

    private void c() {
        this.e.removeCallbacks(this.h);
    }

    private void d() {
        this.j = ActivityChooserView.a.a;
        this.k = ActivityChooserView.a.a;
    }

    void a() {
        if (o == this) {
            o = null;
            az azVar = this.l;
            if (azVar != null) {
                azVar.a();
                this.l = null;
                d();
                this.e.removeOnAttachStateChangeListener(this);
            } else {
                Log.e(a, "sActiveHandler.mPopup == null");
            }
        }
        if (n == this) {
            a((ay) null);
        }
        this.e.removeCallbacks(this.i);
    }

    void a(boolean z) {
        long longPressTimeout;
        if (android.support.v4.view.ab.af(this.e)) {
            a((ay) null);
            ay ayVar = o;
            if (ayVar != null) {
                ayVar.a();
            }
            o = this;
            this.m = z;
            this.l = new az(this.e.getContext());
            this.l.a(this.e, this.j, this.k, this.m, this.f);
            this.e.addOnAttachStateChangeListener(this);
            if (this.m) {
                longPressTimeout = b;
            } else {
                longPressTimeout = ((android.support.v4.view.ab.P(this.e) & 1) == 1 ? d : c) - ViewConfiguration.getLongPressTimeout();
            }
            this.e.removeCallbacks(this.i);
            this.e.postDelayed(this.i, longPressTimeout);
        }
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.l == null || !this.m) {
            AccessibilityManager accessibilityManager = (AccessibilityManager) this.e.getContext().getSystemService("accessibility");
            if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
                return false;
            }
            int action = motionEvent.getAction();
            if (action != 7) {
                if (action == 10) {
                    d();
                    a();
                }
            } else if (this.e.isEnabled() && this.l == null && a(motionEvent)) {
                a(this);
            }
            return false;
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.j = view.getWidth() / 2;
        this.k = view.getHeight() / 2;
        a(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        a();
    }
}
