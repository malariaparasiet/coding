package com.wifiled.gameview.snake.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

/* loaded from: classes3.dex */
public class Food extends Point {
    public void newFood(int i, int i2) {
        Log.d("akon", "#newFood# x: " + i + " y:" + i2);
        set(i, i2);
    }

    public void drawMe(Canvas canvas, Paint paint, int i, int i2) {
        paint.setColor(-16711936);
        canvas.drawRect(this.x * i, this.y * i2, (this.x + 1) * i, (this.y + 1) * i2, paint);
    }
}
