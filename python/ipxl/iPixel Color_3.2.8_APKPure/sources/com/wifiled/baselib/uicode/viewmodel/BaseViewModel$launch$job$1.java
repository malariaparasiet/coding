package com.wifiled.baselib.uicode.viewmodel;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: BaseViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.baselib.uicode.viewmodel.BaseViewModel$launch$job$1", f = "BaseViewModel.kt", i = {1}, l = {123, 125}, m = "invokeSuspend", n = {"t"}, s = {"L$0"})
/* loaded from: classes2.dex */
final class BaseViewModel$launch$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Continuation<? super Unit>, Object> $block;
    final /* synthetic */ Function3<Integer, String, Continuation<? super Unit>, Object> $failed;
    Object L$0;
    int label;
    final /* synthetic */ BaseViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    BaseViewModel$launch$job$1(Function1<? super Continuation<? super Unit>, ? extends Object> function1, BaseViewModel baseViewModel, Function3<? super Integer, ? super String, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super BaseViewModel$launch$job$1> continuation) {
        super(2, continuation);
        this.$block = function1;
        this.this$0 = baseViewModel;
        this.$failed = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseViewModel$launch$job$1(this.$block, this.this$0, this.$failed, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseViewModel$launch$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x002f, code lost:
    
        if (r7.invoke(r6) == r0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0047, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0045, code lost:
    
        if (r7 != r0) goto L20;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L24
            if (r1 == r3) goto L1e
            if (r1 != r2) goto L16
            java.lang.Object r0 = r6.L$0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L48
        L16:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L1e:
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L22
            goto L48
        L22:
            r7 = move-exception
            goto L32
        L24:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r7 = r6.$block     // Catch: java.lang.Throwable -> L22
            r6.label = r3     // Catch: java.lang.Throwable -> L22
            java.lang.Object r7 = r7.invoke(r6)     // Catch: java.lang.Throwable -> L22
            if (r7 != r0) goto L48
            goto L47
        L32:
            com.wifiled.baselib.uicode.viewmodel.BaseViewModel r1 = r6.this$0
            kotlin.jvm.functions.Function3<java.lang.Integer, java.lang.String, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r3 = r6.$failed
            r4 = r6
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r6.L$0 = r5
            r6.label = r2
            java.lang.Object r7 = com.wifiled.baselib.uicode.viewmodel.BaseViewModel.access$onFailSuspend(r1, r7, r3, r4)
            if (r7 != r0) goto L48
        L47:
            return r0
        L48:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.baselib.uicode.viewmodel.BaseViewModel$launch$job$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
