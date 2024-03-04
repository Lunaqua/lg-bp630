package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.annotation.aq;
import android.support.annotation.p;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.a.a;
import android.support.v7.view.b;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class ActionBar {
    @Deprecated
    public static final int a = 0;
    @Deprecated
    public static final int b = 1;
    @Deprecated
    public static final int c = 2;
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 4;
    public static final int g = 8;
    public static final int h = 16;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int a;

        public LayoutParams(int i) {
            this(-2, -1, i);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.a = 0;
            this.a = 8388627;
        }

        public LayoutParams(int i, int i2, int i3) {
            super(i, i2);
            this.a = 0;
            this.a = i3;
        }

        public LayoutParams(@af Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.l.ActionBarLayout);
            this.a = obtainStyledAttributes.getInt(a.l.ActionBarLayout_android_layout_gravity, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.a = 0;
            this.a = layoutParams.a;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 0;
        }
    }

    @an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface a {
    }

    @an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface b {
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface c {
        void a(boolean z);
    }

    @Deprecated
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface d {
        boolean a(int i, long j);
    }

    @Deprecated
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class e {
        public static final int a = -1;

        public abstract int a();

        public abstract e a(@p int i);

        public abstract e a(Drawable drawable);

        public abstract e a(f fVar);

        public abstract e a(View view);

        public abstract e a(CharSequence charSequence);

        public abstract e a(Object obj);

        public abstract Drawable b();

        public abstract e b(int i);

        public abstract e b(CharSequence charSequence);

        public abstract e c(int i);

        public abstract CharSequence c();

        public abstract e d(@aq int i);

        public abstract View d();

        public abstract Object e();

        public abstract void f();

        public abstract CharSequence g();
    }

    @Deprecated
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface f {
        void a(e eVar, FragmentTransaction fragmentTransaction);

        void b(e eVar, FragmentTransaction fragmentTransaction);

        void c(e eVar, FragmentTransaction fragmentTransaction);
    }

    @Deprecated
    public abstract int a();

    @an(a = {an.a.LIBRARY_GROUP})
    public android.support.v7.view.b a(b.a aVar) {
        return null;
    }

    public void a(float f2) {
        if (f2 != 0.0f) {
            throw new UnsupportedOperationException("Setting a non-zero elevation is not supported in this action bar configuration.");
        }
    }

    public abstract void a(int i);

    public abstract void a(int i, int i2);

    @an(a = {an.a.LIBRARY_GROUP})
    public void a(Configuration configuration) {
    }

    public abstract void a(Drawable drawable);

    public abstract void a(c cVar);

    @Deprecated
    public abstract void a(e eVar);

    @Deprecated
    public abstract void a(e eVar, int i);

    @Deprecated
    public abstract void a(e eVar, int i, boolean z);

    @Deprecated
    public abstract void a(e eVar, boolean z);

    public abstract void a(View view);

    public abstract void a(View view, LayoutParams layoutParams);

    @Deprecated
    public abstract void a(SpinnerAdapter spinnerAdapter, d dVar);

    public abstract void a(CharSequence charSequence);

    public abstract void a(boolean z);

    @an(a = {an.a.LIBRARY_GROUP})
    public boolean a(int i, KeyEvent keyEvent) {
        return false;
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public boolean a(KeyEvent keyEvent) {
        return false;
    }

    @Deprecated
    public abstract int b();

    public abstract void b(@p int i);

    public abstract void b(Drawable drawable);

    public abstract void b(c cVar);

    @Deprecated
    public abstract void b(e eVar);

    public abstract void b(CharSequence charSequence);

    public abstract void b(boolean z);

    public abstract View c();

    public abstract void c(@p int i);

    public abstract void c(@ag Drawable drawable);

    @Deprecated
    public abstract void c(e eVar);

    public void c(@ag CharSequence charSequence) {
    }

    public abstract void c(boolean z);

    @ag
    public abstract CharSequence d();

    @Deprecated
    public abstract void d(int i);

    public void d(Drawable drawable) {
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public void d(CharSequence charSequence) {
    }

    public abstract void d(boolean z);

    @ag
    public abstract CharSequence e();

    public abstract void e(@aq int i);

    public void e(Drawable drawable) {
    }

    public abstract void e(boolean z);

    @Deprecated
    public abstract int f();

    public abstract void f(int i);

    public void f(@ag Drawable drawable) {
    }

    public void f(boolean z) {
    }

    public abstract int g();

    public abstract void g(int i);

    public void g(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("Hide on content scroll is not supported in this action bar configuration.");
        }
    }

    @Deprecated
    public abstract e h();

    @Deprecated
    public abstract void h(int i);

    @an(a = {an.a.LIBRARY_GROUP})
    public void h(boolean z) {
    }

    @Deprecated
    public abstract void i();

    @Deprecated
    public abstract void i(int i);

    @an(a = {an.a.LIBRARY_GROUP})
    public void i(boolean z) {
    }

    @ag
    @Deprecated
    public abstract e j();

    @Deprecated
    public abstract e j(int i);

    @an(a = {an.a.LIBRARY_GROUP})
    public void j(boolean z) {
    }

    @Deprecated
    public abstract int k();

    public void k(@p int i) {
    }

    public abstract int l();

    public void l(@aq int i) {
    }

    public abstract void m();

    public void m(int i) {
        if (i != 0) {
            throw new UnsupportedOperationException("Setting an explicit action bar hide offset is not supported in this action bar configuration.");
        }
    }

    public abstract void n();

    public abstract boolean o();

    public Context p() {
        return null;
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public boolean q() {
        return false;
    }

    public boolean r() {
        return false;
    }

    public int s() {
        return 0;
    }

    public float t() {
        return 0.0f;
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public boolean u() {
        return false;
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public boolean v() {
        return false;
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public boolean w() {
        return false;
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public boolean x() {
        return false;
    }

    @an(a = {an.a.LIBRARY_GROUP})
    boolean y() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void z() {
    }
}
