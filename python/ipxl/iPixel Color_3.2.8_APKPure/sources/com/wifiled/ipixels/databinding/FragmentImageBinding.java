package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class FragmentImageBinding implements ViewBinding {
    public final RecyclerView diyRecyclerview;
    public final ImageView ivTitleBg;
    public final View line;
    public final LinearLayout noData;
    private final ConstraintLayout rootView;
    public final TabLayout tabLayout;
    public final ViewPager viewPager;

    private FragmentImageBinding(ConstraintLayout rootView, RecyclerView diyRecyclerview, ImageView ivTitleBg, View line, LinearLayout noData, TabLayout tabLayout, ViewPager viewPager) {
        this.rootView = rootView;
        this.diyRecyclerview = diyRecyclerview;
        this.ivTitleBg = ivTitleBg;
        this.line = line;
        this.noData = noData;
        this.tabLayout = tabLayout;
        this.viewPager = viewPager;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentImageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_image, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentImageBinding bind(View rootView) {
        int i = R.id.diyRecyclerview;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.diyRecyclerview);
        if (recyclerView != null) {
            i = R.id.iv_title_bg;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_title_bg);
            if (imageView != null) {
                i = R.id.line;
                View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.line);
                if (findChildViewById != null) {
                    i = R.id.no_data;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.no_data);
                    if (linearLayout != null) {
                        i = R.id.tabLayout;
                        TabLayout tabLayout = (TabLayout) ViewBindings.findChildViewById(rootView, R.id.tabLayout);
                        if (tabLayout != null) {
                            i = R.id.viewPager;
                            ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(rootView, R.id.viewPager);
                            if (viewPager != null) {
                                return new FragmentImageBinding((ConstraintLayout) rootView, recyclerView, imageView, findChildViewById, linearLayout, tabLayout, viewPager);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
