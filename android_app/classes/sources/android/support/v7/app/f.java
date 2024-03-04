package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.aa;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.annotation.v;
import android.support.v4.view.h;
import android.support.v7.a.a;
import android.support.v7.view.b;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class f extends Dialog implements d {
    private e a;
    private final h.a b;

    public f(Context context) {
        this(context, 0);
    }

    public f(Context context, int i) {
        super(context, a(context, i));
        this.b = new h.a() { // from class: android.support.v7.app.f.1
            @Override // android.support.v4.view.h.a
            public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
                return f.this.a(keyEvent);
            }
        };
        c().a((Bundle) null);
        c().k();
    }

    protected f(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.b = new h.a() { // from class: android.support.v7.app.f.1
            @Override // android.support.v4.view.h.a
            public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
                return f.this.a(keyEvent);
            }
        };
    }

    private static int a(Context context, int i) {
        if (i == 0) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(a.b.dialogTheme, typedValue, true);
            return typedValue.resourceId;
        }
        return i;
    }

    @Override // android.support.v7.app.d
    @ag
    public android.support.v7.view.b a(b.a aVar) {
        return null;
    }

    @Override // android.support.v7.app.d
    public void a(android.support.v7.view.b bVar) {
    }

    boolean a(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        c().b(view, layoutParams);
    }

    public ActionBar b() {
        return c().a();
    }

    @Override // android.support.v7.app.d
    public void b(android.support.v7.view.b bVar) {
    }

    public e c() {
        if (this.a == null) {
            this.a = e.a(this, this);
        }
        return this.a;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return android.support.v4.view.h.a(this.b, getWindow().getDecorView(), this, keyEvent);
    }

    public boolean e(int i) {
        return c().c(i);
    }

    @Override // android.app.Dialog
    @ag
    public <T extends View> T findViewById(@v int i) {
        return (T) c().a(i);
    }

    @Override // android.app.Dialog
    @an(a = {an.a.LIBRARY_GROUP})
    public void invalidateOptionsMenu() {
        c().f();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        c().i();
        super.onCreate(bundle);
        c().a(bundle);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
        c().d();
    }

    @Override // android.app.Dialog
    public void setContentView(@aa int i) {
        c().b(i);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        c().a(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        c().a(view, layoutParams);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        super.setTitle(i);
        c().a(getContext().getString(i));
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        c().a(charSequence);
    }
}
