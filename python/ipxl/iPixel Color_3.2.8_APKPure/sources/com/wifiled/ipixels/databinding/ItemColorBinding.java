package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ItemColorBinding implements ViewBinding {
    public final View colorView;
    public final RelativeLayout rlPaintOutsideFrame;
    private final FrameLayout rootView;

    private ItemColorBinding(FrameLayout rootView, View colorView, RelativeLayout rlPaintOutsideFrame) {
        this.rootView = rootView;
        this.colorView = colorView;
        this.rlPaintOutsideFrame = rlPaintOutsideFrame;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ItemColorBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemColorBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_color, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemColorBinding bind(View rootView) {
        int i = R.id.colorView;
        View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.colorView);
        if (findChildViewById != null) {
            i = R.id.rl_paint_outside_frame;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_paint_outside_frame);
            if (relativeLayout != null) {
                return new ItemColorBinding((FrameLayout) rootView, findChildViewById, relativeLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
