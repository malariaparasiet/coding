package com.wifiled.gameview.snake.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.lang.reflect.Array;

/* loaded from: classes3.dex */
public class Ground {
    private final int SPERATOR_WHITE_LINE = 50;
    private boolean[][] chessboard = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, 64, 64);

    public Ground() {
        for (int i = 0; i < 64; i++) {
            boolean[] zArr = this.chessboard[i];
            zArr[0] = true;
            zArr[63] = true;
        }
        for (int i2 = 0; i2 < 64; i2++) {
            boolean[][] zArr2 = this.chessboard;
            zArr2[0][i2] = true;
            zArr2[63][i2] = true;
            zArr2[50][i2] = true;
        }
    }

    public boolean isAtBrick(int i, int i2) {
        boolean[] zArr = this.chessboard[i2];
        if (i >= zArr.length) {
            return true;
        }
        return zArr[i];
    }

    public void drawMe(Canvas canvas, Paint paint, int i, int i2) {
        Canvas canvas2;
        Paint paint2;
        paint.setColor(-1);
        for (int i3 = 0; i3 < 64; i3++) {
            int i4 = 0;
            while (i4 < 64) {
                if (this.chessboard[i3][i4]) {
                    canvas2 = canvas;
                    paint2 = paint;
                    canvas2.drawRect(i4 * i, i3 * i2, (i4 + 1) * i, (i3 + 1) * i2, paint2);
                } else {
                    canvas2 = canvas;
                    paint2 = paint;
                }
                i4++;
                canvas = canvas2;
                paint = paint2;
            }
        }
    }

    public void setChessboard(boolean z) {
        for (int i = 0; i < 64; i++) {
            boolean[] zArr = this.chessboard[50];
            zArr[i] = z;
            zArr[0] = true;
            zArr[63] = true;
        }
    }
}
