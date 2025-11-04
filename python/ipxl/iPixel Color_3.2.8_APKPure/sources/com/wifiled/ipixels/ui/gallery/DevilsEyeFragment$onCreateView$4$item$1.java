package com.wifiled.ipixels.ui.gallery;

import android.content.Intent;
import android.graphics.Bitmap;
import androidx.fragment.app.FragmentActivity;
import com.blankj.utilcode.util.FileIOUtils;
import com.bumptech.glide.Glide;
import com.wifiled.baselib.data.Record;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.core.GifCore;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.greenrobot.eventbus.EventBus;

/* compiled from: DevilsEyeFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$onCreateView$4$item$1", f = "DevilsEyeFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class DevilsEyeFragment$onCreateView$4$item$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Record $item;
    final /* synthetic */ Record $righItem;
    int label;
    final /* synthetic */ DevilsEyeFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DevilsEyeFragment$onCreateView$4$item$1(DevilsEyeFragment devilsEyeFragment, Record record, Record record2, Continuation<? super DevilsEyeFragment$onCreateView$4$item$1> continuation) {
        super(2, continuation);
        this.this$0 = devilsEyeFragment;
        this.$item = record;
        this.$righItem = record2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DevilsEyeFragment$onCreateView$4$item$1(this.this$0, this.$item, this.$righItem, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DevilsEyeFragment$onCreateView$4$item$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        boolean selectMode;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        try {
            File file = Glide.with(this.this$0).asFile().load(this.$item.getFile()).submit(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
            File file2 = Glide.with(this.this$0).asFile().load(this.$righItem.getFile()).submit(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
            this.this$0.setMLeftRGBData(FileIOUtils.readFile2BytesByChannel(file));
            this.this$0.setMRightRGBData2(FileIOUtils.readFile2BytesByChannel(file2));
            str = this.this$0.type;
            if (Intrinsics.areEqual(str, "动画")) {
                selectMode = this.this$0.getSelectMode();
                if (!selectMode) {
                    if (AppConfig.INSTANCE.getMcu() > 4 && this.this$0.getMLeftRGBData() != null && this.this$0.getMRightRGBData2() != null) {
                        DevilsEyeFragment devilsEyeFragment = this.this$0;
                        byte[] mLeftRGBData = devilsEyeFragment.getMLeftRGBData();
                        Intrinsics.checkNotNull(mLeftRGBData);
                        byte[] mRightRGBData2 = this.this$0.getMRightRGBData2();
                        Intrinsics.checkNotNull(mRightRGBData2);
                        devilsEyeFragment.sendGif(mLeftRGBData, mRightRGBData2);
                    }
                } else {
                    byte[] bgrData = this.this$0.getBgrData();
                    Intrinsics.checkNotNull(bgrData);
                    ArrayList<Bitmap> decodeGif = GifCore.decodeGif(bgrData);
                    Intent intent = new Intent();
                    ArrayList arrayList = new ArrayList();
                    Iterator<T> it = decodeGif.iterator();
                    while (it.hasNext()) {
                        arrayList.add((Bitmap) it.next());
                    }
                    EventBus.getDefault().postSticky(arrayList);
                    intent.putExtra("sendData", this.this$0.getBgrData());
                    intent.putExtra("sendType", 1);
                    FragmentActivity activity = this.this$0.getActivity();
                    if (activity != null) {
                        activity.setResult(-1, intent);
                    }
                    FragmentActivity activity2 = this.this$0.getActivity();
                    if (activity2 != null) {
                        activity2.finish();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Unit.INSTANCE;
    }
}
