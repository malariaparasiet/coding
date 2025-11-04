package com.wifiled.ipixels.ui.control;

import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.baselib.data.Record;
import com.wifiled.baselib.retrofit.CryptographicParsingToolKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* compiled from: RemoteControlActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.control.RemoteControlActivity$bindData$4$convert$1", f = "RemoteControlActivity.kt", i = {0, 0, 0}, l = {244}, m = "invokeSuspend", n = {"decodedFile", "it", "$i$a$-let-RemoteControlActivity$bindData$4$convert$1$1"}, s = {"L$0", "L$1", "I$0"})
/* loaded from: classes3.dex */
final class RemoteControlActivity$bindData$4$convert$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Record $gif;
    final /* synthetic */ RecyclerViewHolder $holder;
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ RemoteControlActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RemoteControlActivity$bindData$4$convert$1(Record record, RemoteControlActivity remoteControlActivity, RecyclerViewHolder recyclerViewHolder, Continuation<? super RemoteControlActivity$bindData$4$convert$1> continuation) {
        super(2, continuation);
        this.$gif = record;
        this.this$0 = remoteControlActivity;
        this.$holder = recyclerViewHolder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteControlActivity$bindData$4$convert$1(this.$gif, this.this$0, this.$holder, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteControlActivity$bindData$4$convert$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String filePath;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Record record = this.$gif;
            File decryptedFile = (record == null || (filePath = record.getFilePath()) == null) ? null : CryptographicParsingToolKt.getDecryptedFile(filePath, 1, this.this$0);
            if (decryptedFile != null) {
                Record record2 = this.$gif;
                RemoteControlActivity remoteControlActivity = this.this$0;
                RecyclerViewHolder recyclerViewHolder = this.$holder;
                if (record2 != null) {
                    record2.setFile(decryptedFile);
                }
                MainCoroutineDispatcher main = Dispatchers.getMain();
                RemoteControlActivity$bindData$4$convert$1$1$1 remoteControlActivity$bindData$4$convert$1$1$1 = new RemoteControlActivity$bindData$4$convert$1$1$1(remoteControlActivity, decryptedFile, recyclerViewHolder, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(decryptedFile);
                this.L$1 = SpillingKt.nullOutSpilledVariable(decryptedFile);
                this.I$0 = 0;
                this.label = 1;
                if (BuildersKt.withContext(main, remoteControlActivity$bindData$4$convert$1$1$1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
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
