package com.wifiled.blelibrary.ble.queue;

import com.wifiled.blelibrary.ble.model.BleDevice;

/* loaded from: classes2.dex */
public class RequestTask {
    private String[] address;
    private byte[] data;
    private long delay;
    private BleDevice[] devices;

    RequestTask(String[] strArr, BleDevice[] bleDeviceArr, byte[] bArr, long j) {
        this.address = strArr;
        this.devices = bleDeviceArr;
        this.data = bArr;
        this.delay = j;
    }

    public String[] getAddress() {
        return this.address;
    }

    public BleDevice[] getDevices() {
        return this.devices;
    }

    public byte[] getData() {
        return this.data;
    }

    public long getDelay() {
        return this.delay;
    }

    public static final class Builder {
        private String[] address;
        private byte[] data;
        private long delay = 500;
        private BleDevice[] devices;

        public Builder address(String... strArr) {
            this.address = strArr;
            return this;
        }

        public Builder devices(BleDevice... bleDeviceArr) {
            this.devices = bleDeviceArr;
            return this;
        }

        public Builder data(byte[] bArr) {
            this.data = bArr;
            return this;
        }

        public Builder delay(long j) {
            this.delay = j;
            return this;
        }

        public RequestTask build() {
            return new RequestTask(this.address, this.devices, this.data, this.delay);
        }
    }
}
