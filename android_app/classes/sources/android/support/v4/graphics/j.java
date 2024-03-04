package android.support.v4.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.support.annotation.an;
import android.support.v4.content.b.d;
import android.support.v4.h.d;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;
@ak(a = 26)
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class j extends h {
    private static final String h = "TypefaceCompatApi26Impl";
    private static final String i = "android.graphics.FontFamily";
    private static final String j = "addFontFromAssetManager";
    private static final String k = "addFontFromBuffer";
    private static final String l = "createFromFamiliesWithDefault";
    private static final String m = "freeze";
    private static final String n = "abortCreation";
    private static final int o = -1;
    private static final String p = "sans-serif";
    protected final Class a;
    protected final Constructor b;
    protected final Method c;
    protected final Method d;
    protected final Method e;
    protected final Method f;
    protected final Method g;

    public j() {
        Method method;
        Constructor constructor;
        Method method2;
        Method method3;
        Method method4;
        Method method5;
        Class cls = null;
        try {
            Class a = a();
            constructor = a(a);
            method2 = b(a);
            method3 = c(a);
            method4 = d(a);
            method5 = e(a);
            method = f(a);
            cls = a;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e(h, "Unable to collect necessary methods for class " + e.getClass().getName(), e);
            method = null;
            constructor = null;
            method2 = null;
            method3 = null;
            method4 = null;
            method5 = null;
        }
        this.a = cls;
        this.b = constructor;
        this.c = method2;
        this.d = method3;
        this.e = method4;
        this.f = method5;
        this.g = method;
    }

    private boolean a(Context context, Object obj, String str, int i2, int i3, int i4, @ag FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.c.invoke(obj, context.getAssets(), str, 0, false, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean a(Object obj, ByteBuffer byteBuffer, int i2, int i3, int i4) {
        try {
            return ((Boolean) this.d.invoke(obj, byteBuffer, Integer.valueOf(i2), null, Integer.valueOf(i3), Integer.valueOf(i4))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean b() {
        if (this.c == null) {
            Log.w(h, "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.c != null;
    }

    private boolean b(Object obj) {
        try {
            return ((Boolean) this.e.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Object c() {
        try {
            return this.b.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void c(Object obj) {
        try {
            this.f.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.support.v4.graphics.l
    @ag
    public Typeface a(Context context, Resources resources, int i2, String str, int i3) {
        if (b()) {
            Object c = c();
            if (!a(context, c, str, 0, -1, -1, null)) {
                c(c);
                return null;
            } else if (b(c)) {
                return a(c);
            } else {
                return null;
            }
        }
        return super.a(context, resources, i2, str, i3);
    }

    @Override // android.support.v4.graphics.h, android.support.v4.graphics.l
    public Typeface a(Context context, @ag CancellationSignal cancellationSignal, @af d.c[] cVarArr, int i2) {
        if (cVarArr.length < 1) {
            return null;
        }
        if (!b()) {
            d.c a = a(cVarArr, i2);
            try {
                ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(a.a(), "r", cancellationSignal);
                if (openFileDescriptor == null) {
                    if (openFileDescriptor != null) {
                        openFileDescriptor.close();
                    }
                    return null;
                }
                Typeface build = new Typeface.Builder(openFileDescriptor.getFileDescriptor()).setWeight(a.c()).setItalic(a.d()).build();
                if (openFileDescriptor != null) {
                    openFileDescriptor.close();
                }
                return build;
            } catch (IOException unused) {
                return null;
            }
        }
        Map<Uri, ByteBuffer> a2 = android.support.v4.h.d.a(context, cVarArr, cancellationSignal);
        Object c = c();
        boolean z = false;
        for (d.c cVar : cVarArr) {
            ByteBuffer byteBuffer = a2.get(cVar.a());
            if (byteBuffer != null) {
                if (!a(c, byteBuffer, cVar.b(), cVar.c(), cVar.d() ? 1 : 0)) {
                    c(c);
                    return null;
                }
                z = true;
            }
        }
        if (!z) {
            c(c);
            return null;
        } else if (b(c)) {
            return Typeface.create(a(c), i2);
        } else {
            return null;
        }
    }

    @Override // android.support.v4.graphics.l
    public Typeface a(Context context, d.c cVar, Resources resources, int i2) {
        d.C0017d[] a;
        if (b()) {
            Object c = c();
            for (d.C0017d c0017d : cVar.a()) {
                if (!a(context, c, c0017d.a(), c0017d.e(), c0017d.b(), c0017d.c() ? 1 : 0, FontVariationAxis.fromFontVariationSettings(c0017d.d()))) {
                    c(c);
                    return null;
                }
            }
            if (b(c)) {
                return a(c);
            }
            return null;
        }
        return super.a(context, cVar, resources, i2);
    }

    protected Typeface a(Object obj) {
        try {
            Object newInstance = Array.newInstance(this.a, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) this.g.invoke(null, newInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    protected Class a() {
        return Class.forName(i);
    }

    protected Constructor a(Class cls) {
        return cls.getConstructor(new Class[0]);
    }

    protected Method b(Class cls) {
        return cls.getMethod(j, AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class);
    }

    protected Method c(Class cls) {
        return cls.getMethod(k, ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE);
    }

    protected Method d(Class cls) {
        return cls.getMethod(m, new Class[0]);
    }

    protected Method e(Class cls) {
        return cls.getMethod(n, new Class[0]);
    }

    protected Method f(Class cls) {
        Method declaredMethod = Typeface.class.getDeclaredMethod(l, Array.newInstance(cls, 1).getClass(), Integer.TYPE, Integer.TYPE);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
