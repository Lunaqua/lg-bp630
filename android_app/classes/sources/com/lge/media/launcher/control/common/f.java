package com.lge.media.launcher.control.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.SoundPool;
import android.os.Vibrator;
import com.lge.media.launcher.b;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class f {
    public static final String a = "shared_prefs";
    public static int b = 0;
    public static int c = 0;
    public static int d = 0;
    public static int e = 0;
    private static final int g = 50;
    private static boolean h = false;
    private static Vibrator j = null;
    private static SharedPreferences k = null;
    private static final String l = "toggle_preference_sound_effect";
    private static final String m = "toggle_preference_vibration_effect";
    private static final f f = new f();
    private static SoundPool i = null;

    protected f() {
    }

    public static f a() {
        return f;
    }

    public static void a(int i2) {
        if (h) {
            i.play(i2, 1.0f, 1.0f, 0, 0, 1.0f);
        }
        if (b()) {
            j.vibrate(50L);
        }
    }

    public static void a(Context context, SoundPool soundPool) {
        i = new SoundPool(1, 5, 0);
        e = i.load(context, b.l.tap, 1);
        b = i.load(context, b.l.back, 1);
        d = i.load(context, b.l.popup, 1);
        c = i.load(context, b.l.confirm, 1);
        j = (Vibrator) context.getSystemService("vibrator");
        a.b("init haptic");
        k = context.getSharedPreferences("shared_prefs", 0);
        h = c();
    }

    public static void a(SoundPool soundPool) {
        a.b("release haptic");
        if (soundPool != null) {
            soundPool.release();
        }
    }

    public static void a(boolean z) {
        SharedPreferences sharedPreferences = k;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(m, z).apply();
        }
    }

    public static void b(boolean z) {
        SharedPreferences sharedPreferences = k;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(l, z).apply();
        }
        h = z;
    }

    public static boolean b() {
        SharedPreferences sharedPreferences = k;
        return sharedPreferences != null && sharedPreferences.getBoolean(m, false);
    }

    public static void c(boolean z) {
        h = !z ? false : c();
    }

    public static boolean c() {
        SharedPreferences sharedPreferences = k;
        return sharedPreferences != null && sharedPreferences.getBoolean(l, false);
    }

    public static void d() {
        if (h) {
            i.play(e, 1.0f, 1.0f, 0, 0, 1.0f);
        }
        if (b()) {
            j.vibrate(50L);
        }
    }
}
