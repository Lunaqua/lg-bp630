package kankan.wheel.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.ActivityChooserView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class WheelScroller {
    public static final int a = 1;
    private static final int b = 400;
    private ScrollingListener c;
    private Context d;
    private GestureDetector e;
    private Scroller f;
    private int g;
    private float h;
    private boolean i;
    private GestureDetector.SimpleOnGestureListener j = new GestureDetector.SimpleOnGestureListener() { // from class: kankan.wheel.widget.WheelScroller.1
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            WheelScroller.this.g = 0;
            WheelScroller.this.f.fling(0, WheelScroller.this.g, 0, (int) (-f2), 0, 0, -2147483647, ActivityChooserView.a.a);
            WheelScroller.this.a(0);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return true;
        }
    };
    private final int k = 0;
    private final int l = 1;
    private Handler m = new Handler() { // from class: kankan.wheel.widget.WheelScroller.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WheelScroller.this.f.computeScrollOffset();
            int currY = WheelScroller.this.f.getCurrY();
            int i = WheelScroller.this.g - currY;
            WheelScroller.this.g = currY;
            if (i != 0) {
                WheelScroller.this.c.a(i);
            }
            if (Math.abs(currY - WheelScroller.this.f.getFinalY()) < 1) {
                WheelScroller.this.f.getFinalY();
                WheelScroller.this.f.forceFinished(true);
            }
            if (!WheelScroller.this.f.isFinished()) {
                WheelScroller.this.m.sendEmptyMessage(message.what);
            } else if (message.what == 0) {
                WheelScroller.this.d();
            } else {
                WheelScroller.this.b();
            }
        }
    };

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface ScrollingListener {
        void a();

        void a(int i);

        void b();

        void c();
    }

    public WheelScroller(Context context, ScrollingListener scrollingListener) {
        this.e = new GestureDetector(context, this.j);
        this.e.setIsLongpressEnabled(false);
        this.f = new Scroller(context);
        this.c = scrollingListener;
        this.d = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        c();
        this.m.sendEmptyMessage(i);
    }

    private void c() {
        this.m.removeMessages(0);
        this.m.removeMessages(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.c.c();
        a(1);
    }

    private void e() {
        if (this.i) {
            return;
        }
        this.i = true;
        this.c.a();
    }

    public void a() {
        this.f.forceFinished(true);
    }

    public void a(int i, int i2) {
        this.f.forceFinished(true);
        this.g = 0;
        this.f.startScroll(0, 0, 0, i, i2 != 0 ? i2 : b);
        a(0);
        e();
    }

    public void a(Interpolator interpolator) {
        this.f.forceFinished(true);
        this.f = new Scroller(this.d, interpolator);
    }

    public boolean a(MotionEvent motionEvent) {
        int y;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.h = motionEvent.getY();
            this.f.forceFinished(true);
            c();
        } else if (action == 2 && (y = (int) (motionEvent.getY() - this.h)) != 0) {
            e();
            this.c.a(y);
            this.h = motionEvent.getY();
        }
        if (!this.e.onTouchEvent(motionEvent) && motionEvent.getAction() == 1) {
            d();
        }
        return true;
    }

    void b() {
        if (this.i) {
            this.c.b();
            this.i = false;
        }
    }
}
