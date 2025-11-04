package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Recomposer.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "parentFrameClock", "Landroidx/compose/runtime/MonotonicFrameClock;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2", f = "Recomposer.kt", i = {0}, l = {398, 416}, m = "invokeSuspend", n = {"toApply"}, s = {"L$2"})
/* loaded from: classes.dex */
final class Recomposer$runRecomposeAndApplyChanges$2 extends SuspendLambda implements Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ Recomposer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Recomposer$runRecomposeAndApplyChanges$2(Recomposer recomposer, Continuation<? super Recomposer$runRecomposeAndApplyChanges$2> continuation) {
        super(3, continuation);
        this.this$0 = recomposer;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, MonotonicFrameClock monotonicFrameClock, Continuation<? super Unit> continuation) {
        Recomposer$runRecomposeAndApplyChanges$2 recomposer$runRecomposeAndApplyChanges$2 = new Recomposer$runRecomposeAndApplyChanges$2(this.this$0, continuation);
        recomposer$runRecomposeAndApplyChanges$2.L$0 = monotonicFrameClock;
        return recomposer$runRecomposeAndApplyChanges$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00ad, code lost:
    
        if (r10.withFrameNanos(new androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2.AnonymousClass2(), r9) == r0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00af, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x006c, code lost:
    
        if (r5 == r0) goto L30;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0092 -> B:6:0x0053). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00ad -> B:6:0x0053). Please report as a decompilation issue!!! */
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
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L3e
            if (r1 == r3) goto L2a
            if (r1 != r2) goto L22
            java.lang.Object r1 = r9.L$2
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r4 = r9.L$1
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r5 = r9.L$0
            androidx.compose.runtime.MonotonicFrameClock r5 = (androidx.compose.runtime.MonotonicFrameClock) r5
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r4
            r4 = r1
            r1 = r10
            r10 = r5
            goto L53
        L22:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L2a:
            java.lang.Object r1 = r9.L$2
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r4 = r9.L$1
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r5 = r9.L$0
            androidx.compose.runtime.MonotonicFrameClock r5 = (androidx.compose.runtime.MonotonicFrameClock) r5
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r4
            r4 = r1
            r1 = r10
            r10 = r5
            goto L6f
        L3e:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.L$0
            androidx.compose.runtime.MonotonicFrameClock r10 = (androidx.compose.runtime.MonotonicFrameClock) r10
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.List r1 = (java.util.List) r1
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.List r4 = (java.util.List) r4
        L53:
            androidx.compose.runtime.Recomposer r5 = r9.this$0
            boolean r5 = androidx.compose.runtime.Recomposer.access$getShouldKeepRecomposing(r5)
            if (r5 == 0) goto Lb3
            androidx.compose.runtime.Recomposer r5 = r9.this$0
            r6 = r9
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r9.L$0 = r10
            r9.L$1 = r1
            r9.L$2 = r4
            r9.label = r3
            java.lang.Object r5 = androidx.compose.runtime.Recomposer.access$awaitWorkAvailable(r5, r6)
            if (r5 != r0) goto L6f
            goto Laf
        L6f:
            androidx.compose.runtime.Recomposer r5 = r9.this$0
            java.lang.Object r5 = androidx.compose.runtime.Recomposer.access$getStateLock$p(r5)
            androidx.compose.runtime.Recomposer r6 = r9.this$0
            monitor-enter(r5)
            boolean r7 = androidx.compose.runtime.Recomposer.access$getHasFrameWorkLocked(r6)     // Catch: java.lang.Throwable -> Lb0
            r8 = 0
            if (r7 != 0) goto L89
            androidx.compose.runtime.Recomposer.access$recordComposerModificationsLocked(r6)     // Catch: java.lang.Throwable -> Lb0
            boolean r6 = androidx.compose.runtime.Recomposer.access$getHasFrameWorkLocked(r6)     // Catch: java.lang.Throwable -> Lb0
            if (r6 != 0) goto L89
            r8 = r3
        L89:
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r8)     // Catch: java.lang.Throwable -> Lb0
            monitor-exit(r5)
            boolean r5 = r6.booleanValue()
            if (r5 == 0) goto L95
            goto L53
        L95:
            androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2$2 r5 = new androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2$2
            androidx.compose.runtime.Recomposer r6 = r9.this$0
            r5.<init>()
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            r6 = r9
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r9.L$0 = r10
            r9.L$1 = r1
            r9.L$2 = r4
            r9.label = r2
            java.lang.Object r5 = r10.withFrameNanos(r5, r6)
            if (r5 != r0) goto L53
        Laf:
            return r0
        Lb0:
            r10 = move-exception
            monitor-exit(r5)
            throw r10
        Lb3:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
