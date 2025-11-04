package com.wifiled.blelibrary.ble.request;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import com.wifiled.blelibrary.ble.Ble;
import com.wifiled.blelibrary.ble.BleRequestImpl;
import com.wifiled.blelibrary.ble.annotation.Implement;
import com.wifiled.blelibrary.ble.callback.BleNotifyCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.BleWrapperCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.NotifyWrapperCallback;
import com.wifiled.blelibrary.ble.model.BleDevice;
import java.util.UUID;

@Implement(NotifyRequest.class)
/* loaded from: classes2.dex */
public class NotifyRequest<T extends BleDevice> implements NotifyWrapperCallback<T> {
    private static final String TAG = "NotifyRequest";
    private BleNotifyCallback<T> notifyCallback;
    private final BleWrapperCallback<T> bleWrapperCallback = Ble.options().getBleWrapperCallback();
    private final BleRequestImpl<T> bleRequest = BleRequestImpl.getBleRequest();
    private Ble<T> ble = Ble.getInstance();

    public void notify(T t, boolean z, BleNotifyCallback<T> bleNotifyCallback) {
        this.notifyCallback = bleNotifyCallback;
        this.bleRequest.setCharacteristicNotification(t.getBleAddress(), z);
    }

    public void notifyByUuid(T t, boolean z, UUID uuid, UUID uuid2, BleNotifyCallback<T> bleNotifyCallback) {
        this.notifyCallback = bleNotifyCallback;
        this.bleRequest.setCharacteristicNotificationByUuid(t.getBleAddress(), z, uuid, uuid2);
    }

    @Deprecated
    public void cancelNotify(T t, BleNotifyCallback<T> bleNotifyCallback) {
        this.notifyCallback = bleNotifyCallback;
        this.bleRequest.setCharacteristicNotification(t.getBleAddress(), false);
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.NotifyWrapperCallback
    public void onChanged(T t, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BleNotifyCallback<T> bleNotifyCallback = this.notifyCallback;
        if (bleNotifyCallback != null) {
            bleNotifyCallback.onChanged(t, bluetoothGattCharacteristic);
        }
        BleWrapperCallback<T> bleWrapperCallback = this.bleWrapperCallback;
        if (bleWrapperCallback != null) {
            bleWrapperCallback.onChanged((BleWrapperCallback<T>) t, bluetoothGattCharacteristic);
        }
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.NotifyWrapperCallback
    public void onNotifySuccess(T t) {
        BleNotifyCallback<T> bleNotifyCallback = this.notifyCallback;
        if (bleNotifyCallback != null) {
            bleNotifyCallback.onNotifySuccess(t);
        }
        BleWrapperCallback<T> bleWrapperCallback = this.bleWrapperCallback;
        if (bleWrapperCallback != null) {
            bleWrapperCallback.onNotifySuccess((BleWrapperCallback<T>) t);
        }
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.NotifyWrapperCallback
    public void onNotifyFailed(T t, int i) {
        BleNotifyCallback<T> bleNotifyCallback = this.notifyCallback;
        if (bleNotifyCallback != null) {
            bleNotifyCallback.onNotifyFailed(t, i);
        }
        BleWrapperCallback<T> bleWrapperCallback = this.bleWrapperCallback;
        if (bleWrapperCallback != null) {
            bleWrapperCallback.onNotifyFailed(t, i);
        }
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.NotifyWrapperCallback
    public void onNotifyCanceled(T t) {
        BleNotifyCallback<T> bleNotifyCallback = this.notifyCallback;
        if (bleNotifyCallback != null) {
            bleNotifyCallback.onNotifyCanceled(t);
        }
        BleWrapperCallback<T> bleWrapperCallback = this.bleWrapperCallback;
        if (bleWrapperCallback != null) {
            bleWrapperCallback.onNotifyCanceled((BleWrapperCallback<T>) t);
        }
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.NotifyWrapperCallback
    public void onWriteSuccess(BluetoothDevice bluetoothDevice, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        if (this.notifyCallback != null) {
            T bleDevice = this.ble.getBleDevice(bluetoothDevice);
            if (bleDevice.getBluetoothDevice() == null) {
                bleDevice.setBluetoothDevice(bluetoothDevice);
            }
            this.notifyCallback.onWriteSuccess(bleDevice, bluetoothGattCharacteristic, i);
        }
    }
}
