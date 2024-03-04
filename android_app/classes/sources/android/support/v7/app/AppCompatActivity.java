package android.support.v7.app;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.aa;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ar;
import android.support.annotation.v;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.a;
import android.support.v7.view.b;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ba;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class AppCompatActivity extends FragmentActivity implements TaskStackBuilder.SupportParentable, a.b, d {
    private e a;
    private int b = 0;
    private Resources c;

    private boolean a(int i, KeyEvent keyEvent) {
        Window window;
        return (Build.VERSION.SDK_INT >= 26 || keyEvent.isCtrlPressed() || KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) || keyEvent.getRepeatCount() != 0 || KeyEvent.isModifierKey(keyEvent.getKeyCode()) || (window = getWindow()) == null || window.getDecorView() == null || !window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) ? false : true;
    }

    @Override // android.support.v7.app.a.b
    @ag
    public a.InterfaceC0038a a() {
        return e().h();
    }

    @Override // android.support.v7.app.d
    @ag
    public android.support.v7.view.b a(@af b.a aVar) {
        return null;
    }

    public void a(@af TaskStackBuilder taskStackBuilder) {
        taskStackBuilder.addParentStack(this);
    }

    @Override // android.support.v7.app.d
    @android.support.annotation.i
    public void a(@af android.support.v7.view.b bVar) {
    }

    public void a(@ag Toolbar toolbar) {
        e().a(toolbar);
    }

    @Deprecated
    public void a(boolean z) {
    }

    public boolean a(int i) {
        return e().c(i);
    }

    public boolean a(@af Intent intent) {
        return NavUtils.shouldUpRecreateTask(this, intent);
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        e().b(view, layoutParams);
    }

    @ag
    public ActionBar b() {
        return e().a();
    }

    @ag
    public android.support.v7.view.b b(@af b.a aVar) {
        return e().a(aVar);
    }

    @Deprecated
    public void b(int i) {
    }

    public void b(@af Intent intent) {
        NavUtils.navigateUpTo(this, intent);
    }

    public void b(@af TaskStackBuilder taskStackBuilder) {
    }

    @Override // android.support.v7.app.d
    @android.support.annotation.i
    public void b(@af android.support.v7.view.b bVar) {
    }

    @Deprecated
    public void b(boolean z) {
    }

    @Deprecated
    public void c(boolean z) {
    }

    public boolean c() {
        Intent supportParentActivityIntent = getSupportParentActivityIntent();
        if (supportParentActivityIntent != null) {
            if (!a(supportParentActivityIntent)) {
                b(supportParentActivityIntent);
                return true;
            }
            TaskStackBuilder create = TaskStackBuilder.create(this);
            a(create);
            b(create);
            create.startActivities();
            try {
                ActivityCompat.finishAffinity(this);
                return true;
            } catch (IllegalStateException unused) {
                finish();
                return true;
            }
        }
        return false;
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        ActionBar b = b();
        if (getWindow().hasFeature(0)) {
            if (b == null || !b.v()) {
                super.closeOptionsMenu();
            }
        }
    }

    @Deprecated
    public void d() {
    }

    @Override // android.support.v4.app.SupportActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        ActionBar b = b();
        if (keyCode == 82 && b != null && b.a(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @af
    public e e() {
        if (this.a == null) {
            this.a = e.a(this, this);
        }
        return this.a;
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(@v int i) {
        return (T) e().a(i);
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        return e().b();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.c == null && ba.a()) {
            this.c = new ba(this, super.getResources());
        }
        Resources resources = this.c;
        return resources == null ? super.getResources() : resources;
    }

    @Override // android.support.v4.app.TaskStackBuilder.SupportParentable
    @ag
    public Intent getSupportParentActivityIntent() {
        return NavUtils.getParentActivityIntent(this);
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        e().f();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        e().a(configuration);
        if (this.c != null) {
            this.c.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        d();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(@ag Bundle bundle) {
        e e = e();
        e.i();
        e.a(bundle);
        if (e.k() && this.b != 0) {
            if (Build.VERSION.SDK_INT >= 23) {
                onApplyThemeResource(getTheme(), this.b, false);
            } else {
                setTheme(this.b);
            }
        }
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        e().g();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        ActionBar b = b();
        if (menuItem.getItemId() != 16908332 || b == null || (b.g() & 4) == 0) {
            return false;
        }
        return c();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    protected void onPostCreate(@ag Bundle bundle) {
        super.onPostCreate(bundle);
        e().b(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        e().e();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        e().c(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        e().c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        e().d();
    }

    @Override // android.app.Activity
    protected void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        e().a(charSequence);
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        ActionBar b = b();
        if (getWindow().hasFeature(0)) {
            if (b == null || !b.u()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // android.app.Activity
    public void setContentView(@aa int i) {
        e().b(i);
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        e().a(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        e().a(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(@ar int i) {
        super.setTheme(i);
        this.b = i;
    }

    @Override // android.support.v4.app.FragmentActivity
    public void supportInvalidateOptionsMenu() {
        e().f();
    }
}
