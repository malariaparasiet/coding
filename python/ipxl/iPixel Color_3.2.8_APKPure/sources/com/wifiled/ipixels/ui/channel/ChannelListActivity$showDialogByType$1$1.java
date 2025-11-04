package com.wifiled.ipixels.ui.channel;

import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.send.SendResultCallback;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChannelListActivity.kt */
@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/wifiled/ipixels/ui/channel/ChannelListActivity$showDialogByType$1$1", "Lcom/wifiled/ipixels/core/send/SendResultCallback;", "onResult", "", "result", "", "onError", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChannelListActivity$showDialogByType$1$1 implements SendResultCallback {
    final /* synthetic */ ChannelListActivity this$0;

    ChannelListActivity$showDialogByType$1$1(ChannelListActivity channelListActivity) {
        this.this$0 = channelListActivity;
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onResult(byte[] result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.length == 5 && result[4] == 1 && !this.this$0.isDestroyed()) {
            final ChannelListActivity channelListActivity = this.this$0;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$showDialogByType$1$1$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit onResult$lambda$0;
                    onResult$lambda$0 = ChannelListActivity$showDialogByType$1$1.onResult$lambda$0(ChannelListActivity.this);
                    return onResult$lambda$0;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$0(ChannelListActivity channelListActivity) {
        channelListActivity.toast(channelListActivity.getString(R.string.success));
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onError(final int result) {
        final ChannelListActivity channelListActivity = this.this$0;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$showDialogByType$1$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onError$lambda$1;
                onError$lambda$1 = ChannelListActivity$showDialogByType$1$1.onError$lambda$1(ChannelListActivity.this, result);
                return onError$lambda$1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onError$lambda$1(ChannelListActivity channelListActivity, int i) {
        ToastUtil.show(channelListActivity.getString(R.string.msg_send_fail) + "(" + i + ")");
        return Unit.INSTANCE;
    }
}
