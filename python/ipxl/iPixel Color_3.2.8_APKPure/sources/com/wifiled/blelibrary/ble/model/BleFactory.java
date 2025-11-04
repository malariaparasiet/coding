package com.wifiled.blelibrary.ble.model;

import android.bluetooth.BluetoothDevice;
import com.wifiled.blelibrary.ble.model.BleDevice;

/* loaded from: classes2.dex */
public abstract class BleFactory<T extends BleDevice> {
    public T create(String str, String str2) {
        return (T) new BleDevice(str, str2);
    }

    public static <T extends BleDevice> T create(BluetoothDevice bluetoothDevice) {
        return (T) new BleDevice(bluetoothDevice);
    }
}
