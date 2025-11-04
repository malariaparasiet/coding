package com.wifiled.blelibrary.ble.callback.wrapper;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;

/* loaded from: classes2.dex */
public interface NotifyWrapperCallback<T> {
    void onChanged(T t, BluetoothGattCharacteristic bluetoothGattCharacteristic);

    void onNotifyCanceled(T t);

    void onNotifyFailed(T t, int i);

    void onNotifySuccess(T t);

    void onWriteSuccess(BluetoothDevice bluetoothDevice, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i);
}
