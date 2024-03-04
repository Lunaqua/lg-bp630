package android.support.v4.h;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.support.annotation.an;
import android.support.annotation.av;
import android.support.annotation.t;
import android.support.annotation.x;
import android.support.v4.content.b.g;
import android.support.v4.graphics.m;
import android.support.v4.h.f;
import android.support.v4.j.k;
import android.support.v4.j.q;
import android.support.v4.j.r;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class d {
    @an(a = {an.a.LIBRARY_GROUP})
    public static final String a = "font_results";
    @an(a = {an.a.LIBRARY_GROUP})
    static final int b = -1;
    @an(a = {an.a.LIBRARY_GROUP})
    static final int c = -2;
    private static final String g = "FontsContractCompat";
    static final k<String, Typeface> d = new k<>(16);
    private static final int h = 10000;
    private static final f i = new f("fonts", 10, h);
    static final Object e = new Object();
    @t(a = "sLock")
    static final r<String, ArrayList<f.a<e>>> f = new r<>();
    private static final Comparator<byte[]> j = new Comparator<byte[]>() { // from class: android.support.v4.h.d.5
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(byte[] bArr, byte[] bArr2) {
            int i2;
            int i3;
            if (bArr.length == bArr2.length) {
                for (int i4 = 0; i4 < bArr.length; i4++) {
                    if (bArr[i4] != bArr2[i4]) {
                        i2 = bArr[i4];
                        i3 = bArr2[i4];
                    }
                }
                return 0;
            }
            i2 = bArr.length;
            i3 = bArr2.length;
            return i2 - i3;
        }
    };

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class a implements BaseColumns {
        public static final String a = "file_id";
        public static final String b = "font_ttc_index";
        public static final String c = "font_variation_settings";
        public static final String d = "font_weight";
        public static final String e = "font_italic";
        public static final String f = "result_code";
        public static final int g = 0;
        public static final int h = 1;
        public static final int i = 2;
        public static final int j = 3;
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b {
        public static final int a = 0;
        public static final int b = 1;
        public static final int c = 2;
        private final int d;
        private final c[] e;

        @an(a = {an.a.LIBRARY_GROUP})
        public b(int i, @ag c[] cVarArr) {
            this.d = i;
            this.e = cVarArr;
        }

        public int a() {
            return this.d;
        }

        public c[] b() {
            return this.e;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class c {
        private final Uri a;
        private final int b;
        private final int c;
        private final boolean d;
        private final int e;

        @an(a = {an.a.LIBRARY_GROUP})
        public c(@af Uri uri, @x(a = 0) int i, @x(a = 1, b = 1000) int i2, boolean z, int i3) {
            this.a = (Uri) q.a(uri);
            this.b = i;
            this.c = i2;
            this.d = z;
            this.e = i3;
        }

        @af
        public Uri a() {
            return this.a;
        }

        @x(a = 0)
        public int b() {
            return this.b;
        }

        @x(a = 1, b = 1000)
        public int c() {
            return this.c;
        }

        public boolean d() {
            return this.d;
        }

        public int e() {
            return this.e;
        }
    }

    /* renamed from: android.support.v4.h.d$d  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class C0023d {
        @an(a = {an.a.LIBRARY_GROUP})
        public static final int a = 0;
        public static final int b = -1;
        public static final int c = -2;
        public static final int d = -3;
        public static final int e = -4;
        public static final int f = 1;
        public static final int g = 2;
        public static final int h = 3;

        @an(a = {an.a.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        /* renamed from: android.support.v4.h.d$d$a */
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public @interface a {
        }

        public void a(int i) {
        }

        public void a(Typeface typeface) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class e {
        final Typeface a;
        final int b;

        e(@ag Typeface typeface, int i) {
            this.a = typeface;
            this.b = i;
        }
    }

    private d() {
    }

    @ag
    @an(a = {an.a.LIBRARY_GROUP})
    @av
    public static ProviderInfo a(@af PackageManager packageManager, @af android.support.v4.h.c cVar, @ag Resources resources) {
        String a2 = cVar.a();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(a2, 0);
        if (resolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + a2);
        } else if (!resolveContentProvider.packageName.equals(cVar.b())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + a2 + ", but package was not " + cVar.b());
        } else {
            List<byte[]> a3 = a(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
            Collections.sort(a3, j);
            List<List<byte[]>> a4 = a(cVar, resources);
            for (int i2 = 0; i2 < a4.size(); i2++) {
                ArrayList arrayList = new ArrayList(a4.get(i2));
                Collections.sort(arrayList, j);
                if (a(a3, arrayList)) {
                    return resolveContentProvider;
                }
            }
            return null;
        }
    }

    @ag
    public static Typeface a(@af Context context, @ag CancellationSignal cancellationSignal, @af c[] cVarArr) {
        return android.support.v4.graphics.g.a(context, cancellationSignal, cVarArr, 0);
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public static Typeface a(final Context context, final android.support.v4.h.c cVar, @ag final g.a aVar, @ag final Handler handler, boolean z, int i2, final int i3) {
        final String str = cVar.f() + "-" + i3;
        Typeface a2 = d.a((k<String, Typeface>) str);
        if (a2 != null) {
            if (aVar != null) {
                aVar.a(a2);
            }
            return a2;
        } else if (z && i2 == -1) {
            e a3 = a(context, cVar, i3);
            if (aVar != null) {
                if (a3.b == 0) {
                    aVar.a(a3.a, handler);
                } else {
                    aVar.a(a3.b, handler);
                }
            }
            return a3.a;
        } else {
            Callable<e> callable = new Callable<e>() { // from class: android.support.v4.h.d.1
                @Override // java.util.concurrent.Callable
                /* renamed from: a */
                public e call() {
                    e a4 = d.a(context, cVar, i3);
                    if (a4.a != null) {
                        d.d.a(str, a4.a);
                    }
                    return a4;
                }
            };
            if (z) {
                try {
                    return ((e) i.a(callable, i2)).a;
                } catch (InterruptedException unused) {
                    return null;
                }
            }
            f.a<e> aVar2 = aVar == null ? null : new f.a<e>() { // from class: android.support.v4.h.d.2
                @Override // android.support.v4.h.f.a
                public void a(e eVar) {
                    if (eVar == null) {
                        g.a.this.a(1, handler);
                    } else if (eVar.b == 0) {
                        g.a.this.a(eVar.a, handler);
                    } else {
                        g.a.this.a(eVar.b, handler);
                    }
                }
            };
            synchronized (e) {
                if (f.containsKey(str)) {
                    if (aVar2 != null) {
                        f.get(str).add(aVar2);
                    }
                    return null;
                }
                if (aVar2 != null) {
                    ArrayList<f.a<e>> arrayList = new ArrayList<>();
                    arrayList.add(aVar2);
                    f.put(str, arrayList);
                }
                i.a(callable, new f.a<e>() { // from class: android.support.v4.h.d.3
                    @Override // android.support.v4.h.f.a
                    public void a(e eVar) {
                        synchronized (d.e) {
                            ArrayList<f.a<e>> arrayList2 = d.f.get(str);
                            if (arrayList2 == null) {
                                return;
                            }
                            d.f.remove(str);
                            for (int i4 = 0; i4 < arrayList2.size(); i4++) {
                                arrayList2.get(i4).a(eVar);
                            }
                        }
                    }
                });
                return null;
            }
        }
    }

    @af
    public static b a(@af Context context, @ag CancellationSignal cancellationSignal, @af android.support.v4.h.c cVar) {
        ProviderInfo a2 = a(context.getPackageManager(), cVar, context.getResources());
        return a2 == null ? new b(1, null) : new b(0, a(context, cVar, a2.authority, cancellationSignal));
    }

    @af
    static e a(Context context, android.support.v4.h.c cVar, int i2) {
        try {
            b a2 = a(context, (CancellationSignal) null, cVar);
            if (a2.a() != 0) {
                return new e(null, a2.a() == 1 ? -2 : -3);
            }
            Typeface a3 = android.support.v4.graphics.g.a(context, null, a2.b(), i2);
            return new e(a3, a3 != null ? 0 : -3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new e(null, -1);
        }
    }

    private static List<List<byte[]>> a(android.support.v4.h.c cVar, Resources resources) {
        return cVar.d() != null ? cVar.d() : android.support.v4.content.b.d.a(resources, cVar.e());
    }

    private static List<byte[]> a(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    @ak(a = 19)
    @an(a = {an.a.LIBRARY_GROUP})
    public static Map<Uri, ByteBuffer> a(Context context, c[] cVarArr, CancellationSignal cancellationSignal) {
        HashMap hashMap = new HashMap();
        for (c cVar : cVarArr) {
            if (cVar.e() == 0) {
                Uri a2 = cVar.a();
                if (!hashMap.containsKey(a2)) {
                    hashMap.put(a2, m.a(context, cancellationSignal, a2));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public static void a() {
        d.a();
    }

    public static void a(@af final Context context, @af final android.support.v4.h.c cVar, @af final C0023d c0023d, @af Handler handler) {
        final Handler handler2 = new Handler();
        handler.post(new Runnable() { // from class: android.support.v4.h.d.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    b a2 = d.a(context, (CancellationSignal) null, cVar);
                    if (a2.a() != 0) {
                        int a3 = a2.a();
                        if (a3 == 1) {
                            handler2.post(new Runnable() { // from class: android.support.v4.h.d.4.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    c0023d.a(-2);
                                }
                            });
                            return;
                        } else if (a3 != 2) {
                            handler2.post(new Runnable() { // from class: android.support.v4.h.d.4.4
                                @Override // java.lang.Runnable
                                public void run() {
                                    c0023d.a(-3);
                                }
                            });
                            return;
                        } else {
                            handler2.post(new Runnable() { // from class: android.support.v4.h.d.4.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    c0023d.a(-3);
                                }
                            });
                            return;
                        }
                    }
                    c[] b2 = a2.b();
                    if (b2 == null || b2.length == 0) {
                        handler2.post(new Runnable() { // from class: android.support.v4.h.d.4.5
                            @Override // java.lang.Runnable
                            public void run() {
                                c0023d.a(1);
                            }
                        });
                        return;
                    }
                    for (c cVar2 : b2) {
                        if (cVar2.e() != 0) {
                            final int e2 = cVar2.e();
                            if (e2 < 0) {
                                handler2.post(new Runnable() { // from class: android.support.v4.h.d.4.6
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        c0023d.a(-3);
                                    }
                                });
                                return;
                            } else {
                                handler2.post(new Runnable() { // from class: android.support.v4.h.d.4.7
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        c0023d.a(e2);
                                    }
                                });
                                return;
                            }
                        }
                    }
                    final Typeface a4 = d.a(context, (CancellationSignal) null, b2);
                    if (a4 == null) {
                        handler2.post(new Runnable() { // from class: android.support.v4.h.d.4.8
                            @Override // java.lang.Runnable
                            public void run() {
                                c0023d.a(-3);
                            }
                        });
                    } else {
                        handler2.post(new Runnable() { // from class: android.support.v4.h.d.4.9
                            @Override // java.lang.Runnable
                            public void run() {
                                c0023d.a(a4);
                            }
                        });
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                    handler2.post(new Runnable() { // from class: android.support.v4.h.d.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            c0023d.a(-1);
                        }
                    });
                }
            }
        });
    }

    private static boolean a(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (!Arrays.equals(list.get(i2), list2.get(i2))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x012b  */
    @android.support.annotation.af
    @android.support.annotation.av
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static android.support.v4.h.d.c[] a(android.content.Context r19, android.support.v4.h.c r20, java.lang.String r21, android.os.CancellationSignal r22) {
        /*
            Method dump skipped, instructions count: 321
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.h.d.a(android.content.Context, android.support.v4.h.c, java.lang.String, android.os.CancellationSignal):android.support.v4.h.d$c[]");
    }
}
