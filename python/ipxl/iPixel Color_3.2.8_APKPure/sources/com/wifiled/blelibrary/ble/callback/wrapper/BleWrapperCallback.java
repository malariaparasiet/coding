package com.wifiled.blelibrary.ble.callback.wrapper;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.util.Log;
import com.wifiled.blelibrary.ble.callback.BleScanCallback;
import com.wifiled.blelibrary.ble.model.BleDevice;

/* loaded from: classes2.dex */
public abstract class BleWrapperCallback<T extends BleDevice> extends BleScanCallback<T> implements ConnectWrapperCallback<T>, NotifyWrapperCallback<T>, WriteWrapperCallback<T>, ReadWrapperCallback<T>, DescWrapperCallback<T>, MtuWrapperCallback<T> {
    private static final String TAG = "BleWrapperCallback";

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.NotifyWrapperCallback
    public void onChanged(T t, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ConnectWrapperCallback
    public void onConnectFailed(T t, int i) {
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ConnectWrapperCallback
    public void onConnectionChanged(T t) {
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.DescWrapperCallback
    public void onDescReadFailed(T t, int i) {
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.DescWrapperCallback
    public void onDescReadSuccess(T t, BluetoothGattDescriptor bluetoothGattDescriptor) {
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.DescWrapperCallback
    public void onDescWriteFailed(T t, int i) {
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.DescWrapperCallback
    public void onDescWriteSuccess(T t, BluetoothGattDescriptor bluetoothGattDescriptor) {
    }

    @Override // com.wifiled.blelibrary.ble.callback.BleScanCallback
    public void onLeScan(T t, int i, byte[] bArr) {
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.MtuWrapperCallback
    public void onMtuChanged(T t, int i, int i2) {
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.NotifyWrapperCallback
    public void onNotifyCanceled(T t) {
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.NotifyWrapperCallback
    public void onNotifySuccess(T t) {
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ReadWrapperCallback
    public void onReadFailed(T t, int i) {
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ReadWrapperCallback
    public void onReadSuccess(T t, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ConnectWrapperCallback
    public void onReady(T t) {
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ConnectWrapperCallback
    public void onServicesDiscovered(T t, BluetoothGatt bluetoothGatt) {
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.WriteWrapperCallback
    public void onWriteFailed(T t, int i) {
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.WriteWrapperCallback
    public void onWriteSuccess(T t, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        Log.d("akon", "run here onWriteSuccess");
    }
}
