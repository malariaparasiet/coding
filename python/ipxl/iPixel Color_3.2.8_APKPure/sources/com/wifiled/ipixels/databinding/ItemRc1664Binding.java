package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ItemRc1664Binding implements ViewBinding {
    public final ImageView ivAnimPreview;
    public final ImageView ivAnimSelect;
    public final RelativeLayout rlImageOutsideFrame;
    private final ConstraintLayout rootView;
    public final TextView tvIndex;

    private ItemRc1664Binding(ConstraintLayout rootView, ImageView ivAnimPreview, ImageView ivAnimSelect, RelativeLayout rlImageOutsideFrame, TextView tvIndex) {
        this.rootView = rootView;
        this.ivAnimPreview = ivAnimPreview;
        this.ivAnimSelect = ivAnimSelect;
        this.rlImageOutsideFrame = rlImageOutsideFrame;
        this.tvIndex = tvIndex;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemRc1664Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemRc1664Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_rc_1664, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemRc1664Binding bind(View rootView) {
        int i = R.id.iv_anim_preview;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_anim_preview);
        if (imageView != null) {
            i = R.id.iv_anim_select;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_anim_select);
            if (imageView2 != null) {
                i = R.id.rl_image_outside_frame;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_image_outside_frame);
                if (relativeLayout != null) {
                    i = R.id.tv_index;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_index);
                    if (textView != null) {
                        return new ItemRc1664Binding((ConstraintLayout) rootView, imageView, imageView2, relativeLayout, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
