package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class DialogTextBorderBinding implements ViewBinding {
    public final RecyclerView borderEffectList;
    public final RecyclerView borderList;
    public final AppCompatImageView ivCancel;
    public final AppCompatImageView ivSubmit;
    public final RelativeLayout rlSpeedSeek;
    private final ConstraintLayout rootView;
    public final SeekBar sbSpeed;
    public final TextView tvEffect;
    public final TextView tvSpeed;

    private DialogTextBorderBinding(ConstraintLayout rootView, RecyclerView borderEffectList, RecyclerView borderList, AppCompatImageView ivCancel, AppCompatImageView ivSubmit, RelativeLayout rlSpeedSeek, SeekBar sbSpeed, TextView tvEffect, TextView tvSpeed) {
        this.rootView = rootView;
        this.borderEffectList = borderEffectList;
        this.borderList = borderList;
        this.ivCancel = ivCancel;
        this.ivSubmit = ivSubmit;
        this.rlSpeedSeek = rlSpeedSeek;
        this.sbSpeed = sbSpeed;
        this.tvEffect = tvEffect;
        this.tvSpeed = tvSpeed;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogTextBorderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogTextBorderBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.dialog_text_border, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogTextBorderBinding bind(View rootView) {
        int i = R.id.border_effect_list;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.border_effect_list);
        if (recyclerView != null) {
            i = R.id.border_list;
            RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.border_list);
            if (recyclerView2 != null) {
                i = R.id.iv_cancel;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.iv_cancel);
                if (appCompatImageView != null) {
                    i = R.id.iv_submit;
                    AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.iv_submit);
                    if (appCompatImageView2 != null) {
                        i = R.id.rl_speed_seek;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_speed_seek);
                        if (relativeLayout != null) {
                            i = R.id.sb_speed;
                            SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, R.id.sb_speed);
                            if (seekBar != null) {
                                i = R.id.tv_effect;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_effect);
                                if (textView != null) {
                                    i = R.id.tv_speed;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_speed);
                                    if (textView2 != null) {
                                        return new DialogTextBorderBinding((ConstraintLayout) rootView, recyclerView, recyclerView2, appCompatImageView, appCompatImageView2, relativeLayout, seekBar, textView, textView2);
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
