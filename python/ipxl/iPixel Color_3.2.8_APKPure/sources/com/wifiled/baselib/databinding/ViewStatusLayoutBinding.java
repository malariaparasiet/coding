package com.wifiled.baselib.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.baselib.R;
import com.wifiled.baselib.uicode.roundview.RoundTextView;

/* loaded from: classes2.dex */
public final class ViewStatusLayoutBinding implements ViewBinding {
    public final AppCompatImageView ivStatusImage;
    public final LinearLayout llBaseRoot;
    private final LinearLayout rootView;
    public final RoundTextView rtvRefresh;
    public final AppCompatTextView tvStatusText;

    private ViewStatusLayoutBinding(LinearLayout linearLayout, AppCompatImageView appCompatImageView, LinearLayout linearLayout2, RoundTextView roundTextView, AppCompatTextView appCompatTextView) {
        this.rootView = linearLayout;
        this.ivStatusImage = appCompatImageView;
        this.llBaseRoot = linearLayout2;
        this.rtvRefresh = roundTextView;
        this.tvStatusText = appCompatTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ViewStatusLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewStatusLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.view_status_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ViewStatusLayoutBinding bind(View view) {
        int i = R.id.iv_status_image;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            LinearLayout linearLayout = (LinearLayout) view;
            i = R.id.rtv_refresh;
            RoundTextView roundTextView = (RoundTextView) ViewBindings.findChildViewById(view, i);
            if (roundTextView != null) {
                i = R.id.tv_status_text;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                if (appCompatTextView != null) {
                    return new ViewStatusLayoutBinding(linearLayout, appCompatImageView, linearLayout, roundTextView, appCompatTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
