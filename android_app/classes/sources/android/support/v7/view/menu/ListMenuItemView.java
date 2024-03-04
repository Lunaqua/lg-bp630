package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.an;
import android.support.v4.view.ab;
import android.support.v7.a.a;
import android.support.v7.view.menu.q;
import android.support.v7.widget.av;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ListMenuItemView extends LinearLayout implements q.a, AbsListView.SelectionBoundsAdjuster {
    private static final String a = "ListMenuItemView";
    private k b;
    private ImageView c;
    private RadioButton d;
    private TextView e;
    private CheckBox f;
    private TextView g;
    private ImageView h;
    private ImageView i;
    private LinearLayout j;
    private Drawable k;
    private int l;
    private Context m;
    private boolean n;
    private Drawable o;
    private boolean p;
    private int q;
    private LayoutInflater r;
    private boolean s;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.b.listMenuViewStyle);
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        av a2 = av.a(getContext(), attributeSet, a.l.MenuView, i, 0);
        this.k = a2.a(a.l.MenuView_android_itemBackground);
        this.l = a2.g(a.l.MenuView_android_itemTextAppearance, -1);
        this.n = a2.a(a.l.MenuView_preserveIconSpacing, false);
        this.m = context;
        this.o = a2.a(a.l.MenuView_subMenuArrow);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{16843049}, a.b.dropDownListViewStyle, 0);
        this.p = obtainStyledAttributes.hasValue(0);
        a2.e();
        obtainStyledAttributes.recycle();
    }

    private void a(View view) {
        a(view, -1);
    }

    private void a(View view, int i) {
        LinearLayout linearLayout = this.j;
        if (linearLayout != null) {
            linearLayout.addView(view, i);
        } else {
            addView(view, i);
        }
    }

    private void b() {
        this.c = (ImageView) getInflater().inflate(a.i.abc_list_menu_item_icon, (ViewGroup) this, false);
        a(this.c, 0);
    }

    private void d() {
        this.d = (RadioButton) getInflater().inflate(a.i.abc_list_menu_item_radio, (ViewGroup) this, false);
        a(this.d);
    }

    private void e() {
        this.f = (CheckBox) getInflater().inflate(a.i.abc_list_menu_item_checkbox, (ViewGroup) this, false);
        a(this.f);
    }

    private LayoutInflater getInflater() {
        if (this.r == null) {
            this.r = LayoutInflater.from(getContext());
        }
        return this.r;
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView imageView = this.h;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
    }

    @Override // android.support.v7.view.menu.q.a
    public void a(k kVar, int i) {
        this.b = kVar;
        this.q = i;
        setVisibility(kVar.isVisible() ? 0 : 8);
        setTitle(kVar.a((q.a) this));
        setCheckable(kVar.isCheckable());
        a(kVar.g(), kVar.e());
        setIcon(kVar.getIcon());
        setEnabled(kVar.isEnabled());
        setSubMenuArrowVisible(kVar.hasSubMenu());
        setContentDescription(kVar.getContentDescription());
    }

    @Override // android.support.v7.view.menu.q.a
    public void a(boolean z, char c) {
        int i = (z && this.b.g()) ? 0 : 8;
        if (i == 0) {
            this.g.setText(this.b.f());
        }
        if (this.g.getVisibility() != i) {
            this.g.setVisibility(i);
        }
    }

    @Override // android.support.v7.view.menu.q.a
    public boolean a() {
        return false;
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.i;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.i.getLayoutParams();
        rect.top += this.i.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    @Override // android.support.v7.view.menu.q.a
    public boolean c() {
        return this.s;
    }

    @Override // android.support.v7.view.menu.q.a
    public k getItemData() {
        return this.b;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        ab.a(this, this.k);
        this.e = (TextView) findViewById(a.g.title);
        int i = this.l;
        if (i != -1) {
            this.e.setTextAppearance(this.m, i);
        }
        this.g = (TextView) findViewById(a.g.shortcut);
        this.h = (ImageView) findViewById(a.g.submenuarrow);
        ImageView imageView = this.h;
        if (imageView != null) {
            imageView.setImageDrawable(this.o);
        }
        this.i = (ImageView) findViewById(a.g.group_divider);
        this.j = (LinearLayout) findViewById(a.g.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.c != null && this.n) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.c.getLayoutParams();
            if (layoutParams.height > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = layoutParams.height;
            }
        }
        super.onMeasure(i, i2);
    }

    @Override // android.support.v7.view.menu.q.a
    public void setCheckable(boolean z) {
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        if (!z && this.d == null && this.f == null) {
            return;
        }
        if (this.b.h()) {
            if (this.d == null) {
                d();
            }
            compoundButton = this.d;
            compoundButton2 = this.f;
        } else {
            if (this.f == null) {
                e();
            }
            compoundButton = this.f;
            compoundButton2 = this.d;
        }
        if (z) {
            compoundButton.setChecked(this.b.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (compoundButton2 == null || compoundButton2.getVisibility() == 8) {
                return;
            }
            compoundButton2.setVisibility(8);
            return;
        }
        CheckBox checkBox = this.f;
        if (checkBox != null) {
            checkBox.setVisibility(8);
        }
        RadioButton radioButton = this.d;
        if (radioButton != null) {
            radioButton.setVisibility(8);
        }
    }

    @Override // android.support.v7.view.menu.q.a
    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.b.h()) {
            if (this.d == null) {
                d();
            }
            compoundButton = this.d;
        } else {
            if (this.f == null) {
                e();
            }
            compoundButton = this.f;
        }
        compoundButton.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.s = z;
        this.n = z;
    }

    public void setGroupDividerEnabled(boolean z) {
        ImageView imageView = this.i;
        if (imageView != null) {
            imageView.setVisibility((this.p || !z) ? 8 : 0);
        }
    }

    @Override // android.support.v7.view.menu.q.a
    public void setIcon(Drawable drawable) {
        boolean z = this.b.j() || this.s;
        if (z || this.n) {
            if (this.c == null && drawable == null && !this.n) {
                return;
            }
            if (this.c == null) {
                b();
            }
            if (drawable == null && !this.n) {
                this.c.setVisibility(8);
                return;
            }
            ImageView imageView = this.c;
            if (!z) {
                drawable = null;
            }
            imageView.setImageDrawable(drawable);
            if (this.c.getVisibility() != 0) {
                this.c.setVisibility(0);
            }
        }
    }

    @Override // android.support.v7.view.menu.q.a
    public void setTitle(CharSequence charSequence) {
        int i;
        TextView textView;
        if (charSequence != null) {
            this.e.setText(charSequence);
            if (this.e.getVisibility() == 0) {
                return;
            }
            textView = this.e;
            i = 0;
        } else {
            i = 8;
            if (this.e.getVisibility() == 8) {
                return;
            }
            textView = this.e;
        }
        textView.setVisibility(i);
    }
}
