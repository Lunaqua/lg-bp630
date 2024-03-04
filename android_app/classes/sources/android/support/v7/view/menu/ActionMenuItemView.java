package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.q;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ax;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ActionMenuItemView extends AppCompatTextView implements q.a, ActionMenuView.a, View.OnClickListener {
    private static final String e = "ActionMenuItemView";
    private static final int m = 32;
    k b;
    h.b c;
    b d;
    private CharSequence f;
    private Drawable g;
    private android.support.v7.widget.w h;
    private boolean i;
    private boolean j;
    private int k;
    private int l;
    private int n;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class a extends android.support.v7.widget.w {
        public a() {
            super(ActionMenuItemView.this);
        }

        @Override // android.support.v7.widget.w
        public t a() {
            if (ActionMenuItemView.this.d != null) {
                return ActionMenuItemView.this.d.a();
            }
            return null;
        }

        @Override // android.support.v7.widget.w
        protected boolean b() {
            t a;
            return ActionMenuItemView.this.c != null && ActionMenuItemView.this.c.a(ActionMenuItemView.this.b) && (a = a()) != null && a.e();
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class b {
        public abstract t a();
    }

    public ActionMenuItemView(Context context) {
        this(context, null);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Resources resources = context.getResources();
        this.i = f();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.l.ActionMenuItemView, i, 0);
        this.k = obtainStyledAttributes.getDimensionPixelSize(a.l.ActionMenuItemView_android_minWidth, 0);
        obtainStyledAttributes.recycle();
        this.n = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.l = -1;
        setSaveEnabled(false);
    }

    private boolean f() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        return i >= 480 || (i >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    private void g() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.f);
        if (this.g != null && (!this.b.n() || (!this.i && !this.j))) {
            z = false;
        }
        boolean z3 = z2 & z;
        setText(z3 ? this.f : null);
        CharSequence contentDescription = this.b.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            contentDescription = z3 ? null : this.b.getTitle();
        }
        setContentDescription(contentDescription);
        CharSequence tooltipText = this.b.getTooltipText();
        if (TextUtils.isEmpty(tooltipText)) {
            ax.a(this, z3 ? null : this.b.getTitle());
        } else {
            ax.a(this, tooltipText);
        }
    }

    @Override // android.support.v7.view.menu.q.a
    public void a(k kVar, int i) {
        this.b = kVar;
        setIcon(kVar.getIcon());
        setTitle(kVar.a((q.a) this));
        setId(kVar.getItemId());
        setVisibility(kVar.isVisible() ? 0 : 8);
        setEnabled(kVar.isEnabled());
        if (kVar.hasSubMenu() && this.h == null) {
            this.h = new a();
        }
    }

    @Override // android.support.v7.view.menu.q.a
    public void a(boolean z, char c) {
    }

    @Override // android.support.v7.view.menu.q.a
    public boolean a() {
        return true;
    }

    public boolean b() {
        return !TextUtils.isEmpty(getText());
    }

    @Override // android.support.v7.view.menu.q.a
    public boolean c() {
        return true;
    }

    @Override // android.support.v7.widget.ActionMenuView.a
    public boolean d() {
        return b() && this.b.getIcon() == null;
    }

    @Override // android.support.v7.widget.ActionMenuView.a
    public boolean e() {
        return b();
    }

    @Override // android.support.v7.view.menu.q.a
    public k getItemData() {
        return this.b;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        h.b bVar = this.c;
        if (bVar != null) {
            bVar.a(this.b);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.i = f();
        g();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        boolean b2 = b();
        if (b2 && (i3 = this.l) >= 0) {
            super.setPadding(i3, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int measuredWidth = getMeasuredWidth();
        int min = mode == Integer.MIN_VALUE ? Math.min(size, this.k) : this.k;
        if (mode != 1073741824 && this.k > 0 && measuredWidth < min) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(min, 1073741824), i2);
        }
        if (b2 || this.g == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.g.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        android.support.v7.widget.w wVar;
        if (this.b.hasSubMenu() && (wVar = this.h) != null && wVar.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.support.v7.view.menu.q.a
    public void setCheckable(boolean z) {
    }

    @Override // android.support.v7.view.menu.q.a
    public void setChecked(boolean z) {
    }

    public void setExpandedFormat(boolean z) {
        if (this.j != z) {
            this.j = z;
            k kVar = this.b;
            if (kVar != null) {
                kVar.i();
            }
        }
    }

    @Override // android.support.v7.view.menu.q.a
    public void setIcon(Drawable drawable) {
        this.g = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i = this.n;
            if (intrinsicWidth > i) {
                intrinsicHeight = (int) (intrinsicHeight * (i / intrinsicWidth));
                intrinsicWidth = i;
            }
            int i2 = this.n;
            if (intrinsicHeight > i2) {
                intrinsicWidth = (int) (intrinsicWidth * (i2 / intrinsicHeight));
                intrinsicHeight = i2;
            }
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        }
        setCompoundDrawables(drawable, null, null, null);
        g();
    }

    public void setItemInvoker(h.b bVar) {
        this.c = bVar;
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        this.l = i;
        super.setPadding(i, i2, i3, i4);
    }

    public void setPopupCallback(b bVar) {
        this.d = bVar;
    }

    @Override // android.support.v7.view.menu.q.a
    public void setTitle(CharSequence charSequence) {
        this.f = charSequence;
        g();
    }
}
