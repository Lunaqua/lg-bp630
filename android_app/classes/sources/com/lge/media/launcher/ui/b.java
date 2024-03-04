package com.lge.media.launcher.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.lge.media.launcher.b;
import java.util.ArrayList;
import java.util.Locale;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class b extends BaseAdapter {
    Context a;
    int b;
    ArrayList<a> c;
    Boolean d;
    float e;

    public b(Context context, int i, ArrayList<a> arrayList, Boolean bool, float f) {
        this.a = context;
        this.b = i;
        this.c = arrayList;
        this.d = bool;
        this.e = f;
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
        int i2;
        int i3;
        View inflate = view == null ? ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(this.b, (ViewGroup) null, false) : view;
        ImageView imageView = (ImageView) inflate.findViewById(b.h.image_device);
        TextView textView = (TextView) inflate.findViewById(b.h.text_device);
        if (this.c.get(i).b()) {
            com.lge.UDAP.a.c a = this.c.get(i).a();
            if (a != null) {
                textView.setText(com.lge.media.launcher.control.common.e.f(a.g()) + " (" + a.k() + ")");
                textView.setContentDescription(com.lge.media.launcher.control.common.e.f(a.g()) + " (" + a.k() + "), " + this.a.getString(b.m.label_device_power_on));
                String lowerCase = a.g().toLowerCase(Locale.ENGLISH);
                if (lowerCase.contains(com.lge.media.launcher.control.common.d.A)) {
                    i3 = b.g.icon_disc;
                } else if (lowerCase.contains(com.lge.media.launcher.control.common.d.B)) {
                    imageView.setImageResource(b.g.icon_speaker);
                } else if (lowerCase.contains(com.lge.media.launcher.control.common.d.C)) {
                    i3 = b.g.icon_hr;
                } else if (lowerCase.contains(com.lge.media.launcher.control.common.d.D)) {
                    i3 = b.g.icon_smartbox;
                } else if (lowerCase.contains(com.lge.media.launcher.control.common.d.F)) {
                    i3 = b.g.icon_sound_bar;
                } else if (lowerCase.contains(com.lge.media.launcher.control.common.d.G)) {
                    i3 = b.g.icon_bdplate;
                }
                imageView.setImageResource(i3);
            }
            inflate.setBackgroundColor(0);
            if (i % 2 == 1) {
                try {
                    inflate.setBackgroundResource(b.g.bg_content_list_b);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (this.d.booleanValue()) {
                Bitmap decodeResource = BitmapFactory.decodeResource(this.a.getResources(), b.g.icon_speaker);
                textView.setTextSize(this.e * 17.0f);
                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                layoutParams.width = (int) (decodeResource.getWidth() * this.e);
                layoutParams.height = (int) (decodeResource.getHeight() * this.e);
                imageView.setLayoutParams(layoutParams);
            }
        } else {
            textView.setText(com.lge.media.launcher.control.common.e.f(this.c.get(i).c()) + " (" + this.c.get(i).e() + ")");
            textView.setContentDescription(com.lge.media.launcher.control.common.e.f(this.c.get(i).c()) + " (" + this.c.get(i).e() + "), " + this.a.getString(b.m.label_device_power_off));
            String lowerCase2 = this.c.get(i).c().toLowerCase(Locale.ENGLISH);
            if (lowerCase2.contains(com.lge.media.launcher.control.common.d.A)) {
                i2 = b.g.icon_disc_off;
            } else if (lowerCase2.contains(com.lge.media.launcher.control.common.d.B)) {
                i2 = b.g.icon_speaker_off;
            } else if (lowerCase2.contains(com.lge.media.launcher.control.common.d.C)) {
                i2 = b.g.icon_hr_off;
            } else if (lowerCase2.contains(com.lge.media.launcher.control.common.d.D)) {
                i2 = b.g.icon_smartbox_off;
            } else if (lowerCase2.contains(com.lge.media.launcher.control.common.d.F)) {
                i2 = b.g.icon_sound_bar_off;
            } else if (lowerCase2.contains(com.lge.media.launcher.control.common.d.G)) {
                i2 = b.g.icon_bdplate_off;
            }
            imageView.setImageResource(i2);
        }
        return inflate;
    }
}
