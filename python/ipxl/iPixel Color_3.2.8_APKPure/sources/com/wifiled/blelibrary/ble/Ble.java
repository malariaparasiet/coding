package com.wifiled.blelibrary.ble;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import com.wifiled.blelibrary.ble.callback.BleConnectCallback;
import com.wifiled.blelibrary.ble.callback.BleMtuCallback;
import com.wifiled.blelibrary.ble.callback.BleNotifyCallback;
import com.wifiled.blelibrary.ble.callback.BleReadCallback;
import com.wifiled.blelibrary.ble.callback.BleReadDescCallback;
import com.wifiled.blelibrary.ble.callback.BleReadRssiCallback;
import com.wifiled.blelibrary.ble.callback.BleScanCallback;
import com.wifiled.blelibrary.ble.callback.BleStatusCallback;
import com.wifiled.blelibrary.ble.callback.BleWriteCallback;
import com.wifiled.blelibrary.ble.callback.BleWriteDescCallback;
import com.wifiled.blelibrary.ble.callback.BleWriteEntityCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.BluetoothChangedObserver;
import com.wifiled.blelibrary.ble.exception.BleException;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.blelibrary.ble.model.EntityData;
import com.wifiled.blelibrary.ble.proxy.RequestImpl;
import com.wifiled.blelibrary.ble.proxy.RequestListener;
import com.wifiled.blelibrary.ble.proxy.RequestProxy;
import com.wifiled.blelibrary.ble.queue.RequestTask;
import com.wifiled.blelibrary.ble.queue.WriteQueue;
import com.wifiled.blelibrary.ble.queue.reconnect.DefaultReConnectHandler;
import com.wifiled.blelibrary.ble.request.ConnectRequest;
import com.wifiled.blelibrary.ble.request.DescriptorRequest;
import com.wifiled.blelibrary.ble.request.Rproxy;
import com.wifiled.blelibrary.ble.request.ScanRequest;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* loaded from: classes2.dex */
public final class Ble<T extends BleDevice> {
    public static final int REQUEST_ENABLE_BT = 1;
    private static final String TAG = "Ble";
    private static Options options;
    private static volatile Ble sInstance;
    private BluetoothChangedObserver bleObserver;
    private BleRequestImpl<T> bleRequestImpl;
    private BluetoothAdapter bluetoothAdapter;
    private Context context;
    private final Object locker = new Object();
    private RequestListener<T> request;

    public interface InitCallback {
        void failed(int i);

        void success();
    }

    private Ble() {
    }

    public static <T extends BleDevice> Ble<T> getInstance() {
        if (sInstance == null) {
            synchronized (Ble.class) {
                if (sInstance == null) {
                    sInstance = new Ble();
                }
            }
        }
        return sInstance;
    }

    public void init(Context context, Options options2, InitCallback initCallback) {
        if (context == null) {
            throw new BleException("context is null");
        }
        if (this.context != null) {
            BleLog.e(TAG, "Ble is Initialized!");
            if (initCallback != null) {
                initCallback.failed(BleStates.InitAlready);
                return;
            }
            return;
        }
        this.context = context;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.bluetoothAdapter = defaultAdapter;
        if (defaultAdapter == null) {
            if (initCallback != null) {
                BleLog.e(TAG, "bluetoothAdapter is not available!");
                initCallback.failed(BleStates.NotAvailable);
                return;
            }
            return;
        }
        if (!isSupportBle(context)) {
            if (initCallback != null) {
                BleLog.e(TAG, "not support ble!");
                initCallback.failed(BleStates.NotSupportBLE);
                return;
            }
            return;
        }
        if (options2 == null) {
            options2 = options();
        }
        options = options2;
        BleLog.init(options2);
        this.request = (RequestListener) RequestProxy.newProxy().bindProxy(context, RequestImpl.newRequestImpl());
        BleRequestImpl<T> bleRequest = BleRequestImpl.getBleRequest();
        this.bleRequestImpl = bleRequest;
        bleRequest.initialize(context);
        initBleObserver();
        BleLog.d(TAG, "Ble init success");
        if (initCallback != null) {
            initCallback.success();
        }
    }

    public static <T extends BleDevice> Ble<T> create(Context context, InitCallback initCallback) {
        return create(context, options(), initCallback);
    }

    public static <T extends BleDevice> Ble<T> create(Context context, Options options2, InitCallback initCallback) {
        Ble<T> ble = getInstance();
        ble.init(context, options2, initCallback);
        return ble;
    }

    public void setBleStatusCallback(BleStatusCallback bleStatusCallback) {
        BluetoothChangedObserver bluetoothChangedObserver = this.bleObserver;
        if (bluetoothChangedObserver != null) {
            bluetoothChangedObserver.setBleScanCallbackInner(bleStatusCallback);
        }
    }

