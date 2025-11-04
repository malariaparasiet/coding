package com.wifiled.blelibrary.ble.request;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.os.Handler;
import com.wifiled.blelibrary.ble.BleHandler;
import com.wifiled.blelibrary.ble.BleLog;
import com.wifiled.blelibrary.ble.annotation.Implement;
import com.wifiled.blelibrary.ble.exception.AdvertiserUnsupportException;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.blelibrary.ble.utils.ThreadUtils;

@Implement(AdvertiserRequest.class)
/* loaded from: classes2.dex */
public class AdvertiserRequest<T extends BleDevice> {
    private static final String TAG = "AdvertiserRequest";
    private BluetoothLeAdvertiser mAdvertiser;
    private AdvertiseData myAdvertiseData;
    private AdvertiseSettings myAdvertiseSettings;
    private Handler mHandler = BleHandler.of();
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private Runnable stopAvertiseRunnable = new Runnable() { // from class: com.wifiled.blelibrary.ble.request.AdvertiserRequest.3
        @Override // java.lang.Runnable
        public void run() {
            AdvertiserRequest.this.stopAdvertising();
        }
    };
    private AdvertiseCallback mAdvertiseCallback = new AdvertiseCallback() { // from class: com.wifiled.blelibrary.ble.request.AdvertiserRequest.4
        @Override // android.bluetooth.le.AdvertiseCallback
        public void onStartSuccess(AdvertiseSettings advertiseSettings) {
            super.onStartSuccess(advertiseSettings);
            BleLog.d(AdvertiserRequest.TAG, "onStartSuccess: 开启广播成功");
        }

        @Override // android.bluetooth.le.AdvertiseCallback
        public void onStartFailure(int i) {
            super.onStartFailure(i);
            if (i == 1) {
                BleLog.e(AdvertiserRequest.TAG, "Failed to start advertising as the advertise data to be broadcasted is larger than 31 bytes.");
                return;
            }
            if (i == 2) {
                BleLog.e(AdvertiserRequest.TAG, "Failed to start advertising because no advertising instance is available.");
                return;
            }
            if (i == 3) {
                BleLog.e(AdvertiserRequest.TAG, "Failed to start advertising as the advertising is already started");
            } else if (i == 4) {
                BleLog.e(AdvertiserRequest.TAG, "Operation failed due to an internal error");
            } else if (i == 5) {
                BleLog.e(AdvertiserRequest.TAG, "This feature is not supported on this platform");
            }
        }
    };

    private void setAdvertiserSettings() {
        BluetoothLeAdvertiser bluetoothLeAdvertiser = this.bluetoothAdapter.getBluetoothLeAdvertiser();
        this.mAdvertiser = bluetoothLeAdvertiser;
        if (bluetoothLeAdvertiser == null) {
            try {
                throw new AdvertiserUnsupportException("Device does not support Avertise!");
            } catch (AdvertiserUnsupportException e) {
                e.printStackTrace();
            }
        }
        this.myAdvertiseSettings = new AdvertiseSettings.Builder().setAdvertiseMode(2).setConnectable(true).setTimeout(0).setTxPowerLevel(3).build();
    }

    public void startAdvertising(final byte[] bArr, final AdvertiseSettings advertiseSettings) {
        if (this.bluetoothAdapter.isEnabled()) {
            this.mHandler.removeCallbacks(this.stopAvertiseRunnable);
            if (this.mAdvertiser != null) {
                ThreadUtils.asyn(new Runnable() { // from class: com.wifiled.blelibrary.ble.request.AdvertiserRequest.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AdvertiserRequest.this.mAdvertiser.stopAdvertising(AdvertiserRequest.this.mAdvertiseCallback);
                        AdvertiserRequest.this.myAdvertiseData = new AdvertiseData.Builder().addManufacturerData(65520, bArr).setIncludeDeviceName(true).build();
                        AdvertiserRequest.this.mAdvertiser.startAdvertising(advertiseSettings, AdvertiserRequest.this.myAdvertiseData, AdvertiserRequest.this.mAdvertiseCallback);
                    }
                });
            }
        }
    }

    public void startAdvertising(byte[] bArr) {
        setAdvertiserSettings();
        startAdvertising(bArr, this.myAdvertiseSettings);
    }

    public void stopAdvertising() {
        if (!this.bluetoothAdapter.isEnabled() || this.mAdvertiser == null) {
            return;
        }
        ThreadUtils.asyn(new Runnable() { // from class: com.wifiled.blelibrary.ble.request.AdvertiserRequest.2
            @Override // java.lang.Runnable
            public void run() {
                BleLog.d(AdvertiserRequest.TAG, "stopAdvertising: 停止广播");
                AdvertiserRequest.this.mAdvertiser.stopAdvertising(AdvertiserRequest.this.mAdvertiseCallback);
            }
        });
    }

    public void stopAdvertising(Long l) {
        this.mHandler.postDelayed(this.stopAvertiseRunnable, l.longValue());
    }
}
