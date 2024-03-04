package android.support.v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a implements android.support.v4.d.a.b {
    private static final int A = 0;
    private static final int C = 1;
    private static final int D = 2;
    private static final int E = 4;
    private static final int F = 8;
    private static final int G = 16;
    private final int f;
    private final int g;
    private final int h;
    private final int i;
    private CharSequence j;
    private CharSequence k;
    private Intent l;
    private char m;
    private char o;
    private Drawable q;
    private Context s;
    private MenuItem.OnMenuItemClickListener t;
    private CharSequence u;
    private CharSequence v;
    private int n = 4096;
    private int p = 4096;
    private int r = 0;
    private ColorStateList w = null;
    private PorterDuff.Mode x = null;
    private boolean y = false;
    private boolean z = false;
    private int B = 16;

    public a(Context context, int i, int i2, int i3, int i4, CharSequence charSequence) {
        this.s = context;
        this.f = i2;
        this.g = i;
        this.h = i3;
        this.i = i4;
        this.j = charSequence;
    }

    private void c() {
        if (this.q != null) {
            if (this.y || this.z) {
                this.q = android.support.v4.graphics.drawable.a.g(this.q);
                this.q = this.q.mutate();
                if (this.y) {
                    android.support.v4.graphics.drawable.a.a(this.q, this.w);
                }
                if (this.z) {
                    android.support.v4.graphics.drawable.a.a(this.q, this.x);
                }
            }
        }
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    /* renamed from: a */
    public android.support.v4.d.a.b setActionView(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // android.support.v4.d.a.b
    public android.support.v4.d.a.b a(android.support.v4.view.b bVar) {
        throw new UnsupportedOperationException();
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    /* renamed from: a */
    public android.support.v4.d.a.b setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    /* renamed from: a */
    public android.support.v4.d.a.b setContentDescription(CharSequence charSequence) {
        this.u = charSequence;
        return this;
    }

    @Override // android.support.v4.d.a.b
    public android.support.v4.view.b a() {
        return null;
    }

    public a a(boolean z) {
        this.B = (z ? 4 : 0) | (this.B & (-5));
        return this;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    /* renamed from: b */
    public android.support.v4.d.a.b setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }

    @Override // android.view.MenuItem
    /* renamed from: b */
    public android.support.v4.d.a.b setTooltipText(CharSequence charSequence) {
        this.v = charSequence;
        return this;
    }

    public boolean b() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.t;
        if (onMenuItemClickListener == null || !onMenuItemClickListener.onMenuItemClick(this)) {
            Intent intent = this.l;
            if (intent != null) {
                this.s.startActivity(intent);
                return true;
            }
            return false;
        }
        return true;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public boolean collapseActionView() {
        return false;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public boolean expandActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public View getActionView() {
        return null;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.p;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.o;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.u;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.g;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.q;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.w;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.x;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.l;
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public int getNumericModifiers() {
        return this.n;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.m;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.i;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.j;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.k;
        return charSequence != null ? charSequence : this.j;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.v;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return false;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.B & 1) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.B & 2) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.B & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return (this.B & 8) == 0;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c) {
        this.o = Character.toLowerCase(c);
        return this;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c, int i) {
        this.o = Character.toLowerCase(c);
        this.p = KeyEvent.normalizeMetaState(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z) {
        this.B = (z ? 1 : 0) | (this.B & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z) {
        this.B = (z ? 2 : 0) | (this.B & (-3));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z) {
        this.B = (z ? 16 : 0) | (this.B & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i) {
        this.r = i;
        this.q = android.support.v4.content.c.getDrawable(this.s, i);
        c();
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.q = drawable;
        this.r = 0;
        c();
        return this;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public MenuItem setIconTintList(@ag ColorStateList colorStateList) {
        this.w = colorStateList;
        this.y = true;
        c();
        return this;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.x = mode;
        this.z = true;
        c();
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.l = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c) {
        this.m = c;
        return this;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public MenuItem setNumericShortcut(char c, int i) {
        this.m = c;
        this.n = KeyEvent.normalizeMetaState(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.t = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c, char c2) {
        this.m = c;
        this.o = Character.toLowerCase(c2);
        return this;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.m = c;
        this.n = KeyEvent.normalizeMetaState(i);
        this.o = Character.toLowerCase(c2);
        this.p = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    @Override // android.support.v4.d.a.b, android.view.MenuItem
    public void setShowAsAction(int i) {
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i) {
        this.j = this.s.getResources().getString(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.j = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.k = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z) {
        this.B = (this.B & 8) | (z ? 0 : 8);
        return this;
    }
}
