package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.support.v7.widget.ActivityChooserView;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class m {
    static final float a = -1.0f;
    private static final String b = "ACTVAutoSizeHelper";
    private static final int d = 12;
    private static final int e = 112;
    private static final int f = 1;
    private static final int h = 1048576;
    private int i = 0;
    private boolean j = false;
    private float k = a;
    private float l = a;
    private float m = a;
    private int[] n = new int[0];
    private boolean o = false;
    private TextPaint p;
    private final TextView q;
    private final Context r;
    private static final RectF c = new RectF();
    private static ConcurrentHashMap<String, Method> g = new ConcurrentHashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(TextView textView) {
        this.q = textView;
        this.r = this.q.getContext();
    }

    private int a(RectF rectF) {
        int length = this.n.length;
        if (length != 0) {
            int i = length - 1;
            int i2 = 1;
            int i3 = 0;
            while (i2 <= i) {
                int i4 = (i2 + i) / 2;
                if (a(this.n[i4], rectF)) {
                    int i5 = i4 + 1;
                    i3 = i2;
                    i2 = i5;
                } else {
                    i3 = i4 - 1;
                    i = i3;
                }
            }
            return this.n[i3];
        }
        throw new IllegalStateException("No available text sizes to choose from.");
    }

    private StaticLayout a(CharSequence charSequence, Layout.Alignment alignment, int i) {
        float floatValue;
        float floatValue2;
        boolean booleanValue;
        if (Build.VERSION.SDK_INT >= 16) {
            floatValue = this.q.getLineSpacingMultiplier();
            floatValue2 = this.q.getLineSpacingExtra();
            booleanValue = this.q.getIncludeFontPadding();
        } else {
            floatValue = ((Float) a((Object) this.q, "getLineSpacingMultiplier", (String) Float.valueOf(1.0f))).floatValue();
            floatValue2 = ((Float) a((Object) this.q, "getLineSpacingExtra", (String) Float.valueOf(0.0f))).floatValue();
            booleanValue = ((Boolean) a((Object) this.q, "getIncludeFontPadding", (String) true)).booleanValue();
        }
        return new StaticLayout(charSequence, this.p, i, alignment, floatValue, floatValue2, booleanValue);
    }

    @android.support.annotation.ak(a = 23)
    private StaticLayout a(CharSequence charSequence, Layout.Alignment alignment, int i, int i2) {
        TextDirectionHeuristic textDirectionHeuristic = (TextDirectionHeuristic) a((Object) this.q, "getTextDirectionHeuristic", (String) TextDirectionHeuristics.FIRSTSTRONG_LTR);
        StaticLayout.Builder hyphenationFrequency = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), this.p, i).setAlignment(alignment).setLineSpacing(this.q.getLineSpacingExtra(), this.q.getLineSpacingMultiplier()).setIncludePad(this.q.getIncludeFontPadding()).setBreakStrategy(this.q.getBreakStrategy()).setHyphenationFrequency(this.q.getHyphenationFrequency());
        if (i2 == -1) {
            i2 = ActivityChooserView.a.a;
        }
        return hyphenationFrequency.setMaxLines(i2).setTextDirection(textDirectionHeuristic).build();
    }

    private <T> T a(@android.support.annotation.af Object obj, @android.support.annotation.af String str, @android.support.annotation.af T t) {
        try {
            return (T) a(str).invoke(obj, new Object[0]);
        } catch (Exception e2) {
            Log.w(b, "Failed to invoke TextView#" + str + "() method", e2);
            return t;
        }
    }

    @android.support.annotation.ag
    private Method a(@android.support.annotation.af String str) {
        try {
            Method method = g.get(str);
            if (method == null && (method = TextView.class.getDeclaredMethod(str, new Class[0])) != null) {
                method.setAccessible(true);
                g.put(str, method);
            }
            return method;
        } catch (Exception e2) {
            Log.w(b, "Failed to retrieve TextView#" + str + "() method", e2);
            return null;
        }
    }

    private void a(float f2) {
        if (f2 != this.q.getPaint().getTextSize()) {
            this.q.getPaint().setTextSize(f2);
            boolean isInLayout = Build.VERSION.SDK_INT >= 18 ? this.q.isInLayout() : false;
            if (this.q.getLayout() != null) {
                this.j = false;
                try {
                    Method a2 = a("nullLayouts");
                    if (a2 != null) {
                        a2.invoke(this.q, new Object[0]);
                    }
                } catch (Exception e2) {
                    Log.w(b, "Failed to invoke TextView#nullLayouts() method", e2);
                }
                if (isInLayout) {
                    this.q.forceLayout();
                } else {
                    this.q.requestLayout();
                }
                this.q.invalidate();
            }
        }
    }

    private void a(float f2, float f3, float f4) {
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f2 + "px) is less or equal to (0px)");
        } else if (f3 <= f2) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f3 + "px) is less or equal to minimum auto-size text size (" + f2 + "px)");
        } else if (f4 <= 0.0f) {
            throw new IllegalArgumentException("The auto-size step granularity (" + f4 + "px) is less or equal to (0px)");
        } else {
            this.i = 1;
            this.l = f2;
            this.m = f3;
            this.k = f4;
            this.o = false;
        }
    }

    private void a(TypedArray typedArray) {
        int length = typedArray.length();
        int[] iArr = new int[length];
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                iArr[i] = typedArray.getDimensionPixelSize(i, -1);
            }
            this.n = a(iArr);
            h();
        }
    }

    private boolean a(int i, RectF rectF) {
        CharSequence transformation;
        CharSequence text = this.q.getText();
        TransformationMethod transformationMethod = this.q.getTransformationMethod();
        if (transformationMethod != null && (transformation = transformationMethod.getTransformation(text, this.q)) != null) {
            text = transformation;
        }
        int maxLines = Build.VERSION.SDK_INT >= 16 ? this.q.getMaxLines() : -1;
        TextPaint textPaint = this.p;
        if (textPaint == null) {
            this.p = new TextPaint();
        } else {
            textPaint.reset();
        }
        this.p.set(this.q.getPaint());
        this.p.setTextSize(i);
        Layout.Alignment alignment = (Layout.Alignment) a((Object) this.q, "getLayoutAlignment", (String) Layout.Alignment.ALIGN_NORMAL);
        StaticLayout a2 = Build.VERSION.SDK_INT >= 23 ? a(text, alignment, Math.round(rectF.right), maxLines) : a(text, alignment, Math.round(rectF.right));
        return (maxLines == -1 || (a2.getLineCount() <= maxLines && a2.getLineEnd(a2.getLineCount() - 1) == text.length())) && ((float) a2.getHeight()) <= rectF.bottom;
    }

    private int[] a(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (i > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i)) < 0) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr2[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr2;
    }

    private boolean h() {
        int length = this.n.length;
        this.o = length > 0;
        if (this.o) {
            this.i = 1;
            int[] iArr = this.n;
            this.l = iArr[0];
            this.m = iArr[length - 1];
            this.k = a;
        }
        return this.o;
    }

    private boolean i() {
        if (k() && this.i == 1) {
            if (!this.o || this.n.length == 0) {
                float round = Math.round(this.l);
                int i = 1;
                while (Math.round(this.k + round) <= Math.round(this.m)) {
                    i++;
                    round += this.k;
                }
                int[] iArr = new int[i];
                float f2 = this.l;
                for (int i2 = 0; i2 < i; i2++) {
                    iArr[i2] = Math.round(f2);
                    f2 += this.k;
                }
                this.n = a(iArr);
            }
            this.j = true;
        } else {
            this.j = false;
        }
        return this.j;
    }

    private void j() {
        this.i = 0;
        this.l = a;
        this.m = a;
        this.k = a;
        this.n = new int[0];
        this.j = false;
    }

    private boolean k() {
        return !(this.q instanceof AppCompatEditText);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public int a() {
        return this.i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void a(int i) {
        if (k()) {
            if (i == 0) {
                j();
            } else if (i != 1) {
                throw new IllegalArgumentException("Unknown auto-size text type: " + i);
            } else {
                DisplayMetrics displayMetrics = this.r.getResources().getDisplayMetrics();
                a(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
                if (i()) {
                    f();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void a(int i, float f2) {
        Context context = this.r;
        a(TypedValue.applyDimension(i, f2, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void a(int i, int i2, int i3, int i4) {
        if (k()) {
            DisplayMetrics displayMetrics = this.r.getResources().getDisplayMetrics();
            a(TypedValue.applyDimension(i4, i, displayMetrics), TypedValue.applyDimension(i4, i2, displayMetrics), TypedValue.applyDimension(i4, i3, displayMetrics));
            if (i()) {
                f();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(AttributeSet attributeSet, int i) {
        int resourceId;
        TypedArray obtainStyledAttributes = this.r.obtainStyledAttributes(attributeSet, a.l.AppCompatTextView, i, 0);
        if (obtainStyledAttributes.hasValue(a.l.AppCompatTextView_autoSizeTextType)) {
            this.i = obtainStyledAttributes.getInt(a.l.AppCompatTextView_autoSizeTextType, 0);
        }
        float dimension = obtainStyledAttributes.hasValue(a.l.AppCompatTextView_autoSizeStepGranularity) ? obtainStyledAttributes.getDimension(a.l.AppCompatTextView_autoSizeStepGranularity, a) : a;
        float dimension2 = obtainStyledAttributes.hasValue(a.l.AppCompatTextView_autoSizeMinTextSize) ? obtainStyledAttributes.getDimension(a.l.AppCompatTextView_autoSizeMinTextSize, a) : a;
        float dimension3 = obtainStyledAttributes.hasValue(a.l.AppCompatTextView_autoSizeMaxTextSize) ? obtainStyledAttributes.getDimension(a.l.AppCompatTextView_autoSizeMaxTextSize, a) : a;
        if (obtainStyledAttributes.hasValue(a.l.AppCompatTextView_autoSizePresetSizes) && (resourceId = obtainStyledAttributes.getResourceId(a.l.AppCompatTextView_autoSizePresetSizes, 0)) > 0) {
            TypedArray obtainTypedArray = obtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            a(obtainTypedArray);
            obtainTypedArray.recycle();
        }
        obtainStyledAttributes.recycle();
        if (!k()) {
            this.i = 0;
        } else if (this.i == 1) {
            if (!this.o) {
                DisplayMetrics displayMetrics = this.r.getResources().getDisplayMetrics();
                if (dimension2 == a) {
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (dimension3 == a) {
                    dimension3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (dimension == a) {
                    dimension = 1.0f;
                }
                a(dimension2, dimension3, dimension);
            }
            i();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void a(@android.support.annotation.af int[] iArr, int i) {
        if (k()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArr2 = new int[length];
                if (i == 0) {
                    iArr2 = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = this.r.getResources().getDisplayMetrics();
                    for (int i2 = 0; i2 < length; i2++) {
                        iArr2[i2] = Math.round(TypedValue.applyDimension(i, iArr[i2], displayMetrics));
                    }
                }
                this.n = a(iArr2);
                if (!h()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                this.o = false;
            }
            if (i()) {
                f();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public int b() {
        return Math.round(this.k);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public int c() {
        return Math.round(this.l);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public int d() {
        return Math.round(this.m);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public int[] e() {
        return this.n;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void f() {
        if (g()) {
            if (this.j) {
                if (this.q.getMeasuredHeight() <= 0 || this.q.getMeasuredWidth() <= 0) {
                    return;
                }
                int measuredWidth = ((Boolean) a((Object) this.q, "getHorizontallyScrolling", (String) false)).booleanValue() ? 1048576 : (this.q.getMeasuredWidth() - this.q.getTotalPaddingLeft()) - this.q.getTotalPaddingRight();
                int height = (this.q.getHeight() - this.q.getCompoundPaddingBottom()) - this.q.getCompoundPaddingTop();
                if (measuredWidth <= 0 || height <= 0) {
                    return;
                }
                synchronized (c) {
                    c.setEmpty();
                    c.right = measuredWidth;
                    c.bottom = height;
                    float a2 = a(c);
                    if (a2 != this.q.getTextSize()) {
                        a(0, a2);
                    }
                }
            }
            this.j = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public boolean g() {
        return k() && this.i != 0;
    }
}
