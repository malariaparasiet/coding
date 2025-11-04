package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class DialogDiyBinding implements ViewBinding {
    public final ImageView ivDiyAnim;
    public final ImageView ivDiyImage;
    public final ImageView ivDiyProjection;
    public final ImageView ivDiyTextImage;
    private final LinearLayout rootView;

    private DialogDiyBinding(LinearLayout rootView, ImageView ivDiyAnim, ImageView ivDiyImage, ImageView ivDiyProjection, ImageView ivDiyTextImage) {
        this.rootView = rootView;
        this.ivDiyAnim = ivDiyAnim;
        this.ivDiyImage = ivDiyImage;
        this.ivDiyProjection = ivDiyProjection;
        this.ivDiyTextImage = ivDiyTextImage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogDiyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogDiyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.dialog_diy, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogDiyBinding bind(View rootView) {
        int i = R.id.iv_diy_anim;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_diy_anim);
        if (imageView != null) {
            i = R.id.iv_diy_image;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_diy_image);
            if (imageView2 != null) {
                i = R.id.iv_diy_projection;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_diy_projection);
                if (imageView3 != null) {
                    i = R.id.iv_diy_text_image;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_diy_text_image);
                    if (imageView4 != null) {
                        return new DialogDiyBinding((LinearLayout) rootView, imageView, imageView2, imageView3, imageView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
