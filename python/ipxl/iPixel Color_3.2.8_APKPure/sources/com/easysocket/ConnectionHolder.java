package com.easysocket;

import com.easysocket.config.EasySocketOptions;
import com.easysocket.connection.connect.TcpConnection;
import com.easysocket.entity.SocketAddress;
import com.easysocket.interfaces.config.IConnectionSwitchListener;
import com.easysocket.interfaces.conn.IConnectionManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class ConnectionHolder {
    private volatile Map<String, IConnectionManager> mConnectionManagerMap;

    private static class InstanceHolder {
        private static final ConnectionHolder INSTANCE = new ConnectionHolder();

        private InstanceHolder() {
        }
    }

    public static ConnectionHolder getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private ConnectionHolder() {
        this.mConnectionManagerMap = new HashMap();
        this.mConnectionManagerMap.clear();
    }

    public void removeConnection(SocketAddress socketAddress) {
        this.mConnectionManagerMap.remove(createKey(socketAddress));
    }

    public IConnectionManager getConnection(SocketAddress socketAddress) {
        IConnectionManager iConnectionManager = this.mConnectionManagerMap.get(createKey(socketAddress));
        if (iConnectionManager == null) {
            return getConnection(socketAddress, EasySocketOptions.getDefaultOptions());
        }
        return getConnection(socketAddress, iConnectionManager.getOptions());
    }

    public IConnectionManager getConnection(SocketAddress socketAddress, EasySocketOptions easySocketOptions) {
        IConnectionManager iConnectionManager = this.mConnectionManagerMap.get(createKey(socketAddress));
        if (iConnectionManager != null) {
            iConnectionManager.setOptions(easySocketOptions);
            return iConnectionManager;
        }
        return createNewManagerAndCache(socketAddress, easySocketOptions);
    }

    private IConnectionManager createNewManagerAndCache(SocketAddress socketAddress, EasySocketOptions easySocketOptions) {
        TcpConnection tcpConnection = new TcpConnection(socketAddress);
        tcpConnection.setOptions(easySocketOptions);
        tcpConnection.setOnConnectionSwitchListener(new IConnectionSwitchListener() { // from class: com.easysocket.ConnectionHolder.1
            @Override // com.easysocket.interfaces.config.IConnectionSwitchListener
            public void onSwitchConnectionInfo(IConnectionManager iConnectionManager, SocketAddress socketAddress2, SocketAddress socketAddress3) {
                synchronized (ConnectionHolder.this.mConnectionManagerMap) {
                    ConnectionHolder.this.mConnectionManagerMap.remove(ConnectionHolder.this.createKey(socketAddress2));
                    ConnectionHolder.this.mConnectionManagerMap.put(ConnectionHolder.this.createKey(socketAddress3), iConnectionManager);
                }
            }
        });
        synchronized (this.mConnectionManagerMap) {
            this.mConnectionManagerMap.put(createKey(socketAddress), tcpConnection);
        }
        return tcpConnection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String createKey(SocketAddress socketAddress) {
        return socketAddress.getIp() + ":" + socketAddress.getPort();
    }
}
