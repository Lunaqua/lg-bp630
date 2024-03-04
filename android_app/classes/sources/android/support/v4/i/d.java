package android.support.v4.i;

import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.support.annotation.an;
import android.support.annotation.au;
import android.support.annotation.t;
import android.support.annotation.x;
import android.support.v4.j.m;
import android.support.v4.j.q;
import android.support.v4.os.o;
import android.support.v7.widget.ActivityChooserView;
import android.text.Layout;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class d implements Spannable {
    private static final char a = '\n';
    private static final Object b = new Object();
    @t(a = "sLock")
    @af
    private static Executor c = null;
    @af
    private final Spannable d;
    @af
    private final a e;
    @af
    private final int[] f;
    @ag
    private final PrecomputedText g;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class a {
        final PrecomputedText.Params a;
        @af
        private final TextPaint b;
        @ag
        private final TextDirectionHeuristic c;
        private final int d;
        private final int e;

        /* renamed from: android.support.v4.i.d$a$a  reason: collision with other inner class name */
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static class C0027a {
            @af
            private final TextPaint a;
            private TextDirectionHeuristic b;
            private int c;
            private int d;

            public C0027a(@af TextPaint textPaint) {
                this.a = textPaint;
                if (Build.VERSION.SDK_INT >= 23) {
                    this.c = 1;
                    this.d = 1;
                } else {
                    this.d = 0;
                    this.c = 0;
                }
                this.b = Build.VERSION.SDK_INT >= 18 ? TextDirectionHeuristics.FIRSTSTRONG_LTR : null;
            }

            @ak(a = 23)
            public C0027a a(int i) {
                this.c = i;
                return this;
            }

            @ak(a = 18)
            public C0027a a(@af TextDirectionHeuristic textDirectionHeuristic) {
                this.b = textDirectionHeuristic;
                return this;
            }

            @af
            public a a() {
                return new a(this.a, this.b, this.c, this.d);
            }

            @ak(a = 23)
            public C0027a b(int i) {
                this.d = i;
                return this;
            }
        }

        @ak(a = 28)
        public a(@af PrecomputedText.Params params) {
            this.b = params.getTextPaint();
            this.c = params.getTextDirection();
            this.d = params.getBreakStrategy();
            this.e = params.getHyphenationFrequency();
            this.a = params;
        }

        a(@af TextPaint textPaint, @af TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            this.a = Build.VERSION.SDK_INT >= 28 ? new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(textDirectionHeuristic).build() : null;
            this.b = textPaint;
            this.c = textDirectionHeuristic;
            this.d = i;
            this.e = i2;
        }

        @af
        public TextPaint a() {
            return this.b;
        }

        @ag
        @ak(a = 18)
        public TextDirectionHeuristic b() {
            return this.c;
        }

        @ak(a = 23)
        public int c() {
            return this.d;
        }

        @ak(a = 23)
        public int d() {
            return this.e;
        }

        public boolean equals(@ag Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || !(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            PrecomputedText.Params params = this.a;
            if (params != null) {
                return params.equals(aVar.a);
            }
            if (Build.VERSION.SDK_INT < 23 || (this.d == aVar.c() && this.e == aVar.d())) {
                if ((Build.VERSION.SDK_INT < 18 || this.c == aVar.b()) && this.b.getTextSize() == aVar.a().getTextSize() && this.b.getTextScaleX() == aVar.a().getTextScaleX() && this.b.getTextSkewX() == aVar.a().getTextSkewX()) {
                    if ((Build.VERSION.SDK_INT < 21 || (this.b.getLetterSpacing() == aVar.a().getLetterSpacing() && TextUtils.equals(this.b.getFontFeatureSettings(), aVar.a().getFontFeatureSettings()))) && this.b.getFlags() == aVar.a().getFlags()) {
                        if (Build.VERSION.SDK_INT >= 24) {
                            if (!this.b.getTextLocales().equals(aVar.a().getTextLocales())) {
                                return false;
                            }
                        } else if (Build.VERSION.SDK_INT >= 17 && !this.b.getTextLocale().equals(aVar.a().getTextLocale())) {
                            return false;
                        }
                        if (this.b.getTypeface() == null) {
                            if (aVar.a().getTypeface() != null) {
                                return false;
                            }
                        } else if (!this.b.getTypeface().equals(aVar.a().getTypeface())) {
                            return false;
                        }
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            if (Build.VERSION.SDK_INT >= 24) {
                return m.a(Float.valueOf(this.b.getTextSize()), Float.valueOf(this.b.getTextScaleX()), Float.valueOf(this.b.getTextSkewX()), Float.valueOf(this.b.getLetterSpacing()), Integer.valueOf(this.b.getFlags()), this.b.getTextLocales(), this.b.getTypeface(), Boolean.valueOf(this.b.isElegantTextHeight()), this.c, Integer.valueOf(this.d), Integer.valueOf(this.e));
            }
            if (Build.VERSION.SDK_INT >= 21) {
                return m.a(Float.valueOf(this.b.getTextSize()), Float.valueOf(this.b.getTextScaleX()), Float.valueOf(this.b.getTextSkewX()), Float.valueOf(this.b.getLetterSpacing()), Integer.valueOf(this.b.getFlags()), this.b.getTextLocale(), this.b.getTypeface(), Boolean.valueOf(this.b.isElegantTextHeight()), this.c, Integer.valueOf(this.d), Integer.valueOf(this.e));
            }
            if (Build.VERSION.SDK_INT < 18 && Build.VERSION.SDK_INT < 17) {
                return m.a(Float.valueOf(this.b.getTextSize()), Float.valueOf(this.b.getTextScaleX()), Float.valueOf(this.b.getTextSkewX()), Integer.valueOf(this.b.getFlags()), this.b.getTypeface(), this.c, Integer.valueOf(this.d), Integer.valueOf(this.e));
            }
            return m.a(Float.valueOf(this.b.getTextSize()), Float.valueOf(this.b.getTextScaleX()), Float.valueOf(this.b.getTextSkewX()), Integer.valueOf(this.b.getFlags()), this.b.getTextLocale(), this.b.getTypeface(), this.c, Integer.valueOf(this.d), Integer.valueOf(this.e));
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x00e5  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.String toString() {
            /*
                Method dump skipped, instructions count: 331
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.i.d.a.toString():java.lang.String");
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class b extends FutureTask<d> {

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        private static class a implements Callable<d> {
            private a a;
            private CharSequence b;

            a(@af a aVar, @af CharSequence charSequence) {
                this.a = aVar;
                this.b = charSequence;
            }

            @Override // java.util.concurrent.Callable
            /* renamed from: a */
            public d call() {
                return d.a(this.b, this.a);
            }
        }

        b(@af a aVar, @af CharSequence charSequence) {
            super(new a(aVar, charSequence));
        }
    }

    @ak(a = 28)
    private d(@af PrecomputedText precomputedText, @af a aVar) {
        this.d = precomputedText;
        this.e = aVar;
        this.f = null;
        this.g = precomputedText;
    }

    private d(@af CharSequence charSequence, @af a aVar, @af int[] iArr) {
        this.d = new SpannableString(charSequence);
        this.e = aVar;
        this.f = iArr;
        this.g = null;
    }

    public static d a(@af CharSequence charSequence, @af a aVar) {
        q.a(charSequence);
        q.a(aVar);
        try {
            o.a("PrecomputedText");
            if (Build.VERSION.SDK_INT < 28 || aVar.a == null) {
                ArrayList arrayList = new ArrayList();
                int length = charSequence.length();
                int i = 0;
                while (i < length) {
                    int indexOf = TextUtils.indexOf(charSequence, (char) a, i, length);
                    i = indexOf < 0 ? length : indexOf + 1;
                    arrayList.add(Integer.valueOf(i));
                }
                int[] iArr = new int[arrayList.size()];
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    iArr[i2] = ((Integer) arrayList.get(i2)).intValue();
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), aVar.a(), ActivityChooserView.a.a).setBreakStrategy(aVar.c()).setHyphenationFrequency(aVar.d()).setTextDirection(aVar.b()).build();
                } else if (Build.VERSION.SDK_INT >= 21) {
                    new StaticLayout(charSequence, aVar.a(), ActivityChooserView.a.a, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                }
                return new d(charSequence, aVar, iArr);
            }
            return new d(PrecomputedText.create(charSequence, aVar.a), aVar);
        } finally {
            o.a();
        }
    }

    @au
    public static Future<d> a(@af CharSequence charSequence, @af a aVar, @ag Executor executor) {
        b bVar = new b(aVar, charSequence);
        if (executor == null) {
            synchronized (b) {
                if (c == null) {
                    c = Executors.newFixedThreadPool(1);
                }
                executor = c;
            }
        }
        executor.execute(bVar);
        return bVar;
    }

    private int c(@x(a = 0) int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = this.f;
            if (i2 >= iArr.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("pos must be less than ");
                int[] iArr2 = this.f;
                sb.append(iArr2[iArr2.length - 1]);
                sb.append(", gave ");
                sb.append(i);
                throw new IndexOutOfBoundsException(sb.toString());
            } else if (i < iArr[i2]) {
                return i2;
            } else {
                i2++;
            }
        }
    }

    @x(a = 0)
    public int a(@x(a = 0) int i) {
        q.a(i, 0, c(), "paraIndex");
        if (Build.VERSION.SDK_INT >= 28) {
            return this.g.getParagraphStart(i);
        }
        if (i == 0) {
            return 0;
        }
        return this.f[i - 1];
    }

    @ag
    @ak(a = 28)
    @an(a = {an.a.LIBRARY_GROUP})
    public PrecomputedText a() {
        Spannable spannable = this.d;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @x(a = 0)
    public int b(@x(a = 0) int i) {
        q.a(i, 0, c(), "paraIndex");
        return Build.VERSION.SDK_INT >= 28 ? this.g.getParagraphEnd(i) : this.f[i];
    }

    @af
    public a b() {
        return this.e;
    }

    @x(a = 0)
    public int c() {
        return Build.VERSION.SDK_INT >= 28 ? this.g.getParagraphCount() : this.f.length;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.d.charAt(i);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.d.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.d.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.d.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 28 ? (T[]) this.g.getSpans(i, i2, cls) : (T[]) this.d.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.d.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.d.nextSpanTransition(i, i2, cls);
    }

    @Override // android.text.Spannable
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 28) {
            this.g.removeSpan(obj);
        } else {
            this.d.removeSpan(obj);
        }
    }

    @Override // android.text.Spannable
    public void setSpan(Object obj, int i, int i2, int i3) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 28) {
            this.g.setSpan(obj, i, i2, i3);
        } else {
            this.d.setSpan(obj, i, i2, i3);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return this.d.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.d.toString();
    }
}
