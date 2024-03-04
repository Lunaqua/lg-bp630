package android.support.v4.view.a;

import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.af;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class e {
    private final AccessibilityRecord a;

    @Deprecated
    public e(Object obj) {
        this.a = (AccessibilityRecord) obj;
    }

    public static int a(AccessibilityRecord accessibilityRecord) {
        if (Build.VERSION.SDK_INT >= 15) {
            return accessibilityRecord.getMaxScrollX();
        }
        return 0;
    }

    @Deprecated
    public static e a(e eVar) {
        return new e(AccessibilityRecord.obtain(eVar.a));
    }

    public static void a(AccessibilityRecord accessibilityRecord, int i) {
        if (Build.VERSION.SDK_INT >= 15) {
            accessibilityRecord.setMaxScrollX(i);
        }
    }

    public static void a(@af AccessibilityRecord accessibilityRecord, View view, int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            accessibilityRecord.setSource(view, i);
        }
    }

    public static int b(AccessibilityRecord accessibilityRecord) {
        if (Build.VERSION.SDK_INT >= 15) {
            return accessibilityRecord.getMaxScrollY();
        }
        return 0;
    }

    @Deprecated
    public static e b() {
        return new e(AccessibilityRecord.obtain());
    }

    public static void b(AccessibilityRecord accessibilityRecord, int i) {
        if (Build.VERSION.SDK_INT >= 15) {
            accessibilityRecord.setMaxScrollY(i);
        }
    }

    @Deprecated
    public Object a() {
        return this.a;
    }

    @Deprecated
    public void a(int i) {
        this.a.setItemCount(i);
    }

    @Deprecated
    public void a(Parcelable parcelable) {
        this.a.setParcelableData(parcelable);
    }

    @Deprecated
    public void a(View view) {
        this.a.setSource(view);
    }

    @Deprecated
    public void a(View view, int i) {
        a(this.a, view, i);
    }

    @Deprecated
    public void a(CharSequence charSequence) {
        this.a.setClassName(charSequence);
    }

    @Deprecated
    public void a(boolean z) {
        this.a.setChecked(z);
    }

    @Deprecated
    public void b(int i) {
        this.a.setCurrentItemIndex(i);
    }

    @Deprecated
    public void b(CharSequence charSequence) {
        this.a.setBeforeText(charSequence);
    }

    @Deprecated
    public void b(boolean z) {
        this.a.setEnabled(z);
    }

    @Deprecated
    public c c() {
        return c.a((Object) this.a.getSource());
    }

    @Deprecated
    public void c(int i) {
        this.a.setFromIndex(i);
    }

    @Deprecated
    public void c(CharSequence charSequence) {
        this.a.setContentDescription(charSequence);
    }

    @Deprecated
    public void c(boolean z) {
        this.a.setPassword(z);
    }

    @Deprecated
    public int d() {
        return this.a.getWindowId();
    }

    @Deprecated
    public void d(int i) {
        this.a.setToIndex(i);
    }

    @Deprecated
    public void d(boolean z) {
        this.a.setFullScreen(z);
    }

    @Deprecated
    public void e(int i) {
        this.a.setScrollX(i);
    }

    @Deprecated
    public void e(boolean z) {
        this.a.setScrollable(z);
    }

    @Deprecated
    public boolean e() {
        return this.a.isChecked();
    }

    @Deprecated
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            e eVar = (e) obj;
            AccessibilityRecord accessibilityRecord = this.a;
            if (accessibilityRecord == null) {
                if (eVar.a != null) {
                    return false;
                }
            } else if (!accessibilityRecord.equals(eVar.a)) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Deprecated
    public void f(int i) {
        this.a.setScrollY(i);
    }

    @Deprecated
    public boolean f() {
        return this.a.isEnabled();
    }

    @Deprecated
    public void g(int i) {
        a(this.a, i);
    }

    @Deprecated
    public boolean g() {
        return this.a.isPassword();
    }

    @Deprecated
    public void h(int i) {
        b(this.a, i);
    }

    @Deprecated
    public boolean h() {
        return this.a.isFullScreen();
    }

    @Deprecated
    public int hashCode() {
        AccessibilityRecord accessibilityRecord = this.a;
        if (accessibilityRecord == null) {
            return 0;
        }
        return accessibilityRecord.hashCode();
    }

    @Deprecated
    public void i(int i) {
        this.a.setAddedCount(i);
    }

    @Deprecated
    public boolean i() {
        return this.a.isScrollable();
    }

    @Deprecated
    public int j() {
        return this.a.getItemCount();
    }

    @Deprecated
    public void j(int i) {
        this.a.setRemovedCount(i);
    }

    @Deprecated
    public int k() {
        return this.a.getCurrentItemIndex();
    }

    @Deprecated
    public int l() {
        return this.a.getFromIndex();
    }

    @Deprecated
    public int m() {
        return this.a.getToIndex();
    }

    @Deprecated
    public int n() {
        return this.a.getScrollX();
    }

    @Deprecated
    public int o() {
        return this.a.getScrollY();
    }

    @Deprecated
    public int p() {
        return a(this.a);
    }

    @Deprecated
    public int q() {
        return b(this.a);
    }

    @Deprecated
    public int r() {
        return this.a.getAddedCount();
    }

    @Deprecated
    public int s() {
        return this.a.getRemovedCount();
    }

    @Deprecated
    public CharSequence t() {
        return this.a.getClassName();
    }

    @Deprecated
    public List<CharSequence> u() {
        return this.a.getText();
    }

    @Deprecated
    public CharSequence v() {
        return this.a.getBeforeText();
    }

    @Deprecated
    public CharSequence w() {
        return this.a.getContentDescription();
    }

    @Deprecated
    public Parcelable x() {
        return this.a.getParcelableData();
    }

    @Deprecated
    public void y() {
        this.a.recycle();
    }
}
