package com.lge.media.launcher.syncstatus;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.lge.media.launcher.b;
import com.lge.media.launcher.control.common.m;
import java.util.ArrayList;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c extends m {
    Button a;
    private LinearLayout b;
    private LinearLayout c;
    private LinearLayout d;

    public c(Context context, ViewGroup viewGroup) {
        super(context, viewGroup);
        a(viewGroup);
        a();
        b();
    }

    @Override // com.lge.media.launcher.control.common.m
    protected void a() {
    }

    @Override // com.lge.media.launcher.control.common.m
    protected void a(ViewGroup viewGroup) {
        com.lge.media.launcher.control.common.a.a("input Device initView");
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(b.h.layout_ui_sync_page_root);
        View inflate = LayoutInflater.from(this.g).inflate(b.j.sync_input_device_page, (ViewGroup) null, true);
        viewGroup2.addView(inflate, viewGroup2.getChildCount(), viewGroup2.getLayoutParams());
        this.b = (LinearLayout) inflate.findViewById(b.h.progress_waiting);
        this.c = (LinearLayout) inflate.findViewById(b.h.popup_linked_device);
        this.a = (Button) inflate.findViewById(b.h.btn_popup_one);
        this.d = (LinearLayout) inflate.findViewById(b.h.title_layout);
        View findViewById = inflate.findViewById(b.h.linked_device_title_textview);
        findViewById.setContentDescription(this.g.getString(b.m.linked_deivce_icon_list) + ", " + this.g.getString(b.m.label_title) + 1);
    }

    @Override // com.lge.media.launcher.control.common.m
    public void a(k kVar) {
        com.lge.media.launcher.control.common.a.b("input Device fillViews");
        this.b.setVisibility(8);
        this.c.setVisibility(0);
        com.lge.media.launcher.control.common.a.b("size : " + this.g.f.r.p.size());
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < kVar.p.size(); i++) {
            arrayList.add(kVar.p.get(i));
            com.lge.media.launcher.control.common.a.a("i : " + this.g.f.r.p.get(i).b);
        }
        Button button = (Button) this.c.findViewById(b.h.btn_popup_one);
        button.setText(b.m.close);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.c.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.lge.media.launcher.control.common.a.c("popup cancel btn click..");
                c.this.g.g.b.e(c.this.g.s.a, -1);
                c.this.b();
                c.this.g.e.setEnabled(false);
            }
        });
        ListView listView = (ListView) this.c.findViewById(b.h.list_popup_device);
        listView.setAdapter((ListAdapter) new e(this.g, b.j.device_popup_item, arrayList, Boolean.valueOf(this.g.f.R), this.g.f.U));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lge.media.launcher.syncstatus.c.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                d dVar = c.this.g.s.p.get(i2);
                if ("DIM".equals(dVar.c)) {
                    return;
                }
                int parseInt = Integer.parseInt(dVar.a);
                com.lge.media.launcher.control.common.f.d();
                com.lge.media.launcher.control.common.a.c("change device is index : " + parseInt);
                c.this.g.g.b.e(c.this.g.s.a, parseInt);
                c.this.b();
            }
        });
        this.g.e.setEnabled(true);
        this.d.sendAccessibilityEvent(8);
    }

    @Override // com.lge.media.launcher.control.common.m
    public void b() {
        com.lge.media.launcher.control.common.a.b("input Device hideViews");
        this.b.setVisibility(0);
        this.c.setVisibility(8);
    }

    @Override // com.lge.media.launcher.control.common.m
    public void c() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            com.lge.media.launcher.control.common.a.c("Linked Device change cancel");
            this.g.g.b.e(this.g.s.a, -1);
            this.g.e.setEnabled(false);
            b();
        }
        return false;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
