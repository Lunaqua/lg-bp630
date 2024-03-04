package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.widget.RadioButton;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class AppCompatRadioButton extends RadioButton implements android.support.v4.widget.v {
    private final f a;
    private final l b;

    public AppCompatRadioButton(Context context) {
        this(context, null);
    }

    public AppCompatRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.b.radioButtonStyle);
    }

    public AppCompatRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(as.a(context), attributeSet, i);
        this.a = new f(this);
        this.a.a(attributeSet, i);
        this.b = new l(this);
        this.b.a(attributeSet, i);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        f fVar = this.a;
        return fVar != null ? fVar.a(compoundPaddingLeft) : compoundPaddingLeft;
    }

    @Override // android.support.v4.widget.v
    @android.support.annotation.ag
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public ColorStateList getSupportButtonTintList() {
        f fVar = this.a;
        if (fVar != null) {
            return fVar.a();
        }
        return null;
    }

    @Override // android.support.v4.widget.v
    @android.support.annotation.ag
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public PorterDuff.Mode getSupportButtonTintMode() {
        f fVar = this.a;
        if (fVar != null) {
            return fVar.b();
        }
        return null;
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(@android.support.annotation.p int i) {
        setButtonDrawable(android.support.v7.b.a.a.b(getContext(), i));
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        f fVar = this.a;
        if (fVar != null) {
            fVar.c();
        }
    }

    @Override // android.support.v4.widget.v
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setSupportButtonTintList(@android.support.annotation.ag ColorStateList colorStateList) {
        f fVar = this.a;
        if (fVar != null) {
            fVar.a(colorStateList);
        }
    }

    @Override // android.support.v4.widget.v
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setSupportButtonTintMode(@android.support.annotation.ag PorterDuff.Mode mode) {
        f fVar = this.a;
        if (fVar != null) {
            fVar.a(mode);
        }
    }
}
