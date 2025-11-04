package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class FragmentLandGameSnakeBinding implements ViewBinding {
    public final ConstraintLayout clGameSnakeLand;
    public final ConstraintLayout clLandSnake;
    public final ConstraintLayout clPortaitOrientationCtrl;
    public final ConstraintLayout constraintLayout4;
    public final Guideline glHorCtrlBtn;
    public final Guideline glInsideHorCtrlBtn;
    public final Guideline glInsideVerCtrlBtn;
    public final Guideline glVerCtrlBtn;
    public final CustomImageView ivLandDeform;
    public final CustomImageView ivLandDown;
    public final CustomImageView ivLandLeft;
    public final CustomImageView ivLandRight;
    public final CustomImageView ivLandStart;
    public final CustomImageView ivLandUp;
    private final ConstraintLayout rootView;

    private FragmentLandGameSnakeBinding(ConstraintLayout rootView, ConstraintLayout clGameSnakeLand, ConstraintLayout clLandSnake, ConstraintLayout clPortaitOrientationCtrl, ConstraintLayout constraintLayout4, Guideline glHorCtrlBtn, Guideline glInsideHorCtrlBtn, Guideline glInsideVerCtrlBtn, Guideline glVerCtrlBtn, CustomImageView ivLandDeform, CustomImageView ivLandDown, CustomImageView ivLandLeft, CustomImageView ivLandRight, CustomImageView ivLandStart, CustomImageView ivLandUp) {
        this.rootView = rootView;
        this.clGameSnakeLand = clGameSnakeLand;
        this.clLandSnake = clLandSnake;
        this.clPortaitOrientationCtrl = clPortaitOrientationCtrl;
        this.constraintLayout4 = constraintLayout4;
        this.glHorCtrlBtn = glHorCtrlBtn;
        this.glInsideHorCtrlBtn = glInsideHorCtrlBtn;
        this.glInsideVerCtrlBtn = glInsideVerCtrlBtn;
        this.glVerCtrlBtn = glVerCtrlBtn;
        this.ivLandDeform = ivLandDeform;
        this.ivLandDown = ivLandDown;
        this.ivLandLeft = ivLandLeft;
        this.ivLandRight = ivLandRight;
        this.ivLandStart = ivLandStart;
        this.ivLandUp = ivLandUp;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentLandGameSnakeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentLandGameSnakeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_land_game_snake, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentLandGameSnakeBinding bind(View rootView) {
        int i = R.id.cl_game_snake_land;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_game_snake_land);
        if (constraintLayout != null) {
            ConstraintLayout constraintLayout2 = (ConstraintLayout) rootView;
            i = R.id.cl_portait_orientation_ctrl;
            ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_portait_orientation_ctrl);
            if (constraintLayout3 != null) {
                i = R.id.constraintLayout4;
                ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.constraintLayout4);
                if (constraintLayout4 != null) {
                    i = R.id.gl_hor_ctrl_btn;
                    Guideline guideline = (Guideline) ViewBindings.findChildViewById(rootView, R.id.gl_hor_ctrl_btn);
                    if (guideline != null) {
                        i = R.id.gl_inside_hor_ctrl_btn;
                        Guideline guideline2 = (Guideline) ViewBindings.findChildViewById(rootView, R.id.gl_inside_hor_ctrl_btn);
                        if (guideline2 != null) {
                            i = R.id.gl_inside_ver_ctrl_btn;
                            Guideline guideline3 = (Guideline) ViewBindings.findChildViewById(rootView, R.id.gl_inside_ver_ctrl_btn);
                            if (guideline3 != null) {
                                i = R.id.gl_ver_ctrl_btn;
                                Guideline guideline4 = (Guideline) ViewBindings.findChildViewById(rootView, R.id.gl_ver_ctrl_btn);
                                if (guideline4 != null) {
                                    i = R.id.iv_land_deform;
                                    CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_land_deform);
                                    if (customImageView != null) {
                                        i = R.id.iv_land_down;
                                        CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_land_down);
                                        if (customImageView2 != null) {
                                            i = R.id.iv_land_left;
                                            CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_land_left);
                                            if (customImageView3 != null) {
                                                i = R.id.iv_land_right;
                                                CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_land_right);
                                                if (customImageView4 != null) {
                                                    i = R.id.iv_land_start;
                                                    CustomImageView customImageView5 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_land_start);
                                                    if (customImageView5 != null) {
                                                        i = R.id.iv_land_up;
                                                        CustomImageView customImageView6 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_land_up);
                                                        if (customImageView6 != null) {
                                                            return new FragmentLandGameSnakeBinding(constraintLayout2, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, guideline, guideline2, guideline3, guideline4, customImageView, customImageView2, customImageView3, customImageView4, customImageView5, customImageView6);
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
