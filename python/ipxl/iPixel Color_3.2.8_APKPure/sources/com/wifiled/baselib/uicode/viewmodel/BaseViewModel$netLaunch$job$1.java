package com.wifiled.baselib.uicode.viewmodel;

import com.wifiled.baselib.uicode.net.CommonResponse;
import com.wifiled.baselib.uicode.net.NetExtKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: BaseViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.baselib.uicode.viewmodel.BaseViewModel$netLaunch$job$1", f = "BaseViewModel.kt", i = {}, l = {141}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class BaseViewModel$netLaunch$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Continuation<? super CommonResponse<T>>, Object> $block;
    final /* synthetic */ Function3<Integer, String, T, Unit> $failed;
    final /* synthetic */ Function2<String, T, Unit> $success;
    int label;
    final /* synthetic */ BaseViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    BaseViewModel$netLaunch$job$1(Function1<? super Continuation<? super CommonResponse<T>>, ? extends Object> function1, Function2<? super String, ? super T, Unit> function2, Function3<? super Integer, ? super String, ? super T, Unit> function3, BaseViewModel baseViewModel, Continuation<? super BaseViewModel$netLaunch$job$1> continuation) {
        super(2, continuation);
        this.$block = function1;
        this.$success = function2;
        this.$failed = function3;
        this.this$0 = baseViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseViewModel$netLaunch$job$1(this.$block, this.$success, this.$failed, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseViewModel$netLaunch$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Function1<Continuation<? super CommonResponse<T>>, Object> function1 = this.$block;
                this.label = 1;
                obj = function1.invoke(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            NetExtKt.process((CommonResponse) obj, this.$success, this.$failed);
        } catch (Throwable th) {
            this.this$0.onFailException(th, this.$failed);
        }
        return Unit.INSTANCE;
    }
}
