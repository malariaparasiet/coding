package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class FragmentLoadBinding implements ViewBinding {
    public final RecyclerView localRecyclerview;
    public final ImageView nullErr;
    public final SmartRefreshLayout refreshLayout;
    private final FrameLayout rootView;

    private FragmentLoadBinding(FrameLayout rootView, RecyclerView localRecyclerview, ImageView nullErr, SmartRefreshLayout refreshLayout) {
        this.rootView = rootView;
        this.localRecyclerview = localRecyclerview;
        this.nullErr = nullErr;
        this.refreshLayout = refreshLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentLoadBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentLoadBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_load, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentLoadBinding bind(View rootView) {
        int i = R.id.localRecyclerview;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.localRecyclerview);
        if (recyclerView != null) {
            i = R.id.null_err;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.null_err);
            if (imageView != null) {
                i = R.id.refreshLayout;
                SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, R.id.refreshLayout);
                if (smartRefreshLayout != null) {
                    return new FragmentLoadBinding((FrameLayout) rootView, recyclerView, imageView, smartRefreshLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
