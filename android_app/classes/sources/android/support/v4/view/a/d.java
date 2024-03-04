package android.support.v4.view.a;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.ArrayList;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class d {
    public static final int a = -1;
    private final Object b;

    @ak(a = 16)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static class a extends AccessibilityNodeProvider {
        final d a;

        a(d dVar) {
            this.a = dVar;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            c a = this.a.a(i);
            if (a == null) {
                return null;
            }
            return a.a();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            List<c> a = this.a.a(str, i);
            if (a == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int size = a.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(a.get(i2).a());
            }
            return arrayList;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i, int i2, Bundle bundle) {
            return this.a.a(i, i2, bundle);
        }
    }

    @ak(a = 19)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static class b extends a {
        b(d dVar) {
            super(dVar);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int i) {
            c b = this.a.b(i);
            if (b == null) {
                return null;
            }
            return b.a();
        }
    }

    public d() {
        this.b = Build.VERSION.SDK_INT >= 19 ? new b(this) : Build.VERSION.SDK_INT >= 16 ? new a(this) : null;
    }

    public d(Object obj) {
        this.b = obj;
    }

    @ag
    public c a(int i) {
        return null;
    }

    public Object a() {
        return this.b;
    }

    @ag
    public List<c> a(String str, int i) {
        return null;
    }

    public boolean a(int i, int i2, Bundle bundle) {
        return false;
    }

    @ag
    public c b(int i) {
        return null;
    }
}
