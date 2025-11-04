package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class DialogTextChangeTipBinding implements ViewBinding {
    public final TextView cancel;
    public final Guideline glBottom;
    public final View lineView;
    public final View lineView2;
    public final TextView message;
    private final ConstraintLayout rootView;
    public final TextView submit;
    public final TextView title;

    private DialogTextChangeTipBinding(ConstraintLayout rootView, TextView cancel, Guideline glBottom, View lineView, View lineView2, TextView message, TextView submit, TextView title) {
        this.rootView = rootView;
        this.cancel = cancel;
        this.glBottom = glBottom;
        this.lineView = lineView;
        this.lineView2 = lineView2;
        this.message = message;
        this.submit = submit;
        this.title = title;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogTextChangeTipBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogTextChangeTipBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.dialog_text_change_tip, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogTextChangeTipBinding bind(View rootView) {
        int i = R.id.cancel;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.cancel);
        if (textView != null) {
            i = R.id.gl_bottom;
            Guideline guideline = (Guideline) ViewBindings.findChildViewById(rootView, R.id.gl_bottom);
            if (guideline != null) {
                i = R.id.line_view;
                View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_view);
                if (findChildViewById != null) {
                    i = R.id.line_view2;
                    View findChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.line_view2);
                    if (findChildViewById2 != null) {
                        i = R.id.message;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.message);
                        if (textView2 != null) {
                            i = R.id.submit;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.submit);
                            if (textView3 != null) {
                                i = R.id.title;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.title);
                                if (textView4 != null) {
                                    return new DialogTextChangeTipBinding((ConstraintLayout) rootView, textView, guideline, findChildViewById, findChildViewById2, textView2, textView3, textView4);
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
