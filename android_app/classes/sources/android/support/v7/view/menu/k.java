package android.support.v7.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.v4.view.b;
import android.support.v7.a.a;
import android.support.v7.view.menu.q;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.LinearLayout;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class k implements android.support.v4.d.a.b {
    private static final int H = 1;
    private static final int I = 2;
    private static final int J = 4;
    private static final int K = 8;
    private static final int L = 16;
    private static final int M = 32;
    static final int g = 0;
    private static final String h = "MenuItemImpl";
    private static final int i = 3;
    private CharSequence A;
    private int N;
    private View O;
    private android.support.v4.view.b P;
    private MenuItem.OnActionExpandListener Q;
    private ContextMenu.ContextMenuInfo S;
    h f;
    private final int j;
    private final int k;
    private final int l;
    private final int m;
    private CharSequence n;
    private CharSequence o;
    private Intent p;
    private char q;
    private char s;
    private Drawable u;
    private v w;
    private Runnable x;
    private MenuItem.OnMenuItemClickListener y;
    private CharSequence z;
    private int r = 4096;
    private int t = 4096;
    private int v = 0;
    private ColorStateList B = null;
    private PorterDuff.Mode C = null;
    private boolean D = false;
    private boolean E = false;
    private boolean F = false;
    private int G = 16;
    private boolean R = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(h hVar, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6) {
        this.N = 0;
        this.f = hVar;
        this.j = i3;
        this.k = i2;
        this.l = i4;
        this.m = i5;
        this.n = charSequence;
        this.N = i6;
    }

    private Drawable a(Drawable drawable) {
        if (drawable != null && this.F && (this.D || this.E)) {
            drawable = android.support.v4.graphics.drawable.a.g(drawable).mutate();
            if (this.D) {
                android.support.v4.graphics.drawable.a.a(drawable, this.B);
            }
            if (this.E) {
                android.support.v4.graphics.drawable.a.a(drawable, this.C);
            }
            this.F = false;
        }
        return drawable;
    }

    private static void a(StringBuilder sb, int i2, int i3, String str) {
        if ((i2 & i3) == i3) {
            sb.append(str);
        }
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    /* renamed from: a */
    public android.support.v4.d.a.b setActionView(int i2) {
        Context g2 = this.f.g();
        setActionView(LayoutInflater.from(g2).inflate(i2, (ViewGroup) new LinearLayout(g2), false));
        return this;
    }

    @Override // android.support.v4.d.a.b
    public android.support.v4.d.a.b a(android.support.v4.view.b bVar) {
        android.support.v4.view.b bVar2 = this.P;
        if (bVar2 != null) {
            bVar2.h();
        }
        this.O = null;
        this.P = bVar;
        this.f.c(true);
        android.support.v4.view.b bVar3 = this.P;
        if (bVar3 != null) {
            bVar3.a(new b.InterfaceC0033b() { // from class: android.support.v7.view.menu.k.1
                @Override // android.support.v4.view.b.InterfaceC0033b
                public void a(boolean z) {
                    k.this.f.a(k.this);
                }
            });
        }
        return this;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    /* renamed from: a */
    public android.support.v4.d.a.b setActionView(View view) {
        int i2;
        this.O = view;
        this.P = null;
        if (view != null && view.getId() == -1 && (i2 = this.j) > 0) {
            view.setId(i2);
        }
        this.f.b(this);
        return this;
    }

    @Override // android.view.MenuItem
    /* renamed from: a */
    public android.support.v4.d.a.b setContentDescription(CharSequence charSequence) {
        this.z = charSequence;
        this.f.c(false);
        return this;
    }

    @Override // android.support.v4.d.a.b
    public android.support.v4.view.b a() {
        return this.P;
    }

    public MenuItem a(Runnable runnable) {
        this.x = runnable;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharSequence a(q.a aVar) {
        return (aVar == null || !aVar.a()) ? getTitle() : getTitleCondensed();
    }

    public void a(v vVar) {
        this.w = vVar;
        vVar.setHeaderTitle(getTitle());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.S = contextMenuInfo;
    }

    public void a(boolean z) {
        this.G = (z ? 4 : 0) | (this.G & (-5));
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    /* renamed from: b */
    public android.support.v4.d.a.b setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    @Override // android.view.MenuItem
    /* renamed from: b */
    public android.support.v4.d.a.b setTooltipText(CharSequence charSequence) {
        this.A = charSequence;
        this.f.c(false);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(boolean z) {
        int i2 = this.G;
        this.G = (z ? 2 : 0) | (i2 & (-3));
        if (i2 != this.G) {
            this.f.c(false);
        }
    }

    public boolean b() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.y;
        if (onMenuItemClickListener == null || !onMenuItemClickListener.onMenuItemClick(this)) {
            h hVar = this.f;
            if (hVar.a(hVar, this)) {
                return true;
            }
            Runnable runnable = this.x;
            if (runnable != null) {
                runnable.run();
                return true;
            }
            if (this.p != null) {
                try {
                    this.f.g().startActivity(this.p);
                    return true;
                } catch (ActivityNotFoundException e) {
                    Log.e(h, "Can't find activity to handle intent; ignoring", e);
                }
            }
            android.support.v4.view.b bVar = this.P;
            return bVar != null && bVar.f();
        }
        return true;
    }

    public int c() {
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(boolean z) {
        int i2 = this.G;
        this.G = (z ? 0 : 8) | (i2 & (-9));
        return i2 != this.G;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public boolean collapseActionView() {
        if ((this.N & 8) == 0) {
            return false;
        }
        if (this.O == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.Q;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.f.d(this);
        }
        return false;
    }

    Runnable d() {
        return this.x;
    }

    public void d(boolean z) {
        this.G = z ? this.G | 32 : this.G & (-33);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public char e() {
        return this.f.d() ? this.s : this.q;
    }

    public void e(boolean z) {
        this.R = z;
        this.f.c(false);
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public boolean expandActionView() {
        if (o()) {
            MenuItem.OnActionExpandListener onActionExpandListener = this.Q;
            if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
                return this.f.c(this);
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String f() {
        int i2;
        char e = e();
        if (e == 0) {
            return com.lge.media.launcher.a.d;
        }
        Resources resources = this.f.g().getResources();
        StringBuilder sb = new StringBuilder();
        if (ViewConfiguration.get(this.f.g()).hasPermanentMenuKey()) {
            sb.append(resources.getString(a.j.abc_prepend_shortcut_label));
        }
        int i3 = this.f.d() ? this.t : this.r;
        a(sb, i3, 65536, resources.getString(a.j.abc_menu_meta_shortcut_label));
        a(sb, i3, 4096, resources.getString(a.j.abc_menu_ctrl_shortcut_label));
        a(sb, i3, 2, resources.getString(a.j.abc_menu_alt_shortcut_label));
        a(sb, i3, 1, resources.getString(a.j.abc_menu_shift_shortcut_label));
        a(sb, i3, 4, resources.getString(a.j.abc_menu_sym_shortcut_label));
        a(sb, i3, 8, resources.getString(a.j.abc_menu_function_shortcut_label));
        if (e == '\b') {
            i2 = a.j.abc_menu_delete_shortcut_label;
        } else if (e == '\n') {
            i2 = a.j.abc_menu_enter_shortcut_label;
        } else if (e != ' ') {
            sb.append(e);
            return sb.toString();
        } else {
            i2 = a.j.abc_menu_space_shortcut_label;
        }
        sb.append(resources.getString(i2));
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean g() {
        return this.f.e() && e() != 0;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public View getActionView() {
        View view = this.O;
        if (view != null) {
            return view;
        }
        android.support.v4.view.b bVar = this.P;
        if (bVar != null) {
            this.O = bVar.a(this);
            return this.O;
        }
        return null;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.t;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.s;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.z;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.k;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        Drawable drawable = this.u;
        if (drawable != null) {
            return a(drawable);
        }
        if (this.v != 0) {
            Drawable b = android.support.v7.b.a.a.b(this.f.g(), this.v);
            this.v = 0;
            this.u = b;
            return a(b);
        }
        return null;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.B;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.C;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.p;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public int getItemId() {
        return this.j;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.S;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public int getNumericModifiers() {
        return this.r;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.q;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.l;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return this.w;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public CharSequence getTitle() {
        return this.n;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.o;
        if (charSequence == null) {
            charSequence = this.n;
        }
        return (Build.VERSION.SDK_INT >= 18 || charSequence == null || (charSequence instanceof String)) ? charSequence : charSequence.toString();
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.A;
    }

    public boolean h() {
        return (this.G & 4) != 0;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return this.w != null;
    }

    public void i() {
        this.f.b(this);
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.R;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.G & 1) == 1;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.G & 2) == 2;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.G & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        android.support.v4.view.b bVar = this.P;
        return (bVar == null || !bVar.c()) ? (this.G & 8) == 0 : (this.G & 8) == 0 && this.P.d();
    }

    public boolean j() {
        return this.f.s();
    }

    public boolean k() {
        return (this.G & 32) == 32;
    }

    public boolean l() {
        return (this.N & 1) == 1;
    }

    public boolean m() {
        return (this.N & 2) == 2;
    }

    public boolean n() {
        return (this.N & 4) == 4;
    }

    public boolean o() {
        android.support.v4.view.b bVar;
        if ((this.N & 8) != 0) {
            if (this.O == null && (bVar = this.P) != null) {
                this.O = bVar.a(this);
            }
            return this.O != null;
        }
        return false;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c) {
        if (this.s == c) {
            return this;
        }
        this.s = Character.toLowerCase(c);
        this.f.c(false);
        return this;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c, int i2) {
        if (this.s == c && this.t == i2) {
            return this;
        }
        this.s = Character.toLowerCase(c);
        this.t = KeyEvent.normalizeMetaState(i2);
        this.f.c(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z) {
        int i2 = this.G;
        this.G = (z ? 1 : 0) | (i2 & (-2));
        if (i2 != this.G) {
            this.f.c(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z) {
        if ((this.G & 4) != 0) {
            this.f.a((MenuItem) this);
        } else {
            b(z);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z) {
        this.G = z ? this.G | 16 : this.G & (-17);
        this.f.c(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i2) {
        this.u = null;
        this.v = i2;
        this.F = true;
        this.f.c(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.v = 0;
        this.u = drawable;
        this.F = true;
        this.f.c(false);
        return this;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public MenuItem setIconTintList(@ag ColorStateList colorStateList) {
        this.B = colorStateList;
        this.D = true;
        this.F = true;
        this.f.c(false);
        return this;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.C = mode;
        this.E = true;
        this.F = true;
        this.f.c(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.p = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c) {
        if (this.q == c) {
            return this;
        }
        this.q = c;
        this.f.c(false);
        return this;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public MenuItem setNumericShortcut(char c, int i2) {
        if (this.q == c && this.r == i2) {
            return this;
        }
        this.q = c;
        this.r = KeyEvent.normalizeMetaState(i2);
        this.f.c(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.Q = onActionExpandListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.y = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c, char c2) {
        this.q = c;
        this.s = Character.toLowerCase(c2);
        this.f.c(false);
        return this;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public MenuItem setShortcut(char c, char c2, int i2, int i3) {
        this.q = c;
        this.r = KeyEvent.normalizeMetaState(i2);
        this.s = Character.toLowerCase(c2);
        this.t = KeyEvent.normalizeMetaState(i3);
        this.f.c(false);
        return this;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public void setShowAsAction(int i2) {
        int i3 = i2 & 3;
        if (i3 != 0 && i3 != 1 && i3 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.N = i2;
        this.f.b(this);
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i2) {
        return setTitle(this.f.g().getString(i2));
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.n = charSequence;
        this.f.c(false);
        v vVar = this.w;
        if (vVar != null) {
            vVar.setHeaderTitle(charSequence);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.o = charSequence;
        if (charSequence == null) {
            CharSequence charSequence2 = this.n;
        }
        this.f.c(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z) {
        if (c(z)) {
            this.f.a(this);
        }
        return this;
    }

    public String toString() {
        CharSequence charSequence = this.n;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }
}
