package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.customview.IOSLoadingView;

/* loaded from: classes3.dex */
public final class DialogLoadingBinding implements ViewBinding {
    public final ConstraintLayout dialogLoadingView;
    public final IOSLoadingView progBarLoad;
    private final ConstraintLayout rootView;
    public final TextView tipTextView;

    private DialogLoadingBinding(ConstraintLayout rootView, ConstraintLayout dialogLoadingView, IOSLoadingView progBarLoad, TextView tipTextView) {
        this.rootView = rootView;
        this.dialogLoadingView = dialogLoadingView;
        this.progBarLoad = progBarLoad;
        this.tipTextView = tipTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogLoadingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogLoadingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.dialog_loading, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogLoadingBinding bind(View rootView) {
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
        int i = R.id.progBar_load;
        IOSLoadingView iOSLoadingView = (IOSLoadingView) ViewBindings.findChildViewById(rootView, R.id.progBar_load);
        if (iOSLoadingView != null) {
            i = R.id.tipTextView;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tipTextView);
            if (textView != null) {
                return new DialogLoadingBinding(constraintLayout, constraintLayout, iOSLoadingView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
