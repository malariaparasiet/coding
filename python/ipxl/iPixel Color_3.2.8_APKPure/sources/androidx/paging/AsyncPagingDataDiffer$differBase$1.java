package androidx.paging;

import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: AsyncPagingDataDiffer.kt */
@Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016JE\u0010\u0004\u001a\u0004\u0018\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\t\u001a\u00020\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"androidx/paging/AsyncPagingDataDiffer$differBase$1", "Landroidx/paging/PagingDataDiffer;", "postEvents", "", "presentNewList", "", "previousList", "Landroidx/paging/NullPaddedList;", "newList", "lastAccessedIndex", "onListPresentable", "Lkotlin/Function0;", "", "(Landroidx/paging/NullPaddedList;Landroidx/paging/NullPaddedList;ILkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "paging-runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class AsyncPagingDataDiffer$differBase$1<T> extends PagingDataDiffer<T> {
    final /* synthetic */ AsyncPagingDataDiffer<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AsyncPagingDataDiffer$differBase$1(AsyncPagingDataDiffer<T> asyncPagingDataDiffer, DifferCallback differCallback, CoroutineDispatcher coroutineDispatcher) {
        super(differCallback, coroutineDispatcher);
        this.this$0 = asyncPagingDataDiffer;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // androidx.paging.PagingDataDiffer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object presentNewList(androidx.paging.NullPaddedList<T> r7, androidx.paging.NullPaddedList<T> r8, int r9, kotlin.jvm.functions.Function0<kotlin.Unit> r10, kotlin.coroutines.Continuation<? super java.lang.Integer> r11) {
        /*
            r6 = this;
            boolean r0 = r11 instanceof androidx.paging.AsyncPagingDataDiffer$differBase$1$presentNewList$1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.paging.AsyncPagingDataDiffer$differBase$1$presentNewList$1 r0 = (androidx.paging.AsyncPagingDataDiffer$differBase$1$presentNewList$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            androidx.paging.AsyncPagingDataDiffer$differBase$1$presentNewList$1 r0 = new androidx.paging.AsyncPagingDataDiffer$differBase$1$presentNewList$1
            r0.<init>(r6, r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L46
            if (r2 != r3) goto L3e
            int r9 = r0.I$0
            java.lang.Object r7 = r0.L$3
            r10 = r7
            kotlin.jvm.functions.Function0 r10 = (kotlin.jvm.functions.Function0) r10
            java.lang.Object r7 = r0.L$2
            r8 = r7
            androidx.paging.NullPaddedList r8 = (androidx.paging.NullPaddedList) r8
            java.lang.Object r7 = r0.L$1
            androidx.paging.NullPaddedList r7 = (androidx.paging.NullPaddedList) r7
            java.lang.Object r0 = r0.L$0
            androidx.paging.AsyncPagingDataDiffer$differBase$1 r0 = (androidx.paging.AsyncPagingDataDiffer$differBase$1) r0
            kotlin.ResultKt.throwOnFailure(r11)
            goto La4
        L3e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L46:
            kotlin.ResultKt.throwOnFailure(r11)
            int r11 = r7.getSize()
            r2 = 0
            r4 = 0
            if (r11 != 0) goto L65
            r10.invoke()
            androidx.paging.AsyncPagingDataDiffer<T> r7 = r6.this$0
            androidx.paging.DifferCallback r7 = r7.getDifferCallback()
            int r8 = r8.getSize()
            r7.onInserted(r2, r8)
            r7 = r4
            java.lang.Integer r7 = (java.lang.Integer) r7
            return r4
        L65:
            int r11 = r8.getSize()
            if (r11 != 0) goto L7f
            r10.invoke()
            androidx.paging.AsyncPagingDataDiffer<T> r8 = r6.this$0
            androidx.paging.DifferCallback r8 = r8.getDifferCallback()
            int r7 = r7.getSize()
            r8.onRemoved(r2, r7)
            r7 = r4
            java.lang.Integer r7 = (java.lang.Integer) r7
            return r4
        L7f:
            androidx.paging.AsyncPagingDataDiffer<T> r11 = r6.this$0
            kotlinx.coroutines.CoroutineDispatcher r11 = androidx.paging.AsyncPagingDataDiffer.access$getWorkerDispatcher$p(r11)
            kotlin.coroutines.CoroutineContext r11 = (kotlin.coroutines.CoroutineContext) r11
            androidx.paging.AsyncPagingDataDiffer$differBase$1$presentNewList$diffResult$1 r2 = new androidx.paging.AsyncPagingDataDiffer$differBase$1$presentNewList$diffResult$1
            androidx.paging.AsyncPagingDataDiffer<T> r5 = r6.this$0
            r2.<init>(r7, r8, r5, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.L$3 = r10
            r0.I$0 = r9
            r0.label = r3
            java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r11, r2, r0)
            if (r11 != r1) goto La3
            return r1
        La3:
            r0 = r6
        La4:
            androidx.paging.NullPaddedDiffResult r11 = (androidx.paging.NullPaddedDiffResult) r11
            r10.invoke()
            androidx.paging.AsyncPagingDataDiffer<T> r10 = r0.this$0
            androidx.recyclerview.widget.ListUpdateCallback r10 = androidx.paging.AsyncPagingDataDiffer.access$getUpdateCallback$p(r10)
            androidx.paging.NullPaddedListDiffHelperKt.dispatchDiff(r7, r10, r8, r11)
            int r7 = androidx.paging.NullPaddedListDiffHelperKt.transformAnchorIndex(r7, r11, r8, r9)
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.AsyncPagingDataDiffer$differBase$1.presentNewList(androidx.paging.NullPaddedList, androidx.paging.NullPaddedList, int, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // androidx.paging.PagingDataDiffer
    public boolean postEvents() {
        return this.this$0.getInGetItem();
    }
}
