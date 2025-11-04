package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.wifiled.gameview.pingpong.BallView;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class LayoutGameBallBinding implements ViewBinding {
    private final BallView rootView;

    private LayoutGameBallBinding(BallView rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public BallView getRoot() {
        return this.rootView;
    }

    public static LayoutGameBallBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutGameBallBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.layout_game_ball, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutGameBallBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        return new LayoutGameBallBinding((BallView) rootView);
    }
}
