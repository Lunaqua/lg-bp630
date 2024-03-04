package android.support.v4.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ai;
import android.support.annotation.ak;
import android.support.annotation.an;
import android.support.annotation.ar;
import android.support.v4.i.d;
import android.text.Editable;
import android.text.PrecomputedText;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class u {
    public static final int a = 0;
    public static final int b = 1;
    private static final String c = "TextViewCompat";
    private static Field d = null;
    private static boolean e = false;
    private static Field f = null;
    private static boolean g = false;
    private static Field h = null;
    private static boolean i = false;
    private static Field j = null;
    private static boolean k = false;
    private static final int l = 1;

    @an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface a {
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ak(a = 26)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b implements ActionMode.Callback {
        private static final int a = 100;
        private final ActionMode.Callback b;
        private final TextView c;
        private Class d;
        private Method e;
        private boolean f;
        private boolean g = false;

        b(ActionMode.Callback callback, TextView textView) {
            this.b = callback;
            this.c = textView;
        }

        private Intent a() {
            return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
        }

        private Intent a(ResolveInfo resolveInfo, TextView textView) {
            return a().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !a(textView)).setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        }

        private List<ResolveInfo> a(Context context, PackageManager packageManager) {
            ArrayList arrayList = new ArrayList();
            if (context instanceof Activity) {
                for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(a(), 0)) {
                    if (a(resolveInfo, context)) {
                        arrayList.add(resolveInfo);
                    }
                }
                return arrayList;
            }
            return arrayList;
        }

        private void a(Menu menu) {
            Context context = this.c.getContext();
            PackageManager packageManager = context.getPackageManager();
            if (!this.g) {
                this.g = true;
                try {
                    this.d = Class.forName("com.android.internal.view.menu.MenuBuilder");
                    this.e = this.d.getDeclaredMethod("removeItemAt", Integer.TYPE);
                    this.f = true;
                } catch (ClassNotFoundException | NoSuchMethodException unused) {
                    this.d = null;
                    this.e = null;
                    this.f = false;
                }
            }
            try {
                Method declaredMethod = (this.f && this.d.isInstance(menu)) ? this.e : menu.getClass().getDeclaredMethod("removeItemAt", Integer.TYPE);
                for (int size = menu.size() - 1; size >= 0; size--) {
                    MenuItem item = menu.getItem(size);
                    if (item.getIntent() != null && "android.intent.action.PROCESS_TEXT".equals(item.getIntent().getAction())) {
                        declaredMethod.invoke(menu, Integer.valueOf(size));
                    }
                }
                List<ResolveInfo> a2 = a(context, packageManager);
                for (int i = 0; i < a2.size(); i++) {
                    ResolveInfo resolveInfo = a2.get(i);
                    menu.add(0, 0, i + 100, resolveInfo.loadLabel(packageManager)).setIntent(a(resolveInfo, this.c)).setShowAsAction(1);
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
            }
        }

        private boolean a(ResolveInfo resolveInfo, Context context) {
            if (context.getPackageName().equals(resolveInfo.activityInfo.packageName)) {
                return true;
            }
            if (resolveInfo.activityInfo.exported) {
                return resolveInfo.activityInfo.permission == null || context.checkSelfPermission(resolveInfo.activityInfo.permission) == 0;
            }
            return false;
        }

        private boolean a(TextView textView) {
            return (textView instanceof Editable) && textView.onCheckIsTextEditor() && textView.isEnabled();
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.b.onActionItemClicked(actionMode, menuItem);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.b.onCreateActionMode(actionMode, menu);
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
            this.b.onDestroyActionMode(actionMode);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            a(menu);
            return this.b.onPrepareActionMode(actionMode, menu);
        }
    }

    private u() {
    }

    @ak(a = 18)
    private static int a(@af TextDirectionHeuristic textDirectionHeuristic) {
        if (textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL || textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
            return 1;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.ANYRTL_LTR) {
            return 2;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.LTR) {
            return 3;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.RTL) {
            return 4;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.LOCALE) {
            return 5;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
            return 6;
        }
        return textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL ? 7 : 1;
    }

    public static int a(@af TextView textView) {
        if (Build.VERSION.SDK_INT >= 16) {
            return textView.getMaxLines();
        }
        if (!g) {
            f = a("mMaxMode");
            g = true;
        }
        Field field = f;
        if (field == null || a(field, textView) != 1) {
            return -1;
        }
        if (!e) {
            d = a("mMaximum");
            e = true;
        }
        Field field2 = d;
        if (field2 != null) {
            return a(field2, textView);
        }
        return -1;
    }

    private static int a(Field field, TextView textView) {
        try {
            return field.getInt(textView);
        } catch (IllegalAccessException unused) {
            Log.d(c, "Could not retrieve value of " + field.getName() + " field.");
            return -1;
        }
    }

    private static Field a(String str) {
        Field field = null;
        try {
            field = TextView.class.getDeclaredField(str);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException unused) {
            Log.e(c, "Could not retrieve " + str + " field.");
            return field;
        }
    }

    public static void a(@af TextView textView, @ar int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            textView.setTextAppearance(i2);
        } else {
            textView.setTextAppearance(textView.getContext(), i2);
        }
    }

    public static void a(@af TextView textView, @android.support.annotation.p int i2, @android.support.annotation.p int i3, @android.support.annotation.p int i4, @android.support.annotation.p int i5) {
        if (Build.VERSION.SDK_INT >= 18) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(i2, i3, i4, i5);
        } else if (Build.VERSION.SDK_INT < 17) {
            textView.setCompoundDrawablesWithIntrinsicBounds(i2, i3, i4, i5);
        } else {
            boolean z = textView.getLayoutDirection() == 1;
            int i6 = z ? i4 : i2;
            if (!z) {
                i2 = i4;
            }
            textView.setCompoundDrawablesWithIntrinsicBounds(i6, i3, i2, i5);
        }
    }

    public static void a(@af TextView textView, @ag Drawable drawable, @ag Drawable drawable2, @ag Drawable drawable3, @ag Drawable drawable4) {
        if (Build.VERSION.SDK_INT >= 18) {
            textView.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        } else if (Build.VERSION.SDK_INT < 17) {
            textView.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        } else {
            boolean z = textView.getLayoutDirection() == 1;
            Drawable drawable5 = z ? drawable3 : drawable;
            if (!z) {
                drawable = drawable3;
            }
            textView.setCompoundDrawables(drawable5, drawable2, drawable, drawable4);
        }
    }

    public static void a(@af TextView textView, @af d.a aVar) {
        if (Build.VERSION.SDK_INT >= 18) {
            textView.setTextDirection(a(aVar.b()));
        }
        if (Build.VERSION.SDK_INT >= 23) {
            textView.getPaint().set(aVar.a());
            textView.setBreakStrategy(aVar.c());
            textView.setHyphenationFrequency(aVar.d());
            return;
        }
        float textScaleX = aVar.a().getTextScaleX();
        textView.getPaint().set(aVar.a());
        if (textScaleX == textView.getTextScaleX()) {
            textView.setTextScaleX((textScaleX / 2.0f) + 1.0f);
        }
        textView.setTextScaleX(textScaleX);
    }

    public static void a(@af TextView textView, @af android.support.v4.i.d dVar) {
        PrecomputedText precomputedText;
        if (Build.VERSION.SDK_INT >= 28) {
            precomputedText = dVar.a();
        } else {
            boolean equals = k(textView).equals(dVar.b());
            precomputedText = dVar;
            if (!equals) {
                throw new IllegalArgumentException("Given text can not be applied to TextView.");
            }
        }
        textView.setText(precomputedText);
    }

    public static void a(@af TextView textView, @af ActionMode.Callback callback) {
        textView.setCustomSelectionActionModeCallback(b(textView, callback));
    }

    public static void a(@af TextView textView, @af int[] iArr, int i2) {
        if (Build.VERSION.SDK_INT >= 27) {
            textView.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
        } else if (textView instanceof android.support.v4.widget.b) {
            ((android.support.v4.widget.b) textView).setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
        }
    }

    public static int b(@af TextView textView) {
        if (Build.VERSION.SDK_INT >= 16) {
            return textView.getMinLines();
        }
        if (!k) {
            j = a("mMinMode");
            k = true;
        }
        Field field = j;
        if (field == null || a(field, textView) != 1) {
            return -1;
        }
        if (!i) {
            h = a("mMinimum");
            i = true;
        }
        Field field2 = h;
        if (field2 != null) {
            return a(field2, textView);
        }
        return -1;
    }

    @af
    @an(a = {an.a.LIBRARY_GROUP})
    public static ActionMode.Callback b(@af TextView textView, @af ActionMode.Callback callback) {
        return (Build.VERSION.SDK_INT < 26 || Build.VERSION.SDK_INT > 27 || (callback instanceof b)) ? callback : new b(callback, textView);
    }

    public static void b(@af TextView textView, int i2) {
        if (Build.VERSION.SDK_INT >= 27) {
            textView.setAutoSizeTextTypeWithDefaults(i2);
        } else if (textView instanceof android.support.v4.widget.b) {
            ((android.support.v4.widget.b) textView).setAutoSizeTextTypeWithDefaults(i2);
        }
    }

    public static void b(@af TextView textView, int i2, int i3, int i4, int i5) {
        if (Build.VERSION.SDK_INT >= 27) {
            textView.setAutoSizeTextTypeUniformWithConfiguration(i2, i3, i4, i5);
        } else if (textView instanceof android.support.v4.widget.b) {
            ((android.support.v4.widget.b) textView).setAutoSizeTextTypeUniformWithConfiguration(i2, i3, i4, i5);
        }
    }

    public static void b(@af TextView textView, @ag Drawable drawable, @ag Drawable drawable2, @ag Drawable drawable3, @ag Drawable drawable4) {
        if (Build.VERSION.SDK_INT >= 18) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        } else if (Build.VERSION.SDK_INT < 17) {
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        } else {
            boolean z = textView.getLayoutDirection() == 1;
            Drawable drawable5 = z ? drawable3 : drawable;
            if (!z) {
                drawable = drawable3;
            }
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable5, drawable2, drawable, drawable4);
        }
    }

    public static void c(@af TextView textView, @android.support.annotation.x(a = 0) @ai int i2) {
        android.support.v4.j.q.a(i2);
        if (Build.VERSION.SDK_INT >= 28) {
            textView.setFirstBaselineToTopHeight(i2);
            return;
        }
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i3 = (Build.VERSION.SDK_INT < 16 || textView.getIncludeFontPadding()) ? fontMetricsInt.top : fontMetricsInt.ascent;
        if (i2 > Math.abs(i3)) {
            textView.setPadding(textView.getPaddingLeft(), i2 - (-i3), textView.getPaddingRight(), textView.getPaddingBottom());
        }
    }

    @af
    public static Drawable[] c(@af TextView textView) {
        if (Build.VERSION.SDK_INT >= 18) {
            return textView.getCompoundDrawablesRelative();
        }
        if (Build.VERSION.SDK_INT >= 17) {
            boolean z = textView.getLayoutDirection() == 1;
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            if (z) {
                Drawable drawable = compoundDrawables[2];
                Drawable drawable2 = compoundDrawables[0];
                compoundDrawables[0] = drawable;
                compoundDrawables[2] = drawable2;
            }
            return compoundDrawables;
        }
        return textView.getCompoundDrawables();
    }

    public static int d(@af TextView textView) {
        if (Build.VERSION.SDK_INT >= 27) {
            return textView.getAutoSizeTextType();
        }
        if (textView instanceof android.support.v4.widget.b) {
            return ((android.support.v4.widget.b) textView).getAutoSizeTextType();
        }
        return 0;
    }

    public static void d(@af TextView textView, @android.support.annotation.x(a = 0) @ai int i2) {
        android.support.v4.j.q.a(i2);
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i3 = (Build.VERSION.SDK_INT < 16 || textView.getIncludeFontPadding()) ? fontMetricsInt.bottom : fontMetricsInt.descent;
        if (i2 > Math.abs(i3)) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i2 - i3);
        }
    }

    public static int e(@af TextView textView) {
        if (Build.VERSION.SDK_INT >= 27) {
            return textView.getAutoSizeStepGranularity();
        }
        if (textView instanceof android.support.v4.widget.b) {
            return ((android.support.v4.widget.b) textView).getAutoSizeStepGranularity();
        }
        return -1;
    }

    public static void e(@af TextView textView, @android.support.annotation.x(a = 0) @ai int i2) {
        android.support.v4.j.q.a(i2);
        int fontMetricsInt = textView.getPaint().getFontMetricsInt(null);
        if (i2 != fontMetricsInt) {
            textView.setLineSpacing(i2 - fontMetricsInt, 1.0f);
        }
    }

    public static int f(@af TextView textView) {
        if (Build.VERSION.SDK_INT >= 27) {
            return textView.getAutoSizeMinTextSize();
        }
        if (textView instanceof android.support.v4.widget.b) {
            return ((android.support.v4.widget.b) textView).getAutoSizeMinTextSize();
        }
        return -1;
    }

    public static int g(@af TextView textView) {
        if (Build.VERSION.SDK_INT >= 27) {
            return textView.getAutoSizeMaxTextSize();
        }
        if (textView instanceof android.support.v4.widget.b) {
            return ((android.support.v4.widget.b) textView).getAutoSizeMaxTextSize();
        }
        return -1;
    }

    @af
    public static int[] h(@af TextView textView) {
        return Build.VERSION.SDK_INT >= 27 ? textView.getAutoSizeTextAvailableSizes() : textView instanceof android.support.v4.widget.b ? ((android.support.v4.widget.b) textView).getAutoSizeTextAvailableSizes() : new int[0];
    }

    public static int i(@af TextView textView) {
        return textView.getPaddingTop() - textView.getPaint().getFontMetricsInt().top;
    }

    public static int j(@af TextView textView) {
        return textView.getPaddingBottom() + textView.getPaint().getFontMetricsInt().bottom;
    }

    @af
    public static d.a k(@af TextView textView) {
        if (Build.VERSION.SDK_INT >= 28) {
            return new d.a(textView.getTextMetricsParams());
        }
        d.a.C0027a c0027a = new d.a.C0027a(new TextPaint(textView.getPaint()));
        if (Build.VERSION.SDK_INT >= 23) {
            c0027a.a(textView.getBreakStrategy());
            c0027a.b(textView.getHyphenationFrequency());
        }
        if (Build.VERSION.SDK_INT >= 18) {
            c0027a.a(l(textView));
        }
        return c0027a.a();
    }

    @ak(a = 18)
    private static TextDirectionHeuristic l(@af TextView textView) {
        if (textView.getTransformationMethod() instanceof PasswordTransformationMethod) {
            return TextDirectionHeuristics.LTR;
        }
        if (Build.VERSION.SDK_INT >= 28 && (textView.getInputType() & 15) == 3) {
            byte directionality = Character.getDirectionality(DecimalFormatSymbols.getInstance(textView.getTextLocale()).getDigitStrings()[0].codePointAt(0));
            return (directionality == 1 || directionality == 2) ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
        }
        boolean z = textView.getLayoutDirection() == 1;
        switch (textView.getTextDirection()) {
            case 2:
                return TextDirectionHeuristics.ANYRTL_LTR;
            case 3:
                return TextDirectionHeuristics.LTR;
            case 4:
                return TextDirectionHeuristics.RTL;
            case 5:
                return TextDirectionHeuristics.LOCALE;
            case 6:
                return TextDirectionHeuristics.FIRSTSTRONG_LTR;
            case 7:
                return TextDirectionHeuristics.FIRSTSTRONG_RTL;
            default:
                return z ? TextDirectionHeuristics.FIRSTSTRONG_RTL : TextDirectionHeuristics.FIRSTSTRONG_LTR;
        }
    }
}
