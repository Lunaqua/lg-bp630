package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class a extends ViewGroup {
    private static final int g = 200;
    protected final C0049a a;
    protected final Context b;
    protected ActionMenuView c;
    protected ActionMenuPresenter d;
    protected int e;
    protected android.support.v4.view.af f;
    private boolean h;
    private boolean i;

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: android.support.v7.widget.a$a  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class C0049a implements android.support.v4.view.ag {
        int a;
        private boolean c = false;

        protected C0049a() {
        }

        public C0049a a(android.support.v4.view.af afVar, int i) {
            a.this.f = afVar;
            this.a = i;
            return this;
        }

        @Override // android.support.v4.view.ag
        public void a(View view) {
            a.super.setVisibility(0);
            this.c = false;
        }

        @Override // android.support.v4.view.ag
        public void b(View view) {
            if (this.c) {
                return;
            }
            a aVar = a.this;
            aVar.f = null;
            a.super.setVisibility(this.a);
        }

        @Override // android.support.v4.view.ag
        public void c(View view) {
            this.c = true;
        }
    }

    a(Context context) {
        this(context, null);
    }

    a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new C0049a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(a.b.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.b = context;
        } else {
            this.b = new ContextThemeWrapper(context, typedValue.resourceId);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int a(int i, int i2, boolean z) {
        return z ? i - i2 : i + i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int a(View view, int i, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int a(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = i2 + ((i3 - measuredHeight) / 2);
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        return z ? -measuredWidth : measuredWidth;
    }

    public android.support.v4.view.af a(int i, long j) {
        android.support.v4.view.af a;
        android.support.v4.view.af afVar = this.f;
        if (afVar != null) {
            afVar.d();
        }
        if (i == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            a = android.support.v4.view.ab.C(this).a(1.0f);
        } else {
            a = android.support.v4.view.ab.C(this).a(0.0f);
        }
        a.a(j);
        a.a(this.a.a(a, i));
        return a;
    }

    public void a(int i) {
        a(i, 200L).e();
    }

    public boolean a() {
        ActionMenuPresenter actionMenuPresenter = this.d;
        if (actionMenuPresenter != null) {
            return actionMenuPresenter.e();
        }
        return false;
    }

    public void b() {
        post(new Runnable() { // from class: android.support.v7.widget.a.1
            @Override // java.lang.Runnable
            public void run() {
                a.this.a();
            }
        });
    }

    public boolean c() {
        ActionMenuPresenter actionMenuPresenter = this.d;
        if (actionMenuPresenter != null) {
            return actionMenuPresenter.g();
        }
        return false;
    }

    public boolean d() {
        ActionMenuPresenter actionMenuPresenter = this.d;
        if (actionMenuPresenter != null) {
            return actionMenuPresenter.j();
        }
        return false;
    }

    public boolean e() {
        ActionMenuPresenter actionMenuPresenter = this.d;
        if (actionMenuPresenter != null) {
            return actionMenuPresenter.k();
        }
        return false;
    }

    public boolean f() {
        ActionMenuPresenter actionMenuPresenter = this.d;
        return actionMenuPresenter != null && actionMenuPresenter.l();
    }

    public boolean g() {
        return f() && getVisibility() == 0;
    }

    public int getAnimatedVisibility() {
        return this.f != null ? this.a.a : getVisibility();
    }

    public int getContentHeight() {
        return this.e;
    }

    public void h() {
        ActionMenuPresenter actionMenuPresenter = this.d;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.h();
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, a.l.ActionBar, a.b.actionBarStyle, 0);
        setContentHeight(obtainStyledAttributes.getLayoutDimension(a.l.ActionBar_height, 0));
        obtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.d;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.a(configuration);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.i = false;
        }
        if (!this.i) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.i = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.i = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.h = false;
        }
        if (!this.h) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.h = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.h = false;
        }
        return true;
    }

    public void setContentHeight(int i) {
        this.e = i;
        requestLayout();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i != getVisibility()) {
            android.support.v4.view.af afVar = this.f;
            if (afVar != null) {
                afVar.d();
            }
            super.setVisibility(i);
        }
    }
}
