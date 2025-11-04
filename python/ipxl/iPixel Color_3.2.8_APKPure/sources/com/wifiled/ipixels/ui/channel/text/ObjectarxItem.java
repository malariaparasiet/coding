package com.wifiled.ipixels.ui.channel.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ObjectarxItem.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u001d\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0004\u0010\bB%\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u0004\u0010\u000bJ\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\nJ\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\nH\u0002J\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0014J\u0018\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\nH\u0014R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/wifiled/ipixels/ui/channel/text/ObjectarxItem;", "Landroid/view/View;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "bgrData", "Landroid/graphics/Bitmap;", TypedValues.Custom.S_COLOR, "Landroid/graphics/Color;", "size", "divide", "interval", "paint", "Landroid/graphics/Paint;", "setData", "", "setDivide", "setAllColor", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ObjectarxItem extends View {
    private Bitmap bgrData;
    private Color color;
    private int divide;
    private int interval;
    private Paint paint;
    private int size;

    public ObjectarxItem(Context context) {
        super(context);
        this.size = 4;
        this.divide = 15;
        this.interval = 1;
        this.paint = new Paint();
    }

    public ObjectarxItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.size = 4;
        this.divide = 15;
        this.interval = 1;
        this.paint = new Paint();
    }

    public ObjectarxItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.size = 4;
        this.divide = 15;
        this.interval = 1;
        this.paint = new Paint();
    }

    public static /* synthetic */ void setData$default(ObjectarxItem objectarxItem, Bitmap bitmap, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 3;
        }
        objectarxItem.setData(bitmap, i);
    }

    public final void setData(Bitmap bgrData, int size) {
        Intrinsics.checkNotNullParameter(bgrData, "bgrData");
        this.bgrData = bgrData;
        this.size = size;
        setDivide(bgrData.getHeight());
        requestLayout();
        invalidate();
    }

    private final void setDivide(int divide) {
        this.divide = divide;
    }

    public final void setAllColor(Color color) {
        Intrinsics.checkNotNullParameter(color, "color");
        this.color = color;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        Bitmap bitmap = this.bgrData;
        if (bitmap != null) {
            int height = bitmap.getHeight();
            for (int i = 0; i < height; i++) {
                int width = bitmap.getWidth();
                for (int i2 = 0; i2 < width; i2++) {
                    this.paint.setColor(bitmap.getPixel(i2, i));
                    int i3 = this.interval;
                    int i4 = this.size;
                    canvas.drawRect(new Rect((i3 + i4) * i2, (i3 + i4) * i, ((i3 + i4) * i2) + i4, ((i3 + i4) * i) + i4), this.paint);
                }
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int suggestedMinimumWidth;
        int suggestedMinimumHeight;
        Bitmap bitmap = this.bgrData;
        int width = bitmap != null ? bitmap.getWidth() : 0;
        Bitmap bitmap2 = this.bgrData;
        int height = bitmap2 != null ? bitmap2.getHeight() : 0;
        if (width > 0) {
            int i = this.interval;
            suggestedMinimumWidth = ((this.size + i) * width) - i;
        } else {
            suggestedMinimumWidth = getSuggestedMinimumWidth();
        }
        if (height > 0) {
            int i2 = this.interval;
            suggestedMinimumHeight = ((this.size + i2) * height) - i2;
        } else {
            suggestedMinimumHeight = getSuggestedMinimumHeight();
        }
        setMeasuredDimension(suggestedMinimumWidth, suggestedMinimumHeight);
    }
}
