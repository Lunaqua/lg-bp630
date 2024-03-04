package com.lge.media.launcher.ui;

import android.content.Context;
import android.view.View;
import com.lge.media.launcher.control.common.MainAct;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class d extends View implements View.OnClickListener, View.OnTouchListener {
    public MainAct g;

    public d(Context context) {
        super(context);
        this.g = (MainAct) context;
    }
}
