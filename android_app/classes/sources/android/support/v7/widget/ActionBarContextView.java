package android.support.v7.widget;

import android.content.Context;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ActionBarContextView extends a {
    private static final String g = "ActionBarContextView";
    private CharSequence h;
    private CharSequence i;
    private View j;
    private View k;
    private LinearLayout l;
    private TextView m;
    private TextView n;
    private int o;
    private int p;
    private boolean q;
    private int r;

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.b.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        av a = av.a(context, attributeSet, a.l.ActionMode, i, 0);
        android.support.v4.view.ab.a(this, a.a(a.l.ActionMode_background));
        this.o = a.g(a.l.ActionMode_titleTextStyle, 0);
        this.p = a.g(a.l.ActionMode_subtitleTextStyle, 0);
        this.e = a.f(a.l.ActionMode_height, 0);
        this.r = a.g(a.l.ActionMode_closeItemLayout, a.i.abc_action_mode_close_item_material);
        a.e();
    }

    private void l() {
        if (this.l == null) {
            LayoutInflater.from(getContext()).inflate(a.i.abc_action_bar_title_item, this);
            this.l = (LinearLayout) getChildAt(getChildCount() - 1);
            this.m = (TextView) this.l.findViewById(a.g.action_bar_title);
            this.n = (TextView) this.l.findViewById(a.g.action_bar_subtitle);
            if (this.o != 0) {
                this.m.setTextAppearance(getContext(), this.o);
            }
            if (this.p != 0) {
                this.n.setTextAppearance(getContext(), this.p);
            }
        }
        this.m.setText(this.h);
        this.n.setText(this.i);
        boolean z = !TextUtils.isEmpty(this.h);
        boolean z2 = !TextUtils.isEmpty(this.i);
        int i = 0;
        this.n.setVisibility(z2 ? 0 : 8);
        LinearLayout linearLayout = this.l;
        if (!z && !z2) {
            i = 8;
        }
        linearLayout.setVisibility(i);
        if (this.l.getParent() == null) {
            addView(this.l);
        }
    }

    @Override // android.support.v7.widget.a
    public /* bridge */ /* synthetic */ android.support.v4.view.af a(int i, long j) {
        return super.a(i, j);
    }

    @Override // android.support.v7.widget.a
    public /* bridge */ /* synthetic */ void a(int i) {
        super.a(i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x001f, code lost:
        if (r0.getParent() == null) goto L4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(final android.support.v7.view.b r4) {
        /*
            r3 = this;
            android.view.View r0 = r3.j
            if (r0 != 0) goto L1b
            android.content.Context r0 = r3.getContext()
            android.view.LayoutInflater r0 = android.view.LayoutInflater.from(r0)
            int r1 = r3.r
            r2 = 0
            android.view.View r0 = r0.inflate(r1, r3, r2)
            r3.j = r0
        L15:
            android.view.View r0 = r3.j
            r3.addView(r0)
            goto L22
        L1b:
            android.view.ViewParent r0 = r0.getParent()
            if (r0 != 0) goto L22
            goto L15
        L22:
            android.view.View r0 = r3.j
            int r1 = android.support.v7.a.a.g.action_mode_close_button
            android.view.View r0 = r0.findViewById(r1)
            android.support.v7.widget.ActionBarContextView$1 r1 = new android.support.v7.widget.ActionBarContextView$1
            r1.<init>()
            r0.setOnClickListener(r1)
            android.view.Menu r4 = r4.b()
            android.support.v7.view.menu.h r4 = (android.support.v7.view.menu.h) r4
            android.support.v7.widget.ActionMenuPresenter r0 = r3.d
            if (r0 == 0) goto L41
            android.support.v7.widget.ActionMenuPresenter r0 = r3.d
            r0.h()
        L41:
            android.support.v7.widget.ActionMenuPresenter r0 = new android.support.v7.widget.ActionMenuPresenter
            android.content.Context r1 = r3.getContext()
            r0.<init>(r1)
            r3.d = r0
            android.support.v7.widget.ActionMenuPresenter r0 = r3.d
            r1 = 1
            r0.c(r1)
            android.view.ViewGroup$LayoutParams r0 = new android.view.ViewGroup$LayoutParams
            r1 = -2
            r2 = -1
            r0.<init>(r1, r2)
            android.support.v7.widget.ActionMenuPresenter r1 = r3.d
            android.content.Context r2 = r3.b
            r4.a(r1, r2)
            android.support.v7.widget.ActionMenuPresenter r4 = r3.d
            android.support.v7.view.menu.q r4 = r4.a(r3)
            android.support.v7.widget.ActionMenuView r4 = (android.support.v7.widget.ActionMenuView) r4
            r3.c = r4
            android.support.v7.widget.ActionMenuView r4 = r3.c
            r1 = 0
            android.support.v4.view.ab.a(r4, r1)
            android.support.v7.widget.ActionMenuView r4 = r3.c
            r3.addView(r4, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionBarContextView.a(android.support.v7.view.b):void");
    }

    @Override // android.support.v7.widget.a
    public boolean a() {
        if (this.d != null) {
            return this.d.e();
        }
        return false;
    }

    @Override // android.support.v7.widget.a
    public /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    @Override // android.support.v7.widget.a
    public boolean c() {
        if (this.d != null) {
            return this.d.g();
        }
        return false;
    }

    @Override // android.support.v7.widget.a
    public boolean d() {
        if (this.d != null) {
            return this.d.j();
        }
        return false;
    }

    @Override // android.support.v7.widget.a
    public /* bridge */ /* synthetic */ boolean e() {
        return super.e();
    }

    @Override // android.support.v7.widget.a
    public /* bridge */ /* synthetic */ boolean f() {
        return super.f();
    }

    @Override // android.support.v7.widget.a
    public /* bridge */ /* synthetic */ boolean g() {
        return super.g();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // android.support.v7.widget.a
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override // android.support.v7.widget.a
    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.i;
    }

    public CharSequence getTitle() {
        return this.h;
    }

    @Override // android.support.v7.widget.a
    public /* bridge */ /* synthetic */ void h() {
        super.h();
    }

    public void i() {
        if (this.j == null) {
            j();
        }
    }

    public void j() {
        removeAllViews();
        this.k = null;
        this.c = null;
    }

    public boolean k() {
        return this.q;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.d != null) {
            this.d.g();
            this.d.i();
        }
    }

    @Override // android.support.v7.widget.a, android.view.View
    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() != 32) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            return;
        }
        accessibilityEvent.setSource(this);
        accessibilityEvent.setClassName(getClass().getName());
        accessibilityEvent.setPackageName(getContext().getPackageName());
        accessibilityEvent.setContentDescription(this.h);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        boolean a = bd.a(this);
        int paddingRight = a ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        View view = this.j;
        if (view == null || view.getVisibility() == 8) {
            i5 = paddingRight;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.j.getLayoutParams();
            int i6 = a ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i7 = a ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int a2 = a(paddingRight, i6, a);
            i5 = a(a2 + a(this.j, a2, paddingTop, paddingTop2, a), i7, a);
        }
        LinearLayout linearLayout = this.l;
        if (linearLayout != null && this.k == null && linearLayout.getVisibility() != 8) {
            i5 += a(this.l, i5, paddingTop, paddingTop2, a);
        }
        int i8 = i5;
        View view2 = this.k;
        if (view2 != null) {
            a(view2, i8, paddingTop, paddingTop2, a);
        }
        int paddingLeft = a ? getPaddingLeft() : (i3 - i) - getPaddingRight();
        if (this.c != null) {
            a(this.c, paddingLeft, paddingTop, paddingTop2, !a);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
        } else if (View.MeasureSpec.getMode(i2) == 0) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        } else {
            int size = View.MeasureSpec.getSize(i);
            int size2 = this.e > 0 ? this.e : View.MeasureSpec.getSize(i2);
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
            int i3 = size2 - paddingTop;
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
            View view = this.j;
            if (view != null) {
                int a = a(view, paddingLeft, makeMeasureSpec, 0);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.j.getLayoutParams();
                paddingLeft = a - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
            }
            if (this.c != null && this.c.getParent() == this) {
                paddingLeft = a(this.c, paddingLeft, makeMeasureSpec, 0);
            }
            LinearLayout linearLayout = this.l;
            if (linearLayout != null && this.k == null) {
                if (this.q) {
                    this.l.measure(View.MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                    int measuredWidth = this.l.getMeasuredWidth();
                    boolean z = measuredWidth <= paddingLeft;
                    if (z) {
                        paddingLeft -= measuredWidth;
                    }
                    this.l.setVisibility(z ? 0 : 8);
                } else {
                    paddingLeft = a(linearLayout, paddingLeft, makeMeasureSpec, 0);
                }
            }
            View view2 = this.k;
            if (view2 != null) {
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                int i4 = layoutParams.width != -2 ? 1073741824 : Integer.MIN_VALUE;
                if (layoutParams.width >= 0) {
                    paddingLeft = Math.min(layoutParams.width, paddingLeft);
                }
                int i5 = layoutParams.height == -2 ? Integer.MIN_VALUE : 1073741824;
                if (layoutParams.height >= 0) {
                    i3 = Math.min(layoutParams.height, i3);
                }
                this.k.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i4), View.MeasureSpec.makeMeasureSpec(i3, i5));
            }
            if (this.e > 0) {
                setMeasuredDimension(size, size2);
                return;
            }
            int childCount = getChildCount();
            int i6 = 0;
            for (int i7 = 0; i7 < childCount; i7++) {
                int measuredHeight = getChildAt(i7).getMeasuredHeight() + paddingTop;
                if (measuredHeight > i6) {
                    i6 = measuredHeight;
                }
            }
            setMeasuredDimension(size, i6);
        }
    }

    @Override // android.support.v7.widget.a, android.view.View
    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.support.v7.widget.a
    public void setContentHeight(int i) {
        this.e = i;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.k;
        if (view2 != null) {
            removeView(view2);
        }
        this.k = view;
        if (view != null && (linearLayout = this.l) != null) {
            removeView(linearLayout);
            this.l = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.i = charSequence;
        l();
    }

    public void setTitle(CharSequence charSequence) {
        this.h = charSequence;
        l();
    }

    public void setTitleOptional(boolean z) {
        if (z != this.q) {
            requestLayout();
        }
        this.q = z;
    }

    @Override // android.support.v7.widget.a, android.view.View
    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
