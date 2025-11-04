package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class AdapterMusicBinding implements ViewBinding {
    public final ImageView ivMusicState;
    private final RelativeLayout rootView;
    public final TextView tvName;

    private AdapterMusicBinding(RelativeLayout rootView, ImageView ivMusicState, TextView tvName) {
        this.rootView = rootView;
        this.ivMusicState = ivMusicState;
        this.tvName = tvName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static AdapterMusicBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static AdapterMusicBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.adapter_music, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static AdapterMusicBinding bind(View rootView) {
        int i = R.id.iv_music_state;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_music_state);
        if (imageView != null) {
            i = R.id.tv_name;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_name);
            if (textView != null) {
                return new AdapterMusicBinding((RelativeLayout) rootView, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
