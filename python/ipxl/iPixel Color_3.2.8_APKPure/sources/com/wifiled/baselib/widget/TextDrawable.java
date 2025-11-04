package com.wifiled.baselib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.wifiled.baselib.R;

/* loaded from: classes2.dex */
public class TextDrawable extends AppCompatTextView {
    private Drawable drawableLeft;
    private Drawable drawableRight;
    private Drawable drawableTop;
    private int leftHeight;
    private int leftWidth;
    private Context mContext;
    private int rightHeight;
    private int rightWidth;
    private int topHeight;
    private int topWidth;

    public TextDrawable(Context context) {
        this(context, null, 0);
        this.mContext = context;
    }

    public TextDrawable(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.mContext = context;
    }

    public TextDrawable(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TextDrawable);
        this.drawableLeft = obtainStyledAttributes.getDrawable(R.styleable.TextDrawable_leftDrawable);
        this.drawableRight = obtainStyledAttributes.getDrawable(R.styleable.TextDrawable_rightDrawable);
        this.drawableTop = obtainStyledAttributes.getDrawable(R.styleable.TextDrawable_topDrawable);
        if (this.drawableLeft != null) {
            this.leftWidth = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.TextDrawable_leftDrawableWidth, dip2px(context, 32.0f));
            this.leftHeight = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.TextDrawable_leftDrawableHeight, dip2px(context, 32.0f));
        }
        if (this.drawableRight != null) {
            this.rightWidth = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.TextDrawable_rightDrawableWidth, dip2px(context, 20.0f));
            this.rightHeight = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.TextDrawable_rightDrawableHeight, dip2px(context, 20.0f));
        }
        if (this.drawableTop != null) {
            this.topWidth = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.TextDrawable_topDrawableWidth, dip2px(context, 20.0f));
            this.topHeight = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.TextDrawable_topDrawableHeight, dip2px(context, 20.0f));
        }
        obtainStyledAttributes.recycle();
    }

    public int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Drawable drawable = this.drawableLeft;
        if (drawable != null) {
            drawable.setBounds(0, 0, this.leftWidth, this.leftHeight);
        }
        Drawable drawable2 = this.drawableRight;
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, this.rightWidth, this.rightHeight);
        }
        Drawable drawable3 = this.drawableTop;
        if (drawable3 != null) {
            drawable3.setBounds(0, 0, this.topWidth, this.topHeight);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setCompoundDrawables(this.drawableLeft, this.drawableTop, this.drawableRight, null);
    }

    public void setDrawableLeft(Drawable drawable) {
        this.drawableLeft = drawable;
        invalidate();
    }

    public void setDrawableLeft(int i) {
        this.drawableLeft = this.mContext.getResources().getDrawable(i);
        invalidate();
    }

    public void setDrawableRight(Drawable drawable) {
        this.drawableRight = this.drawableLeft;
        invalidate();
    }

    public void setDrawableRight(int i) {
        this.drawableRight = this.mContext.getResources().getDrawable(i);
        invalidate();
    }

    public void setDrawable(Drawable drawable) {
        this.drawableTop = drawable;
        invalidate();
    }

    public void setDrawableTop(int i) {
        this.drawableTop = this.mContext.getResources().getDrawable(i);
        invalidate();
    }
}