    public void startScan(BleScanCallback<T> bleScanCallback) {
        this.request.startScan(bleScanCallback, options().scanPeriod);
    }

    public void startScan(BleScanCallback<T> bleScanCallback, long j) {
        this.request.startScan(bleScanCallback, j);
    }

    public void stopScan() {
        this.request.stopScan();
    }

    public void connect(T t, BleConnectCallback<T> bleConnectCallback) {
        synchronized (this.locker) {
            this.request.connect((RequestListener<T>) t, (BleConnectCallback<RequestListener<T>>) bleConnectCallback);
        }
    }

    public void connect(String str, BleConnectCallback<T> bleConnectCallback) {
        synchronized (this.locker) {
            this.request.connect(str, bleConnectCallback);
        }
    }

    public void connects(List<T> list, BleConnectCallback<T> bleConnectCallback) {
        ((ConnectRequest) Rproxy.getRequest(ConnectRequest.class)).connect(list, bleConnectCallback);
    }

    public void cancelConnecting(T t) {
        ((ConnectRequest) Rproxy.getRequest(ConnectRequest.class)).cancelConnecting(t);
    }

    public void cancelConnectings(List<T> list) {
        ((ConnectRequest) Rproxy.getRequest(ConnectRequest.class)).cancelConnectings(list);
    }

    public void autoConnect(T t, boolean z) {
        DefaultReConnectHandler.provideReconnectHandler().resetAutoConnect(t, z);
    }

    public void cancelAutoConnects() {
        DefaultReConnectHandler.provideReconnectHandler().cancelAutoConnect();
    }

    public void disconnect(T t) {
        this.request.disconnect(t);
    }

    public void disconnect(T t, BleConnectCallback<T> bleConnectCallback) {
        this.request.disconnect(t, bleConnectCallback);
    }

    public void disconnectAll() {
        List<T> connectedDevices = getConnectedDevices();
        if (connectedDevices.isEmpty()) {
            return;
        }
        Iterator<T> it = connectedDevices.iterator();
        while (it.hasNext()) {
            this.request.disconnect(it.next());
        }
    }

    public void startNotify(T t, BleNotifyCallback<T> bleNotifyCallback) {
        this.request.notify(t, bleNotifyCallback);
    }

    public void cancelNotify(T t, BleNotifyCallback<T> bleNotifyCallback) {
        this.request.cancelNotify(t, bleNotifyCallback);
    }

    public void enableNotify(T t, boolean z, BleNotifyCallback<T> bleNotifyCallback) {
        this.request.enableNotify(t, z, bleNotifyCallback);
    }

    public void enableNotifyByUuid(T t, boolean z, UUID uuid, UUID uuid2, BleNotifyCallback<T> bleNotifyCallback) {
        this.request.enableNotifyByUuid(t, z, uuid, uuid2, bleNotifyCallback);
    }

    public boolean read(T t, BleReadCallback<T> bleReadCallback) {
        return this.request.read(t, bleReadCallback);
    }

    public boolean readByUuid(T t, UUID uuid, UUID uuid2, BleReadCallback<T> bleReadCallback) {
        return this.request.readByUuid(t, uuid, uuid2, bleReadCallback);
    }

    public boolean readDesByUuid(T t, UUID uuid, UUID uuid2, UUID uuid3, BleReadDescCallback<T> bleReadDescCallback) {
        return ((DescriptorRequest) Rproxy.getRequest(DescriptorRequest.class)).readDes(t, uuid, uuid2, uuid3, bleReadDescCallback);
    }

    public boolean writeDesByUuid(T t, byte[] bArr, UUID uuid, UUID uuid2, UUID uuid3, BleWriteDescCallback<T> bleWriteDescCallback) {
        return ((DescriptorRequest) Rproxy.getRequest(DescriptorRequest.class)).writeDes(t, bArr, uuid, uuid2, uuid3, bleWriteDescCallback);
    }

    public boolean readRssi(T t, BleReadRssiCallback<T> bleReadRssiCallback) {
        return this.request.readRssi(t, bleReadRssiCallback);
    }

    public boolean setMTU(String str, int i, BleMtuCallback<T> bleMtuCallback) {
        return this.request.setMtu(str, i, bleMtuCallback);
    }

    public boolean write(T t, byte[] bArr, BleWriteCallback<T> bleWriteCallback) {
        return this.request.write(t, bArr, bleWriteCallback);
    }

    public boolean writeByUuid(T t, byte[] bArr, UUID uuid, UUID uuid2, BleWriteCallback<T> bleWriteCallback) {
        return this.request.writeByUuid(t, bArr, uuid, uuid2, bleWriteCallback);
    }

    public void writeQueueDelay(long j, RequestTask requestTask) {
        writeQueue(requestTask);
    }

