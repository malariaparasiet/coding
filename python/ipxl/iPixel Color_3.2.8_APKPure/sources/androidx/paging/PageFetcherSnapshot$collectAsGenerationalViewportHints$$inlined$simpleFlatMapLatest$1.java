package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import com.jieli.jl_bt_ota.constant.Command;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: FlowExt.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\u008a@¨\u0006\u0006"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "androidx/paging/FlowExtKt$simpleFlatMapLatest$1"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "androidx.paging.PageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1", f = "PageFetcherSnapshot.kt", i = {0, 0, 0}, l = {Command.CMD_OTA_SEND_FIRMWARE_UPDATE_BLOCK, 244}, m = "invokeSuspend", n = {"this_$iv", "$this$withLock_u24default$iv$iv", "generationId"}, s = {"L$1", "L$2", "I$0"})
/* loaded from: classes2.dex */
public final class PageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1 extends SuspendLambda implements Function3<FlowCollector<? super GenerationalViewportHint>, Integer, Continuation<? super Unit>, Object> {
    final /* synthetic */ LoadType $loadType$inlined;
    int I$0;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ PageFetcherSnapshot this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1(Continuation continuation, PageFetcherSnapshot pageFetcherSnapshot, LoadType loadType) {
        super(3, continuation);
        this.this$0 = pageFetcherSnapshot;
        this.$loadType$inlined = loadType;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(FlowCollector<? super GenerationalViewportHint> flowCollector, Integer num, Continuation<? super Unit> continuation) {
        PageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1 pageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1 = new PageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1(continuation, this.this$0, this.$loadType$inlined);
        pageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1.L$0 = flowCollector;
        pageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1.L$1 = num;
        return pageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x00d0, code lost:
    
        if (kotlinx.coroutines.flow.FlowKt.emitAll(r7, r11, r10) == r0) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00d2, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0059, code lost:
    
        if (r5.lock(null, r10) == r0) goto L27;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L2e
            if (r1 == r3) goto L1c
            if (r1 != r2) goto L14
            kotlin.ResultKt.throwOnFailure(r11)
            goto Ld3
        L14:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L1c:
            int r1 = r10.I$0
            java.lang.Object r5 = r10.L$2
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            java.lang.Object r6 = r10.L$1
            androidx.paging.PageFetcherSnapshotState$Holder r6 = (androidx.paging.PageFetcherSnapshotState.Holder) r6
            java.lang.Object r7 = r10.L$0
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
            kotlin.ResultKt.throwOnFailure(r11)
            goto L5d
        L2e:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.Object r11 = r10.L$0
            r7 = r11
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
            java.lang.Object r11 = r10.L$1
            r1 = r10
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            java.lang.Number r11 = (java.lang.Number) r11
            int r1 = r11.intValue()
            androidx.paging.PageFetcherSnapshot r11 = r10.this$0
            androidx.paging.PageFetcherSnapshotState$Holder r6 = androidx.paging.PageFetcherSnapshot.access$getStateHolder$p(r11)
            kotlinx.coroutines.sync.Mutex r5 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r6)
            r10.L$0 = r7
            r10.L$1 = r6
            r10.L$2 = r5
            r10.I$0 = r1
            r10.label = r3
            java.lang.Object r11 = r5.lock(r4, r10)
            if (r11 != r0) goto L5d
            goto Ld2
        L5d:
            androidx.paging.PageFetcherSnapshotState r11 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r6)     // Catch: java.lang.Throwable -> Ld6
            androidx.paging.MutableLoadStateCollection r6 = r11.getSourceLoadStates()     // Catch: java.lang.Throwable -> Ld6
            androidx.paging.LoadType r8 = r10.$loadType$inlined     // Catch: java.lang.Throwable -> Ld6
            androidx.paging.LoadState r6 = r6.get(r8)     // Catch: java.lang.Throwable -> Ld6
            androidx.paging.LoadState$NotLoading$Companion r8 = androidx.paging.LoadState.NotLoading.INSTANCE     // Catch: java.lang.Throwable -> Ld6
            androidx.paging.LoadState$NotLoading r8 = r8.getComplete$paging_common()     // Catch: java.lang.Throwable -> Ld6
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r8)     // Catch: java.lang.Throwable -> Ld6
            r8 = 0
            if (r6 == 0) goto L82
            androidx.paging.GenerationalViewportHint[] r11 = new androidx.paging.GenerationalViewportHint[r8]     // Catch: java.lang.Throwable -> Ld6
            kotlinx.coroutines.flow.Flow r11 = kotlinx.coroutines.flow.FlowKt.flowOf(r11)     // Catch: java.lang.Throwable -> Ld6
            r5.unlock(r4)
            goto Lc1
        L82:
            androidx.paging.MutableLoadStateCollection r6 = r11.getSourceLoadStates()     // Catch: java.lang.Throwable -> Ld6
            androidx.paging.LoadType r9 = r10.$loadType$inlined     // Catch: java.lang.Throwable -> Ld6
            androidx.paging.LoadState r6 = r6.get(r9)     // Catch: java.lang.Throwable -> Ld6
            boolean r6 = r6 instanceof androidx.paging.LoadState.Error     // Catch: java.lang.Throwable -> Ld6
            if (r6 != 0) goto La1
            androidx.paging.MutableLoadStateCollection r11 = r11.getSourceLoadStates()     // Catch: java.lang.Throwable -> Ld6
            androidx.paging.LoadType r6 = r10.$loadType$inlined     // Catch: java.lang.Throwable -> Ld6
            androidx.paging.LoadState$NotLoading$Companion r9 = androidx.paging.LoadState.NotLoading.INSTANCE     // Catch: java.lang.Throwable -> Ld6
            androidx.paging.LoadState$NotLoading r9 = r9.getIncomplete$paging_common()     // Catch: java.lang.Throwable -> Ld6
            androidx.paging.LoadState r9 = (androidx.paging.LoadState) r9     // Catch: java.lang.Throwable -> Ld6
            r11.set(r6, r9)     // Catch: java.lang.Throwable -> Ld6
        La1:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> Ld6
            r5.unlock(r4)
            androidx.paging.PageFetcherSnapshot r11 = r10.this$0
            androidx.paging.HintHandler r11 = androidx.paging.PageFetcherSnapshot.access$getHintHandler$p(r11)
            androidx.paging.LoadType r5 = r10.$loadType$inlined
            kotlinx.coroutines.flow.Flow r11 = r11.hintFor(r5)
            if (r1 != 0) goto Lb5
            r3 = r8
        Lb5:
            kotlinx.coroutines.flow.Flow r11 = kotlinx.coroutines.flow.FlowKt.drop(r11, r3)
            androidx.paging.PageFetcherSnapshot$collectAsGenerationalViewportHints$lambda-6$$inlined$map$1 r3 = new androidx.paging.PageFetcherSnapshot$collectAsGenerationalViewportHints$lambda-6$$inlined$map$1
            r3.<init>()
            r11 = r3
            kotlinx.coroutines.flow.Flow r11 = (kotlinx.coroutines.flow.Flow) r11
        Lc1:
            r1 = r10
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r10.L$0 = r4
            r10.L$1 = r4
            r10.L$2 = r4
            r10.label = r2
            java.lang.Object r11 = kotlinx.coroutines.flow.FlowKt.emitAll(r7, r11, r1)
            if (r11 != r0) goto Ld3
        Ld2:
            return r0
        Ld3:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        Ld6:
            r11 = move-exception
            r5.unlock(r4)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
