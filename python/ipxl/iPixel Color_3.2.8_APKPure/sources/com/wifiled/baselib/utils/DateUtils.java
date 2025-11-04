package com.wifiled.baselib.utils;

import android.content.Context;
import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes2.dex */
public class DateUtils {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String FORMAT_HH_MM = "HH:mm";
    public static final String FORMAT_HH_MM_SS = "HH:mm:ss";
    public static final String FORMAT_MMCDD = "MM月dd日";
    public static final String FORMAT_MMCDD_HH_MM = "MM月dd日 HH:mm";
    public static final String FORMAT_MM_DD_HH_MM = "MM-dd HH:mm";
    public static final String FORMAT_MM_DD_HH_MM_SS = "MM-dd HH:mm:ss";
    public static final String FORMAT_MM_SS = "mm:ss";
    public static final String FORMAT_YYYY = "yyyy";
    public static final String FORMAT_YYYY2MM2DD = "yyyy.MM.dd";
    public static final String FORMAT_YYYY2MM2DD_HH_MM = "yyyy.MM.dd HH:mm";
    public static final String FORMAT_YYYYCMMCDD = "yyyy年MM月dd日";
    public static final String FORMAT_YYYY_MM = "yyyy-MM";
    public static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final long ONE_DAY = 86400000;
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static boolean isThisWeek(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(2);
        int i = calendar.get(3);
        calendar.setTime(date);
        return calendar.get(3) == i;
    }

    public static boolean isToday(Date date) {
        return isThisTime(date, DATE_FORMAT);
    }

    public static boolean isThisMonth(Date date) {
        return isThisTime(date, FORMAT_YYYY_MM);
    }

    public static boolean isThisYear(Date date) {
        return isThisTime(date, FORMAT_YYYY);
    }

    public static boolean isYesterDay(Date date) {
        return (Calendar.getInstance().getTimeInMillis() / ONE_DAY) - (date.getTime() / ONE_DAY) == 1;
    }

    private static boolean isThisTime(Date date, String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        return simpleDateFormat.format(date).equals(simpleDateFormat.format(new Date()));
    }

    public static int getDayOfMonth(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i, i2, 0);
        return calendar.get(5);
    }

    public static int getDayOffset(long j, long j2) {
        if (j > j2) {
            j2 = getDayStartTime(getCalendar(j2)).getTimeInMillis();
        } else {
            j = getDayStartTime(getCalendar(j)).getTimeInMillis();
        }
        return (int) ((j - j2) / ONE_DAY);
    }

    public static Calendar getCalendar(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return calendar;
    }

    public static Calendar getDayStartTime(Calendar calendar) {
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar;
    }

    public static String getDurationInString(long j) {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(1) + "年" + (calendar.get(2) + 1) + "月" + calendar.get(5) + "日" + calendar.get(11) + ":" + calendar.get(12) + ":" + calendar.get(13);
    }

    public static int getYear() {
        return Calendar.getInstance().get(1);
    }

    public static int getMonth() {
        return Calendar.getInstance().get(2) + 1;
    }

    public static int getDay() {
        return Calendar.getInstance().get(5);
    }

    public static int getHourInt() {
        return Calendar.getInstance().get(11);
    }

    public static int getMinuteInt() {
        return Calendar.getInstance().get(12);
    }

    public static int getSecondInt() {
        return Calendar.getInstance().get(13);
    }

    public static byte[] getTimeByte() {
        return new byte[]{(byte) getHourInt(), (byte) getMinuteInt(), (byte) getSecondInt()};
    }

    public static int getWeekOfDate(Date date) {
        int[] iArr = {7, 1, 2, 3, 4, 5, 6};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(7) - 1;
        if (i < 0) {
            i = 0;
        }
        return iArr[i];
    }

    public static long convertToLong(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str)) {
                return 0L;
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = TIME_FORMAT;
            }
            return new SimpleDateFormat(str2, Locale.getDefault()).parse(str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static String convertToString(long j) {
        if (j > 0) {
            return new SimpleDateFormat(TIME_FORMAT, Locale.getDefault()).format(new Date(j));
        }
        return "";
    }

    public static int getWeekStatus(Context context, byte[] bArr) {
        if (bArr != null && bArr.length == 8) {
            byte b = bArr[6];
            if (b == 1 && bArr[5] == 1 && bArr[4] == 1 && bArr[3] == 1 && bArr[2] == 1 && bArr[1] == 1 && bArr[0] == 1) {
                return 1;
            }
            if (b == 0 && bArr[5] == 0 && bArr[4] == 0 && bArr[3] == 0 && bArr[2] == 0 && bArr[1] == 0 && bArr[0] == 0) {
                return 0;
            }
        }
        return 2;
    }
}
