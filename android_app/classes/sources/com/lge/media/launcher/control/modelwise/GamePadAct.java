package com.lge.media.launcher.control.modelwise;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.a.c;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.lge.UDAP.ROAP.a.b;
import com.lge.media.launcher.b;
import com.lge.media.launcher.control.common.d;
import com.lge.media.launcher.control.common.e;
import com.lge.media.launcher.control.common.f;
import com.lge.media.launcher.sp.WatchSoundPrivacy;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class GamePadAct extends Activity implements View.OnClickListener, View.OnTouchListener {
    private static final int k = 0;
    private static final int l = 1;
    private static final int m = 2;
    private static final int n = 3;
    private static final int o = 4;
    private PendingIntent A;
    private IntentFilter[] B;
    private int E;
    private int F;
    public Button a;
    public Button b;
    public Button c;
    public Button d;
    public Button e;
    public Button f;
    public Button g;
    public Button h;
    private ImageView s;
    private e t;
    private NfcAdapter z;
    private int p = 25;
    private int q = 50;
    private int r = 47;
    private ArrayList<Button> u = new ArrayList<>();
    private HashMap<Integer, b.c> v = new HashMap<>();
    private boolean w = false;
    private boolean x = false;
    private int[][] y = (int[][]) Array.newInstance(int.class, 7, 2);
    int[] i = {b.h.btn_game_left, b.h.btn_game_right, b.h.btn_game_up, b.h.btn_game_down, b.h.btn_game_red, b.h.btn_game_blue, b.h.btn_game_yellow, b.h.btn_game_green};
    b.c[] j = {b.c.KEYCODE_LEFT, b.c.KEYCODE_RIGHT, b.c.KEYCODE_UP, b.c.KEYCODE_DOWN, b.c.KEYCODE_RED, b.c.KEYCODE_BLUE, b.c.KEYCODE_YELLOW, b.c.KEYCODE_GREEN};
    private int C = 4;
    private b.c D = null;

    private int a(int i, int i2) {
        int i3 = i - this.E;
        int i4 = i2 - this.F;
        if (Math.abs(i3) + Math.abs(i4) < 10) {
            return 4;
        }
        return Math.abs(i3) > Math.abs(i4) ? i3 < 0 ? 0 : 1 : i4 < 0 ? 2 : 3;
    }

    private void a() {
        int i;
        if (!this.x) {
            this.x = true;
            int a = this.t.a();
            if (a != 120) {
                if (a == 160) {
                    this.p = 16;
                    this.q = 32;
                    i = 31;
                } else if (a != 213 && a != 240) {
                    if (a == 320) {
                        this.p = 32;
                        this.q = 64;
                        i = 62;
                    } else if (a == 480) {
                        this.p = 48;
                        this.q = 96;
                        i = 93;
                    }
                }
                this.r = i;
            }
        }
        ((TextView) findViewById(b.h.text_title_gamepad)).setContentDescription(getString(b.m.game_pad) + getString(b.m.label_title) + "1");
        for (int i2 : this.i) {
            this.u.add((Button) findViewById(i2));
        }
        this.s = (ImageView) findViewById(b.h.img_game_jog);
        this.a = (Button) findViewById(b.h.btn_game_red);
        this.b = (Button) findViewById(b.h.btn_game_green);
        this.c = (Button) findViewById(b.h.btn_game_blue);
        this.d = (Button) findViewById(b.h.btn_game_yellow);
        this.e = (Button) findViewById(b.h.btn_game_left);
        this.f = (Button) findViewById(b.h.btn_game_right);
        this.g = (Button) findViewById(b.h.btn_game_up);
        this.h = (Button) findViewById(b.h.btn_game_down);
        findViewById(b.h.button_back).setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.modelwise.GamePadAct.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GamePadAct.this.finish();
            }
        });
        b();
    }

    private void a(int i) {
        int i2;
        Button button;
        this.C = i;
        b.c cVar = this.D;
        if (cVar != null) {
            this.t.c(cVar);
        }
        this.u.get(0).setBackgroundResource(b.g.bg_game_arrow_l_n);
        this.u.get(1).setBackgroundResource(b.g.bg_game_arrow_r_n);
        this.u.get(2).setBackgroundResource(b.g.bg_game_arrow_u_n);
        this.u.get(3).setBackgroundResource(b.g.bg_game_arrow_d_n);
        int i3 = this.C;
        if (i3 == 0) {
            boolean z = this.t.R;
            i2 = b.g.bg_game_arrow_l_p;
            if (z) {
                this.D = b.c.KEYCODE_LEFT;
                this.t.b(this.D);
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), b.g.bg_game_jog_p), (int) (this.y[5][0] * this.t.U), (int) (this.y[5][1] * this.t.U), true);
                this.s.setPadding(0, this.p, 0, 0);
                this.s.setImageBitmap(createScaledBitmap);
                this.s.requestLayout();
            } else {
                this.D = b.c.KEYCODE_LEFT;
                this.t.b(this.D);
                this.s.setPadding(0, this.p, 0, 0);
            }
            button = this.u.get(0);
        } else if (i3 == 1) {
            boolean z2 = this.t.R;
            i2 = b.g.bg_game_arrow_r_p;
            if (z2) {
                this.D = b.c.KEYCODE_RIGHT;
                this.t.b(this.D);
                Bitmap createScaledBitmap2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), b.g.bg_game_jog_p), (int) (this.y[5][0] * this.t.U), (int) (this.y[5][1] * this.t.U), true);
                this.s.setPadding(this.q, this.p, 0, 0);
                this.s.setImageBitmap(createScaledBitmap2);
                this.s.requestLayout();
            } else {
                this.D = b.c.KEYCODE_RIGHT;
                this.t.b(this.D);
                this.s.setPadding(this.q, this.p, 0, 0);
                this.s.setImageResource(b.g.bg_game_jog_p);
            }
            button = this.u.get(1);
        } else if (i3 == 2) {
            if (this.t.R) {
                this.D = b.c.KEYCODE_UP;
                this.t.b(this.D);
                Bitmap createScaledBitmap3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), b.g.bg_game_jog_p), (int) (this.y[5][0] * this.t.U), (int) (this.y[5][1] * this.t.U), true);
                this.s.setPadding(this.p, 0, 0, 0);
                this.s.setImageBitmap(createScaledBitmap3);
                this.s.requestLayout();
            } else {
                this.D = b.c.KEYCODE_UP;
                this.t.b(this.D);
                this.s.setPadding(this.p, 0, 0, 0);
                this.s.setImageResource(b.g.bg_game_jog_p);
            }
            this.u.get(2).setBackgroundResource(b.g.bg_game_arrow_u_p);
            return;
        } else if (i3 != 3) {
            if (i3 != 4) {
                return;
            }
            if (!this.t.R) {
                ImageView imageView = this.s;
                int i4 = this.p;
                imageView.setPadding(i4, i4, 0, 0);
                this.s.setImageResource(b.g.bg_game_jog_n);
                return;
            }
            Bitmap createScaledBitmap4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), b.g.bg_game_jog_n), (int) (this.y[5][0] * this.t.U), (int) (this.y[5][1] * this.t.U), true);
            ImageView imageView2 = this.s;
            int i5 = this.p;
            imageView2.setPadding(i5, i5, 0, 0);
            this.s.setImageBitmap(createScaledBitmap4);
            this.s.requestLayout();
            return;
        } else {
            boolean z3 = this.t.R;
            i2 = b.g.bg_game_arrow_d_p;
            if (z3) {
                this.D = b.c.KEYCODE_DOWN;
                this.t.b(this.D);
                Bitmap createScaledBitmap5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), b.g.bg_game_jog_p), (int) (this.y[5][0] * this.t.U), (int) (this.y[5][1] * this.t.U), true);
                this.s.setPadding(this.p, this.r, 0, 0);
                this.s.setImageBitmap(createScaledBitmap5);
                this.s.requestLayout();
            } else {
                this.D = b.c.KEYCODE_DOWN;
                this.t.b(this.D);
                this.s.setPadding(this.p, this.r, 0, 0);
                this.s.setImageResource(b.g.bg_game_jog_p);
            }
            button = this.u.get(3);
        }
        button.setBackgroundResource(i2);
    }

    private void b() {
        this.h.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.control.modelwise.GamePadAct.2
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                c.a(accessibilityNodeInfo).i(GamePadAct.this.g);
            }
        });
        this.e.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.control.modelwise.GamePadAct.3
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                c.a(accessibilityNodeInfo).i(GamePadAct.this.h);
            }
        });
        this.f.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.control.modelwise.GamePadAct.4
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                c.a(accessibilityNodeInfo).i(GamePadAct.this.e);
            }
        });
        this.d.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.control.modelwise.GamePadAct.5
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                c.a(accessibilityNodeInfo).i(GamePadAct.this.a);
            }
        });
        this.b.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.control.modelwise.GamePadAct.6
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                c.a(accessibilityNodeInfo).i(GamePadAct.this.d);
            }
        });
        this.c.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.control.modelwise.GamePadAct.7
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                c.a(accessibilityNodeInfo).i(GamePadAct.this.b);
            }
        });
    }

    private void c() {
        for (int i = 0; i < this.j.length; i++) {
            this.v.put(Integer.valueOf(this.i[i]), this.j[i]);
        }
    }

    private void d() {
        for (int i = 0; i < this.u.size(); i++) {
            this.u.get(i).setOnClickListener(this);
        }
        this.s.setOnTouchListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        f.d();
        this.t.a(this.v.get(Integer.valueOf(view.getId())));
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(b.j.gamepad);
        this.w = getIntent().getBooleanExtra(d.Q, false);
        if (!this.w) {
            registerReceiver(new WatchSoundPrivacy(), new IntentFilter("android.intent.action.HEADSET_PLUG"));
        }
        this.t = e.a(this, (Handler) null);
        a();
        c();
        d();
        try {
            this.z = NfcAdapter.getDefaultAdapter(this);
            if (this.z == null) {
                com.lge.media.launcher.control.common.a.b("This device does not support NFc");
            }
            this.A = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(536870912), 0);
            this.B = new IntentFilter[]{new IntentFilter("android.nfc.action.TAG_DISCOVERED")};
        } catch (Exception unused) {
            com.lge.media.launcher.control.common.a.a("NFC Adapter exception");
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        com.lge.media.launcher.control.common.a.a("GamePadAct OnDestroy");
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        com.lge.media.launcher.control.common.a.b("BDP RemotePage Tagging again");
    }

    @Override // android.app.Activity
    protected void onResume() {
        com.lge.media.launcher.control.common.a.a("GamePadAct OnResume");
        super.onResume();
        try {
            this.z.enableForegroundDispatch(this, this.A, this.B, null);
        } catch (Exception unused) {
            com.lge.media.launcher.control.common.a.a("NFC Adapter exception");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x00af, code lost:
        if (r7.C != r8) goto L13;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouch(android.view.View r8, android.view.MotionEvent r9) {
        /*
            r7 = this;
            com.lge.media.launcher.control.common.e r0 = r7.t
            boolean r0 = r0.R
            r1 = 1
            if (r0 == 0) goto L69
            android.content.res.Resources r0 = r7.getResources()
            r2 = 2131165297(0x7f070071, float:1.7944807E38)
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeResource(r0, r2)
            int[][] r2 = r7.y
            r3 = 5
            r2 = r2[r3]
            r4 = 0
            r2 = r2[r4]
            float r2 = (float) r2
            com.lge.media.launcher.control.common.e r5 = r7.t
            float r5 = r5.U
            float r2 = r2 * r5
            int r2 = (int) r2
            int[][] r5 = r7.y
            r5 = r5[r3]
            r5 = r5[r1]
            float r5 = (float) r5
            com.lge.media.launcher.control.common.e r6 = r7.t
            float r6 = r6.U
            float r5 = r5 * r6
            int r5 = (int) r5
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createScaledBitmap(r0, r2, r5, r1)
            android.widget.ImageView r2 = r7.s
            r2.setImageBitmap(r0)
            android.content.res.Resources r0 = r7.getResources()
            r2 = 2131165296(0x7f070070, float:1.7944805E38)
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeResource(r0, r2)
            int[][] r2 = r7.y
            r2 = r2[r3]
            r2 = r2[r4]
            float r2 = (float) r2
            com.lge.media.launcher.control.common.e r4 = r7.t
            float r4 = r4.U
            float r2 = r2 * r4
            int r2 = (int) r2
            int[][] r4 = r7.y
            r3 = r4[r3]
            r3 = r3[r1]
            float r3 = (float) r3
            com.lge.media.launcher.control.common.e r4 = r7.t
            float r4 = r4.U
            float r3 = r3 * r4
            int r3 = (int) r3
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createScaledBitmap(r0, r2, r3, r1)
            android.widget.ImageView r2 = r7.s
            r2.setImageBitmap(r0)
        L69:
            int r8 = r8.getId()
            r0 = 2131230916(0x7f0800c4, float:1.8077898E38)
            if (r8 != r0) goto Lc5
            float r8 = r9.getX()
            int r8 = (int) r8
            float r0 = r9.getY()
            int r0 = (int) r0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "X: "
            r2.append(r3)
            r2.append(r8)
            java.lang.String r3 = " Y: "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.lge.media.launcher.control.common.a.c(r2)
            int r9 = r9.getAction()
            if (r9 == 0) goto Lbc
            if (r9 == r1) goto Lb2
            r2 = 2
            if (r9 == r2) goto La4
            goto Lc5
        La4:
            java.lang.String r9 = "ACTION_MOVE"
            com.lge.media.launcher.control.common.a.b(r9)
            int r8 = r7.a(r8, r0)
            int r9 = r7.C
            if (r9 == r8) goto Lc5
            goto Lb8
        Lb2:
            java.lang.String r8 = "ACTION_UP"
            com.lge.media.launcher.control.common.a.b(r8)
            r8 = 4
        Lb8:
            r7.a(r8)
            goto Lc5
        Lbc:
            java.lang.String r9 = "ACTION_DOWN"
            com.lge.media.launcher.control.common.a.b(r9)
            r7.E = r8
            r7.F = r0
        Lc5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lge.media.launcher.control.modelwise.GamePadAct.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
