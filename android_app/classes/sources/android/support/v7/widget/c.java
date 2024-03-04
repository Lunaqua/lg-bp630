package android.support.v7.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c extends DataSetObservable {
    static final boolean a = false;
    static final String b = "c";
    static final String c = "historical-records";
    static final String d = "historical-record";
    static final String e = "activity";
    static final String f = "time";
    static final String g = "weight";
    public static final String h = "activity_choser_model_history.xml";
    public static final int i = 50;
    private static final int m = 5;
    private static final float n = 1.0f;
    private static final String o = ".xml";
    private static final int p = -1;
    private static final Object q = new Object();
    private static final Map<String, c> r = new HashMap();
    private f B;
    final Context j;
    final String k;
    private Intent v;
    private final Object s = new Object();
    private final List<b> t = new ArrayList();
    private final List<e> u = new ArrayList();
    private InterfaceC0051c w = new d();
    private int x = 50;
    boolean l = true;
    private boolean y = false;
    private boolean z = true;
    private boolean A = false;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        void setActivityChooserModel(c cVar);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class b implements Comparable<b> {
        public final ResolveInfo a;
        public float b;

        public b(ResolveInfo resolveInfo) {
            this.a = resolveInfo;
        }

        @Override // java.lang.Comparable
        /* renamed from: a */
        public int compareTo(b bVar) {
            return Float.floatToIntBits(bVar.b) - Float.floatToIntBits(this.b);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && Float.floatToIntBits(this.b) == Float.floatToIntBits(((b) obj).b);
        }

        public int hashCode() {
            return Float.floatToIntBits(this.b) + 31;
        }

        public String toString() {
            return "[resolveInfo:" + this.a.toString() + "; weight:" + new BigDecimal(this.b) + "]";
        }
    }

    /* renamed from: android.support.v7.widget.c$c  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface InterfaceC0051c {
        void a(Intent intent, List<b> list, List<e> list2);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static final class d implements InterfaceC0051c {
        private static final float a = 0.95f;
        private final Map<ComponentName, b> b = new HashMap();

        d() {
        }

        @Override // android.support.v7.widget.c.InterfaceC0051c
        public void a(Intent intent, List<b> list, List<e> list2) {
            Map<ComponentName, b> map = this.b;
            map.clear();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                b bVar = list.get(i);
                bVar.b = 0.0f;
                map.put(new ComponentName(bVar.a.activityInfo.packageName, bVar.a.activityInfo.name), bVar);
            }
            float f = c.n;
            for (int size2 = list2.size() - 1; size2 >= 0; size2--) {
                e eVar = list2.get(size2);
                b bVar2 = map.get(eVar.a);
                if (bVar2 != null) {
                    bVar2.b += eVar.c * f;
                    f *= a;
                }
            }
            Collections.sort(list);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class e {
        public final ComponentName a;
        public final long b;
        public final float c;

        public e(ComponentName componentName, long j, float f) {
            this.a = componentName;
            this.b = j;
            this.c = f;
        }

        public e(String str, long j, float f) {
            this(ComponentName.unflattenFromString(str), j, f);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                e eVar = (e) obj;
                ComponentName componentName = this.a;
                if (componentName == null) {
                    if (eVar.a != null) {
                        return false;
                    }
                } else if (!componentName.equals(eVar.a)) {
                    return false;
                }
                return this.b == eVar.b && Float.floatToIntBits(this.c) == Float.floatToIntBits(eVar.c);
            }
            return false;
        }

        public int hashCode() {
            ComponentName componentName = this.a;
            int hashCode = componentName == null ? 0 : componentName.hashCode();
            long j = this.b;
            return ((((hashCode + 31) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + Float.floatToIntBits(this.c);
        }

        public String toString() {
            return "[; activity:" + this.a + "; time:" + this.b + "; weight:" + new BigDecimal(this.c) + "]";
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface f {
        boolean a(c cVar, Intent intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public final class g extends AsyncTask<Object, Void, Void> {
        g() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x006d, code lost:
            if (r15 != null) goto L15;
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x006f, code lost:
            r15.close();
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0092, code lost:
            if (r15 == null) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x00b2, code lost:
            if (r15 == null) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x00d2, code lost:
            if (r15 == null) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x00d5, code lost:
            return null;
         */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Void doInBackground(java.lang.Object... r15) {
            /*
                Method dump skipped, instructions count: 246
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.c.g.doInBackground(java.lang.Object[]):java.lang.Void");
        }
    }

    private c(Context context, String str) {
        this.j = context.getApplicationContext();
        if (TextUtils.isEmpty(str) || str.endsWith(o)) {
            this.k = str;
            return;
        }
        this.k = str + o;
    }

    public static c a(Context context, String str) {
        c cVar;
        synchronized (q) {
            cVar = r.get(str);
            if (cVar == null) {
                cVar = new c(context, str);
                r.put(str, cVar);
            }
        }
        return cVar;
    }

    private boolean a(e eVar) {
        boolean add = this.u.add(eVar);
        if (add) {
            this.z = true;
            k();
            f();
            h();
            notifyChanged();
        }
        return add;
    }

    private void f() {
        if (!this.y) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        }
        if (this.z) {
            this.z = false;
            if (TextUtils.isEmpty(this.k)) {
                return;
            }
            new g().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new ArrayList(this.u), this.k);
        }
    }

    private void g() {
        boolean i2 = i() | j();
        k();
        if (i2) {
            h();
            notifyChanged();
        }
    }

    private boolean h() {
        if (this.w == null || this.v == null || this.t.isEmpty() || this.u.isEmpty()) {
            return false;
        }
        this.w.a(this.v, this.t, Collections.unmodifiableList(this.u));
        return true;
    }

    private boolean i() {
        if (!this.A || this.v == null) {
            return false;
        }
        this.A = false;
        this.t.clear();
        List<ResolveInfo> queryIntentActivities = this.j.getPackageManager().queryIntentActivities(this.v, 0);
        int size = queryIntentActivities.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.t.add(new b(queryIntentActivities.get(i2)));
        }
        return true;
    }

    private boolean j() {
        if (this.l && this.z && !TextUtils.isEmpty(this.k)) {
            this.l = false;
            this.y = true;
            l();
            return true;
        }
        return false;
    }

    private void k() {
        int size = this.u.size() - this.x;
        if (size <= 0) {
            return;
        }
        this.z = true;
        for (int i2 = 0; i2 < size; i2++) {
            this.u.remove(0);
        }
    }

    private void l() {
        XmlPullParser newPullParser;
        try {
            FileInputStream openFileInput = this.j.openFileInput(this.k);
            try {
                try {
                    try {
                        newPullParser = Xml.newPullParser();
                        newPullParser.setInput(openFileInput, "UTF-8");
                        for (int i2 = 0; i2 != 1 && i2 != 2; i2 = newPullParser.next()) {
                        }
                    } catch (IOException e2) {
                        String str = b;
                        Log.e(str, "Error reading historical recrod file: " + this.k, e2);
                        if (openFileInput == null) {
                            return;
                        }
                    }
                } catch (XmlPullParserException e3) {
                    String str2 = b;
                    Log.e(str2, "Error reading historical recrod file: " + this.k, e3);
                    if (openFileInput == null) {
                        return;
                    }
                }
                if (!c.equals(newPullParser.getName())) {
                    throw new XmlPullParserException("Share records file does not start with historical-records tag.");
                }
                List<e> list = this.u;
                list.clear();
                while (true) {
                    int next = newPullParser.next();
                    if (next == 1) {
                        if (openFileInput == null) {
                            return;
                        }
                    } else if (next != 3 && next != 4) {
                        if (!d.equals(newPullParser.getName())) {
                            throw new XmlPullParserException("Share records file not well-formed.");
                        }
                        list.add(new e(newPullParser.getAttributeValue(null, e), Long.parseLong(newPullParser.getAttributeValue(null, f)), Float.parseFloat(newPullParser.getAttributeValue(null, g))));
                    }
                }
                try {
                    openFileInput.close();
                } catch (IOException unused) {
                }
            } catch (Throwable th) {
                if (openFileInput != null) {
                    try {
                        openFileInput.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException unused3) {
        }
    }

    public int a(ResolveInfo resolveInfo) {
        synchronized (this.s) {
            g();
            List<b> list = this.t;
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (list.get(i2).a == resolveInfo) {
                    return i2;
                }
            }
            return -1;
        }
    }

    public Intent a() {
        Intent intent;
        synchronized (this.s) {
            intent = this.v;
        }
        return intent;
    }

    public ResolveInfo a(int i2) {
        ResolveInfo resolveInfo;
        synchronized (this.s) {
            g();
            resolveInfo = this.t.get(i2).a;
        }
        return resolveInfo;
    }

    public void a(Intent intent) {
        synchronized (this.s) {
            if (this.v == intent) {
                return;
            }
            this.v = intent;
            this.A = true;
            g();
        }
    }

    public void a(InterfaceC0051c interfaceC0051c) {
        synchronized (this.s) {
            if (this.w == interfaceC0051c) {
                return;
            }
            this.w = interfaceC0051c;
            if (h()) {
                notifyChanged();
            }
        }
    }

    public void a(f fVar) {
        synchronized (this.s) {
            this.B = fVar;
        }
    }

    public int b() {
        int size;
        synchronized (this.s) {
            g();
            size = this.t.size();
        }
        return size;
    }

    public Intent b(int i2) {
        synchronized (this.s) {
            if (this.v == null) {
                return null;
            }
            g();
            b bVar = this.t.get(i2);
            ComponentName componentName = new ComponentName(bVar.a.activityInfo.packageName, bVar.a.activityInfo.name);
            Intent intent = new Intent(this.v);
            intent.setComponent(componentName);
            if (this.B != null) {
                if (this.B.a(this, new Intent(intent))) {
                    return null;
                }
            }
            a(new e(componentName, System.currentTimeMillis(), (float) n));
            return intent;
        }
    }

    public ResolveInfo c() {
        synchronized (this.s) {
            g();
            if (this.t.isEmpty()) {
                return null;
            }
            return this.t.get(0).a;
        }
    }

    public void c(int i2) {
        synchronized (this.s) {
            g();
            b bVar = this.t.get(i2);
            b bVar2 = this.t.get(0);
            a(new e(new ComponentName(bVar.a.activityInfo.packageName, bVar.a.activityInfo.name), System.currentTimeMillis(), bVar2 != null ? (bVar2.b - bVar.b) + 5.0f : n));
        }
    }

    public int d() {
        int i2;
        synchronized (this.s) {
            i2 = this.x;
        }
        return i2;
    }

    public void d(int i2) {
        synchronized (this.s) {
            if (this.x == i2) {
                return;
            }
            this.x = i2;
            k();
            if (h()) {
                notifyChanged();
            }
        }
    }

    public int e() {
        int size;
        synchronized (this.s) {
            g();
            size = this.u.size();
        }
        return size;
    }
}
