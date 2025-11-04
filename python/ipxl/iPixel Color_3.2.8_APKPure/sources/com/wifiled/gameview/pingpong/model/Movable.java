package com.wifiled.gameview.pingpong.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MoveAble.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010D\u001a\u00020EH\u0016J\u000e\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020IR\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0013\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001a\u0010\u0016\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u000fR\u001a\u0010\u0019\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR\u001a\u0010!\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001b\"\u0004\b#\u0010\u001dR\u001a\u0010$\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001b\"\u0004\b&\u0010\u001dR\u001a\u0010'\u001a\u00020(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010*\"\u0004\b/\u0010,R\u001a\u00100\u001a\u000201X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001a\u00106\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u001b\"\u0004\b8\u0010\u001dR\u001a\u00109\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u001b\"\u0004\b;\u0010\u001dR\u001a\u0010<\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u001b\"\u0004\b>\u0010\u001dR\u001a\u0010?\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010C¨\u0006J"}, d2 = {"Lcom/wifiled/gameview/pingpong/model/Movable;", "", "x", "", "y", "ballRadius", "", "ballPaint", "Landroid/graphics/Paint;", "<init>", "(IIFLandroid/graphics/Paint;)V", "startX", "getStartX", "()I", "setStartX", "(I)V", "startY", "getStartY", "setStartY", "currentX", "getCurrentX", "setCurrentX", "currentY", "getCurrentY", "setCurrentY", "startVX", "getStartVX", "()F", "setStartVX", "(F)V", "startVY", "getStartVY", "setStartVY", "currentVX", "getCurrentVX", "setCurrentVX", "currentVY", "getCurrentVY", "setCurrentVY", "timeX", "", "getTimeX", "()D", "setTimeX", "(D)V", "timeY", "getTimeY", "setTimeY", "bFall", "", "getBFall", "()Z", "setBFall", "(Z)V", "impactDecFactory", "getImpactDecFactory", "setImpactDecFactory", "impactIncFactory", "getImpactIncFactory", "setImpactIncFactory", "mBallRadius", "getMBallRadius", "setMBallRadius", "mBallPaint", "getMBallPaint", "()Landroid/graphics/Paint;", "setMBallPaint", "(Landroid/graphics/Paint;)V", "toString", "", "drawSelf", "", "canvas", "Landroid/graphics/Canvas;", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Movable {
    private boolean bFall;
    private float currentVX;
    private float currentVY;
    private int currentX;
    private int currentY;
    private float impactDecFactory;
    private float impactIncFactory;
    private Paint mBallPaint;
    private float mBallRadius;
    private float startVX;
    private float startVY;
    private int startX;
    private int startY;
    private double timeX;
    private double timeY;

    public Movable(int i, int i2, float f, Paint ballPaint) {
        Intrinsics.checkNotNullParameter(ballPaint, "ballPaint");
        this.bFall = true;
        this.impactDecFactory = 0.25f;
        this.impactIncFactory = 10.0f;
        this.startX = i;
        this.startY = i2;
        this.currentX = i;
        this.currentY = i2;
        this.mBallRadius = f;
        this.mBallPaint = ballPaint;
        this.timeY = System.nanoTime();
        this.timeX = System.nanoTime();
        this.currentVX = (float) (30 + (25 * Math.random()));
    }

    public final int getStartX() {
        return this.startX;
    }

    public final void setStartX(int i) {
        this.startX = i;
    }

    public final int getStartY() {
        return this.startY;
    }

    public final void setStartY(int i) {
        this.startY = i;
    }

    public final int getCurrentX() {
        return this.currentX;
    }

    public final void setCurrentX(int i) {
        this.currentX = i;
    }

    public final int getCurrentY() {
        return this.currentY;
    }

    public final void setCurrentY(int i) {
        this.currentY = i;
    }

    public final float getStartVX() {
        return this.startVX;
    }

    public final void setStartVX(float f) {
        this.startVX = f;
    }

    public final float getStartVY() {
        return this.startVY;
    }

    public final void setStartVY(float f) {
        this.startVY = f;
    }

    public final float getCurrentVX() {
        return this.currentVX;
    }

    public final void setCurrentVX(float f) {
        this.currentVX = f;
    }

    public final float getCurrentVY() {
        return this.currentVY;
    }

    public final void setCurrentVY(float f) {
        this.currentVY = f;
    }

    public final double getTimeX() {
        return this.timeX;
    }

    public final void setTimeX(double d) {
        this.timeX = d;
    }

    public final double getTimeY() {
        return this.timeY;
    }

    public final void setTimeY(double d) {
        this.timeY = d;
    }

    public final boolean getBFall() {
        return this.bFall;
    }

    public final void setBFall(boolean z) {
        this.bFall = z;
    }

    public final float getImpactDecFactory() {
        return this.impactDecFactory;
    }

    public final void setImpactDecFactory(float f) {
        this.impactDecFactory = f;
    }

    public final float getImpactIncFactory() {
        return this.impactIncFactory;
    }

    public final void setImpactIncFactory(float f) {
        this.impactIncFactory = f;
    }

    public final float getMBallRadius() {
        return this.mBallRadius;
    }

    public final void setMBallRadius(float f) {
        this.mBallRadius = f;
    }

    public final Paint getMBallPaint() {
        return this.mBallPaint;
    }

    public final void setMBallPaint(Paint paint) {
        Intrinsics.checkNotNullParameter(paint, "<set-?>");
        this.mBallPaint = paint;
    }

    public String toString() {
        return "#startGame# startX [" + this.startX + "] startY [" + this.startY + "] currentX [" + this.currentX + "] currentY [" + this.currentY + "] startVX [" + this.startVX + "] startVY [" + this.startVY + "] currentVX [" + this.currentVX + "] currentVY [" + this.currentVY + "] timeX [" + this.timeX + "] timeY [" + this.timeY + "] ";
    }

    public final void drawSelf(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.drawCircle(this.currentX, this.currentY, this.mBallRadius, this.mBallPaint);
    }
}
