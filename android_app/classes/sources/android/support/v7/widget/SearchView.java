package android.support.v7.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.an;
import android.support.v4.view.AbsSavedState;
import android.support.v7.a.a;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class SearchView extends LinearLayoutCompat implements android.support.v7.view.c {
    static final boolean a = false;
    static final String b = "SearchView";
    static final a q = new a();
    private static final String s = "nm";
    private int[] A;
    private int[] B;
    private final ImageView C;
    private final Drawable D;
    private final int E;
    private final int F;
    private final Intent G;
    private final Intent H;
    private final CharSequence I;
    private c J;
    private b K;
    private d L;
    private View.OnClickListener M;
    private boolean N;
    private boolean O;
    private boolean P;
    private CharSequence Q;
    private boolean R;
    private boolean S;
    private int T;
    private boolean U;
    private CharSequence V;
    private CharSequence W;
    private boolean aa;
    private int ab;
    private Bundle ac;
    private final Runnable ad;
    private Runnable ae;
    private final WeakHashMap<String, Drawable.ConstantState> af;
    private final View.OnClickListener ag;
    private final TextView.OnEditorActionListener ah;
    private final AdapterView.OnItemClickListener ai;
    private final AdapterView.OnItemSelectedListener aj;
    private TextWatcher ak;
    final SearchAutoComplete c;
    final ImageView d;
    final ImageView k;
    final ImageView l;
    final ImageView m;
    View.OnFocusChangeListener n;
    android.support.v4.widget.f o;
    SearchableInfo p;
    View.OnKeyListener r;
    private final View t;
    private final View u;
    private final View v;
    private final View w;
    private e x;
    private Rect y;
    private Rect z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: android.support.v7.widget.SearchView.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        boolean a;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = ((Boolean) parcel.readValue(null)).booleanValue();
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.a + "}";
        }

        @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.a));
        }
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
        final Runnable a;
        private int b;
        private SearchView c;
        private boolean d;

        public SearchAutoComplete(Context context) {
            this(context, null);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, a.b.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.a = new Runnable() { // from class: android.support.v7.widget.SearchView.SearchAutoComplete.1
                @Override // java.lang.Runnable
                public void run() {
                    SearchAutoComplete.this.b();
                }
            };
            this.b = getThreshold();
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            if (i < 960 || i2 < 720 || configuration.orientation != 2) {
                if (i < 600) {
                    return (i < 640 || i2 < 480) ? 160 : 192;
                }
                return 192;
            }
            return 256;
        }

        boolean a() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        void b() {
            if (this.d) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.d = false;
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            return this.b <= 0 || super.enoughToFilter();
        }

        @Override // android.support.v7.widget.AppCompatAutoCompleteTextView, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.d) {
                removeCallbacks(this.a);
                post(this.a);
            }
            return onCreateInputConnection;
        }

        @Override // android.view.View
        protected void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        protected void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            this.c.o();
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                } else if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.c.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.c.hasFocus() && getVisibility() == 0) {
                this.d = true;
                if (SearchView.a(getContext())) {
                    SearchView.q.a(this, true);
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        protected void replaceText(CharSequence charSequence) {
        }

        void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.d = false;
                removeCallbacks(this.a);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (!inputMethodManager.isActive(this)) {
                this.d = true;
            } else {
                this.d = false;
                removeCallbacks(this.a);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        void setSearchView(SearchView searchView) {
            this.c = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i) {
            super.setThreshold(i);
            this.b = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a {
        private Method a;
        private Method b;
        private Method c;
        private Method d;

        a() {
            try {
                this.a = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.a.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                this.b = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.b.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                this.c = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.c.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
        }

        void a(AutoCompleteTextView autoCompleteTextView) {
            Method method = this.a;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        void a(AutoCompleteTextView autoCompleteTextView, boolean z) {
            Method method = this.c;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, Boolean.valueOf(z));
                } catch (Exception unused) {
                }
            }
        }

        void b(AutoCompleteTextView autoCompleteTextView) {
            Method method = this.b;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface b {
        boolean a();
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface c {
        boolean a(String str);

        boolean b(String str);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface d {
        boolean a(int i);

        boolean b(int i);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class e extends TouchDelegate {
        private final View a;
        private final Rect b;
        private final Rect c;
        private final Rect d;
        private final int e;
        private boolean f;

        public e(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.b = new Rect();
            this.d = new Rect();
            this.c = new Rect();
            a(rect, rect2);
            this.a = view;
        }

        public void a(Rect rect, Rect rect2) {
            this.b.set(rect);
            this.d.set(rect);
            Rect rect3 = this.d;
            int i = this.e;
            rect3.inset(-i, -i);
            this.c.set(rect2);
        }

        @Override // android.view.TouchDelegate
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z;
            float f;
            int i;
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            boolean z2 = true;
            if (action == 0) {
                if (this.b.contains(x, y)) {
                    this.f = true;
                    z = true;
                }
                z = false;
            } else if (action == 1 || action == 2) {
                z = this.f;
                if (z && !this.d.contains(x, y)) {
                    z2 = false;
                }
            } else {
                if (action == 3) {
                    z = this.f;
                    this.f = false;
                }
                z = false;
            }
            if (z) {
                if (!z2 || this.c.contains(x, y)) {
                    f = x - this.c.left;
                    i = y - this.c.top;
                } else {
                    f = this.a.getWidth() / 2;
                    i = this.a.getHeight() / 2;
                }
                motionEvent.setLocation(f, i);
                return this.a.dispatchTouchEvent(motionEvent);
            }
            return false;
        }
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.b.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.y = new Rect();
        this.z = new Rect();
        this.A = new int[2];
        this.B = new int[2];
        this.ad = new Runnable() { // from class: android.support.v7.widget.SearchView.1
            @Override // java.lang.Runnable
            public void run() {
                SearchView.this.g();
            }
        };
        this.ae = new Runnable() { // from class: android.support.v7.widget.SearchView.3
            @Override // java.lang.Runnable
            public void run() {
                if (SearchView.this.o == null || !(SearchView.this.o instanceof ap)) {
                    return;
                }
                SearchView.this.o.a((Cursor) null);
            }
        };
        this.af = new WeakHashMap<>();
        this.ag = new View.OnClickListener() { // from class: android.support.v7.widget.SearchView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view == SearchView.this.d) {
                    SearchView.this.m();
                } else if (view == SearchView.this.l) {
                    SearchView.this.i();
                } else if (view == SearchView.this.k) {
                    SearchView.this.h();
                } else if (view == SearchView.this.m) {
                    SearchView.this.n();
                } else if (view == SearchView.this.c) {
                    SearchView.this.q();
                }
            }
        };
        this.r = new View.OnKeyListener() { // from class: android.support.v7.widget.SearchView.7
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i2, KeyEvent keyEvent) {
                if (SearchView.this.p == null) {
                    return false;
                }
                if (!SearchView.this.c.isPopupShowing() || SearchView.this.c.getListSelection() == -1) {
                    if (!SearchView.this.c.a() && keyEvent.hasNoModifiers() && keyEvent.getAction() == 1 && i2 == 66) {
                        view.cancelLongPress();
                        SearchView searchView = SearchView.this;
                        searchView.a(0, (String) null, searchView.c.getText().toString());
                        return true;
                    }
                    return false;
                }
                return SearchView.this.a(view, i2, keyEvent);
            }
        };
        this.ah = new TextView.OnEditorActionListener() { // from class: android.support.v7.widget.SearchView.8
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
                SearchView.this.h();
                return true;
            }
        };
        this.ai = new AdapterView.OnItemClickListener() { // from class: android.support.v7.widget.SearchView.9
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                SearchView.this.a(i2, 0, (String) null);
            }
        };
        this.aj = new AdapterView.OnItemSelectedListener() { // from class: android.support.v7.widget.SearchView.10
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
                SearchView.this.a(i2);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        this.ak = new TextWatcher() { // from class: android.support.v7.widget.SearchView.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                SearchView.this.b(charSequence);
            }
        };
        av a2 = av.a(context, attributeSet, a.l.SearchView, i, 0);
        LayoutInflater.from(context).inflate(a2.g(a.l.SearchView_layout, a.i.abc_search_view), (ViewGroup) this, true);
        this.c = (SearchAutoComplete) findViewById(a.g.search_src_text);
        this.c.setSearchView(this);
        this.t = findViewById(a.g.search_edit_frame);
        this.u = findViewById(a.g.search_plate);
        this.v = findViewById(a.g.submit_area);
        this.d = (ImageView) findViewById(a.g.search_button);
        this.k = (ImageView) findViewById(a.g.search_go_btn);
        this.l = (ImageView) findViewById(a.g.search_close_btn);
        this.m = (ImageView) findViewById(a.g.search_voice_btn);
        this.C = (ImageView) findViewById(a.g.search_mag_icon);
        android.support.v4.view.ab.a(this.u, a2.a(a.l.SearchView_queryBackground));
        android.support.v4.view.ab.a(this.v, a2.a(a.l.SearchView_submitBackground));
        this.d.setImageDrawable(a2.a(a.l.SearchView_searchIcon));
        this.k.setImageDrawable(a2.a(a.l.SearchView_goIcon));
        this.l.setImageDrawable(a2.a(a.l.SearchView_closeIcon));
        this.m.setImageDrawable(a2.a(a.l.SearchView_voiceIcon));
        this.C.setImageDrawable(a2.a(a.l.SearchView_searchIcon));
        this.D = a2.a(a.l.SearchView_searchHintIcon);
        ax.a(this.d, getResources().getString(a.j.abc_searchview_description_search));
        this.E = a2.g(a.l.SearchView_suggestionRowLayout, a.i.abc_search_dropdown_item_icons_2line);
        this.F = a2.g(a.l.SearchView_commitIcon, 0);
        this.d.setOnClickListener(this.ag);
        this.l.setOnClickListener(this.ag);
        this.k.setOnClickListener(this.ag);
        this.m.setOnClickListener(this.ag);
        this.c.setOnClickListener(this.ag);
        this.c.addTextChangedListener(this.ak);
        this.c.setOnEditorActionListener(this.ah);
        this.c.setOnItemClickListener(this.ai);
        this.c.setOnItemSelectedListener(this.aj);
        this.c.setOnKeyListener(this.r);
        this.c.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: android.support.v7.widget.SearchView.4
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (SearchView.this.n != null) {
                    SearchView.this.n.onFocusChange(SearchView.this, z);
                }
            }
        });
        setIconifiedByDefault(a2.a(a.l.SearchView_iconifiedByDefault, true));
        int e2 = a2.e(a.l.SearchView_android_maxWidth, -1);
        if (e2 != -1) {
            setMaxWidth(e2);
        }
        this.I = a2.d(a.l.SearchView_defaultQueryHint);
        this.Q = a2.d(a.l.SearchView_queryHint);
        int a3 = a2.a(a.l.SearchView_android_imeOptions, -1);
        if (a3 != -1) {
            setImeOptions(a3);
        }
        int a4 = a2.a(a.l.SearchView_android_inputType, -1);
        if (a4 != -1) {
            setInputType(a4);
        }
        setFocusable(a2.a(a.l.SearchView_android_focusable, true));
        a2.e();
        this.G = new Intent("android.speech.action.WEB_SEARCH");
        this.G.addFlags(268435456);
        this.G.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        this.H = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.H.addFlags(268435456);
        this.w = findViewById(this.c.getDropDownAnchor());
        View view = this.w;
        if (view != null) {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: android.support.v7.widget.SearchView.5
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                    SearchView.this.p();
                }
            });
        }
        a(this.N);
        w();
    }

    private Intent a(Intent intent, SearchableInfo searchableInfo) {
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        intent2.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
        return intent2;
    }

    private Intent a(Cursor cursor, int i, String str) {
        int i2;
        String a2;
        try {
            String a3 = ap.a(cursor, "suggest_intent_action");
            if (a3 == null) {
                a3 = this.p.getSuggestIntentAction();
            }
            if (a3 == null) {
                a3 = "android.intent.action.SEARCH";
            }
            String str2 = a3;
            String a4 = ap.a(cursor, "suggest_intent_data");
            if (a4 == null) {
                a4 = this.p.getSuggestIntentData();
            }
            if (a4 != null && (a2 = ap.a(cursor, "suggest_intent_data_id")) != null) {
                a4 = a4 + "/" + Uri.encode(a2);
            }
            return a(str2, a4 == null ? null : Uri.parse(a4), ap.a(cursor, "suggest_intent_extra_data"), ap.a(cursor, "suggest_intent_query"), i, str);
        } catch (RuntimeException e2) {
            try {
                i2 = cursor.getPosition();
            } catch (RuntimeException unused) {
                i2 = -1;
            }
            Log.w(b, "Search suggestions cursor at row " + i2 + " returned exception.", e2);
            return null;
        }
    }

    private Intent a(String str, Uri uri, String str2, String str3, int i, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.W);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.ac;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i != 0) {
            intent.putExtra("action_key", i);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.p.getSearchActivity());
        return intent;
    }

    private void a(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            getContext().startActivity(intent);
        } catch (RuntimeException e2) {
            Log.e(b, "Failed launch activity: " + intent, e2);
        }
    }

    private void a(View view, Rect rect) {
        view.getLocationInWindow(this.A);
        getLocationInWindow(this.B);
        int[] iArr = this.A;
        int i = iArr[1];
        int[] iArr2 = this.B;
        int i2 = i - iArr2[1];
        int i3 = iArr[0] - iArr2[0];
        rect.set(i3, i2, view.getWidth() + i3, view.getHeight() + i2);
    }

    private void a(boolean z) {
        this.O = z;
        int i = 8;
        int i2 = z ? 0 : 8;
        boolean z2 = !TextUtils.isEmpty(this.c.getText());
        this.d.setVisibility(i2);
        b(z2);
        this.t.setVisibility(z ? 8 : 0);
        if (this.C.getDrawable() != null && !this.N) {
            i = 0;
        }
        this.C.setVisibility(i);
        u();
        c(z2 ? false : true);
        t();
    }

    static boolean a(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    private Intent b(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1073741824);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.ac;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        Resources resources = getResources();
        String string = searchableInfo.getVoiceLanguageModeId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageModeId()) : "free_form";
        String string2 = searchableInfo.getVoicePromptTextId() != 0 ? resources.getString(searchableInfo.getVoicePromptTextId()) : null;
        String string3 = searchableInfo.getVoiceLanguageId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageId()) : null;
        int voiceMaxResults = searchableInfo.getVoiceMaxResults() != 0 ? searchableInfo.getVoiceMaxResults() : 1;
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", string);
        intent3.putExtra("android.speech.extra.PROMPT", string2);
        intent3.putExtra("android.speech.extra.LANGUAGE", string3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", voiceMaxResults);
        intent3.putExtra("calling_package", searchActivity != null ? searchActivity.flattenToShortString() : null);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    private void b(boolean z) {
        this.k.setVisibility((this.P && s() && hasFocus() && (z || !this.U)) ? 0 : 8);
    }

    private boolean b(int i, int i2, String str) {
        Cursor a2 = this.o.a();
        if (a2 == null || !a2.moveToPosition(i)) {
            return false;
        }
        a(a(a2, i2, str));
        return true;
    }

    private CharSequence c(CharSequence charSequence) {
        if (!this.N || this.D == null) {
            return charSequence;
        }
        double textSize = this.c.getTextSize();
        Double.isNaN(textSize);
        int i = (int) (textSize * 1.25d);
        this.D.setBounds(0, 0, i, i);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.D), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    private void c(boolean z) {
        int i;
        if (this.U && !d() && z) {
            i = 0;
            this.k.setVisibility(8);
        } else {
            i = 8;
        }
        this.m.setVisibility(i);
    }

    private void e(int i) {
        CharSequence c2;
        Editable text = this.c.getText();
        Cursor a2 = this.o.a();
        if (a2 == null) {
            return;
        }
        if (!a2.moveToPosition(i) || (c2 = this.o.c(a2)) == null) {
            setQuery(text);
        } else {
            setQuery(c2);
        }
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(a.e.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(a.e.abc_search_view_preferred_width);
    }

    private boolean r() {
        SearchableInfo searchableInfo = this.p;
        if (searchableInfo == null || !searchableInfo.getVoiceSearchEnabled()) {
            return false;
        }
        Intent intent = null;
        if (this.p.getVoiceSearchLaunchWebSearch()) {
            intent = this.G;
        } else if (this.p.getVoiceSearchLaunchRecognizer()) {
            intent = this.H;
        }
        return (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) ? false : true;
    }

    private boolean s() {
        return (this.P || this.U) && !d();
    }

    private void setQuery(CharSequence charSequence) {
        this.c.setText(charSequence);
        this.c.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    private void t() {
        this.v.setVisibility((s() && (this.k.getVisibility() == 0 || this.m.getVisibility() == 0)) ? 0 : 8);
    }

    private void u() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.c.getText());
        if (!z2 && (!this.N || this.aa)) {
            z = false;
        }
        this.l.setVisibility(z ? 0 : 8);
        Drawable drawable = this.l.getDrawable();
        if (drawable != null) {
            drawable.setState(z2 ? ENABLED_STATE_SET : EMPTY_STATE_SET);
        }
    }

    private void v() {
        post(this.ad);
    }

    private void w() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.c;
        if (queryHint == null) {
            queryHint = com.lge.media.launcher.a.d;
        }
        searchAutoComplete.setHint(c(queryHint));
    }

    private void x() {
        this.c.setThreshold(this.p.getSuggestThreshold());
        this.c.setImeOptions(this.p.getImeOptions());
        int inputType = this.p.getInputType();
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.p.getSuggestAuthority() != null) {
                inputType = inputType | 65536 | 524288;
            }
        }
        this.c.setInputType(inputType);
        android.support.v4.widget.f fVar = this.o;
        if (fVar != null) {
            fVar.a((Cursor) null);
        }
        if (this.p.getSuggestAuthority() != null) {
            this.o = new ap(getContext(), this, this.p, this.af);
            this.c.setAdapter(this.o);
            ((ap) this.o).c(this.R ? 2 : 1);
        }
    }

    private void y() {
        this.c.dismissDropDown();
    }

    @Override // android.support.v7.view.c
    public void a() {
        if (this.aa) {
            return;
        }
        this.aa = true;
        this.ab = this.c.getImeOptions();
        this.c.setImeOptions(this.ab | 33554432);
        this.c.setText(com.lge.media.launcher.a.d);
        setIconified(false);
    }

    void a(int i, String str, String str2) {
        getContext().startActivity(a("android.intent.action.SEARCH", (Uri) null, (String) null, str2, i, str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(CharSequence charSequence) {
        setQuery(charSequence);
    }

    public void a(CharSequence charSequence, boolean z) {
        this.c.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.c;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.W = charSequence;
        }
        if (!z || TextUtils.isEmpty(charSequence)) {
            return;
        }
        h();
    }

    boolean a(int i) {
        d dVar = this.L;
        if (dVar == null || !dVar.a(i)) {
            e(i);
            return true;
        }
        return false;
    }

    boolean a(int i, int i2, String str) {
        d dVar = this.L;
        if (dVar == null || !dVar.b(i)) {
            b(i, 0, null);
            this.c.setImeVisibility(false);
            y();
            return true;
        }
        return false;
    }

    boolean a(View view, int i, KeyEvent keyEvent) {
        if (this.p != null && this.o != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i == 66 || i == 84 || i == 61) {
                return a(this.c.getListSelection(), 0, (String) null);
            }
            if (i == 21 || i == 22) {
                this.c.setSelection(i == 21 ? 0 : this.c.length());
                this.c.setListSelection(0);
                this.c.clearListSelection();
                q.a(this.c, true);
                return true;
            } else if (i != 19 || this.c.getListSelection() == 0) {
                return false;
            }
        }
        return false;
    }

    @Override // android.support.v7.view.c
    public void b() {
        a(com.lge.media.launcher.a.d, false);
        clearFocus();
        a(true);
        this.c.setImeOptions(this.ab);
        this.aa = false;
    }

    void b(CharSequence charSequence) {
        Editable text = this.c.getText();
        this.W = text;
        boolean z = !TextUtils.isEmpty(text);
        b(z);
        c(z ? false : true);
        u();
        t();
        if (this.J != null && !TextUtils.equals(charSequence, this.V)) {
            this.J.b(charSequence.toString());
        }
        this.V = charSequence.toString();
    }

    public boolean c() {
        return this.N;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.S = true;
        super.clearFocus();
        this.c.clearFocus();
        this.c.setImeVisibility(false);
        this.S = false;
    }

    public boolean d() {
        return this.O;
    }

    public boolean e() {
        return this.P;
    }

    public boolean f() {
        return this.R;
    }

    void g() {
        int[] iArr = this.c.hasFocus() ? FOCUSED_STATE_SET : EMPTY_STATE_SET;
        Drawable background = this.u.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.v.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    public int getImeOptions() {
        return this.c.getImeOptions();
    }

    public int getInputType() {
        return this.c.getInputType();
    }

    public int getMaxWidth() {
        return this.T;
    }

    public CharSequence getQuery() {
        return this.c.getText();
    }

    @android.support.annotation.ag
    public CharSequence getQueryHint() {
        CharSequence charSequence = this.Q;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.p;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.I : getContext().getText(this.p.getHintId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSuggestionCommitIconResId() {
        return this.F;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSuggestionRowLayout() {
        return this.E;
    }

    public android.support.v4.widget.f getSuggestionsAdapter() {
        return this.o;
    }

    void h() {
        Editable text = this.c.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        c cVar = this.J;
        if (cVar == null || !cVar.a(text.toString())) {
            if (this.p != null) {
                a(0, (String) null, text.toString());
            }
            this.c.setImeVisibility(false);
            y();
        }
    }

    void i() {
        if (!TextUtils.isEmpty(this.c.getText())) {
            this.c.setText(com.lge.media.launcher.a.d);
            this.c.requestFocus();
            this.c.setImeVisibility(true);
        } else if (this.N) {
            b bVar = this.K;
            if (bVar == null || !bVar.a()) {
                clearFocus();
                a(true);
            }
        }
    }

    void m() {
        a(false);
        this.c.requestFocus();
        this.c.setImeVisibility(true);
        View.OnClickListener onClickListener = this.M;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    void n() {
        Intent b2;
        SearchableInfo searchableInfo = this.p;
        if (searchableInfo == null) {
            return;
        }
        try {
            if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                b2 = a(this.G, searchableInfo);
            } else if (!searchableInfo.getVoiceSearchLaunchRecognizer()) {
                return;
            } else {
                b2 = b(this.H, searchableInfo);
            }
            getContext().startActivity(b2);
        } catch (ActivityNotFoundException unused) {
            Log.w(b, "Could not find voice search activity");
        }
    }

    void o() {
        a(d());
        v();
        if (this.c.hasFocus()) {
            q();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.ad);
        post(this.ae);
        super.onDetachedFromWindow();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            a(this.c, this.y);
            this.z.set(this.y.left, 0, this.y.right, i4 - i2);
            e eVar = this.x;
            if (eVar != null) {
                eVar.a(this.z, this.y);
                return;
            }
            this.x = new e(this.z, this.y, this.c);
            setTouchDelegate(this.x);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001f, code lost:
        if (r0 <= 0) goto L13;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004b  */
    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r4, int r5) {
        /*
            r3 = this;
            boolean r0 = r3.d()
            if (r0 == 0) goto La
            super.onMeasure(r4, r5)
            return
        La:
            int r0 = android.view.View.MeasureSpec.getMode(r4)
            int r4 = android.view.View.MeasureSpec.getSize(r4)
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = 1073741824(0x40000000, float:2.0)
            if (r0 == r1) goto L2c
            if (r0 == 0) goto L22
            if (r0 == r2) goto L1d
            goto L39
        L1d:
            int r0 = r3.T
            if (r0 <= 0) goto L39
            goto L30
        L22:
            int r4 = r3.T
            if (r4 <= 0) goto L27
            goto L39
        L27:
            int r4 = r3.getPreferredWidth()
            goto L39
        L2c:
            int r0 = r3.T
            if (r0 <= 0) goto L31
        L30:
            goto L35
        L31:
            int r0 = r3.getPreferredWidth()
        L35:
            int r4 = java.lang.Math.min(r0, r4)
        L39:
            int r0 = android.view.View.MeasureSpec.getMode(r5)
            int r5 = android.view.View.MeasureSpec.getSize(r5)
            if (r0 == r1) goto L4b
            if (r0 == 0) goto L46
            goto L53
        L46:
            int r5 = r3.getPreferredHeight()
            goto L53
        L4b:
            int r0 = r3.getPreferredHeight()
            int r5 = java.lang.Math.min(r0, r5)
        L53:
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r2)
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r2)
            super.onMeasure(r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.SearchView.onMeasure(int, int):void");
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        a(savedState.a);
        requestLayout();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = d();
        return savedState;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        v();
    }

    void p() {
        if (this.w.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.u.getPaddingLeft();
            Rect rect = new Rect();
            boolean a2 = bd.a(this);
            int dimensionPixelSize = this.N ? resources.getDimensionPixelSize(a.e.abc_dropdownitem_icon_width) + resources.getDimensionPixelSize(a.e.abc_dropdownitem_text_padding_left) : 0;
            this.c.getDropDownBackground().getPadding(rect);
            this.c.setDropDownHorizontalOffset(a2 ? -rect.left : paddingLeft - (rect.left + dimensionPixelSize));
            this.c.setDropDownWidth((((this.w.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
        }
    }

    void q() {
        q.a(this.c);
        q.b(this.c);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i, Rect rect) {
        if (!this.S && isFocusable()) {
            if (d()) {
                return super.requestFocus(i, rect);
            }
            boolean requestFocus = this.c.requestFocus(i, rect);
            if (requestFocus) {
                a(false);
            }
            return requestFocus;
        }
        return false;
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setAppSearchData(Bundle bundle) {
        this.ac = bundle;
    }

    public void setIconified(boolean z) {
        if (z) {
            i();
        } else {
            m();
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.N == z) {
            return;
        }
        this.N = z;
        a(z);
        w();
    }

    public void setImeOptions(int i) {
        this.c.setImeOptions(i);
    }

    public void setInputType(int i) {
        this.c.setInputType(i);
    }

    public void setMaxWidth(int i) {
        this.T = i;
        requestLayout();
    }

    public void setOnCloseListener(b bVar) {
        this.K = bVar;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.n = onFocusChangeListener;
    }

    public void setOnQueryTextListener(c cVar) {
        this.J = cVar;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.M = onClickListener;
    }

    public void setOnSuggestionListener(d dVar) {
        this.L = dVar;
    }

    public void setQueryHint(@android.support.annotation.ag CharSequence charSequence) {
        this.Q = charSequence;
        w();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.R = z;
        android.support.v4.widget.f fVar = this.o;
        if (fVar instanceof ap) {
            ((ap) fVar).c(z ? 2 : 1);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.p = searchableInfo;
        if (this.p != null) {
            x();
            w();
        }
        this.U = r();
        if (this.U) {
            this.c.setPrivateImeOptions(s);
        }
        a(d());
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.P = z;
        a(d());
    }

    public void setSuggestionsAdapter(android.support.v4.widget.f fVar) {
        this.o = fVar;
        this.c.setAdapter(this.o);
    }
}
