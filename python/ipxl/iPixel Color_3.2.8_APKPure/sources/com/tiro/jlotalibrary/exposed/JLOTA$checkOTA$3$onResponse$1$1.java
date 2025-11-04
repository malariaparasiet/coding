package com.tiro.jlotalibrary.exposed;

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

/* compiled from: JLOTA.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.tiro.jlotalibrary.exposed.JLOTA$checkOTA$3$onResponse$1$1", f = "JLOTA.kt", i = {0, 0, 1, 1, 1, 1}, l = {91, 93}, m = "invokeSuspend", n = {ImagesContract.URL, "$i$a$-let-JLOTA$checkOTA$3$onResponse$1$1$1", ImagesContract.URL, "bytes", "$i$a$-let-JLOTA$checkOTA$3$onResponse$1$1$1", "$i$a$-let-JLOTA$checkOTA$3$onResponse$1$1$1$1"}, s = {"L$2", "I$1", "L$0", "L$1", "I$0", "I$1"})
/* loaded from: classes2.dex */
final class JLOTA$checkOTA$3$onResponse$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ OTAInformation $it;
    final /* synthetic */ int $version;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    JLOTA$checkOTA$3$onResponse$1$1(OTAInformation oTAInformation, Context context, int i, Continuation<? super JLOTA$checkOTA$3$onResponse$1$1> continuation) {
        super(2, continuation);
        this.$it = oTAInformation;
        this.$context = context;
        this.$version = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new JLOTA$checkOTA$3$onResponse$1$1(this.$it, this.$context, this.$version, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((JLOTA$checkOTA$3$onResponse$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x00a1, code lost:
    
        if (kotlinx.coroutines.BuildersKt.withContext(r9, r10, r11) == r0) goto L22;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0077  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 1
            r3 = 0
            r4 = 0
            r5 = 2
            if (r1 == 0) goto L39
            if (r1 == r2) goto L25
            if (r1 != r5) goto L1d
            java.lang.Object r0 = r11.L$1
            byte[] r0 = (byte[]) r0
            java.lang.Object r0 = r11.L$0
            java.lang.String r0 = (java.lang.String) r0
            kotlin.ResultKt.throwOnFailure(r12)
            goto La4
        L1d:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L25:
            int r1 = r11.I$1
            int r2 = r11.I$0
            java.lang.Object r6 = r11.L$2
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r11.L$1
            com.wifiled.baselib.retrofit.entity.OTAInformation r7 = (com.wifiled.baselib.retrofit.entity.OTAInformation) r7
            java.lang.Object r8 = r11.L$0
            android.content.Context r8 = (android.content.Context) r8
            kotlin.ResultKt.throwOnFailure(r12)
            goto L73
        L39:
            kotlin.ResultKt.throwOnFailure(r12)
            com.wifiled.baselib.retrofit.entity.OTAInformation r12 = r11.$it
            java.lang.String r6 = r12.getFile_path()
            if (r6 == 0) goto La4
            android.content.Context r8 = r11.$context
            com.wifiled.baselib.retrofit.entity.OTAInformation r7 = r11.$it
            int r12 = r11.$version
            r1 = r6
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            java.lang.String r9 = ".ufw"
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            boolean r1 = kotlin.text.StringsKt.contains$default(r1, r9, r4, r5, r3)
            if (r1 == 0) goto La4
            com.tiro.jlotalibrary.exposed.JLOTA r1 = com.tiro.jlotalibrary.exposed.JLOTA.INSTANCE
            r11.L$0 = r8
            r11.L$1 = r7
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r11.L$2 = r9
            r11.I$0 = r12
            r11.I$1 = r4
            r11.label = r2
            java.lang.Object r1 = com.tiro.jlotalibrary.exposed.JLOTA.access$getHttpToStream(r1, r6, r11)
            if (r1 != r0) goto L70
            goto La3
        L70:
            r2 = r12
            r12 = r1
            r1 = r4
        L73:
            byte[] r12 = (byte[]) r12
            if (r12 == 0) goto La4
            com.tiro.jlotalibrary.exposed.JLOTA r9 = com.tiro.jlotalibrary.exposed.JLOTA.INSTANCE
            r9.setData(r12)
            kotlinx.coroutines.MainCoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getMain()
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            com.tiro.jlotalibrary.exposed.JLOTA$checkOTA$3$onResponse$1$1$1$1$1 r10 = new com.tiro.jlotalibrary.exposed.JLOTA$checkOTA$3$onResponse$1$1$1$1$1
            r10.<init>(r8, r7, r2, r3)
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r11.L$0 = r2
            java.lang.Object r12 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r11.L$1 = r12
            r11.L$2 = r3
            r11.I$0 = r1
            r11.I$1 = r4
            r11.label = r5
            java.lang.Object r12 = kotlinx.coroutines.BuildersKt.withContext(r9, r10, r11)
            if (r12 != r0) goto La4
        La3:
            return r0
        La4:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tiro.jlotalibrary.exposed.JLOTA$checkOTA$3$onResponse$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
