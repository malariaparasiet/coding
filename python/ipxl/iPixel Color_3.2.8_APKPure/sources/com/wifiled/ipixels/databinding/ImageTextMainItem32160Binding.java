package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ImageTextMainItem32160Binding implements ViewBinding {
    public final AppCompatImageView img;
    public final View imgBg;
    public final RelativeLayout relayoutChannelText;
    public final RecyclerView rlChannelTextItem;
    private final ConstraintLayout rootView;

    private ImageTextMainItem32160Binding(ConstraintLayout rootView, AppCompatImageView img, View imgBg, RelativeLayout relayoutChannelText, RecyclerView rlChannelTextItem) {
        this.rootView = rootView;
        this.img = img;
        this.imgBg = imgBg;
        this.relayoutChannelText = relayoutChannelText;
        this.rlChannelTextItem = rlChannelTextItem;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ImageTextMainItem32160Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ImageTextMainItem32160Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.image_text_main_item_32160, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ImageTextMainItem32160Binding bind(View rootView) {
        int i = R.id.img;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.img);
        if (appCompatImageView != null) {
            i = R.id.img_bg;
            View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.img_bg);
            if (findChildViewById != null) {
                i = R.id.relayout_channel_text;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.relayout_channel_text);
                if (relativeLayout != null) {
                    i = R.id.rl_channel_text_item;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rl_channel_text_item);
                    if (recyclerView != null) {
                        return new ImageTextMainItem32160Binding((ConstraintLayout) rootView, appCompatImageView, findChildViewById, relativeLayout, recyclerView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
