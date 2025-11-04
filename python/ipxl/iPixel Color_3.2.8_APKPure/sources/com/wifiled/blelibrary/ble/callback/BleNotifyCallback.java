package com.wifiled.blelibrary.ble.callback;

import android.bluetooth.BluetoothGattCharacteristic;

/* loaded from: classes2.dex */
public abstract class BleNotifyCallback<T> {
    public abstract void onChanged(T t, BluetoothGattCharacteristic bluetoothGattCharacteristic);

    public void onNotifyCanceled(T t) {
    }

    public void onNotifyFailed(T t, int i) {
    }

    public void onNotifySuccess(T t) {
    }

    public abstract void onWriteSuccess(T t, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i);
}
