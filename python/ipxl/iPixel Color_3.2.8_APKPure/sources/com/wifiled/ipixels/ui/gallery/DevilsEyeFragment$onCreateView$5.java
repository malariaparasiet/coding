package com.wifiled.ipixels.ui.gallery;

import android.widget.ImageView;
import androidx.paging.CombinedLoadStates;
import androidx.paging.LoadState;
import com.blankj.utilcode.util.LogUtils;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.wifiled.ipixels.ui.adapter.EyeAnimAdapter;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: DevilsEyeFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$onCreateView$5", f = "DevilsEyeFragment.kt", i = {}, l = {197}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class DevilsEyeFragment$onCreateView$5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.BooleanRef $isRetry;
    final /* synthetic */ EyeAnimAdapter $localAdapter;
    final /* synthetic */ SmartRefreshLayout $refreshLayout;
    int label;
    final /* synthetic */ DevilsEyeFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DevilsEyeFragment$onCreateView$5(EyeAnimAdapter eyeAnimAdapter, DevilsEyeFragment devilsEyeFragment, SmartRefreshLayout smartRefreshLayout, Ref.BooleanRef booleanRef, Continuation<? super DevilsEyeFragment$onCreateView$5> continuation) {
        super(2, continuation);
        this.$localAdapter = eyeAnimAdapter;
        this.this$0 = devilsEyeFragment;
        this.$refreshLayout = smartRefreshLayout;
        this.$isRetry = booleanRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DevilsEyeFragment$onCreateView$5(this.$localAdapter, this.this$0, this.$refreshLayout, this.$isRetry, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DevilsEyeFragment$onCreateView$5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow<CombinedLoadStates> loadStateFlow = this.$localAdapter.getLoadStateFlow();
            final DevilsEyeFragment devilsEyeFragment = this.this$0;
            final SmartRefreshLayout smartRefreshLayout = this.$refreshLayout;
            final Ref.BooleanRef booleanRef = this.$isRetry;
            final EyeAnimAdapter eyeAnimAdapter = this.$localAdapter;
            this.label = 1;
            if (loadStateFlow.collect(new FlowCollector() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$onCreateView$5.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((CombinedLoadStates) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(CombinedLoadStates combinedLoadStates, Continuation<? super Unit> continuation) {
                    String str;
                    ImageView imageView;
                    ImageView imageView2;
                    String str2;
                    String str3;
                    String str4;
                    ImageView imageView3;
                    LoadState refresh = combinedLoadStates.getRefresh();
                    ImageView imageView4 = null;
                    if (refresh instanceof LoadState.NotLoading) {
                        imageView3 = DevilsEyeFragment.this.null_err;
                        if (imageView3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("null_err");
                        } else {
                            imageView4 = imageView3;
                        }
                        imageView4.setVisibility(8);
                        smartRefreshLayout.finishRefresh();
                        booleanRef.element = false;
                    } else if (!(refresh instanceof LoadState.Loading)) {
                        if (!(refresh instanceof LoadState.Error)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        smartRefreshLayout.finishRefresh(false);
                        if (eyeAnimAdapter.getItemCount() == 0) {
                            imageView = DevilsEyeFragment.this.null_err;
                            if (imageView == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("null_err");
                            }
                            imageView2 = DevilsEyeFragment.this.null_err;
                            if (imageView2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("null_err");
                            } else {
                                imageView4 = imageView2;
                            }
                            imageView4.setVisibility(0);
                        }
                        str = DevilsEyeFragment.this.categoryName;
                        LogUtils.i("请求远程数据--" + str + "--finishRefresh(false)");
                    }
                    LoadState append = combinedLoadStates.getAppend();
                    if (append instanceof LoadState.NotLoading) {
                        str4 = DevilsEyeFragment.this.categoryName;
                        LogUtils.i("请求远程数据--" + str4 + "--是否加载到最后" + combinedLoadStates.getAppend().getEndOfPaginationReached() + " ---finishLoadMore");
                        if (combinedLoadStates.getAppend().getEndOfPaginationReached()) {
                            smartRefreshLayout.finishLoadMoreWithNoMoreData();
                        } else {
                            smartRefreshLayout.finishLoadMore();
                        }
                        booleanRef.element = false;
                    } else if (append instanceof LoadState.Loading) {
                        str3 = DevilsEyeFragment.this.categoryName;
                        LogUtils.i("请求远程数据--" + str3 + "--finishLoadMore(3000)");
                    } else if (append instanceof LoadState.Error) {
                        str2 = DevilsEyeFragment.this.categoryName;
                        LogUtils.i("请求远程数据--" + str2 + "--finishLoadMore(false)");
                        booleanRef.element = true;
                        smartRefreshLayout.finishLoadMore(false);
                    } else {
                        throw new NoWhenBranchMatchedException();
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
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
