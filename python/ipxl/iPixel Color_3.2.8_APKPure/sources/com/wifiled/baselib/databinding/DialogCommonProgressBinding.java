package com.wifiled.baselib.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.airbnb.lottie.LottieAnimationView;
import com.wifiled.baselib.R;

/* loaded from: classes2.dex */
public final class DialogCommonProgressBinding implements ViewBinding {
    public final LottieAnimationView lottieProgress;
    private final LottieAnimationView rootView;

    private DialogCommonProgressBinding(LottieAnimationView lottieAnimationView, LottieAnimationView lottieAnimationView2) {
        this.rootView = lottieAnimationView;
        this.lottieProgress = lottieAnimationView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LottieAnimationView getRoot() {
        return this.rootView;
    }

    public static DialogCommonProgressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogCommonProgressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_common_progress, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogCommonProgressBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        LottieAnimationView lottieAnimationView = (LottieAnimationView) view;
        return new DialogCommonProgressBinding(lottieAnimationView, lottieAnimationView);
    }
}
