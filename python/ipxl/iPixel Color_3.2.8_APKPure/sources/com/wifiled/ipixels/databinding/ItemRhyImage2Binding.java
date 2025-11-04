package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ItemRhyImage2Binding implements ViewBinding {
    public final ImageView ivRhyImage;
    public final RelativeLayout rootView;
    private final RelativeLayout rootView_;

    private ItemRhyImage2Binding(RelativeLayout rootView_, ImageView ivRhyImage, RelativeLayout rootView) {
        this.rootView_ = rootView_;
        this.ivRhyImage = ivRhyImage;
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView_;
    }

    public static ItemRhyImage2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemRhyImage2Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_rhy_image2, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemRhyImage2Binding bind(View rootView) {
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_rhy_image);
        if (imageView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) rootView;
            return new ItemRhyImage2Binding(relativeLayout, imageView, relativeLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.iv_rhy_image)));
    }
}
