package com.wifiled.ipixels.ui.diy;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.GifCore;
import com.wifiled.ipixels.vo.DiyAnimVO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: TemplateDiyAnimActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.diy.TemplateDiyAnimActivity$pickMultipleMedia$1$2$1", f = "TemplateDiyAnimActivity.kt", i = {}, l = {682}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class TemplateDiyAnimActivity$pickMultipleMedia$1$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef<String> $filePath;
    final /* synthetic */ Ref.IntRef $iIndex;
    int label;
    final /* synthetic */ TemplateDiyAnimActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TemplateDiyAnimActivity$pickMultipleMedia$1$2$1(TemplateDiyAnimActivity templateDiyAnimActivity, Ref.ObjectRef<String> objectRef, Ref.IntRef intRef, Continuation<? super TemplateDiyAnimActivity$pickMultipleMedia$1$2$1> continuation) {
        super(2, continuation);
        this.this$0 = templateDiyAnimActivity;
        this.$filePath = objectRef;
        this.$iIndex = intRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TemplateDiyAnimActivity$pickMultipleMedia$1$2$1(this.this$0, this.$filePath, this.$iIndex, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TemplateDiyAnimActivity$pickMultipleMedia$1$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ImageView imageView;
        RecyclerView recyclerView;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        RecyclerView recyclerView2 = null;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            TemplateDiyAnimActivity templateDiyAnimActivity = this.this$0;
            UtilsExtensionKt.showLoadingDialog$default((Activity) templateDiyAnimActivity, false, templateDiyAnimActivity.getString(R.string.msg_loading) + "...", false, 5, (Object) null);
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1(this.$filePath, null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Ref.ObjectRef<String> objectRef = this.$filePath;
        TemplateDiyAnimActivity templateDiyAnimActivity2 = this.this$0;
        Ref.IntRef intRef = this.$iIndex;
        List list = (List) obj;
        String element = objectRef.element;
        Intrinsics.checkNotNullExpressionValue(element, "element");
        for (Bitmap bitmap : GifCore.decodeGif(element)) {
            int i2 = intRef.element;
            intRef.element = i2 + 1;
            templateDiyAnimActivity2.addFrame(bitmap, i2);
        }
        if (!list.isEmpty()) {
            imageView = templateDiyAnimActivity2.iv_git_preview;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_git_preview");
                imageView = null;
            }
            imageView.setImageBitmap(((DiyAnimVO) list.get(0)).getBitmap());
            recyclerView = templateDiyAnimActivity2.rl_diy_anim_add_item;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_diy_anim_add_item");
            } else {
                recyclerView2 = recyclerView;
            }
            recyclerView2.scrollToPosition(list.size() - 1);
        }
        UtilsExtensionKt.showLoadingDialog$default((Activity) this.this$0, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* compiled from: TemplateDiyAnimActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/wifiled/ipixels/vo/DiyAnimVO;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.wifiled.ipixels.ui.diy.TemplateDiyAnimActivity$pickMultipleMedia$1$2$1$1", f = "TemplateDiyAnimActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.wifiled.ipixels.ui.diy.TemplateDiyAnimActivity$pickMultipleMedia$1$2$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends DiyAnimVO>>, Object> {
        final /* synthetic */ Ref.ObjectRef<String> $filePath;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Ref.ObjectRef<String> objectRef, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$filePath = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$filePath, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends DiyAnimVO>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<DiyAnimVO>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<DiyAnimVO>> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            String element = this.$filePath.element;
            Intrinsics.checkNotNullExpressionValue(element, "element");
            ArrayList<Bitmap> decodeGif = GifCore.decodeGif(element);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(decodeGif, 10));
            Iterator<T> it = decodeGif.iterator();
            while (it.hasNext()) {
                arrayList.add(new DiyAnimVO((Bitmap) it.next()));
            }
            return arrayList;
        }
    }
}
