package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class DialogSelectDeivceBinding implements ViewBinding {
    public final ImageView cancelBtn;
    public final ImageView confirmBtn;
    public final RadioButton device1;
    public final RadioButton device2;
    public final RadioGroup deviceGroup;
    public final LinearLayout dialogLayout;
    public final LinearLayout llMessage;
    private final LinearLayout rootView;
    public final TextView title;

    private DialogSelectDeivceBinding(LinearLayout rootView, ImageView cancelBtn, ImageView confirmBtn, RadioButton device1, RadioButton device2, RadioGroup deviceGroup, LinearLayout dialogLayout, LinearLayout llMessage, TextView title) {
        this.rootView = rootView;
        this.cancelBtn = cancelBtn;
        this.confirmBtn = confirmBtn;
        this.device1 = device1;
        this.device2 = device2;
        this.deviceGroup = deviceGroup;
        this.dialogLayout = dialogLayout;
        this.llMessage = llMessage;
        this.title = title;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogSelectDeivceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogSelectDeivceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.dialog_select_deivce, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogSelectDeivceBinding bind(View rootView) {
        int i = R.id.cancel_btn;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.cancel_btn);
        if (imageView != null) {
            i = R.id.confirm_btn;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.confirm_btn);
            if (imageView2 != null) {
                i = R.id.device_1;
                RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.device_1);
                if (radioButton != null) {
                    i = R.id.device_2;
                    RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.device_2);
                    if (radioButton2 != null) {
                        i = R.id.device_group;
                        RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(rootView, R.id.device_group);
                        if (radioGroup != null) {
                            LinearLayout linearLayout = (LinearLayout) rootView;
                            i = R.id.ll_message;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_message);
                            if (linearLayout2 != null) {
                                i = R.id.title;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.title);
                                if (textView != null) {
                                    return new DialogSelectDeivceBinding(linearLayout, imageView, imageView2, radioButton, radioButton2, radioGroup, linearLayout, linearLayout2, textView);
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
