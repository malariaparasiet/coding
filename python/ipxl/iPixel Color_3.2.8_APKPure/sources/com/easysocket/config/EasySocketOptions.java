package com.easysocket.config;

import androidx.lifecycle.CoroutineLiveDataKt;
import com.bumptech.glide.load.Key;
import com.easysocket.connection.reconnect.AbsReconnection;
import com.easysocket.connection.reconnect.DefaultReConnection;
import com.easysocket.entity.SocketAddress;
import com.easysocket.interfaces.io.IMessageProtocol;
import com.wifiled.ipixels.view.video_clip.VideoTrimmerUtil;
import java.nio.ByteOrder;

/* loaded from: classes2.dex */
public class EasySocketOptions {
    private static boolean isDebug = true;
    private SocketAddress backupAddress;
    private CallbakcIdKeyFactory callbakcIdKeyFactory;
    private String charsetName;
    private int connectTimeout;
    private SocketSSLConfig easySSLConfig;
    private long heartbeatFreq;
    private boolean isOpenRequestTimeout;
    private int maxHeartbeatLoseTimes;
    private int maxReadBytes;
    private int maxResponseDataMb;
    private int maxWriteBytes;
    private IMessageProtocol messageProtocol;
    private ByteOrder readOrder;
    private AbsReconnection reconnectionManager;
    private long requestTimeout;
    private SocketAddress socketAddress;
    private SocketFactory socketFactory;
    private ByteOrder writeOrder;

    public boolean isDebug() {
        return isDebug;
    }

    public static class Builder {
        EasySocketOptions socketOptions;

        public Builder() {
            this(EasySocketOptions.getDefaultOptions());
        }

        public Builder(EasySocketOptions easySocketOptions) {
            this.socketOptions = easySocketOptions;
        }

        public Builder setSocketAddress(SocketAddress socketAddress) {
            this.socketOptions.socketAddress = socketAddress;
            return this;
        }

        public Builder setBackupAddress(SocketAddress socketAddress) {
            this.socketOptions.backupAddress = socketAddress;
            return this;
        }

        public Builder setOpenRequestTimeout(boolean z) {
            this.socketOptions.isOpenRequestTimeout = z;
            return this;
        }

        public Builder setRequestTimeout(long j) {
            this.socketOptions.requestTimeout = j;
            return this;
        }

        public Builder setCallbackIdKeyFactory(CallbakcIdKeyFactory callbakcIdKeyFactory) {
            this.socketOptions.callbakcIdKeyFactory = callbakcIdKeyFactory;
            return this;
        }

        public Builder setWriteOrder(ByteOrder byteOrder) {
            this.socketOptions.writeOrder = byteOrder;
            return this;
        }

        public Builder setReadOrder(ByteOrder byteOrder) {
            this.socketOptions.readOrder = byteOrder;
            return this;
        }

        public Builder setReaderProtocol(IMessageProtocol iMessageProtocol) {
            this.socketOptions.messageProtocol = iMessageProtocol;
            return this;
        }

        public Builder setMaxWriteBytes(int i) {
            this.socketOptions.maxWriteBytes = i;
            return this;
        }

        public Builder setMaxReadBytes(int i) {
            this.socketOptions.maxReadBytes = i;
            return this;
        }

        public Builder setHeartbeatFreq(long j) {
            this.socketOptions.heartbeatFreq = j;
            return this;
        }

        public Builder setMaxHeartbeatLoseTimes(int i) {
            this.socketOptions.maxHeartbeatLoseTimes = i;
            return this;
        }

        public Builder setConnectTimeout(int i) {
            this.socketOptions.connectTimeout = i;
            return this;
        }

        public Builder setMaxResponseDataMb(int i) {
            this.socketOptions.maxResponseDataMb = i;
            return this;
        }

        public Builder setReconnectionManager(AbsReconnection absReconnection) {
            this.socketOptions.reconnectionManager = absReconnection;
            return this;
        }

        public Builder setEasySSLConfig(SocketSSLConfig socketSSLConfig) {
            this.socketOptions.easySSLConfig = socketSSLConfig;
            return this;
        }

        public Builder setSocketFactory(SocketFactory socketFactory) {
            this.socketOptions.socketFactory = socketFactory;
            return this;
        }

        public Builder setCharsetName(String str) {
            this.socketOptions.charsetName = str;
            return this;
        }

        public EasySocketOptions build() {
            return this.socketOptions;
        }
    }

