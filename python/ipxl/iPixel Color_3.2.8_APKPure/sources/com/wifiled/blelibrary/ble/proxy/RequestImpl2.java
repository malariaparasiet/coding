package com.wifiled.blelibrary.ble.proxy;

import com.wifiled.blelibrary.ble.callback.BleConnectCallback;
import com.wifiled.blelibrary.ble.callback.BleMtuCallback;
import com.wifiled.blelibrary.ble.callback.BleNotifyCallback;
import com.wifiled.blelibrary.ble.callback.BleReadCallback;
import com.wifiled.blelibrary.ble.callback.BleReadRssiCallback;
import com.wifiled.blelibrary.ble.callback.BleScanCallback;
import com.wifiled.blelibrary.ble.callback.BleWriteCallback;
import com.wifiled.blelibrary.ble.callback.BleWriteEntityCallback;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.blelibrary.ble.model.EntityData;
import com.wifiled.blelibrary.ble.request.ConnectRequest2;
import com.wifiled.blelibrary.ble.request.MtuRequest2;
import com.wifiled.blelibrary.ble.request.NotifyRequest2;
import com.wifiled.blelibrary.ble.request.ReadRequest2;
import com.wifiled.blelibrary.ble.request.ReadRssiRequest2;
import com.wifiled.blelibrary.ble.request.Rproxy2;
import com.wifiled.blelibrary.ble.request.WriteRequest2;
import java.util.UUID;

