package com.easysocket.entity;

/* loaded from: classes2.dex */
public class SocketAddress {
    private SocketAddress backupAddress;
    private String ip;
    private int port;

    public SocketAddress getBackupAddress() {
        return this.backupAddress;
    }

    public void setBackupAddress(SocketAddress socketAddress) {
        this.backupAddress = socketAddress;
    }

    public SocketAddress(String str, int i) {
        this.ip = str;
        this.port = i;
    }

    public String getIp() {
        return this.ip;
    }

    public int getPort() {
        return this.port;
    }
}
