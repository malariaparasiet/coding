package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class DialogSelectMediaBinding implements ViewBinding {
    public final LinearLayout linearLayout;
    public final RecyclerView rlActions;
    private final LinearLayout rootView;
    public final TextView tvCamera;
    public final TextView tvCancel;
    public final TextView tvGallery;
    public final TextView tvGif;

    private DialogSelectMediaBinding(LinearLayout rootView, LinearLayout linearLayout, RecyclerView rlActions, TextView tvCamera, TextView tvCancel, TextView tvGallery, TextView tvGif) {
        this.rootView = rootView;
        this.linearLayout = linearLayout;
        this.rlActions = rlActions;
        this.tvCamera = tvCamera;
        this.tvCancel = tvCancel;
        this.tvGallery = tvGallery;
        this.tvGif = tvGif;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogSelectMediaBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogSelectMediaBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.dialog_select_media, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogSelectMediaBinding bind(View rootView) {
        int i = R.id.linearLayout;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.linearLayout);
        if (linearLayout != null) {
            i = R.id.rl_actions;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rl_actions);
            if (recyclerView != null) {
                i = R.id.tv_camera;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_camera);
                if (textView != null) {
                    i = R.id.tv_cancel;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_cancel);
                    if (textView2 != null) {
                        i = R.id.tv_gallery;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_gallery);
                        if (textView3 != null) {
                            i = R.id.tv_gif;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_gif);
                            if (textView4 != null) {
                                return new DialogSelectMediaBinding((LinearLayout) rootView, linearLayout, recyclerView, textView, textView2, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
