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
public final class ItemImage32128Binding implements ViewBinding {
    public final ImageView ivPreview;
    public final RelativeLayout rl128bg;
    public final RelativeLayout rlImageOutsideFrame;
    public final RelativeLayout rlSelect;
    private final ConstraintLayout rootView;

    private ItemImage32128Binding(ConstraintLayout rootView, ImageView ivPreview, RelativeLayout rl128bg, RelativeLayout rlImageOutsideFrame, RelativeLayout rlSelect) {
        this.rootView = rootView;
        this.ivPreview = ivPreview;
        this.rl128bg = rl128bg;
        this.rlImageOutsideFrame = rlImageOutsideFrame;
        this.rlSelect = rlSelect;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemImage32128Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemImage32128Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_image_32128, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemImage32128Binding bind(View rootView) {
        int i = R.id.iv_preview;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_preview);
        if (imageView != null) {
            i = R.id.rl_128bg;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_128bg);
            if (relativeLayout != null) {
                i = R.id.rl_image_outside_frame;
                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_image_outside_frame);
                if (relativeLayout2 != null) {
                    i = R.id.rl_select;
                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_select);
                    if (relativeLayout3 != null) {
                        return new ItemImage32128Binding((ConstraintLayout) rootView, imageView, relativeLayout, relativeLayout2, relativeLayout3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
