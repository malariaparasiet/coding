package com.wifiled.baselib.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.wifiled.baselib.R;

/* loaded from: classes2.dex */
public final class LayoutStatusLayoutManagerLoadingBinding implements ViewBinding {
    public final LottieAnimationView lottieLoading;
    private final ConstraintLayout rootView;

    private LayoutStatusLayoutManagerLoadingBinding(ConstraintLayout constraintLayout, LottieAnimationView lottieAnimationView) {
        this.rootView = constraintLayout;
        this.lottieLoading = lottieAnimationView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutStatusLayoutManagerLoadingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutStatusLayoutManagerLoadingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_status_layout_manager_loading, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutStatusLayoutManagerLoadingBinding bind(View view) {
        int i = R.id.lottie_loading;
        LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
        if (lottieAnimationView != null) {
            return new LayoutStatusLayoutManagerLoadingBinding((ConstraintLayout) view, lottieAnimationView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
