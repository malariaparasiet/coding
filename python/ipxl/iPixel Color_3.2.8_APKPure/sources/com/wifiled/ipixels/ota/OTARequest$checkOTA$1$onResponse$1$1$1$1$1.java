package com.wifiled.ipixels.ota;

import android.content.Context;
import android.content.Intent;
import com.wifiled.baselib.retrofit.entity.OTAInformation;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: OTARequest.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ota.OTARequest$checkOTA$1$onResponse$1$1$1$1$1", f = "OTARequest.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class OTARequest$checkOTA$1$onResponse$1$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ OTAInformation $it;
    final /* synthetic */ String $macAddress;
    final /* synthetic */ int $version;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    OTARequest$checkOTA$1$onResponse$1$1$1$1$1(Context context, OTAInformation oTAInformation, String str, int i, Continuation<? super OTARequest$checkOTA$1$onResponse$1$1$1$1$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$it = oTAInformation;
        this.$macAddress = str;
        this.$version = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OTARequest$checkOTA$1$onResponse$1$1$1$1$1(this.$context, this.$it, this.$macAddress, this.$version, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OTARequest$checkOTA$1$onResponse$1$1$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Intent intent = new Intent(this.$context, (Class<?>) OTADialogActivity.class);
        intent.putExtra("desc_cn", this.$it.getDesc_cn());
        intent.putExtra("desc_en", this.$it.getDesc_en());
        intent.putExtra("version_no", this.$it.getVersion_no());
        intent.putExtra("macAddress", this.$macAddress);
        intent.putExtra("version_old", String.valueOf(this.$version));
        intent.addFlags(268435456);
        this.$context.startActivity(intent);
        return Unit.INSTANCE;
    }
}
