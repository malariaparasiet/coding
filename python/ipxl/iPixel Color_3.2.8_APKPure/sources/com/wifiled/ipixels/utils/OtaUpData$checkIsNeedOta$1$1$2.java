package com.wifiled.ipixels.utils;

import android.content.Context;
import com.blankj.utilcode.util.ToastUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.ota.BleSOTAData;
import com.wifiled.ipixels.ota.OTARequest;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: OtaUpData.kt */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/wifiled/ipixels/utils/OtaUpData$checkIsNeedOta$1$1$2", "Lcom/wifiled/ipixels/ota/BleSOTAData;", "sendData", "", "item", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OtaUpData$checkIsNeedOta$1$1$2 implements BleSOTAData {
    final /* synthetic */ Ref.ObjectRef<Context> $tContext;

    OtaUpData$checkIsNeedOta$1$1$2(Ref.ObjectRef<Context> objectRef) {
        this.$tContext = objectRef;
    }

    @Override // com.wifiled.ipixels.ota.BleSOTAData
    public boolean sendData(byte[] item) {
        Intrinsics.checkNotNullParameter(item, "item");
        AppConfig.INSTANCE.setBCheckOta(true);
        OtaUpData otaUpData = OtaUpData.INSTANCE;
        Context context = this.$tContext.element;
        final Ref.ObjectRef<Context> objectRef = this.$tContext;
        otaUpData.updateOtaByByte2(item, context, new Function1() { // from class: com.wifiled.ipixels.utils.OtaUpData$checkIsNeedOta$1$1$2$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData$lambda$8;
                sendData$lambda$8 = OtaUpData$checkIsNeedOta$1$1$2.sendData$lambda$8(Ref.ObjectRef.this, (SendCore.CallbackBuilder) obj);
                return sendData$lambda$8;
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$8(final Ref.ObjectRef objectRef, SendCore.CallbackBuilder updateOtaByByte2) {
        Intrinsics.checkNotNullParameter(updateOtaByByte2, "$this$updateOtaByByte2");
        updateOtaByByte2.onStart(new Function0() { // from class: com.wifiled.ipixels.utils.OtaUpData$checkIsNeedOta$1$1$2$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit unit;
                unit = Unit.INSTANCE;
                return unit;
            }
        });
        updateOtaByByte2.onProgress(new Function1() { // from class: com.wifiled.ipixels.utils.OtaUpData$checkIsNeedOta$1$1$2$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData$lambda$8$lambda$2;
                sendData$lambda$8$lambda$2 = OtaUpData$checkIsNeedOta$1$1$2.sendData$lambda$8$lambda$2(Ref.ObjectRef.this, ((Integer) obj).intValue());
                return sendData$lambda$8$lambda$2;
            }
        });
        updateOtaByByte2.onSuccess(new Function0() { // from class: com.wifiled.ipixels.utils.OtaUpData$checkIsNeedOta$1$1$2$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$8$lambda$4;
                sendData$lambda$8$lambda$4 = OtaUpData$checkIsNeedOta$1$1$2.sendData$lambda$8$lambda$4(Ref.ObjectRef.this);
                return sendData$lambda$8$lambda$4;
            }
        });
        updateOtaByByte2.onError(new Function1() { // from class: com.wifiled.ipixels.utils.OtaUpData$checkIsNeedOta$1$1$2$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData$lambda$8$lambda$6;
                sendData$lambda$8$lambda$6 = OtaUpData$checkIsNeedOta$1$1$2.sendData$lambda$8$lambda$6(Ref.ObjectRef.this, ((Integer) obj).intValue());
                return sendData$lambda$8$lambda$6;
            }
        });
        updateOtaByByte2.onCompleted(new Function0() { // from class: com.wifiled.ipixels.utils.OtaUpData$checkIsNeedOta$1$1$2$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$8$lambda$7;
                sendData$lambda$8$lambda$7 = OtaUpData$checkIsNeedOta$1$1$2.sendData$lambda$8$lambda$7(Ref.ObjectRef.this);
                return sendData$lambda$8$lambda$7;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$8$lambda$2(final Ref.ObjectRef objectRef, int i) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.utils.OtaUpData$checkIsNeedOta$1$1$2$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$8$lambda$2$lambda$1;
                sendData$lambda$8$lambda$2$lambda$1 = OtaUpData$checkIsNeedOta$1$1$2.sendData$lambda$8$lambda$2$lambda$1(Ref.ObjectRef.this);
                return sendData$lambda$8$lambda$2$lambda$1;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData$lambda$8$lambda$2$lambda$1(Ref.ObjectRef objectRef) {
        UtilsExtensionKt.showLoadingDialog((Context) objectRef.element, true, String.valueOf(((Context) objectRef.element).getString(R.string.msg_ota_upd_mcu)), true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$8$lambda$4(final Ref.ObjectRef objectRef) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.utils.OtaUpData$checkIsNeedOta$1$1$2$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$8$lambda$4$lambda$3;
                sendData$lambda$8$lambda$4$lambda$3 = OtaUpData$checkIsNeedOta$1$1$2.sendData$lambda$8$lambda$4$lambda$3(Ref.ObjectRef.this);
                return sendData$lambda$8$lambda$4$lambda$3;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData$lambda$8$lambda$4$lambda$3(Ref.ObjectRef objectRef) {
        ToastUtils.showLong(String.valueOf(((Context) objectRef.element).getString(R.string.ota_upd_success)), new Object[0]);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$8$lambda$6(final Ref.ObjectRef objectRef, int i) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.utils.OtaUpData$checkIsNeedOta$1$1$2$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$8$lambda$6$lambda$5;
                sendData$lambda$8$lambda$6$lambda$5 = OtaUpData$checkIsNeedOta$1$1$2.sendData$lambda$8$lambda$6$lambda$5(Ref.ObjectRef.this);
                return sendData$lambda$8$lambda$6$lambda$5;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData$lambda$8$lambda$6$lambda$5(Ref.ObjectRef objectRef) {
        ToastUtil.show(String.valueOf(((Context) objectRef.element).getString(R.string.fail)));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData$lambda$8$lambda$7(Ref.ObjectRef objectRef) {
        UtilsExtensionKt.showLoadingDialog$default((Context) objectRef.element, false, (String) null, false, 12, (Object) null);
        OTARequest.INSTANCE.unRegister();
        return Unit.INSTANCE;
    }
}
