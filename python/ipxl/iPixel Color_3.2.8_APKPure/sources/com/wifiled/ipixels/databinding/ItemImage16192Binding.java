package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ItemImage16192Binding implements ViewBinding {
    public final ImageView ivPreview;
    public final RelativeLayout rlImageOutsideFrame;
    private final ConstraintLayout rootView;

    private ItemImage16192Binding(ConstraintLayout rootView, ImageView ivPreview, RelativeLayout rlImageOutsideFrame) {
        this.rootView = rootView;
        this.ivPreview = ivPreview;
        this.rlImageOutsideFrame = rlImageOutsideFrame;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemImage16192Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemImage16192Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_image_16192, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemImage16192Binding bind(View rootView) {
        int i = R.id.iv_preview;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_preview);
        if (imageView != null) {
            i = R.id.rl_image_outside_frame;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_image_outside_frame);
            if (relativeLayout != null) {
                return new ItemImage16192Binding((ConstraintLayout) rootView, imageView, relativeLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
