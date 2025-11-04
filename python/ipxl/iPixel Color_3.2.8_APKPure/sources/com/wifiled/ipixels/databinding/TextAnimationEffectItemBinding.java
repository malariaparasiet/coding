package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class TextAnimationEffectItemBinding implements ViewBinding {
    public final ImageView ivAnima;
    public final RelativeLayout rlBg;
    private final ConstraintLayout rootView;

    private TextAnimationEffectItemBinding(ConstraintLayout rootView, ImageView ivAnima, RelativeLayout rlBg) {
        this.rootView = rootView;
        this.ivAnima = ivAnima;
        this.rlBg = rlBg;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TextAnimationEffectItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static TextAnimationEffectItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.text_animation_effect_item, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static TextAnimationEffectItemBinding bind(View rootView) {
        int i = R.id.iv_anima;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_anima);
        if (imageView != null) {
            i = R.id.rl_bg;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_bg);
            if (relativeLayout != null) {
                return new TextAnimationEffectItemBinding((ConstraintLayout) rootView, imageView, relativeLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
