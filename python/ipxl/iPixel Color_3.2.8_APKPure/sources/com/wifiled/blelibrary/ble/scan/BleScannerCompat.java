package com.wifiled.blelibrary.ble.scan;

import android.bluetooth.BluetoothAdapter;
import com.wifiled.blelibrary.ble.callback.wrapper.ScanWrapperCallback;

/* loaded from: classes2.dex */
public abstract class BleScannerCompat {
    private static BleScannerCompat mInstance;
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    ScanWrapperCallback scanWrapperCallback;

    public static BleScannerCompat getScanner() {
        BleScannerCompat bleScannerCompat = mInstance;
        if (bleScannerCompat != null) {
            return bleScannerCompat;
        }
        BluetoothScannerImplLollipop bluetoothScannerImplLollipop = new BluetoothScannerImplLollipop();
        mInstance = bluetoothScannerImplLollipop;
        return bluetoothScannerImplLollipop;
    }

    public void startScan(ScanWrapperCallback scanWrapperCallback) {
        this.scanWrapperCallback = scanWrapperCallback;
        scanWrapperCallback.onStart();
    }

    public void stopScan() {
        ScanWrapperCallback scanWrapperCallback = this.scanWrapperCallback;
        if (scanWrapperCallback != null) {
            scanWrapperCallback.onStop();
        }
    }
}
