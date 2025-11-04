package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class ActivityChooseBinding implements ViewBinding {
    public final ConstraintLayout activityMainLayout;
    public final CustomImageView ivAdd;
    public final ImageView ivControlLedOnoff;
    public final RecyclerView recyclerview;
    public final RelativeLayout rlCtrlLedOnoff;
    public final RelativeLayout rlNav;
    private final ConstraintLayout rootView;

    private ActivityChooseBinding(ConstraintLayout rootView, ConstraintLayout activityMainLayout, CustomImageView ivAdd, ImageView ivControlLedOnoff, RecyclerView recyclerview, RelativeLayout rlCtrlLedOnoff, RelativeLayout rlNav) {
        this.rootView = rootView;
        this.activityMainLayout = activityMainLayout;
        this.ivAdd = ivAdd;
        this.ivControlLedOnoff = ivControlLedOnoff;
        this.recyclerview = recyclerview;
        this.rlCtrlLedOnoff = rlCtrlLedOnoff;
        this.rlNav = rlNav;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityChooseBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityChooseBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_choose, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityChooseBinding bind(View rootView) {
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
        int i = R.id.iv_add;
        CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_add);
        if (customImageView != null) {
            i = R.id.iv_control_led_onoff;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_control_led_onoff);
            if (imageView != null) {
                i = R.id.recyclerview;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerview);
                if (recyclerView != null) {
                    i = R.id.rl_ctrl_led_onoff;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_ctrl_led_onoff);
                    if (relativeLayout != null) {
                        i = R.id.rl_nav;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_nav);
                        if (relativeLayout2 != null) {
                            return new ActivityChooseBinding(constraintLayout, constraintLayout, customImageView, imageView, recyclerView, relativeLayout, relativeLayout2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
