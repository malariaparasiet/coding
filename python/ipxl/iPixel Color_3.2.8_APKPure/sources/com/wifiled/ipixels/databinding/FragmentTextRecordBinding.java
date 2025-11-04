package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class FragmentTextRecordBinding implements ViewBinding {
    public final ImageView ivBottom;
    public final CustomImageView ivEffectNaviTextinputFun;
    public final RecyclerView recyclerviewHistory;
    private final ConstraintLayout rootView;

    private FragmentTextRecordBinding(ConstraintLayout rootView, ImageView ivBottom, CustomImageView ivEffectNaviTextinputFun, RecyclerView recyclerviewHistory) {
        this.rootView = rootView;
        this.ivBottom = ivBottom;
        this.ivEffectNaviTextinputFun = ivEffectNaviTextinputFun;
        this.recyclerviewHistory = recyclerviewHistory;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentTextRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentTextRecordBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_text_record, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentTextRecordBinding bind(View rootView) {
        int i = R.id.iv_bottom;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_bottom);
        if (imageView != null) {
            i = R.id.iv_effect_navi_textinput_fun;
            CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_effect_navi_textinput_fun);
            if (customImageView != null) {
                i = R.id.recyclerviewHistory;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerviewHistory);
                if (recyclerView != null) {
                    return new FragmentTextRecordBinding((ConstraintLayout) rootView, imageView, customImageView, recyclerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
