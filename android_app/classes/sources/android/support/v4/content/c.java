package android.support.v4.content;

import android.accessibilityservice.AccessibilityService;
import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.app.admin.DevicePolicyManager;
import android.app.job.JobScheduler;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.RestrictionsManager;
import android.content.pm.LauncherApps;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.tv.TvInputManager;
import android.net.ConnectivityManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.os.PowerManager;
import android.os.Process;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.m;
import android.support.annotation.p;
import android.support.v4.app.NotificationCompat;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextServicesManager;
import java.io.File;
import java.util.HashMap;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c {
    private static final String TAG = "ContextCompat";
    private static final Object sLock = new Object();
    private static TypedValue sTempValue;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class a {
        static final HashMap<Class<?>, String> a = new HashMap<>();

        static {
            if (Build.VERSION.SDK_INT > 22) {
                a.put(SubscriptionManager.class, "telephony_subscription_service");
                a.put(UsageStatsManager.class, "usagestats");
            }
            if (Build.VERSION.SDK_INT > 21) {
                a.put(AppWidgetManager.class, "appwidget");
                a.put(BatteryManager.class, "batterymanager");
                a.put(CameraManager.class, "camera");
                a.put(JobScheduler.class, "jobscheduler");
                a.put(LauncherApps.class, "launcherapps");
                a.put(MediaProjectionManager.class, "media_projection");
                a.put(MediaSessionManager.class, "media_session");
                a.put(RestrictionsManager.class, "restrictions");
                a.put(TelecomManager.class, "telecom");
                a.put(TvInputManager.class, "tv_input");
            }
            if (Build.VERSION.SDK_INT > 19) {
                a.put(AppOpsManager.class, "appops");
                a.put(CaptioningManager.class, "captioning");
                a.put(ConsumerIrManager.class, "consumer_ir");
                a.put(PrintManager.class, "print");
            }
            if (Build.VERSION.SDK_INT > 18) {
                a.put(BluetoothManager.class, "bluetooth");
            }
            if (Build.VERSION.SDK_INT > 17) {
                a.put(DisplayManager.class, "display");
                a.put(UserManager.class, "user");
            }
            if (Build.VERSION.SDK_INT > 16) {
                a.put(InputManager.class, "input");
                a.put(MediaRouter.class, "media_router");
                a.put(NsdManager.class, "servicediscovery");
            }
            a.put(AccessibilityService.class, "accessibility");
            a.put(AccountManager.class, "account");
            a.put(ActivityManager.class, "activity");
            a.put(AlarmManager.class, NotificationCompat.CATEGORY_ALARM);
            a.put(AudioManager.class, "audio");
            a.put(ClipboardManager.class, "clipboard");
            a.put(ConnectivityManager.class, "connectivity");
            a.put(DevicePolicyManager.class, "device_policy");
            a.put(DownloadManager.class, "download");
            a.put(DropBoxManager.class, "dropbox");
            a.put(InputMethodManager.class, "input_method");
            a.put(KeyguardManager.class, "keyguard");
            a.put(LayoutInflater.class, "layout_inflater");
            a.put(LocationManager.class, "location");
            a.put(NfcManager.class, "nfc");
            a.put(NotificationManager.class, "notification");
            a.put(PowerManager.class, "power");
            a.put(SearchManager.class, "search");
            a.put(SensorManager.class, "sensor");
            a.put(StorageManager.class, "storage");
            a.put(TelephonyManager.class, "phone");
            a.put(TextServicesManager.class, "textservices");
            a.put(UiModeManager.class, "uimode");
            a.put(UsbManager.class, "usb");
            a.put(Vibrator.class, "vibrator");
            a.put(WallpaperManager.class, "wallpaper");
            a.put(WifiP2pManager.class, "wifip2p");
            a.put(WifiManager.class, "wifi");
            a.put(WindowManager.class, "window");
        }

        private a() {
        }
    }

    private static File buildPath(File file, String... strArr) {
        for (String str : strArr) {
            if (file == null) {
                file = new File(str);
            } else if (str != null) {
                file = new File(file, str);
            }
        }
        return file;
    }

    public static int checkSelfPermission(@af Context context, @af String str) {
        if (str != null) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }

    @ag
    public static Context createDeviceProtectedStorageContext(@af Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return context.createDeviceProtectedStorageContext();
        }
        return null;
    }

    private static synchronized File createFilesDir(File file) {
        synchronized (c.class) {
            if (file.exists() || file.mkdirs()) {
                return file;
            }
            if (file.exists()) {
                return file;
            }
            Log.w(TAG, "Unable to create files subdir " + file.getPath());
            return null;
        }
    }

    public static File getCodeCacheDir(@af Context context) {
        return Build.VERSION.SDK_INT >= 21 ? context.getCodeCacheDir() : createFilesDir(new File(context.getApplicationInfo().dataDir, "code_cache"));
    }

    @android.support.annotation.k
    public static int getColor(@af Context context, @m int i) {
        return Build.VERSION.SDK_INT >= 23 ? context.getColor(i) : context.getResources().getColor(i);
    }

    @ag
    public static ColorStateList getColorStateList(@af Context context, @m int i) {
        return Build.VERSION.SDK_INT >= 23 ? context.getColorStateList(i) : context.getResources().getColorStateList(i);
    }

    @ag
    public static File getDataDir(@af Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return context.getDataDir();
        }
        String str = context.getApplicationInfo().dataDir;
        if (str != null) {
            return new File(str);
        }
        return null;
    }

    @ag
    public static Drawable getDrawable(@af Context context, @p int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getDrawable(i);
        }
        if (Build.VERSION.SDK_INT < 16) {
            synchronized (sLock) {
                if (sTempValue == null) {
                    sTempValue = new TypedValue();
                }
                context.getResources().getValue(i, sTempValue, true);
                i = sTempValue.resourceId;
            }
        }
        return context.getResources().getDrawable(i);
    }

    @af
    public static File[] getExternalCacheDirs(@af Context context) {
        return Build.VERSION.SDK_INT >= 19 ? context.getExternalCacheDirs() : new File[]{context.getExternalCacheDir()};
    }

    @af
    public static File[] getExternalFilesDirs(@af Context context, @ag String str) {
        return Build.VERSION.SDK_INT >= 19 ? context.getExternalFilesDirs(str) : new File[]{context.getExternalFilesDir(str)};
    }

    @ag
    public static File getNoBackupFilesDir(@af Context context) {
        return Build.VERSION.SDK_INT >= 21 ? context.getNoBackupFilesDir() : createFilesDir(new File(context.getApplicationInfo().dataDir, "no_backup"));
    }

    @af
    public static File[] getObbDirs(@af Context context) {
        return Build.VERSION.SDK_INT >= 19 ? context.getObbDirs() : new File[]{context.getObbDir()};
    }

    @ag
    public static <T> T getSystemService(@af Context context, @af Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 23) {
            return (T) context.getSystemService(cls);
        }
        String systemServiceName = getSystemServiceName(context, cls);
        if (systemServiceName != null) {
            return (T) context.getSystemService(systemServiceName);
        }
        return null;
    }

    @ag
    public static String getSystemServiceName(@af Context context, @af Class<?> cls) {
        return Build.VERSION.SDK_INT >= 23 ? context.getSystemServiceName(cls) : a.a.get(cls);
    }

    public static boolean isDeviceProtectedStorage(@af Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return context.isDeviceProtectedStorage();
        }
        return false;
    }

    public static boolean startActivities(@af Context context, @af Intent[] intentArr) {
        return startActivities(context, intentArr, null);
    }

    public static boolean startActivities(@af Context context, @af Intent[] intentArr, @ag Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            context.startActivities(intentArr, bundle);
            return true;
        }
        context.startActivities(intentArr);
        return true;
    }

    public static void startActivity(@af Context context, @af Intent intent, @ag Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            context.startActivity(intent, bundle);
        } else {
            context.startActivity(intent);
        }
    }

    public static void startForegroundService(@af Context context, @af Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }
}
