package com.easysocket.config;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* loaded from: classes2.dex */
public class SocketSSLConfig {
    private SSLSocketFactory mCustomSSLFactory;
    private KeyManager[] mKeyManagers;
    private String mProtocol;
    private TrustManager[] mTrustManagers;

    private SocketSSLConfig() {
    }

    public static class Builder {
        private SocketSSLConfig mConfig = new SocketSSLConfig();

        public Builder setProtocol(String str) {
            this.mConfig.mProtocol = str;
            return this;
        }

        public Builder setTrustManagers(TrustManager[] trustManagerArr) {
            this.mConfig.mTrustManagers = trustManagerArr;
            return this;
        }

        public Builder setKeyManagers(KeyManager[] keyManagerArr) {
            this.mConfig.mKeyManagers = keyManagerArr;
            return this;
        }

        public Builder setCustomSSLFactory(SSLSocketFactory sSLSocketFactory) {
            this.mConfig.mCustomSSLFactory = sSLSocketFactory;
            return this;
        }

        public SocketSSLConfig build() {
            return this.mConfig;
        }
    }

    public KeyManager[] getKeyManagers() {
        return this.mKeyManagers;
    }

    public String getProtocol() {
        return this.mProtocol;
    }

    public TrustManager[] getTrustManagers() {
        return this.mTrustManagers;
    }

    public SSLSocketFactory getCustomSSLFactory() {
        return this.mCustomSSLFactory;
    }
}
