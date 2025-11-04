package com.wifiled.gameview.balatetris.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TetrisGround.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\u0010\u0018\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J.\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001aR\u000e\u0010\u0006\u001a\u00020\u0003X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/wifiled/gameview/balatetris/model/TetrisGround;", "", "type", "", "<init>", "(I)V", "SPERATOR_VERTICAL_LINE_NO", "SPERATOR_HORIZONTAL_LINE_NO", "HEIGHT_COUNT", "getHEIGHT_COUNT", "()I", "WIDTH_COUNT", "getWIDTH_COUNT", "chessboard", "", "", "[[Z", "drawMe", "", "canvas", "Landroid/graphics/Canvas;", "paint", "Landroid/graphics/Paint;", "w", "h", PlayerFinal.STATE, "", "Companion", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TetrisGround {
    public static final int TYPE_INSIDE = 1;
    public static final int TYPE_OUTSIDE = 0;
    private boolean[][] chessboard;
    private final int SPERATOR_VERTICAL_LINE_NO = 41;
    private final int SPERATOR_HORIZONTAL_LINE_NO = 30;
    private final int HEIGHT_COUNT = 62;
    private final int WIDTH_COUNT = 62;

    public TetrisGround(int i) {
        boolean[][] zArr = new boolean[62][];
        for (int i2 = 0; i2 < 62; i2++) {
            zArr[i2] = new boolean[this.WIDTH_COUNT];
        }
        this.chessboard = zArr;
        if (i != 0) {
            if (i != 1) {
                return;
            }
            int i3 = this.WIDTH_COUNT;
            for (int i4 = 0; i4 < i3; i4++) {
                boolean[] zArr2 = this.chessboard[i4];
                zArr2[0] = true;
                zArr2[this.WIDTH_COUNT - 1] = true;
                zArr2[this.SPERATOR_VERTICAL_LINE_NO] = true;
            }
            int i5 = this.HEIGHT_COUNT;
            for (int i6 = 0; i6 < i5; i6++) {
                boolean[][] zArr3 = this.chessboard;
                zArr3[0][i6] = true;
                zArr3[this.WIDTH_COUNT - 1][i6] = true;
            }
            return;
        }
        int i7 = this.WIDTH_COUNT;
        for (int i8 = 0; i8 < i7; i8++) {
            boolean[] zArr4 = this.chessboard[i8];
            zArr4[0] = true;
            zArr4[this.WIDTH_COUNT - 1] = true;
            zArr4[this.SPERATOR_VERTICAL_LINE_NO] = true;
        }
        int i9 = this.HEIGHT_COUNT;
        for (int i10 = 0; i10 < i9; i10++) {
            boolean[][] zArr5 = this.chessboard;
            zArr5[0][i10] = true;
            zArr5[this.WIDTH_COUNT - 1][i10] = true;
            if (i10 > this.SPERATOR_VERTICAL_LINE_NO) {
                zArr5[this.SPERATOR_HORIZONTAL_LINE_NO][i10] = true;
            }
        }
    }

    public final int getHEIGHT_COUNT() {
        return this.HEIGHT_COUNT;
    }

    public final int getWIDTH_COUNT() {
        return this.WIDTH_COUNT;
    }

    public final void drawMe(Canvas canvas, Paint paint, int w, int h, byte state) {
        Paint paint2 = paint;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(paint, "paint");
        paint.setColor(-1);
        int i = this.HEIGHT_COUNT;
        int i2 = 0;
        while (i2 < i) {
            int i3 = this.WIDTH_COUNT;
            int i4 = 0;
            while (i4 < i3) {
                if (this.chessboard[i2][i4] && (state != 2 || ((i2 != this.SPERATOR_HORIZONTAL_LINE_NO || i4 == 0 || i4 == this.WIDTH_COUNT - 1) && (i4 != this.SPERATOR_VERTICAL_LINE_NO || i2 == 0 || i2 == this.HEIGHT_COUNT - 1)))) {
                    canvas.drawRect(i4 * w, i2 * h, (i4 + 1) * w, (i2 + 1) * h, paint2);
                }
                i4++;
                paint2 = paint;
            }
            i2++;
            paint2 = paint;
        }
    }
}
