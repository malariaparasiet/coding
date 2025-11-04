package com.tiro.jlotalibrary.exposed;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;

/* compiled from: BleStateCallBack.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007H&J\u001a\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/tiro/jlotalibrary/exposed/BleStateCallBack;", "", "onBleConnection", "", "gatt", "Landroid/bluetooth/BluetoothGatt;", NotificationCompat.CATEGORY_STATUS, "", "onBleMtuChanged", "block", "onBleData", "characteristic", "Landroid/bluetooth/BluetoothGattCharacteristic;", "jlotalibrary_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface BleStateCallBack {
    void onBleConnection(BluetoothGatt gatt, int status);

    void onBleData(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic);

    void onBleMtuChanged(int block, int status);
}
