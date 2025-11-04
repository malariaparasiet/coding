package com.wifiled.ipixels.ui.subzone;

import android.app.Activity;
import com.blankj.utilcode.util.ActivityUtils;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.SendDataCallback;
import com.wifiled.ipixels.core.send.SendResultCallback;
import com.wifiled.ipixels.ui.channel.ChannelIndex;
import com.wifiled.ipixels.ui.channel.ChannelListItem;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SubzoneListActivity.kt */
@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/wifiled/ipixels/ui/subzone/SubzoneListActivity$sendData$1$2", "Lcom/wifiled/ipixels/core/send/SendResultCallback;", "onResult", "", "result", "", "onError", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SubzoneListActivity$sendData$1$2 implements SendResultCallback {
    final /* synthetic */ byte[] $imageData;
    final /* synthetic */ SendDataCallback $it;
    final /* synthetic */ SubzoneData $subzoneData;
    final /* synthetic */ SubzoneListActivity this$0;

    SubzoneListActivity$sendData$1$2(SubzoneData subzoneData, SendDataCallback sendDataCallback, byte[] bArr, SubzoneListActivity subzoneListActivity) {
        this.$subzoneData = subzoneData;
        this.$it = sendDataCallback;
        this.$imageData = bArr;
        this.this$0 = subzoneListActivity;
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onResult(byte[] result) {
        String str;
        Intrinsics.checkNotNullParameter(result, "result");
        if (Arrays.equals(result, new byte[]{5, 0, 8, ByteCompanionObject.MIN_VALUE, 1})) {
            SendCore.sendTemplateData$default(true, this.$subzoneData, this.$it, (byte) 0, 8, null);
            if (this.$imageData != null) {
                ChannelIndex.INSTANCE.mapSaveChannel().put(Integer.valueOf(ChannelIndex.INSTANCE.index()), new ChannelListItem.SubzoneView(false, String.valueOf(ChannelIndex.INSTANCE.index()), false, this.$subzoneData, this.$imageData));
                SubzoneListActivity subzoneListActivity = this.this$0;
                switch (AppConfig.INSTANCE.getLedType()) {
                    case 9:
                        str = "channel_data_24_48";
                        break;
                    case 10:
                        str = "channel_data_32_64";
                        break;
                    case 11:
                        str = "channel_data_32_96";
                        break;
                    case 12:
                        str = "channel_data_128_2";
                        break;
                    case 13:
                    default:
                        str = "channel_data";
                        break;
                    case 14:
                        str = "channel_data_32_160";
                        break;
                    case 15:
                        str = "channel_data_32_192";
                        break;
                    case 16:
                        str = "channel_data_32_256";
                        break;
                    case 17:
                        str = "channel_data_32_320";
                        break;
                    case 18:
                        str = "channel_data_32_384";
                        break;
                    case 19:
                        str = "channel_data_32_448";
                        break;
                }
                SPUtils.put(subzoneListActivity, str, ChannelIndex.INSTANCE.mapSaveChannel());
            }
        }
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onError(final int result) {
        if (ActivityUtils.isActivityAlive((Activity) this.this$0)) {
            final SubzoneListActivity subzoneListActivity = this.this$0;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.subzone.SubzoneListActivity$sendData$1$2$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit onError$lambda$0;
                    onError$lambda$0 = SubzoneListActivity$sendData$1$2.onError$lambda$0(SubzoneListActivity.this, result);
                    return onError$lambda$0;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onError$lambda$0(SubzoneListActivity subzoneListActivity, int i) {
        ToastUtil.show(subzoneListActivity.getString(R.string.msg_send_fail) + "(" + i + ")");
        return Unit.INSTANCE;
    }
}
