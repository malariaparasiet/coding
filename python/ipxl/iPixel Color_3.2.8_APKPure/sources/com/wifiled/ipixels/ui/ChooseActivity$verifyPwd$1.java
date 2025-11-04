package com.wifiled.ipixels.ui;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.blelibrary.ble.utils.ByteUtils;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.BleManager;
import com.wifiled.ipixels.core.send.SendResultCallback;
import com.wifiled.ipixels.ui.dialog.ConnectPwdDialog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChooseActivity.kt */
@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/wifiled/ipixels/ui/ChooseActivity$verifyPwd$1", "Lcom/wifiled/ipixels/core/send/SendResultCallback;", "onResult", "", "result", "", "onError", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChooseActivity$verifyPwd$1 implements SendResultCallback {
    final /* synthetic */ ChooseActivity this$0;

    ChooseActivity$verifyPwd$1(ChooseActivity chooseActivity) {
        this.this$0 = chooseActivity;
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onResult(byte[] result) {
        String str;
        ConnectPwdDialog connectPwdDialog;
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.length >= 5) {
            boolean z = false;
            if (result[0] == 5 && result[1] == 0 && result[2] == 5 && result[3] == 2) {
                LogUtils.vTag("ruis", "data " + ByteUtils.toHexString(result));
                LogUtils.vTag("ruis", "data[4].toInt() == 1 " + (result[4] == 1));
                ChooseActivity chooseActivity = this.this$0;
                if (result[4] == 1) {
                    LogUtils.vTag("ruis", "校验成功");
                    if (BleManager.INSTANCE.get().getConnectedDevice() != null || AppConfig.INSTANCE.getConnectType() == 0) {
                        SPUtils sPUtils = SPUtils.getInstance();
                        BleDevice connectedDevice = BleManager.INSTANCE.get().getConnectedDevice();
                        String valueOf = String.valueOf(connectedDevice != null ? connectedDevice.getBleAddress() : null);
                        str = this.this$0.mCurPwd;
                        sPUtils.put(valueOf, str);
                    }
                    final ChooseActivity chooseActivity2 = this.this$0;
                    UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.ChooseActivity$verifyPwd$1$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit onResult$lambda$0;
                            onResult$lambda$0 = ChooseActivity$verifyPwd$1.onResult$lambda$0(ChooseActivity.this);
                            return onResult$lambda$0;
                        }
                    });
                    connectPwdDialog = this.this$0.connectPwdDialog;
                    Intrinsics.checkNotNull(connectPwdDialog);
                    connectPwdDialog.cancel();
                    z = true;
                } else {
                    LogUtils.vTag("ruis", "校验失败");
                    final ChooseActivity chooseActivity3 = this.this$0;
                    UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.ChooseActivity$verifyPwd$1$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit onResult$lambda$1;
                            onResult$lambda$1 = ChooseActivity$verifyPwd$1.onResult$lambda$1(ChooseActivity.this);
                            return onResult$lambda$1;
                        }
                    });
                }
                chooseActivity.isInputPwd = z;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$0(ChooseActivity chooseActivity) {
        ToastUtil.show(chooseActivity.getString(R.string.verify_success));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$1(ChooseActivity chooseActivity) {
        ToastUtil.show(chooseActivity.getString(R.string.check_failure));
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onError(final int result) {
        final ChooseActivity chooseActivity = this.this$0;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.ChooseActivity$verifyPwd$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onError$lambda$2;
                onError$lambda$2 = ChooseActivity$verifyPwd$1.onError$lambda$2(ChooseActivity.this, result);
                return onError$lambda$2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onError$lambda$2(ChooseActivity chooseActivity, int i) {
        ToastUtil.show(chooseActivity.getString(R.string.msg_send_fail) + "(" + i + ")");
        return Unit.INSTANCE;
    }
}
