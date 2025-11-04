package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ActivityGalleryBinding implements ViewBinding {
    public final ConstraintLayout ilGalleryToobar;
    public final ImageView ivBottom;
    public final ImageView ivGalleryEyesChange;
    public final ImageView ivGallerySend;
    public final ImageView ivNavBg;
    public final FrameLayout navigationContainer;
    private final ConstraintLayout rootView;

    private ActivityGalleryBinding(ConstraintLayout rootView, ConstraintLayout ilGalleryToobar, ImageView ivBottom, ImageView ivGalleryEyesChange, ImageView ivGallerySend, ImageView ivNavBg, FrameLayout navigationContainer) {
        this.rootView = rootView;
        this.ilGalleryToobar = ilGalleryToobar;
        this.ivBottom = ivBottom;
        this.ivGalleryEyesChange = ivGalleryEyesChange;
        this.ivGallerySend = ivGallerySend;
        this.ivNavBg = ivNavBg;
        this.navigationContainer = navigationContainer;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityGalleryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityGalleryBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_gallery, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityGalleryBinding bind(View rootView) {
        int i = R.id.il_gallery_toobar;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_gallery_toobar);
        if (constraintLayout != null) {
            i = R.id.iv_bottom;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_bottom);
            if (imageView != null) {
                i = R.id.iv_gallery_eyes_change;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_gallery_eyes_change);
                if (imageView2 != null) {
                    i = R.id.iv_gallery_send;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_gallery_send);
                    if (imageView3 != null) {
                        i = R.id.iv_nav_bg;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_nav_bg);
                        if (imageView4 != null) {
                            i = R.id.navigation_container;
                            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.navigation_container);
                            if (frameLayout != null) {
                                return new ActivityGalleryBinding((ConstraintLayout) rootView, constraintLayout, imageView, imageView2, imageView3, imageView4, frameLayout);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
