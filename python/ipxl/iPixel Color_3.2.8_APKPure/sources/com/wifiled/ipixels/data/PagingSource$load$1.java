package com.wifiled.ipixels.data;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: PagingSource.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.data.PagingSource", f = "PagingSource.kt", i = {0, 0, 0, 0}, l = {40}, m = "load", n = {"params", "pageNo", "page", "pageSize"}, s = {"L$0", "L$1", "I$0", "I$1"})
/* loaded from: classes3.dex */
final class PagingSource$load$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PagingSource this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PagingSource$load$1(PagingSource pagingSource, Continuation<? super PagingSource$load$1> continuation) {
        super(continuation);
        this.this$0 = pagingSource;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.load(null, this);
    }
}
