package com.lge.media.launcher.control.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.lge.media.launcher.b;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class AboutAct extends Activity {
    private TextView a;
    private NfcAdapter b;
    private PendingIntent c;
    private IntentFilter[] d;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(b.j.about);
        ((TextView) findViewById(b.h.text_top_title)).setContentDescription(getString(b.m.about) + getString(b.m.label_title) + "1");
        findViewById(b.h.button_back).setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.AboutAct.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AboutAct.this.finish();
            }
        });
        this.a = (TextView) findViewById(b.h.text_app_version);
        try {
            this.b = NfcAdapter.getDefaultAdapter(this);
            if (this.b == null) {
                a.b("This device does not support NFc");
            }
            this.c = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(536870912), 0);
            this.d = new IntentFilter[]{new IntentFilter("android.nfc.action.TAG_DISCOVERED")};
        } catch (Exception unused) {
            a.a("NFC Adapter exception");
        }
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        a.b("BDP RemotePage Tagging again");
    }

    @Override // android.app.Activity
    protected void onResume() {
        a.a("PreferencesAct OnResume");
        super.onResume();
        try {
            this.a.setText(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
            this.b.enableForegroundDispatch(this, this.c, this.d, null);
        } catch (Exception unused) {
            a.a("NFC Adapter exception");
        }
    }
}
