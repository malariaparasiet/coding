package com.tiro.jlotalibrary.exposed;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import java.util.UUID;
import kotlin.Metadata;

/* compiled from: BleSOTAData.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH&J\b\u0010\t\u001a\u00020\nH&J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rH&J\u0012\u0010\u000e\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u000fH&J*\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\f\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0015\u001a\u00020\u0016H&¨\u0006\u0017À\u0006\u0003"}, d2 = {"Lcom/tiro/jlotalibrary/exposed/BleSOTAData;", "", "registerBleCallback", "", "back", "Lcom/tiro/jlotalibrary/exposed/BleStateCallBack;", "unregisterBleCallback", "getGatt", "Landroid/bluetooth/BluetoothGatt;", "getMtu", "", "connect", "device", "", "disconnect", "Landroid/bluetooth/BluetoothDevice;", "sendData", "", "uuidService", "Ljava/util/UUID;", "uuidWrite", "item", "", "jlotalibrary_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface BleSOTAData {
    void connect(String device);

    void disconnect(BluetoothDevice device);

    BluetoothGatt getGatt();

    int getMtu();

    void registerBleCallback(BleStateCallBack back);

    boolean sendData(UUID uuidService, UUID uuidWrite, BluetoothDevice device, byte[] item);

    void unregisterBleCallback();
}
