package com.wifiled.ipixels.ui.riding;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.RemoteException;
import android.util.Log;
import androidx.core.internal.view.SupportMenu;
import com.wifiled.baselib.utils.ContextHolder;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.IRemoteCallback;
import com.wifiled.ipixels.IRemoteService;
import com.wifiled.ipixels.R;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.Charsets;

/* compiled from: DeviceSensorService.kt */
@Metadata(d1 = {"\u0000G\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0014\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001e\u001fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\"\u0010\u0016\u001a\u00020\u00172\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0016J\b\u0010\u001a\u001a\u00020\u0012H\u0016J\b\u0010\u001b\u001a\u00020\u0012H\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002R\u0014\u0010\u0004\u001a\b\u0018\u00010\u0005R\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015¨\u0006 "}, d2 = {"Lcom/wifiled/ipixels/ui/riding/DeviceSensorService;", "Landroid/app/Service;", "<init>", "()V", "m_wklk", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "sm", "Landroid/hardware/SensorManager;", "getSm", "()Landroid/hardware/SensorManager;", "setSm", "(Landroid/hardware/SensorManager;)V", "onCreate", "", "sensorEventListener", "com/wifiled/ipixels/ui/riding/DeviceSensorService$sensorEventListener$1", "Lcom/wifiled/ipixels/ui/riding/DeviceSensorService$sensorEventListener$1;", "onStartCommand", "", "flags", "startId", "onDestroy", "setWakeLock", "createForegroundNotification", "Landroid/app/Notification;", "Companion", "LocalBinder", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceSensorService extends Service {
    private static final String TAG = "DeviceSensorService";
    private static int isTest;
    private static IRemoteCallback mCallback;
    private PowerManager.WakeLock m_wklk;
    private DeviceSensorService$sensorEventListener$1 sensorEventListener = new SensorEventListener() { // from class: com.wifiled.ipixels.ui.riding.DeviceSensorService$sensorEventListener$1
        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent event) {
            Intrinsics.checkNotNull(event);
            int roundToInt = MathKt.roundToInt(event.values[2] * 100) / 100;
            LogUtils.logi("DeviceSensorService>>>#1.0#[onSensorChanged]: " + roundToInt, new Object[0]);
            try {
                IRemoteCallback mCallback2 = DeviceSensorService.INSTANCE.getMCallback();
                Intrinsics.checkNotNull(mCallback2);
                mCallback2.onSensorEvent(roundToInt);
            } catch (Exception unused) {
            }
            DeviceSensorService.INSTANCE.setTest(roundToInt);
        }
    };
    private SensorManager sm;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static LocalBinder mBinder = new LocalBinder();

    /* compiled from: DeviceSensorService.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lcom/wifiled/ipixels/ui/riding/DeviceSensorService$Companion;", "", "<init>", "()V", "TAG", "", "mBinder", "Lcom/wifiled/ipixels/ui/riding/DeviceSensorService$LocalBinder;", "getMBinder", "()Lcom/wifiled/ipixels/ui/riding/DeviceSensorService$LocalBinder;", "setMBinder", "(Lcom/wifiled/ipixels/ui/riding/DeviceSensorService$LocalBinder;)V", "mCallback", "Lcom/wifiled/ipixels/IRemoteCallback;", "getMCallback", "()Lcom/wifiled/ipixels/IRemoteCallback;", "setMCallback", "(Lcom/wifiled/ipixels/IRemoteCallback;)V", "isTest", "", "()I", "setTest", "(I)V", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LocalBinder getMBinder() {
            return DeviceSensorService.mBinder;
        }

        public final void setMBinder(LocalBinder localBinder) {
            Intrinsics.checkNotNullParameter(localBinder, "<set-?>");
            DeviceSensorService.mBinder = localBinder;
        }

        public final IRemoteCallback getMCallback() {
            return DeviceSensorService.mCallback;
        }

        public final void setMCallback(IRemoteCallback iRemoteCallback) {
            DeviceSensorService.mCallback = iRemoteCallback;
        }

        public final int isTest() {
            return DeviceSensorService.isTest;
        }

        public final void setTest(int i) {
            DeviceSensorService.isTest = i;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public final SensorManager getSm() {
        return this.sm;
    }

    public final void setSm(SensorManager sensorManager) {
        this.sm = sensorManager;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        try {
            if (this.sm == null) {
                Object systemService = ContextHolder.getContext().getSystemService("sensor");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.SensorManager");
                this.sm = (SensorManager) systemService;
            }
            SensorManager sensorManager = this.sm;
            Sensor defaultSensor = sensorManager != null ? sensorManager.getDefaultSensor(3) : null;
            SensorManager sensorManager2 = this.sm;
            Intrinsics.checkNotNull(sensorManager2);
            sensorManager2.registerListener(this.sensorEventListener, defaultSensor, 3);
            setWakeLock();
            Object systemService2 = getSystemService("power");
            Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.os.PowerManager");
            PowerManager.WakeLock newWakeLock = ((PowerManager) systemService2).newWakeLock(1, DeviceSensorService.class.getName());
            this.m_wklk = newWakeLock;
            if (newWakeLock != null) {
                newWakeLock.acquire(600000L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        SensorManager sensorManager = this.sm;
        if (sensorManager != null) {
            Intrinsics.checkNotNull(sensorManager);
            sensorManager.unregisterListener(this.sensorEventListener);
        }
        PowerManager.WakeLock wakeLock = this.m_wklk;
        if (wakeLock != null) {
            wakeLock.release();
        }
        this.m_wklk = null;
        stopForeground(true);
    }

    /* compiled from: DeviceSensorService.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/wifiled/ipixels/ui/riding/DeviceSensorService$LocalBinder;", "Lcom/wifiled/ipixels/IRemoteService$Stub;", "<init>", "()V", "registerCallback", "", "cb", "Lcom/wifiled/ipixels/IRemoteCallback;", "unregisterCallback", "asBinder", "Landroid/os/IBinder;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class LocalBinder extends IRemoteService.Stub {
        @Override // com.wifiled.ipixels.IRemoteService.Stub, android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.wifiled.ipixels.IRemoteService
        public void registerCallback(IRemoteCallback cb) throws RemoteException {
            Intrinsics.checkNotNullParameter(cb, "cb");
            DeviceSensorService.INSTANCE.setMCallback(cb);
        }

        @Override // com.wifiled.ipixels.IRemoteService
        public void unregisterCallback(IRemoteCallback cb) throws RemoteException {
            DeviceSensorService.INSTANCE.setMCallback(null);
        }
    }

    private final void setWakeLock() {
        try {
            OutputStream outputStream = Runtime.getRuntime().exec("sh").getOutputStream();
            Intrinsics.checkNotNullExpressionValue(outputStream, "getOutputStream(...)");
            byte[] bytes = "echo PowerManagerService.noSuspend > /sys/power/wake_lock".getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            outputStream.write(bytes);
            Thread.sleep(100L);
            outputStream.flush();
            outputStream.close();
            Log.w(TAG, "setPowerWakeLock: 写入成功");
        } catch (IOException e) {
            Log.w(TAG, "setPowerWakeLock: 写入失败");
            e.printStackTrace();
        } catch (InterruptedException e2) {
            Log.w(TAG, "setPowerWakeLock: 写入失败");
            e2.printStackTrace();
        }
    }

    private final Notification createForegroundNotification() {
        Object systemService = getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationChannel notificationChannel = new NotificationChannel("notification_channel_id_01", "Foreground Service Notification", 4);
        notificationChannel.setDescription("Channel description");
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(SupportMenu.CATEGORY_MASK);
        notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
        notificationChannel.enableVibration(true);
        ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
        Notification.Builder builder = new Notification.Builder(App.INSTANCE.getContext(), "notification_channel_id_01");
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setContentTitle("iPixel");
        builder.setContentText(getString(R.string.ride_titile));
        builder.setWhen(System.currentTimeMillis());
        Notification build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }
}
