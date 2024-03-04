package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.text.method.SingleLineTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Locale;
@ViewPager.a
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class PagerTitleStrip extends ViewGroup {
    private static final int[] n = {16842804, 16842901, 16842904, 16842927};
    private static final int[] o = {16843660};
    private static final float p = 0.6f;
    private static final int q = 16;
    ViewPager a;
    TextView b;
    TextView c;
    TextView d;
    float e;
    int f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private boolean k;
    private final a l;
    private WeakReference<v> m;
    private int r;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class a extends DataSetObserver implements ViewPager.d, ViewPager.e {
        private int b;

        a() {
        }

        @Override // android.support.v4.view.ViewPager.e
        public void a(int i) {
            if (this.b == 0) {
                PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
                pagerTitleStrip.a(pagerTitleStrip.a.getCurrentItem(), PagerTitleStrip.this.a.getAdapter());
                float f = PagerTitleStrip.this.e >= 0.0f ? PagerTitleStrip.this.e : 0.0f;
                PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
                pagerTitleStrip2.a(pagerTitleStrip2.a.getCurrentItem(), f, true);
            }
        }

        @Override // android.support.v4.view.ViewPager.e
        public void a(int i, float f, int i2) {
            if (f > 0.5f) {
                i++;
            }
            PagerTitleStrip.this.a(i, f, false);
        }

        @Override // android.support.v4.view.ViewPager.d
        public void a(ViewPager viewPager, v vVar, v vVar2) {
            PagerTitleStrip.this.a(vVar, vVar2);
        }

        @Override // android.support.v4.view.ViewPager.e
        public void b(int i) {
            this.b = i;
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
            pagerTitleStrip.a(pagerTitleStrip.a.getCurrentItem(), PagerTitleStrip.this.a.getAdapter());
            float f = PagerTitleStrip.this.e >= 0.0f ? PagerTitleStrip.this.e : 0.0f;
            PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
            pagerTitleStrip2.a(pagerTitleStrip2.a.getCurrentItem(), f, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b extends SingleLineTransformationMethod {
        private Locale a;

        b(Context context) {
            this.a = context.getResources().getConfiguration().locale;
        }

        @Override // android.text.method.ReplacementTransformationMethod, android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence charSequence, View view) {
            CharSequence transformation = super.getTransformation(charSequence, view);
            if (transformation != null) {
                return transformation.toString().toUpperCase(this.a);
            }
            return null;
        }
    }

    public PagerTitleStrip(@android.support.annotation.af Context context) {
        this(context, null);
    }

    public PagerTitleStrip(@android.support.annotation.af Context context, @android.support.annotation.ag AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = -1;
        this.e = -1.0f;
        this.l = new a();
        TextView textView = new TextView(context);
        this.b = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.c = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.d = textView3;
        addView(textView3);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, n);
        boolean z = false;
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            android.support.v4.widget.u.a(this.b, resourceId);
            android.support.v4.widget.u.a(this.c, resourceId);
            android.support.v4.widget.u.a(this.d, resourceId);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            a(0, dimensionPixelSize);
        }
        if (obtainStyledAttributes.hasValue(2)) {
            int color = obtainStyledAttributes.getColor(2, 0);
            this.b.setTextColor(color);
            this.c.setTextColor(color);
            this.d.setTextColor(color);
        }
        this.i = obtainStyledAttributes.getInteger(3, 80);
        obtainStyledAttributes.recycle();
        this.f = this.c.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(p);
        this.b.setEllipsize(TextUtils.TruncateAt.END);
        this.c.setEllipsize(TextUtils.TruncateAt.END);
        this.d.setEllipsize(TextUtils.TruncateAt.END);
        if (resourceId != 0) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, o);
            z = obtainStyledAttributes2.getBoolean(0, false);
            obtainStyledAttributes2.recycle();
        }
        if (z) {
            setSingleLineAllCaps(this.b);
            setSingleLineAllCaps(this.c);
            setSingleLineAllCaps(this.d);
        } else {
            this.b.setSingleLine();
            this.c.setSingleLine();
            this.d.setSingleLine();
        }
        this.h = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }

    private static void setSingleLineAllCaps(TextView textView) {
        textView.setTransformationMethod(new b(textView.getContext()));
    }

    public void a(int i, float f) {
        this.b.setTextSize(i, f);
        this.c.setTextSize(i, f);
        this.d.setTextSize(i, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, float f, boolean z) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (i != this.g) {
            a(i, this.a.getAdapter());
        } else if (!z && f == this.e) {
            return;
        }
        this.k = true;
        int measuredWidth = this.b.getMeasuredWidth();
        int measuredWidth2 = this.c.getMeasuredWidth();
        int measuredWidth3 = this.d.getMeasuredWidth();
        int i6 = measuredWidth2 / 2;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i7 = paddingRight + i6;
        int i8 = (width - (paddingLeft + i6)) - i7;
        float f2 = 0.5f + f;
        if (f2 > 1.0f) {
            f2 -= 1.0f;
        }
        int i9 = ((width - i7) - ((int) (i8 * f2))) - i6;
        int i10 = measuredWidth2 + i9;
        int baseline = this.b.getBaseline();
        int baseline2 = this.c.getBaseline();
        int baseline3 = this.d.getBaseline();
        int max = Math.max(Math.max(baseline, baseline2), baseline3);
        int i11 = max - baseline;
        int i12 = max - baseline2;
        int i13 = max - baseline3;
        int max2 = Math.max(Math.max(this.b.getMeasuredHeight() + i11, this.c.getMeasuredHeight() + i12), this.d.getMeasuredHeight() + i13);
        int i14 = this.i & 112;
        if (i14 == 16) {
            i2 = (((height - paddingTop) - paddingBottom) - max2) / 2;
        } else if (i14 != 80) {
            i3 = i11 + paddingTop;
            i4 = i12 + paddingTop;
            i5 = paddingTop + i13;
            TextView textView = this.c;
            textView.layout(i9, i4, i10, textView.getMeasuredHeight() + i4);
            int min = Math.min(paddingLeft, (i9 - this.h) - measuredWidth);
            TextView textView2 = this.b;
            textView2.layout(min, i3, measuredWidth + min, textView2.getMeasuredHeight() + i3);
            int max3 = Math.max((width - paddingRight) - measuredWidth3, i10 + this.h);
            TextView textView3 = this.d;
            textView3.layout(max3, i5, max3 + measuredWidth3, textView3.getMeasuredHeight() + i5);
            this.e = f;
            this.k = false;
        } else {
            i2 = (height - paddingBottom) - max2;
        }
        i3 = i11 + i2;
        i4 = i12 + i2;
        i5 = i2 + i13;
        TextView textView4 = this.c;
        textView4.layout(i9, i4, i10, textView4.getMeasuredHeight() + i4);
        int min2 = Math.min(paddingLeft, (i9 - this.h) - measuredWidth);
        TextView textView22 = this.b;
        textView22.layout(min2, i3, measuredWidth + min2, textView22.getMeasuredHeight() + i3);
        int max32 = Math.max((width - paddingRight) - measuredWidth3, i10 + this.h);
        TextView textView32 = this.d;
        textView32.layout(max32, i5, max32 + measuredWidth3, textView32.getMeasuredHeight() + i5);
        this.e = f;
        this.k = false;
    }

    void a(int i, v vVar) {
        int count = vVar != null ? vVar.getCount() : 0;
        this.j = true;
        CharSequence charSequence = null;
        this.b.setText((i < 1 || vVar == null) ? null : vVar.getPageTitle(i - 1));
        this.c.setText((vVar == null || i >= count) ? null : vVar.getPageTitle(i));
        int i2 = i + 1;
        if (i2 < count && vVar != null) {
            charSequence = vVar.getPageTitle(i2);
        }
        this.d.setText(charSequence);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((getWidth() - getPaddingLeft()) - getPaddingRight()) * 0.8f)), Integer.MIN_VALUE);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        this.b.measure(makeMeasureSpec, makeMeasureSpec2);
        this.c.measure(makeMeasureSpec, makeMeasureSpec2);
        this.d.measure(makeMeasureSpec, makeMeasureSpec2);
        this.g = i;
        if (!this.k) {
            a(i, this.e, false);
        }
        this.j = false;
    }

    void a(v vVar, v vVar2) {
        if (vVar != null) {
            vVar.unregisterDataSetObserver(this.l);
            this.m = null;
        }
        if (vVar2 != null) {
            vVar2.registerDataSetObserver(this.l);
            this.m = new WeakReference<>(vVar2);
        }
        ViewPager viewPager = this.a;
        if (viewPager != null) {
            this.g = -1;
            this.e = -1.0f;
            a(viewPager.getCurrentItem(), vVar2);
            requestLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMinHeight() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return 0;
    }

    public int getTextSpacing() {
        return this.h;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (!(parent instanceof ViewPager)) {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }
        ViewPager viewPager = (ViewPager) parent;
        v adapter = viewPager.getAdapter();
        viewPager.c(this.l);
        viewPager.a((ViewPager.d) this.l);
        this.a = viewPager;
        WeakReference<v> weakReference = this.m;
        a(weakReference != null ? weakReference.get() : null, adapter);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.a;
        if (viewPager != null) {
            a(viewPager.getAdapter(), (v) null);
            this.a.c((ViewPager.e) null);
            this.a.b((ViewPager.d) this.l);
            this.a = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.a != null) {
            float f = this.e;
            if (f < 0.0f) {
                f = 0.0f;
            }
            a(this.g, f, true);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int max;
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = getChildMeasureSpec(i2, paddingTop, -2);
        int size = View.MeasureSpec.getSize(i);
        int childMeasureSpec2 = getChildMeasureSpec(i, (int) (size * 0.2f), -2);
        this.b.measure(childMeasureSpec2, childMeasureSpec);
        this.c.measure(childMeasureSpec2, childMeasureSpec);
        this.d.measure(childMeasureSpec2, childMeasureSpec);
        if (View.MeasureSpec.getMode(i2) == 1073741824) {
            max = View.MeasureSpec.getSize(i2);
        } else {
            max = Math.max(getMinHeight(), this.c.getMeasuredHeight() + paddingTop);
        }
        setMeasuredDimension(size, View.resolveSizeAndState(max, i2, this.c.getMeasuredState() << 16));
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.j) {
            return;
        }
        super.requestLayout();
    }

    public void setGravity(int i) {
        this.i = i;
        requestLayout();
    }

    public void setNonPrimaryAlpha(@android.support.annotation.q(a = 0.0d, b = 1.0d) float f) {
        this.r = ((int) (f * 255.0f)) & 255;
        int i = (this.r << 24) | (this.f & ab.r);
        this.b.setTextColor(i);
        this.d.setTextColor(i);
    }

    public void setTextColor(@android.support.annotation.k int i) {
        this.f = i;
        this.c.setTextColor(i);
        int i2 = (this.r << 24) | (this.f & ab.r);
        this.b.setTextColor(i2);
        this.d.setTextColor(i2);
    }

    public void setTextSpacing(int i) {
        this.h = i;
        requestLayout();
    }
}
