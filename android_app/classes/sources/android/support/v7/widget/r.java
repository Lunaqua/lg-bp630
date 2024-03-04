package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class r extends RecyclerView.h {
    public static final int a = 0;
    public static final int b = 1;
    private static final String c = "DividerItem";
    private static final int[] d = {16843284};
    private Drawable e;
    private int f;
    private final Rect g = new Rect();

    public r(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(d);
        this.e = obtainStyledAttributes.getDrawable(0);
        if (this.e == null) {
            Log.w(c, "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        obtainStyledAttributes.recycle();
        a(i);
    }

    private void c(Canvas canvas, RecyclerView recyclerView) {
        int width;
        int i;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            i = recyclerView.getPaddingLeft();
            width = recyclerView.getWidth() - recyclerView.getPaddingRight();
            canvas.clipRect(i, recyclerView.getPaddingTop(), width, recyclerView.getHeight() - recyclerView.getPaddingBottom());
        } else {
            width = recyclerView.getWidth();
            i = 0;
        }
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            recyclerView.a(childAt, this.g);
            int round = this.g.bottom + Math.round(childAt.getTranslationY());
            this.e.setBounds(i, round - this.e.getIntrinsicHeight(), width, round);
            this.e.draw(canvas);
        }
        canvas.restore();
    }

    private void d(Canvas canvas, RecyclerView recyclerView) {
        int height;
        int i;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            i = recyclerView.getPaddingTop();
            height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
            canvas.clipRect(recyclerView.getPaddingLeft(), i, recyclerView.getWidth() - recyclerView.getPaddingRight(), height);
        } else {
            height = recyclerView.getHeight();
            i = 0;
        }
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            recyclerView.getLayoutManager().a(childAt, this.g);
            int round = this.g.right + Math.round(childAt.getTranslationX());
            this.e.setBounds(round - this.e.getIntrinsicWidth(), i, round, height);
            this.e.draw(canvas);
        }
        canvas.restore();
    }

    public void a(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        this.f = i;
    }

    @Override // android.support.v7.widget.RecyclerView.h
    public void a(Canvas canvas, RecyclerView recyclerView, RecyclerView.u uVar) {
        if (recyclerView.getLayoutManager() == null || this.e == null) {
            return;
        }
        if (this.f == 1) {
            c(canvas, recyclerView);
        } else {
            d(canvas, recyclerView);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.h
    public void a(Rect rect, View view, RecyclerView recyclerView, RecyclerView.u uVar) {
        Drawable drawable = this.e;
        if (drawable == null) {
            rect.set(0, 0, 0, 0);
        } else if (this.f == 1) {
            rect.set(0, 0, 0, drawable.getIntrinsicHeight());
        } else {
            rect.set(0, 0, drawable.getIntrinsicWidth(), 0);
        }
    }

    public void a(@android.support.annotation.af Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        }
        this.e = drawable;
    }
}
