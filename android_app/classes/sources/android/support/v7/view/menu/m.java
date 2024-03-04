package android.support.v7.view.menu;

import android.content.Context;
import android.support.annotation.ak;
import android.support.annotation.an;
import android.support.v4.view.b;
import android.support.v7.view.menu.l;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;
/* JADX INFO: Access modifiers changed from: package-private */
@ak(a = 16)
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class m extends l {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    class a extends l.a implements ActionProvider.VisibilityListener {
        b.InterfaceC0033b c;

        public a(Context context, ActionProvider actionProvider) {
            super(context, actionProvider);
        }

        @Override // android.support.v4.view.b
        public View a(MenuItem menuItem) {
            return this.a.onCreateActionView(menuItem);
        }

        @Override // android.support.v4.view.b
        public void a(b.InterfaceC0033b interfaceC0033b) {
            this.c = interfaceC0033b;
            this.a.setVisibilityListener(interfaceC0033b != null ? this : null);
        }

        @Override // android.support.v4.view.b
        public boolean c() {
            return this.a.overridesItemVisibility();
        }

        @Override // android.support.v4.view.b
        public boolean d() {
            return this.a.isVisible();
        }

        @Override // android.support.v4.view.b
        public void e() {
            this.a.refreshVisibility();
        }

        @Override // android.view.ActionProvider.VisibilityListener
        public void onActionProviderVisibilityChanged(boolean z) {
            b.InterfaceC0033b interfaceC0033b = this.c;
            if (interfaceC0033b != null) {
                interfaceC0033b.a(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(Context context, android.support.v4.d.a.b bVar) {
        super(context, bVar);
    }

    @Override // android.support.v7.view.menu.l
    l.a a(ActionProvider actionProvider) {
        return new a(this.a, actionProvider);
    }
}
