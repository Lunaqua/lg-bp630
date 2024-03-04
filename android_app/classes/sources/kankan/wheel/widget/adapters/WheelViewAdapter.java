package kankan.wheel.widget.adapters;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface WheelViewAdapter {
    View a(int i, View view, ViewGroup viewGroup);

    View a(View view, ViewGroup viewGroup);

    void a(DataSetObserver dataSetObserver);

    void b(DataSetObserver dataSetObserver);

    int i();
}
