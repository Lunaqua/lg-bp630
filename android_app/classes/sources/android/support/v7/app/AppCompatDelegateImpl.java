package android.support.v7.app;

import android.app.Activity;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ak;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ab;
import android.support.v4.view.af;
import android.support.v4.view.ag;
import android.support.v4.view.ah;
import android.support.v4.view.h;
import android.support.v4.view.u;
import android.support.v4.view.w;
import android.support.v7.a.a;
import android.support.v7.app.a;
import android.support.v7.view.b;
import android.support.v7.view.f;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.p;
import android.support.v7.view.menu.q;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.av;
import android.support.v7.widget.ba;
import android.support.v7.widget.bd;
import android.support.v7.widget.o;
import android.support.v7.widget.v;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.lang.Thread;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class AppCompatDelegateImpl extends android.support.v7.app.e implements h.a, LayoutInflater.Factory2 {
    private static final boolean E = false;
    private static final boolean F;
    private static final String G = "appcompat:local_night_mode";
    private static final int[] H;
    private static boolean I = false;
    static final String j = ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.";
    boolean A;
    boolean B;
    boolean C;
    int D;
    private CharSequence J;
    private o K;
    private b L;
    private g M;
    private boolean O;
    private ViewGroup P;
    private TextView Q;
    private View R;
    private boolean S;
    private boolean T;
    private boolean U;
    private PanelFeatureState[] V;
    private PanelFeatureState W;
    private boolean X;
    private boolean Z;
    private e aa;
    private boolean ac;
    private Rect ad;
    private Rect ae;
    private AppCompatViewInflater af;
    final Context k;
    final Window l;
    final Window.Callback m;
    final Window.Callback n;
    final android.support.v7.app.d o;
    ActionBar p;
    MenuInflater q;
    android.support.v7.view.b r;
    ActionBarContextView s;
    PopupWindow t;
    Runnable u;
    boolean w;
    boolean x;
    boolean y;
    boolean z;
    af v = null;
    private boolean N = true;
    private int Y = -100;
    private final Runnable ab = new Runnable() { // from class: android.support.v7.app.AppCompatDelegateImpl.2
        @Override // java.lang.Runnable
        public void run() {
            if ((AppCompatDelegateImpl.this.D & 1) != 0) {
                AppCompatDelegateImpl.this.j(0);
            }
            if ((AppCompatDelegateImpl.this.D & 4096) != 0) {
                AppCompatDelegateImpl.this.j(108);
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            appCompatDelegateImpl.C = false;
            appCompatDelegateImpl.D = 0;
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class PanelFeatureState {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        ViewGroup g;
        View h;
        View i;
        android.support.v7.view.menu.h j;
        android.support.v7.view.menu.f k;
        Context l;
        boolean m;
        boolean n;
        boolean o;
        public boolean p;
        boolean q = false;
        boolean r;
        boolean s;
        Bundle t;
        Bundle u;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: android.support.v7.app.AppCompatDelegateImpl.PanelFeatureState.SavedState.1
                @Override // android.os.Parcelable.Creator
                /* renamed from: a */
                public SavedState createFromParcel(Parcel parcel) {
                    return SavedState.a(parcel, null);
                }

                @Override // android.os.Parcelable.ClassLoaderCreator
                /* renamed from: a */
                public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.a(parcel, classLoader);
                }

                @Override // android.os.Parcelable.Creator
                /* renamed from: a */
                public SavedState[] newArray(int i) {
                    return new SavedState[i];
                }
            };
            int a;
            boolean b;
            Bundle c;

            SavedState() {
            }

            static SavedState a(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.a = parcel.readInt();
                savedState.b = parcel.readInt() == 1;
                if (savedState.b) {
                    savedState.c = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.a);
                parcel.writeInt(this.b ? 1 : 0);
                if (this.b) {
                    parcel.writeBundle(this.c);
                }
            }
        }

        PanelFeatureState(int i) {
            this.a = i;
        }

        q a(p.a aVar) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                this.k = new android.support.v7.view.menu.f(this.l, a.i.abc_list_menu_item_layout);
                this.k.a(aVar);
                this.j.a(this.k);
            }
            return this.k.a(this.g);
        }

        void a(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(a.b.actionBarPopupTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            }
            newTheme.resolveAttribute(a.b.panelMenuListTheme, typedValue, true);
            newTheme.applyStyle(typedValue.resourceId != 0 ? typedValue.resourceId : a.k.Theme_AppCompat_CompactMenu, true);
            android.support.v7.view.d dVar = new android.support.v7.view.d(context, 0);
            dVar.getTheme().setTo(newTheme);
            this.l = dVar;
            TypedArray obtainStyledAttributes = dVar.obtainStyledAttributes(a.l.AppCompatTheme);
            this.b = obtainStyledAttributes.getResourceId(a.l.AppCompatTheme_panelBackground, 0);
            this.f = obtainStyledAttributes.getResourceId(a.l.AppCompatTheme_android_windowAnimationStyle, 0);
            obtainStyledAttributes.recycle();
        }

        void a(Parcelable parcelable) {
            SavedState savedState = (SavedState) parcelable;
            this.a = savedState.a;
            this.s = savedState.b;
            this.t = savedState.c;
            this.h = null;
            this.g = null;
        }

        void a(android.support.v7.view.menu.h hVar) {
            android.support.v7.view.menu.f fVar;
            android.support.v7.view.menu.h hVar2 = this.j;
            if (hVar == hVar2) {
                return;
            }
            if (hVar2 != null) {
                hVar2.b(this.k);
            }
            this.j = hVar;
            if (hVar == null || (fVar = this.k) == null) {
                return;
            }
            hVar.a(fVar);
        }

        public boolean a() {
            if (this.h == null) {
                return false;
            }
            return this.i != null || this.k.a().getCount() > 0;
        }

        public void b() {
            android.support.v7.view.menu.h hVar = this.j;
            if (hVar != null) {
                hVar.b(this.k);
            }
            this.k = null;
        }

        Parcelable c() {
            SavedState savedState = new SavedState();
            savedState.a = this.a;
            savedState.b = this.o;
            if (this.j != null) {
                savedState.c = new Bundle();
                this.j.a(savedState.c);
            }
            return savedState;
        }

        void d() {
            Bundle bundle;
            android.support.v7.view.menu.h hVar = this.j;
            if (hVar == null || (bundle = this.t) == null) {
                return;
            }
            hVar.b(bundle);
            this.t = null;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class a implements a.InterfaceC0038a {
        a() {
        }

        @Override // android.support.v7.app.a.InterfaceC0038a
        public Drawable a() {
            av a = av.a(b(), (AttributeSet) null, new int[]{a.b.homeAsUpIndicator});
            Drawable a2 = a.a(0);
            a.e();
            return a2;
        }

        @Override // android.support.v7.app.a.InterfaceC0038a
        public void a(int i) {
            ActionBar a = AppCompatDelegateImpl.this.a();
            if (a != null) {
                a.l(i);
            }
        }

        @Override // android.support.v7.app.a.InterfaceC0038a
        public void a(Drawable drawable, int i) {
            ActionBar a = AppCompatDelegateImpl.this.a();
            if (a != null) {
                a.f(drawable);
                a.l(i);
            }
        }

        @Override // android.support.v7.app.a.InterfaceC0038a
        public Context b() {
            return AppCompatDelegateImpl.this.p();
        }

        @Override // android.support.v7.app.a.InterfaceC0038a
        public boolean c() {
            ActionBar a = AppCompatDelegateImpl.this.a();
            return (a == null || (a.g() & 4) == 0) ? false : true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public final class b implements p.a {
        b() {
        }

        @Override // android.support.v7.view.menu.p.a
        public void a(android.support.v7.view.menu.h hVar, boolean z) {
            AppCompatDelegateImpl.this.b(hVar);
        }

        @Override // android.support.v7.view.menu.p.a
        public boolean a(android.support.v7.view.menu.h hVar) {
            Window.Callback o = AppCompatDelegateImpl.this.o();
            if (o != null) {
                o.onMenuOpened(108, hVar);
                return true;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class c implements b.a {
        private b.a b;

        public c(b.a aVar) {
            this.b = aVar;
        }

        @Override // android.support.v7.view.b.a
        public void a(android.support.v7.view.b bVar) {
            this.b.a(bVar);
            if (AppCompatDelegateImpl.this.t != null) {
                AppCompatDelegateImpl.this.l.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.u);
            }
            if (AppCompatDelegateImpl.this.s != null) {
                AppCompatDelegateImpl.this.s();
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                appCompatDelegateImpl.v = ab.C(appCompatDelegateImpl.s).a(0.0f);
                AppCompatDelegateImpl.this.v.a(new ah() { // from class: android.support.v7.app.AppCompatDelegateImpl.c.1
                    @Override // android.support.v4.view.ah, android.support.v4.view.ag
                    public void b(View view) {
                        AppCompatDelegateImpl.this.s.setVisibility(8);
                        if (AppCompatDelegateImpl.this.t != null) {
                            AppCompatDelegateImpl.this.t.dismiss();
                        } else if (AppCompatDelegateImpl.this.s.getParent() instanceof View) {
                            ab.Q((View) AppCompatDelegateImpl.this.s.getParent());
                        }
                        AppCompatDelegateImpl.this.s.removeAllViews();
                        AppCompatDelegateImpl.this.v.a((ag) null);
                        AppCompatDelegateImpl.this.v = null;
                    }
                });
            }
            if (AppCompatDelegateImpl.this.o != null) {
                AppCompatDelegateImpl.this.o.b(AppCompatDelegateImpl.this.r);
            }
            AppCompatDelegateImpl.this.r = null;
        }

        @Override // android.support.v7.view.b.a
        public boolean a(android.support.v7.view.b bVar, Menu menu) {
            return this.b.a(bVar, menu);
        }

        @Override // android.support.v7.view.b.a
        public boolean a(android.support.v7.view.b bVar, MenuItem menuItem) {
            return this.b.a(bVar, menuItem);
        }

        @Override // android.support.v7.view.b.a
        public boolean b(android.support.v7.view.b bVar, Menu menu) {
            return this.b.b(bVar, menu);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    class d extends android.support.v7.view.i {
        d(Window.Callback callback) {
            super(callback);
        }

        final ActionMode a(ActionMode.Callback callback) {
            f.a aVar = new f.a(AppCompatDelegateImpl.this.k, callback);
            android.support.v7.view.b a = AppCompatDelegateImpl.this.a(aVar);
            if (a != null) {
                return aVar.b(a);
            }
            return null;
        }

        @Override // android.support.v7.view.i, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.support.v7.view.i, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.a(keyEvent.getKeyCode(), keyEvent);
        }

        @Override // android.support.v7.view.i, android.view.Window.Callback
        public void onContentChanged() {
        }

        @Override // android.support.v7.view.i, android.view.Window.Callback
        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof android.support.v7.view.menu.h)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        @Override // android.support.v7.view.i, android.view.Window.Callback
        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            AppCompatDelegateImpl.this.h(i);
            return true;
        }

        @Override // android.support.v7.view.i, android.view.Window.Callback
        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            AppCompatDelegateImpl.this.g(i);
        }

        @Override // android.support.v7.view.i, android.view.Window.Callback
        public boolean onPreparePanel(int i, View view, Menu menu) {
            android.support.v7.view.menu.h hVar = menu instanceof android.support.v7.view.menu.h ? (android.support.v7.view.menu.h) menu : null;
            if (i == 0 && hVar == null) {
                return false;
            }
            if (hVar != null) {
                hVar.e(true);
            }
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (hVar != null) {
                hVar.e(false);
            }
            return onPreparePanel;
        }

        @Override // android.support.v7.view.i, android.view.Window.Callback
        @ak(a = 24)
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            PanelFeatureState a = AppCompatDelegateImpl.this.a(0, true);
            if (a != null && a.j != null) {
                menu = a.j;
            }
            super.onProvideKeyboardShortcuts(list, menu, i);
        }

        @Override // android.support.v7.view.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            return AppCompatDelegateImpl.this.j() ? a(callback) : super.onWindowStartingActionMode(callback);
        }

        @Override // android.support.v7.view.i, android.view.Window.Callback
        @ak(a = 23)
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            return (AppCompatDelegateImpl.this.j() && i == 0) ? a(callback) : super.onWindowStartingActionMode(callback, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.av
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public final class e {
        private l b;
        private boolean c;
        private BroadcastReceiver d;
        private IntentFilter e;

        e(l lVar) {
            this.b = lVar;
            this.c = lVar.a();
        }

        int a() {
            this.c = this.b.a();
            return this.c ? 2 : 1;
        }

        void b() {
            boolean a = this.b.a();
            if (a != this.c) {
                this.c = a;
                AppCompatDelegateImpl.this.k();
            }
        }

        void c() {
            d();
            if (this.d == null) {
                this.d = new BroadcastReceiver() { // from class: android.support.v7.app.AppCompatDelegateImpl.e.1
                    @Override // android.content.BroadcastReceiver
                    public void onReceive(Context context, Intent intent) {
                        e.this.b();
                    }
                };
            }
            if (this.e == null) {
                this.e = new IntentFilter();
                this.e.addAction("android.intent.action.TIME_SET");
                this.e.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.e.addAction("android.intent.action.TIME_TICK");
            }
            AppCompatDelegateImpl.this.k.registerReceiver(this.d, this.e);
        }

        void d() {
            if (this.d != null) {
                AppCompatDelegateImpl.this.k.unregisterReceiver(this.d);
                this.d = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class f extends ContentFrameLayout {
        public f(Context context) {
            super(context);
        }

        private boolean a(int i, int i2) {
            return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0 && a((int) motionEvent.getX(), (int) motionEvent.getY())) {
                AppCompatDelegateImpl.this.i(0);
                return true;
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // android.view.View
        public void setBackgroundResource(int i) {
            setBackgroundDrawable(android.support.v7.b.a.a.b(getContext(), i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public final class g implements p.a {
        g() {
        }

        @Override // android.support.v7.view.menu.p.a
        public void a(android.support.v7.view.menu.h hVar, boolean z) {
            android.support.v7.view.menu.h r = hVar.r();
            boolean z2 = r != hVar;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z2) {
                hVar = r;
            }
            PanelFeatureState a = appCompatDelegateImpl.a((Menu) hVar);
            if (a != null) {
                if (!z2) {
                    AppCompatDelegateImpl.this.a(a, z);
                    return;
                }
                AppCompatDelegateImpl.this.a(a.a, a, r);
                AppCompatDelegateImpl.this.a(a, true);
            }
        }

        @Override // android.support.v7.view.menu.p.a
        public boolean a(android.support.v7.view.menu.h hVar) {
            Window.Callback o;
            if (hVar != null || !AppCompatDelegateImpl.this.w || (o = AppCompatDelegateImpl.this.o()) == null || AppCompatDelegateImpl.this.B) {
                return true;
            }
            o.onMenuOpened(108, hVar);
            return true;
        }
    }

    static {
        F = Build.VERSION.SDK_INT < 21;
        H = new int[]{16842836};
        if (!F || I) {
            return;
        }
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: android.support.v7.app.AppCompatDelegateImpl.1
            private boolean a(Throwable th) {
                String message;
                if (!(th instanceof Resources.NotFoundException) || (message = th.getMessage()) == null) {
                    return false;
                }
                return message.contains("drawable") || message.contains("Drawable");
            }

            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                if (!a(th)) {
                    defaultUncaughtExceptionHandler.uncaughtException(thread, th);
                    return;
                }
                Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + AppCompatDelegateImpl.j);
                notFoundException.initCause(th.getCause());
                notFoundException.setStackTrace(th.getStackTrace());
                defaultUncaughtExceptionHandler.uncaughtException(thread, notFoundException);
            }
        });
        I = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppCompatDelegateImpl(Context context, Window window, android.support.v7.app.d dVar) {
        this.k = context;
        this.l = window;
        this.o = dVar;
        this.m = this.l.getCallback();
        Window.Callback callback = this.m;
        if (callback instanceof d) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        this.n = new d(callback);
        this.l.setCallback(this.n);
        av a2 = av.a(context, (AttributeSet) null, H);
        Drawable b2 = a2.b(0);
        if (b2 != null) {
            this.l.setBackgroundDrawable(b2);
        }
        a2.e();
    }

    private void A() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.P.findViewById(16908290);
        View decorView = this.l.getDecorView();
        contentFrameLayout.a(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray obtainStyledAttributes = this.k.obtainStyledAttributes(a.l.AppCompatTheme);
        obtainStyledAttributes.getValue(a.l.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(a.l.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (obtainStyledAttributes.hasValue(a.l.AppCompatTheme_windowFixedWidthMajor)) {
            obtainStyledAttributes.getValue(a.l.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (obtainStyledAttributes.hasValue(a.l.AppCompatTheme_windowFixedWidthMinor)) {
            obtainStyledAttributes.getValue(a.l.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (obtainStyledAttributes.hasValue(a.l.AppCompatTheme_windowFixedHeightMajor)) {
            obtainStyledAttributes.getValue(a.l.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (obtainStyledAttributes.hasValue(a.l.AppCompatTheme_windowFixedHeightMinor)) {
            obtainStyledAttributes.getValue(a.l.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    private void B() {
        if (this.O) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private int C() {
        int i = this.Y;
        return i != -100 ? i : l();
    }

    private void D() {
        if (this.aa == null) {
            this.aa = new e(l.a(this.k));
        }
    }

    private boolean E() {
        if (this.Z) {
            Context context = this.k;
            if (context instanceof Activity) {
                try {
                    return (context.getPackageManager().getActivityInfo(new ComponentName(this.k, this.k.getClass()), 0).configChanges & 512) == 0;
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e2);
                    return true;
                }
            }
        }
        return false;
    }

    private void a(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        int i;
        ViewGroup.LayoutParams layoutParams;
        if (panelFeatureState.o || this.B) {
            return;
        }
        if (panelFeatureState.a == 0) {
            if ((this.k.getResources().getConfiguration().screenLayout & 15) == 4) {
                return;
            }
        }
        Window.Callback o = o();
        if (o != null && !o.onMenuOpened(panelFeatureState.a, panelFeatureState.j)) {
            a(panelFeatureState, true);
            return;
        }
        WindowManager windowManager = (WindowManager) this.k.getSystemService("window");
        if (windowManager != null && b(panelFeatureState, keyEvent)) {
            if (panelFeatureState.g == null || panelFeatureState.q) {
                if (panelFeatureState.g == null) {
                    if (!a(panelFeatureState) || panelFeatureState.g == null) {
                        return;
                    }
                } else if (panelFeatureState.q && panelFeatureState.g.getChildCount() > 0) {
                    panelFeatureState.g.removeAllViews();
                }
                if (!c(panelFeatureState) || !panelFeatureState.a()) {
                    return;
                }
                ViewGroup.LayoutParams layoutParams2 = panelFeatureState.h.getLayoutParams();
                if (layoutParams2 == null) {
                    layoutParams2 = new ViewGroup.LayoutParams(-2, -2);
                }
                panelFeatureState.g.setBackgroundResource(panelFeatureState.b);
                ViewParent parent = panelFeatureState.h.getParent();
                if (parent != null && (parent instanceof ViewGroup)) {
                    ((ViewGroup) parent).removeView(panelFeatureState.h);
                }
                panelFeatureState.g.addView(panelFeatureState.h, layoutParams2);
                if (!panelFeatureState.h.hasFocus()) {
                    panelFeatureState.h.requestFocus();
                }
            } else if (panelFeatureState.i != null && (layoutParams = panelFeatureState.i.getLayoutParams()) != null && layoutParams.width == -1) {
                i = -1;
                panelFeatureState.n = false;
                WindowManager.LayoutParams layoutParams3 = new WindowManager.LayoutParams(i, -2, panelFeatureState.d, panelFeatureState.e, w.d, 8519680, -3);
                layoutParams3.gravity = panelFeatureState.c;
                layoutParams3.windowAnimations = panelFeatureState.f;
                windowManager.addView(panelFeatureState.g, layoutParams3);
                panelFeatureState.o = true;
            }
            i = -2;
            panelFeatureState.n = false;
            WindowManager.LayoutParams layoutParams32 = new WindowManager.LayoutParams(i, -2, panelFeatureState.d, panelFeatureState.e, w.d, 8519680, -3);
            layoutParams32.gravity = panelFeatureState.c;
            layoutParams32.windowAnimations = panelFeatureState.f;
            windowManager.addView(panelFeatureState.g, layoutParams32);
            panelFeatureState.o = true;
        }
    }

    private void a(android.support.v7.view.menu.h hVar, boolean z) {
        o oVar = this.K;
        if (oVar == null || !oVar.h() || (ViewConfiguration.get(this.k).hasPermanentMenuKey() && !this.K.j())) {
            PanelFeatureState a2 = a(0, true);
            a2.q = true;
            a(a2, false);
            a(a2, (KeyEvent) null);
            return;
        }
        Window.Callback o = o();
        if (this.K.i() && z) {
            this.K.l();
            if (this.B) {
                return;
            }
            o.onPanelClosed(108, a(0, true).j);
        } else if (o == null || this.B) {
        } else {
            if (this.C && (this.D & 1) != 0) {
                this.l.getDecorView().removeCallbacks(this.ab);
                this.ab.run();
            }
            PanelFeatureState a3 = a(0, true);
            if (a3.j == null || a3.r || !o.onPreparePanel(0, a3.i, a3.j)) {
                return;
            }
            o.onMenuOpened(108, a3.j);
            this.K.k();
        }
    }

    private boolean a(PanelFeatureState panelFeatureState) {
        panelFeatureState.a(p());
        panelFeatureState.g = new f(panelFeatureState.l);
        panelFeatureState.c = 81;
        return true;
    }

    private boolean a(PanelFeatureState panelFeatureState, int i, KeyEvent keyEvent, int i2) {
        boolean z = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.m || b(panelFeatureState, keyEvent)) && panelFeatureState.j != null) {
            z = panelFeatureState.j.performShortcut(i, keyEvent, i2);
        }
        if (z && (i2 & 1) == 0 && this.K == null) {
            a(panelFeatureState, true);
        }
        return z;
    }

    private boolean a(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.l.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || ab.af((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    private boolean b(PanelFeatureState panelFeatureState) {
        Context context = this.k;
        if ((panelFeatureState.a == 0 || panelFeatureState.a == 108) && this.K != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(a.b.actionBarTheme, typedValue, true);
            Resources.Theme theme2 = null;
            if (typedValue.resourceId != 0) {
                theme2 = context.getResources().newTheme();
                theme2.setTo(theme);
                theme2.applyStyle(typedValue.resourceId, true);
                theme2.resolveAttribute(a.b.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(a.b.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (theme2 == null) {
                    theme2 = context.getResources().newTheme();
                    theme2.setTo(theme);
                }
                theme2.applyStyle(typedValue.resourceId, true);
            }
            if (theme2 != null) {
                android.support.v7.view.d dVar = new android.support.v7.view.d(context, 0);
                dVar.getTheme().setTo(theme2);
                context = dVar;
            }
        }
        android.support.v7.view.menu.h hVar = new android.support.v7.view.menu.h(context);
        hVar.a(this);
        panelFeatureState.a(hVar);
        return true;
    }

    private boolean b(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        o oVar;
        o oVar2;
        o oVar3;
        if (this.B) {
            return false;
        }
        if (panelFeatureState.m) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.W;
        if (panelFeatureState2 != null && panelFeatureState2 != panelFeatureState) {
            a(panelFeatureState2, false);
        }
        Window.Callback o = o();
        if (o != null) {
            panelFeatureState.i = o.onCreatePanelView(panelFeatureState.a);
        }
        boolean z = panelFeatureState.a == 0 || panelFeatureState.a == 108;
        if (z && (oVar3 = this.K) != null) {
            oVar3.m();
        }
        if (panelFeatureState.i == null && (!z || !(n() instanceof j))) {
            if (panelFeatureState.j == null || panelFeatureState.r) {
                if (panelFeatureState.j == null && (!b(panelFeatureState) || panelFeatureState.j == null)) {
                    return false;
                }
                if (z && this.K != null) {
                    if (this.L == null) {
                        this.L = new b();
                    }
                    this.K.a(panelFeatureState.j, this.L);
                }
                panelFeatureState.j.i();
                if (!o.onCreatePanelMenu(panelFeatureState.a, panelFeatureState.j)) {
                    panelFeatureState.a((android.support.v7.view.menu.h) null);
                    if (z && (oVar = this.K) != null) {
                        oVar.a(null, this.L);
                    }
                    return false;
                }
                panelFeatureState.r = false;
            }
            panelFeatureState.j.i();
            if (panelFeatureState.u != null) {
                panelFeatureState.j.d(panelFeatureState.u);
                panelFeatureState.u = null;
            }
            if (!o.onPreparePanel(0, panelFeatureState.i, panelFeatureState.j)) {
                if (z && (oVar2 = this.K) != null) {
                    oVar2.a(null, this.L);
                }
                panelFeatureState.j.j();
                return false;
            }
            panelFeatureState.p = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            panelFeatureState.j.setQwertyMode(panelFeatureState.p);
            panelFeatureState.j.j();
        }
        panelFeatureState.m = true;
        panelFeatureState.n = false;
        this.W = panelFeatureState;
        return true;
    }

    private boolean c(PanelFeatureState panelFeatureState) {
        if (panelFeatureState.i != null) {
            panelFeatureState.h = panelFeatureState.i;
            return true;
        } else if (panelFeatureState.j == null) {
            return false;
        } else {
            if (this.M == null) {
                this.M = new g();
            }
            panelFeatureState.h = (View) panelFeatureState.a(this.M);
            return panelFeatureState.h != null;
        }
    }

    private boolean d(int i, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() == 0) {
            PanelFeatureState a2 = a(i, true);
            if (a2.o) {
                return false;
            }
            return b(a2, keyEvent);
        }
        return false;
    }

    private boolean e(int i, KeyEvent keyEvent) {
        boolean z;
        boolean z2;
        o oVar;
        if (this.r != null) {
            return false;
        }
        PanelFeatureState a2 = a(i, true);
        if (i != 0 || (oVar = this.K) == null || !oVar.h() || ViewConfiguration.get(this.k).hasPermanentMenuKey()) {
            if (a2.o || a2.n) {
                z = a2.o;
                a(a2, true);
            } else {
                if (a2.m) {
                    if (a2.r) {
                        a2.m = false;
                        z2 = b(a2, keyEvent);
                    } else {
                        z2 = true;
                    }
                    if (z2) {
                        a(a2, keyEvent);
                        z = true;
                    }
                }
                z = false;
            }
        } else if (this.K.i()) {
            z = this.K.l();
        } else {
            if (!this.B && b(a2, keyEvent)) {
                z = this.K.k();
            }
            z = false;
        }
        if (z) {
            AudioManager audioManager = (AudioManager) this.k.getSystemService("audio");
            if (audioManager != null) {
                audioManager.playSoundEffect(0);
            } else {
                Log.w("AppCompatDelegate", "Couldn't get audio manager");
            }
        }
        return z;
    }

    private void m(int i) {
        this.D = (1 << i) | this.D;
        if (this.C) {
            return;
        }
        ab.a(this.l.getDecorView(), this.ab);
        this.C = true;
    }

    private int n(int i) {
        if (i == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        } else if (i == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            return 109;
        } else {
            return i;
        }
    }

    private boolean o(int i) {
        Resources resources = this.k.getResources();
        Configuration configuration = resources.getConfiguration();
        int i2 = configuration.uiMode & 48;
        int i3 = i == 2 ? 32 : 16;
        if (i2 != i3) {
            if (E()) {
                ((Activity) this.k).recreate();
                return true;
            }
            Configuration configuration2 = new Configuration(configuration);
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            configuration2.uiMode = i3 | (configuration2.uiMode & (-49));
            resources.updateConfiguration(configuration2, displayMetrics);
            if (Build.VERSION.SDK_INT < 26) {
                i.a(resources);
                return true;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void x() {
        /*
            r3 = this;
            r3.y()
            boolean r0 = r3.w
            if (r0 == 0) goto L33
            android.support.v7.app.ActionBar r0 = r3.p
            if (r0 == 0) goto Lc
            goto L33
        Lc:
            android.view.Window$Callback r0 = r3.m
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L1e
            android.support.v7.app.m r1 = new android.support.v7.app.m
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r2 = r3.x
            r1.<init>(r0, r2)
        L1b:
            r3.p = r1
            goto L2a
        L1e:
            boolean r1 = r0 instanceof android.app.Dialog
            if (r1 == 0) goto L2a
            android.support.v7.app.m r1 = new android.support.v7.app.m
            android.app.Dialog r0 = (android.app.Dialog) r0
            r1.<init>(r0)
            goto L1b
        L2a:
            android.support.v7.app.ActionBar r0 = r3.p
            if (r0 == 0) goto L33
            boolean r1 = r3.ac
            r0.h(r1)
        L33:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImpl.x():void");
    }

    private void y() {
        if (this.O) {
            return;
        }
        this.P = z();
        CharSequence q = q();
        if (!TextUtils.isEmpty(q)) {
            o oVar = this.K;
            if (oVar != null) {
                oVar.setWindowTitle(q);
            } else if (n() != null) {
                n().d(q);
            } else {
                TextView textView = this.Q;
                if (textView != null) {
                    textView.setText(q);
                }
            }
        }
        A();
        a(this.P);
        this.O = true;
        PanelFeatureState a2 = a(0, false);
        if (this.B) {
            return;
        }
        if (a2 == null || a2.j == null) {
            m(108);
        }
    }

    private ViewGroup z() {
        ViewGroup viewGroup;
        TypedArray obtainStyledAttributes = this.k.obtainStyledAttributes(a.l.AppCompatTheme);
        if (!obtainStyledAttributes.hasValue(a.l.AppCompatTheme_windowActionBar)) {
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (obtainStyledAttributes.getBoolean(a.l.AppCompatTheme_windowNoTitle, false)) {
            c(1);
        } else if (obtainStyledAttributes.getBoolean(a.l.AppCompatTheme_windowActionBar, false)) {
            c(108);
        }
        if (obtainStyledAttributes.getBoolean(a.l.AppCompatTheme_windowActionBarOverlay, false)) {
            c(109);
        }
        if (obtainStyledAttributes.getBoolean(a.l.AppCompatTheme_windowActionModeOverlay, false)) {
            c(10);
        }
        this.z = obtainStyledAttributes.getBoolean(a.l.AppCompatTheme_android_windowIsFloating, false);
        obtainStyledAttributes.recycle();
        this.l.getDecorView();
        LayoutInflater from = LayoutInflater.from(this.k);
        if (this.A) {
            viewGroup = (ViewGroup) from.inflate(this.y ? a.i.abc_screen_simple_overlay_action_mode : a.i.abc_screen_simple, (ViewGroup) null);
            if (Build.VERSION.SDK_INT >= 21) {
                ab.a(viewGroup, new u() { // from class: android.support.v7.app.AppCompatDelegateImpl.3
                    @Override // android.support.v4.view.u
                    public android.support.v4.view.ak a(View view, android.support.v4.view.ak akVar) {
                        int b2 = akVar.b();
                        int k = AppCompatDelegateImpl.this.k(b2);
                        if (b2 != k) {
                            akVar = akVar.a(akVar.a(), k, akVar.c(), akVar.d());
                        }
                        return ab.a(view, akVar);
                    }
                });
            } else {
                ((v) viewGroup).setOnFitSystemWindowsListener(new v.a() { // from class: android.support.v7.app.AppCompatDelegateImpl.4
                    @Override // android.support.v7.widget.v.a
                    public void a(Rect rect) {
                        rect.top = AppCompatDelegateImpl.this.k(rect.top);
                    }
                });
            }
        } else if (this.z) {
            viewGroup = (ViewGroup) from.inflate(a.i.abc_dialog_title_material, (ViewGroup) null);
            this.x = false;
            this.w = false;
        } else if (this.w) {
            TypedValue typedValue = new TypedValue();
            this.k.getTheme().resolveAttribute(a.b.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new android.support.v7.view.d(this.k, typedValue.resourceId) : this.k).inflate(a.i.abc_screen_toolbar, (ViewGroup) null);
            this.K = (o) viewGroup.findViewById(a.g.decor_content_parent);
            this.K.setWindowCallback(o());
            if (this.x) {
                this.K.a(109);
            }
            if (this.S) {
                this.K.a(2);
            }
            if (this.T) {
                this.K.a(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.w + ", windowActionBarOverlay: " + this.x + ", android:windowIsFloating: " + this.z + ", windowActionModeOverlay: " + this.y + ", windowNoTitle: " + this.A + " }");
        }
        if (this.K == null) {
            this.Q = (TextView) viewGroup.findViewById(a.g.title);
        }
        bd.b(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(a.g.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.l.findViewById(16908290);
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup2.setId(-1);
            contentFrameLayout.setId(16908290);
            if (viewGroup2 instanceof FrameLayout) {
                ((FrameLayout) viewGroup2).setForeground(null);
            }
        }
        this.l.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new ContentFrameLayout.a() { // from class: android.support.v7.app.AppCompatDelegateImpl.5
            @Override // android.support.v7.widget.ContentFrameLayout.a
            public void a() {
            }

            @Override // android.support.v7.widget.ContentFrameLayout.a
            public void b() {
                AppCompatDelegateImpl.this.v();
            }
        });
        return viewGroup;
    }

    @Override // android.support.v7.app.e
    public ActionBar a() {
        x();
        return this.p;
    }

    protected PanelFeatureState a(int i, boolean z) {
        PanelFeatureState[] panelFeatureStateArr = this.V;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[i + 1];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.V = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i];
        if (panelFeatureState == null) {
            PanelFeatureState panelFeatureState2 = new PanelFeatureState(i);
            panelFeatureStateArr[i] = panelFeatureState2;
            return panelFeatureState2;
        }
        return panelFeatureState;
    }

    PanelFeatureState a(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.V;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i = 0; i < length; i++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i];
            if (panelFeatureState != null && panelFeatureState.j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    @Override // android.support.v7.app.e
    public android.support.v7.view.b a(@android.support.annotation.af b.a aVar) {
        android.support.v7.app.d dVar;
        if (aVar != null) {
            android.support.v7.view.b bVar = this.r;
            if (bVar != null) {
                bVar.c();
            }
            c cVar = new c(aVar);
            ActionBar a2 = a();
            if (a2 != null) {
                this.r = a2.a(cVar);
                android.support.v7.view.b bVar2 = this.r;
                if (bVar2 != null && (dVar = this.o) != null) {
                    dVar.a(bVar2);
                }
            }
            if (this.r == null) {
                this.r = b(cVar);
            }
            return this.r;
        }
        throw new IllegalArgumentException("ActionMode callback can not be null.");
    }

    @Override // android.support.v7.app.e
    @android.support.annotation.ag
    public <T extends View> T a(@android.support.annotation.v int i) {
        y();
        return (T) this.l.findViewById(i);
    }

    @Override // android.support.v7.app.e
    public View a(View view, String str, @android.support.annotation.af Context context, @android.support.annotation.af AttributeSet attributeSet) {
        boolean z;
        AppCompatViewInflater appCompatViewInflater;
        boolean z2 = false;
        if (this.af == null) {
            String string = this.k.obtainStyledAttributes(a.l.AppCompatTheme).getString(a.l.AppCompatTheme_viewInflaterClass);
            if (string == null || AppCompatViewInflater.class.getName().equals(string)) {
                appCompatViewInflater = new AppCompatViewInflater();
            } else {
                try {
                    this.af = (AppCompatViewInflater) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    appCompatViewInflater = new AppCompatViewInflater();
                }
            }
            this.af = appCompatViewInflater;
        }
        if (F) {
            if (!(attributeSet instanceof XmlPullParser)) {
                z2 = a((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                z2 = true;
            }
            z = z2;
        } else {
            z = false;
        }
        return this.af.a(view, str, context, attributeSet, z, F, true, ba.a());
    }

    void a(int i, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.V;
                if (i < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i];
                }
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.o) && !this.B) {
            this.m.onPanelClosed(i, menu);
        }
    }

    @Override // android.support.v7.app.e
    public void a(Configuration configuration) {
        ActionBar a2;
        if (this.w && this.O && (a2 = a()) != null) {
            a2.a(configuration);
        }
        android.support.v7.widget.g.a().a(this.k);
        k();
    }

    @Override // android.support.v7.app.e
    public void a(Bundle bundle) {
        Window.Callback callback = this.m;
        if (callback instanceof Activity) {
            String str = null;
            try {
                str = NavUtils.getParentActivityName((Activity) callback);
            } catch (IllegalArgumentException unused) {
            }
            if (str != null) {
                ActionBar n = n();
                if (n == null) {
                    this.ac = true;
                } else {
                    n.h(true);
                }
            }
        }
        if (bundle == null || this.Y != -100) {
            return;
        }
        this.Y = bundle.getInt(G, -100);
    }

    void a(PanelFeatureState panelFeatureState, boolean z) {
        o oVar;
        if (z && panelFeatureState.a == 0 && (oVar = this.K) != null && oVar.i()) {
            b(panelFeatureState.j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.k.getSystemService("window");
        if (windowManager != null && panelFeatureState.o && panelFeatureState.g != null) {
            windowManager.removeView(panelFeatureState.g);
            if (z) {
                a(panelFeatureState.a, panelFeatureState, (Menu) null);
            }
        }
        panelFeatureState.m = false;
        panelFeatureState.n = false;
        panelFeatureState.o = false;
        panelFeatureState.h = null;
        panelFeatureState.q = true;
        if (this.W == panelFeatureState) {
            this.W = null;
        }
    }

    @Override // android.support.v7.view.menu.h.a
    public void a(android.support.v7.view.menu.h hVar) {
        a(hVar, true);
    }

    @Override // android.support.v7.app.e
    public void a(Toolbar toolbar) {
        Window window;
        Window.Callback callback;
        if (this.m instanceof Activity) {
            ActionBar a2 = a();
            if (a2 instanceof m) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            this.q = null;
            if (a2 != null) {
                a2.z();
            }
            if (toolbar != null) {
                j jVar = new j(toolbar, ((Activity) this.m).getTitle(), this.n);
                this.p = jVar;
                window = this.l;
                callback = jVar.A();
            } else {
                this.p = null;
                window = this.l;
                callback = this.n;
            }
            window.setCallback(callback);
            f();
        }
    }

    @Override // android.support.v7.app.e
    public void a(View view) {
        y();
        ViewGroup viewGroup = (ViewGroup) this.P.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.m.onContentChanged();
    }

    @Override // android.support.v7.app.e
    public void a(View view, ViewGroup.LayoutParams layoutParams) {
        y();
        ViewGroup viewGroup = (ViewGroup) this.P.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.m.onContentChanged();
    }

    void a(ViewGroup viewGroup) {
    }

    @Override // android.support.v7.app.e
    public final void a(CharSequence charSequence) {
        this.J = charSequence;
        o oVar = this.K;
        if (oVar != null) {
            oVar.setWindowTitle(charSequence);
        } else if (n() != null) {
            n().d(charSequence);
        } else {
            TextView textView = this.Q;
            if (textView != null) {
                textView.setText(charSequence);
            }
        }
    }

    @Override // android.support.v7.app.e
    public void a(boolean z) {
        this.N = z;
    }

    boolean a(int i, KeyEvent keyEvent) {
        ActionBar a2 = a();
        if (a2 == null || !a2.a(i, keyEvent)) {
            PanelFeatureState panelFeatureState = this.W;
            if (panelFeatureState != null && a(panelFeatureState, keyEvent.getKeyCode(), keyEvent, 1)) {
                PanelFeatureState panelFeatureState2 = this.W;
                if (panelFeatureState2 != null) {
                    panelFeatureState2.n = true;
                }
                return true;
            }
            if (this.W == null) {
                PanelFeatureState a3 = a(0, true);
                b(a3, keyEvent);
                boolean a4 = a(a3, keyEvent.getKeyCode(), keyEvent, 1);
                a3.m = false;
                if (a4) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @Override // android.support.v7.view.menu.h.a
    public boolean a(android.support.v7.view.menu.h hVar, MenuItem menuItem) {
        PanelFeatureState a2;
        Window.Callback o = o();
        if (o == null || this.B || (a2 = a((Menu) hVar.r())) == null) {
            return false;
        }
        return o.onMenuItemSelected(a2.a, menuItem);
    }

    boolean a(KeyEvent keyEvent) {
        View decorView;
        Window.Callback callback = this.m;
        if (((callback instanceof h.a) || (callback instanceof android.support.v7.app.f)) && (decorView = this.l.getDecorView()) != null && android.support.v4.view.h.a(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.m.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? c(keyCode, keyEvent) : b(keyCode, keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    android.support.v7.view.b b(@android.support.annotation.af android.support.v7.view.b.a r8) {
        /*
            Method dump skipped, instructions count: 372
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImpl.b(android.support.v7.view.b$a):android.support.v7.view.b");
    }

    @Override // android.support.v7.app.e
    public MenuInflater b() {
        if (this.q == null) {
            x();
            ActionBar actionBar = this.p;
            this.q = new android.support.v7.view.g(actionBar != null ? actionBar.p() : this.k);
        }
        return this.q;
    }

    @Override // android.support.v7.app.e
    public void b(int i) {
        y();
        ViewGroup viewGroup = (ViewGroup) this.P.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.k).inflate(i, viewGroup);
        this.m.onContentChanged();
    }

    @Override // android.support.v7.app.e
    public void b(Bundle bundle) {
        y();
    }

    void b(android.support.v7.view.menu.h hVar) {
        if (this.U) {
            return;
        }
        this.U = true;
        this.K.n();
        Window.Callback o = o();
        if (o != null && !this.B) {
            o.onPanelClosed(108, hVar);
        }
        this.U = false;
    }

    @Override // android.support.v7.app.e
    public void b(View view, ViewGroup.LayoutParams layoutParams) {
        y();
        ((ViewGroup) this.P.findViewById(16908290)).addView(view, layoutParams);
        this.m.onContentChanged();
    }

    boolean b(int i, KeyEvent keyEvent) {
        if (i == 4) {
            boolean z = this.X;
            this.X = false;
            PanelFeatureState a2 = a(0, false);
            if (a2 != null && a2.o) {
                if (!z) {
                    a(a2, true);
                }
                return true;
            } else if (t()) {
                return true;
            }
        } else if (i == 82) {
            e(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // android.support.v7.app.e
    public void c() {
        k();
    }

    @Override // android.support.v7.app.e
    public void c(Bundle bundle) {
        int i = this.Y;
        if (i != -100) {
            bundle.putInt(G, i);
        }
    }

    @Override // android.support.v7.app.e
    public boolean c(int i) {
        int n = n(i);
        if (this.A && n == 108) {
            return false;
        }
        if (this.w && n == 1) {
            this.w = false;
        }
        if (n == 1) {
            B();
            this.A = true;
            return true;
        } else if (n == 2) {
            B();
            this.S = true;
            return true;
        } else if (n == 5) {
            B();
            this.T = true;
            return true;
        } else if (n == 10) {
            B();
            this.y = true;
            return true;
        } else if (n == 108) {
            B();
            this.w = true;
            return true;
        } else if (n != 109) {
            return this.l.requestFeature(n);
        } else {
            B();
            this.x = true;
            return true;
        }
    }

    boolean c(int i, KeyEvent keyEvent) {
        if (i == 4) {
            this.X = (keyEvent.getFlags() & 128) != 0;
        } else if (i == 82) {
            d(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // android.support.v7.app.e
    public void d() {
        ActionBar a2 = a();
        if (a2 != null) {
            a2.i(false);
        }
        e eVar = this.aa;
        if (eVar != null) {
            eVar.d();
        }
    }

    @Override // android.support.v7.app.e
    public boolean d(int i) {
        int n = n(i);
        return (n != 1 ? n != 2 ? n != 5 ? n != 10 ? n != 108 ? n != 109 ? false : this.x : this.w : this.y : this.T : this.S : this.A) || this.l.hasFeature(i);
    }

    @Override // android.support.v7.app.e
    public void e() {
        ActionBar a2 = a();
        if (a2 != null) {
            a2.i(true);
        }
    }

    @Override // android.support.v7.app.e
    public void e(int i) {
        if (i != -1 && i != 0 && i != 1 && i != 2) {
            Log.i("AppCompatDelegate", "setLocalNightMode() called with an unknown mode");
        } else if (this.Y != i) {
            this.Y = i;
            if (this.Z) {
                k();
            }
        }
    }

    @Override // android.support.v7.app.e
    public void f() {
        ActionBar a2 = a();
        if (a2 == null || !a2.w()) {
            m(0);
        }
    }

    @Override // android.support.v7.app.e
    public void g() {
        if (this.C) {
            this.l.getDecorView().removeCallbacks(this.ab);
        }
        this.B = true;
        ActionBar actionBar = this.p;
        if (actionBar != null) {
            actionBar.z();
        }
        e eVar = this.aa;
        if (eVar != null) {
            eVar.d();
        }
    }

    void g(int i) {
        if (i == 108) {
            ActionBar a2 = a();
            if (a2 != null) {
                a2.j(false);
            }
        } else if (i == 0) {
            PanelFeatureState a3 = a(i, true);
            if (a3.o) {
                a(a3, false);
            }
        }
    }

    @Override // android.support.v7.app.e
    public final a.InterfaceC0038a h() {
        return new a();
    }

    void h(int i) {
        ActionBar a2;
        if (i != 108 || (a2 = a()) == null) {
            return;
        }
        a2.j(true);
    }

    @Override // android.support.v7.app.e
    public void i() {
        LayoutInflater from = LayoutInflater.from(this.k);
        if (from.getFactory() == null) {
            android.support.v4.view.i.a(from, this);
        } else if (from.getFactory2() instanceof AppCompatDelegateImpl) {
        } else {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    void i(int i) {
        a(a(i, true), true);
    }

    void j(int i) {
        PanelFeatureState a2;
        PanelFeatureState a3 = a(i, true);
        if (a3.j != null) {
            Bundle bundle = new Bundle();
            a3.j.c(bundle);
            if (bundle.size() > 0) {
                a3.u = bundle;
            }
            a3.j.i();
            a3.j.clear();
        }
        a3.r = true;
        a3.q = true;
        if ((i != 108 && i != 0) || this.K == null || (a2 = a(0, false)) == null) {
            return;
        }
        a2.m = false;
        b(a2, (KeyEvent) null);
    }

    @Override // android.support.v7.app.e
    public boolean j() {
        return this.N;
    }

    int k(int i) {
        boolean z;
        boolean z2;
        ActionBarContextView actionBarContextView = this.s;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.s.getLayoutParams();
            if (this.s.isShown()) {
                if (this.ad == null) {
                    this.ad = new Rect();
                    this.ae = new Rect();
                }
                Rect rect = this.ad;
                Rect rect2 = this.ae;
                rect.set(0, i, 0, 0);
                bd.a(this.P, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i : 0)) {
                    marginLayoutParams.topMargin = i;
                    View view = this.R;
                    if (view == null) {
                        this.R = new View(this.k);
                        this.R.setBackgroundColor(this.k.getResources().getColor(a.d.abc_input_method_navigation_guard));
                        this.P.addView(this.R, -1, new ViewGroup.LayoutParams(-1, i));
                    } else {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        if (layoutParams.height != i) {
                            layoutParams.height = i;
                            this.R.setLayoutParams(layoutParams);
                        }
                    }
                    z2 = true;
                } else {
                    z2 = false;
                }
                z = this.R != null;
                if (!this.y && z) {
                    i = 0;
                }
            } else {
                if (marginLayoutParams.topMargin != 0) {
                    marginLayoutParams.topMargin = 0;
                    z2 = true;
                } else {
                    z2 = false;
                }
                z = false;
            }
            if (z2) {
                this.s.setLayoutParams(marginLayoutParams);
            }
        }
        View view2 = this.R;
        if (view2 != null) {
            view2.setVisibility(z ? 0 : 8);
        }
        return i;
    }

    @Override // android.support.v7.app.e
    public boolean k() {
        int C = C();
        int l = l(C);
        boolean o = l != -1 ? o(l) : false;
        if (C == 0) {
            D();
            this.aa.c();
        }
        this.Z = true;
        return o;
    }

    int l(int i) {
        if (i != -100) {
            if (i != 0) {
                return i;
            }
            if (Build.VERSION.SDK_INT < 23 || ((UiModeManager) this.k.getSystemService(UiModeManager.class)).getNightMode() != 0) {
                D();
                return this.aa.a();
            }
            return -1;
        }
        return -1;
    }

    final ActionBar n() {
        return this.p;
    }

    final Window.Callback o() {
        return this.l.getCallback();
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return a(view, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    final Context p() {
        ActionBar a2 = a();
        Context p = a2 != null ? a2.p() : null;
        return p == null ? this.k : p;
    }

    final CharSequence q() {
        Window.Callback callback = this.m;
        return callback instanceof Activity ? ((Activity) callback).getTitle() : this.J;
    }

    final boolean r() {
        ViewGroup viewGroup;
        return this.O && (viewGroup = this.P) != null && ab.ab(viewGroup);
    }

    void s() {
        af afVar = this.v;
        if (afVar != null) {
            afVar.d();
        }
    }

    boolean t() {
        android.support.v7.view.b bVar = this.r;
        if (bVar != null) {
            bVar.c();
            return true;
        }
        ActionBar a2 = a();
        return a2 != null && a2.x();
    }

    ViewGroup u() {
        return this.P;
    }

    void v() {
        o oVar = this.K;
        if (oVar != null) {
            oVar.n();
        }
        if (this.t != null) {
            this.l.getDecorView().removeCallbacks(this.u);
            if (this.t.isShowing()) {
                try {
                    this.t.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.t = null;
        }
        s();
        PanelFeatureState a2 = a(0, false);
        if (a2 == null || a2.j == null) {
            return;
        }
        a2.j.close();
    }

    @android.support.annotation.av
    final e w() {
        D();
        return this.aa;
    }
}
