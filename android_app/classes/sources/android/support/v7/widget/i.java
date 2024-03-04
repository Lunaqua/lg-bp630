package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.widget.ImageView;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class i {
    private final ImageView a;
    private at b;
    private at c;
    private at d;

    public i(ImageView imageView) {
        this.a = imageView;
    }

    private boolean a(@android.support.annotation.af Drawable drawable) {
        if (this.d == null) {
            this.d = new at();
        }
        at atVar = this.d;
        atVar.a();
        ColorStateList a = android.support.v4.widget.l.a(this.a);
        if (a != null) {
            atVar.d = true;
            atVar.a = a;
        }
        PorterDuff.Mode b = android.support.v4.widget.l.b(this.a);
        if (b != null) {
            atVar.c = true;
            atVar.b = b;
        }
        if (atVar.d || atVar.c) {
            g.a(drawable, atVar, this.a.getDrawableState());
            return true;
        }
        return false;
    }

    private boolean e() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.b != null : i == 21;
    }

    public void a(int i) {
        if (i != 0) {
            Drawable b = android.support.v7.b.a.a.b(this.a.getContext(), i);
            if (b != null) {
                s.b(b);
            }
            this.a.setImageDrawable(b);
        } else {
            this.a.setImageDrawable(null);
        }
        d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ColorStateList colorStateList) {
        if (this.c == null) {
            this.c = new at();
        }
        at atVar = this.c;
        atVar.a = colorStateList;
        atVar.d = true;
        d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(PorterDuff.Mode mode) {
        if (this.c == null) {
            this.c = new at();
        }
        at atVar = this.c;
        atVar.b = mode;
        atVar.c = true;
        d();
    }

    public void a(AttributeSet attributeSet, int i) {
        int g;
        av a = av.a(this.a.getContext(), attributeSet, a.l.AppCompatImageView, i, 0);
        try {
            Drawable drawable = this.a.getDrawable();
            if (drawable == null && (g = a.g(a.l.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = android.support.v7.b.a.a.b(this.a.getContext(), g)) != null) {
                this.a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                s.b(drawable);
            }
            if (a.j(a.l.AppCompatImageView_tint)) {
                android.support.v4.widget.l.a(this.a, a.g(a.l.AppCompatImageView_tint));
            }
            if (a.j(a.l.AppCompatImageView_tintMode)) {
                android.support.v4.widget.l.a(this.a, s.a(a.a(a.l.AppCompatImageView_tintMode, -1), null));
            }
        } finally {
            a.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        return Build.VERSION.SDK_INT < 21 || !(this.a.getBackground() instanceof RippleDrawable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList b() {
        at atVar = this.c;
        if (atVar != null) {
            return atVar.a;
        }
        return null;
    }

    void b(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.b == null) {
                this.b = new at();
            }
            at atVar = this.b;
            atVar.a = colorStateList;
            atVar.d = true;
        } else {
            this.b = null;
        }
        d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PorterDuff.Mode c() {
        at atVar = this.c;
        if (atVar != null) {
            return atVar.b;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        Drawable drawable = this.a.getDrawable();
        if (drawable != null) {
            s.b(drawable);
        }
        if (drawable != null) {
            if (e() && a(drawable)) {
                return;
            }
            at atVar = this.c;
            if (atVar != null) {
                g.a(drawable, atVar, this.a.getDrawableState());
                return;
            }
            at atVar2 = this.b;
            if (atVar2 != null) {
                g.a(drawable, atVar2, this.a.getDrawableState());
            }
        }
    }
}
