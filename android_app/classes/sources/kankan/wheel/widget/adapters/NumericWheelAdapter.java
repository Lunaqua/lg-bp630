package kankan.wheel.widget.adapters;

import android.content.Context;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class NumericWheelAdapter extends AbstractWheelTextAdapter {
    public static final int k = 9;
    private static final int l = 0;
    private int m;
    private int n;
    private String o;

    public NumericWheelAdapter(Context context) {
        this(context, 0, 9);
    }

    public NumericWheelAdapter(Context context, int i, int i2) {
        this(context, i, i2, null);
    }

    public NumericWheelAdapter(Context context, int i, int i2, String str) {
        super(context);
        this.m = i;
        this.n = i2;
        this.o = str;
    }

    @Override // kankan.wheel.widget.adapters.AbstractWheelTextAdapter
    public CharSequence f(int i) {
        if (i < 0 || i >= i()) {
            return null;
        }
        int i2 = this.m + i;
        String str = this.o;
        return str != null ? String.format(str, Integer.valueOf(i2)) : Integer.toString(i2);
    }

    @Override // kankan.wheel.widget.adapters.WheelViewAdapter
    public int i() {
        return (this.n - this.m) + 1;
    }
}
