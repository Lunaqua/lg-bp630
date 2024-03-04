package android.support.v7.view.menu;

import android.content.Context;
import android.support.annotation.an;
import android.support.v7.view.menu.p;
import android.support.v7.view.menu.q;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class b implements p {
    protected Context a;
    protected Context b;
    protected h c;
    protected LayoutInflater d;
    protected LayoutInflater e;
    protected q f;
    private p.a g;
    private int h;
    private int i;
    private int j;

    public b(Context context, int i, int i2) {
        this.a = context;
        this.d = LayoutInflater.from(context);
        this.h = i;
        this.i = i2;
    }

    public p.a a() {
        return this.g;
    }

    @Override // android.support.v7.view.menu.p
    public q a(ViewGroup viewGroup) {
        if (this.f == null) {
            this.f = (q) this.d.inflate(this.h, viewGroup, false);
            this.f.a(this.c);
            b(true);
        }
        return this.f;
    }

    public View a(k kVar, View view, ViewGroup viewGroup) {
        q.a b = view instanceof q.a ? (q.a) view : b(viewGroup);
        a(kVar, b);
        return (View) b;
    }

    public void a(int i) {
        this.j = i;
    }

    @Override // android.support.v7.view.menu.p
    public void a(Context context, h hVar) {
        this.b = context;
        this.e = LayoutInflater.from(this.b);
        this.c = hVar;
    }

    @Override // android.support.v7.view.menu.p
    public void a(h hVar, boolean z) {
        p.a aVar = this.g;
        if (aVar != null) {
            aVar.a(hVar, z);
        }
    }

    public abstract void a(k kVar, q.a aVar);

    @Override // android.support.v7.view.menu.p
    public void a(p.a aVar) {
        this.g = aVar;
    }

    protected void a(View view, int i) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.f).addView(view, i);
    }

    public boolean a(int i, k kVar) {
        return true;
    }

    @Override // android.support.v7.view.menu.p
    public boolean a(h hVar, k kVar) {
        return false;
    }

    @Override // android.support.v7.view.menu.p
    public boolean a(v vVar) {
        p.a aVar = this.g;
        if (aVar != null) {
            return aVar.a(vVar);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    public q.a b(ViewGroup viewGroup) {
        return (q.a) this.d.inflate(this.i, viewGroup, false);
    }

    @Override // android.support.v7.view.menu.p
    public void b(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.f;
        if (viewGroup == null) {
            return;
        }
        h hVar = this.c;
        int i = 0;
        if (hVar != null) {
            hVar.l();
            ArrayList<k> k = this.c.k();
            int size = k.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                k kVar = k.get(i3);
                if (a(i2, kVar)) {
                    View childAt = viewGroup.getChildAt(i2);
                    k itemData = childAt instanceof q.a ? ((q.a) childAt).getItemData() : null;
                    View a = a(kVar, childAt, viewGroup);
                    if (kVar != itemData) {
                        a.setPressed(false);
                        a.jumpDrawablesToCurrentState();
                    }
                    if (a != childAt) {
                        a(a, i2);
                    }
                    i2++;
                }
            }
            i = i2;
        }
        while (i < viewGroup.getChildCount()) {
            if (!a(viewGroup, i)) {
                i++;
            }
        }
    }

    @Override // android.support.v7.view.menu.p
    public boolean b() {
        return false;
    }

    @Override // android.support.v7.view.menu.p
    public boolean b(h hVar, k kVar) {
        return false;
    }

    @Override // android.support.v7.view.menu.p
    public int c() {
        return this.j;
    }
}
