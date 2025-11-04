package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class ActivitySelectTemplateBinding implements ViewBinding {
    public final ConstraintLayout ilTextToolbar;
    public final CustomImageView ivBack;
    public final CustomImageView ivMiddle;
    public final CustomImageView ivRight;
    public final CustomImageView ivRightSubtitle;
    public final NestedScrollView main;
    private final NestedScrollView rootView;
    public final ConstraintLayout tem1;
    public final ImageView tem1Iv1;
    public final ImageView tem1Iv2;
    public final ImageView tem1Iv3;
    public final ImageView tem1Iv4;
    public final ImageView tem1Iv5;
    public final ImageView tem1Iv6;
    public final TextView tem1Tv1;
    public final TextView tem1Tv2;
    public final TextView tem1Tv3;
    public final TextView tem1Tv4;
    public final TextView tem1Tv5;
    public final TextView tem1Tv6;
    public final TextView tem1Tv7;
    public final TextView tem1Tv8;
    public final ConstraintLayout tem2;
    public final ConstraintLayout tem3;
    public final ConstraintLayout tem4;
    public final ConstraintLayout tem5;
    public final ConstraintLayout tem6;
    public final TextView tvTitle;

    private ActivitySelectTemplateBinding(NestedScrollView rootView, ConstraintLayout ilTextToolbar, CustomImageView ivBack, CustomImageView ivMiddle, CustomImageView ivRight, CustomImageView ivRightSubtitle, NestedScrollView main, ConstraintLayout tem1, ImageView tem1Iv1, ImageView tem1Iv2, ImageView tem1Iv3, ImageView tem1Iv4, ImageView tem1Iv5, ImageView tem1Iv6, TextView tem1Tv1, TextView tem1Tv2, TextView tem1Tv3, TextView tem1Tv4, TextView tem1Tv5, TextView tem1Tv6, TextView tem1Tv7, TextView tem1Tv8, ConstraintLayout tem2, ConstraintLayout tem3, ConstraintLayout tem4, ConstraintLayout tem5, ConstraintLayout tem6, TextView tvTitle) {
        this.rootView = rootView;
        this.ilTextToolbar = ilTextToolbar;
        this.ivBack = ivBack;
        this.ivMiddle = ivMiddle;
        this.ivRight = ivRight;
        this.ivRightSubtitle = ivRightSubtitle;
        this.main = main;
        this.tem1 = tem1;
        this.tem1Iv1 = tem1Iv1;
        this.tem1Iv2 = tem1Iv2;
        this.tem1Iv3 = tem1Iv3;
        this.tem1Iv4 = tem1Iv4;
        this.tem1Iv5 = tem1Iv5;
        this.tem1Iv6 = tem1Iv6;
        this.tem1Tv1 = tem1Tv1;
        this.tem1Tv2 = tem1Tv2;
        this.tem1Tv3 = tem1Tv3;
        this.tem1Tv4 = tem1Tv4;
        this.tem1Tv5 = tem1Tv5;
        this.tem1Tv6 = tem1Tv6;
        this.tem1Tv7 = tem1Tv7;
        this.tem1Tv8 = tem1Tv8;
        this.tem2 = tem2;
        this.tem3 = tem3;
        this.tem4 = tem4;
        this.tem5 = tem5;
        this.tem6 = tem6;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    public static ActivitySelectTemplateBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySelectTemplateBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_select_template, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivitySelectTemplateBinding bind(View rootView) {
        int i = R.id.il_text_toolbar;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_text_toolbar);
        if (constraintLayout != null) {
            i = R.id.iv_back;
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
                            NestedScrollView nestedScrollView = (NestedScrollView) rootView;
                            i = R.id.tem_1;
                            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.tem_1);
                            if (constraintLayout2 != null) {
                                i = R.id.tem1_iv1;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tem1_iv1);
                                if (imageView != null) {
                                    i = R.id.tem1_iv2;
                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tem1_iv2);
                                    if (imageView2 != null) {
                                        i = R.id.tem1_iv3;
                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tem1_iv3);
                                        if (imageView3 != null) {
                                            i = R.id.tem1_iv4;
                                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tem1_iv4);
                                            if (imageView4 != null) {
                                                i = R.id.tem1_iv5;
                                                ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tem1_iv5);
                                                if (imageView5 != null) {
                                                    i = R.id.tem1_iv6;
                                                    ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tem1_iv6);
                                                    if (imageView6 != null) {
                                                        i = R.id.tem1_tv1;
                                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tem1_tv1);
                                                        if (textView != null) {
                                                            i = R.id.tem1_tv2;
                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tem1_tv2);
                                                            if (textView2 != null) {
                                                                i = R.id.tem1_tv3;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tem1_tv3);
                                                                if (textView3 != null) {
                                                                    i = R.id.tem1_tv4;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tem1_tv4);
                                                                    if (textView4 != null) {
                                                                        i = R.id.tem1_tv5;
                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tem1_tv5);
                                                                        if (textView5 != null) {
                                                                            i = R.id.tem1_tv6;
                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tem1_tv6);
                                                                            if (textView6 != null) {
                                                                                i = R.id.tem1_tv7;
                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tem1_tv7);
                                                                                if (textView7 != null) {
                                                                                    i = R.id.tem1_tv8;
                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tem1_tv8);
                                                                                    if (textView8 != null) {
                                                                                        i = R.id.tem_2;
                                                                                        ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.tem_2);
                                                                                        if (constraintLayout3 != null) {
                                                                                            i = R.id.tem_3;
                                                                                            ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.tem_3);
                                                                                            if (constraintLayout4 != null) {
                                                                                                i = R.id.tem_4;
                                                                                                ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.tem_4);
                                                                                                if (constraintLayout5 != null) {
                                                                                                    i = R.id.tem_5;
                                                                                                    ConstraintLayout constraintLayout6 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.tem_5);
                                                                                                    if (constraintLayout6 != null) {
                                                                                                        i = R.id.tem_6;
                                                                                                        ConstraintLayout constraintLayout7 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.tem_6);
                                                                                                        if (constraintLayout7 != null) {
                                                                                                            i = R.id.tv_title;
                                                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                                                                                                            if (textView9 != null) {
                                                                                                                return new ActivitySelectTemplateBinding(nestedScrollView, constraintLayout, customImageView, customImageView2, customImageView3, customImageView4, nestedScrollView, constraintLayout2, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, constraintLayout3, constraintLayout4, constraintLayout5, constraintLayout6, constraintLayout7, textView9);
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
