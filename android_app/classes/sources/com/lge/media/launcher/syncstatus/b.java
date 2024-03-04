package com.lge.media.launcher.syncstatus;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lge.UDAP.ROAP.a.b;
import com.lge.media.launcher.b;
import com.lge.media.launcher.control.common.MainAct;
import com.lge.media.launcher.control.common.m;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class b extends m {
    private ImageView A;
    private ImageView B;
    private ImageView C;
    private ImageView D;
    private ImageView E;
    private ImageView F;
    private ImageView G;
    private ImageView H;
    private ListView I;
    private boolean J;
    private boolean K;
    private int L;
    private String M;
    View.OnClickListener a;
    View.OnClickListener b;
    View.OnClickListener c;
    private ViewGroup d;
    private LinearLayout e;
    private LinearLayout f;
    private LinearLayout h;
    private LinearLayout i;
    private LinearLayout j;
    private LinearLayout k;
    private LinearLayout l;
    private RelativeLayout m;
    private RelativeLayout n;
    private FrameLayout o;
    private ImageView p;
    private ImageView q;
    private Button r;
    private TextView s;
    private Button t;
    private TextView u;
    private TextView v;
    private ImageView w;
    private ImageView x;
    private ImageView y;
    private ImageView z;

    public b(Context context, ViewGroup viewGroup) {
        super(context, viewGroup);
        this.J = true;
        this.K = true;
        this.a = new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.b.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("MOVIE".equals(b.this.g.f.r.d)) {
                    return;
                }
                com.lge.media.launcher.control.common.f.d();
                com.lge.media.launcher.control.common.a.b("movie onclick!");
                com.lge.media.launcher.control.common.a.b("type " + b.this.g.s.a + " , 0");
                b.this.g.g.b.d(b.this.g.s.a, 0);
                com.lge.media.launcher.control.common.a.c("Timer start");
                b.this.g.f.ag.start();
                b.this.b();
            }
        };
        this.b = new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.b.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("PHOTO".equals(b.this.g.f.r.d)) {
                    return;
                }
                com.lge.media.launcher.control.common.f.d();
                com.lge.media.launcher.control.common.a.b("photo onclick!");
                com.lge.media.launcher.control.common.a.b("type " + b.this.g.s.a + " , 1");
                b.this.g.g.b.d(b.this.g.s.a, 1);
                com.lge.media.launcher.control.common.a.c("Timer start");
                b.this.g.f.ag.start();
                b.this.b();
            }
        };
        this.c = new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.b.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("MUSIC".equals(b.this.g.f.r.d)) {
                    return;
                }
                com.lge.media.launcher.control.common.f.d();
                com.lge.media.launcher.control.common.a.b("music onclick!");
                com.lge.media.launcher.control.common.a.b("type " + b.this.g.s.a + " , 2");
                b.this.g.g.b.d(b.this.g.s.a, 2);
                com.lge.media.launcher.control.common.a.c("Timer start");
                b.this.g.f.ag.start();
                b.this.b();
            }
        };
        a(viewGroup);
        a();
        b();
    }

    private void d() {
        TextView textView;
        String str;
        if (this.g.f.r.h.equals("Audio")) {
            this.u.setText(b.m.audio);
            textView = this.u;
            str = this.g.getString(b.m.audio) + this.g.getString(b.m.label_title) + 2;
        } else {
            this.u.setText(this.g.f.r.h);
            textView = this.u;
            str = this.g.f.r.h + this.g.getString(b.m.label_title) + 2;
        }
        textView.setContentDescription(str);
    }

    @Override // com.lge.media.launcher.control.common.m
    protected void a() {
        com.lge.media.launcher.control.common.a.a("set Listeners");
        this.o.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.b.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.lge.media.launcher.control.common.f.d();
                com.lge.media.launcher.control.common.a.b("linkedDevice onclick");
                b.this.g.g.b.e(b.this.g.s.a, 0);
                b.this.g.e.setEnabled(false);
            }
        });
        this.w.setOnClickListener(this.a);
        this.x.setOnClickListener(this.c);
        this.y.setOnClickListener(this.b);
        this.z.setOnClickListener(this.a);
        this.A.setOnClickListener(this.c);
        this.B.setOnClickListener(this.b);
        this.C.setOnClickListener(this.a);
        this.D.setOnClickListener(this.c);
        this.E.setOnClickListener(this.b);
        this.I.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lge.media.launcher.syncstatus.b.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                com.lge.media.launcher.control.common.f.d();
                d dVar = b.this.g.s.p.get(i);
                int parseInt = Integer.parseInt(dVar.a);
                if (dVar.d.equals("UPFOLDER")) {
                    b.this.g.f.a(b.c.KEYCODE_BACK);
                } else if (dVar.d.equals("FOLDER")) {
                    b.this.g.g.b.b(b.this.g.s.a, parseInt);
                } else if (dVar.d.equals("SUBTITLE")) {
                    com.lge.media.launcher.control.common.a.b("sync select");
                    b.this.g.g.b.f(b.this.g.s.a, parseInt);
                } else {
                    com.lge.media.launcher.control.common.a.b("sync movie/photo/music select " + parseInt);
                    b.this.g.g.b.c(b.this.g.s.a, parseInt);
                    b.this.g.b.b();
                }
            }
        });
        this.I.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.lge.media.launcher.syncstatus.b.3
            private int b;

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                this.b = i3;
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
                int parseInt;
                if (i != 0) {
                    return;
                }
                if (b.this.g.f.r.d.equals("MUSIC") && b.this.g.f.r.b.equals("DISC")) {
                    return;
                }
                if (absListView.getLastVisiblePosition() == this.b - 1 || absListView.getFirstVisiblePosition() == 0) {
                    if (absListView.getFirstVisiblePosition() == 0) {
                        if (b.this.J) {
                            com.lge.media.launcher.control.common.a.c("First scroll, don't any action");
                            b.this.J = false;
                            return;
                        }
                        parseInt = Integer.parseInt(b.this.g.s.f) - 1;
                    } else if (b.this.K) {
                        com.lge.media.launcher.control.common.a.c("First scroll, don't any action");
                        b.this.K = false;
                        return;
                    } else {
                        parseInt = Integer.parseInt(b.this.g.s.g) + 1;
                    }
                    if (parseInt == 0 || parseInt - 1 == Integer.parseInt(b.this.g.f.r.e) || b.this.g.f.B) {
                        return;
                    }
                    com.lge.media.launcher.control.common.a.c("Request more list information");
                    b.this.b();
                    b.this.g.f.B = true;
                    if (parseInt < Integer.parseInt(b.this.g.s.f)) {
                        com.lge.media.launcher.control.common.a.c("List up");
                        b.this.J = true;
                    } else {
                        com.lge.media.launcher.control.common.a.c("List down");
                        b.this.K = true;
                    }
                    com.lge.media.launcher.control.common.a.c("Request item index : " + parseInt);
                    b.this.g.g.b.e(parseInt);
                    com.lge.media.launcher.control.common.a.c("Timer start");
                    b.this.g.f.ag.start();
                }
            }
        });
        this.r.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.b.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.lge.media.launcher.control.common.f.d();
                if (!b.this.g.f.r.b.equals("DISC")) {
                    b.this.g.f.a(b.c.KEYCODE_GREEN);
                    com.lge.media.launcher.control.common.a.c("Timer start");
                    b.this.g.f.ag.start();
                    b.this.g.b.b();
                    return;
                }
                b.this.g.d.a(3);
                b.this.g.c.getAdapter().notifyDataSetChanged();
                b.this.g.c.setCurrentItem(b.this.g.i());
                b.this.g.b.b();
                b.this.g.b.a(b.this.g.f.r);
            }
        });
        this.v.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.b.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.lge.media.launcher.control.common.f.d();
                if (!b.this.g.f.r.b.equals("DISC")) {
                    b.this.g.f.a(b.c.KEYCODE_GREEN);
                    com.lge.media.launcher.control.common.a.c("Timer start");
                    b.this.g.f.ag.start();
                    b.this.g.b.b();
                    return;
                }
                b.this.g.d.a(3);
                b.this.g.c.getAdapter().notifyDataSetChanged();
                b.this.g.c.setCurrentItem(b.this.g.i());
                b.this.g.b.b();
                b.this.g.b.a(b.this.g.f.r);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x01d5  */
    @Override // com.lge.media.launcher.control.common.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void a(android.view.ViewGroup r5) {
        /*
            Method dump skipped, instructions count: 480
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.syncstatus.b.a(android.view.ViewGroup):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0247 A[LOOP:0: B:49:0x0243->B:51:0x0247, LOOP_END] */
    @Override // com.lge.media.launcher.control.common.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.lge.media.launcher.syncstatus.k r18) {
        /*
            Method dump skipped, instructions count: 639
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.syncstatus.b.a(com.lge.media.launcher.syncstatus.k):void");
    }

    @Override // com.lge.media.launcher.control.common.m
    public void b() {
        com.lge.media.launcher.control.common.a.b("file browser hideViews");
        this.e.setVisibility(0);
        this.f.setVisibility(8);
        this.h.setVisibility(8);
        this.n.setVisibility(8);
    }

    @Override // com.lge.media.launcher.control.common.m
    public void c() {
        if (this.g.f.K.length() > 1) {
            this.m.setVisibility(8);
            this.n.setVisibility(0);
            this.v.setText(this.g.f.K);
            return;
        }
        this.m.setVisibility(0);
        this.n.setVisibility(8);
        d();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id;
        MainAct mainAct = this.g;
        if (!MainAct.h && (id = view.getId()) != 2131230913 && id != 2131231107) {
            switch (id) {
                case b.h.btn_mstab_movie /* 2131230799 */:
                case b.h.btn_mstab_music /* 2131230800 */:
                case b.h.btn_mstab_photo /* 2131230801 */:
                case b.h.btn_mtab_movie /* 2131230802 */:
                case b.h.btn_mtab_music /* 2131230803 */:
                case b.h.btn_mtab_photo /* 2131230804 */:
                    break;
                default:
                    switch (id) {
                        case b.h.btn_ptab_movie /* 2131230834 */:
                        case b.h.btn_ptab_music /* 2131230835 */:
                        case b.h.btn_ptab_photo /* 2131230836 */:
                            break;
                        default:
                            return;
                    }
            }
        }
        com.lge.media.launcher.control.common.f.d();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
