package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.v4.j.q;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class FragmentHostCallback<E> extends FragmentContainer {
    @ag
    private final Activity mActivity;
    @af
    private final Context mContext;
    final FragmentManagerImpl mFragmentManager;
    @af
    private final Handler mHandler;
    private final int mWindowAnimations;

    FragmentHostCallback(@ag Activity activity, @af Context context, @af Handler handler, int i) {
        this.mFragmentManager = new FragmentManagerImpl();
        this.mActivity = activity;
        this.mContext = (Context) q.a(context, "context == null");
        this.mHandler = (Handler) q.a(handler, "handler == null");
        this.mWindowAnimations = i;
    }

    public FragmentHostCallback(@af Context context, @af Handler handler, int i) {
        this(context instanceof Activity ? (Activity) context : null, context, handler, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentHostCallback(@af FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, fragmentActivity.mHandler, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @ag
    public Activity getActivity() {
        return this.mActivity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @af
    public Context getContext() {
        return this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentManagerImpl getFragmentManagerImpl() {
        return this.mFragmentManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @af
    public Handler getHandler() {
        return this.mHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onAttachFragment(Fragment fragment) {
    }

    public void onDump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // android.support.v4.app.FragmentContainer
    @ag
    public View onFindViewById(int i) {
        return null;
    }

    @ag
    public abstract E onGetHost();

    @af
    public LayoutInflater onGetLayoutInflater() {
        return LayoutInflater.from(this.mContext);
    }

    public int onGetWindowAnimations() {
        return this.mWindowAnimations;
    }

    @Override // android.support.v4.app.FragmentContainer
    public boolean onHasView() {
        return true;
    }

    public boolean onHasWindowAnimations() {
        return true;
    }

    public void onRequestPermissionsFromFragment(@af Fragment fragment, @af String[] strArr, int i) {
    }

    public boolean onShouldSaveFragmentState(Fragment fragment) {
        return true;
    }

    public boolean onShouldShowRequestPermissionRationale(@af String str) {
        return false;
    }

    public void onStartActivityFromFragment(Fragment fragment, Intent intent, int i) {
        onStartActivityFromFragment(fragment, intent, i, null);
    }

    public void onStartActivityFromFragment(Fragment fragment, Intent intent, int i, @ag Bundle bundle) {
        if (i != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        this.mContext.startActivity(intent);
    }

    public void onStartIntentSenderFromFragment(Fragment fragment, IntentSender intentSender, int i, @ag Intent intent, int i2, int i3, int i4, Bundle bundle) {
        if (i != -1) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        }
        ActivityCompat.startIntentSenderForResult(this.mActivity, intentSender, i, intent, i2, i3, i4, bundle);
    }

    public void onSupportInvalidateOptionsMenu() {
    }
}
