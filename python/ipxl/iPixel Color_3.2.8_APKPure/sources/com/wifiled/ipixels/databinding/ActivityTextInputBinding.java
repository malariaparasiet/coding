package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.TextEmojiView;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class ActivityTextInputBinding implements ViewBinding {
    public final Barrier barrierTop;
    public final ConstraintLayout clEdit;
    public final ConstraintLayout clEditOutframe;
    public final ConstraintLayout clInput;
    public final ConstraintLayout clSave;
    public final ConstraintLayout clShowTextFrame;
    public final EditText etText;
    public final Guideline guidelineTextEmojiView;
    public final ToolbarLayoutBinding incInput;
    public final CustomImageView ivCancel;
    public final CustomImageView ivEmojiNext;
    public final CustomImageView ivEmojiPrev;
    public final CustomImageView ivOk;
    public final CustomImageView ivSend;
    public final View keyboard;
    public final ConstraintLayout relativeLayoutEmoji;
    public final RecyclerView rlInput;
    private final ConstraintLayout rootView;
    public final TextEmojiView textinputEmojiView;
    public final TextView tvFontlimit;

    private ActivityTextInputBinding(ConstraintLayout rootView, Barrier barrierTop, ConstraintLayout clEdit, ConstraintLayout clEditOutframe, ConstraintLayout clInput, ConstraintLayout clSave, ConstraintLayout clShowTextFrame, EditText etText, Guideline guidelineTextEmojiView, ToolbarLayoutBinding incInput, CustomImageView ivCancel, CustomImageView ivEmojiNext, CustomImageView ivEmojiPrev, CustomImageView ivOk, CustomImageView ivSend, View keyboard, ConstraintLayout relativeLayoutEmoji, RecyclerView rlInput, TextEmojiView textinputEmojiView, TextView tvFontlimit) {
        this.rootView = rootView;
        this.barrierTop = barrierTop;
        this.clEdit = clEdit;
        this.clEditOutframe = clEditOutframe;
        this.clInput = clInput;
        this.clSave = clSave;
        this.clShowTextFrame = clShowTextFrame;
        this.etText = etText;
        this.guidelineTextEmojiView = guidelineTextEmojiView;
        this.incInput = incInput;
        this.ivCancel = ivCancel;
        this.ivEmojiNext = ivEmojiNext;
        this.ivEmojiPrev = ivEmojiPrev;
        this.ivOk = ivOk;
        this.ivSend = ivSend;
        this.keyboard = keyboard;
        this.relativeLayoutEmoji = relativeLayoutEmoji;
        this.rlInput = rlInput;
        this.textinputEmojiView = textinputEmojiView;
        this.tvFontlimit = tvFontlimit;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityTextInputBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityTextInputBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_text_input, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityTextInputBinding bind(View rootView) {
        int i = R.id.barrier_top;
        Barrier barrier = (Barrier) ViewBindings.findChildViewById(rootView, R.id.barrier_top);
        if (barrier != null) {
            i = R.id.cl_edit;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_edit);
            if (constraintLayout != null) {
                i = R.id.cl_edit_outframe;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_edit_outframe);
                if (constraintLayout2 != null) {
                    ConstraintLayout constraintLayout3 = (ConstraintLayout) rootView;
                    i = R.id.cl_save;
                    ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_save);
                    if (constraintLayout4 != null) {
                        i = R.id.cl_show_text_frame;
                        ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_show_text_frame);
                        if (constraintLayout5 != null) {
                            i = R.id.et_text;
                            EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.et_text);
                            if (editText != null) {
                                i = R.id.guideline_textEmojiView;
                                Guideline guideline = (Guideline) ViewBindings.findChildViewById(rootView, R.id.guideline_textEmojiView);
                                if (guideline != null) {
                                    i = R.id.inc_input;
                                    View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.inc_input);
                                    if (findChildViewById != null) {
                                        ToolbarLayoutBinding bind = ToolbarLayoutBinding.bind(findChildViewById);
                                        i = R.id.iv_cancel;
                                        CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_cancel);
                                        if (customImageView != null) {
                                            i = R.id.iv_emoji_next;
                                            CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_emoji_next);
                                            if (customImageView2 != null) {
                                                i = R.id.iv_emoji_prev;
                                                CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_emoji_prev);
                                                if (customImageView3 != null) {
                                                    i = R.id.iv_ok;
                                                    CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_ok);
                                                    if (customImageView4 != null) {
                                                        i = R.id.iv_send;
                                                        CustomImageView customImageView5 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_send);
                                                        if (customImageView5 != null) {
                                                            i = R.id.keyboard;
                                                            View findChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.keyboard);
                                                            if (findChildViewById2 != null) {
                                                                i = R.id.relativeLayout_emoji;
                                                                ConstraintLayout constraintLayout6 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.relativeLayout_emoji);
                                                                if (constraintLayout6 != null) {
                                                                    i = R.id.rl_input;
                                                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rl_input);
                                                                    if (recyclerView != null) {
                                                                        i = R.id.textinput_EmojiView;
                                                                        TextEmojiView textEmojiView = (TextEmojiView) ViewBindings.findChildViewById(rootView, R.id.textinput_EmojiView);
                                                                        if (textEmojiView != null) {
                                                                            i = R.id.tv_fontlimit;
                                                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_fontlimit);
                                                                            if (textView != null) {
                                                                                return new ActivityTextInputBinding(constraintLayout3, barrier, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, constraintLayout5, editText, guideline, bind, customImageView, customImageView2, customImageView3, customImageView4, customImageView5, findChildViewById2, constraintLayout6, recyclerView, textEmojiView, textView);
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
