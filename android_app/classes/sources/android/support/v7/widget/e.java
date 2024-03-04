package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class e {
    private final View a;
    private at d;
    private at e;
    private at f;
    private int c = -1;
    private final g b = g.a();

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(View view) {
        this.a = view;
    }

    private boolean b(@android.support.annotation.af Drawable drawable) {
        if (this.f == null) {
            this.f = new at();
        }
        at atVar = this.f;
        atVar.a();
        ColorStateList V = android.support.v4.view.ab.V(this.a);
        if (V != null) {
            atVar.d = true;
            atVar.a = V;
        }
        PorterDuff.Mode W = android.support.v4.view.ab.W(this.a);
        if (W != null) {
            atVar.c = true;
            atVar.b = W;
        }
        if (atVar.d || atVar.c) {
            g.a(drawable, atVar, this.a.getDrawableState());
            return true;
        }
        return false;
    }

    private boolean d() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.d != null : i == 21;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList a() {
        at atVar = this.e;
        if (atVar != null) {
            return atVar.a;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i) {
        this.c = i;
        g gVar = this.b;
        b(gVar != null ? gVar.b(this.a.getContext(), i) : null);
        c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new at();
        }
        at atVar = this.e;
        atVar.a = colorStateList;
        atVar.d = true;
        c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(PorterDuff.Mode mode) {
        if (this.e == null) {
            this.e = new at();
        }
        at atVar = this.e;
        atVar.b = mode;
        atVar.c = true;
        c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Drawable drawable) {
        this.c = -1;
        b((ColorStateList) null);
        c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(AttributeSet attributeSet, int i) {
        av a = av.a(this.a.getContext(), attributeSet, a.l.ViewBackgroundHelper, i, 0);
        try {
            if (a.j(a.l.ViewBackgroundHelper_android_background)) {
                this.c = a.g(a.l.ViewBackgroundHelper_android_background, -1);
                ColorStateList b = this.b.b(this.a.getContext(), this.c);
                if (b != null) {
                    b(b);
                }
            }
            if (a.j(a.l.ViewBackgroundHelper_backgroundTint)) {
                android.support.v4.view.ab.a(this.a, a.g(a.l.ViewBackgroundHelper_backgroundTint));
            }
            if (a.j(a.l.ViewBackgroundHelper_backgroundTintMode)) {
                android.support.v4.view.ab.a(this.a, s.a(a.a(a.l.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
        } finally {
            a.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PorterDuff.Mode b() {
        at atVar = this.e;
        if (atVar != null) {
            return atVar.b;
        }
        return null;
    }

    void b(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.d == null) {
                this.d = new at();
            }
            at atVar = this.d;
            atVar.a = colorStateList;
            atVar.d = true;
        } else {
            this.d = null;
        }
        c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        Drawable background = this.a.getBackground();
        if (background != null) {
            if (d() && b(background)) {
                return;
            }
            at atVar = this.e;
            if (atVar != null) {
                g.a(background, atVar, this.a.getDrawableState());
                return;
            }
            at atVar2 = this.d;
            if (atVar2 != null) {
                g.a(background, atVar2, this.a.getDrawableState());
            }
        }
    }
}
