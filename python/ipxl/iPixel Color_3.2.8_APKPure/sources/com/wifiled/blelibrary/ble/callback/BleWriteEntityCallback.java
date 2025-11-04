package com.wifiled.blelibrary.ble.callback;

/* loaded from: classes2.dex */
public abstract class BleWriteEntityCallback<T> {
    public void onWriteCancel() {
    }

    public abstract void onWriteFailed();

    public void onWriteProgress(double d) {
    }

    public abstract void onWriteSuccess();
}
