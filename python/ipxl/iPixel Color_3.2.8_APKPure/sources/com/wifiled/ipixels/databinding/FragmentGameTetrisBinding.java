package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.admin.balatetris.TetrisView;
import com.wifiled.gameview.utils.GridLineView;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class FragmentGameTetrisBinding implements ViewBinding {
    public final ConstraintLayout clPortaitOrientationCtrl;
    public final ConstraintLayout clPortaitTetris;
    public final ConstraintLayout clScreenFrame;
    public final ConstraintLayout constraintLayout4;
    public final Guideline glHorCtrlBtn;
    public final Guideline glInsideHorCtrlBtn;
    public final Guideline glInsideVerCtrlBtn;
    public final Guideline glTetrisHor;
    public final Guideline glVerCtrlBtn;
    public final CustomImageView ivTetrisDown;
    public final CustomImageView ivTetrisLeft;
    public final CustomImageView ivTetrisRight;
    public final CustomImageView ivTetrisRotate;
    public final CustomImageView ivTetrisStart;
    public final CustomImageView ivTetrisUp;
    private final ConstraintLayout rootView;
    public final TextView tvTetrisLevel;
    public final TextView tvTetrisScore;
    public final TetrisView viewTetris;
    public final GridLineView viewTetrisGridline;

    private FragmentGameTetrisBinding(ConstraintLayout rootView, ConstraintLayout clPortaitOrientationCtrl, ConstraintLayout clPortaitTetris, ConstraintLayout clScreenFrame, ConstraintLayout constraintLayout4, Guideline glHorCtrlBtn, Guideline glInsideHorCtrlBtn, Guideline glInsideVerCtrlBtn, Guideline glTetrisHor, Guideline glVerCtrlBtn, CustomImageView ivTetrisDown, CustomImageView ivTetrisLeft, CustomImageView ivTetrisRight, CustomImageView ivTetrisRotate, CustomImageView ivTetrisStart, CustomImageView ivTetrisUp, TextView tvTetrisLevel, TextView tvTetrisScore, TetrisView viewTetris, GridLineView viewTetrisGridline) {
        this.rootView = rootView;
        this.clPortaitOrientationCtrl = clPortaitOrientationCtrl;
        this.clPortaitTetris = clPortaitTetris;
        this.clScreenFrame = clScreenFrame;
        this.constraintLayout4 = constraintLayout4;
        this.glHorCtrlBtn = glHorCtrlBtn;
        this.glInsideHorCtrlBtn = glInsideHorCtrlBtn;
        this.glInsideVerCtrlBtn = glInsideVerCtrlBtn;
        this.glTetrisHor = glTetrisHor;
        this.glVerCtrlBtn = glVerCtrlBtn;
        this.ivTetrisDown = ivTetrisDown;
        this.ivTetrisLeft = ivTetrisLeft;
        this.ivTetrisRight = ivTetrisRight;
        this.ivTetrisRotate = ivTetrisRotate;
        this.ivTetrisStart = ivTetrisStart;
        this.ivTetrisUp = ivTetrisUp;
        this.tvTetrisLevel = tvTetrisLevel;
        this.tvTetrisScore = tvTetrisScore;
        this.viewTetris = viewTetris;
        this.viewTetrisGridline = viewTetrisGridline;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentGameTetrisBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentGameTetrisBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_game_tetris, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentGameTetrisBinding bind(View rootView) {
        int i = R.id.cl_portait_orientation_ctrl;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_portait_orientation_ctrl);
        if (constraintLayout != null) {
            ConstraintLayout constraintLayout2 = (ConstraintLayout) rootView;
            i = R.id.cl_screen_frame;
            ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_screen_frame);
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
                                i = R.id.gl_tetris_hor;
                                Guideline guideline4 = (Guideline) ViewBindings.findChildViewById(rootView, R.id.gl_tetris_hor);
                                if (guideline4 != null) {
                                    i = R.id.gl_ver_ctrl_btn;
                                    Guideline guideline5 = (Guideline) ViewBindings.findChildViewById(rootView, R.id.gl_ver_ctrl_btn);
                                    if (guideline5 != null) {
                                        i = R.id.iv_tetris_down;
                                        CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_tetris_down);
                                        if (customImageView != null) {
                                            i = R.id.iv_tetris_left;
                                            CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_tetris_left);
                                            if (customImageView2 != null) {
                                                i = R.id.iv_tetris_right;
                                                CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_tetris_right);
                                                if (customImageView3 != null) {
                                                    i = R.id.iv_tetris_rotate;
                                                    CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_tetris_rotate);
                                                    if (customImageView4 != null) {
                                                        i = R.id.iv_tetris_start;
                                                        CustomImageView customImageView5 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_tetris_start);
                                                        if (customImageView5 != null) {
                                                            i = R.id.iv_tetris_up;
                                                            CustomImageView customImageView6 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_tetris_up);
                                                            if (customImageView6 != null) {
                                                                i = R.id.tv_tetris_level;
                                                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_tetris_level);
                                                                if (textView != null) {
                                                                    i = R.id.tv_tetris_score;
                                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_tetris_score);
                                                                    if (textView2 != null) {
                                                                        i = R.id.view_tetris;
                                                                        TetrisView tetrisView = (TetrisView) ViewBindings.findChildViewById(rootView, R.id.view_tetris);
                                                                        if (tetrisView != null) {
                                                                            i = R.id.view_tetris_gridline;
                                                                            GridLineView gridLineView = (GridLineView) ViewBindings.findChildViewById(rootView, R.id.view_tetris_gridline);
                                                                            if (gridLineView != null) {
                                                                                return new FragmentGameTetrisBinding(constraintLayout2, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, guideline, guideline2, guideline3, guideline4, guideline5, customImageView, customImageView2, customImageView3, customImageView4, customImageView5, customImageView6, textView, textView2, tetrisView, gridLineView);
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
