package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.af;
import android.widget.EdgeEffect;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class i {
    private EdgeEffect a;

    @Deprecated
    public i(Context context) {
        this.a = new EdgeEffect(context);
    }

    public static void a(@af EdgeEffect edgeEffect, float f, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            edgeEffect.onPull(f, f2);
        } else {
            edgeEffect.onPull(f);
        }
    }

    @Deprecated
    public void a(int i, int i2) {
        this.a.setSize(i, i2);
    }

    @Deprecated
    public boolean a() {
        return this.a.isFinished();
    }

    @Deprecated
    public boolean a(float f) {
        this.a.onPull(f);
        return true;
    }

    @Deprecated
    public boolean a(float f, float f2) {
        a(this.a, f, f2);
        return true;
    }

    @Deprecated
    public boolean a(int i) {
        this.a.onAbsorb(i);
        return true;
    }

    @Deprecated
    public boolean a(Canvas canvas) {
        return this.a.draw(canvas);
    }

    @Deprecated
    public void b() {
        this.a.finish();
    }

    @Deprecated
    public boolean c() {
        this.a.onRelease();
        return this.a.isFinished();
    }
}
