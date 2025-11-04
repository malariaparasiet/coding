package com.wifiled.ipixels.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IOSLoadingView.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 !2\u00020\u0001:\u0001!B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB\u0013\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\nB\u001d\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\u000bJ\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0018\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0014J\u0010\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020 H\u0014R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018¨\u0006\""}, d2 = {"Lcom/wifiled/ipixels/view/customview/IOSLoadingView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "(Landroid/content/Context;)V", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "iWidth", "iHeight", "widthRect", "heigheRect", "rectPaint", "Landroid/graphics/Paint;", "pos", "rect", "Landroid/graphics/Rect;", TypedValues.Custom.S_COLOR, "", "", "[Ljava/lang/String;", "init", "", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onDraw", "canvas", "Landroid/graphics/Canvas;", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class IOSLoadingView extends View {
    private static final String TAG = "IOSLoadingView";
    private final String[] color;
    private int heigheRect;
    private int iHeight;
    private int iWidth;
    private int pos;
    private Rect rect;
    private Paint rectPaint;
    private int widthRect;

    public IOSLoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.color = new String[]{"#bbbbbb", "#aaaaaa", "#999999", "#888888", "#777777", "#666666"};
        init();
    }

    public IOSLoadingView(Context context) {
        this(context, null);
    }

    public IOSLoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private final void init() {
        this.rectPaint = new Paint(1);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        if (mode == Integer.MIN_VALUE || mode2 == Integer.MIN_VALUE) {
            this.iWidth = 200;
        } else {
            this.iWidth = View.MeasureSpec.getSize(widthMeasureSpec);
            int size = View.MeasureSpec.getSize(heightMeasureSpec);
            this.iHeight = size;
            this.iWidth = Math.min(this.iWidth, size);
        }
        int i = this.iWidth;
        int i2 = i / 12;
        this.widthRect = i2;
        this.heigheRect = i2 * 4;
        setMeasuredDimension(i, i);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        if (this.rect == null) {
            int i = this.iWidth;
            int i2 = this.widthRect;
            this.rect = new Rect((i - i2) / 2, 0, (i + i2) / 2, this.heigheRect);
        }
        for (int i3 = 0; i3 < 12; i3++) {
            int i4 = this.pos;
            if (i3 - i4 >= 5) {
                Paint paint = this.rectPaint;
                Intrinsics.checkNotNull(paint);
                paint.setColor(Color.parseColor(this.color[5]));
            } else {
                int i5 = i3 - i4;
                if (i5 >= 0 && i5 < 5) {
                    Paint paint2 = this.rectPaint;
                    Intrinsics.checkNotNull(paint2);
                    paint2.setColor(Color.parseColor(this.color[i3 - this.pos]));
                } else if (i3 - i4 >= -7 && i3 - i4 < 0) {
                    Paint paint3 = this.rectPaint;
                    Intrinsics.checkNotNull(paint3);
                    paint3.setColor(Color.parseColor(this.color[5]));
                } else if (i3 - i4 >= -11 && i3 - i4 < -7) {
                    Paint paint4 = this.rectPaint;
                    Intrinsics.checkNotNull(paint4);
                    paint4.setColor(Color.parseColor(this.color[(i3 + 12) - this.pos]));
                }
            }
            Rect rect = this.rect;
            Intrinsics.checkNotNull(rect);
            Paint paint5 = this.rectPaint;
            Intrinsics.checkNotNull(paint5);
            canvas.drawRect(rect, paint5);
            int i6 = this.iWidth;
            canvas.rotate(30.0f, i6 / 2, i6 / 2);
        }
        int i7 = this.pos + 1;
        this.pos = i7;
        if (i7 > 11) {
            this.pos = 0;
        }
        postInvalidateDelayed(100L);
    }
}
