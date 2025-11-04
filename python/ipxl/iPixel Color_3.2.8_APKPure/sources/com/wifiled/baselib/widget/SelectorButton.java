package com.wifiled.baselib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatButton;
import com.wifiled.baselib.R;

/* loaded from: classes2.dex */
public class SelectorButton extends AppCompatButton {
    public SelectorButton(Context context) {
        super(context);
    }

    public SelectorButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SelectorButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        StateListDrawable stateListDrawable = new StateListDrawable();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SelectorButton);
        int indexCount = obtainStyledAttributes.getIndexCount();
        Drawable drawable = null;
        Drawable drawable2 = null;
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == R.styleable.SelectorButton_normal_drawable) {
                drawable2 = obtainStyledAttributes.getDrawable(index);
            } else if (index == R.styleable.SelectorButton_pressed_drawable) {
                drawable = obtainStyledAttributes.getDrawable(index);
            }
        }
        if (indexCount >= 2) {
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, drawable);
            stateListDrawable.addState(new int[]{android.R.attr.state_focused}, drawable);
            stateListDrawable.addState(new int[0], drawable2);
            setBackgroundDrawable(stateListDrawable);
        }
        obtainStyledAttributes.recycle();
    }

    public void setSelecorDrawable(Drawable drawable, Drawable drawable2) {
        System.out.println("nn:" + drawable);
        System.out.println("pp:" + drawable2);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, drawable2);
        stateListDrawable.addState(new int[]{android.R.attr.state_focused}, drawable2);
        stateListDrawable.addState(new int[0], drawable);
        setBackgroundDrawable(stateListDrawable);
    }
}
