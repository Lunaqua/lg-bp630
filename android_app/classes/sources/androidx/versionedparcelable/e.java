package androidx.versionedparcelable;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.NetworkOnMainThreadException;
import android.os.Parcelable;
import android.support.annotation.af;
import android.support.annotation.ak;
import android.support.annotation.an;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseBooleanArray;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class e {
    private static final String a = "VersionedParcel";
    private static final int b = -1;
    private static final int c = -2;
    private static final int d = -3;
    private static final int e = -4;
    private static final int f = -5;
    private static final int g = -6;
    private static final int h = -7;
    private static final int i = -9;
    private static final int j = 1;
    private static final int k = 2;
    private static final int l = 3;
    private static final int m = 4;
    private static final int n = 5;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a extends RuntimeException {
        public a(Throwable th) {
            super(th);
        }
    }

    private <T> int a(T t) {
        if (t instanceof String) {
            return 4;
        }
        if (t instanceof Parcelable) {
            return 2;
        }
        if (t instanceof h) {
            return 1;
        }
        if (t instanceof Serializable) {
            return 3;
        }
        if (t instanceof IBinder) {
            return 5;
        }
        throw new IllegalArgumentException(t.getClass().getName() + " cannot be VersionedParcelled");
    }

    protected static <T extends h> T a(String str, e eVar) {
        try {
            return (T) Class.forName(str, true, e.class.getClassLoader()).getDeclaredMethod("read", e.class).invoke(null, eVar);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
        } catch (InvocationTargetException e5) {
            if (e5.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e5.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e5);
        }
    }

    private static Class a(Class<? extends h> cls) {
        return Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
    }

    private Exception a(int i2, String str) {
        return b(i2, str);
    }

    @af
    protected static Throwable a(@af Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    private <T, S extends Collection<T>> S a(int i2, S s) {
        int d2 = d();
        if (d2 < 0) {
            return null;
        }
        if (d2 != 0) {
            int d3 = d();
            if (d2 < 0) {
                return null;
            }
            if (d3 == 1) {
                while (d2 > 0) {
                    s.add(t());
                    d2--;
                }
            } else if (d3 == 2) {
                while (d2 > 0) {
                    s.add(k());
                    d2--;
                }
            } else if (d3 == 3) {
                while (d2 > 0) {
                    s.add(u());
                    d2--;
                }
            } else if (d3 == 4) {
                while (d2 > 0) {
                    s.add(h());
                    d2--;
                }
            } else if (d3 == 5) {
                while (d2 > 0) {
                    s.add(i());
                    d2--;
                }
            }
        }
        return s;
    }

    protected static <T extends h> void a(T t, e eVar) {
        try {
            c(t).getDeclaredMethod("write", t.getClass(), e.class).invoke(null, t, eVar);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
        } catch (InvocationTargetException e5) {
            if (!(e5.getCause() instanceof RuntimeException)) {
                throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e5);
            }
            throw ((RuntimeException) e5.getCause());
        }
    }

    private void a(Serializable serializable) {
        if (serializable == null) {
            a((String) null);
            return;
        }
        String name = serializable.getClass().getName();
        a(name);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(serializable);
            objectOutputStream.close();
            a(byteArrayOutputStream.toByteArray());
        } catch (IOException e2) {
            throw new RuntimeException("VersionedParcelable encountered IOException writing serializable object (name = " + name + ")", e2);
        }
    }

    private <T> void a(Collection<T> collection, int i2) {
        c(i2);
        if (collection == null) {
            a(-1);
            return;
        }
        int size = collection.size();
        a(size);
        if (size > 0) {
            int a2 = a((e) collection.iterator().next());
            a(a2);
            if (a2 == 1) {
                for (T t : collection) {
                    a(t);
                }
            } else if (a2 == 2) {
                for (T t2 : collection) {
                    a(t2);
                }
            } else if (a2 == 3) {
                for (T t3 : collection) {
                    a(t3);
                }
            } else if (a2 == 4) {
                for (T t4 : collection) {
                    a(t4);
                }
            } else if (a2 == 5) {
                for (T t5 : collection) {
                    a(t5);
                }
            }
        }
    }

    private Exception b(int i2, String str) {
        switch (i2) {
            case i /* -9 */:
                return (Exception) k();
            case -8:
            default:
                return new RuntimeException("Unknown exception code: " + i2 + " msg " + str);
            case h /* -7 */:
                return new UnsupportedOperationException(str);
            case g /* -6 */:
                return new NetworkOnMainThreadException();
            case f /* -5 */:
                return new IllegalStateException(str);
            case -4:
                return new NullPointerException(str);
            case -3:
                return new IllegalArgumentException(str);
            case -2:
                return new BadParcelableException(str);
            case -1:
                return new SecurityException(str);
        }
    }

    private void b(h hVar) {
        try {
            a(a((Class<? extends h>) hVar.getClass()).getName());
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException(hVar.getClass().getSimpleName() + " does not have a Parcelizer", e2);
        }
    }

    private static <T extends h> Class c(T t) {
        return a((Class<? extends h>) t.getClass());
    }

    private int v() {
        return d();
    }

    public void a(byte b2, int i2) {
        c(i2);
        a((int) b2);
    }

    protected abstract void a(double d2);

    public void a(double d2, int i2) {
        c(i2);
        a(d2);
    }

    protected abstract void a(float f2);

    public void a(float f2, int i2) {
        c(i2);
        a(f2);
    }

    protected abstract void a(int i2);

    public void a(int i2, int i3) {
        c(i3);
        a(i2);
    }

    protected abstract void a(long j2);

    public void a(long j2, int i2) {
        c(i2);
        a(j2);
    }

    protected abstract void a(Bundle bundle);

    public void a(Bundle bundle, int i2) {
        c(i2);
        a(bundle);
    }

    protected abstract void a(IBinder iBinder);

    public void a(IBinder iBinder, int i2) {
        c(i2);
        a(iBinder);
    }

    protected abstract void a(IInterface iInterface);

    public void a(IInterface iInterface, int i2) {
        c(i2);
        a(iInterface);
    }

    protected abstract void a(Parcelable parcelable);

    public void a(Parcelable parcelable, int i2) {
        c(i2);
        a(parcelable);
    }

    @ak(b = 21)
    public void a(Size size, int i2) {
        c(i2);
        a(size != null);
        if (size != null) {
            a(size.getWidth());
            a(size.getHeight());
        }
    }

    @ak(b = 21)
    public void a(SizeF sizeF, int i2) {
        c(i2);
        a(sizeF != null);
        if (sizeF != null) {
            a(sizeF.getWidth());
            a(sizeF.getHeight());
        }
    }

    public void a(SparseBooleanArray sparseBooleanArray, int i2) {
        c(i2);
        if (sparseBooleanArray == null) {
            a(-1);
            return;
        }
        int size = sparseBooleanArray.size();
        a(size);
        for (int i3 = 0; i3 < size; i3++) {
            a(sparseBooleanArray.keyAt(i3));
            a(sparseBooleanArray.valueAt(i3));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(h hVar) {
        if (hVar == null) {
            a((String) null);
            return;
        }
        b(hVar);
        e c2 = c();
        a(hVar, c2);
        c2.b();
    }

    public void a(h hVar, int i2) {
        c(i2);
        a(hVar);
    }

    public void a(Serializable serializable, int i2) {
        c(i2);
        a(serializable);
    }

    public void a(Exception exc, int i2) {
        c(i2);
        if (exc == null) {
            s();
            return;
        }
        int i3 = 0;
        if ((exc instanceof Parcelable) && exc.getClass().getClassLoader() == Parcelable.class.getClassLoader()) {
            i3 = i;
        } else if (exc instanceof SecurityException) {
            i3 = -1;
        } else if (exc instanceof BadParcelableException) {
            i3 = -2;
        } else if (exc instanceof IllegalArgumentException) {
            i3 = -3;
        } else if (exc instanceof NullPointerException) {
            i3 = -4;
        } else if (exc instanceof IllegalStateException) {
            i3 = f;
        } else if (exc instanceof NetworkOnMainThreadException) {
            i3 = g;
        } else if (exc instanceof UnsupportedOperationException) {
            i3 = h;
        }
        a(i3);
        if (i3 == 0) {
            if (!(exc instanceof RuntimeException)) {
                throw new RuntimeException(exc);
            }
            throw ((RuntimeException) exc);
        }
        a(exc.getMessage());
        if (i3 != i) {
            return;
        }
        a((Parcelable) exc);
    }

    protected abstract void a(String str);

    public void a(String str, int i2) {
        c(i2);
        a(str);
    }

    public <T> void a(List<T> list, int i2) {
        a((Collection) list, i2);
    }

    public <T> void a(Set<T> set, int i2) {
        a((Collection) set, i2);
    }

    protected abstract void a(boolean z);

    public void a(boolean z, int i2) {
        c(i2);
        a(z);
    }

    public void a(boolean z, boolean z2) {
    }

    protected abstract void a(byte[] bArr);

    public void a(byte[] bArr, int i2) {
        c(i2);
        a(bArr);
    }

    protected abstract void a(byte[] bArr, int i2, int i3);

    public void a(byte[] bArr, int i2, int i3, int i4) {
        c(i4);
        a(bArr, i2, i3);
    }

    public void a(char[] cArr, int i2) {
        c(i2);
        if (cArr == null) {
            a(-1);
            return;
        }
        a(cArr.length);
        for (char c2 : cArr) {
            a((int) c2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(double[] dArr) {
        if (dArr == null) {
            a(-1);
            return;
        }
        a(dArr.length);
        for (double d2 : dArr) {
            a(d2);
        }
    }

    public void a(double[] dArr, int i2) {
        c(i2);
        a(dArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(float[] fArr) {
        if (fArr == null) {
            a(-1);
            return;
        }
        a(fArr.length);
        for (float f2 : fArr) {
            a(f2);
        }
    }

    public void a(float[] fArr, int i2) {
        c(i2);
        a(fArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(int[] iArr) {
        if (iArr == null) {
            a(-1);
            return;
        }
        a(iArr.length);
        for (int i2 : iArr) {
            a(i2);
        }
    }

    public void a(int[] iArr, int i2) {
        c(i2);
        a(iArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(long[] jArr) {
        if (jArr == null) {
            a(-1);
            return;
        }
        a(jArr.length);
        for (long j2 : jArr) {
            a(j2);
        }
    }

    public void a(long[] jArr, int i2) {
        c(i2);
        a(jArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T> void a(T[] tArr) {
        if (tArr == null) {
            a(-1);
            return;
        }
        int length = tArr.length;
        a(length);
        if (length > 0) {
            int i2 = 0;
            int a2 = a((e) tArr[0]);
            a(a2);
            if (a2 == 1) {
                while (i2 < length) {
                    a((h) tArr[i2]);
                    i2++;
                }
            } else if (a2 == 2) {
                while (i2 < length) {
                    a((Parcelable) tArr[i2]);
                    i2++;
                }
            } else if (a2 == 3) {
                while (i2 < length) {
                    a((Serializable) tArr[i2]);
                    i2++;
                }
            } else if (a2 == 4) {
                while (i2 < length) {
                    a((String) tArr[i2]);
                    i2++;
                }
            } else if (a2 != 5) {
            } else {
                while (i2 < length) {
                    a((IBinder) tArr[i2]);
                    i2++;
                }
            }
        }
    }

    public <T> void a(T[] tArr, int i2) {
        c(i2);
        a((Object[]) tArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(boolean[] zArr) {
        if (zArr == null) {
            a(-1);
            return;
        }
        a(zArr.length);
        for (boolean z : zArr) {
            a(z ? 1 : 0);
        }
    }

    public void a(boolean[] zArr, int i2) {
        c(i2);
        a(zArr);
    }

    public boolean a() {
        return false;
    }

    public byte b(byte b2, int i2) {
        return !b(i2) ? b2 : (byte) (d() & 255);
    }

    public double b(double d2, int i2) {
        return !b(i2) ? d2 : g();
    }

    public float b(float f2, int i2) {
        return !b(i2) ? f2 : f();
    }

    public int b(int i2, int i3) {
        return !b(i3) ? i2 : d();
    }

    public long b(long j2, int i2) {
        return !b(i2) ? j2 : e();
    }

    public Bundle b(Bundle bundle, int i2) {
        return !b(i2) ? bundle : l();
    }

    public IBinder b(IBinder iBinder, int i2) {
        return !b(i2) ? iBinder : i();
    }

    public <T extends Parcelable> T b(T t, int i2) {
        return !b(i2) ? t : (T) k();
    }

    @ak(b = 21)
    public Size b(Size size, int i2) {
        if (b(i2)) {
            if (m()) {
                return new Size(d(), d());
            }
            return null;
        }
        return size;
    }

    @ak(b = 21)
    public SizeF b(SizeF sizeF, int i2) {
        if (b(i2)) {
            if (m()) {
                return new SizeF(f(), f());
            }
            return null;
        }
        return sizeF;
    }

    public SparseBooleanArray b(SparseBooleanArray sparseBooleanArray, int i2) {
        if (b(i2)) {
            int d2 = d();
            if (d2 < 0) {
                return null;
            }
            SparseBooleanArray sparseBooleanArray2 = new SparseBooleanArray(d2);
            for (int i3 = 0; i3 < d2; i3++) {
                sparseBooleanArray2.put(d(), m());
            }
            return sparseBooleanArray2;
        }
        return sparseBooleanArray;
    }

    public <T extends h> T b(T t, int i2) {
        return !b(i2) ? t : (T) t();
    }

    public Exception b(Exception exc, int i2) {
        int v;
        return (b(i2) && (v = v()) != 0) ? a(v, h()) : exc;
    }

    public String b(String str, int i2) {
        return !b(i2) ? str : h();
    }

    public <T> List<T> b(List<T> list, int i2) {
        return !b(i2) ? list : (List) a(i2, (int) new ArrayList());
    }

    public <T> Set<T> b(Set<T> set, int i2) {
        return !b(i2) ? set : (Set) a(i2, (int) new android.support.v4.j.b());
    }

    protected abstract void b();

    protected abstract boolean b(int i2);

    public boolean b(boolean z, int i2) {
        return !b(i2) ? z : m();
    }

    public byte[] b(byte[] bArr, int i2) {
        return !b(i2) ? bArr : j();
    }

    public char[] b(char[] cArr, int i2) {
        if (b(i2)) {
            int d2 = d();
            if (d2 < 0) {
                return null;
            }
            char[] cArr2 = new char[d2];
            for (int i3 = 0; i3 < d2; i3++) {
                cArr2[i3] = (char) d();
            }
            return cArr2;
        }
        return cArr;
    }

    public double[] b(double[] dArr, int i2) {
        return !b(i2) ? dArr : r();
    }

    public float[] b(float[] fArr, int i2) {
        return !b(i2) ? fArr : q();
    }

    public int[] b(int[] iArr, int i2) {
        return !b(i2) ? iArr : o();
    }

    public long[] b(long[] jArr, int i2) {
        return !b(i2) ? jArr : p();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T> T[] b(T[] tArr) {
        int d2 = d();
        if (d2 < 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(d2);
        if (d2 != 0) {
            int d3 = d();
            if (d2 < 0) {
                return null;
            }
            if (d3 == 1) {
                while (d2 > 0) {
                    arrayList.add(t());
                    d2--;
                }
            } else if (d3 == 2) {
                while (d2 > 0) {
                    arrayList.add(k());
                    d2--;
                }
            } else if (d3 == 3) {
                while (d2 > 0) {
                    arrayList.add(u());
                    d2--;
                }
            } else if (d3 == 4) {
                while (d2 > 0) {
                    arrayList.add(h());
                    d2--;
                }
            } else if (d3 == 5) {
                while (d2 > 0) {
                    arrayList.add(i());
                    d2--;
                }
            }
        }
        return (T[]) arrayList.toArray(tArr);
    }

    public <T> T[] b(T[] tArr, int i2) {
        return !b(i2) ? tArr : (T[]) b(tArr);
    }

    public boolean[] b(boolean[] zArr, int i2) {
        return !b(i2) ? zArr : n();
    }

    protected abstract e c();

    protected abstract void c(int i2);

    protected abstract int d();

    protected abstract long e();

    protected abstract float f();

    protected abstract double g();

    protected abstract String h();

    protected abstract IBinder i();

    protected abstract byte[] j();

    protected abstract <T extends Parcelable> T k();

    protected abstract Bundle l();

    protected abstract boolean m();

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean[] n() {
        int d2 = d();
        if (d2 < 0) {
            return null;
        }
        boolean[] zArr = new boolean[d2];
        for (int i2 = 0; i2 < d2; i2++) {
            zArr[i2] = d() != 0;
        }
        return zArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int[] o() {
        int d2 = d();
        if (d2 < 0) {
            return null;
        }
        int[] iArr = new int[d2];
        for (int i2 = 0; i2 < d2; i2++) {
            iArr[i2] = d();
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long[] p() {
        int d2 = d();
        if (d2 < 0) {
            return null;
        }
        long[] jArr = new long[d2];
        for (int i2 = 0; i2 < d2; i2++) {
            jArr[i2] = e();
        }
        return jArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float[] q() {
        int d2 = d();
        if (d2 < 0) {
            return null;
        }
        float[] fArr = new float[d2];
        for (int i2 = 0; i2 < d2; i2++) {
            fArr[i2] = f();
        }
        return fArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double[] r() {
        int d2 = d();
        if (d2 < 0) {
            return null;
        }
        double[] dArr = new double[d2];
        for (int i2 = 0; i2 < d2; i2++) {
            dArr[i2] = g();
        }
        return dArr;
    }

    protected void s() {
        a(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T extends h> T t() {
        String h2 = h();
        if (h2 == null) {
            return null;
        }
        return (T) a(h2, c());
    }

    protected Serializable u() {
        String h2 = h();
        if (h2 == null) {
            return null;
        }
        try {
            return (Serializable) new ObjectInputStream(new ByteArrayInputStream(j())) { // from class: androidx.versionedparcelable.e.1
                @Override // java.io.ObjectInputStream
                protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) {
                    Class<?> cls = Class.forName(objectStreamClass.getName(), false, getClass().getClassLoader());
                    return cls != null ? cls : super.resolveClass(objectStreamClass);
                }
            }.readObject();
        } catch (IOException e2) {
            throw new RuntimeException("VersionedParcelable encountered IOException reading a Serializable object (name = " + h2 + ")", e2);
        } catch (ClassNotFoundException e3) {
            throw new RuntimeException("VersionedParcelable encountered ClassNotFoundException reading a Serializable object (name = " + h2 + ")", e3);
        }
    }
}
