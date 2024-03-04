package com.lge.media.launcher.control.modelwise;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.a.c;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.lge.UDAP.ROAP.a.b;
import com.lge.media.launcher.b;
import com.lge.media.launcher.control.common.MainAct;
import com.lge.media.launcher.control.common.d;
import com.lge.media.launcher.control.common.f;
import com.lge.media.launcher.control.common.h;
import com.lge.media.launcher.cplist.e;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class b extends h {
    private Button A;
    private Button B;
    private Button C;
    private Button D;
    private Button E;
    private Button F;
    private Button G;
    private Button H;
    private Button I;
    private Button J;
    private FrameLayout K;
    private Button L;
    private ViewGroup M;
    private RelativeLayout N;
    private RelativeLayout O;
    private RelativeLayout P;
    private RelativeLayout Q;
    private LinearLayout R;
    private LinearLayout S;
    private Button T;
    LinearLayout c;
    LinearLayout d;
    int[] e;
    b.c[] f;
    private Toast h;
    private ArrayList<View> i;
    private HashMap<Integer, b.c> j;
    private FrameLayout k;
    private FrameLayout l;
    private FrameLayout m;
    private FrameLayout n;
    private LinearLayout o;
    private LinearLayout p;
    private RelativeLayout q;
    private LinearLayout r;
    private GridView s;
    private Button t;
    private Button u;
    private Button v;
    private Button w;
    private Button x;
    private Button y;
    private Button z;

    public b(Context context, ViewGroup viewGroup) {
        super(context, viewGroup);
        this.i = new ArrayList<>();
        this.j = new HashMap<>();
        this.e = new int[]{b.h.layout_main_mute, b.h.layout_main_function, b.h.layout_main_home, b.h.layout_main_info, b.h.layout_btn_back, b.h.layout_btn_disc_menu, b.h.btn_arrow_enter, b.h.btn_prev, b.h.btn_rew, b.h.btn_ff, b.h.btn_next, b.h.btn_stop, b.h.btn_play, b.h.btn_pause, b.h.btn_repeat, b.h.btn_disc_menu, b.h.btn_popup_title, b.h.btn_info, b.h.btn_red, b.h.btn_green, b.h.btn_yellow, b.h.btn_blue, b.h.btn_sound_effect, b.h.btn_speaker_level, b.h.btn_optical, b.h.btn_num_one, b.h.btn_num_two, b.h.btn_num_three, b.h.btn_num_four, b.h.btn_num_five, b.h.btn_num_six, b.h.btn_num_seven, b.h.btn_num_eight, b.h.btn_num_nine, b.h.btn_num_zero, b.h.btn_radio, b.h.btn_sound_control, b.h.btn_trick_play, b.h.btn_num_pad, b.h.btn_key_sound_control, b.h.btn_key_trick_play, b.h.btn_key_num_pad};
        this.f = new b.c[]{b.c.KEYCODE_MUTE, b.c.KEYCODE_FUNCTION, b.c.KEYCODE_HOME, b.c.KEYCODE_INFO, b.c.KEYCODE_BACK, b.c.KEYCODE_DISC_MENU, b.c.KEYCODE_ENTER, b.c.KEYCODE_SKIP_BACKWARD, b.c.KEYCODE_FAST_BACKWARD, b.c.KEYCODE_FAST_FORWARD, b.c.KEYCODE_SKIP_FORWARD, b.c.KEYCODE_STOP, b.c.KEYCODE_PLAY, b.c.KEYCODE_PAUSE, b.c.KEYCODE_REPEAT, b.c.KEYCODE_DISC_MENU, b.c.KEYCODE_POPUP, b.c.KEYCODE_INFO, b.c.KEYCODE_RED, b.c.KEYCODE_GREEN, b.c.KEYCODE_YELLOW, b.c.KEYCODE_BLUE, b.c.KEYCODE_SOUND_EFFECT, b.c.KEYCODE_SPEAKER_LEVEL, b.c.KEYCODE_OPTICAL, b.c.KEYCODE_1, b.c.KEYCODE_2, b.c.KEYCODE_3, b.c.KEYCODE_4, b.c.KEYCODE_5, b.c.KEYCODE_6, b.c.KEYCODE_7, b.c.KEYCODE_8, b.c.KEYCODE_9, b.c.KEYCODE_0, b.c.KEYCODE_TUNER};
        a(viewGroup);
        a();
        b();
    }

    private void a(View view, int i) {
        f.d();
        this.g.f.a(this.j.get(Integer.valueOf(view.getId())));
    }

    private void a(b.c cVar) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.g.getSystemService("accessibility");
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return;
        }
        this.g.f.a(cVar);
    }

    private void g() {
        this.F.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lge.media.launcher.control.modelwise.b.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                b.this.h.setText(b.m.label_red);
                b.this.h.show();
                return false;
            }
        });
        this.G.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lge.media.launcher.control.modelwise.b.9
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                b.this.h.setText(b.m.label_blue);
                b.this.h.show();
                return false;
            }
        });
        this.H.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lge.media.launcher.control.modelwise.b.10
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                b.this.h.setText(b.m.label_green);
                b.this.h.show();
                return false;
            }
        });
        this.I.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lge.media.launcher.control.modelwise.b.11
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                b.this.h.setText(b.m.label_yellow);
                b.this.h.show();
                return false;
            }
        });
    }

    private void h() {
        this.w.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.control.modelwise.b.12
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                c.a(accessibilityNodeInfo).i(b.this.v);
            }
        });
        this.t.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.control.modelwise.b.13
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                c.a(accessibilityNodeInfo).i(b.this.w);
            }
        });
        this.u.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.control.modelwise.b.14
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                c.a(accessibilityNodeInfo).i(b.this.t);
            }
        });
        this.z.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.control.modelwise.b.15
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                c.a(accessibilityNodeInfo).i(b.this.u);
            }
        });
    }

    @Override // com.lge.media.launcher.control.common.h
    protected void a() {
        for (int i = 0; i < this.f.length; i++) {
            this.j.put(Integer.valueOf(this.e[i]), this.f[i]);
        }
    }

    @Override // com.lge.media.launcher.control.common.h
    protected void a(ViewGroup viewGroup) {
        FrameLayout frameLayout;
        LayoutInflater from;
        int i;
        FrameLayout frameLayout2;
        LayoutInflater from2;
        int i2;
        FrameLayout frameLayout3;
        LayoutInflater from3;
        int i3;
        View findViewById;
        LinearLayout linearLayout;
        this.M = (ViewGroup) viewGroup.findViewById(b.h.layout_remote_page_root);
        View inflate = LayoutInflater.from(this.g).inflate(b.j.hts_key_control, (ViewGroup) null, true);
        ViewGroup viewGroup2 = this.M;
        viewGroup2.addView(inflate, viewGroup2.getChildCount(), this.M.getLayoutParams());
        this.K = (FrameLayout) inflate.findViewById(b.h.hts_key_control_body_layout);
        this.k = (FrameLayout) inflate.findViewById(b.h.layout_hts_root);
        this.q = (RelativeLayout) inflate.findViewById(b.h.layout_control);
        this.r = (LinearLayout) inflate.findViewById(b.h.progress_waiting);
        this.o = (LinearLayout) inflate.findViewById(b.h.layout_layer_btn);
        this.p = (LinearLayout) inflate.findViewById(b.h.layout_layer_btn_r);
        this.J = (Button) inflate.findViewById(b.h.btn_radio);
        MainAct mainAct = this.g;
        if (MainAct.h || this.g.f.n() == null || this.g.f.n().Q()) {
            this.o.setVisibility(4);
            this.p.setVisibility(0);
            this.k.addView(LayoutInflater.from(this.g).inflate(b.j.hts_key_trick_play_r, (ViewGroup) null, false), this.k.getChildCount());
            this.k.addView(LayoutInflater.from(this.g).inflate(b.j.hts_key_numbers_r, (ViewGroup) null, false), this.k.getChildCount());
            this.C = (Button) inflate.findViewById(b.h.btn_sound_control_r);
            this.D = (Button) inflate.findViewById(b.h.btn_trick_play_r);
            this.E = (Button) inflate.findViewById(b.h.btn_num_pad_r);
            this.C.setOnClickListener(this);
            this.D.setOnClickListener(this);
            this.E.setOnClickListener(this);
            int o = this.g.f.o();
            if (o == 0) {
                frameLayout = this.k;
                from = LayoutInflater.from(this.g);
                i = b.j.hts_3d;
                frameLayout.addView(from.inflate(i, (ViewGroup) null, false), this.k.getChildCount());
                findViewById = inflate.findViewById(b.h.btn_3d_sound);
                this.A = (Button) findViewById;
                this.A.setOnClickListener(this);
            } else if (o == 1) {
                frameLayout2 = this.k;
                from2 = LayoutInflater.from(this.g);
                i2 = b.j.hts_sound_zooming_r;
                frameLayout2.addView(from2.inflate(i2, (ViewGroup) null, false), this.k.getChildCount());
                findViewById = inflate.findViewById(b.h.btn_sound_zoom);
                this.A = (Button) findViewById;
                this.A.setOnClickListener(this);
            } else if (o == 2) {
                frameLayout3 = this.k;
                from3 = LayoutInflater.from(this.g);
                i3 = b.j.hts_no_sound_r;
                frameLayout3.addView(from3.inflate(i3, (ViewGroup) null, false), this.k.getChildCount());
                this.A = null;
            }
        } else {
            this.o.setVisibility(0);
            this.p.setVisibility(4);
            this.k.addView(LayoutInflater.from(this.g).inflate(b.j.hts_key_trick_play, (ViewGroup) null, false), this.k.getChildCount());
            this.k.addView(LayoutInflater.from(this.g).inflate(b.j.hts_key_numbers, (ViewGroup) null, false), this.k.getChildCount());
            int o2 = this.g.f.o();
            if (o2 == 0) {
                frameLayout = this.k;
                from = LayoutInflater.from(this.g);
                i = b.j.hts_3d_sound;
                frameLayout.addView(from.inflate(i, (ViewGroup) null, false), this.k.getChildCount());
                findViewById = inflate.findViewById(b.h.btn_3d_sound);
                this.A = (Button) findViewById;
                this.A.setOnClickListener(this);
            } else if (o2 == 1) {
                frameLayout2 = this.k;
                from2 = LayoutInflater.from(this.g);
                i2 = b.j.hts_sound_zooming;
                frameLayout2.addView(from2.inflate(i2, (ViewGroup) null, false), this.k.getChildCount());
                findViewById = inflate.findViewById(b.h.btn_sound_zoom);
                this.A = (Button) findViewById;
                this.A.setOnClickListener(this);
            } else if (o2 == 2) {
                frameLayout3 = this.k;
                from3 = LayoutInflater.from(this.g);
                i3 = b.j.hts_no_sound;
                frameLayout3.addView(from3.inflate(i3, (ViewGroup) null, false), this.k.getChildCount());
                this.A = null;
            }
        }
        this.l = (FrameLayout) inflate.findViewById(b.h.layout_hts_sound_control);
        this.m = (FrameLayout) inflate.findViewById(b.h.layout_hts_key_trick_play);
        this.n = (FrameLayout) inflate.findViewById(b.h.layout_hts_key_numbers);
        this.t = (Button) inflate.findViewById(b.h.btn_arrow_left);
        this.u = (Button) inflate.findViewById(b.h.btn_arrow_right);
        this.v = (Button) inflate.findViewById(b.h.btn_arrow_up);
        this.w = (Button) inflate.findViewById(b.h.btn_arrow_down);
        this.z = (Button) inflate.findViewById(b.h.btn_arrow_enter);
        h();
        this.x = (Button) inflate.findViewById(b.h.btn_main_plus);
        this.y = (Button) inflate.findViewById(b.h.btn_main_minus);
        this.B = (Button) inflate.findViewById(b.h.btn_num_clear);
        this.N = (RelativeLayout) inflate.findViewById(b.h.layout_arrow_left);
        this.O = (RelativeLayout) inflate.findViewById(b.h.layout_arrow_right);
        this.P = (RelativeLayout) inflate.findViewById(b.h.layout_arrow_up);
        this.Q = (RelativeLayout) inflate.findViewById(b.h.layout_arrow_down);
        this.R = (LinearLayout) inflate.findViewById(b.h.layout_bottom_pads_for_cp);
        this.S = (LinearLayout) inflate.findViewById(b.h.layout_bottom_pad_for_touch_and_game);
        this.s = (GridView) inflate.findViewById(b.h.cp_list);
        this.T = (Button) inflate.findViewById(b.h.btn_game_pad);
        int i4 = 0;
        while (true) {
            int[] iArr = this.e;
            if (i4 >= iArr.length) {
                break;
            }
            this.i.add(inflate.findViewById(iArr[i4]));
            i4++;
        }
        this.c = (LinearLayout) inflate.findViewById(b.h.layout_btn_back);
        this.d = (LinearLayout) inflate.findViewById(b.h.layout_btn_disc_menu);
        this.n.setVisibility(4);
        this.l.setVisibility(4);
        this.m.setVisibility(4);
        MainAct mainAct2 = this.g;
        if (!MainAct.h) {
            if (!this.g.f.n().t()) {
                this.S.setVisibility(8);
                linearLayout = this.R;
            }
            this.F = (Button) inflate.findViewById(b.h.btn_red);
            this.G = (Button) inflate.findViewById(b.h.btn_blue);
            this.H = (Button) inflate.findViewById(b.h.btn_green);
            this.I = (Button) inflate.findViewById(b.h.btn_yellow);
            g();
            this.h = Toast.makeText(this.g, com.lge.media.launcher.a.d, 0);
            com.lge.media.launcher.c.a(inflate.findViewById(b.h.btn_num_dim), false);
            com.lge.media.launcher.c.a(inflate.findViewById(b.h.textview_volume));
            com.lge.media.launcher.c.b(inflate.findViewById(b.h.layout_main_home));
            com.lge.media.launcher.c.b(inflate.findViewById(b.h.layout_main_info));
            com.lge.media.launcher.c.b(inflate.findViewById(b.h.layout_btn_back));
            com.lge.media.launcher.c.b(inflate.findViewById(b.h.layout_btn_disc_menu));
            com.lge.media.launcher.c.b(inflate.findViewById(b.h.layout_main_mute));
            com.lge.media.launcher.c.b(inflate.findViewById(b.h.layout_main_function));
        }
        linearLayout = this.S;
        linearLayout.setVisibility(8);
        this.F = (Button) inflate.findViewById(b.h.btn_red);
        this.G = (Button) inflate.findViewById(b.h.btn_blue);
        this.H = (Button) inflate.findViewById(b.h.btn_green);
        this.I = (Button) inflate.findViewById(b.h.btn_yellow);
        g();
        this.h = Toast.makeText(this.g, com.lge.media.launcher.a.d, 0);
        com.lge.media.launcher.c.a(inflate.findViewById(b.h.btn_num_dim), false);
        com.lge.media.launcher.c.a(inflate.findViewById(b.h.textview_volume));
        com.lge.media.launcher.c.b(inflate.findViewById(b.h.layout_main_home));
        com.lge.media.launcher.c.b(inflate.findViewById(b.h.layout_main_info));
        com.lge.media.launcher.c.b(inflate.findViewById(b.h.layout_btn_back));
        com.lge.media.launcher.c.b(inflate.findViewById(b.h.layout_btn_disc_menu));
        com.lge.media.launcher.c.b(inflate.findViewById(b.h.layout_main_mute));
        com.lge.media.launcher.c.b(inflate.findViewById(b.h.layout_main_function));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lge.media.launcher.control.common.h
    public void a(com.lge.media.launcher.cplist.c cVar) {
        this.r.setVisibility(4);
        this.q.setVisibility(0);
        if ((!MainAct.h && !this.g.f.n().S()) || cVar == null) {
            this.R.setVisibility(8);
            return;
        }
        this.R.setVisibility(0);
        e eVar = new e(this.g, (ArrayList) cVar.a);
        this.s.setAdapter((ListAdapter) eVar);
        if (eVar.getCount() == 0) {
            this.R.setVisibility(8);
        } else if (eVar.getCount() == 1) {
            this.s.setNumColumns(1);
        }
        this.s.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lge.media.launcher.control.modelwise.b.8
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (MainAct.h) {
                    return;
                }
                try {
                    b.this.g.g.b.h(Integer.parseInt(b.this.g.f.s.a.get(i).a()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override // com.lge.media.launcher.control.common.h
    protected void b() {
        for (int i = 0; i < this.i.size(); i++) {
            try {
                this.i.get(i).setOnClickListener(this);
            } catch (Exception unused) {
                Log.e(d.a, "HTS btn set Listeners error num : " + i);
            }
        }
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.t.setOnTouchListener(this);
        this.u.setOnTouchListener(this);
        this.v.setOnTouchListener(this);
        this.w.setOnTouchListener(this);
        this.x.setOnTouchListener(this);
        this.y.setOnTouchListener(this);
        this.B.setOnTouchListener(this);
        this.J.setOnClickListener(this);
        this.T.setOnClickListener(this);
        this.N.setOnTouchListener(this);
        this.O.setOnTouchListener(this);
        this.P.setOnTouchListener(this);
        this.Q.setOnTouchListener(this);
        this.t.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.w.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.y.setOnClickListener(this);
        this.B.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lge.media.launcher.control.common.h
    public void c() {
        this.l.setVisibility(4);
        this.n.setVisibility(4);
        this.m.setVisibility(0);
        com.lge.media.launcher.c.a((View) this.K, false);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.lge.media.launcher.control.modelwise.b.16
            @Override // java.lang.Runnable
            public void run() {
                b.this.k.findViewById(b.h.btn_prev).sendAccessibilityEvent(8);
            }
        }, 500L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lge.media.launcher.control.common.h
    public void d() {
        this.l.setVisibility(4);
        this.n.setVisibility(4);
        this.m.setVisibility(4);
        com.lge.media.launcher.c.a((View) this.K, true);
    }

    @Override // com.lge.media.launcher.control.common.h
    public void e() {
        this.r.setVisibility(4);
        this.q.setVisibility(0);
    }

    @Override // com.lge.media.launcher.control.common.h
    public void f() {
        this.r.setVisibility(0);
        this.q.setVisibility(4);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        com.lge.media.launcher.control.common.e eVar;
        b.c cVar;
        b.c cVar2;
        int i;
        Handler handler;
        Runnable runnable;
        Log.d(d.a, "onclick!");
        switch (view.getId()) {
            case b.h.btn_3d_sound /* 2131230756 */:
                f.d();
                eVar = this.g.f;
                cVar = b.c.KEYCODE_3D_SOUND;
                eVar.a(cVar);
                return;
            case b.h.btn_arrow_down /* 2131230757 */:
                cVar2 = b.c.KEYCODE_DOWN;
                a(cVar2);
                return;
            case b.h.btn_arrow_left /* 2131230759 */:
                cVar2 = b.c.KEYCODE_LEFT;
                a(cVar2);
                return;
            case b.h.btn_arrow_right /* 2131230760 */:
                cVar2 = b.c.KEYCODE_RIGHT;
                a(cVar2);
                return;
            case b.h.btn_arrow_up /* 2131230761 */:
                cVar2 = b.c.KEYCODE_UP;
                a(cVar2);
                return;
            case b.h.btn_blue /* 2131230765 */:
                i = b.m.label_blue;
                a(view, i);
                return;
            case b.h.btn_disc_menu /* 2131230770 */:
            case b.h.btn_info /* 2131230788 */:
            case b.h.btn_popup_title /* 2131230830 */:
                this.m.setVisibility(4);
                com.lge.media.launcher.c.a((View) this.K, true);
                f.d();
                this.g.f.a(this.j.get(Integer.valueOf(view.getId())));
                return;
            case b.h.btn_game_pad /* 2131230782 */:
                f.d();
                Intent intent = new Intent(this.g, GamePadAct.class);
                MainAct mainAct = this.g;
                intent.putExtra(d.Q, MainAct.h);
                this.g.startActivity(intent);
                return;
            case b.h.btn_green /* 2131230787 */:
                i = b.m.label_green;
                a(view, i);
                return;
            case b.h.btn_key_num_pad /* 2131230792 */:
            case b.h.layout_hts_key_numbers /* 2131230952 */:
                f.d();
                this.n.setVisibility(4);
                com.lge.media.launcher.c.a((View) this.K, true);
                handler = new Handler(Looper.getMainLooper());
                runnable = new Runnable() { // from class: com.lge.media.launcher.control.modelwise.b.7
                    @Override // java.lang.Runnable
                    public void run() {
                        FrameLayout frameLayout;
                        int i2;
                        if (MainAct.h || b.this.g.f.n() == null || b.this.g.f.n().Q()) {
                            frameLayout = b.this.k;
                            i2 = b.h.btn_num_pad_r;
                        } else {
                            frameLayout = b.this.k;
                            i2 = b.h.btn_num_pad;
                        }
                        frameLayout.findViewById(i2).sendAccessibilityEvent(8);
                    }
                };
                handler.postDelayed(runnable, 500L);
                return;
            case b.h.btn_key_sound_control /* 2131230793 */:
            case b.h.layout_hts_sound_control /* 2131230955 */:
                f.d();
                this.l.setVisibility(4);
                com.lge.media.launcher.c.a((View) this.K, true);
                handler = new Handler(Looper.getMainLooper());
                runnable = new Runnable() { // from class: com.lge.media.launcher.control.modelwise.b.3
                    @Override // java.lang.Runnable
                    public void run() {
                        FrameLayout frameLayout;
                        int i2;
                        if (MainAct.h || b.this.g.f.n() == null || b.this.g.f.n().Q()) {
                            frameLayout = b.this.k;
                            i2 = b.h.btn_sound_control_r;
                        } else {
                            frameLayout = b.this.k;
                            i2 = b.h.btn_sound_control;
                        }
                        frameLayout.findViewById(i2).sendAccessibilityEvent(8);
                    }
                };
                handler.postDelayed(runnable, 500L);
                return;
            case b.h.btn_key_trick_play /* 2131230794 */:
            case b.h.layout_hts_key_trick_play /* 2131230953 */:
                f.d();
                this.m.setVisibility(4);
                com.lge.media.launcher.c.a((View) this.K, true);
                handler = new Handler(Looper.getMainLooper());
                runnable = new Runnable() { // from class: com.lge.media.launcher.control.modelwise.b.5
                    @Override // java.lang.Runnable
                    public void run() {
                        FrameLayout frameLayout;
                        int i2;
                        if (MainAct.h || b.this.g.f.n() == null || b.this.g.f.n().Q()) {
                            frameLayout = b.this.k;
                            i2 = b.h.btn_trick_play_r;
                        } else {
                            frameLayout = b.this.k;
                            i2 = b.h.btn_trick_play;
                        }
                        frameLayout.findViewById(i2).sendAccessibilityEvent(8);
                    }
                };
                handler.postDelayed(runnable, 500L);
                return;
            case b.h.btn_main_info /* 2131230795 */:
                f.d();
                this.g.f.z = false;
                eVar = this.g.f;
                cVar = b.c.KEYCODE_INFO;
                eVar.a(cVar);
                return;
            case b.h.btn_main_minus /* 2131230796 */:
                if (this.g.f.u) {
                    this.g.f();
                    return;
                }
                cVar2 = b.c.KEYCODE_VOLUME_DOWN;
                a(cVar2);
                return;
            case b.h.btn_main_plus /* 2131230797 */:
                if (this.g.f.u) {
                    this.g.e();
                    return;
                }
                cVar2 = b.c.KEYCODE_VOLUME_UP;
                a(cVar2);
                return;
            case b.h.btn_next /* 2131230805 */:
                f.d();
                eVar = this.g.f;
                cVar = b.c.KEYCODE_NEXT;
                eVar.a(cVar);
                return;
            case b.h.btn_num_clear /* 2131230810 */:
                cVar2 = b.c.KEYCODE_CLEAR;
                a(cVar2);
                return;
            case b.h.btn_num_pad /* 2131230819 */:
            case b.h.btn_num_pad_r /* 2131230820 */:
                f.d();
                this.n.setVisibility(0);
                com.lge.media.launcher.c.a((View) this.K, false);
                handler = new Handler(Looper.getMainLooper());
                runnable = new Runnable() { // from class: com.lge.media.launcher.control.modelwise.b.6
                    @Override // java.lang.Runnable
                    public void run() {
                        b.this.k.findViewById(b.h.btn_num_one).sendAccessibilityEvent(8);
                    }
                };
                handler.postDelayed(runnable, 500L);
                return;
            case b.h.btn_pause /* 2131230827 */:
                f.d();
                eVar = this.g.f;
                cVar = b.c.KEYCODE_PAUSE;
                eVar.a(cVar);
                return;
            case b.h.btn_play /* 2131230828 */:
                f.d();
                eVar = this.g.f;
                cVar = b.c.KEYCODE_PLAY;
                eVar.a(cVar);
                return;
            case b.h.btn_prev /* 2131230833 */:
                f.d();
                eVar = this.g.f;
                cVar = b.c.KEYCODE_PREVIOUS;
                eVar.a(cVar);
                return;
            case b.h.btn_radio /* 2131230837 */:
                f.d();
                this.g.f.v = true;
                eVar = this.g.f;
                cVar = b.c.KEYCODE_TUNER;
                eVar.a(cVar);
                return;
            case b.h.btn_red /* 2131230838 */:
                i = b.m.label_red;
                a(view, i);
                return;
            case b.h.btn_repeat /* 2131230841 */:
                f.d();
                MainAct mainAct2 = this.g;
                if (!MainAct.h) {
                    this.g.f.n().s();
                }
                eVar = this.g.f;
                cVar = b.c.KEYCODE_REPEAT;
                eVar.a(cVar);
                return;
            case b.h.btn_sound_control /* 2131230844 */:
            case b.h.btn_sound_control_r /* 2131230845 */:
                f.d();
                this.l.setVisibility(0);
                com.lge.media.launcher.c.a((View) this.K, false);
                handler = new Handler(Looper.getMainLooper());
                runnable = new Runnable() { // from class: com.lge.media.launcher.control.modelwise.b.2
                    @Override // java.lang.Runnable
                    public void run() {
                        FrameLayout frameLayout;
                        int i2;
                        int o = b.this.g.f.o();
                        if (o == 0) {
                            frameLayout = b.this.k;
                            i2 = b.h.btn_3d_sound;
                        } else if (o == 1) {
                            frameLayout = b.this.k;
                            i2 = b.h.btn_sound_zoom;
                        } else if (o != 2) {
                            return;
                        } else {
                            frameLayout = b.this.k;
                            i2 = b.h.btn_sound_effect;
                        }
                        frameLayout.findViewById(i2).sendAccessibilityEvent(8);
                    }
                };
                handler.postDelayed(runnable, 500L);
                return;
            case b.h.btn_stop /* 2131230850 */:
                f.d();
                eVar = this.g.f;
                cVar = b.c.KEYCODE_STOP;
                eVar.a(cVar);
                return;
            case b.h.btn_trick_play /* 2131230855 */:
            case b.h.btn_trick_play_r /* 2131230856 */:
                f.d();
                this.m.setVisibility(0);
                com.lge.media.launcher.c.a((View) this.K, false);
                handler = new Handler(Looper.getMainLooper());
                runnable = new Runnable() { // from class: com.lge.media.launcher.control.modelwise.b.4
                    @Override // java.lang.Runnable
                    public void run() {
                        b.this.k.findViewById(b.h.btn_prev).sendAccessibilityEvent(8);
                    }
                };
                handler.postDelayed(runnable, 500L);
                return;
            case b.h.btn_yellow /* 2131230857 */:
                i = b.m.label_yellow;
                a(view, i);
                return;
            default:
                f.d();
                this.g.f.a(this.j.get(Integer.valueOf(view.getId())));
                return;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        MainAct mainAct;
        b.c cVar;
        com.lge.media.launcher.control.common.e eVar;
        b.c cVar2;
        int action = motionEvent.getAction();
        if (action == 0) {
            switch (view.getId()) {
                case b.h.btn_arrow_down /* 2131230757 */:
                    this.g.f.b(b.c.KEYCODE_DOWN);
                    mainAct = this.g;
                    cVar = b.c.KEYCODE_DOWN;
                    mainAct.q = cVar;
                    break;
                case b.h.btn_arrow_left /* 2131230759 */:
                    this.g.f.b(b.c.KEYCODE_LEFT);
                    mainAct = this.g;
                    cVar = b.c.KEYCODE_LEFT;
                    mainAct.q = cVar;
                    break;
                case b.h.btn_arrow_right /* 2131230760 */:
                    this.g.f.b(b.c.KEYCODE_RIGHT);
                    mainAct = this.g;
                    cVar = b.c.KEYCODE_RIGHT;
                    mainAct.q = cVar;
                    break;
                case b.h.btn_arrow_up /* 2131230761 */:
                    this.g.f.b(b.c.KEYCODE_UP);
                    mainAct = this.g;
                    cVar = b.c.KEYCODE_UP;
                    mainAct.q = cVar;
                    break;
                case b.h.btn_main_minus /* 2131230796 */:
                    if (!this.g.f.u) {
                        this.g.f.b(b.c.KEYCODE_VOLUME_DOWN);
                        mainAct = this.g;
                        cVar = b.c.KEYCODE_VOLUME_DOWN;
                        mainAct.q = cVar;
                        break;
                    } else {
                        this.g.f();
                        break;
                    }
                case b.h.btn_main_plus /* 2131230797 */:
                    if (!this.g.f.u) {
                        this.g.f.b(b.c.KEYCODE_VOLUME_UP);
                        mainAct = this.g;
                        cVar = b.c.KEYCODE_VOLUME_UP;
                        mainAct.q = cVar;
                        break;
                    } else {
                        this.g.e();
                        break;
                    }
                case b.h.btn_num_clear /* 2131230810 */:
                    this.g.f.b(b.c.KEYCODE_CLEAR);
                    mainAct = this.g;
                    cVar = b.c.KEYCODE_CLEAR;
                    mainAct.q = cVar;
                    break;
                case b.h.layout_arrow_down /* 2131230931 */:
                    Button button = this.w;
                    if (button != null) {
                        button.setPressed(true);
                    }
                    this.g.f.b(b.c.KEYCODE_DOWN);
                    mainAct = this.g;
                    cVar = b.c.KEYCODE_DOWN;
                    mainAct.q = cVar;
                    break;
                case b.h.layout_arrow_left /* 2131230932 */:
                    Button button2 = this.t;
                    if (button2 != null) {
                        button2.setPressed(true);
                    }
                    this.g.f.b(b.c.KEYCODE_LEFT);
                    mainAct = this.g;
                    cVar = b.c.KEYCODE_LEFT;
                    mainAct.q = cVar;
                    break;
                case b.h.layout_arrow_right /* 2131230933 */:
                    Button button3 = this.u;
                    if (button3 != null) {
                        button3.setPressed(true);
                    }
                    this.g.f.b(b.c.KEYCODE_RIGHT);
                    mainAct = this.g;
                    cVar = b.c.KEYCODE_RIGHT;
                    mainAct.q = cVar;
                    break;
                case b.h.layout_arrow_up /* 2131230934 */:
                    Button button4 = this.v;
                    if (button4 != null) {
                        button4.setPressed(true);
                    }
                    this.g.f.b(b.c.KEYCODE_UP);
                    mainAct = this.g;
                    cVar = b.c.KEYCODE_UP;
                    mainAct.q = cVar;
                    break;
            }
        } else if (action == 1) {
            switch (view.getId()) {
                case b.h.btn_arrow_down /* 2131230757 */:
                    eVar = this.g.f;
                    cVar2 = b.c.KEYCODE_DOWN;
                    eVar.c(cVar2);
                    break;
                case b.h.btn_arrow_left /* 2131230759 */:
                    eVar = this.g.f;
                    cVar2 = b.c.KEYCODE_LEFT;
                    eVar.c(cVar2);
                    break;
                case b.h.btn_arrow_right /* 2131230760 */:
                    eVar = this.g.f;
                    cVar2 = b.c.KEYCODE_RIGHT;
                    eVar.c(cVar2);
                    break;
                case b.h.btn_arrow_up /* 2131230761 */:
                    eVar = this.g.f;
                    cVar2 = b.c.KEYCODE_UP;
                    eVar.c(cVar2);
                    break;
                case b.h.btn_main_minus /* 2131230796 */:
                    eVar = this.g.f;
                    cVar2 = b.c.KEYCODE_VOLUME_DOWN;
                    eVar.c(cVar2);
                    break;
                case b.h.btn_main_plus /* 2131230797 */:
                    eVar = this.g.f;
                    cVar2 = b.c.KEYCODE_VOLUME_UP;
                    eVar.c(cVar2);
                    break;
                case b.h.btn_num_clear /* 2131230810 */:
                    eVar = this.g.f;
                    cVar2 = b.c.KEYCODE_CLEAR;
                    eVar.c(cVar2);
                    break;
                case b.h.layout_arrow_down /* 2131230931 */:
                    Button button5 = this.w;
                    if (button5 != null) {
                        button5.setPressed(false);
                    }
                    eVar = this.g.f;
                    cVar2 = b.c.KEYCODE_DOWN;
                    eVar.c(cVar2);
                    break;
                case b.h.layout_arrow_left /* 2131230932 */:
                    Button button6 = this.t;
                    if (button6 != null) {
                        button6.setPressed(false);
                    }
                    eVar = this.g.f;
                    cVar2 = b.c.KEYCODE_LEFT;
                    eVar.c(cVar2);
                    break;
                case b.h.layout_arrow_right /* 2131230933 */:
                    Button button7 = this.u;
                    if (button7 != null) {
                        button7.setPressed(false);
                    }
                    eVar = this.g.f;
                    cVar2 = b.c.KEYCODE_RIGHT;
                    eVar.c(cVar2);
                    break;
                case b.h.layout_arrow_up /* 2131230934 */:
                    Button button8 = this.v;
                    if (button8 != null) {
                        button8.setPressed(false);
                    }
                    eVar = this.g.f;
                    cVar2 = b.c.KEYCODE_UP;
                    eVar.c(cVar2);
                    break;
            }
        }
        return false;
    }
}
