package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ItemImageImageviewBinding implements ViewBinding {
    public final ImageView ivPreview;
    public final RelativeLayout rlImageOutsideFrame;
    private final RelativeLayout rootView;
    public final TextView tvIndex;

    private ItemImageImageviewBinding(RelativeLayout rootView, ImageView ivPreview, RelativeLayout rlImageOutsideFrame, TextView tvIndex) {
        this.rootView = rootView;
        this.ivPreview = ivPreview;
        this.rlImageOutsideFrame = rlImageOutsideFrame;
        this.tvIndex = tvIndex;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemImageImageviewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemImageImageviewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_image_imageview, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemImageImageviewBinding bind(View rootView) {
        int i = R.id.iv_preview;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_preview);
        if (imageView != null) {
            i = R.id.rl_image_outside_frame;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_image_outside_frame);
            if (relativeLayout != null) {
                i = R.id.tv_index;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_index);
                if (textView != null) {
                    return new ItemImageImageviewBinding((RelativeLayout) rootView, imageView, relativeLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
