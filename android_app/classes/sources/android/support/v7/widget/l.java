package android.support.v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.an;
import android.support.v4.content.b.g;
import android.support.v7.a.a;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import java.lang.ref.WeakReference;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class l {
    private static final int a = 1;
    private static final int b = 2;
    private static final int c = 3;
    private final TextView d;
    private at e;
    private at f;
    private at g;
    private at h;
    private at i;
    private at j;
    @android.support.annotation.af
    private final m k;
    private int l = 0;
    private Typeface m;
    private boolean n;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(TextView textView) {
        this.d = textView;
        this.k = new m(this.d);
    }

    private static at a(Context context, g gVar, int i) {
        ColorStateList b2 = gVar.b(context, i);
        if (b2 != null) {
            at atVar = new at();
            atVar.d = true;
            atVar.a = b2;
            return atVar;
        }
        return null;
    }

    private void a(Context context, av avVar) {
        String e;
        Typeface typeface;
        this.l = avVar.a(a.l.TextAppearance_android_textStyle, this.l);
        if (avVar.j(a.l.TextAppearance_android_fontFamily) || avVar.j(a.l.TextAppearance_fontFamily)) {
            this.m = null;
            int i = avVar.j(a.l.TextAppearance_fontFamily) ? a.l.TextAppearance_fontFamily : a.l.TextAppearance_android_fontFamily;
            if (!context.isRestricted()) {
                final WeakReference weakReference = new WeakReference(this.d);
                try {
                    this.m = avVar.a(i, this.l, new g.a() { // from class: android.support.v7.widget.l.1
                        @Override // android.support.v4.content.b.g.a
                        public void a(int i2) {
                        }

                        @Override // android.support.v4.content.b.g.a
                        public void a(@android.support.annotation.af Typeface typeface2) {
                            l.this.a(weakReference, typeface2);
                        }
                    });
                    this.n = this.m == null;
                } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
                }
            }
            if (this.m != null || (e = avVar.e(i)) == null) {
                return;
            }
            this.m = Typeface.create(e, this.l);
        } else if (avVar.j(a.l.TextAppearance_android_typeface)) {
            this.n = false;
            int a2 = avVar.a(a.l.TextAppearance_android_typeface, 1);
            if (a2 == 1) {
                typeface = Typeface.SANS_SERIF;
            } else if (a2 == 2) {
                typeface = Typeface.SERIF;
            } else if (a2 != 3) {
                return;
            } else {
                typeface = Typeface.MONOSPACE;
            }
            this.m = typeface;
        }
    }

    private void a(Drawable drawable, at atVar) {
        if (drawable == null || atVar == null) {
            return;
        }
        g.a(drawable, atVar, this.d.getDrawableState());
    }

    private void b(int i, float f) {
        this.k.a(i, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        if (this.e != null || this.f != null || this.g != null || this.h != null) {
            Drawable[] compoundDrawables = this.d.getCompoundDrawables();
            a(compoundDrawables[0], this.e);
            a(compoundDrawables[1], this.f);
            a(compoundDrawables[2], this.g);
            a(compoundDrawables[3], this.h);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (this.i == null && this.j == null) {
                return;
            }
            Drawable[] compoundDrawablesRelative = this.d.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.i);
            a(compoundDrawablesRelative[2], this.j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i) {
        this.k.a(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void a(int i, float f) {
        if (android.support.v4.widget.b.a || c()) {
            return;
        }
        b(i, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, int i2, int i3, int i4) {
        this.k.a(i, i2, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context, int i) {
        ColorStateList g;
        av a2 = av.a(context, i, a.l.TextAppearance);
        if (a2.j(a.l.TextAppearance_textAllCaps)) {
            a(a2.a(a.l.TextAppearance_textAllCaps, false));
        }
        if (Build.VERSION.SDK_INT < 23 && a2.j(a.l.TextAppearance_android_textColor) && (g = a2.g(a.l.TextAppearance_android_textColor)) != null) {
            this.d.setTextColor(g);
        }
        if (a2.j(a.l.TextAppearance_android_textSize) && a2.e(a.l.TextAppearance_android_textSize, -1) == 0) {
            this.d.setTextSize(0, 0.0f);
        }
        a(context, a2);
        a2.e();
        Typeface typeface = this.m;
        if (typeface != null) {
            this.d.setTypeface(typeface, this.l);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"NewApi"})
    public void a(AttributeSet attributeSet, int i) {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        boolean z;
        boolean z2;
        Context context = this.d.getContext();
        g a2 = g.a();
        av a3 = av.a(context, attributeSet, a.l.AppCompatTextHelper, i, 0);
        int g = a3.g(a.l.AppCompatTextHelper_android_textAppearance, -1);
        if (a3.j(a.l.AppCompatTextHelper_android_drawableLeft)) {
            this.e = a(context, a2, a3.g(a.l.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if (a3.j(a.l.AppCompatTextHelper_android_drawableTop)) {
            this.f = a(context, a2, a3.g(a.l.AppCompatTextHelper_android_drawableTop, 0));
        }
        if (a3.j(a.l.AppCompatTextHelper_android_drawableRight)) {
            this.g = a(context, a2, a3.g(a.l.AppCompatTextHelper_android_drawableRight, 0));
        }
        if (a3.j(a.l.AppCompatTextHelper_android_drawableBottom)) {
            this.h = a(context, a2, a3.g(a.l.AppCompatTextHelper_android_drawableBottom, 0));
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (a3.j(a.l.AppCompatTextHelper_android_drawableStart)) {
                this.i = a(context, a2, a3.g(a.l.AppCompatTextHelper_android_drawableStart, 0));
            }
            if (a3.j(a.l.AppCompatTextHelper_android_drawableEnd)) {
                this.j = a(context, a2, a3.g(a.l.AppCompatTextHelper_android_drawableEnd, 0));
            }
        }
        a3.e();
        boolean z3 = this.d.getTransformationMethod() instanceof PasswordTransformationMethod;
        boolean z4 = true;
        if (g != -1) {
            av a4 = av.a(context, g, a.l.TextAppearance);
            if (z3 || !a4.j(a.l.TextAppearance_textAllCaps)) {
                z = false;
                z2 = false;
            } else {
                z2 = a4.a(a.l.TextAppearance_textAllCaps, false);
                z = true;
            }
            a(context, a4);
            if (Build.VERSION.SDK_INT < 23) {
                ColorStateList g2 = a4.j(a.l.TextAppearance_android_textColor) ? a4.g(a.l.TextAppearance_android_textColor) : null;
                colorStateList2 = a4.j(a.l.TextAppearance_android_textColorHint) ? a4.g(a.l.TextAppearance_android_textColorHint) : null;
                ColorStateList colorStateList3 = g2;
                colorStateList = a4.j(a.l.TextAppearance_android_textColorLink) ? a4.g(a.l.TextAppearance_android_textColorLink) : null;
                r10 = colorStateList3;
            } else {
                colorStateList = null;
                colorStateList2 = null;
            }
            a4.e();
        } else {
            colorStateList = null;
            colorStateList2 = null;
            z = false;
            z2 = false;
        }
        av a5 = av.a(context, attributeSet, a.l.TextAppearance, i, 0);
        if (z3 || !a5.j(a.l.TextAppearance_textAllCaps)) {
            z4 = z;
        } else {
            z2 = a5.a(a.l.TextAppearance_textAllCaps, false);
        }
        if (Build.VERSION.SDK_INT < 23) {
            if (a5.j(a.l.TextAppearance_android_textColor)) {
                r10 = a5.g(a.l.TextAppearance_android_textColor);
            }
            if (a5.j(a.l.TextAppearance_android_textColorHint)) {
                colorStateList2 = a5.g(a.l.TextAppearance_android_textColorHint);
            }
            if (a5.j(a.l.TextAppearance_android_textColorLink)) {
                colorStateList = a5.g(a.l.TextAppearance_android_textColorLink);
            }
        }
        if (Build.VERSION.SDK_INT >= 28 && a5.j(a.l.TextAppearance_android_textSize) && a5.e(a.l.TextAppearance_android_textSize, -1) == 0) {
            this.d.setTextSize(0, 0.0f);
        }
        a(context, a5);
        a5.e();
        if (r10 != null) {
            this.d.setTextColor(r10);
        }
        if (colorStateList2 != null) {
            this.d.setHintTextColor(colorStateList2);
        }
        if (colorStateList != null) {
            this.d.setLinkTextColor(colorStateList);
        }
        if (!z3 && z4) {
            a(z2);
        }
        Typeface typeface = this.m;
        if (typeface != null) {
            this.d.setTypeface(typeface, this.l);
        }
        this.k.a(attributeSet, i);
        if (android.support.v4.widget.b.a && this.k.a() != 0) {
            int[] e = this.k.e();
            if (e.length > 0) {
                if (this.d.getAutoSizeStepGranularity() != -1.0f) {
                    this.d.setAutoSizeTextTypeUniformWithConfiguration(this.k.c(), this.k.d(), this.k.b(), 0);
                } else {
                    this.d.setAutoSizeTextTypeUniformWithPresetSizes(e, 0);
                }
            }
        }
        av a6 = av.a(context, attributeSet, a.l.AppCompatTextView);
        int e2 = a6.e(a.l.AppCompatTextView_firstBaselineToTopHeight, -1);
        int e3 = a6.e(a.l.AppCompatTextView_lastBaselineToBottomHeight, -1);
        int e4 = a6.e(a.l.AppCompatTextView_lineHeight, -1);
        a6.e();
        if (e2 != -1) {
            android.support.v4.widget.u.c(this.d, e2);
        }
        if (e3 != -1) {
            android.support.v4.widget.u.d(this.d, e3);
        }
        if (e4 != -1) {
            android.support.v4.widget.u.e(this.d, e4);
        }
    }

    void a(WeakReference<TextView> weakReference, Typeface typeface) {
        if (this.n) {
            this.m = typeface;
            TextView textView = weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, this.l);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        this.d.setAllCaps(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void a(boolean z, int i, int i2, int i3, int i4) {
        if (android.support.v4.widget.b.a) {
            return;
        }
        b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(@android.support.annotation.af int[] iArr, int i) {
        this.k.a(iArr, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void b() {
        this.k.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public boolean c() {
        return this.k.g();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d() {
        return this.k.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e() {
        return this.k.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f() {
        return this.k.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g() {
        return this.k.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] h() {
        return this.k.e();
    }
}
