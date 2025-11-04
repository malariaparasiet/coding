package com.wifiled.ipixels.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.wifiled.baselib.base.BaseDialog;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ConnectPwdDialog.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u00011B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010!\u001a\u00020\"H\u0014J\b\u0010#\u001a\u00020$H\u0014J\b\u0010%\u001a\u00020\"H\u0014J\u0010\u0010&\u001a\u00020\"2\u0006\u0010'\u001a\u00020$H\u0002J\u0012\u0010(\u001a\u00020\"2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u0010\u0010-\u001a\u00020\"2\b\u0010+\u001a\u0004\u0018\u00010,J\u0012\u0010.\u001a\u00020\"2\b\u0010/\u001a\u0004\u0018\u000100H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00060\nj\u0002`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/wifiled/ipixels/ui/dialog/ConnectPwdDialog;", "Lcom/wifiled/baselib/base/BaseDialog;", "Landroid/view/View$OnClickListener;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "pwdStr1", "", "sb1", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "tv_cancel", "Landroid/widget/TextView;", "tv_ok", "tv_key_0", "tv_key_1", "tv_key_2", "tv_key_3", "tv_key_4", "tv_key_5", "tv_key_6", "tv_key_7", "tv_key_8", "tv_key_9", "tv_set_pwd_1", "tv_set_pwd_2", "tv_set_pwd_3", "tv_set_pwd_4", "tv_set_pwd_5", "tv_set_pwd_6", "rl_key_delete", "Landroid/widget/RelativeLayout;", "bindData", "", "layoutId", "", "initView", "onKeyClick", "num", "setOnDismissListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/content/DialogInterface$OnDismissListener;", "clickListener", "Lcom/wifiled/ipixels/ui/dialog/ConnectPwdDialog$ClickListener;", "setClickListener", "onClick", "v", "Landroid/view/View;", "ClickListener", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ConnectPwdDialog extends BaseDialog implements View.OnClickListener {
    private ClickListener clickListener;
    private String pwdStr1;
    private RelativeLayout rl_key_delete;
    private final StringBuilder sb1;
    private TextView tv_cancel;
    private TextView tv_key_0;
    private TextView tv_key_1;
    private TextView tv_key_2;
    private TextView tv_key_3;
    private TextView tv_key_4;
    private TextView tv_key_5;
    private TextView tv_key_6;
    private TextView tv_key_7;
    private TextView tv_key_8;
    private TextView tv_key_9;
    private TextView tv_ok;
    private TextView tv_set_pwd_1;
    private TextView tv_set_pwd_2;
    private TextView tv_set_pwd_3;
    private TextView tv_set_pwd_4;
    private TextView tv_set_pwd_5;
    private TextView tv_set_pwd_6;

    /* compiled from: ConnectPwdDialog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/ui/dialog/ConnectPwdDialog$ClickListener;", "", "onCancel", "", "onOk", "pwd", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface ClickListener {
        void onCancel();

        void onOk(String pwd);
    }

    @Override // com.wifiled.baselib.base.BaseDialog
    protected int layoutId() {
        return R.layout.dialog_pwd;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConnectPwdDialog(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.pwdStr1 = "";
        this.sb1 = new StringBuilder();
    }

    @Override // com.wifiled.baselib.base.BaseDialog
    protected void bindData() {
        TextView textView = this.tv_cancel;
        RelativeLayout relativeLayout = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_cancel");
            textView = null;
        }
        ConnectPwdDialog connectPwdDialog = this;
        textView.setOnClickListener(connectPwdDialog);
        TextView textView2 = this.tv_ok;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_ok");
            textView2 = null;
        }
        textView2.setOnClickListener(connectPwdDialog);
        TextView textView3 = this.tv_key_0;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_key_0");
            textView3 = null;
        }
        textView3.setOnClickListener(connectPwdDialog);
        TextView textView4 = this.tv_key_1;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_key_1");
            textView4 = null;
        }
        textView4.setOnClickListener(connectPwdDialog);
        TextView textView5 = this.tv_key_2;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_key_2");
            textView5 = null;
        }
        textView5.setOnClickListener(connectPwdDialog);
        TextView textView6 = this.tv_key_3;
        if (textView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_key_3");
            textView6 = null;
        }
        textView6.setOnClickListener(connectPwdDialog);
        TextView textView7 = this.tv_key_4;
        if (textView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_key_4");
            textView7 = null;
        }
        textView7.setOnClickListener(connectPwdDialog);
        TextView textView8 = this.tv_key_5;
        if (textView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_key_5");
            textView8 = null;
        }
        textView8.setOnClickListener(connectPwdDialog);
        TextView textView9 = this.tv_key_6;
        if (textView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_key_6");
            textView9 = null;
        }
        textView9.setOnClickListener(connectPwdDialog);
        TextView textView10 = this.tv_key_7;
        if (textView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_key_7");
            textView10 = null;
        }
        textView10.setOnClickListener(connectPwdDialog);
        TextView textView11 = this.tv_key_8;
        if (textView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_key_8");
            textView11 = null;
        }
        textView11.setOnClickListener(connectPwdDialog);
        TextView textView12 = this.tv_key_9;
        if (textView12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_key_9");
            textView12 = null;
        }
        textView12.setOnClickListener(connectPwdDialog);
        RelativeLayout relativeLayout2 = this.rl_key_delete;
        if (relativeLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_key_delete");
        } else {
            relativeLayout = relativeLayout2;
        }
        relativeLayout.setOnClickListener(connectPwdDialog);
    }

    @Override // com.wifiled.baselib.base.BaseDialog
    protected void initView() {
        View findViewById = findViewById(R.id.tv_cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.tv_cancel = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.tv_ok);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.tv_ok = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.tv_key_0);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.tv_key_0 = (TextView) findViewById3;
        View findViewById4 = findViewById(R.id.tv_key_1);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.tv_key_1 = (TextView) findViewById4;
        View findViewById5 = findViewById(R.id.tv_key_2);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.tv_key_2 = (TextView) findViewById5;
        View findViewById6 = findViewById(R.id.tv_key_3);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.tv_key_3 = (TextView) findViewById6;
        View findViewById7 = findViewById(R.id.tv_key_4);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.tv_key_4 = (TextView) findViewById7;
        View findViewById8 = findViewById(R.id.tv_key_5);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.tv_key_5 = (TextView) findViewById8;
        View findViewById9 = findViewById(R.id.tv_key_6);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.tv_key_6 = (TextView) findViewById9;
        View findViewById10 = findViewById(R.id.tv_key_7);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
        this.tv_key_7 = (TextView) findViewById10;
        View findViewById11 = findViewById(R.id.tv_key_8);
        Intrinsics.checkNotNullExpressionValue(findViewById11, "findViewById(...)");
        this.tv_key_8 = (TextView) findViewById11;
        View findViewById12 = findViewById(R.id.tv_key_9);
        Intrinsics.checkNotNullExpressionValue(findViewById12, "findViewById(...)");
        this.tv_key_9 = (TextView) findViewById12;
        View findViewById13 = findViewById(R.id.tv_set_pwd_1);
        Intrinsics.checkNotNullExpressionValue(findViewById13, "findViewById(...)");
        this.tv_set_pwd_1 = (TextView) findViewById13;
        View findViewById14 = findViewById(R.id.tv_set_pwd_2);
        Intrinsics.checkNotNullExpressionValue(findViewById14, "findViewById(...)");
        this.tv_set_pwd_2 = (TextView) findViewById14;
        View findViewById15 = findViewById(R.id.tv_set_pwd_3);
        Intrinsics.checkNotNullExpressionValue(findViewById15, "findViewById(...)");
        this.tv_set_pwd_3 = (TextView) findViewById15;
        View findViewById16 = findViewById(R.id.tv_set_pwd_4);
        Intrinsics.checkNotNullExpressionValue(findViewById16, "findViewById(...)");
        this.tv_set_pwd_4 = (TextView) findViewById16;
        View findViewById17 = findViewById(R.id.tv_set_pwd_5);
        Intrinsics.checkNotNullExpressionValue(findViewById17, "findViewById(...)");
        this.tv_set_pwd_5 = (TextView) findViewById17;
        View findViewById18 = findViewById(R.id.tv_set_pwd_6);
        Intrinsics.checkNotNullExpressionValue(findViewById18, "findViewById(...)");
        this.tv_set_pwd_6 = (TextView) findViewById18;
        View findViewById19 = findViewById(R.id.rl_key_delete);
        Intrinsics.checkNotNullExpressionValue(findViewById19, "findViewById(...)");
        this.rl_key_delete = (RelativeLayout) findViewById19;
    }

    private final void onKeyClick(int num) {
        TextView textView;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        if (num >= 0) {
            if (this.sb1.length() < 6) {
                this.sb1.append(new StringBuilder().append(num).toString());
            }
        } else if (this.sb1.length() > 0) {
            StringBuilder sb = this.sb1;
            sb.deleteCharAt(sb.length() - 1);
        }
        String sb2 = this.sb1.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        this.pwdStr1 = sb2;
        if (this.sb1.length() == 0) {
            TextView textView7 = this.tv_set_pwd_1;
            if (textView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_1");
                textView7 = null;
            }
            textView7.setText("");
            TextView textView8 = this.tv_set_pwd_2;
            if (textView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_2");
                textView8 = null;
            }
            textView8.setText("");
            TextView textView9 = this.tv_set_pwd_3;
            if (textView9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_3");
                textView9 = null;
            }
            textView9.setText("");
            TextView textView10 = this.tv_set_pwd_4;
            if (textView10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_4");
                textView10 = null;
            }
            textView10.setText("");
            TextView textView11 = this.tv_set_pwd_5;
            if (textView11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_5");
                textView11 = null;
            }
            textView11.setText("");
            TextView textView12 = this.tv_set_pwd_6;
            if (textView12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_6");
                textView6 = null;
            } else {
                textView6 = textView12;
            }
            textView6.setText("");
            return;
        }
        if (this.pwdStr1.length() == 1) {
            TextView textView13 = this.tv_set_pwd_1;
            if (textView13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_1");
                textView13 = null;
            }
            textView13.setText(String.valueOf(this.pwdStr1.charAt(0)));
            TextView textView14 = this.tv_set_pwd_2;
            if (textView14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_2");
                textView14 = null;
            }
            textView14.setText("");
            TextView textView15 = this.tv_set_pwd_3;
            if (textView15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_3");
                textView15 = null;
            }
            textView15.setText("");
            TextView textView16 = this.tv_set_pwd_4;
            if (textView16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_4");
                textView16 = null;
            }
            textView16.setText("");
            TextView textView17 = this.tv_set_pwd_5;
            if (textView17 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_5");
                textView17 = null;
            }
            textView17.setText("");
            TextView textView18 = this.tv_set_pwd_6;
            if (textView18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_6");
                textView5 = null;
            } else {
                textView5 = textView18;
            }
            textView5.setText("");
            return;
        }
        if (this.pwdStr1.length() == 2) {
            TextView textView19 = this.tv_set_pwd_1;
            if (textView19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_1");
                textView19 = null;
            }
            textView19.setText(String.valueOf(this.pwdStr1.charAt(0)));
            TextView textView20 = this.tv_set_pwd_2;
            if (textView20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_2");
                textView20 = null;
            }
            textView20.setText(String.valueOf(this.pwdStr1.charAt(1)));
            TextView textView21 = this.tv_set_pwd_3;
            if (textView21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_3");
                textView21 = null;
            }
            textView21.setText("");
            TextView textView22 = this.tv_set_pwd_4;
            if (textView22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_4");
                textView22 = null;
            }
            textView22.setText("");
            TextView textView23 = this.tv_set_pwd_5;
            if (textView23 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_5");
                textView23 = null;
            }
            textView23.setText("");
            TextView textView24 = this.tv_set_pwd_6;
            if (textView24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_6");
                textView4 = null;
            } else {
                textView4 = textView24;
            }
            textView4.setText("");
            return;
        }
        if (this.pwdStr1.length() == 3) {
            TextView textView25 = this.tv_set_pwd_1;
            if (textView25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_1");
                textView25 = null;
            }
            textView25.setText(String.valueOf(this.pwdStr1.charAt(0)));
            TextView textView26 = this.tv_set_pwd_2;
            if (textView26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_2");
                textView26 = null;
            }
            textView26.setText(String.valueOf(this.pwdStr1.charAt(1)));
            TextView textView27 = this.tv_set_pwd_3;
            if (textView27 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_3");
                textView27 = null;
            }
            textView27.setText(String.valueOf(this.pwdStr1.charAt(2)));
            TextView textView28 = this.tv_set_pwd_4;
            if (textView28 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_4");
                textView28 = null;
            }
            textView28.setText("");
            TextView textView29 = this.tv_set_pwd_5;
            if (textView29 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_5");
                textView29 = null;
            }
            textView29.setText("");
            TextView textView30 = this.tv_set_pwd_6;
            if (textView30 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_6");
                textView3 = null;
            } else {
                textView3 = textView30;
            }
            textView3.setText("");
            return;
        }
        if (this.pwdStr1.length() == 4) {
            TextView textView31 = this.tv_set_pwd_1;
            if (textView31 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_1");
                textView31 = null;
            }
            textView31.setText(String.valueOf(this.pwdStr1.charAt(0)));
            TextView textView32 = this.tv_set_pwd_2;
            if (textView32 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_2");
                textView32 = null;
            }
            textView32.setText(String.valueOf(this.pwdStr1.charAt(1)));
            TextView textView33 = this.tv_set_pwd_3;
            if (textView33 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_3");
                textView33 = null;
            }
            textView33.setText(String.valueOf(this.pwdStr1.charAt(2)));
            TextView textView34 = this.tv_set_pwd_4;
            if (textView34 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_4");
                textView34 = null;
            }
            textView34.setText(String.valueOf(this.pwdStr1.charAt(3)));
            TextView textView35 = this.tv_set_pwd_5;
            if (textView35 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_5");
                textView35 = null;
            }
            textView35.setText("");
            TextView textView36 = this.tv_set_pwd_6;
            if (textView36 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_6");
                textView2 = null;
            } else {
                textView2 = textView36;
            }
            textView2.setText("");
            return;
        }
        if (this.pwdStr1.length() == 5) {
            TextView textView37 = this.tv_set_pwd_1;
            if (textView37 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_1");
                textView37 = null;
            }
            textView37.setText(String.valueOf(this.pwdStr1.charAt(0)));
            TextView textView38 = this.tv_set_pwd_2;
            if (textView38 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_2");
                textView38 = null;
            }
            textView38.setText(String.valueOf(this.pwdStr1.charAt(1)));
            TextView textView39 = this.tv_set_pwd_3;
            if (textView39 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_3");
                textView39 = null;
            }
            textView39.setText(String.valueOf(this.pwdStr1.charAt(2)));
            TextView textView40 = this.tv_set_pwd_4;
            if (textView40 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_4");
                textView40 = null;
            }
            textView40.setText(String.valueOf(this.pwdStr1.charAt(3)));
            TextView textView41 = this.tv_set_pwd_5;
            if (textView41 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_5");
                textView41 = null;
            }
            textView41.setText(String.valueOf(this.pwdStr1.charAt(4)));
            TextView textView42 = this.tv_set_pwd_6;
            if (textView42 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_6");
                textView = null;
            } else {
                textView = textView42;
            }
            textView.setText("");
            return;
        }
        if (this.pwdStr1.length() == 6) {
            TextView textView43 = this.tv_set_pwd_1;
            if (textView43 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_1");
                textView43 = null;
            }
            textView43.setText(String.valueOf(this.pwdStr1.charAt(0)));
            TextView textView44 = this.tv_set_pwd_2;
            if (textView44 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_2");
                textView44 = null;
            }
            textView44.setText(String.valueOf(this.pwdStr1.charAt(1)));
            TextView textView45 = this.tv_set_pwd_3;
            if (textView45 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_3");
                textView45 = null;
            }
            textView45.setText(String.valueOf(this.pwdStr1.charAt(2)));
            TextView textView46 = this.tv_set_pwd_4;
            if (textView46 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_4");
                textView46 = null;
            }
            textView46.setText(String.valueOf(this.pwdStr1.charAt(3)));
            TextView textView47 = this.tv_set_pwd_5;
            if (textView47 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_5");
                textView47 = null;
            }
            textView47.setText(String.valueOf(this.pwdStr1.charAt(4)));
            TextView textView48 = this.tv_set_pwd_6;
            if (textView48 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_6");
                textView48 = null;
            }
            textView48.setText(String.valueOf(this.pwdStr1.charAt(5)));
        }
    }

    @Override // android.app.Dialog
    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        super.setOnDismissListener(listener);
    }

    public final void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer valueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (valueOf != null && valueOf.intValue() == R.id.tv_cancel) {
            ClickListener clickListener = this.clickListener;
            Intrinsics.checkNotNull(clickListener);
            clickListener.onCancel();
            return;
        }
        if (valueOf != null && valueOf.intValue() == R.id.tv_ok) {
            if (TextUtils.isEmpty(this.pwdStr1)) {
                ToastUtil.show(this.mActivity.getString(R.string.input_old_pwd));
                return;
            }
            if (this.pwdStr1.length() != 6) {
                ToastUtil.show(this.mActivity.getString(R.string.pwd_leng_error));
                return;
            }
            ClickListener clickListener2 = this.clickListener;
            if (clickListener2 != null) {
                Intrinsics.checkNotNull(clickListener2);
                clickListener2.onOk(this.pwdStr1);
                return;
            }
            return;
        }
        if (valueOf != null && valueOf.intValue() == R.id.tv_key_0) {
            onKeyClick(0);
            return;
        }
        if (valueOf != null && valueOf.intValue() == R.id.tv_key_1) {
            onKeyClick(1);
            return;
        }
        if (valueOf != null && valueOf.intValue() == R.id.tv_key_2) {
            onKeyClick(2);
            return;
        }
        if (valueOf != null && valueOf.intValue() == R.id.tv_key_3) {
            onKeyClick(3);
            return;
        }
        if (valueOf != null && valueOf.intValue() == R.id.tv_key_4) {
            onKeyClick(4);
            return;
        }
        if (valueOf != null && valueOf.intValue() == R.id.tv_key_5) {
            onKeyClick(5);
            return;
        }
        if (valueOf != null && valueOf.intValue() == R.id.tv_key_6) {
            onKeyClick(6);
            return;
        }
        if (valueOf != null && valueOf.intValue() == R.id.tv_key_7) {
            onKeyClick(7);
            return;
        }
        if (valueOf != null && valueOf.intValue() == R.id.tv_key_8) {
            onKeyClick(8);
            return;
        }
        if (valueOf != null && valueOf.intValue() == R.id.tv_key_9) {
            onKeyClick(9);
        } else if (valueOf != null && valueOf.intValue() == R.id.rl_key_delete) {
            onKeyClick(-1);
        }
    }
}
