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
public final class ItemRhyImageBinding implements ViewBinding {
    public final ImageView ivRhyImage;
    public final RelativeLayout rootView;
    private final RelativeLayout rootView_;

    private ItemRhyImageBinding(RelativeLayout rootView_, ImageView ivRhyImage, RelativeLayout rootView) {
        this.rootView_ = rootView_;
        this.ivRhyImage = ivRhyImage;
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView_;
    }

    public static ItemRhyImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemRhyImageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_rhy_image, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemRhyImageBinding bind(View rootView) {
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_rhy_image);
        if (imageView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) rootView;
            return new ItemRhyImageBinding(relativeLayout, imageView, relativeLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.iv_rhy_image)));
    }
}
