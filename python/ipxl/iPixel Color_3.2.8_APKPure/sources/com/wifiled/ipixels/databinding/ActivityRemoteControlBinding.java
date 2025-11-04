package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class ActivityRemoteControlBinding implements ViewBinding {
    public final CustomImageView customImageView;
    public final ConstraintLayout ilColockToobar;
    public final RecyclerView localRecyclerview;
    private final ConstraintLayout rootView;

    private ActivityRemoteControlBinding(ConstraintLayout rootView, CustomImageView customImageView, ConstraintLayout ilColockToobar, RecyclerView localRecyclerview) {
        this.rootView = rootView;
        this.customImageView = customImageView;
        this.ilColockToobar = ilColockToobar;
        this.localRecyclerview = localRecyclerview;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRemoteControlBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityRemoteControlBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_remote_control, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityRemoteControlBinding bind(View rootView) {
        int i = R.id.customImageView;
        CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.customImageView);
        if (customImageView != null) {
            i = R.id.il_colock_toobar;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_colock_toobar);
            if (constraintLayout != null) {
                i = R.id.localRecyclerview;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.localRecyclerview);
                if (recyclerView != null) {
                    return new ActivityRemoteControlBinding((ConstraintLayout) rootView, customImageView, constraintLayout, recyclerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
