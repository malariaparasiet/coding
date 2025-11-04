package com.wifiled.ipixels.ui.channel;

import android.app.Activity;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendDataCallback;
import com.wifiled.ipixels.ui.dialog.CustomDialog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: ChannelListActivity.kt */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"com/wifiled/ipixels/ui/channel/ChannelListActivity$sendData2$4", "Lcom/wifiled/ipixels/core/SendDataCallback;", "onStart", "", "onProgress", "progress", "", "onCompleted", "onError", "error", "onResult", "result", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChannelListActivity$sendData2$4 implements SendDataCallback {
    final /* synthetic */ ChannelListActivity this$0;

    @Override // com.wifiled.ipixels.core.SendDataCallback
    public void onProgress(int progress) {
    }

    ChannelListActivity$sendData2$4(ChannelListActivity channelListActivity) {
        this.this$0 = channelListActivity;
    }

    @Override // com.wifiled.ipixels.core.SendDataCallback
    public void onStart() {
        LogUtils.vTag("ruis", "sendTemplateData --- onStart");
    }

    @Override // com.wifiled.ipixels.core.SendDataCallback
    public void onCompleted() {
        int i;
        int i2;
        boolean z;
        i = this.this$0.iSelTotalCount2;
        this.this$0.iSelTotalCount2 = i - 1;
        ChannelListActivity channelListActivity = this.this$0;
        i2 = channelListActivity.iSelTotalCount2;
        channelListActivity.isCAllSendOver2 = i2 == 0;
        z = this.this$0.isCAllSendOver;
        if (z) {
            AppConfig.INSTANCE.setCancel(false);
            final ChannelListActivity channelListActivity2 = this.this$0;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$sendData2$4$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit onCompleted$lambda$0;
                    onCompleted$lambda$0 = ChannelListActivity$sendData2$4.onCompleted$lambda$0(ChannelListActivity.this);
                    return onCompleted$lambda$0;
                }
            });
        } else {
            Thread.sleep(200L);
            this.this$0.sendData2();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCompleted$lambda$0(ChannelListActivity channelListActivity) {
        CustomDialog.Builder builder;
        CustomDialog.Builder builder2;
        builder = channelListActivity.builder;
        builder.refReshMessage(channelListActivity.getString(R.string.msg_send_suc));
        builder2 = channelListActivity.builder;
        builder2.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.ipixels.core.SendDataCallback
    public void onError(final int error) {
        final ChannelListActivity channelListActivity = this.this$0;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$sendData2$4$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onError$lambda$1;
                onError$lambda$1 = ChannelListActivity$sendData2$4.onError$lambda$1(ChannelListActivity.this, error);
                return onError$lambda$1;
            }
        });
        final ChannelListActivity channelListActivity2 = this.this$0;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$sendData2$4$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onError$lambda$2;
                onError$lambda$2 = ChannelListActivity$sendData2$4.onError$lambda$2(ChannelListActivity.this);
                return onError$lambda$2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onError$lambda$1(ChannelListActivity channelListActivity, int i) {
        ToastUtil.show(channelListActivity.getString(R.string.msg_send_fail) + "(" + i + ")");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onError$lambda$2(ChannelListActivity channelListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) channelListActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v4, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.wifiled.ipixels.core.SendDataCallback
    public void onResult(byte[] result) {
        byte b;
        Intrinsics.checkNotNullParameter(result, "result");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? string = this.this$0.getString(R.string.msg_send_fail);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        objectRef.element = string;
        if (StringsKt.contains$default((CharSequence) new String(result, Charsets.UTF_8), (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) new String(result, Charsets.UTF_8), (CharSequence) "Socket connect fail", false, 2, (Object) null)) {
            final ChannelListActivity channelListActivity = this.this$0;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$sendData2$4$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit onResult$lambda$3;
                    onResult$lambda$3 = ChannelListActivity$sendData2$4.onResult$lambda$3(ChannelListActivity.this, objectRef);
                    return onResult$lambda$3;
                }
            });
            return;
        }
        if (result.length <= 4 || result[0] != 5 || (b = result[4]) == 1 || b == 3 || b != 2) {
            return;
        }
        ?? string2 = this.this$0.getString(R.string.channel_tip_low_space);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        objectRef.element = string2;
        final ChannelListActivity channelListActivity2 = this.this$0;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$sendData2$4$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onResult$lambda$4;
                onResult$lambda$4 = ChannelListActivity$sendData2$4.onResult$lambda$4(ChannelListActivity.this, objectRef);
                return onResult$lambda$4;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit onResult$lambda$3(ChannelListActivity channelListActivity, Ref.ObjectRef objectRef) {
        CustomDialog.Builder builder;
        CustomDialog.Builder builder2;
        builder = channelListActivity.builder;
        builder.refReshMessage((String) objectRef.element);
        builder2 = channelListActivity.builder;
        builder2.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit onResult$lambda$4(ChannelListActivity channelListActivity, Ref.ObjectRef objectRef) {
        CustomDialog.Builder builder;
        CustomDialog.Builder builder2;
        builder = channelListActivity.builder;
        builder.refReshMessage((String) objectRef.element);
        builder2 = channelListActivity.builder;
        builder2.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }
}
