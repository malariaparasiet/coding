package com.wifiled.blelibrary.ble.scan;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import com.wifiled.blelibrary.ble.callback.wrapper.ScanWrapperCallback;

/* loaded from: classes2.dex */
class BluetoothScannerImplJB extends BleScannerCompat {
    private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() { // from class: com.wifiled.blelibrary.ble.scan.BluetoothScannerImplJB.1
        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            BluetoothScannerImplJB.this.scanWrapperCallback.onLeScan(bluetoothDevice, i, bArr);
        }
    };

    BluetoothScannerImplJB() {
    }

    @Override // com.wifiled.blelibrary.ble.scan.BleScannerCompat
    public void startScan(ScanWrapperCallback scanWrapperCallback) {
        super.startScan(scanWrapperCallback);
        this.bluetoothAdapter.startLeScan(this.leScanCallback);
    }

    @Override // com.wifiled.blelibrary.ble.scan.BleScannerCompat
    public void stopScan() {
        super.stopScan();
        this.bluetoothAdapter.stopLeScan(this.leScanCallback);
    }
}
