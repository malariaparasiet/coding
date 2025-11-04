package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tiro.jlotalibrary.view.TextButton;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class ActivityOtadialogBinding implements ViewBinding {
    public final TextButton cancelButton;
    public final TextView newVersion;
    public final TextView oldVersion;
    public final TextView releaseNotes;
    private final ConstraintLayout rootView;
    public final TextButton sureButton;
    public final TextView textView;
    public final TextView tip;
    public final TextView tip1;
    public final TextView warn;

    private ActivityOtadialogBinding(ConstraintLayout rootView, TextButton cancelButton, TextView newVersion, TextView oldVersion, TextView releaseNotes, TextButton sureButton, TextView textView, TextView tip, TextView tip1, TextView warn) {
        this.rootView = rootView;
        this.cancelButton = cancelButton;
        this.newVersion = newVersion;
        this.oldVersion = oldVersion;
        this.releaseNotes = releaseNotes;
        this.sureButton = sureButton;
        this.textView = textView;
        this.tip = tip;
        this.tip1 = tip1;
        this.warn = warn;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityOtadialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityOtadialogBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_otadialog, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityOtadialogBinding bind(View rootView) {
        int i = R.id.cancel_button;
        TextButton textButton = (TextButton) ViewBindings.findChildViewById(rootView, R.id.cancel_button);
        if (textButton != null) {
            i = R.id.new_version;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.new_version);
            if (textView != null) {
                i = R.id.old_version;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.old_version);
                if (textView2 != null) {
                    i = R.id.release_notes;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.release_notes);
                    if (textView3 != null) {
                        i = R.id.sure_button;
                        TextButton textButton2 = (TextButton) ViewBindings.findChildViewById(rootView, R.id.sure_button);
                        if (textButton2 != null) {
                            i = R.id.textView;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.textView);
                            if (textView4 != null) {
                                i = R.id.tip;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tip);
                                if (textView5 != null) {
                                    i = R.id.tip1;
                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tip1);
                                    if (textView6 != null) {
                                        i = R.id.warn;
                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.warn);
                                        if (textView7 != null) {
                                            return new ActivityOtadialogBinding((ConstraintLayout) rootView, textButton, textView, textView2, textView3, textButton2, textView4, textView5, textView6, textView7);
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
