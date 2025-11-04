package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.wifiled.baselib.widget.NoNewLineEditText;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class ActivityTextBinding implements ViewBinding {
    public final Barrier barrierTop;
    public final ConstraintLayout clEditInAttr;
    public final ConstraintLayout clEditKbInAttr;
    public final ConstraintLayout clEditOutframeInAttr;
    public final ConstraintLayout clKbEmojBottom;
    public final ConstraintLayout clMenuTab;
    public final ConstraintLayout clTextMain;
    public final Guideline glMiddle;
    public final Guideline guideline6;
    public final ConstraintLayout ilTextToolbar;
    public final CustomImageView ivEmojiInAttr;
    public final CustomImageView ivKbInAttr;
    public final ImageView ivTextMode;
    public final ImageView ivTextSend;
    public final LinearLayout ll;
    public final RelativeLayout rlHideKeyboard;
    public final RecyclerView rlInputInAttr;
    private final ConstraintLayout rootView;
    public final NoNewLineEditText testEdit;
    public final TextView tvFontlimitInAttr;
    public final TextView tvMenuAttr;
    public final TextView tvMenuEffect;
    public final TextView tvMenuRec;
    public final View viewTextinputMiddle;
    public final View viewTextmainKeyboard;
    public final ViewPager2 viewpager;

    private ActivityTextBinding(ConstraintLayout rootView, Barrier barrierTop, ConstraintLayout clEditInAttr, ConstraintLayout clEditKbInAttr, ConstraintLayout clEditOutframeInAttr, ConstraintLayout clKbEmojBottom, ConstraintLayout clMenuTab, ConstraintLayout clTextMain, Guideline glMiddle, Guideline guideline6, ConstraintLayout ilTextToolbar, CustomImageView ivEmojiInAttr, CustomImageView ivKbInAttr, ImageView ivTextMode, ImageView ivTextSend, LinearLayout ll, RelativeLayout rlHideKeyboard, RecyclerView rlInputInAttr, NoNewLineEditText testEdit, TextView tvFontlimitInAttr, TextView tvMenuAttr, TextView tvMenuEffect, TextView tvMenuRec, View viewTextinputMiddle, View viewTextmainKeyboard, ViewPager2 viewpager) {
        this.rootView = rootView;
        this.barrierTop = barrierTop;
        this.clEditInAttr = clEditInAttr;
        this.clEditKbInAttr = clEditKbInAttr;
        this.clEditOutframeInAttr = clEditOutframeInAttr;
        this.clKbEmojBottom = clKbEmojBottom;
        this.clMenuTab = clMenuTab;
        this.clTextMain = clTextMain;
        this.glMiddle = glMiddle;
        this.guideline6 = guideline6;
        this.ilTextToolbar = ilTextToolbar;
        this.ivEmojiInAttr = ivEmojiInAttr;
        this.ivKbInAttr = ivKbInAttr;
        this.ivTextMode = ivTextMode;
        this.ivTextSend = ivTextSend;
        this.ll = ll;
        this.rlHideKeyboard = rlHideKeyboard;
        this.rlInputInAttr = rlInputInAttr;
        this.testEdit = testEdit;
        this.tvFontlimitInAttr = tvFontlimitInAttr;
        this.tvMenuAttr = tvMenuAttr;
        this.tvMenuEffect = tvMenuEffect;
        this.tvMenuRec = tvMenuRec;
        this.viewTextinputMiddle = viewTextinputMiddle;
        this.viewTextmainKeyboard = viewTextmainKeyboard;
        this.viewpager = viewpager;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityTextBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityTextBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_text, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityTextBinding bind(View rootView) {
        int i = R.id.barrier_top;
        Barrier barrier = (Barrier) ViewBindings.findChildViewById(rootView, R.id.barrier_top);
        if (barrier != null) {
            i = R.id.cl_edit_in_attr;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_edit_in_attr);
            if (constraintLayout != null) {
                i = R.id.cl_edit_kb_in_attr;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_edit_kb_in_attr);
                if (constraintLayout2 != null) {
                    i = R.id.cl_edit_outframe_in_attr;
                    ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_edit_outframe_in_attr);
                    if (constraintLayout3 != null) {
                        i = R.id.cl_kb_emoj_bottom;
                        ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_kb_emoj_bottom);
                        if (constraintLayout4 != null) {
                            i = R.id.cl_menu_tab;
                            ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_menu_tab);
                            if (constraintLayout5 != null) {
                                ConstraintLayout constraintLayout6 = (ConstraintLayout) rootView;
                                i = R.id.gl_middle;
                                Guideline guideline = (Guideline) ViewBindings.findChildViewById(rootView, R.id.gl_middle);
                                if (guideline != null) {
                                    i = R.id.guideline6;
                                    Guideline guideline2 = (Guideline) ViewBindings.findChildViewById(rootView, R.id.guideline6);
                                    if (guideline2 != null) {
                                        i = R.id.il_text_toolbar;
                                        ConstraintLayout constraintLayout7 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_text_toolbar);
                                        if (constraintLayout7 != null) {
                                            i = R.id.iv_emoji_in_attr;
                                            CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_emoji_in_attr);
                                            if (customImageView != null) {
                                                i = R.id.iv_kb_in_attr;
                                                CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_kb_in_attr);
                                                if (customImageView2 != null) {
                                                    i = R.id.iv_text_mode;
                                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_mode);
                                                    if (imageView != null) {
                                                        i = R.id.iv_text_send;
                                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_text_send);
                                                        if (imageView2 != null) {
                                                            i = R.id.ll;
                                                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll);
                                                            if (linearLayout != null) {
                                                                i = R.id.rl_hide_keyboard;
                                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_hide_keyboard);
                                                                if (relativeLayout != null) {
                                                                    i = R.id.rl_input_in_attr;
                                                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rl_input_in_attr);
                                                                    if (recyclerView != null) {
                                                                        i = R.id.test_edit;
                                                                        NoNewLineEditText noNewLineEditText = (NoNewLineEditText) ViewBindings.findChildViewById(rootView, R.id.test_edit);
                                                                        if (noNewLineEditText != null) {
                                                                            i = R.id.tv_fontlimit_in_attr;
                                                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_fontlimit_in_attr);
                                                                            if (textView != null) {
                                                                                i = R.id.tv_menu_attr;
                                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_menu_attr);
                                                                                if (textView2 != null) {
                                                                                    i = R.id.tv_menu_effect;
                                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_menu_effect);
                                                                                    if (textView3 != null) {
                                                                                        i = R.id.tv_menu_rec;
                                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_menu_rec);
                                                                                        if (textView4 != null) {
                                                                                            i = R.id.view_textinput_middle;
                                                                                            View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.view_textinput_middle);
                                                                                            if (findChildViewById != null) {
                                                                                                i = R.id.view_textmain_keyboard;
                                                                                                View findChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.view_textmain_keyboard);
                                                                                                if (findChildViewById2 != null) {
                                                                                                    i = R.id.viewpager;
                                                                                                    ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(rootView, R.id.viewpager);
                                                                                                    if (viewPager2 != null) {
                                                                                                        return new ActivityTextBinding(constraintLayout6, barrier, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, constraintLayout5, constraintLayout6, guideline, guideline2, constraintLayout7, customImageView, customImageView2, imageView, imageView2, linearLayout, relativeLayout, recyclerView, noNewLineEditText, textView, textView2, textView3, textView4, findChildViewById, findChildViewById2, viewPager2);
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
