package android.support.v7.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.support.v7.view.menu.p;
import android.support.v7.view.menu.q;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class f implements p, AdapterView.OnItemClickListener {
    public static final String i = "android:menu:list";
    private static final String j = "ListMenuPresenter";
    Context a;
    LayoutInflater b;
    h c;
    ExpandedMenuView d;
    int e;
    int f;
    int g;
    a h;
    private p.a k;
    private int l;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class a extends BaseAdapter {
        private int b = -1;

        public a() {
            a();
        }

        @Override // android.widget.Adapter
        /* renamed from: a */
        public k getItem(int i) {
            ArrayList<k> n = f.this.c.n();
            int i2 = i + f.this.e;
            int i3 = this.b;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return n.get(i2);
        }

        void a() {
            k t = f.this.c.t();
            if (t != null) {
                ArrayList<k> n = f.this.c.n();
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
            int size = f.this.c.n().size() - f.this.e;
            return this.b < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = f.this.b.inflate(f.this.g, viewGroup, false);
            }
            ((q.a) view).a(getItem(i), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public f(int i2, int i3) {
        this.g = i2;
        this.f = i3;
    }

    public f(Context context, int i2) {
        this(i2, 0);
        this.a = context;
        this.b = LayoutInflater.from(this.a);
    }

    @Override // android.support.v7.view.menu.p
    public q a(ViewGroup viewGroup) {
        if (this.d == null) {
            this.d = (ExpandedMenuView) this.b.inflate(a.i.abc_expanded_menu_layout, viewGroup, false);
            if (this.h == null) {
                this.h = new a();
            }
            this.d.setAdapter((ListAdapter) this.h);
            this.d.setOnItemClickListener(this);
        }
        return this.d;
    }

    public ListAdapter a() {
        if (this.h == null) {
            this.h = new a();
        }
        return this.h;
    }

    public void a(int i2) {
        this.e = i2;
        if (this.d != null) {
            b(false);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r2.b == null) goto L4;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // android.support.v7.view.menu.p
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(android.content.Context r3, android.support.v7.view.menu.h r4) {
        /*
            r2 = this;
            int r0 = r2.f
            if (r0 == 0) goto L14
            android.view.ContextThemeWrapper r1 = new android.view.ContextThemeWrapper
            r1.<init>(r3, r0)
            r2.a = r1
        Lb:
            android.content.Context r3 = r2.a
            android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r3)
            r2.b = r3
            goto L1f
        L14:
            android.content.Context r0 = r2.a
            if (r0 == 0) goto L1f
            r2.a = r3
            android.view.LayoutInflater r3 = r2.b
            if (r3 != 0) goto L1f
            goto Lb
        L1f:
            r2.c = r4
            android.support.v7.view.menu.f$a r3 = r2.h
            if (r3 == 0) goto L28
            r3.notifyDataSetChanged()
        L28:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.menu.f.a(android.content.Context, android.support.v7.view.menu.h):void");
    }

    public void a(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.d;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray(i, sparseArray);
    }

    @Override // android.support.v7.view.menu.p
    public void a(Parcelable parcelable) {
        b((Bundle) parcelable);
    }

    @Override // android.support.v7.view.menu.p
    public void a(h hVar, boolean z) {
        p.a aVar = this.k;
        if (aVar != null) {
            aVar.a(hVar, z);
        }
    }

    @Override // android.support.v7.view.menu.p
    public void a(p.a aVar) {
        this.k = aVar;
    }

    @Override // android.support.v7.view.menu.p
    public boolean a(h hVar, k kVar) {
        return false;
    }

    @Override // android.support.v7.view.menu.p
    public boolean a(v vVar) {
        if (vVar.hasVisibleItems()) {
            new i(vVar).a((IBinder) null);
            p.a aVar = this.k;
            if (aVar != null) {
                aVar.a(vVar);
                return true;
            }
            return true;
        }
        return false;
    }

    public void b(int i2) {
        this.l = i2;
    }

    public void b(Bundle bundle) {
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(i);
        if (sparseParcelableArray != null) {
            this.d.restoreHierarchyState(sparseParcelableArray);
        }
    }

    @Override // android.support.v7.view.menu.p
    public void b(boolean z) {
        a aVar = this.h;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // android.support.v7.view.menu.p
    public boolean b() {
        return false;
    }

    @Override // android.support.v7.view.menu.p
    public boolean b(h hVar, k kVar) {
        return false;
    }

    @Override // android.support.v7.view.menu.p
    public int c() {
        return this.l;
    }

    int d() {
        return this.e;
    }

    @Override // android.support.v7.view.menu.p
    public Parcelable f() {
        if (this.d == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        a(bundle);
        return bundle;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        this.c.a(this.h.getItem(i2), this, 0);
    }
}
