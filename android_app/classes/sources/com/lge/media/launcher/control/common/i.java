package com.lge.media.launcher.control.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.lge.media.launcher.b;
import com.lge.media.launcher.control.common.l;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class i extends b<k> {
    TextView F;
    CheckBox G;
    l.a H;

    i(View view, l.a aVar) {
        super(view);
        this.F = (TextView) view.findViewById(b.h.textview_title);
        this.G = (CheckBox) view.findViewById(2131230871);
        this.H = aVar;
    }

    public static i a(ViewGroup viewGroup, l.a aVar) {
        return new i(LayoutInflater.from(viewGroup.getContext()).inflate(b.j.setting_content_item, viewGroup, false), aVar);
    }

    @Override // com.lge.media.launcher.control.common.b
    /* renamed from: a */
    public void b(k kVar) {
        this.F.setText(kVar.b);
        if (kVar.f) {
            this.G.setVisibility(0);
            this.G.setChecked(kVar.c);
            if (!kVar.d) {
                this.G.setEnabled(false);
                this.a.setEnabled(false);
                this.F.setTextColor(this.a.getContext().getResources().getColor(b.e.externel_speaker_text_disable));
            }
        }
        this.a.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.i.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                i.this.H.a(view, i.this.f(), i.this.h());
            }
        });
    }
}
