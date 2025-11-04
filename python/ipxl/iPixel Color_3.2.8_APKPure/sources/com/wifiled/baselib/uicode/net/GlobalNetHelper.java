package com.wifiled.baselib.uicode.net;

import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.blankj.utilcode.util.LogUtils;
import com.google.gson.JsonSyntaxException;
import com.wifiled.baselib.uicode.inner.INetView;
import com.wifiled.baselib.uicode.utils.UICoreConfig;
import java.io.EOFException;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import javax.net.ssl.SSLException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: GlobalNetHelper.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0003\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JW\u0010\u0004\u001a\u00020\u00052\u001c\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00072*\u0010\u000b\u001a&\b\u0001\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\fH\u0016¢\u0006\u0002\u0010\u000fJ¦\u0001\u0010\u0010\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u00112\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u00120\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00072:\u0010\u0013\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0015\u0012\u0013\u0018\u0001H\u0011¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\t0\u001421\u0010\u000b\u001a-\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0015\u0012\u0013\u0018\u0001H\u0011¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\t0\fH\u0016¢\u0006\u0002\u0010\u0019JW\u0010\u001a\u001a\u00020\u00052\u001c\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00072*\u0010\u000b\u001a&\b\u0001\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\fH\u0016¢\u0006\u0002\u0010\u000fJ¦\u0001\u0010\u001b\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u00112\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u00120\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00072:\u0010\u0013\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0015\u0012\u0013\u0018\u0001H\u0011¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\t0\u001421\u0010\u000b\u001a-\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0015\u0012\u0013\u0018\u0001H\u0011¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\t0\fH\u0016¢\u0006\u0002\u0010\u0019JB\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u001e2*\u0010\u000b\u001a&\b\u0001\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\fH\u0082@¢\u0006\u0002\u0010\u001fJI\u0010 \u001a\u00020\t\"\u0004\b\u0000\u0010\u00112\u0006\u0010\u001d\u001a\u00020\u001e21\u0010\u000b\u001a-\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0015\u0012\u0013\u0018\u0001H\u0011¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\t0\fH\u0002¨\u0006!"}, d2 = {"Lcom/wifiled/baselib/uicode/net/GlobalNetHelper;", "Lcom/wifiled/baselib/uicode/inner/INetView;", "<init>", "()V", "launch", "Lkotlinx/coroutines/Job;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "failed", "Lkotlin/Function3;", "", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/Job;", "netLaunch", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/wifiled/baselib/uicode/net/CommonResponse;", "success", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, NotificationCompat.CATEGORY_MESSAGE, "d", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/Job;", "ioLaunch", "ioNetLaunch", "onFailSuspend", "t", "", "(Ljava/lang/Throwable;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onFailException", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GlobalNetHelper implements INetView {
    public static final GlobalNetHelper INSTANCE = new GlobalNetHelper();

    private GlobalNetHelper() {
    }

    @Override // com.wifiled.baselib.uicode.inner.INetView
    public Job launch(Function1<? super Continuation<? super Unit>, ? extends Object> block, Function3<? super Integer, ? super String, ? super Continuation<? super Unit>, ? extends Object> failed) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(failed, "failed");
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new GlobalNetHelper$launch$job$1(block, failed, null), 2, null);
        return launch$default;
    }

    @Override // com.wifiled.baselib.uicode.inner.INetView
    public <T> Job netLaunch(Function1<? super Continuation<? super CommonResponse<T>>, ? extends Object> block, Function2<? super String, ? super T, Unit> success, Function3<? super Integer, ? super String, ? super T, Unit> failed) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(failed, "failed");
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new GlobalNetHelper$netLaunch$job$1(block, success, failed, null), 2, null);
        return launch$default;
    }

    @Override // com.wifiled.baselib.uicode.inner.INetView
    public Job ioLaunch(Function1<? super Continuation<? super Unit>, ? extends Object> block, Function3<? super Integer, ? super String, ? super Continuation<? super Unit>, ? extends Object> failed) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(failed, "failed");
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new GlobalNetHelper$ioLaunch$job$1(block, failed, null), 2, null);
        return launch$default;
    }

    @Override // com.wifiled.baselib.uicode.inner.INetView
    public <T> Job ioNetLaunch(Function1<? super Continuation<? super CommonResponse<T>>, ? extends Object> block, Function2<? super String, ? super T, Unit> success, Function3<? super Integer, ? super String, ? super T, Unit> failed) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(failed, "failed");
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new GlobalNetHelper$ioNetLaunch$job$1(block, success, failed, null), 2, null);
        return launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object onFailSuspend(Throwable th, Function3<? super Integer, ? super String, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
        String message = th.getMessage();
        if (!(message != null ? StringsKt.contains$default((CharSequence) message, (CharSequence) "HTTP 401", false, 2, (Object) null) : false)) {
            LogUtils.e(th);
            if (th instanceof EOFException) {
                if (UICoreConfig.INSTANCE.getMode()) {
                    Object invoke = function3.invoke(Boxing.boxInt(HttpNetCode.NET_CONNECT_ERROR), "网络读取异常：" + th.getMessage(), continuation);
                    return invoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke : Unit.INSTANCE;
                }
                Object invoke2 = function3.invoke(Boxing.boxInt(HttpNetCode.NET_CONNECT_ERROR), "网络读取异常", continuation);
                return invoke2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke2 : Unit.INSTANCE;
            }
            if (th instanceof SocketTimeoutException) {
                if (UICoreConfig.INSTANCE.getMode()) {
                    Object invoke3 = function3.invoke(Boxing.boxInt(HttpNetCode.NET_TIMEOUT), "网络超时：" + th.getMessage(), continuation);
                    return invoke3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke3 : Unit.INSTANCE;
                }
                Object invoke4 = function3.invoke(Boxing.boxInt(HttpNetCode.NET_TIMEOUT), "网络超时", continuation);
                return invoke4 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke4 : Unit.INSTANCE;
            }
            if (th instanceof SSLException) {
                if (UICoreConfig.INSTANCE.getMode()) {
                    Object invoke5 = function3.invoke(Boxing.boxInt(HttpNetCode.NET_CONNECT_ERROR), "SSL校验未通过：" + th.getMessage(), continuation);
                    return invoke5 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke5 : Unit.INSTANCE;
                }
                Object invoke6 = function3.invoke(Boxing.boxInt(HttpNetCode.NET_CONNECT_ERROR), "SSL校验未通过", continuation);
                return invoke6 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke6 : Unit.INSTANCE;
            }
            if (th instanceof ParseException) {
                if (UICoreConfig.INSTANCE.getMode()) {
                    Object invoke7 = function3.invoke(Boxing.boxInt(HttpNetCode.PARSE_ERROR), "Parse解析异常：" + th.getMessage(), continuation);
                    return invoke7 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke7 : Unit.INSTANCE;
                }
                Object invoke8 = function3.invoke(Boxing.boxInt(HttpNetCode.PARSE_ERROR), "Parse解析异常", continuation);
                return invoke8 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke8 : Unit.INSTANCE;
            }
            if (th instanceof JsonSyntaxException) {
                if (UICoreConfig.INSTANCE.getMode()) {
                    Object invoke9 = function3.invoke(Boxing.boxInt(HttpNetCode.JSON_ERROR), "Json解析异常：" + th.getMessage(), continuation);
                    return invoke9 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke9 : Unit.INSTANCE;
                }
                Object invoke10 = function3.invoke(Boxing.boxInt(HttpNetCode.JSON_ERROR), "Json解析异常", continuation);
                return invoke10 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke10 : Unit.INSTANCE;
            }
            if (UICoreConfig.INSTANCE.getMode()) {
                Object invoke11 = function3.invoke(Boxing.boxInt(HttpNetCode.NET_CONNECT_ERROR), "网络繁忙：" + th.getMessage(), continuation);
                return invoke11 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke11 : Unit.INSTANCE;
            }
            Object invoke12 = function3.invoke(Boxing.boxInt(HttpNetCode.NET_CONNECT_ERROR), "网络繁忙", continuation);
            return invoke12 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke12 : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> void onFailException(Throwable t, Function3<? super Integer, ? super String, ? super T, Unit> failed) {
        String message = t.getMessage();
        if (message != null ? StringsKt.contains$default((CharSequence) message, (CharSequence) "HTTP 401", false, 2, (Object) null) : false) {
            return;
        }
        LogUtils.e(t);
        if (t instanceof EOFException) {
            if (UICoreConfig.INSTANCE.getMode()) {
                failed.invoke(Integer.valueOf(HttpNetCode.NET_CONNECT_ERROR), "网络读取异常：" + t.getMessage(), null);
                return;
            } else {
                failed.invoke(Integer.valueOf(HttpNetCode.NET_CONNECT_ERROR), "网络读取异常", null);
                return;
            }
        }
        if (t instanceof SocketTimeoutException) {
            if (UICoreConfig.INSTANCE.getMode()) {
                failed.invoke(Integer.valueOf(HttpNetCode.NET_TIMEOUT), "网络超时：" + t.getMessage(), null);
                return;
            } else {
                failed.invoke(Integer.valueOf(HttpNetCode.NET_TIMEOUT), "网络超时", null);
                return;
            }
        }
        if (t instanceof SSLException) {
            if (UICoreConfig.INSTANCE.getMode()) {
                failed.invoke(Integer.valueOf(HttpNetCode.NET_CONNECT_ERROR), "SSL校验未通过：" + t.getMessage(), null);
                return;
            } else {
                failed.invoke(Integer.valueOf(HttpNetCode.NET_CONNECT_ERROR), "SSL校验未通过", null);
                return;
            }
        }
        if (t instanceof ParseException) {
            if (UICoreConfig.INSTANCE.getMode()) {
                failed.invoke(Integer.valueOf(HttpNetCode.PARSE_ERROR), "Parse解析异常：" + t.getMessage(), null);
                return;
            } else {
                failed.invoke(Integer.valueOf(HttpNetCode.PARSE_ERROR), "Parse解析异常", null);
                return;
            }
        }
        if (t instanceof JsonSyntaxException) {
            if (UICoreConfig.INSTANCE.getMode()) {
                failed.invoke(Integer.valueOf(HttpNetCode.JSON_ERROR), "Json解析异常：" + t.getMessage(), null);
                return;
            } else {
                failed.invoke(Integer.valueOf(HttpNetCode.JSON_ERROR), "Json解析异常", null);
                return;
            }
        }
        if (UICoreConfig.INSTANCE.getMode()) {
            failed.invoke(Integer.valueOf(HttpNetCode.NET_CONNECT_ERROR), "网络繁忙：" + t.getMessage(), null);
        } else {
            failed.invoke(Integer.valueOf(HttpNetCode.NET_CONNECT_ERROR), "网络繁忙", null);
        }
    }
}
