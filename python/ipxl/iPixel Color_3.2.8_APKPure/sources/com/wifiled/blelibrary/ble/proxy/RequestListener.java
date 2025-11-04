package com.wifiled.blelibrary.ble.proxy;

import com.wifiled.blelibrary.ble.callback.BleConnectCallback;
import com.wifiled.blelibrary.ble.callback.BleMtuCallback;
import com.wifiled.blelibrary.ble.callback.BleNotifyCallback;
import com.wifiled.blelibrary.ble.callback.BleReadCallback;
import com.wifiled.blelibrary.ble.callback.BleReadRssiCallback;
import com.wifiled.blelibrary.ble.callback.BleScanCallback;
import com.wifiled.blelibrary.ble.callback.BleWriteCallback;
import com.wifiled.blelibrary.ble.callback.BleWriteEntityCallback;
import com.wifiled.blelibrary.ble.model.EntityData;
import java.util.UUID;

/* loaded from: classes2.dex */
public interface RequestListener<T> {
    void cancelNotify(T t, BleNotifyCallback<T> bleNotifyCallback);

    void cancelWriteEntity();

    boolean connect(T t, BleConnectCallback<T> bleConnectCallback);

    boolean connect(String str, BleConnectCallback<T> bleConnectCallback);

    void disconnect(T t);

    void disconnect(T t, BleConnectCallback<T> bleConnectCallback);

    void enableNotify(T t, boolean z, BleNotifyCallback<T> bleNotifyCallback);

    void enableNotifyByUuid(T t, boolean z, UUID uuid, UUID uuid2, BleNotifyCallback<T> bleNotifyCallback);

    void notify(T t, BleNotifyCallback<T> bleNotifyCallback);

    boolean read(T t, BleReadCallback<T> bleReadCallback);

    boolean readByUuid(T t, UUID uuid, UUID uuid2, BleReadCallback<T> bleReadCallback);

    boolean readRssi(T t, BleReadRssiCallback<T> bleReadRssiCallback);

    boolean setMtu(String str, int i, BleMtuCallback<T> bleMtuCallback);

    void startScan(BleScanCallback<T> bleScanCallback, long j);

    void stopScan();

    boolean write(T t, byte[] bArr, BleWriteCallback<T> bleWriteCallback);

    boolean writeByUuid(T t, byte[] bArr, UUID uuid, UUID uuid2, BleWriteCallback<T> bleWriteCallback);

    void writeEntity(EntityData entityData, BleWriteEntityCallback<T> bleWriteEntityCallback);

    void writeEntity(T t, byte[] bArr, int i, int i2, BleWriteEntityCallback<T> bleWriteEntityCallback);
}
