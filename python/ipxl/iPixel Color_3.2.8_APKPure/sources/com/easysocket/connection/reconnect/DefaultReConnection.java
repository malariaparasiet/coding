package com.easysocket.connection.reconnect;

import com.easysocket.entity.SocketAddress;
import com.easysocket.utils.LogUtil;
import com.wifiled.ipixels.view.video_clip.VideoTrimmerUtil;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class DefaultReConnection extends AbsReconnection {
    private static final int MAX_CONNECTION_FAILED_TIMES = 10;
    private ScheduledExecutorService reConnExecutor;
    private int connectionFailedTimes = 0;
    private long reconnectTimeDelay = VideoTrimmerUtil.MAX_SHOOT_DURATION;
    private final Runnable RcConnTask = new Runnable() { // from class: com.easysocket.connection.reconnect.DefaultReConnection.1
        @Override // java.lang.Runnable
        public void run() {
            LogUtil.d("执行重连");
            if (DefaultReConnection.this.isDetach) {
                DefaultReConnection.this.shutDown();
                return;
            }
            if (!DefaultReConnection.this.connectionManager.isConnectViable()) {
                DefaultReConnection.this.shutDown();
                return;
            }
            if (DefaultReConnection.this.reconnectTimeDelay < DefaultReConnection.this.connectionManager.getOptions().getConnectTimeout()) {
                DefaultReConnection.this.reconnectTimeDelay = r0.connectionManager.getOptions().getConnectTimeout();
            }
            DefaultReConnection.this.connectionManager.connect();
        }
    };

    private void reconnect() {
        ScheduledExecutorService scheduledExecutorService = this.reConnExecutor;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
            this.reConnExecutor = newSingleThreadScheduledExecutor;
            newSingleThreadScheduledExecutor.scheduleWithFixedDelay(this.RcConnTask, 0L, this.reconnectTimeDelay, TimeUnit.MILLISECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shutDown() {
        ScheduledExecutorService scheduledExecutorService = this.reConnExecutor;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            return;
        }
        this.reConnExecutor.shutdownNow();
        this.reConnExecutor = null;
    }

    public boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass();
    }

    @Override // com.easysocket.interfaces.conn.ISocketActionListener
    public void onSocketConnSuccess(SocketAddress socketAddress) {
        shutDown();
    }

    @Override // com.easysocket.interfaces.conn.ISocketActionListener
    public void onSocketConnFail(SocketAddress socketAddress, Boolean bool) {
        if (!bool.booleanValue()) {
            shutDown();
            return;
        }
        int i = this.connectionFailedTimes + 1;
        this.connectionFailedTimes = i;
        if (i > 10 && socketAddress.getBackupAddress() != null) {
            this.connectionFailedTimes = 0;
            SocketAddress backupAddress = socketAddress.getBackupAddress();
            backupAddress.setBackupAddress(new SocketAddress(socketAddress.getIp(), socketAddress.getPort()));
            if (this.connectionManager.isConnectViable()) {
                this.connectionManager.switchHost(backupAddress);
                reconnect();
                return;
            }
            return;
        }
        reconnect();
    }

    @Override // com.easysocket.interfaces.conn.ISocketActionListener
    public void onSocketDisconnect(SocketAddress socketAddress, Boolean bool) {
        if (!bool.booleanValue()) {
            shutDown();
        } else {
            reconnect();
        }
    }
}
