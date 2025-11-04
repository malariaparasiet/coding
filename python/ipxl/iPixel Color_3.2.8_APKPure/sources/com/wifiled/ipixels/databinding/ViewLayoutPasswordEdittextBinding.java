package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ViewLayoutPasswordEdittextBinding implements ViewBinding {
    public final LinearLayout llSetPwd;
    private final RelativeLayout rootView;
    public final TextView tvSetPwd1;
    public final TextView tvSetPwd2;
    public final TextView tvSetPwd3;
    public final TextView tvSetPwd4;
    public final TextView tvSetPwd5;
    public final TextView tvSetPwd6;

    private ViewLayoutPasswordEdittextBinding(RelativeLayout rootView, LinearLayout llSetPwd, TextView tvSetPwd1, TextView tvSetPwd2, TextView tvSetPwd3, TextView tvSetPwd4, TextView tvSetPwd5, TextView tvSetPwd6) {
        this.rootView = rootView;
        this.llSetPwd = llSetPwd;
        this.tvSetPwd1 = tvSetPwd1;
        this.tvSetPwd2 = tvSetPwd2;
        this.tvSetPwd3 = tvSetPwd3;
        this.tvSetPwd4 = tvSetPwd4;
        this.tvSetPwd5 = tvSetPwd5;
        this.tvSetPwd6 = tvSetPwd6;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ViewLayoutPasswordEdittextBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ViewLayoutPasswordEdittextBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.view_layout_password_edittext, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ViewLayoutPasswordEdittextBinding bind(View rootView) {
        int i = R.id.ll_set_pwd;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_set_pwd);
        if (linearLayout != null) {
            i = R.id.tv_set_pwd_1;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_set_pwd_1);
            if (textView != null) {
                i = R.id.tv_set_pwd_2;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_set_pwd_2);
                if (textView2 != null) {
                    i = R.id.tv_set_pwd_3;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_set_pwd_3);
                    if (textView3 != null) {
                        i = R.id.tv_set_pwd_4;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_set_pwd_4);
                        if (textView4 != null) {
                            i = R.id.tv_set_pwd_5;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_set_pwd_5);
                            if (textView5 != null) {
                                i = R.id.tv_set_pwd_6;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_set_pwd_6);
                                if (textView6 != null) {
                                    return new ViewLayoutPasswordEdittextBinding((RelativeLayout) rootView, linearLayout, textView, textView2, textView3, textView4, textView5, textView6);
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
