package com.wifiled.ipixels.data;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: PagingSourceEye.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.data.PagingSourceEye", f = "PagingSourceEye.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {40, 43}, m = "load", n = {"params", "pageNo", "page", "pageSize", "params", "pageNo", "leftResponse", "page", "pageSize"}, s = {"L$0", "L$1", "I$0", "I$1", "L$0", "L$1", "L$2", "I$0", "I$1"})
/* loaded from: classes3.dex */
final class PagingSourceEye$load$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PagingSourceEye this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PagingSourceEye$load$1(PagingSourceEye pagingSourceEye, Continuation<? super PagingSourceEye$load$1> continuation) {
        super(continuation);
        this.this$0 = pagingSourceEye;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.load(null, this);
    }
}
