package com.wifiled.baselib.utils;

import android.os.Handler;
import androidx.core.os.HandlerCompat;

/* loaded from: classes2.dex */
public class HandlerUtils {
    private static final Handler handler = new Handler();

    public static int setTimeout(long j, Runnable runnable) {
        int hashCode = runnable.hashCode();
        setTimeout(hashCode, j, runnable);
        return hashCode;
    }

    public static void setTimeout(int i, long j, Runnable runnable) {
        HandlerCompat.postDelayed(handler, runnable, Integer.valueOf(i), j);
        LogUtils.logi("HandlerUtils>>>[setTimeout]: " + i, new Object[0]);
    }

    public static void removeTimeout(int i) {
        handler.removeCallbacksAndMessages(Integer.valueOf(i));
    }

    public static void removeTimeout(Runnable runnable) {
        handler.removeCallbacks(runnable);
    }

    public static void clearTimeout() {
        handler.removeCallbacksAndMessages(null);
    }
}
