package com.easysocket.connection.reconnect;

import com.easysocket.entity.OriginReadData;
import com.easysocket.entity.SocketAddress;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.interfaces.conn.IReconnListener;
import com.easysocket.interfaces.conn.ISocketActionListener;

/* loaded from: classes2.dex */
public abstract class AbsReconnection implements ISocketActionListener, IReconnListener {
    protected IConnectionManager connectionManager;
    protected boolean isDetach;

    @Override // com.easysocket.interfaces.conn.ISocketActionListener
    public void onSocketResponse(SocketAddress socketAddress, OriginReadData originReadData) {
    }

    @Override // com.easysocket.interfaces.conn.IReconnListener
    public synchronized void attach(IConnectionManager iConnectionManager) {
        if (!this.isDetach) {
            detach();
        }
        this.isDetach = false;
        this.connectionManager = iConnectionManager;
        iConnectionManager.subscribeSocketAction(this);
    }

    @Override // com.easysocket.interfaces.conn.IReconnListener
    public synchronized void detach() {
        this.isDetach = true;
        IConnectionManager iConnectionManager = this.connectionManager;
        if (iConnectionManager != null) {
            iConnectionManager.unSubscribeSocketAction(this);
        }
    }
}
