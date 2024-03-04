package com.lge.media.launcher.syncstatus;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lge.media.launcher.b;
import java.util.ArrayList;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a extends BaseAdapter {
    Context a;
    int b;
    ArrayList<d> c;
    String d;
    Boolean e;
    float f;
    boolean g = false;

    /* renamed from: com.lge.media.launcher.syncstatus.a$a  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static class C0066a {
        ImageView a;
        TextView b;
        LinearLayout c;

        C0066a() {
        }
    }

    public a(Context context, int i, ArrayList<d> arrayList, String str, Boolean bool, float f) {
        this.a = context;
        this.b = i;
        this.c = arrayList;
        this.e = bool;
        this.f = f;
        this.d = str;
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

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        final C0066a c0066a;
        TextView textView;
        ImageView imageView;
        int i2;
        if (view == null) {
            c0066a = new C0066a();
            view2 = ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(this.b, (ViewGroup) null, false);
            c0066a.a = (ImageView) view2.findViewById(b.h.image_device);
            c0066a.b = (TextView) view2.findViewById(b.h.text_device);
            c0066a.c = (LinearLayout) view2.findViewById(b.h.item_layout);
            view2.setTag(c0066a);
        } else {
            view2 = view;
            c0066a = (C0066a) view.getTag();
        }
        int i3 = 1;
        if (!this.g) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.lge.media.launcher.syncstatus.a.1
                @Override // java.lang.Runnable
                public void run() {
                    c0066a.c.sendAccessibilityEvent(8);
                }
            }, 200L);
            this.g = true;
        }
        if (c0066a.c.getContext().getResources().getConfiguration().fontScale > 1.0f) {
            textView = c0066a.b;
            i3 = 3;
        } else {
            textView = c0066a.b;
        }
        textView.setMaxLines(i3);
        String str = this.c.get(i).d;
        String str2 = this.c.get(i).b;
        if (str2 != null) {
            c0066a.b.setText(str2);
            c0066a.b.setContentDescription(str2);
            if (str.equals("FOLDER")) {
                c0066a.a.setImageResource(b.g.icon_folder);
                TextView textView2 = c0066a.b;
                textView2.setContentDescription(str2 + " " + this.a.getString(b.m.label_folder));
            } else {
                if (str.equals("UPFOLDER")) {
                    imageView = c0066a.a;
                    i2 = b.g.icon_folder_top;
                } else if (str.equals("MUSIC")) {
                    imageView = c0066a.a;
                    i2 = b.g.bg_default_music;
                } else if (str.equals("MOVIE")) {
                    imageView = c0066a.a;
                    i2 = b.g.bg_default_movie;
                } else if (str.equals("PHOTO")) {
                    imageView = c0066a.a;
                    i2 = b.g.bg_default_photo;
                } else if (str.equals("SUBTITLE")) {
                    imageView = c0066a.a;
                    i2 = b.g.bg_default_sub;
                }
                imageView.setImageResource(i2);
            }
            view2.setBackgroundColor(0);
            if (i % 2 == 0) {
                view2.setBackgroundResource(b.g.bg_content_list);
            }
        }
        if (this.e.booleanValue()) {
            Bitmap decodeResource = BitmapFactory.decodeResource(this.a.getResources(), b.g.icon_folder);
            c0066a.b.setTextSize(this.f * 17.0f);
            ViewGroup.LayoutParams layoutParams = c0066a.a.getLayoutParams();
            layoutParams.width = (int) (decodeResource.getWidth() * this.f);
            layoutParams.height = (int) (decodeResource.getHeight() * this.f);
            c0066a.a.setLayoutParams(layoutParams);
        }
        return view2;
    }
}
