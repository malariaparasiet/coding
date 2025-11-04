package com.wifiled.ipixels.ui.imgtxt;

import com.blankj.utilcode.util.FileIOUtils;
import com.wifiled.ipixels.ui.channel.ChannelListItem;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ImageTextActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.imgtxt.ImageTextActivity$mTextAnimationResult$1$1", f = "ImageTextActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class ImageTextActivity$mTextAnimationResult$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $trimPath;
    int label;
    final /* synthetic */ ImageTextActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ImageTextActivity$mTextAnimationResult$1$1(String str, ImageTextActivity imageTextActivity, Continuation<? super ImageTextActivity$mTextAnimationResult$1$1> continuation) {
        super(2, continuation);
        this.$trimPath = str;
        this.this$0 = imageTextActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ImageTextActivity$mTextAnimationResult$1$1(this.$trimPath, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ImageTextActivity$mTextAnimationResult$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        final byte[] readFile2BytesByChannel = FileIOUtils.readFile2BytesByChannel(this.$trimPath);
        final ImageTextActivity imageTextActivity = this.this$0;
        final String str = this.$trimPath;
        imageTextActivity.runOnUiThread(new Runnable() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextActivity$mTextAnimationResult$1$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ImageTextActivity$mTextAnimationResult$1$1.invokeSuspend$lambda$0(ImageTextActivity.this, str, readFile2BytesByChannel);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(ImageTextActivity imageTextActivity, String str, byte[] bArr) {
        int i;
        ImageTextMainAdatper imageTextMainAdatper;
        ImageTextMainAdatper imageTextMainAdatper2;
        int i2;
        ImageTextMainAdatper imageTextMainAdatper3;
        ImageTextMainAdatper imageTextMainAdatper4;
        ImageTextMainAdatper imageTextMainAdatper5;
        ImageTextMainAdatper imageTextMainAdatper6;
        int i3;
        i = imageTextActivity.mAdapterPosition;
        imageTextMainAdatper = imageTextActivity.mImageTextMainAdatper;
        ImageTextMainAdatper imageTextMainAdatper7 = null;
        if (imageTextMainAdatper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
            imageTextMainAdatper = null;
        }
        if (i != imageTextMainAdatper.getData().size() - 1) {
            imageTextMainAdatper6 = imageTextActivity.mImageTextMainAdatper;
            if (imageTextMainAdatper6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                imageTextMainAdatper6 = null;
            }
            i3 = imageTextActivity.mAdapterPosition;
            imageTextMainAdatper6.removeAt(i3);
        }
        imageTextMainAdatper2 = imageTextActivity.mImageTextMainAdatper;
        if (imageTextMainAdatper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
            imageTextMainAdatper2 = null;
        }
        i2 = imageTextActivity.mAdapterPosition;
        imageTextMainAdatper2.addData(i2, (int) new ChannelListItem.GiftView(false, str, "1", false, bArr));
        imageTextMainAdatper3 = imageTextActivity.mImageTextMainAdatper;
        if (imageTextMainAdatper3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
            imageTextMainAdatper3 = null;
        }
        if (imageTextMainAdatper3.getData().size() == 7) {
            imageTextMainAdatper5 = imageTextActivity.mImageTextMainAdatper;
            if (imageTextMainAdatper5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
            } else {
                imageTextMainAdatper7 = imageTextMainAdatper5;
            }
            imageTextMainAdatper7.setEditStatus(true);
            return;
        }
        imageTextMainAdatper4 = imageTextActivity.mImageTextMainAdatper;
        if (imageTextMainAdatper4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
        } else {
            imageTextMainAdatper7 = imageTextMainAdatper4;
        }
        imageTextMainAdatper7.setEditStatus(false);
    }
}
