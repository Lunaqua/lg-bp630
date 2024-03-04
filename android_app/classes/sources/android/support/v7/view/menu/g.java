package android.support.v7.view.menu;

import android.support.annotation.an;
import android.support.v7.view.menu.q;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class g extends BaseAdapter {
    h a;
    private int b = -1;
    private boolean c;
    private final boolean d;
    private final LayoutInflater e;
    private final int f;

    public g(h hVar, LayoutInflater layoutInflater, boolean z, int i) {
        this.d = z;
        this.e = layoutInflater;
        this.a = hVar;
        this.f = i;
        c();
    }

    @Override // android.widget.Adapter
    /* renamed from: a */
    public k getItem(int i) {
        ArrayList<k> n = this.d ? this.a.n() : this.a.k();
        int i2 = this.b;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return n.get(i);
    }

    public void a(boolean z) {
        this.c = z;
    }

    public boolean a() {
        return this.c;
    }

    public h b() {
        return this.a;
    }

    void c() {
        k t = this.a.t();
        if (t != null) {
            ArrayList<k> n = this.a.n();
            int size = n.size();
            for (int i = 0; i < size; i++) {
                if (n.get(i) == t) {
                    this.b = i;
                    return;
                }
            }
        }
        this.b = -1;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList<k> n = this.d ? this.a.n() : this.a.k();
        return this.b < 0 ? n.size() : n.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.e.inflate(this.f, viewGroup, false);
        }
        int groupId = getItem(i).getGroupId();
        int i2 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.a.b() && groupId != (i2 >= 0 ? getItem(i2).getGroupId() : groupId));
        q.a aVar = (q.a) view;
        if (this.c) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.a(getItem(i), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        c();
        super.notifyDataSetChanged();
    }
}
