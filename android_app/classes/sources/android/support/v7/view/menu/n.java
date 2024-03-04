package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class n implements p, t, AdapterView.OnItemClickListener {
    private Rect a;

    /* JADX INFO: Access modifiers changed from: protected */
    public static int a(ListAdapter listAdapter, ViewGroup viewGroup, Context context, int i) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        ViewGroup viewGroup2 = viewGroup;
        View view = null;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < count; i4++) {
            int itemViewType = listAdapter.getItemViewType(i4);
            if (itemViewType != i3) {
                view = null;
                i3 = itemViewType;
            }
            if (viewGroup2 == null) {
                viewGroup2 = new FrameLayout(context);
            }
            view = listAdapter.getView(i4, view, viewGroup2);
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i) {
                return i;
            }
            if (measuredWidth > i2) {
                i2 = measuredWidth;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static g a(ListAdapter listAdapter) {
        return listAdapter instanceof HeaderViewListAdapter ? (g) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter() : (g) listAdapter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean b(h hVar) {
        int size = hVar.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = hVar.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    @Override // android.support.v7.view.menu.p
    public q a(ViewGroup viewGroup) {
        throw new UnsupportedOperationException("MenuPopups manage their own views");
    }

    public abstract void a(int i);

    @Override // android.support.v7.view.menu.p
    public void a(@af Context context, @ag h hVar) {
    }

    public void a(Rect rect) {
        this.a = rect;
    }

    public abstract void a(h hVar);

    public abstract void a(View view);

    public abstract void a(PopupWindow.OnDismissListener onDismissListener);

    public abstract void a(boolean z);

    @Override // android.support.v7.view.menu.p
    public boolean a(h hVar, k kVar) {
        return false;
    }

    public abstract void b(int i);

    @Override // android.support.v7.view.menu.p
    public boolean b(h hVar, k kVar) {
        return false;
    }

    @Override // android.support.v7.view.menu.p
    public int c() {
        return 0;
    }

    public abstract void c(int i);

    public abstract void c(boolean z);

    protected boolean h() {
        return true;
    }

    public Rect i() {
        return this.a;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        a(listAdapter).a.a((MenuItem) listAdapter.getItem(i), this, h() ? 0 : 4);
    }
}
