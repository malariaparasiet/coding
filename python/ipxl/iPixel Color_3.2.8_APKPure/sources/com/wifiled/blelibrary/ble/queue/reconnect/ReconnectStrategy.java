package com.wifiled.blelibrary.ble.queue.reconnect;

/* loaded from: classes2.dex */
public class ReconnectStrategy {
    public long delay;
    public boolean reconnectIfOpenBluetooth;
    public int times;

    private ReconnectStrategy(Builder builder) {
        this.times = builder.times;
        this.delay = builder.delay;
        this.reconnectIfOpenBluetooth = builder.reconnectIfOpenBluetooth;
    }

    public static final class Builder {
        public int times = -1;
        public long delay = DefaultReConnectHandler.DEFAULT_CONNECT_DELAY;
        public boolean reconnectIfOpenBluetooth = true;

        public Builder times(int i) {
            this.times = i;
            return this;
        }

        public Builder delay(long j) {
            this.delay = j;
            return this;
        }

        public Builder reconnectIfOpenBluetooth(boolean z) {
            this.reconnectIfOpenBluetooth = z;
            return this;
        }

        public ReconnectStrategy build() {
            return new ReconnectStrategy(this);
        }
    }
}
