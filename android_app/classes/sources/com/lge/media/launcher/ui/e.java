package com.lge.media.launcher.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.lge.media.launcher.b;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class e {
    private static final e a = new e();
    private static Context c;
    private static View d;
    private PopupWindow b;

    protected e() {
    }

    public static e a(Context context) {
        c = context;
        return a;
    }

    public static e a(Context context, View view) {
        c = context;
        d = view;
        return a;
    }

    public void a() {
        com.lge.media.launcher.control.common.a.a("close popup");
        PopupWindow popupWindow = this.b;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.b.dismiss();
    }

    public void a(boolean z, int i, int i2, int i3, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, boolean z2, String str, float f) {
        View inflate = ((LayoutInflater) c.getSystemService("layout_inflater")).inflate(b.j.popup, (ViewGroup) null, false);
        TextView textView = (TextView) inflate.findViewById(b.h.text_popup_content);
        Button button = (Button) inflate.findViewById(b.h.btn_popup_one);
        Button button2 = (Button) inflate.findViewById(b.h.btn_popup_two);
        if (!z2) {
            textView.setText(i);
            button.setText(i2);
            if (z) {
                button2.setText(i3);
            } else {
                button2.setVisibility(8);
            }
        }
        button.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener2);
        this.b = new PopupWindow(inflate, -1, -1, true);
        this.b.setTouchable(true);
        this.b.setAnimationStyle(16973826);
        this.b.showAtLocation(d, 17, 0, 0);
    }
}
