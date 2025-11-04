package com.wifiled.ipixels.core.send;

import com.wifiled.ipixels.core.SendCore;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WifiSend.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J3\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t¢\u0006\u0002\b\u000bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/core/send/WifiSend;", "Lcom/wifiled/ipixels/core/send/SimpleSend;", "sendGifData", "", "is_down", "", "data", "", "callbackBuilder", "Lkotlin/Function1;", "Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;", "Lkotlin/ExtensionFunctionType;", "sendCameraData", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface WifiSend extends SimpleSend {
    void sendCameraData(byte[] data);

    void sendGifData(boolean is_down, byte[] data, Function1<? super SendCore.CallbackBuilder, Unit> callbackBuilder);

    /* compiled from: WifiSend.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void deleteAllData(WifiSend wifiSend, SendResultCallback sendResultCallback) {
            WifiSend.super.deleteAllData(sendResultCallback);
        }

        @Deprecated
        public static void deleteAllData2(WifiSend wifiSend, SendResultCallback sendResultCallback) {
            WifiSend.super.deleteAllData2(sendResultCallback);
        }

        @Deprecated
        public static void sendColockMode(WifiSend wifiSend, int i, boolean z, boolean z2, SendResultCallback sendResultCallback) {
            WifiSend.super.sendColockMode(i, z, z2, sendResultCallback);
        }

        @Deprecated
        public static void sendCompat(WifiSend wifiSend, byte[] data, SendResultCallback sendResultCallback) {
            Intrinsics.checkNotNullParameter(data, "data");
            WifiSend.super.sendCompat(data, sendResultCallback);
        }

        @Deprecated
        public static void sendCompat2(WifiSend wifiSend, byte[] data, SendResultCallback sendResultCallback) {
            Intrinsics.checkNotNullParameter(data, "data");
            WifiSend.super.sendCompat2(data, sendResultCallback);
        }

        @Deprecated
        public static void sendLedOnOff(WifiSend wifiSend, int i, SendResultCallback sendResultCallback) {
            WifiSend.super.sendLedOnOff(i, sendResultCallback);
        }

        @Deprecated
        public static void sendRhythm(WifiSend wifiSend, int i, int i2) {
            WifiSend.super.sendRhythm(i, i2);
        }

        @Deprecated
        public static void sendRhythmChart(WifiSend wifiSend, int i, byte[] data) {
            Intrinsics.checkNotNullParameter(data, "data");
            WifiSend.super.sendRhythmChart(i, data);
        }

        @Deprecated
        public static void sendSportData(WifiSend wifiSend, int i, int i2, int i3, SendResultCallback sendResultCallback) {
            WifiSend.super.sendSportData(i, i2, i3, sendResultCallback);
        }

        @Deprecated
        public static void setDiyFunMode(WifiSend wifiSend, int i, SendResultCallback sendResultCallback) {
            WifiSend.super.setDiyFunMode(i, sendResultCallback);
        }

        @Deprecated
        public static void setLedLight(WifiSend wifiSend, int i, SendResultCallback sendResultCallback) {
            WifiSend.super.setLedLight(i, sendResultCallback);
        }

        @Deprecated
        public static void setPwd(WifiSend wifiSend, int i, String pwdStr, SendResultCallback sendResultCallback) {
            Intrinsics.checkNotNullParameter(pwdStr, "pwdStr");
            WifiSend.super.setPwd(i, pwdStr, sendResultCallback);
        }

        @Deprecated
        public static void setUpsideDown(WifiSend wifiSend, int i, SendResultCallback sendResultCallback) {
            WifiSend.super.setUpsideDown(i, sendResultCallback);
        }

        @Deprecated
        public static void upDataOTA2900Start(WifiSend wifiSend, SendResultCallback sendResultCallback) {
            WifiSend.super.upDataOTA2900Start(sendResultCallback);
        }

        @Deprecated
        public static void updateOtaMcuOrWifiStep1(WifiSend wifiSend, int i, byte[] CRC32b, int i2, int i3, SendResultCallback sendResultCallback) {
            Intrinsics.checkNotNullParameter(CRC32b, "CRC32b");
            WifiSend.super.updateOtaMcuOrWifiStep1(i, CRC32b, i2, i3, sendResultCallback);
        }

        @Deprecated
        public static void verifyPwd(WifiSend wifiSend, String pwd, SendResultCallback sendResultCallback) {
            Intrinsics.checkNotNullParameter(pwd, "pwd");
            WifiSend.super.verifyPwd(pwd, sendResultCallback);
        }
    }

    static /* synthetic */ void sendGifData$default(WifiSend wifiSend, boolean z, byte[] bArr, Function1 function1, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendGifData");
        }
        if ((i & 1) != 0) {
            z = true;
        }
        wifiSend.sendGifData(z, bArr, function1);
    }
}
