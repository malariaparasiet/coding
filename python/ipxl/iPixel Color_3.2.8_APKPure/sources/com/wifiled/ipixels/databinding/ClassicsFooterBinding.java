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
public final class ClassicsFooterBinding implements ViewBinding {
    public final ImageView notify;
    public final TextView retry;
    private final ConstraintLayout rootView;

    private ClassicsFooterBinding(ConstraintLayout rootView, ImageView notify, TextView retry) {
        this.rootView = rootView;
        this.notify = notify;
        this.retry = retry;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ClassicsFooterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ClassicsFooterBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.classics_footer, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ClassicsFooterBinding bind(View rootView) {
        int i = R.id.notify;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.notify);
        if (imageView != null) {
            i = R.id.retry;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.retry);
            if (textView != null) {
                return new ClassicsFooterBinding((ConstraintLayout) rootView, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
