package android.arch.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.arch.lifecycle.d;
import android.os.Bundle;
import android.support.annotation.an;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class p extends Fragment {
    private static final String a = "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag";
    private a b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        void a();

        void b();

        void c();
    }

    public static void a(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag(a) == null) {
            fragmentManager.beginTransaction().add(new p(), a).commit();
            fragmentManager.executePendingTransactions();
        }
    }

    private void a(d.a aVar) {
        Activity activity = getActivity();
        if (activity instanceof h) {
            ((h) activity).a().a(aVar);
        } else if (activity instanceof f) {
            d lifecycle = ((f) activity).getLifecycle();
            if (lifecycle instanceof g) {
                ((g) lifecycle).a(aVar);
            }
        }
    }

    static p b(Activity activity) {
        return (p) activity.getFragmentManager().findFragmentByTag(a);
    }

    private void b(a aVar) {
        if (aVar != null) {
            aVar.a();
        }
    }

    private void c(a aVar) {
        if (aVar != null) {
            aVar.b();
        }
    }

    private void d(a aVar) {
        if (aVar != null) {
            aVar.c();
        }
    }

    void a(a aVar) {
        this.b = aVar;
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        b(this.b);
        a(d.a.ON_CREATE);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        a(d.a.ON_DESTROY);
        this.b = null;
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        a(d.a.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        d(this.b);
        a(d.a.ON_RESUME);
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        c(this.b);
        a(d.a.ON_START);
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        a(d.a.ON_STOP);
    }
}
