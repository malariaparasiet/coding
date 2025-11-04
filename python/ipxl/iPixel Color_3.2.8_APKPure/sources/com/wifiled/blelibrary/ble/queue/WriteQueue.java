package com.wifiled.blelibrary.ble.queue;

import com.wifiled.blelibrary.ble.BleRequestImpl;
import com.wifiled.blelibrary.ble.model.BleDevice;

/* loaded from: classes2.dex */
public final class WriteQueue extends Queue {
    private static volatile WriteQueue sInstance;
    protected BleRequestImpl bleRequest = BleRequestImpl.getBleRequest();

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

    private WriteQueue() {
    }

    public static WriteQueue getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (WriteQueue.class) {
            if (sInstance == null) {
                sInstance = new WriteQueue();
            }
        }
        return sInstance;
    }

    @Override // com.wifiled.blelibrary.ble.queue.Queue
    public void execute(RequestTask requestTask) {
        for (BleDevice bleDevice : requestTask.getDevices()) {
            this.bleRequest.writeCharacteristic(bleDevice.getBleAddress(), requestTask.getData());
        }
    }
}
