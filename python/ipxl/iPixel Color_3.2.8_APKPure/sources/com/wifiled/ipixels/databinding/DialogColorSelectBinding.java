package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.RoundColorPaletteHSV360;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class DialogColorSelectBinding implements ViewBinding {
    public final ConstraintLayout clTextGradient;
    public final RoundColorPaletteHSV360 colorPicker;
    public final ConstraintLayout constraintLayout2;
    public final Guideline guideline2;
    public final CustomImageView ivClearBgcolor;
    public final CustomImageView ivConfirmBgcolor;
    public final CustomImageView ivGradient01;
    public final CustomImageView ivGradient02;
    public final CustomImageView ivGradient03;
    public final CustomImageView ivGradient04;
    public final RelativeLayout rlGradient01;
    public final RelativeLayout rlGradient02;
    public final RelativeLayout rlGradient03;
    public final RelativeLayout rlGradient04;
    private final ConstraintLayout rootView;

    private DialogColorSelectBinding(ConstraintLayout rootView, ConstraintLayout clTextGradient, RoundColorPaletteHSV360 colorPicker, ConstraintLayout constraintLayout2, Guideline guideline2, CustomImageView ivClearBgcolor, CustomImageView ivConfirmBgcolor, CustomImageView ivGradient01, CustomImageView ivGradient02, CustomImageView ivGradient03, CustomImageView ivGradient04, RelativeLayout rlGradient01, RelativeLayout rlGradient02, RelativeLayout rlGradient03, RelativeLayout rlGradient04) {
        this.rootView = rootView;
        this.clTextGradient = clTextGradient;
        this.colorPicker = colorPicker;
        this.constraintLayout2 = constraintLayout2;
        this.guideline2 = guideline2;
        this.ivClearBgcolor = ivClearBgcolor;
        this.ivConfirmBgcolor = ivConfirmBgcolor;
        this.ivGradient01 = ivGradient01;
        this.ivGradient02 = ivGradient02;
        this.ivGradient03 = ivGradient03;
        this.ivGradient04 = ivGradient04;
        this.rlGradient01 = rlGradient01;
        this.rlGradient02 = rlGradient02;
        this.rlGradient03 = rlGradient03;
        this.rlGradient04 = rlGradient04;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogColorSelectBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogColorSelectBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.dialog_color_select, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogColorSelectBinding bind(View rootView) {
        int i = R.id.cl_text_gradient;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_text_gradient);
        if (constraintLayout != null) {
            i = R.id.color_picker;
            RoundColorPaletteHSV360 roundColorPaletteHSV360 = (RoundColorPaletteHSV360) ViewBindings.findChildViewById(rootView, R.id.color_picker);
            if (roundColorPaletteHSV360 != null) {
                i = R.id.constraintLayout2;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.constraintLayout2);
                if (constraintLayout2 != null) {
                    i = R.id.guideline2;
                    Guideline guideline = (Guideline) ViewBindings.findChildViewById(rootView, R.id.guideline2);
                    if (guideline != null) {
                        i = R.id.iv_clear_bgcolor;
                        CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_clear_bgcolor);
                        if (customImageView != null) {
                            i = R.id.iv_confirm_bgcolor;
                            CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_confirm_bgcolor);
                            if (customImageView2 != null) {
                                i = R.id.iv_gradient_01;
                                CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_gradient_01);
                                if (customImageView3 != null) {
                                    i = R.id.iv_gradient_02;
                                    CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_gradient_02);
                                    if (customImageView4 != null) {
                                        i = R.id.iv_gradient_03;
                                        CustomImageView customImageView5 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_gradient_03);
                                        if (customImageView5 != null) {
                                            i = R.id.iv_gradient_04;
                                            CustomImageView customImageView6 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_gradient_04);
                                            if (customImageView6 != null) {
                                                i = R.id.rl_gradient_01;
                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_gradient_01);
                                                if (relativeLayout != null) {
                                                    i = R.id.rl_gradient_02;
                                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_gradient_02);
                                                    if (relativeLayout2 != null) {
                                                        i = R.id.rl_gradient_03;
                                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_gradient_03);
                                                        if (relativeLayout3 != null) {
                                                            i = R.id.rl_gradient_04;
                                                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_gradient_04);
                                                            if (relativeLayout4 != null) {
                                                                return new DialogColorSelectBinding((ConstraintLayout) rootView, constraintLayout, roundColorPaletteHSV360, constraintLayout2, guideline, customImageView, customImageView2, customImageView3, customImageView4, customImageView5, customImageView6, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4);
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
