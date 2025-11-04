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
public final class ViewTemplateFour112Binding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final View tem4Border;
    public final View tem4BorderView1;
    public final View tem4BorderView2;
    public final ImageView tem4Iv1;
    public final TextView tem4Tv1;
    public final RelativeLayout tem4Tv1Text;
    public final ObjectarxItem tem4Tv1TextRl;
    public final TextView tem4Tv2;
    public final RelativeLayout tem4Tv2Text;
    public final ObjectarxItem tem4Tv2TextRl;
    public final ConstraintLayout template;

    private ViewTemplateFour112Binding(ConstraintLayout rootView, View tem4Border, View tem4BorderView1, View tem4BorderView2, ImageView tem4Iv1, TextView tem4Tv1, RelativeLayout tem4Tv1Text, ObjectarxItem tem4Tv1TextRl, TextView tem4Tv2, RelativeLayout tem4Tv2Text, ObjectarxItem tem4Tv2TextRl, ConstraintLayout template) {
        this.rootView = rootView;
        this.tem4Border = tem4Border;
        this.tem4BorderView1 = tem4BorderView1;
        this.tem4BorderView2 = tem4BorderView2;
        this.tem4Iv1 = tem4Iv1;
        this.tem4Tv1 = tem4Tv1;
        this.tem4Tv1Text = tem4Tv1Text;
        this.tem4Tv1TextRl = tem4Tv1TextRl;
        this.tem4Tv2 = tem4Tv2;
        this.tem4Tv2Text = tem4Tv2Text;
        this.tem4Tv2TextRl = tem4Tv2TextRl;
        this.template = template;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewTemplateFour112Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ViewTemplateFour112Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.view_template_four_1_12, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ViewTemplateFour112Binding bind(View rootView) {
        int i = R.id.tem4_border;
        View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.tem4_border);
        if (findChildViewById != null) {
            i = R.id.tem4_border_view1;
            View findChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.tem4_border_view1);
            if (findChildViewById2 != null) {
                i = R.id.tem4_border_view2;
                View findChildViewById3 = ViewBindings.findChildViewById(rootView, R.id.tem4_border_view2);
                if (findChildViewById3 != null) {
                    i = R.id.tem4_iv1;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tem4_iv1);
                    if (imageView != null) {
                        i = R.id.tem4_tv1;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tem4_tv1);
                        if (textView != null) {
                            i = R.id.tem4_tv1_text;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.tem4_tv1_text);
                            if (relativeLayout != null) {
                                i = R.id.tem4_tv1_text_rl;
                                ObjectarxItem objectarxItem = (ObjectarxItem) ViewBindings.findChildViewById(rootView, R.id.tem4_tv1_text_rl);
                                if (objectarxItem != null) {
                                    i = R.id.tem4_tv2;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tem4_tv2);
                                    if (textView2 != null) {
                                        i = R.id.tem4_tv2_text;
                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.tem4_tv2_text);
                                        if (relativeLayout2 != null) {
                                            i = R.id.tem4_tv2_text_rl;
                                            ObjectarxItem objectarxItem2 = (ObjectarxItem) ViewBindings.findChildViewById(rootView, R.id.tem4_tv2_text_rl);
                                            if (objectarxItem2 != null) {
                                                i = R.id.template;
                                                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.template);
                                                if (constraintLayout != null) {
                                                    return new ViewTemplateFour112Binding((ConstraintLayout) rootView, findChildViewById, findChildViewById2, findChildViewById3, imageView, textView, relativeLayout, objectarxItem, textView2, relativeLayout2, objectarxItem2, constraintLayout);
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
