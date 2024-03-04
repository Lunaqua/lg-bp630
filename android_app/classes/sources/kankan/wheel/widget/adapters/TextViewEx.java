package kankan.wheel.widget.adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ab;
import android.widget.TextView;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class TextViewEx extends TextView {
    Path a;
    private Paint b;
    private String c;
    private int d;

    public TextViewEx(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.b = new Paint();
        this.b.setAntiAlias(true);
        this.b.setTextSize(16.0f);
        this.b.setColor(ab.s);
        this.b.setStyle(Paint.Style.FILL);
        this.a = new Path();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.drawLine(0.0f, 5.0f, canvas.getWidth(), 5.0f, this.b);
        super.onDraw(canvas);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
        this.c = charSequence.toString();
    }
}
