package com.lge.media.launcher.control.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.AudioTrack;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import com.lge.UDAP.ROAP.a;
import com.lge.UDAP.ROAP.a.b;
import com.lge.UDAP.ROAP.b;
import com.lge.UDAP.ROAP.b.a;
import com.lge.UDAP.ROAP.c.d;
import com.lge.UDAP.a.a;
import com.lge.UDAP.a.c;
import com.lge.media.launcher.b;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class e {
    public static boolean E = false;
    public static final int a = 101;
    private static final e ao = new e();
    private static Handler aq;
    private static Context ar;
    public String K;
    public int L;
    public int M;
    public String N;
    public int O;
    public byte P;
    private String ap;
    private com.lge.UDAP.ROAP.e.a ay;
    private com.lge.UDAP.ROAP.b az;
    public String b;
    public String c;
    private String as = null;
    private String at = null;
    private int au = -1;
    private int av = -1;
    private boolean aw = false;
    private int ax = 44100;
    public View d = null;
    public boolean e = false;
    public boolean f = false;
    public boolean g = false;
    public String h = com.lge.media.launcher.a.d;
    public String i = com.lge.media.launcher.a.d;
    public boolean j = false;
    public boolean k = false;
    public int l = 0;
    public boolean m = true;
    public int n = 0;
    public boolean o = true;
    public boolean p = true;
    public int q = 0;
    private com.lge.media.launcher.contents.c aA = null;
    public com.lge.media.launcher.syncstatus.k r = null;
    public com.lge.media.launcher.cplist.c s = null;
    public com.lge.media.launcher.a.a t = null;
    private boolean aB = false;
    public boolean u = false;
    public boolean v = false;
    public boolean w = false;
    public boolean x = false;
    public boolean y = true;
    public boolean z = false;
    public boolean A = false;
    public boolean B = false;
    public boolean C = false;
    public boolean D = false;
    public int F = 1;
    public int G = 0;
    public com.lge.media.launcher.sp.a H = null;
    public AudioTrack I = null;
    public boolean J = false;
    public boolean Q = true;
    public boolean R = false;
    public boolean S = false;
    public String T = "HDPI";
    public float U = 1.0f;
    public int V = 0;
    public int W = 0;
    public int X = 0;
    private ArrayList<com.lge.media.launcher.ui.a> aC = new ArrayList<>();
    private ArrayList<com.lge.UDAP.a.c> aD = new ArrayList<>();
    public Handler Y = new Handler() { // from class: com.lge.media.launcher.control.common.e.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            com.lge.UDAP.a.c cVar;
            boolean z;
            StringBuilder sb;
            boolean z2;
            int i = message.what;
            if (i == 0) {
                if (message.obj != null) {
                    cVar = (com.lge.UDAP.a.c) message.obj;
                    if (e.this.aD.size() > 0) {
                        Iterator it = e.this.aD.iterator();
                        while (it.hasNext()) {
                            com.lge.UDAP.a.c cVar2 = (com.lge.UDAP.a.c) it.next();
                            Iterator it2 = it;
                            if (cVar2.d().equals(cVar.d()) && cVar2.g().equals(cVar.g()) && cVar2.k().equals(cVar.k())) {
                                z = true;
                                break;
                            }
                            it = it2;
                        }
                    }
                    z = false;
                    if (!z && (cVar.g().contains("av.bh.15y") || cVar.g().contains("av.nb.15y") || cVar.g().contains("av.bp.15y") || cVar.g().contains("av.lb.15y") || cVar.g().contains("av.bh.9430") || cVar.g().contains("av.nb.3730") || cVar.g().contains("av.bp.730") || cVar.g().contains("av.lb.540"))) {
                        sb = new StringBuilder();
                        sb.append("Device name : ");
                        sb.append(cVar.g());
                        a.a(sb.toString());
                        e.this.aD.add(cVar);
                    }
                }
                a.b("man, received data");
            } else if (i != 1) {
                if (i == 2) {
                    e.this.ay = (com.lge.UDAP.ROAP.e.a) message.obj;
                } else if (i != 3) {
                    if (i == 6) {
                        if (message.obj != null) {
                            cVar = (com.lge.UDAP.a.c) message.obj;
                            if (e.this.aD.size() > 0) {
                                Iterator it3 = e.this.aD.iterator();
                                while (it3.hasNext()) {
                                    com.lge.UDAP.a.c cVar3 = (com.lge.UDAP.a.c) it3.next();
                                    if (cVar3.d().equals(cVar.d()) && cVar3.g().equals(cVar.g()) && cVar3.k().equals(cVar.k())) {
                                        z2 = true;
                                        break;
                                    }
                                }
                            }
                            z2 = false;
                            if (!z2 && (cVar.g().contains("av.bh.15y") || cVar.g().contains("av.nb.15y") || cVar.g().contains("av.bp.15y") || cVar.g().contains("av.lb.15y") || cVar.g().contains("av.bh.9430") || cVar.g().contains("av.nb.3730") || cVar.g().contains("av.bp.730") || cVar.g().contains("av.lb.540"))) {
                                sb = new StringBuilder();
                                sb.append("Device name : ");
                                sb.append(cVar.g());
                                a.a(sb.toString());
                                e.this.aD.add(cVar);
                            }
                        }
                        a.b("man, received data");
                    } else if (i != 7 && i != 50) {
                        switch (i) {
                            case 25:
                            case 26:
                            case 27:
                                break;
                            default:
                                switch (i) {
                                    case 31:
                                    case 32:
                                    case 33:
                                    case 34:
                                    case 35:
                                    case 36:
                                    case 37:
                                        break;
                                    default:
                                        switch (i) {
                                            case 60:
                                            case 61:
                                            case 62:
                                            case 63:
                                                break;
                                            default:
                                                super.handleMessage(message);
                                                return;
                                        }
                                }
                        }
                    }
                }
            }
            e.this.a(message);
        }
    };
    public Handler Z = new Handler() { // from class: com.lge.media.launcher.control.common.e.32
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            String str;
            Handler handler;
            Handler handler2;
            Message obtainMessage;
            a.c("content handler");
            int i = message.what;
            if (i == 0) {
                e eVar = e.this;
                eVar.k = false;
                try {
                    eVar.g(new String((byte[]) message.obj, "UTF-8"));
                    return;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return;
                }
            }
            if (i == 1) {
                e eVar2 = e.this;
                eVar2.k = false;
                str = "2";
                if (eVar2.aB) {
                    handler = e.this.Z;
                    obtainMessage = handler.obtainMessage(4, str);
                } else {
                    a.b("bgm info failed");
                    handler2 = e.this.Z;
                    obtainMessage = handler2.obtainMessage(5, str);
                }
            } else if (i == 2) {
                e eVar3 = e.this;
                eVar3.k = false;
                if (eVar3.aB) {
                    e.this.a((byte[]) message.obj);
                    obtainMessage = e.this.Z.obtainMessage(4, "1");
                } else {
                    obtainMessage = e.this.Z.obtainMessage(5, "1");
                }
            } else if (i == 3) {
                e eVar4 = e.this;
                eVar4.k = false;
                str = "3";
                if (eVar4.aB) {
                    e.this.a(e.this.Z.obtainMessage(4, "3"));
                }
                if (e.this.aB && e.this.aA.c.contains(d.ar)) {
                    handler = e.this.Z;
                    str = "0";
                    obtainMessage = handler.obtainMessage(4, str);
                }
                handler2 = e.this.Z;
                obtainMessage = handler2.obtainMessage(5, str);
            } else if (i == 6) {
                e eVar5 = e.this;
                eVar5.k = false;
                if (eVar5.aB) {
                    return;
                }
                a.b("bgm info invalid");
                handler2 = e.this.Z;
                str = "4";
                obtainMessage = handler2.obtainMessage(5, str);
            } else if (i != 7) {
                super.handleMessage(message);
                return;
            } else {
                e eVar6 = e.this;
                eVar6.k = false;
                obtainMessage = eVar6.Z.obtainMessage(7);
            }
            e.this.a(obtainMessage);
        }
    };
    private Handler aE = new Handler() { // from class: com.lge.media.launcher.control.common.e.36
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 0) {
                e.this.d((b.c) message.obj);
                e.this.aE.sendMessageDelayed(e.this.aE.obtainMessage(message.what, message.obj), 200L);
            }
            super.handleMessage(message);
        }
    };
    public Handler aa = new Handler() { // from class: com.lge.media.launcher.control.common.e.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 40) {
                super.handleMessage(message);
                return;
            }
            a.c("set sync event.. ");
            e.this.a(e.this.aa.obtainMessage(40));
        }
    };
    public Handler ab = new Handler() { // from class: com.lge.media.launcher.control.common.e.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            int i2 = 25;
            if (i != 25) {
                i2 = 26;
                if (i != 26) {
                    return;
                }
            }
            e.this.a(e.this.ab.obtainMessage(i2));
        }
    };
    public Handler ac = new Handler() { // from class: com.lge.media.launcher.control.common.e.8
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            a.c("sync handler");
            int i = message.what;
            if (i == 20) {
                try {
                    a.c("getreadyinformation");
                    e.this.h(new String((byte[]) message.obj, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else if (i == 21) {
                e.this.a(e.this.ac.obtainMessage(21));
                e.this.j = false;
            } else {
                int i2 = 24;
                if (i != 24) {
                    i2 = 28;
                    if (i != 28) {
                        i2 = 70;
                        if (i != 70) {
                            super.handleMessage(message);
                            return;
                        }
                    }
                }
                e.this.a(e.this.ac.obtainMessage(i2));
            }
        }
    };
    public Handler ad = new Handler() { // from class: com.lge.media.launcher.control.common.e.9
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Handler handler;
            int i = message.what;
            int i2 = 16;
            if (i != 16) {
                i2 = 17;
                if (i != 17) {
                    switch (i) {
                        case 8:
                            handler = e.this.ad;
                            i2 = 8;
                            break;
                        case 9:
                            handler = e.this.ad;
                            i2 = 9;
                            break;
                        case 10:
                            handler = e.this.ad;
                            i2 = 10;
                            break;
                        case 11:
                            handler = e.this.ad;
                            i2 = 11;
                            break;
                        case 12:
                            handler = e.this.ad;
                            i2 = 12;
                            break;
                        default:
                            return;
                    }
                    e.this.a(handler.obtainMessage(i2));
                }
            }
            handler = e.this.ad;
            e.this.a(handler.obtainMessage(i2));
        }
    };
    public Handler ae = new Handler() { // from class: com.lge.media.launcher.control.common.e.16
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Handler handler;
            int i;
            a.c("cp list handler");
            switch (message.what) {
                case 101:
                    try {
                        a.c("get ready cp list");
                        e.this.i(new String((byte[]) message.obj, "UTF-8"));
                        return;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return;
                    }
                case 102:
                    if (e.this.n >= 5) {
                        handler = e.this.ae;
                        i = 102;
                        break;
                    } else if (MainAct.h || e.this.n().S()) {
                        e.this.C();
                        return;
                    } else {
                        return;
                    }
                case 103:
                    e eVar = e.this;
                    eVar.Q = false;
                    handler = eVar.ae;
                    i = 103;
                    break;
                case 104:
                    handler = e.this.ae;
                    i = 104;
                    break;
                case 105:
                    handler = e.this.ae;
                    i = 105;
                    break;
                case 106:
                    handler = e.this.ae;
                    i = 106;
                    break;
                case 107:
                    handler = e.this.ae;
                    i = 107;
                    break;
                case 108:
                    handler = e.this.ae;
                    i = 108;
                    break;
                default:
                    super.handleMessage(message);
                    return;
            }
            e.this.a(handler.obtainMessage(i));
        }
    };
    public Handler af = new Handler() { // from class: com.lge.media.launcher.control.common.e.20
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Handler handler;
            int i;
            a.c("external speaker list handler");
            switch (message.what) {
                case d.bn /* 201 */:
                    try {
                        a.c("get ready external speaker list");
                        e.this.j(new String((byte[]) message.obj, "UTF-8"));
                        return;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return;
                    }
                case d.bo /* 202 */:
                    if (e.this.q >= 5) {
                        handler = e.this.af;
                        i = d.bo;
                        break;
                    } else {
                        e.this.F();
                        return;
                    }
                case d.bp /* 203 */:
                    handler = e.this.af;
                    i = d.bp;
                    break;
                case d.bq /* 204 */:
                    handler = e.this.af;
                    i = d.bq;
                    break;
                case d.br /* 205 */:
                    handler = e.this.af;
                    i = d.br;
                    break;
                default:
                    super.handleMessage(message);
                    return;
            }
            e.this.a(handler.obtainMessage(i));
        }
    };
    public CountDownTimer ag = new CountDownTimer(15000, 16000) { // from class: com.lge.media.launcher.control.common.e.22
        @Override // android.os.CountDownTimer
        public void onFinish() {
            a.c("GUI sync timer finish");
            e.this.ac.sendMessage(e.this.ac.obtainMessage(24));
            e.this.j = false;
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }
    };
    public CountDownTimer ah = new CountDownTimer(15000, 16000) { // from class: com.lge.media.launcher.control.common.e.24
        @Override // android.os.CountDownTimer
        public void onFinish() {
            a.c("CP list timer finish");
            e.this.ae.sendMessage(e.this.ae.obtainMessage(104));
            e.this.m = true;
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }
    };
    public CountDownTimer ai = new CountDownTimer(60000, 66000) { // from class: com.lge.media.launcher.control.common.e.25
        @Override // android.os.CountDownTimer
        public void onFinish() {
            a.c("CP loading timer finish");
            e.this.ae.sendMessage(e.this.ae.obtainMessage(108));
            e.this.o = true;
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }
    };
    public CountDownTimer aj = new CountDownTimer(15000, 16000) { // from class: com.lge.media.launcher.control.common.e.26
        @Override // android.os.CountDownTimer
        public void onFinish() {
            a.c("CP list timer finish");
            e.this.af.sendMessage(e.this.af.obtainMessage(d.bp));
            e.this.p = true;
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }
    };
    public CountDownTimer ak = new CountDownTimer(15000, 16000) { // from class: com.lge.media.launcher.control.common.e.27
        @Override // android.os.CountDownTimer
        public void onFinish() {
            a.c("Command timer finish");
            e.this.ac.sendMessage(e.this.ac.obtainMessage(70));
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }
    };
    public CountDownTimer al = new CountDownTimer(30000, 31000) { // from class: com.lge.media.launcher.control.common.e.28
        @Override // android.os.CountDownTimer
        public void onFinish() {
            a.c("Music ID timer finish");
            e.this.Z.sendMessage(e.this.Z.obtainMessage(1));
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }
    };
    public CountDownTimer am = new CountDownTimer(30000, 31000) { // from class: com.lge.media.launcher.control.common.e.29
        @Override // android.os.CountDownTimer
        public void onFinish() {
            a.c("SP timer finish");
            e eVar = e.this;
            eVar.m = true;
            e.this.a(eVar.ad.obtainMessage(16));
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }
    };
    public CountDownTimer an = new CountDownTimer(1000, 1500) { // from class: com.lge.media.launcher.control.common.e.30
        @Override // android.os.CountDownTimer
        public void onFinish() {
            a.c("Btn change timer finish");
            e.this.a(e.this.ad.obtainMessage(19));
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }
    };

    protected e() {
    }

    private void M() {
        ar.getSharedPreferences(MainAct.C, 0).edit().clear().commit();
    }

    private String N() {
        return ar.getSharedPreferences(d.d, 0).getString(d.f, null);
    }

    private void O() {
        a.a("addDummyDevices");
        try {
            this.aD.add(new com.lge.UDAP.a.c(new DatagramPacket(a(71, "av.bp.15y").array(), 71, InetAddress.getLocalHost(), 0)));
            this.aD.add(new com.lge.UDAP.a.c(new DatagramPacket(a(72, "av.bh.15y").array(), 72, InetAddress.getLocalHost(), 0)));
            this.aD.add(new com.lge.UDAP.a.c(new DatagramPacket(a(72, "av.nb.15y").array(), 72, InetAddress.getLocalHost(), 0)));
            a.a("devices size:" + this.aD.size());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static e a(Context context, Handler handler) {
        ar = context;
        aq = handler;
        return ao;
    }

    public static String a(String str, String str2) {
        String hexString = Integer.toHexString(Integer.parseInt(str2.substring(str2.lastIndexOf(".") + 1, str2.length())));
        return str.substring(3, str.length()) + "_" + hexString;
    }

    private ByteBuffer a(int i, String str) {
        a.b("start create data buffer.");
        ByteBuffer allocate = ByteBuffer.allocate(i);
        allocate.putInt(0);
        allocate.put((byte) 1);
        allocate.putInt(62);
        try {
            allocate.put("00:00:00:00:00:00".getBytes("UTF-8"));
            allocate.put("000000000000".getBytes("UTF-8"));
            allocate.putInt(8);
            allocate.put("_lg_roap".getBytes("UTF-8"));
            allocate.putInt(str.length());
            allocate.put(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        allocate.put((byte) 0);
        allocate.put((byte) 1);
        allocate.put((byte) 1);
        allocate.put((byte) 1);
        allocate.putInt(0);
        return allocate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Message message) {
        Handler handler = aq;
        if (handler != null) {
            aq.sendMessage(handler.obtainMessage(message.what, message.obj));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(byte[] bArr) {
        this.aA.g = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        if (this.aA.g == null) {
            this.aA.g = BitmapFactory.decodeResource(ar.getResources(), b.g.bg_n_frame);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] a(InputStream inputStream) {
        try {
            byte[] bArr = new byte[inputStream.available()];
            inputStream.read(bArr);
            inputStream.close();
            return bArr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void b(Context context) {
        a.a("Updata ring status");
        E = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("toggle_preference_AutoPause_effect", true);
        a.c("Auto pause status is " + E);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(b.c cVar) {
        f.d();
        if (this.aw) {
            return;
        }
        a.c("Sending down keyCode.. " + cVar);
        this.az.b.a(b.a.DOWN, cVar);
    }

    private void e(b.c cVar) {
        if (this.aw) {
            return;
        }
        a.c("Sending up keyCode.. " + cVar);
        this.az.b.a(b.a.UP, cVar);
    }

    public static String f(String str) {
        Context context;
        int i;
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        if (lowerCase.contains(d.A)) {
            context = ar;
            i = b.m.lg_bluray_player;
        } else if (lowerCase.contains(d.B)) {
            context = ar;
            i = b.m.lg_bluray_home_theater;
        } else if (lowerCase.contains(d.C)) {
            context = ar;
            i = b.m.lg_bluray_recorder;
        } else if (lowerCase.contains(d.D)) {
            context = ar;
            i = b.m.lg_smart_tv_upgrader;
        } else if (lowerCase.contains(d.F)) {
            context = ar;
            i = b.m.lg_bluray_sound_bar;
        } else if (!lowerCase.contains(d.G)) {
            return null;
        } else {
            context = ar;
            i = b.m.lg_bluray_sound_plate;
        }
        return context.getString(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.lge.media.launcher.control.common.e$35] */
    public void g(final String str) {
        new Thread() { // from class: com.lge.media.launcher.control.common.e.35
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                if (e.this.aA != null) {
                    e.this.aA.a(str);
                    if (!e.this.aB || e.this.aw) {
                        if (!e.this.aw) {
                            e.this.Z.sendMessage(e.this.aA.g == null ? e.this.Z.obtainMessage(3) : e.this.Z.obtainMessage(2, null));
                        }
                    } else if (e.this.aA.e == null) {
                        a.b("coverArt image request starts.");
                        e.this.az.a.h();
                    }
                    if (e.this.aB && e.this.aw) {
                        InputStream openRawResource = e.ar.getResources().openRawResource(b.l.default_disc_info_thumb);
                        byte[] a2 = e.this.a(openRawResource);
                        if (a2 != null) {
                            e.this.Z.sendMessage(e.this.Z.obtainMessage(2, a2));
                        }
                        try {
                            openRawResource.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.lge.media.launcher.control.common.e$7] */
    public void h(final String str) {
        new Thread() { // from class: com.lge.media.launcher.control.common.e.7
            /* JADX WARN: Code restructure failed: missing block: B:14:0x006e, code lost:
                if (r0.I != null) goto L9;
             */
            /* JADX WARN: Code restructure failed: missing block: B:15:0x0070, code lost:
                r4.b.I.setPlaybackRate(r4.b.ax);
                r0 = r4.b;
                r0.y = true;
                r0.G = 0;
             */
            /* JADX WARN: Code restructure failed: missing block: B:9:0x0047, code lost:
                if (r0.I != null) goto L9;
             */
            @Override // java.lang.Thread, java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    r4 = this;
                    com.lge.media.launcher.control.common.e r0 = com.lge.media.launcher.control.common.e.this
                    com.lge.media.launcher.syncstatus.k r0 = r0.r
                    if (r0 == 0) goto L94
                    com.lge.media.launcher.control.common.e r0 = com.lge.media.launcher.control.common.e.this
                    com.lge.media.launcher.syncstatus.k r0 = r0.r
                    com.lge.media.launcher.control.common.e r1 = com.lge.media.launcher.control.common.e.this
                    java.lang.String r1 = r1.e()
                    java.lang.String r2 = r2
                    r0.a(r1, r2)
                    com.lge.media.launcher.control.common.e r0 = com.lge.media.launcher.control.common.e.this
                    com.lge.media.launcher.syncstatus.k r1 = r0.r
                    java.lang.String r1 = r1.d
                    boolean r0 = com.lge.media.launcher.control.common.e.b(r0, r1)
                    r1 = 1
                    r2 = 0
                    if (r0 == 0) goto L4a
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r3 = "AudioTrack: This is music 1, "
                    r0.append(r3)
                    com.lge.media.launcher.control.common.e r3 = com.lge.media.launcher.control.common.e.this
                    boolean r3 = r3.D
                    r0.append(r3)
                    java.lang.String r0 = r0.toString()
                    com.lge.media.launcher.control.common.a.b(r0)
                    com.lge.media.launcher.control.common.e r0 = com.lge.media.launcher.control.common.e.this
                    boolean r0 = r0.D
                    if (r0 != 0) goto L83
                    com.lge.media.launcher.control.common.e r0 = com.lge.media.launcher.control.common.e.this
                    r0.D = r1
                    android.media.AudioTrack r0 = r0.I
                    if (r0 == 0) goto L83
                    goto L70
                L4a:
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r3 = "AudioTrack: This is music 2, "
                    r0.append(r3)
                    com.lge.media.launcher.control.common.e r3 = com.lge.media.launcher.control.common.e.this
                    boolean r3 = r3.D
                    r0.append(r3)
                    java.lang.String r0 = r0.toString()
                    com.lge.media.launcher.control.common.a.b(r0)
                    com.lge.media.launcher.control.common.e r0 = com.lge.media.launcher.control.common.e.this
                    boolean r0 = r0.D
                    if (r0 == 0) goto L83
                    com.lge.media.launcher.control.common.e r0 = com.lge.media.launcher.control.common.e.this
                    r0.D = r2
                    android.media.AudioTrack r0 = r0.I
                    if (r0 == 0) goto L83
                L70:
                    com.lge.media.launcher.control.common.e r0 = com.lge.media.launcher.control.common.e.this
                    android.media.AudioTrack r0 = r0.I
                    com.lge.media.launcher.control.common.e r3 = com.lge.media.launcher.control.common.e.this
                    int r3 = com.lge.media.launcher.control.common.e.g(r3)
                    r0.setPlaybackRate(r3)
                    com.lge.media.launcher.control.common.e r0 = com.lge.media.launcher.control.common.e.this
                    r0.y = r1
                    r0.G = r2
                L83:
                    com.lge.media.launcher.control.common.e r0 = com.lge.media.launcher.control.common.e.this
                    android.os.Handler r0 = r0.ac
                    r1 = 28
                    android.os.Message r0 = r0.obtainMessage(r1)
                    com.lge.media.launcher.control.common.e r1 = com.lge.media.launcher.control.common.e.this
                    android.os.Handler r1 = r1.ac
                    r1.sendMessage(r0)
                L94:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.control.common.e.AnonymousClass7.run():void");
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.lge.media.launcher.control.common.e$17] */
    public void i(final String str) {
        new Thread() { // from class: com.lge.media.launcher.control.common.e.17
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                if (e.this.s != null) {
                    e.this.s.a(str);
                    e.this.ae.sendMessage(e.this.ae.obtainMessage(103));
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.lge.media.launcher.control.common.e$21] */
    public void j(final String str) {
        new Thread() { // from class: com.lge.media.launcher.control.common.e.21
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                if (e.this.t != null) {
                    e.this.t.a(str);
                    e.this.af.sendMessage(e.this.af.obtainMessage(d.bq));
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k(String str) {
        return str.equalsIgnoreCase("MUSIC");
    }

    public void A() {
        a.b("Start listen play back noti");
        this.az.c.a(new a.j() { // from class: com.lge.media.launcher.control.common.e.13
            @Override // com.lge.UDAP.ROAP.a.j
            public void a(a.e eVar, String str, int i, int i2, String str2) {
                Handler handler;
                int i3;
                String K = e.this.K();
                a.c("serverIP : " + K);
                a.c("socketIP : " + str2);
                if (K == null || !K.equals(str2)) {
                    return;
                }
                e eVar2 = e.this;
                eVar2.K = str;
                eVar2.L = i;
                eVar2.M = i2;
                if (eVar == a.e.PLAYBACK_PLAY) {
                    a.c("Playback play");
                    a.b("Playback event, now title is : " + str + ", deviceMan nowTitle : " + e.this.K);
                    a.b("Playback event, CurTime " + i + ", nowCurTime : " + e.this.L);
                    a.b("Playback event, totalTime " + i2 + ", nowTotalTime : " + e.this.M);
                    handler = e.this.Y;
                    i3 = 31;
                } else if (eVar == a.e.PLAYBACK_STOP) {
                    handler = e.this.Y;
                    i3 = 32;
                } else if (eVar == a.e.PLAYBACK_PAUSE) {
                    handler = e.this.Y;
                    i3 = 33;
                } else if (eVar == a.e.PLAYBACK_FAST_FWD) {
                    handler = e.this.Y;
                    i3 = 34;
                } else if (eVar == a.e.PLAYBACK_FAST_BWD) {
                    handler = e.this.Y;
                    i3 = 35;
                } else if (eVar == a.e.PLAYBACK_SLOW_FWD) {
                    handler = e.this.Y;
                    i3 = 36;
                } else if (eVar != a.e.PLAYBACK_NOW_PLAYING) {
                    return;
                } else {
                    a.c("Playback now playing");
                    a.b("Playback event, now title is : " + str + ", deviceMan nowTitle : " + e.this.K);
                    a.b("Playback event, CurTime " + i + ", nowCurTime : " + e.this.L);
                    a.b("Playback event, totalTime " + i2 + ", nowTotalTime : " + e.this.M);
                    handler = e.this.Y;
                    i3 = 37;
                }
                e.this.Y.sendMessage(handler.obtainMessage(i3));
            }
        });
    }

    public void B() {
        a.b("Start listen CP status noti");
        this.az.c.a(new a.g() { // from class: com.lge.media.launcher.control.common.e.14
            @Override // com.lge.UDAP.ROAP.a.g
            public void a(a.b bVar) {
                Handler handler;
                int i;
                if (bVar == a.b.CP_LOADING_START) {
                    if (!e.this.o) {
                        return;
                    }
                    e eVar = e.this;
                    eVar.o = false;
                    handler = eVar.ae;
                    i = 105;
                } else if (bVar == a.b.CP_LOADING_END) {
                    e eVar2 = e.this;
                    eVar2.o = true;
                    handler = eVar2.ae;
                    i = 106;
                } else if (bVar != a.b.CP_LIST_CHANGE) {
                    return;
                } else {
                    e eVar3 = e.this;
                    eVar3.Q = true;
                    handler = eVar3.ae;
                    i = 107;
                }
                e.this.ae.sendMessage(handler.obtainMessage(i));
            }
        });
    }

    public void C() {
        a.c("requestCPList");
        a.a("Timer start");
        if (this.aw) {
            this.ah.start();
            a.c(d.Q);
            this.s = new com.lge.media.launcher.cplist.c();
            InputStream openRawResource = ar.getResources().openRawResource(b.l.cplist_sample);
            byte[] a2 = a(openRawResource);
            if (a2 != null) {
                this.ae.sendMessage(this.ae.obtainMessage(101, a2));
            }
            try {
                openRawResource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (D() != null && !this.Q) {
            this.ae.sendMessage(this.ae.obtainMessage(103));
        } else {
            this.ah.start();
            if (this.m) {
                try {
                    this.az.a.a(new a.t() { // from class: com.lge.media.launcher.control.common.e.15
                        @Override // com.lge.UDAP.ROAP.a.t
                        public void a(d.b bVar, byte[] bArr) {
                            Message obtainMessage;
                            a.a("Receivce response CPList");
                            a.c("Timer stop");
                            e.this.ah.cancel();
                            e eVar = e.this;
                            eVar.m = true;
                            eVar.B = false;
                            if (bVar == d.b.CP_LIST_OK) {
                                a.b("CPList OK");
                                e eVar2 = e.this;
                                eVar2.n = 0;
                                eVar2.s = new com.lge.media.launcher.cplist.c();
                                obtainMessage = e.this.ae.obtainMessage(101, bArr);
                            } else if (bVar != d.b.CP_LIST_FAIL) {
                                return;
                            } else {
                                a.b("CPList fail");
                                e.this.n++;
                                obtainMessage = e.this.ae.obtainMessage(102);
                            }
                            e.this.ae.sendMessage(obtainMessage);
                        }
                    });
                    this.m = false;
                    this.az.a.p();
                } catch (NullPointerException unused) {
                    this.s = new com.lge.media.launcher.cplist.c();
                }
            }
        }
    }

    public com.lge.media.launcher.cplist.c D() {
        return this.s;
    }

    public void E() {
        a.b("Start listen External Speaker status noti");
        this.az.c.a(new a.h() { // from class: com.lge.media.launcher.control.common.e.18
            @Override // com.lge.UDAP.ROAP.a.h
            public void a(a.c cVar) {
                if (cVar != a.c.EXTSPK_UNKNOWN && cVar == a.c.EXTSPK_LIST_CHANGE) {
                    e.this.af.sendMessage(e.this.af.obtainMessage(d.br));
                }
            }
        });
    }

    public void F() {
        a.c("requestExtSpkList");
        a.a("Timer start");
        if (this.aw) {
            return;
        }
        this.aj.start();
        if (this.p) {
            try {
                this.az.a.a(new a.y() { // from class: com.lge.media.launcher.control.common.e.19
                    @Override // com.lge.UDAP.ROAP.a.y
                    public void a(d.i iVar, byte[] bArr) {
                        Message obtainMessage;
                        a.a("Receivce response ExtraSpeakerList");
                        e.this.t = new com.lge.media.launcher.a.a();
                        e.this.aj.cancel();
                        e eVar = e.this;
                        eVar.p = true;
                        eVar.B = false;
                        if (iVar == d.i.EXTSPK_LIST_OK) {
                            a.b("ExtraSpeakerList OK");
                            e eVar2 = e.this;
                            eVar2.q = 0;
                            obtainMessage = eVar2.af.obtainMessage(d.bn, bArr);
                        } else if (iVar != d.i.EXTSPK_LIST_FAIL) {
                            return;
                        } else {
                            a.b("ExtraSpeakerList fail");
                            e.this.q++;
                            obtainMessage = e.this.af.obtainMessage(d.bo);
                        }
                        e.this.af.sendMessage(obtainMessage);
                    }
                });
                this.p = false;
                this.az.a.q();
            } catch (NullPointerException unused) {
                this.t = new com.lge.media.launcher.a.a();
            }
        }
    }

    public com.lge.media.launcher.a.a G() {
        return this.t;
    }

    public void H() {
        try {
            String[] split = ar.getSharedPreferences(d.d, 0).getString(d.g, null).split("&");
            this.aC.clear();
            if (split.length > 1) {
                for (int i = 0; i < split.length; i += 3) {
                    this.aC.add(new com.lge.media.launcher.ui.a(split[i], split[i + 1], split[i + 2]));
                }
            }
        } catch (NullPointerException unused) {
            this.aC.clear();
        }
    }

    public void I() {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<com.lge.media.launcher.ui.a> it = this.aC.iterator();
        while (it.hasNext()) {
            com.lge.media.launcher.ui.a next = it.next();
            stringBuffer.append(next.c());
            stringBuffer.append("&");
            stringBuffer.append(next.d());
            stringBuffer.append("&");
            stringBuffer.append(next.e());
            stringBuffer.append("&");
        }
        SharedPreferences.Editor edit = ar.getSharedPreferences(d.d, 0).edit();
        edit.putString(d.g, stringBuffer.toString());
        edit.commit();
    }

    public ArrayList<com.lge.media.launcher.ui.a> J() {
        return this.aC;
    }

    public String K() {
        a.b("getSelectedDeviceIP " + b().size() + " " + b().toString());
        if (b().size() == 0 || this.au == -1 || b().size() <= d()) {
            return null;
        }
        return b().get(d()).k();
    }

    public int a() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) ar).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }

    public String a(int i) {
        if (i >= 0) {
            return this.aD.get(i).d();
        }
        return null;
    }

    public String a(String str, int i, int i2) {
        if (i > i2) {
            return str.substring(0, i2) + "..";
        }
        return str;
    }

    public void a(Context context) {
        a.a("init ring status");
        b(context);
        a.c("Auto pause status is " + E);
    }

    public void a(b.c cVar) {
        f.d();
        if (this.aw) {
            return;
        }
        a.c("Sending keyCode.. " + cVar);
        this.az.b.a(b.a.DOWN, cVar);
        this.az.b.a(b.a.UP, cVar);
    }

    public void a(String str) {
        if (str != null) {
            this.ap = str;
        }
    }

    public void a(String str, String str2, String str3) {
        boolean z;
        Iterator<com.lge.media.launcher.ui.a> it = this.aC.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            com.lge.media.launcher.ui.a next = it.next();
            if (next.d().equals(str2)) {
                a.b("exist mac address. " + str2);
                if (!next.e().equals(str3)) {
                    a.b("exist mac address. but diffrent host ip. change host ip");
                    next.c(str3);
                }
                z = true;
            }
        }
        if (z) {
            return;
        }
        this.aC.add(new com.lge.media.launcher.ui.a(str, str2, str3));
    }

    public void a(boolean z) {
        a.c("Timer start");
        this.al.start();
        if (this.aw) {
            this.aB = z;
            this.aA = new com.lge.media.launcher.contents.c();
            InputStream openRawResource = ar.getResources().openRawResource(b.l.default_disc_info_data);
            byte[] a2 = a(openRawResource);
            if (a2 != null) {
                this.Z.sendMessage(this.Z.obtainMessage(0, a2));
            }
            try {
                openRawResource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (this.k) {
        } else {
            this.k = true;
            a.c("Sending request disc or BGM information..");
            this.az.a.a(new a.v() { // from class: com.lge.media.launcher.control.common.e.34
                @Override // com.lge.UDAP.ROAP.a.v
                public void a(d.j jVar, d.e eVar, d.EnumC0056d enumC0056d, byte[] bArr) {
                    e eVar2;
                    com.lge.media.launcher.contents.c cVar;
                    a.a("Receivce response MusicID info");
                    a.c("Timer stop");
                    e.this.al.cancel();
                    e.this.az.a.e();
                    Message obtainMessage = e.this.Z.obtainMessage();
                    if (jVar == d.j.INFO_OK) {
                        a.c("Getting disc information ok");
                        if (eVar == d.e.BGM_INFO) {
                            a.c("it's BGM info");
                            eVar2 = e.this;
                            cVar = new com.lge.media.launcher.contents.c();
                        } else {
                            a.c("it's Disc info");
                            if (enumC0056d == d.EnumC0056d.TEXT_DATA) {
                                eVar2 = e.this;
                                cVar = new com.lge.media.launcher.contents.c();
                            } else if (enumC0056d == d.EnumC0056d.THUMBNAIL_DATA) {
                                obtainMessage = e.this.Z.obtainMessage(2, bArr);
                            }
                        }
                        eVar2.aA = cVar;
                        obtainMessage = e.this.Z.obtainMessage(0, bArr);
                    } else if (jVar == d.j.INFO_FAIL) {
                        a.a("Getting information has failed");
                        obtainMessage = e.this.Z.obtainMessage(7);
                    } else {
                        if (enumC0056d == d.EnumC0056d.TEXT_DATA) {
                            a.a("Getting disc information has failed");
                            if (jVar == d.j.INFO_INVALID_STATUS) {
                                obtainMessage = e.this.Z.obtainMessage(6);
                            }
                        }
                        if (enumC0056d == d.EnumC0056d.THUMBNAIL_DATA) {
                            obtainMessage = e.this.Z.obtainMessage(3);
                            a.a("Getting disc image has failed");
                        }
                    }
                    e.this.Z.sendMessage(obtainMessage);
                }
            });
            this.aB = z;
            if (z) {
                a.b("Disc information requested.");
                this.az.a.g();
                return;
            }
            a.b("BGM information requested.");
            this.az.a.d();
        }
    }

    public ArrayList<com.lge.UDAP.a.c> b() {
        return this.aD;
    }

    public void b(int i) {
        this.au = i;
    }

    public void b(b.c cVar) {
        f.d();
        if (this.aw) {
            return;
        }
        Handler handler = this.aE;
        handler.sendMessage(handler.obtainMessage(0, cVar));
    }

    public void b(String str) {
        a.b("setCurDevicePos");
        for (int i = 0; i < this.aD.size(); i++) {
            if (str != null && str.equals(this.aD.get(i).k())) {
                a.b("setCurDevicePos find. pos = " + i);
                this.au = i;
            }
        }
    }

    public int c() {
        return this.aD.size();
    }

    public void c(int i) {
        this.av = i;
    }

    public void c(b.c cVar) {
        if (this.aw) {
            return;
        }
        e(cVar);
        this.aE.removeMessages(0);
    }

    public void c(String str) {
        this.c = str;
    }

    public int d() {
        return this.au;
    }

    public void d(int i) {
        if (!a(i).equals(this.as)) {
            M();
        }
        this.as = a(i);
        this.at = f(this.aD.get(i).g()) + " " + a(this.aD.get(i).g(), this.aD.get(i).k());
        SharedPreferences.Editor edit = ar.getSharedPreferences(d.d, 0).edit();
        edit.putString(d.e, this.as);
        edit.putString(d.f, this.at);
        edit.commit();
    }

    public void d(String str) {
        this.b = str;
    }

    public String e() {
        if (this.au < 0 || this.aD.size() == 0) {
            return null;
        }
        return this.aD.get(this.au).g();
    }

    public void e(String str) {
        a.c("start scanDevices - mac");
        this.as = str;
        a.a("Last Mac:" + this.as);
        if (this.aw) {
            this.Y.sendMessage(this.Y.obtainMessage(6));
            return;
        }
        if (this.aD.size() > 0) {
            this.aD.clear();
        }
        if (this.ap == null) {
            a.b("mac address is null");
            return;
        }
        a.a("phoneMacAddress address:" + this.ap);
        com.lge.UDAP.a.a aVar = new com.lge.UDAP.a.a(this.ap, "_lg_roap", "android", (byte) 1);
        aVar.a(new a.InterfaceC0064a() { // from class: com.lge.media.launcher.control.common.e.23
            @Override // com.lge.UDAP.a.a.InterfaceC0064a
            public void a(int i) {
                a.a("WOL DISCOVERY erroe code:" + i);
                Message obtainMessage = e.this.Y.obtainMessage(7);
                obtainMessage.obj = i == 0 ? ", and Discovery Time Out..." : "error !!";
                e.this.Y.sendMessage(obtainMessage);
            }

            @Override // com.lge.UDAP.a.a.InterfaceC0064a
            public void a(com.lge.UDAP.a.c cVar) {
                a.b("data receive");
                a.b("CRC32:" + cVar.a());
                a.b("ver.:" + ((int) cVar.c()));
                a.b("mac:" + cVar.d());
                a.b("uuid:" + cVar.e());
                a.b("protocol name:" + cVar.f());
                a.b("model name:" + cVar.g());
                a.b("Auth.:" + ((int) cVar.h()));
                a.b("roapType.:" + ((int) cVar.i()));
                ArrayList<c.a> j = cVar.j();
                a.b("number port:" + j.size());
                Iterator<c.a> it = j.iterator();
                while (it.hasNext()) {
                    c.a next = it.next();
                    a.b("port type:" + ((int) next.a()) + ", port:" + next.b());
                }
                a.b("server ip:" + cVar.k());
                Message obtainMessage = e.this.Y.obtainMessage(6);
                obtainMessage.obj = cVar;
                if (cVar.g().toLowerCase(Locale.ENGLISH).contains(d.E)) {
                    return;
                }
                e.this.Y.sendMessage(obtainMessage);
            }
        });
        a.c("start result:" + aVar.a(this.b));
    }

    public boolean e(int i) {
        return this.as != null && i < this.aD.size() && this.as.equals(this.aD.get(i).d());
    }

    public int f() {
        return this.av;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x009b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void f(int r4) {
        /*
            r3 = this;
            android.content.Intent r0 = new android.content.Intent
            android.content.Context r1 = com.lge.media.launcher.control.common.e.ar
            java.lang.Class<com.lge.media.launcher.control.common.MainAct> r2 = com.lge.media.launcher.control.common.MainAct.class
            r0.<init>(r1, r2)
            r3.b(r4)
            java.lang.String r4 = r3.e()
            java.util.Locale r1 = java.util.Locale.ENGLISH
            java.lang.String r4 = r4.toLowerCase(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Cur Device Name: "
            r1.append(r2)
            java.lang.String r2 = r3.e()
            r1.append(r2)
            java.lang.String r2 = "Demo? :"
            r1.append(r2)
            boolean r2 = r3.aw
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.lge.media.launcher.control.common.a.b(r1)
            java.lang.String r1 = "bp"
            boolean r1 = r4.contains(r1)
            if (r1 == 0) goto L45
            r4 = 0
        L41:
            r3.c(r4)
            goto L6b
        L45:
            java.lang.String r1 = "bh"
            boolean r1 = r4.contains(r1)
            if (r1 != 0) goto L69
            java.lang.String r1 = "nb"
            boolean r1 = r4.contains(r1)
            if (r1 != 0) goto L69
            java.lang.String r1 = "lb"
            boolean r4 = r4.contains(r1)
            if (r4 == 0) goto L5e
            goto L69
        L5e:
            android.os.Handler r4 = com.lge.media.launcher.control.common.e.aq
            r1 = 4
            android.os.Message r4 = r4.obtainMessage(r1)
            r3.a(r4)
            goto L6b
        L69:
            r4 = 1
            goto L41
        L6b:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r1 = "DeviceM : "
            r4.append(r1)
            boolean r1 = r3.aw
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            com.lge.media.launcher.control.common.a.a(r4)
            boolean r4 = r3.aw
            java.lang.String r1 = "isDemoMode"
            r0.putExtra(r1, r4)
            r4 = 67108864(0x4000000, float:1.5046328E-36)
            r0.addFlags(r4)
            android.content.Context r4 = com.lge.media.launcher.control.common.e.ar
            boolean r1 = r4 instanceof android.app.Activity
            if (r1 == 0) goto L9b
            android.app.Activity r4 = (android.app.Activity) r4
            r1 = 100
            r4.startActivityForResult(r0, r1)
            goto L9e
        L9b:
            r4.startActivity(r0)
        L9e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.control.common.e.f(int):void");
    }

    public String g() {
        return ar.getSharedPreferences(d.d, 0).getString(d.e, null);
    }

    public void g(int i) {
        com.lge.UDAP.a.c cVar;
        StringBuilder sb;
        if (this.A) {
            com.lge.UDAP.a.c cVar2 = new com.lge.UDAP.a.c();
            cVar2.a(c.b.ROAP_TYPE_PROTOBUFFER);
            cVar2.a(this.N);
            cVar2.getClass();
            c.a aVar = new c.a(this.P, this.O);
            ArrayList<c.a> arrayList = new ArrayList<>();
            arrayList.add(aVar);
            cVar2.a(arrayList);
            b.a aVar2 = new b.a(cVar2);
            try {
                a.a("roapMan create");
                this.az = new com.lge.UDAP.ROAP.b(aVar2);
            } catch (IOException e) {
                e = e;
                e.printStackTrace();
                sb = new StringBuilder();
                sb.append("roapMan create error:");
                sb.append(e);
                a.a(sb.toString());
                this.az.a();
            }
        } else if (this.aD.size() <= 0 || (cVar = this.aD.get(i)) == null) {
            return;
        } else {
            a.a("ResParam is not null");
            cVar.a(c.b.ROAP_TYPE_PROTOBUFFER);
            try {
                this.az = new com.lge.UDAP.ROAP.b(new b.a(cVar));
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                sb = new StringBuilder();
                sb.append("roapMan create error:");
                sb.append(e);
                a.a(sb.toString());
                this.az.a();
            }
        }
        this.az.a();
    }

    public void h() {
        a.c("start scanDevices");
        this.as = g();
        this.at = N();
        a.a("Last Mac:" + this.as);
        a.a("devices.size:" + this.aD.size());
        if (this.aw) {
            this.Y.sendMessage(this.Y.obtainMessage(0));
            return;
        }
        if (this.aD.size() > 0) {
            this.aD.clear();
        }
        if (this.ap == null) {
            a.b("mac address is null");
            return;
        }
        a.a("phoneMacAddress:" + this.ap);
        com.lge.UDAP.a.a aVar = new com.lge.UDAP.a.a(this.ap, "_lg_roap", "android", (byte) 1);
        aVar.a(new a.InterfaceC0064a() { // from class: com.lge.media.launcher.control.common.e.12
            @Override // com.lge.UDAP.a.a.InterfaceC0064a
            public void a(int i) {
                a.a("erroe code:" + i);
                Message obtainMessage = e.this.Y.obtainMessage(1);
                obtainMessage.obj = i == 0 ? ", and Discovery Time Out..." : "error !!";
                e.this.Y.sendMessage(obtainMessage);
            }

            @Override // com.lge.UDAP.a.a.InterfaceC0064a
            public void a(com.lge.UDAP.a.c cVar) {
                a.b("data receive");
                a.b("CRC32:" + cVar.a());
                a.b("ver.:" + ((int) cVar.c()));
                a.b("mac:" + cVar.d());
                a.b("uuid:" + cVar.e());
                a.b("protocol name:" + cVar.f());
                a.b("model name:" + cVar.g());
                a.b("Auth.:" + ((int) cVar.h()));
                a.b("roapType.:" + ((int) cVar.i()));
                ArrayList<c.a> j = cVar.j();
                a.b("number port:" + j.size());
                Iterator<c.a> it = j.iterator();
                while (it.hasNext()) {
                    c.a next = it.next();
                    a.b("port type:" + ((int) next.a()) + ", port:" + next.b());
                }
                a.b("server ip:" + cVar.k());
                Message obtainMessage = e.this.Y.obtainMessage(0);
                obtainMessage.obj = cVar;
                if (cVar.g().toLowerCase(Locale.ENGLISH).contains(d.E)) {
                    return;
                }
                e.this.Y.sendMessage(obtainMessage);
            }
        });
        a.c("start result:" + aVar.a(this.b));
    }

    public void h(int i) {
        com.lge.UDAP.a.c cVar;
        if (this.A || (cVar = this.aD.get(i)) == null) {
            return;
        }
        a.a("ResParam is not null");
        cVar.a(c.b.ROAP_TYPE_PROTOBUFFER);
        this.az.a(new b.a(cVar));
    }

    public void i() {
        if (this.A || this.az == null) {
            a.b("start Roapman here");
            g(this.au);
        } else {
            a.b("change Roapman here");
            h(this.au);
        }
        m();
    }

    public void i(int i) {
        a.a(" set page count = " + i);
        this.F = i;
    }

    public void j() {
        this.aw = true;
        this.aD.clear();
        O();
        f(0);
    }

    public void j(int i) {
        this.ax = i;
    }

    public void k() {
        this.aw = false;
    }

    public void l() {
        if (this.az != null) {
            a.a("stopping roapman here..");
            this.az.b();
            this.az = null;
        }
    }

    public void m() {
        this.az.a.a(new a.u() { // from class: com.lge.media.launcher.control.common.e.31
            @Override // com.lge.UDAP.ROAP.a.u
            public void a(d.c cVar, com.lge.UDAP.ROAP.e.a aVar) {
                Message obtainMessage = e.this.Y.obtainMessage();
                if (cVar != d.c.CAP_OK || aVar == null) {
                    obtainMessage.what = 3;
                } else {
                    obtainMessage.obj = aVar;
                    obtainMessage.what = 2;
                    a.b("ScanwithSkip?" + aVar.p());
                    a.b("PlaywithPause?" + aVar.q());
                }
                e.this.Y.sendMessage(obtainMessage);
            }
        });
        this.az.a.c();
    }

    public com.lge.UDAP.ROAP.e.a n() {
        com.lge.UDAP.ROAP.e.a aVar = this.ay;
        if (aVar != null) {
            return aVar;
        }
        return null;
    }

    public int o() {
        com.lge.UDAP.ROAP.e.a aVar = this.ay;
        if (aVar != null) {
            if (aVar.m()) {
                return 0;
            }
            if (this.ay.n()) {
                return 1;
            }
        }
        return 2;
    }

    public com.lge.UDAP.ROAP.b p() {
        return this.az;
    }

    public void q() {
        a.b("Start listen BGM info noti");
        this.az.c.a(new a.f() { // from class: com.lge.media.launcher.control.common.e.33
            @Override // com.lge.UDAP.ROAP.a.f
            public void a(a.EnumC0054a enumC0054a) {
                Handler handler;
                int i;
                if (enumC0054a == a.EnumC0054a.BGM_INFO_OK) {
                    a.c("BGM info msg is OK");
                    handler = e.this.Y;
                    i = 60;
                } else if (enumC0054a == a.EnumC0054a.BGM_INFO_FAIL) {
                    a.c("BGM info msg is Fail");
                    handler = e.this.Y;
                    i = 61;
                } else if (enumC0054a == a.EnumC0054a.BGM_INFO_LOADING) {
                    a.c("BGM info msg is roading");
                    handler = e.this.Y;
                    i = 62;
                } else if (enumC0054a != a.EnumC0054a.BGM_INFO_CANCEL) {
                    return;
                } else {
                    a.c("BGM info msg is cancel");
                    handler = e.this.Y;
                    i = 63;
                }
                e.this.Y.sendMessage(handler.obtainMessage(i));
            }
        });
    }

    public com.lge.media.launcher.contents.c r() {
        return this.aA;
    }

    public com.lge.media.launcher.syncstatus.k s() {
        return this.r;
    }

    public void t() {
        a.a("start listen text input noti");
        this.az.c.a(new a.n() { // from class: com.lge.media.launcher.control.common.e.2
            @Override // com.lge.UDAP.ROAP.a.n
            public void a(a.i iVar, String str, int i) {
                if (iVar != a.i.VIEW_TEXTINPUT) {
                    if (iVar == a.i.VIEW_TOUCHPAD) {
                        a.a("Touchpad evnet!");
                        return;
                    }
                    return;
                }
                a.a("Text Input evnet!");
                e.this.h = str;
                a.b("phoneinputmsg : " + e.this.i);
                a.b("setinputmsg : " + str);
                if (!e.this.e) {
                    a.a("Text Input view change");
                    Intent intent = new Intent(e.ar, TextInputAct.class);
                    intent.addFlags(67108864);
                    e.ar.startActivity(intent);
                    e.this.e = true;
                    return;
                }
                a.a("Text sending..");
                if (e.this.h.equals(e.this.i) || e.this.h.equals(com.lge.media.launcher.a.d)) {
                    return;
                }
                a.b("not same textinput info");
                e.this.aa.sendMessage(e.this.aa.obtainMessage(40));
            }
        });
    }

    public void u() {
        a.b("stop listen text input noti");
        this.az.c.a((a.n) null);
    }

    public void v() {
        a.b("start listen sync status noti");
        this.az.c.a(new a.l() { // from class: com.lge.media.launcher.control.common.e.4
            @Override // com.lge.UDAP.ROAP.a.l
            public void a(a.g gVar) {
                Handler handler;
                int i;
                if (gVar == a.g.SS_VIEW_CHANGE) {
                    a.b("event msg SYNC_SS_VIEW_CHANGE");
                    e eVar = e.this;
                    eVar.j = false;
                    handler = eVar.Y;
                    i = 25;
                } else if (gVar == a.g.SS_ENABLED) {
                    a.b("event msg SYNC_SYNC_SS_ENABLED");
                    handler = e.this.Y;
                    i = 26;
                } else if (gVar != a.g.SS_UNKNOWN) {
                    return;
                } else {
                    a.b("event msg SYNC_SYNC_SS_UNKNOWN");
                    handler = e.this.Y;
                    i = 27;
                }
                e.this.Y.sendMessage(handler.obtainMessage(i));
            }
        });
    }

    public void w() {
        a.c("requestSyncStatusInformation");
        a.a("Timer start");
        this.ag.start();
        if (!this.aw) {
            if (this.j) {
                return;
            }
            this.az.a.a(new a.aa() { // from class: com.lge.media.launcher.control.common.e.6
                @Override // com.lge.UDAP.ROAP.a.aa
                public void a(d.m mVar, String str, int i) {
                    a.a("Receivce response SyncStatusInfo");
                    a.c("Timer stop");
                    e.this.ag.cancel();
                    e.this.j = false;
                    a.a("str : " + str);
                    a.a("legnth : " + i);
                    if (mVar != d.m.SYNC_STATUS_INFO_TEXT) {
                        if (mVar == d.m.SYNC_STATUS_INFO_FAIL) {
                            a.b("SyncStatusInfo Fail");
                            e.this.ac.sendMessage(e.this.ac.obtainMessage(21));
                            return;
                        }
                        return;
                    }
                    a.b("SyncStatusInfo OK");
                    e.this.h = str;
                    a.b("phoneinputmsg : " + e.this.i);
                    a.b("setinputmsg : " + e.this.h);
                    if (e.this.e) {
                        return;
                    }
                    a.a("Text Input called..");
                    Intent intent = new Intent(e.ar, TextInputAct.class);
                    intent.addFlags(67108864);
                    e.ar.startActivity(intent);
                    e.this.e = true;
                }

                @Override // com.lge.UDAP.ROAP.a.aa
                public void a(d.m mVar, byte[] bArr) {
                    Message obtainMessage;
                    a.a("Receivce response SyncStatusInfo");
                    a.c("Timer stop");
                    e.this.ag.cancel();
                    e eVar = e.this;
                    eVar.j = false;
                    eVar.B = false;
                    if (mVar == d.m.SYNC_STATUS_INFO_OK) {
                        a.b("SyncStatusInfo OK");
                        e.this.r = new com.lge.media.launcher.syncstatus.k();
                        obtainMessage = e.this.ac.obtainMessage(20, bArr);
                    } else if (mVar == d.m.SYNC_STATUS_INFO_TOUCH) {
                        a.b("SyncStatusInfo touch");
                        return;
                    } else if (mVar != d.m.SYNC_STATUS_INFO_FAIL) {
                        return;
                    } else {
                        a.b("SyncStatusInfo fail");
                        obtainMessage = e.this.ac.obtainMessage(21);
                    }
                    e.this.ac.sendMessage(obtainMessage);
                }
            });
            this.j = true;
            this.az.a.j();
            return;
        }
        a.c(d.Q);
        this.r = new com.lge.media.launcher.syncstatus.k();
        InputStream openRawResource = ar.getResources().openRawResource(b.l.sync_status_sample);
        byte[] a2 = a(openRawResource);
        if (a2 != null) {
            this.ac.sendMessage(this.ac.obtainMessage(20, a2));
        }
        try {
            openRawResource.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void x() {
        a.b("start listen sp noti");
        this.az.c.a(new a.k() { // from class: com.lge.media.launcher.control.common.e.10
            @Override // com.lge.UDAP.ROAP.a.k
            public void a(a.f fVar) {
                Handler handler;
                int i;
                if (fVar == a.f.SP_SOUND_ON) {
                    a.b("event msg SP_SOUND_ON");
                    handler = e.this.ad;
                    i = 8;
                } else if (fVar == a.f.SP_SOUND_OFF) {
                    a.b("event msg SP_SOUND_OFF");
                    handler = e.this.ad;
                    i = 9;
                } else if (fVar == a.f.SP_SAMPLERATE_CHANGE) {
                    a.b("event msg SP_SAMPLERATE_CHANGE");
                    handler = e.this.ad;
                    i = 11;
                } else if (fVar == a.f.SP_STOP) {
                    a.b("event msg SP_STOP");
                    e eVar = e.this;
                    eVar.p = true;
                    handler = eVar.ad;
                    i = 16;
                } else if (fVar == a.f.SP_NOT_SUPPORT) {
                    a.b("event msg SP_NOT_SUPPORT");
                    handler = e.this.ad;
                    i = 17;
                } else if (fVar == a.f.SP_ALIVE) {
                    a.b("event msg SP_ALIVE");
                    a.b("SPTimer start");
                    e.this.am.cancel();
                    e.this.am.start();
                    return;
                } else if (fVar != null) {
                    return;
                } else {
                    a.b("event msg SP_STATUS_FAIL");
                    handler = e.this.ad;
                    i = 10;
                }
                e.this.ad.sendMessage(handler.obtainMessage(i));
            }
        });
    }

    public void y() {
        a.b("sp request");
        if (this.aw) {
            return;
        }
        this.az.a.a(new a.z() { // from class: com.lge.media.launcher.control.common.e.11
            @Override // com.lge.UDAP.ROAP.a.z
            public void a(d.l lVar, int i) {
                if (lVar == d.l.SP_INFO_OK) {
                    a.c("response msg SP_INFO_OK");
                    e.this.j(i);
                    e.this.ad.sendMessage(e.this.ad.obtainMessage(12));
                }
            }
        });
        this.az.a.k();
    }

    public int z() {
        return this.ax;
    }
}
