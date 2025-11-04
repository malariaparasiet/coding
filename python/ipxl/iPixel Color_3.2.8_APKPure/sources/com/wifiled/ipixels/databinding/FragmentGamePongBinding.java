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
import com.wifiled.gameview.pingpong.BallView;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class FragmentGamePongBinding implements ViewBinding {
    public final ConstraintLayout clPortaitOrientationCtrl;
    public final ConstraintLayout clPortaitPong;
    public final ConstraintLayout clScreenFrame;
    public final ConstraintLayout constraintLayout4;
    public final Guideline glHorCtrlBtn;
    public final Guideline glInsideHorCtrlBtn;
    public final Guideline glInsideVerCtrlBtn;
    public final Guideline glPongHor;
    public final Guideline glVerCtrlBtn;
    public final ImageView imageView5;
    public final CustomImageView ivPongDeform;
    public final CustomImageView ivPongDown;
    public final CustomImageView ivPongLeft;
    public final CustomImageView ivPongRight;
    public final CustomImageView ivPongStart;
    public final CustomImageView ivPongUp;
    private final ConstraintLayout rootView;
    public final TextView textView;
    public final TextView tvPingScore;
    public final BallView viewPong;

    private FragmentGamePongBinding(ConstraintLayout rootView, ConstraintLayout clPortaitOrientationCtrl, ConstraintLayout clPortaitPong, ConstraintLayout clScreenFrame, ConstraintLayout constraintLayout4, Guideline glHorCtrlBtn, Guideline glInsideHorCtrlBtn, Guideline glInsideVerCtrlBtn, Guideline glPongHor, Guideline glVerCtrlBtn, ImageView imageView5, CustomImageView ivPongDeform, CustomImageView ivPongDown, CustomImageView ivPongLeft, CustomImageView ivPongRight, CustomImageView ivPongStart, CustomImageView ivPongUp, TextView textView, TextView tvPingScore, BallView viewPong) {
        this.rootView = rootView;
        this.clPortaitOrientationCtrl = clPortaitOrientationCtrl;
        this.clPortaitPong = clPortaitPong;
        this.clScreenFrame = clScreenFrame;
        this.constraintLayout4 = constraintLayout4;
        this.glHorCtrlBtn = glHorCtrlBtn;
        this.glInsideHorCtrlBtn = glInsideHorCtrlBtn;
        this.glInsideVerCtrlBtn = glInsideVerCtrlBtn;
        this.glPongHor = glPongHor;
        this.glVerCtrlBtn = glVerCtrlBtn;
        this.imageView5 = imageView5;
        this.ivPongDeform = ivPongDeform;
        this.ivPongDown = ivPongDown;
        this.ivPongLeft = ivPongLeft;
        this.ivPongRight = ivPongRight;
        this.ivPongStart = ivPongStart;
        this.ivPongUp = ivPongUp;
        this.textView = textView;
        this.tvPingScore = tvPingScore;
        this.viewPong = viewPong;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentGamePongBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentGamePongBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_game_pong, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentGamePongBinding bind(View rootView) {
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
                                i = R.id.gl_pong_hor;
                                Guideline guideline4 = (Guideline) ViewBindings.findChildViewById(rootView, R.id.gl_pong_hor);
                                if (guideline4 != null) {
                                    i = R.id.gl_ver_ctrl_btn;
                                    Guideline guideline5 = (Guideline) ViewBindings.findChildViewById(rootView, R.id.gl_ver_ctrl_btn);
                                    if (guideline5 != null) {
                                        i = R.id.imageView5;
                                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imageView5);
                                        if (imageView != null) {
                                            i = R.id.iv_pong_deform;
                                            CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_pong_deform);
                                            if (customImageView != null) {
                                                i = R.id.iv_pong_down;
                                                CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_pong_down);
                                                if (customImageView2 != null) {
                                                    i = R.id.iv_pong_left;
                                                    CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_pong_left);
                                                    if (customImageView3 != null) {
                                                        i = R.id.iv_pong_right;
                                                        CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_pong_right);
                                                        if (customImageView4 != null) {
                                                            i = R.id.iv_pong_start;
                                                            CustomImageView customImageView5 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_pong_start);
                                                            if (customImageView5 != null) {
                                                                i = R.id.iv_pong_up;
                                                                CustomImageView customImageView6 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_pong_up);
                                                                if (customImageView6 != null) {
                                                                    i = R.id.textView;
                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.textView);
                                                                    if (textView != null) {
                                                                        i = R.id.tv_ping_score;
                                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_ping_score);
                                                                        if (textView2 != null) {
                                                                            i = R.id.view_pong;
                                                                            BallView ballView = (BallView) ViewBindings.findChildViewById(rootView, R.id.view_pong);
                                                                            if (ballView != null) {
                                                                                return new FragmentGamePongBinding(constraintLayout2, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, guideline, guideline2, guideline3, guideline4, guideline5, imageView, customImageView, customImageView2, customImageView3, customImageView4, customImageView5, customImageView6, textView, textView2, ballView);
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
