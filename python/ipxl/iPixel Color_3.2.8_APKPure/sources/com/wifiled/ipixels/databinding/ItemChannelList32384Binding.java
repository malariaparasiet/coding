package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.ui.channel.text.ObjectarxItem;
import com.wifiled.ipixels.view.LedView;

/* loaded from: classes3.dex */
public final class ItemChannelList32384Binding implements ViewBinding {
    public final ImageView ivChannelFix;
    public final ImageView ivChannelImagGifItem;
    public final ImageView ivChlDel;
    public final ImageView ivChlSel;
    public final ImageView leftEyesGif;
    public final ObjectarxItem objectarxItem;
    public final RelativeLayout relayoutChannelText;
    public final ImageView rightEyesGif;
    public final LedView rlChannelTextBg;
    private final ConstraintLayout rootView;
    public final TextView tvNum;

    private ItemChannelList32384Binding(ConstraintLayout rootView, ImageView ivChannelFix, ImageView ivChannelImagGifItem, ImageView ivChlDel, ImageView ivChlSel, ImageView leftEyesGif, ObjectarxItem objectarxItem, RelativeLayout relayoutChannelText, ImageView rightEyesGif, LedView rlChannelTextBg, TextView tvNum) {
        this.rootView = rootView;
        this.ivChannelFix = ivChannelFix;
        this.ivChannelImagGifItem = ivChannelImagGifItem;
        this.ivChlDel = ivChlDel;
        this.ivChlSel = ivChlSel;
        this.leftEyesGif = leftEyesGif;
        this.objectarxItem = objectarxItem;
        this.relayoutChannelText = relayoutChannelText;
        this.rightEyesGif = rightEyesGif;
        this.rlChannelTextBg = rlChannelTextBg;
        this.tvNum = tvNum;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemChannelList32384Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemChannelList32384Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_channel_list_32384, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemChannelList32384Binding bind(View rootView) {
        int i = R.id.iv_channel_fix;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_channel_fix);
        if (imageView != null) {
            i = R.id.iv_channel_imag_gif_item;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_channel_imag_gif_item);
            if (imageView2 != null) {
                i = R.id.iv_chl_del;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_chl_del);
                if (imageView3 != null) {
                    i = R.id.iv_chl_sel;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_chl_sel);
                    if (imageView4 != null) {
                        i = R.id.left_eyes_gif;
                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.left_eyes_gif);
                        if (imageView5 != null) {
                            i = R.id.objectarxItem;
                            ObjectarxItem objectarxItem = (ObjectarxItem) ViewBindings.findChildViewById(rootView, R.id.objectarxItem);
                            if (objectarxItem != null) {
                                i = R.id.relayout_channel_text;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.relayout_channel_text);
                                if (relativeLayout != null) {
                                    i = R.id.right_eyes_gif;
                                    ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.right_eyes_gif);
                                    if (imageView6 != null) {
                                        i = R.id.rl_channel_text_bg;
                                        LedView ledView = (LedView) ViewBindings.findChildViewById(rootView, R.id.rl_channel_text_bg);
                                        if (ledView != null) {
                                            i = R.id.tv_num;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_num);
                                            if (textView != null) {
                                                return new ItemChannelList32384Binding((ConstraintLayout) rootView, imageView, imageView2, imageView3, imageView4, imageView5, objectarxItem, relativeLayout, imageView6, ledView, textView);
                                            }
                                        }
                                    }
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
