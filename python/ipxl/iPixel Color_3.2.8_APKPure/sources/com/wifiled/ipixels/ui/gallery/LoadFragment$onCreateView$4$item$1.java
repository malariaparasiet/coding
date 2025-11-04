package com.wifiled.ipixels.ui.gallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.fragment.app.FragmentActivity;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.wifiled.baselib.data.Record;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.core.GifCore;
import com.wifiled.ipixels.utils.BGRUtils;
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

/* compiled from: LoadFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.gallery.LoadFragment$onCreateView$4$item$1", f = "LoadFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class LoadFragment$onCreateView$4$item$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Record $item;
    int label;
    final /* synthetic */ LoadFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LoadFragment$onCreateView$4$item$1(LoadFragment loadFragment, Record record, Continuation<? super LoadFragment$onCreateView$4$item$1> continuation) {
        super(2, continuation);
        this.this$0 = loadFragment;
        this.$item = record;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LoadFragment$onCreateView$4$item$1(this.this$0, this.$item, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LoadFragment$onCreateView$4$item$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        boolean selectMode;
        boolean selectMode2;
        boolean selectMode3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        try {
            this.this$0.setBgrData(FileIOUtils.readFile2BytesByChannel(Glide.with(this.this$0).asFile().load(this.$item.getFile()).submit(Integer.MIN_VALUE, Integer.MIN_VALUE).get()));
            str = this.this$0.type;
            if (Intrinsics.areEqual(str, "动画")) {
                selectMode3 = this.this$0.getSelectMode();
                if (!selectMode3) {
                    byte[] bgrData = this.this$0.getBgrData();
                    if (bgrData != null) {
                        LoadFragment loadFragment = this.this$0;
                        if (AppConfig.INSTANCE.getMcu() > 4) {
                            loadFragment.sendGif(bgrData);
                        }
                    }
                } else {
                    byte[] bgrData2 = this.this$0.getBgrData();
                    Intrinsics.checkNotNull(bgrData2);
                    ArrayList<Bitmap> decodeGif = GifCore.decodeGif(bgrData2);
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
            } else {
                byte[] bgrData3 = this.this$0.getBgrData();
                byte[] bgrData4 = this.this$0.getBgrData();
                Intrinsics.checkNotNull(bgrData4);
                byte[] bitmap2RGB = BGRUtils.bitmap2RGB(BitmapFactory.decodeByteArray(bgrData3, 0, bgrData4.length));
                byte[] bgrData5 = this.this$0.getBgrData();
                LoadFragment loadFragment2 = this.this$0;
                selectMode = loadFragment2.getSelectMode();
                if (!selectMode && AppConfig.INSTANCE.getLedType() != 16) {
                    bitmap2RGB = this.this$0.getBgrData();
                }
                loadFragment2.setBgrData(bitmap2RGB);
                selectMode2 = this.this$0.getSelectMode();
                if (selectMode2) {
                    Intent intent2 = new Intent();
                    intent2.putExtra("bgr", this.this$0.getBgrData());
                    if (AppConfig.INSTANCE.getLedType() == 16) {
                        intent2.putExtra("sendData", this.this$0.getBgrData());
                    } else {
                        intent2.putExtra("sendData", bgrData5);
                    }
                    intent2.putExtra("fileData", bgrData5);
                    intent2.putExtra("sendType", 2);
                    FragmentActivity activity3 = this.this$0.getActivity();
                    if (activity3 != null) {
                        activity3.setResult(-1, intent2);
                    }
                    FragmentActivity activity4 = this.this$0.getActivity();
                    if (activity4 != null) {
                        activity4.finish();
                    }
                } else {
                    byte[] bgrData6 = this.this$0.getBgrData();
                    if (bgrData6 != null) {
                        LoadFragment loadFragment3 = this.this$0;
                        LogUtils.vTag("ruis", "send image data ----");
                        if (AppConfig.INSTANCE.getMcu() > 4) {
                            loadFragment3.sendPng(bgrData6);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Unit.INSTANCE;
    }
}
