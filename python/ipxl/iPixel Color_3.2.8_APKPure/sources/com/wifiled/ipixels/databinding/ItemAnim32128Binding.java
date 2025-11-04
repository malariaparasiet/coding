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
public final class ItemAnim32128Binding implements ViewBinding {
    public final ImageView ivAnimPreview;
    public final RelativeLayout rl128bg;
    public final RelativeLayout rlImageOutsideFrame;
    private final ConstraintLayout rootView;
    public final TextView tvIndex;

    private ItemAnim32128Binding(ConstraintLayout rootView, ImageView ivAnimPreview, RelativeLayout rl128bg, RelativeLayout rlImageOutsideFrame, TextView tvIndex) {
        this.rootView = rootView;
        this.ivAnimPreview = ivAnimPreview;
        this.rl128bg = rl128bg;
        this.rlImageOutsideFrame = rlImageOutsideFrame;
        this.tvIndex = tvIndex;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemAnim32128Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemAnim32128Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_anim_32128, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemAnim32128Binding bind(View rootView) {
        int i = R.id.iv_anim_preview;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_anim_preview);
        if (imageView != null) {
            i = R.id.rl_128bg;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_128bg);
            if (relativeLayout != null) {
                i = R.id.rl_image_outside_frame;
                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_image_outside_frame);
                if (relativeLayout2 != null) {
                    i = R.id.tv_index;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_index);
                    if (textView != null) {
                        return new ItemAnim32128Binding((ConstraintLayout) rootView, imageView, relativeLayout, relativeLayout2, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
