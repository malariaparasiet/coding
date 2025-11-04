package com.tiro.jlotalibrary;

import com.alibaba.fastjson2.JSONB;
import com.tiro.jlotalibrary.exposed.BleSOTAData;
import com.tiro.jlotalibrary.exposed.JLOTA;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: JLDialogActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.tiro.jlotalibrary.JLDialogActivity$btEventCallback$1$onMandatoryUpgrade$1", f = "JLDialogActivity.kt", i = {}, l = {215}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class JLDialogActivity$btEventCallback$1$onMandatoryUpgrade$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ JLDialogActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    JLDialogActivity$btEventCallback$1$onMandatoryUpgrade$1(JLDialogActivity jLDialogActivity, Continuation<? super JLDialogActivity$btEventCallback$1$onMandatoryUpgrade$1> continuation) {
        super(2, continuation);
        this.this$0 = jLDialogActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new JLDialogActivity$btEventCallback$1$onMandatoryUpgrade$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((JLDialogActivity$btEventCallback$1$onMandatoryUpgrade$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        JLOTAManager jLOTAManager;
        JLOTAManager jLOTAManager2;
        JLOTAManager jLOTAManager3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            jLOTAManager = this.this$0.otaManager;
            if (jLOTAManager != null && jLOTAManager.isOTA()) {
                return Unit.INSTANCE;
            }
            BleSOTAData bleSOTAData = JLOTA.INSTANCE.getBleSOTAData();
            UUID fromString = UUID.fromString("000000fa-0000-1000-8000-00805f9b34fb");
            Intrinsics.checkNotNullExpressionValue(fromString, "fromString(...)");
            UUID fromString2 = UUID.fromString("0000fa02-0000-1000-8000-00805f9b34fb");
            Intrinsics.checkNotNullExpressionValue(fromString2, "fromString(...)");
            jLOTAManager2 = this.this$0.otaManager;
            bleSOTAData.sendData(fromString, fromString2, jLOTAManager2 != null ? jLOTAManager2.getBluetoothDevice() : null, new byte[]{4, 0, 2, JSONB.Constants.BC_INT64_SHORT_MIN});
            this.label = 1;
            if (DelayKt.delay(200L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        jLOTAManager3 = this.this$0.otaManager;
        if (jLOTAManager3 != null) {
            jLOTAManager3.startOTA(this.this$0);
        }
        return Unit.INSTANCE;
    }
}
