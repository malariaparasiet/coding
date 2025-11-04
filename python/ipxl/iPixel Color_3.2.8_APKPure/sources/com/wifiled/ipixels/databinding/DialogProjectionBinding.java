package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class DialogProjectionBinding implements ViewBinding {
    public final ImageView ivCamera;
    public final ImageView ivLocal;
    private final LinearLayout rootView;

    private DialogProjectionBinding(LinearLayout rootView, ImageView ivCamera, ImageView ivLocal) {
        this.rootView = rootView;
        this.ivCamera = ivCamera;
        this.ivLocal = ivLocal;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogProjectionBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogProjectionBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.dialog_projection, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogProjectionBinding bind(View rootView) {
        int i = R.id.iv_camera;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_camera);
        if (imageView != null) {
            i = R.id.iv_local;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_local);
            if (imageView2 != null) {
                return new DialogProjectionBinding((LinearLayout) rootView, imageView, imageView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
