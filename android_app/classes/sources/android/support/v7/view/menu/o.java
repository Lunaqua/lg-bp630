package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.annotation.ar;
import android.support.v4.view.ab;
import android.support.v7.a.a;
import android.support.v7.view.menu.p;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class o implements j {
    private static final int a = 48;
    private final Context b;
    private final h c;
    private final boolean d;
    private final int e;
    private final int f;
    private View g;
    private int h;
    private boolean i;
    private p.a j;
    private n k;
    private PopupWindow.OnDismissListener l;
    private final PopupWindow.OnDismissListener m;

    public o(@af Context context, @af h hVar) {
        this(context, hVar, null, false, a.b.popupMenuStyle, 0);
    }

    public o(@af Context context, @af h hVar, @af View view) {
        this(context, hVar, view, false, a.b.popupMenuStyle, 0);
    }

    public o(@af Context context, @af h hVar, @af View view, boolean z, @android.support.annotation.f int i) {
        this(context, hVar, view, z, i, 0);
    }

    public o(@af Context context, @af h hVar, @af View view, boolean z, @android.support.annotation.f int i, @ar int i2) {
        this.h = android.support.v4.view.f.b;
        this.m = new PopupWindow.OnDismissListener() { // from class: android.support.v7.view.menu.o.1
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                o.this.f();
            }
        };
        this.b = context;
        this.c = hVar;
        this.g = view;
        this.d = z;
        this.e = i;
        this.f = i2;
    }

    private void a(int i, int i2, boolean z, boolean z2) {
        n d = d();
        d.c(z2);
        if (z) {
            if ((android.support.v4.view.f.a(this.h, ab.m(this.g)) & 7) == 5) {
                i -= this.g.getWidth();
            }
            d.b(i);
            d.c(i2);
            int i3 = (int) ((this.b.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            d.a(new Rect(i - i3, i2 - i3, i + i3, i2 + i3));
        }
        d.a();
    }

    @af
    private n i() {
        Display defaultDisplay = ((WindowManager) this.b.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        n eVar = Math.min(point.x, point.y) >= this.b.getResources().getDimensionPixelSize(a.e.abc_cascading_menus_min_smallest_width) ? new e(this.b, this.g, this.e, this.f, this.d) : new u(this.b, this.c, this.g, this.e, this.f, this.d);
        eVar.a(this.c);
        eVar.a(this.m);
        eVar.a(this.g);
        eVar.a(this.j);
        eVar.a(this.i);
        eVar.a(this.h);
        return eVar;
    }

    @Override // android.support.v7.view.menu.j
    public void a() {
        if (g()) {
            this.k.d();
        }
    }

    public void a(int i) {
        this.h = i;
    }

    public void a(int i, int i2) {
        if (!b(i, i2)) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    @Override // android.support.v7.view.menu.j
    public void a(@ag p.a aVar) {
        this.j = aVar;
        n nVar = this.k;
        if (nVar != null) {
            nVar.a(aVar);
        }
    }

    public void a(@af View view) {
        this.g = view;
    }

    public void a(@ag PopupWindow.OnDismissListener onDismissListener) {
        this.l = onDismissListener;
    }

    public void a(boolean z) {
        this.i = z;
        n nVar = this.k;
        if (nVar != null) {
            nVar.a(z);
        }
    }

    public int b() {
        return this.h;
    }

    public boolean b(int i, int i2) {
        if (g()) {
            return true;
        }
        if (this.g == null) {
            return false;
        }
        a(i, i2, true, true);
        return true;
    }

    public void c() {
        if (!e()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    @af
    public n d() {
        if (this.k == null) {
            this.k = i();
        }
        return this.k;
    }

    public boolean e() {
        if (g()) {
            return true;
        }
        if (this.g == null) {
            return false;
        }
        a(0, 0, false, false);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void f() {
        this.k = null;
        PopupWindow.OnDismissListener onDismissListener = this.l;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public boolean g() {
        n nVar = this.k;
        return nVar != null && nVar.e();
    }

    public ListView h() {
        return d().g();
    }
}
