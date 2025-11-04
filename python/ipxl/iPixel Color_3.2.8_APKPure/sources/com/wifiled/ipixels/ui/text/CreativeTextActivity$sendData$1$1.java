package com.wifiled.ipixels.ui.text;

import android.app.Activity;
import com.blankj.utilcode.util.ActivityUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.send.SendResultCallback;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CreativeTextActivity.kt */
@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/wifiled/ipixels/ui/text/CreativeTextActivity$sendData$1$1", "Lcom/wifiled/ipixels/core/send/SendResultCallback;", "onResult", "", "result", "", "onError", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CreativeTextActivity$sendData$1$1 implements SendResultCallback {
    final /* synthetic */ byte[] $it;
    final /* synthetic */ CreativeTextActivity this$0;

    CreativeTextActivity$sendData$1$1(CreativeTextActivity creativeTextActivity, byte[] bArr) {
        this.this$0 = creativeTextActivity;
        this.$it = bArr;
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onResult(byte[] result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (Arrays.equals(result, new byte[]{5, 0, 8, ByteCompanionObject.MIN_VALUE, 1})) {
            CreativeTextActivity.sendGif$default(this.this$0, this.$it, false, 2, null);
        }
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onError(final int result) {
        if (ActivityUtils.isActivityAlive((Activity) this.this$0)) {
            final CreativeTextActivity creativeTextActivity = this.this$0;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$sendData$1$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit onError$lambda$0;
                    onError$lambda$0 = CreativeTextActivity$sendData$1$1.onError$lambda$0(CreativeTextActivity.this, result);
                    return onError$lambda$0;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onError$lambda$0(CreativeTextActivity creativeTextActivity, int i) {
        ToastUtil.show(creativeTextActivity.getString(R.string.msg_send_fail) + "(" + i + ")");
        return Unit.INSTANCE;
    }
}
