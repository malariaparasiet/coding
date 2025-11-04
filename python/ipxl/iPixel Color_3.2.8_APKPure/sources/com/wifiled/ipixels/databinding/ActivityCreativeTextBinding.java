package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.baselib.widget.NoNewLineEditText;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.ColorBarView;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class ActivityCreativeTextBinding implements ViewBinding {
    public final ImageView animationBg;
    public final ImageView animationBgIcon;
    public final ImageView borderBg;
    public final ImageView borderIcon;
    public final ConstraintLayout clTextBgSet;
    public final ConstraintLayout clTextBorderSet;
    public final ConstraintLayout clTextPauseSet;
    public final ConstraintLayout clTextSizeSetBc;
    public final ColorBarView colorBarViewText;
    public final NoNewLineEditText etInput;
    public final ConstraintLayout gifBg;
    public final AppCompatImageView gifImg;
    public final ConstraintLayout ilTextToolbar;
    public final View imgBg;
    public final CustomImageView ivBack;
    public final ImageView ivBottom;
    public final ImageView ivIcon;
    public final CustomImageView ivMiddle;
    public final CustomImageView ivRight;
    public final CustomImageView ivRightSubtitle;
    public final ImageView ivTextMode;
    public final ImageView ivTextPause;
    public final ImageView ivTextSend;
    public final ImageView ivTextSizeBc;
    public final ImageView ivTextSizeNextBc;
    public final ConstraintLayout main;
    public final RecyclerView recyclerviewColor;
    public final RecyclerView recyclerviewColorBc;
    public final RecyclerView recyclerviewColorBcGradation;
    public final RelativeLayout rlSpeedSeek;
    private final ConstraintLayout rootView;
    public final RecyclerView rvAnimation;
    public final SeekBar sbSpeed;
    public final ImageView tvBg;
    public final ImageView tvBorder;
    public final TextView tvFontlimitInAttr;
    public final TextView tvRight;
    public final ImageView tvSpeed;
    public final TextView tvTextPaauseTime;
    public final ImageView tvTextPause;
    public final TextView tvTextSize32Bc;
    public final TextView tvTitle;

    private ActivityCreativeTextBinding(ConstraintLayout rootView, ImageView animationBg, ImageView animationBgIcon, ImageView borderBg, ImageView borderIcon, ConstraintLayout clTextBgSet, ConstraintLayout clTextBorderSet, ConstraintLayout clTextPauseSet, ConstraintLayout clTextSizeSetBc, ColorBarView colorBarViewText, NoNewLineEditText etInput, ConstraintLayout gifBg, AppCompatImageView gifImg, ConstraintLayout ilTextToolbar, View imgBg, CustomImageView ivBack, ImageView ivBottom, ImageView ivIcon, CustomImageView ivMiddle, CustomImageView ivRight, CustomImageView ivRightSubtitle, ImageView ivTextMode, ImageView ivTextPause, ImageView ivTextSend, ImageView ivTextSizeBc, ImageView ivTextSizeNextBc, ConstraintLayout main, RecyclerView recyclerviewColor, RecyclerView recyclerviewColorBc, RecyclerView recyclerviewColorBcGradation, RelativeLayout rlSpeedSeek, RecyclerView rvAnimation, SeekBar sbSpeed, ImageView tvBg, ImageView tvBorder, TextView tvFontlimitInAttr, TextView tvRight, ImageView tvSpeed, TextView tvTextPaauseTime, ImageView tvTextPause, TextView tvTextSize32Bc, TextView tvTitle) {
        this.rootView = rootView;
        this.animationBg = animationBg;
        this.animationBgIcon = animationBgIcon;
        this.borderBg = borderBg;
        this.borderIcon = borderIcon;
        this.clTextBgSet = clTextBgSet;
        this.clTextBorderSet = clTextBorderSet;
        this.clTextPauseSet = clTextPauseSet;
        this.clTextSizeSetBc = clTextSizeSetBc;
        this.colorBarViewText = colorBarViewText;
        this.etInput = etInput;
        this.gifBg = gifBg;
        this.gifImg = gifImg;
        this.ilTextToolbar = ilTextToolbar;
        this.imgBg = imgBg;
        this.ivBack = ivBack;
        this.ivBottom = ivBottom;
        this.ivIcon = ivIcon;
        this.ivMiddle = ivMiddle;
        this.ivRight = ivRight;
        this.ivRightSubtitle = ivRightSubtitle;
        this.ivTextMode = ivTextMode;
        this.ivTextPause = ivTextPause;
        this.ivTextSend = ivTextSend;
        this.ivTextSizeBc = ivTextSizeBc;
        this.ivTextSizeNextBc = ivTextSizeNextBc;
        this.main = main;
        this.recyclerviewColor = recyclerviewColor;
        this.recyclerviewColorBc = recyclerviewColorBc;
        this.recyclerviewColorBcGradation = recyclerviewColorBcGradation;
        this.rlSpeedSeek = rlSpeedSeek;
        this.rvAnimation = rvAnimation;
        this.sbSpeed = sbSpeed;
        this.tvBg = tvBg;
        this.tvBorder = tvBorder;
        this.tvFontlimitInAttr = tvFontlimitInAttr;
        this.tvRight = tvRight;
        this.tvSpeed = tvSpeed;
        this.tvTextPaauseTime = tvTextPaauseTime;
        this.tvTextPause = tvTextPause;
        this.tvTextSize32Bc = tvTextSize32Bc;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCreativeTextBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityCreativeTextBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_creative_text, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityCreativeTextBinding bind(View rootView) {
        int i = R.id.animation_bg;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.animation_bg);
        if (imageView != null) {
            i = R.id.animation_bg_icon;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.animation_bg_icon);
            if (imageView2 != null) {
                i = R.id.border_bg;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.border_bg);
                if (imageView3 != null) {
                    i = R.id.border_icon;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.border_icon);
                    if (imageView4 != null) {
                        i = R.id.cl_text_bg_set;
                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_text_bg_set);
                        if (constraintLayout != null) {
                            i = R.id.cl_text_border_set;
                            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_text_border_set);
                            if (constraintLayout2 != null) {
                                i = R.id.cl_text_pause_set;
                                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_text_pause_set);
                                if (constraintLayout3 != null) {
                                    i = R.id.cl_text_size_set_bc;
                                    ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_text_size_set_bc);
                                    if (constraintLayout4 != null) {
                                        i = R.id.colorBarView_text;
                                        ColorBarView colorBarView = (ColorBarView) ViewBindings.findChildViewById(rootView, R.id.colorBarView_text);
                                        if (colorBarView != null) {
                                            i = R.id.et_input;
                                            NoNewLineEditText noNewLineEditText = (NoNewLineEditText) ViewBindings.findChildViewById(rootView, R.id.et_input);
                                            if (noNewLineEditText != null) {
                                                i = R.id.gif_bg;
                                                ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.gif_bg);
                                                if (constraintLayout5 != null) {
                                                    i = R.id.gif_img;
                                                    AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.gif_img);
                                                    if (appCompatImageView != null) {
                                                        i = R.id.il_text_toolbar;
                                                        ConstraintLayout constraintLayout6 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_text_toolbar);
                                                        if (constraintLayout6 != null) {
                                                            i = R.id.img_bg;
                                                            View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.img_bg);
                                                            if (findChildViewById != null) {
                                                                i = R.id.iv_back;
                                                                CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_back);
                                                                if (customImageView != null) {
                                                                    i = R.id.iv_bottom;
                                                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_bottom);
                                                                    if (imageView5 != null) {
                                                                        i = R.id.iv_icon;
                                                                        ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_icon);
                                                                        if (imageView6 != null) {
                                                                            i = R.id.iv_middle;
                                                                            CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_middle);
                                                                            if (customImageView2 != null) {
                                                                                i = R.id.iv_right;
                                                                                CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right);
                                                                                if (customImageView3 != null) {
                                                                                    i = R.id.iv_right_subtitle;
                                                                                    CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right_subtitle);
                                                                                    if (customImageView4 != null) {
                                                                                        i = R.id.iv_text_mode;
                                                                                        ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_mode);
                                                                                        if (imageView7 != null) {
                                                                                            i = R.id.iv_text_pause;
                                                                                            ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_pause);
                                                                                            if (imageView8 != null) {
                                                                                                i = R.id.iv_text_send;
                                                                                                ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_send);
                                                                                                if (imageView9 != null) {
                                                                                                    i = R.id.iv_text_size_bc;
                                                                                                    ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_size_bc);
                                                                                                    if (imageView10 != null) {
                                                                                                        i = R.id.iv_text_size_next_bc;
                                                                                                        ImageView imageView11 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_size_next_bc);
                                                                                                        if (imageView11 != null) {
                                                                                                            ConstraintLayout constraintLayout7 = (ConstraintLayout) rootView;
                                                                                                            i = R.id.recyclerview_color;
                                                                                                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerview_color);
                                                                                                            if (recyclerView != null) {
                                                                                                                i = R.id.recyclerview_color_bc;
                                                                                                                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerview_color_bc);
                                                                                                                if (recyclerView2 != null) {
                                                                                                                    i = R.id.recyclerview_color_bc_gradation;
                                                                                                                    RecyclerView recyclerView3 = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerview_color_bc_gradation);
                                                                                                                    if (recyclerView3 != null) {
                                                                                                                        i = R.id.rl_speed_seek;
                                                                                                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_speed_seek);
                                                                                                                        if (relativeLayout != null) {
                                                                                                                            i = R.id.rv_animation;
                                                                                                                            RecyclerView recyclerView4 = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rv_animation);
                                                                                                                            if (recyclerView4 != null) {
                                                                                                                                i = R.id.sb_speed;
                                                                                                                                SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, R.id.sb_speed);
                                                                                                                                if (seekBar != null) {
                                                                                                                                    i = R.id.tv_bg;
                                                                                                                                    ImageView imageView12 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tv_bg);
                                                                                                                                    if (imageView12 != null) {
                                                                                                                                        i = R.id.tv_border;
                                                                                                                                        ImageView imageView13 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tv_border);
                                                                                                                                        if (imageView13 != null) {
                                                                                                                                            i = R.id.tv_fontlimit_in_attr;
                                                                                                                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_fontlimit_in_attr);
                                                                                                                                            if (textView != null) {
                                                                                                                                                i = R.id.tv_right;
                                                                                                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_right);
                                                                                                                                                if (textView2 != null) {
                                                                                                                                                    i = R.id.tv_speed;
                                                                                                                                                    ImageView imageView14 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tv_speed);
                                                                                                                                                    if (imageView14 != null) {
                                                                                                                                                        i = R.id.tv_text_paause_time;
                                                                                                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_paause_time);
                                                                                                                                                        if (textView3 != null) {
                                                                                                                                                            i = R.id.tv_text_pause;
                                                                                                                                                            ImageView imageView15 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tv_text_pause);
                                                                                                                                                            if (imageView15 != null) {
                                                                                                                                                                i = R.id.tv_text_size_32_bc;
                                                                                                                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_size_32_bc);
                                                                                                                                                                if (textView4 != null) {
                                                                                                                                                                    i = R.id.tv_title;
                                                                                                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                                                                                                                                                                    if (textView5 != null) {
                                                                                                                                                                        return new ActivityCreativeTextBinding(constraintLayout7, imageView, imageView2, imageView3, imageView4, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, colorBarView, noNewLineEditText, constraintLayout5, appCompatImageView, constraintLayout6, findChildViewById, customImageView, imageView5, imageView6, customImageView2, customImageView3, customImageView4, imageView7, imageView8, imageView9, imageView10, imageView11, constraintLayout7, recyclerView, recyclerView2, recyclerView3, relativeLayout, recyclerView4, seekBar, imageView12, imageView13, textView, textView2, imageView14, textView3, imageView15, textView4, textView5);
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
