package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class al extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {
    private static final String g = "ScrollingTabContainerView";
    private static final Interpolator m = new DecelerateInterpolator();
    private static final int n = 200;
    Runnable a;
    LinearLayoutCompat b;
    int c;
    int d;
    protected ViewPropertyAnimator e;
    protected final d f;
    private b h;
    private Spinner i;
    private boolean j;
    private int k;
    private int l;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class a extends BaseAdapter {
        a() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return al.this.b.getChildCount();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return ((c) al.this.b.getChildAt(i)).b();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                return al.this.a((ActionBar.e) getItem(i), true);
            }
            ((c) view).a((ActionBar.e) getItem(i));
            return view;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((c) view).b().f();
            int childCount = al.this.b.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = al.this.b.getChildAt(i);
                childAt.setSelected(childAt == view);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class c extends LinearLayout {
        private final int[] b;
        private ActionBar.e c;
        private TextView d;
        private ImageView e;
        private View f;

        public c(Context context, ActionBar.e eVar, boolean z) {
            super(context, null, a.b.actionBarTabStyle);
            this.b = new int[]{16842964};
            this.c = eVar;
            av a = av.a(context, null, this.b, a.b.actionBarTabStyle, 0);
            if (a.j(0)) {
                setBackgroundDrawable(a.a(0));
            }
            a.e();
            if (z) {
                setGravity(8388627);
            }
            a();
        }

        public void a() {
            ActionBar.e eVar = this.c;
            View d = eVar.d();
            if (d != null) {
                ViewParent parent = d.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(d);
                    }
                    addView(d);
                }
                this.f = d;
                TextView textView = this.d;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.e;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.e.setImageDrawable(null);
                    return;
                }
                return;
            }
            View view = this.f;
            if (view != null) {
                removeView(view);
                this.f = null;
            }
            Drawable b = eVar.b();
            CharSequence c = eVar.c();
            if (b != null) {
                if (this.e == null) {
                    AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    appCompatImageView.setLayoutParams(layoutParams);
                    addView(appCompatImageView, 0);
                    this.e = appCompatImageView;
                }
                this.e.setImageDrawable(b);
                this.e.setVisibility(0);
            } else {
                ImageView imageView2 = this.e;
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                    this.e.setImageDrawable(null);
                }
            }
            boolean z = !TextUtils.isEmpty(c);
            if (z) {
                if (this.d == null) {
                    AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), null, a.b.actionBarTabTextStyle);
                    appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    appCompatTextView.setLayoutParams(layoutParams2);
                    addView(appCompatTextView);
                    this.d = appCompatTextView;
                }
                this.d.setText(c);
                this.d.setVisibility(0);
            } else {
                TextView textView2 = this.d;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                    this.d.setText((CharSequence) null);
                }
            }
            ImageView imageView3 = this.e;
            if (imageView3 != null) {
                imageView3.setContentDescription(eVar.g());
            }
            ax.a(this, z ? null : eVar.g());
        }

        public void a(ActionBar.e eVar) {
            this.c = eVar;
            a();
        }

        public ActionBar.e b() {
            return this.c;
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(ActionBar.e.class.getName());
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(ActionBar.e.class.getName());
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (al.this.c <= 0 || getMeasuredWidth() <= al.this.c) {
                return;
            }
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(al.this.c, 1073741824), i2);
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z) {
                sendAccessibilityEvent(4);
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    protected class d extends AnimatorListenerAdapter {
        private boolean b = false;
        private int c;

        protected d() {
        }

        public d a(ViewPropertyAnimator viewPropertyAnimator, int i) {
            this.c = i;
            al.this.e = viewPropertyAnimator;
            return this;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.b = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.b) {
                return;
            }
            al alVar = al.this;
            alVar.e = null;
            alVar.setVisibility(this.c);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            al.this.setVisibility(0);
            this.b = false;
        }
    }

    public al(Context context) {
        super(context);
        this.f = new d();
        setHorizontalScrollBarEnabled(false);
        android.support.v7.view.a a2 = android.support.v7.view.a.a(context);
        setContentHeight(a2.e());
        this.d = a2.g();
        this.b = e();
        addView(this.b, new ViewGroup.LayoutParams(-2, -1));
    }

    private boolean b() {
        Spinner spinner = this.i;
        return spinner != null && spinner.getParent() == this;
    }

    private void c() {
        if (b()) {
            return;
        }
        if (this.i == null) {
            this.i = f();
        }
        removeView(this.b);
        addView(this.i, new ViewGroup.LayoutParams(-2, -1));
        if (this.i.getAdapter() == null) {
            this.i.setAdapter((SpinnerAdapter) new a());
        }
        Runnable runnable = this.a;
        if (runnable != null) {
            removeCallbacks(runnable);
            this.a = null;
        }
        this.i.setSelection(this.l);
    }

    private boolean d() {
        if (b()) {
            removeView(this.i);
            addView(this.b, new ViewGroup.LayoutParams(-2, -1));
            setTabSelected(this.i.getSelectedItemPosition());
            return false;
        }
        return false;
    }

    private LinearLayoutCompat e() {
        LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getContext(), null, a.b.actionBarTabBarStyle);
        linearLayoutCompat.setMeasureWithLargestChildEnabled(true);
        linearLayoutCompat.setGravity(17);
        linearLayoutCompat.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
        return linearLayoutCompat;
    }

    private Spinner f() {
        AppCompatSpinner appCompatSpinner = new AppCompatSpinner(getContext(), null, a.b.actionDropDownStyle);
        appCompatSpinner.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
        appCompatSpinner.setOnItemSelectedListener(this);
        return appCompatSpinner;
    }

    c a(ActionBar.e eVar, boolean z) {
        c cVar = new c(getContext(), eVar, z);
        if (z) {
            cVar.setBackgroundDrawable(null);
            cVar.setLayoutParams(new AbsListView.LayoutParams(-1, this.k));
        } else {
            cVar.setFocusable(true);
            if (this.h == null) {
                this.h = new b();
            }
            cVar.setOnClickListener(this.h);
        }
        return cVar;
    }

    public void a() {
        this.b.removeAllViews();
        Spinner spinner = this.i;
        if (spinner != null) {
            ((a) spinner.getAdapter()).notifyDataSetChanged();
        }
        if (this.j) {
            requestLayout();
        }
    }

    public void a(int i) {
        ViewPropertyAnimator alpha;
        ViewPropertyAnimator viewPropertyAnimator = this.e;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
        if (i == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            alpha = animate().alpha(1.0f);
        } else {
            alpha = animate().alpha(0.0f);
        }
        alpha.setDuration(200L);
        alpha.setInterpolator(m);
        alpha.setListener(this.f.a(alpha, i));
        alpha.start();
    }

    public void a(ActionBar.e eVar, int i, boolean z) {
        c a2 = a(eVar, false);
        this.b.addView(a2, i, new LinearLayoutCompat.LayoutParams(0, -1, 1.0f));
        Spinner spinner = this.i;
        if (spinner != null) {
            ((a) spinner.getAdapter()).notifyDataSetChanged();
        }
        if (z) {
            a2.setSelected(true);
        }
        if (this.j) {
            requestLayout();
        }
    }

    public void b(int i) {
        final View childAt = this.b.getChildAt(i);
        Runnable runnable = this.a;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        this.a = new Runnable() { // from class: android.support.v7.widget.al.1
            @Override // java.lang.Runnable
            public void run() {
                al.this.smoothScrollTo(childAt.getLeft() - ((al.this.getWidth() - childAt.getWidth()) / 2), 0);
                al.this.a = null;
            }
        };
        post(this.a);
    }

    public void b(ActionBar.e eVar, boolean z) {
        c a2 = a(eVar, false);
        this.b.addView(a2, new LinearLayoutCompat.LayoutParams(0, -1, 1.0f));
        Spinner spinner = this.i;
        if (spinner != null) {
            ((a) spinner.getAdapter()).notifyDataSetChanged();
        }
        if (z) {
            a2.setSelected(true);
        }
        if (this.j) {
            requestLayout();
        }
    }

    public void c(int i) {
        ((c) this.b.getChildAt(i)).a();
        Spinner spinner = this.i;
        if (spinner != null) {
            ((a) spinner.getAdapter()).notifyDataSetChanged();
        }
        if (this.j) {
            requestLayout();
        }
    }

    public void d(int i) {
        this.b.removeViewAt(i);
        Spinner spinner = this.i;
        if (spinner != null) {
            ((a) spinner.getAdapter()).notifyDataSetChanged();
        }
        if (this.j) {
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.a;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        android.support.v7.view.a a2 = android.support.v7.view.a.a(getContext());
        setContentHeight(a2.e());
        this.d = a2.g();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.a;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        ((c) view).b().f();
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        int mode = View.MeasureSpec.getMode(i);
        boolean z = true;
        boolean z2 = mode == 1073741824;
        setFillViewport(z2);
        int childCount = this.b.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            i3 = -1;
        } else {
            if (childCount > 2) {
                this.c = (int) (View.MeasureSpec.getSize(i) * 0.4f);
            } else {
                this.c = View.MeasureSpec.getSize(i) / 2;
            }
            i3 = Math.min(this.c, this.d);
        }
        this.c = i3;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.k, 1073741824);
        if ((z2 || !this.j) ? false : false) {
            this.b.measure(0, makeMeasureSpec);
            if (this.b.getMeasuredWidth() > View.MeasureSpec.getSize(i)) {
                c();
                int measuredWidth = getMeasuredWidth();
                super.onMeasure(i, makeMeasureSpec);
                int measuredWidth2 = getMeasuredWidth();
                if (z2 || measuredWidth == measuredWidth2) {
                }
                setTabSelected(this.l);
                return;
            }
        }
        d();
        int measuredWidth3 = getMeasuredWidth();
        super.onMeasure(i, makeMeasureSpec);
        int measuredWidth22 = getMeasuredWidth();
        if (z2) {
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void setAllowCollapse(boolean z) {
        this.j = z;
    }

    public void setContentHeight(int i) {
        this.k = i;
        requestLayout();
    }

    public void setTabSelected(int i) {
        this.l = i;
        int childCount = this.b.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = this.b.getChildAt(i2);
            boolean z = i2 == i;
            childAt.setSelected(z);
            if (z) {
                b(i);
            }
            i2++;
        }
        Spinner spinner = this.i;
        if (spinner == null || i < 0) {
            return;
        }
        spinner.setSelection(i);
    }
}
