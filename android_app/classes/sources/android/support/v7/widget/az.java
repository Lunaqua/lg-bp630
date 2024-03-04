package android.support.v7.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class az {
    private static final String a = "TooltipPopup";
    private final Context b;
    private final View c;
    private final TextView d;
    private final WindowManager.LayoutParams e = new WindowManager.LayoutParams();
    private final Rect f = new Rect();
    private final int[] g = new int[2];
    private final int[] h = new int[2];

    /* JADX INFO: Access modifiers changed from: package-private */
    public az(Context context) {
        this.b = context;
        this.c = LayoutInflater.from(this.b).inflate(a.i.abc_tooltip, (ViewGroup) null);
        this.d = (TextView) this.c.findViewById(a.g.message);
        this.e.setTitle(getClass().getSimpleName());
        this.e.packageName = this.b.getPackageName();
        WindowManager.LayoutParams layoutParams = this.e;
        layoutParams.type = android.support.v4.view.w.d;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = a.k.Animation_AppCompat_Tooltip;
        this.e.flags = 24;
    }

    private static View a(View view) {
        View rootView = view.getRootView();
        ViewGroup.LayoutParams layoutParams = rootView.getLayoutParams();
        if ((layoutParams instanceof WindowManager.LayoutParams) && ((WindowManager.LayoutParams) layoutParams).type == 2) {
            return rootView;
        }
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return ((Activity) context).getWindow().getDecorView();
            }
        }
        return rootView;
    }

    private void a(View view, int i, int i2, boolean z, WindowManager.LayoutParams layoutParams) {
        int height;
        int i3;
        layoutParams.token = view.getApplicationWindowToken();
        int dimensionPixelOffset = this.b.getResources().getDimensionPixelOffset(a.e.tooltip_precise_anchor_threshold);
        if (view.getWidth() < dimensionPixelOffset) {
            i = view.getWidth() / 2;
        }
        if (view.getHeight() >= dimensionPixelOffset) {
            int dimensionPixelOffset2 = this.b.getResources().getDimensionPixelOffset(a.e.tooltip_precise_anchor_extra_offset);
            height = i2 + dimensionPixelOffset2;
            i3 = i2 - dimensionPixelOffset2;
        } else {
            height = view.getHeight();
            i3 = 0;
        }
        layoutParams.gravity = 49;
        int dimensionPixelOffset3 = this.b.getResources().getDimensionPixelOffset(z ? a.e.tooltip_y_offset_touch : a.e.tooltip_y_offset_non_touch);
        View a2 = a(view);
        if (a2 == null) {
            Log.e(a, "Cannot find app view");
            return;
        }
        a2.getWindowVisibleDisplayFrame(this.f);
        if (this.f.left < 0 && this.f.top < 0) {
            Resources resources = this.b.getResources();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
            int dimensionPixelSize = identifier != 0 ? resources.getDimensionPixelSize(identifier) : 0;
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            this.f.set(0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        a2.getLocationOnScreen(this.h);
        view.getLocationOnScreen(this.g);
        int[] iArr = this.g;
        int i4 = iArr[0];
        int[] iArr2 = this.h;
        iArr[0] = i4 - iArr2[0];
        iArr[1] = iArr[1] - iArr2[1];
        layoutParams.x = (iArr[0] + i) - (a2.getWidth() / 2);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.c.measure(makeMeasureSpec, makeMeasureSpec);
        int measuredHeight = this.c.getMeasuredHeight();
        int[] iArr3 = this.g;
        int i5 = ((iArr3[1] + i3) - dimensionPixelOffset3) - measuredHeight;
        int i6 = iArr3[1] + height + dimensionPixelOffset3;
        if (!z ? measuredHeight + i6 <= this.f.height() : i5 < 0) {
            layoutParams.y = i5;
        } else {
            layoutParams.y = i6;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        if (b()) {
            ((WindowManager) this.b.getSystemService("window")).removeView(this.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(View view, int i, int i2, boolean z, CharSequence charSequence) {
        if (b()) {
            a();
        }
        this.d.setText(charSequence);
        a(view, i, i2, z, this.e);
        ((WindowManager) this.b.getSystemService("window")).addView(this.c, this.e);
    }

    boolean b() {
        return this.c.getParent() != null;
    }
}
