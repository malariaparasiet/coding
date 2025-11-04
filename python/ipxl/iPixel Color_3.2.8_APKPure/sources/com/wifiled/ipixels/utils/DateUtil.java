package com.wifiled.ipixels.utils;

import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class DateUtil {
    public static String convertSecondsToTime(long seconds) {
        if (seconds <= 0) {
            return "00:00";
        }
        int i = (int) seconds;
        int i2 = i / 60;
        if (i2 < 60) {
            return unitFormat(i2) + ":" + unitFormat(i % 60);
        }
        int i3 = i2 / 60;
        if (i3 > 99) {
            return "99:59:59";
        }
        return unitFormat(i3) + ":" + unitFormat(i2 % 60) + ":" + unitFormat((int) ((seconds - (i3 * 3600)) - (r1 * 60)));
    }

    public static String convertSecondsToFormat(long seconds, String format) {
        if (TextUtils.isEmpty(format)) {
            return "";
        }
        return new SimpleDateFormat(format, Locale.getDefault()).format(new Date(seconds));
    }

    private static String unitFormat(int i) {
        if (i >= 0 && i < 10) {
            return "0" + Integer.toString(i);
        }
        return "" + i;
    }
}
