package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ItemTextBgBinding implements ViewBinding {
    public final ImageView ivBg;
    public final ImageView ivColor;
    private final FrameLayout rootView;

    private ItemTextBgBinding(FrameLayout rootView, ImageView ivBg, ImageView ivColor) {
        this.rootView = rootView;
        this.ivBg = ivBg;
        this.ivColor = ivColor;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ItemTextBgBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemTextBgBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_text_bg, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemTextBgBinding bind(View rootView) {
        int i = R.id.iv_bg;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_bg);
        if (imageView != null) {
            i = R.id.iv_color;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_color);
            if (imageView2 != null) {
                return new ItemTextBgBinding((FrameLayout) rootView, imageView, imageView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
