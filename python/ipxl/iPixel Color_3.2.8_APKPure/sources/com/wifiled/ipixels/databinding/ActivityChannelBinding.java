package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.baselib.widget.TextDrawable;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ActivityChannelBinding implements ViewBinding {
    public final ConstraintLayout clChannelBottomFun;
    public final ConstraintLayout ilChannelToobar;
    public final ImageView ivBottom;
    public final ImageView ivChannelDel;
    public final ImageView ivChannelSend;
    public final TextDrawable ivChlSelAll;
    public final RecyclerView rlChannelList;
    private final ConstraintLayout rootView;

    private ActivityChannelBinding(ConstraintLayout rootView, ConstraintLayout clChannelBottomFun, ConstraintLayout ilChannelToobar, ImageView ivBottom, ImageView ivChannelDel, ImageView ivChannelSend, TextDrawable ivChlSelAll, RecyclerView rlChannelList) {
        this.rootView = rootView;
        this.clChannelBottomFun = clChannelBottomFun;
        this.ilChannelToobar = ilChannelToobar;
        this.ivBottom = ivBottom;
        this.ivChannelDel = ivChannelDel;
        this.ivChannelSend = ivChannelSend;
        this.ivChlSelAll = ivChlSelAll;
        this.rlChannelList = rlChannelList;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityChannelBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityChannelBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_channel, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityChannelBinding bind(View rootView) {
        int i = R.id.cl_channel_bottom_fun;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_channel_bottom_fun);
        if (constraintLayout != null) {
            i = R.id.il_channel_toobar;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_channel_toobar);
            if (constraintLayout2 != null) {
                i = R.id.iv_bottom;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_bottom);
                if (imageView != null) {
                    i = R.id.iv_channel_del;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_channel_del);
                    if (imageView2 != null) {
                        i = R.id.iv_channel_send;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_channel_send);
                        if (imageView3 != null) {
                            i = R.id.iv_chl_sel_all;
                            TextDrawable textDrawable = (TextDrawable) ViewBindings.findChildViewById(rootView, R.id.iv_chl_sel_all);
                            if (textDrawable != null) {
                                i = R.id.rl_channel_list;
                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rl_channel_list);
                                if (recyclerView != null) {
                                    return new ActivityChannelBinding((ConstraintLayout) rootView, constraintLayout, constraintLayout2, imageView, imageView2, imageView3, textDrawable, recyclerView);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
