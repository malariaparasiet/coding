package com.wifiled.blelibrary.ble.request;

import android.bluetooth.BluetoothGattCharacteristic;
import com.wifiled.blelibrary.ble.Ble;
import com.wifiled.blelibrary.ble.BleRequestImpl;
import com.wifiled.blelibrary.ble.annotation.Implement;
import com.wifiled.blelibrary.ble.callback.BleReadCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.BleWrapperCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.ReadWrapperCallback;
import com.wifiled.blelibrary.ble.model.BleDevice;
import java.util.UUID;

@Implement(ReadRequest2.class)
/* loaded from: classes2.dex */
public class ReadRequest2<T extends BleDevice> implements ReadWrapperCallback<T> {
    private BleReadCallback<T> bleReadCallback;
    private final BleWrapperCallback<T> bleWrapperCallback = Ble.options().getBleWrapperCallback();
    private final BleRequestImpl<T> bleRequest = BleRequestImpl.getBleRequest();

    public boolean read(T t, BleReadCallback<T> bleReadCallback) {
        this.bleReadCallback = bleReadCallback;
        return this.bleRequest.readCharacteristic(t.getBleAddress());
    }

    public boolean readByUuid(T t, UUID uuid, UUID uuid2, BleReadCallback<T> bleReadCallback) {
        this.bleReadCallback = bleReadCallback;
        return this.bleRequest.readCharacteristicByUuid(t.getBleAddress(), uuid, uuid2);
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ReadWrapperCallback
    public void onReadSuccess(T t, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BleReadCallback<T> bleReadCallback = this.bleReadCallback;
        if (bleReadCallback != null) {
            bleReadCallback.onReadSuccess(t, bluetoothGattCharacteristic);
        }
        BleWrapperCallback<T> bleWrapperCallback = this.bleWrapperCallback;
        if (bleWrapperCallback != null) {
            bleWrapperCallback.onReadSuccess((BleWrapperCallback<T>) t, bluetoothGattCharacteristic);
        }
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ReadWrapperCallback
    public void onReadFailed(T t, int i) {
        BleReadCallback<T> bleReadCallback = this.bleReadCallback;
        if (bleReadCallback != null) {
            bleReadCallback.onReadFailed(t, i);
        }
        BleWrapperCallback<T> bleWrapperCallback = this.bleWrapperCallback;
        if (bleWrapperCallback != null) {
            bleWrapperCallback.onReadFailed((BleWrapperCallback<T>) t, i);
        }
    }
}
