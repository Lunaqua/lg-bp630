package android.support.v4.a;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class a {
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 4;
    public static final int d = 8;
    public static final int e = 32;
    public static final int f = -1;
    public static final int g = 2;
    public static final int h = 4;
    public static final int i = 8;
    public static final int j = 16;
    public static final int k = 32;

    private a() {
    }

    public static int a(@af AccessibilityServiceInfo accessibilityServiceInfo) {
        return Build.VERSION.SDK_INT >= 18 ? accessibilityServiceInfo.getCapabilities() : accessibilityServiceInfo.getCanRetrieveWindowContent() ? 1 : 0;
    }

    @af
    public static String a(int i2) {
        StringBuilder sb = new StringBuilder();
        String str = "[";
        while (true) {
            sb.append(str);
            while (i2 > 0) {
                int numberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(i2);
                i2 &= numberOfTrailingZeros ^ (-1);
                if (sb.length() > 1) {
                    sb.append(", ");
                }
                if (numberOfTrailingZeros == 1) {
                    str = "FEEDBACK_SPOKEN";
                } else if (numberOfTrailingZeros == 2) {
                    str = "FEEDBACK_HAPTIC";
                } else if (numberOfTrailingZeros == 4) {
                    str = "FEEDBACK_AUDIBLE";
                } else if (numberOfTrailingZeros == 8) {
                    str = "FEEDBACK_VISUAL";
                } else if (numberOfTrailingZeros == 16) {
                    str = "FEEDBACK_GENERIC";
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }

    @ag
    public static String a(@af AccessibilityServiceInfo accessibilityServiceInfo, @af PackageManager packageManager) {
        return Build.VERSION.SDK_INT >= 16 ? accessibilityServiceInfo.loadDescription(packageManager) : accessibilityServiceInfo.getDescription();
    }

    @ag
    public static String b(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 4) {
                    if (i2 != 8) {
                        if (i2 != 16) {
                            if (i2 != 32) {
                                return null;
                            }
                            return "FLAG_REQUEST_FILTER_KEY_EVENTS";
                        }
                        return "FLAG_REPORT_VIEW_IDS";
                    }
                    return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
                }
                return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
            }
            return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
        }
        return "DEFAULT";
    }

    @af
    public static String c(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 4 ? i2 != 8 ? "UNKNOWN" : "CAPABILITY_CAN_FILTER_KEY_EVENTS" : "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY" : "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION" : "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
    }
}
