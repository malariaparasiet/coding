package com.wifiled.baselib.uicode.net;

import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NetExt.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u001a£\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032:\u0010\u0004\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u0001H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u00052O\u0010\u000b\u001aK\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000e\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u0001H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\f¨\u0006\u000f"}, d2 = {"process", "", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/wifiled/baselib/uicode/net/CommonResponse;", "success", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, NotificationCompat.CATEGORY_MESSAGE, "d", "failed", "Lkotlin/Function3;", "", NotificationCompat.CATEGORY_STATUS, "baselib_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NetExtKt {
    public static final <T> void process(CommonResponse<T> commonResponse, Function2<? super String, ? super T, Unit> success, Function3<? super Integer, ? super String, ? super T, Unit> failed) {
        Intrinsics.checkNotNullParameter(commonResponse, "<this>");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(failed, "failed");
        try {
            if (commonResponse.getStatus() == 0) {
                success.invoke(commonResponse.getMsg(), commonResponse.getData());
            } else {
                failed.invoke(Integer.valueOf(commonResponse.getStatus()), commonResponse.getMsg(), commonResponse.getData());
            }
        } catch (Throwable th) {
            failed.invoke(Integer.valueOf(HttpNetCode.DATA_ERROR), th.getMessage(), null);
        }
    }
}
