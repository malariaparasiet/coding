package com.wifiled.ipixels.ui.adapter;

import android.content.Context;
import com.wifiled.baselib.data.Record;
import com.wifiled.baselib.retrofit.CryptographicParsingToolKt;
import com.wifiled.ipixels.ui.adapter.EyeAnimAdapter;
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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* compiled from: EyeAnimAdapter.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.adapter.EyeAnimAdapter$onBindViewHolder$1$1", f = "EyeAnimAdapter.kt", i = {0, 0}, l = {94}, m = "invokeSuspend", n = {"it", "$i$a$-let-EyeAnimAdapter$onBindViewHolder$1$1$1"}, s = {"L$0", "I$0"})
/* loaded from: classes3.dex */
final class EyeAnimAdapter$onBindViewHolder$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ EyeAnimAdapter.ViewHolder $holder;
    final /* synthetic */ Record $item;
    final /* synthetic */ Ref.IntRef $placeholder;
    int I$0;
    Object L$0;
    int label;
    final /* synthetic */ EyeAnimAdapter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    EyeAnimAdapter$onBindViewHolder$1$1(Record record, EyeAnimAdapter eyeAnimAdapter, EyeAnimAdapter.ViewHolder viewHolder, Ref.IntRef intRef, Continuation<? super EyeAnimAdapter$onBindViewHolder$1$1> continuation) {
        super(2, continuation);
        this.$item = record;
        this.this$0 = eyeAnimAdapter;
        this.$holder = viewHolder;
        this.$placeholder = intRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EyeAnimAdapter$onBindViewHolder$1$1(this.$item, this.this$0, this.$holder, this.$placeholder, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EyeAnimAdapter$onBindViewHolder$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            String filePath = this.$item.getFilePath();
            context = this.this$0.context;
            File decryptedFile = CryptographicParsingToolKt.getDecryptedFile(filePath, 1, context);
            if (decryptedFile != null) {
                Record record = this.$item;
                EyeAnimAdapter.ViewHolder viewHolder = this.$holder;
                Ref.IntRef intRef = this.$placeholder;
                record.setFile(decryptedFile);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                EyeAnimAdapter$onBindViewHolder$1$1$1$1 eyeAnimAdapter$onBindViewHolder$1$1$1$1 = new EyeAnimAdapter$onBindViewHolder$1$1$1$1(viewHolder, decryptedFile, intRef, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(decryptedFile);
                this.I$0 = 0;
                this.label = 1;
                obj = BuildersKt.withContext(main, eyeAnimAdapter$onBindViewHolder$1$1$1$1, this);
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
