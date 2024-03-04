package android.support.v4.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class PagerTabStrip extends PagerTitleStrip {
    private static final String g = "PagerTabStrip";
    private static final int h = 3;
    private static final int i = 6;
    private static final int j = 16;
    private static final int k = 32;
    private static final int l = 64;
    private static final int m = 1;
    private static final int n = 32;
    private boolean A;
    private float B;
    private float C;
    private int D;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private final Paint u;
    private final Rect v;
    private int w;
    private boolean x;
    private boolean y;
    private int z;

    public PagerTabStrip(@android.support.annotation.af Context context) {
        this(context, null);
    }

    public PagerTabStrip(@android.support.annotation.af Context context, @android.support.annotation.ag AttributeSet attributeSet) {
        super(context, attributeSet);
        this.u = new Paint();
        this.v = new Rect();
        this.w = 255;
        this.x = false;
        this.y = false;
        this.o = this.f;
        this.u.setColor(this.o);
        float f = context.getResources().getDisplayMetrics().density;
        this.p = (int) ((3.0f * f) + 0.5f);
        this.q = (int) ((6.0f * f) + 0.5f);
        this.r = (int) (64.0f * f);
        this.t = (int) ((16.0f * f) + 0.5f);
        this.z = (int) ((1.0f * f) + 0.5f);
        this.s = (int) ((f * 32.0f) + 0.5f);
        this.D = ViewConfiguration.get(context).getScaledTouchSlop();
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setTextSpacing(getTextSpacing());
        setWillNotDraw(false);
        this.b.setFocusable(true);
        this.b.setOnClickListener(new View.OnClickListener() { // from class: android.support.v4.view.PagerTabStrip.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PagerTabStrip.this.a.setCurrentItem(PagerTabStrip.this.a.getCurrentItem() - 1);
            }
        });
        this.d.setFocusable(true);
        this.d.setOnClickListener(new View.OnClickListener() { // from class: android.support.v4.view.PagerTabStrip.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PagerTabStrip.this.a.setCurrentItem(PagerTabStrip.this.a.getCurrentItem() + 1);
            }
        });
        if (getBackground() == null) {
            this.x = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v4.view.PagerTitleStrip
    public void a(int i2, float f, boolean z) {
        Rect rect = this.v;
        int height = getHeight();
        int left = this.c.getLeft() - this.t;
        int right = this.c.getRight() + this.t;
        int i3 = height - this.p;
        rect.set(left, i3, right, height);
        super.a(i2, f, z);
        this.w = (int) (Math.abs(f - 0.5f) * 2.0f * 255.0f);
        rect.union(this.c.getLeft() - this.t, i3, this.c.getRight() + this.t, height);
        invalidate(rect);
    }

    public boolean getDrawFullUnderline() {
        return this.x;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v4.view.PagerTitleStrip
    public int getMinHeight() {
        return Math.max(super.getMinHeight(), this.s);
    }

    @android.support.annotation.k
    public int getTabIndicatorColor() {
        return this.o;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int left = this.c.getLeft() - this.t;
        int right = this.c.getRight() + this.t;
        this.u.setColor((this.w << 24) | (this.o & ab.r));
        float f = height;
        canvas.drawRect(left, height - this.p, right, f, this.u);
        if (this.x) {
            this.u.setColor((-16777216) | (this.o & ab.r));
            canvas.drawRect(getPaddingLeft(), height - this.z, getWidth() - getPaddingRight(), f, this.u);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewPager viewPager;
        int currentItem;
        int action = motionEvent.getAction();
        if (action == 0 || !this.A) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (action == 0) {
                this.B = x;
                this.C = y;
                this.A = false;
            } else if (action == 1) {
                if (x < this.c.getLeft() - this.t) {
                    viewPager = this.a;
                    currentItem = this.a.getCurrentItem() - 1;
                } else if (x > this.c.getRight() + this.t) {
                    viewPager = this.a;
                    currentItem = this.a.getCurrentItem() + 1;
                }
                viewPager.setCurrentItem(currentItem);
            } else if (action == 2 && (Math.abs(x - this.B) > this.D || Math.abs(y - this.C) > this.D)) {
                this.A = true;
            }
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public void setBackgroundColor(@android.support.annotation.k int i2) {
        super.setBackgroundColor(i2);
        if (this.y) {
            return;
        }
        this.x = (i2 & ab.s) == 0;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.y) {
            return;
        }
        this.x = drawable == null;
    }

    @Override // android.view.View
    public void setBackgroundResource(@android.support.annotation.p int i2) {
        super.setBackgroundResource(i2);
        if (this.y) {
            return;
        }
        this.x = i2 == 0;
    }

    public void setDrawFullUnderline(boolean z) {
        this.x = z;
        this.y = true;
        invalidate();
    }

    @Override // android.view.View
    public void setPadding(int i2, int i3, int i4, int i5) {
        int i6 = this.q;
        if (i5 < i6) {
            i5 = i6;
        }
        super.setPadding(i2, i3, i4, i5);
    }

    public void setTabIndicatorColor(@android.support.annotation.k int i2) {
        this.o = i2;
        this.u.setColor(this.o);
        invalidate();
    }

    public void setTabIndicatorColorResource(@android.support.annotation.m int i2) {
        setTabIndicatorColor(android.support.v4.content.c.getColor(getContext(), i2));
    }

    @Override // android.support.v4.view.PagerTitleStrip
    public void setTextSpacing(int i2) {
        int i3 = this.r;
        if (i2 < i3) {
            i2 = i3;
        }
        super.setTextSpacing(i2);
    }
}
