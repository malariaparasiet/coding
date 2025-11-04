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
import com.wifiled.ipixels.view.RhythmLedView;
import com.wifiled.ipixels.view.ViewPagerAllResponse;

/* loaded from: classes3.dex */
public final class ActivityMicTestBinding implements ViewBinding {
    public final Guideline guideline3;
    public final ConstraintLayout ilMicToolbar;
    public final ImageView imageView2;
    public final ImageView ivMicRhyImageBg;
    public final RhythmLedView rhyledviewMic1;
    public final RhythmLedView rhyledviewMic2;
    public final RelativeLayout rlMicRhyBg;
    public final RelativeLayout rlMicRoot;
    private final ConstraintLayout rootView;
    public final ViewPagerAllResponse vpMicRhyhm;

    private ActivityMicTestBinding(ConstraintLayout rootView, Guideline guideline3, ConstraintLayout ilMicToolbar, ImageView imageView2, ImageView ivMicRhyImageBg, RhythmLedView rhyledviewMic1, RhythmLedView rhyledviewMic2, RelativeLayout rlMicRhyBg, RelativeLayout rlMicRoot, ViewPagerAllResponse vpMicRhyhm) {
        this.rootView = rootView;
        this.guideline3 = guideline3;
        this.ilMicToolbar = ilMicToolbar;
        this.imageView2 = imageView2;
        this.ivMicRhyImageBg = ivMicRhyImageBg;
        this.rhyledviewMic1 = rhyledviewMic1;
        this.rhyledviewMic2 = rhyledviewMic2;
        this.rlMicRhyBg = rlMicRhyBg;
        this.rlMicRoot = rlMicRoot;
        this.vpMicRhyhm = vpMicRhyhm;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMicTestBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMicTestBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_mic_test, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityMicTestBinding bind(View rootView) {
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
                        RhythmLedView rhythmLedView = (RhythmLedView) ViewBindings.findChildViewById(rootView, R.id.rhyledview_mic_1);
                        if (rhythmLedView != null) {
                            i = R.id.rhyledview_mic_2;
                            RhythmLedView rhythmLedView2 = (RhythmLedView) ViewBindings.findChildViewById(rootView, R.id.rhyledview_mic_2);
                            if (rhythmLedView2 != null) {
                                i = R.id.rl_mic_rhy_bg;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_mic_rhy_bg);
                                if (relativeLayout != null) {
                                    i = R.id.rl_mic_root;
                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_mic_root);
                                    if (relativeLayout2 != null) {
                                        i = R.id.vp_mic_rhyhm;
                                        ViewPagerAllResponse viewPagerAllResponse = (ViewPagerAllResponse) ViewBindings.findChildViewById(rootView, R.id.vp_mic_rhyhm);
                                        if (viewPagerAllResponse != null) {
                                            return new ActivityMicTestBinding((ConstraintLayout) rootView, guideline, constraintLayout, imageView, imageView2, rhythmLedView, rhythmLedView2, relativeLayout, relativeLayout2, viewPagerAllResponse);
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
