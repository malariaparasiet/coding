package com.wifiled.ipixels.core;

import com.easysocket.EasySocket;
import com.easysocket.entity.SocketAddress;
import com.easysocket.interfaces.conn.SocketActionListener;
import com.easysocket.utils.LogUtil;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.send.SendResultCallback;
import com.wifiled.ipixels.ui.text.vo.SendResultMsg;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* compiled from: SocketManager.kt */
@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"com/wifiled/ipixels/core/SocketManager$socketActionListener$1", "Lcom/easysocket/interfaces/conn/SocketActionListener;", "onSocketConnSuccess", "", "socketAddress", "Lcom/easysocket/entity/SocketAddress;", "onSocketConnFail", "isNeedReconnect", "", "onSocketDisconnect", "onSocketResponse", "originReadData", "Lcom/easysocket/entity/OriginReadData;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SocketManager$socketActionListener$1 extends SocketActionListener {
    SocketManager$socketActionListener$1() {
    }

    @Override // com.easysocket.interfaces.conn.SocketActionListener, com.easysocket.interfaces.conn.ISocketActionListener
    public /* bridge */ /* synthetic */ void onSocketConnFail(SocketAddress socketAddress, Boolean bool) {
        onSocketConnFail(socketAddress, bool.booleanValue());
    }

    @Override // com.easysocket.interfaces.conn.SocketActionListener, com.easysocket.interfaces.conn.ISocketActionListener
    public /* bridge */ /* synthetic */ void onSocketDisconnect(SocketAddress socketAddress, Boolean bool) {
        onSocketDisconnect(socketAddress, bool.booleanValue());
    }

    @Override // com.easysocket.interfaces.conn.SocketActionListener, com.easysocket.interfaces.conn.ISocketActionListener
    public void onSocketConnSuccess(SocketAddress socketAddress) {
        Intrinsics.checkNotNullParameter(socketAddress, "socketAddress");
        super.onSocketConnSuccess(socketAddress);
        LogUtils.logi("SocketManager>>>[onSocketConnSuccess]: ip:" + socketAddress.getIp() + " port:" + socketAddress.getPort(), new Object[0]);
        if (AppConfig.INSTANCE.getConnectType() != 0) {
            AppConfig.INSTANCE.setConnectType(0);
            UtilsExtensionKt.asyncDelay(3000L, new Function0() { // from class: com.wifiled.ipixels.core.SocketManager$socketActionListener$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit onSocketConnSuccess$lambda$0;
                    onSocketConnSuccess$lambda$0 = SocketManager$socketActionListener$1.onSocketConnSuccess$lambda$0();
                    return onSocketConnSuccess$lambda$0;
                }
            });
            SocketManager.INSTANCE.startSendThread();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onSocketConnSuccess$lambda$0() {
        EventBus eventBus = EventBus.getDefault();
        byte[] bytes = "wifi connect".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        eventBus.post(new SendResultMsg(bytes));
        return Unit.INSTANCE;
    }

    public void onSocketConnFail(SocketAddress socketAddress, boolean isNeedReconnect) {
        SendResultCallback sendResultCallback;
        SendResultCallback sendResultCallback2;
        Intrinsics.checkNotNullParameter(socketAddress, "socketAddress");
        super.onSocketConnFail(socketAddress, Boolean.valueOf(isNeedReconnect));
        SocketManager.INSTANCE.stopSendThread();
        boolean isReady = BleManager.INSTANCE.get().getIsReady();
        if (isReady) {
            AppConfig.INSTANCE.setConnectType(1);
        } else {
            if (isReady) {
                throw new NoWhenBranchMatchedException();
            }
            AppConfig.INSTANCE.setConnectType(-1);
        }
        AppConfig.INSTANCE.setCurConnectWifi("");
        LogUtils.logi("SocketManager>>>[onSocketConnFail]: isNeedReconnect:" + isNeedReconnect + " " + EasySocket.getInstance().getSpecifyConnection(socketAddress) + "AppConfig.connectType：" + AppConfig.INSTANCE.getConnectType(), new Object[0]);
        String topActivity = AppConfig.INSTANCE.getTopActivity();
        Intrinsics.checkNotNullExpressionValue("GalleryActivity", "getSimpleName(...)");
        if (StringsKt.contains$default((CharSequence) topActivity, (CharSequence) "GalleryActivity", false, 2, (Object) null)) {
            sendResultCallback2 = SocketManager.sendResultCallback;
            if (sendResultCallback2 != null) {
                sendResultCallback2.onResult(new byte[0]);
                return;
            }
            return;
        }
        sendResultCallback = SocketManager.sendResultCallback;
        if (sendResultCallback != null) {
            byte[] bytes = "Socket connect fail".getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            sendResultCallback.onResult(bytes);
        }
        EventBus.getDefault().post(new SendResultMsg(new byte[]{ByteCompanionObject.MIN_VALUE}));
    }

    public void onSocketDisconnect(SocketAddress socketAddress, boolean isNeedReconnect) {
        ByteBuffer byteBuffer;
        SendResultCallback sendResultCallback;
        Intrinsics.checkNotNullParameter(socketAddress, "socketAddress");
        super.onSocketDisconnect(socketAddress, Boolean.valueOf(isNeedReconnect));
        SocketManager.INSTANCE.stopSendThread();
        boolean isReady = BleManager.INSTANCE.get().getIsReady();
        if (isReady) {
            AppConfig.INSTANCE.setConnectType(1);
        } else {
            if (isReady) {
                throw new NoWhenBranchMatchedException();
            }
            AppConfig.INSTANCE.setConnectType(-1);
        }
        LogUtil.d("socket断开连接，是否需要重连：" + isNeedReconnect);
        byteBuffer = SocketManager.recvBf;
        byteBuffer.clear();
        String topActivity = AppConfig.INSTANCE.getTopActivity();
        Intrinsics.checkNotNullExpressionValue("GalleryActivity", "getSimpleName(...)");
        if (StringsKt.contains$default((CharSequence) topActivity, (CharSequence) "GalleryActivity", false, 2, (Object) null)) {
            sendResultCallback = SocketManager.sendResultCallback;
            if (sendResultCallback != null) {
                sendResultCallback.onResult(new byte[0]);
                return;
            }
            return;
        }
        EventBus.getDefault().post(new SendResultMsg(new byte[]{ByteCompanionObject.MIN_VALUE}));
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x0151, code lost:
    
        r12 = com.wifiled.ipixels.core.SocketManager.mSendWifiDataThread;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00e2  */
    @Override // com.easysocket.interfaces.conn.SocketActionListener, com.easysocket.interfaces.conn.ISocketActionListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onSocketResponse(com.easysocket.entity.SocketAddress r11, com.easysocket.entity.OriginReadData r12) {
        /*
            Method dump skipped, instructions count: 563
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.core.SocketManager$socketActionListener$1.onSocketResponse(com.easysocket.entity.SocketAddress, com.easysocket.entity.OriginReadData):void");
    }
}
