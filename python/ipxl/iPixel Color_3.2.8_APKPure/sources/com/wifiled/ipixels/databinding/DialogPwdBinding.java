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
public final class DialogPwdBinding implements ViewBinding {
    public final LinearLayout llBottom;
    public final LinearLayout llSetPwd1;
    public final RelativeLayout rlKeyDelete;
    public final RelativeLayout rlRoot;
    private final RelativeLayout rootView;
    public final TextView tvCancel;
    public final TextView tvKey0;
    public final TextView tvKey1;
    public final TextView tvKey2;
    public final TextView tvKey3;
    public final TextView tvKey4;
    public final TextView tvKey5;
    public final TextView tvKey6;
    public final TextView tvKey7;
    public final TextView tvKey8;
    public final TextView tvKey9;
    public final TextView tvOk;
    public final TextView tvSetPwd1;
    public final TextView tvSetPwd2;
    public final TextView tvSetPwd3;
    public final TextView tvSetPwd4;
    public final TextView tvSetPwd5;
    public final TextView tvSetPwd6;
    public final TextView tvSetPwdTitle1;
    public final TextView tvTitle;

    private DialogPwdBinding(RelativeLayout rootView, LinearLayout llBottom, LinearLayout llSetPwd1, RelativeLayout rlKeyDelete, RelativeLayout rlRoot, TextView tvCancel, TextView tvKey0, TextView tvKey1, TextView tvKey2, TextView tvKey3, TextView tvKey4, TextView tvKey5, TextView tvKey6, TextView tvKey7, TextView tvKey8, TextView tvKey9, TextView tvOk, TextView tvSetPwd1, TextView tvSetPwd2, TextView tvSetPwd3, TextView tvSetPwd4, TextView tvSetPwd5, TextView tvSetPwd6, TextView tvSetPwdTitle1, TextView tvTitle) {
        this.rootView = rootView;
        this.llBottom = llBottom;
        this.llSetPwd1 = llSetPwd1;
        this.rlKeyDelete = rlKeyDelete;
        this.rlRoot = rlRoot;
        this.tvCancel = tvCancel;
        this.tvKey0 = tvKey0;
        this.tvKey1 = tvKey1;
        this.tvKey2 = tvKey2;
        this.tvKey3 = tvKey3;
        this.tvKey4 = tvKey4;
        this.tvKey5 = tvKey5;
        this.tvKey6 = tvKey6;
        this.tvKey7 = tvKey7;
        this.tvKey8 = tvKey8;
        this.tvKey9 = tvKey9;
        this.tvOk = tvOk;
        this.tvSetPwd1 = tvSetPwd1;
        this.tvSetPwd2 = tvSetPwd2;
        this.tvSetPwd3 = tvSetPwd3;
        this.tvSetPwd4 = tvSetPwd4;
        this.tvSetPwd5 = tvSetPwd5;
        this.tvSetPwd6 = tvSetPwd6;
        this.tvSetPwdTitle1 = tvSetPwdTitle1;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static DialogPwdBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogPwdBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.dialog_pwd, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogPwdBinding bind(View rootView) {
        int i = R.id.ll_bottom;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_bottom);
        if (linearLayout != null) {
            i = R.id.ll_set_pwd_1;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_set_pwd_1);
            if (linearLayout2 != null) {
                i = R.id.rl_key_delete;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_key_delete);
                if (relativeLayout != null) {
                    RelativeLayout relativeLayout2 = (RelativeLayout) rootView;
                    i = R.id.tv_cancel;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_cancel);
                    if (textView != null) {
                        i = R.id.tv_key_0;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_0);
                        if (textView2 != null) {
                            i = R.id.tv_key_1;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_1);
                            if (textView3 != null) {
                                i = R.id.tv_key_2;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_2);
                                if (textView4 != null) {
                                    i = R.id.tv_key_3;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_3);
                                    if (textView5 != null) {
                                        i = R.id.tv_key_4;
                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_4);
                                        if (textView6 != null) {
                                            i = R.id.tv_key_5;
                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_5);
                                            if (textView7 != null) {
                                                i = R.id.tv_key_6;
                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_6);
                                                if (textView8 != null) {
                                                    i = R.id.tv_key_7;
                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_7);
                                                    if (textView9 != null) {
                                                        i = R.id.tv_key_8;
                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_8);
                                                        if (textView10 != null) {
                                                            i = R.id.tv_key_9;
                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_9);
                                                            if (textView11 != null) {
                                                                i = R.id.tv_ok;
                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_ok);
                                                                if (textView12 != null) {
                                                                    i = R.id.tv_set_pwd_1;
                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_set_pwd_1);
                                                                    if (textView13 != null) {
                                                                        i = R.id.tv_set_pwd_2;
                                                                        TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_set_pwd_2);
                                                                        if (textView14 != null) {
                                                                            i = R.id.tv_set_pwd_3;
                                                                            TextView textView15 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_set_pwd_3);
                                                                            if (textView15 != null) {
                                                                                i = R.id.tv_set_pwd_4;
                                                                                TextView textView16 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_set_pwd_4);
                                                                                if (textView16 != null) {
                                                                                    i = R.id.tv_set_pwd_5;
                                                                                    TextView textView17 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_set_pwd_5);
                                                                                    if (textView17 != null) {
                                                                                        i = R.id.tv_set_pwd_6;
                                                                                        TextView textView18 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_set_pwd_6);
                                                                                        if (textView18 != null) {
                                                                                            i = R.id.tv_set_pwd_title_1;
                                                                                            TextView textView19 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_set_pwd_title_1);
                                                                                            if (textView19 != null) {
                                                                                                i = R.id.tv_title;
                                                                                                TextView textView20 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                                                                                                if (textView20 != null) {
                                                                                                    return new DialogPwdBinding(relativeLayout2, linearLayout, linearLayout2, relativeLayout, relativeLayout2, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20);
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
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
