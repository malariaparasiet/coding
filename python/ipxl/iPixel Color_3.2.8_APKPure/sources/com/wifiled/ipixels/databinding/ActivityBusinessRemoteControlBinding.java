package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class ActivityBusinessRemoteControlBinding implements ViewBinding {
    public final CustomImageView customImageView;
    public final ConstraintLayout ilTextToolbar;
    public final CustomImageView ivBack;
    public final CustomImageView ivMiddle;
    public final ImageView ivReset;
    public final CustomImageView ivRight;
    public final CustomImageView ivRightSubtitle;
    public final ImageView ivSend;
    public final RecyclerView localRecyclerview;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView tvRight;
    public final TextView tvTitle;

    private ActivityBusinessRemoteControlBinding(ConstraintLayout rootView, CustomImageView customImageView, ConstraintLayout ilTextToolbar, CustomImageView ivBack, CustomImageView ivMiddle, ImageView ivReset, CustomImageView ivRight, CustomImageView ivRightSubtitle, ImageView ivSend, RecyclerView localRecyclerview, ConstraintLayout main, TextView tvRight, TextView tvTitle) {
        this.rootView = rootView;
        this.customImageView = customImageView;
        this.ilTextToolbar = ilTextToolbar;
        this.ivBack = ivBack;
        this.ivMiddle = ivMiddle;
        this.ivReset = ivReset;
        this.ivRight = ivRight;
        this.ivRightSubtitle = ivRightSubtitle;
        this.ivSend = ivSend;
        this.localRecyclerview = localRecyclerview;
        this.main = main;
        this.tvRight = tvRight;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityBusinessRemoteControlBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityBusinessRemoteControlBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_business_remote_control, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityBusinessRemoteControlBinding bind(View rootView) {
        int i = R.id.customImageView;
        CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.customImageView);
        if (customImageView != null) {
            i = R.id.il_text_toolbar;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_text_toolbar);
            if (constraintLayout != null) {
                i = R.id.iv_back;
                CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_back);
                if (customImageView2 != null) {
                    i = R.id.iv_middle;
                    CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_middle);
                    if (customImageView3 != null) {
                        i = R.id.iv_reset;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_reset);
                        if (imageView != null) {
                            i = R.id.iv_right;
                            CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right);
                            if (customImageView4 != null) {
                                i = R.id.iv_right_subtitle;
                                CustomImageView customImageView5 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right_subtitle);
                                if (customImageView5 != null) {
                                    i = R.id.iv_send;
                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_send);
                                    if (imageView2 != null) {
                                        i = R.id.localRecyclerview;
                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.localRecyclerview);
                                        if (recyclerView != null) {
                                            ConstraintLayout constraintLayout2 = (ConstraintLayout) rootView;
                                            i = R.id.tv_right;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_right);
                                            if (textView != null) {
                                                i = R.id.tv_title;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                                                if (textView2 != null) {
                                                    return new ActivityBusinessRemoteControlBinding(constraintLayout2, customImageView, constraintLayout, customImageView2, customImageView3, imageView, customImageView4, customImageView5, imageView2, recyclerView, constraintLayout2, textView, textView2);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
