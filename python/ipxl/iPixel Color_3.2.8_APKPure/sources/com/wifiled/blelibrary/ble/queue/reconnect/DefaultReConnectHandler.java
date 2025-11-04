package com.wifiled.blelibrary.ble.queue.reconnect;

import android.text.TextUtils;
import com.wifiled.blelibrary.ble.BleLog;
import com.wifiled.blelibrary.ble.callback.BleConnectCallback;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.blelibrary.ble.queue.ConnectQueue;
import com.wifiled.blelibrary.ble.queue.RequestTask;
import com.wifiled.blelibrary.ble.request.ConnectRequest;
import com.wifiled.blelibrary.ble.request.Rproxy;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class DefaultReConnectHandler<T extends BleDevice> extends BleConnectCallback<T> {
    public static final long DEFAULT_CONNECT_DELAY = 2000;
    private static final String TAG = "DefaultReConnectHandler";
    private static DefaultReConnectHandler defaultReConnectHandler;
    private final ArrayList<T> autoDevices = new ArrayList<>();

    private DefaultReConnectHandler() {
    }

    public static <T extends BleDevice> DefaultReConnectHandler<T> provideReconnectHandler() {
        if (defaultReConnectHandler == null) {
            defaultReConnectHandler = new DefaultReConnectHandler();
        }
        return defaultReConnectHandler;
    }

    public boolean reconnect(T t) {
        BleLog.e(TAG, "reconnect>>>>>: " + this.autoDevices.size());
        Iterator<T> it = this.autoDevices.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(it.next().getBleAddress(), t.getBleAddress())) {
                return ((ConnectRequest) Rproxy.getRequest(ConnectRequest.class)).connect(t);
            }
        }
        return false;
    }

    private void addAutoPool(T t) {
        if (t != null && t.isAutoConnect()) {
            BleLog.d(TAG, "addAutoPool: Add automatic connection device to the connection pool");
            if (!this.autoDevices.contains(t)) {
                this.autoDevices.add(t);
            }
            ConnectQueue.getInstance().put(new RequestTask.Builder().devices(t).delay(DEFAULT_CONNECT_DELAY).build());
        }
    }

    private void removeAutoPool(T t) {
        if (t == null) {
            return;
        }
        Iterator<T> it = this.autoDevices.iterator();
        while (it.hasNext()) {
            if (t.getBleAddress().equals(it.next().getBleAddress())) {
                it.remove();
            }
        }
    }

    public void resetAutoConnect(T t, boolean z) {
        if (t == null) {
            return;
        }
        t.setAutoConnect(z);
        if (!z) {
            removeAutoPool(t);
            if (t.isConnecting()) {
                ((ConnectRequest) Rproxy.getRequest(ConnectRequest.class)).disconnect(t);
                return;
            }
            return;
        }
        addAutoPool(t);
    }

    public void cancelAutoConnect() {
        this.autoDevices.clear();
    }

    public void openBluetooth() {
        BleLog.i(TAG, "auto devices size：" + this.autoDevices.size());
        Iterator<T> it = this.autoDevices.iterator();
        while (it.hasNext()) {
            addAutoPool(it.next());
        }
    }

    @Override // com.wifiled.blelibrary.ble.callback.BleConnectCallback
    public void onConnectionChanged(T t) {
        if (t.isConnected()) {
            removeAutoPool(t);
            BleLog.e(TAG, "onConnectionChanged: removeAutoPool");
        } else if (t.isDisconnected()) {
            addAutoPool(t);
            BleLog.e(TAG, "onConnectionChanged: addAutoPool");
        }
    }
}
