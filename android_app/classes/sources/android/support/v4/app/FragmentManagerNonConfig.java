package android.support.v4.app;

import android.arch.lifecycle.t;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class FragmentManagerNonConfig {
    private final List<FragmentManagerNonConfig> mChildNonConfigs;
    private final List<Fragment> mFragments;
    private final List<t> mViewModelStores;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentManagerNonConfig(List<Fragment> list, List<FragmentManagerNonConfig> list2, List<t> list3) {
        this.mFragments = list;
        this.mChildNonConfigs = list2;
        this.mViewModelStores = list3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<FragmentManagerNonConfig> getChildNonConfigs() {
        return this.mChildNonConfigs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Fragment> getFragments() {
        return this.mFragments;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<t> getViewModelStores() {
        return this.mViewModelStores;
    }
}
