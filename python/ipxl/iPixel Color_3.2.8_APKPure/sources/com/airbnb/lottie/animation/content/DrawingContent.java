package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import com.airbnb.lottie.utils.DropShadow;

/* loaded from: classes2.dex */
public interface DrawingContent extends Content {
    void draw(Canvas canvas, Matrix matrix, int i, DropShadow dropShadow);

    void getBounds(RectF rectF, Matrix matrix, boolean z);
}
