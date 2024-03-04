package android.support.v4.graphics;

import android.graphics.Typeface;
import android.support.annotation.ak;
import android.support.annotation.an;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@ak(a = 28)
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class k extends j {
    private static final String h = "TypefaceCompatApi28Impl";
    private static final String i = "createFromFamiliesWithDefault";
    private static final int j = -1;
    private static final String k = "sans-serif";

    @Override // android.support.v4.graphics.j
    protected Typeface a(Object obj) {
        try {
            Object newInstance = Array.newInstance(this.a, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) this.g.invoke(null, newInstance, k, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.support.v4.graphics.j
    protected Method f(Class cls) {
        Method declaredMethod = Typeface.class.getDeclaredMethod(i, Array.newInstance(cls, 1).getClass(), String.class, Integer.TYPE, Integer.TYPE);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
