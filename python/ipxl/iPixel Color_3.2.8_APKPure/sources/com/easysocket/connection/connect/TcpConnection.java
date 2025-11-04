package com.easysocket.connection.connect;

import com.easysocket.config.DefaultX509ProtocolTrustManager;
import com.easysocket.config.SocketSSLConfig;
import com.easysocket.entity.SocketAddress;
import com.easysocket.utils.LogUtil;
import com.easysocket.utils.Util;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.security.SecureRandom;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* loaded from: classes2.dex */
public class TcpConnection extends SuperConnection {
    private Socket socket;

    public TcpConnection(SocketAddress socketAddress) {
        super(socketAddress);
    }

    @Override // com.easysocket.connection.connect.SuperConnection
    protected void openConnection() throws Exception {
        try {
            this.socket = getSocketByConfig();
        } catch (Exception e) {
            e.printStackTrace();
            this.connectionStatus.set(0);
        }
        try {
            this.socket.connect(new InetSocketAddress(this.socketAddress.getIp(), this.socketAddress.getPort()), this.socketOptions.getConnectTimeout());
        } catch (Exception e2) {
            e2.printStackTrace();
            this.connectionStatus.set(0);
        }
        this.socket.setTcpNoDelay(true);
        if (!this.socket.isConnected() || this.socket.isClosed()) {
            return;
        }
        onConnectionOpened();
    }

    @Override // com.easysocket.connection.connect.SuperConnection
    protected void closeConnection() throws IOException {
        Socket socket = this.socket;
        if (socket != null) {
            socket.close();
        }
    }

    private synchronized Socket getSocketByConfig() throws Exception {
        if (this.socketOptions.getSocketFactory() != null) {
            return this.socketOptions.getSocketFactory().createSocket(this.socketAddress, this.socketOptions);
        }
        SocketSSLConfig easySSLConfig = this.socketOptions.getEasySSLConfig();
        if (easySSLConfig == null) {
            return new Socket();
        }
        SSLSocketFactory customSSLFactory = easySSLConfig.getCustomSSLFactory();
        if (customSSLFactory == null) {
            String str = "SSL";
            if (!Util.isStringEmpty(easySSLConfig.getProtocol())) {
                str = easySSLConfig.getProtocol();
            }
            TrustManager[] trustManagers = easySSLConfig.getTrustManagers();
            if (trustManagers == null || trustManagers.length == 0) {
                trustManagers = new TrustManager[]{new DefaultX509ProtocolTrustManager()};
            }
            try {
                SSLContext sSLContext = SSLContext.getInstance(str);
                sSLContext.init(easySSLConfig.getKeyManagers(), trustManagers, new SecureRandom());
                return sSLContext.getSocketFactory().createSocket();
            } catch (Exception e) {
                if (this.socketOptions.isDebug()) {
                    e.printStackTrace();
                }
                LogUtil.e(e.getMessage());
                return new Socket();
            }
        }
        try {
            return customSSLFactory.createSocket();
        } catch (IOException e2) {
            if (this.socketOptions.isDebug()) {
                e2.printStackTrace();
            }
            LogUtil.e(e2.getMessage());
            return new Socket();
        }
    }

    @Override // com.easysocket.interfaces.conn.IConnectionManager
    public InputStream getInputStream() {
        Socket socket = this.socket;
        if (socket == null || !socket.isConnected() || this.socket.isClosed()) {
            return null;
        }
        try {
            return this.socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.easysocket.interfaces.conn.IConnectionManager
    public OutputStream getOutStream() {
        Socket socket = this.socket;
        if (socket == null || !socket.isConnected() || this.socket.isClosed()) {
            return null;
        }
        try {
            return this.socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.easysocket.connection.connect.SuperConnection, com.easysocket.interfaces.conn.IConnectionManager
    public boolean isConnectViable() {
        try {
            if (!this.socket.isConnected() || this.socket.isClosed()) {
                return false;
            }
            return this.socket.getKeepAlive();
        } catch (SocketException e) {
            e.printStackTrace();
            return false;
        }
    }
}
