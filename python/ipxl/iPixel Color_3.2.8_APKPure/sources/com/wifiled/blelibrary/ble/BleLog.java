package com.wifiled.blelibrary.ble;

import android.text.TextUtils;
import android.util.Log;
import androidx.camera.core.CameraInfo;
import java.util.Locale;

/* loaded from: classes2.dex */
public class BleLog {
    public static String TAG = "AndroidBLE";
    public static boolean isDebug;

    public static void init(Options options) {
        isDebug = options.logBleEnable;
        if (TextUtils.isEmpty(options.logTAG)) {
            return;
        }
        TAG = options.logTAG;
    }

    private static String getSubTag(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Number) {
            return String.valueOf(obj);
        }
        return obj.getClass().getSimpleName();
    }

    public static void e(Object obj, String str) {
        if (isDebug) {
            Log.e(TAG, buildMessge(getSubTag(obj), str));
        }
    }

    public static void i(Object obj, String str) {
        if (isDebug) {
            Log.i(TAG, buildMessge(getSubTag(obj), str));
        }
    }

    public static void w(Object obj, String str) {
        if (isDebug) {
            Log.w(TAG, buildMessge(getSubTag(obj), str));
        }
    }

    public static void d(Object obj, String str) {
        if (isDebug) {
            Log.d(TAG, buildMessge(getSubTag(obj), str));
        }
    }

    private static String buildMessge(String str, String str2) {
        return String.format(Locale.CHINA, "[%d] %s: %s", Long.valueOf(Thread.currentThread().getId()), str, str2);
    }

    private static String buildMessage(String str, Object... objArr) {
        String str2;
        if (objArr != null) {
            str = String.format(Locale.CHINA, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        int i = 2;
        while (true) {
            if (i >= stackTrace.length) {
                str2 = CameraInfo.IMPLEMENTATION_TYPE_UNKNOWN;
                break;
            }
            if (!stackTrace[i].getClass().equals(BleLog.class)) {
                String className = stackTrace[i].getClassName();
                String substring = className.substring(className.lastIndexOf(46) + 1);
                str2 = substring.substring(substring.lastIndexOf(36) + 1) + "." + stackTrace[i].getMethodName();
                break;
            }
            i++;
        }
        return String.format(Locale.CHINA, "[%d] %s: %s", Long.valueOf(Thread.currentThread().getId()), str2, str);
    }
}
