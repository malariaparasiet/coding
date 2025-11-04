package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewFive12;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewFour12;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewOne12;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewThree12;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewTwo12;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class ActivityEditTemplateBinding implements ViewBinding {
    public final ConstraintLayout borderCl;
    public final ConstraintLayout ilTextToolbar;
    public final CustomImageView ivBack;
    public final ImageView ivBorder;
    public final ImageView ivBorderNext;
    public final CustomImageView ivMiddle;
    public final ImageView ivNext;
    public final CustomImageView ivRight;
    public final CustomImageView ivRightSubtitle;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TemplateViewOne12 template1;
    public final TemplateViewTwo12 template2;
    public final TemplateViewThree12 template3;
    public final ConstraintLayout template3264;
    public final TemplateViewFour12 template4;
    public final TemplateViewFive12 template5;
    public final TextView textBorder;
    public final TextView tvTitle;
    public final View view;

    private ActivityEditTemplateBinding(ConstraintLayout rootView, ConstraintLayout borderCl, ConstraintLayout ilTextToolbar, CustomImageView ivBack, ImageView ivBorder, ImageView ivBorderNext, CustomImageView ivMiddle, ImageView ivNext, CustomImageView ivRight, CustomImageView ivRightSubtitle, ConstraintLayout main, TemplateViewOne12 template1, TemplateViewTwo12 template2, TemplateViewThree12 template3, ConstraintLayout template3264, TemplateViewFour12 template4, TemplateViewFive12 template5, TextView textBorder, TextView tvTitle, View view) {
        this.rootView = rootView;
        this.borderCl = borderCl;
        this.ilTextToolbar = ilTextToolbar;
        this.ivBack = ivBack;
        this.ivBorder = ivBorder;
        this.ivBorderNext = ivBorderNext;
        this.ivMiddle = ivMiddle;
        this.ivNext = ivNext;
        this.ivRight = ivRight;
        this.ivRightSubtitle = ivRightSubtitle;
        this.main = main;
        this.template1 = template1;
        this.template2 = template2;
        this.template3 = template3;
        this.template3264 = template3264;
        this.template4 = template4;
        this.template5 = template5;
        this.textBorder = textBorder;
        this.tvTitle = tvTitle;
        this.view = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityEditTemplateBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityEditTemplateBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_edit_template, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityEditTemplateBinding bind(View rootView) {
        int i = R.id.border_cl;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.border_cl);
        if (constraintLayout != null) {
            i = R.id.il_text_toolbar;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_text_toolbar);
            if (constraintLayout2 != null) {
                i = R.id.iv_back;
                CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_back);
                if (customImageView != null) {
                    i = R.id.iv_border;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_border);
                    if (imageView != null) {
                        i = R.id.iv_border_next;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_border_next);
                        if (imageView2 != null) {
                            i = R.id.iv_middle;
                            CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_middle);
                            if (customImageView2 != null) {
                                i = R.id.iv_next;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_next);
                                if (imageView3 != null) {
                                    i = R.id.iv_right;
                                    CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right);
                                    if (customImageView3 != null) {
                                        i = R.id.iv_right_subtitle;
                                        CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right_subtitle);
                                        if (customImageView4 != null) {
                                            ConstraintLayout constraintLayout3 = (ConstraintLayout) rootView;
                                            i = R.id.template1;
                                            TemplateViewOne12 templateViewOne12 = (TemplateViewOne12) ViewBindings.findChildViewById(rootView, R.id.template1);
                                            if (templateViewOne12 != null) {
                                                i = R.id.template2;
                                                TemplateViewTwo12 templateViewTwo12 = (TemplateViewTwo12) ViewBindings.findChildViewById(rootView, R.id.template2);
                                                if (templateViewTwo12 != null) {
                                                    i = R.id.template3;
                                                    TemplateViewThree12 templateViewThree12 = (TemplateViewThree12) ViewBindings.findChildViewById(rootView, R.id.template3);
                                                    if (templateViewThree12 != null) {
                                                        i = R.id.template_3264;
                                                        ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.template_3264);
                                                        if (constraintLayout4 != null) {
                                                            i = R.id.template4;
                                                            TemplateViewFour12 templateViewFour12 = (TemplateViewFour12) ViewBindings.findChildViewById(rootView, R.id.template4);
                                                            if (templateViewFour12 != null) {
                                                                i = R.id.template5;
                                                                TemplateViewFive12 templateViewFive12 = (TemplateViewFive12) ViewBindings.findChildViewById(rootView, R.id.template5);
                                                                if (templateViewFive12 != null) {
                                                                    i = R.id.text_border;
                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.text_border);
                                                                    if (textView != null) {
                                                                        i = R.id.tv_title;
                                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                                                                        if (textView2 != null) {
                                                                            i = R.id.view;
                                                                            View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.view);
                                                                            if (findChildViewById != null) {
                                                                                return new ActivityEditTemplateBinding(constraintLayout3, constraintLayout, constraintLayout2, customImageView, imageView, imageView2, customImageView2, imageView3, customImageView3, customImageView4, constraintLayout3, templateViewOne12, templateViewTwo12, templateViewThree12, constraintLayout4, templateViewFour12, templateViewFive12, textView, textView2, findChildViewById);
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
