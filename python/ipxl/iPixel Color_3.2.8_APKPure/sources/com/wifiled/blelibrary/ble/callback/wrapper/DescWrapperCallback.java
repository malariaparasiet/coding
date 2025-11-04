package com.wifiled.blelibrary.ble.callback.wrapper;

import android.bluetooth.BluetoothGattDescriptor;

/* loaded from: classes2.dex */
public interface DescWrapperCallback<T> {
    void onDescReadFailed(T t, int i);

    void onDescReadSuccess(T t, BluetoothGattDescriptor bluetoothGattDescriptor);

    void onDescWriteFailed(T t, int i);

    void onDescWriteSuccess(T t, BluetoothGattDescriptor bluetoothGattDescriptor);
}
