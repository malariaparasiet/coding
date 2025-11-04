package com.wifiled.baselib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.wifiled.baselib.R;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class BannerView extends LinearLayout {
    private Drawable drawableFocus;
    private Drawable drawableUnfocus;
    private ArrayList<ImageView> tips;

    public BannerView(Context context) {
        this(context, null, 0);
    }

    public BannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.tips = new ArrayList<>();
        init(context, attributeSet);
    }

    public BannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.tips = new ArrayList<>();
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BannerView);
        this.drawableFocus = obtainStyledAttributes.getDrawable(R.styleable.BannerView_focus_drawable);
        this.drawableUnfocus = obtainStyledAttributes.getDrawable(R.styleable.BannerView_unfoucs_drawable);
        obtainStyledAttributes.recycle();
    }

    public void addBannerDot(int i, int i2, int i3) {
        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(i, i2));
        this.tips.add(imageView);
        imageView.setImageDrawable(this.drawableFocus);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(-2, -2));
        layoutParams.leftMargin = i3;
        layoutParams.rightMargin = i3;
        addView(imageView, layoutParams);
    }

    public void removeBannerDot(int i) {
        if (this.tips.size() > i) {
            this.tips.remove(i);
        }
        if (getChildCount() > i) {
            removeViewAt(i);
        }
    }

    public void setFocusDot(int i) {
        for (int i2 = 0; i2 < this.tips.size(); i2++) {
            if (i2 == i) {
                this.tips.get(i2).setImageDrawable(this.drawableFocus);
            } else {
                this.tips.get(i2).setImageDrawable(this.drawableUnfocus);
            }
        }
    }

    public int getDotSize() {
        return this.tips.size();
    }
}
