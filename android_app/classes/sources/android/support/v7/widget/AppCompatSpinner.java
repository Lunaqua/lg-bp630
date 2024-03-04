package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class AppCompatSpinner extends Spinner implements android.support.v4.view.z {
    private static final int[] d = {16843505};
    private static final int e = 15;
    private static final String f = "AppCompatSpinner";
    private static final int g = 0;
    private static final int h = 1;
    private static final int i = -1;
    b a;
    int b;
    final Rect c;
    private final e j;
    private final Context k;
    private w l;
    private SpinnerAdapter m;
    private final boolean n;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter a;
        private ListAdapter b;

        public a(@android.support.annotation.ag SpinnerAdapter spinnerAdapter, @android.support.annotation.ag Resources.Theme theme) {
            this.a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.b = (ListAdapter) spinnerAdapter;
            }
            if (theme != null) {
                if (Build.VERSION.SDK_INT >= 23 && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                    ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                    if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                        themedSpinnerAdapter.setDropDownViewTheme(theme);
                    }
                } else if (spinnerAdapter instanceof ar) {
                    ar arVar = (ar) spinnerAdapter;
                    if (arVar.a() == null) {
                        arVar.a(theme);
                    }
                }
            }
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.b;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        @Override // android.widget.SpinnerAdapter
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter == null) {
                return -1L;
            }
            return spinnerAdapter.getItemId(i);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int i) {
            return 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            return getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.a;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            return getCount() == 0;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.b;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i);
            }
            return true;
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class b extends ListPopupWindow {
        ListAdapter a;
        private CharSequence p;
        private final Rect q;

        public b(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.q = new Rect();
            b(AppCompatSpinner.this);
            a(true);
            a(0);
            a(new AdapterView.OnItemClickListener() { // from class: android.support.v7.widget.AppCompatSpinner.b.1
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                    AppCompatSpinner.this.setSelection(i2);
                    if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                        AppCompatSpinner.this.performItemClick(view, i2, b.this.a.getItemId(i2));
                    }
                    b.this.d();
                }
            });
        }

        @Override // android.support.v7.widget.ListPopupWindow, android.support.v7.view.menu.t
        public void a() {
            ViewTreeObserver viewTreeObserver;
            boolean e = e();
            c();
            k(2);
            super.a();
            g().setChoiceMode(1);
            l(AppCompatSpinner.this.getSelectedItemPosition());
            if (e || (viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver()) == null) {
                return;
            }
            final ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: android.support.v7.widget.AppCompatSpinner.b.2
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    b bVar = b.this;
                    if (!bVar.a(AppCompatSpinner.this)) {
                        b.this.d();
                        return;
                    }
                    b.this.c();
                    b.super.a();
                }
            };
            viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener);
            a(new PopupWindow.OnDismissListener() { // from class: android.support.v7.widget.AppCompatSpinner.b.3
                @Override // android.widget.PopupWindow.OnDismissListener
                public void onDismiss() {
                    ViewTreeObserver viewTreeObserver2 = AppCompatSpinner.this.getViewTreeObserver();
                    if (viewTreeObserver2 != null) {
                        viewTreeObserver2.removeGlobalOnLayoutListener(onGlobalLayoutListener);
                    }
                }
            });
        }

        @Override // android.support.v7.widget.ListPopupWindow
        public void a(ListAdapter listAdapter) {
            super.a(listAdapter);
            this.a = listAdapter;
        }

        public void a(CharSequence charSequence) {
            this.p = charSequence;
        }

        boolean a(View view) {
            return android.support.v4.view.ab.af(view) && view.getGlobalVisibleRect(this.q);
        }

        public CharSequence b() {
            return this.p;
        }

        void c() {
            int i;
            Drawable k = k();
            int i2 = 0;
            if (k != null) {
                k.getPadding(AppCompatSpinner.this.c);
                i2 = bd.a(AppCompatSpinner.this) ? AppCompatSpinner.this.c.right : -AppCompatSpinner.this.c.left;
            } else {
                Rect rect = AppCompatSpinner.this.c;
                AppCompatSpinner.this.c.right = 0;
                rect.left = 0;
            }
            int paddingLeft = AppCompatSpinner.this.getPaddingLeft();
            int paddingRight = AppCompatSpinner.this.getPaddingRight();
            int width = AppCompatSpinner.this.getWidth();
            if (AppCompatSpinner.this.b == -2) {
                int a = AppCompatSpinner.this.a((SpinnerAdapter) this.a, k());
                int i3 = (AppCompatSpinner.this.getContext().getResources().getDisplayMetrics().widthPixels - AppCompatSpinner.this.c.left) - AppCompatSpinner.this.c.right;
                if (a > i3) {
                    a = i3;
                }
                i = Math.max(a, (width - paddingLeft) - paddingRight);
            } else {
                i = AppCompatSpinner.this.b == -1 ? (width - paddingLeft) - paddingRight : AppCompatSpinner.this.b;
            }
            h(i);
            d(bd.a(AppCompatSpinner.this) ? i2 + ((width - paddingRight) - p()) : i2 + paddingLeft);
        }
    }

    public AppCompatSpinner(Context context) {
        this(context, (AttributeSet) null);
    }

    public AppCompatSpinner(Context context, int i2) {
        this(context, null, a.b.spinnerStyle, i2);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.b.spinnerStyle);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, -1);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i2, int i3) {
        this(context, attributeSet, i2, i3, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0054, code lost:
        if (r12 != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0056, code lost:
        r12.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0068, code lost:
        if (r12 == null) goto L9;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00cb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AppCompatSpinner(android.content.Context r8, android.util.AttributeSet r9, int r10, int r11, android.content.res.Resources.Theme r12) {
        /*
            r7 = this;
            r7.<init>(r8, r9, r10)
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r7.c = r0
            int[] r0 = android.support.v7.a.a.l.Spinner
            r1 = 0
            android.support.v7.widget.av r0 = android.support.v7.widget.av.a(r8, r9, r0, r10, r1)
            android.support.v7.widget.e r2 = new android.support.v7.widget.e
            r2.<init>(r7)
            r7.j = r2
            r2 = 0
            if (r12 == 0) goto L23
            android.support.v7.view.d r3 = new android.support.v7.view.d
            r3.<init>(r8, r12)
        L20:
            r7.k = r3
            goto L3c
        L23:
            int r12 = android.support.v7.a.a.l.Spinner_popupTheme
            int r12 = r0.g(r12, r1)
            if (r12 == 0) goto L31
            android.support.v7.view.d r3 = new android.support.v7.view.d
            r3.<init>(r8, r12)
            goto L20
        L31:
            int r12 = android.os.Build.VERSION.SDK_INT
            r3 = 23
            if (r12 >= r3) goto L39
            r12 = r8
            goto L3a
        L39:
            r12 = r2
        L3a:
            r7.k = r12
        L3c:
            android.content.Context r12 = r7.k
            r3 = 1
            if (r12 == 0) goto Laa
            r12 = -1
            if (r11 != r12) goto L72
            int[] r12 = android.support.v7.widget.AppCompatSpinner.d     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5f
            android.content.res.TypedArray r12 = r8.obtainStyledAttributes(r9, r12, r10, r1)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5f
            boolean r4 = r12.hasValue(r1)     // Catch: java.lang.Exception -> L5a java.lang.Throwable -> L6b
            if (r4 == 0) goto L54
            int r11 = r12.getInt(r1, r1)     // Catch: java.lang.Exception -> L5a java.lang.Throwable -> L6b
        L54:
            if (r12 == 0) goto L72
        L56:
            r12.recycle()
            goto L72
        L5a:
            r4 = move-exception
            goto L61
        L5c:
            r8 = move-exception
            r12 = r2
            goto L6c
        L5f:
            r4 = move-exception
            r12 = r2
        L61:
            java.lang.String r5 = "AppCompatSpinner"
            java.lang.String r6 = "Could not read android:spinnerMode"
            android.util.Log.i(r5, r6, r4)     // Catch: java.lang.Throwable -> L6b
            if (r12 == 0) goto L72
            goto L56
        L6b:
            r8 = move-exception
        L6c:
            if (r12 == 0) goto L71
            r12.recycle()
        L71:
            throw r8
        L72:
            if (r11 != r3) goto Laa
            android.support.v7.widget.AppCompatSpinner$b r11 = new android.support.v7.widget.AppCompatSpinner$b
            android.content.Context r12 = r7.k
            r11.<init>(r12, r9, r10)
            android.content.Context r12 = r7.k
            int[] r4 = android.support.v7.a.a.l.Spinner
            android.support.v7.widget.av r12 = android.support.v7.widget.av.a(r12, r9, r4, r10, r1)
            int r1 = android.support.v7.a.a.l.Spinner_android_dropDownWidth
            r4 = -2
            int r1 = r12.f(r1, r4)
            r7.b = r1
            int r1 = android.support.v7.a.a.l.Spinner_android_popupBackground
            android.graphics.drawable.Drawable r1 = r12.a(r1)
            r11.b(r1)
            int r1 = android.support.v7.a.a.l.Spinner_android_prompt
            java.lang.String r1 = r0.e(r1)
            r11.a(r1)
            r12.e()
            r7.a = r11
            android.support.v7.widget.AppCompatSpinner$1 r12 = new android.support.v7.widget.AppCompatSpinner$1
            r12.<init>(r7)
            r7.l = r12
        Laa:
            int r11 = android.support.v7.a.a.l.Spinner_android_entries
            java.lang.CharSequence[] r11 = r0.h(r11)
            if (r11 == 0) goto Lc2
            android.widget.ArrayAdapter r12 = new android.widget.ArrayAdapter
            r1 = 17367048(0x1090008, float:2.5162948E-38)
            r12.<init>(r8, r1, r11)
            int r8 = android.support.v7.a.a.i.support_simple_spinner_dropdown_item
            r12.setDropDownViewResource(r8)
            r7.setAdapter(r12)
        Lc2:
            r0.e()
            r7.n = r3
            android.widget.SpinnerAdapter r8 = r7.m
            if (r8 == 0) goto Ld0
            r7.setAdapter(r8)
            r7.m = r2
        Ld0:
            android.support.v7.widget.e r8 = r7.j
            r8.a(r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatSpinner.<init>(android.content.Context, android.util.AttributeSet, int, int, android.content.res.Resources$Theme):void");
    }

    int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i2 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        View view = null;
        int i3 = 0;
        for (int max2 = Math.max(0, max - (15 - (min - max))); max2 < min; max2++) {
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != i2) {
                view = null;
                i2 = itemViewType;
            }
            view = spinnerAdapter.getView(max2, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i3 = Math.max(i3, view.getMeasuredWidth());
        }
        if (drawable != null) {
            drawable.getPadding(this.c);
            return i3 + this.c.left + this.c.right;
        }
        return i3;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.j;
        if (eVar != null) {
            eVar.c();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        b bVar = this.a;
        if (bVar != null) {
            return bVar.n();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownHorizontalOffset();
        }
        return 0;
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        b bVar = this.a;
        if (bVar != null) {
            return bVar.o();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownVerticalOffset();
        }
        return 0;
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        if (this.a != null) {
            return this.b;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownWidth();
        }
        return 0;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        b bVar = this.a;
        if (bVar != null) {
            return bVar.k();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getPopupBackground();
        }
        return null;
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        if (this.a != null) {
            return this.k;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return super.getPopupContext();
        }
        return null;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        b bVar = this.a;
        return bVar != null ? bVar.b() : super.getPrompt();
    }

    @Override // android.support.v4.view.z
    @android.support.annotation.ag
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.j;
        if (eVar != null) {
            return eVar.a();
        }
        return null;
    }

    @Override // android.support.v4.view.z
    @android.support.annotation.ag
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.j;
        if (eVar != null) {
            return eVar.b();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b bVar = this.a;
        if (bVar == null || !bVar.e()) {
            return;
        }
        this.a.d();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.a == null || View.MeasureSpec.getMode(i2) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i2)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        w wVar = this.l;
        if (wVar == null || !wVar.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean performClick() {
        b bVar = this.a;
        if (bVar != null) {
            if (bVar.e()) {
                return true;
            }
            this.a.a();
            return true;
        }
        return super.performClick();
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.n) {
            this.m = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.a != null) {
            Context context = this.k;
            if (context == null) {
                context = getContext();
            }
            this.a.a(new a(spinnerAdapter, context.getTheme()));
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.j;
        if (eVar != null) {
            eVar.a(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(@android.support.annotation.p int i2) {
        super.setBackgroundResource(i2);
        e eVar = this.j;
        if (eVar != null) {
            eVar.a(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i2) {
        b bVar = this.a;
        if (bVar != null) {
            bVar.d(i2);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownHorizontalOffset(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i2) {
        b bVar = this.a;
        if (bVar != null) {
            bVar.e(i2);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownVerticalOffset(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i2) {
        if (this.a != null) {
            this.b = i2;
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownWidth(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        b bVar = this.a;
        if (bVar != null) {
            bVar.b(drawable);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(@android.support.annotation.p int i2) {
        setPopupBackgroundDrawable(android.support.v7.b.a.a.b(getPopupContext(), i2));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        b bVar = this.a;
        if (bVar != null) {
            bVar.a(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    @Override // android.support.v4.view.z
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setSupportBackgroundTintList(@android.support.annotation.ag ColorStateList colorStateList) {
        e eVar = this.j;
        if (eVar != null) {
            eVar.a(colorStateList);
        }
    }

    @Override // android.support.v4.view.z
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setSupportBackgroundTintMode(@android.support.annotation.ag PorterDuff.Mode mode) {
        e eVar = this.j;
        if (eVar != null) {
            eVar.a(mode);
        }
    }
}
