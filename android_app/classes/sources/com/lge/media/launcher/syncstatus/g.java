package com.lge.media.launcher.syncstatus;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.ArrayList;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class g extends m {
    private static final int A = 3;
    private static final int B = 4;
    private static final int r = 0;
    private static final int s = 1;
    private static final int t = 2;
    private static int u = 0;
    private static final int x = 0;
    private static final int y = 1;
    private static final int z = 2;
    private int C;
    private RecyclerView D;
    private ArrayList<d> E;
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
    private int v;
    private int w;

    public g(Context context, ViewGroup viewGroup) {
        super(context, viewGroup);
        this.v = 0;
        this.w = 0;
        this.E = new ArrayList<>();
        this.b = viewGroup;
        a(viewGroup);
        a();
    }

    private int a(String str) {
        if (str.equals("MOVIE")) {
            return 0;
        }
        return str.equals("PHOTO") ? 1 : 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x013f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(int r9) {
        /*
            Method dump skipped, instructions count: 369
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.syncstatus.g.a(int):void");
    }

    private boolean d() {
        if (this.g.s != null && this.g.s.p != null && this.g.s.p.size() == 3 && this.g.s.p.get(0).b.equalsIgnoreCase("MUSIC") && this.g.s.p.get(1).b.equalsIgnoreCase("MOVIE") && this.g.s.p.get(2).b.equalsIgnoreCase("PHOTO")) {
            this.f.setVisibility(4);
            return true;
        }
        return false;
    }

    @Override // com.lge.media.launcher.control.common.m
    protected void a() {
        com.lge.media.launcher.control.common.a.a("set Listeners");
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.p.setOnClickListener(this);
    }

    @Override // com.lge.media.launcher.control.common.m
    protected void a(ViewGroup viewGroup) {
        com.lge.media.launcher.control.common.a.a("home initView");
        this.a = (ViewGroup) viewGroup.findViewById(b.h.layout_ui_sync_page_root);
        View inflate = LayoutInflater.from(this.g).inflate(b.j.sync_page_main, (ViewGroup) null, true);
        ViewGroup viewGroup2 = this.a;
        viewGroup2.addView(inflate, viewGroup2.getChildCount(), this.a.getLayoutParams());
        this.g.g = this.g.f.p();
        if (this.g.g == null) {
            com.lge.media.launcher.control.common.a.a("mainsyncpage roapMan is null");
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
        this.D = (RecyclerView) inflate.findViewById(b.h.list_contents);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.g);
        linearLayoutManager.b(1);
        this.D.setLayoutManager(linearLayoutManager);
    }

    @Override // com.lge.media.launcher.control.common.m
    public void a(k kVar) {
        com.lge.media.launcher.control.common.a.a("home fillViews");
        this.d.setVisibility(4);
        this.e.setVisibility(0);
        this.C = this.g.s.p.size();
        com.lge.media.launcher.control.common.a.c("itemListSize : " + this.C);
        a(u);
        MainAct mainAct = this.g;
        if (MainAct.h) {
            com.lge.media.launcher.control.common.a.a("demo fillViews");
            this.c.setVisibility(0);
        }
        MainAct mainAct2 = this.g;
        if (MainAct.h || this.g.g == null || !this.g.s.e.equals("0")) {
            return;
        }
        com.lge.media.launcher.control.common.a.c(" Auto popup view ");
        this.h.setVisibility(8);
        this.i.setVisibility(0);
        this.j.setVisibility(8);
    }

    @Override // com.lge.media.launcher.control.common.m
    public void b() {
        com.lge.media.launcher.control.common.a.a("home hideViews");
        this.d.setVisibility(0);
        this.e.setVisibility(4);
        com.lge.media.launcher.c.a(this.q, 100);
    }

    @Override // com.lge.media.launcher.control.common.m
    public void c() {
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    @Override // android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onClick(android.view.View r5) {
        /*
            r4 = this;
            java.lang.String r0 = "[MainSyncPage]onclick!"
            com.lge.media.launcher.control.common.a.b(r0)
            com.lge.media.launcher.control.common.MainAct r0 = r4.g
            boolean r0 = com.lge.media.launcher.control.common.MainAct.h
            r1 = 0
            r2 = 2
            r3 = 1
            if (r0 != 0) goto L72
            int r5 = r5.getId()
            switch(r5) {
                case 2131230762: goto L4d;
                case 2131230763: goto L34;
                case 2131230764: goto L1a;
                default: goto L15;
            }
        L15:
            switch(r5) {
                case 2131230851: goto L34;
                case 2131230852: goto L1a;
                case 2131230853: goto L4d;
                default: goto L18;
            }
        L18:
            goto L91
        L1a:
            com.lge.media.launcher.control.common.f.d()
            boolean r5 = r4.d()
            if (r5 == 0) goto L80
            com.lge.media.launcher.control.common.MainAct r5 = r4.g
            com.lge.UDAP.ROAP.b r5 = r5.g
            com.lge.UDAP.ROAP.a.b r5 = r5.b
            com.lge.media.launcher.control.common.MainAct r0 = r4.g
            com.lge.media.launcher.syncstatus.k r0 = r0.s
            java.lang.String r0 = r0.a
            r1 = 3
            r5.e(r0, r1)
            goto L65
        L34:
            com.lge.media.launcher.control.common.f.d()
            boolean r5 = r4.d()
            if (r5 == 0) goto L87
            com.lge.media.launcher.control.common.MainAct r5 = r4.g
            com.lge.UDAP.ROAP.b r5 = r5.g
            com.lge.UDAP.ROAP.a.b r5 = r5.b
            com.lge.media.launcher.control.common.MainAct r0 = r4.g
            com.lge.media.launcher.syncstatus.k r0 = r0.s
            java.lang.String r0 = r0.a
            r5.e(r0, r3)
            goto L65
        L4d:
            com.lge.media.launcher.control.common.f.d()
            boolean r5 = r4.d()
            if (r5 == 0) goto L8e
            com.lge.media.launcher.control.common.MainAct r5 = r4.g
            com.lge.UDAP.ROAP.b r5 = r5.g
            com.lge.UDAP.ROAP.a.b r5 = r5.b
            com.lge.media.launcher.control.common.MainAct r0 = r4.g
            com.lge.media.launcher.syncstatus.k r0 = r0.s
            java.lang.String r0 = r0.a
            r5.e(r0, r2)
        L65:
            com.lge.media.launcher.control.common.MainAct r5 = r4.g
            com.lge.media.launcher.control.common.e r5 = r5.f
            android.os.CountDownTimer r5 = r5.ag
            r5.start()
            r4.b()
            goto L91
        L72:
            int r5 = r5.getId()
            switch(r5) {
                case 2131230762: goto L8b;
                case 2131230763: goto L84;
                case 2131230764: goto L7d;
                default: goto L79;
            }
        L79:
            switch(r5) {
                case 2131230851: goto L84;
                case 2131230852: goto L7d;
                case 2131230853: goto L8b;
                default: goto L7c;
            }
        L7c:
            goto L91
        L7d:
            com.lge.media.launcher.control.common.f.d()
        L80:
            r4.a(r2)
            goto L91
        L84:
            com.lge.media.launcher.control.common.f.d()
        L87:
            r4.a(r1)
            goto L91
        L8b:
            com.lge.media.launcher.control.common.f.d()
        L8e:
            r4.a(r3)
        L91:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.syncstatus.g.onClick(android.view.View):void");
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        Button button = this.k;
        if (button != null) {
            ((BitmapDrawable) button.getBackground()).getBitmap().recycle();
        }
        Button button2 = this.l;
        if (button2 != null) {
            ((BitmapDrawable) button2.getBackground()).getBitmap().recycle();
        }
        Button button3 = this.m;
        if (button3 != null) {
            ((BitmapDrawable) button3.getBackground()).getBitmap().recycle();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
