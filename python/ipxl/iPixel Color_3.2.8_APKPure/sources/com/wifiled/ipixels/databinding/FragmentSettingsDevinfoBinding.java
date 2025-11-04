package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class FragmentSettingsDevinfoBinding implements ViewBinding {
    public final Guideline glDevinfoHor;
    private final ConstraintLayout rootView;
    public final TextView setDevinfoDevtype;
    public final TextView setDevinfoMcuver;
    public final TextView setDevinfoWifiver;

    private FragmentSettingsDevinfoBinding(ConstraintLayout rootView, Guideline glDevinfoHor, TextView setDevinfoDevtype, TextView setDevinfoMcuver, TextView setDevinfoWifiver) {
        this.rootView = rootView;
        this.glDevinfoHor = glDevinfoHor;
        this.setDevinfoDevtype = setDevinfoDevtype;
        this.setDevinfoMcuver = setDevinfoMcuver;
        this.setDevinfoWifiver = setDevinfoWifiver;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSettingsDevinfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentSettingsDevinfoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_settings_devinfo, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentSettingsDevinfoBinding bind(View rootView) {
        int i = R.id.gl_devinfo_hor;
        Guideline guideline = (Guideline) ViewBindings.findChildViewById(rootView, R.id.gl_devinfo_hor);
        if (guideline != null) {
            i = R.id.set_devinfo_devtype;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.set_devinfo_devtype);
            if (textView != null) {
                i = R.id.set_devinfo_mcuver;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.set_devinfo_mcuver);
                if (textView2 != null) {
                    i = R.id.set_devinfo_wifiver;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.set_devinfo_wifiver);
                    if (textView3 != null) {
                        return new FragmentSettingsDevinfoBinding((ConstraintLayout) rootView, guideline, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
