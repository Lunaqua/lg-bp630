package android.support.v7.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.af;
import android.support.annotation.am;
import android.support.annotation.av;
import android.util.Log;
import java.util.Calendar;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class l {
    private static final String a = "TwilightManager";
    private static final int b = 6;
    private static final int c = 22;
    private static l d;
    private final Context e;
    private final LocationManager f;
    private final a g = new a();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a {
        boolean a;
        long b;
        long c;
        long d;
        long e;
        long f;

        a() {
        }
    }

    @av
    l(@af Context context, @af LocationManager locationManager) {
        this.e = context;
        this.f = locationManager;
    }

    @am(c = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    private Location a(String str) {
        try {
            if (this.f.isProviderEnabled(str)) {
                return this.f.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e) {
            Log.d(a, "Failed to get last known location", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static l a(@af Context context) {
        if (d == null) {
            Context applicationContext = context.getApplicationContext();
            d = new l(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return d;
    }

    private void a(@af Location location) {
        long j;
        a aVar = this.g;
        long currentTimeMillis = System.currentTimeMillis();
        k a2 = k.a();
        a2.a(currentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j2 = a2.c;
        a2.a(currentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = a2.e == 1;
        long j3 = a2.d;
        long j4 = a2.c;
        boolean z2 = z;
        a2.a(86400000 + currentTimeMillis, location.getLatitude(), location.getLongitude());
        long j5 = a2.d;
        if (j3 == -1 || j4 == -1) {
            j = 43200000 + currentTimeMillis;
        } else {
            j = (currentTimeMillis > j4 ? 0 + j5 : currentTimeMillis > j3 ? 0 + j4 : 0 + j3) + 60000;
        }
        aVar.a = z2;
        aVar.b = j2;
        aVar.c = j3;
        aVar.d = j4;
        aVar.e = j5;
        aVar.f = j;
    }

    @av
    static void a(l lVar) {
        d = lVar;
    }

    @SuppressLint({"MissingPermission"})
    private Location b() {
        Location a2 = android.support.v4.content.j.a(this.e, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? a("network") : null;
        Location a3 = android.support.v4.content.j.a(this.e, "android.permission.ACCESS_FINE_LOCATION") == 0 ? a("gps") : null;
        return (a3 == null || a2 == null) ? a3 != null ? a3 : a2 : a3.getTime() > a2.getTime() ? a3 : a2;
    }

    private boolean c() {
        return this.g.f > System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        a aVar = this.g;
        if (c()) {
            return aVar.a;
        }
        Location b2 = b();
        if (b2 != null) {
            a(b2);
            return aVar.a;
        }
        Log.i(a, "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }
}
