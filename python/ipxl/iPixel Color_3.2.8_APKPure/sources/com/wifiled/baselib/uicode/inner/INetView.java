package com.wifiled.baselib.uicode.inner;

import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.wifiled.baselib.uicode.net.CommonResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.Job;

/* compiled from: INetView.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001JW\u0010\u0002\u001a\u00020\u00032\u001c\u0010\u0004\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00052*\u0010\b\u001a&\b\u0001\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tH&¢\u0006\u0002\u0010\fJ¦\u0001\u0010\r\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u000e2\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u000f0\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00052:\u0010\u0010\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u0001H\u000e¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00070\u001121\u0010\b\u001a-\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0015\u0012\u0013\u0018\u0001H\u000e¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00070\tH&¢\u0006\u0002\u0010\u0016JW\u0010\u0017\u001a\u00020\u00032\u001c\u0010\u0004\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00052*\u0010\b\u001a&\b\u0001\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tH&¢\u0006\u0002\u0010\fJ¦\u0001\u0010\u0018\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u000e2\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u000f0\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00052:\u0010\u0010\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u0001H\u000e¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00070\u001121\u0010\b\u001a-\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0015\u0012\u0013\u0018\u0001H\u000e¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00070\tH&¢\u0006\u0002\u0010\u0016¨\u0006\u0019À\u0006\u0003"}, d2 = {"Lcom/wifiled/baselib/uicode/inner/INetView;", "", "launch", "Lkotlinx/coroutines/Job;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "failed", "Lkotlin/Function3;", "", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/Job;", "netLaunch", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/wifiled/baselib/uicode/net/CommonResponse;", "success", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, NotificationCompat.CATEGORY_MESSAGE, "d", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/Job;", "ioLaunch", "ioNetLaunch", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface INetView {
    Job ioLaunch(Function1<? super Continuation<? super Unit>, ? extends Object> block, Function3<? super Integer, ? super String, ? super Continuation<? super Unit>, ? extends Object> failed);

    <T> Job ioNetLaunch(Function1<? super Continuation<? super CommonResponse<T>>, ? extends Object> block, Function2<? super String, ? super T, Unit> success, Function3<? super Integer, ? super String, ? super T, Unit> failed);

    Job launch(Function1<? super Continuation<? super Unit>, ? extends Object> block, Function3<? super Integer, ? super String, ? super Continuation<? super Unit>, ? extends Object> failed);

    <T> Job netLaunch(Function1<? super Continuation<? super CommonResponse<T>>, ? extends Object> block, Function2<? super String, ? super T, Unit> success, Function3<? super Integer, ? super String, ? super T, Unit> failed);
}
