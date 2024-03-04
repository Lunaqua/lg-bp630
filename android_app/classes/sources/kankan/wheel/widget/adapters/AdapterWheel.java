package kankan.wheel.widget.adapters;

import android.content.Context;
import kankan.wheel.widget.WheelAdapter;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class AdapterWheel extends AbstractWheelTextAdapter {
    private WheelAdapter k;

    public AdapterWheel(Context context, WheelAdapter wheelAdapter) {
        super(context);
        this.k = wheelAdapter;
    }

    @Override // kankan.wheel.widget.adapters.AbstractWheelTextAdapter
    protected CharSequence f(int i) {
        return this.k.a(i);
    }

    public WheelAdapter h() {
        return this.k;
    }

    @Override // kankan.wheel.widget.adapters.WheelViewAdapter
    public int i() {
        return this.k.a();
    }
}
