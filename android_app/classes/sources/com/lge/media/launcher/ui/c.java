package com.lge.media.launcher.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.lge.media.launcher.b;
import java.util.ArrayList;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c extends BaseAdapter {
    Context a;
    int b;
    ArrayList<String> c;
    ArrayList<com.lge.media.launcher.contents.d> d;
    View.OnClickListener e;

    public c(Context context, int i, ArrayList<String> arrayList, ArrayList<com.lge.media.launcher.contents.d> arrayList2, View.OnClickListener onClickListener) {
        this.a = context;
        this.b = i;
        this.c = arrayList;
        this.d = arrayList2;
        this.e = onClickListener;
        com.lge.media.launcher.control.common.a.c("device list adapter created.." + arrayList.size());
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

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(this.b, (ViewGroup) null, false);
        }
        Button button = (Button) view.findViewById(b.h.btn_info_item_search);
        button.setOnClickListener(this.e);
        TextView textView = (TextView) view.findViewById(b.h.text_info_item);
        textView.setText(this.c.get(i));
        button.setText(textView.getText());
        button.setTextColor(0);
        ArrayList<com.lge.media.launcher.contents.d> arrayList = this.d;
        if (arrayList != null && arrayList.get(i).d != null && this.d.get(i).d.equals("play")) {
            textView.setTextColor(android.support.v4.d.a.a.c);
        }
        return view;
    }
}
