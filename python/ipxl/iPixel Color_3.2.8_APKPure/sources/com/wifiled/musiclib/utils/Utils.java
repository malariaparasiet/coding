package com.wifiled.musiclib.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/* loaded from: classes3.dex */
public class Utils {
    public static String getAppName(Context context) {
        try {
            return context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
