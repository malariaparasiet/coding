package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ActivityColockBinding implements ViewBinding {
    public final ImageView dateIv;
    public final TextView dateTv;
    public final ConstraintLayout ilColockTimeSetting;
    public final ConstraintLayout ilColockToobar;
    public final ImageView ivBottom;
    public final ImageView ivColockSend;
    public final RecyclerView rlTimeImage;
    private final ConstraintLayout rootView;
    public final ImageView timeScaleIv;
    public final TextView timeScaleTv;

    private ActivityColockBinding(ConstraintLayout rootView, ImageView dateIv, TextView dateTv, ConstraintLayout ilColockTimeSetting, ConstraintLayout ilColockToobar, ImageView ivBottom, ImageView ivColockSend, RecyclerView rlTimeImage, ImageView timeScaleIv, TextView timeScaleTv) {
        this.rootView = rootView;
        this.dateIv = dateIv;
        this.dateTv = dateTv;
        this.ilColockTimeSetting = ilColockTimeSetting;
        this.ilColockToobar = ilColockToobar;
        this.ivBottom = ivBottom;
        this.ivColockSend = ivColockSend;
        this.rlTimeImage = rlTimeImage;
        this.timeScaleIv = timeScaleIv;
        this.timeScaleTv = timeScaleTv;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityColockBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityColockBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_colock, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityColockBinding bind(View rootView) {
        int i = R.id.date_iv;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.date_iv);
        if (imageView != null) {
            i = R.id.date_tv;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.date_tv);
            if (textView != null) {
                i = R.id.il_colock_time_setting;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_colock_time_setting);
                if (constraintLayout != null) {
                    i = R.id.il_colock_toobar;
                    ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_colock_toobar);
                    if (constraintLayout2 != null) {
                        i = R.id.iv_bottom;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_bottom);
                        if (imageView2 != null) {
                            i = R.id.iv_colock_send;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_colock_send);
                            if (imageView3 != null) {
                                i = R.id.rl_time_image;
                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rl_time_image);
                                if (recyclerView != null) {
                                    i = R.id.time_scale_iv;
                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.time_scale_iv);
                                    if (imageView4 != null) {
                                        i = R.id.time_scale_tv;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.time_scale_tv);
                                        if (textView2 != null) {
                                            return new ActivityColockBinding((ConstraintLayout) rootView, imageView, textView, constraintLayout, constraintLayout2, imageView2, imageView3, recyclerView, imageView4, textView2);
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
