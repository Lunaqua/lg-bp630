package android.support.v7.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.a.a;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class SwitchCompat extends CompoundButton {
    private static final int c = 250;
    private static final int d = 0;
    private static final int e = 1;
    private static final int f = 2;
    private static final String g = "android.widget.Switch";
    private static final int h = 1;
    private static final int i = 2;
    private static final int j = 3;
    private CharSequence A;
    private boolean B;
    private int C;
    private int D;
    private float E;
    private float F;
    private VelocityTracker G;
    private int H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private final TextPaint P;
    private ColorStateList Q;
    private Layout R;
    private Layout S;
    private TransformationMethod T;
    private final Rect U;
    float a;
    ObjectAnimator b;
    private Drawable l;
    private ColorStateList m;
    private PorterDuff.Mode n;
    private boolean o;
    private boolean p;
    private Drawable q;
    private ColorStateList r;
    private PorterDuff.Mode s;
    private boolean t;
    private boolean u;
    private int v;
    private int w;
    private int x;
    private boolean y;
    private CharSequence z;
    private static final Property<SwitchCompat, Float> k = new Property<SwitchCompat, Float>(Float.class, "thumbPos") { // from class: android.support.v7.widget.SwitchCompat.1
        @Override // android.util.Property
        /* renamed from: a */
        public Float get(SwitchCompat switchCompat) {
            return Float.valueOf(switchCompat.a);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public void set(SwitchCompat switchCompat, Float f2) {
            switchCompat.setThumbPosition(f2.floatValue());
        }
    };
    private static final int[] V = {16842912};

    public SwitchCompat(Context context) {
        this(context, null);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.b.switchStyle);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.m = null;
        this.n = null;
        this.o = false;
        this.p = false;
        this.r = null;
        this.s = null;
        this.t = false;
        this.u = false;
        this.G = VelocityTracker.obtain();
        this.U = new Rect();
        this.P = new TextPaint(1);
        Resources resources = getResources();
        this.P.density = resources.getDisplayMetrics().density;
        av a = av.a(context, attributeSet, a.l.SwitchCompat, i2, 0);
        this.l = a.a(a.l.SwitchCompat_android_thumb);
        Drawable drawable = this.l;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        this.q = a.a(a.l.SwitchCompat_track);
        Drawable drawable2 = this.q;
        if (drawable2 != null) {
            drawable2.setCallback(this);
        }
        this.z = a.d(a.l.SwitchCompat_android_textOn);
        this.A = a.d(a.l.SwitchCompat_android_textOff);
        this.B = a.a(a.l.SwitchCompat_showText, true);
        this.v = a.e(a.l.SwitchCompat_thumbTextPadding, 0);
        this.w = a.e(a.l.SwitchCompat_switchMinWidth, 0);
        this.x = a.e(a.l.SwitchCompat_switchPadding, 0);
        this.y = a.a(a.l.SwitchCompat_splitTrack, false);
        ColorStateList g2 = a.g(a.l.SwitchCompat_thumbTint);
        if (g2 != null) {
            this.m = g2;
            this.o = true;
        }
        PorterDuff.Mode a2 = s.a(a.a(a.l.SwitchCompat_thumbTintMode, -1), null);
        if (this.n != a2) {
            this.n = a2;
            this.p = true;
        }
        if (this.o || this.p) {
            b();
        }
        ColorStateList g3 = a.g(a.l.SwitchCompat_trackTint);
        if (g3 != null) {
            this.r = g3;
            this.t = true;
        }
        PorterDuff.Mode a3 = s.a(a.a(a.l.SwitchCompat_trackTintMode, -1), null);
        if (this.s != a3) {
            this.s = a3;
            this.u = true;
        }
        if (this.t || this.u) {
            a();
        }
        int g4 = a.g(a.l.SwitchCompat_switchTextAppearance, 0);
        if (g4 != 0) {
            a(context, g4);
        }
        a.e();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.D = viewConfiguration.getScaledTouchSlop();
        this.H = viewConfiguration.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }

    private static float a(float f2, float f3, float f4) {
        return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
    }

    private Layout a(CharSequence charSequence) {
        TransformationMethod transformationMethod = this.T;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, this);
        }
        CharSequence charSequence2 = charSequence;
        TextPaint textPaint = this.P;
        return new StaticLayout(charSequence2, textPaint, charSequence2 != null ? (int) Math.ceil(Layout.getDesiredWidth(charSequence2, textPaint)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    private void a() {
        if (this.q != null) {
            if (this.t || this.u) {
                this.q = this.q.mutate();
                if (this.t) {
                    android.support.v4.graphics.drawable.a.a(this.q, this.r);
                }
                if (this.u) {
                    android.support.v4.graphics.drawable.a.a(this.q, this.s);
                }
                if (this.q.isStateful()) {
                    this.q.setState(getDrawableState());
                }
            }
        }
    }

    private void a(int i2, int i3) {
        a(i2 != 1 ? i2 != 2 ? i2 != 3 ? null : Typeface.MONOSPACE : Typeface.SERIF : Typeface.SANS_SERIF, i3);
    }

    private void a(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        super.onTouchEvent(obtain);
        obtain.recycle();
    }

    private void a(boolean z) {
        this.b = ObjectAnimator.ofFloat(this, k, z ? 1.0f : 0.0f);
        this.b.setDuration(250L);
        if (Build.VERSION.SDK_INT >= 18) {
            this.b.setAutoCancel(true);
        }
        this.b.start();
    }

    private boolean a(float f2, float f3) {
        if (this.l == null) {
            return false;
        }
        int thumbOffset = getThumbOffset();
        this.l.getPadding(this.U);
        int i2 = this.M;
        int i3 = this.D;
        int i4 = i2 - i3;
        int i5 = (this.L + thumbOffset) - i3;
        int i6 = this.K + i5 + this.U.left + this.U.right;
        int i7 = this.D;
        return f2 > ((float) i5) && f2 < ((float) (i6 + i7)) && f3 > ((float) i4) && f3 < ((float) (this.O + i7));
    }

    private void b() {
        if (this.l != null) {
            if (this.o || this.p) {
                this.l = this.l.mutate();
                if (this.o) {
                    android.support.v4.graphics.drawable.a.a(this.l, this.m);
                }
                if (this.p) {
                    android.support.v4.graphics.drawable.a.a(this.l, this.n);
                }
                if (this.l.isStateful()) {
                    this.l.setState(getDrawableState());
                }
            }
        }
    }

    private void b(MotionEvent motionEvent) {
        boolean z;
        this.C = 0;
        boolean z2 = true;
        boolean z3 = motionEvent.getAction() == 1 && isEnabled();
        boolean isChecked = isChecked();
        if (z3) {
            this.G.computeCurrentVelocity(1000);
            float xVelocity = this.G.getXVelocity();
            if (Math.abs(xVelocity) > this.H) {
                if (!bd.a(this) ? xVelocity <= 0.0f : xVelocity >= 0.0f) {
                    z2 = false;
                }
                z = z2;
            } else {
                z = getTargetCheckedState();
            }
        } else {
            z = isChecked;
        }
        if (z != isChecked) {
            playSoundEffect(0);
        }
        setChecked(z);
        a(motionEvent);
    }

    private void c() {
        ObjectAnimator objectAnimator = this.b;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    private boolean getTargetCheckedState() {
        return this.a > 0.5f;
    }

    private int getThumbOffset() {
        return (int) (((bd.a(this) ? 1.0f - this.a : this.a) * getThumbScrollRange()) + 0.5f);
    }

    private int getThumbScrollRange() {
        Drawable drawable = this.q;
        if (drawable != null) {
            Rect rect = this.U;
            drawable.getPadding(rect);
            Drawable drawable2 = this.l;
            Rect a = drawable2 != null ? s.a(drawable2) : s.a;
            return ((((this.I - this.K) - rect.left) - rect.right) - a.left) - a.right;
        }
        return 0;
    }

    public void a(Context context, int i2) {
        av a = av.a(context, i2, a.l.TextAppearance);
        ColorStateList g2 = a.g(a.l.TextAppearance_android_textColor);
        if (g2 == null) {
            g2 = getTextColors();
        }
        this.Q = g2;
        int e2 = a.e(a.l.TextAppearance_android_textSize, 0);
        if (e2 != 0) {
            float f2 = e2;
            if (f2 != this.P.getTextSize()) {
                this.P.setTextSize(f2);
                requestLayout();
            }
        }
        a(a.a(a.l.TextAppearance_android_typeface, -1), a.a(a.l.TextAppearance_android_textStyle, -1));
        this.T = a.a(a.l.TextAppearance_textAllCaps, false) ? new android.support.v7.e.a(getContext()) : null;
        a.e();
    }

    public void a(Typeface typeface, int i2) {
        if (i2 <= 0) {
            this.P.setFakeBoldText(false);
            this.P.setTextSkewX(0.0f);
            setSwitchTypeface(typeface);
            return;
        }
        Typeface defaultFromStyle = typeface == null ? Typeface.defaultFromStyle(i2) : Typeface.create(typeface, i2);
        setSwitchTypeface(defaultFromStyle);
        int style = ((defaultFromStyle != null ? defaultFromStyle.getStyle() : 0) ^ (-1)) & i2;
        this.P.setFakeBoldText((style & 1) != 0);
        this.P.setTextSkewX((style & 2) != 0 ? -0.25f : 0.0f);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i2;
        int i3;
        Rect rect = this.U;
        int i4 = this.L;
        int i5 = this.M;
        int i6 = this.N;
        int i7 = this.O;
        int thumbOffset = getThumbOffset() + i4;
        Drawable drawable = this.l;
        Rect a = drawable != null ? s.a(drawable) : s.a;
        Drawable drawable2 = this.q;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            thumbOffset += rect.left;
            if (a != null) {
                if (a.left > rect.left) {
                    i4 += a.left - rect.left;
                }
                i2 = a.top > rect.top ? (a.top - rect.top) + i5 : i5;
                if (a.right > rect.right) {
                    i6 -= a.right - rect.right;
                }
                if (a.bottom > rect.bottom) {
                    i3 = i7 - (a.bottom - rect.bottom);
                    this.q.setBounds(i4, i2, i6, i3);
                }
            } else {
                i2 = i5;
            }
            i3 = i7;
            this.q.setBounds(i4, i2, i6, i3);
        }
        Drawable drawable3 = this.l;
        if (drawable3 != null) {
            drawable3.getPadding(rect);
            int i8 = thumbOffset - rect.left;
            int i9 = thumbOffset + this.K + rect.right;
            this.l.setBounds(i8, i5, i9, i7);
            Drawable background = getBackground();
            if (background != null) {
                android.support.v4.graphics.drawable.a.a(background, i8, i5, i9, i7);
            }
        }
        super.draw(canvas);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableHotspotChanged(float f2, float f3) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.drawableHotspotChanged(f2, f3);
        }
        Drawable drawable = this.l;
        if (drawable != null) {
            android.support.v4.graphics.drawable.a.a(drawable, f2, f3);
        }
        Drawable drawable2 = this.q;
        if (drawable2 != null) {
            android.support.v4.graphics.drawable.a.a(drawable2, f2, f3);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.l;
        boolean z = false;
        if (drawable != null && drawable.isStateful()) {
            z = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.q;
        if (drawable2 != null && drawable2.isStateful()) {
            z |= drawable2.setState(drawableState);
        }
        if (z) {
            invalidate();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        if (bd.a(this)) {
            int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.I;
            return !TextUtils.isEmpty(getText()) ? compoundPaddingLeft + this.x : compoundPaddingLeft;
        }
        return super.getCompoundPaddingLeft();
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingRight() {
        if (bd.a(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.I;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingRight + this.x : compoundPaddingRight;
    }

    public boolean getShowText() {
        return this.B;
    }

    public boolean getSplitTrack() {
        return this.y;
    }

    public int getSwitchMinWidth() {
        return this.w;
    }

    public int getSwitchPadding() {
        return this.x;
    }

    public CharSequence getTextOff() {
        return this.A;
    }

    public CharSequence getTextOn() {
        return this.z;
    }

    public Drawable getThumbDrawable() {
        return this.l;
    }

    public int getThumbTextPadding() {
        return this.v;
    }

    @android.support.annotation.ag
    public ColorStateList getThumbTintList() {
        return this.m;
    }

    @android.support.annotation.ag
    public PorterDuff.Mode getThumbTintMode() {
        return this.n;
    }

    public Drawable getTrackDrawable() {
        return this.q;
    }

    @android.support.annotation.ag
    public ColorStateList getTrackTintList() {
        return this.r;
    }

    @android.support.annotation.ag
    public PorterDuff.Mode getTrackTintMode() {
        return this.s;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.l;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.q;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        ObjectAnimator objectAnimator = this.b;
        if (objectAnimator == null || !objectAnimator.isStarted()) {
            return;
        }
        this.b.end();
        this.b = null;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, V);
        }
        return onCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        int width;
        super.onDraw(canvas);
        Rect rect = this.U;
        Drawable drawable = this.q;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i2 = this.M;
        int i3 = this.O;
        int i4 = i2 + rect.top;
        int i5 = i3 - rect.bottom;
        Drawable drawable2 = this.l;
        if (drawable != null) {
            if (!this.y || drawable2 == null) {
                drawable.draw(canvas);
            } else {
                Rect a = s.a(drawable2);
                drawable2.copyBounds(rect);
                rect.left += a.left;
                rect.right -= a.right;
                int save = canvas.save();
                canvas.clipRect(rect, Region.Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(save);
            }
        }
        int save2 = canvas.save();
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        Layout layout = getTargetCheckedState() ? this.R : this.S;
        if (layout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.Q;
            if (colorStateList != null) {
                this.P.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            this.P.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                width = bounds.left + bounds.right;
            } else {
                width = getWidth();
            }
            canvas.translate((width / 2) - (layout.getWidth() / 2), ((i4 + i5) / 2) - (layout.getHeight() / 2));
            layout.draw(canvas);
        }
        canvas.restoreToCount(save2);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(g);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(g);
        CharSequence charSequence = isChecked() ? this.z : this.A;
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        CharSequence text = accessibilityNodeInfo.getText();
        if (TextUtils.isEmpty(text)) {
            accessibilityNodeInfo.setText(charSequence);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(text);
        sb.append(' ');
        sb.append(charSequence);
        accessibilityNodeInfo.setText(sb);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        int width;
        int i7;
        int i8;
        int paddingTop;
        int i9;
        super.onLayout(z, i2, i3, i4, i5);
        int i10 = 0;
        if (this.l != null) {
            Rect rect = this.U;
            Drawable drawable = this.q;
            if (drawable != null) {
                drawable.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect a = s.a(this.l);
            i6 = Math.max(0, a.left - rect.left);
            i10 = Math.max(0, a.right - rect.right);
        } else {
            i6 = 0;
        }
        if (bd.a(this)) {
            i7 = getPaddingLeft() + i6;
            width = ((this.I + i7) - i6) - i10;
        } else {
            width = (getWidth() - getPaddingRight()) - i10;
            i7 = (width - this.I) + i6 + i10;
        }
        int gravity = getGravity() & 112;
        if (gravity == 16) {
            i8 = this.J;
            paddingTop = (((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2) - (i8 / 2);
        } else if (gravity == 80) {
            i9 = getHeight() - getPaddingBottom();
            paddingTop = i9 - this.J;
            this.L = i7;
            this.M = paddingTop;
            this.O = i9;
            this.N = width;
        } else {
            paddingTop = getPaddingTop();
            i8 = this.J;
        }
        i9 = i8 + paddingTop;
        this.L = i7;
        this.M = paddingTop;
        this.O = i9;
        this.N = width;
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        if (this.B) {
            if (this.R == null) {
                this.R = a(this.z);
            }
            if (this.S == null) {
                this.S = a(this.A);
            }
        }
        Rect rect = this.U;
        Drawable drawable = this.l;
        int i6 = 0;
        if (drawable != null) {
            drawable.getPadding(rect);
            i4 = (this.l.getIntrinsicWidth() - rect.left) - rect.right;
            i5 = this.l.getIntrinsicHeight();
        } else {
            i4 = 0;
            i5 = 0;
        }
        this.K = Math.max(this.B ? Math.max(this.R.getWidth(), this.S.getWidth()) + (this.v * 2) : 0, i4);
        Drawable drawable2 = this.q;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            i6 = this.q.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        int i7 = rect.left;
        int i8 = rect.right;
        Drawable drawable3 = this.l;
        if (drawable3 != null) {
            Rect a = s.a(drawable3);
            i7 = Math.max(i7, a.left);
            i8 = Math.max(i8, a.right);
        }
        int max = Math.max(this.w, (this.K * 2) + i7 + i8);
        int max2 = Math.max(i6, i5);
        this.I = max;
        this.J = max2;
        super.onMeasure(i2, i3);
        if (getMeasuredHeight() < max2) {
            setMeasuredDimension(getMeasuredWidthAndState(), max2);
        }
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence charSequence = isChecked() ? this.z : this.A;
        if (charSequence != null) {
            accessibilityEvent.getText().add(charSequence);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0012, code lost:
        if (r0 != 3) goto L8;
     */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            android.view.VelocityTracker r0 = r6.G
            r0.addMovement(r7)
            int r0 = r7.getActionMasked()
            r1 = 1
            if (r0 == 0) goto La1
            r2 = 2
            if (r0 == r1) goto L8d
            if (r0 == r2) goto L16
            r3 = 3
            if (r0 == r3) goto L8d
            goto Lbb
        L16:
            int r0 = r6.C
            if (r0 == 0) goto Lbb
            if (r0 == r1) goto L59
            if (r0 == r2) goto L20
            goto Lbb
        L20:
            float r7 = r7.getX()
            int r0 = r6.getThumbScrollRange()
            float r2 = r6.E
            float r2 = r7 - r2
            r3 = 1065353216(0x3f800000, float:1.0)
            r4 = 0
            if (r0 == 0) goto L34
            float r0 = (float) r0
            float r2 = r2 / r0
            goto L3f
        L34:
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L3b
            r2 = 1065353216(0x3f800000, float:1.0)
            goto L3f
        L3b:
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
        L3f:
            boolean r0 = android.support.v7.widget.bd.a(r6)
            if (r0 == 0) goto L46
            float r2 = -r2
        L46:
            float r0 = r6.a
            float r0 = r0 + r2
            float r0 = a(r0, r4, r3)
            float r2 = r6.a
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 == 0) goto L58
            r6.E = r7
            r6.setThumbPosition(r0)
        L58:
            return r1
        L59:
            float r0 = r7.getX()
            float r3 = r7.getY()
            float r4 = r6.E
            float r4 = r0 - r4
            float r4 = java.lang.Math.abs(r4)
            int r5 = r6.D
            float r5 = (float) r5
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 > 0) goto L7f
            float r4 = r6.F
            float r4 = r3 - r4
            float r4 = java.lang.Math.abs(r4)
            int r5 = r6.D
            float r5 = (float) r5
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto Lbb
        L7f:
            r6.C = r2
            android.view.ViewParent r7 = r6.getParent()
            r7.requestDisallowInterceptTouchEvent(r1)
            r6.E = r0
            r6.F = r3
            return r1
        L8d:
            int r0 = r6.C
            if (r0 != r2) goto L98
            r6.b(r7)
            super.onTouchEvent(r7)
            return r1
        L98:
            r0 = 0
            r6.C = r0
            android.view.VelocityTracker r0 = r6.G
            r0.clear()
            goto Lbb
        La1:
            float r0 = r7.getX()
            float r2 = r7.getY()
            boolean r3 = r6.isEnabled()
            if (r3 == 0) goto Lbb
            boolean r3 = r6.a(r0, r2)
            if (r3 == 0) goto Lbb
            r6.C = r1
            r6.E = r0
            r6.F = r2
        Lbb:
            boolean r7 = super.onTouchEvent(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.SwitchCompat.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        super.setChecked(z);
        boolean isChecked = isChecked();
        if (getWindowToken() != null && android.support.v4.view.ab.ab(this)) {
            a(isChecked);
            return;
        }
        c();
        setThumbPosition(isChecked ? 1.0f : 0.0f);
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(android.support.v4.widget.u.b(this, callback));
    }

    public void setShowText(boolean z) {
        if (this.B != z) {
            this.B = z;
            requestLayout();
        }
    }

    public void setSplitTrack(boolean z) {
        this.y = z;
        invalidate();
    }

    public void setSwitchMinWidth(int i2) {
        this.w = i2;
        requestLayout();
    }

    public void setSwitchPadding(int i2) {
        this.x = i2;
        requestLayout();
    }

    public void setSwitchTypeface(Typeface typeface) {
        if ((this.P.getTypeface() == null || this.P.getTypeface().equals(typeface)) && (this.P.getTypeface() != null || typeface == null)) {
            return;
        }
        this.P.setTypeface(typeface);
        requestLayout();
        invalidate();
    }

    public void setTextOff(CharSequence charSequence) {
        this.A = charSequence;
        requestLayout();
    }

    public void setTextOn(CharSequence charSequence) {
        this.z = charSequence;
        requestLayout();
    }

    public void setThumbDrawable(Drawable drawable) {
        Drawable drawable2 = this.l;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.l = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    void setThumbPosition(float f2) {
        this.a = f2;
        invalidate();
    }

    public void setThumbResource(int i2) {
        setThumbDrawable(android.support.v7.b.a.a.b(getContext(), i2));
    }

    public void setThumbTextPadding(int i2) {
        this.v = i2;
        requestLayout();
    }

    public void setThumbTintList(@android.support.annotation.ag ColorStateList colorStateList) {
        this.m = colorStateList;
        this.o = true;
        b();
    }

    public void setThumbTintMode(@android.support.annotation.ag PorterDuff.Mode mode) {
        this.n = mode;
        this.p = true;
        b();
    }

    public void setTrackDrawable(Drawable drawable) {
        Drawable drawable2 = this.q;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.q = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i2) {
        setTrackDrawable(android.support.v7.b.a.a.b(getContext(), i2));
    }

    public void setTrackTintList(@android.support.annotation.ag ColorStateList colorStateList) {
        this.r = colorStateList;
        this.t = true;
        a();
    }

    public void setTrackTintMode(@android.support.annotation.ag PorterDuff.Mode mode) {
        this.s = mode;
        this.u = true;
        a();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.l || drawable == this.q;
    }
}
