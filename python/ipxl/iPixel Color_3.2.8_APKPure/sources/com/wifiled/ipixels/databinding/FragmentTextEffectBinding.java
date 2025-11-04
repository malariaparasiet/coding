package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class FragmentTextEffectBinding implements ViewBinding {
    public final Guideline guideline8;
    public final ImageView ivBottom;
    public final CustomImageView ivEffectNaviTextinputFun;
    public final RelativeLayout relativeLayout;
    public final RecyclerView rlEffect;
    public final RelativeLayout rlLightSeek;
    public final RelativeLayout rlSpeedSeek;
    private final ConstraintLayout rootView;
    public final SeekBar sbSpeed;
    public final SeekBar sbTextAlpha;

    private FragmentTextEffectBinding(ConstraintLayout rootView, Guideline guideline8, ImageView ivBottom, CustomImageView ivEffectNaviTextinputFun, RelativeLayout relativeLayout, RecyclerView rlEffect, RelativeLayout rlLightSeek, RelativeLayout rlSpeedSeek, SeekBar sbSpeed, SeekBar sbTextAlpha) {
        this.rootView = rootView;
        this.guideline8 = guideline8;
        this.ivBottom = ivBottom;
        this.ivEffectNaviTextinputFun = ivEffectNaviTextinputFun;
        this.relativeLayout = relativeLayout;
        this.rlEffect = rlEffect;
        this.rlLightSeek = rlLightSeek;
        this.rlSpeedSeek = rlSpeedSeek;
        this.sbSpeed = sbSpeed;
        this.sbTextAlpha = sbTextAlpha;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentTextEffectBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentTextEffectBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_text_effect, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentTextEffectBinding bind(View rootView) {
        int i = R.id.guideline8;
        Guideline guideline = (Guideline) ViewBindings.findChildViewById(rootView, R.id.guideline8);
        if (guideline != null) {
            i = R.id.iv_bottom;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_bottom);
            if (imageView != null) {
                i = R.id.iv_effect_navi_textinput_fun;
                CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_effect_navi_textinput_fun);
                if (customImageView != null) {
                    i = R.id.relativeLayout;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.relativeLayout);
                    if (relativeLayout != null) {
                        i = R.id.rl_effect;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rl_effect);
                        if (recyclerView != null) {
                            i = R.id.rl_light_seek;
                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_light_seek);
                            if (relativeLayout2 != null) {
                                i = R.id.rl_speed_seek;
                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_speed_seek);
                                if (relativeLayout3 != null) {
                                    i = R.id.sb_speed;
                                    SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, R.id.sb_speed);
                                    if (seekBar != null) {
                                        i = R.id.sb_text_alpha;
                                        SeekBar seekBar2 = (SeekBar) ViewBindings.findChildViewById(rootView, R.id.sb_text_alpha);
                                        if (seekBar2 != null) {
                                            return new FragmentTextEffectBinding((ConstraintLayout) rootView, guideline, imageView, customImageView, relativeLayout, recyclerView, relativeLayout2, relativeLayout3, seekBar, seekBar2);
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
