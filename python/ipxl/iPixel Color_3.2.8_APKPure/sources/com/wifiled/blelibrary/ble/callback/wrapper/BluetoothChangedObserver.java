package com.wifiled.blelibrary.ble.callback.wrapper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.wifiled.blelibrary.ble.BleLog;
import com.wifiled.blelibrary.ble.callback.BleStatusCallback;
import com.wifiled.blelibrary.ble.queue.reconnect.DefaultReConnectHandler;
import com.wifiled.blelibrary.ble.request.ConnectRequest;
import com.wifiled.blelibrary.ble.request.Rproxy;
import com.wifiled.blelibrary.ble.request.ScanRequest;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public class BluetoothChangedObserver {
    private BleStatusCallback bleStatusCallback;
    private BleReceiver mBleReceiver;
    private Context mContext;

    public BluetoothChangedObserver(Context context) {
        this.mContext = context;
    }

    public void setBleScanCallbackInner(BleStatusCallback bleStatusCallback) {
        this.bleStatusCallback = bleStatusCallback;
    }

    public void registerReceiver() {
        this.mBleReceiver = new BleReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        if (Build.VERSION.SDK_INT > 33) {
            this.mContext.registerReceiver(this.mBleReceiver, intentFilter, 4);
        } else {
            this.mContext.registerReceiver(this.mBleReceiver, intentFilter);
        }
    }

    public void unregisterReceiver() {
        try {
            this.mContext.unregisterReceiver(this.mBleReceiver);
            this.bleStatusCallback = null;
        } catch (Exception unused) {
        }
    }

    static class BleReceiver extends BroadcastReceiver {
        private WeakReference<BluetoothChangedObserver> mObserverWeakReference;

        public BleReceiver(BluetoothChangedObserver bluetoothChangedObserver) {
            this.mObserverWeakReference = new WeakReference<>(bluetoothChangedObserver);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                BluetoothChangedObserver bluetoothChangedObserver = this.mObserverWeakReference.get();
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                if (intExtra == 12) {
                    BleLog.e("", "系统蓝牙已开启");
                    if (bluetoothChangedObserver.bleStatusCallback != null) {
                        bluetoothChangedObserver.bleStatusCallback.onBluetoothStatusChanged(true);
                    }
                    DefaultReConnectHandler.provideReconnectHandler().openBluetooth();
                    return;
                }
                if (intExtra == 10) {
                    BleLog.e("", "系统蓝牙已关闭");
                    if (bluetoothChangedObserver.bleStatusCallback != null) {
                        bluetoothChangedObserver.bleStatusCallback.onBluetoothStatusChanged(false);
                    }
                    ScanRequest scanRequest = (ScanRequest) Rproxy.getRequest(ScanRequest.class);
                    if (scanRequest.isScanning()) {
                        scanRequest.onStop();
                    }
                    ((ConnectRequest) Rproxy.getRequest(ConnectRequest.class)).closeBluetooth();
                }
            }
        }
    }
}
