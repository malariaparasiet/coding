package com.wifiled.ipixels.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wifiled.ipixels.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PasswordEditText.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u001b\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0004\u0010\bJ\u0012\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u00060\u0019j\u0002`\u001aJ\u0014\u0010\u001b\u001a\u00020\u00172\n\u0010\u0018\u001a\u00060\u0019j\u0002`\u001aH\u0002R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/wifiled/ipixels/view/PasswordEditText;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mView", "Landroid/view/View;", "getMView", "()Landroid/view/View;", "setMView", "(Landroid/view/View;)V", "tv_set_pwd_1", "Landroid/widget/TextView;", "tv_set_pwd_2", "tv_set_pwd_3", "tv_set_pwd_4", "tv_set_pwd_5", "tv_set_pwd_6", "initData", "", "pwdSB", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "tvPwdStrSetData", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PasswordEditText extends RelativeLayout {
    private View mView;
    private TextView tv_set_pwd_1;
    private TextView tv_set_pwd_2;
    private TextView tv_set_pwd_3;
    private TextView tv_set_pwd_4;
    private TextView tv_set_pwd_5;
    private TextView tv_set_pwd_6;

    public final View getMView() {
        return this.mView;
    }

    public final void setMView(View view) {
        this.mView = view;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PasswordEditText(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PasswordEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_layout_password_edittext, (ViewGroup) this, true);
        this.mView = inflate;
        Intrinsics.checkNotNull(inflate);
        View findViewById = inflate.findViewById(R.id.tv_set_pwd_1);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.tv_set_pwd_1 = (TextView) findViewById;
        View view = this.mView;
        Intrinsics.checkNotNull(view);
        View findViewById2 = view.findViewById(R.id.tv_set_pwd_2);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.tv_set_pwd_2 = (TextView) findViewById2;
        View view2 = this.mView;
        Intrinsics.checkNotNull(view2);
        View findViewById3 = view2.findViewById(R.id.tv_set_pwd_3);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.tv_set_pwd_3 = (TextView) findViewById3;
        View view3 = this.mView;
        Intrinsics.checkNotNull(view3);
        View findViewById4 = view3.findViewById(R.id.tv_set_pwd_4);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.tv_set_pwd_4 = (TextView) findViewById4;
        View view4 = this.mView;
        Intrinsics.checkNotNull(view4);
        View findViewById5 = view4.findViewById(R.id.tv_set_pwd_5);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.tv_set_pwd_5 = (TextView) findViewById5;
        View view5 = this.mView;
        Intrinsics.checkNotNull(view5);
        View findViewById6 = view5.findViewById(R.id.tv_set_pwd_6);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.tv_set_pwd_6 = (TextView) findViewById6;
    }

    public final void initData(StringBuilder pwdSB) {
        Intrinsics.checkNotNullParameter(pwdSB, "pwdSB");
        tvPwdStrSetData(pwdSB);
    }

    private final void tvPwdStrSetData(StringBuilder pwdSB) {
        TextView textView = null;
        if (pwdSB.length() == 0) {
            TextView textView2 = this.tv_set_pwd_1;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_1");
                textView2 = null;
            }
            textView2.setText("");
            TextView textView3 = this.tv_set_pwd_2;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_2");
                textView3 = null;
            }
            textView3.setText("");
            TextView textView4 = this.tv_set_pwd_3;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_3");
                textView4 = null;
            }
            textView4.setText("");
            TextView textView5 = this.tv_set_pwd_4;
            if (textView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_4");
                textView5 = null;
            }
            textView5.setText("");
            TextView textView6 = this.tv_set_pwd_5;
            if (textView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_5");
                textView6 = null;
            }
            textView6.setText("");
            TextView textView7 = this.tv_set_pwd_6;
            if (textView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_6");
            } else {
                textView = textView7;
            }
            textView.setText("");
        }
        switch (pwdSB.length()) {
            case 1:
                TextView textView8 = this.tv_set_pwd_1;
                if (textView8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_1");
                    textView8 = null;
                }
                textView8.setText(String.valueOf(pwdSB.charAt(0)));
                TextView textView9 = this.tv_set_pwd_2;
                if (textView9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_2");
                    textView9 = null;
                }
                textView9.setText("");
                TextView textView10 = this.tv_set_pwd_3;
                if (textView10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_3");
                    textView10 = null;
                }
                textView10.setText("");
                TextView textView11 = this.tv_set_pwd_4;
                if (textView11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_4");
                    textView11 = null;
                }
                textView11.setText("");
                TextView textView12 = this.tv_set_pwd_5;
                if (textView12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_5");
                    textView12 = null;
                }
                textView12.setText("");
                TextView textView13 = this.tv_set_pwd_6;
                if (textView13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_6");
                } else {
                    textView = textView13;
                }
                textView.setText("");
                break;
            case 2:
                TextView textView14 = this.tv_set_pwd_1;
                if (textView14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_1");
                    textView14 = null;
                }
                textView14.setText(String.valueOf(pwdSB.charAt(0)));
                TextView textView15 = this.tv_set_pwd_2;
                if (textView15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_2");
                    textView15 = null;
                }
                textView15.setText(String.valueOf(pwdSB.charAt(1)));
                TextView textView16 = this.tv_set_pwd_3;
                if (textView16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_3");
                    textView16 = null;
                }
                textView16.setText("");
                TextView textView17 = this.tv_set_pwd_4;
                if (textView17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_4");
                    textView17 = null;
                }
                textView17.setText("");
                TextView textView18 = this.tv_set_pwd_5;
                if (textView18 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_5");
                    textView18 = null;
                }
                textView18.setText("");
                TextView textView19 = this.tv_set_pwd_6;
                if (textView19 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_6");
                } else {
                    textView = textView19;
                }
                textView.setText("");
                break;
            case 3:
                TextView textView20 = this.tv_set_pwd_1;
                if (textView20 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_1");
                    textView20 = null;
                }
                textView20.setText(String.valueOf(pwdSB.charAt(0)));
                TextView textView21 = this.tv_set_pwd_2;
                if (textView21 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_2");
                    textView21 = null;
                }
                textView21.setText(String.valueOf(pwdSB.charAt(1)));
                TextView textView22 = this.tv_set_pwd_3;
                if (textView22 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_3");
                    textView22 = null;
                }
                textView22.setText(String.valueOf(pwdSB.charAt(2)));
                TextView textView23 = this.tv_set_pwd_4;
                if (textView23 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_4");
                    textView23 = null;
                }
                textView23.setText("");
                TextView textView24 = this.tv_set_pwd_5;
                if (textView24 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_5");
                    textView24 = null;
                }
                textView24.setText("");
                TextView textView25 = this.tv_set_pwd_6;
                if (textView25 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_6");
                } else {
                    textView = textView25;
                }
                textView.setText("");
                break;
            case 4:
                TextView textView26 = this.tv_set_pwd_1;
                if (textView26 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_1");
                    textView26 = null;
                }
                textView26.setText(String.valueOf(pwdSB.charAt(0)));
                TextView textView27 = this.tv_set_pwd_2;
                if (textView27 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_2");
                    textView27 = null;
                }
                textView27.setText(String.valueOf(pwdSB.charAt(1)));
                TextView textView28 = this.tv_set_pwd_3;
                if (textView28 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_3");
                    textView28 = null;
                }
                textView28.setText(String.valueOf(pwdSB.charAt(2)));
                TextView textView29 = this.tv_set_pwd_4;
                if (textView29 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_4");
                    textView29 = null;
                }
                textView29.setText(String.valueOf(pwdSB.charAt(3)));
                TextView textView30 = this.tv_set_pwd_5;
                if (textView30 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_5");
                    textView30 = null;
                }
                textView30.setText("");
                TextView textView31 = this.tv_set_pwd_6;
                if (textView31 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_6");
                } else {
                    textView = textView31;
                }
                textView.setText("");
                break;
            case 5:
                TextView textView32 = this.tv_set_pwd_1;
                if (textView32 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_1");
                    textView32 = null;
                }
                textView32.setText(String.valueOf(pwdSB.charAt(0)));
                TextView textView33 = this.tv_set_pwd_2;
                if (textView33 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_2");
                    textView33 = null;
                }
                textView33.setText(String.valueOf(pwdSB.charAt(1)));
                TextView textView34 = this.tv_set_pwd_3;
                if (textView34 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_3");
                    textView34 = null;
                }
                textView34.setText(String.valueOf(pwdSB.charAt(2)));
                TextView textView35 = this.tv_set_pwd_4;
                if (textView35 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_4");
                    textView35 = null;
                }
                textView35.setText(String.valueOf(pwdSB.charAt(3)));
                TextView textView36 = this.tv_set_pwd_5;
                if (textView36 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_5");
                    textView36 = null;
                }
                textView36.setText(String.valueOf(pwdSB.charAt(4)));
                TextView textView37 = this.tv_set_pwd_6;
                if (textView37 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_6");
                } else {
                    textView = textView37;
                }
                textView.setText("");
                break;
            case 6:
                TextView textView38 = this.tv_set_pwd_1;
                if (textView38 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_1");
                    textView38 = null;
                }
                textView38.setText(String.valueOf(pwdSB.charAt(0)));
                TextView textView39 = this.tv_set_pwd_2;
                if (textView39 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_2");
                    textView39 = null;
                }
                textView39.setText(String.valueOf(pwdSB.charAt(1)));
                TextView textView40 = this.tv_set_pwd_3;
                if (textView40 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_3");
                    textView40 = null;
                }
                textView40.setText(String.valueOf(pwdSB.charAt(2)));
                TextView textView41 = this.tv_set_pwd_4;
                if (textView41 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_4");
                    textView41 = null;
                }
                textView41.setText(String.valueOf(pwdSB.charAt(3)));
                TextView textView42 = this.tv_set_pwd_5;
                if (textView42 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_5");
                    textView42 = null;
                }
                textView42.setText(String.valueOf(pwdSB.charAt(4)));
                TextView textView43 = this.tv_set_pwd_6;
                if (textView43 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_set_pwd_6");
                } else {
                    textView = textView43;
                }
                textView.setText(String.valueOf(pwdSB.charAt(5)));
                break;
        }
    }
}
