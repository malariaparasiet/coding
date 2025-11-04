package androidx.paging;

import androidx.autofill.HintConstants;
import androidx.paging.HintHandler;
import androidx.paging.ViewportHint;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: HintHandler.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0002\u0012\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00102\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0011\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/paging/HintHandler;", "", "()V", "lastAccessHint", "Landroidx/paging/ViewportHint$Access;", "getLastAccessHint", "()Landroidx/paging/ViewportHint$Access;", PlayerFinal.STATE, "Landroidx/paging/HintHandler$State;", "forceSetHint", "", "loadType", "Landroidx/paging/LoadType;", "viewportHint", "Landroidx/paging/ViewportHint;", "hintFor", "Lkotlinx/coroutines/flow/Flow;", "processHint", "HintFlow", "State", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class HintHandler {
    private final State state = new State(this);

    /* compiled from: HintHandler.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoadType.values().length];
            iArr[LoadType.PREPEND.ordinal()] = 1;
            iArr[LoadType.APPEND.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final ViewportHint.Access getLastAccessHint() {
        return this.state.getLastAccessHint();
    }

    public final Flow<ViewportHint> hintFor(LoadType loadType) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        int i = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
        if (i == 1) {
            return this.state.getPrependFlow();
        }
        if (i == 2) {
            return this.state.getAppendFlow();
        }
        throw new IllegalArgumentException("invalid load type for hints");
    }

    public final void forceSetHint(final LoadType loadType, final ViewportHint viewportHint) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(viewportHint, "viewportHint");
        if (!(loadType == LoadType.PREPEND || loadType == LoadType.APPEND)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("invalid load type for reset: ", loadType).toString());
        }
        this.state.modify(null, new Function2<HintFlow, HintFlow, Unit>() { // from class: androidx.paging.HintHandler$forceSetHint$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(HintHandler.HintFlow hintFlow, HintHandler.HintFlow hintFlow2) {
                invoke2(hintFlow, hintFlow2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HintHandler.HintFlow prependHint, HintHandler.HintFlow appendHint) {
                Intrinsics.checkNotNullParameter(prependHint, "prependHint");
                Intrinsics.checkNotNullParameter(appendHint, "appendHint");
                if (LoadType.this == LoadType.PREPEND) {
                    prependHint.setValue(viewportHint);
                } else {
                    appendHint.setValue(viewportHint);
                }
            }
        });
    }

    public final void processHint(final ViewportHint viewportHint) {
        Intrinsics.checkNotNullParameter(viewportHint, "viewportHint");
        this.state.modify(viewportHint instanceof ViewportHint.Access ? (ViewportHint.Access) viewportHint : null, new Function2<HintFlow, HintFlow, Unit>() { // from class: androidx.paging.HintHandler$processHint$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(HintHandler.HintFlow hintFlow, HintHandler.HintFlow hintFlow2) {
                invoke2(hintFlow, hintFlow2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HintHandler.HintFlow prependHint, HintHandler.HintFlow appendHint) {
                Intrinsics.checkNotNullParameter(prependHint, "prependHint");
                Intrinsics.checkNotNullParameter(appendHint, "appendHint");
                if (HintHandlerKt.shouldPrioritizeOver(ViewportHint.this, prependHint.getValue(), LoadType.PREPEND)) {
                    prependHint.setValue(ViewportHint.this);
                }
                if (HintHandlerKt.shouldPrioritizeOver(ViewportHint.this, appendHint.getValue(), LoadType.APPEND)) {
                    appendHint.setValue(ViewportHint.this);
                }
            }
        });
    }

    /* compiled from: HintHandler.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JP\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\f2>\u0010\u0018\u001a:\u0012\u0017\u0012\u00150\u0004R\u00020\u0005¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0012\u0012\u0017\u0012\u00150\u0004R\u00020\u0005¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00020\u00160\u0019R\u0012\u0010\u0003\u001a\u00060\u0004R\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\"\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00060\u0004R\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u00078F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\n¨\u0006\u001c"}, d2 = {"Landroidx/paging/HintHandler$State;", "", "(Landroidx/paging/HintHandler;)V", "append", "Landroidx/paging/HintHandler$HintFlow;", "Landroidx/paging/HintHandler;", "appendFlow", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/ViewportHint;", "getAppendFlow", "()Lkotlinx/coroutines/flow/Flow;", "<set-?>", "Landroidx/paging/ViewportHint$Access;", "lastAccessHint", "getLastAccessHint", "()Landroidx/paging/ViewportHint$Access;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "prepend", "prependFlow", "getPrependFlow", "modify", "", "accessHint", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    private final class State {
        private final HintFlow append;
        private ViewportHint.Access lastAccessHint;
        private final ReentrantLock lock;
        private final HintFlow prepend;
        final /* synthetic */ HintHandler this$0;

        public State(HintHandler this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.prepend = new HintFlow(this$0);
            this.append = new HintFlow(this$0);
            this.lock = new ReentrantLock();
        }

        public final ViewportHint.Access getLastAccessHint() {
            return this.lastAccessHint;
        }

        public final Flow<ViewportHint> getPrependFlow() {
            return this.prepend.getFlow();
        }

        public final Flow<ViewportHint> getAppendFlow() {
            return this.append.getFlow();
        }

        public final void modify(ViewportHint.Access accessHint, Function2<? super HintFlow, ? super HintFlow, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            if (accessHint != null) {
                try {
                    this.lastAccessHint = accessHint;
                } finally {
                    reentrantLock.unlock();
                }
            }
            block.invoke(this.prepend, this.append);
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: HintHandler.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR(\u0010\n\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/paging/HintHandler$HintFlow;", "", "(Landroidx/paging/HintHandler;)V", "_flow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Landroidx/paging/ViewportHint;", "flow", "Lkotlinx/coroutines/flow/Flow;", "getFlow", "()Lkotlinx/coroutines/flow/Flow;", "value", "getValue", "()Landroidx/paging/ViewportHint;", "setValue", "(Landroidx/paging/ViewportHint;)V", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    final class HintFlow {
        private final MutableSharedFlow<ViewportHint> _flow;
        final /* synthetic */ HintHandler this$0;
        private ViewportHint value;

        public HintFlow(HintHandler this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this._flow = SharedFlowKt.MutableSharedFlow$default(1, 0, BufferOverflow.DROP_OLDEST, 2, null);
        }

        public final ViewportHint getValue() {
            return this.value;
        }

        public final void setValue(ViewportHint viewportHint) {
            this.value = viewportHint;
            if (viewportHint != null) {
                this._flow.tryEmit(viewportHint);
            }
        }

        public final Flow<ViewportHint> getFlow() {
            return this._flow;
        }
    }
}
