package com.wifiled.ipixels.callback;

import androidx.autofill.HintConstants;
import com.wifiled.musiclib.player.constant.DbFinal;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CallBack.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u000f\u001a\u00020\n2!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\n0\u0005R7\u0010\u0004\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Lcom/wifiled/ipixels/callback/CallBack;", "", "<init>", "()V", "saveDiyImageAction", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, DbFinal.LOCAL_PATH, "", "getSaveDiyImageAction$app_googleRelease", "()Lkotlin/jvm/functions/Function1;", "setSaveDiyImageAction$app_googleRelease", "(Lkotlin/jvm/functions/Function1;)V", "onSaveDiyImage", "action", "result", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CallBack {
    private Function1<? super String, Unit> saveDiyImageAction;

    public final Function1<String, Unit> getSaveDiyImageAction$app_googleRelease() {
        return this.saveDiyImageAction;
    }

    public final void setSaveDiyImageAction$app_googleRelease(Function1<? super String, Unit> function1) {
        this.saveDiyImageAction = function1;
    }

    public final void onSaveDiyImage(Function1<? super String, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        this.saveDiyImageAction = action;
    }
}
