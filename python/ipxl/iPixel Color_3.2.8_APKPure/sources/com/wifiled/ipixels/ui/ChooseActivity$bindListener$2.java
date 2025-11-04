package com.wifiled.ipixels.ui;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Lifecycle;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.tiro.jlotalibrary.exposed.BleStateCallBack;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.gameview.utils.GridLineView;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.BleManager2;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.data.CloudManager;
import com.wifiled.ipixels.ui.channel.ChannelIndex;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ChooseActivity.kt */
@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\"\u0010\n\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u000f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J \u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H\u0016¨\u0006\u0014"}, d2 = {"com/wifiled/ipixels/ui/ChooseActivity$bindListener$2", "Lcom/wifiled/ipixels/core/BleManager2$BleCallback;", "onScan", "", "device", "Lcom/wifiled/blelibrary/ble/model/BleDevice;", "rssi", "", "scanRecord", "", "onChanged", "data", "characteristic", "Landroid/bluetooth/BluetoothGattCharacteristic;", "onConnectionChanged", "onNotifySuccess", "onMtuChanged", "device1", "mtu", NotificationCompat.CATEGORY_STATUS, "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChooseActivity$bindListener$2 extends BleManager2.BleCallback {
    final /* synthetic */ ChooseActivity this$0;

    ChooseActivity$bindListener$2(ChooseActivity chooseActivity) {
        this.this$0 = chooseActivity;
    }

    @Override // com.wifiled.ipixels.core.BleManager2.BleCallback
    public void onScan(BleDevice device, int rssi, byte[] scanRecord) {
        List list;
        Intrinsics.checkNotNullParameter(device, "device");
        super.onScan(device, rssi, scanRecord);
        ChooseActivity chooseActivity = this.this$0;
        String str = device.getCid() + device.getPid();
        list = chooseActivity.mBlueFilter;
        if (list != null) {
            list.contains(str);
        }
    }

    @Override // com.wifiled.ipixels.core.BleManager2.BleCallback
    public void onChanged(BleDevice device, byte[] data, BluetoothGattCharacteristic characteristic) {
        BleStateCallBack bleStateCallBack;
        BleStateCallBack bleStateCallBack2;
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(characteristic, "characteristic");
        super.onChanged(device, data, characteristic);
        bleStateCallBack = this.this$0.bleStateCallBack2;
        if (bleStateCallBack != null) {
            bleStateCallBack2 = this.this$0.bleStateCallBack2;
            Intrinsics.checkNotNull(bleStateCallBack2);
            BluetoothGatt bluetoothGatt = this.this$0.getBleRequest2().getBluetoothGatt(device != null ? device.getBleAddress() : null);
            Intrinsics.checkNotNullExpressionValue(bluetoothGatt, "getBluetoothGatt(...)");
            bleStateCallBack2.onBleData(bluetoothGatt, characteristic);
        }
    }

    @Override // com.wifiled.ipixels.core.BleManager2.BleCallback
    public void onConnectionChanged(BleDevice device) {
        BleStateCallBack bleStateCallBack;
        BleStateCallBack bleStateCallBack2;
        Intrinsics.checkNotNullParameter(device, "device");
        super.onConnectionChanged(device);
        LogUtils.file("ruis", "onConnectionChanged");
        if (device.isDisconnected()) {
            bleStateCallBack = this.this$0.bleStateCallBack2;
            if (bleStateCallBack == null || this.this$0.getBleRequest2().getBluetoothGatt(device.getBleAddress()) == null) {
                return;
            }
            bleStateCallBack2 = this.this$0.bleStateCallBack2;
            Intrinsics.checkNotNull(bleStateCallBack2);
            BluetoothGatt bluetoothGatt = this.this$0.getBleRequest2().getBluetoothGatt(device.getBleAddress());
            Intrinsics.checkNotNullExpressionValue(bluetoothGatt, "getBluetoothGatt(...)");
            bleStateCallBack2.onBleConnection(bluetoothGatt, 0);
        }
    }

    @Override // com.wifiled.ipixels.core.BleManager2.BleCallback
    public void onNotifySuccess(BleDevice device) {
        super.onNotifySuccess(device);
        this.this$0.setSetDescriptor2(true);
    }

    @Override // com.wifiled.ipixels.core.BleManager2.BleCallback
    public void onMtuChanged(final BleDevice device1, int mtu, int status) {
        BleStateCallBack bleStateCallBack;
        BleStateCallBack bleStateCallBack2;
        BleStateCallBack bleStateCallBack3;
        Intrinsics.checkNotNullParameter(device1, "device1");
        if (Lifecycle.State.RESUMED == this.this$0.getLifecycle().getState()) {
            bleStateCallBack = this.this$0.bleStateCallBack2;
            if (bleStateCallBack != null) {
                bleStateCallBack2 = this.this$0.bleStateCallBack2;
                Intrinsics.checkNotNull(bleStateCallBack2);
                bleStateCallBack2.onBleMtuChanged(mtu, status);
                bleStateCallBack3 = this.this$0.bleStateCallBack2;
                Intrinsics.checkNotNull(bleStateCallBack3);
                BluetoothGatt bluetoothGatt = this.this$0.getBleRequest2().getBluetoothGatt(device1.getBleAddress());
                Intrinsics.checkNotNullExpressionValue(bluetoothGatt, "getBluetoothGatt(...)");
                bleStateCallBack3.onBleConnection(bluetoothGatt, 2);
            }
            this.this$0.setSetMtu2(true);
            this.this$0.mtu_2 = mtu;
            String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
            Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
            boolean contains$default = StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "zh", false, 2, (Object) null);
            AppConfig appConfig = AppConfig.INSTANCE;
            String cid = device1.getCid();
            Intrinsics.checkNotNullExpressionValue(cid, "getCid(...)");
            appConfig.setCid(cid);
            AppConfig appConfig2 = AppConfig.INSTANCE;
            String pid = device1.getPid();
            Intrinsics.checkNotNullExpressionValue(pid, "getPid(...)");
            appConfig2.setPid(pid);
            CloudManager cloudManager = CloudManager.INSTANCE;
            ChooseActivity chooseActivity = this.this$0;
            String cid2 = device1.getCid();
            Intrinsics.checkNotNullExpressionValue(cid2, "getCid(...)");
            String pid2 = device1.getPid();
            Intrinsics.checkNotNullExpressionValue(pid2, "getPid(...)");
            cloudManager.uploadInformation(chooseActivity, cid2, pid2);
            ChooseActivity chooseActivity2 = this.this$0;
            Observable<Long> observeOn = Observable.interval(5L, TimeUnit.SECONDS).take(1L).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            final ChooseActivity chooseActivity3 = this.this$0;
            final Function1 function1 = new Function1() { // from class: com.wifiled.ipixels.ui.ChooseActivity$bindListener$2$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit onMtuChanged$lambda$1;
                    onMtuChanged$lambda$1 = ChooseActivity$bindListener$2.onMtuChanged$lambda$1(ChooseActivity.this, (Long) obj);
                    return onMtuChanged$lambda$1;
                }
            };
            chooseActivity2.disposable2 = observeOn.subscribe(new Consumer() { // from class: com.wifiled.ipixels.ui.ChooseActivity$bindListener$2$$ExternalSyntheticLambda3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Function1.this.invoke(obj);
                }
            });
            SendCore sendCore = SendCore.INSTANCE;
            final ChooseActivity chooseActivity4 = this.this$0;
            sendCore.getLedType2(contains$default ? (byte) 1 : (byte) 0, new Function1() { // from class: com.wifiled.ipixels.ui.ChooseActivity$bindListener$2$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit onMtuChanged$lambda$5;
                    onMtuChanged$lambda$5 = ChooseActivity$bindListener$2.onMtuChanged$lambda$5(BleDevice.this, chooseActivity4, (SendCore.CallbackBuilder) obj);
                    return onMtuChanged$lambda$5;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onMtuChanged$lambda$1(ChooseActivity chooseActivity, Long l) {
        Disposable disposable;
        ToastUtil.show(chooseActivity.getString(R.string.device_connect_error) + "(10021)");
        BleManager2.INSTANCE.get().disconnect();
        disposable = chooseActivity.disposable2;
        Intrinsics.checkNotNull(disposable);
        disposable.dispose();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onMtuChanged$lambda$5(final BleDevice bleDevice, final ChooseActivity chooseActivity, SendCore.CallbackBuilder getLedType2) {
        Intrinsics.checkNotNullParameter(getLedType2, "$this$getLedType2");
        getLedType2.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.ChooseActivity$bindListener$2$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onMtuChanged$lambda$5$lambda$4;
                onMtuChanged$lambda$5$lambda$4 = ChooseActivity$bindListener$2.onMtuChanged$lambda$5$lambda$4(BleDevice.this, chooseActivity, (byte[]) obj);
                return onMtuChanged$lambda$5$lambda$4;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onMtuChanged$lambda$5$lambda$4(BleDevice bleDevice, final ChooseActivity chooseActivity, byte[] result) {
        Disposable disposable;
        Disposable disposable2;
        Disposable disposable3;
        String str;
        boolean z;
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.length >= 9) {
            LogUtils.file("getLedType result" + ((int) result[4]));
            byte b = result[4];
            if (b == -127) {
                AppConfig.INSTANCE.setLedType(2);
            } else if (b == Byte.MIN_VALUE) {
                AppConfig.INSTANCE.setLedType(0);
            } else if (b == -126) {
                AppConfig.INSTANCE.setLedType(4);
            } else if (b == -125) {
                AppConfig.INSTANCE.setLedType(3);
            } else if (b == -124) {
                AppConfig.INSTANCE.setLedType(1);
            } else if (b == -123) {
                AppConfig.INSTANCE.setLedType(5);
            } else if (b == -122) {
                AppConfig.INSTANCE.setLedType(6);
                AppConfig.INSTANCE.setLedHasWifi(false);
                AppConfig.INSTANCE.setLedTextSize(32);
            } else if (b == -121) {
                AppConfig.INSTANCE.setLedType(7);
            } else if (b == -120) {
                AppConfig.INSTANCE.setLedType(8);
            } else if (b == -119) {
                AppConfig.INSTANCE.setLedType(9);
                AppConfig.INSTANCE.setLedTextSize(24);
            } else if (b == -118) {
                AppConfig.INSTANCE.setLedType(10);
                AppConfig.INSTANCE.setLedTextSize(32);
            } else if (b == -117) {
                AppConfig.INSTANCE.setLedType(11);
                AppConfig.INSTANCE.setLedTextSize(32);
            } else if (b == -116) {
                AppConfig.INSTANCE.setLedType(12);
                AppConfig.INSTANCE.setLedTextSize(32);
            } else if (b == -115) {
                AppConfig.INSTANCE.setLedType(13);
                AppConfig.INSTANCE.setLedTextSize(32);
            } else if (b == -114) {
                AppConfig.INSTANCE.setLedType(14);
                AppConfig.INSTANCE.setLedTextSize(32);
            } else if (b == -113) {
                AppConfig.INSTANCE.setLedType(15);
                AppConfig.INSTANCE.setLedTextSize(32);
            } else if (b == -112) {
                AppConfig.INSTANCE.setLedType(16);
                AppConfig.INSTANCE.setLedTextSize(32);
            } else if (b == -111) {
                AppConfig.INSTANCE.setLedType(17);
                AppConfig.INSTANCE.setLedTextSize(32);
            } else if (b == -110) {
                AppConfig.INSTANCE.setLedType(18);
                AppConfig.INSTANCE.setLedTextSize(32);
            } else {
                AppConfig.INSTANCE.setLedType(0);
            }
            AppConfig.INSTANCE.setBledOn(true);
            if (result.length >= 11) {
                AppConfig.INSTANCE.setPwdFlag(result[10]);
            }
            if (AppConfig.INSTANCE.getPwdFlag() == 1) {
                if (!SPUtils.getInstance().contains(bleDevice.getBleAddress())) {
                    str = "";
                } else {
                    str = SPUtils.getInstance().getString(bleDevice.getBleAddress());
                    Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
                }
                if (!StringsKt.isBlank(str)) {
                    z = chooseActivity.isAuto;
                    if (z) {
                        chooseActivity.verifyPwd(str);
                    }
                }
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.ChooseActivity$bindListener$2$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit onMtuChanged$lambda$5$lambda$4$lambda$3;
                        onMtuChanged$lambda$5$lambda$4$lambda$3 = ChooseActivity$bindListener$2.onMtuChanged$lambda$5$lambda$4$lambda$3(ChooseActivity.this);
                        return onMtuChanged$lambda$5$lambda$4$lambda$3;
                    }
                });
            }
            ChannelIndex.INSTANCE.initChannelData();
            GridLineView.INSTANCE.setLedType(AppConfig.INSTANCE.getLedType());
            disposable = chooseActivity.disposable2;
            if (disposable != null) {
                disposable2 = chooseActivity.disposable2;
                Intrinsics.checkNotNull(disposable2);
                if (!disposable2.isDisposed()) {
                    disposable3 = chooseActivity.disposable2;
                    Intrinsics.checkNotNull(disposable3);
                    disposable3.dispose();
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onMtuChanged$lambda$5$lambda$4$lambda$3(ChooseActivity chooseActivity) {
        chooseActivity.showPwdDialog();
        return Unit.INSTANCE;
    }
}
