package android.support.v4.c.a;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.view.Display;
import android.view.WindowManager;
import java.util.WeakHashMap;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class a {
    public static final String a = "android.hardware.display.category.PRESENTATION";
    private static final WeakHashMap<Context, a> b = new WeakHashMap<>();
    private final Context c;

    private a(Context context) {
        this.c = context;
    }

    @af
    public static a a(@af Context context) {
        a aVar;
        synchronized (b) {
            aVar = b.get(context);
            if (aVar == null) {
                aVar = new a(context);
                b.put(context, aVar);
            }
        }
        return aVar;
    }

    @ag
    public Display a(int i) {
        if (Build.VERSION.SDK_INT >= 17) {
            return ((DisplayManager) this.c.getSystemService("display")).getDisplay(i);
        }
        Display defaultDisplay = ((WindowManager) this.c.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay.getDisplayId() == i) {
            return defaultDisplay;
        }
        return null;
    }

    @af
    public Display[] a() {
        return Build.VERSION.SDK_INT >= 17 ? ((DisplayManager) this.c.getSystemService("display")).getDisplays() : new Display[]{((WindowManager) this.c.getSystemService("window")).getDefaultDisplay()};
    }

    @af
    public Display[] a(@ag String str) {
        return Build.VERSION.SDK_INT >= 17 ? ((DisplayManager) this.c.getSystemService("display")).getDisplays(str) : str == null ? new Display[0] : new Display[]{((WindowManager) this.c.getSystemService("window")).getDefaultDisplay()};
    }
}
