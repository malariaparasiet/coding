package com.wifiled.blelibrary.ble.callback;

import android.bluetooth.BluetoothGattCharacteristic;

/* loaded from: classes2.dex */
public abstract class BleReadCallback<T> {
    public void onReadFailed(T t, int i) {
    }

    public void onReadSuccess(T t, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
    }
}
