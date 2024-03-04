package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class g extends Drawable implements Drawable.Callback, e, f {
    static final PorterDuff.Mode a = PorterDuff.Mode.SRC_IN;
    a b;
    Drawable c;
    private int d;
    private PorterDuff.Mode e;
    private boolean f;
    private boolean g;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class a extends Drawable.ConstantState {
        int a;
        Drawable.ConstantState b;
        ColorStateList c;
        PorterDuff.Mode d;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(@ag a aVar, @ag Resources resources) {
            this.c = null;
            this.d = g.a;
            if (aVar != null) {
                this.a = aVar.a;
                this.b = aVar.b;
                this.c = aVar.c;
                this.d = aVar.d;
            }
        }

        boolean a() {
            return this.b != null;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            int i = this.a;
            Drawable.ConstantState constantState = this.b;
            return i | (constantState != null ? constantState.getChangingConfigurations() : 0);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @af
        public Drawable newDrawable() {
            return newDrawable(null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @af
        public abstract Drawable newDrawable(@ag Resources resources);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b extends a {
        b(@ag a aVar, @ag Resources resources) {
            super(aVar, resources);
        }

        @Override // android.support.v4.graphics.drawable.g.a, android.graphics.drawable.Drawable.ConstantState
        @af
        public Drawable newDrawable(@ag Resources resources) {
            return new g(this, resources);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(@ag Drawable drawable) {
        this.b = b();
        a(drawable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(@af a aVar, @ag Resources resources) {
        this.b = aVar;
        a(resources);
    }

    private void a(@ag Resources resources) {
        a aVar = this.b;
        if (aVar == null || aVar.b == null) {
            return;
        }
        a(this.b.b.newDrawable(resources));
    }

    private boolean a(int[] iArr) {
        if (c()) {
            ColorStateList colorStateList = this.b.c;
            PorterDuff.Mode mode = this.b.d;
            if (colorStateList == null || mode == null) {
                this.f = false;
                clearColorFilter();
            } else {
                int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
                if (!this.f || colorForState != this.d || mode != this.e) {
                    setColorFilter(colorForState, mode);
                    this.d = colorForState;
                    this.e = mode;
                    this.f = true;
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // android.support.v4.graphics.drawable.f
    public final Drawable a() {
        return this.c;
    }

    @Override // android.support.v4.graphics.drawable.f
    public final void a(Drawable drawable) {
        Drawable drawable2 = this.c;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.c = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            setVisible(drawable.isVisible(), true);
            setState(drawable.getState());
            setLevel(drawable.getLevel());
            setBounds(drawable.getBounds());
            a aVar = this.b;
            if (aVar != null) {
                aVar.b = drawable.getConstantState();
            }
        }
        invalidateSelf();
    }

    @af
    a b() {
        return new b(this.b, null);
    }

    protected boolean c() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@af Canvas canvas) {
        this.c.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        a aVar = this.b;
        return changingConfigurations | (aVar != null ? aVar.getChangingConfigurations() : 0) | this.c.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    @ag
    public Drawable.ConstantState getConstantState() {
        a aVar = this.b;
        if (aVar == null || !aVar.a()) {
            return null;
        }
        this.b.a = getChangingConfigurations();
        return this.b;
    }

    @Override // android.graphics.drawable.Drawable
    @af
    public Drawable getCurrent() {
        return this.c.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.c.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.c.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.c.getMinimumHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.c.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.c.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(@af Rect rect) {
        return this.c.getPadding(rect);
    }

    @Override // android.graphics.drawable.Drawable
    @af
    public int[] getState() {
        return this.c.getState();
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        return this.c.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(@af Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    @ak(a = 19)
    public boolean isAutoMirrored() {
        return this.c.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        a aVar;
        ColorStateList colorStateList = (!c() || (aVar = this.b) == null) ? null : aVar.c;
        return (colorStateList != null && colorStateList.isStateful()) || this.c.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.c.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    @af
    public Drawable mutate() {
        if (!this.g && super.mutate() == this) {
            this.b = b();
            Drawable drawable = this.c;
            if (drawable != null) {
                drawable.mutate();
            }
            a aVar = this.b;
            if (aVar != null) {
                Drawable drawable2 = this.c;
                aVar.b = drawable2 != null ? drawable2.getConstantState() : null;
            }
            this.g = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.c;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        return this.c.setLevel(i);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(@af Drawable drawable, @af Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.c.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    @ak(a = 19)
    public void setAutoMirrored(boolean z) {
        this.c.setAutoMirrored(z);
    }

    @Override // android.graphics.drawable.Drawable
    public void setChangingConfigurations(int i) {
        this.c.setChangingConfigurations(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.c.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.c.setDither(z);
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.c.setFilterBitmap(z);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(@af int[] iArr) {
        return a(iArr) || this.c.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, android.support.v4.graphics.drawable.e
    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable, android.support.v4.graphics.drawable.e
    public void setTintList(ColorStateList colorStateList) {
        this.b.c = colorStateList;
        a(getState());
    }

    @Override // android.graphics.drawable.Drawable, android.support.v4.graphics.drawable.e
    public void setTintMode(@af PorterDuff.Mode mode) {
        this.b.d = mode;
        a(getState());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.c.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(@af Drawable drawable, @af Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
