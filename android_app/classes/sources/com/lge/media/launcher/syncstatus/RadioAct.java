package com.lge.media.launcher.syncstatus;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.SoundPool;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.lge.UDAP.ROAP.a.b;
import com.lge.media.launcher.b;
import java.util.ArrayList;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class RadioAct extends Activity implements View.OnClickListener, View.OnTouchListener {
    public static final int a = 100;
    private static final int g = 0;
    private static final int h = 1;
    private static final int i = 2;
    private static final int j = 3;
    private static final int k = 4;
    private com.lge.media.launcher.ui.e A;
    private Dialog B;
    private SoundPool C;
    private NfcAdapter E;
    private PendingIntent F;
    private IntentFilter[] G;
    private Toast H;
    public com.lge.media.launcher.control.common.e b;
    private RelativeLayout l;
    private LinearLayout m;
    private TextView n;
    private Button p;
    private Button q;
    private Button r;
    private Button s;
    private Button t;
    private Button u;
    private Button v;
    private Button w;
    private Button x;
    private TextView y;
    private ArrayList<TextView> o = new ArrayList<>();
    private int[] z = {b.h.radio_info_freq, b.h.radio_info_band, b.h.radio_info_preset, b.h.radio_info_stereo, b.h.rds_info_station};
    private k D = null;
    protected boolean c = false;
    protected boolean d = false;
    public com.lge.media.launcher.sp.a e = null;
    public Handler f = new Handler() { // from class: com.lge.media.launcher.syncstatus.RadioAct.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
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
            RadioAct radioAct;
            k s;
            String str2;
            com.lge.media.launcher.control.common.a.b("radio Handler");
            int i5 = message.what;
            if (i5 == 16) {
                if (RadioAct.this.c || !RadioAct.this.b.u) {
                    return;
                }
                com.lge.media.launcher.control.common.a.c("Recived sp stop msg and sp turn off");
                RadioAct.this.b.u = false;
                RadioAct.this.b.H.a(RadioAct.this.b.u);
                RadioAct.this.g();
                return;
            }
            if (i5 != 17) {
                if (i5 == 19) {
                    RadioAct.this.p.setEnabled(true);
                    return;
                }
                if (i5 != 21) {
                    if (i5 != 25) {
                        if (i5 == 28) {
                            if (RadioAct.this.d) {
                                com.lge.media.launcher.control.common.a.b("radio view init");
                                radioAct = RadioAct.this;
                                radioAct.d = false;
                                s = radioAct.b.r;
                            } else {
                                com.lge.media.launcher.control.common.a.b("radio view not init");
                                radioAct = RadioAct.this;
                                s = radioAct.b.s();
                            }
                            radioAct.D = s;
                            if (RadioAct.this.D.a.equals("home") || RadioAct.this.D.a.equals("playback")) {
                                RadioAct.this.finish();
                                if (((PowerManager) RadioAct.this.getSystemService("power")).isScreenOn()) {
                                    RadioAct.this.onBackPressed();
                                    return;
                                }
                                return;
                            } else if (RadioAct.this.D.a.equals("radio")) {
                                RadioAct.this.f();
                                Toast toast = RadioAct.this.H;
                                toast.setText(((TextView) RadioAct.this.o.get(1)).getText().toString() + " " + ((TextView) RadioAct.this.o.get(0)).getText().toString() + " " + RadioAct.this.y.getText().toString());
                                RadioAct.this.H.show();
                                return;
                            } else {
                                return;
                            }
                        }
                        switch (i5) {
                            case 8:
                                com.lge.media.launcher.control.common.a.c("receive sp status on");
                                RadioAct.this.b.u = false;
                                RadioAct radioAct2 = RadioAct.this;
                                radioAct2.A = com.lge.media.launcher.ui.e.a(radioAct2, radioAct2.findViewById(b.h.layout_top));
                                if (!RadioAct.this.b.R) {
                                    eVar = RadioAct.this.A;
                                    z = false;
                                    i2 = b.m.sound_privacy_error;
                                    i3 = b.m.ok;
                                    i4 = 0;
                                    onClickListener = RadioAct.this.I;
                                    onClickListener2 = null;
                                    z2 = false;
                                    str = null;
                                    f = 0.0f;
                                    break;
                                } else {
                                    eVar = RadioAct.this.A;
                                    z = false;
                                    i2 = b.m.sound_privacy_error;
                                    i3 = b.m.ok;
                                    i4 = 0;
                                    onClickListener = RadioAct.this.I;
                                    onClickListener2 = null;
                                    z2 = RadioAct.this.b.R;
                                    str = RadioAct.this.b.T;
                                    f = RadioAct.this.b.U;
                                    break;
                                }
                            case 9:
                                str2 = "receive sp status off";
                                break;
                            case 10:
                                return;
                            case 11:
                                str2 = "receive sp status sample reate change";
                                break;
                            case 12:
                                com.lge.media.launcher.control.common.a.c("receive sp info ok");
                                RadioAct.this.b.H.c();
                                RadioAct.this.b.H.a();
                                return;
                            default:
                                super.handleMessage(message);
                                return;
                        }
                        com.lge.media.launcher.control.common.a.c(str2);
                        RadioAct.this.b.y();
                        return;
                    }
                    com.lge.media.launcher.control.common.a.c("Timer cancel");
                    RadioAct.this.b.ag.cancel();
                } else if (!RadioAct.this.d) {
                    RadioAct radioAct3 = RadioAct.this;
                    radioAct3.D = radioAct3.b.r;
                    RadioAct.this.f();
                    return;
                }
                RadioAct.this.b.w();
                RadioAct.this.p.setEnabled(false);
                RadioAct.this.b.an.start();
                return;
            } else if (RadioAct.this.c || !RadioAct.this.b.u) {
                return;
            } else {
                com.lge.media.launcher.control.common.a.c("Recived sp not support msg");
                RadioAct.this.b.u = false;
                RadioAct.this.b.H.a(RadioAct.this.b.u);
                RadioAct.this.g();
                RadioAct radioAct4 = RadioAct.this;
                radioAct4.A = com.lge.media.launcher.ui.e.a(radioAct4, radioAct4.findViewById(b.h.layout_top));
                if (RadioAct.this.b.R) {
                    eVar = RadioAct.this.A;
                    z = false;
                    i2 = b.m.sound_privacy_not_support;
                    i3 = b.m.ok;
                    i4 = 0;
                    onClickListener = RadioAct.this.J;
                    onClickListener2 = null;
                    z2 = RadioAct.this.b.R;
                    str = RadioAct.this.b.T;
                    f = RadioAct.this.b.U;
                } else {
                    eVar = RadioAct.this.A;
                    z = false;
                    i2 = b.m.sound_privacy_not_support;
                    i3 = b.m.ok;
                    i4 = 0;
                    onClickListener = RadioAct.this.J;
                    onClickListener2 = null;
                    z2 = false;
                    str = null;
                    f = 0.0f;
                }
            }
            eVar.a(z, i2, i3, i4, onClickListener, onClickListener2, z2, str, f);
        }
    };
    private View.OnClickListener I = new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.RadioAct.11
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.lge.media.launcher.control.common.f.d();
            RadioAct.this.A.a();
            RadioAct.this.b.u = false;
            com.lge.media.launcher.control.common.a.c("isSpOn : " + RadioAct.this.b.u);
            RadioAct.this.g();
        }
    };
    private View.OnClickListener J = new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.RadioAct.13
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.lge.media.launcher.control.common.f.d();
            RadioAct.this.A.a();
        }
    };

    private void c() {
        com.lge.media.launcher.control.common.a.b("RadioAct Initview");
        this.l = (RelativeLayout) findViewById(b.h.layout_radio);
        this.m = (LinearLayout) findViewById(b.h.progress_waiting);
        this.n = (TextView) findViewById(b.h.text_title_radio);
        TextView textView = this.n;
        textView.setContentDescription(getString(b.m.radio) + getString(b.m.label_title) + "1");
        this.p = (Button) findViewById(b.h.btn_sound_privacy);
        this.q = (Button) findViewById(b.h.btn_refresh);
        this.r = (Button) findViewById(b.h.FM_mode);
        this.s = (Button) findViewById(b.h.btn_arrow_up);
        this.t = (Button) findViewById(b.h.btn_arrow_down);
        this.u = (Button) findViewById(b.h.btn_arrow_right);
        this.v = (Button) findViewById(b.h.btn_arrow_left);
        com.lge.media.launcher.control.common.f.a(getApplicationContext(), this.C);
        this.b.a(this);
        int i2 = 0;
        while (true) {
            int[] iArr = this.z;
            if (i2 >= iArr.length) {
                break;
            }
            this.o.add((TextView) findViewById(iArr[i2]));
            i2++;
        }
        this.y = (TextView) findViewById(b.h.radio_info_band_freq);
        this.b.d = findViewById(b.h.layout_top);
        com.lge.media.launcher.control.common.e eVar = this.b;
        eVar.H = com.lge.media.launcher.sp.a.a(this, eVar);
        if (!this.c && !this.b.n().O()) {
            this.p.setVisibility(8);
        }
        g();
        this.B = new Dialog(this, 16973840);
        this.B.setContentView(b.j.sp_popup);
        this.w = (Button) this.B.findViewById(b.h.btn_popup_one);
        this.x = (Button) this.B.findViewById(b.h.btn_popup_two);
        this.w.setText(b.m.ok);
        this.x.setText(b.m.cancel);
        this.w.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.RadioAct.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RadioAct.this.b.J = true;
                com.lge.media.launcher.control.common.f.d();
                RadioAct.this.B.cancel();
                RadioAct.this.b.u = true;
                RadioAct.this.g();
                RadioAct.this.b.H.a(RadioAct.this.b.u);
            }
        });
        this.x.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.RadioAct.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RadioAct.this.b.J = false;
                com.lge.media.launcher.control.common.f.d();
                RadioAct.this.B.cancel();
                RadioAct.this.p.setBackgroundResource(b.g.btn_spk_n);
                RadioAct.this.p.setContentDescription(RadioAct.this.getString(b.m.label_private_sound));
            }
        });
        this.p.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.syncstatus.RadioAct.15
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                android.support.v4.view.a.c.a(accessibilityNodeInfo).i(RadioAct.this.n);
            }
        });
        this.q.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.syncstatus.RadioAct.16
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                android.support.v4.view.a.c.a(accessibilityNodeInfo).i(RadioAct.this.p);
            }
        });
        com.lge.media.launcher.c.a(this.n, 500);
        d();
    }

    private void d() {
        this.t.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.syncstatus.RadioAct.17
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                android.support.v4.view.a.c.a(accessibilityNodeInfo).i(RadioAct.this.s);
            }
        });
        this.v.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.syncstatus.RadioAct.18
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                android.support.v4.view.a.c.a(accessibilityNodeInfo).i(RadioAct.this.t);
            }
        });
        this.u.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.syncstatus.RadioAct.19
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                android.support.v4.view.a.c.a(accessibilityNodeInfo).i(RadioAct.this.v);
            }
        });
    }

    private void e() {
        com.lge.media.launcher.control.common.a.b("RadioAct setListener");
        this.p.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.r.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.RadioAct.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.lge.media.launcher.control.common.f.d();
                RadioAct.this.b.a(b.c.KEYCODE_BLUE);
            }
        });
        this.s.setOnTouchListener(new View.OnTouchListener() { // from class: com.lge.media.launcher.syncstatus.RadioAct.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    com.lge.media.launcher.control.common.f.d();
                    RadioAct.this.b.b(b.c.KEYCODE_UP);
                    return false;
                } else if (action != 1) {
                    return false;
                } else {
                    RadioAct.this.b.c(b.c.KEYCODE_UP);
                    return false;
                }
            }
        });
        this.t.setOnTouchListener(new View.OnTouchListener() { // from class: com.lge.media.launcher.syncstatus.RadioAct.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    com.lge.media.launcher.control.common.f.d();
                    RadioAct.this.b.b(b.c.KEYCODE_DOWN);
                    return false;
                } else if (action != 1) {
                    return false;
                } else {
                    RadioAct.this.b.c(b.c.KEYCODE_DOWN);
                    return false;
                }
            }
        });
        this.u.setOnTouchListener(new View.OnTouchListener() { // from class: com.lge.media.launcher.syncstatus.RadioAct.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    com.lge.media.launcher.control.common.f.d();
                    RadioAct.this.b.b(b.c.KEYCODE_RIGHT);
                    return false;
                } else if (action != 1) {
                    return false;
                } else {
                    RadioAct.this.b.c(b.c.KEYCODE_RIGHT);
                    return false;
                }
            }
        });
        this.v.setOnTouchListener(new View.OnTouchListener() { // from class: com.lge.media.launcher.syncstatus.RadioAct.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    com.lge.media.launcher.control.common.f.d();
                    RadioAct.this.b.b(b.c.KEYCODE_LEFT);
                    return false;
                } else if (action != 1) {
                    return false;
                } else {
                    RadioAct.this.b.c(b.c.KEYCODE_LEFT);
                    return false;
                }
            }
        });
        this.s.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.RadioAct.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.lge.media.launcher.control.common.f.d();
                RadioAct.this.b.a(b.c.KEYCODE_UP);
            }
        });
        this.t.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.RadioAct.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.lge.media.launcher.control.common.f.d();
                RadioAct.this.b.a(b.c.KEYCODE_DOWN);
            }
        });
        this.u.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.RadioAct.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.lge.media.launcher.control.common.f.d();
                RadioAct.this.b.a(b.c.KEYCODE_RIGHT);
            }
        });
        this.v.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.RadioAct.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.lge.media.launcher.control.common.f.d();
                RadioAct.this.b.a(b.c.KEYCODE_LEFT);
            }
        });
        findViewById(b.h.button_back).setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.RadioAct.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RadioAct.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        TextView textView;
        com.lge.media.launcher.control.common.a.b("Radio fillViews");
        com.lge.media.launcher.control.common.a.b("device isRbtn : " + this.b.v);
        this.m.setVisibility(4);
        this.l.setVisibility(0);
        this.o.get(0).setText(this.D.q);
        this.o.get(1).setText(this.D.r);
        this.o.get(4).setText(this.D.u);
        String str = "         ";
        if (this.D.s.contains("PR")) {
            this.o.get(2).setText(this.D.s);
        } else {
            this.o.get(2).setText("         ");
        }
        if (this.D.u.equals("STEREO")) {
            textView = this.o.get(3);
            str = this.D.t;
        } else {
            textView = this.o.get(3);
        }
        textView.setText(str);
        if (this.D.r.equals("AM")) {
            this.y.setText("kHz");
        }
        com.lge.media.launcher.control.common.a.b("what is freq ? " + this.D.q);
        com.lge.media.launcher.control.common.a.b("what is band type? " + this.D.r);
        com.lge.media.launcher.control.common.a.b("what is preset ? " + this.D.s);
        com.lge.media.launcher.control.common.a.b("what is stereo? " + this.D.t);
        com.lge.media.launcher.control.common.a.b("what is station ? " + this.D.u);
        if (this.D.r.equals("FM")) {
            this.r.setEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        Button button;
        StringBuilder sb;
        int i2;
        if (this.b.u) {
            this.p.setBackgroundResource(b.g.btn_spk_p);
            button = this.p;
            sb = new StringBuilder();
            sb.append(getString(b.m.label_output_speaker));
            sb.append(", ");
            i2 = b.m.label_on;
        } else {
            this.p.setBackgroundResource(b.g.btn_spk_n);
            button = this.p;
            sb = new StringBuilder();
            sb.append(getString(b.m.label_output_speaker));
            sb.append(", ");
            i2 = b.m.label_off;
        }
        sb.append(getString(i2));
        button.setContentDescription(sb.toString());
    }

    public void a() {
        com.lge.media.launcher.control.common.a.b("Radio hideViews");
        this.m.setVisibility(0);
        this.l.setVisibility(4);
    }

    public void b() {
        String packageName = ((ActivityManager) getSystemService("activity")).getRunningTasks(1).get(0).baseActivity.getPackageName();
        if (!packageName.equals("com.lge.media.launcher")) {
            com.lge.media.launcher.control.common.a.a("LG Remote app is not Foreground");
            if (this.b.u) {
                com.lge.media.launcher.control.common.a.a("SP sound mute");
                ((AudioManager) getSystemService("audio")).setStreamMute(3, true);
            }
        }
        if (packageName.equals("com.lge.media.launcher")) {
            com.lge.media.launcher.control.common.a.a("LG Remote app is Foreground");
            if (this.b.u) {
                com.lge.media.launcher.control.common.a.a("SP sound unmute");
                ((AudioManager) getSystemService("audio")).setStreamMute(3, false);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == 2131230722) {
            com.lge.media.launcher.control.common.f.d();
            this.b.a(b.c.KEYCODE_BLUE);
        } else if (id == 2131230839) {
            com.lge.media.launcher.control.common.f.d();
            if (this.c) {
                return;
            }
            com.lge.media.launcher.control.common.a.c("startListenSyncStatusNoti RadioAct refresh");
            this.b.w();
            a();
            this.p.setEnabled(false);
            this.b.an.start();
        } else if (id != 2131230847) {
        } else {
            com.lge.media.launcher.control.common.a.c("Sound Privacy btn push");
            com.lge.media.launcher.control.common.f.d();
            if (this.c) {
                return;
            }
            if (this.b.u) {
                com.lge.media.launcher.control.common.a.c("btnEnable");
                this.p.setEnabled(false);
                this.b.an.start();
                this.b.u = false;
            } else if (!this.b.J) {
                this.B.show();
                return;
            } else {
                com.lge.media.launcher.control.common.a.c("btnEnable");
                this.p.setEnabled(false);
                this.b.an.start();
                this.b.u = true;
            }
            g();
            this.b.H.a(this.b.u);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        com.lge.media.launcher.control.common.a.b("Radio create");
        super.onCreate(bundle);
        setContentView(b.j.radio_key_control);
        this.c = getIntent().getBooleanExtra(com.lge.media.launcher.control.common.d.Q, false);
        this.b = com.lge.media.launcher.control.common.e.a(this, this.f);
        this.d = true;
        this.b.w = true;
        c();
        e();
        this.D = this.b.r;
        if (this.c) {
            this.r.setEnabled(true);
            this.m.setVisibility(4);
            this.l.setVisibility(0);
        } else {
            this.D = this.b.r;
            f();
        }
        try {
            this.E = NfcAdapter.getDefaultAdapter(this);
            if (this.E == null) {
                com.lge.media.launcher.control.common.a.b("This device does not support NFc");
            }
            this.F = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(536870912), 0);
            this.G = new IntentFilter[]{new IntentFilter("android.nfc.action.TAG_DISCOVERED")};
        } catch (Exception unused) {
            com.lge.media.launcher.control.common.a.a("NFC Adapter exception");
        }
        this.H = Toast.makeText(getApplicationContext(), com.lge.media.launcher.a.d, 0);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.l = null;
        this.m = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.e = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.f = null;
        this.I = null;
        this.J = null;
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || this.c) {
            return false;
        }
        com.lge.media.launcher.control.common.a.b("Go to main control view");
        this.b.g = true;
        finish();
        onBackPressed();
        return false;
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        b();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        b();
        try {
            this.E.enableForegroundDispatch(this, this.F, this.G, null);
        } catch (Exception unused) {
            com.lge.media.launcher.control.common.a.a("NFC Adapter exception");
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        com.lge.media.launcher.control.common.e eVar;
        b.c cVar;
        com.lge.media.launcher.control.common.e eVar2;
        b.c cVar2;
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                return false;
            }
            switch (view.getId()) {
                case b.h.btn_arrow_down /* 2131230757 */:
                    eVar2 = this.b;
                    cVar2 = b.c.KEYCODE_DOWN;
                    break;
                case b.h.btn_arrow_enter /* 2131230758 */:
                default:
                    return false;
                case b.h.btn_arrow_left /* 2131230759 */:
                    eVar2 = this.b;
                    cVar2 = b.c.KEYCODE_LEFT;
                    break;
                case b.h.btn_arrow_right /* 2131230760 */:
                    eVar2 = this.b;
                    cVar2 = b.c.KEYCODE_RIGHT;
                    break;
                case b.h.btn_arrow_up /* 2131230761 */:
                    eVar2 = this.b;
                    cVar2 = b.c.KEYCODE_UP;
                    break;
            }
            eVar2.c(cVar2);
            return false;
        }
        switch (view.getId()) {
            case b.h.btn_arrow_down /* 2131230757 */:
                com.lge.media.launcher.control.common.f.d();
                eVar = this.b;
                cVar = b.c.KEYCODE_DOWN;
                break;
            case b.h.btn_arrow_enter /* 2131230758 */:
            default:
                return false;
            case b.h.btn_arrow_left /* 2131230759 */:
                com.lge.media.launcher.control.common.f.d();
                eVar = this.b;
                cVar = b.c.KEYCODE_LEFT;
                break;
            case b.h.btn_arrow_right /* 2131230760 */:
                com.lge.media.launcher.control.common.f.d();
                eVar = this.b;
                cVar = b.c.KEYCODE_RIGHT;
                break;
            case b.h.btn_arrow_up /* 2131230761 */:
                com.lge.media.launcher.control.common.f.d();
                eVar = this.b;
                cVar = b.c.KEYCODE_UP;
                break;
        }
        eVar.b(cVar);
        return false;
    }
}
