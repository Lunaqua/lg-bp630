package com.lge.media.launcher.cplist.gridview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class CPGridView extends DragGridView {
    public GridView a;
    AdapterView.OnItemLongClickListener b;

    public CPGridView(Context context) {
        super(context);
        this.b = new AdapterView.OnItemLongClickListener() { // from class: com.lge.media.launcher.cplist.gridview.CPGridView.1
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i <= -1) {
                    return true;
                }
                CPGridView cPGridView = CPGridView.this;
                cPGridView.d = i;
                cPGridView.b();
                return true;
            }
        };
        setOnItemLongClickListener(this.b);
    }

    public CPGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new AdapterView.OnItemLongClickListener() { // from class: com.lge.media.launcher.cplist.gridview.CPGridView.1
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i <= -1) {
                    return true;
                }
                CPGridView cPGridView = CPGridView.this;
                cPGridView.d = i;
                cPGridView.b();
                return true;
            }
        };
        setOnItemLongClickListener(this.b);
    }

    public CPGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new AdapterView.OnItemLongClickListener() { // from class: com.lge.media.launcher.cplist.gridview.CPGridView.1
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i2, long j) {
                if (i2 <= -1) {
                    return true;
                }
                CPGridView cPGridView = CPGridView.this;
                cPGridView.d = i2;
                cPGridView.b();
                return true;
            }
        };
        setOnItemLongClickListener(this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.m = getChildAt(this.d - getFirstVisiblePosition());
        this.m.setDrawingCacheEnabled(true);
        if (!this.n) {
            this.m.setVisibility(4);
        }
        Bitmap createBitmap = Bitmap.createBitmap(this.m.getDrawingCache());
        if (!createBitmap.isMutable()) {
            createBitmap = createBitmap.copy(Bitmap.Config.ARGB_8888, true);
        }
        new Canvas(createBitmap).drawColor(-1308622848, PorterDuff.Mode.DST_IN);
        ImageView imageView = new ImageView(this.c);
        imageView.setImageBitmap(createBitmap);
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        Matrix matrix = new Matrix();
        matrix.postScale(1.2f, 1.2f);
        imageView.setImageMatrix(matrix);
        Point point = this.i;
        double width = createBitmap.getWidth();
        Double.isNaN(width);
        point.x = (int) (width * 0.5d);
        Point point2 = this.i;
        double height = createBitmap.getHeight();
        Double.isNaN(height);
        point2.y = (int) (height * 0.8d);
        this.g.x = this.h.x - this.i.x;
        this.g.y = this.h.y + this.i.y + getTop();
        this.f.addView(imageView, this.g);
        this.e = imageView;
        this.k.a();
    }

    public void a() {
        if (this.e != null) {
            this.f.removeView(this.e);
            this.l.a();
            if (!this.n) {
                this.m.setVisibility(0);
            }
            this.e = null;
        }
    }

    public void a(int i, int i2) {
        if (this.e == null) {
            return;
        }
        this.g.x = i - this.i.x;
        this.g.y = i2 + this.i.y + getTop();
        this.f.updateViewLayout(this.e, this.g);
    }

    public void b(int i, int i2) {
        int top = i2 + getTop();
        if (this.e == null) {
            return;
        }
        if (top < this.a.getBottom() && top > this.a.getTop()) {
            this.j.a(this.d, i);
        }
        a();
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 1) {
            b(x, y);
        } else if (action == 2) {
            a(x, y);
        } else if (action == 3) {
            a();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setDropView(GridView gridView) {
        this.a = gridView;
    }
}
