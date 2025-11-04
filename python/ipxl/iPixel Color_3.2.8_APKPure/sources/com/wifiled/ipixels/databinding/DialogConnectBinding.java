package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class DialogConnectBinding implements ViewBinding {
    public final ConstraintLayout clRefresh;
    public final ConstraintLayout constraintLayout;
    public final ImageButton ivRefresh;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;

    private DialogConnectBinding(ConstraintLayout rootView, ConstraintLayout clRefresh, ConstraintLayout constraintLayout, ImageButton ivRefresh, RecyclerView recyclerView) {
        this.rootView = rootView;
        this.clRefresh = clRefresh;
        this.constraintLayout = constraintLayout;
        this.ivRefresh = ivRefresh;
        this.recyclerView = recyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogConnectBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogConnectBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.dialog_connect, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogConnectBinding bind(View rootView) {
        int i = R.id.cl_refresh;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_refresh);
        if (constraintLayout != null) {
            i = R.id.constraintLayout;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.constraintLayout);
            if (constraintLayout2 != null) {
                i = R.id.iv_refresh;
                ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(rootView, R.id.iv_refresh);
                if (imageButton != null) {
                    i = R.id.recyclerView;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerView);
                    if (recyclerView != null) {
                        return new DialogConnectBinding((ConstraintLayout) rootView, constraintLayout, constraintLayout2, imageButton, recyclerView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
