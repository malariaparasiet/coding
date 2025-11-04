package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class ActivityImageTextBinding implements ViewBinding {
    public final EditText etInputName;
    public final ConstraintLayout ilTextToolbar;
    public final RecyclerView imgRecycle;
    public final CustomImageView ivBack;
    public final ImageView ivCancel;
    public final CustomImageView ivMiddle;
    public final ImageView ivOk;
    public final CustomImageView ivRight;
    public final CustomImageView ivRightSubtitle;
    public final RecyclerView rcvImage;
    private final ConstraintLayout rootView;
    public final TextView tvName;
    public final TextView tvRight;
    public final TextView tvThemeColor;
    public final TextView tvTitle;

    private ActivityImageTextBinding(ConstraintLayout rootView, EditText etInputName, ConstraintLayout ilTextToolbar, RecyclerView imgRecycle, CustomImageView ivBack, ImageView ivCancel, CustomImageView ivMiddle, ImageView ivOk, CustomImageView ivRight, CustomImageView ivRightSubtitle, RecyclerView rcvImage, TextView tvName, TextView tvRight, TextView tvThemeColor, TextView tvTitle) {
        this.rootView = rootView;
        this.etInputName = etInputName;
        this.ilTextToolbar = ilTextToolbar;
        this.imgRecycle = imgRecycle;
        this.ivBack = ivBack;
        this.ivCancel = ivCancel;
        this.ivMiddle = ivMiddle;
        this.ivOk = ivOk;
        this.ivRight = ivRight;
        this.ivRightSubtitle = ivRightSubtitle;
        this.rcvImage = rcvImage;
        this.tvName = tvName;
        this.tvRight = tvRight;
        this.tvThemeColor = tvThemeColor;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityImageTextBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityImageTextBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_image_text, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityImageTextBinding bind(View rootView) {
        int i = R.id.et_input_name;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.et_input_name);
        if (editText != null) {
            i = R.id.il_text_toolbar;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_text_toolbar);
            if (constraintLayout != null) {
                i = R.id.img_recycle;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.img_recycle);
                if (recyclerView != null) {
                    i = R.id.iv_back;
                    CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_back);
                    if (customImageView != null) {
                        i = R.id.iv_cancel;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_cancel);
                        if (imageView != null) {
                            i = R.id.iv_middle;
                            CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_middle);
                            if (customImageView2 != null) {
                                i = R.id.iv_ok;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_ok);
                                if (imageView2 != null) {
                                    i = R.id.iv_right;
                                    CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right);
                                    if (customImageView3 != null) {
                                        i = R.id.iv_right_subtitle;
                                        CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right_subtitle);
                                        if (customImageView4 != null) {
                                            i = R.id.rcv_image;
                                            RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_image);
                                            if (recyclerView2 != null) {
                                                i = R.id.tv_name;
                                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_name);
                                                if (textView != null) {
                                                    i = R.id.tv_right;
                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_right);
                                                    if (textView2 != null) {
                                                        i = R.id.tv_theme_color;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_theme_color);
                                                        if (textView3 != null) {
                                                            i = R.id.tv_title;
                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                                                            if (textView4 != null) {
                                                                return new ActivityImageTextBinding((ConstraintLayout) rootView, editText, constraintLayout, recyclerView, customImageView, imageView, customImageView2, imageView2, customImageView3, customImageView4, recyclerView2, textView, textView2, textView3, textView4);
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
