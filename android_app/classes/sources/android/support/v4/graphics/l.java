package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.v4.content.b.d;
import android.support.v4.h.d;
import android.support.v7.widget.ActivityChooserView;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
/* JADX INFO: Access modifiers changed from: package-private */
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class l {
    private static final String a = "TypefaceCompatBaseImpl";
    private static final String b = "cached_font_";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a<T> {
        boolean a(T t);

        int b(T t);
    }

    private d.C0017d a(d.c cVar, int i) {
        return (d.C0017d) a(cVar.a(), i, new a<d.C0017d>() { // from class: android.support.v4.graphics.l.2
            @Override // android.support.v4.graphics.l.a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public int b(d.C0017d c0017d) {
                return c0017d.b();
            }

            @Override // android.support.v4.graphics.l.a
            /* renamed from: b  reason: avoid collision after fix types in other method */
            public boolean a(d.C0017d c0017d) {
                return c0017d.c();
            }
        });
    }

    private static <T> T a(T[] tArr, int i, a<T> aVar) {
        int i2 = (i & 1) == 0 ? 400 : 700;
        boolean z = (i & 2) != 0;
        T t = null;
        int i3 = ActivityChooserView.a.a;
        for (T t2 : tArr) {
            int abs = (Math.abs(aVar.b(t2) - i2) * 2) + (aVar.a(t2) == z ? 0 : 1);
            if (t == null || i3 > abs) {
                t = t2;
                i3 = abs;
            }
        }
        return t;
    }

    @ag
    public Typeface a(Context context, Resources resources, int i, String str, int i2) {
        File a2 = m.a(context);
        if (a2 == null) {
            return null;
        }
        try {
            if (m.a(a2, resources, i)) {
                return Typeface.createFromFile(a2.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            a2.delete();
        }
    }

    public Typeface a(Context context, @ag CancellationSignal cancellationSignal, @af d.c[] cVarArr, int i) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        if (cVarArr.length < 1) {
            return null;
        }
        try {
            inputStream = context.getContentResolver().openInputStream(a(cVarArr, i).a());
        } catch (IOException unused) {
            inputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            Typeface a2 = a(context, inputStream);
            m.a(inputStream);
            return a2;
        } catch (IOException unused2) {
            m.a(inputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            inputStream2 = inputStream;
            m.a(inputStream2);
            throw th;
        }
    }

    @ag
    public Typeface a(Context context, d.c cVar, Resources resources, int i) {
        d.C0017d a2 = a(cVar, i);
        if (a2 == null) {
            return null;
        }
        return g.a(context, resources, a2.f(), a2.a(), i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Typeface a(Context context, InputStream inputStream) {
        File a2 = m.a(context);
        if (a2 == null) {
            return null;
        }
        try {
            if (m.a(a2, inputStream)) {
                return Typeface.createFromFile(a2.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            a2.delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public d.c a(d.c[] cVarArr, int i) {
        return (d.c) a(cVarArr, i, new a<d.c>() { // from class: android.support.v4.graphics.l.1
            @Override // android.support.v4.graphics.l.a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public int b(d.c cVar) {
                return cVar.c();
            }

            @Override // android.support.v4.graphics.l.a
            /* renamed from: b  reason: avoid collision after fix types in other method */
            public boolean a(d.c cVar) {
                return cVar.d();
            }
        });
    }
}
