package androidx.compose.ui.input.pointer;

import com.alibaba.fastjson2.internal.asm.Opcodes;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SuspendingPointerInputFilter.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt$pointerInput$2$2$1", f = "SuspendingPointerInputFilter.kt", i = {}, l = {Opcodes.GOTO}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class SuspendingPointerInputFilterKt$pointerInput$2$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<PointerInputScope, Continuation<? super Unit>, Object> $block;
    final /* synthetic */ SuspendingPointerInputFilter $this_apply;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SuspendingPointerInputFilterKt$pointerInput$2$2$1(Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object> function2, SuspendingPointerInputFilter suspendingPointerInputFilter, Continuation<? super SuspendingPointerInputFilterKt$pointerInput$2$2$1> continuation) {
        super(2, continuation);
        this.$block = function2;
        this.$this_apply = suspendingPointerInputFilter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SuspendingPointerInputFilterKt$pointerInput$2$2$1(this.$block, this.$this_apply, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SuspendingPointerInputFilterKt$pointerInput$2$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function2<PointerInputScope, Continuation<? super Unit>, Object> function2 = this.$block;
            SuspendingPointerInputFilter suspendingPointerInputFilter = this.$this_apply;
            this.label = 1;
            if (function2.invoke(suspendingPointerInputFilter, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
