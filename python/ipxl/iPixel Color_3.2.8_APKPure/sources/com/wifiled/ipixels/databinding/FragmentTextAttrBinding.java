package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.ColorBarView;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class FragmentTextAttrBinding implements ViewBinding {
    public final ImageView blackBg;
    public final ConstraintLayout clFontAlignment;
    public final ConstraintLayout clTextBgColorSet;
    public final ConstraintLayout clTextBgColorSet2;
    public final ConstraintLayout clTextBgColorSet2Bc;
    public final ConstraintLayout clTextBorder;
    public final ConstraintLayout clTextBorderBc;
    public final ConstraintLayout clTextColorSet;
    public final ConstraintLayout clTextFontSet;
    public final ConstraintLayout clTextFontSetBc;
    public final ConstraintLayout clTextSizeSet;
    public final ConstraintLayout clTextSizeSetBc;
    public final ColorBarView colorBarViewBg;
    public final RecyclerView colorBarViewBgBc;
    public final ColorBarView colorBarViewText;
    public final ImageView imageView;
    public final ImageView ivBorder;
    public final ImageView ivBorderBc;
    public final CustomImageView ivIconTextColor;
    public final ImageView ivNext;
    public final ImageView ivNextBc;
    public final CustomImageView ivShowTextBgcolor;
    public final ImageView ivShowTextBgcolorTransparent;
    public final ImageView ivShowTextBgcolorTransparentBc;
    public final CustomImageView ivShowTextcolor;
    public final CustomImageView ivTextBgColor;
    public final ImageView ivTextBgColor2;
    public final ImageView ivTextBgColor2Bc;
    public final ImageView ivTextFont;
    public final ImageView ivTextFontBc;
    public final ImageView ivTextFontNext;
    public final ImageView ivTextFontNextBc;
    public final ImageView ivTextSize;
    public final ImageView ivTextSizeBc;
    public final ImageView ivTextSizeNext;
    public final ImageView ivTextSizeNextBc;
    public final RadioButton rbCenter;
    public final RadioButton rbLeft;
    public final RadioButton rbRight;
    public final RadioButton rbVerticalBottom;
    public final RadioButton rbVerticalCenter;
    public final RadioButton rbVerticalTop;
    public final RecyclerView recyclerviewBaseColor;
    public final RecyclerView recyclerviewBaseColorGradation;
    public final RecyclerView recyclerviewColor;
    public final RadioGroup rgAlign;
    public final RadioGroup rgAlignVertical;
    public final RelativeLayout rlOutsideBgcolorFrame;
    public final RelativeLayout rlOutsideBgcolorFrame2;
    public final RelativeLayout rlOutsideBgcolorFrame2Bc;
    public final RelativeLayout rlOutsideTextcolorFrame;
    private final ConstraintLayout rootView;
    public final LinearLayout styleBaseColor;
    public final LinearLayout styleRectangle;
    public final LinearLayout styleSquare;
    public final TextView textBold;
    public final TextView textBoldBc;
    public final ImageView textBoldIv;
    public final ImageView textBoldIvBc;
    public final ConstraintLayout textBoldLayout;
    public final ConstraintLayout textBoldLayoutBc;
    public final TextView textBorder;
    public final TextView textBorderBc;
    public final TextView textDirection;
    public final TextView textDirectionBc;
    public final ImageView textDirectionHorizontal;
    public final ImageView textDirectionHorizontalBc;
    public final ConstraintLayout textDirectionLayout;
    public final ConstraintLayout textDirectionLayoutBc;
    public final ImageView textDirectionVertical;
    public final ImageView textDirectionVerticalBc;
    public final AppCompatTextView tvFont;
    public final TextView tvTextFont;
    public final TextView tvTextFontBc;
    public final AppCompatTextView tvTextSize;
    public final TextView tvTextSize32128;
    public final TextView tvTextSize32Bc;

    private FragmentTextAttrBinding(ConstraintLayout rootView, ImageView blackBg, ConstraintLayout clFontAlignment, ConstraintLayout clTextBgColorSet, ConstraintLayout clTextBgColorSet2, ConstraintLayout clTextBgColorSet2Bc, ConstraintLayout clTextBorder, ConstraintLayout clTextBorderBc, ConstraintLayout clTextColorSet, ConstraintLayout clTextFontSet, ConstraintLayout clTextFontSetBc, ConstraintLayout clTextSizeSet, ConstraintLayout clTextSizeSetBc, ColorBarView colorBarViewBg, RecyclerView colorBarViewBgBc, ColorBarView colorBarViewText, ImageView imageView, ImageView ivBorder, ImageView ivBorderBc, CustomImageView ivIconTextColor, ImageView ivNext, ImageView ivNextBc, CustomImageView ivShowTextBgcolor, ImageView ivShowTextBgcolorTransparent, ImageView ivShowTextBgcolorTransparentBc, CustomImageView ivShowTextcolor, CustomImageView ivTextBgColor, ImageView ivTextBgColor2, ImageView ivTextBgColor2Bc, ImageView ivTextFont, ImageView ivTextFontBc, ImageView ivTextFontNext, ImageView ivTextFontNextBc, ImageView ivTextSize, ImageView ivTextSizeBc, ImageView ivTextSizeNext, ImageView ivTextSizeNextBc, RadioButton rbCenter, RadioButton rbLeft, RadioButton rbRight, RadioButton rbVerticalBottom, RadioButton rbVerticalCenter, RadioButton rbVerticalTop, RecyclerView recyclerviewBaseColor, RecyclerView recyclerviewBaseColorGradation, RecyclerView recyclerviewColor, RadioGroup rgAlign, RadioGroup rgAlignVertical, RelativeLayout rlOutsideBgcolorFrame, RelativeLayout rlOutsideBgcolorFrame2, RelativeLayout rlOutsideBgcolorFrame2Bc, RelativeLayout rlOutsideTextcolorFrame, LinearLayout styleBaseColor, LinearLayout styleRectangle, LinearLayout styleSquare, TextView textBold, TextView textBoldBc, ImageView textBoldIv, ImageView textBoldIvBc, ConstraintLayout textBoldLayout, ConstraintLayout textBoldLayoutBc, TextView textBorder, TextView textBorderBc, TextView textDirection, TextView textDirectionBc, ImageView textDirectionHorizontal, ImageView textDirectionHorizontalBc, ConstraintLayout textDirectionLayout, ConstraintLayout textDirectionLayoutBc, ImageView textDirectionVertical, ImageView textDirectionVerticalBc, AppCompatTextView tvFont, TextView tvTextFont, TextView tvTextFontBc, AppCompatTextView tvTextSize, TextView tvTextSize32128, TextView tvTextSize32Bc) {
        this.rootView = rootView;
        this.blackBg = blackBg;
        this.clFontAlignment = clFontAlignment;
        this.clTextBgColorSet = clTextBgColorSet;
        this.clTextBgColorSet2 = clTextBgColorSet2;
        this.clTextBgColorSet2Bc = clTextBgColorSet2Bc;
        this.clTextBorder = clTextBorder;
        this.clTextBorderBc = clTextBorderBc;
        this.clTextColorSet = clTextColorSet;
        this.clTextFontSet = clTextFontSet;
        this.clTextFontSetBc = clTextFontSetBc;
        this.clTextSizeSet = clTextSizeSet;
        this.clTextSizeSetBc = clTextSizeSetBc;
        this.colorBarViewBg = colorBarViewBg;
        this.colorBarViewBgBc = colorBarViewBgBc;
        this.colorBarViewText = colorBarViewText;
        this.imageView = imageView;
        this.ivBorder = ivBorder;
        this.ivBorderBc = ivBorderBc;
        this.ivIconTextColor = ivIconTextColor;
        this.ivNext = ivNext;
        this.ivNextBc = ivNextBc;
        this.ivShowTextBgcolor = ivShowTextBgcolor;
        this.ivShowTextBgcolorTransparent = ivShowTextBgcolorTransparent;
        this.ivShowTextBgcolorTransparentBc = ivShowTextBgcolorTransparentBc;
        this.ivShowTextcolor = ivShowTextcolor;
        this.ivTextBgColor = ivTextBgColor;
        this.ivTextBgColor2 = ivTextBgColor2;
        this.ivTextBgColor2Bc = ivTextBgColor2Bc;
        this.ivTextFont = ivTextFont;
        this.ivTextFontBc = ivTextFontBc;
        this.ivTextFontNext = ivTextFontNext;
        this.ivTextFontNextBc = ivTextFontNextBc;
        this.ivTextSize = ivTextSize;
        this.ivTextSizeBc = ivTextSizeBc;
        this.ivTextSizeNext = ivTextSizeNext;
        this.ivTextSizeNextBc = ivTextSizeNextBc;
        this.rbCenter = rbCenter;
        this.rbLeft = rbLeft;
        this.rbRight = rbRight;
        this.rbVerticalBottom = rbVerticalBottom;
        this.rbVerticalCenter = rbVerticalCenter;
        this.rbVerticalTop = rbVerticalTop;
        this.recyclerviewBaseColor = recyclerviewBaseColor;
        this.recyclerviewBaseColorGradation = recyclerviewBaseColorGradation;
        this.recyclerviewColor = recyclerviewColor;
        this.rgAlign = rgAlign;
        this.rgAlignVertical = rgAlignVertical;
        this.rlOutsideBgcolorFrame = rlOutsideBgcolorFrame;
        this.rlOutsideBgcolorFrame2 = rlOutsideBgcolorFrame2;
        this.rlOutsideBgcolorFrame2Bc = rlOutsideBgcolorFrame2Bc;
        this.rlOutsideTextcolorFrame = rlOutsideTextcolorFrame;
        this.styleBaseColor = styleBaseColor;
        this.styleRectangle = styleRectangle;
        this.styleSquare = styleSquare;
        this.textBold = textBold;
        this.textBoldBc = textBoldBc;
        this.textBoldIv = textBoldIv;
        this.textBoldIvBc = textBoldIvBc;
        this.textBoldLayout = textBoldLayout;
        this.textBoldLayoutBc = textBoldLayoutBc;
        this.textBorder = textBorder;
        this.textBorderBc = textBorderBc;
        this.textDirection = textDirection;
        this.textDirectionBc = textDirectionBc;
        this.textDirectionHorizontal = textDirectionHorizontal;
        this.textDirectionHorizontalBc = textDirectionHorizontalBc;
        this.textDirectionLayout = textDirectionLayout;
        this.textDirectionLayoutBc = textDirectionLayoutBc;
        this.textDirectionVertical = textDirectionVertical;
        this.textDirectionVerticalBc = textDirectionVerticalBc;
        this.tvFont = tvFont;
        this.tvTextFont = tvTextFont;
        this.tvTextFontBc = tvTextFontBc;
        this.tvTextSize = tvTextSize;
        this.tvTextSize32128 = tvTextSize32128;
        this.tvTextSize32Bc = tvTextSize32Bc;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentTextAttrBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentTextAttrBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_text_attr, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentTextAttrBinding bind(View rootView) {
        int i = R.id.black_bg;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.black_bg);
        if (imageView != null) {
            i = R.id.cl_font_alignment;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_font_alignment);
            if (constraintLayout != null) {
                i = R.id.cl_text_bg_color_set;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_text_bg_color_set);
                if (constraintLayout2 != null) {
                    i = R.id.cl_text_bg_color_set2;
                    ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_text_bg_color_set2);
                    if (constraintLayout3 != null) {
                        i = R.id.cl_text_bg_color_set2_bc;
                        ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_text_bg_color_set2_bc);
                        if (constraintLayout4 != null) {
                            i = R.id.cl_text_border;
                            ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_text_border);
                            if (constraintLayout5 != null) {
                                i = R.id.cl_text_border_bc;
                                ConstraintLayout constraintLayout6 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_text_border_bc);
                                if (constraintLayout6 != null) {
                                    i = R.id.cl_text_color_set;
                                    ConstraintLayout constraintLayout7 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_text_color_set);
                                    if (constraintLayout7 != null) {
                                        i = R.id.cl_text_font_set;
                                        ConstraintLayout constraintLayout8 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_text_font_set);
                                        if (constraintLayout8 != null) {
                                            i = R.id.cl_text_font_set_bc;
                                            ConstraintLayout constraintLayout9 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_text_font_set_bc);
                                            if (constraintLayout9 != null) {
                                                i = R.id.cl_text_size_set;
                                                ConstraintLayout constraintLayout10 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_text_size_set);
                                                if (constraintLayout10 != null) {
                                                    i = R.id.cl_text_size_set_bc;
                                                    ConstraintLayout constraintLayout11 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_text_size_set_bc);
                                                    if (constraintLayout11 != null) {
                                                        i = R.id.colorBarView_bg;
                                                        ColorBarView colorBarView = (ColorBarView) ViewBindings.findChildViewById(rootView, R.id.colorBarView_bg);
                                                        if (colorBarView != null) {
                                                            i = R.id.colorBarView_bg_bc;
                                                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.colorBarView_bg_bc);
                                                            if (recyclerView != null) {
                                                                i = R.id.colorBarView_text;
                                                                ColorBarView colorBarView2 = (ColorBarView) ViewBindings.findChildViewById(rootView, R.id.colorBarView_text);
                                                                if (colorBarView2 != null) {
                                                                    i = R.id.imageView;
                                                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imageView);
                                                                    if (imageView2 != null) {
                                                                        i = R.id.iv_border;
                                                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_border);
                                                                        if (imageView3 != null) {
                                                                            i = R.id.iv_border_bc;
                                                                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_border_bc);
                                                                            if (imageView4 != null) {
                                                                                i = R.id.iv_icon_text_color;
                                                                                CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_icon_text_color);
                                                                                if (customImageView != null) {
                                                                                    i = R.id.iv_next;
                                                                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_next);
                                                                                    if (imageView5 != null) {
                                                                                        i = R.id.iv_next_bc;
                                                                                        ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_next_bc);
                                                                                        if (imageView6 != null) {
                                                                                            i = R.id.iv_show_text_bgcolor;
                                                                                            CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_show_text_bgcolor);
                                                                                            if (customImageView2 != null) {
                                                                                                i = R.id.iv_show_text_bgcolor_transparent;
                                                                                                ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_show_text_bgcolor_transparent);
                                                                                                if (imageView7 != null) {
                                                                                                    i = R.id.iv_show_text_bgcolor_transparent_bc;
                                                                                                    ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_show_text_bgcolor_transparent_bc);
                                                                                                    if (imageView8 != null) {
                                                                                                        i = R.id.iv_show_textcolor;
                                                                                                        CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_show_textcolor);
                                                                                                        if (customImageView3 != null) {
                                                                                                            i = R.id.iv_text_bg_color;
                                                                                                            CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_bg_color);
                                                                                                            if (customImageView4 != null) {
                                                                                                                i = R.id.iv_text_bg_color2;
                                                                                                                ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_bg_color2);
                                                                                                                if (imageView9 != null) {
                                                                                                                    i = R.id.iv_text_bg_color2_bc;
                                                                                                                    ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_bg_color2_bc);
                                                                                                                    if (imageView10 != null) {
                                                                                                                        i = R.id.iv_text_font;
                                                                                                                        ImageView imageView11 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_font);
                                                                                                                        if (imageView11 != null) {
                                                                                                                            i = R.id.iv_text_font_bc;
                                                                                                                            ImageView imageView12 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_font_bc);
                                                                                                                            if (imageView12 != null) {
                                                                                                                                i = R.id.iv_text_font_next;
                                                                                                                                ImageView imageView13 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_font_next);
                                                                                                                                if (imageView13 != null) {
                                                                                                                                    i = R.id.iv_text_font_next_bc;
                                                                                                                                    ImageView imageView14 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_font_next_bc);
                                                                                                                                    if (imageView14 != null) {
                                                                                                                                        i = R.id.iv_text_size;
                                                                                                                                        ImageView imageView15 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_size);
                                                                                                                                        if (imageView15 != null) {
                                                                                                                                            i = R.id.iv_text_size_bc;
                                                                                                                                            ImageView imageView16 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_size_bc);
                                                                                                                                            if (imageView16 != null) {
                                                                                                                                                i = R.id.iv_text_size_next;
                                                                                                                                                ImageView imageView17 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_size_next);
                                                                                                                                                if (imageView17 != null) {
                                                                                                                                                    i = R.id.iv_text_size_next_bc;
                                                                                                                                                    ImageView imageView18 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_size_next_bc);
                                                                                                                                                    if (imageView18 != null) {
                                                                                                                                                        i = R.id.rb_center;
                                                                                                                                                        RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_center);
                                                                                                                                                        if (radioButton != null) {
                                                                                                                                                            i = R.id.rb_left;
                                                                                                                                                            RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_left);
                                                                                                                                                            if (radioButton2 != null) {
                                                                                                                                                                i = R.id.rb_right;
                                                                                                                                                                RadioButton radioButton3 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_right);
                                                                                                                                                                if (radioButton3 != null) {
                                                                                                                                                                    i = R.id.rb_vertical_bottom;
                                                                                                                                                                    RadioButton radioButton4 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_vertical_bottom);
                                                                                                                                                                    if (radioButton4 != null) {
                                                                                                                                                                        i = R.id.rb_vertical_center;
                                                                                                                                                                        RadioButton radioButton5 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_vertical_center);
                                                                                                                                                                        if (radioButton5 != null) {
                                                                                                                                                                            i = R.id.rb_vertical_top;
                                                                                                                                                                            RadioButton radioButton6 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_vertical_top);
                                                                                                                                                                            if (radioButton6 != null) {
                                                                                                                                                                                i = R.id.recyclerview_base_color;
                                                                                                                                                                                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerview_base_color);
                                                                                                                                                                                if (recyclerView2 != null) {
                                                                                                                                                                                    i = R.id.recyclerview_base_color_gradation;
                                                                                                                                                                                    RecyclerView recyclerView3 = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerview_base_color_gradation);
                                                                                                                                                                                    if (recyclerView3 != null) {
                                                                                                                                                                                        i = R.id.recyclerview_color;
                                                                                                                                                                                        RecyclerView recyclerView4 = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerview_color);
                                                                                                                                                                                        if (recyclerView4 != null) {
                                                                                                                                                                                            i = R.id.rg_align;
                                                                                                                                                                                            RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(rootView, R.id.rg_align);
                                                                                                                                                                                            if (radioGroup != null) {
                                                                                                                                                                                                i = R.id.rg_align_vertical;
                                                                                                                                                                                                RadioGroup radioGroup2 = (RadioGroup) ViewBindings.findChildViewById(rootView, R.id.rg_align_vertical);
                                                                                                                                                                                                if (radioGroup2 != null) {
                                                                                                                                                                                                    i = R.id.rl_outside_bgcolor_frame;
                                                                                                                                                                                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_outside_bgcolor_frame);
                                                                                                                                                                                                    if (relativeLayout != null) {
                                                                                                                                                                                                        i = R.id.rl_outside_bgcolor_frame2;
                                                                                                                                                                                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_outside_bgcolor_frame2);
                                                                                                                                                                                                        if (relativeLayout2 != null) {
                                                                                                                                                                                                            i = R.id.rl_outside_bgcolor_frame2_bc;
                                                                                                                                                                                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_outside_bgcolor_frame2_bc);
                                                                                                                                                                                                            if (relativeLayout3 != null) {
                                                                                                                                                                                                                i = R.id.rl_outside_textcolor_frame;
                                                                                                                                                                                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_outside_textcolor_frame);
                                                                                                                                                                                                                if (relativeLayout4 != null) {
                                                                                                                                                                                                                    i = R.id.style_base_color;
                                                                                                                                                                                                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.style_base_color);
                                                                                                                                                                                                                    if (linearLayout != null) {
                                                                                                                                                                                                                        i = R.id.style_rectangle;
                                                                                                                                                                                                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.style_rectangle);
                                                                                                                                                                                                                        if (linearLayout2 != null) {
                                                                                                                                                                                                                            i = R.id.style_square;
                                                                                                                                                                                                                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.style_square);
                                                                                                                                                                                                                            if (linearLayout3 != null) {
                                                                                                                                                                                                                                i = R.id.text_bold;
                                                                                                                                                                                                                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.text_bold);
                                                                                                                                                                                                                                if (textView != null) {
                                                                                                                                                                                                                                    i = R.id.text_bold_bc;
                                                                                                                                                                                                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text_bold_bc);
                                                                                                                                                                                                                                    if (textView2 != null) {
                                                                                                                                                                                                                                        i = R.id.text_bold_iv;
                                                                                                                                                                                                                                        ImageView imageView19 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.text_bold_iv);
                                                                                                                                                                                                                                        if (imageView19 != null) {
                                                                                                                                                                                                                                            i = R.id.text_bold_iv_bc;
                                                                                                                                                                                                                                            ImageView imageView20 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.text_bold_iv_bc);
                                                                                                                                                                                                                                            if (imageView20 != null) {
                                                                                                                                                                                                                                                i = R.id.text_bold_layout;
                                                                                                                                                                                                                                                ConstraintLayout constraintLayout12 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.text_bold_layout);
                                                                                                                                                                                                                                                if (constraintLayout12 != null) {
                                                                                                                                                                                                                                                    i = R.id.text_bold_layout_bc;
                                                                                                                                                                                                                                                    ConstraintLayout constraintLayout13 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.text_bold_layout_bc);
                                                                                                                                                                                                                                                    if (constraintLayout13 != null) {
                                                                                                                                                                                                                                                        i = R.id.text_border;
                                                                                                                                                                                                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text_border);
                                                                                                                                                                                                                                                        if (textView3 != null) {
                                                                                                                                                                                                                                                            i = R.id.text_border_bc;
                                                                                                                                                                                                                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text_border_bc);
                                                                                                                                                                                                                                                            if (textView4 != null) {
                                                                                                                                                                                                                                                                i = R.id.text_direction;
                                                                                                                                                                                                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text_direction);
                                                                                                                                                                                                                                                                if (textView5 != null) {
                                                                                                                                                                                                                                                                    i = R.id.text_direction_bc;
                                                                                                                                                                                                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text_direction_bc);
                                                                                                                                                                                                                                                                    if (textView6 != null) {
                                                                                                                                                                                                                                                                        i = R.id.text_direction_horizontal;
                                                                                                                                                                                                                                                                        ImageView imageView21 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.text_direction_horizontal);
                                                                                                                                                                                                                                                                        if (imageView21 != null) {
                                                                                                                                                                                                                                                                            i = R.id.text_direction_horizontal_bc;
                                                                                                                                                                                                                                                                            ImageView imageView22 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.text_direction_horizontal_bc);
                                                                                                                                                                                                                                                                            if (imageView22 != null) {
                                                                                                                                                                                                                                                                                i = R.id.text_direction_layout;
                                                                                                                                                                                                                                                                                ConstraintLayout constraintLayout14 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.text_direction_layout);
                                                                                                                                                                                                                                                                                if (constraintLayout14 != null) {
                                                                                                                                                                                                                                                                                    i = R.id.text_direction_layout_bc;
                                                                                                                                                                                                                                                                                    ConstraintLayout constraintLayout15 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.text_direction_layout_bc);
                                                                                                                                                                                                                                                                                    if (constraintLayout15 != null) {
                                                                                                                                                                                                                                                                                        i = R.id.text_direction_vertical;
                                                                                                                                                                                                                                                                                        ImageView imageView23 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.text_direction_vertical);
                                                                                                                                                                                                                                                                                        if (imageView23 != null) {
                                                                                                                                                                                                                                                                                            i = R.id.text_direction_vertical_bc;
                                                                                                                                                                                                                                                                                            ImageView imageView24 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.text_direction_vertical_bc);
                                                                                                                                                                                                                                                                                            if (imageView24 != null) {
                                                                                                                                                                                                                                                                                                i = R.id.tv_font;
                                                                                                                                                                                                                                                                                                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.tv_font);
                                                                                                                                                                                                                                                                                                if (appCompatTextView != null) {
                                                                                                                                                                                                                                                                                                    i = R.id.tv_text_font;
                                                                                                                                                                                                                                                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_font);
                                                                                                                                                                                                                                                                                                    if (textView7 != null) {
                                                                                                                                                                                                                                                                                                        i = R.id.tv_text_font_bc;
                                                                                                                                                                                                                                                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_font_bc);
                                                                                                                                                                                                                                                                                                        if (textView8 != null) {
                                                                                                                                                                                                                                                                                                            i = R.id.tv_textSize;
                                                                                                                                                                                                                                                                                                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.tv_textSize);
                                                                                                                                                                                                                                                                                                            if (appCompatTextView2 != null) {
                                                                                                                                                                                                                                                                                                                i = R.id.tv_text_size_32_128;
                                                                                                                                                                                                                                                                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_size_32_128);
                                                                                                                                                                                                                                                                                                                if (textView9 != null) {
                                                                                                                                                                                                                                                                                                                    i = R.id.tv_text_size_32_bc;
                                                                                                                                                                                                                                                                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_size_32_bc);
                                                                                                                                                                                                                                                                                                                    if (textView10 != null) {
                                                                                                                                                                                                                                                                                                                        return new FragmentTextAttrBinding((ConstraintLayout) rootView, imageView, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, constraintLayout5, constraintLayout6, constraintLayout7, constraintLayout8, constraintLayout9, constraintLayout10, constraintLayout11, colorBarView, recyclerView, colorBarView2, imageView2, imageView3, imageView4, customImageView, imageView5, imageView6, customImageView2, imageView7, imageView8, customImageView3, customImageView4, imageView9, imageView10, imageView11, imageView12, imageView13, imageView14, imageView15, imageView16, imageView17, imageView18, radioButton, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6, recyclerView2, recyclerView3, recyclerView4, radioGroup, radioGroup2, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, linearLayout, linearLayout2, linearLayout3, textView, textView2, imageView19, imageView20, constraintLayout12, constraintLayout13, textView3, textView4, textView5, textView6, imageView21, imageView22, constraintLayout14, constraintLayout15, imageView23, imageView24, appCompatTextView, textView7, textView8, appCompatTextView2, textView9, textView10);
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
