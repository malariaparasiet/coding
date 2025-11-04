package com.wifiled.blelibrary.ble.callback;

import android.bluetooth.BluetoothGattDescriptor;

/* loaded from: classes2.dex */
public abstract class BleWriteDescCallback<T> {
    public void onDescWriteFailed(T t, int i) {
    }

    public void onDescWriteSuccess(T t, BluetoothGattDescriptor bluetoothGattDescriptor) {
    }
}
