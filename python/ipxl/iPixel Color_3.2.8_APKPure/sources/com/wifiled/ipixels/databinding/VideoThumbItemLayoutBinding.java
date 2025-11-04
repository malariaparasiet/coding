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
public final class VideoThumbItemLayoutBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final ImageView thumb;

    private VideoThumbItemLayoutBinding(LinearLayout rootView, ImageView thumb) {
        this.rootView = rootView;
        this.thumb = thumb;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static VideoThumbItemLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static VideoThumbItemLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.video_thumb_item_layout, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static VideoThumbItemLayoutBinding bind(View rootView) {
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.thumb);
        if (imageView != null) {
            return new VideoThumbItemLayoutBinding((LinearLayout) rootView, imageView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.thumb)));
    }
}
