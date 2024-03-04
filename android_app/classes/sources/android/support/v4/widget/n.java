package android.support.v4.widget;

import android.support.annotation.af;
import android.widget.ListView;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class n extends a {
    private final ListView l;

    public n(@af ListView listView) {
        super(listView);
        this.l = listView;
    }

    @Override // android.support.v4.widget.a
    public void a(int i, int i2) {
        o.a(this.l, i2);
    }

    @Override // android.support.v4.widget.a
    public boolean e(int i) {
        return false;
    }

    @Override // android.support.v4.widget.a
    public boolean f(int i) {
        ListView listView = this.l;
        int count = listView.getCount();
        if (count == 0) {
            return false;
        }
        int childCount = listView.getChildCount();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int i2 = firstVisiblePosition + childCount;
        if (i > 0) {
            if (i2 >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                return false;
            }
        } else if (i >= 0) {
            return false;
        } else {
            if (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }
}
