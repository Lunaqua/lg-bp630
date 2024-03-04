package android.support.v7.widget;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ah extends android.support.v4.view.a {
    final RecyclerView a;
    final android.support.v4.view.a b = new a(this);

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a extends android.support.v4.view.a {
        final ah a;

        public a(@android.support.annotation.af ah ahVar) {
            this.a = ahVar;
        }

        @Override // android.support.v4.view.a
        public void a(View view, android.support.v4.view.a.c cVar) {
            super.a(view, cVar);
            if (this.a.b() || this.a.a.getLayoutManager() == null) {
                return;
            }
            this.a.a.getLayoutManager().a(view, cVar);
        }

        @Override // android.support.v4.view.a
        public boolean a(View view, int i, Bundle bundle) {
            if (super.a(view, i, bundle)) {
                return true;
            }
            if (this.a.b() || this.a.a.getLayoutManager() == null) {
                return false;
            }
            return this.a.a.getLayoutManager().a(view, i, bundle);
        }
    }

    public ah(@android.support.annotation.af RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    @Override // android.support.v4.view.a
    public void a(View view, android.support.v4.view.a.c cVar) {
        super.a(view, cVar);
        cVar.b((CharSequence) RecyclerView.class.getName());
        if (b() || this.a.getLayoutManager() == null) {
            return;
        }
        this.a.getLayoutManager().a(cVar);
    }

    @Override // android.support.v4.view.a
    public boolean a(View view, int i, Bundle bundle) {
        if (super.a(view, i, bundle)) {
            return true;
        }
        if (b() || this.a.getLayoutManager() == null) {
            return false;
        }
        return this.a.getLayoutManager().a(i, bundle);
    }

    boolean b() {
        return this.a.B();
    }

    @android.support.annotation.af
    public android.support.v4.view.a c() {
        return this.b;
    }

    @Override // android.support.v4.view.a
    public void d(View view, AccessibilityEvent accessibilityEvent) {
        super.d(view, accessibilityEvent);
        accessibilityEvent.setClassName(RecyclerView.class.getName());
        if (!(view instanceof RecyclerView) || b()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().a(accessibilityEvent);
        }
    }
}
