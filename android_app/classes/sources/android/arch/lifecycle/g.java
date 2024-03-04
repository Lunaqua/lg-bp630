package android.arch.lifecycle;

import android.arch.lifecycle.d;
import android.support.annotation.ac;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class g extends d {
    private static final String a = "LifecycleRegistry";
    private final WeakReference<f> d;
    private android.arch.a.b.a<e, a> b = new android.arch.a.b.a<>();
    private int e = 0;
    private boolean f = false;
    private boolean g = false;
    private ArrayList<d.b> h = new ArrayList<>();
    private d.b c = d.b.INITIALIZED;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.arch.lifecycle.g$1  reason: invalid class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b = new int[d.b.values().length];

        static {
            try {
                b[d.b.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[d.b.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[d.b.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[d.b.RESUMED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[d.b.DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            a = new int[d.a.values().length];
            try {
                a[d.a.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[d.a.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[d.a.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[d.a.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[d.a.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[d.a.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[d.a.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a {
        d.b a;
        GenericLifecycleObserver b;

        a(e eVar, d.b bVar) {
            this.b = i.a(eVar);
            this.a = bVar;
        }

        void a(f fVar, d.a aVar) {
            d.b b = g.b(aVar);
            this.a = g.a(this.a, b);
            this.b.a(fVar, aVar);
            this.a = b;
        }
    }

    public g(@af f fVar) {
        this.d = new WeakReference<>(fVar);
    }

    static d.b a(@af d.b bVar, @ag d.b bVar2) {
        return (bVar2 == null || bVar2.compareTo(bVar) >= 0) ? bVar : bVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(f fVar) {
        android.arch.a.b.b<e, a>.d c = this.b.c();
        while (c.hasNext() && !this.g) {
            Map.Entry next = c.next();
            a aVar = (a) next.getValue();
            while (aVar.a.compareTo(this.c) < 0 && !this.g && this.b.c(next.getKey())) {
                c(aVar.a);
                aVar.a(fVar, e(aVar.a));
                d();
            }
        }
    }

    static d.b b(d.a aVar) {
        switch (aVar) {
            case ON_CREATE:
            case ON_STOP:
                return d.b.CREATED;
            case ON_START:
            case ON_PAUSE:
                return d.b.STARTED;
            case ON_RESUME:
                return d.b.RESUMED;
            case ON_DESTROY:
                return d.b.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + aVar);
        }
    }

    private void b(d.b bVar) {
        if (this.c == bVar) {
            return;
        }
        this.c = bVar;
        if (this.f || this.e != 0) {
            this.g = true;
            return;
        }
        this.f = true;
        e();
        this.f = false;
    }

    private void b(f fVar) {
        Iterator<Map.Entry<e, a>> b = this.b.b();
        while (b.hasNext() && !this.g) {
            Map.Entry<e, a> next = b.next();
            a value = next.getValue();
            while (value.a.compareTo(this.c) > 0 && !this.g && this.b.c(next.getKey())) {
                d.a d = d(value.a);
                c(b(d));
                value.a(fVar, d);
                d();
            }
        }
    }

    private d.b c(e eVar) {
        Map.Entry<e, a> d = this.b.d(eVar);
        d.b bVar = null;
        d.b bVar2 = d != null ? d.getValue().a : null;
        if (!this.h.isEmpty()) {
            ArrayList<d.b> arrayList = this.h;
            bVar = arrayList.get(arrayList.size() - 1);
        }
        return a(a(this.c, bVar2), bVar);
    }

    private void c(d.b bVar) {
        this.h.add(bVar);
    }

    private boolean c() {
        if (this.b.a() == 0) {
            return true;
        }
        d.b bVar = this.b.d().getValue().a;
        d.b bVar2 = this.b.e().getValue().a;
        return bVar == bVar2 && this.c == bVar2;
    }

    private static d.a d(d.b bVar) {
        int i = AnonymousClass1.b[bVar.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            throw new IllegalArgumentException("Unexpected state value " + bVar);
                        }
                        throw new IllegalArgumentException();
                    }
                    return d.a.ON_PAUSE;
                }
                return d.a.ON_STOP;
            }
            return d.a.ON_DESTROY;
        }
        throw new IllegalArgumentException();
    }

    private void d() {
        ArrayList<d.b> arrayList = this.h;
        arrayList.remove(arrayList.size() - 1);
    }

    private static d.a e(d.b bVar) {
        int i = AnonymousClass1.b[bVar.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return d.a.ON_START;
            }
            if (i == 3) {
                return d.a.ON_RESUME;
            }
            if (i == 4) {
                throw new IllegalArgumentException();
            }
            if (i != 5) {
                throw new IllegalArgumentException("Unexpected state value " + bVar);
            }
        }
        return d.a.ON_CREATE;
    }

    private void e() {
        f fVar = this.d.get();
        if (fVar == null) {
            Log.w(a, "LifecycleOwner is garbage collected, you shouldn't try dispatch new events from it.");
            return;
        }
        while (!c()) {
            this.g = false;
            if (this.c.compareTo(this.b.d().getValue().a) < 0) {
                b(fVar);
            }
            Map.Entry<e, a> e = this.b.e();
            if (!this.g && e != null && this.c.compareTo(e.getValue().a) > 0) {
                a(fVar);
            }
        }
        this.g = false;
    }

    @Override // android.arch.lifecycle.d
    @af
    public d.b a() {
        return this.c;
    }

    public void a(@af d.a aVar) {
        b(b(aVar));
    }

    @ac
    public void a(@af d.b bVar) {
        b(bVar);
    }

    @Override // android.arch.lifecycle.d
    public void a(@af e eVar) {
        f fVar;
        a aVar = new a(eVar, this.c == d.b.DESTROYED ? d.b.DESTROYED : d.b.INITIALIZED);
        if (this.b.a(eVar, aVar) == null && (fVar = this.d.get()) != null) {
            boolean z = this.e != 0 || this.f;
            d.b c = c(eVar);
            this.e++;
            while (aVar.a.compareTo(c) < 0 && this.b.c(eVar)) {
                c(aVar.a);
                aVar.a(fVar, e(aVar.a));
                d();
                c = c(eVar);
            }
            if (!z) {
                e();
            }
            this.e--;
        }
    }

    public int b() {
        return this.b.a();
    }

    @Override // android.arch.lifecycle.d
    public void b(@af e eVar) {
        this.b.b(eVar);
    }
}
