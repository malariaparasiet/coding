package com.wifiled.baselib.uicode.roundview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.wifiled.baselib.R;

/* loaded from: classes2.dex */
public class RoundViewDelegate {
    private int backgroundColor;
    private int backgroundPressColor;
    private Context context;
    private int cornerRadius;
    private int cornerRadius_BL;
    private int cornerRadius_BR;
    private int cornerRadius_TL;
    private int cornerRadius_TR;
    private boolean isRadiusHalfHeight;
    private boolean isRippleEnable;
    private boolean isWidthHeightEqual;
    private int strokeColor;
    private int strokePressColor;
    private int strokeWidth;
    private int textPressColor;
    private View view;
    private GradientDrawable gd_background = new GradientDrawable();
    private GradientDrawable gd_background_press = new GradientDrawable();
    private float[] radiusArr = new float[8];

    public RoundViewDelegate(View view, Context context, AttributeSet attributeSet) {
        this.view = view;
        this.context = context;
        obtainAttributes(context, attributeSet);
    }

    private void obtainAttributes(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundTextView);
        this.backgroundColor = obtainStyledAttributes.getColor(R.styleable.RoundTextView_rv_backgroundColor, 0);
        this.backgroundPressColor = obtainStyledAttributes.getColor(R.styleable.RoundTextView_rv_backgroundPressColor, Integer.MAX_VALUE);
        this.cornerRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius, 0);
        this.strokeWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundTextView_rv_strokeWidth, 0);
        this.strokeColor = obtainStyledAttributes.getColor(R.styleable.RoundTextView_rv_strokeColor, 0);
        this.strokePressColor = obtainStyledAttributes.getColor(R.styleable.RoundTextView_rv_strokePressColor, Integer.MAX_VALUE);
        this.textPressColor = obtainStyledAttributes.getColor(R.styleable.RoundTextView_rv_textPressColor, Integer.MAX_VALUE);
        this.isRadiusHalfHeight = obtainStyledAttributes.getBoolean(R.styleable.RoundTextView_rv_isRadiusHalfHeight, false);
        this.isWidthHeightEqual = obtainStyledAttributes.getBoolean(R.styleable.RoundTextView_rv_isWidthHeightEqual, false);
        this.cornerRadius_TL = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_TL, 0);
        this.cornerRadius_TR = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_TR, 0);
        this.cornerRadius_BL = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_BL, 0);
        this.cornerRadius_BR = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_BR, 0);
        this.isRippleEnable = obtainStyledAttributes.getBoolean(R.styleable.RoundTextView_rv_isRippleEnable, true);
        obtainStyledAttributes.recycle();
    }

    public void setBackgroundColor(int i) {
        this.backgroundColor = i;
        setBgSelector();
    }

    public void setBackgroundPressColor(int i) {
        this.backgroundPressColor = i;
        setBgSelector();
    }

    public void setCornerRadius(int i) {
        this.cornerRadius = dp2px(i);
        setBgSelector();
    }

    public void setStrokeWidth(int i) {
        this.strokeWidth = dp2px(i);
        setBgSelector();
    }

    public void setStrokeColor(int i) {
        this.strokeColor = i;
        setBgSelector();
    }

    public void setStrokePressColor(int i) {
        this.strokePressColor = i;
        setBgSelector();
    }

    public void setTextPressColor(int i) {
        this.textPressColor = i;
        setBgSelector();
    }

    public void setIsRadiusHalfHeight(boolean z) {
        this.isRadiusHalfHeight = z;
        setBgSelector();
    }

    public void setIsWidthHeightEqual(boolean z) {
        this.isWidthHeightEqual = z;
        setBgSelector();
    }

    public void setCornerRadius_TL(int i) {
        this.cornerRadius_TL = i;
        setBgSelector();
    }

    public void setCornerRadius_TR(int i) {
        this.cornerRadius_TR = i;
        setBgSelector();
    }

    public void setCornerRadius_BL(int i) {
        this.cornerRadius_BL = i;
        setBgSelector();
    }

    public void setCornerRadius_BR(int i) {
        this.cornerRadius_BR = i;
        setBgSelector();
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public int getBackgroundPressColor() {
        return this.backgroundPressColor;
    }

    public int getCornerRadius() {
        return this.cornerRadius;
    }

    public int getStrokeWidth() {
        return this.strokeWidth;
    }

    public int getStrokeColor() {
        return this.strokeColor;
    }

    public int getStrokePressColor() {
        return this.strokePressColor;
    }

    public int getTextPressColor() {
        return this.textPressColor;
    }

    public boolean isRadiusHalfHeight() {
        return this.isRadiusHalfHeight;
    }

    public boolean isWidthHeightEqual() {
        return this.isWidthHeightEqual;
    }

    public int getCornerRadius_TL() {
        return this.cornerRadius_TL;
    }

    public int getCornerRadius_TR() {
        return this.cornerRadius_TR;
    }

    public int getCornerRadius_BL() {
        return this.cornerRadius_BL;
    }

    public int getCornerRadius_BR() {
        return this.cornerRadius_BR;
    }

    protected int dp2px(float f) {
        return (int) ((f * this.context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    protected int sp2px(float f) {
        return (int) ((f * this.context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    private void setDrawable(GradientDrawable gradientDrawable, int i, int i2) {
        gradientDrawable.setColor(i);
        int i3 = this.cornerRadius_TL;
        if (i3 > 0 || this.cornerRadius_TR > 0 || this.cornerRadius_BR > 0 || this.cornerRadius_BL > 0) {
            float[] fArr = this.radiusArr;
            fArr[0] = i3;
            fArr[1] = i3;
            int i4 = this.cornerRadius_TR;
            fArr[2] = i4;
            fArr[3] = i4;
            int i5 = this.cornerRadius_BR;
            fArr[4] = i5;
            fArr[5] = i5;
            int i6 = this.cornerRadius_BL;
            fArr[6] = i6;
            fArr[7] = i6;
            gradientDrawable.setCornerRadii(fArr);
        } else {
            gradientDrawable.setCornerRadius(this.cornerRadius);
        }
        gradientDrawable.setStroke(this.strokeWidth, i2);
    }

    public void setBgSelector() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (this.isRippleEnable) {
            setDrawable(this.gd_background, this.backgroundColor, this.strokeColor);
            this.view.setBackground(new RippleDrawable(getPressedColorSelector(this.backgroundColor, this.backgroundPressColor), this.gd_background, null));
        } else {
            setDrawable(this.gd_background, this.backgroundColor, this.strokeColor);
            stateListDrawable.addState(new int[]{-16842919}, this.gd_background);
            int i = this.backgroundPressColor;
            if (i != Integer.MAX_VALUE || this.strokePressColor != Integer.MAX_VALUE) {
                GradientDrawable gradientDrawable = this.gd_background_press;
                if (i == Integer.MAX_VALUE) {
                    i = this.backgroundColor;
                }
                int i2 = this.strokePressColor;
                if (i2 == Integer.MAX_VALUE) {
                    i2 = this.strokeColor;
                }
                setDrawable(gradientDrawable, i, i2);
                stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, this.gd_background_press);
            }
            this.view.setBackground(stateListDrawable);
        }
        View view = this.view;
        if (!(view instanceof TextView) || this.textPressColor == Integer.MAX_VALUE) {
            return;
        }
        ((TextView) this.view).setTextColor(new ColorStateList(new int[][]{new int[]{-16842919}, new int[]{android.R.attr.state_pressed}}, new int[]{((TextView) view).getTextColors().getDefaultColor(), this.textPressColor}));
    }

    private ColorStateList getPressedColorSelector(int i, int i2) {
        return new ColorStateList(new int[][]{new int[]{android.R.attr.state_pressed}, new int[]{android.R.attr.state_focused}, new int[]{android.R.attr.state_activated}, new int[0]}, new int[]{i2, i2, i2, i});
    }
}
