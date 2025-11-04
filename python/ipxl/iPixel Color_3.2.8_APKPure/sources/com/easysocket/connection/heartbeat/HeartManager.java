package com.easysocket.connection.heartbeat;

import com.easysocket.EasySocket;
import com.easysocket.config.EasySocketOptions;
import com.easysocket.entity.OriginReadData;
import com.easysocket.entity.SocketAddress;
import com.easysocket.entity.basemsg.ISender;
import com.easysocket.interfaces.config.IOptions;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.interfaces.conn.IHeartManager;
import com.easysocket.interfaces.conn.ISocketActionDispatch;
import com.easysocket.interfaces.conn.ISocketActionListener;
import com.google.gson.Gson;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class HeartManager implements IOptions, ISocketActionListener, IHeartManager {
    private byte[] clientHeart;
    private IConnectionManager connectionManager;
    private long freq;
    private ScheduledExecutorService heartExecutor;
    private HeartbeatListener heartbeatListener;
    private boolean isActivate;
    private EasySocketOptions socketOptions;
    private AtomicInteger loseTimes = new AtomicInteger(-1);
    private final Runnable beatTask = new Runnable() { // from class: com.easysocket.connection.heartbeat.HeartManager.1
        @Override // java.lang.Runnable
        public void run() {
            if (HeartManager.this.socketOptions.getMaxHeartbeatLoseTimes() == -1 || HeartManager.this.loseTimes.incrementAndGet() < HeartManager.this.socketOptions.getMaxHeartbeatLoseTimes()) {
                HeartManager.this.connectionManager.upBytes(HeartManager.this.clientHeart);
            } else {
                HeartManager.this.connectionManager.disconnect(Boolean.TRUE);
                HeartManager.this.resetLoseTimes();
            }
        }
    };

    public interface HeartbeatListener {
        boolean isServerHeartbeat(OriginReadData originReadData);
    }

    public HeartManager(IConnectionManager iConnectionManager, ISocketActionDispatch iSocketActionDispatch) {
        this.connectionManager = iConnectionManager;
        this.socketOptions = iConnectionManager.getOptions();
        iSocketActionDispatch.subscribe(this);
    }

    @Override // com.easysocket.interfaces.conn.IHeartManager
    public void startHeartbeat(ISender iSender, HeartbeatListener heartbeatListener) {
        startHeartbeat(new Gson().toJson(iSender).getBytes(Charset.forName(EasySocket.getInstance().getOptions().getCharsetName())), heartbeatListener);
    }

    @Override // com.easysocket.interfaces.conn.IHeartManager
    public void startHeartbeat(byte[] bArr, HeartbeatListener heartbeatListener) {
        this.clientHeart = bArr;
        this.heartbeatListener = heartbeatListener;
        this.isActivate = true;
        startHeartThread();
    }

    @Override // com.easysocket.interfaces.conn.IHeartManager
    public void startHeartbeat(String str, HeartbeatListener heartbeatListener) {
        startHeartbeat(str.getBytes(Charset.forName(EasySocket.getInstance().getOptions().getCharsetName())), heartbeatListener);
    }

    private void startHeartThread() {
        this.freq = this.socketOptions.getHeartbeatFreq();
        ScheduledExecutorService scheduledExecutorService = this.heartExecutor;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
            this.heartExecutor = newSingleThreadScheduledExecutor;
            newSingleThreadScheduledExecutor.scheduleWithFixedDelay(this.beatTask, 0L, this.freq, TimeUnit.MILLISECONDS);
        }
    }

    @Override // com.easysocket.interfaces.conn.IHeartManager
    public void stopHeartbeat() {
        this.isActivate = false;
        stopHeartThread();
    }

    private void stopHeartThread() {
        ScheduledExecutorService scheduledExecutorService = this.heartExecutor;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            return;
        }
        this.heartExecutor.shutdownNow();
        this.heartExecutor = null;
        resetLoseTimes();
    }

    @Override // com.easysocket.interfaces.conn.IHeartManager
    public void onReceiveHeartBeat() {
        resetLoseTimes();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetLoseTimes() {
        this.loseTimes.set(-1);
    }

    @Override // com.easysocket.interfaces.conn.ISocketActionListener
    public void onSocketConnSuccess(SocketAddress socketAddress) {
        if (this.isActivate) {
            startHeartThread();
        }
    }

    @Override // com.easysocket.interfaces.conn.ISocketActionListener
    public void onSocketConnFail(SocketAddress socketAddress, Boolean bool) {
        if (bool.booleanValue()) {
            return;
        }
        stopHeartThread();
    }

    @Override // com.easysocket.interfaces.conn.ISocketActionListener
    public void onSocketDisconnect(SocketAddress socketAddress, Boolean bool) {
        if (bool.booleanValue()) {
            return;
        }
        stopHeartThread();
    }

    @Override // com.easysocket.interfaces.conn.ISocketActionListener
    public void onSocketResponse(SocketAddress socketAddress, OriginReadData originReadData) {
        HeartbeatListener heartbeatListener = this.heartbeatListener;
        if (heartbeatListener == null || !heartbeatListener.isServerHeartbeat(originReadData)) {
            return;
        }
        onReceiveHeartBeat();
    }

    @Override // com.easysocket.interfaces.config.IOptions
    public Object setOptions(EasySocketOptions easySocketOptions) {
        this.socketOptions = easySocketOptions;
        long heartbeatFreq = easySocketOptions.getHeartbeatFreq();
        this.freq = heartbeatFreq;
        if (heartbeatFreq < 1000) {
            heartbeatFreq = 1000;
        }
        this.freq = heartbeatFreq;
        return this;
    }

    @Override // com.easysocket.interfaces.config.IOptions
    public EasySocketOptions getOptions() {
        return this.socketOptions;
    }
}
