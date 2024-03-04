package android.support.v4.app;

import android.os.Parcelable;
import android.support.annotation.af;
import android.support.v4.view.v;
import android.view.View;
import android.view.ViewGroup;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class FragmentPagerAdapter extends v {
    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentPagerAdapter";
    private FragmentTransaction mCurTransaction = null;
    private Fragment mCurrentPrimaryItem = null;
    private final FragmentManager mFragmentManager;

    public FragmentPagerAdapter(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    private static String makeFragmentName(int i, long j) {
        return "android:switcher:" + i + ":" + j;
    }

    @Override // android.support.v4.view.v
    public void destroyItem(@af ViewGroup viewGroup, int i, @af Object obj) {
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        this.mCurTransaction.detach((Fragment) obj);
    }

    @Override // android.support.v4.view.v
    public void finishUpdate(@af ViewGroup viewGroup) {
        FragmentTransaction fragmentTransaction = this.mCurTransaction;
        if (fragmentTransaction != null) {
            fragmentTransaction.commitNowAllowingStateLoss();
            this.mCurTransaction = null;
        }
    }

    public abstract Fragment getItem(int i);

    public long getItemId(int i) {
        return i;
    }

    @Override // android.support.v4.view.v
    @af
    public Object instantiateItem(@af ViewGroup viewGroup, int i) {
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        long itemId = getItemId(i);
        Fragment findFragmentByTag = this.mFragmentManager.findFragmentByTag(makeFragmentName(viewGroup.getId(), itemId));
        if (findFragmentByTag != null) {
            this.mCurTransaction.attach(findFragmentByTag);
        } else {
            findFragmentByTag = getItem(i);
            this.mCurTransaction.add(viewGroup.getId(), findFragmentByTag, makeFragmentName(viewGroup.getId(), itemId));
        }
        if (findFragmentByTag != this.mCurrentPrimaryItem) {
            findFragmentByTag.setMenuVisibility(false);
            findFragmentByTag.setUserVisibleHint(false);
        }
        return findFragmentByTag;
    }

    @Override // android.support.v4.view.v
    public boolean isViewFromObject(@af View view, @af Object obj) {
        return ((Fragment) obj).getView() == view;
    }

    @Override // android.support.v4.view.v
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    @Override // android.support.v4.view.v
    public Parcelable saveState() {
        return null;
    }

    @Override // android.support.v4.view.v
    public void setPrimaryItem(@af ViewGroup viewGroup, int i, @af Object obj) {
        Fragment fragment = (Fragment) obj;
        Fragment fragment2 = this.mCurrentPrimaryItem;
        if (fragment != fragment2) {
            if (fragment2 != null) {
                fragment2.setMenuVisibility(false);
                this.mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            fragment.setMenuVisibility(true);
            fragment.setUserVisibleHint(true);
            this.mCurrentPrimaryItem = fragment;
        }
    }

    @Override // android.support.v4.view.v
    public void startUpdate(@af ViewGroup viewGroup) {
        if (viewGroup.getId() != -1) {
            return;
        }
        throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
    }
}
