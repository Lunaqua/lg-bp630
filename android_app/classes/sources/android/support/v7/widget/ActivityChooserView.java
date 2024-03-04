package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.support.v7.widget.c;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ActivityChooserView extends ViewGroup implements c.a {
    private static final String i = "ActivityChooserView";
    final a a;
    final FrameLayout b;
    final FrameLayout c;
    android.support.v4.view.b d;
    final DataSetObserver e;
    PopupWindow.OnDismissListener f;
    boolean g;
    int h;
    private final b j;
    private final View k;
    private final Drawable l;
    private final ImageView m;
    private final ImageView n;
    private final int o;
    private final ViewTreeObserver.OnGlobalLayoutListener p;
    private ListPopupWindow q;
    private boolean r;
    private int s;

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class InnerLayout extends LinearLayout {
        private static final int[] a = {16842964};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            av a2 = av.a(context, attributeSet, a);
            setBackgroundDrawable(a2.a(0));
            a2.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class a extends BaseAdapter {
        public static final int a = Integer.MAX_VALUE;
        public static final int b = 4;
        private static final int d = 0;
        private static final int e = 1;
        private static final int f = 3;
        private c g;
        private int h = 4;
        private boolean i;
        private boolean j;
        private boolean k;

        a() {
        }

        public int a() {
            int i = this.h;
            this.h = a;
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
            int count = getCount();
            View view = null;
            int i2 = 0;
            for (int i3 = 0; i3 < count; i3++) {
                view = getView(i3, view, null);
                view.measure(makeMeasureSpec, makeMeasureSpec2);
                i2 = Math.max(i2, view.getMeasuredWidth());
            }
            this.h = i;
            return i2;
        }

        public void a(int i) {
            if (this.h != i) {
                this.h = i;
                notifyDataSetChanged();
            }
        }

        public void a(c cVar) {
            c e2 = ActivityChooserView.this.a.e();
            if (e2 != null && ActivityChooserView.this.isShown()) {
                e2.unregisterObserver(ActivityChooserView.this.e);
            }
            this.g = cVar;
            if (cVar != null && ActivityChooserView.this.isShown()) {
                cVar.registerObserver(ActivityChooserView.this.e);
            }
            notifyDataSetChanged();
        }

        public void a(boolean z) {
            if (this.k != z) {
                this.k = z;
                notifyDataSetChanged();
            }
        }

        public void a(boolean z, boolean z2) {
            if (this.i == z && this.j == z2) {
                return;
            }
            this.i = z;
            this.j = z2;
            notifyDataSetChanged();
        }

        public ResolveInfo b() {
            return this.g.c();
        }

        public int c() {
            return this.g.b();
        }

        public int d() {
            return this.g.e();
        }

        public c e() {
            return this.g;
        }

        public boolean f() {
            return this.i;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int b2 = this.g.b();
            if (!this.i && this.g.c() != null) {
                b2--;
            }
            int min = Math.min(b2, this.h);
            return this.k ? min + 1 : min;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            int itemViewType = getItemViewType(i);
            if (itemViewType != 0) {
                if (itemViewType == 1) {
                    return null;
                }
                throw new IllegalArgumentException();
            }
            if (!this.i && this.g.c() != null) {
                i++;
            }
            return this.g.a(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i) {
            return (this.k && i == getCount() - 1) ? 1 : 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            int itemViewType = getItemViewType(i);
            if (itemViewType != 0) {
                if (itemViewType == 1) {
                    if (view == null || view.getId() != 1) {
                        View inflate = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(a.i.abc_activity_chooser_view_list_item, viewGroup, false);
                        inflate.setId(1);
                        ((TextView) inflate.findViewById(a.g.title)).setText(ActivityChooserView.this.getContext().getString(a.j.abc_activity_chooser_view_see_all));
                        return inflate;
                    }
                    return view;
                }
                throw new IllegalArgumentException();
            }
            if (view == null || view.getId() != a.g.list_item) {
                view = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(a.i.abc_activity_chooser_view_list_item, viewGroup, false);
            }
            PackageManager packageManager = ActivityChooserView.this.getContext().getPackageManager();
            ResolveInfo resolveInfo = (ResolveInfo) getItem(i);
            ((ImageView) view.findViewById(a.g.icon)).setImageDrawable(resolveInfo.loadIcon(packageManager));
            ((TextView) view.findViewById(a.g.title)).setText(resolveInfo.loadLabel(packageManager));
            if (this.i && i == 0 && this.j) {
                view.setActivated(true);
            } else {
                view.setActivated(false);
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class b implements View.OnClickListener, View.OnLongClickListener, AdapterView.OnItemClickListener, PopupWindow.OnDismissListener {
        b() {
        }

        private void a() {
            if (ActivityChooserView.this.f != null) {
                ActivityChooserView.this.f.onDismiss();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view != ActivityChooserView.this.c) {
                if (view != ActivityChooserView.this.b) {
                    throw new IllegalArgumentException();
                }
                ActivityChooserView activityChooserView = ActivityChooserView.this;
                activityChooserView.g = false;
                activityChooserView.a(activityChooserView.h);
                return;
            }
            ActivityChooserView.this.b();
            Intent b = ActivityChooserView.this.a.e().b(ActivityChooserView.this.a.e().a(ActivityChooserView.this.a.b()));
            if (b != null) {
                b.addFlags(524288);
                ActivityChooserView.this.getContext().startActivity(b);
            }
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            a();
            if (ActivityChooserView.this.d != null) {
                ActivityChooserView.this.d.a(false);
            }
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            int itemViewType = ((a) adapterView.getAdapter()).getItemViewType(i);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    throw new IllegalArgumentException();
                }
                ActivityChooserView.this.a(a.a);
                return;
            }
            ActivityChooserView.this.b();
            if (ActivityChooserView.this.g) {
                if (i > 0) {
                    ActivityChooserView.this.a.e().c(i);
                    return;
                }
                return;
            }
            if (!ActivityChooserView.this.a.f()) {
                i++;
            }
            Intent b = ActivityChooserView.this.a.e().b(i);
            if (b != null) {
                b.addFlags(524288);
                ActivityChooserView.this.getContext().startActivity(b);
            }
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (view == ActivityChooserView.this.c) {
                if (ActivityChooserView.this.a.getCount() > 0) {
                    ActivityChooserView activityChooserView = ActivityChooserView.this;
                    activityChooserView.g = true;
                    activityChooserView.a(activityChooserView.h);
                }
                return true;
            }
            throw new IllegalArgumentException();
        }
    }

    public ActivityChooserView(Context context) {
        this(context, null);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.e = new DataSetObserver() { // from class: android.support.v7.widget.ActivityChooserView.1
            @Override // android.database.DataSetObserver
            public void onChanged() {
                super.onChanged();
                ActivityChooserView.this.a.notifyDataSetChanged();
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                super.onInvalidated();
                ActivityChooserView.this.a.notifyDataSetInvalidated();
            }
        };
        this.p = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: android.support.v7.widget.ActivityChooserView.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (ActivityChooserView.this.c()) {
                    if (!ActivityChooserView.this.isShown()) {
                        ActivityChooserView.this.getListPopupWindow().d();
                        return;
                    }
                    ActivityChooserView.this.getListPopupWindow().a();
                    if (ActivityChooserView.this.d != null) {
                        ActivityChooserView.this.d.a(true);
                    }
                }
            }
        };
        this.h = 4;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.l.ActivityChooserView, i2, 0);
        this.h = obtainStyledAttributes.getInt(a.l.ActivityChooserView_initialActivityCount, 4);
        Drawable drawable = obtainStyledAttributes.getDrawable(a.l.ActivityChooserView_expandActivityOverflowButtonDrawable);
        obtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(a.i.abc_activity_chooser_view, (ViewGroup) this, true);
        this.j = new b();
        this.k = findViewById(a.g.activity_chooser_view_content);
        this.l = this.k.getBackground();
        this.c = (FrameLayout) findViewById(a.g.default_activity_button);
        this.c.setOnClickListener(this.j);
        this.c.setOnLongClickListener(this.j);
        this.n = (ImageView) this.c.findViewById(a.g.image);
        FrameLayout frameLayout = (FrameLayout) findViewById(a.g.expand_activities_button);
        frameLayout.setOnClickListener(this.j);
        frameLayout.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: android.support.v7.widget.ActivityChooserView.3
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                android.support.v4.view.a.c.a(accessibilityNodeInfo).p(true);
            }
        });
        frameLayout.setOnTouchListener(new w(frameLayout) { // from class: android.support.v7.widget.ActivityChooserView.4
            @Override // android.support.v7.widget.w
            public android.support.v7.view.menu.t a() {
                return ActivityChooserView.this.getListPopupWindow();
            }

            @Override // android.support.v7.widget.w
            protected boolean b() {
                ActivityChooserView.this.a();
                return true;
            }

            @Override // android.support.v7.widget.w
            protected boolean c() {
                ActivityChooserView.this.b();
                return true;
            }
        });
        this.b = frameLayout;
        this.m = (ImageView) frameLayout.findViewById(a.g.image);
        this.m.setImageDrawable(drawable);
        this.a = new a();
        this.a.registerDataSetObserver(new DataSetObserver() { // from class: android.support.v7.widget.ActivityChooserView.5
            @Override // android.database.DataSetObserver
            public void onChanged() {
                super.onChanged();
                ActivityChooserView.this.d();
            }
        });
        Resources resources = context.getResources();
        this.o = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(a.e.abc_config_prefDialogWidth));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7, types: [boolean, int] */
    void a(int i2) {
        a aVar;
        if (this.a.e() == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.p);
        ?? r0 = this.c.getVisibility() == 0 ? 1 : 0;
        int c = this.a.c();
        if (i2 == Integer.MAX_VALUE || c <= i2 + r0) {
            this.a.a(false);
            aVar = this.a;
        } else {
            this.a.a(true);
            aVar = this.a;
            i2--;
        }
        aVar.a(i2);
        ListPopupWindow listPopupWindow = getListPopupWindow();
        if (listPopupWindow.e()) {
            return;
        }
        if (this.g || r0 == 0) {
            this.a.a(true, r0);
        } else {
            this.a.a(false, false);
        }
        listPopupWindow.h(Math.min(this.a.a(), this.o));
        listPopupWindow.a();
        android.support.v4.view.b bVar = this.d;
        if (bVar != null) {
            bVar.a(true);
        }
        listPopupWindow.g().setContentDescription(getContext().getString(a.j.abc_activitychooserview_choose_application));
        listPopupWindow.g().setSelector(new ColorDrawable(0));
    }

    public boolean a() {
        if (c() || !this.r) {
            return false;
        }
        this.g = false;
        a(this.h);
        return true;
    }

    public boolean b() {
        if (c()) {
            getListPopupWindow().d();
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeGlobalOnLayoutListener(this.p);
                return true;
            }
            return true;
        }
        return true;
    }

    public boolean c() {
        return getListPopupWindow().e();
    }

    void d() {
        View view;
        Drawable drawable;
        if (this.a.getCount() > 0) {
            this.b.setEnabled(true);
        } else {
            this.b.setEnabled(false);
        }
        int c = this.a.c();
        int d = this.a.d();
        if (c == 1 || (c > 1 && d > 0)) {
            this.c.setVisibility(0);
            ResolveInfo b2 = this.a.b();
            PackageManager packageManager = getContext().getPackageManager();
            this.n.setImageDrawable(b2.loadIcon(packageManager));
            if (this.s != 0) {
                this.c.setContentDescription(getContext().getString(this.s, b2.loadLabel(packageManager)));
            }
        } else {
            this.c.setVisibility(8);
        }
        if (this.c.getVisibility() == 0) {
            view = this.k;
            drawable = this.l;
        } else {
            view = this.k;
            drawable = null;
        }
        view.setBackgroundDrawable(drawable);
    }

    public c getDataModel() {
        return this.a.e();
    }

    ListPopupWindow getListPopupWindow() {
        if (this.q == null) {
            this.q = new ListPopupWindow(getContext());
            this.q.a(this.a);
            this.q.b(this);
            this.q.a(true);
            this.q.a((AdapterView.OnItemClickListener) this.j);
            this.q.a((PopupWindow.OnDismissListener) this.j);
        }
        return this.q;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c e = this.a.e();
        if (e != null) {
            e.registerObserver(this.e);
        }
        this.r = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c e = this.a.e();
        if (e != null) {
            e.unregisterObserver(this.e);
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.p);
        }
        if (c()) {
            b();
        }
        this.r = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        this.k.layout(0, 0, i4 - i2, i5 - i3);
        if (c()) {
            return;
        }
        b();
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        View view = this.k;
        if (this.c.getVisibility() != 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i3), 1073741824);
        }
        measureChild(view, i2, i3);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    @Override // android.support.v7.widget.c.a
    public void setActivityChooserModel(c cVar) {
        this.a.a(cVar);
        if (c()) {
            b();
            a();
        }
    }

    public void setDefaultActionButtonContentDescription(int i2) {
        this.s = i2;
    }

    public void setExpandActivityOverflowButtonContentDescription(int i2) {
        this.m.setContentDescription(getContext().getString(i2));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.m.setImageDrawable(drawable);
    }

    public void setInitialActivityCount(int i2) {
        this.h = i2;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.f = onDismissListener;
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setProvider(android.support.v4.view.b bVar) {
        this.d = bVar;
    }
}
