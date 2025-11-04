package com.wifiled.ipixels.ui.dialog;

import android.bluetooth.BluetoothGattCharacteristic;
import android.util.Base64;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.retrofit.ApiService;
import com.wifiled.baselib.retrofit.NetWorkManager;
import com.wifiled.baselib.retrofit.entity.CloudRes;
import com.wifiled.baselib.utils.AppUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.BleManager;
import com.wifiled.ipixels.ui.adapter.ConnectAdapter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* compiled from: ConnectDialog.kt */
@Metadata(d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\"\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001a\u0010\r\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000e\u001a\u00020\tH\u0016J\u0012\u0010\u000f\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\u0010\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\"\u0010\u0011\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J \u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\tH\u0016¨\u0006\u0019"}, d2 = {"com/wifiled/ipixels/ui/dialog/ConnectDialog$bleCallback$1", "Lcom/wifiled/ipixels/core/BleManager$BleCallback;", "onScanStart", "", "onScanEnd", "onScan", "device", "Lcom/wifiled/blelibrary/ble/model/BleDevice;", "rssi", "", "scanRecord", "", "onConnectionChanged", "onConnectionException", "errorCode", "onConnectionTimeout", "onReady", "onChanged", "data", "characteristic", "Landroid/bluetooth/BluetoothGattCharacteristic;", "onMtuChanged", "device1", "mtu", NotificationCompat.CATEGORY_STATUS, "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ConnectDialog$bleCallback$1 extends BleManager.BleCallback {
    final /* synthetic */ ConnectDialog this$0;

    ConnectDialog$bleCallback$1(ConnectDialog connectDialog) {
        this.this$0 = connectDialog;
    }

    @Override // com.wifiled.ipixels.core.BleManager.BleCallback
    public void onScanStart() {
        super.onScanStart();
        this.this$0.startAnim();
    }

    @Override // com.wifiled.ipixels.core.BleManager.BleCallback
    public void onScanEnd() {
        super.onScanEnd();
        this.this$0.stopAnim();
    }

    @Override // com.wifiled.ipixels.core.BleManager.BleCallback
    public void onScan(BleDevice device, int rssi, byte[] scanRecord) {
        Intrinsics.checkNotNullParameter(device, "device");
        this.this$0.addDevice(device);
    }

    @Override // com.wifiled.ipixels.core.BleManager.BleCallback
    public void onConnectionChanged(BleDevice device) {
        int i;
        ConnectAdapter connectAdapter;
        boolean z;
        int i2;
        int i3;
        int i4;
        ConnectAdapter connectAdapter2;
        Intrinsics.checkNotNullParameter(device, "device");
        ConnectAdapter connectAdapter3 = null;
        if (device.isConnected()) {
            connectAdapter2 = this.this$0.adapter;
            if (connectAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                connectAdapter3 = connectAdapter2;
            }
            connectAdapter3.notifyDataSetChanged();
        } else if (device.isConnecting()) {
            this.this$0.mNewState = 0;
            ConnectDialog connectDialog = this.this$0;
            String string = connectDialog.getContext().getString(R.string.msg_bt_connecting);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            ConnectDialog.showLoadingDialogPlus$default(connectDialog, true, string, false, 4, null);
        } else if (device.isDisconnected()) {
            i = this.this$0.mPrevState;
            if (i != 1) {
                z = this.this$0.mIsManualOperation;
                if (!z) {
                    this.this$0.hideLoadingDialog(1000L);
                }
            }
            this.this$0.mIsManualOperation = false;
            connectAdapter = this.this$0.adapter;
            if (connectAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                connectAdapter3 = connectAdapter;
            }
            connectAdapter3.notifyDataSetChanged();
        }
        i2 = this.this$0.mPrevState;
        i3 = this.this$0.mNewState;
        if (i2 != i3) {
            ConnectDialog connectDialog2 = this.this$0;
            i4 = connectDialog2.mNewState;
            connectDialog2.mPrevState = i4;
        }
    }

    @Override // com.wifiled.ipixels.core.BleManager.BleCallback
    public void onConnectionException(BleDevice device, int errorCode) {
        LogUtils.vTag("ruis", "onConnectionException1" + errorCode + "}");
        String str = "onConnectionException\n deviceMac = " + (device != null ? device.getBleAddress() : null) + "\n deviceName = " + (device != null ? device.getBleName() : null) + "\n errorCode = " + errorCode + "\n";
        this.this$0.hideLoadingDialog(0L);
        ApiService request = NetWorkManager.INSTANCE.getRequest();
        String packageName = AppUtils.getPackageName(this.this$0.getContext());
        Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
        String appMetaData = AppUtils.getAppMetaData(App.INSTANCE.getContext(), "HEATON_CHANNEL");
        Intrinsics.checkNotNullExpressionValue(appMetaData, "getAppMetaData(...)");
        String versionName = AppUtils.getVersionName(this.this$0.getContext());
        Intrinsics.checkNotNullExpressionValue(versionName, "getVersionName(...)");
        String valueOf = String.valueOf(AppUtils.getVersionCode(this.this$0.getContext()));
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        byte[] bytes = str.getBytes(UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        String encodeToString = Base64.encodeToString(bytes, 0);
        Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(...)");
        ApiService.uploadCrash$default(request, packageName, appMetaData, null, null, null, null, versionName, valueOf, encodeToString, 60, null).enqueue(new Callback<CloudRes>() { // from class: com.wifiled.ipixels.ui.dialog.ConnectDialog$bleCallback$1$onConnectionException$1
            @Override // retrofit2.Callback
            public void onFailure(Call<CloudRes> call, Throwable t) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t, "t");
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<CloudRes> call, Response<CloudRes> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
            }
        });
    }

    @Override // com.wifiled.ipixels.core.BleManager.BleCallback
    public void onConnectionTimeout(BleDevice device) {
        final ConnectDialog connectDialog = this.this$0;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.dialog.ConnectDialog$bleCallback$1$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onConnectionTimeout$lambda$0;
                onConnectionTimeout$lambda$0 = ConnectDialog$bleCallback$1.onConnectionTimeout$lambda$0(ConnectDialog.this);
                return onConnectionTimeout$lambda$0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onConnectionTimeout$lambda$0(ConnectDialog connectDialog) {
        connectDialog.hideLoadingDialog(0L);
        ToastUtil.show(connectDialog.getContext().getString(R.string.msg_bt_connect_fail) + "（$10024）");
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.ipixels.core.BleManager.BleCallback
    public void onReady(BleDevice device) {
        this.this$0.mNewState = 1;
    }

    @Override // com.wifiled.ipixels.core.BleManager.BleCallback
    public void onChanged(BleDevice device, byte[] data, BluetoothGattCharacteristic characteristic) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(characteristic, "characteristic");
        super.onChanged(device, data, characteristic);
        LogUtils.vTag("ruis", "onChanged1");
    }

    @Override // com.wifiled.ipixels.core.BleManager.BleCallback
    public void onMtuChanged(BleDevice device1, int mtu, int status) {
        Intrinsics.checkNotNullParameter(device1, "device1");
        LogUtils.vTag("ruis", "onMtuChanged1");
        final ConnectDialog connectDialog = this.this$0;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.dialog.ConnectDialog$bleCallback$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onMtuChanged$lambda$1;
                onMtuChanged$lambda$1 = ConnectDialog$bleCallback$1.onMtuChanged$lambda$1(ConnectDialog.this);
                return onMtuChanged$lambda$1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onMtuChanged$lambda$1(ConnectDialog connectDialog) {
        connectDialog.hideLoadingDialog(0L);
        ToastUtil.show(connectDialog.getContext().getString(R.string.msg_bt_connect_suc));
        return Unit.INSTANCE;
    }
}
