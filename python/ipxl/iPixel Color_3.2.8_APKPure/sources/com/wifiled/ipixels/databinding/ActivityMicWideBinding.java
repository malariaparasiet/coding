package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.RhythmLedView2;
import com.wifiled.ipixels.view.ViewPagerAllResponse;

/* loaded from: classes3.dex */
public final class ActivityMicWideBinding implements ViewBinding {
    public final Guideline guideline3;
    public final ConstraintLayout ilMicToolbar;
    public final ImageView imageView2;
    public final ImageView ivMicRhyImageBg;
    public final RhythmLedView2 rhyledviewMic1;
    public final RhythmLedView2 rhyledviewMic2;
    public final RhythmLedView2 rhyledviewMic3;
    public final RelativeLayout rlMicRhyBg;
    public final RelativeLayout rlMicRoot;
    private final ConstraintLayout rootView;
    public final ViewPagerAllResponse vpMicRhyhm;

    private ActivityMicWideBinding(ConstraintLayout rootView, Guideline guideline3, ConstraintLayout ilMicToolbar, ImageView imageView2, ImageView ivMicRhyImageBg, RhythmLedView2 rhyledviewMic1, RhythmLedView2 rhyledviewMic2, RhythmLedView2 rhyledviewMic3, RelativeLayout rlMicRhyBg, RelativeLayout rlMicRoot, ViewPagerAllResponse vpMicRhyhm) {
        this.rootView = rootView;
        this.guideline3 = guideline3;
        this.ilMicToolbar = ilMicToolbar;
        this.imageView2 = imageView2;
        this.ivMicRhyImageBg = ivMicRhyImageBg;
        this.rhyledviewMic1 = rhyledviewMic1;
        this.rhyledviewMic2 = rhyledviewMic2;
        this.rhyledviewMic3 = rhyledviewMic3;
        this.rlMicRhyBg = rlMicRhyBg;
        this.rlMicRoot = rlMicRoot;
        this.vpMicRhyhm = vpMicRhyhm;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMicWideBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMicWideBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_mic_wide, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityMicWideBinding bind(View rootView) {
        int i = R.id.guideline3;
        Guideline guideline = (Guideline) ViewBindings.findChildViewById(rootView, R.id.guideline3);
        if (guideline != null) {
            i = R.id.il_mic_toolbar;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_mic_toolbar);
            if (constraintLayout != null) {
                i = R.id.imageView2;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imageView2);
                if (imageView != null) {
                    i = R.id.iv_mic_rhy_image_bg;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_mic_rhy_image_bg);
                    if (imageView2 != null) {
                        i = R.id.rhyledview_mic_1;
                        RhythmLedView2 rhythmLedView2 = (RhythmLedView2) ViewBindings.findChildViewById(rootView, R.id.rhyledview_mic_1);
                        if (rhythmLedView2 != null) {
                            i = R.id.rhyledview_mic_2;
                            RhythmLedView2 rhythmLedView22 = (RhythmLedView2) ViewBindings.findChildViewById(rootView, R.id.rhyledview_mic_2);
                            if (rhythmLedView22 != null) {
                                i = R.id.rhyledview_mic_3;
                                RhythmLedView2 rhythmLedView23 = (RhythmLedView2) ViewBindings.findChildViewById(rootView, R.id.rhyledview_mic_3);
                                if (rhythmLedView23 != null) {
                                    i = R.id.rl_mic_rhy_bg;
                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_mic_rhy_bg);
                                    if (relativeLayout != null) {
                                        i = R.id.rl_mic_root;
                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_mic_root);
                                        if (relativeLayout2 != null) {
                                            i = R.id.vp_mic_rhyhm;
                                            ViewPagerAllResponse viewPagerAllResponse = (ViewPagerAllResponse) ViewBindings.findChildViewById(rootView, R.id.vp_mic_rhyhm);
                                            if (viewPagerAllResponse != null) {
                                                return new ActivityMicWideBinding((ConstraintLayout) rootView, guideline, constraintLayout, imageView, imageView2, rhythmLedView2, rhythmLedView22, rhythmLedView23, relativeLayout, relativeLayout2, viewPagerAllResponse);
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
