package com.wifiled.ipixels.ui.diy;

import android.graphics.Bitmap;
import androidx.core.view.PointerIconCompat;
import com.squareup.gifencoder.Image;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.core.GifCore;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.db.GifDao;
import com.wifiled.ipixels.model.Gif;
import com.wifiled.ipixels.utils.BitmapUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: DiyAnimActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.diy.DiyAnimActivity$saveGif$1", f = "DiyAnimActivity.kt", i = {}, l = {PointerIconCompat.TYPE_HAND}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class DiyAnimActivity$saveGif$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<Bitmap> $bitmaps;
    final /* synthetic */ SendCore.CallbackBuilder $callback;
    int label;
    final /* synthetic */ DiyAnimActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DiyAnimActivity$saveGif$1(SendCore.CallbackBuilder callbackBuilder, DiyAnimActivity diyAnimActivity, List<Bitmap> list, Continuation<? super DiyAnimActivity$saveGif$1> continuation) {
        super(2, continuation);
        this.$callback = callbackBuilder;
        this.this$0 = diyAnimActivity;
        this.$bitmaps = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DiyAnimActivity$saveGif$1(this.$callback, this.this$0, this.$bitmaps, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DiyAnimActivity$saveGif$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function0<Unit> startAction$app_googleRelease = this.$callback.getStartAction$app_googleRelease();
            if (startAction$app_googleRelease != null) {
                startAction$app_googleRelease.invoke();
            }
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1(this.this$0, this.$bitmaps, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Function0<Unit> completeAction$app_googleRelease = this.$callback.getCompleteAction$app_googleRelease();
        if (completeAction$app_googleRelease != null) {
            completeAction$app_googleRelease.invoke();
        }
        return Unit.INSTANCE;
    }

    /* compiled from: DiyAnimActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.wifiled.ipixels.ui.diy.DiyAnimActivity$saveGif$1$1", f = "DiyAnimActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.wifiled.ipixels.ui.diy.DiyAnimActivity$saveGif$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Long>, Object> {
        final /* synthetic */ List<Bitmap> $bitmaps;
        int label;
        final /* synthetic */ DiyAnimActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(DiyAnimActivity diyAnimActivity, List<Bitmap> list, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = diyAnimActivity;
            this.$bitmaps = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$bitmaps, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Long> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ArrayList arrayList;
            ArrayList arrayList2;
            int i;
            GifDao gifDao;
            ArrayList arrayList3;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                arrayList = this.this$0.diyImage;
                arrayList.clear();
                List<Bitmap> list = this.$bitmaps;
                DiyAnimActivity diyAnimActivity = this.this$0;
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    Image fromRgb = Image.fromRgb(BitmapUtils.INSTANCE.getRGBArray((Bitmap) it.next()), AppConfig.INSTANCE.getLedSize().get(0).intValue());
                    arrayList3 = diyAnimActivity.diyImage;
                    arrayList3.add(fromRgb);
                }
                GifCore gifCore = GifCore.INSTANCE;
                arrayList2 = this.this$0.diyImage;
                i = this.this$0.duration;
                Gif gif = new Gif(0, gifCore.newBitmaps2gif2(arrayList2, 3, i), "bgrUrl", AppConfig.INSTANCE.getLedType(), 0, 16, null);
                gifDao = this.this$0.getGifDao();
                return Boxing.boxLong(gifDao.add(gif));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
