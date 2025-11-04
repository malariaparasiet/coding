package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.video_clip.VideoTrimmerView;

/* loaded from: classes3.dex */
public final class ActivityVideoTrimBinding implements ViewBinding {
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final VideoTrimmerView trimmerView;

    private ActivityVideoTrimBinding(RelativeLayout rootView, RelativeLayout root, VideoTrimmerView trimmerView) {
        this.rootView = rootView;
        this.root = root;
        this.trimmerView = trimmerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityVideoTrimBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityVideoTrimBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_video_trim, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityVideoTrimBinding bind(View rootView) {
        RelativeLayout relativeLayout = (RelativeLayout) rootView;
        VideoTrimmerView videoTrimmerView = (VideoTrimmerView) ViewBindings.findChildViewById(rootView, R.id.trimmerView);
        if (videoTrimmerView != null) {
            return new ActivityVideoTrimBinding(relativeLayout, relativeLayout, videoTrimmerView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.trimmerView)));
    }
}
