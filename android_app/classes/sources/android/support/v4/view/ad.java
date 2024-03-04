package android.support.v4.view;

import android.os.Build;
import android.support.b.a;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class ad {
    public static final int a = 0;
    public static final int b = 1;

    private ad() {
    }

    public static int a(@android.support.annotation.af ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT >= 18) {
            return viewGroup.getLayoutMode();
        }
        return 0;
    }

    public static void a(@android.support.annotation.af ViewGroup viewGroup, int i) {
        if (Build.VERSION.SDK_INT >= 18) {
            viewGroup.setLayoutMode(i);
        }
    }

    @Deprecated
    public static void a(ViewGroup viewGroup, boolean z) {
        viewGroup.setMotionEventSplittingEnabled(z);
    }

    @Deprecated
    public static boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return viewGroup.onRequestSendAccessibilityEvent(view, accessibilityEvent);
    }

    public static void b(@android.support.annotation.af ViewGroup viewGroup, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            viewGroup.setTransitionGroup(z);
        } else {
            viewGroup.setTag(a.e.tag_transition_group, Boolean.valueOf(z));
        }
    }

    public static boolean b(@android.support.annotation.af ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT >= 21) {
            return viewGroup.isTransitionGroup();
        }
        Boolean bool = (Boolean) viewGroup.getTag(a.e.tag_transition_group);
        return ((bool == null || !bool.booleanValue()) && viewGroup.getBackground() == null && ab.O(viewGroup) == null) ? false : true;
    }

    public static int c(@android.support.annotation.af ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT >= 21) {
            return viewGroup.getNestedScrollAxes();
        }
        if (viewGroup instanceof r) {
            return ((r) viewGroup).getNestedScrollAxes();
        }
        return 0;
    }
}
