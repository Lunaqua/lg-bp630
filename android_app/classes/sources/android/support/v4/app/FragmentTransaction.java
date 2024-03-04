package android.support.v4.app;

import android.support.annotation.a;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.aq;
import android.support.annotation.ar;
import android.support.annotation.b;
import android.support.annotation.v;
import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class FragmentTransaction {
    public static final int TRANSIT_ENTER_MASK = 4096;
    public static final int TRANSIT_EXIT_MASK = 8192;
    public static final int TRANSIT_FRAGMENT_CLOSE = 8194;
    public static final int TRANSIT_FRAGMENT_FADE = 4099;
    public static final int TRANSIT_FRAGMENT_OPEN = 4097;
    public static final int TRANSIT_NONE = 0;
    public static final int TRANSIT_UNSET = -1;

    @af
    public abstract FragmentTransaction add(@v int i, @af Fragment fragment);

    @af
    public abstract FragmentTransaction add(@v int i, @af Fragment fragment, @ag String str);

    @af
    public abstract FragmentTransaction add(@af Fragment fragment, @ag String str);

    @af
    public abstract FragmentTransaction addSharedElement(@af View view, @af String str);

    @af
    public abstract FragmentTransaction addToBackStack(@ag String str);

    @af
    public abstract FragmentTransaction attach(@af Fragment fragment);

    public abstract int commit();

    public abstract int commitAllowingStateLoss();

    public abstract void commitNow();

    public abstract void commitNowAllowingStateLoss();

    @af
    public abstract FragmentTransaction detach(@af Fragment fragment);

    @af
    public abstract FragmentTransaction disallowAddToBackStack();

    @af
    public abstract FragmentTransaction hide(@af Fragment fragment);

    public abstract boolean isAddToBackStackAllowed();

    public abstract boolean isEmpty();

    @af
    public abstract FragmentTransaction remove(@af Fragment fragment);

    @af
    public abstract FragmentTransaction replace(@v int i, @af Fragment fragment);

    @af
    public abstract FragmentTransaction replace(@v int i, @af Fragment fragment, @ag String str);

    @af
    public abstract FragmentTransaction runOnCommit(@af Runnable runnable);

    @Deprecated
    public abstract FragmentTransaction setAllowOptimization(boolean z);

    @af
    public abstract FragmentTransaction setBreadCrumbShortTitle(@aq int i);

    @af
    public abstract FragmentTransaction setBreadCrumbShortTitle(@ag CharSequence charSequence);

    @af
    public abstract FragmentTransaction setBreadCrumbTitle(@aq int i);

    @af
    public abstract FragmentTransaction setBreadCrumbTitle(@ag CharSequence charSequence);

    @af
    public abstract FragmentTransaction setCustomAnimations(@a @b int i, @a @b int i2);

    @af
    public abstract FragmentTransaction setCustomAnimations(@a @b int i, @a @b int i2, @a @b int i3, @a @b int i4);

    @af
    public abstract FragmentTransaction setPrimaryNavigationFragment(@ag Fragment fragment);

    @af
    public abstract FragmentTransaction setReorderingAllowed(boolean z);

    @af
    public abstract FragmentTransaction setTransition(int i);

    @af
    public abstract FragmentTransaction setTransitionStyle(@ar int i);

    @af
    public abstract FragmentTransaction show(@af Fragment fragment);
}
