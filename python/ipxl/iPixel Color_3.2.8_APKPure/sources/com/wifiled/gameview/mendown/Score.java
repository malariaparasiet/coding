package com.wifiled.gameview.mendown;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.google.android.material.card.MaterialCardViewHelper;

/* loaded from: classes3.dex */
public class Score {
    private Paint mPaint;
    public int x;
    public int y;
    public int panelWidth = MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION;
    public int panelHeight = 120;

    public Score(Paint paint) {
        this.mPaint = paint;
    }

    public void drawPanel(Canvas canvas) {
        canvas.save();
        canvas.drawRoundRect(new RectF(this.x, this.y, r1 + this.panelWidth, r3 + this.panelHeight), 0.0f, 0.0f, this.mPaint);
        int i = this.x;
        int i2 = this.y;
        int i3 = this.panelHeight;
        this.mPaint.setShader(new LinearGradient(i, i2 + i3, i, i2 + i3 + 8, new int[]{Color.parseColor("#9e666666"), Color.parseColor("#6e666666"), Color.parseColor("#1edddddd")}, (float[]) null, Shader.TileMode.REPEAT));
        int i4 = this.x;
        int i5 = this.y;
        int i6 = this.panelHeight;
        canvas.drawRect(new RectF(i4, i5 + i6, i4 + this.panelWidth, i5 + i6 + 8), this.mPaint);
        this.mPaint.setShader(null);
        canvas.restore();
    }

    public void drawScore(Canvas canvas, String str) {
        canvas.save();
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = this.mPaint.getFontMetrics();
        canvas.drawText(str, this.x + (this.panelWidth / 2), this.y + (((this.panelHeight / 2) + ((fontMetrics.bottom - fontMetrics.top) / 2.0f)) - fontMetrics.bottom), this.mPaint);
        canvas.restore();
    }
}
