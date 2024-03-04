package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.an;
import android.support.v7.view.menu.ListMenuItemView;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import java.lang.reflect.Method;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ac extends ListPopupWindow implements ab {
    private static final String a = "MenuPopupWindow";
    private static Method b;
    private ab p;

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a extends t {
        final int d;
        final int e;
        private ab f;
        private MenuItem g;

        public a(Context context, boolean z) {
            super(context, z);
            Configuration configuration = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT < 17 || 1 != configuration.getLayoutDirection()) {
                this.d = 22;
                this.e = 21;
                return;
            }
            this.d = 21;
            this.e = 22;
        }

        @Override // android.support.v7.widget.t
        public /* bridge */ /* synthetic */ int a(int i, int i2, int i3, int i4, int i5) {
            return super.a(i, i2, i3, i4, i5);
        }

        @Override // android.support.v7.widget.t
        public /* bridge */ /* synthetic */ int a(int i, boolean z) {
            return super.a(i, z);
        }

        public void a() {
            setSelection(-1);
        }

        @Override // android.support.v7.widget.t
        public /* bridge */ /* synthetic */ boolean a(MotionEvent motionEvent, int i) {
            return super.a(motionEvent, i);
        }

        @Override // android.support.v7.widget.t, android.view.ViewGroup, android.view.View
        public /* bridge */ /* synthetic */ boolean hasFocus() {
            return super.hasFocus();
        }

        @Override // android.support.v7.widget.t, android.view.View
        public /* bridge */ /* synthetic */ boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        @Override // android.support.v7.widget.t, android.view.View
        public /* bridge */ /* synthetic */ boolean isFocused() {
            return super.isFocused();
        }

        @Override // android.support.v7.widget.t, android.view.View
        public /* bridge */ /* synthetic */ boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        @Override // android.support.v7.widget.t, android.view.View
        public boolean onHoverEvent(MotionEvent motionEvent) {
            int i;
            int pointToPosition;
            int i2;
            if (this.f != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    i = headerViewListAdapter.getHeadersCount();
                    adapter = headerViewListAdapter.getWrappedAdapter();
                } else {
                    i = 0;
                }
                android.support.v7.view.menu.g gVar = (android.support.v7.view.menu.g) adapter;
                android.support.v7.view.menu.k kVar = null;
                if (motionEvent.getAction() != 10 && (pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && (i2 = pointToPosition - i) >= 0 && i2 < gVar.getCount()) {
                    kVar = gVar.getItem(i2);
                }
                MenuItem menuItem = this.g;
                if (menuItem != kVar) {
                    android.support.v7.view.menu.h b = gVar.b();
                    if (menuItem != null) {
                        this.f.a(b, menuItem);
                    }
                    this.g = kVar;
                    if (kVar != null) {
                        this.f.b(b, kVar);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i == this.d) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            } else if (listMenuItemView == null || i != this.e) {
                return super.onKeyDown(i, keyEvent);
            } else {
                setSelection(-1);
                ((android.support.v7.view.menu.g) getAdapter()).b().b(false);
                return true;
            }
        }

        @Override // android.support.v7.widget.t, android.widget.AbsListView, android.view.View
        public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        public void setHoverListener(ab abVar) {
            this.f = abVar;
        }

        @Override // android.support.v7.widget.t, android.widget.AbsListView
        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }
    }

    static {
        try {
            b = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
        } catch (NoSuchMethodException unused) {
            Log.i(a, "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public ac(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // android.support.v7.widget.ListPopupWindow
    t a(Context context, boolean z) {
        a aVar = new a(context, z);
        aVar.setHoverListener(this);
        return aVar;
    }

    @Override // android.support.v7.widget.ab
    public void a(@android.support.annotation.af android.support.v7.view.menu.h hVar, @android.support.annotation.af MenuItem menuItem) {
        ab abVar = this.p;
        if (abVar != null) {
            abVar.a(hVar, menuItem);
        }
    }

    public void a(ab abVar) {
        this.p = abVar;
    }

    public void a(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.h.setEnterTransition((Transition) obj);
        }
    }

    @Override // android.support.v7.widget.ab
    public void b(@android.support.annotation.af android.support.v7.view.menu.h hVar, @android.support.annotation.af MenuItem menuItem) {
        ab abVar = this.p;
        if (abVar != null) {
            abVar.b(hVar, menuItem);
        }
    }

    public void b(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.h.setExitTransition((Transition) obj);
        }
    }

    public void e(boolean z) {
        Method method = b;
        if (method != null) {
            try {
                method.invoke(this.h, Boolean.valueOf(z));
            } catch (Exception unused) {
                Log.i(a, "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }
}
