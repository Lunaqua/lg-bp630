package com.lge.media.launcher.syncstatus;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.lge.media.launcher.b;
import com.lge.media.launcher.control.common.MainAct;
import com.lge.media.launcher.control.common.m;
import com.lge.media.launcher.syncstatus.f;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class h extends m {
    private static final int v = 0;
    private static final int w = 1;
    private static final int x = 2;
    private static int y;
    private ViewGroup a;
    private ViewGroup b;
    private RelativeLayout c;
    private LinearLayout d;
    private LinearLayout e;
    private LinearLayout f;
    private LinearLayout h;
    private LinearLayout i;
    private LinearLayout j;
    private Button k;
    private Button l;
    private Button m;
    private Button n;
    private Button o;
    private Button p;
    private ProgressBar q;
    private int r;
    private String s;
    private RecyclerView t;
    private ArrayList<d> u;

    public h(Context context, ViewGroup viewGroup) {
        super(context, viewGroup);
        this.u = new ArrayList<>();
        this.b = viewGroup;
        a(viewGroup);
        a();
    }

    private void a(int i) {
        y = i;
        if (i == 0) {
            this.k.setBackgroundResource(b.g.icon_movie_s);
            this.l.setBackgroundResource(b.g.icon_picture);
            this.m.setBackgroundResource(b.g.icon_music);
            this.k.setSelected(true);
            this.l.setSelected(false);
        } else if (i != 1) {
            if (i != 2) {
                return;
            }
            this.k.setBackgroundResource(b.g.icon_movie);
            this.l.setBackgroundResource(b.g.icon_picture);
            this.m.setBackgroundResource(b.g.icon_music_s);
            this.k.setSelected(false);
            this.l.setSelected(false);
            this.m.setSelected(true);
            return;
        } else {
            this.k.setBackgroundResource(b.g.icon_movie);
            this.l.setBackgroundResource(b.g.icon_picture_s);
            this.m.setBackgroundResource(b.g.icon_music);
            this.k.setSelected(false);
            this.l.setSelected(true);
        }
        this.m.setSelected(false);
    }

    @Override // com.lge.media.launcher.control.common.m
    protected void a() {
        Log.e(com.lge.media.launcher.control.common.d.a, "set Listeners");
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.p.setOnClickListener(this);
    }

    @Override // com.lge.media.launcher.control.common.m
    protected void a(ViewGroup viewGroup) {
        Log.e(com.lge.media.launcher.control.common.d.a, "home initView");
        this.a = (ViewGroup) viewGroup.findViewById(b.h.layout_ui_sync_page_root);
        View inflate = LayoutInflater.from(this.g).inflate(b.j.sync_page_main_for_13, (ViewGroup) null, true);
        ViewGroup viewGroup2 = this.a;
        viewGroup2.addView(inflate, viewGroup2.getChildCount(), this.a.getLayoutParams());
        this.g.g = this.g.f.p();
        if (this.g.g == null) {
            Log.e(com.lge.media.launcher.control.common.d.a, "mainsyncpage roapMan is null");
        }
        View findViewById = inflate.findViewById(b.h.textview_linked_device_title);
        findViewById.setContentDescription(this.g.getString(b.m.linked_deivce_icon_list) + this.g.getString(b.m.label_title) + 2);
        this.d = (LinearLayout) inflate.findViewById(b.h.progress_waiting);
        this.q = (ProgressBar) inflate.findViewById(b.h.progressbar_loading);
        this.e = (LinearLayout) inflate.findViewById(b.h.layout_ui_sync_page_list);
        this.f = (LinearLayout) inflate.findViewById(b.h.layout_no_linked_device);
        this.c = (RelativeLayout) inflate.findViewById(b.h.layout_contents_list);
        this.k = (Button) inflate.findViewById(b.h.btn_sync_main_movie);
        this.l = (Button) inflate.findViewById(b.h.btn_sync_main_photo);
        this.m = (Button) inflate.findViewById(b.h.btn_sync_main_music);
        this.n = (Button) inflate.findViewById(b.h.btn_auto_sync_main_movie);
        this.o = (Button) inflate.findViewById(b.h.btn_auto_sync_main_photo);
        this.p = (Button) inflate.findViewById(b.h.btn_auto_sync_main_music);
        this.h = (LinearLayout) inflate.findViewById(b.h.layout_home_btn);
        this.i = (LinearLayout) inflate.findViewById(b.h.layout_auto_popup_btn);
        this.j = (LinearLayout) inflate.findViewById(b.h.layout_linked_device_text);
        this.t = (RecyclerView) inflate.findViewById(b.h.list_contents);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.g);
        linearLayoutManager.b(1);
        this.t.setLayoutManager(linearLayoutManager);
        a(y);
    }

    @Override // com.lge.media.launcher.control.common.m
    public void a(k kVar) {
        Log.e(com.lge.media.launcher.control.common.d.a, "home fillViews");
        this.d.setVisibility(4);
        int i = 0;
        this.e.setVisibility(0);
        this.r = this.g.s.p.size();
        Log.i(com.lge.media.launcher.control.common.d.a, "itemListSize : " + this.r);
        MainAct mainAct = this.g;
        if (MainAct.h) {
            Log.e(com.lge.media.launcher.control.common.d.a, "demo fillViews");
            this.c.setVisibility(0);
        }
        this.u.clear();
        MainAct mainAct2 = this.g;
        if (MainAct.h || this.g.g == null) {
            return;
        }
        if (this.g.s.e.equals("0")) {
            Log.i(com.lge.media.launcher.control.common.d.a, " Auto popup view ");
            this.h.setVisibility(8);
            this.i.setVisibility(0);
            this.j.setVisibility(8);
            return;
        }
        int i2 = this.r;
        if (i2 == 3) {
            Log.i(com.lge.media.launcher.control.common.d.a, " no linked device information ");
            this.f.setVisibility(0);
            this.c.setVisibility(4);
        } else if (i2 > 3) {
            Log.i(com.lge.media.launcher.control.common.d.a, " exist linked device information ");
            this.f.setVisibility(4);
            this.c.setVisibility(0);
            Log.i(com.lge.media.launcher.control.common.d.a, "size : " + this.r);
            while (true) {
                int i3 = this.r;
                if (i >= i3) {
                    break;
                }
                if (i != 3 && i != 4 && i != 5) {
                    if (i != 6) {
                        i++;
                    } else if (i3 <= 7) {
                        this.g.s.p.get(i).b = "Device List";
                    }
                }
                this.u.add(this.g.s.p.get(i));
                i++;
            }
        }
        this.t.setAdapter(new f(this.g, b.j.main_sync_item, this.u, new f.b() { // from class: com.lge.media.launcher.syncstatus.h.1
            @Override // com.lge.media.launcher.syncstatus.f.b
            public void a(View view, int i4, long j) {
                MainAct mainAct3 = h.this.g;
                if (MainAct.h) {
                    return;
                }
                d dVar = (d) h.this.u.get(i4);
                com.lge.media.launcher.control.common.f.d();
                h.this.g.g.b.e(h.this.g.s.a, Integer.parseInt(dVar.a));
                com.lge.media.launcher.control.common.a.b("type " + h.this.g.s.a + " , " + dVar.a);
                com.lge.media.launcher.control.common.a.c("Timer start");
                h.this.g.f.ag.start();
                h.this.b();
            }
        }, Boolean.valueOf(this.g.f.R), this.g.f.U));
    }

    @Override // com.lge.media.launcher.control.common.m
    public void b() {
        Log.e(com.lge.media.launcher.control.common.d.a, "home hideViews");
        this.d.setVisibility(0);
        this.e.setVisibility(4);
        com.lge.media.launcher.c.a(this.q, 100);
    }

    @Override // com.lge.media.launcher.control.common.m
    public void c() {
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0063  */
    @Override // android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onClick(android.view.View r7) {
        /*
            r6 = this;
            java.lang.String r0 = "LGMagic"
            java.lang.String r1 = "onclick!"
            android.util.Log.d(r0, r1)
            com.lge.media.launcher.control.common.MainAct r1 = r6.g
            boolean r1 = com.lge.media.launcher.control.common.MainAct.h
            if (r1 != 0) goto Le8
            int r7 = r7.getId()
            r1 = 1
            r2 = 2
            java.lang.String r3 = "Timer start"
            java.lang.String r4 = "type "
            switch(r7) {
                case 2131230762: goto La6;
                case 2131230763: goto L63;
                case 2131230764: goto L1f;
                default: goto L1a;
            }
        L1a:
            switch(r7) {
                case 2131230851: goto L63;
                case 2131230852: goto L1f;
                case 2131230853: goto La6;
                default: goto L1d;
            }
        L1d:
            goto Leb
        L1f:
            com.lge.media.launcher.control.common.f.d()
            com.lge.media.launcher.control.common.MainAct r7 = r6.g
            com.lge.UDAP.ROAP.b r7 = r7.g
            com.lge.UDAP.ROAP.a.b r7 = r7.b
            com.lge.media.launcher.control.common.MainAct r1 = r6.g
            com.lge.media.launcher.syncstatus.k r1 = r1.s
            java.lang.String r1 = r1.a
            r5 = 3
            r7.e(r1, r5)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r4)
            com.lge.media.launcher.control.common.MainAct r1 = r6.g
            com.lge.media.launcher.syncstatus.k r1 = r1.s
            java.lang.String r1 = r1.a
            r7.append(r1)
            java.lang.String r1 = " , 3"
            r7.append(r1)
            java.lang.String r7 = r7.toString()
            android.util.Log.d(r0, r7)
            android.util.Log.i(r0, r3)
            com.lge.media.launcher.control.common.MainAct r7 = r6.g
            com.lge.media.launcher.control.common.e r7 = r7.f
            android.os.CountDownTimer r7 = r7.ag
            r7.start()
            r6.b()
            r6.a(r2)
            goto Leb
        L63:
            com.lge.media.launcher.control.common.f.d()
            com.lge.media.launcher.control.common.MainAct r7 = r6.g
            com.lge.UDAP.ROAP.b r7 = r7.g
            com.lge.UDAP.ROAP.a.b r7 = r7.b
            com.lge.media.launcher.control.common.MainAct r2 = r6.g
            com.lge.media.launcher.syncstatus.k r2 = r2.s
            java.lang.String r2 = r2.a
            r7.e(r2, r1)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r4)
            com.lge.media.launcher.control.common.MainAct r1 = r6.g
            com.lge.media.launcher.syncstatus.k r1 = r1.s
            java.lang.String r1 = r1.a
            r7.append(r1)
            java.lang.String r1 = " , 1"
            r7.append(r1)
            java.lang.String r7 = r7.toString()
            android.util.Log.d(r0, r7)
            android.util.Log.i(r0, r3)
            com.lge.media.launcher.control.common.MainAct r7 = r6.g
            com.lge.media.launcher.control.common.e r7 = r7.f
            android.os.CountDownTimer r7 = r7.ag
            r7.start()
            r6.b()
            r7 = 0
            r6.a(r7)
            goto Leb
        La6:
            com.lge.media.launcher.control.common.f.d()
            com.lge.media.launcher.control.common.MainAct r7 = r6.g
            com.lge.UDAP.ROAP.b r7 = r7.g
            com.lge.UDAP.ROAP.a.b r7 = r7.b
            com.lge.media.launcher.control.common.MainAct r5 = r6.g
            com.lge.media.launcher.syncstatus.k r5 = r5.s
            java.lang.String r5 = r5.a
            r7.e(r5, r2)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r4)
            com.lge.media.launcher.control.common.MainAct r2 = r6.g
            com.lge.media.launcher.syncstatus.k r2 = r2.s
            java.lang.String r2 = r2.a
            r7.append(r2)
            java.lang.String r2 = " , 2"
            r7.append(r2)
            java.lang.String r7 = r7.toString()
            android.util.Log.d(r0, r7)
            android.util.Log.i(r0, r3)
            com.lge.media.launcher.control.common.MainAct r7 = r6.g
            com.lge.media.launcher.control.common.e r7 = r7.f
            android.os.CountDownTimer r7 = r7.ag
            r7.start()
            r6.b()
            r6.a(r1)
            goto Leb
        Le8:
            com.lge.media.launcher.control.common.f.d()
        Leb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.syncstatus.h.onClick(android.view.View):void");
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    protected void setAccessibilityFocus(final View view) {
        Executors.newSingleThreadScheduledExecutor().schedule(new Runnable() { // from class: com.lge.media.launcher.syncstatus.h.2
            @Override // java.lang.Runnable
            public void run() {
                view.sendAccessibilityEvent(8);
            }
        }, 100L, TimeUnit.MILLISECONDS);
    }
}
