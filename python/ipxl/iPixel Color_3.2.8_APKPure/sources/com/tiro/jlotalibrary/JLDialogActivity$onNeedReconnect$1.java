package com.tiro.jlotalibrary;

import com.wifiled.musiclib.player.service.PlayerService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: JLDialogActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.tiro.jlotalibrary.JLDialogActivity$onNeedReconnect$1", f = "JLDialogActivity.kt", i = {0, 0}, l = {PlayerService.SEEK_CHANGE}, m = "invokeSuspend", n = {"it", "$i$a$-repeat-JLDialogActivity$onNeedReconnect$1$1"}, s = {"I$2", "I$3"})
/* loaded from: classes2.dex */
final class JLDialogActivity$onNeedReconnect$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $addr;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    JLDialogActivity$onNeedReconnect$1(String str, Continuation<? super JLDialogActivity$onNeedReconnect$1> continuation) {
        super(2, continuation);
        this.$addr = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new JLDialogActivity$onNeedReconnect$1(this.$addr, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((JLDialogActivity$onNeedReconnect$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002d A[Catch: CancellationException -> 0x0068, TryCatch #0 {CancellationException -> 0x0068, blocks: (B:6:0x0016, B:7:0x0043, B:10:0x002d, B:21:0x0025), top: B:2:0x0008 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0042 -> B:7:0x0043). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L22
            if (r1 != r3) goto L1a
            int r1 = r9.I$2
            int r4 = r9.I$1
            int r5 = r9.I$0
            java.lang.Object r6 = r9.L$0
            java.lang.String r6 = (java.lang.String) r6
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.util.concurrent.CancellationException -> L68
            goto L43
        L1a:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L22:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.String r10 = r9.$addr     // Catch: java.util.concurrent.CancellationException -> L68
            r1 = 6
            r6 = r10
            r5 = r1
            r1 = r2
        L2b:
            if (r1 >= r5) goto L70
            r9.L$0 = r6     // Catch: java.util.concurrent.CancellationException -> L68
            r9.I$0 = r5     // Catch: java.util.concurrent.CancellationException -> L68
            r9.I$1 = r1     // Catch: java.util.concurrent.CancellationException -> L68
            r9.I$2 = r1     // Catch: java.util.concurrent.CancellationException -> L68
            r9.I$3 = r2     // Catch: java.util.concurrent.CancellationException -> L68
            r9.label = r3     // Catch: java.util.concurrent.CancellationException -> L68
            r7 = 5000(0x1388, double:2.4703E-320)
            java.lang.Object r10 = kotlinx.coroutines.DelayKt.delay(r7, r9)     // Catch: java.util.concurrent.CancellationException -> L68
            if (r10 != r0) goto L42
            return r0
        L42:
            r4 = r1
        L43:
            com.tiro.jlotalibrary.util.LogUtil r10 = com.tiro.jlotalibrary.util.LogUtil.INSTANCE     // Catch: java.util.concurrent.CancellationException -> L68
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.util.concurrent.CancellationException -> L68
            r7.<init>()     // Catch: java.util.concurrent.CancellationException -> L68
            java.lang.String r8 = "连接设备，"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch: java.util.concurrent.CancellationException -> L68
            java.lang.StringBuilder r1 = r7.append(r1)     // Catch: java.util.concurrent.CancellationException -> L68
            java.lang.String r1 = r1.toString()     // Catch: java.util.concurrent.CancellationException -> L68
            r10.d(r1)     // Catch: java.util.concurrent.CancellationException -> L68
            com.tiro.jlotalibrary.exposed.JLOTA r10 = com.tiro.jlotalibrary.exposed.JLOTA.INSTANCE     // Catch: java.util.concurrent.CancellationException -> L68
            com.tiro.jlotalibrary.exposed.BleSOTAData r10 = r10.getBleSOTAData()     // Catch: java.util.concurrent.CancellationException -> L68
            r10.connect(r6)     // Catch: java.util.concurrent.CancellationException -> L68
            int r1 = r4 + 1
            goto L2b
        L68:
            com.tiro.jlotalibrary.util.LogUtil r10 = com.tiro.jlotalibrary.util.LogUtil.INSTANCE
            java.lang.String r0 = "连接取消"
            r10.d(r0)
        L70:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tiro.jlotalibrary.JLDialogActivity$onNeedReconnect$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
