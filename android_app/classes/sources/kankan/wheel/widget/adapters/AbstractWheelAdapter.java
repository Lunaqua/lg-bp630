package kankan.wheel.widget.adapters;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedList;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class AbstractWheelAdapter implements WheelViewAdapter {
    private List<DataSetObserver> a;

    @Override // kankan.wheel.widget.adapters.WheelViewAdapter
    public View a(View view, ViewGroup viewGroup) {
        return null;
    }

    protected void a() {
        List<DataSetObserver> list = this.a;
        if (list != null) {
            for (DataSetObserver dataSetObserver : list) {
                dataSetObserver.onChanged();
            }
        }
    }

    @Override // kankan.wheel.widget.adapters.WheelViewAdapter
    public void a(DataSetObserver dataSetObserver) {
        if (this.a == null) {
            this.a = new LinkedList();
        }
        this.a.add(dataSetObserver);
    }

    protected void b() {
        List<DataSetObserver> list = this.a;
        if (list != null) {
            for (DataSetObserver dataSetObserver : list) {
                dataSetObserver.onInvalidated();
            }
        }
    }

    @Override // kankan.wheel.widget.adapters.WheelViewAdapter
    public void b(DataSetObserver dataSetObserver) {
        List<DataSetObserver> list = this.a;
        if (list != null) {
            list.remove(dataSetObserver);
        }
    }
}
