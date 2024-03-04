package android.support.v4.view.a;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c {
    public static final String A = "ACTION_ARGUMENT_SELECTION_START_INT";
    public static final String B = "ACTION_ARGUMENT_SELECTION_END_INT";
    public static final String C = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
    public static final String D = "android.view.accessibility.action.ARGUMENT_ROW_INT";
    public static final String E = "android.view.accessibility.action.ARGUMENT_COLUMN_INT";
    public static final String F = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE";
    public static final String G = "ACTION_ARGUMENT_MOVE_WINDOW_X";
    public static final String H = "ACTION_ARGUMENT_MOVE_WINDOW_Y";
    public static final int I = 1;
    public static final int J = 2;
    public static final int K = 1;
    public static final int L = 2;
    public static final int M = 4;
    public static final int N = 8;
    public static final int O = 16;
    private static final String P = "AccessibilityNodeInfo.roleDescription";
    private static final String Q = "androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY";
    private static final String R = "androidx.view.accessibility.AccessibilityNodeInfoCompat.TOOLTIP_TEXT_KEY";
    private static final String S = "androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY";
    private static final String T = "androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY";
    private static final int U = 1;
    private static final int V = 2;
    private static final int W = 4;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 4;
    public static final int e = 8;
    public static final int f = 16;
    public static final int g = 32;
    public static final int h = 64;
    public static final int i = 128;
    public static final int j = 256;
    public static final int k = 512;
    public static final int l = 1024;
    public static final int m = 2048;
    public static final int n = 4096;
    public static final int o = 8192;
    public static final int p = 16384;
    public static final int q = 32768;
    public static final int r = 65536;
    public static final int s = 131072;
    public static final int t = 262144;
    public static final int u = 524288;
    public static final int v = 1048576;
    public static final int w = 2097152;
    public static final String x = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
    public static final String y = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
    public static final String z = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
    private final AccessibilityNodeInfo X;
    @an(a = {an.a.LIBRARY_GROUP})
    public int a = -1;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a {
        public static final a A;
        public static final a B;
        public static final a C;
        public static final a D;
        public static final a E;
        public static final a F;
        public static final a G;
        public static final a a = new a(1, null);
        public static final a b = new a(2, null);
        public static final a c = new a(4, null);
        public static final a d = new a(8, null);
        public static final a e = new a(16, null);
        public static final a f = new a(32, null);
        public static final a g = new a(64, null);
        public static final a h = new a(128, null);
        public static final a i = new a(256, null);
        public static final a j = new a(512, null);
        public static final a k = new a(1024, null);
        public static final a l = new a(2048, null);
        public static final a m = new a(4096, null);
        public static final a n = new a(8192, null);
        public static final a o = new a(16384, null);
        public static final a p = new a(32768, null);
        public static final a q = new a(65536, null);
        public static final a r = new a(131072, null);
        public static final a s = new a(262144, null);
        public static final a t = new a(524288, null);
        public static final a u = new a(1048576, null);
        public static final a v = new a(2097152, null);
        public static final a w;
        public static final a x;
        public static final a y;
        public static final a z;
        final Object H;

        static {
            w = new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN : null);
            x = new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION : null);
            y = new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP : null);
            z = new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT : null);
            A = new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN : null);
            B = new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT : null);
            C = new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK : null);
            D = new a(Build.VERSION.SDK_INT >= 24 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS : null);
            E = new a(Build.VERSION.SDK_INT >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null);
            F = new a(Build.VERSION.SDK_INT >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null);
            G = new a(Build.VERSION.SDK_INT >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null);
        }

        public a(int i2, CharSequence charSequence) {
            this(Build.VERSION.SDK_INT >= 21 ? new AccessibilityNodeInfo.AccessibilityAction(i2, charSequence) : null);
        }

        a(Object obj) {
            this.H = obj;
        }

        public int a() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.AccessibilityAction) this.H).getId();
            }
            return 0;
        }

        public CharSequence b() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.AccessibilityAction) this.H).getLabel();
            }
            return null;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b {
        public static final int a = 0;
        public static final int b = 1;
        public static final int c = 2;
        final Object d;

        b(Object obj) {
            this.d = obj;
        }

        public static b a(int i, int i2, boolean z) {
            return Build.VERSION.SDK_INT >= 19 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z)) : new b(null);
        }

        public static b a(int i, int i2, boolean z, int i3) {
            return Build.VERSION.SDK_INT >= 21 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z, i3)) : Build.VERSION.SDK_INT >= 19 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z)) : new b(null);
        }

        public int a() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionInfo) this.d).getColumnCount();
            }
            return 0;
        }

        public int b() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionInfo) this.d).getRowCount();
            }
            return 0;
        }

        public boolean c() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionInfo) this.d).isHierarchical();
            }
            return false;
        }

        public int d() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.CollectionInfo) this.d).getSelectionMode();
            }
            return 0;
        }
    }

    /* renamed from: android.support.v4.view.a.c$c  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class C0032c {
        final Object a;

        C0032c(Object obj) {
            this.a = obj;
        }

        public static C0032c a(int i, int i2, int i3, int i4, boolean z) {
            return Build.VERSION.SDK_INT >= 19 ? new C0032c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z)) : new C0032c(null);
        }

        public static C0032c a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
            return Build.VERSION.SDK_INT >= 21 ? new C0032c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z, z2)) : Build.VERSION.SDK_INT >= 19 ? new C0032c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z)) : new C0032c(null);
        }

        public int a() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionItemInfo) this.a).getColumnIndex();
            }
            return 0;
        }

        public int b() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionItemInfo) this.a).getColumnSpan();
            }
            return 0;
        }

        public int c() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionItemInfo) this.a).getRowIndex();
            }
            return 0;
        }

        public int d() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionItemInfo) this.a).getRowSpan();
            }
            return 0;
        }

        public boolean e() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionItemInfo) this.a).isHeading();
            }
            return false;
        }

        public boolean f() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.CollectionItemInfo) this.a).isSelected();
            }
            return false;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class d {
        public static final int a = 0;
        public static final int b = 1;
        public static final int c = 2;
        final Object d;

        d(Object obj) {
            this.d = obj;
        }

        public static d a(int i, float f, float f2, float f3) {
            return Build.VERSION.SDK_INT >= 19 ? new d(AccessibilityNodeInfo.RangeInfo.obtain(i, f, f2, f3)) : new d(null);
        }

        public float a() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.RangeInfo) this.d).getCurrent();
            }
            return 0.0f;
        }

        public float b() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.RangeInfo) this.d).getMax();
            }
            return 0.0f;
        }

        public float c() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.RangeInfo) this.d).getMin();
            }
            return 0.0f;
        }

        public int d() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.RangeInfo) this.d).getType();
            }
            return 0;
        }
    }

    private c(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.X = accessibilityNodeInfo;
    }

    @Deprecated
    public c(Object obj) {
        this.X = (AccessibilityNodeInfo) obj;
    }

    public static c a(c cVar) {
        return a(AccessibilityNodeInfo.obtain(cVar.X));
    }

    public static c a(View view) {
        return a(AccessibilityNodeInfo.obtain(view));
    }

    public static c a(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 16) {
            return a((Object) AccessibilityNodeInfo.obtain(view, i2));
        }
        return null;
    }

    public static c a(@af AccessibilityNodeInfo accessibilityNodeInfo) {
        return new c(accessibilityNodeInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static c a(Object obj) {
        if (obj != null) {
            return new c(obj);
        }
        return null;
    }

    private void a(int i2, boolean z2) {
        Bundle O2 = O();
        if (O2 != null) {
            int i3 = O2.getInt(T, 0) & (i2 ^ (-1));
            if (!z2) {
                i2 = 0;
            }
            O2.putInt(T, i2 | i3);
        }
    }

    public static c c() {
        return a(AccessibilityNodeInfo.obtain());
    }

    private boolean k(int i2) {
        Bundle O2 = O();
        return O2 != null && (O2.getInt(T, 0) & i2) == i2;
    }

    private static String l(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                switch (i2) {
                    case 4:
                        return "ACTION_SELECT";
                    case 8:
                        return "ACTION_CLEAR_SELECTION";
                    case 16:
                        return "ACTION_CLICK";
                    case 32:
                        return "ACTION_LONG_CLICK";
                    case 64:
                        return "ACTION_ACCESSIBILITY_FOCUS";
                    case 128:
                        return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
                    case 256:
                        return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
                    case 512:
                        return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
                    case 1024:
                        return "ACTION_NEXT_HTML_ELEMENT";
                    case 2048:
                        return "ACTION_PREVIOUS_HTML_ELEMENT";
                    case 4096:
                        return "ACTION_SCROLL_FORWARD";
                    case 8192:
                        return "ACTION_SCROLL_BACKWARD";
                    case 16384:
                        return "ACTION_COPY";
                    case 32768:
                        return "ACTION_PASTE";
                    case 65536:
                        return "ACTION_CUT";
                    case 131072:
                        return "ACTION_SET_SELECTION";
                    default:
                        return "ACTION_UNKNOWN";
                }
            }
            return "ACTION_CLEAR_FOCUS";
        }
        return "ACTION_FOCUS";
    }

    public String A() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.X.getViewIdResourceName();
        }
        return null;
    }

    public int B() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.X.getLiveRegion();
        }
        return 0;
    }

    public int C() {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.X.getDrawingOrder();
        }
        return 0;
    }

    public b D() {
        AccessibilityNodeInfo.CollectionInfo collectionInfo;
        if (Build.VERSION.SDK_INT < 19 || (collectionInfo = this.X.getCollectionInfo()) == null) {
            return null;
        }
        return new b(collectionInfo);
    }

    public C0032c E() {
        AccessibilityNodeInfo.CollectionItemInfo collectionItemInfo;
        if (Build.VERSION.SDK_INT < 19 || (collectionItemInfo = this.X.getCollectionItemInfo()) == null) {
            return null;
        }
        return new C0032c(collectionItemInfo);
    }

    public d F() {
        AccessibilityNodeInfo.RangeInfo rangeInfo;
        if (Build.VERSION.SDK_INT < 19 || (rangeInfo = this.X.getRangeInfo()) == null) {
            return null;
        }
        return new d(rangeInfo);
    }

    public List<a> G() {
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = Build.VERSION.SDK_INT >= 21 ? this.X.getActionList() : null;
        if (actionList != null) {
            ArrayList arrayList = new ArrayList();
            int size = actionList.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(new a(actionList.get(i2)));
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    public boolean H() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.X.isContentInvalid();
        }
        return false;
    }

    public boolean I() {
        if (Build.VERSION.SDK_INT >= 23) {
            return this.X.isContextClickable();
        }
        return false;
    }

    @ag
    public CharSequence J() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.X.getHintText();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return this.X.getExtras().getCharSequence(S);
        }
        return null;
    }

    public CharSequence K() {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.X.getError();
        }
        return null;
    }

    public c L() {
        if (Build.VERSION.SDK_INT >= 17) {
            return a((Object) this.X.getLabelFor());
        }
        return null;
    }

    public c M() {
        if (Build.VERSION.SDK_INT >= 17) {
            return a((Object) this.X.getLabeledBy());
        }
        return null;
    }

    public boolean N() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.X.canOpenPopup();
        }
        return false;
    }

    public Bundle O() {
        return Build.VERSION.SDK_INT >= 19 ? this.X.getExtras() : new Bundle();
    }

    public int P() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.X.getInputType();
        }
        return 0;
    }

    public int Q() {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.X.getMaxTextLength();
        }
        return -1;
    }

    public int R() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.X.getTextSelectionStart();
        }
        return -1;
    }

    public int S() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.X.getTextSelectionEnd();
        }
        return -1;
    }

    public c T() {
        if (Build.VERSION.SDK_INT >= 22) {
            return a((Object) this.X.getTraversalBefore());
        }
        return null;
    }

    public c U() {
        if (Build.VERSION.SDK_INT >= 22) {
            return a((Object) this.X.getTraversalAfter());
        }
        return null;
    }

    public f V() {
        if (Build.VERSION.SDK_INT >= 21) {
            return f.a(this.X.getWindow());
        }
        return null;
    }

    public boolean W() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.X.isDismissable();
        }
        return false;
    }

    public boolean X() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.X.isEditable();
        }
        return false;
    }

    public boolean Y() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.X.isMultiLine();
        }
        return false;
    }

    @ag
    public CharSequence Z() {
        if (Build.VERSION.SDK_INT >= 28) {
            return this.X.getTooltipText();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return this.X.getExtras().getCharSequence(R);
        }
        return null;
    }

    public c a(int i2) {
        if (Build.VERSION.SDK_INT >= 16) {
            return a((Object) this.X.findFocus(i2));
        }
        return null;
    }

    public AccessibilityNodeInfo a() {
        return this.X;
    }

    public List<c> a(String str) {
        ArrayList arrayList = new ArrayList();
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = this.X.findAccessibilityNodeInfosByText(str);
        int size = findAccessibilityNodeInfosByText.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(a(findAccessibilityNodeInfosByText.get(i2)));
        }
        return arrayList;
    }

    public void a(int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 18) {
            this.X.setTextSelection(i2, i3);
        }
    }

    public void a(Rect rect) {
        this.X.getBoundsInParent(rect);
    }

    public void a(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.X.addAction((AccessibilityNodeInfo.AccessibilityAction) aVar.H);
        }
    }

    public void a(d dVar) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.X.setRangeInfo((AccessibilityNodeInfo.RangeInfo) dVar.d);
        }
    }

    public void a(CharSequence charSequence) {
        this.X.setPackageName(charSequence);
    }

    public void a(boolean z2) {
        this.X.setCheckable(z2);
    }

    public boolean a(int i2, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.X.performAction(i2, bundle);
        }
        return false;
    }

    @ag
    public CharSequence aa() {
        if (Build.VERSION.SDK_INT >= 28) {
            return this.X.getPaneTitle();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return this.X.getExtras().getCharSequence(Q);
        }
        return null;
    }

    public boolean ab() {
        return Build.VERSION.SDK_INT >= 28 ? this.X.isScreenReaderFocusable() : k(1);
    }

    public boolean ac() {
        return Build.VERSION.SDK_INT >= 26 ? this.X.isShowingHintText() : k(4);
    }

    public boolean ad() {
        if (Build.VERSION.SDK_INT >= 28) {
            return this.X.isHeading();
        }
        if (k(2)) {
            return true;
        }
        C0032c E2 = E();
        return E2 != null && E2.e();
    }

    public boolean ae() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.X.refresh();
        }
        return false;
    }

    @ag
    public CharSequence af() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.X.getExtras().getCharSequence(P);
        }
        return null;
    }

    public c b(int i2) {
        if (Build.VERSION.SDK_INT >= 16) {
            return a((Object) this.X.focusSearch(i2));
        }
        return null;
    }

    @Deprecated
    public Object b() {
        return this.X;
    }

    public void b(Rect rect) {
        this.X.setBoundsInParent(rect);
    }

    public void b(View view) {
        this.X.setSource(view);
    }

    public void b(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.X.setSource(view, i2);
        }
    }

    public void b(CharSequence charSequence) {
        this.X.setClassName(charSequence);
    }

    public void b(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.X.setCollectionInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((b) obj).d);
        }
    }

    public void b(String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            this.X.setViewIdResourceName(str);
        }
    }

    public void b(boolean z2) {
        this.X.setChecked(z2);
    }

    public boolean b(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.X.removeAction((AccessibilityNodeInfo.AccessibilityAction) aVar.H);
        }
        return false;
    }

    public c c(int i2) {
        return a((Object) this.X.getChild(i2));
    }

    public List<c> c(String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = this.X.findAccessibilityNodeInfosByViewId(str);
            ArrayList arrayList = new ArrayList();
            for (AccessibilityNodeInfo accessibilityNodeInfo : findAccessibilityNodeInfosByViewId) {
                arrayList.add(a(accessibilityNodeInfo));
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    public void c(Rect rect) {
        this.X.getBoundsInScreen(rect);
    }

    public void c(View view) {
        this.X.addChild(view);
    }

    public void c(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.X.addChild(view, i2);
        }
    }

    public void c(CharSequence charSequence) {
        this.X.setText(charSequence);
    }

    public void c(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.X.setCollectionItemInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionItemInfo) ((C0032c) obj).a);
        }
    }

    public void c(boolean z2) {
        this.X.setFocusable(z2);
    }

    public int d() {
        return this.X.getWindowId();
    }

    public void d(int i2) {
        this.X.addAction(i2);
    }

    public void d(Rect rect) {
        this.X.setBoundsInScreen(rect);
    }

    public void d(CharSequence charSequence) {
        this.X.setContentDescription(charSequence);
    }

    public void d(boolean z2) {
        this.X.setFocused(z2);
    }

    public boolean d(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.X.removeChild(view);
        }
        return false;
    }

    public boolean d(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.X.removeChild(view, i2);
        }
        return false;
    }

    public int e() {
        return this.X.getChildCount();
    }

    public void e(View view) {
        this.X.setParent(view);
    }

    public void e(View view, int i2) {
        this.a = i2;
        if (Build.VERSION.SDK_INT >= 16) {
            this.X.setParent(view, i2);
        }
    }

    public void e(@ag CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.X.setHintText(charSequence);
        } else if (Build.VERSION.SDK_INT >= 19) {
            this.X.getExtras().putCharSequence(S, charSequence);
        }
    }

    public void e(boolean z2) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.X.setVisibleToUser(z2);
        }
    }

    public boolean e(int i2) {
        return this.X.performAction(i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            c cVar = (c) obj;
            AccessibilityNodeInfo accessibilityNodeInfo = this.X;
            if (accessibilityNodeInfo == null) {
                if (cVar.X != null) {
                    return false;
                }
            } else if (!accessibilityNodeInfo.equals(cVar.X)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int f() {
        return this.X.getActions();
    }

    public void f(int i2) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.X.setMovementGranularities(i2);
        }
    }

    public void f(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            this.X.setLabelFor(view);
        }
    }

    public void f(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 17) {
            this.X.setLabelFor(view, i2);
        }
    }

    public void f(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.X.setError(charSequence);
        }
    }

    public void f(boolean z2) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.X.setAccessibilityFocused(z2);
        }
    }

    public int g() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.X.getMovementGranularities();
        }
        return 0;
    }

    public void g(int i2) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.X.setLiveRegion(i2);
        }
    }

    public void g(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            this.X.setLabeledBy(view);
        }
    }

    public void g(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 17) {
            this.X.setLabeledBy(view, i2);
        }
    }

    public void g(@ag CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.X.setTooltipText(charSequence);
        } else if (Build.VERSION.SDK_INT >= 19) {
            this.X.getExtras().putCharSequence(R, charSequence);
        }
    }

    public void g(boolean z2) {
        this.X.setSelected(z2);
    }

    public c h() {
        return a((Object) this.X.getParent());
    }

    public void h(int i2) {
        if (Build.VERSION.SDK_INT >= 24) {
            this.X.setDrawingOrder(i2);
        }
    }

    public void h(View view) {
        if (Build.VERSION.SDK_INT >= 22) {
            this.X.setTraversalBefore(view);
        }
    }

    public void h(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 22) {
            this.X.setTraversalBefore(view, i2);
        }
    }

    public void h(@ag CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.X.setPaneTitle(charSequence);
        } else if (Build.VERSION.SDK_INT >= 19) {
            this.X.getExtras().putCharSequence(Q, charSequence);
        }
    }

    public void h(boolean z2) {
        this.X.setClickable(z2);
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.X;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public void i(int i2) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.X.setInputType(i2);
        }
    }

    public void i(View view) {
        if (Build.VERSION.SDK_INT >= 22) {
            this.X.setTraversalAfter(view);
        }
    }

    public void i(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 22) {
            this.X.setTraversalAfter(view, i2);
        }
    }

    public void i(@ag CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.X.getExtras().putCharSequence(P, charSequence);
        }
    }

    public void i(boolean z2) {
        this.X.setLongClickable(z2);
    }

    public boolean i() {
        return this.X.isCheckable();
    }

    public void j(int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.X.setMaxTextLength(i2);
        }
    }

    public void j(boolean z2) {
        this.X.setEnabled(z2);
    }

    public boolean j() {
        return this.X.isChecked();
    }

    public void k(boolean z2) {
        this.X.setPassword(z2);
    }

    public boolean k() {
        return this.X.isFocusable();
    }

    public void l(boolean z2) {
        this.X.setScrollable(z2);
    }

    public boolean l() {
        return this.X.isFocused();
    }

    public void m(boolean z2) {
        if (Build.VERSION.SDK_INT >= 24) {
            this.X.setImportantForAccessibility(z2);
        }
    }

    public boolean m() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.X.isVisibleToUser();
        }
        return false;
    }

    public void n(boolean z2) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.X.setContentInvalid(z2);
        }
    }

    public boolean n() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.X.isAccessibilityFocused();
        }
        return false;
    }

    public void o(boolean z2) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.X.setContextClickable(z2);
        }
    }

    public boolean o() {
        return this.X.isSelected();
    }

    public void p(boolean z2) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.X.setCanOpenPopup(z2);
        }
    }

    public boolean p() {
        return this.X.isClickable();
    }

    public void q(boolean z2) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.X.setDismissable(z2);
        }
    }

    public boolean q() {
        return this.X.isLongClickable();
    }

    public void r(boolean z2) {
        if (Build.VERSION.SDK_INT >= 18) {
            this.X.setEditable(z2);
        }
    }

    public boolean r() {
        return this.X.isEnabled();
    }

    public void s(boolean z2) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.X.setMultiLine(z2);
        }
    }

    public boolean s() {
        return this.X.isPassword();
    }

    public void t(boolean z2) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.X.setScreenReaderFocusable(z2);
        } else {
            a(1, z2);
        }
    }

    public boolean t() {
        return this.X.isScrollable();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        a(rect);
        sb.append("; boundsInParent: " + rect);
        c(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(v());
        sb.append("; className: ");
        sb.append(w());
        sb.append("; text: ");
        sb.append(x());
        sb.append("; contentDescription: ");
        sb.append(y());
        sb.append("; viewId: ");
        sb.append(A());
        sb.append("; checkable: ");
        sb.append(i());
        sb.append("; checked: ");
        sb.append(j());
        sb.append("; focusable: ");
        sb.append(k());
        sb.append("; focused: ");
        sb.append(l());
        sb.append("; selected: ");
        sb.append(o());
        sb.append("; clickable: ");
        sb.append(p());
        sb.append("; longClickable: ");
        sb.append(q());
        sb.append("; enabled: ");
        sb.append(r());
        sb.append("; password: ");
        sb.append(s());
        sb.append("; scrollable: " + t());
        sb.append("; [");
        int f2 = f();
        while (f2 != 0) {
            int numberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(f2);
            f2 &= numberOfTrailingZeros ^ (-1);
            sb.append(l(numberOfTrailingZeros));
            if (f2 != 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public void u(boolean z2) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.X.setShowingHintText(z2);
        } else {
            a(4, z2);
        }
    }

    public boolean u() {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.X.isImportantForAccessibility();
        }
        return true;
    }

    public CharSequence v() {
        return this.X.getPackageName();
    }

    public void v(boolean z2) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.X.setHeading(z2);
        } else {
            a(2, z2);
        }
    }

    public CharSequence w() {
        return this.X.getClassName();
    }

    public CharSequence x() {
        return this.X.getText();
    }

    public CharSequence y() {
        return this.X.getContentDescription();
    }

    public void z() {
        this.X.recycle();
    }
}
