package com.wifiled.baselib.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.wifiled.baselib.R;
import com.wifiled.baselib.uicode.statuslayout.DefaultStatusLayout;

/* loaded from: classes2.dex */
public final class StatusLayoutDefaultLoadErrorBinding implements ViewBinding {
    private final DefaultStatusLayout rootView;
    public final DefaultStatusLayout statusErrorLl;

    private StatusLayoutDefaultLoadErrorBinding(DefaultStatusLayout defaultStatusLayout, DefaultStatusLayout defaultStatusLayout2) {
        this.rootView = defaultStatusLayout;
        this.statusErrorLl = defaultStatusLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public DefaultStatusLayout getRoot() {
        return this.rootView;
    }

    public static StatusLayoutDefaultLoadErrorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static StatusLayoutDefaultLoadErrorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.status_layout_default_load_error, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static StatusLayoutDefaultLoadErrorBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        DefaultStatusLayout defaultStatusLayout = (DefaultStatusLayout) view;
        return new StatusLayoutDefaultLoadErrorBinding(defaultStatusLayout, defaultStatusLayout);
    }
}
