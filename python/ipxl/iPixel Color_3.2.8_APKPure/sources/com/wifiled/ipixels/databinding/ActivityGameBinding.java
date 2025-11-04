package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ActivityGameBinding implements ViewBinding {
    public final ConstraintLayout ilGame;
    public final FrameLayout navigationContainer;
    private final ConstraintLayout rootView;

    private ActivityGameBinding(ConstraintLayout rootView, ConstraintLayout ilGame, FrameLayout navigationContainer) {
        this.rootView = rootView;
        this.ilGame = ilGame;
        this.navigationContainer = navigationContainer;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityGameBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_game, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityGameBinding bind(View rootView) {
        int i = R.id.il_game;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_game);
        if (constraintLayout != null) {
            i = R.id.navigation_container;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.navigation_container);
            if (frameLayout != null) {
                return new ActivityGameBinding((ConstraintLayout) rootView, constraintLayout, frameLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
