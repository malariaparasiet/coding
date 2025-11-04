package com.wifiled.ipixels.core.send;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SimpleSend.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/core/send/SimpleSend;", "Lcom/wifiled/ipixels/core/send/BaseSend;", "setTextSpeed", "", "speed", "", "callback", "Lcom/wifiled/ipixels/core/send/SendResultCallback;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface SimpleSend extends BaseSend {
    void setTextSpeed(int speed, SendResultCallback callback);

    /* compiled from: SimpleSend.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void deleteAllData(SimpleSend simpleSend, SendResultCallback sendResultCallback) {
            SimpleSend.super.deleteAllData(sendResultCallback);
        }

        @Deprecated
        public static void deleteAllData2(SimpleSend simpleSend, SendResultCallback sendResultCallback) {
            SimpleSend.super.deleteAllData2(sendResultCallback);
        }

        @Deprecated
        public static void sendColockMode(SimpleSend simpleSend, int i, boolean z, boolean z2, SendResultCallback sendResultCallback) {
            SimpleSend.super.sendColockMode(i, z, z2, sendResultCallback);
        }

        @Deprecated
        public static void sendCompat(SimpleSend simpleSend, byte[] data, SendResultCallback sendResultCallback) {
            Intrinsics.checkNotNullParameter(data, "data");
            SimpleSend.super.sendCompat(data, sendResultCallback);
        }

        @Deprecated
        public static void sendCompat2(SimpleSend simpleSend, byte[] data, SendResultCallback sendResultCallback) {
            Intrinsics.checkNotNullParameter(data, "data");
            SimpleSend.super.sendCompat2(data, sendResultCallback);
        }

        @Deprecated
        public static void sendLedOnOff(SimpleSend simpleSend, int i, SendResultCallback sendResultCallback) {
            SimpleSend.super.sendLedOnOff(i, sendResultCallback);
        }

        @Deprecated
        public static void sendRhythm(SimpleSend simpleSend, int i, int i2) {
            SimpleSend.super.sendRhythm(i, i2);
        }

        @Deprecated
        public static void sendRhythmChart(SimpleSend simpleSend, int i, byte[] data) {
            Intrinsics.checkNotNullParameter(data, "data");
            SimpleSend.super.sendRhythmChart(i, data);
        }

        @Deprecated
        public static void sendSportData(SimpleSend simpleSend, int i, int i2, int i3, SendResultCallback sendResultCallback) {
            SimpleSend.super.sendSportData(i, i2, i3, sendResultCallback);
        }

        @Deprecated
        public static void setDiyFunMode(SimpleSend simpleSend, int i, SendResultCallback sendResultCallback) {
            SimpleSend.super.setDiyFunMode(i, sendResultCallback);
        }

        @Deprecated
        public static void setLedLight(SimpleSend simpleSend, int i, SendResultCallback sendResultCallback) {
            SimpleSend.super.setLedLight(i, sendResultCallback);
        }

        @Deprecated
        public static void setPwd(SimpleSend simpleSend, int i, String pwdStr, SendResultCallback sendResultCallback) {
            Intrinsics.checkNotNullParameter(pwdStr, "pwdStr");
            SimpleSend.super.setPwd(i, pwdStr, sendResultCallback);
        }

        @Deprecated
        public static void setUpsideDown(SimpleSend simpleSend, int i, SendResultCallback sendResultCallback) {
            SimpleSend.super.setUpsideDown(i, sendResultCallback);
        }

        @Deprecated
        public static void upDataOTA2900Start(SimpleSend simpleSend, SendResultCallback sendResultCallback) {
            SimpleSend.super.upDataOTA2900Start(sendResultCallback);
        }

        @Deprecated
        public static void updateOtaMcuOrWifiStep1(SimpleSend simpleSend, int i, byte[] CRC32b, int i2, int i3, SendResultCallback sendResultCallback) {
            Intrinsics.checkNotNullParameter(CRC32b, "CRC32b");
            SimpleSend.super.updateOtaMcuOrWifiStep1(i, CRC32b, i2, i3, sendResultCallback);
        }

        @Deprecated
        public static void verifyPwd(SimpleSend simpleSend, String pwd, SendResultCallback sendResultCallback) {
            Intrinsics.checkNotNullParameter(pwd, "pwd");
            SimpleSend.super.verifyPwd(pwd, sendResultCallback);
        }
    }

    static /* synthetic */ void setTextSpeed$default(SimpleSend simpleSend, int i, SendResultCallback sendResultCallback, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setTextSpeed");
        }
        if ((i2 & 2) != 0) {
            sendResultCallback = null;
        }
        simpleSend.setTextSpeed(i, sendResultCallback);
    }
}
