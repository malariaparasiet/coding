package com.wifiled.blelibrary.ble.callback.wrapper;

import android.bluetooth.BluetoothGatt;

/* loaded from: classes2.dex */
public interface ConnectWrapperCallback<T> {
    void onConnectFailed(T t, int i);

    void onConnectionChanged(T t);

    void onReady(T t);

    void onServicesDiscovered(T t, BluetoothGatt bluetoothGatt);
}
