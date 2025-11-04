package com.wifiled.gameview.mendown;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/* loaded from: classes3.dex */
public class Barrier {
    private int mHeight;
    private Paint mPaint;
    public int mPositionX;
    public int mPositionY;
    private int mScreenWidth;
    private int mWidth;

    public Barrier(int i, Paint paint) {
        this.mScreenWidth = i;
        this.mPaint = paint;
        this.mWidth = i / 4;
    }

    public void drawBarrier(Canvas canvas) {
        canvas.save();
        canvas.drawRect(new RectF(this.mPositionX, this.mPositionY, this.mWidth + r1, r3 + this.mHeight), this.mPaint);
        canvas.restore();
    }

    public void setHeight(int i) {
        this.mHeight = i;
    }

    public int getWidth() {
        return this.mWidth;
    }
}
