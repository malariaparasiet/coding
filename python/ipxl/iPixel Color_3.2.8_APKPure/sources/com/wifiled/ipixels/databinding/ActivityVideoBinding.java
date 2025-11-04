package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.video_clip.ZVideoView;

/* loaded from: classes3.dex */
public final class ActivityVideoBinding implements ViewBinding {
    public final FrameLayout flPreview;
    public final ImageView ivPreview;
    public final RelativeLayout layoutSurfaceView;
    private final RelativeLayout rootView;
    public final SeekBar seekbar;
    public final ZVideoView videoView;

    private ActivityVideoBinding(RelativeLayout rootView, FrameLayout flPreview, ImageView ivPreview, RelativeLayout layoutSurfaceView, SeekBar seekbar, ZVideoView videoView) {
        this.rootView = rootView;
        this.flPreview = flPreview;
        this.ivPreview = ivPreview;
        this.layoutSurfaceView = layoutSurfaceView;
        this.seekbar = seekbar;
        this.videoView = videoView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityVideoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityVideoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_video, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityVideoBinding bind(View rootView) {
        int i = R.id.fl_preview;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.fl_preview);
        if (frameLayout != null) {
            i = R.id.iv_preview;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_preview);
            if (imageView != null) {
                i = R.id.layout_surface_view;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.layout_surface_view);
                if (relativeLayout != null) {
                    i = R.id.seekbar;
                    SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, R.id.seekbar);
                    if (seekBar != null) {
                        i = R.id.videoView;
                        ZVideoView zVideoView = (ZVideoView) ViewBindings.findChildViewById(rootView, R.id.videoView);
                        if (zVideoView != null) {
                            return new ActivityVideoBinding((RelativeLayout) rootView, frameLayout, imageView, relativeLayout, seekBar, zVideoView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
