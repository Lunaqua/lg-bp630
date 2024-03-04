package android.support.v7.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class AppCompatCheckedTextView extends CheckedTextView {
    private static final int[] a = {16843016};
    private final l b;

    public AppCompatCheckedTextView(Context context) {
        this(context, null);
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843720);
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet, int i) {
        super(as.a(context), attributeSet, i);
        this.b = new l(this);
        this.b.a(attributeSet, i);
        this.b.a();
        av a2 = av.a(getContext(), attributeSet, a, i, 0);
        setCheckMarkDrawable(a2.a(0));
        a2.e();
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        l lVar = this.b;
        if (lVar != null) {
            lVar.a();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return h.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(@android.support.annotation.p int i) {
        setCheckMarkDrawable(android.support.v7.b.a.a.b(getContext(), i));
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(android.support.v4.widget.u.b(this, callback));
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        l lVar = this.b;
        if (lVar != null) {
            lVar.a(context, i);
        }
    }
}
