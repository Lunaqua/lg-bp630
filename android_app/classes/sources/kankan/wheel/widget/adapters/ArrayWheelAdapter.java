package kankan.wheel.widget.adapters;

import android.content.Context;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ArrayWheelAdapter<T> extends AbstractWheelTextAdapter {
    private T[] k;

    public ArrayWheelAdapter(Context context, T[] tArr) {
        super(context);
        this.k = tArr;
    }

    @Override // kankan.wheel.widget.adapters.AbstractWheelTextAdapter
    public CharSequence f(int i) {
        if (i >= 0) {
            T[] tArr = this.k;
            if (i < tArr.length) {
                T t = tArr[i];
                return t instanceof CharSequence ? (CharSequence) t : t.toString();
            }
            return null;
        }
        return null;
    }

    @Override // kankan.wheel.widget.adapters.WheelViewAdapter
    public int i() {
        return this.k.length;
    }
}
