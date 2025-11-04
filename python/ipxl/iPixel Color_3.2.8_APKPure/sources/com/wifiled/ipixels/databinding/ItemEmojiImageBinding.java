package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ItemEmojiImageBinding implements ViewBinding {
    public final ImageView ivEmoji;
    private final ImageView rootView;

    private ItemEmojiImageBinding(ImageView rootView, ImageView ivEmoji) {
        this.rootView = rootView;
        this.ivEmoji = ivEmoji;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ImageView getRoot() {
        return this.rootView;
    }

    public static ItemEmojiImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemEmojiImageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_emoji_image, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemEmojiImageBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        ImageView imageView = (ImageView) rootView;
        return new ItemEmojiImageBinding(imageView, imageView);
    }
}
