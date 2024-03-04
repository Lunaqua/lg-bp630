package kankan.wheel.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import com.lge.media.launcher.b;
import java.util.LinkedList;
import java.util.List;
import kankan.wheel.widget.WheelScroller;
import kankan.wheel.widget.adapters.WheelViewAdapter;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class WheelView extends View {
    private static final int[] c = {-15658735, 11184810, 11184810};
    private static final int d = 10;
    private static final int e = 10;
    private static final int f = 7;
    boolean a;
    WheelScroller.ScrollingListener b;
    private int g;
    private int h;
    private int i;
    private Drawable j;
    private GradientDrawable k;
    private GradientDrawable l;
    private WheelScroller m;
    private boolean n;
    private int o;
    private LinearLayout p;
    private int q;
    private WheelViewAdapter r;
    private WheelRecycle s;
    private List<OnWheelChangedListener> t;
    private List<OnWheelScrollListener> u;
    private List<OnWheelClickedListener> v;
    private DataSetObserver w;

    public WheelView(Context context) {
        super(context);
        this.g = 0;
        this.h = 7;
        this.i = 0;
        this.a = false;
        this.s = new WheelRecycle(this);
        this.t = new LinkedList();
        this.u = new LinkedList();
        this.v = new LinkedList();
        this.b = new WheelScroller.ScrollingListener() { // from class: kankan.wheel.widget.WheelView.1
            @Override // kankan.wheel.widget.WheelScroller.ScrollingListener
            public void a() {
                WheelView.this.n = true;
                WheelView.this.a();
            }

            @Override // kankan.wheel.widget.WheelScroller.ScrollingListener
            public void a(int i) {
                WheelView.this.b(i);
                int height = WheelView.this.getHeight();
                if (WheelView.this.o <= height && WheelView.this.o >= (height = -height)) {
                    return;
                }
                WheelView.this.o = height;
                WheelView.this.m.a();
            }

            @Override // kankan.wheel.widget.WheelScroller.ScrollingListener
            public void b() {
                if (WheelView.this.n) {
                    WheelView.this.b();
                    WheelView.this.n = false;
                }
                WheelView.this.o = 0;
                WheelView.this.invalidate();
            }

            @Override // kankan.wheel.widget.WheelScroller.ScrollingListener
            public void c() {
                if (Math.abs(WheelView.this.o) > 1) {
                    WheelView.this.m.a(WheelView.this.o, 0);
                }
            }
        };
        this.w = new DataSetObserver() { // from class: kankan.wheel.widget.WheelView.2
            @Override // android.database.DataSetObserver
            public void onChanged() {
                WheelView.this.a(false);
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                WheelView.this.a(true);
            }
        };
        a(context);
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = 0;
        this.h = 7;
        this.i = 0;
        this.a = false;
        this.s = new WheelRecycle(this);
        this.t = new LinkedList();
        this.u = new LinkedList();
        this.v = new LinkedList();
        this.b = new WheelScroller.ScrollingListener() { // from class: kankan.wheel.widget.WheelView.1
            @Override // kankan.wheel.widget.WheelScroller.ScrollingListener
            public void a() {
                WheelView.this.n = true;
                WheelView.this.a();
            }

            @Override // kankan.wheel.widget.WheelScroller.ScrollingListener
            public void a(int i) {
                WheelView.this.b(i);
                int height = WheelView.this.getHeight();
                if (WheelView.this.o <= height && WheelView.this.o >= (height = -height)) {
                    return;
                }
                WheelView.this.o = height;
                WheelView.this.m.a();
            }

            @Override // kankan.wheel.widget.WheelScroller.ScrollingListener
            public void b() {
                if (WheelView.this.n) {
                    WheelView.this.b();
                    WheelView.this.n = false;
                }
                WheelView.this.o = 0;
                WheelView.this.invalidate();
            }

            @Override // kankan.wheel.widget.WheelScroller.ScrollingListener
            public void c() {
                if (Math.abs(WheelView.this.o) > 1) {
                    WheelView.this.m.a(WheelView.this.o, 0);
                }
            }
        };
        this.w = new DataSetObserver() { // from class: kankan.wheel.widget.WheelView.2
            @Override // android.database.DataSetObserver
            public void onChanged() {
                WheelView.this.a(false);
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                WheelView.this.a(true);
            }
        };
        a(context);
    }

    public WheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = 0;
        this.h = 7;
        this.i = 0;
        this.a = false;
        this.s = new WheelRecycle(this);
        this.t = new LinkedList();
        this.u = new LinkedList();
        this.v = new LinkedList();
        this.b = new WheelScroller.ScrollingListener() { // from class: kankan.wheel.widget.WheelView.1
            @Override // kankan.wheel.widget.WheelScroller.ScrollingListener
            public void a() {
                WheelView.this.n = true;
                WheelView.this.a();
            }

            @Override // kankan.wheel.widget.WheelScroller.ScrollingListener
            public void a(int i2) {
                WheelView.this.b(i2);
                int height = WheelView.this.getHeight();
                if (WheelView.this.o <= height && WheelView.this.o >= (height = -height)) {
                    return;
                }
                WheelView.this.o = height;
                WheelView.this.m.a();
            }

            @Override // kankan.wheel.widget.WheelScroller.ScrollingListener
            public void b() {
                if (WheelView.this.n) {
                    WheelView.this.b();
                    WheelView.this.n = false;
                }
                WheelView.this.o = 0;
                WheelView.this.invalidate();
            }

            @Override // kankan.wheel.widget.WheelScroller.ScrollingListener
            public void c() {
                if (Math.abs(WheelView.this.o) > 1) {
                    WheelView.this.m.a(WheelView.this.o, 0);
                }
            }
        };
        this.w = new DataSetObserver() { // from class: kankan.wheel.widget.WheelView.2
            @Override // android.database.DataSetObserver
            public void onChanged() {
                WheelView.this.a(false);
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                WheelView.this.a(true);
            }
        };
        a(context);
    }

    private int a(LinearLayout linearLayout) {
        if (linearLayout != null && linearLayout.getChildAt(0) != null) {
            this.i = linearLayout.getChildAt(0).getMeasuredHeight();
        }
        int i = this.i;
        return Math.max((this.h * i) - ((i * 10) / 50), getSuggestedMinimumHeight());
    }

    private void a(Context context) {
        this.m = new WheelScroller(getContext(), this.b);
    }

    private void a(Canvas canvas) {
        double itemHeight = getItemHeight();
        Double.isNaN(itemHeight);
        int i = (int) (itemHeight * 1.5d);
        this.k.setBounds(0, 0, getWidth(), i);
        this.k.draw(canvas);
        this.l.setBounds(0, getHeight() - i, getWidth(), getHeight());
        this.l.draw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        this.o += i;
        int itemHeight = getItemHeight();
        int i2 = this.o / itemHeight;
        int i3 = this.g - i2;
        int i4 = this.r.i();
        int i5 = this.o % itemHeight;
        if (Math.abs(i5) <= itemHeight / 2) {
            i5 = 0;
        }
        if (this.a && i4 > 0) {
            if (i5 > 0) {
                i3--;
                i2++;
            } else if (i5 < 0) {
                i3++;
                i2--;
            }
            while (i3 < 0) {
                i3 += i4;
            }
            i3 %= i4;
        } else if (i3 < 0) {
            i2 = this.g;
            i3 = 0;
        } else if (i3 >= i4) {
            i2 = (this.g - i4) + 1;
            i3 = i4 - 1;
        } else if (i3 > 0 && i5 > 0) {
            i3--;
            i2++;
        } else if (i3 < i4 - 1 && i5 < 0) {
            i3++;
            i2--;
        }
        int i6 = this.o;
        if (i3 != this.g) {
            a(i3, false);
        } else {
            invalidate();
        }
        this.o = i6 - (i2 * itemHeight);
        if (this.o > getHeight()) {
            this.o = (this.o % getHeight()) + getHeight();
        }
    }

    private void b(Canvas canvas) {
        canvas.save();
        canvas.translate(10.0f, (-(((this.g - this.q) * getItemHeight()) + ((getItemHeight() - getHeight()) / 2))) + this.o);
        this.p.draw(canvas);
        canvas.restore();
    }

    private boolean b(int i, boolean z) {
        View d2 = d(i);
        if (d2 != null) {
            if (z) {
                this.p.addView(d2, 0);
                return true;
            }
            this.p.addView(d2);
            return true;
        }
        return false;
    }

    private int c(int i, int i2) {
        e();
        this.p.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.p.measure(View.MeasureSpec.makeMeasureSpec(i, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredWidth = this.p.getMeasuredWidth();
        if (i2 != 1073741824) {
            int max = Math.max(measuredWidth + 20, getSuggestedMinimumWidth());
            if (i2 != Integer.MIN_VALUE || i >= max) {
                i = max;
            }
        }
        this.p.measure(View.MeasureSpec.makeMeasureSpec(i - 20, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
        return i;
    }

    private void c(Canvas canvas) {
        int height = getHeight() / 2;
        double itemHeight = getItemHeight() / 2;
        Double.isNaN(itemHeight);
        int i = (int) (itemHeight * 1.2d);
        this.j.setBounds(0, height - i, getWidth(), height + i);
        this.j.draw(canvas);
    }

    private boolean c(int i) {
        WheelViewAdapter wheelViewAdapter = this.r;
        return wheelViewAdapter != null && wheelViewAdapter.i() > 0 && (this.a || (i >= 0 && i < this.r.i()));
    }

    private View d(int i) {
        WheelViewAdapter wheelViewAdapter = this.r;
        if (wheelViewAdapter == null || wheelViewAdapter.i() == 0) {
            return null;
        }
        int i2 = this.r.i();
        if (c(i)) {
            while (i < 0) {
                i += i2;
            }
            return this.r.a(i % i2, this.s.a(), this.p);
        }
        return this.r.a(this.s.b(), this.p);
    }

    private void d(int i, int i2) {
        this.p.layout(0, 0, i - 20, i2);
    }

    private void e() {
        if (this.j == null) {
            this.j = getContext().getResources().getDrawable(b.g.wheel_val);
        }
        if (this.k == null) {
            this.k = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, c);
        }
        if (this.l == null) {
            this.l = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, c);
        }
        setBackgroundResource(b.g.wheel_bg);
    }

    private boolean f() {
        boolean z;
        ItemsRange itemsRange = getItemsRange();
        LinearLayout linearLayout = this.p;
        if (linearLayout != null) {
            int a = this.s.a(linearLayout, this.q, itemsRange);
            z = this.q != a;
            this.q = a;
        } else {
            h();
            z = true;
        }
        if (!z) {
            z = (this.q == itemsRange.a() && this.p.getChildCount() == itemsRange.c()) ? false : true;
        }
        if (this.q <= itemsRange.a() || this.q > itemsRange.b()) {
            this.q = itemsRange.a();
        } else {
            for (int i = this.q - 1; i >= itemsRange.a() && b(i, true); i--) {
                this.q = i;
            }
        }
        int i2 = this.q;
        for (int childCount = this.p.getChildCount(); childCount < itemsRange.c(); childCount++) {
            if (!b(this.q + childCount, false) && this.p.getChildCount() == 0) {
                i2++;
            }
        }
        this.q = i2;
        return z;
    }

    private void g() {
        if (f()) {
            c(getWidth(), 1073741824);
            d(getWidth(), getHeight());
        }
    }

    private int getItemHeight() {
        int i = this.i;
        if (i != 0) {
            return i;
        }
        LinearLayout linearLayout = this.p;
        if (linearLayout == null || linearLayout.getChildAt(0) == null) {
            return getHeight() / this.h;
        }
        this.i = this.p.getChildAt(0).getHeight();
        return this.i;
    }

    private ItemsRange getItemsRange() {
        if (getItemHeight() == 0) {
            return null;
        }
        int i = this.g;
        int i2 = 1;
        while (getItemHeight() * i2 < getHeight()) {
            i--;
            i2 += 2;
        }
        int i3 = this.o;
        if (i3 != 0) {
            if (i3 > 0) {
                i--;
            }
            int itemHeight = this.o / getItemHeight();
            i -= itemHeight;
            double d2 = i2 + 1;
            double asin = Math.asin(itemHeight);
            Double.isNaN(d2);
            i2 = (int) (d2 + asin);
        }
        return new ItemsRange(i, i2);
    }

    private void h() {
        if (this.p == null) {
            this.p = new LinearLayout(getContext());
            this.p.setOrientation(1);
        }
    }

    private void i() {
        LinearLayout linearLayout = this.p;
        if (linearLayout != null) {
            this.s.a(linearLayout, this.q, new ItemsRange());
        } else {
            h();
        }
        int i = this.h / 2;
        for (int i2 = this.g + i; i2 >= this.g - i; i2--) {
            if (b(i2, true)) {
                this.q = i2;
            }
        }
    }

    protected void a() {
        for (OnWheelScrollListener onWheelScrollListener : this.u) {
            onWheelScrollListener.a(this);
        }
    }

    protected void a(int i) {
        for (OnWheelClickedListener onWheelClickedListener : this.v) {
            onWheelClickedListener.a(this, i);
        }
    }

    protected void a(int i, int i2) {
        for (OnWheelChangedListener onWheelChangedListener : this.t) {
            onWheelChangedListener.a(this, i, i2);
        }
    }

    public void a(int i, boolean z) {
        int min;
        WheelViewAdapter wheelViewAdapter = this.r;
        if (wheelViewAdapter == null || wheelViewAdapter.i() == 0) {
            return;
        }
        int i2 = this.r.i();
        if (i < 0 || i >= i2) {
            if (!this.a) {
                return;
            }
            while (i < 0) {
                i += i2;
            }
            i %= i2;
        }
        int i3 = this.g;
        if (i != i3) {
            if (!z) {
                this.o = 0;
                this.g = i;
                a(i3, this.g);
                invalidate();
                return;
            }
            int i4 = i - i3;
            if (this.a && (min = (i2 + Math.min(i, i3)) - Math.max(i, this.g)) < Math.abs(i4)) {
                i4 = i4 < 0 ? min : -min;
            }
            b(i4, 0);
        }
    }

    public void a(OnWheelChangedListener onWheelChangedListener) {
        this.t.add(onWheelChangedListener);
    }

    public void a(OnWheelClickedListener onWheelClickedListener) {
        this.v.add(onWheelClickedListener);
    }

    public void a(OnWheelScrollListener onWheelScrollListener) {
        this.u.add(onWheelScrollListener);
    }

    public void a(boolean z) {
        if (z) {
            this.s.c();
            LinearLayout linearLayout = this.p;
            if (linearLayout != null) {
                linearLayout.removeAllViews();
            }
            this.o = 0;
        } else {
            LinearLayout linearLayout2 = this.p;
            if (linearLayout2 != null) {
                this.s.a(linearLayout2, this.q, new ItemsRange());
            }
        }
        invalidate();
    }

    protected void b() {
        for (OnWheelScrollListener onWheelScrollListener : this.u) {
            onWheelScrollListener.b(this);
        }
    }

    public void b(int i, int i2) {
        this.m.a((i * getItemHeight()) - this.o, i2);
    }

    public void b(OnWheelChangedListener onWheelChangedListener) {
        this.t.remove(onWheelChangedListener);
    }

    public void b(OnWheelClickedListener onWheelClickedListener) {
        this.v.remove(onWheelClickedListener);
    }

    public void b(OnWheelScrollListener onWheelScrollListener) {
        this.u.remove(onWheelScrollListener);
    }

    public boolean c() {
        return this.a;
    }

    public void d() {
        this.m.a();
    }

    public int getCurrentItem() {
        return this.g;
    }

    public WheelViewAdapter getViewAdapter() {
        return this.r;
    }

    public int getVisibleItems() {
        return this.h;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        WheelViewAdapter wheelViewAdapter = this.r;
        if (wheelViewAdapter != null && wheelViewAdapter.i() > 0) {
            g();
            b(canvas);
        }
        a(canvas);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        d(i3 - i, i4 - i2);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        i();
        int c2 = c(size, mode);
        if (mode2 != 1073741824) {
            int a = a(this.p);
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(a, size2) : a;
        }
        setMeasuredDimension(c2, size2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || getViewAdapter() == null) {
            return true;
        }
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2 && getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        } else if (!this.n) {
            int y = ((int) motionEvent.getY()) - (getHeight() / 2);
            int itemHeight = (y > 0 ? y + (getItemHeight() / 2) : y - (getItemHeight() / 2)) / getItemHeight();
            if (itemHeight != 0 && c(this.g + itemHeight)) {
                a(this.g + itemHeight);
            }
        }
        return this.m.a(motionEvent);
    }

    public void setCurrentItem(int i) {
        a(i, false);
    }

    public void setCyclic(boolean z) {
        this.a = z;
        a(false);
    }

    public void setInterpolator(Interpolator interpolator) {
        this.m.a(interpolator);
    }

    public void setViewAdapter(WheelViewAdapter wheelViewAdapter) {
        WheelViewAdapter wheelViewAdapter2 = this.r;
        if (wheelViewAdapter2 != null) {
            wheelViewAdapter2.b(this.w);
        }
        this.r = wheelViewAdapter;
        WheelViewAdapter wheelViewAdapter3 = this.r;
        if (wheelViewAdapter3 != null) {
            wheelViewAdapter3.a(this.w);
        }
        a(true);
    }

    public void setVisibleItems(int i) {
        this.h = i;
    }
}
