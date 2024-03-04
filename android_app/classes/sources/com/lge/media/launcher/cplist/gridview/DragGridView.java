package com.lge.media.launcher.cplist.gridview;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class DragGridView extends GridView {
    public Context c;
    public int d;
    public ImageView e;
    public WindowManager f;
    public WindowManager.LayoutParams g;
    public Point h;
    public Point i;
    public b j;
    public d k;
    public c l;
    public View m;
    public boolean n;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        void a(int i, int i2);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface b {
        void a(int i, int i2);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface c {
        void a();
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface d {
        void a();
    }

    public DragGridView(Context context) {
        super(context);
        this.c = null;
        this.d = -1;
        this.h = new Point(0, 0);
        this.i = new Point(0, 0);
        this.n = true;
        a(context);
    }

    public DragGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = null;
        this.d = -1;
        this.h = new Point(0, 0);
        this.i = new Point(0, 0);
        this.n = true;
        a(context);
    }

    public DragGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = null;
        this.d = -1;
        this.h = new Point(0, 0);
        this.i = new Point(0, 0);
        this.n = true;
        a(context);
    }

    private void a(Context context) {
        this.c = context;
        this.f = (WindowManager) context.getSystemService("window");
        this.g = new WindowManager.LayoutParams();
        WindowManager.LayoutParams layoutParams = this.g;
        layoutParams.gravity = 51;
        layoutParams.height = -2;
        layoutParams.width = -2;
        layoutParams.format = -3;
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (motionEvent.getAction() == 0) {
            this.h.set(x, y);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setDragItemVisible(boolean z) {
        this.n = z;
    }

    public void setOnDropListener(b bVar) {
        this.j = bVar;
    }

    public void setOnEndDragListener(c cVar) {
        this.l = cVar;
    }

    public void setOnStartDragListener(d dVar) {
        this.k = dVar;
    }
}
