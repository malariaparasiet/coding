package com.wifiled.blelibrary.ble.queue;

import com.wifiled.blelibrary.ble.BleLog;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.blelibrary.ble.queue.reconnect.DefaultReConnectHandler;

/* loaded from: classes2.dex */
public final class ConnectQueue extends Queue {
    private static volatile ConnectQueue sInstance;

    @Override // com.wifiled.blelibrary.ble.queue.Queue
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.wifiled.blelibrary.ble.queue.Queue
    public /* bridge */ /* synthetic */ void put(RequestTask requestTask) {
        super.put(requestTask);
    }

    @Override // com.wifiled.blelibrary.ble.queue.Queue
    public /* bridge */ /* synthetic */ void remove(Task task) {
        super.remove(task);
    }

    @Override // com.wifiled.blelibrary.ble.queue.Queue
    public /* bridge */ /* synthetic */ void shutDown() {
        super.shutDown();
    }

    private ConnectQueue() {
    }

    public static ConnectQueue getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (ConnectQueue.class) {
            if (sInstance == null) {
                sInstance = new ConnectQueue();
            }
        }
        return sInstance;
    }

    @Override // com.wifiled.blelibrary.ble.queue.Queue
    public void execute(RequestTask requestTask) {
        BleDevice bleDevice = requestTask.getDevices()[0];
        BleLog.i("ConnectQueue", "正在重新连接设备:>>>>>>>result:" + DefaultReConnectHandler.provideReconnectHandler().reconnect(bleDevice) + ">>>" + bleDevice.getBleName());
    }
}
