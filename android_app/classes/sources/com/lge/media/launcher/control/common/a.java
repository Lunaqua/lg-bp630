package com.lge.media.launcher.control.common;

import android.util.Log;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a {
    private static final boolean a = true;
    private static final String b = "AV_LOG";

    public static String a() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        if (stackTraceElement == null) {
            return " ";
        }
        String fileName = stackTraceElement.getFileName();
        String methodName = stackTraceElement.getMethodName();
        int lineNumber = stackTraceElement.getLineNumber();
        return "(at " + fileName + " " + methodName + " " + Integer.toString(lineNumber) + ")";
    }

    public static void a(String str) {
        Log.e(b, str + a());
    }

    public static void a(String str, Throwable th) {
        Log.e(b, str + a(), th);
    }

    public static void b(String str) {
        Log.d(b, str + a());
    }

    public static void b(String str, Throwable th) {
        Log.d(b, str + a(), th);
    }

    public static void c(String str) {
        Log.i(b, str + a());
    }

    public static void c(String str, Throwable th) {
        Log.i(b, str + a(), th);
    }

    public static void d(String str) {
        Log.v(b, str + a());
    }

    public static void d(String str, Throwable th) {
        Log.v(b, str + a(), th);
    }

    public static void e(String str) {
        Log.w(b, str + a());
    }

    public static void e(String str, Throwable th) {
        Log.w(b, str + a(), th);
    }
}
