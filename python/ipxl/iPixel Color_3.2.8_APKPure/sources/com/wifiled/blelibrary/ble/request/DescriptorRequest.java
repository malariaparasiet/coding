package com.wifiled.blelibrary.ble.request;

import android.bluetooth.BluetoothGattDescriptor;
import com.wifiled.blelibrary.ble.Ble;
import com.wifiled.blelibrary.ble.BleRequestImpl;
import com.wifiled.blelibrary.ble.annotation.Implement;
import com.wifiled.blelibrary.ble.callback.BleReadDescCallback;
import com.wifiled.blelibrary.ble.callback.BleWriteDescCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.BleWrapperCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.DescWrapperCallback;
import com.wifiled.blelibrary.ble.model.BleDevice;
import java.util.UUID;

@Implement(DescriptorRequest.class)
/* loaded from: classes2.dex */
public class DescriptorRequest<T extends BleDevice> implements DescWrapperCallback<T> {
    private BleReadDescCallback<T> bleReadDescCallback;
    private BleWriteDescCallback<T> bleWriteDescCallback;
    private final BleWrapperCallback<T> bleWrapperCallback = Ble.options().getBleWrapperCallback();
    private final BleRequestImpl<T> bleRequest = BleRequestImpl.getBleRequest();

    public boolean readDes(T t, UUID uuid, UUID uuid2, UUID uuid3, BleReadDescCallback<T> bleReadDescCallback) {
        this.bleReadDescCallback = bleReadDescCallback;
        return this.bleRequest.readDescriptor(t.getBleAddress(), uuid, uuid2, uuid3);
    }

    public boolean writeDes(T t, byte[] bArr, UUID uuid, UUID uuid2, UUID uuid3, BleWriteDescCallback<T> bleWriteDescCallback) {
        this.bleWriteDescCallback = bleWriteDescCallback;
        return this.bleRequest.writeDescriptor(t.getBleAddress(), bArr, uuid, uuid2, uuid3);
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.DescWrapperCallback
    public void onDescReadSuccess(T t, BluetoothGattDescriptor bluetoothGattDescriptor) {
        BleReadDescCallback<T> bleReadDescCallback = this.bleReadDescCallback;
        if (bleReadDescCallback != null) {
            bleReadDescCallback.onDescReadSuccess(t, bluetoothGattDescriptor);
        }
        BleWrapperCallback<T> bleWrapperCallback = this.bleWrapperCallback;
        if (bleWrapperCallback != null) {
            bleWrapperCallback.onDescReadSuccess((BleWrapperCallback<T>) t, bluetoothGattDescriptor);
        }
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.DescWrapperCallback
    public void onDescReadFailed(T t, int i) {
        BleReadDescCallback<T> bleReadDescCallback = this.bleReadDescCallback;
        if (bleReadDescCallback != null) {
            bleReadDescCallback.onDescReadFailed(t, i);
        }
        BleWrapperCallback<T> bleWrapperCallback = this.bleWrapperCallback;
        if (bleWrapperCallback != null) {
            bleWrapperCallback.onDescReadFailed((BleWrapperCallback<T>) t, i);
        }
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.DescWrapperCallback
    public void onDescWriteSuccess(T t, BluetoothGattDescriptor bluetoothGattDescriptor) {
        BleWriteDescCallback<T> bleWriteDescCallback = this.bleWriteDescCallback;
        if (bleWriteDescCallback != null) {
            bleWriteDescCallback.onDescWriteSuccess(t, bluetoothGattDescriptor);
        }
        BleWrapperCallback<T> bleWrapperCallback = this.bleWrapperCallback;
        if (bleWrapperCallback != null) {
            bleWrapperCallback.onDescWriteSuccess((BleWrapperCallback<T>) t, bluetoothGattDescriptor);
        }
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.DescWrapperCallback
    public void onDescWriteFailed(T t, int i) {
        BleWriteDescCallback<T> bleWriteDescCallback = this.bleWriteDescCallback;
        if (bleWriteDescCallback != null) {
            bleWriteDescCallback.onDescWriteFailed(t, i);
        }
        BleWrapperCallback<T> bleWrapperCallback = this.bleWrapperCallback;
        if (bleWrapperCallback != null) {
            bleWrapperCallback.onDescWriteFailed((BleWrapperCallback<T>) t, i);
        }
    }
}
