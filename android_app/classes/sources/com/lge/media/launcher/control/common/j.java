package com.lge.media.launcher.control.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lge.media.launcher.b;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class j extends b<k> {
    TextView F;

    j(View view) {
        super(view);
        this.F = (TextView) view.findViewById(b.h.textview_title);
    }

    public static j a(ViewGroup viewGroup) {
        return new j(LayoutInflater.from(viewGroup.getContext()).inflate(b.j.setting_header_item, viewGroup, false));
    }

    @Override // com.lge.media.launcher.control.common.b
    /* renamed from: a */
    public void b(k kVar) {
        this.F.setText(kVar.b);
        TextView textView = this.F;
        textView.setContentDescription(kVar.b + kVar.e);
    }
}
