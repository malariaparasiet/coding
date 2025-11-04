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
public final class ViewTemplateOne13Binding implements ViewBinding {
    public final ImageView borderImg;
    public final ObjectarxItem objectarxItem1;
    public final ObjectarxItem objectarxItem2;
    private final ConstraintLayout rootView;
    public final View tem1BorderView1;
    public final View tem1BorderView2;
    public final TextView tem1Tv1;
    public final RelativeLayout tem1Tv1Text;
    public final TextView tem1Tv2;
    public final RelativeLayout tem1Tv2Text;
    public final ConstraintLayout text1;
    public final ConstraintLayout text2;

    private ViewTemplateOne13Binding(ConstraintLayout rootView, ImageView borderImg, ObjectarxItem objectarxItem1, ObjectarxItem objectarxItem2, View tem1BorderView1, View tem1BorderView2, TextView tem1Tv1, RelativeLayout tem1Tv1Text, TextView tem1Tv2, RelativeLayout tem1Tv2Text, ConstraintLayout text1, ConstraintLayout text2) {
        this.rootView = rootView;
        this.borderImg = borderImg;
        this.objectarxItem1 = objectarxItem1;
        this.objectarxItem2 = objectarxItem2;
        this.tem1BorderView1 = tem1BorderView1;
        this.tem1BorderView2 = tem1BorderView2;
        this.tem1Tv1 = tem1Tv1;
        this.tem1Tv1Text = tem1Tv1Text;
        this.tem1Tv2 = tem1Tv2;
        this.tem1Tv2Text = tem1Tv2Text;
        this.text1 = text1;
        this.text2 = text2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewTemplateOne13Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ViewTemplateOne13Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.view_template_one_1_3, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ViewTemplateOne13Binding bind(View rootView) {
        int i = R.id.border_img;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.border_img);
        if (imageView != null) {
            i = R.id.objectarxItem1;
            ObjectarxItem objectarxItem = (ObjectarxItem) ViewBindings.findChildViewById(rootView, R.id.objectarxItem1);
            if (objectarxItem != null) {
                i = R.id.objectarxItem2;
                ObjectarxItem objectarxItem2 = (ObjectarxItem) ViewBindings.findChildViewById(rootView, R.id.objectarxItem2);
                if (objectarxItem2 != null) {
                    i = R.id.tem1_border_view1;
                    View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.tem1_border_view1);
                    if (findChildViewById != null) {
                        i = R.id.tem1_border_view2;
                        View findChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.tem1_border_view2);
                        if (findChildViewById2 != null) {
                            i = R.id.tem1_tv1;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tem1_tv1);
                            if (textView != null) {
                                i = R.id.tem1_tv1_text;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.tem1_tv1_text);
                                if (relativeLayout != null) {
                                    i = R.id.tem1_tv2;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tem1_tv2);
                                    if (textView2 != null) {
                                        i = R.id.tem1_tv2_text;
                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.tem1_tv2_text);
                                        if (relativeLayout2 != null) {
                                            i = R.id.text1;
                                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.text1);
                                            if (constraintLayout != null) {
                                                i = R.id.text2;
                                                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.text2);
                                                if (constraintLayout2 != null) {
                                                    return new ViewTemplateOne13Binding((ConstraintLayout) rootView, imageView, objectarxItem, objectarxItem2, findChildViewById, findChildViewById2, textView, relativeLayout, textView2, relativeLayout2, constraintLayout, constraintLayout2);
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
