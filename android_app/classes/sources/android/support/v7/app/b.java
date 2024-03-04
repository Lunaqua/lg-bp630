package android.support.v7.app;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.reflect.Method;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class b {
    private static final String a = "ActionBarDrawerToggleHC";
    private static final int[] b = {16843531};

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static class a {
        public Method a;
        public Method b;
        public ImageView c;

        a(Activity activity) {
            try {
                this.a = android.app.ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", Drawable.class);
                this.b = android.app.ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", Integer.TYPE);
            } catch (NoSuchMethodException unused) {
                View findViewById = activity.findViewById(16908332);
                if (findViewById == null) {
                    return;
                }
                ViewGroup viewGroup = (ViewGroup) findViewById.getParent();
                if (viewGroup.getChildCount() != 2) {
                    return;
                }
                View childAt = viewGroup.getChildAt(0);
                View childAt2 = childAt.getId() != 16908332 ? childAt : viewGroup.getChildAt(1);
                if (childAt2 instanceof ImageView) {
                    this.c = (ImageView) childAt2;
                }
            }
        }
    }

    private b() {
    }

    public static Drawable a(Activity activity) {
        TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(b);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }

    public static a a(a aVar, Activity activity, int i) {
        if (aVar == null) {
            aVar = new a(activity);
        }
        if (aVar.a != null) {
            try {
                android.app.ActionBar actionBar = activity.getActionBar();
                aVar.b.invoke(actionBar, Integer.valueOf(i));
                if (Build.VERSION.SDK_INT <= 19) {
                    actionBar.setSubtitle(actionBar.getSubtitle());
                }
            } catch (Exception e) {
                Log.w(a, "Couldn't set content description via JB-MR2 API", e);
            }
        }
        return aVar;
    }

    public static a a(a aVar, Activity activity, Drawable drawable, int i) {
        a aVar2 = new a(activity);
        if (aVar2.a != null) {
            try {
                android.app.ActionBar actionBar = activity.getActionBar();
                aVar2.a.invoke(actionBar, drawable);
                aVar2.b.invoke(actionBar, Integer.valueOf(i));
            } catch (Exception e) {
                Log.w(a, "Couldn't set home-as-up indicator via JB-MR2 API", e);
            }
        } else if (aVar2.c != null) {
            aVar2.c.setImageDrawable(drawable);
        } else {
            Log.w(a, "Couldn't set home-as-up indicator");
        }
        return aVar2;
    }
}
