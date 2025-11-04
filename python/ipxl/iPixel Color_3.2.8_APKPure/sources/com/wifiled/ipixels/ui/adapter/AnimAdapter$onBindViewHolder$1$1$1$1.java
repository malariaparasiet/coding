package com.wifiled.ipixels.ui.adapter;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ViewTarget;
import com.wifiled.ipixels.ui.adapter.AnimAdapter;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AnimAdapter.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a#\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00040\u00040\u0001¢\u0006\u0002\b\u0005*\u00020\u0006H\n"}, d2 = {"<anonymous>", "Lcom/bumptech/glide/request/target/ViewTarget;", "Landroid/widget/ImageView;", "kotlin.jvm.PlatformType", "Landroid/graphics/drawable/Drawable;", "Lkotlin/jvm/internal/EnhancedNullability;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.adapter.AnimAdapter$onBindViewHolder$1$1$1$1", f = "AnimAdapter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class AnimAdapter$onBindViewHolder$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ViewTarget<ImageView, Drawable>>, Object> {
    final /* synthetic */ AnimAdapter.ViewHolder $holder;
    final /* synthetic */ File $it;
    final /* synthetic */ Ref.IntRef $placeholder;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AnimAdapter$onBindViewHolder$1$1$1$1(AnimAdapter.ViewHolder viewHolder, File file, Ref.IntRef intRef, Continuation<? super AnimAdapter$onBindViewHolder$1$1$1$1> continuation) {
        super(2, continuation);
        this.$holder = viewHolder;
        this.$it = file;
        this.$placeholder = intRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AnimAdapter$onBindViewHolder$1$1$1$1(this.$holder, this.$it, this.$placeholder, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ViewTarget<ImageView, Drawable>> continuation) {
        return ((AnimAdapter$onBindViewHolder$1$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return Glide.with(this.$holder.itemView).load(this.$it).placeholder(this.$placeholder.element).diskCacheStrategy(DiskCacheStrategy.NONE).into(this.$holder.getPreview());
    }
}
