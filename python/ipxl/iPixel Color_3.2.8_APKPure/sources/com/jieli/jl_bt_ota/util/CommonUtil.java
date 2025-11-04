package com.jieli.jl_bt_ota.util;

import android.content.Context;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Locale;

/* loaded from: classes2.dex */
public class CommonUtil {
    private static WeakReference<Context> a = null;
    public static final boolean isSupportAndroid12 = true;

    private static void a() {
        WeakReference<Context> weakReference = a;
        if (weakReference != null) {
            weakReference.clear();
            System.gc();
        }
    }

    public static void checkAllNotNull(Object... objArr) {
        for (Object obj : objArr) {
            obj.getClass();
        }
    }

    public static boolean checkHasConnectPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 31) {
            return checkHasPermission(context, "android.permission.BLUETOOTH_CONNECT");
        }
        return true;
    }

    public static boolean checkHasLocationPermission(Context context) {
        return Build.VERSION.SDK_INT >= 31 || checkHasPermission(context, "android.permission.ACCESS_FINE_LOCATION") || checkHasPermission(context, "android.permission.ACCESS_COARSE_LOCATION");
    }

    public static boolean checkHasPermission(Context context, String str) {
        return context != null && ActivityCompat.checkSelfPermission(context, str) == 0;
    }

    public static boolean checkHasScanPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 31) {
            return checkHasPermission(context, "android.permission.BLUETOOTH_SCAN");
        }
        return true;
    }

    public static <T> T checkNotNull(T t) {
        t.getClass();
        return t;
    }

    public static String formatString(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    public static long getCurrentTime() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static Context getMainContext() {
        WeakReference<Context> weakReference = a;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public static void setMainContext(Context context) {
        a = new WeakReference<>((Context) checkNotNull(context));
    }

    public static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
