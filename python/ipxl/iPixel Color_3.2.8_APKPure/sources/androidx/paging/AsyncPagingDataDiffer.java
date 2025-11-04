package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleKt;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;

/* compiled from: AsyncPagingDataDiffer.kt */
@Metadata(d1 = {"\u0000\u007f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\f\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B1\b\u0007\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ\u001a\u0010)\u001a\u00020%2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020%0+J\u0014\u0010,\u001a\u00020%2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020%0-J\u0017\u0010.\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010/\u001a\u00020\u001c¢\u0006\u0002\u00100J\u0017\u00101\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010/\u001a\u00020\u001c¢\u0006\u0002\u00100J\u0006\u00102\u001a\u00020%J\u001a\u00103\u001a\u00020%2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020%0+J\u0014\u00104\u001a\u00020%2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020%0-J\u0006\u00105\u001a\u00020%J\f\u00106\u001a\b\u0012\u0004\u0012\u00028\u000007J\u001c\u00108\u001a\u00020%2\u0006\u00109\u001a\u00020:2\f\u0010;\u001a\b\u0012\u0004\u0012\u00028\u00000<J\u001f\u00108\u001a\u00020%2\f\u0010;\u001a\b\u0012\u0004\u0012\u00028\u00000<H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010=R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0080\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R \u0010\u0014\u001a\u00020\u0015X\u0080\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 ¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020%0 ¢\u0006\b\n\u0000\u001a\u0004\b&\u0010#R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006>"}, d2 = {"Landroidx/paging/AsyncPagingDataDiffer;", ExifInterface.GPS_DIRECTION_TRUE, "", "diffCallback", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "updateCallback", "Landroidx/recyclerview/widget/ListUpdateCallback;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "workerDispatcher", "(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;Landroidx/recyclerview/widget/ListUpdateCallback;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "differBase", "androidx/paging/AsyncPagingDataDiffer$differBase$1", "Landroidx/paging/AsyncPagingDataDiffer$differBase$1;", "differCallback", "Landroidx/paging/DifferCallback;", "getDifferCallback$paging_runtime_release$annotations", "()V", "getDifferCallback$paging_runtime_release", "()Landroidx/paging/DifferCallback;", "inGetItem", "", "getInGetItem$paging_runtime_release$annotations", "getInGetItem$paging_runtime_release", "()Z", "setInGetItem$paging_runtime_release", "(Z)V", "itemCount", "", "getItemCount", "()I", "loadStateFlow", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/CombinedLoadStates;", "getLoadStateFlow", "()Lkotlinx/coroutines/flow/Flow;", "onPagesUpdatedFlow", "", "getOnPagesUpdatedFlow", "submitDataId", "Ljava/util/concurrent/atomic/AtomicInteger;", "addLoadStateListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lkotlin/Function1;", "addOnPagesUpdatedListener", "Lkotlin/Function0;", "getItem", "index", "(I)Ljava/lang/Object;", "peek", "refresh", "removeLoadStateListener", "removeOnPagesUpdatedListener", "retry", "snapshot", "Landroidx/paging/ItemSnapshotList;", "submitData", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "pagingData", "Landroidx/paging/PagingData;", "(Landroidx/paging/PagingData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "paging-runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class AsyncPagingDataDiffer<T> {
    private final DiffUtil.ItemCallback<T> diffCallback;
    private final AsyncPagingDataDiffer$differBase$1 differBase;
    private final DifferCallback differCallback;
    private boolean inGetItem;
    private final Flow<CombinedLoadStates> loadStateFlow;
    private final CoroutineDispatcher mainDispatcher;
    private final Flow<Unit> onPagesUpdatedFlow;
    private final AtomicInteger submitDataId;
    private final ListUpdateCallback updateCallback;
    private final CoroutineDispatcher workerDispatcher;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AsyncPagingDataDiffer(DiffUtil.ItemCallback<T> diffCallback, ListUpdateCallback updateCallback) {
        this(diffCallback, updateCallback, null, null, 12, null);
        Intrinsics.checkNotNullParameter(diffCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(updateCallback, "updateCallback");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AsyncPagingDataDiffer(DiffUtil.ItemCallback<T> diffCallback, ListUpdateCallback updateCallback, CoroutineDispatcher mainDispatcher) {
        this(diffCallback, updateCallback, mainDispatcher, null, 8, null);
        Intrinsics.checkNotNullParameter(diffCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(updateCallback, "updateCallback");
        Intrinsics.checkNotNullParameter(mainDispatcher, "mainDispatcher");
    }

    public static /* synthetic */ void getDifferCallback$paging_runtime_release$annotations() {
    }

    public static /* synthetic */ void getInGetItem$paging_runtime_release$annotations() {
    }

    public AsyncPagingDataDiffer(DiffUtil.ItemCallback<T> diffCallback, ListUpdateCallback updateCallback, CoroutineDispatcher mainDispatcher, CoroutineDispatcher workerDispatcher) {
        Intrinsics.checkNotNullParameter(diffCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(updateCallback, "updateCallback");
        Intrinsics.checkNotNullParameter(mainDispatcher, "mainDispatcher");
        Intrinsics.checkNotNullParameter(workerDispatcher, "workerDispatcher");
        this.diffCallback = diffCallback;
        this.updateCallback = updateCallback;
        this.mainDispatcher = mainDispatcher;
        this.workerDispatcher = workerDispatcher;
        DifferCallback differCallback = new DifferCallback(this) { // from class: androidx.paging.AsyncPagingDataDiffer$differCallback$1
            final /* synthetic */ AsyncPagingDataDiffer<T> this$0;

            {
                this.this$0 = this;
            }

            @Override // androidx.paging.DifferCallback
            public void onInserted(int position, int count) {
                ListUpdateCallback listUpdateCallback;
                if (count > 0) {
                    listUpdateCallback = ((AsyncPagingDataDiffer) this.this$0).updateCallback;
                    listUpdateCallback.onInserted(position, count);
                }
            }

            @Override // androidx.paging.DifferCallback
            public void onRemoved(int position, int count) {
                ListUpdateCallback listUpdateCallback;
                if (count > 0) {
                    listUpdateCallback = ((AsyncPagingDataDiffer) this.this$0).updateCallback;
                    listUpdateCallback.onRemoved(position, count);
                }
            }

            @Override // androidx.paging.DifferCallback
            public void onChanged(int position, int count) {
                ListUpdateCallback listUpdateCallback;
                if (count > 0) {
                    listUpdateCallback = ((AsyncPagingDataDiffer) this.this$0).updateCallback;
                    listUpdateCallback.onChanged(position, count, null);
                }
            }
        };
        this.differCallback = differCallback;
        AsyncPagingDataDiffer$differBase$1 asyncPagingDataDiffer$differBase$1 = new AsyncPagingDataDiffer$differBase$1(this, differCallback, mainDispatcher);
        this.differBase = asyncPagingDataDiffer$differBase$1;
        this.submitDataId = new AtomicInteger(0);
        this.loadStateFlow = asyncPagingDataDiffer$differBase$1.getLoadStateFlow();
        this.onPagesUpdatedFlow = asyncPagingDataDiffer$differBase$1.getOnPagesUpdatedFlow();
    }

    public /* synthetic */ AsyncPagingDataDiffer(DiffUtil.ItemCallback itemCallback, ListUpdateCallback listUpdateCallback, MainCoroutineDispatcher mainCoroutineDispatcher, CoroutineDispatcher coroutineDispatcher, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(itemCallback, listUpdateCallback, (i & 4) != 0 ? Dispatchers.getMain() : mainCoroutineDispatcher, (i & 8) != 0 ? Dispatchers.getDefault() : coroutineDispatcher);
    }

    /* renamed from: getDifferCallback$paging_runtime_release, reason: from getter */
    public final DifferCallback getDifferCallback() {
        return this.differCallback;
    }

    /* renamed from: getInGetItem$paging_runtime_release, reason: from getter */
    public final boolean getInGetItem() {
        return this.inGetItem;
    }

    public final void setInGetItem$paging_runtime_release(boolean z) {
        this.inGetItem = z;
    }

    public final Object submitData(PagingData<T> pagingData, Continuation<? super Unit> continuation) {
        this.submitDataId.incrementAndGet();
        Object collectFrom = this.differBase.collectFrom(pagingData, continuation);
        return collectFrom == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collectFrom : Unit.INSTANCE;
    }

    public final void submitData(Lifecycle lifecycle, PagingData<T> pagingData) {
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        Intrinsics.checkNotNullParameter(pagingData, "pagingData");
        BuildersKt__Builders_commonKt.launch$default(LifecycleKt.getCoroutineScope(lifecycle), null, null, new AsyncPagingDataDiffer$submitData$2(this, this.submitDataId.incrementAndGet(), pagingData, null), 3, null);
    }

    public final void retry() {
        this.differBase.retry();
    }

    public final void refresh() {
        this.differBase.refresh();
    }

    public final T getItem(int index) {
        try {
            this.inGetItem = true;
            return this.differBase.get(index);
        } finally {
            this.inGetItem = false;
        }
    }

    public final T peek(int index) {
        return this.differBase.peek(index);
    }

    public final ItemSnapshotList<T> snapshot() {
        return this.differBase.snapshot();
    }

    public final int getItemCount() {
        return this.differBase.getSize();
    }

    public final Flow<CombinedLoadStates> getLoadStateFlow() {
        return this.loadStateFlow;
    }

    public final Flow<Unit> getOnPagesUpdatedFlow() {
        return this.onPagesUpdatedFlow;
    }

    public final void addOnPagesUpdatedListener(Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.differBase.addOnPagesUpdatedListener(listener);
    }

    public final void removeOnPagesUpdatedListener(Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.differBase.removeOnPagesUpdatedListener(listener);
    }

    public final void addLoadStateListener(Function1<? super CombinedLoadStates, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.differBase.addLoadStateListener(listener);
    }

    public final void removeLoadStateListener(Function1<? super CombinedLoadStates, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.differBase.removeLoadStateListener(listener);
    }
}
