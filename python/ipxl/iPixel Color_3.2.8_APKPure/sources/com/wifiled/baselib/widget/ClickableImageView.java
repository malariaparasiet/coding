package com.wifiled.baselib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.wifiled.baselib.R;

/* loaded from: classes2.dex */
public class ClickableImageView extends AppCompatImageView {
    public final float[] BG_NO_PRESSED;
    public final float[] BG_PRESSED;
    private float pressedAlpha;

    public ClickableImageView(Context context) {
        super(context);
        this.BG_PRESSED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, -50.0f, 0.0f, 1.0f, 0.0f, 0.0f, -50.0f, 0.0f, 0.0f, 1.0f, 0.0f, -50.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
        this.BG_NO_PRESSED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    }

    public ClickableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.BG_PRESSED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, -50.0f, 0.0f, 1.0f, 0.0f, 0.0f, -50.0f, 0.0f, 0.0f, 1.0f, 0.0f, -50.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
        this.BG_NO_PRESSED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ClickableImageView);
        this.pressedAlpha = obtainStyledAttributes.getFloat(R.styleable.ClickableImageView_pressed_alpha, 0.5f);
        obtainStyledAttributes.recycle();
    }

    public ClickableImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.BG_PRESSED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, -50.0f, 0.0f, 1.0f, 0.0f, 0.0f, -50.0f, 0.0f, 0.0f, 1.0f, 0.0f, -50.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
        this.BG_NO_PRESSED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        updateView(z);
        super.setPressed(z);
    }

    private void updateView(boolean z) {
        if (z) {
            setDrawingCacheEnabled(true);
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(this.pressedAlpha);
            setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            getDrawable().setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            return;
        }
        setColorFilter(new ColorMatrixColorFilter(this.BG_NO_PRESSED));
        getDrawable().setColorFilter(new ColorMatrixColorFilter(this.BG_NO_PRESSED));
    }
}
