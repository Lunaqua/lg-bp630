package com.lge.media.launcher.syncstatus;

import android.content.Context;
import android.widget.BaseAdapter;
import java.util.ArrayList;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class e extends BaseAdapter {
    Context a;
    int b;
    ArrayList<d> c;
    Boolean d;
    float e;

    public e(Context context, int i, ArrayList<d> arrayList, Boolean bool, float f) {
        this.a = context;
        this.b = i;
        this.c = arrayList;
        this.d = bool;
        this.e = f;
        com.lge.media.launcher.control.common.a.c("context list adapter created.." + arrayList.size());
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.c.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.c.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x016b  */
    @Override // android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View getView(int r10, android.view.View r11, android.view.ViewGroup r12) {
        /*
            Method dump skipped, instructions count: 427
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.syncstatus.e.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return !"DIM".equals(this.c.get(i).c);
    }
}
