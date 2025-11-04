package com.wifiled.ipixels.common;

import com.wifiled.baselib.utils.LogUtils;
import kotlin.Metadata;

/* compiled from: DispatcherWithTimeout.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001¨\u0006\u0002"}, d2 = {"main", "", "app_googleRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DispatcherWithTimeoutKt {
    public static final void main() {
        new Dispatcher().add(new Runnable() { // from class: com.wifiled.ipixels.common.DispatcherWithTimeoutKt$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DispatcherWithTimeoutKt.main$lambda$0();
            }
        }).execute();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void main$lambda$0() {
        LogUtils.logi(">>>[main]: ", new Object[0]);
    }
}
