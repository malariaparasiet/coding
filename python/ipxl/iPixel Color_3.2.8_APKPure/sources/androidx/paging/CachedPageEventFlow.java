package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: CachedPageEventFlow.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B!\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0015\u001a\u00020\u0016R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0018\u00010\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0013\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0018\u00010\u00100\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/paging/CachedPageEventFlow;", ExifInterface.GPS_DIRECTION_TRUE, "", "src", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PageEvent;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/CoroutineScope;)V", "downstreamFlow", "getDownstreamFlow", "()Lkotlinx/coroutines/flow/Flow;", "job", "Lkotlinx/coroutines/Job;", "mutableSharedSrc", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lkotlin/collections/IndexedValue;", "pageController", "Landroidx/paging/FlattenedPageController;", "sharedForDownstream", "Lkotlinx/coroutines/flow/SharedFlow;", "close", "", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CachedPageEventFlow<T> {
    private final Flow<PageEvent<T>> downstreamFlow;
    private final Job job;
    private final MutableSharedFlow<IndexedValue<PageEvent<T>>> mutableSharedSrc;
    private final FlattenedPageController<T> pageController;
    private final SharedFlow<IndexedValue<PageEvent<T>>> sharedForDownstream;

    public CachedPageEventFlow(Flow<? extends PageEvent<T>> src, CoroutineScope scope) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.pageController = new FlattenedPageController<>();
        MutableSharedFlow<IndexedValue<PageEvent<T>>> MutableSharedFlow = SharedFlowKt.MutableSharedFlow(1, Integer.MAX_VALUE, BufferOverflow.SUSPEND);
        this.mutableSharedSrc = MutableSharedFlow;
        this.sharedForDownstream = FlowKt.onSubscription(MutableSharedFlow, new CachedPageEventFlow$sharedForDownstream$1(this, null));
        launch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, CoroutineStart.LAZY, new CachedPageEventFlow$job$1(src, this, null), 1, null);
        launch$default.invokeOnCompletion(new Function1<Throwable, Unit>(this) { // from class: androidx.paging.CachedPageEventFlow$job$2$1
            final /* synthetic */ CachedPageEventFlow<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                MutableSharedFlow mutableSharedFlow;
                mutableSharedFlow = ((CachedPageEventFlow) this.this$0).mutableSharedSrc;
                mutableSharedFlow.tryEmit(null);
            }
        });
        Unit unit = Unit.INSTANCE;
        this.job = launch$default;
        this.downstreamFlow = FlowKt.flow(new CachedPageEventFlow$downstreamFlow$1(this, null));
    }

    public final void close() {
        Job.DefaultImpls.cancel$default(this.job, (CancellationException) null, 1, (Object) null);
    }

    public final Flow<PageEvent<T>> getDownstreamFlow() {
        return this.downstreamFlow;
    }
}
