package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.a.a;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.Field;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class t extends ListView {
    public static final int a = -1;
    public static final int b = -1;
    b c;
    private final Rect d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private Field j;
    private a k;
    private boolean l;
    private boolean m;
    private boolean n;
    private android.support.v4.view.af o;
    private android.support.v4.widget.n p;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a extends android.support.v7.c.a.c {
        private boolean a;

        a(Drawable drawable) {
            super(drawable);
            this.a = true;
        }

        void a(boolean z) {
            this.a = z;
        }

        @Override // android.support.v7.c.a.c, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.a) {
                super.draw(canvas);
            }
        }

        @Override // android.support.v7.c.a.c, android.graphics.drawable.Drawable
        public void setHotspot(float f, float f2) {
            if (this.a) {
                super.setHotspot(f, f2);
            }
        }

        @Override // android.support.v7.c.a.c, android.graphics.drawable.Drawable
        public void setHotspotBounds(int i, int i2, int i3, int i4) {
            if (this.a) {
                super.setHotspotBounds(i, i2, i3, i4);
            }
        }

        @Override // android.support.v7.c.a.c, android.graphics.drawable.Drawable
        public boolean setState(int[] iArr) {
            if (this.a) {
                return super.setState(iArr);
            }
            return false;
        }

        @Override // android.support.v7.c.a.c, android.graphics.drawable.Drawable
        public boolean setVisible(boolean z, boolean z2) {
            if (this.a) {
                return super.setVisible(z, z2);
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class b implements Runnable {
        b() {
        }

        public void a() {
            t tVar = t.this;
            tVar.c = null;
            tVar.removeCallbacks(this);
        }

        public void b() {
            t.this.post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            t tVar = t.this;
            tVar.c = null;
            tVar.drawableStateChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public t(Context context, boolean z) {
        super(context, null, a.b.dropDownListViewStyle);
        this.d = new Rect();
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.m = z;
        setCacheColorHint(0);
        try {
            this.j = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.j.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void a() {
        Drawable selector = getSelector();
        if (selector != null && c() && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    private void a(int i, View view) {
        Drawable selector = getSelector();
        boolean z = (selector == null || i == -1) ? false : true;
        if (z) {
            selector.setVisible(false, false);
        }
        b(i, view);
        if (z) {
            Rect rect = this.d;
            float exactCenterX = rect.exactCenterX();
            float exactCenterY = rect.exactCenterY();
            selector.setVisible(getVisibility() == 0, false);
            android.support.v4.graphics.drawable.a.a(selector, exactCenterX, exactCenterY);
        }
    }

    private void a(int i, View view, float f, float f2) {
        a(i, view);
        Drawable selector = getSelector();
        if (selector == null || i == -1) {
            return;
        }
        android.support.v4.graphics.drawable.a.a(selector, f, f2);
    }

    private void a(Canvas canvas) {
        Drawable selector;
        if (this.d.isEmpty() || (selector = getSelector()) == null) {
            return;
        }
        selector.setBounds(this.d);
        selector.draw(canvas);
    }

    private void a(View view, int i) {
        performItemClick(view, i, getItemIdAtPosition(i));
    }

    private void a(View view, int i, float f, float f2) {
        View childAt;
        this.n = true;
        if (Build.VERSION.SDK_INT >= 21) {
            drawableHotspotChanged(f, f2);
        }
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        int i2 = this.i;
        if (i2 != -1 && (childAt = getChildAt(i2 - getFirstVisiblePosition())) != null && childAt != view && childAt.isPressed()) {
            childAt.setPressed(false);
        }
        this.i = i;
        float left = f - view.getLeft();
        float top = f2 - view.getTop();
        if (Build.VERSION.SDK_INT >= 21) {
            view.drawableHotspotChanged(left, top);
        }
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        a(i, view, f, f2);
        setSelectorEnabled(false);
        refreshDrawableState();
    }

    private void b() {
        this.n = false;
        setPressed(false);
        drawableStateChanged();
        View childAt = getChildAt(this.i - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setPressed(false);
        }
        android.support.v4.view.af afVar = this.o;
        if (afVar != null) {
            afVar.d();
            this.o = null;
        }
    }

    private void b(int i, View view) {
        Rect rect = this.d;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        rect.left -= this.e;
        rect.top -= this.f;
        rect.right += this.g;
        rect.bottom += this.h;
        try {
            boolean z = this.j.getBoolean(this);
            if (view.isEnabled() != z) {
                this.j.set(this, Boolean.valueOf(!z));
                if (i != -1) {
                    refreshDrawableState();
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private boolean c() {
        return this.n;
    }

    private void setSelectorEnabled(boolean z) {
        a aVar = this.k;
        if (aVar != null) {
            aVar.a(z);
        }
    }

    public int a(int i, int i2, int i3, int i4, int i5) {
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        getListPaddingLeft();
        getListPaddingRight();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        int i6 = listPaddingTop + listPaddingBottom;
        dividerHeight = (dividerHeight <= 0 || divider == null) ? 0 : 0;
        int count = adapter.getCount();
        int i7 = i6;
        View view = null;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i8 < count) {
            int itemViewType = adapter.getItemViewType(i8);
            if (itemViewType != i9) {
                view = null;
                i9 = itemViewType;
            }
            view = adapter.getView(i8, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            view.measure(i, layoutParams.height > 0 ? View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0));
            view.forceLayout();
            if (i8 > 0) {
                i7 += dividerHeight;
            }
            i7 += view.getMeasuredHeight();
            if (i7 >= i4) {
                return (i5 < 0 || i8 <= i5 || i10 <= 0 || i7 == i4) ? i4 : i10;
            }
            if (i5 >= 0 && i8 >= i5) {
                i10 = i7;
            }
            i8++;
        }
        return i7;
    }

    public int a(int i, boolean z) {
        int min;
        ListAdapter adapter = getAdapter();
        if (adapter != null && !isInTouchMode()) {
            int count = adapter.getCount();
            if (!getAdapter().areAllItemsEnabled()) {
                if (z) {
                    min = Math.max(0, i);
                    while (min < count && !adapter.isEnabled(min)) {
                        min++;
                    }
                } else {
                    min = Math.min(i, count - 1);
                    while (min >= 0 && !adapter.isEnabled(min)) {
                        min--;
                    }
                }
                if (min < 0 || min >= count) {
                    return -1;
                }
                return min;
            } else if (i >= 0 && i < count) {
                return i;
            }
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000c, code lost:
        if (r0 != 3) goto L7;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0048 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(android.view.MotionEvent r8, int r9) {
        /*
            r7 = this;
            int r0 = r8.getActionMasked()
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L16
            r3 = 2
            if (r0 == r3) goto L14
            r9 = 3
            if (r0 == r9) goto L11
        Le:
            r9 = 0
            r3 = 1
            goto L46
        L11:
            r9 = 0
            r3 = 0
            goto L46
        L14:
            r3 = 1
            goto L17
        L16:
            r3 = 0
        L17:
            int r9 = r8.findPointerIndex(r9)
            if (r9 >= 0) goto L1e
            goto L11
        L1e:
            float r4 = r8.getX(r9)
            int r4 = (int) r4
            float r9 = r8.getY(r9)
            int r9 = (int) r9
            int r5 = r7.pointToPosition(r4, r9)
            r6 = -1
            if (r5 != r6) goto L31
            r9 = 1
            goto L46
        L31:
            int r3 = r7.getFirstVisiblePosition()
            int r3 = r5 - r3
            android.view.View r3 = r7.getChildAt(r3)
            float r4 = (float) r4
            float r9 = (float) r9
            r7.a(r3, r5, r4, r9)
            if (r0 != r2) goto Le
            r7.a(r3, r5)
            goto Le
        L46:
            if (r3 == 0) goto L4a
            if (r9 == 0) goto L4d
        L4a:
            r7.b()
        L4d:
            if (r3 == 0) goto L65
            android.support.v4.widget.n r9 = r7.p
            if (r9 != 0) goto L5a
            android.support.v4.widget.n r9 = new android.support.v4.widget.n
            r9.<init>(r7)
            r7.p = r9
        L5a:
            android.support.v4.widget.n r9 = r7.p
            r9.a(r2)
            android.support.v4.widget.n r9 = r7.p
            r9.onTouch(r7, r8)
            goto L6c
        L65:
            android.support.v4.widget.n r8 = r7.p
            if (r8 == 0) goto L6c
            r8.a(r1)
        L6c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.t.a(android.view.MotionEvent, int):boolean");
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        a(canvas);
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        if (this.c != null) {
            return;
        }
        super.drawableStateChanged();
        setSelectorEnabled(true);
        a();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        return this.m || super.hasFocus();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.m || super.hasWindowFocus();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.m || super.isFocused();
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        return (this.m && this.l) || super.isInTouchMode();
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.c = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(@android.support.annotation.af MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.c == null) {
            this.c = new b();
            this.c.b();
        }
        boolean onHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (pointToPosition != -1 && pointToPosition != getSelectedItemPosition()) {
                View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    setSelectionFromTop(pointToPosition, childAt.getTop() - getTop());
                }
                a();
            }
        } else {
            setSelection(-1);
        }
        return onHoverEvent;
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.i = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        b bVar = this.c;
        if (bVar != null) {
            bVar.a();
        }
        return super.onTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setListSelectionHidden(boolean z) {
        this.l = z;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        this.k = drawable != null ? new a(drawable) : null;
        super.setSelector(this.k);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.e = rect.left;
        this.f = rect.top;
        this.g = rect.right;
        this.h = rect.bottom;
    }
}
