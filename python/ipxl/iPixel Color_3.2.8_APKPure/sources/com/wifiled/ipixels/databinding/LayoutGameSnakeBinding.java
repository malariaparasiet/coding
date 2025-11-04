package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.wifiled.gameview.snake.view.StageView;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class LayoutGameSnakeBinding implements ViewBinding {
    private final StageView rootView;

    private LayoutGameSnakeBinding(StageView rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public StageView getRoot() {
        return this.rootView;
    }

    public static LayoutGameSnakeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutGameSnakeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.layout_game_snake, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutGameSnakeBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        return new LayoutGameSnakeBinding((StageView) rootView);
    }
}
