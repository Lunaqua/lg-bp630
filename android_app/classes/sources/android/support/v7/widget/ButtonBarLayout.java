package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ButtonBarLayout extends LinearLayout {
    private static final int a = 16;
    private boolean b;
    private int c;
    private int d;

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = -1;
        this.d = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.l.ButtonBarLayout);
        this.b = obtainStyledAttributes.getBoolean(a.l.ButtonBarLayout_allowStacking, true);
        obtainStyledAttributes.recycle();
    }

    private int a(int i) {
        int childCount = getChildCount();
        while (i < childCount) {
            if (getChildAt(i).getVisibility() == 0) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private boolean a() {
        return getOrientation() == 1;
    }

    private void setStacked(boolean z) {
        setOrientation(z ? 1 : 0);
        setGravity(z ? 5 : 80);
        View findViewById = findViewById(a.g.spacer);
        if (findViewById != null) {
            findViewById.setVisibility(z ? 8 : 4);
        }
        for (int childCount = getChildCount() - 2; childCount >= 0; childCount--) {
            bringChildToFront(getChildAt(childCount));
        }
    }

    @Override // android.view.View
    public int getMinimumHeight() {
        return Math.max(this.d, super.getMinimumHeight());
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        boolean z;
        int size = View.MeasureSpec.getSize(i);
        int i4 = 0;
        if (this.b) {
            if (size > this.c && a()) {
                setStacked(false);
            }
            this.c = size;
        }
        if (a() || View.MeasureSpec.getMode(i) != 1073741824) {
            i3 = i;
            z = false;
        } else {
            i3 = View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            z = true;
        }
        super.onMeasure(i3, i2);
        if (this.b && !a()) {
            if ((getMeasuredWidthAndState() & android.support.v4.view.ab.s) == 16777216) {
                setStacked(true);
                z = true;
            }
        }
        if (z) {
            super.onMeasure(i, i2);
        }
        int a2 = a(0);
        if (a2 >= 0) {
            View childAt = getChildAt(a2);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            int paddingTop = getPaddingTop() + childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin + 0;
            if (a()) {
                int a3 = a(a2 + 1);
                if (a3 >= 0) {
                    paddingTop += getChildAt(a3).getPaddingTop() + ((int) (getResources().getDisplayMetrics().density * 16.0f));
                }
                i4 = paddingTop;
            } else {
                i4 = paddingTop + getPaddingBottom();
            }
        }
        if (android.support.v4.view.ab.B(this) != i4) {
            setMinimumHeight(i4);
        }
    }

    public void setAllowStacking(boolean z) {
        if (this.b != z) {
            this.b = z;
            if (!this.b && getOrientation() == 1) {
                setStacked(false);
            }
            requestLayout();
        }
    }
}
