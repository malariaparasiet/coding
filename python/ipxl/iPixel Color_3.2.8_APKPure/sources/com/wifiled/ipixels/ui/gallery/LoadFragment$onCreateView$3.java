package com.wifiled.ipixels.ui.gallery;

import androidx.paging.PagingData;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.wifiled.baselib.data.Record;
import com.wifiled.ipixels.data.Paging3ViewModel;
import com.wifiled.ipixels.ui.adapter.AnimAdapter;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: LoadFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.gallery.LoadFragment$onCreateView$3", f = "LoadFragment.kt", i = {}, l = {Opcodes.LCMP}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class LoadFragment$onCreateView$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $filterTags;
    final /* synthetic */ String $language;
    final /* synthetic */ AnimAdapter $localAdapter;
    final /* synthetic */ Lazy<Paging3ViewModel> $viewModel$delegate;
    int label;
    final /* synthetic */ LoadFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LoadFragment$onCreateView$3(LoadFragment loadFragment, String str, String str2, Lazy<Paging3ViewModel> lazy, AnimAdapter animAdapter, Continuation<? super LoadFragment$onCreateView$3> continuation) {
        super(2, continuation);
        this.this$0 = loadFragment;
        this.$language = str;
        this.$filterTags = str2;
        this.$viewModel$delegate = lazy;
        this.$localAdapter = animAdapter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LoadFragment$onCreateView$3(this.this$0, this.$language, this.$filterTags, this.$viewModel$delegate, this.$localAdapter, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LoadFragment$onCreateView$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Paging3ViewModel onCreateView$lambda$2;
        String str;
        String str2;
        String str3;
        int i;
        int i2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            onCreateView$lambda$2 = LoadFragment.onCreateView$lambda$2(this.$viewModel$delegate);
            str = this.this$0.type;
            str2 = this.this$0.label;
            str3 = this.this$0.categoryName;
            i = this.this$0.mWidth;
            i2 = this.this$0.mHeight;
            Flow<PagingData<Record>> pagingData = onCreateView$lambda$2.getPagingData(str, str2, str3, i, i2, this.$language, this.$filterTags);
            final AnimAdapter animAdapter = this.$localAdapter;
            this.label = 1;
            if (pagingData.collect(new FlowCollector() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$onCreateView$3.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((PagingData<Record>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(PagingData<Record> pagingData2, Continuation<? super Unit> continuation) {
                    Object submitData = AnimAdapter.this.submitData(pagingData2, continuation);
                    return submitData == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? submitData : Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
