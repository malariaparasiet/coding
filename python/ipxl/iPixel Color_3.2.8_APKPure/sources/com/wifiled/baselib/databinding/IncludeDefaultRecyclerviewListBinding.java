package com.wifiled.baselib.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.wifiled.baselib.R;

/* loaded from: classes2.dex */
public final class IncludeDefaultRecyclerviewListBinding implements ViewBinding {
    public final RecyclerView recyclerView;
    public final SmartRefreshLayout refreshLayout;
    private final SmartRefreshLayout rootView;

    private IncludeDefaultRecyclerviewListBinding(SmartRefreshLayout smartRefreshLayout, RecyclerView recyclerView, SmartRefreshLayout smartRefreshLayout2) {
        this.rootView = smartRefreshLayout;
        this.recyclerView = recyclerView;
        this.refreshLayout = smartRefreshLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public SmartRefreshLayout getRoot() {
        return this.rootView;
    }

    public static IncludeDefaultRecyclerviewListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static IncludeDefaultRecyclerviewListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.include_default_recyclerview_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static IncludeDefaultRecyclerviewListBinding bind(View view) {
        int i = R.id.recyclerView;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
        if (recyclerView != null) {
            SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) view;
            return new IncludeDefaultRecyclerviewListBinding(smartRefreshLayout, recyclerView, smartRefreshLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
