package com.wifiled.ipixels.ui.settings;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProvider;
import com.blankj.utilcode.util.SPUtils;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.uicode.base.UiBaseActivity;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.Constants;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.BleManager;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.send.SendResultCallback;
import com.wifiled.ipixels.databinding.ActivitySetPwdBinding;
import com.wifiled.ipixels.ui.model.SetPwdModel;
import com.wifiled.ipixels.view.customview.CustomImageView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SetPwdActivity.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 72\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00042\u00020\u0005:\u00017B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0003H\u0016J\b\u0010\u001d\u001a\u00020\u0019H\u0016J\b\u0010\u001e\u001a\u00020\u0002H\u0016J\b\u0010\u001f\u001a\u00020\u0019H\u0002J\b\u0010 \u001a\u00020\u0019H\u0002J\u0012\u0010!\u001a\u00020\u00192\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0010\u0010$\u001a\u00020\u00192\u0006\u0010%\u001a\u00020\u000bH\u0002J\b\u0010,\u001a\u00020\u0019H\u0002J\u0014\u0010-\u001a\u00020\u00192\n\u0010.\u001a\u00060\rj\u0002`\u000eH\u0002J\u0010\u0010/\u001a\u00020\u00192\u0006\u00100\u001a\u00020\tH\u0002J\u0010\u00101\u001a\u00020\u00192\u0006\u00100\u001a\u00020\tH\u0002J\u0010\u00102\u001a\u00020\u00192\u0006\u00100\u001a\u00020\tH\u0002J\u0010\u00103\u001a\u00020\u00192\u0006\u00100\u001a\u00020\tH\u0002J\u001c\u00104\u001a\u00020\u00172\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u00105\u001a\u0004\u0018\u000106H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00060\rj\u0002`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00060\rj\u0002`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00060\rj\u0002`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u00060\rj\u0002`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u00020'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+¨\u00068"}, d2 = {"Lcom/wifiled/ipixels/ui/settings/SetPwdActivity;", "Lcom/wifiled/baselib/uicode/base/UiBaseActivity;", "Lcom/wifiled/ipixels/ui/model/SetPwdModel;", "Lcom/wifiled/ipixels/databinding/ActivitySetPwdBinding;", "Landroid/view/View$OnClickListener;", "Landroid/view/View$OnTouchListener;", "<init>", "()V", "mSelectFlag", "", "pwdStr1", "", "sb1", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "pwdStr2", "sb2", "pwdStr3", "sb3", "pwdStr4", "sb4", "curPwd", "mIsCleanPwd", "", "initView", "", "savedInstanceState", "Landroid/os/Bundle;", "getViewBinding", "initData", "initViewModel", "initToolBar", "setView", "onClick", "v", "Landroid/view/View;", "sendPwd", "pwd", "sendResultCallback", "Lcom/wifiled/ipixels/core/send/SendResultCallback;", "getSendResultCallback", "()Lcom/wifiled/ipixels/core/send/SendResultCallback;", "setSendResultCallback", "(Lcom/wifiled/ipixels/core/send/SendResultCallback;)V", "cleanAllData", "tvPwdStrSetData", "pwdSB", "onKeyClick", "num", "onKeyClickNew", "onKeyClickClear", "onKeyClickSetPwd", "onTouch", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SetPwdActivity extends UiBaseActivity<SetPwdModel, ActivitySetPwdBinding> implements View.OnClickListener, View.OnTouchListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String curPwd;
    private boolean mIsCleanPwd;
    private int mSelectFlag = 3;
    private String pwdStr1 = "";
    private final StringBuilder sb1 = new StringBuilder();
    private String pwdStr2 = "";
    private final StringBuilder sb2 = new StringBuilder();
    private String pwdStr3 = "";
    private final StringBuilder sb3 = new StringBuilder();
    private String pwdStr4 = "";
    private final StringBuilder sb4 = new StringBuilder();
    private SendResultCallback sendResultCallback = new SetPwdActivity$sendResultCallback$1(this);

    /* compiled from: SetPwdActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/wifiled/ipixels/ui/settings/SetPwdActivity$Companion;", "", "<init>", "()V", "start", "", "context", "Landroid/content/Context;", "type", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, int type) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, (Class<?>) SetPwdActivity.class);
            intent.putExtra(Constants.FLAG, type);
            context.startActivity(intent);
        }
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity, com.wifiled.baselib.uicode.inner.IBaseView
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initToolBar();
        setView();
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity
    public ActivitySetPwdBinding getViewBinding() {
        ActivitySetPwdBinding inflate = ActivitySetPwdBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return inflate;
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity, com.wifiled.baselib.uicode.inner.IBaseView
    public void initData() {
        super.initData();
        int intExtra = getIntent().getIntExtra(Constants.FLAG, 3);
        this.mSelectFlag = intExtra;
        if (intExtra == 3) {
            getBinding().setNewPwdCl.setVisibility(0);
            getBinding().changeOrCleanPwdCl.setVisibility(8);
        } else {
            getBinding().setNewPwdCl.setVisibility(8);
            getBinding().changeOrCleanPwdCl.setVisibility(0);
        }
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity
    public SetPwdModel initViewModel() {
        return (SetPwdModel) new ViewModelProvider(this).get(SetPwdModel.class);
    }

    private final void initToolBar() {
        getBinding().tvTitle.setText(getString(R.string.set_pwd));
        CustomImageView ivRight = getBinding().ivRight;
        Intrinsics.checkNotNullExpressionValue(ivRight, "ivRight");
        UtilsExtensionKt.hide(ivRight);
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        if (StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "ar", false, 2, (Object) null)) {
            getBinding().ivBack.setBackgroundResource(R.mipmap.icon_back_ar);
        } else {
            getBinding().ivBack.setBackgroundResource(R.mipmap.icon_back);
        }
    }

    private final void setView() {
        SetPwdActivity setPwdActivity = this;
        getBinding().ivBack.setOnClickListener(setPwdActivity);
        getBinding().tvKey1.setOnClickListener(setPwdActivity);
        getBinding().tvKey2.setOnClickListener(setPwdActivity);
        getBinding().tvKey3.setOnClickListener(setPwdActivity);
        getBinding().tvKey4.setOnClickListener(setPwdActivity);
        getBinding().tvKey5.setOnClickListener(setPwdActivity);
        getBinding().tvKey6.setOnClickListener(setPwdActivity);
        getBinding().tvKey7.setOnClickListener(setPwdActivity);
        getBinding().tvKey8.setOnClickListener(setPwdActivity);
        getBinding().tvKey9.setOnClickListener(setPwdActivity);
        getBinding().tvKey0.setOnClickListener(setPwdActivity);
        SetPwdActivity setPwdActivity2 = this;
        getBinding().inputOldPwd.setOnTouchListener(setPwdActivity2);
        getBinding().inputNewPwd.setOnTouchListener(setPwdActivity2);
        getBinding().rlKeyDelete.setOnClickListener(setPwdActivity);
        getBinding().llClearPwd.setOnClickListener(setPwdActivity);
        getBinding().llUpdatePwd.setOnClickListener(setPwdActivity);
        getBinding().btnOk.setOnClickListener(setPwdActivity);
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView ivBack = getBinding().ivBack;
        Intrinsics.checkNotNullExpressionValue(ivBack, "ivBack");
        companion.attachViewOnTouchListener(ivBack);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (Intrinsics.areEqual(v, getBinding().btnOk)) {
            Log.v("ruis", "mSelectFlag-----" + this.mSelectFlag);
            int i = this.mSelectFlag;
            if (i != 0 && i != 1) {
                if (i == 2) {
                    if (TextUtils.isEmpty(this.pwdStr3)) {
                        ToastUtil.show(getString(R.string.Please_enter_password));
                        return;
                    } else if (this.pwdStr3.length() != 6) {
                        ToastUtil.show(getString(R.string.pwd_leng_error));
                        return;
                    } else {
                        sendPwd(this.pwdStr3);
                        return;
                    }
                }
                if (i != 3) {
                    return;
                }
                if (TextUtils.isEmpty(this.pwdStr4)) {
                    ToastUtil.show(getString(R.string.Please_enter_password));
                    return;
                } else if (this.pwdStr4.length() != 6) {
                    ToastUtil.show(getString(R.string.pwd_leng_error));
                    return;
                } else {
                    sendPwd(this.pwdStr4);
                    return;
                }
            }
            if (TextUtils.isEmpty(this.pwdStr1)) {
                ToastUtil.show(getString(R.string.input_old_pwd));
                return;
            }
            if (this.pwdStr1.length() != 6) {
                ToastUtil.show(getString(R.string.pwd_leng_error));
                return;
            }
            if (TextUtils.isEmpty(this.pwdStr2)) {
                ToastUtil.show(getString(R.string.please_input_new_pwd));
                return;
            }
            if (this.pwdStr2.length() != 6) {
                ToastUtil.show(getString(R.string.pwd_leng_error));
                return;
            }
            BleDevice connectedDevice = BleManager.INSTANCE.get().getConnectedDevice();
            if (connectedDevice == null) {
                ToastUtil.show(getString(R.string.msg_dev_connect_null));
                return;
            }
            if (SPUtils.getInstance().contains(connectedDevice.getBleAddress())) {
                String string = SPUtils.getInstance().getString(connectedDevice.getBleAddress());
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                if (!Intrinsics.areEqual(this.pwdStr1, string)) {
                    ToastUtil.show(getString(R.string.Incorrect_password));
                    return;
                }
            }
            sendPwd(this.pwdStr2);
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().llClearPwd)) {
            getBinding().tvUpdatePwd.setTextColor(Color.parseColor("#BBBBBB"));
            getBinding().ivLine.setVisibility(4);
            getBinding().tvClearPwd.setTextColor(Color.parseColor("#FFCC00"));
            getBinding().ivLine2.setVisibility(0);
            getBinding().resetPwdTip.setVisibility(8);
            getBinding().resetPwdTip2.setVisibility(0);
            getBinding().tvNewPwd.setVisibility(8);
            getBinding().inputNewPwd.setVisibility(8);
            this.mSelectFlag = 2;
            this.mIsCleanPwd = true;
            cleanAllData();
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().llUpdatePwd)) {
            getBinding().tvUpdatePwd.setTextColor(Color.parseColor("#FFCC00"));
            getBinding().ivLine.setVisibility(0);
            getBinding().tvClearPwd.setTextColor(Color.parseColor("#BBBBBB"));
            getBinding().ivLine2.setVisibility(4);
            getBinding().resetPwdTip.setVisibility(0);
            getBinding().resetPwdTip2.setVisibility(8);
            getBinding().tvNewPwd.setVisibility(0);
            getBinding().inputNewPwd.setVisibility(0);
            this.mSelectFlag = 0;
            this.mIsCleanPwd = false;
            cleanAllData();
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().ivBack)) {
            finish();
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().tvKey1)) {
            int i2 = this.mSelectFlag;
            if (i2 == 0) {
                onKeyClick(1);
                return;
            }
            if (i2 == 1) {
                onKeyClickNew(1);
                return;
            } else if (i2 == 2) {
                onKeyClickClear(1);
                return;
            } else {
                if (i2 != 3) {
                    return;
                }
                onKeyClickSetPwd(1);
                return;
            }
        }
        if (Intrinsics.areEqual(v, getBinding().tvKey2)) {
            int i3 = this.mSelectFlag;
            if (i3 == 0) {
                onKeyClick(2);
                return;
            }
            if (i3 == 1) {
                onKeyClickNew(2);
                return;
            } else if (i3 == 2) {
                onKeyClickClear(2);
                return;
            } else {
                if (i3 != 3) {
                    return;
                }
                onKeyClickSetPwd(2);
                return;
            }
        }
        if (Intrinsics.areEqual(v, getBinding().tvKey3)) {
            int i4 = this.mSelectFlag;
            if (i4 == 0) {
                onKeyClick(3);
                return;
            }
            if (i4 == 1) {
                onKeyClickNew(3);
                return;
            } else if (i4 == 2) {
                onKeyClickClear(3);
                return;
            } else {
                if (i4 != 3) {
                    return;
                }
                onKeyClickSetPwd(3);
                return;
            }
        }
        if (Intrinsics.areEqual(v, getBinding().tvKey4)) {
            int i5 = this.mSelectFlag;
            if (i5 == 0) {
                onKeyClick(4);
                return;
            }
            if (i5 == 1) {
                onKeyClickNew(4);
                return;
            } else if (i5 == 2) {
                onKeyClickClear(4);
                return;
            } else {
                if (i5 != 3) {
                    return;
                }
                onKeyClickSetPwd(4);
                return;
            }
        }
        if (Intrinsics.areEqual(v, getBinding().tvKey5)) {
            int i6 = this.mSelectFlag;
            if (i6 == 0) {
                onKeyClick(5);
                return;
            }
            if (i6 == 1) {
                onKeyClickNew(5);
                return;
            } else if (i6 == 2) {
                onKeyClickClear(5);
                return;
            } else {
                if (i6 != 3) {
                    return;
                }
                onKeyClickSetPwd(5);
                return;
            }
        }
        if (Intrinsics.areEqual(v, getBinding().tvKey6)) {
            int i7 = this.mSelectFlag;
            if (i7 == 0) {
                onKeyClick(6);
                return;
            }
            if (i7 == 1) {
                onKeyClickNew(6);
                return;
            } else if (i7 == 2) {
                onKeyClickClear(6);
                return;
            } else {
                if (i7 != 3) {
                    return;
                }
                onKeyClickSetPwd(6);
                return;
            }
        }
        if (Intrinsics.areEqual(v, getBinding().tvKey7)) {
            int i8 = this.mSelectFlag;
            if (i8 == 0) {
                onKeyClick(7);
                return;
            }
            if (i8 == 1) {
                onKeyClickNew(7);
                return;
            } else if (i8 == 2) {
                onKeyClickClear(77);
                return;
            } else {
                if (i8 != 3) {
                    return;
                }
                onKeyClickSetPwd(7);
                return;
            }
        }
        if (Intrinsics.areEqual(v, getBinding().tvKey8)) {
            int i9 = this.mSelectFlag;
            if (i9 == 0) {
                onKeyClick(8);
                return;
            }
            if (i9 == 1) {
                onKeyClickNew(8);
                return;
            } else if (i9 == 2) {
                onKeyClickClear(8);
                return;
            } else {
                if (i9 != 3) {
                    return;
                }
                onKeyClickSetPwd(8);
                return;
            }
        }
        if (Intrinsics.areEqual(v, getBinding().tvKey9)) {
            int i10 = this.mSelectFlag;
            if (i10 == 0) {
                onKeyClick(9);
                return;
            }
            if (i10 == 1) {
                onKeyClickNew(9);
                return;
            } else if (i10 == 2) {
                onKeyClickClear(9);
                return;
            } else {
                if (i10 != 3) {
                    return;
                }
                onKeyClickSetPwd(9);
                return;
            }
        }
        if (Intrinsics.areEqual(v, getBinding().tvKey0)) {
            int i11 = this.mSelectFlag;
            if (i11 == 0) {
                onKeyClick(0);
                return;
            }
            if (i11 == 1) {
                onKeyClickNew(0);
                return;
            } else if (i11 == 2) {
                onKeyClickClear(0);
                return;
            } else {
                if (i11 != 3) {
                    return;
                }
                onKeyClickSetPwd(0);
                return;
            }
        }
        if (Intrinsics.areEqual(v, getBinding().rlKeyDelete)) {
            int i12 = this.mSelectFlag;
            if (i12 == 0) {
                onKeyClick(-1);
                return;
            }
            if (i12 == 1) {
                onKeyClickNew(-1);
            } else if (i12 == 2) {
                onKeyClickClear(-1);
            } else {
                if (i12 != 3) {
                    return;
                }
                onKeyClickSetPwd(-1);
            }
        }
    }

    private final void sendPwd(String pwd) {
        this.curPwd = pwd;
        BleDevice connectedDevice = BleManager.INSTANCE.get().getConnectedDevice();
        if (connectedDevice == null) {
            ToastUtil.show(getString(R.string.msg_dev_connect_null));
            return;
        }
        int i = this.mSelectFlag;
        if (i == 0 || i == 1) {
            SendCore.INSTANCE.setPwd(1, pwd, this.sendResultCallback);
            return;
        }
        if (i != 2) {
            if (i == 3) {
                SendCore.INSTANCE.setPwd(1, pwd, this.sendResultCallback);
            }
        } else {
            String string = SPUtils.getInstance().getString(connectedDevice.getBleAddress());
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            if (!Intrinsics.areEqual(pwd, string)) {
                ToastUtil.show("密码不正确");
            } else {
                SendCore.INSTANCE.setPwd(0, pwd, this.sendResultCallback);
            }
        }
    }

    public final SendResultCallback getSendResultCallback() {
        return this.sendResultCallback;
    }

    public final void setSendResultCallback(SendResultCallback sendResultCallback) {
        Intrinsics.checkNotNullParameter(sendResultCallback, "<set-?>");
        this.sendResultCallback = sendResultCallback;
    }

    private final void cleanAllData() {
        StringsKt.clear(this.sb1);
        StringsKt.clear(this.sb2);
        StringsKt.clear(this.sb3);
        StringsKt.clear(this.sb4);
        getBinding().inputOldPwd.initData(this.sb1);
        getBinding().inputNewPwd.initData(this.sb2);
        getBinding().inputPwd.initData(this.sb3);
        this.pwdStr1 = "";
        this.pwdStr2 = "";
        this.pwdStr3 = "";
        this.pwdStr4 = "";
    }

    private final void tvPwdStrSetData(StringBuilder pwdSB) {
        int i = this.mSelectFlag;
        if (i != 0) {
            if (i == 1) {
                getBinding().inputNewPwd.initData(pwdSB);
                return;
            } else if (i != 2) {
                if (i != 3) {
                    return;
                }
                getBinding().inputPwd.initData(pwdSB);
                return;
            }
        }
        getBinding().inputOldPwd.initData(pwdSB);
    }

    private final void onKeyClick(int num) {
        if (num >= 0) {
            if (this.sb1.length() < 6) {
                this.sb1.append(num);
            }
        } else if (this.sb1.length() > 0) {
            this.sb1.deleteCharAt(r3.length() - 1);
        }
        String sb = this.sb1.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
        this.pwdStr1 = sb;
        tvPwdStrSetData(this.sb1);
    }

    private final void onKeyClickNew(int num) {
        if (num >= 0) {
            if (this.sb2.length() < 6) {
                this.sb2.append(num);
            }
        } else if (this.sb2.length() > 0) {
            this.sb2.deleteCharAt(r3.length() - 1);
        }
        String sb = this.sb2.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
        this.pwdStr2 = sb;
        tvPwdStrSetData(this.sb2);
    }

    private final void onKeyClickClear(int num) {
        if (num >= 0) {
            if (this.sb3.length() < 6) {
                this.sb3.append(num);
            }
        } else if (this.sb3.length() > 0) {
            this.sb3.deleteCharAt(r3.length() - 1);
        }
        String sb = this.sb3.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
        this.pwdStr3 = sb;
        tvPwdStrSetData(this.sb3);
    }

    private final void onKeyClickSetPwd(int num) {
        if (num >= 0) {
            if (this.sb4.length() < 6) {
                this.sb4.append(num);
            }
        } else if (this.sb4.length() > 0) {
            this.sb4.deleteCharAt(r3.length() - 1);
        }
        String sb = this.sb4.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
        this.pwdStr4 = sb;
        tvPwdStrSetData(this.sb4);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        if (Intrinsics.areEqual(v, getBinding().inputOldPwd)) {
            if (this.mIsCleanPwd) {
                this.mSelectFlag = 2;
            } else {
                this.mSelectFlag = 0;
            }
            Intrinsics.checkNotNull(event);
            if (event.getAction() == 0) {
                getBinding().inputOldPwd.setAlpha(0.6f);
            } else if (event.getAction() == 1) {
                getBinding().inputOldPwd.setAlpha(1.0f);
            }
        } else if (Intrinsics.areEqual(v, getBinding().inputNewPwd)) {
            this.mSelectFlag = 1;
            Intrinsics.checkNotNull(event);
            if (event.getAction() == 0) {
                getBinding().inputNewPwd.setAlpha(0.6f);
            } else if (event.getAction() == 1) {
                getBinding().inputNewPwd.setAlpha(1.0f);
            }
        }
        return true;
    }
}
