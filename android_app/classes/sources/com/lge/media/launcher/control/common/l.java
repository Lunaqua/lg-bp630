package com.lge.media.launcher.control.common;

import android.content.Context;
import android.support.annotation.af;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class l extends RecyclerView.a<b<k>> {
    a a;
    Context b;
    ArrayList<k> c;
    Boolean d;
    float e;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        void a(View view, int i, long j);
    }

    public l(Context context, ArrayList<k> arrayList, a aVar, Boolean bool, float f) {
        this.b = context;
        this.c = arrayList;
        this.d = bool;
        this.e = f;
        this.a = aVar;
        com.lge.media.launcher.control.common.a.c("main sync list adapter created.." + arrayList.size());
    }

    @Override // android.support.v7.widget.RecyclerView.a
    public int a() {
        return this.c.size();
    }

    public k a(int i) {
        return this.c.get(i);
    }

    @Override // android.support.v7.widget.RecyclerView.a
    public void a(@af b<k> bVar, int i) {
        bVar.b((b<k>) this.c.get(i));
    }

    @Override // android.support.v7.widget.RecyclerView.a
    public int b(int i) {
        return this.c.get(i).a;
    }

    @Override // android.support.v7.widget.RecyclerView.a
    public long c(int i) {
        return i;
    }

    @Override // android.support.v7.widget.RecyclerView.a
    @af
    /* renamed from: c */
    public b<k> a(@af ViewGroup viewGroup, int i) {
        return i.a(viewGroup, this.a);
    }
}
