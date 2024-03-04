package com.lge.media.launcher.control.common;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c extends RecyclerView.h {
    private Drawable a;

    public c(Drawable drawable) {
        this.a = drawable;
    }

    @Override // android.support.v7.widget.RecyclerView.h
    public void b(Canvas canvas, RecyclerView recyclerView, RecyclerView.u uVar) {
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i <= childCount - 2; i++) {
            View childAt = recyclerView.getChildAt(i);
            int bottom = childAt.getBottom() + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            this.a.setBounds(paddingLeft, bottom, width, this.a.getIntrinsicHeight() + bottom);
            this.a.draw(canvas);
        }
    }
}
