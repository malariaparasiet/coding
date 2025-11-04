package com.wifiled.ipixels.ui.control;

import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.ipixels.R;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: RemoteControlActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.control.RemoteControlActivity$bindData$4$convert$1$1$1", f = "RemoteControlActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class RemoteControlActivity$bindData$4$convert$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ RecyclerViewHolder $holder;
    final /* synthetic */ File $it;
    int label;
    final /* synthetic */ RemoteControlActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RemoteControlActivity$bindData$4$convert$1$1$1(RemoteControlActivity remoteControlActivity, File file, RecyclerViewHolder recyclerViewHolder, Continuation<? super RemoteControlActivity$bindData$4$convert$1$1$1> continuation) {
        super(2, continuation);
        this.this$0 = remoteControlActivity;
        this.$it = file;
        this.$holder = recyclerViewHolder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteControlActivity$bindData$4$convert$1$1$1(this.this$0, this.$it, this.$holder, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteControlActivity$bindData$4$convert$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Glide.with((FragmentActivity) this.this$0).asGif().load(this.$it).diskCacheStrategy(DiskCacheStrategy.NONE).into((ImageView) this.$holder.getView(R.id.iv_anim_preview));
        return Unit.INSTANCE;
    }
}
