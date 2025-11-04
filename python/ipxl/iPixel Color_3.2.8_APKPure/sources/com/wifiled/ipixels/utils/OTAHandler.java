package com.wifiled.ipixels.utils;

import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.video.AudioStats;
import com.alibaba.fastjson2.JSONB;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.utils.ThreadUtils;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.send.SendResultCallback;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OTAHandler.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ(\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011H\u0002J\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u00162\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u000fH\u0002J\u0010\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\rH\u0002J\b\u0010\u001a\u001a\u00020\u000bH\u0002J\u0018\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010!\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0005¨\u0006#"}, d2 = {"Lcom/wifiled/ipixels/utils/OTAHandler;", "", "callbackBuilder", "Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;", "<init>", "(Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;)V", "callback", "getCallback", "()Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;", "setCallback", "dealOtaForMCU", "", "temp", "", "convertTo32", "", "b1", "", "b2", "b3", "b4", "cutArray", "", "data", "maxLength", "sendOtaData", "startWithTimeOut", "showProgress", "progress", "", "message", "", "showSuccess", "showError", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OTAHandler {
    private static int address;
    private static int errorCount;
    private static boolean isCancel;
    private static boolean isOta;
    private static double lastValue;
    private static int nextLength;
    private static int offSet;
    private static byte[] otaData;
    private static int sendCount;
    private static int sendIndex;
    private SendCore.CallbackBuilder callback;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static List<byte[]> sendArray = new ArrayList();
    private static int timeOut = 5;
    private static final int limitLength = 509;

    private final int convertTo32(byte b1, byte b2, byte b3, byte b4) {
        return ((b1 & UByte.MAX_VALUE) << 24) | ((b2 & UByte.MAX_VALUE) << 16) | ((b3 & UByte.MAX_VALUE) << 8) | (b4 & UByte.MAX_VALUE);
    }

    public OTAHandler(SendCore.CallbackBuilder callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        this.callback = callbackBuilder;
    }

    /* compiled from: OTAHandler.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u001b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0018\"\u0004\b#\u0010\u001aR\u001a\u0010$\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0018\"\u0004\b&\u0010\u001aR\u001a\u0010'\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0018\"\u0004\b)\u0010\u001aR\u001a\u0010*\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0018\"\u0004\b,\u0010\u001aR\u001a\u0010-\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0018\"\u0004\b/\u0010\u001aR\u001a\u00100\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0006\"\u0004\b1\u0010\bR\u001a\u00102\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0018\"\u0004\b4\u0010\u001aR\u0014\u00105\u001a\u00020\u0016X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0018¨\u00067"}, d2 = {"Lcom/wifiled/ipixels/utils/OTAHandler$Companion;", "", "<init>", "()V", "isOta", "", "()Z", "setOta", "(Z)V", "otaData", "", "getOtaData", "()[B", "setOtaData", "([B)V", "sendArray", "", "getSendArray", "()Ljava/util/List;", "setSendArray", "(Ljava/util/List;)V", "sendIndex", "", "getSendIndex", "()I", "setSendIndex", "(I)V", "lastValue", "", "getLastValue", "()D", "setLastValue", "(D)V", "offSet", "getOffSet", "setOffSet", "nextLength", "getNextLength", "setNextLength", "address", "getAddress", "setAddress", "sendCount", "getSendCount", "setSendCount", "timeOut", "getTimeOut", "setTimeOut", "isCancel", "setCancel", "errorCount", "getErrorCount", "setErrorCount", "limitLength", "getLimitLength", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isOta() {
            return OTAHandler.isOta;
        }

        public final void setOta(boolean z) {
            OTAHandler.isOta = z;
        }

        public final byte[] getOtaData() {
            return OTAHandler.otaData;
        }

        public final void setOtaData(byte[] bArr) {
            OTAHandler.otaData = bArr;
        }

        public final List<byte[]> getSendArray() {
            return OTAHandler.sendArray;
        }

        public final void setSendArray(List<byte[]> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            OTAHandler.sendArray = list;
        }

        public final int getSendIndex() {
            return OTAHandler.sendIndex;
        }

        public final void setSendIndex(int i) {
            OTAHandler.sendIndex = i;
        }

        public final double getLastValue() {
            return OTAHandler.lastValue;
        }

        public final void setLastValue(double d) {
            OTAHandler.lastValue = d;
        }

        public final int getOffSet() {
            return OTAHandler.offSet;
        }

        public final void setOffSet(int i) {
            OTAHandler.offSet = i;
        }

        public final int getNextLength() {
            return OTAHandler.nextLength;
        }

        public final void setNextLength(int i) {
            OTAHandler.nextLength = i;
        }

        public final int getAddress() {
            return OTAHandler.address;
        }

        public final void setAddress(int i) {
            OTAHandler.address = i;
        }

        public final int getSendCount() {
            return OTAHandler.sendCount;
        }

        public final void setSendCount(int i) {
            OTAHandler.sendCount = i;
        }

        public final int getTimeOut() {
            return OTAHandler.timeOut;
        }

        public final void setTimeOut(int i) {
            OTAHandler.timeOut = i;
        }

        public final boolean isCancel() {
            return OTAHandler.isCancel;
        }

        public final void setCancel(boolean z) {
            OTAHandler.isCancel = z;
        }

        public final int getErrorCount() {
            return OTAHandler.errorCount;
        }

        public final void setErrorCount(int i) {
            OTAHandler.errorCount = i;
        }

        public final int getLimitLength() {
            return OTAHandler.limitLength;
        }
    }

    public final SendCore.CallbackBuilder getCallback() {
        return this.callback;
    }

    public final void setCallback(SendCore.CallbackBuilder callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "<set-?>");
        this.callback = callbackBuilder;
    }

    public final void dealOtaForMCU(byte[] temp) {
        Intrinsics.checkNotNullParameter(temp, "temp");
        if (temp.length >= 5) {
            System.out.println((Object) "dealOtaForMCU");
            LogUtils.vTag("otaLog", "dealOtaForMCU onResult=" + com.wifiled.blelibrary.ble.utils.ByteUtils.toHexString(temp));
            if (temp[0] == 12 && temp[1] == 0 && temp[2] == -91 && temp[3] == 90 && temp.length >= 12) {
                int convertTo32 = convertTo32(temp[11], temp[10], temp[9], temp[8]);
                int convertTo322 = convertTo32(temp[7], temp[6], temp[5], temp[4]);
                LogUtils.vTag("otaLog", "dataLength--size :" + convertTo32);
                LogUtils.vTag("otaLog", "address--size :" + convertTo322);
                int i = convertTo32 + 12;
                ArrayList arrayList = new ArrayList();
                arrayList.add(Byte.valueOf((byte) (i & 255)));
                arrayList.add(Byte.valueOf((byte) ((i >> 8) & 255)));
                arrayList.add(Byte.valueOf(JSONB.Constants.BC_OBJECT_END));
                arrayList.add((byte) 90);
                for (int i2 = 4; i2 < 12; i2++) {
                    arrayList.add(Byte.valueOf(temp[i2]));
                }
                byte[] bArr = otaData;
                LogUtils.vTag("otaLog", "otaData--size :" + (bArr != null ? Integer.valueOf(bArr.length) : null));
                byte[] bArr2 = otaData;
                if (bArr2 != null) {
                    int i3 = convertTo322 + convertTo32;
                    for (int i4 = convertTo322; i4 < i3; i4++) {
                        arrayList.add(Byte.valueOf(bArr2[i4]));
                    }
                }
                if (address != convertTo322) {
                    offSet += convertTo32;
                }
                double d = offSet + convertTo322;
                byte[] bArr3 = otaData;
                double length = d / (bArr3 != null ? bArr3.length : 1);
                if (length - lastValue > AudioStats.AUDIO_AMPLITUDE_NONE) {
                    if (convertTo322 + convertTo32 < (bArr3 != null ? bArr3.length : 1)) {
                        showProgress(length, "OTA 进行中");
                    } else {
                        showProgress(1.0d, "OTA 进行中");
                    }
                }
                lastValue = length;
                List<byte[]> mutableList = CollectionsKt.toMutableList((Collection) cutArray(CollectionsKt.toByteArray(arrayList), limitLength));
                sendArray = mutableList;
                LogUtils.vTag("otaLog", "发送===>: 长度: " + i + " 偏移量: " + offSet + " 包数: " + mutableList.size());
                sendIndex = 0;
                errorCount = 0;
                isCancel = false;
                timeOut = 5;
                isOta = true;
                if (sendArray.isEmpty()) {
                    showError("OTA 失败");
                    return;
                }
                if (sendIndex >= sendArray.size()) {
                    sendArray.clear();
                    showSuccess("OTA 成功");
                    startWithTimeOut();
                } else {
                    byte[] bArr4 = sendArray.get(sendIndex);
                    sendCount = bArr4.length;
                    LogUtils.vTag("otaLog", "总包数: " + sendArray.size() + " 当前发送帧数: " + sendIndex + ", 长度: " + bArr4.length);
                    sendOtaData(bArr4);
                    startWithTimeOut();
                }
                address = convertTo322;
                nextLength = convertTo32;
            }
        }
    }

    private final List<byte[]> cutArray(byte[] data, int maxLength) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < data.length) {
            int i2 = i + maxLength;
            arrayList.add(ArraysKt.copyOfRange(data, i, Math.min(i2, data.length)));
            i = i2;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendOtaData(byte[] data) {
        LogUtils.vTag("otaLog", "发送数据:" + com.wifiled.blelibrary.ble.utils.ByteUtils.toHexString(data));
        ThreadUtils.delay(30L);
        SendCore.INSTANCE.sendCompat(data, new SendResultCallback() { // from class: com.wifiled.ipixels.utils.OTAHandler$sendOtaData$1
            @Override // com.wifiled.ipixels.core.send.SendResultCallback
            public void onError(int errorCode) {
            }

            @Override // com.wifiled.ipixels.core.send.SendResultCallback
            public void onResult(byte[] result) {
                Intrinsics.checkNotNullParameter(result, "result");
                if (result.length == 12) {
                    LogUtils.vTag("otaLog", "sendOtaData onResult=" + com.wifiled.blelibrary.ble.utils.ByteUtils.toHexString(result));
                    OTAHandler.this.dealOtaForMCU(result);
                    return;
                }
                LogUtils.vTag("otaLog", "sendOtaData onResult=" + com.wifiled.blelibrary.ble.utils.ByteUtils.toHexString(result));
                if (result[0] == 5 && result[1] == 0 && result[2] == -86 && result[3] == 85) {
                    OTAHandler.INSTANCE.getSendArray().clear();
                    if (result[4] == 1) {
                        OTAHandler.INSTANCE.setOta(false);
                        OTAHandler.this.showSuccess("OTA 成功");
                    } else {
                        OTAHandler.INSTANCE.setOta(false);
                        OTAHandler.this.showError("OTA 失败");
                    }
                    OTAHandler.INSTANCE.setOtaData(null);
                    return;
                }
                OTAHandler.INSTANCE.setSendIndex(OTAHandler.INSTANCE.getSendIndex() + 1);
                LogUtils.vTag("otaLog", "sendOtaData sendIndex=" + OTAHandler.INSTANCE.getSendIndex() + "     sendArray.size-" + OTAHandler.INSTANCE.getSendArray().size());
                if (OTAHandler.INSTANCE.getSendIndex() <= OTAHandler.INSTANCE.getSendArray().size() - 1) {
                    OTAHandler.this.sendOtaData(OTAHandler.INSTANCE.getSendArray().get(OTAHandler.INSTANCE.getSendIndex()));
                }
            }
        });
    }

    private final void startWithTimeOut() {
        System.out.println((Object) "启动超时处理");
    }

    private final void showProgress(double progress, String message) {
        Function1<Integer, Unit> progressAction$app_googleRelease = this.callback.getProgressAction$app_googleRelease();
        if (progressAction$app_googleRelease != null) {
            progressAction$app_googleRelease.invoke(Integer.valueOf((int) (progress * 100)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSuccess(String message) {
        Function0<Unit> completeAction$app_googleRelease = this.callback.getCompleteAction$app_googleRelease();
        if (completeAction$app_googleRelease != null) {
            completeAction$app_googleRelease.invoke();
        }
        Function0<Unit> successAction$app_googleRelease = this.callback.getSuccessAction$app_googleRelease();
        if (successAction$app_googleRelease != null) {
            successAction$app_googleRelease.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showError(String message) {
        Function0<Unit> completeAction$app_googleRelease = this.callback.getCompleteAction$app_googleRelease();
        if (completeAction$app_googleRelease != null) {
            completeAction$app_googleRelease.invoke();
        }
        Function1<Integer, Unit> errorAction$app_googleRelease = this.callback.getErrorAction$app_googleRelease();
        if (errorAction$app_googleRelease != null) {
            errorAction$app_googleRelease.invoke(Integer.valueOf(CameraAccessExceptionCompat.CAMERA_UNAVAILABLE_DO_NOT_DISTURB));
        }
    }
}
