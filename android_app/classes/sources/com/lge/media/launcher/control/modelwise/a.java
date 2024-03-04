package com.lge.media.launcher.control.modelwise;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.a.c;
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
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a extends h {
    private RelativeLayout A;
    private RelativeLayout B;
    private FrameLayout C;
    int[] c;
    b.c[] d;
    LinearLayout e;
    private Toast f;
    private LinearLayout h;
    private LinearLayout i;
    private Button j;
    private Button k;
    private Button l;
    private Button m;
    private Button n;
    private Button o;
    private Button p;
    private Button q;
    private Button r;
    private Button s;
    private LinearLayout t;
    private LinearLayout u;
    private GridView v;
    private LinearLayout w;
    private ViewGroup x;
    private RelativeLayout y;
    private RelativeLayout z;

    public a(Context context, ViewGroup viewGroup) {
        super(context, viewGroup);
        this.c = new int[]{b.h.layout_main_home, b.h.layout_main_info, b.h.layout_btn_back, b.h.layout_btn_disc_menu, b.h.btn_arrow_enter, b.h.btn_prev, b.h.btn_rew, b.h.btn_ff, b.h.btn_next, b.h.btn_stop, b.h.btn_play, b.h.btn_pause, b.h.btn_repeat, b.h.btn_disc_menu, b.h.btn_popup_title, b.h.btn_info, b.h.btn_red, b.h.btn_green, b.h.btn_yellow, b.h.btn_blue, b.h.btn_num_one, b.h.btn_num_two, b.h.btn_num_three, b.h.btn_num_four, b.h.btn_num_five, b.h.btn_num_six, b.h.btn_num_seven, b.h.btn_num_eight, b.h.btn_num_nine, b.h.btn_num_zero, b.h.btn_trick_play, b.h.btn_num_pad, b.h.btn_key_trick_play, b.h.btn_key_num_pad, b.h.btn_game_pad};
        this.d = new b.c[]{b.c.KEYCODE_HOME, b.c.KEYCODE_INFO, b.c.KEYCODE_BACK, b.c.KEYCODE_DISC_MENU, b.c.KEYCODE_ENTER, b.c.KEYCODE_SKIP_BACKWARD, b.c.KEYCODE_FAST_BACKWARD, b.c.KEYCODE_FAST_FORWARD, b.c.KEYCODE_SKIP_FORWARD, b.c.KEYCODE_STOP, b.c.KEYCODE_PLAY, b.c.KEYCODE_PAUSE, b.c.KEYCODE_REPEAT, b.c.KEYCODE_DISC_MENU, b.c.KEYCODE_POPUP, b.c.KEYCODE_INFO, b.c.KEYCODE_RED, b.c.KEYCODE_GREEN, b.c.KEYCODE_YELLOW, b.c.KEYCODE_BLUE, b.c.KEYCODE_1, b.c.KEYCODE_2, b.c.KEYCODE_3, b.c.KEYCODE_4, b.c.KEYCODE_5, b.c.KEYCODE_6, b.c.KEYCODE_7, b.c.KEYCODE_8, b.c.KEYCODE_9, b.c.KEYCODE_0};
        a(viewGroup);
        a();
        b();
    }

    private void a(View view, int i) {
        f.d();
        this.g.f.a(this.b.get(Integer.valueOf(view.getId())));
    }

    private void a(b.c cVar) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.g.getSystemService("accessibility");
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return;
        }
        this.g.f.a(cVar);
    }

    private void g() {
        this.p.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lge.media.launcher.control.modelwise.a.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                a.this.f.setText(b.m.label_red);
                a.this.f.show();
                return false;
            }
        });
        this.q.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lge.media.launcher.control.modelwise.a.7
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                a.this.f.setText(b.m.label_blue);
                a.this.f.show();
                return false;
            }
        });
        this.r.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lge.media.launcher.control.modelwise.a.8
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                a.this.f.setText(b.m.label_green);
                a.this.f.show();
                return false;
            }
        });
        this.s.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lge.media.launcher.control.modelwise.a.9
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                a.this.f.setText(b.m.label_yellow);
                a.this.f.show();
                return false;
            }
        });
    }

    private void h() {
        this.m.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.control.modelwise.a.10
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                c.a(accessibilityNodeInfo).i(a.this.l);
            }
        });
        this.j.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.control.modelwise.a.11
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                c.a(accessibilityNodeInfo).i(a.this.m);
            }
        });
        this.k.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.control.modelwise.a.12
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                c.a(accessibilityNodeInfo).i(a.this.j);
            }
        });
        this.o.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.control.modelwise.a.13
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                c.a(accessibilityNodeInfo).i(a.this.k);
            }
        });
    }

    @Override // com.lge.media.launcher.control.common.h
    protected void a() {
        for (int i = 0; i < this.d.length; i++) {
            this.b.put(Integer.valueOf(this.c[i]), this.d[i]);
        }
    }

    @Override // com.lge.media.launcher.control.common.h
    protected void a(ViewGroup viewGroup) {
        this.x = (ViewGroup) viewGroup.findViewById(b.h.layout_remote_page_root);
        View inflate = LayoutInflater.from(this.g).inflate(b.j.bdp_key_control, (ViewGroup) null, true);
        ViewGroup viewGroup2 = this.x;
        viewGroup2.addView(inflate, viewGroup2.getChildCount(), this.x.getLayoutParams());
        for (int i = 0; i < this.c.length; i++) {
            this.a.add(inflate.findViewById(this.c[i]));
        }
        this.C = (FrameLayout) inflate.findViewById(b.h.bdp_key_control_body_layout);
        this.i = (LinearLayout) inflate.findViewById(b.h.layout_bdp_key_trick_play_3d);
        this.h = (LinearLayout) inflate.findViewById(b.h.layout_bdp_key_numbers);
        this.j = (Button) inflate.findViewById(b.h.btn_arrow_left);
        this.k = (Button) inflate.findViewById(b.h.btn_arrow_right);
        this.l = (Button) inflate.findViewById(b.h.btn_arrow_up);
        this.m = (Button) inflate.findViewById(b.h.btn_arrow_down);
        this.o = (Button) inflate.findViewById(b.h.btn_arrow_enter);
        h();
        this.y = (RelativeLayout) inflate.findViewById(b.h.layout_arrow_left);
        this.z = (RelativeLayout) inflate.findViewById(b.h.layout_arrow_right);
        this.A = (RelativeLayout) inflate.findViewById(b.h.layout_arrow_up);
        this.B = (RelativeLayout) inflate.findViewById(b.h.layout_arrow_down);
        this.e = (LinearLayout) inflate.findViewById(b.h.layout_arrow_center);
        this.t = (LinearLayout) inflate.findViewById(b.h.layout_bottom_pads_for_cp);
        this.v = (GridView) inflate.findViewById(b.h.cp_list);
        this.w = (LinearLayout) inflate.findViewById(b.h.progress_waiting);
        this.w.setVisibility(4);
        this.u = (LinearLayout) inflate.findViewById(b.h.layout_bottom_pad_for_touch_and_game);
        this.n = (Button) inflate.findViewById(b.h.btn_game_pad);
        MainAct mainAct = this.g;
        if (MainAct.h || !this.g.f.n().t()) {
            this.u.setVisibility(8);
        }
        this.p = (Button) inflate.findViewById(b.h.btn_red);
        this.q = (Button) inflate.findViewById(b.h.btn_blue);
        this.r = (Button) inflate.findViewById(b.h.btn_green);
        this.s = (Button) inflate.findViewById(b.h.btn_yellow);
        g();
        com.lge.media.launcher.c.a(inflate.findViewById(b.h.btn_num_dim_1), false);
        com.lge.media.launcher.c.a(inflate.findViewById(b.h.btn_num_dim_2), false);
        com.lge.media.launcher.c.b(inflate.findViewById(b.h.layout_main_home));
        com.lge.media.launcher.c.b(inflate.findViewById(b.h.layout_main_info));
        com.lge.media.launcher.c.b(inflate.findViewById(b.h.layout_btn_back));
        com.lge.media.launcher.c.b(inflate.findViewById(b.h.layout_btn_disc_menu));
        this.f = Toast.makeText(this.g, com.lge.media.launcher.a.d, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lge.media.launcher.control.common.h
    public void a(com.lge.media.launcher.cplist.c cVar) {
        if ((MainAct.h || this.g.f.n().S()) && cVar != null) {
            this.t.setVisibility(0);
            e eVar = new e(this.g, (ArrayList) cVar.a);
            com.lge.media.launcher.control.common.a.b("**** itemList : " + cVar.a.size());
            if (cVar.a.size() >= 1) {
                this.v.setAdapter((ListAdapter) eVar);
                if (eVar.getCount() == 0) {
                    this.t.setVisibility(8);
                } else if (eVar.getCount() == 1) {
                    this.v.setNumColumns(1);
                }
                this.v.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lge.media.launcher.control.modelwise.a.6
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        if (MainAct.h) {
                            return;
                        }
                        try {
                            a.this.g.g.b.h(Integer.parseInt(a.this.g.f.s.a.get(i).a()));
                        } catch (NumberFormatException e) {
                            a.this.w.setVisibility(4);
                            e.printStackTrace();
                        }
                    }
                });
            }
        } else {
            this.t.setVisibility(8);
        }
        this.w.setVisibility(4);
    }

    @Override // com.lge.media.launcher.control.common.h
    protected void b() {
        for (int i = 0; i < this.a.size(); i++) {
            this.a.get(i).setOnClickListener(this);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("set Listener ctx.isDemo : ");
        MainAct mainAct = this.g;
        sb.append(MainAct.h);
        com.lge.media.launcher.control.common.a.a(sb.toString());
        this.i.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.j.setOnTouchListener(this);
        this.k.setOnTouchListener(this);
        this.l.setOnTouchListener(this);
        this.m.setOnTouchListener(this);
        this.n.setOnClickListener(this);
        this.y.setOnTouchListener(this);
        this.z.setOnTouchListener(this);
        this.A.setOnTouchListener(this);
        this.B.setOnTouchListener(this);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lge.media.launcher.control.common.h
    public void c() {
        this.h.setVisibility(4);
        this.i.setVisibility(0);
        com.lge.media.launcher.c.a((View) this.C, false);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.lge.media.launcher.control.modelwise.a.14
            @Override // java.lang.Runnable
            public void run() {
                a.this.x.findViewById(b.h.btn_prev).sendAccessibilityEvent(8);
            }
        }, 500L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lge.media.launcher.control.common.h
    public void d() {
        this.h.setVisibility(4);
        this.i.setVisibility(4);
        com.lge.media.launcher.c.a((View) this.C, true);
    }

    @Override // com.lge.media.launcher.control.common.h
    public void e() {
        this.w.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lge.media.launcher.control.common.h
    public void f() {
        this.w.setVisibility(0);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        b.c cVar;
        int i;
        Handler handler;
        Runnable runnable;
        com.lge.media.launcher.control.common.e eVar;
        b.c cVar2;
        com.lge.media.launcher.control.common.a.b("[BDPRemotePage]onclick!");
        switch (view.getId()) {
            case b.h.btn_arrow_down /* 2131230757 */:
                cVar = b.c.KEYCODE_DOWN;
                a(cVar);
                return;
            case b.h.btn_arrow_left /* 2131230759 */:
                cVar = b.c.KEYCODE_LEFT;
                a(cVar);
                return;
            case b.h.btn_arrow_right /* 2131230760 */:
                cVar = b.c.KEYCODE_RIGHT;
                a(cVar);
                return;
            case b.h.btn_arrow_up /* 2131230761 */:
                cVar = b.c.KEYCODE_UP;
                a(cVar);
                return;
            case b.h.btn_blue /* 2131230765 */:
                i = b.m.label_blue;
                a(view, i);
                return;
            case b.h.btn_disc_menu /* 2131230770 */:
            case b.h.btn_info /* 2131230788 */:
            case b.h.btn_popup_title /* 2131230830 */:
                this.i.setVisibility(4);
                com.lge.media.launcher.c.a((View) this.C, true);
                f.d();
                com.lge.media.launcher.control.common.a.b("view id : " + view.getId());
                this.g.f.a(this.b.get(Integer.valueOf(view.getId())));
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
            case b.h.layout_bdp_key_numbers /* 2131230937 */:
                f.d();
                this.h.setVisibility(4);
                com.lge.media.launcher.c.a((View) this.C, true);
                handler = new Handler(Looper.getMainLooper());
                runnable = new Runnable() { // from class: com.lge.media.launcher.control.modelwise.a.5
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.x.findViewById(b.h.btn_num_pad).sendAccessibilityEvent(8);
                    }
                };
                handler.postDelayed(runnable, 500L);
                return;
            case b.h.btn_key_trick_play /* 2131230794 */:
            case b.h.layout_bdp_key_trick_play_3d /* 2131230938 */:
                f.d();
                this.i.setVisibility(4);
                com.lge.media.launcher.c.a((View) this.C, true);
                handler = new Handler(Looper.getMainLooper());
                runnable = new Runnable() { // from class: com.lge.media.launcher.control.modelwise.a.3
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.x.findViewById(b.h.btn_trick_play).sendAccessibilityEvent(8);
                    }
                };
                handler.postDelayed(runnable, 500L);
                return;
            case b.h.btn_next /* 2131230805 */:
                f.d();
                eVar = this.g.f;
                cVar2 = b.c.KEYCODE_NEXT;
                eVar.a(cVar2);
                return;
            case b.h.btn_num_pad /* 2131230819 */:
                f.d();
                this.h.setVisibility(0);
                com.lge.media.launcher.c.a((View) this.C, false);
                handler = new Handler(Looper.getMainLooper());
                runnable = new Runnable() { // from class: com.lge.media.launcher.control.modelwise.a.4
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.x.findViewById(b.h.btn_num_one).sendAccessibilityEvent(8);
                    }
                };
                handler.postDelayed(runnable, 500L);
                return;
            case b.h.btn_pause /* 2131230827 */:
                f.d();
                eVar = this.g.f;
                cVar2 = b.c.KEYCODE_PAUSE;
                eVar.a(cVar2);
                return;
            case b.h.btn_play /* 2131230828 */:
                f.d();
                eVar = this.g.f;
                cVar2 = b.c.KEYCODE_PLAY;
                eVar.a(cVar2);
                return;
            case b.h.btn_prev /* 2131230833 */:
                f.d();
                eVar = this.g.f;
                cVar2 = b.c.KEYCODE_PREVIOUS;
                eVar.a(cVar2);
                return;
            case b.h.btn_red /* 2131230838 */:
                i = b.m.label_red;
                a(view, i);
                return;
            case b.h.btn_repeat /* 2131230841 */:
                MainAct mainAct2 = this.g;
                if (!MainAct.h) {
                    this.g.f.n().s();
                }
                f.d();
                eVar = this.g.f;
                cVar2 = b.c.KEYCODE_REPEAT;
                eVar.a(cVar2);
                return;
            case b.h.btn_stop /* 2131230850 */:
                f.d();
                eVar = this.g.f;
                cVar2 = b.c.KEYCODE_STOP;
                eVar.a(cVar2);
                return;
            case b.h.btn_trick_play /* 2131230855 */:
                f.d();
                this.i.setVisibility(0);
                com.lge.media.launcher.c.a((View) this.C, false);
                handler = new Handler(Looper.getMainLooper());
                runnable = new Runnable() { // from class: com.lge.media.launcher.control.modelwise.a.2
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.x.findViewById(b.h.btn_prev).sendAccessibilityEvent(8);
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
                com.lge.media.launcher.control.common.a.b("view id : " + view.getId());
                this.g.f.a(this.b.get(Integer.valueOf(view.getId())));
                return;
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        MainAct mainAct;
        b.c cVar;
        com.lge.media.launcher.control.common.e eVar;
        b.c cVar2;
        com.lge.media.launcher.control.common.a.b("onTouch : " + view.getId() + "Event : " + motionEvent.getAction());
        int action = motionEvent.getAction();
        if (action == 0) {
            int id = view.getId();
            if (id != 2131230757) {
                switch (id) {
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
                    default:
                        switch (id) {
                            case b.h.layout_arrow_down /* 2131230931 */:
                                Button button = this.m;
                                if (button != null) {
                                    button.setPressed(true);
                                    break;
                                }
                                break;
                            case b.h.layout_arrow_left /* 2131230932 */:
                                Button button2 = this.j;
                                if (button2 != null) {
                                    button2.setPressed(true);
                                }
                                this.g.f.b(b.c.KEYCODE_LEFT);
                                mainAct = this.g;
                                cVar = b.c.KEYCODE_LEFT;
                                mainAct.q = cVar;
                                break;
                            case b.h.layout_arrow_right /* 2131230933 */:
                                Button button3 = this.k;
                                if (button3 != null) {
                                    button3.setPressed(true);
                                }
                                this.g.f.b(b.c.KEYCODE_RIGHT);
                                mainAct = this.g;
                                cVar = b.c.KEYCODE_RIGHT;
                                mainAct.q = cVar;
                                break;
                            case b.h.layout_arrow_up /* 2131230934 */:
                                Button button4 = this.l;
                                if (button4 != null) {
                                    button4.setPressed(true);
                                }
                                this.g.f.b(b.c.KEYCODE_UP);
                                mainAct = this.g;
                                cVar = b.c.KEYCODE_UP;
                                mainAct.q = cVar;
                                break;
                        }
                }
            }
            this.g.f.b(b.c.KEYCODE_DOWN);
            mainAct = this.g;
            cVar = b.c.KEYCODE_DOWN;
            mainAct.q = cVar;
        } else if (action == 1) {
            int id2 = view.getId();
            if (id2 != 2131230757) {
                switch (id2) {
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
                    default:
                        switch (id2) {
                            case b.h.layout_arrow_down /* 2131230931 */:
                                Button button5 = this.m;
                                if (button5 != null) {
                                    button5.setPressed(false);
                                    break;
                                }
                                break;
                            case b.h.layout_arrow_left /* 2131230932 */:
                                Button button6 = this.j;
                                if (button6 != null) {
                                    button6.setPressed(false);
                                }
                                eVar = this.g.f;
                                cVar2 = b.c.KEYCODE_LEFT;
                                eVar.c(cVar2);
                                break;
                            case b.h.layout_arrow_right /* 2131230933 */:
                                Button button7 = this.k;
                                if (button7 != null) {
                                    button7.setPressed(false);
                                }
                                eVar = this.g.f;
                                cVar2 = b.c.KEYCODE_RIGHT;
                                eVar.c(cVar2);
                                break;
                            case b.h.layout_arrow_up /* 2131230934 */:
                                Button button8 = this.l;
                                if (button8 != null) {
                                    button8.setPressed(false);
                                }
                                eVar = this.g.f;
                                cVar2 = b.c.KEYCODE_UP;
                                eVar.c(cVar2);
                                break;
                        }
                }
            }
            eVar = this.g.f;
            cVar2 = b.c.KEYCODE_DOWN;
            eVar.c(cVar2);
        }
        return false;
    }
}
