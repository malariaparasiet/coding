package com.wifiled.ipixels.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/* loaded from: classes3.dex */
public class CenterAlignImageSpan extends ImageSpan {
    public CenterAlignImageSpan(Drawable d) {
        super(d);
    }

    public CenterAlignImageSpan(Drawable d, int verticalAlignment) {
        super(d, verticalAlignment);
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Drawable drawable = getDrawable();
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int i = ((((fontMetricsInt.descent + y) + y) + fontMetricsInt.ascent) / 2) - (drawable.getBounds().bottom / 2);
        canvas.save();
        canvas.translate(x, i);
        drawable.draw(canvas);
        canvas.restore();
    }
}
