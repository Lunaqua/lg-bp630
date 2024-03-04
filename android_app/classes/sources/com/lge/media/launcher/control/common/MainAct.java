package com.lge.media.launcher.control.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lge.UDAP.ROAP.a.b;
import com.lge.media.launcher.YouTubeSearch.YouTubeSearchView;
import com.lge.media.launcher.b;
import com.lge.media.launcher.discovery.ChooseDeviceAct;
import com.lge.media.launcher.syncstatus.RadioAct;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class MainAct extends Activity {
    public static final String C = "avremote_cplist_pref";
    public static final String D = "favorite";
    private static ArrayList<String> aE = new ArrayList<>();
    public static boolean h = false;
    private RelativeLayout F;
    private h G;
    private g H;
    private YouTubeSearchView I;
    private WatchSoundPrivacy J;
    private Button K;
    private Button L;
    private Button M;
    private Button N;
    public RelativeLayout a;
    private NfcAdapter aA;
    private PendingIntent aB;
    private IntentFilter[] aC;
    private Dialog ak;
    private Dialog al;
    private Dialog am;
    private Dialog an;
    private Dialog ao;
    private Dialog ap;
    private Dialog aq;
    private Dialog ar;
    private Dialog as;
    private SoundPool at;
    private ArrayAdapter az;
    public m b;
    public ViewPager c;
    public com.lge.media.launcher.syncstatus.l d;
    public Button e;
    public e f;
    public int v;
    public int w;
    public int x;
    public String y;
    private LinearLayout O = null;
    private LinearLayout P = null;
    private LinearLayout Q = null;
    private LinearLayout R = null;
    private LinearLayout S = null;
    private LinearLayout T = null;
    private RadioButton U = null;
    private RadioButton V = null;
    private RadioButton W = null;
    private TextView X = null;
    private CheckBox Y = null;
    private Button Z = null;
    private CheckBox aa = null;
    private ListView ab = null;
    private Drawable ac = null;
    private Drawable ad = null;
    private Drawable ae = null;
    private Drawable af = null;
    private Drawable ag = null;
    private Drawable ah = null;
    private Drawable ai = null;
    private Drawable aj = null;
    public com.lge.UDAP.ROAP.b g = null;
    protected boolean i = true;
    protected boolean j = false;
    protected boolean k = false;
    private boolean au = false;
    public boolean l = false;
    public boolean m = false;
    public boolean n = false;
    public boolean o = false;
    public boolean p = false;
    public b.c q = null;
    public AudioManager r = null;
    public com.lge.media.launcher.syncstatus.k s = null;
    public com.lge.media.launcher.cplist.c t = null;
    public com.lge.media.launcher.a.a u = null;
    private int av = 2;
    private int aw = 0;
    private int ax = 0;
    private int ay = 0;
    public WifiManager.WifiLock z = null;
    public PowerManager.WakeLock A = null;
    public Thread B = null;
    private SharedPreferences aD = null;
    @SuppressLint({"HandlerLeak"})
    public Handler E = new Handler() { // from class: com.lge.media.launcher.control.common.MainAct.12
        /* JADX WARN: Code restructure failed: missing block: B:126:0x038b, code lost:
            if (r11.a.ak != null) goto L112;
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x02c5, code lost:
            if (r11.a.ak != null) goto L112;
         */
        /* JADX WARN: Code restructure failed: missing block: B:94:0x02c7, code lost:
            r11.a.ak.cancel();
         */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void handleMessage(android.os.Message r12) {
            /*
                Method dump skipped, instructions count: 2696
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.control.common.MainAct.AnonymousClass12.handleMessage(android.os.Message):void");
        }
    };
    private View.OnClickListener aF = new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.22
        /* JADX WARN: Code restructure failed: missing block: B:30:0x00b0, code lost:
            if (r2.a.az != null) goto L34;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x00b2, code lost:
            r2.a.u.a.clear();
            r2.a.az.notifyDataSetChanged();
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x00fe, code lost:
            if (r2.a.az != null) goto L34;
         */
        @Override // android.view.View.OnClickListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onClick(android.view.View r3) {
            /*
                Method dump skipped, instructions count: 342
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.control.common.MainAct.AnonymousClass22.onClick(android.view.View):void");
        }
    };

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class WatchSoundPrivacy extends BroadcastReceiver {
        final int a = 0;
        final int b = 1;

        public WatchSoundPrivacy() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int i = intent.getExtras().getInt("state");
            if (1 == i) {
                a.c("headset connected");
            } else if (i != 0) {
                return;
            } else {
                a.c("headset disconnected");
                if (MainAct.this.am.isShowing()) {
                    MainAct.this.am.cancel();
                }
            }
            MainAct.this.f.C = true;
        }
    }

    private void A() {
        a.b("popupspeaker setExternalOnly");
        this.p = true;
        this.W.setChecked(false);
        this.U.setChecked(false);
        this.V.setChecked(false);
        this.Y.setEnabled(true);
        this.aa.setChecked(false);
        this.Z.setEnabled(true);
        ArrayAdapter arrayAdapter = this.az;
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
        com.lge.media.launcher.a.a aVar = this.u;
        if (aVar == null) {
            return;
        }
        if (aVar.b) {
            this.Y.setChecked(true);
        } else {
            this.Y.setChecked(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        a.b("popupspeaker setExternalOnlySelectNothing");
        this.Z.setEnabled(true);
        ArrayAdapter arrayAdapter = this.az;
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ed, code lost:
        if (r5.f.u != false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void C() {
        /*
            Method dump skipped, instructions count: 279
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.control.common.MainAct.C():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        CheckBox checkBox;
        com.lge.media.launcher.syncstatus.k kVar;
        com.lge.media.launcher.a.a aVar = this.u;
        if (aVar == null) {
            return;
        }
        boolean z = true;
        if (aVar.a.size() > 0) {
            this.W.setEnabled(true);
            this.Q.setEnabled(true);
            this.X.setTextColor(getResources().getColor(b.e.externel_speaker_text_enable));
            this.Y.setEnabled(true);
        }
        this.ab = (ListView) this.an.findViewById(b.h.multi_room_speaker_list);
        if (this.p && (kVar = this.s) != null && kVar.d.equals("MUSIC")) {
            checkBox = this.aa;
        } else {
            checkBox = this.aa;
            z = false;
        }
        checkBox.setEnabled(z);
        this.az = new ArrayAdapter<com.lge.media.launcher.a.b>(this, b.j.external_speaker_item, this.u.a) { // from class: com.lge.media.launcher.control.common.MainAct.21
            /* JADX INFO: Access modifiers changed from: private */
            public Drawable a(String str, boolean z2) {
                if (com.lge.media.launcher.a.b.a.equals(str)) {
                    return (a() && z2) ? MainAct.this.ac : MainAct.this.ag;
                } else if (com.lge.media.launcher.a.b.b.equals(str)) {
                    return (a() && z2) ? MainAct.this.ad : MainAct.this.ah;
                } else if (com.lge.media.launcher.a.b.c.equals(str)) {
                    return (a() && z2) ? MainAct.this.ae : MainAct.this.ai;
                } else if (com.lge.media.launcher.a.b.d.equals(str)) {
                    return (a() && z2) ? MainAct.this.af : MainAct.this.aj;
                } else {
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void a(String str, boolean z2, ImageView imageView) {
                MainAct mainAct;
                int i;
                if (com.lge.media.launcher.a.b.a.equals(str)) {
                    mainAct = MainAct.this;
                    i = b.m.label_channel_mono;
                } else if (com.lge.media.launcher.a.b.b.equals(str)) {
                    mainAct = MainAct.this;
                    i = b.m.label_channel_stereo;
                } else if (!com.lge.media.launcher.a.b.c.equals(str)) {
                    if (com.lge.media.launcher.a.b.d.equals(str)) {
                        mainAct = MainAct.this;
                        i = b.m.label_channel_right;
                    }
                    imageView.setEnabled(!a() && z2);
                } else {
                    mainAct = MainAct.this;
                    i = b.m.label_channel_left;
                }
                imageView.setContentDescription(mainAct.getString(i));
                imageView.setEnabled(!a() && z2);
            }

            private boolean a() {
                return MainAct.this.W.isChecked() || MainAct.this.p;
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(final int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = ((LayoutInflater) MainAct.this.getSystemService("layout_inflater")).inflate(b.j.external_speaker_item, (ViewGroup) null);
                }
                LinearLayout linearLayout = (LinearLayout) view.findViewById(b.h.rood_layout);
                final CheckBox checkBox2 = (CheckBox) view.findViewById(b.h.check_box);
                final ImageView imageView = (ImageView) view.findViewById(b.h.speaker_channel);
                TextView textView = (TextView) view.findViewById(b.h.speaker_name_text);
                textView.setText(getItem(i).f);
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.21.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        int i2;
                        a.b("checkBox onClick");
                        getItem(i).a(!getItem(i).c());
                        checkBox2.setChecked(getItem(i).c());
                        ImageView imageView2 = imageView;
                        AnonymousClass21 anonymousClass21 = AnonymousClass21.this;
                        imageView2.setImageDrawable(anonymousClass21.a(anonymousClass21.getItem(i).d(), getItem(i).c()));
                        AnonymousClass21 anonymousClass212 = AnonymousClass21.this;
                        anonymousClass212.a(anonymousClass212.getItem(i).d(), getItem(i).c(), imageView);
                        Iterator<com.lge.media.launcher.a.b> it = MainAct.this.u.a.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                i2 = 0;
                                break;
                            } else if (it.next().c()) {
                                i2 = 1;
                                break;
                            }
                        }
                        if (MainAct.this.f.n().O() || !MainAct.this.f.n().R()) {
                            if (i2 <= 0) {
                                MainAct.this.Z.setEnabled(false);
                                return;
                            }
                        } else if (i2 != MainAct.this.u.a.size()) {
                            MainAct.this.aa.setChecked(false);
                            return;
                        } else if (i2 <= 0) {
                            MainAct.this.aa.setChecked(false);
                            MainAct.this.Y.setChecked(false);
                            MainAct.this.B();
                            return;
                        }
                        MainAct.this.Z.setEnabled(true);
                    }
                });
                imageView.setImageDrawable(a(getItem(i).d(), getItem(i).c()));
                a(getItem(i).d(), getItem(i).c(), imageView);
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.21.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        if (getItem(i).c()) {
                            getItem(i).f();
                            ImageView imageView2 = imageView;
                            AnonymousClass21 anonymousClass21 = AnonymousClass21.this;
                            imageView2.setImageDrawable(anonymousClass21.a(anonymousClass21.getItem(i).d(), getItem(i).c()));
                            AnonymousClass21 anonymousClass212 = AnonymousClass21.this;
                            anonymousClass212.a(anonymousClass212.getItem(i).d(), getItem(i).c(), imageView);
                        }
                    }
                });
                if (getItem(i).c()) {
                    checkBox2.setChecked(true);
                } else {
                    checkBox2.setChecked(false);
                }
                if (a()) {
                    checkBox2.setEnabled(true);
                    imageView.setEnabled(true);
                    textView.setEnabled(true);
                } else {
                    checkBox2.setEnabled(false);
                    checkBox2.setChecked(false);
                    imageView.setEnabled(false);
                    textView.setEnabled(false);
                }
                if (MainAct.this.W.isChecked()) {
                    linearLayout.setEnabled(true);
                } else {
                    linearLayout.setEnabled(false);
                }
                return view;
            }
        };
        C();
        this.ab.setAdapter((ListAdapter) this.az);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E() {
        if (h || this.f.u) {
            return;
        }
        a.c("SP was off");
        if (!this.f.J) {
            f.d();
            this.am.show();
            return;
        }
        a.c("btnEnable");
        if (this.f.n().R()) {
            this.g.b.a(com.lge.media.launcher.a.d, false);
        }
        this.e.setEnabled(false);
        this.f.an.start();
        this.f.u = true;
        f.d();
        N();
        this.f.H.a(this.f.u);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F() {
        if (h || !this.f.u) {
            return;
        }
        a.c("SP was on");
        a.c("btnEnable");
        this.e.setEnabled(false);
        this.f.an.start();
        this.f.u = false;
        f.d();
        N();
        this.f.H.a(this.f.u);
        PowerManager.WakeLock wakeLock = this.A;
        if (wakeLock != null && wakeLock.isHeld()) {
            this.A.release();
            this.A = null;
        }
        WifiManager.WifiLock wifiLock = this.z;
        if (wifiLock == null || !wifiLock.isHeld()) {
            return;
        }
        this.z.release();
        this.z = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G() {
        this.F.setOnClickListener(this.aF);
        this.K.setOnClickListener(this.aF);
        this.e.setOnClickListener(this.aF);
        this.M.setOnClickListener(this.aF);
        this.N.setOnClickListener(this.aF);
        this.c.setOnPageChangeListener(new ViewPager.e() { // from class: com.lge.media.launcher.control.common.MainAct.24
            @Override // android.support.v4.view.ViewPager.e
            public void a(int i) {
                Button button;
                View.OnClickListener onClickListener;
                a.b("Flicking");
                if (i != MainAct.this.ay) {
                    if (i == MainAct.this.aw) {
                        MainAct.this.c.setCurrentItem(MainAct.this.aw);
                        if (!MainAct.this.f.Q && MainAct.this.t != null) {
                            MainAct.this.H.b();
                            MainAct.this.H.a(MainAct.this.t);
                            return;
                        } else if (MainAct.h) {
                            a.b("Demo requestCPList");
                            MainAct.this.H.b();
                            MainAct.this.f.C();
                            return;
                        } else {
                            a.a("cpList is null");
                            a.b("flicking requestCPList");
                            MainAct mainAct = MainAct.this;
                            mainAct.k = true;
                            if (mainAct.f.n().S()) {
                                MainAct.this.f.C();
                            }
                            MainAct.this.H.b();
                            return;
                        }
                    }
                    return;
                }
                MainAct.this.c.setCurrentItem(MainAct.this.ay);
                if (MainAct.h) {
                    a.b("Demo requestSyncStatusInformation");
                    MainAct.this.f.w();
                    return;
                }
                if (MainAct.this.s == null) {
                    a.a("syncStatusInfo is null");
                } else {
                    a.a("syncStatusInfo is");
                    if (MainAct.this.s.d.equals("OTHER")) {
                        if (MainAct.this.s.b.contains("DATA")) {
                            a.a(" Data & other");
                            button = MainAct.this.L;
                            onClickListener = new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.24.1
                                @Override // android.view.View.OnClickListener
                                public void onClick(View view) {
                                    MainAct.this.ak.cancel();
                                    MainAct.this.g.b.g(3);
                                }
                            };
                        } else if (!MainAct.this.s.b.equals("DISC")) {
                            return;
                        } else {
                            a.a("Disc & Music or Disc & other");
                            button = MainAct.this.L;
                            onClickListener = new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.24.2
                                @Override // android.view.View.OnClickListener
                                public void onClick(View view) {
                                    MainAct.this.ak.cancel();
                                    MainAct.this.G.d();
                                    MainAct.this.g.b.g(1);
                                }
                            };
                        }
                        button.setOnClickListener(onClickListener);
                        MainAct.this.ak.show();
                        return;
                    } else if (MainAct.this.f.w) {
                        MainAct.this.f.w = false;
                        MainAct.this.f.a(b.c.KEYCODE_HOME);
                        MainAct.this.b.b();
                    }
                }
                a.b("flicking requestSyncStatusInformation");
                MainAct mainAct2 = MainAct.this;
                mainAct2.k = true;
                mainAct2.f.w();
                MainAct.this.b.b();
            }

            @Override // android.support.v4.view.ViewPager.e
            public void a(int i, float f, int i2) {
            }

            @Override // android.support.v4.view.ViewPager.e
            public void b(int i) {
                MainAct.this.c();
            }
        });
    }

    private void H() {
        e eVar;
        float f;
        float f2;
        float f3;
        e eVar2 = this.f;
        int width = getWindowManager().getDefaultDisplay().getWidth();
        eVar2.W = width;
        e eVar3 = this.f;
        int height = getWindowManager().getDefaultDisplay().getHeight();
        eVar3.X = height;
        a.a("Window height : " + this.f.X + ", width : " + this.f.W);
        if (this.f.W > this.f.X) {
            height = this.f.W;
            width = this.f.X;
        }
        while (true) {
            int i = height % width;
            if (i == 0) {
                break;
            }
            height = width;
            width = i;
        }
        int i2 = this.f.W / width;
        int i3 = this.f.X / width;
        a.a("Window resolution " + i3 + ":" + i2);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i4 = displayMetrics.densityDpi;
        if (i4 == 120 || i4 == 160) {
            a.b("Screen's density is medium");
            this.f.T = "MDPI";
        } else if (i4 == 213 || i4 == 240) {
            a.b("Screen's density is high");
            this.f.T = "HDPI";
        } else if (i4 == 320) {
            a.b("Screen's density is xhigh");
            this.f.T = "XHDPI";
        }
        Configuration configuration = getResources().getConfiguration();
        if ((configuration.screenLayout & 15) == 1) {
            a.b("Screen is small");
            this.f.V = 1;
        } else if ((configuration.screenLayout & 15) == 2) {
            a.b("Screen is normal");
            this.f.V = 2;
        } else if ((configuration.screenLayout & 15) == 3) {
            a.b("Screen is large");
            this.f.V = 3;
        } else if ((configuration.screenLayout & 15) == 4) {
            a.b("Screen is xlarge");
            this.f.V = 4;
        }
        if (this.f.V == 1 || this.f.V == 2) {
            if (i2 == 3 && i3 == 4) {
                eVar = this.f;
                eVar.S = true;
                f = eVar.X / 1280.0f;
            }
            a.a("ratio : " + this.f.U);
        }
        if (this.f.V == 3 || this.f.V == 4) {
            if (i3 - i2 == 1) {
                this.f.S = true;
            }
            if (this.f.T.equals("MDPI")) {
                eVar = this.f;
                f2 = eVar.X;
                f3 = 480.0f;
            } else if (this.f.T.equals("HDPI")) {
                eVar = this.f;
                f2 = eVar.X;
                f3 = 800.0f;
            } else if (this.f.T.equals("XHDPI")) {
                eVar = this.f;
                f = eVar.X / 1280.0f;
            }
            f = f2 / f3;
        }
        a.a("ratio : " + this.f.U);
        eVar.U = f;
        a.a("ratio : " + this.f.U);
    }

    private void I() {
        aE.clear();
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(this.aD.getString(D, null), "&");
            while (stringTokenizer.hasMoreTokens()) {
                aE.add(stringTokenizer.nextToken());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void J() {
        this.ac = null;
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.ag = null;
        this.ah = null;
        this.ai = null;
        this.aj = null;
        this.ak = null;
        this.al = null;
        this.am = null;
        this.an = null;
        this.E = null;
        this.aF = null;
        com.lge.media.launcher.syncstatus.l lVar = this.d;
        if (lVar != null) {
            lVar.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K() {
        this.c.setVisibility(0);
        this.I.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L() {
        this.c.setVisibility(8);
        this.I.setVisibility(0);
    }

    private boolean M() {
        YouTubeSearchView youTubeSearchView = this.I;
        return youTubeSearchView != null && youTubeSearchView.getVisibility() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N() {
        Button button;
        StringBuilder sb;
        int i;
        if (this.f.u) {
            this.e.setBackgroundResource(b.g.btn_spk_p);
            button = this.e;
            sb = new StringBuilder();
            sb.append(getString(b.m.label_output_speaker));
            sb.append(", ");
            i = b.m.label_on;
        } else {
            this.e.setBackgroundResource(b.g.btn_spk_n);
            button = this.e;
            sb = new StringBuilder();
            sb.append(getString(b.m.label_output_speaker));
            sb.append(", ");
            i = b.m.label_off;
        }
        sb.append(getString(i));
        button.setContentDescription(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void m() {
        /*
            Method dump skipped, instructions count: 499
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.control.common.MainAct.m():void");
    }

    private void n() {
        this.f.v();
        this.f.t();
        this.f.A();
        this.f.x();
        this.f.q();
        this.f.B();
        this.f.E();
    }

    private void o() {
        a.c("MainAct initPopupView");
        this.ak = new Dialog(this, 16973840);
        this.ak.setContentView(b.j.popup);
        this.L = (Button) this.ak.findViewById(b.h.btn_popup_one);
        Button button = (Button) this.ak.findViewById(b.h.btn_popup_two);
        ((TextView) this.ak.findViewById(b.h.text_popup_content)).setText(b.m.end_content_q);
        this.L.setText(b.m.ok);
        button.setText(b.m.cancel);
        this.L.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainAct.this.ak.cancel();
                MainAct.this.G.d();
                MainAct.this.f.a(b.c.KEYCODE_STOP);
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainAct.this.c.setCurrentItem(MainAct.this.ax);
                MainAct.this.G.c();
                MainAct.this.ak.cancel();
            }
        });
        if (h) {
            this.i = false;
        } else if (this.f.n().P()) {
            a.b("init requestSyncStatusInformation");
            this.f.w();
            this.f.w = false;
        }
        this.am = new Dialog(this, 16973840);
        this.am.setContentView(b.j.sp_popup);
        View findViewById = this.am.findViewById(b.h.popup_title);
        findViewById.setContentDescription(getString(b.m.sound_privacy) + ", " + getString(b.m.label_title) + 1);
        ((Button) this.am.findViewById(b.h.btn_popup_one)).setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.27
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainAct.this.f.n().R()) {
                    MainAct.this.g.b.a(com.lge.media.launcher.a.d, false);
                }
                MainAct.this.f.J = true;
                f.d();
                MainAct.this.am.cancel();
                a.c("SPOKListener btn click");
                MainAct.this.f.u = true;
                a.c("btnEnable");
                MainAct.this.e.setEnabled(false);
                MainAct.this.f.an.start();
                MainAct.this.N();
                MainAct.this.f.H.a(MainAct.this.f.u);
            }
        });
        ((Button) this.am.findViewById(b.h.btn_popup_two)).setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.28
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainAct.this.f.J = false;
                f.d();
                a.c("btnEnable");
                MainAct.this.e.setEnabled(false);
                MainAct.this.f.an.start();
                MainAct.this.am.cancel();
                MainAct.this.e.setBackgroundResource(b.g.btn_spk_n);
                MainAct.this.e.setContentDescription(MainAct.this.getString(b.m.label_output_speaker));
            }
        });
        this.al = new Dialog(this, 16973840);
        this.al.setContentView(b.j.popup);
        Button button2 = (Button) this.al.findViewById(b.h.btn_popup_one);
        Button button3 = (Button) this.al.findViewById(b.h.btn_popup_two);
        ((TextView) this.al.findViewById(b.h.text_popup_content)).setText(b.m.no_response);
        button2.setText(b.m.ok);
        button3.setText(b.m.cancel);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.29
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a.a("Device no response popup close");
                MainAct.this.al.cancel();
                if (MainAct.this.av != MainAct.this.aw) {
                    MainAct.this.f.w();
                } else if (MainAct.h || MainAct.this.f.n().S()) {
                    MainAct.this.f.C();
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.30
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.lge.media.launcher.syncstatus.l lVar;
                int i;
                a.a("Device no response popup close");
                if (MainAct.this.av == MainAct.this.aw) {
                    if ((MainAct.h || MainAct.this.f.n().S()) && MainAct.this.t != null) {
                        MainAct.this.H.a(MainAct.this.t);
                    }
                    MainAct.this.c.setCurrentItem(MainAct.this.ax);
                } else if (MainAct.this.s != null) {
                    if (MainAct.this.s.a.equals("radio")) {
                        Intent intent = new Intent(MainAct.this, RadioAct.class);
                        intent.putExtra(d.Q, MainAct.h);
                        MainAct.this.startActivityForResult(intent, 100);
                    } else if (MainAct.this.s.a.equals("home")) {
                        if (!MainAct.h) {
                            if (MainAct.this.k || MainAct.this.c.getCurrentItem() == MainAct.this.ay) {
                                MainAct mainAct = MainAct.this;
                                mainAct.k = false;
                                mainAct.f.w = false;
                                MainAct.this.f.e = false;
                            } else {
                                MainAct.this.G.d();
                                MainAct.this.c.setCurrentItem(MainAct.this.ax);
                            }
                        }
                        MainAct.this.d.a(0);
                        MainAct.this.c.getAdapter().notifyDataSetChanged();
                        MainAct.this.c.setCurrentItem(MainAct.this.ay);
                        MainAct.this.b.a(MainAct.this.s);
                    } else if (MainAct.this.s.a.equals("smartShare")) {
                        MainAct.this.d.a(1);
                        MainAct.this.c.getAdapter().notifyDataSetChanged();
                        MainAct.this.c.setCurrentItem(MainAct.this.ay);
                        MainAct.this.b.a(MainAct.this.s);
                    } else {
                        if (MainAct.this.s.a.equals("inputDevice")) {
                            MainAct.this.c.setCurrentItem(MainAct.this.ay);
                            lVar = MainAct.this.d;
                            i = 2;
                        } else if (!MainAct.this.s.a.equals("playback")) {
                            if (MainAct.this.s.a == null) {
                                a.a("SYNC INFORMATION is null. type is null.");
                            }
                            MainAct.this.c.setCurrentItem(MainAct.this.ax);
                        } else if (MainAct.this.s.d.equals("MUSIC")) {
                            MainAct.this.c.setCurrentItem(MainAct.this.ay);
                            lVar = MainAct.this.d;
                            i = 3;
                        } else {
                            MainAct.this.c.setCurrentItem(MainAct.this.ax);
                            MainAct.this.G.c();
                        }
                        lVar.a(i);
                        MainAct.this.c.getAdapter().notifyDataSetChanged();
                        MainAct.this.b.a(MainAct.this.s);
                    }
                }
                MainAct mainAct2 = MainAct.this;
                mainAct2.k = false;
                mainAct2.al.cancel();
            }
        });
        u();
        t();
        s();
        r();
        q();
        p();
    }

    private void p() {
        this.as = new Dialog(this, 16973840);
        this.as.setContentView(b.j.popup);
        ((TextView) this.as.findViewById(b.h.text_popup_content)).setText(b.m.power_off);
        Button button = (Button) this.as.findViewById(b.h.btn_popup_one);
        button.setText(b.m.ok);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                MainAct.this.f.a(b.c.KEYCODE_POWER);
                MainAct.this.as.dismiss();
                Intent intent = new Intent();
                intent.putExtra("mac", MainAct.this.f.g());
                a.b("power off. mac " + MainAct.this.f.g());
                MainAct.this.setResult(101, intent);
                MainAct.this.finish();
            }
        });
        Button button2 = (Button) this.as.findViewById(b.h.btn_popup_two);
        button2.setText(b.m.cancel);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                MainAct.this.as.dismiss();
            }
        });
    }

    private void q() {
        this.ar = new Dialog(this, 16973840);
        this.ar.setContentView(b.j.popup);
        this.ar.findViewById(b.h.btn_popup_two).setVisibility(8);
        Button button = (Button) this.ar.findViewById(b.h.btn_popup_one);
        button.setText(b.m.ok);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                MainAct.this.ar.dismiss();
            }
        });
    }

    private void r() {
        this.aq = new Dialog(this, 16973840);
        this.aq.setContentView(b.j.popup);
        this.aq.findViewById(b.h.btn_popup_two).setVisibility(8);
        ((TextView) this.aq.findViewById(b.h.text_popup_content)).setText(b.m.sound_privacy_not_support);
        Button button = (Button) this.aq.findViewById(b.h.btn_popup_one);
        button.setText(b.m.ok);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                MainAct.this.aq.dismiss();
            }
        });
    }

    private void s() {
        this.ap = new Dialog(this, 16973840);
        this.ap.setContentView(b.j.popup);
        this.ap.findViewById(b.h.btn_popup_two).setVisibility(8);
        ((TextView) this.ap.findViewById(b.h.text_popup_content)).setText(b.m.sound_privacy_error);
        Button button = (Button) this.ap.findViewById(b.h.btn_popup_one);
        button.setText(b.m.ok);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                MainAct.this.ap.dismiss();
                a.c("SPEOKListener");
                MainAct.this.f.u = false;
                a.c("isSpOn : " + MainAct.this.f.u);
                try {
                    MainAct.this.a();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                MainAct.this.N();
                MainAct.this.f.H.a(MainAct.this.f.u);
            }
        });
    }

    private void t() {
        this.ao = new Dialog(this, 16973840);
        this.ao.setContentView(b.j.popup);
        ((TextView) this.ao.findViewById(b.h.text_popup_content)).setText(b.m.device_connect_fail);
        Button button = (Button) this.ao.findViewById(b.h.btn_popup_one);
        button.setText(b.m.ok);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                MainAct.this.ao.dismiss();
                MainAct.this.f.m();
            }
        });
        Button button2 = (Button) this.ao.findViewById(b.h.btn_popup_two);
        button2.setText(b.m.cancel);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                MainAct.this.ao.dismiss();
                MainAct.this.finish();
            }
        });
    }

    private void u() {
        int i;
        this.an = new Dialog(this, 16973840);
        this.an.setContentView(b.j.external_speaker_popup);
        View findViewById = this.an.findViewById(b.h.popup_title);
        findViewById.setContentDescription(getString(b.m.external_speaker) + ", " + getString(b.m.label_title) + 1);
        this.O = (LinearLayout) this.an.findViewById(b.h.home_layout);
        this.P = (LinearLayout) this.an.findViewById(b.h.sound_privacy_layout);
        this.Q = (LinearLayout) this.an.findViewById(b.h.multiroom_layout);
        this.R = (LinearLayout) this.an.findViewById(b.h.multi_room_radio_layout);
        this.S = (LinearLayout) this.an.findViewById(b.h.multi_room_only_select_layout);
        this.U = (RadioButton) this.an.findViewById(b.h.home_radio_button);
        this.V = (RadioButton) this.an.findViewById(b.h.private_sound_radio_button);
        this.W = (RadioButton) this.an.findViewById(b.h.multi_room_speaker_button);
        TextView textView = (TextView) this.an.findViewById(b.h.home_radio_text);
        this.X = (TextView) this.an.findViewById(b.h.multi_room_speaker_text);
        TextView textView2 = (TextView) this.an.findViewById(b.h.mute_external_speaker_textview);
        this.T = (LinearLayout) this.an.findViewById(b.h.mute_external_speaker_layout);
        this.T.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainAct.this.W.isChecked()) {
                    MainAct.this.Y.setChecked(!MainAct.this.Y.isChecked());
                }
            }
        });
        this.Y = (CheckBox) this.an.findViewById(b.h.mute_external_speaker_checkbox);
        this.aa = (CheckBox) this.an.findViewById(b.h.mute_external_speaker_only_checkbox);
        this.Z = (Button) this.an.findViewById(b.h.btn_popup_one);
        Button button = (Button) this.an.findViewById(b.h.btn_popup_two);
        this.U.setChecked(true);
        this.O.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainAct.this.x();
            }
        });
        int f = this.f.f();
        if (f != 0) {
            if (f == 1) {
                textView.setText(b.m.lg_bluray_home_theater);
                i = b.m.mute_bluray_hometheater;
            }
            this.P.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.11
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MainAct.this.y();
                }
            });
            this.R.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.13
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MainAct.this.z();
                }
            });
            this.Q.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.14
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MainAct.this.z();
                }
            });
            this.Z.setText(b.m.ok);
            this.Z.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.15
                /* JADX WARN: Code restructure failed: missing block: B:21:0x00ca, code lost:
                    if (r10.a.f.n().R() != false) goto L35;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:37:0x0140, code lost:
                    if (r10.a.f.n().R() != false) goto L5;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:40:0x0166, code lost:
                    if (r10.a.f.n().R() != false) goto L35;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:41:0x0168, code lost:
                    r10.a.g.b.a(r11.substring(0, r11.length() - 1), r10.a.Y.isChecked());
                 */
                /* JADX WARN: Code restructure failed: missing block: B:5:0x002a, code lost:
                    if (r10.a.f.n().R() != false) goto L5;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:6:0x002c, code lost:
                    r10.a.g.b.a(com.lge.media.launcher.a.d, false);
                 */
                @Override // android.view.View.OnClickListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onClick(android.view.View r11) {
                    /*
                        Method dump skipped, instructions count: 418
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.control.common.MainAct.AnonymousClass15.onClick(android.view.View):void");
                }
            });
            button.setText(b.m.cancel);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.16
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (MainAct.this.an == null || !MainAct.this.an.isShowing()) {
                        return;
                    }
                    MainAct.this.an.cancel();
                }
            });
            this.aa.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.17
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (MainAct.this.aa.isChecked()) {
                        a.b("checkBoxSelectAll onClick. checked");
                        for (com.lge.media.launcher.a.b bVar : MainAct.this.u.a) {
                            bVar.a(true);
                        }
                    } else {
                        a.b("checkBoxSelectAll onClick. unchecked");
                        for (com.lge.media.launcher.a.b bVar2 : MainAct.this.u.a) {
                            bVar2.a(false);
                        }
                    }
                    if (MainAct.this.az != null) {
                        MainAct.this.az.notifyDataSetChanged();
                    }
                }
            });
            this.ac = getResources().getDrawable(b.g.popup_mono_p);
            this.ad = getResources().getDrawable(b.g.popup_stereo_p);
            this.ae = getResources().getDrawable(b.g.popup_leftt_p);
            this.af = getResources().getDrawable(b.g.popup_right_p);
            this.ag = getResources().getDrawable(b.g.popup_mono_d);
            this.ah = getResources().getDrawable(b.g.popup_stereo_d);
            this.ai = getResources().getDrawable(b.g.popup_leftt_d);
            this.aj = getResources().getDrawable(b.g.popup_right_d);
        }
        textView.setText(b.m.lg_bluray_player);
        i = b.m.mute_bluray_player;
        textView2.setText(getString(i));
        this.P.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainAct.this.y();
            }
        });
        this.R.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainAct.this.z();
            }
        });
        this.Q.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainAct.this.z();
            }
        });
        this.Z.setText(b.m.ok);
        this.Z.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.15
            @Override // android.view.View.OnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onClick(android.view.View r11) {
                /*
                    Method dump skipped, instructions count: 418
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.control.common.MainAct.AnonymousClass15.onClick(android.view.View):void");
            }
        });
        button.setText(b.m.cancel);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainAct.this.an == null || !MainAct.this.an.isShowing()) {
                    return;
                }
                MainAct.this.an.cancel();
            }
        });
        this.aa.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.MainAct.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainAct.this.aa.isChecked()) {
                    a.b("checkBoxSelectAll onClick. checked");
                    for (com.lge.media.launcher.a.b bVar : MainAct.this.u.a) {
                        bVar.a(true);
                    }
                } else {
                    a.b("checkBoxSelectAll onClick. unchecked");
                    for (com.lge.media.launcher.a.b bVar2 : MainAct.this.u.a) {
                        bVar2.a(false);
                    }
                }
                if (MainAct.this.az != null) {
                    MainAct.this.az.notifyDataSetChanged();
                }
            }
        });
        this.ac = getResources().getDrawable(b.g.popup_mono_p);
        this.ad = getResources().getDrawable(b.g.popup_stereo_p);
        this.ae = getResources().getDrawable(b.g.popup_leftt_p);
        this.af = getResources().getDrawable(b.g.popup_right_p);
        this.ag = getResources().getDrawable(b.g.popup_mono_d);
        this.ah = getResources().getDrawable(b.g.popup_stereo_d);
        this.ai = getResources().getDrawable(b.g.popup_leftt_d);
        this.aj = getResources().getDrawable(b.g.popup_right_d);
    }

    private void v() {
        this.W.setEnabled(false);
        this.Q.setEnabled(false);
        this.X.setTextColor(getResources().getColor(b.e.externel_speaker_text_disable));
        this.Y.setEnabled(false);
        com.lge.media.launcher.syncstatus.k kVar = this.s;
        if (kVar == null || !kVar.a.equals("playback")) {
            a.b("syncStatusInfo not playback");
            if (this.f.n().O() || !this.f.n().R()) {
                return;
            }
            a.b("syncStatusInfo not playback & only");
        } else {
            a.b("syncStatusInfo playback");
            if (this.s.d.equals("MUSIC")) {
                a.b("syncStatusInfo playback MUSIC");
                if (this.f.n().O() || !this.f.n().R()) {
                    return;
                }
            } else {
                a.b("syncStatusInfo playback " + this.s.d);
                if (this.f.n().O() || !this.f.n().R()) {
                    return;
                }
            }
            a.b("syncStatusInfo playback & only");
        }
        this.O.setVisibility(8);
        this.P.setVisibility(8);
        this.Q.setClickable(false);
        this.R.setVisibility(4);
        this.S.setVisibility(0);
        A();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        v();
        if (this.f.u) {
            E();
        }
        Dialog dialog = this.an;
        if (dialog != null) {
            dialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        a.b("popupspeaker setHomeStatus");
        if (!this.p) {
            this.U.setChecked(true);
            this.Y.setEnabled(false);
            this.Y.setChecked(false);
        }
        com.lge.media.launcher.a.a aVar = this.u;
        if (aVar == null || aVar.a.isEmpty()) {
            this.R.setEnabled(false);
        }
        this.V.setChecked(false);
        this.W.setChecked(false);
        this.T.setEnabled(false);
        this.Z.setEnabled(true);
        ArrayAdapter arrayAdapter = this.az;
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
        this.O.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.control.common.MainAct.18
            /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
            /* JADX WARN: Removed duplicated region for block: B:12:0x003d  */
            @Override // android.view.View.AccessibilityDelegate
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onInitializeAccessibilityNodeInfo(android.view.View r5, android.view.accessibility.AccessibilityNodeInfo r6) {
                /*
                    r4 = this;
                    super.onInitializeAccessibilityNodeInfo(r5, r6)
                    com.lge.media.launcher.control.common.MainAct r5 = com.lge.media.launcher.control.common.MainAct.this
                    com.lge.media.launcher.control.common.e r5 = r5.f
                    int r5 = r5.f()
                    r0 = 1
                    if (r5 == 0) goto L19
                    if (r5 == r0) goto L13
                    java.lang.String r5 = ""
                    goto L22
                L13:
                    com.lge.media.launcher.control.common.MainAct r5 = com.lge.media.launcher.control.common.MainAct.this
                    r1 = 2131558590(0x7f0d00be, float:1.87425E38)
                    goto L1e
                L19:
                    com.lge.media.launcher.control.common.MainAct r5 = com.lge.media.launcher.control.common.MainAct.this
                    r1 = 2131558591(0x7f0d00bf, float:1.8742502E38)
                L1e:
                    java.lang.String r5 = r5.getString(r1)
                L22:
                    com.lge.media.launcher.control.common.MainAct r1 = com.lge.media.launcher.control.common.MainAct.this
                    android.widget.RadioButton r1 = com.lge.media.launcher.control.common.MainAct.A(r1)
                    boolean r1 = r1.isChecked()
                    r2 = 0
                    if (r1 == 0) goto L3d
                    com.lge.media.launcher.control.common.MainAct r1 = com.lge.media.launcher.control.common.MainAct.this
                    r3 = 2131558576(0x7f0d00b0, float:1.8742472E38)
                    java.lang.Object[] r0 = new java.lang.Object[r0]
                    r0[r2] = r5
                    java.lang.String r5 = r1.getString(r3, r0)
                    goto L4a
                L3d:
                    com.lge.media.launcher.control.common.MainAct r1 = com.lge.media.launcher.control.common.MainAct.this
                    r3 = 2131558545(0x7f0d0091, float:1.8742409E38)
                    java.lang.Object[] r0 = new java.lang.Object[r0]
                    r0[r2] = r5
                    java.lang.String r5 = r1.getString(r3, r0)
                L4a:
                    r6.setContentDescription(r5)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.control.common.MainAct.AnonymousClass18.onInitializeAccessibilityNodeInfo(android.view.View, android.view.accessibility.AccessibilityNodeInfo):void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        a.b("popupspeaker setSoundPrivacyStatus");
        this.V.setChecked(true);
        this.R.setEnabled(true);
        this.U.setChecked(false);
        this.W.setChecked(false);
        this.Y.setEnabled(false);
        this.Y.setChecked(false);
        this.T.setEnabled(false);
        this.Z.setEnabled(true);
        com.lge.media.launcher.a.a aVar = this.u;
        if (aVar == null || aVar.a.isEmpty()) {
            this.R.setEnabled(false);
        }
        ArrayAdapter arrayAdapter = this.az;
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
        this.P.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.control.common.MainAct.19
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                String string;
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                if (MainAct.this.V.isChecked()) {
                    MainAct mainAct = MainAct.this;
                    string = mainAct.getString(b.m.label_selected, new Object[]{mainAct.getString(b.m.sound_privacy)});
                } else {
                    MainAct mainAct2 = MainAct.this;
                    string = mainAct2.getString(b.m.label_not_selected, new Object[]{mainAct2.getString(b.m.sound_privacy)});
                }
                accessibilityNodeInfo.setContentDescription(string);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        a.b("popupspeaker setExternalSpeakerStatus");
        this.W.setChecked(true);
        this.U.setChecked(false);
        this.V.setChecked(false);
        this.Y.setEnabled(true);
        this.T.setEnabled(true);
        this.Z.setEnabled(false);
        ArrayAdapter arrayAdapter = this.az;
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
        com.lge.media.launcher.a.a aVar = this.u;
        if (aVar == null || aVar.a.isEmpty()) {
            this.R.setEnabled(false);
        }
        com.lge.media.launcher.a.a aVar2 = this.u;
        if (aVar2 == null) {
            return;
        }
        for (com.lge.media.launcher.a.b bVar : aVar2.a) {
            if (bVar.c()) {
                this.Z.setEnabled(true);
            }
        }
        this.R.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.control.common.MainAct.20
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                String string;
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                if (MainAct.this.W.isChecked()) {
                    MainAct mainAct = MainAct.this;
                    string = mainAct.getString(b.m.label_selected, new Object[]{mainAct.getString(b.m.music_flow)});
                } else {
                    MainAct mainAct2 = MainAct.this;
                    string = mainAct2.getString(b.m.label_not_selected, new Object[]{mainAct2.getString(b.m.music_flow)});
                }
                accessibilityNodeInfo.setContentDescription(string);
            }
        });
    }

    public void a() {
        this.B = new Thread(new Runnable() { // from class: com.lge.media.launcher.control.common.MainAct.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    MainAct.this.b();
                } catch (Exception e) {
                    a.a("Exception : " + e.toString());
                }
            }
        });
        a.c("changeToWakeMode");
        this.B.setDaemon(true);
        this.B.start();
    }

    public void a(int i) {
        this.aw = i - 1;
        this.ax = i;
        this.ay = i + 1;
    }

    public void a(g gVar) {
        this.H = gVar;
    }

    public void a(h hVar) {
        this.G = hVar;
    }

    public void a(m mVar) {
        this.b = mVar;
    }

    public void b() {
        if (this.A == null) {
            this.A = ((PowerManager) getSystemService("power")).newWakeLock(1, d.a);
            if (!this.A.isHeld()) {
                this.A.acquire();
            }
        }
        if (this.z == null) {
            this.z = ((WifiManager) getApplicationContext().getSystemService("wifi")).createWifiLock(1, d.a);
            if (this.z.isHeld()) {
                return;
            }
            this.z.acquire();
        }
    }

    public void c() {
        b.c cVar = this.q;
        if (cVar != null) {
            this.f.c(cVar);
        }
    }

    public void d() {
        if (((ActivityManager) getSystemService("activity")).getRunningTasks(1).get(0).baseActivity.getPackageName().equals("com.lge.media.launcher")) {
            return;
        }
        a.a("LG Remote app is not Foreground");
        if (this.f.u) {
            a.a("SP sound off");
            e eVar = this.f;
            eVar.u = false;
            eVar.H.a(this.f.u);
            N();
        }
    }

    public void e() {
        a.c("Audio Vol up");
        int streamVolume = this.r.getStreamVolume(3);
        if (streamVolume < this.r.getStreamMaxVolume(3)) {
            this.r.setStreamVolume(3, streamVolume + 1, 4);
        }
    }

    public void f() {
        a.c("Audio Vol down");
        int streamVolume = this.r.getStreamVolume(3);
        if (streamVolume > 0) {
            this.r.setStreamVolume(3, streamVolume - 1, 4);
        }
    }

    public int g() {
        return this.aw;
    }

    public int h() {
        return this.ax;
    }

    public int i() {
        return this.ay;
    }

    public void j() {
        if (aE != null) {
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = aE.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append("&");
            }
            this.aD.edit().putString(D, sb.toString()).commit();
        }
    }

    public ArrayList<String> k() {
        return aE;
    }

    public void l() {
        a.a("initViewFinish()");
        if (!h) {
            this.f.D();
        }
        if (h || this.f.n().S()) {
            this.f.C();
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 100) {
            this.G.e();
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        YouTubeSearchView youTubeSearchView = this.I;
        if (youTubeSearchView == null || !youTubeSearchView.onBackPressed()) {
            if (M()) {
                K();
                return;
            }
            ViewPager viewPager = this.c;
            if (viewPager == null || viewPager.getCurrentItem() == this.ax) {
                a.b("Go to choose device view");
                this.i = true;
                super.onBackPressed();
                return;
            }
            a.b("Go to main control view");
            this.c.setCurrentItem(this.ax);
            this.k = false;
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        e eVar;
        int i;
        e eVar2;
        super.onCreate(bundle);
        a.c("MainAct onCreate");
        this.o = getIntent().getBooleanExtra(d.R, false);
        a.a("Strat launcher : " + this.o);
        if (this.o) {
            f.a(getApplicationContext(), this.at);
            this.f = e.a(this, this.E);
            e eVar3 = this.f;
            eVar3.s = null;
            eVar3.A = this.o;
            this.y = getIntent().getExtras().getString(d.S);
            a.b("selectDeviceName : " + this.y);
            this.f.c(this.y);
            this.f.N = getIntent().getExtras().getString(d.T);
            a.b("serverIP : " + this.f.N);
            this.f.O = getIntent().getExtras().getInt(d.U);
            a.b("serverPort : " + this.f.O);
            this.f.P = getIntent().getExtras().getByte(d.V);
            a.b("serverPortType : " + ((int) this.f.P));
            setContentView(b.j.main_page);
            if (this.y.contains(d.A)) {
                this.f.c(0);
            } else if (this.y.contains(d.B) || this.y.contains(d.F) || this.y.contains(d.G)) {
                this.f.c(1);
            } else {
                if (this.y.contains(d.C)) {
                    eVar = this.f;
                    i = 2;
                } else if (this.y.contains(d.D)) {
                    eVar = this.f;
                    i = 3;
                }
                eVar.c(i);
            }
            WifiInfo connectionInfo = ((WifiManager) getApplicationContext().getSystemService("wifi")).getConnectionInfo();
            String macAddress = connectionInfo.getMacAddress();
            a.a("ap mac : " + macAddress);
            a.a("ap bssid : " + connectionInfo.getBSSID());
            int ipAddress = connectionInfo.getIpAddress();
            String num = Integer.toString(ipAddress & 255);
            for (int i2 = 8; i2 <= 24; i2 += 8) {
                num = num.concat(".").concat(Integer.toString((ipAddress >> i2) & 255));
            }
            PrintStream printStream = System.out;
            printStream.println("wifi ip : " + num);
            if (macAddress == null) {
                a.a("MAC address is null");
                eVar2 = this.f;
                macAddress = "00:00:00:00:00:00";
            } else {
                eVar2 = this.f;
            }
            eVar2.a(macAddress);
            this.f.k();
            this.f.d(num);
            this.f.h();
            this.f.i();
            a.b("Get resolution");
            H();
        } else {
            h = getIntent().getBooleanExtra(d.Q, false);
            a.a("MainAct isDemo : " + h);
            setContentView(b.j.main_page);
            this.f = e.a(this, this.E);
            if (this.f.c() == 0) {
                a.a("device list size is 0. finish");
                finish();
                startActivity(new Intent(this, ChooseDeviceAct.class));
                return;
            }
            this.g = this.f.p();
            this.f.a(this);
            this.f.s = null;
            if (this.g == null) {
                a.a("roapMan is null");
            }
            if (h) {
                m();
                G();
            } else {
                this.f.i();
            }
        }
        try {
            this.aA = NfcAdapter.getDefaultAdapter(this);
            if (this.aA == null) {
                a.b("This device does not support NFc");
            }
            this.aB = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(536870912), 0);
            this.aC = new IntentFilter[]{new IntentFilter("android.nfc.action.TAG_DISCOVERED")};
        } catch (Exception unused) {
            a.a("NFC Adapter exception");
        }
        this.aD = getSharedPreferences(C, 0);
        I();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(b.k.options_menu, menu);
        return true;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        a.b("MainAct OnDestroy");
        this.i = true;
        this.au = false;
        if (this.f.u) {
            a.a("MainAct1 OnDestroy. sp off");
            this.f.an.start();
            e eVar = this.f;
            eVar.u = false;
            eVar.H.a(this.f.u);
        }
        WatchSoundPrivacy watchSoundPrivacy = this.J;
        if (watchSoundPrivacy != null) {
            unregisterReceiver(watchSoundPrivacy);
        }
        this.f.A = false;
        J();
        super.onDestroy();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 2131231011) {
            f.d();
            Intent intent = new Intent(this, SettingsAct.class);
            if (!h && this.f.n().h()) {
                intent.putExtra("touchpad", true);
            }
            intent.putExtra(d.Q, h);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        a.b("MainAct onPause");
        d();
        j();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        a.c("MainAct OnResume");
        this.f = e.a(this, this.E);
        try {
            this.aA.enableForegroundDispatch(this, this.aB, this.aC, null);
        } catch (Exception unused) {
            a.a("NFC Adapter exception");
        }
        d();
        if (h) {
            return;
        }
        if (!this.i && this.f.w && !this.f.g) {
            this.f.w = false;
            this.d.a(0);
            this.c.getAdapter().notifyDataSetChanged();
            this.c.setCurrentItem(this.ax);
            N();
            com.lge.media.launcher.syncstatus.k kVar = this.s;
            if (kVar != null && kVar.a.equals("playback") && this.s.d.equals("MOVIE")) {
                this.G.c();
            }
        }
        if (this.f.g) {
            this.f.g = false;
            this.d.a(0);
            this.c.getAdapter().notifyDataSetChanged();
            this.c.setCurrentItem(this.ax);
            N();
        }
        if (this.f.e) {
            e eVar = this.f;
            eVar.e = false;
            eVar.i = null;
        }
        if (this.f.z) {
            e eVar2 = this.f;
            eVar2.z = false;
            eVar2.w();
        }
    }
}
