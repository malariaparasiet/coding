package com.wifiled.ipixels.utils;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.utils.ContextHolder;
import com.wifiled.blelibrary.ble.utils.CrcUtils;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.ota.OTARequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* compiled from: OtaUpData.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JF\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u001e\u0010\u0010\u001a\u001a\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\t0\u0011J/\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000f2\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\t0\u0017¢\u0006\u0002\b\u0019J/\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0015\u001a\u00020\u000f2\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\t0\u0017¢\u0006\u0002\b\u0019J/\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0015\u001a\u00020\u000f2\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\t0\u0017¢\u0006\u0002\b\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/wifiled/ipixels/utils/OtaUpData;", "", "<init>", "()V", "timer", "Ljava/util/Timer;", "timertask", "Ljava/util/TimerTask;", "checkIsNeedOta", "", "cid", "", "pid", "macAddress", "context", "Landroid/content/Context;", NotificationCompat.CATEGORY_CALL, "Lkotlin/Function3;", "", "updateOtaByInsertBinName", "fileName", "tContext", "callbackBuilder", "Lkotlin/Function1;", "Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;", "Lkotlin/ExtensionFunctionType;", "updateOtaByByte", "data", "", "updateOtaByByte2", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OtaUpData {
    public static final OtaUpData INSTANCE = new OtaUpData();
    private static Timer timer;
    private static TimerTask timertask;

    private OtaUpData() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void checkIsNeedOta(final String cid, final String pid, final String macAddress, final Context context, final Function3<? super Integer, ? super Integer, ? super String, Unit> call) {
        Intrinsics.checkNotNullParameter(cid, "cid");
        Intrinsics.checkNotNullParameter(pid, "pid");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(call, "call");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = context;
        SendCore.getHwInfo(new Function1() { // from class: com.wifiled.ipixels.utils.OtaUpData$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit checkIsNeedOta$lambda$1;
                checkIsNeedOta$lambda$1 = OtaUpData.checkIsNeedOta$lambda$1(Function3.this, macAddress, context, cid, pid, objectRef, (SendCore.CallbackBuilder) obj);
                return checkIsNeedOta$lambda$1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit checkIsNeedOta$lambda$1(final Function3 function3, final String str, final Context context, final String str2, final String str3, final Ref.ObjectRef objectRef, SendCore.CallbackBuilder getHwInfo) {
        Intrinsics.checkNotNullParameter(getHwInfo, "$this$getHwInfo");
        getHwInfo.onResult(new Function1() { // from class: com.wifiled.ipixels.utils.OtaUpData$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit checkIsNeedOta$lambda$1$lambda$0;
                checkIsNeedOta$lambda$1$lambda$0 = OtaUpData.checkIsNeedOta$lambda$1$lambda$0(Function3.this, str, context, str2, str3, objectRef, (byte[]) obj);
                return checkIsNeedOta$lambda$1$lambda$0;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit checkIsNeedOta$lambda$1$lambda$0(Function3 function3, String str, Context context, String str2, String str3, Ref.ObjectRef objectRef, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.length == 8 && it[0] == 8 && it[1] == 0 && it[2] == 5 && it[3] == Byte.MIN_VALUE) {
            String arrays = Arrays.toString(it);
            Intrinsics.checkNotNullExpressionValue(arrays, "toString(...)");
            LogUtils.v("查询结果" + arrays + "MCU更新" + ((int) it[4]));
            AppConfig.INSTANCE.getArrayDevinfo().clear();
            AppConfig.INSTANCE.getArrayDevinfo().addAll(ArraysKt.toMutableList(it));
            StringBuilder sb = new StringBuilder();
            StringBuilder append = sb.append(Byte.valueOf(it[4]));
            byte b = it[5];
            String sb2 = append.append(b < 10 ? "0" + ((int) b) : Byte.valueOf(b)).toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
            int parseInt = Integer.parseInt(sb2);
            StringsKt.clear(sb);
            StringBuilder append2 = sb.append(Byte.valueOf(it[6])).append(".");
            byte b2 = it[7];
            String sb3 = append2.append(b2 < 10 ? "0" + ((int) b2) : Byte.valueOf(b2)).toString();
            Intrinsics.checkNotNullExpressionValue(sb3, "toString(...)");
            AppConfig.INSTANCE.setMcuVersion(parseInt);
            function3.invoke(Integer.valueOf(it[4]), Integer.valueOf(parseInt), sb3);
            if (200 <= parseInt && parseInt < 1400 && !AppConfig.INSTANCE.getConnectList().contains(str)) {
                OTARequest.INSTANCE.checkOTA(context, parseInt, str2, str3, str, new OtaUpData$checkIsNeedOta$1$1$1(objectRef));
            } else if (2800 <= parseInt && parseInt < 3400 && !AppConfig.INSTANCE.getConnectList().contains(str)) {
                OTARequest.INSTANCE.checkOTA(context, parseInt, str2, str3, str, new OtaUpData$checkIsNeedOta$1$1$2(objectRef));
            }
        }
        return Unit.INSTANCE;
    }

    public final void updateOtaByInsertBinName(String fileName, Context tContext, Function1<? super SendCore.CallbackBuilder, Unit> callbackBuilder) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(tContext, "tContext");
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        LogUtils.v("$>>>[updateOtaByInsertBinName]: " + fileName);
        final SendCore.CallbackBuilder callbackBuilder2 = new SendCore.CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        try {
            FileInputStream fileInputStream = new FileInputStream("/data/data/" + ContextHolder.getContext().getPackageName() + "/ota/" + fileName);
            int available = fileInputStream.available();
            Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = available / 12288;
            if (available % 12288 != 0) {
                intRef.element++;
            }
            byte[] readBytes = ByteStreamsKt.readBytes(fileInputStream);
            byte[] int2byte = com.wifiled.blelibrary.ble.utils.ByteUtils.int2byte((int) CrcUtils.CRC32.CRC32(readBytes, 0, readBytes.length));
            Ref.IntRef intRef2 = new Ref.IntRef();
            TimerTask timerTask = null;
            if (StringsKt.contains$default((CharSequence) fileName, (CharSequence) "wifi_softAP", false, 2, (Object) null)) {
                intRef2.element = 2;
            }
            timer = new Timer();
            timertask = new TimerTask() { // from class: com.wifiled.ipixels.utils.OtaUpData$updateOtaByInsertBinName$1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    Function1<Integer, Unit> errorAction$app_googleRelease = SendCore.CallbackBuilder.this.getErrorAction$app_googleRelease();
                    if (errorAction$app_googleRelease != null) {
                        errorAction$app_googleRelease.invoke(10011);
                    }
                }
            };
            Timer timer2 = timer;
            if (timer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("timer");
                timer2 = null;
            }
            TimerTask timerTask2 = timertask;
            if (timerTask2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("timertask");
            } else {
                timerTask = timerTask2;
            }
            timer2.schedule(timerTask, 15000L);
            SendCore sendCore = SendCore.INSTANCE;
            int i = intRef.element;
            Intrinsics.checkNotNull(int2byte);
            sendCore.updateOtaMcuOrWifiStep1(i, int2byte, available, intRef2.element, new OtaUpData$updateOtaByInsertBinName$2(intRef2, readBytes, intRef, callbackBuilder2, tContext, fileInputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateOtaByByte(byte[] data, Context tContext, Function1<? super SendCore.CallbackBuilder, Unit> callbackBuilder) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(tContext, "tContext");
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        final SendCore.CallbackBuilder callbackBuilder2 = new SendCore.CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        try {
            int length = data.length;
            Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = length / 12288;
            if (length % 12288 != 0) {
                intRef.element++;
            }
            byte[] int2byte = com.wifiled.blelibrary.ble.utils.ByteUtils.int2byte((int) CrcUtils.CRC32.CRC32(data, 0, data.length));
            Ref.IntRef intRef2 = new Ref.IntRef();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = tContext;
            timer = new Timer();
            timertask = new TimerTask() { // from class: com.wifiled.ipixels.utils.OtaUpData$updateOtaByByte$1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    Function1<Integer, Unit> errorAction$app_googleRelease = SendCore.CallbackBuilder.this.getErrorAction$app_googleRelease();
                    if (errorAction$app_googleRelease != null) {
                        errorAction$app_googleRelease.invoke(10011);
                    }
                }
            };
            Timer timer2 = timer;
            TimerTask timerTask = null;
            if (timer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("timer");
                timer2 = null;
            }
            TimerTask timerTask2 = timertask;
            if (timerTask2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("timertask");
            } else {
                timerTask = timerTask2;
            }
            timer2.schedule(timerTask, 15000L);
            SendCore sendCore = SendCore.INSTANCE;
            int i = intRef.element;
            Intrinsics.checkNotNull(int2byte);
            sendCore.updateOtaMcuOrWifiStep1(i, int2byte, length, intRef2.element, new OtaUpData$updateOtaByByte$2(intRef2, data, intRef, callbackBuilder2, objectRef));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final void updateOtaByByte2(byte[] data, Context tContext, Function1<? super SendCore.CallbackBuilder, Unit> callbackBuilder) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(tContext, "tContext");
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        OTAHandler.INSTANCE.setOtaData(data);
        OTAHandler.INSTANCE.setOffSet(0);
        OTAHandler.INSTANCE.setAddress(-1);
        OTAHandler.INSTANCE.setNextLength(0);
        SendCore.CallbackBuilder callbackBuilder2 = new SendCore.CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        try {
            SendCore.INSTANCE.upDataOTA2900Start(new OtaUpData$updateOtaByByte2$sendResultCallback$1(callbackBuilder2, tContext));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
