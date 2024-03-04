package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AutoCompleteTextView;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class AppCompatAutoCompleteTextView extends AutoCompleteTextView implements android.support.v4.view.z {
    private static final int[] a = {16843126};
    private final e b;
    private final l c;

    public AppCompatAutoCompleteTextView(Context context) {
        this(context, null);
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.b.autoCompleteTextViewStyle);
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(as.a(context), attributeSet, i);
        av a2 = av.a(getContext(), attributeSet, a, i, 0);
        if (a2.j(0)) {
            setDropDownBackgroundDrawable(a2.a(0));
        }
        a2.e();
        this.b = new e(this);
        this.b.a(attributeSet, i);
        this.c = new l(this);
        this.c.a(attributeSet, i);
        this.c.a();
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.b;
        if (eVar != null) {
            eVar.c();
        }
        l lVar = this.c;
        if (lVar != null) {
            lVar.a();
        }
    }

    @Override // android.support.v4.view.z
    @android.support.annotation.ag
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.b;
        if (eVar != null) {
            return eVar.a();
        }
        return null;
    }

    @Override // android.support.v4.view.z
    @android.support.annotation.ag
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.b;
        if (eVar != null) {
            return eVar.b();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return h.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.b;
        if (eVar != null) {
            eVar.a(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(@android.support.annotation.p int i) {
        super.setBackgroundResource(i);
        e eVar = this.b;
        if (eVar != null) {
            eVar.a(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(android.support.v4.widget.u.b(this, callback));
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(@android.support.annotation.p int i) {
        setDropDownBackgroundDrawable(android.support.v7.b.a.a.b(getContext(), i));
    }

    @Override // android.support.v4.view.z
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setSupportBackgroundTintList(@android.support.annotation.ag ColorStateList colorStateList) {
        e eVar = this.b;
        if (eVar != null) {
            eVar.a(colorStateList);
        }
    }

    @Override // android.support.v4.view.z
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setSupportBackgroundTintMode(@android.support.annotation.ag PorterDuff.Mode mode) {
        e eVar = this.b;
        if (eVar != null) {
            eVar.a(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        l lVar = this.c;
        if (lVar != null) {
            lVar.a(context, i);
        }
    }
}
