package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.gameview.snake.view.StageView;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class FragmentGameSnakeBinding implements ViewBinding {
    public final ConstraintLayout clPortaitOrientationCtrl;
    public final ConstraintLayout clPortaitSnake;
    public final ConstraintLayout clScreenFrame;
    public final ConstraintLayout constraintLayout4;
    public final Guideline glHorCtrlBtn;
    public final Guideline glInsideHorCtrlBtn;
    public final Guideline glInsideVerCtrlBtn;
    public final Guideline glSnakeHor;
    public final Guideline glVerCtrlBtn;
    public final ImageView imageView3;
    private final ConstraintLayout rootView;
    public final CustomImageView tdDeform;
    public final CustomImageView tdDown;
    public final CustomImageView tdLeft;
    public final CustomImageView tdRight;
    public final CustomImageView tdStart;
    public final CustomImageView tdUp;
    public final TextView tvSnakeLevel;
    public final TextView tvSnakePoint;
    public final StageView viewSnake;

    private FragmentGameSnakeBinding(ConstraintLayout rootView, ConstraintLayout clPortaitOrientationCtrl, ConstraintLayout clPortaitSnake, ConstraintLayout clScreenFrame, ConstraintLayout constraintLayout4, Guideline glHorCtrlBtn, Guideline glInsideHorCtrlBtn, Guideline glInsideVerCtrlBtn, Guideline glSnakeHor, Guideline glVerCtrlBtn, ImageView imageView3, CustomImageView tdDeform, CustomImageView tdDown, CustomImageView tdLeft, CustomImageView tdRight, CustomImageView tdStart, CustomImageView tdUp, TextView tvSnakeLevel, TextView tvSnakePoint, StageView viewSnake) {
        this.rootView = rootView;
        this.clPortaitOrientationCtrl = clPortaitOrientationCtrl;
        this.clPortaitSnake = clPortaitSnake;
        this.clScreenFrame = clScreenFrame;
        this.constraintLayout4 = constraintLayout4;
        this.glHorCtrlBtn = glHorCtrlBtn;
        this.glInsideHorCtrlBtn = glInsideHorCtrlBtn;
        this.glInsideVerCtrlBtn = glInsideVerCtrlBtn;
        this.glSnakeHor = glSnakeHor;
        this.glVerCtrlBtn = glVerCtrlBtn;
        this.imageView3 = imageView3;
        this.tdDeform = tdDeform;
        this.tdDown = tdDown;
        this.tdLeft = tdLeft;
        this.tdRight = tdRight;
        this.tdStart = tdStart;
        this.tdUp = tdUp;
        this.tvSnakeLevel = tvSnakeLevel;
        this.tvSnakePoint = tvSnakePoint;
        this.viewSnake = viewSnake;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentGameSnakeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentGameSnakeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_game_snake, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentGameSnakeBinding bind(View rootView) {
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
                                i = R.id.gl_snake_hor;
                                Guideline guideline4 = (Guideline) ViewBindings.findChildViewById(rootView, R.id.gl_snake_hor);
                                if (guideline4 != null) {
                                    i = R.id.gl_ver_ctrl_btn;
                                    Guideline guideline5 = (Guideline) ViewBindings.findChildViewById(rootView, R.id.gl_ver_ctrl_btn);
                                    if (guideline5 != null) {
                                        i = R.id.imageView3;
                                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imageView3);
                                        if (imageView != null) {
                                            i = R.id.td_deform;
                                            CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.td_deform);
                                            if (customImageView != null) {
                                                i = R.id.td_down;
                                                CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.td_down);
                                                if (customImageView2 != null) {
                                                    i = R.id.td_left;
                                                    CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.td_left);
                                                    if (customImageView3 != null) {
                                                        i = R.id.td_right;
                                                        CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.td_right);
                                                        if (customImageView4 != null) {
                                                            i = R.id.td_start;
                                                            CustomImageView customImageView5 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.td_start);
                                                            if (customImageView5 != null) {
                                                                i = R.id.td_up;
                                                                CustomImageView customImageView6 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.td_up);
                                                                if (customImageView6 != null) {
                                                                    i = R.id.tv_snake_level;
                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_snake_level);
                                                                    if (textView != null) {
                                                                        i = R.id.tv_snake_point;
                                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_snake_point);
                                                                        if (textView2 != null) {
                                                                            i = R.id.view_snake;
                                                                            StageView stageView = (StageView) ViewBindings.findChildViewById(rootView, R.id.view_snake);
                                                                            if (stageView != null) {
                                                                                return new FragmentGameSnakeBinding(constraintLayout2, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, guideline, guideline2, guideline3, guideline4, guideline5, imageView, customImageView, customImageView2, customImageView3, customImageView4, customImageView5, customImageView6, textView, textView2, stageView);
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
