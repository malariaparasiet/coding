package com.wifiled.ipixels.core;

import android.bluetooth.BluetoothDevice;
import java.util.UUID;

/* loaded from: classes3.dex */
public interface OnWriteDataCallback {
    void onBleResult(BluetoothDevice device, UUID serviceUUID, UUID characteristicUUID, boolean result, byte[] data);
}
