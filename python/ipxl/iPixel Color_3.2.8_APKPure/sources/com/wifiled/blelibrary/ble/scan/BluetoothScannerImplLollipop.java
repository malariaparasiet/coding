package com.wifiled.blelibrary.ble.scan;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.ParcelUuid;
import com.wifiled.blelibrary.ble.Ble;
import com.wifiled.blelibrary.ble.BleLog;
import com.wifiled.blelibrary.ble.callback.wrapper.ScanWrapperCallback;
import com.wifiled.blelibrary.ble.model.ScanRecord;
import com.wifiled.blelibrary.ble.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* loaded from: classes2.dex */
class BluetoothScannerImplLollipop extends BleScannerCompat {
    private static final String TAG = "BluetoothScannerImplLol";
    private ScanSettings scanSettings;
    private BluetoothLeScanner scanner;
    private List<ScanFilter> filters = new ArrayList();
    private final ScanCallback scannerCallback = new ScanCallback() { // from class: com.wifiled.blelibrary.ble.scan.BluetoothScannerImplLollipop.1
        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            ScanRecord parseFromBytes;
            BluetoothDevice device = scanResult.getDevice();
            byte[] bytes = scanResult.getScanRecord().getBytes();
            if (BluetoothScannerImplLollipop.this.scanWrapperCallback != null) {
                BluetoothScannerImplLollipop.this.scanWrapperCallback.onLeScan(device, scanResult.getRssi(), bytes);
            }
            if (!Ble.options().isParseScanData || (parseFromBytes = ScanRecord.parseFromBytes(bytes)) == null || BluetoothScannerImplLollipop.this.scanWrapperCallback == null) {
                return;
            }
            BluetoothScannerImplLollipop.this.scanWrapperCallback.onParsedData(device, parseFromBytes);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> list) {
            Iterator<ScanResult> it = list.iterator();
            while (it.hasNext()) {
                BleLog.d("ScanResult - Results", it.next().toString());
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            BleLog.e("Scan Failed", "Error Code: " + i);
            if (BluetoothScannerImplLollipop.this.scanWrapperCallback != null) {
                BluetoothScannerImplLollipop.this.scanWrapperCallback.onScanFailed(i);
            }
        }
    };

    BluetoothScannerImplLollipop() {
    }

    @Override // com.wifiled.blelibrary.ble.scan.BleScannerCompat
    public void startScan(ScanWrapperCallback scanWrapperCallback) {
        super.startScan(scanWrapperCallback);
        if (this.scanner == null) {
            this.scanner = this.bluetoothAdapter.getBluetoothLeScanner();
        }
        setScanSettings();
        this.scanner.startScan(this.filters, this.scanSettings, this.scannerCallback);
    }

    @Override // com.wifiled.blelibrary.ble.scan.BleScannerCompat
    public void stopScan() {
        if (this.scanner == null) {
            this.scanner = this.bluetoothAdapter.getBluetoothLeScanner();
        }
        this.scanner.stopScan(this.scannerCallback);
        super.stopScan();
    }

    private void setScanSettings() {
        boolean isBackground = Utils.isBackground(Ble.getInstance().getContext());
        BleLog.d(TAG, "currently in the background:>>>>>" + isBackground);
        ScanFilter scanFilter = Ble.options().getScanFilter();
        if (scanFilter != null) {
            this.filters.add(scanFilter);
        }
        if (isBackground) {
            UUID uuidService = Ble.options().getUuidService();
            if (scanFilter == null) {
                this.filters.add(new ScanFilter.Builder().setServiceUuid(ParcelUuid.fromString(uuidService.toString())).build());
            }
            this.scanSettings = new ScanSettings.Builder().setScanMode(0).build();
            return;
        }
        if (scanFilter == null) {
            this.filters.clear();
        }
        this.scanSettings = new ScanSettings.Builder().setScanMode(2).build();
    }
}
