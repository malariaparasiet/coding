package com.wifiled.blelibrary.ble.callback;

import android.bluetooth.BluetoothGattDescriptor;

/* loaded from: classes2.dex */
public abstract class BleReadDescCallback<T> {
    public void onDescReadFailed(T t, int i) {
    }

    public void onDescReadSuccess(T t, BluetoothGattDescriptor bluetoothGattDescriptor) {
    }
}
