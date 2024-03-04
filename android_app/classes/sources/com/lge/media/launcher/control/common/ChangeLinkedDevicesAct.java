package com.lge.media.launcher.control.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.lge.media.launcher.b;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ChangeLinkedDevicesAct extends Activity {
    private static final int b = 1000;
    private static final int c = 1001;
    private Button d;
    private LinearLayout e;
    private LinearLayout f;
    private LinearLayout g;
    private ListView h;
    private ProgressBar i;
    private com.lge.media.launcher.ui.e j;
    private e k;
    private NfcAdapter m;
    private PendingIntent n;
    private IntentFilter[] o;
    private int l = -1;
    private com.lge.media.launcher.ui.b p = null;
    public Handler a = new Handler() { // from class: com.lge.media.launcher.control.common.ChangeLinkedDevicesAct.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ChangeLinkedDevicesAct changeLinkedDevicesAct;
            int i;
            com.lge.media.launcher.ui.e eVar;
            boolean z;
            int i2;
            int i3;
            int i4;
            View.OnClickListener onClickListener;
            View.OnClickListener onClickListener2;
            boolean z2;
            String str;
            float f;
            int i5 = message.what;
            if (i5 == 0) {
                ChangeLinkedDevicesAct.f(ChangeLinkedDevicesAct.this);
                a.b("Choose, received data " + ChangeLinkedDevicesAct.this.l + "," + ChangeLinkedDevicesAct.this.k.e(ChangeLinkedDevicesAct.this.l));
                changeLinkedDevicesAct = ChangeLinkedDevicesAct.this;
                i = changeLinkedDevicesAct.l;
            } else if (i5 != 1) {
                if (i5 != 4) {
                    if (i5 != 1000) {
                        if (i5 != 1001) {
                            super.handleMessage(message);
                            return;
                        } else if (ChangeLinkedDevicesAct.this.k.U > 2.0f) {
                            eVar = ChangeLinkedDevicesAct.this.j;
                            z = true;
                            i2 = b.m.exit;
                            i3 = b.m.ok;
                            i4 = b.m.cancel;
                            onClickListener = ChangeLinkedDevicesAct.this.r;
                            onClickListener2 = ChangeLinkedDevicesAct.this.s;
                            z2 = ChangeLinkedDevicesAct.this.k.R;
                            str = ChangeLinkedDevicesAct.this.k.T;
                            f = ChangeLinkedDevicesAct.this.k.U;
                        } else {
                            eVar = ChangeLinkedDevicesAct.this.j;
                            z = true;
                            i2 = b.m.exit;
                            i3 = b.m.ok;
                            i4 = b.m.cancel;
                            onClickListener = ChangeLinkedDevicesAct.this.r;
                            onClickListener2 = ChangeLinkedDevicesAct.this.s;
                            z2 = false;
                            str = null;
                            f = 0.0f;
                        }
                    } else if (ChangeLinkedDevicesAct.this.k.U > 2.0f) {
                        eVar = ChangeLinkedDevicesAct.this.j;
                        z = false;
                        i2 = b.m.wifi_off;
                        i3 = b.m.ok;
                        i4 = 0;
                        onClickListener = ChangeLinkedDevicesAct.this.q;
                        onClickListener2 = null;
                        z2 = ChangeLinkedDevicesAct.this.k.R;
                        str = ChangeLinkedDevicesAct.this.k.T;
                        f = ChangeLinkedDevicesAct.this.k.U;
                    } else {
                        eVar = ChangeLinkedDevicesAct.this.j;
                        z = false;
                        i2 = b.m.wifi_off;
                        i3 = b.m.ok;
                        i4 = 0;
                        onClickListener = ChangeLinkedDevicesAct.this.q;
                        onClickListener2 = null;
                        z2 = false;
                        str = null;
                        f = 0.0f;
                    }
                } else if (ChangeLinkedDevicesAct.this.k.U > 2.0f) {
                    eVar = ChangeLinkedDevicesAct.this.j;
                    z = false;
                    i2 = b.m.error_unknown_device;
                    i3 = b.m.ok;
                    i4 = 0;
                    onClickListener = ChangeLinkedDevicesAct.this.q;
                    onClickListener2 = null;
                    z2 = ChangeLinkedDevicesAct.this.k.R;
                    str = ChangeLinkedDevicesAct.this.k.T;
                    f = ChangeLinkedDevicesAct.this.k.U;
                } else {
                    eVar = ChangeLinkedDevicesAct.this.j;
                    z = false;
                    i2 = b.m.error_unknown_device;
                    i3 = b.m.ok;
                    i4 = 0;
                    onClickListener = ChangeLinkedDevicesAct.this.q;
                    onClickListener2 = null;
                    z2 = false;
                    str = null;
                    f = 0.0f;
                }
                eVar.a(z, i2, i3, i4, onClickListener, onClickListener2, z2, str, f);
                return;
            } else {
                a.a("Choose, received timeout" + message.obj + ChangeLinkedDevicesAct.this.l);
                if (ChangeLinkedDevicesAct.this.l < 0 && ChangeLinkedDevicesAct.this.k.J().size() == 0) {
                    ChangeLinkedDevicesAct.this.e();
                    ChangeLinkedDevicesAct.this.d.setEnabled(true);
                } else if (ChangeLinkedDevicesAct.this.l >= 0 || ChangeLinkedDevicesAct.this.k.J().size() <= 0) {
                    return;
                } else {
                    changeLinkedDevicesAct = ChangeLinkedDevicesAct.this;
                    i = -1;
                }
            }
            changeLinkedDevicesAct.a(i);
            ChangeLinkedDevicesAct.this.d.setEnabled(true);
        }
    };
    private View.OnClickListener q = new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.ChangeLinkedDevicesAct.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.d();
            ChangeLinkedDevicesAct.this.j.a();
            ChangeLinkedDevicesAct.this.f();
        }
    };
    private View.OnClickListener r = new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.ChangeLinkedDevicesAct.6
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.d();
            ChangeLinkedDevicesAct.this.j.a();
            ChangeLinkedDevicesAct.this.k.l();
            ChangeLinkedDevicesAct.this.finish();
        }
    };
    private View.OnClickListener s = new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.ChangeLinkedDevicesAct.7
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.d();
            ChangeLinkedDevicesAct.this.j.a();
        }
    };

    private void a() {
        a.b("Initview - PrefchangeLinkedDevices");
        ((TextView) findViewById(b.h.text_top_title)).setContentDescription(getString(b.m.category_devices) + getString(b.m.label_title) + "1");
        this.e = (LinearLayout) findViewById(b.h.layout_content);
        this.f = (LinearLayout) findViewById(b.h.layout_devices);
        this.h = (ListView) findViewById(b.h.list_devices);
        this.g = (LinearLayout) findViewById(b.h.progress_waiting);
        this.i = (ProgressBar) findViewById(b.h.progressbar_searching);
        this.d = (Button) findViewById(b.h.btn_refresh);
        this.d.setEnabled(true);
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (!this.f.isShown()) {
            this.g.setVisibility(4);
            this.e.setVisibility(4);
            this.f.setVisibility(0);
            com.lge.media.launcher.c.a(this.d, 100);
        }
        b(i);
    }

    private void b() {
        this.h.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lge.media.launcher.control.common.ChangeLinkedDevicesAct.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                f.d();
                if (!MainAct.h) {
                    if (!((com.lge.media.launcher.ui.a) ChangeLinkedDevicesAct.this.p.getItem(i)).b()) {
                        com.lge.media.launcher.discovery.a.a(((com.lge.media.launcher.ui.a) ChangeLinkedDevicesAct.this.p.getItem(i)).d());
                        return;
                    }
                    ChangeLinkedDevicesAct.this.k.d(i);
                    String lowerCase = ((com.lge.media.launcher.ui.a) ChangeLinkedDevicesAct.this.p.getItem(i)).c().toLowerCase(Locale.ENGLISH);
                    if (lowerCase.contains(d.A) && lowerCase.contains("15y")) {
                        ChangeLinkedDevicesAct.this.k.a(((com.lge.media.launcher.ui.a) ChangeLinkedDevicesAct.this.p.getItem(i)).c(), ((com.lge.media.launcher.ui.a) ChangeLinkedDevicesAct.this.p.getItem(i)).d(), ((com.lge.media.launcher.ui.a) ChangeLinkedDevicesAct.this.p.getItem(i)).e());
                    }
                }
                ChangeLinkedDevicesAct.this.k.f(i);
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.ChangeLinkedDevicesAct.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                ChangeLinkedDevicesAct.this.d();
                ChangeLinkedDevicesAct.this.c();
                ChangeLinkedDevicesAct.this.d.setEnabled(false);
            }
        });
        findViewById(b.h.button_back).setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.ChangeLinkedDevicesAct.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChangeLinkedDevicesAct.this.finish();
            }
        });
    }

    private void b(int i) {
        boolean z;
        a.c("update device index is... " + i);
        ArrayList<com.lge.UDAP.a.c> b2 = this.k.b();
        ArrayList<com.lge.media.launcher.ui.a> J = this.k.J();
        ArrayList arrayList = new ArrayList();
        if (MainAct.h) {
            for (int i2 = 0; i2 < b2.size(); i2++) {
                arrayList.add(new com.lge.media.launcher.ui.a(b2.get(i2)));
            }
            this.p = new com.lge.media.launcher.ui.b(this, b.j.device_item, arrayList, Boolean.valueOf(this.k.R), this.k.U);
            this.h.setAdapter((ListAdapter) this.p);
            return;
        }
        for (int i3 = 0; i3 < b2.size(); i3++) {
            arrayList.add(new com.lge.media.launcher.ui.a(b2.get(i3)));
        }
        for (int i4 = 0; i4 < J.size(); i4++) {
            int i5 = 0;
            while (true) {
                if (i5 >= b2.size()) {
                    z = false;
                    break;
                } else if (b2.get(i5).d().equals(J.get(i4).d())) {
                    z = true;
                    break;
                } else {
                    i5++;
                }
            }
            if (!z) {
                arrayList.add(new com.lge.media.launcher.ui.a(J.get(i4).c(), J.get(i4).d(), J.get(i4).e()));
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            com.lge.media.launcher.ui.a aVar = (com.lge.media.launcher.ui.a) it.next();
            String lowerCase = aVar.c().toLowerCase(Locale.ENGLISH);
            if (lowerCase.contains(d.A) && lowerCase.contains("15y")) {
                this.k.a(aVar.c(), aVar.d(), aVar.e());
            }
        }
        this.p = new com.lge.media.launcher.ui.b(this, b.j.device_item, arrayList, Boolean.valueOf(this.k.R), this.k.U);
        this.h.setAdapter((ListAdapter) this.p);
        this.h.requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        WifiManager wifiManager = (WifiManager) getSystemService("wifi");
        if (!wifiManager.isWifiEnabled()) {
            a.b("WiFi is off");
            Handler handler = this.a;
            handler.sendMessageDelayed(handler.obtainMessage(1000), 100L);
            this.k = e.a(this, this.a);
            return;
        }
        a.b("WiFi is on");
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        String macAddress = connectionInfo.getMacAddress();
        int ipAddress = connectionInfo.getIpAddress();
        String num = Integer.toString(ipAddress & 255);
        for (int i = 8; i <= 24; i += 8) {
            num = num.concat(".").concat(Integer.toString((ipAddress >> i) & 255));
        }
        PrintStream printStream = System.out;
        printStream.println("wifi ip : " + num);
        this.k = e.a(this, this.a);
        if (macAddress == null) {
            a.a("MAC address is null");
            this.k.a(new String("00:00:00:00:00:00"));
        } else {
            this.k.a(macAddress);
        }
        if (!MainAct.h) {
            this.k.k();
            this.l = -1;
            this.k.d(num);
        }
        this.k.h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.f.setVisibility(4);
        this.e.setVisibility(4);
        this.g.setVisibility(0);
        com.lge.media.launcher.c.a(this.i, 100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.g.setVisibility(4);
        this.f.setVisibility(4);
        this.e.setVisibility(0);
        com.lge.media.launcher.c.a(this.d, 100);
    }

    static /* synthetic */ int f(ChangeLinkedDevicesAct changeLinkedDevicesAct) {
        int i = changeLinkedDevicesAct.l;
        changeLinkedDevicesAct.l = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.g.setVisibility(4);
        this.f.setVisibility(4);
        this.e.setVisibility(4);
        com.lge.media.launcher.c.a(this.d, 100);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.k = e.a(this, this.a);
        this.k.H();
        setContentView(b.j.change_linked_devices);
        a();
        this.k.h();
        b();
        try {
            this.m = NfcAdapter.getDefaultAdapter(this);
            this.n = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(536870912), 0);
            this.o = new IntentFilter[]{new IntentFilter("android.nfc.action.TAG_DISCOVERED")};
        } catch (Exception unused) {
            a.a("NFC Adapter exception");
        }
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        a.b("BDP RemotePage Tagging again");
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        e eVar = this.k;
        if (eVar != null) {
            eVar.I();
        }
        PreferenceManager.getDefaultSharedPreferences(this).edit().commit();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            this.m.enableForegroundDispatch(this, this.n, this.o, null);
        } catch (Exception unused) {
            a.a("NFC Adapter exception");
        }
    }
}
