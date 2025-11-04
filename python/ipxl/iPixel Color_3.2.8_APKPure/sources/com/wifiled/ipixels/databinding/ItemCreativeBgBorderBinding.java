package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ItemCreativeBgBorderBinding implements ViewBinding {
    public final ConstraintLayout bgLayout;
    public final AppCompatImageView ivBorder;
    private final ConstraintLayout rootView;

    private ItemCreativeBgBorderBinding(ConstraintLayout rootView, ConstraintLayout bgLayout, AppCompatImageView ivBorder) {
        this.rootView = rootView;
        this.bgLayout = bgLayout;
        this.ivBorder = ivBorder;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemCreativeBgBorderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemCreativeBgBorderBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_creative_bg_border, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemCreativeBgBorderBinding bind(View rootView) {
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.iv_border);
        if (appCompatImageView != null) {
            return new ItemCreativeBgBorderBinding(constraintLayout, constraintLayout, appCompatImageView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.iv_border)));
    }
}
