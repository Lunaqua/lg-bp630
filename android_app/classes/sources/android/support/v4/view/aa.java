package android.support.v4.view;

import android.view.VelocityTracker;
@Deprecated
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class aa {
    private aa() {
    }

    @Deprecated
    public static float a(VelocityTracker velocityTracker, int i) {
        return velocityTracker.getXVelocity(i);
    }

    @Deprecated
    public static float b(VelocityTracker velocityTracker, int i) {
        return velocityTracker.getYVelocity(i);
    }
}
