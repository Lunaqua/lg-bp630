package android.support.v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.a.a;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class ap extends android.support.v4.widget.r implements View.OnClickListener {
    static final int l = 0;
    static final int m = 1;
    static final int n = 2;
    static final int o = -1;
    private static final boolean p = false;
    private static final String q = "SuggestionsAdapter";
    private static final int r = 50;
    private ColorStateList A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private final SearchManager s;
    private final SearchView t;
    private final SearchableInfo u;
    private final Context v;
    private final WeakHashMap<String, Drawable.ConstantState> w;
    private final int x;
    private boolean y;
    private int z;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class a {
        public final TextView a;
        public final TextView b;
        public final ImageView c;
        public final ImageView d;
        public final ImageView e;

        public a(View view) {
            this.a = (TextView) view.findViewById(16908308);
            this.b = (TextView) view.findViewById(16908309);
            this.c = (ImageView) view.findViewById(16908295);
            this.d = (ImageView) view.findViewById(16908296);
            this.e = (ImageView) view.findViewById(a.g.edit_query);
        }
    }

    public ap(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, Drawable.ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), (Cursor) null, true);
        this.y = false;
        this.z = 1;
        this.B = -1;
        this.C = -1;
        this.D = -1;
        this.E = -1;
        this.F = -1;
        this.G = -1;
        this.s = (SearchManager) this.d.getSystemService("search");
        this.t = searchView;
        this.u = searchableInfo;
        this.x = searchView.getSuggestionCommitIconResId();
        this.v = context;
        this.w = weakHashMap;
    }

    private Drawable a(ComponentName componentName) {
        String flattenToShortString = componentName.flattenToShortString();
        if (!this.w.containsKey(flattenToShortString)) {
            Drawable b = b(componentName);
            this.w.put(flattenToShortString, b != null ? b.getConstantState() : null);
            return b;
        }
        Drawable.ConstantState constantState = this.w.get(flattenToShortString);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable(this.v.getResources());
    }

    private Drawable a(String str) {
        if (str == null || str.isEmpty() || "0".equals(str)) {
            return null;
        }
        try {
            int parseInt = Integer.parseInt(str);
            String str2 = "android.resource://" + this.v.getPackageName() + "/" + parseInt;
            Drawable b = b(str2);
            if (b != null) {
                return b;
            }
            Drawable drawable = android.support.v4.content.c.getDrawable(this.v, parseInt);
            a(str2, drawable);
            return drawable;
        } catch (Resources.NotFoundException unused) {
            Log.w(q, "Icon resource not found: " + str);
            return null;
        } catch (NumberFormatException unused2) {
            Drawable b2 = b(str);
            if (b2 != null) {
                return b2;
            }
            Drawable b3 = b(Uri.parse(str));
            a(str, b3);
            return b3;
        }
    }

    private static String a(Cursor cursor, int i) {
        if (i == -1) {
            return null;
        }
        try {
            return cursor.getString(i);
        } catch (Exception e) {
            Log.e(q, "unexpected error retrieving valid column from cursor, did the remote process die?", e);
            return null;
        }
    }

    public static String a(Cursor cursor, String str) {
        return a(cursor, cursor.getColumnIndex(str));
    }

    private void a(ImageView imageView, Drawable drawable, int i) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    private void a(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        textView.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
    }

    private void a(String str, Drawable drawable) {
        if (drawable != null) {
            this.w.put(str, drawable.getConstantState());
        }
    }

    private Drawable b(ComponentName componentName) {
        String nameNotFoundException;
        ActivityInfo activityInfo;
        int iconResource;
        PackageManager packageManager = this.d.getPackageManager();
        try {
            activityInfo = packageManager.getActivityInfo(componentName, 128);
            iconResource = activityInfo.getIconResource();
        } catch (PackageManager.NameNotFoundException e) {
            nameNotFoundException = e.toString();
        }
        if (iconResource == 0) {
            return null;
        }
        Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
        if (drawable == null) {
            nameNotFoundException = "Invalid icon resource " + iconResource + " for " + componentName.flattenToShortString();
            Log.w(q, nameNotFoundException);
            return null;
        }
        return drawable;
    }

    private Drawable b(Uri uri) {
        try {
            if ("android.resource".equals(uri.getScheme())) {
                try {
                    return a(uri);
                } catch (Resources.NotFoundException unused) {
                    throw new FileNotFoundException("Resource does not exist: " + uri);
                }
            }
            InputStream openInputStream = this.v.getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                throw new FileNotFoundException("Failed to open " + uri);
            }
            Drawable createFromStream = Drawable.createFromStream(openInputStream, null);
            try {
                openInputStream.close();
            } catch (IOException e) {
                Log.e(q, "Error closing icon stream for " + uri, e);
            }
            return createFromStream;
        } catch (FileNotFoundException e2) {
            Log.w(q, "Icon not found: " + uri + ", " + e2.getMessage());
            return null;
        }
        Log.w(q, "Icon not found: " + uri + ", " + e2.getMessage());
        return null;
    }

    private Drawable b(String str) {
        Drawable.ConstantState constantState = this.w.get(str);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable();
    }

    private CharSequence b(CharSequence charSequence) {
        if (this.A == null) {
            TypedValue typedValue = new TypedValue();
            this.d.getTheme().resolveAttribute(a.b.textColorSearchUrl, typedValue, true);
            this.A = this.d.getResources().getColorStateList(typedValue.resourceId);
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.A, null), 0, charSequence.length(), 33);
        return spannableString;
    }

    private void d(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras == null || extras.getBoolean("in_progress")) {
        }
    }

    private Drawable e(Cursor cursor) {
        int i = this.E;
        if (i == -1) {
            return null;
        }
        Drawable a2 = a(cursor.getString(i));
        return a2 != null ? a2 : g(cursor);
    }

    private Drawable f(Cursor cursor) {
        int i = this.F;
        if (i == -1) {
            return null;
        }
        return a(cursor.getString(i));
    }

    private Drawable g(Cursor cursor) {
        Drawable a2 = a(this.u.getSearchActivity());
        return a2 != null ? a2 : this.d.getPackageManager().getDefaultActivityIcon();
    }

    Cursor a(SearchableInfo searchableInfo, String str, int i) {
        String suggestAuthority;
        String[] strArr = null;
        if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder fragment = new Uri.Builder().scheme("content").authority(suggestAuthority).query(com.lge.media.launcher.a.d).fragment(com.lge.media.launcher.a.d);
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            fragment.appendEncodedPath(suggestPath);
        }
        fragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            fragment.appendPath(str);
        }
        String[] strArr2 = strArr;
        if (i > 0) {
            fragment.appendQueryParameter("limit", String.valueOf(i));
        }
        return this.d.getContentResolver().query(fragment.build(), null, suggestSelection, strArr2, null);
    }

    @Override // android.support.v4.widget.f, android.support.v4.widget.g.a
    public Cursor a(CharSequence charSequence) {
        String charSequence2 = charSequence == null ? com.lge.media.launcher.a.d : charSequence.toString();
        if (this.t.getVisibility() == 0 && this.t.getWindowVisibility() == 0) {
            try {
                Cursor a2 = a(this.u, charSequence2, 50);
                if (a2 != null) {
                    a2.getCount();
                    return a2;
                }
            } catch (RuntimeException e) {
                Log.w(q, "Search suggestions query threw an exception.", e);
            }
        }
        return null;
    }

    Drawable a(Uri uri) {
        int parseInt;
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources resourcesForApplication = this.d.getPackageManager().getResourcesForApplication(authority);
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                throw new FileNotFoundException("No path: " + uri);
            }
            int size = pathSegments.size();
            if (size == 1) {
                try {
                    parseInt = Integer.parseInt(pathSegments.get(0));
                } catch (NumberFormatException unused) {
                    throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                }
            } else if (size != 2) {
                throw new FileNotFoundException("More than two path segments: " + uri);
            } else {
                parseInt = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
            }
            if (parseInt != 0) {
                return resourcesForApplication.getDrawable(parseInt);
            }
            throw new FileNotFoundException("No resource found for: " + uri);
        } catch (PackageManager.NameNotFoundException unused2) {
            throw new FileNotFoundException("No package found for authority: " + uri);
        }
    }

    @Override // android.support.v4.widget.r, android.support.v4.widget.f
    public View a(Context context, Cursor cursor, ViewGroup viewGroup) {
        View a2 = super.a(context, cursor, viewGroup);
        a2.setTag(new a(a2));
        ((ImageView) a2.findViewById(a.g.edit_query)).setImageResource(this.x);
        return a2;
    }

    @Override // android.support.v4.widget.f, android.support.v4.widget.g.a
    public void a(Cursor cursor) {
        if (this.y) {
            Log.w(q, "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super.a(cursor);
            if (cursor != null) {
                this.B = cursor.getColumnIndex("suggest_text_1");
                this.C = cursor.getColumnIndex("suggest_text_2");
                this.D = cursor.getColumnIndex("suggest_text_2_url");
                this.E = cursor.getColumnIndex("suggest_icon_1");
                this.F = cursor.getColumnIndex("suggest_icon_2");
                this.G = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e) {
            Log.e(q, "error changing cursor and caching columns", e);
        }
    }

    @Override // android.support.v4.widget.f
    public void a(View view, Context context, Cursor cursor) {
        a aVar = (a) view.getTag();
        int i = this.G;
        int i2 = i != -1 ? cursor.getInt(i) : 0;
        if (aVar.a != null) {
            a(aVar.a, a(cursor, this.B));
        }
        if (aVar.b != null) {
            String a2 = a(cursor, this.D);
            CharSequence b = a2 != null ? b((CharSequence) a2) : a(cursor, this.C);
            if (TextUtils.isEmpty(b)) {
                if (aVar.a != null) {
                    aVar.a.setSingleLine(false);
                    aVar.a.setMaxLines(2);
                }
            } else if (aVar.a != null) {
                aVar.a.setSingleLine(true);
                aVar.a.setMaxLines(1);
            }
            a(aVar.b, b);
        }
        if (aVar.c != null) {
            a(aVar.c, e(cursor), 4);
        }
        if (aVar.d != null) {
            a(aVar.d, f(cursor), 8);
        }
        int i3 = this.z;
        if (i3 != 2 && (i3 != 1 || (i2 & 1) == 0)) {
            aVar.e.setVisibility(8);
            return;
        }
        aVar.e.setVisibility(0);
        aVar.e.setTag(aVar.a.getText());
        aVar.e.setOnClickListener(this);
    }

    @Override // android.support.v4.widget.f, android.support.v4.widget.g.a
    public CharSequence c(Cursor cursor) {
        String a2;
        String a3;
        if (cursor == null) {
            return null;
        }
        String a4 = a(cursor, "suggest_intent_query");
        if (a4 != null) {
            return a4;
        }
        if (!this.u.shouldRewriteQueryFromData() || (a3 = a(cursor, "suggest_intent_data")) == null) {
            if (!this.u.shouldRewriteQueryFromText() || (a2 = a(cursor, "suggest_text_1")) == null) {
                return null;
            }
            return a2;
        }
        return a3;
    }

    public void c(int i) {
        this.z = i;
    }

    public int d() {
        return this.z;
    }

    public void e() {
        a((Cursor) null);
        this.y = true;
    }

    @Override // android.support.v4.widget.f, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i, view, viewGroup);
        } catch (RuntimeException e) {
            Log.w(q, "Search suggestions cursor threw exception.", e);
            View b = b(this.d, this.c, viewGroup);
            if (b != null) {
                ((a) b.getTag()).a.setText(e.toString());
            }
            return b;
        }
    }

    @Override // android.support.v4.widget.f, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i, view, viewGroup);
        } catch (RuntimeException e) {
            Log.w(q, "Search suggestions cursor threw exception.", e);
            View a2 = a(this.d, this.c, viewGroup);
            if (a2 != null) {
                ((a) a2.getTag()).a.setText(e.toString());
            }
            return a2;
        }
    }

    @Override // android.support.v4.widget.f, android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        d(a());
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        d(a());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.t.a((CharSequence) tag);
        }
    }
}
