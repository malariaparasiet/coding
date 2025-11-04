package com.wifiled.baselib.widget.loopview;

/* loaded from: classes2.dex */
final class InertiaTimerTask implements Runnable {
    float a = 2.1474836E9f;
    final LoopView loopView;
    final float velocityY;

    InertiaTimerTask(LoopView loopView, float f) {
        this.loopView = loopView;
        this.velocityY = f;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.a == 2.1474836E9f) {
            if (Math.abs(this.velocityY) <= 2000.0f) {
                this.a = this.velocityY;
            } else if (this.velocityY > 0.0f) {
                this.a = 2000.0f;
            } else {
                this.a = -2000.0f;
            }
        }
        if (Math.abs(this.a) >= 0.0f && Math.abs(this.a) <= 20.0f) {
            this.loopView.cancelFuture();
            this.loopView.handler.sendEmptyMessage(2000);
            return;
        }
        this.loopView.totalScrollY -= (int) ((this.a * 10.0f) / 1000.0f);
        if (!this.loopView.isLoop) {
            float f = this.loopView.lineSpacingMultiplier * this.loopView.maxTextHeight;
            if (this.loopView.totalScrollY <= ((int) ((-this.loopView.initPosition) * f))) {
                this.a = 40.0f;
                this.loopView.totalScrollY = (int) ((-r3.initPosition) * f);
            } else if (this.loopView.totalScrollY >= ((int) (((this.loopView.items.size() - 1) - this.loopView.initPosition) * f))) {
                this.loopView.totalScrollY = (int) (((r3.items.size() - 1) - this.loopView.initPosition) * f);
                this.a = -40.0f;
            }
        }
        float f2 = this.a;
        if (f2 < 0.0f) {
            this.a = f2 + 20.0f;
        } else {
            this.a = f2 - 20.0f;
        }
        this.loopView.handler.sendEmptyMessage(1000);
    }
}
