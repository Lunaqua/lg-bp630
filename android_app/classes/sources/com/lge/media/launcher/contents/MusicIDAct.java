package com.lge.media.launcher.contents;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.lge.UDAP.ROAP.a.b;
import com.lge.media.launcher.b;
import com.lge.media.launcher.control.common.e;
import com.lge.media.launcher.control.common.f;
import java.util.ArrayList;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class MusicIDAct extends Activity {
    private static int f = 95;
    private static final int g = 0;
    private static final int h = 1;
    private static final int i = 2;
    private static final int j = 3;
    private static final int k = 0;
    private static final int l = 0;
    private LinearLayout A;
    private Button B;
    private e C;
    private c D;
    private String E;
    private Button m;
    private Button n;
    private Button o;
    private ImageView p;
    private ScrollView y;
    private LinearLayout z;
    private ArrayList<TextView> q = new ArrayList<>();
    private int[] r = {b.h.text_info_title, b.h.text_info_genre, b.h.text_info_release_date, b.h.text_info_arist};
    private ArrayList<LinearLayout> s = new ArrayList<>();
    private int[] t = {b.h.layout_info_track};
    private ArrayList<ListView> u = new ArrayList<>();
    private int[] v = {b.h.listview_tracks};
    private ArrayList<LinearLayout> w = new ArrayList<>();
    private int[] x = {b.h.layout_info_track_list};
    public boolean a = false;
    public boolean b = false;
    public boolean c = false;
    private com.lge.UDAP.ROAP.b F = null;
    public Handler d = new Handler() { // from class: com.lge.media.launcher.contents.MusicIDAct.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            MusicIDAct musicIDAct;
            int i2 = message.what;
            if (i2 != 5) {
                if (i2 != 6) {
                    if (i2 != 7) {
                        if (i2 != 25) {
                            switch (i2) {
                                case 60:
                                    com.lge.media.launcher.control.common.a.c("RECEIVED_BGM_INFORMATION_OK");
                                    if (!MusicIDAct.this.c) {
                                        musicIDAct = MusicIDAct.this;
                                        musicIDAct.c = true;
                                        break;
                                    }
                                    break;
                                case 61:
                                    com.lge.media.launcher.control.common.a.c("RECEIVED_BGM_INFORMATION_FAIL");
                                    break;
                                case 62:
                                    com.lge.media.launcher.control.common.a.c("RECEIVED_BGM_INFORMATION_ROADING");
                                    MusicIDAct.this.b();
                                    break;
                                case 63:
                                    com.lge.media.launcher.control.common.a.c("Handler msg is BGM_INFO_CANCEL");
                                    MusicIDAct.this.onBackPressed();
                                    MusicIDAct.this.C.z = false;
                                    break;
                            }
                        } else {
                            com.lge.media.launcher.control.common.a.c("Handler msg is SYNC_SS_VIEW_CHANGE");
                            MusicIDAct.this.onBackPressed();
                            MusicIDAct.this.C.z = true;
                        }
                        MusicIDAct.this.finish();
                    }
                    MusicIDAct.this.b((int) b.m.music_id_info_fail);
                } else {
                    musicIDAct = MusicIDAct.this;
                }
                musicIDAct.C.a(false);
            } else {
                MusicIDAct.this.a(Integer.parseInt((String) message.obj));
            }
            super.handleMessage(message);
        }
    };
    public View.OnClickListener e = new View.OnClickListener() { // from class: com.lge.media.launcher.contents.MusicIDAct.9
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.d();
            if (view.getId() == 2131230790) {
                MusicIDAct musicIDAct = MusicIDAct.this;
                musicIDAct.E = (String) ((TextView) musicIDAct.q.get(0)).getText();
            } else {
                MusicIDAct.this.E = (String) ((Button) view).getText();
            }
            MusicIDAct.this.showDialog(0);
        }
    };

    private Dialog a(String str) {
        com.lge.media.launcher.control.common.a.c("string2:" + b.m.searchfor + str);
        this.E = str;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        return builder.setTitle(getString(b.m.searchfor) + this.E).setItems(b.C0065b.select_dialog_items, new DialogInterface.OnClickListener() { // from class: com.lge.media.launcher.contents.MusicIDAct.10
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                Intent intent;
                if (i2 == 0) {
                    intent = new Intent("android.intent.action.WEB_SEARCH");
                } else if (i2 != 1) {
                    return;
                } else {
                    intent = new Intent("android.intent.action.SEARCH");
                    intent.setPackage("com.google.android.youtube");
                }
                intent.putExtra("query", MusicIDAct.this.E);
                MusicIDAct.this.startActivity(intent);
            }
        }).create();
    }

    private void a() {
        this.z.setVisibility(8);
        this.y.setVisibility(8);
        this.A.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        int i3;
        if (i2 != 1) {
            if (i2 == 2) {
                i3 = b.m.music_id_fail;
            } else if (i2 != 3) {
                if (i2 != 4) {
                    return;
                }
                i3 = b.m.music_id_invalid;
            }
            b(i3);
            return;
        }
        this.D = this.C.r();
        d();
        e();
        f();
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.z.setVisibility(0);
        this.y.setVisibility(8);
        this.A.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2) {
        Button button;
        View.OnClickListener onClickListener;
        this.z = (LinearLayout) findViewById(b.h.layout_mid_progress_waiting);
        this.A = (LinearLayout) findViewById(b.h.layout_mid_fail);
        this.y = (ScrollView) findViewById(b.h.scrollview_main);
        ((TextView) findViewById(b.h.text_fail)).setText(i2);
        a();
        if (i2 == 2131558601) {
            com.lge.media.launcher.control.common.a.a("Music ID has not info");
            this.o.setVisibility(8);
            this.B.setVisibility(0);
            button = this.B;
            onClickListener = new View.OnClickListener() { // from class: com.lge.media.launcher.contents.MusicIDAct.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MusicIDAct.this.F.b.f(1);
                    MusicIDAct.this.onBackPressed();
                }
            };
        } else {
            com.lge.media.launcher.control.common.a.a("Music ID not response");
            this.B.setVisibility(8);
            this.o.setVisibility(0);
            button = this.o;
            onClickListener = new View.OnClickListener() { // from class: com.lge.media.launcher.contents.MusicIDAct.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MusicIDAct.this.b();
                    MusicIDAct.this.C.a(b.c.KEYCODE_BGM);
                    MusicIDAct.this.C.a(false);
                }
            };
        }
        button.setOnClickListener(onClickListener);
    }

    private void c() {
        this.z.setVisibility(8);
        this.y.setVisibility(0);
        this.A.setVisibility(8);
    }

    private void d() {
        int i2;
        int a = this.C.a();
        if (a != 120) {
            if (a == 160) {
                i2 = 48;
            } else if (a == 213 || a == 240) {
                i2 = 73;
            } else if (a == 320) {
                i2 = 95;
            } else if (a == 480) {
                i2 = 146;
            }
            f = i2;
        }
        this.z = (LinearLayout) findViewById(b.h.layout_mid_progress_waiting);
        this.A = (LinearLayout) findViewById(b.h.layout_mid_fail);
        this.y = (ScrollView) findViewById(b.h.scrollview_main);
        c();
        this.m = (Button) findViewById(b.h.btn_info_share);
        this.n = (Button) findViewById(b.h.btn_info_search);
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int[] iArr = this.r;
            if (i4 >= iArr.length) {
                break;
            }
            this.q.add((TextView) findViewById(iArr[i4]));
            i4++;
        }
        int i5 = 0;
        while (true) {
            int[] iArr2 = this.t;
            if (i5 >= iArr2.length) {
                break;
            }
            this.s.add((LinearLayout) findViewById(iArr2[i5]));
            i5++;
        }
        int i6 = 0;
        while (true) {
            int[] iArr3 = this.v;
            if (i6 >= iArr3.length) {
                break;
            }
            this.u.add((ListView) findViewById(iArr3[i6]));
            i6++;
        }
        while (true) {
            int[] iArr4 = this.x;
            if (i3 >= iArr4.length) {
                this.m.setVisibility(8);
                return;
            } else {
                this.w.add((LinearLayout) findViewById(iArr4[i3]));
                i3++;
            }
        }
    }

    private void e() {
        this.p.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.contents.MusicIDAct.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                MusicIDAct.this.startActivity(new Intent(MusicIDAct.this, DiscInfoImageAct.class));
            }
        });
        this.m.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.contents.MusicIDAct.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                StringBuilder sb;
                MusicIDAct musicIDAct;
                int i2;
                f.d();
                Intent intent = new Intent("android.intent.action.SEND");
                int f2 = MusicIDAct.this.C.f();
                if (f2 == 0) {
                    sb = new StringBuilder();
                    sb.append(MusicIDAct.this.getString(b.m.IamWatching));
                    sb.append((Object) ((TextView) MusicIDAct.this.q.get(0)).getText());
                    musicIDAct = MusicIDAct.this;
                    i2 = b.m.onLGBDP;
                } else if (f2 == 1) {
                    sb = new StringBuilder();
                    sb.append(MusicIDAct.this.getString(b.m.IamWatching));
                    sb.append((Object) ((TextView) MusicIDAct.this.q.get(0)).getText());
                    musicIDAct = MusicIDAct.this;
                    i2 = b.m.onLGHTS;
                } else if (f2 != 2) {
                    if (f2 == 3) {
                        sb = new StringBuilder();
                        sb.append(MusicIDAct.this.getString(b.m.IamWatching));
                        sb.append((Object) ((TextView) MusicIDAct.this.q.get(0)).getText());
                        musicIDAct = MusicIDAct.this;
                        i2 = b.m.onLGSTU;
                    }
                    intent.setType("text/plain");
                    MusicIDAct musicIDAct2 = MusicIDAct.this;
                    musicIDAct2.startActivity(Intent.createChooser(intent, MusicIDAct.this.getString(b.m.shareto) + ((Object) ((TextView) MusicIDAct.this.q.get(0)).getText())));
                } else {
                    sb = new StringBuilder();
                    sb.append(MusicIDAct.this.getString(b.m.IamWatching));
                    sb.append((Object) ((TextView) MusicIDAct.this.q.get(0)).getText());
                    musicIDAct = MusicIDAct.this;
                    i2 = b.m.onLGHR;
                }
                sb.append(musicIDAct.getString(i2));
                intent.putExtra("android.intent.extra.TEXT", sb.toString());
                intent.setType("text/plain");
                MusicIDAct musicIDAct22 = MusicIDAct.this;
                musicIDAct22.startActivity(Intent.createChooser(intent, MusicIDAct.this.getString(b.m.shareto) + ((Object) ((TextView) MusicIDAct.this.q.get(0)).getText())));
            }
        });
        this.n.setOnClickListener(this.e);
    }

    private void f() {
        if (this.D.p.size() == 0) {
            this.s.get(0).setVisibility(8);
        }
    }

    private void g() {
        this.c = false;
        this.q.get(0).setText(this.D.c);
        this.q.get(1).setText(this.D.d);
        this.q.get(2).setText(this.D.f);
        this.q.get(3).setText(this.D.o);
        if (this.D.g != null) {
            com.lge.media.launcher.control.common.a.a("exsited image..");
            this.p.setImageBitmap(this.D.g);
        } else {
            com.lge.media.launcher.control.common.a.a("no image..");
            this.p.setImageResource(b.g.bg_n_frame);
        }
        this.p.setContentDescription(getString(b.m.label_image, new Object[]{this.D.c}));
        if (this.D.p.size() > 0) {
            com.lge.media.launcher.control.common.a.a("tracks listview is ready");
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < this.D.p.size(); i2++) {
                arrayList.add(this.D.p.get(i2).c + " ( " + this.D.p.get(i2).b + " )");
            }
            for (int i3 = 0; i3 < this.D.p.size(); i3++) {
                arrayList2.add(this.D.p.get(i3));
            }
            this.u.get(0).setAdapter((ListAdapter) new com.lge.media.launcher.ui.c(this, b.j.disc_info_item, arrayList, arrayList2, this.e));
            this.w.get(0).setLayoutParams(new LinearLayout.LayoutParams(-1, this.D.p.size() * f));
            com.lge.media.launcher.control.common.a.a("discInfo.tracks.size : " + this.D.p.size());
        }
        this.y.post(new Runnable() { // from class: com.lge.media.launcher.contents.MusicIDAct.2
            @Override // java.lang.Runnable
            public void run() {
                MusicIDAct.this.y.scrollTo(0, 0);
            }
        });
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(b.j.music_id);
        com.lge.media.launcher.control.common.a.c("Create MusicIDAct");
        this.C = e.a(this, this.d);
        this.F = this.C.p();
        this.C.z = true;
        findViewById(b.h.button_back).setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.contents.MusicIDAct.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MusicIDAct.this.finish();
            }
        });
        ((TextView) findViewById(b.h.text_top_title)).setContentDescription(getString(b.m.music_id) + getString(b.m.label_title) + "1");
        this.z = (LinearLayout) findViewById(b.h.layout_mid_progress_waiting);
        this.A = (LinearLayout) findViewById(b.h.layout_mid_fail);
        this.y = (ScrollView) findViewById(b.h.scrollview_main);
        this.a = getIntent().getBooleanExtra(com.lge.media.launcher.control.common.d.aw, false);
        this.b = getIntent().getBooleanExtra(com.lge.media.launcher.control.common.d.ax, false);
        com.lge.media.launcher.control.common.a.a("DEBUD isBGMInfoOK : " + this.a + ", isBGMInfoRoading : " + this.b);
        if (this.a) {
            this.C.a(false);
        } else if (this.b) {
            this.C.a(b.c.KEYCODE_INFO);
        } else {
            b(b.m.music_id_info_fail);
        }
        findViewById(b.h.btn_cancel).setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.contents.MusicIDAct.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.lge.media.launcher.control.common.a.b("DEBUG MusicID requeat cancel..");
                f.d();
                MusicIDAct.this.F.b.f(0);
                MusicIDAct.this.C.z = false;
                MusicIDAct.this.onBackPressed();
            }
        });
        this.o = (Button) findViewById(b.h.btn_mid_fail);
        this.B = (Button) findViewById(b.h.btn_fail_cancel);
        this.p = (ImageView) findViewById(b.h.img_title_image);
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i2) {
        if (i2 != 0) {
            return null;
        }
        return a(this.E);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            com.lge.media.launcher.control.common.a.b("DEBUG onBackpressed & sending a action MusicID Cancel");
            this.F.b.f(0);
            this.C.z = false;
            onBackPressed();
        }
        return false;
    }

    @Override // android.app.Activity
    protected void onPrepareDialog(int i2, Dialog dialog) {
        super.onPrepareDialog(i2, dialog);
        dialog.setTitle(getString(b.m.searchfor) + this.E);
    }
}
