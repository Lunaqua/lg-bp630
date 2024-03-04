package android.support.v4.view;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class e {
    private final a a;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    interface a {
        void a(GestureDetector.OnDoubleTapListener onDoubleTapListener);

        void a(boolean z);

        boolean a();

        boolean a(MotionEvent motionEvent);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static class b implements a {
        private static final int j = ViewConfiguration.getLongPressTimeout();
        private static final int k = ViewConfiguration.getTapTimeout();
        private static final int l = ViewConfiguration.getDoubleTapTimeout();
        private static final int m = 1;
        private static final int n = 2;
        private static final int o = 3;
        private VelocityTracker A;
        final GestureDetector.OnGestureListener a;
        GestureDetector.OnDoubleTapListener b;
        boolean c;
        boolean d;
        MotionEvent e;
        private int f;
        private int g;
        private int h;
        private int i;
        private final Handler p;
        private boolean q;
        private boolean r;
        private boolean s;
        private MotionEvent t;
        private boolean u;
        private float v;
        private float w;
        private float x;
        private float y;
        private boolean z;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        private class a extends Handler {
            a() {
            }

            a(Handler handler) {
                super(handler.getLooper());
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    b.this.a.onShowPress(b.this.e);
                } else if (i == 2) {
                    b.this.b();
                } else if (i != 3) {
                    throw new RuntimeException("Unknown message " + message);
                } else if (b.this.b != null) {
                    if (b.this.c) {
                        b.this.d = true;
                    } else {
                        b.this.b.onSingleTapConfirmed(b.this.e);
                    }
                }
            }
        }

        b(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            if (handler != null) {
                this.p = new a(handler);
            } else {
                this.p = new a();
            }
            this.a = onGestureListener;
            if (onGestureListener instanceof GestureDetector.OnDoubleTapListener) {
                a((GestureDetector.OnDoubleTapListener) onGestureListener);
            }
            a(context);
        }

        private void a(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null");
            }
            if (this.a == null) {
                throw new IllegalArgumentException("OnGestureListener must not be null");
            }
            this.z = true;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            int scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
            int scaledDoubleTapSlop = viewConfiguration.getScaledDoubleTapSlop();
            this.h = viewConfiguration.getScaledMinimumFlingVelocity();
            this.i = viewConfiguration.getScaledMaximumFlingVelocity();
            this.f = scaledTouchSlop * scaledTouchSlop;
            this.g = scaledDoubleTapSlop * scaledDoubleTapSlop;
        }

        private boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, MotionEvent motionEvent3) {
            if (this.s && motionEvent3.getEventTime() - motionEvent2.getEventTime() <= l) {
                int x = ((int) motionEvent.getX()) - ((int) motionEvent3.getX());
                int y = ((int) motionEvent.getY()) - ((int) motionEvent3.getY());
                return (x * x) + (y * y) < this.g;
            }
            return false;
        }

        private void c() {
            this.p.removeMessages(1);
            this.p.removeMessages(2);
            this.p.removeMessages(3);
            this.A.recycle();
            this.A = null;
            this.u = false;
            this.c = false;
            this.r = false;
            this.s = false;
            this.d = false;
            if (this.q) {
                this.q = false;
            }
        }

        private void d() {
            this.p.removeMessages(1);
            this.p.removeMessages(2);
            this.p.removeMessages(3);
            this.u = false;
            this.r = false;
            this.s = false;
            this.d = false;
            if (this.q) {
                this.q = false;
            }
        }

        @Override // android.support.v4.view.e.a
        public void a(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
            this.b = onDoubleTapListener;
        }

        @Override // android.support.v4.view.e.a
        public void a(boolean z) {
            this.z = z;
        }

        @Override // android.support.v4.view.e.a
        public boolean a() {
            return this.z;
        }

        /* JADX WARN: Removed duplicated region for block: B:107:0x0208  */
        /* JADX WARN: Removed duplicated region for block: B:110:0x021f  */
        @Override // android.support.v4.view.e.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean a(android.view.MotionEvent r13) {
            /*
                Method dump skipped, instructions count: 591
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.e.b.a(android.view.MotionEvent):boolean");
        }

        void b() {
            this.p.removeMessages(3);
            this.d = false;
            this.q = true;
            this.a.onLongPress(this.e);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static class c implements a {
        private final GestureDetector a;

        c(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            this.a = new GestureDetector(context, onGestureListener, handler);
        }

        @Override // android.support.v4.view.e.a
        public void a(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
            this.a.setOnDoubleTapListener(onDoubleTapListener);
        }

        @Override // android.support.v4.view.e.a
        public void a(boolean z) {
            this.a.setIsLongpressEnabled(z);
        }

        @Override // android.support.v4.view.e.a
        public boolean a() {
            return this.a.isLongpressEnabled();
        }

        @Override // android.support.v4.view.e.a
        public boolean a(MotionEvent motionEvent) {
            return this.a.onTouchEvent(motionEvent);
        }
    }

    public e(Context context, GestureDetector.OnGestureListener onGestureListener) {
        this(context, onGestureListener, null);
    }

    public e(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
        this.a = Build.VERSION.SDK_INT > 17 ? new c(context, onGestureListener, handler) : new b(context, onGestureListener, handler);
    }

    public void a(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.a.a(onDoubleTapListener);
    }

    public void a(boolean z) {
        this.a.a(z);
    }

    public boolean a() {
        return this.a.a();
    }

    public boolean a(MotionEvent motionEvent) {
        return this.a.a(motionEvent);
    }
}
