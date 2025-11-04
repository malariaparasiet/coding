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
public final class ActivityTextAnimationBinding implements ViewBinding {
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
    public final ImageView ivTextPause;
    public final ImageView ivTextSend;
    public final RecyclerView recyclerviewColor;
    public final RecyclerView recyclerviewColorBc;
    public final RecyclerView recyclerviewColorBcGradation;
    public final RelativeLayout rlSpeedSeek;
    private final ConstraintLayout rootView;
    public final RecyclerView rvAnimation;
    public final SeekBar sbSpeed;
    public final TextView textDirection;
    public final ImageView textDirectionHorizontal;
    public final ImageView textDirectionVertical;
    public final TextView tvFontlimitInAttr;
    public final TextView tvRight;
    public final TextView tvSpeed;
    public final TextView tvTextPaauseTime;
    public final TextView tvTextPause;
    public final TextView tvTitle;

    private ActivityTextAnimationBinding(ConstraintLayout rootView, ColorBarView colorBarViewText, NoNewLineEditText etInput, ConstraintLayout gifBg, AppCompatImageView gifImg, ConstraintLayout ilTextToolbar, View imgBg, CustomImageView ivBack, ImageView ivBottom, ImageView ivIcon, CustomImageView ivMiddle, CustomImageView ivRight, CustomImageView ivRightSubtitle, ImageView ivTextPause, ImageView ivTextSend, RecyclerView recyclerviewColor, RecyclerView recyclerviewColorBc, RecyclerView recyclerviewColorBcGradation, RelativeLayout rlSpeedSeek, RecyclerView rvAnimation, SeekBar sbSpeed, TextView textDirection, ImageView textDirectionHorizontal, ImageView textDirectionVertical, TextView tvFontlimitInAttr, TextView tvRight, TextView tvSpeed, TextView tvTextPaauseTime, TextView tvTextPause, TextView tvTitle) {
        this.rootView = rootView;
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
        this.ivTextPause = ivTextPause;
        this.ivTextSend = ivTextSend;
        this.recyclerviewColor = recyclerviewColor;
        this.recyclerviewColorBc = recyclerviewColorBc;
        this.recyclerviewColorBcGradation = recyclerviewColorBcGradation;
        this.rlSpeedSeek = rlSpeedSeek;
        this.rvAnimation = rvAnimation;
        this.sbSpeed = sbSpeed;
        this.textDirection = textDirection;
        this.textDirectionHorizontal = textDirectionHorizontal;
        this.textDirectionVertical = textDirectionVertical;
        this.tvFontlimitInAttr = tvFontlimitInAttr;
        this.tvRight = tvRight;
        this.tvSpeed = tvSpeed;
        this.tvTextPaauseTime = tvTextPaauseTime;
        this.tvTextPause = tvTextPause;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityTextAnimationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityTextAnimationBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_text_animation, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityTextAnimationBinding bind(View rootView) {
        int i = R.id.colorBarView_text;
        ColorBarView colorBarView = (ColorBarView) ViewBindings.findChildViewById(rootView, R.id.colorBarView_text);
        if (colorBarView != null) {
            i = R.id.et_input;
            NoNewLineEditText noNewLineEditText = (NoNewLineEditText) ViewBindings.findChildViewById(rootView, R.id.et_input);
            if (noNewLineEditText != null) {
                i = R.id.gif_bg;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.gif_bg);
                if (constraintLayout != null) {
                    i = R.id.gif_img;
                    AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.gif_img);
                    if (appCompatImageView != null) {
                        i = R.id.il_text_toolbar;
                        ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_text_toolbar);
                        if (constraintLayout2 != null) {
                            i = R.id.img_bg;
                            View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.img_bg);
                            if (findChildViewById != null) {
                                i = R.id.iv_back;
                                CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_back);
                                if (customImageView != null) {
                                    i = R.id.iv_bottom;
                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_bottom);
                                    if (imageView != null) {
                                        i = R.id.iv_icon;
                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_icon);
                                        if (imageView2 != null) {
                                            i = R.id.iv_middle;
                                            CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_middle);
                                            if (customImageView2 != null) {
                                                i = R.id.iv_right;
                                                CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right);
                                                if (customImageView3 != null) {
                                                    i = R.id.iv_right_subtitle;
                                                    CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right_subtitle);
                                                    if (customImageView4 != null) {
                                                        i = R.id.iv_text_pause;
                                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_pause);
                                                        if (imageView3 != null) {
                                                            i = R.id.iv_text_send;
                                                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_send);
                                                            if (imageView4 != null) {
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
                                                                                        i = R.id.text_direction;
                                                                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.text_direction);
                                                                                        if (textView != null) {
                                                                                            i = R.id.text_direction_horizontal;
                                                                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.text_direction_horizontal);
                                                                                            if (imageView5 != null) {
                                                                                                i = R.id.text_direction_vertical;
                                                                                                ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.text_direction_vertical);
                                                                                                if (imageView6 != null) {
                                                                                                    i = R.id.tv_fontlimit_in_attr;
                                                                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_fontlimit_in_attr);
                                                                                                    if (textView2 != null) {
                                                                                                        i = R.id.tv_right;
                                                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_right);
                                                                                                        if (textView3 != null) {
                                                                                                            i = R.id.tv_speed;
                                                                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_speed);
                                                                                                            if (textView4 != null) {
                                                                                                                i = R.id.tv_text_paause_time;
                                                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_paause_time);
                                                                                                                if (textView5 != null) {
                                                                                                                    i = R.id.tv_text_pause;
                                                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_pause);
                                                                                                                    if (textView6 != null) {
                                                                                                                        i = R.id.tv_title;
                                                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                                                                                                                        if (textView7 != null) {
                                                                                                                            return new ActivityTextAnimationBinding((ConstraintLayout) rootView, colorBarView, noNewLineEditText, constraintLayout, appCompatImageView, constraintLayout2, findChildViewById, customImageView, imageView, imageView2, customImageView2, customImageView3, customImageView4, imageView3, imageView4, recyclerView, recyclerView2, recyclerView3, relativeLayout, recyclerView4, seekBar, textView, imageView5, imageView6, textView2, textView3, textView4, textView5, textView6, textView7);
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
