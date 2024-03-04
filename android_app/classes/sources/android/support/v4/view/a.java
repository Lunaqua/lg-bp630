package android.support.v4.view;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a {
    private static final View.AccessibilityDelegate a = new View.AccessibilityDelegate();
    private final View.AccessibilityDelegate b = new C0030a(this);

    /* renamed from: android.support.v4.view.a$a  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static final class C0030a extends View.AccessibilityDelegate {
        private final a a;

        C0030a(a aVar) {
            this.a = aVar;
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.a.b(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        @android.support.annotation.ak(a = 16)
        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            android.support.v4.view.a.d a = this.a.a(view);
            if (a != null) {
                return (AccessibilityNodeProvider) a.a();
            }
            return null;
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.a.d(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            this.a.a(view, android.support.v4.view.a.c.a(accessibilityNodeInfo));
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.a.c(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.a.a(viewGroup, view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            return this.a.a(view, i, bundle);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(View view, int i) {
            this.a.a(view, i);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.a.a(view, accessibilityEvent);
        }
    }

    public android.support.v4.view.a.d a(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider;
        if (Build.VERSION.SDK_INT < 16 || (accessibilityNodeProvider = a.getAccessibilityNodeProvider(view)) == null) {
            return null;
        }
        return new android.support.v4.view.a.d(accessibilityNodeProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View.AccessibilityDelegate a() {
        return this.b;
    }

    public void a(View view, int i) {
        a.sendAccessibilityEvent(view, i);
    }

    public void a(View view, android.support.v4.view.a.c cVar) {
        a.onInitializeAccessibilityNodeInfo(view, cVar.a());
    }

    public void a(View view, AccessibilityEvent accessibilityEvent) {
        a.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public boolean a(View view, int i, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return a.performAccessibilityAction(view, i, bundle);
        }
        return false;
    }

    public boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public boolean b(View view, AccessibilityEvent accessibilityEvent) {
        return a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public void c(View view, AccessibilityEvent accessibilityEvent) {
        a.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public void d(View view, AccessibilityEvent accessibilityEvent) {
        a.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }
}
