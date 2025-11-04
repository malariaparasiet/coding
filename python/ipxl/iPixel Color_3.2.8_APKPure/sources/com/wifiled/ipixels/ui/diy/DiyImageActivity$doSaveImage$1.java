package com.wifiled.ipixels.ui.diy;

import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.callback.CallBack;
import com.wifiled.ipixels.db.ImageDao;
import com.wifiled.ipixels.model.Image;
import com.wifiled.ipixels.utils.BGRUtils;
import com.wifiled.ipixels.utils.FileUtil;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: DiyImageActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.diy.DiyImageActivity$doSaveImage$1", f = "DiyImageActivity.kt", i = {}, l = {319}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class DiyImageActivity$doSaveImage$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte[] $arrByte;
    final /* synthetic */ CallBack $callback;
    int label;
    final /* synthetic */ DiyImageActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DiyImageActivity$doSaveImage$1(DiyImageActivity diyImageActivity, byte[] bArr, CallBack callBack, Continuation<? super DiyImageActivity$doSaveImage$1> continuation) {
        super(2, continuation);
        this.this$0 = diyImageActivity;
        this.$arrByte = bArr;
        this.$callback = callBack;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DiyImageActivity$doSaveImage$1(this.this$0, this.$arrByte, this.$callback, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DiyImageActivity$doSaveImage$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: DiyImageActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.wifiled.ipixels.ui.diy.DiyImageActivity$doSaveImage$1$1", f = "DiyImageActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.wifiled.ipixels.ui.diy.DiyImageActivity$doSaveImage$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ byte[] $arrByte;
        final /* synthetic */ CallBack $callback;
        int label;
        final /* synthetic */ DiyImageActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(DiyImageActivity diyImageActivity, byte[] bArr, CallBack callBack, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = diyImageActivity;
            this.$arrByte = bArr;
            this.$callback = callBack;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$arrByte, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            int i;
            int i2;
            int i3;
            ImageDao imageDao;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            String path = FileUtil.getPath(1, ".jpg");
            i = this.this$0.mIsTemplate;
            if (i == 0) {
                FileUtil.bitmap2File(new File(path), BGRUtils.RGB2bitmap(this.$arrByte));
                Intrinsics.checkNotNull(path);
                Image image = new Image(0, path, "", AppConfig.INSTANCE.getLedType(), 100);
                imageDao = this.this$0.getImageDao();
                Boxing.boxLong(imageDao.add(image));
            } else {
                File file = new File(path);
                byte[] bArr = this.$arrByte;
                i2 = this.this$0.mTemplateWidth;
                i3 = this.this$0.mTemplateHeight;
                FileUtil.bitmap2File(file, BGRUtils.RGB2bitmap(bArr, i2, i3));
            }
            Function1<String, Unit> saveDiyImageAction$app_googleRelease = this.$callback.getSaveDiyImageAction$app_googleRelease();
            if (saveDiyImageAction$app_googleRelease != null) {
                Intrinsics.checkNotNull(path);
                saveDiyImageAction$app_googleRelease.invoke(path);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1(this.this$0, this.$arrByte, this.$callback, null), this) == coroutine_suspended) {
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
