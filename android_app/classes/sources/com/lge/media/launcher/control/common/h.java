package com.lge.media.launcher.control.common;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.lge.UDAP.ROAP.a.b;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class h extends com.lge.media.launcher.ui.d {
    protected ArrayList<View> a;
    protected HashMap<Integer, b.c> b;
    private ViewGroup c;

    public h(Context context, ViewGroup viewGroup) {
        super(context);
        this.a = new ArrayList<>();
        this.b = new HashMap<>();
        this.c = viewGroup;
    }

    protected abstract void a();

    protected abstract void a(ViewGroup viewGroup);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void a(com.lge.media.launcher.cplist.c cVar);

    protected abstract void b();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void c();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void d();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void e();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void f();
}
