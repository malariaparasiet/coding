package com.wifiled.blelibrary.ble.model;

import android.text.TextUtils;
import com.wifiled.blelibrary.ble.exception.BleWriteException;

/* loaded from: classes2.dex */
public class EntityData {
    private static final int DEFAULT_LENGTH = 20;
    private String address;
    private boolean autoWriteMode;
    private byte[] data;
    private long delay;
    private boolean lastPackComplete;
    private int packLength;

    public EntityData(boolean z, String str, byte[] bArr, int i, long j, boolean z2) {
        this.autoWriteMode = z;
        this.address = str;
        this.data = bArr;
        this.packLength = i;
        this.delay = j;
        this.lastPackComplete = z2;
    }

    public EntityData(String str, byte[] bArr, int i, long j, boolean z) {
        this(false, str, bArr, i, j, false);
    }

    public EntityData(String str, byte[] bArr, int i, long j) {
        this(false, str, bArr, i, j, false);
    }

    public EntityData() {
        this.packLength = 20;
    }

    public EntityData(String str, byte[] bArr, int i) {
        this(false, str, bArr, i, 0L, false);
    }

    public boolean isAutoWriteMode() {
        return this.autoWriteMode;
    }

    public void setAutoWriteMode(boolean z) {
        this.autoWriteMode = z;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public byte[] getData() {
        if (this.data == null) {
            this.data = new byte[0];
        }
        return this.data;
    }

    public void setData(byte[] bArr) {
        this.data = bArr;
    }

    public int getPackLength() {
        return this.packLength;
    }

    public void setPackLength(int i) {
        this.packLength = i;
    }

    public long getDelay() {
        return this.delay;
    }

    public void setDelay(long j) {
        this.delay = j;
    }

    public boolean isLastPackComplete() {
        return this.lastPackComplete;
    }

    public void setLastPackComplete(boolean z) {
        this.lastPackComplete = z;
    }

    public static class Builder {
        private String address;
        private boolean autoWriteMode;
        private byte[] data;
        private long delay;
        private boolean lastPackComplete;
        private int packLength = 20;

        public boolean isAutoWriteMode() {
            return this.autoWriteMode;
        }

        public Builder setAutoWriteMode(boolean z) {
            this.autoWriteMode = z;
            return this;
        }

        public String getAddress() {
            return this.address;
        }

        public Builder setAddress(String str) {
            this.address = str;
            return this;
        }

        public byte[] getData() {
            return this.data;
        }

        public Builder setData(byte[] bArr) {
            this.data = bArr;
            return this;
        }

        public int getPackLength() {
            return this.packLength;
        }

        public Builder setPackLength(int i) {
            this.packLength = i;
            return this;
        }

        public long getDelay() {
            return this.delay;
        }

        public Builder setDelay(long j) {
            this.delay = j;
            return this;
        }

        public boolean isLastPackComplete() {
            return this.lastPackComplete;
        }

        public Builder setLastPackComplete(boolean z) {
            this.lastPackComplete = z;
            return this;
        }

        public EntityData build() {
            return new EntityData(this.autoWriteMode, this.address, this.data, this.packLength, this.delay, this.lastPackComplete);
        }
    }

    public static void validParms(EntityData entityData) {
        String str;
        if (!TextUtils.isEmpty(entityData.address)) {
            str = "";
        } else {
            str = "ble address isn't null";
        }
        if (entityData.data == null) {
            str = "ble data isn't null";
        }
        if (entityData.packLength <= 0) {
            str = "The data length per packet cannot be less than 0";
        }
        if (!TextUtils.isEmpty(str)) {
            throw new BleWriteException(str);
        }
    }
}
