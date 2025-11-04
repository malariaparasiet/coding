package com.wifiled.ipixels.ui.gallery;

import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.send.SendResultCallback;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnimFragment.kt */
@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/wifiled/ipixels/ui/gallery/AnimFragment$dowaloadEyeToDevice$3", "Lcom/wifiled/ipixels/core/send/SendResultCallback;", "onResult", "", "result", "", "onError", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AnimFragment$dowaloadEyeToDevice$3 implements SendResultCallback {
    final /* synthetic */ byte[] $leftData;
    final /* synthetic */ byte[] $rightData;
    final /* synthetic */ AnimFragment this$0;

    AnimFragment$dowaloadEyeToDevice$3(byte[] bArr, AnimFragment animFragment, byte[] bArr2) {
        this.$rightData = bArr;
        this.this$0 = animFragment;
        this.$leftData = bArr2;
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onResult(byte[] result) {
        Function1 function1;
        Function1 function12;
        Intrinsics.checkNotNullParameter(result, "result");
        if (Arrays.equals(result, new byte[]{5, 0, 8, ByteCompanionObject.MIN_VALUE, 1})) {
            if (!AppConfig.INSTANCE.isExchange()) {
                SendCore sendCore = SendCore.INSTANCE;
                byte[] bArr = this.$rightData;
                function12 = this.this$0.sendRightEyeCallBack;
                SendCore.sendEyeData$default(sendCore, false, null, null, bArr, function12, (byte) 0, 39, null);
                return;
            }
            SendCore sendCore2 = SendCore.INSTANCE;
            byte[] bArr2 = this.$leftData;
            function1 = this.this$0.sendRightEyeCallBack;
            SendCore.sendEyeData$default(sendCore2, false, null, null, bArr2, function1, (byte) 0, 39, null);
        }
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onError(final int result) {
        if (this.this$0.isAdded()) {
            final AnimFragment animFragment = this.this$0;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$dowaloadEyeToDevice$3$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit onError$lambda$0;
                    onError$lambda$0 = AnimFragment$dowaloadEyeToDevice$3.onError$lambda$0(AnimFragment.this, result);
                    return onError$lambda$0;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onError$lambda$0(AnimFragment animFragment, int i) {
        ToastUtil.show(animFragment.getString(R.string.msg_send_fail) + "(" + i + ")");
        return Unit.INSTANCE;
    }
}
