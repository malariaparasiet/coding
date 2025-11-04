package com.easysocket.interfaces.conn;

import com.easysocket.entity.SocketAddress;
import com.easysocket.interfaces.callback.ICallBack;
import com.easysocket.interfaces.config.IOptions;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public interface IConnectionManager extends ISubscribeSocketAction, IOptions<IConnectionManager>, ISend, ICallBack {
    void connect();

    void disconnect(Boolean bool);

    int getConnectionStatus();

    IHeartManager getHeartManager();

    InputStream getInputStream();

    OutputStream getOutStream();

    boolean isConnectViable();

    void switchHost(SocketAddress socketAddress);
}
