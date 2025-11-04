package com.wifiled.baselib.uicode.net;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.logging.HttpLoggingInterceptor;

/* compiled from: NetLongLogger.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/wifiled/baselib/uicode/net/NetLongLogger;", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "<init>", "()V", "log", "", "message", "", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NetLongLogger implements HttpLoggingInterceptor.Logger {
    @Override // okhttp3.logging.HttpLoggingInterceptor.Logger
    public void log(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (message.length() > 3000) {
            int i = 0;
            while (i < message.length()) {
                int i2 = i + 3000;
                if (i2 < message.length()) {
                    String str = "NetLog" + i;
                    String substring = message.substring(i, i2);
                    Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                    Log.i(str, substring);
                } else {
                    String str2 = "NetLog" + i;
                    String substring2 = message.substring(i, message.length());
                    Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
                    Log.i(str2, substring2);
                }
                i = i2;
            }
            return;
        }
        Log.i("NetLog", message);
    }
}