    public void writeQueue(RequestTask requestTask) {
        WriteQueue.getInstance().put(requestTask);
    }

    public void writeEntity(T t, byte[] bArr, int i, int i2, BleWriteEntityCallback<T> bleWriteEntityCallback) {
        this.request.writeEntity(t, bArr, i, i2, bleWriteEntityCallback);
    }

    public void writeEntity(EntityData entityData, BleWriteEntityCallback<T> bleWriteEntityCallback) {
        this.request.writeEntity(entityData, bleWriteEntityCallback);
    }

    public void cancelWriteEntity() {
        this.request.cancelWriteEntity();
    }

    public BleRequestImpl getBleRequest() {
        return this.bleRequestImpl;
    }

    public T getBleDevice(String str) {
        return (T) ((ConnectRequest) Rproxy.getRequest(ConnectRequest.class)).getBleDevice(str);
    }

    public T getBleDevice(BluetoothDevice bluetoothDevice) {
        ConnectRequest connectRequest = (ConnectRequest) Rproxy.getRequest(ConnectRequest.class);
        if (bluetoothDevice != null) {
            return (T) connectRequest.getBleDevice(bluetoothDevice.getAddress());
        }
        return null;
    }

    public Object getLocker() {
        return this.locker;
    }

    public boolean isScanning() {
        return ((ScanRequest) Rproxy.getRequest(ScanRequest.class)).isScanning();
    }

    public List<T> getConnectedDevices() {
        return ((ConnectRequest) Rproxy.getRequest(ConnectRequest.class)).getConnectedDevices();
    }

    public void released() {
        releaseGatts();
        releaseBleObserver();
        if (isScanning()) {
            stopScan();
        }
        this.bleRequestImpl.release();
        this.bleRequestImpl = null;
        Rproxy.release();
        this.context = null;
        BleLog.d(TAG, "AndroidBLE already released");
    }

    private void releaseGatts() {
        BleLog.d(TAG, "BluetoothGatts is released");
        synchronized (this.locker) {
            Iterator<T> it = getConnectedDevices().iterator();
            while (it.hasNext()) {
                disconnect(it.next());
            }
        }
    }

    private void initBleObserver() {
        if (this.bleObserver == null) {
            BluetoothChangedObserver bluetoothChangedObserver = new BluetoothChangedObserver(this.context);
            this.bleObserver = bluetoothChangedObserver;
            bluetoothChangedObserver.registerReceiver();
        }
    }

    private void releaseBleObserver() {
        BleLog.d(TAG, "BleObserver is released");
        BluetoothChangedObserver bluetoothChangedObserver = this.bleObserver;
        if (bluetoothChangedObserver != null) {
            bluetoothChangedObserver.unregisterReceiver();
            this.bleObserver = null;
        }
    }

    public void cancelCallback(Object obj) {
        if (obj instanceof BleScanCallback) {
            ((ScanRequest) Rproxy.getRequest(ScanRequest.class)).cancelScanCallback();
        } else if (obj instanceof BleConnectCallback) {
            ((ConnectRequest) Rproxy.getRequest(ConnectRequest.class)).cancelConnectCallback();
        }
    }

    private BluetoothAdapter getBluetoothAdapter() {
        if (this.bluetoothAdapter == null) {
            this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        return this.bluetoothAdapter;
    }

    public boolean isSupportBle(Context context) {
        return getBluetoothAdapter() != null && context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public boolean isBleEnable() {
        BluetoothAdapter bluetoothAdapter = getBluetoothAdapter();
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    public void turnOnBlueTooth(Activity activity) {
        if (isBleEnable()) {
            return;
        }
        activity.startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 1);
    }

    public void turnOnBlueToothNo() {
        BluetoothAdapter bluetoothAdapter;
        if (isBleEnable() || (bluetoothAdapter = this.bluetoothAdapter) == null) {
            return;
        }
        bluetoothAdapter.enable();
    }

    public boolean turnOffBlueTooth() {
        if (isBleEnable()) {
            return this.bluetoothAdapter.disable();
        }
        return true;
    }

    public boolean refreshDeviceCache(String str) {
        BleRequestImpl<T> bleRequestImpl = this.bleRequestImpl;
        if (bleRequestImpl != null) {
            return bleRequestImpl.refreshDeviceCache(str);
        }
        return false;
    }

    public boolean isDeviceBusy(T t) {
        BleRequestImpl<T> bleRequestImpl = this.bleRequestImpl;
        if (bleRequestImpl != null) {
            return bleRequestImpl.isDeviceBusy(t);
        }
        return false;
    }

    public static Options options() {
        if (options == null) {
            options = new Options();
        }
        return options;
    }

    public Context getContext() {
        return this.context;
    }
}
