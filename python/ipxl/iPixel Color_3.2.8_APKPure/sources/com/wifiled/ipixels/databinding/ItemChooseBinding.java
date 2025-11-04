package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class ItemChooseBinding implements ViewBinding {
    public final CustomImageView ivCategory;
    public final ConstraintLayout llMainpage;
    private final ConstraintLayout rootView;
    public final TextView tvCategory;

    private ItemChooseBinding(ConstraintLayout rootView, CustomImageView ivCategory, ConstraintLayout llMainpage, TextView tvCategory) {
        this.rootView = rootView;
        this.ivCategory = ivCategory;
        this.llMainpage = llMainpage;
        this.tvCategory = tvCategory;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemChooseBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemChooseBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_choose, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemChooseBinding bind(View rootView) {
        int i = R.id.iv_category;
        CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_category);
        if (customImageView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_category);
            if (textView != null) {
                return new ItemChooseBinding(constraintLayout, customImageView, constraintLayout, textView);
            }
            i = R.id.tv_category;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
