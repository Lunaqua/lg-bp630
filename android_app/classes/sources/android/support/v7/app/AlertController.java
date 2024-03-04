package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.ag;
import android.support.v4.view.ab;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.a.a;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.lang.ref.WeakReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class AlertController {
    private int A;
    private CharSequence C;
    private Drawable D;
    private CharSequence E;
    private Drawable F;
    private CharSequence G;
    private Drawable H;
    private Drawable J;
    private ImageView K;
    private TextView L;
    private TextView M;
    private View N;
    private int O;
    private int P;
    private boolean Q;
    final f a;
    ListView b;
    Button c;
    Message d;
    Button e;
    Message f;
    Button g;
    Message h;
    NestedScrollView i;
    ListAdapter j;
    int l;
    int m;
    int n;
    int o;
    Handler p;
    private final Context q;
    private final Window r;
    private final int s;
    private CharSequence t;
    private CharSequence u;
    private View v;
    private int w;
    private int x;
    private int y;
    private int z;
    private boolean B = false;
    private int I = 0;
    int k = -1;
    private int R = 0;
    private final View.OnClickListener S = new View.OnClickListener() { // from class: android.support.v7.app.AlertController.1
        /* JADX WARN: Removed duplicated region for block: B:20:0x003a  */
        @Override // android.view.View.OnClickListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onClick(android.view.View r3) {
            /*
                r2 = this;
                android.support.v7.app.AlertController r0 = android.support.v7.app.AlertController.this
                android.widget.Button r0 = r0.c
                if (r3 != r0) goto L15
                android.support.v7.app.AlertController r0 = android.support.v7.app.AlertController.this
                android.os.Message r0 = r0.d
                if (r0 == 0) goto L15
                android.support.v7.app.AlertController r3 = android.support.v7.app.AlertController.this
                android.os.Message r3 = r3.d
            L10:
                android.os.Message r3 = android.os.Message.obtain(r3)
                goto L38
            L15:
                android.support.v7.app.AlertController r0 = android.support.v7.app.AlertController.this
                android.widget.Button r0 = r0.e
                if (r3 != r0) goto L26
                android.support.v7.app.AlertController r0 = android.support.v7.app.AlertController.this
                android.os.Message r0 = r0.f
                if (r0 == 0) goto L26
                android.support.v7.app.AlertController r3 = android.support.v7.app.AlertController.this
                android.os.Message r3 = r3.f
                goto L10
            L26:
                android.support.v7.app.AlertController r0 = android.support.v7.app.AlertController.this
                android.widget.Button r0 = r0.g
                if (r3 != r0) goto L37
                android.support.v7.app.AlertController r3 = android.support.v7.app.AlertController.this
                android.os.Message r3 = r3.h
                if (r3 == 0) goto L37
                android.support.v7.app.AlertController r3 = android.support.v7.app.AlertController.this
                android.os.Message r3 = r3.h
                goto L10
            L37:
                r3 = 0
            L38:
                if (r3 == 0) goto L3d
                r3.sendToTarget()
            L3d:
                android.support.v7.app.AlertController r3 = android.support.v7.app.AlertController.this
                android.os.Handler r3 = r3.p
                r0 = 1
                android.support.v7.app.AlertController r1 = android.support.v7.app.AlertController.this
                android.support.v7.app.f r1 = r1.a
                android.os.Message r3 = r3.obtainMessage(r0, r1)
                r3.sendToTarget()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AlertController.AnonymousClass1.onClick(android.view.View):void");
        }
    };

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class RecycleListView extends ListView {
        private final int a;
        private final int b;

        public RecycleListView(Context context) {
            this(context, null);
        }

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.l.RecycleListView);
            this.b = obtainStyledAttributes.getDimensionPixelOffset(a.l.RecycleListView_paddingBottomNoButtons, -1);
            this.a = obtainStyledAttributes.getDimensionPixelOffset(a.l.RecycleListView_paddingTopNoTitle, -1);
        }

        public void a(boolean z, boolean z2) {
            if (z2 && z) {
                return;
            }
            setPadding(getPaddingLeft(), z ? getPaddingTop() : this.a, getPaddingRight(), z2 ? getPaddingBottom() : this.b);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a {
        public int A;
        public int B;
        public int C;
        public int D;
        public boolean[] F;
        public boolean G;
        public boolean H;
        public DialogInterface.OnMultiChoiceClickListener J;
        public Cursor K;
        public String L;
        public String M;
        public boolean N;
        public AdapterView.OnItemSelectedListener O;
        public InterfaceC0037a P;
        public final Context a;
        public final LayoutInflater b;
        public Drawable d;
        public CharSequence f;
        public View g;
        public CharSequence h;
        public CharSequence i;
        public Drawable j;
        public DialogInterface.OnClickListener k;
        public CharSequence l;
        public Drawable m;
        public DialogInterface.OnClickListener n;
        public CharSequence o;
        public Drawable p;
        public DialogInterface.OnClickListener q;
        public DialogInterface.OnCancelListener s;
        public DialogInterface.OnDismissListener t;
        public DialogInterface.OnKeyListener u;
        public CharSequence[] v;
        public ListAdapter w;
        public DialogInterface.OnClickListener x;
        public int y;
        public View z;
        public int c = 0;
        public int e = 0;
        public boolean E = false;
        public int I = -1;
        public boolean Q = true;
        public boolean r = true;

        /* renamed from: android.support.v7.app.AlertController$a$a  reason: collision with other inner class name */
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public interface InterfaceC0037a {
            void a(ListView listView);
        }

        public a(Context context) {
            this.a = context;
            this.b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x008f  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0096  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x009a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void b(final android.support.v7.app.AlertController r12) {
            /*
                r11 = this;
                android.view.LayoutInflater r0 = r11.b
                int r1 = r12.l
                r2 = 0
                android.view.View r0 = r0.inflate(r1, r2)
                android.support.v7.app.AlertController$RecycleListView r0 = (android.support.v7.app.AlertController.RecycleListView) r0
                boolean r1 = r11.G
                r8 = 1
                if (r1 == 0) goto L33
                android.database.Cursor r4 = r11.K
                if (r4 != 0) goto L26
                android.support.v7.app.AlertController$a$1 r9 = new android.support.v7.app.AlertController$a$1
                android.content.Context r3 = r11.a
                int r4 = r12.m
                r5 = 16908308(0x1020014, float:2.3877285E-38)
                java.lang.CharSequence[] r6 = r11.v
                r1 = r9
                r2 = r11
                r7 = r0
                r1.<init>(r3, r4, r5, r6)
                goto L67
            L26:
                android.support.v7.app.AlertController$a$2 r9 = new android.support.v7.app.AlertController$a$2
                android.content.Context r3 = r11.a
                r5 = 0
                r1 = r9
                r2 = r11
                r6 = r0
                r7 = r12
                r1.<init>(r3, r4, r5)
                goto L67
            L33:
                boolean r1 = r11.H
                if (r1 == 0) goto L3a
                int r1 = r12.n
                goto L3c
            L3a:
                int r1 = r12.o
            L3c:
                r4 = r1
                android.database.Cursor r5 = r11.K
                r1 = 16908308(0x1020014, float:2.3877285E-38)
                if (r5 == 0) goto L59
                android.widget.SimpleCursorAdapter r9 = new android.widget.SimpleCursorAdapter
                android.content.Context r3 = r11.a
                java.lang.String[] r6 = new java.lang.String[r8]
                java.lang.String r2 = r11.L
                r7 = 0
                r6[r7] = r2
                int[] r10 = new int[r8]
                r10[r7] = r1
                r2 = r9
                r7 = r10
                r2.<init>(r3, r4, r5, r6, r7)
                goto L67
            L59:
                android.widget.ListAdapter r9 = r11.w
                if (r9 == 0) goto L5e
                goto L67
            L5e:
                android.support.v7.app.AlertController$c r9 = new android.support.v7.app.AlertController$c
                android.content.Context r2 = r11.a
                java.lang.CharSequence[] r3 = r11.v
                r9.<init>(r2, r4, r1, r3)
            L67:
                android.support.v7.app.AlertController$a$a r1 = r11.P
                if (r1 == 0) goto L6e
                r1.a(r0)
            L6e:
                r12.j = r9
                int r1 = r11.I
                r12.k = r1
                android.content.DialogInterface$OnClickListener r1 = r11.x
                if (r1 == 0) goto L81
                android.support.v7.app.AlertController$a$3 r1 = new android.support.v7.app.AlertController$a$3
                r1.<init>()
            L7d:
                r0.setOnItemClickListener(r1)
                goto L8b
            L81:
                android.content.DialogInterface$OnMultiChoiceClickListener r1 = r11.J
                if (r1 == 0) goto L8b
                android.support.v7.app.AlertController$a$4 r1 = new android.support.v7.app.AlertController$a$4
                r1.<init>()
                goto L7d
            L8b:
                android.widget.AdapterView$OnItemSelectedListener r1 = r11.O
                if (r1 == 0) goto L92
                r0.setOnItemSelectedListener(r1)
            L92:
                boolean r1 = r11.H
                if (r1 == 0) goto L9a
                r0.setChoiceMode(r8)
                goto La2
            L9a:
                boolean r1 = r11.G
                if (r1 == 0) goto La2
                r1 = 2
                r0.setChoiceMode(r1)
            La2:
                r12.b = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AlertController.a.b(android.support.v7.app.AlertController):void");
        }

        public void a(AlertController alertController) {
            View view = this.g;
            if (view != null) {
                alertController.b(view);
            } else {
                CharSequence charSequence = this.f;
                if (charSequence != null) {
                    alertController.a(charSequence);
                }
                Drawable drawable = this.d;
                if (drawable != null) {
                    alertController.a(drawable);
                }
                int i = this.c;
                if (i != 0) {
                    alertController.c(i);
                }
                int i2 = this.e;
                if (i2 != 0) {
                    alertController.c(alertController.d(i2));
                }
            }
            CharSequence charSequence2 = this.h;
            if (charSequence2 != null) {
                alertController.b(charSequence2);
            }
            if (this.i != null || this.j != null) {
                alertController.a(-1, this.i, this.k, (Message) null, this.j);
            }
            if (this.l != null || this.m != null) {
                alertController.a(-2, this.l, this.n, (Message) null, this.m);
            }
            if (this.o != null || this.p != null) {
                alertController.a(-3, this.o, this.q, (Message) null, this.p);
            }
            if (this.v != null || this.K != null || this.w != null) {
                b(alertController);
            }
            View view2 = this.z;
            if (view2 != null) {
                if (this.E) {
                    alertController.a(view2, this.A, this.B, this.C, this.D);
                    return;
                } else {
                    alertController.c(view2);
                    return;
                }
            }
            int i3 = this.y;
            if (i3 != 0) {
                alertController.a(i3);
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static final class b extends Handler {
        private static final int a = 1;
        private WeakReference<DialogInterface> b;

        public b(DialogInterface dialogInterface) {
            this.b = new WeakReference<>(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == -3 || i == -2 || i == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick(this.b.get(), message.what);
            } else if (i != 1) {
            } else {
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class c extends ArrayAdapter<CharSequence> {
        public c(Context context, int i, int i2, CharSequence[] charSequenceArr) {
            super(context, i, i2, charSequenceArr);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    public AlertController(Context context, f fVar, Window window) {
        this.q = context;
        this.a = fVar;
        this.r = window;
        this.p = new b(fVar);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, a.l.AlertDialog, a.b.alertDialogStyle, 0);
        this.O = obtainStyledAttributes.getResourceId(a.l.AlertDialog_android_layout, 0);
        this.P = obtainStyledAttributes.getResourceId(a.l.AlertDialog_buttonPanelSideLayout, 0);
        this.l = obtainStyledAttributes.getResourceId(a.l.AlertDialog_listLayout, 0);
        this.m = obtainStyledAttributes.getResourceId(a.l.AlertDialog_multiChoiceItemLayout, 0);
        this.n = obtainStyledAttributes.getResourceId(a.l.AlertDialog_singleChoiceItemLayout, 0);
        this.o = obtainStyledAttributes.getResourceId(a.l.AlertDialog_listItemLayout, 0);
        this.Q = obtainStyledAttributes.getBoolean(a.l.AlertDialog_showTitle, true);
        this.s = obtainStyledAttributes.getDimensionPixelSize(a.l.AlertDialog_buttonIconDimen, 0);
        obtainStyledAttributes.recycle();
        fVar.e(1);
    }

    @ag
    private ViewGroup a(@ag View view, @ag View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    static void a(View view, View view2, View view3) {
        if (view2 != null) {
            view2.setVisibility(view.canScrollVertically(-1) ? 0 : 4);
        }
        if (view3 != null) {
            view3.setVisibility(view.canScrollVertically(1) ? 0 : 4);
        }
    }

    private void a(ViewGroup viewGroup) {
        View view = this.v;
        if (view == null) {
            view = this.w != 0 ? LayoutInflater.from(this.q).inflate(this.w, viewGroup, false) : null;
        }
        boolean z = view != null;
        if (!z || !a(view)) {
            this.r.setFlags(131072, 131072);
        }
        if (!z) {
            viewGroup.setVisibility(8);
            return;
        }
        FrameLayout frameLayout = (FrameLayout) this.r.findViewById(a.g.custom);
        frameLayout.addView(view, new ViewGroup.LayoutParams(-1, -1));
        if (this.B) {
            frameLayout.setPadding(this.x, this.y, this.z, this.A);
        }
        if (this.b != null) {
            ((LinearLayoutCompat.LayoutParams) viewGroup.getLayoutParams()).g = 0.0f;
        }
    }

    private void a(ViewGroup viewGroup, View view, int i, int i2) {
        final View findViewById = this.r.findViewById(a.g.scrollIndicatorUp);
        View findViewById2 = this.r.findViewById(a.g.scrollIndicatorDown);
        if (Build.VERSION.SDK_INT >= 23) {
            ab.b(view, i, i2);
            if (findViewById != null) {
                viewGroup.removeView(findViewById);
            }
            if (findViewById2 != null) {
                viewGroup.removeView(findViewById2);
                return;
            }
            return;
        }
        final View view2 = null;
        if (findViewById != null && (i & 1) == 0) {
            viewGroup.removeView(findViewById);
            findViewById = null;
        }
        if (findViewById2 == null || (i & 2) != 0) {
            view2 = findViewById2;
        } else {
            viewGroup.removeView(findViewById2);
        }
        if (findViewById == null && view2 == null) {
            return;
        }
        if (this.u != null) {
            this.i.setOnScrollChangeListener(new NestedScrollView.b() { // from class: android.support.v7.app.AlertController.2
                @Override // android.support.v4.widget.NestedScrollView.b
                public void a(NestedScrollView nestedScrollView, int i3, int i4, int i5, int i6) {
                    AlertController.a(nestedScrollView, findViewById, view2);
                }
            });
            this.i.post(new Runnable() { // from class: android.support.v7.app.AlertController.3
                @Override // java.lang.Runnable
                public void run() {
                    AlertController.a(AlertController.this.i, findViewById, view2);
                }
            });
            return;
        }
        ListView listView = this.b;
        if (listView != null) {
            listView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: android.support.v7.app.AlertController.4
                @Override // android.widget.AbsListView.OnScrollListener
                public void onScroll(AbsListView absListView, int i3, int i4, int i5) {
                    AlertController.a(absListView, findViewById, view2);
                }

                @Override // android.widget.AbsListView.OnScrollListener
                public void onScrollStateChanged(AbsListView absListView, int i3) {
                }
            });
            this.b.post(new Runnable() { // from class: android.support.v7.app.AlertController.5
                @Override // java.lang.Runnable
                public void run() {
                    AlertController.a(AlertController.this.b, findViewById, view2);
                }
            });
            return;
        }
        if (findViewById != null) {
            viewGroup.removeView(findViewById);
        }
        if (view2 != null) {
            viewGroup.removeView(view2);
        }
    }

    private void a(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }

    private static boolean a(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(a.b.alertDialogCenterButtons, typedValue, true);
        return typedValue.data != 0;
    }

    static boolean a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            while (childCount > 0) {
                childCount--;
                if (a(viewGroup.getChildAt(childCount))) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private void b(ViewGroup viewGroup) {
        if (this.N != null) {
            viewGroup.addView(this.N, 0, new ViewGroup.LayoutParams(-1, -2));
            this.r.findViewById(a.g.title_template).setVisibility(8);
            return;
        }
        this.K = (ImageView) this.r.findViewById(16908294);
        if (!(!TextUtils.isEmpty(this.t)) || !this.Q) {
            this.r.findViewById(a.g.title_template).setVisibility(8);
            this.K.setVisibility(8);
            viewGroup.setVisibility(8);
            return;
        }
        this.L = (TextView) this.r.findViewById(a.g.alertTitle);
        this.L.setText(this.t);
        int i = this.I;
        if (i != 0) {
            this.K.setImageResource(i);
            return;
        }
        Drawable drawable = this.J;
        if (drawable != null) {
            this.K.setImageDrawable(drawable);
            return;
        }
        this.L.setPadding(this.K.getPaddingLeft(), this.K.getPaddingTop(), this.K.getPaddingRight(), this.K.getPaddingBottom());
        this.K.setVisibility(8);
    }

    private int c() {
        int i = this.P;
        return (i != 0 && this.R == 1) ? i : this.O;
    }

    private void c(ViewGroup viewGroup) {
        this.i = (NestedScrollView) this.r.findViewById(a.g.scrollView);
        this.i.setFocusable(false);
        this.i.setNestedScrollingEnabled(false);
        this.M = (TextView) viewGroup.findViewById(16908299);
        TextView textView = this.M;
        if (textView == null) {
            return;
        }
        CharSequence charSequence = this.u;
        if (charSequence != null) {
            textView.setText(charSequence);
            return;
        }
        textView.setVisibility(8);
        this.i.removeView(this.M);
        if (this.b == null) {
            viewGroup.setVisibility(8);
            return;
        }
        ViewGroup viewGroup2 = (ViewGroup) this.i.getParent();
        int indexOfChild = viewGroup2.indexOfChild(this.i);
        viewGroup2.removeViewAt(indexOfChild);
        viewGroup2.addView(this.b, indexOfChild, new ViewGroup.LayoutParams(-1, -1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void d() {
        View findViewById;
        ListAdapter listAdapter;
        View findViewById2;
        View findViewById3 = this.r.findViewById(a.g.parentPanel);
        View findViewById4 = findViewById3.findViewById(a.g.topPanel);
        View findViewById5 = findViewById3.findViewById(a.g.contentPanel);
        View findViewById6 = findViewById3.findViewById(a.g.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) findViewById3.findViewById(a.g.customPanel);
        a(viewGroup);
        View findViewById7 = viewGroup.findViewById(a.g.topPanel);
        View findViewById8 = viewGroup.findViewById(a.g.contentPanel);
        View findViewById9 = viewGroup.findViewById(a.g.buttonPanel);
        ViewGroup a2 = a(findViewById7, findViewById4);
        ViewGroup a3 = a(findViewById8, findViewById5);
        ViewGroup a4 = a(findViewById9, findViewById6);
        c(a3);
        d(a4);
        b(a2);
        boolean z = (viewGroup == null || viewGroup.getVisibility() == 8) ? false : true;
        boolean z2 = (a2 == null || a2.getVisibility() == 8) ? 0 : 1;
        boolean z3 = (a4 == null || a4.getVisibility() == 8) ? false : true;
        if (!z3 && a3 != null && (findViewById2 = a3.findViewById(a.g.textSpacerNoButtons)) != null) {
            findViewById2.setVisibility(0);
        }
        if (z2) {
            NestedScrollView nestedScrollView = this.i;
            if (nestedScrollView != null) {
                nestedScrollView.setClipToPadding(true);
            }
            View view = null;
            if (this.u != null || this.b != null) {
                view = a2.findViewById(a.g.titleDividerNoCustom);
            }
            if (view != null) {
                view.setVisibility(0);
            }
        } else if (a3 != null && (findViewById = a3.findViewById(a.g.textSpacerNoTitle)) != null) {
            findViewById.setVisibility(0);
        }
        ListView listView = this.b;
        if (listView instanceof RecycleListView) {
            ((RecycleListView) listView).a(z2, z3);
        }
        if (!z) {
            View view2 = this.b;
            if (view2 == null) {
                view2 = this.i;
            }
            if (view2 != null) {
                a(a3, view2, z2 | (z3 ? 2 : 0), 3);
            }
        }
        ListView listView2 = this.b;
        if (listView2 == null || (listAdapter = this.j) == null) {
            return;
        }
        listView2.setAdapter(listAdapter);
        int i = this.k;
        if (i > -1) {
            listView2.setItemChecked(i, true);
            listView2.setSelection(i);
        }
    }

    private void d(ViewGroup viewGroup) {
        boolean z;
        Button button;
        this.c = (Button) viewGroup.findViewById(16908313);
        this.c.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.C) && this.D == null) {
            this.c.setVisibility(8);
            z = false;
        } else {
            this.c.setText(this.C);
            Drawable drawable = this.D;
            if (drawable != null) {
                int i = this.s;
                drawable.setBounds(0, 0, i, i);
                this.c.setCompoundDrawables(this.D, null, null, null);
            }
            this.c.setVisibility(0);
            z = true;
        }
        this.e = (Button) viewGroup.findViewById(16908314);
        this.e.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.E) && this.F == null) {
            this.e.setVisibility(8);
        } else {
            this.e.setText(this.E);
            Drawable drawable2 = this.F;
            if (drawable2 != null) {
                int i2 = this.s;
                drawable2.setBounds(0, 0, i2, i2);
                this.e.setCompoundDrawables(this.F, null, null, null);
            }
            this.e.setVisibility(0);
            z |= true;
        }
        this.g = (Button) viewGroup.findViewById(16908315);
        this.g.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.G) && this.H == null) {
            this.g.setVisibility(8);
        } else {
            this.g.setText(this.G);
            Drawable drawable3 = this.D;
            if (drawable3 != null) {
                int i3 = this.s;
                drawable3.setBounds(0, 0, i3, i3);
                this.c.setCompoundDrawables(this.D, null, null, null);
            }
            this.g.setVisibility(0);
            z |= true;
        }
        if (a(this.q)) {
            if (z) {
                button = this.c;
            } else if (z) {
                button = this.e;
            } else if (z) {
                button = this.g;
            }
            a(button);
        }
        if (z) {
            return;
        }
        viewGroup.setVisibility(8);
    }

    public void a() {
        this.a.setContentView(c());
        d();
    }

    public void a(int i) {
        this.v = null;
        this.w = i;
        this.B = false;
    }

    public void a(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        if (message == null && onClickListener != null) {
            message = this.p.obtainMessage(i, onClickListener);
        }
        if (i == -3) {
            this.G = charSequence;
            this.h = message;
            this.H = drawable;
        } else if (i == -2) {
            this.E = charSequence;
            this.f = message;
            this.F = drawable;
        } else if (i != -1) {
            throw new IllegalArgumentException("Button does not exist");
        } else {
            this.C = charSequence;
            this.d = message;
            this.D = drawable;
        }
    }

    public void a(Drawable drawable) {
        this.J = drawable;
        this.I = 0;
        ImageView imageView = this.K;
        if (imageView != null) {
            if (drawable == null) {
                imageView.setVisibility(8);
                return;
            }
            imageView.setVisibility(0);
            this.K.setImageDrawable(drawable);
        }
    }

    public void a(View view, int i, int i2, int i3, int i4) {
        this.v = view;
        this.w = 0;
        this.B = true;
        this.x = i;
        this.y = i2;
        this.z = i3;
        this.A = i4;
    }

    public void a(CharSequence charSequence) {
        this.t = charSequence;
        TextView textView = this.L;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public boolean a(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.i;
        return nestedScrollView != null && nestedScrollView.a(keyEvent);
    }

    public ListView b() {
        return this.b;
    }

    public void b(int i) {
        this.R = i;
    }

    public void b(View view) {
        this.N = view;
    }

    public void b(CharSequence charSequence) {
        this.u = charSequence;
        TextView textView = this.M;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public boolean b(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.i;
        return nestedScrollView != null && nestedScrollView.a(keyEvent);
    }

    public void c(int i) {
        this.J = null;
        this.I = i;
        ImageView imageView = this.K;
        if (imageView != null) {
            if (i == 0) {
                imageView.setVisibility(8);
                return;
            }
            imageView.setVisibility(0);
            this.K.setImageResource(this.I);
        }
    }

    public void c(View view) {
        this.v = view;
        this.w = 0;
        this.B = false;
    }

    public int d(int i) {
        TypedValue typedValue = new TypedValue();
        this.q.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId;
    }

    public Button e(int i) {
        if (i != -3) {
            if (i != -2) {
                if (i != -1) {
                    return null;
                }
                return this.c;
            }
            return this.e;
        }
        return this.g;
    }
}
