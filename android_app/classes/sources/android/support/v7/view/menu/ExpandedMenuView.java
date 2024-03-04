package android.support.v7.view.menu;

import android.content.Context;
import android.support.annotation.an;
import android.support.v7.view.menu.h;
import android.support.v7.widget.av;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class ExpandedMenuView extends ListView implements h.b, q, AdapterView.OnItemClickListener {
    private static final int[] a = {16842964, 16843049};
    private h b;
    private int c;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        av a2 = av.a(context, attributeSet, a, i, 0);
        if (a2.j(0)) {
            setBackgroundDrawable(a2.a(0));
        }
        if (a2.j(1)) {
            setDivider(a2.a(1));
        }
        a2.e();
    }

    @Override // android.support.v7.view.menu.q
    public void a(h hVar) {
        this.b = hVar;
    }

    @Override // android.support.v7.view.menu.h.b
    public boolean a(k kVar) {
        return this.b.a(kVar, 0);
    }

    @Override // android.support.v7.view.menu.q
    public int getWindowAnimations() {
        return this.c;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        a((k) getAdapter().getItem(i));
    }
}
