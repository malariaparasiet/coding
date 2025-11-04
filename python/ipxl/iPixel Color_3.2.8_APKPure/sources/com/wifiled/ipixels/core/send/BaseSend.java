package com.wifiled.ipixels.core.send;

import com.alibaba.fastjson2.JSONB;
import com.blankj.utilcode.util.ToastUtils;
import com.wifiled.baselib.utils.DateUtils;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.blelibrary.ble.utils.ByteUtils;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.core.BleManager;
import com.wifiled.ipixels.core.BleManager2;
import com.wifiled.ipixels.core.SocketManager;
import com.wifiled.ipixels.ui.text.vo.SendResultMsg;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.Date;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.greenrobot.eventbus.EventBus;

/* compiled from: BaseSend.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u001a\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0014\u0010\u000e\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0014\u0010\u000f\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u000b2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J$\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u001a\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u000b2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J6\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0014\u0010 \u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J*\u0010!\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J,\u0010$\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010(\u001a\u00020\u00032\u0006\u0010)\u001a\u00020\u000b2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010*\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006+À\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/core/send/BaseSend;", "", "sendCompat", "", "data", "", "callback", "Lcom/wifiled/ipixels/core/send/SendResultCallback;", "sendCompat2", "sendRhythm", "level", "", PlayerFinal.PLAYER_MODE, "sendRhythmChart", "deleteAllData", "deleteAllData2", "setLedLight", ToastUtils.MODE.LIGHT, "setPwd", "flag", "pwdStr", "", "verifyPwd", "pwd", "sendResultCallback", "setUpsideDown", "isDown", "updateOtaMcuOrWifiStep1", "pkgCount", "CRC32b", "binSize", "otaType", "upDataOTA2900Start", "sendSportData", "speed", "decimal", "sendColockMode", "timeScale", "", "showDate", "sendLedOnOff", "onOff", "setDiyFunMode", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface BaseSend {

    /* compiled from: BaseSend.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void sendCompat(BaseSend baseSend, byte[] data, SendResultCallback sendResultCallback) {
            Intrinsics.checkNotNullParameter(data, "data");
            BaseSend.super.sendCompat(data, sendResultCallback);
        }

        @Deprecated
        public static void sendCompat2(BaseSend baseSend, byte[] data, SendResultCallback sendResultCallback) {
            Intrinsics.checkNotNullParameter(data, "data");
            BaseSend.super.sendCompat2(data, sendResultCallback);
        }

        @Deprecated
        public static void sendRhythm(BaseSend baseSend, int i, int i2) {
            BaseSend.super.sendRhythm(i, i2);
        }

        @Deprecated
        public static void sendRhythmChart(BaseSend baseSend, int i, byte[] data) {
            Intrinsics.checkNotNullParameter(data, "data");
            BaseSend.super.sendRhythmChart(i, data);
        }

        @Deprecated
        public static void deleteAllData(BaseSend baseSend, SendResultCallback sendResultCallback) {
            BaseSend.super.deleteAllData(sendResultCallback);
        }

        @Deprecated
        public static void deleteAllData2(BaseSend baseSend, SendResultCallback sendResultCallback) {
            BaseSend.super.deleteAllData2(sendResultCallback);
        }

        @Deprecated
        public static void setLedLight(BaseSend baseSend, int i, SendResultCallback sendResultCallback) {
            BaseSend.super.setLedLight(i, sendResultCallback);
        }

        @Deprecated
        public static void setPwd(BaseSend baseSend, int i, String pwdStr, SendResultCallback sendResultCallback) {
            Intrinsics.checkNotNullParameter(pwdStr, "pwdStr");
            BaseSend.super.setPwd(i, pwdStr, sendResultCallback);
        }

        @Deprecated
        public static void verifyPwd(BaseSend baseSend, String pwd, SendResultCallback sendResultCallback) {
            Intrinsics.checkNotNullParameter(pwd, "pwd");
            BaseSend.super.verifyPwd(pwd, sendResultCallback);
        }

        @Deprecated
        public static void setUpsideDown(BaseSend baseSend, int i, SendResultCallback sendResultCallback) {
            BaseSend.super.setUpsideDown(i, sendResultCallback);
        }

        @Deprecated
        public static void updateOtaMcuOrWifiStep1(BaseSend baseSend, int i, byte[] CRC32b, int i2, int i3, SendResultCallback sendResultCallback) {
            Intrinsics.checkNotNullParameter(CRC32b, "CRC32b");
            BaseSend.super.updateOtaMcuOrWifiStep1(i, CRC32b, i2, i3, sendResultCallback);
        }

        @Deprecated
        public static void upDataOTA2900Start(BaseSend baseSend, SendResultCallback sendResultCallback) {
            BaseSend.super.upDataOTA2900Start(sendResultCallback);
        }

        @Deprecated
        public static void sendSportData(BaseSend baseSend, int i, int i2, int i3, SendResultCallback sendResultCallback) {
            BaseSend.super.sendSportData(i, i2, i3, sendResultCallback);
        }

        @Deprecated
        public static void sendColockMode(BaseSend baseSend, int i, boolean z, boolean z2, SendResultCallback sendResultCallback) {
            BaseSend.super.sendColockMode(i, z, z2, sendResultCallback);
        }

        @Deprecated
        public static void sendLedOnOff(BaseSend baseSend, int i, SendResultCallback sendResultCallback) {
            BaseSend.super.sendLedOnOff(i, sendResultCallback);
        }

        @Deprecated
        public static void setDiyFunMode(BaseSend baseSend, int i, SendResultCallback sendResultCallback) {
            BaseSend.super.setDiyFunMode(i, sendResultCallback);
        }
    }

    default void sendCompat(byte[] data, SendResultCallback callback) {
        Intrinsics.checkNotNullParameter(data, "data");
        int connectType = AppConfig.INSTANCE.getConnectType();
        if (connectType == 0) {
            SocketManager.INSTANCE.sendData(data, callback);
            return;
        }
        if (connectType == 1) {
            BleManager.INSTANCE.get().write(data, callback);
            return;
        }
        EventBus eventBus = EventBus.getDefault();
        byte[] bytes = "dev disconnect".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        eventBus.post(new SendResultMsg(bytes));
    }

    default void sendCompat2(byte[] data, SendResultCallback callback) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (AppConfig.INSTANCE.getConnectType2() == 1) {
            BleManager2.INSTANCE.get().write(data, callback);
            return;
        }
        EventBus eventBus = EventBus.getDefault();
        byte[] bytes = "dev disconnect".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        eventBus.post(new SendResultMsg(bytes));
    }

    default void sendRhythm(int level, int mode) {
        byte[] bArr = {6, 0, 0, 2, 0, 0};
        bArr[4] = (byte) level;
        bArr[5] = (byte) mode;
        sendCompat(bArr, null);
        if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            sendCompat2(bArr, null);
        }
        LogUtils.logi("SendCore>>>[sendRhythm]:" + level, new Object[0]);
    }

    default void sendRhythmChart(int mode, byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        byte[] bArr = new byte[11];
        int length = data.length;
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = 1;
            if (i >= length) {
                break;
            }
            int i4 = i2 + 1;
            int i5 = ((data[i] & UByte.MAX_VALUE) * 15) / 255;
            int abs = Math.abs(i5);
            if (1 <= abs && abs < 16) {
                i3 = i5;
            } else if (abs != 0) {
                i3 = 15;
            }
            bArr[i2] = (byte) i3;
            i++;
            i2 = i4;
        }
        LogUtils.logi("$#1.0#[sendRhythmChart]: " + ByteUtils.toHexString(bArr), new Object[0]);
        byte[] plus = ArraysKt.plus(new byte[]{JSONB.Constants.BC_INT32_NUM_16, 0, 1, 2, (byte) mode}, bArr);
        sendCompat(plus, null);
        if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            sendCompat2(plus, null);
        }
    }

    static /* synthetic */ void deleteAllData$default(BaseSend baseSend, SendResultCallback sendResultCallback, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deleteAllData");
        }
        if ((i & 1) != 0) {
            sendResultCallback = null;
        }
        baseSend.deleteAllData(sendResultCallback);
    }

    default void deleteAllData(SendResultCallback callback) {
        byte[] bArr = {4, 0, 3, ByteCompanionObject.MIN_VALUE};
        if (BleManager.INSTANCE.get().getConnectedDevice() != null || AppConfig.INSTANCE.getConnectType() == 0) {
            sendCompat(bArr, callback);
        }
        LogUtils.logi("SendCore>>>[deleteAllData]:", new Object[0]);
    }

    static /* synthetic */ void deleteAllData2$default(BaseSend baseSend, SendResultCallback sendResultCallback, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deleteAllData2");
        }
        if ((i & 1) != 0) {
            sendResultCallback = null;
        }
        baseSend.deleteAllData2(sendResultCallback);
    }

    default void deleteAllData2(SendResultCallback callback) {
        byte[] bArr = {4, 0, 3, ByteCompanionObject.MIN_VALUE};
        if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            sendCompat2(bArr, callback);
        }
        LogUtils.logi("SendCore>>>[deleteAllData]:", new Object[0]);
    }

    static /* synthetic */ void setLedLight$default(BaseSend baseSend, int i, SendResultCallback sendResultCallback, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setLedLight");
        }
        if ((i2 & 2) != 0) {
            sendResultCallback = null;
        }
        baseSend.setLedLight(i, sendResultCallback);
    }

    default void setLedLight(int light, SendResultCallback callback) {
        byte[] bArr = {5, 0, 4, ByteCompanionObject.MIN_VALUE, 50};
        bArr[4] = (byte) light;
        sendCompat(bArr, callback);
        if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            sendCompat2(bArr, callback);
        }
        LogUtils.logi("SendCore>>>[setLedLight]:" + light, new Object[0]);
    }

    static /* synthetic */ void setPwd$default(BaseSend baseSend, int i, String str, SendResultCallback sendResultCallback, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setPwd");
        }
        if ((i2 & 4) != 0) {
            sendResultCallback = null;
        }
        baseSend.setPwd(i, str, sendResultCallback);
    }

    default void setPwd(int flag, String pwdStr, SendResultCallback callback) {
        Intrinsics.checkNotNullParameter(pwdStr, "pwdStr");
        if (pwdStr.length() != 6) {
            return;
        }
        String substring = pwdStr.substring(0, 2);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        String substring2 = pwdStr.substring(2, 4);
        Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
        String substring3 = pwdStr.substring(4, 6);
        Intrinsics.checkNotNullExpressionValue(substring3, "substring(...)");
        sendCompat(new byte[]{8, 0, 4, 2, (byte) flag, Byte.parseByte(substring), Byte.parseByte(substring2), Byte.parseByte(substring3)}, callback);
        LogUtils.logi("====pwd1Str:" + substring + "  pwd2Str:" + substring2 + "  pwd3Str:" + substring3, new Object[0]);
    }

    default void verifyPwd(String pwd, SendResultCallback sendResultCallback) {
        Intrinsics.checkNotNullParameter(pwd, "pwd");
        if (pwd.length() != 6) {
            return;
        }
        String substring = pwd.substring(0, 2);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        String substring2 = pwd.substring(2, 4);
        Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
        String substring3 = pwd.substring(4, 6);
        Intrinsics.checkNotNullExpressionValue(substring3, "substring(...)");
        sendCompat(new byte[]{7, 0, 5, 2, Byte.parseByte(substring), Byte.parseByte(substring2), Byte.parseByte(substring3)}, sendResultCallback);
    }

    static /* synthetic */ void setUpsideDown$default(BaseSend baseSend, int i, SendResultCallback sendResultCallback, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setUpsideDown");
        }
        if ((i2 & 2) != 0) {
            sendResultCallback = null;
        }
        baseSend.setUpsideDown(i, sendResultCallback);
    }

    default void setUpsideDown(int isDown, SendResultCallback callback) {
        byte[] bArr = {5, 0, 6, ByteCompanionObject.MIN_VALUE, 0};
        bArr[4] = (byte) isDown;
        sendCompat(bArr, callback);
        if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            sendCompat2(bArr, callback);
        }
        LogUtils.logi("SendCore>>>[setUpsideDown]:" + isDown, new Object[0]);
    }

    static /* synthetic */ void updateOtaMcuOrWifiStep1$default(BaseSend baseSend, int i, byte[] bArr, int i2, int i3, SendResultCallback sendResultCallback, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateOtaMcuOrWifiStep1");
        }
        if ((i4 & 8) != 0) {
            i3 = 0;
        }
        int i5 = i3;
        if ((i4 & 16) != 0) {
            sendResultCallback = null;
        }
        baseSend.updateOtaMcuOrWifiStep1(i, bArr, i2, i5, sendResultCallback);
    }

    default void updateOtaMcuOrWifiStep1(int pkgCount, byte[] CRC32b, int binSize, int otaType, SendResultCallback callback) {
        Intrinsics.checkNotNullParameter(CRC32b, "CRC32b");
        byte[] plus = ArraysKt.plus(ArraysKt.plus(new byte[]{13, 0, (byte) otaType, JSONB.Constants.BC_INT64_SHORT_MIN}, (byte) pkgCount), CRC32b);
        byte[] int2byte = ByteUtils.int2byte(binSize);
        Intrinsics.checkNotNull(int2byte);
        sendCompat(ArraysKt.plus(plus, int2byte), callback);
    }

    static /* synthetic */ void upDataOTA2900Start$default(BaseSend baseSend, SendResultCallback sendResultCallback, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: upDataOTA2900Start");
        }
        if ((i & 1) != 0) {
            sendResultCallback = null;
        }
        baseSend.upDataOTA2900Start(sendResultCallback);
    }

    default void upDataOTA2900Start(SendResultCallback callback) {
        sendCompat(new byte[]{5, 0, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, 85, 2}, callback);
    }

    default void sendSportData(int mode, int speed, int decimal, SendResultCallback callback) {
        byte[] bArr = {7, 0, 6, 0, 0, 0, 0};
        bArr[4] = (byte) mode;
        bArr[5] = (byte) speed;
        bArr[6] = (byte) decimal;
        sendCompat(bArr, callback);
        LogUtils.logi("SendCore>>>[sendSportMode]:" + mode, new Object[0]);
    }

    static /* synthetic */ void sendColockMode$default(BaseSend baseSend, int i, boolean z, boolean z2, SendResultCallback sendResultCallback, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendColockMode");
        }
        if ((i2 & 8) != 0) {
            sendResultCallback = null;
        }
        baseSend.sendColockMode(i, z, z2, sendResultCallback);
    }

    default void sendColockMode(int mode, boolean timeScale, boolean showDate, SendResultCallback callback) {
        byte[] bArr = {11, 0, 6, 1, 1, 1, 0, 0, 0, 0, 0};
        bArr[4] = (byte) mode;
        if (timeScale) {
            bArr[5] = 0;
        } else {
            bArr[5] = 1;
        }
        if (showDate) {
            bArr[6] = 1;
        } else {
            bArr[6] = 0;
        }
        int year = DateUtils.getYear() - 2000;
        int month = DateUtils.getMonth();
        int day = DateUtils.getDay();
        int weekOfDate = DateUtils.getWeekOfDate(new Date());
        bArr[7] = (byte) year;
        bArr[8] = (byte) month;
        bArr[9] = (byte) day;
        bArr[10] = (byte) weekOfDate;
        if (BleManager.INSTANCE.get().getConnectedDevice() != null || AppConfig.INSTANCE.getConnectType() == 0) {
            sendCompat(bArr, callback);
        }
        if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            sendCompat2(bArr, callback);
        }
        LogUtils.logi("SendCore>>>[sendColockMode]:" + mode, new Object[0]);
    }

    static /* synthetic */ void sendLedOnOff$default(BaseSend baseSend, int i, SendResultCallback sendResultCallback, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendLedOnOff");
        }
        if ((i2 & 2) != 0) {
            sendResultCallback = null;
        }
        baseSend.sendLedOnOff(i, sendResultCallback);
    }

    default void sendLedOnOff(int onOff, SendResultCallback callback) {
        byte[] bArr = {5, 0, 7, 1, 1};
        bArr[4] = (byte) onOff;
        if (BleManager.INSTANCE.get().getConnectedDevice() != null || AppConfig.INSTANCE.getConnectType() == 0) {
            sendCompat(bArr, callback);
        }
        if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            sendCompat2(bArr, callback);
        }
        LogUtils.logi("SendCore>>>[sendLedOnOff]:" + onOff, new Object[0]);
    }

    static /* synthetic */ void setDiyFunMode$default(BaseSend baseSend, int i, SendResultCallback sendResultCallback, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setDiyFunMode");
        }
        if ((i2 & 2) != 0) {
            sendResultCallback = null;
        }
        baseSend.setDiyFunMode(i, sendResultCallback);
    }

    default void setDiyFunMode(int mode, SendResultCallback callback) {
        byte[] bArr = {5, 0, 4, 1, 0};
        bArr[4] = (byte) mode;
        if (BleManager.INSTANCE.get().getConnectedDevice() != null || AppConfig.INSTANCE.getConnectType() == 0) {
            sendCompat(bArr, callback);
        }
        if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            sendCompat2(bArr, callback);
        }
        LogUtils.logi("SendCore>>>[setDiyFunMode]:" + mode, new Object[0]);
    }
}
