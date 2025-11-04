package com.easysocket.interfaces.conn;

import com.easysocket.entity.OriginReadData;
import com.easysocket.entity.SocketAddress;

/* loaded from: classes2.dex */
public interface ISocketActionListener {
    void onSocketConnFail(SocketAddress socketAddress, Boolean bool);

    void onSocketConnSuccess(SocketAddress socketAddress);

    void onSocketDisconnect(SocketAddress socketAddress, Boolean bool);

    void onSocketResponse(SocketAddress socketAddress, OriginReadData originReadData);
}
