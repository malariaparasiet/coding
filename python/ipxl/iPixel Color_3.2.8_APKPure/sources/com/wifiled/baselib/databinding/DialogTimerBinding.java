package com.wifiled.baselib.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.baselib.R;
import com.wifiled.baselib.widget.loopview.LoopView;

/* loaded from: classes2.dex */
public final class DialogTimerBinding implements ViewBinding {
    public final LoopView loopView;
    private final LinearLayout rootView;

    private DialogTimerBinding(LinearLayout linearLayout, LoopView loopView) {
        this.rootView = linearLayout;
        this.loopView = loopView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogTimerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogTimerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_timer, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogTimerBinding bind(View view) {
        int i = R.id.loopView;
        LoopView loopView = (LoopView) ViewBindings.findChildViewById(view, i);
        if (loopView != null) {
            return new DialogTimerBinding((LinearLayout) view, loopView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
