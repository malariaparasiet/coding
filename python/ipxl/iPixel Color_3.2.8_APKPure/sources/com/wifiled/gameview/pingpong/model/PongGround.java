package com.wifiled.gameview.pingpong.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PongGround.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0018\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/wifiled/gameview/pingpong/model/PongGround;", "", "<init>", "()V", "SPERATOR_WHITE_LINE", "", "chessboard", "", "", "[[Z", "mLinePaint", "Landroid/graphics/Paint;", "drawMe", "", "canvas", "Landroid/graphics/Canvas;", "paint", "w", "h", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PongGround {
    private final int SPERATOR_WHITE_LINE = 50;
    private boolean[][] chessboard;
    private Paint mLinePaint;

    public PongGround() {
        boolean[][] zArr = new boolean[64][];
        for (int i = 0; i < 64; i++) {
            zArr[i] = new boolean[64];
        }
        this.chessboard = zArr;
        Paint paint = new Paint();
        this.mLinePaint = paint;
        paint.setColor(Color.parseColor("#2e2f32"));
        for (int i2 = 0; i2 < 64; i2++) {
            boolean[] zArr2 = this.chessboard[i2];
            zArr2[0] = true;
            zArr2[63] = true;
        }
        for (int i3 = 0; i3 < 64; i3++) {
            boolean[][] zArr3 = this.chessboard;
            zArr3[0][i3] = true;
            zArr3[63][i3] = true;
        }
    }

    public final void drawMe(Canvas canvas, Paint paint, int w, int h) {
        int i;
        int i2;
        int i3;
        Paint paint2 = paint;
        Canvas canvas2 = canvas;
        Intrinsics.checkNotNullParameter(canvas2, "canvas");
        Intrinsics.checkNotNullParameter(paint2, "paint");
        paint2.setColor(-1);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        int i4 = 0;
        while (true) {
            int i5 = 64;
            if (i4 >= 64) {
                Log.d(getClass().getSimpleName(), "drawMe: cost " + (SystemClock.elapsedRealtime() - elapsedRealtime) + "  ms");
                return;
            }
            int i6 = 0;
            while (i6 < i5) {
                if (this.chessboard[i4][i6]) {
                    float f = i6 * w;
                    float f2 = i4 * h;
                    float f3 = (i6 + 1) * w;
                    float f4 = (i4 + 1) * h;
                    canvas2.drawRect(f, f2, f3, f4, paint2);
                    i3 = i6;
                    i = i4;
                    i2 = i5;
                    canvas.drawLine(f, f2, f, f4, this.mLinePaint);
                    canvas.drawRect(f, f2, f3, f2, this.mLinePaint);
                    float f5 = w;
                    canvas.drawLine(0.0f, f2, f5, f2, this.mLinePaint);
                    canvas.drawLine(63 * f5, f2, f5 * i2, f2, this.mLinePaint);
                } else {
                    i = i4;
                    i2 = i5;
                    i3 = i6;
                }
                i6 = i3 + 1;
                canvas2 = canvas;
                paint2 = paint;
                i4 = i;
                i5 = i2;
            }
            i4++;
            canvas2 = canvas;
            paint2 = paint;
        }
    }
}
