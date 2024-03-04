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
public class e extends BaseAdapter {
    ArrayList<a> a;
    LayoutInflater b;
    final int c = 2;

    public e(Context context, ArrayList<a> arrayList) {
        this.a = arrayList;
        this.b = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.a.size() > 2) {
            return 2;
        }
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
        if (view == null) {
            view = this.b.inflate(b.j.cplist_item_bottom, viewGroup, false);
        }
        ImageView imageView = (ImageView) view.findViewById(b.h.cp_list_item_icon);
        ((TextView) view.findViewById(b.h.cplist_item_name)).setText(this.a.get(i).b());
        if (this.a.get(i).c() != null) {
            imageView.setImageBitmap(this.a.get(i).c());
        } else {
            imageView.setImageResource(b.g.home_mlb_html5);
        }
        return view;
    }
}
