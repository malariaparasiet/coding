package androidx.paging;

import androidx.paging.AccessorState;
import androidx.paging.LoadState;
import androidx.paging.RemoteMediator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: RemoteMediatorAccessor.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Key", "", "Value", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1", f = "RemoteMediatorAccessor.kt", i = {0}, l = {266}, m = "invokeSuspend", n = {"launchAppendPrepend"}, s = {"L$0"})
/* loaded from: classes2.dex */
final class RemoteMediatorAccessImpl$launchRefresh$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ RemoteMediatorAccessImpl<Key, Value> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RemoteMediatorAccessImpl$launchRefresh$1(RemoteMediatorAccessImpl<Key, Value> remoteMediatorAccessImpl, Continuation<? super RemoteMediatorAccessImpl$launchRefresh$1> continuation) {
        super(2, continuation);
        this.this$0 = remoteMediatorAccessImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteMediatorAccessImpl$launchRefresh$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteMediatorAccessImpl$launchRefresh$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SingleRunner singleRunner;
        Ref.BooleanRef booleanRef;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
            singleRunner = ((RemoteMediatorAccessImpl) this.this$0).isolationRunner;
            this.L$0 = booleanRef2;
            this.label = 1;
            if (singleRunner.runInIsolation(2, new AnonymousClass1(this.this$0, booleanRef2, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            booleanRef = booleanRef2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            booleanRef = (Ref.BooleanRef) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (booleanRef.element) {
            this.this$0.launchBoundary();
        }
        return Unit.INSTANCE;
    }

    /* compiled from: RemoteMediatorAccessor.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Key", "", "Value"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1$1", f = "RemoteMediatorAccessor.kt", i = {}, l = {273}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.BooleanRef $launchAppendPrepend;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ RemoteMediatorAccessImpl<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(RemoteMediatorAccessImpl<Key, Value> remoteMediatorAccessImpl, Ref.BooleanRef booleanRef, Continuation<? super AnonymousClass1> continuation) {
            super(1, continuation);
            this.this$0 = remoteMediatorAccessImpl;
            this.$launchAppendPrepend = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$launchAppendPrepend, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AccessorStateHolder accessorStateHolder;
            RemoteMediatorAccessImpl remoteMediatorAccessImpl;
            RemoteMediator remoteMediator;
            Ref.BooleanRef booleanRef;
            AccessorStateHolder accessorStateHolder2;
            boolean booleanValue;
            AccessorStateHolder accessorStateHolder3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                accessorStateHolder = ((RemoteMediatorAccessImpl) this.this$0).accessorState;
                PagingState pagingState = (PagingState) accessorStateHolder.use(new Function1<AccessorState<Key, Value>, PagingState<Key, Value>>() { // from class: androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1$1$pendingPagingState$1
                    @Override // kotlin.jvm.functions.Function1
                    public final PagingState<Key, Value> invoke(AccessorState<Key, Value> it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return it.getPendingRefresh();
                    }
                });
                if (pagingState != null) {
                    remoteMediatorAccessImpl = this.this$0;
                    Ref.BooleanRef booleanRef2 = this.$launchAppendPrepend;
                    remoteMediator = remoteMediatorAccessImpl.remoteMediator;
                    LoadType loadType = LoadType.REFRESH;
                    this.L$0 = remoteMediatorAccessImpl;
                    this.L$1 = booleanRef2;
                    this.label = 1;
                    obj = remoteMediator.load(loadType, pagingState, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    booleanRef = booleanRef2;
                }
                return Unit.INSTANCE;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            booleanRef = (Ref.BooleanRef) this.L$1;
            remoteMediatorAccessImpl = (RemoteMediatorAccessImpl) this.L$0;
            ResultKt.throwOnFailure(obj);
            final RemoteMediator.MediatorResult mediatorResult = (RemoteMediator.MediatorResult) obj;
            if (mediatorResult instanceof RemoteMediator.MediatorResult.Success) {
                accessorStateHolder3 = remoteMediatorAccessImpl.accessorState;
                booleanValue = ((Boolean) accessorStateHolder3.use(new Function1<AccessorState<Key, Value>, Boolean>() { // from class: androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1$1$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(AccessorState<Key, Value> it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        it.clearPendingRequest(LoadType.REFRESH);
                        if (((RemoteMediator.MediatorResult.Success) RemoteMediator.MediatorResult.this).getEndOfPaginationReached()) {
                            it.setBlockState(LoadType.REFRESH, AccessorState.BlockState.COMPLETED);
                            it.setBlockState(LoadType.PREPEND, AccessorState.BlockState.COMPLETED);
                            it.setBlockState(LoadType.APPEND, AccessorState.BlockState.COMPLETED);
                            it.clearPendingRequests();
                        } else {
                            it.setBlockState(LoadType.PREPEND, AccessorState.BlockState.UNBLOCKED);
                            it.setBlockState(LoadType.APPEND, AccessorState.BlockState.UNBLOCKED);
                        }
                        it.setError(LoadType.PREPEND, null);
                        it.setError(LoadType.APPEND, null);
                        return Boolean.valueOf(it.getPendingBoundary() != null);
                    }
                })).booleanValue();
            } else if (mediatorResult instanceof RemoteMediator.MediatorResult.Error) {
                accessorStateHolder2 = remoteMediatorAccessImpl.accessorState;
                booleanValue = ((Boolean) accessorStateHolder2.use(new Function1<AccessorState<Key, Value>, Boolean>() { // from class: androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1$1$1$2
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(AccessorState<Key, Value> it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        it.clearPendingRequest(LoadType.REFRESH);
                        it.setError(LoadType.REFRESH, new LoadState.Error(((RemoteMediator.MediatorResult.Error) RemoteMediator.MediatorResult.this).getThrowable()));
                        return Boolean.valueOf(it.getPendingBoundary() != null);
                    }
                })).booleanValue();
            } else {
                throw new NoWhenBranchMatchedException();
            }
            booleanRef.element = booleanValue;
            return Unit.INSTANCE;
        }
    }
}
