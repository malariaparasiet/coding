package com.wifiled.ipixels.ui.gallery;

import androidx.paging.PagingData;
import com.wifiled.baselib.data.Record;
import com.wifiled.ipixels.data.Paging3ViewEyeModel;
import com.wifiled.ipixels.ui.adapter.EyeAnimAdapter;
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

/* compiled from: DevilsEyeFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$onCreateView$3", f = "DevilsEyeFragment.kt", i = {}, l = {144}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class DevilsEyeFragment$onCreateView$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $filterTags;
    final /* synthetic */ String $language;
    final /* synthetic */ EyeAnimAdapter $localAdapter;
    final /* synthetic */ Lazy<Paging3ViewEyeModel> $viewModel$delegate;
    int label;
    final /* synthetic */ DevilsEyeFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DevilsEyeFragment$onCreateView$3(DevilsEyeFragment devilsEyeFragment, String str, String str2, Lazy<Paging3ViewEyeModel> lazy, EyeAnimAdapter eyeAnimAdapter, Continuation<? super DevilsEyeFragment$onCreateView$3> continuation) {
        super(2, continuation);
        this.this$0 = devilsEyeFragment;
        this.$language = str;
        this.$filterTags = str2;
        this.$viewModel$delegate = lazy;
        this.$localAdapter = eyeAnimAdapter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DevilsEyeFragment$onCreateView$3(this.this$0, this.$language, this.$filterTags, this.$viewModel$delegate, this.$localAdapter, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DevilsEyeFragment$onCreateView$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Paging3ViewEyeModel onCreateView$lambda$2;
        String str;
        String str2;
        String str3;
        int i;
        int i2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            onCreateView$lambda$2 = DevilsEyeFragment.onCreateView$lambda$2((Lazy<Paging3ViewEyeModel>) this.$viewModel$delegate);
            str = this.this$0.type;
            str2 = this.this$0.label;
            str3 = this.this$0.categoryName;
            i = this.this$0.mWidth;
            i2 = this.this$0.mHeight;
            Flow<PagingData<Record>> pagingData = onCreateView$lambda$2.getPagingData(str, str2, str3, i, i2, this.$language, this.$filterTags);
            final EyeAnimAdapter eyeAnimAdapter = this.$localAdapter;
            this.label = 1;
            if (pagingData.collect(new FlowCollector() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$onCreateView$3.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((PagingData<Record>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(PagingData<Record> pagingData2, Continuation<? super Unit> continuation) {
                    Object submitData = EyeAnimAdapter.this.submitData(pagingData2, continuation);
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
