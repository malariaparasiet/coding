package com.easysocket.config;

import com.easysocket.entity.SocketAddress;
import java.net.Socket;

/* loaded from: classes2.dex */
public abstract class SocketFactory {
    public abstract Socket createSocket(SocketAddress socketAddress, EasySocketOptions easySocketOptions) throws Exception;
}
