package com.wifiled.gameview.snake.utils;

import android.util.Log;

/* loaded from: classes3.dex */
public class LogUtil {
    public static boolean LOG_OPEN = true;

    public static void e(Class cls, String str, String str2) {
        if (LOG_OPEN) {
            Log.e(str, cls.getSimpleName() + "----->>>>>>" + str2);
        }
    }

    public static void d(Class cls, String str, String str2) {
        if (LOG_OPEN) {
            Log.d(str, cls.getName() + "----->>>>>>" + str2);
        }
    }
}
