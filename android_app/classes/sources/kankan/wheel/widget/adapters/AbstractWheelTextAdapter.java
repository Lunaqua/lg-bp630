package kankan.wheel.widget.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class AbstractWheelTextAdapter extends AbstractWheelAdapter {
    public static final int a = -1;
    protected static final int b = 0;
    public static final int c = -15724528;
    public static final int d = -9437072;
    public static final int e = 36;
    protected Context f;
    protected LayoutInflater g;
    protected int h;
    protected int i;
    protected int j;
    private int k;
    private int l;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractWheelTextAdapter(Context context) {
        this(context, -1);
    }

    protected AbstractWheelTextAdapter(Context context, int i) {
        this(context, i, 0);
    }

    protected AbstractWheelTextAdapter(Context context, int i, int i2) {
        this.k = c;
        this.l = 36;
        this.f = context;
        this.h = i;
        this.i = i2;
        this.g = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    private View a(int i, ViewGroup viewGroup) {
        if (i != -1) {
            if (i != 0) {
                return this.g.inflate(i, viewGroup, false);
            }
            return null;
        }
        return new TextViewEx(this.f);
    }

    private TextViewEx a(View view, int i) {
        if (i == 0) {
            try {
                if (view instanceof TextViewEx) {
                    return (TextViewEx) view;
                }
            } catch (ClassCastException e2) {
                Log.e("AbstractWheelAdapter", "You must supply a resource ID for a TextView");
                throw new IllegalStateException("AbstractWheelAdapter requires the resource ID to be a TextView", e2);
            }
        }
        return null;
    }

    @Override // kankan.wheel.widget.adapters.WheelViewAdapter
    public View a(int i, View view, ViewGroup viewGroup) {
        if (i < 0 || i >= i()) {
            return null;
        }
        if (view == null) {
            view = a(this.h, viewGroup);
        }
        TextViewEx a2 = a(view, this.i);
        if (a2 != null) {
            f(i);
            a2.setText(" ");
            if (this.h == -1) {
                a(a2);
            }
        }
        return view;
    }

    @Override // kankan.wheel.widget.adapters.AbstractWheelAdapter, kankan.wheel.widget.adapters.WheelViewAdapter
    public View a(View view, ViewGroup viewGroup) {
        if (view == null) {
            view = a(this.j, viewGroup);
        }
        if (this.j == -1 && (view instanceof TextView)) {
            a((TextView) view);
        }
        return view;
    }

    public void a(int i) {
        this.k = i;
    }

    protected void a(TextView textView) {
        textView.setTextColor(this.k);
        textView.setGravity(17);
        textView.setTextSize(this.l);
        textView.setLines(1);
        textView.setTypeface(Typeface.SANS_SERIF, 1);
    }

    public void b(int i) {
        this.l = i;
    }

    public int c() {
        return this.k;
    }

    public void c(int i) {
        this.h = i;
    }

    public int d() {
        return this.l;
    }

    public void d(int i) {
        this.i = i;
    }

    public int e() {
        return this.h;
    }

    public void e(int i) {
        this.j = i;
    }

    public int f() {
        return this.i;
    }

    protected abstract CharSequence f(int i);

    public int g() {
        return this.j;
    }
}
