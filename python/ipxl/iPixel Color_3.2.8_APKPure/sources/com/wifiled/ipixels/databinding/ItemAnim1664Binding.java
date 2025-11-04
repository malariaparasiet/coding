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
public final class ItemAnim1664Binding implements ViewBinding {
    public final ImageView ivAnimPreview;
    public final RelativeLayout rlImageOutsideFrame;
    private final ConstraintLayout rootView;
    public final TextView tvIndex;

    private ItemAnim1664Binding(ConstraintLayout rootView, ImageView ivAnimPreview, RelativeLayout rlImageOutsideFrame, TextView tvIndex) {
        this.rootView = rootView;
        this.ivAnimPreview = ivAnimPreview;
        this.rlImageOutsideFrame = rlImageOutsideFrame;
        this.tvIndex = tvIndex;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemAnim1664Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemAnim1664Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_anim_1664, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemAnim1664Binding bind(View rootView) {
        int i = R.id.iv_anim_preview;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_anim_preview);
        if (imageView != null) {
            i = R.id.rl_image_outside_frame;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_image_outside_frame);
            if (relativeLayout != null) {
                i = R.id.tv_index;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_index);
                if (textView != null) {
                    return new ItemAnim1664Binding((ConstraintLayout) rootView, imageView, relativeLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
