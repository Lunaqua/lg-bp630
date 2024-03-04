package com.lge.media.launcher.syncstatus;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lge.UDAP.ROAP.a.b;
import com.lge.media.launcher.b;
import com.lge.media.launcher.control.common.MainAct;
import com.lge.media.launcher.control.common.m;
import java.lang.reflect.Array;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class i extends m {
    private ViewGroup a;
    private ViewGroup b;
    private LinearLayout c;
    private LinearLayout d;
    private Button e;
    private Button f;
    private Button h;
    private Button i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private ImageView o;
    private ImageView p;
    private CustomSeekBar q;
    private boolean r;
    private int[][] s;
    private int t;
    private int u;

    public i(Context context, ViewGroup viewGroup) {
        super(context, viewGroup);
        this.r = false;
        this.s = (int[][]) Array.newInstance(int.class, 13, 2);
        this.t = 15;
        this.u = 5;
        this.b = viewGroup;
        a(viewGroup);
        a();
        b();
    }

    private void d() {
        this.r = true;
        if (this.g.f.T.equals("XHDPI") || this.g.f.T.equals("HDPI")) {
            this.t = (int) (this.t * this.g.f.U);
            this.u = (int) (this.u * this.g.f.U);
        }
        this.j.setTextSize((int) (this.g.f.U * 18.0f));
        this.k.setTextSize((int) (this.g.f.U * 23.0f));
        this.l.setTextSize((int) (this.g.f.U * 18.0f));
        this.m.setTextSize((int) (this.g.f.U * 14.0f));
        this.n.setTextSize((int) (this.g.f.U * 14.0f));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (this.s[3][0] * this.g.f.U), (int) (this.s[3][1] * this.g.f.U));
        layoutParams.addRule(3, b.h.btn_file_list);
        layoutParams.addRule(13);
        layoutParams.setMargins(0, 10, 0, 0);
        this.o.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((int) (this.s[4][0] * this.g.f.U), (int) (this.s[4][1] * this.g.f.U));
        layoutParams2.addRule(3, b.h.btn_file_list);
        layoutParams2.addRule(11);
        layoutParams2.setMargins(0, (int) ((this.s[3][1] * this.g.f.U) / 2.0f), ((this.g.f.X * 720) / 1280) / 4, 0);
        this.p.setLayoutParams(layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams((int) (this.s[0][0] * this.g.f.U), (int) (this.s[0][1] * this.g.f.U));
        layoutParams3.addRule(11);
        layoutParams3.setMargins(0, this.u, this.t, 0);
        this.e.setLayoutParams(layoutParams3);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams((int) (this.s[1][0] * this.g.f.U), (int) (this.s[1][1] * this.g.f.U));
        layoutParams4.addRule(13);
        this.f.setLayoutParams(layoutParams4);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams((int) (this.s[2][0] * this.g.f.U), (int) (this.s[2][1] * this.g.f.U));
        layoutParams5.addRule(0, b.h.btn_now_player_play_pause);
        layoutParams5.addRule(15);
        this.h.setLayoutParams(layoutParams5);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams((int) (this.s[2][0] * this.g.f.U), (int) (this.s[2][1] * this.g.f.U));
        layoutParams6.addRule(1, b.h.btn_now_player_play_pause);
        layoutParams6.addRule(15);
        this.i.setLayoutParams(layoutParams6);
    }

    private void getbtnSize() {
        this.s[0][0] = this.e.getBackground().getIntrinsicWidth();
        this.s[0][1] = this.e.getBackground().getIntrinsicHeight();
        this.s[1][0] = this.f.getBackground().getIntrinsicWidth();
        this.s[1][1] = this.f.getBackground().getIntrinsicHeight();
        this.s[2][0] = this.h.getBackground().getIntrinsicWidth();
        this.s[2][1] = this.h.getBackground().getIntrinsicHeight();
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), b.g.bg_player_thumb_dafault);
        this.s[3][0] = decodeResource.getWidth();
        this.s[3][1] = decodeResource.getHeight();
        Bitmap decodeResource2 = BitmapFactory.decodeResource(getResources(), b.g.icon_player_music);
        this.s[4][0] = decodeResource2.getWidth();
        this.s[4][1] = decodeResource2.getHeight();
    }

    public String a(String str) {
        StringBuilder sb;
        String sb2;
        int parseInt = Integer.parseInt(str);
        int i = parseInt / 60;
        int i2 = parseInt % 60;
        com.lge.media.launcher.control.common.a.a("minutes : " + i + " , seconds : " + i2);
        if (i == 0) {
            sb2 = "00:";
        } else {
            if (i < 10) {
                sb = new StringBuilder();
                sb.append("0");
            } else {
                sb = new StringBuilder();
            }
            sb.append(Integer.toString(i));
            sb.append(":");
            sb2 = sb.toString();
        }
        if (i2 == 0) {
            return sb2 + "00";
        } else if (i2 >= 10) {
            return sb2 + Integer.toString(i2);
        } else {
            return sb2 + "0" + Integer.toString(i2);
        }
    }

    @Override // com.lge.media.launcher.control.common.m
    protected void a() {
        com.lge.media.launcher.control.common.a.b("Set Listeners");
        this.e.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.f.setOnClickListener(this);
    }

    @Override // com.lge.media.launcher.control.common.m
    protected void a(ViewGroup viewGroup) {
        com.lge.media.launcher.control.common.a.b("Now play initView");
        this.a = (ViewGroup) viewGroup.findViewById(b.h.layout_ui_sync_page_root);
        View inflate = LayoutInflater.from(this.g).inflate(b.j.sync_now_play, (ViewGroup) null, true);
        ViewGroup viewGroup2 = this.a;
        viewGroup2.addView(inflate, viewGroup2.getChildCount(), this.a.getLayoutParams());
        this.c = (LinearLayout) inflate.findViewById(b.h.progress_waiting);
        this.d = (LinearLayout) inflate.findViewById(b.h.layout_now_play);
        this.e = (Button) inflate.findViewById(b.h.btn_file_list);
        this.f = (Button) inflate.findViewById(b.h.btn_now_player_play_pause);
        this.h = (Button) inflate.findViewById(b.h.btn_now_player_prev);
        this.i = (Button) inflate.findViewById(b.h.btn_now_player_next);
        this.j = (TextView) inflate.findViewById(b.h.text_now_play_artist);
        this.k = (TextView) inflate.findViewById(b.h.text_now_play_title);
        this.l = (TextView) inflate.findViewById(b.h.text_now_play_album_title);
        this.m = (TextView) inflate.findViewById(b.h.text_init_time);
        this.n = (TextView) inflate.findViewById(b.h.text_total_time);
        if (this.g.getResources().getConfiguration().fontScale > 1.0f) {
            this.k.setMaxLines(3);
            this.j.setMaxLines(3);
            this.l.setMaxLines(3);
        } else {
            this.k.setMaxLines(1);
            this.j.setMaxLines(1);
            this.l.setMaxLines(1);
        }
        this.o = (ImageView) inflate.findViewById(b.h.img_now_play);
        this.p = (ImageView) inflate.findViewById(b.h.img_music_icon);
        this.q = (CustomSeekBar) inflate.findViewById(b.h.progress_now_play);
        this.q.setEnabled(false);
        this.q.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.syncstatus.i.1
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                super.onInitializeAccessibilityEvent(view, accessibilityEvent);
                accessibilityEvent.setClassName(null);
            }

            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                MainAct mainAct;
                int i;
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.setClassName(null);
                if (i.this.g.f.r.n == null || i.this.g.f.r.n.isEmpty()) {
                    return;
                }
                MainAct mainAct2 = i.this.g;
                Object[] objArr = new Object[4];
                objArr[0] = com.lge.media.launcher.c.a(i.this.g, Integer.parseInt(i.this.g.f.r.n));
                objArr[1] = com.lge.media.launcher.c.a(i.this.g, i.this.g.f.L);
                if (i.this.g.n) {
                    mainAct = i.this.g;
                    i = b.m.label_playing;
                } else {
                    mainAct = i.this.g;
                    i = b.m.label_paused;
                }
                objArr[2] = mainAct.getString(i);
                objArr[3] = i.this.g.getString(b.m.label_play_slide);
                accessibilityNodeInfo.setContentDescription(mainAct2.getString(b.m.label_play_time, objArr));
            }
        });
        com.lge.media.launcher.c.a(this.m);
        com.lge.media.launcher.c.a(this.n);
        if (!this.g.f.R || this.r) {
            return;
        }
        getbtnSize();
        d();
    }

    @Override // com.lge.media.launcher.control.common.m
    public void a(k kVar) {
        com.lge.media.launcher.control.common.a.b("Now play fillViews");
        this.c.setVisibility(4);
        if (!this.d.isShown()) {
            this.d.setVisibility(0);
        }
        this.k.setText(this.g.f.r.j);
        this.j.setText(this.g.f.r.k);
        this.l.setText(this.g.f.r.l);
        this.m.setText(a(this.g.f.r.m));
        this.n.setText(a(this.g.f.r.n));
        if (this.g.f.r.o != null) {
            com.lge.media.launcher.control.common.a.a("coverArtImg is not null");
            this.o.setImageBitmap(this.g.f.r.o);
        } else {
            com.lge.media.launcher.control.common.a.a("coverArtImg is null");
        }
        this.q.setMax(Integer.parseInt(this.g.f.r.n));
        this.q.setProgress(Integer.parseInt(this.g.f.r.m));
        this.q.incrementProgressBy(1);
    }

    @Override // com.lge.media.launcher.control.common.m
    public void b() {
        com.lge.media.launcher.control.common.a.b("Now play hideViews");
        this.c.setVisibility(0);
        this.d.setVisibility(4);
    }

    @Override // com.lge.media.launcher.control.common.m
    public void c() {
        Button button;
        MainAct mainAct;
        int i;
        if (this.g.n) {
            this.f.setBackgroundResource(b.g.btn_now_player_pause);
            button = this.f;
            mainAct = this.g;
            i = b.m.label_pause;
        } else {
            this.f.setBackgroundResource(b.g.btn_now_player_play);
            button = this.f;
            mainAct = this.g;
            i = b.m.label_play;
        }
        button.setContentDescription(mainAct.getString(i));
        this.q.setProgress(this.g.f.L);
        this.m.setText(a(Integer.toString(this.g.f.L)));
        this.k.setText(this.g.f.K);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        com.lge.media.launcher.control.common.e eVar;
        b.c cVar;
        Button button;
        MainAct mainAct;
        int i;
        MainAct mainAct2 = this.g;
        if (MainAct.h) {
            com.lge.media.launcher.control.common.f.d();
            return;
        }
        int id = view.getId();
        if (id == 2131230777) {
            com.lge.media.launcher.control.common.f.d();
            if (!this.g.f.r.b.equals("DISC")) {
                this.g.f.a(b.c.KEYCODE_BACK);
                b();
                com.lge.media.launcher.control.common.a.a("Timer start");
                this.g.f.ak.start();
                return;
            }
            this.g.d.a(1);
            this.g.c.getAdapter().notifyDataSetChanged();
            this.g.c.setCurrentItem(this.g.i());
            this.g.b.b();
            this.g.b.a(this.g.f.r);
            return;
        }
        switch (id) {
            case b.h.btn_now_player_next /* 2131230807 */:
                com.lge.media.launcher.control.common.f.d();
                eVar = this.g.f;
                cVar = b.c.KEYCODE_NEXT;
                break;
            case b.h.btn_now_player_play_pause /* 2131230808 */:
                com.lge.media.launcher.control.common.f.d();
                if (this.g.n) {
                    this.g.f.a(b.c.KEYCODE_PAUSE);
                    this.f.setBackgroundResource(b.g.btn_now_player_play);
                    button = this.f;
                    mainAct = this.g;
                    i = b.m.label_play;
                } else {
                    this.g.f.a(b.c.KEYCODE_PLAY);
                    this.f.setBackgroundResource(b.g.btn_now_player_pause);
                    button = this.f;
                    mainAct = this.g;
                    i = b.m.label_pause;
                }
                button.setContentDescription(mainAct.getString(i));
                return;
            case b.h.btn_now_player_prev /* 2131230809 */:
                com.lge.media.launcher.control.common.f.d();
                eVar = this.g.f;
                cVar = b.c.KEYCODE_PREVIOUS;
                break;
            default:
                return;
        }
        eVar.a(cVar);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
