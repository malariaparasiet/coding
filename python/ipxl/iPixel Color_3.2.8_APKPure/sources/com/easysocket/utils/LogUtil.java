package com.easysocket.utils;

import android.util.Log;

/* loaded from: classes2.dex */
public class LogUtil {
    public static final String LOGTAG = "easysocket";
    public static boolean debugEnabled = false;

    private static String getDebugInfo() {
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        return stackTrace[2].getClassName() + " " + stackTrace[2].getMethodName() + "():" + stackTrace[2].getLineNumber() + " ";
    }

    private static String getLogInfoByArray(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void i(String... strArr) {
        if (debugEnabled) {
            i(LOGTAG, getDebugInfo() + getLogInfoByArray(strArr));
        }
    }

    public static void e(Throwable th) {
        if (debugEnabled) {
            Log.e(LOGTAG, getDebugInfo(), th);
        }
    }

    public static void e(String... strArr) {
        if (debugEnabled) {
            e(LOGTAG, getDebugInfo() + getLogInfoByArray(strArr));
        }
    }

    public static void d(String... strArr) {
        if (debugEnabled) {
            d(LOGTAG, getDebugInfo() + getLogInfoByArray(strArr));
        }
    }

    public static void v(String... strArr) {
        if (debugEnabled) {
            v(LOGTAG, getDebugInfo() + getLogInfoByArray(strArr));
        }
    }

    public static void w(String... strArr) {
        if (debugEnabled) {
            w(LOGTAG, getDebugInfo() + getLogInfoByArray(strArr));
        }
    }

    private static void i(String str, String str2) {
        System.out.println(str + "：" + str2);
    }

    private static void d(String str, String str2) {
        System.out.println(str + "：" + str2);
    }

    private static void v(String str, String str2) {
        System.out.println(str + "：" + str2);
    }

    private static void e(String str, String str2) {
        System.err.println(str + "：" + str2);
    }

    private static void w(String str, String str2) {
        System.err.println(str + "：" + str2);
    }
}
