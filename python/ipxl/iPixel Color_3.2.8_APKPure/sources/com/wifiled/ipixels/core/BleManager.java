package com.wifiled.ipixels.core;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.util.Base64;
import android.util.Log;
import androidx.camera.core.RetryPolicy;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.retrofit.ApiService;
import com.wifiled.baselib.retrofit.NetWorkManager;
import com.wifiled.baselib.retrofit.entity.CloudRes;
import com.wifiled.baselib.utils.AppUtils;
import com.wifiled.blelibrary.ble.Ble;
import com.wifiled.blelibrary.ble.BleLog;
import com.wifiled.blelibrary.ble.callback.BleConnectCallback;
import com.wifiled.blelibrary.ble.callback.BleMtuCallback;
import com.wifiled.blelibrary.ble.callback.BleNotifyCallback;
import com.wifiled.blelibrary.ble.callback.BleScanCallback;
import com.wifiled.blelibrary.ble.callback.BleStatusCallback;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.blelibrary.ble.model.ScanRecord;
import com.wifiled.blelibrary.ble.utils.ByteUtils;
import com.wifiled.blelibrary.ble.utils.UuidUtils;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.AutoSendParaCount;
import com.wifiled.ipixels.core.AdvertisementParser;
import com.wifiled.ipixels.core.BleManager;
import com.wifiled.ipixels.core.SendBleDataThread;
import com.wifiled.ipixels.core.send.SendResultCallback;
import com.wifiled.ipixels.ui.text.vo.SendResultMsg;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.HexExtensionsKt;
import kotlin.text.HexFormat;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* compiled from: BleManager.kt */
@Metadata(d1 = {"\u0000¥\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0018*\u0003*16\b\u0016\u0018\u0000 Z2\u00020\u0001:\u0002Z[B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\b\u0010\"\u001a\u0004\u0018\u00010\u0006J\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060$J\b\u0010%\u001a\u00020&H\u0002J\u0006\u0010'\u001a\u00020\u001fJ\u0006\u0010(\u001a\u00020\u001fJ\u000e\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020.J\u0010\u0010,\u001a\u00020\u001f2\b\u0010/\u001a\u0004\u0018\u00010\u0006J\u0006\u00103\u001a\u00020\u001fJ\u0010\u00104\u001a\u00020\u001f2\b\u0010/\u001a\u0004\u0018\u00010\u0006J0\u00108\u001a\u00020\u000e2\b\u0010/\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010;2\b\u0010<\u001a\u0004\u0018\u00010;2\b\u0010=\u001a\u0004\u0018\u00010>H\u0007J\u001a\u0010?\u001a\u00020\u000e2\b\u0010@\u001a\u0004\u0018\u0001092\b\u0010A\u001a\u0004\u0018\u000109J8\u0010B\u001a\u00020\u001f2\b\u0010/\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010;2\b\u0010<\u001a\u0004\u0018\u00010;2\b\u0010=\u001a\u0004\u0018\u00010>2\b\u0010C\u001a\u0004\u0018\u00010DJ0\u0010E\u001a\u00020\u001f2\u0006\u0010F\u001a\u0002092\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010G\u001a\u00020\u00142\u0006\u0010=\u001a\u00020>H\u0002J0\u0010H\u001a\u00020\u001f2\u0006\u0010/\u001a\u0002092\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020>2\u0006\u0010C\u001a\u00020DH\u0002J\u0018\u0010I\u001a\u00020\u001f2\u0006\u0010=\u001a\u00020>2\b\u0010C\u001a\u0004\u0018\u00010\u0010J\u0018\u0010J\u001a\u00020\u001f2\u0006\u0010K\u001a\u00020.2\u0006\u0010=\u001a\u00020>H\u0002J\u0010\u0010L\u001a\u00020\u001f2\u0006\u0010M\u001a\u00020\u001aH\u0002J\b\u0010N\u001a\u0004\u0018\u00010\u001aJ\b\u0010O\u001a\u00020\u001fH\u0002J\b\u0010P\u001a\u00020\u001fH\u0002J\u0006\u0010Q\u001a\u00020\u000eJ\u0006\u0010R\u001a\u00020\u0014J\u000e\u0010S\u001a\u00020\u001f2\u0006\u0010T\u001a\u00020\u0014J\u000e\u0010U\u001a\u00020\u001f2\u0006\u0010C\u001a\u00020\tJ\u0010\u0010V\u001a\u00020\u001f2\b\u0010C\u001a\u0004\u0018\u00010\tJ\u0006\u0010W\u001a\u00020\u001fJ\u0016\u0010U\u001a\u00020\u001f2\u0006\u0010X\u001a\u00020\f2\u0006\u0010C\u001a\u00020\tJ\u0012\u0010Y\u001a\u00020\u001f2\b\b\u0001\u0010X\u001a\u00020\fH\u0007R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\t0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u0014X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u0014X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u00020*X\u0082\u000e¢\u0006\u0004\n\u0002\u0010+R\u0010\u00100\u001a\u000201X\u0082\u000e¢\u0006\u0004\n\u0002\u00102R\u0010\u00105\u001a\u000206X\u0082\u000e¢\u0006\u0004\n\u0002\u00107¨\u0006\\"}, d2 = {"Lcom/wifiled/ipixels/core/BleManager;", "Landroidx/lifecycle/LifecycleObserver;", "<init>", "()V", "ble", "Lcom/wifiled/blelibrary/ble/Ble;", "Lcom/wifiled/blelibrary/ble/model/BleDevice;", "bleCallbacks", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/wifiled/ipixels/core/BleManager$BleCallback;", "callbackMap", "", "Landroidx/lifecycle/LifecycleOwner;", "isReady", "", "sendResultCallback", "Lcom/wifiled/ipixels/core/send/SendResultCallback;", "isMtuSetSuc", "Ljava/util/concurrent/atomic/AtomicBoolean;", "SEND_DATA_MAX_TIMEOUT", "", "getSEND_DATA_MAX_TIMEOUT", "()I", "perSendDataUnit", "getPerSendDataUnit", "mConnectedBtGatt", "Landroid/bluetooth/BluetoothGatt;", "mSendBleDataThread", "Lcom/wifiled/ipixels/core/SendBleDataThread;", "mBleMtu", "init", "", "context", "Landroid/content/Context;", "getConnectedDevice", "getConnectedDeviceList", "", "bleStatusCallback", "Lcom/wifiled/blelibrary/ble/callback/BleStatusCallback;", "scan", "stopScan", "bleScanCallback", "com/wifiled/ipixels/core/BleManager$bleScanCallback$1", "Lcom/wifiled/ipixels/core/BleManager$bleScanCallback$1;", "connect", "address", "", "device", "connectCallback", "com/wifiled/ipixels/core/BleManager$connectCallback$1", "Lcom/wifiled/ipixels/core/BleManager$connectCallback$1;", "disconnect", "notify", "bleNotifyCallback", "com/wifiled/ipixels/core/BleManager$bleNotifyCallback$1", "Lcom/wifiled/ipixels/core/BleManager$bleNotifyCallback$1;", "writeDataByBleSync", "Landroid/bluetooth/BluetoothDevice;", "serviceUUID", "Ljava/util/UUID;", "characteristicUUID", "data", "", "deviceEquals", "device1", "device2", "writeDataByBleAsync", "callback", "Lcom/wifiled/ipixels/core/OnWriteDataCallback;", "wakeupSendThread", "gatt", NotificationCompat.CATEGORY_STATUS, "addSendTask", "write", "uploadCrash", "errorStr", "setConnectedBtGatt", "gatIns", "getConnectedBtGatt", "startSendDataThread", "stopSendDataThread", "getReady", "getBleMtu", "setBleMtu", "mtu", "setBleCallback", "removeBleCallback", "removeSendCallBack", "lifecycleOwner", "onDestroy", "Companion", "BleCallback", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public class BleManager implements LifecycleObserver {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String TAG = "BleManager";
    private static BleManager instance;
    private Ble<BleDevice> ble;
    private boolean isReady;
    private BluetoothGatt mConnectedBtGatt;
    private SendBleDataThread mSendBleDataThread;
    private SendResultCallback sendResultCallback;
    private CopyOnWriteArrayList<BleCallback> bleCallbacks = new CopyOnWriteArrayList<>();
    private final Map<LifecycleOwner, BleCallback> callbackMap = new LinkedHashMap();
    private AtomicBoolean isMtuSetSuc = new AtomicBoolean(false);
    private final int SEND_DATA_MAX_TIMEOUT = 6000;
    private final int perSendDataUnit = 509;
    private int mBleMtu = 20;
    private BleManager$bleScanCallback$1 bleScanCallback = new BleScanCallback<BleDevice>() { // from class: com.wifiled.ipixels.core.BleManager$bleScanCallback$1
        @Override // com.wifiled.blelibrary.ble.callback.BleScanCallback
        public void onStart() {
            CopyOnWriteArrayList copyOnWriteArrayList;
            super.onStart();
            LogUtils.v("BleManager>>>[onStart]: ");
            copyOnWriteArrayList = BleManager.this.bleCallbacks;
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((BleManager.BleCallback) it.next()).onScanStart();
            }
        }

        @Override // com.wifiled.blelibrary.ble.callback.BleScanCallback
        public void onStop() {
            CopyOnWriteArrayList copyOnWriteArrayList;
            super.onStop();
            LogUtils.v("BleManager>>>[onStop]: ");
            copyOnWriteArrayList = BleManager.this.bleCallbacks;
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((BleManager.BleCallback) it.next()).onScanEnd();
            }
        }

        @Override // com.wifiled.blelibrary.ble.callback.BleScanCallback
        public void onLeScan(BleDevice device, int rssi, byte[] scanRecord) {
            Integer manufacturerId;
            CopyOnWriteArrayList copyOnWriteArrayList;
            CopyOnWriteArrayList copyOnWriteArrayList2;
            char c;
            Intrinsics.checkNotNullParameter(device, "device");
            String bleName = device.getBleName();
            if (bleName != null && StringsKt.contains$default((CharSequence) bleName, (CharSequence) "LED_BLE", false, 2, (Object) null)) {
                if (scanRecord != null) {
                    byte[] bArr = (scanRecord.length > 32 && scanRecord[27] == 84 && scanRecord[28] == 82 && scanRecord[29] == 0 && scanRecord[30] == 114) ? scanRecord : null;
                    if (bArr != null) {
                        c = 28;
                        device.setPid(StringsKt.takeLast("00" + ((int) bArr[33]), 2));
                        device.setCid(StringsKt.takeLast("0000" + ((int) bArr[31]) + HexExtensionsKt.toHexString$default((int) bArr[32], (HexFormat) null, 1, (Object) null), 4));
                        device.setAround(bArr[34]);
                    } else {
                        c = 28;
                    }
                    byte[] bArr2 = (scanRecord.length > 32 && scanRecord[23] == 84 && scanRecord[24] == 82 && scanRecord[25] == 0 && scanRecord[26] == 114) ? scanRecord : null;
                    if (bArr2 != null) {
                        device.setPid(StringsKt.takeLast("00" + ((int) bArr2[29]), 2));
                        device.setCid(StringsKt.takeLast("0000" + ((int) bArr2[27]) + HexExtensionsKt.toHexString$default((int) bArr2[c], (HexFormat) null, 1, (Object) null), 4));
                        device.setAround(bArr2[30]);
                        device.setSize(UByte.m3585constructorimpl(bArr2[31]) & UByte.MAX_VALUE);
                    }
                }
                copyOnWriteArrayList2 = BleManager.this.bleCallbacks;
                Iterator it = copyOnWriteArrayList2.iterator();
                while (it.hasNext()) {
                    ((BleManager.BleCallback) it.next()).onScan(device, rssi, scanRecord);
                }
                return;
            }
            if (scanRecord != null) {
                BleManager bleManager = BleManager.this;
                AdvertisementParser.ParsedAdvertisement parseAdvertisement = new AdvertisementParser().parseAdvertisement(scanRecord);
                byte[] manufacturerData = parseAdvertisement.getManufacturerData();
                if (manufacturerData == null || (manufacturerId = parseAdvertisement.getManufacturerId()) == null || manufacturerId.intValue() != 21586 || manufacturerData[1] != 114) {
                    return;
                }
                device.setPid(StringsKt.takeLast("00" + ((int) manufacturerData[4]), 2));
                device.setCid(StringsKt.takeLast("0000" + ((int) manufacturerData[2]) + HexExtensionsKt.toHexString$default((int) manufacturerData[3], (HexFormat) null, 1, (Object) null), 4));
                if (manufacturerData.length > 5) {
                    device.setAround(manufacturerData[5]);
                }
                if (manufacturerData.length > 6) {
                    device.setSize(UByte.m3585constructorimpl(manufacturerData[6]) & UByte.MAX_VALUE);
                }
                copyOnWriteArrayList = bleManager.bleCallbacks;
                Iterator it2 = copyOnWriteArrayList.iterator();
                while (it2.hasNext()) {
                    ((BleManager.BleCallback) it2.next()).onScan(device, rssi, scanRecord);
                }
            }
        }

        @Override // com.wifiled.blelibrary.ble.callback.BleScanCallback
        public void onScanFailed(int errorCode) {
            Ble ble;
            super.onScanFailed(errorCode);
            if (errorCode == -1) {
                ble = BleManager.this.ble;
                if (ble == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("ble");
                    ble = null;
                }
                ble.turnOnBlueToothNo();
            }
        }
    };
    private BleManager$connectCallback$1 connectCallback = new BleConnectCallback<BleDevice>() { // from class: com.wifiled.ipixels.core.BleManager$connectCallback$1
        @Override // com.wifiled.blelibrary.ble.callback.BleConnectCallback
        public void onConnectionChanged(BleDevice device) {
            CopyOnWriteArrayList copyOnWriteArrayList;
            Intrinsics.checkNotNullParameter(device, "device");
            copyOnWriteArrayList = BleManager.this.bleCallbacks;
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((BleManager.BleCallback) it.next()).onConnectionChanged(device);
            }
            if (device.isDisconnected()) {
                BleManager.this.isReady = false;
                BleManager.this.stopSendDataThread();
                if (AppConfig.INSTANCE.getConnectType() == 1) {
                    AppConfig.INSTANCE.setConnectType(-1);
                }
            }
        }

        @Override // com.wifiled.blelibrary.ble.callback.BleConnectCallback
        public void onConnectFailed(BleDevice device, int errorCode) {
            CopyOnWriteArrayList copyOnWriteArrayList;
            super.onConnectFailed((BleManager$connectCallback$1) device, errorCode);
            copyOnWriteArrayList = BleManager.this.bleCallbacks;
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((BleManager.BleCallback) it.next()).onConnectionException(device, errorCode);
            }
            AutoSendParaCount.INSTANCE.reset();
            if (AppConfig.INSTANCE.getConnectType() == 1) {
                AppConfig.INSTANCE.setConnectType(-1);
            }
        }

        @Override // com.wifiled.blelibrary.ble.callback.BleConnectCallback
        public void onReady(BleDevice device) {
            CopyOnWriteArrayList copyOnWriteArrayList;
            super.onReady((BleManager$connectCallback$1) device);
            Log.v("ruis", "BM-onReady");
            BleManager.this.isReady = true;
            copyOnWriteArrayList = BleManager.this.bleCallbacks;
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((BleManager.BleCallback) it.next()).onReady(device);
            }
            BleManager.this.notify(device);
        }

        @Override // com.wifiled.blelibrary.ble.callback.BleConnectCallback
        public void onServicesDiscovered(BleDevice device, BluetoothGatt gatt) {
            super.onServicesDiscovered((BleManager$connectCallback$1) device, gatt);
            BleManager bleManager = BleManager.this;
            Intrinsics.checkNotNull(gatt);
            bleManager.setConnectedBtGatt(gatt);
            BleManager.this.startSendDataThread();
        }
    };
    private BleManager$bleNotifyCallback$1 bleNotifyCallback = new BleNotifyCallback<BleDevice>() { // from class: com.wifiled.ipixels.core.BleManager$bleNotifyCallback$1
        /* JADX WARN: Removed duplicated region for block: B:38:0x00b5  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00c1  */
        /* JADX WARN: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
        @Override // com.wifiled.blelibrary.ble.callback.BleNotifyCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onChanged(com.wifiled.blelibrary.ble.model.BleDevice r7, android.bluetooth.BluetoothGattCharacteristic r8) {
            /*
                r6 = this;
                if (r8 == 0) goto L7
                byte[] r0 = r8.getValue()
                goto L8
            L7:
                r0 = 0
            L8:
                if (r0 == 0) goto Lc4
                com.wifiled.ipixels.core.BleManager r1 = com.wifiled.ipixels.core.BleManager.this
                java.lang.String r2 = com.wifiled.blelibrary.ble.utils.ByteUtils.toHexString(r0)
                java.lang.String r3 = com.wifiled.blelibrary.ble.utils.ByteUtils.toString(r0)
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.StringBuilder r2 = r4.append(r2)
                java.lang.String r4 = " 【"
                java.lang.StringBuilder r2 = r2.append(r4)
                java.lang.StringBuilder r2 = r2.append(r3)
                java.lang.String r3 = "】"
                java.lang.StringBuilder r2 = r2.append(r3)
                java.lang.String r2 = r2.toString()
                java.lang.String r3 = "BleManager receive mcu data>>>>>onChanged:"
                com.wifiled.blelibrary.ble.BleLog.d(r3, r2)
                java.util.concurrent.CopyOnWriteArrayList r2 = com.wifiled.ipixels.core.BleManager.access$getBleCallbacks$p(r1)
                java.lang.Iterable r2 = (java.lang.Iterable) r2
                java.util.Iterator r2 = r2.iterator()
            L41:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L51
                java.lang.Object r3 = r2.next()
                com.wifiled.ipixels.core.BleManager$BleCallback r3 = (com.wifiled.ipixels.core.BleManager.BleCallback) r3
                r3.onChanged(r7, r0, r8)
                goto L41
            L51:
                com.wifiled.ipixels.core.send.SendResultCallback r7 = com.wifiled.ipixels.core.BleManager.access$getSendResultCallback$p(r1)
                if (r7 == 0) goto L5a
                r7.onResult(r0)
            L5a:
                int r7 = r0.length
                r8 = 5
                r2 = 4
                r3 = 0
                if (r7 < r2) goto La3
                r7 = r0[r3]
                if (r7 != r8) goto La3
                r7 = r0[r2]
                if (r7 == 0) goto L8c
                r4 = 1
                if (r7 == r4) goto L82
                r5 = 3
                if (r7 == r5) goto L6f
                goto La3
            L6f:
                com.wifiled.ipixels.core.SendBleDataThread r7 = com.wifiled.ipixels.core.BleManager.access$getMSendBleDataThread$p(r1)
                if (r7 == 0) goto L78
                r7.clear12kDataQueue()
            L78:
                com.wifiled.ipixels.core.SendBleDataThread r7 = com.wifiled.ipixels.core.BleManager.access$getMSendBleDataThread$p(r1)
                if (r7 == 0) goto La4
                r7.clearTotalDataQueue()
                goto La4
            L82:
                com.wifiled.ipixels.core.SendBleDataThread r7 = com.wifiled.ipixels.core.BleManager.access$getMSendBleDataThread$p(r1)
                if (r7 == 0) goto La4
                r7.clear12kDataQueue()
                goto La4
            L8c:
                com.wifiled.ipixels.core.SendBleDataThread r7 = com.wifiled.ipixels.core.BleManager.access$getMSendBleDataThread$p(r1)
                if (r7 == 0) goto L95
                r7.clear12kDataQueue()
            L95:
                com.wifiled.ipixels.core.SendBleDataThread r7 = com.wifiled.ipixels.core.BleManager.access$getMSendBleDataThread$p(r1)
                if (r7 == 0) goto L9e
                r7.setAllDataResponse(r3)
            L9e:
                com.wifiled.ipixels.AutoSendParaCount r7 = com.wifiled.ipixels.AutoSendParaCount.INSTANCE
                r7.addCrcCount()
            La3:
                r4 = r3
            La4:
                int r7 = r0.length
                if (r7 < r2) goto Laf
                r7 = r0[r3]
                if (r7 != r8) goto Laf
                r7 = r0[r2]
                if (r7 == 0) goto Lc4
            Laf:
                com.wifiled.ipixels.core.SendBleDataThread r7 = com.wifiled.ipixels.core.BleManager.access$getMSendBleDataThread$p(r1)
                if (r7 == 0) goto Lb8
                r7.clear12kDataQueue()
            Lb8:
                com.wifiled.baselib.utils.TimeUtils.cancelCount()
                com.wifiled.ipixels.core.SendBleDataThread r7 = com.wifiled.ipixels.core.BleManager.access$getMSendBleDataThread$p(r1)
                if (r7 == 0) goto Lc4
                r7.setPer12kDataResponse(r4)
            Lc4:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.core.BleManager$bleNotifyCallback$1.onChanged(com.wifiled.blelibrary.ble.model.BleDevice, android.bluetooth.BluetoothGattCharacteristic):void");
        }

        @Override // com.wifiled.blelibrary.ble.callback.BleNotifyCallback
        public void onNotifySuccess(BleDevice device) {
            CopyOnWriteArrayList copyOnWriteArrayList;
            Ble ble;
            copyOnWriteArrayList = BleManager.this.bleCallbacks;
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((BleManager.BleCallback) it.next()).onNotifySuccess(device);
            }
            if (AppConfig.INSTANCE.getConnectType() == -1) {
                AppConfig.INSTANCE.setConnectType(1);
            }
            Log.v("ruis", "1111onNotifySuccess BM-onNotifySuccess setMTU");
            ble = BleManager.this.ble;
            if (ble == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ble");
                ble = null;
            }
            String bleAddress = device != null ? device.getBleAddress() : null;
            final BleManager bleManager = BleManager.this;
            ble.setMTU(bleAddress, 512, new BleMtuCallback<BleDevice>() { // from class: com.wifiled.ipixels.core.BleManager$bleNotifyCallback$1$onNotifySuccess$2
                @Override // com.wifiled.blelibrary.ble.callback.BleMtuCallback
                public void onMtuChanged(BleDevice device1, int mtu, int status) {
                    AtomicBoolean atomicBoolean;
                    CopyOnWriteArrayList copyOnWriteArrayList2;
                    Intrinsics.checkNotNullParameter(device1, "device1");
                    Log.v("ruis", "mtu == " + mtu + "    status=" + status + " ");
                    atomicBoolean = BleManager.this.isMtuSetSuc;
                    atomicBoolean.set(true);
                    BleManager bleManager2 = BleManager.this;
                    bleManager2.setBleMtu(bleManager2.getPerSendDataUnit());
                    copyOnWriteArrayList2 = BleManager.this.bleCallbacks;
                    Iterator it2 = copyOnWriteArrayList2.iterator();
                    while (it2.hasNext()) {
                        ((BleManager.BleCallback) it2.next()).onMtuChanged(device1, mtu, status);
                    }
                }
            });
        }

        @Override // com.wifiled.blelibrary.ble.callback.BleNotifyCallback
        public void onWriteSuccess(BleDevice bleDevice, BluetoothGattCharacteristic characteristic, int statu) {
            CopyOnWriteArrayList<BleManager.BleCallback> copyOnWriteArrayList;
            BluetoothDevice bluetoothDevice;
            Intrinsics.checkNotNull(characteristic);
            UUID uuid = characteristic.getUuid();
            BluetoothGattService service = characteristic.getService();
            UUID uuid2 = service != null ? service.getUuid() : null;
            if (bleDevice != null && (bluetoothDevice = bleDevice.getBluetoothDevice()) != null) {
                BleManager bleManager = BleManager.this;
                Intrinsics.checkNotNull(uuid2);
                Intrinsics.checkNotNull(uuid);
                byte[] value = characteristic.getValue();
                Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
                bleManager.wakeupSendThread(bluetoothDevice, uuid2, uuid, 0, value);
            }
            copyOnWriteArrayList = BleManager.this.bleCallbacks;
            for (BleManager.BleCallback bleCallback : copyOnWriteArrayList) {
                Intrinsics.checkNotNull(bleDevice);
                bleCallback.onWriteSuccess(bleDevice, characteristic, statu);
            }
        }
    };

    /* compiled from: BleManager.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\"\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u001a\u0010\u000e\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016J\u001a\u0010\u0011\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\u000bH\u0016J\u0012\u0010\u0013\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\"\u0010\u0014\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010\u0019\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J \u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000bH\u0016J \u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u000bH\u0016¨\u0006 "}, d2 = {"Lcom/wifiled/ipixels/core/BleManager$BleCallback;", "", "<init>", "()V", "onScanStart", "", "onScanEnd", "onScan", "device", "Lcom/wifiled/blelibrary/ble/model/BleDevice;", "rssi", "", "scanRecord", "", "onParsedData", "Lcom/wifiled/blelibrary/ble/model/ScanRecord;", "onConnectionChanged", "onConnectionException", "errorCode", "onConnectionTimeout", "onChanged", "data", "characteristic", "Landroid/bluetooth/BluetoothGattCharacteristic;", "onReady", "onNotifySuccess", "onMtuChanged", "device1", "mtu", NotificationCompat.CATEGORY_STATUS, "onWriteSuccess", "btdevice", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class BleCallback {
        public void onChanged(BleDevice device, byte[] data, BluetoothGattCharacteristic characteristic) {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(characteristic, "characteristic");
        }

        public void onConnectionChanged(BleDevice device) {
            Intrinsics.checkNotNullParameter(device, "device");
        }

        public void onConnectionException(BleDevice device, int errorCode) {
        }

        public void onConnectionTimeout(BleDevice device) {
        }

        public void onMtuChanged(BleDevice device1, int mtu, int status) {
            Intrinsics.checkNotNullParameter(device1, "device1");
        }

        public void onNotifySuccess(BleDevice device) {
        }

        public void onParsedData(BleDevice device, ScanRecord scanRecord) {
            Intrinsics.checkNotNullParameter(device, "device");
        }

        public void onReady(BleDevice device) {
        }

        public void onScan(BleDevice device, int rssi, byte[] scanRecord) {
            Intrinsics.checkNotNullParameter(device, "device");
        }

        public void onScanEnd() {
        }

        public void onScanStart() {
        }

        public void onWriteSuccess(BleDevice btdevice, BluetoothGattCharacteristic characteristic, int status) {
            Intrinsics.checkNotNullParameter(btdevice, "btdevice");
            Intrinsics.checkNotNullParameter(characteristic, "characteristic");
        }
    }

    public final int getSEND_DATA_MAX_TIMEOUT() {
        return this.SEND_DATA_MAX_TIMEOUT;
    }

    public final int getPerSendDataUnit() {
        return this.perSendDataUnit;
    }

    /* compiled from: BleManager.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u0007H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/wifiled/ipixels/core/BleManager$Companion;", "", "<init>", "()V", "TAG", "", "instance", "Lcom/wifiled/ipixels/core/BleManager;", "getInstance", "()Lcom/wifiled/ipixels/core/BleManager;", "get", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final BleManager getInstance() {
            if (BleManager.instance == null) {
                BleManager.instance = new BleManager();
            }
            return BleManager.instance;
        }

        @JvmStatic
        public final synchronized BleManager get() {
            BleManager companion;
            companion = getInstance();
            Intrinsics.checkNotNull(companion);
            return companion;
        }
    }

    @JvmStatic
    public static final synchronized BleManager get() {
        BleManager bleManager;
        synchronized (BleManager.class) {
            bleManager = INSTANCE.get();
        }
        return bleManager;
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Ble<BleDevice> create = Ble.options().setLogBleEnable(true).setThrowBleException(false).setLogTAG("AndroidBLE").setIgnoreRepeat(false).setConnectFailedRetryCount(3).setConnectTimeout(RetryPolicy.DEFAULT_RETRY_TIMEOUT_IN_MILLIS).setScanPeriod(15000L).setUuidService(UUID.fromString(UuidUtils.uuid16To128("00fa"))).setUuidWriteCha(UUID.fromString(UuidUtils.uuid16To128("fa02"))).create(context, new Ble.InitCallback() { // from class: com.wifiled.ipixels.core.BleManager$init$1
            @Override // com.wifiled.blelibrary.ble.Ble.InitCallback
            public void success() {
                BleLog.e("MainApplication", "init success");
            }

            @Override // com.wifiled.blelibrary.ble.Ble.InitCallback
            public void failed(int failedCode) {
                BleLog.e("MainApplication", "init fail：" + failedCode);
            }
        });
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        this.ble = create;
        if (create == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ble");
            create = null;
        }
        create.setBleStatusCallback(bleStatusCallback());
    }

    public final BleDevice getConnectedDevice() {
        List connectedDevices = Ble.getInstance().getConnectedDevices();
        Intrinsics.checkNotNull(connectedDevices);
        if (connectedDevices.isEmpty()) {
            return null;
        }
        return (BleDevice) connectedDevices.get(0);
    }

    public final List<BleDevice> getConnectedDeviceList() {
        List<BleDevice> connectedDevices = Ble.getInstance().getConnectedDevices();
        Intrinsics.checkNotNull(connectedDevices);
        return connectedDevices;
    }

    private final BleStatusCallback bleStatusCallback() {
        return new BleStatusCallback() { // from class: com.wifiled.ipixels.core.BleManager$$ExternalSyntheticLambda1
            @Override // com.wifiled.blelibrary.ble.callback.BleStatusCallback
            public final void onBluetoothStatusChanged(boolean z) {
                BleManager.bleStatusCallback$lambda$0(BleManager.this, z);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bleStatusCallback$lambda$0(BleManager bleManager, boolean z) {
        if (z) {
            bleManager.scan();
        } else {
            bleManager.stopScan();
        }
    }

    public final void scan() {
        try {
            Ble<BleDevice> ble = this.ble;
            if (ble == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ble");
                ble = null;
            }
            ble.startScan(this.bleScanCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void stopScan() {
        Ble<BleDevice> ble = this.ble;
        if (ble == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ble");
            ble = null;
        }
        ble.stopScan();
    }

    public final void connect(String address) {
        Intrinsics.checkNotNullParameter(address, "address");
        Ble<BleDevice> ble = this.ble;
        if (ble == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ble");
            ble = null;
        }
        ble.connect(address, this.connectCallback);
    }

    public final void connect(BleDevice device) {
        Ble<BleDevice> ble = this.ble;
        if (ble == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ble");
            ble = null;
        }
        ble.connect((Ble<BleDevice>) device, (BleConnectCallback<Ble<BleDevice>>) this.connectCallback);
    }

    public final void disconnect() {
        Ble<BleDevice> ble = null;
        try {
            List connectedDevices = Ble.getInstance().getConnectedDevices();
            if (connectedDevices == null || connectedDevices.size() <= 0) {
                return;
            }
            Ble<BleDevice> ble2 = this.ble;
            if (ble2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ble");
                ble2 = null;
            }
            ble2.disconnect((BleDevice) connectedDevices.get(0));
        } catch (NegativeArraySizeException unused) {
            Ble<BleDevice> ble3 = this.ble;
            if (ble3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ble");
            } else {
                ble = ble3;
            }
            ble.disconnectAll();
        } catch (Exception unused2) {
            Ble<BleDevice> ble4 = this.ble;
            if (ble4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ble");
            } else {
                ble = ble4;
            }
            ble.disconnectAll();
        }
    }

    public final void notify(BleDevice device) {
        Ble<BleDevice> ble = this.ble;
        if (ble == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ble");
            ble = null;
        }
        ble.startNotify(device, this.bleNotifyCallback);
    }

    public final boolean writeDataByBleSync(BluetoothDevice device, UUID serviceUUID, UUID characteristicUUID, byte[] data) {
        BluetoothGattCharacteristic characteristic;
        boolean z = false;
        if (this.mConnectedBtGatt != null && device != null && 1 != device.getType() && serviceUUID != null && characteristicUUID != null && data != null && data.length != 0) {
            BluetoothGatt bluetoothGatt = this.mConnectedBtGatt;
            Intrinsics.checkNotNull(bluetoothGatt);
            BluetoothGattService service = bluetoothGatt.getService(serviceUUID);
            if (service == null || (characteristic = service.getCharacteristic(characteristicUUID)) == null) {
                return false;
            }
            try {
                characteristic.setValue(data);
                BluetoothGatt bluetoothGatt2 = this.mConnectedBtGatt;
                Intrinsics.checkNotNull(bluetoothGatt2);
                z = bluetoothGatt2.writeCharacteristic(characteristic);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!z) {
                Log.d("BleManager", "debug ");
            }
        }
        return z;
    }

    public final boolean deviceEquals(BluetoothDevice device1, BluetoothDevice device2) {
        return (device1 == null || device2 == null || !Intrinsics.areEqual(device1.getAddress(), device2.getAddress())) ? false : true;
    }

    public final void writeDataByBleAsync(BluetoothDevice device, UUID serviceUUID, UUID characteristicUUID, byte[] data, OnWriteDataCallback callback) {
        Intrinsics.checkNotNull(device);
        Intrinsics.checkNotNull(serviceUUID);
        Intrinsics.checkNotNull(characteristicUUID);
        Intrinsics.checkNotNull(data);
        Intrinsics.checkNotNull(callback);
        addSendTask(device, serviceUUID, characteristicUUID, data, callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void wakeupSendThread(BluetoothDevice gatt, UUID serviceUUID, UUID characteristicUUID, int status, byte[] data) {
        if (this.mSendBleDataThread != null) {
            SendBleDataThread.BleSendTask bleSendTask = new SendBleDataThread.BleSendTask(gatt, serviceUUID, characteristicUUID, data, null);
            bleSendTask.setStatus(status);
            SendBleDataThread sendBleDataThread = this.mSendBleDataThread;
            Intrinsics.checkNotNull(sendBleDataThread);
            sendBleDataThread.wakeupSendThread(bleSendTask);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void addSendTask(android.bluetooth.BluetoothDevice r8, java.util.UUID r9, java.util.UUID r10, byte[] r11, com.wifiled.ipixels.core.OnWriteDataCallback r12) {
        /*
            r7 = this;
            com.wifiled.ipixels.core.SendBleDataThread r0 = r7.mSendBleDataThread
            if (r0 == 0) goto L28
            android.bluetooth.BluetoothGatt r0 = r7.mConnectedBtGatt
            if (r0 == 0) goto L28
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            android.bluetooth.BluetoothDevice r0 = r0.getDevice()
            boolean r0 = r7.deviceEquals(r8, r0)
            if (r0 == 0) goto L28
            com.wifiled.ipixels.core.SendBleDataThread r1 = r7.mSendBleDataThread
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            r2 = r8
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r12
            boolean r8 = r1.addSendTask(r2, r3, r4, r5, r6)
            r1 = r2
            r2 = r3
            r3 = r4
            r0 = r6
            goto L2e
        L28:
            r1 = r8
            r2 = r9
            r3 = r10
            r5 = r11
            r0 = r12
            r8 = 0
        L2e:
            if (r8 != 0) goto L34
            r4 = 0
            r0.onBleResult(r1, r2, r3, r4, r5)
        L34:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.core.BleManager.addSendTask(android.bluetooth.BluetoothDevice, java.util.UUID, java.util.UUID, byte[], com.wifiled.ipixels.core.OnWriteDataCallback):void");
    }

    public final void write(final byte[] data, SendResultCallback callback) {
        Intrinsics.checkNotNullParameter(data, "data");
        Ble<BleDevice> ble = this.ble;
        if (ble == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ble");
            ble = null;
        }
        List<BleDevice> connectedDevices = ble.getConnectedDevices();
        Intrinsics.checkNotNullExpressionValue(connectedDevices, "getConnectedDevices(...)");
        if (connectedDevices.isEmpty() || !this.isReady) {
            return;
        }
        this.sendResultCallback = callback;
        BluetoothGatt bluetoothGatt = this.mConnectedBtGatt;
        Intrinsics.checkNotNull(bluetoothGatt);
        writeDataByBleAsync(bluetoothGatt.getDevice(), UUID.fromString(UuidUtils.uuid16To128("00fa")), UUID.fromString(UuidUtils.uuid16To128("fa02")), data, new OnWriteDataCallback() { // from class: com.wifiled.ipixels.core.BleManager$$ExternalSyntheticLambda0
            @Override // com.wifiled.ipixels.core.OnWriteDataCallback
            public final void onBleResult(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, boolean z, byte[] bArr) {
                BleManager.write$lambda$1(BleManager.this, data, bluetoothDevice, uuid, uuid2, z, bArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void write$lambda$1(BleManager bleManager, byte[] bArr, BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, boolean z, byte[] bArr2) {
        if (z) {
            SendResultCallback sendResultCallback = bleManager.sendResultCallback;
            if (sendResultCallback != null) {
                Intrinsics.checkNotNull(bArr2);
                sendResultCallback.onResult(bArr2);
                return;
            }
            return;
        }
        if (z) {
            throw new NoWhenBranchMatchedException();
        }
        Intrinsics.checkNotNull(bArr2);
        Log.e("BleManager", "#1.1# write false ".concat(new String(bArr2, Charsets.UTF_8)));
        if (StringsKt.contains$default((CharSequence) new String(bArr2, Charsets.UTF_8), (CharSequence) "cur12k_no_answer", false, 2, (Object) null)) {
            SendResultCallback sendResultCallback2 = bleManager.sendResultCallback;
            if (sendResultCallback2 != null) {
                sendResultCallback2.onError(100110);
            }
        } else if (StringsKt.contains$default((CharSequence) new String(bArr2, Charsets.UTF_8), (CharSequence) "allsend_no_answer", false, 2, (Object) null)) {
            SendResultCallback sendResultCallback3 = bleManager.sendResultCallback;
            if (sendResultCallback3 != null) {
                sendResultCallback3.onError(100111);
            }
        } else if (StringsKt.contains$default((CharSequence) new String(bArr2, Charsets.UTF_8), (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null)) {
            SendResultCallback sendResultCallback4 = bleManager.sendResultCallback;
            if (sendResultCallback4 != null) {
                sendResultCallback4.onError(100112);
            }
        } else {
            EventBus.getDefault().post(new SendResultMsg(new byte[]{Byte.MAX_VALUE}));
            SendResultCallback sendResultCallback5 = bleManager.sendResultCallback;
            if (sendResultCallback5 != null) {
                sendResultCallback5.onError(10010);
            }
        }
        LogUtils.file("发送数据错误， 错误数据---" + bArr);
    }

    private final void uploadCrash(String errorStr, byte[] data) {
        String str = "BleManage send Error\n data = " + new String(errorStr) + "\n SendData = " + ByteUtils.toHexString(data) + "\n ";
        ApiService request = NetWorkManager.INSTANCE.getRequest();
        String packageName = AppUtils.getPackageName(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
        String appMetaData = AppUtils.getAppMetaData(App.INSTANCE.getContext(), "HEATON_CHANNEL");
        Intrinsics.checkNotNullExpressionValue(appMetaData, "getAppMetaData(...)");
        String versionName = AppUtils.getVersionName(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(versionName, "getVersionName(...)");
        String valueOf = String.valueOf(AppUtils.getVersionCode(App.INSTANCE.getContext()));
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        byte[] bytes = str.getBytes(UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        String encodeToString = Base64.encodeToString(bytes, 0);
        Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(...)");
        ApiService.uploadCrash$default(request, packageName, appMetaData, null, null, null, null, versionName, valueOf, encodeToString, 60, null).enqueue(new Callback<CloudRes>() { // from class: com.wifiled.ipixels.core.BleManager$uploadCrash$1
            @Override // retrofit2.Callback
            public void onFailure(Call<CloudRes> call, Throwable t) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t, "t");
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<CloudRes> call, Response<CloudRes> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                Log.e("ruis", "发送错误，上报日志到服务器");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setConnectedBtGatt(BluetoothGatt gatIns) {
        synchronized (this) {
            this.mConnectedBtGatt = gatIns;
            Unit unit = Unit.INSTANCE;
        }
    }

    /* renamed from: getConnectedBtGatt, reason: from getter */
    public final BluetoothGatt getMConnectedBtGatt() {
        return this.mConnectedBtGatt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startSendDataThread() {
        LogUtils.v("开始发送数据线程");
        if (this.mSendBleDataThread == null) {
            SendBleDataThread sendBleDataThread = new SendBleDataThread(this, new OnThreadStateListener() { // from class: com.wifiled.ipixels.core.BleManager$startSendDataThread$1
                @Override // com.wifiled.ipixels.core.OnThreadStateListener
                public void onStart(long id, String name) {
                }

                @Override // com.wifiled.ipixels.core.OnThreadStateListener
                public void onEnd(long id, String name) {
                    BleManager.this.mSendBleDataThread = null;
                }
            });
            this.mSendBleDataThread = sendBleDataThread;
            Intrinsics.checkNotNull(sendBleDataThread);
            sendBleDataThread.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopSendDataThread() {
        LogUtils.v("停止发送数据线程");
        SendBleDataThread sendBleDataThread = this.mSendBleDataThread;
        if (sendBleDataThread != null) {
            Intrinsics.checkNotNull(sendBleDataThread);
            sendBleDataThread.stopThread();
            this.mSendBleDataThread = null;
        }
        byte[] bytes = "dev disconnect".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        SendResultCallback sendResultCallback = this.sendResultCallback;
        if (sendResultCallback != null) {
            sendResultCallback.onResult(bytes);
        }
    }

    /* renamed from: getReady, reason: from getter */
    public final boolean getIsReady() {
        return this.isReady;
    }

    /* renamed from: getBleMtu, reason: from getter */
    public final int getMBleMtu() {
        return this.mBleMtu;
    }

    public final void setBleMtu(int mtu) {
        this.mBleMtu = mtu;
    }

    public final void setBleCallback(BleCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (this.bleCallbacks.contains(callback)) {
            return;
        }
        this.bleCallbacks.add(callback);
    }

    public final void removeBleCallback(BleCallback callback) {
        if (this.bleCallbacks.contains(callback)) {
            this.bleCallbacks.remove(callback);
            LogUtils.v("BleManager>>>[removeBleCallback]: ");
        }
    }

    public final void removeSendCallBack() {
        this.sendResultCallback = null;
    }

    public final void setBleCallback(LifecycleOwner lifecycleOwner, BleCallback callback) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (this.bleCallbacks.contains(callback)) {
            return;
        }
        this.bleCallbacks.add(callback);
        this.callbackMap.put(lifecycleOwner, callback);
        lifecycleOwner.getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public final void onDestroy(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        if (this.callbackMap.containsKey(lifecycleOwner)) {
            this.bleCallbacks.remove(this.callbackMap.remove(lifecycleOwner));
            lifecycleOwner.getLifecycle().removeObserver(this);
            LogUtils.v("BleManager>>>[onDestroy]: ");
        }
    }
}
