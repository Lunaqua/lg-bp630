package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class au extends ai {
    private final WeakReference<Context> a;

    public au(@android.support.annotation.af Context context, @android.support.annotation.af Resources resources) {
        super(resources);
        this.a = new WeakReference<>(context);
    }

    @Override // android.support.v7.widget.ai, android.content.res.Resources
    public Drawable getDrawable(int i) {
        Drawable drawable = super.getDrawable(i);
        Context context = this.a.get();
        if (drawable != null && context != null) {
            g.a();
            g.a(context, i, drawable);
        }
        return drawable;
    }
}
