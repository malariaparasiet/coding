package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class ActivityImageTextListBinding implements ViewBinding {
    public final ConstraintLayout clBtn;
    public final ConstraintLayout ilTextToolbar;
    public final CustomImageView ivBack;
    public final AppCompatImageView ivDelete;
    public final AppCompatImageView ivEdit;
    public final CustomImageView ivMiddle;
    public final CustomImageView ivRight;
    public final CustomImageView ivRightSubtitle;
    private final ConstraintLayout rootView;
    public final RecyclerView rvList;
    public final TextView tvRight;
    public final TextView tvTitle;

    private ActivityImageTextListBinding(ConstraintLayout rootView, ConstraintLayout clBtn, ConstraintLayout ilTextToolbar, CustomImageView ivBack, AppCompatImageView ivDelete, AppCompatImageView ivEdit, CustomImageView ivMiddle, CustomImageView ivRight, CustomImageView ivRightSubtitle, RecyclerView rvList, TextView tvRight, TextView tvTitle) {
        this.rootView = rootView;
        this.clBtn = clBtn;
        this.ilTextToolbar = ilTextToolbar;
        this.ivBack = ivBack;
        this.ivDelete = ivDelete;
        this.ivEdit = ivEdit;
        this.ivMiddle = ivMiddle;
        this.ivRight = ivRight;
        this.ivRightSubtitle = ivRightSubtitle;
        this.rvList = rvList;
        this.tvRight = tvRight;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityImageTextListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityImageTextListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_image_text_list, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityImageTextListBinding bind(View rootView) {
        int i = R.id.cl_btn;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_btn);
        if (constraintLayout != null) {
            i = R.id.il_text_toolbar;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_text_toolbar);
            if (constraintLayout2 != null) {
                i = R.id.iv_back;
                CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_back);
                if (customImageView != null) {
                    i = R.id.iv_delete;
                    AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.iv_delete);
                    if (appCompatImageView != null) {
                        i = R.id.iv_edit;
                        AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.iv_edit);
                        if (appCompatImageView2 != null) {
                            i = R.id.iv_middle;
                            CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_middle);
                            if (customImageView2 != null) {
                                i = R.id.iv_right;
                                CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right);
                                if (customImageView3 != null) {
                                    i = R.id.iv_right_subtitle;
                                    CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right_subtitle);
                                    if (customImageView4 != null) {
                                        i = R.id.rv_list;
                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rv_list);
                                        if (recyclerView != null) {
                                            i = R.id.tv_right;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_right);
                                            if (textView != null) {
                                                i = R.id.tv_title;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                                                if (textView2 != null) {
                                                    return new ActivityImageTextListBinding((ConstraintLayout) rootView, constraintLayout, constraintLayout2, customImageView, appCompatImageView, appCompatImageView2, customImageView2, customImageView3, customImageView4, recyclerView, textView, textView2);
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
