package com.lge.media.launcher.discovery;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.SoundPool;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.lge.media.launcher.b;
import com.lge.media.launcher.c;
import com.lge.media.launcher.control.common.d;
import com.lge.media.launcher.control.common.e;
import com.lge.media.launcher.control.common.f;
import com.lge.media.launcher.setup.TermsOfUseActivity;
import com.lge.media.launcher.ui.b;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Locale;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ChooseDeviceAct extends Activity {
    private static final int C = 1;
    private static final int D = 2;
    public static final int a = 101;
    public static String b = null;
    public static final String c = "application/com.lge.magic";
    private static final int e = 1000;
    private static final int f = 1001;
    private String A;
    private NfcAdapter g;
    private Button h;
    private Button i;
    private LinearLayout j;
    private LinearLayout k;
    private LinearLayout l;
    private LinearLayout m;
    private ProgressBar n;
    private ProgressBar o;
    private ListView p;
    private e q;
    private SoundPool s;
    private Handler w;
    private Runnable x;
    private Handler y;
    private Runnable z;
    private int r = -1;
    private ArrayList<com.lge.media.launcher.ui.a> t = new ArrayList<>();
    private boolean u = false;
    private b v = null;
    private int B = 0;
    private boolean E = false;
    private int F = -1;
    private String G = null;
    private Dialog H = null;
    private Dialog I = null;
    private Dialog J = null;
    private Dialog K = null;
    private Dialog L = null;
    @SuppressLint({"HandlerLeak"})
    public Handler d = new Handler() { // from class: com.lge.media.launcher.discovery.ChooseDeviceAct.8
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Dialog dialog;
            int i = message.what;
            if (i != 0) {
                if (i == 1) {
                    com.lge.media.launcher.control.common.a.a("Choose, received timeout" + message.obj + ChooseDeviceAct.this.r);
                    if (ChooseDeviceAct.this.r < 0 && ChooseDeviceAct.this.q.J().size() == 0) {
                        ChooseDeviceAct.this.k();
                    } else if (ChooseDeviceAct.this.r >= 0 || ChooseDeviceAct.this.q.J().size() <= 0) {
                        return;
                    } else {
                        ChooseDeviceAct.this.b(-1);
                    }
                    ChooseDeviceAct.this.h.setEnabled(true);
                    return;
                }
                if (i == 4) {
                    dialog = ChooseDeviceAct.this.J;
                } else if (i == 6) {
                    ChooseDeviceAct.b(ChooseDeviceAct.this);
                    if (!ChooseDeviceAct.this.u || !ChooseDeviceAct.this.q.e(ChooseDeviceAct.this.r)) {
                        return;
                    }
                } else if (i == 7) {
                    ChooseDeviceAct.k(ChooseDeviceAct.this);
                    if (ChooseDeviceAct.this.B == 4) {
                        ChooseDeviceAct.this.h();
                        return;
                    } else {
                        ChooseDeviceAct.this.y.postDelayed(ChooseDeviceAct.this.z, 5000L);
                        return;
                    }
                } else if (i == 1000) {
                    dialog = ChooseDeviceAct.this.I;
                } else if (i != 1001) {
                    super.handleMessage(message);
                    return;
                } else {
                    dialog = ChooseDeviceAct.this.H;
                }
                dialog.show();
                return;
            }
            ChooseDeviceAct.b(ChooseDeviceAct.this);
            com.lge.media.launcher.control.common.a.b("Choose, received data " + ChooseDeviceAct.this.r + "," + ChooseDeviceAct.this.q.e(ChooseDeviceAct.this.r));
            ChooseDeviceAct chooseDeviceAct = ChooseDeviceAct.this;
            chooseDeviceAct.b(chooseDeviceAct.r);
            ChooseDeviceAct.this.h.setEnabled(true);
            if (!ChooseDeviceAct.this.u || !ChooseDeviceAct.this.q.e(ChooseDeviceAct.this.r)) {
                return;
            }
            ChooseDeviceAct.this.q.f(ChooseDeviceAct.this.r);
        }
    };

    private void a() {
        com.lge.media.launcher.control.common.a.b("Initview - choosedeviceact");
        f.a(getApplicationContext(), this.s);
        ((TextView) findViewById(b.h.text_top_title)).setContentDescription(getString(b.m.choose_device) + getString(b.m.label_title) + "1");
        this.h = (Button) findViewById(b.h.btn_refresh);
        this.i = (Button) findViewById(b.h.btn_demo_mode);
        this.j = (LinearLayout) findViewById(b.h.layout_content);
        this.k = (LinearLayout) findViewById(b.h.layout_devices);
        this.p = (ListView) findViewById(b.h.list_devices);
        this.l = (LinearLayout) findViewById(b.h.progress_waiting);
        this.m = (LinearLayout) findViewById(b.h.progress_wol_waiting);
        this.n = (ProgressBar) findViewById(b.h.progressbar_searching);
        this.o = (ProgressBar) findViewById(b.h.progressbar_wol_searching);
        i();
        b();
        c();
        d();
        e();
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        e eVar;
        WifiManager wifiManager = (WifiManager) getSystemService("wifi");
        if (!wifiManager.isWifiEnabled()) {
            com.lge.media.launcher.control.common.a.b("WiFi is off");
            Handler handler = this.d;
            handler.sendMessageDelayed(handler.obtainMessage(1000), 100L);
            this.q = e.a(this, this.d);
            return;
        }
        com.lge.media.launcher.control.common.a.b("WiFi is on");
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        String macAddress = connectionInfo.getMacAddress();
        int ipAddress = connectionInfo.getIpAddress();
        String num = Integer.toString(ipAddress & 255);
        for (int i2 = 8; i2 <= 24; i2 += 8) {
            num = num.concat(".").concat(Integer.toString((ipAddress >> i2) & 255));
        }
        PrintStream printStream = System.out;
        printStream.println("wifi ip : " + num);
        this.q = e.a(this, this.d);
        if (macAddress == null) {
            com.lge.media.launcher.control.common.a.a("MAC address is null");
            eVar = this.q;
            macAddress = "00:00:00:00:00:00";
        } else {
            eVar = this.q;
        }
        eVar.a(macAddress);
        this.q.k();
        this.r = -1;
        this.q.d(num);
        if (i == 1) {
            this.q.h();
        } else {
            this.q.e(this.A);
        }
    }

    static /* synthetic */ int b(ChooseDeviceAct chooseDeviceAct) {
        int i = chooseDeviceAct.r;
        chooseDeviceAct.r = i + 1;
        return i;
    }

    private void b() {
        this.K = new Dialog(this, 16973840);
        this.K.setContentView(b.j.popup);
        ((TextView) this.K.findViewById(b.h.text_popup_content)).setText(b.m.remove_speaker);
        Button button = (Button) this.K.findViewById(b.h.btn_popup_one);
        button.setText(b.m.ok);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.discovery.ChooseDeviceAct.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                ListIterator listIterator = ChooseDeviceAct.this.t.listIterator();
                while (true) {
                    if (!listIterator.hasNext()) {
                        break;
                    } else if (((com.lge.media.launcher.ui.a) listIterator.next()).d().equals(ChooseDeviceAct.this.A)) {
                        listIterator.remove();
                        break;
                    }
                }
                Iterator<com.lge.media.launcher.ui.a> it = ChooseDeviceAct.this.q.J().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (it.next().d().equals(ChooseDeviceAct.this.A)) {
                        it.remove();
                        break;
                    }
                }
                if (ChooseDeviceAct.this.q != null) {
                    ChooseDeviceAct.this.q.I();
                }
                ChooseDeviceAct.this.v.notifyDataSetChanged();
                ChooseDeviceAct.this.K.dismiss();
            }
        });
        Button button2 = (Button) this.K.findViewById(b.h.btn_popup_two);
        button2.setText(b.m.cancel);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.discovery.ChooseDeviceAct.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                ChooseDeviceAct.this.K.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (!this.k.isShown()) {
            this.l.setVisibility(4);
            this.m.setVisibility(4);
            this.j.setVisibility(4);
            this.k.setVisibility(0);
            c.a(this.h, 100);
        }
        com.lge.media.launcher.control.common.a.a("device list size : " + this.q.c());
        if (this.q.c() > 0 || this.q.J().size() > 0) {
            c(i);
        } else {
            k();
        }
    }

    private void c() {
        this.H = new Dialog(this, 16973840);
        this.H.setContentView(b.j.popup);
        ((TextView) this.H.findViewById(b.h.text_popup_content)).setText(b.m.exit);
        Button button = (Button) this.H.findViewById(b.h.btn_popup_one);
        button.setText(b.m.ok);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.discovery.ChooseDeviceAct.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                ChooseDeviceAct.this.H.dismiss();
                ChooseDeviceAct.this.q.l();
                ActivityCompat.finishAffinity(ChooseDeviceAct.this);
            }
        });
        Button button2 = (Button) this.H.findViewById(b.h.btn_popup_two);
        button2.setText(b.m.cancel);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.discovery.ChooseDeviceAct.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                ChooseDeviceAct.this.H.dismiss();
            }
        });
    }

    private void c(int i) {
        boolean z;
        com.lge.media.launcher.control.common.a.c("update device index is... " + i);
        ArrayList<com.lge.UDAP.a.c> b2 = this.q.b();
        ArrayList<com.lge.media.launcher.ui.a> J = this.q.J();
        this.t.clear();
        for (int i2 = 0; i2 < b2.size(); i2++) {
            com.lge.media.launcher.ui.a aVar = new com.lge.media.launcher.ui.a(b2.get(i2));
            if (aVar.d().equals(this.G)) {
                if (this.E && this.F < i) {
                    com.lge.media.launcher.control.common.a.b("power off. divice is on. but show off : " + this.G);
                    aVar.f();
                    this.F = i;
                } else if (this.F >= i) {
                    this.G = null;
                    this.E = false;
                    this.F = -1;
                }
                String lowerCase = aVar.c().toLowerCase(Locale.ENGLISH);
                if (lowerCase.contains(d.A)) {
                    if (!lowerCase.contains("15y")) {
                    }
                }
            }
            this.t.add(aVar);
        }
        for (int i3 = 0; i3 < J.size(); i3++) {
            int i4 = 0;
            while (true) {
                if (i4 >= b2.size()) {
                    z = false;
                    break;
                } else if (b2.get(i4).d().equals(J.get(i3).d())) {
                    z = true;
                    break;
                } else {
                    i4++;
                }
            }
            if (!z) {
                this.t.add(new com.lge.media.launcher.ui.a(J.get(i3).c(), J.get(i3).d(), J.get(i3).e()));
            }
        }
        Iterator<com.lge.media.launcher.ui.a> it = this.t.iterator();
        while (it.hasNext()) {
            com.lge.media.launcher.ui.a next = it.next();
            String lowerCase2 = next.c().toLowerCase(Locale.ENGLISH);
            if (lowerCase2.contains(d.A) && lowerCase2.contains("15y")) {
                this.q.a(next.c(), next.d(), next.e());
            }
        }
        if (this.t.isEmpty()) {
            k();
            return;
        }
        this.v = new com.lge.media.launcher.ui.b(this, b.j.device_item, this.t, Boolean.valueOf(this.q.R), this.q.U);
        this.p.setAdapter((ListAdapter) this.v);
        this.p.requestLayout();
    }

    private void d() {
        this.I = new Dialog(this, 16973840);
        this.I.setContentView(b.j.popup);
        this.I.findViewById(b.h.btn_popup_two).setVisibility(8);
        ((TextView) this.I.findViewById(b.h.text_popup_content)).setText(b.m.wifi_off);
        Button button = (Button) this.I.findViewById(b.h.btn_popup_one);
        button.setText(b.m.ok);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.discovery.ChooseDeviceAct.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                ChooseDeviceAct.this.I.dismiss();
                ChooseDeviceAct.this.h.setEnabled(true);
                ChooseDeviceAct.this.l();
            }
        });
    }

    private void e() {
        this.J = new Dialog(this, 16973840);
        this.J.setContentView(b.j.popup);
        this.J.findViewById(b.h.btn_popup_two).setVisibility(8);
        ((TextView) this.J.findViewById(b.h.text_popup_content)).setText(b.m.error_unknown_device);
        Button button = (Button) this.J.findViewById(b.h.btn_popup_one);
        button.setText(b.m.ok);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.discovery.ChooseDeviceAct.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                ChooseDeviceAct.this.J.dismiss();
                ChooseDeviceAct.this.h.setEnabled(true);
                ChooseDeviceAct.this.l();
            }
        });
    }

    private void f() {
        this.L = new Dialog(this, 16973840);
        this.L.setContentView(b.j.popup);
        this.L.findViewById(b.h.btn_popup_two).setVisibility(8);
        ((TextView) this.L.findViewById(b.h.text_popup_content)).setText(b.m.wol_timeout);
        Button button = (Button) this.L.findViewById(b.h.btn_popup_one);
        button.setText(b.m.ok);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.discovery.ChooseDeviceAct.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChooseDeviceAct.this.L.dismiss();
                ChooseDeviceAct.this.u = true;
                ChooseDeviceAct.this.i.setEnabled(true);
                ChooseDeviceAct.this.m.setVisibility(4);
                ChooseDeviceAct.this.i();
                ChooseDeviceAct.this.a(1);
            }
        });
    }

    private void g() {
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.discovery.ChooseDeviceAct.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                ChooseDeviceAct.this.i();
                ChooseDeviceAct.this.a(1);
                ChooseDeviceAct.this.h.setEnabled(false);
            }
        });
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.discovery.ChooseDeviceAct.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                ChooseDeviceAct.this.h.setEnabled(false);
                ChooseDeviceAct.this.q.j();
            }
        });
        this.p.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lge.media.launcher.discovery.ChooseDeviceAct.5
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                f.d();
                if (((com.lge.media.launcher.ui.a) ChooseDeviceAct.this.v.getItem(i)).b()) {
                    ChooseDeviceAct.this.q.d(i);
                    String lowerCase = ((com.lge.media.launcher.ui.a) ChooseDeviceAct.this.v.getItem(i)).c().toLowerCase(Locale.ENGLISH);
                    if (lowerCase.contains(d.A) && lowerCase.contains("15y")) {
                        ChooseDeviceAct.this.q.a(((com.lge.media.launcher.ui.a) ChooseDeviceAct.this.v.getItem(i)).c(), ((com.lge.media.launcher.ui.a) ChooseDeviceAct.this.v.getItem(i)).d(), ((com.lge.media.launcher.ui.a) ChooseDeviceAct.this.v.getItem(i)).e());
                    }
                    ChooseDeviceAct.this.q.f(i);
                    return;
                }
                ChooseDeviceAct.this.j();
                ChooseDeviceAct chooseDeviceAct = ChooseDeviceAct.this;
                chooseDeviceAct.A = ((com.lge.media.launcher.ui.a) chooseDeviceAct.v.getItem(i)).d();
                ChooseDeviceAct.this.w.postDelayed(ChooseDeviceAct.this.x, 0L);
                ChooseDeviceAct.this.w.postDelayed(ChooseDeviceAct.this.x, 1000L);
                ChooseDeviceAct.this.w.postDelayed(ChooseDeviceAct.this.x, 2000L);
                ChooseDeviceAct.this.B = 0;
                ChooseDeviceAct.this.y.postDelayed(ChooseDeviceAct.this.z, 5000L);
                ChooseDeviceAct.this.h.setEnabled(false);
            }
        });
        this.p.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.lge.media.launcher.discovery.ChooseDeviceAct.6
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (((com.lge.media.launcher.ui.a) ChooseDeviceAct.this.v.getItem(i)).b()) {
                    return false;
                }
                f.d();
                ChooseDeviceAct chooseDeviceAct = ChooseDeviceAct.this;
                chooseDeviceAct.A = ((com.lge.media.launcher.ui.a) chooseDeviceAct.v.getItem(i)).d();
                ChooseDeviceAct.this.K.show();
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.L.show();
        this.i.setEnabled(false);
        this.h.setEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.k.setVisibility(4);
        this.j.setVisibility(4);
        this.l.setVisibility(0);
        c.a(this.n, 100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.k.setVisibility(4);
        this.j.setVisibility(4);
        this.m.setVisibility(0);
        c.a(this.o, 100);
    }

    static /* synthetic */ int k(ChooseDeviceAct chooseDeviceAct) {
        int i = chooseDeviceAct.B;
        chooseDeviceAct.B = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.l.setVisibility(4);
        this.m.setVisibility(4);
        this.k.setVisibility(4);
        this.j.setVisibility(0);
        c.a(this.h, 100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        this.l.setVisibility(4);
        this.m.setVisibility(4);
        this.k.setVisibility(4);
        this.j.setVisibility(0);
        c.a(this.h, 100);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0148  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void m() {
        /*
            Method dump skipped, instructions count: 365
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.discovery.ChooseDeviceAct.m():void");
    }

    private void n() {
        Dialog dialog = this.H;
        if (dialog != null) {
            dialog.dismiss();
        }
        Dialog dialog2 = this.I;
        if (dialog2 != null) {
            dialog2.dismiss();
        }
        Dialog dialog3 = this.J;
        if (dialog3 != null) {
            dialog3.dismiss();
        }
        Dialog dialog4 = this.K;
        if (dialog4 != null) {
            dialog4.dismiss();
        }
        Dialog dialog5 = this.L;
        if (dialog5 != null) {
            dialog5.dismiss();
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        com.lge.media.launcher.control.common.a.b("onActivityResult. power off");
        if (i == 101 && i2 == 0) {
            finish();
        } else if (i2 == 101 && intent != null) {
            String stringExtra = intent.getStringExtra("mac");
            com.lge.media.launcher.control.common.a.b("onActivityResult. power off mac " + stringExtra);
            Iterator<com.lge.media.launcher.ui.a> it = this.t.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (it.next().d().equals(stringExtra)) {
                    com.lge.media.launcher.control.common.a.b("onActivityResult. power off. found!");
                    this.u = false;
                    this.E = true;
                    this.G = stringExtra;
                    break;
                }
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        com.lge.media.launcher.control.common.a.b("onAttachedToWindow");
        a(1);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        Handler handler = this.d;
        handler.sendMessage(handler.obtainMessage(1001));
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.q = e.a(this, this.d);
        this.q.H();
        com.lge.media.launcher.control.common.a.c("setContentView start");
        setContentView(b.j.choose_device);
        com.lge.media.launcher.control.common.a.b("Get resolution");
        m();
        com.lge.media.launcher.control.common.a.b("initView start");
        a();
        com.lge.media.launcher.control.common.a.b("setListener start");
        g();
        int parseInt = Integer.parseInt(Build.VERSION.SDK);
        com.lge.media.launcher.control.common.a.a("SDK version : " + parseInt);
        try {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectNetwork().penaltyLog().build());
            this.g = NfcAdapter.getDefaultAdapter(this);
            if (this.g == null) {
                com.lge.media.launcher.control.common.a.b("This device does not support NFc");
            }
            Intent intent = getIntent();
            if (intent.getType() != null && intent.getType().equals(c)) {
                b = new String(((NdefMessage) getIntent().getParcelableArrayExtra("android.nfc.extra.NDEF_MESSAGES")[0]).getRecords()[0].getPayload());
                com.lge.media.launcher.control.common.a.c("Tagged info is :" + b);
            }
        } catch (Exception unused) {
            com.lge.media.launcher.control.common.a.a("NFC Adapter exception");
        }
        this.u = true;
        if (!getSharedPreferences("shared_prefs", 0).getBoolean(d.bs, false)) {
            startActivityForResult(new Intent(this, TermsOfUseActivity.class), 101);
            return;
        }
        this.x = new Runnable() { // from class: com.lge.media.launcher.discovery.ChooseDeviceAct.1
            @Override // java.lang.Runnable
            public void run() {
                a.a(ChooseDeviceAct.this.A);
            }
        };
        this.w = new Handler();
        this.z = new Runnable() { // from class: com.lge.media.launcher.discovery.ChooseDeviceAct.7
            @Override // java.lang.Runnable
            public void run() {
                ChooseDeviceAct.this.u = true;
                ChooseDeviceAct.this.a(2);
            }
        };
        this.y = new Handler();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        com.lge.media.launcher.control.common.a.a("ChooseDeviceAct OnDestroy");
        n();
        if (this.q.u && this.q.H != null) {
            com.lge.media.launcher.control.common.a.a("ChooseDeviceAct OnDestroy. sp off");
            this.q.an.start();
            e eVar = this.q;
            eVar.u = false;
            eVar.H.a(this.q.u);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        com.lge.media.launcher.control.common.a.a("ChooseDeviceAct OnPause");
        super.onPause();
        this.u = false;
        e eVar = this.q;
        if (eVar != null) {
            eVar.I();
        }
        n();
    }

    @Override // android.app.Activity
    protected void onResume() {
        com.lge.media.launcher.control.common.a.a("ChooseDeviceAct OnResume");
        com.lge.media.launcher.control.common.a.b("hasStarted : " + this.u);
        super.onResume();
        this.q.g = false;
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", 0);
        if (this.u || !sharedPreferences.getBoolean(d.bs, false)) {
            return;
        }
        n();
        i();
        a(1);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        com.lge.media.launcher.control.common.a.b("onWindowFocusChanged");
    }
}
