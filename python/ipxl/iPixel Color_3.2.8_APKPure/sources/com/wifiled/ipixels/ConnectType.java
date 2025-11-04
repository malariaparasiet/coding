package com.wifiled.ipixels;

import kotlin.Metadata;

/* compiled from: AppConfig.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/wifiled/ipixels/ConnectType;", "", "<init>", "()V", "WIFI", "", "BLE", "NULL_CONNECT", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ConnectType {
    public static final int BLE = 1;
    public static final ConnectType INSTANCE = new ConnectType();
    public static final int NULL_CONNECT = -1;
    public static final int WIFI = 0;

    private ConnectType() {
    }
}
