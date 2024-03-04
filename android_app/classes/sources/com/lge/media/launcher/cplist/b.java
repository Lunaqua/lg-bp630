package com.lge.media.launcher.cplist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.lge.media.launcher.b;
import java.util.ArrayList;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class b extends BaseAdapter {
    ArrayList<a> a;
    LayoutInflater b;

    public b(Context context, ArrayList<a> arrayList) {
        this.a = arrayList;
        this.b = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.a.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.a.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        StringBuilder sb;
        Context context;
        int i2;
        if (view == null) {
            view = this.b.inflate(b.j.cplist_item, viewGroup, false);
        }
        ImageView imageView = (ImageView) view.findViewById(b.h.cp_list_item_icon);
        ((TextView) view.findViewById(b.h.cplist_item_name)).setText(this.a.get(i).b());
        if (this.a.get(i).c() != null) {
            imageView.setImageBitmap(this.a.get(i).c());
        } else {
            imageView.setImageResource(b.g.home_mlb_html5);
        }
        if (this.a.get(i).d) {
            sb = new StringBuilder();
            sb.append(this.a.get(i).b());
            sb.append(", ");
            context = view.getContext();
            i2 = b.m.label_remove_favorite_with_long_click;
        } else {
            sb = new StringBuilder();
            sb.append(this.a.get(i).b());
            sb.append(", ");
            context = view.getContext();
            i2 = b.m.label_add_favorite_with_long_click;
        }
        sb.append(context.getString(i2));
        view.setContentDescription(sb.toString());
        return view;
    }
}
