package com.wifiled.baselib.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.baselib.R;

/* loaded from: classes2.dex */
public final class ToolbarLayoutBinding implements ViewBinding {
    public final LinearLayout appbar;
    private final LinearLayout rootView;
    public final Toolbar toolbar;
    public final ImageView toolbarSub;
    public final TextView toolbarTitle;

    private ToolbarLayoutBinding(LinearLayout linearLayout, LinearLayout linearLayout2, Toolbar toolbar, ImageView imageView, TextView textView) {
        this.rootView = linearLayout;
        this.appbar = linearLayout2;
        this.toolbar = toolbar;
        this.toolbarSub = imageView;
        this.toolbarTitle = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ToolbarLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ToolbarLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.toolbar_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ToolbarLayoutBinding bind(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        int i = R.id.toolbar;
        Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, i);
        if (toolbar != null) {
            i = R.id.toolbar_sub;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R.id.toolbar_title;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    return new ToolbarLayoutBinding(linearLayout, linearLayout, toolbar, imageView, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
