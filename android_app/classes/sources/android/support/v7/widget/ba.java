package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.an;
import java.lang.ref.WeakReference;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ba extends Resources {
    public static final int a = 20;
    private static boolean b = false;
    private final WeakReference<Context> c;

    public ba(@android.support.annotation.af Context context, @android.support.annotation.af Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.c = new WeakReference<>(context);
    }

    public static void a(boolean z) {
        b = z;
    }

    public static boolean a() {
        return b() && Build.VERSION.SDK_INT <= 20;
    }

    public static boolean b() {
        return b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Drawable a(int i) {
        return super.getDrawable(i);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i) {
        Context context = this.c.get();
        return context != null ? g.a().a(context, this, i) : super.getDrawable(i);
    }
}
