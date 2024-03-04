package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class r extends f {
    private int l;
    private int m;
    private LayoutInflater n;

    @Deprecated
    public r(Context context, int i, Cursor cursor) {
        super(context, cursor);
        this.m = i;
        this.l = i;
        this.n = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public r(Context context, int i, Cursor cursor, int i2) {
        super(context, cursor, i2);
        this.m = i;
        this.l = i;
        this.n = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Deprecated
    public r(Context context, int i, Cursor cursor, boolean z) {
        super(context, cursor, z);
        this.m = i;
        this.l = i;
        this.n = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // android.support.v4.widget.f
    public View a(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.n.inflate(this.l, viewGroup, false);
    }

    public void a(int i) {
        this.l = i;
    }

    @Override // android.support.v4.widget.f
    public View b(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.n.inflate(this.m, viewGroup, false);
    }

    public void b(int i) {
        this.m = i;
    }
}
