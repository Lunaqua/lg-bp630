package android.support.v4.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.annotation.an;
import android.support.v4.widget.g;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class f extends BaseAdapter implements g.a, Filterable {
    @Deprecated
    public static final int j = 1;
    public static final int k = 2;
    @an(a = {an.a.LIBRARY_GROUP})
    protected boolean a;
    @an(a = {an.a.LIBRARY_GROUP})
    protected boolean b;
    @an(a = {an.a.LIBRARY_GROUP})
    protected Cursor c;
    @an(a = {an.a.LIBRARY_GROUP})
    protected Context d;
    @an(a = {an.a.LIBRARY_GROUP})
    protected int e;
    @an(a = {an.a.LIBRARY_GROUP})
    protected a f;
    @an(a = {an.a.LIBRARY_GROUP})
    protected DataSetObserver g;
    @an(a = {an.a.LIBRARY_GROUP})
    protected g h;
    @an(a = {an.a.LIBRARY_GROUP})
    protected FilterQueryProvider i;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class a extends ContentObserver {
        a() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            f.this.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class b extends DataSetObserver {
        b() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            f fVar = f.this;
            fVar.a = true;
            fVar.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            f fVar = f.this;
            fVar.a = false;
            fVar.notifyDataSetInvalidated();
        }
    }

    @Deprecated
    public f(Context context, Cursor cursor) {
        a(context, cursor, 1);
    }

    public f(Context context, Cursor cursor, int i) {
        a(context, cursor, i);
    }

    public f(Context context, Cursor cursor, boolean z) {
        a(context, cursor, z ? 1 : 2);
    }

    @Override // android.support.v4.widget.g.a
    public Cursor a() {
        return this.c;
    }

    @Override // android.support.v4.widget.g.a
    public Cursor a(CharSequence charSequence) {
        FilterQueryProvider filterQueryProvider = this.i;
        return filterQueryProvider != null ? filterQueryProvider.runQuery(charSequence) : this.c;
    }

    public abstract View a(Context context, Cursor cursor, ViewGroup viewGroup);

    void a(Context context, Cursor cursor, int i) {
        b bVar;
        if ((i & 1) == 1) {
            i |= 2;
            this.b = true;
        } else {
            this.b = false;
        }
        boolean z = cursor != null;
        this.c = cursor;
        this.a = z;
        this.d = context;
        this.e = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i & 2) == 2) {
            this.f = new a();
            bVar = new b();
        } else {
            bVar = null;
            this.f = null;
        }
        this.g = bVar;
        if (z) {
            a aVar = this.f;
            if (aVar != null) {
                cursor.registerContentObserver(aVar);
            }
            DataSetObserver dataSetObserver = this.g;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    @Deprecated
    protected void a(Context context, Cursor cursor, boolean z) {
        a(context, cursor, z ? 1 : 2);
    }

    @Override // android.support.v4.widget.g.a
    public void a(Cursor cursor) {
        Cursor b2 = b(cursor);
        if (b2 != null) {
            b2.close();
        }
    }

    public abstract void a(View view, Context context, Cursor cursor);

    public void a(FilterQueryProvider filterQueryProvider) {
        this.i = filterQueryProvider;
    }

    public Cursor b(Cursor cursor) {
        Cursor cursor2 = this.c;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            a aVar = this.f;
            if (aVar != null) {
                cursor2.unregisterContentObserver(aVar);
            }
            DataSetObserver dataSetObserver = this.g;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.c = cursor;
        if (cursor != null) {
            a aVar2 = this.f;
            if (aVar2 != null) {
                cursor.registerContentObserver(aVar2);
            }
            DataSetObserver dataSetObserver2 = this.g;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.e = cursor.getColumnIndexOrThrow("_id");
            this.a = true;
            notifyDataSetChanged();
        } else {
            this.e = -1;
            this.a = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }

    public View b(Context context, Cursor cursor, ViewGroup viewGroup) {
        return a(context, cursor, viewGroup);
    }

    public FilterQueryProvider b() {
        return this.i;
    }

    @Override // android.support.v4.widget.g.a
    public CharSequence c(Cursor cursor) {
        return cursor == null ? com.lge.media.launcher.a.d : cursor.toString();
    }

    protected void c() {
        Cursor cursor;
        if (!this.b || (cursor = this.c) == null || cursor.isClosed()) {
            return;
        }
        this.a = this.c.requery();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.a || (cursor = this.c) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (this.a) {
            this.c.moveToPosition(i);
            if (view == null) {
                view = b(this.d, this.c, viewGroup);
            }
            a(view, this.d, this.c);
            return view;
        }
        return null;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.h == null) {
            this.h = new g(this);
        }
        return this.h;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        Cursor cursor;
        if (!this.a || (cursor = this.c) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        return this.c;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        Cursor cursor;
        if (this.a && (cursor = this.c) != null && cursor.moveToPosition(i)) {
            return this.c.getLong(this.e);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (this.a) {
            if (this.c.moveToPosition(i)) {
                if (view == null) {
                    view = a(this.d, this.c, viewGroup);
                }
                a(view, this.d, this.c);
                return view;
            }
            throw new IllegalStateException("couldn't move cursor to position " + i);
        }
        throw new IllegalStateException("this should only be called when the cursor is valid");
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }
}
