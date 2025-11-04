package com.wifiled.ipixels.core;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.wifiled.baselib.CoreBase;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.ipixels.AppConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AppLifeObserver.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007J\b\u0010\u0006\u001a\u00020\u0005H\u0007J\b\u0010\u0007\u001a\u00020\bH\u0003¨\u0006\t"}, d2 = {"Lcom/wifiled/ipixels/core/AppLifeObserver;", "Landroidx/lifecycle/LifecycleObserver;", "<init>", "()V", "onForeground", "", "onBackground", "getConnectWifiSsid", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AppLifeObserver implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public final void onForeground() {
        LogUtils.logd("Application enters the foreground", new Object[0]);
        String connectWifiSsid = getConnectWifiSsid();
        if (!StringsKt.contains$default((CharSequence) connectWifiSsid, (CharSequence) "LED_WIFI_", false, 2, (Object) null) || AppConfig.INSTANCE.getConnectType() == 0) {
            return;
        }
        AppConfig.INSTANCE.setCurConnectWifi(StringsKt.replace$default(StringsKt.replace$default(connectWifiSsid, "\"", "", false, 4, (Object) null), " ", "", false, 4, (Object) null));
        SocketManager.releaseSocket$default(SocketManager.INSTANCE, false, 1, null);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public final void onBackground() {
        AppConfig.INSTANCE.setBHasSwitchedBg(true);
        LogUtils.logd("Application enters the background", new Object[0]);
    }

    private final String getConnectWifiSsid() {
        WifiManager wifiManager = (WifiManager) CoreBase.getContext().getApplicationContext().getSystemService("wifi");
        WifiInfo connectionInfo = wifiManager != null ? wifiManager.getConnectionInfo() : null;
        Intrinsics.checkNotNull(connectionInfo);
        String ssid = connectionInfo.getSSID();
        Intrinsics.checkNotNullExpressionValue(ssid, "getSSID(...)");
        Log.d("wifiInfo", connectionInfo.toString());
        Log.d("#1.0.1#SSID:", ssid);
        if (ssid.equals("<unknown ssid>")) {
            Object systemService = CoreBase.getContext().getSystemService("connectivity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
                ssid = String.valueOf(activeNetworkInfo != null ? activeNetworkInfo.getExtraInfo() : null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.d("#1.1#SSID:", ssid);
        return ssid;
    }
}
