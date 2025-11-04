package com.wifiled.ipixels.ui.riding;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.hardware.SensorEvent;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.wifiled.baselib.base.BaseActivity;
import com.wifiled.baselib.permission.IPermission;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.blelibrary.ble.queue.reconnect.DefaultReConnectHandler;
import com.wifiled.ipixels.IRemoteCallback;
import com.wifiled.ipixels.IRemoteService;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.utils.ClickFilter;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DebugKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: RidingModeActivity.kt */
@Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003*\u000225\u0018\u0000 h2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002hiB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010(\u001a\u00020\tH\u0014J\b\u0010)\u001a\u00020*H\u0014J\b\u0010+\u001a\u00020*H\u0014J\b\u0010.\u001a\u00020*H\u0014J\b\u00107\u001a\u00020*H\u0002J\b\u00108\u001a\u00020*H\u0002J\u0010\u00109\u001a\u00020*2\u0006\u0010:\u001a\u00020;H\u0007J\u0010\u0010<\u001a\u00020*2\u0006\u0010=\u001a\u00020>H\u0002J\u0010\u0010G\u001a\u00020*2\u0006\u0010H\u001a\u00020IH\u0002J\b\u0010J\u001a\u00020*H\u0002J\b\u0010K\u001a\u00020*H\u0002J\b\u0010L\u001a\u00020*H\u0014J.\u0010M\u001a\u00020*2\u0006\u0010N\u001a\u00020\t2\b\b\u0002\u0010?\u001a\u00020\t2\b\b\u0002\u0010D\u001a\u00020\t2\b\b\u0002\u0010O\u001a\u00020\u000bH\u0002J\b\u0010T\u001a\u00020*H\u0002J\u0012\u0010[\u001a\u00020*2\b\u0010\\\u001a\u0004\u0018\u00010]H\u0016J\u001c\u0010^\u001a\u00020\u000b2\b\u0010\\\u001a\u0004\u0018\u00010]2\b\u0010=\u001a\u0004\u0018\u00010_H\u0017J \u0010`\u001a\u00020\u000b2\u0006\u0010a\u001a\u00020]2\u0006\u0010b\u001a\u00020\t2\u0006\u0010c\u001a\u00020\tH\u0002J\u0010\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020gH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u000202X\u0082\u000e¢\u0006\u0004\n\u0002\u00103R\u0010\u00104\u001a\u000205X\u0082\u000e¢\u0006\u0004\n\u0002\u00106R\u001a\u0010?\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u001a\u0010D\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010A\"\u0004\bF\u0010CR\u0010\u0010P\u001a\u0004\u0018\u00010QX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020SX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010U\u001a\u00020VX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010Z¨\u0006j"}, d2 = {"Lcom/wifiled/ipixels/ui/riding/RidingModeActivity;", "Lcom/wifiled/baselib/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "Landroid/view/View$OnTouchListener;", "<init>", "()V", "lastLatitude", "", "mValueX", "", "autoMode", "", "autoFlash", "isSpeedSel", "ridingViewModel", "Lcom/wifiled/ipixels/ui/riding/RidingViewModel;", "getRidingViewModel", "()Lcom/wifiled/ipixels/ui/riding/RidingViewModel;", "ridingViewModel$delegate", "Lkotlin/Lazy;", "distance", "", "serviceForegroundIntent", "Landroid/content/Intent;", "mIsBindService", "iv_auto", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "iv_cancel", "iv_flash", "iv_go", "iv_left", "iv_right", "iv_sos", "iv_speed", "iv_stop", "timer", "Landroid/widget/Chronometer;", "tv_distance", "Landroid/widget/TextView;", "tv_speed", "layoutId", "initView", "", "bindData", "myBroadcast", "Landroid/content/BroadcastReceiver;", "onDestroy", "mService", "Lcom/wifiled/ipixels/IRemoteService;", "mCallback", "com/wifiled/ipixels/ui/riding/RidingModeActivity$mCallback$1", "Lcom/wifiled/ipixels/ui/riding/RidingModeActivity$mCallback$1;", "connection", "com/wifiled/ipixels/ui/riding/RidingModeActivity$connection$1", "Lcom/wifiled/ipixels/ui/riding/RidingModeActivity$connection$1;", "initTimer", "initViewModel", "onEvent", "model", "Lcom/wifiled/ipixels/ui/riding/RideModel;", "setSensorRender", NotificationCompat.CATEGORY_EVENT, "Landroid/hardware/SensorEvent;", "speed", "getSpeed", "()I", "setSpeed", "(I)V", "decimal", "getDecimal", "setDecimal", "setLocationRender", "location", "Landroid/location/Location;", "initRidingView", "init", "bindListener", "sendSportData", PlayerFinal.PLAYER_MODE, DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "singleThreadExecutor", "Ljava/util/concurrent/ScheduledExecutorService;", "runSend", "Ljava/lang/Runnable;", "scheduleNext", "time", "", "getTime", "()J", "setTime", "(J)V", "onClick", "v", "Landroid/view/View;", "onTouch", "Landroid/view/MotionEvent;", "isTouchPointInView", "view", "x", "y", "changTVsize", "Landroid/text/SpannableString;", "value", "", "Companion", "RemoteCallback", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RidingModeActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String[] PERMISSION_LOCATION_NEW;
    private boolean autoFlash;
    private boolean autoMode;
    private int decimal;
    private float distance;
    private boolean isSpeedSel;
    private CustomImageView iv_auto;
    private CustomImageView iv_cancel;
    private CustomImageView iv_flash;
    private CustomImageView iv_go;
    private CustomImageView iv_left;
    private CustomImageView iv_right;
    private CustomImageView iv_sos;
    private CustomImageView iv_speed;
    private CustomImageView iv_stop;
    private boolean mIsBindService;
    private IRemoteService mService;
    private int mValueX;

    /* renamed from: ridingViewModel$delegate, reason: from kotlin metadata */
    private final Lazy ridingViewModel;
    private Intent serviceForegroundIntent;
    private int speed;
    private long time;
    private Chronometer timer;
    private TextView tv_distance;
    private TextView tv_speed;
    private final double[] lastLatitude = new double[2];
    private BroadcastReceiver myBroadcast = new BroadcastReceiver() { // from class: com.wifiled.ipixels.ui.riding.RidingModeActivity$myBroadcast$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            String action = intent.getAction();
            if (Intrinsics.areEqual(action, "android.intent.action.SCREEN_OFF")) {
                Log.i("trans", "screen off");
            } else if (Intrinsics.areEqual(action, "android.intent.action.SCREEN_ON")) {
                Log.i("trans", "screen on");
            }
        }
    };
    private RidingModeActivity$mCallback$1 mCallback = new RemoteCallback() { // from class: com.wifiled.ipixels.ui.riding.RidingModeActivity$mCallback$1
        @Override // com.wifiled.ipixels.ui.riding.RidingModeActivity.RemoteCallback, com.wifiled.ipixels.IRemoteCallback
        public void onSensorEvent(int valueZ) {
            boolean z;
            super.onSensorEvent(valueZ);
            z = RidingModeActivity.this.autoMode;
            if (z) {
                if (valueZ < 0) {
                    if (System.currentTimeMillis() - RidingModeActivity.this.getTime() > 1900) {
                        LogUtils.logi(">>>[当前方向]: 向-右 " + valueZ, new Object[0]);
                        RidingModeActivity.this.setTime(System.currentTimeMillis());
                        RidingModeActivity.sendSportData$default(RidingModeActivity.this, 1, 0, 0, true, 6, null);
                        return;
                    }
                    return;
                }
                if (valueZ <= 0 || System.currentTimeMillis() - RidingModeActivity.this.getTime() <= 1900) {
                    return;
                }
                LogUtils.logi(">>>[当前方向]: 向-左" + valueZ, new Object[0]);
                RidingModeActivity.this.setTime(System.currentTimeMillis());
                RidingModeActivity.sendSportData$default(RidingModeActivity.this, 0, 0, 0, true, 6, null);
            }
        }
    };
    private RidingModeActivity$connection$1 connection = new ServiceConnection() { // from class: com.wifiled.ipixels.ui.riding.RidingModeActivity$connection$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            IRemoteService iRemoteService;
            RidingModeActivity$mCallback$1 ridingModeActivity$mCallback$1;
            try {
                RidingModeActivity.this.mService = IRemoteService.Stub.asInterface(service);
                iRemoteService = RidingModeActivity.this.mService;
                if (iRemoteService != null) {
                    ridingModeActivity$mCallback$1 = RidingModeActivity.this.mCallback;
                    iRemoteService.registerCallback(ridingModeActivity$mCallback$1);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            IRemoteService iRemoteService;
            RidingModeActivity$mCallback$1 ridingModeActivity$mCallback$1;
            try {
                iRemoteService = RidingModeActivity.this.mService;
                if (iRemoteService != null) {
                    ridingModeActivity$mCallback$1 = RidingModeActivity.this.mCallback;
                    iRemoteService.unregisterCallback(ridingModeActivity$mCallback$1);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            RidingModeActivity.this.mService = null;
        }
    };
    private ScheduledExecutorService singleThreadExecutor = Executors.newSingleThreadScheduledExecutor();
    private final Runnable runSend = new Runnable() { // from class: com.wifiled.ipixels.ui.riding.RidingModeActivity$$ExternalSyntheticLambda4
        @Override // java.lang.Runnable
        public final void run() {
            RidingModeActivity.runSend$lambda$5(RidingModeActivity.this);
        }
    };

    /* compiled from: RidingModeActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/wifiled/ipixels/ui/riding/RidingModeActivity$RemoteCallback;", "Lcom/wifiled/ipixels/IRemoteCallback$Stub;", "<init>", "()V", "onSensorEvent", "", "valueZ", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class RemoteCallback extends IRemoteCallback.Stub {
        @Override // com.wifiled.ipixels.IRemoteCallback
        public void onSensorEvent(int valueZ) {
        }
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_riding;
    }

    /* compiled from: RidingModeActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lcom/wifiled/ipixels/ui/riding/RidingModeActivity$Companion;", "", "<init>", "()V", "PERMISSION_LOCATION_NEW", "", "", "getPERMISSION_LOCATION_NEW", "()[Ljava/lang/String;", "[Ljava/lang/String;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String[] getPERMISSION_LOCATION_NEW() {
            return RidingModeActivity.PERMISSION_LOCATION_NEW;
        }
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.wifiled.ipixels.ui.riding.RidingModeActivity$mCallback$1] */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.wifiled.ipixels.ui.riding.RidingModeActivity$connection$1] */
    public RidingModeActivity() {
        final RidingModeActivity ridingModeActivity = this;
        final Function0 function0 = null;
        this.ridingViewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(RidingViewModel.class), new Function0<ViewModelStore>() { // from class: com.wifiled.ipixels.ui.riding.RidingModeActivity$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return ComponentActivity.this.getViewModelStore();
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.wifiled.ipixels.ui.riding.RidingModeActivity$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return ComponentActivity.this.getDefaultViewModelProviderFactory();
            }
        }, new Function0<CreationExtras>() { // from class: com.wifiled.ipixels.ui.riding.RidingModeActivity$special$$inlined$viewModels$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                return (function02 == null || (creationExtras = (CreationExtras) function02.invoke()) == null) ? ridingModeActivity.getDefaultViewModelCreationExtras() : creationExtras;
            }
        });
    }

    static {
        String[] strArr;
        if (Build.VERSION.SDK_INT >= 33) {
            strArr = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.NEARBY_WIFI_DEVICES", "android.permission.ACCESS_FINE_LOCATION"};
        } else {
            strArr = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
        }
        PERMISSION_LOCATION_NEW = strArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RidingViewModel getRidingViewModel() {
        return (RidingViewModel) this.ridingViewModel.getValue();
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void initView() {
        super.initView();
        View findViewById = findViewById(R.id.iv_auto);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.iv_auto = (CustomImageView) findViewById;
        View findViewById2 = findViewById(R.id.iv_cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.iv_cancel = (CustomImageView) findViewById2;
        View findViewById3 = findViewById(R.id.iv_flash);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.iv_flash = (CustomImageView) findViewById3;
        View findViewById4 = findViewById(R.id.iv_go);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.iv_go = (CustomImageView) findViewById4;
        View findViewById5 = findViewById(R.id.iv_left);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.iv_left = (CustomImageView) findViewById5;
        View findViewById6 = findViewById(R.id.iv_right);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.iv_right = (CustomImageView) findViewById6;
        View findViewById7 = findViewById(R.id.iv_sos);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.iv_sos = (CustomImageView) findViewById7;
        View findViewById8 = findViewById(R.id.iv_speed);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.iv_speed = (CustomImageView) findViewById8;
        View findViewById9 = findViewById(R.id.iv_stop);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.iv_stop = (CustomImageView) findViewById9;
        View findViewById10 = findViewById(R.id.timer);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
        this.timer = (Chronometer) findViewById10;
        View findViewById11 = findViewById(R.id.tv_distance);
        Intrinsics.checkNotNullExpressionValue(findViewById11, "findViewById(...)");
        this.tv_distance = (TextView) findViewById11;
        View findViewById12 = findViewById(R.id.tv_speed);
        Intrinsics.checkNotNullExpressionValue(findViewById12, "findViewById(...)");
        this.tv_speed = (TextView) findViewById12;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindData() {
        try {
            init();
            initRidingView();
            initTimer();
            EventBus.getDefault().register(this);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            if (Build.VERSION.SDK_INT > 33) {
                registerReceiver(this.myBroadcast, intentFilter, 4);
            } else {
                registerReceiver(this.myBroadcast, intentFilter);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.show(getString(R.string.open_bt_relative));
        }
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        ScheduledExecutorService scheduledExecutorService = this.singleThreadExecutor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
        this.singleThreadExecutor = null;
        if (this.mIsBindService) {
            try {
                unbindService(this.connection);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        try {
            IRemoteService iRemoteService = this.mService;
            if (iRemoteService != null) {
                iRemoteService.unregisterCallback(this.mCallback);
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        this.mService = null;
        unregisterReceiver(this.myBroadcast);
        EventBus.getDefault().unregister(this);
    }

    private final void initTimer() {
        Chronometer chronometer = this.timer;
        Chronometer chronometer2 = null;
        if (chronometer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timer");
            chronometer = null;
        }
        chronometer.setBase(SystemClock.elapsedRealtime());
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Chronometer chronometer3 = this.timer;
        if (chronometer3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timer");
            chronometer3 = null;
        }
        long base = ((elapsedRealtime - chronometer3.getBase()) / 1000) / 60;
        Chronometer chronometer4 = this.timer;
        if (chronometer4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timer");
            chronometer4 = null;
        }
        chronometer4.setFormat("0" + base + ":%s");
        Chronometer chronometer5 = this.timer;
        if (chronometer5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timer");
        } else {
            chronometer2 = chronometer5;
        }
        chronometer2.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initViewModel() {
        getRidingViewModel().getLocationLiveData().observe(this, new RidingModeActivity$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.wifiled.ipixels.ui.riding.RidingModeActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit initViewModel$lambda$0;
                initViewModel$lambda$0 = RidingModeActivity.initViewModel$lambda$0(RidingModeActivity.this, (Location) obj);
                return initViewModel$lambda$0;
            }
        }));
        this.serviceForegroundIntent = new Intent(this, (Class<?>) DeviceSensorService.class);
        if (Build.VERSION.SDK_INT > 33) {
            Intent intent = this.serviceForegroundIntent;
            Intrinsics.checkNotNull(intent);
            bindService(intent, this.connection, Context.BindServiceFlags.of(513L));
        } else {
            Intent intent2 = this.serviceForegroundIntent;
            Intrinsics.checkNotNull(intent2);
            bindService(intent2, this.connection, 1);
        }
        this.mIsBindService = true;
        UtilsExtensionKt.uiDelay(new Function0() { // from class: com.wifiled.ipixels.ui.riding.RidingModeActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit initViewModel$lambda$2;
                initViewModel$lambda$2 = RidingModeActivity.initViewModel$lambda$2(RidingModeActivity.this);
                return initViewModel$lambda$2;
            }
        }, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initViewModel$lambda$0(RidingModeActivity ridingModeActivity, Location location) {
        Intrinsics.checkNotNull(location);
        ridingModeActivity.setLocationRender(location);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initViewModel$lambda$2(final RidingModeActivity ridingModeActivity) {
        MutableLiveData<SensorEvent> sensorLiveData = ridingModeActivity.getRidingViewModel().getSensorLiveData();
        if (sensorLiveData != null) {
            sensorLiveData.observe(ridingModeActivity, new RidingModeActivity$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.wifiled.ipixels.ui.riding.RidingModeActivity$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit initViewModel$lambda$2$lambda$1;
                    initViewModel$lambda$2$lambda$1 = RidingModeActivity.initViewModel$lambda$2$lambda$1(RidingModeActivity.this, (SensorEvent) obj);
                    return initViewModel$lambda$2$lambda$1;
                }
            }));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initViewModel$lambda$2$lambda$1(RidingModeActivity ridingModeActivity, SensorEvent sensorEvent) {
        if (ridingModeActivity.autoMode) {
            Intrinsics.checkNotNull(sensorEvent);
            ridingModeActivity.setSensorRender(sensorEvent);
        }
        return Unit.INSTANCE;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public final void onEvent(RideModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        LogUtils.logi(this.TAG + ">>>[initViewModel]: " + this.autoMode, new Object[0]);
        if (this.autoMode) {
            setSensorRender(model.getEvent());
        }
    }

    private final void setSensorRender(SensorEvent event) {
        float f = 100;
        int roundToInt = MathKt.roundToInt(event.values[0] * f) / 100;
        int roundToInt2 = MathKt.roundToInt(event.values[1] * f) / 100;
        int roundToInt3 = MathKt.roundToInt(event.values[2] * f) / 100;
        Log.d("akon", "valuex: " + roundToInt + " valueY：" + roundToInt2 + " valueZ：" + roundToInt3 + "-> mValuex: " + this.mValueX);
        if (roundToInt3 < 0) {
            LogUtils.logi(this.TAG + ">>>[当前方向]: 向右", new Object[0]);
            sendSportData$default(this, 1, 0, 0, true, 6, null);
        } else if (roundToInt3 > 0) {
            LogUtils.logi(this.TAG + ">>>[当前方向]: 向左", new Object[0]);
            sendSportData$default(this, 0, 0, 0, true, 6, null);
        }
        this.mValueX = roundToInt;
    }

    public final int getSpeed() {
        return this.speed;
    }

    public final void setSpeed(int i) {
        this.speed = i;
    }

    public final int getDecimal() {
        return this.decimal;
    }

    public final void setDecimal(int i) {
        this.decimal = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x012a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void setLocationRender(android.location.Location r17) {
        /*
            Method dump skipped, instructions count: 346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.riding.RidingModeActivity.setLocationRender(android.location.Location):void");
    }

    private final void initRidingView() {
        TextView textView = this.tv_speed;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_speed");
            textView = null;
        }
        textView.setText(changTVsize("00.0km/h"));
        TextView textView3 = this.tv_distance;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_distance");
        } else {
            textView2 = textView3;
        }
        textView2.setText(changTVsize("00.0/km"));
    }

    private final void init() {
        getRidingViewModel().init(this);
        requestPermissionSelfPermis(PERMISSION_LOCATION_NEW, "", new IPermission() { // from class: com.wifiled.ipixels.ui.riding.RidingModeActivity$init$1
            @Override // com.wifiled.baselib.permission.IPermission
            public void permissionGranted() {
                RidingViewModel ridingViewModel;
                ridingViewModel = RidingModeActivity.this.getRidingViewModel();
                ridingViewModel.initLocation(RidingModeActivity.this);
                RidingModeActivity.this.initViewModel();
            }

            @Override // com.wifiled.baselib.permission.IPermission
            public void permissionDenied(int requestCode, List<String> denyList) {
                super.permissionDenied(requestCode, denyList);
                ToastUtil.show(RidingModeActivity.this.getString(R.string.open_location));
            }
        });
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindListener() {
        super.bindListener();
        RidingModeActivity ridingModeActivity = this;
        View[] viewArr = new View[9];
        CustomImageView customImageView = this.iv_auto;
        CustomImageView customImageView2 = null;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_auto");
            customImageView = null;
        }
        viewArr[0] = customImageView;
        CustomImageView customImageView3 = this.iv_speed;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_speed");
            customImageView3 = null;
        }
        viewArr[1] = customImageView3;
        CustomImageView customImageView4 = this.iv_flash;
        if (customImageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_flash");
            customImageView4 = null;
        }
        viewArr[2] = customImageView4;
        CustomImageView customImageView5 = this.iv_cancel;
        if (customImageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_cancel");
            customImageView5 = null;
        }
        viewArr[3] = customImageView5;
        CustomImageView customImageView6 = this.iv_go;
        if (customImageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_go");
            customImageView6 = null;
        }
        viewArr[4] = customImageView6;
        CustomImageView customImageView7 = this.iv_left;
        if (customImageView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_left");
            customImageView7 = null;
        }
        viewArr[5] = customImageView7;
        CustomImageView customImageView8 = this.iv_stop;
        if (customImageView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_stop");
            customImageView8 = null;
        }
        viewArr[6] = customImageView8;
        CustomImageView customImageView9 = this.iv_right;
        if (customImageView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView9 = null;
        }
        viewArr[7] = customImageView9;
        CustomImageView customImageView10 = this.iv_sos;
        if (customImageView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_sos");
            customImageView10 = null;
        }
        viewArr[8] = customImageView10;
        UtilsExtensionKt.setOnClicks(ridingModeActivity, viewArr);
        View[] viewArr2 = new View[9];
        CustomImageView customImageView11 = this.iv_auto;
        if (customImageView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_auto");
            customImageView11 = null;
        }
        viewArr2[0] = customImageView11;
        CustomImageView customImageView12 = this.iv_speed;
        if (customImageView12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_speed");
            customImageView12 = null;
        }
        viewArr2[1] = customImageView12;
        CustomImageView customImageView13 = this.iv_flash;
        if (customImageView13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_flash");
            customImageView13 = null;
        }
        viewArr2[2] = customImageView13;
        CustomImageView customImageView14 = this.iv_cancel;
        if (customImageView14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_cancel");
            customImageView14 = null;
        }
        viewArr2[3] = customImageView14;
        CustomImageView customImageView15 = this.iv_go;
        if (customImageView15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_go");
            customImageView15 = null;
        }
        viewArr2[4] = customImageView15;
        CustomImageView customImageView16 = this.iv_left;
        if (customImageView16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_left");
            customImageView16 = null;
        }
        viewArr2[5] = customImageView16;
        CustomImageView customImageView17 = this.iv_stop;
        if (customImageView17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_stop");
            customImageView17 = null;
        }
        viewArr2[6] = customImageView17;
        CustomImageView customImageView18 = this.iv_right;
        if (customImageView18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView18 = null;
        }
        viewArr2[7] = customImageView18;
        CustomImageView customImageView19 = this.iv_sos;
        if (customImageView19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_sos");
        } else {
            customImageView2 = customImageView19;
        }
        viewArr2[8] = customImageView2;
        UtilsExtensionKt.setOnTouchClicks(viewArr2);
    }

    static /* synthetic */ void sendSportData$default(RidingModeActivity ridingModeActivity, int i, int i2, int i3, boolean z, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = 0;
        }
        if ((i4 & 8) != 0) {
            z = false;
        }
        ridingModeActivity.sendSportData(i, i2, i3, z);
    }

    private final void sendSportData(int mode, int speed, int decimal, boolean auto) {
        this.autoMode = auto;
        SendCore.INSTANCE.sendSportData(mode, speed, decimal, new RidingModeActivity$sendSportData$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runSend$lambda$5(final RidingModeActivity ridingModeActivity) {
        ridingModeActivity.runOnUiThread(new Runnable() { // from class: com.wifiled.ipixels.ui.riding.RidingModeActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                RidingModeActivity.runSend$lambda$5$lambda$4(RidingModeActivity.this);
            }
        });
        if (ridingModeActivity.autoFlash) {
            if (!ridingModeActivity.autoMode || System.currentTimeMillis() - ridingModeActivity.time > 500) {
                sendSportData$default(ridingModeActivity, 5, 0, 0, ridingModeActivity.autoMode, 6, null);
            }
            ridingModeActivity.scheduleNext();
            return;
        }
        if (ridingModeActivity.isSpeedSel) {
            if (!ridingModeActivity.autoMode || System.currentTimeMillis() - ridingModeActivity.time > 500) {
                ridingModeActivity.sendSportData(6, ridingModeActivity.speed, ridingModeActivity.decimal, ridingModeActivity.autoMode);
            }
            ridingModeActivity.scheduleNext();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runSend$lambda$5$lambda$4(RidingModeActivity ridingModeActivity) {
        CustomImageView customImageView = ridingModeActivity.iv_flash;
        CustomImageView customImageView2 = null;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_flash");
            customImageView = null;
        }
        customImageView.setSelected(ridingModeActivity.autoFlash);
        CustomImageView customImageView3 = ridingModeActivity.iv_speed;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_speed");
        } else {
            customImageView2 = customImageView3;
        }
        customImageView2.setSelected(ridingModeActivity.isSpeedSel);
    }

    private final void scheduleNext() {
        ScheduledExecutorService scheduledExecutorService = this.singleThreadExecutor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.schedule(this.runSend, DefaultReConnectHandler.DEFAULT_CONNECT_DELAY, TimeUnit.MILLISECONDS);
        }
    }

    public final long getTime() {
        return this.time;
    }

    public final void setTime(long j) {
        this.time = j;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        ScheduledExecutorService scheduledExecutorService;
        ScheduledExecutorService scheduledExecutorService2;
        CustomImageView customImageView = null;
        Integer valueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if ((valueOf != null && valueOf.intValue() == R.id.iv_go) || ((valueOf != null && valueOf.intValue() == R.id.iv_left) || ((valueOf != null && valueOf.intValue() == R.id.iv_right) || ((valueOf != null && valueOf.intValue() == R.id.iv_stop) || (valueOf != null && valueOf.intValue() == R.id.iv_sos))))) {
            CustomImageView customImageView2 = this.iv_flash;
            if (customImageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_flash");
                customImageView2 = null;
            }
            customImageView2.setSelected(false);
        }
        CustomImageView customImageView3 = this.iv_flash;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_flash");
            customImageView3 = null;
        }
        customImageView3.setSelected(false);
        Integer valueOf2 = v != null ? Integer.valueOf(v.getId()) : null;
        if (valueOf2 != null && valueOf2.intValue() == R.id.iv_cancel) {
            finish();
            return;
        }
        if (valueOf2 != null && valueOf2.intValue() == R.id.iv_auto) {
            if (ClickFilter.filter()) {
                return;
            }
            this.autoMode = !this.autoMode;
            CustomImageView customImageView4 = this.iv_auto;
            if (customImageView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_auto");
            } else {
                customImageView = customImageView4;
            }
            customImageView.setSelected(this.autoMode);
            return;
        }
        if (valueOf2 != null && valueOf2.intValue() == R.id.iv_speed) {
            if (ClickFilter.filter()) {
                return;
            }
            this.isSpeedSel = !this.isSpeedSel;
            this.autoFlash = false;
            CustomImageView customImageView5 = this.iv_flash;
            if (customImageView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_flash");
                customImageView5 = null;
            }
            customImageView5.setSelected(this.autoFlash);
            CustomImageView customImageView6 = this.iv_speed;
            if (customImageView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_speed");
            } else {
                customImageView = customImageView6;
            }
            customImageView.setSelected(this.isSpeedSel);
            if (this.autoFlash || (scheduledExecutorService2 = this.singleThreadExecutor) == null) {
                return;
            }
            scheduledExecutorService2.execute(this.runSend);
            return;
        }
        if (valueOf2 != null && valueOf2.intValue() == R.id.iv_flash) {
            if (ClickFilter.filter()) {
                return;
            }
            this.autoFlash = !this.autoFlash;
            this.isSpeedSel = false;
            CustomImageView customImageView7 = this.iv_speed;
            if (customImageView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_speed");
                customImageView7 = null;
            }
            customImageView7.setSelected(this.isSpeedSel);
            if (this.autoFlash && (scheduledExecutorService = this.singleThreadExecutor) != null) {
                scheduledExecutorService.execute(this.runSend);
            }
            CustomImageView customImageView8 = this.iv_flash;
            if (customImageView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_flash");
            } else {
                customImageView = customImageView8;
            }
            customImageView.setSelected(this.autoFlash);
            return;
        }
        if (valueOf2 != null && valueOf2.intValue() == R.id.iv_go) {
            this.time = System.currentTimeMillis() + 2000;
            sendSportData$default(this, 2, 0, 0, this.autoMode, 6, null);
            return;
        }
        if (valueOf2 != null && valueOf2.intValue() == R.id.iv_left) {
            sendSportData$default(this, 0, 0, 0, this.autoMode, 6, null);
            return;
        }
        if (valueOf2 != null && valueOf2.intValue() == R.id.iv_stop) {
            this.time = System.currentTimeMillis() + 2000;
            sendSportData$default(this, 3, 0, 0, this.autoMode, 6, null);
        } else if (valueOf2 != null && valueOf2.intValue() == R.id.iv_right) {
            this.time = System.currentTimeMillis() + 2000;
            sendSportData$default(this, 1, 0, 0, this.autoMode, 6, null);
        } else if (valueOf2 != null && valueOf2.intValue() == R.id.iv_sos) {
            this.time = System.currentTimeMillis() + 2000;
            sendSportData$default(this, 4, 0, 0, this.autoMode, 6, null);
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        Boolean valueOf;
        Integer valueOf2 = event != null ? Integer.valueOf(event.getAction()) : null;
        if (valueOf2 != null && valueOf2.intValue() == 0) {
            if (v != null) {
                v.setAlpha(0.5f);
            }
            Log.d("akon", "ACTION_DOWN: " + (v != null ? Boolean.valueOf(v.isFocused()) : null));
        } else if (valueOf2 != null && valueOf2.intValue() == 1) {
            if (v != null) {
                v.setAlpha(1.0f);
            }
            valueOf = v != null ? Boolean.valueOf(isTouchPointInView(v, (int) event.getRawX(), (int) event.getRawY())) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue()) {
                v.performClick();
            }
        } else if (valueOf2 != null && valueOf2.intValue() == 2) {
            valueOf = v != null ? Boolean.valueOf(isTouchPointInView(v, (int) event.getRawX(), (int) event.getRawY())) : null;
            Intrinsics.checkNotNull(valueOf);
            boolean booleanValue = valueOf.booleanValue();
            if (booleanValue) {
                v.setAlpha(0.5f);
            } else {
                if (booleanValue) {
                    throw new NoWhenBranchMatchedException();
                }
                v.setAlpha(1.0f);
            }
        }
        return true;
    }

    private final boolean isTouchPointInView(View view, int x, int y) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return i2 <= y && y <= view.getMeasuredHeight() + i2 && x >= i && x <= view.getMeasuredWidth() + i;
    }

    private final SpannableString changTVsize(String value) {
        String str = value;
        SpannableString spannableString = new SpannableString(str);
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) ".", false, 2, (Object) null)) {
            spannableString.setSpan(new RelativeSizeSpan(0.6f), StringsKt.indexOf$default((CharSequence) str, ".", 0, false, 6, (Object) null), value.length(), 33);
        }
        return spannableString;
    }
}
