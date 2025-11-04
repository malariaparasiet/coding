package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.baselib.widget.CheckableLayout;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ItemLanguageSingleChoiceBinding implements ViewBinding {
    public final ImageView ivState;
    private final CheckableLayout rootView;
    public final TextView tvLanguageTitle;

    private ItemLanguageSingleChoiceBinding(CheckableLayout rootView, ImageView ivState, TextView tvLanguageTitle) {
        this.rootView = rootView;
        this.ivState = ivState;
        this.tvLanguageTitle = tvLanguageTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CheckableLayout getRoot() {
        return this.rootView;
    }

    public static ItemLanguageSingleChoiceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemLanguageSingleChoiceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_language_single_choice, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemLanguageSingleChoiceBinding bind(View rootView) {
        int i = R.id.iv_state;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_state);
        if (imageView != null) {
            i = R.id.tv_language_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_language_title);
            if (textView != null) {
                return new ItemLanguageSingleChoiceBinding((CheckableLayout) rootView, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
