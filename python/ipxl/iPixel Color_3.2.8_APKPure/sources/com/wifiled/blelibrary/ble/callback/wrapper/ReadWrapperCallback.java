package com.wifiled.blelibrary.ble.callback.wrapper;

import android.bluetooth.BluetoothGattCharacteristic;

/* loaded from: classes2.dex */
public interface ReadWrapperCallback<T> {
    void onReadFailed(T t, int i);

    void onReadSuccess(T t, BluetoothGattCharacteristic bluetoothGattCharacteristic);
}
