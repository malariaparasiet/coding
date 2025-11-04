package com.google.android.play.core.ktx;

import com.jieli.jl_bt_ota.constant.Command;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: com.google.android.play:app-update-ktx@@2.1.0 */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.google.android.play.core.ktx.AppUpdateManagerKtxKt", f = "AppUpdateManagerKtx.kt", i = {}, l = {Command.CMD_OTA_INQUIRE_DEVICE_IF_CAN_UPDATE}, m = "requestAppUpdateInfo", n = {}, s = {})
/* loaded from: classes2.dex */
final class AppUpdateManagerKtxKt$requestAppUpdateInfo$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    AppUpdateManagerKtxKt$requestAppUpdateInfo$1(Continuation<? super AppUpdateManagerKtxKt$requestAppUpdateInfo$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return AppUpdateManagerKtxKt.requestAppUpdateInfo(null, this);
    }
}
