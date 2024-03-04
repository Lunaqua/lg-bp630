package android.support.v4.view;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class ae {
    private static final String a = "ViewParentCompat";

    private ae() {
    }

    public static void a(ViewParent viewParent, View view) {
        a(viewParent, view, 0);
    }

    public static void a(ViewParent viewParent, View view, int i) {
        if (viewParent instanceof s) {
            ((s) viewParent).c(view, i);
        } else if (i == 0) {
            if (Build.VERSION.SDK_INT < 21) {
                if (viewParent instanceof r) {
                    ((r) viewParent).onStopNestedScroll(view);
                    return;
                }
                return;
            }
            try {
                viewParent.onStopNestedScroll(view);
            } catch (AbstractMethodError e) {
                Log.e(a, "ViewParent " + viewParent + " does not implement interface method onStopNestedScroll", e);
            }
        }
    }

    public static void a(ViewParent viewParent, View view, int i, int i2, int i3, int i4) {
        a(viewParent, view, i, i2, i3, i4, 0);
    }

    public static void a(ViewParent viewParent, View view, int i, int i2, int i3, int i4, int i5) {
        if (viewParent instanceof s) {
            ((s) viewParent).a(view, i, i2, i3, i4, i5);
        } else if (i5 == 0) {
            if (Build.VERSION.SDK_INT < 21) {
                if (viewParent instanceof r) {
                    ((r) viewParent).onNestedScroll(view, i, i2, i3, i4);
                    return;
                }
                return;
            }
            try {
                viewParent.onNestedScroll(view, i, i2, i3, i4);
            } catch (AbstractMethodError e) {
                Log.e(a, "ViewParent " + viewParent + " does not implement interface method onNestedScroll", e);
            }
        }
    }

    public static void a(ViewParent viewParent, View view, int i, int i2, int[] iArr) {
        a(viewParent, view, i, i2, iArr, 0);
    }

    public static void a(ViewParent viewParent, View view, int i, int i2, int[] iArr, int i3) {
        if (viewParent instanceof s) {
            ((s) viewParent).a(view, i, i2, iArr, i3);
        } else if (i3 == 0) {
            if (Build.VERSION.SDK_INT < 21) {
                if (viewParent instanceof r) {
                    ((r) viewParent).onNestedPreScroll(view, i, i2, iArr);
                    return;
                }
                return;
            }
            try {
                viewParent.onNestedPreScroll(view, i, i2, iArr);
            } catch (AbstractMethodError e) {
                Log.e(a, "ViewParent " + viewParent + " does not implement interface method onNestedPreScroll", e);
            }
        }
    }

    public static boolean a(ViewParent viewParent, View view, float f, float f2) {
        if (Build.VERSION.SDK_INT < 21) {
            if (viewParent instanceof r) {
                return ((r) viewParent).onNestedPreFling(view, f, f2);
            }
            return false;
        }
        try {
            return viewParent.onNestedPreFling(view, f, f2);
        } catch (AbstractMethodError e) {
            Log.e(a, "ViewParent " + viewParent + " does not implement interface method onNestedPreFling", e);
            return false;
        }
    }

    public static boolean a(ViewParent viewParent, View view, float f, float f2, boolean z) {
        if (Build.VERSION.SDK_INT < 21) {
            if (viewParent instanceof r) {
                return ((r) viewParent).onNestedFling(view, f, f2, z);
            }
            return false;
        }
        try {
            return viewParent.onNestedFling(view, f, f2, z);
        } catch (AbstractMethodError e) {
            Log.e(a, "ViewParent " + viewParent + " does not implement interface method onNestedFling", e);
            return false;
        }
    }

    public static boolean a(ViewParent viewParent, View view, View view2, int i) {
        return a(viewParent, view, view2, i, 0);
    }

    public static boolean a(ViewParent viewParent, View view, View view2, int i, int i2) {
        if (viewParent instanceof s) {
            return ((s) viewParent).a(view, view2, i, i2);
        }
        if (i2 == 0) {
            if (Build.VERSION.SDK_INT < 21) {
                if (viewParent instanceof r) {
                    return ((r) viewParent).onStartNestedScroll(view, view2, i);
                }
                return false;
            }
            try {
                return viewParent.onStartNestedScroll(view, view2, i);
            } catch (AbstractMethodError e) {
                Log.e(a, "ViewParent " + viewParent + " does not implement interface method onStartNestedScroll", e);
                return false;
            }
        }
        return false;
    }

    @Deprecated
    public static boolean a(ViewParent viewParent, View view, AccessibilityEvent accessibilityEvent) {
        return viewParent.requestSendAccessibilityEvent(view, accessibilityEvent);
    }

    public static void b(ViewParent viewParent, View view, View view2, int i) {
        b(viewParent, view, view2, i, 0);
    }

    public static void b(ViewParent viewParent, View view, View view2, int i, int i2) {
        if (viewParent instanceof s) {
            ((s) viewParent).b(view, view2, i, i2);
        } else if (i2 == 0) {
            if (Build.VERSION.SDK_INT < 21) {
                if (viewParent instanceof r) {
                    ((r) viewParent).onNestedScrollAccepted(view, view2, i);
                    return;
                }
                return;
            }
            try {
                viewParent.onNestedScrollAccepted(view, view2, i);
            } catch (AbstractMethodError e) {
                Log.e(a, "ViewParent " + viewParent + " does not implement interface method onNestedScrollAccepted", e);
            }
        }
    }

    public static void c(ViewParent viewParent, View view, View view2, int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            viewParent.notifySubtreeAccessibilityStateChanged(view, view2, i);
        }
    }
}
