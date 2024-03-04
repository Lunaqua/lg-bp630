package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.support.annotation.an;
import android.support.v4.content.b.d;
import android.support.v4.h.d;
import android.support.v4.j.r;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;
@ak(a = 24)
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class i extends l {
    private static final String a = "TypefaceCompatApi24Impl";
    private static final String b = "android.graphics.FontFamily";
    private static final String c = "addFontWeightStyle";
    private static final String d = "createFromFamiliesWithDefault";
    private static final Class e;
    private static final Constructor f;
    private static final Method g;
    private static final Method h;

    static {
        Class<?> cls;
        Method method;
        Method method2;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName(b);
            Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
            method2 = cls.getMethod(c, ByteBuffer.class, Integer.TYPE, List.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod(d, Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e(a, e2.getClass().getName(), e2);
            cls = null;
            method = null;
            method2 = null;
        }
        f = constructor;
        e = cls;
        g = method2;
        h = method;
    }

    private static Typeface a(Object obj) {
        try {
            Object newInstance = Array.newInstance(e, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) h.invoke(null, newInstance);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static boolean a() {
        if (g == null) {
            Log.w(a, "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return g != null;
    }

    private static boolean a(Object obj, ByteBuffer byteBuffer, int i, int i2, boolean z) {
        try {
            return ((Boolean) g.invoke(obj, byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    private static Object b() {
        try {
            return f.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // android.support.v4.graphics.l
    public Typeface a(Context context, @ag CancellationSignal cancellationSignal, @af d.c[] cVarArr, int i) {
        Object b2 = b();
        r rVar = new r();
        for (d.c cVar : cVarArr) {
            Uri a2 = cVar.a();
            ByteBuffer byteBuffer = (ByteBuffer) rVar.get(a2);
            if (byteBuffer == null) {
                byteBuffer = m.a(context, cancellationSignal, a2);
                rVar.put(a2, byteBuffer);
            }
            if (!a(b2, byteBuffer, cVar.b(), cVar.c(), cVar.d())) {
                return null;
            }
        }
        return Typeface.create(a(b2), i);
    }

    @Override // android.support.v4.graphics.l
    public Typeface a(Context context, d.c cVar, Resources resources, int i) {
        d.C0017d[] a2;
        Object b2 = b();
        for (d.C0017d c0017d : cVar.a()) {
            ByteBuffer a3 = m.a(context, resources, c0017d.f());
            if (a3 == null || !a(b2, a3, c0017d.e(), c0017d.b(), c0017d.c())) {
                return null;
            }
        }
        return a(b2);
    }
}
