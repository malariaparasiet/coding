package com.wifiled.blelibrary.ble.request;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.text.TextUtils;
import android.util.Log;
import com.wifiled.blelibrary.ble.Ble;
import com.wifiled.blelibrary.ble.BleLog;
import com.wifiled.blelibrary.ble.BleRequestImpl2;
import com.wifiled.blelibrary.ble.BleStates;
import com.wifiled.blelibrary.ble.annotation.Implement;
import com.wifiled.blelibrary.ble.callback.BleConnectCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.BleWrapperCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.ConnectWrapperCallback;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.blelibrary.ble.model.BleFactory;
import com.wifiled.blelibrary.ble.queue.reconnect.DefaultReConnectHandler;
import com.wifiled.blelibrary.ble.queue.retry.RetryDispatcher;
import com.wifiled.blelibrary.ble.request.BleConnectsDispatcher;
import com.wifiled.blelibrary.ble.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Implement(ConnectRequest2.class)
/* loaded from: classes2.dex */
public class ConnectRequest2<T extends BleDevice> implements ConnectWrapperCallback<T> {
    private static final String TAG = "ConnectRequest";
    private BleConnectCallback<T> connectCallback;
    private final Map<String, T> devices = new HashMap();
    private final Map<String, T> connectedDevices = new ConcurrentHashMap();
    private final BleConnectsDispatcher<T> dispatcher = new BleConnectsDispatcher<>();
    private final BleRequestImpl2<T> bleRequest = BleRequestImpl2.getBleRequest();
    private final List<BleConnectCallback<T>> connectInnerCallbacks = new ArrayList();
    private final BleWrapperCallback<T> bleWrapperCallback = Ble.options().getBleWrapperCallback();

    protected ConnectRequest2() {
        addConnectHandlerCallbacks(DefaultReConnectHandler.provideReconnectHandler());
        addConnectHandlerCallbacks(RetryDispatcher.getInstance());
    }

