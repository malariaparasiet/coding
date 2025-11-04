package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ActivityDiyBinding implements ViewBinding {
    public final FrameLayout navigationContainer;
    private final FrameLayout rootView;

    private ActivityDiyBinding(FrameLayout rootView, FrameLayout navigationContainer) {
        this.rootView = rootView;
        this.navigationContainer = navigationContainer;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDiyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDiyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_diy, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityDiyBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        FrameLayout frameLayout = (FrameLayout) rootView;
        return new ActivityDiyBinding(frameLayout, frameLayout);
    }
}
