package com.wifiled.ipixels.utils;

import android.content.Context;
import com.wifiled.ipixels.AppConfig;
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
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/wifiled/ipixels/utils/OtaUpData$checkIsNeedOta$1$1$1", "Lcom/wifiled/ipixels/ota/BleSOTAData;", "sendData", "", "item", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OtaUpData$checkIsNeedOta$1$1$1 implements BleSOTAData {
    final /* synthetic */ Ref.ObjectRef<Context> $tContext;

    OtaUpData$checkIsNeedOta$1$1$1(Ref.ObjectRef<Context> objectRef) {
        this.$tContext = objectRef;
    }

    @Override // com.wifiled.ipixels.ota.BleSOTAData
    public boolean sendData(byte[] item) {
        Intrinsics.checkNotNullParameter(item, "item");
        AppConfig.INSTANCE.setBCheckOta(true);
        OtaUpData.INSTANCE.updateOtaByByte(item, this.$tContext.element, new Function1() { // from class: com.wifiled.ipixels.utils.OtaUpData$checkIsNeedOta$1$1$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData$lambda$2;
                sendData$lambda$2 = OtaUpData$checkIsNeedOta$1$1$1.sendData$lambda$2((SendCore.CallbackBuilder) obj);
                return sendData$lambda$2;
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$2(SendCore.CallbackBuilder updateOtaByByte) {
        Intrinsics.checkNotNullParameter(updateOtaByByte, "$this$updateOtaByByte");
        updateOtaByByte.onStart(new Function0() { // from class: com.wifiled.ipixels.utils.OtaUpData$checkIsNeedOta$1$1$1$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit unit;
                unit = Unit.INSTANCE;
                return unit;
            }
        });
        updateOtaByByte.onCompleted(new Function0() { // from class: com.wifiled.ipixels.utils.OtaUpData$checkIsNeedOta$1$1$1$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$2$lambda$1;
                sendData$lambda$2$lambda$1 = OtaUpData$checkIsNeedOta$1$1$1.sendData$lambda$2$lambda$1();
                return sendData$lambda$2$lambda$1;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$2$lambda$1() {
        OTARequest.INSTANCE.unRegister();
        return Unit.INSTANCE;
    }
}
