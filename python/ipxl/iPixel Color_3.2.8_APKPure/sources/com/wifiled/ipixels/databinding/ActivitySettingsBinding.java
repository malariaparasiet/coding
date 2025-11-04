package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ActivitySettingsBinding implements ViewBinding {
    public final RelativeLayout ilSettingsToolbar;
    private final ConstraintLayout rootView;
    public final FrameLayout setNavigationContainer;

    private ActivitySettingsBinding(ConstraintLayout rootView, RelativeLayout ilSettingsToolbar, FrameLayout setNavigationContainer) {
        this.rootView = rootView;
        this.ilSettingsToolbar = ilSettingsToolbar;
        this.setNavigationContainer = setNavigationContainer;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySettingsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySettingsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_settings, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivitySettingsBinding bind(View rootView) {
        int i = R.id.il_settings_toolbar;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.il_settings_toolbar);
        if (relativeLayout != null) {
            i = R.id.set_navigation_container;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.set_navigation_container);
            if (frameLayout != null) {
                return new ActivitySettingsBinding((ConstraintLayout) rootView, relativeLayout, frameLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
