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
public final class ViewTemplateTwo16Binding implements ViewBinding {
    public final ObjectarxItem objectarxItem1;
    private final ConstraintLayout rootView;
    public final View tem2BorderView1;
    public final ImageView tem2Iv1;
    public final TextView tem2Tv1;
    public final RelativeLayout tem2Tv1Text;
    public final ConstraintLayout template;

    private ViewTemplateTwo16Binding(ConstraintLayout rootView, ObjectarxItem objectarxItem1, View tem2BorderView1, ImageView tem2Iv1, TextView tem2Tv1, RelativeLayout tem2Tv1Text, ConstraintLayout template) {
        this.rootView = rootView;
        this.objectarxItem1 = objectarxItem1;
        this.tem2BorderView1 = tem2BorderView1;
        this.tem2Iv1 = tem2Iv1;
        this.tem2Tv1 = tem2Tv1;
        this.tem2Tv1Text = tem2Tv1Text;
        this.template = template;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewTemplateTwo16Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ViewTemplateTwo16Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.view_template_two_1_6, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ViewTemplateTwo16Binding bind(View rootView) {
        int i = R.id.objectarxItem1;
        ObjectarxItem objectarxItem = (ObjectarxItem) ViewBindings.findChildViewById(rootView, R.id.objectarxItem1);
        if (objectarxItem != null) {
            i = R.id.tem2_border_view1;
            View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.tem2_border_view1);
            if (findChildViewById != null) {
                i = R.id.tem2_iv1;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tem2_iv1);
                if (imageView != null) {
                    i = R.id.tem2_tv1;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tem2_tv1);
                    if (textView != null) {
                        i = R.id.tem2_tv1_text;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.tem2_tv1_text);
                        if (relativeLayout != null) {
                            i = R.id.template;
                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.template);
                            if (constraintLayout != null) {
                                return new ViewTemplateTwo16Binding((ConstraintLayout) rootView, objectarxItem, findChildViewById, imageView, textView, relativeLayout, constraintLayout);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
