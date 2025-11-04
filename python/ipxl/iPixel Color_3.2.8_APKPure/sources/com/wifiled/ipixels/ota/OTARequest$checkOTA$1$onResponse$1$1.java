package com.wifiled.ipixels.ota;

import android.content.Context;
import com.google.android.gms.common.internal.ImagesContract;
import com.wifiled.baselib.retrofit.entity.OTAInformation;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: OTARequest.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ota.OTARequest$checkOTA$1$onResponse$1$1", f = "OTARequest.kt", i = {0, 0, 1, 1, 1, 1}, l = {79, 81}, m = "invokeSuspend", n = {ImagesContract.URL, "$i$a$-let-OTARequest$checkOTA$1$onResponse$1$1$1", ImagesContract.URL, "bytes", "$i$a$-let-OTARequest$checkOTA$1$onResponse$1$1$1", "$i$a$-let-OTARequest$checkOTA$1$onResponse$1$1$1$1"}, s = {"L$3", "I$1", "L$0", "L$1", "I$0", "I$1"})
/* loaded from: classes3.dex */
final class OTARequest$checkOTA$1$onResponse$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ OTAInformation $it;
    final /* synthetic */ String $macAddress;
    final /* synthetic */ int $version;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    OTARequest$checkOTA$1$onResponse$1$1(OTAInformation oTAInformation, Context context, String str, int i, Continuation<? super OTARequest$checkOTA$1$onResponse$1$1> continuation) {
        super(2, continuation);
        this.$it = oTAInformation;
        this.$context = context;
        this.$macAddress = str;
        this.$version = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OTARequest$checkOTA$1$onResponse$1$1(this.$it, this.$context, this.$macAddress, this.$version, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OTARequest$checkOTA$1$onResponse$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x00c0, code lost:
    
        if (kotlinx.coroutines.BuildersKt.withContext(r12, r6, r13) == r0) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0092  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 1
            r3 = 0
            r4 = 0
            r5 = 2
            if (r1 == 0) goto L42
            if (r1 == r2) goto L25
            if (r1 != r5) goto L1d
            java.lang.Object r0 = r13.L$1
            byte[] r0 = (byte[]) r0
            java.lang.Object r0 = r13.L$0
            java.lang.String r0 = (java.lang.String) r0
            kotlin.ResultKt.throwOnFailure(r14)
            goto Lc3
        L1d:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L25:
            int r1 = r13.I$1
            int r2 = r13.I$0
            java.lang.Object r6 = r13.L$3
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r13.L$2
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r13.L$1
            com.wifiled.baselib.retrofit.entity.OTAInformation r8 = (com.wifiled.baselib.retrofit.entity.OTAInformation) r8
            java.lang.Object r9 = r13.L$0
            android.content.Context r9 = (android.content.Context) r9
            kotlin.ResultKt.throwOnFailure(r14)
            r10 = r9
            r9 = r7
            r7 = r10
            r10 = r2
        L40:
            r2 = r6
            goto L8e
        L42:
            kotlin.ResultKt.throwOnFailure(r14)
            com.wifiled.baselib.retrofit.entity.OTAInformation r14 = r13.$it
            java.lang.String r6 = r14.getFile_path()
            if (r6 == 0) goto Lc3
            android.content.Context r9 = r13.$context
            com.wifiled.baselib.retrofit.entity.OTAInformation r8 = r13.$it
            java.lang.String r7 = r13.$macAddress
            int r14 = r13.$version
            r1 = r6
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            java.lang.String r10 = ".bin"
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10
            boolean r10 = kotlin.text.StringsKt.contains$default(r1, r10, r4, r5, r3)
            if (r10 != 0) goto L6c
            java.lang.String r10 = ".ufw"
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10
            boolean r1 = kotlin.text.StringsKt.contains$default(r1, r10, r4, r5, r3)
            if (r1 == 0) goto Lc3
        L6c:
            com.wifiled.ipixels.ota.OTARequest r1 = com.wifiled.ipixels.ota.OTARequest.INSTANCE
            r13.L$0 = r9
            r13.L$1 = r8
            r13.L$2 = r7
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r13.L$3 = r10
            r13.I$0 = r14
            r13.I$1 = r4
            r13.label = r2
            java.lang.Object r1 = com.wifiled.ipixels.ota.OTARequest.access$getHttpToStream(r1, r6, r13)
            if (r1 != r0) goto L87
            goto Lc2
        L87:
            r2 = r9
            r9 = r7
            r7 = r2
            r10 = r14
            r14 = r1
            r1 = r4
            goto L40
        L8e:
            byte[] r14 = (byte[]) r14
            if (r14 == 0) goto Lc3
            com.wifiled.ipixels.ota.OTARequest r6 = com.wifiled.ipixels.ota.OTARequest.INSTANCE
            r6.setData(r14)
            kotlinx.coroutines.MainCoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getMain()
            r12 = r6
            kotlin.coroutines.CoroutineContext r12 = (kotlin.coroutines.CoroutineContext) r12
            com.wifiled.ipixels.ota.OTARequest$checkOTA$1$onResponse$1$1$1$1$1 r6 = new com.wifiled.ipixels.ota.OTARequest$checkOTA$1$onResponse$1$1$1$1$1
            r11 = 0
            r6.<init>(r7, r8, r9, r10, r11)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r13.L$0 = r2
            java.lang.Object r14 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)
            r13.L$1 = r14
            r13.L$2 = r3
            r13.L$3 = r3
            r13.I$0 = r1
            r13.I$1 = r4
            r13.label = r5
            java.lang.Object r14 = kotlinx.coroutines.BuildersKt.withContext(r12, r6, r13)
            if (r14 != r0) goto Lc3
        Lc2:
            return r0
        Lc3:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ota.OTARequest$checkOTA$1$onResponse$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
