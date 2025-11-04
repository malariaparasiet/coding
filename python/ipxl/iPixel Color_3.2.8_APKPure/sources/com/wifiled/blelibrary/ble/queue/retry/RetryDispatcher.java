package com.wifiled.blelibrary.ble.queue.retry;

import com.wifiled.blelibrary.ble.Ble;
import com.wifiled.blelibrary.ble.BleLog;
import com.wifiled.blelibrary.ble.callback.BleConnectCallback;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.blelibrary.ble.request.ConnectRequest;
import com.wifiled.blelibrary.ble.request.Rproxy;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class RetryDispatcher<T extends BleDevice> extends BleConnectCallback<T> implements RetryCallback<T> {
    private static final String TAG = "RetryDispatcher";
    private static RetryDispatcher retryDispatcher;
    private final Map<String, Integer> deviceRetryMap = new HashMap();

    public static <T extends BleDevice> RetryDispatcher<T> getInstance() {
        if (retryDispatcher == null) {
            retryDispatcher = new RetryDispatcher();
        }
        return retryDispatcher;
    }

    @Override // com.wifiled.blelibrary.ble.queue.retry.RetryCallback
    public void retry(T t) {
        BleLog.i(TAG, "正在尝试重试连接第" + this.deviceRetryMap.get(t.getBleAddress()) + "次重连: " + t.getBleName());
        if (t.isAutoConnect()) {
            return;
        }
        ((ConnectRequest) Rproxy.getRequest(ConnectRequest.class)).connect(t);
    }

    @Override // com.wifiled.blelibrary.ble.callback.BleConnectCallback
    public void onConnectionChanged(BleDevice bleDevice) {
        BleLog.i(TAG, "onConnectionChanged:" + bleDevice.getBleName() + "---连接状态:" + bleDevice.isConnected());
        if (bleDevice.isConnected()) {
            this.deviceRetryMap.remove(bleDevice.getBleAddress());
        }
    }

    @Override // com.wifiled.blelibrary.ble.callback.BleConnectCallback
    public void onConnectFailed(T t, int i) {
        super.onConnectFailed((RetryDispatcher<T>) t, i);
        if (i == 2032 || i == 2031) {
            String bleAddress = t.getBleAddress();
            int i2 = Ble.options().connectFailedRetryCount;
            if (i2 <= 0) {
                return;
            }
            if (this.deviceRetryMap.containsKey(bleAddress)) {
                i2 = this.deviceRetryMap.get(bleAddress).intValue();
            }
            if (i2 <= 0) {
                this.deviceRetryMap.remove(bleAddress);
            } else {
                this.deviceRetryMap.put(bleAddress, Integer.valueOf(i2 - 1));
                retry((RetryDispatcher<T>) t);
            }
        }
    }
}
