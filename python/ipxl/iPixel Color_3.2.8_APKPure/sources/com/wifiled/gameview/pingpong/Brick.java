package com.wifiled.gameview.pingpong;

import android.graphics.RectF;

/* loaded from: classes3.dex */
public class Brick {
    private String color;
    private boolean isImpact;
    private RectF rectF;

    public String getColor() {
        return this.color;
    }

    public void setColor(String str) {
        this.color = str;
    }

    public RectF getRectF() {
        return this.rectF;
    }

    public void setRectF(RectF rectF) {
        this.rectF = rectF;
    }

    public boolean isImpact() {
        return this.isImpact;
    }

    public void setImpact(boolean z) {
        this.isImpact = z;
    }
}
