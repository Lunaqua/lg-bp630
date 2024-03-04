package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.af;
import android.support.annotation.an;
import android.support.v4.view.ac;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class h implements android.support.v4.d.a.a {
    private static final String j = "MenuBuilder";
    private static final String k = "android:menu:presenters";
    private static final String l = "android:menu:actionviewstates";
    private static final String m = "android:menu:expandedactionview";
    private static final int[] n = {1, 4, 5, 3, 2, 0};
    private ContextMenu.ContextMenuInfo A;
    private SparseArray<Parcelable> B;
    private k J;
    private boolean L;
    CharSequence g;
    Drawable h;
    View i;
    private final Context o;
    private final Resources p;
    private boolean q;
    private boolean r;
    private a s;
    private int z = 0;
    private boolean C = false;
    private boolean D = false;
    private boolean E = false;
    private boolean F = false;
    private boolean G = false;
    private ArrayList<k> H = new ArrayList<>();
    private CopyOnWriteArrayList<WeakReference<p>> I = new CopyOnWriteArrayList<>();
    private boolean K = false;
    private ArrayList<k> t = new ArrayList<>();
    private ArrayList<k> u = new ArrayList<>();
    private boolean v = true;
    private ArrayList<k> w = new ArrayList<>();
    private ArrayList<k> x = new ArrayList<>();
    private boolean y = true;

    @an(a = {an.a.LIBRARY_GROUP})
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        void a(h hVar);

        boolean a(h hVar, MenuItem menuItem);
    }

    @an(a = {an.a.LIBRARY_GROUP})
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface b {
        boolean a(k kVar);
    }

    public h(Context context) {
        this.o = context;
        this.p = context.getResources();
        g(true);
    }

    private static int a(ArrayList<k> arrayList, int i) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size).c() <= i) {
                return size + 1;
            }
        }
        return 0;
    }

    private k a(int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        return new k(this, i, i2, i3, i4, charSequence, i5);
    }

    private void a(int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        Resources f = f();
        if (view != null) {
            this.i = view;
            this.g = null;
            this.h = null;
        } else {
            if (i > 0) {
                this.g = f.getText(i);
            } else if (charSequence != null) {
                this.g = charSequence;
            }
            if (i2 > 0) {
                this.h = android.support.v4.content.c.getDrawable(g(), i2);
            } else if (drawable != null) {
                this.h = drawable;
            }
            this.i = null;
        }
        c(false);
    }

    private void a(int i, boolean z) {
        if (i < 0 || i >= this.t.size()) {
            return;
        }
        this.t.remove(i);
        if (z) {
            c(true);
        }
    }

    private boolean a(v vVar, p pVar) {
        if (this.I.isEmpty()) {
            return false;
        }
        boolean a2 = pVar != null ? pVar.a(vVar) : false;
        Iterator<WeakReference<p>> it = this.I.iterator();
        while (it.hasNext()) {
            WeakReference<p> next = it.next();
            p pVar2 = next.get();
            if (pVar2 == null) {
                this.I.remove(next);
            } else if (!a2) {
                a2 = pVar2.a(vVar);
            }
        }
        return a2;
    }

    private void e(Bundle bundle) {
        Parcelable f;
        if (this.I.isEmpty()) {
            return;
        }
        SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
        Iterator<WeakReference<p>> it = this.I.iterator();
        while (it.hasNext()) {
            WeakReference<p> next = it.next();
            p pVar = next.get();
            if (pVar == null) {
                this.I.remove(next);
            } else {
                int c = pVar.c();
                if (c > 0 && (f = pVar.f()) != null) {
                    sparseArray.put(c, f);
                }
            }
        }
        bundle.putSparseParcelableArray(k, sparseArray);
    }

    private void f(Bundle bundle) {
        Parcelable parcelable;
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(k);
        if (sparseParcelableArray == null || this.I.isEmpty()) {
            return;
        }
        Iterator<WeakReference<p>> it = this.I.iterator();
        while (it.hasNext()) {
            WeakReference<p> next = it.next();
            p pVar = next.get();
            if (pVar == null) {
                this.I.remove(next);
            } else {
                int c = pVar.c();
                if (c > 0 && (parcelable = (Parcelable) sparseParcelableArray.get(c)) != null) {
                    pVar.a(parcelable);
                }
            }
        }
    }

    private void f(boolean z) {
        if (this.I.isEmpty()) {
            return;
        }
        i();
        Iterator<WeakReference<p>> it = this.I.iterator();
        while (it.hasNext()) {
            WeakReference<p> next = it.next();
            p pVar = next.get();
            if (pVar == null) {
                this.I.remove(next);
            } else {
                pVar.b(z);
            }
        }
        j();
    }

    private static int g(int i) {
        int i2 = ((-65536) & i) >> 16;
        if (i2 >= 0) {
            int[] iArr = n;
            if (i2 < iArr.length) {
                return (i & android.support.v4.d.a.a.a) | (iArr[i2] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    private void g(boolean z) {
        boolean z2 = true;
        this.r = (z && this.p.getConfiguration().keyboard != 1 && ac.c(ViewConfiguration.get(this.o), this.o)) ? false : false;
    }

    public int a(int i, int i2) {
        int size = size();
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < size) {
            if (this.t.get(i2).getGroupId() == i) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public h a(int i) {
        this.z = i;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public h a(Drawable drawable) {
        a(0, null, 0, drawable, null);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public h a(View view) {
        a(0, null, 0, null, view);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public h a(CharSequence charSequence) {
        a(0, charSequence, 0, null, null);
        return this;
    }

    k a(int i, KeyEvent keyEvent) {
        ArrayList<k> arrayList = this.H;
        arrayList.clear();
        a(arrayList, i, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return arrayList.get(0);
        }
        boolean d = d();
        for (int i2 = 0; i2 < size; i2++) {
            k kVar = arrayList.get(i2);
            char alphabeticShortcut = d ? kVar.getAlphabeticShortcut() : kVar.getNumericShortcut();
            if ((alphabeticShortcut == keyData.meta[0] && (metaState & 2) == 0) || ((alphabeticShortcut == keyData.meta[2] && (metaState & 2) != 0) || (d && alphabeticShortcut == '\b' && i == 67))) {
                return kVar;
            }
        }
        return null;
    }

    protected MenuItem a(int i, int i2, int i3, CharSequence charSequence) {
        int g = g(i3);
        k a2 = a(i, i2, i3, g, charSequence, this.z);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.A;
        if (contextMenuInfo != null) {
            a2.a(contextMenuInfo);
        }
        ArrayList<k> arrayList = this.t;
        arrayList.add(a(arrayList, g), a2);
        c(true);
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String a() {
        return l;
    }

    public void a(Bundle bundle) {
        e(bundle);
    }

    public void a(a aVar) {
        this.s = aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(k kVar) {
        this.v = true;
        c(true);
    }

    public void a(p pVar) {
        a(pVar, this.o);
    }

    public void a(p pVar, Context context) {
        this.I.add(new WeakReference<>(pVar));
        pVar.a(context, this);
        this.y = true;
    }

    public void a(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.A = contextMenuInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.t.size();
        i();
        for (int i = 0; i < size; i++) {
            k kVar = this.t.get(i);
            if (kVar.getGroupId() == groupId && kVar.h() && kVar.isCheckable()) {
                kVar.b(kVar == menuItem);
            }
        }
        j();
    }

    void a(List<k> list, int i, KeyEvent keyEvent) {
        boolean d = d();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            int size = this.t.size();
            for (int i2 = 0; i2 < size; i2++) {
                k kVar = this.t.get(i2);
                if (kVar.hasSubMenu()) {
                    ((h) kVar.getSubMenu()).a(list, i, keyEvent);
                }
                char alphabeticShortcut = d ? kVar.getAlphabeticShortcut() : kVar.getNumericShortcut();
                if (((modifiers & android.support.v4.d.a.a.e) == ((d ? kVar.getAlphabeticModifiers() : kVar.getNumericModifiers()) & android.support.v4.d.a.a.e)) && alphabeticShortcut != 0 && ((alphabeticShortcut == keyData.meta[0] || alphabeticShortcut == keyData.meta[2] || (d && alphabeticShortcut == '\b' && i == 67)) && kVar.isEnabled())) {
                    list.add(kVar);
                }
            }
        }
    }

    public void a(boolean z) {
        if (this.r == z) {
            return;
        }
        g(z);
        c(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(h hVar, MenuItem menuItem) {
        a aVar = this.s;
        return aVar != null && aVar.a(hVar, menuItem);
    }

    public boolean a(MenuItem menuItem, int i) {
        return a(menuItem, (p) null, i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002b, code lost:
        if (r1 != false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002d, code lost:
        b(true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003c, code lost:
        if ((r9 & 1) == 0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0068, code lost:
        if (r1 == false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006b, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(android.view.MenuItem r7, android.support.v7.view.menu.p r8, int r9) {
        /*
            r6 = this;
            android.support.v7.view.menu.k r7 = (android.support.v7.view.menu.k) r7
            r0 = 0
            if (r7 == 0) goto L6c
            boolean r1 = r7.isEnabled()
            if (r1 != 0) goto Lc
            goto L6c
        Lc:
            boolean r1 = r7.b()
            android.support.v4.view.b r2 = r7.a()
            r3 = 1
            if (r2 == 0) goto L1f
            boolean r4 = r2.g()
            if (r4 == 0) goto L1f
            r4 = 1
            goto L20
        L1f:
            r4 = 0
        L20:
            boolean r5 = r7.o()
            if (r5 == 0) goto L31
            boolean r7 = r7.expandActionView()
            r1 = r1 | r7
            if (r1 == 0) goto L6b
        L2d:
            r6.b(r3)
            goto L6b
        L31:
            boolean r5 = r7.hasSubMenu()
            if (r5 != 0) goto L3f
            if (r4 == 0) goto L3a
            goto L3f
        L3a:
            r7 = r9 & 1
            if (r7 != 0) goto L6b
            goto L2d
        L3f:
            r9 = r9 & 4
            if (r9 != 0) goto L46
            r6.b(r0)
        L46:
            boolean r9 = r7.hasSubMenu()
            if (r9 != 0) goto L58
            android.support.v7.view.menu.v r9 = new android.support.v7.view.menu.v
            android.content.Context r0 = r6.g()
            r9.<init>(r0, r6, r7)
            r7.a(r9)
        L58:
            android.view.SubMenu r7 = r7.getSubMenu()
            android.support.v7.view.menu.v r7 = (android.support.v7.view.menu.v) r7
            if (r4 == 0) goto L63
            r2.a(r7)
        L63:
            boolean r7 = r6.a(r7, r8)
            r1 = r1 | r7
            if (r1 != 0) goto L6b
            goto L2d
        L6b:
            return r1
        L6c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.menu.h.a(android.view.MenuItem, android.support.v7.view.menu.p, int):boolean");
    }

    @Override // android.view.Menu
    public MenuItem add(int i) {
        return a(0, 0, 0, this.p.getString(i));
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, int i4) {
        return a(i, i2, i3, this.p.getString(i4));
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return a(i, i2, i3, charSequence);
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        PackageManager packageManager = this.o.getPackageManager();
        List<ResolveInfo> queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = queryIntentActivityOptions != null ? queryIntentActivityOptions.size() : 0;
        if ((i4 & 1) == 0) {
            removeGroup(i);
        }
        for (int i5 = 0; i5 < size; i5++) {
            ResolveInfo resolveInfo = queryIntentActivityOptions.get(i5);
            Intent intent2 = new Intent(resolveInfo.specificIndex < 0 ? intent : intentArr[resolveInfo.specificIndex]);
            intent2.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            MenuItem intent3 = add(i, i2, i3, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && resolveInfo.specificIndex >= 0) {
                menuItemArr[resolveInfo.specificIndex] = intent3;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, this.p.getString(i));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, this.p.getString(i4));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        k kVar = (k) a(i, i2, i3, charSequence);
        v vVar = new v(this.o, this, kVar);
        kVar.a(vVar);
        return vVar;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public void b(int i) {
        a(i, true);
    }

    public void b(Bundle bundle) {
        f(bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(k kVar) {
        this.y = true;
        c(true);
    }

    public void b(p pVar) {
        Iterator<WeakReference<p>> it = this.I.iterator();
        while (it.hasNext()) {
            WeakReference<p> next = it.next();
            p pVar2 = next.get();
            if (pVar2 == null || pVar2 == pVar) {
                this.I.remove(next);
            }
        }
    }

    public final void b(boolean z) {
        if (this.G) {
            return;
        }
        this.G = true;
        Iterator<WeakReference<p>> it = this.I.iterator();
        while (it.hasNext()) {
            WeakReference<p> next = it.next();
            p pVar = next.get();
            if (pVar == null) {
                this.I.remove(next);
            } else {
                pVar.a(this, z);
            }
        }
        this.G = false;
    }

    public boolean b() {
        return this.K;
    }

    public int c(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.t.get(i2).getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    public void c() {
        this.C = true;
        clear();
        clearHeader();
        this.I.clear();
        this.C = false;
        this.D = false;
        this.E = false;
        c(true);
    }

    public void c(Bundle bundle) {
        int size = size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt(m, item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((v) item.getSubMenu()).c(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(a(), sparseArray);
        }
    }

    public void c(boolean z) {
        if (this.C) {
            this.D = true;
            if (z) {
                this.E = true;
                return;
            }
            return;
        }
        if (z) {
            this.v = true;
            this.y = true;
        }
        f(z);
    }

    public boolean c(k kVar) {
        boolean z = false;
        if (this.I.isEmpty()) {
            return false;
        }
        i();
        Iterator<WeakReference<p>> it = this.I.iterator();
        while (it.hasNext()) {
            WeakReference<p> next = it.next();
            p pVar = next.get();
            if (pVar == null) {
                this.I.remove(next);
            } else {
                z = pVar.a(this, kVar);
                if (z) {
                    break;
                }
            }
        }
        j();
        if (z) {
            this.J = kVar;
        }
        return z;
    }

    @Override // android.view.Menu
    public void clear() {
        k kVar = this.J;
        if (kVar != null) {
            d(kVar);
        }
        this.t.clear();
        c(true);
    }

    public void clearHeader() {
        this.h = null;
        this.g = null;
        this.i = null;
        c(false);
    }

    @Override // android.view.Menu
    public void close() {
        b(true);
    }

    public int d(int i) {
        return a(i, 0);
    }

    public void d(Bundle bundle) {
        MenuItem findItem;
        if (bundle == null) {
            return;
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(a());
        int size = size();
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((v) item.getSubMenu()).d(bundle);
            }
        }
        int i2 = bundle.getInt(m);
        if (i2 <= 0 || (findItem = findItem(i2)) == null) {
            return;
        }
        findItem.expandActionView();
    }

    public void d(boolean z) {
        this.F = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        return this.q;
    }

    public boolean d(k kVar) {
        boolean z = false;
        if (!this.I.isEmpty() && this.J == kVar) {
            i();
            Iterator<WeakReference<p>> it = this.I.iterator();
            while (it.hasNext()) {
                WeakReference<p> next = it.next();
                p pVar = next.get();
                if (pVar == null) {
                    this.I.remove(next);
                } else {
                    z = pVar.b(this, kVar);
                    if (z) {
                        break;
                    }
                }
            }
            j();
            if (z) {
                this.J = null;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public h e(int i) {
        a(i, null, 0, null, null);
        return this;
    }

    public void e(boolean z) {
        this.L = z;
    }

    public boolean e() {
        return this.r;
    }

    Resources f() {
        return this.p;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public h f(int i) {
        a(0, null, i, null, null);
        return this;
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i) {
        MenuItem findItem;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            k kVar = this.t.get(i2);
            if (kVar.getItemId() == i) {
                return kVar;
            }
            if (kVar.hasSubMenu() && (findItem = kVar.getSubMenu().findItem(i)) != null) {
                return findItem;
            }
        }
        return null;
    }

    public Context g() {
        return this.o;
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i) {
        return this.t.get(i);
    }

    public void h() {
        a aVar = this.s;
        if (aVar != null) {
            aVar.a(this);
        }
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        if (this.L) {
            return true;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.t.get(i).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public void i() {
        if (this.C) {
            return;
        }
        this.C = true;
        this.D = false;
        this.E = false;
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return a(i, keyEvent) != null;
    }

    public void j() {
        this.C = false;
        if (this.D) {
            this.D = false;
            c(this.E);
        }
    }

    @af
    public ArrayList<k> k() {
        if (this.v) {
            this.u.clear();
            int size = this.t.size();
            for (int i = 0; i < size; i++) {
                k kVar = this.t.get(i);
                if (kVar.isVisible()) {
                    this.u.add(kVar);
                }
            }
            this.v = false;
            this.y = true;
            return this.u;
        }
        return this.u;
    }

    public void l() {
        ArrayList<k> k2 = k();
        if (this.y) {
            Iterator<WeakReference<p>> it = this.I.iterator();
            boolean z = false;
            while (it.hasNext()) {
                WeakReference<p> next = it.next();
                p pVar = next.get();
                if (pVar == null) {
                    this.I.remove(next);
                } else {
                    z |= pVar.b();
                }
            }
            if (z) {
                this.w.clear();
                this.x.clear();
                int size = k2.size();
                for (int i = 0; i < size; i++) {
                    k kVar = k2.get(i);
                    (kVar.k() ? this.w : this.x).add(kVar);
                }
            } else {
                this.w.clear();
                this.x.clear();
                this.x.addAll(k());
            }
            this.y = false;
        }
    }

    public ArrayList<k> m() {
        l();
        return this.w;
    }

    public ArrayList<k> n() {
        l();
        return this.x;
    }

    public CharSequence o() {
        return this.g;
    }

    public Drawable p() {
        return this.h;
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i, int i2) {
        return a(findItem(i), i2);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        k a2 = a(i, keyEvent);
        boolean a3 = a2 != null ? a(a2, i2) : false;
        if ((i2 & 2) != 0) {
            b(true);
        }
        return a3;
    }

    public View q() {
        return this.i;
    }

    public h r() {
        return this;
    }

    @Override // android.view.Menu
    public void removeGroup(int i) {
        int d = d(i);
        if (d >= 0) {
            int size = this.t.size() - d;
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (i2 >= size || this.t.get(d).getGroupId() != i) {
                    break;
                }
                a(d, false);
                i2 = i3;
            }
            c(true);
        }
    }

    @Override // android.view.Menu
    public void removeItem(int i) {
        a(c(i), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean s() {
        return this.F;
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i, boolean z, boolean z2) {
        int size = this.t.size();
        for (int i2 = 0; i2 < size; i2++) {
            k kVar = this.t.get(i2);
            if (kVar.getGroupId() == i) {
                kVar.a(z2);
                kVar.setCheckable(z);
            }
        }
    }

    @Override // android.support.v4.d.a.a, android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.K = z;
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i, boolean z) {
        int size = this.t.size();
        for (int i2 = 0; i2 < size; i2++) {
            k kVar = this.t.get(i2);
            if (kVar.getGroupId() == i) {
                kVar.setEnabled(z);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i, boolean z) {
        int size = this.t.size();
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            k kVar = this.t.get(i2);
            if (kVar.getGroupId() == i && kVar.c(z)) {
                z2 = true;
            }
        }
        if (z2) {
            c(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z) {
        this.q = z;
        c(false);
    }

    @Override // android.view.Menu
    public int size() {
        return this.t.size();
    }

    public k t() {
        return this.J;
    }
}
