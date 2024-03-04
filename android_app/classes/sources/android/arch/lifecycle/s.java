package android.arch.lifecycle;

import android.app.Application;
import android.support.annotation.ac;
import android.support.annotation.af;
import java.lang.reflect.InvocationTargetException;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class s {
    private static final String a = "android.arch.lifecycle.ViewModelProvider.DefaultKey";
    private final b b;
    private final t c;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a extends c {
        private static a a;
        private Application b;

        public a(@af Application application) {
            this.b = application;
        }

        @af
        public static a a(@af Application application) {
            if (a == null) {
                a = new a(application);
            }
            return a;
        }

        @Override // android.arch.lifecycle.s.c, android.arch.lifecycle.s.b
        @af
        public <T extends r> T create(@af Class<T> cls) {
            if (AndroidViewModel.class.isAssignableFrom(cls)) {
                try {
                    return cls.getConstructor(Application.class).newInstance(this.b);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e);
                } catch (InstantiationException e2) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e2);
                } catch (NoSuchMethodException e3) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e3);
                } catch (InvocationTargetException e4) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e4);
                }
            }
            return (T) super.create(cls);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface b {
        @af
        <T extends r> T create(@af Class<T> cls);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class c implements b {
        @Override // android.arch.lifecycle.s.b
        @af
        public <T extends r> T create(@af Class<T> cls) {
            try {
                return cls.newInstance();
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot create an instance of " + cls, e);
            } catch (InstantiationException e2) {
                throw new RuntimeException("Cannot create an instance of " + cls, e2);
            }
        }
    }

    public s(@af t tVar, @af b bVar) {
        this.b = bVar;
        this.c = tVar;
    }

    public s(@af u uVar, @af b bVar) {
        this(uVar.getViewModelStore(), bVar);
    }

    @ac
    @af
    public <T extends r> T a(@af Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return (T) a("android.arch.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @ac
    @af
    public <T extends r> T a(@af String str, @af Class<T> cls) {
        T t = (T) this.c.a(str);
        if (cls.isInstance(t)) {
            return t;
        }
        T t2 = (T) this.b.create(cls);
        this.c.a(str, t2);
        return t2;
    }
}
