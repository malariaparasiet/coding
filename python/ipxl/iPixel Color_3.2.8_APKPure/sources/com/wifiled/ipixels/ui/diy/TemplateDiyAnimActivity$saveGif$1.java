package com.wifiled.ipixels.ui.diy;

import android.graphics.Bitmap;
import com.squareup.gifencoder.Image;
import com.wifiled.ipixels.callback.CallBack;
import com.wifiled.ipixels.core.GifCore;
import com.wifiled.ipixels.utils.BitmapUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: TemplateDiyAnimActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.diy.TemplateDiyAnimActivity$saveGif$1", f = "TemplateDiyAnimActivity.kt", i = {}, l = {812}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class TemplateDiyAnimActivity$saveGif$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<Bitmap> $bitmaps;
    final /* synthetic */ CallBack $callback;
    int label;
    final /* synthetic */ TemplateDiyAnimActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TemplateDiyAnimActivity$saveGif$1(TemplateDiyAnimActivity templateDiyAnimActivity, List<Bitmap> list, CallBack callBack, Continuation<? super TemplateDiyAnimActivity$saveGif$1> continuation) {
        super(2, continuation);
        this.this$0 = templateDiyAnimActivity;
        this.$bitmaps = list;
        this.$callback = callBack;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TemplateDiyAnimActivity$saveGif$1(this.this$0, this.$bitmaps, this.$callback, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TemplateDiyAnimActivity$saveGif$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: TemplateDiyAnimActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.wifiled.ipixels.ui.diy.TemplateDiyAnimActivity$saveGif$1$1", f = "TemplateDiyAnimActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.wifiled.ipixels.ui.diy.TemplateDiyAnimActivity$saveGif$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<Bitmap> $bitmaps;
        final /* synthetic */ CallBack $callback;
        int label;
        final /* synthetic */ TemplateDiyAnimActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(TemplateDiyAnimActivity templateDiyAnimActivity, List<Bitmap> list, CallBack callBack, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = templateDiyAnimActivity;
            this.$bitmaps = list;
            this.$callback = callBack;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$bitmaps, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ArrayList arrayList;
            ArrayList arrayList2;
            int i;
            int i2;
            int i3;
            int i4;
            ArrayList arrayList3;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                arrayList = this.this$0.diyImage;
                arrayList.clear();
                List<Bitmap> list = this.$bitmaps;
                TemplateDiyAnimActivity templateDiyAnimActivity = this.this$0;
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    int[] rGBArray = BitmapUtils.INSTANCE.getRGBArray((Bitmap) it.next());
                    i4 = templateDiyAnimActivity.mTemplateWidth;
                    Image fromRgb = Image.fromRgb(rGBArray, i4);
                    arrayList3 = templateDiyAnimActivity.diyImage;
                    arrayList3.add(fromRgb);
                }
                GifCore gifCore = GifCore.INSTANCE;
                arrayList2 = this.this$0.diyImage;
                i = this.this$0.duration;
                i2 = this.this$0.mTemplateWidth;
                i3 = this.this$0.mTemplateHeight;
                String newBitmaps2TemplateGif = gifCore.newBitmaps2TemplateGif(arrayList2, 3, i, i2, i3);
                Function1<String, Unit> saveDiyImageAction$app_googleRelease = this.$callback.getSaveDiyImageAction$app_googleRelease();
                if (saveDiyImageAction$app_googleRelease != null) {
                    saveDiyImageAction$app_googleRelease.invoke(newBitmaps2TemplateGif);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1(this.this$0, this.$bitmaps, this.$callback, null), this) == coroutine_suspended) {
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
