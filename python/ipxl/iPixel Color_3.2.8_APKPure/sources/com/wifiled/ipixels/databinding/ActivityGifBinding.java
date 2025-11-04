package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ActivityGifBinding implements ViewBinding {
    public final Button btnAdd;
    public final Button btnMix;
    public final Button btnSelect;
    public final RecyclerView recyclerview;
    private final RelativeLayout rootView;

    private ActivityGifBinding(RelativeLayout rootView, Button btnAdd, Button btnMix, Button btnSelect, RecyclerView recyclerview) {
        this.rootView = rootView;
        this.btnAdd = btnAdd;
        this.btnMix = btnMix;
        this.btnSelect = btnSelect;
        this.recyclerview = recyclerview;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityGifBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityGifBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_gif, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityGifBinding bind(View rootView) {
        int i = R.id.btn_add;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_add);
        if (button != null) {
            i = R.id.btn_mix;
            Button button2 = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_mix);
            if (button2 != null) {
                i = R.id.btn_select;
                Button button3 = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_select);
                if (button3 != null) {
                    i = R.id.recyclerview;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerview);
                    if (recyclerView != null) {
                        return new ActivityGifBinding((RelativeLayout) rootView, button, button2, button3, recyclerView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
