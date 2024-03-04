package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.support.v7.a.a;
import android.support.v7.widget.c;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class am extends android.support.v4.view.b {
    public static final String a = "share_history.xml";
    private static final int e = 4;
    final Context b;
    String c;
    a d;
    private int f;
    private final c g;
    private c.f h;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        boolean a(am amVar, Intent intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class b implements c.f {
        b() {
        }

        @Override // android.support.v7.widget.c.f
        public boolean a(android.support.v7.widget.c cVar, Intent intent) {
            if (am.this.d != null) {
                am.this.d.a(am.this, intent);
                return false;
            }
            return false;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class c implements MenuItem.OnMenuItemClickListener {
        c() {
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            Intent b = android.support.v7.widget.c.a(am.this.b, am.this.c).b(menuItem.getItemId());
            if (b != null) {
                String action = b.getAction();
                if ("android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action)) {
                    am.this.b(b);
                }
                am.this.b.startActivity(b);
                return true;
            }
            return true;
        }
    }

    public am(Context context) {
        super(context);
        this.f = 4;
        this.g = new c();
        this.c = a;
        this.b = context;
    }

    private void i() {
        if (this.d == null) {
            return;
        }
        if (this.h == null) {
            this.h = new b();
        }
        android.support.v7.widget.c.a(this.b, this.c).a(this.h);
    }

    public void a(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if ("android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action)) {
                b(intent);
            }
        }
        android.support.v7.widget.c.a(this.b, this.c).a(intent);
    }

    public void a(a aVar) {
        this.d = aVar;
        i();
    }

    @Override // android.support.v4.view.b
    public void a(SubMenu subMenu) {
        subMenu.clear();
        android.support.v7.widget.c a2 = android.support.v7.widget.c.a(this.b, this.c);
        PackageManager packageManager = this.b.getPackageManager();
        int b2 = a2.b();
        int min = Math.min(b2, this.f);
        for (int i = 0; i < min; i++) {
            ResolveInfo a3 = a2.a(i);
            subMenu.add(0, i, i, a3.loadLabel(packageManager)).setIcon(a3.loadIcon(packageManager)).setOnMenuItemClickListener(this.g);
        }
        if (min < b2) {
            SubMenu addSubMenu = subMenu.addSubMenu(0, min, min, this.b.getString(a.j.abc_activity_chooser_view_see_all));
            for (int i2 = 0; i2 < b2; i2++) {
                ResolveInfo a4 = a2.a(i2);
                addSubMenu.add(0, i2, i2, a4.loadLabel(packageManager)).setIcon(a4.loadIcon(packageManager)).setOnMenuItemClickListener(this.g);
            }
        }
    }

    public void a(String str) {
        this.c = str;
        i();
    }

    @Override // android.support.v4.view.b
    public View b() {
        ActivityChooserView activityChooserView = new ActivityChooserView(this.b);
        if (!activityChooserView.isInEditMode()) {
            activityChooserView.setActivityChooserModel(android.support.v7.widget.c.a(this.b, this.c));
        }
        TypedValue typedValue = new TypedValue();
        this.b.getTheme().resolveAttribute(a.b.actionModeShareDrawable, typedValue, true);
        activityChooserView.setExpandActivityOverflowButtonDrawable(android.support.v7.b.a.a.b(this.b, typedValue.resourceId));
        activityChooserView.setProvider(this);
        activityChooserView.setDefaultActionButtonContentDescription(a.j.abc_shareactionprovider_share_with_application);
        activityChooserView.setExpandActivityOverflowButtonContentDescription(a.j.abc_shareactionprovider_share_with);
        return activityChooserView;
    }

    void b(Intent intent) {
        intent.addFlags(Build.VERSION.SDK_INT >= 21 ? 134742016 : 524288);
    }

    @Override // android.support.v4.view.b
    public boolean g() {
        return true;
    }
}
