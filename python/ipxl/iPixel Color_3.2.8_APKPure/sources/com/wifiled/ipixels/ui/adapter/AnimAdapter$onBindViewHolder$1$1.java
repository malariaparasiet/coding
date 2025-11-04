package com.wifiled.ipixels.ui.adapter;

import android.content.Context;
import com.wifiled.baselib.data.Record;
import com.wifiled.baselib.retrofit.CryptographicParsingToolKt;
import com.wifiled.ipixels.ui.adapter.AnimAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.SocketException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* compiled from: AnimAdapter.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.adapter.AnimAdapter$onBindViewHolder$1$1", f = "AnimAdapter.kt", i = {0, 0, 0}, l = {103}, m = "invokeSuspend", n = {"it", "type", "$i$a$-let-AnimAdapter$onBindViewHolder$1$1$1"}, s = {"L$0", "I$0", "I$1"})
/* loaded from: classes3.dex */
final class AnimAdapter$onBindViewHolder$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AnimAdapter.ViewHolder $holder;
    final /* synthetic */ Record $item;
    final /* synthetic */ Ref.IntRef $placeholder;
    int I$0;
    int I$1;
    Object L$0;
    int label;
    final /* synthetic */ AnimAdapter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AnimAdapter$onBindViewHolder$1$1(Record record, AnimAdapter animAdapter, AnimAdapter.ViewHolder viewHolder, Ref.IntRef intRef, Continuation<? super AnimAdapter$onBindViewHolder$1$1> continuation) {
        super(2, continuation);
        this.$item = record;
        this.this$0 = animAdapter;
        this.$holder = viewHolder;
        this.$placeholder = intRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AnimAdapter$onBindViewHolder$1$1(this.$item, this.this$0, this.$holder, this.$placeholder, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AnimAdapter$onBindViewHolder$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Context context;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SocketException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            boolean areEqual = Intrinsics.areEqual(this.$item.getType(), "动画");
            String filePath = this.$item.getFilePath();
            context = this.this$0.context;
            File decryptedFile = CryptographicParsingToolKt.getDecryptedFile(filePath, areEqual ? 1 : 0, context);
            if (decryptedFile != null) {
                Record record = this.$item;
                AnimAdapter.ViewHolder viewHolder = this.$holder;
                Ref.IntRef intRef = this.$placeholder;
                record.setFile(decryptedFile);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnimAdapter$onBindViewHolder$1$1$1$1 animAdapter$onBindViewHolder$1$1$1$1 = new AnimAdapter$onBindViewHolder$1$1$1$1(viewHolder, decryptedFile, intRef, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(decryptedFile);
                this.I$0 = areEqual ? 1 : 0;
                this.I$1 = 0;
                this.label = 1;
                obj = BuildersKt.withContext(main, animAdapter$onBindViewHolder$1$1$1$1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return Unit.INSTANCE;
    }
}
