package com.wifiled.baselib.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.baselib.R;

/* loaded from: classes2.dex */
public final class ActivityBaseBinding implements ViewBinding {
    public final FrameLayout flContainer;
    public final LinearLayout llBaseRoot;
    private final LinearLayout rootView;
    public final ViewStub vsToolbar;

    private ActivityBaseBinding(LinearLayout linearLayout, FrameLayout frameLayout, LinearLayout linearLayout2, ViewStub viewStub) {
        this.rootView = linearLayout;
        this.flContainer = frameLayout;
        this.llBaseRoot = linearLayout2;
        this.vsToolbar = viewStub;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityBaseBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityBaseBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_base, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityBaseBinding bind(View view) {
        int i = R.id.fl_container;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
        if (frameLayout != null) {
            LinearLayout linearLayout = (LinearLayout) view;
            int i2 = R.id.vs_toolbar;
            ViewStub viewStub = (ViewStub) ViewBindings.findChildViewById(view, i2);
            if (viewStub != null) {
                return new ActivityBaseBinding(linearLayout, frameLayout, linearLayout, viewStub);
            }
            i = i2;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
