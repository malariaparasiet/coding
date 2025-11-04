package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class ActivityRidingBinding implements ViewBinding {
    public final ImageView imageView;
    public final CustomImageView ivAuto;
    public final CustomImageView ivCancel;
    public final CustomImageView ivFlash;
    public final CustomImageView ivGo;
    public final CustomImageView ivLeft;
    public final CustomImageView ivRight;
    public final CustomImageView ivSos;
    public final CustomImageView ivSpeed;
    public final CustomImageView ivStop;
    public final RelativeLayout llBottom;
    private final ConstraintLayout rootView;
    public final Chronometer timer;
    public final TextView tvDistance;
    public final TextView tvSpeed;
    public final TextView tvTitle;

    private ActivityRidingBinding(ConstraintLayout rootView, ImageView imageView, CustomImageView ivAuto, CustomImageView ivCancel, CustomImageView ivFlash, CustomImageView ivGo, CustomImageView ivLeft, CustomImageView ivRight, CustomImageView ivSos, CustomImageView ivSpeed, CustomImageView ivStop, RelativeLayout llBottom, Chronometer timer, TextView tvDistance, TextView tvSpeed, TextView tvTitle) {
        this.rootView = rootView;
        this.imageView = imageView;
        this.ivAuto = ivAuto;
        this.ivCancel = ivCancel;
        this.ivFlash = ivFlash;
        this.ivGo = ivGo;
        this.ivLeft = ivLeft;
        this.ivRight = ivRight;
        this.ivSos = ivSos;
        this.ivSpeed = ivSpeed;
        this.ivStop = ivStop;
        this.llBottom = llBottom;
        this.timer = timer;
        this.tvDistance = tvDistance;
        this.tvSpeed = tvSpeed;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRidingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityRidingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_riding, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityRidingBinding bind(View rootView) {
        int i = R.id.imageView;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imageView);
        if (imageView != null) {
            i = R.id.iv_auto;
            CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_auto);
            if (customImageView != null) {
                i = R.id.iv_cancel;
                CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_cancel);
                if (customImageView2 != null) {
                    i = R.id.iv_flash;
                    CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_flash);
                    if (customImageView3 != null) {
                        i = R.id.iv_go;
                        CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_go);
                        if (customImageView4 != null) {
                            i = R.id.iv_left;
                            CustomImageView customImageView5 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_left);
                            if (customImageView5 != null) {
                                i = R.id.iv_right;
                                CustomImageView customImageView6 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right);
                                if (customImageView6 != null) {
                                    i = R.id.iv_sos;
                                    CustomImageView customImageView7 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_sos);
                                    if (customImageView7 != null) {
                                        i = R.id.iv_speed;
                                        CustomImageView customImageView8 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_speed);
                                        if (customImageView8 != null) {
                                            i = R.id.iv_stop;
                                            CustomImageView customImageView9 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_stop);
                                            if (customImageView9 != null) {
                                                i = R.id.ll_bottom;
                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.ll_bottom);
                                                if (relativeLayout != null) {
                                                    i = R.id.timer;
                                                    Chronometer chronometer = (Chronometer) ViewBindings.findChildViewById(rootView, R.id.timer);
                                                    if (chronometer != null) {
                                                        i = R.id.tv_distance;
                                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_distance);
                                                        if (textView != null) {
                                                            i = R.id.tv_speed;
                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_speed);
                                                            if (textView2 != null) {
                                                                i = R.id.tv_title;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                                                                if (textView3 != null) {
                                                                    return new ActivityRidingBinding((ConstraintLayout) rootView, imageView, customImageView, customImageView2, customImageView3, customImageView4, customImageView5, customImageView6, customImageView7, customImageView8, customImageView9, relativeLayout, chronometer, textView, textView2, textView3);
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
