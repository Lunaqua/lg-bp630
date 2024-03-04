package com.lge.media.launcher.control.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.lge.media.launcher.b;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ManualAct extends Activity {
    private NfcAdapter a;
    private PendingIntent b;
    private IntentFilter[] c;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        ImageView imageView;
        int i;
        super.onCreate(bundle);
        setContentView(b.j.manual);
        ((TextView) findViewById(b.h.text_top_title)).setContentDescription(getString(b.m.help) + getString(b.m.label_title) + "1");
        findViewById(b.h.button_back).setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.ManualAct.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ManualAct.this.finish();
            }
        });
        try {
            this.a = NfcAdapter.getDefaultAdapter(this);
            if (this.a == null) {
                a.b("This device does not support NFc");
            }
            this.b = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(536870912), 0);
            this.c = new IntentFilter[]{new IntentFilter("android.nfc.action.TAG_DISCOVERED")};
        } catch (Exception unused) {
            a.a("NFC Adapter exception");
        }
        int f = e.a(this, (Handler) null).f();
        if (f == 0) {
            imageView = (ImageView) findViewById(b.h.img_general_operation);
            i = b.g.thumb_media_01;
        } else if (f == 1) {
            imageView = (ImageView) findViewById(b.h.img_general_operation);
            i = b.g.thumb_media_04;
        } else if (f != 2) {
            if (f == 3) {
                imageView = (ImageView) findViewById(b.h.img_general_operation);
                i = b.g.thumb_media_02;
            }
            View findViewById = findViewById(b.h.title_user_guide);
            com.lge.media.launcher.c.a(findViewById, getString(b.m.label_title) + "2");
            View findViewById2 = findViewById(b.h.textview_title_1);
            findViewById2.setContentDescription(getString(b.m.preparation) + getString(b.m.label_title) + "3");
            View findViewById3 = findViewById(b.h.textview_title_2);
            findViewById3.setContentDescription(getString(b.m.device_connection) + getString(b.m.label_title) + "3");
            View findViewById4 = findViewById(b.h.textview_title_3);
            findViewById4.setContentDescription(getString(b.m.general_operation) + getString(b.m.label_title) + "3");
            View findViewById5 = findViewById(b.h.textview_title_4);
            findViewById5.setContentDescription(getString(b.m.troubleshooting) + getString(b.m.label_title) + "3");
        } else {
            imageView = (ImageView) findViewById(b.h.img_general_operation);
            i = b.g.thumb_media_05;
        }
        imageView.setImageResource(i);
        View findViewById6 = findViewById(b.h.title_user_guide);
        com.lge.media.launcher.c.a(findViewById6, getString(b.m.label_title) + "2");
        View findViewById22 = findViewById(b.h.textview_title_1);
        findViewById22.setContentDescription(getString(b.m.preparation) + getString(b.m.label_title) + "3");
        View findViewById32 = findViewById(b.h.textview_title_2);
        findViewById32.setContentDescription(getString(b.m.device_connection) + getString(b.m.label_title) + "3");
        View findViewById42 = findViewById(b.h.textview_title_3);
        findViewById42.setContentDescription(getString(b.m.general_operation) + getString(b.m.label_title) + "3");
        View findViewById52 = findViewById(b.h.textview_title_4);
        findViewById52.setContentDescription(getString(b.m.troubleshooting) + getString(b.m.label_title) + "3");
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        a.b("BDP RemotePage Tagging again");
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            this.a.enableForegroundDispatch(this, this.b, this.c, null);
        } catch (Exception unused) {
            a.a("NFC Adapter exception");
        }
    }
}
