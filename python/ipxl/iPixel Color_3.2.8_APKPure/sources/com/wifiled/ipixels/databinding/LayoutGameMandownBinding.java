package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.wifiled.gameview.mendown.GameLayout;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class LayoutGameMandownBinding implements ViewBinding {
    private final GameLayout rootView;

    private LayoutGameMandownBinding(GameLayout rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public GameLayout getRoot() {
        return this.rootView;
    }

    public static LayoutGameMandownBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutGameMandownBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.layout_game_mandown, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutGameMandownBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        return new LayoutGameMandownBinding((GameLayout) rootView);
    }
}
