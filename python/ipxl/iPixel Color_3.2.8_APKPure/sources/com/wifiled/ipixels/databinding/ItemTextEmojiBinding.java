package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ItemTextEmojiBinding implements ViewBinding {
    public final ImageView ivEmoji;
    private final RelativeLayout rootView;
    public final TextView tvText;

    private ItemTextEmojiBinding(RelativeLayout rootView, ImageView ivEmoji, TextView tvText) {
        this.rootView = rootView;
        this.ivEmoji = ivEmoji;
        this.tvText = tvText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemTextEmojiBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemTextEmojiBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_text_emoji, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemTextEmojiBinding bind(View rootView) {
        int i = R.id.iv_emoji;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_emoji);
        if (imageView != null) {
            i = R.id.tv_text;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text);
            if (textView != null) {
                return new ItemTextEmojiBinding((RelativeLayout) rootView, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
