package android.support.r.a;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class b {
    private final View a;
    private final a b;
    private int c;
    private int d;
    private boolean e;
    private final View.OnLongClickListener f = new View.OnLongClickListener() { // from class: android.support.r.a.b.1
        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            return b.this.a(view);
        }
    };
    private final View.OnTouchListener g = new View.OnTouchListener() { // from class: android.support.r.a.b.2
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return b.this.a(view, motionEvent);
        }
    };

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        boolean a(View view, b bVar);
    }

    public b(View view, a aVar) {
        this.a = view;
        this.b = aVar;
    }

    public void a() {
        this.a.setOnLongClickListener(this.f);
        this.a.setOnTouchListener(this.g);
    }

    public void a(Point point) {
        point.set(this.c, this.d);
    }

    public boolean a(View view) {
        return this.b.a(view, this);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0018, code lost:
        if (r2 != 3) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(android.view.View r7, android.view.MotionEvent r8) {
        /*
            r6 = this;
            float r0 = r8.getX()
            int r0 = (int) r0
            float r1 = r8.getY()
            int r1 = (int) r1
            int r2 = r8.getAction()
            r3 = 0
            if (r2 == 0) goto L4b
            r4 = 1
            if (r2 == r4) goto L48
            r5 = 2
            if (r2 == r5) goto L1b
            r7 = 3
            if (r2 == r7) goto L48
            goto L4f
        L1b:
            r2 = 8194(0x2002, float:1.1482E-41)
            boolean r2 = android.support.v4.view.n.e(r8, r2)
            if (r2 == 0) goto L4f
            int r8 = r8.getButtonState()
            r8 = r8 & r4
            if (r8 != 0) goto L2b
            goto L4f
        L2b:
            boolean r8 = r6.e
            if (r8 == 0) goto L30
            goto L4f
        L30:
            int r8 = r6.c
            if (r8 != r0) goto L39
            int r8 = r6.d
            if (r8 != r1) goto L39
            goto L4f
        L39:
            r6.c = r0
            r6.d = r1
            android.support.r.a.b$a r8 = r6.b
            boolean r7 = r8.a(r7, r6)
            r6.e = r7
            boolean r7 = r6.e
            return r7
        L48:
            r6.e = r3
            goto L4f
        L4b:
            r6.c = r0
            r6.d = r1
        L4f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.r.a.b.a(android.view.View, android.view.MotionEvent):boolean");
    }

    public void b() {
        this.a.setOnLongClickListener(null);
        this.a.setOnTouchListener(null);
    }
}
