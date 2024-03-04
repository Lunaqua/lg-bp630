package android.support.v4.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.an;
import android.support.annotation.au;
import android.support.b.a;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeProvider;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ab {
    public static final int A = 1;
    public static final int B = 2;
    public static final int C = 4;
    public static final int D = 8;
    public static final int E = 16;
    public static final int F = 32;
    private static final String G = "ViewCompat";
    private static Field I = null;
    private static boolean J = false;
    private static Field K = null;
    private static boolean L = false;
    private static Method M = null;
    private static Method N = null;
    private static boolean O = false;
    private static WeakHashMap<View, String> P = null;
    private static Method R = null;
    private static Field S = null;
    private static ThreadLocal<Rect> U = null;
    @Deprecated
    public static final int a = 0;
    @Deprecated
    public static final int b = 1;
    @Deprecated
    public static final int c = 2;
    public static final int d = 0;
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 4;
    public static final int h = 0;
    public static final int i = 1;
    public static final int j = 2;
    @Deprecated
    public static final int k = 0;
    @Deprecated
    public static final int l = 1;
    @Deprecated
    public static final int m = 2;
    public static final int n = 0;
    public static final int o = 1;
    public static final int p = 2;
    public static final int q = 3;
    @Deprecated
    public static final int r = 16777215;
    @Deprecated
    public static final int s = -16777216;
    @Deprecated
    public static final int t = 16;
    @Deprecated
    public static final int u = 16777216;
    public static final int v = 0;
    public static final int w = 1;
    public static final int x = 2;
    public static final int y = 0;
    public static final int z = 1;
    private static final AtomicInteger H = new AtomicInteger(1);
    private static WeakHashMap<View, af> Q = null;
    private static boolean T = false;

    @an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface a {
    }

    @an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface b {
    }

    @an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface c {
    }

    @an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface d {
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface e {
        boolean a(View view, KeyEvent keyEvent);
    }

    @android.support.annotation.ak(a = 28)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class f implements View.OnUnhandledKeyEventListener {
        private e a;

        f(e eVar) {
            this.a = eVar;
        }

        @Override // android.view.View.OnUnhandledKeyEventListener
        public boolean onUnhandledKeyEvent(View view, KeyEvent keyEvent) {
            return this.a.a(view, keyEvent);
        }
    }

    @an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface g {
    }

    @an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface h {
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static class i {
        private static final ArrayList<WeakReference<View>> a = new ArrayList<>();
        @android.support.annotation.ag
        private WeakHashMap<View, Boolean> b = null;
        private SparseArray<WeakReference<View>> c = null;
        private WeakReference<KeyEvent> d = null;

        i() {
        }

        static i a(View view) {
            i iVar = (i) view.getTag(a.e.tag_unhandled_key_event_manager);
            if (iVar == null) {
                i iVar2 = new i();
                view.setTag(a.e.tag_unhandled_key_event_manager, iVar2);
                return iVar2;
            }
            return iVar;
        }

        private SparseArray<WeakReference<View>> a() {
            if (this.c == null) {
                this.c = new SparseArray<>();
            }
            return this.c;
        }

        @android.support.annotation.ag
        private View b(View view, KeyEvent keyEvent) {
            WeakHashMap<View, Boolean> weakHashMap = this.b;
            if (weakHashMap != null && weakHashMap.containsKey(view)) {
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        View b = b(viewGroup.getChildAt(childCount), keyEvent);
                        if (b != null) {
                            return b;
                        }
                    }
                }
                if (c(view, keyEvent)) {
                    return view;
                }
            }
            return null;
        }

        private void b() {
            WeakHashMap<View, Boolean> weakHashMap = this.b;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            if (a.isEmpty()) {
                return;
            }
            synchronized (a) {
                if (this.b == null) {
                    this.b = new WeakHashMap<>();
                }
                for (int size = a.size() - 1; size >= 0; size--) {
                    View view = a.get(size).get();
                    if (view == null) {
                        a.remove(size);
                    } else {
                        this.b.put(view, Boolean.TRUE);
                        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                            this.b.put((View) parent, Boolean.TRUE);
                        }
                    }
                }
            }
        }

        static void b(View view) {
            synchronized (a) {
                Iterator<WeakReference<View>> it = a.iterator();
                while (it.hasNext()) {
                    if (it.next().get() == view) {
                        return;
                    }
                }
                a.add(new WeakReference<>(view));
            }
        }

        static void c(View view) {
            synchronized (a) {
                for (int i = 0; i < a.size(); i++) {
                    if (a.get(i).get() == view) {
                        a.remove(i);
                        return;
                    }
                }
            }
        }

        private boolean c(@android.support.annotation.af View view, @android.support.annotation.af KeyEvent keyEvent) {
            ArrayList arrayList = (ArrayList) view.getTag(a.e.tag_unhandled_key_listeners);
            if (arrayList != null) {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    if (((e) arrayList.get(size)).a(view, keyEvent)) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }

        boolean a(KeyEvent keyEvent) {
            int indexOfKey;
            WeakReference<KeyEvent> weakReference = this.d;
            if (weakReference == null || weakReference.get() != keyEvent) {
                this.d = new WeakReference<>(keyEvent);
                WeakReference<View> weakReference2 = null;
                SparseArray<WeakReference<View>> a2 = a();
                if (keyEvent.getAction() == 1 && (indexOfKey = a2.indexOfKey(keyEvent.getKeyCode())) >= 0) {
                    weakReference2 = a2.valueAt(indexOfKey);
                    a2.removeAt(indexOfKey);
                }
                if (weakReference2 == null) {
                    weakReference2 = a2.get(keyEvent.getKeyCode());
                }
                if (weakReference2 != null) {
                    View view = weakReference2.get();
                    if (view != null && ab.af(view)) {
                        c(view, keyEvent);
                    }
                    return true;
                }
                return false;
            }
            return false;
        }

        boolean a(View view, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0) {
                b();
            }
            View b = b(view, keyEvent);
            if (keyEvent.getAction() == 0) {
                int keyCode = keyEvent.getKeyCode();
                if (b != null && !KeyEvent.isModifierKey(keyCode)) {
                    a().put(keyCode, new WeakReference<>(b));
                }
            }
            return b != null;
        }
    }

    protected ab() {
    }

    public static int A(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumWidth();
        }
        if (!J) {
            try {
                I = View.class.getDeclaredField("mMinWidth");
                I.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            J = true;
        }
        Field field = I;
        if (field != null) {
            try {
                return ((Integer) field.get(view)).intValue();
            } catch (Exception unused2) {
                return 0;
            }
        }
        return 0;
    }

    public static int B(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumHeight();
        }
        if (!L) {
            try {
                K = View.class.getDeclaredField("mMinHeight");
                K.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            L = true;
        }
        Field field = K;
        if (field != null) {
            try {
                return ((Integer) field.get(view)).intValue();
            } catch (Exception unused2) {
                return 0;
            }
        }
        return 0;
    }

    @android.support.annotation.af
    public static af C(@android.support.annotation.af View view) {
        if (Q == null) {
            Q = new WeakHashMap<>();
        }
        af afVar = Q.get(view);
        if (afVar == null) {
            af afVar2 = new af(view);
            Q.put(view, afVar2);
            return afVar2;
        }
        return afVar;
    }

    @Deprecated
    public static float D(View view) {
        return view.getPivotX();
    }

    @Deprecated
    public static float E(View view) {
        return view.getPivotY();
    }

    @Deprecated
    public static float F(View view) {
        return view.getRotation();
    }

    @Deprecated
    public static float G(View view) {
        return view.getRotationX();
    }

    @Deprecated
    public static float H(View view) {
        return view.getRotationY();
    }

    @Deprecated
    public static float I(View view) {
        return view.getScaleX();
    }

    @Deprecated
    public static float J(View view) {
        return view.getScaleY();
    }

    @Deprecated
    public static float K(View view) {
        return view.getX();
    }

    @Deprecated
    public static float L(View view) {
        return view.getY();
    }

    public static float M(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getElevation();
        }
        return 0.0f;
    }

    public static float N(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getTranslationZ();
        }
        return 0.0f;
    }

    @android.support.annotation.ag
    public static String O(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getTransitionName();
        }
        WeakHashMap<View, String> weakHashMap = P;
        if (weakHashMap == null) {
            return null;
        }
        return weakHashMap.get(view);
    }

    public static int P(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getWindowSystemUiVisibility();
        }
        return 0;
    }

    public static void Q(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 20) {
            view.requestApplyInsets();
        } else if (Build.VERSION.SDK_INT >= 16) {
            view.requestFitSystemWindows();
        }
    }

    public static boolean R(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getFitsSystemWindows();
        }
        return false;
    }

    @Deprecated
    public static void S(View view) {
        view.jumpDrawablesToCurrentState();
    }

    public static boolean T(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.hasOverlappingRendering();
        }
        return true;
    }

    public static boolean U(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.isPaddingRelative();
        }
        return false;
    }

    public static ColorStateList V(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintList();
        }
        if (view instanceof z) {
            return ((z) view).getSupportBackgroundTintList();
        }
        return null;
    }

    public static PorterDuff.Mode W(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintMode();
        }
        if (view instanceof z) {
            return ((z) view).getSupportBackgroundTintMode();
        }
        return null;
    }

    public static boolean X(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.isNestedScrollingEnabled();
        }
        if (view instanceof o) {
            return ((o) view).isNestedScrollingEnabled();
        }
        return false;
    }

    public static void Y(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.stopNestedScroll();
        } else if (view instanceof o) {
            ((o) view).stopNestedScroll();
        }
    }

    public static boolean Z(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.hasNestedScrollingParent();
        }
        if (view instanceof o) {
            return ((o) view).hasNestedScrollingParent();
        }
        return false;
    }

    public static int a() {
        int i2;
        int i3;
        if (Build.VERSION.SDK_INT >= 17) {
            return View.generateViewId();
        }
        do {
            i2 = H.get();
            i3 = i2 + 1;
            if (i3 > 16777215) {
                i3 = 1;
            }
        } while (!H.compareAndSet(i2, i3));
        return i2;
    }

    @Deprecated
    public static int a(int i2, int i3) {
        return View.combineMeasuredStates(i2, i3);
    }

    @Deprecated
    public static int a(int i2, int i3, int i4) {
        return View.resolveSizeAndState(i2, i3, i4);
    }

    @Deprecated
    public static int a(View view) {
        return view.getOverScrollMode();
    }

    public static ak a(@android.support.annotation.af View view, ak akVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            WindowInsets windowInsets = (WindowInsets) ak.a(akVar);
            WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
            if (onApplyWindowInsets != windowInsets) {
                windowInsets = new WindowInsets(onApplyWindowInsets);
            }
            return ak.a(windowInsets);
        }
        return akVar;
    }

    public static View a(@android.support.annotation.af View view, View view2, int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.keyboardNavigationClusterSearch(view2, i2);
        }
        return null;
    }

    @Deprecated
    public static void a(View view, float f2) {
        view.setTranslationX(f2);
    }

    public static void a(@android.support.annotation.af View view, int i2, int i3, int i4, int i5) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation(i2, i3, i4, i5);
        } else {
            view.postInvalidate(i2, i3, i4, i5);
        }
    }

    @Deprecated
    public static void a(View view, int i2, Paint paint) {
        view.setLayerType(i2, paint);
    }

    public static void a(@android.support.annotation.af View view, ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT < 21) {
            if (view instanceof z) {
                ((z) view).setSupportBackgroundTintList(colorStateList);
                return;
            }
            return;
        }
        view.setBackgroundTintList(colorStateList);
        if (Build.VERSION.SDK_INT == 21) {
            Drawable background = view.getBackground();
            boolean z2 = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
            if (background == null || !z2) {
                return;
            }
            if (background.isStateful()) {
                background.setState(view.getDrawableState());
            }
            view.setBackground(background);
        }
    }

    public static void a(@android.support.annotation.af View view, Paint paint) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setLayerPaint(paint);
            return;
        }
        view.setLayerType(view.getLayerType(), paint);
        view.invalidate();
    }

    public static void a(@android.support.annotation.af View view, PorterDuff.Mode mode) {
        if (Build.VERSION.SDK_INT < 21) {
            if (view instanceof z) {
                ((z) view).setSupportBackgroundTintMode(mode);
                return;
            }
            return;
        }
        view.setBackgroundTintMode(mode);
        if (Build.VERSION.SDK_INT == 21) {
            Drawable background = view.getBackground();
            boolean z2 = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
            if (background == null || !z2) {
                return;
            }
            if (background.isStateful()) {
                background.setState(view.getDrawableState());
            }
            view.setBackground(background);
        }
    }

    public static void a(@android.support.annotation.af View view, Rect rect) {
        if (Build.VERSION.SDK_INT >= 18) {
            view.setClipBounds(rect);
        }
    }

    public static void a(@android.support.annotation.af View view, @android.support.annotation.ag Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void a(@android.support.annotation.af View view, android.support.v4.view.a.c cVar) {
        view.onInitializeAccessibilityNodeInfo(cVar.a());
    }

    public static void a(@android.support.annotation.af View view, android.support.v4.view.a aVar) {
        view.setAccessibilityDelegate(aVar == null ? null : aVar.a());
    }

    public static void a(@android.support.annotation.af View view, @android.support.annotation.af e eVar) {
        if (Build.VERSION.SDK_INT >= 28) {
            Map map = (Map) view.getTag(a.e.tag_unhandled_key_listeners);
            if (map == null) {
                map = new android.support.v4.j.a();
                view.setTag(a.e.tag_unhandled_key_listeners, map);
            }
            f fVar = new f(eVar);
            map.put(eVar, fVar);
            view.addOnUnhandledKeyEventListener(fVar);
            return;
        }
        ArrayList arrayList = (ArrayList) view.getTag(a.e.tag_unhandled_key_listeners);
        if (arrayList == null) {
            arrayList = new ArrayList();
            view.setTag(a.e.tag_unhandled_key_listeners, arrayList);
        }
        arrayList.add(eVar);
        if (arrayList.size() == 1) {
            i.b(view);
        }
    }

    public static void a(@android.support.annotation.af View view, final u uVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (uVar == null) {
                view.setOnApplyWindowInsetsListener(null);
            } else {
                view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: android.support.v4.view.ab.1
                    @Override // android.view.View.OnApplyWindowInsetsListener
                    public WindowInsets onApplyWindowInsets(View view2, WindowInsets windowInsets) {
                        return (WindowInsets) ak.a(u.this.a(view2, ak.a(windowInsets)));
                    }
                });
            }
        }
    }

    public static void a(@android.support.annotation.af View view, w wVar) {
        if (Build.VERSION.SDK_INT >= 24) {
            view.setPointerIcon((PointerIcon) (wVar != null ? wVar.a() : null));
        }
    }

    public static void a(@android.support.annotation.af View view, View.DragShadowBuilder dragShadowBuilder) {
        if (Build.VERSION.SDK_INT >= 24) {
            view.updateDragShadow(dragShadowBuilder);
        }
    }

    @Deprecated
    public static void a(View view, AccessibilityEvent accessibilityEvent) {
        view.onPopulateAccessibilityEvent(accessibilityEvent);
    }

    public static void a(@android.support.annotation.af View view, @android.support.annotation.ag CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setTooltipText(charSequence);
        }
    }

    public static void a(@android.support.annotation.af View view, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimation(runnable);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay());
        }
    }

    public static void a(@android.support.annotation.af View view, Runnable runnable, long j2) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimationDelayed(runnable, j2);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay() + j2);
        }
    }

    public static void a(@android.support.annotation.af View view, String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setTransitionName(str);
            return;
        }
        if (P == null) {
            P = new WeakHashMap<>();
        }
        P.put(view, str);
    }

    public static void a(@android.support.annotation.af View view, @android.support.annotation.af Collection<View> collection, int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.addKeyboardNavigationClusters(collection, i2);
        }
    }

    public static void a(@android.support.annotation.af View view, boolean z2) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setHasTransientState(z2);
        }
    }

    public static void a(@android.support.annotation.af View view, @android.support.annotation.ag String... strArr) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setAutofillHints(strArr);
        }
    }

    @Deprecated
    public static void a(ViewGroup viewGroup, boolean z2) {
        if (R == null) {
            try {
                R = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException e2) {
                Log.e(G, "Unable to find childrenDrawingOrderEnabled", e2);
            }
            R.setAccessible(true);
        }
        try {
            R.invoke(viewGroup, Boolean.valueOf(z2));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e3) {
            Log.e(G, "Unable to invoke childrenDrawingOrderEnabled", e3);
        }
    }

    public static boolean a(@android.support.annotation.af View view, float f2, float f3) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.dispatchNestedPreFling(f2, f3);
        }
        if (view instanceof o) {
            return ((o) view).dispatchNestedPreFling(f2, f3);
        }
        return false;
    }

    public static boolean a(@android.support.annotation.af View view, float f2, float f3, boolean z2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.dispatchNestedFling(f2, f3, z2);
        }
        if (view instanceof o) {
            return ((o) view).dispatchNestedFling(f2, f3, z2);
        }
        return false;
    }

    @Deprecated
    public static boolean a(View view, int i2) {
        return view.canScrollHorizontally(i2);
    }

    public static boolean a(@android.support.annotation.af View view, int i2, int i3) {
        if (view instanceof p) {
            return ((p) view).a(i2, i3);
        }
        if (i3 == 0) {
            return j(view, i2);
        }
        return false;
    }

    public static boolean a(@android.support.annotation.af View view, int i2, int i3, int i4, int i5, @android.support.annotation.ag int[] iArr) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.dispatchNestedScroll(i2, i3, i4, i5, iArr);
        }
        if (view instanceof o) {
            return ((o) view).dispatchNestedScroll(i2, i3, i4, i5, iArr);
        }
        return false;
    }

    public static boolean a(@android.support.annotation.af View view, int i2, int i3, int i4, int i5, @android.support.annotation.ag int[] iArr, int i6) {
        if (view instanceof p) {
            return ((p) view).a(i2, i3, i4, i5, iArr, i6);
        }
        if (i6 == 0) {
            return a(view, i2, i3, i4, i5, iArr);
        }
        return false;
    }

    public static boolean a(@android.support.annotation.af View view, int i2, int i3, @android.support.annotation.ag int[] iArr, @android.support.annotation.ag int[] iArr2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.dispatchNestedPreScroll(i2, i3, iArr, iArr2);
        }
        if (view instanceof o) {
            return ((o) view).dispatchNestedPreScroll(i2, i3, iArr, iArr2);
        }
        return false;
    }

    public static boolean a(@android.support.annotation.af View view, int i2, int i3, @android.support.annotation.ag int[] iArr, @android.support.annotation.ag int[] iArr2, int i4) {
        if (view instanceof p) {
            return ((p) view).a(i2, i3, iArr, iArr2, i4);
        }
        if (i4 == 0) {
            return a(view, i2, i3, iArr, iArr2);
        }
        return false;
    }

    public static boolean a(@android.support.annotation.af View view, int i2, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.performAccessibilityAction(i2, bundle);
        }
        return false;
    }

    public static boolean a(@android.support.annotation.af View view, ClipData clipData, View.DragShadowBuilder dragShadowBuilder, Object obj, int i2) {
        return Build.VERSION.SDK_INT >= 24 ? view.startDragAndDrop(clipData, dragShadowBuilder, obj, i2) : view.startDrag(clipData, dragShadowBuilder, obj, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @au
    public static boolean a(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return i.a(view).a(keyEvent);
    }

    public static boolean aa(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 18) {
            return view.isInLayout();
        }
        return false;
    }

    public static boolean ab(@android.support.annotation.af View view) {
        return Build.VERSION.SDK_INT >= 19 ? view.isLaidOut() : view.getWidth() > 0 && view.getHeight() > 0;
    }

    public static boolean ac(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            return view.isLayoutDirectionResolved();
        }
        return false;
    }

    public static float ad(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getZ();
        }
        return 0.0f;
    }

    @android.support.annotation.ag
    public static Rect ae(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 18) {
            return view.getClipBounds();
        }
        return null;
    }

    public static boolean af(@android.support.annotation.af View view) {
        return Build.VERSION.SDK_INT >= 19 ? view.isAttachedToWindow() : view.getWindowToken() != null;
    }

    public static boolean ag(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 15) {
            return view.hasOnClickListeners();
        }
        return false;
    }

    public static int ah(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 23) {
            return view.getScrollIndicators();
        }
        return 0;
    }

    @android.support.annotation.ag
    public static Display ai(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getDisplay();
        }
        if (af(view)) {
            return ((WindowManager) view.getContext().getSystemService("window")).getDefaultDisplay();
        }
        return null;
    }

    public static void aj(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 24) {
            view.cancelDragAndDrop();
        }
    }

    public static int ak(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.getNextClusterForwardId();
        }
        return -1;
    }

    public static boolean al(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.isKeyboardNavigationCluster();
        }
        return false;
    }

    public static boolean am(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.isFocusedByDefault();
        }
        return false;
    }

    public static boolean an(@android.support.annotation.af View view) {
        return Build.VERSION.SDK_INT >= 26 ? view.restoreDefaultFocus() : view.requestFocus();
    }

    public static boolean ao(@android.support.annotation.af View view) {
        return Build.VERSION.SDK_INT >= 26 ? view.hasExplicitFocusable() : view.hasFocusable();
    }

    private static void ap(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }

    @SuppressLint({"InlinedApi"})
    public static int b(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.getImportantForAutofill();
        }
        return 0;
    }

    private static Rect b() {
        if (U == null) {
            U = new ThreadLocal<>();
        }
        Rect rect = U.get();
        if (rect == null) {
            rect = new Rect();
            U.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    public static ak b(@android.support.annotation.af View view, ak akVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            WindowInsets windowInsets = (WindowInsets) ak.a(akVar);
            WindowInsets dispatchApplyWindowInsets = view.dispatchApplyWindowInsets(windowInsets);
            if (dispatchApplyWindowInsets != windowInsets) {
                windowInsets = new WindowInsets(dispatchApplyWindowInsets);
            }
            return ak.a(windowInsets);
        }
        return akVar;
    }

    @Deprecated
    public static void b(View view, float f2) {
        view.setTranslationY(f2);
    }

    public static void b(@android.support.annotation.af View view, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i2, i3);
        }
    }

    public static void b(@android.support.annotation.af View view, @android.support.annotation.ai int i2, @android.support.annotation.ai int i3, @android.support.annotation.ai int i4, @android.support.annotation.ai int i5) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setPaddingRelative(i2, i3, i4, i5);
        } else {
            view.setPadding(i2, i3, i4, i5);
        }
    }

    public static void b(@android.support.annotation.af View view, @android.support.annotation.af e eVar) {
        View.OnUnhandledKeyEventListener onUnhandledKeyEventListener;
        if (Build.VERSION.SDK_INT >= 28) {
            Map map = (Map) view.getTag(a.e.tag_unhandled_key_listeners);
            if (map == null || (onUnhandledKeyEventListener = (View.OnUnhandledKeyEventListener) map.get(eVar)) == null) {
                return;
            }
            view.removeOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
            return;
        }
        ArrayList arrayList = (ArrayList) view.getTag(a.e.tag_unhandled_key_listeners);
        if (arrayList != null) {
            arrayList.remove(eVar);
            if (arrayList.size() == 0) {
                i.c(view);
            }
        }
    }

    @Deprecated
    public static void b(View view, AccessibilityEvent accessibilityEvent) {
        view.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    @Deprecated
    public static void b(View view, boolean z2) {
        view.setFitsSystemWindows(z2);
    }

    @Deprecated
    public static boolean b(View view, int i2) {
        return view.canScrollVertically(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @au
    public static boolean b(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return i.a(view).a(view, keyEvent);
    }

    private static void c() {
        try {
            M = View.class.getDeclaredMethod("dispatchStartTemporaryDetach", new Class[0]);
            N = View.class.getDeclaredMethod("dispatchFinishTemporaryDetach", new Class[0]);
        } catch (NoSuchMethodException e2) {
            Log.e(G, "Couldn't find method", e2);
        }
        O = true;
    }

    @Deprecated
    public static void c(View view, @android.support.annotation.q(a = 0.0d, b = 1.0d) float f2) {
        view.setAlpha(f2);
    }

    @Deprecated
    public static void c(View view, int i2) {
        view.setOverScrollMode(i2);
    }

    @Deprecated
    public static void c(View view, boolean z2) {
        view.setSaveFromParentEnabled(z2);
    }

    public static boolean c(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.isImportantForAutofill();
        }
        return true;
    }

    @Deprecated
    public static void d(View view, float f2) {
        view.setX(f2);
    }

    public static void d(@android.support.annotation.af View view, int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setImportantForAutofill(i2);
        }
    }

    @Deprecated
    public static void d(View view, boolean z2) {
        view.setActivated(z2);
    }

    public static boolean d(@android.support.annotation.af View view) {
        if (T) {
            return false;
        }
        if (S == null) {
            try {
                S = View.class.getDeclaredField("mAccessibilityDelegate");
                S.setAccessible(true);
            } catch (Throwable unused) {
                T = true;
                return false;
            }
        }
        try {
            return S.get(view) != null;
        } catch (Throwable unused2) {
            T = true;
            return false;
        }
    }

    @Deprecated
    public static void e(View view, float f2) {
        view.setY(f2);
    }

    public static void e(@android.support.annotation.af View view, int i2) {
        if (Build.VERSION.SDK_INT < 19) {
            if (Build.VERSION.SDK_INT < 16) {
                return;
            }
            if (i2 == 4) {
                i2 = 2;
            }
        }
        view.setImportantForAccessibility(i2);
    }

    public static void e(@android.support.annotation.af View view, boolean z2) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setNestedScrollingEnabled(z2);
        } else if (view instanceof o) {
            ((o) view).setNestedScrollingEnabled(z2);
        }
    }

    public static boolean e(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.hasTransientState();
        }
        return false;
    }

    public static void f(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation();
        } else {
            view.postInvalidate();
        }
    }

    @Deprecated
    public static void f(View view, float f2) {
        view.setRotation(f2);
    }

    public static void f(@android.support.annotation.af View view, @android.support.annotation.v int i2) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setLabelFor(i2);
        }
    }

    public static void f(@android.support.annotation.af View view, boolean z2) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setKeyboardNavigationCluster(z2);
        }
    }

    public static int g(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getImportantForAccessibility();
        }
        return 0;
    }

    @Deprecated
    public static void g(View view, float f2) {
        view.setRotationX(f2);
    }

    public static void g(@android.support.annotation.af View view, int i2) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setLayoutDirection(i2);
        }
    }

    public static void g(@android.support.annotation.af View view, boolean z2) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setFocusedByDefault(z2);
        }
    }

    @android.support.annotation.af
    public static <T extends View> T h(@android.support.annotation.af View view, @android.support.annotation.v int i2) {
        if (Build.VERSION.SDK_INT >= 28) {
            return (T) view.requireViewById(i2);
        }
        T t2 = (T) view.findViewById(i2);
        if (t2 != null) {
            return t2;
        }
        throw new IllegalArgumentException("ID does not reference a View inside this View");
    }

    @Deprecated
    public static void h(View view, float f2) {
        view.setRotationY(f2);
    }

    public static boolean h(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.isImportantForAccessibility();
        }
        return true;
    }

    public static android.support.v4.view.a.d i(@android.support.annotation.af View view) {
        AccessibilityNodeProvider accessibilityNodeProvider;
        if (Build.VERSION.SDK_INT < 16 || (accessibilityNodeProvider = view.getAccessibilityNodeProvider()) == null) {
            return null;
        }
        return new android.support.v4.view.a.d(accessibilityNodeProvider);
    }

    @Deprecated
    public static void i(View view, float f2) {
        view.setScaleX(f2);
    }

    public static void i(@android.support.annotation.af View view, int i2) {
        if (Build.VERSION.SDK_INT >= 19) {
            view.setAccessibilityLiveRegion(i2);
        }
    }

    @Deprecated
    public static float j(View view) {
        return view.getAlpha();
    }

    @Deprecated
    public static void j(View view, float f2) {
        view.setScaleY(f2);
    }

    public static boolean j(@android.support.annotation.af View view, int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.startNestedScroll(i2);
        }
        if (view instanceof o) {
            return ((o) view).startNestedScroll(i2);
        }
        return false;
    }

    @Deprecated
    public static int k(View view) {
        return view.getLayerType();
    }

    @Deprecated
    public static void k(View view, float f2) {
        view.setPivotX(f2);
    }

    public static void k(@android.support.annotation.af View view, int i2) {
        if (view instanceof p) {
            ((p) view).a(i2);
        } else if (i2 == 0) {
            Y(view);
        }
    }

    public static int l(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getLabelFor();
        }
        return 0;
    }

    @Deprecated
    public static void l(View view, float f2) {
        view.setPivotY(f2);
    }

    public static boolean l(@android.support.annotation.af View view, int i2) {
        if (view instanceof p) {
            ((p) view).b(i2);
            return false;
        } else if (i2 == 0) {
            return Z(view);
        } else {
            return false;
        }
    }

    public static int m(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getLayoutDirection();
        }
        return 0;
    }

    public static void m(@android.support.annotation.af View view, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setElevation(f2);
        }
    }

    public static void m(@android.support.annotation.af View view, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.offsetTopAndBottom(i2);
        } else if (Build.VERSION.SDK_INT < 21) {
            q(view, i2);
        } else {
            Rect b2 = b();
            boolean z2 = false;
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                View view2 = (View) parent;
                b2.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
                z2 = !b2.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
            q(view, i2);
            if (z2 && b2.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                ((View) parent).invalidate(b2);
            }
        }
    }

    public static ViewParent n(@android.support.annotation.af View view) {
        return Build.VERSION.SDK_INT >= 16 ? view.getParentForAccessibility() : view.getParent();
    }

    public static void n(@android.support.annotation.af View view, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setTranslationZ(f2);
        }
    }

    public static void n(@android.support.annotation.af View view, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.offsetLeftAndRight(i2);
        } else if (Build.VERSION.SDK_INT < 21) {
            r(view, i2);
        } else {
            Rect b2 = b();
            boolean z2 = false;
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                View view2 = (View) parent;
                b2.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
                z2 = !b2.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
            r(view, i2);
            if (z2 && b2.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                ((View) parent).invalidate(b2);
            }
        }
    }

    public static void o(@android.support.annotation.af View view, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setZ(f2);
        }
    }

    public static void o(@android.support.annotation.af View view, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i2);
        }
    }

    @Deprecated
    public static boolean o(View view) {
        return view.isOpaque();
    }

    @Deprecated
    public static int p(View view) {
        return view.getMeasuredWidthAndState();
    }

    public static void p(@android.support.annotation.af View view, int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setNextClusterForwardId(i2);
        }
    }

    @Deprecated
    public static int q(View view) {
        return view.getMeasuredHeightAndState();
    }

    private static void q(View view, int i2) {
        view.offsetTopAndBottom(i2);
        if (view.getVisibility() == 0) {
            ap(view);
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                ap((View) parent);
            }
        }
    }

    @Deprecated
    public static int r(View view) {
        return view.getMeasuredState();
    }

    private static void r(View view, int i2) {
        view.offsetLeftAndRight(i2);
        if (view.getVisibility() == 0) {
            ap(view);
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                ap((View) parent);
            }
        }
    }

    public static int s(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            return view.getAccessibilityLiveRegion();
        }
        return 0;
    }

    @android.support.annotation.ai
    public static int t(@android.support.annotation.af View view) {
        return Build.VERSION.SDK_INT >= 17 ? view.getPaddingStart() : view.getPaddingLeft();
    }

    @android.support.annotation.ai
    public static int u(@android.support.annotation.af View view) {
        return Build.VERSION.SDK_INT >= 17 ? view.getPaddingEnd() : view.getPaddingRight();
    }

    public static void v(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 24) {
            view.dispatchStartTemporaryDetach();
            return;
        }
        if (!O) {
            c();
        }
        Method method = M;
        if (method == null) {
            view.onStartTemporaryDetach();
            return;
        }
        try {
            method.invoke(view, new Object[0]);
        } catch (Exception e2) {
            Log.d(G, "Error calling dispatchStartTemporaryDetach", e2);
        }
    }

    public static void w(@android.support.annotation.af View view) {
        if (Build.VERSION.SDK_INT >= 24) {
            view.dispatchFinishTemporaryDetach();
            return;
        }
        if (!O) {
            c();
        }
        Method method = N;
        if (method == null) {
            view.onFinishTemporaryDetach();
            return;
        }
        try {
            method.invoke(view, new Object[0]);
        } catch (Exception e2) {
            Log.d(G, "Error calling dispatchFinishTemporaryDetach", e2);
        }
    }

    @Deprecated
    public static float x(View view) {
        return view.getTranslationX();
    }

    @Deprecated
    public static float y(View view) {
        return view.getTranslationY();
    }

    @android.support.annotation.ag
    @Deprecated
    public static Matrix z(View view) {
        return view.getMatrix();
    }
}
