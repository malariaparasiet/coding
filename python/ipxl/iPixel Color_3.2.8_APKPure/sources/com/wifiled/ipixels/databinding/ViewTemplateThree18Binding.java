package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.ui.channel.text.ObjectarxItem;

/* loaded from: classes3.dex */
public final class ViewTemplateThree18Binding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final View tem3BorderView1;
    public final ImageView tem3Iv1;
    public final TextView tem3Tv1;
    public final RelativeLayout tem3Tv1Text;
    public final ObjectarxItem tem3Tv1TextRl;
    public final ConstraintLayout template;

    private ViewTemplateThree18Binding(ConstraintLayout rootView, View tem3BorderView1, ImageView tem3Iv1, TextView tem3Tv1, RelativeLayout tem3Tv1Text, ObjectarxItem tem3Tv1TextRl, ConstraintLayout template) {
        this.rootView = rootView;
        this.tem3BorderView1 = tem3BorderView1;
        this.tem3Iv1 = tem3Iv1;
        this.tem3Tv1 = tem3Tv1;
        this.tem3Tv1Text = tem3Tv1Text;
        this.tem3Tv1TextRl = tem3Tv1TextRl;
        this.template = template;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewTemplateThree18Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ViewTemplateThree18Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.view_template_three_1_8, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ViewTemplateThree18Binding bind(View rootView) {
        int i = R.id.tem3_border_view1;
        View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.tem3_border_view1);
        if (findChildViewById != null) {
            i = R.id.tem3_iv1;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tem3_iv1);
            if (imageView != null) {
                i = R.id.tem3_tv1;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tem3_tv1);
                if (textView != null) {
                    i = R.id.tem3_tv1_text;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.tem3_tv1_text);
                    if (relativeLayout != null) {
                        i = R.id.tem3_tv1_text_rl;
                        ObjectarxItem objectarxItem = (ObjectarxItem) ViewBindings.findChildViewById(rootView, R.id.tem3_tv1_text_rl);
                        if (objectarxItem != null) {
                            i = R.id.template;
                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.template);
                            if (constraintLayout != null) {
                                return new ViewTemplateThree18Binding((ConstraintLayout) rootView, findChildViewById, imageView, textView, relativeLayout, objectarxItem, constraintLayout);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
