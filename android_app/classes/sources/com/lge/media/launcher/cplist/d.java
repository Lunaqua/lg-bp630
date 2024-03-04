package com.lge.media.launcher.cplist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.lge.media.launcher.b;
import com.lge.media.launcher.control.common.MainAct;
import com.lge.media.launcher.control.common.g;
import com.lge.media.launcher.cplist.gridview.CPGridView;
import com.lge.media.launcher.cplist.gridview.FavoriteGridView;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class d extends g {
    private static ArrayList<a> j;
    private ViewGroup a;
    private LinearLayout b;
    private CPGridView c;
    private FavoriteGridView d;
    private TextView e;
    private b f;
    private b h;
    private final int i;
    private HashMap<String, Integer> k;
    private final AdapterView.OnItemClickListener l;
    private final AdapterView.OnItemLongClickListener m;
    private final AdapterView.OnItemClickListener n;
    private final AdapterView.OnItemLongClickListener o;

    public d(Context context, ViewGroup viewGroup) {
        super(context, viewGroup);
        this.i = 4;
        this.k = new HashMap<>();
        this.l = new AdapterView.OnItemClickListener() { // from class: com.lge.media.launcher.cplist.d.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j2) {
                if (MainAct.h) {
                    return;
                }
                try {
                    d.this.g.g.b.h(Integer.parseInt(d.this.f.a.get(i).a()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        };
        this.m = new AdapterView.OnItemLongClickListener() { // from class: com.lge.media.launcher.cplist.d.2
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j2) {
                int count;
                if (MainAct.h || (count = d.this.h.getCount()) == 4) {
                    return false;
                }
                for (int i2 = 0; i2 < count; i2++) {
                    if (((a) d.this.h.getItem(i2)).b().equals(((a) d.this.f.getItem(i)).b())) {
                        return false;
                    }
                }
                d dVar = d.this;
                a a = dVar.a(dVar.f.a.get(i).b());
                a.d = true;
                d.this.h.a.add(a);
                d.this.f.a.remove(i);
                d.this.d.setNumColumns(count + 1);
                d.this.h.notifyDataSetChanged();
                d.this.f.notifyDataSetChanged();
                d.this.d();
                d.this.setVisibleDragHere(false);
                return true;
            }
        };
        this.n = new AdapterView.OnItemClickListener() { // from class: com.lge.media.launcher.cplist.d.3
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j2) {
                if (MainAct.h) {
                    return;
                }
                try {
                    d.this.g.g.b.h(Integer.parseInt(d.this.h.a.get(i).a()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        };
        this.o = new AdapterView.OnItemLongClickListener() { // from class: com.lge.media.launcher.cplist.d.4
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j2) {
                if (MainAct.h) {
                    return false;
                }
                d.this.h.a.remove(i);
                d.this.e();
                d.this.h.notifyDataSetChanged();
                d.this.f.notifyDataSetChanged();
                d.this.d.setNumColumns(d.this.h.getCount());
                d.this.d();
                if (d.this.h.getCount() == 0) {
                    d.this.setVisibleDragHere(true);
                }
                return true;
            }
        };
        a(viewGroup);
        a();
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public a a(String str) {
        return j.get(this.k.get(str).intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        boolean z;
        this.f.a.clear();
        for (int i = 0; i < j.size(); i++) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.h.getCount()) {
                    z = false;
                    break;
                } else if (((a) this.h.getItem(i2)).b().equals(j.get(i).b())) {
                    z = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (!z) {
                this.f.a.add(j.get(i));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVisibleDragHere(boolean z) {
        if (z) {
            this.e.setVisibility(0);
            this.d.setVisibility(4);
            return;
        }
        this.e.setVisibility(4);
        this.d.setVisibility(0);
    }

    @Override // com.lge.media.launcher.control.common.g
    protected void a() {
        Log.e(com.lge.media.launcher.control.common.d.a, "set Listeners");
        this.c.setOnItemClickListener(this.l);
        this.c.setOnItemLongClickListener(this.m);
        this.d.setOnItemClickListener(this.n);
        this.d.setOnItemLongClickListener(this.o);
    }

    @Override // com.lge.media.launcher.control.common.g
    protected void a(ViewGroup viewGroup) {
        Log.e(com.lge.media.launcher.control.common.d.a, "premium list initView");
        this.a = (ViewGroup) viewGroup.findViewById(b.h.layout_premium_page_root);
        View inflate = LayoutInflater.from(this.g).inflate(b.j.cplist_page, (ViewGroup) null, true);
        ViewGroup viewGroup2 = this.a;
        viewGroup2.addView(inflate, viewGroup2.getChildCount(), this.a.getLayoutParams());
        this.b = (LinearLayout) inflate.findViewById(b.h.progress_waiting);
        this.c = (CPGridView) inflate.findViewById(b.h.premium_list_grid_view);
        this.d = (FavoriteGridView) inflate.findViewById(b.h.premium_list_grid_view_favorite);
        this.e = (TextView) inflate.findViewById(b.h.drag_here_text_view);
        this.d.setContentDescription(this.g.getString(b.m.favotite_bar));
    }

    @Override // com.lge.media.launcher.control.common.g
    public void a(c cVar) {
        Log.d(com.lge.media.launcher.control.common.d.a, "premium list fillViews");
        this.b.setVisibility(4);
        this.c.setVisibility(0);
        j = (ArrayList) cVar.a;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < j.size(); i++) {
            this.k.put(j.get(i).b(), Integer.valueOf(i));
        }
        int i2 = 0;
        while (true) {
            boolean z = true;
            if (i2 >= j.size()) {
                break;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= this.g.k().size()) {
                    z = false;
                    break;
                } else if (this.g.k().get(i3).equals(j.get(i2).b())) {
                    break;
                } else {
                    i3++;
                }
            }
            if (!z) {
                arrayList2.add(j.get(i2));
            }
            i2++;
        }
        for (int i4 = 0; i4 < this.g.k().size(); i4++) {
            int i5 = 0;
            while (true) {
                if (i5 >= j.size()) {
                    break;
                } else if (this.g.k().get(i4).equals(j.get(i5).b())) {
                    a aVar = j.get(i5);
                    aVar.d = true;
                    arrayList.add(aVar);
                    break;
                } else {
                    i5++;
                }
            }
        }
        this.f = new b(this.g, arrayList2);
        this.c.setAdapter((ListAdapter) this.f);
        this.h = new b(this.g, arrayList);
        this.d.setAdapter((ListAdapter) this.h);
        this.d.setNumColumns(this.h.getCount());
        setVisibleDragHere(this.h.getCount() == 0);
        this.f.notifyDataSetChanged();
        this.h.notifyDataSetChanged();
    }

    @Override // com.lge.media.launcher.control.common.g
    public void b() {
        Log.d(com.lge.media.launcher.control.common.d.a, "premium list hideViews");
        this.b.setVisibility(0);
        this.c.setVisibility(4);
    }

    @Override // com.lge.media.launcher.control.common.g
    public void c() {
    }

    public void d() {
        this.g.k().clear();
        for (int i = 0; i < this.h.getCount(); i++) {
            this.g.k().add(((a) this.h.getItem(i)).b());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
