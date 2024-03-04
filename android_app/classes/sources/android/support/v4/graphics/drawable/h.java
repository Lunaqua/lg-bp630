package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.support.v4.graphics.drawable.g;
import android.util.Log;
import java.lang.reflect.Method;
@ak(a = 21)
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class h extends g {
    private static final String d = "WrappedDrawableApi21";
    private static Method e;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class a extends g.a {
        a(@ag g.a aVar, @ag Resources resources) {
            super(aVar, resources);
        }

        @Override // android.support.v4.graphics.drawable.g.a, android.graphics.drawable.Drawable.ConstantState
        @af
        public Drawable newDrawable(@ag Resources resources) {
            return new h(this, resources);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(Drawable drawable) {
        super(drawable);
        d();
    }

    h(g.a aVar, Resources resources) {
        super(aVar, resources);
        d();
    }

    private void d() {
        if (e == null) {
            try {
                e = Drawable.class.getDeclaredMethod("isProjected", new Class[0]);
            } catch (Exception e2) {
                Log.w(d, "Failed to retrieve Drawable#isProjected() method", e2);
            }
        }
    }

    @Override // android.support.v4.graphics.drawable.g
    @af
    g.a b() {
        return new a(this.b, null);
    }

    @Override // android.support.v4.graphics.drawable.g
    protected boolean c() {
        if (Build.VERSION.SDK_INT == 21) {
            Drawable drawable = this.c;
            return (drawable instanceof GradientDrawable) || (drawable instanceof DrawableContainer) || (drawable instanceof InsetDrawable) || (drawable instanceof RippleDrawable);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    @af
    public Rect getDirtyBounds() {
        return this.c.getDirtyBounds();
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(@af Outline outline) {
        this.c.getOutline(outline);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isProjected() {
        Method method;
        if (this.c != null && (method = e) != null) {
            try {
                return ((Boolean) method.invoke(this.c, new Object[0])).booleanValue();
            } catch (Exception e2) {
                Log.w(d, "Error calling Drawable#isProjected() method", e2);
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f, float f2) {
        this.c.setHotspot(f, f2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        this.c.setHotspotBounds(i, i2, i3, i4);
    }

    @Override // android.support.v4.graphics.drawable.g, android.graphics.drawable.Drawable
    public boolean setState(@af int[] iArr) {
        if (super.setState(iArr)) {
            invalidateSelf();
            return true;
        }
        return false;
    }

    @Override // android.support.v4.graphics.drawable.g, android.graphics.drawable.Drawable, android.support.v4.graphics.drawable.e
    public void setTint(int i) {
        if (c()) {
            super.setTint(i);
        } else {
            this.c.setTint(i);
        }
    }

    @Override // android.support.v4.graphics.drawable.g, android.graphics.drawable.Drawable, android.support.v4.graphics.drawable.e
    public void setTintList(ColorStateList colorStateList) {
        if (c()) {
            super.setTintList(colorStateList);
        } else {
            this.c.setTintList(colorStateList);
        }
    }

    @Override // android.support.v4.graphics.drawable.g, android.graphics.drawable.Drawable, android.support.v4.graphics.drawable.e
    public void setTintMode(PorterDuff.Mode mode) {
        if (c()) {
            super.setTintMode(mode);
        } else {
            this.c.setTintMode(mode);
        }
    }
}