    public boolean connect(T t) {
        return connect((ConnectRequest2<T>) t, (BleConnectCallback<ConnectRequest2<T>>) this.connectCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized boolean connect(T t, BleConnectCallback<T> bleConnectCallback) {
        this.connectCallback = bleConnectCallback;
        if (t == null) {
            doConnectException(null, BleStates.DeviceNull);
            return false;
        }
        if (t.isConnecting()) {
            return false;
        }
        if (!Ble.getInstance().isBleEnable()) {
            doConnectException(t, BleStates.BluetoothNotOpen);
            return false;
        }
        if (this.connectedDevices.size() >= Ble.options().getMaxConnectNum()) {
            BleLog.e(TAG, "Maximum number of connections Exception");
            doConnectException(t, BleStates.MaxConnectNumException);
            return false;
        }
        t.setAutoConnect(Ble.options().autoConnect);
        addBleToPool(t);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            Log.e(TAG, "BluetoothAdapter not initialized");
            return false;
        }
        return this.bleRequest.connect(BleFactory.create(defaultAdapter.getRemoteDevice(t.getBleAddress())));
    }

    private void doConnectException(final T t, final int i) {
        runOnUiThread(new Runnable() { // from class: com.wifiled.blelibrary.ble.request.ConnectRequest2.1
            @Override // java.lang.Runnable
            public void run() {
                if (ConnectRequest2.this.connectCallback != null) {
                    ConnectRequest2.this.connectCallback.onConnectFailed(t, i);
                }
            }
        });
        Iterator<BleConnectCallback<T>> it = this.connectInnerCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onConnectFailed(t, i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean connect(String str, BleConnectCallback<T> bleConnectCallback) {
        return connect((ConnectRequest2<T>) Ble.options().getFactory().create(str, ""), (BleConnectCallback<ConnectRequest2<T>>) bleConnectCallback);
    }

    public void connect(List<T> list, final BleConnectCallback<T> bleConnectCallback) {
        this.dispatcher.excute(list, new BleConnectsDispatcher.NextCallback<T>() { // from class: com.wifiled.blelibrary.ble.request.ConnectRequest2.2
            @Override // com.wifiled.blelibrary.ble.request.BleConnectsDispatcher.NextCallback
            public void onNext(T t) {
                ConnectRequest2.this.connect((ConnectRequest2) t, (BleConnectCallback<ConnectRequest2>) bleConnectCallback);
            }
        });
    }

    public void cancelConnecting(T t) {
        boolean isConnecting = t.isConnecting();
        boolean isContains = this.dispatcher.isContains(t);
        if (isConnecting || isContains) {
            if (this.connectCallback != null) {
                BleLog.d(TAG, "cancel connecting device：" + t.getBleName());
                this.connectCallback.onConnectCancel(t);
            }
            if (isConnecting) {
                disconnect(t);
                this.bleRequest.cancelTimeout(t.getBleAddress());
                t.setConnectionState(0);
                onConnectionChanged((ConnectRequest2<T>) t);
            }
            if (isContains) {
                this.dispatcher.cancelOne(t);
            }
        }
    }

    public void cancelConnectings(List<T> list) {
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            cancelConnecting(it.next());
        }
    }

    public void disconnect(String str) {
        if (this.connectedDevices.containsKey(str)) {
            disconnect(this.connectedDevices.get(str));
        }
    }

    public void disconnect(BleDevice bleDevice) {
        disconnect(bleDevice, this.connectCallback);
    }

    public void disconnect(BleDevice bleDevice, BleConnectCallback<T> bleConnectCallback) {
        if (bleDevice != null) {
            this.connectCallback = bleConnectCallback;
            bleDevice.setAutoConnect(false);
            this.bleRequest.disconnect(bleDevice.getBleAddress());
        }
    }

    public void closeBluetooth() {
        if (this.connectedDevices.isEmpty()) {
            return;
        }
        for (T t : this.connectedDevices.values()) {
            if (this.connectCallback != null) {
                t.setConnectionState(0);
                BleLog.e(TAG, "System Bluetooth is disconnected>>>> " + t.getBleName());
                this.connectCallback.onConnectionChanged(t);
            }
        }
        this.bleRequest.close();
        this.connectedDevices.clear();
        this.devices.clear();
    }

    private void runOnUiThread(Runnable runnable) {
        ThreadUtils.ui(runnable);
    }

    void addConnectHandlerCallbacks(BleConnectCallback<T> bleConnectCallback) {
        this.connectInnerCallbacks.add(bleConnectCallback);
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ConnectWrapperCallback
    public void onConnectionChanged(final T t) {
        if (t == null) {
            return;
        }
        if (t.isConnected()) {
            this.connectedDevices.put(t.getBleAddress(), t);
            BleLog.d(TAG, "connected>>>> " + t.getBleName());
        } else if (t.isDisconnected()) {
            this.connectedDevices.remove(t.getBleAddress());
            this.devices.remove(t.getBleAddress());
            BleLog.d(TAG, "disconnected>>>> " + t.getBleName());
        }
        runOnUiThread(new Runnable() { // from class: com.wifiled.blelibrary.ble.request.ConnectRequest2.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                if (ConnectRequest2.this.connectCallback != null) {
                    ConnectRequest2.this.connectCallback.onConnectionChanged(t);
                }
                if (ConnectRequest2.this.bleWrapperCallback != null) {
                    ConnectRequest2.this.bleWrapperCallback.onConnectionChanged((BleWrapperCallback) t);
                }
            }
        });
        Iterator<BleConnectCallback<T>> it = this.connectInnerCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onConnectionChanged(t);
        }
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ConnectWrapperCallback
    public void onConnectFailed(T t, int i) {
        if (t == null) {
            return;
        }
        BleLog.e(TAG, "onConnectFailed>>>> " + t.getBleName() + "\n异常码:" + i);
        t.setConnectionState(0);
        onConnectionChanged((ConnectRequest2<T>) t);
        doConnectException(t, i);
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ConnectWrapperCallback
    public void onReady(final T t) {
        if (t == null) {
            return;
        }
        BleLog.d(TAG, "onReady>>>> " + t.getBleName());
        runOnUiThread(new Runnable() { // from class: com.wifiled.blelibrary.ble.request.ConnectRequest2.4
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                if (ConnectRequest2.this.connectCallback != null) {
                    ConnectRequest2.this.connectCallback.onReady(t);
                }
                if (ConnectRequest2.this.bleWrapperCallback != null) {
                    ConnectRequest2.this.bleWrapperCallback.onReady((BleWrapperCallback) t);
                }
            }
        });
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ConnectWrapperCallback
    public void onServicesDiscovered(T t, BluetoothGatt bluetoothGatt) {
        BleLog.d(TAG, "onServicesDiscovered>>>> " + t.getBleName());
        BleConnectCallback<T> bleConnectCallback = this.connectCallback;
        if (bleConnectCallback != null) {
            bleConnectCallback.onServicesDiscovered(t, bluetoothGatt);
        }
        BleWrapperCallback<T> bleWrapperCallback = this.bleWrapperCallback;
        if (bleWrapperCallback != null) {
            bleWrapperCallback.onServicesDiscovered((BleWrapperCallback<T>) t, bluetoothGatt);
        }
    }

    private void addBleToPool(T t) {
        if (this.devices.containsKey(t.getBleAddress())) {
            BleLog.d(TAG, "addBleToPool>>>> device pool already exist device");
        } else {
            this.devices.put(t.getBleAddress(), t);
            BleLog.d(TAG, "addBleToPool>>>> added a new device to the device pool");
        }
    }

    public T getBleDevice(String str) {
        if (TextUtils.isEmpty(str)) {
            BleLog.e(TAG, "By address to get BleDevice but address is null");
            return null;
        }
        return this.devices.get(str);
    }

    public ArrayList<T> getConnectedDevices() {
        try {
            return new ArrayList<>(this.connectedDevices.values());
        } catch (Exception unused) {
            return new ArrayList<>();
        }
    }

    public void cancelConnectCallback() {
        this.connectCallback = null;
    }
}
