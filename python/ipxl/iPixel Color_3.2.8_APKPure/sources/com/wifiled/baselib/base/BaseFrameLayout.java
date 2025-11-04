package com.wifiled.baselib.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* loaded from: classes2.dex */
public abstract class BaseFrameLayout extends FrameLayout {
    protected abstract void bindData();

    protected void bindListener() {
    }

    protected abstract int layoutId();

    public BaseFrameLayout(Context context) {
        super(context);
        bindLayout(context, null, 0);
    }

    public BaseFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        bindLayout(context, attributeSet, 0);
    }

    public BaseFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        bindLayout(context, attributeSet, i);
    }

    protected void bindLayout(Context context, AttributeSet attributeSet, int i) {
        LayoutInflater.from(context).inflate(layoutId(), (ViewGroup) this, true);
        bindData();
        bindListener();
    }
}
