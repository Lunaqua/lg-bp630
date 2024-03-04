package android.support.v4.view;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class v {
    public static final int POSITION_NONE = -2;
    public static final int POSITION_UNCHANGED = -1;
    private final DataSetObservable mObservable = new DataSetObservable();
    private DataSetObserver mViewPagerObserver;

    @Deprecated
    public void destroyItem(@android.support.annotation.af View view, int i, @android.support.annotation.af Object obj) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void destroyItem(@android.support.annotation.af ViewGroup viewGroup, int i, @android.support.annotation.af Object obj) {
        destroyItem((View) viewGroup, i, obj);
    }

    @Deprecated
    public void finishUpdate(@android.support.annotation.af View view) {
    }

    public void finishUpdate(@android.support.annotation.af ViewGroup viewGroup) {
        finishUpdate((View) viewGroup);
    }

    public abstract int getCount();

    public int getItemPosition(@android.support.annotation.af Object obj) {
        return -1;
    }

    @android.support.annotation.ag
    public CharSequence getPageTitle(int i) {
        return null;
    }

    public float getPageWidth(int i) {
        return 1.0f;
    }

    @android.support.annotation.af
    @Deprecated
    public Object instantiateItem(@android.support.annotation.af View view, int i) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    @android.support.annotation.af
    public Object instantiateItem(@android.support.annotation.af ViewGroup viewGroup, int i) {
        return instantiateItem((View) viewGroup, i);
    }

    public abstract boolean isViewFromObject(@android.support.annotation.af View view, @android.support.annotation.af Object obj);

    public void notifyDataSetChanged() {
        synchronized (this) {
            if (this.mViewPagerObserver != null) {
                this.mViewPagerObserver.onChanged();
            }
        }
        this.mObservable.notifyChanged();
    }

    public void registerDataSetObserver(@android.support.annotation.af DataSetObserver dataSetObserver) {
        this.mObservable.registerObserver(dataSetObserver);
    }

    public void restoreState(@android.support.annotation.ag Parcelable parcelable, @android.support.annotation.ag ClassLoader classLoader) {
    }

    @android.support.annotation.ag
    public Parcelable saveState() {
        return null;
    }

    @Deprecated
    public void setPrimaryItem(@android.support.annotation.af View view, int i, @android.support.annotation.af Object obj) {
    }

    public void setPrimaryItem(@android.support.annotation.af ViewGroup viewGroup, int i, @android.support.annotation.af Object obj) {
        setPrimaryItem((View) viewGroup, i, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setViewPagerObserver(DataSetObserver dataSetObserver) {
        synchronized (this) {
            this.mViewPagerObserver = dataSetObserver;
        }
    }

    @Deprecated
    public void startUpdate(@android.support.annotation.af View view) {
    }

    public void startUpdate(@android.support.annotation.af ViewGroup viewGroup) {
        startUpdate((View) viewGroup);
    }

    public void unregisterDataSetObserver(@android.support.annotation.af DataSetObserver dataSetObserver) {
        this.mObservable.unregisterObserver(dataSetObserver);
    }
}
