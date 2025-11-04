package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.ui.channel.text.ObjectarxItem;

/* loaded from: classes3.dex */
public final class ItemRc21BusinessBinding implements ViewBinding {
    public final ImageView ivAnimEdit;
    public final ImageView ivAnimPreview;
    public final ImageView ivAnimSelect;
    public final ImageView ivNum;
    public final ObjectarxItem objectarxItem;
    public final RelativeLayout relayoutChannelText;
    public final RelativeLayout rlImageOutsideFrame;
    private final ConstraintLayout rootView;

    private ItemRc21BusinessBinding(ConstraintLayout rootView, ImageView ivAnimEdit, ImageView ivAnimPreview, ImageView ivAnimSelect, ImageView ivNum, ObjectarxItem objectarxItem, RelativeLayout relayoutChannelText, RelativeLayout rlImageOutsideFrame) {
        this.rootView = rootView;
        this.ivAnimEdit = ivAnimEdit;
        this.ivAnimPreview = ivAnimPreview;
        this.ivAnimSelect = ivAnimSelect;
        this.ivNum = ivNum;
        this.objectarxItem = objectarxItem;
        this.relayoutChannelText = relayoutChannelText;
        this.rlImageOutsideFrame = rlImageOutsideFrame;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemRc21BusinessBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemRc21BusinessBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_rc_2_1_business, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemRc21BusinessBinding bind(View rootView) {
        int i = R.id.iv_anim_edit;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_anim_edit);
        if (imageView != null) {
            i = R.id.iv_anim_preview;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_anim_preview);
            if (imageView2 != null) {
                i = R.id.iv_anim_select;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_anim_select);
                if (imageView3 != null) {
                    i = R.id.iv_num;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_num);
                    if (imageView4 != null) {
                        i = R.id.objectarxItem;
                        ObjectarxItem objectarxItem = (ObjectarxItem) ViewBindings.findChildViewById(rootView, R.id.objectarxItem);
                        if (objectarxItem != null) {
                            i = R.id.relayout_channel_text;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.relayout_channel_text);
                            if (relativeLayout != null) {
                                i = R.id.rl_image_outside_frame;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_image_outside_frame);
                                if (relativeLayout2 != null) {
                                    return new ItemRc21BusinessBinding((ConstraintLayout) rootView, imageView, imageView2, imageView3, imageView4, objectarxItem, relativeLayout, relativeLayout2);
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
