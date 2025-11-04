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
public final class ToolbarLayoutBinding implements ViewBinding {
    public final CustomImageView ivBack;
    public final CustomImageView ivMiddle;
    public final CustomImageView ivRight;
    public final CustomImageView ivRightSubtitle;
    private final ConstraintLayout rootView;
    public final ConstraintLayout titleBar;
    public final TextView tvRight;
    public final TextView tvTitle;

    private ToolbarLayoutBinding(ConstraintLayout rootView, CustomImageView ivBack, CustomImageView ivMiddle, CustomImageView ivRight, CustomImageView ivRightSubtitle, ConstraintLayout titleBar, TextView tvRight, TextView tvTitle) {
        this.rootView = rootView;
        this.ivBack = ivBack;
        this.ivMiddle = ivMiddle;
        this.ivRight = ivRight;
        this.ivRightSubtitle = ivRightSubtitle;
        this.titleBar = titleBar;
        this.tvRight = tvRight;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ToolbarLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ToolbarLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.toolbar_layout, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ToolbarLayoutBinding bind(View rootView) {
        int i = R.id.iv_back;
        CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_back);
        if (customImageView != null) {
            i = R.id.iv_middle;
            CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_middle);
            if (customImageView2 != null) {
                i = R.id.iv_right;
                CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right);
                if (customImageView3 != null) {
                    i = R.id.iv_right_subtitle;
                    CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right_subtitle);
                    if (customImageView4 != null) {
                        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                        i = R.id.tv_right;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_right);
                        if (textView != null) {
                            i = R.id.tv_title;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                            if (textView2 != null) {
                                return new ToolbarLayoutBinding(constraintLayout, customImageView, customImageView2, customImageView3, customImageView4, constraintLayout, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
