package android.support.v4.app;

import android.app.Activity;
import android.arch.lifecycle.d;
import android.arch.lifecycle.f;
import android.arch.lifecycle.g;
import android.arch.lifecycle.p;
import android.os.Bundle;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.annotation.i;
import android.support.v4.j.r;
import android.support.v4.view.h;
import android.view.KeyEvent;
import android.view.View;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class SupportActivity extends Activity implements f, h.a {
    private r<Class<? extends ExtraData>, ExtraData> mExtraDataMap = new r<>();
    private g mLifecycleRegistry = new g(this);

    @an(a = {an.a.LIBRARY_GROUP})
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class ExtraData {
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !h.a(decorView, keyEvent)) {
            return h.a(this, decorView, this, keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !h.a(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public <T extends ExtraData> T getExtraData(Class<T> cls) {
        return (T) this.mExtraDataMap.get(cls);
    }

    public d getLifecycle() {
        return this.mLifecycleRegistry;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(@ag Bundle bundle) {
        super.onCreate(bundle);
        p.a(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    @i
    public void onSaveInstanceState(Bundle bundle) {
        this.mLifecycleRegistry.a(d.b.CREATED);
        super.onSaveInstanceState(bundle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @an(a = {an.a.LIBRARY_GROUP})
    public void putExtraData(ExtraData extraData) {
        this.mExtraDataMap.put(extraData.getClass(), extraData);
    }

    @Override // android.support.v4.view.h.a
    @an(a = {an.a.LIBRARY_GROUP})
    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }
}
