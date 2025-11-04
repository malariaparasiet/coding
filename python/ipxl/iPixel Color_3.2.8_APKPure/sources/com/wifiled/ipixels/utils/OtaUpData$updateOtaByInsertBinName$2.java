package com.wifiled.ipixels.utils;

import android.content.Context;
import com.alibaba.fastjson2.JSONB;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.blelibrary.ble.queue.reconnect.DefaultReConnectHandler;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.send.SendResultCallback;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: OtaUpData.kt */
@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/wifiled/ipixels/utils/OtaUpData$updateOtaByInsertBinName$2", "Lcom/wifiled/ipixels/core/send/SendResultCallback;", "onResult", "", "result", "", "onError", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OtaUpData$updateOtaByInsertBinName$2 implements SendResultCallback {
    final /* synthetic */ SendCore.CallbackBuilder $callback;
    final /* synthetic */ Ref.IntRef $iTotalLoopCount;
    final /* synthetic */ InputStream $inputStream;
    final /* synthetic */ Ref.IntRef $otaType;
    final /* synthetic */ byte[] $readBuf;
    final /* synthetic */ Context $tContext;

    OtaUpData$updateOtaByInsertBinName$2(Ref.IntRef intRef, byte[] bArr, Ref.IntRef intRef2, SendCore.CallbackBuilder callbackBuilder, Context context, InputStream inputStream) {
        this.$otaType = intRef;
        this.$readBuf = bArr;
        this.$iTotalLoopCount = intRef2;
        this.$callback = callbackBuilder;
        this.$tContext = context;
        this.$inputStream = inputStream;
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onResult(byte[] result) {
        Intrinsics.checkNotNullParameter(result, "result");
        Iterator it = CollectionsKt.mutableListOf(new byte[]{5, 0, 0, JSONB.Constants.BC_INT64_SHORT_MIN, 1}, new byte[]{5, 0, 2, JSONB.Constants.BC_INT64_SHORT_MIN, 1}).iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (Arrays.equals((byte[]) it.next(), result)) {
                z = true;
            }
        }
        if (z) {
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            int i = this.$otaType.element + 1;
            byte[] bArr = this.$readBuf;
            int i2 = this.$iTotalLoopCount.element;
            final SendCore.CallbackBuilder callbackBuilder = this.$callback;
            final Context context = this.$tContext;
            final Ref.IntRef intRef = this.$otaType;
            final InputStream inputStream = this.$inputStream;
            SendCore.sendOtaDataInner(i, bArr, i2, new Function1() { // from class: com.wifiled.ipixels.utils.OtaUpData$updateOtaByInsertBinName$2$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit onResult$lambda$11;
                    onResult$lambda$11 = OtaUpData$updateOtaByInsertBinName$2.onResult$lambda$11(Ref.BooleanRef.this, callbackBuilder, context, intRef, inputStream, (SendCore.CallbackBuilder) obj);
                    return onResult$lambda$11;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$11(final Ref.BooleanRef booleanRef, final SendCore.CallbackBuilder callbackBuilder, final Context context, final Ref.IntRef intRef, final InputStream inputStream, SendCore.CallbackBuilder sendOtaDataInner) {
        Intrinsics.checkNotNullParameter(sendOtaDataInner, "$this$sendOtaDataInner");
        sendOtaDataInner.onStart(new Function0() { // from class: com.wifiled.ipixels.utils.OtaUpData$updateOtaByInsertBinName$2$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onResult$lambda$11$lambda$1;
                onResult$lambda$11$lambda$1 = OtaUpData$updateOtaByInsertBinName$2.onResult$lambda$11$lambda$1(Ref.BooleanRef.this, callbackBuilder);
                return onResult$lambda$11$lambda$1;
            }
        });
        sendOtaDataInner.onProgress(new Function1() { // from class: com.wifiled.ipixels.utils.OtaUpData$updateOtaByInsertBinName$2$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onResult$lambda$11$lambda$3;
                onResult$lambda$11$lambda$3 = OtaUpData$updateOtaByInsertBinName$2.onResult$lambda$11$lambda$3(context, intRef, callbackBuilder, ((Integer) obj).intValue());
                return onResult$lambda$11$lambda$3;
            }
        });
        sendOtaDataInner.onCompleted(new Function0() { // from class: com.wifiled.ipixels.utils.OtaUpData$updateOtaByInsertBinName$2$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onResult$lambda$11$lambda$5;
                onResult$lambda$11$lambda$5 = OtaUpData$updateOtaByInsertBinName$2.onResult$lambda$11$lambda$5(inputStream, callbackBuilder, context);
                return onResult$lambda$11$lambda$5;
            }
        });
        sendOtaDataInner.onError(new Function1() { // from class: com.wifiled.ipixels.utils.OtaUpData$updateOtaByInsertBinName$2$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onResult$lambda$11$lambda$6;
                onResult$lambda$11$lambda$6 = OtaUpData$updateOtaByInsertBinName$2.onResult$lambda$11$lambda$6(Ref.BooleanRef.this, context, ((Integer) obj).intValue());
                return onResult$lambda$11$lambda$6;
            }
        });
        sendOtaDataInner.onResult(new Function1() { // from class: com.wifiled.ipixels.utils.OtaUpData$updateOtaByInsertBinName$2$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onResult$lambda$11$lambda$10;
                onResult$lambda$11$lambda$10 = OtaUpData$updateOtaByInsertBinName$2.onResult$lambda$11$lambda$10(context, (byte[]) obj);
                return onResult$lambda$11$lambda$10;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$11$lambda$1(Ref.BooleanRef booleanRef, SendCore.CallbackBuilder callbackBuilder) {
        if (booleanRef.element) {
            booleanRef.element = false;
        }
        Function0<Unit> startAction$app_googleRelease = callbackBuilder.getStartAction$app_googleRelease();
        if (startAction$app_googleRelease != null) {
            startAction$app_googleRelease.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$11$lambda$3(final Context context, final Ref.IntRef intRef, final SendCore.CallbackBuilder callbackBuilder, final int i) {
        if (AppConfig.INSTANCE.getConnectType() == -1) {
            return Unit.INSTANCE;
        }
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.utils.OtaUpData$updateOtaByInsertBinName$2$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onResult$lambda$11$lambda$3$lambda$2;
                onResult$lambda$11$lambda$3$lambda$2 = OtaUpData$updateOtaByInsertBinName$2.onResult$lambda$11$lambda$3$lambda$2(context, intRef, i, callbackBuilder);
                return onResult$lambda$11$lambda$3$lambda$2;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$11$lambda$3$lambda$2(Context context, Ref.IntRef intRef, int i, final SendCore.CallbackBuilder callbackBuilder) {
        Timer timer;
        TimerTask timerTask;
        Timer timer2;
        TimerTask timerTask2;
        timer = OtaUpData.timer;
        TimerTask timerTask3 = null;
        if (timer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timer");
            timer = null;
        }
        timer.cancel();
        timerTask = OtaUpData.timertask;
        if (timerTask == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timertask");
            timerTask = null;
        }
        timerTask.cancel();
        String str = context.getString(R.string.msg_ota_upd_mcu) + ":";
        if (intRef.element > 1) {
            str = context.getString(R.string.msg_ota_upd_wifi);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        }
        OtaUpData otaUpData = OtaUpData.INSTANCE;
        OtaUpData.timer = new Timer();
        OtaUpData otaUpData2 = OtaUpData.INSTANCE;
        OtaUpData.timertask = new TimerTask() { // from class: com.wifiled.ipixels.utils.OtaUpData$updateOtaByInsertBinName$2$onResult$2$2$1$1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Function1<Integer, Unit> errorAction$app_googleRelease = SendCore.CallbackBuilder.this.getErrorAction$app_googleRelease();
                if (errorAction$app_googleRelease != null) {
                    errorAction$app_googleRelease.invoke(10011);
                }
            }
        };
        timer2 = OtaUpData.timer;
        if (timer2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timer");
            timer2 = null;
        }
        timerTask2 = OtaUpData.timertask;
        if (timerTask2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timertask");
        } else {
            timerTask3 = timerTask2;
        }
        timer2.schedule(timerTask3, 15000L);
        UtilsExtensionKt.showLoadingDialog(context, true, str + " " + i + " %", true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$11$lambda$5(InputStream inputStream, SendCore.CallbackBuilder callbackBuilder, final Context context) {
        Timer timer;
        TimerTask timerTask;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.utils.OtaUpData$updateOtaByInsertBinName$2$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onResult$lambda$11$lambda$5$lambda$4;
                onResult$lambda$11$lambda$5$lambda$4 = OtaUpData$updateOtaByInsertBinName$2.onResult$lambda$11$lambda$5$lambda$4(context);
                return onResult$lambda$11$lambda$5$lambda$4;
            }
        });
        timer = OtaUpData.timer;
        TimerTask timerTask2 = null;
        if (timer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timer");
            timer = null;
        }
        timer.cancel();
        timerTask = OtaUpData.timertask;
        if (timerTask == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timertask");
        } else {
            timerTask2 = timerTask;
        }
        timerTask2.cancel();
        inputStream.close();
        AppConfig.INSTANCE.setBCheckOta(false);
        AppConfig.INSTANCE.getArrayDevinfo().clear();
        Function0<Unit> completeAction$app_googleRelease = callbackBuilder.getCompleteAction$app_googleRelease();
        if (completeAction$app_googleRelease != null) {
            completeAction$app_googleRelease.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$11$lambda$5$lambda$4(Context context) {
        UtilsExtensionKt.hideDialogStyle1();
        ToastUtil.show(context != null ? context.getString(R.string.ota_upd_success) : null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$11$lambda$6(Ref.BooleanRef booleanRef, Context context, int i) {
        Timer timer;
        TimerTask timerTask;
        if (!booleanRef.element) {
            timer = OtaUpData.timer;
            if (timer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("timer");
                timer = null;
            }
            timer.cancel();
            timerTask = OtaUpData.timertask;
            if (timerTask == null) {
                Intrinsics.throwUninitializedPropertyAccessException("timertask");
                timerTask = null;
            }
            timerTask.cancel();
            UtilsExtensionKt.hideDialogStyle1();
            ToastUtil.show(context != null ? context.getString(R.string.msg_send_fail) : null);
            booleanRef.element = true;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$11$lambda$10(final Context context, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String str = new String(it, Charsets.UTF_8);
        LogUtils.v("$>>>[onResult]: ".concat(str));
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "dev disconnect", false, 2, (Object) null)) {
            UtilsExtensionKt.hideDialogStyle1();
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.utils.OtaUpData$updateOtaByInsertBinName$2$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit onResult$lambda$11$lambda$10$lambda$8;
                    onResult$lambda$11$lambda$10$lambda$8 = OtaUpData$updateOtaByInsertBinName$2.onResult$lambda$11$lambda$10$lambda$8(context);
                    return onResult$lambda$11$lambda$10$lambda$8;
                }
            });
            UtilsExtensionKt.uiDelay(new Function0() { // from class: com.wifiled.ipixels.utils.OtaUpData$updateOtaByInsertBinName$2$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit onResult$lambda$11$lambda$10$lambda$9;
                    onResult$lambda$11$lambda$10$lambda$9 = OtaUpData$updateOtaByInsertBinName$2.onResult$lambda$11$lambda$10$lambda$9();
                    return onResult$lambda$11$lambda$10$lambda$9;
                }
            }, DefaultReConnectHandler.DEFAULT_CONNECT_DELAY);
        }
        if (it.length <= 4) {
            UtilsExtensionKt.hideDialogStyle1();
            return Unit.INSTANCE;
        }
        if (it.length == 5) {
            byte b = it[4];
            if (b == 2) {
                LogUtils.v("SendCore>>>[onResult]:空间不足 ");
                UtilsExtensionKt.hideDialogStyle1();
            } else if (b == 3) {
                LogUtils.v("SendCore>>>[onResult]:保存文件成功 ");
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$11$lambda$10$lambda$8(Context context) {
        if (AppConfig.INSTANCE.getBCheckOta()) {
            String string = context.getString(R.string.msg_upd_fail);
            Intrinsics.checkNotNull(string);
            UtilsExtensionKt.showLoadingDialog$default(context, false, string, false, 2, (Object) null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$11$lambda$10$lambda$9() {
        UtilsExtensionKt.hideDialogStyle1();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onError$lambda$12(Context context, int i) {
        ToastUtil.show(context.getString(R.string.msg_send_fail) + "(" + i + ")");
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onError(final int result) {
        final Context context = this.$tContext;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.utils.OtaUpData$updateOtaByInsertBinName$2$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onError$lambda$12;
                onError$lambda$12 = OtaUpData$updateOtaByInsertBinName$2.onError$lambda$12(context, result);
                return onError$lambda$12;
            }
        });
    }
}
