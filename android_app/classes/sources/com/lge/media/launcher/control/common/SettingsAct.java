package com.lge.media.launcher.control.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ag;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.lge.media.launcher.b;
import com.lge.media.launcher.control.common.l;
import java.util.ArrayList;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class SettingsAct extends Activity {
    public static final int a = 0;
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 0;
    public static final int e = 1;
    public static final int f = 2;
    private NfcAdapter j;
    private PendingIntent k;
    private IntentFilter[] l;
    private l m;
    private final ArrayList<k> n = new ArrayList<>();
    private final ArrayList<k> o = new ArrayList<>();
    private final ArrayList<k> p = new ArrayList<>();
    l.a g = new l.a() { // from class: com.lge.media.launcher.control.common.SettingsAct.2
        @Override // com.lge.media.launcher.control.common.l.a
        public void a(View view, int i, long j) {
            SettingsAct settingsAct = SettingsAct.this;
            settingsAct.startActivity(new Intent(settingsAct, ChangeLinkedDevicesAct.class));
            f.d();
        }
    };
    l.a h = new l.a() { // from class: com.lge.media.launcher.control.common.SettingsAct.3
        @Override // com.lge.media.launcher.control.common.l.a
        public void a(View view, int i, long j) {
            k kVar;
            boolean b2;
            int i2 = ((k) SettingsAct.this.o.get(i)).a;
            if (i2 != 0) {
                if (i2 == 1) {
                    f.b(!f.c());
                    kVar = (k) SettingsAct.this.o.get(i);
                    b2 = f.c();
                }
                f.d();
            }
            f.a(!f.b());
            kVar = (k) SettingsAct.this.o.get(i);
            b2 = f.b();
            kVar.c = b2;
            SettingsAct.this.m.d();
            f.d();
        }
    };
    l.a i = new l.a() { // from class: com.lge.media.launcher.control.common.SettingsAct.4
        @Override // com.lge.media.launcher.control.common.l.a
        public void a(View view, int i, long j) {
            SettingsAct settingsAct;
            Intent intent;
            int i2 = ((k) SettingsAct.this.p.get(i)).a;
            if (i2 == 0) {
                settingsAct = SettingsAct.this;
                intent = new Intent(settingsAct, ManualAct.class);
            } else if (i2 != 1) {
                if (i2 == 2) {
                    settingsAct = SettingsAct.this;
                    intent = new Intent(settingsAct, OpenSourceAct.class);
                }
                f.d();
            } else {
                settingsAct = SettingsAct.this;
                intent = new Intent(settingsAct, AboutAct.class);
            }
            settingsAct.startActivity(intent);
            f.d();
        }
    };

    @Override // android.app.Activity
    protected void onCreate(@ag Bundle bundle) {
        String str;
        super.onCreate(bundle);
        setContentView(b.j.activity_settings);
        ((TextView) findViewById(b.h.text_top_title)).setContentDescription(getString(b.m.settings) + getString(b.m.label_title) + 1);
        findViewById(b.h.button_back).setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.SettingsAct.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingsAct.this.finish();
            }
        });
        findViewById(b.h.textview_change_device_header).setContentDescription(getString(b.m.category_devices) + getString(b.m.label_title) + 2);
        c cVar = new c(android.support.v4.content.c.getDrawable(this, b.g.list_divider));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.b(1);
        RecyclerView recyclerView = (RecyclerView) findViewById(b.h.recyclerview_change_device);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.a(cVar);
        findViewById(b.h.textview_effect_header).setContentDescription(getString(b.m.category_effect) + getString(b.m.label_title) + 2);
        c cVar2 = new c(android.support.v4.content.c.getDrawable(this, b.g.list_divider));
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.b(1);
        RecyclerView recyclerView2 = (RecyclerView) findViewById(b.h.recyclerview_effect);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView2.a(cVar2);
        findViewById(b.h.textview_manual_header).setContentDescription(getString(b.m.category_manual) + getString(b.m.label_title) + 2);
        c cVar3 = new c(android.support.v4.content.c.getDrawable(this, b.g.list_divider));
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this);
        linearLayoutManager3.b(1);
        RecyclerView recyclerView3 = (RecyclerView) findViewById(b.h.recyclerview_manual);
        recyclerView3.setLayoutManager(linearLayoutManager3);
        recyclerView3.a(cVar3);
        e a2 = e.a(this, (Handler) null);
        boolean booleanExtra = getIntent().getBooleanExtra(d.Q, false);
        try {
            this.j = NfcAdapter.getDefaultAdapter(this);
            if (this.j == null) {
                a.b("This device does not support NFc");
            }
            this.k = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(536870912), 0);
            this.l = new IntentFilter[]{new IntentFilter("android.nfc.action.TAG_DISCOVERED")};
        } catch (Exception unused) {
            a.a("NFC Adapter exception");
        }
        if (a2.A) {
            str = a2.c;
        } else {
            str = a2.e() + " " + e.a(a2.e(), a2.b().get(a2.d()).k());
        }
        this.n.add(new k(0, e.f(str), com.lge.media.launcher.a.d));
        this.o.add(new k(0, getString(b.m.vibration_effects), com.lge.media.launcher.a.d, f.b(), !booleanExtra));
        this.o.add(new k(1, getString(b.m.sound_effects), com.lge.media.launcher.a.d, f.c(), !booleanExtra));
        this.p.add(new k(0, getString(b.m.help), com.lge.media.launcher.a.d));
        this.p.add(new k(1, getString(b.m.about), com.lge.media.launcher.a.d));
        this.p.add(new k(2, getString(b.m.open_source_licence), com.lge.media.launcher.a.d));
        l lVar = new l(this, this.n, this.g, Boolean.valueOf(a2.R), a2.U);
        lVar.a(true);
        recyclerView.setAdapter(lVar);
        this.m = new l(this, this.o, this.h, Boolean.valueOf(a2.R), a2.U);
        this.m.a(true);
        recyclerView2.setAdapter(this.m);
        l lVar2 = new l(this, this.p, this.i, Boolean.valueOf(a2.R), a2.U);
        lVar2.a(true);
        recyclerView3.setAdapter(lVar2);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            this.j.enableForegroundDispatch(this, this.k, this.l, null);
        } catch (Exception unused) {
            a.a("NFC Adapter exception");
        }
    }
}
