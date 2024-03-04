package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.annotation.aq;
import android.support.annotation.v;
import android.support.v4.app.Fragment;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class FragmentManager {
    public static final int POP_BACK_STACK_INCLUSIVE = 1;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface BackStackEntry {
        @ag
        CharSequence getBreadCrumbShortTitle();

        @aq
        int getBreadCrumbShortTitleRes();

        @ag
        CharSequence getBreadCrumbTitle();

        @aq
        int getBreadCrumbTitleRes();

        int getId();

        @ag
        String getName();
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class FragmentLifecycleCallbacks {
        public void onFragmentActivityCreated(@af FragmentManager fragmentManager, @af Fragment fragment, @ag Bundle bundle) {
        }

        public void onFragmentAttached(@af FragmentManager fragmentManager, @af Fragment fragment, @af Context context) {
        }

        public void onFragmentCreated(@af FragmentManager fragmentManager, @af Fragment fragment, @ag Bundle bundle) {
        }

        public void onFragmentDestroyed(@af FragmentManager fragmentManager, @af Fragment fragment) {
        }

        public void onFragmentDetached(@af FragmentManager fragmentManager, @af Fragment fragment) {
        }

        public void onFragmentPaused(@af FragmentManager fragmentManager, @af Fragment fragment) {
        }

        public void onFragmentPreAttached(@af FragmentManager fragmentManager, @af Fragment fragment, @af Context context) {
        }

        public void onFragmentPreCreated(@af FragmentManager fragmentManager, @af Fragment fragment, @ag Bundle bundle) {
        }

        public void onFragmentResumed(@af FragmentManager fragmentManager, @af Fragment fragment) {
        }

        public void onFragmentSaveInstanceState(@af FragmentManager fragmentManager, @af Fragment fragment, @af Bundle bundle) {
        }

        public void onFragmentStarted(@af FragmentManager fragmentManager, @af Fragment fragment) {
        }

        public void onFragmentStopped(@af FragmentManager fragmentManager, @af Fragment fragment) {
        }

        public void onFragmentViewCreated(@af FragmentManager fragmentManager, @af Fragment fragment, @af View view, @ag Bundle bundle) {
        }

        public void onFragmentViewDestroyed(@af FragmentManager fragmentManager, @af Fragment fragment) {
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface OnBackStackChangedListener {
        void onBackStackChanged();
    }

    public static void enableDebugLogging(boolean z) {
        FragmentManagerImpl.DEBUG = z;
    }

    public abstract void addOnBackStackChangedListener(@af OnBackStackChangedListener onBackStackChangedListener);

    @af
    public abstract FragmentTransaction beginTransaction();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract boolean executePendingTransactions();

    @ag
    public abstract Fragment findFragmentById(@v int i);

    @ag
    public abstract Fragment findFragmentByTag(@ag String str);

    @af
    public abstract BackStackEntry getBackStackEntryAt(int i);

    public abstract int getBackStackEntryCount();

    @ag
    public abstract Fragment getFragment(@af Bundle bundle, @af String str);

    @af
    public abstract List<Fragment> getFragments();

    @ag
    public abstract Fragment getPrimaryNavigationFragment();

    public abstract boolean isDestroyed();

    public abstract boolean isStateSaved();

    @an(a = {an.a.LIBRARY_GROUP})
    @Deprecated
    public FragmentTransaction openTransaction() {
        return beginTransaction();
    }

    public abstract void popBackStack();

    public abstract void popBackStack(int i, int i2);

    public abstract void popBackStack(@ag String str, int i);

    public abstract boolean popBackStackImmediate();

    public abstract boolean popBackStackImmediate(int i, int i2);

    public abstract boolean popBackStackImmediate(@ag String str, int i);

    public abstract void putFragment(@af Bundle bundle, @af String str, @af Fragment fragment);

    public abstract void registerFragmentLifecycleCallbacks(@af FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z);

    public abstract void removeOnBackStackChangedListener(@af OnBackStackChangedListener onBackStackChangedListener);

    @ag
    public abstract Fragment.SavedState saveFragmentInstanceState(Fragment fragment);

    public abstract void unregisterFragmentLifecycleCallbacks(@af FragmentLifecycleCallbacks fragmentLifecycleCallbacks);
}
