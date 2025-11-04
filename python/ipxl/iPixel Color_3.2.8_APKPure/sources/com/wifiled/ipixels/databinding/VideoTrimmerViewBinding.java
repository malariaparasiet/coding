package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.video_clip.ZVideoView;

/* loaded from: classes3.dex */
public final class VideoTrimmerViewBinding implements ViewBinding {
    public final ImageView iconVideoPlay;
    public final RelativeLayout layout;
    public final RelativeLayout layoutSurfaceView;
    public final ImageView positionIcon;
    private final RelativeLayout rootView;
    public final LinearLayout seekBarLayout;
    public final FrameLayout videoFramesLayout;
    public final RecyclerView videoFramesRecyclerView;
    public final ZVideoView videoLoader;
    public final TextView videoShootTip;

    private VideoTrimmerViewBinding(RelativeLayout rootView, ImageView iconVideoPlay, RelativeLayout layout, RelativeLayout layoutSurfaceView, ImageView positionIcon, LinearLayout seekBarLayout, FrameLayout videoFramesLayout, RecyclerView videoFramesRecyclerView, ZVideoView videoLoader, TextView videoShootTip) {
        this.rootView = rootView;
        this.iconVideoPlay = iconVideoPlay;
        this.layout = layout;
        this.layoutSurfaceView = layoutSurfaceView;
        this.positionIcon = positionIcon;
        this.seekBarLayout = seekBarLayout;
        this.videoFramesLayout = videoFramesLayout;
        this.videoFramesRecyclerView = videoFramesRecyclerView;
        this.videoLoader = videoLoader;
        this.videoShootTip = videoShootTip;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static VideoTrimmerViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static VideoTrimmerViewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.video_trimmer_view, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static VideoTrimmerViewBinding bind(View rootView) {
        int i = R.id.icon_video_play;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.icon_video_play);
        if (imageView != null) {
            i = R.id.layout;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.layout);
            if (relativeLayout != null) {
                i = R.id.layout_surface_view;
                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.layout_surface_view);
                if (relativeLayout2 != null) {
                    i = R.id.positionIcon;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.positionIcon);
                    if (imageView2 != null) {
                        i = R.id.seekBarLayout;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.seekBarLayout);
                        if (linearLayout != null) {
                            i = R.id.video_frames_layout;
                            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.video_frames_layout);
                            if (frameLayout != null) {
                                i = R.id.video_frames_recyclerView;
                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.video_frames_recyclerView);
                                if (recyclerView != null) {
                                    i = R.id.video_loader;
                                    ZVideoView zVideoView = (ZVideoView) ViewBindings.findChildViewById(rootView, R.id.video_loader);
                                    if (zVideoView != null) {
                                        i = R.id.video_shoot_tip;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.video_shoot_tip);
                                        if (textView != null) {
                                            return new VideoTrimmerViewBinding((RelativeLayout) rootView, imageView, relativeLayout, relativeLayout2, imageView2, linearLayout, frameLayout, recyclerView, zVideoView, textView);
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
