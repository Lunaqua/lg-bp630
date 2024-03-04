package com.lge.media.launcher.syncstatus;

import android.content.Context;
import android.support.annotation.af;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lge.UDAP.ROAP.e.b;
import com.lge.media.launcher.b;
import java.util.ArrayList;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class f extends RecyclerView.a<a> {
    Context a;
    int b;
    ArrayList<d> c;
    Boolean d;
    float e;
    b f;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a extends RecyclerView.x {
        TextView F;
        b G;

        public a(View view, final b bVar) {
            super(view);
            this.F = (TextView) view.findViewById(b.h.textview_device);
            if (bVar != null) {
                this.G = bVar;
                view.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.f.a.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        bVar.a(view2, a.this.f(), a.this.h());
                    }
                });
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface b {
        void a(View view, int i, long j);
    }

    public f(Context context, int i, ArrayList<d> arrayList, b bVar, Boolean bool, float f) {
        this.a = context;
        this.b = i;
        this.c = arrayList;
        this.d = bool;
        this.e = f;
        this.f = bVar;
        com.lge.media.launcher.control.common.a.c("main sync list adapter created.." + arrayList.size());
    }

    @Override // android.support.v7.widget.RecyclerView.a
    public int a() {
        return this.c.size();
    }

    public d a(int i) {
        return this.c.get(i);
    }

    @Override // android.support.v7.widget.RecyclerView.a
    public void a(a aVar, int i) {
        String str;
        TextView textView;
        int i2;
        d dVar = this.c.get(i);
        StringBuilder sb = new StringBuilder();
        if (dVar.b.length() > 22) {
            sb.append(dVar.b.substring(0, 20));
            str = "...";
        } else {
            str = dVar.b;
        }
        sb.append(str);
        aVar.F.setText(sb.toString());
        String str2 = dVar.d;
        char c = 65535;
        int hashCode = str2.hashCode();
        if (hashCode != 84324) {
            if (hashCode != 2098581) {
                if (hashCode == 2101307 && str2.equals("DLNA")) {
                    c = 2;
                }
            } else if (str2.equals("DISC")) {
                c = 0;
            }
        } else if (str2.equals(b.a.C0058b.C0059a.C0060a.c.c)) {
            c = 1;
        }
        if (c == 0) {
            textView = aVar.F;
            i2 = b.g.btn_bpdisc;
        } else if (c == 1) {
            textView = aVar.F;
            i2 = b.g.btn_usb;
        } else if (c != 2) {
            textView = aVar.F;
            i2 = b.g.btn_device_list;
        } else {
            textView = aVar.F;
            i2 = b.g.btn_dlna;
        }
        textView.setBackgroundResource(i2);
    }

    @Override // android.support.v7.widget.RecyclerView.a
    public long c(int i) {
        return i;
    }

    @Override // android.support.v7.widget.RecyclerView.a
    @af
    /* renamed from: c */
    public a a(@af ViewGroup viewGroup, int i) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(this.b, viewGroup, false), this.f);
    }
}
