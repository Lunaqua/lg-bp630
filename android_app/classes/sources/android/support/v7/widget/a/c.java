package android.support.v7.widget.a;

import android.graphics.Canvas;
import android.os.Build;
import android.support.v4.view.ab;
import android.support.v7.d.a;
import android.support.v7.widget.RecyclerView;
import android.view.View;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c implements b {
    static final b a = new c();

    c() {
    }

    private static float a(RecyclerView recyclerView, View view) {
        int childCount = recyclerView.getChildCount();
        float f = 0.0f;
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            if (childAt != view) {
                float M = ab.M(childAt);
                if (M > f) {
                    f = M;
                }
            }
        }
        return f;
    }

    @Override // android.support.v7.widget.a.b
    public void a(Canvas canvas, RecyclerView recyclerView, View view, float f, float f2, int i, boolean z) {
        if (Build.VERSION.SDK_INT >= 21 && z && view.getTag(a.e.item_touch_helper_previous_elevation) == null) {
            Float valueOf = Float.valueOf(ab.M(view));
            ab.m(view, a(recyclerView, view) + 1.0f);
            view.setTag(a.e.item_touch_helper_previous_elevation, valueOf);
        }
        view.setTranslationX(f);
        view.setTranslationY(f2);
    }

    @Override // android.support.v7.widget.a.b
    public void a(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            Object tag = view.getTag(a.e.item_touch_helper_previous_elevation);
            if (tag != null && (tag instanceof Float)) {
                ab.m(view, ((Float) tag).floatValue());
            }
            view.setTag(a.e.item_touch_helper_previous_elevation, null);
        }
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
    }

    @Override // android.support.v7.widget.a.b
    public void b(Canvas canvas, RecyclerView recyclerView, View view, float f, float f2, int i, boolean z) {
    }

    @Override // android.support.v7.widget.a.b
    public void b(View view) {
    }
}
