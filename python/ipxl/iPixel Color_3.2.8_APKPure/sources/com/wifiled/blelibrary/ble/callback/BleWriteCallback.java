package com.wifiled.blelibrary.ble.callback;

import android.bluetooth.BluetoothGattCharacteristic;

/* loaded from: classes2.dex */
public abstract class BleWriteCallback<T> {
    public void onWriteFailed(T t, int i) {
    }

    public abstract void onWriteSuccess(T t, BluetoothGattCharacteristic bluetoothGattCharacteristic);
}
