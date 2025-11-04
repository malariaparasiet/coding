package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ViewTemplateSix12Binding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final ImageView tem6Iv1;
    public final ImageView tem6Iv2;
    public final ConstraintLayout template;

    private ViewTemplateSix12Binding(ConstraintLayout rootView, ImageView tem6Iv1, ImageView tem6Iv2, ConstraintLayout template) {
        this.rootView = rootView;
        this.tem6Iv1 = tem6Iv1;
        this.tem6Iv2 = tem6Iv2;
        this.template = template;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewTemplateSix12Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ViewTemplateSix12Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.view_template_six_1_2, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ViewTemplateSix12Binding bind(View rootView) {
        int i = R.id.tem6_iv1;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tem6_iv1);
        if (imageView != null) {
            i = R.id.tem6_iv2;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tem6_iv2);
            if (imageView2 != null) {
                i = R.id.template;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.template);
                if (constraintLayout != null) {
                    return new ViewTemplateSix12Binding((ConstraintLayout) rootView, imageView, imageView2, constraintLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
