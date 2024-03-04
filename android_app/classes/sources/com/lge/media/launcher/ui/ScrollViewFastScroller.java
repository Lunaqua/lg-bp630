package com.lge.media.launcher.ui;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.af;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import com.lge.media.launcher.b;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ScrollViewFastScroller extends LinearLayout {
    private static final int a = 5;
    private View b;
    private NestedScrollView c;
    private final a d;
    private int e;
    private ObjectAnimator f;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class a implements ViewTreeObserver.OnScrollChangedListener {
        private a() {
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        @SuppressLint({"RestrictedApi"})
        public void onScrollChanged() {
            if (ScrollViewFastScroller.this.b.isSelected()) {
                return;
            }
            float computeVerticalScrollRange = ScrollViewFastScroller.this.c.computeVerticalScrollRange() - ScrollViewFastScroller.this.c.computeVerticalScrollExtent();
            ScrollViewFastScroller scrollViewFastScroller = ScrollViewFastScroller.this;
            scrollViewFastScroller.setBubbleAndHandlePosition((scrollViewFastScroller.e * ScrollViewFastScroller.this.c.computeVerticalScrollOffset()) / computeVerticalScrollRange);
        }
    }

    public ScrollViewFastScroller(Context context) {
        super(context);
        this.d = new a();
        this.f = null;
        a(context);
    }

    public ScrollViewFastScroller(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new a();
        this.f = null;
        a(context);
    }

    public ScrollViewFastScroller(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new a();
        this.f = null;
        a(context);
    }

    private int a(int i, int i2, int i3) {
        return Math.min(Math.max(i, i3), i2);
    }

    private void a(Context context) {
        setOrientation(0);
        setClipChildren(false);
        LayoutInflater.from(context).inflate(b.j.recycler_view_fast_scroller, (ViewGroup) this, true);
        this.b = findViewById(b.h.fastscroller_handle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBubbleAndHandlePosition(float f) {
        int height = this.b.getHeight();
        this.b.setY(a(0, this.e - height, (int) (f - (height / 2))));
    }

    private void setRecyclerViewPosition(int i) {
        if (this.c != null) {
            float f = 0.0f;
            if (this.b.getY() != 0.0f) {
                float y = this.b.getY() + this.b.getHeight();
                int i2 = this.e;
                f = y >= ((float) (i2 + (-5))) ? 1.0f : i / i2;
            }
            NestedScrollView nestedScrollView = this.c;
            nestedScrollView.scrollTo(0, Math.round(f * nestedScrollView.getChildAt(0).getHeight()));
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.e = i2;
    }

    @Override // android.view.View
    public boolean onTouchEvent(@af MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action != 3) {
                        return super.onTouchEvent(motionEvent);
                    }
                }
            }
            this.b.setSelected(false);
            return true;
        } else if (motionEvent.getX() < this.b.getX()) {
            return false;
        } else {
            ObjectAnimator objectAnimator = this.f;
            if (objectAnimator != null) {
                objectAnimator.cancel();
            }
            this.b.setSelected(true);
        }
        float y = motionEvent.getY();
        setBubbleAndHandlePosition(y);
        setRecyclerViewPosition((int) y);
        return true;
    }

    public void setScrollView(NestedScrollView nestedScrollView) {
        this.c = nestedScrollView;
        this.c.getViewTreeObserver().addOnScrollChangedListener(this.d);
    }
}
