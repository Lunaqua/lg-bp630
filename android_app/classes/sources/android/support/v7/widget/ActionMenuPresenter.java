package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.b;
import android.support.v7.a.a;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.p;
import android.support.v7.view.menu.q;
import android.support.v7.widget.ActionMenuView;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ActionMenuPresenter extends android.support.v7.view.menu.b implements b.a {
    private static final String m = "ActionMenuPresenter";
    private View A;
    private b B;
    d g;
    e h;
    a i;
    c j;
    final f k;
    int l;
    private Drawable n;
    private boolean o;
    private boolean p;
    private boolean q;
    private int r;
    private int s;
    private int t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private int y;
    private final SparseBooleanArray z;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: android.support.v7.widget.ActionMenuPresenter.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        public int a;

        SavedState() {
        }

        SavedState(Parcel parcel) {
            this.a = parcel.readInt();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class a extends android.support.v7.view.menu.o {
        public a(Context context, android.support.v7.view.menu.v vVar, View view) {
            super(context, vVar, view, false, a.b.actionOverflowMenuStyle);
            if (!((android.support.v7.view.menu.k) vVar.getItem()).k()) {
                a(ActionMenuPresenter.this.g == null ? (View) ActionMenuPresenter.this.f : ActionMenuPresenter.this.g);
            }
            a(ActionMenuPresenter.this.k);
        }

        @Override // android.support.v7.view.menu.o
        protected void f() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            actionMenuPresenter.i = null;
            actionMenuPresenter.l = 0;
            super.f();
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class b extends ActionMenuItemView.b {
        b() {
        }

        @Override // android.support.v7.view.menu.ActionMenuItemView.b
        public android.support.v7.view.menu.t a() {
            if (ActionMenuPresenter.this.i != null) {
                return ActionMenuPresenter.this.i.d();
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class c implements Runnable {
        private e b;

        public c(e eVar) {
            this.b = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ActionMenuPresenter.this.c != null) {
                ActionMenuPresenter.this.c.h();
            }
            View view = (View) ActionMenuPresenter.this.f;
            if (view != null && view.getWindowToken() != null && this.b.e()) {
                ActionMenuPresenter.this.h = this.b;
            }
            ActionMenuPresenter.this.j = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class d extends AppCompatImageView implements ActionMenuView.a {
        private final float[] b;

        public d(Context context) {
            super(context, null, a.b.actionOverflowButtonStyle);
            this.b = new float[2];
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            ax.a(this, getContentDescription());
            setOnTouchListener(new w(this) { // from class: android.support.v7.widget.ActionMenuPresenter.d.1
                @Override // android.support.v7.widget.w
                public android.support.v7.view.menu.t a() {
                    if (ActionMenuPresenter.this.h == null) {
                        return null;
                    }
                    return ActionMenuPresenter.this.h.d();
                }

                @Override // android.support.v7.widget.w
                public boolean b() {
                    ActionMenuPresenter.this.e();
                    return true;
                }

                @Override // android.support.v7.widget.w
                public boolean c() {
                    if (ActionMenuPresenter.this.j != null) {
                        return false;
                    }
                    ActionMenuPresenter.this.g();
                    return true;
                }
            });
        }

        @Override // android.support.v7.widget.ActionMenuView.a
        public boolean d() {
            return false;
        }

        @Override // android.support.v7.widget.ActionMenuView.a
        public boolean e() {
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.e();
            return true;
        }

        @Override // android.widget.ImageView
        protected boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int max = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                android.support.v4.graphics.drawable.a.a(background, paddingLeft - max, paddingTop - max, paddingLeft + max, paddingTop + max);
            }
            return frame;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class e extends android.support.v7.view.menu.o {
        public e(Context context, android.support.v7.view.menu.h hVar, View view, boolean z) {
            super(context, hVar, view, z, a.b.actionOverflowMenuStyle);
            a(android.support.v4.view.f.c);
            a(ActionMenuPresenter.this.k);
        }

        @Override // android.support.v7.view.menu.o
        protected void f() {
            if (ActionMenuPresenter.this.c != null) {
                ActionMenuPresenter.this.c.close();
            }
            ActionMenuPresenter.this.h = null;
            super.f();
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class f implements p.a {
        f() {
        }

        @Override // android.support.v7.view.menu.p.a
        public void a(android.support.v7.view.menu.h hVar, boolean z) {
            if (hVar instanceof android.support.v7.view.menu.v) {
                hVar.r().b(false);
            }
            p.a a = ActionMenuPresenter.this.a();
            if (a != null) {
                a.a(hVar, z);
            }
        }

        @Override // android.support.v7.view.menu.p.a
        public boolean a(android.support.v7.view.menu.h hVar) {
            if (hVar == null) {
                return false;
            }
            ActionMenuPresenter.this.l = ((android.support.v7.view.menu.v) hVar).getItem().getItemId();
            p.a a = ActionMenuPresenter.this.a();
            if (a != null) {
                return a.a(hVar);
            }
            return false;
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, a.i.abc_action_menu_layout, a.i.abc_action_menu_item_layout);
        this.z = new SparseBooleanArray();
        this.k = new f();
    }

    private View a(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.f;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof q.a) && ((q.a) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    @Override // android.support.v7.view.menu.b, android.support.v7.view.menu.p
    public android.support.v7.view.menu.q a(ViewGroup viewGroup) {
        android.support.v7.view.menu.q qVar = this.f;
        android.support.v7.view.menu.q a2 = super.a(viewGroup);
        if (qVar != a2) {
            ((ActionMenuView) a2).setPresenter(this);
        }
        return a2;
    }

    @Override // android.support.v7.view.menu.b
    public View a(android.support.v7.view.menu.k kVar, View view, ViewGroup viewGroup) {
        View actionView = kVar.getActionView();
        if (actionView == null || kVar.o()) {
            actionView = super.a(kVar, view, viewGroup);
        }
        actionView.setVisibility(kVar.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    public void a(int i, boolean z) {
        this.r = i;
        this.v = z;
        this.w = true;
    }

    @Override // android.support.v7.view.menu.b, android.support.v7.view.menu.p
    public void a(@android.support.annotation.af Context context, @android.support.annotation.ag android.support.v7.view.menu.h hVar) {
        super.a(context, hVar);
        Resources resources = context.getResources();
        android.support.v7.view.a a2 = android.support.v7.view.a.a(context);
        if (!this.q) {
            this.p = a2.b();
        }
        if (!this.w) {
            this.r = a2.c();
        }
        if (!this.u) {
            this.t = a2.a();
        }
        int i = this.r;
        if (this.p) {
            if (this.g == null) {
                this.g = new d(this.a);
                if (this.o) {
                    this.g.setImageDrawable(this.n);
                    this.n = null;
                    this.o = false;
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.g.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i -= this.g.getMeasuredWidth();
        } else {
            this.g = null;
        }
        this.s = i;
        this.y = (int) (resources.getDisplayMetrics().density * 56.0f);
        this.A = null;
    }

    public void a(Configuration configuration) {
        if (!this.u) {
            this.t = android.support.v7.view.a.a(this.b).a();
        }
        if (this.c != null) {
            this.c.c(true);
        }
    }

    public void a(Drawable drawable) {
        d dVar = this.g;
        if (dVar != null) {
            dVar.setImageDrawable(drawable);
            return;
        }
        this.o = true;
        this.n = drawable;
    }

    @Override // android.support.v7.view.menu.p
    public void a(Parcelable parcelable) {
        MenuItem findItem;
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            if (savedState.a <= 0 || (findItem = this.c.findItem(savedState.a)) == null) {
                return;
            }
            a((android.support.v7.view.menu.v) findItem.getSubMenu());
        }
    }

    @Override // android.support.v7.view.menu.b, android.support.v7.view.menu.p
    public void a(android.support.v7.view.menu.h hVar, boolean z) {
        h();
        super.a(hVar, z);
    }

    @Override // android.support.v7.view.menu.b
    public void a(android.support.v7.view.menu.k kVar, q.a aVar) {
        aVar.a(kVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.f);
        if (this.B == null) {
            this.B = new b();
        }
        actionMenuItemView.setPopupCallback(this.B);
    }

    public void a(ActionMenuView actionMenuView) {
        this.f = actionMenuView;
        actionMenuView.a(this.c);
    }

    @Override // android.support.v4.view.b.a
    public void a(boolean z) {
        if (z) {
            super.a((android.support.v7.view.menu.v) null);
        } else if (this.c != null) {
            this.c.b(false);
        }
    }

    @Override // android.support.v7.view.menu.b
    public boolean a(int i, android.support.v7.view.menu.k kVar) {
        return kVar.k();
    }

    @Override // android.support.v7.view.menu.b, android.support.v7.view.menu.p
    public boolean a(android.support.v7.view.menu.v vVar) {
        boolean z = false;
        if (vVar.hasVisibleItems()) {
            android.support.v7.view.menu.v vVar2 = vVar;
            while (vVar2.u() != this.c) {
                vVar2 = (android.support.v7.view.menu.v) vVar2.u();
            }
            View a2 = a(vVar2.getItem());
            if (a2 == null) {
                return false;
            }
            this.l = vVar.getItem().getItemId();
            int size = vVar.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                MenuItem item = vVar.getItem(i);
                if (item.isVisible() && item.getIcon() != null) {
                    z = true;
                    break;
                }
                i++;
            }
            this.i = new a(this.b, vVar, a2);
            this.i.a(z);
            this.i.c();
            super.a(vVar);
            return true;
        }
        return false;
    }

    @Override // android.support.v7.view.menu.b
    public boolean a(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.g) {
            return false;
        }
        return super.a(viewGroup, i);
    }

    public void b(int i) {
        this.t = i;
        this.u = true;
    }

    @Override // android.support.v7.view.menu.b, android.support.v7.view.menu.p
    public void b(boolean z) {
        super.b(z);
        ((View) this.f).requestLayout();
        boolean z2 = false;
        if (this.c != null) {
            ArrayList<android.support.v7.view.menu.k> m2 = this.c.m();
            int size = m2.size();
            for (int i = 0; i < size; i++) {
                android.support.v4.view.b a2 = m2.get(i).a();
                if (a2 != null) {
                    a2.a(this);
                }
            }
        }
        ArrayList<android.support.v7.view.menu.k> n = this.c != null ? this.c.n() : null;
        if (this.p && n != null) {
            int size2 = n.size();
            if (size2 == 1) {
                z2 = !n.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z2 = true;
            }
        }
        if (z2) {
            if (this.g == null) {
                this.g = new d(this.a);
            }
            ViewGroup viewGroup = (ViewGroup) this.g.getParent();
            if (viewGroup != this.f) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.g);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f;
                actionMenuView.addView(this.g, actionMenuView.c());
            }
        } else {
            d dVar = this.g;
            if (dVar != null && dVar.getParent() == this.f) {
                ((ViewGroup) this.f).removeView(this.g);
            }
        }
        ((ActionMenuView) this.f).setOverflowReserved(this.p);
    }

    @Override // android.support.v7.view.menu.b, android.support.v7.view.menu.p
    public boolean b() {
        ArrayList<android.support.v7.view.menu.k> arrayList;
        int i;
        int i2;
        int i3;
        int i4;
        boolean z;
        ActionMenuPresenter actionMenuPresenter = this;
        int i5 = 0;
        if (actionMenuPresenter.c != null) {
            arrayList = actionMenuPresenter.c.k();
            i = arrayList.size();
        } else {
            arrayList = null;
            i = 0;
        }
        int i6 = actionMenuPresenter.t;
        int i7 = actionMenuPresenter.s;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) actionMenuPresenter.f;
        int i8 = i6;
        boolean z2 = false;
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < i; i11++) {
            android.support.v7.view.menu.k kVar = arrayList.get(i11);
            if (kVar.m()) {
                i9++;
            } else if (kVar.l()) {
                i10++;
            } else {
                z2 = true;
            }
            if (actionMenuPresenter.x && kVar.isActionViewExpanded()) {
                i8 = 0;
            }
        }
        if (actionMenuPresenter.p && (z2 || i10 + i9 > i8)) {
            i8--;
        }
        int i12 = i8 - i9;
        SparseBooleanArray sparseBooleanArray = actionMenuPresenter.z;
        sparseBooleanArray.clear();
        if (actionMenuPresenter.v) {
            int i13 = actionMenuPresenter.y;
            i3 = i7 / i13;
            i2 = i13 + ((i7 % i13) / i3);
        } else {
            i2 = 0;
            i3 = 0;
        }
        int i14 = i7;
        int i15 = 0;
        int i16 = 0;
        while (i15 < i) {
            android.support.v7.view.menu.k kVar2 = arrayList.get(i15);
            if (kVar2.m()) {
                View a2 = actionMenuPresenter.a(kVar2, actionMenuPresenter.A, viewGroup);
                if (actionMenuPresenter.A == null) {
                    actionMenuPresenter.A = a2;
                }
                if (actionMenuPresenter.v) {
                    i3 -= ActionMenuView.a(a2, i2, i3, makeMeasureSpec, i5);
                } else {
                    a2.measure(makeMeasureSpec, makeMeasureSpec);
                }
                int measuredWidth = a2.getMeasuredWidth();
                i14 -= measuredWidth;
                if (i16 != 0) {
                    measuredWidth = i16;
                }
                int groupId = kVar2.getGroupId();
                if (groupId != 0) {
                    z = true;
                    sparseBooleanArray.put(groupId, true);
                } else {
                    z = true;
                }
                kVar2.d(z);
                i4 = i;
                i16 = measuredWidth;
            } else if (kVar2.l()) {
                int groupId2 = kVar2.getGroupId();
                boolean z3 = sparseBooleanArray.get(groupId2);
                boolean z4 = (i12 > 0 || z3) && i14 > 0 && (!actionMenuPresenter.v || i3 > 0);
                if (z4) {
                    boolean z5 = z4;
                    View a3 = actionMenuPresenter.a(kVar2, actionMenuPresenter.A, viewGroup);
                    i4 = i;
                    if (actionMenuPresenter.A == null) {
                        actionMenuPresenter.A = a3;
                    }
                    if (actionMenuPresenter.v) {
                        int a4 = ActionMenuView.a(a3, i2, i3, makeMeasureSpec, 0);
                        i3 -= a4;
                        if (a4 == 0) {
                            z5 = false;
                        }
                    } else {
                        a3.measure(makeMeasureSpec, makeMeasureSpec);
                    }
                    int measuredWidth2 = a3.getMeasuredWidth();
                    i14 -= measuredWidth2;
                    if (i16 == 0) {
                        i16 = measuredWidth2;
                    }
                    z4 = z5 & (!actionMenuPresenter.v ? i14 + i16 <= 0 : i14 < 0);
                } else {
                    i4 = i;
                }
                if (z4 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z3) {
                    sparseBooleanArray.put(groupId2, false);
                    for (int i17 = 0; i17 < i15; i17++) {
                        android.support.v7.view.menu.k kVar3 = arrayList.get(i17);
                        if (kVar3.getGroupId() == groupId2) {
                            if (kVar3.k()) {
                                i12++;
                            }
                            kVar3.d(false);
                        }
                    }
                }
                if (z4) {
                    i12--;
                }
                kVar2.d(z4);
            } else {
                i4 = i;
                kVar2.d(false);
                i15++;
                i5 = 0;
                actionMenuPresenter = this;
                i = i4;
            }
            i15++;
            i5 = 0;
            actionMenuPresenter = this;
            i = i4;
        }
        return true;
    }

    public void c(boolean z) {
        this.p = z;
        this.q = true;
    }

    public Drawable d() {
        d dVar = this.g;
        if (dVar != null) {
            return dVar.getDrawable();
        }
        if (this.o) {
            return this.n;
        }
        return null;
    }

    public void d(boolean z) {
        this.x = z;
    }

    public boolean e() {
        if (!this.p || j() || this.c == null || this.f == null || this.j != null || this.c.n().isEmpty()) {
            return false;
        }
        this.j = new c(new e(this.b, this.c, this.g, true));
        ((View) this.f).post(this.j);
        super.a((android.support.v7.view.menu.v) null);
        return true;
    }

    @Override // android.support.v7.view.menu.p
    public Parcelable f() {
        SavedState savedState = new SavedState();
        savedState.a = this.l;
        return savedState;
    }

    public boolean g() {
        if (this.j != null && this.f != null) {
            ((View) this.f).removeCallbacks(this.j);
            this.j = null;
            return true;
        }
        e eVar = this.h;
        if (eVar != null) {
            eVar.a();
            return true;
        }
        return false;
    }

    public boolean h() {
        return g() | i();
    }

    public boolean i() {
        a aVar = this.i;
        if (aVar != null) {
            aVar.a();
            return true;
        }
        return false;
    }

    public boolean j() {
        e eVar = this.h;
        return eVar != null && eVar.g();
    }

    public boolean k() {
        return this.j != null || j();
    }

    public boolean l() {
        return this.p;
    }
}
