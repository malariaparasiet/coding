package com.wifiled.ipixels.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes3.dex */
public class LedItemRhyView extends View {
    private static final String TAG = "LedItemView";
    private int columnNumber;
    private boolean isChecked;
    private Paint paint;
    private int rowNumber;
    private int viewNumber;

    public LedItemRhyView(Context context) {
        super(context);
        Paint paint = new Paint();
        this.paint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.paint.setAntiAlias(false);
        this.paint.setDither(false);
        init(0, 0.0f);
    }

    public LedItemRhyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Paint paint = new Paint();
        this.paint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.paint.setAntiAlias(false);
        this.paint.setDither(false);
        init(0, 0.0f);
    }

    public LedItemRhyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Paint paint = new Paint();
        this.paint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.paint.setAntiAlias(false);
        this.paint.setDither(false);
        init(0, 0.0f);
    }

    private void init(int color, float strokeWidth) {
        this.paint.setColor(color);
        if (this.isChecked) {
            return;
        }
        this.paint.setStrokeWidth(0.0f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(new Rect((int) (getWidth() * 0.9d), (int) (getHeight() * 0.9d), 0, 0), this.paint);
    }

    public int getViewNumber() {
        return this.viewNumber;
    }

    public void setViewNumber(int viewNumber) {
        this.viewNumber = viewNumber;
    }

    public int getColumnNumber() {
        return this.columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public int getRowNumber() {
        return this.rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }

    public void setPaint(int color) {
        init(color, 1.0f);
    }

    public void setPaint(int color, float strokeWidth) {
        init(color, strokeWidth);
    }
}
