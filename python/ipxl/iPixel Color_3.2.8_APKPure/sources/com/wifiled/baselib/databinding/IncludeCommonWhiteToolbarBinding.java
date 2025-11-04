package com.wifiled.baselib.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.wifiled.baselib.R;
import com.wifiled.baselib.uicode.view.ToolBarView;

/* loaded from: classes2.dex */
public final class IncludeCommonWhiteToolbarBinding implements ViewBinding {
    private final ToolBarView rootView;
    public final ToolBarView toolBarView;

    private IncludeCommonWhiteToolbarBinding(ToolBarView toolBarView, ToolBarView toolBarView2) {
        this.rootView = toolBarView;
        this.toolBarView = toolBarView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ToolBarView getRoot() {
        return this.rootView;
    }

    public static IncludeCommonWhiteToolbarBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static IncludeCommonWhiteToolbarBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.include_common_white_toolbar, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static IncludeCommonWhiteToolbarBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        ToolBarView toolBarView = (ToolBarView) view;
        return new IncludeCommonWhiteToolbarBinding(toolBarView, toolBarView);
    }
}
