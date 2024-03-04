package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.an;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class as extends ContextWrapper {
    private static final Object a = new Object();
    private static ArrayList<WeakReference<as>> b;
    private final Resources c;
    private final Resources.Theme d;

    private as(@android.support.annotation.af Context context) {
        super(context);
        if (!ba.a()) {
            this.c = new au(this, context.getResources());
            this.d = null;
            return;
        }
        this.c = new ba(this, context.getResources());
        this.d = this.c.newTheme();
        this.d.setTo(context.getTheme());
    }

    public static Context a(@android.support.annotation.af Context context) {
        if (b(context)) {
            synchronized (a) {
                if (b == null) {
                    b = new ArrayList<>();
                } else {
                    for (int size = b.size() - 1; size >= 0; size--) {
                        WeakReference<as> weakReference = b.get(size);
                        if (weakReference == null || weakReference.get() == null) {
                            b.remove(size);
                        }
                    }
                    for (int size2 = b.size() - 1; size2 >= 0; size2--) {
                        WeakReference<as> weakReference2 = b.get(size2);
                        as asVar = weakReference2 != null ? weakReference2.get() : null;
                        if (asVar != null && asVar.getBaseContext() == context) {
                            return asVar;
                        }
                    }
                }
                as asVar2 = new as(context);
                b.add(new WeakReference<>(asVar2));
                return asVar2;
            }
        }
        return context;
    }

    private static boolean b(@android.support.annotation.af Context context) {
        if ((context instanceof as) || (context.getResources() instanceof au) || (context.getResources() instanceof ba)) {
            return false;
        }
        return Build.VERSION.SDK_INT < 21 || ba.a();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.c.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.d;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        Resources.Theme theme = this.d;
        if (theme == null) {
            super.setTheme(i);
        } else {
            theme.applyStyle(i, true);
        }
    }
}
