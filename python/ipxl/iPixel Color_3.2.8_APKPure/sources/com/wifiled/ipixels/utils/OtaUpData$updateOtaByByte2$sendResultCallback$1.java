package com.wifiled.ipixels.utils;

import android.content.Context;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.utils.ThreadUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.send.SendResultCallback;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OtaUpData.kt */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/wifiled/ipixels/utils/OtaUpData$updateOtaByByte2$sendResultCallback$1", "Lcom/wifiled/ipixels/core/send/SendResultCallback;", "onResult", "", "result", "", "onError", "errorCode", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OtaUpData$updateOtaByByte2$sendResultCallback$1 implements SendResultCallback {
    final /* synthetic */ SendCore.CallbackBuilder $callback;
    final /* synthetic */ Context $tContext;

    OtaUpData$updateOtaByByte2$sendResultCallback$1(SendCore.CallbackBuilder callbackBuilder, Context context) {
        this.$callback = callbackBuilder;
        this.$tContext = context;
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onResult(byte[] result) {
        Intrinsics.checkNotNullParameter(result, "result");
        LogUtils.vTag("ruis", "upDataOTA2900Start sendResultCallback--" + com.wifiled.blelibrary.ble.utils.ByteUtils.toHexString(result));
        if (result.length == 12) {
            ThreadUtils.delay(30L);
            new OTAHandler(this.$callback).dealOtaForMCU(result);
        }
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onError(int errorCode) {
        final Context context = this.$tContext;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.utils.OtaUpData$updateOtaByByte2$sendResultCallback$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onError$lambda$0;
                onError$lambda$0 = OtaUpData$updateOtaByByte2$sendResultCallback$1.onError$lambda$0(context);
                return onError$lambda$0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onError$lambda$0(Context context) {
        ToastUtil.show(String.valueOf(context.getString(R.string.msg_send_fail)));
        return Unit.INSTANCE;
    }
}
