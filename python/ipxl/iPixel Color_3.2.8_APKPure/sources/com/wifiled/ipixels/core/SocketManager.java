package com.wifiled.ipixels.core;

import androidx.autofill.HintConstants;
import com.easysocket.EasySocket;
import com.easysocket.config.EasySocketOptions;
import com.easysocket.entity.SocketAddress;
import com.easysocket.interfaces.conn.ISocketActionListener;
import com.wifiled.ipixels.core.send.SendResultCallback;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: SocketManager.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\"#B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0011\u001a\u00020\u000fJ\u0006\u0010\u0012\u001a\u00020\rJ\u0010\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\rJ\u001c\u0010\u0015\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0007J\u0006\u0010\u001b\u001a\u00020\u000fJ\u0006\u0010\u001c\u001a\u00020\u000fJ\u001a\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0005H\u0016J\u001a\u0010!\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0005H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/wifiled/ipixels/core/SocketManager;", "Lcom/wifiled/ipixels/core/OnThreadStateListener;", "<init>", "()V", "TAG", "", "sendResultCallback", "Lcom/wifiled/ipixels/core/send/SendResultCallback;", "mSendWifiDataThread", "Lcom/wifiled/ipixels/core/SendWifiDataThread;", "recvBf", "Ljava/nio/ByteBuffer;", "isInitOver", "", "initSocket", "", "connect", "disconnect", "isConnectViable", "releaseSocket", "isReConnect", "sendData", "data", "", "callback", "socketActionListener", "Lcom/easysocket/interfaces/conn/ISocketActionListener;", "startSendThread", "stopSendThread", "onStart", "id", "", HintConstants.AUTOFILL_HINT_NAME, "onEnd", "SendCallback", "SendResult", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SocketManager implements OnThreadStateListener {
    public static final SocketManager INSTANCE = new SocketManager();
    public static final String TAG = "SocketManager";
    private static boolean isInitOver;
    private static SendWifiDataThread mSendWifiDataThread;
    private static ByteBuffer recvBf;
    private static SendResultCallback sendResultCallback;
    private static final ISocketActionListener socketActionListener;

    /* compiled from: SocketManager.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/core/SocketManager$SendCallback;", "", "onStart", "", "onProgress", "index", "", "onFinish", "onError", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface SendCallback {
        void onError();

        void onFinish();

        void onProgress(int index);

        void onStart();
    }

    @Override // com.wifiled.ipixels.core.OnThreadStateListener
    public void onEnd(long id, String name) {
    }

    @Override // com.wifiled.ipixels.core.OnThreadStateListener
    public void onStart(long id, String name) {
    }

    private SocketManager() {
    }

    static {
        ByteBuffer allocate = ByteBuffer.allocate(256);
        Intrinsics.checkNotNullExpressionValue(allocate, "allocate(...)");
        recvBf = allocate;
        socketActionListener = new SocketManager$socketActionListener$1();
    }

    public final void initSocket() {
        EasySocketOptions build = new EasySocketOptions.Builder().setSocketAddress(new SocketAddress("192.168.4.1", 80)).setMaxWriteBytes(12288).build();
        EasySocket.getInstance().setDebug(false);
        EasySocket.getInstance().options(build).createConnection().subscribeSocketAction(socketActionListener).connect();
    }

    public final void connect() {
        EasySocket.getInstance().connect();
    }

    public final void disconnect() {
        EasySocket.getInstance().disconnect(false);
    }

    public final boolean isConnectViable() {
        return EasySocket.getInstance().getConnection().isConnectViable();
    }

    public static /* synthetic */ void releaseSocket$default(SocketManager socketManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        socketManager.releaseSocket(z);
    }

    public final void releaseSocket(boolean isReConnect) {
        synchronized (Reflection.getOrCreateKotlinClass(SocketManager.class)) {
            try {
                if (isReConnect) {
                    EasySocket.getInstance().disconnect(false);
                    EasySocket.getInstance().connect();
                } else {
                    if (isReConnect) {
                        throw new NoWhenBranchMatchedException();
                    }
                    EasySocket.getInstance().destroyConnection();
                    EasySocketOptions build = new EasySocketOptions.Builder().setSocketAddress(new SocketAddress("192.168.4.1", 80)).setMaxWriteBytes(12288).build();
                    EasySocket.getInstance().setDebug(false);
                    EasySocket.getInstance().options(build).createConnection().subscribeSocketAction(socketActionListener);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static /* synthetic */ void sendData$default(SocketManager socketManager, byte[] bArr, SendResultCallback sendResultCallback2, int i, Object obj) {
        if ((i & 2) != 0) {
            sendResultCallback2 = null;
        }
        socketManager.sendData(bArr, sendResultCallback2);
    }

    public final void sendData(byte[] data, SendResultCallback callback) {
        if (data != null) {
            sendResultCallback = callback;
            SendWifiDataThread sendWifiDataThread = mSendWifiDataThread;
            if (sendWifiDataThread != null) {
                sendWifiDataThread.addSendTask(data, callback);
            }
        }
    }

    /* compiled from: SocketManager.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/wifiled/ipixels/core/SocketManager$SendResult;", "", "<init>", "()V", "COMMAND_INVALID", "", "COMMAND_VALID", "NOT_ENOUGH_SPACE", "SAVE_SUCCESS", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SendResult {
        public static final int COMMAND_INVALID = 0;
        public static final int COMMAND_VALID = 1;
        public static final SendResult INSTANCE = new SendResult();
        public static final int NOT_ENOUGH_SPACE = 2;
        public static final int SAVE_SUCCESS = 3;

        private SendResult() {
        }
    }

    public final void startSendThread() {
        SendWifiDataThread sendWifiDataThread;
        if (mSendWifiDataThread == null) {
            mSendWifiDataThread = new SendWifiDataThread(this);
        }
        SendWifiDataThread sendWifiDataThread2 = mSendWifiDataThread;
        if (sendWifiDataThread2 == null || sendWifiDataThread2.isAlive() || (sendWifiDataThread = mSendWifiDataThread) == null) {
            return;
        }
        sendWifiDataThread.start();
    }

    public final void stopSendThread() {
        SendWifiDataThread sendWifiDataThread = mSendWifiDataThread;
        if (sendWifiDataThread != null) {
            if (sendWifiDataThread != null) {
                sendWifiDataThread.interrupt();
            }
            mSendWifiDataThread = null;
        }
    }
}
