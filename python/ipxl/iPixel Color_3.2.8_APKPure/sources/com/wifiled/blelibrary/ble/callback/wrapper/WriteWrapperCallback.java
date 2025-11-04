package com.wifiled.blelibrary.ble.callback.wrapper;

import android.bluetooth.BluetoothGattCharacteristic;

/* loaded from: classes2.dex */
public interface WriteWrapperCallback<T> {
    void onWriteFailed(T t, int i);

    void onWriteSuccess(T t, BluetoothGattCharacteristic bluetoothGattCharacteristic);
}
