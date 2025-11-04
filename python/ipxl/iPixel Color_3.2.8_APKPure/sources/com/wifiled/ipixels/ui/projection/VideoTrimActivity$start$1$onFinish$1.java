package com.wifiled.ipixels.ui.projection;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import com.squareup.gifencoder.Image;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.GifCore;
import com.wifiled.ipixels.db.GifDao;
import com.wifiled.ipixels.model.Gif;
import com.wifiled.ipixels.utils.BitmapUtils;
import java.util.ArrayList;
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

/* compiled from: VideoTrimActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.projection.VideoTrimActivity$start$1$onFinish$1", f = "VideoTrimActivity.kt", i = {0, 0, 0}, l = {117}, m = "invokeSuspend", n = {"diyImage", "gifPath", "gif"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes3.dex */
final class VideoTrimActivity$start$1$onFinish$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $path;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ VideoTrimActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VideoTrimActivity$start$1$onFinish$1(VideoTrimActivity videoTrimActivity, String str, Continuation<? super VideoTrimActivity$start$1$onFinish$1> continuation) {
        super(2, continuation);
        this.this$0 = videoTrimActivity;
        this.$path = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoTrimActivity$start$1$onFinish$1(this.this$0, this.$path, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoTrimActivity$start$1$onFinish$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        GifDao gifDao;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ArrayList arrayList = new ArrayList();
            for (Bitmap bitmap : this.this$0.getBitmaps()) {
                arrayList.add(Image.fromRgb(BitmapUtils.INSTANCE.getRGBArray(bitmap), bitmap.getWidth()));
            }
            String newBitmaps2gif2 = GifCore.INSTANCE.newBitmaps2gif2(arrayList, 3, 110);
            str = this.this$0.TAG;
            LogUtils.logi(str + ">>>[onFinish]: " + newBitmaps2gif2, new Object[0]);
            Gif gif = new Gif(0, newBitmaps2gif2, "", AppConfig.INSTANCE.getLedType(), 0, 16, null);
            gifDao = this.this$0.getGifDao();
            gifDao.add(gif);
            this.L$0 = SpillingKt.nullOutSpilledVariable(arrayList);
            this.L$1 = SpillingKt.nullOutSpilledVariable(newBitmaps2gif2);
            this.L$2 = SpillingKt.nullOutSpilledVariable(gif);
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(this.this$0, this.$path, newBitmaps2gif2, null), this) == coroutine_suspended) {
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

    /* compiled from: VideoTrimActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.wifiled.ipixels.ui.projection.VideoTrimActivity$start$1$onFinish$1$2", f = "VideoTrimActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.wifiled.ipixels.ui.projection.VideoTrimActivity$start$1$onFinish$1$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $gifPath;
        final /* synthetic */ String $path;
        int label;
        final /* synthetic */ VideoTrimActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(VideoTrimActivity videoTrimActivity, String str, String str2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = videoTrimActivity;
            this.$path = str;
            this.$gifPath = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, this.$path, this.$gifPath, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            UtilsExtensionKt.showLoadingDialog$default((Activity) this.this$0, false, (String) null, false, 6, (Object) null);
            Intent intent = new Intent();
            intent.putExtra("trim_path", this.$path);
            intent.putExtra("gif_path", this.$gifPath);
            this.this$0.setResult(-1, intent);
            this.this$0.finish();
            return Unit.INSTANCE;
        }
    }
}
