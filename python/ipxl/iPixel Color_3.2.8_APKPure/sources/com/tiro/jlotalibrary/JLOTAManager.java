package com.tiro.jlotalibrary;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.Context;
import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import com.jieli.jl_bt_ota.impl.BluetoothOTAManager;
import com.jieli.jl_bt_ota.model.base.BaseError;
import com.tiro.jlotalibrary.util.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;

/* compiled from: JLOTAManager.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001Bâ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012#\u0010\u0004\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005\u0012#\u0010\u000b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005\u0012#\u0010\f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\n0\u0005\u0012b\u0010\u000e\u001a^\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0012\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u000f¢\u0006\u0004\b\u0016\u0010\u0017J\n\u0010,\u001a\u0004\u0018\u00010\u0006H\u0016J\n\u0010-\u001a\u0004\u0018\u00010&H\u0016J\u0012\u0010.\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010/\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0016J\u001c\u00100\u001a\u00020\u00152\b\u0010\t\u001a\u0004\u0018\u00010\u00062\b\u00101\u001a\u0004\u0018\u00010\u0013H\u0017J\u001e\u00102\u001a\b\u0012\u0004\u0012\u00020\u0013032\u0006\u00101\u001a\u00020\u00132\u0006\u00104\u001a\u00020#H\u0002J\u0012\u00105\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u00106\u001a\u00020\nH\u0016J\u0016\u00107\u001a\u00020\n2\u0006\u00108\u001a\u00020&2\u0006\u00109\u001a\u00020#J\u0016\u0010:\u001a\u00020\n2\u0006\u0010;\u001a\u00020#2\u0006\u00109\u001a\u00020#J\u001a\u0010<\u001a\u00020\n2\b\u00108\u001a\u0004\u0018\u00010&2\b\u0010=\u001a\u0004\u0018\u00010>J\u0010\u0010?\u001a\u00020#2\u0006\u00109\u001a\u00020#H\u0002R.\u0010\u0004\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R.\u0010\u000b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R.\u0010\f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019Rm\u0010\u000e\u001a^\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0012\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00130\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010'\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+¨\u0006@"}, d2 = {"Lcom/tiro/jlotalibrary/JLOTAManager;", "Lcom/jieli/jl_bt_ota/impl/BluetoothOTAManager;", "context", "Landroid/content/Context;", "connect", "Lkotlin/Function1;", "Landroid/bluetooth/BluetoothDevice;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "device", "", "disconnect", "error", "Lcom/jieli/jl_bt_ota/model/base/BaseError;", "sendData", "Lkotlin/Function4;", "Ljava/util/UUID;", "uuidService", "uuidWrite", "", "item", "", "<init>", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "getConnect", "()Lkotlin/jvm/functions/Function1;", "getDisconnect", "getError", "getSendData", "()Lkotlin/jvm/functions/Function4;", "linked", "Ljava/util/concurrent/LinkedBlockingQueue;", "connectStatusCache", "", "", "", "bluetoothDevice", "bluetoothGatt", "Landroid/bluetooth/BluetoothGatt;", "mtu", "getMtu", "()I", "setMtu", "(I)V", "getConnectedDevice", "getConnectedBluetoothGatt", "connectBluetoothDevice", "disconnectBluetoothDevice", "sendDataToDevice", "data", "splitByteArray", "", "size", "errorEventCallback", "release", "onBleConnection", "gatt", NotificationCompat.CATEGORY_STATUS, "onBleMtuChanged", "block", "onBleData", "characteristic", "Landroid/bluetooth/BluetoothGattCharacteristic;", "changeConnectStatus", "jlotalibrary_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JLOTAManager extends BluetoothOTAManager {
    private BluetoothDevice bluetoothDevice;
    private BluetoothGatt bluetoothGatt;
    private final Function1<BluetoothDevice, Unit> connect;
    private final Map<String, Integer> connectStatusCache;
    private final Function1<BluetoothDevice, Unit> disconnect;
    private final Function1<BaseError, Unit> error;
    private final LinkedBlockingQueue<byte[]> linked;
    private int mtu;
    private final Function4<UUID, UUID, BluetoothDevice, byte[], Boolean> sendData;

    private final int changeConnectStatus(int status) {
        if (status == 0) {
            return 0;
        }
        if (status != 1) {
            return status != 2 ? 0 : 1;
        }
        return 3;
    }

    public final Function1<BluetoothDevice, Unit> getConnect() {
        return this.connect;
    }

    public final Function1<BluetoothDevice, Unit> getDisconnect() {
        return this.disconnect;
    }

    public final Function1<BaseError, Unit> getError() {
        return this.error;
    }

    public final Function4<UUID, UUID, BluetoothDevice, byte[], Boolean> getSendData() {
        return this.sendData;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public JLOTAManager(Context context, Function1<? super BluetoothDevice, Unit> connect, Function1<? super BluetoothDevice, Unit> disconnect, Function1<? super BaseError, Unit> error, Function4<? super UUID, ? super UUID, ? super BluetoothDevice, ? super byte[], Boolean> sendData) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(connect, "connect");
        Intrinsics.checkNotNullParameter(disconnect, "disconnect");
        Intrinsics.checkNotNullParameter(error, "error");
        Intrinsics.checkNotNullParameter(sendData, "sendData");
        this.connect = connect;
        this.disconnect = disconnect;
        this.error = error;
        this.sendData = sendData;
        this.linked = new LinkedBlockingQueue<>();
        this.connectStatusCache = new LinkedHashMap();
        this.mtu = 500;
    }

    public final int getMtu() {
        return this.mtu;
    }

    public final void setMtu(int i) {
        this.mtu = i;
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    /* renamed from: getConnectedDevice, reason: from getter */
    public BluetoothDevice getBluetoothDevice() {
        return this.bluetoothDevice;
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    /* renamed from: getConnectedBluetoothGatt, reason: from getter */
    public BluetoothGatt getBluetoothGatt() {
        return this.bluetoothGatt;
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void connectBluetoothDevice(BluetoothDevice device) {
        this.connect.invoke(device);
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void disconnectBluetoothDevice(BluetoothDevice device) {
        this.disconnect.invoke(device);
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public boolean sendDataToDevice(BluetoothDevice device, byte[] data) {
        if (data == null) {
            return false;
        }
        LogUtil logUtil = LogUtil.INSTANCE;
        int i = this.mtu;
        String arrays = Arrays.toString(data);
        Intrinsics.checkNotNullExpressionValue(arrays, "toString(...)");
        logUtil.d("发送：data--" + i + "---- " + arrays);
        Iterator<T> it = splitByteArray(data, this.mtu - 6).iterator();
        while (it.hasNext()) {
            this.linked.put((byte[]) it.next());
        }
        try {
            BuildersKt__BuildersKt.runBlocking$default(null, new JLOTAManager$sendDataToDevice$1$2(this, device, null), 1, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private final List<byte[]> splitByteArray(byte[] data, int size) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < data.length) {
            int i2 = i + size;
            arrayList.add(ArraysKt.copyOfRange(data, i, Math.min(i2, data.length)));
            i = i2;
        }
        return arrayList;
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothOTAManager, com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void errorEventCallback(BaseError error) {
        LogUtil.INSTANCE.e("error:code--" + (error != null ? Integer.valueOf(error.getCode()) : null) + "\nmessage--" + (error != null ? error.getMessage() : null));
        this.error.invoke(error);
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothOTAManager, com.jieli.jl_bt_ota.impl.BluetoothBreProfiles, com.jieli.jl_bt_ota.impl.BluetoothDiscovery, com.jieli.jl_bt_ota.impl.BluetoothBase, com.jieli.jl_bt_ota.interfaces.IUpgradeManager
    public void release() {
        this.linked.clear();
        this.connectStatusCache.clear();
        this.bluetoothDevice = null;
        this.bluetoothGatt = null;
    }

    public final void onBleConnection(BluetoothGatt gatt, int status) {
        Intrinsics.checkNotNullParameter(gatt, "gatt");
        int changeConnectStatus = changeConnectStatus(status);
        Integer num = this.connectStatusCache.get(gatt.getDevice().getAddress());
        if (num != null && num.intValue() == changeConnectStatus) {
            LogUtil.INSTANCE.i("不要重复传入相同状态");
            return;
        }
        this.connectStatusCache.put(gatt.getDevice().getAddress(), Integer.valueOf(changeConnectStatus));
        LogUtil.INSTANCE.d("#1传递设备的连接状态 -- " + changeConnectStatus);
        if (changeConnectStatus == 0) {
            this.bluetoothDevice = null;
            this.bluetoothGatt = null;
        } else if (changeConnectStatus == 1) {
            this.bluetoothDevice = gatt.getDevice();
            this.bluetoothGatt = gatt;
        }
        onBtDeviceConnection(gatt.getDevice(), changeConnectStatus);
    }

    public final void onBleMtuChanged(int block, int status) {
        this.mtu = block;
        onMtuChanged(getBluetoothGatt(), block, status);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onBleData(android.bluetooth.BluetoothGatt r7, android.bluetooth.BluetoothGattCharacteristic r8) {
        /*
            r6 = this;
            com.tiro.jlotalibrary.util.LogUtil r0 = com.tiro.jlotalibrary.util.LogUtil.INSTANCE
            r1 = 0
            if (r7 == 0) goto L39
            android.bluetooth.BluetoothDevice r2 = r6.bluetoothDevice
            if (r2 == 0) goto Le
            java.lang.String r2 = r2.getAddress()
            goto Lf
        Le:
            r2 = r1
        Lf:
            android.bluetooth.BluetoothDevice r3 = r7.getDevice()
            if (r3 == 0) goto L1a
            java.lang.String r3 = r3.getAddress()
            goto L1b
        L1a:
            r3 = r1
        L1b:
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            if (r2 == 0) goto L39
            if (r8 == 0) goto L2e
            android.bluetooth.BluetoothGattService r2 = r8.getService()
            if (r2 == 0) goto L2e
            java.util.UUID r2 = r2.getUuid()
            goto L2f
        L2e:
            r2 = r1
        L2f:
            java.util.UUID r3 = com.jieli.jl_bt_ota.constant.BluetoothConstant.UUID_SERVICE
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            if (r2 == 0) goto L39
            r2 = 1
            goto L3a
        L39:
            r2 = 0
        L3a:
            com.tiro.jlotalibrary.util.LogUtil r3 = com.tiro.jlotalibrary.util.LogUtil.INSTANCE
            if (r8 == 0) goto L43
            byte[] r4 = r8.getValue()
            goto L44
        L43:
            r4 = r1
        L44:
            java.lang.String r3 = r3.binaryToHexString(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "#3传递设备 接收数据 --"
            r4.<init>(r5)
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r4 = "-- "
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.d(r2)
            if (r7 == 0) goto La3
            android.bluetooth.BluetoothDevice r0 = r6.bluetoothDevice
            if (r0 == 0) goto L6f
            java.lang.String r0 = r0.getAddress()
            goto L70
        L6f:
            r0 = r1
        L70:
            android.bluetooth.BluetoothDevice r7 = r7.getDevice()
            if (r7 == 0) goto L7b
            java.lang.String r7 = r7.getAddress()
            goto L7c
        L7b:
            r7 = r1
        L7c:
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r7)
            if (r7 == 0) goto La3
            if (r8 == 0) goto L8f
            android.bluetooth.BluetoothGattService r7 = r8.getService()
            if (r7 == 0) goto L8f
            java.util.UUID r7 = r7.getUuid()
            goto L90
        L8f:
            r7 = r1
        L90:
            java.util.UUID r0 = com.jieli.jl_bt_ota.constant.BluetoothConstant.UUID_SERVICE
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r0)
            if (r7 == 0) goto La3
            android.bluetooth.BluetoothDevice r7 = r6.bluetoothDevice
            if (r8 == 0) goto La0
            byte[] r1 = r8.getValue()
        La0:
            r6.onReceiveDeviceData(r7, r1)
        La3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tiro.jlotalibrary.JLOTAManager.onBleData(android.bluetooth.BluetoothGatt, android.bluetooth.BluetoothGattCharacteristic):void");
    }
}
