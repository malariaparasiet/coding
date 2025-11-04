package com.wifiled.blelibrary.ble.request;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import androidx.core.os.HandlerCompat;
import com.wifiled.blelibrary.ble.Ble;
import com.wifiled.blelibrary.ble.BleHandler;
import com.wifiled.blelibrary.ble.BleStates;
import com.wifiled.blelibrary.ble.callback.BleScanCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.BleWrapperCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.ScanWrapperCallback;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.blelibrary.ble.model.ScanRecord;
import com.wifiled.blelibrary.ble.scan.BleScannerCompat;
import com.wifiled.blelibrary.ble.utils.Utils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class ScanRequest<T extends BleDevice> implements ScanWrapperCallback {
    private static final String HANDLER_TOKEN = "stop_token";
    private static final String TAG = "ScanRequest";
    private BleScanCallback<T> bleScanCallback;
    private boolean scanning = false;
    private final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private final Map<String, T> scanDevices = new HashMap();
    private final Handler handler = BleHandler.of();
    private final BleWrapperCallback<T> bleWrapperCallback = Ble.options().getBleWrapperCallback();

    public void startScan(BleScanCallback<T> bleScanCallback, long j) {
        if (bleScanCallback == null) {
            throw new IllegalArgumentException("BleScanCallback can not be null!");
        }
        this.bleScanCallback = bleScanCallback;
        if (!hasRequiredPermissions()) {
            this.bleScanCallback.onScanFailed(BleStates.BlePermissionError);
            return;
        }
        if (!isBluetoothEnabled()) {
            this.bleScanCallback.onScanFailed(BleStates.BluetoothNotOpen);
            return;
        }
        if (this.scanning) {
            this.bleScanCallback.onScanFailed(BleStates.ScanAlready);
            return;
        }
        this.scanning = true;
        BleScannerCompat.getScanner().startScan(this);
        if (j >= 0) {
            HandlerCompat.postDelayed(this.handler, new Runnable() { // from class: com.wifiled.blelibrary.ble.request.ScanRequest$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ScanRequest.this.m3059x6af4d7ce();
                }
            }, HANDLER_TOKEN, j);
        }
    }

    /* renamed from: lambda$startScan$0$com-wifiled-blelibrary-ble-request-ScanRequest, reason: not valid java name */
    /* synthetic */ void m3059x6af4d7ce() {
        if (this.scanning) {
            stopScan();
        }
    }

    public void stopScan() {
        if (isBluetoothEnabled()) {
            if (!this.scanning) {
                BleScanCallback<T> bleScanCallback = this.bleScanCallback;
                if (bleScanCallback != null) {
                    bleScanCallback.onScanFailed(BleStates.ScanStopAlready);
                    return;
                }
                return;
            }
            this.handler.removeCallbacksAndMessages(HANDLER_TOKEN);
            BleScannerCompat.getScanner().stopScan();
        }
    }

    private boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    private boolean hasRequiredPermissions() {
        Context context = Ble.getInstance().getContext();
        if (Build.VERSION.SDK_INT >= 31) {
            return Utils.isPermission(context, "android.permission.BLUETOOTH_SCAN") && Utils.isPermission(context, "android.permission.ACCESS_FINE_LOCATION");
        }
        return Utils.isPermission(context, "android.permission.ACCESS_FINE_LOCATION");
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ScanWrapperCallback
    public void onStart() {
        BleScanCallback<T> bleScanCallback = this.bleScanCallback;
        if (bleScanCallback != null) {
            bleScanCallback.onStart();
        }
        BleWrapperCallback<T> bleWrapperCallback = this.bleWrapperCallback;
        if (bleWrapperCallback != null) {
            bleWrapperCallback.onStart();
        }
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ScanWrapperCallback
    public void onStop() {
        this.scanning = false;
        BleScanCallback<T> bleScanCallback = this.bleScanCallback;
        if (bleScanCallback != null) {
            bleScanCallback.onStop();
        }
        BleWrapperCallback<T> bleWrapperCallback = this.bleWrapperCallback;
        if (bleWrapperCallback != null) {
            bleWrapperCallback.onStop();
        }
        this.scanDevices.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ScanWrapperCallback
    public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        if (bluetoothDevice == null) {
            return;
        }
        String address = bluetoothDevice.getAddress();
        T device = getDevice(address);
        if (device == null) {
            BleDevice create = Ble.options().getFactory().create(address, bluetoothDevice.getName());
            create.setDeviceType(bluetoothDevice.getType());
            this.scanDevices.put(address, create);
            BleScanCallback<T> bleScanCallback = this.bleScanCallback;
            if (bleScanCallback != null) {
                bleScanCallback.onLeScan(create, i, bArr);
            }
            BleWrapperCallback<T> bleWrapperCallback = this.bleWrapperCallback;
            if (bleWrapperCallback != 0) {
                bleWrapperCallback.onLeScan((BleWrapperCallback<T>) create, i, bArr);
                return;
            }
            return;
        }
        if (Ble.options().isIgnoreRepeat) {
            return;
        }
        BleScanCallback<T> bleScanCallback2 = this.bleScanCallback;
        if (bleScanCallback2 != null) {
            bleScanCallback2.onLeScan(device, i, bArr);
        }
        BleWrapperCallback<T> bleWrapperCallback2 = this.bleWrapperCallback;
        if (bleWrapperCallback2 != null) {
            bleWrapperCallback2.onLeScan((BleWrapperCallback<T>) device, i, bArr);
        }
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ScanWrapperCallback
    public void onScanFailed(int i) {
        this.scanning = false;
        BleScanCallback<T> bleScanCallback = this.bleScanCallback;
        if (bleScanCallback != null) {
            bleScanCallback.onScanFailed(i);
        }
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ScanWrapperCallback
    public void onParsedData(BluetoothDevice bluetoothDevice, ScanRecord scanRecord) {
        if (this.bleScanCallback == null || bluetoothDevice == null) {
            return;
        }
        this.bleScanCallback.onParsedData(getDevice(bluetoothDevice.getAddress()), scanRecord);
    }

    public boolean isScanning() {
        return this.scanning;
    }

    private T getDevice(String str) {
        return this.scanDevices.get(str);
    }

    public void cancelScanCallback() {
        this.bleScanCallback = null;
    }
}
