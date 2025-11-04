package com.tiro.jlotalibrary;

import android.bluetooth.BluetoothDevice;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: JLOTAManager.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.tiro.jlotalibrary.JLOTAManager$sendDataToDevice$1$2", f = "JLOTAManager.kt", i = {0, 0, 0, 0}, l = {86}, m = "invokeSuspend", n = {"item", "result", "it", "$i$a$-repeat-JLOTAManager$sendDataToDevice$1$2$1"}, s = {"L$0", "L$1", "I$2", "I$3"})
/* loaded from: classes2.dex */
final class JLOTAManager$sendDataToDevice$1$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BluetoothDevice $device;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ JLOTAManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    JLOTAManager$sendDataToDevice$1$2(JLOTAManager jLOTAManager, BluetoothDevice bluetoothDevice, Continuation<? super JLOTAManager$sendDataToDevice$1$2> continuation) {
        super(2, continuation);
        this.this$0 = jLOTAManager;
        this.$device = bluetoothDevice;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new JLOTAManager$sendDataToDevice$1$2(this.this$0, this.$device, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((JLOTAManager$sendDataToDevice$1$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0041 A[Catch: InterruptedException -> 0x0025, TryCatch #0 {InterruptedException -> 0x0025, blocks: (B:6:0x0020, B:9:0x0033, B:11:0x0041, B:13:0x004f, B:17:0x0052, B:20:0x0064, B:22:0x0068), top: B:5:0x0020 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0064 A[Catch: InterruptedException -> 0x0025, TryCatch #0 {InterruptedException -> 0x0025, blocks: (B:6:0x0020, B:9:0x0033, B:11:0x0041, B:13:0x004f, B:17:0x0052, B:20:0x0064, B:22:0x0068), top: B:5:0x0020 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0052 -> B:8:0x0062). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0066 -> B:7:0x00a1). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x009e -> B:7:0x00a1). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L30
            if (r1 != r3) goto L28
            int r1 = r12.I$1
            int r4 = r12.I$0
            java.lang.Object r5 = r12.L$3
            android.bluetooth.BluetoothDevice r5 = (android.bluetooth.BluetoothDevice) r5
            java.lang.Object r6 = r12.L$2
            com.tiro.jlotalibrary.JLOTAManager r6 = (com.tiro.jlotalibrary.JLOTAManager) r6
            java.lang.Object r7 = r12.L$1
            kotlin.jvm.internal.Ref$BooleanRef r7 = (kotlin.jvm.internal.Ref.BooleanRef) r7
            java.lang.Object r8 = r12.L$0
            byte[] r8 = (byte[]) r8
            kotlin.ResultKt.throwOnFailure(r13)     // Catch: java.lang.InterruptedException -> L25
            goto La1
        L25:
            r13 = move-exception
            goto La3
        L28:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L30:
            kotlin.ResultKt.throwOnFailure(r13)
        L33:
            com.tiro.jlotalibrary.JLOTAManager r13 = r12.this$0     // Catch: java.lang.InterruptedException -> L25
            java.util.concurrent.LinkedBlockingQueue r13 = com.tiro.jlotalibrary.JLOTAManager.access$getLinked$p(r13)     // Catch: java.lang.InterruptedException -> L25
            java.util.Collection r13 = (java.util.Collection) r13     // Catch: java.lang.InterruptedException -> L25
            boolean r13 = r13.isEmpty()     // Catch: java.lang.InterruptedException -> L25
            if (r13 != 0) goto Lae
            com.tiro.jlotalibrary.JLOTAManager r13 = r12.this$0     // Catch: java.lang.InterruptedException -> L25
            java.util.concurrent.LinkedBlockingQueue r13 = com.tiro.jlotalibrary.JLOTAManager.access$getLinked$p(r13)     // Catch: java.lang.InterruptedException -> L25
            java.lang.Object r13 = r13.poll()     // Catch: java.lang.InterruptedException -> L25
            byte[] r13 = (byte[]) r13     // Catch: java.lang.InterruptedException -> L25
            if (r13 != 0) goto L52
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch: java.lang.InterruptedException -> L25
            return r13
        L52:
            kotlin.jvm.internal.Ref$BooleanRef r1 = new kotlin.jvm.internal.Ref$BooleanRef     // Catch: java.lang.InterruptedException -> L25
            r1.<init>()     // Catch: java.lang.InterruptedException -> L25
            com.tiro.jlotalibrary.JLOTAManager r4 = r12.this$0     // Catch: java.lang.InterruptedException -> L25
            android.bluetooth.BluetoothDevice r5 = r12.$device     // Catch: java.lang.InterruptedException -> L25
            r6 = 6
            r7 = r6
            r6 = r4
            r4 = r7
            r8 = r13
            r7 = r1
            r1 = r2
        L62:
            if (r1 >= r4) goto L33
            boolean r13 = r7.element     // Catch: java.lang.InterruptedException -> L25
            if (r13 != 0) goto La1
            kotlin.jvm.functions.Function4 r13 = r6.getSendData()     // Catch: java.lang.InterruptedException -> L25
            java.util.UUID r9 = com.jieli.jl_bt_ota.constant.BluetoothConstant.UUID_SERVICE     // Catch: java.lang.InterruptedException -> L25
            java.lang.String r10 = "UUID_SERVICE"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r10)     // Catch: java.lang.InterruptedException -> L25
            java.util.UUID r10 = com.jieli.jl_bt_ota.constant.BluetoothConstant.UUID_WRITE     // Catch: java.lang.InterruptedException -> L25
            java.lang.String r11 = "UUID_WRITE"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r11)     // Catch: java.lang.InterruptedException -> L25
            java.lang.Object r13 = r13.invoke(r9, r10, r5, r8)     // Catch: java.lang.InterruptedException -> L25
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch: java.lang.InterruptedException -> L25
            boolean r13 = r13.booleanValue()     // Catch: java.lang.InterruptedException -> L25
            r7.element = r13     // Catch: java.lang.InterruptedException -> L25
            r12.L$0 = r8     // Catch: java.lang.InterruptedException -> L25
            r12.L$1 = r7     // Catch: java.lang.InterruptedException -> L25
            r12.L$2 = r6     // Catch: java.lang.InterruptedException -> L25
            r12.L$3 = r5     // Catch: java.lang.InterruptedException -> L25
            r12.I$0 = r4     // Catch: java.lang.InterruptedException -> L25
            r12.I$1 = r1     // Catch: java.lang.InterruptedException -> L25
            r12.I$2 = r1     // Catch: java.lang.InterruptedException -> L25
            r12.I$3 = r2     // Catch: java.lang.InterruptedException -> L25
            r12.label = r3     // Catch: java.lang.InterruptedException -> L25
            r9 = 10
            java.lang.Object r13 = kotlinx.coroutines.DelayKt.delay(r9, r12)     // Catch: java.lang.InterruptedException -> L25
            if (r13 != r0) goto La1
            return r0
        La1:
            int r1 = r1 + r3
            goto L62
        La3:
            r13.printStackTrace()
            com.tiro.jlotalibrary.util.LogUtil r13 = com.tiro.jlotalibrary.util.LogUtil.INSTANCE
            java.lang.String r0 = "发送任务中断"
            r13.e(r0)
        Lae:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tiro.jlotalibrary.JLOTAManager$sendDataToDevice$1$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
