package com.wifiled.blelibrary.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import androidx.core.os.HandlerCompat;
import com.wifiled.blelibrary.ble.callback.wrapper.ConnectWrapperCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.DescWrapperCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.MtuWrapperCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.NotifyWrapperCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.ReadRssiWrapperCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.ReadWrapperCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.WriteWrapperCallback;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.blelibrary.ble.request.ConnectRequest2;
import com.wifiled.blelibrary.ble.request.DescriptorRequest2;
import com.wifiled.blelibrary.ble.request.MtuRequest2;
import com.wifiled.blelibrary.ble.request.NotifyRequest2;
import com.wifiled.blelibrary.ble.request.ReadRequest2;
import com.wifiled.blelibrary.ble.request.ReadRssiRequest2;
import com.wifiled.blelibrary.ble.request.Rproxy2;
import com.wifiled.blelibrary.ble.request.WriteRequest2;
import com.wifiled.blelibrary.ble.utils.ByteUtils;
import com.wifiled.blelibrary.ota.OtaListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes2.dex */
public final class BleRequestImpl2<T extends BleDevice> {
    private static final String TAG = "BleRequestImpl2";
    private static BleRequestImpl2 instance;
    private BluetoothAdapter bluetoothAdapter;
    private ConnectWrapperCallback<T> connectWrapperCallback;
    private Context context;
    private DescWrapperCallback<T> descWrapperCallback;
    private MtuWrapperCallback<T> mtuWrapperCallback;
    private NotifyWrapperCallback<T> notifyWrapperCallback;
    private Options2 options;
    private OtaListener otaListener;
    private BluetoothGattCharacteristic otaWriteCharacteristic;
    private ReadRssiWrapperCallback<T> readRssiWrapperCallback;
    private ReadWrapperCallback<T> readWrapperCallback;
    private WriteWrapperCallback<T> writeWrapperCallback;
    private final Handler handler = BleHandler.of();
    private final Object locker = new Object();
    private final List<BluetoothGattCharacteristic> notifyCharacteristics = new ArrayList();
    private boolean otaUpdating = false;
    private final Map<String, BluetoothGattCharacteristic> writeCharacteristicMap = new HashMap();
    private final Map<String, BluetoothGattCharacteristic> readCharacteristicMap = new HashMap();
    private final Map<String, BluetoothGatt> gattHashMap = new HashMap();
    private final List<String> connectedAddressList = new ArrayList();
    private boolean isWriteReturn = true;
    private int notifyIndex = 0;
    private final BluetoothGattCallback gattCallback = new BluetoothGattCallback() { // from class: com.wifiled.blelibrary.ble.BleRequestImpl2.1
        static final /* synthetic */ boolean $assertionsDisabled = false;

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            BluetoothDevice device = bluetoothGatt.getDevice();
            if (device == null) {
                return;
            }
            String address = device.getAddress();
            BleRequestImpl2.this.cancelTimeout(address);
            BleDevice bleDeviceInternal = BleRequestImpl2.this.getBleDeviceInternal(address);
            if (i != 0) {
                BleLog.e(BleRequestImpl2.TAG, "onConnectionStateChange----: Connection status is abnormal:" + i);
                BleRequestImpl2.this.close(device.getAddress());
                if (BleRequestImpl2.this.connectWrapperCallback != null) {
                    BleRequestImpl2.this.connectWrapperCallback.onConnectFailed(bleDeviceInternal, BleRequestImpl2.this.getErrorCode(bleDeviceInternal));
                    return;
                }
                return;
            }
            if (i2 != 2) {
                if (i2 == 0) {
                    BleLog.d(BleRequestImpl2.TAG, "onConnectionStateChange:----device is disconnected.");
                    if (BleRequestImpl2.this.connectWrapperCallback != null) {
                        bleDeviceInternal.setConnectionState(0);
                        BleRequestImpl2.this.connectWrapperCallback.onConnectionChanged(bleDeviceInternal);
                    }
                    BleRequestImpl2.this.close(device.getAddress());
                    return;
                }
                return;
            }
            BleRequestImpl2.this.connectedAddressList.add(device.getAddress());
            if (BleRequestImpl2.this.connectWrapperCallback != null) {
                bleDeviceInternal.setConnectionState(2);
                BleRequestImpl2.this.connectWrapperCallback.onConnectionChanged(bleDeviceInternal);
            }
            BleLog.d(BleRequestImpl2.TAG, "onConnectionStateChange:----device is connected.");
            BluetoothGatt bluetoothGatt2 = BleRequestImpl2.this.getBluetoothGatt(device.getAddress());
            if (bluetoothGatt2 != null) {
                BleLog.d(BleRequestImpl2.TAG, "trying to start service discovery");
                bluetoothGatt2.discoverServices();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (bluetoothGatt == null || bluetoothGatt.getDevice() == null) {
                return;
            }
            BleLog.d(BleRequestImpl2.TAG, "onMtuChanged mtu=" + i + ",status=" + i2);
            if (BleRequestImpl2.this.mtuWrapperCallback != null) {
                BleRequestImpl2.this.mtuWrapperCallback.onMtuChanged(BleRequestImpl2.this.getBleDeviceInternal(bluetoothGatt.getDevice().getAddress()), i, i2);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            if (i == 0) {
                BleRequestImpl2.this.notifyCharacteristics.clear();
                BleRequestImpl2.this.notifyIndex = 0;
                BleRequestImpl2.this.displayGattServices(bluetoothGatt);
                return;
            }
            BleLog.e(BleRequestImpl2.TAG, "onServicesDiscovered received: " + i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (bluetoothGatt == null || bluetoothGatt.getDevice() == null) {
                return;
            }
            BleLog.d(BleRequestImpl2.TAG, "onCharacteristicRead:" + i);
            BleDevice bleDeviceInternal = BleRequestImpl2.this.getBleDeviceInternal(bluetoothGatt.getDevice().getAddress());
            if (i == 0) {
                if (BleRequestImpl2.this.readWrapperCallback != null) {
                    BleRequestImpl2.this.readWrapperCallback.onReadSuccess(bleDeviceInternal, bluetoothGattCharacteristic);
                }
            } else if (BleRequestImpl2.this.readWrapperCallback != null) {
                BleRequestImpl2.this.readWrapperCallback.onReadFailed(bleDeviceInternal, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (bluetoothGatt == null || bluetoothGatt.getDevice() == null) {
                return;
            }
            BleLog.d(BleRequestImpl2.TAG, bluetoothGatt.getDevice().getAddress() + "-----write success----- status: " + i);
            synchronized (BleRequestImpl2.this.locker) {
                BleDevice bleDeviceInternal = BleRequestImpl2.this.getBleDeviceInternal(bluetoothGatt.getDevice().getAddress());
                if (i == 0) {
                    BleLog.d(BleRequestImpl2.TAG, "notifyWrapperCallback： " + BleRequestImpl2.this.notifyWrapperCallback);
                    if (BleRequestImpl2.this.notifyWrapperCallback != null) {
                        BleRequestImpl2.this.notifyWrapperCallback.onWriteSuccess(bluetoothGatt.getDevice(), bluetoothGattCharacteristic, i);
                    }
                    if (BleRequestImpl2.this.writeWrapperCallback != null) {
                        BleRequestImpl2.this.writeWrapperCallback.onWriteSuccess(bleDeviceInternal, bluetoothGattCharacteristic);
                    }
                    if (BleRequestImpl2.this.options.uuid_ota_write_cha.equals(bluetoothGattCharacteristic.getUuid()) && BleRequestImpl2.this.otaListener != null) {
                        BleRequestImpl2.this.otaListener.onWrite();
                    }
                } else if (BleRequestImpl2.this.writeWrapperCallback != null) {
                    BleRequestImpl2.this.writeWrapperCallback.onWriteFailed(bleDeviceInternal, i);
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            synchronized (BleRequestImpl2.this.locker) {
                if (bluetoothGatt != null) {
                    if (bluetoothGatt.getDevice() != null) {
                        BleLog.d(BleRequestImpl2.TAG, bluetoothGatt.getDevice().getAddress() + " -- onCharacteristicChanged: " + (bluetoothGattCharacteristic.getValue() != null ? ByteUtils.toHexString(bluetoothGattCharacteristic.getValue()) : ""));
                        BleDevice bleDeviceInternal = BleRequestImpl2.this.getBleDeviceInternal(bluetoothGatt.getDevice().getAddress());
                        if (BleRequestImpl2.this.notifyWrapperCallback != null) {
                            BleRequestImpl2.this.notifyWrapperCallback.onChanged(bleDeviceInternal, bluetoothGattCharacteristic);
                        }
                        if ((BleRequestImpl2.this.options.uuid_ota_write_cha.equals(bluetoothGattCharacteristic.getUuid()) || BleRequestImpl2.this.options.uuid_ota_notify_cha.equals(bluetoothGattCharacteristic.getUuid())) && BleRequestImpl2.this.otaListener != null) {
                            BleRequestImpl2.this.otaListener.onChange(bluetoothGattCharacteristic.getValue());
                        }
                    }
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            Log.d(BleRequestImpl2.TAG, "onDescriptorWrite====================" + i);
            if (bluetoothGatt == null || bluetoothGatt.getDevice() == null) {
                return;
            }
            BleLog.d(BleRequestImpl2.TAG, "write descriptor uuid:" + bluetoothGattDescriptor.getCharacteristic().getUuid() + "   ->status[" + i + "]");
            BleDevice bleDeviceInternal = BleRequestImpl2.this.getBleDeviceInternal(bluetoothGatt.getDevice().getAddress());
            if (i == 0) {
                if (BleRequestImpl2.this.notifyCharacteristics.size() <= 0 || BleRequestImpl2.this.notifyIndex >= BleRequestImpl2.this.notifyCharacteristics.size()) {
                    Log.d(BleRequestImpl2.TAG, "onDescriptorWrite====setCharacteristicNotification is completed===");
                    if (BleRequestImpl2.this.notifyWrapperCallback != null) {
                        if (Arrays.equals(bluetoothGattDescriptor.getValue(), BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE) || Arrays.equals(bluetoothGattDescriptor.getValue(), BluetoothGattDescriptor.ENABLE_INDICATION_VALUE)) {
                            Log.d(BleRequestImpl2.TAG, "onDescriptorWrite onNotifySuccess===gatt.getDevice():" + bluetoothGatt.getDevice().getName());
                            BleRequestImpl2.this.notifyWrapperCallback.onNotifySuccess(bleDeviceInternal);
                            return;
                        } else {
                            if (Arrays.equals(bluetoothGattDescriptor.getValue(), BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE)) {
                                BleRequestImpl2.this.notifyWrapperCallback.onNotifyCanceled(bleDeviceInternal);
                                return;
                            }
                            return;
                        }
                    }
                    return;
                }
                Log.d(BleRequestImpl2.TAG, "onDescriptorWrite2:====================" + BleRequestImpl2.this.notifyCharacteristics.size() + "> notifyIndex" + BleRequestImpl2.this.notifyIndex);
                BleRequestImpl2.this.setCharacteristicNotification(bluetoothGatt.getDevice().getAddress(), true);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
            if (bluetoothGatt == null || bluetoothGatt.getDevice() == null) {
                return;
            }
            BleLog.d(BleRequestImpl2.TAG, "read descriptor uuid:" + bluetoothGattDescriptor.getCharacteristic().getUuid());
            BleDevice bleDeviceInternal = BleRequestImpl2.this.getBleDeviceInternal(bluetoothGatt.getDevice().getAddress());
            if (i == 0) {
                if (BleRequestImpl2.this.descWrapperCallback != null) {
                    BleRequestImpl2.this.descWrapperCallback.onDescReadSuccess(bleDeviceInternal, bluetoothGattDescriptor);
                }
            } else if (BleRequestImpl2.this.descWrapperCallback != null) {
                BleRequestImpl2.this.descWrapperCallback.onDescReadFailed(bleDeviceInternal, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            BleLog.d(BleRequestImpl2.TAG, "read remoteRssi, rssi: " + i);
            if (bluetoothGatt == null || bluetoothGatt.getDevice() == null || BleRequestImpl2.this.readRssiWrapperCallback == null) {
                return;
            }
            BleRequestImpl2.this.readRssiWrapperCallback.onReadRssiSuccess(BleRequestImpl2.this.getBleDeviceInternal(bluetoothGatt.getDevice().getAddress()), i);
        }
    };

    private BleRequestImpl2() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getErrorCode(T t) {
        return t.isConnected() ? BleStates.ConnectException : t.isConnecting() ? BleStates.ConnectFailed : BleStates.ConnectError;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public T getBleDeviceInternal(String str) {
        return (T) ((ConnectRequest2) Rproxy2.getRequest(ConnectRequest2.class)).getBleDevice(str);
    }

    public List<BluetoothDevice> getConnectedDevices() {
        BluetoothManager bluetoothManager = (BluetoothManager) this.context.getSystemService("bluetooth");
        if (bluetoothManager != null) {
            return bluetoothManager.getConnectedDevices(7);
        }
        return null;
    }

    public static <T extends BleDevice> BleRequestImpl2<T> getBleRequest() {
        if (instance == null) {
            instance = new BleRequestImpl2();
        }
        return instance;
    }

    void initialize(Context context) {
        this.connectWrapperCallback = (ConnectWrapperCallback) Rproxy2.getRequest(ConnectRequest2.class);
        this.notifyWrapperCallback = (NotifyWrapperCallback) Rproxy2.getRequest(NotifyRequest2.class);
        this.mtuWrapperCallback = (MtuWrapperCallback) Rproxy2.getRequest(MtuRequest2.class);
        this.readWrapperCallback = (ReadWrapperCallback) Rproxy2.getRequest(ReadRequest2.class);
        this.readRssiWrapperCallback = (ReadRssiWrapperCallback) Rproxy2.getRequest(ReadRssiRequest2.class);
        this.writeWrapperCallback = (WriteWrapperCallback) Rproxy2.getRequest(WriteRequest2.class);
        this.descWrapperCallback = (DescWrapperCallback) Rproxy2.getRequest(DescriptorRequest2.class);
        this.context = context;
        this.options = Ble2.options();
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    void release() {
        this.connectWrapperCallback = null;
        this.mtuWrapperCallback = null;
        this.notifyWrapperCallback = null;
        this.readRssiWrapperCallback = null;
        this.readWrapperCallback = null;
        this.writeWrapperCallback = null;
        this.handler.removeCallbacksAndMessages(null);
        BleLog.d(TAG, "BleRequestImpl is released");
    }

    public void cancelTimeout(String str) {
        this.handler.removeCallbacksAndMessages(str);
    }

    public boolean connect(final T t) {
        BluetoothGatt connectGatt;
        String bleAddress = t.getBleAddress();
        if (this.connectedAddressList.contains(t.getBleAddress()) && t.isConnected()) {
            BleLog.e(TAG, "this is device already connected.");
            this.connectWrapperCallback.onConnectFailed(t, BleStates.ConnectedAlready);
            return false;
        }
        if (this.bluetoothAdapter == null) {
            BleLog.e(TAG, "bluetoothAdapter not available");
            this.connectWrapperCallback.onConnectFailed(t, BleStates.NotAvailable);
            return false;
        }
        if (!BluetoothAdapter.checkBluetoothAddress(bleAddress)) {
            BleLog.e(TAG, "the device address is invalid");
            this.connectWrapperCallback.onConnectFailed(t, BleStates.InvalidAddress);
            return false;
        }
        final BluetoothDevice remoteDevice = this.bluetoothAdapter.getRemoteDevice(bleAddress);
        if (remoteDevice == null) {
            BleLog.e(TAG, "no device");
            this.connectWrapperCallback.onConnectFailed(t, BleStates.DeviceNull);
            return false;
        }
        HandlerCompat.postDelayed(this.handler, new Runnable() { // from class: com.wifiled.blelibrary.ble.BleRequestImpl2.2
            @Override // java.lang.Runnable
            public void run() {
                BleRequestImpl2.this.connectWrapperCallback.onConnectFailed(t, BleStates.ConnectTimeOut);
                BleRequestImpl2.this.close(remoteDevice.getAddress());
            }
        }, remoteDevice.getAddress(), this.options.connectTimeout);
        t.setConnectionState(1);
        t.setBleName(remoteDevice.getName());
        this.connectWrapperCallback.onConnectionChanged(t);
        if (remoteDevice.getType() == 3) {
            connectGatt = remoteDevice.connectGatt(this.context, false, this.gattCallback, 2);
        } else {
            connectGatt = remoteDevice.connectGatt(this.context, false, this.gattCallback);
        }
        if (connectGatt == null) {
            return false;
        }
        this.gattHashMap.put(bleAddress, connectGatt);
        BleLog.d(TAG, "Trying to create a new connection.");
        return true;
    }

    public void disconnect(String str) {
        BluetoothGatt bluetoothGatt = getBluetoothGatt(str);
        if (bluetoothGatt != null) {
            bluetoothGatt.disconnect();
        }
        this.notifyIndex = 0;
        this.notifyCharacteristics.clear();
        this.writeCharacteristicMap.remove(str);
        this.readCharacteristicMap.remove(str);
        this.otaWriteCharacteristic = null;
    }

    public void close(String str) {
        BluetoothGatt bluetoothGatt = getBluetoothGatt(str);
        if (bluetoothGatt != null) {
            bluetoothGatt.close();
            this.gattHashMap.remove(str);
        }
        this.connectedAddressList.remove(str);
    }

    public boolean setMtu(String str, int i) {
        BluetoothGatt bluetoothGatt;
        if (i <= 20 || (bluetoothGatt = getBluetoothGatt(str)) == null) {
            return false;
        }
        boolean requestMtu = bluetoothGatt.requestMtu(i);
        BleLog.d(TAG, "requestMTU " + i + " result=" + requestMtu);
        return requestMtu;
    }

    public void close() {
        Iterator<String> it = this.connectedAddressList.iterator();
        while (it.hasNext()) {
            BluetoothGatt bluetoothGatt = getBluetoothGatt(it.next());
            if (bluetoothGatt != null) {
                bluetoothGatt.close();
            }
        }
        this.gattHashMap.clear();
        this.connectedAddressList.clear();
    }

    public boolean refreshDeviceCache(String str) {
        BluetoothGatt bluetoothGatt = getBluetoothGatt(str);
        if (bluetoothGatt != null) {
            try {
                Method method = bluetoothGatt.getClass().getMethod("refresh", new Class[0]);
                if (method != null) {
                    return ((Boolean) method.invoke(bluetoothGatt, new Object[0])).booleanValue();
                }
            } catch (Exception unused) {
                BleLog.e(TAG, "An exception occured while refreshing device");
            }
        }
        return false;
    }

    public boolean isDeviceBusy(T t) {
        boolean z = false;
        try {
            BluetoothGatt bluetoothGatt = getBluetoothGatt(t.getBleAddress());
            if (bluetoothGatt == null) {
                return false;
            }
            Field declaredField = bluetoothGatt.getClass().getDeclaredField("mDeviceBusy");
            declaredField.setAccessible(true);
            z = ((Boolean) declaredField.get(bluetoothGatt)).booleanValue();
            BleLog.i(TAG, "isDeviceBusy state:" + z);
            return z;
        } catch (IllegalAccessException | NoSuchFieldException unused) {
            return z;
        }
    }

    public boolean writeCharacteristic(String str, byte[] bArr) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.writeCharacteristicMap.get(str);
        if (bluetoothGattCharacteristic != null) {
            if (!this.options.uuid_write_cha.equals(bluetoothGattCharacteristic.getUuid())) {
                return false;
            }
            bluetoothGattCharacteristic.setValue(bArr);
            boolean writeCharacteristic = getBluetoothGatt(str).writeCharacteristic(bluetoothGattCharacteristic);
            BleLog.d(TAG, str + " -- write result:" + writeCharacteristic);
            return writeCharacteristic;
        }
        WriteWrapperCallback<T> writeWrapperCallback = this.writeWrapperCallback;
        if (writeWrapperCallback == null) {
            return false;
        }
        writeWrapperCallback.onWriteFailed(getBleDeviceInternal(str), BleStates.NotInitUuid);
        return false;
    }

    public boolean writeCharacteristicByUuid(String str, byte[] bArr, UUID uuid, UUID uuid2) {
        BluetoothGatt bluetoothGatt = getBluetoothGatt(str);
        BluetoothGattCharacteristic gattCharacteristic = gattCharacteristic(bluetoothGatt, uuid, uuid2);
        if (gattCharacteristic == null) {
            return false;
        }
        gattCharacteristic.setValue(bArr);
        boolean writeCharacteristic = bluetoothGatt.writeCharacteristic(gattCharacteristic);
        BleLog.d(TAG, str + " -- write result:" + writeCharacteristic);
        return writeCharacteristic;
    }

    public BluetoothGattCharacteristic gattCharacteristic(BluetoothGatt bluetoothGatt, UUID uuid, UUID uuid2) {
        if (bluetoothGatt == null) {
            BleLog.e(TAG, "BluetoothGatt is null");
            return null;
        }
        BluetoothGattService service = bluetoothGatt.getService(uuid);
        if (service == null) {
            BleLog.e(TAG, "serviceUUID is null");
            return null;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuid2);
        if (characteristic != null) {
            return characteristic;
        }
        BleLog.e(TAG, "characteristicUUID is null");
        return null;
    }

    public boolean readCharacteristic(String str) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.readCharacteristicMap.get(str);
        if (bluetoothGattCharacteristic != null) {
            if (!this.options.uuid_read_cha.equals(bluetoothGattCharacteristic.getUuid())) {
                return false;
            }
            boolean readCharacteristic = getBluetoothGatt(str).readCharacteristic(bluetoothGattCharacteristic);
            BleLog.d(TAG, "read result:" + readCharacteristic);
            return readCharacteristic;
        }
        ReadWrapperCallback<T> readWrapperCallback = this.readWrapperCallback;
        if (readWrapperCallback == null) {
            return false;
        }
        readWrapperCallback.onReadFailed(getBleDeviceInternal(str), BleStates.NotInitUuid);
        return false;
    }

    public boolean readCharacteristicByUuid(String str, UUID uuid, UUID uuid2) {
        BluetoothGatt bluetoothGatt = getBluetoothGatt(str);
        BluetoothGattCharacteristic gattCharacteristic = gattCharacteristic(bluetoothGatt, uuid, uuid2);
        if (gattCharacteristic == null) {
            return false;
        }
        boolean readCharacteristic = bluetoothGatt.readCharacteristic(gattCharacteristic);
        BleLog.d(TAG, str + " -- read result:" + readCharacteristic);
        return readCharacteristic;
    }

    public boolean readDescriptor(String str, UUID uuid, UUID uuid2, UUID uuid3) {
        BluetoothGattDescriptor descriptor;
        BluetoothGatt bluetoothGatt = getBluetoothGatt(str);
        BluetoothGattCharacteristic gattCharacteristic = gattCharacteristic(bluetoothGatt, uuid, uuid2);
        if (gattCharacteristic == null || (descriptor = gattCharacteristic.getDescriptor(uuid3)) == null) {
            return false;
        }
        return bluetoothGatt.readDescriptor(descriptor);
    }

    public boolean writeDescriptor(String str, byte[] bArr, UUID uuid, UUID uuid2, UUID uuid3) {
        BluetoothGattDescriptor descriptor;
        Log.v("ruis", "writeDescriptor+++++++++++++++++");
        BluetoothGatt bluetoothGatt = getBluetoothGatt(str);
        BluetoothGattCharacteristic gattCharacteristic = gattCharacteristic(bluetoothGatt, uuid, uuid2);
        if (gattCharacteristic == null || (descriptor = gattCharacteristic.getDescriptor(uuid3)) == null) {
            return false;
        }
        descriptor.setValue(bArr);
        return bluetoothGatt.writeDescriptor(descriptor);
    }

    public boolean readRssi(String str) {
        boolean readRemoteRssi = getBluetoothGatt(str).readRemoteRssi();
        BleLog.d(TAG, str + "read result:" + readRemoteRssi);
        return readRemoteRssi;
    }

    public void setCharacteristicNotification(String str, boolean z) {
        Log.d("akon", "setCharacteristicNotification：size" + this.notifyCharacteristics.size() + "notifyIndex=" + this.notifyIndex);
        if (this.notifyCharacteristics.size() <= 0 || this.notifyIndex >= this.notifyCharacteristics.size()) {
            return;
        }
        List<BluetoothGattCharacteristic> list = this.notifyCharacteristics;
        int i = this.notifyIndex;
        this.notifyIndex = i + 1;
        BluetoothGattCharacteristic bluetoothGattCharacteristic = list.get(i);
        String str2 = TAG;
        Log.i(str2, "启用或禁用给定特征的通知:" + bluetoothGattCharacteristic.getUuid().toString() + " serID:" + bluetoothGattCharacteristic.getService().getUuid() + " notifyCharacteristics.size:" + this.notifyCharacteristics.size());
        String uuid = bluetoothGattCharacteristic.getService().getUuid().toString();
        if (uuid.equals(this.options.uuid_service.toString()) || uuid.equals(this.options.uuid_jlota_service.toString())) {
            Log.d(str2, "开启始设置Notification:" + uuid);
            setCharacteristicNotificationInternal(this.gattHashMap.get(str), bluetoothGattCharacteristic, z);
        }
    }

    private void setCharacteristicNotificationInternal(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        if (bluetoothGattCharacteristic == null) {
            BleLog.e(TAG, "characteristic is null");
            NotifyWrapperCallback<T> notifyWrapperCallback = this.notifyWrapperCallback;
            if (notifyWrapperCallback != null) {
                notifyWrapperCallback.onNotifyFailed(getBleDeviceInternal(bluetoothGatt.getDevice().getAddress()), BleStates.CharaUuidNull);
                return;
            }
            return;
        }
        Log.i(TAG, "设置通知返回结果：" + bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z) + " characteristic:" + bluetoothGattCharacteristic.getUuid());
        if (bluetoothGattCharacteristic.getDescriptors().size() > 0) {
            for (BluetoothGattDescriptor bluetoothGattDescriptor : bluetoothGattCharacteristic.getDescriptors()) {
                if (bluetoothGattDescriptor != null && this.options.uuid_notify_desc.equals(bluetoothGattDescriptor.getUuid())) {
                    if ((bluetoothGattCharacteristic.getProperties() & 16) != 0) {
                        Log.d(TAG, "setCharacteristicNotificationInternal 111 enabled----   " + z);
                        bluetoothGattDescriptor.setValue(z ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                    } else if ((bluetoothGattCharacteristic.getProperties() & 32) != 0) {
                        Log.d(TAG, "setCharacteristicNotificationInternal 222 enabled----   " + z);
                        bluetoothGattDescriptor.setValue(z ? BluetoothGattDescriptor.ENABLE_INDICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                    }
                    bluetoothGatt.writeDescriptor(bluetoothGattDescriptor);
                    String str = TAG;
                    BleLog.d(str, "setCharacteristicNotificationInternal writeDescriptor ret:     descriptor:" + bluetoothGattDescriptor.getUuid());
                    BleLog.d(str, "setCharacteristicNotificationInternal writeDescriptor ret:     uuid:" + bluetoothGattCharacteristic.getUuid());
                }
            }
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "将设备状态改为已连接111");
        }
    }

    public void setCharacteristicNotificationByUuid(String str, boolean z, UUID uuid, UUID uuid2) {
        BluetoothGatt bluetoothGatt = getBluetoothGatt(str);
        setCharacteristicNotificationInternal(bluetoothGatt, gattCharacteristic(bluetoothGatt, uuid, uuid2), z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayGattServices(BluetoothGatt bluetoothGatt) {
        BluetoothDevice device = bluetoothGatt.getDevice();
        List<BluetoothGattService> services = bluetoothGatt.getServices();
        if (services == null || device == null) {
            BleLog.e(TAG, "displayGattServices gattServices or device is null");
            if (device != null) {
                close(device.getAddress());
                return;
            }
            return;
        }
        if (services.isEmpty()) {
            BleLog.e(TAG, "displayGattServices gattServices size is 0");
            disconnect(device.getAddress());
            return;
        }
        if (this.connectWrapperCallback != null) {
            this.connectWrapperCallback.onServicesDiscovered(getBleDeviceInternal(device.getAddress()), bluetoothGatt);
        }
        for (BluetoothGattService bluetoothGattService : services) {
            String uuid = bluetoothGattService.getUuid().toString();
            String str = TAG;
            BleLog.d(str, "discovered gattServices: " + uuid);
            BleLog.d(str, "options.uuid_service.toString(): " + this.options.uuid_service.toString());
            BleLog.d(str, "options.uuid_ota_service.toString(): " + this.options.uuid_ota_service.toString());
            if (uuid.equals(this.options.uuid_service.toString()) || uuid.equals(this.options.uuid_ota_service.toString()) || uuid.equals(this.options.uuid_jlota_service.toString())) {
                BleLog.i(str, "service_uuid is set up successfully:" + uuid + "options.uuid:" + this.options.uuid_service.toString());
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                    String uuid2 = bluetoothGattCharacteristic.getUuid().toString();
                    String str2 = TAG;
                    BleLog.d(str2, "characteristic_uuid: " + uuid2);
                    int properties = bluetoothGattCharacteristic.getProperties();
                    StringBuilder sb = new StringBuilder();
                    if ((properties & 8) != 0) {
                        sb.append("write,");
                    }
                    if ((properties & 4) != 0) {
                        sb.append("write_no_response,");
                    }
                    if ((properties & 2) != 0) {
                        sb.append("read,");
                    }
                    if ((bluetoothGattCharacteristic.getProperties() & 16) != 0) {
                        this.notifyCharacteristics.add(bluetoothGattCharacteristic);
                        sb.append("notify,");
                    }
                    if ((bluetoothGattCharacteristic.getProperties() & 32) != 0) {
                        this.notifyCharacteristics.add(bluetoothGattCharacteristic);
                        sb.append("indicate,");
                    }
                    int length = sb.length();
                    if (length > 0) {
                        sb.deleteCharAt(length - 1);
                        BleLog.d(str2, sb.insert(0, "characteristic properties is ").toString());
                    }
                    if (uuid2.equals(this.options.uuid_write_cha.toString())) {
                        BleLog.i(str2, "write characteristic set up successfully:" + uuid2);
                        this.writeCharacteristicMap.put(device.getAddress(), bluetoothGattCharacteristic);
                    }
                    if (uuid2.equals(this.options.uuid_read_cha.toString())) {
                        BleLog.i(str2, "read characteristic set up successfully:" + uuid2);
                        this.readCharacteristicMap.put(device.getAddress(), bluetoothGattCharacteristic);
                    }
                }
            }
        }
        ConnectWrapperCallback<T> connectWrapperCallback = this.connectWrapperCallback;
        if (connectWrapperCallback != null) {
            connectWrapperCallback.onReady(getBleDeviceInternal(device.getAddress()));
        }
    }

    private boolean isContainUUID(String str) {
        for (UUID uuid : this.options.uuid_services_extra) {
            if (uuid != null && str.equals(uuid.toString())) {
                return true;
            }
        }
        return false;
    }

    public BluetoothGattCharacteristic getWriteCharacteristic(String str) {
        synchronized (this.locker) {
            Map<String, BluetoothGattCharacteristic> map = this.writeCharacteristicMap;
            if (map == null) {
                return null;
            }
            return map.get(str);
        }
    }

    public BluetoothGattCharacteristic getReadCharacteristic(String str) {
        synchronized (this.locker) {
            Map<String, BluetoothGattCharacteristic> map = this.readCharacteristicMap;
            if (map == null) {
                return null;
            }
            return map.get(str);
        }
    }

    public List<BluetoothGattService> getSupportedGattServices(String str) {
        BluetoothGatt bluetoothGatt = getBluetoothGatt(str);
        if (bluetoothGatt == null) {
            return null;
        }
        return bluetoothGatt.getServices();
    }

    public BluetoothGatt getBluetoothGatt(String str) {
        return this.gattHashMap.get(str);
    }

    public boolean writeOtaData(String str, byte[] bArr) {
        try {
            if (this.otaWriteCharacteristic == null) {
                this.otaUpdating = true;
                BluetoothGattService service = getBluetoothGatt(str).getService(this.options.uuid_ota_service);
                if (service == null) {
                    return false;
                }
                BluetoothGattCharacteristic characteristic = service.getCharacteristic(this.options.uuid_ota_notify_cha);
                if (characteristic != null) {
                    getBluetoothGatt(str).setCharacteristicNotification(characteristic, true);
                }
                this.otaWriteCharacteristic = service.getCharacteristic(this.options.uuid_ota_write_cha);
            }
            if (this.otaWriteCharacteristic == null || !this.options.uuid_ota_write_cha.equals(this.otaWriteCharacteristic.getUuid())) {
                return true;
            }
            this.otaWriteCharacteristic.setValue(bArr);
            boolean writeCharacteristic = writeCharacteristic(getBluetoothGatt(str), this.otaWriteCharacteristic);
            String str2 = TAG;
            BleLog.d(str2, str + " -- write data:" + Arrays.toString(bArr));
            BleLog.d(str2, str + " -- write result:" + writeCharacteristic);
            return writeCharacteristic;
        } catch (Exception unused) {
            close();
            return false;
        }
    }

    private boolean writeCharacteristic(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        boolean z;
        synchronized (this.locker) {
            if (bluetoothGatt != null && bluetoothGattCharacteristic != null) {
                z = bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
            }
        }
        return z;
    }

    public void otaUpdateComplete() {
        this.otaUpdating = false;
    }

    public void setOtaUpdating(boolean z) {
        this.otaUpdating = z;
    }

    public void setOtaListener(OtaListener otaListener) {
        this.otaListener = otaListener;
    }
}
