package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ItemTextEffectBinding implements ViewBinding {
    public final ConstraintLayout clEffectFrame;
    private final ConstraintLayout rootView;
    public final TextView tvEffect;

    private ItemTextEffectBinding(ConstraintLayout rootView, ConstraintLayout clEffectFrame, TextView tvEffect) {
        this.rootView = rootView;
        this.clEffectFrame = clEffectFrame;
        this.tvEffect = tvEffect;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemTextEffectBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemTextEffectBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_text_effect, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemTextEffectBinding bind(View rootView) {
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_effect);
        if (textView != null) {
            return new ItemTextEffectBinding(constraintLayout, constraintLayout, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.tv_effect)));
    }
}
