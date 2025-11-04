package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class DialogCustomBinding implements ViewBinding {
    public final ImageView cancelBtn;
    public final ImageView confirmBtn;
    public final LinearLayout dialogLayout;
    public final LinearLayout llMessage;
    public final TextView message;
    private final LinearLayout rootView;
    public final TextView title;

    private DialogCustomBinding(LinearLayout rootView, ImageView cancelBtn, ImageView confirmBtn, LinearLayout dialogLayout, LinearLayout llMessage, TextView message, TextView title) {
        this.rootView = rootView;
        this.cancelBtn = cancelBtn;
        this.confirmBtn = confirmBtn;
        this.dialogLayout = dialogLayout;
        this.llMessage = llMessage;
        this.message = message;
        this.title = title;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogCustomBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogCustomBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.dialog_custom, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogCustomBinding bind(View rootView) {
        int i = R.id.cancel_btn;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.cancel_btn);
        if (imageView != null) {
            i = R.id.confirm_btn;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.confirm_btn);
            if (imageView2 != null) {
                LinearLayout linearLayout = (LinearLayout) rootView;
                i = R.id.ll_message;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_message);
                if (linearLayout2 != null) {
                    i = R.id.message;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.message);
                    if (textView != null) {
                        i = R.id.title;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.title);
                        if (textView2 != null) {
                            return new DialogCustomBinding(linearLayout, imageView, imageView2, linearLayout, linearLayout2, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
