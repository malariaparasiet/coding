package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.PasswordEditText;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class ActivitySetPwdBinding implements ViewBinding {
    public final ImageView btnOk;
    public final ConstraintLayout changeOrCleanPwdCl;
    public final ConstraintLayout ilSettingsToolbar;
    public final PasswordEditText inputNewPwd;
    public final PasswordEditText inputOldPwd;
    public final PasswordEditText inputPwd;
    public final CustomImageView ivBack;
    public final View ivLine;
    public final View ivLine2;
    public final View ivLine3;
    public final CustomImageView ivMiddle;
    public final CustomImageView ivRight;
    public final CustomImageView ivRightSubtitle;
    public final LinearLayout keyBoard;
    public final LinearLayout llClearPwd;
    public final LinearLayout llUpdatePwd;
    public final TextView resetPwdTip;
    public final TextView resetPwdTip2;
    public final RelativeLayout rlKeyDelete;
    private final ConstraintLayout rootView;
    public final ConstraintLayout setNewPwdCl;
    public final TextView tvClearPwd;
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
    public final TextView tvNewPwd;
    public final TextView tvOldPwd;
    public final TextView tvRight;
    public final TextView tvSetPwdTip;
    public final TextView tvTitle;
    public final TextView tvUpdatePwd;

    private ActivitySetPwdBinding(ConstraintLayout rootView, ImageView btnOk, ConstraintLayout changeOrCleanPwdCl, ConstraintLayout ilSettingsToolbar, PasswordEditText inputNewPwd, PasswordEditText inputOldPwd, PasswordEditText inputPwd, CustomImageView ivBack, View ivLine, View ivLine2, View ivLine3, CustomImageView ivMiddle, CustomImageView ivRight, CustomImageView ivRightSubtitle, LinearLayout keyBoard, LinearLayout llClearPwd, LinearLayout llUpdatePwd, TextView resetPwdTip, TextView resetPwdTip2, RelativeLayout rlKeyDelete, ConstraintLayout setNewPwdCl, TextView tvClearPwd, TextView tvKey0, TextView tvKey1, TextView tvKey2, TextView tvKey3, TextView tvKey4, TextView tvKey5, TextView tvKey6, TextView tvKey7, TextView tvKey8, TextView tvKey9, TextView tvNewPwd, TextView tvOldPwd, TextView tvRight, TextView tvSetPwdTip, TextView tvTitle, TextView tvUpdatePwd) {
        this.rootView = rootView;
        this.btnOk = btnOk;
        this.changeOrCleanPwdCl = changeOrCleanPwdCl;
        this.ilSettingsToolbar = ilSettingsToolbar;
        this.inputNewPwd = inputNewPwd;
        this.inputOldPwd = inputOldPwd;
        this.inputPwd = inputPwd;
        this.ivBack = ivBack;
        this.ivLine = ivLine;
        this.ivLine2 = ivLine2;
        this.ivLine3 = ivLine3;
        this.ivMiddle = ivMiddle;
        this.ivRight = ivRight;
        this.ivRightSubtitle = ivRightSubtitle;
        this.keyBoard = keyBoard;
        this.llClearPwd = llClearPwd;
        this.llUpdatePwd = llUpdatePwd;
        this.resetPwdTip = resetPwdTip;
        this.resetPwdTip2 = resetPwdTip2;
        this.rlKeyDelete = rlKeyDelete;
        this.setNewPwdCl = setNewPwdCl;
        this.tvClearPwd = tvClearPwd;
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
        this.tvNewPwd = tvNewPwd;
        this.tvOldPwd = tvOldPwd;
        this.tvRight = tvRight;
        this.tvSetPwdTip = tvSetPwdTip;
        this.tvTitle = tvTitle;
        this.tvUpdatePwd = tvUpdatePwd;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySetPwdBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySetPwdBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_set_pwd, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivitySetPwdBinding bind(View rootView) {
        int i = R.id.btn_ok;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_ok);
        if (imageView != null) {
            i = R.id.change_or_clean_pwd_cl;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.change_or_clean_pwd_cl);
            if (constraintLayout != null) {
                i = R.id.il_settings_toolbar;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_settings_toolbar);
                if (constraintLayout2 != null) {
                    i = R.id.input_new_pwd;
                    PasswordEditText passwordEditText = (PasswordEditText) ViewBindings.findChildViewById(rootView, R.id.input_new_pwd);
                    if (passwordEditText != null) {
                        i = R.id.input_old_pwd;
                        PasswordEditText passwordEditText2 = (PasswordEditText) ViewBindings.findChildViewById(rootView, R.id.input_old_pwd);
                        if (passwordEditText2 != null) {
                            i = R.id.input_pwd;
                            PasswordEditText passwordEditText3 = (PasswordEditText) ViewBindings.findChildViewById(rootView, R.id.input_pwd);
                            if (passwordEditText3 != null) {
                                i = R.id.iv_back;
                                CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_back);
                                if (customImageView != null) {
                                    i = R.id.iv_line;
                                    View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.iv_line);
                                    if (findChildViewById != null) {
                                        i = R.id.iv_line2;
                                        View findChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.iv_line2);
                                        if (findChildViewById2 != null) {
                                            i = R.id.iv_line3;
                                            View findChildViewById3 = ViewBindings.findChildViewById(rootView, R.id.iv_line3);
                                            if (findChildViewById3 != null) {
                                                i = R.id.iv_middle;
                                                CustomImageView customImageView2 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_middle);
                                                if (customImageView2 != null) {
                                                    i = R.id.iv_right;
                                                    CustomImageView customImageView3 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right);
                                                    if (customImageView3 != null) {
                                                        i = R.id.iv_right_subtitle;
                                                        CustomImageView customImageView4 = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right_subtitle);
                                                        if (customImageView4 != null) {
                                                            i = R.id.key_board;
                                                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.key_board);
                                                            if (linearLayout != null) {
                                                                i = R.id.ll_clear_pwd;
                                                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_clear_pwd);
                                                                if (linearLayout2 != null) {
                                                                    i = R.id.ll_update_pwd;
                                                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_update_pwd);
                                                                    if (linearLayout3 != null) {
                                                                        i = R.id.reset_pwd_tip;
                                                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.reset_pwd_tip);
                                                                        if (textView != null) {
                                                                            i = R.id.reset_pwd_tip2;
                                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.reset_pwd_tip2);
                                                                            if (textView2 != null) {
                                                                                i = R.id.rl_key_delete;
                                                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_key_delete);
                                                                                if (relativeLayout != null) {
                                                                                    i = R.id.set_new_pwd_cl;
                                                                                    ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.set_new_pwd_cl);
                                                                                    if (constraintLayout3 != null) {
                                                                                        i = R.id.tv_clear_pwd;
                                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_clear_pwd);
                                                                                        if (textView3 != null) {
                                                                                            i = R.id.tv_key_0;
                                                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_0);
                                                                                            if (textView4 != null) {
                                                                                                i = R.id.tv_key_1;
                                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_1);
                                                                                                if (textView5 != null) {
                                                                                                    i = R.id.tv_key_2;
                                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_2);
                                                                                                    if (textView6 != null) {
                                                                                                        i = R.id.tv_key_3;
                                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_3);
                                                                                                        if (textView7 != null) {
                                                                                                            i = R.id.tv_key_4;
                                                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_4);
                                                                                                            if (textView8 != null) {
                                                                                                                i = R.id.tv_key_5;
                                                                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_5);
                                                                                                                if (textView9 != null) {
                                                                                                                    i = R.id.tv_key_6;
                                                                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_6);
                                                                                                                    if (textView10 != null) {
                                                                                                                        i = R.id.tv_key_7;
                                                                                                                        TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_7);
                                                                                                                        if (textView11 != null) {
                                                                                                                            i = R.id.tv_key_8;
                                                                                                                            TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_8);
                                                                                                                            if (textView12 != null) {
                                                                                                                                i = R.id.tv_key_9;
                                                                                                                                TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_key_9);
                                                                                                                                if (textView13 != null) {
                                                                                                                                    i = R.id.tv_new_pwd;
                                                                                                                                    TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_new_pwd);
                                                                                                                                    if (textView14 != null) {
                                                                                                                                        i = R.id.tv_old_pwd;
                                                                                                                                        TextView textView15 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_old_pwd);
                                                                                                                                        if (textView15 != null) {
                                                                                                                                            i = R.id.tv_right;
                                                                                                                                            TextView textView16 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_right);
                                                                                                                                            if (textView16 != null) {
                                                                                                                                                i = R.id.tv_set_pwd_tip;
                                                                                                                                                TextView textView17 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_set_pwd_tip);
                                                                                                                                                if (textView17 != null) {
                                                                                                                                                    i = R.id.tv_title;
                                                                                                                                                    TextView textView18 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                                                                                                                                                    if (textView18 != null) {
                                                                                                                                                        i = R.id.tv_update_pwd;
                                                                                                                                                        TextView textView19 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_update_pwd);
                                                                                                                                                        if (textView19 != null) {
                                                                                                                                                            return new ActivitySetPwdBinding((ConstraintLayout) rootView, imageView, constraintLayout, constraintLayout2, passwordEditText, passwordEditText2, passwordEditText3, customImageView, findChildViewById, findChildViewById2, findChildViewById3, customImageView2, customImageView3, customImageView4, linearLayout, linearLayout2, linearLayout3, textView, textView2, relativeLayout, constraintLayout3, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19);
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
