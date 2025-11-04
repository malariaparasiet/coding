package com.wifiled.ipixels.ui.text;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Size;
import androidx.fragment.app.FragmentActivity;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.databinding.ActivityCreativeTextBinding;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: CreativeTextActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.text.CreativeTextActivity$createGif$3", f = "CreativeTextActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class CreativeTextActivity$createGif$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $isSave;
    final /* synthetic */ int $position;
    final /* synthetic */ String $text;
    int label;
    final /* synthetic */ CreativeTextActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CreativeTextActivity$createGif$3(int i, CreativeTextActivity creativeTextActivity, String str, boolean z, Continuation<? super CreativeTextActivity$createGif$3> continuation) {
        super(2, continuation);
        this.$position = i;
        this.this$0 = creativeTextActivity;
        this.$text = str;
        this.$isSave = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CreativeTextActivity$createGif$3(this.$position, this.this$0, this.$text, this.$isSave, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CreativeTextActivity$createGif$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AnimationType animationType;
        int i;
        int i2;
        String str;
        int i3;
        int i4;
        int i5;
        int i6;
        float f;
        int i7;
        int i8;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        switch (this.$position) {
            case 0:
                animationType = AnimationType.BOUNCE;
                break;
            case 1:
                animationType = AnimationType.PRINTER;
                break;
            case 2:
                animationType = AnimationType.THREE_D_ROTATE;
                break;
            case 3:
                animationType = AnimationType.WAVE_IN;
                break;
            case 4:
                animationType = AnimationType.ROTATE_SCALE;
                break;
            case 5:
                animationType = AnimationType.CLOCK;
                break;
            case 6:
                animationType = AnimationType.ROTATE_ENTER;
                break;
            case 7:
                animationType = AnimationType.PARTICLE;
                break;
            case 8:
                animationType = AnimationType.MIDDLE_SPLIT;
                break;
            case 9:
                animationType = AnimationType.EXPAND_SHRINK;
                break;
            case 10:
                animationType = AnimationType.THREE_D_Y_ROTATE;
                break;
            case 11:
                animationType = AnimationType.ROTATE_ENTER_LEFT;
                break;
            default:
                animationType = AnimationType.BOUNCE;
                break;
        }
        AnimationType animationType2 = animationType;
        i = this.this$0.mSelectBg;
        String str2 = "";
        if (i == 0) {
            str = "";
        } else {
            Integer num = AppConfig.INSTANCE.getLedSize().get(1);
            Integer num2 = AppConfig.INSTANCE.getLedSize().get(0);
            i2 = this.this$0.mSelectBg;
            str = "gif/text_ani_bg_" + num + "_" + num2 + "_" + i2 + ".gif";
        }
        LogUtils.vTag("ruis", "bgSrc src---" + str);
        i3 = this.this$0.mSelectBorder;
        if (i3 != 0) {
            Integer num3 = AppConfig.INSTANCE.getLedSize().get(0);
            Integer num4 = AppConfig.INSTANCE.getLedSize().get(1);
            i8 = this.this$0.mSelectBorder;
            str2 = "border/ani_border_" + num3 + "_" + num4 + "_" + i8 + ".png";
        }
        String str3 = str2;
        LogUtils.vTag("ruis", "border src---" + str3);
        try {
            CreativeTextUtil creativeTextUtil = new CreativeTextUtil(this.this$0);
            String str4 = this.$text;
            i4 = this.this$0.mTextColor;
            i5 = this.this$0.mGradientColor;
            TextAlignment textAlignment = TextAlignment.CENTER;
            i6 = this.this$0.mSelectFontSize;
            float f2 = i6;
            Size size = new Size(AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue());
            f = this.this$0.mAnimationSpeed;
            i7 = this.this$0.mTextDuration;
            final CreativeTextActivity creativeTextActivity = this.this$0;
            final boolean z = this.$isSave;
            creativeTextUtil.createGIFWithText(str4, animationType2, str, str3, i4, i5, textAlignment, f2, size, f, i7, new Function1() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$createGif$3$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    Unit invokeSuspend$lambda$1;
                    invokeSuspend$lambda$1 = CreativeTextActivity$createGif$3.invokeSuspend$lambda$1(CreativeTextActivity.this, z, (String) obj2);
                    return invokeSuspend$lambda$1;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            UtilsExtensionKt.showLoadingDialog$default((Activity) this.this$0, false, (String) null, false, 6, (Object) null);
            e.printStackTrace();
            final CreativeTextActivity creativeTextActivity2 = this.this$0;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$createGif$3$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit invokeSuspend$lambda$2;
                    invokeSuspend$lambda$2 = CreativeTextActivity$createGif$3.invokeSuspend$lambda$2(CreativeTextActivity.this);
                    return invokeSuspend$lambda$2;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(final CreativeTextActivity creativeTextActivity, final boolean z, final String str) {
        creativeTextActivity.mGifPath = str;
        creativeTextActivity.runOnUiThread(new Runnable() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$createGif$3$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CreativeTextActivity$createGif$3.invokeSuspend$lambda$1$lambda$0(CreativeTextActivity.this, str, z);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1$lambda$0(CreativeTextActivity creativeTextActivity, String str, boolean z) {
        ActivityCreativeTextBinding binding;
        ActivityCreativeTextBinding binding2;
        CreativeTextActivity creativeTextActivity2 = creativeTextActivity;
        RequestManager with = Glide.with((FragmentActivity) creativeTextActivity2);
        binding = creativeTextActivity.getBinding();
        with.clear(binding.gifImg);
        RequestBuilder<Drawable> load = Glide.with((FragmentActivity) creativeTextActivity2).load(str);
        binding2 = creativeTextActivity.getBinding();
        load.into(binding2.gifImg);
        UtilsExtensionKt.showLoadingDialog$default((Activity) creativeTextActivity, false, (String) null, false, 6, (Object) null);
        if (z) {
            creativeTextActivity.sendData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$2(CreativeTextActivity creativeTextActivity) {
        ToastUtil.show(creativeTextActivity.getString(R.string.msg_data_error));
        return Unit.INSTANCE;
    }
}
