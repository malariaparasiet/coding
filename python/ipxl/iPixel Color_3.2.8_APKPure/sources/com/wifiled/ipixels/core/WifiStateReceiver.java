package com.wifiled.ipixels.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import com.wifiled.baselib.CoreBase;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.ipixels.AppConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: WifiStateReceiver.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0017J\b\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/wifiled/ipixels/core/WifiStateReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "connectType", "", "tState", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "getConnectWifiSsid", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WifiStateReceiver extends BroadcastReceiver {
    private int connectType = -1;
    private int tState = -1;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int intExtra;
        Intrinsics.checkNotNullParameter(intent, "intent");
        LogUtils.loge(intent.getAction() + " ->wifiState:" + intent.getIntExtra("wifi_state", 0), new Object[0]);
        if ((Intrinsics.areEqual("android.net.wifi.WIFI_STATE_CHANGED", intent.getAction()) || Intrinsics.areEqual("android.net.wifi.STATE_CHANGE", intent.getAction())) && this.tState != (intExtra = intent.getIntExtra("wifi_state", 0))) {
            this.tState = intExtra;
            if (intExtra == 0) {
                if (AppConfig.INSTANCE.getConnectType() == 0) {
                    AppConfig.INSTANCE.setConnectType(-1);
                }
                this.connectType = -1;
                this.tState = -1;
            } else if (intExtra == 1) {
                if (AppConfig.INSTANCE.getConnectType() == 0) {
                    AppConfig.INSTANCE.setConnectType(-1);
                }
                this.connectType = -1;
                this.tState = -1;
            }
        }
        Object systemService = context != null ? context.getSystemService("connectivity") : null;
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable() || activeNetworkInfo.getType() != 1 || this.connectType == 1) {
            return;
        }
        this.connectType = 1;
        String connectWifiSsid = getConnectWifiSsid();
        if (StringsKt.contains$default((CharSequence) connectWifiSsid, (CharSequence) "LED_WIFI_", false, 2, (Object) null)) {
            AppConfig.INSTANCE.setCurConnectWifi(StringsKt.replace$default(StringsKt.replace$default(connectWifiSsid, "\"", "", false, 4, (Object) null), " ", "", false, 4, (Object) null));
        }
        if (AppConfig.INSTANCE.getConnectType() != 0) {
            SocketManager.releaseSocket$default(SocketManager.INSTANCE, false, 1, null);
        }
    }

    private final String getConnectWifiSsid() {
        WifiManager wifiManager = (WifiManager) CoreBase.getContext().getApplicationContext().getSystemService("wifi");
        WifiInfo connectionInfo = wifiManager != null ? wifiManager.getConnectionInfo() : null;
        Intrinsics.checkNotNull(connectionInfo);
        String ssid = connectionInfo.getSSID();
        Intrinsics.checkNotNullExpressionValue(ssid, "getSSID(...)");
        if (ssid.equals("<unknown ssid>")) {
            Object systemService = CoreBase.getContext().getSystemService("connectivity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
                String extraInfo = activeNetworkInfo != null ? activeNetworkInfo.getExtraInfo() : null;
                Intrinsics.checkNotNull(extraInfo);
                ssid = extraInfo;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.d("#1.1#SSID:", ssid);
        return ssid;
    }
}
