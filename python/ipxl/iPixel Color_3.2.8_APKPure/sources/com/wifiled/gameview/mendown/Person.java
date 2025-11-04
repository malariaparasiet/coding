package com.wifiled.gameview.mendown;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;

/* loaded from: classes3.dex */
public class Person {
    public static final int SPEED = 10;
    private Bitmap bitmap;
    private int mHeaderRadius;
    private Paint mPaint;
    public int mPersonX;
    public int mPersonY;

    public Person(Paint paint, int i, Bitmap bitmap) {
        this.mPaint = paint;
        this.mHeaderRadius = i;
        this.bitmap = bitmap;
    }

    public void draw(Canvas canvas) {
        canvas.save();
        Path path = new Path();
        int i = this.mPersonX;
        path.addCircle(i + r2, this.mPersonY + r2, this.mHeaderRadius, Path.Direction.CCW);
        canvas.clipPath(path);
        Bitmap bitmap = this.bitmap;
        int i2 = this.mPersonX;
        int i3 = this.mPersonY;
        int i4 = this.mHeaderRadius;
        canvas.drawBitmap(bitmap, (Rect) null, new RectF(i2, i3, i2 + (i4 * 2), i3 + (i4 * 2)), this.mPaint);
        canvas.restore();
    }
}
