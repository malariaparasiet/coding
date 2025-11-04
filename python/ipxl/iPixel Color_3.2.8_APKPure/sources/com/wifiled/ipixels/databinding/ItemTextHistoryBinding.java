package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.TextEmojiView;

/* loaded from: classes3.dex */
public final class ItemTextHistoryBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextEmojiView textrecEmojiView;

    private ItemTextHistoryBinding(ConstraintLayout rootView, TextEmojiView textrecEmojiView) {
        this.rootView = rootView;
        this.textrecEmojiView = textrecEmojiView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemTextHistoryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemTextHistoryBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_text_history, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemTextHistoryBinding bind(View rootView) {
        TextEmojiView textEmojiView = (TextEmojiView) ViewBindings.findChildViewById(rootView, R.id.textrec_EmojiView);
        if (textEmojiView != null) {
            return new ItemTextHistoryBinding((ConstraintLayout) rootView, textEmojiView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.textrec_EmojiView)));
    }
}
