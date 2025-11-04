package com.wifiled.blelibrary.ble.callback.wrapper;

import android.bluetooth.BluetoothDevice;
import com.wifiled.blelibrary.ble.model.ScanRecord;

/* loaded from: classes2.dex */
public interface ScanWrapperCallback {
    void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr);

    void onParsedData(BluetoothDevice bluetoothDevice, ScanRecord scanRecord);

    void onScanFailed(int i);

    void onStart();

    void onStop();
}
