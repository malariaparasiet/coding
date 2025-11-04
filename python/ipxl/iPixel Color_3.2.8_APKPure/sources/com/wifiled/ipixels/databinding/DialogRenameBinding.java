package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class DialogRenameBinding implements ViewBinding {
    public final ImageView popupsNo;
    public final ImageView popupsOk;
    public final EditText rename;
    private final ConstraintLayout rootView;
    public final TextView title;

    private DialogRenameBinding(ConstraintLayout rootView, ImageView popupsNo, ImageView popupsOk, EditText rename, TextView title) {
        this.rootView = rootView;
        this.popupsNo = popupsNo;
        this.popupsOk = popupsOk;
        this.rename = rename;
        this.title = title;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogRenameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogRenameBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.dialog_rename, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogRenameBinding bind(View rootView) {
        int i = R.id.popups_no;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.popups_no);
        if (imageView != null) {
            i = R.id.popups_ok;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.popups_ok);
            if (imageView2 != null) {
                i = R.id.rename;
                EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.rename);
                if (editText != null) {
                    i = R.id.title;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.title);
                    if (textView != null) {
                        return new DialogRenameBinding((ConstraintLayout) rootView, imageView, imageView2, editText, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
