package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class LinearLayoutCompat extends ViewGroup {
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = 0;
    public static final int h = 1;
    public static final int i = 2;
    public static final int j = 4;
    private static final int q = 4;
    private static final int r = 0;
    private static final int s = 1;
    private static final int t = 2;
    private static final int u = 3;
    private boolean a;
    private int b;
    private int c;
    private int d;
    private int k;
    private int l;
    private float m;
    private boolean n;
    private int[] o;
    private int[] p;
    private Drawable v;
    private int w;
    private int x;
    private int y;
    private int z;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public float g;
        public int h;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.h = -1;
            this.g = 0.0f;
        }

        public LayoutParams(int i, int i2, float f) {
            super(i, i2);
            this.h = -1;
            this.g = f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.h = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.l.LinearLayoutCompat_Layout);
            this.g = obtainStyledAttributes.getFloat(a.l.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.h = obtainStyledAttributes.getInt(a.l.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.h = -1;
            this.g = layoutParams.g;
            this.h = layoutParams.h;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.h = -1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.h = -1;
        }
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface a {
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface b {
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = true;
        this.b = -1;
        this.c = 0;
        this.k = 8388659;
        av a2 = av.a(context, attributeSet, a.l.LinearLayoutCompat, i2, 0);
        int a3 = a2.a(a.l.LinearLayoutCompat_android_orientation, -1);
        if (a3 >= 0) {
            setOrientation(a3);
        }
        int a4 = a2.a(a.l.LinearLayoutCompat_android_gravity, -1);
        if (a4 >= 0) {
            setGravity(a4);
        }
        boolean a5 = a2.a(a.l.LinearLayoutCompat_android_baselineAligned, true);
        if (!a5) {
            setBaselineAligned(a5);
        }
        this.m = a2.a(a.l.LinearLayoutCompat_android_weightSum, -1.0f);
        this.b = a2.a(a.l.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.n = a2.a(a.l.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(a2.a(a.l.LinearLayoutCompat_divider));
        this.y = a2.a(a.l.LinearLayoutCompat_showDividers, 0);
        this.z = a2.e(a.l.LinearLayoutCompat_dividerPadding, 0);
        a2.e();
    }

    private void a(View view, int i2, int i3, int i4, int i5) {
        view.layout(i2, i3, i4 + i2, i5 + i3);
    }

    private void c(int i2, int i3) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i4 = 0; i4 < i2; i4++) {
            View b2 = b(i4);
            if (b2.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) b2.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i5 = layoutParams.height;
                    layoutParams.height = b2.getMeasuredHeight();
                    measureChildWithMargins(b2, makeMeasureSpec, 0, i3, 0);
                    layoutParams.height = i5;
                }
            }
        }
    }

    private void d(int i2, int i3) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i4 = 0; i4 < i2; i4++) {
            View b2 = b(i4);
            if (b2.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) b2.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i5 = layoutParams.width;
                    layoutParams.width = b2.getMeasuredWidth();
                    measureChildWithMargins(b2, i3, 0, makeMeasureSpec, 0);
                    layoutParams.width = i5;
                }
            }
        }
    }

    int a(View view) {
        return 0;
    }

    int a(View view, int i2) {
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:132:0x02dd, code lost:
        if (r15 > 0) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x02e8, code lost:
        if (r15 < 0) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x02ea, code lost:
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x02eb, code lost:
        r13.measure(r9, android.view.View.MeasureSpec.makeMeasureSpec(r15, r10));
        r1 = android.view.View.combineMeasuredStates(r1, r13.getMeasuredState() & android.support.v4.view.g.u);
     */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0326  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void a(int r34, int r35) {
        /*
            Method dump skipped, instructions count: 913
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.a(int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void a(int r18, int r19, int r20, int r21) {
        /*
            r17 = this;
            r6 = r17
            int r7 = r17.getPaddingLeft()
            int r0 = r20 - r18
            int r1 = r17.getPaddingRight()
            int r8 = r0 - r1
            int r0 = r0 - r7
            int r1 = r17.getPaddingRight()
            int r9 = r0 - r1
            int r10 = r17.getVirtualChildCount()
            int r0 = r6.k
            r1 = r0 & 112(0x70, float:1.57E-43)
            r2 = 8388615(0x800007, float:1.1754953E-38)
            r11 = r0 & r2
            r0 = 16
            if (r1 == r0) goto L3b
            r0 = 80
            if (r1 == r0) goto L2f
            int r0 = r17.getPaddingTop()
            goto L47
        L2f:
            int r0 = r17.getPaddingTop()
            int r0 = r0 + r21
            int r0 = r0 - r19
            int r1 = r6.l
            int r0 = r0 - r1
            goto L47
        L3b:
            int r0 = r17.getPaddingTop()
            int r1 = r21 - r19
            int r2 = r6.l
            int r1 = r1 - r2
            int r1 = r1 / 2
            int r0 = r0 + r1
        L47:
            r1 = 0
            r12 = 0
        L49:
            if (r12 >= r10) goto Lc7
            android.view.View r13 = r6.b(r12)
            r14 = 1
            if (r13 != 0) goto L59
            int r1 = r6.d(r12)
            int r0 = r0 + r1
            goto Lc4
        L59:
            int r1 = r13.getVisibility()
            r2 = 8
            if (r1 == r2) goto Lc4
            int r4 = r13.getMeasuredWidth()
            int r15 = r13.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r1 = r13.getLayoutParams()
            r5 = r1
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r5 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r5
            int r1 = r5.h
            if (r1 >= 0) goto L75
            r1 = r11
        L75:
            int r2 = android.support.v4.view.ab.m(r17)
            int r1 = android.support.v4.view.f.a(r1, r2)
            r1 = r1 & 7
            if (r1 == r14) goto L8c
            r2 = 5
            if (r1 == r2) goto L89
            int r1 = r5.leftMargin
            int r1 = r1 + r7
        L87:
            r2 = r1
            goto L98
        L89:
            int r1 = r8 - r4
            goto L94
        L8c:
            int r1 = r9 - r4
            int r1 = r1 / 2
            int r1 = r1 + r7
            int r2 = r5.leftMargin
            int r1 = r1 + r2
        L94:
            int r2 = r5.rightMargin
            int r1 = r1 - r2
            goto L87
        L98:
            boolean r1 = r6.c(r12)
            if (r1 == 0) goto La1
            int r1 = r6.x
            int r0 = r0 + r1
        La1:
            int r1 = r5.topMargin
            int r16 = r0 + r1
            int r0 = r6.a(r13)
            int r3 = r16 + r0
            r0 = r17
            r1 = r13
            r14 = r5
            r5 = r15
            r0.a(r1, r2, r3, r4, r5)
            int r0 = r14.bottomMargin
            int r15 = r15 + r0
            int r0 = r6.b(r13)
            int r15 = r15 + r0
            int r16 = r16 + r15
            int r0 = r6.a(r13, r12)
            int r12 = r12 + r0
            r0 = r16
        Lc4:
            r1 = 1
            int r12 = r12 + r1
            goto L49
        Lc7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.a(int, int, int, int):void");
    }

    void a(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View b2 = b(i2);
            if (b2 != null && b2.getVisibility() != 8 && c(i2)) {
                a(canvas, (b2.getTop() - ((LayoutParams) b2.getLayoutParams()).topMargin) - this.x);
            }
        }
        if (c(virtualChildCount)) {
            View b3 = b(virtualChildCount - 1);
            a(canvas, b3 == null ? (getHeight() - getPaddingBottom()) - this.x : b3.getBottom() + ((LayoutParams) b3.getLayoutParams()).bottomMargin);
        }
    }

    void a(Canvas canvas, int i2) {
        this.v.setBounds(getPaddingLeft() + this.z, i2, (getWidth() - getPaddingRight()) - this.z, this.x + i2);
        this.v.draw(canvas);
    }

    void a(View view, int i2, int i3, int i4, int i5, int i6) {
        measureChildWithMargins(view, i3, i4, i5, i6);
    }

    int b(View view) {
        return 0;
    }

    @Override // android.view.ViewGroup
    /* renamed from: b */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: b */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    View b(int i2) {
        return getChildAt(i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:163:0x03ad, code lost:
        if (r8 > 0) goto L176;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x03b8, code lost:
        if (r8 < 0) goto L175;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x03ba, code lost:
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x03bb, code lost:
        r14.measure(android.view.View.MeasureSpec.makeMeasureSpec(r8, r4), r2);
        r9 = android.view.View.combineMeasuredStates(r9, r14.getMeasuredState() & android.support.v4.view.ab.s);
        r2 = r2;
        r4 = r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void b(int r39, int r40) {
        /*
            Method dump skipped, instructions count: 1293
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.b(int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void b(int r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instructions count: 326
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.b(int, int, int, int):void");
    }

    void b(Canvas canvas) {
        int right;
        int left;
        int i2;
        int virtualChildCount = getVirtualChildCount();
        boolean a2 = bd.a(this);
        for (int i3 = 0; i3 < virtualChildCount; i3++) {
            View b2 = b(i3);
            if (b2 != null && b2.getVisibility() != 8 && c(i3)) {
                LayoutParams layoutParams = (LayoutParams) b2.getLayoutParams();
                b(canvas, a2 ? b2.getRight() + layoutParams.rightMargin : (b2.getLeft() - layoutParams.leftMargin) - this.w);
            }
        }
        if (c(virtualChildCount)) {
            View b3 = b(virtualChildCount - 1);
            if (b3 != null) {
                LayoutParams layoutParams2 = (LayoutParams) b3.getLayoutParams();
                if (a2) {
                    left = b3.getLeft();
                    i2 = layoutParams2.leftMargin;
                    right = (left - i2) - this.w;
                } else {
                    right = b3.getRight() + layoutParams2.rightMargin;
                }
            } else if (a2) {
                right = getPaddingLeft();
            } else {
                left = getWidth();
                i2 = getPaddingRight();
                right = (left - i2) - this.w;
            }
            b(canvas, right);
        }
    }

    void b(Canvas canvas, int i2) {
        this.v.setBounds(i2, getPaddingTop() + this.z, this.w + i2, (getHeight() - getPaddingBottom()) - this.z);
        this.v.draw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @android.support.annotation.an(a = {an.a.LIBRARY})
    public boolean c(int i2) {
        if (i2 == 0) {
            return (this.y & 1) != 0;
        } else if (i2 == getChildCount()) {
            return (this.y & 4) != 0;
        } else if ((this.y & 2) != 0) {
            for (int i3 = i2 - 1; i3 >= 0; i3--) {
                if (getChildAt(i3).getVisibility() != 8) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    int d(int i2) {
        return 0;
    }

    @Override // android.view.View
    public int getBaseline() {
        int i2;
        if (this.b < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i3 = this.b;
        if (childCount > i3) {
            View childAt = getChildAt(i3);
            int baseline = childAt.getBaseline();
            if (baseline == -1) {
                if (this.b == 0) {
                    return -1;
                }
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
            int i4 = this.c;
            if (this.d == 1 && (i2 = this.k & 112) != 48) {
                if (i2 == 16) {
                    i4 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.l) / 2;
                } else if (i2 == 80) {
                    i4 = ((getBottom() - getTop()) - getPaddingBottom()) - this.l;
                }
            }
            return i4 + ((LayoutParams) childAt.getLayoutParams()).topMargin + baseline;
        }
        throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
    }

    public int getBaselineAlignedChildIndex() {
        return this.b;
    }

    public Drawable getDividerDrawable() {
        return this.v;
    }

    public int getDividerPadding() {
        return this.z;
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public int getDividerWidth() {
        return this.w;
    }

    public int getGravity() {
        return this.k;
    }

    public int getOrientation() {
        return this.d;
    }

    public int getShowDividers() {
        return this.y;
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: j */
    public LayoutParams generateDefaultLayoutParams() {
        int i2 = this.d;
        if (i2 == 0) {
            return new LayoutParams(-2, -2);
        }
        if (i2 == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    public boolean k() {
        return this.a;
    }

    public boolean l() {
        return this.n;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.v == null) {
            return;
        }
        if (this.d == 1) {
            a(canvas);
        } else {
            b(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (this.d == 1) {
            a(i2, i3, i4, i5);
        } else {
            b(i2, i3, i4, i5);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        if (this.d == 1) {
            a(i2, i3);
        } else {
            b(i2, i3);
        }
    }

    public void setBaselineAligned(boolean z) {
        this.a = z;
    }

    public void setBaselineAlignedChildIndex(int i2) {
        if (i2 >= 0 && i2 < getChildCount()) {
            this.b = i2;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.v) {
            return;
        }
        this.v = drawable;
        if (drawable != null) {
            this.w = drawable.getIntrinsicWidth();
            this.x = drawable.getIntrinsicHeight();
        } else {
            this.w = 0;
            this.x = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i2) {
        this.z = i2;
    }

    public void setGravity(int i2) {
        if (this.k != i2) {
            if ((8388615 & i2) == 0) {
                i2 |= android.support.v4.view.f.b;
            }
            if ((i2 & 112) == 0) {
                i2 |= 48;
            }
            this.k = i2;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i2) {
        int i3 = i2 & android.support.v4.view.f.d;
        int i4 = this.k;
        if ((8388615 & i4) != i3) {
            this.k = i3 | ((-8388616) & i4);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.n = z;
    }

    public void setOrientation(int i2) {
        if (this.d != i2) {
            this.d = i2;
            requestLayout();
        }
    }

    public void setShowDividers(int i2) {
        if (i2 != this.y) {
            requestLayout();
        }
        this.y = i2;
    }

    public void setVerticalGravity(int i2) {
        int i3 = i2 & 112;
        int i4 = this.k;
        if ((i4 & 112) != i3) {
            this.k = i3 | (i4 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f2) {
        this.m = Math.max(0.0f, f2);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
