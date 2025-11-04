package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ImageTextListItemBinding implements ViewBinding {
    public final ImageView ivSchedule;
    public final ImageView ivScheduleSelect;
    private final ConstraintLayout rootView;
    public final TextView tvContent;

    private ImageTextListItemBinding(ConstraintLayout rootView, ImageView ivSchedule, ImageView ivScheduleSelect, TextView tvContent) {
        this.rootView = rootView;
        this.ivSchedule = ivSchedule;
        this.ivScheduleSelect = ivScheduleSelect;
        this.tvContent = tvContent;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ImageTextListItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ImageTextListItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.image_text_list_item, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ImageTextListItemBinding bind(View rootView) {
        int i = R.id.iv_schedule;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_schedule);
        if (imageView != null) {
            i = R.id.iv_schedule_select;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_schedule_select);
            if (imageView2 != null) {
                i = R.id.tv_content;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content);
                if (textView != null) {
                    return new ImageTextListItemBinding((ConstraintLayout) rootView, imageView, imageView2, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
