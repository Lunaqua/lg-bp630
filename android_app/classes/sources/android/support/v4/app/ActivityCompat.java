package android.support.v4.app;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.support.annotation.an;
import android.support.annotation.v;
import android.support.annotation.x;
import android.support.r.a.a;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.content.c;
import android.view.DragEvent;
import android.view.View;
import java.util.List;
import java.util.Map;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ActivityCompat extends c {
    private static PermissionCompatDelegate sDelegate;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface OnRequestPermissionsResultCallback {
        void onRequestPermissionsResult(int i, @af String[] strArr, @af int[] iArr);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface PermissionCompatDelegate {
        boolean onActivityResult(@af Activity activity, @x(a = 0) int i, int i2, @ag Intent intent);

        boolean requestPermissions(@af Activity activity, @af String[] strArr, @x(a = 0) int i);
    }

    @an(a = {an.a.LIBRARY_GROUP})
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface RequestPermissionsRequestCodeValidator {
        void validateRequestPermissionsRequestCode(int i);
    }

    @ak(a = 21)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class SharedElementCallback21Impl extends android.app.SharedElementCallback {
        private final SharedElementCallback mCallback;

        SharedElementCallback21Impl(SharedElementCallback sharedElementCallback) {
            this.mCallback = sharedElementCallback;
        }

        @Override // android.app.SharedElementCallback
        public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectF) {
            return this.mCallback.onCaptureSharedElementSnapshot(view, matrix, rectF);
        }

        @Override // android.app.SharedElementCallback
        public View onCreateSnapshotView(Context context, Parcelable parcelable) {
            return this.mCallback.onCreateSnapshotView(context, parcelable);
        }

        @Override // android.app.SharedElementCallback
        public void onMapSharedElements(List<String> list, Map<String, View> map) {
            this.mCallback.onMapSharedElements(list, map);
        }

        @Override // android.app.SharedElementCallback
        public void onRejectSharedElements(List<View> list) {
            this.mCallback.onRejectSharedElements(list);
        }

        @Override // android.app.SharedElementCallback
        public void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
            this.mCallback.onSharedElementEnd(list, list2, list3);
        }

        @Override // android.app.SharedElementCallback
        public void onSharedElementStart(List<String> list, List<View> list2, List<View> list3) {
            this.mCallback.onSharedElementStart(list, list2, list3);
        }

        @Override // android.app.SharedElementCallback
        @ak(a = 23)
        public void onSharedElementsArrived(List<String> list, List<View> list2, final SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener) {
            this.mCallback.onSharedElementsArrived(list, list2, new SharedElementCallback.OnSharedElementsReadyListener() { // from class: android.support.v4.app.ActivityCompat.SharedElementCallback21Impl.1
                @Override // android.support.v4.app.SharedElementCallback.OnSharedElementsReadyListener
                public void onSharedElementsReady() {
                    onSharedElementsReadyListener.onSharedElementsReady();
                }
            });
        }
    }

    protected ActivityCompat() {
    }

    public static void finishAffinity(@af Activity activity) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.finishAffinity();
        } else {
            activity.finish();
        }
    }

    public static void finishAfterTransition(@af Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.finishAfterTransition();
        } else {
            activity.finish();
        }
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public static PermissionCompatDelegate getPermissionCompatDelegate() {
        return sDelegate;
    }

    @ag
    public static Uri getReferrer(@af Activity activity) {
        if (Build.VERSION.SDK_INT >= 22) {
            return activity.getReferrer();
        }
        Intent intent = activity.getIntent();
        Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.REFERRER");
        if (uri != null) {
            return uri;
        }
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        if (stringExtra != null) {
            return Uri.parse(stringExtra);
        }
        return null;
    }

    @Deprecated
    public static boolean invalidateOptionsMenu(Activity activity) {
        activity.invalidateOptionsMenu();
        return true;
    }

    public static void postponeEnterTransition(@af Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.postponeEnterTransition();
        }
    }

    @ag
    public static a requestDragAndDropPermissions(Activity activity, DragEvent dragEvent) {
        return a.a(activity, dragEvent);
    }

    public static void requestPermissions(@af final Activity activity, @af final String[] strArr, @x(a = 0) final int i) {
        PermissionCompatDelegate permissionCompatDelegate = sDelegate;
        if (permissionCompatDelegate == null || !permissionCompatDelegate.requestPermissions(activity, strArr, i)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (activity instanceof RequestPermissionsRequestCodeValidator) {
                    ((RequestPermissionsRequestCodeValidator) activity).validateRequestPermissionsRequestCode(i);
                }
                activity.requestPermissions(strArr, i);
            } else if (activity instanceof OnRequestPermissionsResultCallback) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: android.support.v4.app.ActivityCompat.1
                    @Override // java.lang.Runnable
                    public void run() {
                        int[] iArr = new int[strArr.length];
                        PackageManager packageManager = activity.getPackageManager();
                        String packageName = activity.getPackageName();
                        int length = strArr.length;
                        for (int i2 = 0; i2 < length; i2++) {
                            iArr[i2] = packageManager.checkPermission(strArr[i2], packageName);
                        }
                        ((OnRequestPermissionsResultCallback) activity).onRequestPermissionsResult(i, strArr, iArr);
                    }
                });
            }
        }
    }

    @af
    public static <T extends View> T requireViewById(@af Activity activity, @v int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            return (T) activity.requireViewById(i);
        }
        T t = (T) activity.findViewById(i);
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("ID does not reference a View inside this Activity");
    }

    public static void setEnterSharedElementCallback(@af Activity activity, @ag SharedElementCallback sharedElementCallback) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.setEnterSharedElementCallback(sharedElementCallback != null ? new SharedElementCallback21Impl(sharedElementCallback) : null);
        }
    }

    public static void setExitSharedElementCallback(@af Activity activity, @ag SharedElementCallback sharedElementCallback) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.setExitSharedElementCallback(sharedElementCallback != null ? new SharedElementCallback21Impl(sharedElementCallback) : null);
        }
    }

    public static void setPermissionCompatDelegate(@ag PermissionCompatDelegate permissionCompatDelegate) {
        sDelegate = permissionCompatDelegate;
    }

    public static boolean shouldShowRequestPermissionRationale(@af Activity activity, @af String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
        return false;
    }

    public static void startActivityForResult(@af Activity activity, @af Intent intent, int i, @ag Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.startActivityForResult(intent, i, bundle);
        } else {
            activity.startActivityForResult(intent, i);
        }
    }

    public static void startIntentSenderForResult(@af Activity activity, @af IntentSender intentSender, int i, @ag Intent intent, int i2, int i3, int i4, @ag Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
        } else {
            activity.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
        }
    }

    public static void startPostponedEnterTransition(@af Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.startPostponedEnterTransition();
        }
    }
}
