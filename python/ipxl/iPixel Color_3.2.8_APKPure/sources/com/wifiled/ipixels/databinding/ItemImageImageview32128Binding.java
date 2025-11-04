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
public final class ItemImageImageview32128Binding implements ViewBinding {
    public final ImageView ivPreview;
    public final RelativeLayout rlImageOutsideFrame;
    public final RelativeLayout rlSelectBg;
    private final ConstraintLayout rootView;

    private ItemImageImageview32128Binding(ConstraintLayout rootView, ImageView ivPreview, RelativeLayout rlImageOutsideFrame, RelativeLayout rlSelectBg) {
        this.rootView = rootView;
        this.ivPreview = ivPreview;
        this.rlImageOutsideFrame = rlImageOutsideFrame;
        this.rlSelectBg = rlSelectBg;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemImageImageview32128Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemImageImageview32128Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_image_imageview_32128, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemImageImageview32128Binding bind(View rootView) {
        int i = R.id.iv_preview;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_preview);
        if (imageView != null) {
            i = R.id.rl_image_outside_frame;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_image_outside_frame);
            if (relativeLayout != null) {
                i = R.id.rl_select_bg;
                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_select_bg);
                if (relativeLayout2 != null) {
                    return new ItemImageImageview32128Binding((ConstraintLayout) rootView, imageView, relativeLayout, relativeLayout2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
