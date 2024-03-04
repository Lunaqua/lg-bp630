package android.support.v4.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.support.v4.j.n;
import android.util.Pair;
import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ActivityOptionsCompat {
    public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
    public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";

    @ak(a = 16)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class ActivityOptionsCompatImpl extends ActivityOptionsCompat {
        private final ActivityOptions mActivityOptions;

        ActivityOptionsCompatImpl(ActivityOptions activityOptions) {
            this.mActivityOptions = activityOptions;
        }

        @Override // android.support.v4.app.ActivityOptionsCompat
        public Rect getLaunchBounds() {
            if (Build.VERSION.SDK_INT < 24) {
                return null;
            }
            return this.mActivityOptions.getLaunchBounds();
        }

        @Override // android.support.v4.app.ActivityOptionsCompat
        public void requestUsageTimeReport(PendingIntent pendingIntent) {
            if (Build.VERSION.SDK_INT >= 23) {
                this.mActivityOptions.requestUsageTimeReport(pendingIntent);
            }
        }

        @Override // android.support.v4.app.ActivityOptionsCompat
        public ActivityOptionsCompat setLaunchBounds(@ag Rect rect) {
            return Build.VERSION.SDK_INT < 24 ? this : new ActivityOptionsCompatImpl(this.mActivityOptions.setLaunchBounds(rect));
        }

        @Override // android.support.v4.app.ActivityOptionsCompat
        public Bundle toBundle() {
            return this.mActivityOptions.toBundle();
        }

        @Override // android.support.v4.app.ActivityOptionsCompat
        public void update(ActivityOptionsCompat activityOptionsCompat) {
            if (activityOptionsCompat instanceof ActivityOptionsCompatImpl) {
                this.mActivityOptions.update(((ActivityOptionsCompatImpl) activityOptionsCompat).mActivityOptions);
            }
        }
    }

    protected ActivityOptionsCompat() {
    }

    @af
    public static ActivityOptionsCompat makeBasic() {
        return Build.VERSION.SDK_INT >= 23 ? new ActivityOptionsCompatImpl(ActivityOptions.makeBasic()) : new ActivityOptionsCompat();
    }

    @af
    public static ActivityOptionsCompat makeClipRevealAnimation(@af View view, int i, int i2, int i3, int i4) {
        return Build.VERSION.SDK_INT >= 23 ? new ActivityOptionsCompatImpl(ActivityOptions.makeClipRevealAnimation(view, i, i2, i3, i4)) : new ActivityOptionsCompat();
    }

    @af
    public static ActivityOptionsCompat makeCustomAnimation(@af Context context, int i, int i2) {
        return Build.VERSION.SDK_INT >= 16 ? new ActivityOptionsCompatImpl(ActivityOptions.makeCustomAnimation(context, i, i2)) : new ActivityOptionsCompat();
    }

    @af
    public static ActivityOptionsCompat makeScaleUpAnimation(@af View view, int i, int i2, int i3, int i4) {
        return Build.VERSION.SDK_INT >= 16 ? new ActivityOptionsCompatImpl(ActivityOptions.makeScaleUpAnimation(view, i, i2, i3, i4)) : new ActivityOptionsCompat();
    }

    @af
    public static ActivityOptionsCompat makeSceneTransitionAnimation(@af Activity activity, @af View view, @af String str) {
        return Build.VERSION.SDK_INT >= 21 ? new ActivityOptionsCompatImpl(ActivityOptions.makeSceneTransitionAnimation(activity, view, str)) : new ActivityOptionsCompat();
    }

    @af
    public static ActivityOptionsCompat makeSceneTransitionAnimation(@af Activity activity, n<View, String>... nVarArr) {
        if (Build.VERSION.SDK_INT >= 21) {
            Pair[] pairArr = null;
            if (nVarArr != null) {
                pairArr = new Pair[nVarArr.length];
                for (int i = 0; i < nVarArr.length; i++) {
                    pairArr[i] = Pair.create(nVarArr[i].a, nVarArr[i].b);
                }
            }
            return new ActivityOptionsCompatImpl(ActivityOptions.makeSceneTransitionAnimation(activity, pairArr));
        }
        return new ActivityOptionsCompat();
    }

    @af
    public static ActivityOptionsCompat makeTaskLaunchBehind() {
        return Build.VERSION.SDK_INT >= 21 ? new ActivityOptionsCompatImpl(ActivityOptions.makeTaskLaunchBehind()) : new ActivityOptionsCompat();
    }

    @af
    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(@af View view, @af Bitmap bitmap, int i, int i2) {
        return Build.VERSION.SDK_INT >= 16 ? new ActivityOptionsCompatImpl(ActivityOptions.makeThumbnailScaleUpAnimation(view, bitmap, i, i2)) : new ActivityOptionsCompat();
    }

    @ag
    public Rect getLaunchBounds() {
        return null;
    }

    public void requestUsageTimeReport(@af PendingIntent pendingIntent) {
    }

    @af
    public ActivityOptionsCompat setLaunchBounds(@ag Rect rect) {
        return this;
    }

    @ag
    public Bundle toBundle() {
        return null;
    }

    public void update(@af ActivityOptionsCompat activityOptionsCompat) {
    }
}
