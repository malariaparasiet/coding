package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ItemConnectBinding implements ViewBinding {
    public final ImageView icEdit;
    private final ConstraintLayout rootView;
    public final TextView tvName;
    public final ImageView tvState;

    private ItemConnectBinding(ConstraintLayout rootView, ImageView icEdit, TextView tvName, ImageView tvState) {
        this.rootView = rootView;
        this.icEdit = icEdit;
        this.tvName = tvName;
        this.tvState = tvState;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemConnectBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemConnectBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_connect, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemConnectBinding bind(View rootView) {
        int i = R.id.ic_edit;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.ic_edit);
        if (imageView != null) {
            i = R.id.tv_name;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_name);
            if (textView != null) {
                i = R.id.tv_state;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tv_state);
                if (imageView2 != null) {
                    return new ItemConnectBinding((ConstraintLayout) rootView, imageView, textView, imageView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