    public static EasySocketOptions getDefaultOptions() {
        EasySocketOptions easySocketOptions = new EasySocketOptions();
        easySocketOptions.socketAddress = null;
        easySocketOptions.backupAddress = null;
        easySocketOptions.heartbeatFreq = CoroutineLiveDataKt.DEFAULT_TIMEOUT;
        easySocketOptions.messageProtocol = null;
        easySocketOptions.maxResponseDataMb = 5;
        easySocketOptions.connectTimeout = 5000;
        easySocketOptions.maxWriteBytes = 100;
        easySocketOptions.maxReadBytes = 50;
        easySocketOptions.readOrder = ByteOrder.BIG_ENDIAN;
        easySocketOptions.writeOrder = ByteOrder.BIG_ENDIAN;
        easySocketOptions.maxHeartbeatLoseTimes = 5;
        easySocketOptions.reconnectionManager = new DefaultReConnection();
        easySocketOptions.easySSLConfig = null;
        easySocketOptions.socketFactory = null;
        easySocketOptions.callbakcIdKeyFactory = null;
        easySocketOptions.requestTimeout = VideoTrimmerUtil.MAX_SHOOT_DURATION;
        easySocketOptions.isOpenRequestTimeout = true;
        easySocketOptions.charsetName = Key.STRING_CHARSET_NAME;
        return easySocketOptions;
    }

    public String getCharsetName() {
        return this.charsetName;
    }

    public ByteOrder getWriteOrder() {
        return this.writeOrder;
    }

    public ByteOrder getReadOrder() {
        return this.readOrder;
    }

    public IMessageProtocol getMessageProtocol() {
        return this.messageProtocol;
    }

    public int getMaxWriteBytes() {
        return this.maxWriteBytes;
    }

    public int getMaxReadBytes() {
        return this.maxReadBytes;
    }

    public long getHeartbeatFreq() {
        return this.heartbeatFreq;
    }

    public int getMaxHeartbeatLoseTimes() {
        return this.maxHeartbeatLoseTimes;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public int getMaxResponseDataMb() {
        return this.maxResponseDataMb;
    }

    public AbsReconnection getReconnectionManager() {
        return this.reconnectionManager;
    }

    public SocketSSLConfig getEasySSLConfig() {
        return this.easySSLConfig;
    }

    public SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public long getRequestTimeout() {
        return this.requestTimeout;
    }

    public boolean isOpenRequestTimeout() {
        return this.isOpenRequestTimeout;
    }

    public CallbakcIdKeyFactory getCallbakcIdKeyFactory() {
        return this.callbakcIdKeyFactory;
    }

    public static void setIsDebug(boolean z) {
        isDebug = z;
    }

    public void setWriteOrder(ByteOrder byteOrder) {
        this.writeOrder = byteOrder;
    }

    public void setReadOrder(ByteOrder byteOrder) {
        this.readOrder = byteOrder;
    }

    public void setMessageProtocol(IMessageProtocol iMessageProtocol) {
        this.messageProtocol = iMessageProtocol;
    }

    public void setMaxWriteBytes(int i) {
        this.maxWriteBytes = i;
    }

    public void setMaxReadBytes(int i) {
        this.maxReadBytes = i;
    }

    public void setHeartbeatFreq(long j) {
        this.heartbeatFreq = j;
    }

    public void setMaxHeartbeatLoseTimes(int i) {
        this.maxHeartbeatLoseTimes = i;
    }

    public void setConnectTimeout(int i) {
        this.connectTimeout = i;
    }

    public void setMaxResponseDataMb(int i) {
        this.maxResponseDataMb = i;
    }

    public void setReconnectionManager(AbsReconnection absReconnection) {
        this.reconnectionManager = absReconnection;
    }

    public void setEasySSLConfig(SocketSSLConfig socketSSLConfig) {
        this.easySSLConfig = socketSSLConfig;
    }

    public void setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    public void setCallbakcIdKeyFactory(CallbakcIdKeyFactory callbakcIdKeyFactory) {
        this.callbakcIdKeyFactory = callbakcIdKeyFactory;
    }

    public void setRequestTimeout(long j) {
        this.requestTimeout = j;
    }

    public void setOpenRequestTimeout(boolean z) {
        this.isOpenRequestTimeout = z;
    }

    public SocketAddress getSocketAddress() {
        return this.socketAddress;
    }

    public SocketAddress getBackupAddress() {
        return this.backupAddress;
    }
}
