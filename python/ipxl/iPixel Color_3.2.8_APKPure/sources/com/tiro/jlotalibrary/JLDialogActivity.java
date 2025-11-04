package com.tiro.jlotalibrary;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import com.jieli.jl_bt_ota.interfaces.BtEventCallback;
import com.jieli.jl_bt_ota.interfaces.IUpgradeCallback;
import com.jieli.jl_bt_ota.model.BluetoothOTAConfigure;
import com.jieli.jl_bt_ota.model.base.BaseError;
import com.tiro.jlotalibrary.exposed.BleStateCallBack;
import com.tiro.jlotalibrary.exposed.JLOTA;
import com.tiro.jlotalibrary.util.LogUtil;
import com.wifiled.baselib.app.Constance;
import com.wifiled.baselib.utils.SPUtils;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;

/* compiled from: JLDialogActivity.kt */
@Metadata(d1 = {"\u0000\u0087\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001 \u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\u0012\u0010\u0019\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u0006\u0010\u001c\u001a\u00020\u0003J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0002J\u0018\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J\u0018\u0010'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020&2\u0006\u0010%\u001a\u00020&H\u0016J\u001a\u0010)\u001a\u00020\u00162\u0006\u0010#\u001a\u00020$2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010,\u001a\u00020\u0016H\u0016J\u001a\u0010-\u001a\u00020\u00162\b\u0010.\u001a\u0004\u0018\u00010\u00142\u0006\u0010/\u001a\u000200H\u0016J\u0018\u00101\u001a\u00020\u00162\u0006\u00102\u001a\u00020&2\u0006\u00103\u001a\u000204H\u0016J\b\u00105\u001a\u00020\u0016H\u0016J\b\u00106\u001a\u00020\u0016H\u0016J\u0012\u00107\u001a\u00020\u00162\b\u00108\u001a\u0004\u0018\u000109H\u0016J\b\u0010:\u001a\u00020\u0016H\u0014J\b\u0010;\u001a\u00020\u0016H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010!¨\u0006<"}, d2 = {"Lcom/tiro/jlotalibrary/JLDialogActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/jieli/jl_bt_ota/interfaces/IUpgradeCallback;", "Lcom/tiro/jlotalibrary/exposed/BleStateCallBack;", "<init>", "()V", "warn", "Landroid/widget/TextView;", "tip", "tip1", "cancel", "Landroid/view/View;", "sure", "jobConnect", "Lkotlinx/coroutines/Job;", "jobAuthDevice", "otaManager", "Lcom/tiro/jlotalibrary/JLOTAManager;", "queue", "Ljava/util/concurrent/LinkedBlockingQueue;", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "attachBaseContext", "newBase", "Landroid/content/Context;", "registerBleCallBack", "initView", "initOTA", "btEventCallback", "com/tiro/jlotalibrary/JLDialogActivity$btEventCallback$1", "Lcom/tiro/jlotalibrary/JLDialogActivity$btEventCallback$1;", "onBleConnection", "gatt", "Landroid/bluetooth/BluetoothGatt;", NotificationCompat.CATEGORY_STATUS, "", "onBleMtuChanged", "block", "onBleData", "characteristic", "Landroid/bluetooth/BluetoothGattCharacteristic;", "onStartOTA", "onNeedReconnect", "addr", "isNewReconnectWay", "", "onProgress", "type", "progress", "", "onStopOTA", "onCancelOTA", "onError", "error", "Lcom/jieli/jl_bt_ota/model/base/BaseError;", "onDestroy", "onBackPressed", "jlotalibrary_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JLDialogActivity extends AppCompatActivity implements IUpgradeCallback, BleStateCallBack {
    private View cancel;
    private Job jobAuthDevice;
    private Job jobConnect;
    private JLOTAManager otaManager;
    private View sure;
    private TextView tip;
    private TextView tip1;
    private TextView warn;
    private final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(1);
    private final JLDialogActivity$btEventCallback$1 btEventCallback = new BtEventCallback() { // from class: com.tiro.jlotalibrary.JLDialogActivity$btEventCallback$1
        @Override // com.jieli.jl_bt_ota.interfaces.BtEventCallback, com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
        public void onConnection(BluetoothDevice device, int status) {
            Job job;
            JLOTAManager jLOTAManager;
            LogUtil.INSTANCE.d("必须等待库回调连接成功才可以开始OTA操作 " + status);
            if (status == 1) {
                job = JLDialogActivity.this.jobAuthDevice;
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                jLOTAManager = JLDialogActivity.this.otaManager;
                if (jLOTAManager == null || !jLOTAManager.isOTA()) {
                    BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(JLDialogActivity.this), null, null, new JLDialogActivity$btEventCallback$1$onConnection$1(JLDialogActivity.this, null), 3, null);
                }
            }
        }

        @Override // com.jieli.jl_bt_ota.interfaces.BtEventCallback, com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
        public void onMandatoryUpgrade(BluetoothDevice device) {
            LogUtil.INSTANCE.d("需要强制升级的回调");
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(JLDialogActivity.this), null, null, new JLDialogActivity$btEventCallback$1$onMandatoryUpgrade$1(JLDialogActivity.this, null), 3, null);
            super.onMandatoryUpgrade(device);
        }
    };

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jl_dialog);
        getWindow().getDecorView().setVisibility(8);
        initView();
        initOTA();
        if (getIntent().getBooleanExtra("force", false) && this.queue.isEmpty()) {
            LogUtil.INSTANCE.d("升级操作纳入队列");
            this.queue.add("up_data");
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        if (newBase == null) {
            super.attachBaseContext(newBase);
            return;
        }
        String string = newBase.getSharedPreferences(SPUtils.FILE_NAME, 0).getString(Constance.SP.LANGUAGE, Locale.getDefault().getLanguage());
        Locale locale = Locale.getDefault();
        if (StringsKt.equals(string, "en", true)) {
            locale = Locale.ENGLISH;
        } else if (string != null && StringsKt.contains((CharSequence) string, (CharSequence) "zh", true)) {
            locale = Locale.SIMPLIFIED_CHINESE;
        }
        Configuration configuration = newBase.getResources().getConfiguration();
        configuration.setLocale(locale);
        super.attachBaseContext(newBase.createConfigurationContext(configuration));
    }

    public final BleStateCallBack registerBleCallBack() {
        return this;
    }

    private final void initView() {
        String stringExtra;
        JLOTA.INSTANCE.getBleSOTAData().registerBleCallback(this);
        View findViewById = findViewById(R.id.tip);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.tip = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.tip1);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.tip1 = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.warn);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.warn = (TextView) findViewById3;
        View findViewById4 = findViewById(R.id.cancel_button);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.cancel = findViewById4;
        View findViewById5 = findViewById(R.id.sure_button);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.sure = findViewById5;
        TextView textView = (TextView) findViewById(R.id.old_version);
        TextView textView2 = (TextView) findViewById(R.id.new_version);
        TextView textView3 = (TextView) findViewById(R.id.release_notes);
        TextView textView4 = this.tip;
        View view = null;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tip");
            textView4 = null;
        }
        textView4.setVisibility(4);
        TextView textView5 = this.tip1;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tip1");
            textView5 = null;
        }
        textView5.setVisibility(4);
        TextView textView6 = this.warn;
        if (textView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("warn");
            textView6 = null;
        }
        textView6.setVisibility(0);
        View view2 = this.cancel;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cancel");
            view2 = null;
        }
        view2.setVisibility(0);
        View view3 = this.sure;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sure");
            view3 = null;
        }
        view3.setVisibility(0);
        String string = getString(R.string.ota_current_firmware_version);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String format = String.format(string, Arrays.copyOf(new Object[]{getIntent().getStringExtra("version_old")}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        textView.setText(format);
        String string2 = getString(R.string.ota_new_firmware_version);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String format2 = String.format(string2, Arrays.copyOf(new Object[]{getIntent().getStringExtra("version_no")}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
        textView2.setText(format2);
        String string3 = getString(R.string.ota_release_notes);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        String string4 = getString(R.string.ota_release_notes);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        if (StringsKt.contains$default((CharSequence) string4, (CharSequence) "更新说明", false, 2, (Object) null)) {
            stringExtra = getIntent().getStringExtra("desc_cn");
        } else {
            stringExtra = getIntent().getStringExtra("desc_en");
        }
        String format3 = String.format(string3, Arrays.copyOf(new Object[]{stringExtra}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
        textView3.setText(format3);
        View view4 = this.cancel;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cancel");
            view4 = null;
        }
        view4.setOnClickListener(new View.OnClickListener() { // from class: com.tiro.jlotalibrary.JLDialogActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view5) {
                JLDialogActivity.initView$lambda$1(JLDialogActivity.this, view5);
            }
        });
        View view5 = this.sure;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sure");
        } else {
            view = view5;
        }
        view.setOnClickListener(new View.OnClickListener() { // from class: com.tiro.jlotalibrary.JLDialogActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view6) {
                JLDialogActivity.initView$lambda$2(JLDialogActivity.this, view6);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$1(JLDialogActivity jLDialogActivity, View view) {
        BluetoothDevice device;
        BluetoothGatt gatt = JLOTA.INSTANCE.getBleSOTAData().getGatt();
        if (gatt != null && (device = gatt.getDevice()) != null) {
            JLOTA.INSTANCE.getChache().add(device.getAddress());
        }
        jLDialogActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$2(JLDialogActivity jLDialogActivity, View view) {
        if (jLDialogActivity.queue.isEmpty()) {
            LogUtil.INSTANCE.d("升级操作纳入队列");
            jLDialogActivity.queue.add("up_data");
        }
        LogUtil.INSTANCE.d("点击升级");
    }

    private final void initOTA() {
        Job launch$default;
        JLOTAManager jLOTAManager;
        BluetoothOTAConfigure bluetoothOption;
        BluetoothOTAConfigure createDefault = BluetoothOTAConfigure.createDefault();
        createDefault.setPriority(0).setUseAuthDevice(true).setBleIntervalMs(500).setTimeoutMs(3000).setMtu(JLOTA.INSTANCE.getBleSOTAData().getMtu()).setNeedChangeMtu(false).setUseReconnect(true);
        JLOTAManager jLOTAManager2 = new JLOTAManager(this, new Function1() { // from class: com.tiro.jlotalibrary.JLDialogActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit initOTA$lambda$3;
                initOTA$lambda$3 = JLDialogActivity.initOTA$lambda$3((BluetoothDevice) obj);
                return initOTA$lambda$3;
            }
        }, new Function1() { // from class: com.tiro.jlotalibrary.JLDialogActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit initOTA$lambda$4;
                initOTA$lambda$4 = JLDialogActivity.initOTA$lambda$4((BluetoothDevice) obj);
                return initOTA$lambda$4;
            }
        }, new Function1() { // from class: com.tiro.jlotalibrary.JLDialogActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit initOTA$lambda$5;
                initOTA$lambda$5 = JLDialogActivity.initOTA$lambda$5(JLDialogActivity.this, (BaseError) obj);
                return initOTA$lambda$5;
            }
        }, new Function4() { // from class: com.tiro.jlotalibrary.JLDialogActivity$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                boolean initOTA$lambda$6;
                initOTA$lambda$6 = JLDialogActivity.initOTA$lambda$6((UUID) obj, (UUID) obj2, (BluetoothDevice) obj3, (byte[]) obj4);
                return Boolean.valueOf(initOTA$lambda$6);
            }
        });
        this.otaManager = jLOTAManager2;
        jLOTAManager2.configure(createDefault);
        JLOTAManager jLOTAManager3 = this.otaManager;
        if (jLOTAManager3 != null && (bluetoothOption = jLOTAManager3.getBluetoothOption()) != null) {
            bluetoothOption.setFirmwareFileData(JLOTA.INSTANCE.getData());
        }
        JLOTAManager jLOTAManager4 = this.otaManager;
        if (jLOTAManager4 != null) {
            jLOTAManager4.setMtu(JLOTA.INSTANCE.getBleSOTAData().getMtu());
        }
        JLOTAManager jLOTAManager5 = this.otaManager;
        if (jLOTAManager5 != null) {
            jLOTAManager5.registerBluetoothCallback(this.btEventCallback);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new JLDialogActivity$initOTA$5(this, null), 3, null);
        this.jobAuthDevice = launch$default;
        BluetoothGatt gatt = JLOTA.INSTANCE.getBleSOTAData().getGatt();
        if (gatt == null || (jLOTAManager = this.otaManager) == null) {
            return;
        }
        jLOTAManager.onBleConnection(gatt, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initOTA$lambda$3(BluetoothDevice bluetoothDevice) {
        LogUtil.INSTANCE.d("开始重连连设备");
        JLOTA.INSTANCE.getBleSOTAData().connect(bluetoothDevice != null ? bluetoothDevice.getAddress() : null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initOTA$lambda$4(BluetoothDevice bluetoothDevice) {
        JLOTA.INSTANCE.getBleSOTAData().disconnect(bluetoothDevice);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initOTA$lambda$5(JLDialogActivity jLDialogActivity, BaseError baseError) {
        BuildersKt__BuildersKt.runBlocking$default(null, new JLDialogActivity$initOTA$3$1(baseError, jLDialogActivity, null), 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initOTA$lambda$6(UUID uuidService, UUID uuidWrite, BluetoothDevice bluetoothDevice, byte[] item) {
        Intrinsics.checkNotNullParameter(uuidService, "uuidService");
        Intrinsics.checkNotNullParameter(uuidWrite, "uuidWrite");
        Intrinsics.checkNotNullParameter(item, "item");
        return JLOTA.INSTANCE.getBleSOTAData().sendData(uuidService, uuidWrite, bluetoothDevice, item);
    }

    @Override // com.tiro.jlotalibrary.exposed.BleStateCallBack
    public void onBleConnection(BluetoothGatt gatt, int status) {
        Job job;
        Intrinsics.checkNotNullParameter(gatt, "gatt");
        Log.v("ruis", "JLDialogActivity -onBleConnection");
        if (status == 1 && (job = this.jobConnect) != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        JLOTAManager jLOTAManager = this.otaManager;
        if (jLOTAManager != null) {
            jLOTAManager.onBleConnection(gatt, status);
        }
    }

    @Override // com.tiro.jlotalibrary.exposed.BleStateCallBack
    public void onBleMtuChanged(int block, int status) {
        Log.v("ruis", "JLDialogActivity -onBleMtuChanged");
        JLOTAManager jLOTAManager = this.otaManager;
        if (jLOTAManager != null) {
            jLOTAManager.onBleMtuChanged(block, status);
        }
    }

    @Override // com.tiro.jlotalibrary.exposed.BleStateCallBack
    public void onBleData(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
        Intrinsics.checkNotNullParameter(gatt, "gatt");
        Log.v("ruis", "JLDialogActivity -onBleData");
        JLOTAManager jLOTAManager = this.otaManager;
        if (jLOTAManager != null) {
            jLOTAManager.onBleData(gatt, characteristic);
        }
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
    public void onStartOTA() {
        TextView textView = this.tip;
        View view = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tip");
            textView = null;
        }
        textView.setVisibility(0);
        TextView textView2 = this.tip1;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tip1");
            textView2 = null;
        }
        textView2.setVisibility(0);
        TextView textView3 = this.warn;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("warn");
            textView3 = null;
        }
        textView3.setVisibility(4);
        View view2 = this.cancel;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cancel");
            view2 = null;
        }
        view2.setVisibility(4);
        View view3 = this.sure;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sure");
        } else {
            view = view3;
        }
        view.setVisibility(4);
        LogUtil.INSTANCE.d("开始");
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
    public void onNeedReconnect(String addr, boolean isNewReconnectWay) {
        Job launch$default;
        BluetoothOTAConfigure bluetoothOption;
        LogUtil logUtil = LogUtil.INSTANCE;
        JLOTAManager jLOTAManager = this.otaManager;
        logUtil.d("回调需要回连的设备地址" + isNewReconnectWay + " ---" + addr + " --" + ((jLOTAManager == null || (bluetoothOption = jLOTAManager.getBluetoothOption()) == null) ? null : Boolean.valueOf(bluetoothOption.isUseReconnect())));
        TextView textView = this.tip;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tip");
            textView = null;
        }
        textView.setText(getString(R.string.ota_restarting));
        launch$default = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new JLDialogActivity$onNeedReconnect$1(addr, null), 3, null);
        this.jobConnect = launch$default;
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
    public void onProgress(int type, float progress) {
        String str;
        TextView textView = this.tip;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tip");
            textView = null;
        }
        if (type == 0) {
            String string = getString(R.string.ota_verification);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String format = String.format(string, Arrays.copyOf(new Object[]{((int) progress) + " %"}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            str = format;
        } else {
            String string2 = getString(R.string.ota_mcu_upata);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            String format2 = String.format(string2, Arrays.copyOf(new Object[]{((int) progress) + " %"}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
            str = format2;
        }
        textView.setText(str);
        LogUtil.INSTANCE.d("0 --- 下载loader  1 --- 升级固件\n " + type + " --- " + progress);
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
    public void onStopOTA() {
        LogUtil.INSTANCE.d("升级完成");
        TextView textView = this.tip;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tip");
            textView = null;
        }
        textView.setText(getString(R.string.ota_complete));
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new JLDialogActivity$onStopOTA$1(this, null), 3, null);
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
    public void onCancelOTA() {
        LogUtil.INSTANCE.d("回调OTA升级被取消");
        LogUtil.INSTANCE.e("双备份OTA才允许OTA流程取消");
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
    public void onError(BaseError error) {
        LogUtil.INSTANCE.e("--- " + (error != null ? Integer.valueOf(error.getCode()) : null) + " 发生错误" + (error != null ? error.getMessage() : null));
        TextView textView = this.tip;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tip");
            textView = null;
        }
        textView.setText(getString(R.string.ota_same_upgrade_file));
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new JLDialogActivity$onError$1(this, null), 3, null);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        JLOTAManager jLOTAManager = this.otaManager;
        if (jLOTAManager != null) {
            jLOTAManager.unregisterBluetoothCallback(this.btEventCallback);
        }
        JLOTA.INSTANCE.getBleSOTAData().unregisterBleCallback();
        JLOTAManager jLOTAManager2 = this.otaManager;
        if (jLOTAManager2 != null) {
            jLOTAManager2.release();
        }
        super.onDestroy();
    }
}
