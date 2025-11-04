package com.wifiled.baselib.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.baselib.R;

/* loaded from: classes2.dex */
public final class ProgressDialogBinding implements ViewBinding {
    public final TextView message;
    public final ProgressBar progress;
    private final FrameLayout rootView;

    private ProgressDialogBinding(FrameLayout frameLayout, TextView textView, ProgressBar progressBar) {
        this.rootView = frameLayout;
        this.message = textView;
        this.progress = progressBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ProgressDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ProgressDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.progress_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ProgressDialogBinding bind(View view) {
        int i = R.id.message;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = android.R.id.progress;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, android.R.id.progress);
            if (progressBar != null) {
                return new ProgressDialogBinding((FrameLayout) view, textView, progressBar);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
