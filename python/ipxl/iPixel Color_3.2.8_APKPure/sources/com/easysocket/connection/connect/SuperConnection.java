package com.easysocket.connection.connect;

import com.easysocket.EasySocket;
import com.easysocket.callback.SuperCallBack;
import com.easysocket.config.EasySocketOptions;
import com.easysocket.connection.action.SocketAction;
import com.easysocket.connection.dispatcher.CallbackResponseDispatcher;
import com.easysocket.connection.dispatcher.SocketActionDispatcher;
import com.easysocket.connection.heartbeat.HeartManager;
import com.easysocket.connection.iowork.IOManager;
import com.easysocket.connection.reconnect.AbsReconnection;
import com.easysocket.entity.SocketAddress;
import com.easysocket.entity.basemsg.ISender;
import com.easysocket.entity.basemsg.SuperCallbackSender;
import com.easysocket.entity.exception.NoNullException;
import com.easysocket.interfaces.config.IConnectionSwitchListener;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.interfaces.conn.ISocketActionListener;
import com.easysocket.utils.LogUtil;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public abstract class SuperConnection implements IConnectionManager {
    private SocketActionDispatcher actionDispatcher;
    private CallbackResponseDispatcher callbackResponseDispatcher;
    private Thread connectThread;
    protected final AtomicInteger connectionStatus = new AtomicInteger(0);
    private IConnectionSwitchListener connectionSwitchListener;
    private HeartManager heartManager;
    private IOManager ioManager;
    private AbsReconnection reconnection;
    protected SocketAddress socketAddress;
    protected EasySocketOptions socketOptions;

    protected abstract void closeConnection() throws IOException;

    protected abstract void openConnection() throws Exception;

    public SuperConnection(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
        this.actionDispatcher = new SocketActionDispatcher(this, socketAddress);
    }

    @Override // com.easysocket.interfaces.conn.ISubscribeSocketAction
    public void subscribeSocketAction(ISocketActionListener iSocketActionListener) {
        this.actionDispatcher.subscribe(iSocketActionListener);
    }

    @Override // com.easysocket.interfaces.conn.ISubscribeSocketAction
    public void unSubscribeSocketAction(ISocketActionListener iSocketActionListener) {
        this.actionDispatcher.unsubscribe(iSocketActionListener);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.easysocket.interfaces.config.IOptions
    public synchronized IConnectionManager setOptions(EasySocketOptions easySocketOptions) {
        if (easySocketOptions == null) {
            return this;
        }
        this.socketOptions = easySocketOptions;
        IOManager iOManager = this.ioManager;
        if (iOManager != null) {
            iOManager.setOptions(easySocketOptions);
        }
        HeartManager heartManager = this.heartManager;
        if (heartManager != null) {
            heartManager.setOptions(easySocketOptions);
        }
        CallbackResponseDispatcher callbackResponseDispatcher = this.callbackResponseDispatcher;
        if (callbackResponseDispatcher != null) {
            callbackResponseDispatcher.setSocketOptions(easySocketOptions);
        }
        AbsReconnection absReconnection = this.reconnection;
        if (absReconnection != null && !absReconnection.equals(easySocketOptions.getReconnectionManager())) {
            this.reconnection.detach();
            AbsReconnection reconnectionManager = easySocketOptions.getReconnectionManager();
            this.reconnection = reconnectionManager;
            reconnectionManager.attach(this);
        }
        return this;
    }

    @Override // com.easysocket.interfaces.config.IOptions
    public EasySocketOptions getOptions() {
        return this.socketOptions;
    }

    @Override // com.easysocket.interfaces.conn.IConnectionManager
    public synchronized void connect() {
        LogUtil.d("开始socket连接");
        if (this.connectionStatus.get() != 0) {
            LogUtil.d("socket已连接");
            return;
        }
        this.connectionStatus.set(1);
        if (this.socketAddress.getIp() == null) {
            throw new NoNullException("连接参数有误，请检查是否设置了IP");
        }
        if (this.heartManager == null) {
            this.heartManager = new HeartManager(this, this.actionDispatcher);
        }
        AbsReconnection absReconnection = this.reconnection;
        if (absReconnection != null) {
            absReconnection.detach();
        }
        AbsReconnection reconnectionManager = this.socketOptions.getReconnectionManager();
        this.reconnection = reconnectionManager;
        if (reconnectionManager != null) {
            reconnectionManager.attach(this);
        }
        ConnectThread connectThread = new ConnectThread("connect thread for" + this.socketAddress);
        this.connectThread = connectThread;
        connectThread.setDaemon(true);
        this.connectThread.start();
    }

    @Override // com.easysocket.interfaces.conn.IConnectionManager
    public synchronized void disconnect(Boolean bool) {
        if (this.connectionStatus.get() == 3) {
            return;
        }
        this.connectionStatus.set(3);
        DisconnectThread disconnectThread = new DisconnectThread(bool, "disconn thread：" + (this.socketAddress.getIp() + " : " + this.socketAddress.getPort()));
        disconnectThread.setDaemon(true);
        disconnectThread.start();
    }

    private class DisconnectThread extends Thread {
        Boolean isNeedReconnect;

        public DisconnectThread(Boolean bool, String str) {
            super(str);
            this.isNeedReconnect = bool;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                try {
                    if (SuperConnection.this.ioManager != null) {
                        SuperConnection.this.ioManager.closeIO();
                    }
                    if (SuperConnection.this.callbackResponseDispatcher != null) {
                        SuperConnection.this.callbackResponseDispatcher.shutdownThread();
                    }
                    if (SuperConnection.this.connectThread != null && SuperConnection.this.connectThread.isAlive() && !SuperConnection.this.connectThread.isInterrupted()) {
                        SuperConnection.this.connectThread.interrupt();
                    }
                    LogUtil.d("关闭socket连接");
                    SuperConnection.this.closeConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } finally {
                SuperConnection.this.connectionStatus.set(0);
            }
        }
    }

    private class ConnectThread extends Thread {
        public ConnectThread(String str) {
            super(str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                SuperConnection.this.openConnection();
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.d("socket连接失败");
                SuperConnection.this.connectionStatus.set(0);
                SuperConnection.this.actionDispatcher.dispatchAction(SocketAction.ACTION_CONN_FAIL, Boolean.TRUE);
            }
        }
    }

    protected void onConnectionOpened() {
        LogUtil.d("socket连接成功");
        this.actionDispatcher.dispatchAction(SocketAction.ACTION_CONN_SUCCESS);
        this.connectionStatus.set(2);
        openSocketManager();
    }

    private void openSocketManager() {
        if (this.callbackResponseDispatcher == null) {
            this.callbackResponseDispatcher = new CallbackResponseDispatcher(this);
        }
        if (this.ioManager == null) {
            this.ioManager = new IOManager(this, this.actionDispatcher);
        }
        this.callbackResponseDispatcher.engineThread();
        this.ioManager.startIO();
    }

    @Override // com.easysocket.interfaces.conn.IConnectionManager
    public synchronized void switchHost(SocketAddress socketAddress) {
        if (socketAddress != null) {
            SocketAddress socketAddress2 = this.socketAddress;
            this.socketAddress = socketAddress;
            SocketActionDispatcher socketActionDispatcher = this.actionDispatcher;
            if (socketActionDispatcher != null) {
                socketActionDispatcher.setSocketAddress(socketAddress);
            }
            IConnectionSwitchListener iConnectionSwitchListener = this.connectionSwitchListener;
            if (iConnectionSwitchListener != null) {
                iConnectionSwitchListener.onSwitchConnectionInfo(this, socketAddress2, socketAddress);
            }
        }
    }

    public void setOnConnectionSwitchListener(IConnectionSwitchListener iConnectionSwitchListener) {
        this.connectionSwitchListener = iConnectionSwitchListener;
    }

    @Override // com.easysocket.interfaces.conn.IConnectionManager
    public boolean isConnectViable() {
        return this.connectionStatus.get() == 0;
    }

    @Override // com.easysocket.interfaces.conn.IConnectionManager
    public int getConnectionStatus() {
        return this.connectionStatus.get();
    }

    private IConnectionManager sendBytes(byte[] bArr) {
        if (this.socketOptions.getMessageProtocol() != null) {
            bArr = this.socketOptions.getMessageProtocol().pack(bArr);
        }
        IOManager iOManager = this.ioManager;
        if (iOManager != null) {
            iOManager.sendBytes(bArr);
        }
        return this;
    }

    @Override // com.easysocket.interfaces.callback.ICallBack
    public void onCallBack(SuperCallBack superCallBack) {
        this.callbackResponseDispatcher.addSocketCallback(superCallBack);
    }

    @Override // com.easysocket.interfaces.conn.ISend
    public synchronized IConnectionManager upBytes(byte[] bArr) {
        sendBytes(bArr);
        return this;
    }

    @Override // com.easysocket.interfaces.conn.ISend
    public synchronized IConnectionManager upString(String str) {
        sendBytes(str.getBytes(Charset.forName(EasySocket.getInstance().getOptions().getCharsetName())));
        return this;
    }

    @Override // com.easysocket.interfaces.conn.ISend
    public synchronized IConnectionManager upObject(ISender iSender) {
        sendBytes(new Gson().toJson(iSender).getBytes(Charset.forName(EasySocket.getInstance().getOptions().getCharsetName())));
        return this;
    }

    @Override // com.easysocket.interfaces.conn.ISend
    public synchronized IConnectionManager upCallbackMessage(SuperCallbackSender superCallbackSender) {
        this.callbackResponseDispatcher.checkCallbackSender(superCallbackSender);
        sendBytes(new Gson().toJson(superCallbackSender).getBytes(Charset.forName(EasySocket.getInstance().getOptions().getCharsetName())));
        return this;
    }

    @Override // com.easysocket.interfaces.conn.IConnectionManager
    public HeartManager getHeartManager() {
        return this.heartManager;
    }
}
