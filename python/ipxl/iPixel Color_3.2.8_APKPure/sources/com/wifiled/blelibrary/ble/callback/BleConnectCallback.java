package com.wifiled.blelibrary.ble.callback;

import android.bluetooth.BluetoothGatt;

/* loaded from: classes2.dex */
public abstract class BleConnectCallback<T> {
    public void onConnectCancel(T t) {
    }

    public void onConnectFailed(T t, int i) {
    }

    public abstract void onConnectionChanged(T t);

    public void onReady(T t) {
    }

    public void onServicesDiscovered(T t, BluetoothGatt bluetoothGatt) {
    }
}
