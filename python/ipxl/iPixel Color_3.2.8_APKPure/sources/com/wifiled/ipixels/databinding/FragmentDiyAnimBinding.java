package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.VerticalSeekBar;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class FragmentDiyAnimBinding implements ViewBinding {
    public final ImageView btnSelect;
    public final ConstraintLayout clEditBg;
    public final FrameLayout flPreview;
    public final Guideline glBottom;
    public final Guideline glEdit;
    public final Guideline guideline4;
    public final Guideline guideline5;
    public final ConstraintLayout ilDiyAnimToolbar;
    public final ToolbarLayoutBinding incInput;
    public final ImageView ivDiyAnimClear;
    public final CustomImageView ivDiyAnimCopy;
    public final CustomImageView ivDiyAnimDelete;
    public final CustomImageView ivDiyAnimEdit;
    public final CustomImageView ivDiyAnimEditOk;
    public final ImageView ivDiyAnimPlay;
    public final ImageView ivGitPreview;
    public final RecyclerView rlDiyAnimAddItem;
    public final RelativeLayout rlDiyLightSeek;
    public final RelativeLayout rlDiySpeedSeek;
    private final ConstraintLayout rootView;
    public final VerticalSeekBar sbAnimAlpha;
    public final VerticalSeekBar sbAnimSpeed;
    public final TextView tvEdit;

    private FragmentDiyAnimBinding(ConstraintLayout rootView, ImageView btnSelect, ConstraintLayout clEditBg, FrameLayout flPreview, Guideline glBottom, Guideline glEdit, Guideline guideline4, Guideline guideline5, ConstraintLayout ilDiyAnimToolbar, ToolbarLayoutBinding incInput, ImageView ivDiyAnimClear, CustomImageView ivDiyAnimCopy, CustomImageView ivDiyAnimDelete, CustomImageView ivDiyAnimEdit, CustomImageView ivDiyAnimEditOk, ImageView ivDiyAnimPlay, ImageView ivGitPreview, RecyclerView rlDiyAnimAddItem, RelativeLayout rlDiyLightSeek, RelativeLayout rlDiySpeedSeek, VerticalSeekBar sbAnimAlpha, VerticalSeekBar sbAnimSpeed, TextView tvEdit) {
        this.rootView = rootView;
        this.btnSelect = btnSelect;
        this.clEditBg = clEditBg;
        this.flPreview = flPreview;
        this.glBottom = glBottom;
        this.glEdit = glEdit;
        this.guideline4 = guideline4;
        this.guideline5 = guideline5;
        this.ilDiyAnimToolbar = ilDiyAnimToolbar;
        this.incInput = incInput;
        this.ivDiyAnimClear = ivDiyAnimClear;
        this.ivDiyAnimCopy = ivDiyAnimCopy;
        this.ivDiyAnimDelete = ivDiyAnimDelete;
        this.ivDiyAnimEdit = ivDiyAnimEdit;
        this.ivDiyAnimEditOk = ivDiyAnimEditOk;
        this.ivDiyAnimPlay = ivDiyAnimPlay;
        this.ivGitPreview = ivGitPreview;
        this.rlDiyAnimAddItem = rlDiyAnimAddItem;
        this.rlDiyLightSeek = rlDiyLightSeek;
        this.rlDiySpeedSeek = rlDiySpeedSeek;
        this.sbAnimAlpha = sbAnimAlpha;
        this.sbAnimSpeed = sbAnimSpeed;
        this.tvEdit = tvEdit;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentDiyAnimBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentDiyAnimBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_diy_anim, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentDiyAnimBinding bind(View rootView) {
        int i = R.id.btn_select;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_select);
        if (imageView != null) {
            i = R.id.cl_edit_bg;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_edit_bg);
            if (constraintLayout != null) {
                i = R.id.fl_preview;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.fl_preview);
                if (frameLayout != null) {
                    i = R.id.gl_bottom;
                    Guideline guideline = (Guideline) ViewBindings.findChildViewById(rootView, R.id.gl_bottom);
                    if (guideline != null) {
                        i = R.id.gl_edit;
                        Guideline guideline2 = (Guideline) ViewBindings.findChildViewById(rootView, R.id.gl_edit);
                        if (guideline2 != null) {
                            i = R.id.guideline4;
                            Guideline guideline3 = (Guideline) ViewBindings.findChildViewById(rootView, R.id.guideline4);
                            if (guideline3 != null) {
                                i = R.id.guideline5;
                                Guideline guideline4 = (Guideline) ViewBindings.findChildViewById(rootView, R.id.guideline5);
                                if (guideline4 != null) {
                                    i = R.id.il_diy_anim_toolbar;
                                    ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_diy_anim_toolbar);
                                    if (constraintLayout2 != null) {
                                        i = R.id.inc_input;
                                        View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.inc_input);
                                        if (findChildViewById != null) {
                                            ToolbarLayoutBinding bind = ToolbarLayoutBinding.bind(findChildViewById);
                                            i = R.id.iv_diy_anim_clear;
                                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_diy_anim_clear);
                                            if (imageView2 != null) {
                                                i = R.id.iv_diy_anim_copy;
                                                CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_diy_anim_copy);
                                                if (customImageView != null) {
                                                    i = R.id.iv_diy_anim_delete;
                                                    CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_diy_anim_delete);
                                                    if (customImageView2 != null) {
                                                        i = R.id.iv_diy_anim_edit;
                                                        CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_diy_anim_edit);
                                                        if (customImageView3 != null) {
                                                            i = R.id.iv_diy_anim_edit_ok;
                                                            CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_diy_anim_edit_ok);
                                                            if (customImageView4 != null) {
                                                                i = R.id.iv_diy_anim_play;
                                                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_diy_anim_play);
                                                                if (imageView3 != null) {
                                                                    i = R.id.iv_git_preview;
                                                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_git_preview);
                                                                    if (imageView4 != null) {
                                                                        i = R.id.rl_diy_anim_add_item;
                                                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rl_diy_anim_add_item);
                                                                        if (recyclerView != null) {
                                                                            i = R.id.rl_diy_light_seek;
                                                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_diy_light_seek);
                                                                            if (relativeLayout != null) {
                                                                                i = R.id.rl_diy_speed_seek;
                                                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_diy_speed_seek);
                                                                                if (relativeLayout2 != null) {
                                                                                    i = R.id.sb_anim_alpha;
                                                                                    VerticalSeekBar verticalSeekBar = (VerticalSeekBar) ViewBindings.findChildViewById(rootView, R.id.sb_anim_alpha);
                                                                                    if (verticalSeekBar != null) {
                                                                                        i = R.id.sb_anim_speed;
                                                                                        VerticalSeekBar verticalSeekBar2 = (VerticalSeekBar) ViewBindings.findChildViewById(rootView, R.id.sb_anim_speed);
                                                                                        if (verticalSeekBar2 != null) {
                                                                                            i = R.id.tv_edit;
                                                                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_edit);
                                                                                            if (textView != null) {
                                                                                                return new FragmentDiyAnimBinding((ConstraintLayout) rootView, imageView, constraintLayout, frameLayout, guideline, guideline2, guideline3, guideline4, constraintLayout2, bind, imageView2, customImageView, customImageView2, customImageView3, customImageView4, imageView3, imageView4, recyclerView, relativeLayout, relativeLayout2, verticalSeekBar, verticalSeekBar2, textView);
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
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
