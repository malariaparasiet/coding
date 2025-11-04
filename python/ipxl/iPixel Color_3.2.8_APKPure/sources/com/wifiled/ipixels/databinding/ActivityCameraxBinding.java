package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.camera.view.PreviewView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ActivityCameraxBinding implements ViewBinding {
    public final ImageView ivPreview;
    private final ConstraintLayout rootView;
    public final PreviewView viewFinder;

    private ActivityCameraxBinding(ConstraintLayout rootView, ImageView ivPreview, PreviewView viewFinder) {
        this.rootView = rootView;
        this.ivPreview = ivPreview;
        this.viewFinder = viewFinder;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCameraxBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityCameraxBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_camerax, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityCameraxBinding bind(View rootView) {
        int i = R.id.iv_preview;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_preview);
        if (imageView != null) {
            i = R.id.viewFinder;
            PreviewView previewView = (PreviewView) ViewBindings.findChildViewById(rootView, R.id.viewFinder);
            if (previewView != null) {
                return new ActivityCameraxBinding((ConstraintLayout) rootView, imageView, previewView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
