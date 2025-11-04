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
import com.wifiled.blelibrary.ble.request.ConnectRequest;
import com.wifiled.blelibrary.ble.request.MtuRequest;
import com.wifiled.blelibrary.ble.request.NotifyRequest;
import com.wifiled.blelibrary.ble.request.ReadRequest;
import com.wifiled.blelibrary.ble.request.ReadRssiRequest;
import com.wifiled.blelibrary.ble.request.Rproxy;
import com.wifiled.blelibrary.ble.request.ScanRequest;
import com.wifiled.blelibrary.ble.request.WriteRequest;
import java.util.UUID;

/* loaded from: classes2.dex */
public class RequestImpl<T extends BleDevice> implements RequestListener<T> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ void cancelNotify(Object obj, BleNotifyCallback bleNotifyCallback) {
        cancelNotify((RequestImpl<T>) obj, (BleNotifyCallback<RequestImpl<T>>) bleNotifyCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ boolean connect(Object obj, BleConnectCallback bleConnectCallback) {
        return connect((RequestImpl<T>) obj, (BleConnectCallback<RequestImpl<T>>) bleConnectCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ void disconnect(Object obj, BleConnectCallback bleConnectCallback) {
        disconnect((RequestImpl<T>) obj, (BleConnectCallback<RequestImpl<T>>) bleConnectCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ void enableNotify(Object obj, boolean z, BleNotifyCallback bleNotifyCallback) {
        enableNotify((RequestImpl<T>) obj, z, (BleNotifyCallback<RequestImpl<T>>) bleNotifyCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ void enableNotifyByUuid(Object obj, boolean z, UUID uuid, UUID uuid2, BleNotifyCallback bleNotifyCallback) {
        enableNotifyByUuid((RequestImpl<T>) obj, z, uuid, uuid2, (BleNotifyCallback<RequestImpl<T>>) bleNotifyCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ void notify(Object obj, BleNotifyCallback bleNotifyCallback) {
        notify((RequestImpl<T>) obj, (BleNotifyCallback<RequestImpl<T>>) bleNotifyCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ boolean read(Object obj, BleReadCallback bleReadCallback) {
        return read((RequestImpl<T>) obj, (BleReadCallback<RequestImpl<T>>) bleReadCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ boolean readByUuid(Object obj, UUID uuid, UUID uuid2, BleReadCallback bleReadCallback) {
        return readByUuid((RequestImpl<T>) obj, uuid, uuid2, (BleReadCallback<RequestImpl<T>>) bleReadCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ boolean readRssi(Object obj, BleReadRssiCallback bleReadRssiCallback) {
        return readRssi((RequestImpl<T>) obj, (BleReadRssiCallback<RequestImpl<T>>) bleReadRssiCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ boolean write(Object obj, byte[] bArr, BleWriteCallback bleWriteCallback) {
        return write((RequestImpl<T>) obj, bArr, (BleWriteCallback<RequestImpl<T>>) bleWriteCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ boolean writeByUuid(Object obj, byte[] bArr, UUID uuid, UUID uuid2, BleWriteCallback bleWriteCallback) {
        return writeByUuid((RequestImpl<T>) obj, bArr, uuid, uuid2, (BleWriteCallback<RequestImpl<T>>) bleWriteCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public /* bridge */ /* synthetic */ void writeEntity(Object obj, byte[] bArr, int i, int i2, BleWriteEntityCallback bleWriteEntityCallback) {
        writeEntity((RequestImpl<T>) obj, bArr, i, i2, (BleWriteEntityCallback<RequestImpl<T>>) bleWriteEntityCallback);
    }

    public static RequestImpl newRequestImpl() {
        return new RequestImpl();
    }

    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public void startScan(BleScanCallback<T> bleScanCallback, long j) {
        ((ScanRequest) Rproxy.getRequest(ScanRequest.class)).startScan(bleScanCallback, j);
    }

    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public void stopScan() {
        ((ScanRequest) Rproxy.getRequest(ScanRequest.class)).stopScan();
    }

    public boolean connect(T t, BleConnectCallback<T> bleConnectCallback) {
        return ((ConnectRequest) Rproxy.getRequest(ConnectRequest.class)).connect((ConnectRequest) t, (BleConnectCallback<ConnectRequest>) bleConnectCallback);
    }

    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public boolean connect(String str, BleConnectCallback<T> bleConnectCallback) {
        return ((ConnectRequest) Rproxy.getRequest(ConnectRequest.class)).connect(str, bleConnectCallback);
    }

    public void notify(T t, BleNotifyCallback<T> bleNotifyCallback) {
        ((NotifyRequest) Rproxy.getRequest(NotifyRequest.class)).notify(t, true, bleNotifyCallback);
    }

    public void cancelNotify(T t, BleNotifyCallback<T> bleNotifyCallback) {
        ((NotifyRequest) Rproxy.getRequest(NotifyRequest.class)).cancelNotify(t, bleNotifyCallback);
    }

    public void enableNotify(T t, boolean z, BleNotifyCallback<T> bleNotifyCallback) {
        ((NotifyRequest) Rproxy.getRequest(NotifyRequest.class)).notify(t, z, bleNotifyCallback);
    }

    public void enableNotifyByUuid(T t, boolean z, UUID uuid, UUID uuid2, BleNotifyCallback<T> bleNotifyCallback) {
        ((NotifyRequest) Rproxy.getRequest(NotifyRequest.class)).notifyByUuid(t, z, uuid, uuid2, bleNotifyCallback);
    }

    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public void disconnect(T t) {
        ((ConnectRequest) Rproxy.getRequest(ConnectRequest.class)).disconnect(t);
    }

    public void disconnect(T t, BleConnectCallback<T> bleConnectCallback) {
        ((ConnectRequest) Rproxy.getRequest(ConnectRequest.class)).disconnect(t, bleConnectCallback);
    }

    public boolean read(T t, BleReadCallback<T> bleReadCallback) {
        return ((ReadRequest) Rproxy.getRequest(ReadRequest.class)).read(t, bleReadCallback);
    }

    public boolean readByUuid(T t, UUID uuid, UUID uuid2, BleReadCallback<T> bleReadCallback) {
        return ((ReadRequest) Rproxy.getRequest(ReadRequest.class)).readByUuid(t, uuid, uuid2, bleReadCallback);
    }

    public boolean readRssi(T t, BleReadRssiCallback<T> bleReadRssiCallback) {
        return ((ReadRssiRequest) Rproxy.getRequest(ReadRssiRequest.class)).readRssi(t, bleReadRssiCallback);
    }

    public boolean write(T t, byte[] bArr, BleWriteCallback<T> bleWriteCallback) {
        return ((WriteRequest) Rproxy.getRequest(WriteRequest.class)).write(t, bArr, bleWriteCallback);
    }

    public boolean writeByUuid(T t, byte[] bArr, UUID uuid, UUID uuid2, BleWriteCallback<T> bleWriteCallback) {
        return ((WriteRequest) Rproxy.getRequest(WriteRequest.class)).writeByUuid(t, bArr, uuid, uuid2, bleWriteCallback);
    }

    public void writeEntity(T t, byte[] bArr, int i, int i2, BleWriteEntityCallback<T> bleWriteEntityCallback) {
        ((WriteRequest) Rproxy.getRequest(WriteRequest.class)).writeEntity(t, bArr, i, i2, bleWriteEntityCallback);
    }

    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public void writeEntity(EntityData entityData, BleWriteEntityCallback<T> bleWriteEntityCallback) {
        ((WriteRequest) Rproxy.getRequest(WriteRequest.class)).writeEntity(entityData, bleWriteEntityCallback);
    }

    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public void cancelWriteEntity() {
        ((WriteRequest) Rproxy.getRequest(WriteRequest.class)).cancelWriteEntity();
    }

    @Override // com.wifiled.blelibrary.ble.proxy.RequestListener
    public boolean setMtu(String str, int i, BleMtuCallback<T> bleMtuCallback) {
        return ((MtuRequest) Rproxy.getRequest(MtuRequest.class)).setMtu(str, i, bleMtuCallback);
    }
}
