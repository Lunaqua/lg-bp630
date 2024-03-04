package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.annotation.aq;
import android.support.annotation.ar;
import android.support.annotation.p;
import android.support.v7.a.a;
import android.support.v7.app.AlertController;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c extends f implements DialogInterface {
    static final int b = 0;
    static final int c = 1;
    final AlertController a;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a {
        private final AlertController.a a;
        private final int b;

        public a(@af Context context) {
            this(context, c.a(context, 0));
        }

        public a(@af Context context, @ar int i) {
            this.a = new AlertController.a(new ContextThemeWrapper(context, c.a(context, i)));
            this.b = i;
        }

        @af
        public Context a() {
            return this.a.a;
        }

        public a a(@aq int i) {
            AlertController.a aVar = this.a;
            aVar.f = aVar.a.getText(i);
            return this;
        }

        public a a(@android.support.annotation.e int i, int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.v = aVar.a.getResources().getTextArray(i);
            AlertController.a aVar2 = this.a;
            aVar2.x = onClickListener;
            aVar2.I = i2;
            aVar2.H = true;
            return this;
        }

        public a a(@aq int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.i = aVar.a.getText(i);
            this.a.k = onClickListener;
            return this;
        }

        public a a(@android.support.annotation.e int i, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            AlertController.a aVar = this.a;
            aVar.v = aVar.a.getResources().getTextArray(i);
            AlertController.a aVar2 = this.a;
            aVar2.J = onMultiChoiceClickListener;
            aVar2.F = zArr;
            aVar2.G = true;
            return this;
        }

        public a a(DialogInterface.OnCancelListener onCancelListener) {
            this.a.s = onCancelListener;
            return this;
        }

        public a a(DialogInterface.OnDismissListener onDismissListener) {
            this.a.t = onDismissListener;
            return this;
        }

        public a a(DialogInterface.OnKeyListener onKeyListener) {
            this.a.u = onKeyListener;
            return this;
        }

        public a a(Cursor cursor, int i, String str, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.K = cursor;
            aVar.x = onClickListener;
            aVar.I = i;
            aVar.L = str;
            aVar.H = true;
            return this;
        }

        public a a(Cursor cursor, DialogInterface.OnClickListener onClickListener, String str) {
            AlertController.a aVar = this.a;
            aVar.K = cursor;
            aVar.L = str;
            aVar.x = onClickListener;
            return this;
        }

        public a a(Cursor cursor, String str, String str2, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            AlertController.a aVar = this.a;
            aVar.K = cursor;
            aVar.J = onMultiChoiceClickListener;
            aVar.M = str;
            aVar.L = str2;
            aVar.G = true;
            return this;
        }

        public a a(@ag Drawable drawable) {
            this.a.d = drawable;
            return this;
        }

        public a a(@ag View view) {
            this.a.g = view;
            return this;
        }

        @an(a = {an.a.LIBRARY_GROUP})
        @Deprecated
        public a a(View view, int i, int i2, int i3, int i4) {
            AlertController.a aVar = this.a;
            aVar.z = view;
            aVar.y = 0;
            aVar.E = true;
            aVar.A = i;
            aVar.B = i2;
            aVar.C = i3;
            aVar.D = i4;
            return this;
        }

        public a a(AdapterView.OnItemSelectedListener onItemSelectedListener) {
            this.a.O = onItemSelectedListener;
            return this;
        }

        public a a(ListAdapter listAdapter, int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.w = listAdapter;
            aVar.x = onClickListener;
            aVar.I = i;
            aVar.H = true;
            return this;
        }

        public a a(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.w = listAdapter;
            aVar.x = onClickListener;
            return this;
        }

        public a a(@ag CharSequence charSequence) {
            this.a.f = charSequence;
            return this;
        }

        public a a(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.i = charSequence;
            aVar.k = onClickListener;
            return this;
        }

        public a a(boolean z) {
            this.a.r = z;
            return this;
        }

        public a a(CharSequence[] charSequenceArr, int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.v = charSequenceArr;
            aVar.x = onClickListener;
            aVar.I = i;
            aVar.H = true;
            return this;
        }

        public a a(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.v = charSequenceArr;
            aVar.x = onClickListener;
            return this;
        }

        public a a(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            AlertController.a aVar = this.a;
            aVar.v = charSequenceArr;
            aVar.J = onMultiChoiceClickListener;
            aVar.F = zArr;
            aVar.G = true;
            return this;
        }

        public a b(@aq int i) {
            AlertController.a aVar = this.a;
            aVar.h = aVar.a.getText(i);
            return this;
        }

        public a b(@aq int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.l = aVar.a.getText(i);
            this.a.n = onClickListener;
            return this;
        }

        public a b(Drawable drawable) {
            this.a.j = drawable;
            return this;
        }

        public a b(View view) {
            AlertController.a aVar = this.a;
            aVar.z = view;
            aVar.y = 0;
            aVar.E = false;
            return this;
        }

        public a b(@ag CharSequence charSequence) {
            this.a.h = charSequence;
            return this;
        }

        public a b(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.l = charSequence;
            aVar.n = onClickListener;
            return this;
        }

        @Deprecated
        public a b(boolean z) {
            this.a.N = z;
            return this;
        }

        public c b() {
            c cVar = new c(this.a.a, this.b);
            this.a.a(cVar.a);
            cVar.setCancelable(this.a.r);
            if (this.a.r) {
                cVar.setCanceledOnTouchOutside(true);
            }
            cVar.setOnCancelListener(this.a.s);
            cVar.setOnDismissListener(this.a.t);
            if (this.a.u != null) {
                cVar.setOnKeyListener(this.a.u);
            }
            return cVar;
        }

        public a c(@p int i) {
            this.a.c = i;
            return this;
        }

        public a c(@aq int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.o = aVar.a.getText(i);
            this.a.q = onClickListener;
            return this;
        }

        public a c(Drawable drawable) {
            this.a.m = drawable;
            return this;
        }

        public a c(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.o = charSequence;
            aVar.q = onClickListener;
            return this;
        }

        @an(a = {an.a.LIBRARY_GROUP})
        public a c(boolean z) {
            this.a.Q = z;
            return this;
        }

        public c c() {
            c b = b();
            b.show();
            return b;
        }

        public a d(@android.support.annotation.f int i) {
            TypedValue typedValue = new TypedValue();
            this.a.a.getTheme().resolveAttribute(i, typedValue, true);
            this.a.c = typedValue.resourceId;
            return this;
        }

        public a d(@android.support.annotation.e int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.v = aVar.a.getResources().getTextArray(i);
            this.a.x = onClickListener;
            return this;
        }

        public a d(Drawable drawable) {
            this.a.p = drawable;
            return this;
        }

        public a e(int i) {
            AlertController.a aVar = this.a;
            aVar.z = null;
            aVar.y = i;
            aVar.E = false;
            return this;
        }
    }

    protected c(@af Context context) {
        this(context, 0);
    }

    protected c(@af Context context, @ar int i) {
        super(context, a(context, i));
        this.a = new AlertController(getContext(), this, getWindow());
    }

    protected c(@af Context context, boolean z, @ag DialogInterface.OnCancelListener onCancelListener) {
        this(context, 0);
        setCancelable(z);
        setOnCancelListener(onCancelListener);
    }

    static int a(@af Context context, @ar int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(a.b.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public Button a(int i) {
        return this.a.e(i);
    }

    public ListView a() {
        return this.a.b();
    }

    public void a(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        this.a.a(i, charSequence, onClickListener, (Message) null, (Drawable) null);
    }

    public void a(int i, CharSequence charSequence, Drawable drawable, DialogInterface.OnClickListener onClickListener) {
        this.a.a(i, charSequence, onClickListener, (Message) null, drawable);
    }

    public void a(int i, CharSequence charSequence, Message message) {
        this.a.a(i, charSequence, (DialogInterface.OnClickListener) null, message, (Drawable) null);
    }

    public void a(Drawable drawable) {
        this.a.a(drawable);
    }

    public void a(View view) {
        this.a.b(view);
    }

    public void a(View view, int i, int i2, int i3, int i4) {
        this.a.a(view, i, i2, i3, i4);
    }

    public void a(CharSequence charSequence) {
        this.a.b(charSequence);
    }

    @an(a = {an.a.LIBRARY_GROUP})
    void b(int i) {
        this.a.b(i);
    }

    public void b(View view) {
        this.a.c(view);
    }

    public void c(int i) {
        this.a.c(i);
    }

    public void d(int i) {
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(i, typedValue, true);
        this.a.c(typedValue.resourceId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.f, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a.a();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.a.a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.a.b(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.support.v7.app.f, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.a.a(charSequence);
    }
}
