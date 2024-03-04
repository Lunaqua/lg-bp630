package android.support.v7.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.support.annotation.aa;
import android.support.annotation.an;
import android.support.v4.view.m;
import android.support.v7.a.a;
import android.support.v7.view.menu.k;
import android.support.v7.view.menu.l;
import android.support.v7.widget.s;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParserException;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class g extends MenuInflater {
    static final String a = "SupportMenuInflater";
    static final int b = 0;
    static final Class<?>[] c = {Context.class};
    static final Class<?>[] d = c;
    private static final String h = "menu";
    private static final String i = "group";
    private static final String j = "item";
    final Object[] e;
    final Object[] f;
    Context g;
    private Object k;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a implements MenuItem.OnMenuItemClickListener {
        private static final Class<?>[] a = {MenuItem.class};
        private Object b;
        private Method c;

        public a(Object obj, String str) {
            this.b = obj;
            Class<?> cls = obj.getClass();
            try {
                this.c = cls.getMethod(str, a);
            } catch (Exception e) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.c.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.c.invoke(this.b, menuItem)).booleanValue();
                }
                this.c.invoke(this.b, menuItem);
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class b {
        private static final int G = 0;
        private static final int H = 0;
        private static final int I = 0;
        private static final int J = 0;
        private static final int K = 0;
        private static final boolean L = false;
        private static final boolean M = true;
        private static final boolean N = true;
        private String A;
        private String B;
        private CharSequence C;
        private CharSequence D;
        private ColorStateList E = null;
        private PorterDuff.Mode F = null;
        android.support.v4.view.b a;
        private Menu c;
        private int d;
        private int e;
        private int f;
        private int g;
        private boolean h;
        private boolean i;
        private boolean j;
        private int k;
        private int l;
        private CharSequence m;
        private CharSequence n;
        private int o;
        private char p;
        private int q;
        private char r;
        private int s;
        private int t;
        private boolean u;
        private boolean v;
        private boolean w;
        private int x;
        private int y;
        private String z;

        public b(Menu menu) {
            this.c = menu;
            a();
        }

        private char a(String str) {
            if (str == null) {
                return (char) 0;
            }
            return str.charAt(0);
        }

        private <T> T a(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = g.this.g.getClassLoader().loadClass(str).getConstructor(clsArr);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(objArr);
            } catch (Exception e) {
                Log.w(g.a, "Cannot instantiate class: " + str, e);
                return null;
            }
        }

        private void a(MenuItem menuItem) {
            boolean z = false;
            menuItem.setChecked(this.u).setVisible(this.v).setEnabled(this.w).setCheckable(this.t >= 1).setTitleCondensed(this.n).setIcon(this.o);
            int i = this.x;
            if (i >= 0) {
                menuItem.setShowAsAction(i);
            }
            if (this.B != null) {
                if (g.this.g.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new a(g.this.a(), this.B));
            }
            boolean z2 = menuItem instanceof k;
            if (z2) {
                k kVar = (k) menuItem;
            }
            if (this.t >= 2) {
                if (z2) {
                    ((k) menuItem).a(true);
                } else if (menuItem instanceof l) {
                    ((l) menuItem).a(true);
                }
            }
            String str = this.z;
            if (str != null) {
                menuItem.setActionView((View) a(str, g.c, g.this.e));
                z = true;
            }
            int i2 = this.y;
            if (i2 > 0) {
                if (z) {
                    Log.w(g.a, "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                } else {
                    menuItem.setActionView(i2);
                }
            }
            android.support.v4.view.b bVar = this.a;
            if (bVar != null) {
                m.a(menuItem, bVar);
            }
            m.a(menuItem, this.C);
            m.b(menuItem, this.D);
            m.b(menuItem, this.p, this.q);
            m.a(menuItem, this.r, this.s);
            PorterDuff.Mode mode = this.F;
            if (mode != null) {
                m.a(menuItem, mode);
            }
            ColorStateList colorStateList = this.E;
            if (colorStateList != null) {
                m.a(menuItem, colorStateList);
            }
        }

        public void a() {
            this.d = 0;
            this.e = 0;
            this.f = 0;
            this.g = 0;
            this.h = true;
            this.i = true;
        }

        public void a(AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = g.this.g.obtainStyledAttributes(attributeSet, a.l.MenuGroup);
            this.d = obtainStyledAttributes.getResourceId(a.l.MenuGroup_android_id, 0);
            this.e = obtainStyledAttributes.getInt(a.l.MenuGroup_android_menuCategory, 0);
            this.f = obtainStyledAttributes.getInt(a.l.MenuGroup_android_orderInCategory, 0);
            this.g = obtainStyledAttributes.getInt(a.l.MenuGroup_android_checkableBehavior, 0);
            this.h = obtainStyledAttributes.getBoolean(a.l.MenuGroup_android_visible, true);
            this.i = obtainStyledAttributes.getBoolean(a.l.MenuGroup_android_enabled, true);
            obtainStyledAttributes.recycle();
        }

        public void b() {
            this.j = true;
            a(this.c.add(this.d, this.k, this.l, this.m));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void b(AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = g.this.g.obtainStyledAttributes(attributeSet, a.l.MenuItem);
            this.k = obtainStyledAttributes.getResourceId(a.l.MenuItem_android_id, 0);
            this.l = (obtainStyledAttributes.getInt(a.l.MenuItem_android_menuCategory, this.e) & android.support.v4.d.a.a.c) | (obtainStyledAttributes.getInt(a.l.MenuItem_android_orderInCategory, this.f) & android.support.v4.d.a.a.a);
            this.m = obtainStyledAttributes.getText(a.l.MenuItem_android_title);
            this.n = obtainStyledAttributes.getText(a.l.MenuItem_android_titleCondensed);
            this.o = obtainStyledAttributes.getResourceId(a.l.MenuItem_android_icon, 0);
            this.p = a(obtainStyledAttributes.getString(a.l.MenuItem_android_alphabeticShortcut));
            this.q = obtainStyledAttributes.getInt(a.l.MenuItem_alphabeticModifiers, 4096);
            this.r = a(obtainStyledAttributes.getString(a.l.MenuItem_android_numericShortcut));
            this.s = obtainStyledAttributes.getInt(a.l.MenuItem_numericModifiers, 4096);
            this.t = obtainStyledAttributes.hasValue(a.l.MenuItem_android_checkable) ? obtainStyledAttributes.getBoolean(a.l.MenuItem_android_checkable, false) : this.g;
            this.u = obtainStyledAttributes.getBoolean(a.l.MenuItem_android_checked, false);
            this.v = obtainStyledAttributes.getBoolean(a.l.MenuItem_android_visible, this.h);
            this.w = obtainStyledAttributes.getBoolean(a.l.MenuItem_android_enabled, this.i);
            this.x = obtainStyledAttributes.getInt(a.l.MenuItem_showAsAction, -1);
            this.B = obtainStyledAttributes.getString(a.l.MenuItem_android_onClick);
            this.y = obtainStyledAttributes.getResourceId(a.l.MenuItem_actionLayout, 0);
            this.z = obtainStyledAttributes.getString(a.l.MenuItem_actionViewClass);
            this.A = obtainStyledAttributes.getString(a.l.MenuItem_actionProviderClass);
            boolean z = this.A != null;
            if (z && this.y == 0 && this.z == null) {
                this.a = (android.support.v4.view.b) a(this.A, g.d, g.this.f);
            } else {
                if (z) {
                    Log.w(g.a, "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.a = null;
            }
            this.C = obtainStyledAttributes.getText(a.l.MenuItem_contentDescription);
            this.D = obtainStyledAttributes.getText(a.l.MenuItem_tooltipText);
            if (obtainStyledAttributes.hasValue(a.l.MenuItem_iconTintMode)) {
                this.F = s.a(obtainStyledAttributes.getInt(a.l.MenuItem_iconTintMode, -1), this.F);
            } else {
                this.F = null;
            }
            if (obtainStyledAttributes.hasValue(a.l.MenuItem_iconTint)) {
                this.E = obtainStyledAttributes.getColorStateList(a.l.MenuItem_iconTint);
            } else {
                this.E = null;
            }
            obtainStyledAttributes.recycle();
            this.j = false;
        }

        public SubMenu c() {
            this.j = true;
            SubMenu addSubMenu = this.c.addSubMenu(this.d, this.k, this.l, this.m);
            a(addSubMenu.getItem());
            return addSubMenu;
        }

        public boolean d() {
            return this.j;
        }
    }

    public g(Context context) {
        super(context);
        this.g = context;
        this.e = new Object[]{context};
        this.f = this.e;
    }

    private Object a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? a(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x003b, code lost:
        r6 = r15;
        r8 = null;
        r15 = false;
        r7 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0041, code lost:
        if (r15 != false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0043, code lost:
        if (r6 == 1) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0049, code lost:
        if (r6 == 2) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        if (r6 == 3) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0050, code lost:
        r6 = r13.getName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0054, code lost:
        if (r7 == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005a, code lost:
        if (r6.equals(r8) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005c, code lost:
        r8 = null;
        r7 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0063, code lost:
        if (r6.equals(android.support.v7.view.g.i) == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0065, code lost:
        r0.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006d, code lost:
        if (r6.equals(android.support.v7.view.g.j) == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0073, code lost:
        if (r0.d() != false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0077, code lost:
        if (r0.a == null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x007f, code lost:
        if (r0.a.g() == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0081, code lost:
        r0.c();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0085, code lost:
        r0.b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x008d, code lost:
        if (r6.equals(android.support.v7.view.g.h) == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008f, code lost:
        r15 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0091, code lost:
        if (r7 == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0094, code lost:
        r6 = r13.getName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009c, code lost:
        if (r6.equals(android.support.v7.view.g.i) == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009e, code lost:
        r0.a(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00a6, code lost:
        if (r6.equals(android.support.v7.view.g.j) == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a8, code lost:
        r0.b(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b0, code lost:
        if (r6.equals(android.support.v7.view.g.h) == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00b2, code lost:
        a(r13, r14, r0.c());
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00ba, code lost:
        r8 = r6;
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00bc, code lost:
        r6 = r13.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00c8, code lost:
        throw new java.lang.RuntimeException("Unexpected end of document");
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00c9, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(org.xmlpull.v1.XmlPullParser r13, android.util.AttributeSet r14, android.view.Menu r15) {
        /*
            r12 = this;
            android.support.v7.view.g$b r0 = new android.support.v7.view.g$b
            r0.<init>(r15)
            int r15 = r13.getEventType()
        L9:
            r1 = 2
            java.lang.String r2 = "menu"
            r3 = 1
            if (r15 != r1) goto L35
            java.lang.String r15 = r13.getName()
            boolean r4 = r15.equals(r2)
            if (r4 == 0) goto L1e
            int r15 = r13.next()
            goto L3b
        L1e:
            java.lang.RuntimeException r13 = new java.lang.RuntimeException
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r0 = "Expecting menu, got "
            r14.append(r0)
            r14.append(r15)
            java.lang.String r14 = r14.toString()
            r13.<init>(r14)
            throw r13
        L35:
            int r15 = r13.next()
            if (r15 != r3) goto L9
        L3b:
            r4 = 0
            r5 = 0
            r6 = r15
            r8 = r4
            r15 = 0
            r7 = 0
        L41:
            if (r15 != 0) goto Lc9
            if (r6 == r3) goto Lc1
            java.lang.String r9 = "item"
            java.lang.String r10 = "group"
            if (r6 == r1) goto L91
            r11 = 3
            if (r6 == r11) goto L50
            goto Lbc
        L50:
            java.lang.String r6 = r13.getName()
            if (r7 == 0) goto L5f
            boolean r11 = r6.equals(r8)
            if (r11 == 0) goto L5f
            r8 = r4
            r7 = 0
            goto Lbc
        L5f:
            boolean r10 = r6.equals(r10)
            if (r10 == 0) goto L69
            r0.a()
            goto Lbc
        L69:
            boolean r9 = r6.equals(r9)
            if (r9 == 0) goto L89
            boolean r6 = r0.d()
            if (r6 != 0) goto Lbc
            android.support.v4.view.b r6 = r0.a
            if (r6 == 0) goto L85
            android.support.v4.view.b r6 = r0.a
            boolean r6 = r6.g()
            if (r6 == 0) goto L85
            r0.c()
            goto Lbc
        L85:
            r0.b()
            goto Lbc
        L89:
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto Lbc
            r15 = 1
            goto Lbc
        L91:
            if (r7 == 0) goto L94
            goto Lbc
        L94:
            java.lang.String r6 = r13.getName()
            boolean r10 = r6.equals(r10)
            if (r10 == 0) goto La2
            r0.a(r14)
            goto Lbc
        La2:
            boolean r9 = r6.equals(r9)
            if (r9 == 0) goto Lac
            r0.b(r14)
            goto Lbc
        Lac:
            boolean r9 = r6.equals(r2)
            if (r9 == 0) goto Lba
            android.view.SubMenu r6 = r0.c()
            r12.a(r13, r14, r6)
            goto Lbc
        Lba:
            r8 = r6
            r7 = 1
        Lbc:
            int r6 = r13.next()
            goto L41
        Lc1:
            java.lang.RuntimeException r13 = new java.lang.RuntimeException
            java.lang.String r14 = "Unexpected end of document"
            r13.<init>(r14)
            throw r13
        Lc9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.g.a(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.Menu):void");
    }

    Object a() {
        if (this.k == null) {
            this.k = a(this.g);
        }
        return this.k;
    }

    @Override // android.view.MenuInflater
    public void inflate(@aa int i2, Menu menu) {
        if (!(menu instanceof android.support.v4.d.a.a)) {
            super.inflate(i2, menu);
            return;
        }
        XmlResourceParser xmlResourceParser = null;
        try {
            try {
                try {
                    xmlResourceParser = this.g.getResources().getLayout(i2);
                    a(xmlResourceParser, Xml.asAttributeSet(xmlResourceParser), menu);
                } catch (XmlPullParserException e) {
                    throw new InflateException("Error inflating menu XML", e);
                }
            } catch (IOException e2) {
                throw new InflateException("Error inflating menu XML", e2);
            }
        } finally {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
        }
    }
}