/* loaded from: classes2.dex */
public class RequestImpl2<T extends BleDevice> implements RequestListener<T> {
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public void startScan(BleScanCallback<T> bleScanCallback, long j) {
    }

    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public void stopScan() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ void cancelNotify(Object obj, BleNotifyCallback bleNotifyCallback) {
        cancelNotify((RequestImpl2<T>) obj, (BleNotifyCallback<RequestImpl2<T>>) bleNotifyCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ boolean connect(Object obj, BleConnectCallback bleConnectCallback) {
        return connect((RequestImpl2<T>) obj, (BleConnectCallback<RequestImpl2<T>>) bleConnectCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ void disconnect(Object obj, BleConnectCallback bleConnectCallback) {
        disconnect((RequestImpl2<T>) obj, (BleConnectCallback<RequestImpl2<T>>) bleConnectCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ void enableNotify(Object obj, boolean z, BleNotifyCallback bleNotifyCallback) {
        enableNotify((RequestImpl2<T>) obj, z, (BleNotifyCallback<RequestImpl2<T>>) bleNotifyCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ void enableNotifyByUuid(Object obj, boolean z, UUID uuid, UUID uuid2, BleNotifyCallback bleNotifyCallback) {
        enableNotifyByUuid((RequestImpl2<T>) obj, z, uuid, uuid2, (BleNotifyCallback<RequestImpl2<T>>) bleNotifyCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ void notify(Object obj, BleNotifyCallback bleNotifyCallback) {
        notify((RequestImpl2<T>) obj, (BleNotifyCallback<RequestImpl2<T>>) bleNotifyCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ boolean read(Object obj, BleReadCallback bleReadCallback) {
        return read((RequestImpl2<T>) obj, (BleReadCallback<RequestImpl2<T>>) bleReadCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ boolean readByUuid(Object obj, UUID uuid, UUID uuid2, BleReadCallback bleReadCallback) {
        return readByUuid((RequestImpl2<T>) obj, uuid, uuid2, (BleReadCallback<RequestImpl2<T>>) bleReadCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ boolean readRssi(Object obj, BleReadRssiCallback bleReadRssiCallback) {
        return readRssi((RequestImpl2<T>) obj, (BleReadRssiCallback<RequestImpl2<T>>) bleReadRssiCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ boolean write(Object obj, byte[] bArr, BleWriteCallback bleWriteCallback) {
        return write((RequestImpl2<T>) obj, bArr, (BleWriteCallback<RequestImpl2<T>>) bleWriteCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ boolean writeByUuid(Object obj, byte[] bArr, UUID uuid, UUID uuid2, BleWriteCallback bleWriteCallback) {
        return writeByUuid((RequestImpl2<T>) obj, bArr, uuid, uuid2, (BleWriteCallback<RequestImpl2<T>>) bleWriteCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ void writeEntity(Object obj, byte[] bArr, int i, int i2, BleWriteEntityCallback bleWriteEntityCallback) {
        writeEntity((RequestImpl2<T>) obj, bArr, i, i2, (BleWriteEntityCallback<RequestImpl2<T>>) bleWriteEntityCallback);
    }

    public static RequestImpl2 newRequestImpl() {
        return new RequestImpl2();
    }

    public boolean connect(T t, BleConnectCallback<T> bleConnectCallback) {
        return ((ConnectRequest2) Rproxy2.getRequest(ConnectRequest2.class)).connect((ConnectRequest2) t, (BleConnectCallback<ConnectRequest2>) bleConnectCallback);
    }

    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public boolean connect(String str, BleConnectCallback<T> bleConnectCallback) {
        return ((ConnectRequest2) Rproxy2.getRequest(ConnectRequest2.class)).connect(str, bleConnectCallback);
    }

    public void notify(T t, BleNotifyCallback<T> bleNotifyCallback) {
        ((NotifyRequest2) Rproxy2.getRequest(NotifyRequest2.class)).notify(t, true, bleNotifyCallback);
    }

    public void cancelNotify(T t, BleNotifyCallback<T> bleNotifyCallback) {
        ((NotifyRequest2) Rproxy2.getRequest(NotifyRequest2.class)).cancelNotify(t, bleNotifyCallback);
    }

    public void enableNotify(T t, boolean z, BleNotifyCallback<T> bleNotifyCallback) {
        ((NotifyRequest2) Rproxy2.getRequest(NotifyRequest2.class)).notify(t, z, bleNotifyCallback);
    }

    public void enableNotifyByUuid(T t, boolean z, UUID uuid, UUID uuid2, BleNotifyCallback<T> bleNotifyCallback) {
        ((NotifyRequest2) Rproxy2.getRequest(NotifyRequest2.class)).notifyByUuid(t, z, uuid, uuid2, bleNotifyCallback);
    }

    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public void disconnect(T t) {
        ((ConnectRequest2) Rproxy2.getRequest(ConnectRequest2.class)).disconnect(t);
    }

    public void disconnect(T t, BleConnectCallback<T> bleConnectCallback) {
        ((ConnectRequest2) Rproxy2.getRequest(ConnectRequest2.class)).disconnect(t, bleConnectCallback);
    }

    public boolean read(T t, BleReadCallback<T> bleReadCallback) {
        return ((ReadRequest2) Rproxy2.getRequest(ReadRequest2.class)).read(t, bleReadCallback);
    }

    public boolean readByUuid(T t, UUID uuid, UUID uuid2, BleReadCallback<T> bleReadCallback) {
        return ((ReadRequest2) Rproxy2.getRequest(ReadRequest2.class)).readByUuid(t, uuid, uuid2, bleReadCallback);
    }

    public boolean readRssi(T t, BleReadRssiCallback<T> bleReadRssiCallback) {
        return ((ReadRssiRequest2) Rproxy2.getRequest(ReadRssiRequest2.class)).readRssi(t, bleReadRssiCallback);
    }

    public boolean write(T t, byte[] bArr, BleWriteCallback<T> bleWriteCallback) {
        return ((WriteRequest2) Rproxy2.getRequest(WriteRequest2.class)).write(t, bArr, bleWriteCallback);
    }

    public boolean writeByUuid(T t, byte[] bArr, UUID uuid, UUID uuid2, BleWriteCallback<T> bleWriteCallback) {
        return ((WriteRequest2) Rproxy2.getRequest(WriteRequest2.class)).writeByUuid(t, bArr, uuid, uuid2, bleWriteCallback);
    }

    public void writeEntity(T t, byte[] bArr, int i, int i2, BleWriteEntityCallback<T> bleWriteEntityCallback) {
        ((WriteRequest2) Rproxy2.getRequest(WriteRequest2.class)).writeEntity(t, bArr, i, i2, bleWriteEntityCallback);
    }

    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public void writeEntity(EntityData entityData, BleWriteEntityCallback<T> bleWriteEntityCallback) {
        ((WriteRequest2) Rproxy2.getRequest(WriteRequest2.class)).writeEntity(entityData, bleWriteEntityCallback);
    }

    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public void cancelWriteEntity() {
        ((WriteRequest2) Rproxy2.getRequest(WriteRequest2.class)).cancelWriteEntity();
    }

    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public boolean setMtu(String str, int i, BleMtuCallback<T> bleMtuCallback) {
        return ((MtuRequest2) Rproxy2.getRequest(MtuRequest2.class)).setMtu(str, i, bleMtuCallback);
    }
}
