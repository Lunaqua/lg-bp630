package com.lge.media.launcher.syncstatus;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.support.v4.view.v;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.lge.media.launcher.b;
import com.lge.media.launcher.control.common.MainAct;
import com.lge.media.launcher.control.common.m;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class l extends v {
    private MainAct a;
    private LayoutInflater b;
    private View e;
    private View f;
    private View g;
    private LinearLayout i;
    private LinearLayout j;
    private LinearLayout k;
    private Button l;
    private Button m;
    private Button n;
    private Button o;
    private Button p;
    private Button q;
    private Button r;
    private Button s;
    private Button t;
    private Drawable u;
    private Drawable v;
    private LinearLayout c = null;
    private LinearLayout d = null;
    private int h = 2;
    private View.OnClickListener w = new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.l.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Handler handler;
            Runnable runnable;
            if (l.this.a.g() == 0) {
                if (l.this.a.c.getCurrentItem() == l.this.a.g()) {
                    handler = new Handler(Looper.getMainLooper());
                    runnable = new Runnable() { // from class: com.lge.media.launcher.syncstatus.l.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            l.this.i.sendAccessibilityEvent(32768);
                        }
                    };
                } else {
                    handler = new Handler(Looper.getMainLooper());
                    runnable = new Runnable() { // from class: com.lge.media.launcher.syncstatus.l.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            l.this.i.sendAccessibilityEvent(8);
                        }
                    };
                }
                handler.postDelayed(runnable, 500L);
                l.this.a.c.setCurrentItem(l.this.a.g());
            }
        }
    };
    private View.OnClickListener x = new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.l.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Handler handler;
            Runnable runnable;
            if (l.this.a.c.getCurrentItem() == l.this.a.h()) {
                handler = new Handler(Looper.getMainLooper());
                runnable = new Runnable() { // from class: com.lge.media.launcher.syncstatus.l.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        l.this.p.sendAccessibilityEvent(32768);
                    }
                };
            } else {
                handler = new Handler(Looper.getMainLooper());
                runnable = new Runnable() { // from class: com.lge.media.launcher.syncstatus.l.2.2
                    @Override // java.lang.Runnable
                    public void run() {
                        l.this.p.sendAccessibilityEvent(8);
                    }
                };
            }
            handler.postDelayed(runnable, 500L);
            l.this.a.c.setCurrentItem(l.this.a.h());
        }
    };
    private View.OnClickListener y = new View.OnClickListener() { // from class: com.lge.media.launcher.syncstatus.l.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Handler handler;
            Runnable runnable;
            if (l.this.a.c.getCurrentItem() == l.this.a.i()) {
                handler = new Handler(Looper.getMainLooper());
                runnable = new Runnable() { // from class: com.lge.media.launcher.syncstatus.l.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        l.this.t.sendAccessibilityEvent(32768);
                    }
                };
            } else {
                handler = new Handler(Looper.getMainLooper());
                runnable = new Runnable() { // from class: com.lge.media.launcher.syncstatus.l.3.2
                    @Override // java.lang.Runnable
                    public void run() {
                        l.this.t.sendAccessibilityEvent(8);
                    }
                };
            }
            handler.postDelayed(runnable, 500L);
            l.this.a.c.setCurrentItem(l.this.a.i());
        }
    };

    public l(MainAct mainAct) {
        this.a = mainAct;
        this.b = LayoutInflater.from(this.a);
        d();
    }

    private void a(View view) {
        view.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.syncstatus.l.5
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityEvent(View view2, AccessibilityEvent accessibilityEvent) {
                super.onInitializeAccessibilityEvent(view2, accessibilityEvent);
                accessibilityEvent.setClassName(Button.class.getName());
            }

            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfo);
                accessibilityNodeInfo.setClassName(Button.class.getName());
            }
        });
    }

    private void d() {
        e();
        f();
        g();
    }

    private void e() {
        com.lge.media.launcher.control.common.a.b("createCpView");
        if (this.a == null) {
            return;
        }
        this.e = this.b.inflate(b.j.cplist_toppage, (ViewGroup) null);
        this.d = (LinearLayout) this.e.findViewById(b.h.layout_premium_page_top);
        this.i = (LinearLayout) this.e.findViewById(b.h.layout_cp_page);
        this.l = (Button) this.e.findViewById(b.h.btn_cp_page);
        this.m = (Button) this.e.findViewById(b.h.btn_remote_page);
        this.n = (Button) this.e.findViewById(b.h.btn_sync_page);
        this.i.setOnClickListener(this.w);
        this.m.setOnClickListener(this.x);
        this.n.setOnClickListener(this.y);
        this.l.setTextColor(Color.rgb(221, 0, 84));
        MainAct mainAct = this.a;
        mainAct.a(new com.lge.media.launcher.cplist.d(mainAct, this.d));
        com.lge.media.launcher.c.a((View) this.l, false);
        this.i.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.syncstatus.l.4
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.setClassName(Button.class.getName());
                accessibilityNodeInfo.setContentDescription(l.this.a.getString(b.m.premium_selected));
            }
        });
    }

    private void f() {
        MainAct mainAct;
        com.lge.media.launcher.control.common.h aVar;
        com.lge.media.launcher.control.common.a.b("createRemoteView");
        if (this.a == null) {
            return;
        }
        this.f = this.b.inflate(b.j.remote_page, (ViewGroup) null);
        this.d = (LinearLayout) this.f.findViewById(b.h.layout_remote_page_top);
        this.j = (LinearLayout) this.f.findViewById(b.h.layout_cp_page);
        this.u = ((ImageView) this.e.findViewById(b.h.image_premium)).getDrawable();
        this.o = (Button) this.f.findViewById(b.h.btn_cp_page);
        this.p = (Button) this.f.findViewById(b.h.btn_remote_page);
        this.q = (Button) this.f.findViewById(b.h.btn_sync_page);
        this.j.setOnClickListener(this.w);
        this.p.setOnClickListener(this.x);
        this.q.setOnClickListener(this.y);
        StringBuilder sb = new StringBuilder();
        sb.append("ctx.isdemo : ");
        MainAct mainAct2 = this.a;
        sb.append(MainAct.h);
        com.lge.media.launcher.control.common.a.a(sb.toString());
        com.lge.media.launcher.control.common.a.b("cur Device Group in pagerAdapter : " + this.a.f.f());
        int f = this.a.f.f();
        if (f != 0) {
            if (f == 1) {
                mainAct = this.a;
                aVar = new com.lge.media.launcher.control.modelwise.b(mainAct, this.d);
            }
            com.lge.media.launcher.c.a((View) this.o, false);
            a(this.j);
        }
        mainAct = this.a;
        aVar = new com.lge.media.launcher.control.modelwise.a(mainAct, this.d);
        mainAct.a(aVar);
        com.lge.media.launcher.c.a((View) this.o, false);
        a(this.j);
    }

    private void g() {
        com.lge.media.launcher.control.common.a.b("createSmartShareView");
        a();
    }

    public void a() {
        String str;
        MainAct mainAct;
        m hVar;
        com.lge.media.launcher.control.common.a.c("getUIPageViewInfo");
        if (this.a == null) {
            return;
        }
        this.g = this.b.inflate(b.j.ui_sync_page, (ViewGroup) null);
        this.c = (LinearLayout) this.g.findViewById(b.h.layout_ui_sync_page_top);
        this.k = (LinearLayout) this.g.findViewById(b.h.layout_cp_page);
        this.v = ((ImageView) this.e.findViewById(b.h.image_premium)).getDrawable();
        this.r = (Button) this.g.findViewById(b.h.btn_cp_page);
        this.s = (Button) this.g.findViewById(b.h.btn_remote_page);
        this.t = (Button) this.g.findViewById(b.h.btn_sync_page);
        this.k.setOnClickListener(this.w);
        this.s.setOnClickListener(this.x);
        this.t.setOnClickListener(this.y);
        if (this.a.g() == 0) {
            this.r.setTextColor(Color.rgb(90, 90, 90));
            this.v.setAlpha(255);
            this.r.setEnabled(true);
        } else {
            this.r.setTextColor(Color.rgb(222, 222, 222));
            this.v.setAlpha(70);
            this.r.setEnabled(false);
        }
        if (this.a.f.l == 0) {
            if (this.a.f.e() == null || !this.a.f.e().contains("15y")) {
                mainAct = this.a;
                hVar = new h(mainAct, this.c);
            } else {
                mainAct = this.a;
                hVar = new g(mainAct, this.c);
            }
            mainAct.a(hVar);
            this.a.l();
            str = "Call home viewPage";
        } else if (this.a.f.l == 1) {
            MainAct mainAct2 = this.a;
            mainAct2.a(new b(mainAct2, this.c));
            this.a.l();
            str = "Call smartShare viewPage";
        } else if (this.a.f.l != 2) {
            if (this.a.f.l == 3) {
                MainAct mainAct3 = this.a;
                mainAct3.a(new i(mainAct3, this.c));
                this.a.l();
                str = "Call now play viewPage";
            }
            com.lge.media.launcher.c.a((View) this.r, false);
            a(this.k);
        } else {
            MainAct mainAct4 = this.a;
            mainAct4.a(new c(mainAct4, this.c));
            this.a.l();
            str = "Call inputDeivce viewPage";
        }
        com.lge.media.launcher.control.common.a.b(str);
        com.lge.media.launcher.c.a((View) this.r, false);
        a(this.k);
    }

    public void a(int i) {
        MainAct mainAct = this.a;
        if (mainAct == null) {
            return;
        }
        mainAct.f.l = i;
    }

    public void b() {
        com.lge.media.launcher.control.common.a.b("changeFillView");
        MainAct mainAct = this.a;
        if (mainAct == null) {
            return;
        }
        mainAct.l();
    }

    public void c() {
        this.e = null;
        this.f = null;
        this.g = null;
        this.a = null;
    }

    @Override // android.support.v4.view.v
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        ((ViewPager) viewGroup).removeView((View) obj);
    }

    @Override // android.support.v4.view.v
    public int getCount() {
        MainAct mainAct = this.a;
        if (mainAct == null || mainAct.f == null) {
            return 0;
        }
        this.h = this.a.f.F;
        return this.h;
    }

    @Override // android.support.v4.view.v
    public int getItemPosition(Object obj) {
        com.lge.media.launcher.control.common.a.b("getItemPosition");
        return -2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0045, code lost:
        if (r7.h > 2) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0047, code lost:
        r7.q.setTextColor(android.graphics.Color.rgb(90, 90, 90));
        r9 = r7.q;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0056, code lost:
        r7.q.setTextColor(android.graphics.Color.rgb(222, 222, 222));
        r9 = r7.q;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00a7, code lost:
        if (r7.h > 1) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0016, code lost:
        if (r9 != 2) goto L10;
     */
    @Override // android.support.v4.view.v
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object instantiateItem(android.view.ViewGroup r8, int r9) {
        /*
            r7 = this;
            java.lang.String r0 = "instantiateItem"
            com.lge.media.launcher.control.common.a.b(r0)
            com.lge.media.launcher.control.common.MainAct r0 = r7.a
            r1 = 0
            if (r0 != 0) goto Lb
            return r1
        Lb:
            r2 = 2
            r3 = 0
            r4 = 1
            r5 = 90
            r6 = 222(0xde, float:3.11E-43)
            if (r9 == 0) goto L65
            if (r9 == r4) goto L21
            if (r9 == r2) goto L1a
            goto Laa
        L1a:
            r7.a()
            android.view.View r1 = r7.g
            goto Laa
        L21:
            android.view.View r1 = r7.f
            int r9 = r0.g()
            if (r9 != 0) goto L1a
            android.widget.Button r9 = r7.o
            int r0 = android.graphics.Color.rgb(r5, r5, r5)
            r9.setTextColor(r0)
            android.graphics.drawable.Drawable r9 = r7.u
            r0 = 255(0xff, float:3.57E-43)
            r9.setAlpha(r0)
            android.widget.Button r9 = r7.o
            r9.setEnabled(r4)
            android.widget.LinearLayout r9 = r7.j
            r9.setEnabled(r4)
            int r9 = r7.h
            if (r9 <= r2) goto L56
        L47:
            android.widget.Button r9 = r7.q
            int r0 = android.graphics.Color.rgb(r5, r5, r5)
            r9.setTextColor(r0)
            android.widget.Button r9 = r7.q
        L52:
            r9.setEnabled(r4)
            goto Laa
        L56:
            android.widget.Button r9 = r7.q
            int r0 = android.graphics.Color.rgb(r6, r6, r6)
            r9.setTextColor(r0)
            android.widget.Button r9 = r7.q
        L61:
            r9.setEnabled(r3)
            goto Laa
        L65:
            int r9 = r0.g()
            if (r9 != 0) goto L89
            android.view.View r1 = r7.e
            int r9 = r7.h
            if (r9 <= r2) goto L7d
            android.widget.Button r9 = r7.n
            int r0 = android.graphics.Color.rgb(r5, r5, r5)
            r9.setTextColor(r0)
            android.widget.Button r9 = r7.n
            goto L52
        L7d:
            android.widget.Button r9 = r7.n
            int r0 = android.graphics.Color.rgb(r6, r6, r6)
            r9.setTextColor(r0)
            android.widget.Button r9 = r7.n
            goto L61
        L89:
            android.view.View r1 = r7.f
            android.widget.Button r9 = r7.o
            int r0 = android.graphics.Color.rgb(r6, r6, r6)
            r9.setTextColor(r0)
            android.graphics.drawable.Drawable r9 = r7.u
            r0 = 70
            r9.setAlpha(r0)
            android.widget.Button r9 = r7.o
            r9.setEnabled(r3)
            android.widget.LinearLayout r9 = r7.j
            r9.setEnabled(r3)
            int r9 = r7.h
            if (r9 <= r4) goto L56
            goto L47
        Laa:
            android.support.v4.view.ViewPager r8 = (android.support.v4.view.ViewPager) r8
            r8.addView(r1, r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.syncstatus.l.instantiateItem(android.view.ViewGroup, int):java.lang.Object");
    }

    @Override // android.support.v4.view.v
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
