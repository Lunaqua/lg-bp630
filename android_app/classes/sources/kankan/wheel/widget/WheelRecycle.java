package kankan.wheel.widget;

import android.view.View;
import android.widget.LinearLayout;
import java.util.LinkedList;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class WheelRecycle {
    private List<View> a;
    private List<View> b;
    private WheelView c;

    public WheelRecycle(WheelView wheelView) {
        this.c = wheelView;
    }

    private View a(List<View> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        View view = list.get(0);
        list.remove(0);
        return view;
    }

    private List<View> a(View view, List<View> list) {
        if (list == null) {
            list = new LinkedList<>();
        }
        list.add(view);
        return list;
    }

    private void a(View view, int i) {
        int i2 = this.c.getViewAdapter().i();
        if ((i < 0 || i >= i2) && !this.c.c()) {
            this.b = a(view, this.b);
            return;
        }
        while (i < 0) {
            i += i2;
        }
        int i3 = i % i2;
        this.a = a(view, this.a);
    }

    public int a(LinearLayout linearLayout, int i, ItemsRange itemsRange) {
        int i2 = 0;
        int i3 = i;
        while (i2 < linearLayout.getChildCount()) {
            if (itemsRange.a(i3)) {
                i2++;
            } else {
                a(linearLayout.getChildAt(i2), i3);
                linearLayout.removeViewAt(i2);
                if (i2 == 0) {
                    i++;
                }
            }
            i3++;
        }
        return i;
    }

    public View a() {
        return a(this.a);
    }

    public View b() {
        return a(this.b);
    }

    public void c() {
        List<View> list = this.a;
        if (list != null) {
            list.clear();
        }
        List<View> list2 = this.b;
        if (list2 != null) {
            list2.clear();
        }
    }
}
