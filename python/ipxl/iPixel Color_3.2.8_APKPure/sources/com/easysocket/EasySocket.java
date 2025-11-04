package com.easysocket;

import com.easysocket.config.EasySocketOptions;
import com.easysocket.connection.heartbeat.HeartManager;
import com.easysocket.entity.SocketAddress;
import com.easysocket.entity.basemsg.ISender;
import com.easysocket.entity.basemsg.SuperCallbackSender;
import com.easysocket.entity.exception.InitialExeption;
import com.easysocket.entity.exception.NoNullException;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.interfaces.conn.ISocketActionListener;

/* loaded from: classes2.dex */
public class EasySocket {
    private static ConnectionHolder connectionHolder = ConnectionHolder.getInstance();
    private static volatile EasySocket singleton = null;
    private IConnectionManager connection;
    private EasySocketOptions options;

    public static EasySocket getInstance() {
        if (singleton == null) {
            synchronized (EasySocket.class) {
                if (singleton == null) {
                    singleton = new EasySocket();
                }
            }
        }
        return singleton;
    }

    public EasySocket options(EasySocketOptions easySocketOptions) {
        this.options = easySocketOptions;
        return this;
    }

    public EasySocketOptions getOptions() {
        EasySocketOptions easySocketOptions = this.options;
        return easySocketOptions == null ? EasySocketOptions.getDefaultOptions() : easySocketOptions;
    }

    public EasySocket createConnection() {
        SocketAddress socketAddress = this.options.getSocketAddress();
        if (this.options.getSocketAddress() == null) {
            throw new InitialExeption("请在EasySocketOptions中设置SocketAddress");
        }
        if (this.options.getBackupAddress() != null) {
            socketAddress.setBackupAddress(this.options.getBackupAddress());
        }
        if (this.connection == null) {
            ConnectionHolder connectionHolder2 = connectionHolder;
            EasySocketOptions easySocketOptions = this.options;
            if (easySocketOptions == null) {
                easySocketOptions = EasySocketOptions.getDefaultOptions();
            }
            this.connection = connectionHolder2.getConnection(socketAddress, easySocketOptions);
        }
        this.connection.connect();
        return this;
    }

    public EasySocket connect() {
        getConnection().connect();
        return this;
    }

    public boolean isConnectViable() {
        return getConnection().isConnectViable();
    }

    public EasySocket disconnect(boolean z) {
        getConnection().disconnect(new Boolean(z));
        return this;
    }

    public EasySocket destroyConnection() {
        getConnection().disconnect(new Boolean(false));
        connectionHolder.removeConnection(this.options.getSocketAddress());
        this.connection = null;
        return this;
    }

    public IConnectionManager upCallbackMessage(SuperCallbackSender superCallbackSender) {
        getConnection().upCallbackMessage(superCallbackSender);
        return this.connection;
    }

    public IConnectionManager upObject(ISender iSender) {
        getConnection().upObject(iSender);
        return this.connection;
    }

    public IConnectionManager upString(String str) {
        getConnection().upString(str);
        return this.connection;
    }

    public IConnectionManager upBytes(byte[] bArr) {
        getConnection().upBytes(bArr);
        return this.connection;
    }

    public EasySocket subscribeSocketAction(ISocketActionListener iSocketActionListener) {
        getConnection().subscribeSocketAction(iSocketActionListener);
        return this;
    }

    public EasySocket startHeartBeat(ISender iSender, HeartManager.HeartbeatListener heartbeatListener) {
        getConnection().getHeartManager().startHeartbeat(iSender, heartbeatListener);
        return this;
    }

    public EasySocket startHeartBeat(String str, HeartManager.HeartbeatListener heartbeatListener) {
        getConnection().getHeartManager().startHeartbeat(str, heartbeatListener);
        return this;
    }

    public EasySocket startHeartBeat(byte[] bArr, HeartManager.HeartbeatListener heartbeatListener) {
        getConnection().getHeartManager().startHeartbeat(bArr, heartbeatListener);
        return this;
    }

    public IConnectionManager getConnection() {
        IConnectionManager iConnectionManager = this.connection;
        if (iConnectionManager != null) {
            return iConnectionManager;
        }
        throw new NoNullException("请先创建socket连接");
    }

    public IConnectionManager buildSpecifyConnection(SocketAddress socketAddress, EasySocketOptions easySocketOptions) {
        ConnectionHolder connectionHolder2 = connectionHolder;
        if (easySocketOptions == null) {
            easySocketOptions = EasySocketOptions.getDefaultOptions();
        }
        IConnectionManager connection = connectionHolder2.getConnection(socketAddress, easySocketOptions);
        connection.connect();
        return connection;
    }

    public IConnectionManager getSpecifyConnection(SocketAddress socketAddress) {
        return connectionHolder.getConnection(socketAddress);
    }

    public IConnectionManager upToSpecifyConnection(ISender iSender, SocketAddress socketAddress) {
        IConnectionManager specifyConnection = getSpecifyConnection(socketAddress);
        if (specifyConnection != null) {
            specifyConnection.upObject(iSender);
        }
        return specifyConnection;
    }

    public void setDebug(boolean z) {
        EasySocketOptions.setIsDebug(z);
    }
}
