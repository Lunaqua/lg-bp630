package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class g {
    private static final String a = "AppCompatDrawableManag";
    private static final boolean b = false;
    private static final String d = "appcompat_skip_skip";
    private static final String e = "android.graphics.drawable.VectorDrawable";
    private static g f;
    private WeakHashMap<Context, android.support.v4.j.s<ColorStateList>> n;
    private android.support.v4.j.a<String, d> o;
    private android.support.v4.j.s<String> p;
    private final WeakHashMap<Context, android.support.v4.j.j<WeakReference<Drawable.ConstantState>>> q = new WeakHashMap<>(0);
    private TypedValue r;
    private boolean s;
    private static final PorterDuff.Mode c = PorterDuff.Mode.SRC_IN;
    private static final c g = new c(6);
    private static final int[] h = {a.f.abc_textfield_search_default_mtrl_alpha, a.f.abc_textfield_default_mtrl_alpha, a.f.abc_ab_share_pack_mtrl_alpha};
    private static final int[] i = {a.f.abc_ic_commit_search_api_mtrl_alpha, a.f.abc_seekbar_tick_mark_material, a.f.abc_ic_menu_share_mtrl_alpha, a.f.abc_ic_menu_copy_mtrl_am_alpha, a.f.abc_ic_menu_cut_mtrl_alpha, a.f.abc_ic_menu_selectall_mtrl_alpha, a.f.abc_ic_menu_paste_mtrl_am_alpha};
    private static final int[] j = {a.f.abc_textfield_activated_mtrl_alpha, a.f.abc_textfield_search_activated_mtrl_alpha, a.f.abc_cab_background_top_mtrl_alpha, a.f.abc_text_cursor_material, a.f.abc_text_select_handle_left_mtrl_dark, a.f.abc_text_select_handle_middle_mtrl_dark, a.f.abc_text_select_handle_right_mtrl_dark, a.f.abc_text_select_handle_left_mtrl_light, a.f.abc_text_select_handle_middle_mtrl_light, a.f.abc_text_select_handle_right_mtrl_light};
    private static final int[] k = {a.f.abc_popup_background_mtrl_mult, a.f.abc_cab_background_internal_bg, a.f.abc_menu_hardkey_panel_mtrl_mult};
    private static final int[] l = {a.f.abc_tab_indicator_material, a.f.abc_textfield_search_material};
    private static final int[] m = {a.f.abc_btn_check_material, a.f.abc_btn_radio_material};

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.ak(a = 11)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a implements d {
        a() {
        }

        @Override // android.support.v7.widget.g.d
        public Drawable a(@android.support.annotation.af Context context, @android.support.annotation.af XmlPullParser xmlPullParser, @android.support.annotation.af AttributeSet attributeSet, @android.support.annotation.ag Resources.Theme theme) {
            try {
                return android.support.v7.c.a.a.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e);
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b implements d {
        b() {
        }

        @Override // android.support.v7.widget.g.d
        public Drawable a(@android.support.annotation.af Context context, @android.support.annotation.af XmlPullParser xmlPullParser, @android.support.annotation.af AttributeSet attributeSet, @android.support.annotation.ag Resources.Theme theme) {
            try {
                return android.support.k.a.c.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e);
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class c extends android.support.v4.j.k<Integer, PorterDuffColorFilter> {
        public c(int i) {
            super(i);
        }

        private static int b(int i, PorterDuff.Mode mode) {
            return ((i + 31) * 31) + mode.hashCode();
        }

        PorterDuffColorFilter a(int i, PorterDuff.Mode mode) {
            return a((c) Integer.valueOf(b(i, mode)));
        }

        PorterDuffColorFilter a(int i, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return a((c) Integer.valueOf(b(i, mode)), (Integer) porterDuffColorFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface d {
        Drawable a(@android.support.annotation.af Context context, @android.support.annotation.af XmlPullParser xmlPullParser, @android.support.annotation.af AttributeSet attributeSet, @android.support.annotation.ag Resources.Theme theme);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class e implements d {
        e() {
        }

        @Override // android.support.v7.widget.g.d
        public Drawable a(@android.support.annotation.af Context context, @android.support.annotation.af XmlPullParser xmlPullParser, @android.support.annotation.af AttributeSet attributeSet, @android.support.annotation.ag Resources.Theme theme) {
            try {
                return android.support.k.a.j.a(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e);
                return null;
            }
        }
    }

    private static long a(TypedValue typedValue) {
        return (typedValue.assetCookie << 32) | typedValue.data;
    }

    static PorterDuff.Mode a(int i2) {
        if (i2 == a.f.abc_switch_thumb_material) {
            return PorterDuff.Mode.MULTIPLY;
        }
        return null;
    }

    public static synchronized PorterDuffColorFilter a(int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter a2;
        synchronized (g.class) {
            a2 = g.a(i2, mode);
            if (a2 == null) {
                a2 = new PorterDuffColorFilter(i2, mode);
                g.a(i2, mode, a2);
            }
        }
        return a2;
    }

    private static PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return a(colorStateList.getColorForState(iArr, 0), mode);
    }

    private Drawable a(@android.support.annotation.af Context context, @android.support.annotation.p int i2, boolean z, @android.support.annotation.af Drawable drawable) {
        LayerDrawable layerDrawable;
        Drawable findDrawableByLayerId;
        int i3;
        ColorStateList b2 = b(context, i2);
        if (b2 != null) {
            if (s.c(drawable)) {
                drawable = drawable.mutate();
            }
            Drawable g2 = android.support.v4.graphics.drawable.a.g(drawable);
            android.support.v4.graphics.drawable.a.a(g2, b2);
            PorterDuff.Mode a2 = a(i2);
            if (a2 != null) {
                android.support.v4.graphics.drawable.a.a(g2, a2);
                return g2;
            }
            return g2;
        }
        if (i2 == a.f.abc_seekbar_track_material) {
            layerDrawable = (LayerDrawable) drawable;
            a(layerDrawable.findDrawableByLayerId(16908288), aq.a(context, a.b.colorControlNormal), c);
            findDrawableByLayerId = layerDrawable.findDrawableByLayerId(16908303);
            i3 = a.b.colorControlNormal;
        } else if (i2 != a.f.abc_ratingbar_material && i2 != a.f.abc_ratingbar_indicator_material && i2 != a.f.abc_ratingbar_small_material) {
            if (a(context, i2, drawable) || !z) {
                return drawable;
            }
            return null;
        } else {
            layerDrawable = (LayerDrawable) drawable;
            a(layerDrawable.findDrawableByLayerId(16908288), aq.c(context, a.b.colorControlNormal), c);
            findDrawableByLayerId = layerDrawable.findDrawableByLayerId(16908303);
            i3 = a.b.colorControlActivated;
        }
        a(findDrawableByLayerId, aq.a(context, i3), c);
        a(layerDrawable.findDrawableByLayerId(16908301), aq.a(context, a.b.colorControlActivated), c);
        return drawable;
    }

    private synchronized Drawable a(@android.support.annotation.af Context context, long j2) {
        android.support.v4.j.j<WeakReference<Drawable.ConstantState>> jVar = this.q.get(context);
        if (jVar == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> a2 = jVar.a(j2);
        if (a2 != null) {
            Drawable.ConstantState constantState = a2.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            jVar.b(j2);
        }
        return null;
    }

    public static synchronized g a() {
        g gVar;
        synchronized (g.class) {
            if (f == null) {
                f = new g();
                a(f);
            }
            gVar = f;
        }
        return gVar;
    }

    private void a(@android.support.annotation.af Context context, @android.support.annotation.p int i2, @android.support.annotation.af ColorStateList colorStateList) {
        if (this.n == null) {
            this.n = new WeakHashMap<>();
        }
        android.support.v4.j.s<ColorStateList> sVar = this.n.get(context);
        if (sVar == null) {
            sVar = new android.support.v4.j.s<>();
            this.n.put(context, sVar);
        }
        sVar.d(i2, colorStateList);
    }

    private static void a(Drawable drawable, int i2, PorterDuff.Mode mode) {
        if (s.c(drawable)) {
            drawable = drawable.mutate();
        }
        if (mode == null) {
            mode = c;
        }
        drawable.setColorFilter(a(i2, mode));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Drawable drawable, at atVar, int[] iArr) {
        if (s.c(drawable) && drawable.mutate() != drawable) {
            Log.d(a, "Mutated drawable is not the same instance as the input.");
            return;
        }
        if (atVar.d || atVar.c) {
            drawable.setColorFilter(a(atVar.d ? atVar.a : null, atVar.c ? atVar.b : c, iArr));
        } else {
            drawable.clearColorFilter();
        }
        if (Build.VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
        }
    }

    private static void a(@android.support.annotation.af g gVar) {
        if (Build.VERSION.SDK_INT < 24) {
            gVar.a("vector", new e());
            gVar.a("animated-vector", new b());
            gVar.a("animated-selector", new a());
        }
    }

    private void a(@android.support.annotation.af String str, @android.support.annotation.af d dVar) {
        if (this.o == null) {
            this.o = new android.support.v4.j.a<>();
        }
        this.o.put(str, dVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0061 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(@android.support.annotation.af android.content.Context r6, @android.support.annotation.p int r7, @android.support.annotation.af android.graphics.drawable.Drawable r8) {
        /*
            android.graphics.PorterDuff$Mode r0 = android.support.v7.widget.g.c
            int[] r1 = android.support.v7.widget.g.h
            boolean r1 = a(r1, r7)
            r2 = 16842801(0x1010031, float:2.3693695E-38)
            r3 = -1
            r4 = 0
            r5 = 1
            if (r1 == 0) goto L15
            int r2 = android.support.v7.a.a.b.colorControlNormal
        L12:
            r7 = 1
            r1 = -1
            goto L44
        L15:
            int[] r1 = android.support.v7.widget.g.j
            boolean r1 = a(r1, r7)
            if (r1 == 0) goto L20
            int r2 = android.support.v7.a.a.b.colorControlActivated
            goto L12
        L20:
            int[] r1 = android.support.v7.widget.g.k
            boolean r1 = a(r1, r7)
            if (r1 == 0) goto L2b
            android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
            goto L12
        L2b:
            int r1 = android.support.v7.a.a.f.abc_list_divider_mtrl_alpha
            if (r7 != r1) goto L3c
            r2 = 16842800(0x1010030, float:2.3693693E-38)
            r7 = 1109603123(0x42233333, float:40.8)
            int r7 = java.lang.Math.round(r7)
            r1 = r7
            r7 = 1
            goto L44
        L3c:
            int r1 = android.support.v7.a.a.f.abc_dialog_material_background
            if (r7 != r1) goto L41
            goto L12
        L41:
            r7 = 0
            r1 = -1
            r2 = 0
        L44:
            if (r7 == 0) goto L61
            boolean r7 = android.support.v7.widget.s.c(r8)
            if (r7 == 0) goto L50
            android.graphics.drawable.Drawable r8 = r8.mutate()
        L50:
            int r6 = android.support.v7.widget.aq.a(r6, r2)
            android.graphics.PorterDuffColorFilter r6 = a(r6, r0)
            r8.setColorFilter(r6)
            if (r1 == r3) goto L60
            r8.setAlpha(r1)
        L60:
            return r5
        L61:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.g.a(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
    }

    private synchronized boolean a(@android.support.annotation.af Context context, long j2, @android.support.annotation.af Drawable drawable) {
        boolean z;
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState != null) {
            android.support.v4.j.j<WeakReference<Drawable.ConstantState>> jVar = this.q.get(context);
            if (jVar == null) {
                jVar = new android.support.v4.j.j<>();
                this.q.put(context, jVar);
            }
            jVar.b(j2, new WeakReference<>(constantState));
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    private static boolean a(@android.support.annotation.af Drawable drawable) {
        return (drawable instanceof android.support.k.a.j) || e.equals(drawable.getClass().getName());
    }

    private static boolean a(int[] iArr, int i2) {
        for (int i3 : iArr) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    private ColorStateList b(@android.support.annotation.af Context context) {
        return f(context, aq.a(context, a.b.colorButtonNormal));
    }

    private void b(@android.support.annotation.af String str, @android.support.annotation.af d dVar) {
        android.support.v4.j.a<String, d> aVar = this.o;
        if (aVar == null || aVar.get(str) != dVar) {
            return;
        }
        this.o.remove(str);
    }

    private ColorStateList c(@android.support.annotation.af Context context) {
        return f(context, 0);
    }

    private Drawable c(@android.support.annotation.af Context context, @android.support.annotation.p int i2) {
        if (this.r == null) {
            this.r = new TypedValue();
        }
        TypedValue typedValue = this.r;
        context.getResources().getValue(i2, typedValue, true);
        long a2 = a(typedValue);
        Drawable a3 = a(context, a2);
        if (a3 != null) {
            return a3;
        }
        if (i2 == a.f.abc_cab_background_top_material) {
            a3 = new LayerDrawable(new Drawable[]{a(context, a.f.abc_cab_background_internal_bg), a(context, a.f.abc_cab_background_top_mtrl_alpha)});
        }
        if (a3 != null) {
            a3.setChangingConfigurations(typedValue.changingConfigurations);
            a(context, a2, a3);
        }
        return a3;
    }

    private ColorStateList d(@android.support.annotation.af Context context) {
        return f(context, aq.a(context, a.b.colorAccent));
    }

    private Drawable d(@android.support.annotation.af Context context, @android.support.annotation.p int i2) {
        int next;
        android.support.v4.j.a<String, d> aVar = this.o;
        if (aVar == null || aVar.isEmpty()) {
            return null;
        }
        android.support.v4.j.s<String> sVar = this.p;
        if (sVar != null) {
            String a2 = sVar.a(i2);
            if (d.equals(a2) || (a2 != null && this.o.get(a2) == null)) {
                return null;
            }
        } else {
            this.p = new android.support.v4.j.s<>();
        }
        if (this.r == null) {
            this.r = new TypedValue();
        }
        TypedValue typedValue = this.r;
        Resources resources = context.getResources();
        resources.getValue(i2, typedValue, true);
        long a3 = a(typedValue);
        Drawable a4 = a(context, a3);
        if (a4 != null) {
            return a4;
        }
        if (typedValue.string != null && typedValue.string.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i2);
                AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                while (true) {
                    next = xml.next();
                    if (next == 2 || next == 1) {
                        break;
                    }
                }
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                String name = xml.getName();
                this.p.d(i2, name);
                d dVar = this.o.get(name);
                if (dVar != null) {
                    a4 = dVar.a(context, xml, asAttributeSet, context.getTheme());
                }
                if (a4 != null) {
                    a4.setChangingConfigurations(typedValue.changingConfigurations);
                    a(context, a3, a4);
                }
            } catch (Exception e2) {
                Log.e(a, "Exception while inflating drawable", e2);
            }
        }
        if (a4 == null) {
            this.p.d(i2, d);
        }
        return a4;
    }

    private ColorStateList e(Context context) {
        int[][] iArr = new int[3];
        int[] iArr2 = new int[3];
        ColorStateList b2 = aq.b(context, a.b.colorSwitchThumbNormal);
        if (b2 == null || !b2.isStateful()) {
            iArr[0] = aq.a;
            iArr2[0] = aq.c(context, a.b.colorSwitchThumbNormal);
            iArr[1] = aq.e;
            iArr2[1] = aq.a(context, a.b.colorControlActivated);
            iArr[2] = aq.h;
            iArr2[2] = aq.a(context, a.b.colorSwitchThumbNormal);
        } else {
            iArr[0] = aq.a;
            iArr2[0] = b2.getColorForState(iArr[0], 0);
            iArr[1] = aq.e;
            iArr2[1] = aq.a(context, a.b.colorControlActivated);
            iArr[2] = aq.h;
            iArr2[2] = b2.getDefaultColor();
        }
        return new ColorStateList(iArr, iArr2);
    }

    private ColorStateList e(@android.support.annotation.af Context context, @android.support.annotation.p int i2) {
        android.support.v4.j.s<ColorStateList> sVar;
        WeakHashMap<Context, android.support.v4.j.s<ColorStateList>> weakHashMap = this.n;
        if (weakHashMap == null || (sVar = weakHashMap.get(context)) == null) {
            return null;
        }
        return sVar.a(i2);
    }

    private ColorStateList f(@android.support.annotation.af Context context, @android.support.annotation.k int i2) {
        int a2 = aq.a(context, a.b.colorControlHighlight);
        return new ColorStateList(new int[][]{aq.a, aq.d, aq.b, aq.h}, new int[]{aq.c(context, a.b.colorButtonNormal), android.support.v4.graphics.b.a(a2, i2), android.support.v4.graphics.b.a(a2, i2), i2});
    }

    private void f(@android.support.annotation.af Context context) {
        if (this.s) {
            return;
        }
        this.s = true;
        Drawable a2 = a(context, a.f.abc_vector_test);
        if (a2 == null || !a(a2)) {
            this.s = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    public synchronized Drawable a(@android.support.annotation.af Context context, @android.support.annotation.p int i2) {
        return a(context, i2, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Drawable a(@android.support.annotation.af Context context, @android.support.annotation.p int i2, boolean z) {
        Drawable d2;
        f(context);
        d2 = d(context, i2);
        if (d2 == null) {
            d2 = c(context, i2);
        }
        if (d2 == null) {
            d2 = android.support.v4.content.c.getDrawable(context, i2);
        }
        if (d2 != null) {
            d2 = a(context, i2, z, d2);
        }
        if (d2 != null) {
            s.b(d2);
        }
        return d2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Drawable a(@android.support.annotation.af Context context, @android.support.annotation.af ba baVar, @android.support.annotation.p int i2) {
        Drawable d2 = d(context, i2);
        if (d2 == null) {
            d2 = baVar.a(i2);
        }
        if (d2 != null) {
            return a(context, i2, false, d2);
        }
        return null;
    }

    public synchronized void a(@android.support.annotation.af Context context) {
        android.support.v4.j.j<WeakReference<Drawable.ConstantState>> jVar = this.q.get(context);
        if (jVar != null) {
            jVar.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0078 A[Catch: all -> 0x007d, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0007, B:7:0x000b, B:8:0x000d, B:43:0x0078, B:9:0x0013, B:11:0x0017, B:12:0x001a, B:14:0x001e, B:15:0x0023, B:17:0x0027, B:18:0x002c, B:20:0x0030, B:21:0x0035, B:23:0x0039, B:24:0x003e, B:26:0x0042, B:29:0x0047, B:31:0x004f, B:32:0x0056, B:34:0x005e, B:35:0x0061, B:37:0x0069, B:38:0x006c, B:40:0x0070, B:41:0x0073), top: B:51:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized android.content.res.ColorStateList b(@android.support.annotation.af android.content.Context r3, @android.support.annotation.p int r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            android.content.res.ColorStateList r0 = r2.e(r3, r4)     // Catch: java.lang.Throwable -> L7d
            if (r0 != 0) goto L7b
            int r1 = android.support.v7.a.a.f.abc_edit_text_material     // Catch: java.lang.Throwable -> L7d
            if (r4 != r1) goto L13
            int r0 = android.support.v7.a.a.d.abc_tint_edittext     // Catch: java.lang.Throwable -> L7d
        Ld:
            android.content.res.ColorStateList r0 = android.support.v7.b.a.a.a(r3, r0)     // Catch: java.lang.Throwable -> L7d
            goto L76
        L13:
            int r1 = android.support.v7.a.a.f.abc_switch_track_mtrl_alpha     // Catch: java.lang.Throwable -> L7d
            if (r4 != r1) goto L1a
            int r0 = android.support.v7.a.a.d.abc_tint_switch_track     // Catch: java.lang.Throwable -> L7d
            goto Ld
        L1a:
            int r1 = android.support.v7.a.a.f.abc_switch_thumb_material     // Catch: java.lang.Throwable -> L7d
            if (r4 != r1) goto L23
            android.content.res.ColorStateList r0 = r2.e(r3)     // Catch: java.lang.Throwable -> L7d
            goto L76
        L23:
            int r1 = android.support.v7.a.a.f.abc_btn_default_mtrl_shape     // Catch: java.lang.Throwable -> L7d
            if (r4 != r1) goto L2c
            android.content.res.ColorStateList r0 = r2.b(r3)     // Catch: java.lang.Throwable -> L7d
            goto L76
        L2c:
            int r1 = android.support.v7.a.a.f.abc_btn_borderless_material     // Catch: java.lang.Throwable -> L7d
            if (r4 != r1) goto L35
            android.content.res.ColorStateList r0 = r2.c(r3)     // Catch: java.lang.Throwable -> L7d
            goto L76
        L35:
            int r1 = android.support.v7.a.a.f.abc_btn_colored_material     // Catch: java.lang.Throwable -> L7d
            if (r4 != r1) goto L3e
            android.content.res.ColorStateList r0 = r2.d(r3)     // Catch: java.lang.Throwable -> L7d
            goto L76
        L3e:
            int r1 = android.support.v7.a.a.f.abc_spinner_mtrl_am_alpha     // Catch: java.lang.Throwable -> L7d
            if (r4 == r1) goto L73
            int r1 = android.support.v7.a.a.f.abc_spinner_textfield_background_material     // Catch: java.lang.Throwable -> L7d
            if (r4 != r1) goto L47
            goto L73
        L47:
            int[] r1 = android.support.v7.widget.g.i     // Catch: java.lang.Throwable -> L7d
            boolean r1 = a(r1, r4)     // Catch: java.lang.Throwable -> L7d
            if (r1 == 0) goto L56
            int r0 = android.support.v7.a.a.b.colorControlNormal     // Catch: java.lang.Throwable -> L7d
            android.content.res.ColorStateList r0 = android.support.v7.widget.aq.b(r3, r0)     // Catch: java.lang.Throwable -> L7d
            goto L76
        L56:
            int[] r1 = android.support.v7.widget.g.l     // Catch: java.lang.Throwable -> L7d
            boolean r1 = a(r1, r4)     // Catch: java.lang.Throwable -> L7d
            if (r1 == 0) goto L61
            int r0 = android.support.v7.a.a.d.abc_tint_default     // Catch: java.lang.Throwable -> L7d
            goto Ld
        L61:
            int[] r1 = android.support.v7.widget.g.m     // Catch: java.lang.Throwable -> L7d
            boolean r1 = a(r1, r4)     // Catch: java.lang.Throwable -> L7d
            if (r1 == 0) goto L6c
            int r0 = android.support.v7.a.a.d.abc_tint_btn_checkable     // Catch: java.lang.Throwable -> L7d
            goto Ld
        L6c:
            int r1 = android.support.v7.a.a.f.abc_seekbar_thumb_material     // Catch: java.lang.Throwable -> L7d
            if (r4 != r1) goto L76
            int r0 = android.support.v7.a.a.d.abc_tint_seek_thumb     // Catch: java.lang.Throwable -> L7d
            goto Ld
        L73:
            int r0 = android.support.v7.a.a.d.abc_tint_spinner     // Catch: java.lang.Throwable -> L7d
            goto Ld
        L76:
            if (r0 == 0) goto L7b
            r2.a(r3, r4, r0)     // Catch: java.lang.Throwable -> L7d
        L7b:
            monitor-exit(r2)
            return r0
        L7d:
            r3 = move-exception
            monitor-exit(r2)
            goto L81
        L80:
            throw r3
        L81:
            goto L80
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.g.b(android.content.Context, int):android.content.res.ColorStateList");
    }
}
