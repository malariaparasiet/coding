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
public final class FragmentLandGameTetrisBinding implements ViewBinding {
    public final ConstraintLayout clGameTetrisLand;
    public final ConstraintLayout clLandTetris;
    public final ConstraintLayout clPortaitOrientationCtrl;
    public final ConstraintLayout constraintLayout4;
    public final Guideline glHorCtrlBtn;
    public final Guideline glInsideHorCtrlBtn;
    public final Guideline glInsideVerCtrlBtn;
    public final Guideline glVerCtrlBtn;
    public final CustomImageView ivTetrisLandDeform;
    public final CustomImageView ivTetrisLandDown;
    public final CustomImageView ivTetrisLandLeft;
    public final CustomImageView ivTetrisLandRight;
    public final CustomImageView ivTetrisLandStart;
    public final CustomImageView ivTetrisLandUp;
    private final ConstraintLayout rootView;

    private FragmentLandGameTetrisBinding(ConstraintLayout rootView, ConstraintLayout clGameTetrisLand, ConstraintLayout clLandTetris, ConstraintLayout clPortaitOrientationCtrl, ConstraintLayout constraintLayout4, Guideline glHorCtrlBtn, Guideline glInsideHorCtrlBtn, Guideline glInsideVerCtrlBtn, Guideline glVerCtrlBtn, CustomImageView ivTetrisLandDeform, CustomImageView ivTetrisLandDown, CustomImageView ivTetrisLandLeft, CustomImageView ivTetrisLandRight, CustomImageView ivTetrisLandStart, CustomImageView ivTetrisLandUp) {
        this.rootView = rootView;
        this.clGameTetrisLand = clGameTetrisLand;
        this.clLandTetris = clLandTetris;
        this.clPortaitOrientationCtrl = clPortaitOrientationCtrl;
        this.constraintLayout4 = constraintLayout4;
        this.glHorCtrlBtn = glHorCtrlBtn;
        this.glInsideHorCtrlBtn = glInsideHorCtrlBtn;
        this.glInsideVerCtrlBtn = glInsideVerCtrlBtn;
        this.glVerCtrlBtn = glVerCtrlBtn;
        this.ivTetrisLandDeform = ivTetrisLandDeform;
        this.ivTetrisLandDown = ivTetrisLandDown;
        this.ivTetrisLandLeft = ivTetrisLandLeft;
        this.ivTetrisLandRight = ivTetrisLandRight;
        this.ivTetrisLandStart = ivTetrisLandStart;
        this.ivTetrisLandUp = ivTetrisLandUp;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentLandGameTetrisBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentLandGameTetrisBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_land_game_tetris, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentLandGameTetrisBinding bind(View rootView) {
        int i = R.id.cl_game_tetris_land;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_game_tetris_land);
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
                                    i = R.id.iv_tetris_land_deform;
                                    CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_tetris_land_deform);
                                    if (customImageView != null) {
                                        i = R.id.iv_tetris_land_down;
                                        CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_tetris_land_down);
                                        if (customImageView2 != null) {
                                            i = R.id.iv_tetris_land_left;
                                            CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_tetris_land_left);
                                            if (customImageView3 != null) {
                                                i = R.id.iv_tetris_land_right;
                                                CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_tetris_land_right);
                                                if (customImageView4 != null) {
                                                    i = R.id.iv_tetris_land_start;
                                                    CustomImageView customImageView5 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_tetris_land_start);
                                                    if (customImageView5 != null) {
                                                        i = R.id.iv_tetris_land_up;
                                                        CustomImageView customImageView6 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_tetris_land_up);
                                                        if (customImageView6 != null) {
                                                            return new FragmentLandGameTetrisBinding(constraintLayout2, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, guideline, guideline2, guideline3, guideline4, customImageView, customImageView2, customImageView3, customImageView4, customImageView5, customImageView6);
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
