package com.wifiled.ipixels.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import com.wifiled.baselib.utils.LogUtils;

/* loaded from: classes3.dex */
public class WrapImageSpan extends ImageSpan {
    public WrapImageSpan(Drawable drawable) {
        super(drawable);
    }

    public WrapImageSpan(Drawable drawable, int verticalAlignment) {
        super(drawable, verticalAlignment);
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        Rect bounds = getDrawable().getBounds();
        if (fm != null) {
            Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
            int i = fontMetricsInt.bottom - fontMetricsInt.top;
            int i2 = (bounds.bottom - bounds.top) / 2;
            int i3 = i / 4;
            int i4 = i2 - i3;
            int i5 = -(i2 + i3);
            fm.ascent = i5;
            fm.top = i5;
            fm.bottom = i4;
            fm.descent = i4;
        }
        return bounds.right;
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Drawable drawable = getDrawable();
        canvas.save();
        int i = (((bottom - top) - drawable.getBounds().bottom) / 2) + top;
        LogUtils.logi("WrapImageSpan>>>[draw]: " + i, new Object[0]);
        canvas.translate(x, i);
        drawable.draw(canvas);
        canvas.restore();
    }
}
