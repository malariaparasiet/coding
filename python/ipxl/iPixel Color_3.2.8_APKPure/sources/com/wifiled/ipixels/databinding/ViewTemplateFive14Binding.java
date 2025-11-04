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
public final class ViewTemplateFive14Binding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final View tem5Border;
    public final View tem5BorderView1;
    public final View tem5BorderView2;
    public final ImageView tem5Iv1;
    public final TextView tem5Tv1;
    public final RelativeLayout tem5Tv1Text;
    public final ObjectarxItem tem5Tv1TextRl;
    public final TextView tem5Tv2;
    public final RelativeLayout tem5Tv2Text;
    public final ObjectarxItem tem5Tv2TextRl;
    public final ConstraintLayout template;

    private ViewTemplateFive14Binding(ConstraintLayout rootView, View tem5Border, View tem5BorderView1, View tem5BorderView2, ImageView tem5Iv1, TextView tem5Tv1, RelativeLayout tem5Tv1Text, ObjectarxItem tem5Tv1TextRl, TextView tem5Tv2, RelativeLayout tem5Tv2Text, ObjectarxItem tem5Tv2TextRl, ConstraintLayout template) {
        this.rootView = rootView;
        this.tem5Border = tem5Border;
        this.tem5BorderView1 = tem5BorderView1;
        this.tem5BorderView2 = tem5BorderView2;
        this.tem5Iv1 = tem5Iv1;
        this.tem5Tv1 = tem5Tv1;
        this.tem5Tv1Text = tem5Tv1Text;
        this.tem5Tv1TextRl = tem5Tv1TextRl;
        this.tem5Tv2 = tem5Tv2;
        this.tem5Tv2Text = tem5Tv2Text;
        this.tem5Tv2TextRl = tem5Tv2TextRl;
        this.template = template;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewTemplateFive14Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ViewTemplateFive14Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.view_template_five_1_4, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ViewTemplateFive14Binding bind(View rootView) {
        int i = R.id.tem5_border;
        View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.tem5_border);
        if (findChildViewById != null) {
            i = R.id.tem5_border_view1;
            View findChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.tem5_border_view1);
            if (findChildViewById2 != null) {
                i = R.id.tem5_border_view2;
                View findChildViewById3 = ViewBindings.findChildViewById(rootView, R.id.tem5_border_view2);
                if (findChildViewById3 != null) {
                    i = R.id.tem5_iv1;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tem5_iv1);
                    if (imageView != null) {
                        i = R.id.tem5_tv1;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tem5_tv1);
                        if (textView != null) {
                            i = R.id.tem5_tv1_text;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.tem5_tv1_text);
                            if (relativeLayout != null) {
                                i = R.id.tem5_tv1_text_rl;
                                ObjectarxItem objectarxItem = (ObjectarxItem) ViewBindings.findChildViewById(rootView, R.id.tem5_tv1_text_rl);
                                if (objectarxItem != null) {
                                    i = R.id.tem5_tv2;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tem5_tv2);
                                    if (textView2 != null) {
                                        i = R.id.tem5_tv2_text;
                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.tem5_tv2_text);
                                        if (relativeLayout2 != null) {
                                            i = R.id.tem5_tv2_text_rl;
                                            ObjectarxItem objectarxItem2 = (ObjectarxItem) ViewBindings.findChildViewById(rootView, R.id.tem5_tv2_text_rl);
                                            if (objectarxItem2 != null) {
                                                i = R.id.template;
                                                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.template);
                                                if (constraintLayout != null) {
                                                    return new ViewTemplateFive14Binding((ConstraintLayout) rootView, findChildViewById, findChildViewById2, findChildViewById3, imageView, textView, relativeLayout, objectarxItem, textView2, relativeLayout2, objectarxItem2, constraintLayout);
                                                }
                                            }
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
