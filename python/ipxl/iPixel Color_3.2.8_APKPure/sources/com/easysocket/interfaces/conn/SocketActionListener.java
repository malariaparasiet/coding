package com.easysocket.interfaces.conn;

import com.easysocket.entity.OriginReadData;
import com.easysocket.entity.SocketAddress;

/* loaded from: classes2.dex */
public abstract class SocketActionListener implements ISocketActionListener {
    @Override // com.easysocket.interfaces.conn.ISocketActionListener
    public void onSocketConnFail(SocketAddress socketAddress, Boolean bool) {
    }

    @Override // com.easysocket.interfaces.conn.ISocketActionListener
    public void onSocketConnSuccess(SocketAddress socketAddress) {
    }

    @Override // com.easysocket.interfaces.conn.ISocketActionListener
    public void onSocketDisconnect(SocketAddress socketAddress, Boolean bool) {
    }

    @Override // com.easysocket.interfaces.conn.ISocketActionListener
    public void onSocketResponse(SocketAddress socketAddress, OriginReadData originReadData) {
    }
}
