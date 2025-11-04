package androidx.paging;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: PagingData.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \r*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0001\rB#\b\u0000\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR \u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Landroidx/paging/PagingData;", ExifInterface.GPS_DIRECTION_TRUE, "", "flow", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PageEvent;", "receiver", "Landroidx/paging/UiReceiver;", "(Lkotlinx/coroutines/flow/Flow;Landroidx/paging/UiReceiver;)V", "getFlow$paging_common", "()Lkotlinx/coroutines/flow/Flow;", "getReceiver$paging_common", "()Landroidx/paging/UiReceiver;", "Companion", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class PagingData<T> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final PagingData<Object> EMPTY;
    private static final UiReceiver NOOP_RECEIVER;
    private final Flow<PageEvent<T>> flow;
    private final UiReceiver receiver;

    @JvmStatic
    public static final <T> PagingData<T> empty() {
        return INSTANCE.empty();
    }

    @JvmStatic
    public static final <T> PagingData<T> from(List<? extends T> list) {
        return INSTANCE.from(list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PagingData(Flow<? extends PageEvent<T>> flow, UiReceiver receiver) {
        Intrinsics.checkNotNullParameter(flow, "flow");
        Intrinsics.checkNotNullParameter(receiver, "receiver");
        this.flow = flow;
        this.receiver = receiver;
    }

    public final Flow<PageEvent<T>> getFlow$paging_common() {
        return this.flow;
    }

    /* renamed from: getReceiver$paging_common, reason: from getter */
    public final UiReceiver getReceiver() {
        return this.receiver;
    }

    /* compiled from: PagingData.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0004\"\b\b\u0001\u0010\r*\u00020\u0001H\u0007J&\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u0004\"\b\b\u0001\u0010\r*\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0010H\u0007R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0080\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, d2 = {"Landroidx/paging/PagingData$Companion;", "", "()V", "EMPTY", "Landroidx/paging/PagingData;", "getEMPTY$paging_common$annotations", "getEMPTY$paging_common", "()Landroidx/paging/PagingData;", "NOOP_RECEIVER", "Landroidx/paging/UiReceiver;", "getNOOP_RECEIVER$paging_common", "()Landroidx/paging/UiReceiver;", "empty", ExifInterface.GPS_DIRECTION_TRUE, TypedValues.TransitionType.S_FROM, "data", "", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getEMPTY$paging_common$annotations() {
        }

        private Companion() {
        }

        public final UiReceiver getNOOP_RECEIVER$paging_common() {
            return PagingData.NOOP_RECEIVER;
        }

        public final PagingData<Object> getEMPTY$paging_common() {
            return PagingData.EMPTY;
        }

        @JvmStatic
        public final <T> PagingData<T> empty() {
            return (PagingData<T>) getEMPTY$paging_common();
        }

        @JvmStatic
        public final <T> PagingData<T> from(List<? extends T> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new PagingData<>(FlowKt.flowOf(PageEvent.Insert.Companion.Refresh$default(PageEvent.Insert.INSTANCE, CollectionsKt.listOf(new TransformablePage(0, data)), 0, 0, new LoadStates(LoadState.NotLoading.INSTANCE.getIncomplete$paging_common(), LoadState.NotLoading.INSTANCE.getComplete$paging_common(), LoadState.NotLoading.INSTANCE.getComplete$paging_common()), null, 16, null)), getNOOP_RECEIVER$paging_common());
        }
    }

    static {
        UiReceiver uiReceiver = new UiReceiver() { // from class: androidx.paging.PagingData$Companion$NOOP_RECEIVER$1
            @Override // androidx.paging.UiReceiver
            public void accessHint(ViewportHint viewportHint) {
                Intrinsics.checkNotNullParameter(viewportHint, "viewportHint");
            }

            @Override // androidx.paging.UiReceiver
            public void refresh() {
            }

            @Override // androidx.paging.UiReceiver
            public void retry() {
            }
        };
        NOOP_RECEIVER = uiReceiver;
        EMPTY = new PagingData<>(FlowKt.flowOf(PageEvent.Insert.INSTANCE.getEMPTY_REFRESH_LOCAL()), uiReceiver);
    }
}
