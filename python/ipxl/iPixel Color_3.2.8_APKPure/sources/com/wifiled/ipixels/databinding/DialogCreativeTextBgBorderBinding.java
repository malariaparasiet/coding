package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class DialogCreativeTextBgBorderBinding implements ViewBinding {
    public final RecyclerView borderList;
    public final TextView dialogTitle;
    public final ImageView ivCancel;
    public final ImageView ivConfirm;
    private final ConstraintLayout rootView;

    private DialogCreativeTextBgBorderBinding(ConstraintLayout rootView, RecyclerView borderList, TextView dialogTitle, ImageView ivCancel, ImageView ivConfirm) {
        this.rootView = rootView;
        this.borderList = borderList;
        this.dialogTitle = dialogTitle;
        this.ivCancel = ivCancel;
        this.ivConfirm = ivConfirm;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogCreativeTextBgBorderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogCreativeTextBgBorderBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.dialog_creative_text_bg_border, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogCreativeTextBgBorderBinding bind(View rootView) {
        int i = R.id.border_list;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.border_list);
        if (recyclerView != null) {
            i = R.id.dialog_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.dialog_title);
            if (textView != null) {
                i = R.id.iv_cancel;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_cancel);
                if (imageView != null) {
                    i = R.id.iv_confirm;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_confirm);
                    if (imageView2 != null) {
                        return new DialogCreativeTextBgBorderBinding((ConstraintLayout) rootView, recyclerView, textView, imageView, imageView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
